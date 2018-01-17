package com.example.araien_subbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public SubscriptionAdapter subAdapter;
    public List<Subscription> subList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Subscription s1 = new Subscription("2018-01-17", "Netflix", 0.1, "Netflix");
        Subscription s2 = new Subscription("2018-01-18", "Gym", 100, "Gym membership.");
        Subscription s3 = new Subscription("2018-02-12", "Flixify", 99.99999, "Flix");

        subList.add(s1);
        subList.add(s2);
        subList.add(s3);

        subAdapter = new SubscriptionAdapter(this, subList);
        recyclerView.setAdapter(subAdapter);

    }


    // Interface with Subscription class

//
//    public void addSubscription() {
//
//        String name;
//        double charge;
//        String comment;
//
//        // Open add activity
//
//        // if button = cancel, return
//
//    }
//
//    public void editSubscription() {
//
//        // Open edit activity
//
//        // if button = cancel, return
//
//    }
//
//    public void deleteSubscription() {
//
//        // Open delete activity
//
//    }
}
