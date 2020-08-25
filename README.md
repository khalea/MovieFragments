# MovieFragments

* Super basic Android app using lists, fragments, and the TMDB api to search for movies.*

### Demo

Featured movies are shown on the 'homepage' in a horizontally scrolling card view. You can click on a card to view more details about the movie. Using the search in the toolbar, you can find details on more movies as well.

<img src="/Android/MovieFragments/MoviesApp.gif" width="360" height="640"/>

### Intro & Components

- RecyclerView + MovieRVAdapter -> The list & respective adapter that handles binding & displaying data in their respective rows.

- DetailFragment -> A detail view that expands info about a movie selected from the RecyclerView. 

- MovieAPI -> Class that aids in interacting with TMDB. Uses the [Volley Library](https://developer.android.com/training/volley) to gather movie data.

- MovieContent -> Movie Object class, plus a bootlegged way to store data. 😅

### Known issues

- [ ] Ratings in the detail views show 10 stars instead of 5
- [ ] First batch of search results loads in twice (verified in logcat)
- [ ] Searches with 1 page of results will scroll infinitely (add check on "total_pages")

### Remaining TODOs

- [X] Load in movie posters
- [X] Add pagination for results
- [X] Auto-refresh RecyclerView
- [ ] Search autocomplete
- [X] Search history
- [ ] Interaction/animation/notif when no results are found
