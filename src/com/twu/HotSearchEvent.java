package com.twu;

public class HotSearchEvent {

    public String title;
    public Integer hotValue = 0;
    public Boolean isSuper = false;
    public Boolean isBought = false;
    public Integer boughtPosition = 0;
    public Integer lastBoughtValue = 0;
    public Integer currentPosition = 0;

    HotSearchEvent(String title) {
        this.title = title.toLowerCase();
    }

    @Override
    public String toString() {
        return title + " " + hotValue.toString();
    }

    public Integer getHotValue() {
        return hotValue;
    }
}
