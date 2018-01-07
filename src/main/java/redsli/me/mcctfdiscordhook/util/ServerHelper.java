package redsli.me.mcctfdiscordhook.util;

import redsli.me.mcctfdiscordhook.Main;

/**
 * Created by redslime on 28.11.2017
 *
 * Helps fetching server data
 */
public class ServerHelper {
	
	public static String currentIP = "???";

    /**
     * @return Whether the current server is a ctf server or not. Depends on accurate ip information -> {@link #currentIP}
     */
	public static boolean isOnCTF() {
		return (currentIP.contains("mcctf.com") || currentIP.contains("ctfmatch.brawl.com")) && !Main.mc.isSingleplayer();
	}

    /**
     * @return State used for Minecraft discord presence
     */
	public static String getState() {
	    if(Main.mc.getCurrentServerData() != null) {
            if (Main.mc.getCurrentServerData().serverIP != null) {
                if (Main.mc.getCurrentServerData().serverIP.trim() != "") {
                    return "On " + Main.mc.getCurrentServerData().serverIP; // this is the ip as specified in the multiplayer server list
                }
            }
        }
        return "In main menu";
	}
}