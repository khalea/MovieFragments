package com.example.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

        Toast.makeText(getBaseContext(), "Main Activity Started", Toast.LENGTH_LONG).show();

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            performSearch(query);
            Log.d("Query", "Query working (sorta)");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable config with SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        /*
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchBar).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Log.d("Query", "Query/Search item selected");
        }
        return super.onOptionsItemSelected(item);
    }

    private void performSearch(String query) {
        Toast.makeText(getBaseContext(), "Query: " + query, Toast.LENGTH_LONG).show();
    }


}