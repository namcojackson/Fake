/**
 *  <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC006001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * One Time Invoice API
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/03/2013   Fujitsu         S.Nakai         Create          N/A
 * 06/13/2013   SRAA            Y.Chen          Update          ISSUE#48
 * 08/28/2013   Hitachi        Y.Igarashi       Update          QC1943
 * 09/28/2015   Hitachi         A.Kohinata      Update          NA#
 * 08/16/2017   Hitachi         K.Kim           Update          QC#19406
 *</pre>
 */
public interface NSZC006001Constant {

    /** Input parameter "Global Company Code" is a mandatory field. */
    String NSZM0001E = "NSZM0001E";
    /** Input parameter "Merchandise Code" is a mandatory field. */
    String NSZM0013E = "NSZM0013E";
    /** Input parameter "Bill To Code" is a mandatory field. */
    String NSZM0015E = "NSZM0015E";
    /** Input parameter "Mode Code" is a mandatory field. */
    String NSZM0122E = "NSZM0122E";
    /** Input parameter "Sell To Code" is a mandatory field. */
    String NSZM0016E = "NSZM0016E";
    /** Input parameter "Service Machine Master PK" is a mandatory field. */
    String NSZM0074E = "NSZM0074E";
    /** Input parameter "Invoice Currency Code" is a mandatory field. */
    String NSZM0070E = "NSZM0070E";
    /**
     * Input parameter "Original Invoice Number" is a mandatory field when the mode code is "Correct".
     */
    String NSZM0123E = "NSZM0123E";
    /** Input parameter "FSR#" is a mandatory field. */
    String NSZM0124E = "NSZM0124E";
    /** Input parameter "FSR Visit#" is a mandatory field. */
    String NSZM0125E = "NSZM0125E";
    /** Input parameter "Payment Term Cash Discount Code" is a mandatory field. */
    String NSZM0126E = "NSZM0126E";
    /** "Invoice Line List" for the entered parameter has not been set up. */
    String NSZM0174E = "NSZM0174E";
    /** Target FSR# data does not exist .TABLE ID [FSR] */
    String NSZM0091E = "NSZM0091E";
    /** Target FSR_VISIT# data does not exist .TABLE ID [FSR_VISIT] */
    String NSZM0092E = "NSZM0092E";
    /** The corresponding data does not exist in "PMT_TERM_CASH_DISC". */
    String NSZM0093E = "NSZM0093E";
    /** The corresponding data does not exist in "PMT_TERM". */
    String NSZM0094E = "NSZM0094E";
    /** The corresponding data does not exist in "REMIT_TO". */
    String NSZM0095E = "NSZM0095E";
    /**
     * The conversion rate between the functional and trading currencies could
     * not be obtained.
     */
    String NSZM0096E = "NSZM0096E";
    /** Please specify other than 0 for the exchange rate. */
    String NSZM0097E = "NSZM0097E";
    /** The corresponding data does not exist in "CUST_INV_REQ". */
    String NSZM0098E = "NSZM0098E";
    /** The corresponding data does not exist in "CCY". */
    String NSZM0099E = "NSZM0099E";
    /** The corresponding data does not exist in "Target MDL_MDSE_RELN_V". */
    String NSZM0100E = "NSZM0100E";
    /** The corresponding data does not exist in "S21_ORG". */
    String NSZM0101E = "NSZM0101E";
    /** The corresponding data does not exist in "VAR_CHAR_CONST". */
    String NSZM0102E = "NSZM0102E";
    /** The corresponding data does not exist in "COA_PROD". */
    String NSZM0103E = "NSZM0103E";
    /** The corresponding data does not exist in "CUST_INV_REQ". */
    String NSZM0104E = "NSZM0104E";
    /** The corresponding data does not exist in "MDSE". */
    String NSZM0105E = "NSZM0105E";
    /** Input parameter "Invoice Quantity" is a mandatory field. */
    String NSZM0128E = "NSZM0128E";
    /** Input parameter "Unit Hos Aot" is a mandatory field. */
    String NSZM0129E = "NSZM0129E";
    /** Input parameter "Unit Deal Amount" is a mandatory field. */
    String NSZM0130E = "NSZM0130E";
    /** Input parameter "Invoice Line Deal Sales Amount" is a mandatory field. */
    String NSZM0131E = "NSZM0131E";
    /** Input parameter "Invoice Deal Amount" is a mandatory field. */
    String NSZM0132E = "NSZM0132E";
    /** Input parameter "Sales Rep Code" is a mandatory field. */
    String NSZM0133E = "NSZM0133E";
    /** Input parameter "Trx Code" is a mandatory field. */
    String NSZM0134E = "NSZM0134E";
    /** Input parameter "Trx Reason Code" is a mandatory field. */
    String NSZM0135E = "NSZM0135E";
    /** The corresponding data does not exist in "SVC_INV". */
    String NSZM0136E = "NSZM0136E";
    /** The corresponding data does not exist in "SVC_INV_LINE". */
    String NSZM0137E = "NSZM0137E";
    /** It failed to register to the SVC_INV table. */
    String NSZM0138E = "NSZM0138E";
    /** It failed to register to the SVC_INV_LINE table. */
    String NSZM0139E = "NSZM0139E";
    /** The corresponding data does not exist in "CNTY". */
    String NSZM0140E = "NSZM0140E";
    /** It failed to assign a Service Invoice. */
    String NSZM0141E = "NSZM0141E";
    /** The corresponding data does not exist in "BILL_TO_CUST". */
    String NSZM0142E = "NSZM0142E";
    /** It failed to assign a Service Invoice Line. */
    String NSZM0143E = "NSZM0143E";
    /** Input Parameter xxModeCd is not correct. */
    String NSZM0175E = "NSZM0175E";
    /** Input parameter "Service Charge Type Code" is a mandatory field. */
    String NSZM0176E = "NSZM0176E";
    /** Input parameter "Tax Rate" or "Ship To Cust Cd" is a mandatory field. */
    String NSZM0177E = "NSZM0177E";
    /** "Inv Line Deal Disc Amt" is not set to the parameter. */
    String NSZM0178E = "NSZM0178E";
    /** "Deal Disc Unit Amt" is not set to the parameter. */
    String NSZM0179E = "NSZM0179E";
    /** It failed to insert the FSR_EVEINT. */
    String NSZM0173E = "NSZM0173E";
    /** It exceeds the maximum number of digits. <Table:[SVC_INV_LINE], Field:[INV_LINE_DEAL_SLS_AMT]> */
    String NSZM0106E = "NSZM0106E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV_LINE], Field:[INV_LINE_FUNC_SLS_AMT]> */
    String NSZM0107E = "NSZM0107E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV_LINE], Field:[INV_LINE_NET_DEAL_AMT]> */
    String NSZM0108E = "NSZM0108E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV_LINE], Field:[INV_LINE_NET_FUNC_AMT]> */
    String NSZM0109E = "NSZM0109E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV_LINE], Field:[INV_LINE_DEAL_TAX_AMT]> */
    String NSZM0110E = "NSZM0110E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV_LINE], Field:[INV_LINE_FUNC_TAX_AMT]> */
    String NSZM0111E = "NSZM0111E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV_LINE], Field:[INV_LINE_DISC_DEAL_AMT]> */
    String NSZM0112E = "NSZM0112E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV_LINE], Field:[INV_LINE_DISC_FUNC_AMT]> */
    String NSZM0113E = "NSZM0113E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV], Field:[INV_TOT_DEAL_SLS_AMT]> */
    String NSZM0114E = "NSZM0114E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV], Field:[INV_TOT_FUNC_SLS_AMT]> */
    String NSZM0115E = "NSZM0115E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV], Field:[INV_TOT_DEAL_TAX_AMT]> */
    String NSZM0116E = "NSZM0116E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV], Field:[INV_TOT_FUNC_TAX_AMT]> */
    String NSZM0117E = "NSZM0117E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV], Field:[INV_TOT_DEAL_DISC_AMT]> */
    String NSZM0118E = "NSZM0118E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV], Field:[INV_TOT_FUNC_DISC_AMT]> */
    String NSZM0119E = "NSZM0119E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV], Field:[INV_TOT_DEAL_NET_AMT]> */
    String NSZM0120E = "NSZM0120E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV], Field:[INV_TOT_FUNC_NET_AMT]> */
    String NSZM0121E = "NSZM0121E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV_LINE], Field:[INV_LINE_FUNC_UNIT_PRC_AMT]> */
    String NSZM0375E = "NSZM0375E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV_LINE], Field:[INV_LINE_DEAL_UNIT_PRC_AMT]> */
    String NSZM0376E = "NSZM0376E";
    /** The corresponding data does not exist in "SELL_TO_CUST". */
    String NSZM0190E = "NSZM0190E";
    /** The entered 'Ship To Customer Code' does not exist. */
    String NSZM0084E = "NSZM0084E";
    /** Intangible Mdse Cd cannot be obtained. */
    String NSZM0377E = "NSZM0377E";
    /** It failed to assign a Service Invoice Line Alloc. */
    String NSZM0378E = "NSZM0378E";
    /** It failed to register to the SVC_INV_LINE_ALLOC table. */
    String NSZM0379E = "NSZM0379E";
    /** The corresponding data does not exist in "GLBL_CMPY". */
    String NSZM0380E = "NSZM0380E";
    /** The corresponding data does not exist in "CUST_CR_PRFL or BR". */
    String NSZM0381E = "NSZM0381E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV_LINE_ALLOC], Field:[DEAL_GRS_UNIT_PRC_AMT]> */
    String NSZM0382E = "NSZM0382E";
    /** It exceeds the maximum number of digits.<Table:[SVC_INV_LINE_ALLOC], Field:[FUNC_GRS_UNIT_PRC_AMT]> */
    String NSZM0383E = "NSZM0383E";
    // Add Start 09/28/2015
    /** The corresponding data does not exist in "SVC_MACH_MSTR". */
    String NSZM0638E = "NSZM0638E";
    /** The corresponding data does not exist in "DS_CONTR". */
    String NSZM0639E = "NSZM0639E";
    /** The corresponding data does not exist in "TOC". */
    String NSZM0640E = "NSZM0640E";
    /** The corresponding data does not exist in "SHIP_TO_CUST". */
    String NSZM0642E = "NSZM0642E";
    /** The corresponding data does not exist in "SVC_INV_CHRG_TP". */
    String NSZM0643E = "NSZM0643E";
    /** Input parameter "UOM Code" is a mandatory field. */
    String NSZM0644E = "NSZM0644E";
    /** Input parameter "Service Labor Unit Amount" is a mandatory field. */
    String NSZM0645E = "NSZM0645E";
    /** Input parameter "Service Travel Unit Amount" is a mandatory field. */
    String NSZM0646E = "NSZM0646E";
    /** Failed to insert the SVC_INV_LINE_TAX_DTL. */
    String NSZM0651E = "NSZM0651E";
    // Add End 09/28/2015
    // START 2017/08/16 K.Kim [QC#19406, ADD]
    /** The corresponding data does not exist in "CTAC_PSN". */
    String NSZM1293E = "NSZM1293E";
    // END 2017/08/16 K.Kim [QC#19406, ADD]

