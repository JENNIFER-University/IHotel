package edu.jennifer.pluginmanager;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by khalid on 4/19/17.
 */
public class PluginLoader {


    /**
     * Plugin Configuration file name
     */
    private final String CONFIG_NAME = "plugin.conf";

    /**
     * Main Class key
     */
    private final String KEY_MAIN_CLASS = "main_class";

    /**
     * Instnace of the loader
     */
    private static PluginLoader instance;

    /**
     * Plugin directory must be passed when getting instance of this class
     */
    private String pluginsDir;

    /**
     * HashMap to hold the plugin. The key is the plugin name
     */
    private HashMap<String, Plugin> plugins;

    /**
     * Get Instance
     * @param pluginDir plugin dir
     * @return
     */
    public static PluginLoader getInstance(String pluginDir){
        if(pluginDir == null) {
            throw new RuntimeException("Plugin Dir was not specified");
        }

        if(instance == null) {
            instance = new PluginLoader(pluginDir);
        }
        return  instance;
    }

    private PluginLoader(String pluginsDir){
        this.pluginsDir = pluginsDir;
        plugins = new HashMap<>();
    }


    /**
     * Load the plugin
     */
    public void loadPlugins(){
        try{
            File pluginDir = new File(this.pluginsDir);
            File[] pluginsFiles = pluginDir.listFiles();
            if(pluginsFiles.length == 0) {
                System.out.println("No Plugin found");
                return;
            }

            for(File file : pluginsFiles) {
                if(file.isDirectory() || !file.getName().endsWith(".jar"))
                    continue;

                Class<?> aClass = loadClass(file);
                Plugin plugin = initPlugin(aClass);
                plugins.put(plugin.getName(), plugin);
            }
        }catch (Exception ex){
            System.out.println("Failed to load plugin");
        }
    }

    /**
     * Loading the plugin
     * @param pluginJar Plugin Jar File
     * @return Plugin Class
     */
    private Class<?> loadClass(File pluginJar){
        try{
            JarFile jarFile = new JarFile(pluginJar);
            JarEntry jarEntry = jarFile.getJarEntry(CONFIG_NAME);
            Properties properties = new Properties();
            properties.load(jarFile.getInputStream(jarEntry));

            URL[] urls = new URL[] { pluginJar.toURI().toURL() };
            ClassLoader loader = new URLClassLoader(urls);
            return loader.loadClass(properties.getProperty(KEY_MAIN_CLASS));

        }catch (Exception ex){
            System.out.println("Failed to load a plugin");
            ex.printStackTrace();
        }
        return  null;
    }


    /**
     * Initializing the plugin
     * @param clazz Plugin Class
     * @return Plugin Class
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private Plugin initPlugin(Class<?> clazz) throws IllegalAccessException, InstantiationException{
        return (Plugin) clazz.newInstance();
    }

    /**
     * Get All the plugins
     * @return
     */
    public HashMap<String, Plugin> getPlugins() {
        return plugins;
    }

    /**
     * Get a plugin by name
     * @param name the plugin name
     * @return Plugin object
     */
    public Plugin getPluginByName(String name) {
        return plugins.get(name);
    }

    /**
     * Get All plugin names
     * @return Plugin names as Set
     */
    public Set<String> listPluginNames(){
        return getPlugins().keySet();
    }

    /**
     * Check a plugin by name
     * @param name
     * @return
     */
    public boolean hasPlugin(String name){
        return plugins.containsKey(name);
    }
}
