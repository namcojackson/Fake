package com.canon.cusa.s21.api.NLZ.NLZC002001;


import static com.canon.cusa.s21.api.NLZ.NLZC001001.NLZC001001.OPT_CHK_FREE_OH;
import static com.canon.cusa.s21.api.NLZ.NLZC001001.NLZC001001.PROC_TP_ALLOC;
import static com.canon.cusa.s21.api.NLZ.NLZC001001.NLZC001001.PROC_TP_CANC_ALLOC;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CPO_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.INVTYTMsg;
import business.db.INVTY_DTL_DLYTMsg;
import business.db.INVTY_LOC_VTMsg;
import business.db.INVTY_LOC_VTMsgArray;
import business.db.INVTY_ORD_DTLTMsg;
import business.db.INVTY_TRXTMsg;
import business.db.MDSETMsg;
import business.db.RTL_SWHTMsg;
import business.db.RWS_HDRTMsg;
import business.db.S21_ORGTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.TRX_RSNTMsg;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.parts.NLZC001001PMsg;
import business.parts.NLZC002001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC001001.NLZC001001;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_RSN;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * NLZC0020 : Inventory Update API
 * This API is Update to the Inventory Transaction.
 *</pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   CITS            Hisashi         Update          for CSA Pattern
 * 12/21/2015   CSAI            Y.Imazu         Update          QC#793(Loan SWH)
 * 04/28/2016   CSAI            Y.Imazu         Update          QC#7715
 * 05/10/2016   CSAI            Y.Imazu         Update          QC#8141
 * 05/27/2016   CSAI            Y.Imazu         Update          QC#9213
 * 06/03/2016   CSAI            Y.Imazu         Update          QC#9498
 * 06/21/2016   CSAI            Y.Imazu         Update          QC#10523
 * 06/30/2016   CSAI            K.Lee           Update          Configuration Change
 * 07/28/2016   CSAI            Y.Imazu         Update          QC#12545
 * 08/02/2016   CSAI            Y.Imazu         Update          QC#12782
 * 03/15/2017   CITS            T.Tokutomi      Update          Merge DS
 * 09/14/2017   CITS            S.Endo          Update          Sol#373(QC#16346)
 * 09/29/2017   Fujitsu         H.Nagashima     Update          QC#20840
 * 10/20/2017   Fujitsu         S.Ohki          Update          Sol#430(QC#16347)
 * 10/23/2017   Fujitsu         S.Ohki          Update          Sol#430(QC#16347-2)
 * 12/14/2017   CITS            K.Ogino         Update          QC#22101
 * 01/11/2018   CITS            K.Ogino         Update          QC#23365
 * 01/26/2018   CITS            K.Ogino         Update          QC#23802
 * 02/06/2018   CITS            K.Ogino         Update          QC#23962
 * 02/14/2018   CITS            K.Ogino         Update          QC#23802
 * 02/20/2018   CITS            T.Gotoda        Update          QC#24045
 * 03/19/2018   CITS            T.Tokutomi      Update          QC#12110
 * 04/20/2018   CITS            T.Tokutomi      Update          QC#25581
 * 08/09/2018   CITS            K.Ogino         Update          QC#27665
 * 10/04/2018   CITS            K.Ogino         Update          QC#28954
 * 05/27/2019   CITS            K.Ogino         Update          QC#50378
 * 08/08/2019   CITS            M.Naito         Update          QC#52333
 * 05/27/2020   CITS            K.Ogino         Update          QC#54864
 * 07/31/2020   CITS            K.Ogino         Update          QC#57462
 * 08/03/2021   CITS            K.Ogino         Update          QC#59038
 * 14/03/2024   CITS            J.Cho           Update          QC#63527
 *</pre>
 */
public class NLZC002001 extends S21ApiCommonBase {

    // -- Request Type Code ---------------------
    /** Request Type Code : Stock-in */
    public static final String RQST_STOCK_IN = "I";
    /** Request Type Code : Stock-out */
    public static final String RQST_STOCK_OUT = "O";

    // -- Transaction Detail Code ---------------
    /** Transaction Detail Code : Import */
    public static final String TRX_DTL_IMPT = "01";
    /** Transaction Detail Code : Domestic */
    public static final String TRX_DTL_DOM = "02";
    /** Transaction Detail Code : Item Entry SP */
    public static final String TRX_DTL_ITM_ENTY_SP = "03";
    /** Transaction Detail Code : Sell Then Buy */
    public static final String TRX_DTL_SELL_THEN_BUY = "04";
    /** Transaction Detail Code : Intangible with Cost */
    public static final String TRX_DTL_INTG_WITH_COST = "05";
    /** Transaction Detail Code : S21 */
    public static final String TRX_DTL_S21 = "06";
    /** Transaction Detail Code : ROSS */
    public static final String TRX_DTL_ROSS = "07";
    /** Transaction Detail Code : eStore */
    public static final String TRX_DTL_ESTORE = "08";
    /** Transaction Detail Code : Item Change Screen */
    public static final String TRX_DTL_ITM_CHNG_SCR = "09";
    /** Transaction Detail Code : Kitting & Refurbish */
    public static final String TRX_DTL_KIT_AND_RFRB = "10";
    /** Transaction Detail Code : Unkit by Batch */
    public static final String TRX_DTL_UNKIT_BY_BAT = "11";
    /** Transaction Detail Code : Ship Confirmation */
    public static final String TRX_DTL_SHIP_CONF = "12";
    /** Transaction Detail Code : Receive Confirmation */
    public static final String TRX_DTL_RCV_CONF = "13";
    /** Transaction Detail Code : Regular Sales */
    public static final String TRX_DTL_REG_SLS = "14";
    /** Transaction Detail Code : Expense */
    public static final String TRX_DTL_EXP = "15";
    /** Transaction Detail Code : Trial Ship */
    public static final String TRX_DTL_TRIAL_SHIP = "16";
    /** Transaction Detail Code : Trial to Inventory */
    public static final String TRX_DTL_TRIAL_TO_INVTY = "17";
    /** Transaction Detail Code : Trial to Inventory (Loss on Ship) */
    public static final String TRX_DTL_LOSS_ON_SHIP = "18";
    // MOD Start 03/19/2018 [QC#12110]
    /** Transaction Detail Code : Receive by Purchase Order */
    public static final String TRX_DTL_RCV_PO = "19";
    /** Transaction Detail Code : Receive by Warehouse Transfer */
    public static final String TRX_DTL_RCV_WH_TRSF = "20";
    // MOD End 03/19/2018 [QC#12110]

    // -- System Type Code ----------------------
    /** System Type Code : Out-Bound */
    public static final String SYS_TP_OTBD = "01";
    /** System Type Code : In-Bound */
    public static final String SYS_TP_INBD = "02";
    /** System Type Code : Return */
    public static final String SYS_TP_RTRN = "03";
    /** System Type Code : Order Management */
    public static final String SYS_TP_OM = "04";
    /** System Type Code : Inventory */
    public static final String SYS_TP_INVTY = "05";
    /** System Type Code : ROSS */
    public static final String SYS_TP_ROSS = "06";
    /** System Type Code : Procurement */
    public static final String SYS_TP_PROCR = "07";
    /** System Type Code : eStore Interface */
    public static final String SYS_TP_ESTORE = "08";
    /** System Type Code : Export */
    public static final String SYS_TP_EXPORT = "09";

    // -- Error Message Code --------------------
    /** Invalid Process Type. */
    public static final String MSG_ID_NLZM0001E = "NLZM0001E";
    /** Data Company Code is empty. */
    public static final String MSG_ID_NLZM0003E = "NLZM0003E";
    /** Merchandise Code is empty. */
    public static final String MSG_ID_NLZM0005E = "NLZM0005E";
    /** Location Code is empty. */
    public static final String MSG_ID_NLZM0006E = "NLZM0006E";
    /** Location Status Code is empty. */
    public static final String MSG_ID_NLZM0007E = "NLZM0007E";
    /** Stock Status Code is empty. */
    public static final String MSG_ID_NLZM0008E = "NLZM0008E";
    /** Request Qty is empty. */
    public static final String MSG_ID_NLZM0009E = "NLZM0009E";
    /** Request Quantity is not a positive number. */
    public static final String MSG_ID_NLZM0011E = "NLZM0011E";
    /** Inventory master is not found. */
    public static final String MSG_ID_NLZM0014E = "NLZM0014E";
    /** Transaction Code is empty. */
    public static final String MSG_ID_NLZM0018E = "NLZM0018E";
    /** Transaction Reason Code is empty. */
    public static final String MSG_ID_NLZM0019E = "NLZM0019E";
    /** Transaction Reason master is not found. */
    public static final String MSG_ID_NLZM0020E = "NLZM0020E";
    /** Merchandise master is not found. */
    public static final String MSG_ID_NLZM0021E = "NLZM0021E";
    /** Location code master is not found. */
    public static final String MSG_ID_NLZM0022E = "NLZM0022E";
    /** Location status code master is not found. */
    public static final String MSG_ID_NLZM0023E = "NLZM0023E";
    /** Stock status code master is not found. */
    public static final String MSG_ID_NLZM0024E = "NLZM0024E";
    /** Transaction Date is empty. */
    public static final String MSG_ID_NLZM0025E = "NLZM0025E";
    /** Shipping Order is not found. */
    public static final String MSG_ID_NLZM0034E = "NLZM0034E";
    /** Shipping Order Detail is not found. */
    public static final String MSG_ID_NLZM0035E = "NLZM0035E";
    /** Customer Purchase Order Detail is not found. */
    public static final String MSG_ID_NLZM0036E = "NLZM0036E";
    /** Shipping Order Customer Detail is not found. */
    public static final String MSG_ID_NLZM0037E = "NLZM0037E";
    /** Organization Master is not found. */
    public static final String MSG_ID_NLZM0038E = "NLZM0038E";
    /** Location Quantity is overflow. */
    public static final String MSG_ID_NLZM0039E = "NLZM0039E";
    /** DB Error has occurred. */
    public static final String MSG_ID_NLZM0044E = "NLZM0044E";
    /** Shipping Order Customer Detail (Bill to) is not found. */
    public static final String MSG_ID_NLZM0045E = "NLZM0045E";
    /** Shipping Order Customer Detail (Ship to) is not found. */
    public static final String MSG_ID_NLZM0046E = "NLZM0046E";
    /** System source code is empty. */
    public static final String MSG_ID_NLZM0056E = "NLZM0056E";
    /** Sell to Customer is not found. */
    public static final String MSG_ID_NLZM0089E = "NLZM0089E";
    /** Bill to Customer is not found. */
    public static final String MSG_ID_NLZM0090E = "NLZM0090E";
    /** Ship to Customer is not found. */
    public static final String MSG_ID_NLZM0091E = "NLZM0091E";
    /** Vendor Master is not found. */
    public static final String MSG_ID_NLZM0092E = "NLZM0092E";
    /** Global Company Master is not found. */
    public static final String MSG_ID_NLZM0095E = "NLZM0095E";
    /** Inventory Master is not found. */
    public static final String MSG_ID_NLZM0096E = "NLZM0096E";
    /** Input parameter is Error. */
    public static final String MSG_ID_NLZM0113E = "NLZM0113E";
    /** Available Qty is overflow. */
    public static final String MSG_ID_NLZM0118E = "NLZM0118E";
    /** Work Order is not found. */
    public static final String MSG_ID_NLZM0119E = "NLZM0119E";
    /** Allocated Qty is overflow. */
    public static final String MSG_ID_NLZM0124E = "NLZM0124E";
    /** Valid MDSE does not exist in MDSE Master. 
     * (Unit Cost Amount or Asset Recovery Amount are invalid in MDSE Master) */
    public static final String MSG_ID_NLZM2217E = "NLZM2217E";
    /** Serial number is larger than the request qty. */
    public static final String MSG_ID_NLZM2508E = "NLZM2508E";
    // QC#25581 Add error message.
    /** TOC_CD cannot be found in S21_ORG. */
    public static final String MSG_ID_NFCM0549E = "NFCM0549E";
    // -- Worning Message Code ------------------
    /** Location Quantity has become negative. */
    public static final String MSG_ID_NLZM0040W = "NLZM0040W";
    /** Allocated Quantity has become negative. */
    public static final String MSG_ID_NLZM0041W = "NLZM0041W";
    /** Available Quantity has become negative. */
    public static final String MSG_ID_NLZM0042W = "NLZM0042W";

    // -- Scenario case -------------------------
    /** Unexpected */
    private static final int UNEXPECTED = 0;

    /** Purchase Stock-In */
    private static final int PRCH_STK_I = 100;
//    /** Purchase Stock-In/Purchase Stock-In/Inbound/Import */
//    private static final int PRCH_STK_I_PRCH_STK_I_INBD_IMPT = PRCH_STK_I + 1;
    /** Purchase Stock-In/Purchase Stock-In/Inbound/Domestic */
    private static final int PRCH_STK_I_PRCH_STK_I_INBD_DOM = PRCH_STK_I + 2;
    /** Purchase Stock-In/Purchase Stock-In/Inventory/Item Entry SP */
    private static final int PRCH_STK_I_PRCH_STK_I_INVTY_ITM_ENTY_SP = PRCH_STK_I + 3;
    /** Purchase Stock-In/Purchase Stock-In/Inbound/Sell Then Buy */
    private static final int PRCH_STK_I_PRCH_STK_I_INBD_SELL_THEN_BUY = PRCH_STK_I + 4;
    /** Purchase Stock-In/Purchase Stock-In/OM/Intangible with Cost */
    private static final int PRCH_STK_I_PRCH_STK_I_OM_INTG_WITH_COST = PRCH_STK_I + 5;
//    /** Purchase Stock-In/Purchase Stock-In/eStore Interface/eStore */
//    private static final int PRCH_STK_I_PRCH_STK_I_ESTORE_INTFC_ESTORE = PRCH_STK_I + 6;
//    /** Purchase Stock-In/Purchase Stock-In/ROSS/ROSS */
//    private static final int PRCH_STK_I_PRCH_STK_I_ROSS_ROSS = PRCH_STK_I + 7;
//    /** Purchase Stock-In/Purchase Stock-In/Export/ */
//    private static final int PRCH_STK_I_PRCH_STK_I_EXPT = PRCH_STK_I + 8;
//    /** Purchase Stock-In/Export Vendor Return/Export/ */
//    private static final int PRCH_STK_I_EXPT_VND_RTRN_EXPT = PRCH_STK_I + 9;
    /** Purchase Stock-In/Domestic Vendor Return/Outbound/ */
    private static final int PRCH_STK_I_DOM_VND_RTRN_OTBD = PRCH_STK_I + 10;
//    /** Purchase Stock-In/Export Vendor Return Asset/Export/ */
//    private static final int PRCH_STK_I_EXPT_VND_RTRN_AST_EXPT = PRCH_STK_I + 11;
//    /** Purchase Stock-In/Domestic Vendor Return Asset/Outbound/ */
//    private static final int PRCH_STK_I_DOM_VND_RTRN_AST_OTBD = PRCH_STK_I + 12;
    /** Purchase Stock-In/Purchase Stock-In/Subcontract */
    private static final int PRCH_STK_I_PRCH_STK_I_SUB_CONTR = PRCH_STK_I + 13;


    /** Sales */
    private static final int SLS = 200;
    /** Sales/Regular Sales with Cost/OM/ */
    private static final int SLS_REG_SLS_WITH_COST_OM = SLS + 1;
//    /** Sales/Regular Sales with Cost/eStore Interface/eStore */
//    private static final int SLS_REG_SLS_WITH_COST_ESTORE_INTFC_ESTORE = SLS + 2;
//    /** Sales/Regular Sales with Cost/ROSS/ROSS */
//    private static final int SLS_REG_SLS_WITH_COST_ROSS_ROSS = SLS + 3;
    /** Sales/Regular Sales with Cost/OM/Intangible with Cost */
    private static final int SLS_REG_SLS_WITH_COST_OM_INTG_WITH_COST = SLS + 4;
    /** Sales/Credit & Rebill/OM/ */
    private static final int SLS_CR_AND_REBIL_OM = SLS + 5;
    /** Sales/Return/Inbound/ */
    private static final int SLS_RTRN_INBD = SLS + 6;
//    /** Sales/Return/Return/ */
//    private static final int SLS_RTRN_RTRN = SLS + 7;
//    /** Sales/Return/eStore Interface/eStore */
//    private static final int SLS_RTRN_ESTORE_INTFC_ESTORE = SLS + 8;
//    /** Sales/Return/Inbound/ROSS */
//    private static final int SLS_RTRN_INBD_ROSS = SLS + 9;
//    /** Sales/Return/ROSS/ROSS */
//    private static final int SLS_RTRN_ROSS_ROSS = SLS + 10;
//    /** Sales/Loan to Sale/OM/ */
//    private static final int SLS_LOAN_TO_SLS_OM = SLS + 11;
//    /** Sales/Trial to Sale/OM/ */
//    private static final int SLS_TRL_TO_SLS_OM = SLS + 12;
    /** Sales/Loan to Sale/OM/ */
    private static final int SLS_LOAN_TO_SLS_OM = SLS + 12;
//    /** Sales/Regular Sales Asset/OM/ */
//    private static final int SLS_REG_SLS_AST_OM = SLS + 13;

    //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) START
    /** Sales/Return/Inbound/InitialSupply */
    private static final int SLS_RTRN_INBD_INIT_SUPPLY = SLS + 13;
    /** Sales/Return/Inbound/ContractSupply */
    private static final int SLS_RTRN_INBD_CNTR_SUPPLY = SLS + 14;
    //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) END

    // 2017/10/20 S21_NA#16347 Add Start
    /** Sales/CASH LEASE - INIT SUP/Credit/OM */
    private static final int SLS_CASH_LEASE_INIT_SUP = SLS + 15;
    /** Sales/CASH LEASE - CNTR SUP/OM */
    private static final int SLS_CASH_LEASE_CNTR_SUP = SLS + 16;
    /** Sales/Loan to Sales/OM */
    private static final int SLS_LOAN_TO_SALES = SLS + 17;
    /** Sales/Regular Sales Asset for AJE Link/OM */
    private static final int SLS_REG_SALES_AJE_LINK = SLS + 18;
    // 2017/10/20 S21_NA#16347 Add End

    /** Expense */
    private static final int EXP = 300;
    /** Expense/Expense Shipment/OM/ */
    private static final int EXP_EXP_SHIP_OM = EXP + 1;
    /** Expense/Expense Shipment/OM/Intangible with cost */
    private static final int EXP_EXP_SHIP_OM_INTG_WITH_COST = EXP + 2;
//    /** Expense/Expense Shipment/eStore Interface/eStore */
//    private static final int EXP_EXP_SHIP_ESTORE_INTFC_ESTORE = EXP + 3;
//    /** Expense/Expense Shipment/ROSS/ROSS */
//    private static final int EXP_EXP_SHIP_ROSS_ROSS = EXP + 4;
    /** Expense/Expense Return/Inbound/ */
    private static final int EXP_EXP_RTRN_INBD = EXP + 5;
//    /** Expense/Expense Return/Return/ */
//    private static final int EXP_EXP_RTRN_RTRN = EXP + 6;
//    /** Expense/Expense Return/eStore Interface/eStore */
//    private static final int EXP_EXP_RTRN_ESTORE_INTFC_ESTORE = EXP + 7;
//    /** Expense/Expense Return/Inbound/ROSS */
//    private static final int EXP_EXP_RTRN_INBD_ROSS = EXP + 8;
//    /** Expense/Expense Return/ROSS/ROSS */
//    private static final int EXP_EXP_RTRN_ROSS_ROSS = EXP + 9;
//    /** Expense/Expense Shipment Asset/OM/ */
//    private static final int EXP_EXP_SHIP_AST_OM = EXP + 10;
    /** Expense/Loan Shipment Stock-Out Expense/ */
    private static final int EXP_LOAN_SHIP_STK_O = EXP + 11;
    /** Expense/Loan to Inventory Shortage Stock-Out/ */
    private static final int EXP_LOAN_TO_INVTY_SHORT_STK_O = EXP + 12;
    /** Expense/Drop Shipment Loan Stock-Out Expense/ */
    private static final int EXP_LOAN_DROP_SHIP_STK_O = EXP + 13;
    /** Expense/Loan Stock-Out Expense Intangible with Cost/ */
    private static final int EXP_LOAN_SHIP_STK_O_INTG_WITH_COST = EXP + 14;
    // QC#63527 2024/03/14 Start
    /** Expense/Off The Book Loan Shipment Stock-Out Expense/ */
    private static final int EXP_OFF_THE_BOOK_LOAN_SHIP_STK_O = EXP + 15;
    /** Expense/Off The Book Loan to Inventory Shortage Stock-Out/ */
    private static final int EXP_OFF_THE_BOOK_LOAN_TO_INVTY_SHORT_STK_O = EXP + 16;
    // QC#63527 2024/03/14 End

//    /** Loan */
//    private static final int LOAN = 400;
//    /** Loan/Loan Stock Out/OM/ */
//    private static final int LOAN_LOAN_STK_O_OM = LOAN + 1;
//    /** Loan/Loan Stock In/Outbound/ */
//    private static final int LOAN_LOAN_STK_I_OTBD = LOAN + 2;
//    /** Loan/Drop Shipment Loan Stock Out/Inbound/ */
//    private static final int LOAN_DROP_SHIP_LOAN_STK_O_INBD = LOAN + 3;
//    /** Loan/Drop Shipment Loan Stock In/Inbound/ */
//    private static final int LOAN_DROP_SHIP_LOAN_STK_I_INBD = LOAN + 4;
//    /** Loan/Loan Stock Out Intangible with Cost/OM/ */
//    private static final int LOAN_LOAN_STK_O_INTG_WITH_COST_OM = LOAN + 5;
//    /** Loan/Loan Stock Out Intangible with Cost/Export/ */
//    private static final int LOAN_LOAN_STK_O_INTG_WITH_COST_EXPT = LOAN + 6;
//    /** Loan/Loan Stock In Intangible with Cost/OM/ */
//    private static final int LOAN_LOAN_STK_I_INTG_WITH_COST_OM = LOAN + 7;
//    /** Loan/Loan Stock In Intangible with Cost/Export/ */
//    private static final int LOAN_LOAN_STK_I_INTG_WITH_COST_EXPT = LOAN + 8;
//    /** Loan/Loan To Asset Return/Inbound/ */
//    private static final int LOAN_LOAN_TO_AST_RTRN_INBD = LOAN + 9;
//    /** Loan/Loan To Asset Return/Return/ */
//    private static final int LOAN_LOAN_TO_AST_RTRN_RTRN = LOAN + 10;
//    /** Loan/Loan Shipment Stock Out Asset/OM/ */
//    private static final int LOAN_LOAN_SHIP_STK_O_AST_OM = LOAN + 11;

    /** Rental */
    private static final int RNTL = 500;
//    /** Rental/Rental Stock Out/ROSS/ROSS */
//    private static final int RNTL_RNTL_STK_O_ROSS_ROSS = RNTL + 1;
    /** Rental/Rental Stock Out/OM/ */
    private static final int RNTL_RNTL_STK_O_OM = RNTL + 2;
    /** Rental/Drop Shipment Rental Stock Out/Inbound/ */
    private static final int RNTL_DROP_SHIP_RNTL_STK_O_INBD = RNTL + 3;
    /** Rental/Rental Stock Out Intangible with Cost/OM/ */
    private static final int RNTL_RNTL_STK_O_INTG_WITH_COST_OM = RNTL + 4;
    /** Rental/Rental To Asset Return/Inbound/ */
    private static final int RNTL_RNTL_TO_AST_RTRN_INBD = RNTL + 5;
//    /** Rental/Rental To Asset Return/Return/ */
//    private static final int RNTL_RNTL_TO_AST_RTRN_RTRN = RNTL + 6;
//    /** Rental/Rental Shipment Stock Out Asset/OM/ */
//    private static final int RNTL_RNTL_SHIP_STK_O_AST_OM = RNTL + 7;
    /** Rental/Rental Shipment Stock Out Expense/OM/ */
    private static final int RNTL_RNTL_SHIP_STK_O_EXP_OM = RNTL + 8;
    /** Rental/Rental Shipment Stock Out Expense/OM/Intangible with cost/ */
    private static final int RNTL_RNTL_SHIP_STK_O_EXP_OM_INTG_WITH_COST = RNTL + 9;
    /** Rental/Rental Shipment Stock Out Expense/OM/Sell Then Buy */
    private static final int RNTL_RNTL_SHIP_STK_O_EXP_OM_SELL_THEN_BUY = RNTL + 10;

    //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) START
    /** Rental/Rental Return/Initial Supply */
    private static final int RNTL_RTRN_INIT_SUPPLY = RNTL + 11;
    //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) END

    // 2017/10/20 S21_NA#16347 Add Start
    /** Sales/Rental Shipment Stock-Out/OM */
    private static final int RNTL_SHIP_STK_OUT = RNTL + 12;
    /** Sales/Rental Shipment Stock-Out Expense/OM */
    private static final int RNTL_SHIP_STK_OUT_EXP = RNTL + 13;
    // 2017/10/20 S21_NA#16347 Add End

    /** Adjustment */
    private static final int ADJ = 600;
    /** Adjustment/Adjustment/Inventory/ */
    private static final int ADJ_ADJ_INVTY = ADJ + 1;
    /** Adjustment/Disposal/Outbound/ */
    private static final int ADJ_DSPL_OTBD = ADJ + 2;
//    /** Adjustment/Loan to Disposal/OM/ */
//    private static final int ADJ_LOAN_TO_DSPL_OM = ADJ + 3;
    /** Adjustment/Item Change Stock Out/Inbound/Item Change Screen */
    private static final int ADJ_ITM_CHNG_STK_O_INBD_ITM_CHNG_SCR = ADJ + 4;
    /** Adjustment/Item Change Stock In/Inbound/Item Change Screen */
    private static final int ADJ_ITM_CHNG_STK_I_INBD_ITM_CHNG_SCR = ADJ + 5;
//    /** Adjustment/Item Change Stock Out/Return/Unkit by Batch */
//    private static final int ADJ_ITM_CHNG_STK_O_RTRN_UNKIT_BY_BAT = ADJ + 6;
//    /** Adjustment/Item Change Stock In/Return/Unkit by Batch */
//    private static final int ADJ_ITM_CHNG_STK_I_RTRN_UNKIT_BY_BAT = ADJ + 7;
//    /** Adjustment/Item Change Stock Out From Vendor/Inbound/Kitting & Refurbish */
//    private static final int ADJ_ITM_CHNG_STK_O_VND_INBD_KIT_AND_RFRB = ADJ + 8;
//    /** Adjustment/Item Change Stock In From Vendor/Inbound/Kitting & Refurbish */
//    private static final int ADJ_ITM_CHNG_STK_I_VND_INBD_KIT_AND_RFRB = ADJ + 9;
    /** Adjustment/Asset Disposal/Outbound/ */
    private static final int ADJ_AST_DSPL_OTBD = ADJ + 10;
//    /** Adjustment/Asset Item Change Stock Out/Inbound/Item Change Screen */
//    private static final int ADJ_AST_ITM_CHNG_STK_O_INBD_ITM_CHNG_SCR = ADJ + 11;
//    /** Adjustment/Asset Item Change Stock In/Inbound/Item Change Screen */
//    private static final int ADJ_AST_ITM_CHNG_STK_I_INBD_ITM_CHNG_SCR = ADJ + 12;
    /** Adjustment/Kitting Item Change Stock Out/Inbound/Kitting */
    private static final int ADJ_KIT_ITM_CHNG_STK_O_INBD_KIT_AND_RFRB = ADJ + 13;
    /** Adjustment/Kitting Item Change Stock In/Inbound/Kitting */
    private static final int ADJ_KIT_ITM_CHNG_STK_I_INBD_KIT_AND_RFRB = ADJ + 14;
    /** Adjustment/Cycle Count Adjustment/ */
    private static final int ADJ_CYCLE_COUNT_ADJ = ADJ + 15;
    /** Adjustment/PhysicalInventory Adjustment */
    private static final int ADJ_PHYS_INVTY_ADJ = ADJ + 16;
    /** Adjustment/Refurb Vendor Transfer Stock Out from Vendor */
    private static final int ADJ_RFRB_VND_TRNSF_STK_O_FROM_VND = ADJ + 17;
    /** Adjustment/Refurb Expense Ship Out */
    private static final int ADJ_RFRB_EXP_SHIP_O = ADJ + 18;
    /** Adjustment/Buy Back Stock Out */
    private static final int ADJ_BUY_BACK_STK_O = ADJ + 19;
    /** Adjustment/WH Transfer In-Transit Shortage Out/Inbound/Receiving Confirmation */
    private static final int ADJ_WH_TRNSF_ITRNST_SHORT_OUT_INBD_RCV_CONF = ADJ + 20;
    /** Adjustment/WH Transfer In-Transit Shortage Out/OM/Receiving Confirmation */
    private static final int ADJ_WH_TRNSF_ITRNST_SHORT_OUT_OM_RCV_CONF = ADJ + 21;
    // MOD Start 03/19/2018 [QC#12110]
    /** Adjustment/Purchase Order Expense Out */
    private static final int ADJ_REPLEN_TOOL_EXPENSE_OUT_PO = ADJ + 22;
    /** Adjustment/WH Transfer Expense Out */
    private static final int ADJ_REPLEN_TOOL_EXPENSE_OUT_WH_TRSF = ADJ + 23;
    // MOD End 03/19/2018 [QC#12110]
    

//    /** Insurance Claim */
//    private static final int INS_CLM = 700;
//    /** Insurance Claim/Loss on Shipment/Inventory/ */
//    private static final int INS_CLM_LOSS_ON_SHIP_INVTY = INS_CLM + 1;
//    /** Insurance Claim/Loss on Receiving/Inventory/ */
//    private static final int INS_CLM_LOSS_ON_RCV_INVTY = INS_CLM + 2;

    /** Movement */
    private static final int MOVE = 800;
//    /** Movement/In-Transit Stock In/Inbound/ */
//    private static final int MOVE_ITRNST_STK_I_INBD = MOVE + 1;
//    /** Movement/In-Transit Stock Out/Inbound/ */
//    private static final int MOVE_ITRNST_STK_O_INBD = MOVE + 2;
    /** Movement/In-Transit Stock In/Inbound/Domestic */
    private static final int MOVE_ITRNST_STK_I_INBD_DOM = MOVE + 3;
    /** Movement/In-Transit Stock Out/Inbound/Domestic */
    private static final int MOVE_ITRNST_STK_O_INBD_DOM = MOVE + 4;
    /** Movement/WH Transfer Stock Out/Outbound/Ship Confirmation */
    private static final int MOVE_WH_TRNSF_STK_O_OTBD_SHIP_CONF = MOVE + 5;
    /** Movement/WH Transfer Stock In/Inbound/Receive Confirmation */
    private static final int MOVE_WH_TRNSF_STK_I_INBD_RCV_CONF = MOVE + 6;
    /** Movement/WH Transfer In-Transit Stock In/Outbound/Ship Confirmation */
    private static final int MOVE_WH_TRNSF_ITRNST_STK_I_OTBD_SHIP_CONF = MOVE + 7;
    /** Movement/WH Transfer In-Transit Stock Out/Inbound/Receive Confirmation */
    private static final int MOVE_WH_TRNSF_ITRNST_STK_O_INBD_RCV_CONF = MOVE + 8;
    /** Movement/Status Change Stock Out/Inbound/ */
    private static final int MOVE_STS_CHNG_STK_O_INBD = MOVE + 9;
    /** Movement/Status Change Stock In/Inbound/ */
    private static final int MOVE_STS_CHNG_STK_I_INBD = MOVE + 10;
//  /** Movement/Status Change Stock Out/Inventory/ */
//  private static final int MOVE_STS_CHNG_STK_O_INVTY = MOVE + 11;
//  /** Movement/Status Change Stock In/Inventory/ */
//  private static final int MOVE_STS_CHNG_STK_I_INVTY = MOVE + 12;
//  /** Movement/Domestic Shipment Stock Out/Outbound/Regular Sales */
//  private static final int MOVE_DOM_SHIP_STK_O_OTBD_REG_SLS = MOVE + 13;
//  /** Movement/Domestic Shipment Stock In/Outbound/Regular Sales */
//  private static final int MOVE_DOM_SHIP_STK_I_OTBD_REG_SLS = MOVE + 14;
//  /** Movement/Domestic Shipment Stock Out/Outbound/Expense */
//  private static final int MOVE_DOM_SHIP_STK_O_OTBD_EXP = MOVE + 15;
//  /** Movement/Domestic Shipment Stock In/Outbound/Expense */
//  private static final int MOVE_DOM_SHIP_STK_I_OTBD_EXP = MOVE + 16;
//  /** Movement/Trial Shipment Stock Out/Outbound/Trial Ship */
//  private static final int MOVE_TRL_SHIP_STK_O_OTBD_TRL_SHIP = MOVE + 17;
    /** Movement/Loan Shipment Stock Out/Outbound/Trial Ship */
    private static final int MOVE_LOAN_SHIP_STK_O_OTBD_TRL_SHIP = MOVE + 17;
//  /** Movement/Trial Shipment Stock In/Outbound/Trial Ship */
//  private static final int MOVE_TRL_SHIP_STK_I_OTBD_TRL_SHIP = MOVE + 18;
    /** Movement/Loan Shipment Stock In/Outbound/Trial Ship */
    private static final int MOVE_LOAN_SHIP_STK_I_OTBD_TRL_SHIP = MOVE + 18;
//  /** Movement/Trial to Inventory Stock Out/Inbound/Trial to Inventory */
//  private static final int MOVE_TRL_INVTY_STK_O_INBD_TRL_INVTY = MOVE + 19;
    /** Movement/Loan to Inventory Stock Out/Inbound/Trial to Inventory */
    private static final int MOVE_LOAN_INVTY_STK_O_INBD_TRL_INVTY = MOVE + 19;
//  /** Movement/Trial to Inventory Stock Out/Return/Trial to Inventory */
//  private static final int MOVE_TRL_INVTY_STK_O_RTRN_TRL_INVTY = MOVE + 20;
//  /** Movement/Trial to Inventory Stock In/Inbound/Trial to Inventory */
//  private static final int MOVE_TRL_INVTY_STK_I_INBD_TRL_INVTY = MOVE + 21;
    /** Movement/Loan to Inventory Stock In/Inbound/Trial to Inventory */
    private static final int MOVE_LOAN_INVTY_STK_I_INBD_TRL_INVTY = MOVE + 21;
//  /** Movement/Trial to Inventory Stock In/Return/Trial to Inventory */
//  private static final int MOVE_TRL_INVTY_STK_I_RTRN_TRL_INVTY = MOVE + 22;
//  /** Movement/Trial to Inventory Stock Out/OM/Trial to Inventory (Loss on Ship) */
//  private static final int MOVE_TRL_INVTY_STK_O_OM_TRL_INVTY_LS = MOVE + 23;
//  /** Movement/Trial to Inventory Stock In/OM/Trial to Inventory (Loss on Ship) */
//  private static final int MOVE_TRL_INVTY_STK_I_OM_TRL_INVTY_LS = MOVE + 24;
    /** Movement/Direct Sale Shipment Stock Out/Outbound/ROSS */
    private static final int MOVE_DRCT_SLS_SHIP_STK_O_OTBD_ROSS = MOVE + 25;
    /** Movement/Direct Sale Shipment Stock In/Outbound/ROSS */
    private static final int MOVE_DRCT_SLS_SHIP_STK_I_OTBD_ROSS = MOVE + 26;
    // QC#63527 2024/03/14 Start 
    /** Movement/Off The Book Loan Shipment Stock Out/Outbound/Trial Ship */
    private static final int MOVE_OFF_THE_BOOK_LOAN_SHIP_STK_O_OTBD_TRL_SHIP = MOVE + 27;
    /** Movement/Off The Book Loan Shipment Stock In/Outbound/Trial Ship */
    private static final int MOVE_OFF_THE_BOOK_LOAN_SHIP_STK_I_OTBD_TRL_SHIP = MOVE + 28;
    /** Movement/Off The Book Loan to Inventory Stock Out/Inbound/Trial to Inventory */
    private static final int MOVE_OFF_THE_BOOK_LOAN_INVTY_STK_O_INBD_TRL_INVTY = MOVE + 29;
    /** Movement/Off The Book Loan to Inventory Stock In/Inbound/Trial to Inventory */
    private static final int MOVE_OFF_THE_BOOK_LOAN_INVTY_STK_I_INBD_TRL_INVTY = MOVE + 31;
    // QC#63527 2024/03/14 End
//  /** Movement/Direct Sale Shipment Cancel Stock Out/Inbound/ROSS */
//  private static final int MOVE_DRCT_SLS_SHIP_CANC_STK_O_INBD_ROSS = MOVE + 27;
//  /** Movement/Direct Sale Shipment Cancel Stock Out/Return/ROSS */
//  private static final int MOVE_DRCT_SLS_SHIP_CANC_STK_O_RTRN_ROSS = MOVE + 28;
//  /** Movement/Direct Sale Shipment Cancel Stock Out/ROSS/ROSS */
//  private static final int MOVE_DRCT_SLS_SHIP_CANC_STK_O_ROSS_ROSS = MOVE + 29;
//  /** Movement/Direct Sale Shipment Cancel Stock In/Inbound/ROSS */
//  private static final int MOVE_DRCT_SLS_SHIP_CANC_STK_I_INBD_ROSS = MOVE + 30;
//  /** Movement/Direct Sale Shipment Cancel Stock In//Return/ROSS */
//  private static final int MOVE_DRCT_SLS_SHIP_CANC_STK_I_RTRN_ROSS = MOVE + 31;
//  /** Movement/Direct Sale Shipment Cancel Stock In/ROSS/ROSS */
//  private static final int MOVE_DRCT_SLS_SHIP_CANC_STK_I_ROSS_ROSS = MOVE + 32;
//  /** Movement/Vendor Transfer Stock Out/Outbound/Ship Confirmation */
//  private static final int MOVE_VND_TRNSF_STK_O_OTBD_SHIP_CONF = MOVE + 33;
//  /** Movement/Vendor Transfer Stock In/Outbound/Ship Confirmation */
//  private static final int MOVE_VND_TRNSF_STK_I_OTBD_SHIP_CONF = MOVE + 34;
//  /** Movement/Vendor Transfer Stock Out From Vendor/Inbound/Receive Confirmation */
//  private static final int MOVE_VND_TRNSF_STK_O_VND_INBD_RCV_CONF = MOVE + 35;
//  /** Movement/Vendor Transfer Stock In From Vendor/Inbound/Receive Confirmation */
//  private static final int MOVE_VND_TRNSF_STK_I_VND_INBD_RCV_CONF = MOVE + 36;
//  /** Movement/Vendor Transfer Stock Out From Vendor/Procurment */
//  private static final int MOVE_VND_TRNSF_STK_O_VND_PROCR = MOVE + 37;
//  /** Movement/Vendor Transfer Stock In From Vendor/Procurment */
//  private static final int MOVE_VND_TRNSF_STK_I_VND_PROCR = MOVE + 38;
//  /** Movement/Location Status Change Stock Out/Inbound/ */
//  private static final int MOVE_LOC_STS_CHNG_STK_O_INBD = MOVE + 39;
    /** Movement/Location Status Change Stock Out/Outbound/Ship Confirmation/ */
    private static final int MOVE_LOC_STS_CHNG_STK_O_OTBD_SHIP_CONF = MOVE + 39;
//  /** Movement/Location Status Change Stock In/Inbound/ */
//  private static final int MOVE_LOC_STS_CHNG_STK_I_INBD = MOVE + 40;
    /** Movement/Location Status Change Stock In/Outbound/Ship Confirmation/ */
    private static final int MOVE_LOC_STS_CHNG_STK_I_OTBD_SHIP_CONF = MOVE + 40;
//  /** Movement/Ownership Change Stock Out/OM/ */
//  private static final int MOVE_OWNR_CHNG_STK_O_OM = MOVE + 41;
//  /** Movement/Ownership Change Stock In/OM/ */
//  private static final int MOVE_OWNR_CHNG_STK_I_OM = MOVE + 42;
//  /** Movement/Drop Shipment Stock Out/InBound/Regular Sales */
//  private static final int MOVE_DROP_SHIP_STK_O_INBD_REG_SLS = MOVE + 43;
//  /** Movement/Drop Shipment Stock In/InBound/Regular Sales */
//  private static final int MOVE_DROP_SHIP_STK_I_INBD_REG_SLS = MOVE + 44;
//  /** Movement/Drop Shipment Trial Stock Out/Inbound/Trail Ship */
//  private static final int MOVE_DROP_SHIP_TRL_STK_O_INBD_TRL_SHIP = MOVE + 45;
    /** Movement/Drop Shipment Loan Stock Out/Inbound/Trail Ship */
    private static final int MOVE_DROP_SHIP_LOAN_STK_O_INBD_TRL_SHIP = MOVE + 45;
//  /** Movement/Drop Shipment Trial Stock In/Inbound/Trail Ship */
//  private static final int MOVE_DROP_SHIP_TRL_STK_I_INBD_TRL_SHIP = MOVE + 46;
    /** Movement/Drop Shipment Loan Stock In/Inbound/Trail Ship */
    private static final int MOVE_DROP_SHIP_LOAN_STK_I_INBD_TRL_SHIP = MOVE + 46;
//  /** Movement/Export Shipment Stock Out/Outbound/ */
//  private static final int MOVE_EXP_SHIP_STK_O_OTBD = MOVE + 47;
//  /** Movement/Export Shipment Stock In/Outbound/ */
//  private static final int MOVE_EXP_SHIP_STK_I_OTBD = MOVE + 48;
//  /** Movement/Export Trial Stock Out/OM/Trail Ship */
//  private static final int MOVE_EXP_TRL_STK_O_OM_TRL_SHIP = MOVE + 49;
//  /** Movement/Export Trial Stock In/OM/Trail Ship */
//  private static final int MOVE_EXP_TRL_STK_I_OM_TRL_SHIP = MOVE + 50;
//  /** Movement/Export Vendor Return Stock Out/Outbound/ */
//  private static final int MOVE_EXP_VND_RTRN_STK_O_OTBD = MOVE + 51;
//  /** Movement/Export Vendor Return Stock In/Outbound/ */
//  private static final int MOVE_EXP_VND_RTRN_STK_I_OTBD = MOVE + 52;
//  /** Movement/Drop Shipment Stock Out/Export/ */
//  private static final int MOVE_DROP_SHIPMENT_STOCK_OUT_EXPT = MOVE + 53;
//  /** Movement/Drop Shipment Stock In/Export/ */
//  private static final int MOVE_DROP_SHIPMENT_STOCK_IN_EXPT = MOVE + 54;
//  /** Movement/Trial Stock Out Intangible with Cost/OM/ */
//  private static final int MOVE_TRL_STK_O_INTG_WITH_COST_OM = MOVE + 55;
//  /** Movement/Trial Stock Out Intangible with Cost/Export/ */
//  private static final int MOVE_TRL_STK_O_INTG_WITH_COST_EXPT = MOVE + 56;
//  /** Movement/Trial Stock In Intangible with Cost/OM/ */
//  private static final int MOVE_TRL_STK_I_INTG_WITH_COST_OM = MOVE + 57;
//  /** Movement/Trial Stock In Intangible with Cost/Export/ */
//  private static final int MOVE_TRL_STK_I_INTG_WITH_COST_EXPT = MOVE + 58;
    /** Movement/Direct Sale Stock Out Intangible with Cost/ROSS/ */
    private static final int MOVE_DRCT_SLS_STK_O_INTG_WITH_COST_ROSS = MOVE + 59;
    /** Movement/Direct Sale Stock In Intangible with Cost/ROSS/ */
    private static final int MOVE_DRCT_SLS_STK_I_INTG_WITH_COST_ROSS = MOVE + 60;
    /** Movement/Internal W/H Transfer Stock Out/Out-Bound/Ship Confirmation */
    private static final int MOVE_INTERNAL_WH_TRNSF_STK_O_OTBD_SHIP_CONF = MOVE + 64;
    /** Movement/Internal W/H Transfer Stock In/In-Bound/Receive Confirmation */
    private static final int MOVE_INTERNAL_WH_TRNSF_STK_I_INBD_RCV_CONF = MOVE + 65;
//  /** Movement/Off-the-Book Stock-Out/Procurement/ */
//  private static final int MOVE_OFF_THE_BOOK_STK_O_PROCR = MOVE + 66;
    /** Movement/Off-the-Book Stock-Out/OM/ */
    private static final int MOVE_OFF_THE_BOOK_STK_O_OM = MOVE + 66;
//  /** Movement/Off-the-Book Stock-Out/Inventory/ */
//  private static final int MOVE_OFF_THE_BOOK_STK_O_INVTY = MOVE + 67;
    /** Movement/Off-the-Book Stock-In/Inbound/ */
    private static final int MOVE_OFF_THE_BOOK_STK_I_INBD = MOVE + 68;
//  /** Movement/Off-the-Book Stock-In/Inventory/ */
//  private static final int MOVE_OFF_THE_BOOK_STK_I_INVTY = MOVE + 69;
//    /** Movement/Off-the-Book Stock-In/Return/ */
//    private static final int MOVE_OFF_THE_BOOK_STK_I_RTRN = MOVE + 70;
    /** Movement/WH Transfer Stock Out/OM/Ship Confirmation */
    private static final int MOVE_WH_TRNSF_STK_O_OM_SHIP_CONF = MOVE + 71;
    /** Movement/WH Transfer Stock In/OM/Receive Confirmation */
    private static final int MOVE_WH_TRNSF_STK_I_OM_RCV_CONF = MOVE + 72;
    /** Movement/WH Transfer In-Transit Stock In/OM/Ship Confirmation */
    private static final int MOVE_WH_TRNSF_ITRNST_STK_I_OM_SHIP_CONF = MOVE + 73;
    /** Movement/WH Transfer In-Transit Stock Out/OM/Receive Confirmation */
    private static final int MOVE_WH_TRNSF_ITRNST_STK_O_OM_RCV_CONF = MOVE + 74;
    /** Movement/Asset WH Transfer Stock Out/OM/Ship Confirmation */
    private static final int MOVE_AST_WH_TRNSF_STK_O_OM_SHIP_CONF = MOVE + 75;
    /** Movement/Asset WH Transfer Stock In/OM/Receive Confirmation */
    private static final int MOVE_AST_WH_TRNSF_ITRNST_STK_I_OM_SHIP_CONF = MOVE + 76;
    /** Movement/Asset WH Transfer In-Transit Stock Out/OM/Receive Confirmation */
    private static final int MOVE_AST_WH_TRNSF_ITRNST_STK_O_OM_RCV_CONF = MOVE + 77;
    /** Movement/Asset WH Transfer In-Transit Stock Out/OM/Receive Confirmation */
    private static final int MOVE_AST_WH_TRNSF_STK_I_OM_RCV_CONF = MOVE + 78;
    /** Movement/Off-the-Book WH Transfer Stock Out/OM/Ship Confirmation */
    private static final int MOVE_OFF_THE_BOOK_WH_TRNSF_STK_O_OM_SHIP_CONF = MOVE + 79;
    /** Movement/Off-the-Book WH Transfer Stock In/OM/Receive Confirmation */
    private static final int MOVE_OFF_THE_BOOK_WH_TRNSF_ITRNST_STK_I_OM_SHIP_CONF = MOVE + 80;
    /** Movement/Off-the-Book WH Transfer In-Transit Stock Out/OM/Receive Confirmation */
    private static final int MOVE_OFF_THE_BOOK_WH_TRNSF_ITRNST_STK_O_OM_RCV_CONF = MOVE + 81;
    /** Movement/Off-the-Book WH Transfer In-Transit Stock Out/OM/Receive Confirmation */
    private static final int MOVE_OFF_THE_BOOK_WH_TRNSF_STK_I_OM_RCV_CONF = MOVE + 82;
    /** Movement/Showroom Transfer Stock Out/OM/Ship Confirmation */
    private static final int MOVE_SHRM_TRNSF_STK_O_OM_SHIP_CONF = MOVE + 83;
    /** Movement/Showroom Transfer Stock In/OM/Receive Confirmation */
    private static final int MOVE_SHRM_TRNSF_ITRNST_STK_I_OM_SHIP_CONF = MOVE + 84;
    /** Movement/Showroom Transfer In-Transit Stock In/OM/Ship Confirmation */
    private static final int MOVE_SHRM_TRNSF_ITRNST_STK_O_OM_RCV_CONF = MOVE + 85;
    /** Movement/Showroom Transfer In-Transit Stock Out/OM/Receive Confirmation */
    private static final int MOVE_SHRM_TRNSF_STK_I_OM_RCV_CONF = MOVE + 86;
    /** Movement/Direct Sale Shipment Stock Out Asset/Outbound/ */
    private static final int MOVE_DRCT_SLS_SHIP_STK_O_AST_OTBD = MOVE + 87;
    /** Movement/Direct Sale Shipment Stock In Asset/Outbound/ */
    private static final int MOVE_DRCT_SLS_SHIP_STK_I_AST_OTBD = MOVE + 88;
    /** Movement/Location Status Change Cancel Stock Out/Procurement/ */
    private static final int MOVE_LOC_STS_CHNG_CANC_STK_O_PROCR = MOVE + 89;
    /** Movement/Location Status Change Cancel Stock In/Procurement/ */
    private static final int MOVE_LOC_STS_CHNG_CANC_STK_I_PROCR = MOVE + 90;
    /** Movement/Refurbish Vendor Transfer Stock Out/Outbound/Ship Confirmation/ */
    private static final int MOVE_RFRB_VND_TRSF_STK_O_OTBD_SHIP_CONF = MOVE + 91;
    /** Movement/Refurbish Vendor Transfer Stock In/Outbound/Ship Confirmation/ */
    private static final int MOVE_RFRB_VND_TRSF_STK_I_OTBD_SHIP_CONF = MOVE + 92;
    /** Movement/Off-the-Book Shipment Stock-Out/Outbound/ */
    private static final int MOVE_OFF_THE_BOOK_SHIP_STK_O_OTBD = MOVE + 93;
    /** Movement/Off-the-Book Shipment Stock-In/Outbound/ */
    private static final int MOVE_OFF_THE_BOOK_SHIP_STK_I_OTBD = MOVE + 94;
    /** Movement/Asset WH Transfer In-Transit Shortage Out/OM/Receive Confirmation */
    private static final int MOVE_AST_WH_TRNSF_ITRNST_SHORT_O_OM_RCV_CONF = MOVE + 95;
    /** Movement/Off-the-Book WH Transfer In-Transit Stock In/OM/Ship Confirmation */
    private static final int MOVE_OFF_THE_BOOK_WH_TRNSF_ITRNST_SHORT_O_OM_RCV_CONF = MOVE + 96;
    /** Movement/Configuration Change Stock Out/Inbound/ */
    private static final int MOVE_CONFIG_CHNG_STK_O_INBD = MOVE + 97;
    /** Movement/Configuration Change Stock In/Inbound/ */
    private static final int MOVE_CONFIG_CHNG_STK_I_INBD = MOVE + 98;
    /** Movement/Reman Parts Transfer Stock-Out/Outbound/ */
    private static final int MOVE_REMAN_PARTS_TRSF_STK_OUT = MOVE + 50;
    /** Movement/Reman Parts Transfer Stock-IN/Outbound/ */
    private static final int MOVE_REMAN_PARTS_TRSF_STK_IN = MOVE + 51;
    /** Movement/Reman Parts Transfer Back Stock-Out/Outbound/ */
    private static final int MOVE_REMAN_PARTS_TRSF_BCK_STK_OUT = MOVE + 52;
    /** Movement/Reman Parts Transfer Back Stock-In/Outbound/ */
    private static final int MOVE_REMAN_PARTS_TRSF_BCK_STK_IN = MOVE + 53;

    /** Parts Usage */
   private static final int PARTS_USG = 900;
    /** Parts Usage/Parts Usage/Outbound/ */
    private static final int PARTS_USG_PARTS_USG_OTBD = PARTS_USG + 1;
    /** Parts Usage/Parts Usage Return/Inbound/ */
    private static final int PARTS_USG_PARTS_USG_RTRN_INBD = PARTS_USG + 2;

    /** Remanufacturing */
   private static final int REMAN = 1000;
    /** Remanufacturing/Parts Usage for Reman/Outbound/ */
    private static final int REMAN_PRT_USG_FOR_REMAN_OTBD = REMAN + 1;
    /** Remanufacturing/Reman Item Change Stock Out/Inbound/ */
    private static final int REMAN_REMAN_ITM_CHNG_STK_O_INBD = REMAN + 2;
    /** Remanufacturing/Reman Item Change Stock In/Inbound/ */
    private static final int REMAN_REMAN_ITM_CHNG_STK_I_INBD = REMAN + 3;
    /** Remanufacturing/Parts Usage return for Reman/Inbound/ */
    private static final int REMAN_PRT_USG_RTRN_FOR_REMAN_INBD = REMAN + 4;
    /** Remanufacturing/Reman Item Change Stock Out for Accessary/Inbound/ */
    private static final int REMAN_REMAN_ITM_CHNG_STK_O_INBD_ACSRY = REMAN + 5;
    /** Remanufacturing/Reman Item Change Stock In for Accessary/Inbound/ */
    private static final int REMAN_REMAN_ITM_CHNG_STK_I_INBD_ACSRY = REMAN + 6;

    /** Inventory Valuation */
   private static final int INVTY_VALUE = 1100;
    /** Inventory Valuation/Sub WH Transfer Stock Out/Inbound/ */
    private static final int INVTY_VALUE_SWH_TRNSF_STK_O_INBD = INVTY_VALUE + 1;
    /** Inventory Valuation/Sub WH Transfer Stock In/Inbound/ */
    private static final int INVTY_VALUE_SWH_TRNSF_STK_I_INBD = INVTY_VALUE + 2;

    /** EMSD Shipment */
   private static final int EMSD = 1200;
    /** EMSD Shipment/EMSD Shipment Stock Out/OM/ */
    private static final int EMSD_EMSD_SHIP_STK_O_OM = EMSD + 1;
    /** EMSD Shipment/Drop Shipment EMSD Stock Out/OM/ */
    private static final int EMSD_DROP_SHIP_EMSD_STK_O_OM = EMSD + 2;
    /** EMSD Shipment/EMSD Stock Out Intangible with Cost/OM/ */
    private static final int EMSD_EMSD_SHIP_STK_O_INTG_WITH_COST_OM = EMSD + 3;
    /** EMSD Shipment/EMSD To Asset Return/Inbound/ */
    private static final int EMSD_EMSD_TO_AST_RTRN_INBD = EMSD + 4;
    /** EMSD Shipment/EMSD Shipment Stock-Out Expense/OM/ */
    private static final int EMSD_EMSD_SHIP_STK_O_EXP_OM = EMSD + 5;
    /** EMSD Shipment/EMSD Shipment Stock-Out Expense/OM/Intangible with Cost/ */
    private static final int EMSD_EMSD_SHIP_STK_O_EXP_OM_INTG_WITH_COST = EMSD + 6;
    /** EMSD Shipment/EMSD Shipment Stock-Out Expense/OM/Sell then Buy/ */
    private static final int EMSD_EMSD_SHIP_STK_O_EXP_OM_SELL_THEN_BUY = EMSD + 7;
    /** EMSD Shipment/EMSD Shipment Stock-Out Asset/OM/ */
    private static final int EMSD_EMSD_SHIP_STK_O_AST_OM = EMSD + 8;

    //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) START
    /** EMSD Shipment/Return/EMSD Initial Supply */
    private static final int EMSD_RTRN_INIT_SUPPLY = EMSD + 9;
    //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) END

    // 2017/10/20 S21_NA#16347 Add Start
    /** Sales/EMSD Shipment Stock-Out/OM */
    private static final int EMSD_SHIP_STK_OUT = EMSD + 10;
    /** Sales/EMSD Shipment Stock-Out Expense/OM */
    private static final int EMSD_SHIP_STK_OUT_EXP = EMSD + 11;
    // 2017/10/20 S21_NA#16347 Add End

    // -- Other Internal constants --------------
    /** Return Code of EZDTBLAccessor : Normal end */
    private static final String RTNCD_NORMAL_END = "0000";
    /** Return Code of EZDTBLAccessor : Record Duplicate */
    private static final String RTNCD_DUPLICATE = "2300";
    /** Debug level for Debug */
    private static final int CST_DEBUG_MSG_LVL = 1;
    /** Minimam Qty */
    private static final BigDecimal MIN_QTY = new BigDecimal("-9999999999");
    /** Maximam Qty */
    private static final BigDecimal MAX_QTY = new BigDecimal("9999999999");

    // -- Other Internal Variable ---------------
    /** SSM client */
    private S21SsmBatchClient ssmBatchClient = null;
    /** Executed Allocation Flag */
    private String execAllocFlg = null;
    /** Var Char Name: COST_ROUND_OPTION */
    private static final String COST_ROUND_OPTION = "COST_ROUND_OPTION";
    /** Column Name: COST_PRECISION  */
    private static final String COST_PRECISION = "COST_PRECISION";
    /** Cost Round Option; Round up */
    private static final String ROUND_UP = "1";
    /** Cost Round Option; Round */
    private static final String ROUND = "2";

    /* 12/21/2015 CSAI Y.Imazu Add QC#793 START */
    // Loan Dummy Warehouse Code
    private static final String LOAN_DUMMY_WH_CD = "LOAN_DUMMY_WH_CD";

    // DS Condition Constant Group ID for Loan
    private static final String LOAN_SWH_COND_CONST_GRP_ID = "NLZC0020_LOAN_SWH";
    /* 12/21/2015 CSAI Y.Imazu Add QC#793 END */

    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NLZC002001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Inventory Update API (List)
     * </pre>
     * 
     * @param inpPrmMsgAry Input parameter list.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final List<NLZC002001PMsg> inpPrmMsgAry, final ONBATCH_TYPE onBatchType) {
        for (int i = 0; i < inpPrmMsgAry.size(); i++) {
            execute(inpPrmMsgAry.get(i), onBatchType);
        }
    }

    /**
     * <pre>
     * Inventory Update API
     * </pre>
     * 
     * @param inpPrmPMsg Input parameter
     * @param onBatchType Kind of Online or Batch
     */
    public void execute(final NLZC002001PMsg inpPrmPMsg, final ONBATCH_TYPE onBatchType) {

        // Create Message Map
        final S21ApiMessageMap msgMap = new S21ApiMessageMap(inpPrmPMsg);

        try {
            // Create TMsg
            INVTYTMsg invtyTMsg = new INVTYTMsg();
            INVTY_TRXTMsg invtyTrxTMsg = new INVTY_TRXTMsg();

            // Set common data.
            if (!setCommonData(msgMap, invtyTrxTMsg)) {
                return;
            }

            // Find the Inventory Master.
            if (!findInvty(msgMap, invtyTMsg)) {
                return;
            }

            // Set scenario data.
            if (!setScenarioData(msgMap, invtyTrxTMsg)) {
                return;
            }

            // Update Inventory Master
            if (!updateInvty(msgMap, invtyTMsg, invtyTrxTMsg.locTpCd.getValue(), onBatchType)) {
                return;
            }

            // Insert Inventory Transaction
            if (!insertInvtyTrx(msgMap, invtyTrxTMsg)) {
                return;
            }

            // Update Inventory Dtail Daily
            if (!updateInvtyDtlDly(msgMap, invtyTrxTMsg)) {
                return;
            }

        // locked by another user
        } catch (EZDDBRecordLockedException e) {
            printDebugLog("The subjective record has been locked by another user.");
            throw e;

        } finally {

            // Flush Message Map.
            msgMap.flush();
        }
    }

    /**
     * <pre>
     * Set common Data.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setCommonData(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Check the common parameter.
        if (!checkParameterCom(msgMap)) {
            return false;
        }

        // Find the Merchandise Master.
        if (!findMdse(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Find the Inventory Location Type and Name from Inventory Location View.
        if (!findInvtyLocTpAndNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Set other parameter for Inventory Transaction TMsg.
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        BigDecimal rqstQty = inpPrmPMsg.xxRqstQty.getValue();
        String rqstTpCd = inpPrmPMsg.xxRqstTpCd.getValue();
        invtyTrxTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
        invtyTrxTMsg.mdseCd.setValue(inpPrmPMsg.mdseCd.getValue());
        invtyTrxTMsg.invtyLocCd.setValue(inpPrmPMsg.invtyLocCd.getValue());
        invtyTrxTMsg.locStsCd.setValue(inpPrmPMsg.locStsCd.getValue());
        invtyTrxTMsg.stkStsCd.setValue(inpPrmPMsg.stkStsCd.getValue());
        invtyTrxTMsg.trxCd.setValue(inpPrmPMsg.trxCd.getValue());
        invtyTrxTMsg.trxRsnCd.setValue(inpPrmPMsg.trxRsnCd.getValue());
        invtyTrxTMsg.invtyTrxDt.setValue(inpPrmPMsg.invtyTrxDt.getValue());
        invtyTrxTMsg.sysSrcCd.setValue(inpPrmPMsg.sysSrcCd.getValue());
        invtyTrxTMsg.expProjCd.setValue(inpPrmPMsg.expProjCd.getValue());
        invtyTrxTMsg.estrIntfcFlg.setValue(ZYPConstant.FLG_OFF_N);
        invtyTrxTMsg.histCratCpltFlg.setValue(ZYPConstant.FLG_OFF_N);

        // Stock-In
        if (RQST_STOCK_IN.equals(rqstTpCd)) {

            // Inventory Qty = Request Qty
            invtyTrxTMsg.invtyTrxQty.setValue(rqstQty);

        // Stock-Out
        } else {

            // Inventory Qty = Request Qty * -1
            invtyTrxTMsg.invtyTrxQty.setValue(rqstQty.multiply(BigDecimal.ONE.negate()));

            // Initial Executed Allocation Flag
            if (!initExecAllocFlg(msgMap, invtyTrxTMsg)) {
                return false;
            }
        }

        return true;
    }

    /**
     * <pre>
     * Set Scenario Datas.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setScenarioData(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Get scenario number.
        int scenarioNo = decnScenario(msgMap);

        // unexpected
        if (UNEXPECTED == scenarioNo) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Scenario case");
            return false;

        // Purchase Stock-In
        } else if (scenarioNo - PRCH_STK_I < 100) {
            return setPrchStkI(msgMap, invtyTrxTMsg, scenarioNo);

        // Sales
        } else if (scenarioNo - SLS < 100) {
            return setSls(msgMap, invtyTrxTMsg, scenarioNo);

        // Expense
        } else if (scenarioNo - EXP < 100) {
            return setExp(msgMap, invtyTrxTMsg, scenarioNo);

//        // Loan
//        } else if (scenarioNo - LOAN < 100) {
//            return setLoan(msgMap, invtyTrxTMsg, scenarioNo);

        // Rental
        } else if (scenarioNo - RNTL < 100) {
            return setRntl(msgMap, invtyTrxTMsg, scenarioNo);

        // Adjustment
        } else if (scenarioNo - ADJ < 100) {
            return setAdj(msgMap, invtyTrxTMsg, scenarioNo);

//        // Insurance Claim
//        } else if (scenarioNo - INS_CLM < 100) {
//            return setInsClm(msgMap, invtyTrxTMsg, scenarioNo);

        // Movement
        } else if (scenarioNo - MOVE < 100) {
            return setMove(msgMap, invtyTrxTMsg, scenarioNo);

        // Parts Usage
        } else if (scenarioNo - PARTS_USG < 100) {
            return setPartsUsg(msgMap, invtyTrxTMsg, scenarioNo);

        // Remanufacturing
        } else if (scenarioNo - REMAN < 100) {
            return setReman(msgMap, invtyTrxTMsg, scenarioNo);

        // Inventory Valuation
        } else if (scenarioNo - INVTY_VALUE < 100) {
            return setInvtyValue(msgMap, invtyTrxTMsg, scenarioNo);

        // EMSD Shipment
        } else if (scenarioNo - EMSD < 100) {
            return setEMSDShip(msgMap, invtyTrxTMsg, scenarioNo);

        } else {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Scenario case");
            return false;
        }
    }

    /**
     * <pre>
     * Decision the scenario.
     * Transaction code, Reason code, System source code, Transaction detail code
     * to decision the scenario.
     * </pre>
     * 
     * @param msgMap Message Map
     * @return Scenario number
     */
    private int decnScenario(S21ApiMessageMap msgMap) {

        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        String trxCd = inpPrmPMsg.trxCd.getValue();
        String trxRsnCd = inpPrmPMsg.trxRsnCd.getValue();
        String sysTp = inpPrmPMsg.xxSysTp.getValue();
        String trxDtlCd = inpPrmPMsg.xxTrxDtlCd.getValue();

        // Transaction Code = Purchase Stock-In
        if (TRX.PURCHASE_STOCK_IN.equals(trxCd)) {
            return decnScenarioForPrchStkI(trxRsnCd, sysTp, trxDtlCd);

        // Transaction Code = Sales
        } else if (TRX.SALES.equals(trxCd)) {
            return decnScenarioForSls(trxRsnCd, sysTp, trxDtlCd);

        // Transaction Code = Expense
        } else if (TRX.EXPENSE_SHIPMENT.equals(trxCd)) {
            return decnScenarioForExp(trxRsnCd, sysTp, trxDtlCd);

//        // Transaction Code = Loan
//        } else if (TRX.LOAN_SHIPMENT.equals(trxCd)) {
//            return decnScenarioForLoan(trxRsnCd, sysTp, trxDtlCd);

        // Transaction Code = Rental
        } else if (TRX.RENTAL_SHIPMENT.equals(trxCd)) {
            return decnScenarioForRntl(trxRsnCd, sysTp, trxDtlCd);

        // Transaction Code = Adjustment
        } else if (TRX.ADJUSTMENT.equals(trxCd)) {
            return decnScenarioForAdj(trxRsnCd, sysTp, trxDtlCd);

//        // Transaction Code = Insurance Claim
//        } else if (TRX.INSURANCE_CLAIM.equals(trxCd)) {
//            return decisionScenarioForInsClm(trxRsnCd, sysTp, trxDtlCd);

        // Transaction Code = Movement
        } else if (TRX.MOVEMENT.equals(trxCd)) {
            return decnScenarioForMove(trxRsnCd, sysTp, trxDtlCd);

        // Transaction Code = Parts Usage
        } else if (TRX.PARTS_USAGE.equals(trxCd)) {
            return decnScenarioForPartsUsg(trxRsnCd, sysTp, trxDtlCd);

        // Transaction Code = Remanufacturing
        } else if (TRX.REMANUFACTURING.equals(trxCd)) {
            return decnScenarioForReman(trxRsnCd, sysTp, trxDtlCd);

        // Transaction Code = Inventory Valuation
        } else if (TRX.INVENTORY_VALUATION.equals(trxCd)) {
            return decnScenarioForInvtyValue(trxRsnCd, sysTp, trxDtlCd);

        // Transaction Code = EMSD Shipment
        } else if (TRX.EMSD_SHIPMENT.equals(trxCd)) {
            return decnScenarioForEmsdShip(trxRsnCd, sysTp, trxDtlCd);
        }

        return UNEXPECTED;
    }

    /**
     * <pre>
     * Transaction code is "Purchase Stock-In" to decision the scenario.
     * Reason code, System source code, Transaction detail code
     * to decision the scenario.
     * </pre>
     * 
     * @param trxRsnCd Transaction Reason Code
     * @param sysTp System Type
     * @param trxDtlCd Transaction Detail Code
     * @return Scenario number
     */
    private int decnScenarioForPrchStkI(String trxRsnCd, String sysTp, String trxDtlCd) {

        // Reason Code = Purchase Stock-In
        if (TRX_RSN.PURCHASE_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Domestic
                if (TRX_DTL_DOM.equals(trxDtlCd)) {
                    return PRCH_STK_I_PRCH_STK_I_INBD_DOM;

                // Transaction Detail = Sell Then Buy
                } else if (TRX_DTL_SELL_THEN_BUY.equals(trxDtlCd)) {
                    return PRCH_STK_I_PRCH_STK_I_INBD_SELL_THEN_BUY;
                }

            // System Type = Inventory
            } else if (SYS_TP_INVTY.equals(sysTp)) {

                // Transaction Detail = Item Entry SP
                if (TRX_DTL_ITM_ENTY_SP.equals(trxDtlCd)) {
                    return PRCH_STK_I_PRCH_STK_I_INVTY_ITM_ENTY_SP;
                }

            // System Type = OM
            } else if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Intangible with Cost
                if (TRX_DTL_INTG_WITH_COST.equals(trxDtlCd)) {
                    return PRCH_STK_I_PRCH_STK_I_OM_INTG_WITH_COST;
                }

            // System Type = Procurement
            } else if (SYS_TP_PROCR.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return PRCH_STK_I_PRCH_STK_I_SUB_CONTR;
                }
            }

        // Reason Code = Domestic Vendor Return
        } else if (TRX_RSN.DOMESTIC_VENDOR_RETURN.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return PRCH_STK_I_DOM_VND_RTRN_OTBD;
                }
            }
        }

        return UNEXPECTED;
    }

    /**
     * <pre>
     * Transaction code is "Sales" to decision the scenario.
     * Reason code, System source code, Transaction detail code
     * to decision the scenario.
     * </pre>
     * 
     * @param trxRsnCd Transaction Reason Code
     * @param sysTp System Type
     * @param trxDtlCd Transaction Detail Code
     * @return Scenario number
     */
    private int decnScenarioForSls(String trxRsnCd, String sysTp, String trxDtlCd) {

        // Reason Code = Regular Sales Cash Lease - MDSE
        if (TRX_RSN.REGULAR_SALES_CASH_LEASE_MDSE.equals(trxRsnCd)) {

          // System Type = OM
          if (SYS_TP_OM.equals(sysTp)) {

              // Transaction Detail = null
              if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                  return SLS_REG_SLS_WITH_COST_OM;

              // Transaction Detail = Intangible with Cost
              } else if (TRX_DTL_INTG_WITH_COST.equals(trxDtlCd)) {
                  return SLS_REG_SLS_WITH_COST_OM_INTG_WITH_COST;
              }
          }

        // Reason Code = Regular Sales Cash Lease - INIT Supply
        } else if (TRX_RSN.REGULAR_SALES_CASH_LEASE_INIT_SUP.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return SLS_REG_SLS_WITH_COST_OM;

                // Transaction Detail = Intangible with Cost
                } else if (TRX_DTL_INTG_WITH_COST.equals(trxDtlCd)) {
                    return SLS_REG_SLS_WITH_COST_OM_INTG_WITH_COST;
                }
            }

        // Reason Code = Regular Sales Cash Lease - Supply
        } else if (TRX_RSN.REGULAR_SALES_CASH_LEASE_SUP.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return SLS_REG_SLS_WITH_COST_OM;

                // Transaction Detail = Intangible with Cost
                } else if (TRX_DTL_INTG_WITH_COST.equals(trxDtlCd)) {
                    return SLS_REG_SLS_WITH_COST_OM_INTG_WITH_COST;
                }
            }

        // Reason Code = Regular Sales Cash Lease - Contract Supply
        } else if (TRX_RSN.REGULAR_SALES_CASH_LEASE_CNTR_SUP.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return SLS_REG_SLS_WITH_COST_OM;

                // Transaction Detail = Intangible with Cost
                } else if (TRX_DTL_INTG_WITH_COST.equals(trxDtlCd)) {
                    return SLS_REG_SLS_WITH_COST_OM_INTG_WITH_COST;
                }
            }

        // Reason Code = Credit & Rebill
        } else if (TRX_RSN.CREDIT_AND_REBILL.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return SLS_CR_AND_REBIL_OM;
                }
            }

        // Reason Code = Return
        } else if (TRX_RSN.RETURN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return SLS_RTRN_INBD;
                }
            }

        // Reason Code = Loan to Sale
        } else if (TRX_RSN.LOAN_TO_SALES.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return SLS_LOAN_TO_SLS_OM;
                }
            }
        //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) START
        } else if (TRX_RSN.RETURN_INITIAL_SUPPLY.equals(trxRsnCd)) {
            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return SLS_RTRN_INBD_INIT_SUPPLY;
                }
            }
        } else if (TRX_RSN.RETURN_CONTRACT_SUPPLY.equals(trxRsnCd)) {
            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return SLS_RTRN_INBD_CNTR_SUPPLY;
                }
            }
        // 2017/10/20 S21_NA#16347 Add Start
        // Reason Code = CASH LEASE - INIT SUP
        } else if (TRX_RSN.CASH_LEASE_INIT_SUP_CREDIT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return SLS_CASH_LEASE_INIT_SUP;
                }
            }
        // Reason Code = CASH LEASE - CNTR SUP
        } else if (TRX_RSN.CASH_LEASE_CNTR_SUP_CREDIT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return SLS_CASH_LEASE_CNTR_SUP;
                }
            }
        // Reason Code = Loan to Sales
        } else if (TRX_RSN.LOAN_TO_SALES_CREDIT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return SLS_LOAN_TO_SALES;
                }
            }
        // Reason Code = Regular Sales Asset for AJE Link
        } else if (TRX_RSN.REGULAR_SALES_ASSET_FOR_AJE_LINK_CREDIT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return SLS_REG_SALES_AJE_LINK;
                }
            }
        // 2017/10/20 S21_NA#16347 Add End
        }
        //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) END
        return UNEXPECTED;
    }

    /**
     * <pre>
     * Transaction code is "Expense" to decision the scenario.
     * Reason code, System source code, Transaction detail code
     * to decision the scenario.
     * </pre>
     * 
     * @param trxRsnCd Transaction Reason Code
     * @param sysTp System Type
     * @param trxDtlCd Transaction Detail Code
     * @return Scenario number
     */
    private int decnScenarioForExp(String trxRsnCd, String sysTp, String trxDtlCd) {

        // Reason Code = Expense Shipment
        if (TRX_RSN.EXPENSE_SHIPMENT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return EXP_EXP_SHIP_OM;

                // Transaction Detail = Intangible with Cost
                } else if (TRX_DTL_INTG_WITH_COST.equals(trxDtlCd)) {
                    return EXP_EXP_SHIP_OM_INTG_WITH_COST;
                }
            }

        // Reason Code = Expense Return
        } else if (TRX_RSN.EXPENSE_RETURN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return EXP_EXP_RTRN_INBD;
                }
            }

        // Reason Code = Expense Loan Shipment
        } else if (TRX_RSN.EXPENSE_LOAN_SHIPMENT_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = Trial Ship
                if (TRX_DTL_TRIAL_SHIP.equals(trxDtlCd)) {
                    return EXP_LOAN_SHIP_STK_O;
                }
            }

        // Reason Code = Loan to Inventory Shortage
        } else if (TRX_RSN.EXPENSE_LOAN_TO_INVTY_SHORT_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Trial to Inventory
                if (TRX_DTL_TRIAL_TO_INVTY.equals(trxDtlCd)) {
                    return EXP_LOAN_TO_INVTY_SHORT_STK_O;
                }
            }

        // QC#63527 2024/03/14 Start
        // Reason Code = Off The Book Expense Loan Shipment
        }  else if (TRX_RSN.OFF_THE_BOOK_EXPENSE_LOAN_SHIPMENT_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = Trial Ship
                if (TRX_DTL_TRIAL_SHIP.equals(trxDtlCd)) {
                    return EXP_OFF_THE_BOOK_LOAN_SHIP_STK_O;
                }
            }

        // Reason Code = Off The Book Loan to Inventory Shortage
        } else if (TRX_RSN.OFF_THE_BOOK_EXPENSE_LOAN_TO_INVTY_SHORT_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Trial to Inventory
                if (TRX_DTL_TRIAL_TO_INVTY.equals(trxDtlCd)) {
                    return EXP_OFF_THE_BOOK_LOAN_TO_INVTY_SHORT_STK_O;
                }
            }
        // QC#63527 2024/03/14 End

        // Reason Code = Expense Loan Drop Shipment
        } else if (TRX_RSN.EXPENSE_LOAN_DROP_SHIPMENT_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Trial Ship
                if (TRX_DTL_TRIAL_SHIP.equals(trxDtlCd)) {
                    return EXP_LOAN_DROP_SHIP_STK_O;
                }
            }

        // Reason Code = Expense Loan Shipment Intangible with Cost
        } else if (TRX_RSN.EXPENSE_LOAN_SHIPMENT_STOCK_OUT_INTANGIBLE_WITH_COST.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Intangible with Cost
                if (TRX_DTL_INTG_WITH_COST.equals(trxDtlCd)) {
                    return EXP_LOAN_SHIP_STK_O_INTG_WITH_COST;
                }
            }
        }

        return UNEXPECTED;
    }

    /**
     * <pre>
     * Transaction code is "Rental" to decision the scenario.
     * Reason code, System source code, Transaction detail code
     * to decision the scenario.
     * </pre>
     * 
     * @param trxRsnCd Transaction Reason Code
     * @param sysTp System Type
     * @param trxDtlCd Transaction Detail Code
     * @return Scenario number
     */
    private int decnScenarioForRntl(String trxRsnCd, String sysTp, String trxDtlCd) {

        // Reason Code = Rental Stock Out
        if (TRX_RSN.RENTAL_SHIPMENT_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return RNTL_RNTL_STK_O_OM;
                }
            }

        // Reason Code = Drop Shipment Rental Stock Out
        } else if (TRX_RSN.DROP_SHIPMENT_RENTAL_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return RNTL_DROP_SHIP_RNTL_STK_O_INBD;
                }
            }

        // Reason Code = Rental Stock Out Intangible with Cost
        } else if (TRX_RSN.RENTAL_STOCK_OUT_INTANGIBLE_WITH_COST.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return RNTL_RNTL_STK_O_INTG_WITH_COST_OM;
                }
            }

        // Reason Code = Rental To Asset Return
        } else if (TRX_RSN.RENTAL_TO_ASSET_RETURN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return RNTL_RNTL_TO_AST_RTRN_INBD;
                }
            }

        // Reason Code = Rental Shipment Stock Out Expense
        } else if (TRX_RSN.RENTAL_SHIPMENT_STOCK_OUT_EXPENSE.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return RNTL_RNTL_SHIP_STK_O_EXP_OM;

                } else if (TRX_DTL_INTG_WITH_COST.equals(trxDtlCd)) {
                    return RNTL_RNTL_SHIP_STK_O_EXP_OM_INTG_WITH_COST;

                } else if (TRX_DTL_SELL_THEN_BUY.equals(trxDtlCd)) {
                    return RNTL_RNTL_SHIP_STK_O_EXP_OM_SELL_THEN_BUY;
                }
            }
        //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) START
        } else if (TRX_RSN.RETURN_RENTAL_INITIAL_SUPPLY.equals(trxRsnCd)) {
            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return RNTL_RTRN_INIT_SUPPLY;
                }
            }
        // 2017/10/20 S21_NA#16347 Add Start
        // Reason Code = Rental Shipment Stock-Out
        } else if (TRX_RSN.RENTAL_SHIPMENT_STOCK_OUT_CREDIT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return RNTL_SHIP_STK_OUT;
                }
            }
        // Reason Code = Rental Shipment Stock-Out Expense
        } else if (TRX_RSN.RENTAL_SHIPMENT_STOCK_OUT_EXPENSE_CREDIT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return RNTL_SHIP_STK_OUT_EXP;
                }
            }
        // 2017/10/20 S21_NA#16347 Add End
        }
        //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) START
        return UNEXPECTED;
    }

    /**
     * <pre>
     * Transaction code is "Adjustment" to decision the scenario.
     * Reason code, System source code, Transaction detail code
     * to decision the scenario.
     * </pre>
     * 
     * @param trxRsnCd Transaction Reason Code
     * @param sysTp System Type
     * @param trxDtlCd Transaction Detail Code
     * @return Scenario number
     */
    private int decnScenarioForAdj(String trxRsnCd, String sysTp, String trxDtlCd) {

        // Reason Code = Adjustment(01)
        if (TRX_RSN.ADJUSTMENT.equals(trxRsnCd)) {

            // System Type = Inventory
            if (SYS_TP_INVTY.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return ADJ_ADJ_INVTY;
                }
            }

        // Reason Code = Disposal(02)
        } else if (TRX_RSN.DISPOSAL.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return ADJ_DSPL_OTBD;
                }
            }

        // Reason Code = Item Change Stock Out(06)
        } else if (TRX_RSN.ITEM_CHANGE_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Item Change Screen
                if (TRX_DTL_ITM_CHNG_SCR.equals(trxDtlCd)) {
                    return ADJ_ITM_CHNG_STK_O_INBD_ITM_CHNG_SCR;
                }
            }

        // Reason Code = Item Change Stock In(07)
        } else if (TRX_RSN.ITEM_CHANGE_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Item Change Screen
                if (TRX_DTL_ITM_CHNG_SCR.equals(trxDtlCd)) {
                    return ADJ_ITM_CHNG_STK_I_INBD_ITM_CHNG_SCR;
                }
            }

        // Reason Code = Asset Disposal(12)
        } else if (TRX_RSN.ASSET_DISPOSAL.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return ADJ_AST_DSPL_OTBD;
                }
            }

        // Reason Code = Cycle Count Adjustment(13)
        } else if (TRX_RSN.CYCLE_COUNT_ADJUSTMENT.equals(trxRsnCd)) {

            // System Type = Inventory
            if (SYS_TP_INVTY.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return ADJ_CYCLE_COUNT_ADJ;
                }
            }

        // Reason Code = Physical Inventory Adjustment(14)
        } else if (TRX_RSN.PHYSICAL_INVENTORY_ADJUSTMENT.equals(trxRsnCd)) {

            // System Type = Inventory
            if (SYS_TP_INVTY.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return ADJ_PHYS_INVTY_ADJ;
                }
            }

        // Reason Code = Refrb Vendor Transfer Stock Out from Vendor(15)
        } else if (TRX_RSN.REFURB_VENDOR_TRANSFER_STOCK_OUT_FROM_VENDOR.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return ADJ_RFRB_VND_TRNSF_STK_O_FROM_VND;
                }
            }

        // Reason Code = Refrb Expense Ship Out(16)
        } else if (TRX_RSN.REFURB_EXPENSE_SHIP_OUT.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return ADJ_RFRB_EXP_SHIP_O;
                }
            }

        // Reason Code = Buy Back Stock Out(17)
        } else if (TRX_RSN.BUY_BACK_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return ADJ_BUY_BACK_STK_O;
                }
            }

            // Reason Code = Kitting Item Change Stock Out(18)
        } else if (TRX_RSN.KITTING_ITEM_CHANGE_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Kitting
                if (TRX_DTL_KIT_AND_RFRB.equals(trxDtlCd)) {
                    return ADJ_KIT_ITM_CHNG_STK_O_INBD_KIT_AND_RFRB;
                }
            }

        // Reason Code = Kitting Item Change Stock In(19)
        } else if (TRX_RSN.KITTING_ITEM_CHANGE_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Kitting
                if (TRX_DTL_KIT_AND_RFRB.equals(trxDtlCd)) {
                    return ADJ_KIT_ITM_CHNG_STK_I_INBD_KIT_AND_RFRB;
                }
            }

        // Reason Code = Warehouse Transfer In-Transit Shortage Out(20)
        } else if (TRX_RSN.WAREHOUSE_TRANSFER_IN_TRANSIT_SHORTAGE_OUT.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Receive Confirmation
                if (TRX_DTL_RCV_CONF.equals(trxDtlCd)) {
                    return ADJ_WH_TRNSF_ITRNST_SHORT_OUT_INBD_RCV_CONF;
                }

                // System Type = Inbound
            } else if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Receive Confirmation
                if (TRX_DTL_RCV_CONF.equals(trxDtlCd)) {
                    return ADJ_WH_TRNSF_ITRNST_SHORT_OUT_OM_RCV_CONF;
                }
            }
        // QC#12110 Add New Trx-rsn. 
        // Reason Code = Replen Tool Expense Out (22).
        } else if(TRX_RSN.REPLEN_TOOL_EXPENSE_OUT.equals(trxRsnCd)){

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = Receive by PO
                if(TRX_DTL_RCV_PO.equals(trxDtlCd)){
                    return ADJ_REPLEN_TOOL_EXPENSE_OUT_PO;
                }
                // Transaction Detail = Receive by W/H Transfer
                if(TRX_DTL_RCV_WH_TRSF.equals(trxDtlCd)){
                    return ADJ_REPLEN_TOOL_EXPENSE_OUT_WH_TRSF;
                }
            }
        }

        return UNEXPECTED;
    }

    /**
     * <pre>
     * Transaction code is "Movement" to decision the scenario.
     * Reason code, System source code, Transaction detail code
     * to decision the scenario.
     * </pre>
     * 
     * @param trxRsnCd Transaction Reason Code
     * @param sysTp System Type
     * @param trxDtlCd Transaction Detail Code
     * @return Scenario number
     */
    private int decnScenarioForMove(String trxRsnCd, String sysTp, String trxDtlCd) {

        // Reason Code = In-Transit Stock In(01)
        if (TRX_RSN.INBOUND_IN_TRANSIT_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Domestic
                if (TRX_DTL_DOM.equals(trxDtlCd)) {
                    return MOVE_ITRNST_STK_I_INBD_DOM;
                }
            }

        // Reason Code = In-Transit Stock Out(02)
        } else if (TRX_RSN.INBOUND_IN_TRANSIT_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Domestic
                if (TRX_DTL_DOM.equals(trxDtlCd)) {
                    return MOVE_ITRNST_STK_O_INBD_DOM;
                }
            }

        // Reason Code = WH Transfer Stock Out(03)
        } else if (TRX_RSN.WAREHOUSE_TRANSFER_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = Ship Confirmation
                if (TRX_DTL_SHIP_CONF.equals(trxDtlCd)) {
                    return MOVE_WH_TRNSF_STK_O_OTBD_SHIP_CONF;
                }

            // System Type = OM
            } else if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Ship Confirmation
                if (TRX_DTL_SHIP_CONF.equals(trxDtlCd)) {
                    return MOVE_WH_TRNSF_STK_O_OM_SHIP_CONF;
                }
            }

        // Reason Code = WH Transfer In-Transit Stock In(04)
        } else if (TRX_RSN.WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = Ship Confirmation
                if (TRX_DTL_SHIP_CONF.equals(trxDtlCd)) {
                    return MOVE_WH_TRNSF_ITRNST_STK_I_OTBD_SHIP_CONF;
                }

            // System Type = OM
            } else if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Ship Confirmation
                if (TRX_DTL_SHIP_CONF.equals(trxDtlCd)) {
                    return MOVE_WH_TRNSF_ITRNST_STK_I_OM_SHIP_CONF;
                }
            }

        // Reason Code = WH Transfer In-Transit Stock Out(21)
        } else if (TRX_RSN.WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Receive Confirmation
                if (TRX_DTL_RCV_CONF.equals(trxDtlCd)) {
                    return MOVE_WH_TRNSF_ITRNST_STK_O_INBD_RCV_CONF;
                }

            // System Type = OM
            } else if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Receive Confirmation
                if (TRX_DTL_RCV_CONF.equals(trxDtlCd)) {
                    return MOVE_WH_TRNSF_ITRNST_STK_O_OM_RCV_CONF;
                }
            }

        // Reason Code = WH Transfer Stock In(22)
        } else if (TRX_RSN.WAREHOUSE_TRANSFER_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Receive Confirmation
                if (TRX_DTL_RCV_CONF.equals(trxDtlCd)) {
                    return MOVE_WH_TRNSF_STK_I_INBD_RCV_CONF;
                }

            // System Type = OM
            } else if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Receive Confirmation
                if (TRX_DTL_RCV_CONF.equals(trxDtlCd)) {
                    return MOVE_WH_TRNSF_STK_I_OM_RCV_CONF;
                }
            }

        // Reason Code = Asset WH Transfer Stock Out(50)
        } else if (TRX_RSN.ASSET_WAREHOUSE_TRANSFER_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Ship Confirmation
                if (TRX_DTL_SHIP_CONF.equals(trxDtlCd)) {
                    return MOVE_AST_WH_TRNSF_STK_O_OM_SHIP_CONF;
                }
            }

        // Reason Code = Asset WH Transfer In-Transit Stock In(51)
        } else if (TRX_RSN.ASSET_WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_IN.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Ship Confirmation
                if (TRX_DTL_SHIP_CONF.equals(trxDtlCd)) {
                    return MOVE_AST_WH_TRNSF_ITRNST_STK_I_OM_SHIP_CONF;
                }
            }

        // Reason Code = Asset WH Transfer In-Transit Stock Out(52)
        } else if (TRX_RSN.ASSET_WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Receive Confirmation
                if (TRX_DTL_RCV_CONF.equals(trxDtlCd)) {
                    return MOVE_AST_WH_TRNSF_ITRNST_STK_O_OM_RCV_CONF;
                }
            }

        // Reason Code = Asset WH Transfer Stock In(53)
        } else if (TRX_RSN.ASSET_WAREHOUSE_TRANSFER_STOCK_IN.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Receive Confirmation
                if (TRX_DTL_RCV_CONF.equals(trxDtlCd)) {
                    return MOVE_AST_WH_TRNSF_STK_I_OM_RCV_CONF;
                }
            }

        // Reason Code = Asset WH Transfer In-Transit Shortage Out(68)
        } else if (TRX_RSN.ASSET_WAREHOUSE_TRANSFER_IN_TRANSIT_SHORTAGE_OUT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Receive Confirmation
                if (TRX_DTL_RCV_CONF.equals(trxDtlCd)) {
                    return MOVE_AST_WH_TRNSF_ITRNST_SHORT_O_OM_RCV_CONF;
                }
            }

        // Reason Code = Off-the-Book WH Transfer Stock Out(54)
        } else if (TRX_RSN.OFF_THE_BOOK_WAREHOUSE_TRANSFER_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Ship Confirmation
                if (TRX_DTL_SHIP_CONF.equals(trxDtlCd)) {
                    return MOVE_OFF_THE_BOOK_WH_TRNSF_STK_O_OM_SHIP_CONF;
                }
            }

        // Reason Code = Off-the-Book WH Transfer In-Transit Stock In(55)
        } else if (TRX_RSN.OFF_THE_BOOK_WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_IN.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Ship Confirmation
                if (TRX_DTL_SHIP_CONF.equals(trxDtlCd)) {
                    return MOVE_OFF_THE_BOOK_WH_TRNSF_ITRNST_STK_I_OM_SHIP_CONF;
                }
            }

        // Reason Code = Off-the-Book WH Transfer In-Transit Stock Out(56)
        } else if (TRX_RSN.OFF_THE_BOOK_WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Receive Confirmation
                if (TRX_DTL_RCV_CONF.equals(trxDtlCd)) {
                    return MOVE_OFF_THE_BOOK_WH_TRNSF_ITRNST_STK_O_OM_RCV_CONF;
                }
            }

        // Reason Code = Off-the-Book WH Transfer Stock In(57)
        } else if (TRX_RSN.OFF_THE_BOOK_WAREHOUSE_TRANSFER_STOCK_IN.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Receive Confirmation
                if (TRX_DTL_RCV_CONF.equals(trxDtlCd)) {
                    return MOVE_OFF_THE_BOOK_WH_TRNSF_STK_I_OM_RCV_CONF;
                }
            }

        // Reason Code = Off-the-Book WH Transfer In-Transit Shortage Out(69)
        } else if (TRX_RSN.OFF_THE_BOOK_WAREHOUSE_TRANSFER_IN_TRANSIT_SHORTAGE_OUT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Receive Confirmation
                if (TRX_DTL_RCV_CONF.equals(trxDtlCd)) {
                    return MOVE_OFF_THE_BOOK_WH_TRNSF_ITRNST_SHORT_O_OM_RCV_CONF;
                }
            }

        // Reason Code = Internal WH Transfer Stock Out (44)
        } else if (TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = Ship Confirmation
                if (TRX_DTL_SHIP_CONF.equals(trxDtlCd)) {
                    return MOVE_INTERNAL_WH_TRNSF_STK_O_OTBD_SHIP_CONF;
                }
            }

        // Reason Code = Internal WH Transfer Stock In (45)
        } else if (TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Receive Confirmation
                if (TRX_DTL_RCV_CONF.equals(trxDtlCd)) {
                    return MOVE_INTERNAL_WH_TRNSF_STK_I_INBD_RCV_CONF;
                }
            }

        // Reason Code = Showroom Transfer Stock Out(58)
        } else if (TRX_RSN.SHOWROOM_TRANSFER_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Ship Confirmation
                if (TRX_DTL_SHIP_CONF.equals(trxDtlCd)) {
                    return MOVE_SHRM_TRNSF_STK_O_OM_SHIP_CONF;
                }
            }

        // Reason Code = Showroom Transfer In-Transit Stock In(59)
        } else if (TRX_RSN.SHOWROOM_TRANSFER_IN_TRANSIT_STOCK_IN.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Ship Confirmation
                if (TRX_DTL_SHIP_CONF.equals(trxDtlCd)) {
                    return MOVE_SHRM_TRNSF_ITRNST_STK_I_OM_SHIP_CONF;
                }
            }

        // Reason Code = Showroom Transfer In-Transit Stock Out(60)
        } else if (TRX_RSN.SHOWROOM_TRANSFER_IN_TRANSIT_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Receive Confirmation
                if (TRX_DTL_RCV_CONF.equals(trxDtlCd)) {
                    return MOVE_SHRM_TRNSF_ITRNST_STK_O_OM_RCV_CONF;
                }
            }

        // Reason Code = Showroom Transfer Stock In(61)
        } else if (TRX_RSN.SHOWROOM_TRANSFER_STOCK_IN.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = Receive Confirmation
                if (TRX_DTL_RCV_CONF.equals(trxDtlCd)) {
                    return MOVE_SHRM_TRNSF_STK_I_OM_RCV_CONF;
                }
            }

        // Reason Code = Direct Sale Shipment Stock Out(11)
        } else if (TRX_RSN.DIRECT_SALE_SHIPMENT_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = ROSS
                if (TRX_DTL_ROSS.equals(trxDtlCd)) {
                    return MOVE_DRCT_SLS_SHIP_STK_O_OTBD_ROSS;
                }
            }

        // Reason Code = Direct Sale Shipment Stock In(12)
        } else if (TRX_RSN.DIRECT_SALE_SHIPMENT_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = ROSS
                if (TRX_DTL_ROSS.equals(trxDtlCd)) {
                    return MOVE_DRCT_SLS_SHIP_STK_I_OTBD_ROSS;
                }
            }

        // Reason Code = Direct Sale Stock Out Intangible with Cost
        } else if (TRX_RSN.DIRECT_SALE_STOCK_OUT_INTANGIBLE_WITH_COST.equals(trxRsnCd)) {

            // System Type = ROSS
            if (SYS_TP_ROSS.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return MOVE_DRCT_SLS_STK_O_INTG_WITH_COST_ROSS;
                }
            }

        // Reason Code = Direct Sale Stock In Intangible with Cost
        } else if (TRX_RSN.DIRECT_SALE_STOCK_IN_INTANGIBLE_WITH_COST.equals(trxRsnCd)) {

            // System Type = ROSS
            if (SYS_TP_ROSS.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return MOVE_DRCT_SLS_STK_I_INTG_WITH_COST_ROSS;
                }
            }

        // Reason Code = Loan Shipment Stock Out
        } else if (TRX_RSN.LOAN_SHIPMENT_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = Trial Ship
                if (TRX_DTL_TRIAL_SHIP.equals(trxDtlCd)) {
                    return MOVE_LOAN_SHIP_STK_O_OTBD_TRL_SHIP;
                }
            }

        // Reason Code = Loan Shipment Stock In
        } else if (TRX_RSN.LOAN_SHIPMENT_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = Trial Ship
                if (TRX_DTL_TRIAL_SHIP.equals(trxDtlCd)) {
                    return MOVE_LOAN_SHIP_STK_I_OTBD_TRL_SHIP;
                }
            }

        // QC#63527 2024/03/14 Start
        // Reason Code = Off The Book Loan Shipment Stock Out
        } else if (TRX_RSN.OFF_THE_BOOK_LOAN_SHIPMENT_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = Trial Ship
                if (TRX_DTL_TRIAL_SHIP.equals(trxDtlCd)) {
                    return MOVE_OFF_THE_BOOK_LOAN_SHIP_STK_O_OTBD_TRL_SHIP;
                }
            }

        // Reason Code = Off The Book Loan Shipment Stock In
        } else if (TRX_RSN.OFF_THE_BOOK_LOAN_SHIPMENT_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = Trial Ship
                if (TRX_DTL_TRIAL_SHIP.equals(trxDtlCd)) {
                    return MOVE_OFF_THE_BOOK_LOAN_SHIP_STK_I_OTBD_TRL_SHIP;
                }
            }
        // QC#63527 2024/03/14 End

        // Reason Code = Drop Shipment Loan Stock Out
        } else if (TRX_RSN.DROP_SHIPMENT_LOAN_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Trial Ship
                if (TRX_DTL_TRIAL_SHIP.equals(trxDtlCd)) {
                    return MOVE_DROP_SHIP_LOAN_STK_O_INBD_TRL_SHIP;
                }
            }

        // Reason Code = Drop Shipment Loan Stock In
        } else if (TRX_RSN.DROP_SHIPMENT_LOAN_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Trial Ship
                if (TRX_DTL_TRIAL_SHIP.equals(trxDtlCd)) {
                    return MOVE_DROP_SHIP_LOAN_STK_I_INBD_TRL_SHIP;
                }
                }

        // Reason Code = Direct Sale Shipment Stock Out Asset
        } else if (TRX_RSN.DIRECT_SALE_SHIPMENT_STOCK_OUT_ASSET.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return MOVE_DRCT_SLS_SHIP_STK_O_AST_OTBD;
                }
            }

        // Reason Code = Direct Sale Shipment Stock In Asset
        } else if (TRX_RSN.DIRECT_SALE_SHIPMENT_STOCK_IN_ASSET.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return MOVE_DRCT_SLS_SHIP_STK_I_AST_OTBD;
                }
            }

        // Reason Code = Loan to Inventory Stock Out
        } else if (TRX_RSN.LOAN_TO_INVENTORY_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Trial to Inventory
                if (TRX_DTL_TRIAL_TO_INVTY.equals(trxDtlCd)) {
                    return MOVE_LOAN_INVTY_STK_O_INBD_TRL_INVTY;
                }
            }

        // Reason Code = Loan to Inventory Stock In
        } else if (TRX_RSN.LOAN_TO_INVENTORY_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Trial to Inventory
                if (TRX_DTL_TRIAL_TO_INVTY.equals(trxDtlCd)) {
                    return MOVE_LOAN_INVTY_STK_I_INBD_TRL_INVTY;
                }
            }

        // QC#63527 2024/03/14 Start
        // Reason Code = Off The Book Loan to Inventory Stock Out
        } else if (TRX_RSN.OFF_THE_BOOK_LOAN_TO_INVENTORY_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Trial to Inventory
                if (TRX_DTL_TRIAL_TO_INVTY.equals(trxDtlCd)) {
                    return MOVE_OFF_THE_BOOK_LOAN_INVTY_STK_O_INBD_TRL_INVTY;
                }
            }

        // Reason Code = Off The Book Loan to Inventory Stock In
        } else if (TRX_RSN.OFF_THE_BOOK_LOAN_TO_INVENTORY_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = Trial to Inventory
                if (TRX_DTL_TRIAL_TO_INVTY.equals(trxDtlCd)) {
                    return MOVE_OFF_THE_BOOK_LOAN_INVTY_STK_I_INBD_TRL_INVTY;
                }
            }
        // QC#63527 2024/03/14 End

        // Reason Code = Status Change Stock Out
        } else if (TRX_RSN.STOCK_STATUS_CHANGE_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return MOVE_STS_CHNG_STK_O_INBD;
                }
            }

        // Reason Code = Status Change Stock In
        } else if (TRX_RSN.STOCK_STATUS_CHANGE_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return MOVE_STS_CHNG_STK_I_INBD;
                }
            }

        // Reason Code = Location Status Change Stock Out
        } else if (TRX_RSN.LOCATION_STATUS_CHANGE_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = Ship Confirmation
                if (TRX_DTL_SHIP_CONF.equals(trxDtlCd)) {
                    return MOVE_LOC_STS_CHNG_STK_O_OTBD_SHIP_CONF;
                }
            }

        // Reason Code = Location Status Change Stock In
        } else if (TRX_RSN.LOCATION_STATUS_CHANGE_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = Ship Confirmation
                if (TRX_DTL_SHIP_CONF.equals(trxDtlCd)) {
                    return MOVE_LOC_STS_CHNG_STK_I_OTBD_SHIP_CONF;
                }
            }

        // Reason Code = Location Status Change Cancel Stock Out (66)
        } else if (TRX_RSN.LOCATION_STATUS_CHANGE_CANCEL_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Procurement
            if (SYS_TP_PROCR.equals(sysTp)) {

                // Transaction Detail = null
                if (TRX_DTL_RCV_CONF.equals(trxDtlCd)) {
                    return MOVE_LOC_STS_CHNG_CANC_STK_O_PROCR;
                }
            }

        // Reason Code = Location Status Change Cancel Stock In (67)
        } else if (TRX_RSN.LOCATION_STATUS_CHANGE_CANCEL_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Procurement
            if (SYS_TP_PROCR.equals(sysTp)) {

                // Transaction Detail = null
                if (TRX_DTL_RCV_CONF.equals(trxDtlCd)) {
                    return MOVE_LOC_STS_CHNG_CANC_STK_I_PROCR;
                }
            }

        // Reason Code = Refurb Vendor Transfer Stock Out
        } else if (TRX_RSN.REFURB_VENDOR_TRANSFER_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = Ship Confirmation
                if (TRX_DTL_SHIP_CONF.equals(trxDtlCd)) {
                    return MOVE_RFRB_VND_TRSF_STK_O_OTBD_SHIP_CONF;
                }
            }

        // Reason Code = Refurb Vendor Transfer Stock In
        } else if (TRX_RSN.REFURB_VENDOR_TRANSFER_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = Ship Confirmation
                if (TRX_DTL_SHIP_CONF.equals(trxDtlCd)) {
                    return MOVE_RFRB_VND_TRSF_STK_I_OTBD_SHIP_CONF;
                }
            }

        // Reason Code = Off-the-Book Stock-Out(46)
        } else if (TRX_RSN.OFF_THE_BOOK_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return MOVE_OFF_THE_BOOK_STK_O_OM;
                }
            }

        // Reason Code = Off-the-Book Stock-In(47)
        } else if (TRX_RSN.OFF_THE_BOOK_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return MOVE_OFF_THE_BOOK_STK_I_INBD;
                }
            }

        // Reason Code = Off-the-Book Shipment Stock-Out(48)
        } else if (TRX_RSN.OFF_THE_BOOK_SHIPMENT_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return MOVE_OFF_THE_BOOK_SHIP_STK_O_OTBD;
                }
            }

        // Reason Code = Off-the-Book Shipment Stock-In(49)
        } else if (TRX_RSN.OFF_THE_BOOK_SHIPMENT_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return MOVE_OFF_THE_BOOK_SHIP_STK_I_OTBD;
                }
            }
        // Reason Code = Configuration Change Stock Out
        } else if (TRX_RSN.CONFIG_CHANGE_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return MOVE_CONFIG_CHNG_STK_O_INBD;
                }
            }

        // Reason Code = Configuration Change Stock In
        } else if (TRX_RSN.CONFIG_CHANGE_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return MOVE_CONFIG_CHNG_STK_I_INBD;
                }
            }

        // Reason Code = Reman Parts Transfer Stock-Out(70)
        } else if (TRX_RSN.REMAN_PARTS_TRANSFER_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return MOVE_REMAN_PARTS_TRSF_STK_OUT;
                }
            }

        // Reason Code = Reman Parts Transfer Stock-In(71)
        } else if (TRX_RSN.REMAN_PARTS_TRANSFER_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return MOVE_REMAN_PARTS_TRSF_STK_IN;
                }
            }

        // Reason Code = Reman Parts Transfer Back Stock-Out(72)
        } else if (TRX_RSN.REMAN_PARTS_TRANSFER_BACK_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return MOVE_REMAN_PARTS_TRSF_BCK_STK_OUT;
                }
            }

        // Reason Code = Reman Parts Transfer Back Stock-In(73)
        } else if (TRX_RSN.REMAN_PARTS_TRANSFER_BACK_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return MOVE_REMAN_PARTS_TRSF_BCK_STK_IN;
                }
            }
        }

        return UNEXPECTED;
    }

    /**
     * <pre>
     * Transaction code is "Parts Usage" to decision the scenario.
     * Reason code, System source code, Transaction detail code
     * to decision the scenario.
     * </pre>
     * 
     * @param trxRsnCd Transaction Reason Code
     * @param sysTp System Type
     * @param trxDtlCd Transaction Detail Code
     * @return Scenario number
     */
    private int decnScenarioForPartsUsg(String trxRsnCd, String sysTp, String trxDtlCd) {

        // Reason Code = Parts Usage
        if (TRX_RSN.PARTS_USAGE.equals(trxRsnCd)) {

            // System Type = Outbound
            if (SYS_TP_OTBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return PARTS_USG_PARTS_USG_OTBD;
                }
            }

        // Reason Code = Parts Usage Return
        } else if (TRX_RSN.PARTS_USAGE_RETURN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return PARTS_USG_PARTS_USG_RTRN_INBD;
                }
            }
        }

        return UNEXPECTED;
    }

    /**
     * <pre>
     * Transaction code is "Remanufacturing" to decision the scenario.
     * Reason code, System source code, Transaction detail code
     * to decision the scenario.
     * </pre>
     * 
     * @param trxRsnCd Transaction Reason Code
     * @param sysTp System Type
     * @param trxDtlCd Transaction Detail Code
     * @return Scenario number
     */
    private int decnScenarioForReman(String trxRsnCd, String sysTp, String trxDtlCd) {

        // Reason Code = Parts Usage for Reman  (01)
        if (TRX_RSN.PARTS_USAGE_FOR_REMAN.equals(trxRsnCd)) {
            // Transaction Detail = null
            if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                return REMAN_PRT_USG_FOR_REMAN_OTBD;
            }

        // Reason Code = Reman Item Change Stock Out  (02)
        } else if (TRX_RSN.REMAN_ITEM_CHANGE_STOCK_OUT.equals(trxRsnCd)) {
            // Transaction Detail = null
            if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                return REMAN_REMAN_ITM_CHNG_STK_O_INBD;
            }

        // Reason Code = Reman Item Change Stock In  (03)
        } else if (TRX_RSN.REMAN_ITEM_CHANGE_STOCK_IN.equals(trxRsnCd)) {
            // Transaction Detail = null
            if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                return REMAN_REMAN_ITM_CHNG_STK_I_INBD;
            }

        // Reason Code = Parts Usage Return for Reman  (04)
        } else if (TRX_RSN.PARTS_USAGE_RETURN_FOR_REMAN.equals(trxRsnCd)) {
            // Transaction Detail = null
            if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                return REMAN_PRT_USG_RTRN_FOR_REMAN_INBD;
            }

         // Reason Code = Reman Item Change Stock Out for Accessary(05)
        } else if (TRX_RSN.REMAN_ITEM_CHANGE_STOCK_OUT_ACSRY.equals(trxRsnCd)) {
            // Transaction Detail = null
            if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                return REMAN_REMAN_ITM_CHNG_STK_O_INBD_ACSRY;
            }

            // Reason Code = Reman Item Change Stock In for Accessary(06)
        } else if (TRX_RSN.REMAN_ITEM_CHANGE_STOCK_IN_ACSRY.equals(trxRsnCd)) {
            // Transaction Detail = null
            if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                return REMAN_REMAN_ITM_CHNG_STK_I_INBD_ACSRY;
            }
        }

        return UNEXPECTED;
    }

    /**
     * <pre>
     * Transaction code is "Inventory Valuation" to decision the scenario.
     * Reason code, System source code, Transaction detail code
     * to decision the scenario.
     * </pre>
     * 
     * @param trxRsnCd Transaction Reason Code
     * @param sysTp System Type
     * @param trxDtlCd Transaction Detail Code
     * @return Scenario number
     */
    private int decnScenarioForInvtyValue(String trxRsnCd, String sysTp, String trxDtlCd) {

        // Reason Code = SWH Transfer Stock Out
        if (TRX_RSN.SWH_TRANSFER_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return INVTY_VALUE_SWH_TRNSF_STK_O_INBD;
                }
            }

        // Reason Code = SWH Transfer Stock In
        } else if (TRX_RSN.SWH_TRANSFER_STOCK_IN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return INVTY_VALUE_SWH_TRNSF_STK_I_INBD;
                }
            }
        }

        return UNEXPECTED;
    }

    /**
     * <pre>
     * Transaction code is "EMSD Shipment" to decision the scenario.
     * Reason code, System source code, Transaction detail code
     * to decision the scenario.
     * </pre>
     * 
     * @param trxRsnCd Transaction Reason Code
     * @param sysTp System Type
     * @param trxDtlCd Transaction Detail Code
     * @return Scenario number
     */
    private int decnScenarioForEmsdShip(String trxRsnCd, String sysTp, String trxDtlCd) {

        // Reason Code = EMSD Shipment Stock Out
        if (TRX_RSN.EMSD_SHIPMENT_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return EMSD_EMSD_SHIP_STK_O_OM;
                }
            }

        // Reason Code = EMSD Drop Shipment Stock Out
        } else if (TRX_RSN.EMSD_DROP_SHIPMENT_STOCK_OUT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return EMSD_DROP_SHIP_EMSD_STK_O_OM;
                }
            }

        // Reason Code = EMSD Stock Out Intangible with Cost
        } else if (TRX_RSN.EMSD_STOCK_OUT_INTANGIBLE_WITH_COST.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return EMSD_EMSD_SHIP_STK_O_INTG_WITH_COST_OM;
                }
            }

        // Reason Code = EMSD to Asset Return
        } else if (TRX_RSN.EMSD_TO_ASSET_RETURN.equals(trxRsnCd)) {

            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return EMSD_EMSD_TO_AST_RTRN_INBD;
                }
            }

        // Reason Code = EMSD Shipment Stock Out Expense
        } else if (TRX_RSN.EMSD_SHIPMENT_STOCK_OUT_EXPENSE.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return EMSD_EMSD_SHIP_STK_O_EXP_OM;

                // Transaction Detail = Intangible with Cost
                } else if (TRX_DTL_INTG_WITH_COST.equals(trxDtlCd)) {
                    return EMSD_EMSD_SHIP_STK_O_EXP_OM_INTG_WITH_COST;

                // Transaction Detail = null
                } else if (TRX_DTL_SELL_THEN_BUY.equals(trxDtlCd)) {
                    return EMSD_EMSD_SHIP_STK_O_EXP_OM_SELL_THEN_BUY;

                }
            }

        // Reason Code = EMSD Shipment Stock Out Asset
        } else if (TRX_RSN.EMSD_SHIPMENT_STOCK_OUT_ASSET.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return EMSD_EMSD_SHIP_STK_O_AST_OM;
                }
            }
        //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) START
        } else if (TRX_RSN.RETURN_EMSD_INITIAL_SUPPLY.equals(trxRsnCd)) {
            // System Type = Inbound
            if (SYS_TP_INBD.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return EMSD_RTRN_INIT_SUPPLY;
                }
            }
        // 2017/10/20 S21_NA#16347 Add Start
        // Reason Code = EMSD Shipment Stock-Out
        } else if (TRX_RSN.EMSD_SHIPMENT_STOCK_OUT_CREDIT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return EMSD_SHIP_STK_OUT;
                }
            }
        // Reason Code = EMSD Shipment Stock-Out Expense
        } else if (TRX_RSN.EMSD_SHIPMENT_STOCK_OUT_EXPENSE_CREDIT.equals(trxRsnCd)) {

            // System Type = OM
            if (SYS_TP_OM.equals(sysTp)) {

                // Transaction Detail = null
                if (!ZYPCommonFunc.hasValue(trxDtlCd)) {
                    return EMSD_SHIP_STK_OUT_EXP;
                }
            }
        // 2017/10/20 S21_NA#16347 Add End
        }
    //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) START

        return UNEXPECTED;
    }

    /**
     * <pre>
     * Check common parameters.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @return Result (true:Normal, false:Error)
     */
    private boolean checkParameterCom(S21ApiMessageMap msgMap) {

        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Request Type an unauthorized.
        String rqstTpCd = inpPrmPMsg.xxRqstTpCd.getValue();
        if (!ZYPCommonFunc.hasValue(rqstTpCd)
                || !RQST_STOCK_IN.equals(rqstTpCd)
                && !RQST_STOCK_OUT.equals(rqstTpCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0001E);
            return false;
        }

        // Global Company Code has not been entered.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.glblCmpyCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0003E);
            return false;
        }

        // Merchandise Code has not been entered.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.mdseCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0005E);
            return false;
        }

        // Location Code has not been entered.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.invtyLocCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0006E);
            return false;
        }

        // Location Status Code has not been entered.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.locStsCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0007E);
            return false;
        }

        // Stock Status Code has not been entered.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.stkStsCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0008E);
            return false;
        }

        // Transaction Code has not been entered.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.trxCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0018E);
            return false;
        }

        // Transaction Reason Code has not been entered.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.trxRsnCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0019E);
            return false;
        }

        // System Source Code has not been entered.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.sysSrcCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0056E);
            return false;
        }

        // Request Qty has not been entered.
        BigDecimal rqstQty = inpPrmPMsg.xxRqstQty.getValue();
        if (!ZYPCommonFunc.hasValue(rqstQty)) {
            addMsgId(msgMap, MSG_ID_NLZM0009E);
            return false;
        }

        // Request Qty is not a positive number.
        if (BigDecimal.ZERO.compareTo(rqstQty) >= 0) {
            addMsgId(msgMap, MSG_ID_NLZM0011E);
            return false;
        }

        // Transaction Date has not been entered.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.invtyTrxDt.getValue())) {
            addMsgId(msgMap, MSG_ID_NLZM0025E);
            return false;
        }

        // Serial number is larger than the request number.
        if (inpPrmPMsg.serNumList.getValidCount() > 0) {
            BigDecimal rqstQtyAbs = rqstQty.abs();
            if (rqstQtyAbs.compareTo(BigDecimal.valueOf(inpPrmPMsg.serNumList.getValidCount())) < 0) {
                addMsgId(msgMap, MSG_ID_NLZM2508E);
                return false;
            }
        }
        return true;
    }

    /**
     * <pre>
     * Find the Merchandise Master.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean findMdse(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Find the Merchandise Master.
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        MDSETMsg mdseTMsg = new MDSETMsg();
        mdseTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
        mdseTMsg.mdseCd.setValue(inpPrmPMsg.mdseCd.getValue());
        mdseTMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);

        // If an error occurs, add a message to the Message Map.
        if (mdseTMsg == null) {
            addMsgId(msgMap, MSG_ID_NLZM0021E);
            return false;
        }
        if (!RTNCD_NORMAL_END.equals(mdseTMsg.getReturnCode())) {
            addMsgId(msgMap, MSG_ID_NLZM0044E, "Merchandise Master " + mdseTMsg.getReturnCode());
            return false;
        }

        // Sets Inventory Transaction TMsg.
        invtyTrxTMsg.zerothProdCtrlCd.setValue(mdseTMsg.zerothProdCtrlCd.getValue());
        invtyTrxTMsg.firstProdCtrlCd.setValue(mdseTMsg.firstProdCtrlCd.getValue());
        invtyTrxTMsg.scdProdCtrlCd.setValue(mdseTMsg.scdProdCtrlCd.getValue());
        invtyTrxTMsg.thirdProdCtrlCd.setValue(mdseTMsg.thirdProdCtrlCd.getValue());
        invtyTrxTMsg.frthProdCtrlCd.setValue(mdseTMsg.frthProdCtrlCd.getValue());
        invtyTrxTMsg.fifthProdCtrlCd.setValue(mdseTMsg.fifthProdCtrlCd.getValue());
        invtyTrxTMsg.sixthProdCtrlCd.setValue(mdseTMsg.sixthProdCtrlCd.getValue());
        invtyTrxTMsg.svnthProdCtrlCd.setValue(mdseTMsg.svnthProdCtrlCd.getValue());
        invtyTrxTMsg.coaProdCd.setValue(mdseTMsg.coaProdCd.getValue());
        invtyTrxTMsg.mdseNm.setValue(mdseTMsg.mdseNm.getValue());
        invtyTrxTMsg.unitCostAmt.setValue(mdseTMsg.thisMthTotStdCostAmt.getValue());
        invtyTrxTMsg.invtyCtrlFlg.setValue(mdseTMsg.invtyCtrlFlg.getValue());
        invtyTrxTMsg.invtyValFlg.setValue(mdseTMsg.invtyValFlg.getValue());
        BigDecimal rqstQty = inpPrmPMsg.xxRqstQty.getValue();
        BigDecimal thisMthTotStdCostAmt = mdseTMsg.thisMthTotStdCostAmt.getValue();
        invtyTrxTMsg.shipCostAmt.setValue(rqstQty.multiply(thisMthTotStdCostAmt));
        return true;
    }

    /**
     * <pre>
     * Find the Inventory Location Type and Name from Inventory Locatin View.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean findInvtyLocTpAndNm(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Find the Inventory Location View.
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        String invtyLocCd = inpPrmPMsg.invtyLocCd.getValue();

        /* 12/21/2015 CSAI Y.Imazu Mod QC#793 START */
        // Loan Warehouse Check
        String loanWhCd = ZYPCodeDataUtil.getVarCharConstValue(LOAN_DUMMY_WH_CD, inpPrmPMsg.glblCmpyCd.getValue());
        // QC#63527 2024/03/14 Start
        List<String> loanWhCdList = Arrays.asList(loanWhCd.split(","));

        if (loanWhCdList.contains(invtyLocCd)) {
        // QC#63527 2024/03/14 End

           invtyLocCd = getLoanWh(inpPrmPMsg);

            if (invtyLocCd == null) {

                return false;
            }
        }

        ZYPEZDItemValueSetter.setValue(inpPrmPMsg.invtyLocCd, invtyLocCd);
        /* 12/21/2015 CSAI Y.Imazu Mod QC#793 END */

        INVTY_LOC_VTMsg invtyLocVTMsg = findInvtyLocV(msgMap, invtyLocCd);
        if (invtyLocVTMsg == null) {
            return false;
        }

        // Sets Inventory Transaction TMsg.
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.locTpCd, invtyLocVTMsg.invtyLocTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyLocNm, invtyLocVTMsg.invtyLocNm.getValue());
        return true;
    }

    /* 12/21/2015 CSAI Y.Imazu Add QC#793 START */
    /**
     * <pre>
     * Get Loan Warehouse
     * </pre>
     * 
     * @param inpPrmPMsg NLZC002001PMsg
     * @return String
     */
    private String getLoanWh(NLZC002001PMsg inpPrmPMsg) {

        // Get Outbound Inboud Code & Sub WH Code from DS_COND_CONST
        Map<String, Object> dsCondConstParam = new HashMap<String, Object>();
        dsCondConstParam.put("glblCmpyCd", inpPrmPMsg.glblCmpyCd.getValue());
        dsCondConstParam.put("condConstGrpId", LOAN_SWH_COND_CONST_GRP_ID);
        dsCondConstParam.put("trxCd", inpPrmPMsg.trxCd.getValue());
        dsCondConstParam.put("trxRsnCd", inpPrmPMsg.trxRsnCd.getValue());

        Map<String, Object> dsCondConstMap = (Map<String, Object>) ssmBatchClient.queryObject("getDsCondConst", dsCondConstParam);

        if (dsCondConstMap != null) {

            if ((String) dsCondConstMap.get("SWH_CD") != null) {

                RTL_SWHTMsg rtlSwhTMsg = new RTL_SWHTMsg();
                ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.glblCmpyCd, inpPrmPMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlWhCd, inpPrmPMsg.invtyLocCd.getValue());
                ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlSwhCd, (String) dsCondConstMap.get("SWH_CD"));
                rtlSwhTMsg = (RTL_SWHTMsg) S21ApiTBLAccessor.findByKeyForUpdate(rtlSwhTMsg);

                if (rtlSwhTMsg == null) {

                    return null;
                }

                return rtlSwhTMsg.invtyLocCd.getValue();

            } else if ((String) dsCondConstMap.get("INBD_OTBD_CD") != null) {

                if (INBD_OTBD.INBOUND.equals((String) dsCondConstMap.get("INBD_OTBD_CD"))) {

                    Map<String, Object> whParam = new HashMap<String, Object>();
                    whParam.put("glblCmpyCd", inpPrmPMsg.glblCmpyCd.getValue());
                    whParam.put("invtyLocCd", inpPrmPMsg.invtyLocCd.getValue());
                    whParam.put("shipFromLocCd", inpPrmPMsg.shipFromLocCustCd.getValue());

                    return (String) ssmBatchClient.queryObject("getLoanWhFromShipWh", whParam);

                } else if (INBD_OTBD.OUTBOUND.equals((String) dsCondConstMap.get("INBD_OTBD_CD"))) {

                    Map<String, Object> whParam = new HashMap<String, Object>();
                    whParam.put("glblCmpyCd", inpPrmPMsg.glblCmpyCd.getValue());
                    whParam.put("invtyLocCd", inpPrmPMsg.invtyLocCd.getValue());
                    whParam.put("cpoOrdNum", inpPrmPMsg.origCpoOrdNum.getValue());
                    whParam.put("cpoDtlLineNum", inpPrmPMsg.origCpoDtlLineNum.getValue());
                    whParam.put("cpoDtlLineSubNum", inpPrmPMsg.origCpoDtlLineSubNum.getValue());

                    return (String) ssmBatchClient.queryObject("getLoanWhFromOrgOrd", whParam);
                }
            }
        }

        return null;
    }
    /* 12/21/2015 CSAI Y.Imazu Add QC#793 END */

    /* 06/03/2016 CSAI Y.Imazu Add QC#9498 START */
    /**
     * Set PO Source Warehouse
     * @param msgMap S21ApiMessageMap
     * @param invtyTrxTMsg INVTY_TRXTMsg
     */
    private void setPoSrcWh(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.poOrdNum) || !ZYPCommonFunc.hasValue(inpPrmPMsg.poOrdDtlLineNum)) {

            return;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", inpPrmPMsg.glblCmpyCd.getValue());
        param.put("poOrdNum", inpPrmPMsg.poOrdNum.getValue());
        param.put("poOrdDtlLineNum", inpPrmPMsg.poOrdDtlLineNum.getValue());

        Map<String, String> poSrcWhMap = (Map<String, String>) ssmBatchClient.queryObject("getPoSrcWh", param);

        if (poSrcWhMap != null) {

            if (ZYPCommonFunc.hasValue(poSrcWhMap.get("SHIP_FROM_LOC_CUST_CD"))) {

                ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipFromLocCustCd, poSrcWhMap.get("SHIP_FROM_LOC_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipFromLocCustNm, poSrcWhMap.get("SHIP_FROM_LOC_CUST_NM"));
            }
        }
    }

    /**
     * Set PO Vendor
     * @param msgMap S21ApiMessageMap
     * @param invtyTrxTMsg INVTY_TRXTMsg
     */
    private void setPoVnd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.poOrdNum)) {

            return;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", inpPrmPMsg.glblCmpyCd.getValue());
        param.put("poOrdNum", inpPrmPMsg.poOrdNum.getValue());

        Map<String, String> poVndMap = (Map<String, String>) ssmBatchClient.queryObject("getPoVnd", param);

        if (poVndMap != null) {

            ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.vndCd, poVndMap.get("VND_CD"));
            ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.vndNm, poVndMap.get("VND_NM"));
        }
    }

    /**
     * Set SO Ship From Location
     * @param msgMap S21ApiMessageMap
     * @param invtyTrxTMsg INVTY_TRXTMsg
     */
    private void setSoShipFromLoc(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.soNum) || !ZYPCommonFunc.hasValue(inpPrmPMsg.soSlpNum)) {

            return;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", inpPrmPMsg.glblCmpyCd.getValue());
        param.put("soNum", inpPrmPMsg.soNum.getValue());
        param.put("soSlpNum", inpPrmPMsg.soSlpNum.getValue());

        Map<String, String> soShipFromLocMap = (Map<String, String>) ssmBatchClient.queryObject("getSoShipFromLoc", param);

        if (soShipFromLocMap != null) {

            ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipFromLocCustCd, soShipFromLocMap.get("SHIP_FROM_LOC_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipFromLocCustNm, soShipFromLocMap.get("SHIP_FROM_LOC_CUST_NM"));
        }
    }
    /* 06/03/2016 CSAI Y.Imazu Add QC#9498 END */

    /**
     * <pre>
     * Find the Inventory Location View.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyLocCd Inventory Locaiton Code
     * @return Result (not null:Normal, null:Error)
     */
    private INVTY_LOC_VTMsg findInvtyLocV(S21ApiMessageMap msgMap, String invtyLocCd) {

        // Find the Inventory Location View.
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        INVTY_LOC_VTMsg invtyLocVTMsg = new INVTY_LOC_VTMsg();
        invtyLocVTMsg.setSQLID("001");
        invtyLocVTMsg.setConditionValue("glblCmpyCd01", inpPrmPMsg.glblCmpyCd.getValue());
        invtyLocVTMsg.setConditionValue("invtyLocCd01", invtyLocCd);
        invtyLocVTMsg.setMaxCount(1);
        INVTY_LOC_VTMsgArray invtyLocVTMsgAry = (INVTY_LOC_VTMsgArray) S21ApiTBLAccessor.findByCondition(invtyLocVTMsg);

        // If an error occurs, add a message to the Message Map.
        if (invtyLocVTMsgAry.getValidCount() == 0) {
            addMsgId(msgMap, MSG_ID_NLZM0022E);
            return null;
        }
        invtyLocVTMsg = invtyLocVTMsgAry.no(0);
        if (!RTNCD_NORMAL_END.equals(invtyLocVTMsg.getReturnCode())) {
            addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Location View " + invtyLocVTMsg.getReturnCode());
            return null;
        }

        // Return the Inventory Location TMsg.
        return invtyLocVTMsg;
    }

    /**
     * <pre>
     * Find the Inventory Master.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTMsg Inventory Master TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean findInvty(S21ApiMessageMap msgMap, INVTYTMsg invtyTMsg) {

        // Get the Request Type Code.
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        String rqstTpCd = inpPrmPMsg.xxRqstTpCd.getValue();

        // Find the Inventory Master.
        String glblCmpyCd = inpPrmPMsg.glblCmpyCd.getValue();
        String locStsCd = inpPrmPMsg.locStsCd.getValue();
        String stkStsCd = inpPrmPMsg.stkStsCd.getValue();
        INVTYTMsg rInvtyTMsg = new INVTYTMsg();
        rInvtyTMsg.glblCmpyCd.setValue(glblCmpyCd);
        rInvtyTMsg.mdseCd.setValue(inpPrmPMsg.mdseCd.getValue());
        rInvtyTMsg.invtyLocCd.setValue(inpPrmPMsg.invtyLocCd.getValue());
        rInvtyTMsg.locStsCd.setValue(locStsCd);
        rInvtyTMsg.stkStsCd.setValue(stkStsCd);
        rInvtyTMsg = (INVTYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(rInvtyTMsg);

        // Inventory Master not found.
        if (rInvtyTMsg == null) {

            // Stock-Out
            if (RQST_STOCK_OUT.equals(rqstTpCd)) {
                addMsgId(msgMap, MSG_ID_NLZM0014E);
                return false;

            // Check the Code Master.
            } else {
                if (!checkCdTbl(msgMap, LOC_STS.class, glblCmpyCd, locStsCd)) {
                    return false;
                }
                if (!checkCdTbl(msgMap, STK_STS.class, glblCmpyCd, stkStsCd)) {
                    return false;
                }
                return true;
            }
        } else if (!RTNCD_NORMAL_END.equals(rInvtyTMsg.getReturnCode())) {
            addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Master " + rInvtyTMsg.getReturnCode());
            return false;
        }

        // copy
        EZDMsg.copy(rInvtyTMsg, null, invtyTMsg, null);

        BigDecimal rqstQty = inpPrmPMsg.xxRqstQty.getValue();
        BigDecimal invtyQty = invtyTMsg.invtyQty.getValue();
        BigDecimal invtyAvalQty = invtyTMsg.invtyAvalQty.getValue();

        // Stock-Out
        if (RQST_STOCK_OUT.equals(rqstTpCd)) {

            // Inventory Qty < 0
            if (BigDecimal.ZERO.compareTo(invtyQty) > 0) {

                // Inventory Qty - Request Qty < -9,999,999,999
                if (MIN_QTY.compareTo(invtyQty.subtract(rqstQty)) > 0) {
                    addMsgId(msgMap, MSG_ID_NLZM0039E);
                    return false;
                }
            }

            // Request Qty > Inventory Qty
            if (rqstQty.compareTo(invtyQty) > 0) {
                addMsgId(msgMap, MSG_ID_NLZM0040W);
            }

            // is Allocation
            if (ZYPConstant.FLG_ON_Y.equals(execAllocFlg)) {

                // QC#54864
                int scenarioNo = decnScenario(msgMap);
                if (scenarioNo == INVTY_VALUE_SWH_TRNSF_STK_O_INBD && ZYPCommonFunc.hasValue(inpPrmPMsg.origCpoOrdNum)) {
                    // QC#57462 2020/07/31 Add
                    execAllocFlg = ZYPConstant.FLG_OFF_N;
                    return true;
                }

                BigDecimal invtyAllocQty = invtyTMsg.invtyAllocQty.getValue();

                // Inventory Allocation Qty < 0
                if (BigDecimal.ZERO.compareTo(invtyAllocQty) > 0) {

                    // Inventory Allocation Qty - Request Qty <
                    // -9,999,999,999
                    if (MIN_QTY.compareTo(invtyAllocQty.subtract(rqstQty)) > 0) {
                        addMsgId(msgMap, MSG_ID_NLZM0124E);
                        return false;
                    }
                }

                // Request Qty > Inventory Allocation Qty
                if (rqstQty.compareTo(invtyAllocQty) > 0) {
                    addMsgId(msgMap, MSG_ID_NLZM0041W);
                }

            } else {

                // Inventory Available Qty < 0
                if (BigDecimal.ZERO.compareTo(invtyAvalQty) > 0) {

                    // Inventory Available Qty - Request Qty < -9,999,999,999
                    if (MIN_QTY.compareTo(invtyAvalQty.subtract(rqstQty)) > 0) {
                        addMsgId(msgMap, MSG_ID_NLZM0118E);
                        return false;
                    }
                }

                // Request Qty > Inventory Available Qty
                if (rqstQty.compareTo(invtyAvalQty) > 0) {
                    addMsgId(msgMap, MSG_ID_NLZM0042W);
                }
            }

        // Stock-In
        } else {

            // Inventory Qty > 0
            if (BigDecimal.ZERO.compareTo(invtyQty) < 0) {

                // Inventory Qty + Request Qty > 9,999,999,999
                if (MAX_QTY.compareTo(invtyQty.add(rqstQty)) < 0) {
                    addMsgId(msgMap, MSG_ID_NLZM0039E);
                    return false;
                }
            }

            // Inventory Availabele Qty > 0
            if (BigDecimal.ZERO.compareTo(invtyAvalQty) < 0) {

                // Inventory Availabele Qty + Request Qty > 9,999,999,999
                if (MAX_QTY.compareTo(invtyAvalQty.add(rqstQty)) < 0) {
                    addMsgId(msgMap, MSG_ID_NLZM0118E);
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * <pre>
     * Find the code table.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param cdTbl Code Table class
     * @param glblCmpyCd Global Company Code
     * @param cdValue Cole Value
     * @return Result (true:Normal, false:Error)
     */
    private boolean checkCdTbl(S21ApiMessageMap msgMap, Class cdTbl, String glblCmpyCd, String cdValue) {

        EZDTMsg tMsg = ZYPCodeDataUtil.findByCode(cdTbl, glblCmpyCd, cdValue);

        // If an error occurs, add a message to the Message Map.
        if (tMsg == null) {
            if (LOC_STS.class.equals(cdTbl)) {
                addMsgId(msgMap, MSG_ID_NLZM0023E);
            } else if (STK_STS.class.equals(cdTbl)) {
                addMsgId(msgMap, MSG_ID_NLZM0024E);
            } else {
                addMsgId(msgMap, MSG_ID_NLZM0044E, "checkCdTbl");
            }
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Check parameter is Allocation or not.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg INVTY_TRXTMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean initExecAllocFlg(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Find the Transaction Reason Master.
        TRX_RSNTMsg trxRsnTMsg = new TRX_RSNTMsg();
        trxRsnTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
        trxRsnTMsg.trxCd.setValue(inpPrmPMsg.trxCd.getValue());
        trxRsnTMsg.trxRsnCd.setValue(inpPrmPMsg.trxRsnCd.getValue());
        trxRsnTMsg = (TRX_RSNTMsg) S21ApiTBLAccessor.findByKey(trxRsnTMsg);

        // If an error occurs, add a message to the Message Map.
        if (trxRsnTMsg == null) {
            addMsgId(msgMap, MSG_ID_NLZM0020E);
            return false;
        }
        if (!RTNCD_NORMAL_END.equals(trxRsnTMsg.getReturnCode())) {
            addMsgId(msgMap, MSG_ID_NLZM0044E, "Transaction Reason " + trxRsnTMsg.getReturnCode());
            return false;
        }

        // Sets Executed Allocation Flag.
        execAllocFlg = trxRsnTMsg.execAllocFlg.getValue();
        return true;
    }

    /**
     * <pre>
     * Find the Global Company Master.
     * If an error occurs, return null and add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean findGlblCmpy(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Find the Global Company Master.
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        GLBL_CMPYTMsg glblTMsg = new GLBL_CMPYTMsg();
        glblTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
        glblTMsg = (GLBL_CMPYTMsg) S21ApiTBLAccessor.findByKey(glblTMsg);

        // If an error occurs, add a message to the Message Map.
        if (glblTMsg == null) {
            addMsgId(msgMap, MSG_ID_NLZM0095E);
            return false;
        }
        if (!RTNCD_NORMAL_END.equals(glblTMsg.getReturnCode())) {
            addMsgId(msgMap, MSG_ID_NLZM0044E, "Global Company Master " + glblTMsg.getReturnCode());
            return false;
        }

        // Sets Inventory Transaction TMsg.
        invtyTrxTMsg.ccyCd.setValue(glblTMsg.stdCcyCd.getValue());
        return true;
    }

    /**
     * <pre>
     * Find the Vendor Master.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean findVnd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Check the Vendor Code.
        // If an error occurs, add a message to the Message Map.
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        String vndCd = inpPrmPMsg.vndCd.getValue();
        if (!ZYPCommonFunc.hasValue(vndCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Vendor Code");
            return false;
        }

        // Check the Vendor Name.
        // Vendor Name case that has normal end.
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.vndNm)) {
            invtyTrxTMsg.vndCd.setValue(vndCd);
            invtyTrxTMsg.vndNm.setValue(inpPrmPMsg.vndNm.getValue());
            return true;
        }

        // Find the Vendor Master.
        VNDTMsg vndTMsg = new VNDTMsg();
        vndTMsg.setSQLID("004");
        vndTMsg.setConditionValue("glblCmpyCd01", inpPrmPMsg.glblCmpyCd.getValue());
        vndTMsg.setConditionValue("vndCd01", vndCd);
        vndTMsg.setMaxCount(1);
        VNDTMsgArray vndTMsgAry = (VNDTMsgArray) S21ApiTBLAccessor.findByCondition(vndTMsg);

        // If an error occurs, add a message to the Message Map.
        if (vndTMsgAry.getValidCount() == 0) {
            addMsgId(msgMap, MSG_ID_NLZM0092E);
            return false;
        }
        vndTMsg = vndTMsgAry.no(0);
        if (!RTNCD_NORMAL_END.equals(vndTMsg.getReturnCode())) {
            addMsgId(msgMap, MSG_ID_NLZM0044E, "Vendor Master " + vndTMsg.getReturnCode());
            return false;
        }

        // Sets Inventory Transaction TMsg.
        invtyTrxTMsg.vndCd.setValue(vndCd);
        invtyTrxTMsg.vndNm.setValue(vndTMsg.locNm.getValue());
        return true;
    }

    /**
     * <pre>
     * Find the Shipping Order and Shipping Order Customer Detail.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @param setFlg Location Name Flags[Bill to Name, Sell to Name,
     * Ship to Name]<br>
     * (true:set, false:skip)
     * @return Result (true:Normal, false:Error)
     */
    private boolean findShpgOrdCustDtl(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg, boolean[] setFlg) {

        // Check the SO Number.
        // If an error occurs, add a message to the Message Map.
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        String soNo = inpPrmPMsg.soNum.getValue();
        if (!ZYPCommonFunc.hasValue(soNo)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "SO Number");
            return false;
        }

        // Find Shipping Order and Shipping Order Customer Detail.
        NLZC002001ShpgOrdMsg shpgOrdMsg = new NLZC002001ShpgOrdMsg();
        shpgOrdMsg.setGlblCmpyCd(inpPrmPMsg.glblCmpyCd.getValue());
        shpgOrdMsg.setSoNum(soNo);
        List shpgOrdMsgAry = ssmBatchClient.queryObjectList("getShpgOrdCustDtl", shpgOrdMsg);

        // If an error occurs, add a message to the Message Map.
        if (shpgOrdMsgAry.isEmpty()) {
            addMsgId(msgMap, MSG_ID_NLZM0034E);
            return false;
        }

        // Sets Inventory Transaction TMsg.
        String soCustDataTpCd = null;
        String soCustLineLocNm = null;
        boolean flg = false;
        for (int i = 0; i < setFlg.length; i++) {

            if (!setFlg[i]) {
                continue;
            }

            flg = false;
            for (int j = 0; j < shpgOrdMsgAry.size(); j++) {
                shpgOrdMsg = (NLZC002001ShpgOrdMsg) shpgOrdMsgAry.get(j);
                soCustDataTpCd = shpgOrdMsg.getSoCustDataTpCd();
                soCustLineLocNm = shpgOrdMsg.getSoCustLineLocNm01();

                // Bill to Name
                if (i == 0 && SO_CUST_DATA_TP.BILL_TO.equals(soCustDataTpCd)) {
                    ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.billToCustCd, shpgOrdMsg.getBillToCustCd());
                    ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.billToCustNm, soCustLineLocNm);
                    flg = true;
                    break;

                // Sell to Name
                } else if (i == 1 && SO_CUST_DATA_TP.SELL_TO.equals(soCustDataTpCd)) {
                    ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.sellToCustCd, shpgOrdMsg.getSellToCustCd());
                    ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.sellToCustNm, soCustLineLocNm);
                    flg = true;
                    break;

                // Ship to Name
                } else if (i == 2 && SO_CUST_DATA_TP.SHIP_TO.equals(soCustDataTpCd)) {
                    ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipToCustCd, shpgOrdMsg.getShipToCustCd());
                    ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipToCustNm, soCustLineLocNm);
                    flg = true;
                    break;
                }
            }

            if (!flg) {
                if (i == 0) {
                    addMsgId(msgMap, MSG_ID_NLZM0045E);
                } else if (i == 1) {
                    addMsgId(msgMap, MSG_ID_NLZM0037E);
                } else if (i == 2) {
                    addMsgId(msgMap, MSG_ID_NLZM0046E);
                }
                return false;
            }
        }

        return true;
    }

    /**
     * <pre>
     * Find the Shipping Order Detail and Shipping Order Customer Detail.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @param setFlg Location Name Flags[Bill to Name, Sell to Name,
     * Ship to Name]<br>
     * (true:set, false:skip)
     * @return Result (true:Normal, false:Error)
     */
    private boolean findDsShpgOrdCustDtl(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg, boolean[] setFlg) {

        // Check the SO Number.
        // If an error occurs, add a message to the Message Map.
        // QC#24045 MOD START
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        String soNum = inpPrmPMsg.soNum.getValue();
        String soSlpNum = inpPrmPMsg.soSlpNum.getValue();
        if (!ZYPCommonFunc.hasValue(soNum) || !ZYPCommonFunc.hasValue(soSlpNum)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "SO Number, SO Slip Number");
            return false;
        }

        // Find Shipping Order and Shipping Order Customer Detail.
        NLZC002001ShpgOrdMsg shpgOrdMsg = new NLZC002001ShpgOrdMsg();
        shpgOrdMsg.setGlblCmpyCd(inpPrmPMsg.glblCmpyCd.getValue());
        shpgOrdMsg.setSoNum(soNum);
        shpgOrdMsg.setSoSlpNum(soSlpNum);
        List shpgOrdMsgAry = ssmBatchClient.queryObjectList("getDsShpgOrdCustDtl", shpgOrdMsg);
        // QC#24045 MOD END

        // If an error occurs, add a message to the Message Map.
        if (shpgOrdMsgAry.isEmpty()) {
            addMsgId(msgMap, MSG_ID_NLZM0034E);
            return false;
        }

        // Sets Inventory Transaction TMsg.
        String soCustDataTpCd = null;
        String soCustLineLocNm = null;
        boolean flg = false;
        for (int i = 0; i < setFlg.length; i++) {

            if (!setFlg[i]) {
                continue;
            }

            flg = false;
            for (int j = 0; j < shpgOrdMsgAry.size(); j++) {
                shpgOrdMsg = (NLZC002001ShpgOrdMsg) shpgOrdMsgAry.get(j);
                soCustDataTpCd = shpgOrdMsg.getSoCustDataTpCd();
                soCustLineLocNm = shpgOrdMsg.getSoCustLineLocNm01();

                // Ship to Name
                if (i == 0 && SO_CUST_DATA_TP.SHIP_TO.equals(soCustDataTpCd)) {
                    ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipToCustCd, shpgOrdMsg.getShipToCustCd());

                    INVTY_LOC_VTMsg invtyLocVTMsg = findInvtyLocV(msgMap, invtyTrxTMsg.shipToCustCd.getValue());
                    if (invtyLocVTMsg == null) {
                        return false;
                    }

                    ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipToCustNm, invtyLocVTMsg.invtyLocNm);
                    flg = true;
                    break;
                }
            }

            if (!flg) {
                if (i == 0) {
                    addMsgId(msgMap, MSG_ID_NLZM0046E);
                }
                return false;
            }
        }

        return true;
    }

    /**
     * <pre>
     * Find the Sell to Customer.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean findSellToCust(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Check the Sell to Customer Code.
        // If an error occurs, add a message to the Message Map.
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        String sellToCustCd = inpPrmPMsg.sellToCustCd.getValue();
        if (!ZYPCommonFunc.hasValue(sellToCustCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Sell To Code");
            return false;
        }

        // Check the Sell to Customer Name.
        // Sell to Customer Name case that has normal end.
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.sellToCustNm)) {
            invtyTrxTMsg.sellToCustCd.setValue(sellToCustCd);
            invtyTrxTMsg.sellToCustNm.setValue(inpPrmPMsg.sellToCustNm.getValue());
            return true;
        }

        // Find the Sell to Customer Master.
        SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();
        sellToCustTMsg.setSQLID("003");
        sellToCustTMsg.setConditionValue("glblCmpyCd01", inpPrmPMsg.glblCmpyCd.getValue());
        sellToCustTMsg.setConditionValue("sellToCustCd01", sellToCustCd);
        sellToCustTMsg.setMaxCount(1);
        SELL_TO_CUSTTMsgArray sellToCustTMsgAry =
            (SELL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(sellToCustTMsg);

        // If an error occurs, add a message to the Message Map.
        if (sellToCustTMsgAry.getValidCount() == 0) {
            addMsgId(msgMap, MSG_ID_NLZM0089E);
            return false;
        }
        sellToCustTMsg = sellToCustTMsgAry.no(0);
        if (!RTNCD_NORMAL_END.equals(sellToCustTMsg.getReturnCode())) {
            addMsgId(msgMap, MSG_ID_NLZM0044E, "Sell to Customer " + sellToCustTMsg.getReturnCode());
            return false;
        }

        // Sets Inventory Transaction TMsg.
        invtyTrxTMsg.sellToCustCd.setValue(sellToCustCd);
        invtyTrxTMsg.sellToCustNm.setValue(sellToCustTMsg.locNm.getValue());
        return true;
    }

    /**
     * <pre>
     * Find the Bill to Customer.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean findBillToCust(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Check the Bill to Customer Code.
        // If an error occurs, add a message to the Message Map.
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        String billToCustCd = inpPrmPMsg.billToCustCd.getValue();
        if (!ZYPCommonFunc.hasValue(billToCustCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Bill To Code");
            return false;
        }

        // Check the Bill to Customer Name.
        // Bill to Customer Name case that has normal end.
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.billToCustNm)) {
            invtyTrxTMsg.billToCustCd.setValue(billToCustCd);
            invtyTrxTMsg.billToCustNm.setValue(inpPrmPMsg.billToCustNm.getValue());
            return true;
        }

        // Find the Bill to Customer Master.
        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        billToCustTMsg.setSQLID("019");
        billToCustTMsg.setConditionValue("glblCmpyCd01", inpPrmPMsg.glblCmpyCd.getValue());
        billToCustTMsg.setConditionValue("billToCustCd01", billToCustCd);
        billToCustTMsg.setMaxCount(1);
        BILL_TO_CUSTTMsgArray billToCustTMsgAry =
            (BILL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(billToCustTMsg);

        // If an error occurs, add a message to the Message Map.
        if (billToCustTMsgAry.getValidCount() == 0) {
            addMsgId(msgMap, MSG_ID_NLZM0090E);
            return false;
        }
        billToCustTMsg = billToCustTMsgAry.no(0);
        if (!RTNCD_NORMAL_END.equals(billToCustTMsg.getReturnCode())) {
            addMsgId(msgMap, MSG_ID_NLZM0044E, "Bill to Customer " + billToCustTMsg.getReturnCode());
            return false;
        }

        // Sets Inventory Transaction TMsg.
        invtyTrxTMsg.billToCustCd.setValue(billToCustCd);
        invtyTrxTMsg.billToCustNm.setValue(billToCustTMsg.locNm.getValue());
        return true;
    }

    /**
     * <pre>
     * Find the Ship to Customer.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean findShipToCust(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Check the Ship to Customer Code.
        // If an error occurs, add a message to the Message Map.
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        String shipToCustCd = inpPrmPMsg.shipToCustCd.getValue();
        if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Ship To Code");
            return false;
        }

        // Check the Ship to Customer Name.
        // Ship to Customer Name case that has normal end.
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.shipToCustNm)) {
            invtyTrxTMsg.shipToCustCd.setValue(shipToCustCd);
            invtyTrxTMsg.shipToCustNm.setValue(inpPrmPMsg.shipToCustNm.getValue());
            return true;
        }

        // Find the Ship to Customer Master.
        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        shipToCustTMsg.setSQLID("004");
        shipToCustTMsg.setConditionValue("glblCmpyCd01", inpPrmPMsg.glblCmpyCd.getValue());
        shipToCustTMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        shipToCustTMsg.setMaxCount(1);
        SHIP_TO_CUSTTMsgArray shipToCustTMsgAry = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(shipToCustTMsg);

        // If an error occurs, add a message to the Message Map.
        if (shipToCustTMsgAry.getValidCount() == 0) {
            addMsgId(msgMap, MSG_ID_NLZM0091E);
            return false;
        }
        shipToCustTMsg = shipToCustTMsgAry.no(0);
        if (!RTNCD_NORMAL_END.equals(shipToCustTMsg.getReturnCode())) {
            addMsgId(msgMap, MSG_ID_NLZM0044E, "Ship to Customer " + shipToCustTMsg.getReturnCode());
            return false;
        }

        // Sets Inventory Transaction TMsg.
        invtyTrxTMsg.shipToCustCd.setValue(shipToCustCd);
        invtyTrxTMsg.shipToCustNm.setValue(shipToCustTMsg.locNm.getValue());
        return true;
    }

    /**
     * <pre>
     * Find the Customer Purchase Order Detail.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @param setFlg Code Setting Flag[CCY Code, TOC Code, UOM Code, Account Code, Project Code]<br>
     *         (true:set, false:skip)
     * @return Result (true:Normal, false:Error)
     */
    private boolean findCpoDtl(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg, boolean[] setFlg) {

        // Check the CPO Detail's PK.
        // If an error occurs, add a message to the Message Map.
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        String cpoOrdNum = inpPrmPMsg.cpoOrdNum.getValue();
        String cpoDtlLineNum = inpPrmPMsg.cpoDtlLineNum.getValue();
        String cpoDtlLineSubNum = inpPrmPMsg.cpoDtlLineSubNum.getValue();
        if (!ZYPCommonFunc.hasValue(cpoOrdNum)
                || !ZYPCommonFunc.hasValue(cpoDtlLineNum)
                || !ZYPCommonFunc.hasValue(cpoDtlLineSubNum)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "CPO#, CPO Line#, CPO Line Sub#");
            return false;
        }

        // Find the CPO Detail.
        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        cpoDtlTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
        cpoDtlTMsg.cpoOrdNum.setValue(cpoOrdNum);
        cpoDtlTMsg.cpoDtlLineNum.setValue(cpoDtlLineNum);
        cpoDtlTMsg.cpoDtlLineSubNum.setValue(cpoDtlLineSubNum);
        cpoDtlTMsg = (CPO_DTLTMsg) S21ApiTBLAccessor.findByKey(cpoDtlTMsg);

        // If an error occurs, add a message to the Message Map.
        if (cpoDtlTMsg == null) {
            addMsgId(msgMap, MSG_ID_NLZM0036E);
            return false;
        }
        if (!RTNCD_NORMAL_END.equals(cpoDtlTMsg.getReturnCode())) {
            addMsgId(msgMap, MSG_ID_NLZM0044E, "CPO Detail " + cpoDtlTMsg.getReturnCode());
            return false;
        }

        // Sets Inventory Transaction TMsg.
        if (setFlg[0]) {
            invtyTrxTMsg.ccyCd.setValue(cpoDtlTMsg.ccyCd.getValue());
        }
        if (setFlg[1]) {
            invtyTrxTMsg.tocCd.setValue(cpoDtlTMsg.slsRepOrSlsTeamTocCd.getValue());
        }
        if (setFlg[2]) {
            invtyTrxTMsg.uomCd.setValue(cpoDtlTMsg.custUomCd.getValue());
        }
        if (setFlg[3]) {
            invtyTrxTMsg.coaAcctCd.setValue(cpoDtlTMsg.coaAcctCd.getValue());
        }
        if (setFlg[4]) {
            invtyTrxTMsg.expProjCd.setValue(cpoDtlTMsg.coaProjCd.getValue());
        }

        // Set COA Items to Inventory Transaction TMsg.
        if (TRX.EXPENSE_SHIPMENT.equals(inpPrmPMsg.trxCd.getValue())
                || (TRX.RENTAL_SHIPMENT.equals(inpPrmPMsg.trxCd.getValue()) && TRX_RSN.RENTAL_SHIPMENT_STOCK_OUT_EXPENSE.equals(inpPrmPMsg.trxRsnCd.getValue()))
                || (TRX.EMSD_SHIPMENT.equals(inpPrmPMsg.trxCd.getValue()) && TRX_RSN.EMSD_SHIPMENT_STOCK_OUT_EXPENSE.equals(inpPrmPMsg.trxRsnCd.getValue()))) {
            invtyTrxTMsg.coaBrCd.setValue(cpoDtlTMsg.coaBrCd.getValue());
            invtyTrxTMsg.coaChCd.setValue(cpoDtlTMsg.coaChCd.getValue());
            invtyTrxTMsg.coaCcCd.setValue(cpoDtlTMsg.coaCcCd.getValue());
            invtyTrxTMsg.coaProdCd.setValue(cpoDtlTMsg.coaProdCd.getValue());
        }

        return true;
    }

    /**
     * <pre>
     * Find the Return Order Detail.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @param setFlg Code Setting Flag[TOC Code]<br>
     *         (true:set, false:skip)
     * @return Result (true:Normal, false:Error)
     */
    private boolean findDsCpoRtrnDtl(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg, boolean[] setFlg) {

        // Check the DS CPO Return Detail's PK.
        // If an error occurs, add a message to the Message Map.
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        String cpoOrdNum = inpPrmPMsg.cpoOrdNum.getValue();
        String cpoDtlLineNum = inpPrmPMsg.cpoDtlLineNum.getValue();
        String cpoDtlLineSubNum = inpPrmPMsg.cpoDtlLineSubNum.getValue();

        if (!ZYPCommonFunc.hasValue(cpoOrdNum)
                || !ZYPCommonFunc.hasValue(cpoDtlLineNum)
                || !ZYPCommonFunc.hasValue(cpoDtlLineSubNum)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "CPO#, Return Line#, Return Line Sub#");
            return false;
        }

        // Find the DS CPO Return Detail.
        DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
        dsCpoRtrnDtlTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
        dsCpoRtrnDtlTMsg.cpoOrdNum.setValue(cpoOrdNum);
        dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum.setValue(cpoDtlLineNum);
        dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.setValue(cpoDtlLineSubNum);
        dsCpoRtrnDtlTMsg = (DS_CPO_RTRN_DTLTMsg) S21ApiTBLAccessor.findByKey(dsCpoRtrnDtlTMsg);

        // If an error occurs, add a message to the Message Map.
        if (dsCpoRtrnDtlTMsg == null) {
            addMsgId(msgMap, MSG_ID_NLZM0036E);
            return false;
        }
        if (!RTNCD_NORMAL_END.equals(dsCpoRtrnDtlTMsg.getReturnCode())) {
            addMsgId(msgMap, MSG_ID_NLZM0044E, "DS CPO Return Detail " + dsCpoRtrnDtlTMsg.getReturnCode());
            return false;
        }

        // Sets Inventory Transaction TMsg.
        if (setFlg[0]) {
            invtyTrxTMsg.tocCd.setValue(dsCpoRtrnDtlTMsg.slsRepOrSlsTeamTocCd.getValue());
        }

        return true;
    }

    /**
     * <pre>
     * Find the Shipping Order Detail and Shipping Plan.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean findShpgOrdShpgPln(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Check the SO Number, SO Slip Number.
        // If an error occurs, add a message to the Message Map.
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        String soNum = inpPrmPMsg.soNum.getValue();
        String soSlpNum = inpPrmPMsg.soSlpNum.getValue();
        if (!ZYPCommonFunc.hasValue(soNum)
                || !ZYPCommonFunc.hasValue(soSlpNum)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "SO Number, SO Slip Number");
            return false;
        }

        // Find the Shipping Order Detail and Shipping Plan.
        NLZC002001ShpgOrdMsg shpgOrdMsg = new NLZC002001ShpgOrdMsg();
        shpgOrdMsg.setGlblCmpyCd(inpPrmPMsg.glblCmpyCd.getValue());
        shpgOrdMsg.setSoNum(soNum);
        shpgOrdMsg.setSoSlpNum(soSlpNum);
        List shpgOrdMsgAry = ssmBatchClient.queryObjectList("getShpgOrdShpgPln", shpgOrdMsg);

        // If an error occurs, add a message to the Message Map.
        if (shpgOrdMsgAry.isEmpty()) {
            addMsgId(msgMap, MSG_ID_NLZM0035E);
            return false;
        }

        // Sets Inventory Transaction TMsg.
        shpgOrdMsg = (NLZC002001ShpgOrdMsg) shpgOrdMsgAry.get(0);
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.tocCd, shpgOrdMsg.getSlsRepTocCd());

        return true;
    }

    /**
     * <pre>
     * Find the S21 Organization Master.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean findS21Org(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Check Toc.
        // if toc is not set, do not search.
        String tocCd = invtyTrxTMsg.tocCd.getValue();
        if (!ZYPCommonFunc.hasValue(tocCd)) {
            return true;
        }

        // Find the S21 Organization Master.
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        S21_ORGTMsg s21OrgTMsg = new S21_ORGTMsg();
        s21OrgTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
        s21OrgTMsg.tocCd.setValue(tocCd);
        s21OrgTMsg = (S21_ORGTMsg) S21ApiTBLAccessor.findByKey(s21OrgTMsg);

        // If an error occurs, add a message to the Message Map.
        if (s21OrgTMsg == null) {
            addMsgId(msgMap, MSG_ID_NLZM0038E);
            return false;
        }
        if (!RTNCD_NORMAL_END.equals(s21OrgTMsg.getReturnCode())) {
            addMsgId(msgMap, MSG_ID_NLZM0044E, "S21 Organization Master " + s21OrgTMsg.getReturnCode());
            return false;
        }

        // Sets Inventory Transaction TMsg.
        invtyTrxTMsg.firstOrgCd.setValue(s21OrgTMsg.firstOrgCd.getValue());
        invtyTrxTMsg.scdOrgCd.setValue(s21OrgTMsg.scdOrgCd.getValue());
        invtyTrxTMsg.thirdOrgCd.setValue(s21OrgTMsg.thirdOrgCd.getValue());
        invtyTrxTMsg.frthOrgCd.setValue(s21OrgTMsg.frthOrgCd.getValue());
        invtyTrxTMsg.fifthOrgCd.setValue(s21OrgTMsg.fifthOrgCd.getValue());
        invtyTrxTMsg.sixthOrgCd.setValue(s21OrgTMsg.sixthOrgCd.getValue());
        invtyTrxTMsg.svnthOrgCd.setValue(s21OrgTMsg.svnthOrgCd.getValue());
        invtyTrxTMsg.eighthOrgCd.setValue(s21OrgTMsg.eighthOrgCd.getValue());
        return true;
    }

    /**
     * <pre>
     * Find the S21 Organization Master and Vendor Return Detail.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean findS21OrgVndRtrnDtl(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Find the Inventory Order and Warehous.
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        NLZC002001OrgVndRtrnCpoMsg orgVndRtrnCpoMsg = new NLZC002001OrgVndRtrnCpoMsg();
        orgVndRtrnCpoMsg.setGlblCmpyCd(inpPrmPMsg.glblCmpyCd.getValue());
        orgVndRtrnCpoMsg.setVndRtrnNum(inpPrmPMsg.vndRtrnNum.getValue());
        orgVndRtrnCpoMsg.setTrxLineNum(inpPrmPMsg.trxLineNum.getValue());
        orgVndRtrnCpoMsg.setTrxLineSubNum(inpPrmPMsg.trxLineSubNum.getValue());
        List orgVndRtrnCpoMsgAry = ssmBatchClient.queryObjectList("getS21OrgCdVndRtrn", orgVndRtrnCpoMsg);

        // If an error occurs, add a message to the Message Map.
        if (orgVndRtrnCpoMsgAry.isEmpty()) {
            addMsgId(msgMap, MSG_ID_NLZM0038E);
            return false;
        }

        // Sets Inventory Transaction TMsg.
        orgVndRtrnCpoMsg = (NLZC002001OrgVndRtrnCpoMsg) orgVndRtrnCpoMsgAry.get(0);
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.firstOrgCd, orgVndRtrnCpoMsg.getFirstOrgCd());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.scdOrgCd, orgVndRtrnCpoMsg.getScdOrgCd());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.thirdOrgCd, orgVndRtrnCpoMsg.getThirdOrgCd());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.frthOrgCd, orgVndRtrnCpoMsg.getFrthOrgCd());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.fifthOrgCd, orgVndRtrnCpoMsg.getFifthOrgCd());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.sixthOrgCd, orgVndRtrnCpoMsg.getSixthOrgCd());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.svnthOrgCd, orgVndRtrnCpoMsg.getSvnthOrgCd());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.eighthOrgCd, orgVndRtrnCpoMsg.getEighthOrgCd());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.tocCd, orgVndRtrnCpoMsg.getTocCd());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.ccyCd, orgVndRtrnCpoMsg.getCcyCd());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's Shipped From Location Customer Name.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean findShippedFromLocCustNm(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Check parameter.
        String shipFromLocCustCd = invtyTrxTMsg.shipFromLocCustCd.getValue();
        if (!ZYPCommonFunc.hasValue(shipFromLocCustCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Shipped From Code");
            return false;
        } else if (ZYPCommonFunc.hasValue(invtyTrxTMsg.shipFromLocCustNm)) {
            return true;
        }

        // Find the Inventory Location View.
        INVTY_LOC_VTMsg invtyLocVTMsg = findInvtyLocV(msgMap, shipFromLocCustCd);
        if (invtyLocVTMsg == null) {
            return false;
        }

        // Sets Inventory Transaction TMsg.
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyLocVTMsg.invtyLocNm.getValue());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's Shipped To Location Customer Name.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean findShippedToLocCustNm(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Check parameter.
        String shipToLocCustCd = invtyTrxTMsg.shipToLocCustCd.getValue();
        if (!ZYPCommonFunc.hasValue(shipToLocCustCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Shipped To Code");
            return false;
        } else if (ZYPCommonFunc.hasValue(invtyTrxTMsg.shipToLocCustNm)) {
            return true;
        }

        // Find the Inventory Location View.
        INVTY_LOC_VTMsg invtyLocVTMsg = findInvtyLocV(msgMap, shipToLocCustCd);
        if (invtyLocVTMsg == null) {
            return false;
        }

        // Sets Inventory Transaction TMsg.
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyLocVTMsg.invtyLocNm.getValue());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's PO Numbers.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setPoNum(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.poOrdNum) || !ZYPCommonFunc.hasValue(inpPrmPMsg.poOrdDtlLineNum)) {

            addMsgId(msgMap, MSG_ID_NLZM0113E, "PO Number");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.poOrdNum.setValue(inpPrmPMsg.poOrdNum.getValue());
        invtyTrxTMsg.poOrdDtlLineNum.setValue(inpPrmPMsg.poOrdDtlLineNum.getValue());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's PO Receiving Numbers.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setPoRcvNum(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.poRcvNum) || !ZYPCommonFunc.hasValue(inpPrmPMsg.poRcvLineNum)) {

            addMsgId(msgMap, MSG_ID_NLZM0113E, "PO Receiving Number");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.poRcvNum.setValue(inpPrmPMsg.poRcvNum.getValue());
        invtyTrxTMsg.poRcvLineNum.setValue(inpPrmPMsg.poRcvLineNum.getValue());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's RWS Numbers.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRwsNum(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.rwsNum)
                || !ZYPCommonFunc.hasValue(inpPrmPMsg.rwsRefNum)
                || !ZYPCommonFunc.hasValue(inpPrmPMsg.rwsDtlLineNum)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "RWS#, RWS Reference#, RWS Line#");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.rwsNum.setValue(inpPrmPMsg.rwsNum.getValue());
        invtyTrxTMsg.rwsRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());
        invtyTrxTMsg.rwsDtlLineNum.setValue(inpPrmPMsg.rwsDtlLineNum.getValue());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's RMA Numbers.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRmaNum(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.rmaNum)
                || !ZYPCommonFunc.hasValue(inpPrmPMsg.rmaLineNum)
                || !ZYPCommonFunc.hasValue(inpPrmPMsg.rmaLineSubNum)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "RMA#, RMA Line#, RMA Line Sub#");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.rmaNum.setValue(inpPrmPMsg.rmaNum.getValue());
        invtyTrxTMsg.rmaLineNum.setValue(inpPrmPMsg.rmaLineNum.getValue());
        invtyTrxTMsg.rmaLineSubNum.setValue(inpPrmPMsg.rmaLineSubNum.getValue());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's CPO Numbers.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setCpoNum(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.cpoOrdNum)
                || !ZYPCommonFunc.hasValue(inpPrmPMsg.cpoDtlLineNum)
                || !ZYPCommonFunc.hasValue(inpPrmPMsg.cpoDtlLineSubNum)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "CPO#, CPO Line#, CPO Line Sub#");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.cpoOrdNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());
        invtyTrxTMsg.cpoDtlLineNum.setValue(inpPrmPMsg.cpoDtlLineNum.getValue());
        invtyTrxTMsg.cpoDtlLineSubNum.setValue(inpPrmPMsg.cpoDtlLineSubNum.getValue());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's Sell to, Bill to, Ship to.
     * </pre>
     * 
     * @param inpPrmPMsg Input parameter
     * @param invtyTrxTMsg Inventory Transaction TMsg
     */
    private boolean setSellBillShip(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Sell to
        if (!findSellToCust(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Bill to
        if (!findBillToCust(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Ship to
        if (!findShipToCust(msgMap, invtyTrxTMsg)) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's Original CPO Number.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setOrigCpoNum(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.origCpoOrdNum)
                || !ZYPCommonFunc.hasValue(inpPrmPMsg.origCpoDtlLineNum)
                || !ZYPCommonFunc.hasValue(inpPrmPMsg.origCpoDtlLineSubNum)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Orig CPO#, Orig CPO Line#, Orig CPO Line Sub#");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.origCpoOrdNum.setValue(inpPrmPMsg.origCpoOrdNum.getValue());
        invtyTrxTMsg.origCpoDtlLineNum.setValue(inpPrmPMsg.origCpoDtlLineNum.getValue());
        invtyTrxTMsg.origCpoDtlLineSubNum.setValue(inpPrmPMsg.origCpoDtlLineSubNum.getValue());

        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's Invoice Numbers.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setInvNum(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.invNum)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Invoice Number");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.invNum.setValue(inpPrmPMsg.invNum.getValue());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's SO Number.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setSoNum(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.soNum) || !ZYPCommonFunc.hasValue(inpPrmPMsg.soSlpNum)) {

            addMsgId(msgMap, MSG_ID_NLZM0113E, "SO Number, SO Slip Number");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.soNum.setValue(inpPrmPMsg.soNum.getValue());
        invtyTrxTMsg.soSlpNum.setValue(inpPrmPMsg.soSlpNum.getValue());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's Inventory Order Number.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setInvtyOrdNum(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.invtyOrdNum) || !ZYPCommonFunc.hasValue(inpPrmPMsg.invtyOrdLineNum)) {

            addMsgId(msgMap, MSG_ID_NLZM0113E, "Inventory Order Number");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.invtyOrdNum.setValue(inpPrmPMsg.invtyOrdNum.getValue());
        invtyTrxTMsg.invtyOrdLineNum.setValue(inpPrmPMsg.invtyOrdLineNum.getValue());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's Work Order Number.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @param lineFlg true is set Work Order Line Number
     * @return Result (true:Normal, false:Error)
     */
    private boolean setWrkOrdNum(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg, boolean lineFlg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.wrkOrdNum)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Work Order Number");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.wrkOrdNum.setValue(inpPrmPMsg.wrkOrdNum.getValue());

        if (lineFlg) {

            // Check parameter.
            if (!ZYPCommonFunc.hasValue(inpPrmPMsg.wrkOrdLineNum)) {
                addMsgId(msgMap, MSG_ID_NLZM0113E, "Work Order Line Number");
                return false;
            }

            // Set Parameter.
            invtyTrxTMsg.wrkOrdLineNum.setValue(inpPrmPMsg.wrkOrdLineNum.getValue());
        }
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's UOM Code.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setUomCd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.uomCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "UOM Code");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.uomCd.setValue(inpPrmPMsg.uomCd.getValue());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's UOM Code from Each.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setUomCdFromEach(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check the Currency Code.
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.uomCd)) {
            invtyTrxTMsg.uomCd.setValue(inpPrmPMsg.uomCd.getValue());
            return true;
        }

        invtyTrxTMsg.uomCd.setValue(PKG_UOM.EACH);
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's Slip Number.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setSlipNum(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.invtyTrxSlpNum)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Inventory Transaction Slip Number");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.invtyTrxSlpNum.getValue());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's Currency Code.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setCcyCd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.ccyCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "CCY Code");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.ccyCd.setValue(inpPrmPMsg.ccyCd.getValue());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's TOC Code.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setTocCd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.tocCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "TOC Code");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.tocCd.setValue(inpPrmPMsg.tocCd.getValue());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's BOL Number.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setBolNum(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.bolNum)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "BOL Number");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());
        return true;
    }
    /**
     * <pre>
     * Set Inventory Transaction's Pro Number.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setProNum(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.proNum)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Pro Number");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's Shipped Cost Amount and Unit Cost Amount.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setShipCostAmtUnitCostAmt(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg, String invtyLocCd) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(invtyLocCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Inventory Location Code");
            return false;
        }

        // Calculate by Inventory Valuation
        NLXC001001GetInventoryItemCostBean getInventoryItemCostBean = new NLXC001001GetInventoryItemCostBean();

        getInventoryItemCostBean.setGlblCmpyCd(inpPrmPMsg.glblCmpyCd.getValue());
        getInventoryItemCostBean.setMdseCd(inpPrmPMsg.mdseCd.getValue());
        getInventoryItemCostBean.setInvtyLocCd(invtyLocCd);
        getInventoryItemCostBean.setQty(inpPrmPMsg.xxRqstQty.getValue());

        NLXC001001GetInventoryItemCost.getInventoryItemCost(getInventoryItemCostBean);

        // Set Parameter.
        BigDecimal unitCostAmt = getInventoryItemCostBean.getUnitPrcAmt();
        BigDecimal shipCostAmt = getInventoryItemCostBean.getTotPrcAmt();

        if (!ZYPCommonFunc.hasValue(unitCostAmt)) {

            addMsgId(msgMap, MSG_ID_NLZM2217E);
            return false;
        } else {

            invtyTrxTMsg.unitCostAmt.setValue(unitCostAmt);
        }

        if (!ZYPCommonFunc.hasValue(shipCostAmt)) {

            addMsgId(msgMap, MSG_ID_NLZM2217E);
            return false;
        } else {

            invtyTrxTMsg.shipCostAmt.setValue(shipCostAmt);
        }

        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's Shipped Sales Amount and Unit Price Amount.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setShipSlsAmtUnitPrcAmt(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg, String invtyLocCd) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(invtyLocCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Inventory Location Code");
            return false;
        }

        // Calculate by Inventory Valuation
        NLXC001001GetInventoryItemCostBean getInventoryItemCostBean = new NLXC001001GetInventoryItemCostBean();

        getInventoryItemCostBean.setGlblCmpyCd(inpPrmPMsg.glblCmpyCd.getValue());
        getInventoryItemCostBean.setMdseCd(inpPrmPMsg.mdseCd.getValue());
        getInventoryItemCostBean.setInvtyLocCd(invtyLocCd);
        getInventoryItemCostBean.setQty(inpPrmPMsg.xxRqstQty.getValue());

        NLXC001001GetInventoryItemCost.getInventoryItemCost(getInventoryItemCostBean);

        // Set Parameter.
        BigDecimal unitCostAmt = getInventoryItemCostBean.getUnitPrcAmt();
        BigDecimal shipCostAmt = getInventoryItemCostBean.getTotPrcAmt();

        if (!ZYPCommonFunc.hasValue(unitCostAmt)) {

            addMsgId(msgMap, MSG_ID_NLZM2217E);
            return false;
        } else {

            invtyTrxTMsg.unitPrcAmt.setValue(unitCostAmt);
        }

        if (!ZYPCommonFunc.hasValue(shipCostAmt)) {

            addMsgId(msgMap, MSG_ID_NLZM2217E);
            return false;
        } else {

            invtyTrxTMsg.shipSlsAmt.setValue(shipCostAmt);
        }

        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's Shipped Cost Amount.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setShipCostAmt(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.shipCostAmt)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Shipped Cost Amount");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.shipCostAmt.setValue(inpPrmPMsg.shipCostAmt.getValue());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's Unit Cost Amount.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setUnitCostAmt(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // set output parameter and set scale
        String costRound = ZYPCodeDataUtil.getVarCharConstValue(COST_ROUND_OPTION, invtyTrxTMsg.glblCmpyCd.getValue());
        int costPrecision = Integer.valueOf(ZYPCodeDataUtil.getVarCharConstValue(COST_PRECISION, invtyTrxTMsg.glblCmpyCd.getValue()));

        BigDecimal unitCostAmt = (invtyTrxTMsg.shipCostAmt.getValue()).divide(inpPrmPMsg.xxRqstQty.getValue());

        if (ROUND_UP.equals(costRound)) {
            invtyTrxTMsg.unitCostAmt.setValue(unitCostAmt.setScale(costPrecision, BigDecimal.ROUND_UP));

        } else if (ROUND.equals(costRound)) {
            invtyTrxTMsg.unitCostAmt.setValue(unitCostAmt.setScale(costPrecision, BigDecimal.ROUND_HALF_UP));

        } else {
            invtyTrxTMsg.unitCostAmt.setValue(unitCostAmt.setScale(costPrecision, BigDecimal.ROUND_DOWN));

        }

        return true;
    }
    /**
     * <pre>
     * Set Inventory Transaction's COA Product Code.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setCoaProdCd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.coaProdCd)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Coa Product Code");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.coaProdCd.setValue(inpPrmPMsg.coaProdCd.getValue());
        return true;
    }

    /**
     * <pre>
     * Set Inventory Transaction's Currency Code from Global Company.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setCcyCdFromGlblCmpy(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check the Currency Code.
        // Currency Code case that has normal end.
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.ccyCd)) {
            invtyTrxTMsg.ccyCd.setValue(inpPrmPMsg.ccyCd.getValue());
            return true;
        }

        // find Global Company
        return findGlblCmpy(msgMap, invtyTrxTMsg);
    }

    /**
     * <pre>
     * Set Vendor Return Number.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setVendorReturnNum(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Check parameter.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.vndRtrnNum)
                || !ZYPCommonFunc.hasValue(inpPrmPMsg.trxLineNum)
                || !ZYPCommonFunc.hasValue(inpPrmPMsg.trxLineSubNum)) {
            addMsgId(msgMap, MSG_ID_NLZM0113E, "Vendor Return#, Line#, Sub#");
            return false;
        }

        // Set Parameter.
        invtyTrxTMsg.vndRtrnNum.setValue(inpPrmPMsg.vndRtrnNum.getValue());
        invtyTrxTMsg.trxLineNum.setValue(inpPrmPMsg.trxLineNum.getValue());
        invtyTrxTMsg.trxLineSubNum.setValue(inpPrmPMsg.trxLineSubNum.getValue());
        return true;
    }

    /**
     * <pre>
     * Set data for "Purchase Stock-In".
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @param scenarioNo Scenario Number
     * @return Result (true:Normal, false:Error)
     */
    private boolean setPrchStkI(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg, int scenarioNo) {

        switch (scenarioNo) {

            // Purchase Stock-In/Purchase Stock-In/Inbound/Domestic
            case PRCH_STK_I_PRCH_STK_I_INBD_DOM:
                printDebugLog("Purchase Stock-In/Purchase Stock-In/Inbound/Domestic");
                if (!setPrchStkIPrchStkIInbdDom(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Purchase Stock-In/Purchase Stock-In/Inventory/Item Entry SP
            case PRCH_STK_I_PRCH_STK_I_INVTY_ITM_ENTY_SP:
                printDebugLog("Purchase Stock-In/Purchase Stock-In/Inventory/Item Entry SP");
                if (!setPrchStkIPrchStkIInvtyItmEntySp(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Purchase Stock-In/Purchase Stock-In/Inbound/Sell Then Buy
            case PRCH_STK_I_PRCH_STK_I_INBD_SELL_THEN_BUY:
                printDebugLog("Purchase Stock-In/Purchase Stock-In/Inbound/Sell Then Buy");
                if (!setPrchStkIPrchStkIInbdSellThenBuy(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Purchase Stock-In/Purchase Stock-In/OM/Intangible with Cost
            case PRCH_STK_I_PRCH_STK_I_OM_INTG_WITH_COST:
                printDebugLog("Purchase Stock-In/Purchase Stock-In/OM/Intangible with Cost");
                if (!setPrchStkIPrchStkIOmIntgWithCost(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Purchase Stock-In/Purchase Stock-In/Subcontract
            case PRCH_STK_I_PRCH_STK_I_SUB_CONTR:
                printDebugLog("Purchase Stock-In/Purchase Stock-In/Subcontract");
                if (!setPrchStkIPrchStkISubContr(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Purchase Stock-In/Domestic Vendor Return/Outbound/
            case PRCH_STK_I_DOM_VND_RTRN_OTBD:
                printDebugLog("Purchase Stock-In/Domestic Vendor Return/Outbound/");
                if (!setPrchStkIDomVndRtrnOtbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Unexpected
            default:
                addMsgId(msgMap, MSG_ID_NLZM0113E, "Scenario case");
                return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Purchase Stock-In/Purchase Stock-In/Inbound/Domestic".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setPrchStkIPrchStkIInbdDom(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // PO Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poOrdNum, inpPrmPMsg.poOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poOrdDtlLineNum, inpPrmPMsg.poOrdDtlLineNum.getValue());

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.poOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Vendor (find Vender)
        if (!findVnd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.vndCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.vndNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Purchase Stock-In/Purchase Stock-In/Inventory/Item Entry SP".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setPrchStkIPrchStkIInvtyItmEntySp(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Slip Number
        if (!setSlipNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Vendor (find Vendor)
        if (!findVnd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.vndCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.vndNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        invtyTrxTMsg.uomCd.setValue(PKG_UOM.EACH);

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Purchase Stock-In/Purchase Stock-In/Inbound/Sell Then Buy".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setPrchStkIPrchStkIInbdSellThenBuy(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.cpoOrdNum, inpPrmPMsg.cpoOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.cpoDtlLineNum, inpPrmPMsg.cpoDtlLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.cpoDtlLineSubNum, inpPrmPMsg.cpoDtlLineSubNum.getValue());

        // PO Number
        if (!setPoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.poOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Vendor (find Vendor)
        if (!findVnd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.vndCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.vndNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Purchase Stock-In/Purchase Stock-In/OM/Intangible with Cost".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setPrchStkIPrchStkIOmIntgWithCost(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Invoice Number / Ignore mandatory check
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.invNum)) {
            // Set Parameter.
            invtyTrxTMsg.invNum.setValue(inpPrmPMsg.invNum.getValue());
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // set Ref# from rwsRefNum(Only in this case, for Defect#1440)
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.rwsRefNum)) {
            // Set Parameter.
            invtyTrxTMsg.rwsRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());
            invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());
        }

        // Vendor (find Vendor)
        if (!findVnd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.vndCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.vndNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code
        if (!setCcyCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }


        return true;
    }

    /**
     * <pre>
     * Set data for "Purchase Stock-In/Purchase Stock-In/SubContract".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setPrchStkIPrchStkISubContr(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // SO Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.soNum, inpPrmPMsg.soNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.soSlpNum, inpPrmPMsg.soSlpNum.getValue());

        // PO Number
        if (!setPoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.poOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Vendor (find Vendor)
        if (!findVnd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.vndCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.vndNm.getValue());
        /* 06/03/2016 CSAI Y.Imazu Mod QC#9498 START */
        setPoSrcWh(msgMap, invtyTrxTMsg);
        /* 06/03/2016 CSAI Y.Imazu Mod QC#9498 END */

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Purchase Stock-In/Domestic Vendor Return/Outbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setPrchStkIDomVndRtrnOtbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Vendor Return Number
        if (!setVendorReturnNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.vndRtrnNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Vendor (find Vendor)
        if (!findVnd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.vndCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.vndNm.getValue());

        // Organization, TOC Code, Currency Code
        // (find S21 Organization Master and Vendor Return Detail)
        if (!findS21OrgVndRtrnDtl(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Sales".
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @param scenarioNo Scenario Number
     * @return Result (true:Normal, false:Error)
     */
    private boolean setSls(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg, int scenarioNo) {

        switch (scenarioNo) {

            // Sales/Regular Sales with Cost/OM/
            case SLS_REG_SLS_WITH_COST_OM:
                printDebugLog("Sales/Regular Sales with Cost/OM/");
                if (!setSlsRegSlsWithCostOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Sales/Regular Sales with Cost/OM/Intangible with Cost
            case SLS_REG_SLS_WITH_COST_OM_INTG_WITH_COST:
                printDebugLog("Sales/Regular Sales with Cost/OM/Intangible with Cost");
                if (!setSlsRegSlsWithCostOmIntgWithCost(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Sales/Credit & Rebill/OM/
            case SLS_CR_AND_REBIL_OM:
                printDebugLog("Sales/Credit & Rebill/OM/");
                if (!setSlsCrAndRebilOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Sales/Return/Inbound/
            case SLS_RTRN_INBD:
                printDebugLog("Sales/Return/Inbound/");
                if (!setSlsRtrnInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Sales/Loan to Sale/OM/
            case SLS_LOAN_TO_SLS_OM:
                printDebugLog("Sales/Loan to Sale/OM/");
                if (!setSlsLoanToSlsOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

              //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) START
            case SLS_RTRN_INBD_INIT_SUPPLY:
                printDebugLog("Sales/Return/Inbound/InitialSupply");
                if (!setSlsRtrnInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            case SLS_RTRN_INBD_CNTR_SUPPLY:
                printDebugLog("Sales/Return/Inbound/ContractSupply");
                if (!setSlsRtrnInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;
              //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) END

            // 2017/10/20 S21_NA#16347 Add Start
            case SLS_CASH_LEASE_INIT_SUP:
                printDebugLog("CASH LEASE - INIT SUP");
                if (!setSlsCrAndRebilOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break; 

            case SLS_CASH_LEASE_CNTR_SUP:
                printDebugLog("CASH LEASE - CNTR SUP");
                if (!setSlsCrAndRebilOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break; 

            case SLS_LOAN_TO_SALES:
                printDebugLog("Loan to Sales");
                if (!setSlsCrAndRebilOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break; 

            case SLS_REG_SALES_AJE_LINK:
                printDebugLog("Regular Sales Asset for AJE Link");
                if (!setSlsCrAndRebilOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break; 
            // 2017/10/20 S21_NA#16347 Add End

            // Unexpected
            default:
                addMsgId(msgMap, MSG_ID_NLZM0113E, "Scenario case");
                return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Sales/Regular Sales with Cost/OM/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setSlsRegSlsWithCostOm(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Set SO Number.
        invtyTrxTMsg.soNum.setValue(inpPrmPMsg.soNum.getValue());
        invtyTrxTMsg.soSlpNum.setValue(inpPrmPMsg.soSlpNum.getValue());

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Invoice Number
        if (!setInvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code, UOM Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true, false, false })) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount. QC#50378 Mod
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.shipCostAmt)) {
            if (!setShipCostAmt(msgMap, invtyTrxTMsg)) {
                return false;
            }
        } else {
            if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
                return false;
            }
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Sales/Regular Sales with Cost/OM/Intangible with Cost".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setSlsRegSlsWithCostOmIntgWithCost(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Invoice Number
        if (!setInvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code, UOM Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true, false, false })) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount. QC#50378 Mod
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.shipCostAmt)) {
            if (!setShipCostAmt(msgMap, invtyTrxTMsg)) {
                return false;
            }
        } else {
            if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
                return false;
            }
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Sales/Credit & Rebill/OM/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setSlsCrAndRebilOm(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Invoice Number
        if (!setInvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code, CCY Code, UOM Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true, false, false })) {
            return false;
        }

        // Shipped Cost Amount
        if (!setShipCostAmt(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost
        if (!setUnitCostAmt(msgMap, invtyTrxTMsg)) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Sales/Return/Inbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setSlsRtrnInbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RMA Number
        if (!setRmaNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.rmaNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code (find DS CPO Return Detail)
        if (!findDsCpoRtrnDtl(msgMap, invtyTrxTMsg, new boolean[] {true})) {
            return false;
        }

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        setUomCdFromEach(msgMap, invtyTrxTMsg);

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Sales/Loan to Sale/OM/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setSlsLoanToSlsOm(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Invoice Number
        if (!setInvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Original CPO Number
        if (!setOrigCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.origCpoOrdNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // Shipped To
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code
        if (!setTocCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Currency Code
        if (!setCcyCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        /* 12/21/2015 CSAI Y.Imazu Mod QC#793 START */
//        // Shipped Cost Amount
//        if (!setShipCostAmt(msgMap, invtyTrxTMsg)) {
//            return false;
//        }
//
//        // Unit Cost
//        if (!setUnitCostAmt(msgMap, invtyTrxTMsg)) {
//            return false;
//        }
        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }
        /* 12/21/2015 CSAI Y.Imazu Mod QC#793 END */

        // QC#59038
        if ("CR".equals(invtyTrxTMsg.invtyLocCd.getValue()) && ZYPCommonFunc.hasValue(inpPrmPMsg.shipCostAmt)) {
            invtyTrxTMsg.shipCostAmt.setValue(inpPrmPMsg.shipCostAmt.getValue());
            setUnitCostAmt(msgMap, invtyTrxTMsg);
            return true;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Expense".
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @param scenarioNo Scenario Number
     * @return Result (true:Normal, false:Error)
     */
    private boolean setExp(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg, int scenarioNo) {

        switch (scenarioNo) {

            // Expense/Expense Shipment/OM/
            case EXP_EXP_SHIP_OM:
                printDebugLog("Expense/Expense Shipment/OM/");
                if (!setExpExpShipOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Expense/Expense Shipment/OM/Intangible with cost
            case EXP_EXP_SHIP_OM_INTG_WITH_COST:
                printDebugLog("Expense/Expense Shipment/OM/Intangible with cost");
                if (!setExpExpShipOmIntgWithCost(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Expense/Expense Return/Inbound/
            case EXP_EXP_RTRN_INBD:
                printDebugLog("Expense/Expense Return/Inbound/");
                if (!setExpExpRtrnInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Expense/Loan Shipment Stock-Out Expense/
            case EXP_LOAN_SHIP_STK_O:
                printDebugLog("Expense/Loan Shipment Stock-Out Expense/");
                if (!setExpExpLoanShipStkO(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Expense/Loan to Inventory Shortage Stock-Out/
            case EXP_LOAN_TO_INVTY_SHORT_STK_O:
                printDebugLog("Expense/Loan to Inventory Shortage Stock-Out/");
                if (!setExpExpLoanToInvtyShortStkO(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // QC#63527 2024/03/14 Start
            // Expense/Loan Shipment Stock-Out Expense/
            case EXP_OFF_THE_BOOK_LOAN_SHIP_STK_O:
                printDebugLog("Expense/Off The Book Loan Shipment Stock-Out Expense/");
                if (!setExpExpOffTheBookLoanShipStkO(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Expense/Loan to Inventory Shortage Stock-Out/
            case EXP_OFF_THE_BOOK_LOAN_TO_INVTY_SHORT_STK_O:
                printDebugLog("Expense/Off The Book Loan to Inventory Shortage Stock-Out/");
                if (!setExpExpOffTheBookLoanToInvtyShortStkO(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;
            // QC#63527 2024/03/14 End

            // Expense/Drop Shipment Loan Stock-Out Expense/
            case EXP_LOAN_DROP_SHIP_STK_O:
                printDebugLog("Expense/Drop Shipment Loan Stock-Out Expense/");
                if (!setExpExpLoanDropShipStkO(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Expense/Loan Stock-Out Expense Intangible with Cost/
            case EXP_LOAN_SHIP_STK_O_INTG_WITH_COST:
                printDebugLog("Expense/Loan Stock-Out Expense Intangible with Cost/");
                if (!setExpExpLoanStkOIntgWithCost(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Unexpected
            default:
                addMsgId(msgMap, MSG_ID_NLZM0113E, "Scenario case");
                return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Expense/Expense Shipment/OM/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setExpExpShipOm(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Set SO Number.
        invtyTrxTMsg.soNum.setValue(inpPrmPMsg.soNum.getValue());
        invtyTrxTMsg.soSlpNum.setValue(inpPrmPMsg.soSlpNum.getValue());

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code, UOM Code, Account Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true, true, false })) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Expense/Expense Shipment/OM/Intangible with cost".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setExpExpShipOmIntgWithCost(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code, UOM Code, Account Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true, true, false })) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Expense/Expense Return/Inbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setExpExpRtrnInbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RMA Number
        if (!setRmaNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.rmaNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code (find DS CPO Return Detail)
        if (!findDsCpoRtrnDtl(msgMap, invtyTrxTMsg, new boolean[] {true})) {
            return false;
        }

        // COA
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.coaAcctCd, inpPrmPMsg.coaAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.coaBrCd, inpPrmPMsg.coaBrCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.coaChCd, inpPrmPMsg.coaChCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.coaCcCd, inpPrmPMsg.coaCcCd.getValue());

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        setUomCdFromEach(msgMap, invtyTrxTMsg);

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Expense/Loan Shipment Stock-Out Expense/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setExpExpLoanShipStkO(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Set SO Number.
        invtyTrxTMsg.soNum.setValue(inpPrmPMsg.soNum.getValue());
        invtyTrxTMsg.soSlpNum.setValue(inpPrmPMsg.soSlpNum.getValue());

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code, UOM Code, Account Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true, true, false })) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Expense/Loan to Inventory Shortage Stock-Out/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setExpExpLoanToInvtyShortStkO(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RMA Number
        if (!setRmaNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Original CPO Number
        if (!setOrigCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.rmaNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(inpPrmPMsg.shipToLocCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(inpPrmPMsg.shipToLocCustNm.getValue());
        if (!findShippedToLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // TOC Code (find DS CPO Return Detail)
        if (!findDsCpoRtrnDtl(msgMap, invtyTrxTMsg, new boolean[] {true})) {
            return false;
        }

        // COA
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.coaAcctCd, inpPrmPMsg.coaAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.coaBrCd, inpPrmPMsg.coaBrCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.coaChCd, inpPrmPMsg.coaChCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.coaCcCd, inpPrmPMsg.coaCcCd.getValue());

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        setUomCdFromEach(msgMap, invtyTrxTMsg);

        /* 12/21/2015 CSAI Y.Imazu Mod QC#793 START */
//        // Ship Cost Amount
//        if (!setShipCostAmt(msgMap, invtyTrxTMsg)) {
//            return false;
//        }
//
//        // Unit Cost Amount
//        if (!setUnitCostAmt(msgMap, invtyTrxTMsg)) {
//            return false;
//        }
        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }
        /* 12/21/2015 CSAI Y.Imazu Mod QC#793 END */

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    // QC#63527 2024/03/14 Start
    /**
     * <pre>
     * Set data for "Expense/Off The Book Loan Shipment Stock-Out Expense/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setExpExpOffTheBookLoanShipStkO(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Set SO Number.
        invtyTrxTMsg.soNum.setValue(inpPrmPMsg.soNum.getValue());
        invtyTrxTMsg.soSlpNum.setValue(inpPrmPMsg.soSlpNum.getValue());

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code, UOM Code, Account Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true, true, false })) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }
        
        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Expense/Off The Book Loan to Inventory Shortage Stock-Out/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setExpExpOffTheBookLoanToInvtyShortStkO(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RMA Number
        if (!setRmaNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Original CPO Number
        if (!setOrigCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.rmaNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(inpPrmPMsg.shipToLocCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(inpPrmPMsg.shipToLocCustNm.getValue());
        if (!findShippedToLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // TOC Code (find DS CPO Return Detail)
        if (!findDsCpoRtrnDtl(msgMap, invtyTrxTMsg, new boolean[] {true})) {
            return false;
        }

        // COA
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.coaAcctCd, inpPrmPMsg.coaAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.coaBrCd, inpPrmPMsg.coaBrCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.coaChCd, inpPrmPMsg.coaChCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.coaCcCd, inpPrmPMsg.coaCcCd.getValue());

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        setUomCdFromEach(msgMap, invtyTrxTMsg);

        /* 12/21/2015 CSAI Y.Imazu Mod QC#793 START */
//        // Ship Cost Amount
//        if (!setShipCostAmt(msgMap, invtyTrxTMsg)) {
//            return false;
//        }
//
//        // Unit Cost Amount
//        if (!setUnitCostAmt(msgMap, invtyTrxTMsg)) {
//            return false;
//        }
        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }
        /* 12/21/2015 CSAI Y.Imazu Mod QC#793 END */

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }
    // QC#63527 2024/03/14 End

    /**
     * <pre>
     * Set data for "Expense/Drop Shipment Loan Stock-Out Expense/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setExpExpLoanDropShipStkO(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Set SO Number.
        invtyTrxTMsg.soNum.setValue(inpPrmPMsg.soNum.getValue());
        invtyTrxTMsg.soSlpNum.setValue(inpPrmPMsg.soSlpNum.getValue());

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code, UOM Code, Account Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true, true, false })) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Expense/Loan Stock-Out Expense Intangible with Cost/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setExpExpLoanStkOIntgWithCost(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Sell to, Bill to, Ship to from Shipping Order
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code, UOM Code, Account Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true, true, false })) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Rental".
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @param scenarioNo Scenario Number
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRntl(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg, int scenarioNo) {

        switch (scenarioNo) {

            // Rental/Rental Stock Out/OM/
            case RNTL_RNTL_STK_O_OM:
                printDebugLog("Rental/Rental Stock Out/OM/");
                if (!setRntlRntlStkOOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Rental/Drop Shipment Rental Stock Out/Inbound/
            case RNTL_DROP_SHIP_RNTL_STK_O_INBD:
                printDebugLog("Rental/Drop Shipment Rental Stock Out/Inbound/");
                if (!setRntlDropShipRntlStkO_Inbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Rental/Rental Stock Out Intangible with Cost/OM/
            case RNTL_RNTL_STK_O_INTG_WITH_COST_OM:
                printDebugLog("Rental/Rental Stock Out Intangible with Cost/OM/");
                if (!setRntlRntlStkOIntgWithCostOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Rental/Rental To Asset Return/Inbound
            case RNTL_RNTL_TO_AST_RTRN_INBD:
                printDebugLog("Rental/Rental To Asset Return/Inbound");
                if (!setRntlRntlToAstRtrnInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Rental/Rental Shipment Stock Out Expense/OM/
            case RNTL_RNTL_SHIP_STK_O_EXP_OM:
                printDebugLog("Rental/Rental Shipment Stock Out Expense/OM/");
                if (!setRntlRntlShipStkOExpOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Rental/Rental Shipment Stock Out Expense/OM/Intangible with cost/
            case RNTL_RNTL_SHIP_STK_O_EXP_OM_INTG_WITH_COST:
                printDebugLog("Rental/Rental Shipment Stock Out Expense/OM/Intangible with cost/");
                if (!setRntlRntlShipStkOExpOmIntgWithCost(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Rental/Rental Shipment Stock Out Expense/OM/Sell Then Buy
            case RNTL_RNTL_SHIP_STK_O_EXP_OM_SELL_THEN_BUY:
                printDebugLog("Rental/Rental Shipment Stock Out Expense/OM/Sell Then Buy");
                if (!setRntlRntlShipStkOExpOmSellThenBuy(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) START
                // Rental/Rental Return/Initial Supply
            case RNTL_RTRN_INIT_SUPPLY:
                printDebugLog("Rental/Rental Return/Initial Supply");
                if (!setSlsRtrnInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;
            //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) END
            // 2017/10/20 S21_NA#16347 Add Start
            case RNTL_SHIP_STK_OUT:
                printDebugLog("Rental Shipment Stock-Out");
                if (!setSlsCrAndRebilOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break; 

            case RNTL_SHIP_STK_OUT_EXP:
                printDebugLog("Rental Shipment Stock-Out Expense");
                if (!setSlsCrAndRebilOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break; 
            // 2017/10/20 S21_NA#16347 Add End
            // Unexpected
            default:
                addMsgId(msgMap, MSG_ID_NLZM0113E, "Scenario case");
                return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Rental/Rental Stock Out/OM/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRntlRntlStkOOm(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        // 2017/10/23 S21_NA#16347-2 Mod Start
//        if (!setSoNum(msgMap, invtyTrxTMsg)) {
//            return false;
//        }
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.soNum)) {
        	invtyTrxTMsg.soNum.setValue(inpPrmPMsg.soNum.getValue());
            invtyTrxTMsg.soSlpNum.setValue(inpPrmPMsg.soSlpNum.getValue());
        }
        // 2017/10/23 S21_NA#16347-2 Mod End

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, false, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Rental/Drop Shipment Rental Stock Out/Inbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRntlDropShipRntlStkO_Inbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, false, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Rental/Rental Stock Out Intangible with Cost/OM/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRntlRntlStkOIntgWithCostOm(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, false, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Rental/Rental To Asset Return/Inbound".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRntlRntlToAstRtrnInbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RMA Number
        if (!setRmaNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.rmaNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code (find DS CPO Return Detail)
        if (!findDsCpoRtrnDtl(msgMap, invtyTrxTMsg, new boolean[] {true})) {
            return false;
        }

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        setUomCdFromEach(msgMap, invtyTrxTMsg);

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Rental/Rental Shipment Stock Out Expense/OM/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRntlRntlShipStkOExpOm(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        // 2017/10/23 S21_NA#16347-2 Mod Start
//        if (!setSoNum(msgMap, invtyTrxTMsg)) {
//            return false;
//        }
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.soNum)) {
        	invtyTrxTMsg.soNum.setValue(inpPrmPMsg.soNum.getValue());
            invtyTrxTMsg.soSlpNum.setValue(inpPrmPMsg.soSlpNum.getValue());
        }
        // 2017/10/23 S21_NA#16347-2 Mod End

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code, COA Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, true, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Rental/Rental Shipment Stock Out Expense/OM/Intangible with cost/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRntlRntlShipStkOExpOmIntgWithCost(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code, COA Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, true, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Rental/Rental Shipment Stock Out Expense/OM/Sell Then Buy".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRntlRntlShipStkOExpOmSellThenBuy(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        // 2017/10/23 S21_NA#16347-2 Mod Start
//        if (!setSoNum(msgMap, invtyTrxTMsg)) {
//            return false;
//        }
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.soNum)) {
        	invtyTrxTMsg.soNum.setValue(inpPrmPMsg.soNum.getValue());
            invtyTrxTMsg.soSlpNum.setValue(inpPrmPMsg.soSlpNum.getValue());
        }
        // 2017/10/23 S21_NA#16347-2 Mod End

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, true, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Adjustment".
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @param scenarioNo Scenario Number
     * @return Result (true:Normal, false:Error)
     */
    private boolean setAdj(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg, int scenarioNo) {

        switch (scenarioNo) {

            // Adjustment/Adjustment/Inventory/
            case ADJ_ADJ_INVTY:
                printDebugLog("Adjustment/Adjustment/Inventory/");
                if (!setAdjAdjInvty(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Adjustment/Disposal/Outbound/
            case ADJ_DSPL_OTBD:
                printDebugLog("Adjustment/Disposal/Outbound/");
                if (!setAdjDsplOtbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Adjustment/Item Change Stock Out/Inbound/Item Change Screen
            case ADJ_ITM_CHNG_STK_O_INBD_ITM_CHNG_SCR:
                printDebugLog("Adjustment/Item Change Stock Out/Inbound/Item Change Screen");
                if (!setAdjItmChngStkOInbdItmChngScr(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Adjustment/Item Change Stock In/Inbound/Item Change Screen
            case ADJ_ITM_CHNG_STK_I_INBD_ITM_CHNG_SCR:
                printDebugLog("Adjustment/Item Change Stock In/Inbound/Item Change Screen");
                if (!setAdjItmChngStkIInbdItmChngScr(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Adjustment/Asset Disposal/Outbound/
            case ADJ_AST_DSPL_OTBD:
                printDebugLog("Adjustment/Asset Disposal/Outbound/");
                if (!setAdjAstDsplOtbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Adjustment/Cycle Count Adjustment/
            case ADJ_CYCLE_COUNT_ADJ:
                printDebugLog("Adjustment/Cycle Count Adjustment/");
                if (!setAdjCycleCountAdj(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Adjustment/PhysicalInventory Adjustment/
            case ADJ_PHYS_INVTY_ADJ:
                printDebugLog("Adjustment/PhysicalInventory Adjustment/");
                if (!setAdjPhysInvtyAdj(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Adjustment/Refurb Vendor Transfer Stock Out from Vendor/
            case ADJ_RFRB_VND_TRNSF_STK_O_FROM_VND:
                printDebugLog("Adjustment/Refurb Vendor Transfer Stock Out from Vendor/");
                if (!setAdjRfrbVndTrnsfStkOFromVnd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Adjustment/Refurb Expense Ship Out/
            case ADJ_RFRB_EXP_SHIP_O:
                printDebugLog("Adjustment/Refurb Expense Ship Out/");
                if (!setAdjRfrbExpShipO(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Adjustment/Buy Back Stock Out/
            case ADJ_BUY_BACK_STK_O:
                printDebugLog("Adjustment/Buy Back Stock Out/");
                if (!setAdjBuyBackStkO(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Adjustment/Kitting Item Change Stock Out/Inbound/Kitting
            case ADJ_KIT_ITM_CHNG_STK_O_INBD_KIT_AND_RFRB:
                printDebugLog("Adjustment/Kitting Item Change Stock Out/Inbound/Kitting");
                if (!setAdjKitItmChngStkOInbdKit(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Adjustment/Kitting Item Change Stock In/Inbound/Kitting
            case ADJ_KIT_ITM_CHNG_STK_I_INBD_KIT_AND_RFRB:
                printDebugLog("Adjustment/Kitting Item Change Stock In/Inbound/Kitting");
                if (!setAdjKitItmChngStkIInbdKit(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Adjustment/WH Transfer In-Transit Shortage Out/Inbound/Receiving Confirmation/
            case ADJ_WH_TRNSF_ITRNST_SHORT_OUT_INBD_RCV_CONF:
                printDebugLog("Adjustment/WH Transfer In-Transit Shortage Out/Inbound/Receiving Confirmation");
                if (!setAdjWhTrnsfItrstShortOutInbdRcvConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Adjustment/WH Transfer In-Transit Shortage Out/OM/Receiving Confirmation/
            case ADJ_WH_TRNSF_ITRNST_SHORT_OUT_OM_RCV_CONF:
                printDebugLog("Adjustment/WH Transfer In-Transit Shortage Out/OM/Receiving Confirmation");
                if (!setAdjWhTrnsfItrstShortOutOmRcvConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // MOD Start 03/19/2018 [QC#12110]
            case ADJ_REPLEN_TOOL_EXPENSE_OUT_PO:
                printDebugLog("Adjustment/Purchase Order Expense Out");
                if(!setReplenToolsExpenseOutForPo(msgMap, invtyTrxTMsg)){
                    return false;
                }
                break;

            case ADJ_REPLEN_TOOL_EXPENSE_OUT_WH_TRSF:
                printDebugLog("Adjustment/WH Transfer Expense Out");
                if(!setReplenToolsExpenseOutForWhTrsf(msgMap, invtyTrxTMsg)){
                    return false;
                }
                break;
            // MOD End 03/19/2018 [QC#12110]

            // Unexpected
            default:
                addMsgId(msgMap, MSG_ID_NLZM0113E, "Scenario case");
                return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Adjustment/Adjustment/Inventory/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setAdjAdjInvty(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Inventory Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.invtyOrdNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code
        if (!setCcyCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // QC#27665
        setSvcConfigMstrPkWithIO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Adjustment/Disposal/Outbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setAdjDsplOtbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // Inventory Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number and Transaction Line (Sub) Number 
        if (!setPrNumberWithInvtyOrder(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Vendor Code and Name
        if (!setVndNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Adjustment/Item Change Stock Out/Inbound/Item Change Screen".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setAdjItmChngStkOInbdItmChngScr(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Inventory Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.invtyOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Adjustment/Item Change Stock In/Inbound/Item Change Screen".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setAdjItmChngStkIInbdItmChngScr(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Inventory Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.invtyOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Adjustment/Asset Disposal/Outbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setAdjAstDsplOtbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        invtyTrxTMsg.soNum.setValue(inpPrmPMsg.soNum.getValue());
        invtyTrxTMsg.soSlpNum.setValue(inpPrmPMsg.soSlpNum.getValue());

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // Inventory Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number and Transaction Line (Sub) Number 
        if (!setPrNumberWithInvtyOrder(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Vendor Code and Name
        if (!setVndNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Adjustment/Cycle Count Adjustment/Inventory/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setAdjCycleCountAdj(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Inventory Order Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.invtyOrdNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code
        if (!setCcyCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // QC#27665
        setSvcConfigMstrPkWithIO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Adjustment/Physical Inventory Adjustment/Inventory/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setAdjPhysInvtyAdj(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Inventory Order Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.invtyOrdNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code
        if (!setCcyCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // QC#27665
        setSvcConfigMstrPkWithIO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Adjustment/Refurb Vendor Transfer Stock Out from Vendor/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setAdjRfrbVndTrnsfStkOFromVnd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poRcvNum, inpPrmPMsg.poRcvNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poRcvLineNum, inpPrmPMsg.poRcvLineNum.getValue());

        // RWS Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rwsNum, inpPrmPMsg.rwsNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rwsRefNum, inpPrmPMsg.rwsRefNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rwsDtlLineNum, inpPrmPMsg.rwsDtlLineNum.getValue());

        // SO Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.soNum, inpPrmPMsg.soNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.soSlpNum, inpPrmPMsg.soSlpNum.getValue());

        // PO Number
        if (!setPoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Inventory Order Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyOrdNum, inpPrmPMsg.invtyOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyOrdLineNum, inpPrmPMsg.invtyOrdLineNum.getValue());

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.poOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Ship to Customer
        if (!findShipToCust(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // Vendor
        if (!findVnd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Currency Code
        if (!setCcyCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Adjustment/Refurb Expense Ship Out/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setAdjRfrbExpShipO(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // Inventory Order Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number and Transaction Line (Sub) Number 
        if (!setPrNumberWithInvtyOrder(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Vendor
        if (!findVnd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.vndCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.vndNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Vendor Code and Name
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.vndCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.vndNm.getValue());

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Adjustment/Buy Back Stock Out/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setAdjBuyBackStkO(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // PO Number
        if (!setPoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.poOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Ship to Customer
        if (!findShipToCust(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        /* 06/03/2016 CSAI Y.Imazu Add QC#9498 START */
        // Vendor
        setPoVnd(msgMap, invtyTrxTMsg);
        /* 06/03/2016 CSAI Y.Imazu Add QC#9498 END */

        // Currency Code
        if (!setCcyCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Adjustment/Kitting Item Change Stock Out/Inbound/Kitting".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setAdjKitItmChngStkOInbdKit(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poRcvNum, inpPrmPMsg.poRcvNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poRcvLineNum, inpPrmPMsg.poRcvLineNum.getValue());

        // RWS Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rwsNum, inpPrmPMsg.rwsNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rwsRefNum, inpPrmPMsg.rwsRefNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rwsDtlLineNum, inpPrmPMsg.rwsDtlLineNum.getValue());

        // Work Number
        if (!setWrkOrdNum(msgMap, invtyTrxTMsg, false)) {
            return false;
        }
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.wrkOrdLineNum, inpPrmPMsg.wrkOrdLineNum.getValue());

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.wrkOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Adjustment/Kitting Item Change Stock In/Inbound/Kitting".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setAdjKitItmChngStkIInbdKit(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Work Number
        if (!setWrkOrdNum(msgMap, invtyTrxTMsg, false)) {
            return false;
        }
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.wrkOrdLineNum, inpPrmPMsg.wrkOrdLineNum.getValue());

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.wrkOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Adjustment/WH Transfer In-Transit Shortage Out/Inbound/Receiving Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setAdjWhTrnsfItrstShortOutInbdRcvConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Inventory Order Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number and Transaction Line (Sub) Number 
        if (!setPrNumberWithInvtyOrder(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());

        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Adjustment/WH Transfer In-Transit Shortage Out/OM/Receiving Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setAdjWhTrnsfItrstShortOutOmRcvConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());

        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // TOC Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * setReplenToolsExpenseOutForPo
     *   QC#12110 Add method.
     * @param msgMap S21ApiMessageMap
     * @param invtyTrxTMsg INVTY_TRXTMsg
     * @return true(success)/false(error)
     */
    private boolean setReplenToolsExpenseOutForPo(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg){
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        
        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // PO Number
        if (!setPoNum(msgMap, invtyTrxTMsg)){
            return false;
        }

        // Slip Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyTrxSlpNum, inpPrmPMsg.poOrdNum);

        // Reference Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyTrxRefNum, inpPrmPMsg.rwsRefNum);

        // Shipped from
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipFromLocCustCd, invtyTrxTMsg.invtyLocCd);
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipFromLocCustNm, invtyTrxTMsg.invtyLocNm);

        // Shipped to
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipToLocCustCd, invtyTrxTMsg.invtyLocCd);
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipToLocCustNm, invtyTrxTMsg.invtyLocNm);

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // TOC code
        if (!setTocCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // QC#25581 Convert Tech Toc Code => Org func asg toc cd. 
        if(!setOrgFuncTocCd(msgMap, invtyTrxTMsg)){
            return false;
        }

        // UOM Code
        invtyTrxTMsg.uomCd.setValue(PKG_UOM.EACH);

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * setReplenToolsExpenseOutForPo
     *   QC#12110 Add method.
     * @param msgMap S21ApiMessageMap
     * @param invtyTrxTMsg INVTY_TRXTMsg
     * @return true(success)/false(error)
     */
    private boolean setReplenToolsExpenseOutForWhTrsf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg){
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        
        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Inventory Request Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)){
            return false;
        }

        // Slip Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyTrxSlpNum, inpPrmPMsg.invtyTrxSlpNum);

        // Reference Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyTrxRefNum, inpPrmPMsg.rwsRefNum);

        // Shipped from
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipFromLocCustCd, invtyTrxTMsg.invtyLocCd);
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipFromLocCustNm, invtyTrxTMsg.invtyLocNm);

        // Shipped to
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipToLocCustCd, invtyTrxTMsg.invtyLocCd);
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipToLocCustNm, invtyTrxTMsg.invtyLocNm);

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // TOC code
        if (!setTocCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // QC#25581 Convert Tech Toc Code => Org func asg toc cd. 
        if(!setOrgFuncTocCd(msgMap, invtyTrxTMsg)){
            return false;
        }

        // UOM Code
        invtyTrxTMsg.uomCd.setValue(PKG_UOM.EACH);

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement".
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @param scenarioNo Scenario Number
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMove(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg, int scenarioNo) {

        switch (scenarioNo) {

            // Movement/In-Transit Stock In/Inbound/Domestic
            case MOVE_ITRNST_STK_I_INBD_DOM:
                printDebugLog("Movement/In-Transit Stock In/Inbound/Domestic");
                if (!setMoveItrnstStkIInbdDom(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/In-Transit Stock Out/Inbound/Domestic
            case MOVE_ITRNST_STK_O_INBD_DOM:
                printDebugLog("Movement/In-Transit Stock Out/Inbound/Domestic");
                if (!setMoveItrnstStkOInbdDom(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/WH Transfer Stock Out/Outbound/Ship Confirmation
            case MOVE_WH_TRNSF_STK_O_OTBD_SHIP_CONF:
                printDebugLog("Movement/WH Transfer Stock Out/Outbound/Ship Confirmation");
                if (!setMoveWhTrnsfStkOOtbdShipConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/WH Transfer Stock Out/OM/Ship Confirmation
            case MOVE_WH_TRNSF_STK_O_OM_SHIP_CONF:
                printDebugLog("Movement/WH Transfer Stock Out/OM/Ship Confirmation");
                if (!setMoveWhTrnsfStkOOmShipConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/WH Transfer In-Transit Stock In/Outbound/Ship Confirmation
            case MOVE_WH_TRNSF_ITRNST_STK_I_OTBD_SHIP_CONF:
                printDebugLog("Movement/WH Transfer In-Transit Stock In/Outbound/Ship Confirmation");
                if (!setMoveWhTrnsfItrnstStkIOtbdShipConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/WH Transfer In-Transit Stock In/OM/Ship Confirmation
            case MOVE_WH_TRNSF_ITRNST_STK_I_OM_SHIP_CONF:
                printDebugLog("Movement/WH Transfer In-Transit Stock In/OM/Ship Confirmation");
                if (!setMoveWhTrnsfItrnstStkIOmShipConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/WH Transfer In-Transit Stock Out/Inbound/Receive Confirmation
            case MOVE_WH_TRNSF_ITRNST_STK_O_INBD_RCV_CONF:
                printDebugLog("Movement/WH Transfer In-Transit Stock Out/Inbound/Receive Confirmation");
                if (!setMoveWhTrnsfItrnstStkOInbdRcvConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/WH Transfer In-Transit Stock Out/OM/Receive Confirmation
            case MOVE_WH_TRNSF_ITRNST_STK_O_OM_RCV_CONF:
                printDebugLog("Movement/WH Transfer In-Transit Stock Out/OM/Receive Confirmation");
                if (!setMoveWhTrnsfItrnstStkOOmRcvConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/WH Transfer Stock In/Inbound/Receive Confirmation
            case MOVE_WH_TRNSF_STK_I_INBD_RCV_CONF:
                printDebugLog("Movement/WH Transfer Stock In/Inbound/Receive Confirmation");
                if (!setMoveWhTrnsfStkIInbdRcvConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/WH Transfer Stock In/OM/Receive Confirmation
            case MOVE_WH_TRNSF_STK_I_OM_RCV_CONF:
                printDebugLog("Movement/WH Transfer Stock In/OM/Receive Confirmation");
                if (!setMoveWhTrnsfStkIOmRcvConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Asset WH Transfer Stock Out/OM/Ship Confirmation
            case MOVE_AST_WH_TRNSF_STK_O_OM_SHIP_CONF:
                printDebugLog("Movement/Asset WH Transfer Stock Out/OM/Ship Confirmation");
                if (!setMoveAstWhTrnsfStkOOmShipConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Asset WH Transfer In-Transit Stock In/OM/Ship Confirmation
            case MOVE_AST_WH_TRNSF_ITRNST_STK_I_OM_SHIP_CONF:
                printDebugLog("Movement/Asset WH Transfer In-Transit Stock In/OM/Ship Confirmation");
                if (!setMoveAstWhTrnsfItrnstStkIOmShipConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Asset WH Transfer In-Transit Stock Out/OM/Receive Confirmation
            case MOVE_AST_WH_TRNSF_ITRNST_STK_O_OM_RCV_CONF:
                printDebugLog("Movement/Asset WH Transfer In-Transit Stock Out/OM/Receive Confirmation");
                if (!setMoveAstWhTrnsfItrnstStkOOmRcvConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Asset WH Transfer In-Transit Shortage Out/OM/Receive Confirmation
            case MOVE_AST_WH_TRNSF_ITRNST_SHORT_O_OM_RCV_CONF:
                printDebugLog("Movement/Asset WH Transfer In-Transit Shortage Out/OM/Receive Confirmation");
                if (!setMoveAstWhTrnsfItrnstShortOOmRcvConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Asset WH Transfer Stock In/OM/Receive Confirmation
            case MOVE_AST_WH_TRNSF_STK_I_OM_RCV_CONF:
                printDebugLog("Movement/Asset WH Transfer Stock In/OM/Receive Confirmation");
                if (!setMoveAstWhTrnsfStkIOmRcvConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Off-the-Book WH Transfer Stock Out/OM/Ship Confirmation
            case MOVE_OFF_THE_BOOK_WH_TRNSF_STK_O_OM_SHIP_CONF:
                printDebugLog("Movement/Off-the-Book WH Transfer Stock Out/OM/Ship Confirmation");
                if (!setMoveOffTheBookWhTrnsfStkOOmShipConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Off-the-Book WH Transfer In-Transit Stock In/OM/Ship Confirmation
            case MOVE_OFF_THE_BOOK_WH_TRNSF_ITRNST_STK_I_OM_SHIP_CONF:
                printDebugLog("Movement/Off-the-Book WH Transfer In-Transit Stock In/OM/Ship Confirmation");
                if (!setMoveOffTheBookWhTrnsfItrnstStkIOmShipConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Off-the-Book WH Transfer In-Transit Stock Out/OM/Receive Confirmation
            case MOVE_OFF_THE_BOOK_WH_TRNSF_ITRNST_STK_O_OM_RCV_CONF:
                printDebugLog("Movement/Off-the-Book WH Transfer In-Transit Stock Out/OM/Receive Confirmation");
                if (!setMoveOffTheBookWhTrnsfItrnstStkOOmRcvConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Off-the-Book WH Transfer Stock In/OM/Receive Confirmation
            case MOVE_OFF_THE_BOOK_WH_TRNSF_STK_I_OM_RCV_CONF:
                printDebugLog("Movement/Off-the-Book WH Transfer Stock In/OM/Receive Confirmation");
                if (!setMoveOffTheBookWhTrnsfStkIOmRcvConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Off-the-Book WH Transfer In-Transit Shortage Out/OM/Receive Confirmation
            case MOVE_OFF_THE_BOOK_WH_TRNSF_ITRNST_SHORT_O_OM_RCV_CONF:
                printDebugLog("Movement/Off-the-Book WH Transfer In-Transit Shortage Out/OM/Receive Confirmation");
                if (!setMoveOffTheBookWhTrnsfItrnstShortOOmRcvConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Internal W/H Transfer Stock Out/Out-Bound/Ship Confirmation (44)
            case MOVE_INTERNAL_WH_TRNSF_STK_O_OTBD_SHIP_CONF:
                printDebugLog("Movement/Internal W/H Transfer Stock Out/Out-Bound/Ship Confirmation");
                if (!setMoveInternalWhTrnsfStkOOtbdShipConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Internal W/H Transfer Stock In/In-Bound/Receive Confirmation (45)
            case MOVE_INTERNAL_WH_TRNSF_STK_I_INBD_RCV_CONF:
                printDebugLog("Movement/Internal W/H Transfer Stock In/In-Bound/Receive Confirmation");
                if (!setMoveInternalWhTrnsfStkIInbdRcvConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Showroom Transfer Stock Out/OM/Ship Confirmation
            case MOVE_SHRM_TRNSF_STK_O_OM_SHIP_CONF:
                printDebugLog("Movement/Showroom Transfer Stock Out/OM/Ship Confirmation");
                if (!setMoveShrmTrnsfStkOOmShipConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Showroom Transfer In-Transit Stock In/OM/Ship Confirmation
            case MOVE_SHRM_TRNSF_ITRNST_STK_I_OM_SHIP_CONF:
                printDebugLog("Movement/Showroom Transfer In-Transit Stock In/OM/Ship Confirmation");
                if (!setMoveShrmTrnsfItrnstStkIOmShipConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Showroom Transfer In-Transit Stock Out/OM/Receive Confirmation
            case MOVE_SHRM_TRNSF_ITRNST_STK_O_OM_RCV_CONF:
                printDebugLog("Movement/Showroom Transfer In-Transit Stock Out/OM/Receive Confirmation");
                if (!setMoveShrmTrnsfItrnstStkOOmRcvConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Showroom Transfer Stock In/OM/Receive Confirmation
            case MOVE_SHRM_TRNSF_STK_I_OM_RCV_CONF:
                printDebugLog("Movement/Showroom Transfer Stock In/OM/Receive Confirmation");
                if (!setMoveShrmTrnsfStkIOmRcvConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Direct Sale Shipment Stock Out/Outbound/ROSS
            case MOVE_DRCT_SLS_SHIP_STK_O_OTBD_ROSS:
                printDebugLog("Movement/Direct Sale Shipment Stock Out/Outbound/ROSS");
                if (!setMoveDrctSlsShipStkOOtbdRoss(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Direct Sale Shipment Stock In/Outbound/ROSS
            case MOVE_DRCT_SLS_SHIP_STK_I_OTBD_ROSS:
                printDebugLog("Movement/Direct Sale Shipment Stock In/Outbound/ROSS");
                if (!setMoveDrctSlsShipStkIOtbdRoss(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Direct Sale Stock Out Intangible with Cost/ROSS/
            case MOVE_DRCT_SLS_STK_O_INTG_WITH_COST_ROSS:
                printDebugLog("Movement/Direct Sale Stock Out Intangible with Cost/ROSS/");
                if (!setMoveDrctSlsStkOIntgWithCostRoss(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Direct Sale Stock In Intangible with Cost/ROSS/
            case MOVE_DRCT_SLS_STK_I_INTG_WITH_COST_ROSS:
                printDebugLog("Movement/Direct Sale Stock In Intangible with Cost/ROSS/");
                if (!setMoveDrctSlsStkIIntgWithCostRoss(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Loan Shipment Stock Out/Outbound/Trial Ship
            case MOVE_LOAN_SHIP_STK_O_OTBD_TRL_SHIP:
                printDebugLog("Movement/Loan Shipment Stock Out/Outbound/Trial Ship");
                if (!setMoveLoanShipStkOOtbdTrlShip(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Loan Shipment Stock In/Outbound/Trial Ship
            case MOVE_LOAN_SHIP_STK_I_OTBD_TRL_SHIP:
                printDebugLog("Movement/Loan Shipment Stock In/Outbound/Trial Ship");
                if (!setMoveLoanShipStkIOtbdTrlShip(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // QC#63527 2024/03/14 Start
            // Movement/Off The Book Loan Shipment Stock Out/Outbound/Trial Ship
            case MOVE_OFF_THE_BOOK_LOAN_SHIP_STK_O_OTBD_TRL_SHIP:
                printDebugLog("Movement/Loan Shipment Stock Out/Outbound/Trial Ship");
                if (!setMoveOffTheBookLoanShipStkOOtbdTrlShip(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Off The Book Loan Shipment Stock In/Outbound/Trial Ship
            case MOVE_OFF_THE_BOOK_LOAN_SHIP_STK_I_OTBD_TRL_SHIP:
                printDebugLog("Movement/Loan Shipment Stock In/Outbound/Trial Ship");
                if (!setMoveOffTheBookLoanShipStkIOtbdTrlShip(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;
            // QC#63527 2024/03/14 End

            // Movement/Drop Shipment Loan Stock Out/Inbound/Trail Ship
            case MOVE_DROP_SHIP_LOAN_STK_O_INBD_TRL_SHIP:
                printDebugLog("Movement/Drop Shipment Loan Stock Out/Inbound/Trail Ship");
                if (!setMoveDropShipLoanStkOInbdTrlShip(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Drop Shipment Loan Stock In/Inbound/Trail Ship
            case MOVE_DROP_SHIP_LOAN_STK_I_INBD_TRL_SHIP:
                printDebugLog("Movement/Drop Shipment Loan Stock In/Inbound/Trail Ship");
                if (!setMoveDropShipLoanStkIInbdTrlShip(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Direct Sale Shipment Stock Out Asset/Outbound
            case MOVE_DRCT_SLS_SHIP_STK_O_AST_OTBD:
                printDebugLog("Movement/Direct Sale Shipment Stock Out Asset/Outbound");
                if (!setMoveDrctSlsShipStkOAstOtbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Direct Sale Shipment Stock In Asset/Outbound
            case MOVE_DRCT_SLS_SHIP_STK_I_AST_OTBD:
                printDebugLog("Movement/Direct Sale Shipment Stock In Asset/Outbound");
                if (!setMoveDrctSlsShipStkIAstOtbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Loan to Inventory Stock Out/Inbound/Trial to Inventory
            case MOVE_LOAN_INVTY_STK_O_INBD_TRL_INVTY:
                printDebugLog("Movement/Loan to Inventory Stock Out/Inbound/Trial to Inventory");
                if (!setMoveLoanInvtyStkOInbdTrlInvty(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Loan to Inventory Stock In/Inbound/Trial to Inventory
            case MOVE_LOAN_INVTY_STK_I_INBD_TRL_INVTY:
                printDebugLog("Movement/Loan to Inventory Stock In/Inbound/Trial to Inventory");
                if (!setMoveLoanInvtyStkIInbdTrlInvty(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // QC#63527 2024/03/14 Start
            // Movement/Off The Book Loan to Inventory Stock Out/Inbound/Trial to Inventory
            case MOVE_OFF_THE_BOOK_LOAN_INVTY_STK_O_INBD_TRL_INVTY:
                printDebugLog("Movement/Off The Book Loan to Inventory Stock Out/Inbound/Trial to Inventory");
                if (!setMoveOffTheBookLoanInvtyStkOInbdTrlInvty(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Off The Book Loan to Inventory Stock In/Inbound/Trial to Inventory
            case MOVE_OFF_THE_BOOK_LOAN_INVTY_STK_I_INBD_TRL_INVTY:
                printDebugLog("Movement/Off The Book Loan to Inventory Stock In/Inbound/Trial to Inventory");
                if (!setMoveOffTheBookLoanInvtyStkIInbdTrlInvty(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;
            // QC#63527 2024/03/14 End

            // Movement/Status Change Stock Out/Inbound/
            case MOVE_STS_CHNG_STK_O_INBD:
                printDebugLog("Movement/Status Change Stock Out/Inbound/");
                if (!setMoveStsChngStkOInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Status Change Stock In/Inbound/
            case MOVE_STS_CHNG_STK_I_INBD:
                printDebugLog("Movement/Status Change Stock in/Inbound/");
                if (!setMoveStsChngStkIInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Location Status Change Stock Out/Outbound/
            case MOVE_LOC_STS_CHNG_STK_O_OTBD_SHIP_CONF:
                printDebugLog("Movement/Location Status Change Stock Out/Outbound/");
                if (!setMoveLocStsChngStkOOtbdShipConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Location Status Change Stock In/Outbound/
            case MOVE_LOC_STS_CHNG_STK_I_OTBD_SHIP_CONF:
                printDebugLog("Movement/Location Status Change Stock In/Outbound/");
                if (!setMoveLocStsChngStkIOtbdShipConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Location Status Change Cancel Stock Out/Procurement/
            case MOVE_LOC_STS_CHNG_CANC_STK_O_PROCR:
                printDebugLog("Movement/Location Status Change Cancel Stock Out/Procurement/");
                if (!setMoveLocStsChngCancStkOProcr(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Location Status Change Cancel Stock In/Procurement/
            case MOVE_LOC_STS_CHNG_CANC_STK_I_PROCR:
                printDebugLog("Movement/Location Status Change Cancel Stock In/Procurement/");
                if (!setMoveLocStsChngCancStkIProcr(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Refurbish Vendor Transfer Stock Out/Outbound/Ship Confirmation/
            case MOVE_RFRB_VND_TRSF_STK_O_OTBD_SHIP_CONF:
                printDebugLog("Movement/Refurbish Vendor Transfer Stock Out/Outbound/Ship Confirmation/");
                if (!setRfrbVndTrsfStkOOtbdShipConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Refurbish Vendor Transfer Stock In/Outbound/Ship Confirmation/
            case MOVE_RFRB_VND_TRSF_STK_I_OTBD_SHIP_CONF:
                printDebugLog("Movement/Refurbish Vendor Transfer Stock In/Outbound/Ship Confirmation//");
                if (!setRfrbVndTrsfStkIOtbdShipConf(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Off-the-Book Stock-Out/OM/
            case MOVE_OFF_THE_BOOK_STK_O_OM:
                printDebugLog("Movement/Off-the-Book Stock-Out/OM/");
                if (!setMoveOffTheBookStkOOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Off-the-Book Stock-In/Inbound/
            case MOVE_OFF_THE_BOOK_STK_I_INBD:
                printDebugLog("Movement/Off-the-Book Stock-In/Inbound/");
                if (!setMoveOffTheBookStkIInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Off-the-Book Shipment Stock-Out/Outbound/
            case MOVE_OFF_THE_BOOK_SHIP_STK_O_OTBD:
                printDebugLog("Movement/Off-the-Book Shipment Stock-Out/Outbound/");
                if (!setMoveOffTheBookShipStkOOtbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Off-the-Book Shipment Stock-In/Outbound/
            case MOVE_OFF_THE_BOOK_SHIP_STK_I_OTBD:
                printDebugLog("Movement/Off-the-Book Shipment Stock-In/Outbound/");
                if (!setMoveOffTheBookShipStkIOtbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

                // Movement/Configuration Change Stock Out/Inbound/
            case MOVE_CONFIG_CHNG_STK_O_INBD:
                printDebugLog("Movement/Configuration Change Stock Out/Inbound/");
                if (!setMoveConfigChngStkOInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Movement/Configuration Change Stock In/Inbound/
            case MOVE_CONFIG_CHNG_STK_I_INBD:
                printDebugLog("Movement/Configuration Change Stock in/Inbound/");
                if (!setMoveConfigChngStkIInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;
                
             // Movement/Reman Parts Transfer Stock-Out/Outbound/
            case MOVE_REMAN_PARTS_TRSF_STK_OUT:
                printDebugLog("Movement/Reman Parts Transfer Stock-Out/Outbound/");
                if (!setMoveRemanPartsTrsfStkOut(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

             // Movement/Reman Parts Transfer Stock-In/Outbound/
            case MOVE_REMAN_PARTS_TRSF_STK_IN:
                printDebugLog("Reman Parts Transfer Stock-In/Outbound/");
                if (!setMoveRemanPartsTrsfStkIn(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

             // Movement/Reman Parts Transfer Back Stock-Out/Outbound/
            case MOVE_REMAN_PARTS_TRSF_BCK_STK_OUT:
                printDebugLog("Movement/Reman Parts Transfer Back Stock-Out/Outbound/");
                if (!setMoveRemanPartsTrsfBckStkOut(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

             // Movement/Reman Parts Transfer Back Stock-In/Outbound/
            case MOVE_REMAN_PARTS_TRSF_BCK_STK_IN:
                printDebugLog("Movement/Reman Parts Transfer Back Stock-In/Outbound/");
                if (!setMoveRemanPartsTrsfBckStkIn(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Unexpected
            default:
                addMsgId(msgMap, MSG_ID_NLZM0113E, "Scenario case");
                return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/In-Transit Stock In/Inbound/Domestic".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveItrnstStkIInbdDom(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        invtyTrxTMsg.rwsNum.setValue(inpPrmPMsg.rwsNum.getValue());
        invtyTrxTMsg.rwsRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());
        invtyTrxTMsg.rwsDtlLineNum.setValue(inpPrmPMsg.rwsDtlLineNum.getValue());

        // PO Nubmer
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poOrdNum, inpPrmPMsg.poOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poOrdDtlLineNum, inpPrmPMsg.poOrdDtlLineNum.getValue());

        // Slip Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyTrxSlpNum, inpPrmPMsg.poOrdNum.getValue());

        // Reference Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyTrxRefNum, inpPrmPMsg.invtyTrxSlpNum);

        // Vendor (find Vendor)
        if (!findVnd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.vndCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.vndNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code
        if (!setCcyCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/In-Transit Stock Out/In-Bound/Domestic".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveItrnstStkOInbdDom(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        invtyTrxTMsg.rwsNum.setValue(inpPrmPMsg.rwsNum.getValue());
        invtyTrxTMsg.rwsRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());
        invtyTrxTMsg.rwsDtlLineNum.setValue(inpPrmPMsg.rwsDtlLineNum.getValue());

        // PO Nubmer
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poOrdNum, inpPrmPMsg.poOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poOrdDtlLineNum, inpPrmPMsg.poOrdDtlLineNum.getValue());

        // Slip Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyTrxSlpNum, inpPrmPMsg.poOrdNum.getValue());

        // Reference Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyTrxRefNum, inpPrmPMsg.invtyTrxSlpNum);

        // Vendor (find Vendor)
        if (!findVnd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.vndCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.vndNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code
        if (!setCcyCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/WH Transfer Stock Out/Outbound/Ship Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveWhTrnsfStkOOtbdShipConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // Inventory Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number and Transaction Line (Sub) Number 
        if (!setPrNumberWithInvtyOrder(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Ship to (find Shipping Order Detail and Shipping Order Customer Detail)
        if (!findDsShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true})) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/WH Transfer Stock Out/OM/Ship Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveWhTrnsfStkOOmShipConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Ship to (find Shipping Order Detail and Shipping Order Customer Detail)
        if (!findDsShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true})) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/WH Transfer In-Transit Stock In/Outbound/Ship Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveWhTrnsfItrnstStkIOtbdShipConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // Inventory Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number and Transaction Line (Sub) Number 
        if (!setPrNumberWithInvtyOrder(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/WH Transfer In-Transit Stock In/OM/Ship Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveWhTrnsfItrnstStkIOmShipConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/WH Transfer In-Transit Stock Out/Inbound/Receive Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveWhTrnsfItrnstStkOInbdRcvConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Inventory Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number and Transaction Line (Sub) Number 
        if (!setPrNumberWithInvtyOrder(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/WH Transfer In-Transit Stock Out/OM/Receive Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveWhTrnsfItrnstStkOOmRcvConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/WH Transfer Stock In/Inbound/Receive Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveWhTrnsfStkIInbdRcvConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Inventory Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number and Transaction Line (Sub) Number 
        if (!setPrNumberWithInvtyOrder(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/WH Transfer Stock In/OM/Receive Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveWhTrnsfStkIOmRcvConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Asset WH Transfer Stock Out/OM/Ship Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveAstWhTrnsfStkOOmShipConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Ship to (find Shipping Order Detail and Shipping Order Customer Detail)
        if (!findDsShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true})) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Asset WH Transfer In-Transit Stock In/OM/Ship Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveAstWhTrnsfItrnstStkIOmShipConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Asset WH Transfer In-Transit Stock Out/OM/Receive Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveAstWhTrnsfItrnstStkOOmRcvConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Asset WH Transfer Stock In/OM/Receive Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveAstWhTrnsfStkIOmRcvConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Asset WH Transfer In-Transit Shortage Out/OM/Receive Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveAstWhTrnsfItrnstShortOOmRcvConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Off-the-Book WH Transfer Stock Out/OM/Ship Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveOffTheBookWhTrnsfStkOOmShipConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Ship to (find Shipping Order Detail and Shipping Order Customer Detail)
        if (!findDsShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true})) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Off-the-Book WH Transfer In-Transit Stock In/OM/Ship Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveOffTheBookWhTrnsfItrnstStkIOmShipConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Off-the-Book WH Transfer In-Transit Stock Out/OM/Receive Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveOffTheBookWhTrnsfItrnstStkOOmRcvConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Off-the-Book WH Transfer Stock In/OM/Receive Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveOffTheBookWhTrnsfStkIOmRcvConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Off-the-Book WH Transfer In-Transit Shortage Out/OM/Receive Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveOffTheBookWhTrnsfItrnstShortOOmRcvConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Internal W/H Transfer Stock Out/Out-Bound/Ship Confirmation".(44)
     * </pre>MOVE_INTERNAL_DC_TRNSF_STK_O_OTBD_SHIP_CONF
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveInternalWhTrnsfStkOOtbdShipConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.soNum, inpPrmPMsg.soNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.soSlpNum, inpPrmPMsg.soSlpNum.getValue());

        // BOL Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.bolNum, inpPrmPMsg.bolNum.getValue());

        // Pro Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.proNum, inpPrmPMsg.proNum.getValue());

        // Inventory Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.invtyTrxSlpNum)) {
            invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.invtyTrxSlpNum.getValue());

        } else {
            invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.invtyOrdNum.getValue());
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.invtyOrdNum.getValue());

        // Ship to Customer
        invtyTrxTMsg.shipToCustCd.setValue(inpPrmPMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(inpPrmPMsg.shipToCustNm.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(inpPrmPMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(inpPrmPMsg.shipToCustNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/WH Transfer Stock In/Inbound/Receive Confirmation".(45)
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveInternalWhTrnsfStkIInbdRcvConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rwsNum, inpPrmPMsg.rwsNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rwsRefNum, inpPrmPMsg.rwsRefNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rwsDtlLineNum, inpPrmPMsg.rwsDtlLineNum.getValue());

        // SO Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.soNum, inpPrmPMsg.soNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.soSlpNum, inpPrmPMsg.soSlpNum.getValue());

        // BOL Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.bolNum, inpPrmPMsg.bolNum.getValue());

        // Pro Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.proNum, inpPrmPMsg.proNum.getValue());

        // Inventory Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.invtyTrxSlpNum)) {
            invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.invtyTrxSlpNum.getValue());

        } else {
            invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.invtyOrdNum.getValue());
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.invtyOrdNum.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Showroom Transfer Stock Out/OM/Ship Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveShrmTrnsfStkOOmShipConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Ship to (find Shipping Order Detail and Shipping Order Customer Detail)
        if (!findDsShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true})) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Unit Price and Shipped Sales Amount
        if (!setShipSlsAmtUnitPrcAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipToCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Showroom Transfer In-Transit Stock In/OM/Ship Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveShrmTrnsfItrnstStkIOmShipConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Ship to (find Shipping Order Detail and Shipping Order Customer Detail)
        if (!findDsShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true})) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Unit Price and Shipped Sales Amount
        if (!setShipSlsAmtUnitPrcAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Showroom Transfer In-Transit Stock Out/OM/Receive Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveShrmTrnsfItrnstStkOOmRcvConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Unit Price and Shipped Sales Amount
        if (!setShipSlsAmtUnitPrcAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Showroom Transfer Stock In/OM/Receive Confirmation".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveShrmTrnsfStkIOmRcvConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code (find CPO Detal)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {false, true, false, false, false })) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Unit Price and Shipped Sales Amount
        if (!setShipSlsAmtUnitPrcAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Direct Sale Shipment Stock Out/Outbound/ROSS".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveDrctSlsShipStkOOtbdRoss(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code (find Shipping Order Detail and Shipping Plan)
        if (!findShpgOrdShpgPln(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Direct Sale Shipment Stock In/Outbound/ROSS".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveDrctSlsShipStkIOtbdRoss(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code (find Shipping Order Detail and Shipping Plan)
        if (!findShpgOrdShpgPln(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Direct Sale Stock Out Intangible with Cost/ROSS/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveDrctSlsStkOIntgWithCostRoss(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC code
        if (!setTocCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        setUomCdFromEach(msgMap, invtyTrxTMsg);

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Direct Sale Stock In Intangible with Cost/ROSS/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveDrctSlsStkIIntgWithCostRoss(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC code
        if (!setTocCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        setUomCdFromEach(msgMap, invtyTrxTMsg);

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Loan Shipment Stock Out/Outbound/Trial Ship".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveLoanShipStkOOtbdTrlShip(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, false, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Loan Shipment Stock In/Outbound/Trial Ship".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveLoanShipStkIOtbdTrlShip(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, false, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk. Mod QC#28594
        setSvcConfigMstrPkWithSOorCPO(msgMap, invtyTrxTMsg);

        return true;
    }

    // QC#63527 2024/03/14 Start
    /**
     * <pre>
     * Set data for "Movement/Off The Book Loan Shipment Stock Out/Outbound/Trial Ship".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveOffTheBookLoanShipStkOOtbdTrlShip(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, false, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);


        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Off The Book Loan Shipment Stock In/Outbound/Trial Ship".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveOffTheBookLoanShipStkIOtbdTrlShip(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, false, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk. Mod QC#28594
        setSvcConfigMstrPkWithSOorCPO(msgMap, invtyTrxTMsg);

        return true;
    }
    // QC#63527 2024/03/14 Start

    /**
     * <pre>
     * Set data for "Movement/Drop Shipment Loan Stock Out/Inbound/Trail Ship".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveDropShipLoanStkOInbdTrlShip(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, false, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Drop Shipment Loan Stock In/Inbound/Trail Ship".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveDropShipLoanStkIInbdTrlShip(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, false, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk. Mod QC#28594
        setSvcConfigMstrPkWithSOorCPO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Direct Sale Shipment Stock Out Asset/Outbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveDrctSlsShipStkOAstOtbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code (find Shipping Order Detail and Shipping Plan)
        if (!findShpgOrdShpgPln(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Direct Sale Shipment Stock In Asset/Outbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveDrctSlsShipStkIAstOtbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code (find Shipping Order Detail and Shipping Plan)
        if (!findShpgOrdShpgPln(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Loan to Inventory Stock Out/Inbound/Trial to Inventory".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveLoanInvtyStkOInbdTrlInvty(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RMA Number
        if (!setRmaNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Original CPO Number
        if (!setOrigCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.rmaNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // Shipped to (find Inventory Location View)
        invtyTrxTMsg.shipToLocCustCd.setValue(inpPrmPMsg.shipToLocCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(inpPrmPMsg.shipToLocCustNm.getValue());
        if (!findShippedToLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // TOC code
        if (!findDsCpoRtrnDtl(msgMap, invtyTrxTMsg, new boolean[] {true})) {
            return false;
        }

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        setUomCdFromEach(msgMap, invtyTrxTMsg);

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        /* 12/21/2015 CSAI Y.Imazu Mod QC#793 START */
//        // Shipped Cost Amount
//        if (!setShipCostAmt(msgMap, invtyTrxTMsg)) {
//            return false;
//        }
//
//        // Unit Cost Amount
//        if (!setUnitCostAmt(msgMap, invtyTrxTMsg)) {
//            return false;
//        }
        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }
        /* 12/21/2015 CSAI Y.Imazu Mod QC#793 END */

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);
        // QC#54864
        if (!ZYPCommonFunc.hasValue(invtyTrxTMsg.trialLoanMstrPk)) {
            setSvcConfigMstrPkWithRMA(msgMap, invtyTrxTMsg);
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Loan to Inventory Stock In/Inbound/Trial to Inventory".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveLoanInvtyStkIInbdTrlInvty(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RMA Number
        if (!setRmaNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Original CPO Number
        if (!setOrigCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.rmaNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // Shipped to (find Inventory Location View)
        invtyTrxTMsg.shipToLocCustCd.setValue(inpPrmPMsg.shipToLocCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(inpPrmPMsg.shipToLocCustNm.getValue());
        if (!findShippedToLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // TOC code
        if (!findDsCpoRtrnDtl(msgMap, invtyTrxTMsg, new boolean[] {true})) {
            return false;
        }

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        setUomCdFromEach(msgMap, invtyTrxTMsg);

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipToLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    // QC#63527 2024/03/14 Start
    /**
     * <pre>
     * Set data for "Movement/Off The Book Loan to Inventory Stock Out/Inbound/Trial to Inventory".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveOffTheBookLoanInvtyStkOInbdTrlInvty(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RMA Number
        if (!setRmaNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Original CPO Number
        if (!setOrigCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.rmaNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // Shipped to (find Inventory Location View)
        invtyTrxTMsg.shipToLocCustCd.setValue(inpPrmPMsg.shipToLocCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(inpPrmPMsg.shipToLocCustNm.getValue());
        if (!findShippedToLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // TOC code
        if (!findDsCpoRtrnDtl(msgMap, invtyTrxTMsg, new boolean[] {true})) {
            return false;
        }

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        setUomCdFromEach(msgMap, invtyTrxTMsg);

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        /* 12/21/2015 CSAI Y.Imazu Mod QC#793 START */
//        // Shipped Cost Amount
//        if (!setShipCostAmt(msgMap, invtyTrxTMsg)) {
//            return false;
//        }
//
//        // Unit Cost Amount
//        if (!setUnitCostAmt(msgMap, invtyTrxTMsg)) {
//            return false;
//        }
        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }
        /* 12/21/2015 CSAI Y.Imazu Mod QC#793 END */

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);
        // QC#54864
        if (!ZYPCommonFunc.hasValue(invtyTrxTMsg.trialLoanMstrPk)) {
            setSvcConfigMstrPkWithRMA(msgMap, invtyTrxTMsg);
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Off The Book Loan to Inventory Stock In/Inbound/Trial to Inventory".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveOffTheBookLoanInvtyStkIInbdTrlInvty(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RMA Number
        if (!setRmaNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Original CPO Number
        if (!setOrigCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.rmaNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // Shipped to (find Inventory Location View)
        invtyTrxTMsg.shipToLocCustCd.setValue(inpPrmPMsg.shipToLocCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(inpPrmPMsg.shipToLocCustNm.getValue());
        if (!findShippedToLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // TOC code
        if (!findDsCpoRtrnDtl(msgMap, invtyTrxTMsg, new boolean[] {true})) {
            return false;
        }

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        setUomCdFromEach(msgMap, invtyTrxTMsg);

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipToLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }
    // QC#63527 2024/03/14 End

    /**
     * <pre>
     * Set data for "Movement/Status Change Stock Out/Inbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveStsChngStkOInbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Inventory Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.invtyOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        // QC#23962
        if (!ZYPCommonFunc.hasValue(invtyTrxTMsg.trialLoanMstrPk)) {
            ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.trialLoanMstrPk, getPickSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg));
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Status Change Stock In/Inbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveStsChngStkIInbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Inventory Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.invtyOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Location Status Change Stock Out/Outbound/Ship Confirmation/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveLocStsChngStkOOtbdShipConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Work Order Number
        if (!setWrkOrdNum(msgMap, invtyTrxTMsg, true)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.wrkOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Location Status Change Stock In/Outbound/Ship Confirmation/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveLocStsChngStkIOtbdShipConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Work Order Number
        if (!setWrkOrdNum(msgMap, invtyTrxTMsg, true)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.wrkOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Location Status Change Cancel Stock Out/Procurement/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveLocStsChngCancStkOProcr(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Work Order Number
        if (!setWrkOrdNum(msgMap, invtyTrxTMsg, true)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.wrkOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Location Status Change Cancel Stock In/Procurement/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveLocStsChngCancStkIProcr(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Work Order Number
        if (!setWrkOrdNum(msgMap, invtyTrxTMsg, true)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.wrkOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Refurb Vendor Transfer Stock Out/Outbound/Ship Confirmation/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRfrbVndTrsfStkOOtbdShipConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // PO Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poOrdNum, inpPrmPMsg.poOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poOrdDtlLineNum, inpPrmPMsg.poOrdDtlLineNum.getValue());

        // Inventory Order Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number and Transaction Line (Sub) Number 
        if (!setPrNumberWithInvtyOrder(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Ship to
        if (!findShipToCust(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // Vendor Code
        if (!findVnd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Currency Code
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Refurb Vendor Transfer Stock In/Outbound/Ship Confirmation/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRfrbVndTrsfStkIOtbdShipConf(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // PO Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poOrdNum, inpPrmPMsg.poOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poOrdDtlLineNum, inpPrmPMsg.poOrdDtlLineNum.getValue());

        // Inventory Order Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number and Transaction Line (Sub) Number 
        if (!setPrNumberWithInvtyOrder(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Ship to
        if (!findShipToCust(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        /* 06/03/2016 CSAI Y.Imazu Mod QC#9498 START */
        // invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        // invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        setSoShipFromLoc(msgMap, invtyTrxTMsg);
        /* 06/03/2016 CSAI Y.Imazu Mod QC#9498 END */

        // Shipped to
        /* 06/03/2016 CSAI Y.Imazu Mod QC#9498 START */
        // invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        // invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        /* 06/03/2016 CSAI Y.Imazu Mod QC#9498 END */

        // Vendor Code
        if (!findVnd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Currency Code
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Off-the-Book Stock-Out/OM/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveOffTheBookStkOOm(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Ship to Customer
        if (!findShipToCust(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        setUomCdFromEach(msgMap, invtyTrxTMsg);

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Off-the-Book Stock-In/Inbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveOffTheBookStkIInbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // PO Receiving Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poRcvNum, inpPrmPMsg.poRcvNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poRcvLineNum, inpPrmPMsg.poRcvLineNum.getValue());

        // RWS Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rwsNum, inpPrmPMsg.rwsNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rwsRefNum, inpPrmPMsg.rwsRefNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rwsDtlLineNum, inpPrmPMsg.rwsDtlLineNum.getValue());

        // CPO Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.cpoOrdNum, inpPrmPMsg.cpoOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.cpoDtlLineNum, inpPrmPMsg.cpoDtlLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.cpoDtlLineSubNum, inpPrmPMsg.cpoDtlLineSubNum.getValue());

        // RMA Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rmaNum, inpPrmPMsg.rmaNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rmaLineNum, inpPrmPMsg.rmaLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rmaLineSubNum, inpPrmPMsg.rmaLineSubNum.getValue());

        // PO Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poOrdNum, inpPrmPMsg.poOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.poOrdDtlLineNum, inpPrmPMsg.poOrdDtlLineNum.getValue());

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Ship to
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipToCustCd, inpPrmPMsg.shipToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipToCustNm, inpPrmPMsg.shipToCustNm.getValue());

        // Shipped from
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipFromLocCustCd, invtyTrxTMsg.shipToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipFromLocCustNm, invtyTrxTMsg.shipToCustNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        setUomCdFromEach(msgMap, invtyTrxTMsg);

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Off-the-Book Shipment Stock-Out/Outbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveOffTheBookShipStkOOtbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Off-the-Book Shipment Stock-In/Outbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveOffTheBookShipStkIOtbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Status Change Stock Out/Inbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveConfigChngStkOInbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Inventory Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.invtyOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        INVTY_LOC_VTMsg tMsg = findInvtyLocV(msgMap, invtyTrxTMsg.invtyLocCd.getValue());
        if (tMsg != null) {
            invtyTrxTMsg.shipToCustNm.setValue(tMsg.invtyLocNm.getValue());
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Status Change Stock In/Inbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveConfigChngStkIInbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Inventory Number
        if (!setInvtyOrdNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.invtyOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        invtyTrxTMsg.shipToCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());
        

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Reman Parts Transfer Stock-Out/Outbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveRemanPartsTrsfStkOut(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number (Input Parametar: Reman Order#)
        if (!setSlipNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Reman Parts Transfer Stock-In/Outbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveRemanPartsTrsfStkIn(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        if(!setRwsNum(msgMap, invtyTrxTMsg)){
            return false;
        }

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number (Input Parametar: Reman Order#)
        if (!setSlipNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Reman Parts Transfer Back Stock-Out/Outbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveRemanPartsTrsfBckStkOut(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number (Input Parametar: Reman Order#)
        if (!setSlipNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Movement/Reman Parts Transfer Back Stock-In/Outbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setMoveRemanPartsTrsfBckStkIn(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        if(!setRwsNum(msgMap, invtyTrxTMsg)){
            return false;
        }

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number (Input Parametar: Reman Order#)
        if (!setSlipNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Parts Usage".
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @param scenarioNo Scenario Number
     * @return Result (true:Normal, false:Error)
     */
    private boolean setPartsUsg(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg, int scenarioNo) {

        switch (scenarioNo) {

            // Parts Usage/Parts Usage/Outbound/
            case PARTS_USG_PARTS_USG_OTBD:
            printDebugLog("Parts Usage/Parts Usage/Outbound/");
                if (!setPartsUsgPartsUsgOtbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Parts Usage/Parts Usage Return/Inbound/
            case PARTS_USG_PARTS_USG_RTRN_INBD:
            printDebugLog("Parts Usage/Parts Usage Return/Inbound/");
                if (!setPartsUsgPartsUsgRtrnInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Unexpected
            default:
                addMsgId(msgMap, MSG_ID_NLZM0113E, "Scenario case");
                return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Parts Usage/Parts Usage/Outbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setPartsUsgPartsUsgOtbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // BOL Number
        if (!setBolNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Pro Number
        if (!setProNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        if (!setSlipNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code
        if (!setTocCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        invtyTrxTMsg.uomCd.setValue(PKG_UOM.EACH);

        // COA Product Code
        if (!setCoaProdCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Parts Usage/Parts Usage Return/Inbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setPartsUsgPartsUsgRtrnInbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // BOL Number
        if (!setBolNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Pro Number
        if (!setProNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Num
        if (!setSlipNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code
        if (!setTocCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        invtyTrxTMsg.uomCd.setValue(PKG_UOM.EACH);

        // COA Product Code
        if (!setCoaProdCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Remanufacturing".
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @param scenarioNo Scenario Number
     * @return Result (true:Normal, false:Error)
     */
    private boolean setReman(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg, int scenarioNo) {

        switch (scenarioNo) {

            // Remanufacturing/Parts Usage for Reman/Outbound/
            case REMAN_PRT_USG_FOR_REMAN_OTBD:
            printDebugLog("Remanufacturing/Parts Usage for Reman/Outbound/");
                if (!setRemanPrtUsgForRemanOtbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Remanufacturing/Reman Item Change Stock Out/Outbound/
            case REMAN_REMAN_ITM_CHNG_STK_O_INBD:
            printDebugLog("Remanufacturing/Reman Item Change Stock Out/Outbound/");
                if (!setRemanRemanItmChngStkOInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Remanufacturing/Reman Item Change Stock In/Outbound/
            case REMAN_REMAN_ITM_CHNG_STK_I_INBD:
            printDebugLog("Remanufacturing/Reman Item Change Stock In/Outbound/");
                if (!setRemanRemanItmChngStkIInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Remanufacturing/Parts Usage return for Reman/Outbound/
            case REMAN_PRT_USG_RTRN_FOR_REMAN_INBD:
            printDebugLog("Remanufacturing/Parts Usage return for Reman/Outbound/");
                if (!setRemanPrtUsgRtrnForRemanInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Remanufacturing/Reman Item Change Stock Out for Accessary/Outbound/
            case REMAN_REMAN_ITM_CHNG_STK_O_INBD_ACSRY:
            printDebugLog("Remanufacturing/Reman Item Change Stock Out for Accessary/Outbound/");
                if (!setRemanRemanItmChngStkOOtbdForAccessary(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Remanufacturing/Reman Item Change Stock In for Accessary/Outbound/
            case REMAN_REMAN_ITM_CHNG_STK_I_INBD_ACSRY:
            printDebugLog("Remanufacturing/Reman Item Change Stock In for Accessary/Outbound/");
                if (!setRemanRemanItmChngStkIInbdForAccessary(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Unexpected
            default:
                addMsgId(msgMap, MSG_ID_NLZM0113E, "Scenario case");
                return false;
        }
        return true;
    }

    /**
     * <pre>
     * Set data for "Remanufacturing/Parts Usage for Reman/Outbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRemanPrtUsgForRemanOtbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number (Input Param: Reman Order#)
        if (!setSlipNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(invtyTrxTMsg.soNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        invtyTrxTMsg.uomCd.setValue(PKG_UOM.EACH);

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Remanufacturing/Reman Item Change Stock Out/Inbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRemanRemanItmChngStkOInbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number (Input Param: Reman Order#)
        if (!setSlipNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(invtyTrxTMsg.soNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Remanufacturing/Reman Item Change Stock In/Inbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRemanRemanItmChngStkIInbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Po Recieve Number
        if(!setPoRcvNum(msgMap, invtyTrxTMsg)){
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number (Input parameter: Reman Order #)
        if (!setSlipNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(invtyTrxTMsg.rwsRefNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Remanufacturing/Reman Item Change Stock Out for Accessary/Outbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRemanRemanItmChngStkOOtbdForAccessary(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number (Input parametar: Reman Order#)
        if (!setSlipNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(invtyTrxTMsg.soNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Remanufacturing/Reman Item Change Stock In for Accessary/Inbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRemanRemanItmChngStkIInbdForAccessary(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // PO Recive
        if(!setPoRcvNum(msgMap, invtyTrxTMsg)){
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number (Input parametar: Reman Order#)
        if (!setSlipNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(invtyTrxTMsg.rwsRefNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "Remanufacturing/Reman Item Change Stock Out/Inbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setRemanPrtUsgRtrnForRemanInbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // PO Recieve Number
        if (!setPoRcvNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        if (!setSlipNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(invtyTrxTMsg.rwsRefNum.getValue());

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        invtyTrxTMsg.uomCd.setValue(PKG_UOM.EACH);

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Inventory Valuation".
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @param scenarioNo Scenario Number
     * @return Result (true:Normal, false:Error)
     */
    private boolean setInvtyValue(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg, int scenarioNo) {

        switch (scenarioNo) {

            // Inventory Valuation/Sub WH Transfer Stock Out/Inbound/
            case INVTY_VALUE_SWH_TRNSF_STK_O_INBD:
            printDebugLog("Inventory Valuation/Sub WH Transfer Stock Out/Inbound/");
                if (!setInvtyValueSwhTrnsfStkOInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Inventory Valuation/Sub WH Transfer Stock In/Inbound/
            case INVTY_VALUE_SWH_TRNSF_STK_I_INBD:
            printDebugLog("Inventory Valuation/Sub WH Transfer Stock In/Inbound/");
                if (!setInvtyValueSwhTrnsfStkIInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // Unexpected
            default:
                addMsgId(msgMap, MSG_ID_NLZM0113E, "Scenario case");
                return false;
        }
        return true;
    }

    /**
     * <pre>
     * Set data for "Inventory Valuation/Sub WH Transfer Stock Out/Inbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setInvtyValueSwhTrnsfStkOInbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.soNum, inpPrmPMsg.soNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.soSlpNum, inpPrmPMsg.soSlpNum.getValue());

        // Inventory Order Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyOrdNum, inpPrmPMsg.invtyOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyOrdLineNum, inpPrmPMsg.invtyOrdLineNum.getValue());

        // Slip Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyTrxSlpNum, inpPrmPMsg.invtyOrdNum.getValue());

        // Reference Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyTrxRefNum, inpPrmPMsg.soNum.getValue());

        // Ship to Customer
        if (!findShipToCust(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        // QC#54864
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.origCpoOrdNum)) {
            setLoanReturnSWHChange(msgMap, invtyTrxTMsg);
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "Inventory Valuation/Sub WH Transfer Stock In/Inbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setInvtyValueSwhTrnsfStkIInbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rwsNum, inpPrmPMsg.rwsNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rwsRefNum, inpPrmPMsg.rwsRefNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.rwsDtlLineNum, inpPrmPMsg.rwsDtlLineNum.getValue());

        // SO Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.soNum, inpPrmPMsg.soNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.soSlpNum, inpPrmPMsg.soSlpNum.getValue());

        // Inventory Order Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyOrdNum, inpPrmPMsg.invtyOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyOrdLineNum, inpPrmPMsg.invtyOrdLineNum.getValue());

        // Slip Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyTrxSlpNum, inpPrmPMsg.invtyOrdNum.getValue());

        // Reference Number
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyTrxRefNum, inpPrmPMsg.rwsRefNum.getValue());

        // Ship to Customer
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.shipToCustCd)) {
            if (!findShipToCust(msgMap, invtyTrxTMsg)) {
                return false;
            }
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // Currency Code (find Global Company)
        if (!findGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        // QC#54864
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.origCpoOrdNum)) {
            setLoanReturnSWHChange(msgMap, invtyTrxTMsg);
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "EMSD Shipment".
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @param scenarioNo Scenario Number
     * @return Result (true:Normal, false:Error)
     */
    private boolean setEMSDShip(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg, int scenarioNo) {

        switch (scenarioNo) {

            // EMSD Shipment/EMSD Shipment Stock Out/OM/
            case EMSD_EMSD_SHIP_STK_O_OM:
            printDebugLog("EMSD Shipment/EMSD Shipment Stock Out/OM/");
                if (!setEmsdEmsdShipStkOOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // EMSD Shipment/Drop Shipment EMSD Stock Out/OM/
            case EMSD_DROP_SHIP_EMSD_STK_O_OM:
            printDebugLog("EMSD Shipment/Drop Shipment EMSD Stock Out/OM/");
                if (!setEmsdDropShipEmsdStkOOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // EMSD Shipment/EMSD Stock Out Intangible with Cost/OM/
            case EMSD_EMSD_SHIP_STK_O_INTG_WITH_COST_OM:
            printDebugLog("EMSD Shipment/EMSD Stock Out Intangible with Cost/OM/");
                if (!setEmsdEmsdShipStkOIntgWithCostOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // EMSD Shipment/EMSD To Asset Return/Inbound/
            case EMSD_EMSD_TO_AST_RTRN_INBD:
            printDebugLog("EMSD Shipment/EMSD To Asset Return/Inbound/");
                if (!setEmsdEmsdToAstRtrnInbd(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // EMSD Shipment/EMSD Shipment Stock-Out Expense/OM/
            case EMSD_EMSD_SHIP_STK_O_EXP_OM:
            printDebugLog("EMSD Shipment/EMSD Shipment Stock-Out Expense/OM/");
                if (!setEmsdEmsdShipStkOExpOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // EMSD Shipment/EMSD Shipment Stock-Out Expense/OM/Intangible with Cost/
            case EMSD_EMSD_SHIP_STK_O_EXP_OM_INTG_WITH_COST:
            printDebugLog("EMSD Shipment/EMSD Shipment Stock-Out Expense/OM/Intangible with Cost/");
                if (!setEmsdEmsdShipStkOExpOmIntgWithCost(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // EMSD Shipment/EMSD Shipment Stock-Out Expense/OM/Sell then Buy/
            case EMSD_EMSD_SHIP_STK_O_EXP_OM_SELL_THEN_BUY:
            printDebugLog("EMSD Shipment/EMSD Shipment Stock-Out Expense/OM/Sell then Buy/");
                if (!setEmsdEmsdShipStkOExpOmSellThenBuy(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            // EMSD Shipment/EMSD Shipment Stock-Out Asset/OM/
            case EMSD_EMSD_SHIP_STK_O_AST_OM:
            printDebugLog("EMSD Shipment/EMSD Shipment Stock-Out Asset/OM/");
                if (!setEmsdEmsdShipStkOAstOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break;

            //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) START
            //EMSD Shipment/Return/EMSD Initial Supply
            case EMSD_RTRN_INIT_SUPPLY:
                printDebugLog("EMSD Shipment/Return/EMSD Initial Supply");
                    if (!setSlsRtrnInbd(msgMap, invtyTrxTMsg)) {
                        return false;
                    }
                    break;
            //09/14/2017 CITS S.Endo Add Sol#373(QC#16346) END
            // 2017/10/20 S21_NA#16347 Add Start
            case EMSD_SHIP_STK_OUT:
                printDebugLog("EMSD Shipment Stock-Out");
                if (!setSlsCrAndRebilOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break; 

            case EMSD_SHIP_STK_OUT_EXP:
                printDebugLog("EMSD Shipment Stock-Out Expense");
                if (!setSlsCrAndRebilOm(msgMap, invtyTrxTMsg)) {
                    return false;
                }
                break; 
            // 2017/10/20 S21_NA#16347 Add End
            // Unexpected
            default:
                addMsgId(msgMap, MSG_ID_NLZM0113E, "Scenario case");
                return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "EMSD Shipment/EMSD Shipment Stock Out/OM/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setEmsdEmsdShipStkOOm(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        // 2017/10/23 S21_NA#16347-2 Mod Start
//        if (!setSoNum(msgMap, invtyTrxTMsg)) {
//            return false;
//        }
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.soNum)) {
        	invtyTrxTMsg.soNum.setValue(inpPrmPMsg.soNum.getValue());
            invtyTrxTMsg.soSlpNum.setValue(inpPrmPMsg.soSlpNum.getValue());
        }
        // 2017/10/23 S21_NA#16347-2 Mod End

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Invoice Number
        invtyTrxTMsg.invNum.setValue(inpPrmPMsg.invNum.getValue());  // QC#20840 add

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, false, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "EMSD Shipment/Drop Shipment EMSD Stock Out/OM/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setEmsdDropShipEmsdStkOOm(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, false, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "EMSD Shipment/EMSD Stock Out Intangible with Cost/OM/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setEmsdEmsdShipStkOIntgWithCostOm(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from (find Inventory Location View)
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, false, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Set data for "EMSD Shipment/EMSD To Asset Return/Inbound/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setEmsdEmsdToAstRtrnInbd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RMA Number
        if (!setRmaNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.rmaNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipToCustNm.getValue());

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.invtyLocCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.invtyLocNm.getValue());

        // TOC Code
        if (!findDsCpoRtrnDtl(msgMap, invtyTrxTMsg, new boolean[] {true})) {
            return false;
        }

        // Currency Code
        if (!setCcyCdFromGlblCmpy(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // UOM Code
        setUomCdFromEach(msgMap, invtyTrxTMsg);

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.invtyLocCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithRWS(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "EMSD Shipment/EMSD Shipment Stock-Out Expense/OM/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setEmsdEmsdShipStkOExpOm(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        // 2017/10/23 S21_NA#16347-2 Mod Start
//        if (!setSoNum(msgMap, invtyTrxTMsg)) {
//            return false;
//        }
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.soNum)) {
        	invtyTrxTMsg.soNum.setValue(inpPrmPMsg.soNum.getValue());
            invtyTrxTMsg.soSlpNum.setValue(inpPrmPMsg.soSlpNum.getValue());
        }
        // 2017/10/23 S21_NA#16347-2 Mod End

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code, COA Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, true, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }

    /**
     * <pre>
     * Set data for "EMSD Shipment/EMSD Shipment Stock-Out Expense/OM/Intangible with Cost/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setEmsdEmsdShipStkOExpOmIntgWithCost(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code, COA Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, true, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        return true;
    }


    /**
     * <pre>
     * Set data for "EMSD Shipment/EMSD Shipment Stock-Out Expense/OM/Sell then Buy/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setEmsdEmsdShipStkOExpOmSellThenBuy(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        // 2017/10/23 S21_NA#16347-2 Mod Start
//        if (!setSoNum(msgMap, invtyTrxTMsg)) {
//            return false;
//        }
        if (ZYPCommonFunc.hasValue(inpPrmPMsg.soNum)) {
        	invtyTrxTMsg.soNum.setValue(inpPrmPMsg.soNum.getValue());
            invtyTrxTMsg.soSlpNum.setValue(inpPrmPMsg.soSlpNum.getValue());
        }
        // 2017/10/23 S21_NA#16347-2 Mod End

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill, Ship to (find Shipping Order and Shipping Order Customer Detail)
        if (!findShpgOrdCustDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, true })) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code, COA Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, true, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }


    /**
     * <pre>
     * Set data for "EMSD Shipment/EMSD Shipment Stock-Out Asset/OM/".
     * </pre>
     * 
     * @param msgMap MessageMap
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setEmsdEmsdShipStkOAstOm(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // SO Number
        if (!setSoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // BOL Number
        invtyTrxTMsg.bolNum.setValue(inpPrmPMsg.bolNum.getValue());

        // Pro Number
        invtyTrxTMsg.proNum.setValue(inpPrmPMsg.proNum.getValue());

        // Carrier Code
        invtyTrxTMsg.carrCd.setValue(inpPrmPMsg.carrCd.getValue());

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.cpoOrdNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.soNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped from
        invtyTrxTMsg.shipFromLocCustCd.setValue(inpPrmPMsg.shipFromLocCustCd.getValue());
        invtyTrxTMsg.shipFromLocCustNm.setValue(inpPrmPMsg.shipFromLocCustNm.getValue());
        if (!findShippedFromLocCustNm(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Shipped to
        invtyTrxTMsg.shipToLocCustCd.setValue(invtyTrxTMsg.shipToCustCd.getValue());
        invtyTrxTMsg.shipToLocCustNm.setValue(invtyTrxTMsg.shipToCustNm.getValue());

        // TOC Code, CCY Code, COA Code (find CPO Detail)
        if (!findCpoDtl(msgMap, invtyTrxTMsg, new boolean[] {true, true, false, false, false })) {
            return false;
        }

        // UOM Code
        if (!setUomCd(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Unit Cost and Shipped Cost Amount
        if (!setShipCostAmtUnitCostAmt(msgMap, invtyTrxTMsg, invtyTrxTMsg.shipFromLocCustCd.getValue())) {
            return false;
        }

        // Service Config Master Pk
        setSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

        return true;
    }


    /**
     * <pre>
     * Update the Inventory Master.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTMsg Inventory Master TMsg
     * @param locTpCd Location status Code
     * @return Result (true:Normal, false:Error)
     */
    private boolean updateInvty(S21ApiMessageMap msgMap, INVTYTMsg invtyTMsg, String locTpCd, ONBATCH_TYPE onBatchType) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        BigDecimal rqstQty = inpPrmPMsg.xxRqstQty.getValue();

        // Create
        if (!ZYPCommonFunc.hasValue(invtyTMsg.glblCmpyCd)) {

            invtyTMsg = new INVTYTMsg();
            invtyTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
            invtyTMsg.mdseCd.setValue(inpPrmPMsg.mdseCd.getValue());
            invtyTMsg.invtyLocCd.setValue(inpPrmPMsg.invtyLocCd.getValue());
            invtyTMsg.locStsCd.setValue(inpPrmPMsg.locStsCd.getValue());
            invtyTMsg.stkStsCd.setValue(inpPrmPMsg.stkStsCd.getValue());
            invtyTMsg.locTpCd.setValue(locTpCd);
            invtyTMsg.invtyQty.setValue(rqstQty);
            invtyTMsg.invtyAllocQty.setValue(BigDecimal.ZERO);
            invtyTMsg.invtyAvalQty.setValue(rqstQty);
            S21ApiTBLAccessor.create(invtyTMsg);

            // error
            if (!RTNCD_NORMAL_END.equals(invtyTMsg.getReturnCode())) {
                addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Master " + invtyTMsg.getReturnCode());
                return false;
            }

        // Update
        } else {

            // Stock-In
            if (RQST_STOCK_IN.equals(inpPrmPMsg.xxRqstTpCd.getValue())) {

                // Inventory Qty = Inventory Qty + Request Qty
                invtyTMsg.invtyQty.setValue(invtyTMsg.invtyQty.getValue().add(rqstQty));

                // Available Qty = Available Qty + Request Qty
                invtyTMsg.invtyAvalQty.setValue(invtyTMsg.invtyAvalQty.getValue().add(rqstQty));

            // Stock-Out
            } else {

                setInvtyQtyForStkOut(invtyTMsg, msgMap, onBatchType);
            }
            S21ApiTBLAccessor.update(invtyTMsg);

            // error
            if (!RTNCD_NORMAL_END.equals(invtyTMsg.getReturnCode())) {
                addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Master");
                return false;
            }
        }
        if (isPurchaseStkInToGoodDcStk(inpPrmPMsg)) {
            BigDecimal allocQty = ZERO;
            INVTYTMsg inTrnTMsg = getInvtyInTrn(invtyTMsg);
            if (inTrnTMsg == null) {
                return true;
            }
            if (ZERO.compareTo(inTrnTMsg.invtyAllocQty.getValue()) >= 0) {
                return true;
            }
            if (rqstQty.compareTo(inTrnTMsg.invtyAllocQty.getValue()) <= 0) {
                allocQty = rqstQty;
            } else {
                allocQty = inTrnTMsg.invtyAllocQty.getValue();
            }
            NLZC001001PMsg nlzc0010PMsg = invtyAllocDCStk(msgMap, invtyTMsg, allocQty, onBatchType);
            // Error judgment by message ID
            boolean rslt = true;
            List<String> msgList = S21ApiUtil.getXxMsgIdList(nlzc0010PMsg);
            if (!msgList.isEmpty()) {
                for (String xxMsgId : msgList) {
                    addMsgId(msgMap, xxMsgId);
                    if (xxMsgId.endsWith("E")) {
                        rslt = false;
                    }
                }
            }
            if (rslt && allocQty.compareTo(inTrnTMsg.invtyAllocQty.getValue()) <= 0) {
                nlzc0010PMsg = invtyCancelAllocInTrn(msgMap, inTrnTMsg, allocQty, onBatchType);
                // Error judgment by message ID
                msgList = S21ApiUtil.getXxMsgIdList(nlzc0010PMsg);
                if (!msgList.isEmpty()) {
                    for (String xxMsgId : msgList) {
                        addMsgId(msgMap, xxMsgId);
                        if (xxMsgId.endsWith("E")) {
                            rslt = false;
                        }
                    }
                }
            }
            return rslt;
        }

        return true;
    }

    /**
     * @param invtyTMsg
     * @param inpPrmPMsg
     */
    private void setInvtyQtyForStkOut(INVTYTMsg invtyTMsg, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        BigDecimal rqstQty = inpPrmPMsg.xxRqstQty.getValue();
        // Inventory Qty = Inventory Qty - Request Qty
        invtyTMsg.invtyQty.setValue(invtyTMsg.invtyQty.getValue().subtract(rqstQty));
        // is Allocation
        if (ZYPConstant.FLG_ON_Y.equals(execAllocFlg)) {

            // Allocation Qty = Allocation Qty - Request Qty
            invtyTMsg.invtyAllocQty.setValue(invtyTMsg.invtyAllocQty.getValue().subtract(rqstQty));

            // is not Allocation
        } else {

            // Available Qty = Available Qty - Request Qty
            invtyTMsg.invtyAvalQty.setValue(invtyTMsg.invtyAvalQty.getValue().subtract(rqstQty));
        }

    }

    /**
     * @param inpPrmPMsg
     */
    private boolean isPurchaseStkInToGoodDcStk(NLZC002001PMsg inpPrmPMsg) {
        if (TRX.PURCHASE_STOCK_IN.equals(inpPrmPMsg.trxCd.getValue()) && //
                TRX_RSN.INBOUND_IN_TRANSIT_STOCK_IN.equals(inpPrmPMsg.trxRsnCd.getValue()) && //
                STK_STS.GOOD.equals(inpPrmPMsg.stkStsCd.getValue()) && //
                LOC_STS.DC_STOCK.equals(inpPrmPMsg.locStsCd.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * Move Allocation to DC-Stock
     * </pre>
     * 
     * @param invtyTMsg Inventory TMsg
     * @param rqstQty Request Q'ty
     * @return NLZC001001PMsg (after exec NLZC001001(Inventory Allocation API)
     */
    private NLZC001001PMsg invtyAllocDCStk(S21ApiMessageMap msgMap, INVTYTMsg invtyTMsg, BigDecimal rqstQty, ONBATCH_TYPE onBatchType) {

        NLZC001001PMsg invtyAllocPMsg = new NLZC001001PMsg();

        INVTYTMsg getRecord = getInvtyDCStk(invtyTMsg);
        if (!RTNCD_NORMAL_END.equals(getRecord.getReturnCode()) && //
                !RTNCD_DUPLICATE.equals(getRecord.getReturnCode())) {
            // db error
            addMsgId(msgMap, MSG_ID_NLZM0044E, "get Inventory Record");
            invtyAllocPMsg.xxMsgIdList.no(0).xxMsgId.setValue(MSG_ID_NLZM0044E);
            return invtyAllocPMsg;
        }

        invtyAllocPMsg.xxProcTpCd.setValue(PROC_TP_ALLOC);
        invtyAllocPMsg.glblCmpyCd.setValue(getRecord.glblCmpyCd.getValue());
        invtyAllocPMsg.mdseCd.setValue(getRecord.mdseCd.getValue());
        invtyAllocPMsg.invtyLocCd.setValue(getRecord.invtyLocCd.getValue());
        invtyAllocPMsg.locStsCd.setValue(getRecord.locStsCd.getValue());
        invtyAllocPMsg.stkStsCd.setValue(getRecord.stkStsCd.getValue());
        invtyAllocPMsg.xxRqstQty.setValue(rqstQty);
        invtyAllocPMsg.xxAllocOpt.setValue(OPT_CHK_FREE_OH);

        // Inventory Allocation API is executed
        NLZC001001 invtyAlloc = new NLZC001001();
        invtyAlloc.execute(invtyAllocPMsg, onBatchType);

        return invtyAllocPMsg;
    }

    /**
     * <pre>
     * get Inventory Record of DC-Stock
     * </pre>
     * 
     * @param invtyTMsg Inventory TMsg
     * @return INVTYTMsg get record
     */
    private INVTYTMsg getInvtyDCStk(INVTYTMsg invtyTMsg) {

        INVTYTMsg key = new INVTYTMsg();
        INVTYTMsg getRecord = new INVTYTMsg();

        key.glblCmpyCd.setValue(invtyTMsg.glblCmpyCd.getValue());
        key.mdseCd.setValue(invtyTMsg.mdseCd.getValue());
        key.invtyLocCd.setValue(invtyTMsg.invtyLocCd.getValue());
        key.locStsCd.setValue(LOC_STS.DC_STOCK);
        key.stkStsCd.setValue(invtyTMsg.stkStsCd.getValue());

        getRecord = (INVTYTMsg) EZDTBLAccessor.findByKey(key);
        if (getRecord == null) {
            key.locTpCd.setValue(invtyTMsg.locTpCd.getValue());
            key.invtyQty.setValue(ZERO);
            key.invtyAllocQty.setValue(ZERO);
            key.invtyAvalQty.setValue(ZERO);
            EZDTBLAccessor.insert(key);
            return key;
        }
        return getRecord;

    }

    /**
     * <pre>
     * get Inventory Record of DC-Stock
     * </pre>
     * 
     * @param invtyTMsg Inventory TMsg
     * @return INVTYTMsg get record
     */
    private INVTYTMsg getInvtyInTrn(INVTYTMsg invtyTMsg) {

        INVTYTMsg key = new INVTYTMsg();

        key.glblCmpyCd.setValue(invtyTMsg.glblCmpyCd.getValue());
        key.mdseCd.setValue(invtyTMsg.mdseCd.getValue());
        key.invtyLocCd.setValue(invtyTMsg.invtyLocCd.getValue());
        key.locStsCd.setValue(LOC_STS.IN_TRANSIT);
        key.stkStsCd.setValue(invtyTMsg.stkStsCd.getValue());

        return (INVTYTMsg) S21ApiTBLAccessor.findByKey(key);

    }

    /**
     * <pre>
     * Move Allocation to DC-Stock
     * </pre>
     * 
     * @param invtyTMsg Inventory TMsg
     * @param rqstQty Request Q'ty
     * @return NLZC001001PMsg (after exec NLZC001001(Inventory Allocation API)
     */
    private NLZC001001PMsg invtyCancelAllocInTrn(S21ApiMessageMap msgMap, INVTYTMsg invtyTMsg, BigDecimal rqstQty, ONBATCH_TYPE onBatchType) {

        NLZC001001PMsg invtyAllocPMsg = new NLZC001001PMsg();

        invtyAllocPMsg.xxProcTpCd.setValue(PROC_TP_CANC_ALLOC);
        invtyAllocPMsg.glblCmpyCd.setValue(invtyTMsg.glblCmpyCd.getValue());
        invtyAllocPMsg.mdseCd.setValue(invtyTMsg.mdseCd.getValue());
        invtyAllocPMsg.invtyLocCd.setValue(invtyTMsg.invtyLocCd.getValue());
        invtyAllocPMsg.locStsCd.setValue(invtyTMsg.locStsCd.getValue());
        invtyAllocPMsg.stkStsCd.setValue(invtyTMsg.stkStsCd.getValue());
        invtyAllocPMsg.xxRqstQty.setValue(rqstQty);

        // Inventory Allocation API is executed
        NLZC001001 invtyAlloc = new NLZC001001();
        invtyAlloc.execute(invtyAllocPMsg, onBatchType);

        return invtyAllocPMsg;
    }

    /**
     * <pre>
     * Insert the Inventory Transaction.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean insertInvtyTrx(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // Set Inventory Transaction PK to Oracle Sequence.
        invtyTrxTMsg.invtyTrxPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.INVTY_TRX_SQ));

        // Insert
        S21ApiTBLAccessor.create(invtyTrxTMsg);

        // error
        if (!RTNCD_NORMAL_END.equals(invtyTrxTMsg.getReturnCode())) {
            addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Transaction");
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Update the Inventory Detail Daily
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean updateInvtyDtlDly(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        // QC#28954 LOC_STS.LOAN -> LOC_STS.TRIAL_USE
        // Check Update Target
        if ((LOC_STS.DC_STOCK.equals(invtyTrxTMsg.locStsCd.getValue()) //
                || LOC_STS.TRIAL_USE.equals(invtyTrxTMsg.locStsCd.getValue()))//
                && ZYPConstant.FLG_ON_Y.equals(invtyTrxTMsg.invtyCtrlFlg.getValue())
                && !"DS".equals(invtyTrxTMsg.invtyLocCd.getValue())) {

            // Find Inventory Master
            NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

            // Find the Inventory Master.
            String glblCmpyCd = inpPrmPMsg.glblCmpyCd.getValue();
            String invtyTrxDt = inpPrmPMsg.invtyTrxDt.getValue();
            String locStsCd = inpPrmPMsg.locStsCd.getValue();
            String stkStsCd = inpPrmPMsg.stkStsCd.getValue();
            String rqstTpCd = inpPrmPMsg.xxRqstTpCd.getValue();
            String mdseCd = inpPrmPMsg.mdseCd.getValue();
            String invtyLocCd = inpPrmPMsg.invtyLocCd.getValue();
            BigDecimal rqstQty = inpPrmPMsg.xxRqstQty.getValue();
            String locTpCd = invtyTrxTMsg.locTpCd.getValue();
            BigDecimal svcConfigMstrPk = invtyTrxTMsg.trialLoanMstrPk.getValue();
            BigDecimal pickSvcConfigMstrPk = null;

            INVTYTMsg rInvtyTMsg = new INVTYTMsg();
            rInvtyTMsg.glblCmpyCd.setValue(glblCmpyCd);
            rInvtyTMsg.mdseCd.setValue(mdseCd);
            rInvtyTMsg.invtyLocCd.setValue(invtyLocCd);
            rInvtyTMsg.locStsCd.setValue(locStsCd);
            rInvtyTMsg.stkStsCd.setValue(stkStsCd);
            rInvtyTMsg = (INVTYTMsg) S21ApiTBLAccessor.findByKey(rInvtyTMsg);

            // TODO
            if (RQST_STOCK_OUT.equals(rqstTpCd)) {

                pickSvcConfigMstrPk = getPickSvcConfigMstrPkWithSO(msgMap, invtyTrxTMsg);

                if (ZYPCommonFunc.hasValue(pickSvcConfigMstrPk)) {
                    svcConfigMstrPk = pickSvcConfigMstrPk;
                }

                if (BigDecimal.ZERO.compareTo(rInvtyTMsg.invtyQty.getValue()) == 0) {

                    // Find delete target Inventoy Detail Daily Data
                    // START 2019/08/08 M.Naito [QC#52333, MOD]
//                    List<BigDecimal> invtyDtlDlyList = findInvtyDtlDlyAll(glblCmpyCd, mdseCd, invtyLocCd, locStsCd, stkStsCd, svcConfigMstrPk);
                    List<BigDecimal> invtyDtlDlyList = findInvtyDtlDlyAll(glblCmpyCd, mdseCd, invtyLocCd, locStsCd, stkStsCd, svcConfigMstrPk, null);

                    if (invtyDtlDlyList != null && invtyDtlDlyList.isEmpty() && inpPrmPMsg.serNumList.getValidCount() > 0) {
                        String serNum = inpPrmPMsg.serNumList.no(0).serNum.getValue();
                        invtyDtlDlyList = findInvtyDtlDlyAll(glblCmpyCd, mdseCd, invtyLocCd, locStsCd, stkStsCd, null, serNum);
                    }

                    if (invtyDtlDlyList != null && invtyDtlDlyList.isEmpty()) {
//                        invtyDtlDlyList = findInvtyDtlDlyAll(glblCmpyCd, mdseCd, invtyLocCd, locStsCd, stkStsCd, null);
                        invtyDtlDlyList = findInvtyDtlDlyAll(glblCmpyCd, mdseCd, invtyLocCd, locStsCd, stkStsCd, null, null);
                    }
                    // END 2019/08/08 M.Naito [QC#52333, MOD]

                    if (invtyDtlDlyList != null && !invtyDtlDlyList.isEmpty()) {

                        for (BigDecimal intyDtlDlyPk : invtyDtlDlyList) {

                            INVTY_DTL_DLYTMsg tMsg = new INVTY_DTL_DLYTMsg();
                            tMsg.glblCmpyCd.setValue(glblCmpyCd);
                            tMsg.invtyDtlDlyPk.setValue(intyDtlDlyPk);

                            tMsg = (INVTY_DTL_DLYTMsg) S21ApiTBLAccessor.findByKey(tMsg);

                            if (tMsg != null) {

                                S21ApiTBLAccessor.remove(tMsg);

                                if (!RTNCD_NORMAL_END.equals(tMsg.getReturnCode())) {
                                    addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Detail Daily " + tMsg.getReturnCode());
                                    return false;
                                }
                            }
                        }
                    }

                } else {

                    if (inpPrmPMsg.serNumList.getValidCount() > 0) {

                        List<String> serNumList = new ArrayList<String>(inpPrmPMsg.serNumList.getValidCount());
                        for (int i = 0; i < inpPrmPMsg.serNumList.getValidCount(); i++) {
                            serNumList.add(inpPrmPMsg.serNumList.no(i).serNum.getValue());
                        }

                        // Find delete target Inventoy Detail Daily
                        List<BigDecimal> invtyDtlDlyList = findInvtyDtlDly(glblCmpyCd, mdseCd, invtyLocCd, locStsCd, stkStsCd, null, serNumList, svcConfigMstrPk);

                        if (invtyDtlDlyList != null && invtyDtlDlyList.isEmpty()) {
                            invtyDtlDlyList = findInvtyDtlDlySer(glblCmpyCd, mdseCd, invtyLocCd, locStsCd, stkStsCd, null, serNumList, null);
                        }

                        if (invtyDtlDlyList != null && invtyDtlDlyList.isEmpty()) {
                            invtyDtlDlyList = findInvtyDtlDlySer(glblCmpyCd, mdseCd, invtyLocCd, locStsCd, stkStsCd, null, null, svcConfigMstrPk);
                        }

                        if (invtyDtlDlyList != null && !invtyDtlDlyList.isEmpty()) {

                            for (BigDecimal intyDtlDlyPk : invtyDtlDlyList) {

                                INVTY_DTL_DLYTMsg tMsg = new INVTY_DTL_DLYTMsg();
                                tMsg.glblCmpyCd.setValue(glblCmpyCd);
                                tMsg.invtyDtlDlyPk.setValue(intyDtlDlyPk);

                                tMsg = (INVTY_DTL_DLYTMsg) S21ApiTBLAccessor.findByKey(tMsg);

                                if (tMsg != null) {

                                    S21ApiTBLAccessor.remove(tMsg);

                                    if (!RTNCD_NORMAL_END.equals(tMsg.getReturnCode())) {
                                        addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Detail Daily " + tMsg.getReturnCode());
                                        return false;
                                    }

                                    rqstQty = rqstQty.subtract(tMsg.invtyQty.getValue());
                                }
                            }

                            if (serNumList.size() != invtyDtlDlyList.size() || BigDecimal.ZERO.compareTo(rqstQty) < 0) {

                                if (!lackSerialUpdate(msgMap, rqstQty) ) {
                                    return false;
                                }
                            }

                        } else {

                            if (!lackSerialUpdate(msgMap, rqstQty) ) {
                                return false;
                            }
                        }
                    
                    } else {

                        // Find delete target Inventoy Detail Daily
                        List<BigDecimal> invtyDtlDlyList = findInvtyDtlDly(glblCmpyCd, mdseCd, invtyLocCd, locStsCd, stkStsCd, null, null, svcConfigMstrPk);

                        if (invtyDtlDlyList != null && invtyDtlDlyList.isEmpty()) {
                            invtyDtlDlyList = findInvtyDtlDly(glblCmpyCd, mdseCd, invtyLocCd, locStsCd, stkStsCd, null, null, null);
                        }

                        if (invtyDtlDlyList != null && !invtyDtlDlyList.isEmpty()) {

                            for (BigDecimal intyDtlDlyPk : invtyDtlDlyList) {

                                INVTY_DTL_DLYTMsg tMsg = new INVTY_DTL_DLYTMsg();
                                tMsg.glblCmpyCd.setValue(glblCmpyCd);
                                tMsg.invtyDtlDlyPk.setValue(intyDtlDlyPk);

                                tMsg = (INVTY_DTL_DLYTMsg) S21ApiTBLAccessor.findByKey(tMsg);

                                if (tMsg != null) {

                                    if (BigDecimal.ZERO.compareTo(tMsg.invtyQty.getValue().subtract(rqstQty)) >= 0) {

                                        rqstQty = rqstQty.subtract(tMsg.invtyQty.getValue());
                                        S21ApiTBLAccessor.remove(tMsg);

                                    } else {

                                        tMsg.invtyQty.setValue(tMsg.invtyQty.getValue().subtract(rqstQty));
                                        S21ApiTBLAccessor.update(tMsg);

                                        rqstQty = rqstQty.subtract(rqstQty);
                                    }

                                    if (!RTNCD_NORMAL_END.equals(tMsg.getReturnCode())) {
                                        addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Detail Daily " + tMsg.getReturnCode());
                                        return false;
                                    }

                                    if (BigDecimal.ZERO.compareTo(rqstQty) >= 0) {
                                        break;
                                    }
                                }
                            }
                        }

                    }
                }

            } else if (RQST_STOCK_IN.equals(rqstTpCd)) {

                // Stock In Serial
                for (int i = 0; i < inpPrmPMsg.serNumList.getValidCount(); i++) {

                    List<String> serNumList = new ArrayList<String>(inpPrmPMsg.serNumList.getValidCount());
                    serNumList.add(inpPrmPMsg.serNumList.no(i).serNum.getValue());

                    // Find delete target Inventoy Detail Daily
                    List<BigDecimal> invtyDtlDlyList = findInvtyDtlDly(glblCmpyCd, mdseCd, null, null, null, null, serNumList, svcConfigMstrPk);

                    if (invtyDtlDlyList != null && invtyDtlDlyList.isEmpty()) {
                        invtyDtlDlyList = findInvtyDtlDly(glblCmpyCd, mdseCd, invtyLocCd, locStsCd, stkStsCd, null, serNumList, null);
                    }

                    if (invtyDtlDlyList != null && !invtyDtlDlyList.isEmpty()) {

                        for (BigDecimal intyDtlDlyPk : invtyDtlDlyList) {

                            INVTY_DTL_DLYTMsg tMsg = new INVTY_DTL_DLYTMsg();
                            tMsg.glblCmpyCd.setValue(glblCmpyCd);
                            tMsg.invtyDtlDlyPk.setValue(intyDtlDlyPk);

                            tMsg = (INVTY_DTL_DLYTMsg) S21ApiTBLAccessor.findByKey(tMsg);

                            if (tMsg != null) {

                                String adjDt = tMsg.stkInDt.getValue();
                                BigDecimal adjQty = tMsg.invtyQty.getValue();
                                String adjInvtyLocCd = tMsg.invtyLocCd.getValue();
                                String adjLocStsCd = tMsg.locStsCd.getValue();
                                String adjStkStsCd = tMsg.stkStsCd.getValue();
                                String adjLocTpCd = tMsg.locTpCd.getValue();

                                ZYPEZDItemValueSetter.setValue(tMsg.invtyLocCd, invtyLocCd);
                                ZYPEZDItemValueSetter.setValue(tMsg.locStsCd, locStsCd);
                                ZYPEZDItemValueSetter.setValue(tMsg.stkStsCd, stkStsCd);
                                ZYPEZDItemValueSetter.setValue(tMsg.invtyQty, BigDecimal.ONE);
                                ZYPEZDItemValueSetter.setValue(tMsg.locTpCd, locTpCd);
                                ZYPEZDItemValueSetter.setValue(tMsg.stkInDt, invtyTrxDt);
                                S21ApiTBLAccessor.update(tMsg);

                                if (!RTNCD_NORMAL_END.equals(tMsg.getReturnCode())) {
                                    addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Detail Daily " + tMsg.getReturnCode());
                                    return false;
                                }

                                invtyDtlDlyList = findInvtyDtlDly(glblCmpyCd, mdseCd, adjInvtyLocCd, adjLocStsCd, adjStkStsCd, adjDt, null, svcConfigMstrPk);

                                if (invtyDtlDlyList != null && invtyDtlDlyList.isEmpty()) {
                                    invtyDtlDlyList = findInvtyDtlDly(glblCmpyCd, mdseCd, invtyLocCd, locStsCd, stkStsCd, null, null, null);
                                }

                                if (invtyDtlDlyList != null && !invtyDtlDlyList.isEmpty()) {

                                    tMsg = new INVTY_DTL_DLYTMsg();
                                    tMsg.glblCmpyCd.setValue(glblCmpyCd);
                                    tMsg.invtyDtlDlyPk.setValue(invtyDtlDlyList.get(0));

                                    tMsg = (INVTY_DTL_DLYTMsg) S21ApiTBLAccessor.findByKey(tMsg);

                                    if (tMsg != null) {

                                        tMsg.invtyQty.setValue(tMsg.invtyQty.getValue().add(adjQty));
                                        S21ApiTBLAccessor.update(tMsg);


                                        if (!RTNCD_NORMAL_END.equals(tMsg.getReturnCode())) {
                                            addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Detail Daily " + tMsg.getReturnCode());
                                            return false;
                                        }
                                    } else {

                                        INVTY_DTL_DLYTMsg invtyDtlDlyTMsg = new INVTY_DTL_DLYTMsg();
                                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.glblCmpyCd, glblCmpyCd);
                                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.mdseCd, mdseCd);
                                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.invtyLocCd, adjInvtyLocCd);
                                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.locStsCd, adjLocStsCd);
                                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.stkStsCd, adjStkStsCd);
                                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.stkInDt, adjDt);
                                        invtyDtlDlyTMsg.invtyDtlDlyPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INVTY_DTL_DLY_SQ));
                                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.invtyQty, adjQty);
                                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.locTpCd, adjLocTpCd);
                                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.svcConfigMstrPk, svcConfigMstrPk);

                                        S21ApiTBLAccessor.insert(invtyDtlDlyTMsg);

                                        if (!RTNCD_NORMAL_END.equals(invtyDtlDlyTMsg.getReturnCode())) {
                                            addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Detail Daily " + invtyDtlDlyTMsg.getReturnCode());
                                            return false;
                                        }
                                    }

                                } else {

                                    INVTY_DTL_DLYTMsg invtyDtlDlyTMsg = new INVTY_DTL_DLYTMsg();
                                    ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.glblCmpyCd, glblCmpyCd);
                                    ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.mdseCd, mdseCd);
                                    ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.invtyLocCd, adjInvtyLocCd);
                                    ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.locStsCd, adjLocStsCd);
                                    ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.stkStsCd, adjStkStsCd);
                                    ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.stkInDt, adjDt);
                                    invtyDtlDlyTMsg.invtyDtlDlyPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INVTY_DTL_DLY_SQ));
                                    ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.invtyQty, adjQty);
                                    ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.locTpCd, adjLocTpCd);
                                    ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.svcConfigMstrPk, svcConfigMstrPk);

                                    S21ApiTBLAccessor.insert(invtyDtlDlyTMsg);

                                    if (!RTNCD_NORMAL_END.equals(invtyDtlDlyTMsg.getReturnCode())) {
                                        addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Detail Daily " + invtyDtlDlyTMsg.getReturnCode());
                                        return false;
                                    }
                                }

                                rqstQty = rqstQty.subtract(tMsg.invtyQty.getValue());

                            } else {

                                INVTY_DTL_DLYTMsg invtyDtlDlyTMsg = new INVTY_DTL_DLYTMsg();
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.glblCmpyCd, glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.mdseCd, mdseCd);
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.invtyLocCd, invtyLocCd);
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.locStsCd, locStsCd);
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.stkStsCd, stkStsCd);
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.stkInDt, invtyTrxDt);
                                invtyDtlDlyTMsg.invtyDtlDlyPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INVTY_DTL_DLY_SQ));
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.invtyQty, BigDecimal.ONE);
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.serNum, inpPrmPMsg.serNumList.no(i).serNum);
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.locTpCd, locTpCd);
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.svcConfigMstrPk, svcConfigMstrPk);

                                S21ApiTBLAccessor.insert(invtyDtlDlyTMsg);

                                if (!RTNCD_NORMAL_END.equals(invtyDtlDlyTMsg.getReturnCode())) {
                                    addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Detail Daily " + invtyDtlDlyTMsg.getReturnCode());
                                    return false;
                                }

                                rqstQty = rqstQty.subtract(invtyDtlDlyTMsg.invtyQty.getValue());
                            }
                        }

                    } else {

                        INVTY_DTL_DLYTMsg invtyDtlDlyTMsg = new INVTY_DTL_DLYTMsg();
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.mdseCd, mdseCd);
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.invtyLocCd, invtyLocCd);
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.locStsCd, locStsCd);
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.stkStsCd, stkStsCd);
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.stkInDt, invtyTrxDt);
                        invtyDtlDlyTMsg.invtyDtlDlyPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INVTY_DTL_DLY_SQ));
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.invtyQty, BigDecimal.ONE);
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.serNum, inpPrmPMsg.serNumList.no(i).serNum);
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.locTpCd, locTpCd);
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.svcConfigMstrPk, svcConfigMstrPk);

                        S21ApiTBLAccessor.insert(invtyDtlDlyTMsg);

                        if (!RTNCD_NORMAL_END.equals(invtyDtlDlyTMsg.getReturnCode())) {
                            addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Detail Daily " + invtyDtlDlyTMsg.getReturnCode());
                            return false;
                        }

                        rqstQty = rqstQty.subtract(invtyDtlDlyTMsg.invtyQty.getValue());
                    }

                }

                if (BigDecimal.ZERO.compareTo(rqstQty) < 0) {

                    // Find delete target Inventoy Detail Daily
                    List<BigDecimal> invtyDtlDlyList = findInvtyDtlDly(glblCmpyCd, mdseCd, invtyLocCd, locStsCd, stkStsCd, invtyTrxDt, null, svcConfigMstrPk);

                    if (invtyDtlDlyList != null && !invtyDtlDlyList.isEmpty()) {

                        for (BigDecimal intyDtlDlyPk : invtyDtlDlyList) {

                            INVTY_DTL_DLYTMsg invtyDtlDlyTMsg = new INVTY_DTL_DLYTMsg();
                            invtyDtlDlyTMsg.glblCmpyCd.setValue(glblCmpyCd);
                            invtyDtlDlyTMsg.invtyDtlDlyPk.setValue(intyDtlDlyPk);

                            invtyDtlDlyTMsg = (INVTY_DTL_DLYTMsg) S21ApiTBLAccessor.findByKey(invtyDtlDlyTMsg);

                            if (invtyDtlDlyTMsg != null) {

                                invtyDtlDlyTMsg.invtyQty.setValue(invtyDtlDlyTMsg.invtyQty.getValue().add(rqstQty));
                                S21ApiTBLAccessor.update(invtyDtlDlyTMsg);


                                if (!RTNCD_NORMAL_END.equals(invtyDtlDlyTMsg.getReturnCode())) {
                                    addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Detail Daily " + invtyDtlDlyTMsg.getReturnCode());
                                    return false;
                                }

                            } else {

                                invtyDtlDlyTMsg = new INVTY_DTL_DLYTMsg();
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.glblCmpyCd, glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.mdseCd, mdseCd);
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.invtyLocCd, invtyLocCd);
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.locStsCd, locStsCd);
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.stkStsCd, stkStsCd);
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.stkInDt, invtyTrxDt);
                                invtyDtlDlyTMsg.invtyDtlDlyPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INVTY_DTL_DLY_SQ));
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.invtyQty, rqstQty);
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.locTpCd, locTpCd);
                                ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.svcConfigMstrPk, svcConfigMstrPk);

                                S21ApiTBLAccessor.insert(invtyDtlDlyTMsg);

                                if (!RTNCD_NORMAL_END.equals(invtyDtlDlyTMsg.getReturnCode())) {
                                    addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Detail Daily " + invtyDtlDlyTMsg.getReturnCode());
                                    return false;
                                }
                            }
                        }
                    } else {

                        INVTY_DTL_DLYTMsg invtyDtlDlyTMsg = new INVTY_DTL_DLYTMsg();
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.mdseCd, mdseCd);
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.invtyLocCd, invtyLocCd);
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.locStsCd, locStsCd);
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.stkStsCd, stkStsCd);
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.stkInDt, invtyTrxDt);
                        invtyDtlDlyTMsg.invtyDtlDlyPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INVTY_DTL_DLY_SQ));
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.invtyQty, rqstQty);
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.locTpCd, locTpCd);
                        ZYPEZDItemValueSetter.setValue(invtyDtlDlyTMsg.svcConfigMstrPk, svcConfigMstrPk);

                        S21ApiTBLAccessor.insert(invtyDtlDlyTMsg);

                        if (!RTNCD_NORMAL_END.equals(invtyDtlDlyTMsg.getReturnCode())) {
                            addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Detail Daily " + invtyDtlDlyTMsg.getReturnCode());
                            return false;
                        }
                    }
                }

            }
        } 

        return true;
    }

    /**
     * find Inventory Detail Daily
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param invtyLocCd String
     * @param locStsCd String
     * @param stkStsCd String
     * @param invtyTrxDt String
     * @param serNumList List<String>
     * @param svcConfigMstrPk BigDecimal
     * @return List<BigDecimal>
     */
    private List<BigDecimal> findInvtyDtlDly(String glblCmpyCd, String mdseCd, String invtyLocCd, String locStsCd, String stkStsCd, String invtyTrxDt, List<String> serNumList, BigDecimal svcConfigMstrPk) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("mdseCd", mdseCd);
        if (invtyLocCd != null) {
            param.put("invtyLocCd", invtyLocCd);
        }
        if (locStsCd != null) {
            param.put("locStsCd", locStsCd);
        }
        if (stkStsCd != null) {
            param.put("stkStsCd", stkStsCd);
        }
        if (serNumList != null) {
            param.put("serNumList", serNumList);
        }
        if (invtyTrxDt != null) {
            param.put("invtyTrxDt", invtyTrxDt);
        }
        if (svcConfigMstrPk != null) {
            param.put("svcConfigMstrPk", svcConfigMstrPk);
        }

        List<BigDecimal> invtyDtlDlyList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getInvtyDtailDaily", param);

        return invtyDtlDlyList;
    }

    /**
     * find Inventory Detail Daily
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param invtyLocCd String
     * @param locStsCd String
     * @param stkStsCd String
     * @param invtyTrxDt String
     * @param serNumList List<String>
     * @param svcConfigMstrPk BigDecimal
     * @return List<BigDecimal>
     */
    private List<BigDecimal> findInvtyDtlDlySer(String glblCmpyCd, String mdseCd, String invtyLocCd, String locStsCd, String stkStsCd, String invtyTrxDt, List<String> serNumList, BigDecimal svcConfigMstrPk) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("mdseCd", mdseCd);
        if (invtyLocCd != null) {
            param.put("invtyLocCd", invtyLocCd);
        }
        if (locStsCd != null) {
            param.put("locStsCd", locStsCd);
        }
        if (stkStsCd != null) {
            param.put("stkStsCd", stkStsCd);
        }
        if (serNumList != null) {
            param.put("serNumList", serNumList);
        }
        if (invtyTrxDt != null) {
            param.put("invtyTrxDt", invtyTrxDt);
        }
        if (svcConfigMstrPk != null) {
            param.put("svcConfigMstrPk", svcConfigMstrPk);
        }

        List<BigDecimal> invtyDtlDlyList = (List<BigDecimal>) ssmBatchClient.queryObjectList("findInvtyDtlDlySer", param);

        return invtyDtlDlyList;
    }

    /**
     * find Inventory Detail Daily All Target
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param invtyLocCd String
     * @param locStsCd String
     * @param stkStsCd String
     * @param svcConfigMstrPk BigDecimal
     * @param serNum String
     * @return List<BigDecimal>
     */
    // START 2019/08/08 M.Naito [QC#52333, MOD]
//    private List<BigDecimal> findInvtyDtlDlyAll(String glblCmpyCd, String mdseCd, String invtyLocCd, String locStsCd, String stkStsCd, BigDecimal svcConfigMstrPk) {
    private List<BigDecimal> findInvtyDtlDlyAll(String glblCmpyCd, String mdseCd, String invtyLocCd, String locStsCd, String stkStsCd, BigDecimal svcConfigMstrPk, String serNum) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("mdseCd", mdseCd);
        if (invtyLocCd != null) {
            param.put("invtyLocCd", invtyLocCd);
        }
        if (locStsCd != null) {
            param.put("locStsCd", locStsCd);
        }
        if (stkStsCd != null) {
            param.put("stkStsCd", stkStsCd);
        }
        // QC#23802
        if (svcConfigMstrPk != null) {
            param.put("svcConfigMstrPk", svcConfigMstrPk);
        }
        if (serNum != null) {
            param.put("serNum", serNum);
        }

        List<BigDecimal> invtyDtlDlyList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getInvtyDtailDailyAll", param);

        return invtyDtlDlyList;
    }
    // END 2019/08/08 M.Naito [QC#52333, MOD]

    /**
     * lack Serial Update
     * @param msgMap S21ApiMessageMap
     * @param rqstQty BigDecimal
     * @return boolean
     */
    private boolean lackSerialUpdate(S21ApiMessageMap msgMap, BigDecimal rqstQty) {

        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        // Lack Serial Data
        List<BigDecimal> lackTarget = findInvtyDtlDly(inpPrmPMsg.glblCmpyCd.getValue(), inpPrmPMsg.mdseCd.getValue(), inpPrmPMsg.invtyLocCd.getValue(), inpPrmPMsg.locStsCd.getValue(), inpPrmPMsg.stkStsCd.getValue(), null, null, null);

        for (BigDecimal intyDtlDlyPk : lackTarget) {

            INVTY_DTL_DLYTMsg tMsg = new INVTY_DTL_DLYTMsg();
            tMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
            tMsg.invtyDtlDlyPk.setValue(intyDtlDlyPk);

            tMsg = (INVTY_DTL_DLYTMsg) S21ApiTBLAccessor.findByKey(tMsg);

            if (tMsg != null) {

                if (BigDecimal.ZERO.compareTo(tMsg.invtyQty.getValue().subtract(rqstQty)) >= 0) {

                    rqstQty = rqstQty.subtract(tMsg.invtyQty.getValue());
                    S21ApiTBLAccessor.remove(tMsg);

                } else {

                    tMsg.invtyQty.setValue(tMsg.invtyQty.getValue().subtract(rqstQty));
                    S21ApiTBLAccessor.update(tMsg);

                    rqstQty = rqstQty.subtract(rqstQty);
                }

                if (!RTNCD_NORMAL_END.equals(tMsg.getReturnCode())) {
                    addMsgId(msgMap, MSG_ID_NLZM0044E, "Inventory Detail Daily " + tMsg.getReturnCode());
                    return false;
                }

                if (BigDecimal.ZERO.compareTo(rqstQty) >= 0) {
                    break;
                }
            };
        }

        return true;
        
    }


    /**
     * <pre>
     * set the Vendor Name.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setVndNm(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", inpPrmPMsg.glblCmpyCd.getValue());
        param.put("invtyOrdNum", inpPrmPMsg.invtyOrdNum.getValue());
        param.put("invtyOrdLineNum", inpPrmPMsg.invtyOrdLineNum.getValue());

        Map<String, String> vndMap = (Map<String, String>) ssmBatchClient.queryObject("findVndNmWithPr", param);

        if (vndMap != null) {

            ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.vndCd, vndMap.get("VND_CD"));
            ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.vndNm, vndMap.get("LOC_NM"));
            ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipToCustCd, invtyTrxTMsg.vndCd);
            ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipToCustNm, invtyTrxTMsg.vndNm);
            ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipToLocCustCd, invtyTrxTMsg.vndCd);
            ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.shipToLocCustNm, invtyTrxTMsg.vndNm);
        }

        return true;
    }

    /**
     * <pre>
     * Set service config master Pk With RWS
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setSvcConfigMstrPkWithRWS(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        mdseTMsg.glblCmpyCd.setValue(invtyTrxTMsg.glblCmpyCd.getValue());
        mdseTMsg.mdseCd.setValue(invtyTrxTMsg.mdseCd.getValue());

        mdseTMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {
            return true;
        }

        if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.instlBaseCtrlFlg.getValue())) {

            RWS_HDRTMsg rwsTMsg = new RWS_HDRTMsg();
            rwsTMsg.glblCmpyCd.setValue(invtyTrxTMsg.glblCmpyCd.getValue());
            rwsTMsg.rwsNum.setValue(invtyTrxTMsg.rwsNum.getValue());

            rwsTMsg = (RWS_HDRTMsg) S21ApiTBLAccessor.findByKey(rwsTMsg);

            if (rwsTMsg == null) {
                return true;
            }

            ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.trialLoanMstrPk, rwsTMsg.svcConfigMstrPk);
        }

        return true;
    }

    /**
     * <pre>
     * Set service config master Pk With SO
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setSvcConfigMstrPkWithSO(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        mdseTMsg.glblCmpyCd.setValue(invtyTrxTMsg.glblCmpyCd.getValue());
        mdseTMsg.mdseCd.setValue(invtyTrxTMsg.mdseCd.getValue());

        mdseTMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {
            return true;
        }

        if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.instlBaseCtrlFlg.getValue())) {

            SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
            shpgOrdTMsg.glblCmpyCd.setValue(invtyTrxTMsg.glblCmpyCd.getValue());
            shpgOrdTMsg.soNum.setValue(invtyTrxTMsg.soNum.getValue());

            shpgOrdTMsg = (SHPG_ORDTMsg) S21ApiTBLAccessor.findByKey(shpgOrdTMsg);

            if (shpgOrdTMsg == null) {
                return true;
            }

            ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.trialLoanMstrPk, shpgOrdTMsg.svcConfigMstrPk);
        }

        return true;
    }

    /**
     * Add QC#28594
     * <pre>
     * Set service config master Pk With SO or CPO
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setSvcConfigMstrPkWithSOorCPO(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        mdseTMsg.glblCmpyCd.setValue(invtyTrxTMsg.glblCmpyCd.getValue());
        mdseTMsg.mdseCd.setValue(invtyTrxTMsg.mdseCd.getValue());

        mdseTMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {
            return true;
        }

        if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.instlBaseCtrlFlg.getValue())) {

            SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
            shpgOrdTMsg.glblCmpyCd.setValue(invtyTrxTMsg.glblCmpyCd.getValue());
            shpgOrdTMsg.soNum.setValue(invtyTrxTMsg.soNum.getValue());

            shpgOrdTMsg = (SHPG_ORDTMsg) S21ApiTBLAccessor.findByKey(shpgOrdTMsg);

            if (shpgOrdTMsg == null) {
                return true;
            }

            ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.trialLoanMstrPk, shpgOrdTMsg.svcConfigMstrPk);

            if (!ZYPCommonFunc.hasValue(invtyTrxTMsg.trialLoanMstrPk)) {

                // If an error occurs, add a message to the Message Map.
                NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
                String cpoOrdNum = inpPrmPMsg.cpoOrdNum.getValue();
                String cpoDtlLineNum = inpPrmPMsg.cpoDtlLineNum.getValue();
                String cpoDtlLineSubNum = inpPrmPMsg.cpoDtlLineSubNum.getValue();
                if (!ZYPCommonFunc.hasValue(cpoOrdNum)
                        || !ZYPCommonFunc.hasValue(cpoDtlLineNum)
                        || !ZYPCommonFunc.hasValue(cpoDtlLineSubNum)) {
                    addMsgId(msgMap, MSG_ID_NLZM0113E, "CPO#, CPO Line#, CPO Line Sub#");
                    return false;
                }

                // Find the CPO Detail.
                CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                cpoDtlTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
                cpoDtlTMsg.cpoOrdNum.setValue(cpoOrdNum);
                cpoDtlTMsg.cpoDtlLineNum.setValue(cpoDtlLineNum);
                cpoDtlTMsg.cpoDtlLineSubNum.setValue(cpoDtlLineSubNum);
                cpoDtlTMsg = (CPO_DTLTMsg) S21ApiTBLAccessor.findByKey(cpoDtlTMsg);

                // If an error occurs, add a message to the Message Map.
                if (cpoDtlTMsg == null) {
                    addMsgId(msgMap, MSG_ID_NLZM0036E);
                    return false;
                }
                if (!RTNCD_NORMAL_END.equals(cpoDtlTMsg.getReturnCode())) {
                    addMsgId(msgMap, MSG_ID_NLZM0044E, "CPO Detail " + cpoDtlTMsg.getReturnCode());
                    return false;
                }

                ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.trialLoanMstrPk, cpoDtlTMsg.pickSvcConfigMstrPk);

                if (!ZYPCommonFunc.hasValue(invtyTrxTMsg.trialLoanMstrPk)) {
                    ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.trialLoanMstrPk, cpoDtlTMsg.svcConfigMstrPk);
                }

            }
        }

        return true;
    }

    /**
     * <pre>
     * Set PR Number with Invty Order
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setPrNumberWithInvtyOrder(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        INVTY_ORD_DTLTMsg invtyOrdDtlTMsg = new INVTY_ORD_DTLTMsg();
        invtyOrdDtlTMsg.glblCmpyCd.setValue(invtyTrxTMsg.glblCmpyCd.getValue());
        invtyOrdDtlTMsg.invtyOrdNum.setValue(invtyTrxTMsg.invtyOrdNum.getValue());
        invtyOrdDtlTMsg.invtyOrdLineNum.setValue(invtyTrxTMsg.invtyOrdLineNum.getValue());

        invtyOrdDtlTMsg = (INVTY_ORD_DTLTMsg) S21ApiTBLAccessor.findByKey(invtyOrdDtlTMsg);

        if (invtyOrdDtlTMsg == null) {
            return true;
        }

        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyTrxSlpNum, invtyOrdDtlTMsg.prchReqNum);
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.trxLineNum, invtyOrdDtlTMsg.prchReqLineNum);
        if (ZYPCommonFunc.hasValue(invtyOrdDtlTMsg.prchReqLineSubNum)) {
            ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.trxLineSubNum, invtyOrdDtlTMsg.prchReqLineSubNum.getValue().toString());
        }

        return true;
    }

    /**
     * <pre>
     * Set message id and print debug log.
     * </pre>
     * 
     * @param msgMap Message Manager
     * @param inpPrmMsg EZD PMessage
     * @param msgId String setting value for Message ID
     */
    private void addMsgId(S21ApiMessageMap msgMap, String msgId) {
        addMsgId(msgMap, msgId, "none");
    }

    /**
     * <pre>
     * Set message id and print debug log.
     * </pre>
     * 
     * @param msgMap Message Manager
     * @param inpPrmMsg EZD PMessage
     * @param msgId String setting value for Message ID
     */
    private void addMsgId(S21ApiMessageMap msgMap, String msgId, String str) {
        msgMap.addXxMsgId(msgId);
        S21InfoLogOutput.println("setMsgId:" + msgId + " Description:" + str);
        S21InfoLogOutput.println(msgId, new String[] {str });
        S21InfoLogOutput.println(msgMap.getPmsg().toString());
    }

    /**
     * <pre>
     * Print debug log.
     * </pre>
     * 
     * @param debugMsg debug message
     */
    private void printDebugLog(String debugMsg) {
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, debugMsg, this);
        }
    }
    
    /**
     * <pre>
     * QC#23802 Add
     * Get Pick service config master Pk With SO
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * 
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return BigDecimal
     */
    private BigDecimal getPickSvcConfigMstrPkWithSO(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        BigDecimal pickSvcConfigMstrPk = null;

        MDSETMsg mdseTMsg = new MDSETMsg();
        mdseTMsg.glblCmpyCd.setValue(invtyTrxTMsg.glblCmpyCd.getValue());
        mdseTMsg.mdseCd.setValue(invtyTrxTMsg.mdseCd.getValue());

        mdseTMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {
            return null;
        }

        if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.instlBaseCtrlFlg.getValue())) {

            SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
            shpgOrdDtlTMsg.glblCmpyCd.setValue(invtyTrxTMsg.glblCmpyCd.getValue());
            shpgOrdDtlTMsg.soNum.setValue(invtyTrxTMsg.soNum.getValue());
            shpgOrdDtlTMsg.soSlpNum.setValue(invtyTrxTMsg.soSlpNum.getValue());

            shpgOrdDtlTMsg = (SHPG_ORD_DTLTMsg) S21ApiTBLAccessor.findByKey(shpgOrdDtlTMsg);

            if (shpgOrdDtlTMsg == null) {
                return null;
            }

            if (ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.pickSvcConfigMstrPk)) {
                pickSvcConfigMstrPk = shpgOrdDtlTMsg.pickSvcConfigMstrPk.getValue();
            }
        }

        return pickSvcConfigMstrPk;
    }
    
    
    private boolean setOrgFuncTocCd(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg){
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", inpPrmPMsg.glblCmpyCd.getValue());
        param.put("psnCd", inpPrmPMsg.tocCd.getValue());
        param.put("slsDt", inpPrmPMsg.invtyTrxDt.getValue());

        String orgFuncTocCd = (String) ssmBatchClient.queryObject("getTocCdForAsg", param);

        // Check result.
        if (!ZYPCommonFunc.hasValue(orgFuncTocCd)) {
            addMsgId(msgMap, MSG_ID_NFCM0549E, "TOC Code For Org");
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.tocCd, orgFuncTocCd);
            return true;
        }
    }

    /**
     * <pre>
     * Add QC#27665
     * Set service config master Pk With Inventory Order
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @param invtyTrxTMsg Inventory Transaction TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean setSvcConfigMstrPkWithIO(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        mdseTMsg.glblCmpyCd.setValue(invtyTrxTMsg.glblCmpyCd.getValue());
        mdseTMsg.mdseCd.setValue(invtyTrxTMsg.mdseCd.getValue());

        mdseTMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {
            return true;
        }

        if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.instlBaseCtrlFlg.getValue())) {

            INVTY_ORD_DTLTMsg invtyOrdDtlTMsg = new INVTY_ORD_DTLTMsg();
            invtyOrdDtlTMsg.glblCmpyCd.setValue(invtyTrxTMsg.glblCmpyCd.getValue());
            invtyOrdDtlTMsg.invtyOrdNum.setValue(invtyTrxTMsg.invtyOrdNum.getValue());
            invtyOrdDtlTMsg.invtyOrdLineNum.setValue(invtyTrxTMsg.invtyOrdLineNum.getValue());

            invtyOrdDtlTMsg = (INVTY_ORD_DTLTMsg) S21ApiTBLAccessor.findByKey(invtyOrdDtlTMsg);

            if (invtyOrdDtlTMsg == null) {
                return true;
            }

            ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.trialLoanMstrPk, invtyOrdDtlTMsg.svcConfigMstrPk);
        }

        return true;
    }

    // Add QC#54864
    private boolean setLoanReturnSWHChange(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {
        // QC#54864
        NLZC002001PMsg inpPrmPMsg = (NLZC002001PMsg) msgMap.getPmsg();
        // RWS Number
        if (!setRwsNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // CPO Number
        if (!setCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // RMA Number
        if (!setRmaNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Original CPO Number
        if (!setOrigCpoNum(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Slip Number
        invtyTrxTMsg.invtyTrxSlpNum.setValue(inpPrmPMsg.rmaNum.getValue());

        // Reference Number
        invtyTrxTMsg.invtyTrxRefNum.setValue(inpPrmPMsg.rwsRefNum.getValue());

        // Sell to, Bill to, Ship to
        if (!setSellBillShip(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // TOC code
        if (!findDsCpoRtrnDtl(msgMap, invtyTrxTMsg, new boolean[] {true })) {
            return false;
        }

        // Organization (find S21 Organization Master)
        if (!findS21Org(msgMap, invtyTrxTMsg)) {
            return false;
        }

        // Service Config Mstr Pk
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.trialLoanMstrPk, inpPrmPMsg.trialLoanMstrPk);
        
        return true;
    }

    private void setSvcConfigMstrPkWithRMA(S21ApiMessageMap msgMap, INVTY_TRXTMsg invtyTrxTMsg) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        mdseTMsg.glblCmpyCd.setValue(invtyTrxTMsg.glblCmpyCd.getValue());
        mdseTMsg.mdseCd.setValue(invtyTrxTMsg.mdseCd.getValue());

        mdseTMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {
            return;
        }

        if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.instlBaseCtrlFlg.getValue())) {

            DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.glblCmpyCd, invtyTrxTMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.cpoOrdNum, invtyTrxTMsg.rmaNum.getValue());
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum, invtyTrxTMsg.rmaLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum, invtyTrxTMsg.rmaLineSubNum.getValue());

            dsCpoRtrnDtlTMsg = (DS_CPO_RTRN_DTLTMsg) S21ApiTBLAccessor.findByKey(dsCpoRtrnDtlTMsg);

            if (dsCpoRtrnDtlTMsg == null) {
                return;
            }

            if (ZYPCommonFunc.hasValue(dsCpoRtrnDtlTMsg.svcConfigMstrPk)) {
                ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.trialLoanMstrPk, dsCpoRtrnDtlTMsg.svcConfigMstrPk);
            }
        }
    }
}
