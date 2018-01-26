package com.example.araien_subbook;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by zachredfern on 2018-01-25.
 */

// TODO: components are cleared when clicked
// TODO: If comment is unchanged, no comment is entered.

public class AddSubDialog extends DialogFragment {

    private EditText name, date, charge, comment;
    private AddSubDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Build the dialog box using a custom layout
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.add_sub_dialog, null);
        builder.setView(view);

        // Set the title of the dialog box
        builder.setMessage("Add Subscription");

        // Set the cancel button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do nothing
            }
        });

        // Set the OK button, where a subscription that adheres to given constraints
        // is passed back to the main activity to be added to a subscription list.
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // TODO: Make sure strings adhere to assignment constraints

               // TODO: Use toast to notify user of erroneous inputs

               Subscription sub = new Subscription(date.getText().toString(),
                                                    name.getText().toString(),
                                                    charge.getText().toString(),
                                                    comment.getText().toString());

               // Only call this when all constraints are OK
               listener.addSubscription(sub);

            }
        });

        // Establish dialog box EditText components
        name = view.findViewById(R.id.txtAddSubDialogName);
        date = view.findViewById(R.id.txtAddSubDialogDate);
        charge = view.findViewById(R.id.txtAddSubDialogCharge);
        comment = view.findViewById(R.id.txtAddSubDialogComment);
        return builder.create();
    }

    // Implemented by MainActivity so that a subscription can be retrieved from the dialog box.
    // In other words, allows a subscription to be retrieved from this dialog box.
    public interface AddSubDialogListener {
        void addSubscription(Subscription sub);
    }


    // TODO: description, investigate purpose of onAttach
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (AddSubDialogListener) context;
        }
        catch (ClassCastException ex) {
            throw new ClassCastException(context.toString()
                        + " must implement AddSubDialogListener");
        }
    }


}


































