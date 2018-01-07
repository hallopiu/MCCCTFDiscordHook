package redsli.me.mcctfdiscordhook.util;

import redsli.me.mcctfdiscordhook.Main;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;

/**
 * Created by redslime on 26.11.2017
 * 
 * @author NomNuggetNom
 * @see "https://github.com/NomNuggetNom/mcpvp-mod/blob/master/src/main/java/us/mcpvpmod/util/BoardHelper.java"
 */
public class BoardHelper {
	
	/**
	 * Returns the "title" of the scoreboard's display.
	 */
	public static String getBoardTitle() {
		if (Main.mc.theWorld == null) return "";
		if (Main.mc.theWorld.getScoreboard() == null) return "";
		
		Scoreboard board = Minecraft.getMinecraft().theWorld.getScoreboard();
		if (board != null) {
	        
	        // Null check. For some reason, sometimes _a(0) works and other times _a(1) works.
	        if (board.getObjectiveInDisplaySlot(0) != null) {
				return board.getObjectiveInDisplaySlot(0).getDisplayName();
	        } else if (board.getObjectiveInDisplaySlot(1) != null) {
	        	return board.getObjectiveInDisplaySlot(1).getDisplayName();
	        }
		}
		return "";
	}
	
	/**
	 * Returns the score of the "team" (really a fake player) on the scoreboard.
	 */
	@Deprecated
	public static int getScore(String displayName) {
		if (Main.mc.theWorld == null) return -1;
		if (Main.mc.theWorld.getScoreboard() == null) return -1;
		Scoreboard board = Minecraft.getMinecraft().theWorld.getScoreboard();
		
		if (board != null) {
			// Grab the main objective of the scoreboard.
	        ScoreObjective objective = board.getObjectiveInDisplaySlot(1);
	        
	        // Collection of all scoreboard teams.
	        Collection teams = board.getTeams();
	        
	        // Iterate through the collection of entries.
	        Iterator iterator = teams.iterator();
	        while (iterator.hasNext()) {
		        Score score = (Score)iterator.next();
		        
		        // Check if the name of the "team" (player) is what we're looking for.
		        // In actuality, it would be more precise to use an equals check e.g.:
		        // if (score1.getPlayerName().equals(team)) {
		        if (score.getPlayerName().contains(displayName)) {
		        	
		        	// Get the score of the "team" (player) and return it.
			        return score.getScorePoints();
		        }
	        }
		}
	    return -1;
	}
	
	public static Collection<String> getScores() {
		if (Main.mc.theWorld == null) return null;
		if (Main.mc.theWorld.getScoreboard() == null) return null;
		
		Collection<String> scores = new ArrayList<String>();
		Scoreboard board = Minecraft.getMinecraft().theWorld.getScoreboard();
		
		if (board != null) {
			// Grab the main objective of the scoreboard.
	        ScoreObjective objective = board.getObjectiveInDisplaySlot(1);
	        
	        // Collection of all scoreboard teams.
	        Collection<ScorePlayerTeam> teams = board.getTeams();
	        
	        // Iterate through the collection of entries.
	        Iterator<ScorePlayerTeam> iterator = teams.iterator();
	        while (iterator.hasNext()) {
		        scores.add(iterator.next().getTeamName());
	        }
		}
	    return scores;
	}
	
	public static Collection<ScorePlayerTeam> getTeams() {
		if (Main.mc.theWorld == null) return null;
		if (Main.mc.theWorld.getScoreboard() == null) return null;
		Scoreboard board = Minecraft.getMinecraft().theWorld.getScoreboard();
		
		return board.getTeams();
	}
	
	public static boolean hasTeams() {
		return (getTeams() != null);
	}
	
	/**
	 * Prints all the objectives.
	 */
	public static void printObjectives() {
		if (Main.mc.theWorld == null) return;
		if (Main.mc.theWorld.getScoreboard() == null) return;
		if (Main.mc.theWorld.getScoreboard().getObjectiveNames() == null) return;
		
		System.out.println(Main.mc.theWorld.getScoreboard().getObjectiveNames());
	}
	
	/**
	 * @param player The name of the player.
	 * @return The team name of the player.
	 */
	public static String getTeamName(String player) {
		if (Main.mc.theWorld == null) return "";
		if (Main.mc.theWorld.getScoreboard() == null) return "";
		Scoreboard board = Minecraft.getMinecraft().theWorld.getScoreboard();
		
		if (board.getPlayersTeam(player) == null) return "null";
		return board.getPlayersTeam(player).getRegisteredName();
	}
}