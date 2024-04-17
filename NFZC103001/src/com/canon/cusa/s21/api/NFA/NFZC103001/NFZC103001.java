/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFA.NFZC103001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import static java.util.Arrays.asList;

import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AJE_INV_ACCT_DISTTMsg;
import business.db.AJE_INV_ACCT_DIST_ERRTMsg;
import business.db.DS_INV_SLS_CRTMsg;
import business.db.DS_INV_SLS_CRTMsgArray;
import business.db.JRNL_ENTRYTMsg;
import business.parts.MdseTpAcct;
import business.parts.NFAC000101PMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFZC102001PMsg;
import business.parts.NFZC103001PMsg;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;
import business.parts.NFACommonJrnlEntryConstant.CoaSegment;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.api.NFA.NFZC103001.common.NFZC103001Common;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_FM_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INV_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_DEF_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_ACCTG_RULE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_INVTY_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_SPL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Invoice Distribution Create API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/21/2015   Fujitsu         T.Tanaka        Create          N/A
 * 01/26/2016   CSAI            K.Uramori       Update          Error Handling & Others
 * 03/21/2016   CSAI            K.Uramori       Update          QC#5770 To be able to re-generate distribution, delete existing data first.
 * 04/26/2016   CSAI            K.Uramori       Update          Remove deletion logic of distribution data in error. Modification of QC#5770 will delete the data.
 * 05/13/2016   Fujitsu         S.Fujita        Update          QC#6959
 * 06/07/2016   Fujitsu         S.Fujita        Update          QC#9515
 * 06/21/2016   CSAI            K.Uramori       Update          QC#10541
 * 07/13/2016   Fujitsu         C.Tanaka        Update          QC#6373
 * 09/05/2016   Fujitsu         S.Fujita        Update          QC#10156
 * 2016/09/05   Hitachi         K.Kojima        Update          QC#11049
 * 2016/09/26   Fujitsu         S.Fujita        Update          QC#13362
 * 2016/09/29   Hitachi         K.Kojima        Update          QC#14609
 * 2016/10/20   Hitachi         K.Kojima        Update          QC#14590
 * 2016/11/24   Hitachi         E.Kameishi      Update          QC#16103
 * 2016/12/02   Hitachi         Y.Tsuhimoto     Update          QC#16148
 * 2016/12/08   Fujitsu         T.Murai         Update          QC#16387
 * 2016/12/12   CSAI            K.Uramori       Update          QC#16075
 * 2016/12/26   Fujitsu         T.Murai         Update          QC#16818
 * 2017/07/06   Hitachi         J.Kim           Update          QC#19765
 * 2017/09/05   Fujitsu         H.Nagashima     Update          QC#19726
 * 2017/09/22   Fujitsu         H.Nagashima     Update          QC#17116
 * 2018/01/15   Hitachi         E.Kameishi      Update          QC#23166
 * 2018/03/12   Hitachi         E.Kameishi      Update          QC#23689
 * 2018/05/23   Hitachi         E.Kameishi      Update          QC#21841
 * 2018/06/12   Hitachi         E.Kameishi      Update          QC#26627
 * 2018/10/02   Hitachi         E.Kameishi      Update          QC#28364
 * 2018/10/26   Hitachi         E.Kameishi      Update          QC#28976
 * 2020/01/10   Fujitsu         Y.Matsui        Update          QC#55255   add parameter mtMap to commonJrnlEntry.setValueToJrnlEntryByAjePtrn
 * </pre>
 */
public class NFZC103001 extends S21ApiCommonBase {

    /** */
    public static final String COA_CMPY_CD = "COA_CMPY_CD";

    /** */
    public static final String COA_AFFL_CD = "COA_AFFL_CD";

    /** */
    public static final String COA_BR_CD = "COA_BR_CD";

    /** */
    public static final String COA_CH_CD = "COA_CH_CD";

    /** */
    public static final String COA_CC_CD = "COA_CC_CD";

    /** */
    public static final String COA_ACCT_CD = "COA_ACCT_CD";

    /** */
    public static final String COA_PROD_CD = "COA_PROD_CD";

    /** */
    public static final String COA_PROJ_CD = "COA_PROJ_CD";

    /** */
    public static final String COA_EXTN_CD = "COA_EXTN_CD";

    /** */
    public static final String TRX_CD = "TRX_CD";

    /** */
    public static final String TRX_RSN_CD = "TRX_RSN_CD";

    /** */
    public static final String DEAL_SLS_CR_AMT = "DEAL_SLS_CR_AMT";

    /** */
    public static final String FUNC_SLS_CR_AMT = "FUNC_SLS_CR_AMT";

    /** */
    public static final String INV_LINE_SPL_PCT = "INV_LINE_SPL_PCT";

    /** */
    public static final String SLS_REP_CR_PCT = "SLS_REP_CR_PCT";

    /** */
    public static final String DS_INV_SLS_CR_PK = "DS_INV_SLS_CR_PK";

    /** */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** */
    public static final String MDSE_CD = "MDSE_CD";

    /** */
    public static final String INV_NUM = "INV_NUM";

    /** */
    public static final String INV_TP_CD = "INV_TP_CD";

    /** */
    public static final String ACCT_DT = "ACCT_DT";

    /** */
    public static final String INV_DT = "INV_DT";

    /** */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** */
    public static final String DEAL_CCY_CD = "DEAL_CCY_CD";

    /** */
    public static final String INV_LINE_NUM = "INV_LINE_NUM";

    /** */
    public static final String INV_LINE_DEAL_TAX_AMT = "INV_LINE_DEAL_TAX_AMT"; // ADD 2016/12/07 [QC#16174]

    /** */
    public static final String INV_LINE_FUNC_TAX_AMT = "INV_LINE_FUNC_TAX_AMT"; // ADD 2016/12/07 [QC#16174]
    /** */

    public static final String FRT_DEAL_TAX_AMT = "FRT_DEAL_TAX_AMT"; // ADD 2016/12/07 [QC#16174]

    /** */
    public static final String FRT_FUNC_TAX_AMT = "FRT_FUNC_TAX_AMT"; // ADD 2016/12/07 [QC#16174]

    /** */
//  public static final String TOT_BOL_DEAL_TAX_AMT = "TOT_BOL_DEAL_TAX_AMT"; // DEL 2016/12/07 [QC#16174]

  /** */
//  public static final String TOT_BOL_FUNC_TAX_AMT = "TOT_BOL_FUNC_TAX_AMT"; // DEL 2016/12/07 [QC#16174]

    /** */
    public static final String INV_BOL_LINE_NUM = "INV_BOL_LINE_NUM";

    /** */
    public static final String INV_LINE_SUB_NUM = "INV_LINE_SUB_NUM";

    /** */
    public static final String INV_TRX_LINE_SUB_NUM = "INV_TRX_LINE_SUB_NUM";

    /** */
    public static final String SHIP_DEAL_FRT_AMT = "SHIP_DEAL_FRT_AMT";

    /** */
    public static final String SHIP_FUNC_FRT_AMT = "SHIP_FUNC_FRT_AMT";

    /** */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** */
    public static final String DS_ORD_TP_CD = "DS_ORD_TP_CD";

    /** */
    public static final String DS_ORD_RSN_CD = "DS_ORD_RSN_CD";

    /** */
    public static final String SYS_SRC_CD = "SYS_SRC_CD";

    /** */
    public static final String SYS_SRC_NM = "SYS_SRC_NM";

    /** */
    public static final String COA_ATTRB_NM_01 = "COA_ATTRB_NM_01";

    /** */
    public static final String COA_ATTRB_NM_02 = "COA_ATTRB_NM_02";

    /** */
    public static final String COA_ATTRB_NM_03 = "COA_ATTRB_NM_03";

    /** */
    public static final String COA_ATTRB_NM_04 = "COA_ATTRB_NM_04";

    /** */
    public static final String COA_ATTRB_NM_05 = "COA_ATTRB_NM_05";

    /** */
    public static final String COA_ATTRB_NM_06 = "COA_ATTRB_NM_06";

    /** */
    public static final String COA_ATTRB_NM_07 = "COA_ATTRB_NM_07";

    /** */
    public static final String COA_ATTRB_NM_08 = "COA_ATTRB_NM_08";

    /** */
    public static final String COA_ATTRB_NM_09 = "COA_ATTRB_NM_09";

    /** */
    public static final String DR_CR_TP_CD = "DR_CR_TP_CD";

    /** */
    public static final String DR_CR_TP_CD_CREDIT = "C";

    /** */
    public static final String DR_CR_TP_CD_DEBIT = "D";

    /** */
    public static final int DEBUG_MSG_LVL = 8;

    /** */
    public static final int ERR_MSG_TXT_LEN = 1000;

    /** */
    public static final int INVLD_VAL_TXT_LEN = 50;

    /** */
    static final String AJE_INTFC_TP_CD_VAL = "01";

    /** */
    static final String AJE_INV_ACCT_DIST_SQ = "AJE_INV_ACCT_DIST_SQ";
    
    /** */
    // static final String AJE_INV_ACCT_DIST_ERR_SQ =
    // "AJE_INV_ACCT_DIST_ERR_SQ";
    static final String AJE_INV_ACCT_DIST_ERR_SQ = "AJE_INV_ACCT_DIST_ERR_SQ";

    /** */
    static final String AJE_INV_ACCT_DIST_PK = "AJE_INV_ACCT_DIST_PK";

    /** */
    static final String AJE_INV_ACCT_CLS_CD = "AJE_INV_ACCT_CLS_CD";

    /** */
    static final String DR_CD = "D";

    /** */
    static final String CR_CD = "C";

    /** */
    private static String COAID_COA_CMPY = "1";

    /** */
    private static String COAID_COA_BR = "2";

    /** */
    private static String COAID_COA_CC = "3";

    /** */
    private static String COAID_COA_ACCT = "4";

    /** */
    private static String COAID_COA_PROD = "5";

    /** */
    private static String COAID_COA_CH = "6";

    /** */
    private static String COAID_COA_AFFL = "7";

    /** */
    private static String COAID_COA_PROJ = "8";

    /** */
    private static String COAID_COA_EXTN = "9";

    /** */
    private static String COAMSG_COA_CMPY = "CMPY";

    /** */
    private static String COAMSG_COA_BR = "BR";

    /** */
    private static String COAMSG_COA_CC = "CC";

    /** */
    private static String COAMSG_COA_ACCT = "ACCT";

    /** */
    private static String COAMSG_COA_PROD = "PROD";

    /** */
    private static String COAMSG_COA_CH = "CH";

    /** */
    private static String COAMSG_COA_AFFL = "AFFL";

    /** */
    private static String COAMSG_COA_PROJ = "PROJ";

    /** */
    private static String COAMSG_COA_EXTN = "EXTN";

    /** */
    private static String COA_SEG_ERR = "NFCM0531E";

    /** */
    private static String NO_AJE_PTRN_ERR = "NFAM0037E";
    
    /** */
    private static String NO_SLS_CR_ERR = "ZZZM9006E";

    // START 2016/12/26 T.Murai [QC#16818,ADD]
    private static final int INIT_AJE_LINE_IDX_NUM = 1;
    // END   2016/12/26 T.Murai [QC#16818,ADD]

    // START 2017/07/06 J.Kim [QC#19765,ADD]
    /** Const Name */
    private static String AJE_COA_DEF_TAX_ACCT = "AJE_COA_DEF_TAX_ACCT";
    // END 2017/07/06 J.Kim [QC#19765,ADD]

    // QC#19726 add Start
    private static String COA_ACCT_CD_ITEM_REV = "@ITEM-REV";
    private static String BS_PL_TP_CD_BS = "B";
    // QC#19726 add End

    /** */
    private String ajeInvAcctDistError = null;

    /** Size of Array */
    private int rmvMsgCount = 0;

    /** Array of TMsg */
    // private EZDTMsg[] ajeInvAcctDistMsgs;
    private List<AJE_INV_ACCT_DISTTMsg> listAcctDist = new ArrayList<AJE_INV_ACCT_DISTTMsg>();

    /** Array of TMsg */
    // private EZDTMsg[] ajeInvAcctDistErrMsgs;
    private List<AJE_INV_ACCT_DIST_ERRTMsg> listAcctDistErr = new ArrayList<AJE_INV_ACCT_DIST_ERRTMsg>();

    /** Array of TMsg */
    private EZDTMsg[] rmvMsgs;

    /** */
    static final int BULK_INSERT_COUNT = 100;

    /** Commit Count */
    private int commitCount = 0;

    /** Error Message (Unique Constraint Violation) */
    static final String ZZBM0074E = "ZZBM0074E";

    /** Error Message (Unexpected Error Occurred) */
    static final String NFDM0003E = "NFDM0003E";
    
    /** Error Message (There is no data to be processed.) */
    static final String NWAM0794E = "NWAM0794E";

    /** Error Message (It failed to update [@].) */
    static final String NFZM0027E = "NFZM0027E";

    /** Error Message Id. **/
    private String errMsgId = "";

    /** Error Message Txt. **/
    private String errMsgTxt = "";

    /** */
    private NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();

    /** */
    private S21SsmBatchClient ssmBatchClient;

    /** */
    private String prevBolNum_Tax = "";

    /** */
    private String prevLineNum_Tax = ""; // ADD 2016/12/07 [QC#16174]

    /** */
    private String prevBolNum_Frt_Tax = ""; // ADD 2016/12/07 [QC#16174]

    /** */
    private String prevBolNum_Frt = "";

    /** */
    private String zeroCratFlg = "";
    
    private List<String> ajeLineIndTpLink = new ArrayList<String>();
    // START 2016/11/24 E.Kameishi [QC#16103, ADD]
    /** newcore code */
    private String newcore;

    /** Default COA_BR_CD for newcore */
    private String nonNewCoreBr;

    /** Default COA_CC_CD for newcore */
    private String nonNewCoreCc;
    // END 2016/11/24 E.Kameishi [QC#16103, ADD]
    // START 2018/03/12 E.Kameishi [QC#23689,ADD]
    private String trxRsnContr;
    int ajeInvAcctDistSqNum = 0;
    boolean taxCrBreakFlg = false;
    boolean taxDrBreakFlg = false;
    boolean frtTaxCrBreakFlg = false;
    boolean frtTaxDrBreakFlg = false;
    boolean frtCrBreakFlg = false;
    boolean frtDrBreakFlg = false;
    int errCnt = 0;

    /** Error Message (Unexpected Error Occurred.) */
    static final String NFAM0032E = "NFAM0032E";
    // END 2018/03/12 E.Kameishi [QC#23689,ADD]
    // START 2017/07/06 J.Kim [QC#19765,ADD]
    /** Default COA_ACCT_CD for stAcctCd */
    private String stAcctCd;
    // END 2017/07/06 J.Kim [QC#19765,ADD]

    // QC#19726 add Start
    private String defCoaBrCd;
    private String defCoaCcCd;
    private String defCoaProdCd;
    private String defCoaChCd;
    private String defCoaAfflCd;
    private String defCoaProjCd;
    private String defCoaExtnCd;
    // QC#19726 add End

    // START 2018/05/23 E.Kameishi [QC#21841,MOD]
    private String COA_MDSE_TP_CD = "COA_MDSE_TP_CD";
    private String coaMdseTPCdFrt;
    // END 2018/05/23 E.Kameishi [QC#21841,MOD]

    // START 2020/01/10 [QC#55255,ADD]
    /** Merchandise Type Map */
    private Map<String, List<MdseTpAcct>> mtMap;
    // END 2020/01/10 [QC#55255,ADD]

    /**
     */
    public NFZC103001() {
        super();
    }

