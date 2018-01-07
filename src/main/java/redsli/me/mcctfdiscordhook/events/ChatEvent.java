package redsli.me.mcctfdiscordhook.events;

import redsli.me.mcctfdiscordhook.util.CtfHelper;
import redsli.me.mcctfdiscordhook.util.IgnoreResult;
import redsli.me.mcctfdiscordhook.util.ServerHelper;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by redslime on 28.11.2017
 *
 * Handles incoming chat messages
 */
public class ChatEvent {

    // regex
	public static final String RE_IP = "Server Address: (.*)";
	public static final String RE_MAP = ".*Map: (.*) \\(.*\\).*";

    /**
     * Handle incoming chat messages
     * @param e event
     */
	@SubscribeEvent
	public void onChat(final ClientChatReceivedEvent e) {
		if (e.message == null || e.message.getUnformattedText() == null) return;
		String message = e.message.getUnformattedText();
		catchIP(message);
		catchMap(message);
		IgnoreResult.checkAll(e);
	}

    /**
     * Reads IP from message and sets it to the currentIp in ServerHelper
     * @param message The message to read
     */
	public static void catchIP(String message) {
	    // regex applies
		if (message.matches(RE_IP)) {
		    // get ip with regex and set
			ServerHelper.currentIP = message.replaceAll(RE_IP, "$1");
		}
	}

    /**
     * Reads map name from message and sets it to the current map in CtfHelper
     * @param message The message to read
     */
	public static void catchMap(String message) {
	    // regex applies
		if(message.matches(RE_MAP)) {
		    // get map with regex and set
			CtfHelper.map = message.replaceAll(RE_MAP, "$1");
		}
	}
}
