/*
 *    EditSubActivity.java
 *
 *    Copyright 2018 Araien Redfern
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.example.araien_subbook;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.aware.SubscribeConfig;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// TODO: Clean up layout

/**
 *
 */
public class EditSubActivity extends AppCompatActivity {

    private Button btnEditSubCancel;
    private Button btnEditSubDelete;
    private Button btnEditSubOK;
    private EditText comment;
    private EditText charge;
    private EditText name;
    private EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_sub_layout);

        /* Establish this activity's edit text fields. */
        comment = findViewById(R.id.etEditSubComment);
        charge = findViewById(R.id.etEditSubCharge);
        name = findViewById(R.id.etEditSubName);
        date = findViewById(R.id.etEditSubDate);

        /* Get the subscription instance that was passed to this edit activity. */
        Subscription sub = (Subscription)getIntent().getSerializableExtra("sub");
        comment.setText(sub.getComment());
        charge.setText(sub.getCharge());
        name.setText(sub.getName());
        date.setText(sub.getDate());

        detectCancelButton();
        detectDeleteButton();
        detectOKButton();
    }

    /* Detect the edit subscription Cancel button and set its on click capability. */
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

    /* Detect the edit subscription OK button and set its on click capability. */
    private void detectOKButton() {
        btnEditSubOK = findViewById(R.id.btnEditSubOK);
        btnEditSubOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = findViewById(R.id.etEditSubName);
                date = findViewById(R.id.etEditSubDate);
                charge = findViewById(R.id.etEditSubCharge);
                comment = findViewById(R.id.etEditSubComment);


                // TODO: Ensure constraints are met
                // TODO: If constraints not met, send toast message notifying user


                Subscription sub = new Subscription(date.getText().toString(),
                                                    name.getText().toString(),
                                                    charge.getText().toString(),
                                                    comment.getText().toString());

                // TODO: Resolve potential default integer issue with getIntExtra
                int position = getIntent().getIntExtra("position", 0);

                /* Establish the return intent object. */
                Intent intent = new Intent();
                intent.putExtra("sub", sub);
                intent.putExtra("position", position);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    /* Detect the edit subscription Delete button and set its on click capability. */
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
