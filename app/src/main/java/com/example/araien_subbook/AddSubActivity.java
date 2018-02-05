/*
 *    AddSubActivity.java
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

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.content.Intent;
import android.app.Activity;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class AddSubActivity extends AppCompatActivity {

    private Button btnAddSubCancel;
    private Button btnAddSubOK;
    private EditText comment;
    private EditText charge;
    private EditText name;
    private EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sub_layout);
        detectCancelButton();
        detectOKButton();
    }

    /* Detect the add subscription cancel button and set its on click capability. */
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

    /* Detect the add subscription OK button and set its on click capability. */
    private void detectOKButton() {
        btnAddSubOK = findViewById(R.id.btnAddSubOK);
        btnAddSubOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = findViewById(R.id.etAddSubName);
                date = findViewById(R.id.etAddSubDate);
                charge = findViewById(R.id.etAddSubCharge);
                comment = findViewById(R.id.etAddSubComment);

                // If constraint check failed, abort
                if (!validConstraints()) {
                    return;
                }

                Subscription sub = new Subscription(date.getText().toString(),
                                                    name.getText().toString(),
                                                    charge.getText().toString(),
                                                    comment.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("sub", sub);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    // Check data value constraints
    private boolean validConstraints() {

        // Check subscription name constraints
        if (name.getText().toString().length() == 0) {
            Toast.makeText(AddSubActivity.this, "Must supply a subscription name", Toast.LENGTH_LONG).show();
            return false;
        }
        if (name.getText().toString().length() > 20) {
            Toast.makeText(AddSubActivity.this, "Subscription Name must be 20 characters or less", Toast.LENGTH_LONG).show();
            return false;
        }

        // Check charge value constraints
        if (charge.getText().toString().length() == 0) {
            Toast.makeText(AddSubActivity.this, "Must supply a charge", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            try {
                Double chrg = Double.parseDouble(charge.getText().toString());
                if (chrg < 0.0) {
                    Toast.makeText(AddSubActivity.this, "Charge must be non-negative", Toast.LENGTH_LONG).show();
                    return false;
                }
            }
            catch (Exception e) {
                Toast.makeText(AddSubActivity.this, "Must supply a valid charge", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        // Check date constraints
        if (date.getText().toString().length() == 0) {
            Toast.makeText(AddSubActivity.this, "Must supply a date", Toast.LENGTH_LONG).show();
            return false;
        }
        // http://www.mkyong.com/java/how-to-check-if-date-is-valid-in-java/
        // February 4, 2018
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            try {
                Date d = sdf.parse(date.getText().toString());
            }
            catch (ParseException e) {
                Toast.makeText(AddSubActivity.this, "Must supply a valid date", Toast.LENGTH_LONG).show();
                return false;
            }
        }

        // Check optional comment constraints
        if (comment.getText().toString().length() > 30) {
            Toast.makeText(AddSubActivity.this, "Comment must be 30 characters or less", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}


