/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC001001.constant;

/**
 *<pre>
 * Customer Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/02   Fujitsu         N.Sugiura       Create          N/A
 * 2018/02/23   Fujitsu         Hd.Sugawara     Update          QC#22568
 * 2018/11/28   Fujitsu         C.Hara          Update          QC#29134
 * 2018/12/19   Fujitsu         T.Noguchi       Update          QC#29134
 * 2019/04/05   Fujitsu         T.Noguchi       Update          QC#31030
 *</pre>
 */
public class NMZC001001Constant {

    /** PROCESS MODE 01 New Prospect creation. */
    public static final String PROCESS_MODE_PROS_CRAT = "01";

    /** PROCESS MODE 02 New Prospect update. */
    public static final String PROCESS_MODE_PROS_UPD = "02";

    /** PROCESS MODE 03 New Customer creation. */
    public static final String PROCESS_MODE_CUST_CRAT = "03";

    /** PROCESS MODE 04 New Customer update. */
    public static final String PROCESS_MODE_CUST_UPD = "04";

    /** PROCESS MODE 05 New Customer creation for active . */
    public static final String PROCESS_MODE_CUST_CRAT_ACTV = "05";

    /** PROCESS MODE 06 Exist Customer Location . */
    public static final String PROCESS_MODE_EXST_CUST_LOC = "06";

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NMZM0009E = "NMZM0009E";

    /**
     * Input parameter "Sales Date" is a mandatory field.
     */
    public static final String NMZM0011E = "NMZM0011E";

    /**
     * Process mode is not set.
     */
    public static final String NMZM0054E = "NMZM0054E";

    /**
     * Invalid value is set for process mode.
     */
    public static final String NMZM0055E = "NMZM0055E";

    /**
     * Parameter for @ is not registered yet.
     */
    public static final String NMZM0056E = "NMZM0056E";

    /**
     * It failed to register to the [@] table.
     */
    public static final String NMZM0057E = "NMZM0057E";

    /**
     * The record cannot be updated. Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    public static final String NMZM0058E = "NMZM0058E";

    /**
     * The entered [@] does not exist in [@].
     */
    public static final String NMZM0059E = "NMZM0059E";

    /**
     * The effective date of the entered data is incorrect.
     */
    public static final String NMZM0060E = "NMZM0060E";

    /**
     * [@] for update target is not found.
     */
    public static final String NMZM0061E = "NMZM0061E";

    /**
     * Credit limit has not been set. Regstration status can not be
     * changed to P20.
     */
    public static final String NMZM0062E = "NMZM0062E";

    /**
     * @ is duplicated.
     */
    public static final String NMAM0072E = "NMAM0072E";

    /**
     * If [@] value is [@], then [@] must be [@].
     */
    public static final String NMZM0133E = "NMZM0133E";

    /**
     * You have entered an invalid address. Please enter a valid
     * address.
     */
    public static final String NMZM0178E = "NMZM0178E";

    /**
     * The chronological sequence is wrong.
     */
    public static final String NMAM8115E = "NMAM8115E";

    /**
     * The corresponding [@] does not exist.
     */
    public static final String NMAM0039E = "NMAM0039E";

    /**
     * We found a different address from the one you entered.
     */
    public static final String NMZM0179W = "NMZM0179W";

    // Add Start 2018/02/23 QC#22568
    /**
     * S21 does not allow back date effective dates.  Please change the effective date for From Date.
     */
    public static final String NMZM0368E = "NMZM0368E";

    /**
     * S21 does not allow effective date before today.  Please change the effective date for To Date.
     */
    public static final String NMZM0369E = "NMZM0369E";

    /**
     * S21 does not allow back date effective dates.  Please change the effective date for Bill To Start Date.
     */
    public static final String NMZM0370E = "NMZM0370E";

    /**
     * S21 does not allow effective date before today.  Please change the effective date for Bill To End Date.
     */
    public static final String NMZM0371E = "NMZM0371E";

