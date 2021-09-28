# MobileUETSDK

Integrate the MobileUETSDK to your Android project.

1.Import the JAR to your project.

  1-1. Make an directory named 'libs' in /YourProjectName/app.Put the uetsdk.jar in that directory.
  
       ![image](https://user-images.githubusercontent.com/91300612/135041522-096611c0-9bb4-4bb9-aeac-e61130ffc114.png)
       
  1-2. Add dependency of uetsdk to your gradle file in /YourProjectName/app/build.gradle
  
       ![image](https://user-images.githubusercontent.com/91300612/135042149-da0b5580-ebfe-4831-ab5f-1b64bc088882.png)

       ![image](https://user-images.githubusercontent.com/91300612/135042347-0a309e6b-485b-404d-8175-3d517f623d2b.png)

2. Call the uetsdk interface in code.

  2-1. Make a LogSendingManager object. And, set the anroid context to it.
  
       ![image](https://user-images.githubusercontent.com/91300612/135043764-05eb495c-b5b9-4ef7-b15f-3726180f9644.png)
       
  2-2. Set the parmeters to the LogSendingManager. Parameters specification as follow.

  2-3. Call the send event function to send the info.
  
      ![image](https://user-images.githubusercontent.com/91300612/135044371-f450385a-d539-4035-934d-174b6a8f2284.png)


      

