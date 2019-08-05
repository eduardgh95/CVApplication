package com.example.cvapplicationegh.Utilities;




public class ListItemHome {

    private String txtUsername,txtUrlImage,txtOcupation,txtTime,txtContent;


    public ListItemHome(String mName, String urlImage, String mOcupation, String mTime, String mContent) {

        this.txtUsername = mName;
        this.txtUrlImage = urlImage;
        this.txtOcupation = mOcupation;
        this.txtTime = mTime;
        this.txtContent = mContent;
    }


    public String getStringUsername() {
        return txtUsername;
    }
    public String getStringUrlImage() {
        return txtUrlImage;
    }
    public String getStringOcupation() {
        return txtOcupation;
    }
    public String getStringTime() {
        return txtTime;
    }
    public String getStringContent() {
        return txtContent;
    }

}
