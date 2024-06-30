import os

import pyupbit
from dotenv import load_dotenv
from openai import OpenAI

# Load environment variables from .env file
load_dotenv()

# Initialize OpenAI client
client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))

# Initialize Upbit client
upbit = pyupbit.Upbit(os.getenv("UPBIT_ACCESS_KEY"), os.getenv("UPBIT_SECRET_KEY"))

# Test OpenAI API call
try:
    completion = client.chat.completions.create(
        model="gpt-4o",
        messages=[
            {"role": "system", "content": "You are helpful assistant."},
            {"role": "user", "content": "Hello World~"}
        ]
    )
    print(completion.choices[0].message['content'])
except Exception as e:
    print(f"Error with OpenAI API: {e}")

# Test Upbit API call
try:
    balance = upbit.get_balance("KRW")
    print(balance)
except Exception as e:
    print(f"Error with Upbit API: {e}")
