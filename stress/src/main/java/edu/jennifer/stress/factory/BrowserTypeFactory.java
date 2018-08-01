package edu.jennifer.stress.factory;

import edu.jennifer.common.AppUtil;
import edu.jennifer.stress.model.BrowserFamily;
import edu.jennifer.stress.model.BrowserType;
import edu.jennifer.stress.model.MobileFamily;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Generate Random User agent (HttpClient Type)
 * @author Khalid
 * @Created 7/23/18 9:53 AM.
 */
public class BrowserTypeFactory {

    private static final List<BrowserFamily> VALUES = Collections.unmodifiableList(Arrays.asList(BrowserFamily.values()));
    private static final List<MobileFamily> MOBILE_VALUES = Collections.unmodifiableList(Arrays.asList(MobileFamily.values()));

    public static BrowserType createBrowserType() {
        BrowserFamily browserFamily = VALUES.get(AppUtil.getRandomBounded(VALUES.size()));

        int version;
        String os;
        switch (browserFamily) {
            case IE:
                version = AppUtil.getRandom(BrowserType.IE_MIN, BrowserType.IE_MAX);
                os = BrowserType.OS.get(AppUtil.getRandomBounded(BrowserType.OS.size()));
                return BrowserType.createIE(version, os);
            case FireFox:
                version = AppUtil.getRandom(BrowserType.CHROME_FIREFOX_MIN, BrowserType.CHROME_FIREFOX_MAX);
                os = BrowserType.OS.get(AppUtil.getRandomBounded(BrowserType.OS.size()));
                return BrowserType.createFirefox(version+".0", os);
            case Chrome:
                version = AppUtil.getRandom(BrowserType.CHROME_FIREFOX_MIN, BrowserType.CHROME_FIREFOX_MAX);
                os = BrowserType.OS.get(AppUtil.getRandomBounded(BrowserType.OS.size()));
                return BrowserType.createChrome(version+".0", os);
            case Safari:
                version = AppUtil.getRandom(BrowserType.SAFAIR_MIN, BrowserType.SAFAIR_MAX);
                os = BrowserType.OS.get(AppUtil.getRandomBounded(BrowserType.OS.size()));
                return BrowserType.createSafari(version+".0", os);
            case Mobile:
                return createMobileDevice();

        }

        return null;
    }

    private static BrowserType createMobileDevice() {
        MobileFamily mobileFamily = MOBILE_VALUES.get(AppUtil.getRandomBounded(MOBILE_VALUES.size()));

        int version;
        String os;

        switch (mobileFamily){
            case IPhone:
                version = AppUtil.getRandom(BrowserType.SAFAIR_MIN, BrowserType.SAFAIR_MAX);
                os = BrowserType.IOS_VRTSION[AppUtil.getRandomBounded(BrowserType.IOS_VRTSION.length)];
                return BrowserType.createIPhoneSafari(version+".0", os);
            case IPad:
                version = AppUtil.getRandom(BrowserType.SAFAIR_MIN, BrowserType.SAFAIR_MAX);
                os = BrowserType.IOS_VRTSION[AppUtil.getRandomBounded(BrowserType.IOS_VRTSION.length)];
                return BrowserType.createIpadSafari(version+".0", os);
            case Android:
                version = AppUtil.getRandom(BrowserType.CHROME_FIREFOX_MIN, BrowserType.CHROME_FIREFOX_MAX);
                os = BrowserType.ANDROID_VERSIONS[AppUtil.getRandomBounded(BrowserType.ANDROID_VERSIONS.length)];
                return BrowserType.createIpadSafari(version+".0", os);
            case Windows_Phone:
                version = AppUtil.getRandom(BrowserType.IE_MIN, BrowserType.IE_MAX);
                os = BrowserType.WINDOWS_PHONE_VERSIONS[AppUtil.getRandomBounded(BrowserType.WINDOWS_PHONE_VERSIONS.length)];
                return BrowserType.createMobileIE(version+"", os);

        }

        return null;
    }

}
