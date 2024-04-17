/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0010;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NSBL0010.constant.NSBL0010Constant;
import business.file.NSBL0010F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DT_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ASG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Service Dispatch Schedule/Dispatch Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 * 2013/10/25   Fujitsu         K.Shibuya       Update          #2852
 * 01/16/2014   Fujitsu         M.Fuji          Update          WDS Defect#3306
 * 2014/06/02   Hitachi         T.Aoyagi        Update          CSA-166
 * 2015/11/18   Hitachi         T.Harada        Update          CSA
 * 2016/11/29   Hitachi         N.Arai          Update          QC#13901
 * 2016/12/14   Hitachi         K.Kojima        Update          QC#15653
 * 2016/12/21   Hitachi         K.Kojima        Update          QC#15653
 * 2017/01/05   Hitachi         N.Arai          Update          QC#13901-2
 * 2017/02/06   Hitachi         N.Arai          Update          QC#17197
 * 2017/08/04   Hitachi         U.Kim           Update          QC#19084
 * 2018/07/19   Hitachi         A.Kohinata      Update          QC#27143
 * 2022/07/20   CITS            L.Mandanas      Update          QC#60316
 *</pre>
 */
public final class NSBL0010Query extends S21SsmEZDQuerySupport implements NSBL0010Constant {

    /**
     * Singleton instance.
     */
    private static final NSBL0010Query INSTANCE = new NSBL0010Query();

    /**
     * Constructor.
     */
    private NSBL0010Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPAL0010Query
     */
    public static NSBL0010Query getInstance() {
        return INSTANCE;
    }

    private void setSearchSvcTaskParam(Map<String, Object> paramMap) {
        String currSysDtTm = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");
//        String currSysDt = currSysDtTm.substring(0, LEN_DT);
        String currSysTm = currSysDtTm.substring(LEN_DT, currSysDtTm.length());
        String slsDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode(), BUSINESS_ID);
        String slsDtTm = slsDt.concat(currSysTm);

