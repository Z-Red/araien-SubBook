package com.example.araien_subbook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zachredfern on 2018-01-17.
 *
 * Much of this code was adapted from the tutorial at https://www.youtube.com/watch?v=a4o9zFfyIM4.
 * In particular, the set up of the RecyclerView Adapter was abstracted from that tutorial. As well,
 * there exists some code that was modified from https://www.youtube.com/watch?v=bhhs4bwYyhc to
 * enable the on click functionality of recycler view rows. (Recycler view does not have on click
 * functionality by default.)
 *
 */

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.SubscriptionViewHolder> {

    private Context context;
    private List<Subscription> subList;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // https://www.youtube.com/watch?v=bhhs4bwYyhc
    // 2018-01-26
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // https://www.youtube.com/watch?v=a4o9zFfyIM4
    // 2018-01-24

    // Holds the view information from a given Subscription
    public class SubscriptionViewHolder extends RecyclerView.ViewHolder {

        TextView subName, subDate, subCharge, subComment;

        // Constructor, instantiates the subscription view holder
        public SubscriptionViewHolder(View view) {
            super(view);
            subName = view.findViewById(R.id.subName);
            subDate = view.findViewById(R.id.subDate);
            subCharge = view.findViewById(R.id.subCharge);
            subComment = view.findViewById(R.id.subComment);
    ////////////////////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////////////
            // https://www.youtube.com/watch?v=bhhs4bwYyhc
            // 2018-01-26

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //
                    int position = getAdapterPosition();
                    listener.onItemClick(position);
                }
            });
            ////////////////////////////////////////////////////////////////////////////////////////

        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // https://www.youtube.com/watch?v=a4o9zFfyIM4
    // 2018-01-24

    // Subscription adapter constructor, provided the main activity (context) and the sub list
    public SubscriptionAdapter(Context context, List<Subscription> subList) {
        this.context = context;
        this.subList = subList;
    }


    // Inflates a recycler view row with the custom card view layout for a subscription
    @Override
    public SubscriptionAdapter.SubscriptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.sub_list_layout, parent, false);
        return new SubscriptionViewHolder(view);
    }

    // Populates the data of a card in a given recycler view row
    @Override
    public void onBindViewHolder(SubscriptionAdapter.SubscriptionViewHolder holder, int position) {
        Subscription subscription = subList.get(position);
        holder.subName.setText(subscription.getName());
        holder.subDate.setText(subscription.getDate());
        holder.subCharge.setText(String.format(subscription.getCharge()));
        holder.subComment.setText(subscription.getComment());

    }

    // Returns size of the subscription list
    @Override
    public int getItemCount() {
        return subList.size();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////

}
