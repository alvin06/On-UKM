package com.example.ilhamk.adminonukm;

public class Upload {
    private String imName;
    private String imURL;

    public Upload() {
    }

    public Upload(String imName, String imURL) {
        if(imName.trim().equals("")){
            imName = "No name";
        }
        this.imName = imName;
        this.imURL = imURL;
    }

    public String getImName() {
        return imName;
    }

    public void setImName(String imName) {
        this.imName = imName;
    }

    public String getImURL() {
        return imURL;
    }

    public void setImURL(String imURL) {
        this.imURL = imURL;
    }
}
