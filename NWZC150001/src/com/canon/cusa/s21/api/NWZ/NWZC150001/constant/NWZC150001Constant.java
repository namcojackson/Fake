/** <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre> */
package com.canon.cusa.s21.api.NWZ.NWZC150001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * NWZC150001Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/26   Fujitsu         Hd.Sugawara     Update          QC#22967
 * 2018/05/20   Fujitsu         S.Takami        Update          S21_NA#25604
 * 2018/06/18   Fujitsu         H.Nagashima     Update          S21_NA#24705
 * 2018/06/25   Fujitsu         T.Aoi           Update          S21_NA#20154
 * 2018/06/13   Fujitsu         Y.Kanefusa      Update          S21_NA#24245
 * 2018/07/23   Fujitsu         S.Takami        Update          S21_NA#24745
 * 2018/07/30   Fujitsu         K.Ishizuka      Update          S21_NA#26181
 * 2018/08/24   Fujitsu         K.Ishizuka      Update          S21_NA#27840
 * 2018/08/21   Fujitsu         S.Takami        Update          S21_NA#26767
 * 2018/09/25   Fujitsu         K.Ishizuka      Update          S21_NA#28482
 * 2018/10/10   Fujitsu         K.Ishizuka      Update          S21_NA#28728
 * 2018/10/18   Fujitsu         S.Takami        Update          S21_NA#26229
 * 2018/10/29   Fujitsu         Hd.Sugawara     Update          S21_NA#28882
 * 2018/10/31   Fujitsu         K.Kato          Update          S21_NA#28921
 * 2018/11/01   Fujitsu         K.Kato          Update          S21_NA#28928
 * 2018/11/28   Fujitsu         Hd.Sugawara     Update          S21_NA#29252
 * 2018/12/20   Fujitsu         C.Hara          Update          S21_NA#28928
 * 2019/01/08   Fujitsu         S.Kosaka        Update          QC#29753
 * 2019/02/22   Fujitsu         K.Ishizuka      Update          S21_NA#30449
 * 2019/03/27   Fujitsu         R.Nakamura      Update          S21_NA#30940
 * 2019/07/30   Fujitsu         T.Noguchi       Update          S21_NA#52201
 * 2019/08/06   Fujitsu         S.Kosaka        Update          S21_NA#52409
 * 2019/11/22   Fujitsu         C.Hara          Update          QC#54213
 * 2019/12/13   Fujitsu         S.Iidaka        Update          S21_NA#53013
 * 2020/01/23   Fujitsu         K.Kato          Update          QC#55207-1
 * 2020/06/09   CITS            K.Ogino         Update          QC#56978
 * 2021/04/19   CITS            A.Marte         Update          QC#58549
 * 2023/12/06   CITS            K.Ikeda         Update          QC#61281
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 * 2024/03/14   CITS            J.Cho           Update          QC#63527
 * </pre>
 */
public class NWZC150001Constant {
    /** request type : save */
    public static final String MODE_SAVE = "01";

    /** request type : submit */
    public static final String MODE_SUBMIT = "02";

    /** request type : cancel */
    public static final String MODE_CANCEL = "03";

    /** request type : delete 2016/07/04 S21_NA#7821 Add */
    public static final String MODE_DELETE = "09";

    // 2017/04/26 S21_NA#Review structure Lv.2 Add Start
    /** request type : Validation Mode */
    public static final String MODE_VALID = "10";
    // 2017/04/26 S21_NA#Review structure Lv.2 Add End

    // 2017/05/08 S21_NA#Review structure Lv.2 Add Start
    /** request type : copy for Header */
    public static final String MODE_CHANGE_MODIFICATION = "20";
    // 2017/05/08 S21_NA#Review structure Lv.2 Add End

    // 2017/04/25 S21_NA#Review structure Lv.2 Add Start
    /** request type : copy for Header */
    public static final String MODE_COPY_HEADER = "30";

    /** request type : copy for All */
    public static final String MODE_COPY_ALL = "31";
    // 2017/04/25 S21_NA#Review structure Lv.2 Add End

    // Add Start 2017/09/11 S21_NA#18859(Sol#154)
    /** request type : copy for Rma */
    public static final String MODE_COPY_ALL_RETURN = "32";
    // Add End   2017/09/11 S21_NA#18859(Sol#154)

    /**  */
    public static final String RQST_TP_CONFIG_NEW = "1";

    /**  */
    public static final String RQST_TP_CONFIG_MODIFY = "2";

    /**  */
    public static final String RQST_TP_CONFIG_DELETE = "3";

    /**  */
    public static final String RQST_TP_DTL_SAVE = "1";

    /**  */
    public static final String RQST_TP_DTL_NEW = "2";

    /**  */
    public static final String RQST_TP_DTL_CANCEL = "3";

    /**  */
    public static final String RQST_TP_DTL_MODIFY = "4";

    /** 2016/07/04 S21_NA#7821 Add */
    public static final String RQST_TP_DTL_DELETE = "9";

    /**  */
    public static final String RQST_TP_RTRN_DTL_SAVE = "1";

    /**  */
    public static final String RQST_TP_RTRN_DTL_NEW = "2";

    /**  */
    public static final String RQST_TP_RTRN_DTL_CANCEL = "3";

    /**  */
    public static final String RQST_TP_RTRN_DTL_MODIFY = "4";

    /** 2016/07/04 S21_NA#7821 Add */
    public static final String RQST_TP_RTRN_DTL_DELETE = "9";

    /**  */
    public static final String RQST_TP_SLS_CR_NEW = "1";

    /**  */
    public static final String RQST_TP_SLS_CR_MODIFY = "2";

    /**  */
    public static final String RQST_TP_SLS_CR_DELETE = "3";

    /**  */
    public static final String RQST_TP_DELY_INFO_NEW = "1";

    /**  */
    public static final String RQST_TP_DELY_INFO_MODIFY = "2";

    /**  */
    public static final String RQST_TP_DELY_INFO_DELETE = "3";

    /**  */
    public static final String RQST_TP_SITE_SRVY_NEW = "1";

    /**  */
    public static final String RQST_TP_SITE_SRVY_MODIFY = "2";

    /**  */
    public static final String RQST_TP_SITE_SRVY_DELETE = "3";

    /**  */
    public static final String RQST_TP_INSTL_INFO_NEW = "1";

    /**  */
    public static final String RQST_TP_INSTL_INFO_MODIFY = "2";

