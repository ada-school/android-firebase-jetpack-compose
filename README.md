<img align="right" src="https://github.com/ada-school/module-template/blob/main/ada.png">


## Jetpack Compose Items List with Firestore

Use Firestore and Jetpack Compose to display a list of items.

**Learning Objectives**

- [ ]  Explain what Firebase is.
- [ ]  Configure Firebase Android Project.
- [ ]  Use Firestore and Jetpack Compose to display a list of Items. 


## Growth Mindset 

Let's have an *Growth Mindset* and learn how to integrate two powerful technologies:
* Firestore Realtime Database
* Jetpack Compose

**Main Topics**

* Firestore
* Jetpack Compose
* Firebase
* Android



## Codelab 🧪

🗣️ "I hear and I forget I see and I remember I do and I understand." Confucius



### Part 1: Project Configuration
1. Create an account on <a hrefe="https://console.firebase.google.com/" target="_blanl">Firebase</>.
2. Create a new project on the <a hrefe="https://console.firebase.google.com/" target="_blanl">Firebase Console</>.
4. Download this repository and open it on Android Studio.
5. Open the *build.gradle(:app)* file and change the project id so it is unique:
  ```gradle

      defaultConfig {
          applicationId "org.adaschool.firebase.compose.<replace_with_unique_name_to_register_project>"
  ```
6. Add the Android configuration to the Firebase project with Android(using the same Android project id create on step 5).
7. Follow the configuration steps on the <a hrefe="https://console.firebase.google.com/" target="_blanl">Firestore console</> until you donwload and add the *google-services.json* file into your Android project.
8. Run your project and verify that everything works by adding a new message and seeing it on the <a hrefe="https://console.firebase.google.com/" target="_blanl">Firestore console</>.

### Advance Challenge: Create your Own Prototype

1. Modify this prototype to create a more complex model, for instance add more fields to the form and then display them on the list item.

   ***Hint***: You could create a messageing App such as Telegram or Whatsapp.