    /**
     * @param param NFZC103001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NFZC103001PMsg param, final ONBATCH_TYPE onBatchType) {

        debugLog("execute : start");

        if (param == null) {
            return;
        }

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // ---- 2016/01/27 change to array list
        // this.ajeInvAcctDistMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        // this.ajeInvAcctDistErrMsgs = new
        // EZDTMsg[BULK_INSERT_COUNT];
        // ---- end 2016/01/27
        this.rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        this.rmvMsgCount = 0;
        this.ajeInvAcctDistError = ZYPCodeDataUtil.getVarCharConstValue("AJE_INV_ACCT_DIST_ERROR", param.glblCmpyCd.getValue());
        // AJE Line Indicator Types by which values to be retrieved
        // from DS_INV_SLS_CR
        String lineIndTps = ZYPCodeDataUtil.getVarCharConstValue("AJE_LINE_IND_TP_LINK", param.glblCmpyCd.getValue());
        divLineIndTp(lineIndTps);

        //---- start 2016/0715
        zeroCratFlg = ZYPCodeDataUtil.getVarCharConstValue("AJE_ZERO_ENTRY_CRAT_FLG", param.glblCmpyCd.getValue());
        //---- end 2016/07/15
        
        //---- start 2016/04/26 delete
        //if (this.ajeInvAcctDistError.equals(PROC_STS.ERROR)) {
        //    removeAjeInvAcctDist(msgMap);
        //}
        //---- end 2016/04/26

        // QC#19726 add Start
        String ajeCoaDefValuesCsv = ZYPCodeDataUtil.getVarCharConstValue("AJE_COA_DEF_VALUES", param.glblCmpyCd.getValue());
        String[] ajeCoaDefValues = ajeCoaDefValuesCsv.split(",");
        this.defCoaBrCd   = ajeCoaDefValues[1];
        this.defCoaCcCd   = ajeCoaDefValues[2];
        this.defCoaProdCd = ajeCoaDefValues[4];
        this.defCoaChCd   = ajeCoaDefValues[5];
        this.defCoaAfflCd = ajeCoaDefValues[6];
        this.defCoaProjCd = ajeCoaDefValues[7];
        this.defCoaExtnCd = ajeCoaDefValues[8];
        // QC#19726 add End

        // START 2018/05/23 E.Kameishi [QC#21841,ADD]
        this.coaMdseTPCdFrt = ZYPCodeDataUtil.getVarCharConstValue("AJE_COA_MDSE_TP_FRT", param.glblCmpyCd.getValue());;
        // END 2018/05/23 E.Kameishi [QC#21841,ADD]
        // ---- start mode QC#5770
        // before processing, delete existing data
        deleteExistingData(param.glblCmpyCd.getValue(), param.invNum.getValue());
        deleteExistingErrData(param.glblCmpyCd.getValue(), param.invNum.getValue());
        // ---- end QC#5770
        // START 2018/03/12 E.Kameishi [QC#23689,DEL]
        // START 2016/11/24 E.Kameishi [QC#6103,ADD]
        //this.newcore = ZYPCodeDataUtil.getVarCharConstValue("AJE_NEW_CORE_CD", param.glblCmpyCd.getValue());
        //this.nonNewCoreBr = ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_BR_NONCORE", param.glblCmpyCd.getValue());
        //this.nonNewCoreCc = ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_CC_NONCORE", param.glblCmpyCd.getValue());
        // END 2016/11/24 E.Kameishi [QC#6103,ADD]
        // START 2017/07/06 J.Kim [QC#19765,ADD]
        //this.stAcctCd = ZYPCodeDataUtil.getVarCharConstValue(AJE_COA_DEF_TAX_ACCT, param.glblCmpyCd.getValue());
        // END 2017/07/06 J.Kim [QC#19765,ADD]
        // START 2018/03/12 E.Kameishi [QC#23689,DEL]
        // START 2018/01/15 E.Kameishi [QC#23166,ADD]
        String manAllocTrxRsnCd = ZYPCodeDataUtil.getVarCharConstValue("MAN_ALLOC_TRX_RSN_CD", param.glblCmpyCd.getValue());
        if (!setSvcInvChrgTp(param.glblCmpyCd.getValue(), param.invNum.getValue(), manAllocTrxRsnCd)) {
            addMsg(errMsgId, new String[] {errMsgTxt }, msgMap);
        }
        // END 2018/01/15 E.Kameishi [QC#23166,ADD]
        // START 2018/01/31 E.Kameishi [QC#23689,ADD]
        this.trxRsnContr = ZYPCodeDataUtil.getVarCharConstValue("CONTR_TRX_RSN_CD", param.glblCmpyCd.getValue());
        // END 2018/01/31 E.Kameishi [QC#23689,ADD]
        // START 2018/03/12 E.Kameishi [QC#23689,MOD]
        //if (!doAjeInvAcctDist(msgMap)) {
        //    addMsg(errMsgId, new String[] {errMsgTxt }, msgMap);
        //}

        // START 2020/01/10 [QC#55255,ADD]
        mtMap = commonJrnlEntry.getMdseTpAcct(param.glblCmpyCd.getValue());
        // END 2020/01/10 [QC#55255,ADD]

        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = (PreparedStatement) commonJrnlEntry.getInvoice(param.glblCmpyCd.getValue(), param.invNum.getValue(), param.procDt.getValue(), ps, null);
            rs = ps.executeQuery();

            if (!mainProcess(rs, msgMap, param)) {
                addMsg(errMsgId, new String[] {errMsgTxt }, msgMap);
            }
        } catch (SQLException ex) {
            errMsgId = NFAM0032E;
            errMsgTxt = ex.getMessage();
            addMsg(errMsgId, new String[] {errMsgTxt }, msgMap);
        } catch (NFACommonJrnlEntry.JrnlCommonException ex) {
            errMsgId = ZZBM0074E;
            addMsg(errMsgId, new String[] {errMsgTxt }, msgMap);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        // END 2018/03/12 E.Kameishi [QC#23689,MOD]

        msgMap.flush();
    }

    private void deleteExistingData(String glblCmpyCd, String invNum) {

        AJE_INV_ACCT_DISTTMsg tmsg = new AJE_INV_ACCT_DISTTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.invNum, invNum);

        S21FastTBLAccessor.removeByPartialValue(tmsg, new String[] {"glblCmpyCd", "invNum" });

    }

    private void deleteExistingErrData(String glblCmpyCd, String invNum) {

        AJE_INV_ACCT_DIST_ERRTMsg tmsg = new AJE_INV_ACCT_DIST_ERRTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.invNum, invNum);

        S21FastTBLAccessor.removeByPartialValue(tmsg, new String[] {"glblCmpyCd", "invNum" });

    }

    private void divLineIndTp(String str) {
        if (str == null) {
            str = "";
        }
        StringTokenizer st = new StringTokenizer(str, ",");

        while (st.hasMoreTokens()) {
            ajeLineIndTpLink.add(st.nextToken());
        }

    }

    /**
     * @param params List<NFZC103001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void excute(final List<NFZC103001PMsg> params, final ONBATCH_TYPE onBatchType) {

        if (params != null) {
            for (NFZC103001PMsg msg : params) {
                execute(msg, onBatchType);
            }
            return;
        }
    }

    // START 2018/03/12 E.Kameishi [QC#23689,MOD]
//    /**
//     * @param msgMap
//     * @return
//     */
//    private Boolean doAjeInvAcctDist(S21ApiMessageMap msgMap) {
//
//        NFZC103001PMsg param = (NFZC103001PMsg) msgMap.getPmsg();
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
//        queryParam.put("invNum", param.invNum.getValue());
//        queryParam.put("invoice", INV_INVTY_IND_TP.INVOICE);
//        queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
//        queryParam.put("dfrd", DFRD_ACCTG_RULE.DEFERRED);
//        queryParam.put("immediate", DFRD_ACCTG_RULE.IMMEDIATE);
//        queryParam.put("dspt", SVC_INV_SRC_TP.DISPATCH);
//        //---- start QC#12217 2016/07/20
//        queryParam.put("csmp", CM_DEF_ACCT.CSMP_COA);
//        //---- end QC#12217 2016/07/20
//        // START 2016/10/20 K.Kojima [QC#14590,ADD]
//        queryParam.put("lineBizTp_PPS", LINE_BIZ_TP.PPS);
//        queryParam.put("lineBizTp_LFS", LINE_BIZ_TP.LFS);
//        // END 2016/10/20 K.Kojima [QC#14590,ADD]
//
//        // START 2016/09/05 K.Kojima [QC#11049,DEL]
//        // String fromDate = addMonths(param.procDt.getValue(), -1);
//        // queryParam.put("fromDate", fromDate);
//        // END 2016/09/05 K.Kojima [QC#11049,DEL]
//        
//        //---- start 2016/10/21 QC#14547
//        queryParam.put("dev", INV_LINE_SPL_TP.DEVIATION);
//        //---- end 2016/10/21 QC#14547
//        // START 2016/11/24 E.Kameishi [QC#6103,ADD]
//        queryParam.put("newcore", this.newcore);
//        queryParam.put("defBrNewcore", this.nonNewCoreBr);
//        queryParam.put("defCcNewcore", this.nonNewCoreCc);
//        // END 2016/11/24 E.Kameishi [QC#6103,ADD]
//        // START 2016/12/02 Y.Tsuchimoto [QC#16148,ADD]
//        queryParam.put("bsd", DS_CONTR_CLS.BSD);
//        queryParam.put("fm", AJE_FM_IND_TP.FM);
//        queryParam.put("nonFm", AJE_FM_IND_TP.NON_FM);
//        // END   2016/12/02 Y.Tsuchimoto [QC#16148,ADD]
//        // START 2017/07/06 J.Kim [QC#19765,ADD]
//        queryParam.put("stAcctCd", this.stAcctCd);
//        // END 2017/07/06 J.Kim [QC#19765,ADD]
//        // 2017/09/22 QC#17116 add Start
//        queryParam.put("dsContrCatgFLEET", DS_CONTR_CATG.FLEET);
//        queryParam.put("svcAllocTpOPTIMA", SVC_ALLOC_TP.OPTIMA_SUPPLY_INCLUSIVE);
//        // 2017/09/22 QC#17116 add End
//        // START 2018/01/31 E.Kameishi [QC#23689,MOD]
//        DS_INV_SLS_CRTMsg dsInvSlsCr = new DS_INV_SLS_CRTMsg();
//        dsInvSlsCr.setSQLID("001");
//        dsInvSlsCr.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
//        dsInvSlsCr.setConditionValue("invNum01", param.invNum.getValue());
//        DS_INV_SLS_CRTMsgArray dsInvSlsCrTMsgAry = (DS_INV_SLS_CRTMsgArray) EZDTBLAccessor.findByCondition(dsInvSlsCr);
//
//        List<String> trxRsnContrList = asList(trxRsnContr.split(","));
//        boolean isContr = false;
//        for (int i = 0; i < dsInvSlsCrTMsgAry.getValidCount(); i++) {
//            DS_INV_SLS_CRTMsg tmpDsInvSlsCrTMsg = dsInvSlsCrTMsgAry.no(i);
//            if (TRX.SALES.equals(tmpDsInvSlsCrTMsg.trxCd.getValue())) {
//                if (trxRsnContrList.contains(tmpDsInvSlsCrTMsg.trxRsnCd.getValue())) {
//                    isContr = true;
//                    break;
//                }
//            }
//            
//        }
//        Boolean res = false;
//        if (isContr) {
//            res = (Boolean) ssmBatchClient.queryObject("getInvoiceForContract", queryParam, new AjeInvAcctDistHandler(msgMap));
//        } else {
//            res = (Boolean) ssmBatchClient.queryObject("getInvoice", queryParam, new AjeInvAcctDistHandler(msgMap));
//        }
//        
//         END 2018/01/31 E.Kameishi [QC#23689,MOD]
//        return res;
//    }
    // END 2018/03/12 E.Kameishi [QC#23689,MOD]

