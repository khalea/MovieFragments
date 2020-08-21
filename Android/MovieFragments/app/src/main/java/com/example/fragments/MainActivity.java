package com.example.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

// TODO Auto update RecyclerView after receiving results

public class MainActivity extends AppCompatActivity {

    // Reference https://developers.themoviedb.org/3/getting-started/search-and-query-for-details
    // Using Volley for requests etc https://developer.android.com/training/volley
    // Convert data to movie object & add to MovieContent.ITEMS

    public static int currentPosn;
    public static MovieContent.Movie currentMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference AndroidX FragmentTransaction, FragmentManager
        // FragmentManager helps add/remove/swap fragments from the Activity's ViewGroup
        // It is an interface for interacting with Fragments within an activity
        FragmentManager fragmentManager = getSupportFragmentManager(); // getSupport.. grabs FragMgr to help with interacting w frags
        fragmentManager
                .beginTransaction() // Starts edit operations on Fragments available to the FragMgr
                .add(R.id.mainActivityContainer, new MovieFragment(), MovieFragment.class.getSimpleName()) // Add Fragment to activity
                .commit(); // Schedules commit to main thread queue
    }

    /** Intent Functions/Handlers
     * REFERENCE https://developer.android.com/training/search/setup#create-sc
     * @param intent -> Search Query from Activity
     */

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            //use the query to search your data somehow
            Toast.makeText(getBaseContext(), "Query: " + query, Toast.LENGTH_SHORT).show();
            Log.d("Query", "Query: " + query);

            // TODO Perform GET request, Send Results to MovieContent.ITEMS & Update RV
            MovieContent.clearItems();
            MovieAPI movieAPI = new MovieAPI();
            movieAPI.find(query, this.getBaseContext(), 1);

            // Refresh the view
            getSupportFragmentManager().beginTransaction()
                    .detach(getSupportFragmentManager().findFragmentByTag(MovieFragment.class.getSimpleName()))
                    .attach(getSupportFragmentManager().findFragmentByTag(MovieFragment.class.getSimpleName()))
                    .commit();
        }
    }

    // Options Menu & Item Handlers

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable config with SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Log.d("Query", "Query/Search item selected");
        }
        return super.onOptionsItemSelected(item);
    }


}