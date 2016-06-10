package com.mikepenz.materialdrawer.model.interfaces;

/**
 * Created by mikepenz on 03.02.15.
 */
public interface Badgeable<T> {
    T withBadge(String badge);

    String getBadge();

    void setBadge(String badge);
}
