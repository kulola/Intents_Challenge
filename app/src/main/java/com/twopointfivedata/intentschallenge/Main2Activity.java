package com.twopointfivedata.intentschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etNumber, etWebsite, etLocation;
    ImageView ivVerySatisfied, ivSatisfied, ivNeutral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etWebsite = findViewById(R.id.etWebsite);
        etLocation = findViewById(R.id.etLocation);
        ivVerySatisfied = findViewById(R.id.ivVerySatisfied);
        ivSatisfied = findViewById(R.id.ivSatisfied);
        ivNeutral = findViewById(R.id.ivNeutral);

        ivVerySatisfied.setOnClickListener(this);
        ivSatisfied.setOnClickListener(this);
        ivNeutral.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (etName.getText().toString().isEmpty() || etNumber.getText().toString().isEmpty() ||
                etWebsite.getText().toString().isEmpty() || etLocation.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please make sure all fields are filled", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent();
            intent.putExtra("name", etName.getText().toString().trim());
            intent.putExtra("number", etNumber.getText().toString().trim());
            intent.putExtra("website", etWebsite.getText().toString().trim());
            intent.putExtra("location", etLocation.getText().toString().trim());

            if (view.getId() ==R.id.ivVerySatisfied)
            {
                intent.putExtra("mood","very satisfied");
            }
            else if (view.getId() ==R.id.ivSatisfied)
            {
                intent.putExtra("mood","satisfied");
            }
            else
            {
                intent.putExtra("mood","neutral");
            }

            setResult(RESULT_OK, intent);
            Main2Activity.this.finish();
        }
    }
}
