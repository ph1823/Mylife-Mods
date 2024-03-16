package fr.ph1823.mylife.network.phone;

import fr.ph1823.mylife.data.PhoneSavedData;
import fr.ph1823.mylife.gui.phone.GuiSMS;
import fr.ph1823.mylife.utility.SMS;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.*;

public class SMSListMessage implements IMessage {

    private HashMap<String, List<SMS>> objects = new HashMap<>();
    private boolean isServer = true;
    private String num = "";

    public SMSListMessage() {}

    public SMSListMessage(HashMap<String, List<SMS>> smsList) {
        this.objects = smsList;
    }

    public SMSListMessage(String num) {
        this.isServer = false;
        this.num = num;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        int size = buf.readInt();
        if (size >= 0) {
            for (int i = 0; i < size; i++) {
                String key = ByteBufUtils.readUTF8String(buf);
                int listSize = buf.readInt();
                List<SMS> smsList = new ArrayList<>();
                for (int j = 0; j < listSize; j++) {
                    Date date = new Date(buf.readLong());
                    String message = ByteBufUtils.readUTF8String(buf);
                    smsList.add(new SMS(message, date));
                }
                objects.put(key, smsList);
            }
        } else {
            this.num = ByteBufUtils.readUTF8String(buf);
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        if (this.isServer) {
            buf.writeInt(objects.size());
            for (Map.Entry<String, List<SMS>> entry : objects.entrySet()) {
                ByteBufUtils.writeUTF8String(buf, entry.getKey());
                buf.writeInt(entry.getValue().size());
                for (SMS object : entry.getValue()) {
                    buf.writeLong(object.getDate().getTime());
                    ByteBufUtils.writeUTF8String(buf, object.getMessage());
                }
            }
        } else {
            buf.writeInt(-1);
            ByteBufUtils.writeUTF8String(buf, this.num);
        }
    }

    public static class Handler implements IMessageHandler<SMSListMessage, IMessage> {

        @Override
        public IMessage onMessage(SMSListMessage message, MessageContext ctx) {
            // if its send to server we reply with message list
            if(ctx.side.isServer()) {
                EntityPlayerMP playerMP = ctx.getServerHandler().player;
                PhoneSavedData data = PhoneSavedData.get(playerMP.world);
                // check if requested is equals to phone owner
                if(data.getDataFromPhone(message.num).getOwner().equals(playerMP.getPersistentID()))
                    return new SMSListMessage(data.getDataFromPhone(message.num).getSmsList());
                else return null;
            } else Minecraft.getMinecraft().displayGuiScreen(new GuiSMS(message.objects));
            return null; // no response in this case
        }
    }
}
