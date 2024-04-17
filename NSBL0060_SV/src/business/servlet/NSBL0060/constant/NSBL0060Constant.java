/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0060.constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/20/2013   Hitachi         T.Aoyagi        Create          N/A
 *</pre>
 */
public final class NSBL0060Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSBL0060";

    /**
     * BUSINESS_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * Search button
     */
    public static final List<String> SEARCH = createImmutableList("Search", "Search", "Search");

    /**
     * Common button 8
     */
    public static final List<String> CLEAR = createImmutableList("btn8", "CMN_Clear", "Clear");

    /**
     * Common button 10
     */
    public static final List<String> CLOSE = createImmutableList("btn10", "CMN_Close", "Close");

    /**
     * Button is active
     */
    public static final int BTN_ACTIVE = 1;

    /**
     * Button is inacitve
     */
    public static final int BTN_INACTIVE = 0;

    private static List<String> createImmutableList(String... elements) {

        List<String> tmp = new ArrayList<String>();
        for (String element : elements) {
            tmp.add(element);
        }
        return Collections.unmodifiableList(tmp);
    }
}
