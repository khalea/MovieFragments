package com.example.fragments;

import android.content.SearchRecentSuggestionsProvider;

// Reference -> https://developer.android.com/guide/topics/search/adding-recent-query-suggestions#TheBasics

public class SuggestionProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.example.fragments.SuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;
    public SuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
