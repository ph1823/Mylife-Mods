package fr.ph1823.mylife.data;

import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.utility.SMS;

import java.text.ParseException;
import java.util.*;

public class PhoneData {
    private HashMap<String, List<SMS>> smsList = new HashMap<>();
    private List<String> callHistorys = new LinkedList<>();
    private UUID owner;

    public HashMap<String, List<SMS>> getSmsList() {
        return smsList;
    }

    public void addSms(String num, String content, String date) {
        try {
            SMS sms = new SMS(content, date);
            if (!smsList.containsKey(num)) this.smsList.put(num, new LinkedList<>(Collections.singletonList(sms)));
            else this.smsList.get(num).add(sms);
        } catch (ParseException e) {
            MyLifeMod.LOGGER.info("Parse date error of sms");
            e.printStackTrace();
        }
    }

    public void addSms(String num, String content, Date date) {
        // todo male a function to evitate duplicate code
        SMS sms = new SMS(content, date);
        if (!smsList.containsKey(num)) this.smsList.put(num, new LinkedList<>(Collections.singletonList(sms)));
        else this.smsList.get(num).add(sms);
    }

    public List<String> getCallHistorys() {
        return callHistorys;
    }

    public void addCall(String number) {
        this.callHistorys.add(number);
    }

    @Override
    public String toString() {
        return "SMS: " + this.smsList.size();
    }

    public void setOwner(UUID persistentID) {
        this.owner = persistentID;
    }

    public UUID getOwner() {
        return this.owner;
    }
}
