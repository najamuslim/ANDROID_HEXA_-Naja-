package com.tes.hexavara.hexavara_tes;

public class ListItem {
    private String head,desc,photo;
    private Integer ID;

    public ListItem(Integer ID, String head, String desc, String photo) {
        this.ID = ID;
        this.head = head;
        this.desc = desc;
        this.photo = photo;
    }

    public Integer getID() {
        return ID;
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
