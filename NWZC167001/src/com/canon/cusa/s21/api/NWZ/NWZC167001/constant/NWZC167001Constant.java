/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC167001.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         T.Murai         Create          N/A
 * 2016/09/07   Fujitsu         H.Ikeda         Update          S21_NA#13918,13919
 * 2016/09/13   Fujitsu         H.Ikeda         Update          S21_NA#13918
 * 2016/09/13   Fujitsu         H.Ikeda         Update          S21_NA#14329
 * 2016/10/03   Fujitsu         H.Ikeda         Update          S21_NA#11595
 * 2016/12/14   Fujitsu         S.Ohki          Update          S21_NA#16344
 * 2018/02/26   Fujitsu         Hd.Sugawara     Update          QC#22967
 * 2018/06/25   Fujitsu         H.Nagashima     Update          QC#23726
 * 2020/06/09   CITS            K.Ogino         Update          QC#56978
 * </pre>
 */
public class NWZC167001Constant {

    /** request Mode 1: Save */
    public static final String MODE_SAVE = "01";

    /** request Mode 2: Submit */
    public static final String MODE_SUBMIT = "02";

    /** request type 1:New */
    public static final String RQST_TP_NEW = "1";

    /** request type 2: Modify */
    public static final String RQST_TP_MOD = "2";

    /** request type 3: Cancel */
    public static final String RQST_TP_CAN = "3";

    /** Parent line : 000 */
    public static final String LINE_NUM_000 = "000";

    /** Parent line : 000 */
    public static final String LINE_SUB_NUM_000 = "000";

    /** first line Num : 001 */
    public static final String LINE_NUM_FIRST = "001";

    /** first line Num : 001 */
    public static final String LINE_SUB_NUM_FIRST = "001";

    /** NUM 100 */
    public static final int IDX_100 = 100;

    /** Position NUM 1 */
    public static final String ORD_POSN_NUM1 = "1";

    /** ORD_CATG_CTX_TP */
    public static final String MDSE_ORD_ENTRY_USED_AVALS = "MDSE_ORD_ENTRY_USED_AVALS";

    /** ORD_CATG_CTX_TP */
    public static final String MDSE_ORD_ENTRY_AVALS = "MDSE_ORD_ENTRY_AVALS";

    /** SPLY_QUOTE Number */
    public static final String SUB_SYS_ID_NWZ = "NWZ";

    /** SUB_SYS_ID */
    public static final String PROC_ID_OM = "OM";

    /** DOC_TP_CD */
    public static final String DOC_TP_CD = "QT";

    /** Quote Sequence Code */
    public static final String QUOTE_SEQ_CD = "QT#";

    /** Comma */
    public static final String COMMA = ".";

    /** DOC_TP_CD :Order Create */
    public static final String EVENT_ORDER_CRAT = "Order Create";

    /** DOC_TP_CD :Quote Create */
    public static final String EVENT_QUOTE_CRAT = "Quote Create";

    /** DOC_TP_CD :Quote charge */
    public static final String EVENT_QUOTE_CHNG = "Quote Change";

    /** DOC_TP_CD :Quote Cancel */
    public static final String EVENT_QUOTE_CANC = "Quote Cancel";

    /** SPLY_QUOTE Number */
    public static final String SPLY_QUOTE_NUM = "SPLY_QUOTE_NUM";

    /** Date Format */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    // 2016/12/14 S21_NA#16344 Add Start
    /** Delyvery Comment Text Max Size */
    public static final int DELY_ADDL_CMNT_TXT_MAX_SIZE = 240;
    // 2016/12/14 S21_NA#16344 Add End

    // Message ID
    /** NWZM0135W : The entered Customer P/O Number is already registered. */
    public static final String NWZM0135W = "NWZM0135W";

    /** NWZM0977E : Invalid value is set for process mode. */
    public static final String NWZM0977E = "NWZM0977E";

    /** NWZM0209E : "Request Type Code" has an invalid value. */
    public static final String NWZM0209E = "NWZM0209E";

    /** NWZM0225E : "Carrier Code" is required. */
    public static final String NWZM0225E = "NWZM0225E";

    /** NWZM0011E : "Data Global Company Code" is required. */
    public static final String NWZM0011E = "NWZM0011E";

    /** NWZM0346E : Sales Date is required. */
    public static final String NWZM0346E = "NWZM0346E";

