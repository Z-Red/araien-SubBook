package com.example.araien_subbook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zachredfern on 2018-01-17.
 *
 * https://www.youtube.com/watch?v=a4o9zFfyIM4
 */

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.SubscriptionViewHolder> {

    private Context context;
    private List<Subscription> subList;

    // TODO: Description, investigate Context
    public SubscriptionAdapter(Context context, List<Subscription> subList) {
        this.context = context;
        this.subList = subList;
    }


    // TODO: Description, investigate LayoutInflater
    @Override
    public SubscriptionAdapter.SubscriptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(this.context);
        View view = inf.inflate(R.layout.sub_list_layout, parent, false);
        return new SubscriptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubscriptionAdapter.SubscriptionViewHolder holder, int position) {
        Subscription subscription = subList.get(position);
        holder.subName.setText(subscription.getName());
        holder.subDateValue.setText(subscription.getDate());
        //holder.subChargeValue.setText(String.format(subscription.getCharge()));
        holder.subCommentValue.setText(subscription.getComment());

    }

    // Returns size of the subscription list
    @Override
    public int getItemCount() {
        return subList.size();
    }


    // Holds the view information from a given Subscription
    class SubscriptionViewHolder extends RecyclerView.ViewHolder {

        TextView subName, subDateValue, subChargeValue, subCommentValue;

        // TODO: Description
        private SubscriptionViewHolder(View view) {
            super(view);
            subName = view.findViewById(R.id.subName);
            subDateValue = view.findViewById(R.id.subDateValue);
            subChargeValue = view.findViewById(R.id.subChargeValue);
            subCommentValue = view.findViewById(R.id.subCommentValue);
        }

    }
}
