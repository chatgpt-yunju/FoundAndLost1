package com.flj.latte.ec.main.found;

import java.util.Date;

import cn.bmob.v3.BmobObject;


public class FoundGood extends BmobObject {
    private String title;  //标题
    private String phoneNum;//手机号码
    private String desc;//描述
    private Date time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
