package plugin.login;

import edu.jennifer.pluginmanager.Plugin;

import java.util.HashMap;

/**
 * Created by khalid on 4/20/17.
 */
public class ProfileLoader implements Plugin {

    @Override
    public void setOptions(HashMap<String, Object> options) {

    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getName() {
        return "Slow Login";
    }

    @Override
    public void run() {
        System.out.println(getName() + " is running");
    }
}