    /** NWZM0336E : "User ID" is required. */
    public static final String NWZM0336E = "NWZM0336E";

    /** NWZM0458E : Carrier Account Number is required. */
    public static final String NWZM0458E = "NWZM0458E";

    /** NWZM0087E : "Business Application ID" is not entered. */
    public static final String NWZM0087E = "NWZM0087E";

    /** NWZM1401E : "Direct Sales Order Category Code" is not entered. */
    public static final String NWZM1401E = "NWZM1401E";

    /** NWZM1158E : Freight Condition Code of the parameter is not set. */
    public static final String NWZM1158E = "NWZM1158E";

    /** NWZM0529E : "Sales Rep Code" is not set. */
    public static final String NWZM0529E = "NWZM0529E";

    /** NWZM1253E : An input parameter "DS Order Type Code" has not been set. */
    public static final String NWZM1253E = "NWZM1253E";

    /** NWZM1255E : An input parameter "Shipping Service Level Code" has not been set. */
    public static final String NWZM1255E = "NWZM1255E";

    /** NWZM1258E : An input parameter "Currency Code" has not been set. */
    public static final String NWZM1258E = "NWZM1258E";

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

    /** NWZM0149E : The entered Freight Charge To cannot be selected. */
    public static final String NWZM0149E = "NWZM0149E";

    /**
     * NWZM0151E : Wrong combination: Shipping Service Level and Freight Charge To/Freight Charge
     * Method.
     */
    public static final String NWZM0151E = "NWZM0151E";

    /** NWZM0492E : "Merchandise Code" is not set. */
    public static final String NWZM0492E = "NWZM0492E";

    /** NWZM0492E : Order Quantity of the parameter is not set. */
    public static final String NWZM1179E = "NWZM1179E";

    /** NWZM0109E :The "CPO Order Type Code" you entered cannot be found in the master. */
    public static final String NWZM0109E = "NWZM0109E";

    /** NWZM0598E :The entered "Merchandise Code" does not exist in the Master. */
    public static final String NWZM0598E = "NWZM0598E";

    /** NWZM1415E : The entered "DS Order Category Code" does not exist in the Master. */
    public static final String NWZM1415E = "NWZM1415E";

    /** NWZM1416E : The entered "Bill to Account Code" does not exist in the Master. */
    public static final String NWZM1416E = "NWZM1416E";

    /** NWZM1417E : The entered "Ship to Account Code" does not exist in the Master. */
    public static final String NWZM1417E = "NWZM1417E";

    /** NWZM1418E : The entered "Sold to Location Code" does not exist in the Master. */
    public static final String NWZM1418E = "NWZM1418E";

    /** NWZM0054E : The entered "Sales Rep Code" does not exist in the Master. */
    public static final String NWZM0054E = "NWZM0054E";

    /** NWZM1419E : The entered "Price Category Code" does not exist in the Master. */
    public static final String NWZM1419E = "NWZM1419E";

    /** NWZM1420E : The entered "Floor Price List Code" does not exist in the Master. */
    public static final String NWZM1420E = "NWZM1420E";

    /** NWZM1421E : The entered "Association Program Code" does not exist in the Master. */
    public static final String NWZM1421E = "NWZM1421E";

    /** NWZM1426E : The entered "Freight Condition Code" does not exist in the Master. */
    public static final String NWZM1426E = "NWZM1426E";

    /** NWZM1427E : The entered "Carrier Service Level Code" does not exist in the Master. */
    public static final String NWZM1427E = "NWZM1427E";

    /** NWZM1428E : The entered "Special Handling Type Code" does not exist in the Master. */
    public static final String NWZM1428E = "NWZM1428E";

    /** NWZM1431E : The entered "CPO Line Category Code" does not exist in the Master. */
    public static final String NWZM1431E = "NWZM1431E";

    /** NWZM1432E : The entered "Order Line Source Code" does not existin the Master. */
    public static final String NWZM1432E = "NWZM1432E";

    /** NWZM1433E : The entered "Retail Warehouse Code" does not exist in the Master. */
    public static final String NWZM1433E = "NWZM1433E";

    /** NWZM1434E : The entered "Retail Sub Warehouse Code" does not exist in the Master. */
    public static final String NWZM1434E = "NWZM1434E";

    /** NWZM1444E : The entered "Bill to Location Code" does not exist in the Master. */
    public static final String NWZM1444E = "NWZM1444E";

