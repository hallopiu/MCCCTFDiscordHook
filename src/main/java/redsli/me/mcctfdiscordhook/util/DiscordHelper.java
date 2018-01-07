package redsli.me.mcctfdiscordhook.util;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.DiscordBuild;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
import org.slf4j.Logger;
import redsli.me.mcctfdiscordhook.Main;

/**
 * Created by redslime on 07.01.2018
 *
 * Holds a presence client
 */
public class DiscordHelper {

    private IPCClient client;
    private boolean connected;

    /**
     * Creates a new IPCClient with app id to send rich presences with
     * @param discordAppId The app id to connect to
     */
    public DiscordHelper(long discordAppId) {
        client = new IPCClient(discordAppId);
        client.setListener(new IPCListener() {
            @Override
            public void onPacketSent(IPCClient client, Packet packet) {
//                    System.out.println("Packet sent: " + packet);
            }

            @Override
            public void onPacketReceived(IPCClient client, Packet packet) {
//                    System.out.println("Packed received: " + packet);
            }

            @Override
            public void onReady(IPCClient client) {
                connected = true;
            }
        });
        connect();
    }

    /**
     * Sets a new presence and sends it to Discord
     * @param richPresence The presence to set
     */
    public void setPresence(RichPresence richPresence) {
        client.sendRichPresence(richPresence);
    }

    /**
     * Closes the Discord connection
     */
    public void close() {
        client.close();
        connected = false;
    }

    /**
     * Hooks into Discord
     */
    public void connect() {
        try {
            client.connect(DiscordBuild.STABLE, DiscordBuild.ANY);
        } catch (NoDiscordClientException e) {
            Main.error = true;
            e.printStackTrace();
        }
    }

    /**
     * @return Whether the hook is active or not
     */
    public boolean isConnected() {
        return connected;
    }
}
