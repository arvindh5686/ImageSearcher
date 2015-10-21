# Project 2 - ImageSearcher

ImageSearcher is an android app that allows a user to search for images on web using simple filters. The app utilizes [Google Image Search API](https://developers.google.com/image-search/). Please note that API has been officially deprecated as of May 26, 2011.

Time spent: 8 hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can **search for images** by specifying a query and launching a search. Search displays a grid of image results from the Google Image API.
* [X] User can click on "settings" which allows selection of **advanced search options** to filter results
* [X] User can configure advanced search filters such as:
  * [X] Size (small, medium, large, extra-large)
  * [X] Color filter (black, blue, brown, gray, green, etc...)
  * [X] Type (faces, photo, clip art, line art)
  * [X] Site (espn.com)
* [X] Subsequent searches have any filters applied to the search results
* [X] User can tap on any image in results to see the image **full-screen**
* [X] User can **scroll down to see more images**. The maximum number of images is 64 (limited by API).

The following **optional** features are implemented:

* [X] Implements robust error handling, [check if internet is available](http://guides.codepath.com/android/Sending-and-Managing-Network-Requests#checking-for-network-connectivity), handle error cases, network failures
* [X] Used the **ActionBar SearchView** or custom layout as the query box instead of an EditText
* [X] User can **share an image** to their friends or email it to themselves
* [ ] Replaced Filter Settings Activity with a lightweight modal overlay
* [ ] Improved the user interface and experiment with image assets and/or styling and coloring

The following **bonus** features are implemented:

* [ ] Used the [StaggeredGridView](https://github.com/f-barth/AndroidStaggeredGrid) to display improve the grid of image results
* [ ] User can [zoom or pan images](https://github.com/MikeOrtiz/TouchImageView) displayed in full-screen detail view

## Video Walkthrough 

Here's a walkthrough of implemented user stories:
http://i.imgur.com/aZsJU1R.gif

<img src='http://i.imgur.com/aZsJU1R.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).


## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
