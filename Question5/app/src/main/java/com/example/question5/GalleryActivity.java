package com.example.question5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    private GridView gridView;
    private ImageAdapter adapter;
    private ArrayList<File> imageFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        gridView = findViewById(R.id.gridView);
        imageFiles = fetchImages();
        adapter = new ImageAdapter(this, imageFiles);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File imageFile = imageFiles.get(position);
                Intent intent = new Intent(GalleryActivity.this, ImageDetailsActivity.class);
                intent.putExtra("imagePath", imageFile.getAbsolutePath());
                startActivity(intent);
            }
        });
    }

    private ArrayList<File> fetchImages() {
        ArrayList<File> images = new ArrayList<>();
        File picturesDir = getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES);
        if (picturesDir != null && picturesDir.exists()) {
            File[] files = picturesDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg") || file.getName().endsWith(".png")) {
                        images.add(file);
                    }
                }
            }
        } else {
            Toast.makeText(this, "No images found.", Toast.LENGTH_SHORT).show();
        }
        return images;
    }
}
