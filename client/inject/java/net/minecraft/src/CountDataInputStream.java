package net.minecraft.src;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CountDataInputStream extends DataInputStream {
    /**
     * Creates a DataInputStream that uses the specified
     * underlying InputStream.
     *
     * @param in the specified input stream
     */
    public CountDataInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        ThreadBandwidthCounter.addRead(1);
        return super.read();
    }
}
