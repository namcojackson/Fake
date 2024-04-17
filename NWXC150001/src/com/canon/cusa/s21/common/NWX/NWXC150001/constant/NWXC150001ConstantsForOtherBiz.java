/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC150001.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/08   Fujitsu         S.Takami        Create          S21#NA9078
 * 2016/08/04   Fujitsu         R.Nakamura      Update          S21#NA9078
 * </pre>
 */
public class NWXC150001ConstantsForOtherBiz {
    /** NMAL6760 Calling Mode: Status Active */
    public static final String NMAL6760_STATUS_CD_ACTIVE = "01";
    /** NMAL6760 Calling Mode: Bill To */
    public static final String NMAL6760_DISP_HRCH_ACCT_CD_BILL = "02";
    /** NMAL6760 Calling Mode: Ship To */
    public static final String NMAL6760_DISP_HRCH_ACCT_CD_SHIP = "03";

    // Add Start 2016/08/04 QC#9078
    /** NSAL1240 MODE_CD : 02 */
    public static final String NSAL1240_MODE_02 = "02";

    /** NWAL1600 Mode Type : Reference. */
    public static final String NWAL1600_MODE_REFERENCE = "0";

    /** NWAL1660 MODE_REFERENCE = "10" */
    public static final String NWAL1660_MODE_REFERENCE = "10";
    // Add End 2016/08/04 QC#9078
}
