package fr.ph1823.mylife.utility.phone;

import net.minecraft.nbt.NBTTagCompound;

public class Contact {

    private int num;
    private String firstname = "";
    private String lastname = "";

    public Contact(int num, String firstname, String lastname) {
        this.num = num;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Contact(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public NBTTagCompound toNBT() {
        NBTTagCompound contactTag = new NBTTagCompound();
        contactTag.setString("firstname", this.getFirstname());
        contactTag.setString("lastname", this.getLastname());
        contactTag.setInteger("number", this.getNum());

        return contactTag;
    }
}