package fr.ph1823.mylife.network;

import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.capability.IProfile;
import fr.ph1823.mylife.capability.ProfileCapability;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MoneyMessage implements IMessage {
    double money;

    public MoneyMessage() {

    }

    public MoneyMessage(double money) {
        this.money = money;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        //this.money = Double.parseDouble(ByteBufUtils.readUTF8String(buf));
        this.money = buf.readDouble();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(this.money);
    }

    public static class Handler implements IMessageHandler<MoneyMessage, IMessage> {

        @Override
        public IMessage onMessage(MoneyMessage message, MessageContext ctx) {
            System.out.println(String.format("Received %s from s", message.money));
            if(ctx.getServerHandler().player != null) {
                MyLifeMod.LOGGER.info("update player");
                ctx.getServerHandler().player.getCapability(ProfileCapability.PROFILE_CAPABILITY, null).setMoney(message.money);
            }
            return null; // no response in this case
        }
    }
}
