package com.mikepenz.materialdrawer.model.interfaces;

/**
 * Created by mikepenz on 03.02.15.
 */
public interface Nameable<T> {
    T withName(String name);

    T withName(int nameRes);

    String getName();

    int getNameRes();

    void setName(String name);

    void setNameRes(int nameRes);
}
