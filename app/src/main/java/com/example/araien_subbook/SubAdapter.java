/*
 *    SubscriptionAdapter.java
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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.view.View;

/**
 * Adapted from the following:
 * Simplified Coding, Published on Oct 18, 2017
 * https://www.youtube.com/watch?v=a4o9zFfyIM4
 *
 * Allows the integration between a custom defined card view layout and a
 * recycler view layout, using an instance of my subscription class to
 * populate the data in the card view and subsequently the recycler view.
 */
public class SubAdapter extends RecyclerView.Adapter<SubViewHolder> {

    private ArrayList<Subscription> subList;
    private Context context;

    /* Subscription adapter constructor, provided the main activity (context) and the sub list. */
    public SubAdapter(final Context context, ArrayList<Subscription> subList) {
        this.context = context;
        this.subList = subList;
    }

    /* Inflates a recycler view row with the custom card view layout for a subscription. */
    @Override
    public SubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.sub_list_layout, parent, false);
        return new SubViewHolder(view, this.context);
    }

    /* Populates the data of a card in a given recycler view row. */
    @Override
    public void onBindViewHolder(SubViewHolder holder, int position) {
        Subscription subscription = subList.get(position);
        holder.subCharge.setText(String.format(subscription.getCharge()));
        holder.subComment.setText(subscription.getComment());
        holder.subName.setText(subscription.getName());
        holder.subDate.setText(subscription.getDate());
    }

    /* Returns size of the subscription list. */
    @Override
    public int getItemCount() {
        return subList.size();
    }

}
