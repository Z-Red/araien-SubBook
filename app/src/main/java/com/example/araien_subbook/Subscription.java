package com.example.araien_subbook;

import java.util.Date;

/**
 * Created by zachredfern on 2018-01-16.
 *
 *
 */

public class Subscription {

    private String name;        // Name of the subscription
    private String date;        // Date the subscription started
    private String comment;     // An (optional) comment about the subscription
    private String charge;      // The monthly charge incurred by the subscription

    // Constructor in case of no comment
    public Subscription(String date, String name, String charge) {
        this(date, name, charge, "");
    }

    // Constructor in case a comment is provided
    public Subscription(String date, String name, String charge, String comment) {
        this.name = name;
        this.date = date;
        this.charge = charge;
        this.comment = comment;
    }

    // Get subscription sate
    public String getDate()  { return this.date; }

    // Set subscription date
    public void setDate(String date) { this.date = date; }

    // Get subscription name
    public String getName() { return this.name; }

    // Set subscription name
    public void setName(String name) { this.name = name; }

    // Get charge
    public String getCharge() { return this.charge; }

    // Set charge
    public void setCharge(String charge) { this.charge = charge; }

    // Get comment
    public String getComment() { return this.comment; }

    // Set comment
    public void setComment(String comment) { this.comment = comment; }
}
