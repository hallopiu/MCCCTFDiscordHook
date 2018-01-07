package redsli.me.mcctfdiscordhook;

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
 */

@Mod(modid = Main.MOD_ID, name = Main.MOD_NAME)
public class Main {
	
	public static final String MOD_ID = "mcctfdiscordhook";
	public static final String MOD_NAME = "MCCTF Discord Hook";
	public static final long DISCORD_APP_ID_CTF = 383987182421016577L;
    public static final long DISCORD_APP_ID_MINECRAFT = 399562856477097984L;
	public static final String PREFIX = EnumChatFormatting.DARK_GRAY + "[" + EnumChatFormatting.GOLD + "MCCTFDiscordHook" + EnumChatFormatting.DARK_GRAY+ "] " + EnumChatFormatting.WHITE;
	
	public static DiscordPresenceUpdater dpu;
	public static DiscordHelper discordHelperCtf;
	public static DiscordHelper discordHelperMinecraft;
	
	@Instance
	public static Main instance = new Main();
	
	public static Minecraft mc;
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new JoinEvent());
        MinecraftForge.EVENT_BUS.register(new ChatEvent());
        mc = Minecraft.getMinecraft();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
        discordHelperCtf = new DiscordHelper(DISCORD_APP_ID_CTF);
        discordHelperMinecraft = new DiscordHelper(DISCORD_APP_ID_MINECRAFT);
        dpu = new DiscordPresenceUpdater();

        Timer t = new Timer();
        t.schedule(dpu, 0, 10000);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            discordHelperCtf.close();
            discordHelperMinecraft.close();
            dpu.shutdown();
        }));
	}
}