package net.buj.rml;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class RosepadModLoader {
    public final List<RosepadMod> mods = new ArrayList<>();

    private void loadModsFromFile(File file) {

    }

    public void load(Environment env) {
        mods.clear();

        try {
            Enumeration<URL> roots = ClassLoader.getSystemResources("");
            while (roots.hasMoreElements()) {
                File root = new File(roots.nextElement().getPath());


            }
        } catch (Exception e) {
            System.err.println("Failed to load mods");
            e.printStackTrace();
        }
    }
}
