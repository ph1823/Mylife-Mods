package fr.ph1823.mylife.data;

import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.utility.SMS;

import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PhoneData {
    private HashMap<String, List<SMS>> smsList = new HashMap<>();
    private List<String> callHistorys = new LinkedList<>();

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
}
