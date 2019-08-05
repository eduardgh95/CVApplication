package com.example.cvapplicationegh.Utilities;




public class ListItemEducation {

    private String txtTitle,txtLocation,txtDateMonthStart,txtDateYearStart,txtDateMonthFinish,
            txtDateYearFinish;


    public ListItemEducation(String mTitle,String mLoc,String mDMS,String mDYS,String mDMF, String mDYF) {

        this.txtTitle = mTitle;
        this.txtLocation = mLoc;
        this.txtDateMonthStart = mDMS;
        this.txtDateYearStart = mDYS;
        this.txtDateMonthFinish = mDMF;
        this.txtDateYearFinish = mDYF;
    }


    public String getStringTitle() {
        return txtTitle;
    }
    public String getStringLocation() {
        return txtLocation;
    }
    public String getStringDMonthStart() {
        return txtDateMonthStart;
    }
    public String getStringDYearStart() {
        return txtDateYearStart;
    }
    public String getStringDMonthFinish() { return txtDateMonthFinish; }
    public String getStringDYearFinish() { return txtDateYearFinish; }

}