    /** NWZM1445E : The entered "Ship to Location Code" does not exist in the Master. */
    public static final String NWZM1445E = "NWZM1445E";

    /** NWZM1446E : The entered "Ship to State Code" does not exist in the Master. */
    public static final String NWZM1446E = "NWZM1446E";

    /** NWZM1448E : The entered "Ship to Country Code" does not exist in the Master. */
    public static final String NWZM1448E = "NWZM1448E";

    /** NWZM1449E : It failed to get the Default Carrier Service Level. */
    public static final String NWZM1449E = "NWZM1449E";

    /** NWZM1450E : The relationship between 'Order Category' and 'Order Reason' is incorrect. */
    public static final String NWZM1450E = "NWZM1450E";

    /** NWZM1451E : The relationship between 'Order Reason' and 'Order Sub Reason' is incorrect. */
    public static final String NWZM1451E = "NWZM1451E";

    /**
     * NWZM1452E : The relationship between 'Bill To Customer Location Code' and 'Bill to customer
     * Account Code' is incorrect
     */
    public static final String NWZM1452E = "NWZM1452E";

    /**
     * NWZM1453E : The relationship between 'Add Ship To Customer Code' and 'Ship to Customer
     * Account Code' is incorrect.
     */
    public static final String NWZM1453E = "NWZM1453E";

    /**
     * NWZM1454E : The relationship between 'Sold to customer Location Code' and 'Sell To Customer
     * Account Code' is incorrect.
     */
    public static final String NWZM1454E = "NWZM1454E";

    // Del Start 2018/02/26 QC#22967
    ///**
    // * NWZM1455E : The relationship between 'Bill to', 'Ship to' and 'Sold to' is incorrect.
    // */
    //public static final String NWZM1455E = "NWZM1455E";
    // Del End 2018/02/26 QC#22967

    /**
     * NWZM1456E : The relationship between 'Freight Condition Code', 'Freight Charge To Code' and
     * 'Freight Charge Method Code' is incorrect.
     */
    public static final String NWZM1456E = "NWZM1456E";

    /**
     * NWZM1458E : The relationship between 'Freight Condition Code', 'LOB', 'Carrier Service Level
     * Code' and 'Add Shipping Service Level Code' is incorrect.
     */
    public static final String NWZM1458E = "NWZM1458E";

    /** NWZM1459E : If Freight Condition Code is 'Collect', Carrier Account Number must be entered. */
    public static final String NWZM1459E = "NWZM1459E";

    /**
     * NWZM1460E : If Add Payment Term Cash Discount Code is 'Credit Card', DS Credit Card PK must
     * be entered.
     */
    public static final String NWZM1460E = "NWZM1460E";

    /**
     * NWZM1463E : The relationship between 'Sales Rep' and 'Business Area Organization' is
     * incorrect.
     */
    public static final String NWZM1463E = "NWZM1463E";

    /**
     * NWZM1464E : The relationship between 'Price Category' and 'Order Type Process Definition' is
     * incorrect.
     */
    public static final String NWZM1464E = "NWZM1464E";

    /**
     * NWZM1465E : The relationship between 'Floor Price List' and 'Order Type Process Definition'
     * is incorrect.
     */
    public static final String NWZM1465E = "NWZM1465E";

    /**
     * NWZM1468E : The relationship between 'Merchandise Code' and 'Customer Merchandise Code' is
     * incorrect.
     */
    public static final String NWZM1468E = "NWZM1468E";

    /**
     * NWZM1469E : The relationship between 'CPO Order Type Code' and 'CPO Line Category Code' is
     * incorrect.
     */
    public static final String NWZM1469E = "NWZM1469E";

    /**
     * NWZM1470E : The relationship between 'Retail Warehouse Code', 'Retail Sub Warehouse Code' and
     * 'CPO Order Type Code' is incorrect.
     */
    public static final String NWZM1470E = "NWZM1470E";

    /**
     * NWZM1478E : You can not set the process for the warehouse to the specified CPO Line Category
     * Code.
     */
    public static final String NWZM1478E = "NWZM1478E";

    /** NWZM1486E : Order qty should be greater than 0. */
    public static final String NWZM1486E = "NWZM1486E";

    /** NWZM1488E : Order qty should be equal to minimum qty or more. */
    public static final String NWZM1488E = "NWZM1488E";

    /** NWZM1489E : Order qty should be equal to maximum qty or less. */
    public static final String NWZM1489E = "NWZM1489E";

