# Project_Timer

#### Android Timer App
> * The Project Timer App is a project timer to track all projects and hobbies
> * The timer has a control countdown that adds time to a time counter 
> * The timer has start,pause,resume functions on.
> * The timer has inputs to enter the name of your task.
> * It has inputs for notes tat track goals,and information about your task.
> * The timer has 4 timers to track multiple tasks at the same time.
---
#### Check out the Timer Images
Main activity 
![](img/mainActivity.png)
---
Edit activity
![](img/edit.png)
---
Notes activity
![](img/notes.png)
---

---
#### Code that I used to save instance state
```
    @Override
    protected void onStop(){
       super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name1",name1);
        editor.putLong("startTimeInMillis1", startTimeMilliseconds1);
        editor.putLong("millisLeft1", timeLeftMilliseconds1);
        editor.putBoolean("timerRunning1", timerIsRunning1);
        editor.putLong("endTime1", endTime1);
        editor.putLong("chro1",chrono1Time);
        editor.apply();
        if(countDownTimer1 != null){
            countDownTimer1.cancel();
        }
  }  
  
```
#### Code I used to restore instance state
```
    @Override
    protected void onStart(){
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        startTimeMilliseconds1 = prefs.getLong("startTimeInMillis1",600000);
        timeLeftMilliseconds1 = prefs.getLong("millisLeft1", startTimeMilliseconds1);
        timerIsRunning1 = prefs.getBoolean("timerRunning1",false);
        chrono1Time = prefs.getLong("chro1",0);
        //This resets total time if check box in edit is checked
        if(ch1){
            chronometer1.setBase(SystemClock.elapsedRealtime());
            chrono1Time = 0;
        }
        else{
            chronometer1.setBase(SystemClock.elapsedRealtime() + chrono1Time );
        }
        updateCountDownText1();
        updateWatchInterface1();
        if(timerIsRunning1){
            endTime1 = prefs.getLong("endTime",0);
            timeLeftMilliseconds1 = endTime1 - System.currentTimeMillis();
            if(timeLeftMilliseconds1 < 0){
                timeLeftMilliseconds1 = 0;
                timerIsRunning1 = false;
                updateCountDownText1();
                updateWatchInterface1();
            }
            else{
              startTimer1();
            }
        }
```
---
#### Why I created this timer 
- I wanted to create a timer because I am always curious of how much time I spend on certin tasks.
- I created the timer to get more experiance with java and xml 
- To get experiance with Android Studio creating apps
---
#### Instructions for using this App 
- The Right side of the app is the timer control
- Enter the time in minutes and hit the set button , this sets the countdown time
- Hit the start button to begin the countdown, the countdown time is added to the total time
- The total Time is displayed on the left
- the reset button on the control only resets the countdown time that was entered earlier
- The Edit button pauses the timers and goes to the second activity where you can change the name,reset total times, and add notes
- In the Edit activity you have checkboxes to reset timers or rename timers
- The save button gets the name from the text input and rename radio button and sets the name on the main activity
- The return button just returns
- The note button goes to the notes activity
- The notes can be clicked on to be edited and a save button to save, only one note can be saved at a time
