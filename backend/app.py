#Imports
from flask import Flask, request, jsonify, Response
import firebase_admin
from firebase_admin import db, credentials, storage, auth
from pytube import YouTube, extract 
import os 
import youtube_dl

app = Flask(__name__)

cred = credentials.Certificate('credentials.json')
firebase_admin.initialize_app(cred, {'storageBucket': 'streamtune-c6019.appspot.com', 
                                    'databaseURL': 'https://streamtune-c6019-default-rtdb.firebaseio.com/'})
ref = db.reference('/')

@app.route("/")
def home():
    return "Demo root"

@app.post('/convert') 
def convert():
    try:
        decoded_token = auth.verify_id_token(request.headers["Authorization"])
    except:
        return "Unauthorized", 403
    uid = decoded_token['uid']
    data = request.get_json()
    songID = extract.video_id(data["url"])
    #check if songs exists
    snapshot = ref.child('AllSongs').order_by_key().equal_to(songID).get()
    if len(snapshot) == 0:
        fileName, thumbnail_url, author = urlToMp3(data["url"]) 
        bucket = storage.bucket()
        blob = bucket.blob("mp3/" + songID + ".mp3")
        blob.upload_from_filename(fileName)
        blob.make_public()
        os.remove(fileName)
        fileName = fileName.split("/")[-1]
        users_ref = ref.child('AllSongs')
        users_ref.child(songID).set({
            "id": songID,
            'name': fileName[:-4],
            'url': blob.public_url,
            'album_cover': thumbnail_url,
            'artist': author
        })
        url = blob.public_url
    else:
        fileName = snapshot[songID]["name"]
        url = snapshot[songID]['url']
        thumbnail_url = snapshot[songID]['album_cover']
        author = snapshot[songID].get('artist', "")
    users_songs = ref.child('UserPlaylists')
    if len(users_songs.order_by_key().equal_to(uid).get()) == 0:
        users_songs.child(uid).child("Added Songs").set({
            'name': "Added Songs"
        })
    users_songs.child(uid).child("Added Songs").child("songs").child(songID).set({
        "id": songID,
        'name': fileName[:-4],
        'url': url,
        'album_cover': thumbnail_url,
        'artist': author
    })
    return "success", 200

def urlToMp3(url):
    yt = YouTube(url) 
    video = yt.streams.filter(only_audio=True).first()
    destination = './tmp/mp3'
    out_file = video.download(output_path=destination)
    base, ext = os.path.splitext(out_file)
    new_file = base + '.mp3'
    os.rename(out_file, new_file)
    return new_file, yt.thumbnail_url, yt.author

@app.post('/createPlaylist') 
def createPlaylist():
    try:
        decoded_token = auth.verify_id_token(request.headers["Authorization"])
    except:
        return "Unauthorized", 403
    uid = decoded_token['uid']
    data = request.get_json()
    name = data["name"]
    users_songs = ref.child('UserPlaylists')
    if len(users_songs.order_by_key().equal_to(uid).get()) == 0:
        users_songs.child(uid).child("Added Songs").set({
            'name': "Added Songs"
        })
    ref.child('UserPlaylists').child(uid).child(name).set({
        'name': name
    })
    return "success", 200

@app.post('/addToPlaylist') 
def addToPlaylist():
    try:
        decoded_token = auth.verify_id_token(request.headers["Authorization"])
    except:
        return "Unauthorized", 403
    uid = decoded_token['uid']
    data = request.get_json()
    playlistName = data["playlist_name"]
    songID = data["song_id"]
    songSnapshot = ref.child('UserPlaylists').child(uid).child("Added Songs").child("songs").child(songID).get()
    if len(songSnapshot) == 0:
        return "Song not found", 404
    snapshot = ref.child('UserPlaylists').child(uid).child(playlistName).get()
    if len(snapshot) == 0:
        return "Playlist not found", 404
    ref.child('UserPlaylists').child(uid).child(playlistName).child("songs").child(songID).set({
        "id": songID,
        'name': songSnapshot["name"],
        'url': songSnapshot['url'],
        'album_cover': songSnapshot['album_cover'],
        'artist': songSnapshot.get('artist', "")
    })
    return "success", 200

@app.get('/getPlaylists') 
def getPlaylists():
    try:
        decoded_token = auth.verify_id_token(request.headers["Authorization"])
    except:
        return "Unauthorized", 403
    uid = decoded_token['uid']
    users_songs = ref.child('UserPlaylists')
    if len(users_songs.order_by_key().equal_to(uid).get()) == 0:
        return [], 200
    snapshot = ref.child('UserPlaylists').child(uid).get()
    playlistResponse = []
    for playlistName, playlist in snapshot.items():
        playlistResponse.append({
            'name': playlistName,
            'songs': [playlist['songs'][key] for key in playlist['songs']] if 'songs' in playlist else []
        })
    return playlistResponse, 200

if __name__ == "__main__":
    app.run(debug=True)