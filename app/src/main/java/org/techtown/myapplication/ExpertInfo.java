package org.techtown.myapplication;

public class ExpertInfo {
    private String introduce;
    private String career;
    private String price;
    private String careablePet;

    public ExpertInfo(){

    }

    public ExpertInfo(String introduce, String career, String price, String careablePet) {
        this.introduce = introduce;
        this.career = career;
        this.price = price;
        this.careablePet = careablePet;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCareablePet() {
        return careablePet;
    }

    public void setCareablePet(String careablePet) {
        this.careablePet = careablePet;
    }
}


