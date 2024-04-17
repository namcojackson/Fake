/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB268001;

import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.CELL_DS_CTAC_PNT_VAL_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.CNT_ACCNT_LCTN;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.CNT_PRMRY;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.CTAC_PRIM_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.CTAC_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.DS_ACCT_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.DS_ACCT_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.DS_CTAC_PNT_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.DS_CTAC_PSN_DEPT_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.DS_CTAC_PSN_SALT_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.DS_CTAC_PSN_TTL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.EML_DS_CTAC_PNT_VAL_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.EXTN_DS_CTAC_PNT_VAL_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.EXT_MAX_LENGTH;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.FAX_DS_CTAC_PNT_VAL_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.GET_AVAILABLE_SRVC_MCHN;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.GET_CTAC_TP_PNT_MDN_DFN;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.GET_DS_CTAC_PSN_SALT;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.GET_SRVC_MCHN;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.GET_SVC_CTAC_TP;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.GET_WRK;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.HIT_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.HYPHEN;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.LOC_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.MAX_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.MSG_DESC_CTAC_PRIM_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.MSG_DESC_CTAC_TP;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.MSG_DESC_DS_CTAC_PNT_TP;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.MSG_DESC_DS_CTAC_PSN_DEPT;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.MSG_DESC_DS_CTAC_PSN_SALT;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.MSG_DESC_DS_CTAC_PSN_TTL;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.MSG_DESC_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.MSG_DESC_LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.MSG_DESC_SVC_CTAC_TP;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.MSG_UPLD_CSV_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.MSG_UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM0041E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM8228E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM8347E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM8348E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM8368E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM8411E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM8414E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM8416E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM8417E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM8418E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM8419E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM8452E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM8453E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM8462E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM8463E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM8671E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM8684E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NMAM8698E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NWBM0097E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NYXM0001I;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.NYXM0002E;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PARAM_CTAC_PRIM_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PARAM_CTAC_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PARAM_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PARAM_DS_ACCT_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PARAM_DS_CTAC_PSN_SALT_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PARAM_FLG_ON_Y;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PARAM_LOC_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PARAM_PROC_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PARAM_PROS_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PARAM_RGTN_STS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PARAM_SER_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PARAM_SVC_CTAC_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PARAM_SVC_MACHN_STS_INSTL;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PARAM_SVC_MACHN_STS_W4I;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PARAM_SVC_MACHN_STS_W4R;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PARAM_UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PERIOD;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.PROCESS_MODE01;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.RESULT_MSG_ERR;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.RESULT_MSG_INS;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.RESULT_MSG_UPD;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.SLASH;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.SVC_CTAC_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.TEL_DS_CTAC_PNT_VAL_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.UPLD_CSV_RQST_CMNT_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.UPLD_CSV_RQST_ROW_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB268001.constant.NMAB268001Constant.ZYPM0181E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.common.EZDDebugOutput;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ART10FMsg;
import business.db.CTAC_INFO_WRKTMsg;
import business.db.CTAC_TPTMsg;
import business.db.DS_CTAC_PNT_TPTMsg;
import business.db.DS_CTAC_PNT_TPTMsgArray;
import business.db.DS_CTAC_PSN_DEPTTMsg;
import business.db.DS_CTAC_PSN_TTLTMsg;
import business.db.SVC_MACH_CTAC_PSNTMsg;
import business.db.UPLD_CSV_RQSTTMsg;
import business.parts.NMZC002001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.common.NMX.NMXC106001.NMXC106001CommonCheckUtil;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Contact Mass Upload Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/04   Hitachi         T.Mizuki        Create          N/A
 * 2016/04/19   Fujitsu         C.Yokoi         Update          CSA-QC#7183, CSA-QC#7188
 * 2016/04/20   Fujitsu         N.Sugiura       Update          CSA-QC#7360
 * 2016/04/26   Fujitsu         N.Sugiura       Update          CSA-QC#7602
 * 2016/05/10   Fujitsu         N.Sugiura       Update          CSA-QC#8095
 * 2016/05/13   Fujitsu         N.Sugiura       Update          CSA-QC#8158
 * 2016/06/16   Hitachi         Y.Osawa         Update          QC#8156
 * 2016/06/30   Hitachi         A.Kohinata      Update          CSA-QC#10918
 * 2016/07/04   Hitachi         Y.Takeno        Update          QC#8156
 * 2017/08/30   Fujitsu         H.Sugawara      Update          QC#20791
 * 2017/10/03   Fujitsu         M.Ohno          Update          QC#20972
 * 2018/02/02   Fujitsu         Hd.Sugawara     Update          QC#23904
 * 2018/03/07   Fujitsu         K.Ishizuka      Update          QC#23351
 * 2018/07/18   Fujitsu         Hd.Sugawara     Update          QC#26407
 * 2018/09/25   Hitachi         K.Kitachi       Update          QC#27788
 * 2018/12/14   Fujitsu         Hd.Sugawara     Update          QC#24022
 * 2019/01/17   Fujitsu         S.Kosaka        Update          QC#29642
 *</pre>
 */

public class NMAB268001 extends S21BatchMain {

    /** UserProfile */
    private S21UserProfileService profile = null;

    /** GlobalCompanyCode */
    private String glblCmpyCd = null;

    /** SsmLLClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** BatchHelper */
    private S21RequestBatchHelper batchHelper = null;

    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;

    /** TermCd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** ProcCount */
    private int procCount = 0;

    /** ErrCount */
    private int errCount = 0;

