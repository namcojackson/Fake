/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB052001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import parts.common.EZDMsg;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_RCPT_RCV_INFO_WRKTMsg;
import business.db.AR_RCPT_RCV_INTFCTMsg;
import business.db.NFCI1010_07TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_BAT_INFO_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CUST_ID_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_LOCK_BOX_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BANK_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOCK_BOX_NTFY_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * NFCB052001.
 * Import AR Lock Box Receipt Interface Data.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/10/2015   Fujitsu         M.Nakamura      Create          N/A
 * 03/17/2016   Fujitsu         T.Tanaka        Update          Def#5016
 * 08/23/2016   Fujitsu         S.Fujita        Update          QC#13266
 * 10/18/2016   Hitachi         K.Kasai         Update          QC#14026
 * 11/09/2016   Fujitsu         S.Fujita        Update          QC#15742
 * 12/05/2016   Fujitsu         S.Fujita        Update          QC#16255
 * 05/17/2017   Hitachi         E.Kameishi      Update          QC#18594
 * 05/18/2017   Hitachi         E.Kameishi      Update          QC#18600
 * 05/29/2017   Hitachi         E.Kameishi      Update          QC#18594
 * 12/01/2017   Hitachi         T.Tsuchida      Update          QC#21398
 * 02/01/2018   Hitachi         T.Tsuchida      Update          QC#23053
 * 05/08/2018   Hitachi         E.Kameishi      Update          QC#25721
 * 2018/05/18   Fujitsu         H.Ikeda         Update          QC#25914
 * 2018/07/04   Fujitsu         H.Ikeda         Update          QC#25731
 * 2018/07/20   Fujitsu         H.Ikeda         Update          QC#24991
 * 2018/08/22   Fujitsu         H.Ikeda         Update          QC#27776
 * 2018/11/16   Fujitsu         H.Ikeda         Update          QC#29278
 * 2022/12/14   CITS            T.Omura         Update          QC#60929
 *</pre>
 */
public class NFCB052001 extends S21BatchMain {

    /** Common component for Database accessing */
    private S21SsmBatchClient ssmBatchClient;

    /** SQL Access parts */
    private S21SsmLowLevelCodingClient ssmLLClient;

    /** Common component for Integration management record creation */
    private S21TransactionTableAccessor s21TranTblAccs;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface ID */
    private String intfcId;

    /** Batch Process Date */
    private String batchProcDt;

    /** Parameter File Name */
    private String prmFlNm;

    // START 2022/12/14 T.Omura [QC#60929, ADD]
    /** Lock Box Update Flag */
    private String lockBoxUpdateFlg;
    // END 2022/12/14 T.Omura [QC#60929, ADD]

    /** Lock Box File Name */
    private String lockBoxFlNm;

    /** Total Record Count */
    private int ttlRecCnt = 0;

    /** Normal Record Count */
    private int normRecCnt = 0;

    /** Error Record Count */
    private int errRecCnt = 0;

    // START 2018/11/16 H.Ikeda [QC#29278, ADD]
    /** DEP DT */
    private String depDt;

    /** DEP TM */
    private String depTm;
    // END  2018/11/16 H.Ikeda [QC#29278, ADD]

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** First Number */
    private static final String FIRST_NUM = "_01";

    /** Underscore */
    private static final String STR_UNDERSCORE = "_";

    /** Percent */
    private static final String STR_PERCENT = "%";

    /** Not Create Invoice Number */
    private static final String STR_NOT_CRAT_INV_NUM = "ZZZZZZZZZZZZ";

    /** Divide Value */
    private static final BigDecimal DIVIDE_VAL = BigDecimal.valueOf(100);

    /** Digit Check Pattern */
    private static final String CHK_PTTERN = "^[0-9]*$";

    /** message of [@] is mandatory. */
    private static final String ZZZM9025E = "ZZZM9025E";

    /** Error Message */
    private static final String NFCM0743E = "NFCM0743E";

    /** Error Message */
    private static final String NFCM0744E = "NFCM0744E";

    /** Error Message */
    private static final String NFCM0745E = "NFCM0745E";

    /** Error Message */
    private static final String NFCM0746E = "NFCM0746E";

    /** Error Message */
    private static final String NFCM0747E = "NFCM0747E";

    /** Error Message */
    private static final String NFCM0748E = "NFCM0748E";

    /** Error Message */
    private static final String NFCM0749E = "NFCM0749E";

    /** Error Message */
    private static final String NFCM0750E = "NFCM0750E";

    /** Error Message */
    private static final String NFCM0751E = "NFCM0751E";

    /** Error Message */
    private static final String NFCM0752E = "NFCM0752E";

    // START 2018/02/01 T.Tsuchida [QC#23053,DEL]
//    /** Error Message */
//    private static final String NFCM0753E = "NFCM0753E";
//
//    /** Error Message */
//    private static final String NFCM0754E = "NFCM0754E";
    // START 2018/02/01 T.Tsuchida [QC#23053,DEL]

    /** Error Message */
    private static final String NFCM0755E = "NFCM0755E";

    /** Error Message */
    private static final String NFCM0756E = "NFCM0756E";

    // START 2018/05/18 H.Ikeda [QC#25914,ADD]
    /** Error Message */
    private static final String NFCM0884E = "NFCM0884E";
    // END   2018/05/18 H.Ikeda [QC#25914,ADD]

    /** Warning Message */
    private static final String NFCM0876W = "NFCM0876W";
    // START 2018/07/04 H.Ikeda [QC#25731,ADD]
    /** Error Message */
    private static final String NFCM0615E = "NFCM0615E";
    // END   2018/07/04 H.Ikeda [QC#25731,ADD]
    // START 2016/11/15 S.Fujita [QC#15742,ADD]
    /** Substring Length */
    private int SUBSTRING_LEN13 = 13;
    // END   2016/11/15 S.Fujita [QC#15742,ADD]

    // START 2018/06/28 H.Ikeda [QC#25731,ADD]
    /** Commit count */
    private int COMMIT_CNT = 1000;
    // END   2018/06/28 H.Ikeda [QC#25731,ADD]

