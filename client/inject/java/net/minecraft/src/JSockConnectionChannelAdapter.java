package net.minecraft.src;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class JSockConnectionChannelAdapter extends ConnectionChannelAdapter {
    public final Socket socket;

    public JSockConnectionChannelAdapter(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }

    @Override
    public void finalize() throws IOException {
        socket.setTrafficClass(24);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    @Override
    public String displayHost() {
        return socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
    }
}
