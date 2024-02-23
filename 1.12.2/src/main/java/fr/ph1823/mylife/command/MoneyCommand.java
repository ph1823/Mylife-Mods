package fr.ph1823.mylife.command;

import com.mojang.authlib.GameProfile;
import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.capability.IProfile;
import fr.ph1823.mylife.capability.Profile;
import fr.ph1823.mylife.capability.ProfileCapability;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.server.permission.PermissionAPI;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MoneyCommand extends CommandBase {
    @Override
    public String getName() {
        return "money";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "command.money.usage";
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        if(args.length == 1) return Arrays.asList("get", "set", "give", "remove");
        else if(args.length == 2) return Arrays.asList(server.getOnlinePlayerNames());
        else return super.getTabCompletions(server, sender, args, targetPos);
    }
    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        TextComponentString returnMessage = new TextComponentString("Utilisation incorrecte de la commande.");
        returnMessage.appendText("lol").setStyle(returnMessage.getStyle().setColor(TextFormatting.AQUA));
        EntityPlayer player = null;
        if(sender instanceof EntityPlayer)
            player = (EntityPlayer) sender;

        /*if(args.length == 0 && player != null && player.hasCapability(ProfileCapability.PROFILE_CAPABILITY, null)) {

        } else*/
        if(args.length >= 2 &&
                (player == null || PermissionAPI.hasPermission(player, String.format("mylife.money.%s", args[0])))
        ) {
            if(!args[0].equals("get") && args.length < 3) {
                sender.sendMessage(returnMessage);
                return;
            }
            GameProfile playerProfile = server.getPlayerProfileCache().getGameProfileForUsername(args[1]);
            if(playerProfile == null) {
                sender.sendMessage(new TextComponentString("Player not found"));
                return;
            }
            World world = sender.getEntityWorld();
            EntityPlayerMP playerMP = new EntityPlayerMP(server, (WorldServer) world, playerProfile , new PlayerInteractionManager(world));

            if(!playerMP.hasCapability(ProfileCapability.PROFILE_CAPABILITY, null)) {
                sender.sendMessage(new TextComponentString("Error player data not found"));
                return;
            }

            IProfile profile = playerMP.getCapability(ProfileCapability.PROFILE_CAPABILITY, null);
            switch (args[0]) {
                case "get":
                    sender.sendMessage(new TextComponentString("Money: " + profile.getMoney()));
                    break;
                case "set":
                    profile.setMoney(Double.parseDouble(args[2]));
                    break;
                case "remove":
                    break;
                case "give":
                    break;
            }
        } else sender.sendMessage(returnMessage);
    }
    /*
               if(player.hasCapability(ProfileCapability.PROFILE_CAPABILITY, null)) {
               IProfile profileCapability = player.getCapability(ProfileCapability.PROFILE_CAPABILITY, null);
              // MyLifeMod.logger.info("money: " + profileCapability.getMoney());
               profileCapability.setMoney(40);
           }
     */
}
