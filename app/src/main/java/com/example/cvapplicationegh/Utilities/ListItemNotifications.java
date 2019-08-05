package com.example.cvapplicationegh.Utilities;




public class ListItemNotifications {

    private String txtContent,txtUrlImage,txtTime;

    public ListItemNotifications(String mContent, String urlImage, String mTime) {
        this.txtContent = mContent;
        this.txtUrlImage = urlImage;
        this.txtTime = mTime;
    }

    public String getStringTitleContent() {
        return txtContent;
    }
    public String getStringUrlImage() {
        return txtUrlImage;
    }
    public String getStringTime() {
        return txtTime;
    }

}
