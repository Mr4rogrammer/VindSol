import firebase_admin

from firebase_admin import db

import firebase_admin
from firebase_admin import credentials
from firebase_admin import storage
import os




cred_obj = firebase_admin.credentials.Certificate('info.json')
default_app = firebase_admin.initialize_app(cred_obj, {
	'databaseURL':'https://vindsql-14117-default-rtdb.firebaseio.com',

        'storageBucket' : 'vindsql-14117.appspot.com'
	})


ref = db.reference("highpdf")


bucket = storage.bucket()
directory = 'D:/KK/py/high/'

for foldername in os.listdir(directory):
    if str(foldername[0]) != '.':       
         for image in os.listdir(directory + foldername):
             blob = bucket.blob(f'{foldername}/{image}')
             imagePath = directory + foldername + '/' + image
             print(image)
             blob.upload_from_filename(imagePath)
             blob.make_public()
             print(blob.public_url)
             image=image.replace(".","")
             ref.child(image.replace(".pdf","")).child("data").child("url").set(blob.public_url)
