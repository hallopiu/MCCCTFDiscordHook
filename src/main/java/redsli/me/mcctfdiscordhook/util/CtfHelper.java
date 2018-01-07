package redsli.me.mcctfdiscordhook.util;

/**
 * Created by redslime on 28.11.2017
 *
 * Helps fetching CTF data
 */
public class CtfHelper {

    // regex
	public static final String RE_CURRENT_MAP = ".*\\[([0-9])\\/[0-9]\\].*";
	public static final String RE_ALL_MAPS = ".*\\[[0-9]\\/([0-9])\\].*";

	// current map name
	public static String map = "???";

    /**
     * @return The name of the current map
     */
	public static String getMapName() {
		return map;
	}

    /**
     * @return The ip of the current server
     */
	public static String getIpAddress() {
		return ServerHelper.currentIP;
	}

    /**
     * @return The current map # from the scoreboard title
     */
	public static int getCurrentMap() {
		if(ServerHelper.isOnCTF())
			if(BoardHelper.getBoardTitle() != null) {
				String currentMap = BoardHelper.getBoardTitle().replaceAll(RE_CURRENT_MAP, "$1");
				return safeNumber(currentMap);
			}
		return -1;
	}

    /**
     * @return The max map # from the scoreboard title
     */
	public static int getAllMaps() {
		if(ServerHelper.isOnCTF())
			if(BoardHelper.getBoardTitle() != null) {
				String allMaps = BoardHelper.getBoardTitle().replaceAll(RE_ALL_MAPS, "$1");
				return safeNumber(allMaps);
			}
		return -1;
	}

    /**
     * Safely parses a string into an int. If string provided is not a number, 0 is returned
     * @param str The string to parse
     * @return The parsed int from input string or 0 if invalid string given
     */
	private static int safeNumber(String str) {
		try {
			int i = Integer.valueOf(str);
			return i;
		} catch(NumberFormatException e) {}
		return 0;
	}
}