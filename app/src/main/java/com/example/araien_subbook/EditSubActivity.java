package com.example.araien_subbook;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.aware.SubscribeConfig;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditSubActivity extends AppCompatActivity {

    private EditText name, date, charge, comment;
    private Button btnEditSubCancel, btnEditSubOK, btnEditSubDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_sub_layout);

        // Establish activity buttons and their respective on click functions
        detectCancelButton();
        detectDeleteButton();
        detectOKButton();

        // Establish this activity's edit text fields
        name = findViewById(R.id.etEditSubName);
        date = findViewById(R.id.etEditSubDate);
        charge = findViewById(R.id.etEditSubCharge);
        comment = findViewById(R.id.etEditSubComment);

        // Get the subscription instance that was passed to this edit activity
        // and then populate the fields with the corresponding data
        Subscription sub = (Subscription)getIntent().getSerializableExtra("sub");
        name.setText(sub.getName());
        date.setText(sub.getDate());
        charge.setText(sub.getCharge());
        comment.setText(sub.getComment());


    }

    // Detect the edit subscription Cancel button and set its on click capability
    private void detectCancelButton() {
        btnEditSubCancel = findViewById(R.id.btnEditSubCancel);
        btnEditSubCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    // Detect the edit subscription OK button and set its on click capability
    private void detectOKButton() {
        btnEditSubOK = findViewById(R.id.btnEditSubOK);
        btnEditSubOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = findViewById(R.id.etEditSubName);
                date = findViewById(R.id.etEditSubDate);
                charge = findViewById(R.id.etEditSubCharge);
                comment = findViewById(R.id.etEditSubComment);
                Subscription sub = new Subscription(date.getText().toString(),
                                                    name.getText().toString(),
                                                    charge.getText().toString(),
                                                    comment.getText().toString());

                // TODO: Resolve potential default integer issue with getIntExtra
                int position = getIntent().getIntExtra("position", 0);

                // Establish the return intent object (data returned to main activity)
                Intent intent = new Intent();
                intent.putExtra("sub", sub);
                intent.putExtra("position", position);
                setResult(Activity.RESULT_OK, intent);
                finish();

                // TODO: Ensure constraints are met
                // TODO: If constraints not met, send toast message notifying user
                // TODO: Instantiate new subscription instance with data activity
                // TODO: return this information back to the caller activity
            }
        });
    }

    // Detect the edit subscription Delete button and set its on click capability
    private void detectDeleteButton() {
        btnEditSubDelete = findViewById(R.id.btnEditSubDelete);
        btnEditSubDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Delete the given subscription entry from the subscription list
            }
        });
    }
}
