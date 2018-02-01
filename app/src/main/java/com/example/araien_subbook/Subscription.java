/*
 *    Subscription.java
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

import java.io.Serializable;

/**
 *
 */
public class Subscription implements Serializable {

    private String comment;     /* An (optional) comment about the subscription. */
    private String charge;      /* The monthly charge incurred by the subscription. */
    private String date;        /* Date the subscription started. */
    private String name;        /* Name of the subscription. */


    /* Constructor in case of no comment. */
    public Subscription(String date, String name, String charge) {
        this(date, name, charge, "");
    }

    /* Constructor in case a comment is provided. */
    public Subscription(String date, String name, String charge, String comment) {
        this.comment = comment;
        this.charge = charge;
        this.name = name;
        this.date = date;
    }

    /* Get subscription date. */
    public String getDate()  { return this.date; }

    /* Set subscription date. */
    public void setDate(String date) { this.date = date; }

    /* Get subscription name. */
    public String getName() { return this.name; }

    /* Set subscription name. */
    public void setName(String name) { this.name = name; }

    /* Get charge. */
    public String getCharge() { return this.charge; }

    /* Set charge. */
    public void setCharge(String charge) { this.charge = charge; }

    /* Get comment. */
    public String getComment() { return this.comment; }

    /* Set comment. */
    public void setComment(String comment) { this.comment = comment; }
}