    /**
     * S21 does not allow back date effective dates.  Please change the effective date for Ship To Start Date.
     */
    public static final String NMZM0372E = "NMZM0372E";

    /**
     * S21 does not allow effective date before today.  Please change the effective date for Ship To End Date.
     */
    public static final String NMZM0373E = "NMZM0373E";
    // Add End 2018/02/23 QC#22568

    // 2018/11/28 QC#29134 Add Start
    // 2018/12/19 QC#29134 Mod Start
    ///** @ doesn't exist. */
    //public static final String NMAM8693E = "NMAM8693E";
    //
    ///** @ is not active. */
    //public static final String NMAM8694E = "NMAM8694E";
    /**
     * Related Tech WH doesn't exist.
     */
    public static final String NMAM8699E = "NMAM8699E";

    /**
     * Related Tech WH is not active.
     */
    public static final String NMAM8700E = "NMAM8700E";
    // 2018/12/19 QC#29134 Mod End
    // 2018/11/28 QC#29134 Add End

    /** firstLineAddr */
    public static final String FIRST_ADDR = "firstLineAddr";

    /** scdLineAddr */
    public static final String SCD_ADDR = "scdLineAddr";

    /** ctyAddr */
    public static final String CTY_ADDR = "ctyAddr";

    /** stCd */
    public static final String ST_CD = "stCd";

    /** postCd */
    public static final String POST_CD = "postCd";

    /** ctryCd */
    public static final String CTRY_CD = "ctryCd";

    /** cntyNm */
    public static final String CNTY_NM = "cntyNm";

    /** DS_ACCT_NUM degit:28 */
    public static final int DS_ACCT_NUM = 28;

    /** LOC_NUM degit:30 */
    public static final int LOC_NUM = 30;

    /** CMPY_NM degit:60 */
    public static final int LEN_CMPY_NM = 60;

    /** Location max count for prospective mode */
    public static final int LOC_MAX_CNT_FOR_PROS = 1;

    /** Terminated Effective Date */
    public static final String TERMINATED_EFF_DT = "99991231";

    /** SEQ_SELL_TO_CUST_CD */
    public static final String SEQ_SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** SEQ_LOC_NUM */
    public static final String SEQ_LOC_NUM = "LOC_NUM";

    /** SEQ_BILL_TO_CUST_CD */
    public static final String SEQ_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** SEQ_SHIP_TO_CUST_CD */
    public static final String SEQ_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** LOC_NM max digit length */
    public static final int COL_LOC_NM_MAX_DIGIT_LENGTH = 60;

    /** Business ID */
    public static final String BIZ_ID = "NMZC0010";

    // 2018/11/28 QC#29134 Add Start
    /** VAR_CHAR_CONST_NM_END_TECH_HAZMAT_CD */
    public static final String VAR_CHAR_CONST_NM_END_TECH_HAZMAT_CD = "END_OF_TECH_HAZMAT_CODE";

    /** Error message:Related Tech WH */
    public static final String ERR_MSG_RELATED_TECH_WH = "Related Tech WH";
    // 2018/11/28 QC#29134 Add End

    /**
     * Parameter for Late Fee Amount is not registered yet.
     */
    public static final String NMZM0194E = "NMZM0194E";

    /**
     * Parameter for Account Name is not registered yet.
     */
    public static final String NMZM0195E = "NMZM0195E";

    /**
     * Parameter for Internal / External is not registered yet.
     */
    public static final String NMZM0196E = "NMZM0196E";

    /**
     * Parameter for Classification Code is not registered yet.
     */
    public static final String NMZM0197E = "NMZM0197E";

    /**
     * Parameter for GL Classification code is not registered yet.
     */
    public static final String NMZM0198E = "NMZM0198E";

    /**
     * Parameter for GL Intercompany code is not registered yet.
     */
    public static final String NMZM0199E = "NMZM0199E";

