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

@app.post("/convert")
def convert():
    data = request.get_json()
    songID = extract.video_id(data["url"])
    #check if songs exists
    snapshot = ref.child('songs').order_by_key().equal_to(songID).get()
    if len(snapshot) == 0:
        fileName = urlToMp3(data["url"]) 
        bucket = storage.bucket()
        blob = bucket.blob("mp3/" + songID + ".mp3")
        blob.upload_from_filename(fileName)
        blob.make_public()
        fileName = fileName.split("/")[-1]
        users_ref = ref.child('songs')
        users_ref.child(songID).set({
            "id": songID,
            'name': fileName,
            'url': blob.public_url
        })
    else:
        fileName = snapshot[songID]["name"]
        url = snapshot[songID]['url']
    decoded_token = auth.verify_id_token(request.headers["Authorization"])
    uid = decoded_token['uid']
    users_songs = ref.child('userSongs')
    users_songs.child(uid).child(songID).set({
        "id": songID,
        'name': fileName,
        'url': url
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
    return new_file

if __name__ == "__main__":
    app.run(debug=True)