package edu.jennifer.pluginmanager;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by khalid on 4/19/17.
 */
public class Test {

    public static void main(String[] args) {
        try{
            final String dir = "/Users/khalid/dev/workspaces/ihotel.system/plugincpu/target";
            PluginLoader loader = PluginLoader.getInstance(dir);
            loader.loadPlugins();
            Set<String> pluginNames = loader.listPluginNames();
            for(String name : pluginNames) {
                System.out.println(name);
                Plugin plugin = loader.getPluginByName(name);
                HashMap<String, Object> options = new HashMap<>();
                options.put("threads", 4);
                options.put("duration", 2000);
                plugin.setOptions(options);

                plugin.run();
            }



        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