    /**  */
    public static final String RQST_TP_INSTL_INFO_DELETE = "3";

    /**  */
    public static final String RQST_TP_CTAC_PSN_NEW = "1";

    /**  */
    public static final String RQST_TP_CTAC_PSN_MODIFY = "2";

    /**  */
    public static final String RQST_TP_CTAC_PSN_DELETE = "3";

    /**  */
    public static final String RQST_TP_NEW = "1";

    /**  */
    public static final String RQST_TP_MODIFY = "2";

    /**  */
    public static final String RQST_TP_DELETE = "3";

    // START 2021/04/19 A.Marte [QC#58549, ADD]
    /**  IR type */
    public static final String IR_SRC_TP_CD = "IR";

    /**  Closed Open Flag */
    public static final String OPEN_FLG_CLOSED = "N";
    // END 2021/04/19 A.Marte [QC#58549, ADD]

    /**  result type : shell */
    public static final String RESULT_TP_SHELL = "4";

    /** CPO detail line sub number for set : 000 */
    public static final String SET_LINE_SUB_NUM = "000";

    /** 100% : 100.00 */
    public static final BigDecimal PCT_100 = new BigDecimal(100.00);

    /** Order Entry Screen */
    public static final String BIZ_APP_ID_ORDER_ENTRY = "NWAL1500";

    /** Credit/Rebill Entry Screen */
    public static final String BIZ_APP_ID_CREDIT_REBILL_ENTRY = "NWAL2300"; // 2017/10/31 S21_NA#22138 Add

    /** variable character : ITT out bound hold release memo */
    public static final String VAR_CHAR_CONST_NM_ITT_HOLD_RELEASE_MEMO = "ITT_HOLD_RELEASE_MEMO"; // S21_NA#11280

    /** Timestamp Format 2016/09/05 S21_NA#6100 Add  */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    // QC#16425 2016/12/14 Add Start
    /** ORD_PROC_TP.CONV_PROC_TP_TXT LOAN_TO_SLS*/
    public static final String LOAN_TO_SLS = "LOAN_TO_SLS";

    /** ORD_PROC_TP.CONV_PROC_TP_TXT RENTAL_TO_SLS*/
    public static final String RENTAL_TO_SLS = "RENTAL_TO_SLS";
// QC#16425 2016/12/14 Add End

    // Message ID
    /** NWZM0977E : Invalid value is set for process mode. */
    public static final String NWZM0977E = "NWZM0977E";

    /** NWZM0011E : "Data Global Company Code" is required. */
    public static final String NWZM0011E = "NWZM0011E";

    /** NWZM0346E : Sales Date is required. */
    public static final String NWZM0346E = "NWZM0346E";

    /** NWZM0336E : "User ID" is required. */
    public static final String NWZM0336E = "NWZM0336E";

    /** NWZM0087E : "Business Application ID" is not entered. */
    public static final String NWZM0087E = "NWZM0087E";

    /** NWZM1401E : "Direct Sales Order Category Code" is not entered. */
    public static final String NWZM1401E = "NWZM1401E";

    /**
     * NWZM1253E : An input parameter "DS Order Type Code" has not
     * been set.
     */
    public static final String NWZM1253E = "NWZM1253E";

    /** NWZM1377E : Bill to customer Account Code is required. */
    public static final String NWZM1377E = "NWZM1377E";

    /** NWZM0617E : "Bill To Customer CD" is required. */
    public static final String NWZM0617E = "NWZM0617E";

    /** NWZM1379E : Ship to customer Account Code is required. */
    public static final String NWZM1379E = "NWZM1379E";

    /** NWZM1402E : Sell to customer Account Code is required. */
    public static final String NWZM1402E = "NWZM1402E";

    /** NWZM1403E : "Sold To Customer Code" is required. */
    public static final String NWZM1403E = "NWZM1403E";

    /** NWZM0049E : Shipping Service Level Code is required. */
    public static final String NWZM0049E = "NWZM0049E";

    /** NWZM0619E : "Ship To Customer CD" is required. */
    public static final String NWZM0619E = "NWZM0619E";

    /** NWZM0688E : "Sales Rep TOC Code" is required. */
    public static final String NWZM0688E = "NWZM0688E";

    /** NWZM1404E : "Negotiated Deal Amount" is required. */
    public static final String NWZM1404E = "NWZM1404E";

    /** NWZM1405E : "Price Category Code" is required. */
    public static final String NWZM1405E = "NWZM1405E";

    /** NWZM1406E : "Floor Price List Code" is required. */
    public static final String NWZM1406E = "NWZM1406E";

    /** NWZM1266E : Freight Condition Code is required. */
    public static final String NWZM1266E = "NWZM1266E";

    /* */
    /** NWZM1407E : "DS CPO Config PK" is required. */
    public static final String NWZM1407E = "NWZM1407E";

    /** NWZM1408E : "Direct Sales Order Position Number" is required. */
    public static final String NWZM1408E = "NWZM1408E";

    /** NWZM1409E : "Configuration Category Code" is required. */
    public static final String NWZM1409E = "NWZM1409E";

    /** NWZM1410E : "Configuration Type Code" is required. */
    public static final String NWZM1410E = "NWZM1410E";

    /* */
    /** NWZM1411E : "DS CPO Line Number" is required. */
    public static final String NWZM1411E = "NWZM1411E";

    /** NWZM1412E : "DS CPO Line Sub Number" is required. */
    public static final String NWZM1412E = "NWZM1412E";

    /** NWZM1413E : "CPO Line Category Code" is required. */
    public static final String NWZM1413E = "NWZM1413E";

    /** NWZM1414E : "Base Component Flag" is required. */
    public static final String NWZM1414E = "NWZM1414E";

    /* */
    /**
     * NWZM1415E : The entered "DS Order Category Code" does not exist
     * in the Master.
     */
    public static final String NWZM1415E = "NWZM1415E";

    /**
     * NWZM1416E : The entered "Bill to Account Code" does not exist
     * in the Master.
     */
    public static final String NWZM1416E = "NWZM1416E";

    /**
     * NWZM1417E : The entered "Ship to Account Code" does not exist
     * in the Master.
     */
    public static final String NWZM1417E = "NWZM1417E";

    /**
     * NWZM1418E : The entered "Sold to Location Code" does not exist
     * in the Master.
     */
    public static final String NWZM1418E = "NWZM1418E";

