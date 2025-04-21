package com.example.question5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageDetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView nameText, pathText, sizeText, dateText;
    private Button deleteBtn;
    private File imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        imageView = findViewById(R.id.imageView);
        nameText = findViewById(R.id.nameText);
        pathText = findViewById(R.id.pathText);
        sizeText = findViewById(R.id.sizeText);
        dateText = findViewById(R.id.dateText);
        deleteBtn = findViewById(R.id.deleteBtn);

        String imagePath = getIntent().getStringExtra("imagePath");
        imageFile = new File(imagePath);

        if (imageFile.exists()) {
            imageView.setImageURI(FileProvider.getUriForFile(this, "com.example.question5.fileprovider", imageFile));
            nameText.setText("Name: " + imageFile.getName());
            pathText.setText("Path: " + imageFile.getAbsolutePath());
            sizeText.setText("Size: " + imageFile.length() / 1024 + " KB");
            dateText.setText("Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(imageFile.lastModified())));
        }

        deleteBtn.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Delete Image")
                    .setMessage("Are you sure you want to delete this image?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        if (imageFile.delete()) {
                            Toast.makeText(this, "Image deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(this, "Failed to delete image", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }
}