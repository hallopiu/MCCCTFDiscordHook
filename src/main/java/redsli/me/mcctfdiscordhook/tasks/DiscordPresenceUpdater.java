package redsli.me.mcctfdiscordhook.tasks;

import com.jagrosh.discordipc.entities.RichPresence;
import redsli.me.mcctfdiscordhook.Main;
import redsli.me.mcctfdiscordhook.util.BoardHelper;
import redsli.me.mcctfdiscordhook.util.CtfHelper;
import redsli.me.mcctfdiscordhook.util.ServerHelper;

import java.util.TimerTask;

import static redsli.me.mcctfdiscordhook.events.JoinEvent.SB_TITLE;

/**
 * Created by redslime on 28.11.2017
 *
 * Updates the discord presence
 */
public class DiscordPresenceUpdater extends TimerTask {

    /**
     * Stops the task
     */
	public void shutdown() {
		cancel();
	}

    /**
     * Update ctf or minecraft presence, depending what server the player is on
     */
	@Override
	public void run() {
	    // Scoreboard title matches CTF scoreboard title regex -> ctf server
        if(BoardHelper.getBoardTitle().matches(SB_TITLE)) {
            // build rich presence
            RichPresence richPresence = new RichPresence.Builder()
                    .setDetails("On " + CtfHelper.getIpAddress())
                    .setState("Map: " + CtfHelper.getMapName())
                    .setLargeImage("red_wool", "Capture the Flag")
                    .setParty("Game", CtfHelper.getCurrentMap(), CtfHelper.getAllMaps())
                    .build();
            // send it
            Main.discordHelperCtf.setPresence(richPresence);
        } else { // either another server or main menu/singleplayer
            // build rich presence
            RichPresence richPresence = new RichPresence.Builder()
                    .setState(ServerHelper.getState())
                    .setLargeImage("minecraft", "Minecraft")
                    .build();
            // send it
            Main.discordHelperMinecraft.setPresence(richPresence);
        }
	}
}