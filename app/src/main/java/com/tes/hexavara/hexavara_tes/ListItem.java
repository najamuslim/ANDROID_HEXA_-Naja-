package com.tes.hexavara.hexavara_tes;

public class ListItem {
    private String head,desc,photo;

    public ListItem(String head, String desc, String photo) {
        this.head = head;
        this.desc = desc;
        this.photo = photo;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getPhoto() {
        return photo;
    }
}
