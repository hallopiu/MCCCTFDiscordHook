package redsli.me.mcctfdiscordhook.util;

import redsli.me.mcctfdiscordhook.Main;

/**
 * Created by redslime on 28.11.2017
 */
public class ServerHelper {
	
	public static String currentIP = "???";
	
	public static boolean isOnCTF() {
		return (currentIP.contains("mcctf.com") || currentIP.contains("ctfmatch.brawl.com")) && !Main.mc.isSingleplayer();
	}

	public static String getState() {
	    if(Main.mc.getCurrentServerData() != null) {
            if (Main.mc.getCurrentServerData().serverIP != null) {
                if (Main.mc.getCurrentServerData().serverIP.trim() != "") {
                    return "On " + Main.mc.getCurrentServerData().serverIP;
                }
            }
        }
        return "In main menu";
	}
}