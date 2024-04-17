/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2200.constant;

/**
 *<pre>
 * NWAL2200Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2016/08/31   CITS            S.Tanikawa      Update          Unit Test#202
 * 2017/02/14   Fujitsu         M.Ohno          Update          QC#17302
 * 2017/03/30   Fujitsu         S.Ohki          Update          QC#18175
 * 2017/06/27   Fujitsu         H.Sugawara      Update          QC#18363
 * 2018/01/23   Fujitsu         T.Aoi           Update          QC#18798(Sol#173)
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NWAL2200Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL2200";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The entered @ does not exist in Master. */
    public static final String NWAM0181E = "NWAM0181E";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** @ is incorrect value. */
    public static final String NWAM0300E = "NWAM0300E";

    /** It does not exist in {@} table. */
    public static final String NWAM0809E = "NWAM0809E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** NWZM1401E : "Direct Sales Order Category Code" is not entered. */
    public static final String NWZM1401E = "NWZM1401E";

    /** @ must be selected. */
    public static final String NWAM0667E = "NWAM0667E";

    /** Multiple Details cannot be processed at the same time. */
    public static final String NWAM0683E = "NWAM0683E";

    /** Order qty of serialized item should be 1. */
    public static final String NWAM0772E = "NWAM0772E";

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

    /** <pre>NWZM1794E : Please select Existing IB or Add To Config as Config Type.</pre> */
    public static final String NWZM1794E = "NWZM1794E";

    /** <pre>NWZM1795E : This item is unable to be included in this configuration.</pre> */
    public static final String NWZM1795E = "NWZM1795E";

    /** <pre>NWZM1887E : The entered Config ID isn't located at customer site.</pre> */
    public static final String NWZM1887E = "NWZM1887E";

    /** <pre>NWZM1889E : The system couldn't obtain Transaction Code and Transaction Reason Code. Please confirm the setting.</pre> */
    public static final String NWZM1889E = "NWZM1889E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** Data insert failure. (table name is [@]) */
    public static final String NWAM0728E = "NWAM0728E";

    /** Data update failure. (table name is [@]) */
    public static final String NWAM0729E = "NWAM0729E";

    /** Data delete failure. (table name is [@]) */
    public static final String NWAM0730E = "NWAM0730E";

    /** It failed to register Mail. */
    public static final String NWAM0268E = "NWAM0268E";

    /** The merchandise does not exist in Master  or "Inactive" item. */
    public static final String NWAM2201E = "NWAM2201E";

    // UPDATE START 2016/08/31 Unit Test#202
    /** Since the customer is prospect customer please update account information. */
    public static final String NWAM0883E = "NWAM0883E";
    // UPDATE END 2016/08/31 Unit Test#202

    // 09/16/2016 S21_NA#12145 add Start
    /** Base Price could not be obtained.{@}, {@}. */
    public static final String NWZM1328E = "NWZM1328E";
    // 09/16/2016 S21_NA#12145 add End

    // 01/17/2017 QC#17124 add Start
    /** Ship To has not been entered. */
    public static final String NWAM0021E = "NWAM0021E";
    
    /** Bill To has not been entered. */
    public static final String NWAM0235E = "NWAM0235E";
    // 01/17/2017 QC#17124 add End
    
    // 2017/02/09 S21_NA#17302 Add Start
    /** Please set the Account Cross Reference Info to derive the Ship To Account and Location. */
    public static final String NWAM0929E = "NWAM0929E";
    // 2017/02/09 S21_NA#17302 Add End

    // 2017/03/30 S21_NA#18175 Add Start
    /** "UOM" does not exist in the Master. */
    public static final String NWAM0935E = "NWAM0935E";
    // 2017/03/30 S21_NA#18175 Add End

    // --------------------------------
    // TAB name
    // --------------------------------

    /**
     * TAB name additional header
     */
    public static final String TAB_HEADER = "Header";

    /**
     * TAB name additional header
     */
    public static final String TAB_ADDL_HEADER = "Addl_Header";

    /**
     * TAB name line configuration
     */
    public static final String TAB_LINE_CONFIG = "Line_Config";

    /**
     * TAB name RMA
     */
    public static final String TAB_RMA = "RMA";

    /**
     * TAB name Errors
     */
    public static final String TAB_ERRORS = "Errors";

    /**
     * Header Id - Line
     */
    public static final String HDR_ID_LINE = "AHEAD";

    /**
     * Header Id - RMA
     */
    public static final String HDR_ID_RMA = "CHEAD";

    /**
     * Header Id - Errors
     */
    public static final String HDR_ID_ERRORS = "EHEAD";

    // --------------------------------
    // mail
    // --------------------------------

    /**
     * mail from id
     */
    public static final String MAIL_FROM_REJECT = "FROM0005";

    /**
     * mail template id
     */
    public static final String MAIL_TMPL_REJECT = "NWAL2200M001";

    // --------------------------------
    // other
    // --------------------------------

    /** Period */
    public static final String PERIOD = ".";

    /** Comma */
    public static final String COMMA = ",";

    /** Blank */
    public static final String BLANK = "";

    /** Space */
    public static final String SPACE = " ";

    /** ORD_CATG_BIZ_CTX : EQUIPMENT_ORDER */
    public static final String EQUIPMENT_ORDER = "EQUIPMENT_ORDER";

    /** ORD_CATG_BIZ_CTX : SUPPLIES_ORDER */
    public static final String SUPPLIES_ORDER = "SUPPLIES_ORDER";

    /** Pricing Package UOM Varchar Const Name */
    public static final String PKG_UOM_FOR_PRC = "PKG_UOM_FOR_PRC";

    /** Error level : header */
    public static final String ERROR_LEVEL_HEADER = "Header";

    /** Error level : configuration */
    public static final String ERROR_LEVEL_CONFIG = "Config";

    /** Error level : line */
    public static final String ERROR_LEVEL_LINE = "Line";

    /** Error level : RMA */
    public static final String ERROR_LEVEL_RMA = "RMA";

    // UPDATE START 2016/08/31 Unit Test#202
    /** Address Mass Apply */
    public static final String EVENT_NM_NMAL2340_LINE = "OpenWin_AddressMassApplyDtl";

    /** Address Mass Apply */
    public static final String EVENT_NM_NMAL2340_RMA = "OpenWin_AddressMassApplyRma";
    // UPDATE END 2016/08/31 Unit Test#202

    // Add Start 2017/06/27 QC#18363
    // --------------------------------
    // Valuable
    // --------------------------------
    /** MODE_REFERENCE = "10" */
    public static final String MODE_REFERENCE = "10";
    // Add End 2017/06/27 QC#18363

    // 2018/01/23 QC#18798 Add Start
    public static final String XX_MODE_CD_EMSD = "01";
    // 2018/01/23 QC#18798 Add End

    // 2020/04/27 QC#56638 Add Start
    /** Sales Rep is not assigned to the specified Ship-To customer. */
    public static final String NWAM0757W = "NWAM0757W";

    /** Sales Rep is not assigned to the specified Sold-To customer. */
    public static final String NWAM0981W = "NWAM0981W";
    // 2020/04/27 QC#56638 Add End

    // 2023/11/06 QC#61924 Add Start
    /** Bill To# {@} is deactivated for new transactions. */
    public static final String NWAM0987E = "NWAM0987E";

    /** Ship To# {@} is deactivated for new transactions. */
    public static final String NWAM0988E = "NWAM0988E";

    /** Sold To# {@} is deactivated for new transactions. */
    public static final String NWAM0989E = "NWAM0989E";
    // 2023/11/06 QC#61924 Add End
}
