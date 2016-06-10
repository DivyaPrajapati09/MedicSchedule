package com.example.asus.medic_schedule;

/**
 * Created by PRADEPBHAI on 11/4/2014.
 */
public class RowItem {
    private int image;
    private String add;
    private String name;

    public RowItem(int image,String name,String add)
    {
        this.image=image;
        this.name=name;
        this.add=add;
    }

    public int getImage()
    {
        return image;
    }
    public String getName()
    {
        return name;
    }
    public String getAdd()
    {
        return add;
    }

    public void setImage(int image)
    {
        this.image=image;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setAdd(String add)
    {
        this.add=add;
    }

    @Override
    public String toString()
    {
        return add;
    }
}