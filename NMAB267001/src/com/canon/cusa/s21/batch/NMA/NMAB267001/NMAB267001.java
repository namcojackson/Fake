/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB267001;

import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.ADDL_LOC_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.LABEL_RELN_BILL_TO;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.LABEL_ACCT_RELN;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.NMAM8111E;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.BIG_DEAL_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.BILL_TO_AVAL_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.BILL_TO_EFF_FROM_DT_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.BILL_TO_EFF_THRU_DT_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.BLANK;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.BTC_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.BTC_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.CNTY_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.COA_AFFL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.COA_CH_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.CON_WRK;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.COUNT;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.CTRY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.CTY_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.DBA_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.DPL_STS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.DS_ACCT_CLS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.DS_ACCT_DLR_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.DS_ACCT_ITRL_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.DS_ACCT_LEGAL_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.DS_ACCT_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.DS_ACCT_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.DS_ACCT_URL;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.DS_LOC_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.FAX_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.FRTH_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.GET_ACC;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.GET_LOC;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.GET_WRK;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.GLN_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.LABEL_BILL_TO_AVAL_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.LABEL_BILL_TO_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.LABEL_LOC_ACTV_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.LABEL_LOC_PRIM_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.LABEL_SHIP_TO_AVAL_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.LABEL_SHIP_TO_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.LOC_ACTV_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.LOC_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.LOC_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.LOC_PRIM_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.MAX_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.MAX_DATE_VALUE;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.MAX_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.MSG_UPLD_CSV_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.MSG_UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.NMAM8228E;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.NMAM8403E;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.NMAM8404E;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.NMAM8406E;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.NMAM8411E;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.NMAM8412E;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.NMAM8413E;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.NMAM8414E;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.NMAM8463E;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.NMAM8684E;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.NMZM0179W;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.NUM_8;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.NWBM0097E;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.NYXM0001I;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.NYXM0002E;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.PARAM_CNTY_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.PARAM_CTY_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.PARAM_DS_ACCT_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.PARAM_FRTH_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.PARAM_LOC_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.PARAM_LOC_PRIM_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.PARAM_POST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.PARAM_SCD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.PARAM_ST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.PARAM_UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.POST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.PRIM_USG_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.PROCESS_MODE04;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.PROCESS_MODE06;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.PROV_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.PTY_LOC_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.RELN_BILL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.RESULT_MSG_ERR;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.RESULT_MSG_INS;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.RESULT_MSG_UPD;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.RGTN_STS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.SCD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.SINGLE_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.SHIP_TO_AVAL_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.SHIP_TO_EFF_FROM_DT_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.SHIP_TO_EFF_THRU_DT_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.STC_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.STC_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.ST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.TEL_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.THIRD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.UPLD_CSV_RQST_CMNT_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.UPLD_CSV_RQST_ROW_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB267001.constant.NMAB267001Constant.ZYPM0181E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ART10FMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CTRYTMsg;
import business.db.LINE_BIZ_TPTMsg;
import business.db.LOC_INFO_WRKTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.STTMsg;
import business.db.UPLD_CSV_RQSTTMsg;
import business.parts.NMZC001001PMsg;
import business.parts.NMZC001002PMsg;
import business.parts.NMZC001002_xxMsgIdListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC001001.NMZC001001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Location Mass Upload Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/18   Hitachi         T.Mizuki        Create          N/A
 * 2016/04/20   Fujitsu         N.Sugiura       Update          CSA-QC#7360
 * 2016/04/22   Fujitsu         N.Sugiura       Update          QC#7458
 * 2016/04/28   SRAA            Y.Chen          Update          QC#2674
 * 2016/05/26   Fujitsu         M.Suzuki        Update          QC#9073
 * 2016/06/14   Fujitsu         S.Ohki          Update          QC#9814
 * 2016/06/16   Hitachi         Y.Osawa         Update          QC#8156
 * 2016/06/24   Hitachi         A.Kohinata      Update          QC#10469
 * 2016/06/29   Hitachi         Y.Takeno        Update          QC#8156
 * 2016/09/27   Hitachi         T.Mizuki        Update          QC#14721
 * 2016/10/28   Fujitsu         C.Yokoi         Update          QC#15035
 * 2016/12/21   Fujitsu         N.Sugiura       Update          QC#16798
 * 2017/10/18   Fujitsu         M.Ohno          Update          QC#21514
 * 2017/10/23   Fujitsu         M.Ohno          Update          QC#21559
 * 2017/10/23   Fujitsu         M.Ohno          Update          QC#21559-2
 * 2018/07/02   Fujitsu         T.Noguchi       Update          QC#26423
 * 2018/07/18   Fujitsu         Hd.Sugawara     Update          QC#26407
 * 2018/11/28   Fujitsu         C.Hara          Update          QC#29134
 * 2018/12/19   Fujitsu         T.Noguchi       Update          QC#29134
 *</pre>
 */

public class NMAB267001 extends S21BatchMain {

    /** UserProfile */
    private S21UserProfileService profile = null;

    /** GlobalCompanyCode */
    private String glblCmpyCd = null;

    /** SsmLLClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** process date time */
    private String procDt = null;

    /** BatchHelper */
    private S21RequestBatchHelper batchHelper = null;

    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;

    /** TermCd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    // Del Start 2018/07/18 QC#26407
    ///** ProcCount */
    //private int procCount = 0;
    // Del End 2018/07/18 QC#26407

    // Del Start 2018/07/18 QC#26407
    ///** ErrCount */
    //private int errCount = 0;
    // Del End 2018/07/18 QC#26407

    /** WarnFlg */
    private boolean warnFlg = false;

    // START 2016/06/29 [QC#8156,ADD]
    /** input record count (transaction) */
    private int inputRecCnt  = 0;

    /** insert record count (transaction) */
    private int insRecCnt = 0;

    /** update record count (transaction) */
    private int updRecCnt = 0;

    /** error record count (transaction) */
    private int errRecCnt  = 0;

    /** insert record count (file) */
    private int insRecCntFile = 0;

    /** update record count (file) */
    private int updRecCntFile = 0;

    /** error record count (file) */
    private int errRecCntFile  = 0;
    // END 2016/06/29 [QC#8156,ADD]

    /**
     */
    @Override
    protected void initRoutine() {

        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.procDt = ZYPDateUtil.getBatProcDate();
        batchHelper = new S21RequestBatchHelper();

    }

    /**
     */
    @Override
    protected void mainRoutine() {

        glblCmpyCd = getGlblCmpyCd();
        if (!this.batchHelper.isRecord()) {
            this.termCd = TERM_CD.WARNING_END;
            return;
        }
        for (ART10FMsg request : this.batchHelper.getRequestList()) {
            this.doProcess(request);
        }
    }

