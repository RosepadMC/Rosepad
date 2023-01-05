package net.minecraft.src;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuiMultiplayerList extends GuiScreen {
    private final File serverListFile = new File(System.getProperty("user.dir") + "/servers.txt");
    private final List<String> serverList = new ArrayList<>();
    private final GuiScreen parent;
    private int mode = 0;
    private int page = 0;
    private boolean lock = false;

    public void addToList(String server) {
        serverList.add(server);
        this.actionPerformed();
        try {
            FileWriter writer = new FileWriter(serverListFile, true);
            writer.append(server).append('\n');
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setServerName(int i, String name) {
        serverList.set(i, (serverList.get(i).split(" ")[0] + " " + name).trim());
        try {
            FileWriter writer = new FileWriter(serverListFile, false);
            for (String server : serverList) {
                writer.append(server).append("\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuiMultiplayerList(GuiScreen parent) {
        this.parent = parent;
        try {
            if (!serverListFile.exists()) {
                if (serverListFile.createNewFile()) doNothing();
            }
            else {
                FileReader reader = new FileReader(serverListFile);
                Scanner scanner = new Scanner(reader);
                while (scanner.hasNextLine()) {
                    String addr = scanner.nextLine().trim();
                    if (!addr.isEmpty()) serverList.add(addr);
                }
                scanner.close();
                reader.close();
            }
        } catch (Exception ignored) {}
    }

    @Override
    public void actionPerformed() {
        this.controlList.clear();

        if (this.mode == 0) {
            this.controlList.add(new GuiButton(0, this.width / 2 - 200, this.height / 8 * 7 - 25, 100, 20, "Remove"));
            this.controlList.add(new GuiButton(1, this.width / 2 - 100, this.height / 8 * 7 - 25, 100, 20, "Add"));
            this.controlList.add(new GuiButton(6, this.width / 2, this.height / 8 * 7 - 25, 100, 20, "Rename"));
            this.controlList.add(new GuiButton(2, this.width / 2 + 100, this.height / 8 * 7 - 25, 100, 20, "Direct connect"));
        }
        this.controlList.add(new GuiButton(3, this.width / 2 - 50, this.height / 8 * 7, 100, 20, "Back"));

        final int maxOnPage = (this.height - 120) / 25;

        if (page > 0) {
            this.controlList.add(new GuiButton(4, 8, this.height / 2, 25, 20, "<<<"));
        }
        if ((page + 1) * maxOnPage + 1 < serverList.size()) {
            this.controlList.add(new GuiButton(5, this.width - 8 - 25, this.height / 2, 25, 20, ">>>"));
        }

        for (int i = page * maxOnPage; i < (page + 1) * maxOnPage; i++) {
            if (serverList.size() <= i) break;
            final String name = serverList.get(i).trim().contains(" ")
                    ? serverList.get(i).substring(serverList.get(i).indexOf(" ")).trim()
                    : serverList.get(i).trim();
            this.controlList.add(new GuiButton(7 + i, this.width / 2 - 100, (i - page * maxOnPage) * 25 + 15 + 40, name));
        }
    }

    @Override
    protected void mouseMoved(int x, int y) {
        lock = false;
        super.mouseMoved(x, y);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                this.mode = 1;
                this.actionPerformed();
                break;
            case 1:
                mc.displayGuiScreen(new GuiAddServer(this));
                break;
            case 2:
                mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case 3:
                if (this.mode > 0) {
                    mode = 0;
                    lock = true;
                    actionPerformed();
                }
                else if (!lock) mc.displayGuiScreen(parent);
                break;
            case 4:
                if (!lock) {
                    page--;
                    lock = true;
                    this.actionPerformed();
                }
                break;
            case 5:
                if (!lock) {
                    page++;
                    lock = true;
                    this.actionPerformed();
                }
                break;
            case 6:
                this.mode = 2;
                this.actionPerformed();
                break;
            default:
                if (this.mode == 1 && !lock) {
                    page = 0;
                    mode = 0;
                    lock = true;
                    serverList.remove(button.id - 7);
                    this.actionPerformed();
                    try {
                        FileWriter writer = new FileWriter(serverListFile, false);
                        for (String server : serverList) {
                            writer.append(server).append("\n");
                        }
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if (this.mode == 2 && !lock) {
                    page = 0;
                    mode = 0;
                    lock = true;
                    this.mc.displayGuiScreen(new GuiRenameServer(this, button.id - 7));
                }
                else if (!lock) {
                    String host = serverList.get(button.id - 7).split(" ")[0];
                    final String[] split = host.split(":");
                    try {
                        ConnectionChannelAdapter adapter;
                        if (split[0].equals("ws") || split[0].equals("wss")) {
                            adapter = new WebSocketConnectionChannelAdapter(new URI(host));
                        }
                        else {
                            adapter = new JSockConnectionChannelAdapter(new Socket(InetAddress.getByName(split[0]), split.length > 1 ? Integer.parseInt(split[1]) : 25565));
                        }
                        this.mc.displayGuiScreen(new GuiConnecting(this.mc, adapter));
                    } catch (URISyntaxException e) {
                        this.mc.displayGuiScreen(new GuiConnectFailed("Failed to connect to the server", "Failed to parse URI"));
                    } catch (NumberFormatException e) {
                        this.mc.displayGuiScreen(new GuiConnectFailed("Failed to connect to the server", "Failed to parse port"));
                    } catch (UnknownHostException e) {
                        this.mc.displayGuiScreen(new GuiConnectFailed("Failed to connect to the server", "Unknown host"));
                    } catch (IOException e) {
                        this.mc.displayGuiScreen(new GuiConnectFailed("Failed to connect to the server", "Unknown error"));
                    }
                }
                break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float renderPartialTick) {
        this.drawDefaultBackground();
        this.drawCenteredString(
                fontRenderer,
                mode == 1
                        ? "Delete server" : mode == 2
                        ? "Rename server"
                        : "Multiplayer",
                this.width / 2,
                30,
                0xFFFFFFFF
        );
        if (serverList.isEmpty()) {
            this.drawCenteredString(fontRenderer, "Empty house", this.width / 2, this.height / 2, 0xFFFFFFFF);
        }
        super.drawScreen(mouseX, mouseY, renderPartialTick);
    }

    private void doNothing() {}
}
