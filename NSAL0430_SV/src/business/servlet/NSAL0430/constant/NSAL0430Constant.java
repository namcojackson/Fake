package business.servlet.NSAL0430.constant;

import java.math.BigDecimal;

public class NSAL0430Constant {

    /**
     * Business Id
     */
    public static final String BIZ_ID = "NSAL0430";

    /**
     * Screen Id
     */
    public static final String SCR_ID_00 = "NSAL0430Scrn00";

    /**
     * Function Code: Inquiry
     */
    public static final String EZD_FUNC_CD_INQ = "20";

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /**
     * Invalid SVC_PHYS_MTR_READ_GRP_SQ
     */
    public static final BigDecimal INVLD_SVC_PHYS_MTR_READ_GRP_SQ = BigDecimal.ONE.negate();
}
