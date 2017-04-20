package edu.jennifer.pluginmanager;

import java.util.HashMap;

/**
 * Created by khalid on 4/19/17.
 */
public interface Plugin {

    String getName();

    String getVersion();

    void setOptions(HashMap<String, Object> options);

    void run();
}
