/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB025001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_BAT_RCPTTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_RCPTTMsgArray;
import business.db.AR_RCPT_DTLTMsg;
import business.db.AR_RCPT_IN_PROC_WRKTMsg;
import business.db.AR_RCPT_IN_PROC_WRKTMsgArray;
import business.db.AR_RCPT_RCV_HISTTMsg;
import business.parts.NFZC202001PMsg;
import business.parts.NFZC301001PMsg;
import business.parts.NFZC310001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.api.NFC.NFZC301001.NFZC301001;
import com.canon.cusa.s21.api.NFZ.NFZC310001.NFZC310001;
import com.canon.cusa.s21.common.NFX.NFXC302001.NFCReceiptCreation;
import com.canon.cusa.s21.common.NFX.NFXC302001.NFXC3020Bean;
import com.canon.cusa.s21.common.NFX.NFXC304001.NFCProcessStatus;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCDbConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_BAT_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CUST_ID_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NFCB025001 Receipt Data Creation
 *
 * Date         Company       Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2009   Canon         M.Mimura        Create          N/A
 * 04/03/2010   Canon         H.Tokunaga      Update          5423
 * 04/07/2010   Canon         H.Tokunaga      Update          DefID-5410 update rcpt_history
 * 04/19/2010   Canon         H.Tokunaga      Update          DefID-5797 update applyIntfc stscd=2(err)
 * 05/04/2010   Canon         S.Uehara        Update          5416
 * 05/17/2010   Canon         K.Kimura        Update          6384
 * 05/25/2010   Canon         S.Uehara        Update          6739  NO.043
 * 10/12/2010   Canon         I.Kondo         Update          R2 -> R3 Merge.
 * 04/08/2015   Fujitsu       T.Yoshida       Update          For North America(CSA)
 * 12/17/2015   CSAI          K.Uramori       Update          Add credit profile update API (NFZC2020) call
 * 04/19/2016   Fujitsu       S.Fujita        Update          QC#5306
 * 09/07/2017   Hitachi       T.Tsuchida      Update          QC#20814
 * 12/07/2017   Fujitsu       M.Ohno          Update          QC#21397
 * 01/19/2018   Fujitsu       T.Murai         Update          QC#22420
 * 2018/07/04   Fujitsu       H.Ikeda         Update          QC#25731
 * 2018/07/13   Fujitsu       Y.Matsui        Update          QC#26993
 * 2018/08/02   Fujitsu       H.Ikeda         Update          QC#25926
 * 2018/09/20   Fujitsu       T.Ogura         Update          QC#28097
 *</pre>
 */
public class NFCB025001 extends S21BatchMain {

    /** Program Id */
    private static final String[] PROGRAM_ID = {"NFCB025001" };

    /** Message Id */
    private static final String NFCM0501E = "NFCM0501E";

    /** Message Id */
    private static final String NFCM0561E = "NFCM0561E";

    /** Message Id */
    private static final String NFCM0574E = "NFCM0574E";

    /** Message Id */
    private static final String NFCM0633E = "NFCM0633E";

    /** Message Id */
    private static final String NFCM0560E = "NFCM0560E";

    /** Message Id */
    private static final String NFCM0584I = "NFCM0584I";

    /** Message Id */
    private static final String NFCM0593I = "NFCM0593I";

    /** Message Id */
    private static final String NFCM0563E = "NFCM0563E";

    /** Message Id */
    private static final String NFCM0503E = "NFCM0503E";

    /** Message String */
    private static final String[] MSG_STR_GLBL_CMPY_CD = {"GLBL_CMPY_CD" };

    /** Message String */
    private static final String[] MSG_STR_INTERFACE_ID = {"INTERFACE_ID" };

    /** Condition Items For Update AR_RCPT_RCV_HIST */
    private String[] condItemsForRcptRcvHist = new String[] {"glblCmpyCd", "rcvHistSqPk" };

    /** Update Items For Update AR_RCPT_RCV_HIST */
    private String[] updItemsForRcptRcvHist = new String[] {"rcptDepChkFlg", "endDt", "endTm" };

    /** PROC_STS_CD Bind Name 1 */
    private static final String PROC_STS_CD_BIND_NM_1 = "procStsCd1";

    /** PROC_STS_CD Bind Name 2 */
    private static final String PROC_STS_CD_BIND_NM_2 = "procStsCd2";

    /** RCPT_DEP_CHK_FLG Bind Name 1 */
    private static final String RCPT_DEP_CHK_FLG_BIND_NM_1 = "rcptDepChkFlg1";

    /** APPLY_GRP_KEY */
    private static final String APPLY_GRP_KEY_BIND_NM = "applyGrpKey";
    
    /** RCV_HDR_NUM */
    private static final String RCV_HDR_NUM_BIND_NM = "rcvHdrNum";
    
    /** PROC_STS_CD */
    private static final String PROC_STS_CD_BIND_NM = "procStsCd";
    
    /** AR_BAT_RCPT_NUM */
    private static final String BAT_RCPT_NUM_BIND_NM = "batRcptNm";
    
    /** Interface Id */
    private String interfaceId = "";

    /** UPLD_CSV_ID */
    private String upldCsvId = "";

    /** GlobalCompanyCode */
    private String glblCmpyCd = "";

    /** Processing Count */
    private int procCount = 0;

    /** normal Count */
    private int normalCnt = 0;

    /** err Count */
    private int errCnt = 0;

    /** SQL Access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** UPLD_CSV_ID */
    private String sysDt = "";

    /** Date Time Pattern */
    private static final String HHMMSS = "HHmmss";

    /** Batch Process Date */
    private String batDt = "";

    /** Format of AR_RCPT_DTL.RCPT_DTL_NUM */
    private static final String RCPT_DTL_NUM_FORMAT = "%04d";

    /** RCPT_INTIME */
    private static final String RCPT_INTM = "RCPT_INTM";

    /** RCPT_INTMZN */
    private static final String RCPT_INTMZN = "RCPT_INTMZN";

    // Add Start 2018/01/19 S21_NA#22420
    /** fetch size */
    private int FETCH_SIZE = 1000;
    // Add End 2018/01/19 S21_NA#22420

    // START 2018/07/04 H.Ikeda [QC#25731,ADD]
    /** Payment Number */
    private String multipleCnt = null;

    /** Multiple No */
    private int multipleNo = 0;

    /** Default Multiple No */
    private final int defMultipleNo = 10;

    /** CFS IF ID */
    private final String cfsIfId = "NFCI1040";
    // END   2018/07/04 H.Ikeda [QC#25731,ADD]
    // START 2018/08/02 H.Ikeda [QC#25926,ADD]
    /** CFS Misdirected Payment IF ID */
    private final String cfsMisdirectedPaymentIfId = "NFCI1050";

    /** RCPT CHK NUM Md */
    private final String rcptChkNumMd = "_MD";

    /** RCPT CHK NUM Max Length */
    private final int rcptChkNumMaxLength = 15;

    /** Commit Count */
    private final int COMMIT_CNT = 1000;
    // END   2018/08/02 H.Ikeda [QC#25926,ADD]

