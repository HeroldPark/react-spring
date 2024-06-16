"""
This script demonstrates how to use the SerpApi to get Google search results using the GoogleSearch class.
C:\Program Files\Python312\python.exe
pip install google-search-results
usage : python search_example.py
"""

import sys

from serpapi import GoogleSearch

print(sys.executable)

# Function to read API key from a file
def read_api_key(file_path):
    with open(file_path, 'r') as file:
        return file.read().strip()

# Read API key from 'api_key.txt'
api_key = read_api_key('api_key.txt')

# Replace 'YOUR_API_KEY' with your actual SerpApi key
params = {
  "q": "Coffee",
  "location": "Austin, Texas, United States",
  "hl": "en",
  "gl": "us",
  "google_domain": "google.com",
  "api_key": api_key
}

search = GoogleSearch(params)
results = search.get_dict()

# docs/serpapi_result.txt
print(results)
