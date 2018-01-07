package main.java.redsli.me.mcctfdiscordhook.tasks;

import com.jagrosh.discordipc.entities.RichPresence;
import main.java.redsli.me.mcctfdiscordhook.Main;
import main.java.redsli.me.mcctfdiscordhook.util.BoardHelper;
import main.java.redsli.me.mcctfdiscordhook.util.CtfHelper;
import main.java.redsli.me.mcctfdiscordhook.util.ServerHelper;

import java.util.TimerTask;

import static main.java.redsli.me.mcctfdiscordhook.events.JoinEvent.SB_TITLE;

/**
 * Created by redslime on 28.11.2017
 */
public class DiscordPresenceUpdater extends TimerTask {

	public void shutdown() {
		cancel();
	}

	@Override
	public void run() {
        if(BoardHelper.getBoardTitle().matches(SB_TITLE)) {
            RichPresence richPresence = new RichPresence.Builder()
                    .setDetails("On " + CtfHelper.getIpAddress())
                    .setState("Map: " + CtfHelper.getMapName())
                    .setLargeImage("red_wool", "Capture the Flag")
                    .setParty("Game", CtfHelper.getCurrentMap(), CtfHelper.getAllMaps())
                    .build();

            Main.discordHelperCtf.setPresence(richPresence);
        } else {
            RichPresence richPresence = new RichPresence.Builder()
                    .setState(ServerHelper.getState())
                    .setLargeImage("minecraft", "Minecraft").build();
            Main.discordHelperMinecraft.setPresence(richPresence);
        }
	}
}