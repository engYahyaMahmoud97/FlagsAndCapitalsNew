package com.yahyam.flagsandcapitals.model;

public class AUser {
    private String username ;
    private String whatsappNumber ;
    private String id ;
    private String imageProfile ;

    private boolean isBin = false;

    private String coins ;
    private String clk ;

    public AUser(String username,String whatsappNumber, String id) {
        this.username = username;
        this.whatsappNumber = whatsappNumber;
        this.id = id;
    }

    public AUser() {
    }

    public boolean isBin() {
        return isBin;
    }

    public void setBin(boolean bin) {
        isBin = bin;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public String getClk() {
        return clk;
    }

    public void setClk(String clk) {
        this.clk = clk;
    }
    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
