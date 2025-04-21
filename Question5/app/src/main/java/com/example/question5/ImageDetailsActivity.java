package com.example.question5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageDetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView detailsView;
    File imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        imageView = findViewById(R.id.imageView);
        detailsView = findViewById(R.id.textDetails);

        String path = getIntent().getStringExtra("imagePath");
        imageFile = new File(path);

        imageView.setImageURI(android.net.Uri.fromFile(imageFile));
        detailsView.setText(getImageDetails(imageFile));

        findViewById(R.id.btnDelete).setOnClickListener(v -> confirmDelete());
    }

    private String getImageDetails(File file) {
        String name = file.getName();
        long size = file.length() / 1024;
        long modified = file.lastModified();
        String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(modified));
        return "Name: " + name + "\nSize: " + size + " KB\nPath: " + file.getAbsolutePath() + "\nDate: " + date;
    }

    private void confirmDelete() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Image")
                .setMessage("Are you sure?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (imageFile.delete()) {
                            Toast.makeText(ImageDetailsActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ImageDetailsActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