    /** NWZM1492E : Order qty should be multiple of increment qty. */
    public static final String NWZM1492E = "NWZM1492E";

    /** NWZM0028E : Sales cannot be specified as Order Type for Internal Customer. */
    public static final String NWZM0028E = "NWZM0028E";

    /** NWZM0037E : The item is on selling hold. */
    public static final String NWZM0037E = "NWZM0037E";

    /** NWZM0038E : The merchandise you specified cannot be sold as a single item. */
    public static final String NWZM0038E = "NWZM0038E";

    /** NWZM0039E : The country of origin for the specified merchandise is not permitted. */
    public static final String NWZM0039E = "NWZM0039E";

    /** NWZM0248E : Stock Status is not valid. */
    public static final String NWZM0248E = "NWZM0248E";

    /** NWZM0147E : RDD cannot be entered for Pick-up. */
    public static final String NWZM0147E = "NWZM0147E";

    /** NWZM0417E : Stock Status is not valid. */
    public static final String NWZM0417E = "NWZM0417E";

    /** NWZM0420E : An embargoed country has been specified as 'Ship To' country. */
    public static final String NWZM0420E = "NWZM0420E";

    /** NWZM1138E : Contact Person does not exist in master. */
    public static final String NWZM1138E = "NWZM1138E";

    /** NWZM1262E : The entered 'Warehouse' is not ready for Order. */
    public static final String NWZM1262E = "NWZM1262E";

    /** NWZM1503E : "DROP_SHIP_RTL_WH_CD" doesn't exist in the VAR_CHAR_CONST. */
    public static final String NWZM1503E = "NWZM1503E";

    /** NWZM1796E : "Supply Quote Number" is required. */
    public static final String NWZM1796E = "NWZM1796E";

    /** NWZM1797E : "Supply Quote Category" is required. */
    public static final String NWZM1797E = "NWZM1797E";

    /** NWZM1798E : "Supply Quote Source Type Code" is required. */
    public static final String NWZM1798E = "NWZM1798E";

    /** NWZM1799E : "Pricing Base Date" is required. */
    public static final String NWZM1799E = "NWZM1799E";

    /** NWZM1800E : "Supply Quote Detail Line Number" is required. */
    public static final String NWZM1800E = "NWZM1800E";

    /** NWZM1801E : "Supply Quote Detail Line Sub Number" is required. */
    public static final String NWZM1801E = "NWZM1801E";

    /** NWZM1802E : "Supply Quote Number" cannot be entered if Request Type is New. */
    public static final String NWZM1802E = "NWZM1802E";

    /** NWZM1803E : "Supply Quote Sales Credit Primary Key" is required if Request Type is not New. */
    public static final String NWZM1803E = "NWZM1803E";

    /**
     * NWZM1804E : "Supply Quote Contact Person Primary Key" is required if Request Type is not New.
     */
    public static final String NWZM1804E = "NWZM1804E";

    /** NWZM1805E : "Contact Person Primary Key" is required if Request Type is not New. */
    public static final String NWZM1805E = "NWZM1805E";

    /** NWZM1806E : "Contact Person Type Code" is required. */
    public static final String NWZM1806E = "NWZM1806E";

    /** NWZM1807E : "Contact Person First Name" is required. */
    public static final String NWZM1807E = "NWZM1807E";

    /** NWZM1808E : "DS Contact Point Primary Key 01" is required if Request Type is not New. */
    public static final String NWZM1808E = "NWZM1808E";

    /** NWZM1809E : "DS Contact Point Primary Key 02" is required if Request Type is not New. */
    public static final String NWZM1809E = "NWZM1809E";

    /** NWZM1810E : "DS Contact Point Primary Key 03" is required if Request Type is not New. */
    public static final String NWZM1810E = "NWZM1810E";

    /** NWZM1811E : "DS Contact Point Primary Key 04" is required if Request Type is not New. */
    public static final String NWZM1811E = "NWZM1811E";

    /** NWZM1812E : There is different between set product's parent and child Request Type. */
    public static final String NWZM1812E = "NWZM1812E";

    /** NWZM1813E : The entered "Quote Category Code" does not exist in the Master. */
    public static final String NWZM1813E = "NWZM1813E";

    /** NWZM1814E : The entered "Quote Source Type Code" does not exist in the Master. */
    public static final String NWZM1814E = "NWZM1814E";

