package net.minecraft.src;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;

import org.java_websocket.WebSocket;

public class WebSocketOutputStream extends OutputStream {
    private final WebSocket ws;
    private List<byte[]> dump = new ArrayList<>();

    public WebSocketOutputStream(WebSocket ws) {
        this.ws = ws;
    }

    public synchronized void release() {
        while (dump.size() > 0) {
            ws.send(dump.remove(0));;
        }
        dump = null;
    }

    @Override
    public void write(int b) throws IOException {
        if (b < 0 || b > 255) return;

        if (dump != null)
            dump.add(new byte[] { (byte) b });
        else ws.send(new byte[] { (byte) b });
    }

    @Override
    public void write(byte[] b) throws IOException {
        if (dump != null) dump.add(b);
        else ws.send(ByteBuffer.wrap(b));
    }

    @Override
    public void write(byte[] b, int offset, int length) throws IOException {
        if (dump != null) dump.add(b);
        else ws.send(ByteBuffer.wrap(b, offset, length));
    }

}
