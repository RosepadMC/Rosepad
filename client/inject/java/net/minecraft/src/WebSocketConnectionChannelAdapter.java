package net.minecraft.src;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class WebSocketConnectionChannelAdapter extends ConnectionChannelAdapter {
    private final WebSocketClient ws;
    private final WebSocketInputStream inputStream;
    private final WebSocketOutputStream outputStream;

    public WebSocketConnectionChannelAdapter(URI uri) {
        this.ws = new WebSocketClient(uri) {

            @Override
            public void onOpen(ServerHandshake handshakedata) {
                outputStream.release();
            }

            @Override
            public void onMessage(ByteBuffer bytes) {
                inputStream.append(bytes);
            }

            @Override
            public void onMessage(String message) {
                inputStream.append(ByteBuffer.wrap(message.getBytes()));
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.print("Closed (" + code + "): " + reason);
                try {
                    inputStream.close();
                } catch (IOException ignored) {}
                try {
                    outputStream.close();
                } catch (IOException ignored) {}
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException ignored) {}
                try {
                    outputStream.close();
                } catch (IOException ignored) {}
            }

        };

        inputStream = new WebSocketInputStream(ws);
        outputStream = new WebSocketOutputStream(ws);
    }

    @Override
    public void close() throws IOException {
        ws.close();
    }

    @Override
    public void finalize() throws IOException {
        ws.connect();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return inputStream;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return outputStream;
    }

    @Override
    public String displayHost() {
        return ws.getRemoteSocketAddress().getAddress().getHostAddress() + ":" + ws.getRemoteSocketAddress().getPort();
    }

}