    /** NWZM1815E : The entered "DS Order Type Code" does not exist in the Master. */
    public static final String NWZM1815E = "NWZM1815E";

    /** NWZM1816E : The entered "DS Order Reason" does not exist in the Master. */
    public static final String NWZM1816E = "NWZM1816E";

    /** NWZM1817E : The entered "Sell To Account Code" does not exist in the Master. */
    public static final String NWZM1817E = "NWZM1817E";

    /** NWZM1818E : The entered "Admin Person Code" does not exist in the Master. */
    public static final String NWZM1818E = "NWZM1818E";

    /** NWZM1819E : The entered "Quote Submit Person Code" does not exist in the Master. */
    public static final String NWZM1819E = "NWZM1819E";

    /** NWZM1820E : The entered "Carrier Code" does not exist in the Master. */
    public static final String NWZM1820E = "NWZM1820E";

    /** NWZM1821E : The entered "Payment Term Code" does not exist in the Master. */
    public static final String NWZM1821E = "NWZM1821E";

    /** NWZM1822E : The entered "Shipping Service Level Code" does not exist in the Master. */
    public static final String NWZM1822E = "NWZM1822E";

    /** NWZM1823E : The entered "Freight Charge To Code" does not exist in the Master. */
    public static final String NWZM1823E = "NWZM1823E";

    /** NWZM1824E : The entered "Freight Charge Method Code" does not exist in the Master. */
    public static final String NWZM1824E = "NWZM1824E";

    /** NWZM1825E : The entered "DS Sales Payment Method " does not exist in the Master. */
    public static final String NWZM1825E = "NWZM1825E";

    /** NWZM1826E : The entered "Customer UOM Code" does not exist in the Master. */
    public static final String NWZM1826E = "NWZM1826E";

    /** NWZM1827E : The entered "Inventory Location Code" does not exist in the Master. */
    public static final String NWZM1827E = "NWZM1827E";

    /** NWZM1828E : The entered " Currency Code" does not exist in the Master. */
    public static final String NWZM1828E = "NWZM1828E";

    /** NWZM1829E : The entered " Stock Status Code" does not exist in the Master. */
    public static final String NWZM1829E = "NWZM1829E";

    /** NWZM1830E : The entered "Merchandise Freight Class" does not exist in the Master. */
    public static final String NWZM1830E = "NWZM1830E";

    /** NWZM1831E : The entered "Merchandise Pricing Group" does not exist in the Master. */
    public static final String NWZM1831E = "NWZM1831E";

    /** NWZM1832E : The entered "Sales Rep Role Type Code" does not exist in the Master. */
    public static final String NWZM1832E = "NWZM1832E";

    /** NWZM1833E : The entered "Contact Type Code" does not exist in the Master. */
    public static final String NWZM1833E = "NWZM1833E";

    /** NWZM1834E : The entered "State Code" does not exist in the Master. */
    public static final String NWZM1834E = "NWZM1834E";

    /** NWZM1835E : The entered "Country Code" does not exist in the Master. */
    public static final String NWZM1835E = "NWZM1835E";

    /** NWZM1836E : "Customer Issue PO Number" is required. */
    public static final String NWZM1836E = "NWZM1836E";

    /** NWZM1837E : "Customer Issue PO Date" is required. */
    public static final String NWZM1837E = "NWZM1837E";

    /** NWZM1838E : Payment Credit Card is required. */
    public static final String NWZM1838E = "NWZM1838E";

    /** NWZM1839E : Payment Credit Card or Payment Cash is required. */
    public static final String NWZM1839E = "NWZM1839E";

    /** NWZM1870E : It failed to insert the SPLY_QUOTE. */
    public static final String NWZM1888E = "NWZM1888E";

    /** NWZM1871E : It failed to update the SPLY_QUOTE. */
    public static final String NWZM1871E = "NWZM1871E";

    /** NWZM1872E : It failed to insert the SPLY_QUOTE_DTL. */
    public static final String NWZM1872E = "NWZM1872E";

    /** NWZM1873E : It failed to update the SPLY_QUOTE_DTL. */
    public static final String NWZM1873E = "NWZM1873E";

    /** NWZM1874E : It failed to insert the SPLY_QUOTE_PRC_CALC_BASE. */
    public static final String NWZM1874E = "NWZM1874E";