    /**
     */
    @Override
    protected void termRoutine() {
        // 2016/10/28 CSA-QC#15627 Mod Start
        setTermState(this.termCd, updRecCntFile + insRecCntFile, errRecCntFile);
        // setTermState(this.termCd, procCount, errCount);
        // 2016/10/28 CSA-QC#15627 Mod End
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {

        new NMAB267001().executeBatch(NMAB267001.class.getSimpleName());
    }

    /**
     * @param fMsg ART10FMsg
     */
    private void doProcess(ART10FMsg fMsg) {

        PreparedStatement stmtWrk = null;
        PreparedStatement stmtRcv = null;
        PreparedStatement stmtAc = null;
        ResultSet rsWrk = null;
        ResultSet rsRcv = null;
        ResultSet rsAc = null;

        // Upload ID
        String upldCsvId = getUpldCsvId(fMsg);
        EZDDebugOutput.println(0, "UPLD_CSV_ID" + upldCsvId, this);

        // Upload Request PK
        BigDecimal upldCsvRqstPk = getUpldCsvReqPk(fMsg);
        EZDDebugOutput.println(0, "UPLD_CSV_RQST_PK" + upldCsvRqstPk, this);
        UPLD_CSV_RQSTTMsg upldCSVRqstTMsg = getUpldCSVRqst(upldCsvRqstPk);
        this.messenger = new ZYPCSVUploadMessenger(upldCsvId, upldCsvRqstPk);

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);

            Map<String, Object> inWrkMap = new HashMap<String, Object>();
            inWrkMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
            inWrkMap.put(PARAM_UPLD_CSV_RQST_PK, upldCSVRqstTMsg.upldCsvRqstPk);

            stmtWrk = ssmLLClient.createPreparedStatement(GET_WRK, inWrkMap, execParam);
            rsWrk = stmtWrk.executeQuery();
            LOC_INFO_WRKTMsg wrkTMsg = null;
            // 2016/10/28 CSA-QC#15035 Mod Start
            boolean isSuccess = true;
            // boolean checkAccount = true;
            // 2016/10/28 CSA-QC#15035 Mod End
            boolean insertFlag = true;
            int idx = 0;
            // 2016/10/28 CSA-QC#15035 Mod Start
            NMZC001001PMsg insLocInfMsg = new NMZC001001PMsg();
            NMZC001001PMsg updLocInfMsg = new NMZC001001PMsg();
            // NMZC001001PMsg locInfMsg = new NMZC001001PMsg();
            // 2016/10/28 CSA-QC#15035 Mod End
            NMZC001002_xxMsgIdListPMsg xxMsgIdListPMsg = null;
            LOC_INFO_WRKTMsg updtWrkTMsg = new LOC_INFO_WRKTMsg();
            String dsAcctNumPrev = null;
            BigDecimal rowNumPrev = BigDecimal.valueOf(0);
            // 2016/10/28 CSA-QC#15035 Mod Start
            List<BigDecimal> insRowNumList = new ArrayList<BigDecimal>();
            List<BigDecimal> updRowNumList = new ArrayList<BigDecimal>();
            // List<BigDecimal> rowNumList = new ArrayList<BigDecimal>();
            // 2016/10/28 CSA-QC#15035 Mod End
            // START 2016/06/29 [QC#8156,ADD]
            resetFileRecordCounter();
            resetTransactionRecordCounter();
            // END 2016/06/29 [QC#8156,ADD]

            while (rsWrk.next()) {
                String locationStartDate = null;
                String locationEndDate = null;
                String locNumBfr = null;
                String locNum = null;
                String dsAcctNum = null;
                String statusCode = null;

                // 2018/07/02 QC#26423 Add Start
                execParam.setFetchSize(MAX_FETCH_SIZE);
                // 2018/07/02 QC#26423 Add End

                // START 2016/06/29 [QC#8156,ADD]
                this.inputRecCnt++;
                // END 2016/06/29 [QC#8156,ADD]

                wrkTMsg = transferWrk(rsWrk);
                // 2018/07/02 QC#26423 Del Start (Move)
                // if (checkRCVKey(wrkTMsg)) {
                //    // 2016/10/28 CSA-QC#15035 Mod Start
                //    isSuccess = false;
                //    // errCount++;
                //    // 2016/10/28 CSA-QC#15035 Mod End
                //    continue;
                //}
                // 2018/07/02 QC#26423 Del End
                dsAcctNum = rsWrk.getString(DS_ACCT_NUM);

                // 2018/07/02 QC#26423 Del Start
                // if (!dsAcctNum.equals(dsAcctNumPrev)) {
                // 2018/07/02 QC#26423 Del End
                    if (ZYPCommonFunc.hasValue(dsAcctNumPrev)) {
                        if (isSuccess) {
                            if (callApiNMZC001001(insLocInfMsg, rowNumPrev, insRowNumList)) {
                                // 2016/10/28 CSA-QC#15035 Mod Start
                                setMsgFromNMZC001002(insLocInfMsg, xxMsgIdListPMsg, updtWrkTMsg);
                                // 2017/10/18 QC#21514 add start
                                setMsgLocBillShipCd(insLocInfMsg, insRowNumList);
                                // 2017/10/18 QC#21514 add end
//                                 for (int i = 0; i < locInfMsg.NMZC001002PMsg.getValidCount(); i++) {
//                                    NMZC001002PMsg linePrm = locInfMsg.NMZC001002PMsg.no(i);
//                                    for (int cnt = 0; cnt < linePrm.xxMsgIdList.getValidCount(); cnt++) {
//                                        xxMsgIdListPMsg = linePrm.xxMsgIdList.no(cnt);
//                                        if (NMZM0179W.equals(xxMsgIdListPMsg.xxMsgId.getValue())) {
//                                            if (PARAM_FRTH_LINE_ADDR.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
//                                                setValue(wrkTMsg.firstLineAddr, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
//                                            } else if (PARAM_SCD_LINE_ADDR.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
//                                                setValue(wrkTMsg.scdLineAddr, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
//                                            } else if (PARAM_CTY_ADDR.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
//                                                setValue(wrkTMsg.ctyAddr, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
//                                          } else if (PARAM_ST_CD.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
//                                                setValue(wrkTMsg.stCd, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
//                                            } else if (PARAM_POST_CD.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
//                                                setValue(wrkTMsg.postCd, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
//                                            } else if (PARAM_CNTY_NM.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
//                                                setValue(wrkTMsg.cntyNm, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
//                                            }
//                                        }
//                                    }
//                              }
                            } else {
                                isSuccess = false;
                            }
                            // 2016/10/28 CSA-QC#15035 Mod End

                            // 2016/10/28 CSA-QC#15035 Add Start
                            if (callApiNMZC001001(updLocInfMsg, rowNumPrev, updRowNumList)) {
                                setMsgFromNMZC001002(updLocInfMsg, xxMsgIdListPMsg, updtWrkTMsg);
                            } else {
                                isSuccess = false;
                            }
                            // 2016/10/28 CSA-QC#15035 Add Start
                        }

                        if (isSuccess) {
                            commit();
                            // START 2016/06/29 [QC#8156,ADD]
                            this.errRecCnt = this.inputRecCnt - this.insRecCnt - this.updRecCnt;
                            totalUpTransactionRecordCounter();
                            resetTransactionRecordCounter();
                            // END 2016/06/29 [QC#8156,ADD]
                        } else {
                            rollback();
                            // START 2016/06/29 [QC#8156,ADD]
                            insRecCnt = 0;
                            updRecCnt = 0;
                            errRecCnt = inputRecCnt;
                            totalUpTransactionRecordCounter();
                            resetTransactionRecordCounter();
                            // END 2016/06/29 [QC#8156,ADD]
                            // 2016/10/28 CSA-QC#15035 Add Start
                            setErrMsg(insRowNumList);
                            setErrMsg(updRowNumList);
                        }

                        insRowNumList.clear();
                        updRowNumList.clear();
                        // 2018/07/02 QC#26423 Add Start
                        insLocInfMsg.clear();
                        insLocInfMsg.NMZC001002PMsg.setValidCount(0);
                        updLocInfMsg.clear();
                        updLocInfMsg.NMZC001002PMsg.setValidCount(0);
                        // 2018/07/02 QC#26423 Add End
                        isSuccess = true;
                        // 2016/10/28 CSA-QC#15035 Add End
                    }

                    // 2018/07/02 QC#26423 Add Start
                    if (checkRCVKey(wrkTMsg)) {
                        isSuccess = false;
                        dsAcctNumPrev = rsWrk.getString(DS_ACCT_NUM);
                        rowNumPrev = wrkTMsg.upldCsvRqstRowNum.getValue();
                        continue;
                    }
                    // 2018/07/02 QC#26423 Add End

                    idx = 0;
                    // checkAccount = true;
                    inWrkMap.put(PARAM_DS_ACCT_NUM, dsAcctNum);
                    // inWrkMap.put(PARAM_LOC_PRIM_FLG,
                    // rsWrk.getString(LOC_PRIM_FLG));
                    inWrkMap.put(PARAM_LOC_PRIM_FLG, ZYPConstant.FLG_ON_Y);
                    stmtRcv = ssmLLClient.createPreparedStatement(CON_WRK, inWrkMap, execParam);
                    rsRcv = stmtRcv.executeQuery();

                    // 2018/07/03 QC#26423 Add Start
                    String locNumBase = rsWrk.getString(LOC_NUM);
                    // 2018/07/02 QC#26423 Add End
                    while (rsRcv.next()) {
                        locNumBfr = rsRcv.getString(LOC_NUM);
                        if (2 > rsRcv.getInt(COUNT)) {
                            // 2018/07/03 QC#26423 Mod Start
                            // if (null != locNumBfr && locNumBfr.equals(locNum)) {
                            if (null != locNumBfr && locNumBfr.equals(locNum) && locNumBfr.equals(locNumBase)) {
                            // 2018/07/03 QC#26423 Mod End
                                this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8404E, null);
                                // 2016/10/28 CSA-QC#15035 Mod Start
                                isSuccess = false;
                                // checkAccount = false;
                                // errCount++;
                                // 2016/10/28 CSA-QC#15035 Mod End
                                break;
                            }
                            locNum = rsRcv.getString(LOC_NUM);
                        } else {
                            // 2018/07/03 QC#26423 Add Start
                            if (ZYPConstant.FLG_ON_Y.equals(rsWrk.getString(LOC_PRIM_FLG))) {
                            // 2018/07/03 QC#26423 Add End
                                this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8403E, null);
                                // 2016/10/28 CSA-QC#15035 Mod Start
                                isSuccess = false;
                                // checkAccount = false;
                                // errCount++;
                                // 2016/10/28 CSA-QC#15035 Mod End
                                break;
                            // 2018/07/03 QC#26423 Add Start
                            }
                            // 2018/07/03 QC#26423 Add End
                        }
                    }
                // 2018/07/02 QC#26423 Del Start
                // }
                // 2018/07/02 QC#26423 Del End
                dsAcctNumPrev = rsWrk.getString(DS_ACCT_NUM);
                rowNumPrev = wrkTMsg.upldCsvRqstRowNum.getValue();
                // 2016/12/21 CSA-QC#16798 Del Start
                // 2016/10/28 CSA-QC#15035 Add Start
                // if (!hasValue(rsWrk.getString(LOC_NUM))) {
                //     insRowNumList.add(rowNumPrev);
                // } else {
                //     updRowNumList.add(rowNumPrev);
                // }
                // 2016/10/28 CSA-QC#15035 Add End
                // 2016/12/21 CSA-QC#16798 Del End