    /**
     */
    @Override
    protected void initRoutine() {

        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
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

        setTermState(this.termCd, procCount, errCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {

        new NMAB268001().executeBatch(NMAB268001.class.getSimpleName());
    }

    /**
     * @param fMsg ART10FMsg
     */
    private void doProcess(ART10FMsg fMsg) {

        PreparedStatement ciWrk = null;
        // Del Start 2017/08/30 QC#20791
        //PreparedStatement stmtPsNm = null;
        // Del End 2017/08/30 QC#20791
        ResultSet rsWrk = null;
        // Del Start 2017/08/30 QC#20791
        //ResultSet rsPsNm = null;
        // Del End 2017/08/30 QC#20791

        // Upload ID
        String upldCsvId = getUpldCsvId(fMsg);
        EZDDebugOutput.println(0, "UPLD_CSV_ID" + upldCsvId, this);

        // Upload Request PK
        BigDecimal upldCsvRqstPk = getUpldCsvReqPk(fMsg);
        EZDDebugOutput.println(0, "UPLD_CSV_RQST_PK" + upldCsvRqstPk, this);
        UPLD_CSV_RQSTTMsg upldCSVRqstTMsg = getUpldCSVRqst(upldCsvRqstPk);
        this.messenger = new ZYPCSVUploadMessenger(upldCsvId, upldCsvRqstPk);
        String procDate = ZYPDateUtil.getBatProcDate(glblCmpyCd);

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            Map<String, Object> inWrkMap = new HashMap<String, Object>();
            inWrkMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
            inWrkMap.put(PARAM_UPLD_CSV_RQST_PK, upldCSVRqstTMsg.upldCsvRqstPk);

            ciWrk = ssmLLClient.createPreparedStatement(GET_WRK, inWrkMap, execParam);
            rsWrk = ciWrk.executeQuery();
            CTAC_INFO_WRKTMsg wrkTMsg = null;

            int currentError = 0;
            int currentNomal = 0;
            boolean warnFlg = false;
            while (rsWrk.next()) {
                // Del Start 2017/08/30 QC#20791
                //String lastNmBfr = null;
                //String lastNm = null;
                // Del End 2017/08/30 QC#20791
                wrkTMsg = transferWrk(rsWrk);
                if (checkRCVKey(wrkTMsg)) {
                    setErrorMsg(fMsg);
                    currentError++;
                    continue;
                }
                // 2016/06/30 CSA-QC#10918 Mod Start
                // if ((!ZYPCommonFunc.hasValue(wrkTMsg.dsAcctNum) && !ZYPCommonFunc.hasValue(wrkTMsg.locNum.getValue())) || (ZYPCommonFunc.hasValue(wrkTMsg.dsAcctNum.getValue()) && ZYPCommonFunc.hasValue(wrkTMsg.locNum.getValue()))) {
                if (!ZYPCommonFunc.hasValue(wrkTMsg.dsAcctNum) && !ZYPCommonFunc.hasValue(wrkTMsg.locNum)) {
                // 2016/06/30 CSA-QC#10918 Mod End
                    this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8416E, null);
                    setErrorMsg(fMsg);
                    currentError++;
                    continue;
                }
                Map<String, Object> chkPriMap = new HashMap<String, Object>();
                chkPriMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
                chkPriMap.put(PARAM_UPLD_CSV_RQST_PK, upldCSVRqstTMsg.upldCsvRqstPk);
                chkPriMap.put(PARAM_CTAC_PRIM_FLG, ZYPConstant.FLG_ON_Y);
                // 2016/06/30 CSA-QC#10918 Mod Start
//                if (ZYPCommonFunc.hasValue(wrkTMsg.dsAcctNum.getValue())) {
//                    chkPriMap.put(PARAM_DS_ACCT_NUM, wrkTMsg.dsAcctNum.getValue());
//                } else {
//                    chkPriMap.put(PARAM_LOC_NUM, wrkTMsg.locNum.getValue());
//                }
                if (ZYPCommonFunc.hasValue(wrkTMsg.locNum)) {
                    chkPriMap.put(PARAM_LOC_NUM, wrkTMsg.locNum.getValue());
                } else {
                    chkPriMap.put(PARAM_DS_ACCT_NUM, wrkTMsg.dsAcctNum.getValue());
                }
                // 2016/06/30 CSA-QC#10918 Mod End
                Integer rsCntPrmry = (Integer) this.ssmBatchClient.queryObject(CNT_PRMRY, chkPriMap, execParam);
                if (rsCntPrmry > 1) {
                    if (ZYPCommonFunc.hasValue(wrkTMsg.dsAcctNum.getValue())) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8417E, null);
                    } else {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8418E, null);
                    }
                    setErrorMsg(fMsg);
                    currentError++;
                    continue;
                }
                // Del Start 2017/08/30 QC#20791
                //// checkContactPerson
                //Map<String, Object> prsnNmMap = new HashMap<String, Object>();
                //prsnNmMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
                //prsnNmMap.put(PARAM_UPLD_CSV_RQST_PK, upldCSVRqstTMsg.upldCsvRqstPk);
                //if (ZYPCommonFunc.hasValue(wrkTMsg.dsAcctNum.getValue())) {
                //    prsnNmMap.put(PARAM_DS_ACCT_NUM, wrkTMsg.dsAcctNum.getValue());
                //} else {
                //    prsnNmMap.put(PARAM_LOC_NUM, wrkTMsg.locNum.getValue());
                //}
                //stmtPsNm = ssmLLClient.createPreparedStatement(GET_PRSN_NM, prsnNmMap, execParam);
                //rsPsNm = stmtPsNm.executeQuery();
                //boolean checkAccount = true;
                //while (rsPsNm.next()) {
                //    StringBuilder nmBfr = new StringBuilder();
                //    nmBfr.append(rsPsNm.getString(LAST_NM));
                //    nmBfr.append(rsPsNm.getString(FIRST_NM));
                //    lastNmBfr = nmBfr.toString();
                //    if (null != lastNmBfr && lastNmBfr.equals(lastNm)) {
                //        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8431E, null);
                //        checkAccount = false;
                //        setErrorMsg(fMsg);
                //        currentError++;
                //        break;
                //    }
                //    StringBuilder nm = new StringBuilder();
                //    nm.append(rsPsNm.getString(LAST_NM));
                //    nm.append(rsPsNm.getString(FIRST_NM));
                //    lastNm = nm.toString();
                //}
                //if (!checkAccount) {
                //    continue;
                //}
                // Del End 2017/08/30 QC#20791

