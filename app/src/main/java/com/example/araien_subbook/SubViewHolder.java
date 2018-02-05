/*
 *    SubViewHolder.java
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
import android.content.Context;
import android.widget.TextView;
import android.view.View;

/**
 * Adapted from the following:
 * Simplified Coding, Published on Oct 18, 2017
 * https://www.youtube.com/watch?v=a4o9zFfyIM4
 *
 * Holds the view information from a given Subscription.
 */
public class SubViewHolder extends RecyclerView.ViewHolder {

    public TextView subComment;
    public TextView subCharge;
    public TextView subName;
    public TextView subDate;

    /* Constructor, instantiates the subscription view holder. */
    public SubViewHolder(View view, final Context context) {
        super(view);
        subComment = view.findViewById(R.id.subComment);
        subCharge = view.findViewById(R.id.subCharge);
        subName = view.findViewById(R.id.subName);
        subDate = view.findViewById(R.id.subDate);

        /* Opens an edit subscription activity for a given subscription. */
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                int position = getAdapterPosition();
                ((MainActivity) context).openEditSubActivity(position);
            }
        });


    }

}
