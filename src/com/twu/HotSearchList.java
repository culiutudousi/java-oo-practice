package com.twu;

import java.util.*;
import java.util.stream.Collectors;

public class


HotSearchList {

    Map<String, HotSearchEvent> list = new HashMap<String, HotSearchEvent>();

    HotSearchList() {}

    public Boolean add(HotSearchEvent hotSearchEvent) {
        if (list.containsKey(hotSearchEvent.title)) {
            return false;
        }
        list.put(hotSearchEvent.title, hotSearchEvent);
        return true;
    }

    public void showAll() {
        list.values().stream()
                .sorted(Comparator.comparing(hotSearchEvent -> hotSearchEvent.hotValue))
                .forEach(hotSearchEvent -> System.out.println(hotSearchEvent.toString()));
    }

    public HotSearchEvent getEventByTitle(String title) {
        return list.getOrDefault(title, null);
    }

    public Boolean setEventAsSuper(String title) {
        HotSearchEvent event = getEventByTitle(title);
        if (event == null) {
            return false;
        }
        event.isSuper = true;
        return true;
    }

    public Boolean addHotValue(String title, Integer value) {
        HotSearchEvent event = getEventByTitle(title);
        if (event == null) {
            return false;
        }
        if (event.isSuper) {
            event.hotValue += value * 2;
        } else {
            event.hotValue += value;
        }
        System.out.println(event.toString());
        return true;
    }
}
