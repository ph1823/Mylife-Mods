package fr.ph1823.mylife.utility.phone;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Conversation {

    private int id;
    private int[] nums = new int[2];
    private final List<SMS> smsList = new LinkedList<>();

    public Conversation(int id, int num1, int num2) {
        this.id = id;
        this.nums[0] = num1;
        this.nums[1] = num2;
    }

    public boolean containsNum(int num) {
        return nums[0] == num || nums[1] == num;
    }

    public int[] getNumbers() {
        return this.nums;
    }

    public List<SMS> getSmsList() {
        return smsList;
    }

    public void addSMS(String message) {
        this.smsList.add(new SMS(message, new Date()));
    }

    public void addSMS(String message, int timestamp) {
        this.smsList.add(new SMS(message, new Date(timestamp)));
    }
}
