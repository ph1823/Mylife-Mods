package fr.ph1823.mylife.network.phone;

import fr.ph1823.mylife.data.PhoneSavedData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Date;

public class SMSSendMessage implements IMessage {

    private String sender, receiver, message;

    public SMSSendMessage() {
    }

    public SMSSendMessage(String sender, String receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.sender = ByteBufUtils.readUTF8String(buf);
        this.receiver = ByteBufUtils.readUTF8String(buf);
        this.message = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.sender);
        ByteBufUtils.writeUTF8String(buf, this.receiver);
        ByteBufUtils.writeUTF8String(buf, this.message);
    }


    public static class Handler implements IMessageHandler<SMSSendMessage, IMessage> {

        @Override
        public IMessage onMessage(SMSSendMessage message, MessageContext ctx) {
            // if its send to server we reply with message list
            if(ctx.side.isServer()) {
                EntityPlayerMP playerMP = ctx.getServerHandler().player;
                PhoneSavedData data = PhoneSavedData.get(playerMP.world);

                data.getDataFromPhone(message.receiver).addSMS(message.sender, message.message, new Date(), data);
            }
            return null; // no response in this case
        }
    }
}