    /** NWZM1875E : It failed to update the SPLY_QUOTE_PRC_CALC_BASE. */
    public static final String NWZM1875E = "NWZM1875E";

    /** NWZM1876E : It failed to insert the SPLY_QUOTE_SLS_CR. */
    public static final String NWZM1876E = "NWZM1876E";

    /** NWZM1877E : It failed to update the SPLY_QUOTE_SLS_CR. */
    public static final String NWZM1877E = "NWZM1877E";

    /** NWZM1878E : It failed to insert the SPLY_QUOTE_CTAC_PSN. */
    public static final String NWZM1878E = "NWZM1878E";

    /** NWZM1879E : It failed to update the SPLY_QUOTE_CTAC_PSN. */
    public static final String NWZM1879E = "NWZM1879E";

    /** NWZM1880E : No applicable data was found in SPLY_QUOTE. */
    public static final String NWZM1880E = "NWZM1880E";

    /** NWZM1881E : No applicable data was found in SPLY_QUOTE_DTL. */
    public static final String NWZM1881E = "NWZM1881E";

    /** NWZM1882E : No applicable data was found in SPLY_QUOTE_PRC_CALC_BASE. */
    public static final String NWZM1882E = "NWZM1882E";

    /** NWZM1883E : No applicable data was found in SPLY_QUOTE_SLS_CR. */
    public static final String NWZM1883E = "NWZM1883E";

    /** NWZM1884E : No applicable data was found in SPLY_QUOTE_CTAC_PSN. */
    public static final String NWZM1884E = "NWZM1884E";

    /** NWZM1885E : This applicable data already exists in SPLY_QUOTE_DTL. */
    public static final String NWZM1885E = "NWZM1885E";

    /** NWZM1886E : This applicable data already exists in SPLY_QUOTE_CALC_BASE. */
    public static final String NWZM1886E = "NWZM1886E";

    /** NWZM1933E : It failed to delete the SPLY_QUOTE_SLS_CR. */
    public static final String NWZM1933E = "NWZM1933E";

    /** NWZM1934E : It failed to delete the SPLY_QUOTE_CTAC_PSN. */
    public static final String NWZM1934E = "NWZM1934E";

    /** NWZM2005E : Entered 'PaymentMethod' doesn't agree with 'Payment Term'. */
    public static final String NWZM2005E = "NWZM2005E";
    // QC#13918,13919 2016/09/07 Add Start
    /** NWZM1530E : It failed to get the EASY_PACK type code. */
    public static final String NWZM1530E = "NWZM1530E";
    // QC#13918 2016/09/13 Del Start
    ///** NWZM1532E : It failed to get the Supply Program Contract. */
    //public static final String NWZM1532E = "NWZM1532E";
    // QC#13918 2016/09/13 Del End
    // QC#13918,13919 2016/09/07 Add End

    // QC#13918 2016/09/13 Add Start
    /** NWZM2008E : The customer doesn't have an active Easy Pac I contract. */
    public static final String NWZM2008E = "NWZM2008E";
    // QC#13918 2016/09/13 Add End

    // QC#14329 2016/09/30 Add Start
    /** NWZM2026E : Credit Card information is not entered as Payment Method is "Credit Card". */
    public static final String NWZM2026E = "NWZM2026E";
    // QC#14329 2016/09/30 Add End

    // QC#11595 2016/10/03 Add Start
    /** If Freight Condition Code is 'Collect', Carrier Service Level must be entered. */
    public static final String NWZM2010E = "NWZM2010E";
    // QC#11595 2016/10/03 Add End

    // Add Start 2018/02/26 QC#22967
    /** NWZM2254E : The relationship between 'Ship To' and 'Sold To' is incorrect. */
    public static final String NWZM2254E = "NWZM2254E";

    /** NWZM2255E : The relationship between 'Sold To' and 'Bill To' is incorrect. */
    public static final String NWZM2255E = "NWZM2255E";
    // Add End 2018/02/26 QC#22967

    // QC#23726 2018/06/25 add Start
    /** NWZM2267E : The relationship between 'Line Of Business', 'Ship To Account' and 'Carrier Service Level' is incorrect. */
    public static final String NWZM2267E = "NWZM2267E";
    // QC#23726 2018/06/25 add End

 // 2020/06/09 QC#56978 Add
    /** The Vendor Code [@] has expired, Please review ASL. */
    public static final String NWAM0983E = "NWAM0983E";
    
}