                // 2016/10/28 CSA-QC#15035 Mod Start
                if (!isSuccess) {
                // if (!checkAccount) {
                // 2016/10/28 CSA-QC#15035 Mod End
                    continue;
                }

                // getAccountLocation
                Map<String, Object> inAccntMap = new HashMap<String, Object>();
                inAccntMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
                inAccntMap.put(PARAM_DS_ACCT_NUM, dsAcctNum);
                // 2018/07/02 QC#26423 Add Start
                execParam.setFetchSize(SINGLE_FETCH_SIZE);
                // 2018/07/02 QC#26423 Add End
                // AccountCheck
                if (!hasValue(rsWrk.getString(LOC_NUM))) {
                    stmtAc = ssmLLClient.createPreparedStatement(GET_ACC, inAccntMap, execParam);
                    rsAc = stmtAc.executeQuery();
                    // insert
                    insertFlag = true;
                    if (!rsAc.next()) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8411E, dsAcctNum);
                        // 2016/10/28 CSA-QC#15035 Mod Start
                        isSuccess = false;
                        // errCount++;
                        // 2016/10/28 CSA-QC#15035 Mod End
                        continue;
                    }
                    // LocationCheck
                } else {
                    inAccntMap.put(PARAM_LOC_NUM, rsWrk.getString(LOC_NUM));
                    // -- Add Start QC#9073 2016/05/26 --
                    inAccntMap.put(MAX_DATE, MAX_DATE_VALUE);
                    // ----------------------------------
                    stmtAc = ssmLLClient.createPreparedStatement(GET_LOC, inAccntMap, execParam);
                    rsAc = stmtAc.executeQuery();
                    // update
                    insertFlag = false;
                    if (!rsAc.next()) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8412E, dsAcctNum);
                        // 2016/10/28 CSA-QC#15035 Mod Start
                        isSuccess = false;
                        // errCount++;
                        // 2016/10/28 CSA-QC#15035 Mod End
                        continue;
                    }
                }
                // 2018/07/02 QC#26423 Add Start
                execParam.setFetchSize(MAX_FETCH_SIZE);
                // 2018/07/02 QC#26423 Add End
                // QC#2674
                // if (rsWrk.getString(FIRST_LINE_ADDR) != null &&
                // SIZE_30 <
                // rsWrk.getString(FIRST_LINE_ADDR).length()) {
                // this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(),
                // NMAM8405E, FIRST_LINE_ADDR);
                // errCount++;
                // continue;
                // } else if (rsWrk.getString(SCD_LINE_ADDR) != null
                // && SIZE_30 <
                // rsWrk.getString(SCD_LINE_ADDR).length()) {
                // this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(),
                // NMAM8405E, SCD_LINE_ADDR);
                // errCount++;
                // continue;
                // } else if (rsWrk.getString(THIRD_LINE_ADDR) != null
                // && SIZE_30 <
                // rsWrk.getString(THIRD_LINE_ADDR).length()) {
                // this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(),
                // NMAM8405E, THIRD_LINE_ADDR);
                // errCount++;
                // continue;
                // } else if (rsWrk.getString(FRTH_LINE_ADDR) != null
                // && SIZE_30 <
                // rsWrk.getString(FRTH_LINE_ADDR).length()) {
                // this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(),
                // NMAM8405E, FRTH_LINE_ADDR);
                // errCount++;
                // continue;
                // }
                String billToEffFromDtTxt = null;
                String billToEffThruDtTxt = null;
                String shipToEffFromDtTxt = null;
                String shipToEffThruDtTxt = null;
                if (null != rsWrk.getString(BILL_TO_EFF_FROM_DT_TXT)) {
                    billToEffFromDtTxt = dateFormat(wrkTMsg.upldCsvRqstRowNum.getValue(), rsWrk, rsWrk.getString(BILL_TO_EFF_FROM_DT_TXT), BILL_TO_EFF_FROM_DT_TXT);
                    if (!ZYPCommonFunc.hasValue(billToEffFromDtTxt)) {
                        // 2016/10/28 CSA-QC#15035 Add
                        isSuccess = false;
                        continue;
                    }
                }
                if (null != rsWrk.getString(BILL_TO_EFF_THRU_DT_TXT)) {
                    billToEffThruDtTxt = dateFormat(wrkTMsg.upldCsvRqstRowNum.getValue(), rsWrk, rsWrk.getString(BILL_TO_EFF_THRU_DT_TXT), BILL_TO_EFF_THRU_DT_TXT);
                    if (!ZYPCommonFunc.hasValue(billToEffThruDtTxt)) {
                        // 2016/10/28 CSA-QC#15035 Add
                        isSuccess = false;
                        continue;
                    }
                }
                if (null != rsWrk.getString(SHIP_TO_EFF_FROM_DT_TXT)) {
                    shipToEffFromDtTxt = dateFormat(wrkTMsg.upldCsvRqstRowNum.getValue(), rsWrk, rsWrk.getString(SHIP_TO_EFF_FROM_DT_TXT), SHIP_TO_EFF_FROM_DT_TXT);
                    if (!ZYPCommonFunc.hasValue(shipToEffFromDtTxt)) {
                        // 2016/10/28 CSA-QC#15035 Add
                        isSuccess = false;
                        continue;
                    }
                }
                if (null != rsWrk.getString(SHIP_TO_EFF_THRU_DT_TXT)) {
                    shipToEffThruDtTxt = dateFormat(wrkTMsg.upldCsvRqstRowNum.getValue(), rsWrk, rsWrk.getString(SHIP_TO_EFF_THRU_DT_TXT), SHIP_TO_EFF_THRU_DT_TXT);
                    if (!ZYPCommonFunc.hasValue(shipToEffThruDtTxt)) {
                        // 2016/10/28 CSA-QC#15035 Add
                        isSuccess = false;
                        continue;
                    }
                }

                // 2017/10/23 QC#21559 add start
                if (ZYPCommonFunc.hasValue(rsWrk.getString(SHIP_TO_AVAL_FLG)) && rsWrk.getString(SHIP_TO_AVAL_FLG).equals(ZYPConstant.FLG_ON_Y) && ZYPCommonFunc.hasValue(rsWrk.getString(RELN_BILL_TO_CUST_CD))) {
                    BILL_TO_CUSTTMsg billMsg = new BILL_TO_CUSTTMsg();
                    billMsg.setSQLID("001");
                    billMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                    billMsg.setConditionValue("billToCustCd01", rsWrk.getString(RELN_BILL_TO_CUST_CD));
                    billMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
                    BILL_TO_CUSTTMsgArray billMsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(billMsg);

                    if (billMsgArray == null || billMsgArray.length() <= 0) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8111E, LABEL_RELN_BILL_TO);
                        isSuccess = false;
                        continue;
                    }

                    // 2017/11/07 QC#21559-2 add start
                    if (!rsWrk.getString(DS_ACCT_NUM).equals(billMsgArray.no(0).sellToCustCd.getValue())) {
                    // 2017/11/07 QC#21559-2 add end
                        Map<String, Object> getRelnPrm = new HashMap<String, Object>();
                        getRelnPrm.put("glblCmpyCd", glblCmpyCd);
                        getRelnPrm.put("dsAcctNum", rsWrk.getString(DS_ACCT_NUM));
                        getRelnPrm.put("relnDsAcctNum", billMsgArray.no(0).sellToCustCd.getValue());
                        getRelnPrm.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
                        getRelnPrm.put("dsAcctRelnTp", DS_ACCT_RELN_TP.RELATED_ACCOUNT);
                        getRelnPrm.put("procDate", this.procDt);
                        getRelnPrm.put("maxDate", MAX_DATE_VALUE);

                        int relnCount = (Integer) this.ssmBatchClient.queryObject("getAcctReln", getRelnPrm);

                        if (relnCount <= 0) {
                            this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8111E, LABEL_ACCT_RELN);
                            isSuccess = false;
                            continue;
                        }
                    }
                }
                // 2017/10/23 QC#21559 add end

                // 2016/10/28 CSA-QC#15035 Mod Start
                if (!checkData(rsWrk, wrkTMsg.upldCsvRqstRowNum.getValue(), isSuccess)) {
                    isSuccess = false;
                    continue;
                }
                // checkData(rsWrk, wrkTMsg.upldCsvRqstRowNum.getValue());
                // 2016/10/28 CSA-QC#15035 Mod End

                // insertMode
                String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
                if (insertFlag) {
                    if (ZYPConstant.FLG_ON_Y.equals(rsWrk.getString(LOC_ACTV_FLG))) {
                        statusCode = RGTN_STS.READY_FOR_ORDER_TAKING;
                    } else {
                        statusCode = RGTN_STS.PENDING_COMPLETION;
                    }
                    locationStartDate = salesDate;
                    locationEndDate = null;
                    // updateMode
                } else {
                    int salesDt = Integer.parseInt(salesDate);
                    String active = null;
                    if (ZYPConstant.FLG_OFF_N.equals(rsWrk.getString(LOC_ACTV_FLG))) {
                        if (salesDt >= rsAc.getInt(EFF_FROM_DT) && salesDt <= rsAc.getInt(EFF_THRU_DT) && RGTN_STS.READY_FOR_ORDER_TAKING.equals(rsAc.getString(RGTN_STS_CD))) {
                            // statusCode = RGTN_STS.TERMINATED; //
                            // 2016/06/14 S21_NA#9814 Del Start
                            locationEndDate = ZYPDateUtil.addDays(rsAc.getString(EFF_THRU_DT), -1);
                            active = ZYPConstant.FLG_ON_Y;
                        } else {
                            active = ZYPConstant.FLG_OFF_N;
                        }
                        statusCode = RGTN_STS.TERMINATED; // 2016/06/14
                        // S21_NA#9814
                        // Add Start
                    } else {
                        if (salesDt > rsAc.getInt(EFF_THRU_DT)) {
                            // statusCode =
                            // RGTN_STS.READY_FOR_ORDER_TAKING; //
                            // 2016/06/14 S21_NA#9814 Del Start
                            locationEndDate = BLANK;
                            active = ZYPConstant.FLG_OFF_N;
                        } else {
                            active = ZYPConstant.FLG_ON_Y;
                        }
                        statusCode = RGTN_STS.READY_FOR_ORDER_TAKING; // 2016/06/14
                        // S21_NA#9814
                        // Add
                        // Start
                    }
                    locationStartDate = null;
                    boolean skipData = true;
                    String billTo = null;
                    String shipTo = null;
                    String primary = null;
                    if (rsAc.getString(BTC_EFF_FROM_DT) != null) {
                        billTo = ZYPConstant.FLG_ON_Y;
                    } else {
                        billTo = ZYPConstant.FLG_OFF_N;
                    }
                    if (rsAc.getString(STC_EFF_FROM_DT) != null) {
                        shipTo = ZYPConstant.FLG_ON_Y;
                    } else {
                        shipTo = ZYPConstant.FLG_OFF_N;
                    }
                    if (rsAc.getString(PTY_LOC_PK) != null) {
                        primary = ZYPConstant.FLG_ON_Y;
                    } else {
                        primary = ZYPConstant.FLG_OFF_N;
                    }
                    skipData = checkDiff(rsWrk, rsAc, billTo, shipTo, primary, active);
                    if (skipData) {
                        continue;
                    }
                }

                // 2016/10/28 CSA-QC#15035 Mod Start
                if (insertFlag) {
                    insRowNumList.add(rowNumPrev); // 2016/12/21 QC#16798 Add
                    insLocInfMsg = setInfMsg(rsWrk, rsAc, insertFlag, insRecCnt, insLocInfMsg);
                    setValue(insLocInfMsg.slsDt, salesDate);
                    setValue(insLocInfMsg.rgtnStsCd, statusCode);
                    setValue(insLocInfMsg.NMZC001002PMsg.no(insRecCnt).effFromDt, locationStartDate);
                    setValue(insLocInfMsg.NMZC001002PMsg.no(insRecCnt).effThruDt, locationEndDate);
                    setValue(insLocInfMsg.NMZC001002PMsg.no(insRecCnt).effFromDt_B, billToEffFromDtTxt);
                    setValue(insLocInfMsg.NMZC001002PMsg.no(insRecCnt).effThruDt_B, billToEffThruDtTxt);
                    setValue(insLocInfMsg.NMZC001002PMsg.no(insRecCnt).effFromDt_S, shipToEffFromDtTxt);
                    setValue(insLocInfMsg.NMZC001002PMsg.no(insRecCnt).effThruDt_S, shipToEffThruDtTxt);
                    insRecCnt++;
                } else {
                    updRowNumList.add(rowNumPrev); // 2016/12/21 QC#16798 Add
                    updLocInfMsg = setInfMsg(rsWrk, rsAc, insertFlag, updRecCnt, updLocInfMsg);
                    setValue(updLocInfMsg.slsDt, salesDate);
                    setValue(updLocInfMsg.rgtnStsCd, statusCode);
                    setValue(updLocInfMsg.NMZC001002PMsg.no(updRecCnt).effFromDt, locationStartDate);
                    setValue(updLocInfMsg.NMZC001002PMsg.no(updRecCnt).effThruDt, locationEndDate);
                    setValue(updLocInfMsg.NMZC001002PMsg.no(updRecCnt).effFromDt_B, billToEffFromDtTxt);
                    setValue(updLocInfMsg.NMZC001002PMsg.no(updRecCnt).effThruDt_B, billToEffThruDtTxt);
                    setValue(updLocInfMsg.NMZC001002PMsg.no(updRecCnt).effFromDt_S, shipToEffFromDtTxt);
                    setValue(updLocInfMsg.NMZC001002PMsg.no(updRecCnt).effThruDt_S, shipToEffThruDtTxt);
                    updRecCnt++;
                }
                idx++;


                // locInfMsg = setInfMsg(rsWrk, rsAc, insertFlag, idx, locInfMsg);
                // setValue(locInfMsg.slsDt, salesDate);
                // setValue(locInfMsg.rgtnStsCd, statusCode);
                // setValue(locInfMsg.NMZC001002PMsg.no(idx).effFromDt, locationStartDate);
                // setValue(locInfMsg.NMZC001002PMsg.no(idx).effThruDt, locationEndDate);
                // setValue(locInfMsg.NMZC001002PMsg.no(idx).effFromDt_B, billToEffFromDtTxt);
                // setValue(locInfMsg.NMZC001002PMsg.no(idx).effThruDt_B, billToEffThruDtTxt);
                // setValue(locInfMsg.NMZC001002PMsg.no(idx).effFromDt_S, shipToEffFromDtTxt);
                // setValue(locInfMsg.NMZC001002PMsg.no(idx).effThruDt_S, shipToEffThruDtTxt);
                // idx++;

                // START 2016/06/29 [QC#8156,ADD]
