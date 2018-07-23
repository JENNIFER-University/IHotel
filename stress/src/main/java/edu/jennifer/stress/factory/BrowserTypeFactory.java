package edu.jennifer.stress.factory;

import edu.jennifer.stress.model.BrowserFamily;
import edu.jennifer.stress.model.BrowserType;
import edu.jennifer.stress.util.AppUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Generate Random User agent (HttpClient Type)
 * @author Khalid
 * @Created 7/23/18 9:53 AM.
 */
public class BrowserTypeFactory {

    private static final Random RANDOM = new Random();
    private static final List<BrowserFamily> VALUES = Collections.unmodifiableList(Arrays.asList(BrowserFamily.values()));

    public static BrowserType createBrowserType() {
        BrowserFamily browserFamily = VALUES.get(RANDOM.nextInt(VALUES.size()));

        int version;
        String os;
        switch (browserFamily) {
            case IE:
                version = AppUtil.getRandom(BrowserType.IE_MIN, BrowserType.IE_MAX);
                os = BrowserType.OS.get(AppUtil.getRandom(0, BrowserType.OS.size() -1 ));
                return BrowserType.createIE(version, os);
            case FireFox:
                version = AppUtil.getRandom(BrowserType.CHROME_FIREFOX_MIN, BrowserType.CHROME_FIREFOX_MAX);
                os = BrowserType.OS.get(AppUtil.getRandom(0, BrowserType.OS.size() -1 ));
                return BrowserType.createFirefox(version+".0", os);
            case Chrome:
                version = AppUtil.getRandom(BrowserType.CHROME_FIREFOX_MIN, BrowserType.CHROME_FIREFOX_MAX);
                os = BrowserType.OS.get(AppUtil.getRandom(0, BrowserType.OS.size() -1 ));
                return BrowserType.createChrome(version+".0", os);
            case Safari:
                version = AppUtil.getRandom(BrowserType.SAFAIR_MIN, BrowserType.SAFAIR_MAX);
                os = BrowserType.OS.get(AppUtil.getRandom(6, BrowserType.OS.size() -1 ));
                return BrowserType.createSafari(version+".0", os);

        }

        return null;
    }

}
