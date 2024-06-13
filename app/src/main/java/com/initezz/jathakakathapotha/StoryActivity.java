package com.initezz.jathakakathapotha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class StoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String name = getIntent().getStringExtra("name");

        TextView story_title = findViewById(R.id.story_title);
        story_title.setText(name);

        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset(name+".pdf").load();

    }
}