    /**
     * It is the main method that is called from the batch shell.
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFCB052001().executeBatch(NFCB052001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        this.ssmBatchClient = S21SsmBatchClient.getClient(NFCB052001.class);

        this.s21TranTblAccs = new S21TransactionTableAccessor();

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        this.glblCmpyCd = this.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        this.batchProcDt = ZYPDateUtil.getBatProcDate();
        if (!ZYPCommonFunc.hasValue(batchProcDt)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Batch Process Date" });
        }

        this.intfcId = super.getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.intfcId)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Interface ID" });
        }

        this.prmFlNm = S21BatchMain.getUserVariable1();
        if (!ZYPCommonFunc.hasValue(this.prmFlNm)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Lockbox File Name" });
        }

        // START 2022/12/14 T.Omura [QC#60929, ADD]
        this.lockBoxUpdateFlg = ZYPCodeDataUtil.getVarCharConstValue("LOCK_BOX_UPDATE_FLG", this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.lockBoxUpdateFlg)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"LockBox update Flag" });
        }
        // END 2022/12/14 T.Omura [QC#60929, ADD]
    }

    @Override
    protected void mainRoutine() {

        final BigDecimal[] tranIdAry = this.s21TranTblAccs.getIntegrationRecord(this.intfcId);

        if (tranIdAry.length != 0) {
            for (BigDecimal tranId : tranIdAry) {
                // START 2022/12/14 T.Omura [QC#60929, ADD]
                if (this.lockBoxUpdateFlg.equals(ZYPConstant.FLG_ON_Y)) {
                    interfacedataUpdateCheck(tranId);
                    commit();
                }
                // END 2022/12/14 T.Omura [QC#60929, ADD]
                // Create Lock Box File Name
                this.lockBoxFlNm = createLockBoxFileName();

                insertArRcptRcvIntfc(tranId);

                this.s21TranTblAccs.endIntegrationProcess(this.intfcId, tranId);

                super.commit();

                updateArRcptRcvIntfc();
            }
        } else {
            updateArRcptRcvIntfc();
        }
    }

    @Override
    protected void termRoutine() {
        this.ttlRecCnt = this.normRecCnt + this.errRecCnt;
        if (this.errRecCnt > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        super.setTermState(this.termCd, this.normRecCnt, this.errRecCnt, this.ttlRecCnt);
    }

    private String createLockBoxFileName() {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("lockBoxFlNm", this.prmFlNm + this.batchProcDt + STR_PERCENT);

        List<String> queryObjectList = (List<String>) ssmBatchClient.queryObjectList("getLockboxName", ssmParam);
        return createLockBoxFileName(queryObjectList);
    }

    private String createLockBoxFileName(List<String> valLsit) {

        if (valLsit.isEmpty()) {
            return this.prmFlNm + this.batchProcDt + FIRST_NUM;
        }

        int maxIndex = 0;
        String[] arLockBoxFileNmArray;
        String index = "";
        String existFileNm = "";
        Pattern p = Pattern.compile(CHK_PTTERN);
        for (String arLockBoxFileNm : valLsit) {
            arLockBoxFileNmArray = arLockBoxFileNm.split(STR_UNDERSCORE);
            index = arLockBoxFileNmArray[arLockBoxFileNmArray.length - 1];
            if (p.matcher(index).find()) {
                if (maxIndex < Integer.valueOf(index)) {
                    maxIndex = Integer.valueOf(index);
                }
                if (!ZYPCommonFunc.hasValue(existFileNm)) {
                    StringBuilder str = new StringBuilder();
                    for (int i = 0; i < arLockBoxFileNmArray.length - 1; i++) {
                        if (i != 0) {
                            str.append(STR_UNDERSCORE);
                        }
                        str.append(arLockBoxFileNmArray[i]);
                    }
                    existFileNm = str.toString();
                }
            } else {
                existFileNm = arLockBoxFileNm;
            }
        }

        if (maxIndex == 0) {
            return existFileNm + FIRST_NUM;
        } else {
            maxIndex++;
            return existFileNm + STR_UNDERSCORE //
                    + ZYPCommonFunc.leftPad(String.valueOf(maxIndex), 2, BigDecimal.ZERO.toString());
        }
    }

    private void insertArRcptRcvIntfc(BigDecimal tranId) {
        PreparedStatement nfci1010Stmt = null;
        ResultSet nfci1010Rs = null;
        // START 2018/07/04 H.Ikeda [QC#25731,DEL]
        //boolean isIncreased = false;
        // END   2018/07/04 H.Ikeda [QC#25731,DEL]
        // START 2018/07/04 H.Ikeda [QC#25731,ADD]
        boolean fileErrFlg = false;
        List<AR_RCPT_RCV_INTFCTMsg> insTMsgList = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();
        //String[] nfci1040Line = null;
        // END   2018/07/04 H.Ikeda [QC#25731,ADD]
        try {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("intfcId", this.intfcId);
            ssmParam.put("tranId", tranId);

            nfci1010Stmt = this.ssmLLClient.createPreparedStatement("getInterface", ssmParam);
            nfci1010Rs = nfci1010Stmt.executeQuery();

            // START 2018/07/04 H.Ikeda [QC#25731,MOD]
            // START 2018/05/18 H.Ikeda [QC#25914,ADD]
            //boolean fileErrFlg = isChkFileData(nfci1010Rs);
            //nfci1010Rs.beforeFirst();
            // END   2018/05/18 H.Ikeda [QC#25914,ADD]
            PreparedStatement ps = this.ssmLLClient.createPreparedStatement("getChkFileData", ssmParam);
            ResultSet rs = ps.executeQuery();

            if (isChkFileData(rs)) {
                fileErrFlg = true;
            }
            rs.beforeFirst();
            Map<String, Map<String, Object>> errCheckDataMap = new HashMap<String, Map<String, Object>>();
            while (rs.next()) {
                Map<String, Object> ssmErrChkParam = new HashMap<String, Object>();
                ssmErrChkParam.put("intfcId", this.intfcId);
                ssmErrChkParam.put("tranId", tranId);
                ssmErrChkParam.put("divideVal", DIVIDE_VAL);
                ssmErrChkParam.put("batRcptNum", rs.getString("BAT_RCPT_NUM_06"));
                // START 2018/08/22 H.Ikeda [QC#27776,MOD]
                Map<String, Object> batRcptInfo = null;
                if ("NFCI1050".equals(this.intfcId)) {
                    batRcptInfo = (Map<String, Object>) ssmBatchClient.queryObject("getBatRcptInfo1050", ssmErrChkParam);
                } else {
                    batRcptInfo = (Map<String, Object>) ssmBatchClient.queryObject("getBatRcptInfo", ssmErrChkParam);
                }
                // END    2018/08/22 H.Ikeda [QC#27776,MOD]
                errCheckDataMap.put(rs.getString("BAT_RCPT_NUM_06"), batRcptInfo);
            }
            // END   2018/07/04 H.Ikeda [QC#25731,MOD]
            // START 2018/08/22 H.Ikeda [QC#27776,ADD]
            BigDecimal NFCI1050Cnt = BigDecimal.ZERO;
            if ("NFCI1050".equals(this.intfcId)) {
                Map<String, Object> ssmCntParam = new HashMap<String, Object>();
                ssmCntParam.put("intfcId", this.intfcId);
                ssmCntParam.put("tranId", tranId);
                NFCI1050Cnt = (BigDecimal) ssmBatchClient.queryObject("getCfsMdBatchCnt", ssmCntParam);
            } 
            // END   2018/08/22 H.Ikeda [QC#27776,ADD]

            while (nfci1010Rs.next()) {

                AR_RCPT_RCV_INTFCTMsg tMsg = new AR_RCPT_RCV_INTFCTMsg();
                // START 2018/08/22 H.Ikeda [QC#27776,MOD]
                //tMsg = setArRcptRcvIntfc(tMsg, nfci1010Rs);
                tMsg = setArRcptRcvIntfc(tMsg, nfci1010Rs, NFCI1050Cnt);
                // END   2018/08/22 H.Ikeda [QC#27776,MOD]

                // START 2018/05/18 H.Ikeda [QC#25914,ADD]
                if (fileErrFlg) {
                    // The same file has already been imported. IntfcId = @, DEP_DT = @, DEP_TM = @
                    tMsg = setError(tMsg, NFCM0884E, this.intfcId);
                } else {
                    // START 2018/02/01 T.Tsuchida [QC#23053,DEL]
                    //tMsg = firstFileCheck(tMsg, tranId);
                    // END 2018/02/01 T.Tsuchida [QC#23053,DEL]

                    if (ZYPCommonFunc.hasValue(tMsg.batRcptRecCnt)) {
                        //tMsg = batRcptCheck(tMsg, tranId);
                        tMsg = batRcptCheck(tMsg, errCheckDataMap);
                    }
                    // START 2016/11/09 S.Fujita [QC#15742,DEL]
//                    isIncreased = increaseRecord(nfci1010Rs, tMsg);
                    // END   2016/11/09 S.Fujita [QC#15742,DEL]
                    // START 2018/05/07 E.Kameishi [QC#25721,MOD]
                    // START 2017/05/17 E.Kameishi [QC#18594,ADD]
                    //if (ZYPCommonFunc.hasValue(tMsg.custInvAmt)) {
                    //    if (!ZYPCommonFunc.hasValue(tMsg.custInvNum)) {
                    //        tMsg = setError(tMsg, NFCM0744E, "Customer Invoice Number");
                    //    }
                    //} else {
                    if (!ZYPCommonFunc.hasValue(tMsg.custInvAmt)) {
                        if (!ZYPCommonFunc.hasValue(tMsg.custInvNum)) {
                            ZYPEZDItemValueSetter.setValue(tMsg.custInvAmt, BigDecimal.ZERO);
                            tMsg = setWarning(tMsg, NFCM0876W, null);
                        }
                    }
                    // END 2017/05/17 E.Kameishi [QC#18594,ADD]
                    // END 2018/05/07 E.Kameishi [QC#25721,MOD]
                }
                // END   2018/05/18 H.Ikeda [QC#25914,ADD]
                // START 2018/06/14 H.Ikeda [QC#25731,MOD]
                //if (!isIncreased) {
                //    insertArRcptRcvIntfcTBL(tMsg);
                //    addRecord(nfci1010Rs, tMsg);
                //}
                insTMsgList.add(tMsg);
                addRecord(nfci1010Rs, tMsg, insTMsgList);
                if (insTMsgList.size() >= COMMIT_CNT) {
                    int rtn = EZDFastTBLAccessor.insert(insTMsgList.toArray(new AR_RCPT_RCV_INTFCTMsg[insTMsgList.size()]));
                    if (rtn != insTMsgList.size()) {
                        this.errRecCnt = this.errRecCnt + (insTMsgList.size() - rtn);
                    }
                    insTMsgList.clear();
                    commit();
                }
            }
            if (insTMsgList.size() != 0) {
                // remaining
                int rtn = EZDFastTBLAccessor.insert(insTMsgList.toArray(new AR_RCPT_RCV_INTFCTMsg[insTMsgList.size()]));
                if (rtn != insTMsgList.size()) {
                    this.errRecCnt = this.errRecCnt + (insTMsgList.size() - rtn);
                }
                insTMsgList.clear();
                commit();
            }
            // END   2018/07/04 H.Ikeda [QC#25731,MOD]
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(nfci1010Stmt, nfci1010Rs);
        }

    }
    // ADD 2022/12/14 T.Omura [QC#60929, ADD]
    private void interfacedataUpdateCheck(BigDecimal tranId) {

        List<Map<String, Object>> nFCI1010Info06List = getNFCI1010Info06(tranId);
        List<Map<String, Object>> nFCI1010Info07List = getNFCI1010Info07(tranId);
        List<BigDecimal> checkSeqNumList = new ArrayList<BigDecimal>();

        if (nFCI1010Info06List == null || nFCI1010Info07List == null) {
            return;
        }
        for (Map<String,Object> nFCI1010Info06 : nFCI1010Info06List) {
            int cnt07 = 0;
            for (Map<String,Object> nFCI1010Info07 : nFCI1010Info07List) {
                cnt07++;
                if (checkSeqNumList.contains(nFCI1010Info07.get("SEQ_NUMBER"))) {
                    continue;
                }
                if (nFCI1010InfoMapping(nFCI1010Info06,nFCI1010Info07,checkSeqNumList)) {
                    checkSeqNumList.add((BigDecimal)nFCI1010Info07.get("SEQ_NUMBER"));
                    break;
                }
                if(nFCI1010Info07List.size() == cnt07) {
                    throw new S21AbendException("ZZXM0001E", new String[] {"Batch Receipt Number record does not exists in Trailer. It maybe has the difference data on cont or amount." });
                }
            }
        }
    }

    private boolean nFCI1010InfoMapping(Map<String,Object> nFCI1010Info06, Map<String,Object> nFCI1010Info07,List<BigDecimal> checkSeqNumList) {
        if(nFCI1010Info06.get("TOT_AMT").equals(nFCI1010Info07.get("INTFC_BAT_RCPT_AMT")) &&
                nFCI1010Info06.get("TOT_CNT").equals(nFCI1010Info07.get("BAT_RCPT_REC_CNT"))) {
            updateBatRcptNum(nFCI1010Info07, (String)nFCI1010Info06.get("BAT_RCPT_NUM"));
            return true;
        }
        return false;
    }

    private void updateBatRcptNum(Map<String,Object> nFCI1010Info07,String batRcptNum) {
        NFCI1010_07TMsg tMsg = new NFCI1010_07TMsg();
        tMsg.interfaceId.setValue((String)nFCI1010Info07.get("INTERFACE_ID"));
        tMsg.transactionId.setValue((BigDecimal)nFCI1010Info07.get("TRANSACTION_ID"));
        tMsg.segmentId.setValue((BigDecimal)nFCI1010Info07.get("SEGMENT_ID"));
        tMsg.unitId.setValue((BigDecimal)nFCI1010Info07.get("UNIT_ID"));
        tMsg.seqNumber.setValue((BigDecimal)nFCI1010Info07.get("SEQ_NUMBER"));
        tMsg = (NFCI1010_07TMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
        if(tMsg == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.batRcptNum, batRcptNum);

        EZDTBLAccessor.update(tMsg);
        if(!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NFCM0615E, new String[] {"NFCI1010_07.BAT_RCPT_NUM" });
        }
    }
    private List<Map<String, Object>> getNFCI1010Info06(BigDecimal tranId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tranId", tranId);
        List<Map<String, Object>> returnInfo = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getNFCI1010_06", param);
        return returnInfo;
    }
    private List<Map<String, Object>> getNFCI1010Info07(BigDecimal tranId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tranId", tranId);
        List<Map<String, Object>> returnInfo = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getNFCI1010_07", param);
        return returnInfo;
    }
    // END 2022/12/14 T.Omura [QC#60929, ADD]

//    // START 2018/06/28 H.Ikeda [QC#25731,ADD]
//    private String[] lineStr(BigDecimal tranId) throws SQLException{
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<String> lineList = new ArrayList<String>();
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("intfcId", this.intfcId);
//        ssmParam.put("tranId", tranId);
//
//        ps = this.ssmLLClient.createPreparedStatement("getLineNum", ssmParam);
//        rs = ps.executeQuery();
//        while (rs.next()) {
//            String lineNum = rs.getString("BAT_RCPT_LINE_NUM");
//            int no = chgStr(lineNum) % 10;
//            int cnt = Integer.parseInt(this.multipleCnt);
//            if (no == cnt) {
//                lineList.add(lineNum);
//            }
//        }
//        if (lineList.size() == 0) {
//            return null;
//        } else {
//            String[] lineStr = new String[lineList.size()];
//            for (int i = 0; i < lineList.size(); i++) {
//                lineStr[i] = lineList.get(i);
//            }
//            return lineStr;
//        }
//    }
//
//    private int chgStr(String item) {
//        if (item == null) {
//            return 0;
//        }
//        Pattern ptn = Pattern.compile("^0+([0-9]+.*)");
//        Matcher mch = ptn.matcher(item);
//        String output = null;
//        if (mch.matches()) {
//           output = mch.group(1);
//           if (output == null) {
//               return 0;
//           }
//        }
//        return Integer.parseInt(output);
//    }
//    // END   2018/06/28 H.Ikeda [QC#25731,ADD]

    // START 2018/05/18 H.Ikeda [QC#25914,ADD]
    /**
     * isChkFileData
     * 
     * @param nfci1010Rs
     * @return boolean
     * @throws SQLException
     */
    private boolean isChkFileData(ResultSet nfci1010Rs) throws SQLException{
        while (nfci1010Rs.next()) {
            Map<String, Object> ssmErrChkParam = new HashMap<String, Object>();
            ssmErrChkParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmErrChkParam.put("rcvFuncId", this.intfcId);
            ssmErrChkParam.put("depDt", nfci1010Rs.getString("LOCK_BOX_DEP_DT"));
            ssmErrChkParam.put("depTm", nfci1010Rs.getString("LOCK_BOX_DEP_TM"));
            ssmErrChkParam.put("arLockBoxBatNum", nfci1010Rs.getString("BAT_RCPT_NUM_06"));
            BigDecimal errDataCnt = (BigDecimal) ssmBatchClient.queryObject("getArErrFileDataCnt", ssmErrChkParam);
            if (errDataCnt != null && errDataCnt.compareTo(BigDecimal.ZERO) != 0) {
                return true;
            }
        }

        return false;
    }
    // END   2018/05/18 H.Ikeda [QC#25914,ADD]