        paramMap.put("glblCmpyCd", getGlobalCompanyCode());
        paramMap.put("currSysDt", slsDt);
//        paramMap.put("currSysDtTm", currSysDtTm);
        paramMap.put("currSysDtTm", slsDtTm);
        paramMap.put("currSysTm", currSysTm);
        paramMap.put("dtAttrbCd", DT_ATTRB.BUSINESS);
        paramMap.put("dtAttrbValCd", DT_ATTRB_VAL_CD);
        paramMap.put("techBakTpCd", TECH_BAK_TP_CD);
        paramMap.put("svcMemoCatgTpCd", SVC_MEMO_CATG_TP_CD);
        // START 2015/11/18 T.Harada [CSA,DELETE]
        //paramMap.put("procrTpCdPo", PROCR_TP.PURCHASE_ORDER);
        //paramMap.put("procrTpCdWh", PROCR_TP.WAREHOUSE_TRANSFER);
        //paramMap.put("prchReqStsCdList", PRCH_REQ_STS_CD_LIST);
        //paramMap.put("prchReqStsCancel", PRCH_REQ_STS.CANCELLED);
        //paramMap.put("poStsCdList", PO_STS_CD_LIST);
        //paramMap.put("invtyOrdStsList", INVTY_ORD_STS_LIST);
        // END 2015/11/18 T.Harada [CSA,DELETE]
        paramMap.put("formatTm", SQL_FORMAT_TM);
        paramMap.put("stsComplete", SVC_TASK_STS.COMPLETED);
        paramMap.put("flgN", ZYPConstant.FLG_OFF_N);
        paramMap.put("orgStruTp", ORG_STRU_TP.TERRITORY_STRUCTURE);
    }

    /**
     * setSvcTaskStsCdCond
     * @param bizMsg NSBL0010CMsg
     * @param paramMap Map<String, Object>
     */
    private void setSvcTaskStsCdCond(NSBL0010CMsg bizMsg, Map<String, Object> paramMap) {
        if (ZYPCommonFunc.hasValue(bizMsg.svcTaskStsCd_H3.getValue())) {
            if (SVC_TASK_STS_CD_WAITING_FOR_SCHEDULING.equals(bizMsg.svcTaskStsCd_H3.getValue())) {
                paramMap.put("svcTaskStsCdList", WAITING_FOR_SCHEDULING_STS_CD_LIST);
            } else if (SVC_TASK_STS_CD_ACTIVE_TASK_FSR.equals(bizMsg.svcTaskStsCd_H3.getValue())) {
                paramMap.put("expSvcTaskStsCdList", ACTIVE_TASK_FSR_STS_CD_LIST);
            } else {
                paramMap.put("svcTaskStsCdList", new String[] {bizMsg.svcTaskStsCd_H3.getValue()});
            }
        }
    }

    /**
     * <pre>
     * get SvcTask.
     * </pre>
     * @param bizMsg NSBL0010CMsg  
     * @param shareMsg NSBL0010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcTask(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("bizMsg", bizMsg);
        setSvcTaskStsCdCond(bizMsg, paramMap);
        setSearchSvcTaskParam(paramMap);
        paramMap.put("rowNum", MAX_SEARCH + 1);
        // START 2016/12/14 K.Kojima [QC#15653,ADD]
        paramMap.put("maxDt", MAX_DT_VAL);
        paramMap.put("slsDt", ZYPDateUtil.getSalesDate());
        // END 2016/12/14 K.Kojima [QC#15653,ADD]
        // START 2016/12/21 K.Kojima [QC#15653,ADD]
        paramMap.put("flgY", ZYPConstant.FLG_ON_Y);
        // END 2016/12/21 K.Kojima [QC#15653,ADD]
// START 2017/01/05 N.Arai [QC#13901-2, MOD]
        paramMap.put("notAllowed", SVC_ASG_TP.NOT_ALLOWED);
        paramMap.put("creditProfile", SVC_TASK_HLD_RSN.CREDIT_PROFILE);
// END 2017/01/05 N.Arai [QC#13901-2, MOD]
// add start 2018/07/19 QC#27143
        paramMap.put("overDue", SVC_TASK_HLD_RSN.OVER_DUE);
// add end 2018/07/19 QC#27143
        // START 2022/07/20 L.Mandanas [QC#60316, ADD]
        paramMap.put("creditLimit", SVC_TASK_HLD_RSN.CREDIT_LIMIT);
        // END 2022/07/20 L.Mandanas [QC#60316, ADD]
// START 2016/11/29 N.Arai [QC#13901, MOD]
        if (!getStatusSerchTable(bizMsg, paramMap)) {
            return null;
        }
// END 2016/11/29 N.Arai [QC#13901, MOD]
        return getSsmEZDClient().queryEZDMsgArray("getSvcTask", paramMap, shareMsg.A);
    }

// START 2016/11/29 N.Arai [QC#13901, MOD]
    private boolean getStatusSerchTable(NSBL0010CMsg bizMsg, Map<String, Object> paramMap) {

        if (!ZYPCommonFunc.hasValue(bizMsg.svcTaskStsCd_H3)) {
            return true;
        }

        S21SsmEZDResult res = getSsmEZDClient().queryObjectList("getEnabledStatus", paramMap);
        if (!res.isCodeNormal()) {
            return false;
        }

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) res.getResultObject();
        if (resultList == null || resultList.size() == 0) {
            return false;
        }

        Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
        BigDecimal fsrVisitSerchCnt = (BigDecimal)resultMap.get("FSR_VISIT_SERCH_CNT");
        BigDecimal svcTaskSerchCnt = (BigDecimal)resultMap.get("SVC_TASK_SERCH_CNT");
        BigDecimal fsrSerchCnt = (BigDecimal)resultMap.get("FSR_SERCH_CNT");

        if (BigDecimal.ZERO.compareTo(fsrVisitSerchCnt) != 0 && BigDecimal.ZERO.compareTo(svcTaskSerchCnt) != 0 && BigDecimal.ZERO.compareTo(fsrSerchCnt) != 0) {
            paramMap.put("serchAll", "Y");
        } else if (BigDecimal.ZERO.compareTo(fsrVisitSerchCnt) != 0 && BigDecimal.ZERO.compareTo(svcTaskSerchCnt) != 0) {
            paramMap.put("serchFsrVisitAndSvcTask", "Y");
        } else if (BigDecimal.ZERO.compareTo(fsrVisitSerchCnt) != 0 && BigDecimal.ZERO.compareTo(fsrSerchCnt) != 0) {
            paramMap.put("serchFsrVisitAndFsr", "Y");
        } else if (BigDecimal.ZERO.compareTo(svcTaskSerchCnt) != 0 && BigDecimal.ZERO.compareTo(fsrSerchCnt) != 0) {
            paramMap.put("serchSvcTaskAndFsr", "Y");
        } else if (BigDecimal.ZERO.compareTo(fsrVisitSerchCnt) != 0) {
            paramMap.put("serchFsrVisit", "Y");
        } else if (BigDecimal.ZERO.compareTo(svcTaskSerchCnt) != 0) {
            paramMap.put("serchSvcTask", "Y");
        } else if (BigDecimal.ZERO.compareTo(fsrSerchCnt) != 0) {
            paramMap.put("serchFsr", "Y");
        } else {
            return false;
        }
        return true;
    }
// END 2016/11/29 N.Arai [QC#13901, MOD]

    /**
     * checkTechMstr
     * @param techCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkTechMstr(String techCd) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("glblCmpyCd", getGlobalCompanyCode());
        paramMap.put("techCd", techCd);
        // START 2016/12/14 K.Kojima [QC#15653,ADD]
        paramMap.put("maxDt", MAX_DT_VAL);
        paramMap.put("slsDt", ZYPDateUtil.getSalesDate());
        // END 2016/12/14 K.Kojima [QC#15653,ADD]
        // START 2016/12/21 K.Kojima [QC#15653,ADD]
        paramMap.put("flgY", ZYPConstant.FLG_ON_Y);
        // END 2016/12/21 K.Kojima [QC#15653,ADD]

        return getSsmEZDClient().queryObject("checkTechMstr", paramMap);
    }

    /**
     * checkTechTngHist
     * @param techCd String
     * @param svcTaskNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkTechTngHist(String techCd, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        //START 2014/08/04 U.Kim [QC#19084, ADD]
        String slsDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode(), BUSINESS_ID);
        paramMap.put("slsDt", slsDt);
        //END 2014/08/04 U.Kim [QC#19084, ADD]

        paramMap.put("glblCmpyCd", getGlobalCompanyCode());
        paramMap.put("techCd", techCd);
        paramMap.put("svcTaskNum", svcTaskNum);

        return getSsmEZDClient().queryObject("checkTechTngHist", paramMap);
    }

    // START 2015/11/18 T.Harada [CSA,ADD]
    /**
     * checkSvcNonPrfTech
     * @param techCd String
     * @param svcMachMstrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkSvcNonPrfTech(String techCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        String slsDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode(), BUSINESS_ID);

        paramMap.put("glblCmpyCd", getGlobalCompanyCode());
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        paramMap.put("techCd", techCd);
        paramMap.put("currSysDt", slsDt);

        return getSsmEZDClient().queryObject("checkSvcNonPrfTech", paramMap);
    }
    // END 2015/11/18 T.Harada [CSA,ADD]

    /**
     * getSvcTaskStsList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcTaskStsList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("glblCmpyCd", getGlobalCompanyCode());
        paramMap.put("rejStsList", REJ_STS_LIST);

        return getSsmEZDClient().queryObjectList("getSvcTaskStsList", paramMap);
    }

//    /**
//     * <pre>
//     * get SvcTask.
//     * </pre>
//     * @param shareMsg NSBL0010SMsg
//     * @param idx int
//     * @param mode String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult reloadSvcTask(NSBL0010SMsg shareMsg, int idx, String mode) {
//
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//
//        NSBL0010CMsg tmpBizMsg = new NSBL0010CMsg();
//        ZYPEZDItemValueSetter.setValue(tmpBizMsg.svcTaskNum, shareMsg.A.no(idx).svcTaskNum_A.getValue());
//        try {
//            if (!(MODE_CANCEL.equals(mode) && Integer.parseInt(shareMsg.A.no(idx).fsrVisitNum_A.getValue()) < 2)) {
//                ZYPEZDItemValueSetter.setValue(tmpBizMsg.fsrVisitNum, shareMsg.A.no(idx).fsrVisitNum_A.getValue());
//            }
//        } catch (NumberFormatException e) {
//            if (ZYPCommonFunc.hasValue(shareMsg.A.no(idx).fsrVisitNum_A.getValue())) {
//                ZYPEZDItemValueSetter.setValue(tmpBizMsg.fsrVisitNum, shareMsg.A.no(idx).fsrVisitNum_A.getValue());
//            }
//        }
//        paramMap.put("bizMsg", tmpBizMsg);
//        setSearchSvcTaskParam(paramMap);
//        paramMap.put("rowNum", 1);
//
//        return getSsmEZDClient().queryObject("getSvcTask", paramMap);
//    }

    /**
     * getMsgFlg
     * @param bizMsg NSBL0010CMsg
     * @param svcTaskNum String
     * @return S21SsmEZDResult
     */
    public String getMsgFlg(NSBL0010CMsg bizMsg, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("glblCmpyCd", getGlobalCompanyCode());
        paramMap.put("svcTaskNum", svcTaskNum);
        paramMap.put("svcMemoCatgTpCd", SVC_MEMO_CATG.DISPATCH_MEMO);

        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getMsgFlg", paramMap);
        if (rs.isCodeNotFound()) {
            return ZYPConstant.FLG_OFF_N;
        }
        return ZYPConstant.FLG_ON_Y;
    }

    /**
     * getFsrVisitByLtstFlg
     * @param glblCmpyCd String
     * @param fsrNum String
     * @param fsrVisitLtstFlg String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFsrVisitByLtstFlg(String  glblCmpyCd, String fsrNum, String fsrVisitLtstFlg) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("fsrNum", fsrNum);
        paramMap.put("fsrVisitLtstFlg", fsrVisitLtstFlg);

        return getSsmEZDClient().queryObjectList("getFsrVisitByLtstFlg", paramMap);
    }

    // START 2014/06/02 T.Aoyagi [CSA-166,ADD]
    /**
     * getFsrVisitByContinuousCall
     * @param aSMsg NSBL0010_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFsrVisitByContinuousCall(NSBL0010_ASMsg aSMsg) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", aSMsg.glblCmpyCd_A.getValue());
        paramMap.put("fsrNum", aSMsg.fsrNum_A.getValue());
        paramMap.put("fsrVisitStsCd", SVC_TASK_STS.CONTINUOUS_OPEN);

        return getSsmEZDClient().queryObjectList("getFsrVisitByContinuousCall", paramMap);
    }
    // END 2014/06/02 T.Aoyagi [CSA-166,ADD]

    /**
     * createNSBL0010F00
     * @param bizMsg NSBL0010CMsg
     */
    public void createNSBL0010F00(NSBL0010CMsg bizMsg) {

        // Del #2852 2013/10/15 K.Shibuya Start
        // String csvDtFormat = ZYPCodeDataUtil.getVarCharConstValue(KEY_SVC_DISPT_CSV_DT_FORMAT, getGlobalCompanyCode());
        // Del #2852 2013/10/15 K.Shibuya End
        String csvTmFormat = ZYPCodeDataUtil.getVarCharConstValue(KEY_SVC_DISPT_CSV_TM_FORMAT, getGlobalCompanyCode());

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("bizMsg", bizMsg);
        setSvcTaskStsCdCond(bizMsg, paramMap);
        setSearchSvcTaskParam(paramMap);
        paramMap.put("downloadFlg", ZYPConstant.FLG_ON_Y);
        // Mod #2852 2013/10/25 K.Shibuya Start
        // paramMap.put("csvDtFormat", csvDtFormat);
        paramMap.put("csvDtFormat", ZYPDateUtil.getDateFormatString(true));
        // Mod #2852 2013/10/25 K.Shibuya End
        paramMap.put("csvTmFormat", csvTmFormat);
        paramMap.put("rowNum", MAX_CSV_ROW_COUNT + 1);
        // START 2016/12/14 K.Kojima [QC#15653,ADD]
        paramMap.put("maxDt", MAX_DT_VAL);
        paramMap.put("slsDt", ZYPDateUtil.getSalesDate());
        // END 2016/12/14 K.Kojima [QC#15653,ADD]
        // START 2016/12/21 K.Kojima [QC#15653,ADD]
        paramMap.put("flgY", ZYPConstant.FLG_ON_Y);
        // END 2016/12/21 K.Kojima [QC#15653,ADD]
     // START 2017/02/06 N.Arai [QC#17197, MOD]
        paramMap.put("notAllowed", SVC_ASG_TP.NOT_ALLOWED);
        paramMap.put("creditProfile", SVC_TASK_HLD_RSN.CREDIT_PROFILE);
     // add start 2018/07/19 QC#27143
        paramMap.put("overDue", SVC_TASK_HLD_RSN.OVER_DUE);
// add end 2018/07/19 QC#27143
        if (!getStatusSerchTable(bizMsg, paramMap)) {
            return; 
        }
     // END 2017/02/06 N.Arai [QC#17197, MOD]

        // START MODIFY M.FUJI [Defect#3306]
        S21SsmExecutionParameter rsParam = new S21SsmExecutionParameter();
        rsParam.setFetchSize(FETCH_SIZE_MAX);
        rsParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        rsParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        rsParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

//        S21SsmBatchClient.getClient(NSBL0010Query.class).queryObject("getSvcTask", paramMap, new NSBL0010F00Writer(bizMsg));
        S21SsmBatchClient.getClient(NSBL0010Query.class).queryObject("getSvcTask", paramMap, rsParam, new NSBL0010F00Writer(bizMsg));
        // END MODIFY M.FUJI [Defect#3306]
    }

    /**
     * NSBL0010F00Writer
     */
    private static final class NSBL0010F00Writer extends S21SsmVoidResultSetHandlerSupport {

        /** cMsg */
        private final NSBL0010CMsg cMsg;

        public NSBL0010F00Writer(NSBL0010CMsg cMsg) {
            this.cMsg = cMsg;
        }

        @Override
        protected void doProcessQueryResult(ResultSet rs) throws SQLException {

            // START MODIFY M.FUJI [Defect#3306]
//            rs.last();
//
//            int rowCount = rs.getRow();
//
//            if (rowCount == 0) {
            if (!rs.next()) {

                cMsg.setMessageInfo(NSBM0002E);

                return;
            }

//            if (rowCount > MAX_CSV_ROW_COUNT) {
//
//                cMsg.setMessageInfo(DSBM0003E, new String[] {String.valueOf(MAX_CSV_ROW_COUNT) });
//
//                return;
//            }
            // END MODIFY M.FUJI [Defect#3306]

            NSBL0010F00FMsg fMsg = new NSBL0010F00FMsg();

            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("NSBL0010"), ".csv");

            ZYPCSVOutFile csv = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
            csv.writeHeader(CSV_HEADER);

            // START MODIFY M.FUJI [Defect#3306]
//            rs.beforeFirst();
            int rowCount = 0;

//            while (rs.next()) {
            do {
            // END MODIFY M.FUJI [Defect#3306]

                // START ADD M.FUJI [Defect#3306]
                rowCount++;

                if (rowCount > MAX_CSV_ROW_COUNT) {
                    cMsg.setMessageInfo(NSBM0003E, new String[] {String.valueOf(MAX_CSV_ROW_COUNT) });
                    return;
                }
                // END ADD M.FUJI [Defect#3306]

                fMsg.clear();

                ZYPEZDItemValueSetter.setValue(fMsg.techCd_A, rs.getString(TECH_CD));
// START 2016/11/29 N.Arai [QC#13901, MOD]
//                ZYPEZDItemValueSetter.setValue(fMsg.orgLayerNum_A, rs.getBigDecimal(ORG_LAYER_NUM));
//                ZYPEZDItemValueSetter.setValue(fMsg.orgCd_A, rs.getString(ORG_CD));
//                ZYPEZDItemValueSetter.setValue(fMsg.orgNm_A, rs.getString(ORG_NM));
                ZYPEZDItemValueSetter.setValue(fMsg.svcContrBrCd_A, rs.getString(FLD_SVC_BR_CD));
                ZYPEZDItemValueSetter.setValue(fMsg.svcContrBrDescTxt_A, rs.getString(SVC_CONTR_BR_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(fMsg.svcByLineBizTpCd_A, rs.getString(SVC_BY_LINE_BIZ_TP_CD));
// END 2016/11/29 N.Arai [QC#13901, MOD]
                ZYPEZDItemValueSetter.setValue(fMsg.mdlNm_A, rs.getString(MDL_NM));
                ZYPEZDItemValueSetter.setValue(fMsg.techSchdTz_A, rs.getString(TECH_SCHD_TZ));
                ZYPEZDItemValueSetter.setValue(fMsg.svcCallPrtyCd_A, rs.getString(SVC_CALL_PRTY_CD));
                ZYPEZDItemValueSetter.setValue(fMsg.origSvcCallPrtyCd_A, rs.getString(ORIG_SVC_CALL_PRTY_CD));
                ZYPEZDItemValueSetter.setValue(fMsg.dsSvcCallTpCd_A, rs.getString(DS_SVC_CALL_TP_CD));
                ZYPEZDItemValueSetter.setValue(fMsg.dsSvcCallTpNm_A, rs.getString(DS_SVC_CALL_TP_NM));
                ZYPEZDItemValueSetter.setValue(fMsg.svcBillTpCd_A, rs.getString(SVC_BILL_TP_CD));
                ZYPEZDItemValueSetter.setValue(fMsg.svcBillTpNm_A, rs.getString(SVC_BILL_TP_NM));
                ZYPEZDItemValueSetter.setValue(fMsg.svcPblmSympTpCd_A, rs.getString(SVC_PBLM_SYMP_TP_CD));
                ZYPEZDItemValueSetter.setValue(fMsg.svcPblmSympTpNm_A, rs.getString(SVC_PBLM_SYMP_TP_NM));
                ZYPEZDItemValueSetter.setValue(fMsg.svcTaskRcvTz_A, rs.getString(SVC_TASK_RCV_TZ));
                ZYPEZDItemValueSetter.setValue(fMsg.serNum_A, rs.getString(SER_NUM));
                ZYPEZDItemValueSetter.setValue(fMsg.custAvalFromHourMn_A, rs.getString(CUST_AVAL_FROM_HOUR_MN));
                ZYPEZDItemValueSetter.setValue(fMsg.custAvalToHourMn_A, rs.getString(CUST_AVAL_TO_HOUR_MN));
                ZYPEZDItemValueSetter.setValue(fMsg.svcRspTmMnAot_A, rs.getBigDecimal(SVC_RSP_TM_MN_AOT));
                ZYPEZDItemValueSetter.setValue(fMsg.svcRspTmNum_A, rs.getBigDecimal(SVC_RSP_TM_NUM));
                ZYPEZDItemValueSetter.setValue(fMsg.svcTaskNum_A, rs.getString(SVC_TASK_NUM));
                ZYPEZDItemValueSetter.setValue(fMsg.fsrNum_A, rs.getString(FSR_NUM));
                ZYPEZDItemValueSetter.setValue(fMsg.fsrVisitNum_A, rs.getString(FSR_VISIT_NUM));
                ZYPEZDItemValueSetter.setValue(fMsg.svcTaskStsNm_A, rs.getString(SVC_TASK_STS_NM));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToCustCd_A, rs.getString(SHIP_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(fMsg.locNm_A, rs.getString(LOC_NM));
                ZYPEZDItemValueSetter.setValue(fMsg.techAcptFlg_A, rs.getString(TECH_ACPT_FLG));
                ZYPEZDItemValueSetter.setValue(fMsg.svcCrHldFlg_A, rs.getString(SVC_CR_HLD_FLG));
                ZYPEZDItemValueSetter.setValue(fMsg.techAvalFlg_A, rs.getString(TECH_AVAL_FLG));
                ZYPEZDItemValueSetter.setValue(fMsg.machDownFlg_A, rs.getString(MACH_DOWN_FLG));
                ZYPEZDItemValueSetter.setValue(fMsg.schdDisptEmlFlg_A, rs.getString(SCHD_DISPT_EML_FLG));
                ZYPEZDItemValueSetter.setValue(fMsg.xxBtnFlg_AM, rs.getString(SVC_MEMO_FLG));
                ZYPEZDItemValueSetter.setValue(fMsg.xxBtnFlg_AC, rs.getString(CO_FLG));
                ZYPEZDItemValueSetter.setValue(fMsg.xxBtnFlg_AE, rs.getString(EX_VISIT_FLG));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_A1, rs.getString(XX_DT_TM_A1));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_A2, rs.getString(XX_DT_TM_A2));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_A3, rs.getString(XX_DT_TM_A3));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_A4, rs.getString(XX_DT_TM_A4));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_A5, rs.getString(XX_DT_TM_A5));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_A6, rs.getString(XX_DT_TM_A6));

                csv.write();
                // START MODIFY M.FUJI [Defect#3306]
//              }
              } while (rs.next());
              // END MODIFY M.FUJI [Defect#3306]

            csv.close();
        }
    }

// START 2016/11/29 N.Arai [QC#13901, MOD]
    /**
     * getSvcBillTpList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcBillTpList() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getSvcBillTpList", params);
    }
// END 2016/11/29 N.Arai [QC#13901, MOD]

// START 2017/01/05 N.Arai [QC#13901-2, MOD]
    /**
     * getSvcLineBizList
     * @param glblCmpyCd 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcLineBizList(String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("sysSrcCd", SYS_SRC.S21_SERVICE);

        return getSsmEZDClient().queryObjectList("getSvcLineBiz", params);
    }
// END 2017/01/05 N.Arai [QC#13901-2, MOD]

}
