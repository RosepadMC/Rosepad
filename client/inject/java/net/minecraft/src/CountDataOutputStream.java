package net.minecraft.src;

import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CountDataOutputStream extends DataOutputStream {
    /**
     * Creates a new data output stream to write data to the specified
     * underlying output stream. The counter <code>written</code> is
     * set to zero.
     *
     * @param out the underlying output stream, to be saved for later
     *            use.
     * @see FilterOutputStream#out
     */
    public CountDataOutputStream(OutputStream out) {
        super(out);
    }

    @Override
    public synchronized void write(int b) throws IOException {
        ThreadBandwidthCounter.addWrite(1);
        super.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        if (b.length > 0) ThreadBandwidthCounter.addWrite(b.length);
        super.write(b);
    }

    @Override
    public synchronized void write(byte[] b, int off, int len) throws IOException {
        ThreadBandwidthCounter.addWrite(len);
        super.write(b, off, len);
    }
}