    /**
     * Add months
     * @param yyyyMMdd String
     * @param months String
     * @return String
     */
    public static String addMonths(String yyyyMMdd, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(yyyyMMdd.substring(0, 4)), Integer.parseInt(yyyyMMdd.substring(4, 6)) - 1, Integer.parseInt(yyyyMMdd.substring(6, 8)));
        calendar.add(Calendar.MONTH, months);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(calendar.getTime());
    }
    
    // START 2018/03/12 E.Kameishi [QC#23689,MOD]
    private boolean mainProcess(ResultSet rsNotJrnlized, S21ApiMessageMap msgMap, NFZC103001PMsg param) {

        try {
            if (rsNotJrnlized.first() == false) {
                
                AJE_INV_ACCT_DIST_ERRTMsg invErr = new AJE_INV_ACCT_DIST_ERRTMsg();

                BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_ERR_SQ);
                ZYPEZDItemValueSetter.setValue(invErr.glblCmpyCd, param.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(invErr.ajeInvAcctDistErrPk, pk);
                ZYPEZDItemValueSetter.setValue(invErr.invNum, param.invNum.getValue());
                ZYPEZDItemValueSetter.setValue(invErr.invLineNum, "00001");
                ZYPEZDItemValueSetter.setValue(invErr.invErrMsgTxt, S21MessageFunc.clspGetMessage(NO_SLS_CR_ERR, new String[] {"Sales Credit Information"}));

                S21ApiTBLAccessor.insert(invErr);
                
                errMsgId = NWAM0794E;
                return false;
            }
            rsNotJrnlized.beforeFirst();

            while (rsNotJrnlized.next()) {
                // AJE Pattern to be determined by pattern
                // indicator
                String ajeId = commonJrnlEntry.generateAjeId(rsNotJrnlized.getString(SYS_SRC_CD), rsNotJrnlized.getString(TRX_CD), rsNotJrnlized.getString(TRX_RSN_CD));

                // Get AJE Pattern List for Debit
                List<Map> resAjePtrnList = (List<Map>) commonJrnlEntry.getAjePtrn2(param.glblCmpyCd.getValue(), rsNotJrnlized.getString(SYS_SRC_CD), rsNotJrnlized.getString(TRX_CD), rsNotJrnlized.getString(TRX_RSN_CD), DR_CD);

                // refine AJE Pattern by pattern indicator codes
                List<NFAC000101PMsg> ajePtrnListByAjeIdAndIndTp = commonJrnlEntry.getAJEPtrnByAjeIdAndIndTp(ajeId, rsNotJrnlized, resAjePtrnList);

                // if pattern could not be obtained go to next
                // record. (No need to create one more error
                // record.)
                if (!doProcessPerPattern(ajePtrnListByAjeIdAndIndTp, rsNotJrnlized, DR_CD, msgMap, param)) {
                    continue;
                }

                // Get AJE Pattern List for Credit
                resAjePtrnList = (List<Map>) commonJrnlEntry.getAjePtrn2(param.glblCmpyCd.getValue(), rsNotJrnlized.getString(SYS_SRC_CD), rsNotJrnlized.getString(TRX_CD), rsNotJrnlized.getString(TRX_RSN_CD), CR_CD);

                // refine AJE Pattern by pattern indicator codes
                ajePtrnListByAjeIdAndIndTp = commonJrnlEntry.getAJEPtrnByAjeIdAndIndTp(ajeId, rsNotJrnlized, resAjePtrnList);

                doProcessPerPattern(ajePtrnListByAjeIdAndIndTp, rsNotJrnlized, CR_CD, msgMap, param);

            }

            // START 2018/05/23 E.Kameishi [QC#21841,MOD]
            createAjeInvAcctDist(null, rsNotJrnlized);
            // END 2018/05/23 E.Kameishi [QC#21841,MOD]

            createAjeInvAcctDistErr(null);

            // Update AJE_LINE_IDX_NUM in AJE_INV_ACCT_DIST
            if(!updateInvoice_ACCT_DISTWithIdxNum(param)) {
                return false;
            }

        } catch (NFACommonJrnlEntry.JrnlCommonException ex) {
            errMsgId = ZZBM0074E;
            return Boolean.FALSE;
        } catch (SQLException ex) {
            errMsgId = NFDM0003E;
            errMsgTxt = ex.getMessage();
            return Boolean.FALSE;
        }

        return Boolean.TRUE;

    }
    
    private boolean doProcessPerPattern(List<NFAC000101PMsg> ajePtrnListByAjeIdAndIndTp, ResultSet rsNotJrnlized, String drCrTpCd, S21ApiMessageMap msgMap, NFZC103001PMsg param) throws SQLException, NFACommonJrnlEntry.JrnlCommonException {

        // If AJE pattern could not be obtained, create error data
        // only.
        if (ajePtrnListByAjeIdAndIndTp.size() == 0) {

            AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr = new AJE_INV_ACCT_DIST_ERRTMsg();

            BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_ERR_SQ);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.glblCmpyCd, rsNotJrnlized.getString(GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistErrPk, pk);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invNum, rsNotJrnlized.getString(INV_NUM));
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invBolLineNum, rsNotJrnlized.getString(INV_BOL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineNum, rsNotJrnlized.getString(INV_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineSubNum, rsNotJrnlized.getString(INV_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invTrxLineSubNum, rsNotJrnlized.getString(INV_TRX_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, S21MessageFunc.clspGetMessage(NO_AJE_PTRN_ERR));

            createAjeInvAcctDistErr(ajeInvAcctDistErr);

            addMsg(NO_AJE_PTRN_ERR, null, msgMap);
            // ---- end 2016/01/26

            return false;
        }
        String glDt = NFZC103001Common.getGlDt(param.glblCmpyCd.getValue(), param.procDt.getValue(), rsNotJrnlized.getString(ACCT_DT));

        for (NFAC000101PMsg resAjePtrn : ajePtrnListByAjeIdAndIndTp) {

            AJE_INV_ACCT_DISTTMsg ajeInvAcctDist = new AJE_INV_ACCT_DISTTMsg();
            AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr = new AJE_INV_ACCT_DIST_ERRTMsg();

            // Set Common Data
            setAjeInvAcctDistCommonValues(param, ajeInvAcctDist, rsNotJrnlized, resAjePtrn, drCrTpCd, glDt);

            // Set Amount Data
            setAjeInvAcctDistAmountValues(param, ajeInvAcctDist, rsNotJrnlized, resAjePtrn, drCrTpCd, msgMap);

            // Set 9 Segments Data
            if (!setAjeInvAcctDist9SegmentsValues(param, ajeInvAcctDist, ajeInvAcctDistErr, rsNotJrnlized, resAjePtrn, drCrTpCd, msgMap)) {
                // error
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procStsCd, PROC_STS.ERROR);
            } else {
                // Check 9 Segments
                glCodeCombinationCheck(param, ajeInvAcctDist, ajeInvAcctDistErr, msgMap, rsNotJrnlized);
            }

            // START 2018/05/23 E.Kameishi [QC#21841,MOD]
            createAjeInvAcctDist(ajeInvAcctDist, rsNotJrnlized);
            // END 2018/05/23 E.Kameishi [QC#21841,MOD]

            // If any error, create error record
            if (PROC_STS.ERROR.equals(ajeInvAcctDist.procStsCd.getValue())) {

                // set value other than error message
                BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_ERR_SQ);
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.glblCmpyCd, ajeInvAcctDist.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistErrPk, pk);
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistPk, ajeInvAcctDist.ajeInvAcctDistPk.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invNum, ajeInvAcctDist.invNum.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invBolLineNum, ajeInvAcctDist.invBolLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineNum, ajeInvAcctDist.invLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineSubNum, ajeInvAcctDist.invLineSubNum.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invTrxLineSubNum, ajeInvAcctDist.invLineSubTrxNum.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistSqNum, ajeInvAcctDist.ajeInvAcctDistSqNum.getValue());

                String coaConcat = nvl(ajeInvAcctDist.ajeCoaCmpyCd.getValue()).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaBrCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaCcCd.getValue())).concat(",").concat(
                        nvl(ajeInvAcctDist.ajeCoaAcctCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaProdCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaChCd.getValue())).concat(",").concat(
                        nvl(ajeInvAcctDist.ajeCoaAfflCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaProjCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaExtnCd.getValue()));

                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invldValTxt, coaConcat);

                createAjeInvAcctDistErr(ajeInvAcctDistErr);
            }
        }

        return true;
    }

    private String nvl(String val) {
        if (hasValue(val)) {
            return val;
        } else {
            return "";
        }
    }

    /**
     * Set Common Values
     * @param param NFZC103001PMsg
     * @param jrnlEntry JRNL_ENTRYTMsg
     * @param invSlsCr ResultSet
     * @param resAjePtrn Map
     * @param drCrIndTpCd
     * @param glDt
     * @throws SQLException
     */
    private void setAjeInvAcctDistCommonValues(NFZC103001PMsg param, AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, ResultSet invSlsCr, NFAC000101PMsg resAjePtrn, String drCrIndTpCd, String glDt) throws SQLException {

        BigDecimal ajeInvAcctDistSeqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_SQ);

        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctDistPk, ajeInvAcctDistSeqNum);
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invNum, invSlsCr.getString(INV_NUM));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invBolLineNum, invSlsCr.getString(INV_BOL_LINE_NUM).toString());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invLineNum, invSlsCr.getString(INV_LINE_NUM).toString());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invLineSubNum, invSlsCr.getString(INV_LINE_SUB_NUM).toString());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invLineSubTrxNum, invSlsCr.getString(INV_TRX_LINE_SUB_NUM).toString());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctDistSqNum, String.format("%06d", this.ajeInvAcctDistSqNum));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procDt, param.procDt.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctClsCd, resAjePtrn.ajeLineIndTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.drCrTpCd, drCrIndTpCd);
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.dealCcyCd, invSlsCr.getString(DEAL_CCY_CD));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.funcCcyCd, invSlsCr.getString(DEAL_CCY_CD));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctDistPct, calcAjeInvAcctDistPct(invSlsCr, resAjePtrn.ajeLineIndTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.glDt, glDt);
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.dsInvSlsCrPk, invSlsCr.getBigDecimal(DS_INV_SLS_CR_PK));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeOmIntfcPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procStsCd, PROC_STS.COMPLEATED);

        // newly added fields
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.sysSrcCd, invSlsCr.getString(SYS_SRC_CD));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.sysSrcNm, invSlsCr.getString(SYS_SRC_NM));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.trxCd, resAjePtrn.trxCd.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.trxNm, resAjePtrn.trxNm.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.trxRsnCd, resAjePtrn.trxRsnCd.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.trxRsnNm, resAjePtrn.trxRsnNm.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpCd_01, resAjePtrn.ajePtrnIndTpCd_01.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpNm_01, resAjePtrn.ajePtrnIndTpNm_01.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlCd_01, resAjePtrn.ajePtrnActlCd_01.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlNm_01, resAjePtrn.ajePtrnActlNm_01.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpCd_02, resAjePtrn.ajePtrnIndTpCd_02.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpNm_02, resAjePtrn.ajePtrnIndTpNm_02.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlCd_02, resAjePtrn.ajePtrnActlCd_02.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlNm_02, resAjePtrn.ajePtrnActlNm_02.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpCd_03, resAjePtrn.ajePtrnIndTpCd_03.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpNm_03, resAjePtrn.ajePtrnIndTpNm_03.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlCd_03, resAjePtrn.ajePtrnActlCd_03.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlNm_03, resAjePtrn.ajePtrnActlNm_03.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlSrcCd, resAjePtrn.jrnlSrcCd.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlSrcNm, resAjePtrn.jrnlSrcNm.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlCatgCd, resAjePtrn.jrnlCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlCatgNm, resAjePtrn.jrnlCatgNm.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeLineIdxNum, resAjePtrn.ajeLineIdxNum.getValue());
        if (DR_CD.equals(drCrIndTpCd)) {
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeLineIdxDescTxt, resAjePtrn.drAjeLineIdxDescTxt.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeLineIdxDescTxt, resAjePtrn.crAjeLineIdxDescTxt.getValue());
        }

        // If line indicator type of AJE pattern is "99", set the
        // flag on.
        if (AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(resAjePtrn.ajeLineIndTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.dfrdRevGlStrgFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.dfrdRevGlStrgFlg, ZYPConstant.FLG_OFF_N);
        }

        this.ajeInvAcctDistSqNum++;
    }

    /**
     * @param invSlsCr
     * @param ajeLineIndTpCd
     * @return
     * @throws SQLException
     */
    private BigDecimal calcAjeInvAcctDistPct(ResultSet invSlsCr, String ajeLineIndTpCd) throws SQLException {

        BigDecimal invLineSplPct = BigDecimal.ZERO;
        BigDecimal slsRepCrPct = BigDecimal.ZERO;

        invLineSplPct = invSlsCr.getBigDecimal(INV_LINE_SPL_PCT);
        slsRepCrPct = invSlsCr.getBigDecimal(SLS_REP_CR_PCT);

        if (invLineSplPct == null || slsRepCrPct == null) {
            return null;
        }
        invLineSplPct = invLineSplPct.divide(new BigDecimal(100));
        slsRepCrPct = slsRepCrPct.divide(new BigDecimal(100));
        BigDecimal ajeInvAcctDistPct = invLineSplPct.multiply(slsRepCrPct);
        // START 2018/05/23 E.Kameishi [QC#21841,MOD]
        if(AJE_INV_ACCT_CLS.REVENUE.equals(ajeLineIndTpCd) || AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(ajeLineIndTpCd) || AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(ajeLineIndTpCd) || AJE_INV_ACCT_CLS.FREIGHT.equals(ajeLineIndTpCd)){
            ajeInvAcctDistPct = ajeInvAcctDistPct.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        } else {
            ajeInvAcctDistPct = new BigDecimal(100);
        }
        // END 2018/05/23 E.Kameishi [QC#21841,MOD]

        return ajeInvAcctDistPct;
    }

    /**
     * Set Amount Values
     * @param param
     * @param ajeInvAcctDist
     * @param invSlsCr
     * @param resAjePtrn
     * @param drCrTpCd
     * @return booolean
     * @throws SQLException
     * @throws JrnlCommonException 
     */
    private boolean setAjeInvAcctDistAmountValues(NFZC103001PMsg param, AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, ResultSet invSlsCr, NFAC000101PMsg resAjePtrn, String drCrTpCd, S21ApiMessageMap msgMap) throws SQLException, JrnlCommonException {

        String invTpCd = invSlsCr.getString(INV_TP_CD);

        boolean chkTaxFlg;
        boolean chkFrtTaxFlg;
        boolean chkFrtFlg;

        if (DR_CD.equals(drCrTpCd)) {
            chkTaxFlg = taxDrBreakFlg;
            chkFrtTaxFlg = frtTaxDrBreakFlg;
            chkFrtFlg = frtDrBreakFlg;
        } else {
            chkTaxFlg = taxCrBreakFlg;
            chkFrtTaxFlg = frtTaxCrBreakFlg;
            chkFrtFlg = frtCrBreakFlg;
        }

        // START 2018/06/12 E.Kameishi [QC#26627,MOD]
        // START 2018/05/23 E.Kameishi [QC#21841,MOD]
        // TAX by INV_LINE
        if (resAjePtrn.ajeLineIndTpCd.getValue().equals(AJE_INV_ACCT_CLS.TAX)) {
                if (!prevBolNum_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM)) 
                        || !prevLineNum_Tax.equals(invSlsCr.getString(INV_LINE_NUM)) || !chkTaxFlg) {
            //if (!prevLineNum_Tax.equals(invSlsCr.getString(INV_LINE_NUM)) || !chkTaxFlg) {

                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(INV_LINE_DEAL_TAX_AMT)));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(INV_LINE_FUNC_TAX_AMT)));

                // Freight TAX by INV_BOL
