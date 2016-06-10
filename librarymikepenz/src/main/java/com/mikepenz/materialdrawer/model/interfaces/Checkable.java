package com.mikepenz.materialdrawer.model.interfaces;

/**
 * Created by mikepenz on 03.02.15.
 */
public interface Checkable<T> {
    T withCheckable(boolean checkable);

    boolean isCheckable();

    void setCheckable(boolean checkable);
}