    /**
     * NWAM0054E : The entered "Sales Rep Code" does not exist in the
     * Master.
     */
    public static final String NWAM0054E = "NWAM0054E";

    /**
     * NWZM1419E : The entered "Price Category Code" does not exist in
     * the Master.
     */
    public static final String NWZM1419E = "NWZM1419E";

    /**
     * NWZM1420E : The entered "Floor Price List Code" does not exist
     * in the Master.
     */
    public static final String NWZM1420E = "NWZM1420E";

    /**
     * NWZM1421E : The entered "Association Program Code" does not
     * exist in the Master.
     */
    public static final String NWZM1421E = "NWZM1421E";

    /**
     * NWZM1422E : The entered
     * "Lease End of Term Purchase Option Code" does not exist in the
     * Master.
     */
    public static final String NWZM1422E = "NWZM1422E";

    /**
     * NWZM1423E : The entered "Lease Term Code" does not exist in the
     * Master.
     */
    public static final String NWZM1423E = "NWZM1423E";

    /**
     * NWZM1424E : The entered "Lease Payment Frequency Code" does not
     * exist in the Master.
     */
    public static final String NWZM1424E = "NWZM1424E";

    /**
     * NWZM1425E : The entered "Order Log Type Code" does not exist in
     * the Master.
     */
    public static final String NWZM1425E = "NWZM1425E";

    /**
     * NWZM1426E : The entered "Freight Condition Code" does not exist
     * in the Master.
     */
    public static final String NWZM1426E = "NWZM1426E";

    /**
     * NWZM1427E : The entered "Carrier Service Level Code" does not
     * exist in the Master.
     */
    public static final String NWZM1427E = "NWZM1427E";

    /**
     * NWZM1428E : The entered "Special Handling Type Code" does not
     * exist in the Master.
     */
    public static final String NWZM1428E = "NWZM1428E";

    /**
     * NWZM1429E : The entered "Pre Payment Type Code" does not exist
     * in the Master.
     */
    public static final String NWZM1429E = "NWZM1429E";

    /**
     * NWZM1430E : The entered "Credit Rebill Reason Category Code"
     * does not exist in the Master.
     */
    public static final String NWZM1430E = "NWZM1430E";

    /**
     * NWZM1431E : The entered "CPO Line Category Code" does not exist
     * in the Master.
     */
    public static final String NWZM1431E = "NWZM1431E";

    /**
     * NWZM1432E : The entered "Order Line Source Code" does not exist
     * in the Master.
     */
    public static final String NWZM1432E = "NWZM1432E";

    /**
     * NWZM1433E : The entered "Retail Warehouse Code" does not exist
     * in the Master.
     */
    public static final String NWZM1433E = "NWZM1433E";

    /**
     * NWZM1434E : The entered "Retail Sub Warehouse Code" does not
     * exist in the Master.
     */
    public static final String NWZM1434E = "NWZM1434E";

    /**
     * NWZM1435E : The entered "Credit And Rebill Code" does not exist
     * in the Master.
     */
    public static final String NWZM1435E = "NWZM1435E";

    /**
     * NWZM1436E : The entered "Supplier Type Code" does not exist in
     * the Master.
     */
    public static final String NWZM1436E = "NWZM1436E";

    /**
     * NWZM1437E : The entered "Supplier Post Code" does not exist in
     * the Master.
     */
    public static final String NWZM1437E = "NWZM1437E";

    /**
     * NWZM1438E : The entered
     * "Billing Attribute Customer Account Code" does not exist in the
     * Master.
     */
    public static final String NWZM1438E = "NWZM1438E";

    /**
     * NWZM1439E : The entered "Substitute Item Code" does not exist
     * in the Master.
     */
    public static final String NWZM1439E = "NWZM1439E";

    /* */
    /**
     * NWZM1440E : The entered "Configuration Category Code" does not
     * exist in the Master.
     */
    public static final String NWZM1440E = "NWZM1440E";

    /**
     * NWZM1441E : The entered "Configuration Type Code" does not
     * exist in the Master.
     */
    public static final String NWZM1441E = "NWZM1441E";

    /**
     * NWZM1442E : The entered "Service Configuration Master PK" does
     * not exist in the Master.
     */
    public static final String NWZM1442E = "NWZM1442E";

    /**
     * NWZM1443E : The entered "Model ID" does not exist in the
     * Master.
     */
    public static final String NWZM1443E = "NWZM1443E";

    /**
     * NWZM1444E : The entered "Bill to Location Code" does not exist
     * in the Master.
     */
    public static final String NWZM1444E = "NWZM1444E";

    /**
     * NWZM1445E : The entered "Ship to Location Code" does not exist
     * in the Master.
     */
    public static final String NWZM1445E = "NWZM1445E";

    /**
     * NWZM1446E : The entered "Ship to State Code" does not exist in
     * the Master.
     */
    public static final String NWZM1446E = "NWZM1446E";

    /**
     * NWZM1447E : The entered "Ship to Post Code" does not exist in
     * the Master.
     */
    public static final String NWZM1447E = "NWZM1447E";

    /**
     * NWZM1448E : The entered "Ship to Country Code" does not exist
     * in the Master.
     */
    public static final String NWZM1448E = "NWZM1448E";

    /* */
    /**
     * NWZM0925E : The entered Add Payment Term Cash Discount Code
     * does not exist in Master.
     */
    public static final String NWZM0925E = "NWZM0925E";

    /**
     * NWZM1449E : It failed to get the Default Carrier Service Level.
     */
    public static final String NWZM1449E = "NWZM1449E";

    /**
     * NWZM1450E : The relationship between 'Order Category' and
     * 'Order Reason' is incorrect.
     */
    public static final String NWZM1450E = "NWZM1450E";

    /**
     * NWZM1451E : The relationship between 'Order Reason' and 'Order
     * Sub Reason' is incorrect.
     */
    public static final String NWZM1451E = "NWZM1451E";

    /**
     * NWZM1452E : The relationship between 'Bill To Customer Location
     * Code' and 'Bill to customer Account Code' is incorrect.
     */
    public static final String NWZM1452E = "NWZM1452E";

    /**
     * NWZM1453E : The relationship between 'Add Ship To Customer
     * Code' and 'Ship to Customer Account Code' is incorrect.
     */
    public static final String NWZM1453E = "NWZM1453E";

