package net.minecraft.src;

public class ThreadBandwidthCounter extends Thread {
    private static long writtenBytes = 0;
    private static long readBytes = 0;

    private static long writtenBytesSecond = 0;
    private static long readBytesSecond = 0;

    public static long getWBPS() {
        return writtenBytesSecond;
    }
    public static long getRBPS() {
        return readBytesSecond;
    }

    public static void addWrite(long value) {
        writtenBytes += value;
    }
    public static void addRead(long value) {
        readBytes += value;
    }

    private static boolean running = false;

    @Override
    public void run() {
        try {
            while (running) {
                Thread.sleep(1000);
                writtenBytesSecond = writtenBytes;
                writtenBytes = 0;
                readBytesSecond = readBytes;
                readBytes = 0;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void spawn() {
        if (running) return;
        running = true;
        Thread thread = new ThreadBandwidthCounter();
        thread.start();
    }
}
