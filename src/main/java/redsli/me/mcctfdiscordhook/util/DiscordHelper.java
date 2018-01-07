package redsli.me.mcctfdiscordhook.util;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.DiscordBuild;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;

/**
 * Created by redslime on 07.01.2018
 */
public class DiscordHelper {

    private IPCClient client;
    private boolean connected;

    public DiscordHelper(long discordAppId) {
        client = new IPCClient(discordAppId);
        try {
            client.setListener(new IPCListener() {
                @Override
                public void onPacketSent(IPCClient client, Packet packet) {
                    System.out.println("Packet sent: " + packet);
                }

                @Override
                public void onPacketReceived(IPCClient client, Packet packet) {
                    System.out.println("Packed received: " + packet);
                }

                @Override
                public void onReady(IPCClient client) {
                    connected = true;
                }
            });
            client.connect(DiscordBuild.STABLE, DiscordBuild.ANY);
        } catch (NoDiscordClientException e) {
            e.printStackTrace();
        }
    }

    public void setPresence(RichPresence richPresence) {
        client.sendRichPresence(richPresence);
    }

    public void close() {
        client.close();
        connected = false;
    }

    public void connect() {
        try {
            client.connect(DiscordBuild.STABLE, DiscordBuild.ANY);
        } catch (NoDiscordClientException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return connected;
    }
}
