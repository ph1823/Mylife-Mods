package fr.ph1823.mylife.network;

import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.data.PhoneSavedData;
import fr.ph1823.mylife.utility.SMS;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SMSListMessage implements IMessage {

    private List<SMS> objects = new ArrayList<>();

    @Override
    public void fromBytes(ByteBuf buf) {
        int size = buf.readInt();
        for (int i = 0; i < size; i++) {
            Date date = new Date(buf.readLong());
            String message = ByteBufUtils.readUTF8String(buf);
            objects.add(new SMS(message, date));
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(objects.size());
        for (SMS object : objects) {
            buf.writeLong(object.getDate().getTime());
            ByteBufUtils.writeUTF8String(buf, object.getMessage());
        }
    }

    public static class Handler implements IMessageHandler<SMSListMessage, IMessage> {

        @Override
        public IMessage onMessage(SMSListMessage message, MessageContext ctx) {
            if(ctx.side.isServer()) {
                PhoneSavedData data = PhoneSavedData.get(ctx.getServerHandler().player.world);
                MyLifeMod.LOGGER.info(data.getDataFromPhone("285842"));
               // MyLifeMod.LOGGER.info("data sms: "+ data.getDataFromPhone("285842").getSmsList().size());
                return null;
            }
            return null; // no response in this case
        }
    }
}
