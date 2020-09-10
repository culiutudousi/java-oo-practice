package com.twu;

public class HotSearchEvent {

    String title;
    String description;
    Integer hotValue = 0;
    Boolean isSuper = false;

    HotSearchEvent(String title, String description) {
        this.title = title.toLowerCase();
        this.description = description;
    }

    @Override
    public String toString() {
        return title + ", " + description + ", " + hotValue.toString() + ", " + isSuper.toString();
    }
}
