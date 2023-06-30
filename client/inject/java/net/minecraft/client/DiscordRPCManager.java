package net.minecraft.client;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.buj.rml.Game;
import net.buj.rml.annotations.Nullable;

import java.util.Random;

public class DiscordRPCManager {
    private DiscordRPCManager() {}

    private static long timestamp = 0;
    private static boolean up = false;
    private static @Nullable String joinSecret = null;
    private static @Nullable String spectateSecret = null;

    private static String secret() {
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 64; i++) {
            switch (random.nextInt(3)) {
                case 0:
                    sb.append((char) (48 + random.nextInt(10)));
                    break;
                case 1:
                    sb.append((char) (65 + random.nextInt(25)));
                    break;
                case 2:
                    sb.append((char) (97 + random.nextInt(25)));
                    break;
            }
        }

        return sb.toString();
    }

    public static void start() {
        if (up) return;
        up = true;
        System.out.println("[DRPC] Starting Discord RPC...");
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder()
                .setReadyEventHandler((user) -> {
                    System.out.println("[DRPC] Logged in as " + user.username + "#" + user.discriminator);
                    updatePresence(false);
                })
                .setErroredEventHandler((e, message) -> {
                    System.out.println("[DRPC] Failed to start DiscordRPC: " + message + " (error code " + e + ")");
                    up = false;
                    Minecraft.INSTANCE.gameSettings.enableDiscordRPC = false;
                })
                .setDisconnectedEventHandler((e, message) -> {
                    System.out.println("[DRPC] Disconnected from DiscordRPC: " + message + " (error code " + e + ")");
                    up = false;
                    Minecraft.INSTANCE.gameSettings.enableDiscordRPC = false;
                })
                .build();
        DiscordRPC.discordInitialize(
                "678178584439029760",
                handlers,
                true
        );
    }

    public static void updatePresence(boolean changeWorld) {
        if (changeWorld) {
            timestamp = System.currentTimeMillis() / 1000;
            if (Minecraft.INSTANCE.theWorld == null) {
                joinSecret = null;
                spectateSecret = null;
            }
            else {
                joinSecret = secret();
                spectateSecret = secret();
            }
        }
        if (!up) return;
        System.out.println("[DRPC] Updating...");
        String state = "Idle";
        String smallIcon = "idle";
        String smallIconInfo = "Idle";
        if (Minecraft.INSTANCE.theWorld != null && Minecraft.INSTANCE.thePlayer != null) {
            if (Minecraft.INSTANCE.theWorld.multiplayerWorld) {
                if (Minecraft.INSTANCE.theWorld.rosepadContentEnabled()) {
                    smallIcon = "multiplayer";
                    smallIconInfo = "Rosepad server";
                    state = "Playing multiplayer";
                }
                else {
                    smallIcon = "multiplayer";
                    smallIconInfo = "Vanilla server";
                    state = "Playing on vanilla server";
                }
            }
            else {
                smallIcon = "singleplayer";
                smallIconInfo = "Milestone " + Minecraft.INSTANCE.theWorld.milestone;
                state = "Playing on local world";
            }
        }
        DiscordRichPresence.Builder rpc = new DiscordRichPresence.Builder(state)
                .setBigImage("rosepad", Game.MINECRAFT.getVersionString())
                .setSmallImage(smallIcon, smallIconInfo);
        if (!state.equals("Idle")) {
            rpc.setStartTimestamps(timestamp);
        }
        {
            String joinSecret = DiscordRPCManager.joinSecret == null ? "" : DiscordRPCManager.joinSecret;
            String spectateSecret = DiscordRPCManager.spectateSecret == null ? "" : DiscordRPCManager.spectateSecret;
            rpc.setSecrets(joinSecret, spectateSecret);
        }
        DiscordRPC.discordUpdatePresence(rpc.build());
    }

    public static void stop() {
        if (!up) return;
        up = false;
        System.out.println("[DRPC] Stopping Discord RPC...");
        DiscordRPC.discordShutdown();
    }
}