    /** SQL Bind Name: glblCmpyCd */
    String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** SQL Bind Name: mdseCd */
    String BIND_MDSE_CD = "mdseCd";

    /** SQL Bind Name: custCd */
    String BIND_CUST_CD = "custCd";

    /** SQL Bind Name: vndCd */
    String BIND_VND_CD = "vndCd";

    /** SQL Bind Name: poShptMethCd */
    String BIND_PO_SHPG_METH_CD = "poShpgMethCd";

    /** SQL Bind Name: poDelyPrtyCd */
    String BIND_PO_DELY_PRTY_CD = "poDelyPrtyCd";

    /** Column Name: MDL_ID */
    String MDL_ID = "MDL_ID";

    /** SQL Bind Name: MDL_NM */
    String MDL_NM = "MDL_NM";

    /** KEY_EXCH_RATE */
    String KEY_EXCH_RATE = NSZC006001Constant.class.getName() + "KEY_EXCH_RATE";

    /** KEY_GLBL_CMPY */
    String KEY_GLBL_CMPY = NSZC006001Constant.class.getName() + "KEY_GLBL_CMPY";

    /** VAR_CHAR_CONST: NPAL0110_WH_DS_LIST */
    String KEY_NPAL0110_WH_DS_LIST = "NPAL0110_WH_DS_LIST";