    /**
     * NWZM1454E : The relationship between 'Sold to customer Location
     * Code' and 'Sell To Customer Account Code' is incorrect.
     */
    public static final String NWZM1454E = "NWZM1454E";

    /**
     * NWZM1455E : The relationship between 'Bill to', 'Ship to' and
     * 'Sold to' is incorrect.
     */
    public static final String NWZM1455E = "NWZM1455E";

    /**
     * NWZM1456E : The relationship between 'Freight Condition Code',
     * 'Freight Charge To Code' and 'Freight Charge Method Code' is
     * incorrect.
     */
    public static final String NWZM1456E = "NWZM1456E";

    /**
     * NWZM1457E : The relationship between 'Carrier Service Level
     * Code' and 'Shipping Service Level Code' is incorrect.
     */
    public static final String NWZM1457E = "NWZM1457E";

    /**
     * NWZM1458E : The relationship between 'Freight Condition Code',
     * 'LOB', 'Carrier Service Level Code' and 'Add Shipping Service
     * Level Code' is incorrect.
     */
    public static final String NWZM1458E = "NWZM1458E";

    /**
     * <pre>NWZM2010E : If Freight Condition Code is 'Collect', Carrier Service Level must be entered.</pre>
     */
    public static final String NWZM2010E = "NWZM2010E";

    /**
     * NWZM1459E : If Freight Condition Code is 'Collect', Carrier
     * Account Number must be entered.
     */
    public static final String NWZM1459E = "NWZM1459E";

    /**
     * <pre>NWZM1460E : If Add Payment Term Cash Discount Code is 'Credit Card', DS Credit Card PK must be entered.</pre>
     */
    public static final String NWZM1460E = "NWZM1460E";

    /**
     * NWZM1461E : The relationship between 'CSMP Number' and 'Sell To
     * Customer Account Code' is incorrect.
     */
    public static final String NWZM1461E = "NWZM1461E";

    /**
     * NWZM1462E : The relationship between 'CSA Dealer Reference
     * Number' and 'Sell To Customer Account Code' is incorrect.
     */
    public static final String NWZM1462E = "NWZM1462E";

    /**
     * NWZM1463E : The relationship between 'Sales Rep' and 'Business
     * Area Organization' is incorrect.
     */
    public static final String NWZM1463E = "NWZM1463E";

    /**
     * NWZM1464E : The relationship between 'Price Category' and
     * 'Order Type Process Definition' is incorrect.
     */
    public static final String NWZM1464E = "NWZM1464E";

    /**
     * NWZM1465E : The relationship between 'Floor Price List' and
     * 'Order Type Process Definition' is incorrect.
     */
    public static final String NWZM1465E = "NWZM1465E";

    /**
     * NWZM1466E : If Config Type Code is 'Add Config' or 'Existing
     * Config', Config ID must be entered.
     */
    public static final String NWZM1466E = "NWZM1466E";

    /**
     * NWZM1467E : 'Ship to location' and 'Installed location' are
     * inconsistent.
     */
    public static final String NWZM1467E = "NWZM1467E";

    /**
     * NWZM1468E : The relationship between 'Merchandise Code' and
     * 'Customer Merchandise Code' is incorrect.
     */
    public static final String NWZM1468E = "NWZM1468E";

    /**
     * NWZM1469E : The relationship between 'CPO Order Type Code' and
     * 'CPO Line Category Code' is incorrect.
     */
    public static final String NWZM1469E = "NWZM1469E";

    /**
     * NWZM1470E : The relationship between 'Retail Warehouse Code',
     * 'Retail Sub Warehouse Code' and 'CPO Order Type Code' is
     * incorrect.
     */
    public static final String NWZM1470E = "NWZM1470E";

    /**
     * <pre>NWZM1471E : The entered "Serial Number" does not exist in the Master.</pre>
     */
    public static final String NWZM1471E = "NWZM1471E";

    /**
     * NWZM1472E : The relationship between 'Serial Number' and
     * 'Service Machine Master' is incorrect.
     */
    public static final String NWZM1472E = "NWZM1472E";

    /**
     * NWZM1473E : The relationship between 'Serial Number' and
     * 'Service Configuration Master PK' is incorrect.
     */
    public static final String NWZM1473E = "NWZM1473E";

    /**
     * NWZM1474E : The relationship between 'Serial Number' and
     * 'Warehouse Code' is incorrect.
     */
    public static final String NWZM1474E = "NWZM1474E";

    /**
     * NWZM1475E : The relationship between 'Merchandise Code' and
     * 'Substitute Item Code' is incorrect.
     */
    public static final String NWZM1475E = "NWZM1475E";

    /**
     * NWZM1476E : The entered Line Reference Number does not exist in
     * the line.
     */
    public static final String NWZM1476E = "NWZM1476E";

    /**
     * NWZM1477E : The relationship of 'Billing Attribute Customer
     * Account Code' is incorrect.
     */
    public static final String NWZM1477E = "NWZM1477E";

    /**
     * NWZM1478E : You can not set the process for the warehouse to
     * the specified CPO Line Category Code.
     */
    public static final String NWZM1478E = "NWZM1478E";

    /**
     * NWZM1303E : Service Config Master PK of the parameter is not
     * set.
     */
    public static final String NWZM1303E = "NWZM1303E";

    /**
     * NWZM1479E : Serial# is not set.
     */
    public static final String NWZM1479E = "NWZM1479E";

    /**
     * NWZM1480E : There are unmatched Config PK between inbound and
     * outbound.
     */
    public static final String NWZM1480E = "NWZM1480E";

    /**
     * NWZM1481E : There are unmatched value between inbound and
     * service exchange.
     */
    public static final String NWZM1481E = "NWZM1481E";

    /**
     * NWZM1482E : The Base Component does not exists in config.
     */
    public static final String NWZM1482E = "NWZM1482E";

    /**
     * NWZM1483E : Order qty of main machine should be 1.
     */
    public static final String NWZM1483E = "NWZM1483E";

    /**
     * NWZM1484E : Order qty which is entered serial# should be 1.
     */
    public static final String NWZM1484E = "NWZM1484E";

    /**
     * NWZM1485E : Order qty of license controlled item should be 1.
     */
    public static final String NWZM1485E = "NWZM1485E";

    /** NWZM1486E : Order qty should be greater than 0. */
    public static final String NWZM1486E = "NWZM1486E";

