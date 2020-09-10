package com.twu;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    private void updatePosition() {
        List<Integer> usedPosition = new ArrayList<>();
        list.values().stream()
                .filter(e -> e.isBought)
                .forEach(e -> {
                    usedPosition.add(e.boughtPosition);
                    e.currentPosition = e.boughtPosition;
                });
        list.values().stream()
                .filter(e -> !e.isBought)
                .sorted(Comparator.comparing(HotSearchEvent::getHotValue).reversed())
                .forEachOrdered(e -> {
                    List<Integer> unusedPosition = IntStream.range(1, list.size() + 1)
                            .filter(i -> !usedPosition.contains(i))
                            .boxed()
                            .sorted()
                            .collect(Collectors.toList());
                    Integer index = unusedPosition.get(0);
                    e.currentPosition = index;
                    usedPosition.add(index);
                });
    }

    public void showAll() {
        updatePosition();
        list.values().stream()
                .sorted(Comparator.comparing(e -> e.currentPosition))
                .forEach(e -> System.out.println(String.format("%d %s", e.currentPosition, e.toString())));
    }

    public Boolean addSuper(HotSearchEvent hotSearchEvent) {
        if (list.containsKey(hotSearchEvent.title)) {
            return false;
        }
        hotSearchEvent.isSuper = true;
        list.put(hotSearchEvent.title, hotSearchEvent);
        return true;
    }

    public Boolean addHotValue(String title, Integer value) {
        HotSearchEvent event = list.getOrDefault(title, null);
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

    public Boolean buyPositionForHotEvent(String title, Integer position, Integer price) {
        HotSearchEvent event = list.getOrDefault(title, null);
        if (event == null) {
            return false;
        }
        if (position <= 0 || price <= 0) {
            return false;
        }
        updatePosition();
        List<HotSearchEvent> eventsAtPosition = list.values().stream()
                .filter(e -> e.currentPosition == position)
                .collect(Collectors.toList());
        if (eventsAtPosition.size() == 0) {
            event.isBought = true;
            event.boughtPosition = position;
            event.lastBoughtValue = price;
            return true;
        } else {
            HotSearchEvent eventAtPosition = eventsAtPosition.get(0);
            if (!eventAtPosition.isBought || price > eventAtPosition.lastBoughtValue) {
                list.remove(eventAtPosition.title);
                event.isBought = true;
                event.boughtPosition = position;
                event.lastBoughtValue = price;
                return true;
            }
        }
        return false;
    }
}