//                if (insertFlag) {
//                    insRecCnt++;
//                } else {
//                    updRecCnt++;
//                }
                // END 2016/06/29 [QC#8156,ADD]
                // 2016/10/28 CSA-QC#15035 Mod End
            }

            // 2016/10/28 CSA-QC#15035 Mod Start
            // 2018/07/02 QC#26423 Mod Start
            // if (isSuccess) {
            if (wrkTMsg != null && isSuccess) {
            // 2018/07/02 QC#26423 Mod End
                if (callApiNMZC001001(insLocInfMsg, wrkTMsg.upldCsvRqstRowNum.getValue(), insRowNumList)) {
                    // 2017/10/18 QC#21514 add start
                    setMsgLocBillShipCd(insLocInfMsg, insRowNumList);
                    // 2017/10/18 QC#21514 add end
                    setMsgFromNMZC001002(insLocInfMsg, xxMsgIdListPMsg, updtWrkTMsg);
                } else {
                    isSuccess = false;
                }
    
                if (callApiNMZC001001(updLocInfMsg, wrkTMsg.upldCsvRqstRowNum.getValue(), updRowNumList)) {
                    setMsgFromNMZC001002(updLocInfMsg, xxMsgIdListPMsg, updtWrkTMsg);
                } else {
                    isSuccess = false;
                }
            }

            //            if (locInfMsg.NMZC001002PMsg.getValidCount() > 0 && callApiNMZC001001(locInfMsg, wrkTMsg.upldCsvRqstRowNum.getValue(), rowNumList)) {
//                for (int i = 0; i < locInfMsg.NMZC001002PMsg.getValidCount(); i++) {
//                    NMZC001002PMsg linePrm = locInfMsg.NMZC001002PMsg.no(i);
//                    for (int cnt = 0; cnt < linePrm.xxMsgIdList.getValidCount(); cnt++) {
//                        xxMsgIdListPMsg = linePrm.xxMsgIdList.no(cnt);
//                        if (NMZM0179W.equals(xxMsgIdListPMsg.xxMsgId.getValue())) {
//                            if (PARAM_FRTH_LINE_ADDR.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
//                                setValue(wrkTMsg.firstLineAddr, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
//                            } else if (PARAM_SCD_LINE_ADDR.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
//                                setValue(wrkTMsg.scdLineAddr, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
//                            } else if (PARAM_CTY_ADDR.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
//                                setValue(wrkTMsg.ctyAddr, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
//                            } else if (PARAM_ST_CD.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
//                                setValue(wrkTMsg.stCd, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
//                            } else if (PARAM_POST_CD.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
//                                setValue(wrkTMsg.postCd, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
//                            } else if (PARAM_CNTY_NM.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
//                                setValue(wrkTMsg.cntyNm, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
//                            }
//                            S21FastTBLAccessor.update(wrkTMsg);
//                        }
//                    }
//                }
                // 2016/10/28 CSA-QC#15035 Mod End
            if (isSuccess) {
                commit();
                // START 2016/06/29 [QC#8156,ADD]
                this.errRecCnt = this.inputRecCnt - this.insRecCnt - this.updRecCnt;
                totalUpTransactionRecordCounter();
                resetTransactionRecordCounter();
                // END 2016/06/29 [QC#8156,ADD]
            } else {
                rollback();
                // 2016/10/28 CSA-QC#15035 Add Start
                setErrMsg(insRowNumList);
                setErrMsg(updRowNumList);
                // 2016/10/28 CSA-QC#15035 Add End
                // START 2016/06/29 [QC#8156,ADD]
                insRecCnt = 0;
                updRecCnt = 0;
                errRecCnt = inputRecCnt;
                totalUpTransactionRecordCounter();
                resetTransactionRecordCounter();
                // END 2016/06/29 [QC#8156,ADD]
            }
            if (wrkTMsg != null) {
                S21FastTBLAccessor.update(wrkTMsg);
            }
            // mod start 2016/09/27 CSA QC#14721
            if (errRecCntFile == 0) {
            // mod end 2016/09/27 CSA QC#14721
                batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.NOMAL_END);
                // 2016/04/20 CSA-QC#7360 Add Start
                if (warnFlg) {
                    this.messenger.addMessageForFile(NMAM8463E, null);

                    // START 2016/06/16 [QC#8156,MOD]
                    // this.messenger.uploadMessage();
                    // }
                    // 2016/04/20 CSA-QC#7360 Add End
// START 2016/06/29 [QC#8156,DEL]
//                } else {
//                    this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(this.insRecCntFile, this.updRecCntFile, this.errRecCntFile));
// END 2016/06/29 [QC#8156,DEL]
                }
