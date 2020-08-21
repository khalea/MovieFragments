# MovieFragments

*Android dummy app using lists, fragments, TMDB api, etc*

### Intro & Components

- RecyclerView + MovieRVAdapter -> The list & respective adapter that handles binding & displaying data in their respective rows.

- DetailFragment -> A detail view that expands info about a movie selected from the RecyclerView. 

- MovieAPI -> Class that aids in interacting with TMDB. Uses the [Volley Library](https://developer.android.com/training/volley) to gather movie data.

- MovieContent -> Movie Object class, plus a bootlegged way to store data. 😅

### Remaining TODOs (Aug 20)

- [X] Load in movie posters
- [ ] Add pagination for results
- [X] Auto-refresh RecyclerView
- [X] Search history
- [ ] Interaction/animation/notif when no results are found
