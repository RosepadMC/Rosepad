package net.minecraft.src;

import java.io.*;

public abstract class ConnectionChannelAdapter {
    public abstract void close() throws IOException;
    public abstract void finalize() throws IOException;

    public abstract InputStream getInputStream() throws IOException;
    public abstract OutputStream getOutputStream() throws IOException;

    public abstract String displayHost();
}
