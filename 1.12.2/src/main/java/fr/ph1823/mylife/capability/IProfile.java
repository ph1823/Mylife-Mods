package fr.ph1823.mylife.capability;

public interface IProfile {
    public double getMoney();
    public String getFistname();
    public String getLastname();


    public void setMoney(double money);
    public void setFistname(String firstname);
    public void setLastname(String lastname);
    public void update(IProfile profile);

}
