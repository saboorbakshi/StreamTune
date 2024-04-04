import unittest
import os 
from app import urlToMp3

class TestCalculations(unittest.TestCase):

    def test_sum(self):
        fileName, thumbnail_url, author, duration = urlToMp3("https://www.youtube.com/watch?v=kffacxfA7G4")
        self.assertEqual(fileName.endswith("Baby.mp3"), True, 'The file name is wrong.')
        os.remove(fileName)

    def test_thumbnail_url(self):
        fileName, thumbnail_url, author, duration = urlToMp3("https://www.youtube.com/watch?v=kffacxfA7G4")
        thumbnail_url = thumbnail_url.split("?")[0]
        self.assertEqual(thumbnail_url.endswith(".jpg"), True, 'The song thumbnail url is wrong.')

    def test_author(self):
        fileName, thumbnail_url, author, duration = urlToMp3("https://www.youtube.com/watch?v=kffacxfA7G4")
        self.assertEqual(author, "Justin Bieber", 'The song author is wrong.')

    def test_duration(self):
        fileName, thumbnail_url, author, duration = urlToMp3("https://www.youtube.com/watch?v=kffacxfA7G4")
        self.assertEqual(duration, 219, 'The mp3 file duration is wrong.')



if __name__ == '__main__':
    unittest.main()