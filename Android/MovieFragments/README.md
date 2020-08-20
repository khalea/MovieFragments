# MovieFragments 🎬

### *A simple movie search app — and my first real Android project, yay!* 

### Intro & Components

- RecyclerView + MovieRVAdapter -> The list & respective adapter that handles binding & displaying data in their respective rows.

- DetailFragment -> A detail view that expands info about a movie selected from the RecyclerView. 

- MovieAPI -> Class that aids in interacting with TMDB. Uses the [Volley Library](https://developer.android.com/training/volley) to gather movie data.

- MovieContent -> Movie Object class, plus a bootlegged way to store data. 😅

### Remaining TODOs (Aug 20)

[] Load in movie posters
[] Add pagination for results
[] Auto-refresh RecyclerView
[] Search autocomplete
[] Interaction/animation/notif when no results are found
