package com.initezz.jathakakathapotha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.initezz.jathakakathapotha.adapter.StoriesAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StoriesAdapter storiesAdapter;
    private List<String> pdfNames;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        AssetManager assetManager = getAssets();

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pdfNames = getPDFFileNamesFromAssets(assetManager);

        storiesAdapter = new StoriesAdapter(this, pdfNames);
        recyclerView.setAdapter(storiesAdapter);

        findViewById(R.id.infoBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest =new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        AdView adView1 = findViewById(R.id.adView1);
        AdRequest adRequest1 =new AdRequest.Builder().build();
        adView1.loadAd(adRequest1);

    }

    private void filterList(String text) {
        List<String> filterList = new ArrayList<>();
        for (String pdfNames : pdfNames) {
            if (pdfNames.contains(text)) {
                filterList.add(pdfNames);
            }
        }

        if (filterList.isEmpty()) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            storiesAdapter.setFilteredList(this, filterList);
        }
    }

    private List<String> getPDFFileNamesFromAssets(AssetManager assetManager) {
        List<String> pdfFileNames = new ArrayList<>();
        try {
            // List all files in the assets folder
            String[] files = assetManager.list("");
            if (files != null) {
                // Iterate through the files and add PDF file names to the list
                for (String file : files) {
                    if (file.endsWith(".pdf")) {
                        String fileNameWithoutExtension = removeFileExtension(file);
                        pdfFileNames.add(fileNameWithoutExtension);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pdfFileNames;
    }

    private static String removeFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(0, lastDotIndex);
        }
        return fileName;
    }

}