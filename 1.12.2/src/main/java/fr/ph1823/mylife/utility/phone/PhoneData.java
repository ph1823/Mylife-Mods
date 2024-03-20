package fr.ph1823.mylife.utility.phone;

import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.data.PhoneSavedData;
import fr.ph1823.mylife.utility.phone.SMS;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class PhoneData {
    private final List<String> callHistorys = new LinkedList<>();
    private final List<Contact> contacts = new LinkedList<>();
    private final List<Integer> conversations = new LinkedList<>();
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

    public Contact findContact(int num) {
        return this.contacts
                .stream()
                .filter((contact) -> contact.getNum() == num)
                .findFirst().orElseGet(() -> new Contact(num));
    }

    public List<Contact> findContactByLastname(String lastname) {
        return this.contacts
                .stream()
                .filter((contact) -> contact.getLastname().equals(lastname))
                .collect(Collectors.toList());
    }

    public List<Contact> findContactByFirstname(String firstname) {
        return this.contacts
                .stream()
                .filter((contact) -> contact.getLastname().equals(firstname))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "SMS: ";
    }

    public void setOwner(UUID persistentID) {
        this.owner = persistentID;
    }

    public UUID getOwner() {
        return this.owner;
    }

    public List<Integer> getConversations() {
        return conversations;
    }
}
