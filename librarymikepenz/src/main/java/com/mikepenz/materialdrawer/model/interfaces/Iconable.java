package com.mikepenz.materialdrawer.model.interfaces;

import android.graphics.drawable.Drawable;

import com.mikepenz.iconics.typeface.IIcon;

/**
 * Created by mikepenz on 03.02.15.
 */
public interface Iconable<T> {
    T withIcon(Drawable icon);

    T withIcon(IIcon iicon);

    Drawable getIcon();

    IIcon getIIcon();

    void setIcon(Drawable icon);

    void setIIcon(IIcon iicon);
}
