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
To use/consume this API, you will need to follow the bellow API structure:

* `Root`
`curl http://localhost:8080/`
```json
{
  "start": "2018-05-02T23:00:00+00:00",
  "services": [
    {
      "name": "status",
      "uri": "http://localhosst:8080/status"
    },
    {
      "name": "log",
      "uri": "http://localhost:8080/status"
    }
  ]
}
```

