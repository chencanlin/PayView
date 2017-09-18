package com.ccl.perfectisshit.payview.bean;

/**
 * Created by ccl on 2017/9/18.
 */

public class KeyboardItemBean {
    private String title;
    private int tag;

    public KeyboardItemBean() {
    }

    public KeyboardItemBean(String title, int tag) {
        this.title = title;
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "KeyboardItemBean{" +
                "title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }

    public static interface KeyboardTag {
        int TAG_NUMBER = 0;
        int TAG_BLANK = 1;
        int TAG_DELETE = 2;
    }
}
