package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class ThreadNetworkKeepAlive extends Thread {
    private final NetworkManager manager;

    public ThreadNetworkKeepAlive(NetworkManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        System.out.println("Started alive keeper");

        while (this.manager.isRunning) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.err.println("How the fuck this happened");
                e.printStackTrace();
                return;
            }

            manager.addToSendQueue(new Packet0KeepAlive());
        }

        System.out.println("Stopped alive keeper");
    }
}