    /**
     * Initialization method
     */
    @Override
    protected void initRoutine() {
        debugLog("initRoutine start");

        /* Output Start Message Job Log */
        S21InfoLogOutput.println(NFCM0584I, PROGRAM_ID);

        /* SSM initialization */
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        /* Get global company code */
        if (!getGlblCmpyCd()) {
            execAbendException(NFCM0501E, MSG_STR_GLBL_CMPY_CD);
        }

        /* Get interface id */
        getIfId();

        /* Get UPLD_CSV_ID */
        getUpldCsvId();

        /* check Interface id and UPLD_CSV_ID */
        if (S21StringUtil.isEmpty(this.interfaceId) && S21StringUtil.isEmpty(this.upldCsvId)) {
            execAbendException(NFCM0501E, MSG_STR_INTERFACE_ID);
        }

        this.sysDt = ZYPDateUtil.getCurrentSystemTime(ZYPDateUtil.TYPE_YYYYMMDD);

        // START 2018/07/04 H.Ikeda [QC#25731,ADD]
        String num = getUserVariable2();
        if (ZYPCommonFunc.hasValue(num)) {
            this.multipleCnt = num;
        }

        num = getUserVariable3();
        if (ZYPCommonFunc.hasValue(num)) {
            this.multipleNo = Integer.parseInt(num);
        } else {
            this.multipleNo = defMultipleNo;
        }
        // END   2018/07/04 H.Ikeda [QC#25731,ADD]

        debugLog("initRoutine end");
    }

    /**
     * Main method
     */
    @Override
    protected void mainRoutine() {
        debugLog("mainRoutine start");

        execute();

        debugLog("mainRoutine end");

    }

    /**
     * Termination method
     */
    @Override
    protected void termRoutine() {
        debugLog("termRoutine start");

        /* Set Normal End */
        debugLog("termRoutine : termCd=<" + TERM_CD.NORMAL_END + "> normalCnt=<" + this.normalCnt + "> errCnt=<" + this.errCnt + "> procCount=<" + this.procCount + ">");
        setTermState(TERM_CD.NORMAL_END, this.normalCnt, this.errCnt, this.procCount);

        /* Output Termination Message Job Log */
        S21InfoLogOutput.println(NFCM0593I, PROGRAM_ID);

        debugLog("termRoutine end");
    }

    /**
     * Main method that is called from batch
     * @param args parameter
     */
    public static void main(String[] args) {
        /* Initialization S21BatchMain */
        new NFCB025001().executeBatch(NFCB025001.class.getSimpleName());
    }

    /**
     * Get Global Company Code
     * @return boolean true:Normal false:Failure
     */
    private boolean getGlblCmpyCd() {
        debugLog("getGlblCmpyCd start");

        /* Get Global Company Code */
        this.glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (S21StringUtil.isEmpty(this.glblCmpyCd)) {
            debugLog("getGlblCmpyCd : isEmpty error");
            return false;
        }

        debugLog("getGlblCmpyCd end");
        return true;
    }

    /**
     * Get interface id
     * @return boolean true:Normal false:Failure
     */
    private boolean getIfId() {
        debugLog("getIfId start");

        /* Get interface id */
        this.interfaceId = getInterfaceID();
        if (S21StringUtil.isEmpty(this.interfaceId)) {
            debugLog("getIfId : isEmpty error");
            return false;
        }

        debugLog("getIfId : interfaceId=<" + this.interfaceId + ">");
        debugLog("getIfId end");
        return true;
    }

    /**
     * Get UPLD_CSV_ID
     * @return boolean true:Normal false:Failure
     */
    private boolean getUpldCsvId() {
        debugLog("getUpldCsvId start");

        this.upldCsvId = getUserVariable1();
        if (S21StringUtil.isEmpty(this.upldCsvId)) {
            debugLog("getAgingPerTpCd : getUserVariable1 error");
            return false;
        }

        debugLog("getUpldCsvId end");
        return true;
    }

