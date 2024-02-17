package fr.ph1823.mylife.capability;

public class Profile implements IProfile {
    private double money = 0;
    private String firstname = "";
    private String lastname = "";
    @Override
    public double getMoney() {
        return this.money;
    }

    @Override
    public String getFistname() {
        return this.firstname;
    }

    @Override
    public String getLastname() {
        return this.lastname;
    }

    @Override
    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public void setFistname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public void update(IProfile profile) {
        this.money = profile.getMoney();
        this.firstname = profile.getFistname();
        this.lastname = profile.getLastname();
    }

    public static Profile parseString(String str) {
        String[] info = str.split(":");
        Profile profile = new Profile();
        profile.setFistname(info[0]);
        profile.setLastname(info[1]);
        profile.setMoney(Double.parseDouble(info[2]));

        return profile;
    }

    @Override
    public String toString() {
        return this.firstname + ":" + this.firstname + ":" + this.money;
    }
}
