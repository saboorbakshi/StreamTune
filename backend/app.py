#Imports
from flask import Flask, request, jsonify
import firebase_admin
from firebase_admin import db, credentials

app = Flask(__name__)

cred = credentials.Certificate('credentials.json')
firebase_admin.initialize_app(cred)

@app.route("/")
def home():
    return "Demo root"

if __name__ == "__main__":
    app.run(debug=True)