    /**
     * Parameter for Account Credit flag is not registered yet.
     */
    public static final String NMZM0200E = "NMZM0200E";

    /**
     * Parameter for Credit Rating is not registered yet.
     */
    public static final String NMZM0201E = "NMZM0201E";

    /**
     * Parameter for Credit Limit is not registered yet.
     */
    public static final String NMZM0202E = "NMZM0202E";

    /**
     * Parameter for Credit Hold is not registered yet.
     */
    public static final String NMZM0203E = "NMZM0203E";

    /**
     * Parameter for Grace period(Days) is not registered yet.
     */
    public static final String NMZM0204E = "NMZM0204E";

    /**
     * Parameter for Payment Term is not registered yet.
     */
    public static final String NMZM0205E = "NMZM0205E";

    /**
     * Parameter for Account Number is not registered yet.
     */
    public static final String NMZM0206E = "NMZM0206E";

    /**
     * Parameter for Inactive Reason is not registered yet.
     */
    public static final String NMZM0207E = "NMZM0207E";

    /**
     * Parameter for Registration Status Code is not registered yet.
     */
    public static final String NMZM0208E = "NMZM0208E";

    /**
     * Parameter for Inactive reason Effective end date is not
     * registered yet.
     */
    public static final String NMZM0209E = "NMZM0209E";

    /**
     * Parameter for Account Collection flag is not registered yet.
     */
    public static final String NMZM0210E = "NMZM0210E";

    /**
     * Parameter for Default Collection Portfolio is not registered
     * yet.
     */
    public static final String NMZM0211E = "NMZM0211E";

    /**
     * Parameter for Location Effective From Date is not registered
     * yet.
     */
    public static final String NMZM0212E = "NMZM0212E";

    /**
     * Parameter for First Line Address is not registered yet.
     */
    public static final String NMZM0213E = "NMZM0213E";

    /**
     * Parameter for City Address is not registered yet.
     */
    public static final String NMZM0214E = "NMZM0214E";

    /**
     * Parameter for State Code is not registered yet.
     */
    public static final String NMZM0215E = "NMZM0215E";

    /**
     * Parameter for Postal Code is not registered yet.
     */
    public static final String NMZM0216E = "NMZM0216E";

    /**
     * Parameter for Location Name is not registered yet.
     */
    public static final String NMZM0217E = "NMZM0217E";

    /**
     * Parameter for Bill to cust Effective From Date is not
     * registered yet.
     */
    public static final String NMZM0218E = "NMZM0218E";

    /**
     * Parameter for Ship to cust Effective From Date is not
     * registered yet.
     */
    public static final String NMZM0219E = "NMZM0219E";

    /**
     * Parameter for Bill to Line of Business Type Code is not
     * registered yet.
     */
    public static final String NMZM0220E = "NMZM0220E";

    /**
     * Parameter for Ship to Line of Business Type Code is not
     * registered yet.
     */
    public static final String NMZM0221E = "NMZM0221E";

    /**
     * Parameter for Location Number is not registered yet.
     */
    public static final String NMZM0222E = "NMZM0222E";

    /**
     * Parameter for Cross_Reference Account Type Code is not
     * registered yet.
     */
    public static final String NMZM0223E = "NMZM0223E";

    /**
     * Parameter for Cross_Reference Account Code is not registered
     * yet.
     */
    public static final String NMZM0224E = "NMZM0224E";

    /**
     * Parameter for Cross_Reference Account Name is not registered
     * yet.
     */
    public static final String NMZM0225E = "NMZM0225E";

    /**
     * Parameter for Minimum Balance to calculate Late Fee is not
     * registered yet.
     */
    public static final String NMZM0226E = "NMZM0226E";

    /**
     * It failed to register to the [SELL_TO_CUST] table.
     */
    public static final String NMZM0231E = "NMZM0231E";

    /**
     * It failed to register to the [CMPY] table.
     */
    public static final String NMZM0232E = "NMZM0232E";

