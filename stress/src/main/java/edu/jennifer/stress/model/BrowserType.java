package edu.jennifer.stress.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Khalid
 * @Created 7/23/18 9:32 AM.
 */
public class BrowserType {

    public final static List<String> OS = new ArrayList<>();

    public static final int IE_MIN = 6;
    public static final int IE_MAX = 10;

    public static final int SAFAIR_MIN = 4;
    public static final int SAFAIR_MAX = 10;

    public static final int CHROME_FIREFOX_MIN = 36;
    public static final int CHROME_FIREFOX_MAX = 62;

    public static final String[] WINDOWS_PHONE_VERSIONS = {"7.0","7.5","7.8","8.0","8.1"};
    public static final String[] ANDROID_VERSIONS = {"4.0","4.1","5.0","6.0","7.0","8.0"};
    public static final String[] IOS_VRTSION = {"4_0","6_0","7_0_2","8_0_2","9_0_2","10_0_2"};

    static {
        OS.add("Windows NT 5.1");
        OS.add("Windows NT 6.0");
        OS.add("Windows NT 6.1");
        OS.add("Windows NT 6.2");
        OS.add("Windows NT 6.3");
        OS.add("Windows NT 10.0");

        OS.add("Mac OS X 10_6");
        OS.add("Mac OS X 10_7");
        OS.add("Mac OS X 10_8");
        OS.add("Mac OS X 10_9");
        OS.add("Mac OS X 10_10");
        OS.add("Mac OS X 10_11");
        OS.add("Mac OS X 10_12");
    }


    /**
     * Create Internet Explorer user agent
     *
     * @param version IE Version
     * @param os      Operation System
     * @return BrowserType
     */
    public static BrowserType createIE(int version, String os) {
        String userAgent = String.format("Mozilla/4.0 (compatible; MSIE %d.0; %s; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0; .NET CLR 3.0.04506; InfoPath.2)", version, os);
        return new BrowserType(userAgent);
    }

    /**
     * Create FireFox User Agent
     *
     * @param version Firefox version
     * @param os      Operating system
     * @return BroswerType
     */
    public static BrowserType createFirefox(String version, String os) {
        String userAgent = String.format("Mozilla/5.0 (%s; WOW64; rv:%s) Gecko/20110303 Firefox/%s", os, version, version);
        return new BrowserType(userAgent);
    }

    /**
     * Create Google Chrome
     * @param version Chrome Version
     * @param os Os Version
     * @return
     */
    public static BrowserType createChrome(String version, String os) {
        String userAgent = String.format("Mozilla/5.0 (%s) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/%s Safari/537.36", os, version);
        return new BrowserType(userAgent);
    }

    /**
     * Create Safari
     * @param os
     * @param version
     * @return
     */
    public static BrowserType createSafari(String os, String version) {
        String userAgent = String.format("Mozilla/5.0 (Macintosh; Intel %s) AppleWebKit/537.71 (KHTML, like Gecko) Version/%s Safari/537.71", os, version);
        return new BrowserType(userAgent);
    }


    /**
     * IE on Windows Phone
     * @param version
     * @param phoneVersion
     * @return
     */
    public static BrowserType createMobileIE(String version, String phoneVersion){
        String userAgent = String.format("Mozilla/5.0 (compatible; MSIE %s; Windows Phone %s; Trident/7.0; ARM; Touch; IEMobile/%s)", version, phoneVersion, version);
        return new BrowserType(userAgent);
    }

    /**
     * Android Device
     * @param version
     * @param androidVersion
     * @return
     */
    public static BrowserType createMobileAndroid(String version, String androidVersion){
        String userAgent = String.format("Mozilla/5.0 (Linux; U; Android %s; en-us) AppleWebKit/533.1 (KHTML, like Gecko) Version/%s Mobile Safari/533.1", androidVersion, version);
        return new BrowserType(userAgent);
    }


    /**
     * Iphone Safari
     * @param version
     * @param iosVersion
     * @return
     */
    public static BrowserType createIPhoneSafari(String version, String iosVersion){
        String userAgent = String.format("Mozilla/5.0 (iPhone; CPU iPhone OS %s like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/%s Safari/600.1.4", iosVersion, version);
        return new BrowserType(userAgent);
    }

    /**
     * Ipad Safari
     * @param version
     * @param iosVersion
     * @return
     */
    public static BrowserType createIpadSafari(String version, String iosVersion){
        String userAgent = String.format("Mozilla/5.0 (iPad; U; CPU OS %s like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/%s.0.2 Mobile/8C148 Safari/6533.18.5", iosVersion, version);
        return new BrowserType(userAgent);
    }


    private String userAgent;

    public BrowserType(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUserAgent() {
        return userAgent;
    }

}


