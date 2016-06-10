package com.mikepenz.materialdrawer.model.interfaces;

import android.graphics.drawable.Drawable;

/**
 * Created by mikepenz on 03.02.15.
 */
public interface IProfile<T> {
    T withName(String name);

    String getName();

    void setName(String name);

    T withEmail(String email);

    String getEmail();

    void setEmail(String email);

    T withIcon(Drawable icon);

    Drawable getIcon();

    void setIcon(Drawable icon);

    T withSelectable(boolean selectable);

    boolean isSelectable();

    T setSelectable(boolean selectable);
}