    /**
     * Receipt Data Creation
     */
    private void execute() {
        debugLog("execute start");

        /* Get operation date */
        this.getBatProcDate();

        PreparedStatement stmtSelect = null;
        ResultSet rs = null;

        try {
            /* Get APPLY_GRP_KEY */
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            // Add Start 2018/01/18 S21_NA#22420
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            // Add End 2018/01/18 S21_NA#22420

            // MOD Start 2018/07/04 QC#25731
            //Map<String, String> queryParam = new HashMap<String, String>();
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
            queryParam.put(PROC_STS_CD_BIND_NM_1, NFCConst.CST_PROC_STS_CD_UNPROC);
            queryParam.put(PROC_STS_CD_BIND_NM_2, NFCConst.CST_PROC_STS_CD_RCPT_ONLY);
            if (S21StringUtil.isNotEmpty(this.interfaceId)) {
                queryParam.put(NFCDbConst.INTFC_ID_J, this.interfaceId);
                if (ZYPCommonFunc.hasValue(this.multipleCnt)) {
                    if (cfsIfId.equals(this.interfaceId)) {
                        stmtSelect = this.ssmLLClient.createPreparedStatement("getApplySqNum", queryParam, execParam);
                        rs = stmtSelect.executeQuery();
                        List<String> lineList = new ArrayList<String>();
                        int cnt = Integer.parseInt(this.multipleCnt);
                        while (rs.next()) {
                            String sqNum = rs.getString(NFCDbConst.RCPT_BAT_SQ_NUM);
                            int no = chgStr(sqNum) % this.multipleNo;
                            if (no == cnt) {
                                lineList.add(sqNum);
                            }
                        }
                        String[] lineStr;
                        if (lineList.size() != 0) {
                            lineStr = new String[lineList.size()];
                            for (int i = 0; i < lineList.size(); i++) {
                                lineStr[i] = lineList.get(i);
                            }
                        } else {
                            lineStr = new String[1];
                            lineStr[0] = this.multipleCnt;
                        }
                        queryParam.put("lineList", lineStr);
                    }
                }
                // MOD End   2018/07/04 QC#25731
                stmtSelect = this.ssmLLClient.createPreparedStatement("getApplyGrpKeyByIntfcId", queryParam, execParam);
            } else {
                queryParam.put(NFCDbConst.UPLD_CSV_ID_J, this.upldCsvId);
                stmtSelect = this.ssmLLClient.createPreparedStatement("getApplyGrpKeyByUpldCsvId", queryParam, execParam);
            }
            rs = stmtSelect.executeQuery();

            List<BigDecimal> rcvSqPkList = new ArrayList<BigDecimal>();
            AR_RCPT_IN_PROC_WRKTMsgArray prevRcptInProcWrkForUpdtDataList = null;

            // Del Start 2018/01/18 S21_NA#22420
//            if (rs.first()) {
//                String prevArBatRcptNm = NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_BAT_RCPT_NM));
//                boolean isFirstFlg = true;
//                rs.beforeFirst();
            // Del End 2018/01/18 S21_NA#22420

            // Add Start 2018/01/18 S21_NA#22420
            String prevArBatRcptNm = "";
            boolean hasFirstFlg = false;
            // Add End 2018/01/18 S21_NA#22420
            // START 2018/08/02 H.Ikeda [QC#25926,ADD]
            int rcptNumCnt = 0;
            List<String> arBatRcptNmList = new ArrayList<String>();
            // END   2018/08/02 H.Ikeda [QC#25926,ADD]

                while (rs.next()) {
                    // Add Start 2018/01/18 S21_NA#22420
                    if (rs.isFirst()) {
                      prevArBatRcptNm = NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_BAT_RCPT_NM));
                      hasFirstFlg = true;
                    }
                    // Add EndStart 2018/01/18 S21_NA#22420
                    this.procCount++;
                    String applyGrpKey = NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.APPLY_GRP_KEY));
                    String rcvHdrNum = NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.RCV_HDR_NUM));
                    String arBatRcptNm = NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_BAT_RCPT_NM));
                    String arCustIdStsCd = NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_CUST_ID_STS_CD));
                    BigDecimal rcvSqPk = rs.getBigDecimal(NFCDbConst.RCV_SQ_PK);
                    if (!rcvSqPkList.contains(rcvSqPk)) {
                        rcvSqPkList.add(rcvSqPk);
                    }
                    // START 2018/08/02 H.Ikeda [QC#25926,ADD]
                    rcptNumCnt++;
                    if (this.interfaceId.equals(cfsMisdirectedPaymentIfId) && (!prevArBatRcptNm.equals(arBatRcptNm) || rs.isFirst())) {
                        arBatRcptNmList.add(arBatRcptNm);
                    }
                    // END   2018/08/02 H.Ikeda [QC#25926,ADD]

                    // Mod Start 2018/01/24 S21_NA#22420
                    if (!rs.isFirst()) { 
                 // if (!isFirstFlg) {
                    // Mod End 2018/01/24 S21_NA#22420

                        // commit for NFXC3010 API success(same AR Batch Receipt Name)
                        commit();
                        debugLog("execute : nfzc301001 success : commit");
                    }
                    // isFirstFlg = false; // Del 2018/01/24 S21_NA#22420

                    if (!prevArBatRcptNm.equals(arBatRcptNm) && prevRcptInProcWrkForUpdtDataList != null) {
                        this.insertArBatRcpt(prevRcptInProcWrkForUpdtDataList.no(0));
                        // START 2018/07/13 Y.Matsui [QC#26993, ADD]
                        this.updateArBatRcptStatus(prevRcptInProcWrkForUpdtDataList.no(0).arBatRcptNm.getValue());
                        // END   2018/07/13 Y.Matsui [QC#26993, ADD]
                        commit();
                    }

                    AR_RCPT_IN_PROC_WRKTMsgArray rcptInProcWrkForUpdtDataList = this.getRcptInProcWrkTMsgArray(rcvHdrNum, rcvSqPk);

                    if (AR_CUST_ID_STS.IDENTIFIED.equals(arCustIdStsCd)) {
                        // execute NFXC3020 API
                        NFCReceiptCreation nfcReceiptCreation = new NFCReceiptCreation();
                        NFXC3020Bean nfxc3020bean = nfcReceiptCreation.execute(this.glblCmpyCd, applyGrpKey, this.batDt, rcvHdrNum);

                        if (nfxc3020bean != null && NFCConst.CST_RTN_CD_NORM.equals(nfxc3020bean.getRtrnNo())) {
                            
                            commit();
                            
                            /* check DEAL_SQ_NUM */
                            String dealSqNum = rcvHdrNum;
                            if (S21StringUtil.isEmpty(dealSqNum)) {
                                this.errCnt++;
                                execAbendException(NFCM0633E);
                            }

                            // Receipt Only
                            String procStsCd = rs.getString(NFCDbConst.PROC_STS_CD);
                            if (NFCConst.CST_PROC_STS_CD_RCPT_ONLY.equals(procStsCd)) {
                                this.eraseSuccessProcess(applyGrpKey, rcvHdrNum, NFCConst.CST_PROC_STS_CD_RCPT_ONLY);
                                this.updateArApplyIntfcWrk(applyGrpKey, rcvHdrNum, NFCConst.CST_PROC_STS_CD_RCPT_ONLY_CPLT);
                                this.normalCnt++;
                                
                                // 2015/12/18 move to here. Only when receipt only, commit and go to next record.
                                // commit for NFXC3020 API success
                                commit();
                                // end 2015/12/18

                                // START 2017/09/07 T.Tsuchida [QC#20814,ADD]
                                prevArBatRcptNm = arBatRcptNm;
                                prevRcptInProcWrkForUpdtDataList = rcptInProcWrkForUpdtDataList;
                                // END 2017/09/07 T.Tsuchida [QC#20814,ADD]
                                continue;
                            }

                            /* Set parameter */
                            NFZC301001PMsg param = new NFZC301001PMsg();
                            param.glblCmpyCd.setValue(this.glblCmpyCd);
                            param.applyGrpKey.setValue(applyGrpKey);
                            param.dealSqNum.setValue(dealSqNum);
                            param.batDt.setValue(this.batDt);

                            /* Erase data */
                            NFZC301001 nfzc301001 = new NFZC301001();
                            nfzc301001.execute(param, ONBATCH_TYPE.BATCH);
                            debugLog("afcCashApply.execute : returnCode=<" + param.getReturnCode() + ">");

                            if (NFCConst.CST_NFZC301001_RTN_CD_CPLT.equals(param.getReturnCode())) {
                                /* Complete */
                                this.eraseSuccessProcess(applyGrpKey, rcvHdrNum, NFCConst.CST_PROC_STS_CD_UNPROC);
                                
                                // 2015/12/17 add. Credit Profile Update API call
                                if (!callCrPrflUpdtAPI(rs)) {
                                    rollback();
                                    this.errCnt++;
                                    debugLog("execute : nfzc202001 error : rollback");
                                    
                                    // AR_APPLY_INTFC_WRK.PROC_STS_CD to be updated to '2'
                                    this.updateArApplyIntfcWrk(applyGrpKey, rcvHdrNum, NFCConst.CST_NFZC301001_RTN_CD_ERR);
                                    commit();
                                    
                                } else {
                                // end 2015/12/17
                                    this.normalCnt++;
                                }

                            } else if (NFCConst.CST_NFZC301001_RTN_CD_ERR.equals(param.getReturnCode())) {
                                /* Data Error */
                                rollback();
                                this.errCnt++;
                                debugLog("execute : nfzc301001 delete error : rollback");

                                /* update applyIntfc proc_sts_cd = 2 */
                                this.updateArApplyIntfcWrk(applyGrpKey, rcvHdrNum, NFCConst.CST_NFZC301001_RTN_CD_ERR);
                                commit();

                            } else if (NFCConst.CST_NFZC301001_RTN_CD_EXCLERR.equals(param.getReturnCode())) {
                                /* Exclusive Error */
                                rollback();
                                this.errCnt++;
                                debugLog("execute : nfzc301001 exclusive error : rollback");

                            } else {
                                this.errCnt++;
                                execAbendException(NFCM0560E);
                            }

                        } else {
                            /* Failure registration */
                            debugLog("execute : NFCReceiptCreation error");
                            debugLog("    glblCmpyCd=<" + this.glblCmpyCd + "> applyGrpKey=<" + applyGrpKey + "> batDt=<" + batDt + ">");
                            this.errCnt++;
                            execAbendException(NFCM0561E);
                        }

                    } else {
                        String numberingRcptNum = ZYPNumbering.getUniqueID(this.glblCmpyCd, NFCConst.CST_NUMBERING_KEY_RC);
                        int dtlNum = 1;

                        for (int i = 0; i < rcptInProcWrkForUpdtDataList.getValidCount(); i++) {
                            AR_RCPT_IN_PROC_WRKTMsg procWrkTMsg = rcptInProcWrkForUpdtDataList.no(i);

                            if (i == 0) {
                                this.insertArRcpt(procWrkTMsg, numberingRcptNum);
                            } else {
                                dtlNum++;
                            }
                            this.insertArRcptDtl(procWrkTMsg, numberingRcptNum, String.format(RCPT_DTL_NUM_FORMAT, dtlNum));
                        }

                        this.eraseSuccessProcess(applyGrpKey, rcvHdrNum, NFCConst.CST_PROC_STS_CD_RCPT_ONLY);
                        this.updateArApplyIntfcWrkForUnIdentified(applyGrpKey, rcvHdrNum, numberingRcptNum, NFCConst.CST_PROC_STS_CD_RCPT_ONLY_CPLT);
                        this.normalCnt++;
                    }

                    prevArBatRcptNm = arBatRcptNm;
                    prevRcptInProcWrkForUpdtDataList = rcptInProcWrkForUpdtDataList;
                } // while

                if (prevRcptInProcWrkForUpdtDataList != null) {
                    this.insertArBatRcpt(prevRcptInProcWrkForUpdtDataList.no(0));
                    // START 2018/07/13 Y.Matsui [QC#26993, ADD]
                    this.updateArBatRcptStatus(prevRcptInProcWrkForUpdtDataList.no(0).arBatRcptNm.getValue());
                    // END   2018/07/13 Y.Matsui [QC#26993, ADD]
                    // START 2018/08/02 H.Ikeda [QC#25926,ADD]
                    if (this.interfaceId.equals(cfsMisdirectedPaymentIfId)) {
                        updateArRcptRcptChkNum1050(arBatRcptNmList);
                    }
                    // END   2018/08/02 H.Ikeda [QC#25926,ADD]
                    commit();
                }
                // Mod Start 2018/01/18 S21_NA#22420
                if (!hasFirstFlg) {
                    this.noDataProcess(rcvSqPkList);
                }
                // } else {
                //      this.noDataProcess(rcvSqPkList);
                // }
                // Mod Start 2018/01/18 S21_NA#22420

            // Maybe rcvSqPk is one but it has list just in case data not expect
            this.updateRcptDepChkFlg(rcvSqPkList);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            debugLog("execute : closeResource");
        }

        debugLog("execute end");
    }

    // START 2018/08/02 H.Ikeda [QC#25926,ADD]
    /**
     * Update ArRcpt RcptChkNum(NFCI1050)
     * 
     * @param arBatRcptNmList List<String>
     */
    private void updateArRcptRcptChkNum1050(List<String> arBatRcptNmList){
        // TODO
        if (arBatRcptNmList == null || arBatRcptNmList.size() == 0) {
            return;
        }

        List<AR_RCPTTMsg> updateTMsgList = new ArrayList<AR_RCPTTMsg>();
        for (int i = 0; i < arBatRcptNmList.size(); i++) {
            String prevRcptChkNum = "";
            int cnt = 0;
            String arBatRcptNm = arBatRcptNmList.get(i);
            AR_RCPTTMsgArray arRcptDataList = this.getArRcptTMsgArray(arBatRcptNm);
            for (int j = 0; j < arRcptDataList.length(); j++) {
                AR_RCPTTMsg arRcptMsg = (AR_RCPTTMsg)arRcptDataList.get(j);
                if (ZYPCommonFunc.hasValue(arRcptMsg.rcptChkNum)) {
                    String rcptChkNum = arRcptMsg.rcptChkNum.getValue();
                    if (prevRcptChkNum.equals(rcptChkNum)) {
                        cnt++;
                        arRcptMsg.rcptChkNum.setValue(createRcptChkNum(rcptChkNum, cnt));
                    } else {
                        cnt = 1;
                        arRcptMsg.rcptChkNum.setValue(createRcptChkNum(rcptChkNum, cnt));
                    }
                    prevRcptChkNum = rcptChkNum;
                }
                updateTMsgList.add(arRcptMsg);
                if (updateTMsgList.size() >= COMMIT_CNT) {
                    int rtn = EZDFastTBLAccessor.update(updateTMsgList.toArray(new AR_RCPTTMsg[updateTMsgList.size()]));
                    if (rtn != updateTMsgList.size()) {
                        this.errCnt = this.errCnt + (updateTMsgList.size() - rtn);
                        setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
                        throw new S21AbendException(NFCM0563E);
                    }
                    updateTMsgList.clear();
                }
            }
            if (updateTMsgList != null && updateTMsgList.size() > 0) {
                int rtn = EZDFastTBLAccessor.update(updateTMsgList.toArray(new AR_RCPTTMsg[updateTMsgList.size()]));
                if (rtn != updateTMsgList.size()) {
                    this.errCnt = this.errCnt + (updateTMsgList.size() - rtn);
                    setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
                    throw new S21AbendException(NFCM0563E);
                }
                updateTMsgList.clear();
            }
        }
    }

    /**
     * Create RcptChkNum
     * 
     * @param rcptChkNum String
     * @param cnt        int
     * @return String
     */
    private String createRcptChkNum(String rcptChkNum, int cnt) {
        rcptChkNum = rcptChkNum + rcptChkNumMd + String.format("%03d",cnt);
        if (rcptChkNum.length() > rcptChkNumMaxLength) {
            rcptChkNum = rcptChkNum.substring(0,rcptChkNumMaxLength);
        }
        return rcptChkNum;
    }

    /**
     * Get ArRcpt
     * 
     * @param arBatRcptNm String
     * @return            AR_RCPTTMsgArray
     */
    private AR_RCPTTMsgArray getArRcptTMsgArray(String arBatRcptNm) {
        AR_RCPTTMsg searchCond = new AR_RCPTTMsg();
        searchCond.setSQLID("004");
        searchCond.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        searchCond.setConditionValue("arBatRcptNm01", arBatRcptNm);

        AR_RCPTTMsgArray tMsgArray = (AR_RCPTTMsgArray) EZDTBLAccessor.findByCondition(searchCond);

        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            execAbendException(NFCM0503E);
        }

        return tMsgArray;
    }
    // END   2018/08/02 H.Ikeda [QC#25926,ADD]
    
    
    // START 2018/06/25 H.Ikeda [QC#25731,ADD]
    private int chgStr(String item) {
        if (item == null) {
            return 0;
        }
        Pattern ptn = Pattern.compile("^0+([0-9]+.*)");
        Matcher mch = ptn.matcher(item);
        String output = null;
        if (mch.matches()) {
           output = mch.group(1);
           if (output == null) {
               return 0;
           }
        }
        return Integer.parseInt(output);
    }
    // END   2018/06/25 H.Ikeda [QC#25731,ADD]

    /**
     * get Batch Process Date
     */
    private void getBatProcDate() {

        String batProcDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        if (S21StringUtil.isEmpty(batProcDate)) {
            debugLog("execute : getBatProcDate error");
            execAbendException(NFCM0574E);
        }

        this.batDt = batProcDate;
    }

    /**
     * Execute AbendException
     * @param msgId String
     */
    private void execAbendException(String msgId) {
        setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
        throw new S21AbendException(msgId);
    }

    /**
     * Execute AbendException
     * @param msgId String
     * @param msgStr String[]
     */
    private void execAbendException(String msgId, String[] msgStr) {
        setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
        throw new S21AbendException(msgId, msgStr);
    }

    /**
     * Output Debug Log
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }

    /**
     * Payment reception history information table update.
     * @param rs ResultSet
     * @throws SQLException
     */
    private void updatePaymentReceptionHistory(ResultSet rs) throws SQLException {

        NFCProcessStatus aFCProcessStatus = new NFCProcessStatus();

        String rcvHdrNum = NFCConst.CST_DB_INIT_VAL_STR;

        BigDecimal rcvSqPk = rs.getBigDecimal(NFCDbConst.RCV_SQ_PK);
        rcvHdrNum = rs.getString(NFCDbConst.RCV_HDR_NUM);

        String returnCode = aFCProcessStatus.setProcessStatus(this.glblCmpyCd, rcvSqPk, rcvHdrNum, NFCConst.CST_XX_PROC_TP_CD_UPD, NFCConst.CST_DB_INIT_VAL_STR, NFCConst.CST_DB_INIT_VAL_STR);

        if (NFCConst.CST_RTN_CD_ERR.equals(returnCode)) {
            this.errCnt++;
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0563E);
        }
    }

    /**
     * AR_RCPT_IN_PROC_WRK table item update.
     * @param rs ResultSet
     * @throws SQLException
     */
    private void updateRcptStsCd(ResultSet rs) throws SQLException {
        String rcvHdrNum = NFCConst.CST_DB_INIT_VAL_STR;
        String rcvDtlNum = NFCConst.CST_DB_INIT_VAL_STR;

        BigDecimal rcptInRrocSqPk = rs.getBigDecimal(NFCDbConst.RCPT_IN_PROC_SQ_PK);
        rcvHdrNum = rs.getString(NFCDbConst.RCV_HDR_NUM);
        rcvDtlNum = rs.getString(NFCDbConst.RCV_DTL_NUM);

        AR_RCPT_IN_PROC_WRKTMsg rcptInProcWrkTMsg = rcptInProcWrkForUpdate(this.glblCmpyCd, rcptInRrocSqPk, rcvHdrNum, rcvDtlNum);
        rcptInProcWrkTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        rcptInProcWrkTMsg.rcptInProcSqPk.setValue(rcptInRrocSqPk);
        rcptInProcWrkTMsg.rcvHdrNum.setValue(rcvHdrNum);
        rcptInProcWrkTMsg.rcvDtlNum.setValue(rcvDtlNum);
        rcptInProcWrkTMsg.rcptStsCd.setValue(NFCConst.CST_RCPT_STS_CD_NORM);

        S21FastTBLAccessor.update(rcptInProcWrkTMsg);
    }

    /**
     * <pre>
     * </pre>
     * @param gcc GlobalCompanyCode
     * @param rcvSqPk ReceivedSequencePrimaryKey
     * @param rcvHdrNum ReceiptHeaderNumber
     * @param rcvDtlNum ReceiptDetailNumber
     * @return AR_RCPT_IN_PROC_WRKTMsg
     */
    private AR_RCPT_IN_PROC_WRKTMsg rcptInProcWrkForUpdate(String gcc, BigDecimal rcptInProcSqPk, String rcvHdrNum, String rcvDtlNum) {

        debugLog("rcptInProcWrkForUpdate start");

        AR_RCPT_IN_PROC_WRKTMsg rcptInProcWrkTMsg = new AR_RCPT_IN_PROC_WRKTMsg();
        rcptInProcWrkTMsg.glblCmpyCd.setValue(gcc);
        rcptInProcWrkTMsg.rcptInProcSqPk.setValue(rcptInProcSqPk);
        rcptInProcWrkTMsg.rcvHdrNum.setValue(rcvHdrNum);
        rcptInProcWrkTMsg.rcvDtlNum.setValue(rcvDtlNum);
        rcptInProcWrkTMsg = (AR_RCPT_IN_PROC_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdate(rcptInProcWrkTMsg);

        debugLog("rcptInProcWrkForUpdate end");
        return rcptInProcWrkTMsg;
    }

    /**
     * update Process Status Code
     * @param applyGrpKey Apply Group Key
     * @param applyGrpSubPk Apply Group Sub Key
     * @param procStsCd Process Status Code
     */
    private void updateProcStsCd(String applyGrpKey, BigDecimal applyGrpSubPk, String procStsCd) {

        AR_APPLY_INTFC_WRKTMsg applyIntfcWrkWrkTMsg = applyIntfcWrkForUpdate(applyGrpKey, applyGrpSubPk);
        if (applyIntfcWrkWrkTMsg != null) {
            applyIntfcWrkWrkTMsg.procStsCd.setValue(procStsCd);
            S21FastTBLAccessor.update(applyIntfcWrkWrkTMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(applyIntfcWrkWrkTMsg.getReturnCode())) {
                execAbendException(NFCM0503E);
            }
        }
    }

    /**
     * update AR_APPLY_INTFC_WRKTMsg
     * @param applyGrpKey Apply Group Key
     * @param applyGrpSubPk Apply Group Sub Key
     * @param numberingRcptNum Numbering Receive Number
     * @param numberingRcptDtlNum Numbering Receive Detail Number
     * @param procStsCd Process Status Code
     * @param arRcptTimeMap AR_RCPT Time Map
     */
    private void updateArApplyIntfcWrk(String applyGrpKey, BigDecimal applyGrpSubPk, String numberingRcptNum, String numberingRcptDtlNum, String procStsCd, Map<String, String> arRcptTimeMap) {

        AR_APPLY_INTFC_WRKTMsg applyIntfcWrkWrkTMsg = applyIntfcWrkForUpdate(applyGrpKey, applyGrpSubPk);
        if (applyIntfcWrkWrkTMsg != null) {
            applyIntfcWrkWrkTMsg.rcptNum.setValue(numberingRcptNum);
            applyIntfcWrkWrkTMsg.rcptDtlNum.setValue(numberingRcptDtlNum);
            applyIntfcWrkWrkTMsg.rcptHdrLastUpdTs.setValue(arRcptTimeMap.get(RCPT_INTM));
            applyIntfcWrkWrkTMsg.rcptHdrTz.setValue(arRcptTimeMap.get(RCPT_INTMZN));
            applyIntfcWrkWrkTMsg.procStsCd.setValue(procStsCd);
            S21FastTBLAccessor.update(applyIntfcWrkWrkTMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(applyIntfcWrkWrkTMsg.getReturnCode())) {
                execAbendException(NFCM0503E);
            }
        }
    }

    /**
     * <pre>
     * </pre>
     * @param rcvSqPk ReceivedSequencePrimaryKey
     * @param rcvHdrNum ReceiptHeaderNumber
     * @param rcvDtlNum ReceiptDetailNumber
     * @return AR_APPLY_INTFC_WRKTMsg
     */
    private AR_APPLY_INTFC_WRKTMsg applyIntfcWrkForUpdate(String applyGrpKey, BigDecimal applyGrpSubPk) {

        debugLog("applyIntfcWrkForUpdate start");

        AR_APPLY_INTFC_WRKTMsg applyIntfcWrkWrkTMsg = new AR_APPLY_INTFC_WRKTMsg();
        applyIntfcWrkWrkTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        applyIntfcWrkWrkTMsg.applyGrpKey.setValue(applyGrpKey);
        applyIntfcWrkWrkTMsg.applyGrpSubPk.setValue(applyGrpSubPk);
        applyIntfcWrkWrkTMsg = (AR_APPLY_INTFC_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdate(applyIntfcWrkWrkTMsg);

        debugLog("applyIntfcWrkForUpdate end");
        return applyIntfcWrkWrkTMsg;
    }

    /**
     * Update RCPT_DEP_CHK_FLG In AR_RCPT_RCV_HIST
     * @param rcvSqPkList
     */
    private void updateRcptDepChkFlg(List<BigDecimal> rcvSqPkList) {

        String sysTm = ZYPDateUtil.getCurrentSystemTime(HHMMSS);

        AR_RCPT_RCV_HISTTMsg updMsg = new AR_RCPT_RCV_HISTTMsg();
        updMsg.rcptDepChkFlg.setValue(ZYPConstant.FLG_OFF_N);
        updMsg.endDt.setValue(sysDt);
        updMsg.endTm.setValue(sysTm);
        for (int i = 0; i < rcvSqPkList.size(); i++) {

            BigDecimal rcvSqPk = (BigDecimal) rcvSqPkList.get(i);

            AR_RCPT_RCV_HISTTMsg condMsg = new AR_RCPT_RCV_HISTTMsg();
            condMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            condMsg.rcvHistSqPk.setValue(rcvSqPk);

            S21FastTBLAccessor.updateByPartialValue(condMsg, this.condItemsForRcptRcvHist, updMsg, this.updItemsForRcptRcvHist);
        }
    }

    /**
     * Erase Success Process
     * @param applyGrpKey Apply Group Key
     * @param rcvHdrNum Receive Header Number
     * @param procStsCd Process Status Code
     */
    private void eraseSuccessProcess(String applyGrpKey, String rcvHdrNum, String procStsCd) {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
        queryParam.put(PROC_STS_CD_BIND_NM_1, procStsCd);
        queryParam.put(NFCDbConst.APPLY_GRP_KEY_J, applyGrpKey);
        queryParam.put(NFCDbConst.RCV_HDR_NUM_J, rcvHdrNum);

        PreparedStatement stmtSelect = null;
        ResultSet rs = null;

        try {
            if (S21StringUtil.isNotEmpty(this.interfaceId)) {
                queryParam.put(NFCDbConst.INTFC_ID_J, this.interfaceId);
                stmtSelect = this.ssmLLClient.createPreparedStatement("getReceptHistIntfcId", queryParam, execParam);
                rs = stmtSelect.executeQuery();
                int loopCnt = 0;
                while (rs.next()) {
                    if (loopCnt == 0) {
                        updatePaymentReceptionHistory(rs);
                    }
                    loopCnt++;
                    updateRcptStsCd(rs);
                }
            } else {
                queryParam.put(NFCDbConst.UPLD_CSV_ID_J, this.upldCsvId);
                stmtSelect = this.ssmLLClient.createPreparedStatement("getReceptHistUpldCsvId", queryParam, execParam);
                rs = stmtSelect.executeQuery();
                int loopCnt = 0;
                while (rs.next()) {
                    if (loopCnt == 0) {
                        updatePaymentReceptionHistory(rs);
                    }
                    loopCnt++;
                    updateRcptStsCd(rs);
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }
    
    // added 2015/12/17
    private boolean callCrPrflUpdtAPI (ResultSet rs) throws SQLException{
        
        // get target bill to cust from invoice
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
        queryParam.put(PROC_STS_CD_BIND_NM, PROC_STS.COMPLEATED);  // It is updated by NFZC3010
        queryParam.put(APPLY_GRP_KEY_BIND_NM, rs.getString(NFCDbConst.APPLY_GRP_KEY));
        queryParam.put(RCV_HDR_NUM_BIND_NM, rs.getString(NFCDbConst.RCV_HDR_NUM));
        queryParam.put(BAT_RCPT_NUM_BIND_NM, rs.getString(NFCDbConst.AR_BAT_RCPT_NM));
        
        PreparedStatement stmtSelect = null;
        ResultSet billToRs = null;

        try {
            
            stmtSelect = this.ssmLLClient.createPreparedStatement("getBillToCust", queryParam, execParam);
            billToRs = stmtSelect.executeQuery();
            
            while (billToRs.next()) {
            
                NFZC202001 crPrflUpdApi = new NFZC202001();
                NFZC202001PMsg paramMsg = new NFZC202001PMsg();
                
                ZYPEZDItemValueSetter.setValue(paramMsg.xxModeCd, NFZC202001.MODE_BILL_TO_CUST);
                ZYPEZDItemValueSetter.setValue(paramMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(paramMsg.billToCustCd, billToRs.getString(NFCDbConst.BILL_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(paramMsg.procDt, this.batDt);
        
                crPrflUpdApi.execute(paramMsg, ONBATCH_TYPE.BATCH);
                
                List<String> msgList = S21ApiUtil.getXxMsgIdList(paramMsg);
                for (String msgId : msgList) {
                    if (msgId.endsWith("E")) {
                        return false;
                    }
                }
            }
        
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, billToRs);
        }
        
        
        return true;
    }
    // end 2015/12/17

    /**
     * update AR_APPLY_INTFC_WRK
     * @param applyGrpKey Apply Group Key
     * @param rcvHdrNum Receive Header Number
     * @param procStsCd Process Status Code
     */
    private void updateArApplyIntfcWrk(String applyGrpKey, String rcvHdrNum, String procStsCd) {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
        queryParam.put(NFCDbConst.APPLY_GRP_KEY_J, applyGrpKey);
        queryParam.put(NFCDbConst.RCV_HDR_NUM_J, rcvHdrNum);
        queryParam.put("unProc", NFCConst.CST_PROC_STS_CD_UNPROC);
        queryParam.put("rcptOnly", NFCConst.CST_PROC_STS_CD_RCPT_ONLY);

        PreparedStatement stmtSelect = null;
        ResultSet rs = null;

        try {
            stmtSelect = this.ssmLLClient.createPreparedStatement("getApplyIntfcWrk", queryParam, execParam);
            rs = stmtSelect.executeQuery();
            while (rs.next()) {
                updateProcStsCd(rs.getString(NFCDbConst.APPLY_GRP_KEY), rs.getBigDecimal(NFCDbConst.APPLY_GRP_SUB_PK), procStsCd);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * update AR_APPLY_INTFC_WRK for UnIdentified
     * @param applyGrpKey Apply Group Key
     * @param rcvHdrNum Receive Header Number
     * @param numberingRcptNum Numbering Receive Number
     * @param procStsCd Process Status Code
     */
    private void updateArApplyIntfcWrkForUnIdentified(String applyGrpKey, String rcvHdrNum, String numberingRcptNum, String procStsCd) {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
        queryParam.put(NFCDbConst.APPLY_GRP_KEY_J, applyGrpKey);
        queryParam.put(NFCDbConst.RCV_HDR_NUM_J, rcvHdrNum);
        queryParam.put("unProc", NFCConst.CST_PROC_STS_CD_UNPROC);
        queryParam.put("rcptOnly", NFCConst.CST_PROC_STS_CD_RCPT_ONLY);

        PreparedStatement stmtSelect = null;
        ResultSet rs = null;

        try {
            stmtSelect = this.ssmLLClient.createPreparedStatement("getApplyIntfcWrk", queryParam, execParam);
            rs = stmtSelect.executeQuery();

            Map<String, String> arRcptTimeMap = getArRcptTimeMap(numberingRcptNum);
            int dtlNum = 0;

            while (rs.next()) {
                dtlNum++;
                updateArApplyIntfcWrk(rs.getString(NFCDbConst.APPLY_GRP_KEY), rs.getBigDecimal(NFCDbConst.APPLY_GRP_SUB_PK), numberingRcptNum, String.format(RCPT_DTL_NUM_FORMAT, dtlNum), procStsCd, arRcptTimeMap);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * get AR_RCPT Time Map
     * @param numberingRcptNum Numbering Receive Number
     * @return AR_RCPT Time Map
     */
    private Map<String, String> getArRcptTimeMap(String numberingRcptNum) {

        AR_RCPTTMsg rcptTmsg = new AR_RCPTTMsg();
        rcptTmsg.glblCmpyCd.setValue(this.glblCmpyCd);
        rcptTmsg.rcptNum.setValue(numberingRcptNum);
        rcptTmsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKey(rcptTmsg);

        Map<String, String> arRcptTimeMap = new HashMap<String, String>();

        if (EZDTBLAccessor.RTNCD_NORMAL.equals(rcptTmsg.getReturnCode())) {
            arRcptTimeMap.put(RCPT_INTM, rcptTmsg.ezInTime.getValue());
            arRcptTimeMap.put(RCPT_INTMZN, rcptTmsg.ezInTimeZone.getValue());
        }

        return arRcptTimeMap;
    }

    /**
     * No Data Process
     * @param rcvSqPkList Received Sequence Primary Key
     */
    private void noDataProcess(List<BigDecimal> rcvSqPkList) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
        queryParam.put(PROC_STS_CD_BIND_NM_1, NFCConst.CST_PROC_STS_CD_EDIERR);
        queryParam.put(RCPT_DEP_CHK_FLG_BIND_NM_1, ZYPConstant.FLG_ON_Y);
        if (S21StringUtil.isNotEmpty(this.interfaceId)) {
            queryParam.put(NFCDbConst.INTFC_ID_J, this.interfaceId);
            queryParam.put(NFCDbConst.UPLD_CSV_ID_J, null);
        } else {
            queryParam.put(NFCDbConst.INTFC_ID_J, null);
            queryParam.put(NFCDbConst.UPLD_CSV_ID_J, this.upldCsvId);
        }

        PreparedStatement stmtSelect = null;
        ResultSet rs = null;

        try {
            stmtSelect = this.ssmLLClient.createPreparedStatement("getRcvSqPk", queryParam);
            rs = stmtSelect.executeQuery();

            while (rs.next()) {
                rcvSqPkList.add(rs.getBigDecimal(NFCDbConst.RCV_SQ_PK));
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * get AR Receipt In Process Work List
     * @param rcvHdrNum Receipt Header Number
     * @param rcvSqPk Received Sequence Primary Key
     * @return AR Receipt In Process Work List
     */
    private AR_RCPT_IN_PROC_WRKTMsgArray getRcptInProcWrkTMsgArray(String rcvHdrNum, BigDecimal rcvSqPk) {
        AR_RCPT_IN_PROC_WRKTMsg searchCond = new AR_RCPT_IN_PROC_WRKTMsg();
        searchCond.setSQLID("002");
        searchCond.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        searchCond.setConditionValue("rcvHdrNum01", rcvHdrNum);
        searchCond.setConditionValue("rcvSqPk01", rcvSqPk);
        AR_RCPT_IN_PROC_WRKTMsgArray tMsgArray = (AR_RCPT_IN_PROC_WRKTMsgArray) EZDTBLAccessor.findByCondition(searchCond);

        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            execAbendException(NFCM0503E);
        }

        return tMsgArray;
    }

    /**
     * insert AR_RCPT
     * @param rcptInProcWrkForUpdtData AR_RCPT_IN_PROC_WRKTMsg
     * @param numberingRcptNum Numbering Receipt Number
     */
    private void insertArRcpt(AR_RCPT_IN_PROC_WRKTMsg rcptInProcWrkForUpdtData, String numberingRcptNum) {

        AR_RCPTTMsg insertTMsg = new AR_RCPTTMsg();
        ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.rcptNum, numberingRcptNum);
        ZYPEZDItemValueSetter.setValue(insertTMsg.rcptBatNum, rcptInProcWrkForUpdtData.rcptBatNum);
        ZYPEZDItemValueSetter.setValue(insertTMsg.rcptBatSqNum, rcptInProcWrkForUpdtData.rcptBatSqNum);
        ZYPEZDItemValueSetter.setValue(insertTMsg.arRcptTrxTpCd, rcptInProcWrkForUpdtData.arRcptTrxTpCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.arRcptTpCd, rcptInProcWrkForUpdtData.arRcptTpCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dealCcyCd, rcptInProcWrkForUpdtData.dealCcyCd);
        // START 2016/04/19 S.Fujita [QC#5306,MOD]
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dealRcptAmt, rcptInProcWrkForUpdtData.dealRcptAmt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dealRcptAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        // END 2016/04/19 S.Fujita [QC#5306,MOD]
        ZYPEZDItemValueSetter.setValue(insertTMsg.dealApplyAmt, rcptInProcWrkForUpdtData.dealApplyAmt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dealApplyAdjAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dealRfAmt, rcptInProcWrkForUpdtData.dealRfAmt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dealVoidAmt, rcptInProcWrkForUpdtData.dealVoidAmt);
        // START 2016/04/19 S.Fujita [QC#5306,MOD]
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dealRcptRmngBalAmt, rcptInProcWrkForUpdtData.dealRcptRmngBalAmt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dealRcptRmngBalAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        // END 2016/04/19 S.Fujita [QC#5306,MOD]
        ZYPEZDItemValueSetter.setValue(insertTMsg.exchRate, rcptInProcWrkForUpdtData.exchRate);
        ZYPEZDItemValueSetter.setValue(insertTMsg.funcCcyCd, rcptInProcWrkForUpdtData.funcCcyCd);
        // START 2016/04/19 S.Fujita [QC#5306,MOD]
        // ZYPEZDItemValueSetter.setValue(insertTMsg.funcRcptAmt, rcptInProcWrkForUpdtData.funcRcptAmt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.funcRcptAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        // END 2016/04/19 S.Fujita [QC#5306,MOD]
        ZYPEZDItemValueSetter.setValue(insertTMsg.funcApplyAmt, rcptInProcWrkForUpdtData.funcApplyAmt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.funcApplyAdjAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        ZYPEZDItemValueSetter.setValue(insertTMsg.funcRfAmt, rcptInProcWrkForUpdtData.funcRfAmt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.funcVoidAmt, rcptInProcWrkForUpdtData.funcVoidAmt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.funcRvalVarAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        // START 2016/04/19 S.Fujita [QC#5306,MOD]
        // ZYPEZDItemValueSetter.setValue(insertTMsg.funcRcptRmngBalAmt, rcptInProcWrkForUpdtData.funcRcptRmngBalAmt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.funcRcptRmngBalAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        // END 2016/04/19 S.Fujita [QC#5306,MOD]
        ZYPEZDItemValueSetter.setValue(insertTMsg.rcptDt, rcptInProcWrkForUpdtData.rcptDt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.glDt, rcptInProcWrkForUpdtData.glDt);
        // START 2018/08/02 H.Ikeda [QC#25926,MOD]
        ZYPEZDItemValueSetter.setValue(insertTMsg.rcptChkNum, rcptInProcWrkForUpdtData.rcptChkNum);
        //ZYPEZDItemValueSetter.setValue(insertTMsg.rcptChkNum, createRcptChkNum(rcptInProcWrkForUpdtData.rcptChkNum.getValue(), rcptNumCnt));
        // END   2018/08/02 H.Ikeda [QC#25926,MOD]
        ZYPEZDItemValueSetter.setValue(insertTMsg.rcptChkDt, rcptInProcWrkForUpdtData.rcptChkDt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.tocCd, rcptInProcWrkForUpdtData.tocCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.coaProdCd, rcptInProcWrkForUpdtData.coaProdCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.crAnlstPsnCd, rcptInProcWrkForUpdtData.crAnlstPsnCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.arBankAcctCd, rcptInProcWrkForUpdtData.arBankAcctCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.rcptFirstCmntTxt, rcptInProcWrkForUpdtData.rcptFirstCmntTxt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.rcptScdCmntTxt, rcptInProcWrkForUpdtData.rcptScdCmntTxt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.arCashApplyStsCd, rcptInProcWrkForUpdtData.arCashApplyStsCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.voidFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insertTMsg.rfExchRate, rcptInProcWrkForUpdtData.rfExchRate);
        ZYPEZDItemValueSetter.setValue(insertTMsg.rcvTs, rcptInProcWrkForUpdtData.rcvTs);
        ZYPEZDItemValueSetter.setValue(insertTMsg.rcptInProcSqPk, rcptInProcWrkForUpdtData.rcptInProcSqPk);
        ZYPEZDItemValueSetter.setValue(insertTMsg.cratMethTpCd, rcptInProcWrkForUpdtData.cratMethTpCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.cashAppDt, this.batDt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.exptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insertTMsg.exptFirstBankChrgCcyCd, rcptInProcWrkForUpdtData.dealCcyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dealFirstExptChrgAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        ZYPEZDItemValueSetter.setValue(insertTMsg.funcFirstExptChrgAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        ZYPEZDItemValueSetter.setValue(insertTMsg.exptScdBankChrgCcyCd, rcptInProcWrkForUpdtData.dealCcyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dealScdExptChrgAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        ZYPEZDItemValueSetter.setValue(insertTMsg.funcScdExptChrgAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dealNetRcptAmt, rcptInProcWrkForUpdtData.dealRcptAmt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.funcNetRcptAmt, rcptInProcWrkForUpdtData.funcRcptAmt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.fgnExchLossGainAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        ZYPEZDItemValueSetter.setValue(insertTMsg.rcvFuncId, rcptInProcWrkForUpdtData.rcvFuncId);
        ZYPEZDItemValueSetter.setValue(insertTMsg.lastUpdUserId, rcptInProcWrkForUpdtData.ezUpUserID);
        ZYPEZDItemValueSetter.setValue(insertTMsg.arBatRcptNm, rcptInProcWrkForUpdtData.arBatRcptNm);
        ZYPEZDItemValueSetter.setValue(insertTMsg.arOrgRcptNum, rcptInProcWrkForUpdtData.custRcptNum);
        ZYPEZDItemValueSetter.setValue(insertTMsg.arRcptSrcCd, rcptInProcWrkForUpdtData.arRcptSrcCd);
        // START 2018/09/20 T.Ogura [QC#28097,MOD]
//        ZYPEZDItemValueSetter.setValue(insertTMsg.arRcptStsCd, AR_RCPT_STS.CLEARED);
        ZYPEZDItemValueSetter.setValue(insertTMsg.arRcptStsCd, AR_RCPT_STS.NEW);
        // END   2018/09/20 T.Ogura [QC#28097,MOD]
        // 2017/12/07 QC#21397 mod start
//        ZYPEZDItemValueSetter.setValue(insertTMsg.arRcptRemDt, rcptInProcWrkForUpdtData.rcvDt);
        // 2017/12/07 QC#21397 mod end
        ZYPEZDItemValueSetter.setValue(insertTMsg.arRcptRemDt, rcptInProcWrkForUpdtData.rcptDt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.remDsBankAcctPk, rcptInProcWrkForUpdtData.remDsBankAcctPk);
        ZYPEZDItemValueSetter.setValue(insertTMsg.custDsBankAcctPk, rcptInProcWrkForUpdtData.custDsBankAcctPk);
        ZYPEZDItemValueSetter.setValue(insertTMsg.ajeCratCpltFlg, ZYPConstant.FLG_OFF_N);
        S21FastTBLAccessor.insert(insertTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
            execAbendException(NFCM0503E);
        }
    }

    /**
     * insert AR_RCPT_DTL
     * @param rcptInProcWrkForUpdtData AR_RCPT_IN_PROC_WRKTMsg
     * @param numberingRcptNum Numbering Receipt Number
     * @param numberingRcptNum Numbering Receipt Detail Number
     */
    private void insertArRcptDtl(AR_RCPT_IN_PROC_WRKTMsg rcptInProcWrkForUpdtData, String numberingRcptNum, String numberingRcptDtlNum) {

        AR_RCPT_DTLTMsg insertTMsg = new AR_RCPT_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.rcptNum, numberingRcptNum);
        ZYPEZDItemValueSetter.setValue(insertTMsg.rcptDtlNum, numberingRcptDtlNum);
        ZYPEZDItemValueSetter.setValue(insertTMsg.invNum, rcptInProcWrkForUpdtData.invNum);
        ZYPEZDItemValueSetter.setValue(insertTMsg.arCustRefNum, rcptInProcWrkForUpdtData.arCustRefNum);
        ZYPEZDItemValueSetter.setValue(insertTMsg.arCustRefTpCd, rcptInProcWrkForUpdtData.arCustRefTpCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dealRcptDtlAmt, rcptInProcWrkForUpdtData.dealRcptDtlAmt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.funcRcptDtlAmt, rcptInProcWrkForUpdtData.funcRcptDtlAmt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.rcvTrxTpCd, rcptInProcWrkForUpdtData.rcvTrxTpCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.rcvTrxNum, rcptInProcWrkForUpdtData.rcvTrxNum);
        ZYPEZDItemValueSetter.setValue(insertTMsg.autoCratFlg, rcptInProcWrkForUpdtData.autoCratFlg);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dupErrCd, rcptInProcWrkForUpdtData.dupErrCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.grpInvFlg, rcptInProcWrkForUpdtData.grpInvFlg);
        S21FastTBLAccessor.insert(insertTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
            execAbendException(NFCM0503E);
        }
    }

    /**
     * insert AR_BAT_RCPT
     * @param rcptInProcWrkForUpdtData AR_RCPT_IN_PROC_WRKTMsg
     * @throws SQLException 
     */
    private void insertArBatRcpt(AR_RCPT_IN_PROC_WRKTMsg rcptInProcWrkForUpdtData) throws SQLException {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
        queryParam.put("arbatRcptNm", rcptInProcWrkForUpdtData.arBatRcptNm.getValue());

        PreparedStatement stmtSelect = null;
        ResultSet rs = null;
        
        stmtSelect = this.ssmLLClient.createPreparedStatement("getArBatRcptCnt", queryParam);
        rs = stmtSelect.executeQuery();

        if (rs.next()) {
            int cnt = rs.getInt("AR_BAT_RCPT_CNT");
            if (cnt > 0) {
                return;
            }
        }

        AR_BAT_RCPTTMsg insertTMsg = new AR_BAT_RCPTTMsg();
        ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.arBatRcptNm, rcptInProcWrkForUpdtData.arBatRcptNm);
        ZYPEZDItemValueSetter.setValue(insertTMsg.arRcptSrcCd, rcptInProcWrkForUpdtData.arRcptSrcCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.arBatRcptDt, rcptInProcWrkForUpdtData.rcvDt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.arBatRcptCnt, rcptInProcWrkForUpdtData.batRcptRecCnt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.arBatRcptAmt, rcptInProcWrkForUpdtData.batRcptTotAmt);
        // START 2018/07/13 Y.Matsui [QC#26993, MOD]
        ZYPEZDItemValueSetter.setValue(insertTMsg.arBatRcptStsCd, AR_BAT_RCPT_STS.OPEN);
        // START 2018/07/13 Y.Matsui [QC#26993, MOD]
        ZYPEZDItemValueSetter.setValue(insertTMsg.arLockBoxFileNm, rcptInProcWrkForUpdtData.arLockBoxFileNm);
        ZYPEZDItemValueSetter.setValue(insertTMsg.arLockBoxCd, rcptInProcWrkForUpdtData.arLockBoxCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.arLockBoxBatNum, rcptInProcWrkForUpdtData.rcptBatNum);
        ZYPEZDItemValueSetter.setValue(insertTMsg.remDsBankAcctPk, rcptInProcWrkForUpdtData.remDsBankAcctPk);
        ZYPEZDItemValueSetter.setValue(insertTMsg.custDsBankAcctPk, rcptInProcWrkForUpdtData.custDsBankAcctPk);
        S21FastTBLAccessor.insert(insertTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
            execAbendException(NFCM0503E);
        }
    }

    // START 2018/07/13 Y.Matsui [QC#26993, ADD]
    private boolean updateArBatRcptStatus(String arBatRcptNm) {
        NFZC310001PMsg pMsg = new NFZC310001PMsg();
        pMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        pMsg.arBatRcptNm.setValue(arBatRcptNm);
        new NFZC310001().execute(pMsg, ONBATCH_TYPE.BATCH);
        List<S21ApiMessage> xxMsgList = S21ApiUtil.getXxMsgList(pMsg);
        if (!xxMsgList.isEmpty()) {
            for (S21ApiMessage xxMsg : xxMsgList) {
                execAbendException(xxMsg.getXxMsgid());
            }
        }
        return true;
    }
    // END   2018/07/13 Y.Matsui [QC#26993, ADD]
}
