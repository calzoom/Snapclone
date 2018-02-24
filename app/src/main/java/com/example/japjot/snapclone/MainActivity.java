// main activity
package com.example.japjot.snapclone;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    Navigates to a list screen
//    Button to pick an image from the camera (your choice)
//    Picture is received, an edittext (to add a caption) and a submit button should appear. This should not be a new activity.
//    On submission, the new snap should be pushed to Firebase

    Button listView, photoButton, submitPhoto;
    EditText caption;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (Button)findViewById(R.id.button1);
        listView.setOnClickListener(this);

        photoButton = (Button) this.findViewById(R.id.button2);
        photoButton.setOnClickListener(this);

        submitPhoto = (Button) this.findViewById(R.id.button3);
        submitPhoto.setOnClickListener(this);
        submitPhoto.setVisibility(View.INVISIBLE);

        caption = (EditText) findViewById(R.id.editText);
        caption.setVisibility(View.INVISIBLE);

        imageView = (ImageView)findViewById(R.id.imageView);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);

            caption.setVisibility(View.VISIBLE);
            submitPhoto.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.button1)){
            Intent i = new Intent(MainActivity.this, ListActivity.class);
            startActivity(i);
        }
        else if(v == findViewById(R.id.button2)){
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
        else if(v == findViewById(R.id.button3)){
            //send the picture with caption to Firebase
            String cap = caption.getText().toString();
        }
    }
}
