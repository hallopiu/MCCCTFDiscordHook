package main.java.redsli.me.mcctfdiscordhook.events;

import main.java.redsli.me.mcctfdiscordhook.util.CtfHelper;
import main.java.redsli.me.mcctfdiscordhook.util.IgnoreResult;
import main.java.redsli.me.mcctfdiscordhook.util.ServerHelper;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by redslime on 28.11.2017
 */
public class ChatEvent {

	public static final String RE_IP = "Server Address: (.*)";
	public static final String RE_MAP = ".*Map: (.*) \\(.*\\).*";

	@SubscribeEvent
	public void onChat(final ClientChatReceivedEvent e) {
		if (e.message == null || e.message.getUnformattedText() == null) return;
		String message = e.message.getUnformattedText();
		catchIP(message);
		catchMap(message);
		IgnoreResult.checkAll(e);

	}

	public static void catchIP(String message) {
		if (message.matches(RE_IP)) {
			ServerHelper.currentIP = message.replaceAll(RE_IP, "$1");
		}
	}
	
	public static void catchMap(String message) {
		if(message.matches(RE_MAP)) {
			CtfHelper.map = message.replaceAll(RE_MAP, "$1");
		}
	}
}
