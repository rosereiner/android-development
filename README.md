# android-development

Two Android Applications created in Spring 2019.

**Flag Game App**
Created an android app that was a multi-level flag game. I was given .zip file with pics of flags and json files of the two letter country initals and country name i.e. "US":"United States"
1. Read raw JSON file in and parsed the object into an arraylist
   a. Read in the raw file, which wrote it out as a string
   b. Turned the string bakc into a json object
   c. Looped through the JSON object and got the name from the object which is the two letter country pair like "US" for United States
2. Main Activity
   a. The onCreate() in the main activity lists 3 buttons, each that went to a different activity
      Used onClickListener and an Intent object to change to the four different activities 
      
3. 4 Activities
    1. GuessingFlags - 3 random flags are displayed and a country name that corresponds to one of the flags. The user chooses which flag corresponds to the country name shown.
    2. CountryActivity - A Spinner that holds a drop-down list of country names and a random flag is presented. The user chooses from the drop-down list which country's flag is 
                         shown.
    3. Guess_Hints2 -    A random flag is displayed with x-amount of dashed lines corresponding to the number of letters for the country's name. For example, if the United States flag is shown, then the dashed lines would appear like " _ _ _ _ _ _   _ _ _ _ _ _ ". The user types in the letters they think are in the unrevealed country name.
    
    
**Cooking Helper**
Android application that allowed a user to enter in recipes and it's corresponding ingredients. 
The user could add, view, edit, and delete an existing food item. 
The items a user added was stored inside the built in SQLite database in Android Studio. 