    /**
     * NWZM1487E : Order qty should be equal to allocated qty or more.
     */
    public static final String NWZM1487E = "NWZM1487E";

    /** NWZM1488E : Order qty should be equal to minimum qty or more. */
    public static final String NWZM1488E = "NWZM1488E";

    /** NWZM1489E : Order qty should be equal to maximum qty or less. */
    public static final String NWZM1489E = "NWZM1489E";

    /** NWZM1492E : Order qty should be multiple of increment qty. */
    public static final String NWZM1492E = "NWZM1492E";

    /**
     * NWZM1490E : The relationship of 'CSMP Price List Code' is
     * incorrect.
     */
    public static final String NWZM1490E = "NWZM1490E";

    /** NWZM0073E : The data does not exist in CPO. */
    public static final String NWZM0073E = "NWZM0073E";

    /** NWZM0074E : The data does not exist in CPO_DTL. */
    public static final String NWZM0074E = "NWZM0074E";

    /** NWZM0081E : It failed to update the CPO_DTL. */
    public static final String NWZM0081E = "NWZM0081E";

    /** NWZM1368E : Failed to update the CPO. */
    public static final String NWZM1368E = "NWZM1368E";

    /**
     * NWZM1503E : "DROP_SHIP_RTL_WH_CD" doesn't exist in the
     * VAR_CHAR_CONST.
     */
    public static final String NWZM1503E = "NWZM1503E";

    /**
     * NWZM1504E : "NOT_HARD_ALLOC_WH_CD" doesn't exist in the
     * VAR_CHAR_CONST.
     */
    public static final String NWZM1504E = "NWZM1504E";

    /** NWZM1505E : It failed to get the PRC_EQUIP_CONTR_ATTRB. */
    public static final String NWZM1505E = "NWZM1505E";

    /** NWZM1525E : There are no in-bound data in CPO config. */
    public static final String NWZM1525E = "NWZM1525E";

    /** NWZM1526E : There are no data in SVC_CONFIG_MSTR. */
    public static final String NWZM1526E = "NWZM1526E";

    /** NWZM1527E : There are no out-bound data in CPO config. */
    public static final String NWZM1527E = "NWZM1527E";

    /** NWZM1528E : There are no out-bound data in CPO detail. */
    public static final String NWZM1528E = "NWZM1528E";

    /** <pre>NWZM1529E : The relationship between 'Return Machine Master Order Type' and 'Service Exchange Reason' is incorrect. </pre> */
    public static final String NWZM1529E = "NWZM1529E";

    /** NWZM1530E : It failed to get the EASY_PACK type code. */
    public static final String NWZM1530E = "NWZM1530E";

    /** NWZM1531E : It is not available to use the EASY_PACK item. */
    public static final String NWZM1531E = "NWZM1531E";

    /** NWZM1532E : It failed to get the Supply Program Contract. */
    public static final String NWZM1532E = "NWZM1532E";

    /** NWZM0100E : CPO Detail Line Number is not entered. */
    public static final String NWZM0100E = "NWZM0100E";

    /** <pre>NWZM0101E : CPO Detail Line Sub Number is not entered.</pre> */
    public static final String NWZM0101E = "NWZM0101E";

    /** <pre>NWZM0492E : "Merchandise Code" is not set.</pre> */
    public static final String NWZM0492E = "NWZM0492E";

    /** <pre>NWZM0507E : "Ship To Customer Code" is not set.</pre> */
    public static final String NWZM0507E = "NWZM0507E";

    /** <pre>NWZM1593E : The entered "Hard Disc Drive Removal Code" does not exist in the Master.</pre> */
    public static final String NWZM1593E = "NWZM1593E";

    /** <pre>NWZM1594E : The entered "Return Reason Code" does not exist in the Master.</pre> */
    public static final String NWZM1594E = "NWZM1594E";

    /** <pre>NWZM1595E : The entered "Third Party Disposition Type Code" does not exist in the Master.</pre> */
    public static final String NWZM1595E = "NWZM1595E";

    /** <pre>NWZM1596 : The return qty must be a negative number.</pre> */
    public static final String NWZM1596E = "NWZM1596E";

    /** <pre>NWAM0037E : The merchandise you specified cannot be sold.</pre> */
    public static final String NWAM0037E = "NWAM0037E";

    /** <pre>NWZM1507E : The entered Item is not a returnable item.</pre> */
    public static final String NWZM1507E = "NWZM1507E";

    // 2016/01/13 S21_NA#2726 Add Start
    /** <pre>NWZM1794E : Please select Existing IB or Add To Config as Config Type.</pre> */
    public static final String NWZM1794E = "NWZM1794E";

    /** <pre>NWZM1795E : This item is unable to be included in this configuration.</pre> */
    public static final String NWZM1795E = "NWZM1795E";

    // 2016/01/13 S21_NA#2726 Add End

    // 2016/01/25 S21_NA#3505 Add Start
    /** <pre>NWZM1887E : The entered Config ID isn't located at customer site.</pre> */
    public static final String NWZM1887E = "NWZM1887E";

    // 2016/01/25 S21_NA#3505 Add End

    // 2016/01/26 S21_NA#3740 Add Start
    /** <pre>NWZM1889E : The system couldn't obtain Transaction Code and Transaction Reason Code. Please confirm the setting.</pre> */
    public static final String NWZM1889E = "NWZM1889E";

    // 2016/01/26 S21_NA#3740 Add End

    // 2016/02/17 S21_NA#1703 Add Start
    /** <pre>Order qty of serialized item should be 1.</pre> */
    public static final String NWZM1917E = "NWZM1917E";

    // 2016/02/17 S21_NA#1703 Add End

    // 2016/02/23 S21_NA#3239 Mod Start
    /** <pre>@ is not found.[@].</pre> */
    public static final String NSZM0396E = "NSZM0396E";

    /** <pre>Model is not found.</pre> */
    public static final String NWZM1926W = "NWZM1926W";

    // 2016/02/23 S21_NA#3239 Mod End

    // 2016/02/25 S21_NA#1704 Add Start
    /** <pre>In one config,  it is not possible to specify the same machine.</pre> */
    public static final String NWZM1921E = "NWZM1921E";

    // 2016/02/25 S21_NA#1704 Add End

    // 2016/03/16 S21_NA#5519 Start
    /**
     * You cannot set 2 or more Config ID in one configuration. Please
     * separate these items in different configuration.
     */
    public static final String NWZM1928E = "NWZM1928E";

