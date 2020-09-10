package com.twu;

public class HotSearchSlot {

    public HotSearchEvent hotSearchEvent;
    public Boolean isSuper = false;
    public Boolean isBought = false;
    public Integer boughtPosition = 0;
    public Integer lastBoughtValue = 0;

    HotSearchSlot(HotSearchEvent hotSearchEvent) {
        this.hotSearchEvent = hotSearchEvent;
    }
}
