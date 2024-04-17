package business.blap.NPAL1090;

import static business.blap.NPAL1090.constant.NPAL1090Constant.CSV_FILE_NAME;
import static business.blap.NPAL1090.constant.NPAL1090Constant.EXTN_CSV;
import static business.blap.NPAL1090.constant.NPAL1090Constant.MAX_DOWNLOAD_CNT;
import static business.blap.NPAL1090.constant.NPAL1090Constant.NMAM8181W;
import static business.blap.NPAL1090.constant.NPAL1090Constant.NPAM0089E;
import static business.blap.NPAL1090.constant.NPAL1090Constant.ZZZM9001E;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1090.common.NPAL1090CommonLogic;
import business.blap.NPAL1090.constant.NPAL1090Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeData;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeException;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request List
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/09/2015   CITS       Yasushi Nomura        Create          N/A
 * 12/19/2016   CITS            K.Ogino         Update          QC#15825
 *</pre>
 */
public class NPAL1090BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NPAL1090Scrn00_Search".equals(screenAplID)) {
                doProcess_NPAL1090Scrn00_Search((NPAL1090CMsg) cMsg, (NPAL1090SMsg) sMsg);
            } else if ("NPAL1090Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NPAL1090Scrn00_PageNext((NPAL1090CMsg) cMsg, (NPAL1090SMsg) sMsg);
            } else if ("NPAL1090Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NPAL1090Scrn00_PagePrev((NPAL1090CMsg) cMsg, (NPAL1090SMsg) sMsg);
            } else if (("NPAL1090_INIT".equals(screenAplID)) || ("NPAL1090Scrn00_CMN_Reset".equals(screenAplID))) {
                doProcess_NPAL1090_INIT((NPAL1090CMsg) cMsg, (NPAL1090SMsg) sMsg);
            } else if ("NPAL1090Scrn00_OnChange_SearchOption".equals(screenAplID)) {
                doProcess_OnChange_SearchOption((NPAL1090CMsg) cMsg, (NPAL1090SMsg) sMsg);
            } else if ("NPAL1090Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NPAL1090Scrn00_CMN_Download((NPAL1090CMsg) cMsg, (NPAL1090SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT
     * @param cMsg NPAL1090CMsg
     * @param sMsg NPAL1090SMsg
     */
    private void doProcess_NPAL1090_INIT(NPAL1090CMsg cMsg, NPAL1090SMsg sMsg) {
        String srchOptUsrId = cMsg.srchOptUsrId_U1.getValue();

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        sMsg.clear();
        cMsg.clear();
        cMsg.srchOptUsrId_U1.setValue(srchOptUsrId);

        String globalCompanyCode = getGlobalCompanyCode();
        // SearchOption
        NPAL1090CommonLogic.createPullDownSearchOption(cMsg, globalCompanyCode);
        NPAL1090CommonLogic.createPullDownRequisitionType(cMsg, globalCompanyCode);
        NPAL1090CommonLogic.createPullDownHeaderStatus(cMsg, globalCompanyCode);
        NPAL1090CommonLogic.createPullDownApprovalStatus(cMsg, globalCompanyCode);
        NPAL1090CommonLogic.createPullDownDocumentSourceType(cMsg, globalCompanyCode);
        NPAL1090CommonLogic.createPullDownRequestedShipMethod(cMsg, globalCompanyCode);
    }

    /**
     * <pre>
     * Serch Event
     * </pre>
     * @param cMsg NPAL1090CMsg
     * @param sMsg NPAL1090SMsg
     */
    private void doProcess_NPAL1090Scrn00_Search(NPAL1090CMsg cMsg, NPAL1090SMsg sMsg) {
        NPAL1090CommonLogic.complementFromToDate(cMsg.prchReqCratDt_HF, cMsg.prchReqCratDt_HT);
        NPAL1090CommonLogic.complementFromToDate(cMsg.rqstRcvDt_HF, cMsg.rqstRcvDt_HT);

        // do search
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("prchReqRecTpCdRequest", PRCH_REQ_REC_TP.TECH_REQUEST);
        ssmParam.put("prchReqRecTpCdReturn", PRCH_REQ_REC_TP.TECH_RETURN);
        ssmParam.put("prchReqNum_H1", cMsg.prchReqNum_H1);
        ssmParam.put("prchReqTpCd_SE", cMsg.prchReqTpCd_SE);
        ssmParam.put("prchReqStsCd_SE", cMsg.prchReqStsCd_SE);
        ssmParam.put("prchReqApvlStsCd_SE", cMsg.prchReqApvlStsCd_SE);
        ssmParam.put("prchReqCratDt_HF", cMsg.prchReqCratDt_HF);
        ssmParam.put("prchReqCratDt_HT", cMsg.prchReqCratDt_HT);
        ssmParam.put("prchReqSrcTpCd_SE", cMsg.prchReqSrcTpCd_SE);
        ssmParam.put("fsrNum_H1", cMsg.fsrNum_H1);
        ssmParam.put("svcTaskNum_H1", cMsg.svcTaskNum_H1);
        ssmParam.put("svcMachSerNum_H1", cMsg.svcMachSerNum_H1);
        ssmParam.put("rqstTechTocCd_AC", cMsg.rqstTechTocCd_AC);
        ssmParam.put("shipToCustCd_H1", cMsg.shipToCustCd_H1);
        ssmParam.put("locNm_H1", cMsg.locNm_H1);
        ssmParam.put("rqstRcvDt_HF", cMsg.rqstRcvDt_HF);
        ssmParam.put("rqstRcvDt_HT", cMsg.rqstRcvDt_HT);
        ssmParam.put("rtlWhCd_H1", cMsg.rtlWhCd_H1);
        ssmParam.put("rtlSwhCd_H1", cMsg.rtlSwhCd_H1);
        ssmParam.put("shpgSvcLvlCd_SE", cMsg.shpgSvcLvlCd_SE);
        ssmParam.put("carrNm_H1", cMsg.carrNm_H1);
        ssmParam.put("techNm_H1", cMsg.techNm_H1);
        ssmParam.put("rowNum", sMsg.A.length() + 1);

        S21SsmEZDResult result = NPAL1090Query.getInstance().search(ssmParam);
        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();
            if (resultMap.size() >= (sMsg.A.length() + 1)) {
                // 1000 over
                cMsg.setMessageInfo(NMAM8181W, new String[] {"" + sMsg.A.length(), "" + sMsg.A.length() });
            }
            Map<String, String> techTZIdMap = new HashMap<String, String>();
            int addLineCount = 0;
            for (int i = 0; i < resultMap.size(); i++) {
                if ((sMsg.A.length() - 1) <= i) {
                    break;
                }
                Map<String, Object> recode = (Map<String, Object>) resultMap.get(i);
                setDetail(sMsg, recode, techTZIdMap, i, getGlobalCompanyCode());

                // Copy
                if (i < cMsg.A.length()) {
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                }
                addLineCount++;
            }
            if (addLineCount < cMsg.A.length()) {
                cMsg.A.setValidCount(addLineCount);
            } else {
                cMsg.A.setValidCount(cMsg.A.length());
            }
            sMsg.A.setValidCount(addLineCount);
            NPAL1090CommonLogic.setPagePos(cMsg, sMsg);
        } else {
            // not has search result
            cMsg.setMessageInfo(NPAM0089E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    private static void setDetail(NPAL1090SMsg sMsg, Map<String, Object> map, Map<String, String> techTZIdMap, int index, String glblCmpyCd) {

        // QC#15825
        String rtlWhCd = null;
        if (PRCH_REQ_TP.TECH_RETURN.equals((String) map.get("PRCH_REQ_TP_CD")) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals((String) map.get("PRCH_REQ_TP_CD"))) {
            rtlWhCd = (String) map.get("SRC_RTL_WH_CD");
        } else {
            rtlWhCd = (String) map.get("DEST_RTL_WH_CD");
        }

        String lclTZId = null;
        if (ZYPCommonFunc.hasValue(rtlWhCd)) {
            if (techTZIdMap.containsKey(rtlWhCd)) {
                lclTZId = techTZIdMap.get(rtlWhCd);
            } else {
                String postCd = null;
                String ctryCd = null;
                Map<String, Object> locInfo =  NPAL1090CommonLogic.getLocation(glblCmpyCd, rtlWhCd);
                if (locInfo != null) {
                    postCd = (String) locInfo.get("POST_CD");
                    ctryCd = (String) locInfo.get("CTRY_CD");
                }

                if (ZYPCommonFunc.hasValue(ctryCd) && ZYPCommonFunc.hasValue(postCd)) {
                    try {
                        // QC#18922
                        postCd = NPAL1090CommonLogic.subStrPostCd(postCd);
                        lclTZId = ZYPLocalTimeUtil.getTZId(ctryCd, postCd);
                    } catch (ZYPLocalTimeException e) {
                        lclTZId = null;
                    }
                } else {
                    S21InfoLogOutput.println("Address information of the technician is not registered. TECH_TOC_CD = " + (String) map.get("RQST_TECH_TOC_CD"));
                }

                techTZIdMap.put(rtlWhCd, lclTZId);
            }
        }

        // Tech Request #
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqNum_AC, (String) map.get("PRCH_REQ_NUM"));
        // Request Type
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqTpDescTxt_D1, (String) map.get("PRCH_REQ_TP_DESC_TXT"));
        // Header Status
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqStsDescTxt_D1, (String) map.get("PRCH_REQ_STS_DESC_TXT"));
        // Approval Status
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqApvlStsDescTxt_D1, (String) map.get("PRCH_REQ_APVL_STS_DESC_TXT"));
        // Date Created
        String prchReqCratDt = (String) map.get("PRCH_REQ_CRAT_DT");
        String prchReqCratTm = (String) map.get("PRCH_REQ_CRAT_TM");
        if ((prchReqCratDt != null) && (prchReqCratTm != null)) {
            ZYPLocalTimeData lcl = NPAL1090CommonLogic.convertTimeSys2Lcl(prchReqCratDt, prchReqCratTm, lclTZId);
            if (lcl != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D1, NPAL1090CommonLogic.convertTime2ViewText(lcl, true));
            } else {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D1, ZYPDateUtil.formatEzd8ToDisp(prchReqCratDt, true));
            }
        } else if (prchReqCratDt != null) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D1, ZYPDateUtil.formatEzd8ToDisp(prchReqCratDt, true));
        }
        // Date & Time needed
        String rqstRcvDt = (String) map.get("RQST_RCV_DT");
        String rqstRcvTm = (String) map.get("RQST_RCV_TM");
        if ((rqstRcvDt != null) && (rqstRcvTm != null)) {
            ZYPLocalTimeData lcl = NPAL1090CommonLogic.convertTimeSys2Lcl(rqstRcvDt, rqstRcvTm, lclTZId);
            if (lcl != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D2, NPAL1090CommonLogic.convertTime2ViewText(lcl, true));
            } else {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D2, ZYPDateUtil.formatEzd8ToDisp(rqstRcvDt, true));
            }
        } else if (rqstRcvDt != null) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D2, ZYPDateUtil.formatEzd8ToDisp(rqstRcvDt, true));
        }
        // Document Source Type
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqSrcTpDescTxt_D1, (String) map.get("PRCH_REQ_SRC_TP_DESC_TXT"));
        // Source Doc#
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).trxRefNum_D1, (String) map.get("TRX_REF_NUM"));
        // Requester Code
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqCratByPsnCd_D1, (String) map.get("PRCH_REQ_CRAT_BY_PSN_CD"));
        // Requester Name
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).fullPsnNm_D1, (String) map.get("FULL_PSN_NM"));
        // Service Level
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shpgSvcLvlDescTxt_D1, (String) map.get("SHPG_SVC_LVL_DESC_TXT"));
        // Carrier Code
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).carrCd_D1, (String) map.get("CARR_CD"));
        // Carrier Name
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).carrNm_D1, (String) map.get("CARR_NM"));
        // Technician Code
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rqstTechTocCd_D1, (String) map.get("RQST_TECH_TOC_CD"));
        // Technician Name
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).techNm_D1, (String) map.get("TECH_NM"));
        // Tech WH
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).destRtlWhCd_D1, (String) map.get("DEST_RTL_WH_CD"));
        // Tech WH Name
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlWhNm_D1, (String) map.get("RTL_WH_NM"));
        // Tech SWH
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).destRtlSwhCd_D1, (String) map.get("DEST_RTL_SWH_CD"));
        // Tech SWH Name
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlSwhNm_D1, (String) map.get("RTL_SWH_NM"));
        // Ship To Customer Code
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipToCustCd_D1, (String) map.get("SHIP_TO_CUST_CD"));
        // Ship To Customer Name
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).locNm_D1, (String) map.get("LOC_NM"));
        // Service Request#
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).fsrNum_D1, (String) map.get("FSR_NUM"));
        // Service Request Task#
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).svcTaskNum_D1, (String) map.get("SVC_TASK_NUM"));
        // Service Request Serial#
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).svcMachSerNum_D1, (String) map.get("SVC_MACH_SER_NUM"));
    }

    /**
     * <pre>
     * PagePrev Event
     * </pre>
     * @param cMsg NPAL1090CMsg
     * @param sMsg NPAL1090SMsg
     */
    private void doProcess_NPAL1090Scrn00_PagePrev(NPAL1090CMsg cMsg, NPAL1090SMsg sMsg) {

        // set values to items of pageNation for previous page
        // pageNation
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());
        NPAL1090CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * <pre>
     * PageNext Event
     * </pre>
     * @param cMsg NPAL1090CMsg
     * @param sMsg NPAL1090SMsg
     */
    private void doProcess_NPAL1090Scrn00_PageNext(NPAL1090CMsg cMsg, NPAL1090SMsg sMsg) {

        // set values to items of pageNation for next page pageNation
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length());
        NPAL1090CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * Pulldown onChange Event
     * @param cMsg NPAL1090CMsg
     * @param sMsg NPAL1090SMsg
     */
    private void doProcess_OnChange_SearchOption(NPAL1090CMsg cMsg, NPAL1090SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_SE)) {
            NPAL1090CommonLogic.callNszc0330forSearchSearchOption(cMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());

        }
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NPAL1090Scrn00_CMN_Download(NPAL1090CMsg cMsg, NPAL1090SMsg sMsg) {
        NPAL1090CommonLogic.complementFromToDate(cMsg.prchReqCratDt_HF, cMsg.prchReqCratDt_HT);
        NPAL1090CommonLogic.complementFromToDate(cMsg.rqstRcvDt_HF, cMsg.rqstRcvDt_HT);

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NPAL1090Constant.FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NPAL1090Query.getInstance().getClass());
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), EXTN_CSV);

            String statementId = "search";
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
            ssmParam.put("prchReqRecTpCdRequest", PRCH_REQ_REC_TP.TECH_REQUEST);
            ssmParam.put("prchReqRecTpCdReturn", PRCH_REQ_REC_TP.TECH_RETURN);
            ssmParam.put("prchReqNum_H1", cMsg.prchReqNum_H1);
            ssmParam.put("prchReqTpCd_SE", cMsg.prchReqTpCd_SE);
            ssmParam.put("prchReqStsCd_SE", cMsg.prchReqStsCd_SE);
            ssmParam.put("prchReqApvlStsCd_SE", cMsg.prchReqApvlStsCd_SE);
            ssmParam.put("prchReqCratDt_HF", cMsg.prchReqCratDt_HF);
            ssmParam.put("prchReqCratDt_HT", cMsg.prchReqCratDt_HT);
            ssmParam.put("prchReqSrcTpCd_SE", cMsg.prchReqSrcTpCd_SE);
            ssmParam.put("fsrNum_H1", cMsg.fsrNum_H1);
            ssmParam.put("svcTaskNum_H1", cMsg.svcTaskNum_H1);
            ssmParam.put("svcMachSerNum_H1", cMsg.svcMachSerNum_H1);
            ssmParam.put("rqstTechTocCd_AC", cMsg.rqstTechTocCd_AC);
            ssmParam.put("shipToCustCd_H1", cMsg.shipToCustCd_H1);
            ssmParam.put("locNm_H1", cMsg.locNm_H1);
            ssmParam.put("rqstRcvDt_HF", cMsg.rqstRcvDt_HF);
            ssmParam.put("rqstRcvDt_HT", cMsg.rqstRcvDt_HT);
            ssmParam.put("rtlWhCd_H1", cMsg.rtlWhCd_H1);
            ssmParam.put("rtlSwhCd_H1", cMsg.rtlSwhCd_H1);
            ssmParam.put("shpgSvcLvlCd_SE", cMsg.shpgSvcLvlCd_SE);
            ssmParam.put("carrNm_H1", cMsg.carrNm_H1);
            ssmParam.put("techNm_H1", cMsg.techNm_H1);
            ssmParam.put("rowNum", MAX_DOWNLOAD_CNT);
            stmtSelect = ssmLLClient.createPreparedStatement(statementId, ssmParam, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                cMsg.setMessageInfo(ZZZM9001E, null);
                return;
            }
            NPAL1090CommonLogic.writeCsvFileInfo(cMsg, rs, getGlobalCompanyCode());
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

    }
}
