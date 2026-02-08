# Imagica: AI Image Caption Generator

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)
![ML Kit](https://img.shields.io/badge/Google%20ML%20Kit-4285F4?style=for-the-badge&logo=google&logoColor=white)

**Imagica** is a smart Android application designed to help users find the perfect caption for their social media posts. By leveraging **Google ML Kit**, the app analyzes uploaded images, detects key elements (like "Sunset", "Beach", "Rock"), and automatically generates a list of creative, aesthetic, and relevant captions.


## Key Features

### User Authentication
* **Secure Sign Up/Login:** Powered by **Firebase Authentication**.
* **User Profile:** Stores basic user details (Name, Gender, DOB) securely.

### Smart Image Analysis
* **Image Labeling:** Uses **Google ML Kit's Image Labeling API** to scan uploaded photos and identify objects, scenes, and activities (e.g., detecting "Sunset", "Vacation", "Rock").
* **Keyword Extraction:** Filters the most relevant tags from the image to ensure context-aware results.

### Caption Generator
* **Creative Suggestions:** Maps detected labels to a curated database of quotes and captions.
* **Variety:** Provides multiple caption options (Short, poetic, funny, or inspirational).
* **Refresh Option:** Users can hit "Refresh" to generate a new set of captions if they want more choices.


## How It Works

The application follows a simple 3-step linear flow:

1.  **Input:** The user uploads an image from their gallery.
2.  **Processing:** The app displays a "Loading" state while ML Kit analyzes the image data on-device.
3.  **Output:** Relevant keywords are identified, and corresponding captions are presented to the user.