    /**
     * It failed to register to the [ACCT_LOC] table.
     */
    public static final String NMZM0233E = "NMZM0233E";

    /**
     * It failed to register to the [PTY_LOC_WRK] table.
     */
    public static final String NMZM0234E = "NMZM0234E";

    /**
     * It failed to register to the [DS_PTY_LOC_WRK] table.
     */
    public static final String NMZM0235E = "NMZM0235E";

    /**
     * It failed to register to the [PRIN_CUST] table.
     */
    public static final String NMZM0236E = "NMZM0236E";

    /**
     * It failed to register to the [DS_PRIN_CUST] table.
     */
    public static final String NMZM0237E = "NMZM0237E";

    /**
     * It failed to register to the [LOC_USG] table.
     */
    public static final String NMZM0238E = "NMZM0238E";

    /**
     * It failed to register to the [BILL_TO_CUST] table.
     */
    public static final String NMZM0239E = "NMZM0239E";

    /**
     * It failed to register to the [DS_BILL_TO_CUST] table.
     */
    public static final String NMZM0240E = "NMZM0240E";

    /**
     * It failed to register to the [INV_RCPNT] table.
     */
    public static final String NMZM0241E = "NMZM0241E";

    /**
     * It failed to register to the [DS_INV_RCPNT] table.
     */
    public static final String NMZM0242E = "NMZM0242E";

    /**
     * It failed to register to the [ALT_PAYER] table.
     */
    public static final String NMZM0243E = "NMZM0243E";

    /**
     * It failed to register to the [CUST_CR_PRFL] table.
     */
    public static final String NMZM0244E = "NMZM0244E";

    /**
     * It failed to register to the [DS_CUST_CR_PRFL] table.
     */
    public static final String NMZM0245E = "NMZM0245E";

    /**
     * It failed to register to the [SHIP_TO_CUST] table.
     */
    public static final String NMZM0246E = "NMZM0246E";

    /**
     * It failed to register to the [DS_SHIP_TO_CUST] table.
     */
    public static final String NMZM0247E = "NMZM0247E";

    /**
     * It failed to register to the [DS_ACCT_CR_PRFL] table.
     */
    public static final String NMZM0248E = "NMZM0248E";

    /**
     * It failed to register to the [DS_XREF_ACCT] table.
     */
    public static final String NMZM0249E = "NMZM0249E";

    /**
     * It failed to register to the [DS_CUST_SPCL_MSG] table.
     */
    public static final String NMZM0250E = "NMZM0250E";

    /**
     * It failed to register to the [DS_ACCT_PROS] table.
     */
    public static final String NMZM0251E = "NMZM0251E";

    /**
     * It failed to register to the [DS_ACCT_CUST] table.
     */
    public static final String NMZM0252E = "NMZM0252E";

    /**
     * The record cannot be updated. Table Name: [SELL_TO_CUST]
     */
    public static final String NMZM0257E = "NMZM0257E";

    /**
     * The record cannot be updated. Table Name: [CMPY]
     */
    public static final String NMZM0258E = "NMZM0258E";

    /**
     * The record cannot be updated. Table Name: [ACCT_LOC]
     */
    public static final String NMZM0259E = "NMZM0259E";

    /**
     * The record cannot be updated. Table Name: [PTY_LOC_WRK]
     */
    public static final String NMZM0260E = "NMZM0260E";

    /**
     * The record cannot be updated. Table Name: [DS_PTY_LOC_WRK]
     */
    public static final String NMZM0261E = "NMZM0261E";

    /**
     * The record cannot be updated. Table Name: [PRIN_CUST]
     */
    public static final String NMZM0262E = "NMZM0262E";

    /**
     * The record cannot be updated. Table Name: [DS_PRIN_CUST]
     */
    public static final String NMZM0263E = "NMZM0263E";

    /**
     * The record cannot be updated. Table Name: [BILL_TO_CUST]
     */
    public static final String NMZM0264E = "NMZM0264E";

