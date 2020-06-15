package com.simplemoneycounter.entity;

import java.math.BigDecimal;

public class User {

    public String id, email, password, currency;

    public BigDecimal amount;

    public boolean isNotificationsDone;

    public User() {
    }

    public User(String id, String email, String password, boolean isNotificationsDone) {

        this.id = id;
        this.email = email;
        this.password = password;
        this.isNotificationsDone = isNotificationsDone;
    }
}