                // getCountAccount/Location
                Map<String, Object> inAccntMap = new HashMap<String, Object>();
                inAccntMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
                if (ZYPCommonFunc.hasValue(wrkTMsg.dsAcctNum.getValue())) {
                    inAccntMap.put(PARAM_DS_ACCT_NUM, wrkTMsg.dsAcctNum.getValue());
                } else {
                    inAccntMap.put(PARAM_LOC_NUM, wrkTMsg.locNum.getValue());
                }
                inAccntMap.put(PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
                inAccntMap.put(PARAM_PROC_DATE, procDate);
                inAccntMap.put(PARAM_DATE, HIT_DATE);
                inAccntMap.put(PARAM_PROS_FLG, ZYPConstant.FLG_OFF_N);
                inAccntMap.put(PARAM_FLG_ON_Y, ZYPConstant.FLG_ON_Y);
                Integer cntAccntLctn = (Integer) this.ssmBatchClient.queryObject(CNT_ACCNT_LCTN, inAccntMap, execParam);
                if (cntAccntLctn < 1) {
                    if (ZYPCommonFunc.hasValue(wrkTMsg.dsAcctNum.getValue())) {
                        inAccntMap.put(PARAM_PROS_FLG, ZYPConstant.FLG_ON_Y);
                        cntAccntLctn = (Integer) this.ssmBatchClient.queryObject(CNT_ACCNT_LCTN, inAccntMap, execParam);
                    }
                    if (ZYPCommonFunc.hasValue(wrkTMsg.locNum.getValue()) || cntAccntLctn < 1) {
                        if (ZYPCommonFunc.hasValue(wrkTMsg.dsAcctNum.getValue())) {
                            this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8411E, wrkTMsg.dsAcctNum.getValue());
                        } else {
                            this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8419E, wrkTMsg.locNum.getValue());
                        }
                        setErrorMsg(fMsg);
                        currentError++;
                        continue;
                    }
                }
                // Del Start 2017/08/30 QC#20791
                //// countContactPerson
                //Map<String, Object> cntConPrsnMap = new HashMap<String, Object>();
                //cntConPrsnMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
                //if (ZYPCommonFunc.hasValue(wrkTMsg.dsAcctNum.getValue())) {
                //    cntConPrsnMap.put(PARAM_DS_ACCT_NUM, wrkTMsg.dsAcctNum.getValue());
                //} else {
                //    cntConPrsnMap.put(PARAM_LOC_NUM, wrkTMsg.locNum.getValue());
                //}
                //cntConPrsnMap.put(PARAM_LAST_NM, wrkTMsg.lastNm.getValue());
                //cntConPrsnMap.put(PARAM_FIRST_NM, wrkTMsg.firstNm.getValue());
                //cntConPrsnMap.put(PARAM_PROC_DATE, procDate);
                //cntConPrsnMap.put(PARAM_DATE, HIT_DATE);
                //Integer cntCntctPrsn = (Integer) this.ssmBatchClient.queryObject(CNT_CNTCT_PRSN, cntConPrsnMap, execParam);
                //if (cntCntctPrsn >= 1) {
                //    this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8420E, null);
                //    setErrorMsg(fMsg);
                //    currentError++;
                //    continue;
                //}
                // Del End 2017/08/30 QC#20791
                if (!ZYPConstant.FLG_ON_Y.equals(wrkTMsg.ctacPrimFlg.getValue()) && !ZYPConstant.FLG_OFF_N.equals(wrkTMsg.ctacPrimFlg.getValue())) {
                    // Mod Start 2018/07/18 QC#26407
                    //this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8368E, null);
                    this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8684E, MSG_DESC_CTAC_PRIM_FLG);
                    // Mod End 2018/07/18 QC#26407
                    setErrorMsg(fMsg);
                    currentError++;
                    continue;
                }
                // checkCodeTable
                // 2016/04/26 CSA-QC#7602 Mod Start
                String dsCtacPsnSaltCd = null;
                if (ZYPCommonFunc.hasValue(wrkTMsg.dsCtacPsnSaltDescTxt)) {
                    dsCtacPsnSaltCd = getDsCtacPsnSalt(wrkTMsg.dsCtacPsnSaltDescTxt.getValue());
                    if (!ZYPCommonFunc.hasValue(dsCtacPsnSaltCd)) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8414E, MSG_DESC_DS_CTAC_PSN_SALT);
                        setErrorMsg(fMsg);
                        currentError++;
                        continue;
                    }
                }
                String dsCtacPsnDeptCd = null;
                // 2018/03/06 S21_NA#23351 Mod Start
