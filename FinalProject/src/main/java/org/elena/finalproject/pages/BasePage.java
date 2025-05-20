package org.elena.finalproject.pages;

import org.elena.finalproject.elements.Header;
import org.elena.finalproject.elements.LeftMenu;

public abstract class BasePage {

    private final LeftMenu leftMenu;
    private final Header header;

    public BasePage() {
        leftMenu = new LeftMenu();
        header = new Header();
    }

    public LeftMenu getLeftMenu() {
        return leftMenu;
    }

    public Header getHeader() {
        return header;
    }

    public abstract boolean isPageOpened();
}