    /** String: Comma */
    String COMMA = ",";

    /** header Info */
    int HEADER = -1;

    /** bulkCnt */
    int BULK_CNT = 100;

    /** StsCd:ZERO */
    String STS_CD_ZERO = "0";

    /** StsCd:ONE */
    String STS_CD_ONE = "1";

    // Add Start 09/28/2015
    /** StsCd:TWO */
    String STS_CD_TWO = "2";

    /** default rate */
    int DEFAULT_RATE = 100;
    // Add End 09/28/2015

    /** ZERO PADDING */
    String ZERO3_PAD = "%1$03d";

    /** MULTIPLICATION : for calculate func amount */
    String MULTIPLICATION = "*";

    /** Const Key Coa Cmpy Cd */
    String CONST_KEY_COA_CMPY_CD = "COA_CMPY_CD";

    /** Const Key Coa Extn Cd */
    String CONST_KEY_COA_EXTN_CD = "COA_EXTN_CD";
// ISSUE#48
    /** Const Key Service Coa Acct Cd */
    String CONST_KEY_SVC_COA_ACCT_CD = "SVC_COA_ACCT_CD";

    // Add Start 09/28/2015
    /** Const Key NSZC0060_DS_INV_TYPE_CREDIT */
    String CONST_KEY_NSZC0060_DS_INV_TYPE_CREDIT = "NSZC0060_DS_INV_TYPE_CREDIT";

