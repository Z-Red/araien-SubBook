package com.example.araien_subbook;

import java.util.Date;

/**
 * Created by zachredfern on 2018-01-16.
 *
 *
 */

public class Subscription {

    private Date date;          // Date the subscription started
    private String name;        // Name of the subscription
    private String comment;     // An (optional) comment about the subscription
    private double charge;      // The monthly charge incurred by the subscription



    // Constructor in case of no comment
    public Subscription(Date date, String name, double charge) {
        this(date, name, charge, "");
    }

    // Constructor in case a comment is provided
    public Subscription(Date date, String name, double charge, String comment) {
        this.date = date;
        this.name = name;
        this.charge = charge;
        this.comment = comment;
    }

    // Get subscription sate
    public Date getDate()  { return this.date; }

    // Set subscription date
    public void setDate(Date date) { this.date = date; }

    // Get subscription name
    public String getName() { return this.name; }

    // Set subscription name
    public void setName(String name) { this.name = name; }

    // Get charge
    public double getCharge() { return this.charge; }

    // Set charge
    public void setCharge(Double charge) { this.charge = charge; }

    // Get comment
    public String getComment() { return this.comment; }

    // Set comment
    public void setComment(String comment) { this.comment = comment; }
}
