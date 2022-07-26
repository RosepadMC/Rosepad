package net.buj.rml;

public abstract class RosepadMod {
    public abstract String getName();
    public abstract int[] getVersion();
    public abstract String getVersionTag();

    protected boolean stringIsNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int a : getVersion()) {
            if (builder.length() > 0) builder.append(".");
            builder.append(a);
        }

        String tag;
        if (!stringIsNullOrEmpty(tag = getVersionTag())) {
            builder.append("-").append(tag);
        }

        return builder.toString();
    }
}
