package com.twopointfivedata.intentschallenge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnContact;
    ImageView ivCall, ivAssignment, ivLocation, ivMood;
    final int CREATE_CONTACT = 1;
    String name = "", number = "", website = "", location = "", mood = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_CONTACT)
        {
            if (resultCode == RESULT_OK)
            {
                ivMood.setVisibility(View.VISIBLE);
                ivCall.setVisibility(View.VISIBLE);
                ivLocation.setVisibility(View.VISIBLE);
                ivAssignment.setVisibility(View.VISIBLE);

                name = data.getStringExtra("name");
                number = data.getStringExtra("number");
                website = data.getStringExtra("website");
                location = data.getStringExtra("location");
                mood = data.getStringExtra("mood");

                if (mood.equals("very satisfied"))
                {
                    ivMood.setImageResource(R.drawable.very_satisfied);
                }
                else if (mood.equals("satisfied"))
                {
                    ivMood.setImageResource(R.drawable.satisfied);
                }
                else
                {
                    ivMood.setImageResource(R.drawable.neutral);
                }
            }
            else
            {
                Toast.makeText(this, "No data was passed!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnContact = findViewById(R.id.btnContact);
        ivMood = findViewById(R.id.ivMood);
        ivCall = findViewById(R.id.ivCall);
        ivAssignment = findViewById(R.id.ivAssignment);
        ivLocation = findViewById(R.id.ivLocation);

        ivMood.setVisibility(View.GONE);
        ivCall.setVisibility(View.GONE);
        ivLocation.setVisibility(View.GONE);
        ivAssignment.setVisibility(View.GONE);

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivityForResult(intent, CREATE_CONTACT);
            }
        });

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

        ivAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + website));
                startActivity(intent);
            }
        });

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + location));
                startActivity(intent);
            }
        });
    }
}
