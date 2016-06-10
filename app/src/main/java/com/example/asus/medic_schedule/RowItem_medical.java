package com.example.asus.medic_schedule;

/**
 * Created by ASUS on 4/17/2015.
 */
public class RowItem_medical {

        private int icon;
        private String desc;
        private String title;

        public RowItem_medical(int icon,String title,String desc)
        {
            this.icon=icon;
            this.desc=desc;
            this.title=title;
        }
        public int getIcon()
        {
            return icon;
        }
        public String getDesc()
        {
            return desc;
        }
        public String getTitle()
        {
            return title;
        }
        public void setIcon(int icon)
        {
            this.icon=icon;
        }
        public void setDesc(String desc)
        {
            this.desc=desc;
        }
        public void setTitle(String title)
        {
            this.title=title;
        }

        @Override
        public String toString()
        {
            return desc;
        }
    }


