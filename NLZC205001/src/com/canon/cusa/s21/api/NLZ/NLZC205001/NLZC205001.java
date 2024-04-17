package com.canon.cusa.s21.api.NLZ.NLZC205001;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AREA_LEAD_TMTMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CAL_RELNTMsg;
import business.db.CMPSNTMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.INVTY_ORDTMsg;
import business.db.MSG_TXT_DTLTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.RTL_WHTMsg;
import business.db.SCE_ORD_TPTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_BO_DTLTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_CUST_DTLTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SHPG_ORD_MSGTMsg;
import business.db.SHPG_ORD_SCHD_TRKTMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.TRNSP_LTTMsg;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.db.VND_RTRN_CMNTTMsg;
import business.parts.NLZC205001PMsg;
import business.parts.NLZC401001PMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NWZC003001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC401001.NLZC401001;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.common.NLX.NLXC041001.NLXC041001GetDisptSchdCoord;
import com.canon.cusa.s21.common.NLX.NLXC041001.NLXC041001SchdCoordInfo;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomer;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomerBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_SUB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CARR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DRCT_SHIP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_TO;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_COORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_ORD_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TXT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBigDecimalResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * SO API
 * Shipping Order Create from Shipping Plan.
 * </pre>
 * 
 * <pre>
 * Date        Company  Name         Create/Update                     Defect No
 * -----------------------------------------------------------------------------
 * 06/26/2009  Fujitsu  H.Nishiyama  Create                            N/A
 * 11/10/2009  Fujitsu  D.Fukaya     Update for Export, Vender Return  N/A
 * 12/29/2009  Fujitsu  H.Nishiyama  Update                            2913
 * 06/17/2010  CSAI     D.Fukaya     Update                            6897
 * 06/22/2010  CSAI     D.Fukaya     Update                            5015
 * 08/23/2010  CSAI     D.Fukaya     Update                            294
 * 09/29/2010  CSAI     D.Fukaya     Update                            SO Comment
 * 11/22/2010  CSAI     D.Fukaya     Update                            778(ASN flag for Export)
 * 12/06/2010  CSAI     M.Takahashi  Update                            745
 * 12/08/2010  CSAI     D.Fukaya     Update                            743
 * 01/26/2011  CSAI     M.Takahashi  Update                            1152
 * 11/02/2012  Fujitsu  J.Yasukawa   Update                            Oce WDS SOL#94
 * 12/06/2012  Fujitsu  Y.Taoka      Update                            Oce WDS SOL#94
 * 01/16/2013  Fujitsu  J.Yasukawa   Update                            Oce WDS Defects#195
 * 02/06/2013  Fujitsu  J.Yasukawa   Update                            Oce WDS Defects#121
 * 04/22/2013  Fujitsu  J.Yasukawa   Update                            Oce WDS R-WH003
 * 07/17/2013  Fujitsu  Y.Taoka      Update                            WDS K54-4649
 * 01/24/2014  Fujitsu  S.Yamamoto   Update                            WDS QC#3468
 * 10/23/2015  CITS     T.Tokutomi   Update                            CSA
 * 03/21/2016  CSAI     Y.Imazu      Update                            QC#5742
 * 04/26/2016  CSAI     Y.Imazu      Update                            QC#7623
 * 04/27/2016  CSAI     Y.Imazu      Update                            QC#6539
 * 06/14/2016  CITS     K.Masaki     Update                            QC#9860/9873
 * 07/15/2016  CSAI     Y.Imazu      Update                            QC#11067
 * 08/08/2016  CSAI     Y.Imazu      Update                            QC#13053
 * 12/08/2016  CITS     Y.IWASAKI    Update                            QC#16412
 * 03/13/2017  CITS     K.Ogino      Update                            DS table integration
 * 06/12/2017  CITS     Y.Imazu      Update                            QC#18991
 * 06/08/2017  CITS     R.Shimamoto  Update                            QC#18272
 * 07/03/2017  CITS     M.Naito      Update                            QC#19682
 * 07/26/2017  CITS     S.Endo       Update                            Sol#072(QC#18386)
 * 08/10/2017  CITS     S.Endo       Update                            Sol#035(QC#18108)
 * 09/07/2017  CITS     S.Katsuma    Update                            Sol#032(QC#13117)
 * 09/11/2017  CITS     S.Endo       Update                            Sol#069(QC#18270)
 * 10/17/2017  CITS     S.Katsuma    Update                            Sol#454
 * 10/19/2017  CITS     K.Ogino      Update                            QC#21912
 * 11/15/2017  CITS     K.Ogino      Update                            QC#22344
 * 11/21/2017  CITS     T.Wada       Update                            QC#22302
 * 12/05/2017  CITS     K.Ogino      Update                            QC#22178
 * 12/28/2017  CITS     T.Hakodate   Update                            QC#18460(SOL#087)
 * 01/23/2018  CITS     S.Katsuma    Update                            QC#23049
 * 02/01/2018  CITS     K.Ogino      Update                            QC#23784
 * 03/13/2018  CITS     K.Ogino      Update                            QC#18794
 * 03/29/2018  CITS     K.Ogino      Update                            QC#23559
 * 04/02/2018  CITS     S.Katsuma    Update                            QC#25143
 * 05/07/2018  CITS     S.Katsuma    Update                            QC#25389
 * 06/14/2018  CITS     T.Tokutomi   Update                            QC#24251
 * 06/26/2018  CITS     T.Tokutomi   Update                            QC#20154
 * 06/29/2018  CITS     T.Hakodate   Update                            QC#26526
 * 07/17/2018  CITS     T.Hakodate   Update                            QC#26863
 * 08/24/2018  CITS     K.Ogino      Update                            QC#27833
 * 11/15/2018  Fujitsu  M.Ohno       Update                            QC#27954
 * 11/20/2018  CITS     Y.Iwasaki    Update                            QC#29212
 * 03/15/2019  CITS     K.Ogino      Update                            QC#30792
 * 03/27/2019  CITS     K.Ogino      Update                            QC#30848
 * 04/10/2019  CITS     K.Ogino      Update                            QC#31042
 * 05/27/2019  CITS     K.Ogino      Update                            QC#50365
 * 07/27/2019  CITS     R.Kurahashi  Update                            QC#51804
 * 10/09/2019  CITS     M.Naito      Update                            QC#54026
 * 12/11/2019  CITS     T.Wada       Update                            QC#54989
 * 12/13/2019  Fujitsu  R.Nakamura   Update                            QC#55070
 * 01/28/2020  Fujitsu  R.Nakamura   Update                            QC#55628
 * 03/05/2020  CITS     K.Ogino      Update                            QC#55641
 * 04/05/2023  Hitachi  M.Nakajima   Update                            QC#61001
 * </pre>
 */
public class NLZC205001 extends S21ApiCommonBase {

    /** SCE_ORD_TP_CD: Regular */
    public static final String SCE_ORD_TP_CD_REGULAR = SCE_ORD_TP.REGULAR;

    /** SCE_ORD_TP_CD: Trial For Sales */
    public static final String SCE_ORD_TP_CD_TRIAL_FOR_SALES = SCE_ORD_TP.TRIAL_FOR_SALE;

    /** SCE_ORD_TP_CD: Trial For Use */
    public static final String SCE_ORD_TP_CD_TRIAL_FOR_USE = SCE_ORD_TP.TRIAL_FOR_USE;

    /** SCE_ORD_TP_CD: Loan */
    public static final String SCE_ORD_TP_CD_LOAN = SCE_ORD_TP.LOAN;

    /** SCE_ORD_TP_CD: Direct Sales */
    public static final String SCE_ORD_TP_CD_DIRECT_SALES = SCE_ORD_TP.DIRECT_SALES;

    /** SCE_ORD_TP_CD: Export */
    public static final String SCE_ORD_TP_CD_EXPORT = SCE_ORD_TP.EXPORT;

    /** SCE_ORD_TP_CD: DC Transfer */
    public static final String SCE_ORD_TP_CD_DC_TRANSFER = SCE_ORD_TP.DC_TRANSFER;

    /** SCE_ORD_TP_CD: Disposal */
    public static final String SCE_ORD_TP_CD_DISPOSAL = SCE_ORD_TP.DISPOSAL;

    /** SCE_ORD_TP_CD: Item Change */
    public static final String SCE_ORD_TP_CD_ITEM_CHANGE = SCE_ORD_TP.ITEM_CHANGE;

    /** SCE_ORD_TP_CD: Stock Status Change */
    public static final String SCE_ORD_TP_CD_STOCK_STATUS_CHANGE = SCE_ORD_TP.STOCK_STATUS_CHANGE;

    /** SCE_ORD_TP_CD: Damaged Claim Entry */
    public static final String SCE_ORD_TP_CD_DAMAGED_CLAIM_ENTRY = SCE_ORD_TP.DAMAGED_CLAIM_ENTRY;

    /** SCE_ORD_TP_CD: Damaged Claim Completion */
    public static final String SCE_ORD_TP_CD_DAMAGED_CLAIM_COMPLETION = SCE_ORD_TP.DAMAGED_CLAIM_COMPLETION;

    /** SCE_ORD_TP_CD: Kitting */
    public static final String SCE_ORD_TP_CD_KITTING = SCE_ORD_TP.KITTING;

    /** SCE_ORD_TP_CD: Refurbish */
    public static final String SCE_ORD_TP_CD_REFURBISH = SCE_ORD_TP.REFURBISH;

    /** SCE_ORD_TP_CD: Return to Vendor(Domestic) */
    public static final String SCE_ORD_TP_CD_RETURN_TO_VENDOR_DOMESTIC = SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC;

    /** SCE_ORD_TP_CD: Return to Vendor(Import) */
    public static final String SCE_ORD_TP_CD_RETURN_TO_VENDOR_IMPORT = SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT;

    /** SCE_ORD_TP_CD: DC Transfer */
    public static final String SCE_ORD_TP_CD_DC_TRANSFER_LOSS = SCE_ORD_TP.DC_TRANSFER_LOSS;
    
    /** SCE_ORD_TP_CD: Internal Transfer */
    public static final String SCE_ORD_TP_CD_INTERNAL_TRANSFER = SCE_ORD_TP.INTERNAL_TRANSFER;
    
    /** SCE_ORD_TP_CD: Tech Request */
    public static final String SCE_ORD_TP_CD_TECH_REQUEST = SCE_ORD_TP.TECH_REQUEST;
    
    /** SCE_ORD_TP_CD: Buy Back */
    public static final String SCE_ORD_TP_CD_BUY_BACK = SCE_ORD_TP.BUY_BACK;
    
    /** SCE_ORD_TP_CD: Repair (Subcontract) */
    public static final String SCE_ORD_TP_CD_REPAIR_SUBCONTRACT = SCE_ORD_TP.REPAIR_SUBCONTRACT;
    
    /** SCE_ORD_TP_CD: Refurbish Expense */
    public static final String SCE_ORD_TP_CD_REFURBISH_EXPENSE = SCE_ORD_TP.REFURBISH_EXPENSE;
    
    /** SCE_ORD_TP_CD: Un-Kitting */
    public static final String SCE_ORD_TP_CD_UN_KITTING = SCE_ORD_TP.UN_KITTING;
    
    /** SCE_ORD_TP_CD: Reman Item Change */
    public static final String SCE_ORD_TP_CD_REMAN_ITEM_CHANGE = SCE_ORD_TP.REMAN_ITEM_CHANGE;
    
    /** SCE_ORD_TP_CD: Sub WH Change */
    public static final String SCE_ORD_TP_CD_SUB_WH_CHANGE = SCE_ORD_TP.SUB_WH_CHANGE;
    
    /** SCE_ORD_TP_CD: Config Change */
    public static final String SCE_ORD_TP_CD_CONFIG_CHANGE = SCE_ORD_TP.CONFIG_CHANGE;
    
    /** Shipping Force Flag: off */
    public static final String SHPG_FRCE_FLG_OFF = ZYPConstant.FLG_OFF_N;

    /** Shipping Force Flag: on */
    public static final String SHPG_FRCE_FLG_ON = ZYPConstant.FLG_ON_Y;

    /** The  (@)  was (@). ResultCount = (@)  */
    public static final String ZZBM0009I = "ZZBM0009I";

    /** Please fill out/select the required field. */
    public static final String NLZM2001E = "NLZM2001E";

    /** The data specified does not exist. */
    public static final String NLZM2004E = "NLZM2004E";

    /** The process abended. */
    public static final String NLZM2010E = "NLZM2010E";

    /** The code you entered cannot be found in the master. */
    public static final String NLZM2016E = "NLZM2016E";

    /** The code you entered cannot be found in the master. */
    public static final String NLZM2017E = "NLZM2017E";

    /** The code you entered cannot be found in the master. */
    public static final String NLZM2023E = "NLZM2023E";

    /** The code you entered cannot be found in the master. */
    public static final String NLZM2024E = "NLZM2024E";

    /** The code you entered cannot be found in the master. */
    public static final String NLZM2025E = "NLZM2025E";

    /** The code you entered cannot be found in the master. */
    public static final String NLZM2029E = "NLZM2029E";

    /** The process abended. */
    public static final String NLZM2030E = "NLZM2030E";

    /** The value you entered is incorrect. */
    public static final String NLZM2031E = "NLZM2031E";

    /** The data specified does not exist. */
    public static final String NLZM2034E = "NLZM2034E";

    /** The data specified does not exist. */
    public static final String NLZM2040E = "NLZM2040E";

    // 02/05/2011 D.Fukaya append start
    /** US Domestic state code  have not been set to the table VAR_CHAR_CONST. */
    public static final String NLZM2047E = "NLZM2047E";
    // 02/05/2011 D.Fukaya append end

    /** DB Column: SHIP_PRTY */
    private static final String SHIP_PRTY = "SHIP_PRTY";

    /** DB Column: TRX_SRC_TP_CD */
    private static final String TRX_SRC_TP_CD = "TRX_SRC_TP_CD";

    /** DB Column: TRX_HDR_NUM */
    private static final String TRX_HDR_NUM = "TRX_HDR_NUM";
    
    /** DB Column: TRX_HDR_NUM */
    private static final String RTL_WH_CD = "RTL_WH_CD";

    /** DB Column: BILL_TO_CUST_CD */
    private static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** DB Column: SELL_TO_CUST_CD */
    private static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** DB Column: SHIP_TO_CUST_CD */
    private static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** DB Column: DROP_SHIP_FLG */
    private static final String DROP_SHIP_FLG = "DROP_SHIP_FLG";

    /** DB Column: ORIG_SHPG_SVC_LVL_CD */
    private static final String ORIG_SHPG_SVC_LVL_CD = "ORIG_SHPG_SVC_LVL_CD";

    /** DB Column: REQ_FRT_CHRG_TO_CD */
    private static final String REQ_FRT_CHRG_TO_CD = "REQ_FRT_CHRG_TO_CD";

    /** DB Column: REQ_FRT_CHRG_METH_CD */
    private static final String REQ_FRT_CHRG_METH_CD = "REQ_FRT_CHRG_METH_CD";

    /** DB Column: CARR_CD */
    private static final String CARR_CD = "CARR_CD";

    /** DB Column: RDD_DT */
    private static final String RDD_DT = "RDD_DT";

    /** DB Column: RSD_DT */
    private static final String RSD_DT = "RSD_DT";

    /** DB Column: SHIP_TO_LOC_NM */
    private static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    /** DB Column: SHIP_TO_ADDL_LOC_NM */
    private static final String SHIP_TO_ADDL_LOC_NM = "SHIP_TO_ADDL_LOC_NM";

    /** DB Column: SHIP_TO_FIRST_LINE_ADDR */
    private static final String SHIP_TO_FIRST_LINE_ADDR = "SHIP_TO_FIRST_LINE_ADDR";

    /** DB Column: SHIP_TO_SCD_LINE_ADDR */
    private static final String SHIP_TO_SCD_LINE_ADDR = "SHIP_TO_SCD_LINE_ADDR";

    /** DB Column: SHIP_TO_THIRD_LINE_ADDR */
    private static final String SHIP_TO_THIRD_LINE_ADDR = "SHIP_TO_THIRD_LINE_ADDR";

    /** DB Column: SHIP_TO_FRTH_LINE_ADDR */
    private static final String SHIP_TO_FRTH_LINE_ADDR = "SHIP_TO_FRTH_LINE_ADDR";

    /** DB Column: SHIP_TO_FIRST_REF_CMNT_TXT */
    private static final String SHIP_TO_FIRST_REF_CMNT_TXT = "SHIP_TO_FIRST_REF_CMNT_TXT";

    /** DB Column: SHIP_TO_SCD_REF_CMNT_TXT */
    private static final String SHIP_TO_SCD_REF_CMNT_TXT = "SHIP_TO_SCD_REF_CMNT_TXT";

    /** DB Column: SELL_TO_FIRST_REF_CMNT_TXT */
    private static final String SELL_TO_FIRST_REF_CMNT_TXT = "SELL_TO_FIRST_REF_CMNT_TXT";

    /** DB Column: SELL_TO_SCD_REF_CMNT_TXT */
    private static final String SELL_TO_SCD_REF_CMNT_TXT = "SELL_TO_SCD_REF_CMNT_TXT";

    /** DB Column: SHIP_TO_CTY_ADDR */
    private static final String SHIP_TO_CTY_ADDR = "SHIP_TO_CTY_ADDR";

    /** DB Column: SHIP_TO_ST_CD */
    private static final String SHIP_TO_ST_CD = "SHIP_TO_ST_CD";

    /** DB Column: SHIP_TO_POST_CD */
    private static final String SHIP_TO_POST_CD = "SHIP_TO_POST_CD";

    /** DB Column: SHIP_TO_CTRY_CD */
    private static final String SHIP_TO_CTRY_CD = "SHIP_TO_CTRY_CD";

    /** DB Column: RQST_CARR_CD */
    private static final String RQST_CARR_CD = "RQST_CARR_CD";

    /** DB Column: CARR_ACCT_NUM */
    private static final String CARR_ACCT_NUM = "CARR_ACCT_NUM";

    /** DB Column: INVTY_LOC_CD */
    private static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** DB Column: CUST_ISS_PO_NUM */
    private static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** DB Column: SO_DEPT_NUM */
    private static final String SO_DEPT_NUM = "SO_DEPT_NUM";     
    
    /** DB Column: CPO_SRC_TP_CD */
    private static final String CPO_SRC_TP_CD = "CPO_SRC_TP_CD";

    /** DB Column: RTE_GRP_NUM */
    private static final String RTE_GRP_NUM = "RTE_GRP_NUM";

    /** DB Column: FRT_CHRG_TO_CD */
    private static final String FRT_CHRG_TO_CD = "FRT_CHRG_TO_CD";

    /** DB Column: FRT_CHRG_METH_CD */
    private static final String FRT_CHRG_METH_CD = "FRT_CHRG_METH_CD";

    /** DB Column: SHPG_SVC_LVL_CD */
    private static final String SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** DB Column: SHPG_MODE_CD */
    private static final String SHPG_MODE_CD = "SHPG_MODE_CD";

    /** DB Column: PSD_DT */
    private static final String PSD_DT = "PSD_DT";

    /** DB Column: PDD_DT */
    private static final String PDD_DT = "PDD_DT";

    /** DB Column: ASN_SHIP_TO_CUST_CD */
    private static final String ASN_SHIP_TO_CUST_CD = "ASN_SHIP_TO_CUST_CD";

    /** DB Column: SHIP_SHIP_TO_CUST_CD */
    private static final String SHIP_SHIP_TO_CUST_CD = "SHIP_SHIP_TO_CUST_CD";

    /** DB Column: SHIP_LOC_NM */
    private static final String SHIP_LOC_NM = "SHIP_LOC_NM";

    /** DB Column: SHIP_ADDL_LOC_NM */
    private static final String SHIP_ADDL_LOC_NM = "SHIP_ADDL_LOC_NM";

    /** DB Column: SHIP_FIRST_LINE_ADDR */
    private static final String SHIP_FIRST_LINE_ADDR = "SHIP_FIRST_LINE_ADDR";

    /** DB Column: SHIP_SCD_LINE_ADDR */
    private static final String SHIP_SCD_LINE_ADDR = "SHIP_SCD_LINE_ADDR";

    /** DB Column: SHIP_THIRD_LINE_ADDR */
    private static final String SHIP_THIRD_LINE_ADDR = "SHIP_THIRD_LINE_ADDR";

    /** DB Column: SHIP_FRTH_LINE_ADDR */
    private static final String SHIP_FRTH_LINE_ADDR = "SHIP_FRTH_LINE_ADDR";

    /** DB Column: SHIP_FIRST_REF_CMNT_TXT */
    private static final String SHIP_FIRST_REF_CMNT_TXT = "SHIP_FIRST_REF_CMNT_TXT";

    /** DB Column: SHIP_SCD_REF_CMNT_TXT */
    private static final String SHIP_SCD_REF_CMNT_TXT = "SHIP_SCD_REF_CMNT_TXT";

    /** DB Column: SHIP_CTRY_CD */
    private static final String SHIP_CTRY_CD = "SHIP_CTRY_CD";

    /** DB Column: SHIP_ST_CD */
    private static final String SHIP_ST_CD = "SHIP_ST_CD";

    /** DB Column: SHIP_CTY_ADDR */
    private static final String SHIP_CTY_ADDR = "SHIP_CTY_ADDR";

    /** DB Column: SHIP_TEL_NUM */
    private static final String SHIP_TEL_NUM = "SHIP_TEL_NUM";

    /** DB Column: SHIP_POST_CD */
    private static final String SHIP_POST_CD = "SHIP_POST_CD";

    /** DB Column: SELL_SELL_TO_CUST_CD */
    private static final String SELL_SELL_TO_CUST_CD = "SELL_SELL_TO_CUST_CD";

    /** DB Column: SELL_LOC_NM */
    private static final String SELL_LOC_NM = "SELL_LOC_NM";

    /** DB Column: SELL_ADDL_LOC_NM */
    private static final String SELL_ADDL_LOC_NM = "SELL_ADDL_LOC_NM";

    /** DB Column: SELL_FIRST_LINE_ADDR */
    private static final String SELL_FIRST_LINE_ADDR = "SELL_FIRST_LINE_ADDR";

    /** DB Column: SELL_SCD_LINE_ADDR */
    private static final String SELL_SCD_LINE_ADDR = "SELL_SCD_LINE_ADDR";

    /** DB Column: SELL_THIRD_LINE_ADDR */
    private static final String SELL_THIRD_LINE_ADDR = "SELL_THIRD_LINE_ADDR";

    /** DB Column: SELL_FRTH_LINE_ADDR */
    private static final String SELL_FRTH_LINE_ADDR = "SELL_FRTH_LINE_ADDR";

    /** DB Column: SELL_FIRST_REF_CMNT_TXT */
    private static final String SELL_FIRST_REF_CMNT_TXT = "SELL_FIRST_REF_CMNT_TXT";

    /** DB Column: SELL_SCD_REF_CMNT_TXT */
    private static final String SELL_SCD_REF_CMNT_TXT = "SELL_SCD_REF_CMNT_TXT";

    /** DB Column: SELL_CTRY_CD */
    private static final String SELL_CTRY_CD = "SELL_CTRY_CD";

    /** DB Column: SELL_ST_CD */
    private static final String SELL_ST_CD = "SELL_ST_CD";

    /** DB Column: SELL_CTY_ADDR */
    private static final String SELL_CTY_ADDR = "SELL_CTY_ADDR";

    /** DB Column: SELL_TEL_NUM */
    private static final String SELL_TEL_NUM = "SELL_TEL_NUM";

    /** DB Column: SELL_POST_CD */
    private static final String SELL_POST_CD = "SELL_POST_CD";

    /** DB Column: BILL_BILL_TO_CUST_CD */
    private static final String BILL_BILL_TO_CUST_CD = "BILL_BILL_TO_CUST_CD";

    /** DB Column: ASN_REQ_FLG */
    private static final String ASN_REQ_FLG = "ASN_REQ_FLG";

    /** DB Column: PRINT_SCC_LB_FLG */
    private static final String PRINT_SCC_LB_FLG = "PRINT_SCC_LB_FLG";

    /** DB Column: PRINT_UCC_LB_FLG */
    private static final String PRINT_UCC_LB_FLG = "PRINT_UCC_LB_FLG";

    /** DB Column: MIX_PLT_ALLW_FLG */
    private static final String MIX_PLT_ALLW_FLG = "MIX_PLT_ALLW_FLG";

    /** DB Column: NON_ASN_UCC_LB_FLG */
    private static final String NON_ASN_UCC_LB_FLG = "NON_ASN_UCC_LB_FLG";

    /** DB Column: BILL_LOC_NM */
    private static final String BILL_LOC_NM = "BILL_LOC_NM";

    /** DB Column: BILL_ADDL_LOC_NM */
    private static final String BILL_ADDL_LOC_NM = "BILL_ADDL_LOC_NM";

    /** DB Column: BILL_FIRST_LINE_ADDR */
    private static final String BILL_FIRST_LINE_ADDR = "BILL_FIRST_LINE_ADDR";

    /** DB Column: BILL_SCD_LINE_ADDR */
    private static final String BILL_SCD_LINE_ADDR = "BILL_SCD_LINE_ADDR";

    /** DB Column: BILL_THIRD_LINE_ADDR */
    private static final String BILL_THIRD_LINE_ADDR = "BILL_THIRD_LINE_ADDR";

    /** DB Column: BILL_FRTH_LINE_ADDR */
    private static final String BILL_FRTH_LINE_ADDR = "BILL_FRTH_LINE_ADDR";

    /** DB Column: BILL_FIRST_REF_CMNT_TXT */
    private static final String BILL_FIRST_REF_CMNT_TXT = "BILL_FIRST_REF_CMNT_TXT";

    /** DB Column: BILL_SCD_REF_CMNT_TXT */
    private static final String BILL_SCD_REF_CMNT_TXT = "BILL_SCD_REF_CMNT_TXT";

    /** DB Column: BILL_CTRY_CD */
    private static final String BILL_CTRY_CD = "BILL_CTRY_CD";

    /** DB Column: BILL_ST_CD */
    private static final String BILL_ST_CD = "BILL_ST_CD";

    /** DB Column: BILL_CTY_ADDR */
    private static final String BILL_CTY_ADDR = "BILL_CTY_ADDR";

    /** DB Column: BILL_TEL_NUM */
    private static final String BILL_TEL_NUM = "BILL_TEL_NUM";

    /** DB Column: BILL_POST_CD */
    private static final String BILL_POST_CD = "BILL_POST_CD";

    /** DB Column: RTRN_LB_CD */
    private static final String RTRN_LB_CD = "RTRN_LB_CD";

    /** DB Column: RTRN_SELL_TO_CUST_CD */
    private static final String RTRN_SELL_TO_CUST_CD = "RTRN_SELL_TO_CUST_CD";

    /** DB Column: RTRN_LOC_NM */
    private static final String RTRN_LOC_NM = "RTRN_LOC_NM";

    /** DB Column: RTRN_ADDL_LOC_NM */
    private static final String RTRN_ADDL_LOC_NM = "RTRN_ADDL_LOC_NM";

    /** DB Column: RTRN_FIRST_LINE_ADDR */
    private static final String RTRN_FIRST_LINE_ADDR = "RTRN_FIRST_LINE_ADDR";

    /** DB Column: RTRN_SCD_LINE_ADDR */
    private static final String RTRN_SCD_LINE_ADDR = "RTRN_SCD_LINE_ADDR";

    /** DB Column: RTRN_THIRD_LINE_ADDR */
    private static final String RTRN_THIRD_LINE_ADDR = "RTRN_THIRD_LINE_ADDR";

    /** DB Column: RTRN_FRTH_LINE_ADDR */
    private static final String RTRN_FRTH_LINE_ADDR = "RTRN_FRTH_LINE_ADDR";

    /** DB Column: RTRN_FIRST_REF_CMNT_TXT */
    private static final String RTRN_FIRST_REF_CMNT_TXT = "RTRN_FIRST_REF_CMNT_TXT";

    /** DB Column: RTRN_SCD_REF_CMNT_TXT */
    private static final String RTRN_SCD_REF_CMNT_TXT = "RTRN_SCD_REF_CMNT_TXT";

    /** DB Column: RTRN_CTRY_CD */
    private static final String RTRN_CTRY_CD = "RTRN_CTRY_CD";

    /** DB Column: RTRN_ST_CD */
    private static final String RTRN_ST_CD = "RTRN_ST_CD";

    /** DB Column: RTRN_CTY_ADDR */
    private static final String RTRN_CTY_ADDR = "RTRN_CTY_ADDR";

    /** DB Column: RTRN_TEL_NUM */
    private static final String RTRN_TEL_NUM = "RTRN_TEL_NUM";

    /** DB Column: RTRN_POST_CD */
    private static final String RTRN_POST_CD = "RTRN_POST_CD";

    /** DB Column: WH_WH_CD */
    private static final String WH_WH_CD = "WH_WH_CD";

    /** DB Column: WH_LOC_NM */
    private static final String WH_LOC_NM = "WH_LOC_NM";

    /** DB Column: WH_ADDL_LOC_NM */
    private static final String WH_ADDL_LOC_NM = "WH_ADDL_LOC_NM";

    /** DB Column: WH_FIRST_LINE_ADDR */
    private static final String WH_FIRST_LINE_ADDR = "WH_FIRST_LINE_ADDR";

    /** DB Column: WH_SCD_LINE_ADDR */
    private static final String WH_SCD_LINE_ADDR = "WH_SCD_LINE_ADDR";

    /** DB Column: WH_THIRD_LINE_ADDR */
    private static final String WH_THIRD_LINE_ADDR = "WH_THIRD_LINE_ADDR";

    /** DB Column: WH_FRTH_LINE_ADDR */
    private static final String WH_FRTH_LINE_ADDR = "WH_FRTH_LINE_ADDR";

    /** DB Column: WH_FIRST_REF_CMNT_TXT */
    private static final String WH_FIRST_REF_CMNT_TXT = "WH_FIRST_REF_CMNT_TXT";

    /** DB Column: WH_SCD_REF_CMNT_TXT */
    private static final String WH_SCD_REF_CMNT_TXT = "WH_SCD_REF_CMNT_TXT";

    /** DB Column: WH_CTRY_CD */
    private static final String WH_CTRY_CD = "WH_CTRY_CD";

    /** DB Column: WH_ST_CD */
    private static final String WH_ST_CD = "WH_ST_CD";

    /** DB Column: WH_CTY_ADDR */
    private static final String WH_CTY_ADDR = "WH_CTY_ADDR";

    /** DB Column: WH_TEL_NUM */
    private static final String WH_TEL_NUM = "WH_TEL_NUM";

    /** DB Column: WH_POST_CD */
    private static final String WH_POST_CD = "WH_POST_CD";

    /** DB Column: VND_VND_CD */
    private static final String VND_VND_CD = "VND_VND_CD";

    /** DB Column: VND_LOC_NM */
    private static final String VND_LOC_NM = "VND_LOC_NM";

    /** DB Column: VND_ADDL_LOC_NM */
    private static final String VND_ADDL_LOC_NM = "VND_ADDL_LOC_NM";

    /** DB Column: VND_FIRST_LINE_ADDR */
    private static final String VND_FIRST_LINE_ADDR = "VND_FIRST_LINE_ADDR";

    /** DB Column: VND_SCD_LINE_ADDR */
    private static final String VND_SCD_LINE_ADDR = "VND_SCD_LINE_ADDR";

    /** DB Column: VND_THIRD_LINE_ADDR */
    private static final String VND_THIRD_LINE_ADDR = "VND_THIRD_LINE_ADDR";

    /** DB Column: VND_FRTH_LINE_ADDR */
    private static final String VND_FRTH_LINE_ADDR = "VND_FRTH_LINE_ADDR";

    /** DB Column: VND_FIRST_REF_CMNT_TXT */
    private static final String VND_FIRST_REF_CMNT_TXT = "VND_FIRST_REF_CMNT_TXT";

    /** DB Column: VND_SCD_REF_CMNT_TXT */
    private static final String VND_SCD_REF_CMNT_TXT = "VND_SCD_REF_CMNT_TXT";

    /** DB Column: VND_CTRY_CD */
    private static final String VND_CTRY_CD = "VND_CTRY_CD";

    /** DB Column: VND_ST_CD */
    private static final String VND_ST_CD = "VND_ST_CD";

    /** DB Column: VND_CTY_ADDR */
    private static final String VND_CTY_ADDR = "VND_CTY_ADDR";

    /** DB Column: VND_TEL_NUM */
    private static final String VND_TEL_NUM = "VND_TEL_NUM";

    /** DB Column: VND_POST_CD */
    private static final String VND_POST_CD = "VND_POST_CD";

    /** DB Column: VND_RTRN_NUM */
    private static final String VND_RTRN_NUM = "VND_RTRN_NUM";

    /** DB Column: RTE_GUIDE_CUST_CD */
    private static final String RTE_GUIDE_CUST_CD = "RTE_GUIDE_CUST_CD";

    /** DB Column: RTE_GUIDE_SECT_FIRST_FLG */
    private static final String RTE_GUIDE_SECT_FIRST_FLG = "RTE_GUIDE_SECT_FIRST_FLG";

    /** DB Column: RTE_GUIDE_SECT_SCD_FLG */
    private static final String RTE_GUIDE_SECT_SCD_FLG = "RTE_GUIDE_SECT_SCD_FLG";

    /** DB Column: RTE_GUIDE_SECT_THIRD_FLG */
    private static final String RTE_GUIDE_SECT_THIRD_FLG = "RTE_GUIDE_SECT_THIRD_FLG";

    /** DB Column: RTE_GUIDE_SECT_FRTH_FLG */
    private static final String RTE_GUIDE_SECT_FRTH_FLG = "RTE_GUIDE_SECT_FRTH_FLG";

    /** DB Column: RTE_GUIDE_SECT_FIFTH_FLG */
    private static final String RTE_GUIDE_SECT_FIFTH_FLG = "RTE_GUIDE_SECT_FIFTH_FLG";

    /** DB Column: RTE_GUIDE_SECT_SIXTH_FLG */
    private static final String RTE_GUIDE_SECT_SIXTH_FLG = "RTE_GUIDE_SECT_SIXTH_FLG";

    /** DB Column: RTE_GUIDE_SECT_SVNTH_FLG */
    private static final String RTE_GUIDE_SECT_SVNTH_FLG = "RTE_GUIDE_SECT_SVNTH_FLG";

    /** DB Column: RTE_GUIDE_SECT_EIGHTH_FLG */
    private static final String RTE_GUIDE_SECT_EIGHTH_FLG = "RTE_GUIDE_SECT_EIGHTH_FLG";

    /** DB Column: TRX_CD */
    private static final String TRX_CD = "TRX_CD";

    /** DB Column: TRX_RSN_CD */
    private static final String TRX_RSN_CD = "TRX_RSN_CD";

    /** DB Column: TRX_LINE_NUM */
    private static final String TRX_LINE_NUM = "TRX_LINE_NUM";

    /** DB Column: TRX_LINE_SUB_NUM */
    private static final String TRX_LINE_SUB_NUM = "TRX_LINE_SUB_NUM";

    /** DB Column: SP_FUNC_NET_UNIT_PRC_AMT */
    private static final String SP_FUNC_NET_UNIT_PRC_AMT = "SP_FUNC_NET_UNIT_PRC_AMT";

    /** DB Column: AVAL_SO_QTY */
    private static final String AVAL_SO_QTY = "AVAL_SO_QTY";

    /** DB Column: MDSE_CD */
    private static final String MDSE_CD = "MDSE_CD";

    /** DB Column: STK_STS_CD */
    private static final String STK_STS_CD = "STK_STS_CD";

    /** DB Column: SET_MDSE_CD */
    private static final String SET_MDSE_CD = "SET_MDSE_CD";

    /** DB Column: SYS_SRC_CD */
    private static final String SYS_SRC_CD = "SYS_SRC_CD";

    /** DB Column: IN_EACH_QTY */
    private static final String IN_EACH_QTY = "IN_EACH_QTY";

    /** DB Column: IN_POUND_WT */
    private static final String IN_POUND_WT = "IN_POUND_WT";

    /** DB Column: IN_INCH_VOL */
    private static final String IN_INCH_VOL = "IN_INCH_VOL";

    /** DB Column: MDSE_MDSE_CD */
    private static final String MDSE_MDSE_CD = "MDSE_MDSE_CD";

    /** DB Column: SHPG_SER_TAKE_FLG */
    private static final String SHPG_SER_TAKE_FLG = "SHPG_SER_TAKE_FLG";

    /** DB Column: ORD_QTY */
    private static final String ORD_QTY = "ORD_QTY";

    /** DB Column: CUST_MDSE_CD */
    private static final String CUST_MDSE_CD = "CUST_MDSE_CD";

    /** DB Column: CPO_ORD_TP_CD */
    private static final String CPO_ORD_TP_CD = "CPO_ORD_TP_CD";

    /** DB Column: SET_MDSE_NM */
    private static final String SET_MDSE_NM = "SET_MDSE_NM";

    /** DB Column: FROM_STK_STS_CD */
    private static final String FROM_STK_STS_CD = "FROM_STK_STS_CD";

    /** DB Column: TO_STK_STS_CD */
    private static final String TO_STK_STS_CD = "TO_STK_STS_CD";

    /** DB Column: INVTY_ORD_NUM */
    private static final String INVTY_ORD_NUM = "INVTY_ORD_NUM";

    /** DB Column: INVTY_ORD_LINE_NUM */
    private static final String INVTY_ORD_LINE_NUM = "INVTY_ORD_LINE_NUM";

    /** DB Column: PRNT_MDSE_CD */
    private static final String PRNT_MDSE_CD = "PRNT_MDSE_CD";

    /** DB Column: CHILD_MDSE_QTY */
    private static final String CHILD_MDSE_QTY = "CHILD_MDSE_QTY";

    /** DB Column: RTL_SWH RTL_WH_CD */
    private static final String RS2_RTL_WH_CD = "RS2_RTL_WH_CD";

    /** DB Column: SHIP_TO_CUST_CD SELL_TO_CUST_CD */
    private static final String SHIP_TO_SELL_TO_CUST_CD = "SHIP_TO_SELL_TO_CUST_CD";

    /** DB Column: SHPG_ORD -> INVTY_LOC_CD -> SELL_TO_CUST_CD */
    private static final String INVTY_TO_SELL_TO_CUST_CD = "INVTY_TO_SELL_TO_CUST_CD";

    /** DB Column: CTAC_PTNR_PSN_NM (BILL_TO) */
    private static final String BILL_TO_CTAC_PTNR_PSN_NM = "BILL_TO_CTAC_PTNR_PSN_NM";

    /** DB Column: CTAC_PSN_TEL_NUM (BILL_TO) */
    private static final String BILL_TO_CTAC_PSN_TEL_NUM = "BILL_TO_CTAC_PSN_TEL_NUM";

    /** DB Column: CTAC_PTNR_PSN_NM (SELL_TO) */
    private static final String SELL_TO_CTAC_PTNR_PSN_NM = "SELL_TO_CTAC_PTNR_PSN_NM";

    /** DB Column: CTAC_PSN_TEL_NUM (SELL_TO) */
    private static final String SELL_TO_CTAC_PSN_TEL_NUM = "SELL_TO_CTAC_PSN_TEL_NUM";

    /** DB Column: CTAC_PTNR_PSN_NM (SHIP_TO) */
    private static final String SHIP_TO_CTAC_PTNR_PSN_NM = "SHIP_TO_CTAC_PTNR_PSN_NM";

    /** DB Column: CTAC_PSN_TEL_NUM (SHIP_TO) */
    private static final String SHIP_TO_CTAC_PSN_TEL_NUM = "SHIP_TO_CTAC_PSN_TEL_NUM";

    // 201/12/28 QC#18460(SOL#087) T,Hakodate ADD START
    /** DB Column: RQST_ISTL_TM */
    private static final String RQST_ISTL_TM = "RQST_ISTL_TM";
    // 201/12/28 QC#18460(SOL#087) T,Hakodate ADD END

    /** Table Name: SHPG_ORD */
    private static final String SHPG_ORD = "SHPG_ORD:";

    /** Table Name: SHPG_ORD_DTL */
    private static final String SHPG_ORD_DTL = "SHPG_ORD_DTL:";

    /** Table Name: SHPG_ORD_CUST_DTL */
    private static final String SHPG_ORD_CUST_DTL = "SHPG_ORD_CUST_DTL:";

    /** Table Name: SHPG_ORD_MSG */
    private static final String SHPG_ORD_MSG = "SHPG_ORD_MSG:";

    /** Get Number Key: S/O# Online */
    private static final String NUM_GET_KEY_SO_ONLINE = "SO#_ONL";

    /** Get Number Key: S/O# Batch */
    private static final String NUM_GET_KEY_SO_BATCH = "SO#_BAT";

    /** UCC Number Code: D */
    private static final String UCC_NUM_CD_D = "D";

    /** ASG Location Role Layer Number: 1 */
    private static final String ASG_LOC_ROLE_LAYER_NUM_1 = "1";

    /** ASG Location Role Layer Number: 3 */
    private static final String ASG_LOC_ROLE_LAYER_NUM_3 = "3";

    /** DB Return CD: Normal End */
    private static final String DB_RETURN_CD_NORMAL_END = "0000";
     
    /** Data Created */
    private static final String DATA_PROCESSED = "processed";

    /** Shipping Order Message Shipping Order Description Text Infix */
    private static final String SO_MSG_SO_DESC_TXT_INFIX = ":";

    /** Shipping Order Message Routing Text Sequence Number */
    private static final String SO_MSG_RTE_TXT_SQ_NUM = "1";

    /** Shipping Order Message Routing Description Text Prefix */
    private static final String SO_MSG_RTE_DESC_TXT_PREFIX = "* SEE ROUTING GUIDE=";

    /** Shipping Order Message Routing Description Text Infix */
    private static final String SO_MSG_RTE_DESC_TXT_INFIX = ",";

    /** Shipping Order Message Routing Description Text Suffix */
    private static final String SO_MSG_RTE_DESC_TXT_SFX = "*";

    /** Shipping Order Message Routing Description Text Number Symbol */
    private static final String SO_MSG_RTE_DESC_TXT_NUM_SYMBOL = "#";

    /** System Time Format */
    private static final String DATE_FMT_HHMMSS = "HHmmss";

    /** Shipping Order Slip Number Format */
    private static final String SO_SLP_NUM_FMT = "%03d";

    /** Mdse Code Basic Length */
    private static final int CHILD_ORD_TAKE_MDSE_CD_LG = 8;

    /** CTAC_PTNR_PSN_NM Max Length */
    private static final int CTAC_PTNR_PSN_NM_MAX_LG = 25;

    /** Add QC#31042. SHIP_TO_CTAC_PSN_NM Max Length */
    private static final int SHIP_TO_CTAC_PSN_NM_MAX_LG = 90;

    /** SO_CUST_LINE_LOC_NM_01/02 Max Length */
    private static final int SO_CUST_LINE_LOC_NM_MAX_LG = 35;
    
    /** SO_CUST_LINE_ADDR_01/02/03/04 Max Length */
    private static final int SO_CUST_LINE_ADDR_MAX_LG = 35;
    
    /** TOT_SHIP_AMT Max Length */
    private static final int  TOT_SHIP_AMT_MAX_LG = 19;

    /** TOT_SHIP_AMT Decimal Length */
    private static final int  TOT_SHIP_AMT_DECL_LG = 4;

    /** TOT_SHPG_VOL Max Length */
    private static final int  TOT_SHPG_VOL_MAX_LG = 7;

    /** TOT_SHPG_VOL Decimal Length */
    private static final int  TOT_SHPG_VOL_DECL_LG = 2;

    private static final BigDecimal DIV_VAL_OF_CONV_FROM_INCH_TO_FEET = new BigDecimal(1728);

    /** TOT_SHPG_WT Max Length */
    private static final int TOT_SHPG_WT_MAX_LG = 11;

    /** TOT_SHPG_WT Decimal Length */
    private static final int TOT_SHPG_WT_DECL_LG = 4;

    /** SHPG_PRC_AMT Max Length */
    private static final int SHPG_PRC_AMT_MAX_LG = 19;

    /** SHPG_PRC_AMT Decimal Length */
    private static final int SHPG_PRC_AMT_DECL_LG = 4;

    /** SQL ID : VND */
    private static final String SQL_ID_VND_001 = "001";
    
    /** SQL ID : SELL_TO_CUST */
    private static final String SQL_ID_SELL_TO_CUST_003 = "003";

    /** Table Access Key : GLBL_CMPY_CD */
    private static final String TAK_GLBL_CMPY_CD = "glblCmpyCd01";

    /** Table Access Key : VND_CD */
    private static final String TAK_VND_CD = "vndCd01";

    /** Table Access Key : SELL_TO_CUST_CD */
    private static final String TAK_SELL_TO_CUST_CD = "sellToCustCd01";

    // QC#20154 Add.
    /** Middleware Replace Target Group Code : Tecsys(1) */
    private static final String MW_GRP_CD_TECSYS = "1";

    /** SQL Access Parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Input Parameter Header: glblCmpyCd */
    private String hdrGlblCmpyCd = null;

    /** Input Parameter Header: sceOrdTpCd */
    private String hdrSceOrdTpCd = null;

    /** Input Parameter Header: frceFlg */
    private String hdrShpgFrceFlg = null;

    /** Sales Date */
    private String slsDt = null;

    /** System Time */
    private String sysTm = null;

    /** Shipping Order Number */
    private String soNum = null;

    /** SCE Order Type Table Serial Number Take Flag */
    private String sceOrdTpSerNumTakeFlg = null;

    /** Total Shipping Amount */
    private BigDecimal totShipAmt = BigDecimal.ZERO;

    /** ShipPriority Minimum Value */
    private BigDecimal minShipPrty = null;

    /** ShpgOrd Process Count */
    private int cntShpgOrd = 0;

    /** ShpgOrdDtl Process Count */
    private int cntShpgOrdDtl = 0;

    /** ShpgOrdCustDtl Process Count */
    private int cntShpgOrdCustDtl = 0;

    /** ShpgOrdMsg Process Count */
    private int cntShpgOrdMsg = 0;

    /** Internal Item: CBS_DROP_FLG */
    private static final String CBS_DROP_FLG = "CBS_DROP_FLG";

    /** SO message's length */
    private static final int SO_MSG_LEN = 65;

    /** SO message's max length */
    private static final int SO_MSG_MAX_LEN = 260;
    
    /** line feed code */
    private static final String LINE_FEED_CODE = "\r\n";
    
    /** space */
    private static final String SPACE = " ";

    /** Summary SO Config Flag */
    private boolean configFlg = false;

    /** Summary Hazmat Flg */
    private boolean hazMatFlg = false;

    /** Cache Map for hazMatMdse */
    private Map<String, Boolean> hazMatMdse = new HashMap<String, Boolean>();

    /** Location type code of warehouse */
    private static final String LOC_TP_CD_WAREHOUSE  = LOC_TP.WAREHOUSE;

    /** Location type code of technician */
    private static final String LOC_TP_CD_TECHNICIAN = LOC_TP.TECHNICIAN;

    /** select shipToCustCd or rs2_rtlWhCd */
    private static final List<String> SELECT_ORD_TP_LIST_FOR_SHIP_TO_CUST = Arrays.asList(
            SCE_ORD_TP.DC_TRANSFER //
            , SCE_ORD_TP.DC_TRANSFER_LOSS //
            , SCE_ORD_TP.INTERNAL_TRANSFER //
            , SCE_ORD_TP.TECH_REQUEST //
            , SCE_ORD_TP.BUY_BACK //
            , SCE_ORD_TP.REPAIR_SUBCONTRACT //
            , SCE_ORD_TP.REMAN_ITEM_CHANGE //
            , SCE_ORD_TP.REMAN_LOCATOR_TRANSFER //
            , SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY //
            , SCE_ORD_TP.REMAN_PARTS_USAGE //
            , SCE_ORD_TP.DISPOSAL //
            , SCE_ORD_TP.ITEM_CHANGE //
            , SCE_ORD_TP.STOCK_STATUS_CHANGE //
            , SCE_ORD_TP.SUB_WH_CHANGE //
            , SCE_ORD_TP.CONFIG_CHANGE //
            , SCE_ORD_TP.DAMAGED_CLAIM_ENTRY //
            , SCE_ORD_TP.DAMAGED_CLAIM_COMPLETION //
            , SCE_ORD_TP.KITTING //
            , SCE_ORD_TP.REFURBISH //
            , SCE_ORD_TP.UN_KITTING);

    /** Process Mode : New */
    public static final String MODE_NEW = "0";

    /** Process Mode : Modify (CSA not used) */
    public static final String MODE_MODIFY = "1";

    /** Process Mode : Cancel (CSA not used) */
    public static final String MODE_CANCEL = "2";

    /** Process Mode : Drop Ship */
    public static final String MODE_DROP_SHIP = "3";

    /* 08/26/2016 CSAI Y.Imazu Add QC#9845 START */
    /** Process Mode : WMS Drop */
    public static final String MODE_WMS_DROP = "4";

    /** Process Mode : Send Carrier Request */
    public static final String MODE_CARR_RQST = "5";
    
    //QC#20154 Add mode
    /** Process Mode : Update Shipping Address & WMS DROP */
    public static final String MODE_UPD_SHIP_ADDR_AND_WMS = "6";

    /** Lead Time Mode : Fixed Value */
    private static final String SCHD_LEAD_TM_FIX_MODE = "0";
    /* 08/26/2016 CSAI Y.Imazu Add QC#9845 END */

    /** NLZM0027E:SO Number is not entered. */
    private static final String NLZM0027E = "NLZM0027E";

    /** NLZM1028E:There is no data for SHPG_ORD*/
    private static final String NLZM1028E = "NLZM1028E";

    /** NLZM1029E:There is no data for SHPG_ORD_DTL*/
    private static final String NLZM1029E = "NLZM1029E";

    /** NPZM0101E:Mode is invalid. */
    private static final String NPZM0101E = "NPZM0101E";

    /** NLZM2258E:Failed to register SHPG_BO_DTL. */
    private static final String NLZM2258E = "NLZM2258E";

    /** NLZM2260E:Failed to register SHPG_ORD_SCHD_TRK. */
    private static final String NLZM2260E = "NLZM2260E";

    /** NLZM2271E:The specified record does not exist. */
    private static final String NLZM2271E = "NLZM2271E";

    /** NLZM2314E:ETA Date must be entered. */
    private static final String NLZM2314E = "NLZM2314E";

    /** NLZM2129E:Failed to update SHPG_ORD. */
    private static final String NLZM2129E = "NLZM2129E";

    /** NLZM2130E:Failed to update SHPG_ORD_DTL. */
    private static final String NLZM2130E = "NLZM2130E";

    /* 02/24/2016 CSAI Y.Imazu Add QC#4205 START */
    /** Schedule coordinator that can be assigned automatically cannot be specified. */
    private static final String NLBM1340W = "NLBM1340W";
    /* 02/24/2016 CSAI Y.Imazu Add QC#4205 END */

    /** NLZM0123E:Data does not exist in the Shipping Plan Table.*/
    private static final String NLZM0123E = "NLZM0123E";

    /** NLZM2151E:Data does not exists in Area Lead Time. */
    private static final String NLZM2151E = "NLZM2151E";

    // QC#20154 Add.
    /** NLZM2518E: Ship to address cannot be update if it is already shipped or partially shipped. */
    private static final String NLZM2518W = "NLZM2518W";

    // QC#20154 Add.
    /** NLZM2519W: Ship to Address was changed. Please tell warehouse manager the new Ship to Address. */
    private static final String NLZM2519W = "NLZM2519W";

    /** Parameter delimiter */
    private static final String DELIMITER = ",";

    /** 24 Hours */
    private static final BigDecimal TWENTY_FOUR_HOURS = new BigDecimal(24);

    /** String Number Pattern */
    private static final String STRING_NUM_PATTERN  = "%03d";

    /** OrderSerial Mode */
    private static final String ORDER_SERIAL_MODE = "1";

    /** VAR_CONST Key : Expense Shipment DS_ORD_TO_CD Key */
    private static final String EXPENSE_SHIPMENT_DS_ORD_TO = "EXPENSE_SHIPMENT_DS_ORD_TO";

    /** API Execute Mode */
    private String apiMode = null;
    
    //09/11/2017 CITS S.Endo Add Sol#069(QC#18270) START
    /**  */
    private String swRsdDt = null;
    //09/11/2017 CITS S.Endo Add Sol#069(QC#18270) END

    /** VAR_CONST Key : AUTO_SO_DROP_FLG_CHK_LIST Key */
    private static final String AUTO_SO_DROP_FLG_CHK_LIST = "AUTO_SO_DROP_FLG_CHK_LIST";

    /** NWZM1383E :  Failed to update the DS_CPO_CONFIG. */
    private static final String NWZM1383E = "NWZM1383E";

    /**
     * Format
     */
    public NLZC205001() {

        super();

        //SSM Format
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Shipping Order Create
     * @see com.canon.cusa.s21.api.NLZ.NLZC205001.execute
     * @param params NLZC205001PMsgList
     * @param onBatchType Online/BatchType
     */
    public void execute(final List<NLZC205001PMsg> params, final ONBATCH_TYPE onBatchType) {
        boolean roopFlg = false;

        Map<String, String> baseSoHdr = new HashMap<String, String>();
        List<SHPG_ORD_DTLTMsg> soDtls = new ArrayList<SHPG_ORD_DTLTMsg>();
        List<SHPG_ORD_DTLTMsg> soDtlMdseChngAfters = new ArrayList<SHPG_ORD_DTLTMsg>();
        List<String> soMsgList;
        Set<String> soNumList = new HashSet<String>();

        for (NLZC205001PMsg msg : params) {

            S21ApiMessageMap msgMap = new S21ApiMessageMap(msg);

            final String mode = msg.xxModeCd.getValue();

            if (!ZYPCommonFunc.hasValue(mode)) {
                S21InfoLogOutput.println(NPZM0101E);
                msgMap.addXxMsgId(NPZM0101E);
                msgMap.flush();
                return;
            }

            if (MODE_NEW.equals(mode)) {

                if (!roopFlg) {

                    // First Time Roop of SO Create
                    getBaseSo(msg, onBatchType, baseSoHdr, soDtls, soDtlMdseChngAfters);
                    roopFlg = true;

                } else {

                    // After Scond Time Roop of SO Create
                    regetBaseSo(msg, baseSoHdr, soDtls);
                }

                // As Operation Error as End Roop
                if (S21ApiUtil.isXxMsgId(msg)) {

                    return;
                }
            }

            if (!ZYPCommonFunc.hasValue(this.apiMode)) {
                this.apiMode = mode;
            }
        }

        if (MODE_NEW.equals(apiMode)) {

            soMsgList = getSoMsgList(params, baseSoHdr);

            createSo(onBatchType, baseSoHdr, soDtls, soDtlMdseChngAfters, soMsgList);

            outputProcessCnt();

            for (NLZC205001PMsg msg : params) {

                // Set Normal End Return Value
                msg.soNum.setValue(this.soNum);

                if (!soNumList.contains(msg.soNum.getValue())) {

                    // Update SO
                    updateSO(msg, onBatchType);

                    soNumList.add(msg.soNum.getValue());
                }
            }

        } else {

            for (NLZC205001PMsg pmsgSoApi : params) {

                if (!soNumList.contains(pmsgSoApi.soNum.getValue())) {

                    // Update SO
                    updateSO(pmsgSoApi, onBatchType);

                    soNumList.add(pmsgSoApi.soNum.getValue());
                }
            }
        }
    }

    /**
     * Output Process Count
     */
    private void outputProcessCnt() {

        String[] str1 = {SHPG_ORD, DATA_PROCESSED, String.valueOf(this.cntShpgOrd) };
        String[] str2 = {SHPG_ORD_DTL, DATA_PROCESSED, String.valueOf(this.cntShpgOrdDtl) };
        String[] str3 = {SHPG_ORD_CUST_DTL, DATA_PROCESSED, String.valueOf(this.cntShpgOrdCustDtl) };
        String[] str4 = {SHPG_ORD_MSG, DATA_PROCESSED, String.valueOf(this.cntShpgOrdMsg) };

        S21InfoLogOutput.println(ZZBM0009I, str1);
        S21InfoLogOutput.println(ZZBM0009I, str2);
        S21InfoLogOutput.println(ZZBM0009I, str3);
        S21InfoLogOutput.println(ZZBM0009I, str4);
    }

    /**
     * Get Base Shipping Order to First Time
     * @param param NLZC205001PMsgInterface
     * @param onBatchType Online/BatchType
     * @param baseSoHdr BaseShippingOrderHeader
     * @param soDtls ShippingOrderDetailList
     * @param soDtlMdseChngAfters ShippingOrderDetailListOfMdseChange
     */
    private void getBaseSo(
            final NLZC205001PMsg param,
            final ONBATCH_TYPE onBatchType,
            Map<String, String> baseSoHdr,
            List<SHPG_ORD_DTLTMsg> soDtls,
            List<SHPG_ORD_DTLTMsg> soDtlMdseChngAfters) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String sceOrdTpCd = param.sceOrdTpCd.getValue();
        String shpgFrceFlg = param.shpgFrceFlg.getValue();

        boolean operationErrFlg;

        operationErrFlg = checkRequiredParam(msgMap);

        if (operationErrFlg) {

            msgMap.flush();
            return;
        }

        this.hdrGlblCmpyCd = glblCmpyCd;
        this.hdrSceOrdTpCd = sceOrdTpCd;
        this.hdrShpgFrceFlg = shpgFrceFlg;

        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        this.sysTm = ZYPDateUtil.getCurrentSystemTime(DATE_FMT_HHMMSS);
        this.soNum = getSoNum(msgMap, onBatchType);

        SCE_ORD_TPTMsg sceOrdTpT = getSceOrdTpTMsg(msgMap);

        if (sceOrdTpT == null) {

            msgMap.flush();
            return;
        }

        this.sceOrdTpSerNumTakeFlg = sceOrdTpT.serNumTakeFlg.getValue();

        if (ZYPConstant.FLG_ON_Y.equals(shpgFrceFlg)) {

            this.minShipPrty = getMinShipPrty(msgMap);
        }

        operationErrFlg = getBaseSoHdr(msgMap, baseSoHdr);

        if (operationErrFlg) {

            msgMap.flush();
            return;
        }

        getBaseSoHdrTrxCd(sceOrdTpT, baseSoHdr);

        operationErrFlg = getBaseSoDtl(msgMap, soDtls);

        if (operationErrFlg) {

            msgMap.flush();
            return;
        }

        if (SCE_ORD_TP.ITEM_CHANGE.equals(sceOrdTpCd)) {

            operationErrFlg = getBaseSoDtlItemChngAfters(msgMap, baseSoHdr.get(TRX_HDR_NUM), soDtlMdseChngAfters);

            if (operationErrFlg) {

                msgMap.flush();
                return;
            }
        }

        if (SCE_ORD_TP.REGULAR.equals(sceOrdTpCd)
                || SCE_ORD_TP.TRIAL_FOR_SALE.equals(sceOrdTpCd)
                || SCE_ORD_TP.TRIAL_FOR_USE.equals(sceOrdTpCd)
                || SCE_ORD_TP.LOAN.equals(sceOrdTpCd)
                || SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd)
                || SCE_ORD_TP.EXPORT.equals(sceOrdTpCd)
                || SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(sceOrdTpCd)
                || SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(sceOrdTpCd)) {

            getBaseSoMsgRte(msgMap, baseSoHdr);
        }

        msgMap.flush();
    }

    /**
     * Get Base Shipping Order to After Second Time
     * @param param NLZC205001PMsgInterface
     * @param baseSoHdr BaseShippingOrderHeader
     * @param soDtls ShippingOrderDetailList
     */
    private void regetBaseSo(final NLZC205001PMsg param, Map<String, String> baseSoHdr, List<SHPG_ORD_DTLTMsg> soDtls) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        boolean operationErrFlg;

        operationErrFlg = checkRequiredParam(msgMap);

        if (operationErrFlg) {

            msgMap.flush();
            return;
        }

        operationErrFlg = checkIdentityParam(msgMap);

        if (operationErrFlg) {

            msgMap.flush();
            return;
        }

        if (ZYPConstant.FLG_ON_Y.equals(param.shpgFrceFlg.getValue())) {

            BigDecimal shipPrty = getMinShipPrty(msgMap);

            if (shipPrty != null) {
                if (this.minShipPrty == null || this.minShipPrty.compareTo(shipPrty) > 0) {

                    this.minShipPrty = shipPrty;

                    operationErrFlg = getOrigShpgSvcLvlCd(msgMap, baseSoHdr);

                    if (operationErrFlg) {

                        msgMap.flush();
                        return;
                    }
                }
            }
        }

        operationErrFlg = getBaseSoDtl(msgMap, soDtls);

        if (operationErrFlg) {

            msgMap.flush();
            return;
        }

        msgMap.flush();
    }

    /**
     * Get Minimum Shipping Priority
     * @param msgMap S21ApiMessageMap
     * @return MinimumShipPriority
     */
    private BigDecimal getMinShipPrty(S21ApiMessageMap msgMap) {

        BigDecimal shipPrty = null;

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();
        String sceOrdTpCd = param.sceOrdTpCd.getValue();
        String shpgPlnNum = param.shpgPlnNum.getValue();

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("shpgPlnNum", shpgPlnNum);
        queryParam.put("trxSrcTpCd", getTrxSrcTpCd(sceOrdTpCd));

        shipPrty = (BigDecimal) this.ssmBatchClient.queryObject("getMinShipPrtyRs", queryParam, new GetMinShipPrty());
        return shipPrty;
    }

    /**
     * Get Base So Header
     * @param msgMap S21ApiMessageMap
     * @param baseSoHdr BaseShippingOrderDetailList
     * @return OperationErrorFlag (true: OperationError)
     */
    private boolean getBaseSoHdr(S21ApiMessageMap msgMap, Map<String, String> baseSoHdr) {

        boolean operationErrFlg = false;

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();
        String sceOrdTpCd = param.sceOrdTpCd.getValue();
        String shpgFrceFlg = param.shpgFrceFlg.getValue();
        String shpgPlnNum = param.shpgPlnNum.getValue();

        // SQL bind parameter
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("shpgPlnNum", shpgPlnNum);
        queryParam.put("trxSrcTpCd", getTrxSrcTpCd(sceOrdTpCd));
        // QC#23559 Start
        queryParam.put("ctacPsnTpBillToContract", CTAC_TP.BILL_TO_CONTACT_CONTRACTS);
        queryParam.put("ctacPsnTpBillToEquipment", CTAC_TP.BILL_TO_CONTACT_EQUIPMENT);
        queryParam.put("ctacPsnTpBillToSply", CTAC_TP.BILL_TO_SUPPLIES);
        // QC#23559 End
        // 2018/11/14 QC#27954 Add Start
        queryParam.put("ctacPsnTpBillToParts", CTAC_TP.BILL_TO_CONTACT_PARTS);
        // 2018/11/14 QC#27954 Add End
        queryParam.put("ctacPsnTpSellTo", CTAC_TP.SELL_TO_CONTACT);
        // START 2017/10/17 S.Katsuma [Sol#454,ADD]
//        queryParam.put("ctacPsnTpShipTo", CTAC_TP.SHIP_TO_CONTACT);
        queryParam.put("ctacPsnTpDelivIns", CTAC_TP.DELIVERY_INSTALL);
        queryParam.put("ctacPsnTpOrdCtac", CTAC_TP.ORDER_CONTACT);
        // END 2017/10/17 S.Katsuma [Sol#454,ADD]

        // Pattern 1
        if (SCE_ORD_TP.REGULAR.equals(sceOrdTpCd)
                || SCE_ORD_TP.TRIAL_FOR_SALE.equals(sceOrdTpCd)
                || SCE_ORD_TP.TRIAL_FOR_USE.equals(sceOrdTpCd)
                || SCE_ORD_TP.LOAN.equals(sceOrdTpCd)
                || SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd)) {

            // SQL bind parameter
            queryParam.put("shpgFrceFlg", shpgFrceFlg);
            // QC#27833
            queryParam.put("ordCatgCtxTp", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
            operationErrFlg = (Boolean) this.ssmBatchClient.queryObject("getBaseSoHdrCpoRs", queryParam, new GetBaseSoHdrCpo(msgMap, baseSoHdr));

        // Pattern 3
        } else if (SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd)
                || SCE_ORD_TP.DC_TRANSFER_LOSS.equals(sceOrdTpCd)
                || SCE_ORD_TP.INTERNAL_TRANSFER.equals(sceOrdTpCd)
                || SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)
                || SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd)
                || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd)
                || SCE_ORD_TP.REFURBISH_EXPENSE.equals(sceOrdTpCd)
                || SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd)
                || SCE_ORD_TP.REMAN_LOCATOR_TRANSFER.equals(sceOrdTpCd)
                || SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY.equals(sceOrdTpCd)
                || SCE_ORD_TP.REMAN_PARTS_USAGE.equals(sceOrdTpCd)) {

            if (SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd)) {

                queryParam.put("shpgFrceFlg", shpgFrceFlg);

            } else {

                queryParam.put("shpgFrceFlg", ZYPConstant.FLG_ON_Y);
            }

            queryParam.put("frtChrgToCd", FRT_CHRG_TO.CANON);
            queryParam.put("frtChrgMethCd", FRT_CHRG_METH.N_OR_A);
            operationErrFlg = (Boolean) this.ssmBatchClient.queryObject("getBaseSoHdrDcTransfRs", queryParam, new GetBaseSoHdrDcTransf(msgMap, baseSoHdr));

        // Pattern 4,5
        } else if (SCE_ORD_TP.DISPOSAL.equals(sceOrdTpCd)
                || SCE_ORD_TP.ITEM_CHANGE.equals(sceOrdTpCd)
                || SCE_ORD_TP.STOCK_STATUS_CHANGE.equals(sceOrdTpCd)
                || SCE_ORD_TP.DAMAGED_CLAIM_ENTRY.equals(sceOrdTpCd)
                || SCE_ORD_TP.DAMAGED_CLAIM_COMPLETION.equals(sceOrdTpCd)
                || SCE_ORD_TP.SUB_WH_CHANGE.equals(sceOrdTpCd)
                || SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)) {

            operationErrFlg = (Boolean) this.ssmBatchClient.queryObject("getBaseSoHdrInvtyRs", queryParam, new GetBaseSoHdrInvty(msgMap, baseSoHdr));

        // Pattern 6
        } else if (SCE_ORD_TP.KITTING.equals(sceOrdTpCd)
                || SCE_ORD_TP.REFURBISH.equals(sceOrdTpCd)
                || SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd)) {

            operationErrFlg = (Boolean) this.ssmBatchClient.queryObject("getBaseSoHdrWrkRs", queryParam, new GetBaseSoHdrWrk(msgMap, baseSoHdr));

        // Pattern 7
        } else if (SCE_ORD_TP.EXPORT.equals(sceOrdTpCd)) {

            // SQL bind parameter
            queryParam.put("shpgFrceFlg", shpgFrceFlg);
            operationErrFlg = (Boolean) this.ssmBatchClient.queryObject("getBaseSoHdrExptRs", queryParam, new GetBaseSoHdrExpt(msgMap, baseSoHdr));

        // Pattern 8
        } else if (SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(sceOrdTpCd) || SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(sceOrdTpCd)) {

            operationErrFlg = (Boolean) this.ssmBatchClient.queryObject("getBaseSoHdrVndRtrnRs", queryParam, new GetBaseSoHdrVndRtrn(msgMap, baseSoHdr));

        } else {

            writeOperationErr(NLZM2016E, msgMap);
            operationErrFlg = true;
        }

        return operationErrFlg;
    }

    /**
     * Get SCE Order Type Record
     * @param msgMap S21ApiMessageMap
     * @return SceOrderTypeRecord
     */
    private SCE_ORD_TPTMsg getSceOrdTpTMsg(S21ApiMessageMap msgMap) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();
        String sceOrdTpCd = param.sceOrdTpCd.getValue();

        SCE_ORD_TPTMsg sceOrdTpT = new SCE_ORD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(sceOrdTpT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(sceOrdTpT.sceOrdTpCd, sceOrdTpCd);
        ZYPEZDItemValueSetter.setValue(sceOrdTpT.inbdOtbdCd, INBD_OTBD.OUTBOUND);

        sceOrdTpT = (SCE_ORD_TPTMsg) EZDTBLAccessor.findByKey(sceOrdTpT);

        if (sceOrdTpT == null) {

            writeOperationErr(NLZM2016E, msgMap);
            return sceOrdTpT;
        }

        if (!DB_RETURN_CD_NORMAL_END.equals(sceOrdTpT.getReturnCode())) {

            throw new S21AbendException(NLZM2030E);
        }

        return sceOrdTpT;
    }

    /**
     * Get Transaction Code
     * @param sceOrdTpT SceOrderTypeRecord
     * @param baseSoHdr BaseShippingOrderHeader
     */
    private void getBaseSoHdrTrxCd(SCE_ORD_TPTMsg sceOrdTpT, Map<String, String> baseSoHdr) {

        baseSoHdr.put(TRX_CD, sceOrdTpT.trxCd.getValue());
        baseSoHdr.put(TRX_RSN_CD, sceOrdTpT.trxRsnCd.getValue());
    }

    /**
     * Get ShippingServiceLevel Code
     * @param msgMap S21ApiMessageMap
     * @param baseSoHdr BaseShippingOrderHeader
     * @return OperationErrorFlag (true: OperationError)
     */
    private boolean getOrigShpgSvcLvlCd(S21ApiMessageMap msgMap, Map<String, String> baseSoHdr) {

        boolean operationErrFlg = false;

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        // SQL bind parameter
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("shpgPlnNum", param.shpgPlnNum.getValue());
        queryParam.put("trxSrcTpCd", getTrxSrcTpCd(param.sceOrdTpCd.getValue()));

        operationErrFlg = (Boolean) this.ssmBatchClient.queryObject("getOrigShpgSvcLvlCdRs", queryParam, new GetOrigShpgSvcLvlCd(msgMap, baseSoHdr));
        return operationErrFlg;
    }

    /**
     * Get Base Shipping Order Detail
     * @param msgMap S21ApiMessageMap
     * @param soDtls ShippingOrderDetailList
     * @return OperationErrorFlag (true: OperationError)
     */
    private boolean getBaseSoDtl(S21ApiMessageMap msgMap, List<SHPG_ORD_DTLTMsg> soDtls) {

        boolean operationErrFlg = false;

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();
        String sceOrdTpCd = param.sceOrdTpCd.getValue();
        String shpgPlnNum = param.shpgPlnNum.getValue();

        // SQL bind parameter
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("shpgPlnNum", shpgPlnNum);
        queryParam.put("trxSrcTpCd", getTrxSrcTpCd(sceOrdTpCd));
        queryParam.put("pkgUomCdEa", PKG_UOM.EACH);
        queryParam.put("pkgUomCdCa", PKG_UOM.MASTER_CARTON);
        queryParam.put("dmnPkgApvlStsCd", APVL_STS.SUBMITTED);

        SHPG_ORD_DTLTMsg shpgOrdDtlT = new SHPG_ORD_DTLTMsg();

        // Pattern 1
        if (SCE_ORD_TP.REGULAR.equals(sceOrdTpCd)
                || SCE_ORD_TP.TRIAL_FOR_SALE.equals(sceOrdTpCd)
                || SCE_ORD_TP.TRIAL_FOR_USE.equals(sceOrdTpCd)
                || SCE_ORD_TP.LOAN.equals(sceOrdTpCd)
                || SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd)
                || SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd)) {

            operationErrFlg = (Boolean) this.ssmBatchClient.queryObject("getBaseSoDtlCpoRs", queryParam, new GetBaseSoDtlCpo(msgMap, shpgOrdDtlT));
            getBaseSoDtlSetMdse(msgMap, shpgOrdDtlT);

        // Pattern 2
        } else if (SCE_ORD_TP.ITEM_CHANGE.equals(sceOrdTpCd)
                || SCE_ORD_TP.DISPOSAL.equals(sceOrdTpCd)
                || SCE_ORD_TP.STOCK_STATUS_CHANGE.equals(sceOrdTpCd)
                || SCE_ORD_TP.DAMAGED_CLAIM_ENTRY.equals(sceOrdTpCd)
                || SCE_ORD_TP.DAMAGED_CLAIM_COMPLETION.equals(sceOrdTpCd)
                || SCE_ORD_TP.SUB_WH_CHANGE.equals(sceOrdTpCd)
                || SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)) {

            operationErrFlg = (Boolean) this.ssmBatchClient.queryObject("getBaseSoDtlStkStsChngRs", queryParam, new GetBaseSoDtlStkStsChng(msgMap, shpgOrdDtlT));

        // Pattern 3
        } else if (SCE_ORD_TP.INTERNAL_TRANSFER.equals(sceOrdTpCd)
                || SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)
                || SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd)
                || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd)
                || SCE_ORD_TP.REFURBISH_EXPENSE.equals(sceOrdTpCd)
                || SCE_ORD_TP.KITTING.equals(sceOrdTpCd)
                || SCE_ORD_TP.REFURBISH.equals(sceOrdTpCd)
                || SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd)
                || SCE_ORD_TP.DC_TRANSFER_LOSS.equals(sceOrdTpCd)
                || SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd)
                || SCE_ORD_TP.REMAN_LOCATOR_TRANSFER.equals(sceOrdTpCd)
                || SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY.equals(sceOrdTpCd)
                || SCE_ORD_TP.REMAN_PARTS_USAGE.equals(sceOrdTpCd)) {

            operationErrFlg = (Boolean) this.ssmBatchClient.queryObject("getBaseSoDtlInvtyWrkRs", queryParam, new GetBaseSoDtlInvtyWrk(msgMap, shpgOrdDtlT));

        // Pattern 4
        } else if (SCE_ORD_TP.EXPORT.equals(sceOrdTpCd)) {

            operationErrFlg = (Boolean) this.ssmBatchClient.queryObject("getBaseSoDtlExptRs", queryParam, new GetBaseSoDtlExpt(msgMap, shpgOrdDtlT));
            getBaseSoDtlSetMdse(msgMap, shpgOrdDtlT);

        // Pattern 5
        } else if (SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(sceOrdTpCd) || SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(sceOrdTpCd)) {

            operationErrFlg = (Boolean) this.ssmBatchClient.queryObject("getBaseSoDtlVndRtrnRs", queryParam, new GetBaseSoDtlVndRtrn(msgMap, shpgOrdDtlT));
            getBaseSoDtlSetMdse(msgMap, shpgOrdDtlT);

        } else {

            writeOperationErr(NLZM2016E, msgMap);
            operationErrFlg = true;
        }

        // SO Total Calc
        BigDecimal unitPrcAmt = nullToZero(shpgOrdDtlT.unitPrcAmt.getValue());
        BigDecimal shpgQty = nullToZero(shpgOrdDtlT.shpgQty.getValue());
        BigDecimal shipAmt = unitPrcAmt.multiply(shpgQty);
        this.totShipAmt = this.totShipAmt.add(shipAmt);

        // 11/02/2012 Oce WDS SOL#94 ADD START
        // Summary SO Config Flag
        if (ZYPConstant.FLG_ON_Y.equals(shpgOrdDtlT.configItemFlg.getValue())) {

            this.configFlg = true;
        }

        // Summary HazMat Flag
        if (!this.hazMatFlg) {

            this.hazMatFlg = getHazMatFlg(shpgOrdDtlT, glblCmpyCd);
        }

        // 11/02/2012 Oce WDS SOL#94 ADD END
        soDtls.add(shpgOrdDtlT);

        return operationErrFlg;
    }

    // 11/02/2012 Oce WDS SOL#94 ADD START
    /**
     * getHazMatFlg
     * @param shpgOrdDtlT SHPG_ORD_DTLTMsg
     * @param glblCmpyCd String
     * @return String
     */
    private Boolean getHazMatFlg(SHPG_ORD_DTLTMsg shpgOrdDtlT, String glblCmpyCd) {

        String mdseCd = shpgOrdDtlT.mdseCd.getValue();
        Boolean retHazMatFlg = false;

        if (!hazMatMdse.containsKey(mdseCd)) {

            Map<String, Object> queryParam = new HashMap<String, Object>(2);
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("mdseCd", mdseCd);

            if( (Integer) this.ssmBatchClient.queryObject("getHazMatFlgCount", queryParam) > 0) {

                retHazMatFlg = true;
            }

            hazMatMdse.put(mdseCd, retHazMatFlg);

        } else {

            retHazMatFlg = hazMatMdse.get(mdseCd);
        }

        return retHazMatFlg;
    }
    // 11/02/2012 Oce WDS SOL#94 ADD END

    /**
     * Get Shipping Order Detail Of After Mdse Change
     * @param msgMap S21ApiMessageMap
     * @param invtyOrdNum InventryOrderNumber
     * @param soDtlMdseChngAfters ShippingOrderDetailListOfMdseChange
     * @return OperationErrorFlag (true: OperationError)
     */
    private boolean getBaseSoDtlItemChngAfters(S21ApiMessageMap msgMap, final String invtyOrdNum, List<SHPG_ORD_DTLTMsg> soDtlMdseChngAfters) {

        boolean operationErrFlg = false;

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();

        // SQL bind parameter
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invtyOrdNum", invtyOrdNum);
        queryParam.put("pkgUomCdEa", PKG_UOM.EACH);
        queryParam.put("pkgUomCdCa", PKG_UOM.MASTER_CARTON);
        queryParam.put("dmnPkgApvlStsCd", APVL_STS.SUBMITTED);

        operationErrFlg = (Boolean) this.ssmBatchClient.queryObject("getBaseSoDtlItemChngAfterRs", queryParam, new GetBaseSoDtlItemChngAfters(msgMap, soDtlMdseChngAfters));
        return operationErrFlg;
    }

    /**
     * Get SetMdse Amount (Shipping Order Detail)
     * @param msgMap S21ApiMessageMap
     * @param soDtls ShippingOrderDetailTMsg
     * @return OperationErrorFlag (true: OperationError)
     */
    private void getBaseSoDtlSetMdse(S21ApiMessageMap msgMap, SHPG_ORD_DTLTMsg shpgOrdDtlT) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String sprntMdseCd = shpgOrdDtlT.setMdseCd.getValue();
        String childMdseCd = shpgOrdDtlT.mdseCd.getValue();

        if (!childMdseCd.equals(sprntMdseCd)) {

            // SQL bind parameter
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("prntMdseCd", sprntMdseCd);
            queryParam.put("mdseCmpsnTpCd", MDSE_CMPSN_TP.SET_MDSE);
            queryParam.put("childMdseCd", childMdseCd);

            CMPSNTMsg cmpsnT = new CMPSNTMsg();
            this.ssmBatchClient.queryObject("getBaseSoDtlSetMdseRs", queryParam, new GetBaseSoDtlSetMdse(cmpsnT));

            if (ZYPCommonFunc.hasValue(cmpsnT.prntMdseCd.getValue())) {

                BigDecimal shpgQty = shpgOrdDtlT.shpgQty.getValue();
                BigDecimal childMdseQty = cmpsnT.childMdseQty.getValue();
                BigDecimal setShpgQty = shpgQty.divide(childMdseQty, BigDecimal.ROUND_UP);

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.setShpgQty, setShpgQty);

            } else {

                // SQL bind parameter
                queryParam.remove("childMdseCd");
                queryParam.put("mdseCmpsnTpCd", MDSE_CMPSN_TP.SET_ORDERTAKE_MDSE);
                queryParam.put("childOrdTakeMdseCd", getChildOrdTakeMdseCd(childMdseCd));

                cmpsnT = new CMPSNTMsg();
                this.ssmBatchClient.queryObject("getBaseSoDtlSetMdseRs", queryParam, new GetBaseSoDtlSetMdse(cmpsnT));

                if (ZYPCommonFunc.hasValue(cmpsnT.prntMdseCd.getValue())) {

                    BigDecimal shpgQty = shpgOrdDtlT.shpgQty.getValue();
                    BigDecimal childMdseQty = cmpsnT.childMdseQty.getValue();
                    BigDecimal setShpgQty = shpgQty.divide(childMdseQty, BigDecimal.ROUND_UP);

                    ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.setShpgQty, setShpgQty);
                }
            }
        }
    }

    /**
     * Get Shipping Order Msg Routing Msg
     * @param msgMap S21ApiMessageMap
     * @param baseSoMsg BaseShippingOrderMessage
     * @return OperationErrorFlag (true: OperationError)
     */
    private void getBaseSoMsgRte(S21ApiMessageMap msgMap, Map<String, String> baseSoMsg) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        // SQL bind parameter
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("asgLocRoleLayerNum", ASG_LOC_ROLE_LAYER_NUM_3);
        queryParam.put("custCd", baseSoMsg.get(SHIP_TO_CUST_CD));
        queryParam.put("slsDt", this.slsDt);

        this.ssmBatchClient.queryObject("getBaseSoMsgRteRs", queryParam, new GetBaseSoMsgRte(baseSoMsg));

        if (!ZYPCommonFunc.hasValue(baseSoMsg.get(RTE_GUIDE_CUST_CD))) {

            // SQL bind parameter
            queryParam.put("asgLocRoleLayerNum", ASG_LOC_ROLE_LAYER_NUM_1);
            queryParam.put("custCd", baseSoMsg.get(BILL_TO_CUST_CD));

            this.ssmBatchClient.queryObject("getBaseSoMsgRteRs", queryParam, new GetBaseSoMsgRte(baseSoMsg));
        }
    }

    /**
     * Create Shipping Order
     * @param onBatchType Online/BatchType
     * @param baseSoHdr BaseShippingOrderHeader
     * @param soDtls ShippingOrderDetailList
     * @param soDtlMdseChngAfters ShippingOrderDetailListOfMdseChange
     */
    private void createSo(
            final ONBATCH_TYPE onBatchType,
            final Map<String, String> baseSoHdr,
            final List<SHPG_ORD_DTLTMsg> soDtls,
            final List<SHPG_ORD_DTLTMsg> soDtlMdseChngAfters,
            List<String> soMsgList) {

        createSoComn(
                onBatchType,
                baseSoHdr,
                soDtls,
                soDtlMdseChngAfters,
                soMsgList);

        // Pattern 1 
        if (SCE_ORD_TP.REGULAR.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.TRIAL_FOR_SALE.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.TRIAL_FOR_USE.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.LOAN.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.DIRECT_SALES.equals(this.hdrSceOrdTpCd)) {

            createSoCpo(onBatchType, baseSoHdr, soDtls);

        // Pattern 2
        } else if (SCE_ORD_TP.DC_TRANSFER.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.DC_TRANSFER_LOSS.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.INTERNAL_TRANSFER.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.TECH_REQUEST.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.BUY_BACK.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.REFURBISH_EXPENSE.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.REMAN_LOCATOR_TRANSFER.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.REMAN_PARTS_USAGE.equals(this.hdrSceOrdTpCd)) {

            createSoDcTransf(baseSoHdr);

        // Pattern 3
        } else if (SCE_ORD_TP.DISPOSAL.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.ITEM_CHANGE.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.STOCK_STATUS_CHANGE.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.SUB_WH_CHANGE.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.CONFIG_CHANGE.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.DAMAGED_CLAIM_ENTRY.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.DAMAGED_CLAIM_COMPLETION.equals(this.hdrSceOrdTpCd)) {

            createSoInvty(baseSoHdr);

        // Pattern 4
        } else if (SCE_ORD_TP.KITTING.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.REFURBISH.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.UN_KITTING.equals(this.hdrSceOrdTpCd)) {

            createSoWrk(baseSoHdr);

        // Pattern 5
        } else if (SCE_ORD_TP.EXPORT.equals(this.hdrSceOrdTpCd)) {

            createSoExpt(onBatchType, baseSoHdr, soDtls);

        // Pattern 6
        } else if (SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(this.hdrSceOrdTpCd) || SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(this.hdrSceOrdTpCd)) {

            createSoVndRtrn(onBatchType, baseSoHdr, soDtls);

        } else {

            throw new S21AbendException(NLZM2016E);
        }
    }

    /**
     * Get Shipping Order Number
     * @param msgMap S21ApiMessageMap
     * @param onBatchType Online/BatchType
     * @return ShippingOrderNumber
     */
    private String getSoNum(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        String uniqueID = null;

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();

        if (ONBATCH_TYPE.ONLINE.equals(onBatchType)) {

            uniqueID = ZYPNumbering.getUniqueID(glblCmpyCd, NUM_GET_KEY_SO_ONLINE);

        } else if (ONBATCH_TYPE.BATCH.equals(onBatchType)) {

            uniqueID = ZYPNumbering.getUniqueID(glblCmpyCd, NUM_GET_KEY_SO_BATCH);

        } else {

            throw new S21AbendException(NLZM2030E);
        }

        return uniqueID;
    }

    /**
     * Call Shipping Plan Update API
     * @param onBatchType Online/BatchType
     * @param baseSoHdr BaseShippingOrderHeader
     * @param soDtls ShippingOrderDetailTMsg
     */
    private void callShpgPlnAPI(final ONBATCH_TYPE onBatchType, final Map<String, String> baseSoHdr, final SHPG_ORD_DTLTMsg soDtls) {

        NWZC003001PMsg pmsg = new NWZC003001PMsg();

        pmsg.glblCmpyCd.setValue(this.hdrGlblCmpyCd);
        pmsg.shpgModeCd.setValue(NWZC003001.MODE_PRINTED_SOCREATED);
        pmsg.shpgPlnNum.setValue(soDtls.shpgPlnNum.getValue());
        pmsg.soNum.setValue(this.soNum);
        pmsg.soSlpNum.setValue(soDtls.soSlpNum.getValue());

        if (SCE_ORD_TP.REGULAR.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.TRIAL_FOR_SALE.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.TRIAL_FOR_USE.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.LOAN.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.DIRECT_SALES.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.DC_TRANSFER.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.DC_TRANSFER_LOSS.equals(this.hdrSceOrdTpCd)) {

            if (ZYPConstant.FLG_ON_Y.equals(this.hdrShpgFrceFlg) || SCE_ORD_TP.DC_TRANSFER_LOSS.equals(this.hdrSceOrdTpCd)) {

                pmsg.psdDt.setValue(this.slsDt);

            } else {

                pmsg.shpgSvcLvlCd.setValue(baseSoHdr.get(SHPG_SVC_LVL_CD));
                pmsg.psdDt.setValue(baseSoHdr.get(PSD_DT));
                pmsg.pddDt.setValue(baseSoHdr.get(PDD_DT));
            }
        }
        //09/11/2017 CITS S.Endo MOD Sol#069(QC#18270) START
        if (SCE_ORD_TP.SUB_WH_CHANGE.equals(this.hdrSceOrdTpCd) || SCE_ORD_TP.ITEM_CHANGE.equals(this.hdrSceOrdTpCd)) {
            SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
            shpgPlnTMsg.shpgPlnNum.setValue(soDtls.shpgPlnNum.getValue());
            shpgPlnTMsg.glblCmpyCd.setValue(this.hdrGlblCmpyCd);
            shpgPlnTMsg = (SHPG_PLNTMsg) EZDTBLAccessor.findByKey(shpgPlnTMsg);
            if (shpgPlnTMsg != null){
                ZYPEZDItemValueSetter.setValue(pmsg.psdDt, shpgPlnTMsg.rsdDt);
                swRsdDt = shpgPlnTMsg.rsdDt.getValue();
            }
        }
        //09/11/2017 CITS S.Endo MOD Sol#069(QC#18270) END
        // Set API Parameter
        List<NWZC003001PMsg> params = new ArrayList<NWZC003001PMsg>();
        params.add(pmsg);

        NWZC003001 api = new NWZC003001();
        api.execute(params, onBatchType);

        // ListSize is 1. Then don't use roop.
        if (S21ApiUtil.isXxMsgId(pmsg)) {

            List<String> msgIds = S21ApiUtil.getXxMsgIdList(pmsg);
            String throwMsgId = msgIds.get(0);
            throw new S21AbendException(throwMsgId);
        }
    }

//    /**
//     * Call DO Update API
//     * @param onBatchType Online/BatchType
//     * @param baseSoHdr BaseShippingOrderHeader
//     * @param soDtls ShippingOrderDetailTMsg
//     */
//    private void callDoUpdateAPI(final ONBATCH_TYPE onBatchType, final Map<String, String> baseSoHdr, final List<SHPG_ORD_DTLTMsg> soDtls) {
//
//        NTZC001001PMsg param1 = new NTZC001001PMsg();
//        ZYPEZDItemValueSetter.setValue(param1.glblCmpyCd, this.hdrGlblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(param1.xxProcDoStsCd, DO_STS_TP.DO_CREATED);
//        ZYPEZDItemValueSetter.setValue(param1.trxSrcTpCd, baseSoHdr.get(TRX_SRC_TP_CD));
//        ZYPEZDItemValueSetter.setValue(param1.doRqstSrcNum, this.soNum);
//        ZYPEZDItemValueSetter.setValue(param1.trxHdrNum, baseSoHdr.get(TRX_HDR_NUM));
//        ZYPEZDItemValueSetter.setValue(param1.doNum, this.soNum);
//        ZYPEZDItemValueSetter.setValue(param1.procDt, this.slsDt);
//
//        List<NTZC001001_DtlListPMsg> param2 = new ArrayList<NTZC001001_DtlListPMsg>();
//
//        for (SHPG_ORD_DTLTMsg soDtl : soDtls) {
//
//            NTZC001001_DtlListPMsg pmsg = new NTZC001001_DtlListPMsg();
//            ZYPEZDItemValueSetter.setValue(pmsg.shpgPlnNum, soDtl.shpgPlnNum.getValue());
//            ZYPEZDItemValueSetter.setValue(pmsg.trxLineNum, soDtl.trxLineNum.getValue());
//            ZYPEZDItemValueSetter.setValue(pmsg.trxLineSubNum, soDtl.trxLineSubNum.getValue());
//            ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, soDtl.mdseCd.getValue());
//            param2.add(pmsg);
//        }
//
//        NTZC001001 api = new NTZC001001();
//        api.execute(param1, param2, onBatchType);
//
//        if (S21ApiUtil.isXxMsgId(param1)) {
//
//            List<String> msgIds = S21ApiUtil.getXxMsgIdList(param1);
//            String throwMsgId = msgIds.get(0);
//            throw new S21AbendException(throwMsgId);
//        }
//    }

    /**
     * Check Required Input Parameter
     * @param msgMap S21ApiMessageMap
     * @return boolean
     */
    private boolean checkRequiredParam(S21ApiMessageMap msgMap) {

        boolean operationErrFlg = false;

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();
        String sceOrdTpCd = param.sceOrdTpCd.getValue();
        String shpgPlnNum = param.shpgPlnNum.getValue();

        if (!(ZYPCommonFunc.hasValue(glblCmpyCd)
                && ZYPCommonFunc.hasValue(sceOrdTpCd)
                && ZYPCommonFunc.hasValue(shpgPlnNum))) {

            writeOperationErr(NLZM2001E, msgMap);
            operationErrFlg = true;
        }

        S21InfoLogOutput.println(msgMap.getPmsg().toString());

        return operationErrFlg;
    }

    /**
     * Check Identity Input Parameter
     * @param msgMap S21ApiMessageMap
     * @return boolean
     */
    private boolean checkIdentityParam(S21ApiMessageMap msgMap) {

        boolean operationErrFlg = false;

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();
        String sceOrdTpCd = param.sceOrdTpCd.getValue();
        String shpgFrceFlg = param.shpgFrceFlg.getValue();

        if (!(this.hdrGlblCmpyCd.equals(glblCmpyCd)
                && this.hdrSceOrdTpCd.equals(sceOrdTpCd)
                && this.hdrShpgFrceFlg.equals(shpgFrceFlg))) {

            writeOperationErr(NLZM2031E, msgMap);
            operationErrFlg = true;
        }

        return operationErrFlg;
    }

    /**
     * Get Minimum Shipping Priority
     */
    private class GetMinShipPrty extends S21SsmBigDecimalResultSetHandlerSupport {

        public BigDecimal doProcessQueryResult(ResultSet rs) throws SQLException {

            if (rs.next()) {

                return rs.getBigDecimal(SHIP_PRTY);

            } else {

                return null;
            }
        }
    }

    /**
     * Get Base Shipping Order Header CPO type
     */
    private class GetBaseSoHdrCpo extends S21SsmBooleanResultSetHandlerSupport {

        /** S21ApiMessageMap */
        private S21ApiMessageMap msgMap;

        /** Base Shipping Order Header */
        private Map<String, String> baseSoHeader;

        public GetBaseSoHdrCpo(S21ApiMessageMap msgMap, Map<String, String> baseSoHeader) {

            this.msgMap = msgMap;
            this.baseSoHeader = baseSoHeader;
        }

        /**
         * doProcessQueryResult
         * @param rs ResultSet
         * @return Boolean
         */
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            boolean operationErrFlg = false;

            NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
            String shpgFrceFlg = param.shpgFrceFlg.getValue();

            if (rs.next()) {

                // SHPG_PLN
                baseSoHeader.put(TRX_SRC_TP_CD, nullToEmpty(rs.getString(TRX_SRC_TP_CD)));
                baseSoHeader.put(TRX_HDR_NUM, nullToEmpty(rs.getString(TRX_HDR_NUM)));
                baseSoHeader.put(BILL_TO_CUST_CD, nullToEmpty(rs.getString(BILL_TO_CUST_CD)));
                baseSoHeader.put(SELL_TO_CUST_CD, nullToEmpty(rs.getString(SELL_TO_CUST_CD)));
                baseSoHeader.put(SHIP_TO_CUST_CD, nullToEmpty(rs.getString(SHIP_TO_CUST_CD)));
                baseSoHeader.put(DROP_SHIP_FLG, nullToEmpty(rs.getString(DROP_SHIP_FLG)));
                baseSoHeader.put(CARR_CD, nullToEmpty(rs.getString(CARR_CD)));
                baseSoHeader.put(RDD_DT, nullToEmpty(rs.getString(RDD_DT)));
                baseSoHeader.put(RSD_DT, nullToEmpty(rs.getString(RSD_DT)));
                baseSoHeader.put(RQST_CARR_CD, nullToEmpty(rs.getString(RQST_CARR_CD)));
                baseSoHeader.put(CARR_ACCT_NUM, nullToEmpty(rs.getString(CARR_ACCT_NUM)));
                baseSoHeader.put(INVTY_LOC_CD, nullToEmpty(rs.getString(INVTY_LOC_CD)));
                // CPO
                baseSoHeader.put(CUST_ISS_PO_NUM, nullToEmpty(rs.getString(CUST_ISS_PO_NUM)));
                //baseSoHeader.put(CUST_DEPT_NUM, nullToEmpty(rs.getString(CUST_DEPT_NUM)));
                // EDI_PO_NOTE
                baseSoHeader.put(SO_DEPT_NUM, nullToEmpty(rs.getString(SO_DEPT_NUM)));
                baseSoHeader.put(CPO_SRC_TP_CD, nullToEmpty(rs.getString(CPO_SRC_TP_CD)));
                // BILL_TO_CUST
                baseSoHeader.put(ASN_REQ_FLG, nullToEmpty(rs.getString(ASN_REQ_FLG)));
                baseSoHeader.put(PRINT_SCC_LB_FLG, nullToEmpty(rs.getString(PRINT_SCC_LB_FLG)));
                baseSoHeader.put(PRINT_UCC_LB_FLG, nullToEmpty(rs.getString(PRINT_UCC_LB_FLG)));
                baseSoHeader.put(MIX_PLT_ALLW_FLG, nullToEmpty(rs.getString(MIX_PLT_ALLW_FLG)));
                baseSoHeader.put(NON_ASN_UCC_LB_FLG, nullToEmpty(rs.getString(NON_ASN_UCC_LB_FLG)));
                // RTRN_TO
                baseSoHeader.put(RTRN_LB_CD, nullToEmpty(rs.getString(RTRN_LB_CD)));

                if (ZYPConstant.FLG_ON_Y.equals(shpgFrceFlg)) {

                    // SHPG_PLN
                    baseSoHeader.put(ORIG_SHPG_SVC_LVL_CD, nullToEmpty(rs.getString(ORIG_SHPG_SVC_LVL_CD)));
                    baseSoHeader.put(REQ_FRT_CHRG_TO_CD, nullToEmpty(rs.getString(REQ_FRT_CHRG_TO_CD)));
                    baseSoHeader.put(REQ_FRT_CHRG_METH_CD, nullToEmpty(rs.getString(REQ_FRT_CHRG_METH_CD)));

                } else {

                    // RTE_GRP
                    baseSoHeader.put(RTE_GRP_NUM, nullToEmpty(rs.getString(RTE_GRP_NUM)));
                    baseSoHeader.put(FRT_CHRG_TO_CD, nullToEmpty(rs.getString(FRT_CHRG_TO_CD)));
                    baseSoHeader.put(FRT_CHRG_METH_CD, nullToEmpty(rs.getString(FRT_CHRG_METH_CD)));
                    baseSoHeader.put(SHPG_SVC_LVL_CD, nullToEmpty(rs.getString(SHPG_SVC_LVL_CD)));
                    baseSoHeader.put(SHPG_MODE_CD, nullToEmpty(rs.getString(SHPG_MODE_CD)));
                    baseSoHeader.put(PSD_DT, nullToEmpty(rs.getString(PSD_DT)));
                    baseSoHeader.put(PDD_DT, nullToEmpty(rs.getString(PDD_DT)));
                }

                // Ship-To
                // SHPG_PLN
                baseSoHeader.put(SHIP_TO_LOC_NM, nullToEmpty(rs.getString(SHIP_TO_LOC_NM)));
                baseSoHeader.put(SHIP_TO_ADDL_LOC_NM, nullToEmpty(rs.getString(SHIP_TO_ADDL_LOC_NM)));
                baseSoHeader.put(SHIP_TO_FIRST_LINE_ADDR, nullToEmpty(rs.getString(SHIP_TO_FIRST_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_SCD_LINE_ADDR, nullToEmpty(rs.getString(SHIP_TO_SCD_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_THIRD_LINE_ADDR, nullToEmpty(rs.getString(SHIP_TO_THIRD_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_FRTH_LINE_ADDR, nullToEmpty(rs.getString(SHIP_TO_FRTH_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(SHIP_TO_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(SHIP_TO_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(SHIP_TO_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(SHIP_TO_CTY_ADDR, nullToEmpty(rs.getString(SHIP_TO_CTY_ADDR)));
                baseSoHeader.put(SHIP_TO_ST_CD, nullToEmpty(rs.getString(SHIP_TO_ST_CD)));
                baseSoHeader.put(SHIP_TO_POST_CD, nullToEmpty(rs.getString(SHIP_TO_POST_CD)));
                baseSoHeader.put(SHIP_TO_CTRY_CD, nullToEmpty(rs.getString(SHIP_TO_CTRY_CD)));
                // SHIP_TO_CUST
                baseSoHeader.put(SHIP_LOC_NM, nullToEmpty(rs.getString(SHIP_LOC_NM)));
                baseSoHeader.put(SHIP_ADDL_LOC_NM, nullToEmpty(rs.getString(SHIP_ADDL_LOC_NM)));
                baseSoHeader.put(SHIP_FIRST_LINE_ADDR, nullToEmpty(rs.getString(SHIP_FIRST_LINE_ADDR)));
                baseSoHeader.put(SHIP_SCD_LINE_ADDR, nullToEmpty(rs.getString(SHIP_SCD_LINE_ADDR)));
                baseSoHeader.put(SHIP_THIRD_LINE_ADDR, nullToEmpty(rs.getString(SHIP_THIRD_LINE_ADDR)));
                baseSoHeader.put(SHIP_FRTH_LINE_ADDR, nullToEmpty(rs.getString(SHIP_FRTH_LINE_ADDR)));
                baseSoHeader.put(SHIP_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(SHIP_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(SHIP_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(SHIP_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(SHIP_CTRY_CD, nullToEmpty(rs.getString(SHIP_CTRY_CD)));
                baseSoHeader.put(SHIP_ST_CD, nullToEmpty(rs.getString(SHIP_ST_CD)));
                baseSoHeader.put(SHIP_CTY_ADDR, nullToEmpty(rs.getString(SHIP_CTY_ADDR)));
                baseSoHeader.put(SHIP_TEL_NUM, nullToEmpty(rs.getString(SHIP_TEL_NUM)));
                baseSoHeader.put(SHIP_POST_CD, nullToEmpty(rs.getString(SHIP_POST_CD)));

                // Sell-To
                // SELL_TO_CUST
                baseSoHeader.put(SELL_LOC_NM, nullToEmpty(rs.getString(SELL_LOC_NM)));
                baseSoHeader.put(SELL_ADDL_LOC_NM, nullToEmpty(rs.getString(SELL_ADDL_LOC_NM)));
                baseSoHeader.put(SELL_FIRST_LINE_ADDR, nullToEmpty(rs.getString(SELL_FIRST_LINE_ADDR)));
                baseSoHeader.put(SELL_SCD_LINE_ADDR, nullToEmpty(rs.getString(SELL_SCD_LINE_ADDR)));
                baseSoHeader.put(SELL_THIRD_LINE_ADDR, nullToEmpty(rs.getString(SELL_THIRD_LINE_ADDR)));
                baseSoHeader.put(SELL_FRTH_LINE_ADDR, nullToEmpty(rs.getString(SELL_FRTH_LINE_ADDR)));
                baseSoHeader.put(SELL_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(SELL_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(SELL_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(SELL_SCD_REF_CMNT_TXT)));
                // 12/06/2010 M.Takahashi add start
                baseSoHeader.put(SELL_TO_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(SELL_TO_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(SELL_TO_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(SELL_TO_SCD_REF_CMNT_TXT)));
                // 12/06/2010 M.Takahashi add start   
                baseSoHeader.put(SELL_CTRY_CD, nullToEmpty(rs.getString(SELL_CTRY_CD)));
                baseSoHeader.put(SELL_ST_CD, nullToEmpty(rs.getString(SELL_ST_CD)));
                baseSoHeader.put(SELL_CTY_ADDR, nullToEmpty(rs.getString(SELL_CTY_ADDR)));
                baseSoHeader.put(SELL_TEL_NUM, nullToEmpty(rs.getString(SELL_TEL_NUM)));
                baseSoHeader.put(SELL_POST_CD, nullToEmpty(rs.getString(SELL_POST_CD)));

                // Bill-To
                // BILL_TO_CUST
                baseSoHeader.put(BILL_LOC_NM, nullToEmpty(rs.getString(BILL_LOC_NM)));
                baseSoHeader.put(BILL_ADDL_LOC_NM, nullToEmpty(rs.getString(BILL_ADDL_LOC_NM)));
                baseSoHeader.put(BILL_FIRST_LINE_ADDR, nullToEmpty(rs.getString(BILL_FIRST_LINE_ADDR)));
                baseSoHeader.put(BILL_SCD_LINE_ADDR, nullToEmpty(rs.getString(BILL_SCD_LINE_ADDR)));
                baseSoHeader.put(BILL_THIRD_LINE_ADDR, nullToEmpty(rs.getString(BILL_THIRD_LINE_ADDR)));
                baseSoHeader.put(BILL_FRTH_LINE_ADDR, nullToEmpty(rs.getString(BILL_FRTH_LINE_ADDR)));
                baseSoHeader.put(BILL_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(BILL_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(BILL_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(BILL_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(BILL_CTRY_CD, nullToEmpty(rs.getString(BILL_CTRY_CD)));
                baseSoHeader.put(BILL_ST_CD, nullToEmpty(rs.getString(BILL_ST_CD)));
                baseSoHeader.put(BILL_CTY_ADDR, nullToEmpty(rs.getString(BILL_CTY_ADDR)));
                baseSoHeader.put(BILL_TEL_NUM, nullToEmpty(rs.getString(BILL_TEL_NUM)));
                baseSoHeader.put(BILL_POST_CD, nullToEmpty(rs.getString(BILL_POST_CD)));

                // Return-To
                // RTRN_TO
                baseSoHeader.put(RTRN_LOC_NM, nullToEmpty(rs.getString(RTRN_LOC_NM)));
                baseSoHeader.put(RTRN_ADDL_LOC_NM, nullToEmpty(rs.getString(RTRN_ADDL_LOC_NM)));
                baseSoHeader.put(RTRN_FIRST_LINE_ADDR, nullToEmpty(rs.getString(RTRN_FIRST_LINE_ADDR)));
                baseSoHeader.put(RTRN_SCD_LINE_ADDR, nullToEmpty(rs.getString(RTRN_SCD_LINE_ADDR)));
                baseSoHeader.put(RTRN_THIRD_LINE_ADDR, nullToEmpty(rs.getString(RTRN_THIRD_LINE_ADDR)));
                baseSoHeader.put(RTRN_FRTH_LINE_ADDR, nullToEmpty(rs.getString(RTRN_FRTH_LINE_ADDR)));
                baseSoHeader.put(RTRN_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(RTRN_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(RTRN_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(RTRN_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(RTRN_CTRY_CD, nullToEmpty(rs.getString(RTRN_CTRY_CD)));
                baseSoHeader.put(RTRN_ST_CD, nullToEmpty(rs.getString(RTRN_ST_CD)));
                baseSoHeader.put(RTRN_CTY_ADDR, nullToEmpty(rs.getString(RTRN_CTY_ADDR)));
                baseSoHeader.put(RTRN_TEL_NUM, nullToEmpty(rs.getString(RTRN_TEL_NUM)));
                baseSoHeader.put(RTRN_POST_CD, nullToEmpty(rs.getString(RTRN_POST_CD)));

                // Check Item
                // ASN_SHIP_TO_CUST
                baseSoHeader.put(ASN_SHIP_TO_CUST_CD, nullToEmpty(rs.getString(ASN_SHIP_TO_CUST_CD)));
                // SHIP_TO_CUST
                baseSoHeader.put(SHIP_SHIP_TO_CUST_CD, nullToEmpty(rs.getString(SHIP_SHIP_TO_CUST_CD)));
                // SELL_TO_CUST
                baseSoHeader.put(SELL_SELL_TO_CUST_CD, nullToEmpty(rs.getString(SELL_SELL_TO_CUST_CD)));
                // BILL_TO_CUST
                baseSoHeader.put(BILL_BILL_TO_CUST_CD, nullToEmpty(rs.getString(BILL_BILL_TO_CUST_CD)));
                // RTRN_TO
                baseSoHeader.put(RTRN_SELL_TO_CUST_CD, nullToEmpty(rs.getString(RTRN_SELL_TO_CUST_CD)));

                // RTL_SWH
                baseSoHeader.put(RTL_WH_CD, nullToEmpty(rs.getString(RTL_WH_CD)));

                // Contact
                baseSoHeader.put(BILL_TO_CTAC_PTNR_PSN_NM, nullToEmpty(rs.getString(BILL_TO_CTAC_PTNR_PSN_NM)));
                baseSoHeader.put(SELL_TO_CTAC_PTNR_PSN_NM, nullToEmpty(rs.getString(SELL_TO_CTAC_PTNR_PSN_NM)));
                baseSoHeader.put(SHIP_TO_CTAC_PTNR_PSN_NM, nullToEmpty(rs.getString(SHIP_TO_CTAC_PTNR_PSN_NM)));
                baseSoHeader.put(BILL_TO_CTAC_PSN_TEL_NUM, nullToEmpty(rs.getString(BILL_TO_CTAC_PSN_TEL_NUM)));
                baseSoHeader.put(SELL_TO_CTAC_PSN_TEL_NUM, nullToEmpty(rs.getString(SELL_TO_CTAC_PSN_TEL_NUM)));
                baseSoHeader.put(SHIP_TO_CTAC_PSN_TEL_NUM, nullToEmpty(rs.getString(SHIP_TO_CTAC_PSN_TEL_NUM)));

                setCbsDropFlg(rs);

                // Check Ship-To
                operationErrFlg = checkGetShipToCust();

                if (operationErrFlg) {

                    return operationErrFlg;
                }

                // Check Sell-To
                operationErrFlg = checkGetSellToCust();

                if (operationErrFlg) {

                    return operationErrFlg;
                }

                // check Bill-To
                operationErrFlg = checkGetBillToCust();

                if (operationErrFlg) {

                    return operationErrFlg;
                }

            } else {

                writeOperationErr(NLZM2004E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }

        /**
         * setCbsDropFlg
         * @param rs ResultSet
         */
        private void setCbsDropFlg(ResultSet rs) {

            if (ZYPConstant.FLG_OFF_N.equals(baseSoHeader.get(DROP_SHIP_FLG))) {

                baseSoHeader.put(CBS_DROP_FLG, ZYPConstant.FLG_OFF_N);

            } else {

                if(ZYPCommonFunc.hasValue(baseSoHeader.get(RTRN_LB_CD))) {

                    baseSoHeader.put(CBS_DROP_FLG, ZYPConstant.FLG_ON_Y);

                } else {

                    baseSoHeader.put(CBS_DROP_FLG, ZYPConstant.FLG_OFF_N);
                }
            }
        }

        /**
         * checkGetShipToCust
         * @return Boolean
         */
        private boolean checkGetShipToCust() {

            boolean operationErrFlg = false;

            if (ZYPConstant.FLG_OFF_N.equals(baseSoHeader.get(DROP_SHIP_FLG)) && !ZYPCommonFunc.hasValue(baseSoHeader.get(SHIP_SHIP_TO_CUST_CD))) {

                writeOperationErr(NLZM2023E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }

        /**
         * checkGetSellToCust
         * @return Boolean
         */
        private boolean checkGetSellToCust() {

            boolean operationErrFlg = false;

            if (!ZYPCommonFunc.hasValue(baseSoHeader.get(SELL_SELL_TO_CUST_CD))) {

                writeOperationErr(NLZM2024E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }

        /**
         * checkGetBillToCust
         * @return Boolean
         */
        private boolean checkGetBillToCust() {

            boolean operationErrFlg = false;

            if (!ZYPCommonFunc.hasValue(baseSoHeader.get(BILL_BILL_TO_CUST_CD))) {

                writeOperationErr(NLZM2025E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }
    }

    /**
     * Get Base Shipping Order Header DC Transfer type
     */
    private class GetBaseSoHdrDcTransf extends S21SsmBooleanResultSetHandlerSupport {

        /** S21ApiMessageMap */
        private S21ApiMessageMap msgMap;

        /** Base Shipping Order Header */
        private Map<String, String> baseSoHeader;

        public GetBaseSoHdrDcTransf(S21ApiMessageMap msgMap, Map<String, String> baseSoHeader) {

            this.msgMap = msgMap;
            this.baseSoHeader = baseSoHeader;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            boolean operationErrFlg = false;

            NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
            String shpgFrceFlg = param.shpgFrceFlg.getValue();
            // 06/22/2010 D.Fukaya append start
            String sceOrdTpCd = param.sceOrdTpCd.getValue();
            // 06/22/2010 D.Fukaya append end

            if (rs.next()) {

                // SHPG_PLN
                baseSoHeader.put(TRX_SRC_TP_CD, nullToEmpty(rs.getString(TRX_SRC_TP_CD)));
                baseSoHeader.put(TRX_HDR_NUM, nullToEmpty(rs.getString(TRX_HDR_NUM)));
                baseSoHeader.put(SHIP_TO_CUST_CD, nullToEmpty(rs.getString(SHIP_TO_CUST_CD)));
                baseSoHeader.put(SELL_TO_CUST_CD, nullToEmpty(rs.getString(SELL_TO_CUST_CD)));
                baseSoHeader.put(RDD_DT, nullToEmpty(rs.getString(RDD_DT)));
                baseSoHeader.put(RSD_DT, nullToEmpty(rs.getString(RSD_DT)));
                baseSoHeader.put(INVTY_LOC_CD, nullToEmpty(rs.getString(INVTY_LOC_CD)));
                baseSoHeader.put(CARR_CD, nullToEmpty(rs.getString(CARR_CD)));

                if (ZYPConstant.FLG_ON_Y.equals(shpgFrceFlg) || SCE_ORD_TP.DC_TRANSFER_LOSS.equals(sceOrdTpCd)) {

                    // SHPG_PLN
                    baseSoHeader.put(ORIG_SHPG_SVC_LVL_CD, nullToEmpty(rs.getString(ORIG_SHPG_SVC_LVL_CD)));

                } else {

                    // RTE_GRP
                    baseSoHeader.put(RTE_GRP_NUM, nullToEmpty(rs.getString(RTE_GRP_NUM)));
                    baseSoHeader.put(SHPG_SVC_LVL_CD, nullToEmpty(rs.getString(SHPG_SVC_LVL_CD)));
                    baseSoHeader.put(SHPG_MODE_CD, nullToEmpty(rs.getString(SHPG_MODE_CD)));
                    baseSoHeader.put(PSD_DT, nullToEmpty(rs.getString(PSD_DT)));
                    baseSoHeader.put(PDD_DT, nullToEmpty(rs.getString(PDD_DT)));
                }

                // Ship-To, Sell-To
                // WH
                baseSoHeader.put(WH_LOC_NM, nullToEmpty(rs.getString(WH_LOC_NM)));
                baseSoHeader.put(WH_ADDL_LOC_NM, nullToEmpty(rs.getString(WH_ADDL_LOC_NM)));
                baseSoHeader.put(WH_FIRST_LINE_ADDR, nullToEmpty(rs.getString(WH_FIRST_LINE_ADDR)));
                baseSoHeader.put(WH_SCD_LINE_ADDR, nullToEmpty(rs.getString(WH_SCD_LINE_ADDR)));
                baseSoHeader.put(WH_THIRD_LINE_ADDR, nullToEmpty(rs.getString(WH_THIRD_LINE_ADDR)));
                baseSoHeader.put(WH_FRTH_LINE_ADDR, nullToEmpty(rs.getString(WH_FRTH_LINE_ADDR)));
                baseSoHeader.put(WH_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(WH_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(WH_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(WH_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(WH_CTRY_CD, nullToEmpty(rs.getString(WH_CTRY_CD)));
                baseSoHeader.put(WH_ST_CD, nullToEmpty(rs.getString(WH_ST_CD)));
                baseSoHeader.put(WH_CTY_ADDR, nullToEmpty(rs.getString(WH_CTY_ADDR)));
                baseSoHeader.put(WH_TEL_NUM, nullToEmpty(rs.getString(WH_TEL_NUM)));
                baseSoHeader.put(WH_POST_CD, nullToEmpty(rs.getString(WH_POST_CD)));
                baseSoHeader.put(SHIP_TO_SELL_TO_CUST_CD, nullToEmpty(rs.getString(SHIP_TO_SELL_TO_CUST_CD)));
                baseSoHeader.put(RS2_RTL_WH_CD, nullToEmpty(rs.getString(RS2_RTL_WH_CD)));
                // QC#5728 Add start 2016/3/18
                baseSoHeader.put(RTL_WH_CD, nullToEmpty(rs.getString(RTL_WH_CD)));
                // QC#5728 Add end 2016/3/18
                baseSoHeader.put(WH_WH_CD, nullToEmpty(rs.getString(WH_WH_CD)));

                // START 2018/04/02 S.Katsuma [QC#25143,ADD]
                // SHPG_PLN(DropShip)
                baseSoHeader.put(SHIP_TO_LOC_NM, nullToEmpty(rs.getString(WH_LOC_NM)));
                baseSoHeader.put(SHIP_TO_ADDL_LOC_NM, nullToEmpty(rs.getString(WH_ADDL_LOC_NM)));
                baseSoHeader.put(SHIP_TO_FIRST_LINE_ADDR, nullToEmpty(rs.getString(WH_FIRST_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_SCD_LINE_ADDR, nullToEmpty(rs.getString(WH_SCD_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_THIRD_LINE_ADDR, nullToEmpty(rs.getString(WH_THIRD_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_FRTH_LINE_ADDR, nullToEmpty(rs.getString(WH_FRTH_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(WH_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(SHIP_TO_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(WH_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(SHIP_TO_CTY_ADDR, nullToEmpty(rs.getString(WH_CTY_ADDR)));
                baseSoHeader.put(SHIP_TO_ST_CD, nullToEmpty(rs.getString(WH_ST_CD)));
                baseSoHeader.put(SHIP_TO_POST_CD, nullToEmpty(rs.getString(WH_POST_CD)));
                baseSoHeader.put(SHIP_TO_CTRY_CD, nullToEmpty(rs.getString(WH_CTRY_CD)));
                baseSoHeader.put(SHIP_TO_CUST_CD, nullToEmpty(rs.getString(WH_WH_CD)));
                baseSoHeader.put(DROP_SHIP_FLG, nullToEmpty(rs.getString(DROP_SHIP_FLG)));
                // END 2018/04/02 S.Katsuma [QC#25143,ADD]

                // Contact
                if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {

                    baseSoHeader.put(SHIP_TO_CTAC_PTNR_PSN_NM, nullToEmpty(rs.getString(SHIP_TO_CTAC_PTNR_PSN_NM)));
                }

                // Vendor
                baseSoHeader.put(VND_VND_CD, nullToEmpty(rs.getString(VND_VND_CD)));
                baseSoHeader.put(VND_LOC_NM, nullToEmpty(rs.getString(VND_LOC_NM)));
                baseSoHeader.put(VND_ADDL_LOC_NM, nullToEmpty(rs.getString(VND_ADDL_LOC_NM)));
                baseSoHeader.put(VND_FIRST_LINE_ADDR, nullToEmpty(rs.getString(VND_FIRST_LINE_ADDR)));
                baseSoHeader.put(VND_SCD_LINE_ADDR, nullToEmpty(rs.getString(VND_SCD_LINE_ADDR)));
                baseSoHeader.put(VND_THIRD_LINE_ADDR, nullToEmpty(rs.getString(VND_THIRD_LINE_ADDR)));
                baseSoHeader.put(VND_FRTH_LINE_ADDR, nullToEmpty(rs.getString(VND_FRTH_LINE_ADDR)));
                baseSoHeader.put(VND_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(VND_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(VND_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(VND_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(VND_CTRY_CD, nullToEmpty(rs.getString(VND_CTRY_CD)));
                baseSoHeader.put(VND_ST_CD, nullToEmpty(rs.getString(VND_ST_CD)));
                baseSoHeader.put(VND_CTY_ADDR, nullToEmpty(rs.getString(VND_CTY_ADDR)));
                baseSoHeader.put(VND_TEL_NUM, nullToEmpty(rs.getString(VND_TEL_NUM)));
                baseSoHeader.put(VND_POST_CD, nullToEmpty(rs.getString(VND_POST_CD)));

                // QC#54989 Add Start
                // Bill-To
                // BILL_TO_CUST
                if (condInsSoCustDtlBillTo(nullToEmpty(rs.getString(RTL_WH_CD)))) {
	                baseSoHeader.put(BILL_LOC_NM, nullToEmpty(rs.getString(BILL_LOC_NM)));
	                baseSoHeader.put(BILL_ADDL_LOC_NM, nullToEmpty(rs.getString(BILL_ADDL_LOC_NM)));
	                baseSoHeader.put(BILL_FIRST_LINE_ADDR, nullToEmpty(rs.getString(BILL_FIRST_LINE_ADDR)));
	                baseSoHeader.put(BILL_SCD_LINE_ADDR, nullToEmpty(rs.getString(BILL_SCD_LINE_ADDR)));
	                baseSoHeader.put(BILL_THIRD_LINE_ADDR, nullToEmpty(rs.getString(BILL_THIRD_LINE_ADDR)));
	                baseSoHeader.put(BILL_FRTH_LINE_ADDR, nullToEmpty(rs.getString(BILL_FRTH_LINE_ADDR)));
	                baseSoHeader.put(BILL_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(BILL_FIRST_REF_CMNT_TXT)));
	                baseSoHeader.put(BILL_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(BILL_SCD_REF_CMNT_TXT)));
	                baseSoHeader.put(BILL_CTRY_CD, nullToEmpty(rs.getString(BILL_CTRY_CD)));
	                baseSoHeader.put(BILL_ST_CD, nullToEmpty(rs.getString(BILL_ST_CD)));
	                baseSoHeader.put(BILL_CTY_ADDR, nullToEmpty(rs.getString(BILL_CTY_ADDR)));
	                baseSoHeader.put(BILL_TEL_NUM, nullToEmpty(rs.getString(BILL_TEL_NUM)));
	                baseSoHeader.put(BILL_POST_CD, nullToEmpty(rs.getString(BILL_POST_CD)));
                }
                // QC#54989 Add End

                // Exists in SHIP_CUST_CD
                operationErrFlg = checkGetWh();

                if (operationErrFlg) {

                    return operationErrFlg;
                }

                // Check WH or Technician
                if (!SCE_ORD_TP.REFURBISH_EXPENSE.equals(sceOrdTpCd)) {

                    operationErrFlg = checkGetWhOrTech(param, rs.getString(WH_WH_CD));

                    if (operationErrFlg) {

                        return operationErrFlg;
                    }
                }

            } else {

                writeOperationErr(NLZM2004E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }

        private boolean checkGetWh() {

            boolean operationErrFlg = false;

            if (!ZYPCommonFunc.hasValue(baseSoHeader.get(WH_WH_CD))) {

                writeOperationErr(NLZM2017E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }

       // 02/06/2013 OCE WDS Defects#121 ADD START
        private boolean checkGetWhOrTech(NLZC205001PMsg param, String shipToCustCd) {

            boolean operationErrFlg = false;

            Map<String, Object> queryParam = new HashMap<String, Object>(2);
            queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            queryParam.put("invtyLocCd", shipToCustCd);
            queryParam.put("locTpCdWh", LOC_TP_CD_WAREHOUSE);
            queryParam.put("locTpCdTec", LOC_TP_CD_TECHNICIAN);

            if ((Integer) ssmBatchClient.queryObject("getWhOrTecCountDcTran", queryParam) == 0) {

                writeOperationErr(NLZM2017E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }
        // 02/06/2013 OCE WDS Defects#121 ADD E N D
    }

    /**
     * Get Base Shipping Order Header Inventry type (!=DC Transfer)
     */
    private class GetBaseSoHdrInvty extends S21SsmBooleanResultSetHandlerSupport {

        /** S21ApiMessageMap */
        private S21ApiMessageMap msgMap;

        /** Base Shipping Order Header */
        private Map<String, String> baseSoHeader;

        public GetBaseSoHdrInvty(S21ApiMessageMap msgMap, Map<String, String> baseSoHeader) {

            this.msgMap = msgMap;
            this.baseSoHeader = baseSoHeader;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            boolean operationErrFlg = false;

            if (rs.next()) {

                // SO Header
                // SHPG_PLN
                baseSoHeader.put(TRX_SRC_TP_CD, nullToEmpty(rs.getString(TRX_SRC_TP_CD)));
                baseSoHeader.put(TRX_HDR_NUM, nullToEmpty(rs.getString(TRX_HDR_NUM)));
                baseSoHeader.put(INVTY_LOC_CD, nullToEmpty(rs.getString(INVTY_LOC_CD)));
                baseSoHeader.put(SELL_TO_CUST_CD, nullToEmpty(rs.getString(SELL_TO_CUST_CD)));
                baseSoHeader.put(SHIP_TO_CUST_CD, nullToEmpty(rs.getString(SHIP_TO_CUST_CD)));
                /* 07/03/2017 CITS M.Naito QC#19682 START */
                baseSoHeader.put(CARR_CD, nullToEmpty(rs.getString(CARR_CD)));
                /* 07/03/2017 CITS M.Naito QC#19682 END */
                // Add Start 2020/01/28 QC#55628
                baseSoHeader.put(ORIG_SHPG_SVC_LVL_CD, nullToEmpty(rs.getString(ORIG_SHPG_SVC_LVL_CD)));
                // Add End 2020/01/28 QC#55628

                // Ship-To, Sell-To
                // WH
                baseSoHeader.put(WH_LOC_NM, nullToEmpty(rs.getString(WH_LOC_NM)));
                baseSoHeader.put(WH_ADDL_LOC_NM, nullToEmpty(rs.getString(WH_ADDL_LOC_NM)));
                baseSoHeader.put(WH_FIRST_LINE_ADDR, nullToEmpty(rs.getString(WH_FIRST_LINE_ADDR)));
                baseSoHeader.put(WH_SCD_LINE_ADDR, nullToEmpty(rs.getString(WH_SCD_LINE_ADDR)));
                baseSoHeader.put(WH_THIRD_LINE_ADDR, nullToEmpty(rs.getString(WH_THIRD_LINE_ADDR)));
                baseSoHeader.put(WH_FRTH_LINE_ADDR, nullToEmpty(rs.getString(WH_FRTH_LINE_ADDR)));
                baseSoHeader.put(WH_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(WH_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(WH_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(WH_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(WH_CTRY_CD, nullToEmpty(rs.getString(WH_CTRY_CD)));
                baseSoHeader.put(WH_ST_CD, nullToEmpty(rs.getString(WH_ST_CD)));
                baseSoHeader.put(WH_CTY_ADDR, nullToEmpty(rs.getString(WH_CTY_ADDR)));
                baseSoHeader.put(WH_TEL_NUM, nullToEmpty(rs.getString(WH_TEL_NUM)));
                baseSoHeader.put(WH_POST_CD, nullToEmpty(rs.getString(WH_POST_CD)));
                baseSoHeader.put(RTL_WH_CD, nullToEmpty(rs.getString(RTL_WH_CD)));
                baseSoHeader.put(RS2_RTL_WH_CD, nullToEmpty(rs.getString(RS2_RTL_WH_CD)));
                baseSoHeader.put(INVTY_TO_SELL_TO_CUST_CD, nullToEmpty(rs.getString(INVTY_TO_SELL_TO_CUST_CD)));
                baseSoHeader.put(SHIP_TO_SELL_TO_CUST_CD, nullToEmpty(rs.getString(SHIP_TO_SELL_TO_CUST_CD)));
                baseSoHeader.put(WH_WH_CD, nullToEmpty(rs.getString(WH_WH_CD)));

                // START 2018/05/07 S.Katsuma [QC#25389,ADD]
                // SHPG_PLN(DropShip)
                baseSoHeader.put(SHIP_TO_LOC_NM, nullToEmpty(rs.getString(WH_LOC_NM)));
                baseSoHeader.put(SHIP_TO_ADDL_LOC_NM, nullToEmpty(rs.getString(WH_ADDL_LOC_NM)));
                baseSoHeader.put(SHIP_TO_FIRST_LINE_ADDR, nullToEmpty(rs.getString(WH_FIRST_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_SCD_LINE_ADDR, nullToEmpty(rs.getString(WH_SCD_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_THIRD_LINE_ADDR, nullToEmpty(rs.getString(WH_THIRD_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_FRTH_LINE_ADDR, nullToEmpty(rs.getString(WH_FRTH_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(WH_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(SHIP_TO_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(WH_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(SHIP_TO_CTY_ADDR, nullToEmpty(rs.getString(WH_CTY_ADDR)));
                baseSoHeader.put(SHIP_TO_ST_CD, nullToEmpty(rs.getString(WH_ST_CD)));
                baseSoHeader.put(SHIP_TO_POST_CD, nullToEmpty(rs.getString(WH_POST_CD)));
                baseSoHeader.put(SHIP_TO_CTRY_CD, nullToEmpty(rs.getString(WH_CTRY_CD)));
                baseSoHeader.put(SHIP_TO_CUST_CD, nullToEmpty(rs.getString(WH_WH_CD)));
                baseSoHeader.put(DROP_SHIP_FLG, nullToEmpty(rs.getString(DROP_SHIP_FLG)));
                // END 2018/05/07 S.Katsuma [QC#25389,ADD]

                // QC#54989 Add Start
                if (condInsSoCustDtlBillTo((String)rs.getString(RTL_WH_CD))) {
                    String invtyLocCd = nullToEmpty(rs.getString(INVTY_LOC_CD));
                    String shipToCustCd = nullToEmpty(rs.getString(SHIP_TO_CUST_CD));
                    if (!S21StringUtil.isEquals(invtyLocCd, shipToCustCd))  {
                        baseSoHeader.put(SHIP_TO_CUST_CD, nullToEmpty(shipToCustCd));
                        // SELL_TO Get By Ship_To
                        SHIP_TO_CUSTTMsg shipToCustTMsg = getShipToCust4DI(shipToCustCd);
                        if (shipToCustTMsg != null && shipToCustTMsg.sellToCustCd != null) {
                            baseSoHeader.put(SELL_TO_CUST_CD, shipToCustTMsg.sellToCustCd.getValue());
                        }
                    }

                    // Bill-To
                    // BILL_TO_CUST
                    BILL_TO_CUSTTMsgArray tmsgArray = getBillToCustInfo((String)rs.getString(SHIP_TO_CUST_CD), (String)rs.getString(SELL_TO_CUST_CD));
                    if (tmsgArray != null && tmsgArray.getValidCount() > 0) {
                        baseSoHeader.put(BILL_LOC_NM, nullToEmpty(tmsgArray.no(0).locNm.getValue()));
                        baseSoHeader.put(BILL_ADDL_LOC_NM, nullToEmpty(tmsgArray.no(0).addlLocNm.getValue()));
                        baseSoHeader.put(BILL_FIRST_LINE_ADDR, nullToEmpty(tmsgArray.no(0).firstLineAddr.getValue()));
                        baseSoHeader.put(BILL_SCD_LINE_ADDR, nullToEmpty(tmsgArray.no(0).scdLineAddr.getValue()));
                        baseSoHeader.put(BILL_THIRD_LINE_ADDR, nullToEmpty(tmsgArray.no(0).thirdLineAddr.getValue()));
                        baseSoHeader.put(BILL_FRTH_LINE_ADDR, nullToEmpty(tmsgArray.no(0).frthLineAddr.getValue()));
                        baseSoHeader.put(BILL_FIRST_REF_CMNT_TXT, nullToEmpty(tmsgArray.no(0).firstRefCmntTxt.getValue()));
                        baseSoHeader.put(BILL_SCD_REF_CMNT_TXT, nullToEmpty(tmsgArray.no(0).scdRefCmntTxt.getValue()));
                        baseSoHeader.put(BILL_CTRY_CD, nullToEmpty(tmsgArray.no(0).ctryCd.getValue()));
                        baseSoHeader.put(BILL_ST_CD, nullToEmpty(tmsgArray.no(0).stCd.getValue()));
                        baseSoHeader.put(BILL_CTY_ADDR, nullToEmpty(tmsgArray.no(0).ctyAddr.getValue()));
                        baseSoHeader.put(BILL_TEL_NUM, nullToEmpty(tmsgArray.no(0).telNum.getValue()));
                        baseSoHeader.put(BILL_POST_CD, nullToEmpty(tmsgArray.no(0).postCd.getValue()));
                    }
                }
                // QC#54989 Add End

                // Check WH
                operationErrFlg = checkGetWh();

                if (operationErrFlg) {

                    return operationErrFlg;
                }

            } else {

                writeOperationErr(NLZM2004E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }

        private boolean checkGetWh() {

            boolean operationErrFlg = false;

            if (!ZYPCommonFunc.hasValue(baseSoHeader.get(WH_WH_CD))) {

                writeOperationErr(NLZM2017E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }
    }

    /**
     * Get Base Shipping Order Header Work type
     */
    private class GetBaseSoHdrWrk extends S21SsmBooleanResultSetHandlerSupport {

        /** S21ApiMessageMap */
        private S21ApiMessageMap msgMap;

        /** Base Shipping Order Header */
        private Map<String, String> baseSoHeader;

        public GetBaseSoHdrWrk(S21ApiMessageMap msgMap, Map<String, String> baseSoHeader) {

            this.msgMap = msgMap;
            this.baseSoHeader = baseSoHeader;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            boolean operationErrFlg = false;

            if (rs.next()) {

                // SO Header
                // SHPG_PLN
                baseSoHeader.put(TRX_SRC_TP_CD, nullToEmpty(rs.getString(TRX_SRC_TP_CD)));
                baseSoHeader.put(TRX_HDR_NUM, nullToEmpty(rs.getString(TRX_HDR_NUM)));
                baseSoHeader.put(SHIP_TO_CUST_CD, nullToEmpty(rs.getString(SHIP_TO_CUST_CD)));
                baseSoHeader.put(ORIG_SHPG_SVC_LVL_CD, nullToEmpty(rs.getString(ORIG_SHPG_SVC_LVL_CD)));
                baseSoHeader.put(INVTY_LOC_CD, nullToEmpty(rs.getString(INVTY_LOC_CD)));
                baseSoHeader.put(SELL_TO_CUST_CD, nullToEmpty(rs.getString(SELL_TO_CUST_CD)));
                baseSoHeader.put(SHIP_TO_SELL_TO_CUST_CD, nullToEmpty(rs.getString(SHIP_TO_SELL_TO_CUST_CD)));

                // Ship-To, Sell-To
                // VND
                baseSoHeader.put(VND_LOC_NM, nullToEmpty(rs.getString(VND_LOC_NM)));
                baseSoHeader.put(VND_ADDL_LOC_NM, nullToEmpty(rs.getString(VND_ADDL_LOC_NM)));
                baseSoHeader.put(VND_FIRST_LINE_ADDR, nullToEmpty(rs.getString(VND_FIRST_LINE_ADDR)));
                baseSoHeader.put(VND_SCD_LINE_ADDR, nullToEmpty(rs.getString(VND_SCD_LINE_ADDR)));
                baseSoHeader.put(VND_THIRD_LINE_ADDR, nullToEmpty(rs.getString(VND_THIRD_LINE_ADDR)));
                baseSoHeader.put(VND_FRTH_LINE_ADDR, nullToEmpty(rs.getString(VND_FRTH_LINE_ADDR)));
                baseSoHeader.put(VND_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(VND_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(VND_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(VND_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(VND_CTRY_CD, nullToEmpty(rs.getString(VND_CTRY_CD)));
                baseSoHeader.put(VND_ST_CD, nullToEmpty(rs.getString(VND_ST_CD)));
                baseSoHeader.put(VND_CTY_ADDR, nullToEmpty(rs.getString(VND_CTY_ADDR)));
                baseSoHeader.put(VND_TEL_NUM, nullToEmpty(rs.getString(VND_TEL_NUM)));
                baseSoHeader.put(VND_POST_CD, nullToEmpty(rs.getString(VND_POST_CD)));

                // QC#54989 Add Start
                // Bill-To
                // BILL_TO_CUST
                if (condInsSoCustDtlBillTo(nullToEmpty(rs.getString(RTL_WH_CD)))) {
                    BILL_TO_CUSTTMsgArray tmsgArray = getBillToCustInfo((String)rs.getString(SHIP_TO_CUST_CD), (String)rs.getString(SELL_TO_CUST_CD));
                    if (tmsgArray != null && tmsgArray.getValidCount() > 0) {

                        baseSoHeader.put(BILL_LOC_NM, nullToEmpty(tmsgArray.no(0).locNm.getValue()));
                        baseSoHeader.put(BILL_ADDL_LOC_NM, nullToEmpty(tmsgArray.no(0).addlLocNm.getValue()));
                        baseSoHeader.put(BILL_FIRST_LINE_ADDR, nullToEmpty(tmsgArray.no(0).firstLineAddr.getValue()));
                        baseSoHeader.put(BILL_SCD_LINE_ADDR, nullToEmpty(tmsgArray.no(0).scdLineAddr.getValue()));
                        baseSoHeader.put(BILL_THIRD_LINE_ADDR, nullToEmpty(tmsgArray.no(0).thirdLineAddr.getValue()));
                        baseSoHeader.put(BILL_FRTH_LINE_ADDR, nullToEmpty(tmsgArray.no(0).frthLineAddr.getValue()));
                        baseSoHeader.put(BILL_FIRST_REF_CMNT_TXT, nullToEmpty(tmsgArray.no(0).firstRefCmntTxt.getValue()));
                        baseSoHeader.put(BILL_SCD_REF_CMNT_TXT, nullToEmpty(tmsgArray.no(0).scdRefCmntTxt.getValue()));
                        baseSoHeader.put(BILL_CTRY_CD, nullToEmpty(tmsgArray.no(0).ctryCd.getValue()));
                        baseSoHeader.put(BILL_ST_CD, nullToEmpty(tmsgArray.no(0).stCd.getValue()));
                        baseSoHeader.put(BILL_CTY_ADDR, nullToEmpty(tmsgArray.no(0).ctyAddr.getValue()));
                        baseSoHeader.put(BILL_TEL_NUM, nullToEmpty(tmsgArray.no(0).telNum.getValue()));
                        baseSoHeader.put(BILL_POST_CD, nullToEmpty(tmsgArray.no(0).postCd.getValue()));
                    }
                }

                // QC#54989 Add End
                baseSoHeader.put(RTL_WH_CD, nullToEmpty(rs.getString(RTL_WH_CD)));
                baseSoHeader.put(RS2_RTL_WH_CD, nullToEmpty(rs.getString(RS2_RTL_WH_CD)));

                // VND
                baseSoHeader.put(VND_VND_CD, nullToEmpty(rs.getString(VND_VND_CD)));

            } else {
                writeOperationErr(NLZM2004E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }
    }

    /**
     * Get Base Shipping Order Header Export
     */
    private class GetBaseSoHdrExpt extends S21SsmBooleanResultSetHandlerSupport {

        /** S21ApiMessageMap */
        private S21ApiMessageMap msgMap;

        /** Base Shipping Order Header */
        private Map<String, String> baseSoHeader;

        public GetBaseSoHdrExpt(S21ApiMessageMap msgMap, Map<String, String> baseSoHeader) {

            this.msgMap = msgMap;
            this.baseSoHeader = baseSoHeader;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            boolean operationErrFlg = false;

            NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
            String shpgFrceFlg = param.shpgFrceFlg.getValue();

            if (rs.next()) {

                baseSoHeader.put(SELL_TO_CUST_CD, nullToEmpty(rs.getString(SELL_TO_CUST_CD)));
                baseSoHeader.put(BILL_TO_CUST_CD, nullToEmpty(rs.getString(BILL_TO_CUST_CD)));
                baseSoHeader.put(SHIP_TO_CUST_CD, nullToEmpty(rs.getString(SHIP_TO_CUST_CD)));
                baseSoHeader.put(INVTY_LOC_CD, nullToEmpty(rs.getString(INVTY_LOC_CD)));
                baseSoHeader.put(TRX_HDR_NUM, nullToEmpty(rs.getString(TRX_HDR_NUM)));
                baseSoHeader.put(CUST_ISS_PO_NUM, nullToEmpty(rs.getString(CUST_ISS_PO_NUM)));

                if (!ZYPConstant.FLG_ON_Y.equals(shpgFrceFlg)) {

                    // RTE_GRP
                    baseSoHeader.put(RTE_GRP_NUM, nullToEmpty(rs.getString(RTE_GRP_NUM)));
                    baseSoHeader.put(PSD_DT, nullToEmpty(rs.getString(PSD_DT)));
                }

                baseSoHeader.put(REQ_FRT_CHRG_TO_CD, nullToEmpty(rs.getString(REQ_FRT_CHRG_TO_CD)));
                baseSoHeader.put(REQ_FRT_CHRG_METH_CD, nullToEmpty(rs.getString(REQ_FRT_CHRG_METH_CD)));
                baseSoHeader.put(ORIG_SHPG_SVC_LVL_CD, nullToEmpty(rs.getString(ORIG_SHPG_SVC_LVL_CD)));
                baseSoHeader.put(RSD_DT, nullToEmpty(rs.getString(RSD_DT)));
                baseSoHeader.put(DROP_SHIP_FLG, nullToEmpty(rs.getString(DROP_SHIP_FLG)));
                //baseSoHeader.put(CUST_DEPT_NUM, nullToEmpty(rs.getString(CUST_DEPT_NUM)));

                // EDI_PO_NOTE
                baseSoHeader.put(SO_DEPT_NUM, nullToEmpty(rs.getString(SO_DEPT_NUM)));
                baseSoHeader.put(TRX_SRC_TP_CD, nullToEmpty(rs.getString(TRX_SRC_TP_CD)));
                baseSoHeader.put(SHIP_TO_LOC_NM, nullToEmpty(rs.getString(SHIP_TO_LOC_NM)));
                baseSoHeader.put(SHIP_TO_ADDL_LOC_NM, nullToEmpty(rs.getString(SHIP_TO_ADDL_LOC_NM)));
                baseSoHeader.put(SHIP_TO_FIRST_LINE_ADDR, nullToEmpty(rs.getString(SHIP_TO_FIRST_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_SCD_LINE_ADDR, nullToEmpty(rs.getString(SHIP_TO_SCD_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_THIRD_LINE_ADDR, nullToEmpty(rs.getString(SHIP_TO_THIRD_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_FRTH_LINE_ADDR, nullToEmpty(rs.getString(SHIP_TO_FRTH_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(SHIP_TO_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(SHIP_TO_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(SHIP_TO_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(SHIP_TO_CTY_ADDR, nullToEmpty(rs.getString(SHIP_TO_CTY_ADDR)));
                baseSoHeader.put(SHIP_TO_ST_CD, nullToEmpty(rs.getString(SHIP_TO_ST_CD)));
                baseSoHeader.put(SHIP_TO_POST_CD, nullToEmpty(rs.getString(SHIP_TO_POST_CD)));
                baseSoHeader.put(SHIP_TO_CTRY_CD, nullToEmpty(rs.getString(SHIP_TO_CTRY_CD)));
                baseSoHeader.put(RQST_CARR_CD, nullToEmpty(rs.getString(RQST_CARR_CD)));
                baseSoHeader.put(CARR_ACCT_NUM, nullToEmpty(rs.getString(CARR_ACCT_NUM)));
                baseSoHeader.put(SHIP_SHIP_TO_CUST_CD, nullToEmpty(rs.getString(SHIP_SHIP_TO_CUST_CD)));
                baseSoHeader.put(SHIP_LOC_NM, nullToEmpty(rs.getString(SHIP_LOC_NM)));
                baseSoHeader.put(SHIP_ADDL_LOC_NM, nullToEmpty(rs.getString(SHIP_ADDL_LOC_NM)));
                baseSoHeader.put(SHIP_FIRST_LINE_ADDR, nullToEmpty(rs.getString(SHIP_FIRST_LINE_ADDR)));
                baseSoHeader.put(SHIP_SCD_LINE_ADDR, nullToEmpty(rs.getString(SHIP_SCD_LINE_ADDR)));
                baseSoHeader.put(SHIP_THIRD_LINE_ADDR, nullToEmpty(rs.getString(SHIP_THIRD_LINE_ADDR)));
                baseSoHeader.put(SHIP_FRTH_LINE_ADDR, nullToEmpty(rs.getString(SHIP_FRTH_LINE_ADDR)));
                baseSoHeader.put(SHIP_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(SHIP_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(SHIP_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(SHIP_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(SHIP_CTRY_CD, nullToEmpty(rs.getString(SHIP_CTRY_CD)));
                baseSoHeader.put(SHIP_ST_CD, nullToEmpty(rs.getString(SHIP_ST_CD)));
                baseSoHeader.put(SHIP_CTY_ADDR, nullToEmpty(rs.getString(SHIP_CTY_ADDR)));
                baseSoHeader.put(SHIP_TEL_NUM, nullToEmpty(rs.getString(SHIP_TEL_NUM)));
                baseSoHeader.put(SHIP_POST_CD, nullToEmpty(rs.getString(SHIP_POST_CD)));
                baseSoHeader.put(SELL_SELL_TO_CUST_CD, nullToEmpty(rs.getString(SELL_SELL_TO_CUST_CD)));
                baseSoHeader.put(SELL_LOC_NM, nullToEmpty(rs.getString(SELL_LOC_NM)));
                baseSoHeader.put(SELL_ADDL_LOC_NM, nullToEmpty(rs.getString(SELL_ADDL_LOC_NM)));
                baseSoHeader.put(SELL_FIRST_LINE_ADDR, nullToEmpty(rs.getString(SELL_FIRST_LINE_ADDR)));
                baseSoHeader.put(SELL_SCD_LINE_ADDR, nullToEmpty(rs.getString(SELL_SCD_LINE_ADDR)));
                baseSoHeader.put(SELL_THIRD_LINE_ADDR, nullToEmpty(rs.getString(SELL_THIRD_LINE_ADDR)));
                baseSoHeader.put(SELL_FRTH_LINE_ADDR, nullToEmpty(rs.getString(SELL_FRTH_LINE_ADDR)));
                baseSoHeader.put(SELL_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(SELL_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(SELL_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(SELL_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(SELL_TO_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(SELL_TO_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(SELL_TO_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(SELL_TO_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(SELL_CTRY_CD, nullToEmpty(rs.getString(SELL_CTRY_CD)));
                baseSoHeader.put(SELL_ST_CD, nullToEmpty(rs.getString(SELL_ST_CD)));
                baseSoHeader.put(SELL_CTY_ADDR, nullToEmpty(rs.getString(SELL_CTY_ADDR)));
                baseSoHeader.put(SELL_TEL_NUM, nullToEmpty(rs.getString(SELL_TEL_NUM)));
                baseSoHeader.put(SELL_POST_CD, nullToEmpty(rs.getString(SELL_POST_CD)));
                baseSoHeader.put(BILL_BILL_TO_CUST_CD, nullToEmpty(rs.getString(BILL_BILL_TO_CUST_CD)));
                baseSoHeader.put(ASN_REQ_FLG, nullToEmpty(rs.getString(ASN_REQ_FLG)));
                baseSoHeader.put(PRINT_SCC_LB_FLG, nullToEmpty(rs.getString(PRINT_SCC_LB_FLG)));
                baseSoHeader.put(PRINT_UCC_LB_FLG, nullToEmpty(rs.getString(PRINT_UCC_LB_FLG)));
                baseSoHeader.put(MIX_PLT_ALLW_FLG, nullToEmpty(rs.getString(MIX_PLT_ALLW_FLG)));
                baseSoHeader.put(NON_ASN_UCC_LB_FLG, nullToEmpty(rs.getString(NON_ASN_UCC_LB_FLG)));
                baseSoHeader.put(BILL_LOC_NM, nullToEmpty(rs.getString(BILL_LOC_NM)));
                baseSoHeader.put(BILL_ADDL_LOC_NM, nullToEmpty(rs.getString(BILL_ADDL_LOC_NM)));
                baseSoHeader.put(BILL_FIRST_LINE_ADDR, nullToEmpty(rs.getString(BILL_FIRST_LINE_ADDR)));
                baseSoHeader.put(BILL_SCD_LINE_ADDR, nullToEmpty(rs.getString(BILL_SCD_LINE_ADDR)));
                baseSoHeader.put(BILL_THIRD_LINE_ADDR, nullToEmpty(rs.getString(BILL_THIRD_LINE_ADDR)));
                baseSoHeader.put(BILL_FRTH_LINE_ADDR, nullToEmpty(rs.getString(BILL_FRTH_LINE_ADDR)));
                baseSoHeader.put(BILL_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(BILL_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(BILL_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(BILL_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(BILL_CTRY_CD, nullToEmpty(rs.getString(BILL_CTRY_CD)));
                baseSoHeader.put(BILL_ST_CD, nullToEmpty(rs.getString(BILL_ST_CD)));
                baseSoHeader.put(BILL_CTY_ADDR, nullToEmpty(rs.getString(BILL_CTY_ADDR)));
                baseSoHeader.put(BILL_TEL_NUM, nullToEmpty(rs.getString(BILL_TEL_NUM)));
                baseSoHeader.put(BILL_POST_CD, nullToEmpty(rs.getString(BILL_POST_CD)));
                baseSoHeader.put(RTRN_LB_CD, nullToEmpty(rs.getString(RTRN_LB_CD)));
                baseSoHeader.put(RTRN_SELL_TO_CUST_CD, nullToEmpty(rs.getString(RTRN_SELL_TO_CUST_CD)));
                baseSoHeader.put(RTRN_LOC_NM, nullToEmpty(rs.getString(RTRN_LOC_NM)));
                baseSoHeader.put(RTRN_ADDL_LOC_NM, nullToEmpty(rs.getString(RTRN_ADDL_LOC_NM)));
                baseSoHeader.put(RTRN_FIRST_LINE_ADDR, nullToEmpty(rs.getString(RTRN_FIRST_LINE_ADDR)));
                baseSoHeader.put(RTRN_SCD_LINE_ADDR, nullToEmpty(rs.getString(RTRN_SCD_LINE_ADDR)));
                baseSoHeader.put(RTRN_THIRD_LINE_ADDR, nullToEmpty(rs.getString(RTRN_THIRD_LINE_ADDR)));
                baseSoHeader.put(RTRN_FRTH_LINE_ADDR, nullToEmpty(rs.getString(RTRN_FRTH_LINE_ADDR)));
                baseSoHeader.put(RTRN_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(RTRN_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(RTRN_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(RTRN_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(RTRN_CTRY_CD, nullToEmpty(rs.getString(RTRN_CTRY_CD)));
                baseSoHeader.put(RTRN_ST_CD, nullToEmpty(rs.getString(RTRN_ST_CD)));
                baseSoHeader.put(RTRN_CTY_ADDR, nullToEmpty(rs.getString(RTRN_CTY_ADDR)));
                baseSoHeader.put(RTRN_TEL_NUM, nullToEmpty(rs.getString(RTRN_TEL_NUM)));
                baseSoHeader.put(RTRN_POST_CD, nullToEmpty(rs.getString(RTRN_POST_CD)));
                // ASN_SHIP_TO_CUST
                baseSoHeader.put(ASN_SHIP_TO_CUST_CD, nullToEmpty(rs.getString(ASN_SHIP_TO_CUST_CD)));
                baseSoHeader.put(CPO_SRC_TP_CD, nullToEmpty(rs.getString(CPO_SRC_TP_CD)));

                // Check Item
                baseSoHeader.put(SELL_SELL_TO_CUST_CD, nullToEmpty(rs.getString(SELL_SELL_TO_CUST_CD)));
                baseSoHeader.put(BILL_BILL_TO_CUST_CD, nullToEmpty(rs.getString(BILL_BILL_TO_CUST_CD)));
                baseSoHeader.put(SHIP_SHIP_TO_CUST_CD, nullToEmpty(rs.getString(SHIP_SHIP_TO_CUST_CD)));
                baseSoHeader.put(RTRN_SELL_TO_CUST_CD, nullToEmpty(rs.getString(RTRN_SELL_TO_CUST_CD)));

                baseSoHeader.put(RTL_WH_CD, nullToEmpty(rs.getString(RTL_WH_CD)));

                setCbsDropFlg(rs);

                // Check Sell-To
                operationErrFlg = checkGetSellToCust();
                if (operationErrFlg) {
                    return operationErrFlg;
                }
                // check Bill-To
                operationErrFlg = checkGetBillToCust();
                if (operationErrFlg) {
                    return operationErrFlg;
                }
                // Check Ship-To
                operationErrFlg = checkGetShipToCust();
                if (operationErrFlg) {
                    return operationErrFlg;
                }
            } else {
                writeOperationErr(NLZM2004E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }

        private void setCbsDropFlg(ResultSet rs) {
            if (ZYPConstant.FLG_OFF_N.equals(baseSoHeader.get(DROP_SHIP_FLG))) {
                baseSoHeader.put(CBS_DROP_FLG, ZYPConstant.FLG_OFF_N);
            } else {
                if(ZYPCommonFunc.hasValue(baseSoHeader.get(RTRN_LB_CD))) {
                    baseSoHeader.put(CBS_DROP_FLG, ZYPConstant.FLG_ON_Y);
                } else {
                    baseSoHeader.put(CBS_DROP_FLG, ZYPConstant.FLG_OFF_N);
                }
            }
        }

        private boolean checkGetSellToCust() {

            boolean operationErrFlg = false;

            if (!ZYPCommonFunc.hasValue(baseSoHeader.get(SELL_SELL_TO_CUST_CD))) {
                writeOperationErr(NLZM2024E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }

        private boolean checkGetBillToCust() {

            boolean operationErrFlg = false;

            if (!ZYPCommonFunc.hasValue(baseSoHeader.get(BILL_BILL_TO_CUST_CD))) {
                writeOperationErr(NLZM2025E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }

        private boolean checkGetShipToCust() {

            boolean operationErrFlg = false;

            if (ZYPConstant.FLG_OFF_N.equals(baseSoHeader.get(DROP_SHIP_FLG))
                    && !ZYPCommonFunc.hasValue(baseSoHeader.get(SHIP_SHIP_TO_CUST_CD))) {
                writeOperationErr(NLZM2023E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }
    }

    /**
     * Get Base Shipping Order Header Vendor Return
     */
    private class GetBaseSoHdrVndRtrn extends S21SsmBooleanResultSetHandlerSupport {

        /** S21ApiMessageMap */
        private S21ApiMessageMap msgMap;

        /** Base Shipping Order Header */
        private Map<String, String> baseSoHeader;

        public GetBaseSoHdrVndRtrn(S21ApiMessageMap msgMap, Map<String, String> baseSoHeader) {

            this.msgMap = msgMap;
            this.baseSoHeader = baseSoHeader;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            boolean operationErrFlg = false;

            if (rs.next()) {
                baseSoHeader.put(SELL_TO_CUST_CD, nullToEmpty(rs.getString(SELL_TO_CUST_CD)));
                baseSoHeader.put(BILL_TO_CUST_CD, nullToEmpty(rs.getString(BILL_TO_CUST_CD)));
                baseSoHeader.put(SHIP_TO_CUST_CD, nullToEmpty(rs.getString(SHIP_TO_CUST_CD)));
                baseSoHeader.put(INVTY_LOC_CD, nullToEmpty(rs.getString(INVTY_LOC_CD)));
                baseSoHeader.put(TRX_HDR_NUM, nullToEmpty(rs.getString(TRX_HDR_NUM)));
                baseSoHeader.put(REQ_FRT_CHRG_TO_CD, nullToEmpty(rs.getString(REQ_FRT_CHRG_TO_CD)));
                baseSoHeader.put(REQ_FRT_CHRG_METH_CD, nullToEmpty(rs.getString(REQ_FRT_CHRG_METH_CD)));
                baseSoHeader.put(ORIG_SHPG_SVC_LVL_CD, nullToEmpty(rs.getString(ORIG_SHPG_SVC_LVL_CD)));
                baseSoHeader.put(RSD_DT, nullToEmpty(rs.getString(RSD_DT)));
                baseSoHeader.put(DROP_SHIP_FLG, nullToEmpty(rs.getString(DROP_SHIP_FLG)));
                baseSoHeader.put(TRX_SRC_TP_CD, nullToEmpty(rs.getString(TRX_SRC_TP_CD)));
                baseSoHeader.put(SHIP_TO_LOC_NM, nullToEmpty(rs.getString(SHIP_TO_LOC_NM)));
                baseSoHeader.put(SHIP_TO_ADDL_LOC_NM, nullToEmpty(rs.getString(SHIP_TO_ADDL_LOC_NM)));
                baseSoHeader.put(SHIP_TO_FIRST_LINE_ADDR, nullToEmpty(rs.getString(SHIP_TO_FIRST_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_SCD_LINE_ADDR, nullToEmpty(rs.getString(SHIP_TO_SCD_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_THIRD_LINE_ADDR, nullToEmpty(rs.getString(SHIP_TO_THIRD_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_FRTH_LINE_ADDR, nullToEmpty(rs.getString(SHIP_TO_FRTH_LINE_ADDR)));
                baseSoHeader.put(SHIP_TO_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(SHIP_TO_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(SHIP_TO_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(SHIP_TO_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(SHIP_TO_CTY_ADDR, nullToEmpty(rs.getString(SHIP_TO_CTY_ADDR)));
                baseSoHeader.put(SHIP_TO_ST_CD, nullToEmpty(rs.getString(SHIP_TO_ST_CD)));
                baseSoHeader.put(SHIP_TO_POST_CD, nullToEmpty(rs.getString(SHIP_TO_POST_CD)));
                baseSoHeader.put(SHIP_TO_CTRY_CD, nullToEmpty(rs.getString(SHIP_TO_CTRY_CD)));
                baseSoHeader.put(RQST_CARR_CD, nullToEmpty(rs.getString(RQST_CARR_CD)));
                baseSoHeader.put(CARR_ACCT_NUM, nullToEmpty(rs.getString(CARR_ACCT_NUM)));
                baseSoHeader.put(VND_LOC_NM, nullToEmpty(rs.getString(VND_LOC_NM)));
                baseSoHeader.put(VND_ADDL_LOC_NM, nullToEmpty(rs.getString(VND_ADDL_LOC_NM)));
                baseSoHeader.put(VND_FIRST_LINE_ADDR, nullToEmpty(rs.getString(VND_FIRST_LINE_ADDR)));
                baseSoHeader.put(VND_SCD_LINE_ADDR, nullToEmpty(rs.getString(VND_SCD_LINE_ADDR)));
                baseSoHeader.put(VND_THIRD_LINE_ADDR, nullToEmpty(rs.getString(VND_THIRD_LINE_ADDR)));
                baseSoHeader.put(VND_FRTH_LINE_ADDR, nullToEmpty(rs.getString(VND_FRTH_LINE_ADDR)));
                baseSoHeader.put(VND_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(VND_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(VND_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(VND_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(VND_CTRY_CD, nullToEmpty(rs.getString(VND_CTRY_CD)));
                baseSoHeader.put(VND_ST_CD, nullToEmpty(rs.getString(VND_ST_CD)));
                baseSoHeader.put(VND_CTY_ADDR, nullToEmpty(rs.getString(VND_CTY_ADDR)));
                baseSoHeader.put(VND_TEL_NUM, nullToEmpty(rs.getString(VND_TEL_NUM)));
                baseSoHeader.put(VND_POST_CD, nullToEmpty(rs.getString(VND_POST_CD)));
                baseSoHeader.put(ASN_REQ_FLG, nullToEmpty(rs.getString(ASN_REQ_FLG)));
                baseSoHeader.put(PRINT_SCC_LB_FLG, nullToEmpty(rs.getString(PRINT_SCC_LB_FLG)));
                baseSoHeader.put(PRINT_UCC_LB_FLG, nullToEmpty(rs.getString(PRINT_UCC_LB_FLG)));
                baseSoHeader.put(MIX_PLT_ALLW_FLG, nullToEmpty(rs.getString(MIX_PLT_ALLW_FLG)));
                baseSoHeader.put(NON_ASN_UCC_LB_FLG, nullToEmpty(rs.getString(NON_ASN_UCC_LB_FLG)));
                baseSoHeader.put(BILL_LOC_NM, nullToEmpty(rs.getString(BILL_LOC_NM)));
                baseSoHeader.put(BILL_ADDL_LOC_NM, nullToEmpty(rs.getString(BILL_ADDL_LOC_NM)));
                baseSoHeader.put(BILL_FIRST_LINE_ADDR, nullToEmpty(rs.getString(BILL_FIRST_LINE_ADDR)));
                baseSoHeader.put(BILL_SCD_LINE_ADDR, nullToEmpty(rs.getString(BILL_SCD_LINE_ADDR)));
                baseSoHeader.put(BILL_THIRD_LINE_ADDR, nullToEmpty(rs.getString(BILL_THIRD_LINE_ADDR)));
                baseSoHeader.put(BILL_FRTH_LINE_ADDR, nullToEmpty(rs.getString(BILL_FRTH_LINE_ADDR)));
                baseSoHeader.put(BILL_FIRST_REF_CMNT_TXT, nullToEmpty(rs.getString(BILL_FIRST_REF_CMNT_TXT)));
                baseSoHeader.put(BILL_SCD_REF_CMNT_TXT, nullToEmpty(rs.getString(BILL_SCD_REF_CMNT_TXT)));
                baseSoHeader.put(BILL_CTRY_CD, nullToEmpty(rs.getString(BILL_CTRY_CD)));
                baseSoHeader.put(BILL_ST_CD, nullToEmpty(rs.getString(BILL_ST_CD)));
                baseSoHeader.put(BILL_CTY_ADDR, nullToEmpty(rs.getString(BILL_CTY_ADDR)));
                baseSoHeader.put(BILL_TEL_NUM, nullToEmpty(rs.getString(BILL_TEL_NUM)));
                baseSoHeader.put(BILL_POST_CD, nullToEmpty(rs.getString(BILL_POST_CD)));

                // Check Item
                baseSoHeader.put(VND_RTRN_NUM, nullToEmpty(rs.getString(VND_RTRN_NUM)));
                baseSoHeader.put(VND_VND_CD, nullToEmpty(rs.getString(VND_VND_CD)));
                baseSoHeader.put(BILL_BILL_TO_CUST_CD, nullToEmpty(rs.getString(BILL_BILL_TO_CUST_CD)));

                // Check VND_RTRN_NUM
                operationErrFlg = checkGetVndRtrnNum();
                if (operationErrFlg) {
                    return operationErrFlg;
                }
                // Check VND
                operationErrFlg = checkGetVnd();
                if (operationErrFlg) {
                    return operationErrFlg;
                }

                baseSoHeader.put(RTL_WH_CD, nullToEmpty(rs.getString(RTL_WH_CD)));
            } else {
                writeOperationErr(NLZM2004E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }

        private boolean checkGetVndRtrnNum() {

            boolean operationErrFlg = false;

            if (!ZYPCommonFunc.hasValue(baseSoHeader.get(VND_RTRN_NUM))) {
                writeOperationErr(NLZM2040E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }

        private boolean checkGetVnd() {

            boolean operationErrFlg = false;

            if (!ZYPCommonFunc.hasValue(baseSoHeader.get(VND_VND_CD))) {
                writeOperationErr(NLZM2029E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }
    }

    /**
     * Get Shipping Service Level Code
     */
    private class GetOrigShpgSvcLvlCd extends S21SsmBooleanResultSetHandlerSupport {

        /** S21ApiMessageMap */
        private S21ApiMessageMap msgMap;

        /** Base Shipping Order Header */
        private Map<String, String> baseSoHeader;

        public GetOrigShpgSvcLvlCd(S21ApiMessageMap msgMap, Map<String, String> baseSoHeader) {

            this.msgMap = msgMap;
            this.baseSoHeader = baseSoHeader;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            boolean operationErrFlg = false;

            if (rs.next()) {
                // SHPG_PLN
                baseSoHeader.put(ORIG_SHPG_SVC_LVL_CD, nullToEmpty(rs.getString(ORIG_SHPG_SVC_LVL_CD)));

            } else {
                writeOperationErr(NLZM2004E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }
    }

    /**
     * Get Base Shipping Order Detail CPO type
     */
    private class GetBaseSoDtlCpo extends S21SsmBooleanResultSetHandlerSupport {

        /** S21ApiMessageMap */
        private S21ApiMessageMap msgMap;

        /** Base Shipping Order Detail */
        private SHPG_ORD_DTLTMsg shpgOrdDtlT;

        public GetBaseSoDtlCpo(S21ApiMessageMap msgMap, SHPG_ORD_DTLTMsg shpgOrdDtlT) {

            this.msgMap = msgMap;
            this.shpgOrdDtlT = shpgOrdDtlT;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            boolean operationErrFlg = false;

            NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
            String glblCmpyCd = param.glblCmpyCd.getValue();
            String sceOrdTpCd = param.sceOrdTpCd.getValue();
            String shpgPlnNum = param.shpgPlnNum.getValue();

            if (rs.next()) {

                BigDecimal shpgQty = rs.getBigDecimal(AVAL_SO_QTY);
                BigDecimal rqstOrdQty = rs.getBigDecimal(ORD_QTY);
                BigDecimal inInchVol = rs.getBigDecimal(IN_INCH_VOL);
                BigDecimal inPoundWt = rs.getBigDecimal(IN_POUND_WT);
                BigDecimal unitPrcAmt = rs.getBigDecimal(SP_FUNC_NET_UNIT_PRC_AMT);
                String shpgSerTakeFlg = nullToEmpty(rs.getString(SHPG_SER_TAKE_FLG));
                String cpoSrcTpCd = nullToEmpty(rs.getString(CPO_SRC_TP_CD));
                String cpoOrdTpCd = nullToEmpty(rs.getString(CPO_ORD_TP_CD));

                // 08/23/2010 D.Fukaya modify start
                //BigDecimal totShpgVol = multiplyNullToNull(inInchVol, shpgQty, TOT_SHPG_VOL_DECL_LG);
                BigDecimal totShpgVol = getTotShpgVolInFeet(inInchVol, shpgQty, TOT_SHPG_VOL_DECL_LG);
                // 08/23/2010 D.Fukaya modify end
                totShpgVol = overflowToBorder(totShpgVol, TOT_SHPG_VOL_MAX_LG, TOT_SHPG_VOL_DECL_LG);
                BigDecimal totShpgWt = multiplyNullToNull(inPoundWt, shpgQty, TOT_SHPG_WT_DECL_LG);
                totShpgWt = overflowToBorder(totShpgWt, TOT_SHPG_WT_MAX_LG, TOT_SHPG_WT_DECL_LG);
                BigDecimal shpgPrcAmt = multiplyNullToNull(unitPrcAmt, shpgQty, SHPG_PRC_AMT_DECL_LG);
                shpgPrcAmt = overflowToBorder(shpgPrcAmt, SHPG_PRC_AMT_MAX_LG, SHPG_PRC_AMT_DECL_LG);
                BigDecimal shpgBalQty = subtractNullToNull(rqstOrdQty, shpgQty);

                // Check Serial Number Take Flag
                String serNumTakeFlg = ZYPConstant.FLG_OFF_N;
                if (ZYPConstant.FLG_ON_Y.equals(sceOrdTpSerNumTakeFlg)
                        && ZYPConstant.FLG_ON_Y.equals(shpgSerTakeFlg)
                        && !(CPO_SRC_TP.COMPANY_STORE.equals(cpoSrcTpCd))) {
                    serNumTakeFlg = ZYPConstant.FLG_ON_Y;
                }

                // Check Expense
                String expFlg = ZYPConstant.FLG_OFF_N;
                if (SCE_ORD_TP.REGULAR.equals(sceOrdTpCd)
                        && CPO_ORD_TP.EXPENSE.equals(cpoOrdTpCd)) {
                    expFlg = ZYPConstant.FLG_ON_Y;
                }

                // Set TMsg
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.setMdseNm, rs.getString(SET_MDSE_NM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgPlnNum, shpgPlnNum);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.fromStkStsCd, rs.getString(STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.totShpgVol, totShpgVol);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.totShpgWt, totShpgWt);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgPrcAmt, shpgPrcAmt);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.discPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.unitPrcAmt, unitPrcAmt);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.discUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.rqstOrdQty, rqstOrdQty);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgQty, shpgQty);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shipQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.setShpgQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgBalQty, shpgBalQty);
                // START MOD 04/22/2013 R-WH003
                // back to WS
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgStsCd, SHPG_STS.S_OR_O_PRINTED);
                // E N D MOD 04/22/2013 R-WH003
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.custMdseCd, rs.getString(CUST_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.serNumTakeFlg, serNumTakeFlg);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.inEachQty, rs.getBigDecimal(IN_EACH_QTY));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.setMdseCd, rs.getString(SET_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.mdseCd, rs.getString(MDSE_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxHdrNum, rs.getString(TRX_HDR_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxLineNum, rs.getString(TRX_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.expFlg, expFlg);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.sysSrcCd, rs.getString(SYS_SRC_CD));

                // 11/02/2012 Oce WDS SOL#94 ADD START
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.configItemFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.batNumTakeFlg, ZYPConstant.FLG_OFF_N);
                // 11/02/2012 Oce WDS SOL#94 ADD END
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.rmvConfigFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.preIstlShopRqstFlg, ZYPConstant.FLG_OFF_N);

            } else {
                writeOperationErr(NLZM2004E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }
    }

    /**
     * Get Base Shipping Order Detail Stock Status Cange type
     */
    private class GetBaseSoDtlStkStsChng extends S21SsmBooleanResultSetHandlerSupport {

        /** S21ApiMessageMap */
        private S21ApiMessageMap msgMap;

        /** Base Shipping Order Detail */
        private SHPG_ORD_DTLTMsg shpgOrdDtlT;

        public GetBaseSoDtlStkStsChng(S21ApiMessageMap msgMap, SHPG_ORD_DTLTMsg shpgOrdDtlT) {

            this.msgMap = msgMap;
            this.shpgOrdDtlT = shpgOrdDtlT;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            boolean operationErrFlg = false;

            NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
            String glblCmpyCd = param.glblCmpyCd.getValue();
            String shpgPlnNum = param.shpgPlnNum.getValue();

            if (rs.next()) {

                BigDecimal shpgQty = rs.getBigDecimal(AVAL_SO_QTY);
                BigDecimal inInchVol = rs.getBigDecimal(IN_INCH_VOL);
                BigDecimal inPoundWt = rs.getBigDecimal(IN_POUND_WT);
                BigDecimal unitPrcAmt = rs.getBigDecimal(SP_FUNC_NET_UNIT_PRC_AMT);
                String shpgSerTakeFlg = nullToEmpty(rs.getString(SHPG_SER_TAKE_FLG));

                BigDecimal totShpgVol = getTotShpgVolInFeet(inInchVol, shpgQty, TOT_SHPG_VOL_DECL_LG);
                totShpgVol = overflowToBorder(totShpgVol, TOT_SHPG_VOL_MAX_LG, TOT_SHPG_VOL_DECL_LG);
                BigDecimal totShpgWt = multiplyNullToNull(inPoundWt, shpgQty, TOT_SHPG_WT_DECL_LG);
                totShpgWt = overflowToBorder(totShpgWt, TOT_SHPG_WT_MAX_LG, TOT_SHPG_WT_DECL_LG);
                BigDecimal shpgPrcAmt = multiplyNullToNull(unitPrcAmt, shpgQty, SHPG_PRC_AMT_DECL_LG);
                shpgPrcAmt = overflowToBorder(shpgPrcAmt, SHPG_PRC_AMT_MAX_LG, SHPG_PRC_AMT_DECL_LG);

                // Check Serial Number Take Flag
                String serNumTakeFlg = ZYPConstant.FLG_OFF_N;
                if (ZYPConstant.FLG_ON_Y.equals(sceOrdTpSerNumTakeFlg)
                        && ZYPConstant.FLG_ON_Y.equals(shpgSerTakeFlg)) {
                    serNumTakeFlg = ZYPConstant.FLG_ON_Y;
                }

                // Set TMsg
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgPlnNum, shpgPlnNum);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.fromStkStsCd, rs.getString(FROM_STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.toStkStsCd, rs.getString(TO_STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.totShpgVol, totShpgVol);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.totShpgWt, totShpgWt);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgPrcAmt, shpgPrcAmt);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.discPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.unitPrcAmt, unitPrcAmt);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.discUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.rqstOrdQty, shpgQty);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgQty, shpgQty);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shipQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.setShpgQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgBalQty, BigDecimal.ZERO);
                // START MOD 04/22/2013 R-WH003
                // back to WS
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgStsCd, SHPG_STS.S_OR_O_PRINTED);
                // E N D MOD 04/22/2013 R-WH003
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.serNumTakeFlg, serNumTakeFlg);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.inEachQty, rs.getBigDecimal(IN_EACH_QTY));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.mdseCd, rs.getString(MDSE_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxHdrNum, rs.getString(TRX_HDR_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxLineNum, rs.getString(TRX_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.expFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.sysSrcCd, rs.getString(SYS_SRC_CD));

                // 11/02/2012 Oce WDS SOL#94 ADD START
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.configItemFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.batNumTakeFlg, ZYPConstant.FLG_OFF_N);
                // 11/02/2012 Oce WDS SOL#94 ADD END

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.rmvConfigFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.preIstlShopRqstFlg, ZYPConstant.FLG_OFF_N);
            } else {
                writeOperationErr(NLZM2004E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }
    }

    /**
     * Get Base Shipping Order Detail Inventry type Work type (!= Disposal, Stock Status Change, Damaged Claim Completion)
     */
    private class GetBaseSoDtlInvtyWrk extends S21SsmBooleanResultSetHandlerSupport {

        /** S21ApiMessageMap */
        private S21ApiMessageMap msgMap;

        /** Base Shipping Order Detail */
        private SHPG_ORD_DTLTMsg shpgOrdDtlT;

        public GetBaseSoDtlInvtyWrk(S21ApiMessageMap msgMap, SHPG_ORD_DTLTMsg shpgOrdDtlT) {

            this.msgMap = msgMap;
            this.shpgOrdDtlT = shpgOrdDtlT;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            boolean operationErrFlg = false;

            NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
            String glblCmpyCd = param.glblCmpyCd.getValue();
            String shpgPlnNum = param.shpgPlnNum.getValue();

            if (rs.next()) {

                BigDecimal shpgQty = rs.getBigDecimal(AVAL_SO_QTY);
                BigDecimal inInchVol = rs.getBigDecimal(IN_INCH_VOL);
                BigDecimal inPoundWt = rs.getBigDecimal(IN_POUND_WT);
                BigDecimal unitPrcAmt = rs.getBigDecimal(SP_FUNC_NET_UNIT_PRC_AMT);
                String shpgSerTakeFlg = nullToEmpty(rs.getString(SHPG_SER_TAKE_FLG));

                BigDecimal totShpgVol = getTotShpgVolInFeet(inInchVol, shpgQty, TOT_SHPG_VOL_DECL_LG);

                totShpgVol = overflowToBorder(totShpgVol, TOT_SHPG_VOL_MAX_LG, TOT_SHPG_VOL_DECL_LG);
                BigDecimal totShpgWt = multiplyNullToNull(inPoundWt, shpgQty, TOT_SHPG_WT_DECL_LG);
                totShpgWt = overflowToBorder(totShpgWt, TOT_SHPG_WT_MAX_LG, TOT_SHPG_WT_DECL_LG);
                BigDecimal shpgPrcAmt = multiplyNullToNull(unitPrcAmt, shpgQty, SHPG_PRC_AMT_DECL_LG);
                shpgPrcAmt = overflowToBorder(shpgPrcAmt, SHPG_PRC_AMT_MAX_LG, SHPG_PRC_AMT_DECL_LG);

                // Check Serial Number Take Flag
                String serNumTakeFlg = ZYPConstant.FLG_OFF_N;
                if (ZYPConstant.FLG_ON_Y.equals(sceOrdTpSerNumTakeFlg)
                        && ZYPConstant.FLG_ON_Y.equals(shpgSerTakeFlg)) {
                    serNumTakeFlg = ZYPConstant.FLG_ON_Y;
                }

                // Set TMsg
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgPlnNum, shpgPlnNum);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.fromStkStsCd, rs.getString(STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.totShpgVol, totShpgVol);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.totShpgWt, totShpgWt);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgPrcAmt, shpgPrcAmt);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.discPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.unitPrcAmt, unitPrcAmt);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.discUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.rqstOrdQty, shpgQty);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgQty, shpgQty);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shipQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.setShpgQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgBalQty, BigDecimal.ZERO);
                // START MOD 04/22/2013 R-WH003
                // back to WS
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgStsCd, SHPG_STS.S_OR_O_PRINTED);
                // E N D MOD 04/22/2013 R-WH003
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.serNumTakeFlg, serNumTakeFlg);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.inEachQty, rs.getBigDecimal(IN_EACH_QTY));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.mdseCd, rs.getString(MDSE_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxHdrNum, rs.getString(TRX_HDR_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxLineNum, rs.getString(TRX_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.expFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.sysSrcCd, rs.getString(SYS_SRC_CD));

                // 11/02/2012 Oce WDS SOL#94 ADD START
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.configItemFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.batNumTakeFlg, ZYPConstant.FLG_OFF_N);
                // 11/02/2012 Oce WDS SOL#94 ADD END

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.rmvConfigFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.preIstlShopRqstFlg, ZYPConstant.FLG_OFF_N);

            } else {
                writeOperationErr(NLZM2004E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }
    }

    /**
     * Get Base Shipping Order Detail Export type
     */
    private class GetBaseSoDtlExpt extends S21SsmBooleanResultSetHandlerSupport {

        /** S21ApiMessageMap */
        private S21ApiMessageMap msgMap;

        /** Base Shipping Order Detail */
        private SHPG_ORD_DTLTMsg shpgOrdDtlT;

        public GetBaseSoDtlExpt(S21ApiMessageMap msgMap, SHPG_ORD_DTLTMsg shpgOrdDtlT) {

            this.msgMap = msgMap;
            this.shpgOrdDtlT = shpgOrdDtlT;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            boolean operationErrFlg = false;

            NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
            String glblCmpyCd = param.glblCmpyCd.getValue();
            String shpgPlnNum = param.shpgPlnNum.getValue();

            if (rs.next()) {

                BigDecimal shpgQty = rs.getBigDecimal(AVAL_SO_QTY);
                BigDecimal rqstOrdQty = rs.getBigDecimal(ORD_QTY);
                BigDecimal inInchVol = rs.getBigDecimal(IN_INCH_VOL);
                BigDecimal inPoundWt = rs.getBigDecimal(IN_POUND_WT);
                BigDecimal unitPrcAmt = rs.getBigDecimal(SP_FUNC_NET_UNIT_PRC_AMT);
                String shpgSerTakeFlg = nullToEmpty(rs.getString(SHPG_SER_TAKE_FLG));
                String cpoOrdTpCd = nullToEmpty(rs.getString(CPO_ORD_TP_CD));

                BigDecimal totShpgVol = getTotShpgVolInFeet(inInchVol, shpgQty, TOT_SHPG_VOL_DECL_LG);

                totShpgVol = overflowToBorder(totShpgVol, TOT_SHPG_VOL_MAX_LG, TOT_SHPG_VOL_DECL_LG);
                BigDecimal totShpgWt = multiplyNullToNull(inPoundWt, shpgQty, TOT_SHPG_WT_DECL_LG);
                totShpgWt = overflowToBorder(totShpgWt, TOT_SHPG_WT_MAX_LG, TOT_SHPG_WT_DECL_LG);
                BigDecimal shpgPrcAmt = multiplyNullToNull(unitPrcAmt, shpgQty, SHPG_PRC_AMT_DECL_LG);
                shpgPrcAmt = overflowToBorder(shpgPrcAmt, SHPG_PRC_AMT_MAX_LG, SHPG_PRC_AMT_DECL_LG);
                BigDecimal shpgBalQty = subtractNullToNull(rqstOrdQty, shpgQty);

                // Check Serial Number Take Flag
                String serNumTakeFlg = ZYPConstant.FLG_OFF_N;
                if (ZYPConstant.FLG_ON_Y.equals(sceOrdTpSerNumTakeFlg)
                        && ZYPConstant.FLG_ON_Y.equals(shpgSerTakeFlg)) {
                    serNumTakeFlg = ZYPConstant.FLG_ON_Y;
                }

                // Check Expense
                String expFlg = ZYPConstant.FLG_OFF_N;
                if (CPO_ORD_TP.EXPENSE.equals(cpoOrdTpCd)) {
                    expFlg = ZYPConstant.FLG_ON_Y;
                }

                // Set TMsg
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.setMdseNm, rs.getString(SET_MDSE_NM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgPlnNum, shpgPlnNum);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.fromStkStsCd, rs.getString(STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.totShpgVol, totShpgVol);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.totShpgWt, totShpgWt);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgPrcAmt, shpgPrcAmt);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.discPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.unitPrcAmt, unitPrcAmt);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.discUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.rqstOrdQty, shpgQty);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgQty, shpgQty);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shipQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.setShpgQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgBalQty, shpgBalQty);
                // START MOD 04/22/2013 R-WH003
                // back to WS
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgStsCd, SHPG_STS.S_OR_O_PRINTED);
                // E N D MOD 04/22/2013 R-WH003
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.custMdseCd, rs.getString(CUST_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.serNumTakeFlg, serNumTakeFlg);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.inEachQty, rs.getBigDecimal(IN_EACH_QTY));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.setMdseCd, rs.getString(SET_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.mdseCd, rs.getString(MDSE_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxHdrNum, rs.getString(TRX_HDR_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxLineNum, rs.getString(TRX_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.expFlg, expFlg);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.sysSrcCd, rs.getString(SYS_SRC_CD));

                // 11/02/2012 Oce WDS SOL#94 ADD START
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.batNumTakeFlg, ZYPConstant.FLG_OFF_N);
                // 11/02/2012 Oce WDS SOL#94 ADD END

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.configItemFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.rmvConfigFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.preIstlShopRqstFlg, ZYPConstant.FLG_OFF_N);

            } else {
                writeOperationErr(NLZM2004E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }
    }

    /**
     * Get Base Shipping Order Detail Vender Return type
     */
    private class GetBaseSoDtlVndRtrn extends S21SsmBooleanResultSetHandlerSupport {

        /** S21ApiMessageMap */
        private S21ApiMessageMap msgMap;

        /** Base Shipping Order Detail */
        private SHPG_ORD_DTLTMsg shpgOrdDtlT;

        public GetBaseSoDtlVndRtrn(S21ApiMessageMap msgMap, SHPG_ORD_DTLTMsg shpgOrdDtlT) {

            this.msgMap = msgMap;
            this.shpgOrdDtlT = shpgOrdDtlT;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            boolean operationErrFlg = false;

            NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
            String glblCmpyCd = param.glblCmpyCd.getValue();
            String shpgPlnNum = param.shpgPlnNum.getValue();

            if (rs.next()) {

                BigDecimal shpgQty = rs.getBigDecimal(AVAL_SO_QTY);
                BigDecimal inInchVol = rs.getBigDecimal(IN_INCH_VOL);
                BigDecimal inPoundWt = rs.getBigDecimal(IN_POUND_WT);
                BigDecimal unitPrcAmt = rs.getBigDecimal(SP_FUNC_NET_UNIT_PRC_AMT);
                String shpgSerTakeFlg = nullToEmpty(rs.getString(SHPG_SER_TAKE_FLG));

                // 08/23/2010 D.Fukaya modify start
                //BigDecimal totShpgVol = multiplyNullToNull(inInchVol, shpgQty, TOT_SHPG_VOL_DECL_LG);
                BigDecimal totShpgVol = getTotShpgVolInFeet(inInchVol, shpgQty, TOT_SHPG_VOL_DECL_LG);
                // 08/23/2010 D.Fukaya modify end

                totShpgVol = overflowToBorder(totShpgVol, TOT_SHPG_VOL_MAX_LG, TOT_SHPG_VOL_DECL_LG);
                BigDecimal totShpgWt = multiplyNullToNull(inPoundWt, shpgQty, TOT_SHPG_WT_DECL_LG);
                totShpgWt = overflowToBorder(totShpgWt, TOT_SHPG_WT_MAX_LG, TOT_SHPG_WT_DECL_LG);
                BigDecimal shpgPrcAmt = multiplyNullToNull(unitPrcAmt, shpgQty, SHPG_PRC_AMT_DECL_LG);
                shpgPrcAmt = overflowToBorder(shpgPrcAmt, SHPG_PRC_AMT_MAX_LG, SHPG_PRC_AMT_DECL_LG);

                // Check Serial Number Take Flag
                String serNumTakeFlg = ZYPConstant.FLG_OFF_N;
                if (ZYPConstant.FLG_ON_Y.equals(sceOrdTpSerNumTakeFlg)
                        && ZYPConstant.FLG_ON_Y.equals(shpgSerTakeFlg)) {
                    serNumTakeFlg = ZYPConstant.FLG_ON_Y;
                }

                // Set TMsg
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.setMdseNm, rs.getString(SET_MDSE_NM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgPlnNum, shpgPlnNum);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.fromStkStsCd, rs.getString(STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.totShpgVol, totShpgVol);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.totShpgWt, totShpgWt);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgPrcAmt, shpgPrcAmt);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.discPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.unitPrcAmt, unitPrcAmt);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.discUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.rqstOrdQty, shpgQty);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgQty, shpgQty);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shipQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.setShpgQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgBalQty, BigDecimal.ZERO);
                // START MOD 04/22/2013 R-WH003
                // back to WS
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgStsCd, SHPG_STS.S_OR_O_PRINTED);
                // E N D MOD 04/22/2013 R-WH003
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.serNumTakeFlg, serNumTakeFlg);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.inEachQty, rs.getBigDecimal(IN_EACH_QTY));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.setMdseCd, rs.getString(SET_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.mdseCd, rs.getString(MDSE_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxHdrNum, rs.getString(TRX_HDR_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxLineNum, rs.getString(TRX_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.expFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.sysSrcCd, rs.getString(SYS_SRC_CD));

                // 11/02/2012 Oce WDS SOL#94 ADD START
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.configItemFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.batNumTakeFlg, ZYPConstant.FLG_OFF_N);
                // 11/02/2012 Oce WDS SOL#94 ADD END

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.rmvConfigFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.preIstlShopRqstFlg, ZYPConstant.FLG_OFF_N);

                // Check VND_RTRN_NUM
                operationErrFlg = checkGetVndRtrnNum(rs.getString(VND_RTRN_NUM));
                if (operationErrFlg) {
                    return operationErrFlg;
                }
            } else {
                writeOperationErr(NLZM2004E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }

        private boolean checkGetVndRtrnNum(String vndRtrnNum) {

            boolean operationErrFlg = false;

            if (!ZYPCommonFunc.hasValue(vndRtrnNum)) {
                writeOperationErr(NLZM2040E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }
    }

    /**
     * Get Base Shipping Order Detail After Item Change type
     */
    private class GetBaseSoDtlItemChngAfters extends S21SsmBooleanResultSetHandlerSupport {

        /** S21ApiMessageMap */
        private S21ApiMessageMap msgMap;

        /** Base Shipping Order Detail After Mdse Change */
        private List<SHPG_ORD_DTLTMsg> soDtlMdseChngAfters;

        public GetBaseSoDtlItemChngAfters(S21ApiMessageMap msgMap, List<SHPG_ORD_DTLTMsg> soDtlMdseChngAfters) {

            this.msgMap = msgMap;
            this.soDtlMdseChngAfters = soDtlMdseChngAfters;
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            boolean operationErrFlg = false;

            NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
            String glblCmpyCd = param.glblCmpyCd.getValue();

            for (; rs.next();) {

                BigDecimal shpgQty = rs.getBigDecimal(ORD_QTY);
                BigDecimal inInchVol = rs.getBigDecimal(IN_INCH_VOL);
                BigDecimal inPoundWt = rs.getBigDecimal(IN_POUND_WT);
                String mdseCd = nullToEmpty(rs.getString(MDSE_MDSE_CD));
                String shpgSerTakeFlg = nullToEmpty(rs.getString(SHPG_SER_TAKE_FLG));

                // Check MDSE
                operationErrFlg = checkMdse(mdseCd);
                if (operationErrFlg) {
                    return operationErrFlg;
                }

                BigDecimal shpgQtyAbs = absNullToNull(shpgQty);

                BigDecimal totShpgVol = multiplyNullToNull(inInchVol, shpgQtyAbs, TOT_SHPG_VOL_DECL_LG);
                totShpgVol = overflowToBorder(totShpgVol, TOT_SHPG_VOL_MAX_LG, TOT_SHPG_VOL_DECL_LG);
                BigDecimal totShpgWt = multiplyNullToNull(inPoundWt, shpgQtyAbs, TOT_SHPG_WT_DECL_LG);
                totShpgWt = overflowToBorder(totShpgWt, TOT_SHPG_WT_MAX_LG, TOT_SHPG_WT_DECL_LG);

                // Check Serial Number Take Flag
                String serNumTakeFlg = ZYPConstant.FLG_OFF_N;
                if (ZYPConstant.FLG_ON_Y.equals(sceOrdTpSerNumTakeFlg)
                        && ZYPConstant.FLG_ON_Y.equals(shpgSerTakeFlg)) {
                    serNumTakeFlg = ZYPConstant.FLG_ON_Y;
                }

                SHPG_ORD_DTLTMsg shpgOrdDtlT = new SHPG_ORD_DTLTMsg();

                // Set TMsg
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.fromStkStsCd, rs.getString(STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.totShpgVol, totShpgVol);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.totShpgWt, totShpgWt);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.discPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.discUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.rqstOrdQty, shpgQty);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgQty, shpgQty);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shipQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.setShpgQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgBalQty, BigDecimal.ZERO);
                // START MOD 04/22/2013 R-WH003
                // back to WS
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.shpgStsCd, SHPG_STS.S_OR_O_PRINTED);
                // E N D MOD 04/22/2013 R-WH003
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.serNumTakeFlg, serNumTakeFlg);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.inEachQty, rs.getBigDecimal(IN_EACH_QTY));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.mdseCd, rs.getString(MDSE_CD));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxHdrNum, rs.getString(INVTY_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.trxLineNum, rs.getString(INVTY_ORD_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.expFlg, ZYPConstant.FLG_OFF_N);

                // 11/02/2012 Oce WDS SOL#94 ADD START
                // QC#18794
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.rmvConfigFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.configItemFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.batNumTakeFlg, ZYPConstant.FLG_OFF_N);
                // 11/02/2012 Oce WDS SOL#94 ADD END
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.preIstlShopRqstFlg, ZYPConstant.FLG_OFF_N);

                soDtlMdseChngAfters.add(shpgOrdDtlT);
            }

            // Check Item
            if (soDtlMdseChngAfters.isEmpty()) {
                writeOperationErr(NLZM2004E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }

        private boolean checkMdse(String mdseCd) {

            boolean operationErrFlg = false;

            if (!ZYPCommonFunc.hasValue(mdseCd)) {
                writeOperationErr(NLZM2010E, msgMap);
                operationErrFlg = true;
            }

            return operationErrFlg;
        }
    }

    /**
     * Get Base Shipping Order Detail SetMdse Amount
     */
    private class GetBaseSoDtlSetMdse extends S21SsmVoidResultSetHandlerSupport {

        /** SetMdse Master */
        private CMPSNTMsg cmpsnT;

        public GetBaseSoDtlSetMdse(CMPSNTMsg cmpsnT) {

            this.cmpsnT = cmpsnT;
        }

        public void doProcessQueryResult(ResultSet rs) throws SQLException {

            if (rs.next()) {

                BigDecimal childMdseQty = nullToZero(rs.getBigDecimal(CHILD_MDSE_QTY));
                if (BigDecimal.ZERO.compareTo(childMdseQty) < 0) {

                    // Set TMsg
                    ZYPEZDItemValueSetter.setValue(cmpsnT.prntMdseCd, rs.getString(PRNT_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(cmpsnT.childMdseQty, childMdseQty);

                } else {

                    // Set TMsg (If ChildeAmount <= 0 then Don't get ChildMdseCd)
                    ZYPEZDItemValueSetter.setValue(cmpsnT.prntMdseCd, "");
                }
            } else {
                // Check Get Record
                // Set TMsg
                ZYPEZDItemValueSetter.setValue(cmpsnT.prntMdseCd, "");
            }
        }
    }

    /**
     * Get Base Shipping Order Message Routing
     */
    private class GetBaseSoMsgRte extends S21SsmVoidResultSetHandlerSupport {

        /** Base Shipping Order Message Routing */
        private Map<String, String> baseSoMsgRte;

        public GetBaseSoMsgRte(Map<String, String> baseSoMsgRte) {

            this.baseSoMsgRte = baseSoMsgRte;
        }

        public void doProcessQueryResult(ResultSet rs) throws SQLException {

            if (rs.next()) {

                // Routing
                // RTE_GUIDE
                baseSoMsgRte.put(RTE_GUIDE_CUST_CD, nullToEmpty(rs.getString(RTE_GUIDE_CUST_CD)));
                baseSoMsgRte.put(RTE_GUIDE_SECT_FIRST_FLG, nullToEmpty(rs.getString(RTE_GUIDE_SECT_FIRST_FLG)));
                baseSoMsgRte.put(RTE_GUIDE_SECT_SCD_FLG, nullToEmpty(rs.getString(RTE_GUIDE_SECT_SCD_FLG)));
                baseSoMsgRte.put(RTE_GUIDE_SECT_THIRD_FLG, nullToEmpty(rs.getString(RTE_GUIDE_SECT_THIRD_FLG)));
                baseSoMsgRte.put(RTE_GUIDE_SECT_FRTH_FLG, nullToEmpty(rs.getString(RTE_GUIDE_SECT_FRTH_FLG)));
                baseSoMsgRte.put(RTE_GUIDE_SECT_FIFTH_FLG, nullToEmpty(rs.getString(RTE_GUIDE_SECT_FIFTH_FLG)));
                baseSoMsgRte.put(RTE_GUIDE_SECT_SIXTH_FLG, nullToEmpty(rs.getString(RTE_GUIDE_SECT_SIXTH_FLG)));
                baseSoMsgRte.put(RTE_GUIDE_SECT_SVNTH_FLG, nullToEmpty(rs.getString(RTE_GUIDE_SECT_SVNTH_FLG)));
                baseSoMsgRte.put(RTE_GUIDE_SECT_EIGHTH_FLG, nullToEmpty(rs.getString(RTE_GUIDE_SECT_EIGHTH_FLG)));

                // Check Record
                // RTE_GUIDE
                baseSoMsgRte.put(RTE_GUIDE_CUST_CD, nullToEmpty(rs.getString(RTE_GUIDE_CUST_CD)));

            } else {
                // Check Record
                // RTE_GUIDE
                baseSoMsgRte.put(RTE_GUIDE_CUST_CD, "");
            }
        }
    }

    /**
     * Create Shipping Order Common
     * @param onBatchType Online/BatchType
     * @param baseSoHdr BaseShippingOrderHeader
     * @param soDtls ShippingOrderDetailList
     * @param soDtlMdseChngAfters ShippingOrderDetailListOfMdseChange
     */
    private void createSoComn(
            final ONBATCH_TYPE onBatchType,
            final Map<String, String> baseSoHdr,
            final List<SHPG_ORD_DTLTMsg> soDtls,
            final List<SHPG_ORD_DTLTMsg> soDtlMdseChngAfters,
            final List<String> soMsgDescTxts) {

        int soSlpNum = 0;

        for (SHPG_ORD_DTLTMsg soDtl : soDtls) {

            soSlpNum++;
            String paddingSoSlpNum = String.format(SO_SLP_NUM_FMT, soSlpNum);

            if(SCE_ORD_TP.CONFIG_CHANGE.equals(this.hdrSceOrdTpCd)){
                ZYPEZDItemValueSetter.setValue(soDtl.configItemFlg, ZYPConstant.FLG_ON_Y);
            }

            insertSoDtl(soDtl, paddingSoSlpNum);

            callShpgPlnAPI(onBatchType, baseSoHdr, soDtl);
        }

        for (SHPG_ORD_DTLTMsg soDtlMdseChngAfter : soDtlMdseChngAfters) {

            soSlpNum++;
            String paddingSoSlpNum = String.format(SO_SLP_NUM_FMT, soSlpNum);

            insertSoDtl(soDtlMdseChngAfter, paddingSoSlpNum);
        }

        this.cntShpgOrdDtl = soSlpNum;

        int txtSqNum = 0;
        // 06/17/2010 D.Fukaya append start
        if (soMsgDescTxts != null) {
        // 06/17/2010 D.Fukaya append end
            for (String soMsgDescTxt : soMsgDescTxts) {
                txtSqNum++;

                if (ZYPCommonFunc.hasValue(soMsgDescTxt)) {
                    insertSoMsgMsg(String.valueOf(txtSqNum), soMsgDescTxt);

                    this.cntShpgOrdMsg++;
                }
            }
        }
    }

    /**
     * Create Shipping Order CPO type
     * @param baseSoHdr BaseShippingOrderHeader
     */
    private void createSoCpo(final ONBATCH_TYPE onBatchType, final Map<String, String> baseSoHdr, final List<SHPG_ORD_DTLTMsg> soDtls) {

        insertSoHdrCpo(baseSoHdr);

        String dropShipFlg = baseSoHdr.get(DROP_SHIP_FLG);

        if (ZYPConstant.FLG_OFF_N.equals(dropShipFlg)) {

            insertSoCustDtlShipTo(baseSoHdr);

        } else {

            insertSoCustDtlShipToOneTmUsrFromSellTo(baseSoHdr);
        }

        insertSoCustDtlSellTo(baseSoHdr);
        insertSoCustDtlBillTo(baseSoHdr);

        this.cntShpgOrd++;
        this.cntShpgOrdCustDtl += this.cntShpgOrdCustDtl + 3;

        String cbsDropFlg = baseSoHdr.get(CBS_DROP_FLG);

        if (ZYPConstant.FLG_ON_Y.equals(cbsDropFlg)) {

            insertSoCustDtlRtrnTo(baseSoHdr);
            this.cntShpgOrdCustDtl++;
        }

        String rteGuideCustCd = baseSoHdr.get(RTE_GUIDE_CUST_CD);

        if (ZYPCommonFunc.hasValue(rteGuideCustCd)) {

            List<String> rteGuideSectFlg = getSoMsgRteSectFlgs(baseSoHdr);
            String soMsgDescTxt = createSoMsgRteDescTxt(rteGuideSectFlg);

            if (ZYPCommonFunc.hasValue(soMsgDescTxt)) {

                insertSoMsgRte(soMsgDescTxt);
                this.cntShpgOrdMsg++;
            }
        }

        // 02/05/2011 D.Fukaya append start
//        if (SCE_ORD_TP.EXPORT.equals(this.hdrSceOrdTpCd)) {
//
//            callDoUpdateAPI(onBatchType, baseSoHdr, soDtls);
//        }
        // 02/05/2011 D.Fukaya appnd end
    }


    /**
     * Create Shipping Order DC Transfer type
     * @param baseSoHdr BaseShippingOrderHeader
     */
    private void createSoDcTransf(final Map<String, String> baseSoHdr) {

        insertSoHdrDcTransf(baseSoHdr);

        // START 2018/04/02 S.Katsuma [QC#25143,MOD]
        String dropShipFlg = baseSoHdr.get(DROP_SHIP_FLG);
        // QC#30792
        if (ZYPConstant.FLG_OFF_N.equals(dropShipFlg) || (SCE_ORD_TP.TECH_REQUEST.equals(this.hdrSceOrdTpCd) && !ZYPCommonFunc.hasValue(baseSoHdr.get(SHIP_TO_LOC_NM)))) {
            insertSoCustDtlShipTo(baseSoHdr);
        } else {
            insertSoCustDtlShipToOneTmUsrFromSellTo(baseSoHdr);
        }
        // END 2018/04/02 S.Katsuma [QC#25143,MOD]

        insertSoCustDtlSellTo(baseSoHdr);

        this.cntShpgOrd++;

        // QC#54989 Add Strat
        if (condInsSoCustDtlBillTo((String)baseSoHdr.get(RTL_WH_CD))) {
            String locNum = (String) baseSoHdr.get(BILL_LOC_NM);
            if (ZYPCommonFunc.hasValue(locNum)) {
                insertSoCustDtlBillTo(baseSoHdr);
                this.cntShpgOrdCustDtl ++;
            } else {
                this.cntShpgOrdCustDtl += 2;
            }
        } else {
            this.cntShpgOrdCustDtl += 2;
        }
        // QC#54989 Add End

    }

    /**
     * Create Shipping Order Inventry type (!= DC Transfer)
     * @param baseSoHdr BaseShippingOrderHeader
     */
    private void createSoInvty(final Map<String, String> baseSoHdr) {

        insertSoHdrInvty(baseSoHdr);

        // START 2018/05/07 S.Katsuma [QC#25389,MOD]
        String dropShipFlg = baseSoHdr.get(DROP_SHIP_FLG);
        if (ZYPConstant.FLG_OFF_N.equals(dropShipFlg)) {
            insertSoCustDtlShipTo(baseSoHdr);
        } else {
            insertSoCustDtlShipToOneTmUsrFromSellTo(baseSoHdr);
        }
        // END 2018/05/07 S.Katsuma [QC#25389,MOD]

        insertSoCustDtlSellTo(baseSoHdr);

        this.cntShpgOrd++;
        // QC#54989 Add Strat
        if (condInsSoCustDtlBillTo((String)baseSoHdr.get(RTL_WH_CD))) {
            String locNum = (String) baseSoHdr.get(BILL_LOC_NM);
            if (ZYPCommonFunc.hasValue(locNum)) {
                insertSoCustDtlBillTo(baseSoHdr);
                this.cntShpgOrdCustDtl ++;
            } else {
                this.cntShpgOrdCustDtl += 2;
            }
        } else {
            this.cntShpgOrdCustDtl += 2;
        }
        // QC#54989 Add End 
    }
    /**
     * Create Shipping Order Work type
     * @param baseSoHdr BaseShippingOrderHeader
     */
    private void createSoWrk(final Map<String, String> baseSoHdr) {

        insertSoHdrWrk(baseSoHdr);
        insertSoCustDtlShipTo(baseSoHdr);
        insertSoCustDtlSellTo(baseSoHdr);

        this.cntShpgOrd++;

        // QC#54989 Add Strat
        if (condInsSoCustDtlBillTo((String)baseSoHdr.get(RTL_WH_CD))) {
            String locNum = (String) baseSoHdr.get(BILL_LOC_NM);
            if (ZYPCommonFunc.hasValue(locNum)) {
                insertSoCustDtlBillTo(baseSoHdr);
                this.cntShpgOrdCustDtl ++;
            } else {
                this.cntShpgOrdCustDtl += 2;
            }
        } else {
            this.cntShpgOrdCustDtl += 2;
        }
        
        // QC#54989 Add End
    }

    /**
     * Create Shipping Order Export type
     * @param onBatchType Online/BatchType
     * @param baseSoHdr BaseShippingOrderHeader
     * @param soDtls ShippingOrderDetailList
     */
    private void createSoExpt(final ONBATCH_TYPE onBatchType, final Map<String, String> baseSoHdr, final List<SHPG_ORD_DTLTMsg> soDtls) {

        insertSoHdrExpt(baseSoHdr);
        String dropShipFlg = baseSoHdr.get(DROP_SHIP_FLG);

        if (ZYPConstant.FLG_OFF_N.equals(dropShipFlg)) {

            insertSoCustDtlShipTo(baseSoHdr);

        } else {

            insertSoCustDtlShipToOneTmUsrFromSellTo(baseSoHdr);
        }

        insertSoCustDtlSellTo(baseSoHdr);
        insertSoCustDtlBillTo(baseSoHdr);

        this.cntShpgOrd++;
        this.cntShpgOrdCustDtl += this.cntShpgOrdCustDtl + 3;

        String cbsDropFlg = baseSoHdr.get(CBS_DROP_FLG);

        if (ZYPConstant.FLG_ON_Y.equals(cbsDropFlg)) {

            insertSoCustDtlRtrnTo(baseSoHdr);
            this.cntShpgOrdCustDtl++;
        }

        String rteGuideCustCd = baseSoHdr.get(RTE_GUIDE_CUST_CD);

        if (ZYPCommonFunc.hasValue(rteGuideCustCd)) {

            List<String> rteGuideSectFlg = getSoMsgRteSectFlgs(baseSoHdr);
            String soMsgDescTxt = createSoMsgRteDescTxt(rteGuideSectFlg);

            if (ZYPCommonFunc.hasValue(soMsgDescTxt)) {

                insertSoMsgRte(soMsgDescTxt);
                this.cntShpgOrdMsg++;
            }
        }

//        callDoUpdateAPI(onBatchType, baseSoHdr, soDtls);
    }

    /**
     * Create Shipping Order Vender Return type
     * @param onBatchType Online/BatchType
     * @param baseSoHdr BaseShippingOrderHeader
     * @param soDtls ShippingOrderDetailList
     */
    private void createSoVndRtrn(final ONBATCH_TYPE onBatchType, final Map<String, String> baseSoHdr, final List<SHPG_ORD_DTLTMsg> soDtls) {

        insertSoHdrVndRtrn(baseSoHdr);

        String dropShipFlg = baseSoHdr.get(DROP_SHIP_FLG);

        if (ZYPConstant.FLG_OFF_N.equals(dropShipFlg)) {

            insertSoCustDtlShipTo(baseSoHdr);

        } else {

            insertSoCustDtlShipToOneTmUsr(baseSoHdr);
        }

        insertSoCustDtlSellToVnd(baseSoHdr);
        insertSoCustDtlBillToVnd(baseSoHdr);

        this.cntShpgOrd++;
        this.cntShpgOrdCustDtl += this.cntShpgOrdCustDtl + 3;

        String rteGuideCustCd = baseSoHdr.get(RTE_GUIDE_CUST_CD);

        if (ZYPCommonFunc.hasValue(rteGuideCustCd)) {

            List<String> rteGuideSectFlg = getSoMsgRteSectFlgs(baseSoHdr);
            String soMsgDescTxt = createSoMsgRteDescTxt(rteGuideSectFlg);

            if (ZYPCommonFunc.hasValue(soMsgDescTxt)) {

                insertSoMsgRte(soMsgDescTxt);
                this.cntShpgOrdMsg++;
            }
        }

//        if (SCE_ORD_TP_CD_RETURN_TO_VENDOR_IMPORT.equals(this.hdrSceOrdTpCd)) {
//
//            callDoUpdateAPI(onBatchType, baseSoHdr, soDtls);
//        }
    }

    /**
     * Create Shipping Order Header CPO type
     * @param baseSoHdr BaseShippingOrderHeader
     */
    private void insertSoHdrCpo(final Map<String, String> baseSoHdr) {

        String cbsDropFlg = baseSoHdr.get(CBS_DROP_FLG);
        String cpoSrcTpCd = baseSoHdr.get(CPO_SRC_TP_CD);

        // Check If CBS Drop then Set Return Code.
        String rtrnLbCd = "";
        if (ZYPConstant.FLG_ON_Y.equals(cbsDropFlg)) {
            rtrnLbCd = baseSoHdr.get(RTRN_LB_CD);
        }

        // If CPO_SRC_TP_CD is Company Store then CMPY_STR_IND set flag on.
        String cmpyStrInd = ZYPConstant.FLG_OFF_0;
        if (CPO_SRC_TP.COMPANY_STORE.equals(cpoSrcTpCd)) {
            cmpyStrInd = ZYPConstant.FLG_ON_1;
        }

        SHPG_ORDTMsg shpgOrdT = new SHPG_ORDTMsg();

        setTmsgSoHdr(baseSoHdr, shpgOrdT);
        setTmsgSoHdrCpoRoss(baseSoHdr, shpgOrdT);

        shpgOrdT.rtrnLbCd.setValue(rtrnLbCd);
        shpgOrdT.soDeptNum.setValue(baseSoHdr.get(SO_DEPT_NUM));
        shpgOrdT.cmpyStrInd.setValue(cmpyStrInd);

        if (ZYPConstant.FLG_ON_Y.equals(this.hdrShpgFrceFlg)) {

            setTmsgSoHdrShpgFrceFlgOn(baseSoHdr, shpgOrdT);

            shpgOrdT.frtChrgToCd.setValue(baseSoHdr.get(REQ_FRT_CHRG_TO_CD));
            shpgOrdT.frtChrgMethCd.setValue(baseSoHdr.get(REQ_FRT_CHRG_METH_CD));

        } else {

            setTmsgSoHdrShpgFrceFlgOff(baseSoHdr, shpgOrdT);

            shpgOrdT.frtChrgToCd.setValue(baseSoHdr.get(FRT_CHRG_TO_CD));
            shpgOrdT.frtChrgMethCd.setValue(baseSoHdr.get(FRT_CHRG_METH_CD));
        }

        shpgOrdT.ntcProcStsCd.setValue(PROC_STS.IN_COMPLETED);

        // 11/02/2012 Oce WDS SOL#94 ADD START
        if (this.configFlg) {
            shpgOrdT.soConfigFlg.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            shpgOrdT.soConfigFlg.setValue(ZYPConstant.FLG_OFF_N);
        }

        if (this.hazMatFlg) {
            shpgOrdT.hazMatFlg.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            shpgOrdT.hazMatFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
        // 11/02/2012 Oce WDS SOL#94 ADD END

        S21ApiTBLAccessor.insert(shpgOrdT);
        if (!DB_RETURN_CD_NORMAL_END.equals(shpgOrdT.getReturnCode())) {
            throw new S21AbendException(NLZM2030E);
        }
    }

    /**
     * Create Shipping Order Header DC Transfer type
     * @param baseSoHdr BaseShippingOrderHeader
     */
    private void insertSoHdrDcTransf(final Map<String, String> baseSoHdr) {

        SHPG_ORDTMsg shpgOrdT = new SHPG_ORDTMsg();

        setTmsgSoHdr(baseSoHdr, shpgOrdT);
        setTmsgSoHdrInvtyWrk(baseSoHdr, shpgOrdT);

        /* 12/21/2016 CITS T.Tokutomi QC#13893,13895 START */
        if(SCE_ORD_TP.REFURBISH_EXPENSE.equals(this.hdrSceOrdTpCd)){
            ZYPEZDItemValueSetter.setValue(shpgOrdT.sellToCustCd, baseSoHdr.get(SHIP_TO_CUST_CD));
        } else {
            ZYPEZDItemValueSetter.setValue(shpgOrdT.sellToCustCd, baseSoHdr.get(SELL_TO_CUST_CD));
        }
        /* 12/21/2016 CITS T.Tokutomi QC#13893,13895 END */

        ZYPEZDItemValueSetter.setValue(shpgOrdT.frtChrgToCd, FRT_CHRG_TO.CANON);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.frtChrgMethCd, FRT_CHRG_METH.N_OR_A);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.vndCd, baseSoHdr.get(CARR_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.carrCd, shpgOrdT.vndCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdT.rddDt, baseSoHdr.get(RDD_DT));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.rsdDt, baseSoHdr.get(RSD_DT));

        if (SCE_ORD_TP.REFURBISH_EXPENSE.equals(this.hdrSceOrdTpCd)) {

            ZYPEZDItemValueSetter.setValue(shpgOrdT.shipToCustCd, baseSoHdr.get(SHIP_TO_CUST_CD));

        } else {

            ZYPEZDItemValueSetter.setValue(shpgOrdT.shipToCustCd, baseSoHdr.get(RS2_RTL_WH_CD));
        }

        if (ZYPConstant.FLG_ON_Y.equals(this.hdrShpgFrceFlg) || SCE_ORD_TP.DC_TRANSFER_LOSS.equals(this.hdrSceOrdTpCd)) {

            setTmsgSoHdrShpgFrceFlgOn(baseSoHdr, shpgOrdT);

        } else {

            setTmsgSoHdrShpgFrceFlgOff(baseSoHdr, shpgOrdT);
        }

        shpgOrdT.ntcProcStsCd.setValue(PROC_STS.IN_COMPLETED);

        if (chkConfigItemFlag()) {

            shpgOrdT.soConfigFlg.setValue(ZYPConstant.FLG_ON_Y);

        } else {

            shpgOrdT.soConfigFlg.setValue(ZYPConstant.FLG_OFF_N);
        }

        if (this.hazMatFlg) {

            shpgOrdT.hazMatFlg.setValue(ZYPConstant.FLG_ON_Y);

        } else {

            shpgOrdT.hazMatFlg.setValue(ZYPConstant.FLG_OFF_N);
        }

        S21ApiTBLAccessor.insert(shpgOrdT);

        if (!DB_RETURN_CD_NORMAL_END.equals(shpgOrdT.getReturnCode())) {

            throw new S21AbendException(NLZM2030E);
        }
    }

    /**
     * chkConfigItemFlag
     * @return boolean
     */
    private boolean chkConfigItemFlag() {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.hdrGlblCmpyCd);
        queryParam.put("soNum", this.soNum);
        queryParam.put("configItemFlg", ZYPConstant.FLG_ON_Y);

        BigDecimal configItemCnt = (BigDecimal) ssmBatchClient.queryObject("getConfigItemCnt", queryParam);

        if (configItemCnt.compareTo(BigDecimal.ZERO) > 0) {

            return true;
        }

        return false;
    }
    
    /**
     * Create Shipping Order Header Inventry type (!= DC Transfer)
     * @param baseSoHdr BaseShippingOrderHeader
     */
    private void insertSoHdrInvty(final Map<String, String> baseSoHdr) {

        SHPG_ORDTMsg shpgOrdT = new SHPG_ORDTMsg();

        setTmsgSoHdr(baseSoHdr, shpgOrdT);
        setTmsgSoHdrInvtyWrk(baseSoHdr, shpgOrdT);

        if (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(this.hdrSceOrdTpCd) || SCE_ORD_TP_CD_ITEM_CHANGE.equals(this.hdrSceOrdTpCd)) {

            // Pattern 4
            ZYPEZDItemValueSetter.setValue(shpgOrdT.sellToCustCd, baseSoHdr.get(SHIP_TO_SELL_TO_CUST_CD));

        } else {

            // Pattern 5
            ZYPEZDItemValueSetter.setValue(shpgOrdT.sellToCustCd, baseSoHdr.get(INVTY_TO_SELL_TO_CUST_CD));
        }

        ZYPEZDItemValueSetter.setValue(shpgOrdT.shipToCustCd, baseSoHdr.get(RS2_RTL_WH_CD));

        ZYPEZDItemValueSetter.setValue(shpgOrdT.vndCd, baseSoHdr.get(CARR_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.carrCd, shpgOrdT.vndCd);

        /* 07/03/2017 CITS M.Naito QC#19682 START */
        if (SCE_ORD_TP.DISPOSAL.equals(this.hdrSceOrdTpCd)) {
            /* 07/03/2017 CITS M.Naito QC#19682 END */

            String shipToCustCd = baseSoHdr.get(SHIP_TO_CUST_CD);

            if (ZYPCommonFunc.hasValue(shipToCustCd)) {

                if (doesExistVndCd(shipToCustCd)) {

                    ZYPEZDItemValueSetter.setValue(shpgOrdT.shipToCustCd, shipToCustCd);
                    /* 12/21/2016 CITS T.Tokutomi QC#13893,13895 START */
                    ZYPEZDItemValueSetter.setValue(shpgOrdT.sellToCustCd, shipToCustCd);
                    /* 12/21/2016 CITS T.Tokutomi QC#13893,13895 END */
                }
            }

            // Add Start 2020/01/28 QC#55628
            String shpgSvcLvlCd = baseSoHdr.get(ORIG_SHPG_SVC_LVL_CD);

            if (ZYPCommonFunc.hasValue(shpgSvcLvlCd)) {

                ZYPEZDItemValueSetter.setValue(shpgOrdT.shpgSvcLvlCd, shpgSvcLvlCd);
            }
            // Add End 2020/01/28 QC#55628
        } 

        ZYPEZDItemValueSetter.setValue(shpgOrdT.ntcProcStsCd, PROC_STS.IN_COMPLETED);

        if(SCE_ORD_TP.CONFIG_CHANGE.equals(this.hdrSceOrdTpCd)){

            shpgOrdT.soConfigFlg.setValue(ZYPConstant.FLG_ON_Y);

        } else {

            if(chkConfigItemFlag()){

                shpgOrdT.soConfigFlg.setValue(ZYPConstant.FLG_ON_Y);

            } else {

                shpgOrdT.soConfigFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
        }

        if (this.hazMatFlg) {

            shpgOrdT.hazMatFlg.setValue(ZYPConstant.FLG_ON_Y);

        } else {

            shpgOrdT.hazMatFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
        // 11/02/2012 Oce WDS SOL#94 ADD END
        /* 07/03/2017 CITS M.Naito QC#19682 START */
        ZYPEZDItemValueSetter.setValue(shpgOrdT.ediTpCd, baseSoHdr.get(TRX_SRC_TP_CD));
        /* 07/03/2017 CITS M.Naito QC#19682 END */

        //09/11/2017 CITS S.Endo MOD Sol#069(QC#18270) START
        if (SCE_ORD_TP.SUB_WH_CHANGE.equals(this.hdrSceOrdTpCd) || SCE_ORD_TP.ITEM_CHANGE.equals(this.hdrSceOrdTpCd)  ) {
            ZYPEZDItemValueSetter.setValue(shpgOrdT.psdDt, swRsdDt);
        }
        //09/11/2017 CITS S.Endo MOD Sol#069(QC#18270) END
        S21ApiTBLAccessor.insert(shpgOrdT);

        if (!DB_RETURN_CD_NORMAL_END.equals(shpgOrdT.getReturnCode())) {

            throw new S21AbendException(NLZM2030E);
        }
    }

    /**
     * doesExistVndCd
     * @param shipToCustCd
     * @return boolean
     */
    private boolean doesExistVndCd(String shipToCustCd) {

        VNDTMsg config = new VNDTMsg();
        config.setSQLID(SQL_ID_VND_001);
        config.setConditionValue(TAK_GLBL_CMPY_CD, this.hdrGlblCmpyCd);
        config.setConditionValue(TAK_VND_CD, shipToCustCd);

        if (S21ApiTBLAccessor.count(config) > 0) {

            return true;
        }

        return false;
    }

    /**
     * Create Shipping Order Header Work type
     * @param baseSoHdr BaseShippingOrderHeader
     */
    private void insertSoHdrWrk(final Map<String, String> baseSoHdr) {

        SHPG_ORDTMsg shpgOrdT = new SHPG_ORDTMsg();

        setTmsgSoHdr(baseSoHdr, shpgOrdT);
        setTmsgSoHdrInvtyWrk(baseSoHdr, shpgOrdT);

        shpgOrdT.sellToCustCd.setValue(baseSoHdr.get(SHIP_TO_SELL_TO_CUST_CD));
        shpgOrdT.shipToCustCd.setValue(baseSoHdr.get(RS2_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.vndCd, baseSoHdr.get(CARR_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.carrCd, shpgOrdT.vndCd);
        shpgOrdT.custIssPoNum.setValue(baseSoHdr.get(TRX_HDR_NUM));
        shpgOrdT.frtChrgToCd.setValue(FRT_CHRG_TO.CANON);
        shpgOrdT.frtChrgMethCd.setValue(FRT_CHRG_METH.N_OR_A);
        shpgOrdT.shpgSvcLvlCd.setValue(baseSoHdr.get(ORIG_SHPG_SVC_LVL_CD));
        shpgOrdT.ntcProcStsCd.setValue(PROC_STS.IN_COMPLETED);
        shpgOrdT.soConfigFlg.setValue(ZYPConstant.FLG_OFF_N);

        if (this.hazMatFlg) {

            shpgOrdT.hazMatFlg.setValue(ZYPConstant.FLG_ON_Y);

        } else {

            shpgOrdT.hazMatFlg.setValue(ZYPConstant.FLG_OFF_N);
        }

        S21ApiTBLAccessor.insert(shpgOrdT);

        if (!DB_RETURN_CD_NORMAL_END.equals(shpgOrdT.getReturnCode())) {

            throw new S21AbendException(NLZM2030E);
        }
    }

    /**
     * Create Shipping Order Header Export type
     * @param baseSoHdr BaseShippingOrderHeader
     */
    private void insertSoHdrExpt(final Map<String, String> baseSoHdr) {

        String asnReqFlg = getAsnRequiredFlag(baseSoHdr);

        String cbsDropFlg = baseSoHdr.get(CBS_DROP_FLG);
        String rtrnLbCd = "";

        if (ZYPConstant.FLG_ON_Y.equals(cbsDropFlg)) {

            rtrnLbCd = baseSoHdr.get(RTRN_LB_CD);
        }

        SHPG_ORDTMsg shpgOrdT = new SHPG_ORDTMsg();
        setTmsgSoHdr(baseSoHdr, shpgOrdT);
        shpgOrdT.sellToCustCd.setValue(baseSoHdr.get(SELL_TO_CUST_CD));
        shpgOrdT.billToCustCd.setValue(baseSoHdr.get(BILL_TO_CUST_CD));
        shpgOrdT.shipToCustCd.setValue(baseSoHdr.get(SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.vndCd, baseSoHdr.get(CARR_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.carrCd, shpgOrdT.vndCd);
        shpgOrdT.custIssPoNum.setValue(baseSoHdr.get(CUST_ISS_PO_NUM));
        shpgOrdT.frtChrgToCd.setValue(baseSoHdr.get(REQ_FRT_CHRG_TO_CD));
        shpgOrdT.frtChrgMethCd.setValue(baseSoHdr.get(REQ_FRT_CHRG_METH_CD));
        shpgOrdT.shpgSvcLvlCd.setValue(baseSoHdr.get(ORIG_SHPG_SVC_LVL_CD));
        shpgOrdT.rsdDt.setValue(baseSoHdr.get(RSD_DT));
        shpgOrdT.asnReqFlg.setValue(asnReqFlg);

        if (ZYPConstant.FLG_ON_Y.equals(asnReqFlg)) {

            shpgOrdT.printSccLbFlg.setValue(baseSoHdr.get(PRINT_SCC_LB_FLG));
            shpgOrdT.printUccLbFlg.setValue(baseSoHdr.get(PRINT_UCC_LB_FLG));
            shpgOrdT.mixPltAllwFlg.setValue(baseSoHdr.get(MIX_PLT_ALLW_FLG));
            shpgOrdT.printNonAsnUccLbFlg.setValue(baseSoHdr.get(NON_ASN_UCC_LB_FLG));

        } else {

            shpgOrdT.printSccLbFlg.setValue(ZYPConstant.FLG_OFF_N);
            shpgOrdT.printUccLbFlg.setValue(ZYPConstant.FLG_OFF_N);
            shpgOrdT.mixPltAllwFlg.setValue(ZYPConstant.FLG_OFF_N);
            shpgOrdT.printPltUccLbFlg.setValue(ZYPConstant.FLG_OFF_N);
            shpgOrdT.printNonAsnUccLbFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
        // 12/08/2010 D.Fukaya modify end

        shpgOrdT.rtrnLbCd.setValue(rtrnLbCd);
        shpgOrdT.dropShipFlg.setValue(baseSoHdr.get(DROP_SHIP_FLG));
        shpgOrdT.soDeptNum.setValue(baseSoHdr.get(SO_DEPT_NUM));
        shpgOrdT.ntcProcStsCd.setValue(PROC_STS.IN_COMPLETED);

        if (ZYPConstant.FLG_ON_Y.equals(this.hdrShpgFrceFlg)) {

            shpgOrdT.psdDt.setValue(this.slsDt);

        } else {

            shpgOrdT.rteGrpNum.setValue(baseSoHdr.get(RTE_GRP_NUM));
            shpgOrdT.psdDt.setValue(baseSoHdr.get(PSD_DT));
        }

        // 11/02/2012 Oce WDS SOL#94 ADD START
        if (this.configFlg) {

            shpgOrdT.soConfigFlg.setValue(ZYPConstant.FLG_ON_Y);

        } else {

            shpgOrdT.soConfigFlg.setValue(ZYPConstant.FLG_OFF_N);
        }

        if (this.hazMatFlg) {

            shpgOrdT.hazMatFlg.setValue(ZYPConstant.FLG_ON_Y);

        } else {

            shpgOrdT.hazMatFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
        // 11/02/2012 Oce WDS SOL#94 ADD END

        S21ApiTBLAccessor.insert(shpgOrdT);

        if (!DB_RETURN_CD_NORMAL_END.equals(shpgOrdT.getReturnCode())) {

            throw new S21AbendException(NLZM2030E);
        }
    }

    /**
     * Create Shipping Order Header Vender Return type
     * @param baseSoHdr BaseShippingOrderHeader
     */
    private void insertSoHdrVndRtrn(final Map<String, String> baseSoHdr) {

        SHPG_ORDTMsg shpgOrdT = new SHPG_ORDTMsg();
        setTmsgSoHdr(baseSoHdr, shpgOrdT);
        shpgOrdT.sellToCustCd.setValue(baseSoHdr.get(SELL_TO_CUST_CD));
        shpgOrdT.billToCustCd.setValue(baseSoHdr.get(BILL_TO_CUST_CD));
        shpgOrdT.shipToCustCd.setValue(baseSoHdr.get(SHIP_TO_CUST_CD));
        shpgOrdT.frtChrgToCd.setValue(baseSoHdr.get(REQ_FRT_CHRG_TO_CD));
        shpgOrdT.frtChrgMethCd.setValue(baseSoHdr.get(REQ_FRT_CHRG_METH_CD));
        shpgOrdT.shpgSvcLvlCd.setValue(baseSoHdr.get(ORIG_SHPG_SVC_LVL_CD));
        shpgOrdT.psdDt.setValue(baseSoHdr.get(RSD_DT));
        shpgOrdT.rsdDt.setValue(baseSoHdr.get(RSD_DT));
        shpgOrdT.asnReqFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.printSccLbFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.printUccLbFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.mixPltAllwFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.printPltUccLbFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.printNonAsnUccLbFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.dropShipFlg.setValue(baseSoHdr.get(DROP_SHIP_FLG));
        shpgOrdT.ntcProcStsCd.setValue(PROC_STS.IN_COMPLETED);

        // 11/02/2012 Oce WDS SOL#94 ADD START
        shpgOrdT.soConfigFlg.setValue(ZYPConstant.FLG_OFF_N);

        if (this.hazMatFlg) {

            shpgOrdT.hazMatFlg.setValue(ZYPConstant.FLG_ON_Y);

        } else {

            shpgOrdT.hazMatFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
        // 11/02/2012 Oce WDS SOL#94 ADD END

        S21ApiTBLAccessor.insert(shpgOrdT);

        if (!DB_RETURN_CD_NORMAL_END.equals(shpgOrdT.getReturnCode())) {

            throw new S21AbendException(NLZM2030E);
        }
    }

    /**
     * Create Shipping Order Detail
     * @param shpgOrdDtlT ShippingOrderDetailTMsg
     * @param soSlpNum ShippingOrderSlipNumber
     */
    private void insertSoDtl(final SHPG_ORD_DTLTMsg shpgOrdDtlT, final String soSlpNum) {

        shpgOrdDtlT.soNum.setValue(this.soNum);
        shpgOrdDtlT.soSlpNum.setValue(soSlpNum);

        S21ApiTBLAccessor.insert(shpgOrdDtlT);

        if (!DB_RETURN_CD_NORMAL_END.equals(shpgOrdDtlT.getReturnCode())) {

            throw new S21AbendException(NLZM2030E);
        }
    }

    /**
     * getShipToCust
     * @return SHIP_TO_CUSTTMsg
     */
    private SHIP_TO_CUSTTMsg getShipToCust(String shipToCustCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.hdrGlblCmpyCd);
        queryParam.put("shipToCustCd", shipToCustCd);

        SHIP_TO_CUSTTMsg shipToCustTMsg = (SHIP_TO_CUSTTMsg) ssmBatchClient.queryObject("getShipToCust", queryParam);

        return shipToCustTMsg;
    }

    /**
     * Create Shipping Order Customer Ship-To
     * @param baseSoCustDtl BaseShippingOrderCustomerDetail
     */
    private void insertSoCustDtlShipTo(final Map<String, String> baseSoCustDtl) {

        String shipToCustCd = baseSoCustDtl.get(SHIP_TO_CUST_CD); 

        if (SELECT_ORD_TP_LIST_FOR_SHIP_TO_CUST.contains(this.hdrSceOrdTpCd)) {

            shipToCustCd = baseSoCustDtl.get(RS2_RTL_WH_CD);

            if (!ZYPCommonFunc.hasValue(shipToCustCd) && SCE_ORD_TP.DISPOSAL.equals(this.hdrSceOrdTpCd)) {
                shipToCustCd = baseSoCustDtl.get(SHIP_TO_CUST_CD);
            }
        }
        // QC#54989 Add Start Wada
        if (ZYPCommonFunc.hasValue(shipToCustCd) && SCE_ORD_TP.DISPOSAL.equals(this.hdrSceOrdTpCd)) {
            if(isTecsys(this.hdrGlblCmpyCd, baseSoCustDtl.get(RTL_WH_CD))) {
                String wkInvtyLocCd = baseSoCustDtl.get(INVTY_LOC_CD);
                String wkShipToCustCd = baseSoCustDtl.get(SHIP_TO_CUST_CD);
                if (!S21StringUtil.isEquals(wkInvtyLocCd, wkShipToCustCd)) {
                    shipToCustCd = wkShipToCustCd;
                }
            }
        }
        // QC#54989 Add End

        // Get ship to cust info
        SHIP_TO_CUSTTMsg shipTo = getShipToCust(shipToCustCd);

        if (shipTo != null) {

            /* 12/21/2016 CITS T.Tokutomi QC#13893,13895 START */
            String[] locNum = null;
            if(isVendor(shipToCustCd)){
                locNum = new String[]{getDplyVndNm(shipToCustCd),""};
            } else {
                locNum = new String[] {shipTo.locNm.getValue(), shipTo.addlLocNm.getValue() };
            }
            /* 12/21/2016 CITS T.Tokutomi QC#13893,13895 END */
            String[] cutlocNum = getCutString(locNum, SO_CUST_LINE_LOC_NM_MAX_LG);

            String[] LineAddr = new String[] {shipTo.firstLineAddr.getValue(), shipTo.scdLineAddr.getValue(), shipTo.thirdLineAddr.getValue(), shipTo.frthLineAddr.getValue() };
            String[] cutLineAddr = getCutString(LineAddr, SO_CUST_LINE_ADDR_MAX_LG);

            SHPG_ORD_CUST_DTLTMsg shpgOrdCustDtlTMsg = new SHPG_ORD_CUST_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.glblCmpyCd, this.hdrGlblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soNum, this.soNum);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustDataTpCd, SO_CUST_DATA_TP.SHIP_TO);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineLocNm_01, cutlocNum[0]);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineLocNm_02, cutlocNum[1]);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineAddr_01, cutLineAddr[0]);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineAddr_02, cutLineAddr[1]);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineAddr_03, cutLineAddr[2]);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineAddr_04, cutLineAddr[3]);
            // QC#31042
            // Mod Start 2019/12/20 QC#55070
//            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctacPtnrPsnNm, getCtacPtnrPsnNm(baseSoCustDtl.get(SHIP_TO_CTAC_PTNR_PSN_NM), CTAC_PTNR_PSN_NM_MAX_LG));
            String ctacPtnrPsnNm = getCtacPtnrPsnNm(baseSoCustDtl.get(SHIP_TO_CTAC_PTNR_PSN_NM), CTAC_PTNR_PSN_NM_MAX_LG);
            if (null == ctacPtnrPsnNm && checkGetShipToCtacPsnNm()) {
                ctacPtnrPsnNm = getShipToCtacPsnNm(this.hdrGlblCmpyCd, baseSoCustDtl.get(TRX_HDR_NUM), CTAC_PTNR_PSN_NM_MAX_LG);
            }
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctacPtnrPsnNm, ctacPtnrPsnNm);
            // Mod End 2019/12/20 QC#55070
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctryCd, shipTo.ctryCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.stCd, shipTo.stCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctyAddr, shipTo.ctyAddr.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctacPtnrPsnTelNum, getCtacPtnrPsnTelNum(baseSoCustDtl.get(SHIP_TO_CTAC_PSN_TEL_NUM), shipTo.telNum.getValue()));
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.postCd, shipTo.postCd.getValue());
            // QC#31042
            // Mod Start 2019/12/17 QC#55070
//            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.shipToCtacPsnNm, getCtacPtnrPsnNm(baseSoCustDtl.get(SHIP_TO_CTAC_PTNR_PSN_NM), SHIP_TO_CTAC_PSN_NM_MAX_LG));
            String shipToCtacPsnNm = getCtacPtnrPsnNm(baseSoCustDtl.get(SHIP_TO_CTAC_PTNR_PSN_NM), SHIP_TO_CTAC_PSN_NM_MAX_LG);
            if (null == shipToCtacPsnNm && checkGetShipToCtacPsnNm()) {
                shipToCtacPsnNm = getShipToCtacPsnNm(this.hdrGlblCmpyCd, baseSoCustDtl.get(TRX_HDR_NUM), SHIP_TO_CTAC_PSN_NM_MAX_LG);
            }
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.shipToCtacPsnNm, shipToCtacPsnNm);
            // Mod End 2019/12/17 QC#55070

            S21ApiTBLAccessor.insert(shpgOrdCustDtlTMsg);

            if (!DB_RETURN_CD_NORMAL_END.equals(shpgOrdCustDtlTMsg.getReturnCode())) {

                throw new S21AbendException(NLZM2030E);
            }
        }
    }

    /**
     * Create Shipping Order Customer One Time User Ship-To
     * @param baseSoCustDtl BaseShippingOrderCustomerDetail
     */
    private void insertSoCustDtlShipToOneTmUsr(final Map<String, String> baseSoCustDtl) {

        // 12/06/2010 M.Takahashi add start
        String[] locNum = new String[]{baseSoCustDtl.get(SHIP_TO_LOC_NM), baseSoCustDtl.get(SHIP_TO_ADDL_LOC_NM)};
        String[] cutlocNum = getCutString(locNum, SO_CUST_LINE_LOC_NM_MAX_LG);

        String[] LineAddr = new String[]{baseSoCustDtl.get(SHIP_TO_FIRST_LINE_ADDR), baseSoCustDtl.get(SHIP_TO_SCD_LINE_ADDR)
                , baseSoCustDtl.get(SHIP_TO_THIRD_LINE_ADDR), baseSoCustDtl.get(SHIP_TO_FRTH_LINE_ADDR)};
        String[] cutLineAddr = getCutString(LineAddr,SO_CUST_LINE_ADDR_MAX_LG);
        // 12/06/2010 M.Takahashi add end

        SHPG_ORD_CUST_DTLTMsg shpgOrdCustDtlTMsg = new SHPG_ORD_CUST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.glblCmpyCd, this.hdrGlblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soNum, this.soNum);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustDataTpCd, SO_CUST_DATA_TP.SHIP_TO);
        // 12/06/2010 M.Takahashi modefy start
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineLocNm_01, cutlocNum[0]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineLocNm_02, cutlocNum[1]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineAddr_01, cutLineAddr[0]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineAddr_02, cutLineAddr[1]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineAddr_03, cutLineAddr[2]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineAddr_04, cutLineAddr[3]);
        // QC#31042
        // Mod Start 2019/12/20 QC#55070
//        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctacPtnrPsnNm, getCtacPtnrPsnNm(baseSoCustDtl.get(SHIP_TO_CTAC_PTNR_PSN_NM), CTAC_PTNR_PSN_NM_MAX_LG));
        String ctacPtnrPsnNm = getCtacPtnrPsnNm(baseSoCustDtl.get(SHIP_TO_CTAC_PTNR_PSN_NM), CTAC_PTNR_PSN_NM_MAX_LG);
        if (null == ctacPtnrPsnNm && checkGetShipToCtacPsnNm()) {
            ctacPtnrPsnNm = getShipToCtacPsnNm(this.hdrGlblCmpyCd, baseSoCustDtl.get(TRX_HDR_NUM), CTAC_PTNR_PSN_NM_MAX_LG);
        }
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctacPtnrPsnNm, ctacPtnrPsnNm);
        // Mod End 2019/12/20 QC#55070
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctryCd, baseSoCustDtl.get(SHIP_TO_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.stCd, baseSoCustDtl.get(SHIP_TO_ST_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctyAddr, baseSoCustDtl.get(SHIP_TO_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctacPtnrPsnTelNum, baseSoCustDtl.get(SHIP_TO_CTAC_PSN_TEL_NUM));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.postCd, baseSoCustDtl.get(SHIP_TO_POST_CD));
        // QC#31042
        // Mod Start 2019/12/17 QC#55070
//        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.shipToCtacPsnNm, getCtacPtnrPsnNm(baseSoCustDtl.get(SHIP_TO_CTAC_PTNR_PSN_NM), SHIP_TO_CTAC_PSN_NM_MAX_LG));
        String shipToCtacPsnNm = getCtacPtnrPsnNm(baseSoCustDtl.get(SHIP_TO_CTAC_PTNR_PSN_NM), SHIP_TO_CTAC_PSN_NM_MAX_LG);
        if (null == shipToCtacPsnNm && checkGetShipToCtacPsnNm()) {
            shipToCtacPsnNm = getShipToCtacPsnNm(this.hdrGlblCmpyCd, baseSoCustDtl.get(TRX_HDR_NUM), SHIP_TO_CTAC_PSN_NM_MAX_LG);
        }
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.shipToCtacPsnNm, shipToCtacPsnNm);
        // Mod End 2019/12/17 QC#55070

        S21ApiTBLAccessor.insert(shpgOrdCustDtlTMsg);

        if (!DB_RETURN_CD_NORMAL_END.equals(shpgOrdCustDtlTMsg.getReturnCode())) {

            throw new S21AbendException(NLZM2030E);
        }
    }

    /**
     * Create Shipping Order Customer One Time User Ship-To From CPO Sell-To
     * @param baseSoCustDtl BaseShippingOrderCustomerDetail
     */
    private void insertSoCustDtlShipToOneTmUsrFromSellTo(final Map<String, String> baseSoCustDtl) {

        String[] locNum = new String[]{baseSoCustDtl.get(SHIP_TO_LOC_NM), baseSoCustDtl.get(SHIP_TO_ADDL_LOC_NM)};
        String[] cutlocNum = getCutString(locNum, SO_CUST_LINE_LOC_NM_MAX_LG);

        String[] LineAddr = new String[]{baseSoCustDtl.get(SHIP_TO_FIRST_LINE_ADDR), baseSoCustDtl.get(SHIP_TO_SCD_LINE_ADDR)
                , baseSoCustDtl.get(SHIP_TO_THIRD_LINE_ADDR), baseSoCustDtl.get(SHIP_TO_FRTH_LINE_ADDR)};
        String[] cutLineAddr = getCutString(LineAddr,SO_CUST_LINE_ADDR_MAX_LG);

        SHPG_ORD_CUST_DTLTMsg shpgOrdCustDtlTMsg = new SHPG_ORD_CUST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.glblCmpyCd, this.hdrGlblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soNum, this.soNum);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustDataTpCd, SO_CUST_DATA_TP.SHIP_TO);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineLocNm_01, cutlocNum[0]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineLocNm_02, cutlocNum[1]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineAddr_01, cutLineAddr[0]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineAddr_02, cutLineAddr[1]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineAddr_03, cutLineAddr[2]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineAddr_04, cutLineAddr[3]);
        // QC#31042
        // Mod Start 2019/12/20 QC#55070
//        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctacPtnrPsnNm, getCtacPtnrPsnNm(baseSoCustDtl.get(SHIP_TO_CTAC_PTNR_PSN_NM), CTAC_PTNR_PSN_NM_MAX_LG));
        String ctacPtnrPsnNm = getCtacPtnrPsnNm(baseSoCustDtl.get(SHIP_TO_CTAC_PTNR_PSN_NM), CTAC_PTNR_PSN_NM_MAX_LG);
        if (null == ctacPtnrPsnNm && checkGetShipToCtacPsnNm()) {
            ctacPtnrPsnNm = getShipToCtacPsnNm(this.hdrGlblCmpyCd, baseSoCustDtl.get(TRX_HDR_NUM), CTAC_PTNR_PSN_NM_MAX_LG);
        }
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctacPtnrPsnNm, ctacPtnrPsnNm);
        // Mod End 2019/12/20 QC#55070
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctryCd, baseSoCustDtl.get(SHIP_TO_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.stCd, baseSoCustDtl.get(SHIP_TO_ST_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctyAddr, baseSoCustDtl.get(SHIP_TO_CTY_ADDR));

        // QC#55641
        if (SCE_ORD_TP.TECH_REQUEST.equals(this.hdrSceOrdTpCd)) {

            String shipToCustCd = baseSoCustDtl.get(SHIP_TO_CUST_CD);
            SHIP_TO_CUSTTMsg shipTo = getShipToCust(shipToCustCd);

            if (shipTo != null) {
                ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctacPtnrPsnTelNum, getCtacPtnrPsnTelNum(baseSoCustDtl.get(SHIP_TO_CTAC_PSN_TEL_NUM), shipTo.telNum.getValue()));
            } else {
                ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctacPtnrPsnTelNum, baseSoCustDtl.get(SHIP_TO_CTAC_PSN_TEL_NUM));
            }
        } else {
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctacPtnrPsnTelNum, baseSoCustDtl.get(SHIP_TO_CTAC_PSN_TEL_NUM));
        }

        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.postCd, baseSoCustDtl.get(SHIP_TO_POST_CD));
        // QC#31042
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.shipToCtacPsnNm, getCtacPtnrPsnNm(baseSoCustDtl.get(SHIP_TO_CTAC_PTNR_PSN_NM), SHIP_TO_CTAC_PSN_NM_MAX_LG));
        // Mod Start 2019/12/17 QC#55070
//        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.shipToCtacPsnNm, getCtacPtnrPsnNm(baseSoCustDtl.get(SHIP_TO_CTAC_PTNR_PSN_NM), SHIP_TO_CTAC_PSN_NM_MAX_LG));
        String shipToCtacPsnNm = getCtacPtnrPsnNm(baseSoCustDtl.get(SHIP_TO_CTAC_PTNR_PSN_NM), SHIP_TO_CTAC_PSN_NM_MAX_LG);
        if (null == shipToCtacPsnNm && checkGetShipToCtacPsnNm()) {
            shipToCtacPsnNm = getShipToCtacPsnNm(this.hdrGlblCmpyCd, baseSoCustDtl.get(TRX_HDR_NUM), SHIP_TO_CTAC_PSN_NM_MAX_LG);
        }
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.shipToCtacPsnNm, shipToCtacPsnNm);
        // Mod End 2019/12/17 QC#55070

        S21ApiTBLAccessor.insert(shpgOrdCustDtlTMsg);

        if (!DB_RETURN_CD_NORMAL_END.equals(shpgOrdCustDtlTMsg.getReturnCode())) {

            throw new S21AbendException(NLZM2030E);
        }
    }

    /**
     * Create getCutString Mod QC#51804.
     * @param String[] cutString
     * @param int maxLength
     */
    private String[] getCutString(String[] val,int maxLength) {

        boolean length_chk = false;
        String[] retval = new String[val.length];

        for (int i = 0; i < val.length; i++) {
            // QC#51804
            // if (val[i].length() > maxLength){
            if (val[i].getBytes().length > maxLength){

                length_chk = true;
                break;
            }
        }

        if (length_chk){

            String st = "";

            for (int i = 0; i < val.length; i++) {

                if (!("").equals(val[i])){

                    st = st + val[i] + " ";
                }
            }

            StringBuffer sb = new StringBuffer();
            sb.append(st.trim());
            
            StringBuffer bb;
            String tmp = "";
            byte[] retbyte;
            int cnt = 0;
            int strlen = 0;

            for (int i = 0; i < retval.length; i++) {
                
                if (sb.toString().matches("\\p{ASCII}*")) {

                    if (sb.length() > maxLength) {
    
                        retval[i] = sb.substring(0, maxLength);
                        sb.delete(0, maxLength);
    
                    } else {
    
                        retval[i] = sb.toString();
                        sb.delete(0, sb.length());
                    }
                    
                } else {
                    
                    if (sb.toString().getBytes().length > maxLength) {
                        
                        cnt = 0;
                        strlen = 0;
                        bb = new StringBuffer();
                        
                        for (int j = 0; j < sb.toString().length(); j++) {
                            
                            tmp = sb.substring(j, j + 1);
                            retbyte = tmp.getBytes();
                            
                            if (cnt + retbyte.length > maxLength) {
                                
                                retval[i] = bb.toString();
                                break;
                                
                            } else {
                                
                                bb.append(tmp);
                                cnt += retbyte.length;
                            }
                            
                            strlen++;
                        }
                        
                        retval[i] = bb.toString();
                        sb.delete(0, strlen);
                        
                    } else {
                        
                        retval[i] = sb.toString();
                        sb.delete(0, sb.length());
                    }
                }
            }

        } else {

            retval = val;
        }

        return retval;
    }

    /**
     * Insert Shipping Order Customer Sell-To
     * @param baseSoCustDtl BaseShippingOrderCustomerDetail
     */
    private void insertSoCustDtlSellTo(final Map<String, String> baseSoCustDtl) {

        // Get sell to cust info
        SELL_TO_CUSTTMsg search = new SELL_TO_CUSTTMsg();
        search.setSQLID(SQL_ID_SELL_TO_CUST_003);
        search.setConditionValue(TAK_GLBL_CMPY_CD, this.hdrGlblCmpyCd);
        search.setConditionValue(TAK_SELL_TO_CUST_CD, baseSoCustDtl.get(SELL_TO_CUST_CD));

        SELL_TO_CUSTTMsgArray sellToCustLst = (SELL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(search);
        SELL_TO_CUSTTMsg sellTo = new SELL_TO_CUSTTMsg();

        if (sellToCustLst.length() > 0) {

            sellTo = sellToCustLst.no(0);

            /* 12/21/2016 CITS T.Tokutomi QC#13893,13895 START */
            String shipToCustCd = baseSoCustDtl.get(SHIP_TO_CUST_CD);
            String[] locNum = null;
            if(isVendor(shipToCustCd)){
                locNum = new String[]{getDplyVndNm(shipToCustCd),""};
            } else {
                locNum = new String[] {sellTo.locNm.getValue(), sellTo.addlLocNm.getValue() };
            }

            /* 12/21/2016 CITS T.Tokutomi QC#13893,13895 END */
            String[] cutlocNum = getCutString(locNum, SO_CUST_LINE_LOC_NM_MAX_LG);

            String[] LineAddr = new String[] {sellTo.firstLineAddr.getValue(), sellTo.scdLineAddr.getValue(), sellTo.thirdLineAddr.getValue(), sellTo.frthLineAddr.getValue() };
            String[] cutLineAddr = getCutString(LineAddr, SO_CUST_LINE_ADDR_MAX_LG);

            SHPG_ORD_CUST_DTLTMsg shpgOrdCustDtlTMsg = new SHPG_ORD_CUST_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.glblCmpyCd, this.hdrGlblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soNum, this.soNum);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustDataTpCd, SO_CUST_DATA_TP.SELL_TO);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineLocNm_01, cutlocNum[0]);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineLocNm_02, cutlocNum[1]);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineAddr_01, cutLineAddr[0]);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineAddr_02, cutLineAddr[1]);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineAddr_03, cutLineAddr[2]);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.soCustLineAddr_04, cutLineAddr[3]);
            // QC#31042
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctacPtnrPsnNm, getCtacPtnrPsnNm(baseSoCustDtl.get(SELL_TO_CTAC_PTNR_PSN_NM), CTAC_PTNR_PSN_NM_MAX_LG));
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctryCd, sellTo.ctryCd);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.stCd, sellTo.stCd);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctyAddr, sellTo.ctyAddr);
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.ctacPtnrPsnTelNum, getCtacPtnrPsnTelNum(baseSoCustDtl.get(SELL_TO_CTAC_PSN_TEL_NUM), sellTo.telNum.getValue()));
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.postCd, sellTo.postCd);
            // QC#31042
            ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlTMsg.shipToCtacPsnNm, getCtacPtnrPsnNm(baseSoCustDtl.get(SELL_TO_CTAC_PTNR_PSN_NM), SHIP_TO_CTAC_PSN_NM_MAX_LG));

            S21ApiTBLAccessor.insert(shpgOrdCustDtlTMsg);

            if (!DB_RETURN_CD_NORMAL_END.equals(shpgOrdCustDtlTMsg.getReturnCode())) {

                throw new S21AbendException(NLZM2030E);
            }

        } else if (ZYPCommonFunc.hasValue(baseSoCustDtl.get(VND_VND_CD))) {

            insertSoCustDtlSellToVnd(baseSoCustDtl);
        }
    }

    /**
     * Insert Shipping Order Customer Vender Base Sell-To
     * @param baseSoCustDtl BaseShippingOrderCustomerDetail
     */
    private void insertSoCustDtlSellToVnd(final Map<String, String> baseSoCustDtl) {

        SHPG_ORD_CUST_DTLTMsg shpgOrdCustDtlT = new SHPG_ORD_CUST_DTLTMsg();

        setTmsgSoCustDtlVnd(baseSoCustDtl, shpgOrdCustDtlT);

        shpgOrdCustDtlT.soCustDataTpCd.setValue(SO_CUST_DATA_TP.SELL_TO);

        S21ApiTBLAccessor.insert(shpgOrdCustDtlT);

        if (!DB_RETURN_CD_NORMAL_END.equals(shpgOrdCustDtlT.getReturnCode())) {

            throw new S21AbendException(NLZM2030E);
        }
    }

    /**
     * Insert Shipping Order Customer Bill-To
     * @param baseSoCustDtl BaseShippingOrderCustomerDetail
     */
    private void insertSoCustDtlBillTo(final Map<String, String> baseSoCustDtl) {

        String[] locNum = new String[]{baseSoCustDtl.get(BILL_LOC_NM), baseSoCustDtl.get(BILL_ADDL_LOC_NM)};
        String[] cutlocNum = getCutString(locNum, SO_CUST_LINE_LOC_NM_MAX_LG);

        String[] LineAddr = new String[]{baseSoCustDtl.get(BILL_FIRST_LINE_ADDR), baseSoCustDtl.get(BILL_SCD_LINE_ADDR), baseSoCustDtl.get(BILL_THIRD_LINE_ADDR), baseSoCustDtl.get(BILL_FRTH_LINE_ADDR)};
        String[] cutLineAddr = getCutString(LineAddr,SO_CUST_LINE_ADDR_MAX_LG);

        SHPG_ORD_CUST_DTLTMsg shpgOrdCustDtlT = new SHPG_ORD_CUST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.glblCmpyCd, this.hdrGlblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soNum, this.soNum);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustDataTpCd, SO_CUST_DATA_TP.BILL_TO);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineLocNm_01, cutlocNum[0]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineLocNm_02, cutlocNum[1]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineAddr_01, cutLineAddr[0]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineAddr_02, cutLineAddr[1]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineAddr_03, cutLineAddr[2]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineAddr_04, cutLineAddr[3]);
        // QC#31042
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.ctacPtnrPsnNm, getCtacPtnrPsnNm(baseSoCustDtl.get(BILL_TO_CTAC_PTNR_PSN_NM), CTAC_PTNR_PSN_NM_MAX_LG));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.ctryCd, baseSoCustDtl.get(BILL_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.stCd, baseSoCustDtl.get(BILL_ST_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.ctyAddr, baseSoCustDtl.get(BILL_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.ctacPtnrPsnTelNum, getCtacPtnrPsnTelNum(baseSoCustDtl.get(BILL_TO_CTAC_PSN_TEL_NUM), baseSoCustDtl.get(BILL_TEL_NUM)));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.postCd, baseSoCustDtl.get(BILL_POST_CD));
        // QC#31042
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.shipToCtacPsnNm, getCtacPtnrPsnNm(baseSoCustDtl.get(BILL_TO_CTAC_PTNR_PSN_NM), SHIP_TO_CTAC_PSN_NM_MAX_LG));

        S21ApiTBLAccessor.insert(shpgOrdCustDtlT);

        if (!DB_RETURN_CD_NORMAL_END.equals(shpgOrdCustDtlT.getReturnCode())) {

            throw new S21AbendException(NLZM2030E);
        }
    }

    /* 12/21/2016 CITS T.Tokutomi QC#13893,13895 START*/
    /**
     * getDplyVndNm
     * @param vndCd
     * @return vendor name(Suplier Name + ' - ' + Site)
     */
    private String getDplyVndNm(String vndCd){

        if(!ZYPCommonFunc.hasValue(vndCd)){
            return "";
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.hdrGlblCmpyCd);
        queryParam.put("vndCd", vndCd);

        String dplyVndNm = (String) ssmBatchClient.queryObject("getDplyVndNm", queryParam);

        if (!ZYPCommonFunc.hasValue(dplyVndNm)) {

            return "";
        }

        return dplyVndNm;
    }

    /**
     * isVendor
     * @param vndCd
     * @return true:Vendor
     */
    private boolean isVendor(String vndCd){

        if(!ZYPCommonFunc.hasValue(vndCd)){
            return false;
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.hdrGlblCmpyCd);
        queryParam.put("vndCd", vndCd);

        String result = (String) ssmBatchClient.queryObject("getVnd", queryParam);

        if (!ZYPCommonFunc.hasValue(result)) {

            return false;
        }

        return true;
    }
    /* 12/21/2016 CITS T.Tokutomi QC#13893,13895 END*/

    /**
     * Insert Shipping Order Customer Vender Base Bill-To
     * @param baseSoCustDtl BaseShippingOrderCustomerDetail
     */
    private void insertSoCustDtlBillToVnd(final Map<String, String> baseSoCustDtl) {

        SHPG_ORD_CUST_DTLTMsg shpgOrdCustDtlT = new SHPG_ORD_CUST_DTLTMsg();

        setTmsgSoCustDtlVnd(baseSoCustDtl, shpgOrdCustDtlT);

        shpgOrdCustDtlT.soCustDataTpCd.setValue(SO_CUST_DATA_TP.BILL_TO);

        S21ApiTBLAccessor.insert(shpgOrdCustDtlT);

        if (!DB_RETURN_CD_NORMAL_END.equals(shpgOrdCustDtlT.getReturnCode())) {

            throw new S21AbendException(NLZM2030E);
        }
    }

    /**
     * Insert Shipping Order Customer Return-To
     * @param baseSoCustDtl BaseShippingOrderCustomerDetail
     */
    private void insertSoCustDtlRtrnTo(final Map<String, String> baseSoCustDtl) {
        // QC#31042
        String ctacPtnrPsnNm = getSoCustDtlCtacPtnrPsnNm(baseSoCustDtl.get(RTRN_FIRST_REF_CMNT_TXT), baseSoCustDtl.get(RTRN_SCD_REF_CMNT_TXT), CTAC_PTNR_PSN_NM_MAX_LG);
        String shipToCtacPsnNm = getSoCustDtlCtacPtnrPsnNm(baseSoCustDtl.get(RTRN_FIRST_REF_CMNT_TXT), baseSoCustDtl.get(RTRN_SCD_REF_CMNT_TXT), SHIP_TO_CTAC_PSN_NM_MAX_LG);

        String[] locNum = new String[]{baseSoCustDtl.get(RTRN_LOC_NM), baseSoCustDtl.get(RTRN_ADDL_LOC_NM)};
        String[] cutlocNum = getCutString(locNum, SO_CUST_LINE_LOC_NM_MAX_LG);

        String[] LineAddr = new String[]{baseSoCustDtl.get(RTRN_FIRST_LINE_ADDR), baseSoCustDtl.get(RTRN_SCD_LINE_ADDR), baseSoCustDtl.get(RTRN_THIRD_LINE_ADDR), baseSoCustDtl.get(RTRN_FRTH_LINE_ADDR)};
        String[] cutLineAddr = getCutString(LineAddr,SO_CUST_LINE_ADDR_MAX_LG);

        SHPG_ORD_CUST_DTLTMsg shpgOrdCustDtlT = new SHPG_ORD_CUST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.glblCmpyCd, this.hdrGlblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soNum, this.soNum);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustDataTpCd, SO_CUST_DATA_TP.RETURN_TO);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineLocNm_01, cutlocNum[0]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineLocNm_02, cutlocNum[1]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineAddr_01, cutLineAddr[0]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineAddr_02, cutLineAddr[1]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineAddr_03, cutLineAddr[2]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineAddr_04, cutLineAddr[3]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.ctacPtnrPsnNm, ctacPtnrPsnNm);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.ctryCd, baseSoCustDtl.get(RTRN_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.stCd, baseSoCustDtl.get(RTRN_ST_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.ctyAddr, baseSoCustDtl.get(RTRN_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.ctacPtnrPsnTelNum, baseSoCustDtl.get(RTRN_TEL_NUM));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.postCd, baseSoCustDtl.get(RTRN_POST_CD));
        // QC#31042
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.shipToCtacPsnNm, shipToCtacPsnNm);

        S21ApiTBLAccessor.insert(shpgOrdCustDtlT);

        if (!DB_RETURN_CD_NORMAL_END.equals(shpgOrdCustDtlT.getReturnCode())) {

            throw new S21AbendException(NLZM2030E);
        }
    }

    /**
     * Insert Shipping Order Message Text Data
     * @param txtSqNum TextSequenceNumber
     * @param soMsgDescTxt ShippingOrderMessageDescriptionTextforWMS
     */
    private void insertSoMsgMsg(final String txtSqNum, final String soMsgDescTxt) {

        SHPG_ORD_MSGTMsg shpgOrdMsgT = new SHPG_ORD_MSGTMsg();
        shpgOrdMsgT.glblCmpyCd.setValue(this.hdrGlblCmpyCd);
        shpgOrdMsgT.soNum.setValue(this.soNum);
        shpgOrdMsgT.soMsgTpCd.setValue(SHPG_ORD_MSG_TP.CPO_INFORMATION);
        shpgOrdMsgT.txtSqNum.setValue(txtSqNum);
        shpgOrdMsgT.soMsgDescTxt.setValue(soMsgDescTxt);

        S21ApiTBLAccessor.insert(shpgOrdMsgT);

        if (!DB_RETURN_CD_NORMAL_END.equals(shpgOrdMsgT.getReturnCode())) {

            throw new S21AbendException(NLZM2030E);
        }
    }

    /**
     * Insert Shipping Order Message Routing Data
     * @param soMsgDescTxt ShippingOrderMessageDescriptionTextforWMS
     */
    private void insertSoMsgRte(final String soMsgDescTxt) {

        SHPG_ORD_MSGTMsg shpgOrdMsgT = new SHPG_ORD_MSGTMsg();
        shpgOrdMsgT.glblCmpyCd.setValue(this.hdrGlblCmpyCd);
        shpgOrdMsgT.soNum.setValue(this.soNum);
        shpgOrdMsgT.soMsgTpCd.setValue(SHPG_ORD_MSG_TP.ROUTING_MESSAGE);
        shpgOrdMsgT.txtSqNum.setValue(SO_MSG_RTE_TXT_SQ_NUM);
        shpgOrdMsgT.soMsgDescTxt.setValue(soMsgDescTxt);

        S21ApiTBLAccessor.insert(shpgOrdMsgT);

        if (!DB_RETURN_CD_NORMAL_END.equals(shpgOrdMsgT.getReturnCode())) {

            throw new S21AbendException(NLZM2030E);
        }
    }

    /**
     * Set Shipping Order Header TMsg
     * @param baseSoHdr BaseShippingOrderHeader
     * @param shpgOrdT ShippingOrderHeaderTMsg
     */
    private void setTmsgSoHdr(final Map<String, String> baseSoHdr, SHPG_ORDTMsg shpgOrdT) {

        String shpgFrceFlg = this.hdrShpgFrceFlg;

        String dnldTmTs = this.slsDt + this.sysTm;
        BigDecimal totRoundShipAmt = overflowToBorder(this.totShipAmt, TOT_SHIP_AMT_MAX_LG, TOT_SHIP_AMT_DECL_LG);

        if (!ZYPCommonFunc.hasValue(shpgFrceFlg)) {

            shpgFrceFlg = ZYPConstant.FLG_OFF_N;
        }

        // Set TMsg
        shpgOrdT.glblCmpyCd.setValue(this.hdrGlblCmpyCd);
        shpgOrdT.soNum.setValue(this.soNum);
        shpgOrdT.whCd.setValue(baseSoHdr.get(RTL_WH_CD));
        shpgOrdT.trxHdrNum.setValue(baseSoHdr.get(TRX_HDR_NUM));
        shpgOrdT.trxCd.setValue(baseSoHdr.get(TRX_CD));
        shpgOrdT.trxRsnCd.setValue(baseSoHdr.get(TRX_RSN_CD));
        shpgOrdT.shpgStsCd.setValue(SHPG_STS.S_OR_O_PRINTED);
        shpgOrdT.sceOrdTpCd.setValue(this.hdrSceOrdTpCd);
        shpgOrdT.pickTktNum.setValue(this.soNum);
        shpgOrdT.dnldTmTs.setValue(dnldTmTs);
        shpgOrdT.uccNumCd.setValue(UCC_NUM_CD_D);
        shpgOrdT.totShipAmt.setValue(totRoundShipAmt);
        // QC#23784, 24251 Update
        if (NLXSceConst.SCE_ORD_TP_CD_SC.equals(this.hdrSceOrdTpCd) //
                || NLXSceConst.SCE_ORD_TP_CD_CC.equals(this.hdrSceOrdTpCd)) {
            shpgOrdT.wmsDropFlg.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            shpgOrdT.wmsDropFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
        shpgOrdT.soPrtFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.drctShipTpCd.setValue(DRCT_SHIP_TP.N_OR_A);
        shpgOrdT.shpgFrceFlg.setValue(shpgFrceFlg);
        shpgOrdT.trxSrcTpCd.setValue(baseSoHdr.get(TRX_SRC_TP_CD));

        shpgOrdT.wmsDropRqstFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.wmsRtePathCd.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.preIstlShopRqstFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.rtrnItemInclFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.delyReqFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.tempSchdFlg.setValue(ZYPConstant.FLG_OFF_N);
        
        // 201/12/28 QC#18460(SOL#087) T,Hakodate ADD START
        String schdDelyTm = getRequestInstallTime( baseSoHdr.get(TRX_HDR_NUM));
        if (ZYPCommonFunc.hasValue(schdDelyTm)) {
            shpgOrdT.schdDelyTm.setValue(schdDelyTm);
        }
        ZYPEZDItemValueSetter.setValue(shpgOrdT.techMeetTruckFlg, getTechMeetTruckFlg(baseSoHdr.get(TRX_HDR_NUM)));
        // 201/12/28 QC#18460(SOL#087) T,Hakodate ADD END

    }

    /**
     * Set Shipping Order Header TMsg CPO type ROSS type
     * @param baseSoHdr BaseShippingOrderHeader
     * @param shpgOrdT ShippingOrderHeaderTMsg
     */
    private void setTmsgSoHdrCpoRoss(final Map<String, String> baseSoHdr, SHPG_ORDTMsg shpgOrdT) {

        String asnReqFlg = getAsnRequiredFlag(baseSoHdr);

        // Set TMsg
        shpgOrdT.sellToCustCd.setValue(baseSoHdr.get(SELL_TO_CUST_CD));
        shpgOrdT.billToCustCd.setValue(baseSoHdr.get(BILL_TO_CUST_CD));
        shpgOrdT.shipToCustCd.setValue(baseSoHdr.get(SHIP_TO_CUST_CD));
        shpgOrdT.custIssPoNum.setValue(baseSoHdr.get(CUST_ISS_PO_NUM));

        if (SCE_ORD_TP.DIRECT_SALES.equals(this.hdrSceOrdTpCd) && ZYPCommonFunc.hasValue(baseSoHdr.get(RQST_CARR_CD))) {

            shpgOrdT.vndCd.setValue(baseSoHdr.get(RQST_CARR_CD));

        } else {

            shpgOrdT.vndCd.setValue(baseSoHdr.get(CARR_CD));
        }

        ZYPEZDItemValueSetter.setValue(shpgOrdT.carrCd, shpgOrdT.vndCd);
        shpgOrdT.rddDt.setValue(baseSoHdr.get(RDD_DT));
        shpgOrdT.rsdDt.setValue(baseSoHdr.get(RSD_DT));
        shpgOrdT.asnReqFlg.setValue(asnReqFlg);

        if (ZYPConstant.FLG_ON_Y.equals(asnReqFlg)) {

            shpgOrdT.printSccLbFlg.setValue(baseSoHdr.get(PRINT_SCC_LB_FLG));
            shpgOrdT.printUccLbFlg.setValue(baseSoHdr.get(PRINT_UCC_LB_FLG));
            shpgOrdT.mixPltAllwFlg.setValue(baseSoHdr.get(MIX_PLT_ALLW_FLG));
            shpgOrdT.printNonAsnUccLbFlg.setValue(baseSoHdr.get(NON_ASN_UCC_LB_FLG));

        } else {

            shpgOrdT.printSccLbFlg.setValue(ZYPConstant.FLG_OFF_N);
            shpgOrdT.printUccLbFlg.setValue(ZYPConstant.FLG_OFF_N);
            shpgOrdT.mixPltAllwFlg.setValue(ZYPConstant.FLG_OFF_N);
            shpgOrdT.printPltUccLbFlg.setValue(ZYPConstant.FLG_OFF_N);
            shpgOrdT.printNonAsnUccLbFlg.setValue(ZYPConstant.FLG_OFF_N);
        }

        shpgOrdT.dropShipFlg.setValue(baseSoHdr.get(DROP_SHIP_FLG));
        shpgOrdT.carrAcctNum.setValue(baseSoHdr.get(CARR_ACCT_NUM));
    }

    /**
     * Set Shipping Order Header TMsg Inventry type Work type
     * @param baseSoHdr BaseShippingOrderHeader
     * @param shpgOrdT ShippingOrderHeaderTMsg
     */
    private void setTmsgSoHdrInvtyWrk(final Map<String, String> baseSoHdr, SHPG_ORDTMsg shpgOrdT) {

        shpgOrdT.asnReqFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.printSccLbFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.printUccLbFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.mixPltAllwFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.printPltUccLbFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.printNonAsnUccLbFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.dropShipFlg.setValue(ZYPConstant.FLG_OFF_N);
        shpgOrdT.cmpyStrInd.setValue(ZYPConstant.FLG_OFF_0);
    }

    /**
     * Set Shipping Order Header TMsg Shipping Force Flag OFF
     * @param baseSoHdr BaseShippingOrderHeader
     * @param shpgOrdT ShippingOrderHeaderTMsg
     */
    private void setTmsgSoHdrShpgFrceFlgOff(final Map<String, String> baseSoHdr, SHPG_ORDTMsg shpgOrdT) {

        ZYPEZDItemValueSetter.setValue(shpgOrdT.rteGrpNum, baseSoHdr.get(RTE_GRP_NUM));
        ZYPEZDItemValueSetter.setValue( shpgOrdT.shpgSvcLvlCd, baseSoHdr.get(SHPG_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.shpgModeCd, baseSoHdr.get(SHPG_MODE_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.psdDt, baseSoHdr.get(PSD_DT));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.pddDt, baseSoHdr.get(PDD_DT));
    }

    /**
     * Set Shipping Order Header TMsg Shipping Force Flag ON
     * @param baseSoHdr BaseShippingOrderHeader
     * @param shpgOrdT ShippingOrderHeaderTMsg
     */
    private void setTmsgSoHdrShpgFrceFlgOn(final Map<String, String> baseSoHdr, SHPG_ORDTMsg shpgOrdT) {

        ZYPEZDItemValueSetter.setValue(shpgOrdT.shpgSvcLvlCd, baseSoHdr.get(ORIG_SHPG_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdT.psdDt, this.slsDt);
    }

    /**
     * Set Shipping Order Customer Detail TMsg Vender Base
     * @param baseSoCustDtl BaseShippingOrderCustomerDetail
     * @param shpgOrdT ShippingOrderCustomerDetailTMsg
     */
    private void setTmsgSoCustDtlVnd(final Map<String, String> baseSoCustDtl, SHPG_ORD_CUST_DTLTMsg shpgOrdCustDtlT) {
        // QC#31042
        String ctacPtnrPsnNm = getSoCustDtlCtacPtnrPsnNm(baseSoCustDtl.get(VND_FIRST_REF_CMNT_TXT), baseSoCustDtl.get(VND_SCD_REF_CMNT_TXT), CTAC_PTNR_PSN_NM_MAX_LG);
        String shipToCtacPsnNm = getSoCustDtlCtacPtnrPsnNm(baseSoCustDtl.get(VND_FIRST_REF_CMNT_TXT), baseSoCustDtl.get(VND_SCD_REF_CMNT_TXT), SHIP_TO_CTAC_PSN_NM_MAX_LG);

        /* 12/21/2016 CITS T.Tokutomi QC#13893,13895 START */
        String[] locNum = new String[]{getDplyVndNm(baseSoCustDtl.get(VND_VND_CD)), ""};
        /* 12/21/2016 CITS T.Tokutomi QC#13893,13895 END */
        String[] cutlocNum = getCutString(locNum, SO_CUST_LINE_LOC_NM_MAX_LG);

        String[] LineAddr = new String[]{baseSoCustDtl.get(VND_FIRST_LINE_ADDR), baseSoCustDtl.get(VND_SCD_LINE_ADDR), baseSoCustDtl.get(VND_THIRD_LINE_ADDR), baseSoCustDtl.get(VND_FRTH_LINE_ADDR)};
        String[] cutLineAddr = getCutString(LineAddr, SO_CUST_LINE_ADDR_MAX_LG);

        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.glblCmpyCd, this.hdrGlblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soNum, this.soNum);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineLocNm_01, cutlocNum[0]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineLocNm_02, cutlocNum[1]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineAddr_01, cutLineAddr[0]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineAddr_02, cutLineAddr[1]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineAddr_03, cutLineAddr[2]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.soCustLineAddr_04, cutLineAddr[3]);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.ctacPtnrPsnNm, ctacPtnrPsnNm);
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.ctryCd, baseSoCustDtl.get(VND_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.stCd, baseSoCustDtl.get(VND_ST_CD));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.ctyAddr, baseSoCustDtl.get(VND_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.ctacPtnrPsnTelNum, baseSoCustDtl.get(VND_TEL_NUM));
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.postCd, baseSoCustDtl.get(VND_POST_CD));
        // QC#31042
        ZYPEZDItemValueSetter.setValue(shpgOrdCustDtlT.shipToCtacPsnNm, shipToCtacPsnNm);
    }

    /**
     * Get Base Mdse Code
     * @param mdseCd MdseCode
     * @return BaseMdseCode
     */
    private String getChildOrdTakeMdseCd(final String mdseCd) {

        if (mdseCd.length() > CHILD_ORD_TAKE_MDSE_CD_LG) {

            return mdseCd.substring(0, CHILD_ORD_TAKE_MDSE_CD_LG);

        } else {

            return mdseCd;
        }
    }

    /**
     * Get Shipping Order Customer Partner Person. Mod QC#31042.
     * @param firstRefCmntTxt FirstRefarenceComment
     * @param scdRefCmntTxt ScondRefarenceComment
     * @param size subString size
     * @return ShippingOrderCustomerPartnerPerson
     */
    private String getSoCustDtlCtacPtnrPsnNm(final String firstRefCmntTxt, final String scdRefCmntTxt, int size) {

        String ctacPtnrPsnNm = null;

        if (ZYPCommonFunc.hasValue(firstRefCmntTxt) && ZYPCommonFunc.hasValue(scdRefCmntTxt)) {

            ctacPtnrPsnNm = firstRefCmntTxt + scdRefCmntTxt;

        } else if (ZYPCommonFunc.hasValue(firstRefCmntTxt) && !ZYPCommonFunc.hasValue(scdRefCmntTxt)) {

            ctacPtnrPsnNm = firstRefCmntTxt;

        } else if (!ZYPCommonFunc.hasValue(firstRefCmntTxt) && ZYPCommonFunc.hasValue(scdRefCmntTxt)) {

            ctacPtnrPsnNm = scdRefCmntTxt;
        }
        // QC#31042
        if (ZYPCommonFunc.hasValue(ctacPtnrPsnNm) && ctacPtnrPsnNm.length() > size) {

            ctacPtnrPsnNm = ctacPtnrPsnNm.substring(0, size);
        }

        return ctacPtnrPsnNm;
    }

    /**
     * getCtacPtnrPsnNm. Mod QC#31042
     * @param ctacPtnrPsnNm String
     * @param size int
     * @return String
     */
    private String getCtacPtnrPsnNm(String ctacPtnrPsnNm, int size) {

        if (ZYPCommonFunc.hasValue(ctacPtnrPsnNm) && ctacPtnrPsnNm.length() > size) {

            return ctacPtnrPsnNm.substring(0, size);

        } else if (!ZYPCommonFunc.hasValue(ctacPtnrPsnNm)) {

            return null;
        }

        return ctacPtnrPsnNm;
    }

    /**
     * getCtacPtnrPsnTelNum
     * @param ctacPtnrPsnTelNum String
     * @param mainTelNum String
     * @return String
     */
    private String getCtacPtnrPsnTelNum(String ctacPtnrPsnTelNum, String mainTelNum) {

        if (!ZYPCommonFunc.hasValue(ctacPtnrPsnTelNum)) {

            return mainTelNum;
        }

        return ctacPtnrPsnTelNum;
    }

    /**
     * Get Shipping Order Message Base Routing Guide Security Flag List
     * @param baseSoMsg BaseShippingOrderMessage
     * @return RoutingGuideSecurityFlagList
     */
    private List<String> getSoMsgRteSectFlgs(final Map<String, String> baseSoMsg) {

        List<String> rteGuideSectFlgs = new ArrayList<String>();

        rteGuideSectFlgs.add(baseSoMsg.get(RTE_GUIDE_SECT_FIRST_FLG));
        rteGuideSectFlgs.add(baseSoMsg.get(RTE_GUIDE_SECT_SCD_FLG));
        rteGuideSectFlgs.add(baseSoMsg.get(RTE_GUIDE_SECT_THIRD_FLG));
        rteGuideSectFlgs.add(baseSoMsg.get(RTE_GUIDE_SECT_FRTH_FLG));
        rteGuideSectFlgs.add(baseSoMsg.get(RTE_GUIDE_SECT_FIFTH_FLG));
        rteGuideSectFlgs.add(baseSoMsg.get(RTE_GUIDE_SECT_SIXTH_FLG));
        rteGuideSectFlgs.add(baseSoMsg.get(RTE_GUIDE_SECT_SVNTH_FLG));
        rteGuideSectFlgs.add(baseSoMsg.get(RTE_GUIDE_SECT_EIGHTH_FLG));

        return rteGuideSectFlgs;
    }

    /**
     * Create Shipping Order Message Routing Description Text for WMS List
     * @param rteGuideSectFlgs RoutingGuideSecurityFlagList
     * @return ShippingOrderMessageDescriptionTextforWMSList
     */
    private String createSoMsgRteDescTxt(final List<String> rteGuideSectFlgs) {

        String soMsgDescTxt = "";
        boolean separateFlg = false;
        int sectNum = 0;

        for (String rteGuideSectFlg : rteGuideSectFlgs) {

            sectNum++;

            if (ZYPConstant.FLG_ON_Y.equals(rteGuideSectFlg)) {

                if (!separateFlg) {

                    separateFlg = true;

                } else {

                    soMsgDescTxt += SO_MSG_RTE_DESC_TXT_INFIX;
                }

                soMsgDescTxt += SO_MSG_RTE_DESC_TXT_NUM_SYMBOL + String.valueOf(sectNum);
            }
        }

        if (ZYPCommonFunc.hasValue(soMsgDescTxt)) {

            soMsgDescTxt = SO_MSG_RTE_DESC_TXT_PREFIX + " " + soMsgDescTxt + " " + SO_MSG_RTE_DESC_TXT_SFX;
        }

        return soMsgDescTxt;
    }

    /**
     * Get Transaction Source Type Code For SCE Order Type Code
     * @param sceOrdTpCd SCE Order Type Code
     * @return TransactionSourceTypeCode
     */
    private String getTrxSrcTpCd(final String sceOrdTpCd) {

        if (SCE_ORD_TP.REGULAR.equals(sceOrdTpCd)
                || SCE_ORD_TP.TRIAL_FOR_SALE.equals(sceOrdTpCd)
                || SCE_ORD_TP.TRIAL_FOR_USE.equals(sceOrdTpCd)
                || SCE_ORD_TP.LOAN.equals(sceOrdTpCd)
                || SCE_ORD_TP.EXPORT.equals(sceOrdTpCd)
                || SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd)
                || SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd)
                || SCE_ORD_TP.DC_TRANSFER_LOSS.equals(sceOrdTpCd)) {

            return TRX_SRC_TP.WHOLE_SALES;

        } else if (SCE_ORD_TP.DISPOSAL.equals(sceOrdTpCd)
                || SCE_ORD_TP.ITEM_CHANGE.equals(sceOrdTpCd)
                || SCE_ORD_TP.STOCK_STATUS_CHANGE.equals(sceOrdTpCd)
                || SCE_ORD_TP.DAMAGED_CLAIM_ENTRY.equals(sceOrdTpCd)
                || SCE_ORD_TP.DAMAGED_CLAIM_COMPLETION.equals(sceOrdTpCd)
                || SCE_ORD_TP.INTERNAL_TRANSFER.equals(sceOrdTpCd)
                || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd)
                || SCE_ORD_TP.REFURBISH_EXPENSE.equals(sceOrdTpCd)
                || SCE_ORD_TP.SUB_WH_CHANGE.equals(sceOrdTpCd)
                || SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)) {

            return TRX_SRC_TP.MOVEMENT;

        } else if (SCE_ORD_TP.KITTING.equals(sceOrdTpCd)) {

            return TRX_SRC_TP.INTERNAL_KITTING;

        } else if (SCE_ORD_TP.REFURBISH.equals(sceOrdTpCd)) {

            return TRX_SRC_TP.REFURBISH;

        } else if (SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(sceOrdTpCd) || SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(sceOrdTpCd)) {

            return TRX_SRC_TP.VENDOR_RETURN;

        // QC#5728 Add Start 2016/03/18
        } else if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {

            return TRX_SRC_TP.TECH_REQUEST;
            // QC#5728 Add End 2016/03/18

        /* 03/21/2016 CSAI Y.Imazu Add QC#5742 START */
        } else if (SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd)) {

            return TRX_SRC_TP.BUY_BACK;

        } else if (SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd)) {

            return TRX_SRC_TP.INTERNAL_UN_KITTING;

        } else if (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd)) {

            return TRX_SRC_TP.REMANUFACTURING;
            /* 03/21/2016 CSAI Y.Imazu Add QC#5742 END */

        } else if (SCE_ORD_TP.REMAN_LOCATOR_TRANSFER.equals(sceOrdTpCd)) {

            return TRX_SRC_TP.REMAN_PARTS_LOCATOR_TRANSFER;

        } else if (SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY.equals(sceOrdTpCd)) {

            return TRX_SRC_TP.REMAN_PARTS_BACK_INVENTORY;

        } else if (SCE_ORD_TP.REMAN_PARTS_USAGE.equals(sceOrdTpCd)) {

            return TRX_SRC_TP.REMAN_PARTS_USAGE;

        } else {

            return null;
        }
    }

    /**
     * Write operation error information
     * @param msgId MesseageID
     * @param msgMap S21ApiMessageMap
     */
    private void writeOperationErr(final String msgId, S21ApiMessageMap msgMap) {

        S21InfoLogOutput.println(msgId);
        msgMap.addXxMsgId(msgId);
    }

    /**
     * @param str String
     * @return BigDecimal
     */
    private BigDecimal nullToZero(BigDecimal val) {

        if (val == null) {

            return BigDecimal.ZERO;

        } else {

            return val;
        }
    }

    /**
     * @param str String
     * @return String
     */
    private String nullToEmpty(String str) {

        if (str == null) {

            return "";

        } else {

            return str;
        }
    }

    /**
     * <pre>
     * ABS
     * If val=null then return null
     * </pre>
     * @param arg0 Argument0
     * @return Value (If val=null then return null)
     */
    private BigDecimal absNullToNull(final BigDecimal arg0) {

        if (arg0 == null) {

            return null;

        } else {

            return arg0.abs();
        }
    }

    /**
     * <pre>
     * Subtract
     * If val=null then return null
     * </pre>
     * @param arg0 Argument0
     * @param arg1 Argument1
     * @return Value (If val=null then return null)
     */
    private BigDecimal subtractNullToNull(final BigDecimal arg0, final BigDecimal arg1) {

        if (arg0 == null || arg1 == null) {

            return null;

        } else {

            return arg0.subtract(arg1);
        }
    }

    /**
     * <pre>
     * multiply
     * If val=null then return null
     * </pre>
     * @param arg0 Argument0
     * @param arg1 Argument1
     * @return Value (If val=null then return null)
     */
    private BigDecimal multiplyNullToNull(final BigDecimal arg0, final BigDecimal arg1) {

        if (arg0 == null || arg1 == null) {

            return null;

        } else {

            return arg0.multiply(arg1);
        }
    }

    /**
     * <pre>
     * Multiply
     * If val=null then return null
     * Half up round
     * </pre>
     * @param arg0 Argument0
     * @param arg1 Argument1
     * @param scale DecimalLength
     * @return Value (If val=null then return null)
     */
    private BigDecimal multiplyNullToNull(final BigDecimal arg0, final BigDecimal arg1, int scale) {

        BigDecimal rtrn = multiplyNullToNull(arg0, arg1);

        if (rtrn == null) {

            return null;

        } else {

            return rtrn.setScale(scale, RoundingMode.HALF_UP);
        }
    }

    // 08/23/2010 D.Fukaya append start
    /**
     * <pre>
     * get volume in feet
     * If inInchVol == null or qty == null then return null
     * Half up round
     * </pre>
     * @param inInchVol Volume in inch
     * @param qty Quantity
     * @param scale DecimalLength
     * @return BigDecimal
     */
    private BigDecimal getTotShpgVolInFeet(final BigDecimal inInchVol, final BigDecimal qty, int scale) {

        BigDecimal volInInch = multiplyNullToNull(inInchVol, qty);

        if (volInInch == null) {

            return null;

        } else {

            BigDecimal volInFeet = volInInch.divide(DIV_VAL_OF_CONV_FROM_INCH_TO_FEET, scale, RoundingMode.HALF_UP);
            return volInFeet;
        }
    }
    // 08/23/2010 D.Fukaya append end

    /**
     * Get Max Value
     * @param maxLg MaxLength
     * @param declLg DecimalLength
     * @return MaxValue
     */
    private BigDecimal getMaxVal(final int maxLg, final int declLg) {

        BigDecimal overflowVal = BigDecimal.valueOf(1, declLg - maxLg);
        BigDecimal unitVal = BigDecimal.valueOf(1, declLg);
        return overflowVal.subtract(unitVal);
    }

    /**
     * Overflow Value To Border Value
     * @param val BigDecimalValue
     * @param maxLg MaxLength
     * @param declLg DecimalLength
     * @return BigDecimalValue (If val=null then return null)
     */
    private BigDecimal overflowToBorder(final BigDecimal val, final int maxLg, final int declLg) {

        BigDecimal rtrnVal = val;

        if (val != null) {

            BigDecimal maxVal = getMaxVal(maxLg, declLg);
            BigDecimal minVal = maxVal.negate();
            rtrnVal = rtrnVal.min(maxVal);
            rtrnVal = rtrnVal.max(minVal);
        }

        return rtrnVal;
    }

    // 06/17/2010 D.Fukaya appnend start
    /**
     * replaceLineFeedAndTrimForSoMsg
     * @param target String
     * @retrun String
     */
    private String replaceLineFeedAndTrimForSoMsg(String target) {

        if (ZYPCommonFunc.hasValue(target)) {

            return ZYPCommonFunc.replaceAll(target, LINE_FEED_CODE, SPACE).trim();

        } else {

            return "";
        }
    }

    /**
     * getSoMsgList
     * @param params List<NLZC205001PMsg>
     * @param baseSoHdr Map<String, String>
     * @retrun List<String>
     */
    private List<String> getSoMsgList(final List<NLZC205001PMsg> params, Map<String, String> baseSoHdr) {

        NLZC205001PMsg pMsg = params.get(0);
        String sceOrdTpCd = pMsg.sceOrdTpCd.getValue();

        StringBuffer soMsgTmp = new StringBuffer();
        List<String> soMsgList = new ArrayList<String>();

        soMsgTmp = getSoMsgForCBS(baseSoHdr, soMsgTmp);
        soMsgTmp = getSoMsgForCustAccount(baseSoHdr, soMsgTmp);

        // Pattern 1
        if (SCE_ORD_TP.REGULAR.equals(sceOrdTpCd)
                || SCE_ORD_TP.TRIAL_FOR_SALE.equals(sceOrdTpCd)
                || SCE_ORD_TP.TRIAL_FOR_USE.equals(sceOrdTpCd)
                || SCE_ORD_TP.LOAN.equals(sceOrdTpCd)
                || SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd)
                || SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd)) {

            soMsgTmp = getSoMsgForDsCpoDelyInfo(params, soMsgTmp);

        // Pattern 2
        } else if (SCE_ORD_TP.KITTING.equals(sceOrdTpCd)
                || SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd)
                || SCE_ORD_TP.REFURBISH.equals(sceOrdTpCd)) {

            soMsgTmp = getSoMsgForShpgPln(params, soMsgTmp);

        // Pattern 3
        } else if (SCE_ORD_TP.INTERNAL_TRANSFER.equals(sceOrdTpCd)
                || SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)
                || SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd)
                || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd)
                || SCE_ORD_TP.REFURBISH_EXPENSE.equals(sceOrdTpCd)
                || SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd)
                || SCE_ORD_TP.ITEM_CHANGE.equals(sceOrdTpCd)
                || SCE_ORD_TP.DISPOSAL.equals(sceOrdTpCd)
                || SCE_ORD_TP.STOCK_STATUS_CHANGE.equals(sceOrdTpCd)
                || SCE_ORD_TP.SUB_WH_CHANGE.equals(sceOrdTpCd)
                || SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)
                || SCE_ORD_TP.DAMAGED_CLAIM_ENTRY.equals(sceOrdTpCd)
                || SCE_ORD_TP.DAMAGED_CLAIM_COMPLETION.equals(sceOrdTpCd)
                || SCE_ORD_TP.DC_TRANSFER_LOSS.equals(sceOrdTpCd)) {

            soMsgTmp = getSoMsgForInvtyOrd(params, soMsgTmp);

        // Pattern 4
        } else if (SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(sceOrdTpCd) || SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(sceOrdTpCd)) {

            soMsgTmp = getSoMsgForVndRtrn(params, soMsgTmp);

        // Pattern 5
        } else if(SCE_ORD_TP.EXPORT.equals(sceOrdTpCd)){

            soMsgTmp = getSoMsgForCpo(params, soMsgTmp);
        }

        String soMsg = soMsgTmp.toString().trim();

        if (soMsg.length() == 0) {

            // no so message
            return soMsgList;
        }

        int beginIndexCurrent = 0;
        int endIndexCurrent = SO_MSG_LEN;
        int endIndex = soMsg.length();

        if (SO_MSG_MAX_LEN < endIndex) {

            endIndex = SO_MSG_MAX_LEN;
        }

        while (beginIndexCurrent < endIndex) {

            if (endIndex < endIndexCurrent) {

                endIndexCurrent = endIndex;
            }

            soMsgList.add(soMsg.substring(beginIndexCurrent, endIndexCurrent));

            beginIndexCurrent = beginIndexCurrent + SO_MSG_LEN;
            endIndexCurrent = endIndexCurrent + SO_MSG_LEN;
        }

        return soMsgList;
    }

    /**
     * getSoMsgForCBS
     * @param baseSoHdr Map<String, String>
     * @param soMsgTmp StringBuffer
     * @retrun StringBuffer
     */
    private StringBuffer getSoMsgForCBS(Map<String, String> baseSoHdr, StringBuffer soMsgTmp) {

        if(ZYPCommonFunc.hasValue(baseSoHdr.get(RTRN_LB_CD))) {
            // QC#31042
            String ctacPtnrPsnNm = getSoCustDtlCtacPtnrPsnNm(baseSoHdr.get(SELL_TO_FIRST_REF_CMNT_TXT), baseSoHdr.get(SELL_TO_SCD_REF_CMNT_TXT), CTAC_PTNR_PSN_NM_MAX_LG);

            if(ZYPCommonFunc.hasValue(ctacPtnrPsnNm) && ZYPCommonFunc.isNumberCheck(ctacPtnrPsnNm)) {

                if (ZYPConstant.FLG_ON_Y.equals(baseSoHdr.get(DROP_SHIP_FLG))) {

                    soMsgTmp.append("CBS P/O : " + ctacPtnrPsnNm + " ");

                } else {

                    soMsgTmp.append("CBS S/O : " + ctacPtnrPsnNm + " ");
                }
            }
        }

        return soMsgTmp;
    }

    /**
     * getSoMsgForCustAccount
     * @param baseSoHdr Map<String, String>
     * @param soMsgTmp StringBuffer
     * @retrun StringBuffer
     */
    private StringBuffer getSoMsgForCustAccount(Map<String, String> baseSoHdr, StringBuffer soMsgTmp) {

        String frtChrgToCd = "";
        String frtChrgMethCd = "";

        if (SCE_ORD_TP.REGULAR.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.TRIAL_FOR_SALE.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.TRIAL_FOR_USE.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.LOAN.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.DIRECT_SALES.equals(this.hdrSceOrdTpCd)) {

            if (ZYPConstant.FLG_ON_Y.equals(this.hdrShpgFrceFlg)) {

                frtChrgToCd = baseSoHdr.get(REQ_FRT_CHRG_TO_CD);
                frtChrgMethCd = baseSoHdr.get(REQ_FRT_CHRG_METH_CD);

            } else {

                frtChrgToCd = baseSoHdr.get(FRT_CHRG_TO_CD);
                frtChrgMethCd = baseSoHdr.get(FRT_CHRG_METH_CD);
            }
        }

        if (SCE_ORD_TP.EXPORT.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.RETURN_TO_VENDOR_IMPORT.equals(this.hdrSceOrdTpCd)) {

            frtChrgToCd = baseSoHdr.get(REQ_FRT_CHRG_TO_CD);
            frtChrgMethCd = baseSoHdr.get(REQ_FRT_CHRG_METH_CD);
        }

        String soMsg = null;

        if (FRT_CHRG_TO.CUSTOMER.equals(frtChrgToCd) && FRT_CHRG_METH.CUST_ACCOUNT.equals(frtChrgMethCd)) {

            String carrCd = baseSoHdr.get(RQST_CARR_CD);
            String carrAcctNum = baseSoHdr.get(CARR_ACCT_NUM);

            if (ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(carrAcctNum)) {

                soMsg = baseSoHdr.get(RQST_CARR_CD) + " " + SO_MSG_SO_DESC_TXT_INFIX + " " + baseSoHdr.get(CARR_ACCT_NUM) + " ";
                soMsgTmp.append(soMsg);

            } else if (ZYPCommonFunc.hasValue(carrCd) && !ZYPCommonFunc.hasValue(carrAcctNum)) {

                soMsg = baseSoHdr.get(RQST_CARR_CD) + " ";
                soMsgTmp.append(soMsg);

            } else if (!ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(carrAcctNum)) {

                soMsg = baseSoHdr.get(CARR_ACCT_NUM) + " ";
                soMsgTmp.append(soMsg);
            }
        }

        return soMsgTmp;
    }

    /**
     * getSoMsgForCpo
     * @param params List<NLZC205001PMsg>
     * @param soMsgTmp StringBuffer
     * @retrun StringBuffer
     */
    private StringBuffer getSoMsgForCpo(final List<NLZC205001PMsg> params, StringBuffer soMsgTmp) {

        // 09/29/2010 D.Fukaya append start
        NLZC205001PMsg pMsg2 = params.get(0);
        String glblCmpyCd2 = pMsg2.glblCmpyCd.getValue();
        String sceOrdTpCd2 = pMsg2.sceOrdTpCd.getValue();
        String shpgPlnNum2 = pMsg2.shpgPlnNum.getValue();

        Map<String, Object> queryParam2 = new HashMap<String, Object>();
        queryParam2.put("glblCmpyCd", glblCmpyCd2);
        queryParam2.put("shpgPlnNum", shpgPlnNum2);
        queryParam2.put("trxSrcTpCd", getTrxSrcTpCd(sceOrdTpCd2));
        queryParam2.put("txtTpCd", TXT_TP.S_OR_O_COMMENT);

        List<MSG_TXT_DTLTMsg> tMsgListForHdr = (List<MSG_TXT_DTLTMsg>) this.ssmBatchClient.queryObjectList("getMsgForCpoHdr", queryParam2);

        String msgTxtInfoTxtHdr;
        for (MSG_TXT_DTLTMsg tMsgForHdr : tMsgListForHdr) {

            msgTxtInfoTxtHdr = replaceLineFeedAndTrimForSoMsg(tMsgForHdr.msgTxtInfoTxt.getValue());

            if (ZYPCommonFunc.hasValue(msgTxtInfoTxtHdr)) {

                soMsgTmp.append(msgTxtInfoTxtHdr);
            }
        }

        soMsgTmp.append(SPACE);
        // 09/29/2010 D.Fukaya append end

        for (NLZC205001PMsg pMsg1 : params) {

            String glblCmpyCd1 = pMsg1.glblCmpyCd.getValue();
            String sceOrdTpCd1 = pMsg1.sceOrdTpCd.getValue();
            String shpgPlnNum1 = pMsg1.shpgPlnNum.getValue();

            Map<String, Object> queryParam1 = new HashMap<String, Object>();
            queryParam1.put("glblCmpyCd", glblCmpyCd1);
            queryParam1.put("shpgPlnNum", shpgPlnNum1);
            queryParam1.put("trxSrcTpCd", getTrxSrcTpCd(sceOrdTpCd1));
            queryParam1.put("txtTpCd", TXT_TP.S_OR_O_COMMENT);

            List<MSG_TXT_DTLTMsg> tMsgListForDtl = (List<MSG_TXT_DTLTMsg>) this.ssmBatchClient.queryObjectList("getMsgForCpoDtl", queryParam1);

            String msgTxtInfoTxt;

            for (MSG_TXT_DTLTMsg tMsgForDtl : tMsgListForDtl) {

                msgTxtInfoTxt = replaceLineFeedAndTrimForSoMsg(tMsgForDtl.msgTxtInfoTxt.getValue());

                if (ZYPCommonFunc.hasValue(msgTxtInfoTxt)) {

                    soMsgTmp.append(msgTxtInfoTxt);
                }
            }

            soMsgTmp.append(SPACE);
        }

        return soMsgTmp;
    }

    /**
     * getSoMsgForDsCpoDelyInfo
     * @param params List<NLZC205001PMsg>
     * @param soMsgTmp StringBuffer
     * @retrun StringBuffer
     */
    private StringBuffer getSoMsgForDsCpoDelyInfo(final List<NLZC205001PMsg> params, StringBuffer soMsgTmp) {

        // 09/29/2010 D.Fukaya append start
        NLZC205001PMsg pMsg2 = params.get(0);
        String glblCmpyCd2 = pMsg2.glblCmpyCd.getValue();
        String sceOrdTpCd2 = pMsg2.sceOrdTpCd.getValue();
        String shpgPlnNum2 = pMsg2.shpgPlnNum.getValue();

        Map<String, Object> queryParam2 = new HashMap<String, Object>();
        queryParam2.put("glblCmpyCd", glblCmpyCd2);
        queryParam2.put("shpgPlnNum", shpgPlnNum2);
        queryParam2.put("trxSrcTpCd", getTrxSrcTpCd(sceOrdTpCd2));
        //08/03/2017 CITS S.Endo Mod Sol#035(QC#18108) START
        //List<DS_CPO_DELY_INFOTMsg> tMsgListForHdr = (List<DS_CPO_DELY_INFOTMsg>) this.ssmBatchClient.queryObjectList("getMsgForDsCpoDelyInfoHdr", queryParam2);
        List<Map<String, Object>> tMsgListForHdr = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getMsgForDsCpoDelyInfoHdr", queryParam2);

        String msgTxtInfoTxtHdr;
        //for (DS_CPO_DELY_INFOTMsg tMsgForHdr : tMsgListForHdr) {
        for (Map<String, Object> tMsgForHdr : tMsgListForHdr) {

            //msgTxtInfoTxtHdr = replaceLineFeedAndTrimForSoMsg(tMsgForHdr.delyAddlCmntTxt.getValue());
            msgTxtInfoTxtHdr = replaceLineFeedAndTrimForSoMsg((String) tMsgForHdr.get("DELY_ADDL_CMNT_TXT"));

            if (ZYPCommonFunc.hasValue(msgTxtInfoTxtHdr)) {

                soMsgTmp.append(msgTxtInfoTxtHdr);
            }
        }
        //08/03/2017 CITS S.Endo Mod Sol#035(QC#18108) END
        soMsgTmp.append(SPACE);

        return soMsgTmp;
    }

    /**
     * getSoMsgForShpgPln
     * @param params List<NLZC205001PMsg>
     * @param soMsgTmp StringBuffer
     * @retrun StringBuffer
     */
    private StringBuffer getSoMsgForShpgPln(final List<NLZC205001PMsg> params, StringBuffer soMsgTmp) {

        NLZC205001PMsg pMsg = params.get(0);
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String sceOrdTpCd = pMsg.sceOrdTpCd.getValue();
        String shpgPlnNum = pMsg.shpgPlnNum.getValue();

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("shpgPlnNum", shpgPlnNum);
        queryParam.put("trxSrcTpCd", getTrxSrcTpCd(sceOrdTpCd));

        List<SHPG_PLNTMsg> tMsgList = (List<SHPG_PLNTMsg>) this.ssmBatchClient.queryObjectList("getMsgForShpgPln", queryParam);

        SHPG_PLNTMsg shpgPlnTMsg;

        if (tMsgList.size() > 0) {

            shpgPlnTMsg = tMsgList.get(0);
            String shipCmntFirstLineTxt = replaceLineFeedAndTrimForSoMsg(shpgPlnTMsg.shipCmntFirstLineTxt.getValue());
            String shipCmntScdLineTxt = replaceLineFeedAndTrimForSoMsg(shpgPlnTMsg.shipCmntScdLineTxt.getValue());
            String shipCmntThirdLineTxt = replaceLineFeedAndTrimForSoMsg(shpgPlnTMsg.shipCmntThirdLineTxt.getValue());
            String shipCmntFrthLineTxt = replaceLineFeedAndTrimForSoMsg(shpgPlnTMsg.shipCmntFrthLineTxt.getValue());

            if (ZYPCommonFunc.hasValue(shipCmntFirstLineTxt)) {

                soMsgTmp.append(shipCmntFirstLineTxt);
                soMsgTmp.append(SPACE);
            }

            if (ZYPCommonFunc.hasValue(shipCmntScdLineTxt)) {

                soMsgTmp.append(shipCmntScdLineTxt);
                soMsgTmp.append(SPACE);
            }

            if (ZYPCommonFunc.hasValue(shipCmntThirdLineTxt)) {

                soMsgTmp.append(shipCmntThirdLineTxt);
                soMsgTmp.append(SPACE);
            }

            if (ZYPCommonFunc.hasValue(shipCmntFrthLineTxt)) {

                soMsgTmp.append(shipCmntFrthLineTxt);
                soMsgTmp.append(SPACE);
            }
        }

        return soMsgTmp;
    }

    /**
     * getSoMsgForInvtyOrd
     * @param params List<NLZC205001PMsg>
     * @param soMsgTmp StringBuffer
     * @retrun StringBuffer
     */
    private StringBuffer getSoMsgForInvtyOrd(final List<NLZC205001PMsg> params, StringBuffer soMsgTmp) {

        NLZC205001PMsg pMsg = params.get(0);
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String sceOrdTpCd = pMsg.sceOrdTpCd.getValue();
        String shpgPlnNum = pMsg.shpgPlnNum.getValue();

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("shpgPlnNum", shpgPlnNum);
        queryParam.put("trxSrcTpCd", getTrxSrcTpCd(sceOrdTpCd));

        List<INVTY_ORDTMsg> tMsgList = (List<INVTY_ORDTMsg>) this.ssmBatchClient.queryObjectList("getMsgForInvtyOrd", queryParam);

        INVTY_ORDTMsg invtyOrdTMsg;

        if (tMsgList.size() > 0) {

            invtyOrdTMsg = tMsgList.get(0);
            String firstInvtyOrdCmntTxt = replaceLineFeedAndTrimForSoMsg(invtyOrdTMsg.firstInvtyOrdCmntTxt.getValue());
            String scdInvtyOrdCmntTxt = replaceLineFeedAndTrimForSoMsg(invtyOrdTMsg.scdInvtyOrdCmntTxt.getValue());
            String thirdInvtyOrdCmntTxt = replaceLineFeedAndTrimForSoMsg(invtyOrdTMsg.thirdInvtyOrdCmntTxt.getValue());
            String frthInvtyOrdCmntTxt = replaceLineFeedAndTrimForSoMsg(invtyOrdTMsg.frthInvtyOrdCmntTxt.getValue());

            if (ZYPCommonFunc.hasValue(firstInvtyOrdCmntTxt)) {

                soMsgTmp.append(firstInvtyOrdCmntTxt);
                soMsgTmp.append(SPACE);
            }

            if (ZYPCommonFunc.hasValue(scdInvtyOrdCmntTxt)) {

                soMsgTmp.append(scdInvtyOrdCmntTxt);
                soMsgTmp.append(SPACE);
            }

            if (ZYPCommonFunc.hasValue(thirdInvtyOrdCmntTxt)) {

                soMsgTmp.append(thirdInvtyOrdCmntTxt);
                soMsgTmp.append(SPACE);
            }

            if (ZYPCommonFunc.hasValue(frthInvtyOrdCmntTxt)) {

                soMsgTmp.append(frthInvtyOrdCmntTxt);
                soMsgTmp.append(SPACE);
            }
        }

        return soMsgTmp;
    }

    /**
     * getSoMsgForVndRtrn
     * @param params List<NLZC205001PMsg>
     * @param soMsgTmp StringBuffer
     * @retrun StringBuffer
     */
    private StringBuffer getSoMsgForVndRtrn(final List<NLZC205001PMsg> params, StringBuffer soMsgTmp) {

        NLZC205001PMsg pMsg = params.get(0);
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String sceOrdTpCd = pMsg.sceOrdTpCd.getValue();
        String shpgPlnNum = pMsg.shpgPlnNum.getValue();

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("shpgPlnNum", shpgPlnNum);
        queryParam.put("trxSrcTpCd", getTrxSrcTpCd(sceOrdTpCd));
        queryParam.put("txtTpCd", TXT_TP.S_OR_O_COMMENT);

        List<VND_RTRN_CMNTTMsg> tMsgList = (List<VND_RTRN_CMNTTMsg>) this.ssmBatchClient.queryObjectList("getMsgForVndRtrn", queryParam);

        String vndRtrnCmntTxt;

        for (VND_RTRN_CMNTTMsg tMsg : tMsgList) {

            vndRtrnCmntTxt = replaceLineFeedAndTrimForSoMsg(tMsg.vndRtrnCmntTxt.getValue());
  
            if (ZYPCommonFunc.hasValue(vndRtrnCmntTxt)) {

                soMsgTmp.append(vndRtrnCmntTxt);
                soMsgTmp.append(SPACE);
            }
        }

        return soMsgTmp;
    }
    // 06/17/2010 D.Fukaya appnend end

    // 12/08/2010 D.Fukaya append start
    /**
     * getAsnRequiredFlag
     * @param baseSoHdr Map<String, String>
     * @retrun String
     */
    private String getAsnRequiredFlag(final Map<String, String> baseSoHdr) {

        String asnReqFlg = ZYPConstant.FLG_OFF_N;
        String cpoSrcTpCd = baseSoHdr.get(CPO_SRC_TP_CD);
        String baseAsnReqFlg = baseSoHdr.get(ASN_REQ_FLG);

        if (CPO_SRC_TP.EDI.equals(cpoSrcTpCd)) {

            if (ZYPConstant.FLG_ON_Y.equals(baseAsnReqFlg) || ZYPCommonFunc.hasValue(baseSoHdr.get(ASN_SHIP_TO_CUST_CD))) {

                asnReqFlg = ZYPConstant.FLG_ON_Y;
            }
        }

        return asnReqFlg;
    }
    // 12/08/2010 D.Fukaya append end

    /**
     * updateSO
     * @param param NLZC205001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    private void updateSO(NLZC205001PMsg param, final ONBATCH_TYPE onBatchType) {

        // init
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        final String mode = param.xxModeCd.getValue();

        // QC#20154 Update.
        if (!MODE_NEW.equals(mode) //
                && !MODE_DROP_SHIP.equals(mode) //
                && !MODE_MODIFY.equals(mode) //
                && !MODE_WMS_DROP.equals(mode) //
                && !MODE_CARR_RQST.equals(mode) //
                && !MODE_UPD_SHIP_ADDR_AND_WMS.equals(mode)) {

            S21InfoLogOutput.println(NPZM0101E);
            msgMap.addXxMsgId(NPZM0101E);
            msgMap.flush();
            return;
        }

        if (MODE_DROP_SHIP.equals(mode) && !ZYPCommonFunc.hasValue(param.etaDt)) {

            S21InfoLogOutput.println(NLZM2314E);
            msgMap.addXxMsgId(NLZM2314E);
            msgMap.flush();
            return;
        }

        if (!ZYPCommonFunc.hasValue(param.soNum)) {

            S21InfoLogOutput.println(NLZM0027E);
            msgMap.addXxMsgId(NLZM0027E);
            msgMap.flush();
            return;
        }

        if (MODE_NEW.equals(mode)) {

            doModeNew(msgMap, onBatchType);

        } else if (MODE_MODIFY.equals(mode)) {

            doModeModify(msgMap, onBatchType);

        } else if (MODE_DROP_SHIP.equals(mode)) {

            doModeNew(msgMap, onBatchType);

        /* 08/26/2016 CSAI Y.Imazu Add QC#9845 START */
        } else if (MODE_WMS_DROP.equals(mode)) {

            doWmsDrop(msgMap, onBatchType);

        } else if (MODE_CARR_RQST.equals(mode)) {

            doCarrRqst(msgMap, onBatchType);
        /* 08/26/2016 CSAI Y.Imazu Add QC#9845 END */
        } else if(MODE_UPD_SHIP_ADDR_AND_WMS.equals(mode)){
            // QC#20154 Add method,
            doUpdShipAddrAndWms(msgMap, onBatchType);
        }

        msgMap.flush();
    }

    /**
     * doModeNew (Create SO)
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    private boolean doModeNew(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        /*** do Update Shipping Order and Detail and Schedule ***/
        // get  Shipping Order Info
        List<ShippingOrderInfo> shippingOrderInfoList = getShippingOrderInfo(msgMap);

        // no data about Shipping Order Info
        if (shippingOrderInfoList.isEmpty()) {

            msgMap.addXxMsgId(NLZM2271E);
            S21InfoLogOutput.println(NLZM2271E);
            return false;
        }

        String sceOrdTpCd = shippingOrderInfoList.get(0).getSceOrdTpCd();

        // update Shipping Order Info
        if (!updateShippingOrderDetail(msgMap, shippingOrderInfoList)) {

            return false;
        }

        /*** do create Shipping Back Order ***/
        // get Shipping Back Order Info
        List<ShippingBackOrderInfo> shippingBackOrderInfoList = getShippingBackOrderInfo(msgMap, shippingOrderInfoList);

        // create Shipping Back Order
        if (!createShippingBackOrder(msgMap, shippingBackOrderInfoList)) {

            return false;
        }

        if (notGetSoSerialInfo(sceOrdTpCd)) {

            return true;
        }

        /*** do create SO Serial ***/
        // get SO Serial Info
        List<SoSerialInfo> soSerialInfoList = getSoSerialInfo(msgMap);

        // create SO Serial
        createSoSerial(msgMap, soSerialInfoList, onBatchType);

        return true;
    }

    /**
     * notGetSoSerialInfo
     * @param sceOrdTpCd String
     * @return boolean
     */
    private boolean notGetSoSerialInfo(String sceOrdTpCd) {

        if (NLXSceConst.SCE_ORD_TP_CD_RL.equals(sceOrdTpCd)) {

            return true;

        } else if (NLXSceConst.SCE_ORD_TP_CD_RU.equals(sceOrdTpCd)) {

            return true;

        } else if (NLXSceConst.SCE_ORD_TP_CD_RX.equals(sceOrdTpCd)) {

            return true;
        }

        return false;
    }

    /**
     * get ShippingOrder Info
     * @param msgMap S21ApiMessageMap
     * @return List<ShippingOrderInfo>
     */
    private List<ShippingOrderInfo> getShippingOrderInfo(S21ApiMessageMap msgMap) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
        // Get ShippingOrder Info
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("soNum", param.soNum.getValue());
        queryParam.put("inbdOtbdCd", INBD_OTBD.OUTBOUND);
        queryParam.put("outOfScp", SVC_MACH_PROC_STS.OUT_OF_SCOPE);
        queryParam.put("flgOff", ZYPConstant.FLG_OFF_N);
        queryParam.put("sceOrdTpCC", NLXSceConst.SCE_ORD_TP_CD_CC);
        queryParam.put("sceOrdTpRM", NLXSceConst.SCE_ORD_TP_CD_RM);
        /* 02/17/2016 CSAI Y.Imazu Add QC#4417 START */
        if (MODE_DROP_SHIP.equals(param.xxModeCd.getValue())) {

            queryParam.put("dropShipModeFlg", ZYPConstant.FLG_ON_Y);

        } else {

            queryParam.put("dropShipModeFlg", ZYPConstant.FLG_OFF_N);
        }
        /* 02/17/2016 CSAI Y.Imazu Add QC#4417 END */

        List<ShippingOrderInfo> shippingOrderInfoList = (List<ShippingOrderInfo>) this.ssmBatchClient.queryObjectList("getShippingOrderInfo", queryParam);

        return shippingOrderInfoList;
    }

    /**
     * Update Shipping Order Detail
     * @param msgMap S21ApiMessageMap
     * @param shippingOrderInfoList List<ShippingOrderInfo>
     * @return boolean
     */
    private boolean updateShippingOrderDetail(S21ApiMessageMap msgMap, List<ShippingOrderInfo> shippingOrderInfoList) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
        final String mode = param.xxModeCd.getValue();
        boolean shopItemExistFlg = false;
        boolean rtrnReqPrtExistFlg = false;

        for (ShippingOrderInfo shippingOrderInfo : shippingOrderInfoList) {

            SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.soNum, param.soNum);
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.soSlpNum, shippingOrderInfo.getSoSlpNum());
            //08/03/2017 CITS S.Endo Mod Sol#072(QC#18386) START
            shpgOrdDtlTMsg = (SHPG_ORD_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(shpgOrdDtlTMsg);
            //08/03/2017 CITS S.Endo Mod Sol#072(QC#18386) END

            if (shpgOrdDtlTMsg == null) {
                msgMap.addXxMsgId(NLZM1029E);
                S21InfoLogOutput.println(NLZM1029E, getErrParam(shpgOrdDtlTMsg));
                return false;
            }

            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.rtlWhCd, shippingOrderInfo.getRtlWhCdOfInvtyLoc());
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.rtlSwhCd, shippingOrderInfo.getRtlSwhCdOfInvtyLoc());
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.invtyLocCd, shippingOrderInfo.getInvtyLocCd());
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.shipToRtlWhCd, shippingOrderInfo.getRtlWhCdOfShipToCust());
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.shipToRtlSwhCd, shippingOrderInfo.getRtlSwhCdOfShipToCust());
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.toInvtyLocCd, shippingOrderInfo.getShipToCustCd());

            //set Direct Sales Shipping Order Line Status Code
            if (MODE_NEW.equals(mode)) {

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.dsSoLineStsCd, DS_SO_LINE_STS.ALLOCATED);

            } else if (MODE_DROP_SHIP.equals(mode)) {

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.dsSoLineStsCd, DS_SO_LINE_STS.SHIPPED);
            }

            //set Service Configuration Master PK
            if (NLXSceConst.SCE_ORD_TYPE_RS.equals(shippingOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TYPE_TRAIL_USE.equals(shippingOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_DT.equals(shippingOrderInfo.getSceOrdTpCd())) {

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.pickSvcConfigMstrPk, shippingOrderInfo.getPickSvcConfigMstrPk());

            } else if (NLXSceConst.SCE_ORD_TP_CD_IT.equals(shippingOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_TR.equals(shippingOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_RP.equals(shippingOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_RE.equals(shippingOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_IC.equals(shippingOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_DI.equals(shippingOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_SC.equals(shippingOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_SW.equals(shippingOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_CC.equals(shippingOrderInfo.getSceOrdTpCd())) {

                        ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.pickSvcConfigMstrPk, shippingOrderInfo.getSvcConfigMstrPkOfInvtyOrdDtl());

            } else if (NLXSceConst.SCE_ORD_TP_CD_RD.equals(shippingOrderInfo.getSceOrdTpCd())) {

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.pickSvcConfigMstrPk, shippingOrderInfo.getSvcConfigMstrPkOfPrchReq());

            } else if(NLXSceConst.SCE_ORD_TP_CD_RM.equals(shippingOrderInfo.getSceOrdTpCd())){

                if(ZYPConstant.FLG_OFF_N.equals(shippingOrderInfo.getAddConfigFlg()) && ZYPCommonFunc.hasValue(shippingOrderInfo.getSvcConfigMstrPkOfSvcMachMstr())){

                    // QC#22178
                    ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.pickSvcConfigMstrPk, shippingOrderInfo.getSvcConfigMstrPkOfSvcMachMstr());

                }
            // 2016/10/14 QC#6159 Add Start.
            } else if (NLXSceConst.SCE_ORD_TP_CD_BB.equals(shippingOrderInfo.getSceOrdTpCd())) {

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.pickSvcConfigMstrPk, shippingOrderInfo.getSvcConfigMstrPkOfPd());

            }
            // 2016/10/14 QC#6159 Add End.

            String[] expenseShipmentDsOrdTpList = ZYPCodeDataUtil.getVarCharConstValue(EXPENSE_SHIPMENT_DS_ORD_TO, param.glblCmpyCd.getValue()).split(",");
            
            //set Purchase Requisition Information
            if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(shippingOrderInfo.getSceOrdTpCd())) {

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.prchReqNum, shippingOrderInfo.getOrdSrcRefNum());
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.prchReqLineNum, shippingOrderInfo.getOrdSrcRefLineNum());
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.prchReqLineSubNum, new BigDecimal(shippingOrderInfo.getOrdSrcRefLineSubNum()));

            } else if (NLXSceConst.SCE_ORD_TP_CD_IT.equals(shippingOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_TR.equals(shippingOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_RP.equals(shippingOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_RE.equals(shippingOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_DI.equals(shippingOrderInfo.getSceOrdTpCd())) {

                        ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.prchReqNum, shippingOrderInfo.getPrchReqNum());
                        ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.prchReqLineNum, shippingOrderInfo.getPrchReqLineNum());
                        ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.prchReqLineSubNum, new BigDecimal(shippingOrderInfo.getPrchReqLineSubNum()));

            } else if (NLXSceConst.SCE_ORD_TP_CD_RD.equals(shippingOrderInfo.getSceOrdTpCd())) {

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.prchReqNum, shippingOrderInfo.getTrxHdrNum());
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.prchReqLineNum, shippingOrderInfo.getTrxLineNum());
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.prchReqLineSubNum, new BigDecimal(shippingOrderInfo.getTrxLineSubNum()));

            } else if(NLXSceConst.SCE_ORD_TYPE_RS.equals(shippingOrderInfo.getSceOrdTpCd())
                    && DS_ORD_CATG.INTERNAL_CSA.equals(shippingOrderInfo.getDsOrdCatgCd())
                    && Arrays.asList(expenseShipmentDsOrdTpList).contains(shippingOrderInfo.getDsOrdTpCd())) {
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.prchReqNum, shippingOrderInfo.getOrdSrcRefNum());
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.prchReqLineNum, shippingOrderInfo.getOrdSrcRefLineNum());
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.prchReqLineSubNum, new BigDecimal(shippingOrderInfo.getOrdSrcRefLineSubNum()));   
            }

            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.svcMachProcStsCd, shippingOrderInfo.getSvcMachProcStsCd());
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.preIstlShopRqstFlg, shippingOrderInfo.getShopItemFlg());
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.rmvConfigFlg, shippingOrderInfo.getRmvConfigFlg());
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.backOrdImpctTpCd, shippingOrderInfo.getBackOrdImpctTpCd());
            
            //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("soDtlFlg", ZYPConstant.FLG_ON_1);
            queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            queryParam.put("soNum", param.soNum.getValue());
            queryParam.put("mdseCd", shpgOrdDtlTMsg.mdseCd.getValue());
            queryParam.put("soSlpNum", shippingOrderInfo.getSoSlpNum());
            queryParam.put("configCatgCd", "O");

            Map<String, Object> resultMap = (Map<String,Object>) this.ssmBatchClient.queryObject("getModelCriticality", queryParam);
            
            if (resultMap!=null && resultMap.size() > 0) {
                if (ZYPCommonFunc.hasValue((String)resultMap.get("BACK_ORD_IMPCT_TP_CD"))) {
                    ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.backOrdImpctTpCd, (String)resultMap.get("BACK_ORD_IMPCT_TP_CD"));
                }
            }
            //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.pickConfQty, BigDecimal.ZERO);

            //set Service Machine Master Location Status Code
            SCE_ORD_TPTMsg sceOrdTpTMsg = new SCE_ORD_TPTMsg();
            sceOrdTpTMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            sceOrdTpTMsg.sceOrdTpCd.setValue(shippingOrderInfo.getSceOrdTpCd());
            sceOrdTpTMsg.inbdOtbdCd.setValue(INBD_OTBD.OUTBOUND);
            sceOrdTpTMsg = (SCE_ORD_TPTMsg) S21ApiTBLAccessor.findByKey(sceOrdTpTMsg);

            if (sceOrdTpTMsg != null) {

                if (NLXSceConst.SCE_ORD_TYPE_RS.equals(shippingOrderInfo.getSceOrdTpCd())
                        && ZYPConstant.FLG_ON_Y.equals((shippingOrderInfo.getInstlbaseCtrlFlg()))
                        && ZYPConstant.FLG_ON_Y.equals((shippingOrderInfo.getOutOfWhNodeUsgFlg()))
                        && ZYPConstant.FLG_ON_Y.equals((shippingOrderInfo.getOutOfWhInvtyProcFlg()))) {

                    ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.svcMachMstrLocStsCd, LOC_STS.TRIAL_USE);

                } else {

                    ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.svcMachMstrLocStsCd, sceOrdTpTMsg.svcMachMstrLocStsCd);
                }
            }

            S21ApiTBLAccessor.update(shpgOrdDtlTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdDtlTMsg.getReturnCode())) {

                msgMap.addXxMsgId(NLZM2130E);
                S21InfoLogOutput.println(NLZM2130E, getErrParam(shpgOrdDtlTMsg));
                return false;
            }

            if (ZYPConstant.FLG_ON_Y.equals(shippingOrderInfo.getShopItemFlg())) {

                shopItemExistFlg = true;
            }

            if (ZYPConstant.FLG_ON_Y.equals(shippingOrderInfo.getRtrnReqPrtFlg())) {

                rtrnReqPrtExistFlg = true;
            }

            // QC#22344 Add
            // Update PR Update API(Update SO_NUM)
            if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(shippingOrderInfo.getSceOrdTpCd()) //
                    || NLXSceConst.SCE_ORD_TYPE_RS.equals(shippingOrderInfo.getSceOrdTpCd()) //
                    && DS_ORD_CATG.INTERNAL_CSA.equals(shippingOrderInfo.getDsOrdCatgCd()) //
                    && Arrays.asList(expenseShipmentDsOrdTpList).contains(shippingOrderInfo.getDsOrdTpCd())) {

                // Update PRCH_REQ_DTL.SO_NUM
                PRCH_REQ_DTLTMsg paramTMsg = new PRCH_REQ_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(paramTMsg.glblCmpyCd, param.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(paramTMsg.prchReqNum, shippingOrderInfo.getOrdSrcRefNum());
                ZYPEZDItemValueSetter.setValue(paramTMsg.prchReqLineNum, shippingOrderInfo.getOrdSrcRefLineNum());
                ZYPEZDItemValueSetter.setValue(paramTMsg.prchReqLineSubNum, new BigDecimal(shippingOrderInfo.getOrdSrcRefLineSubNum()));
                PRCH_REQ_DTLTMsg updTMsg = (PRCH_REQ_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);

                if (updTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(updTMsg.soNum, param.soNum.getValue());
                    S21ApiTBLAccessor.update(updTMsg);
                }
            }
            // QC#22344 End
        }

        // Update Shipping Order
        if (!updateShippingOrder(msgMap, shippingOrderInfoList, shopItemExistFlg, rtrnReqPrtExistFlg)) {

            return false;
        }

        return true;
    }

    /**
     * Update  Shipping Order
     * @param msgMap S21ApiMessageMap
     * @param shippingOrderInfoList List<ShippingOrderInfo>
     * @param shopItemExistFlg boolean
     * @param rtrnReqPrtExistFlg boolean
     * @return boolean
     */
    private boolean updateShippingOrder(S21ApiMessageMap msgMap, List<ShippingOrderInfo> shippingOrderInfoList, boolean shopItemExistFlg, boolean rtrnReqPrtExistFlg) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.soNum, param.soNum);
        //08/03/2017 CITS S.Endo Mod Sol#072(QC#18386) START
        shpgOrdTMsg = (SHPG_ORDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(shpgOrdTMsg);
        //08/03/2017 CITS S.Endo Mod Sol#072(QC#18386) START
        if (shpgOrdTMsg == null) {
            msgMap.addXxMsgId(NLZM1028E);
            S21InfoLogOutput.println(NLZM1028E, getErrParam(shpgOrdTMsg));
            return false;
        }

        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.delyReqFlg, ZYPConstant.FLG_OFF_N);

        //set WMS Drop Request Flag
        // QC#22302 Start
        if (isAutoSoDropFlgChkWh(shpgOrdTMsg)) {
        	ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.wmsDropRqstFlg, shippingOrderInfoList.get(0).getAutoSoDropFlg());
        } else {
            if (NLXSceConst.SCE_ORD_TYPE_RS.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TYPE_TRAIL_USE.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())) {

                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.wmsDropRqstFlg, shippingOrderInfoList.get(0).getAutoSoDropFlg());

            } else if(NLXSceConst.SCE_ORD_TP_CD_RU.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())) {

                // QC#21912 Add
                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.wmsDropRqstFlg, ZYPConstant.FLG_OFF_N);

                // QC#26863 Add Start
            } else if (NLXSceConst.SCE_ORD_TP_CD_RX.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())) {

                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.wmsDropRqstFlg, ZYPConstant.FLG_OFF_N);
                // QC#26863 Add End

            } else {

                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.wmsDropRqstFlg, ZYPConstant.FLG_ON_Y);
            }
        }
        // QC#22302 End

        String salesDate = ZYPDateUtil.getSalesDate(param.glblCmpyCd.getValue());
        this.sysTm = ZYPDateUtil.getCurrentSystemTime(DATE_FMT_HHMMSS);
        String sysTime = salesDate + sysTm;

        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.soCratTs, sysTime);

        //set Service Configuration Master PK
        if (NLXSceConst.SCE_ORD_TYPE_RS.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())
                || NLXSceConst.SCE_ORD_TYPE_TRAIL_USE.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())
                || NLXSceConst.SCE_ORD_TP_CD_DT.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())) {

            // QC#26526 MOD START
            // Not Tecsys WH pick same config.
            // QC#30848
            // WH Transfer pick same config.other order type change config
            boolean isKeep = isKeepConfigIdOrdTp(param.glblCmpyCd.getValue(), shippingOrderInfoList.get(0).getDsOrdTpCd());
            BigDecimal pickConfig = getPickSvcConfig(shippingOrderInfoList);
            if (isKeep && ZYPCommonFunc.hasValue(pickConfig)) {
                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.svcConfigMstrPk, pickConfig);
                // QC#50365
                if (ZYPCommonFunc.hasValue(shippingOrderInfoList.get(0).getDsCpoConfigPk())) {
                    DS_CPO_CONFIGTMsg dsCpoConfigTMsg = new DS_CPO_CONFIGTMsg();
                    ZYPEZDItemValueSetter.setValue(dsCpoConfigTMsg.glblCmpyCd, param.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dsCpoConfigTMsg.dsCpoConfigPk, shippingOrderInfoList.get(0).getDsCpoConfigPk());
                    dsCpoConfigTMsg = (DS_CPO_CONFIGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsCpoConfigTMsg);

                    if (dsCpoConfigTMsg != null) {

                        ZYPEZDItemValueSetter.setValue(dsCpoConfigTMsg.svcConfigMstrPk, pickConfig);

                        S21ApiTBLAccessor.update(dsCpoConfigTMsg);

                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCpoConfigTMsg.getReturnCode())) {
                            msgMap.addXxMsgId(NWZM1383E);
                            S21InfoLogOutput.println(NWZM1383E, getErrParam(dsCpoConfigTMsg));
                            S21InfoLogOutput.println(shippingOrderInfoList.get(0).getDsCpoConfigPk().toPlainString());
                            return false;
                        }
                    }
                }
            } else if (isKeep && !ZYPCommonFunc.hasValue(pickConfig)) {
                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.svcConfigMstrPk, shippingOrderInfoList.get(0).getSvcConfigMstrPkOfDsCpoConfig());
            } else {
                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.svcConfigMstrPk, shippingOrderInfoList.get(0).getSvcConfigMstrPkOfDsCpoConfig());
            }

            if (!NLXSceConst.SCE_ORD_TYPE_TRAIL_USE.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())) {

                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.mdlId, shippingOrderInfoList.get(0).getMdlId());

            } else {

                setMdlId(param.glblCmpyCd.getValue(), shpgOrdTMsg);
            }

            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.srcOrdNum, shippingOrderInfoList.get(0).getTrxHdrNum());

        } else if (NLXSceConst.SCE_ORD_TP_CD_IT.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())
                || NLXSceConst.SCE_ORD_TP_CD_TR.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())
                || NLXSceConst.SCE_ORD_TP_CD_RP.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())
                || NLXSceConst.SCE_ORD_TP_CD_RE.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())
                || NLXSceConst.SCE_ORD_TP_CD_IC.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())
                || NLXSceConst.SCE_ORD_TP_CD_DI.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())
                || NLXSceConst.SCE_ORD_TP_CD_SC.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())
                || NLXSceConst.SCE_ORD_TP_CD_SW.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())
                || NLXSceConst.SCE_ORD_TP_CD_CC.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())) {

                    ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.svcConfigMstrPk, shippingOrderInfoList.get(0).getSvcConfigMstrPkOfInvtyOrd());

                    setMdlId(param.glblCmpyCd.getValue(), shpgOrdTMsg);

                    if (NLXSceConst.SCE_ORD_TP_CD_IT.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())
                            || NLXSceConst.SCE_ORD_TP_CD_TR.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())
                            || NLXSceConst.SCE_ORD_TP_CD_RP.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())
                            || NLXSceConst.SCE_ORD_TP_CD_RE.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())
                            || NLXSceConst.SCE_ORD_TP_CD_DI.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())) {

                        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.srcOrdNum, shippingOrderInfoList.get(0).getPrchReqNum());

                    } else {

                        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.srcOrdNum, shippingOrderInfoList.get(0).getTrxHdrNum());

                    }

        } else if (NLXSceConst.SCE_ORD_TP_CD_RD.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())) {

            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.svcConfigMstrPk, shippingOrderInfoList.get(0).getSvcConfigMstrPkOfPrchReq());

            setMdlId(param.glblCmpyCd.getValue(), shpgOrdTMsg);

            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.srcOrdNum, shippingOrderInfoList.get(0).getTrxHdrNum());

        } else if (NLXSceConst.SCE_ORD_TP_CD_RM.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())){

            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.svcConfigMstrPk, shippingOrderInfoList.get(0).getSvcConfigMstrPkOfRmnfOrd());

            setMdlId(param.glblCmpyCd.getValue(), shpgOrdTMsg);

            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.srcOrdNum, shippingOrderInfoList.get(0).getTrxHdrNum());

        } else if (NLXSceConst.SCE_ORD_TP_CD_BB.equals(shippingOrderInfoList.get(0).getSceOrdTpCd())){

            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.svcConfigMstrPk, shippingOrderInfoList.get(0).getSvcConfigMstrPkOfPd());

            setMdlId(param.glblCmpyCd.getValue(), shpgOrdTMsg);

            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.srcOrdNum, shippingOrderInfoList.get(0).getTrxHdrNum());
        } else {

            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.srcOrdNum, shippingOrderInfoList.get(0).getTrxHdrNum());

        }

        //set Pre Install Shop Request Flag
        if (shopItemExistFlg) {

            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.preIstlShopRqstFlg, ZYPConstant.FLG_ON_Y);

        } else {

            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.preIstlShopRqstFlg, ZYPConstant.FLG_OFF_N);
        }

        //set Return Item Include Flag
        if (rtrnReqPrtExistFlg) {

            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.rtrnItemInclFlg, ZYPConstant.FLG_ON_Y);

        } else {

            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.rtrnItemInclFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.dsCpoConfigPk, shippingOrderInfoList.get(0).getDsCpoConfigPk());
        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.billToCustAcctCd, shippingOrderInfoList.get(0).getBillToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.shipToCustAcctCd, shippingOrderInfoList.get(0).getShipToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.wmsRtePathCd, shippingOrderInfoList.get(0).getWmsRtePathCd());

        if (ZYPConstant.FLG_ON_Y.equals(shippingOrderInfoList.get(0).getSchdReqFlg())) {

            if (!setShippingOrderSchedule(msgMap, shippingOrderInfoList, shpgOrdTMsg)) {

                return false;
            }
        }

        S21ApiTBLAccessor.update(shpgOrdTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NLZM2129E);
            S21InfoLogOutput.println(NLZM2129E, getErrParam(shpgOrdTMsg));
            return false;
        }

        return true;
    }

    /**
     * set Model ID
     * @param glblCmpyCd String
     * @param shpgOrdTMsg SHPG_ORDTMsg
     */
    private void setMdlId(String glblCmpyCd, SHPG_ORDTMsg shpgOrdTMsg) {

        if (ZYPCommonFunc.hasValue(shpgOrdTMsg.svcConfigMstrPk)) {
            SVC_CONFIG_MSTRTMsg svcConfMTMsg = new SVC_CONFIG_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(svcConfMTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcConfMTMsg.svcConfigMstrPk, shpgOrdTMsg.svcConfigMstrPk);

            svcConfMTMsg = (SVC_CONFIG_MSTRTMsg) S21ApiTBLAccessor.findByKey(svcConfMTMsg);

            if (svcConfMTMsg != null) {

                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.mdlId, svcConfMTMsg.mdlId);

            }
        }
    }

    /**
     * get Shipping BackOrder Info
     * @param msgMap S21ApiMessageMap
     * @return List<ShippingBackOrderInfo>
     */
    private List<ShippingBackOrderInfo> getShippingBackOrderInfo(S21ApiMessageMap msgMap, List<ShippingOrderInfo> shippingOrderInfoList) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        // Get ShippingBackOrder Info
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("soNum", param.soNum.getValue());
        queryParam.put("shpStsCd", SHPG_STS.VALIDATED);
        queryParam.put("pkgUomCd", PKG_UOM.EACH);
        queryParam.put("flgOn", ZYPConstant.FLG_ON_Y);
        queryParam.put("flgOff", ZYPConstant.FLG_OFF_N);
        /* 02/17/2016 CSAI Y.Imazu Add QC#4417 START */
        if (MODE_DROP_SHIP.equals(param.xxModeCd.getValue())) {

            queryParam.put("dropShipModeFlg", ZYPConstant.FLG_ON_Y);

        } else {

            queryParam.put("dropShipModeFlg", ZYPConstant.FLG_OFF_N);
        }
        /* 02/17/2016 CSAI Y.Imazu Add QC#4417 END */

        queryParam.put("dsCpoConfigPk", shippingOrderInfoList.get(0).getDsCpoConfigPk());

        List<ShippingBackOrderInfo> shippingBackOrderInfoList = (List<ShippingBackOrderInfo>) this.ssmBatchClient.queryObjectList("getShippingBackOrderInfo", queryParam);

        return shippingBackOrderInfoList;
    }

    /**
     * create Shipping BackOrder
     * @param msgMap S21ApiMessageMap
     * @param shippingBackOrderInfoList List<ShippingBackOrderInfo>
     * @return boolean
     */
    private boolean createShippingBackOrder(S21ApiMessageMap msgMap, List<ShippingBackOrderInfo> shippingBackOrderInfoList) {

        SHPG_BO_DTLTMsg shpgBoDtlTMsg = new SHPG_BO_DTLTMsg();
        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
        int cnt = 1;

        for (ShippingBackOrderInfo shippingBackOrderInfo : shippingBackOrderInfoList) {

            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.soNum, param.soNum);
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.soSlpNum, String.format(STRING_NUM_PATTERN, Integer.parseInt(shippingBackOrderInfo.getMaxSoSlpNum()) + cnt));
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.rtlWhCd, shippingBackOrderInfo.getRtlWhCdOfInvtyLoc());
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.rtlSwhCd, shippingBackOrderInfo.getRtlSwhCdOfInvtyLoc());
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.invtyLocCd, shippingBackOrderInfo.getInvtyLocCd());
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.shipToRtlWhCd, shippingBackOrderInfo.getRtlWhCdOfShipToCust());
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.shipToRtlSwhCd, shippingBackOrderInfo.getRtlSwhCdOfShipToCust());
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.toInvtyLocCd, shippingBackOrderInfo.getShipToCustCd());
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.setMdseCd, shippingBackOrderInfo.getSetMdseCd());
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.setMdseNm, shippingBackOrderInfo.getMdseNm());
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.shpgPlnNum, shippingBackOrderInfo.getShpgPlnNum());
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.mdseCd, shippingBackOrderInfo.getMdseCd());
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.trxHdrNum, shippingBackOrderInfo.getTrxHdrNum());
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.trxLineNum, shippingBackOrderInfo.getTrxLineNum());
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.trxLineSubNum, shippingBackOrderInfo.getTrxLineSubNum());

            //set Purchase Requisition Information
            if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(shippingBackOrderInfo.getSceOrdTpCd())) {

                ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.prchReqNum, shippingBackOrderInfo.getOrdSrcRefNum());
                ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.prchReqLineNum, shippingBackOrderInfo.getOrdSrcRefLineNum());
                ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.prchReqLineSubNum, new BigDecimal(shippingBackOrderInfo.getOrdSrcRefLineSubNum()));

            } else if (NLXSceConst.SCE_ORD_TP_CD_IT.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_TR.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_RP.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_RE.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_DI.equals(shippingBackOrderInfo.getSceOrdTpCd())) {

                        ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.prchReqNum, shippingBackOrderInfo.getPrchReqNum());
                        ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.prchReqLineNum, shippingBackOrderInfo.getPrchReqLineNum());
                        ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.prchReqLineSubNum, new BigDecimal(shippingBackOrderInfo.getPrchReqLineSubNum()));

            } else if (NLXSceConst.SCE_ORD_TP_CD_RD.equals(shippingBackOrderInfo.getSceOrdTpCd())) {

                ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.prchReqNum, shippingBackOrderInfo.getTrxHdrNum());
                ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.prchReqLineNum, shippingBackOrderInfo.getTrxLineNum());
                ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.prchReqLineSubNum, new BigDecimal(shippingBackOrderInfo.getTrxLineSubNum()));

            }

            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.fromStkStsCd, shippingBackOrderInfo.getStkStsCd());

            if (NLXSceConst.SCE_ORD_TP_CD_SC.equals(shippingBackOrderInfo.getSceOrdTpCd())) {

                ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.toStkStsCd, shippingBackOrderInfo.getToStkStsCd());
            }

            if (shippingBackOrderInfo.getInInchVol() != null) {

                ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.totShpgVol, shippingBackOrderInfo.getInInchVol().multiply(shippingBackOrderInfo.getOrdQtyOfShpgPln()));
            }

            if (shippingBackOrderInfo.getInPoundWt() != null) {

                ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.totShpgWt, shippingBackOrderInfo.getInPoundWt().multiply(shippingBackOrderInfo.getOrdQtyOfShpgPln()));
            }

            if (shippingBackOrderInfo.getSpFuncNetUnitPrcAmt() != null) {

                ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.shpgPrcAmt, shippingBackOrderInfo.getSpFuncNetUnitPrcAmt().multiply(shippingBackOrderInfo.getOrdQtyOfShpgPln()));
            }

            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.unitPrcAmt, shippingBackOrderInfo.getSpFuncNetUnitPrcAmt());

            //set Request Order Quantity
            if (NLXSceConst.SCE_ORD_TYPE_REGULAR.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TYPE_TRAIL_SALE.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TYPE_TRAIL_USE.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TYPE_LOAN.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TYPE_RS.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_DT.equals(shippingBackOrderInfo.getSceOrdTpCd())) {

                        ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.rqstOrdQty, shippingBackOrderInfo.getOrdQtyOfCpoDtl());

            } else if (NLXSceConst.SCE_ORD_TP_CD_IT.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_TR.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_BB.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_RP.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_IC.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_RM.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_DI.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_SC.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_DE.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_DC.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_SW.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_CC.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_RF.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_KT.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_KU.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TYPE_EXPORT.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_RD.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TYPE_RI.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_RE.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_RL.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_RX.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_RU.equals(shippingBackOrderInfo.getSceOrdTpCd())) {

                        ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.rqstOrdQty, shippingBackOrderInfo.getOrdQtyOfShpgPln());
            }

            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.shpgQty, BigDecimal.ZERO);

            if (shpgBoDtlTMsg.rqstOrdQty.getValue() != null) {

                ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.shpgBalQty, shpgBoDtlTMsg.rqstOrdQty.getValue().subtract(shpgBoDtlTMsg.shpgQty.getValue()));
            }

            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.boQty, shippingBackOrderInfo.getOrdQtyOfShpgPln());
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.custMdseCd, shippingBackOrderInfo.getCustMdseCd());
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.inEachQty, shippingBackOrderInfo.getInEachQty());

            //set Configuration Item Flag
            if (NLXSceConst.SCE_ORD_TYPE_REGULAR.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TYPE_TRAIL_SALE.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TYPE_TRAIL_USE.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TYPE_LOAN.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TYPE_RS.equals(shippingBackOrderInfo.getSceOrdTpCd())
                    || NLXSceConst.SCE_ORD_TP_CD_DT.equals(shippingBackOrderInfo.getSceOrdTpCd())) {

                ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.configItemFlg, shippingBackOrderInfo.getConfigItemFlg());

            } else if (NLXSceConst.SCE_ORD_TP_CD_CC.equals(shippingBackOrderInfo.getSceOrdTpCd())) {

                ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.configItemFlg, ZYPConstant.FLG_ON_Y);

            } else {

                ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.configItemFlg, ZYPConstant.FLG_OFF_N);
            }

            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.preIstlShopRqstFlg, shippingBackOrderInfo.getShopItemFlg());
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.backOrdImpctTpCd, shippingBackOrderInfo.getBackOrdImpctTpCd());
            ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.dsSoLineStsCd, DS_SO_LINE_STS.BACKORDERED);

            //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            queryParam.put("cpoOrdNum", shpgBoDtlTMsg.trxHdrNum.getValue());
            queryParam.put("cpoDtlLineNum", shpgBoDtlTMsg.trxLineNum.getValue());
            queryParam.put("cpoDtlLineSubNum", shpgBoDtlTMsg.trxLineSubNum.getValue());
            queryParam.put("configCatgCd", "O");
            queryParam.put("mdseCd", shpgBoDtlTMsg.mdseCd.getValue());

            Map<String, Object> resultMap = (Map<String,Object>) this.ssmBatchClient.queryObject("getModelCriticalityForBO", queryParam);

            if (resultMap!=null && resultMap.size() > 0) {
                if (ZYPCommonFunc.hasValue((String)resultMap.get("BACK_ORD_IMPCT_TP_CD"))) {
                    ZYPEZDItemValueSetter.setValue(shpgBoDtlTMsg.backOrdImpctTpCd, (String)resultMap.get("BACK_ORD_IMPCT_TP_CD"));
                }
            }
            //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
            S21ApiTBLAccessor.insert(shpgBoDtlTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgBoDtlTMsg.getReturnCode())) {

                msgMap.addXxMsgId(NLZM2258E);
                S21InfoLogOutput.println(NLZM2258E, getErrParam(shpgBoDtlTMsg));
                return false;
            }
            cnt++;
        }

        return true;
    }

    /**
     * Create Shipping Order Schedule
     * @param msgMap S21ApiMessageMap
     * @param shippingOrderInfoList List<ShippingOrderInfo>
     * @param shpgOrdTMsg SHPG_ORD_TMSg
     * @return boolean
     */
    private boolean setShippingOrderSchedule(S21ApiMessageMap msgMap, List<ShippingOrderInfo> shippingOrderInfoList, SHPG_ORDTMsg shpgOrdTMsg) {

        NLZC205001PMsg  param = (NLZC205001PMsg) msgMap.getPmsg();
        final String mode = param.xxModeCd.getValue();

        /*** do create Shipping Order Schedule ***/

        //set Schedule Coordination Status Code
        if (MODE_NEW.equals(mode)) {

            /* 02/24/2016 CSAI Y.Imazu Add QC#2051 START */
            if (isAutoSchdCoord(param.glblCmpyCd.getValue(), shippingOrderInfoList.get(0).getDsOrdCatgCd(), shippingOrderInfoList.get(0).getDsOrdTpCd())) {

                if (ZYPCommonFunc.hasValue(shippingOrderInfoList.get(0).getPddDt())) {

                    ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdCoordStsCd, SCHD_COORD_STS.SCHEDULED);
                    ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdDelyDt, shippingOrderInfoList.get(0).getPddDt());
                }

                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdCarrPickUpDt, shippingOrderInfoList.get(0).getPsdDt());

            } else {

                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdCoordStsCd, SCHD_COORD_STS.AWAITING_SCHEDULING);

            }

            NLXC041001SchdCoordInfo schdCoordInfo = NLXC041001GetDisptSchdCoord.getDisptSchdCoord(param.glblCmpyCd.getValue(), this.slsDt, param.soNum.getValue(), null, null);

            if (schdCoordInfo == null) {

                msgMap.addXxMsgId(NLBM1340W);
                S21InfoLogOutput.println(NLBM1340W);

            } else if (schdCoordInfo.getErrList() != null && !schdCoordInfo.getErrList().isEmpty()) {

                boolean isErrMsg = false;

                for (String msgId : schdCoordInfo.getErrList()) {

                    msgMap.addXxMsgId(msgId);
                    S21InfoLogOutput.println(msgId);

                    if (msgId.endsWith("E")) {

                        isErrMsg = true;
                    }
                }

                if (isErrMsg) {

                    return false;
                }

            } else {

                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdCoordPsnCd, schdCoordInfo.getSchdCoordPsnCd());
            }

        } else if (MODE_DROP_SHIP.equals(mode)) {
            
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdCoordStsCd, SCHD_COORD_STS.SCHEDULED);
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdDelyDt, param.etaDt);
        }

        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.carrCd, shippingOrderInfoList.get(0).getVndCd());
        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.shpgSvcLvlCd, shippingOrderInfoList.get(0).getShpgSvcLvlCd());

        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.tempSchdFlg, ZYPConstant.FLG_OFF_N);

        /*** do create Shipping Order Schedule Tracking ***/
        if (!createShippingOrderScheduleTracking(msgMap, shpgOrdTMsg)) {

            return false;
        }

        return true;
    }

    /**
     * Create Shipping Order Schedule Tracking
     * @param msgMap S21ApiMessageMap
     * @param shpgOrdTMsg SHPG_ORDTMsg
     * @return boolean
     */
    private boolean createShippingOrderScheduleTracking(S21ApiMessageMap msgMap, SHPG_ORDTMsg shpgOrdTMsg) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        SHPG_ORD_SCHD_TRKTMsg shpgOrdSchdTrkTMsg = new SHPG_ORD_SCHD_TRKTMsg();

        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.shpgOrdSchdTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SHPG_ORD_SCHD_TRK_SQ"));
        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.updUsrId, shpgOrdTMsg.ezUpUserID);
        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.updTs, shpgOrdTMsg.ezUpTime);
        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.soNum, shpgOrdTMsg.soNum);
        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdCoordStsCd, shpgOrdTMsg.schdCoordStsCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdCoordPsnCd, shpgOrdTMsg.schdCoordPsnCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdDelyDt, shpgOrdTMsg.schdDelyDt);
        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.carrCd, shpgOrdTMsg.carrCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.shpgSvcLvlCd, shpgOrdTMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.tempSchdFlg, shpgOrdTMsg.tempSchdFlg);
        /* 06/12/2017 CITS Y.Imazu Add QC#18991 START */
        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.tempSchdRsnCd, shpgOrdTMsg.tempSchdRsnCd);
        /* 06/12/2017 CITS Y.Imazu Add QC#18991 END */
        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.tempSchdCmntTxt, shpgOrdTMsg.tempSchdCmntTxt);
        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdDurnTmNum, shpgOrdTMsg.schdDurnTmNum);
        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdIstlDt, shpgOrdTMsg.schdIstlDt.getValue());
        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdIstlTm, shpgOrdTMsg.schdIstlTm.getValue());
        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdCarrPickUpDt, shpgOrdTMsg.schdCarrPickUpDt.getValue());
        // QC#18272 Add.
        ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdDelyTm, param.schdDelyTm.getValue());

        S21ApiTBLAccessor.insert(shpgOrdSchdTrkTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdSchdTrkTMsg.getReturnCode())) {

            msgMap.addXxMsgId(NLZM2260E);
            S21InfoLogOutput.println(NLZM2260E, getErrParam(shpgOrdSchdTrkTMsg));
            return false;
        }

        return true;
    }

    /* 02/24/2016 CSAI Y.Imazu Add QC#2051 START */
    /**
     * Check Auto Schedule Coordination Order
     * @param glblCmpyCd String
     * @param dsOrdCatgCd String
     * @param dsOrdTpCd String
     * @return boolean
     */
    private boolean isAutoSchdCoord(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {

        if (!ZYPCommonFunc.hasValue(dsOrdCatgCd)) {

            return false;
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsOrdCatgCd", dsOrdCatgCd);
        queryParam.put("dsOrdTpCd", dsOrdTpCd);

        BigDecimal exclSchdCoordCnt = (BigDecimal) ssmBatchClient.queryObject("getExclSchdCoordCnt", queryParam);

        if (exclSchdCoordCnt != null && exclSchdCoordCnt.compareTo(BigDecimal.ZERO) > 0) {

            return true;
        }

        return false;
    }

    /**
     * Get SO-Serial Info
     * @param S21ApiMessageMap interface
     * @return List<SoSerialInfo>
     */
    private List<SoSerialInfo> getSoSerialInfo(S21ApiMessageMap msgMap) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        // Get SoSerial Info
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("soNum", param.soNum.getValue());
        List<SoSerialInfo> soSerialInfoList = (List<SoSerialInfo>) this.ssmBatchClient.queryObjectList("getSoSerialInfo", queryParam);

        return soSerialInfoList;
    }

    /**
     * Create SO Serial
     * @param msgMap S21ApiMessageMap
     * @param soSerialInfoList List<SoSerialInfo>
     * @param onBatchType ONBATCH_TYPE
     */
    private void createSoSerial(S21ApiMessageMap msgMap, List<SoSerialInfo> soSerialInfoList, final ONBATCH_TYPE onBatchType) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
        NLZC401001PMsg inPMsg = new NLZC401001PMsg();
        int i = 0;

        if (!soSerialInfoList.isEmpty()) {

            //START 2016/2/12 K.Lee Start WDS_NA QC#4290
            ZYPEZDItemValueSetter.setValue(inPMsg.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inPMsg.slsDt, this.slsDt);
            //END 2016/2/12 K.Lee Start WDS_NA QC#4290

            for (SoSerialInfo soSerialInfo : soSerialInfoList) {

                ZYPEZDItemValueSetter.setValue(inPMsg.serInfo.no(i).xxProcTpCd, ORDER_SERIAL_MODE);
                ZYPEZDItemValueSetter.setValue(inPMsg.serInfo.no(i).mdseCd, soSerialInfo.getMdseCd());
                ZYPEZDItemValueSetter.setValue(inPMsg.serInfo.no(i).serNum, soSerialInfo.getSerNum());
                ZYPEZDItemValueSetter.setValue(inPMsg.serInfo.no(i).soNum, param.soNum);
                ZYPEZDItemValueSetter.setValue(inPMsg.serInfo.no(i).soSlpNum, soSerialInfo.getSoSlpNum());
                ZYPEZDItemValueSetter.setValue(inPMsg.serInfo.no(i).trxHdrNum, soSerialInfo.getTrxHdrNum());
                ZYPEZDItemValueSetter.setValue(inPMsg.serInfo.no(i).trxLineNum, soSerialInfo.getTrxLineNum());
                ZYPEZDItemValueSetter.setValue(inPMsg.serInfo.no(i).trxLineSubNum, soSerialInfo.getTrxLineSubNum());
                ZYPEZDItemValueSetter.setValue(inPMsg.serInfo.no(i).trxSrcTpCd, soSerialInfo.getTrxSrcTpCd());

                i++;
            }

            //START 2016/2/12 K.Lee Start WDS_NA QC#4290
            inPMsg.serInfo.setValidCount(i);
            //END 2016/2/12 K.Lee Start WDS_NA QC#4290

            NLZC401001 api = new NLZC401001();
            api.execute(inPMsg, onBatchType);

            checkApiErr(msgMap, inPMsg);
        }
    }

    /**
     * checkApiErr
     * @param msgMap S21ApiMessageMap
     * @param pMsg NLZC401001PMsg
     */
    private void checkApiErr(S21ApiMessageMap msgMap, NLZC401001PMsg pMsg) {

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);

            for (int i = 0; i < msgList.size(); i++) {

                msgMap.addXxMsgId(msgList.get(i));
            }
        }
    }

    /**
     * <pre>
     * do ModeModify(Update SO)
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    private boolean doModeModify(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        CalculateResult calculateResult = null;

        SHPG_ORDTMsg shpgOrdTMsg = null;

        // Calculate PSD/PDD
        if (ZYPCommonFunc.hasValue(param.rsdDt) || ZYPCommonFunc.hasValue(param.rddDt)) {

            shpgOrdTMsg = getShpgOrd(param.glblCmpyCd.getValue(), param.soNum.getValue());

            if (shpgOrdTMsg == null) {

                msgMap.addXxMsgId(NLZM2271E);
                S21InfoLogOutput.println(NLZM2271E);
                return false;
            }

            calculateResult = calculatePsdPdd(msgMap, shpgOrdTMsg);

            if (calculateResult == null) {

                return false;
            }

            // Update Shipping Order
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.psdDt, calculateResult.getPsdDt());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.pddDt, calculateResult.getPddDt());

            if (ZYPCommonFunc.hasValue(param.schdCoordStsCd)) {

                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdCoordStsCd, param.schdCoordStsCd.getValue());
            }

            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdCoordPsnCd, param.schdCoordPsnCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdDelyDt, calculateResult.getPddDt());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.carrCd, param.carrCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.shpgSvcLvlCd, param.shpgSvcLvlCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.tempSchdRsnCd, param.tempSchdRsnCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.tempSchdCmntTxt, param.tempSchdCmntTxt.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdDurnTmNum, param.schdDurnTmNum.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdIstlDt, param.schdIstlDt.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdIstlTm, param.schdIstlTm.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdCarrPickUpDt, calculateResult.getPsdDt());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.carrAcctNum, param.carrAcctNum.getValue());
            // QC#18272 Add.
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdDelyTm, param.schdDelyTm.getValue());
            // START 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.nextActDt, param.nextActDt.getValue());
            // END 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
            
            // 201/12/28 QC#18460(SOL#087) T,Hakodate ADD START
            if (ZYPCommonFunc.hasValue(param.techMeetTruckFlg)) {
                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.techMeetTruckFlg, param.techMeetTruckFlg.getValue());
            }
            // 201/12/28 QC#18460(SOL#087) T,Hakodate ADD END

            S21ApiTBLAccessor.update(shpgOrdTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdTMsg.getReturnCode())) {

                msgMap.addXxMsgId(NLZM2129E);
                S21InfoLogOutput.println(NLZM2129E, getErrParam(shpgOrdTMsg));
                return false;
            }

            // Update Shipping Plan
            if (!callShpgPlnAPI(msgMap, calculateResult, onBatchType)) {

                return false;
            }

            ZYPEZDItemValueSetter.setValue(param.rsdDt, calculateResult.getPsdDt());
            ZYPEZDItemValueSetter.setValue(param.rddDt, calculateResult.getPddDt());
        } else {

            shpgOrdTMsg = getShpgOrd(param.glblCmpyCd.getValue(), param.soNum.getValue());

            if (shpgOrdTMsg == null) {

                msgMap.addXxMsgId(NLZM2271E);
                S21InfoLogOutput.println(NLZM2271E);
                return false;
            }

            if (ZYPCommonFunc.hasValue(param.schdCoordStsCd)) {

                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdCoordStsCd, param.schdCoordStsCd.getValue());
            }

            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdCoordPsnCd, param.schdCoordPsnCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdDelyDt, param.rddDt.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.carrCd, param.carrCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.shpgSvcLvlCd, param.shpgSvcLvlCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.tempSchdRsnCd, param.tempSchdRsnCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.tempSchdCmntTxt, param.tempSchdCmntTxt.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdDurnTmNum, param.schdDurnTmNum.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdIstlDt, param.schdIstlDt.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdIstlTm, param.schdIstlTm.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdCarrPickUpDt, param.rsdDt.getValue());
            // QC#18272 Add.
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdDelyTm, param.schdDelyTm.getValue());
            // START 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.nextActDt, param.nextActDt.getValue());
            // END 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
            
            // 201/12/28 QC#18460(SOL#087) T,Hakodate ADD START
            if (ZYPCommonFunc.hasValue(param.techMeetTruckFlg)) {
                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.techMeetTruckFlg, param.techMeetTruckFlg.getValue());
            }
            // 201/12/28 QC#18460(SOL#087) T,Hakodate ADD END

            S21ApiTBLAccessor.update(shpgOrdTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdTMsg.getReturnCode())) {

                msgMap.addXxMsgId(NLZM2129E);
                S21InfoLogOutput.println(NLZM2129E, getErrParam(shpgOrdTMsg));
                return false;
            }

        }

        // Insert Shipping Order Schedule Tracking
        if (!createShippingOrderScheduleTracking(msgMap, shpgOrdTMsg)) {

            return false;
        }

        return true;
    }

    /* 08/26/2016 CSAI Y.Imazu Add QC#9845 START */
    /**
     * doWmsDrop
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    private boolean doWmsDrop(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        // Update Shipping Order
        SHPG_ORDTMsg shpgOrdTMsg = getShpgOrd(param.glblCmpyCd.getValue(), param.soNum.getValue());

        if (shpgOrdTMsg == null) {

            msgMap.addXxMsgId(NLZM1028E);
            S21InfoLogOutput.println(NLZM1028E);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.wmsDropRqstFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.wmsDropFlg, ZYPConstant.FLG_OFF_N);
        // START 2018/01/23 S.Katsuma [QC#23049,ADD]
        if (isSameWhAsCarrier(shpgOrdTMsg)) {
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.delyReqFlg, ZYPConstant.FLG_ON_Y);
        }
        // END 2018/01/23 S.Katsuma [QC#23049,ADD]

        S21ApiTBLAccessor.update(shpgOrdTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdTMsg.getReturnCode())) {

            msgMap.addXxMsgId(NLZM2129E);
            S21InfoLogOutput.println(NLZM2129E, getErrParam(shpgOrdTMsg));
            return false;
        }

        return true;
    }

    /**
     * doCarrRqst
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    private boolean doCarrRqst(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        // Update Shipping Order
        SHPG_ORDTMsg shpgOrdTMsg = getShpgOrd(param.glblCmpyCd.getValue(), param.soNum.getValue());

        if (shpgOrdTMsg == null) {

            msgMap.addXxMsgId(NLZM1028E);
            S21InfoLogOutput.println(NLZM1028E);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.delyReqFlg, ZYPConstant.FLG_ON_Y);
        // START 2018/01/23 S.Katsuma [QC#23049,ADD]
        if (isSameWhAsCarrier(shpgOrdTMsg)) {
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.wmsDropRqstFlg, ZYPConstant.FLG_ON_Y);
        }
        // END 2018/01/23 S.Katsuma [QC#23049,ADD]

        S21ApiTBLAccessor.update(shpgOrdTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdTMsg.getReturnCode())) {

            msgMap.addXxMsgId(NLZM2129E);
            S21InfoLogOutput.println(NLZM2129E, getErrParam(shpgOrdTMsg));
            return false;
        }

        return true;
    }

    /**
     * getShpgOrd
     * @param glblCmpyCd String
     * @param soNum String
     * @return SHPG_ORDTMsg
     */
    private SHPG_ORDTMsg getShpgOrd(String glblCmpyCd, String soNum) {

        SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.soNum, soNum);
        return (SHPG_ORDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(shpgOrdTMsg);
    }

    /**
     * <pre>
     * calculate PSD/PDD(almost same logic from Routing Wave NLBB0020)
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param shpgOrdTMsg SHPG_ORDTMsg
     * @return CalculateResult
     */
    private CalculateResult calculatePsdPdd(S21ApiMessageMap msgMap, SHPG_ORDTMsg shpgOrdTMsg) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        /* 08/26/2016 CSAI Y.Imazu Add QC#9845 START */
        CalculateResult calculateResult = new CalculateResult();
        calculateResult.setPsdDt(param.rsdDt.getValue());
        calculateResult.setPddDt(param.rddDt.getValue());

        if (ZYPCommonFunc.hasValue(param.rsdDt) && ZYPCommonFunc.hasValue(param.rddDt)) {

            return calculateResult;
        }

        int transLeadTmDays = 1;

        String schdLeadTmCarcMode = ZYPCodeDataUtil.getVarCharConstValue("SCHD_LEAD_TM_CARC_MODE", param.glblCmpyCd.getValue());

        // Transportation Lead Time is fixed value
        if (!ZYPCommonFunc.hasValue(schdLeadTmCarcMode) || SCHD_LEAD_TM_FIX_MODE.equals(schdLeadTmCarcMode)) {

            String defTransLeadTm = ZYPCodeDataUtil.getVarCharConstValue("SCHD_DEF_LEAD_TM", param.glblCmpyCd.getValue());

            if (ZYPCommonFunc.hasValue(defTransLeadTm) && ZYPCommonFunc.isNumberCheck(defTransLeadTm)) {

                transLeadTmDays = Integer.parseInt(defTransLeadTm);
            }

        // Transportation Lead Time is calculated value
        } else {
        /* 08/26/2016 CSAI Y.Imazu Add QC#9845 END */

            // get Calculate-Info
            DeliveryDateCalcInfo calcInfo = getDeliveryDateCalcInfo(msgMap);

            /* 08/26/2016 CSAI Y.Imazu Add QC#9845 START */
            if (calcInfo != null) {

                if (!ZYPCommonFunc.hasValue(param.shpgSvcLvlCd)) {

                    calcInfo.setShpgSvcLvlCd(null);
                    calcInfo.setShpgSvcTpCd(null);
                    calcInfo.setDelyLeadAot(null);

                } else if (!param.shpgSvcLvlCd.getValue().equals(calcInfo.getShpgSvcLvlCd())) {

                    Map<String, Object> queryParam = new HashMap<String, Object>();
                    queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
                    queryParam.put("shpgSvcLvlCd", param.shpgSvcLvlCd.getValue());
                    Map<String, Object> shpgSvcLvlInfoMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getShpgSvcLvlInfo", queryParam);

                    if (shpgSvcLvlInfoMap != null) {

                        calcInfo.setShpgSvcLvlCd((String) shpgSvcLvlInfoMap.get("SHPG_SVC_LVL_CD"));
                        calcInfo.setShpgSvcTpCd((String) shpgSvcLvlInfoMap.get("SHPG_SVC_TP_CD"));
                        calcInfo.setDelyLeadAot((BigDecimal) shpgSvcLvlInfoMap.get("DELY_LEAD_AOT"));

                    } else {

                        calcInfo.setShpgSvcLvlCd(null);
                        calcInfo.setShpgSvcTpCd(null);
                        calcInfo.setDelyLeadAot(null);
                    }
                }

                if (SHPG_SVC_TP.GUARANTEED_DAYS_DELIVERY.equals(calcInfo.getShpgSvcTpCd()) && ZYPCommonFunc.hasValue(calcInfo.getDelyLeadAot())) {

                    transLeadTmDays = hourToDay(calcInfo.getDelyLeadAot()).intValue();

                } else {

                    if (!ZYPCommonFunc.hasValue(calcInfo.getShpgModeCd())) {

                        String shpgModeCd = getShpgModeCd(msgMap, calcInfo);

                        // Can't get ShpgMode from "SHPG_SVC" -> Get ShpgMode from "SHPG_WT"
                        if (!ZYPCommonFunc.hasValue(shpgModeCd)) {

                            shpgModeCd = getShpgModeCdFromShpgWt(msgMap, calcInfo);
                        }

                        if (ZYPCommonFunc.hasValue(shpgModeCd)) {

                            calcInfo.setShpgModeCd(shpgModeCd);
                        }
                    }

                    /* 08/26/2016 CSAI Y.Imazu Add QC#9845 START */
                    if (ZYPCommonFunc.hasValue(calcInfo.getShpgModeCd())) {

                        BigDecimal transLeadTmHour = getTransportationLT(msgMap, calcInfo, calcInfo.getShpgModeCd());

                        if (ZYPCommonFunc.hasValue(transLeadTmHour)) {

                            transLeadTmDays = hourToDay(transLeadTmHour).intValue();
                        }
                    }
                }
            }
        }

        // Set PDD
        if (ZYPCommonFunc.hasValue(param.rsdDt)) {

            calculateResult.setPddDt(ZYPDateUtil.addDays(param.rsdDt.getValue(), transLeadTmDays));

        // Set PSD
        } else {

            // Get W/H calender code
            String whCalCd = null;

            CAL_RELNTMsg calRelnTMsg = getCalReln(CAL_SUB_TP.WAREHOUSE_CALENDAR, shpgOrdTMsg.whCd.getValue(), param.glblCmpyCd.getValue());

            if (calRelnTMsg == null) {

                // If WH Calendar doesn't set up ,use CUSA Calendar.
                whCalCd = param.glblCmpyCd.getValue();

            } else {

                whCalCd = calRelnTMsg.calTpCd.getValue();
            }

            calculateResult.setPsdDt(ZYPDateUtil.addBusinessDay(whCalCd, param.rddDt.getValue(), transLeadTmDays * -1));
        }

        return calculateResult;

    }

    /**
     * <pre>
     * get DeliveryDateCalc Info
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @return DeliveryDateCalcInfo
     */
    private DeliveryDateCalcInfo getDeliveryDateCalcInfo(S21ApiMessageMap msgMap) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        // Get SoSerial Info
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("soNum", param.soNum.getValue());
        return (DeliveryDateCalcInfo) this.ssmBatchClient.queryObject("getDeliveryDateCalcInfo", queryParam);
    }

    /**
     * Get Shipping Mode Code
     * @param shpgOrdT ShippingOrderTable
     * @return MaxEDITransactionID
     */
    private String getShpgModeCd(S21ApiMessageMap msgMap, DeliveryDateCalcInfo calcInfo) {

        /* 08/26/2016 CSAI Y.Imazu Add QC#9845 START */
        if (!ZYPCommonFunc.hasValue(calcInfo.getShpgSvcLvlCd()) || !ZYPCommonFunc.hasValue(calcInfo.getFrtChrgToCd()) || !ZYPCommonFunc.hasValue(calcInfo.getFrtChrgMethCd())) {

            return null;
        }
        /* 08/26/2016 CSAI Y.Imazu Add QC#9845 END */

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        // SQL bind parameter
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("shpgSvcLvlCd", calcInfo.getShpgSvcLvlCd());
        queryParam.put("frtChrgToCd", calcInfo.getFrtChrgToCd());
        queryParam.put("frtChrgMethCd", calcInfo.getFrtChrgMethCd());

        // If get plural then system error
        List<String> shpgModeCdList = (List<String>) ssmBatchClient.queryObjectList("getMinShpgModeCd", queryParam);

        if (shpgModeCdList != null && !shpgModeCdList.isEmpty()) {

            return shpgModeCdList.get(0);
        }

        return null;
    }

    /**
     * Get Shipping Mode Code
     * @param msgMap S21ApiMessageMap
     * @param calcInfo DeliveryDateCalcInfo
     * @return String
     */
    private String getShpgModeCdFromShpgWt(S21ApiMessageMap msgMap, DeliveryDateCalcInfo calcInfo) {

        /* 08/26/2016 CSAI Y.Imazu Add QC#9845 START */
        if (!ZYPCommonFunc.hasValue(calcInfo.getFrtChrgToCd()) || !ZYPCommonFunc.hasValue(calcInfo.getFrtChrgMethCd())) {

            return null;
        }
        /* 08/26/2016 CSAI Y.Imazu Add QC#9845 END */

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        // SQL bind parameter
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        /* 08/26/2016 CSAI Y.Imazu Add QC#9845 START */
        queryParam.put("sellToCustCd", calcInfo.getSellToCustCd());
        queryParam.put("shipToCustCd", calcInfo.getShipToCustCd());
        queryParam.put("transMinWt", getTotShpgWt(param.glblCmpyCd.getValue(), param.soNum.getValue()));
        /* 08/26/2016 CSAI Y.Imazu Add QC#9845 END */
        queryParam.put("frtChrgToCd", calcInfo.getFrtChrgToCd());
        queryParam.put("frtChrgMethCd", calcInfo.getFrtChrgMethCd());
        queryParam.put("effDt", this.slsDt);

        List<String> shpgModeCdList = (List<String>) ssmBatchClient.queryObjectList("getShpgModeCdListFromShpgWt", queryParam);

        if (shpgModeCdList != null && !shpgModeCdList.isEmpty()) {

            return shpgModeCdList.get(0);
        }

        /* 08/26/2016 CSAI Y.Imazu Add QC#9845 START */
        queryParam.put("shipToCustCd", "*");

        shpgModeCdList = (List<String>) ssmBatchClient.queryObjectList("getShpgModeCdListFromShpgWt", queryParam);

        if (shpgModeCdList != null && !shpgModeCdList.isEmpty()) {

            return shpgModeCdList.get(0);
        }

        queryParam.put("sellToCustCd", "*");

        shpgModeCdList = (List<String>) ssmBatchClient.queryObjectList("getShpgModeCdListFromShpgWt", queryParam);

        if (shpgModeCdList != null && !shpgModeCdList.isEmpty()) {

            return shpgModeCdList.get(0);
        }
        /* 08/26/2016 CSAI Y.Imazu Add QC#9845 END */

        return null;
    }

    /* 08/26/2016 CSAI Y.Imazu Add QC#9845 START */
    /**
     * Get Total Shipping Weight
     * @param glblCmpyCd String
     * @param soNum String
     * @return BigDecimal
     */
    private BigDecimal getTotShpgWt(String glblCmpyCd, String soNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("soNum", soNum);
        queryParam.put("flgY", ZYPConstant.FLG_ON_Y);

        BigDecimal totShpgWt = (BigDecimal) ssmBatchClient.queryObject("getTotShpgWt", queryParam);

        if (totShpgWt == null) {

            totShpgWt = BigDecimal.ZERO;
        }

        return totShpgWt;
    }

    /**
     * Get CAL_RELN
     * @param calSubTpCd CAL_SUB_TP_CD
     * @param calMultCd CAL_MULT_CD
     * @return CAL_RELNTMsg
     */
    private CAL_RELNTMsg getCalReln(String calSubTpCd, String calMultCd, String glblCmpyCd) {

        /* 08/26/2016 CSAI Y.Imazu Mod QC#9845 START */
        CAL_RELNTMsg calReln = new CAL_RELNTMsg();
        /* 08/26/2016 CSAI Y.Imazu Mod QC#9845 END */
        ZYPEZDItemValueSetter.setValue(calReln.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(calReln.calSubTpCd, calSubTpCd);
        ZYPEZDItemValueSetter.setValue(calReln.calMultCd, calMultCd);
        calReln = (CAL_RELNTMsg) EZDTBLAccessor.findByKey(calReln);

        return calReln;
    }

    /**
     * Convert Hour Value to Day Value
     * @param hourValue BigDecimal
     * @return Day Value (If not divided, round up)
     */
    private BigDecimal hourToDay(BigDecimal hourValue) {

        BigDecimal dayValue = hourValue.divide(TWENTY_FOUR_HOURS, 0, BigDecimal.ROUND_UP);
        return dayValue;
    }

    /**
     * Get Transportation LT
     * @param msgMap S21ApiMessageMap
     * @param calcInfo DeliveryDateCalcInfo
     * @param shpgModeCd SHPG_MODE_CD
     * @return BigDecimal
     */
    private BigDecimal getTransportationLT(S21ApiMessageMap msgMap, DeliveryDateCalcInfo calcInfo, String shpgModeCd) {

        BigDecimal transportationLt;

        if (SHPG_SVC_TP.GUARANTEED_DAYS_DELIVERY.equals(calcInfo.getShpgSvcTpCd())) {

            transportationLt = calcInfo.getDelyLeadAot();

        // Get lead time of Ground Standard Delivery / Pick Up
        } else {

            TRNSP_LTTMsg trnspLt = getTrnspLt(msgMap, calcInfo, shpgModeCd);

            if (trnspLt == null) {

                AREA_LEAD_TMTMsg areaLeadTm = getAreaLeadTm(msgMap, calcInfo, shpgModeCd);

                if (areaLeadTm == null) {

                    return null;
                }

                transportationLt = areaLeadTm.delyLeadAot.getValue();

            } else {

                transportationLt = trnspLt.trnspLtAot.getValue();
            }
        }

        return transportationLt;
    }

    /**
     * Get TRNSP_LT
     * @param calcInfo DeliveryDateCalcInfo
     * @param shpgModeCd SHPG_MODE_CD
     * @return TRNSP_LTTMsg
     */
    private TRNSP_LTTMsg getTrnspLt(S21ApiMessageMap msgMap, DeliveryDateCalcInfo calcInfo, String shpgModeCd) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        // Create key of cache map as search condition
        String whCd = calcInfo.getWhCd();
        String shipToStCd = calcInfo.getShipToStCd();
        String shipToPostCd = calcInfo.getShipToPostCd();

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("whCd", whCd);
        queryParam.put("shpgModeCd", shpgModeCd);
        queryParam.put("shipToStCd", shipToStCd);
        queryParam.put("shipToPostCd", shipToPostCd);
        /* 08/26/2016 CSAI Y.Imazu Mod QC#9845 START */
        queryParam.put("effDt", this.slsDt);
        List<TRNSP_LTTMsg> trnspLtList = (List<TRNSP_LTTMsg>) this.ssmBatchClient.queryObjectList("getTrnspLt", queryParam);
        /* 08/26/2016 CSAI Y.Imazu Mod QC#9845 END */

        TRNSP_LTTMsg trnspLt = null;

        if (trnspLtList.isEmpty()) {

            trnspLt = null;

        } else {

            trnspLt = trnspLtList.get(0);
        }

        return trnspLt;
    }

    /**
     * Get AREA_LEAD_TM
     * @param calcInfo DeliveryDateCalcInfo
     * @param shpgModeCd SHPG_MODE_CD
     * @return AREA_LEAD_TMTMsg
     */
    private AREA_LEAD_TMTMsg getAreaLeadTm(S21ApiMessageMap msgMap, DeliveryDateCalcInfo calcInfo, String shpgModeCd) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        // Create key of cache map as search condition
        String whCd = calcInfo.getWhCd();
        String shipToStCd = calcInfo.getShipToStCd();

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("whCd", whCd);
        queryParam.put("shpgModeCd", shpgModeCd);
        queryParam.put("shipToStCd", shipToStCd);
        /* 08/26/2016 CSAI Y.Imazu Mod QC#9845 START */
        queryParam.put("effDt", this.slsDt);
        List<AREA_LEAD_TMTMsg> areaLeadTmList = (List<AREA_LEAD_TMTMsg>) this.ssmBatchClient.queryObjectList("getAreaLeadTm", queryParam);
        /* 08/26/2016 CSAI Y.Imazu Mod QC#9845 END */

        AREA_LEAD_TMTMsg areaLeadTm = null;

        if (areaLeadTmList.isEmpty()) {

            msgMap.addXxMsgId(NLZM2151E);
            S21InfoLogOutput.println(NLZM2151E);
            areaLeadTm = null;

        } else {

            areaLeadTm = areaLeadTmList.get(0);
        }

        return areaLeadTm;
    }

    /**
     * call Shipping-Plan-Update-API(NWZC0030) For RSD/RDD/PSD/PDD Update
     * @param msgMap S21ApiMessageMap
     * @param calculateResult CalculateResult
     * @param onBatchType ONBATCH_TYPE
     * @return true or false(error)
     */
    public static boolean callShpgPlnAPI(S21ApiMessageMap msgMap, CalculateResult calculateResult, final ONBATCH_TYPE onBatchType) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        // get Shipping Plan Number(SHPG_PLN)
        SHPG_PLNTMsg shpgPlnNumTMsg = new SHPG_PLNTMsg();
        shpgPlnNumTMsg.setSQLID("037");
        shpgPlnNumTMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        shpgPlnNumTMsg.setConditionValue("soNum01", param.soNum.getValue());
        SHPG_PLNTMsgArray shpgPlnArray = (SHPG_PLNTMsgArray) EZDTBLAccessor.findByCondition(shpgPlnNumTMsg);

        // no data in SHPG_PLN
        if (shpgPlnArray == null || shpgPlnArray.getValidCount() == 0) {

            msgMap.addXxMsgId(NLZM0123E);
            S21InfoLogOutput.println(NLZM0123E);
            return false;
        }

        // make PMsgList for ShippingPlan-Update-API(NWZC0030)
        List<NWZC003001PMsg> pMsgList = new ArrayList<NWZC003001PMsg>(shpgPlnArray.getValidCount());

        for (int i = 0; i < shpgPlnArray.getValidCount(); i++) {

            SHPG_PLNTMsg shpgPlnTMsg = shpgPlnArray.no(i);

            NWZC003001PMsg nwzc003001PMsg = new NWZC003001PMsg();
            ZYPEZDItemValueSetter.setValue(nwzc003001PMsg.shpgModeCd, NWZC003001.MODE_PSD_PDD_RSD_RDD_UPDATE);
            ZYPEZDItemValueSetter.setValue(nwzc003001PMsg.shpgPlnNum, shpgPlnTMsg.shpgPlnNum);
            ZYPEZDItemValueSetter.setValue(nwzc003001PMsg.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(nwzc003001PMsg.psdDt, calculateResult.getPsdDt());
            ZYPEZDItemValueSetter.setValue(nwzc003001PMsg.pddDt, calculateResult.getPddDt());
            ZYPEZDItemValueSetter.setValue(nwzc003001PMsg.rsdDt, param.rsdDt.getValue());
            ZYPEZDItemValueSetter.setValue(nwzc003001PMsg.rddDt, param.rddDt.getValue());
            pMsgList.add(nwzc003001PMsg);
        }

        // execute ShippingPlan-Update-API(NWZC0030)
        new NWZC003001().execute(pMsgList, onBatchType);

        // error check
        for (NWZC003001PMsg nwzc003001PMsg : pMsgList) {

            if (nwzc003001PMsg.xxMsgIdList.getValidCount() > 0) {

                /* 08/26/2016 CSAI Y.Imazu Add QC#9845 START */
                for (String msgId : S21ApiUtil.getXxMsgIdList(nwzc003001PMsg)) {

                    msgMap.addXxMsgId(msgId);
                    S21InfoLogOutput.println(msgId);

                    if (msgId.endsWith("E")) {

                        return false;
                    }
                }
                /* 08/26/2016 CSAI Y.Imazu Add QC#9845 END */
            }
        }

        return true;
    }

    /**
     * <pre>
     * Get Table Name and Primary Key String List
     * </pre>
     * @param ezdTMsg target TMsg.
     * @return String[2]{Table Name, Primary Key String}
     */
    private static String[] getErrParam(EZDTMsg ezdTMsg) {

        StringBuilder pk = new StringBuilder();
        List[] keyColumnList = ezdTMsg.getKeyColumnList();

        for (int i = 0; i < keyColumnList[1].size(); i++) {

            pk.append((String) keyColumnList[1].get(i));
            pk.append(DELIMITER);
            pk.append(ezdTMsg.getDBValue((String) keyColumnList[0].get(i)));
            pk.append(SPACE);
        }

        return new String[] {ezdTMsg.getTableName(), pk.toString() };
    }    
    /**
     * isAutoSoDropFlgChkWh
     * @param shpgOrdTMsg
     * @return
     */
    private boolean isAutoSoDropFlgChkWh(SHPG_ORDTMsg shpgOrdTMsg) {

        String[] autoSoDropFlgChkList = null;
        String autoSoDropFlgChkListValue = ZYPCodeDataUtil.getVarCharConstValue(AUTO_SO_DROP_FLG_CHK_LIST, shpgOrdTMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(autoSoDropFlgChkListValue)) {
            autoSoDropFlgChkList = autoSoDropFlgChkListValue.split(",");
        } else {
            return false;
        }

        if (shpgOrdTMsg.whCd != null) {
            RTL_WHTMsg  rtlWhTmsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWhTmsg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rtlWhTmsg.rtlWhCd, shpgOrdTMsg.whCd.getValue());
            rtlWhTmsg = (RTL_WHTMsg) S21ApiTBLAccessor.findByKeyForUpdate(rtlWhTmsg);
            if (rtlWhTmsg != null && rtlWhTmsg.whOwnrCd != null) {
                String whOwnrCd = rtlWhTmsg.whOwnrCd.getValue();
                if (Arrays.asList(autoSoDropFlgChkList).contains(whOwnrCd)){
                    return true;
                }
            } else {
                return false;
            }
            
        } else {
            return false;
        }
        return false;
    }

    // 201/12/28 QC#18460(SOL#087) T,Hakodate ADD START
    private String getRequestInstallTime(String trxHdrNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.hdrGlblCmpyCd);
        queryParam.put("trxHdrNum", trxHdrNum);

        return (String) ssmBatchClient.queryObject("getRequestInstallTime", queryParam);
    }
    // 201/12/28 QC#18460(SOL#087) T,Hakodate ADD ENS

    // 2018/01/10 QC#18460(SOL#087) T,Hakodate ADD START
    private String getTechMeetTruckFlg(String trxHdrNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.hdrGlblCmpyCd);
        queryParam.put("trxHdrNum", trxHdrNum);
        queryParam.put("flgOnY", ZYPConstant.FLG_ON_Y);

        String result = (String) ssmBatchClient.queryObject("getTechMeetTruckFlg", queryParam);

        if (ZYPCommonFunc.hasValue(result)) {
            return ZYPConstant.FLG_ON_Y;
        } else {
            return ZYPConstant.FLG_OFF_N;
        }
    }
    // 2018/0110 QC#18460(SOL#087) T,Hakodate END START

    // START 2018/01/23 S.Katsuma [QC#23049,ADD]
    private RTL_WHTMsg getRtlWh(String glblCmpyCd, String whCd) {
        if (ZYPCommonFunc.hasValue(whCd)) {
            RTL_WHTMsg rtlWhTmsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWhTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rtlWhTmsg.rtlWhCd, whCd);
            rtlWhTmsg = (RTL_WHTMsg) S21ApiTBLAccessor.findByKeyForUpdate(rtlWhTmsg);

            if (rtlWhTmsg != null) {
                return rtlWhTmsg;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private VNDTMsg getVnd(String glblCmpyCd, String vndCd) {
        if (ZYPCommonFunc.hasValue(vndCd)) {
            VNDTMsg vndTMsg = new VNDTMsg();
            vndTMsg.setSQLID(SQL_ID_VND_001);
            vndTMsg.setConditionValue(TAK_GLBL_CMPY_CD, glblCmpyCd);
            vndTMsg.setConditionValue(TAK_VND_CD, vndCd);
            VNDTMsgArray vndTMsgArray = (VNDTMsgArray) S21ApiTBLAccessor.findByCondition(vndTMsg);

            if (vndTMsgArray != null && vndTMsgArray.length() > 0) {
                return (VNDTMsg) vndTMsgArray.get(0);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private boolean isSameWhAsCarrier(SHPG_ORDTMsg shpgOrdTMsg) {
        boolean rtnFlg = false;
        if (ZYPCommonFunc.hasValue(shpgOrdTMsg.whCd) && ZYPCommonFunc.hasValue(shpgOrdTMsg.carrCd)) {
            RTL_WHTMsg rtlWhTMsg = getRtlWh(shpgOrdTMsg.glblCmpyCd.getValue(), shpgOrdTMsg.whCd.getValue());
            if (rtlWhTMsg != null) {
                VNDTMsg vndTMsg = getVnd(shpgOrdTMsg.glblCmpyCd.getValue(), shpgOrdTMsg.carrCd.getValue());
                if (vndTMsg != null) {
                    if ((WH_OWNR.APX.equals(rtlWhTMsg.whOwnrCd.getValue()) && CARR_TP.APEX.equals(vndTMsg.carrTpCd.getValue())) ||
                            (WH_OWNR.STI.equals(rtlWhTMsg.whOwnrCd.getValue()) && CARR_TP.STI.equals(vndTMsg.carrTpCd.getValue())) ||
                            // START 2023/04/05 [QC#61001,ADD]
                            (WH_OWNR.AGW.equals(rtlWhTMsg.whOwnrCd.getValue()) && CARR_TP.AGW.equals(vndTMsg.carrTpCd.getValue()))) {
                            // END   2023/04/05 [QC#61001,ADD]
                        rtnFlg = true;
                    }
                }
            }
        }

        return rtnFlg;
    }
    // END 2018/01/23 S.Katsuma [QC#23049,ADD]

    /**
     * QC#20154 Add method.
     */
    private void doUpdShipAddrAndWms(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();

        // Param check
        if (checkRequiredParam(msgMap)) {
            // Error.
            return;
        }

        if (!ZYPCommonFunc.hasValue(param.shpgFrceFlg)) {
            ZYPEZDItemValueSetter.setValue(param.shpgFrceFlg, ZYPConstant.FLG_OFF_N);
        }

        // Master check And get SHPG_ORDTMsg
        SHPG_ORDTMsg so = getShpgOrd(param.glblCmpyCd.getValue(), param.soNum.getValue());
        if (so == null) {
            msgMap.addXxMsgId(NLZM1028E);
            S21InfoLogOutput.println(NLZM1028E);
            return;
        }

        // Status check
        if (isShipped(param.glblCmpyCd.getValue(), param.soNum.getValue())) {
            if(isOpenSoFromSameCpoPosn(param.glblCmpyCd.getValue(), param.shpgPlnNum.getValue())){
                // Not set Warning Message.
                // Skip process.
                return;
            } else {
                // Warning : Ship to address cannot be update if it is
                // already shipped or partially shipped.
                msgMap.addXxMsgId(NLZM2518W);
                S21InfoLogOutput.println(NLZM2518W);
                return;
            }
        }

        // Delete SHPG_ORD_CUST_DTL(SHIP TO)
        deleteShpgOrdCustDtlOfShipto(param.glblCmpyCd.getValue(), param.soNum.getValue());

        // Create SHPG_ORD_CUST_DTL(SHIP TO)
        createShpgOrdCustDtlOfShipto(msgMap, onBatchType);

        // Update WMS
        if (ZYPCommonFunc.hasValue(so.whCd) //
                && isTecsys(so.glblCmpyCd.getValue(), so.whCd.getValue())) {
            doWmsDrop(msgMap, onBatchType);
        } else {
            // Warning : Ship to Address was changed. Please tell
            // warehouse manager the new Ship to Address.
            msgMap.addXxMsgId(NLZM2519W);
            S21InfoLogOutput.println(NLZM2519W);
        }

        // Logic End.
    }

    private boolean isOpenSoFromSameCpoPosn(String glblCmpyCd, String shpgPlnNum) {

        // Check CPO Order.
        Map<String, Object> queryParam1 = new HashMap<String, Object>();
        queryParam1.put("glblCmpyCd", glblCmpyCd);
        queryParam1.put("shpgPlnNum", shpgPlnNum);

        Map<String, String> cpoPosn = (Map<String, String>) ssmBatchClient.queryObject("cntShpgPlnFromCPO", queryParam1);

        if (cpoPosn == null || cpoPosn.isEmpty()) {
            // Not CPO Order.
            return false;
        }

        // Check Same Position Open SO.
        Map<String, Object> queryParam2 = new HashMap<String, Object>();
        queryParam2.put("glblCmpyCd", glblCmpyCd);
        queryParam2.put("cpoOrdNum", cpoPosn.get("CPO_ORD_NUM"));
        queryParam2.put("dsOrdPosnNum", cpoPosn.get("DS_ORD_POSN_NUM"));
        queryParam2.put("shipped", SHPG_STS.SHIPPED);

        List<String> openSoNumList = (List<String>) ssmBatchClient.queryObjectList("getOpenSoForSamePosnCPO", queryParam2);

        if (openSoNumList == null || openSoNumList.isEmpty()) {
            // Does not have open so.
            return false;
        }

        return true;
    }

    /**
     * isShipped
     * QC#20154 Add method.
     * @param glblCmpyCd String
     * @param soNum String
     * @return true:Shipped
     */
    private boolean isShipped(String glblCmpyCd, String soNum) {
        
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("soNum", soNum);
        queryParam.put("shipped", SHPG_STS.SHIPPED);

        Integer cntShipped = (Integer) ssmBatchClient.queryObject("cntShipped", queryParam);

        if(cntShipped > 0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * deleteShpgOrdCustDtlOfShipto
     * QC#20154 Add method.
     * If there is no data is skipped.
     * @param glblCmpyCd
     * @param soNum
     */
    private void deleteShpgOrdCustDtlOfShipto(String glblCmpyCd, String soNum) {

        SHPG_ORD_CUST_DTLTMsg shipTo = new SHPG_ORD_CUST_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(shipTo.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shipTo.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(shipTo.soCustDataTpCd, SO_CUST_DATA_TP.SHIP_TO);

        shipTo = (SHPG_ORD_CUST_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(shipTo);

        if (shipTo != null) {
            S21ApiTBLAccessor.remove(shipTo);
        }
    }

    /**
     * createShpgOrdCustDtlOfShipto
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    private void createShpgOrdCustDtlOfShipto(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NLZC205001PMsg param = (NLZC205001PMsg) msgMap.getPmsg();
        Map<String, String> baseSoHdr = new HashMap<String, String>();
        String shpgFrceFlg = param.shpgFrceFlg.getValue();
        boolean operationErrFlg = false;
        this.hdrGlblCmpyCd = param.glblCmpyCd.getValue();
        this.soNum = param.soNum.getValue();

        SCE_ORD_TPTMsg sceOrdTpT = getSceOrdTpTMsg(msgMap);

        if (sceOrdTpT == null) {
            msgMap.flush();
            return;
        }

        this.sceOrdTpSerNumTakeFlg = sceOrdTpT.serNumTakeFlg.getValue();

        if (ZYPConstant.FLG_ON_Y.equals(shpgFrceFlg)) {
            this.minShipPrty = getMinShipPrty(msgMap);
        }

        operationErrFlg = getBaseSoHdr(msgMap, baseSoHdr);

        if (operationErrFlg) {
            msgMap.flush();
            return;
        }

        String dropShipFlg = baseSoHdr.get(DROP_SHIP_FLG);

        if (ZYPConstant.FLG_OFF_N.equals(dropShipFlg)) {

            insertSoCustDtlShipTo(baseSoHdr);

            // Update Ship to cust code
            String updateShipToCustCd = baseSoHdr.get(SHIP_TO_CUST_CD);

            // START 2019/10/18 M.Naito [QC#54026,MOD]
//            if (SELECT_ORD_TP_LIST_FOR_SHIP_TO_CUST.contains(this.hdrSceOrdTpCd)) {
            if (SELECT_ORD_TP_LIST_FOR_SHIP_TO_CUST.contains(sceOrdTpT.sceOrdTpCd.getValue())) {
            // END 2019/10/18 M.Naito [QC#54026,MOD]

                updateShipToCustCd = baseSoHdr.get(RS2_RTL_WH_CD);

                if (!ZYPCommonFunc.hasValue(updateShipToCustCd) //
                        && SCE_ORD_TP.DISPOSAL.equals(this.hdrSceOrdTpCd)) {
                    updateShipToCustCd = baseSoHdr.get(SHIP_TO_CUST_CD);
                }
            }

            SHPG_ORDTMsg so = new SHPG_ORDTMsg();

            ZYPEZDItemValueSetter.setValue(so.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(so.soNum, param.soNum.getValue());

            so = (SHPG_ORDTMsg) S21ApiTBLAccessor.findByKey(so);

            // Already master check.
            if (updateShipToCustCd != null //
                    && !updateShipToCustCd.equals(so.shipToCustCd.getValue())) {

                so = (SHPG_ORDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(so);

                ZYPEZDItemValueSetter.setValue(so.shipToCustCd, updateShipToCustCd);

                S21ApiTBLAccessor.update(so);
            }

        } else {
            insertSoCustDtlShipToOneTmUsrFromSellTo(baseSoHdr);
        }

        // Logic End.
    }

    /**
     * isTecsys
     * QC#20154 Add method.
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return true:Tecsys
     */
    private boolean isTecsys(String glblCmpyCd, String rtlWhCd){
        
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("rtlWhCd", rtlWhCd);
        queryParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
        queryParam.put("tecsys", MW_GRP_CD_TECSYS);

        String wmsWhCd = (String) ssmBatchClient.queryObject("getWmsWhCdForTecsys", queryParam);
        
        if(wmsWhCd == null){
            return false;
        } else {
            return true;
        }
    }

    /**
     * isNewConfigOrder
     * @param soInfoList
     * @return boolean
     */
    private boolean isNewConfigRequest(List<ShippingOrderInfo> soInfoList) {
        // QC#29212
        // Other than Tecsys
        // If WH is other than Tecsys, SVC_CONFIG_MSTR_PK will be set by following rules.
        // A: New Config Item will be numbered as new Config ID (Use DS_CPO_CONFIG.SVC_CONFIG_MSTR_PK)
        // B: Existing Config Item will be numbered as same Config ID as Pick Config ID (Use CPO_DTL.PICK_SVC_CONFIG_MSTR_PK)
        // C: When IB is added by "Add accessary to Existing IB", existing Config ID is numbered (Use DS_CPO_CONFIG.SVC_CONFIG_MSTR_PK, same as A)
        // D: When Non-IB is added by "Initial Supply", existing Config ID is numbered (Use CPO_DTL.PICK_SVC_CONFIG_MSTR_PK, same as B)
        if (soInfoList == null || soInfoList.size() <= 0) {
            return false;
        }

        for (int n = 0; n < soInfoList.size(); ++n) {
            ShippingOrderInfo soInfo = soInfoList.get(n);
            String instlBaseCtrlFlg = soInfo.getInstlbaseCtrlFlg();
            BigDecimal pickSvcConfigMstrPk = soInfo.getPickSvcConfigMstrPk();
            if (pickSvcConfigMstrPk == null && ZYPConstant.FLG_ON_Y.equals(instlBaseCtrlFlg)) {
                return true;
            }
        }

        return false;
    }

    /**
     * getPickSvcConfig
     * @param soInfoList
     * @return BigDecimal
     */
    private BigDecimal getPickSvcConfig(List<ShippingOrderInfo> soInfoList) {
        // QC#29212
        // When Non-IB is added by "Initial Supply", some Pick Config ID can be null.
        if (soInfoList == null || soInfoList.size() <= 0) {
            return null;
        }

        for (int n = 0; n < soInfoList.size(); ++n) {
            ShippingOrderInfo soInfo = soInfoList.get(n);
            BigDecimal pickSvcConfigMstrPk = soInfo.getPickSvcConfigMstrPk();
            if (pickSvcConfigMstrPk != null) {
                return pickSvcConfigMstrPk;
            }
        }

        return null;
    }

    /**
     * isKeepConfigIdOrdTp
     * QC#30848 Add method.
     * @param glblCmpyCd String
     * @param dsOrdTpCd String
     */
    private boolean isKeepConfigIdOrdTp(String glblCmpyCd, String dsOrdTpCd){
        
        String varOrdTpCd = ZYPCodeDataUtil.getVarCharConstValue("KEEP_CONFIG_ID_ORD_TP  ", glblCmpyCd);

        if (varOrdTpCd != null) {

            List<String> ordTpCdList = new ArrayList<String>();

            String[] ordTpCds = varOrdTpCd.split(",");

            for (int i = 0; i < ordTpCds.length; i++) {

                ordTpCdList.add(ordTpCds[i]);
            }

            if (ordTpCdList.contains(dsOrdTpCd)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // Add Start 2019/12/13 QC#55070
    /**
     * checkGetShipToCtacPsnNm
     */
    private boolean checkGetShipToCtacPsnNm() {

        if (SCE_ORD_TP.DIRECT_SALES.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.DC_TRANSFER.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.REFURBISH_EXPENSE.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.DISPOSAL.equals(this.hdrSceOrdTpCd)
                || SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(this.hdrSceOrdTpCd)) {

            return true;
        }

        return false;
    }

    /**
     * getShipToCtacPsnNm
     * @param glblCmpyCd String
     * @param trxHdrNum String
     * @param maxLength int
     */
    private String getShipToCtacPsnNm(String glblCmpyCd, String trxHdrNum, int maxLength) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("trxHdrNum", trxHdrNum);
        queryParam.put("invtyReqEntry", CPO_SRC_TP.INVENTORY_REQUEST_ENTRY);

        String shipToCtacPsnNm = (String) ssmBatchClient.queryObject("getShipToCtacPsnNm", queryParam);

        if (shipToCtacPsnNm == null || shipToCtacPsnNm.isEmpty()) {
            return null;
        }

        shipToCtacPsnNm = getCtacPtnrPsnNm(shipToCtacPsnNm, maxLength);

        return shipToCtacPsnNm;
    }
    // Add End 2019/12/13 QC#55070
    // QC#54989 Add Start
    /**
     * callCustInfoGetApiForDefaultMode
     * @param glblCmpyCd
     * @param dsAcctNum
     * @param shipToCustCd
     * @param billToCustCd
     * @param xxMode
     * @param xxSelFlg
     * @return
     */
    private NMZC610001PMsg callCustInfoGetApiForDefaultMode(String glblCmpyCd, String dsAcctNum, String shipToCustCd, String billToCustCd, String xxMode, String xxSelFlg) {

        Map<String, Object> ordInfoMap = null;
        Map<String, Object> param = new HashMap<String, Object>();
        
        param = new HashMap<String, Object>();
        param.put("GLBL_CMPY_CD", this.hdrGlblCmpyCd);
        param.put("DS_COND_CONST_GRP_ID", "NPBB0010_DS_ORD_TP");
        param.put("DS_COND_CONST_VAL_TXT_01", ZYPConstant.FLG_OFF_N);

        ordInfoMap = (Map<String, Object>) ssmBatchClient.queryObject("getOrdInfo", param);
        if (ordInfoMap == null) {
            return null;
        }
        NWXC150001DefaultCustomerBean paramBean = new NWXC150001DefaultCustomerBean();
        paramBean.setGlblCmpyCd(glblCmpyCd);
        paramBean.setDsTrxRuleTpCd(getDsTrxRuleTpCd(glblCmpyCd, ordInfoMap));
        paramBean.setDsAcctNum(dsAcctNum);
        if (ZYPCommonFunc.hasValue(shipToCustCd)) {
            paramBean.setShipToCustCd(shipToCustCd);
        } else if (ZYPCommonFunc.hasValue(billToCustCd)) {
            if (!S21StringUtil.isEquals(NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO, xxMode)) {
                paramBean.setBillToCustCd(billToCustCd);
            }
        }
        paramBean.setXxMode(xxMode);
        paramBean.setXxSelFlg(xxSelFlg);
        paramBean.setOnBatchType(ONBATCH_TYPE.ONLINE);

        NMZC610001PMsg pMsg = NWXC150001DefaultCustomer.getDefaultCustomerData(paramBean);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                return null;
            }
        }

        return pMsg;
    }
    /**
     * getDsTrxRuleTpCd
     * @param glblCmpyCd
     * @param ordInfoMap
     * @return
     */
    private String getDsTrxRuleTpCd(String glblCmpyCd, Map<String, Object> ordInfoMap) {

        String dsOrdCatgCd = (String) ordInfoMap.get("DS_ORD_CATG_CD");
        String dsOrdTpCd = (String) ordInfoMap.get("DS_ORD_TP_CD");

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("003");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);
        condition.setConditionValue("dsOrdTpCd01", dsOrdTpCd);

        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);

        tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        return "";
    }
    /**
     * getBillToCustInfo
     * @param shipToCustCd
     * @param sellToCustCd
     * @return
     */
	private BILL_TO_CUSTTMsgArray getBillToCustInfo(String shipToCustCd, String sellToCustCd) {

        String defBillToCustCd = NWXC150001DefaultCustomer.getRelatedBillTo(this.hdrGlblCmpyCd, shipToCustCd);

        if (defBillToCustCd == null) {

            NMZC610001PMsg nMZC610001BSPMsg = callCustInfoGetApiForDefaultMode(this.hdrGlblCmpyCd, sellToCustCd, null, null, //
                    NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_ON_Y);

            if (nMZC610001BSPMsg == null) {
                return null;
            }

            defBillToCustCd = nMZC610001BSPMsg.DefaultBillShipList.no(0).billToCustCd.getValue();

            if (defBillToCustCd == null) {
                return null;
            }
        }

        String billToCustCd = defBillToCustCd;

        BILL_TO_CUSTTMsg condition = new BILL_TO_CUSTTMsg();
        condition.setSQLID("003");
        condition.setConditionValue("glblCmpyCd01", this.hdrGlblCmpyCd);
        condition.setConditionValue("billToCustCd01", billToCustCd);

        BILL_TO_CUSTTMsgArray tmsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);
        return tmsgArray;
    }
    /**
     * condInsSoCustDtlBillTo
     * @param rtlWhCd
     * @return
     */
    private boolean condInsSoCustDtlBillTo(String rtlWhCd) {

        if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
            return false;
        }

        if (SCE_ORD_TP.DC_TRANSFER.equals(this.hdrSceOrdTpCd) 
                || SCE_ORD_TP.DISPOSAL.equals(this.hdrSceOrdTpCd) 
                || SCE_ORD_TP.ITEM_CHANGE.equals(this.hdrSceOrdTpCd) 
                || SCE_ORD_TP.KITTING.equals(this.hdrSceOrdTpCd)) {

            if (isTecsys(this.hdrGlblCmpyCd, rtlWhCd)) {
                return true;
            }
        } 

        return false;
    }
    /**
     * getShipToCust4DI
     * @param shipToCustCd
     * @return
     */
    private SHIP_TO_CUSTTMsg getShipToCust4DI(String shipToCustCd) {
        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        shipToCustTMsg.setSQLID("004");
        shipToCustTMsg.setConditionValue("glblCmpyCd01", this.hdrGlblCmpyCd);
        shipToCustTMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        shipToCustTMsg.setMaxCount(1);
        SHIP_TO_CUSTTMsgArray shipToCustTMsgAry = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(shipToCustTMsg);

        // If an error occurs, add a message to the Message Map.
        if (shipToCustTMsgAry.getValidCount() == 0) {
            return null;
        }
        shipToCustTMsg = shipToCustTMsgAry.no(0);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shipToCustTMsg.getReturnCode())) {
            return null;
        }
        return shipToCustTMsg;
    }
    // QC#54989 Add End
}