//                if (ZYPCommonFunc.hasValue(wrkTMsg.dsCtacPsnDeptDescTxt)) {
//                    dsCtacPsnDeptCd = getDsCtacPsnDept(wrkTMsg.dsCtacPsnDeptDescTxt.getValue());
                if (ZYPCommonFunc.hasValue(wrkTMsg.dsCtacPsnDeptCd)) {
                    dsCtacPsnDeptCd = getDsCtacPsnDept(wrkTMsg.dsCtacPsnDeptCd.getValue());
                    // 2018/03/06 S21_NA#23351 Mod End
                    if (!ZYPCommonFunc.hasValue(dsCtacPsnDeptCd)) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8414E, MSG_DESC_DS_CTAC_PSN_DEPT);
                        setErrorMsg(fMsg);
                        currentError++;
                        continue;
                    }
                }
                // 2016/04/26 CSA-QC#7602 Mod End
                // 2018/03/06 S21_NA#23351 Mod Start
                // String ctacTpCd = getCtacTp(wrkTMsg.ctacTpDescTxt.getValue());
                String ctacTpCd = getCtacTp(wrkTMsg.ctacTpCd.getValue());
                // 2018/03/06 S21_NA#23351 Mod End
                if (!ZYPCommonFunc.hasValue(ctacTpCd)) {
                    this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8414E, MSG_DESC_CTAC_TP);
                    setErrorMsg(fMsg);
                    currentError++;
                    continue;
                }
                // 2016/04/26 CSA-QC#7602 Mod Start
                String dsCtacPsnTtlCd = null;
                // 2018/03/06 S21_NA#23351 Mod Start
                // if (ZYPCommonFunc.hasValue(wrkTMsg.dsCtacPsnTtlDescTxt)) {
                    // dsCtacPsnTtlCd = getDsCtacPsnTtl(wrkTMsg.dsCtacPsnTtlDescTxt.getValue());
                if (ZYPCommonFunc.hasValue(wrkTMsg.dsCtacPsnTtlCd)) {
                    dsCtacPsnTtlCd = getDsCtacPsnTtl(wrkTMsg.dsCtacPsnTtlCd.getValue());
                // 2018/03/06 S21_NA#23351 Mod End
                    if (!ZYPCommonFunc.hasValue(dsCtacPsnTtlCd)) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8414E, MSG_DESC_DS_CTAC_PSN_TTL);
                        setErrorMsg(fMsg);
                        currentError++;
                        continue;
                    }
                }
                // 2016/04/26 CSA-QC#7602 Mod End
                // 2018/03/06 S21_NA#23351 Mod Start
                // String dsCtacPntTpCd = getDsCtacPntTp(wrkTMsg.dsCtacPntTpDescTxt.getValue());
                String dsCtacPntTpCd = getDsCtacPntTp(wrkTMsg.dsCtacPntTpCd.getValue());
                if (!ZYPCommonFunc.hasValue(dsCtacPntTpCd)) {
                    // this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8453E, wrkTMsg.dsCtacPntTpDescTxt.getValue());
                    // 2018/03/06 S21_NA#23351 Mod End
                    this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8453E, wrkTMsg.dsCtacPntTpCd.getValue());
                    setErrorMsg(fMsg);
                    currentError++;
                    continue;
                }

                // 2017/10/03 CSA-QC#20972 add start
                // Format check
                if (!checkTelFormat(wrkTMsg.telDsCtacPntValTxt)) {
                    this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8348E, null);
                    setErrorMsg(fMsg);
                    currentError++;
                    continue;
                }

                if (!checkExtFormat(wrkTMsg.extnDsCtacPntValTxt)) {
                    // Mod Start 2018/12/14 QC#24022
                    //this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8349E, null);
                    this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8698E, null);
                    // Mod End 2018/12/14 QC#24022
                    setErrorMsg(fMsg);
                    currentError++;
                    continue;
                }

                if (!checkEmailFormat(wrkTMsg.emlDsCtacPntValTxt)) {
                    this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8347E, null);
                    setErrorMsg(fMsg);
                    currentError++;
                    continue;
                }

                if (!checkTelFormat(wrkTMsg.cellDsCtacPntValTxt)) {
                    this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8348E, null);
                    setErrorMsg(fMsg);
                    currentError++;
                    continue;
                }

                if (!checkTelFormat(wrkTMsg.faxDsCtacPntValTxt)) {
                    this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8348E, null);
                    setErrorMsg(fMsg);
                    currentError++;
                    continue;
                }

                // Contact Type Check
                if ((DS_CTAC_PNT_TP.PHONE_WORK.equals(dsCtacPntTpCd) && !ZYPCommonFunc.hasValue(wrkTMsg.telDsCtacPntValTxt)) //
                        || (DS_CTAC_PNT_TP.EMAIL_WORK.equals(dsCtacPntTpCd) && !ZYPCommonFunc.hasValue(wrkTMsg.emlDsCtacPntValTxt)) //
                        || (DS_CTAC_PNT_TP.PHONE_MOBILE.equals(dsCtacPntTpCd) && !ZYPCommonFunc.hasValue(wrkTMsg.cellDsCtacPntValTxt)) //
                        || (DS_CTAC_PNT_TP.FAX_WORK.equals(dsCtacPntTpCd) && !ZYPCommonFunc.hasValue(wrkTMsg.faxDsCtacPntValTxt)) //
                        || (DS_CTAC_PNT_TP.PHONE_ASSISTANT.equals(dsCtacPntTpCd) && !ZYPCommonFunc.hasValue(wrkTMsg.telDsCtacPntValTxt))) {
                    this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8671E, null);
                    setErrorMsg(fMsg);
                    currentError++;
                    continue;
                }

                // Ctact Type Mandat
                Map<String, Object> ctacPrmMap = new HashMap<String, Object>();
                ctacPrmMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
                ctacPrmMap.put(PARAM_CTAC_TP_CD, ctacTpCd);

                List<String> dsCtacPntTpList = (List<String>) this.ssmBatchClient.queryObjectList(GET_CTAC_TP_PNT_MDN_DFN, ctacPrmMap);
                StringBuilder sb = new StringBuilder();
                if (dsCtacPntTpList != null) {
                    for (String mndDsCtacPntTp : dsCtacPntTpList) {
                        if ((DS_CTAC_PNT_TP.PHONE_WORK.equals(mndDsCtacPntTp) && !ZYPCommonFunc.hasValue(wrkTMsg.telDsCtacPntValTxt)) //
                                || (DS_CTAC_PNT_TP.EMAIL_WORK.equals(mndDsCtacPntTp) && !ZYPCommonFunc.hasValue(wrkTMsg.emlDsCtacPntValTxt)) //
                                || (DS_CTAC_PNT_TP.PHONE_MOBILE.equals(mndDsCtacPntTp) && !ZYPCommonFunc.hasValue(wrkTMsg.cellDsCtacPntValTxt)) //
                                || (DS_CTAC_PNT_TP.FAX_WORK.equals(mndDsCtacPntTp) && !ZYPCommonFunc.hasValue(wrkTMsg.faxDsCtacPntValTxt)) //
                                || (DS_CTAC_PNT_TP.PHONE_ASSISTANT.equals(mndDsCtacPntTp) && !ZYPCommonFunc.hasValue(wrkTMsg.telDsCtacPntValTxt))) {

                            DS_CTAC_PNT_TPTMsg tMsg = new DS_CTAC_PNT_TPTMsg();
                            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                            ZYPEZDItemValueSetter.setValue(tMsg.dsCtacPntTpCd, mndDsCtacPntTp);
                            tMsg = (DS_CTAC_PNT_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);

                            if (tMsg != null) {
                                if (sb != null && sb.length() > 0) {
                                    sb.append(", ");
                                }
                                sb.append(tMsg.dsCtacPntTpDescTxt.getValue());
                            }
                        }
                    }

                    if (sb != null && sb.length() > 0) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8368E, "Contact Type [" + sb.toString() + "]");
                        setErrorMsg(fMsg);
                        currentError++;
                        continue;
                    }
                }

                // 2017/10/03 CSA-QC#20972 add end

                // Contact Update API
                NMZC002001PMsg cntctUpdtMsg = setNMZC002001PMsg(wrkTMsg, procDate);
                ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ctacTpCd, ctacTpCd);
                ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.dsCtacPsnSaltCd, dsCtacPsnSaltCd);
                ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.dsCtacPsnDeptCd, dsCtacPsnDeptCd);
                ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.dsCtacPsnTtlCd, dsCtacPsnTtlCd);
                ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.dsPrimCtacTpCd, dsCtacPntTpCd);
                new NMZC002001().execute(cntctUpdtMsg, ONBATCH_TYPE.BATCH);
                List<String> errList = S21ApiUtil.getXxMsgIdList(cntctUpdtMsg);

                boolean errExist = false;
                if (!errList.isEmpty()) {
                    for (String xxMsgId : errList) {
                        // 2016/04/20 CSA-QC#7360 Mod Start
                        if (xxMsgId.endsWith("E") || xxMsgId.endsWith("W")) {
                            this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), xxMsgId, null);
                            if (xxMsgId.endsWith("E")) {
                                errExist = true;
                            } else {
                                warnFlg = true;
                            }
                        }
                        // 2016/04/20 CSA-QC#7360 Mod End
                    }
                    if (errExist) {
                        setErrorMsg(fMsg);
                        currentError++;
                        continue;
                    }
                }

                // count SVC_MACH_CTAC_PSN
                if (ZYPCommonFunc.hasValue(wrkTMsg.serNum)) {
                    Map<String, Object> cntMchCtcPsnMap = new HashMap<String, Object>();
                    cntMchCtcPsnMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
                    cntMchCtcPsnMap.put(PARAM_SER_NUM, wrkTMsg.serNum.getValue());
                    // 2016/04/19 CSA-QC#7183 Mod Start
                    // BigDecimal mstrPk = (BigDecimal)
                    // this.ssmBatchClient.queryObject(GET_SRVC_MCHN,
                    // cntMchCtcPsnMap, execParam);

                    cntMchCtcPsnMap.put(PARAM_FLG_ON_Y, ZYPConstant.FLG_ON_Y);
                    cntMchCtcPsnMap.put(PARAM_SVC_MACHN_STS_W4I, SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
                    cntMchCtcPsnMap.put(PARAM_SVC_MACHN_STS_INSTL, SVC_MACH_MSTR_STS.INSTALLED);
                    cntMchCtcPsnMap.put(PARAM_SVC_MACHN_STS_W4R, SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
                    BigDecimal mstrPk = (BigDecimal) this.ssmBatchClient.queryObject(GET_AVAILABLE_SRVC_MCHN, cntMchCtcPsnMap, execParam);
                    // 2016/04/19 CSA-QC#7183 Mod End
                    if (null == mstrPk) {
                        this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8452E, null);
                        setErrorMsg(fMsg);
                        currentError++;
                        continue;
                    } else {
                        // 2016/04/19 CSA-QC#7183 Add Start
                        // Error if IB Contact is not set
                        if (!CTAC_TP.IB_CONTACT.equals(ctacTpCd)) {
                            this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8462E, MSG_DESC_CTAC_TP);
                            setErrorMsg(fMsg);
                            currentError++;
                            continue;
                        }
                        // 2016/04/19 CSA-QC#7183 Add End

                        // 2016/06/30 CSA-QC#10918 Add Start
                        if (!ZYPCommonFunc.hasValue(wrkTMsg.svcCtacTpDescTxt)) {
                            this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM0041E, MSG_DESC_SVC_CTAC_TP);
                            setErrorMsg(fMsg);
                            currentError++;
                            continue;
                        }
                        // 2016/06/30 CSA-QC#10918 Add End

                        BigDecimal svcMachCtacPsnPk = (BigDecimal) this.ssmBatchClient.queryObject(GET_SRVC_MCHN, cntMchCtcPsnMap, execParam);

                        if (ZYPCommonFunc.hasValue(svcMachCtacPsnPk)) {
                            SVC_MACH_CTAC_PSNTMsg smcpTMsg = new SVC_MACH_CTAC_PSNTMsg();
                            ZYPEZDItemValueSetter.setValue(smcpTMsg.glblCmpyCd, glblCmpyCd);
                            ZYPEZDItemValueSetter.setValue(smcpTMsg.svcMachCtacPsnPk, svcMachCtacPsnPk);
                            SVC_MACH_CTAC_PSNTMsg updtTMsg = (SVC_MACH_CTAC_PSNTMsg) S21FastTBLAccessor.findByKeyForUpdate(smcpTMsg);
                            // setValue(updtTMsg.ctacTpCd,
                            // wrkTMsg.serNum.getValue());
                            // //2016/05/13 CSA-QC#8158 Del
                            ZYPEZDItemValueSetter.setValue(updtTMsg.ctacPsnFirstNm, wrkTMsg.firstNm);
                            ZYPEZDItemValueSetter.setValue(updtTMsg.ctacPsnLastNm, wrkTMsg.lastNm);
                            ZYPEZDItemValueSetter.setValue(updtTMsg.dsCtacPntPk, cntctUpdtMsg.ctacPsnPk);
                            String svcCtacTpCd = getSvcCtacTpCd(wrkTMsg.svcCtacTpDescTxt.getValue());
                            if (!ZYPCommonFunc.hasValue(svcCtacTpCd)) {
                                this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8414E, MSG_DESC_SVC_CTAC_TP);
                                setErrorMsg(fMsg);
                                currentError++;
                                continue;
                            } else {
                                ZYPEZDItemValueSetter.setValue(updtTMsg.svcCtacTpCd, svcCtacTpCd);
                            }
                            S21FastTBLAccessor.update(updtTMsg);
                        }
                        // 2016/05/13 CSA-QC#8158 Add Start
                        else {
                            SVC_MACH_CTAC_PSNTMsg smcpTMsg = new SVC_MACH_CTAC_PSNTMsg();
                            ZYPEZDItemValueSetter.setValue(smcpTMsg.glblCmpyCd, glblCmpyCd);
                            ZYPEZDItemValueSetter.setValue(smcpTMsg.svcMachCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_MACH_CTAC_PSN_SQ"));
                            ZYPEZDItemValueSetter.setValue(smcpTMsg.svcMachMstrPk, mstrPk);
                            ZYPEZDItemValueSetter.setValue(smcpTMsg.ctacPsnFirstNm, wrkTMsg.firstNm);
                            ZYPEZDItemValueSetter.setValue(smcpTMsg.ctacPsnLastNm, wrkTMsg.lastNm);
                            ZYPEZDItemValueSetter.setValue(smcpTMsg.dsCtacPntPk, cntctUpdtMsg.ctacPsnPk);
                            String svcCtacTpCd = getSvcCtacTpCd(wrkTMsg.svcCtacTpDescTxt.getValue());
                            if (!ZYPCommonFunc.hasValue(svcCtacTpCd)) {
                                this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NMAM8414E, MSG_DESC_SVC_CTAC_TP);
                                setErrorMsg(fMsg);
                                currentError++;
                                continue;
                            } else {
                                ZYPEZDItemValueSetter.setValue(smcpTMsg.svcCtacTpCd, svcCtacTpCd);
                            }
                            ZYPEZDItemValueSetter.setValue(smcpTMsg.effFromDt, procDate);
                            // START 2018/09/25 K.Kitachi [QC#27788, ADD]
                            ZYPEZDItemValueSetter.setValue(smcpTMsg.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
                            // END 2018/09/25 K.Kitachi [QC#27788, ADD]
                            S21FastTBLAccessor.insert(smcpTMsg);
                        }
                        // 2016/05/13 CSA-QC#8158 Add End
                    }
                }

                // 2016/04/19 CSA-QC#7188 Mod Start
                commit();
                currentNomal++;
                // 2016/04/19 CSA-QC#7188 Mod End
            }
            this.procCount = this.procCount + currentNomal;
            this.errCount = this.errCount + currentError;
            if (currentError == 0) {
                batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.NOMAL_END);
                // 2016/04/20 CSA-QC#7360 Add Start
                if (warnFlg) {
                    this.messenger.addMessageForFile(NMAM8463E, null);
                // START 2016/06/16 [QC#8156,MOD]
                //    this.messenger.uploadMessage(); // CSA-QC#8095
                //}
                //// 2016/04/20 CSA-QC#7360 Add End
                } else {
                    // START 2016/07/06 [QC#8156,MOD]
                    this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(currentNomal, 0, currentError));
                    // END 2016/07/06 [QC#8156,MOD]
                }
                this.messenger.uploadMessage();
                // END 2016/06/16 [QC#8156,MOD]
            } else {
                termCd = TERM_CD.WARNING_END;
                batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.WARING_END);
                // START 2016/06/16 [QC#8156,MOD]
                //// 2016/04/20 CSA-QC#7360 Add Start
                //this.messenger.addMessageForFile(NMAM8457E, null);
                // START 2016/07/06 [QC#8156,MOD]
                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(currentNomal, 0, currentError));
                // END 2016/07/06 [QC#8156,MOD]
                this.messenger.uploadMessage(); // CSA-QC#8095
                //// 2016/04/20 CSA-QC#7360 Add End
                // END 2016/06/16 [QC#8156,MOD]
            }
            commit();
        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ciWrk, rsWrk);
        }
    }

    private void setErrorMsg(ART10FMsg fMsg) {
        rollback();
        this.messenger.uploadMessage();
    }

    private NMZC002001PMsg setNMZC002001PMsg(CTAC_INFO_WRKTMsg wrkTMsg, String procDate) throws SQLException {
        NMZC002001PMsg cntctUpdtMsg = new NMZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.xxModeCd, PROCESS_MODE01);
        ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.slsDt, procDate);
        ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.dsAcctNum, wrkTMsg.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.locNum, wrkTMsg.locNum);
        ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.effFromDt, procDate);
        ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.dsPrimLocFlg, wrkTMsg.ctacPrimFlg);
        ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ctacPsnFirstNm, wrkTMsg.firstNm);
        ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ctacPsnLastNm, wrkTMsg.lastNm);
        ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ctacPsnActvFlg, ZYPConstant.FLG_ON_Y);

        int i = 0;
        if (ZYPCommonFunc.hasValue(wrkTMsg.telDsCtacPntValTxt)) {
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).xxModeCd, PROCESS_MODE01);
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_WORK);
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).dsCtacPntValTxt, wrkTMsg.telDsCtacPntValTxt);
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).dsCtacPsnExtnNum, wrkTMsg.extnDsCtacPntValTxt);
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            // 2019/01/17 QC#29642 Add Start
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).updCtrlFlg, ZYPConstant.FLG_ON_Y);
            // 2019/01/17 QC#29642 Add End
            i++;
        }

        if (ZYPCommonFunc.hasValue(wrkTMsg.emlDsCtacPntValTxt)) {
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).xxModeCd, PROCESS_MODE01);
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).dsCtacPntValTxt, wrkTMsg.emlDsCtacPntValTxt);
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            i++;
        }

        if (ZYPCommonFunc.hasValue(wrkTMsg.cellDsCtacPntValTxt)) {
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).xxModeCd, PROCESS_MODE01);
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_MOBILE);
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).dsCtacPntValTxt, wrkTMsg.cellDsCtacPntValTxt);
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            i++;
        }

        if (ZYPCommonFunc.hasValue(wrkTMsg.faxDsCtacPntValTxt)) {
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).xxModeCd, PROCESS_MODE01);
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.FAX_WORK);
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).dsCtacPntValTxt, wrkTMsg.faxDsCtacPntValTxt);
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cntctUpdtMsg.ContactPointInfoList.no(i).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            i++;
        }
        cntctUpdtMsg.ContactPointInfoList.setValidCount(i);

        return cntctUpdtMsg;
    }

    private String getSvcCtacTpCd(String searchKey) {
        // Mod Start 2018/07/18 QC#26407
        //SVC_CTAC_TPTMsg inMsg = new SVC_CTAC_TPTMsg();
        //ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        //ZYPEZDItemValueSetter.setValue(inMsg.svcCtacTpDescTxt, searchKey);
        //SVC_CTAC_TPTMsgArray inMsgList = (SVC_CTAC_TPTMsgArray) ZYPCodeDataUtil.findByCondition(inMsg);
        //
        //if (inMsgList.length() == 0) {
        //    return null;
        //}
        //return (String) inMsgList.no(0).svcCtacTpCd.getValue();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
        paramMap.put(PARAM_SVC_CTAC_TP_DESC_TXT, searchKey);

        List<String> resultList = (List<String>) this.ssmBatchClient.queryObjectList(GET_SVC_CTAC_TP, paramMap);

        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
        // Mod End 2018/07/18 QC#26407
    }

    private String getDsCtacPsnSalt(String searchKey) {
        // Mod Start 2018/07/18 QC#26407
        //DS_CTAC_PSN_SALTTMsg inMsg = new DS_CTAC_PSN_SALTTMsg();
        //ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        //ZYPEZDItemValueSetter.setValue(inMsg.dsCtacPsnSaltDescTxt, searchKey);
        //DS_CTAC_PSN_SALTTMsgArray inMsgList = (DS_CTAC_PSN_SALTTMsgArray) ZYPCodeDataUtil.findByCondition(inMsg);
        //
        //if (inMsgList.length() == 0) {
        //    return null;
        //}
        //return (String) inMsgList.no(0).dsCtacPsnSaltCd.getValue();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
        paramMap.put(PARAM_DS_CTAC_PSN_SALT_DESC_TXT, searchKey);

        List<String> resultList = (List<String>) this.ssmBatchClient.queryObjectList(GET_DS_CTAC_PSN_SALT, paramMap);

        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
        // Mod End 2018/07/18 QC#26407
    }

    private String getDsCtacPsnDept(String searchKey) {
        DS_CTAC_PSN_DEPTTMsg inMsg = new DS_CTAC_PSN_DEPTTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        // 2018/03/06 S21_NA#23351 Mod Start
//        ZYPEZDItemValueSetter.setValue(inMsg.dsCtacPsnDeptDescTxt, searchKey);
//        DS_CTAC_PSN_DEPTTMsgArray inMsgList = (DS_CTAC_PSN_DEPTTMsgArray) ZYPCodeDataUtil.findByCondition(inMsg);
        ZYPEZDItemValueSetter.setValue(inMsg.dsCtacPsnDeptCd, searchKey);
        inMsg = (DS_CTAC_PSN_DEPTTMsg) ZYPCodeDataUtil.findByKey(inMsg);

//        if (inMsgList.length() == 0) {
//            return null;
//        }
//        return (String) inMsgList.no(0).dsCtacPsnDeptCd.getValue();
        if (inMsg == null){
            return null;
        }
        
        return (String) inMsg.dsCtacPsnDeptCd.getValue();
     // 2018/03/06 S21_NA#23351 Mod End
    }

    private String getCtacTp(String searchKey) {
        // Add Start 2018/02/02 QC#23904
        if (!ZYPCommonFunc.hasValue(searchKey)) {
            // If ctacTpCd has not value, It means DELIVERY / INSTALL.
            return CTAC_TP.DELIVERY_INSTALL;
        }
        // Add End 2018/02/02 QC#23904

        CTAC_TPTMsg inMsg = new CTAC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        // 2018/03/06 S21_NA#23351 Mod Start
        // ZYPEZDItemValueSetter.setValue(inMsg.ctacTpDescTxt, searchKey);
        // CTAC_TPTMsgArray inMsgList = (CTAC_TPTMsgArray) ZYPCodeDataUtil.findByCondition(inMsg);
        // ZYPEZDItemValueSetter.setValue(inMsg.ctacTpDescTxt, searchKey);
        ZYPEZDItemValueSetter.setValue(inMsg.ctacTpCd, searchKey);
        inMsg = (CTAC_TPTMsg) ZYPCodeDataUtil.findByKey(inMsg);

        // if (inMsgList.length() == 0) {
        //     return null;
        // }
        // return (String) inMsgList.no(0).ctacTpCd.getValue();
        if (inMsg == null) {
            return null;
        }
        return (String) inMsg.ctacTpCd.getValue();
        // 2018/03/06 S21_NA#23351 Mod End
    }

    private String getDsCtacPsnTtl(String searchKey) {
        DS_CTAC_PSN_TTLTMsg inMsg = new DS_CTAC_PSN_TTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        // 2018/03/06 S21_NA#23351 Mod Start
//        ZYPEZDItemValueSetter.setValue(inMsg.dsCtacPsnTtlDescTxt, searchKey);
//        DS_CTAC_PSN_TTLTMsgArray inMsgList = (DS_CTAC_PSN_TTLTMsgArray) ZYPCodeDataUtil.findByCondition(inMsg);
        ZYPEZDItemValueSetter.setValue(inMsg.dsCtacPsnTtlCd, searchKey);
        inMsg = (DS_CTAC_PSN_TTLTMsg) ZYPCodeDataUtil.findByKey(inMsg);
//
//        if (inMsgList.length() == 0) {
//            return null;
//        }
//        return (String) inMsgList.no(0).dsCtacPsnTtlCd.getValue();
        if (inMsg == null) {
            return null;
        }
        return (String) inMsg.dsCtacPsnTtlCd.getValue();
        // 2018/03/06 S21_NA#23351 Mod End
    }

    private String getDsCtacPntTp(String searchKey) {
        DS_CTAC_PNT_TPTMsg inMsg = new DS_CTAC_PNT_TPTMsg();
        // 2016/04/19 CSA-QC#7183 Mod Start
        // 2018/03/06 S21_NA#23351 Mod Start
        // inMsg.setSQLID("001");
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        // inMsg.setConditionValue("dsCtacPntTpDescTxt01", searchKey);
        // inMsg.setConditionValue("dsCtacPntTpCd01", DS_CTAC_PNT_TP.PHONE_ASSISTANT);
        inMsg.setConditionValue("dsCtacPntTpCd01", searchKey);
        inMsg.setConditionValue("dsCtacPntTpCd02", DS_CTAC_PNT_TP.PHONE_ASSISTANT);
        // 2018/03/06 S21_NA#23351 Mod End
        // 2016/04/19 CSA-QC#7183 Mod End

        DS_CTAC_PNT_TPTMsgArray inMsgList = (DS_CTAC_PNT_TPTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (inMsgList.length() == 0) {
            return null;
        }
        return (String) inMsgList.no(0).dsCtacPntTpCd.getValue();
    }

    /**
     * @return CTAC_INFO_WRKTMsg
     */
    private CTAC_INFO_WRKTMsg transferWrk(ResultSet rs) throws SQLException {

        CTAC_INFO_WRKTMsg wrkTMsg = new CTAC_INFO_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(wrkTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wrkTMsg.dsAcctNum, rs.getString(DS_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.dsAcctNm, rs.getString(DS_ACCT_NM));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.locNum, rs.getString(LOC_NUM));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.ctacPrimFlg, rs.getString(CTAC_PRIM_FLG));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.lastNm, rs.getString(LAST_NM));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.firstNm, rs.getString(FIRST_NM));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.dsCtacPsnSaltDescTxt, rs.getString(DS_CTAC_PSN_SALT_DESC_TXT));
        // 2018/03/06 S21_NA#23351 Mod Start