    // START 2016/11/09 S.Fujita [QC#15742,DEL]
//    private boolean increaseRecord(ResultSet nfci1010Rs, AR_RCPT_RCV_INTFCTMsg tMsg) throws SQLException {
//        boolean isIncrease = false;
//        if (ZYPCommonFunc.hasValue(nfci1010Rs.getString("UNIT_ID_04"))) {
//            if (!nvl(nfci1010Rs.getString("BAT_RCPT_NUM_06")).equals(nvl(nfci1010Rs.getString("BAT_RCPT_NUM_04"))) //
//                    || !nvl(nfci1010Rs.getString("BAT_RCPT_LINE_NUM_06")).equals(nvl(nfci1010Rs.getString("BAT_RCPT_LINE_NUM_04")))) {
//                AR_RCPT_RCV_INTFCTMsg invTMsg = new AR_RCPT_RCV_INTFCTMsg();
//
//                EZDMsg.copy(tMsg, null, invTMsg, null);
//
//                tMsg.custInvNum.clear();
//                tMsg.arLockBoxBatLineSqNum.clear();
//                insertArRcptRcvIntfcTBL(tMsg);
//                tMsg.custInvAmt.clear();
//
//                ZYPEZDItemValueSetter.setValue(invTMsg.arRcptRcvIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.AR_RCPT_RCV_INTFC_SQ));
//                invTMsg.custBankRteNum.clear();
//                invTMsg.custDsBankAcctNum.clear();
//                invTMsg.custDsBankMicrNum.clear();
//                invTMsg.custRcptNum.clear();
//                invTMsg.custRcptAmt.clear();
//                ZYPEZDItemValueSetter.setValue(invTMsg.arLockBoxBatNum, nfci1010Rs.getString("BAT_RCPT_NUM_04"));
//                ZYPEZDItemValueSetter.setValue(invTMsg.arLockBoxBatLineNum, nfci1010Rs.getString("BAT_RCPT_LINE_NUM_04"));
//
//                insertArRcptRcvIntfcTBL(invTMsg);
//                addRecord(nfci1010Rs, invTMsg);
//                isIncrease = true;
//            }
//        }
//
//        if (ZYPCommonFunc.hasValue(nfci1010Rs.getString("UNIT_ID_07"))) {
//            if (!nvl(nfci1010Rs.getString("BAT_RCPT_NUM_06")).equals(nvl(nfci1010Rs.getString("BAT_RCPT_NUM_07"))) //
//                    && !ZYPCommonFunc.hasValue(nfci1010Rs.getString("BAT_RCPT_NUM_07"))) {
//                AR_RCPT_RCV_INTFCTMsg invTMsg = new AR_RCPT_RCV_INTFCTMsg();
//                EZDMsg.copy(tMsg, null, invTMsg, null);
//
//                insertArRcptRcvIntfcTBL(tMsg);
//
//                ZYPEZDItemValueSetter.setValue(invTMsg.arRcptRcvIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.AR_RCPT_RCV_INTFC_SQ));
//                invTMsg.custBankRteNum.clear();
//                invTMsg.custDsBankAcctNum.clear();
//                invTMsg.custDsBankMicrNum.clear();
//                invTMsg.custRcptNum.clear();
//                invTMsg.custRcptAmt.clear();
//
//                if (!ZYPCommonFunc.hasValue(nfci1010Rs.getString("UNIT_ID_04"))) {
//                    invTMsg.custInvNum.clear();
//                    invTMsg.custBankRteNum.clear();
//                    invTMsg.arLockBoxBatNum.clear();
//                    invTMsg.arLockBoxBatLineNum.clear();
//                    invTMsg.arLockBoxBatLineSqNum.clear();
//                }
//
//                insertArRcptRcvIntfcTBL(invTMsg);
//
//                addRecord(nfci1010Rs, invTMsg);
//
//                isIncrease = true;
//            }
//        }
//
//        return isIncrease;
//    }
    // END   2016/11/09 S.Fujita [QC#15742,DEL]