    /**
     * The record cannot be updated. Table Name: [DS_BILL_TO_CUST]
     */
    public static final String NMZM0265E = "NMZM0265E";

    /**
     * The record cannot be updated. Table Name: [INV_RCPNT]
     */
    public static final String NMZM0266E = "NMZM0266E";

    /**
     * The record cannot be updated. Table Name: [ALT_PAYER]
     */
    public static final String NMZM0267E = "NMZM0267E";

    /**
     * The record cannot be updated. Table Name: [CUST_CR_PRFL]
     */
    public static final String NMZM0268E = "NMZM0268E";

    /**
     * The record cannot be updated. Table Name: [DS_CUST_CR_PRFL]
     */
    public static final String NMZM0269E = "NMZM0269E";

    /**
     * The record cannot be updated. Table Name: [SHIP_TO_CUST]
     */
    public static final String NMZM0270E = "NMZM0270E";

    /**
     * The record cannot be updated. Table Name: [DS_SHIP_TO_CUST]
     */
    public static final String NMZM0271E = "NMZM0271E";

    /**
     * The record cannot be updated. Table Name: [DS_ACCT_CR_PRFL]
     */
    public static final String NMZM0272E = "NMZM0272E";

    /**
     * The record cannot be updated. Table Name: [DS_XREF_ACCT]
     */
    public static final String NMZM0273E = "NMZM0273E";

    /**
     * The record cannot be updated. Table Name: [DS_CUST_SPCL_MSG]
     */
    public static final String NMZM0274E = "NMZM0274E";

    /**
     * The record cannot be updated. Table Name: [DS_ACCT_PROS]
     */
    public static final String NMZM0275E = "NMZM0275E";

    /**
     * The record cannot be updated. Table Name: [DS_ACCT_CUST]
     */
    public static final String NMZM0276E = "NMZM0276E";

    /**
     * The entered [Classification Code] does not exist in
     * [DS_ACCT_CLS].
     */
    public static final String NMZM0277E = "NMZM0277E";

    /**
     * The entered [Direct Sales Account Dealer Code] does not exist
     * in [DS_ACCT_DLR].
     */
    public static final String NMZM0278E = "NMZM0278E";

    /**
     * The entered [Registration Status Code] does not exist in
     * [RGTN_STS].
     */
    public static final String NMZM0279E = "NMZM0279E";

    /**
     * The entered [GL Intercompany code] does not exist in
     * [COA_AFFL].
     */
    public static final String NMZM0280E = "NMZM0280E";

    /**
     * The entered [Direct Sales Customer Collection Account Status
     * Code] does not exist in [DS_CLT_ACCT_STS].
     */
    public static final String NMZM0281E = "NMZM0281E";

    /**
     * The entered [Collection Customer Type Code] does not exist in
     * [CLT_CUST_TP].
     */
    public static final String NMZM0282E = "NMZM0282E";

    /**
     * The entered [Tax Print Type Code] does not exist in
     * [DS_TAX_PRNT_TP].
     */
    public static final String NMZM0283E = "NMZM0283E";

    /**
     * The entered [Direct Sales Customer Tax Calculation Code] does
     * not exist in [DS_CUST_TAX_CALC].
     */
    public static final String NMZM0284E = "NMZM0284E";

    /**
     * The entered [Tax Group Exemption Code] does not exist in
     * [DS_TAX_GRP_EXEM].
     */
    public static final String NMZM0285E = "NMZM0285E";

    /**
     * The entered [Chart of Account Channel Code] does not exist in
     * [COA_CH].
     */
    public static final String NMZM0286E = "NMZM0286E";

    /**
     * The entered [Direct Sales Account Inactive Reason Code] does
     * not exist in [DS_ACCT_INAC_RSN].
     */
    public static final String NMZM0287E = "NMZM0287E";

    /**
     * The entered [Credit Rating] does not exist in [CUST_CR_RTG].
     */
    public static final String NMZM0288E = "NMZM0288E";

