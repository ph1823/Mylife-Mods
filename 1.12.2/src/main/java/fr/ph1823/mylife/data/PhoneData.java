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

    public void addSMS(String num, String content, String date, PhoneSavedData data) {
        try {
            SMS sms = new SMS(content, date);
            this.addSMS(num, sms, data);
        } catch (ParseException e) {
            MyLifeMod.LOGGER.info("Parse date error of sms");
            e.printStackTrace();
        }
    }

    public void addSMS(String num, String content, Date date, PhoneSavedData data) {
        SMS sms = new SMS(content, date);
        this.addSMS(num, sms, data);
    }

    public void addSMS(String num, SMS sms, PhoneSavedData data) {
        if (!smsList.containsKey(num)) this.smsList.put(num, new LinkedList<>(Collections.singletonList(sms)));
        else this.smsList.get(num).add(sms);

        if(data != null) data.markDirty();
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
