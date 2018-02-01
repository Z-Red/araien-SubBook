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
import android.widget.EditText;
import android.content.Intent;
import android.app.Activity;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;

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



                // TODO: Ensure constraints are met
                // TODO: If constraints not met, send toast message notifying user


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
}
