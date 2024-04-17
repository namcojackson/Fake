/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         A.Suda          Create          N/A
 * 2016/06/06   Fujitsu         T.Murai         Update          S21_NA#6442
 * 2016/07/29   Fujitsu         T.Mizuki        Update          S21_NA#12607
 * 2017/02/28   Fujitsu         K.Ishizuka      Update          QC#17851
 * 2017/08/07   Fujitsu         T.Ogura         Update          Sol#249
 * 2018/04/04   Fujitsu         S.Takami        Update          S21_NA#25152
 * 2018/07/20   Fujitsu         Mz.Takahashi    Update          S21_NA#26188
 * 2018/07/27   Fujitsu         T.Noguchi       Update          S21_NA#14307
 * 2018/08/08   Fujitsu         M.Ishii         Update          QC#26551
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 *</pre>
 */
public class NWAL1500MsgConstant {

    // ----------- Message ID -----------
    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Order Number has not been entered. */
    public static final String NWAM0014E = "NWAM0014E";

    /** "Customer P/O Number" is duplicated. */
    public static final String NWAM0368W = "NWAM0368W";

    /** The details of the process target have not been selected */
    public static final String NWAM0504E = "NWAM0504E";

    /** Cannot select more than one @. */
    public static final String NWAM0666E = "NWAM0666E";

    /** @ must be selected. */
    public static final String NWAM0667E = "NWAM0667E";

    /** The order will be cancelled. OK? */
    public static final String NWAM0668I = "NWAM0668I";

    /** @ has not been entered. */
    public static final String NWAM0671E = "NWAM0671E";

    /** Selected line cannot process. */
    public static final String NWAM0682E = "NWAM0682E";

    /** Multiple Details cannot be processed at the same time. */
    public static final String NWAM0683E = "NWAM0683E";

    /** Please select either Config or Line. */
    public static final String NWAM0688E = "NWAM0688E";

    /** It is not possible to simultaneously process the [@] and [@]. */
    public static final String NWAM0690E = "NWAM0690E";

    /** [@] is not selected. */
    public static final String NWAM0697E = "NWAM0697E";

    /** Please select more than one detail. */
    public static final String NWAM0766E = "NWAM0766E";

    /** The selected action[@] is not available. */
    public static final String NWAM0774E = "NWAM0774E";

    /** Model is inactive . If ok, please click Save button again. */
    public static final String NWAM0776W = "NWAM0776W";

    /** "Lease Company P/O Number" is duplicated. */
    public static final String NWAM0779W = "NWAM0779W";

    /** Cannot select the canceled Line. */
    public static final String NWAM0781E = "NWAM0781E";

    /** [@] cannot be selected. */
    public static final String NWAM0783E = "NWAM0783E";

    /** You cannot do this action, because this order is Order Change Modification Order. */
    public static final String NWAM0855E = "NWAM0855E"; // S21_NA#6442 Add

    // mod start 2016/07/29 CSA S21_NA#12607
    /** Cannot Copy. This order is Credit Rebill Order. */
    public static final String NWAM0871E = "NWAM0871E";
    // mod end 2016/07/29 CSA S21_NA#12607

    // S21_NA#11700
    /** Cannot select the Credit Rebill Line. */
    public static final String NWAM0872E = "NWAM0872E";

    // S21_NA#13769(S21_NA#8388) ADD
    /** Cannot register Service Pricing information because declined it. */
    public static final String NWAM0876E = "NWAM0876E";
    
    // S21_NA#17851 ADD
    /** Unregistered data cannot be selected. */ 
    public static final String NWAM0680E = "NWAM0680E";

    /** The data you entered will be lost.  OK? */
    public static final String NWAM0136I = "NWAM0136I"; // 2017/08/07 Sol#249 Add

    // 2018/02/02 S21_NA#19808 Add Start
    /** Please enter "1" or a larger value. */
    public static final String NWAM0370E = "NWAM0370E";

    /** It has exceeded the maximum value (@). */
    public static final String NWAM0707E = "NWAM0707E";

    /** Only numeric values can be entered in [@]. */
    public static final String NWAM0915E = "NWAM0915E";
    // 2018/02/02 S21_NA#19808 Add End
    // 2018/04/04 S21_NA#25152 Add Start
    /** Please enter more than 2 characters if you would like to search with partial key. */
    public static final String NWAM0953E = "NWAM0953E";
    // 2018/04/04 S21_NA#25152 Add End

    // 2018/07/20 S21_NA#26188 Add Start
    /** Only 1 @ can be selected. */
    public static final String NWAM0681E = "NWAM0681E";
    // 2018/07/20 S21_NA#26188 Add End

    // 2018/07/27 S21_NA#14307 Add Start
    /** Customer special instruction is not registered. */
    public static final String NWZM2274W = "NWZM2274W";
    // 2018/07/27 S21_NA#14307 Add End

    // 2018/08/08 QC#26551 Add Start
    /** "Price Change" is not available, because there are some Items that can't get "Sell Price". */
    public static final String NWAM0962E = "NWAM0962E";
    // 2018/08/08 QC#26551 Add End

    // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
    /** There is no line config. */
    public static final String NWAM8474E = "NWAM8474E";
    // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]

    // ----------- Message Parameter -----------
    /** Order Number */
    public static final String MSG_PARAM_ORD_NUM = "Order Number";

    /** Category */
    public static final String MSG_PARAM_CATG = "Category";

    /** Reason */
    public static final String MSG_PARAM_RSN = "Reason";

    /** Sales Rep Code */
    public static final String MSG_PARAM_SLS_REP_CD = "Sales Rep Code";

    /** Sales Rep Name */
    public static final String MSG_PARAM_SLS_REP_NM = "Sales Rep Name";

    /** Ship To Location */
    public static final String MSG_PARAM_SHIP_TO_LOC = "Ship To Location";

    /** Config Line */
    public static final String MSG_PARAM_CONF_LINE = "Config Line";

    /** Component Line */
    public static final String MSG_PARAM_CMPT_LINE = "Component Line";

    /** Model */
    public static final String MSG_PARAM_MDL = "Model";

    /** Config ID */
    public static final String MSG_PARAM_CONFIG_ID = "Config ID";

    /** Serial# */
    public static final String MSG_PARAM_SERIAL_NUM = "Serial#";

    /** Item# */
    public static final String MSG_PARAM_ITEM_NUM = "Item#";

    /** Warehouse */
    public static final String MSG_PARAM_WH = "Warehouse";

    /** Line Config Tab */
    public static final String MSG_PARAM_LINE_CONFIG_TAB = "Line Config Tab";

    /** Rma Tab */
    public static final String MSG_PARAM_RMA_TAB = "RMA Tab";

    /** Set Component Item */
    public static final String MSG_PARAM_SET_COMPONENT = "Set Component Item";

}
