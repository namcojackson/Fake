/*
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3000.constant;

/**
 *<pre>
 *  Serial Number Entry Constant values
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/03/2012   Fujitsu         T.Ishii         Create          N/A
 * 07/26/2013   Fujitsu         M.Nakamura      Update          R-OM031
 * 11/28/2015   CSAI            Y.Imazu         Update          CSA
 * 07/15/2016   CSAI            Y.Imazu         Update          QC#7334
 *</pre>
 */
public interface NLBL3000Constant {

    /**
     * Business ID
     */
    String BIZ_ID = "NLBL3000";

    /**
     * Process ended normally.
     */
    String ZZM8100I = "ZZM8100I";

    /**
     * Please execute again after correcting the error field.
     */
    String ZZM9037E = "ZZM9037E";

    /**
     * No search results found.
     */
    String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    String NZZM0001W = "NZZM0001W";

    /**
     * '@ is duplicated.
     */
    String NLBM1265E = "NLBM1265E";

    /**
     * '@1 does not exist in '@2.
     */
    String NLBM1002E = "NLBM1002E";

    // 07/26/2013 R-OM031 Add Start
    /**
     * NLBM1294W : You can not register the @ @ more than. Please
     * reduce the @.
     */
    String NLBM1294W = "NLBM1294W";

    /* 11/28/2015 CSAI Y.Imazu Add CSA START */
    /** The entered Serial Number is already allocated by other order. */
    String NLZM2317E = "NLZM2317E";

    /** The entered Serial Number is located at customer site. */
    String NLZM2318E = "NLZM2318E";

    /**
     * The entered Serial Number does not include in the specified
     * configuration.
     */
    String NLZM2319E = "NLZM2319E";

    /** Effective Asset does not exist. */
    String NLBM1290E = "NLBM1290E";

    /** The Serial # specified does not exist in this WH. */
    String NLBM1299W = "NLBM1299W";

    /** The Serial # specified exists in other location. */
    String NLBM1337W = "NLBM1337W";

    /** The specified "Warehouse Code " does not exist in the Master. */
    String NLBM1334E = "NLBM1334E";

    /**
     * The specified serial number is the component of the other
     * configuration.
     */
    String NLZM2324E = "NLZM2324E";

    /** @ has not specified. Is this OK? */
    String NLBM1300W = "NLBM1300W";

    /** The Serial # is out range from "@" to "@". */
    String NLBM1338W = "NLBM1338W";

    /** Screen Edit Type : Edit */
    String SCR_EDT_TP_EDIT = "1";
    /* 11/28/2015 CSAI Y.Imazu Add CSA END */

    /* 07/15/2016 CSAI Y.Imazu Add QC#7334 START */
    /**
     * The specified Serial# already exists as different item. Please
     * check the Serial#.
     */
    String NLZM2450E = "NLZM2450E";

    /** This Serial Number was already shipped in same Shipping Order. */
    String NLBM1353E = "NLBM1353E";

    /**
     * This Serial Number was already received in same Receiving
     * Worksheet.
     */
    String NLAM1338E = "NLAM1338E";
    /* 07/15/2016 CSAI Y.Imazu Add QC#7334 END */

    /**
     * Serial #(for Warning Message)
     */
    String MSG_SER_NUM = "Serial#";

    /**
     * Quantity (for Warning Message)
     */
    String MSG_QTY = "Quantity";
    // 07/26/2013 R-OM031 Add End
}