    // START 2018/06/14 H.Ikeda [QC#25731,MOD]
    //private void addRecord(ResultSet nfci1010Rs, AR_RCPT_RCV_INTFCTMsg tMsg) throws SQLException {
    private void addRecord(ResultSet nfci1010Rs, AR_RCPT_RCV_INTFCTMsg tMsg, List<AR_RCPT_RCV_INTFCTMsg> insTMsgList) throws SQLException {
    // END   2018/06/14 H.Ikeda [QC#25731,MOD]
        if (ZYPCommonFunc.hasValue(nfci1010Rs.getString("CUST_INV_NUM_02")) //
                // START 2016/08/23 S.Fujita [QC#13266,MOD]
//                || ZYPCommonFunc.hasValue(nfci1010Rs.getString("INTFC_CUST_INV_AMT_02"))) {
                && ZYPCommonFunc.hasValue(nfci1010Rs.getString("INTFC_CUST_INV_AMT_02"))) {
                // END   2016/08/23 S.Fujita [QC#13266,MOD]
            // START 2016/11/15 S.Fujita [QC#15742,MOD]
//            if (!STR_NOT_CRAT_INV_NUM.equals(nfci1010Rs.getString("CUST_INV_NUM_02"))) {
            if (!STR_NOT_CRAT_INV_NUM.equals(nfci1010Rs.getString("CUST_INV_NUM_02")) || nfci1010Rs.getBigDecimal("INTFC_CUST_INV_AMT_02").compareTo(BigDecimal.ZERO) > 0) {
            // END   2016/11/15 S.Fujita [QC#15742,MOD]
                AR_RCPT_RCV_INTFCTMsg invTMsg = new AR_RCPT_RCV_INTFCTMsg();
                EZDMsg.copy(tMsg, null, invTMsg, null);
                ZYPEZDItemValueSetter.setValue(invTMsg.arRcptRcvIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.AR_RCPT_RCV_INTFC_SQ));
                // START 2016/11/15 S.Fujita [QC#15742,MOD]
//                ZYPEZDItemValueSetter.setValue(invTMsg.custInvNum, nfci1010Rs.getString("CUST_INV_NUM_02"));
                if (nfci1010Rs.getString("CUST_INV_NUM_02").length() > SUBSTRING_LEN13) {
                    ZYPEZDItemValueSetter.setValue(invTMsg.custInvNum, nfci1010Rs.getString("CUST_INV_NUM_02").substring(0, SUBSTRING_LEN13));
                } else {
                    ZYPEZDItemValueSetter.setValue(invTMsg.custInvNum, nfci1010Rs.getString("CUST_INV_NUM_02"));
                }
                // END   2016/11/15 S.Fujita [QC#15742,MOD]
                ZYPEZDItemValueSetter.setValue(invTMsg.custInvAmt, nfci1010Rs.getBigDecimal("INTFC_CUST_INV_AMT_02").divide(DIVIDE_VAL));
                // START 2018/06/14 H.Ikeda [QC#25731,MOD]
                //insertArRcptRcvIntfcTBL(invTMsg);
                insTMsgList.add(invTMsg);
                // END   2018/06/14 H.Ikeda [QC#25731,MOD]
            }
        }

    }

    // START 2018/06/28 H.Ikeda [QC#25731,DEL]
    //private void insertArRcptRcvIntfcTBL(AR_RCPT_RCV_INTFCTMsg tMsg) {
    //    EZDTBLAccessor.insert(tMsg);
    //    if (errCheck(tMsg)) {
    //        this.errRecCnt = this.errRecCnt + 1;
    //    }
    //}
    // END   2018/06/28 H.Ikeda [QC#25731,DEL]
    // START 2018/08/22 H.Ikeda [QC#27776,MOD]
    //private AR_RCPT_RCV_INTFCTMsg setArRcptRcvIntfc(AR_RCPT_RCV_INTFCTMsg tMsg, ResultSet nfci1010Rs) throws SQLException {
    private AR_RCPT_RCV_INTFCTMsg setArRcptRcvIntfc(AR_RCPT_RCV_INTFCTMsg tMsg, ResultSet nfci1010Rs, BigDecimal NFCI1050Cnt) throws SQLException {
    // END   2018/08/22 H.Ikeda [QC#27776,MOD]
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.AR_RCPT_RCV_INTFC_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.rcvFuncId, this.intfcId);
        ZYPEZDItemValueSetter.setValue(tMsg.rcvDt, this.batchProcDt);
        ZYPEZDItemValueSetter.setValue(tMsg.depDt, nfci1010Rs.getString("LOCK_BOX_DEP_DT"));
        ZYPEZDItemValueSetter.setValue(tMsg.depTm, nfci1010Rs.getString("LOCK_BOX_DEP_TM"));
        // START 2018/11/16 H.Ikeda [QC#29278, ADD]
        this.depDt = nfci1010Rs.getString("LOCK_BOX_DEP_DT");
        this.depTm = nfci1010Rs.getString("LOCK_BOX_DEP_TM");
        // END   2018/11/16 H.Ikeda [QC#29278, ADD]                          
        ZYPEZDItemValueSetter.setValue(tMsg.arBatRcptNm, this.lockBoxFlNm + STR_UNDERSCORE + nvl(nfci1010Rs.getString("BAT_RCPT_NUM_06")));
        ZYPEZDItemValueSetter.setValue(tMsg.remBankRteNum, nfci1010Rs.getString("CSA_BANK_RTE_NUM_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.remDsBankAcctNum, nfci1010Rs.getString("CSA_BANK_ACCT_NUM_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.remDsBankMicrNum, nfci1010Rs.getString("CSA_BANK_MICR_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.custBankRteNum, nfci1010Rs.getString("CUST_BANK_RTE_NUM_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.custDsBankAcctNum, nfci1010Rs.getString("CUST_BANK_ACCT_NUM_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.custDsBankMicrNum, nfci1010Rs.getString("CUST_BANK_MICR_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.custRcptNum, nfci1010Rs.getString("CUST_RCPT_NUM"));

        if (ZYPCommonFunc.hasValue(nfci1010Rs.getBigDecimal("INTFC_CUST_RCPT_AMT"))) {
            ZYPEZDItemValueSetter.setValue(tMsg.custRcptAmt, nfci1010Rs.getBigDecimal("INTFC_CUST_RCPT_AMT").divide(DIVIDE_VAL));
        }

        // START 2016/11/15 S.Fujita [QC#15742,MOD]
//        ZYPEZDItemValueSetter.setValue(tMsg.custInvNum, nfci1010Rs.getString("CUST_INV_NUM_01"));
        // START 2017/05/17 E.Kameishi [QC#18594,MOD]
        if (ZYPCommonFunc.hasValue(nfci1010Rs.getString("CUST_INV_NUM_01")) && nfci1010Rs.getString("CUST_INV_NUM_01").length() > SUBSTRING_LEN13) {
            ZYPEZDItemValueSetter.setValue(tMsg.custInvNum, nfci1010Rs.getString("CUST_INV_NUM_01").substring(0, SUBSTRING_LEN13));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.custInvNum, nfci1010Rs.getString("CUST_INV_NUM_01"));
        }
        // END 2017/05/17 E.Kameishi [QC#18594,MOD]
        // END   2016/11/15 S.Fujita [QC#15742,MOD]
        if (ZYPCommonFunc.hasValue(nfci1010Rs.getBigDecimal("INTFC_CUST_INV_AMT_01"))) {
            ZYPEZDItemValueSetter.setValue(tMsg.custInvAmt, nfci1010Rs.getBigDecimal("INTFC_CUST_INV_AMT_01").divide(DIVIDE_VAL));
        }
        ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvErrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.arCustIdStsCd, AR_CUST_ID_STS.SAVED);
        ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvWrkCratFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxStsCd, AR_LOCK_BOX_STS.TEMPORARY_SAVED);
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxFileNm, this.lockBoxFlNm);
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxBatNum, nfci1010Rs.getString("BAT_RCPT_NUM_06"));
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxBatLineNum, nfci1010Rs.getString("BAT_RCPT_LINE_NUM_06"));
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxBatLineSqNum, nfci1010Rs.getString("BAT_RCPT_SQ_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.hdrLockBoxNumTxt, nfci1010Rs.getString("HDR_LOCK_BOX_NUM_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.batLockBoxNumTxt, nfci1010Rs.getString("BAT_LOCK_BOX_NUM_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.trailLockBoxNumTxt, nfci1010Rs.getString("TRIAL_LOCK_BOX_NUM_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxFileErrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcFileRecCnt, nfci1010Rs.getBigDecimal("FILE_REC_CNT"));
        ZYPEZDItemValueSetter.setValue(tMsg.lockBoxRecCnt, nfci1010Rs.getBigDecimal("LOCK_BOX_REC_CNT"));
        if (ZYPCommonFunc.hasValue(nfci1010Rs.getBigDecimal("INTFC_LOCK_BOX_AMT"))) {
            ZYPEZDItemValueSetter.setValue(tMsg.lockBoxTotAmt, nfci1010Rs.getBigDecimal("INTFC_LOCK_BOX_AMT").divide(DIVIDE_VAL));
        }
        // START 2018/08/22 H.Ikeda [QC#27776,MOD]
        if ("NFCI1050".equals(this.intfcId)) {
            ZYPEZDItemValueSetter.setValue(tMsg.batRcptRecCnt, NFCI1050Cnt);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.batRcptRecCnt, nfci1010Rs.getBigDecimal("BAT_RCPT_REC_CNT"));
        }
        // END   2018/08/22 H.Ikeda [QC#27776,MOD]
        if (ZYPCommonFunc.hasValue(nfci1010Rs.getBigDecimal("INTFC_BAT_RCPT_AMT"))) {
            ZYPEZDItemValueSetter.setValue(tMsg.batRcptTotAmt, nfci1010Rs.getBigDecimal("INTFC_BAT_RCPT_AMT").divide(DIVIDE_VAL));
        }
        return tMsg;
    }

    // Start   2018/06/14 H.Ikeda [QC#25731,MOD]
