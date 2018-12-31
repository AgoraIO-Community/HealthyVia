package io.agora.ui.model;


public class Item_Friends_Talke_Model {
    Integer item_friend_talke_image;
    String item_friend_talke_name_text;

    public Item_Friends_Talke_Model(Integer item_friend_talke_image, String item_friend_talke_name_text) {
        this.item_friend_talke_image = item_friend_talke_image;
        this.item_friend_talke_name_text = item_friend_talke_name_text;
    }

    public Integer getItem_friend_talke_image() {
        return item_friend_talke_image;
    }

    public void setItem_friend_talke_image(Integer item_friend_talke_image) {
        this.item_friend_talke_image = item_friend_talke_image;
    }

    public String getItem_friend_talke_name_text() {
        return item_friend_talke_name_text;
    }

    public void setItem_friend_talke_name_text(String item_friend_talke_name_text) {
        this.item_friend_talke_name_text = item_friend_talke_name_text;
    }
}
