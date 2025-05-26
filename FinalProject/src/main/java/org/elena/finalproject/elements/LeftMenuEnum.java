package org.elena.finalproject.elements;

public enum LeftMenuEnum {
    DASHBOARD("menu-dashboard"),
    POSTS("menu-posts"),
    MEDIA("menu-media"),
    PAGES("menu-pages"),
    COMMENTS("menu-comments"),
    APPEARANCE("menu-appearance"),
    PLUGINS("menu-plugins"),
    USERS("menu-users"),
    TOOLS("menu-tools"),
    SETTINGS("menu-settings"),
    PERFORMANCE("toplevel_page_w3tc_dashboard"),
    SMUSH("toplevel_page_smush");

    private String id;

    LeftMenuEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
