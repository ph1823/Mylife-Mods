package fr.ph1823.mylife.data;

import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.utility.phone.Contact;
import fr.ph1823.mylife.utility.phone.Conversation;
import fr.ph1823.mylife.utility.phone.PhoneData;
import fr.ph1823.mylife.utility.phone.SMS;
import net.minecraft.nbt.*;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class PhoneSavedData extends WorldSavedData {
    private static final String DATA_NAME = MyLifeMod.MODID + "_phone";
    private final HashMap<String, PhoneData> phoneNumbers = new HashMap<>();
    private List<Conversation> conversations = new LinkedList<>();

    public PhoneSavedData() {
        super(DATA_NAME);
    }

    public PhoneSavedData(String name) {
        super(name);
    }

    public PhoneData getDataFromPhone(String num) {
        return this.phoneNumbers.get(num);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        for(String key: nbt.getCompoundTag("phones").getKeySet()) {
            NBTTagCompound phoneData = nbt.getCompoundTag(key);
            PhoneData data = new PhoneData();

            //Load sms in phone data
            NBTTagCompound conversationList = phoneData.getCompoundTag("conversation");

            //Load history in phone data

            // Set owner
            data.setOwner(UUID.fromString(phoneData.getString("owner")));
            //Add data in hashmap
            this.phoneNumbers.put(key, data);
        }

        MyLifeMod.LOGGER.info("hashmap: " + this.phoneNumbers.entrySet().size());
    }

    @Override
    @Nonnull
    public NBTTagCompound writeToNBT(@Nonnull NBTTagCompound compound) {
        //Save data of phonesData
        NBTTagCompound phonesData = new NBTTagCompound();
        NBTTagList conversationsTag = new NBTTagList();
        this.phoneNumbers.forEach((number, phoneData) -> {
            NBTTagCompound phoneDataTag = new NBTTagCompound();

            // Save conversation id in list
            NBTTagList conversationList = new NBTTagList();
            phoneData.getConversations().forEach(id -> conversationList.appendTag(new NBTTagInt(id)));

            //Get history
            phoneDataTag.setTag("conversation", conversationList);
            phoneDataTag.setTag("call_history", new NBTTagList());
            phoneDataTag.setString("owner", phoneData.getOwner().toString());
            //NBTTagString
            phonesData.setTag(number, phoneDataTag);
        });

        // set tag phones to save data in key named "phones"
        compound.setTag("phones", phonesData);

        // saved conversations
        this.conversations.forEach((conversation) -> {
            NBTTagCompound conversationTag = new NBTTagCompound();
            conversationTag.setIntArray("numbers", conversation.getNumbers());

            // Save sms list
            NBTTagList smsList = new NBTTagList();
            conversation.getSmsList().forEach(sms -> {
                NBTTagCompound smsTag = new NBTTagCompound();
                smsTag.setLong("date", sms.getDate().getTime());
                smsTag.setString("message", sms.getMessage());

                // Convet contact to nbt tag
                smsTag.setTag("contact", sms.getContact().toNBT());
                smsList.appendTag(smsTag);
            });

            conversationTag.setTag("sms", smsList);
            conversationsTag.appendTag(smsList);
        });

        compound.setTag("conversation", conversationsTag);
        return compound;
    }

    public static PhoneSavedData get(World world) {
        // The IS_GLOBAL constant is there for clarity, and should be simplified into the right branch.
        MapStorage storage = world.getMapStorage();

        assert storage != null;
        PhoneSavedData instance = (PhoneSavedData) storage.getOrLoadData(PhoneSavedData.class, DATA_NAME);

        if (instance == null) {
            instance = new PhoneSavedData();
            storage.setData(DATA_NAME, instance);
        }
        return instance;
    }

    public boolean addNumber(int number) {
        if(!this.phoneNumbers.containsKey(String.valueOf(number))) {
            this.phoneNumbers.put(String.valueOf(number), new PhoneData());
            this.markDirty();
            return true;
        }
        return false;
    }


    public void setOwner(UUID persistentID, String num) {
        if(this.phoneNumbers.containsKey(num) && !this.phoneNumbers.get(num).getOwner().equals(persistentID)) {
            this.phoneNumbers.get(num).setOwner(persistentID);
            this.markDirty();
        }
    }

    public Conversation findConversation(int num1, int num2) {
        return this.conversations
                .stream()
                .filter(conversation -> conversation.containsNum(num1) && conversation.containsNum(num2))
                .findFirst().orElse(new Conversation(this.conversations.size(), num1, num2));
    }

    public void addSMS(int sender, int receiver, String message) {
        Conversation conversation = this.findConversation(sender, receiver);
        conversation.addSMS(message);

        if(!this.conversations.contains(conversation)) this.conversations.add(conversation);
    }
}
