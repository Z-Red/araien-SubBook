package com.example.araien_subbook;

import android.app.Dialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
                            implements AddSubDialog.AddSubDialogListener {

    public RecyclerView recyclerView;
    public SubscriptionAdapter subAdapter;
    public List<Subscription> subList;

    private Button btnAddSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Detect the add subscription button
        btnAddSub = findViewById(R.id.btnAddSub);
        btnAddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddSubDialog();
            }
        });

        // TODO: Detect the save button

        // TODO: Open subscription list if a file exists to do so


        // Set up the subscription list, use the custom subscription adapter to
        // enable the recycler view to hold subscriptions displayed via card view
        subList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // NEEDED?
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // NEEDED?
        subAdapter = new SubscriptionAdapter(this, subList);
        recyclerView.setAdapter(subAdapter);

    }

    // Opens the Add Subscription Dialog
    public void openAddSubDialog() {
        AddSubDialog addSubDialog = new AddSubDialog();
        addSubDialog.show(getFragmentManager(), "addSubDialog");
    }

    // Retrieves a Subscription instance from the Add Subscription Dialog
    // and then adds it to the subscription list, and lastly updates the list.
    @Override
    public void addSubscription(Subscription sub) {
        subList.add(sub);
        subAdapter.notifyDataSetChanged();
    }

    // Uses GSON to save the object list
    public void saveSubscriptions() {

    }

    // load subscriptions from a file if one exists
    public void loadSubscriptions() {

    }
}
