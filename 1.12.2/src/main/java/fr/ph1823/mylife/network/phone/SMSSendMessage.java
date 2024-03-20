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

    private int sender, receiver;
    private String message;

    public SMSSendMessage() {
    }

    public SMSSendMessage(int sender, int receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.sender = buf.readInt();
        this.receiver = buf.readInt();
        this.message = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.sender);
        buf.writeInt(this.receiver);
        ByteBufUtils.writeUTF8String(buf, this.message);
    }


    public static class Handler implements IMessageHandler<SMSSendMessage, IMessage> {

        @Override
        public IMessage onMessage(SMSSendMessage message, MessageContext ctx) {
            // if its send to server we reply with message list
            if(ctx.side.isServer()) {
                EntityPlayerMP playerMP = ctx.getServerHandler().player;
                PhoneSavedData data = PhoneSavedData.get(playerMP.world);

                data.addSMS(message.sender, message.receiver, message.message);
            }
            return null; // no response in this case
        }
    }
}
