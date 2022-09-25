Call Monitor Task
=================

A call monitor app that runs as an HTTP server in the background, and is capable of serving 
telephone call data to clients on the same network, logs those telephone calls, and displays
this call log via its UI.

Call Monitor is currently under heavy development and testing.

Introduction
------------

Call Monitor have been build using the Android Jetpack components.
Android Jetpack is a et of components, tools and guidance to make Android apps. They bring
together the existing Support Library and Architecture Components and arrange them into four 
categories.

Getting Started
---------------
This project uses Gradle build system. To build this project, use the `gradlew build` command
or use the "Import Project" in Android Studio.

### Call Monitor API
Call Monitor app exposes call logs as an API which allow clients from the same network to 
consume these logs.

The are 3 API endpoint which exposes log `status`, `log` and `services`.

`Important notes before starting:`
```
* Start/Stop the server to when you want to start/stop exposing the API log into the network
* Once the app is running, get the IP address of your local network (it's displayed on the app UI), 
along with the port number to be able to hit the api endpoint.
```
To use/consume this API, you will need to follow the bellow API structure:

* `Root`
`curl http://10.0.2.60:8080/`
```json
{
  "start": "2018-05-02T23:00:00+00:00",
  "services": [
    {
      "name": "status",
      "uri": "http://10.0.2.60:8080/status"
    },
    {
      "name": "log",
      "uri": "http://10.0.2.60:8080/status"
    }
  ]
}
```
--------------------------------------------

* `Status`
`curl http://10.0.2.60:8080/status`
```json
{
  "ongoing": "true",
  "number": "+12025550108",
  "name": "Michael Vee"
},
{
  "ongoing": "false",
  "number": "+12072855190",
  "name": "Jordan Sparks"
},
{
  "ongoing": "true",
  "number": "+12025550235",
  "name": "Lil Kim"
}
```
------------------------------

* `Log`
`curl http://10.0.2.60:8080/log`
```json
[
  {
    "beginning": "2018-05-02T23:00:00+00:00",
    "duration": "780",
    "number": "+12025550235",
    "name": "Lil Kim",
    "timesQueried": "3"
  },
  {
    "beginning": "2018-05-02T23:00:00+00:00",
    "duration": "128",
    "number": "+12072855190",
    "name": "Jordan Sparks",
    "timesQueried": "2"
  },
  {
    "beginning": "2018-05-02T23:00:00+00:00",
    "duration": "330",
    "number": "+12025550108",
    "name": "Michael Vee",
    "timesQueried": "4"
  }
]
```
---------------------------------------------


Libraries used
--------------
Call Monitor app uses the following features:

* [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
  * [Android KTX][1] - Write more concise, idiomatic Kotlin code.
  * [Test][2] - An Android testing framework for unit and runtime UI tests.
* [Architecture][3] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
  * [Lifecycles][4] - Create a UI that automatically responds to lifecycle events.
  * [LiveData][5] - Build data objects that notify views when the underlying database changes.
  * [Navigation][6] - Handle everything needed for in-app navigation.
  * [Room][7] - Access your app's SQLite database with in-app objects and compile-time checks.
  * [ViewModel][8] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
* [UI][9] - Details on why and how to use UI Components in your apps - together or separate
* Third party and miscellaneous libraries
  * [Hilt][10]: for [dependency injection][11]
  * [Kotlin Coroutines][12] for managing background threads with simplified code and reducing needs for callbacks

[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/kotlin/ktx
[2]: https://developer.android.com/training/testing/
[3]: https://developer.android.com/jetpack/arch/
[4]: https://developer.android.com/topic/libraries/architecture/lifecycle
[5]: https://developer.android.com/topic/libraries/architecture/livedata
[6]: https://developer.android.com/topic/libraries/architecture/navigation/
[7]: https://developer.android.com/topic/libraries/architecture/room
[8]: https://developer.android.com/topic/libraries/architecture/viewmodel
[9]: https://developer.android.com/guide/topics/ui
[10]: https://developer.android.com/training/dependency-injection/hilt-android
[11]: https://developer.android.com/training/dependency-injection
[12]: https://kotlinlang.org/docs/reference/coroutines-overview.html
