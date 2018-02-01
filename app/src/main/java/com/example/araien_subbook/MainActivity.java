/*
 *    MainActivity.java
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

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.widget.Button;
import android.app.Activity;
import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    public ArrayList<Subscription> subList;
    public RecyclerView recyclerView;
    public SubAdapter subAdapter;

    private Button btnSaveSub;
    private Button btnAddSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Set up Subscription list and load any existing subscription. */
        subList = new ArrayList<>();
        loadSubscriptions();

        /* Put Subscriptions into the recycler view. */
        subAdapter = new SubAdapter(this, subList);
        recyclerView = findViewById(R.id.recyclerView);

        /* Set up recycler view using custom Subscription adapter and card view */
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(subAdapter);
        recyclerView.setHasFixedSize(true);

        // Detect on click capabilities of this view
        detectSaveSubButton();
        detectAddSubButton();

    }

    /* Opens the activity to add a subscription to the subscription list. */
    public void openAddSubActivity() {
        Intent intent = new Intent(this, AddSubActivity.class);
        startActivityForResult(intent, 1 );
    }

    /* Opens the activity to edit an existing subscription from the subscription list. */
    public void openEditSubActivity(int position) {
        Intent intent = new Intent(this, EditSubActivity.class);

        /* Get the subscription that was clicked in the recycler view. */
        Subscription sub = subList.get(position);
        intent.putExtra("sub", sub);
        intent.putExtra("position", position);
        startActivityForResult(intent, 2);
    }

    /* Called when returning from an add or edit subscription activity. */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        /* If add subscription activity called, add new subscription to the sub list. */
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Subscription sub = (Subscription)data.getSerializableExtra("sub");
            subList.add(sub);
            subAdapter.notifyDataSetChanged();
        }

        /* If edit subscription activity called, replace subscription with revision. */
        else if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            Subscription sub = (Subscription)data.getSerializableExtra("sub");
            int position = data.getIntExtra("position", 0);
            subList.set(position, sub);
            subAdapter.notifyDataSetChanged();

        }
    }

    /* Uses GSON to save the object list. */
    private void saveSubscriptions() {
        // TODO: Save subscription list
        return;
    }

    /* Load subscriptions from a file if one exists. */
    private void loadSubscriptions() {
        // TODO: Open subscription list if a file exists to do so
        return;
    }

    /* Detect the add subscription button and set its on click capability. */
    private void detectAddSubButton() {
        btnAddSub = findViewById(R.id.btnAddSub);
        btnAddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddSubActivity();
            }
        });
    }

    /* Detect the save subscriptions button and set its on click capability. */
    public void detectSaveSubButton() {
        btnSaveSub = findViewById(R.id.btnSaveSub);
        btnSaveSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSubscriptions();
            }
        });
    }

}
