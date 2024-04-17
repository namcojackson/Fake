/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1510.constant;

/**
 *<pre>
 * D&I/Contact/Site Survey Constant
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/27   Fujitsu         S.Ohki          Create          N/A
 * 2016/06/06   Fujitsu         S.Ohki          Update          S21_NA#7088
 * 2017/02/14   Fujitsu         M.Ohno          Update          S21_NA#17500
 * 2017/08/15   Fujitsu         N.Sugiura       Update          S21_NA#16452
 * 2018/01/10   Fujitsu         K.Ishizuka      Update          S21_NA#18460
 * 2018/07/18   Fujitsu         K.Ishizuka      Update          S21_NA#26188
 * 2019/11/01   Fujitsu         Mz.Takahashi    Update          QC#53993
 * 2019/11/22   Fujitsu         C.Hara          Update          QC#54213
 * 2019/12/26   Fujitsu         S.Kosaka        Update          QC#54725
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 *</pre>
 */
public class NWAL1510Constant {

    /** Business ID -- NWAL1510 */
    public static final String BUSINESS_ID = "NWAL1510";

    /** Screen ID. */
    public static final String SCREEN_ID = "NWAL1510Scrn00";

    /** Message kind Error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** message name order number */
    public static final String MESSAGE_NAME_ORDER_NUM = "Order Number";

    /** Pull down Delimiter */
    public static final String PULLDOWN_DELIMITER = ":";

    /** AM/PM Pulldown Code */
    public static final String[] AM_PM_PULLDOWN_CD = {"0", "1" };

    /** AM/PM Pulldown Name */
    public static final String[] AM_PM_PULLDOWN_NM = {"AM", "PM" };

    /** NEW */
    public static final String MODE_NEW = "01";

    /** MODIFY */
    public static final String MODE_MODIFY = "02";

    /** DELETE */
    public static final String MODE_DELETE = "03";

    /** Delivery Type Code */
    public static final String DELY_TP_CD_INSTALL = "01";

    /** Contact Point Type Code (Phone) */
    public static final String CTAC_PT_TP_CD_PHONE = "02";

    /** Contact Point Type Code (EMail) */
    public static final String CTAC_PT_TP_CD_MAIL = "04";

    /** Contact Point Type Code (Fax) */
    public static final String CTAC_PT_TP_CD_FAX = "05";

    /** Tab EnableFlg Off */
    public static final String TAB_ENABLE_FLG_OFF = "N";

    /** Tab EnableFlg On */
    public static final String TAB_ENABLE_FLG_ON = "Y";

    /** AM */
    public static final String AM_CD = "0";

    // 2016/06/06 S21_NA#7088 Add Start
    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
    // 2016/06/06 S21_NA#7088 Add End

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** This data has been updated by another user.. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** The error was returned by business API. */
    public static final String NWAM0269E = "NWAM0269E";

    /** The entered @ does not exist in Master. */
    public static final String NWAM0181E = "NWAM0181E";

    /**
     * No Order information linked to the entered 'Order Number'
     * exists.
     */
    public static final String NWAM0142E = "NWAM0142E";

    /** The search returned no results. */
    public static final String NWZM0819E = "NWZM0819E";

    /**
     * There are too many search results. Please review search
     * criteria.
     */
    public static final String NWZM0775E = "NWZM0775E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] Search results for does not exist. */
    public static final String NWAM0722E = "NWAM0722E";

    // 2017/02/14 S21_NA#17500 Add Start
    /** Too many search results for "Material Weights and Dimensions Information" , please select config number. */
    public static final String NWAM0931W = "NWAM0931W";
    // 2017/02/14 S21_NA#17500 Add Start
    // 2017/08/15 S21_NA#16452 Add Start
    /** Please enter telephone number. */
    public static final String NMZM0347E = "NMZM0347E";

    /** Please enter Email address. */
    public static final String NMZM0348E = "NMZM0348E";

    /** Please enter fax number. */
    public static final String NMZM0349E = "NMZM0349E";
    // 2017/08/15 S21_NA#16452 Add End

    /** Button Display Flag */
    public static final String BUTTON_DISP_FLG_ON = "Y";

    /** Site Survey Material Weights and Dimensions Information */
    public static final String WEIGHTS_AND_DIMENSIONS_INFO_NAME = "Material Weights and Dimensions Information";

    /** Quantity Number Zero */
    public static final int QTY_ZERO = 0;

    /** Valid Count Zero */
    public static final int VALID_COUNT_ZERO = 0;

    /** Row Number Zero */
    public static final int ROW_NUM_ONE = 1;

    /** Rtl Wh Category Code Dummy */
    public static final String RTL_WH_CATG_CD_DUMMY = "99";

    /** Service Install Rule Number(No Install:03) */
    public static final String SVC_ISTL_RULE_NUM_NO_INSTALL = "03";

    // START 2023/12/12 K.Watanabe [QC#61300, ADD]
    /** Service Deinstall Rule Number(No Deinstall:54) */
    public static final String SVC_ISTL_RULE_NUM_NO_DEINSTALL = "54";
    // END   2023/12/12 K.Watanabe [QC#61300, ADD]

    /** Time Sub String From */
    public static final int TIME_SUBSTRING_FROM = 8; // 2018/01/10 S21_NA#18460(Sol#087) ADD

    /** Time Sub String To */
    public static final int TIME_SUBSTRING_TO = 12; // 2018/01/10 S21_NA#18460(Sol#087) ADD
    
    // 2018/07/18 S21_NA#26188 Add Start
    /** Required fields missing in selected config. */
    public static final String NWAM0960E = "NWAM0960E";
    
    /** [@] is mandatory. */
    public static final String ZZBM0084E = "ZZBM0084E";
    
    /** Tab anchor Install */
    public static final String TAB_INSTALL = "Install";

    /** Tab anchor Survey */
    public static final String TAB_SURVEY = "Survey";

    /** Tab anchor Contact */
    public static final String TAB_CONTACT = "Contact";
    
    /** PM */
    public static final String PM_CD = "1";

    /** noon */
    public static final int NOON = 1200;
    // 2018/07/18 S21_NA#26188 Add End

    // 2019/11/01 QC#53993 Add Start
    /** The specified value is incorrect. It must be @. */
    public static final String NWAM0974E = "NWAM0974E";
    // 2019/11/01 QC#53993 Add End

    // 2019/11/22 QC#54213 Add Start
    /** No Install Machine Status */
    public static final String RTL_WH_BILL_ONLY = "BO";
    // 2019/11/22 QC#54213 Add End

    // 2019/12/26 QC#54725 Add Srart
    /** [@] could not be acquired. */
    public static final String NWAM0325E = "NWAM0325E";

    /** Tab anchor Survey */
    public static final String MESSAGE_NAME_SCHD_CMNT = "Scheduling Comments";
    // 2019/12/26 QC#54725 Add End
}
