package redsli.me.mcctfdiscordhook.events;

import redsli.me.mcctfdiscordhook.Main;
import redsli.me.mcctfdiscordhook.util.BoardHelper;
import redsli.me.mcctfdiscordhook.util.IgnoreResult;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by redslime on 26.11.2017
 *
 * Handles joining
 */
public class JoinEvent {

    // regex
	public static final String SB_TITLE = "(\\[([0-9]+)\\/[0-9]+] Waiting for players...|\\[([0-9]+)\\/[0-9]+] Starts in ([0-9]+:[0-9]+)|\\[([0-9]+)\\/[0-9]+] Ends in ([0-9]+:[0-9]+)|\\[([0-9]+)\\/[0-9]+] Next game in ([0-9]+:[0-9]+)|\\[([0-9]+)\\/[0-9]+] Match Over \\| ([0-9]+:[0-9]+))";
	private long lastJoin;

    /**
     * Executed when an entity joins a world
     * @param e event
     */
	@SubscribeEvent
	public void onJoin(final EntityJoinWorldEvent e) {
	    // entitiy is our player
		if(e.entity instanceof EntityPlayer && e.entity == Main.mc.thePlayer) {
			// avoid spam-execution of code, for some reason this is fired multiple times when joining
			if(System.currentTimeMillis() - lastJoin > TimeUnit.SECONDS.toMillis(2)) {
				lastJoin = System.currentTimeMillis();
				
				// wait a bit until the scoreboard was sent to the client
				new Timer().schedule(  
					new TimerTask() {
						@Override
						public void run() {
							// player joined a ctf server
							if(BoardHelper.getBoardTitle().matches(SB_TITLE)) {
							    // get ip & map info
								new IgnoreResult("/ip", ChatEvent.RE_IP);
								new IgnoreResult("/map", ChatEvent.RE_MAP);
								if(!Main.error)
								    Main.sendMessage("Sending CTF game status to Discord!");
								else
								    Main.sendMessage(EnumChatFormatting.RED + "RIP. An error occurred. Check logs for more details");
							}
						}
					}, 2000);
			}
		}
	}
}