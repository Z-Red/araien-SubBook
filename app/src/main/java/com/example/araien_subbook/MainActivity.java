package com.example.araien_subbook;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.wifi.aware.SubscribeConfig;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public SubscriptionAdapter subAdapter;
    public ArrayList<Subscription> subList;

    private Button btnAddSub, btnSaveSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Set up the subscription list
        subList = new ArrayList<>();
        //loadSubscriptions();
        // TODO: Load subscriptions if they exist


        // use the custom subscription adapter to enable the recycler
        // view to hold subscriptions displayed via card view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        subAdapter = new SubscriptionAdapter(this, subList);
        recyclerView.setAdapter(subAdapter);

        // Establish activity buttons and their respective on click functions
        detectAddSubButton();
        detectSaveSubButton();


        ////////////////////////////////////////////////////////////////////////////////////////////
        // https://www.youtube.com/watch?v=bhhs4bwYyhc
        // 2018-01-26
        subAdapter.setOnItemClickListener(new SubscriptionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openEditSubActivity(position);
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////

    }

    // Opens the activity to add a subscription to the subscription list
    public void openAddSubActivity() {
        Intent intent = new Intent(this, AddSubActivity.class);
        // no need to putExtra, we are not PROVIDING info to the activity, only taking it
        startActivityForResult(intent, 1 );
    }

    // Opens the activity to edit an existing subscription from the subscription list
    public void openEditSubActivity(int position) {
        Intent intent = new Intent(this, EditSubActivity.class);

        // Get the subscription that was clicked in the recycler view
        Subscription sub = subList.get(position);

        // pass the subscription to the edit subscription activity
        // along with the position it held in the subscription list
        intent.putExtra("sub", sub);
        intent.putExtra("position", position);
        startActivityForResult(intent, 2);
    }

    // Need to differentiate between returning activities
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        // If we had an add subscription activity called
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Subscription sub = (Subscription)data.getSerializableExtra("sub");
            subList.add(sub);
            subAdapter.notifyDataSetChanged();
        }

        // If we had an edit subscription activity called
        else if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            // get array position
            // replace subscription at that location with new version
            Subscription sub = (Subscription)data.getSerializableExtra("sub");
            int position = data.getIntExtra("position", 0);
            subList.set(position, sub);
            subAdapter.notifyDataSetChanged();

        }
    }

    // Uses GSON to save the object list
    private void saveSubscriptions() {
        // TODO: Save subscription list
    }

    // load subscriptions from a file if one exists
    private void loadSubscriptions() {
        // TODO: Open subscription list if a file exists to do so
    }

    // Detect the add subscription button and set its on click capability
    private void detectAddSubButton() {
        btnAddSub = findViewById(R.id.btnAddSub);
        btnAddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddSubActivity();
            }
        });
    }

    // Detect the save subscriptions button and set its on click capability
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
