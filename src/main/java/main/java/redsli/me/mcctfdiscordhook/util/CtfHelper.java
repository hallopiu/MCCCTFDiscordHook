package main.java.redsli.me.mcctfdiscordhook.util;

/**
 * Created by redslime on 28.11.2017
 */
public class CtfHelper {
	
	public static final String RE_CURRENT_MAP = ".*\\[([0-9])\\/[0-9]\\].*";
	public static final String RE_ALL_MAPS = ".*\\[[0-9]\\/([0-9])\\].*";
	
	public static String map = "???";
	
	public static String getMapName() {
		return map;
	}
	
	public static String getIpAddress() {
		return ServerHelper.currentIP;
	}
	
	public static int getCurrentMap() {
		if(ServerHelper.isOnCTF())
			if(BoardHelper.getBoardTitle() != null) {
				String currentMap = BoardHelper.getBoardTitle().replaceAll(RE_CURRENT_MAP, "$1");
				return safeNumber(currentMap);
			}
		return -1;
	}
	
	public static int getAllMaps() {
		if(ServerHelper.isOnCTF())
			if(BoardHelper.getBoardTitle() != null) {
				String allMaps = BoardHelper.getBoardTitle().replaceAll(RE_ALL_MAPS, "$1");
				return safeNumber(allMaps);
			}
		return -1;
	}
	
	private static int safeNumber(String str) {
		try {
			int i = Integer.valueOf(str);
			return i;
		} catch(NumberFormatException e) {}
		return 0;
	}
}