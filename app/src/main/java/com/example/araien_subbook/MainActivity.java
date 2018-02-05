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

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.widget.Button;
import android.app.Activity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    public static final int RESULT_DELETE = 2;
    public ArrayList<Subscription> subList;
    public RecyclerView recyclerView;
    public SubAdapter subAdapter;

    private static final String FILENAME = "sub_list.sav";
    private Button btnSaveSub;
    private Button btnAddSub;
    private TextView tvTotal;

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

    // Called when activity is first created on app startup
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Set up Subscription list and load any existing subscription. */
        subList = new ArrayList<>();
        tvTotal = findViewById(R.id.tvTotal);
        loadSubscriptions();
        updateTotal();

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

    /* Called when returning from an add or edit subscription activity. */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        /* If add subscription activity called, add new subscription to the sub list. */
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Subscription sub = (Subscription)data.getSerializableExtra("sub");
            subList.add(sub);
            subAdapter.notifyDataSetChanged();
            updateTotal();
        }

        /* If edit subscription activity finished with OK code. */
        else if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            Subscription sub = (Subscription)data.getSerializableExtra("sub");
            int position = data.getIntExtra("position", 0);
            subList.set(position, sub);
            subAdapter.notifyDataSetChanged();
            updateTotal();
        }
        /* If edit subscription activity finished with a delete code. */
        else if (requestCode == 2 && resultCode == RESULT_DELETE) {
            int position = data.getIntExtra("position", 0);
            subList.remove(position);
            subAdapter.notifyDataSetChanged();
            updateTotal();
        }
    }

    /* Uses GSON to save the object list. */
    private void saveSubscriptions() {
        try {
            // Converts the subscription list into a GSON object
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(subList, out);
            out.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /* Load subscriptions from a file if one exists. */
    private void loadSubscriptions() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            subList = gson.fromJson(in, new TypeToken<ArrayList<Subscription>>(){}.getType());
        } catch (FileNotFoundException e) {
            subList = new ArrayList<>();
        }
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
    private void detectSaveSubButton() {
        btnSaveSub = findViewById(R.id.btnSaveSub);
        btnSaveSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSubscriptions();
            }
        });
    }

    // Update the total charge
    private void updateTotal() {
        double total = 0.0;
        for (int i = 0; i < subList.size(); ++i) {
            total += Double.parseDouble(subList.get(i).getCharge());
        }
        tvTotal.setText(String.format("%.2f", total));
    }

}
