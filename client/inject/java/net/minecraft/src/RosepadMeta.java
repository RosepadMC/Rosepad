package net.minecraft.src;

public class RosepadMeta {
    public static final long USES_USER_SCRIPTS = 1;

    public RosepadMeta(String brandName, int[] version, String versionTag, long flags) {
        this.brandName = brandName;
        this.version = version;
        this.versionTag = versionTag;
        this.flags = flags;
    }

    private final String brandName;
    private final int[] version;
    private final String versionTag;
    private final long flags;

    public String getBrandName() {
        return brandName;
    }

    public int[] getVersion() {
        return version.clone();
    }

    public String getVersionTag() {
        return versionTag;
    }

    public long getFlags() {
        return flags;
    }

    public String getVersionString() {
        StringBuilder str = new StringBuilder();
        for (int v : getVersion()) {
            if (str.length() > 0) str.append(".");
            str.append(v);
        }
        String tag;
        if ((tag = getVersionTag()).length() > 0) {
            str.append("-").append(tag);
        }
        return str.toString();
    }
}