//    private void updateArRcptRcvIntfc() {
//        final Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
//        // START 2016/12/05 S.Fujita [QC#16255,ADD]
//        ssmParam.put("intfcId", this.intfcId);
//        // END   2016/12/05 S.Fujita [QC#16255,ADD]
//        ssmParam.put("saved", AR_LOCK_BOX_STS.TEMPORARY_SAVED);
//        ssmParam.put("reprocess", AR_LOCK_BOX_STS.REPROCESS);
//
//        List<BigDecimal> queryObjectList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getArRcptRcvIntfcPk", ssmParam);
//        if (queryObjectList.isEmpty()) {
//            // No target Record.
//            return;
//        }
//
//        boolean isFirst = true;
//        // START 2016/10/18 [QC#14026, DEL]
////        boolean isFileError = false;
//        // END 2016/10/18 [QC#14026, DEL]
//        // START 2018/06/14 H.Ikeda [QC#25731,ADD]
//        List<AR_RCPT_RCV_INTFCTMsg> updTMsgList = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();
//        // END   2018/06/14 H.Ikeda [QC#25731,ADD]
//        AR_RCPT_RCV_INTFCTMsg inMsg = new AR_RCPT_RCV_INTFCTMsg();
//        for (BigDecimal arRcptRcvIntfcPk : queryObjectList) {
//            inMsg.clear();
//            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(inMsg.arRcptRcvIntfcPk, arRcptRcvIntfcPk);
//            AR_RCPT_RCV_INTFCTMsg updTMsg = (AR_RCPT_RCV_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
//
//            deleteArRcptRcvInfoWrk(arRcptRcvIntfcPk);
//
//            // START 2017/05/29 E.Kameishi [QC#18594,ADD]
//            boolean checkFlg = checkArRcptRcvInfoWrk(arRcptRcvIntfcPk);
//            // END 2017/05/29 E.Kameishi [QC#18594,ADD]
//
//            // START 2016/12/05 S.Fujita [QC#16255,ADD]
//            updTMsg = updErrFlg(updTMsg);
//            // END   2016/12/05 S.Fujita [QC#16255,ADD]
//            if (isFirst) {
//                updTMsg = firstRecMandatoryCheck(updTMsg);
//                updTMsg = mandatoryCheck(updTMsg, checkFlg);
//                updTMsg = masterCheckAndSet(updTMsg);
//                isFirst = false;
//            } else {
//                updTMsg = mandatoryCheck(updTMsg, checkFlg);
//                updTMsg = masterCheckAndSet(updTMsg);
//            }
//            updTMsg = afterBatRcptCheck(updTMsg, checkFlg);
//
//            if (errCheck(updTMsg)) {
//                // START 2016/10/18 [QC#14026, MOD]
////                isFileError = true;
//                ZYPEZDItemValueSetter.setValue(updTMsg.arLockBoxStsCd, AR_LOCK_BOX_STS.ERROR);
//                ZYPEZDItemValueSetter.setValue(updTMsg.arLockBoxFileErrFlg, ZYPConstant.FLG_ON_Y);
//                // END 2016/10/18 [QC#14026, MOD]
//                this.errRecCnt = this.errRecCnt + 1;
//            } else {
//                ZYPEZDItemValueSetter.setValue(updTMsg.arLockBoxStsCd, AR_LOCK_BOX_STS.WORK_IN_PROGRESS);
//                this.normRecCnt = this.normRecCnt + 1;
//
//            }
//            // START 2018/06/14 H.Ikeda [QC#25731,MOD]
//            //EZDTBLAccessor.update(updTMsg);
//            //commit();
//            updTMsgList.add(updTMsg);
//            if (updTMsgList.size() >= 1000) {
//                int rtn = EZDFastTBLAccessor.update(updTMsgList.toArray(new AR_RCPT_RCV_INTFCTMsg[updTMsgList.size()]));
//                if (rtn != updTMsgList.size()) {
//                    throw new S21AbendException(NFCM0615E, new String[] {"Lockbox File Name" });
//                }
//                updTMsgList.clear();
//                commit();
//            }
//            // END   2018/06/14 H.Ikeda [QC#25731,MOD]
//        }
//        // START 2018/06/14 H.Ikeda [QC#25731,ADD]
//        // remaining
//        int rtn = EZDFastTBLAccessor.update(updTMsgList.toArray(new AR_RCPT_RCV_INTFCTMsg[updTMsgList.size()]));
//        if (rtn != updTMsgList.size()) {
//            throw new S21AbendException(NFCM0615E, new String[] {"Lockbox File Name" });
//        }
//        updTMsgList.clear();
//        commit();
//        // END   2018/06/14 H.Ikeda [QC#25731,ADD]
//        
//        // START 2016/10/18 [QC#14026, DEL]
////        if (isFileError) {
////            for (BigDecimal arRcptRcvIntfcPk : queryObjectList) {
////                inMsg.clear();
////                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
////                ZYPEZDItemValueSetter.setValue(inMsg.arRcptRcvIntfcPk, arRcptRcvIntfcPk);
////                AR_RCPT_RCV_INTFCTMsg updTMsg = (AR_RCPT_RCV_INTFCTMsg) EZDTBLAccessor.findByKey(inMsg);
////                ZYPEZDItemValueSetter.setValue(updTMsg.arLockBoxStsCd, AR_LOCK_BOX_STS.ERROR);
////                ZYPEZDItemValueSetter.setValue(updTMsg.arLockBoxFileErrFlg, ZYPConstant.FLG_ON_Y);
////                EZDTBLAccessor.update(updTMsg);
////            }
////        }
//        // END 2016/10/18 [QC#14026, DEL]
//
//    }
    private void updateArRcptRcvIntfc() {

        boolean isFirst = true;
        String errCd = null;
        String arLockBoxCd = null;
        List<Map<String, Object>> bankAcctList = null;
        String srcMicrNum = null;
        BigDecimal remDsBankAcctPk = null;
        String arRcptSrcCd = null;
        List<BigDecimal> arRcptRcvInfoWrkWarnList = null;
        List<BigDecimal> arRcptRcvInfoWrkList = null;
        PreparedStatement nfci1010Stmt = null;
        ResultSet nfci1010Rs = null;
        List<AR_RCPT_RCV_INTFCTMsg> updTMsgList = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();

        try {
            final Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("intfcId", this.intfcId);
            ssmParam.put("saved", AR_LOCK_BOX_STS.TEMPORARY_SAVED);
            ssmParam.put("reprocess", AR_LOCK_BOX_STS.REPROCESS);
            nfci1010Stmt = this.ssmLLClient.createPreparedStatement("getArRcptRcvIntfcPk", ssmParam);
            nfci1010Rs = nfci1010Stmt.executeQuery();

            while (nfci1010Rs.next()) {
                if (isFirst) {
                    arLockBoxCd = masterCheckArLockBoxCd(nfci1010Rs.getString("HDR_LOCK_BOX_NUM_TXT"));
                    if (arLockBoxCd == null) {
                        errCd = NFCM0746E;
                    } else {
                        bankAcctList = masterCheckBankAcctList(arLockBoxCd);
                        if (bankAcctList == null) {
                            errCd = NFCM0747E;
                        } else {
                            srcMicrNum = nvl((String) bankAcctList.get(0).get("DS_BANK_MICR_NUM"));
                            remDsBankAcctPk = (BigDecimal) bankAcctList.get(0).get("DS_BANK_ACCT_PK");
                            arRcptSrcCd = (String) bankAcctList.get(0).get("AR_RCPT_SRC_CD");
                            if (masterCheckArRcptSrcCnt(arRcptSrcCd)) {
                                errCd = NFCM0748E;
                            }
                        }
                    }

                    arRcptRcvInfoWrkWarnList = checkArRcptRcvInfoWrkWarnList();
                    arRcptRcvInfoWrkList = checkArRcptRcvInfoWrkList();
                }

                AR_RCPT_RCV_INTFCTMsg updTMsg = new AR_RCPT_RCV_INTFCTMsg();
                updTMsg = setArRcptRcvIntfcUpdate(updTMsg, nfci1010Rs, arLockBoxCd, remDsBankAcctPk, arRcptSrcCd);
                if (errCd != null){
                    updTMsg = setError(updTMsg, errCd, null);
                }

                deleteArRcptRcvInfoWrk(updTMsg.arRcptRcvIntfcPk.getValue(), arRcptRcvInfoWrkList) ;

                boolean checkFlg = checkArRcptRcvInfoWrk(updTMsg.arRcptRcvIntfcPk.getValue(), arRcptRcvInfoWrkWarnList);

                updTMsg = updErrFlg(updTMsg);
                if (isFirst) {
                    updTMsg = firstRecMandatoryCheck(updTMsg);
                    updTMsg = mandatoryCheck(updTMsg, checkFlg);
                    if (errCd == null) {
                        updTMsg = masterCheckAndSet(updTMsg, srcMicrNum);
                    }
                    isFirst = false;
                } else {
                    updTMsg = mandatoryCheck(updTMsg, checkFlg);
                    if (errCd == null) {
                        updTMsg = masterCheckAndSet(updTMsg, srcMicrNum);
                    }
                }

                updTMsg = afterBatRcptCheck(updTMsg, checkFlg);

                if (errCheck(updTMsg)) {
                    ZYPEZDItemValueSetter.setValue(updTMsg.arLockBoxStsCd, AR_LOCK_BOX_STS.ERROR);
                    ZYPEZDItemValueSetter.setValue(updTMsg.arLockBoxFileErrFlg, ZYPConstant.FLG_ON_Y);
                    this.errRecCnt = this.errRecCnt + 1;
                } else {
                    ZYPEZDItemValueSetter.setValue(updTMsg.arLockBoxStsCd, AR_LOCK_BOX_STS.WORK_IN_PROGRESS);
                    this.normRecCnt = this.normRecCnt + 1;
                }

                updTMsgList.add(updTMsg);
                if (updTMsgList.size() >= 1000) {
                    int rtn = EZDFastTBLAccessor.update(updTMsgList.toArray(new AR_RCPT_RCV_INTFCTMsg[updTMsgList.size()]));
                    if (rtn != updTMsgList.size()) {
                        throw new S21AbendException(NFCM0615E, new String[] {"Lockbox File Name" });
                    }
                    updTMsgList.clear();
                    commit();
                }
            }
            if (updTMsgList.size() != 0) {
                // remaining
                int rtn = EZDFastTBLAccessor.update(updTMsgList.toArray(new AR_RCPT_RCV_INTFCTMsg[updTMsgList.size()]));
                if (rtn != updTMsgList.size()) {
                    throw new S21AbendException(NFCM0615E, new String[] {"Lockbox File Name" });
                }
            }

            // START 2018/07/20 H.Ikeda [QC#24991,ADD]
                PreparedStatement ps = null;
                ResultSet rs = null;
                isFirst = true;
                updTMsgList = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();
                try {
                    final Map<String, Object> sqlParam = new HashMap<String, Object>();
                    sqlParam.put("glblCmpyCd", this.glblCmpyCd);
                    sqlParam.put("intfcId", this.intfcId);
                    sqlParam.put("error", AR_LOCK_BOX_STS.ERROR);
                    // START 2018/11/16 H.Ikeda [QC#29278, ADD]
                    sqlParam.put("lockBoxNtfyStsCd", LOCK_BOX_NTFY_STS.SEND);
                    // END   2018/11/16 H.Ikeda [QC#29278, ADD]
                    ps = this.ssmLLClient.createPreparedStatement("getArRcptRcvIntfcErr", sqlParam);
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        if (isFirst) {
                            arLockBoxCd = masterCheckArLockBoxCd(rs.getString("HDR_LOCK_BOX_NUM_TXT"));
                            if (arLockBoxCd == null) {
                                errCd = NFCM0746E;
                            } else {
                                bankAcctList = masterCheckBankAcctList(arLockBoxCd);
                                if (bankAcctList == null) {
                                    errCd = NFCM0747E;
                                } else {
                                    srcMicrNum = nvl((String) bankAcctList.get(0).get("DS_BANK_MICR_NUM"));
                                    remDsBankAcctPk = (BigDecimal) bankAcctList.get(0).get("DS_BANK_ACCT_PK");
                                    arRcptSrcCd = (String) bankAcctList.get(0).get("AR_RCPT_SRC_CD");
                                    if (masterCheckArRcptSrcCnt(arRcptSrcCd)) {
                                        errCd = NFCM0748E;
                                    }
                                }
                            }
                            isFirst = false;
                        }
                        
                        AR_RCPT_RCV_INTFCTMsg updTMsg = new AR_RCPT_RCV_INTFCTMsg();
                        updTMsg = setArRcptRcvIntfcUpdate(updTMsg, rs, arLockBoxCd, remDsBankAcctPk, arRcptSrcCd);
                        if (errCd != null){
                            updTMsg = setError(updTMsg, errCd, null);
                        }
                        
                        updTMsgList.add(updTMsg);
                        if (updTMsgList.size() >= 1000) {
                            int rtn = EZDFastTBLAccessor.update(updTMsgList.toArray(new AR_RCPT_RCV_INTFCTMsg[updTMsgList.size()]));
                            if (rtn != updTMsgList.size()) {
                                throw new S21AbendException(NFCM0615E, new String[] {"Lockbox File Name" });
                            }
                            updTMsgList.clear();
                        }
                    }
                    if (updTMsgList.size() != 0) {
                        // remaining
                        int rtn = EZDFastTBLAccessor.update(updTMsgList.toArray(new AR_RCPT_RCV_INTFCTMsg[updTMsgList.size()]));
                        if (rtn != updTMsgList.size()) {
                            throw new S21AbendException(NFCM0615E, new String[] {"Lockbox File Name" });
                        }
                        updTMsgList.clear();
                    }
                } catch (SQLException e) {
                    super.sqlExceptionHandler(e);
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(ps, rs);
                }
            // END   2018/07/20 H.Ikeda [QC#24991,ADD]
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(nfci1010Stmt, nfci1010Rs);
        }
    }
    // END   2018/06/14 H.Ikeda [QC#25731,MOD]

    private AR_RCPT_RCV_INTFCTMsg setArRcptRcvIntfcUpdate(AR_RCPT_RCV_INTFCTMsg tMsg, ResultSet nfci1010Rs, String arLockBoxCd, BigDecimal remDsBankAcctPk, String arRcptSrcCd) throws SQLException {

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvIntfcPk, nfci1010Rs.getBigDecimal("AR_RCPT_RCV_INTFC_PK"));
        ZYPEZDItemValueSetter.setValue(tMsg.rcvFuncId, nfci1010Rs.getString("RCV_FUNC_ID"));
        ZYPEZDItemValueSetter.setValue(tMsg.rcvDt, nfci1010Rs.getString("RCV_DT"));
        ZYPEZDItemValueSetter.setValue(tMsg.depDt, nfci1010Rs.getString("DEP_DT"));
        ZYPEZDItemValueSetter.setValue(tMsg.depTm, nfci1010Rs.getString("DEP_TM"));
        ZYPEZDItemValueSetter.setValue(tMsg.arBatRcptNm, nfci1010Rs.getString("AR_BAT_RCPT_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.remBankRteNum, nfci1010Rs.getString("REM_BANK_RTE_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.remDsBankAcctNum, nfci1010Rs.getString("REM_DS_BANK_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.remDsBankMicrNum, nfci1010Rs.getString("REM_DS_BANK_MICR_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.remDsBankAcctPk, remDsBankAcctPk);
        ZYPEZDItemValueSetter.setValue(tMsg.custBankRteNum, nfci1010Rs.getString("CUST_BANK_RTE_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.custDsBankAcctNum, nfci1010Rs.getString("CUST_DS_BANK_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.custDsBankMicrNum, nfci1010Rs.getString("CUST_DS_BANK_MICR_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.custDsBankAcctPk, nfci1010Rs.getBigDecimal("CUST_DS_BANK_ACCT_PK"));
        ZYPEZDItemValueSetter.setValue(tMsg.custAcctRefNum, nfci1010Rs.getString("CUST_ACCT_REF_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.payerCustCd, nfci1010Rs.getString("PAYER_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, nfci1010Rs.getString("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.custRcptNum, nfci1010Rs.getString("CUST_RCPT_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.vldCustRcptNum, nfci1010Rs.getString("VLD_CUST_RCPT_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.custRcptAmt, nfci1010Rs.getBigDecimal("CUST_RCPT_AMT"));
        ZYPEZDItemValueSetter.setValue(tMsg.custInvNum, nfci1010Rs.getString("CUST_INV_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.arTrxNum, nfci1010Rs.getString("AR_TRX_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.grpInvNum, nfci1010Rs.getString("GRP_INV_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.custInvAmt, nfci1010Rs.getBigDecimal("CUST_INV_AMT"));
        if (arRcptSrcCd == null) {
            ZYPEZDItemValueSetter.setValue(tMsg.arRcptSrcCd, nfci1010Rs.getString("AR_RCPT_SRC_CD"));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.arRcptSrcCd, arRcptSrcCd);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvErrFlg, nfci1010Rs.getString("AR_RCPT_RCV_ERR_FLG"));
        ZYPEZDItemValueSetter.setValue(tMsg.arCustIdStsCd, nfci1010Rs.getString("AR_CUST_ID_STS_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvWrkCratFlg, nfci1010Rs.getString("AR_RCPT_RCV_WRK_CRAT_FLG"));
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxStsCd, nfci1010Rs.getString("AR_LOCK_BOX_STS_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxFileNm, nfci1010Rs.getString("AR_LOCK_BOX_FILE_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxBatNum, nfci1010Rs.getString("AR_LOCK_BOX_BAT_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxBatLineNum, nfci1010Rs.getString("AR_LOCK_BOX_BAT_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxBatLineSqNum, nfci1010Rs.getString("AR_LOCK_BOX_BAT_LINE_SQ_NUM"));
        if (arLockBoxCd == null) {
            ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxCd, nfci1010Rs.getString("AR_LOCK_BOX_CD"));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxCd, arLockBoxCd);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.hdrLockBoxNumTxt, nfci1010Rs.getString("HDR_LOCK_BOX_NUM_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.batLockBoxNumTxt, nfci1010Rs.getString("BAT_LOCK_BOX_NUM_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.trailLockBoxNumTxt, nfci1010Rs.getString("TRAIL_LOCK_BOX_NUM_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxFileErrFlg, nfci1010Rs.getString("AR_LOCK_BOX_FILE_ERR_FLG"));
        ZYPEZDItemValueSetter.setValue(tMsg.intfcFileRecCnt, nfci1010Rs.getBigDecimal("INTFC_FILE_REC_CNT"));
        ZYPEZDItemValueSetter.setValue(tMsg.lockBoxRecCnt, nfci1010Rs.getBigDecimal("LOCK_BOX_REC_CNT"));
        ZYPEZDItemValueSetter.setValue(tMsg.lockBoxTotAmt, nfci1010Rs.getBigDecimal("LOCK_BOX_TOT_AMT"));
        ZYPEZDItemValueSetter.setValue(tMsg.batRcptRecCnt, nfci1010Rs.getBigDecimal("BAT_RCPT_REC_CNT"));
        ZYPEZDItemValueSetter.setValue(tMsg.batRcptTotAmt, nfci1010Rs.getBigDecimal("BAT_RCPT_TOT_AMT"));
        // START 2018/11/16 H.Ikeda [QC#29278, ADD]
        ZYPEZDItemValueSetter.setValue(tMsg.lockBoxNtfyStsCd, nfci1010Rs.getString("LOCK_BOX_NTFY_STS_CD"));
        // END   2018/11/16 H.Ikeda [QC#29278, ADD]
        return tMsg;
    }
    
    
    
    // START 2018/02/01 T.Tsuchida [QC#23053,DEL]
//    /**
//     * firstCheck.
//     * @param tMsg AR_RCPT_RCV_INTFCTMsg
//     * @param tranId BigDecimal
//     * @return AR_RCPT_RCV_INTFCTMsg
//     */
//    private AR_RCPT_RCV_INTFCTMsg firstFileCheck(AR_RCPT_RCV_INTFCTMsg tMsg, BigDecimal tranId) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("intfcId", this.intfcId);
//        ssmParam.put("tranId", tranId);
//
//        BigDecimal fileRecCnt = (BigDecimal) ssmBatchClient.queryObject("getFileRecCnt", ssmParam);
//        if ("".equals(nvl(fileRecCnt)) || !nvl(fileRecCnt).equals(nvl(tMsg.intfcFileRecCnt.getValue()))) {
//            tMsg = setError(tMsg, NFCM0752E, nvl(fileRecCnt).toString());
//        }
//
//        ssmParam.clear();
//        ssmParam.put("intfcId", this.intfcId);
//        ssmParam.put("tranId", tranId);
//        ssmParam.put("divideVal", DIVIDE_VAL);
//        Map<String, Object> lockBoxInfo = (Map<String, Object>) ssmBatchClient.queryObject("getLockBoxInfo", ssmParam);
//        if (lockBoxInfo.isEmpty() || !nvl((BigDecimal) lockBoxInfo.get("REC_CNT")).equals(nvl(tMsg.lockBoxRecCnt.getValue()))) {
//            tMsg = setError(tMsg, NFCM0753E, nvl((BigDecimal) lockBoxInfo.get("REC_CNT")).toString());
//        }
//        if (lockBoxInfo.isEmpty() || !nvl((BigDecimal) lockBoxInfo.get("REC_AMT")).equals(nvl(tMsg.lockBoxTotAmt.getValue()))) {
//            tMsg = setError(tMsg, NFCM0754E, nvl((BigDecimal) lockBoxInfo.get("REC_AMT")).toString());
//        }
//
//        return tMsg;
//    }
    // END 2018/02/01 T.Tsuchida [QC#23053,DEL]

    // START 2018/06/20 H.Ikeda [QC#21037,ADD]
    /**
     * batRcptCheck.
     * @param tMsg AR_RCPT_RCV_INTFCTMsg
     * @param errCheckDataMap Map<String, Map<String, Object>>
     * @return AR_RCPT_RCV_INTFCTMsg
     */
    private AR_RCPT_RCV_INTFCTMsg batRcptCheck(AR_RCPT_RCV_INTFCTMsg tMsg, Map<String, Map<String, Object>> errCheckDataMap) {
        if (!ZYPCommonFunc.hasValue(tMsg.arLockBoxBatNum)) {
            return tMsg;
        }
        boolean errFlg = true;
        for (int i = 0; i < errCheckDataMap.size(); i++) {
            Map<String, Object> batRcptInfo = errCheckDataMap.get(tMsg.arLockBoxBatNum.getValue());
            if (batRcptInfo != null) {
                if (!nvl((BigDecimal) batRcptInfo.get("REC_CNT")).equals(nvl(tMsg.batRcptRecCnt.getValue()))) {
                    tMsg = setError(tMsg, NFCM0755E, nvl((BigDecimal) batRcptInfo.get("REC_CNT")).toString());
                }
                if (!nvl((BigDecimal) batRcptInfo.get("REC_AMT")).equals(nvl(tMsg.batRcptTotAmt.getValue()))) {
                    tMsg = setError(tMsg, NFCM0756E, nvl((BigDecimal) batRcptInfo.get("REC_AMT")).toString());
                }
                errFlg = false;
                break;
            }
        }
        if (errFlg) {
            tMsg = setError(tMsg, NFCM0755E, null);
            tMsg = setError(tMsg, NFCM0756E, null);
        }

        return tMsg;
    }
    // END 2018/06/20 H.Ikeda [QC#21037,ADD]
    /**
     * batRcptCheck.
     * @param tMsg AR_RCPT_RCV_INTFCTMsg
     * @param tranId BigDecimal
     * @return AR_RCPT_RCV_INTFCTMsg
     */
    private AR_RCPT_RCV_INTFCTMsg batRcptCheck(AR_RCPT_RCV_INTFCTMsg tMsg, BigDecimal tranId) {
        if (!ZYPCommonFunc.hasValue(tMsg.arLockBoxBatNum)) {
            return tMsg;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("intfcId", this.intfcId);
        ssmParam.put("tranId", tranId);
        ssmParam.put("divideVal", DIVIDE_VAL);
        ssmParam.put("batRcptNum", tMsg.arLockBoxBatNum.getValue());
        Map<String, Object> batRcptInfo = (Map<String, Object>) ssmBatchClient.queryObject("getBatRcptInfo", ssmParam);
        if (batRcptInfo.isEmpty() || !nvl((BigDecimal) batRcptInfo.get("REC_CNT")).equals(nvl(tMsg.batRcptRecCnt.getValue()))) {
            tMsg = setError(tMsg, NFCM0755E, nvl((BigDecimal) batRcptInfo.get("REC_CNT")).toString());
        }
        if (batRcptInfo.isEmpty() || !nvl((BigDecimal) batRcptInfo.get("REC_AMT")).equals(nvl(tMsg.batRcptTotAmt.getValue()))) {
            tMsg = setError(tMsg, NFCM0756E, nvl((BigDecimal) batRcptInfo.get("REC_AMT")).toString());
        }

        return tMsg;
    }

    private AR_RCPT_RCV_INTFCTMsg afterBatRcptCheck(AR_RCPT_RCV_INTFCTMsg tMsg, boolean checkFLg) {

        if (ZYPCommonFunc.hasValue(tMsg.hdrLockBoxNumTxt) //
                && ZYPCommonFunc.hasValue(tMsg.batLockBoxNumTxt)) {
            if (!tMsg.hdrLockBoxNumTxt.getValue().equals(tMsg.batLockBoxNumTxt.getValue()) //
                    || !tMsg.hdrLockBoxNumTxt.getValue().equals(tMsg.trailLockBoxNumTxt.getValue())) {
                tMsg = setError(tMsg, NFCM0749E, null);
            }
        } else {
            return tMsg;
        }

        if (ZYPCommonFunc.hasValue(tMsg.custRcptAmt)) {
            // START 2017/12/01 T.Tsuchida [QC#21398,MOD]
            //if (BigDecimal.ZERO.compareTo(nvl(tMsg.custRcptAmt.getValue())) >= 0) {
            if (BigDecimal.ZERO.compareTo(nvl(tMsg.custRcptAmt.getValue())) > 0) {
            // END 2017/12/01 T.Tsuchida [QC#21398,MOD]
                tMsg = setError(tMsg, NFCM0750E, null);
            } else {
                if (checkFLg) {
                    if (ZYPCommonFunc.hasValue(tMsg.custRcptAmt)) {
                        BigDecimal sumCustInvAmt = getSumCustInvAmt(tMsg);
                        if (ZYPCommonFunc.hasValue(sumCustInvAmt) //
                            // START 2016/11/09 S.Fujita [QC#15742,MOD]
//                            && !sumCustInvAmt.equals(tMsg.custRcptAmt.getValue())) {
                            && sumCustInvAmt.compareTo(tMsg.custRcptAmt.getValue()) != 0) {
                            // END   2016/11/09 S.Fujita [QC#15742,MOD]
                            tMsg = setError(tMsg, NFCM0751E, nvl(sumCustInvAmt).toString());
                        }
                    }
                }
            }
        }
        return tMsg;
    }

    private AR_RCPT_RCV_INTFCTMsg firstRecMandatoryCheck(AR_RCPT_RCV_INTFCTMsg tMsg) {
        if (!ZYPCommonFunc.hasValue(tMsg.depDt)) {
            tMsg = setError(tMsg, NFCM0743E, "Deposit Date");
        }
        if (!ZYPCommonFunc.hasValue(tMsg.depTm)) {
            tMsg = setError(tMsg, NFCM0743E, "Deposit Time");
        }
        if (!ZYPCommonFunc.hasValue(tMsg.hdrLockBoxNumTxt)) {
            tMsg = setError(tMsg, NFCM0743E, "Header Lock Box Number Text");
        }
        if (!ZYPCommonFunc.hasValue(tMsg.remBankRteNum)) {
            tMsg = setError(tMsg, NFCM0743E, "Remittance Bank Routing Number");
        }
        if (!ZYPCommonFunc.hasValue(tMsg.remDsBankAcctNum)) {
            tMsg = setError(tMsg, NFCM0743E, "Remittance DS Bank Account Number");
        }
        return tMsg;
    }

    private AR_RCPT_RCV_INTFCTMsg mandatoryCheck(AR_RCPT_RCV_INTFCTMsg tMsg, boolean checkFlg) {
        if (!ZYPCommonFunc.hasValue(tMsg.arLockBoxBatNum)) {
            tMsg = setError(tMsg, NFCM0743E, "AR Lock Box Batch Number");
        }

        if (!ZYPCommonFunc.hasValue(tMsg.arLockBoxBatLineNum)) {
            tMsg = setError(tMsg, NFCM0743E, "AR Lock Box Batch Line Number");
        }

        if (!ZYPCommonFunc.hasValue(tMsg.custRcptAmt)) {
            tMsg = setError(tMsg, NFCM0743E, "Customer Receipt Amount");
        }
        // START 2018/05/07 E.Kameishi [QC#25721,MOD]
        // START 2017/05/29 E.Kameishi [QC#18594,ADD]
        //if (checkFlg) {
        //    if (ZYPCommonFunc.hasValue(tMsg.custInvAmt)) {
        //        if (!ZYPCommonFunc.hasValue(tMsg.custInvNum)) {
        //            tMsg = setError(tMsg, NFCM0744E, "Customer Invoice Number");
        //        }
        //    }
        // }
        // START 2018/05/07 E.Kameishi [QC#25721,MOD]
        // END 2017/05/29 E.Kameishi [QC#18594,MOD]
        if (!ZYPCommonFunc.hasValue(tMsg.custRcptNum)) {
            tMsg = setError(tMsg, NFCM0745E, "Customer Receipt Number");
        }

        return tMsg;
    }

    private AR_RCPT_RCV_INTFCTMsg setError(AR_RCPT_RCV_INTFCTMsg tMsg, String msgId, String itemNm) {

        insertArRcptRcvInfoWrk(tMsg, msgId, itemNm);

        ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvErrFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxStsCd, AR_LOCK_BOX_STS.ERROR);
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxFileErrFlg, ZYPConstant.FLG_ON_Y);

        return tMsg;
    }

    private void insertArRcptRcvInfoWrk(AR_RCPT_RCV_INTFCTMsg tMsg, String msgId, String itemVal) {
        String[] prmMsg = null;

        if (NFCM0743E.equals(msgId)) {
            prmMsg = new String[] {itemVal, tMsg.custRcptNum.getValue(), tMsg.custDsBankMicrNum.getValue() };
        } else if (NFCM0744E.equals(msgId)) {
            prmMsg = new String[] {tMsg.custRcptNum.getValue(), tMsg.custDsBankMicrNum.getValue(), nvl(tMsg.custInvAmt.getValue()).toString() };
        } else if (NFCM0745E.equals(msgId)) {
            prmMsg = new String[] {itemVal, tMsg.custDsBankMicrNum.getValue() };
        } else if (NFCM0746E.equals(msgId)) {
            prmMsg = new String[] {tMsg.hdrLockBoxNumTxt.getValue() };
        } else if (NFCM0747E.equals(msgId)) {
            prmMsg = new String[] {tMsg.hdrLockBoxNumTxt.getValue(), tMsg.remDsBankMicrNum.getValue() };
        } else if (NFCM0748E.equals(msgId)) {
            prmMsg = new String[] {tMsg.hdrLockBoxNumTxt.getValue(), tMsg.remDsBankAcctNum.getValue() };
        } else if (NFCM0749E.equals(msgId)) {
            prmMsg = new String[] {tMsg.hdrLockBoxNumTxt.getValue(), tMsg.batLockBoxNumTxt.getValue(), tMsg.trailLockBoxNumTxt.getValue() };
        } else if (NFCM0750E.equals(msgId)) {
            prmMsg = new String[] {tMsg.custRcptNum.getValue(), nvl(tMsg.custRcptAmt.getValue()).toString() };
        } else if (NFCM0751E.equals(msgId)) {
            prmMsg = new String[] {tMsg.custRcptNum.getValue(), itemVal };
        } else if (NFCM0752E.equals(msgId)) {
            prmMsg = new String[] {itemVal, nvl(tMsg.intfcFileRecCnt.getValue()).toString() };
            // START 2018/02/01 T.Tsuchida [QC#23053,DEL]
//        } else if (NFCM0753E.equals(msgId)) {
//            prmMsg = new String[] {itemVal, nvl(tMsg.lockBoxRecCnt.getValue()).toString() };
//        } else if (NFCM0754E.equals(msgId)) {
//            prmMsg = new String[] {itemVal, nvl(tMsg.lockBoxTotAmt.getValue()).toString() };
            // END 2018/02/01 T.Tsuchida [QC#23053,DEL]
        } else if (NFCM0755E.equals(msgId)) {
            prmMsg = new String[] {tMsg.arLockBoxBatNum.getValue(), itemVal, nvl(tMsg.batRcptRecCnt.getValue()).toString() };
        } else if (NFCM0756E.equals(msgId)) {
            prmMsg = new String[] {tMsg.arLockBoxBatNum.getValue(), itemVal, nvl(tMsg.batRcptTotAmt.getValue()).toString() };
        // START 2018/05/18 H.Ikeda [QC#25914,ADD]
        } else if (NFCM0884E.equals(msgId)) {
            prmMsg = new String[] {itemVal, tMsg.depDt.getValue().toString(), tMsg.depTm.getValue().toString()};
        }
        // END   2018/05/18 H.Ikeda [QC#25914,ADD]

        AR_RCPT_RCV_INFO_WRKTMsg insTMsg = new AR_RCPT_RCV_INFO_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.arRcptRcvInfoWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.AR_RCPT_RCV_INFO_WRK_SQ));
        ZYPEZDItemValueSetter.setValue(insTMsg.arRcptRcvIntfcPk, tMsg.arRcptRcvIntfcPk);
        ZYPEZDItemValueSetter.setValue(insTMsg.arBatInfoMsgTxt, getMessageText(msgId, prmMsg));
        ZYPEZDItemValueSetter.setValue(insTMsg.arBatInfoLvlCd, AR_BAT_INFO_LVL.ERROR);

        EZDTBLAccessor.insert(insTMsg);

    }

    private AR_RCPT_RCV_INTFCTMsg masterCheckAndSet(AR_RCPT_RCV_INTFCTMsg tMsg) {

        if (ZYPCommonFunc.hasValue(tMsg.arLockBoxCd)) {
            return tMsg;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("lockBoxNum", tMsg.hdrLockBoxNumTxt.getValue());
        List<Map<String, Object>> arLockBoxList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getArLockBox", ssmParam);
        if (arLockBoxList.isEmpty() || arLockBoxList.size() != 1) {
            tMsg = setError(tMsg, NFCM0746E, null);
            return tMsg;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxCd, (String) arLockBoxList.get(0).get("AR_LOCK_BOX_CD"));

        ssmParam.clear();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("lockBoxCd", tMsg.arLockBoxCd.getValue());
        ssmParam.put("internal", DS_BANK_ACCT_TP.INTERNAL);
        List<Map<String, Object>> bankAcctList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getBankAcct", ssmParam);
        if (bankAcctList.isEmpty()) {
            tMsg.remDsBankAcctPk.clear();
            tMsg = setError(tMsg, NFCM0747E, null);
            return tMsg;
        }
        //Def#5016
//        if (!nvl((String) bankAcctList.get(0).get("DS_BANK_MICR_NUM")).equals(nvl(tMsg.remDsBankMicrNum.getValue()))) {
//            tMsg.remDsBankAcctPk.clear();
//            tMsg = setError(tMsg, NFCM0747E, null);
//            return tMsg;
//        }
        String srcMicrNum = nvl((String) bankAcctList.get(0).get("DS_BANK_MICR_NUM"));
        String dstMicrNum = nvl(tMsg.remDsBankMicrNum.getValue());
        srcMicrNum = srcMicrNum.replace(" ", "");
        dstMicrNum = dstMicrNum.replace(" ", "");
        if (!srcMicrNum.equals(dstMicrNum)) {
            tMsg.remDsBankAcctPk.clear();
            tMsg = setError(tMsg, NFCM0747E, null);
            return tMsg;
        }
        
        
        ZYPEZDItemValueSetter.setValue(tMsg.remDsBankAcctPk, (BigDecimal) bankAcctList.get(0).get("DS_BANK_ACCT_PK"));
        ZYPEZDItemValueSetter.setValue(tMsg.arRcptSrcCd, (String) bankAcctList.get(0).get("AR_RCPT_SRC_CD"));

        ssmParam.clear();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("arRcptSrcCd", tMsg.arRcptSrcCd.getValue());
        BigDecimal arRcptSrcCnt = (BigDecimal) ssmBatchClient.queryObject("getArRcptSrcCnt", ssmParam);
        if (arRcptSrcCnt == null || arRcptSrcCnt.compareTo(BigDecimal.ONE) != 0) {
            tMsg = setError(tMsg, NFCM0748E, null);
            return tMsg;
        }

        return tMsg;
    }

    private String masterCheckArLockBoxCd(String hdrLockBoxNumTxt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("lockBoxNum", hdrLockBoxNumTxt);
        List<Map<String, Object>> arLockBoxList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getArLockBox", ssmParam);
        if (arLockBoxList.isEmpty() || arLockBoxList.size() != 1) {
            return null;
        }
        
        return (String)arLockBoxList.get(0).get("AR_LOCK_BOX_CD");
    }

    private List<Map<String, Object>> masterCheckBankAcctList(String arLockBoxCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("lockBoxCd", arLockBoxCd);
        ssmParam.put("internal", DS_BANK_ACCT_TP.INTERNAL);
        List<Map<String, Object>> bankAcctList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getBankAcct", ssmParam);
        if (bankAcctList.isEmpty()) {
            return null;
        }

        return bankAcctList;
    }

    private boolean masterCheckArRcptSrcCnt(String arRcptSrcCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("arRcptSrcCd", arRcptSrcCd);
        BigDecimal arRcptSrcCnt = (BigDecimal) ssmBatchClient.queryObject("getArRcptSrcCnt", ssmParam);
        if (arRcptSrcCnt == null || arRcptSrcCnt.compareTo(BigDecimal.ONE) != 0) {
            return true;
        }

        return false;
    }

    private AR_RCPT_RCV_INTFCTMsg masterCheckAndSet(AR_RCPT_RCV_INTFCTMsg tMsg, String srcMicrNum) {

        if (ZYPCommonFunc.hasValue(tMsg.arLockBoxCd)) {
            return tMsg;
        }

        String dstMicrNum = nvl(tMsg.remDsBankMicrNum.getValue());
        srcMicrNum = srcMicrNum.replace(" ", "");
        dstMicrNum = dstMicrNum.replace(" ", "");
        if (!srcMicrNum.equals(dstMicrNum)) {
            tMsg.remDsBankAcctPk.clear();
            tMsg = setError(tMsg, NFCM0747E, null);
            return tMsg;
        }
        
        return tMsg;
    }

    private BigDecimal getSumCustInvAmt(AR_RCPT_RCV_INTFCTMsg tMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("arBatRcptNm", tMsg.arBatRcptNm.getValue());
        ssmParam.put("custRcptNum", tMsg.custRcptNum.getValue());
        // START 2017/05/18 E.Kameishi [QC#18600,ADD]
        ssmParam.put("custDsBankMicrNum", tMsg.custDsBankMicrNum.getValue());
        // END 2017/05/18 E.Kameishi [QC#18600,ADD]
        List<BigDecimal> totCustRcptAmtList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getTotCustRcptAmt", ssmParam);
        if (totCustRcptAmtList.isEmpty() || totCustRcptAmtList.size() == 0) {
            return null;
        }

        BigDecimal sumCustInvAmt = BigDecimal.ZERO;
        boolean isNotExist = true;
        for (BigDecimal val : totCustRcptAmtList) {
            sumCustInvAmt = sumCustInvAmt.add(nvl(val));
            if (ZYPCommonFunc.hasValue(val)) {
                isNotExist = false;
            }
        }
        if (isNotExist) {
            return null;
        }
        return sumCustInvAmt;
    }

    private void deleteArRcptRcvInfoWrk(BigDecimal arRcptRcvIntfcPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("arRcptRcvIntfcPk", arRcptRcvIntfcPk);
        ssmParam.put("warning", AR_BAT_INFO_LVL.WARNING);
        List<BigDecimal> arRcptRcvInfoWrkPkList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getArRcptRcvInfoWrk", ssmParam);
        if (arRcptRcvInfoWrkPkList.isEmpty()) {
            return;
        }

        AR_RCPT_RCV_INFO_WRKTMsg inMsg = new AR_RCPT_RCV_INFO_WRKTMsg();
        for (BigDecimal arRcptRcvInfoWrkPk : arRcptRcvInfoWrkPkList) {
            inMsg.clear();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.arRcptRcvInfoWrkPk, arRcptRcvInfoWrkPk);

            EZDTBLAccessor.logicalRemove(inMsg);
        }
    }

    private boolean errCheck(AR_RCPT_RCV_INTFCTMsg tMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(tMsg.arLockBoxFileErrFlg.getValue()) //
                || ZYPConstant.FLG_ON_Y.equals(tMsg.arRcptRcvErrFlg.getValue())) {
            return true;
        }
        return false;
    }

    private String nvl(String val) {
        if (val == null) {
            return "";
        }
        return val;
    }

    private BigDecimal nvl(BigDecimal val) {
        if (val == null) {
            return BigDecimal.ZERO;
        }
        return val;
    }

    /**
     * Get Message Text.
     * @param messageId MessageID
     * @return message text
     */
    private String getMessageText(String messageId, String[] prm) {
        return S21MessageFunc.clspGetMessage(messageId, prm);
    }

    // START 2016/12/05 S.Fujita [QC#16255,ADD]
    private AR_RCPT_RCV_INTFCTMsg updErrFlg(AR_RCPT_RCV_INTFCTMsg tMsg) {

        ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvErrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxFileErrFlg, ZYPConstant.FLG_OFF_N);

        return tMsg;
    }
    // END   2016/12/05 S.Fujita [QC#16255,ADD]

    // START 2017/05/29 E.Kameishi [QC#18594,ADD]
    private AR_RCPT_RCV_INTFCTMsg setWarning(AR_RCPT_RCV_INTFCTMsg tMsg, String msgId, String itemNm) {
        String[] prmMsg = null;

        AR_RCPT_RCV_INFO_WRKTMsg insTMsg = new AR_RCPT_RCV_INFO_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.arRcptRcvInfoWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.AR_RCPT_RCV_INFO_WRK_SQ));
        ZYPEZDItemValueSetter.setValue(insTMsg.arRcptRcvIntfcPk, tMsg.arRcptRcvIntfcPk);
        ZYPEZDItemValueSetter.setValue(insTMsg.arBatInfoMsgTxt, getMessageText(msgId, prmMsg));
        ZYPEZDItemValueSetter.setValue(insTMsg.arBatInfoLvlCd, AR_BAT_INFO_LVL.WARNING);

        EZDTBLAccessor.insert(insTMsg);

        return tMsg;
    }
    
    private boolean checkArRcptRcvInfoWrk(BigDecimal arRcptRcvIntfcPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("arRcptRcvIntfcPk", arRcptRcvIntfcPk);
        ssmParam.put("warning", AR_BAT_INFO_LVL.WARNING);
        List<BigDecimal> arRcptRcvInfoWrkPkList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getArRcptRcvInfoWrkWarn", ssmParam);
        if (arRcptRcvInfoWrkPkList.isEmpty()) {
            return true;
        }
        return false;
    }
    // END 2017/05/29 E.Kameishi [QC#18594,ADD]

    private List<BigDecimal> checkArRcptRcvInfoWrkWarnList() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("warning", AR_BAT_INFO_LVL.WARNING);
        ssmParam.put("intfcId", this.intfcId);
        ssmParam.put("saved", AR_LOCK_BOX_STS.TEMPORARY_SAVED);
        ssmParam.put("reprocess", AR_LOCK_BOX_STS.REPROCESS);
        List<BigDecimal> arRcptRcvInfoWrkPkList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getArRcptRcvInfoWrkWarnList", ssmParam);

        return arRcptRcvInfoWrkPkList;
    }

    private boolean checkArRcptRcvInfoWrk(BigDecimal arRcptRcvIntfcPk, List<BigDecimal> arRcptRcvInfoWrkPkList) {

        if (arRcptRcvInfoWrkPkList.contains(arRcptRcvIntfcPk)) {
            return true;
        }

        return false;
    }

    private List<BigDecimal> checkArRcptRcvInfoWrkList() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("warning", AR_BAT_INFO_LVL.WARNING);
        ssmParam.put("intfcId", this.intfcId);
        ssmParam.put("saved", AR_LOCK_BOX_STS.TEMPORARY_SAVED);
        ssmParam.put("reprocess", AR_LOCK_BOX_STS.REPROCESS);
        List<BigDecimal> arRcptRcvInfoWrkPkList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getArRcptRcvInfoWrkList", ssmParam);

        return arRcptRcvInfoWrkPkList;
    }

    private void deleteArRcptRcvInfoWrk(BigDecimal arRcptRcvInfoWrkPk, List<BigDecimal> arRcptRcvInfoWrkPkList) {

        if (arRcptRcvInfoWrkPkList.contains(arRcptRcvInfoWrkPk)) {
            AR_RCPT_RCV_INFO_WRKTMsg inMsg = new AR_RCPT_RCV_INFO_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.arRcptRcvInfoWrkPk, arRcptRcvInfoWrkPk);
            EZDTBLAccessor.logicalRemove(inMsg);
        }
    }
}
