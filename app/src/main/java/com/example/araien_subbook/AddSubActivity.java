package com.example.araien_subbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddSubActivity extends AppCompatActivity {

    private EditText name, date, charge, comment;
    private Button btnAddSubCancel, btnAddSubOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sub_layout);
        detectCancelButton();
        detectOKButton();


    }

    // Detect the add subscription cancel button and set its on click capability
    private void detectCancelButton() {
        btnAddSubCancel = findViewById(R.id.btnAddSubCancel);
        btnAddSubCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    // Detect the add subscription OK button and set its on click capability
    private void detectOKButton() {
        btnAddSubOK = findViewById(R.id.btnAddSubOK);
        btnAddSubOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = findViewById(R.id.etAddSubName);
                date = findViewById(R.id.etAddSubDate);
                charge = findViewById(R.id.etAddSubCharge);
                comment = findViewById(R.id.etAddSubComment);
                Subscription sub = new Subscription(date.getText().toString(),
                                                    name.getText().toString(),
                                                    charge.getText().toString(),
                                                    comment.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("sub", sub);
                setResult(Activity.RESULT_OK, intent);
                finish();


                // TODO: Ensure constraints are met
                // TODO: If constraints not met, send toast message notifying user
                // TODO: Instantiate new subscription instance with data activity
                // TODO: return this information back to the caller activity
            }
        });
    }
}