    // 2016/03/16 S21_NA#5519 End

    // 2016/06/07 S21_NA#8464 Add Start
    /** There is no DS CPO Configuration data in parameter. */
    public static final String NWZM1944E = "NWZM1944E";

    // 2016/06/07 S21_NA#8464 Add Start

    // 2016/06/09 S21_NA#9426 Add Start
    /** Pmtc Process Status (Success) */
    public static final String PMTC_PROC_STS_SUCCESS = "0";

    /** Pmtc Approve Status (Approved) */
    public static final String PMTC_APVL_STS_APPLOVED = "1";

    /**
     * The Credit Card Information you specified, cannot be obtained
     * from DS_CR_CARD master.
     */
    public static final String NWZM1946E = "NWZM1946E";

    // 2016/06/09 S21_NA#9426 Add End

    /**
     * Some errors were occurred in Credit Card Process. Please
     * contact System Administrator.
     */
    public static final String NWZM1950E = "NWZM1950E"; // 2016/07/26
                                                        // S21_NA#11866-2

    // 2016/07/21 S21_NA#9228 Add Start
    /**
     * You cannot choose payment method "Credit Card", because total
     * amount is negative. (Warning)
     */
    public static final String NWZM1951W = "NWZM1951W";

    /**
     * You cannot choose payment method "Credit Card", because total
     * amount is negative. (Error)
     */
    public static final String NWZM1952E = "NWZM1952E";
    // 2016/07/21 S21_NA#9228 Add End
 
    /**
     * NWZM1990E : The system cannot find a default warehouse for your line. 
     * Please enter a warehouse manually or call system admin for setup change.
     */
    public static final String NWZM1990E = "NWZM1990E";

    // S21_NA#11630 ADD START
    /** <pre>This Order cannot be cancelled because it has been already shipped or Shipping instructions.</pre> */
    public static final String NWZM1992E = "NWZM1992E";

    /** <pre>This Order cannot be cancelled because it has been already created the RWS.</pre> */
    public static final String NWZM1993E = "NWZM1993E";

    /** <pre>This order cannot be canceled. A pair of Credit/Rebill Order doesn't exist.</pre> */
    public static final String NWZM1994E = "NWZM1994E";

    /** <pre>This order cannot be canceled. A pair of Credit/Rebill Order has been already cancelled.</pre> */
    public static final String NWZM1995E = "NWZM1995E";

    /** <pre>This order cannot be canceled. A pair of Credit/Rebill Order has been already closed.</pre> */
    public static final String NWZM1996E = "NWZM1996E";

    /** <pre>It failed to cancel a pair of Credit/Rebill Order.</pre> */
    public static final String NWZM1997E = "NWZM1997E";

    // S21_NA#11630 ADD END

    /** <pre>MDSE information linked to the "Merchandise Code" does not exist.</pre> */
    public static final String NWZM0622E = "NWZM0622E"; // S21_NA#13768 ADD 

    /** NWZM2005E : Entered 'PaymentMethod' doesn't agree with 'Payment Term'. */
    public static final String NWZM2005E = "NWZM2005E"; // S21_NA#11547 ADD

    // Add Start 2018/11/28 QC#29252
    /** NWZM1838E : Payment Credit Card is required. */
    public static final String NWZM1838E = "NWZM1838E";
    // Add End 2018/11/28 QC#29252

    /** NWZM1839E : Payment Credit Card or Payment Cash is required. */
    public static final String NWZM1839E = "NWZM1839E"; // QC#17474 2017/02/21 Add

    /** NWZM2008E : The customer doesn't have an active Easy Pac I contract. */
    public static final String NWZM2008E = "NWZM2008E"; // S21_NA#13918 ADD

    /** Failed to Update DS_CPO_RTRN_DTL. */
    public static final String NWZM2011E = "NWZM2011E"; // S21_NA#13796 ADD 

    /** The data does not exist in DS_CPO_RTRN_DTL. */
    public static final String NWZM2012E = "NWZM2012E"; // S21_NA#13796 ADD 

    // QC#14329 2016/09/30 Add Start
    /** NWZM2026E : Credit Card information is not entered as Payment Method is "Credit Card". */
    public static final String NWZM2026E = "NWZM2026E";
    // QC#14329 2016/09/30 Add End

    // 2016/10/13 S21_NA#7924-2 Add Start
    /** Sales Credit is not available. Please setup sales credit again. */
    public static final String NWZM2033E= "NWZM2033E";
    // 2016/10/13 S21_NA#7924-2 Add End

    // 2016/11/01 S21_NA#10965 Add Start
    /** At least one Machine or Accessory item is required in Config of Retail Equipment Order. */
    public static final String NWZM2046E = "NWZM2046E";
    // 2016/11/01 S21_NA#10965 Add End

    // 2016/12/15 S21_NA#13768 Add Start
    /** Entered outbound items are not allowed for this service exchange. Service Model must retain after exchange. */
    public static final String NWZM2054E = "NWZM2054E";
    // 2016/12/15 S21_NA#13768 Add End

    // 2017/04/25 S21_NA#Review structure Lv.2 Add Start
    /** NWZM1205E : "CPO Order Number for the parameter is not set. */
    public static final String NWZM1205E = "NWZM1205E";
    // 2017/04/25 S21_NA#Review structure Lv.2 Add End

    // 2017/12/21 S21_NA#20050 Add Start
    /** The owner of this item is CFS. Please select CFS retail warehouse. */
    public static final String NWZM2250E = "NWZM2250E";
    // 2017/12/21 S21_NA#20050 Add End

    // 2018/01/11 S21_NA#23329 Add Start
    /** There is no parent item for Software Model. Please set the parent item for the Software Configuration. */
    public static final String NWZM2252E = "NWZM2252E";
    // 2018/01/11 S21_NA#23329 Add End

    // 2018/02/13 S21_NA#22717 Add Start
    /** This item cannot create asset. Please change Line Category. */
    public static final String NWZM2253E = "NWZM2253E";
    // 2018/02/13 S21_NA#22717 Add End

    // Add Start 2018/02/26 QC#22967
    /** The relationship between 'Ship To' and 'Sold To' is incorrect. */
    public static final String NWZM2254E = "NWZM2254E";

