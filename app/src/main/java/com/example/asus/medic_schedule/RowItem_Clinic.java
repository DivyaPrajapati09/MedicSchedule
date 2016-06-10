package com.example.asus.medic_schedule;

/**
 * Created by ASUS on 4/12/2015.
 */
public class RowItem_Clinic {
    private int imageId;
    private String title;
    private String desc;
    private String phno;

    public RowItem_Clinic(int imageId, String title, String desc,String phno) {
        this.imageId = imageId;
        this.title = title;
        this.desc = desc;
        this.phno=phno;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPhno(){return phno;}
    public void setPhno(String phno){this.phno=phno;}
    @Override
    public String toString() {
        return title + "\n" + desc;
    }

}
