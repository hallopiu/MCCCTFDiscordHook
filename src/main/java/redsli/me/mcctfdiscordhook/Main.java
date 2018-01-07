package redsli.me.mcctfdiscordhook;

import net.minecraft.util.ChatComponentText;
import redsli.me.mcctfdiscordhook.events.ChatEvent;
import redsli.me.mcctfdiscordhook.events.JoinEvent;
import redsli.me.mcctfdiscordhook.tasks.DiscordPresenceUpdater;
import redsli.me.mcctfdiscordhook.util.DiscordHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import java.util.Timer;

/**
 * Created by redslime on 28.11.2017
 *
 * Main class for mcctfdiscordhook mod
 */

@Mod(modid = Main.MOD_ID, name = Main.MOD_NAME)
public class Main {
	
	public static final String MOD_ID = "mcctfdiscordhook";
	public static final String MOD_NAME = "MCCTF Discord Hook";
	public static final long DISCORD_APP_ID_CTF = 383987182421016577L;
    public static final long DISCORD_APP_ID_MINECRAFT = 399562856477097984L;
	public static final String PREFIX = EnumChatFormatting.DARK_GRAY + "[" + EnumChatFormatting.GOLD + "MCCTFDiscordHook" + EnumChatFormatting.DARK_GRAY+ "] " + EnumChatFormatting.WHITE;

    @Instance
    public static Main instance = new Main();
    public static Minecraft mc;
	public static DiscordPresenceUpdater dpu;
	public static DiscordHelper discordHelperCtf;
	public static DiscordHelper discordHelperMinecraft;
	public static boolean error = false;
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
	    // register listeners
        MinecraftForge.EVENT_BUS.register(new JoinEvent());
        MinecraftForge.EVENT_BUS.register(new ChatEvent());
        mc = Minecraft.getMinecraft();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
        discordHelperCtf = new DiscordHelper(DISCORD_APP_ID_CTF);
        discordHelperMinecraft = new DiscordHelper(DISCORD_APP_ID_MINECRAFT);
        dpu = new DiscordPresenceUpdater();

        // run updater every 10 seconds
        Timer t = new Timer();
        t.schedule(dpu, 0, 10000);

        // close discord hooks on shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            discordHelperCtf.close();
            discordHelperMinecraft.close();
            dpu.shutdown();
        }));
	}

    /**
     * Sends a message to the player
     * @param message The message to send
     */
	public static void sendMessage(String message) {
        Main.mc.thePlayer.addChatMessage(new ChatComponentText(PREFIX + message));
    }
}