    /** The relationship between 'Sold To' and 'Bill To' is incorrect. */
    public static final String NWZM2255E = "NWZM2255E";
    // Add End 2018/02/26 QC#22967

    // 2018/05/20 S21_NA#25604 Add Start
    /** Please set Main Machine in Line Config or delete Main Machine from same Config ID RMA, because the Config needs only one Main Machine. */
    public static final String NWZM2262E = "NWZM2262E";

    /** Please set Main Machine in RMA config from designated Config or delete Main Machine from same Config ID Line Config, because the Config needs only one Main Machine. */
    public static final String NWZM2263E = "NWZM2263E";
    // 2018/05/20 S21_NA#25604 Add End

    // 2018/06/18 QC#24705 add Start
    /** Please enter the negative value if Line Category is CREDIT. */
    public static final String NWZM2264E = "NWZM2264E";

    /** Please enter the positive value if Line Category is REBILL. */
    public static final String NWZM2265E = "NWZM2265E";
    // 2018/06/18 QC#24705 add End

    // 2018/06/25 QC#20154 Add Start
    /** Ship to address cannot be update if it is already shipped or partially shipped. */
    public static final String NLZM2518W = "NLZM2518W";
    // 2018/06/25 QC#20154 Add End

    // QC#24245 2018/06/13 Add Start
    /** The entered Config ID isn't located in Inventory */
    public static final String NWZM2266E = "NWZM2266E";

    /** You cannot set main machine and model item in the same configuration. */
    public static final String NWZM2269E = "NWZM2269E";

    /** Please select other configuration, because you can't make configuration which has same configuration ID double. */
    public static final String NWZM2270E = "NWZM2270E";
    // QC#24245 2018/06/13 Add End
    
    // 2018/07/30 S21_NA#26181 Add Start
    /** Another open RMA line already exists for the same Serial#. */
    public static final String NWZM2275E = "NWZM2275E";
    // 2018/07/30 S21_NA#26181 Add End

    // 2018/07/23 S21_NA#24745 Add Start
    /** Please choose the other Line Category, because there is no relationship between the owner of the warehouse and the line category. */
    public static final String NWZM2271E = "NWZM2271E";
    /** Please choose the other Line Category, because there is no relationship between the item type and the line category. */
    public static final String NWZM2272E = "NWZM2272E";
    /** Please choose the other Line Category, because there is no relationship between the item and the line category. */
    public static final String NWZM2273E = "NWZM2273E";
// 2018/07/23 S21_NA#24745 Add End
    
    /** Since the target MDSE CD is set as "DISCONTINUED". */
    public static final String NWZM2277E = "NWZM2277E";
    // 2018/08/24 S21_NA#25746 Add

    // 2018/08/21 S21_NA#26767 Add Start
    /** Please set the Retail Warehouse which is related to CSA. */
    public static final String NWZM2278E = "NWZM2278E";
    /** Please set the Retail Warehouse which is related to CFS. */
    public static final String NWZM2279E = "NWZM2279E";
    /** Please set the Retail Warehouse which is related to GMD. */
    public static final String NWZM2280E = "NWZM2280E";
    /** Please set the Retail Warehouse which is related to BSD. */
    public static final String NWZM2281E = "NWZM2281E";
    /** Please set the Retail Warehouse which is related to SHW. */
    public static final String NWZM2282E = "NWZM2282E";
    /** Please set the Retail Warehouse which is related to OTH. */
    public static final String NWZM2283E = "NWZM2283E";
    /** Please set the Retail Warehouse which is related to Return Reason. */
    public static final String NWZM2284E = "NWZM2284E";
    // 2018/08/21 S21_NA#26767 Add End
    
    /** Serial Number is not valid. */ // 2018/10/10 S21_NA#28728 Add
    public static final String NWZM2285E = "NWZM2285E";

    /** Existing IB is not allowed to register partially. */// QC#28772 2018/10/16 Add
    public static final String NWZM2286E = "NWZM2286E";

    // 2018/10/18 S21_NA#26229 Add Start
    /** The system cannnot process with this Line Category and Order Reason, because this item is Rental IB Item. Please change the Order Reason and Line Category. */
    public static final String NWZM2287E = "NWZM2287E";

    /** The system cannnot process with this Line Category and Order Reason, because this item is Loan IB Item. Please change the Order Reason and Line Category. */
    public static final String NWZM2288E = "NWZM2288E";
    // 2018/10/18 S21_NA#26229 Add End

    // Add Start 2018/10/29 QC#28882
    /** Please select Config Action=Return existing IB from customer since the serial number you enter is a part of existing Configuration. */
    public static final String NWZM2289E = "NWZM2289E";
    // Add End 2018/10/29 QC#28882

    /** Please select Config Action=Existing IB in WH since the line item you enter is a component of the config# @.  */ // 2018/10/31 S21_NA#28921 Add
    public static final String NWZM2291E = "NWZM2291E";

    /** Machinemaster status is not ready for return.  */ // 2018/11/01 S21_NA#28928 Add
    public static final String NWZM2292E = "NWZM2292E";

    /** relationship between 'Warehouse' and 'Line Source' is incorrect. */  // QC#29262 2018/11/27 Add
    public static final String NWZM2294E = "NWZM2294E";
    
    /** You cannot select this Machine Master because it is not in warehouse. */  // 2018/12/20 QC#28928 Add
    public static final String NWZM2301E = "NWZM2301E";
    
    /** You cannot return this Machine Master because it is not at customer site. */  // 2018/12/20 QC#28928 Add
    public static final String NWZM2302E = "NWZM2302E";
    
    /** You cannot select this Machine Master because it is selected by another order. */  // 2018/12/20 QC#28928 Add
    public static final String NWZM2303E = "NWZM2303E";
    
    /** Contract# and Serial# is mandatory. Please check the additional line detail info. */  // 2019/02/22 QC#30449 Add
    public static final String NWZM2306E = "NWZM2306E";

    /** The Relationship between 'Sub WH' and 'Line Source' is incorrect. */  // 2019/07/30 QC#52201 Add
    public static final String NWZM2310E = "NWZM2310E";

    // 2017/05/08 S21_NA#Review structure Lv.2 Add Start
    /** Attach Business Name */
    public static final String ATTACH_BUSINESS_NM = "Order Entry";

    /** Attach Data Nm */
    public static final String ATTACH_DATA_ORD_NUM = "Order Number";

