package org.elena.finalproject.users;

public enum UserEnum {
    ADMINISTRATOR("edadmin"),
    EDITOR("ededitor"),
    AUTHOR("edauthor"),
    CONTRIBUTOR("edcontributor"),
    SUBSCRIBER("edsubscriber");

    private String username;

    UserEnum(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