//                if (!prevBolNum_Frt_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM)) || !chkFrtTaxFlg) {
//                    if (!"0".equals(invSlsCr.getString(FRT_DEAL_TAX_AMT))) {
//                        setAjeInvAcctDistAmountValuesForFrtTax(resAjePtrn, invSlsCr, drCrTpCd, param, msgMap);
//
//                        if (CR_CD.equals(drCrTpCd)) {
//                            this.frtTaxCrBreakFlg = true;
//
//                            // turn off the other side of flag
//                            // when BOL breaks so that the value will be set to the other side too
//                            if (!prevBolNum_Frt_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM))) {
//                                prevBolNum_Frt_Tax = invSlsCr.getString(INV_BOL_LINE_NUM);
//                                this.frtTaxDrBreakFlg = false;
//                            }
//
//                        } else {
//                            this.frtTaxDrBreakFlg = true;
//
//                            // turn off the other side of flag
//                            // when BOL breaks so that the value will be set to the other side too
//                            if (!prevBolNum_Frt_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM))) {
//                                prevBolNum_Frt_Tax = invSlsCr.getString(INV_BOL_LINE_NUM);
//                                this.frtTaxCrBreakFlg = false;
//                            }
//                        }
//                    }
//                }

                if (CR_CD.equals(drCrTpCd)) {
                    this.taxCrBreakFlg = true;

                    // turn off the other side of flag
                    // when Line breaks so that the value will be set to the other side too
                    if (!prevBolNum_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM))
                            || !prevLineNum_Tax.equals(invSlsCr.getString(INV_LINE_NUM))) {
                    //if (!prevLineNum_Tax.equals(invSlsCr.getString(INV_LINE_NUM))) {
                        prevBolNum_Tax = invSlsCr.getString(INV_BOL_LINE_NUM);
                        prevLineNum_Tax = invSlsCr.getString(INV_LINE_NUM);
                        this.taxDrBreakFlg = false;
                    }

                } else {
                    this.taxDrBreakFlg = true;

                    // turn off the other side of flag
                    // when BOL breaks so that the value will be set to the other side too
                    if (!prevBolNum_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM))
                            || !prevLineNum_Tax.equals(invSlsCr.getString(INV_LINE_NUM))) {
                    //if (!prevLineNum_Tax.equals(invSlsCr.getString(INV_LINE_NUM))) {
                        prevBolNum_Tax = invSlsCr.getString(INV_BOL_LINE_NUM);
                        prevLineNum_Tax = invSlsCr.getString(INV_LINE_NUM);
                        this.taxCrBreakFlg = false;
                    }
                }
                // END 2018/06/12 E.Kameishi [QC#26627,MOD]
            } else {
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, BigDecimal.ZERO);
            }
            return true;
        // Freight by BOL
        } else if (resAjePtrn.ajeLineIndTpCd.getValue().equals(AJE_INV_ACCT_CLS.FREIGHT)) {

            //if (!prevBolNum_Frt.equals(invSlsCr.getString(INV_BOL_LINE_NUM)) || !chkFrtFlg) {
            if (this.coaMdseTPCdFrt.equals(invSlsCr.getString(COA_MDSE_TP_CD))) {
                //ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(SHIP_DEAL_FRT_AMT)));
                //ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(SHIP_FUNC_FRT_AMT)));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(DEAL_SLS_CR_AMT)));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(FUNC_SLS_CR_AMT)));
//                if (CR_CD.equals(drCrTpCd)) {
//
//                    frtCrBreakFlg = true;
//
//                    // turn off the other side of flag when BOL
//                    // breaks so that the value will be set to the
//                    // other side too
//                    if (!prevBolNum_Frt.equals(invSlsCr.getString(INV_BOL_LINE_NUM))) {
//                        prevBolNum_Frt = invSlsCr.getString(INV_BOL_LINE_NUM);
//                        frtDrBreakFlg = false;
//                    }
//
//                } else {
//                    frtDrBreakFlg = true;
//
//                    // turn off the other side of flag when BOL
//                    // breaks so that the value will be set to the
//                    // other side too
//                    if (!prevBolNum_Frt.equals(invSlsCr.getString(INV_BOL_LINE_NUM))) {
//                        prevBolNum_Frt = invSlsCr.getString(INV_BOL_LINE_NUM);
//                        frtCrBreakFlg = false;
//                    }
//                }
            } else {
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, BigDecimal.ZERO);
            }

            return true;
        } else {
            if (!this.coaMdseTPCdFrt.equals(invSlsCr.getString(COA_MDSE_TP_CD))) {
                // Sales
                // If line indicator type of AJE pattern is "99", jrnlDeal/FuncAmt set ZERO.
                if (AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(resAjePtrn.ajeLineIndTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, BigDecimal.ZERO);
                } else {
                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(DEAL_SLS_CR_AMT)));
                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(FUNC_SLS_CR_AMT)));
                }
            } else {
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, BigDecimal.ZERO);
            }
        }
        // END 2018/05/23 E.Kameishi [QC#21841,MOD]
        return true;
    }

    // Otherwise, tax / freight/ unearned revenue lines are also
    // overwritten.
     
    private boolean setValuesAtLink(String crDrTpCd, String valInTrx, String ajeLineIndTp) {
        
        return false;
    }

    /**
     * Set 9 Segments Values
     * @param param
     * @param jrnlEntry
     * @param invSlsCr
     * @param resAjePtrn
     * @param drCrTpCd
     * @return boolean
     * @throws SQLException
     */
    private boolean setAjeInvAcctDist9SegmentsValues(NFZC103001PMsg param, AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr, ResultSet invSlsCr, NFAC000101PMsg resAjePtrn, String drCrTpCd, S21ApiMessageMap msgMap)
            throws SQLException {

        String resultCd;
        String glblCmpyCd = param.glblCmpyCd.getValue();

        String coaCmpyCd;
        String coaBrCd;
        String coaCcCd;
        String coaAcctCd;
        String coaProdCd;
        String coaChCd;
        String coaAfflCd;
        String coaProjCd;
        String coaExtnCd;

        if (DR_CD.equals(drCrTpCd)) {
            coaCmpyCd = resAjePtrn.drAjeCoaCmpyCd.getValue();
            coaBrCd = resAjePtrn.drAjeCoaBrCd.getValue();
            coaCcCd = resAjePtrn.drAjeCoaCcCd.getValue();
            coaAcctCd = resAjePtrn.drAjeCoaAcctCd.getValue();
            coaProdCd = resAjePtrn.drAjeCoaProdCd.getValue();
            coaChCd = resAjePtrn.drAjeCoaChCd.getValue();
            coaAfflCd = resAjePtrn.drAjeCoaAfflCd.getValue();
            coaProjCd = resAjePtrn.drAjeCoaProjCd.getValue();
            coaExtnCd = resAjePtrn.drAjeCoaExtnCd.getValue();
        } else {
            coaCmpyCd = resAjePtrn.crAjeCoaCmpyCd.getValue();
            coaBrCd = resAjePtrn.crAjeCoaBrCd.getValue();
            coaCcCd = resAjePtrn.crAjeCoaCcCd.getValue();
            coaAcctCd = resAjePtrn.crAjeCoaAcctCd.getValue();
            coaProdCd = resAjePtrn.crAjeCoaProdCd.getValue();
            coaChCd = resAjePtrn.crAjeCoaChCd.getValue();
            coaAfflCd = resAjePtrn.crAjeCoaAfflCd.getValue();
            coaProjCd = resAjePtrn.crAjeCoaProjCd.getValue();
            coaExtnCd = resAjePtrn.crAjeCoaExtnCd.getValue();
        }

        boolean defaultSetFlg = false;
        JRNL_ENTRYTMsg tMsg = new JRNL_ENTRYTMsg();
        String crOrDr = "cr";
        if (DR_CD.equals(drCrTpCd)) {
            crOrDr = "dr";
        }
        for (int i = 0; i < 9; i++) {
            switch (i) {
                case 0:// Company COA_CMPY
                    // {
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_CMPY_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_CMPY_CD);
                    } else if (coaCmpyCd.substring(0, 1).equals("@") || coaCmpyCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.CMPY, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaCmpyCd");
                            
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                            
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaCmpyCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaCmpyCd, resultCd);
                    
                    break;

                case 1:// Branch COA_BR
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_BR_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_BR_CD);
                    } else if (coaBrCd.substring(0, 1).equals("@") || coaBrCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.BR, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaBrCd");
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                            
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaBrCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaBrCd, resultCd);
                    break;

                case 2:// Cost Center COA_CC
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_CC_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_CC_CD);
                    } else if (coaCcCd.substring(0, 1).equals("@") || coaCcCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.CC, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaCcCd");
                            
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaCcCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaCcCd, resultCd);
                    break;

                case 3:// Account COA_ACCT
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_ACCT_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_ACCT_CD);
                    } else if (coaAcctCd.substring(0, 1).equals("@") || coaAcctCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.ACCT, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaAcctCd");
                            
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                            
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaAcctCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaAcctCd, resultCd);
                    if (COA_ACCT_CD_ITEM_REV.equals(coaAcctCd)) {
                        defaultSetFlg = checkBsPlTp(glblCmpyCd, resultCd);
                    }
                    break;

                case 4:// Product COA_PROD
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_PROD_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_PROD_CD);
                    } else if (coaProdCd.substring(0, 1).equals("@") || coaProdCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.PROD, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaProdCd");
                            
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaProdCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaProdCd, resultCd);
                    break;

                case 5:// Channel COA_CH
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_CH_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_CH_CD);
                    } else if (coaChCd.substring(0, 1).equals("@") || coaChCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.CH, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaChCd");
                            
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaChCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaChCd, resultCd);
                    break;

                case 6:// Affiliate COA_AFFL
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_AFFL_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_AFFL_CD);
                    } else if (coaAfflCd.substring(0, 1).equals("@") || coaAfflCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.AFFL, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaAfflCd");
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaAfflCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaAfflCd, resultCd);
                    break;

                case 7:// Merchandise Type COA_PROJ
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_PROJ_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_PROJ_CD);
                    } else if (coaProjCd.substring(0, 1).equals("@") || coaProjCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.PROJ, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaProjCd");
                            
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaProjCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaProjCd, resultCd);
                    break;

                case 8:// Business Unit COA_EXTN
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_EXTN_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_EXTN_CD);
                    } else if (coaExtnCd.substring(0, 1).equals("@") || coaExtnCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.EXTN, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaExtnCd");
                            
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaExtnCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaExtnCd, resultCd);
                    break;

                default:
                    break;
            }
            if (defaultSetFlg) {
                break;
            }
        }
        if (defaultSetFlg) {
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaBrCd, defCoaBrCd);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaCcCd, defCoaCcCd);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaProdCd, defCoaProdCd);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaChCd, defCoaChCd);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaAfflCd, defCoaAfflCd);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaProjCd, defCoaProjCd);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaExtnCd, defCoaExtnCd);
        }

        boolean isError = false;

        // If value was not set, error
        if (!hasValue(ajeInvAcctDist.ajeCoaCmpyCd)) {
            isError = true;
            addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CMPY }, msgMap);

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CMPY })));
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaBrCd)) {
            isError = true;
            addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_BR }, msgMap);

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_BR })));
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaCcCd)) {
            isError = true;
            addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CC }, msgMap);

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CC })));
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaAcctCd)) {
            isError = true;
            addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_ACCT }, msgMap);

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_ACCT })));
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaProdCd)) {
            isError = true;
            addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_PROD }, msgMap);

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_PROD })));
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaChCd)) {
            isError = true;
            addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CH }, msgMap);

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CH })));
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaAfflCd)) {
            isError = true;
            addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_AFFL }, msgMap);

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_AFFL })));
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaProjCd)) {
            isError = true;
            addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_PROJ }, msgMap);

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_PROJ })));
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaExtnCd)) {
            isError = true;
            addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_EXTN }, msgMap);

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_EXTN })));
        }

        return !isError;
    }

    private String getErrorMsg(AJE_INV_ACCT_DIST_ERRTMsg tmsg, String newMsg) {
        String msg;
        if (hasValue(tmsg.invErrMsgTxt)) {
            if ((tmsg.invErrMsgTxt.getValue() + ", " + newMsg).length() > ERR_MSG_TXT_LEN) {
                // if length exceeds, not to add new message.
                msg = tmsg.invErrMsgTxt.getValue();
            } else {
                msg = tmsg.invErrMsgTxt.getValue() + ", " + newMsg;
            }

        } else {
            msg = newMsg;
        }
        return msg;
    }

    @SuppressWarnings("unused")
    private String getInvalidVal(AJE_INV_ACCT_DIST_ERRTMsg tmsg, String newVal) {
        String val;
        if (hasValue(tmsg.invldValTxt)) {
            if ((tmsg.invldValTxt.getValue() + ", " + newVal).length() > INVLD_VAL_TXT_LEN) {
                // if length exceeds, not to add new message.
                val = newVal;
            } else {
                val = tmsg.invldValTxt.getValue() + ", " + newVal;
            }
        } else {
            val = newVal;
        }
        return val;
    }

    @SuppressWarnings("unused")
    private String getInvalidVal2(AJE_INV_ACCT_DIST_ERRTMsg tmsg, String coaTpCd) {
        String val;
        String coaVal = "";

        if (COAID_COA_CMPY.equals(coaTpCd)) {
            coaVal = COAMSG_COA_CMPY;
        } else if (COAID_COA_BR.equals(coaTpCd)) {
            coaVal = COAMSG_COA_BR;
        } else if (COAID_COA_CC.equals(coaTpCd)) {
            coaVal = COAMSG_COA_CC;
        } else if (COAID_COA_ACCT.equals(coaTpCd)) {
            coaVal = COAMSG_COA_ACCT;
        } else if (COAID_COA_PROD.equals(coaTpCd)) {
            coaVal = COAMSG_COA_PROD;
        } else if (COAID_COA_CH.equals(coaTpCd)) {
            coaVal = COAMSG_COA_CH;
        } else if (COAID_COA_AFFL.equals(coaTpCd)) {
            coaVal = COAMSG_COA_AFFL;
        } else if (COAID_COA_PROJ.equals(coaTpCd)) {
            coaVal = COAMSG_COA_PROJ;
        } else if (COAID_COA_EXTN.equals(coaTpCd)) {
            coaVal = COAMSG_COA_EXTN;
        }

        if (hasValue(tmsg.invldValTxt)) {
            if ((tmsg.invldValTxt.getValue() + ", " + coaVal).length() > INVLD_VAL_TXT_LEN) {
                // if length exceeds, not to add new message.
                val = coaVal;
            } else {
                val = tmsg.invldValTxt.getValue() + ", " + coaVal;
            }
        } else {
            val = coaVal;
        }
        return val;
    }

    /**
     * @param invTpCd
     * @param amount
     * @return
     */
    private BigDecimal getAmount(String invTpCd, BigDecimal amount) {

        if (invTpCd.equals(INV_TP.CREDIT_MEMO)) {
            amount = amount.negate();
        }
        return amount;
    }

    /**
     * @param ajeInvAcctDist
     * @throws SQLException
     */
    private void createAjeInvAcctDist(AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, ResultSet invSlsCr) throws SQLException, NFACommonJrnlEntry.JrnlCommonException {
        // START 2018/05/23 E.Kameishi [QC#21841,MOD]
        if (ajeInvAcctDist != null) {
            if ((!(AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue())
                    || AJE_INV_ACCT_CLS.REVENUE.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue())
                    || AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue()))
                        && BigDecimal.ZERO.compareTo(ajeInvAcctDist.jrnlDealAmt.getValue()) == 0)
                || (AJE_INV_ACCT_CLS.REVENUE.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue()) 
                        && this.coaMdseTPCdFrt.equals(invSlsCr.getString(COA_MDSE_TP_CD)))) {
                // skip
                return;
            }
            // add to list
            listAcctDist.add(ajeInvAcctDist);
            return;
        }
        // END 2018/05/23 E.Kameishi [QC#21841,MOD]

        if (ajeInvAcctDist == null && listAcctDist.size() > 0) {
            AJE_INV_ACCT_DISTTMsg[] ajeInvAcctDistMsgs = new AJE_INV_ACCT_DISTTMsg[listAcctDist.size()];
            listAcctDist.toArray(ajeInvAcctDistMsgs);

            ajeInvAcctDistMsgs = setProcStsPerInv(ajeInvAcctDistMsgs);

            int retCnt = S21FastTBLAccessor.insert(ajeInvAcctDistMsgs);

            // if passed records' count and return count don't match, error
            if (retCnt != listAcctDist.size()) {
                throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
            }
            commitCount += listAcctDist.size();
            // initialize
            listAcctDist.clear();
        }

    }

    private AJE_INV_ACCT_DISTTMsg[] setProcStsPerInv(AJE_INV_ACCT_DISTTMsg[] tmsgs) {

        boolean isErr = false;
        AJE_INV_ACCT_DISTTMsg tmsg = new AJE_INV_ACCT_DISTTMsg();

        // if thre's any error
        if (listAcctDistErr.size() > 0) {
            isErr = true;
        }
        // If any of the record has error, entire invoice to be
        // logged as error.
        if (isErr) {
            for (int i = 0; i < tmsgs.length; i++) {

                tmsg = (AJE_INV_ACCT_DISTTMsg) tmsgs[i];

                tmsg.procStsCd.setValue(PROC_STS.ERROR);

                tmsgs[i] = tmsg;
            }
        }

        return tmsgs;
    }

    /**
     * @param ajeInvAcctDist
     * @throws SQLException
     */
    private void createAjeInvAcctDistErr(AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr) throws NFACommonJrnlEntry.JrnlCommonException {

        if (ajeInvAcctDistErr != null) {
            listAcctDistErr.add(ajeInvAcctDistErr);
        }

        // per 10000 lines
        if (ajeInvAcctDistErr == null && listAcctDistErr.size() > 0) {

            AJE_INV_ACCT_DIST_ERRTMsg[] tmsgArray = new AJE_INV_ACCT_DIST_ERRTMsg[listAcctDistErr.size()];
            listAcctDistErr.toArray(tmsgArray);

            int retCnt = S21FastTBLAccessor.insert(tmsgArray);

            // if passed records' count and return count don't
            // match, error
            if (retCnt != listAcctDistErr.size()) {
                throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
            }
            commitCount += listAcctDistErr.size();
            // initialize
            listAcctDistErr.clear();
        }

    }

    /**
     * @param param
     * @param ajeInvAcctDist
     * @param ajeInvAcctDistErr
     */
    private void glCodeCombinationCheck(NFZC103001PMsg param, AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr, S21ApiMessageMap msgMap, ResultSet invSlsCr) throws SQLException, NFACommonJrnlEntry.JrnlCommonException  {
        // START 2018/10/02 E.Kameishi [QC#28364,ADD]
        if ((!(AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue())
                || AJE_INV_ACCT_CLS.REVENUE.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue())
                || AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue()))
                    && BigDecimal.ZERO.compareTo(ajeInvAcctDist.jrnlDealAmt.getValue()) == 0)
            || (AJE_INV_ACCT_CLS.REVENUE.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue()) 
                    && this.coaMdseTPCdFrt.equals(invSlsCr.getString(COA_MDSE_TP_CD)))) {
            // skip
            return;
        }
        // END 2018/10/02 E.Kameishi [QC#28364,ADD]
        NFZC102001 api = new NFZC102001();
        NFZC102001PMsg apiMsg = new NFZC102001PMsg();

        apiMsg.glblCmpyCd.setValue(ajeInvAcctDist.glblCmpyCd.getValue());
        apiMsg.xxMstChkFlg.setValue(ZYPConstant.FLG_ON_Y);
        apiMsg.xxGlCmbnChkFlg.setValue(ZYPConstant.FLG_ON_Y);
        apiMsg.xxArcsApiChkFlg.setValue(ZYPConstant.FLG_OFF_N);

        apiMsg.coaCmpyCd.setValue(ajeInvAcctDist.ajeCoaCmpyCd.getValue());
        apiMsg.coaBrCd.setValue(ajeInvAcctDist.ajeCoaBrCd.getValue());
        apiMsg.coaCcCd.setValue(ajeInvAcctDist.ajeCoaCcCd.getValue());
        apiMsg.coaAcctCd.setValue(ajeInvAcctDist.ajeCoaAcctCd.getValue());
        apiMsg.coaProdCd.setValue(ajeInvAcctDist.ajeCoaProdCd.getValue());
        apiMsg.coaChCd.setValue(ajeInvAcctDist.ajeCoaChCd.getValue());
        apiMsg.coaAfflCd.setValue(ajeInvAcctDist.ajeCoaAfflCd.getValue());
        apiMsg.coaProjCd.setValue(ajeInvAcctDist.ajeCoaProjCd.getValue());
        apiMsg.coaExtnCd.setValue(ajeInvAcctDist.ajeCoaExtnCd.getValue());
        apiMsg.bizAppId.setValue("NFZC1030");

        api.execute(apiMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);
        String result = apiMsg.getReturnCode();

        int i = 0;
        String msgId;
        String msgTxt;
        for (; i < apiMsg.xxMsgIdList.getValidCount(); i++) {
            msgId = apiMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            msgTxt = apiMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue();

            addMsg(msgId, null, msgMap);

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(msgId, new String[] {msgTxt })));
        }
        this.errCnt += i;
        if (apiMsg.xxMsgIdList.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procStsCd, PROC_STS.ERROR);
        }
    }

    private boolean setAjeInvAcctDistAmountValuesForFrtTax(NFAC000101PMsg resAjePtrn, ResultSet rsNotJrnlized, String drCrTpCd, NFZC103001PMsg param, S21ApiMessageMap msgMap) throws SQLException, JrnlCommonException {

        String glDt = NFZC103001Common.getGlDt(param.glblCmpyCd.getValue(), param.procDt.getValue(), rsNotJrnlized.getString(ACCT_DT));

        AJE_INV_ACCT_DISTTMsg ajeInvAcctDist = new AJE_INV_ACCT_DISTTMsg();
        AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr = new AJE_INV_ACCT_DIST_ERRTMsg();

        // Set Common Data
        setAjeInvAcctDistCommonValues(param, ajeInvAcctDist, rsNotJrnlized, resAjePtrn, drCrTpCd, glDt);

        // Set Amount FREIGHT TAX Date
        setAjeInvAcctDistAmountValuesForFrtTax(ajeInvAcctDist, rsNotJrnlized);

        // Set 9 Segments Data
        if (!setAjeInvAcctDist9SegmentsValues(param, ajeInvAcctDist, ajeInvAcctDistErr, rsNotJrnlized, resAjePtrn, drCrTpCd, msgMap)) {
            // error
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procStsCd, PROC_STS.ERROR);
        } else {
            // Check 9 Segments
            glCodeCombinationCheck(param, ajeInvAcctDist, ajeInvAcctDistErr, msgMap, rsNotJrnlized);
        }

        // START 2018/05/23 E.Kameishi [QC#21841,MOD]
        createAjeInvAcctDist(ajeInvAcctDist, rsNotJrnlized);
        // END 2018/05/23 E.Kameishi [QC#21841,MOD]

        // If any error, create error record
        if (PROC_STS.ERROR.equals(ajeInvAcctDist.procStsCd.getValue())) {

            // set value other than error message
            BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_ERR_SQ);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.glblCmpyCd, ajeInvAcctDist.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistErrPk, pk);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistPk, ajeInvAcctDist.ajeInvAcctDistPk.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invNum, ajeInvAcctDist.invNum.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invBolLineNum, ajeInvAcctDist.invBolLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineNum, ajeInvAcctDist.invLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineSubNum, ajeInvAcctDist.invLineSubNum.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invTrxLineSubNum, ajeInvAcctDist.invLineSubTrxNum.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistSqNum, ajeInvAcctDist.ajeInvAcctDistSqNum.getValue());

            String coaConcat = nvl(ajeInvAcctDist.ajeCoaCmpyCd.getValue()).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaBrCd.getValue())).concat(",").concat(
                    nvl(ajeInvAcctDist.ajeCoaCcCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaAcctCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaProdCd.getValue()))
                    .concat(",").concat(nvl(ajeInvAcctDist.ajeCoaChCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaAfflCd.getValue())).concat(",").concat(
                            nvl(ajeInvAcctDist.ajeCoaProjCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaExtnCd.getValue()));

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invldValTxt, coaConcat);

            createAjeInvAcctDistErr(ajeInvAcctDistErr);
        }

        return true;
    }

    /**
     * Set Amount Freight TAX by INV_BOL
     * @param ajeInvAcctDist
     * @param invSlsCr
     * @return booolean
     * @throws SQLException
     * @throws JrnlCommonException
     */
    private boolean setAjeInvAcctDistAmountValuesForFrtTax(AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, ResultSet invSlsCr) throws SQLException, JrnlCommonException {

        String invTpCd = invSlsCr.getString(INV_TP_CD);

        // Freight TAX by INV_BOL
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(FRT_DEAL_TAX_AMT)));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(FRT_FUNC_TAX_AMT)));

        return true;
    }
    /**
     * Update index Number
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    private boolean updateInvoice_ACCT_DISTWithIdxNum(NFZC103001PMsg param) {

        // ----------------------------------------------
        // Update AJE_LINE_IDX_NUM in AJE_INV_ACCT_DIST
        // ----------------------------------------------
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("invNum", param.invNum.getValue());
        List<Map<String, Object>> ajeInvAcctDistList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getAjeInvAcctDistList", ssmParam);

        int ajeLineIdxNum = INIT_AJE_LINE_IDX_NUM;
        BigDecimal dsInvSlsCrPk = BigDecimal.ZERO;
        String preDrCrCd = "";

        if (ajeInvAcctDistList == null) {
            return false;
        }

        for (Map<String, Object> ajeInvAcctDistListData : ajeInvAcctDistList) {

            BigDecimal ajeInvAcctDistPk = (BigDecimal) ajeInvAcctDistListData.get(AJE_INV_ACCT_DIST_PK);
            String drCrTpCd = (String) ajeInvAcctDistListData.get(DR_CR_TP_CD);

            if (preDrCrCd.equals(drCrTpCd)) {
                if (dsInvSlsCrPk.compareTo((BigDecimal) ajeInvAcctDistListData.get(DS_INV_SLS_CR_PK)) == 0) {
                    ajeLineIdxNum++;
                } else {
                    ajeLineIdxNum = INIT_AJE_LINE_IDX_NUM;
                }
            } else {
                ajeLineIdxNum = INIT_AJE_LINE_IDX_NUM;
            }
            dsInvSlsCrPk = (BigDecimal) ajeInvAcctDistListData.get(DS_INV_SLS_CR_PK);
            preDrCrCd = drCrTpCd;

            AJE_INV_ACCT_DISTTMsg inMsg = new AJE_INV_ACCT_DISTTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.ajeInvAcctDistPk, ajeInvAcctDistPk);
            AJE_INV_ACCT_DISTTMsg updMsg = (AJE_INV_ACCT_DISTTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                errMsgId = NWAM0794E;
                return false;
            }

            ZYPEZDItemValueSetter.setValue(updMsg.ajeLineIdxNum, String.format("%02d", ajeLineIdxNum));
            EZDTBLAccessor.update(updMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                errMsgId = NFDM0003E;
                return false;
            }
        }

        return true;
    }
    
    
    // END 2018/03/12 E.Kameishi [QC#23689,MOD]
    // START 2018/03/12 E.Kameishi [QC#23689,DEL]
//    /**
//     * AjeInvAcctDistHandler
//     * @author q02473
//     */
//    private final class AjeInvAcctDistHandler extends S21SsmBooleanResultSetHandlerSupport {
//
//        /** */
//        private List<Map> coaSegLkupList;
//
//        /** */
//        private NFZC103001PMsg param;
//
//        /** */
//        private S21ApiMessageMap msgMap;
//
//        /** */
//        private boolean taxCrBreakFlg = false;
//
//        /** */
//        private boolean taxDrBreakFlg = false;
//
//        /** */
//        private boolean frtTaxCrBreakFlg = false; // ADD 2016/12/08 [QC#16174]
//
//        /** */
//        private boolean frtTaxDrBreakFlg = false; // ADD 2016/12/08 [QC#16174]
//
//        /** */
//        private boolean frtCrBreakFlg = false;
//
//        /** */
//        private boolean frtDrBreakFlg = false;
//
//        /** */
//        private int ajeInvAcctDistSqNum = 0;
//
//        /** */
//        private int errCnt = 0;
//
//        private AjeInvAcctDistHandler(S21ApiMessageMap msgMap) {
//
//            this.param = (NFZC103001PMsg) msgMap.getPmsg();
//            this.msgMap = msgMap;
//
//            // COA Segment Lookup List
//            this.coaSegLkupList = (List<Map>) commonJrnlEntry.getCoaSegLkupAll(param.glblCmpyCd.getValue());
//
//            this.taxCrBreakFlg = false;
//            this.taxDrBreakFlg = false;
//            this.frtTaxCrBreakFlg = false; // ADD 2016/12/08 [QC#16174]
//            this.frtTaxDrBreakFlg = false; // ADD 2016/12/08 [QC#16174]
//            this.frtCrBreakFlg = false;
//            this.frtDrBreakFlg = false;
//        }
//
//        @SuppressWarnings("unchecked")
//        protected Boolean doProcessQueryResult(ResultSet rsNotJrnlized) {
//            
//            try {
//                
//                //---- start 2016/06/21 QC#10541 If there's no result, create data in INV_ERR. 
//                if (rsNotJrnlized.first() == false) {
//                    
//                    AJE_INV_ACCT_DIST_ERRTMsg invErr = new AJE_INV_ACCT_DIST_ERRTMsg();
//
//                    BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_ERR_SQ);
//                    ZYPEZDItemValueSetter.setValue(invErr.glblCmpyCd, param.glblCmpyCd.getValue());
//                    ZYPEZDItemValueSetter.setValue(invErr.ajeInvAcctDistErrPk, pk);
//                    ZYPEZDItemValueSetter.setValue(invErr.invNum, param.invNum.getValue());
//                    ZYPEZDItemValueSetter.setValue(invErr.invLineNum, "00001");
//                    ZYPEZDItemValueSetter.setValue(invErr.invErrMsgTxt, S21MessageFunc.clspGetMessage(NO_SLS_CR_ERR, new String[] {"Sales Credit Information"}));
//
//                    S21ApiTBLAccessor.insert(invErr);
//                    
//                    errMsgId = NWAM0794E;
//                    return false;
//                }
//                //---- end 2016/06/21
//
//                //---- start add 2016/06/24 QC#10541-2
//                // reset the cursor
//                rsNotJrnlized.beforeFirst();
//                //---- end 2016/06/24
//                
//                while (rsNotJrnlized.next()) {
//                    // AJE Pattern to be determined by pattern
//                    // indicator
//                    String ajeId = commonJrnlEntry.generateAjeId(rsNotJrnlized.getString(SYS_SRC_CD), rsNotJrnlized.getString(TRX_CD), rsNotJrnlized.getString(TRX_RSN_CD));
//
//                    // Get AJE Pattern List for Debit
//                    List<Map> resAjePtrnList = (List<Map>) commonJrnlEntry.getAjePtrn2(param.glblCmpyCd.getValue(), rsNotJrnlized.getString(SYS_SRC_CD), rsNotJrnlized.getString(TRX_CD), rsNotJrnlized.getString(TRX_RSN_CD), DR_CD);
//
//                    // refine AJE Pattern by pattern indicator codes
//                    List<NFAC000101PMsg> ajePtrnListByAjeIdAndIndTp = commonJrnlEntry.getAJEPtrnByAjeIdAndIndTp(ajeId, rsNotJrnlized, resAjePtrnList);
//
//                    // if pattern could not be obtained go to next
//                    // record. (No need to create one more error
//                    // record.)
//                    if (!doProcessPerPattern(ajePtrnListByAjeIdAndIndTp, rsNotJrnlized, DR_CD)) {
//                        continue;
//                    }
//
//                    // Get AJE Pattern List for Credit
//                    resAjePtrnList = (List<Map>) commonJrnlEntry.getAjePtrn2(param.glblCmpyCd.getValue(), rsNotJrnlized.getString(SYS_SRC_CD), rsNotJrnlized.getString(TRX_CD), rsNotJrnlized.getString(TRX_RSN_CD), CR_CD);
//
//                    // refine AJE Pattern by pattern indicator codes
//                    ajePtrnListByAjeIdAndIndTp = commonJrnlEntry.getAJEPtrnByAjeIdAndIndTp(ajeId, rsNotJrnlized, resAjePtrnList);
//
//                    doProcessPerPattern(ajePtrnListByAjeIdAndIndTp, rsNotJrnlized, CR_CD);
//
//                }
//
//                createAjeInvAcctDist(null);
//
//                createAjeInvAcctDistErr(null);
//
//                // START 2016/12/26 T.Murai [QC#16818,ADD]
//                // Update AJE_LINE_IDX_NUM in AJE_INV_ACCT_DIST
//                if(!updateInvoice_ACCT_DISTWithIdxNum()) {
//                    return false;
//                }
//                // END   2016/12/26 T.Murai [QC#16818,ADD]
//
//            } catch (NFACommonJrnlEntry.JrnlCommonException ex) {
//                errMsgId = ZZBM0074E;
//                return Boolean.FALSE;
//                // added 2016/01/26
//            } catch (SQLException ex) {
//                errMsgId = NFDM0003E;
//                errMsgTxt = ex.getMessage();
//                return Boolean.FALSE;
//            }
//            // end 2016/01/26
//
//            return Boolean.TRUE;
//        }
//
//        private boolean doProcessPerPattern(List<NFAC000101PMsg> ajePtrnListByAjeIdAndIndTp, ResultSet rsNotJrnlized, String drCrTpCd) throws SQLException, NFACommonJrnlEntry.JrnlCommonException {
//
//            // If AJE pattern could not be obtained, create error data
//            // only.
//            if (ajePtrnListByAjeIdAndIndTp.size() == 0) {
//
//                AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr = new AJE_INV_ACCT_DIST_ERRTMsg();
//
//                BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_ERR_SQ);
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.glblCmpyCd, rsNotJrnlized.getString(GLBL_CMPY_CD));
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistErrPk, pk);
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invNum, rsNotJrnlized.getString(INV_NUM));
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invBolLineNum, rsNotJrnlized.getString(INV_BOL_LINE_NUM));
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineNum, rsNotJrnlized.getString(INV_LINE_NUM));
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineSubNum, rsNotJrnlized.getString(INV_LINE_SUB_NUM));
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invTrxLineSubNum, rsNotJrnlized.getString(INV_TRX_LINE_SUB_NUM));
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, S21MessageFunc.clspGetMessage(NO_AJE_PTRN_ERR));
//
//                createAjeInvAcctDistErr(ajeInvAcctDistErr);
//
//                // ---- start add 2016/01/26 log error in output
//                // parameter
//                addMsg(NO_AJE_PTRN_ERR, null, msgMap);
//                // ---- end 2016/01/26
//
//                return false;
//            }
//
//            // START 2016/09/05 K.Kojima [QC#11049,ADD]
//            String glDt = NFZC103001Common.getGlDt(param.glblCmpyCd.getValue(), param.procDt.getValue(), rsNotJrnlized.getString(ACCT_DT));
//            // END 2016/09/05 K.Kojima [QC#11049,ADD]
//
//            // for (Map<String, String>resAjePtrn : resAjePtrnList) {
//            for (NFAC000101PMsg resAjePtrn : ajePtrnListByAjeIdAndIndTp) {
//
//                AJE_INV_ACCT_DISTTMsg ajeInvAcctDist = new AJE_INV_ACCT_DISTTMsg();
//                AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr = new AJE_INV_ACCT_DIST_ERRTMsg();
//
//                // Set Common Data
//                // START 2016/09/05 K.Kojima [QC#11049,MOD]
//                // setAjeInvAcctDistCommonValues(param, ajeInvAcctDist, rsNotJrnlized, resAjePtrn, drCrTpCd);
//                setAjeInvAcctDistCommonValues(param, ajeInvAcctDist, rsNotJrnlized, resAjePtrn, drCrTpCd, glDt);
//                // END 2016/09/05 K.Kojima [QC#11049,MOD]
//
//                // Set Amount Data
//                setAjeInvAcctDistAmountValues(param, ajeInvAcctDist, rsNotJrnlized, resAjePtrn, drCrTpCd);
//
//                // Set 9 Segments Data
//                if (!setAjeInvAcctDist9SegmentsValues(param, ajeInvAcctDist, ajeInvAcctDistErr, rsNotJrnlized, resAjePtrn, drCrTpCd)) {
//                    // error
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procStsCd, PROC_STS.ERROR);
//                } else {
//                    // Check 9 Segments
//                    glCodeCombinationCheck(param, ajeInvAcctDist, ajeInvAcctDistErr);
//                }
//
//                createAjeInvAcctDist(ajeInvAcctDist);
//
//                // If any error, create error record
//                if (PROC_STS.ERROR.equals(ajeInvAcctDist.procStsCd.getValue())) {
//
//                    // set value other than error message
//                    BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_ERR_SQ);
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.glblCmpyCd, ajeInvAcctDist.glblCmpyCd.getValue());
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistErrPk, pk);
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistPk, ajeInvAcctDist.ajeInvAcctDistPk.getValue());
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invNum, ajeInvAcctDist.invNum.getValue());
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invBolLineNum, ajeInvAcctDist.invBolLineNum.getValue());
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineNum, ajeInvAcctDist.invLineNum.getValue());
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineSubNum, ajeInvAcctDist.invLineSubNum.getValue());
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invTrxLineSubNum, ajeInvAcctDist.invLineSubTrxNum.getValue());
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistSqNum, ajeInvAcctDist.ajeInvAcctDistSqNum.getValue());
//
//                    String coaConcat = nvl(ajeInvAcctDist.ajeCoaCmpyCd.getValue()).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaBrCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaCcCd.getValue())).concat(",").concat(
//                            nvl(ajeInvAcctDist.ajeCoaAcctCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaProdCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaChCd.getValue())).concat(",").concat(
//                            nvl(ajeInvAcctDist.ajeCoaAfflCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaProjCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaExtnCd.getValue()));
//
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invldValTxt, coaConcat);
//
//                    createAjeInvAcctDistErr(ajeInvAcctDistErr);
//                }
//            }
//
//            return true;
//        }
//
//        private String nvl(String val) {
//            if (hasValue(val)) {
//                return val;
//            } else {
//                return "";
//            }
//        }
//
//        /**
//         * Set Common Values
//         * @param param NFZC103001PMsg
//         * @param jrnlEntry JRNL_ENTRYTMsg
//         * @param invSlsCr ResultSet
//         * @param resAjePtrn Map
//         * @param drCrIndTpCd
//         * @param glDt
//         * @throws SQLException
//         */
//        private void setAjeInvAcctDistCommonValues(NFZC103001PMsg param, AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, ResultSet invSlsCr, NFAC000101PMsg resAjePtrn, String drCrIndTpCd, String glDt) throws SQLException {
//
//            BigDecimal ajeInvAcctDistSeqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_SQ);
//
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.glblCmpyCd, param.glblCmpyCd.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctDistPk, ajeInvAcctDistSeqNum);
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invNum, invSlsCr.getString(INV_NUM));
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invBolLineNum, invSlsCr.getString(INV_BOL_LINE_NUM).toString());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invLineNum, invSlsCr.getString(INV_LINE_NUM).toString());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invLineSubNum, invSlsCr.getString(INV_LINE_SUB_NUM).toString());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invLineSubTrxNum, invSlsCr.getString(INV_TRX_LINE_SUB_NUM).toString());
//            // START 2016/06/07 S.Fujita [QC#9515,MOD]
////            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctDistSqNum, String.format("%03d", this.ajeInvAcctDistSqNum));
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctDistSqNum, String.format("%06d", this.ajeInvAcctDistSqNum));
//            // END   2016/06/07 S.Fujita [QC#9515,MOD]
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procDt, param.procDt.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctClsCd, resAjePtrn.ajeLineIndTpCd.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.drCrTpCd, drCrIndTpCd);
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.dealCcyCd, invSlsCr.getString(DEAL_CCY_CD));
//            // START 2016/05/13 S.Fujita [QC#6959,MOD]
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.funcCcyCd, invSlsCr.getString(DEAL_CCY_CD));
//            // END 2016/05/13 S.Fujita [QC#6959,MOD]
//            // START 2016/09/05 S.Fujita [QC#10156,MOD]
////            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctDistPct, calcAjeInvAcctDistPct(invSlsCr));
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctDistPct, calcAjeInvAcctDistPct(invSlsCr, resAjePtrn.ajeLineIndTpCd.getValue()));
//            // END   2016/09/05 S.Fujita [QC#10156,MOD]
//            // START 2016/09/05 K.Kojima [QC#11049,MOD]
//            // ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.glDt,
//            // invSlsCr.getString(ACCT_DT));
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.glDt, glDt);
//            // END 2016/09/05 K.Kojima [QC#11049,MOD]
//            // ---- start mod 2016/01/29 JRNL_CRAT_DT should not be
//            // set
//            // ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlCratDt,
//            // param.procDt.getValue());
//            // ---- end 2016/01/29
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.dsInvSlsCrPk, invSlsCr.getBigDecimal(DS_INV_SLS_CR_PK));
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeOmIntfcPk, BigDecimal.ZERO);
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procStsCd, PROC_STS.COMPLEATED);
//
//            // newly added fields
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.sysSrcCd, invSlsCr.getString(SYS_SRC_CD));
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.sysSrcNm, invSlsCr.getString(SYS_SRC_NM));
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.trxCd, resAjePtrn.trxCd.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.trxNm, resAjePtrn.trxNm.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.trxRsnCd, resAjePtrn.trxRsnCd.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.trxRsnNm, resAjePtrn.trxRsnNm.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpCd_01, resAjePtrn.ajePtrnIndTpCd_01.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpNm_01, resAjePtrn.ajePtrnIndTpNm_01.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlCd_01, resAjePtrn.ajePtrnActlCd_01.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlNm_01, resAjePtrn.ajePtrnActlNm_01.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpCd_02, resAjePtrn.ajePtrnIndTpCd_02.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpNm_02, resAjePtrn.ajePtrnIndTpNm_02.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlCd_02, resAjePtrn.ajePtrnActlCd_02.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlNm_02, resAjePtrn.ajePtrnActlNm_02.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpCd_03, resAjePtrn.ajePtrnIndTpCd_03.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpNm_03, resAjePtrn.ajePtrnIndTpNm_03.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlCd_03, resAjePtrn.ajePtrnActlCd_03.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlNm_03, resAjePtrn.ajePtrnActlNm_03.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlSrcCd, resAjePtrn.jrnlSrcCd.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlSrcNm, resAjePtrn.jrnlSrcNm.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlCatgCd, resAjePtrn.jrnlCatgCd.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlCatgNm, resAjePtrn.jrnlCatgNm.getValue());
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeLineIdxNum, resAjePtrn.ajeLineIdxNum.getValue());
//            if (DR_CD.equals(drCrIndTpCd)) {
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeLineIdxDescTxt, resAjePtrn.drAjeLineIdxDescTxt.getValue());
//            } else {
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeLineIdxDescTxt, resAjePtrn.crAjeLineIdxDescTxt.getValue());
//            }
//
//            // If line indicator type of AJE pattern is "99", set the
//            // flag on.
//            if (AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(resAjePtrn.ajeLineIndTpCd.getValue())) {
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.dfrdRevGlStrgFlg, ZYPConstant.FLG_ON_Y);
//            } else {
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.dfrdRevGlStrgFlg, ZYPConstant.FLG_OFF_N);
//            }
//
//            this.ajeInvAcctDistSqNum++;
//        }
//
//        /**
//         * @param invSlsCr
//         * @param ajeLineIndTpCd
//         * @return
//         * @throws SQLException
//         */
//        private BigDecimal calcAjeInvAcctDistPct(ResultSet invSlsCr, String ajeLineIndTpCd) throws SQLException {
//
//            BigDecimal invLineSplPct = BigDecimal.ZERO;
//            BigDecimal slsRepCrPct = BigDecimal.ZERO;
//
//            invLineSplPct = invSlsCr.getBigDecimal(INV_LINE_SPL_PCT);
//            slsRepCrPct = invSlsCr.getBigDecimal(SLS_REP_CR_PCT);
//
//            if (invLineSplPct == null || slsRepCrPct == null) {
//                return null;
//            }
//            invLineSplPct = invLineSplPct.divide(new BigDecimal(100));
//            slsRepCrPct = slsRepCrPct.divide(new BigDecimal(100));
//            BigDecimal ajeInvAcctDistPct = invLineSplPct.multiply(slsRepCrPct);
//
//            // START 2016/09/05 S.Fujita [QC#10156,MOD]
//            if(AJE_INV_ACCT_CLS.REVENUE.equals(ajeLineIndTpCd) || AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(ajeLineIndTpCd) || AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(ajeLineIndTpCd)){
//                // 2016/02/02 add multiply 100
//                ajeInvAcctDistPct = ajeInvAcctDistPct.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
//            } else {
//                ajeInvAcctDistPct = new BigDecimal(100);
//            }
//            // END   2016/09/05 S.Fujita [QC#10156,MOD]
//
//            return ajeInvAcctDistPct;
//        }
//
//        /**
//         * Set Amount Values
//         * @param param
//         * @param ajeInvAcctDist
//         * @param invSlsCr
//         * @param resAjePtrn
//         * @param drCrTpCd
//         * @return booolean
//         * @throws SQLException
//         * @throws JrnlCommonException 
//         */
//        private boolean setAjeInvAcctDistAmountValues(NFZC103001PMsg param, AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, ResultSet invSlsCr, NFAC000101PMsg resAjePtrn, String drCrTpCd) throws SQLException, JrnlCommonException {
//
//            String invTpCd = invSlsCr.getString(INV_TP_CD);
//
//            boolean chkTaxFlg;
//            boolean chkFrtTaxFlg; // ADD 2016/12/08 [QC#16174]
//            boolean chkFrtFlg;
//
//            if (DR_CD.equals(drCrTpCd)) {
//                chkTaxFlg = taxDrBreakFlg;
//                chkFrtTaxFlg = frtTaxDrBreakFlg; // ADD 2016/12/08 [QC#16174]
//                chkFrtFlg = frtDrBreakFlg;
//            } else {
//                chkTaxFlg = taxCrBreakFlg;
//                chkFrtTaxFlg = frtTaxCrBreakFlg; // ADD 2016/12/08 [QC#16174]
//                chkFrtFlg = frtCrBreakFlg;
//            }
//
//            // START 2016/12/07 [QC#16174, MOD]
////            // TAX by INV_BOL
////            if (resAjePtrn.ajeLineIndTpCd.getValue().equals(AJE_INV_ACCT_CLS.TAX)) {
////
////                if (!prevBolNum_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM)) || !chkTaxFlg) {
////
////                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(TOT_BOL_DEAL_TAX_AMT)));
////                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(TOT_BOL_FUNC_TAX_AMT)));
////                    if (CR_CD.equals(drCrTpCd)) {
////                        this.taxCrBreakFlg = true;
////
////                        // turn off the other side of flag when BOL
////                        // breaks so that the value will be set to the
////                        // other side too
////                        if (!prevBolNum_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM))) {
////                            prevBolNum_Tax = invSlsCr.getString(INV_BOL_LINE_NUM);
////                            this.taxDrBreakFlg = false;
////                        }
////
////                    } else {
////                        this.taxDrBreakFlg = true;
////
////                        // turn off the other side of flag when BOL
////                        // breaks so that the value will be set to the
////                        // other side too
////                        if (!prevBolNum_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM))) {
////                            prevBolNum_Tax = invSlsCr.getString(INV_BOL_LINE_NUM);
////                            this.taxCrBreakFlg = false;
////                        }
////                    }
////                } else {
////                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, BigDecimal.ZERO);
////                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, BigDecimal.ZERO);
////                }
////                return true;
//
//                // TAX by INV_LINE
//                if (resAjePtrn.ajeLineIndTpCd.getValue().equals(AJE_INV_ACCT_CLS.TAX)) {
//
//                    if (!prevBolNum_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM)) 
//                            || !prevLineNum_Tax.equals(invSlsCr.getString(INV_LINE_NUM)) || !chkTaxFlg) {
//
//                        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(INV_LINE_DEAL_TAX_AMT)));
//                        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(INV_LINE_FUNC_TAX_AMT)));
//
//                        // Freight TAX by INV_BOL
//                        if (!prevBolNum_Frt_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM)) || !chkFrtTaxFlg) {
//                            if (!"0".equals(invSlsCr.getString(FRT_DEAL_TAX_AMT))) {
//                                setAjeInvAcctDistAmountValuesForFrtTax(resAjePtrn, invSlsCr, drCrTpCd);
//
//                                if (CR_CD.equals(drCrTpCd)) {
//                                    this.frtTaxCrBreakFlg = true;
//
//                                    // turn off the other side of flag
//                                    // when BOL breaks so that the value will be set to the other side too
//                                    if (!prevBolNum_Frt_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM))) {
//                                        prevBolNum_Frt_Tax = invSlsCr.getString(INV_BOL_LINE_NUM);
//                                        this.frtTaxDrBreakFlg = false;
//                                    }
//
//                                } else {
//                                    this.frtTaxDrBreakFlg = true;
//
//                                    // turn off the other side of flag
//                                    // when BOL breaks so that the value will be set to the other side too
//                                    if (!prevBolNum_Frt_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM))) {
//                                        prevBolNum_Frt_Tax = invSlsCr.getString(INV_BOL_LINE_NUM);
//                                        this.frtTaxCrBreakFlg = false;
//                                    }
//                                }
//                            }
//                        }
//
//                        if (CR_CD.equals(drCrTpCd)) {
//                            this.taxCrBreakFlg = true;
//
//                            // turn off the other side of flag
//                            // when Line breaks so that the value will be set to the other side too
//                            if (!prevBolNum_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM))
//                                    || !prevLineNum_Tax.equals(invSlsCr.getString(INV_LINE_NUM))) {
//                                prevBolNum_Tax = invSlsCr.getString(INV_BOL_LINE_NUM);
//                                prevLineNum_Tax = invSlsCr.getString(INV_LINE_NUM);
//                                this.taxDrBreakFlg = false;
//                            }
//
//                        } else {
//                            this.taxDrBreakFlg = true;
//
//                            // turn off the other side of flag
//                            // when BOL breaks so that the value will be set to the other side too
//                            if (!prevBolNum_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM))
//                                    || !prevLineNum_Tax.equals(invSlsCr.getString(INV_LINE_NUM))) {
//                                prevBolNum_Tax = invSlsCr.getString(INV_BOL_LINE_NUM);
//                                prevLineNum_Tax = invSlsCr.getString(INV_LINE_NUM);
//                                this.taxCrBreakFlg = false;
//                            }
//                        }
//                    } else {
//                        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, BigDecimal.ZERO);
//                        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, BigDecimal.ZERO);
//                    }
//                    return true;
//                // END 2016/12/07 [QC#16174, MOD]
//                // Freight by BOL
//            } else if (resAjePtrn.ajeLineIndTpCd.getValue().equals(AJE_INV_ACCT_CLS.FREIGHT)) {
//
//                if (!prevBolNum_Frt.equals(invSlsCr.getString(INV_BOL_LINE_NUM)) || !chkFrtFlg) {
//
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(SHIP_DEAL_FRT_AMT)));
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(SHIP_FUNC_FRT_AMT)));
//                    if (CR_CD.equals(drCrTpCd)) {
//
//                        frtCrBreakFlg = true;
//
//                        // turn off the other side of flag when BOL
//                        // breaks so that the value will be set to the
//                        // other side too
//                        if (!prevBolNum_Frt.equals(invSlsCr.getString(INV_BOL_LINE_NUM))) {
//                            prevBolNum_Frt = invSlsCr.getString(INV_BOL_LINE_NUM);
//                            frtDrBreakFlg = false;
//                        }
//
//                    } else {
//                        frtDrBreakFlg = true;
//
//                        // turn off the other side of flag when BOL
//                        // breaks so that the value will be set to the
//                        // other side too
//                        if (!prevBolNum_Frt.equals(invSlsCr.getString(INV_BOL_LINE_NUM))) {
//                            prevBolNum_Frt = invSlsCr.getString(INV_BOL_LINE_NUM);
//                            frtCrBreakFlg = false;
//                        }
//                    }
//                } else {
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, BigDecimal.ZERO);
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, BigDecimal.ZERO);
//                }
//
//                return true;
//            } else {
//                // Sales
//                // if
//                // (resAjePtrn.ajeLineIndTpCd.getValue().equals(AJE_LINE_IND_TP_CD_REVENUE))
//                // {
//
//                // START 2016/09/26 S.Fujita [QC#13362,MOD] 
////                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(DEAL_SLS_CR_AMT)));
////                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(FUNC_SLS_CR_AMT)));
//                // If line indicator type of AJE pattern is "99", jrnlDeal/FuncAmt set ZERO.
//                if (AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(resAjePtrn.ajeLineIndTpCd.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, BigDecimal.ZERO);
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, BigDecimal.ZERO);
//                } else {
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(DEAL_SLS_CR_AMT)));
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(FUNC_SLS_CR_AMT)));
//                }
//                // END   2016/09/26 S.Fujita [QC#13362,MOD]
//
//                // }
//            }
//            return true;
//        }
//
//        // --- start add 2016/02/16 check by AJE Line Indicator Type.
//        // Otherwise, tax / freight/ unearned revenue lines are also
//        // overwritten.
//         
//        private boolean setValuesAtLink(String crDrTpCd, String valInTrx, String ajeLineIndTp) {
//
//            /* start QC#14589 Changed to follow AJE pattern and not overwrite by the values in DS_INV_SLS_CR
//            if (DR_CD.equals(crDrTpCd)) {
//                return false;
//            }
//
//            if (ajeLineIndTpLink.contains(ajeLineIndTp) && hasValue(valInTrx)) {
//                return true;
//            }
//
//            return false;
//             
//             */
//            
//            return false;
//        }
//
//        /**
//         * Set 9 Segments Values
//         * @param param
//         * @param jrnlEntry
//         * @param invSlsCr
//         * @param resAjePtrn
//         * @param drCrTpCd
//         * @return boolean
//         * @throws SQLException
//         */
//        private boolean setAjeInvAcctDist9SegmentsValues(NFZC103001PMsg param, AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr, ResultSet invSlsCr, NFAC000101PMsg resAjePtrn, String drCrTpCd)
//                throws SQLException {
//
//            String resultCd;
//            String glblCmpyCd = param.glblCmpyCd.getValue();
//
//            String coaCmpyCd;
//            String coaBrCd;
//            String coaCcCd;
//            String coaAcctCd;
//            String coaProdCd;
//            String coaChCd;
//            String coaAfflCd;
//            String coaProjCd;
//            String coaExtnCd;
//
//            if (DR_CD.equals(drCrTpCd)) {
//                coaCmpyCd = resAjePtrn.drAjeCoaCmpyCd.getValue();
//                coaBrCd = resAjePtrn.drAjeCoaBrCd.getValue();
//                coaCcCd = resAjePtrn.drAjeCoaCcCd.getValue();
//                coaAcctCd = resAjePtrn.drAjeCoaAcctCd.getValue();
//                coaProdCd = resAjePtrn.drAjeCoaProdCd.getValue();
//                coaChCd = resAjePtrn.drAjeCoaChCd.getValue();
//                coaAfflCd = resAjePtrn.drAjeCoaAfflCd.getValue();
//                coaProjCd = resAjePtrn.drAjeCoaProjCd.getValue();
//                coaExtnCd = resAjePtrn.drAjeCoaExtnCd.getValue();
//            } else {
//                coaCmpyCd = resAjePtrn.crAjeCoaCmpyCd.getValue();
//                coaBrCd = resAjePtrn.crAjeCoaBrCd.getValue();
//                coaCcCd = resAjePtrn.crAjeCoaCcCd.getValue();
//                coaAcctCd = resAjePtrn.crAjeCoaAcctCd.getValue();
//                coaProdCd = resAjePtrn.crAjeCoaProdCd.getValue();
//                coaChCd = resAjePtrn.crAjeCoaChCd.getValue();
//                coaAfflCd = resAjePtrn.crAjeCoaAfflCd.getValue();
//                coaProjCd = resAjePtrn.crAjeCoaProjCd.getValue();
//                coaExtnCd = resAjePtrn.crAjeCoaExtnCd.getValue();
//            }
//
//            boolean defaultSetFlg = false;            // QC#19726 add
//
//            // QC#6373 ADD Start
//            JRNL_ENTRYTMsg tMsg = new JRNL_ENTRYTMsg();
//            String crOrDr = "cr";
//            if (DR_CD.equals(drCrTpCd)) {
//                crOrDr = "dr";
//            }
//            // QC#6373 ADD End
//            for (int i = 0; i < 9; i++) {
//                switch (i) {
//                    case 0:// Company COA_CMPY
//                        // if (CR_CD.equals(drCrTpCd) &&
//                        // hasValue(invSlsCr.getString(COA_CMPY_CD)))
//                        // {
//                        if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_CMPY_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
//                            resultCd = invSlsCr.getString(COA_CMPY_CD);
//                        } else if (coaCmpyCd.substring(0, 1).equals("@") || coaCmpyCd.substring(0, 1).equals("#")) {
//                            // QC#6373 MOD Start
//                            //resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaCmpyCd, COA_ATTRB_NM_01, invSlsCr);
//                            //if (resultCd != null) {
//                            //    if (resultCd.subSequence(0, 1).equals("@")) {
//                            //        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_01, invSlsCr);
//                            //    }
//                            //}
//                            if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.CMPY, crOrDr, resAjePtrn, invSlsCr, null)) {
//                                resultCd = (String) tMsg.getDBValue(crOrDr + "CoaCmpyCd");
//                                
//                                //---- start QC#16075 2016/12/12  If COA value can't be retrieved, default value is set and that is logged to comment text.
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
//                                //---- end QC#16075
//                                
//                            } else {
//                                resultCd = null;
//                            }
//                            // QC#6373 MOD End
//                        } else {
//                            resultCd = coaCmpyCd;
//                        }
//
//                        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaCmpyCd, resultCd);
//                        
//                        break;
//
//                    case 1:// Branch COA_BR
//                        // if (CR_CD.equals(drCrTpCd) &&
//                        // hasValue(invSlsCr.getString(COA_BR_CD))) {
//                        if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_BR_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
//                            resultCd = invSlsCr.getString(COA_BR_CD);
//                        } else if (coaBrCd.substring(0, 1).equals("@") || coaBrCd.substring(0, 1).equals("#")) {
//                            // QC#6373 MOD Start
//                            //resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaBrCd, COA_ATTRB_NM_02, invSlsCr);
//                            //if (resultCd != null) {
//                            //    if (resultCd.subSequence(0, 1).equals("@")) {
//                            //        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_02, invSlsCr);
//                            //    }
//                            //}
//                            if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.BR, crOrDr, resAjePtrn, invSlsCr, null)) {
//                                resultCd = (String) tMsg.getDBValue(crOrDr + "CoaBrCd");
//                                
//                                //---- start QC#16075 2016/12/12  If COA value can't be retrieved, default value is set and that is logged to comment text.
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
//                                //---- end QC#16075
//                                
//                            } else {
//                                resultCd = null;
//                            }
//                            // QC#6373 MOD End
//                        } else {
//                            resultCd = coaBrCd;
//                        }
//
//                        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaBrCd, resultCd);
//                        break;
//
//                    case 2:// Cost Center COA_CC
//                        // if (CR_CD.equals(drCrTpCd) &&
//                        // hasValue(invSlsCr.getString(COA_CC_CD))) {
//                        if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_CC_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
//                            resultCd = invSlsCr.getString(COA_CC_CD);
//                        } else if (coaCcCd.substring(0, 1).equals("@") || coaCcCd.substring(0, 1).equals("#")) {
//                            // QC#6373 MOD Start
//                            //resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaCcCd, COA_ATTRB_NM_03, invSlsCr);
//                            //if (resultCd != null) {
//                            //    if (resultCd.subSequence(0, 1).equals("@")) {
//                            //        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_03, invSlsCr);
//                            //    }
//                            //}
//                            if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.CC, crOrDr, resAjePtrn, invSlsCr, null)) {
//                                resultCd = (String) tMsg.getDBValue(crOrDr + "CoaCcCd");
//                                
//                                //---- start QC#16075 2016/12/12  If COA value can't be retrieved, default value is set and that is logged to comment text.
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
//                                //---- end QC#16075
//                            } else {
//                                resultCd = null;
//                            }
//                            // QC#6373 MOD End
//                        } else {
//                            resultCd = coaCcCd;
//                        }
//
//                        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaCcCd, resultCd);
//                        break;
//
//                    case 3:// Account COA_ACCT
//                        // if (CR_CD.equals(drCrTpCd) &&
//                        // hasValue(invSlsCr.getString(COA_ACCT_CD)))
//                        // {
//                        if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_ACCT_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
//                            resultCd = invSlsCr.getString(COA_ACCT_CD);
//                        } else if (coaAcctCd.substring(0, 1).equals("@") || coaAcctCd.substring(0, 1).equals("#")) {
//                            // QC#6373 MOD Start
//                            //resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaAcctCd, COA_ATTRB_NM_04, invSlsCr);
//                            //if (resultCd != null) {
//                            //    if (resultCd.subSequence(0, 1).equals("@")) {
//                            //        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_04, invSlsCr);
//                            //    }
//                            //}
//                            if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.ACCT, crOrDr, resAjePtrn, invSlsCr, null)) {
//                                resultCd = (String) tMsg.getDBValue(crOrDr + "CoaAcctCd");
//                                
//                              //---- start QC#16075 2016/12/12  If COA value can't be retrieved, default value is set and that is logged to comment text.
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
//                                //---- end QC#16075
//                                
//                            } else {
//                                resultCd = null;
//                            }
//                            // QC#6373 MOD End
//                        } else {
//                            resultCd = coaAcctCd;
//                        }
//
//                        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaAcctCd, resultCd);
//                        // QC#19726 add Start
//                        if (COA_ACCT_CD_ITEM_REV.equals(coaAcctCd)) {
//                            defaultSetFlg = checkBsPlTp(glblCmpyCd, resultCd);
//                        }
//                        // QC#19726 add End
//                        break;
//
//                    case 4:// Product COA_PROD
//                        // if (CR_CD.equals(drCrTpCd) &&
//                        // hasValue(invSlsCr.getString(COA_PROD_CD)))
//                        // {
//                        if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_PROD_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
//                            resultCd = invSlsCr.getString(COA_PROD_CD);
//                        } else if (coaProdCd.substring(0, 1).equals("@") || coaProdCd.substring(0, 1).equals("#")) {
//                            // QC#6373 MOD Start
//                            //resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaProdCd, COA_ATTRB_NM_05, invSlsCr);
//                            //if (resultCd != null) {
//                            //    if (resultCd.subSequence(0, 1).equals("@")) {
//                            //        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_05, invSlsCr);
//                            //    }
//                            //}
//                            if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.PROD, crOrDr, resAjePtrn, invSlsCr, null)) {
//                                resultCd = (String) tMsg.getDBValue(crOrDr + "CoaProdCd");
//                                
//                              //---- start QC#16075 2016/12/12  If COA value can't be retrieved, default value is set and that is logged to comment text.
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
//                                //---- end QC#16075
//                                
//                            } else {
//                                resultCd = null;
//                            }
//                            // QC#6373 MOD End
//                        } else {
//                            resultCd = coaProdCd;
//                        }
//
//                        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaProdCd, resultCd);
//                        break;
//
//                    case 5:// Channel COA_CH
//                        // if (CR_CD.equals(drCrTpCd) &&
//                        // hasValue(invSlsCr.getString(COA_CH_CD))) {
//                        if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_CH_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
//                            resultCd = invSlsCr.getString(COA_CH_CD);
//                        } else if (coaChCd.substring(0, 1).equals("@") || coaChCd.substring(0, 1).equals("#")) {
//                            // QC#6373 MOD Start
//                            //resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaChCd, COA_ATTRB_NM_06, invSlsCr);
//                            //if (resultCd != null) {
//                            //    if (resultCd.subSequence(0, 1).equals("@")) {
//                            //        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_06, invSlsCr);
//                            //    }
//                            //}
//                            if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.CH, crOrDr, resAjePtrn, invSlsCr, null)) {
//                                resultCd = (String) tMsg.getDBValue(crOrDr + "CoaChCd");
//                                
//                              //---- start QC#16075 2016/12/12  If COA value can't be retrieved, default value is set and that is logged to comment text.
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
//                                //---- end QC#16075
//                            } else {
//                                resultCd = null;
//                            }
//                            // QC#6373 MOD End
//                        } else {
//                            resultCd = coaChCd;
//                        }
//
//                        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaChCd, resultCd);
//                        break;
//
//                    case 6:// Affiliate COA_AFFL
//                        // if (CR_CD.equals(drCrTpCd) &&
//                        // hasValue(invSlsCr.getString(COA_AFFL_CD)))
//                        // {
//                        if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_AFFL_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
//                            resultCd = invSlsCr.getString(COA_AFFL_CD);
//                        } else if (coaAfflCd.substring(0, 1).equals("@") || coaAfflCd.substring(0, 1).equals("#")) {
//                            // QC#6373 MOD Start
//                            //resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaAfflCd, COA_ATTRB_NM_07, invSlsCr);
//                            //if (resultCd != null) {
//                            //    if (resultCd.subSequence(0, 1).equals("@")) {
//                            //        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_07, invSlsCr);
//                            //    }
//                            //}
//                            if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.AFFL, crOrDr, resAjePtrn, invSlsCr, null)) {
//                                resultCd = (String) tMsg.getDBValue(crOrDr + "CoaAfflCd");
//                                
//                              //---- start QC#16075 2016/12/12  If COA value can't be retrieved, default value is set and that is logged to comment text.
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
//                                //---- end QC#16075
//                            } else {
//                                resultCd = null;
//                            }
//                            // QC#6373 MOD End
//                        } else {
//                            resultCd = coaAfflCd;
//                        }
//
//                        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaAfflCd, resultCd);
//                        break;
//
//                    case 7:// Merchandise Type COA_PROJ
//                        // if (CR_CD.equals(drCrTpCd) &&
//                        // hasValue(invSlsCr.getString(COA_PROJ_CD)))
//                        // {
//                        if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_PROJ_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
//                            resultCd = invSlsCr.getString(COA_PROJ_CD);
//                        } else if (coaProjCd.substring(0, 1).equals("@") || coaProjCd.substring(0, 1).equals("#")) {
//                            // QC#6373 MOD Start
//                            //resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaProjCd, COA_ATTRB_NM_08, invSlsCr);
//                            //if (resultCd != null) {
//                            //    if (resultCd.subSequence(0, 1).equals("@")) {
//                            //        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_08, invSlsCr);
//                            //    }
//                            //}
//                            if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.PROJ, crOrDr, resAjePtrn, invSlsCr, null)) {
//                                resultCd = (String) tMsg.getDBValue(crOrDr + "CoaProjCd");
//                                
//                              //---- start QC#16075 2016/12/12  If COA value can't be retrieved, default value is set and that is logged to comment text.
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
//                                //---- end QC#16075
//                            } else {
//                                resultCd = null;
//                            }
//                            // QC#6373 MOD End
//                        } else {
//                            resultCd = coaProjCd;
//                        }
//
//                        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaProjCd, resultCd);
//                        break;
//
//                    case 8:// Business Unit COA_EXTN
//                        // if (CR_CD.equals(drCrTpCd) &&
//                        // hasValue(invSlsCr.getString(COA_EXTN_CD)))
//                        // {
//                        if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_EXTN_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
//                            resultCd = invSlsCr.getString(COA_EXTN_CD);
//                        } else if (coaExtnCd.substring(0, 1).equals("@") || coaExtnCd.substring(0, 1).equals("#")) {
//                            // QC#6373 MOD Start
//                            //resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaExtnCd, COA_ATTRB_NM_09, invSlsCr);
//                            //if (resultCd != null) {
//                            //    if (resultCd.subSequence(0, 1).equals("@")) {
//                            //        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_09, invSlsCr);
//                            //    }
//                            //}
//                            if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.EXTN, crOrDr, resAjePtrn, invSlsCr, null)) {
//                                resultCd = (String) tMsg.getDBValue(crOrDr + "CoaExtnCd");
//                                
//                              //---- start QC#16075 2016/12/12  If COA value can't be retrieved, default value is set and that is logged to comment text.
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
//                                //---- end QC#16075
//                            } else {
//                                resultCd = null;
//                            }
//                            // QC#6373 MOD End
//                        } else {
//                            resultCd = coaExtnCd;
//                        }
//
//                        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaExtnCd, resultCd);
//                        break;
//
//                    default:
//                        break;
//                }
//                // QC#19726 add Start
//                if (defaultSetFlg) {
//                    break;
//                }
//                // QC#19726 add End
//            }
//            // QC#19726 add Start
//            if (defaultSetFlg) {
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaBrCd, defCoaBrCd);
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaCcCd, defCoaCcCd);
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaProdCd, defCoaProdCd);
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaChCd, defCoaChCd);
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaAfflCd, defCoaAfflCd);
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaProjCd, defCoaProjCd);
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaExtnCd, defCoaExtnCd);
//            }
//            // QC#19726 add End
//
//            boolean isError = false;
//
//            // If value was not set, error
//            if (!hasValue(ajeInvAcctDist.ajeCoaCmpyCd)) {
//                isError = true;
//                addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CMPY }, msgMap);
//
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CMPY })));
//                // ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invldValTxt,
//                // getInvalidVal(ajeInvAcctDistErr, COAMSG_COA_CMPY));
//            }
//            if (!hasValue(ajeInvAcctDist.ajeCoaBrCd)) {
//                isError = true;
//                addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_BR }, msgMap);
//
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_BR })));
//                // ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invldValTxt,
//                // getInvalidVal(ajeInvAcctDistErr, COAMSG_COA_BR));
//            }
//            if (!hasValue(ajeInvAcctDist.ajeCoaCcCd)) {
//                isError = true;
//                addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CC }, msgMap);
//
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CC })));
//                // ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invldValTxt,
//                // getInvalidVal(ajeInvAcctDistErr, COAMSG_COA_CC));
//            }
//            if (!hasValue(ajeInvAcctDist.ajeCoaAcctCd)) {
//                isError = true;
//                addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_ACCT }, msgMap);
//
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_ACCT })));
//                // ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invldValTxt,
//                // getInvalidVal(ajeInvAcctDistErr, COAMSG_COA_ACCT));
//            }
//            if (!hasValue(ajeInvAcctDist.ajeCoaProdCd)) {
//                isError = true;
//                addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_PROD }, msgMap);
//
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_PROD })));
//                // ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invldValTxt,
//                // getInvalidVal(ajeInvAcctDistErr, COAMSG_COA_PROD));
//            }
//            if (!hasValue(ajeInvAcctDist.ajeCoaChCd)) {
//                isError = true;
//                addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CH }, msgMap);
//
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CH })));
//                // ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invldValTxt,
//                // getInvalidVal(ajeInvAcctDistErr, COAMSG_COA_CH));
//            }
//            if (!hasValue(ajeInvAcctDist.ajeCoaAfflCd)) {
//                isError = true;
//                addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_AFFL }, msgMap);
//
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_AFFL })));
//                // ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invldValTxt,
//                // getInvalidVal(ajeInvAcctDistErr, COAMSG_COA_AFFL));
//            }
//            if (!hasValue(ajeInvAcctDist.ajeCoaProjCd)) {
//                isError = true;
//                addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_PROJ }, msgMap);
//
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_PROJ })));
//                // ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invldValTxt,
//                // getInvalidVal(ajeInvAcctDistErr, COAMSG_COA_PROJ));
//            }
//            if (!hasValue(ajeInvAcctDist.ajeCoaExtnCd)) {
//                isError = true;
//                addMsg(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_EXTN }, msgMap);
//
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_EXTN })));
//                // ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invldValTxt,
//                // getInvalidVal(ajeInvAcctDistErr, COAMSG_COA_EXTN));
//            }
//
//            return !isError;
//        }
//
//        private String getErrorMsg(AJE_INV_ACCT_DIST_ERRTMsg tmsg, String newMsg) {
//            String msg;
//            if (hasValue(tmsg.invErrMsgTxt)) {
//                if ((tmsg.invErrMsgTxt.getValue() + ", " + newMsg).length() > ERR_MSG_TXT_LEN) {
//                    // if length exceeds, not to add new message.
//                    msg = tmsg.invErrMsgTxt.getValue();
//                } else {
//                    msg = tmsg.invErrMsgTxt.getValue() + ", " + newMsg;
//                }
//
//            } else {
//                msg = newMsg;
//            }
//            return msg;
//        }
//
//        @SuppressWarnings("unused")
//        private String getInvalidVal(AJE_INV_ACCT_DIST_ERRTMsg tmsg, String newVal) {
//            String val;
//            if (hasValue(tmsg.invldValTxt)) {
//                if ((tmsg.invldValTxt.getValue() + ", " + newVal).length() > INVLD_VAL_TXT_LEN) {
//                    // if length exceeds, not to add new message.
//                    val = newVal;
//                } else {
//                    val = tmsg.invldValTxt.getValue() + ", " + newVal;
//                }
//            } else {
//                val = newVal;
//            }
//            return val;
//        }
//
//        @SuppressWarnings("unused")
//        private String getInvalidVal2(AJE_INV_ACCT_DIST_ERRTMsg tmsg, String coaTpCd) {
//            String val;
//            String coaVal = "";
//
//            if (COAID_COA_CMPY.equals(coaTpCd)) {
//                coaVal = COAMSG_COA_CMPY;
//            } else if (COAID_COA_BR.equals(coaTpCd)) {
//                coaVal = COAMSG_COA_BR;
//            } else if (COAID_COA_CC.equals(coaTpCd)) {
//                coaVal = COAMSG_COA_CC;
//            } else if (COAID_COA_ACCT.equals(coaTpCd)) {
//                coaVal = COAMSG_COA_ACCT;
//            } else if (COAID_COA_PROD.equals(coaTpCd)) {
//                coaVal = COAMSG_COA_PROD;
//            } else if (COAID_COA_CH.equals(coaTpCd)) {
//                coaVal = COAMSG_COA_CH;
//            } else if (COAID_COA_AFFL.equals(coaTpCd)) {
//                coaVal = COAMSG_COA_AFFL;
//            } else if (COAID_COA_PROJ.equals(coaTpCd)) {
//                coaVal = COAMSG_COA_PROJ;
//            } else if (COAID_COA_EXTN.equals(coaTpCd)) {
//                coaVal = COAMSG_COA_EXTN;
//            }
//
//            if (hasValue(tmsg.invldValTxt)) {
//                if ((tmsg.invldValTxt.getValue() + ", " + coaVal).length() > INVLD_VAL_TXT_LEN) {
//                    // if length exceeds, not to add new message.
//                    val = coaVal;
//                } else {
//                    val = tmsg.invldValTxt.getValue() + ", " + coaVal;
//                }
//            } else {
//                val = coaVal;
//            }
//            return val;
//        }
//
//        /**
//         * @param invTpCd
//         * @param amount
//         * @return
//         */
//        private BigDecimal getAmount(String invTpCd, BigDecimal amount) {
//
//            if (invTpCd.equals(INV_TP.CREDIT_MEMO)) {
//                amount = amount.negate();
//            }
//            return amount;
//        }
//
//        /**
//         * @param ajeInvAcctDist
//         * @throws SQLException
//         */
//        // 2016/10/27 change to use array list. Commit per one invoice
//        private void createAjeInvAcctDist(AJE_INV_ACCT_DISTTMsg ajeInvAcctDist) throws NFACommonJrnlEntry.JrnlCommonException {
//
//            if (ajeInvAcctDist != null) {
//                //---- start 2016/07/15 if flag is not set and amount is zero, not to generate data.
//                //if (!ZYPConstant.FLG_ON_Y.equals(zeroCratFlg)) { // DEL 2016/12/07 [QC#16174]
//                    // START 2016/09/26 S.Fujita [QC#13362,MOD]
////                    if (BigDecimal.ZERO.compareTo(ajeInvAcctDist.jrnlDealAmt.getValue()) == 0) {
//                    if (!(AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue()) //
//                            || AJE_INV_ACCT_CLS.REVENUE.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue()) // ADD 2016/12/08 [QC#16174]
//                            || AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue())) // ADD 2016/12/08 [QC#16174]
//                                && BigDecimal.ZERO.compareTo(ajeInvAcctDist.jrnlDealAmt.getValue()) == 0) {
//                    // END   2016/09/26 S.Fujita [QC#13362,MOD]
//                        // skip
//                        return;
//                    }
//                // } // DEL 2016/12/07 [QC#16174]
//
//                // ---- end 2016/07/15
//
//                // add to list
//                listAcctDist.add(ajeInvAcctDist);
//                return;
//            }
//
//            if (ajeInvAcctDist == null && listAcctDist.size() > 0) {
//                AJE_INV_ACCT_DISTTMsg[] ajeInvAcctDistMsgs = new AJE_INV_ACCT_DISTTMsg[listAcctDist.size()];
//                listAcctDist.toArray(ajeInvAcctDistMsgs);
//
//                ajeInvAcctDistMsgs = setProcStsPerInv(ajeInvAcctDistMsgs);
//
//                int retCnt = S21FastTBLAccessor.insert(ajeInvAcctDistMsgs);
//
//                // if passed records' count and return count don't match, error
//                if (retCnt != listAcctDist.size()) {
//                    throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
//                }
//                commitCount += listAcctDist.size();
//                // initialize
//                listAcctDist.clear();
//            }
//
//        }
//
//        // --- start add 2016/01/26
//        private AJE_INV_ACCT_DISTTMsg[] setProcStsPerInv(AJE_INV_ACCT_DISTTMsg[] tmsgs) {
//
//            boolean isErr = false;
//            AJE_INV_ACCT_DISTTMsg tmsg = new AJE_INV_ACCT_DISTTMsg();
//
//            // if thre's any error
//            if (listAcctDistErr.size() > 0) {
//                isErr = true;
//            }
//
//            /*
//             * for (int i = 0; i < tmsgs.length; i++) { tmsg =
//             * tmsgs[i]; if
//             * (PROC_STS.ERROR.equals(tmsg.procStsCd.getValue())) {
//             * isErr = true; break; } }
//             */
//
//            // If any of the record has error, entire invoice to be
//            // logged as error.
//            if (isErr) {
//                for (int i = 0; i < tmsgs.length; i++) {
//
//                    tmsg = (AJE_INV_ACCT_DISTTMsg) tmsgs[i];
//
//                    tmsg.procStsCd.setValue(PROC_STS.ERROR);
//
//                    tmsgs[i] = tmsg;
//                }
//            }
//
//            return tmsgs;
//        }
//
//        // ---- end 2016/01/26
//
//        /**
//         * @param ajeInvAcctDist
//         * @throws SQLException
//         */
//        private void createAjeInvAcctDistErr(AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr) throws NFACommonJrnlEntry.JrnlCommonException {
//
//            if (ajeInvAcctDistErr != null) {
//                listAcctDistErr.add(ajeInvAcctDistErr);
//            }
//
//            // per 10000 lines
//            // if (ajeInvAcctDistErrMsgCount >= BULK_INSERT_COUNT ||
//            // ajeInvAcctDistErr == null) {
//            if (ajeInvAcctDistErr == null && listAcctDistErr.size() > 0) {
//
//                AJE_INV_ACCT_DIST_ERRTMsg[] tmsgArray = new AJE_INV_ACCT_DIST_ERRTMsg[listAcctDistErr.size()];
//                listAcctDistErr.toArray(tmsgArray);
//
//                int retCnt = S21FastTBLAccessor.insert(tmsgArray);
//
//                // if passed records' count and return count don't
//                // match, error
//                if (retCnt != listAcctDistErr.size()) {
//                    throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
//                }
//                commitCount += listAcctDistErr.size();
//                // initialize
//                listAcctDistErr.clear();
//            }
//
//        }
//
//        /**
//         * @param param
//         * @param ajeInvAcctDist
//         * @param ajeInvAcctDistErr
//         */
//        private void glCodeCombinationCheck(NFZC103001PMsg param, AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr) {
//
//            NFZC102001 api = new NFZC102001();
//            NFZC102001PMsg apiMsg = new NFZC102001PMsg();
//
//            apiMsg.glblCmpyCd.setValue(ajeInvAcctDist.glblCmpyCd.getValue());
//            apiMsg.xxMstChkFlg.setValue(ZYPConstant.FLG_ON_Y);
//            apiMsg.xxGlCmbnChkFlg.setValue(ZYPConstant.FLG_ON_Y);
//            apiMsg.xxArcsApiChkFlg.setValue(ZYPConstant.FLG_OFF_N);
//
//            apiMsg.coaCmpyCd.setValue(ajeInvAcctDist.ajeCoaCmpyCd.getValue());
//            apiMsg.coaBrCd.setValue(ajeInvAcctDist.ajeCoaBrCd.getValue());
//            apiMsg.coaCcCd.setValue(ajeInvAcctDist.ajeCoaCcCd.getValue());
//            apiMsg.coaAcctCd.setValue(ajeInvAcctDist.ajeCoaAcctCd.getValue());
//            apiMsg.coaProdCd.setValue(ajeInvAcctDist.ajeCoaProdCd.getValue());
//            apiMsg.coaChCd.setValue(ajeInvAcctDist.ajeCoaChCd.getValue());
//            apiMsg.coaAfflCd.setValue(ajeInvAcctDist.ajeCoaAfflCd.getValue());
//            apiMsg.coaProjCd.setValue(ajeInvAcctDist.ajeCoaProjCd.getValue());
//            apiMsg.coaExtnCd.setValue(ajeInvAcctDist.ajeCoaExtnCd.getValue());
//
//            // START 2016/09/28 K.Kojima [QC#14609,ADD]
//            apiMsg.bizAppId.setValue("NFZC1030");
//            // END 2016/09/28 K.Kojima [QC#14609,ADD]
//
//            api.execute(apiMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);
//            String result = apiMsg.getReturnCode();
//
//            int i = 0;
//            String msgId;
//            String msgTxt;
//            for (; i < apiMsg.xxMsgIdList.getValidCount(); i++) {
//                // param.xxMsgIdList.no(this.errCnt+i).invNum.setValue(ajeInvAcctDist.invNum.getValue());
//                // param.xxMsgIdList.no(this.errCnt+i).procStsCd.setValue(PROC_STS.ERROR);
//                // param.xxMsgIdList.no(this.errCnt+i).xxMsgId.setValue(apiMsg.xxMsgIdList.no(i).xxMsgId.getValue());
//                // param.xxMsgIdList.no(this.errCnt+i).xxMsgPrmTxt_0.setValue(apiMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue());
//
//                msgId = apiMsg.xxMsgIdList.no(i).xxMsgId.getValue();
//                msgTxt = apiMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue();
//
//                addMsg(msgId, null, msgMap);
//
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(msgId, new String[] {msgTxt })));
//
//                // ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invldValTxt,
//                // getInvalidVal2(ajeInvAcctDistErr,
//                // apiMsg.xxMsgIdList.no(i).xxCoaTpCd.getValue()));
//            }
//            this.errCnt += i;
//            if (apiMsg.xxMsgIdList.getValidCount() > 0) {
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procStsCd, PROC_STS.ERROR);
//            }
//        }
//
//        // START 2016/12/07 [QC#16174, ADD]
//        private boolean setAjeInvAcctDistAmountValuesForFrtTax(NFAC000101PMsg resAjePtrn, ResultSet rsNotJrnlized, String drCrTpCd) throws SQLException, JrnlCommonException {
//
//            String glDt = NFZC103001Common.getGlDt(param.glblCmpyCd.getValue(), param.procDt.getValue(), rsNotJrnlized.getString(ACCT_DT));
//
//            AJE_INV_ACCT_DISTTMsg ajeInvAcctDist = new AJE_INV_ACCT_DISTTMsg();
//            AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr = new AJE_INV_ACCT_DIST_ERRTMsg();
//
//            // Set Common Data
//            setAjeInvAcctDistCommonValues(param, ajeInvAcctDist, rsNotJrnlized, resAjePtrn, drCrTpCd, glDt);
//
//            // Set Amount FREIGHT TAX Date
//            setAjeInvAcctDistAmountValuesForFrtTax(ajeInvAcctDist, rsNotJrnlized);
//
//            // Set 9 Segments Data
//            if (!setAjeInvAcctDist9SegmentsValues(param, ajeInvAcctDist, ajeInvAcctDistErr, rsNotJrnlized, resAjePtrn, drCrTpCd)) {
//                // error
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procStsCd, PROC_STS.ERROR);
//            } else {
//                // Check 9 Segments
//                glCodeCombinationCheck(param, ajeInvAcctDist, ajeInvAcctDistErr);
//            }
//
//            createAjeInvAcctDist(ajeInvAcctDist);
//
//            // If any error, create error record
//            if (PROC_STS.ERROR.equals(ajeInvAcctDist.procStsCd.getValue())) {
//
//                // set value other than error message
//                BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_ERR_SQ);
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.glblCmpyCd, ajeInvAcctDist.glblCmpyCd.getValue());
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistErrPk, pk);
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistPk, ajeInvAcctDist.ajeInvAcctDistPk.getValue());
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invNum, ajeInvAcctDist.invNum.getValue());
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invBolLineNum, ajeInvAcctDist.invBolLineNum.getValue());
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineNum, ajeInvAcctDist.invLineNum.getValue());
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineSubNum, ajeInvAcctDist.invLineSubNum.getValue());
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invTrxLineSubNum, ajeInvAcctDist.invLineSubTrxNum.getValue());
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistSqNum, ajeInvAcctDist.ajeInvAcctDistSqNum.getValue());
//
//                String coaConcat = nvl(ajeInvAcctDist.ajeCoaCmpyCd.getValue()).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaBrCd.getValue())).concat(",").concat(
//                        nvl(ajeInvAcctDist.ajeCoaCcCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaAcctCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaProdCd.getValue()))
//                        .concat(",").concat(nvl(ajeInvAcctDist.ajeCoaChCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaAfflCd.getValue())).concat(",").concat(
//                                nvl(ajeInvAcctDist.ajeCoaProjCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaExtnCd.getValue()));
//
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invldValTxt, coaConcat);
//
//                createAjeInvAcctDistErr(ajeInvAcctDistErr);
//            }
//
//            return true;
//        }
//
//        /**
//         * Set Amount Freight TAX by INV_BOL
//         * @param ajeInvAcctDist
//         * @param invSlsCr
//         * @return booolean
//         * @throws SQLException
//         * @throws JrnlCommonException
//         */
//        private boolean setAjeInvAcctDistAmountValuesForFrtTax(AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, ResultSet invSlsCr) throws SQLException, JrnlCommonException {
//
//            String invTpCd = invSlsCr.getString(INV_TP_CD);
//
//            // Freight TAX by INV_BOL
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(FRT_DEAL_TAX_AMT)));
//            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(FRT_FUNC_TAX_AMT)));
//
//            return true;
//        }
//        // END 2016/12/07 [QC#16174, ADD]
//
//        // START 2016/12/26 [QC#16818, ADD]
//        /**
//         * Update index Number
//         * @return boolean
//         */
//        @SuppressWarnings("unchecked")
//        private boolean updateInvoice_ACCT_DISTWithIdxNum() {
//
//            // ----------------------------------------------
//            // Update AJE_LINE_IDX_NUM in AJE_INV_ACCT_DIST
//            // ----------------------------------------------
//            Map<String, Object> ssmParam = new HashMap<String, Object>();
//            ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
//            ssmParam.put("invNum", param.invNum.getValue());
//            List<Map<String, Object>> ajeInvAcctDistList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getAjeInvAcctDistList", ssmParam);
//
//            int ajeLineIdxNum = INIT_AJE_LINE_IDX_NUM;
//            BigDecimal dsInvSlsCrPk = BigDecimal.ZERO;
//            String preDrCrCd = "";
//
//            if (ajeInvAcctDistList == null) {
//                return false;
//            }
//
//            for (Map<String, Object> ajeInvAcctDistListData : ajeInvAcctDistList) {
//
//                BigDecimal ajeInvAcctDistPk = (BigDecimal) ajeInvAcctDistListData.get(AJE_INV_ACCT_DIST_PK);
//                String drCrTpCd = (String) ajeInvAcctDistListData.get(DR_CR_TP_CD);
//
//                if (preDrCrCd.equals(drCrTpCd)) {
//                    if (dsInvSlsCrPk.compareTo((BigDecimal) ajeInvAcctDistListData.get(DS_INV_SLS_CR_PK)) == 0) {
//                        ajeLineIdxNum++;
//                    } else {
//                        ajeLineIdxNum = INIT_AJE_LINE_IDX_NUM;
//                    }
//                } else {
//                    ajeLineIdxNum = INIT_AJE_LINE_IDX_NUM;
//                }
//                dsInvSlsCrPk = (BigDecimal) ajeInvAcctDistListData.get(DS_INV_SLS_CR_PK);
//                preDrCrCd = drCrTpCd;
//
//                AJE_INV_ACCT_DISTTMsg inMsg = new AJE_INV_ACCT_DISTTMsg();
//                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, param.glblCmpyCd.getValue());
//                ZYPEZDItemValueSetter.setValue(inMsg.ajeInvAcctDistPk, ajeInvAcctDistPk);
//                AJE_INV_ACCT_DISTTMsg updMsg = (AJE_INV_ACCT_DISTTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
//                    errMsgId = NWAM0794E;
//                    return false;
//                }
//
//                ZYPEZDItemValueSetter.setValue(updMsg.ajeLineIdxNum, String.format("%02d", ajeLineIdxNum));
//                EZDTBLAccessor.update(updMsg);
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
//                    errMsgId = NFDM0003E;
//                    return false;
//                }
//            }
//
//            return true;
//        }
//        // END   2016/12/26 [QC#16818, ADD]
//    }
    // END 2018/03/12 E.Kameishi [QC#23689,DEL]

    private boolean removeAjeInvAcctDist(S21ApiMessageMap msgMap) {

        NFZC103001PMsg param = (NFZC103001PMsg) msgMap.getPmsg();
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("procStsCd", PROC_STS.ERROR);

        Boolean res = (Boolean) ssmBatchClient.queryObject("getAjeInvAcctDist", queryParam, new DeleteAjeInvAcctDistHandler(msgMap));
        return res;
    }

    private final class DeleteAjeInvAcctDistHandler extends S21SsmBooleanResultSetHandlerSupport {
        private DeleteAjeInvAcctDistHandler(S21ApiMessageMap msgMap) {
        }

        protected Boolean doProcessQueryResult(ResultSet rsDelete) throws SQLException {
            try {
                while (rsDelete.next()) {
                    AJE_INV_ACCT_DISTTMsg ajeInvAcctDist = new AJE_INV_ACCT_DISTTMsg();
                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.glblCmpyCd, rsDelete.getString(GLBL_CMPY_CD));
                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctDistPk, rsDelete.getBigDecimal(AJE_INV_ACCT_DIST_PK));
                    removeAjeInvAcctDist(ajeInvAcctDist);
                }
                if (rmvMsgCount != 0) {
                    removeAjeInvAcctDist(null);
                }

            } catch (NFACommonJrnlEntry.JrnlCommonException ex) {
                errMsgId = ZZBM0074E;
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }

        private void removeAjeInvAcctDist(EZDTMsg rmvRec) throws NFACommonJrnlEntry.JrnlCommonException {

            if (rmvRec != null) {
                rmvMsgs[rmvMsgCount] = rmvRec;
                rmvMsgCount += 1;
            } else {
                rmvMsgs = commonJrnlEntry.changeArraySize(rmvMsgs, rmvMsgCount);
            }

            // per 10000 lines
            if (rmvMsgCount >= BULK_INSERT_COUNT || rmvRec == null) {

                int retCnt = S21FastTBLAccessor.removeLogical(rmvMsgs);

                // if passed records' count and return count don't
                // match, error
                if (retCnt != rmvMsgCount) {
                    throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
                }
                // initialize
                rmvMsgCount = 0;
                rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];
            }
        }
    }

    /**
     * @param logmsg
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(DEBUG_MSG_LVL, logmsg, this);
    }

    // 2016/03/04 add. Messages to be within the length of array
    private void addMsg(String msgId, String[] msgParam, S21ApiMessageMap msgMap) {

        NFZC103001PMsg param = (NFZC103001PMsg) msgMap.getPmsg();

        if (param.xxMsgIdList.getValidCount() < param.xxMsgIdList.length()) {
            if (msgParam != null) {
                msgMap.addXxMsgIdWithPrm(msgId, msgParam);
            } else {
                msgMap.addXxMsgId(msgId);
            }
        }
    }
// QC#19726 add Start
    private boolean checkBsPlTp(String glblCmpyCd, String coaAcctCd) throws SQLException {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("coaAcctCd", coaAcctCd);

        String bsPlTpCd = (String) ssmBatchClient.queryObject("getBsPlTp", queryParam);

        if (BS_PL_TP_CD_BS.equals(bsPlTpCd)) {
            return true;
        }
        return false;
    }
// QC#19726 add End

    // START 2018/01/15 E.Kameishi [QC#23166,ADD]
    private boolean setSvcInvChrgTp(String glblCmpyCd, String invNum, String manAllocTrxRsnCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("trxCd", TRX.SALES);
        queryParam.put("trxRsnCdList", manAllocTrxRsnCd.split(","));
        // START 2018/10/26 E.Kameishi [QC#28976,ADD]
        queryParam.put("creditMemo", INV_TP.CREDIT_MEMO);
        // END 2018/10/26 E.Kameishi [QC#28976,ADD]

        List<Map<String, Object>> svcInvChrgTpList = (List<Map<String, Object>>)  ssmBatchClient.queryObjectList("getSvcInvChrgTp", queryParam);

        if (svcInvChrgTpList != null) {
            for (Map<String, Object> svcInvChrgTp : svcInvChrgTpList) {
                String invSvcInvChrgTp = (String) svcInvChrgTp.get("INV_SVC_INV_CHRG_TP_CD");
                String svcSvcInvChrgTp = (String) svcInvChrgTp.get("SVC_INV_CHRG_TP_CD");
                BigDecimal dsInvSlsCrPk = (BigDecimal) svcInvChrgTp.get("DS_INV_SLS_CR_PK");
                
                if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(invSvcInvChrgTp) || 
                        (SVC_INV_CHRG_TP.ADDITIONAL_CHARGE.equals(invSvcInvChrgTp) && SVC_INV_CHRG_TP.BASE_CHARGE.equals(svcSvcInvChrgTp))) {
                    DS_INV_SLS_CRTMsg dsInvSlsCrTMsg = new DS_INV_SLS_CRTMsg();

                    ZYPEZDItemValueSetter.setValue(dsInvSlsCrTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dsInvSlsCrTMsg.dsInvSlsCrPk, dsInvSlsCrPk);
                    dsInvSlsCrTMsg = (DS_INV_SLS_CRTMsg) EZDTBLAccessor.findByKeyForUpdate(dsInvSlsCrTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsInvSlsCrTMsg.getReturnCode())) {
                        errMsgId = NWAM0794E;
                        return false;
                    }

                    ZYPEZDItemValueSetter.setValue(dsInvSlsCrTMsg.dfrdAcctgRuleCd, DFRD_ACCTG_RULE.DEFERRED);
                    EZDTBLAccessor.update(dsInvSlsCrTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsInvSlsCrTMsg.getReturnCode())) {
                        errMsgId = NFZM0027E;
                        errMsgTxt = S21MessageFunc.clspGetMessage(NFZM0027E, new String[] {"DS_INV_SLS_CR"});
                        return false;
                    }
                }
            }
        }
        return true;
    }
    // END 2018/01/15 E.Kameishi [QC#23166,ADD]
}