//                this.messenger.uploadMessage();
                // END 2016/06/16 [QC#8156,MOD]
            } else {
// START 2016/06/29 [QC#8156,DEL]
                // START 2016/06/16 [QC#8156,MOD]
                // procCount = 0;
                // 2016/04/20 CSA-QC#7360 Add Start
                // this.messenger.addMessageForFile(NMAM8457E, null);
//                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(this.insRecCntFile, this.updRecCntFile, this.errRecCntFile));
                // 2016/04/20 CSA-QC#7360 Add End
//                this.messenger.uploadMessage();
// END 2016/06/29 [QC#8156,DEL]
                // Del Start 2018/07/18 QC#26407
                //procCount = 0;
                // Del End 2018/07/18 QC#26407
                // END 2016/06/16 [QC#8156,MOD]
                termCd = TERM_CD.WARNING_END;
                batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.WARING_END);
            }

            // START 2016/06/29 [QC#8156,MOD]
            if (this.errRecCntFile > 0) {
                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(this.insRecCntFile, this.updRecCntFile, this.errRecCntFile));
            } else if (this.insRecCntFile > 0 || this.updRecCntFile > 0) {
                this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(this.insRecCntFile, this.updRecCntFile, this.errRecCntFile));
            }
            this.messenger.uploadMessage();
            // END 2016/06/29 [QC#8156,MOD]

            commit();
        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtWrk, rsWrk);
        }
    }

    private boolean checkData(ResultSet rsWrk, BigDecimal upldCsvRqstRowNum, boolean isSuccess) throws SQLException {
        if (!ZYPConstant.FLG_ON_Y.equals(rsWrk.getString(BILL_TO_AVAL_FLG)) && !ZYPConstant.FLG_OFF_N.equals(rsWrk.getString(BILL_TO_AVAL_FLG))) {
            // Mod Start 2018/07/18 QC#26407
            //this.messenger.addMessageForRecord(upldCsvRqstRowNum, NMAM8368E, null);
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NMAM8684E, LABEL_BILL_TO_AVAL_FLG);
            // Mod End 2018/07/18 QC#26407
            // 2016/10/28 CSA-QC#15035 Mod Start
            isSuccess = false;
            // errCount++;
            // 2016/10/28 CSA-QC#15035 Mod End
        }
        if (!ZYPConstant.FLG_ON_Y.equals(rsWrk.getString(SHIP_TO_AVAL_FLG)) && !ZYPConstant.FLG_OFF_N.equals(rsWrk.getString(SHIP_TO_AVAL_FLG))) {
            // Mod Start 2018/07/18 QC#26407
            //this.messenger.addMessageForRecord(upldCsvRqstRowNum, NMAM8368E, null);
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NMAM8684E, LABEL_SHIP_TO_AVAL_FLG);
            // Mod End 2018/07/18 QC#26407
            // 2016/10/28 CSA-QC#15035 Mod Start
            isSuccess = false;
            // errCount++;
            // 2016/10/28 CSA-QC#15035 Mod End 
        }
        if (!ZYPConstant.FLG_ON_Y.equals(rsWrk.getString(LOC_PRIM_FLG)) && !ZYPConstant.FLG_OFF_N.equals(rsWrk.getString(LOC_PRIM_FLG))) {
            // Mod Start 2018/07/18 QC#26407
            //this.messenger.addMessageForRecord(upldCsvRqstRowNum, NMAM8368E, null);
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NMAM8684E, LABEL_LOC_PRIM_FLG);
            // Mod End 2018/07/18 QC#26407
            // 2016/10/28 CSA-QC#15035 Mod Start
            isSuccess = false;
            // errCount++;
            // 2016/10/28 CSA-QC#15035 Mod End
        }
        if (!ZYPConstant.FLG_ON_Y.equals(rsWrk.getString(LOC_ACTV_FLG)) && !ZYPConstant.FLG_OFF_N.equals(rsWrk.getString(LOC_ACTV_FLG))) {
            // Mod Start 2018/07/18 QC#26407
            //this.messenger.addMessageForRecord(upldCsvRqstRowNum, NMAM8368E, null);
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NMAM8684E, LABEL_LOC_ACTV_FLG);
            // Mod End 2018/07/18 QC#26407
            // 2016/10/28 CSA-QC#15035 Mod Start
            isSuccess = false;
            // errCount++;
            // 2016/10/28 CSA-QC#15035 Mod End
        }
        if (ZYPConstant.FLG_OFF_N.equals(rsWrk.getString(BILL_TO_AVAL_FLG)) && ZYPConstant.FLG_OFF_N.equals(rsWrk.getString(SHIP_TO_AVAL_FLG))) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NMAM8413E, null);
            // 2016/10/28 CSA-QC#15035 Mod Start
            isSuccess = false;
            // errCount++;
            // 2016/10/28 CSA-QC#15035 Mod End
        }
        if (2 == rsWrk.getString(CTRY_CD).length()) {
            CTRYTMsg cTRYTMsg = getCtry(rsWrk.getString(CTRY_CD));
            if (cTRYTMsg == null) {
                this.messenger.addMessageForRecord(upldCsvRqstRowNum, NMAM8414E, CTRY_CD);
                // 2016/10/28 CSA-QC#15035 Mod Start
                isSuccess = false;
                //  errCount++;
                // 2016/10/28 CSA-QC#15035 Mod End
            }
        } else {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NMAM8414E, CTRY_CD);
            // 2016/10/28 CSA-QC#15035 Mod Start
            isSuccess = false;
            // errCount++;
            // 2016/10/28 CSA-QC#15035 Mod End
        }
        if (2 == rsWrk.getString(ST_CD).length()) {
            STTMsg stTMsg = getSt(rsWrk.getString(ST_CD));
            if (stTMsg == null) {
                this.messenger.addMessageForRecord(upldCsvRqstRowNum, NMAM8414E, ST_CD);
                // 2016/10/28 CSA-QC#15035 Mod Start
                isSuccess = false;
                // errCount++;
                // 2016/10/28 CSA-QC#15035 Mod End
            }
        } else {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NMAM8414E, ST_CD);
            // 2016/10/28 CSA-QC#15035 Mod Start
            isSuccess = false;
            // errCount++;
            // 2016/10/28 CSA-QC#15035 Mod End
        }
        if (NUM_8 >= rsWrk.getString(LINE_BIZ_TP_CD).length()) {
            LINE_BIZ_TPTMsg lineBizTpTMsg = getLineBizTp(rsWrk.getString(LINE_BIZ_TP_CD));
            if (lineBizTpTMsg == null) {
                this.messenger.addMessageForRecord(upldCsvRqstRowNum, NMAM8414E, LINE_BIZ_TP_CD);
                // 2016/10/28 CSA-QC#15035 Mod Start
                isSuccess = false;
                // errCount++;
                // 2016/10/28 CSA-QC#15035 Mod End
            }
        } else {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NMAM8414E, LINE_BIZ_TP_CD);
            // 2016/10/28 CSA-QC#15035 Mod Start
            isSuccess = false;
            // errCount++;
            // 2016/10/28 CSA-QC#15035 Mod End
        }
        return isSuccess;
    }

    private boolean checkDiff(ResultSet rsWrk, ResultSet rsAc, String billTo, String shipTo, String primary, String active) throws SQLException {
        boolean skipData = true;

        if (!getString(rsWrk, DS_ACCT_NUM).equals(getString(rsAc, DS_ACCT_NUM))) {
            skipData = false;
        // 2016/06/24 QC#10469 Del Start
//        } else if (!getString(rsWrk, DS_ACCT_NM).equals(getString(rsAc, DS_ACCT_NM))) {
//            skipData = false;
        // 2016/06/24 QC#10469 Del End
        } else if (!getString(rsWrk, CTRY_CD).equals(getString(rsAc, CTRY_CD))) {
            skipData = false;
        } else if (!getString(rsWrk, FIRST_LINE_ADDR).equals(getString(rsAc, FIRST_LINE_ADDR))) {
            skipData = false;
        } else if (!getString(rsWrk, SCD_LINE_ADDR).equals(getString(rsAc, SCD_LINE_ADDR))) {
            skipData = false;
        } else if (!getString(rsWrk, THIRD_LINE_ADDR).equals(getString(rsAc, THIRD_LINE_ADDR))) {
            skipData = false;
        } else if (!getString(rsWrk, FRTH_LINE_ADDR).equals(getString(rsAc, FRTH_LINE_ADDR))) {
            skipData = false;
        } else if (!getString(rsWrk, CTY_ADDR).equals(getString(rsAc, CTY_ADDR))) {
            skipData = false;
        } else if (!getString(rsWrk, ST_CD).equals(getString(rsAc, ST_CD))) {
            skipData = false;
        } else if (!getString(rsWrk, POST_CD).equals(getString(rsAc, POST_CD))) {
            skipData = false;
        } else if (!getString(rsWrk, CNTY_NM).equals(getString(rsAc, CNTY_NM))) {
            skipData = false;
        } else if (!getString(rsWrk, PROV_NM).equals(getString(rsAc, PROV_NM))) {
            skipData = false;
        } else if (!getString(rsWrk, BILL_TO_AVAL_FLG).equals(billTo)) {
            skipData = false;
        } else if (!getString(rsWrk, SHIP_TO_AVAL_FLG).equals(shipTo)) {
            skipData = false;
        } else if (!getString(rsWrk, BILL_TO_EFF_FROM_DT_TXT).equals(getString(rsAc, BTC_EFF_FROM_DT))) {
            skipData = false;
        } else if (!getString(rsWrk, BILL_TO_EFF_THRU_DT_TXT).equals(getString(rsAc, BTC_EFF_THRU_DT))) {
            skipData = false;
        } else if (!getString(rsWrk, SHIP_TO_EFF_FROM_DT_TXT).equals(getString(rsAc, STC_EFF_FROM_DT))) {
            skipData = false;
        } else if (!getString(rsWrk, SHIP_TO_EFF_THRU_DT_TXT).equals(getString(rsAc, STC_EFF_THRU_DT))) {
            skipData = false;
        } else if (!getString(rsWrk, DS_LOC_NM).equals(getString(rsAc, LOC_NM))) {
            skipData = false;
        } else if (!getString(rsWrk, LINE_BIZ_TP_CD).equals(getString(rsAc, LINE_BIZ_TP_CD))) {
            skipData = false;
        } else if (!getString(rsWrk, LOC_PRIM_FLG).equals(primary)) {
            skipData = false;
        } else if (!getString(rsWrk, LOC_ACTV_FLG).equals(active)) {
            skipData = false;
        } else if (!getString(rsWrk, LOC_NUM).equals(getString(rsAc, LOC_NUM))) {
            skipData = false;
        }
        return skipData;
    }

    private boolean callApiNMZC001001(NMZC001001PMsg pMsg, BigDecimal upldCsvRqstRowNum, List<BigDecimal> rowNumList) {
        // 2016/10/28 CSA-QC#15035 Add Start
        if (pMsg == null || pMsg.NMZC001002PMsg.getValidCount() == 0) {
            return true;
        }
        // 2016/10/28 CSA-QC#15035 Add End

        NMZC001001 api = new NMZC001001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        List<String> errList = S21ApiUtil.getXxMsgIdList(pMsg);

        boolean errExist = false;
        // 2018/12/19 QC#29134 Rollback fix on 2018/11/28 
        if (!errList.isEmpty()) {
            for (String xxMsgId : errList) {
                // 2016/04/20 CSA-QC#7360 Mod Start
                if (xxMsgId.endsWith("E") || xxMsgId.endsWith("W")) {
                    addMessageForRecordList(rowNumList, xxMsgId, null);
                    if (xxMsgId.endsWith("E")) {
                        errExist = true;
                    } else {
                        warnFlg = true;
                    }
                }
                // 2016/04/20 CSA-QC#7360 Mod End
            }
        } else {
            HashMap<String, Integer> chkDupMsgMap = new HashMap<String, Integer>();
            for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
                NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
                errList = S21ApiUtil.getXxMsgIdList(linePrm);
                int j = 0;
                for (String xxMsgId : errList) {
                    if (!chkDupMsgMap.containsKey(xxMsgId)) {
                     // 2016/04/20 CSA-QC#7360 Mod Start
                        if (xxMsgId.endsWith("E") || xxMsgId.endsWith("W")) {
                            // 2016/12/21 CSA-QC#16798 Mod Start 
                            // addMessageForRecordList(rowNumList, xxMsgId, null);
                            this.messenger.addMessageForRecord(rowNumList.get(i), xxMsgId, null);
                            // 2016/12/21 CSA-QC#16798 Mod End 
                            if (xxMsgId.endsWith("E")) {
                                errExist = true;
                            } else {
                                warnFlg = true;
                            }
                        }
                        // 2016/04/20 CSA-QC#7360 Mod End
                        chkDupMsgMap.put(xxMsgId, j);
                        j++;
                    }
                }
            }
        }
        if (errExist) {
        // 2016/10/28 CSA-QC#15035 Delete
        // errCount = errCount + pMsg.NMZC001002PMsg.getValidCount();
            return false;
        } else {
        // 2016/10/28 CSA-QC#15035 Delete
        // procCount = procCount + pMsg.NMZC001002PMsg.getValidCount();
            return true;
        }
    }

    private String dateFormat(BigDecimal upldCsvRqstRowNum, ResultSet rs, String date, String dtNm) {
        String dateFormat = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            Date dt = format.parse(date);
            format.applyPattern("yyyyMMdd");
            dateFormat = format.format(dt);
        } catch (java.text.ParseException e) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NMAM8406E, dtNm);
            // Del Start 2018/07/18 QC#26407
            //errCount++;
            // Del End 2018/07/18 QC#26407
        }
        return dateFormat;
    }

    private CTRYTMsg getCtry(String searchKey) {
        CTRYTMsg inMsg = new CTRYTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.ctryCd, searchKey);
        inMsg = (CTRYTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return null;
        }
        return inMsg;
    }

    private STTMsg getSt(String searchKey) {
        STTMsg inMsg = new STTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.stCd, searchKey);
        inMsg = (STTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return null;
        }
        return inMsg;
    }

    private LINE_BIZ_TPTMsg getLineBizTp(String searchKey) {
        LINE_BIZ_TPTMsg inMsg = new LINE_BIZ_TPTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.lineBizTpCd, searchKey);
        inMsg = (LINE_BIZ_TPTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return null;
        }
        return inMsg;
    }

    /**
     * @return LOC_INFO_WRKTMsg
     * @throws SQLException
     */
    private NMZC001001PMsg setInfMsg(ResultSet rsWrk, ResultSet rsAc, Boolean insertFlag, int idx, NMZC001001PMsg locInfMsg) throws SQLException {

        if (insertFlag) {
            setValue(locInfMsg.xxModeCd, PROCESS_MODE06);
        } else {
            setValue(locInfMsg.xxModeCd, PROCESS_MODE04);
        }
        setValue(locInfMsg.glblCmpyCd, glblCmpyCd);

        setValue(locInfMsg.dsAcctNum, rsWrk.getString(DS_ACCT_NUM));
        // 2016/06/24 QC#10469 Mod Start
        setValue(locInfMsg.dsAcctNm, rsAc.getString(DS_ACCT_NM));
        // 2016/06/24 QC#10469 Mod End

        setValue(locInfMsg.dsAcctItrlFlg, rsAc.getString(DS_ACCT_ITRL_FLG));
        setValue(locInfMsg.dsAcctClsCd, rsAc.getString(DS_ACCT_CLS_CD));
        setValue(locInfMsg.coaChCd, rsAc.getString(COA_CH_CD));
        setValue(locInfMsg.coaAfflCd, rsAc.getString(COA_AFFL_CD));
        setValue(locInfMsg.dsAcctDlrCd, rsAc.getString(DS_ACCT_DLR_CD));
        setValue(locInfMsg.dsAcctLegalNm, rsAc.getString(DS_ACCT_LEGAL_NM));
        setValue(locInfMsg.dbaNm, rsAc.getString(DBA_NM));
        setValue(locInfMsg.dsAcctUrl, rsAc.getString(DS_ACCT_URL));
        setValue(locInfMsg.NMZC001002PMsg.no(idx).xxPrinFlg, rsWrk.getString(LOC_PRIM_FLG));
        if (!insertFlag) {
            setValue(locInfMsg.NMZC001002PMsg.no(idx).locNum, rsWrk.getString(LOC_NUM));
            setValue(locInfMsg.NMZC001002PMsg.no(idx).addlLocNm, rsAc.getString(ADDL_LOC_NM));
            setValue(locInfMsg.NMZC001002PMsg.no(idx).glnNum, rsAc.getBigDecimal(GLN_NUM));
            setValue(locInfMsg.NMZC001002PMsg.no(idx).primUsgFlg_B, rsAc.getString(PRIM_USG_FLG));
            setValue(locInfMsg.NMZC001002PMsg.no(idx).telNum, rsAc.getString(TEL_NUM));
            setValue(locInfMsg.NMZC001002PMsg.no(idx).faxNum, rsAc.getString(FAX_NUM));
            setValue(locInfMsg.NMZC001002PMsg.no(idx).relnBillToCustCd_S, rsAc.getString(RELN_BILL_TO_CUST_CD));
            setValue(locInfMsg.NMZC001002PMsg.no(idx).primUsgFlg_S, rsAc.getString(PRIM_USG_FLG));
            setValue(locInfMsg.NMZC001002PMsg.no(idx).bigDealNum_S, rsAc.getString(BIG_DEAL_NUM));
            setValue(locInfMsg.NMZC001002PMsg.no(idx).dplStsCd_S, rsAc.getString(DPL_STS_CD));
            setValue(locInfMsg.NMZC001002PMsg.no(idx).coaChCd_S, rsAc.getString(COA_CH_CD));
            setValue(locInfMsg.NMZC001002PMsg.no(idx).coaAfflCd_S, rsAc.getString(COA_AFFL_CD));
        }
        setValue(locInfMsg.NMZC001002PMsg.no(idx).actvFlg, rsWrk.getString(LOC_ACTV_FLG));
        setValue(locInfMsg.NMZC001002PMsg.no(idx).firstLineAddr, rsWrk.getString(FIRST_LINE_ADDR));
        setValue(locInfMsg.NMZC001002PMsg.no(idx).scdLineAddr, rsWrk.getString(SCD_LINE_ADDR));
        setValue(locInfMsg.NMZC001002PMsg.no(idx).thirdLineAddr, rsWrk.getString(THIRD_LINE_ADDR));
        setValue(locInfMsg.NMZC001002PMsg.no(idx).frthLineAddr, rsWrk.getString(FRTH_LINE_ADDR));
        setValue(locInfMsg.NMZC001002PMsg.no(idx).ctyAddr, rsWrk.getString(CTY_ADDR));
        setValue(locInfMsg.NMZC001002PMsg.no(idx).cntyNm, rsWrk.getString(CNTY_NM));
        setValue(locInfMsg.NMZC001002PMsg.no(idx).stCd, rsWrk.getString(ST_CD));
        setValue(locInfMsg.NMZC001002PMsg.no(idx).postCd, rsWrk.getString(POST_CD));
        setValue(locInfMsg.NMZC001002PMsg.no(idx).ctryCd, rsWrk.getString(CTRY_CD));
        setValue(locInfMsg.NMZC001002PMsg.no(idx).locNm, rsWrk.getString(DS_LOC_NM));

        // 2016/06/24 QC#10469 Mod Start
        if (ZYPCommonFunc.hasValue(rsWrk.getString(BILL_TO_AVAL_FLG)) && rsWrk.getString(BILL_TO_AVAL_FLG).equals(ZYPConstant.FLG_ON_Y)) {
            setValue(locInfMsg.NMZC001002PMsg.no(idx).billToCustFlg, ZYPConstant.FLG_ON_Y);
//            setValue(locInfMsg.NMZC001002PMsg.no(idx).lineBizTpCd_B, rsWrk.getString(LINE_BIZ_TP_CD));
        } else {
            setValue(locInfMsg.NMZC001002PMsg.no(idx).billToCustFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(rsWrk.getString(SHIP_TO_AVAL_FLG)) && rsWrk.getString(SHIP_TO_AVAL_FLG).equals(ZYPConstant.FLG_ON_Y)) {
            setValue(locInfMsg.NMZC001002PMsg.no(idx).shipToCustFlg, ZYPConstant.FLG_ON_Y);
//            setValue(locInfMsg.NMZC001002PMsg.no(idx).lineBizTpCd_S, rsWrk.getString(LINE_BIZ_TP_CD));
            // 2017/10/23 QC#21559 add start
            setValue(locInfMsg.NMZC001002PMsg.no(idx).relnBillToCustCd_S, rsWrk.getString(RELN_BILL_TO_CUST_CD));
            // 2017/10/23 QC#21559 add end
        } else {
            setValue(locInfMsg.NMZC001002PMsg.no(idx).shipToCustFlg, ZYPConstant.FLG_OFF_N);
        }
        setValue(locInfMsg.NMZC001002PMsg.no(idx).lineBizTpCd, rsWrk.getString(LINE_BIZ_TP_CD));
        // 2016/06/24 QC#10469 Mod End
        locInfMsg.NMZC001002PMsg.setValidCount(idx + 1);

        return locInfMsg;
    }

    /**
     * @return LOC_INFO_WRKTMsg
     */
    private LOC_INFO_WRKTMsg transferWrk(ResultSet rs) throws SQLException {

        LOC_INFO_WRKTMsg wrkTMsg = new LOC_INFO_WRKTMsg();

        setValue(wrkTMsg.glblCmpyCd, glblCmpyCd);
        setValue(wrkTMsg.dsAcctNum, rs.getString(DS_ACCT_NUM));
        setValue(wrkTMsg.dsAcctNm, rs.getString(DS_ACCT_NM));
        setValue(wrkTMsg.ctryCd, rs.getString(CTRY_CD));
        setValue(wrkTMsg.firstLineAddr, rs.getString(FIRST_LINE_ADDR));
        setValue(wrkTMsg.scdLineAddr, rs.getString(SCD_LINE_ADDR));
        setValue(wrkTMsg.thirdLineAddr, rs.getString(THIRD_LINE_ADDR));
        setValue(wrkTMsg.frthLineAddr, rs.getString(FRTH_LINE_ADDR));
        setValue(wrkTMsg.ctyAddr, rs.getString(CTY_ADDR));
        setValue(wrkTMsg.stCd, rs.getString(ST_CD));
        setValue(wrkTMsg.postCd, rs.getString(POST_CD));
        setValue(wrkTMsg.cntyNm, rs.getString(CNTY_NM));
        setValue(wrkTMsg.provNm, rs.getString(PROV_NM));
        setValue(wrkTMsg.billToAvalFlg, rs.getString(BILL_TO_AVAL_FLG));
        setValue(wrkTMsg.shipToAvalFlg, rs.getString(SHIP_TO_AVAL_FLG));
        setValue(wrkTMsg.billToEffFromDtTxt, rs.getString(BILL_TO_EFF_FROM_DT_TXT));
        setValue(wrkTMsg.billToEffThruDtTxt, rs.getString(BILL_TO_EFF_THRU_DT_TXT));
        // 2016/06/24 QC#10469 Mod Start
        setValue(wrkTMsg.shipToEffFromDtTxt, rs.getString(SHIP_TO_EFF_FROM_DT_TXT));
        // 2016/06/24 QC#10469 Mod End
        setValue(wrkTMsg.shipToEffThruDtTxt, rs.getString(SHIP_TO_EFF_THRU_DT_TXT));
        setValue(wrkTMsg.dsLocNm, rs.getString(DS_LOC_NM));
        setValue(wrkTMsg.lineBizTpCd, rs.getString(LINE_BIZ_TP_CD));
        setValue(wrkTMsg.locPrimFlg, rs.getString(LOC_PRIM_FLG));
        setValue(wrkTMsg.locActvFlg, rs.getString(LOC_ACTV_FLG));
        setValue(wrkTMsg.locNum, rs.getString(LOC_NUM));
        setValue(wrkTMsg.upldCsvRqstPk, rs.getBigDecimal(UPLD_CSV_RQST_PK));
        setValue(wrkTMsg.upldCsvRqstRowNum, rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM));
        setValue(wrkTMsg.upldCsvRqstCmntTxt, rs.getString(UPLD_CSV_RQST_CMNT_TXT));
        // 2017/10/23 QC#21559 add start
        setValue(wrkTMsg.relnBillToCustCd, rs.getString(RELN_BILL_TO_CUST_CD));
        // 2017/10/23 QC#21559 add end

        return wrkTMsg;

    }

    /**
     */
    private boolean checkRCVKey(LOC_INFO_WRKTMsg tMsg) {

        if (S21StringUtil.isEmpty(tMsg.dsAcctNum.getValue())) {
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, DS_ACCT_NUM);
            return true;
        }
        if (S21StringUtil.isEmpty(tMsg.ctryCd.getValue())) {
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, CTRY_CD);
            return true;
        }
        if (S21StringUtil.isEmpty(tMsg.firstLineAddr.getValue())) {
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, FIRST_LINE_ADDR);
            return true;
        }
        if (S21StringUtil.isEmpty(tMsg.ctyAddr.getValue())) {
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, CTY_ADDR);
            return true;
        }
        if (S21StringUtil.isEmpty(tMsg.stCd.getValue())) {
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, ST_CD);
            return true;
        }
        if (S21StringUtil.isEmpty(tMsg.postCd.getValue())) {
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, POST_CD);
            return true;
        }
        if (S21StringUtil.isEmpty(tMsg.billToAvalFlg.getValue())) {
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, BILL_TO_AVAL_FLG);
            return true;
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(tMsg.billToAvalFlg.getValue())) {
                if (S21StringUtil.isEmpty(tMsg.billToEffFromDtTxt.getValue())) {
                    this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, LABEL_BILL_TO_FROM_DT);
                    return true;
                }
            }
        }
        if (S21StringUtil.isEmpty(tMsg.shipToAvalFlg.getValue())) {
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, SHIP_TO_AVAL_FLG);
            return true;
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(tMsg.shipToAvalFlg.getValue())) {
                if (S21StringUtil.isEmpty(tMsg.shipToEffFromDtTxt.getValue())) {
                    this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, LABEL_SHIP_TO_FROM_DT);
                    return true;
                }
            }
        }
        if (S21StringUtil.isEmpty(tMsg.lineBizTpCd.getValue())) {
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, LINE_BIZ_TP_CD);
            return true;
        }
        if (S21StringUtil.isEmpty(tMsg.locPrimFlg.getValue())) {
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, LOC_PRIM_FLG);
            return true;
        }
        if (S21StringUtil.isEmpty(tMsg.locActvFlg.getValue())) {
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, LOC_ACTV_FLG);
            return true;
        }
        return false;
    }

    private LOC_INFO_WRKTMsg setMsgFromNMZC001002(NMZC001001PMsg locInfMsg, NMZC001002_xxMsgIdListPMsg xxMsgIdListPMsg, LOC_INFO_WRKTMsg wrkTMsg) {
        for (int i = 0; i < locInfMsg.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = locInfMsg.NMZC001002PMsg.no(i);
            for (int cnt = 0; cnt < linePrm.xxMsgIdList.getValidCount(); cnt++) {
                xxMsgIdListPMsg = linePrm.xxMsgIdList.no(cnt);
                if (NMZM0179W.equals(xxMsgIdListPMsg.xxMsgId.getValue())) {
                    if (PARAM_FRTH_LINE_ADDR.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
                        setValue(wrkTMsg.firstLineAddr, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
                    } else if (PARAM_SCD_LINE_ADDR.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
                        setValue(wrkTMsg.scdLineAddr, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
                    } else if (PARAM_CTY_ADDR.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
                        setValue(wrkTMsg.ctyAddr, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
                    } else if (PARAM_ST_CD.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
                        setValue(wrkTMsg.stCd, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
                    } else if (PARAM_POST_CD.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
                        setValue(wrkTMsg.postCd, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
                    } else if (PARAM_CNTY_NM.equals(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue())) {
                        setValue(wrkTMsg.cntyNm, xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
                    }
                    S21FastTBLAccessor.update(wrkTMsg);
                }
            }
        }
        return wrkTMsg;
    }

    /**
     * @param fMsg ART10FMsg
     * @return Upload CSV Request PK
     */
    private BigDecimal getUpldCsvReqPk(ART10FMsg fMsg) {

        // UploadCsvRequestPK
        String upldCsvReqPk = fMsg.EZREQCondition.getValue();
        String[] msg = {MSG_UPLD_CSV_RQST_PK };
        String removeDQRequestPK = S21RequestBatchHelper.removeDoubleQuotation(upldCsvReqPk);

        if (S21StringUtil.isEmpty(removeDQRequestPK)) {
            throw new S21AbendException(NMAM8228E, msg);
        }
        if (!EZDCommonFunc.isNumeric(removeDQRequestPK)) {
            throw new S21AbendException(NWBM0097E, msg);
        }
        return new BigDecimal(removeDQRequestPK);
    }

    /**
     * @param fMsg ART10FMsg
     * @return Upload CSV ID
     */
    private String getUpldCsvId(ART10FMsg fMsg) {

        String uploadCsvId = fMsg.EZREQUserKey.getValue();
        if (S21StringUtil.isEmpty(uploadCsvId)) {
            String[] msg = {MSG_UPLD_CSV_ID };
            throw new S21AbendException(NMAM8228E, msg);
        }

        return S21RequestBatchHelper.removeDoubleQuotation(uploadCsvId);
    }

    /**
     * @param upldCsvReqPk upload CSV Request Pk
     * @return UPLD_CSV_RQSTTMsg
     */
    private UPLD_CSV_RQSTTMsg getUpldCSVRqst(BigDecimal upldCsvRqstPk) {

        UPLD_CSV_RQSTTMsg inTMsg = new UPLD_CSV_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.upldCsvRqstPk, upldCsvRqstPk);

        UPLD_CSV_RQSTTMsg outTMsg = (UPLD_CSV_RQSTTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            throw new S21AbendException(ZYPM0181E);
        }

        return outTMsg;
    }

    /**
     * @return GlobalCompanyCode
     */
    private String getGlblCmpyCd() {

        String cmpyCd = this.profile.getGlobalCompanyCode();
        if (S21StringUtil.isEmpty(cmpyCd)) {
            String[] msg = {GLOBAL_COMPANY_CODE };
            throw new S21AbendException(NMAM8228E, msg);
        }

        return cmpyCd;
    }

    /**
     * Get String Value from ResultSet. (With Conversion from Null to
     * "")
     * @param rs ResultSet;
     * @param key String
     * @return String
     */
    private String getString(ResultSet rs, String key) throws SQLException {
        String ret = (String) rs.getString(key);
        if (ZYPCommonFunc.hasValue(ret)) {
            return ret;
        }
        return "";
    }

    /**
     * Add Message For Record List
     * @param uploadCSVRowNumList List<BigDecimal>
     * @param messageCd String
     * @param messageArg String
     */
    private void addMessageForRecordList(List<BigDecimal> uploadCSVRowNumList, String messageCd, String messageArg) {

        for (BigDecimal rowNum : uploadCSVRowNumList) {
            this.messenger.addMessageForRecord(rowNum, messageCd, messageArg);
        }
    }

    // START 2016/06/16 [QC#8156,ADD]
    private static String createResultMessageArg(int insCount, int updCount, int errCount) {
        StringBuilder builder = new StringBuilder();
        if (insCount > 0) {
            builder.append(String.format(RESULT_MSG_INS, insCount));
        }
        if (updCount > 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(String.format(RESULT_MSG_UPD, updCount));
        }
        if (errCount > 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(String.format(RESULT_MSG_ERR, errCount));
        }
        return builder.toString();
    }
    // END 2016/06/16 [QC#8156,ADD]

    // START 2016/06/29 [QC#8156,ADD]
    private void resetTransactionRecordCounter() {
        this.inputRecCnt = 0;
        this.insRecCnt = 0;
        this.updRecCnt = 0;
        this.errRecCnt = 0;
    }

    private void totalUpTransactionRecordCounter() {
        insRecCntFile += insRecCnt;
        updRecCntFile += updRecCnt;
        errRecCntFile += errRecCnt;
    }

    private void resetFileRecordCounter() {
        this.insRecCntFile = 0;
        this.updRecCntFile = 0;
        this.errRecCntFile = 0;
    }
    // END 2016/06/29 [QC#8156,ADD]

    private void setErrMsg (List<BigDecimal> rowNumList) {
        // 2018/07/02 QC#26423 Del Start
        //boolean hasErrMsg = false;
        //for (BigDecimal rowNum : rowNumList) {
        //    for (BigDecimal errRowNum : this.messenger.getRowNums()) {
        //        if (ZYPCommonFunc.hasValue(errRowNum) && rowNum.compareTo(errRowNum) == 0) {
        //            hasErrMsg = true;
        //            break;
        //        }
        //    }

        //    if (hasErrMsg) {
        //        hasErrMsg = false;
        //        continue;
        //    }

        //    this.messenger.addMessageForRecord(rowNum, NMAM8566E, null);
        //}
        // 2018/07/02 QC#26423 Del End
    }

    // 2017/10/18 QC#21514 add start
    private void setMsgLocBillShipCd(NMZC001001PMsg locInfMsg, List<BigDecimal> insRowNumList) {
        for (int i = 0; i < locInfMsg.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = locInfMsg.NMZC001002PMsg.no(i);
            StringBuilder msg = new StringBuilder();
            if (ZYPCommonFunc.hasValue(linePrm.locNum)) {
                msg.append("Insert Loc#:");
                msg.append(linePrm.locNum.getValue());
            } else {
                continue;
            }

            BILL_TO_CUSTTMsg billMsg = new BILL_TO_CUSTTMsg();
            billMsg.setSQLID("060");
            billMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            billMsg.setConditionValue("locNum01", linePrm.locNum.getValue());
            BILL_TO_CUSTTMsgArray billMsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(billMsg);

            if (billMsgArray != null && billMsgArray.length() > 0) {
                msg.append(", Bill To Code:");
                msg.append(billMsgArray.no(0).billToCustCd.getValue());
            }

            SHIP_TO_CUSTTMsg shipMsg = new SHIP_TO_CUSTTMsg();
            shipMsg.setSQLID("048");
            shipMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            shipMsg.setConditionValue("locNum01", linePrm.locNum.getValue());
            SHIP_TO_CUSTTMsgArray shipMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipMsg);

            if (shipMsgArray != null && shipMsgArray.length() > 0) {
                msg.append(", Ship To Code:");
                msg.append(shipMsgArray.no(0).shipToCustCd.getValue());
            }

            this.messenger.addMessageForRecord(insRowNumList.get(i), NYXM0001I, msg.toString());
        }
    }
    // 2017/10/18 QC#21514 add end
}
