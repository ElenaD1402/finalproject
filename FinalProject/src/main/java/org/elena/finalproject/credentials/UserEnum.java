package org.elena.finalproject.credentials;

public enum UserEnum {
    ADMINISTRATOR("edadmin", "!fT(oIrg8Kh)2eo&zzLSwMRE"),
    EDITOR("ededitor", "gw2aHx55x(R7^lwz)$$ejhgN"),
    AUTHOR("edauthor", "TOVB&u4Ty*tby0@E6DK3ZT1A"),
    CONTRIBUTOR("edcontributor", "gt$qxL3v^hsfsJw4#1hte(!d"),
    SUBSCRIBER("edsubscriber", "t8QAt7FXK5Y7!MTIQsWLWKAi");

    private String username;
    private String password;

    UserEnum(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
