package net.minecraft.src;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.java_websocket.WebSocket;

public class WebSocketInputStream extends InputStream {
    private final WebSocket socket;
    private final List<ByteBuffer> buffers = new ArrayList<>();

    public WebSocketInputStream(WebSocket socket) {
        this.socket = socket;
    }

    public synchronized void append(ByteBuffer buffer) {
        System.out.println("Added " + buffer.remaining() + " bytes");
        if (!buffer.hasRemaining()) return;
        buffers.add(buffer);
    }

    private synchronized int nextByte() throws IOException {
        if (buffers.size() == 0) throw new IOException("Cannot read next of null");
        ByteBuffer buffer = buffers.get(0);
        byte b = buffer.get();
        if (!buffer.hasRemaining()) buffers.remove(0);
        System.out.println(128 + (int) b);
        return 128 + (int) b;
    }

    @Override
    public int read() throws IOException {
        while (!socket.isClosed()) {
            if (buffers.size() > 0) {
                return nextByte();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {}
        }

        return -1;
    }

    @Override
    public int read(byte[] b) throws IOException {
        if (b == null) throw new NullPointerException();
        if (b.length == 0) return 0;

        int len = b.length - 1;
        int read = 0;

        while (!socket.isClosed()) {
            if (buffers.size() > 0) {
                ByteBuffer buf = buffers.get(0);
                int leftToRead = len - read;
                int add = buf.remaining();

                if (add > leftToRead) {
                    System.arraycopy(buf.array(), 0, b, read, add);
                    read += add;
                }
                else if (add == leftToRead) {
                    System.arraycopy(buf.array(), 0, b, read, add);
                    break;
                }
                else {
                    byte[] arr2 = buf.array();
                    System.arraycopy(arr2, 0, b, read, leftToRead);
                    break;
                }

                if (read >= len) break;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {}
        }

        if (read == 0) return -1;
        return read;
    }
}
