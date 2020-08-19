package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static int currentPosn;
    public static MovieContent.Movie currentBusiness;

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
}