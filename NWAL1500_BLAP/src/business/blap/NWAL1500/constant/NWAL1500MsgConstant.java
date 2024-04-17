/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1500.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         T.Yoshida       Create          N/A
 * 2016/04/18   Fujitsu         S.Takami        Update          S21_NA#5406
 * 2016/06/03   Fujitsu         M.Yamada        Update          S21_NA#7968
 * 2016/06/21   Fujitsu         S.Takami        Update          S21_NA#9849-2
 * 2016/07/11   Fujitsu         S.Takami        Update          S21_NA#7821
 * 2016/07/25   Fujitsu         S.Takami        Update          S21_NA#5062, 9789
 * 2016/07/29   Fujitsu         T.Mizuki        Update          S21_NA#12607
 * 2016/09/13   Fujitsu         S.Takami        Update          S21_NA#8276
 * 2016/09/20   Fujitsu         S.Takami        Update          S21_NA#8279
 * 2016/09/23   Fujitsu         T.Yoshida       Update          S21_NA#10321-20
 * 2016/10/21   Fujitsu         S.Takami        Update          S21_NA#15472
 * 2016/11/08   Fujitsu         S.Takami        Update          S21_NA#15427
 * 2016/11/08   Fujitsu         S.Iidaka        Update          S21_NA#7749
 * 2016/11/09   Fujitsu         S.Iidaka        Update          S21_NA#9863
 * 2016/11/09   Fujitsu         S.Takami        Update          S21_NA#15746, 9864
 * 2016/11/11   Fujitsu         S.Takami        Update          S21_NA#9864-2
 * 2016/12/21   Fujitsu         S.Ohki          Update          S21_NA#16392
 * 2017/08/07   Fujitsu         T.Ogura         Update          Sol#249
 * 2017/10/04   Fujitsu         S.Takami        Update          S21_NA#21300
 * 2018/02/23   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/03/20   Fujitsu         S.Takami        Update          S21_NA#24842
 * 2018/05/11   Fujitsu         T.Aoi           Update          S21_NA#22139
 * 2018/05/16   Fujitsu         M.Ohno          Update          S21_NA#25144
 * 2018/06/01   Fujitsu         M.Ohno          Update          S21_NA#26273
 * 2018/06/05   Fujitsu         S.Takami        Update          S21_NA#26681
 * 2018/06/14   Fujitsu         A.Kosai         Update          S21_NA#25227
 * 2018/06/25   Fujitsu         T.Aoi           Update          S21_NA#20154
 * 2018/10/25   Fujitsu         T.Aoi           Update          S21_NA#28897
 * 2018/11/02   Fujitsu         Hd.Sugawara     Update          S21_NA#29017
 * 2018/12/12   Fujitsu         R.Nakamura      Update          S21_NA#29469
 * 2019/01/30   Fujitsu         M.Ishii         Update          QC#30036
 * 2019/04/15   Fujitsu         K.Ishizuka      Update          S21_NA#31184
 * 2019/06/11   Fujitsu         C.Hara          Update          QC#50787
 * 2019/07/05   Fujitsu         S.Takami        Update          S21_NA#51151
 * 2019/07/18   Fujitsu         K.Kato          Update          S21_NA#51327
 * 2019/09/18   Fujitsu         R.Nakamura      Update          S21_NA#53018
 * 2019/11/15   Fujitsu         R.Nakamura      Update          S21_NA#54515
 * 2019/12/13   Fujitsu         K.Kato          Update          QC#54230
 * 2020/03/16   Fujitsu         C.Hara          Update          QC#56132
 * 2020/04/27   CITS            K.Ogino         Update          QC#56638
 * 2022/08/01   CITS            F.Fadriquela    Update          QC#60273
 * 2022/08/18   Hitachi         H.Watanabe      Update          QC#60255
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NWAL1500MsgConstant {

    // ----------- Message ID -----------
    /** The entered Merchandise Code does not exist in the Master. */
    public static final String NWAM0036E = "NWAM0036E";

    /** The merchandise you specified cannot be sold. */
    public static final String NWAM0037E = "NWAM0037E";

    /** Cannot add anymore details. */
    public static final String NWAM0100E = "NWAM0100E";

    /** No Order information linked to the entered 'Order Number' exists. */
    public static final String NWAM0142E = "NWAM0142E";

    /** The entered @ does not exist in Master. */
    public static final String NWAM0181E = "NWAM0181E";

    /** An error has occurred in the called API [@]. */
    public static final String NWAM0189E = "NWAM0189E";

    /** 'Customer PO Number' has not been entered. */
    public static final String NWAM0209E = "NWAM0209E";

    /** 'Customer PO Date' has not been entered. */
    public static final String NWAM0210E = "NWAM0210E";

    /** @ could not acquired From @. */
    public static final String NWAM0299E = "NWAM0299E";

    /** @ is incorrect value. */
    public static final String NWAM0300E = "NWAM0300E";

    /** In the table [@], there is no data corresponding to [@]. */
    public static final String NWAM0311E = "NWAM0311E";

    /** "Customer P/O Number" is duplicated. */
    public static final String NWAM0368W = "NWAM0368W";

    /** Exchange Rate was not able to be acquired. [@] */
    public static final String NWAM0400E = "NWAM0400E";

    /** The details of the process target have not been selected. */
    public static final String NWAM0504E = "NWAM0504E";

    /** Select at least one of [@]. */
    public static final String NWAM0634E = "NWAM0634E";

    /** Cannot select more than one @. */
    public static final String NWAM0666E = "NWAM0666E";

    /** @ must be selected. */
    public static final String NWAM0667E = "NWAM0667E";

    /** The Serial# in Line#@ and Line#@ is duplicated. */
    public static final String NWAM0669E = "NWAM0669E";

    /** @ has not been entered. */
    public static final String NWAM0671E = "NWAM0671E";

    /** This Order cannot be cancelled because it has been already shipped or Shipping instructions. */
    public static final String NWAM0672E = "NWAM0672E";

    /** Please execute the @ button. */
    public static final String NWAM0673E = "NWAM0673E";

    /** Change Order Created Order # @ */
    public static final String NWAM0677I = "NWAM0677I";

    /** "@" is invalid to process. */
    public static final String NWAM0678E = "NWAM0678E";

    /** Unregistered data cannot be selected. */
    public static final String NWAM0680E = "NWAM0680E";

    /** Only 1 @ can be selected. */
    public static final String NWAM0681E = "NWAM0681E";

    /** Selected line cannot process. */
    public static final String NWAM0682E = "NWAM0682E";

    /** Multiple Details cannot be processed at the same time. */
    public static final String NWAM0683E = "NWAM0683E";

    /** Specified @ does not exist. */
    public static final String NWAM0684E = "NWAM0684E";

    /** The process cannot be executed because the Order is not Closed. */
    public static final String NWAM0685E = "NWAM0685E";

    /** This Order cannot be processed. Please confirm the Order Information. */
    public static final String NWAM0686E = "NWAM0686E";

    /** This Order cannot be processed for Credit Rebill Order exists. */
    public static final String NWAM0687E = "NWAM0687E";

    /** Specified details cannot be changed contains. */
    public static final String NWAM0689E = "NWAM0689E";

    /** It is not possible to simultaneously process the [@] and [@]. */
    public static final String NWAM0690E = "NWAM0690E";

    /** Sales Rep is not assigned to the specified Ship-To customer. */
    public static final String NWAM0757W = "NWAM0757W";

    /** Please select other configuration, because you can't make configuration which has same configuration ID double. */
    public static final String NWAM0758E = "NWAM0758E";

    /** For [@], please enter a date earlier than [@]. */
    public static final String NWAM0765E = "NWAM0765E";

    /** Cannot copy the canceled Order Number. */
    public static final String NWAM0768E = "NWAM0768E";

    /** You cannot select this configuration for deleting, because it includes submitted line(s). */
    public static final String NWAM0769E = "NWAM0769E";

    /** You cannot select this line for deleting, because it had been submitted already. */
    public static final String NWAM0770E = "NWAM0770E";

    /** Order Copy is successfully completed: New Order# [@] */
    public static final String NWAM0771I = "NWAM0771I";

    /** Order qty of serialized item should be 1. */
    public static final String NWAM0772E = "NWAM0772E";

    /** Model is inactive . If ok, please click Save button again. */
    public static final String NWAM0776W = "NWAM0776W";

    /** "Lease Company P/O Number" is duplicated. */
    public static final String NWAM0779W = "NWAM0779W";

    /** Process ended normally. @ records @.(Header : @, Config : @, Line : @,  RMA Config @, RMA : @). */
    public static final String NWAM0780I = "NWAM0780I";

    /** Please select the delete function instead of the cancel function. */
    public static final String NWAM0833E = "NWAM0833E"; // 2016/04/18 S21_NA#5406 Add

    /** This Order cannot be cancelled because it has been already created the RWS. */
    public static final String NWAM0854E = "NWAM0854E"; // S21_NA#7968 Add

    /** Please choose other config action if you would like to do RMA auto Add. */
    public static final String NWAM0864E = "NWAM0864E"; // 2016/06/21 S21_NA#9849-2

    /** Please add accessories in RMA configuration, if you would like to return main machine. */
    public static final String NWAM0870E = "NWAM0870E"; // 2016/07/25 S21_NA#5062, 9789 Add

    // mod start 2016/07/29 CSA S21_NA#12607
    /** Cannot Copy. This order is Credit Rebill Order. */
    public static final String NWAM0871E = "NWAM0871E";
    // mod end 2016/07/29 CSA S21_NA#12607

    /** A pair of Credit/Rebill Order [#@] was also cancelled. */
    public static final String NWAM0874I = "NWAM0874I";

    /** Service Pricing information has been already registered. Please delete it first.  */
    public static final String NWAM0875E = "NWAM0875E";

    /** The entered Order Qty is smaller than Min. Order Qty (@). */
    public static final String NWZM0884E = "NWZM0884E";

    /** The entered Order Qty exceeds Max. Order Qty (@). */
    public static final String NWZM0885E = "NWZM0885E";

    /** Registration Order Qty should be based upon the Order Unit Qty (@). */
    public static final String NWZM0886E = "NWZM0886E";

    // 2016/09/13 S21_NA#8276 Add Start
    /** You cannot set main machine and model item in the same configuration.  */
    public static final String NWAM0892E = "NWAM0892E";
    // 2016/09/13 S21_NA#8276 Add End

    // 2016/09/20 S21_NA#8279 Add Start
    /** Are you shure? You are deleting line(s) which you selected. If there is no problem, plese select "Delete" again. */
    public static final String NWAM0893W = "NWAM0893W";

    /** Are you shure? If you delete line(s), the system will delete line(s) which you selected. If there is no problem, plese select "Delete" again. */
    public static final String NWAM0894W = "NWAM0894W";
    // 2016/09/20 S21_NA#8279 Add End

    /** The relationship between @ and @ is incorrect. Entered value is "@". */
    public static final String NWAM0896E = "NWAM0896E"; // S21_NA#10321-20 Add

    /** [@] cannot be added because the maximum number of line breaks for [@] has been exceeded. */
    public static final String NWAM1500E = "NWAM1500E";

    /** Please input Line Config or RMA */
    public static final String NWAM8252E = "NWAM8252E";

    /** All line count exceeded @. */
    public static final String NWAM8458E = "NWAM8458E";

    /** The line count of config[@] exceeded @. */
    public static final String NWAM8459E = "NWAM8459E";

    /** Please reduce your choice. The system cannot operate for these configurations at once. */
    public static final String NWAM0904E = "NWAM0904E"; // 2016/10/21 S21_NA#15472 Add

    /** When "Install base qty force flag on Config Type" is 'Y' and IB Trackable item, qty should be 1. */
    public static final String NWAM0907E = "NWAM0907E"; // 2016/11/08 S21_NA#7749 Add

    /** When "All component required flag on Config Type" is 'Y', all items of the config ID should be entered. */
    public static final String NWAM0908E = "NWAM0908E"; // 2016/11/08 S21_NA#7749 Add

    /** Please choose other Line Category, because you cannot use this Line Category for this order. */
    public static final String NWAM0909E = "NWAM0909E"; // 2016/11/08 S21_NA#15427 Add

    /** Please delete Shell Pricing data for this item before changing item, because this item is related to Shell Pricing Data. */
    public static final String NWAM0910E = "NWAM0910E"; // 2016/11/09 S21_NA#15746 Add

    /** All items of the config ID should be deleted/cancelled at the same time. Please select Config checkbox instead of Line checkbox. */
    public static final String NWAM0912E = "NWAM0912E"; // 2016/11/09 S21_NA#9863 Add

    /** Please choose other Line Category, because the item in this line is not Inventory Trackable and Install Base Trackable. */
    public static final String NWAM0913E = "NWAM0913E"; // 2016/11/09 S21_NA#9864 Add

    /** Please choose other item, because the item [@] is set item. It is including Loan Order available item and not Loan Order available item. */
    public static final String NWAM0914E = "NWAM0914E"; // 2016/11/11 S21_NA#9864-2

    // 2017/10/04 S21_NA#21300 Add Start
    /** This order can't be cancelled since related PO is already received. */
    public static final String NWAM0946E = "NWAM0946E";

    /** Selected line can't be cancelled since related PO line is already created. */
    public static final String NWAM0947E = "NWAM0947E";
    // 2017/10/04 S21_NA#21300 Add End

    /** It failed to insert the [@]. PK [@] */
    public static final String NWZM1023E = "NWZM1023E";

    /** Please set the Retail Warehouse which is related to [@]. */
    public static final String NWZM2058E = "NWZM2058E"; // 2016/12/21 S21_NA#16392 Add

    /** Model is not found. */
    public static final String NWZM1926W = "NWZM1926W";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** DB error occurred.[CPO update] Please notify to the Admin. */
    public static final String NAZM0493E = "NAZM0493E";

    /** @ is not found.[@] */
    public static final String NSZM0397W = "NSZM0397W";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** Order detail does not exists. */
    public static final String NWAM0694E = "NWAM0694E"; // 2017/08/07 Sol#249 Add

    /** The data you entered will be lost.  OK? */
    public static final String NWAM0136I = "NWAM0136I"; // 2017/08/07 Sol#249 Add

    /** Please clear Serial Number, because this item is not serialized item. */
    public static final String NWAM0939E = "NWAM0939E"; // 2017/09/11 S21_NA#20505 Add

    // 2018/03/05 S21_NA#19808 Add Start
    /** Cannot select the canceled Line. */
    public static final String NWAM0781E = "NWAM0781E";

    /** Please select less than or equal to @ lines. */
    public static final String NWAM0950E = "NWAM0950E";

    /** Please reduce your selection, because your selection has exceeded the system limit. */
    public static final String NWAM0951E = "NWAM0951E";
    // 2018/03/05 S21_NA#19808 Add End
    // 2018/03/20 S21_NA#24842 Add Start
    /** This item is not related the Model [@] or is related 2 or more models. */
    public static final String NWAM0952E = "NWAM0952E";
    // 2018/03/20 S21_NA#24842 Add End

    // 2018/05/16 S21_NA#25144 add start
    /** [@] cannot be selected. */
    public static final String NWAM0783E = "NWAM0783E";
    // 2018/05/16 S21_NA#25144 add end

    // 2018/05/11 S21_NA#22139 Add Start
    /** It failed to register Mail. */
    public static final String NWAM0268E = "NWAM0268E";

    /** The Return Warehouse must be the same for all lines. Please review your entry. */
    public static final String NWAM0955E = "NWAM0955E";

    /** It failed to get Report File. */
    public static final String NWAM0788E = "NWAM0788E";
    // 2018/05/11 S21_NA#22139 Add End

    // 2018/06/01 S21_NA#26273 add start
    /** Order qty should be equal to minimum qty or more.(Mimimum Qty=[@]) */
    public static final String NWZM2023E = "NWZM2023E";

    /** Order qty should be equal to maximum qty or less.(Maximum Qty=[@]) */
    public static final String NWZM2024E = "NWZM2024E";

    /** Order qty should be multiple of increment qty.(Increment Qty=[@]) */
    public static final String NWZM2025E = "NWZM2025E";
    // 2018/06/01 S21_NA#26273 add end

    // 2018/06/05 S21_NA#26681 Add Start
    /** Please change Line Ref#, because referenced line is going to be @. */
    public static final String NWAM0956E = "NWAM0956E";

    /** Selected Line# or Config# is Referenced by other Line(@). Please change Ref#. */
    public static final String NWAM0957E = "NWAM0957E";
    // 2018/06/05 S21_NA#26681 Add End

    // 2018/06/14 S21_NA#25227 Add Start
    /** Please select either Config or Line. */
    public static final String NWAM0688E = "NWAM0688E";

    /** Parent and child of set products can not be selected at the same time. Please select either. */
    public static final String NWAM0958E = "NWAM0958E";
    // 2018/06/14 S21_NA#25227 Add End

    // 2018/06/25 QC#20154 Add Start
    /** Ship to address cannot be update if it is already shipped or partially shipped. */
    public static final String NLZM2518W = "NLZM2518W";
    // 2018/06/25 QC#20154 Add End


    // QC#24245 2018/06/13 Add Start
    /** The entered Config ID isn't located in Inventory */
    public static final String NWZM2266E = "NWZM2266E";

    /** If Config Type Code is '@', this item is not available.*/
    public static final String NWAM0959E = "NWAM0959E";

    /** Please select other configuration, because you can't make configuration which has same configuration ID double. */
    public static final String NWZM2270E = "NWZM2270E";
    // QC#24245 2018/06/13 Add End

    // 2018/10/25 S21_NA#22897 Add Start
    /** Warehouse cannot be changed since RWS has already been created. */
    public static final String NWZM2290E = "NWZM2290E";
    // 2018/10/25 S21_NA#22897 Add End

    // Add Start 2018/11/02 QC#29017
    /** The combination of Item# and Serial# does not exist in the Service Machine Master. */
    public static final String NWZM2293E = "NWZM2293E";
    // Add End 2018/11/02 QC#29017

    // Add Start 2018/12/12 QC#29469
    /** All items you entered are not IB Trackable. Please select "New" for Config Action. */
    public static final String NWAM0963E = "NWAM0963E";
    // Add End 2018/12/12 QC#29469
    // QC#29693 2019/01/08 Add Start
    /** The amount can't be negative. */
    public static final String NWAM0966E = "NWAM0966E";

    /** The amount can't be positive. */
    public static final String NWAM0967E = "NWAM0967E";
    // QC#29693 2019/01/08 Add End

    // 2019/04/15 S21_NA#31184 Add Start
    /** The entered Line Reference Number does not exist in the line. */
    public static final String NWZM1476E = "NWZM1476E";
    // 2019/04/15 S21_NA#31184 Add End

    // 2019/06/11 QC#50787 Add Start
    /**  [@] cannot be canceled / deleted. */
    public static final String NWAM8460E = "NWAM8460E";
    // 2019/06/11 QC#50787 Add End

    // ADD START 2022/08/19 H.Watanabe [QC#60255]
    /** The format of [@] is incorrect. */
    public static final String NWAM8465E = "NWAM8465E";
    // ADD END   2022/08/19 H.Watanabe [QC#60255]

    // ----------- Message Parameter -----------

    /** Category */
    public static final String MSG_PARAM_CATG = "Category";

    /** Reason */
    public static final String MSG_PARAM_RSN = "Reason";

    /** Sales Rep Code */
    public static final String MSG_PARAM_SLS_REP_CD = "Sales Rep Code";

    /** Sales Rep Name */
    public static final String MSG_PARAM_SLS_REP_NM = "Sales Rep Name";

    /** Line Config Tab */
    public static final String MSG_PARAM_LINE_CONFIG_TAB = "Line Config Tab";

    /** RMA Tab */
    public static final String MSG_PARAM_RMA_TAB = "RMA Tab";

    /** Config Line */
    public static final String MSG_PARAM_CONF_LINE = "Config Line";

    /** Bill To Name */
    public static final String MSG_PARAM_BILL_TO_NM = "Bill To Name";

    /** Bill To Number */
    public static final String MSG_PARAM_BILL_TO_NUM = "Bill To Number";

    /** Bill To Location */
    public static final String MSG_PARAM_BILL_TO_LOC = "Bill To Location";

    /** Ship To Name */
    public static final String MSG_PARAM_SHIP_TO_NM = "Ship To Name";

    /** Ship To Number */
    public static final String MSG_PARAM_SHIP_TO_NUM = "Ship To Number";

    /** Ship To Location */
    public static final String MSG_PARAM_SHIP_TO_LOC = "Ship To Location";

    /** Sold To Name */
    public static final String MSG_PARAM_SOLD_TO_NM = "Sold To Name";

    /** Sold To Number */
    public static final String MSG_PARAM_SOLD_TO_NUM = "Sold To Number";

    /** Sold To Location */
    public static final String MSG_PARAM_SOLD_TO_LOC = "Sold To Location";

    // 2019/01/30 QC#30036 Add Start
    /** Customer PO Number */
    public static final String MSG_PARAM_CUST_PO_NUM = "Customer PO Number";
    // 2019/01/30 QC#30036 Add End

    /** Freight Terms */
    public static final String MSG_PARAM_FRT_TERMS = "Freight Terms";

    /** Payment Terms */
    public static final String MSG_PARAM_PMT_TERMS = "Payment Terms";

    /** Carrier Service Level */
    public static final String MSG_PARAM_CARR_SVC_LVL = "Carrier Service Level";

    /** CSMP Number */
    public static final String MSG_PARAM_CSMP_NUM = "CSMP Number";

    /** CSA Number(Dealer Ref#) */
    public static final String MSG_PARAM_CSA_NUM = "CSA Number";

    /** Association Program Name */
    public static final String MSG_PARAM_ASSN_PROGRAM_NM = "Association Program Name";

    /** Association Program Number */
    public static final String MSG_PARAM_ASSN_PROGRAM_NUM = "Association Program Number";

    /** Model */
    public static final String MSG_PARAM_MDL = "Model";

    /** Item # */
    public static final String MSG_PARAM_ITEM = "Item#";

    /** Price List */
    public static final String MSG_PARAM_PRC_LIST = "Price List";

    /** Floor Price List */
    public static final String MSG_PARAM_FLR_PRC_LIST = "Floor Price List";

    /** Warehouse */
    public static final String MSG_PARAM_WH = "Warehouse";

    /** Sub Warehouse */
    public static final String MSG_PARAM_SUB_WH = "Sub Warehouse";

    /** Substitute Item */
    public static final String MSG_PARAM_SBST_ITEM = "Substitute Item";

    // 2018/02/23 S21_NA#19808 Add Start
    /** Config */
    public static final String MSG_PARAM_CONFIG = "Config";
    // 2018/02/23 S21_NA#19808 Add End

    /** Line */
    public static final String MSG_PARAM_LINE = "Line";

    /** Price Change */
    public static final String MSG_PARAM_PRC_CHG = "Price Change";

    /** Component Line */
    public static final String MSG_PARAM_COMP_LINE = "Component Line";

    /** Main Unit */
    public static final String MSG_PARAM_MAIN_UNIT = "Main Unit";

    /** Set Component */
    public static final String MSG_PARAM_SET_COMPONENT = "Set Component";

    /** Sales Credit */
    public static final String MSG_PARAM_SALES_CREDIT = "Sales Credit";

    /** Calc Base */
    public static final String MSG_PARAM_CALC_BASE = "Calc Base";

    /** Log Type */
    public static final String MSG_PARAM_LOG_TYPE = "Log Type";

    /** Min Price */
    public static final String MSG_PARAM_MIN_PRC = "Min Price";

    /** Max Price */
    public static final String MSG_PARAM_MAX_PRC = "Max Price";

    /** Saved */
    public static final String MSG_PARAM_SAVED = "Saved";

    /** Submitted */
    public static final String MSG_PARAM_SUBMITTED = "Submitted";

    /** Deleted */
    public static final String MSG_PARAM_DELETED = "Deleted"; // 2016/07/11 S21_NA#7821 Add

    /** submitted */
    public static final String MSG_FORMAT_REC_NUM_INFO = "Header : %d, Config : %d, Line : %d, RMA Config : %d, RMA : %d";

    /** deleted */
    public static final String MSG_FORMAT_REC_NUM_INFO_DEL = "Config : %d, Line : %d, RMA Config : %d, RMA : %d"; // 2016/07/11 S21_NA#7821 Add

    // 2018/03/05 S21_NA#19808 Add Start
    /** Component Line */
    public static final String MSG_PARAM_CMPT_LINE = "Component Line";
    // 2018/03/05 S21_NA#19808 Add End

    // 2018/06/05 S21_NA#26681 Add Start
    /** for NWAM0956E, deleted */
    public static final String MSG_PARAM_LINE_REF_ACT_DEL = "deleted";

    /** for NWAM0956E, cancelled */
    public static final String MSG_PARAM_LINE_REF_ACT_CANC = "cancelled";

    /** max error param for NWAM0957E */
    public static final int MAX_ERR_REF_NUM_CNT = 10;
    // 2018/06/05 S21_NA#26681 Add End

    // 2019/07/05 S21_NA#51151 Add Start
    /** Please select more than one detail. */
    public static final String NWAM0766E = "NWAM0766E";

    /** Cannot select the Credit Rebill Line. */
    public static final String NWAM0872E = "NWAM0872E";
    // 2019/07/05 S21_NA#51151 Add End

    // Add Start 2019/11/15 QC#54515
    /** Order quantity of IB Trackable item should be 1. */
    public static final String NWAM0975E = "NWAM0975E";
    // Add End 2019/11/15 QC#54515

    // 2019/07/18 S21_NA#51327 Add Start
//    /** Contract has been created. Create a new order and add accessory. */ 
//    public static final String NWAM0970E = "NWAM0970E";
    // 2019/07/18 S21_NA#51327 Add End

    // Add Start 2019/09/18 QC#53018
    /** Cannot specify your selected line category using BILL ONLY WAREHOUSE. */
    public static final String NWAM0972E = "NWAM0972E";
    // Add End 2019/09/18 QC#53018

    // Add Start 2019/10/03 QC#53595
    /** The SalesRep you selected does not belong to IS Territory Group. */
    public static final String NWAM0973E = "NWAM0973E";
    // Add End 2019/10/03 QC#53595

    // 2019/12/13 QC#54230 Add Start
    /** The ship to address on the master has changed, but the ship to address on the SO was not updated because it has already been shipped or partially shipped. */
    public static final String NWAM0976W = "NWAM0976W";
    // 2019/12/13 QC#54230 Add End

    // Add Start 2020/01/17 QC#55202
    /** This item doesn't have Drop RMA. Please update item detail. */
    public static final String NWAM0977E = "NWAM0977E";
    // Add End   2020/01/17 QC#55202

    // Add Start 2020/03/16 QC#56132
    /** The entered 'Warehouse' is not ready for Order. */
    public static final String NWAM0626E = "NWAM0626E";
    // Add End   2020/03/16 QC#56132

    // 2020/04/27 QC#56638 Add Start
    /** Sales Rep is not assigned to the specified Sold-To customer. */
    public static final String NWAM0981W = "NWAM0981W";
    // 2020/04/27 QC#56638 Add End

    // START 2022/08/01 F.Fadriquela [QC#60273, ADD]
    /** You cannot changed the line category for this order */
    public static final String NWAM0986E = "NWAM0986E";
    // END 2022/08/01 F.Fadriquela [QC#60273, ADD]

    // 2023/11/06 QC#61924 Add Start
    /** Bill To# {@} is deactivated for new transactions. */
    public static final String NWAM0987E = "NWAM0987E";

    /** Ship To# {@} is deactivated for new transactions. */
    public static final String NWAM0988E = "NWAM0988E";

    /** Sold To# {@} is deactivated for new transactions. */
    public static final String NWAM0989E = "NWAM0989E";
    // 2023/11/06 QC#61924 Add End
}
