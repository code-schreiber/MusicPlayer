[![Build Status](https://travis-ci.org/code-schreiber/MusicPlayer.svg?branch=master)](https://travis-ci.org/code-schreiber/MusicPlayer) 
[![Open Source Love svg3](https://badges.frapsoft.com/os/v3/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)

<p align="center">
 <b>🎵 MusicPlayer 🎶</b>
 <br>
 <img src='https://github.com/code-schreiber/MusicPlayer/raw/master/app/src/main/ic_launcher-web.png' width='100' height='100'/>
</p>
  
  
This project is a demo android app that connects to the [HearThisAt API](https://hearthis.at/api-v2/) and shows a list of top artists with their tracks. When a track is selected the app streams it until another track is selected.  
Audio keeps on playing while browsing artists/songs. Playback stops by either quiting the app via back button or by swiping it out from the list of recent apps.

##### Technical details and used technologies
This project is written in Kotlin and uses Model-View-Presenter for better testability.

The app is built and unit tested with the help of Travis CI.

###### External libraries used
See [Dependencies.kt](https://github.com/code-schreiber/MusicPlayer/blob/master/buildSrc/src/main/java/Dependencies.kt)
* **Retrofit** for network calls
* **Moshi** for JSON parsing
* **RxAndroid** for getting data from network/cache
* **Fresco** for image loading
* **Dagger** for dependency injection
* **Butterknife** for view injection in activities
* **Timber** for logging
* **MockitoKotlin** for testing behaviour
* **Kluent** for assertions in unit tests