    /**
     * The entered [Grace period(Days)] does not exist in
     * [CR_RISK_CLS].
     */
    public static final String NMZM0289E = "NMZM0289E";

    /**
     * The entered [Payment Term] does not exist in
     * [PMT_TERM_CASH_DISC].
     */
    public static final String NMZM0290E = "NMZM0290E";

    /**
     * The entered [County Name] does not exist in [CNTY].
     */
    public static final String NMZM0291E = "NMZM0291E";

    /**
     * The entered [State Code] does not exist in [ST].
     */
    public static final String NMZM0292E = "NMZM0292E";

    /**
     * The entered [Country Code] does not exist in [CTRY].
     */
    public static final String NMZM0293E = "NMZM0293E";

    /**
     * The entered [Denied Party List Status Code] does not exist in
     * [DPL_STS].
     */
    public static final String NMZM0294E = "NMZM0294E";

    /**
     * The entered [Chart of Account for Bill To] does not exist in
     * [COA_AFFL].
     */
    public static final String NMZM0295E = "NMZM0295E";

    /**
     * The entered [Chart of Account Channel Code for Bill To] does
     * not exist in [COA_CH].
     */
    public static final String NMZM0296E = "NMZM0296E";

    /**
     * The entered [Direct Sales Customer Tax Calculation Code for
     * Bill To] does not exist in [DS_CUST_TAX_CALC].
     */
    public static final String NMZM0297E = "NMZM0297E";

    /**
     * The entered [Direct Sales Customer Tax Calculation Code for
     * Ship To] does not exist in [DS_CUST_TAX_CALC].
     */
    public static final String NMZM0298E = "NMZM0298E";

    /**
     * The entered [Collection Customer Type Code for Bill To] does
     * not exist in [CLT_CUST_TP].
     */
    public static final String NMZM0299E = "NMZM0299E";

    /**
     * The entered [Direct Sales Tax Group Exemption Code for Bill To]
     * does not exist in [DS_TAX_GRP_EXEM].
     */
    public static final String NMZM0300E = "NMZM0300E";

    /**
     * The entered [Direct Sales Tax Group Exemption Code for Ship To]
     * does not exist in [DS_TAX_GRP_EXEM].
     */
    public static final String NMZM0301E = "NMZM0301E";

    /**
     * The entered [Direct Sales Tax Print Type Code for Bill To] does
     * not exist in [DS_TAX_PRNT_TP].
     */
    public static final String NMZM0302E = "NMZM0302E";

    /**
     * The entered [Direct Sales Tax Print Type Code for Ship To] does
     * not exist in [DS_TAX_PRNT_TP].
     */
    public static final String NMZM0303E = "NMZM0303E";

    /**
     * The entered [Line of Business Type Code for BIll To] does not
     * exist in [LINE_BIZ_TP].
     */
    public static final String NMZM0304E = "NMZM0304E";

    /**
     * The entered [Line of Business Type Code for Ship To] does not
     * exist in [LINE_BIZ_TP].
     */
    public static final String NMZM0305E = "NMZM0305E";

    /**
     * The entered [Direct Sales Cross_Reference Account Type Code]
     * does not exist in [DS_XREF_ACCT_TP].
     */
    public static final String NMZM0306E = "NMZM0306E";

    /**
     * [SELL_TO_CUST] for update target is not found.
     */
    public static final String NMZM0311E = "NMZM0311E";

    /**
     * [CMPY] for update target is not found.
     */
    public static final String NMZM0312E = "NMZM0312E";

    /**
     * [ACCT_LOC] for update target is not found.
     */
    public static final String NMZM0313E = "NMZM0313E";

    /**
     * [PTY_LOC_WRK] for update target is not found.
     */
    public static final String NMZM0314E = "NMZM0314E";

    /**
     * [DS_PTY_LOC_WRK] for update target is not found.
     */
    public static final String NMZM0315E = "NMZM0315E";

