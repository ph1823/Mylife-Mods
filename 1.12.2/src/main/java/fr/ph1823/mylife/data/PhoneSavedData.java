package fr.ph1823.mylife.data;

import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.utility.SMS;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.UUID;

public class PhoneSavedData extends WorldSavedData {
    private static final String DATA_NAME = MyLifeMod.MODID + "_phone";
    private final HashMap<String, PhoneData> phoneNumbers = new HashMap<>();

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
        for(String key: nbt.getKeySet()) {
            NBTTagCompound numberNBT = nbt.getCompoundTag(key);
            PhoneData data = new PhoneData();

            //Load sms in phone data
            NBTTagCompound smsTAG = numberNBT.getCompoundTag("sms");
            for (String num : smsTAG.getKeySet()) {
                NBTTagList smsList = smsTAG.getTagList(num, 10);
                for (NBTBase nbtBase : smsList) {
                    if(nbtBase instanceof NBTTagString) {
                        String[] dataInfo = ((NBTTagString) nbtBase).getString().split("::", 2);
                        data.addSMS(num, dataInfo[0], dataInfo[1], null);
                    }
                }
            }

            //Load history in phone data

            // Set owner
            data.setOwner(UUID.fromString(numberNBT.getString("owner")));
            //Add data in hashmap
            this.phoneNumbers.put(key, data);
        }

        MyLifeMod.LOGGER.info("hashmap: " + this.phoneNumbers.entrySet().size());

    }

    @Override
    @Nonnull
    public NBTTagCompound writeToNBT(@Nonnull NBTTagCompound compound) {
        //Get data of stocked number phone
        this.phoneNumbers.forEach((number, phoneData) -> {
            NBTTagCompound numberTAG = new NBTTagCompound();
            NBTTagCompound numSMSTAG = new NBTTagCompound();
            //Create SMS list in nbt and add all existing SMS
            phoneData.getSmsList().forEach((senderNum, smsList) -> {
                //sms is json with date and content
                NBTTagList smsListTAG = new NBTTagList();
                for (SMS sms : smsList)
                    smsListTAG.appendTag(new NBTTagString(sms.toString()));

                numSMSTAG.setTag(senderNum, smsListTAG);
            });

            //Get history
            numberTAG.setTag("sms", numSMSTAG);
            numberTAG.setTag("call_history", new NBTTagList());
            numberTAG.setString("owner", phoneData.getOwner().toString());
            //NBTTagString
            compound.setTag(number, numberTAG);
        });
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
}