    /** Const Key NSZC0060_DS_INV_TYPE_INVOICE */
    String CONST_KEY_NSZC0060_DS_INV_TYPE_INVOICE = "NSZC0060_DS_INV_TYPE_INVOICE";
    // Add End 09/28/2015

    /** Date Format Timestamp (17digit) */
    String DATE_FORMAT_TIMESTAMP = "yyyyMMddHHmmssSSS";

    /** Max Digit 15 */
    BigDecimal MAX_DIGIT15 = new BigDecimal("1000000000000000");
    /** Max Digit 15 */
    BigDecimal MIN_DIGIT15 = new BigDecimal("-1000000000000000");

    /** default scale */
    int DEFAULT_SCL = 4;

    /** BR_CD */
    String COL_BR_CD = "BR_CD";
    /** LOC_NM */
    String COL_LOC_NM = "LOC_NM";
    /** ADDL_LOC_NM */
    String COL_ADDL_LOC_NM = "ADDL_LOC_NM";
    /** FIRST_LINE_ADDR */
    String COL_FIRST_LINE_ADDR = "FIRST_LINE_ADDR";
    /** SCD_LINE_ADDR */
    String COL_SCD_LINE_ADDR = "SCD_LINE_ADDR";
    /** THIRD_LINE_ADDR */
    String COL_THIRD_LINE_ADDR = "THIRD_LINE_ADDR";
    /** FRTH_LINE_ADDR */
    String COL_FRTH_LINE_ADDR = "FRTH_LINE_ADDR";
    /** CTY_ADDR */
    String COL_CTY_ADDR = "CTY_ADDR";
    /** CNTY_NM */
    String COL_CNTY_NM = "CNTY_NM";
    /** PROV_NM */
    String COL_PROV_NM = "PROV_NM";
    /** ST_CD */
    String COL_ST_CD = "ST_CD";
    /** POST_CD */
    String COL_POST_CD = "POST_CD";
    /** CTRY_CD */
    String COL_CTRY_CD = "CTRY_CD";
    /** TEL_NUM */
    String COL_TEL_NUM = "TEL_NUM";
    /** FAX_NUM */
    String COL_FAX_NUM = "FAX_NUM";

    /** SVC_INV_NUM */
    String SVC_INV_NUM = "SVC_INV_NUM";

    // QC1943 Add Start
    /** SVC_INV_LINE_NUM_PAD */
    int SVC_INV_LINE_NUM_PAD = 5;
    // QC1943 Add End
}