    /**
     * [PRIN_CUST] for update target is not found.
     */
    public static final String NMZM0316E = "NMZM0316E";

    /**
     * [DS_PRIN_CUST] for update target is not found.
     */
    public static final String NMZM0317E = "NMZM0317E";

    /**
     * [BILL_TO_CUST] for update target is not found.
     */
    public static final String NMZM0318E = "NMZM0318E";

    /**
     * [DS_BILL_TO_CUST] for update target is not found.
     */
    public static final String NMZM0319E = "NMZM0319E";

    /**
     * [INV_RCPNT] for update target is not found.
     */
    public static final String NMZM0320E = "NMZM0320E";

    /**
     * [ALT_PAYER] for update target is not found.
     */
    public static final String NMZM0321E = "NMZM0321E";

    /**
     * [CUST_CR_PRFL] for update target is not found.
     */
    public static final String NMZM0322E = "NMZM0322E";

    /**
     * [DS_CUST_CR_PRFL] for update target is not found.
     */
    public static final String NMZM0323E = "NMZM0323E";

    /**
     * [SHIP_TO_CUST] for update target is not found.
     */
    public static final String NMZM0324E = "NMZM0324E";

    /**
     * [DS_SHIP_TO_CUST] for update target is not found.
     */
    public static final String NMZM0325E = "NMZM0325E";

    /**
     * [DS_ACCT_CR_PRFL] for update target is not found.
     */
    public static final String NMZM0326E = "NMZM0326E";

    /**
     * [DS_XREF_ACCT] for update target is not found.
     */
    public static final String NMZM0327E = "NMZM0327E";

    /**
     * [DS_ACCT_PROS] for update target is not found.
     */
    public static final String NMZM0328E = "NMZM0328E";

    /**
     * [DS_ACCT_CUST] for update target is not found.
     */
    public static final String NMZM0329E = "NMZM0329E";

    /**
     * The entered [Credit Hold] does not exist in [CR_CHK_REQ_TP].
     */
    public static final String NMZM0330E = "NMZM0330E";

    /**
     * Parameter for Line of Business Type Code is not registered yet.
     */
    public static final String NMZM0331E = "NMZM0331E";

    /**
     * The corresponding [Default Credit Template] does not exist.
     */
    public static final String NMAM8477E = "NMAM8477E";

    /**
     * Can not get the default Remit ID.
     */
    public static final String NMAM8490E = "NMAM8490E";

    /**
     * "DPL Screening Process Error" occurred and the process has been
     * aborted.
     */
    public static final String NMAM8052E = "NMAM8052E";

    /**
     * "Cross Reference# is duplicated.
     */
    public static final String NMAM8628E = "NMAM8628E";

    /** If Hard Hold Flag is Y, please specify Credit Hold as 'Hold'. */
    public static final String NMAM8638E = "NMAM8638E";

    /**
     * Failed to update WH.
     */
    public static final String NMZM0335E = "NMZM0335E";

    /**
     * Failed to update RTL_WH.
     */
    public static final String NMZM0336E = "NMZM0336E";

    /**
     * This location is WH/Tech WH address but  RTL_SWH table data doesn't exist. 
     */
    public static final String NMZM0337E = "NMZM0337E";

    /**
     * Prospect can't have more than 2 locations. 
     */
    public static final String NMZM0345E = "NMZM0345E";

    /**
     * Prospect can't have Bill To / Ship To. 
     */
    public static final String NMZM0346E = "NMZM0346E";

    /** Special Message Max Byte Length */
    public static final int MAX_SPCL_MSG_LEN = 4000;

    /** DPL Rason Max Byte Length */
    public static final int MAX_LEN_DPL_RSN_TXT = 2000;

    // 2019/04/05 QC#31030 Add Start
    /** Ship To Details AppFuncId */
    public static final String APP_FUNC_ID_SHIP_TO_DETAILS = "NMAL6740001";
    // 2019/04/05 QC#31030 Add End
}
