package com.example.asus.medic_schedule;

/**
 * Created by ASUS on 2/2/2015.
 */
public class RowItem_bp {

    private String sys;
    private String dys;
   // private int Image;
    private String pul;
    //private String time;

    public RowItem_bp(String sys,String dys,String pul){
        this.sys=sys;
        this.dys=dys;
       // this.Image=Image;
        this.pul=pul;
       // this.time=time;

    }

    public String getSys()
    {
        return sys;
    }
    public String getDys()
    {
        return dys;
    }
    /*public int getImage()
    {
        return Image;
    }*/
    public String getPul()
    {
        return pul;
    }
   /* public String getTime()
    {
        return time;
    }*/

    public void setSys(String sys)
    {
        this.sys=sys;
    }
    public void setDys(String dys)
    {
        this.dys=dys;
    }
   /* public void setImage(int Image)
    {
        this.Image=Image;
    }*/
    public void setPul(String pul)
    {
        this.pul=pul;
    }
    /*public void setTime(String time)
    {
        this.time=time;
    }*/

   @Override
    public String toString(){
        return "sys" + "dys" + "Image" + "pulse";

    }
}
