package com.ravikiran.collegemagement;

public class Config
{

    /**** All Shared Preferences Keys ****
     allCities
     allCommunities
     SelectedHierarchyId
     SelectedCommunityId
     ***/

    // Logging String
    public static final String TAG = "taggy";

    // Network Strings
    public static final String NETWORK_ONLINE = "online";
    public static final String NETWORK_OFFLINE = "offline";
    public static final String REFRESH_COUNT = "refresh";

    // ** Production ***
    // public static final String API_URL = "https://master.bintix.com/api/v2/agent/";

    // Development
    // public static final String API_URL = "https://devmaster.bintix.com/api/v2/agent/";

    // Staging
    // public static final String API_URL = "https://stagemaster.bintix.com/api/v2/agent/";

    // Test
    public static final String API_URL = "https://testmaster.bintix.com/api/v2/agent/";

    // UAT
    // public static final String API_URL = "https://uatmaster.bintix.com/api/v2/agent/";

    //SharedPreference Name
    public static final String SHARED_PREF = "bintixSharedPref";
}