    /** Attach Doc Tp Cd Order */
    public static final String ATTACH_DOC_TP_ORDER = "10";
    // 2017/05/08 S21_NA#Review structure Lv.2 Add End

    /** Call Sequence: No Operation */
    public static final char CALL_SEQ_NOP = 0X00;

    /** Call Sequence: Submit */
    public static final char CALL_SEQ_SUBMIT = CALL_SEQ_NOP + 0X01;

    /** Call Sequence: Save */
    public static final char CALL_SEQ_SAVE = CALL_SEQ_SUBMIT + 0X01;

    /** Call Sequence: Save -> Submit */
    public static final char CALL_SEQ_SAVESUBMIT = CALL_SEQ_SAVE + 0X01;

    /** Call Sequence: Cancel */
    public static final char CALL_SEQ_CANCEL = CALL_SEQ_SAVESUBMIT + 0X01;

    // 2017/04/26 S21_NA#Review structure Lv.2 Add Start
    /** Call Sequence: Validation */
    public static final char CALL_SEQ_VALID = 0x08;
    // 2017/04/26 S21_NA#Review structure Lv.2 Add End

    // String Asterisk
    public static final String STR_ASTERISK = "*";   // 2017/10/18 S21_NA#16347 Add

    // Add Start 2019/03/27 QC#30940
    // String Comma
    public static final String STR_COMMA = ",";
    // Add End 2019/03/27 QC#30940

    // 2017/10/24 S21_NA#21773 Add Start
    /** CPO_SRC_TP - CREDIT */
    public static final String CPO_SRC_TP_CR = "CR";
    // 2017/10/24 S21_NA#21773 Add End

    // 2017/11/09 S21_NA#22091 Add Start
    /** DI Check Target Table */
    public static final String DI_CHK_TBL_ISTL_INFO = "DS_CPO_ISTL_INFO";

    /** DI Check Target Columns */
    public static final String[] DI_CHK_COL_ISTL_INFO_ARRAY = {"SVC_ISTL_RULE_NUM", "ISTL_DIV_CD"};

    /** DI Check Target Install Type */
    public static final String DI_CHK_COL_ISTL_INFO_ISTL_TP = "SVC_ISTL_RULE_NUM";

    /** DI Check Target Install Division */
    public static final String DI_CHK_COL_ISTL_INFO_ISTL_DIV = "ISTL_DIV_CD";

    /** Service Install Rule Number: No Install */
    public static final String SVC_ISTL_RULE_NUM_NO_INSTALL = "03";
    // 2017/11/09 S21_NA#22091 Add End

    // START 2023/12/12 K.Watanabe [QC#61300, ADD]
    /** Service Deinstall Rule Number: No Install */
    public static final String SVC_DEISTL_RULE_NUM_NO_INSTALL = "54";
    // END 2023/12/12 K.Watanabe [QC#61300, ADD]

    /** Variable Character Constant : CR_AND_BILL_ONLY_DUMMY_WH_CD */ // 2018/01/18 K.Ishizuka S21_NA#23555 ADD
    public static final String CONST_KEY_CR_AND_BILL_ONLY_DUMMY_WH_CD = "CR_AND_BILL_ONLY_DUMMY_WH_CD";

    /** Variable Character Constant : CONST_KEY_NO_HARD_ALLOC_WH_CD */ // 2018/01/24 S21_NA#23706 Add
    public static final String CONST_KEY_NO_HARD_ALLOC_WH_CD = "NOT_HARD_ALLOC_WH_CD";

    /** SLS_CR_PCT_MD */
    public static enum SLS_CR_PCT_MD {
        NONE //
        , WRITER_ONLY //
        , WRITER_AND_INSTALLER //
        ,
    }

    /** DEF_SLS_CR_PCT_WRITER : for get NUM_CONST */
    public static final String DEF_SLS_CR_PCT_WRITER = "DEF_SLS_CR_PCT_WRITER";
    
    /** Drop Ship Warehouse Code */
    public static final String DROP_SHIP_WH_CD = "DROP_SHIP_WH_CD";
    // 2018/08/24 S21_NA#25746 Add
    
    // 2018/09/26 S21_NA#28482 Add Start
    /** Existing Warehouse Config Status */
    public static final String EXST_WH_CONFIG_STS = "EXST_WH_CONFIG_STS";
    
    /** Validation Skip Machine Status */
    public static final String VALID_SKIP_MACH_STS = "VALID_SKIP_MACH_STS";
    // 2018/09/26 S21_NA#28482 Add End

    // 2019/01/08 QC#29753 Add Start
    /** The merchandise you specified cannot be purchased. */
    public static final String NWAM0965E = "NWAM0965E";
    // 2019/01/08 QC#29753 Add End

    // 2019/08/06 QC#52409 Mod Start
    /** Validation Skip Machine Status */
    public static final String RTL_WH_LOAN = "LO";
    // 2019/08/06 QC#52409 Mod End

    // 2019/11/22 QC#54213 Add Start
    /** No Install Machine Status */
    public static final String RTL_WH_BILL_ONLY = "BO";
    // 2019/11/22 QC#54213 Add End

    // 2019/12/13 S21_NA#53013 Add Start
    /** Import API Program ID */
    public static final String IMPT_API_PG_ID = "NWZC2260";
    // 2019/12/13 S21_NA#53013 Add End

    // 2020/01/23 QC#55207-1 Add Start
    /** Please choose other Line Category, because you cannot use this Line Category for this order. */
    public static final String NWAM0909E = "NWAM0909E";
    // 2020/01/23 QC#55207-1 Add End

    // 2020/06/09 QC#56978 Add
    /** The Vendor Code [@] has expired, Please review ASL. */
    public static final String NWAM0983E = "NWAM0983E";
    
    // 2023/12/06 QC#61281 K.Ikeda START
    /** You cannot choose payment method "No Credit Card" if it's not Total Amount $0. (Error) */
    public static final String NWZM2324E = "NWZM2324E";
    
    /** You cannot choose payment method "No Credit Card" if it's not Total Amount $0. (Warning) */
    public static final String NWZM2325W = "NWZM2325W";
    // 2023/12/06 QC#61281 K.Ikeda END
    
    // QC#63527 2024/03/14 Start
    /** Loan Order Action (Loan Sell) */
    public static final String LOAN_ORD_ACTION_LOAN_SELL = "LOAN SELL";
    // QC#63527 2024/03/14 End
}