//        ZYPEZDItemValueSetter.setValue(wrkTMsg.dsCtacPsnDeptDescTxt, rs.getString(DS_CTAC_PSN_DEPT_DESC_TXT));
//        ZYPEZDItemValueSetter.setValue(wrkTMsg.ctacTpDescTxt, rs.getString(CTAC_TP_DESC_TXT));
//        ZYPEZDItemValueSetter.setValue(wrkTMsg.dsCtacPsnTtlDescTxt, rs.getString(DS_CTAC_PSN_TTL_DESC_TXT));
//        ZYPEZDItemValueSetter.setValue(wrkTMsg.dsCtacPntTpDescTxt, rs.getString(DS_CTAC_PNT_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.dsCtacPsnDeptCd, rs.getString(DS_CTAC_PSN_DEPT_CD));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.ctacTpCd, rs.getString(CTAC_TP_CD));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.dsCtacPsnTtlCd, rs.getString(DS_CTAC_PSN_TTL_CD));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.dsCtacPntTpCd, rs.getString(DS_CTAC_PNT_TP_CD));
        // 2018/03/06 S21_NA#23351 Mod End
        ZYPEZDItemValueSetter.setValue(wrkTMsg.telDsCtacPntValTxt, rs.getString(TEL_DS_CTAC_PNT_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.extnDsCtacPntValTxt, rs.getString(EXTN_DS_CTAC_PNT_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.emlDsCtacPntValTxt, rs.getString(EML_DS_CTAC_PNT_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.cellDsCtacPntValTxt, rs.getString(CELL_DS_CTAC_PNT_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.faxDsCtacPntValTxt, rs.getString(FAX_DS_CTAC_PNT_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.serNum, rs.getString(SER_NUM));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.upldCsvRqstCmntTxt, rs.getString(UPLD_CSV_RQST_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.upldCsvRqstPk, rs.getBigDecimal(UPLD_CSV_RQST_PK));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.upldCsvRqstRowNum, rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.svcCtacTpDescTxt, rs.getString(SVC_CTAC_TP_DESC_TXT));

        return wrkTMsg;
    }

    /**
     */
    private boolean checkRCVKey(CTAC_INFO_WRKTMsg tMsg) {

        if (!ZYPCommonFunc.hasValue(tMsg.ctacPrimFlg.getValue())) {
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, MSG_DESC_CTAC_PRIM_FLG);
            return true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.lastNm.getValue())) {
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, MSG_DESC_LAST_NM);
            return true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.firstNm.getValue())) {
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, MSG_DESC_FIRST_NM);
            return true;
        }
        // Del Start 2018/02/02 QC#23904
        //if (!ZYPCommonFunc.hasValue(tMsg.ctacTpDescTxt.getValue())) {
        //    this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, MSG_DESC_CTAC_TP);
        //    return true;
        //}
        // Del End 2018/02/02 QC#23904
        // 2018/03/06 S21_NA#23351 Mod Start
        // if (!ZYPCommonFunc.hasValue(tMsg.dsCtacPntTpDescTxt.getValue())) {
        if (!ZYPCommonFunc.hasValue(tMsg.dsCtacPntTpCd.getValue())) {
        // 2018/03/06 S21_NA#23351 Mod End
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8228E, MSG_DESC_DS_CTAC_PNT_TP);
            return true;
        }
        return false;
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

        if (!ZYPCommonFunc.hasValue(removeDQRequestPK)) {
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
        if (!ZYPCommonFunc.hasValue(uploadCsvId)) {
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
        if (!ZYPCommonFunc.hasValue(cmpyCd)) {
            String[] msg = {GLOBAL_COMPANY_CODE };
            throw new S21AbendException(NMAM8228E, msg);
        }

        return cmpyCd;
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

    // 2017/10/03 CSA-QC#20972 add start
    private boolean checkTelFormat(EZDTStringItem item) {

        if (ZYPCommonFunc.hasValue(item)) {
            String telNum = item.getValue();
            if (telNum.length() < 7 || telNum.length() > 20) {
                return false;
            } else {
                telNum = telNum.replaceAll(SLASH, HYPHEN);
                telNum = telNum.replaceAll(PERIOD, HYPHEN);
                ZYPEZDItemValueSetter.setValue(item, telNum);
                return true;
            }
        }
        return true;
    }

    private boolean checkExtFormat(EZDTStringItem item) {

        if (ZYPCommonFunc.hasValue(item)) {
            String extNum = item.getValue();
            // Mod Start 2018/12/14 QC#24022
            //if (extNum.length() > 4) {
            if (extNum.length() > EXT_MAX_LENGTH) {
                // Mod End 2018/12/14 QC#24022
                return false;
            }
        }

        return true;
    }

    private boolean checkEmailFormat(EZDTStringItem item) {

        if (ZYPCommonFunc.hasValue(item)) {
            return NMXC106001CommonCheckUtil.checkEmailFormat(item.getValue());
        }

        return true;
    }
    // 2017/10/03 CSA-QC#20972 add end
}
