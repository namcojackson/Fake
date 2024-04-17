package business.blap.NPAL1260;

import static business.blap.NPAL1260.constant.NPAL1260Constant.BUSINESS_APPLICATION_ID;
import static business.blap.NPAL1260.constant.NPAL1260Constant.CSV_FILE_NAME;
import static business.blap.NPAL1260.constant.NPAL1260Constant.DATE_STR_LENGTH;
import static business.blap.NPAL1260.constant.NPAL1260Constant.EXTN_CSV;
import static business.blap.NPAL1260.constant.NPAL1260Constant.LIKE_SEARCH_CHAR;
import static business.blap.NPAL1260.constant.NPAL1260Constant.MAX_DOWNLOAD_CNT;
import static business.blap.NPAL1260.constant.NPAL1260Constant.NMAM8181W;
import static business.blap.NPAL1260.constant.NPAL1260Constant.NPAM0089E;
import static business.blap.NPAL1260.constant.NPAL1260Constant.PARENT_LINE_SUB_NUM;
import static business.blap.NPAL1260.constant.NPAL1260Constant.TIME_STR_LENGTH;
import static business.blap.NPAL1260.constant.NPAL1260Constant.VAR_CHAR_POD_ARV_STS_CD;
import static business.blap.NPAL1260.constant.NPAL1260Constant.VND_PO_ACK_STS_CD_IB;
import static business.blap.NPAL1260.constant.NPAL1260Constant.ZZZM9001E;
import static business.blap.NPAL1260.constant.NPAL1260Constant.NPAB1650_NPAL1260_TARGET_POD;
import static business.blap.NPAL1260.constant.NPAL1260Constant.PREMIUM_RUSH_CONDITION_TPL_CARR_NM;
import static business.blap.NPAL1260.constant.NPAL1260Constant.RUSH_CONDITION_TPL_CARR_NM_FEDEX;
import static business.blap.NPAL1260.constant.NPAL1260Constant.RUSH_CONDITION_TPL_CARR_NM_UPS;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import business.blap.NPAL1260.common.NPAL1260CommonLogic;
import business.blap.NPAL1260.constant.NPAL1260Constant;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_PO_ACK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeData;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeException;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1260 Tech Parts Request Inquiry
 * Function Name : serch business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/02/2015   CITS       Yasushi Nomura       Create          N/A
 * 03/08/2016   CITS       Takeshi Tokutomi     Update          QC#4275
 * 07/21/2016   CITS       Ryuichi Shimamoto    Update          QC#11142
 * 12/19/2016   CITS       K.Ogino              Update          QC#15825
 * 12/26/2017   CITS       K.Ogino              Update          QC#21908,QC#21887
 * 02/07/2018   CITS       K.Ogino              Update          QC#18922
 * 03/05/2018   CITS       T.Tokutomi           Update          QC#21913
 * 2018/08/17   CITS       K.Ogino              Update          QC#27601
 * 2018/12/04   Fujitsu    S.Takami             Update          QC#27093
 * 2019/04/26   CITS       K.Ogino              Update          QC#31201
 * 2019/05/13   CITS       K.Ogino              Update          QC#31207
 * 03/12/2020   Fujitsu    R.Nakamura           Update          QC#56104
 * 10/05/2021   CITS       R.Azucena            Update          QC#59216
 * 2023/10/25   Hitachi    G.Quan               Update          QC#61494
 *</pre>
 */
public class NPAL1260BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();

            // QC#21908
            if ("NPAL1260Scrn00_Search".equals(screenAplID) || "NPAL1260_NLBL2020".equals(screenAplID)) {
                doProcess_NPAL1260Scrn00_Search((NPAL1260CMsg) cMsg, (NPAL1260SMsg) sMsg);
            } else if (("NPAL1260_INIT".equals(screenAplID)) || ("NPAL1260Scrn00_CMN_Reset".equals(screenAplID))) {
                doProcess_NPAL1260_INIT((NPAL1260CMsg) cMsg, (NPAL1260SMsg) sMsg);
                ZYPGUITableColumn.getColData((NPAL1260CMsg) cMsg, (NPAL1260SMsg) sMsg);
            } else if ("NPAL1260Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NPAL1260Scrn00_PageNext((NPAL1260CMsg) cMsg, (NPAL1260SMsg) sMsg);
            } else if ("NPAL1260Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NPAL1260Scrn00_PagePrev((NPAL1260CMsg) cMsg, (NPAL1260SMsg) sMsg);
            } else if ("NPAL1260Scrn00_OnChange_SearchOption".equals(screenAplID)) {
                doProcess_OnChange_SearchOption((NPAL1260CMsg) cMsg, (NPAL1260SMsg) sMsg);
            } else if (("NPAL1260Scrn00_OpenWin_SrcWhSwh".equals(screenAplID)) || ("NPAL1260Scrn00_OpenWin_DestWhSwh".equals(screenAplID))) {
                doProcess_OpenWin_WhSwh((NPAL1260CMsg) cMsg, (NPAL1260SMsg) sMsg);
            } else if ("NPAL1260_NMAL6760".equals(screenAplID)) {
                doProcess_NPAL1260_NMAL6760((NPAL1260CMsg) cMsg, (NPAL1260SMsg) sMsg);
            } else if ("NPAL1260Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NPAL1260Scrn00_CMN_Download((NPAL1260CMsg) cMsg, (NPAL1260SMsg) sMsg);
            } else if ("NPAL1260Scrn00_OnChange_TechRequestType".equals(screenAplID)) {
                doProcess_NPAL1260Scrn00_OnChange_TechRequestType((NPAL1260CMsg) cMsg, (NPAL1260SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT
     * @param cMsg NPAL1260CMsg
     * @param sMsg NPAL1260SMsg
     */
    private void doProcess_NPAL1260_INIT(NPAL1260CMsg cMsg, NPAL1260SMsg sMsg) {
        String srchOptUsrId = cMsg.srchOptUsrId_U1.getValue();

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        sMsg.clear();
        cMsg.clear();
        cMsg.srchOptUsrId_U1.setValue(srchOptUsrId);

        String globalCompanyCode = getGlobalCompanyCode();
        // SearchOption
        NPAL1260CommonLogic.createPullDownSearchOption(cMsg, globalCompanyCode);
        // Tech Request Type
        NPAL1260CommonLogic.createPullDownRequisitionType(cMsg, globalCompanyCode);
        // Line Type
        NPAL1260CommonLogic.createPullDownLineType(cMsg, globalCompanyCode);
        // Line Status
        NPAL1260CommonLogic.createPullDownLineStatus(cMsg, globalCompanyCode);
        // Tech Territory
        NPAL1260CommonLogic.createPullDownTechTerritory(cMsg, globalCompanyCode);
        // Document Source Type
        NPAL1260CommonLogic.createPullDownDocumentSourceType(cMsg, globalCompanyCode);
        // Actual Service Level
        NPAL1260CommonLogic.createPullDownRequestedShipMethod(cMsg, globalCompanyCode);
    }

    /**
     * <pre>
     * Serch Event
     * </pre>
     * @param cMsg NPAL1260CMsg
     * @param sMsg NPAL1260SMsg
     */
    private void doProcess_NPAL1260Scrn00_Search(NPAL1260CMsg cMsg, NPAL1260SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("prchReqRecTpCdRequest", PRCH_REQ_REC_TP.TECH_REQUEST);
        ssmParam.put("prchReqRecTpCdReturn", PRCH_REQ_REC_TP.TECH_RETURN);
        ssmParam.put("prchReqRecTpCdRequisition", PRCH_REQ_REC_TP.PO_REQUISITION);
        ssmParam.put("prchReqNum_H1", cMsg.prchReqNum_H1);
        ssmParam.put("prchReqTpCd_SE", cMsg.prchReqTpCd_SE);
        ssmParam.put("lineBizTpCd_SE", cMsg.lineBizTpCd_SE);
        ssmParam.put("prchReqSrcTpCd_SE", cMsg.prchReqSrcTpCd_SE);
        ssmParam.put("fsrNum_H1", cMsg.fsrNum_H1);
        ssmParam.put("svcTaskNum_H1", cMsg.svcTaskNum_H1);
        ssmParam.put("svcMachSerNum_H1", cMsg.svcMachSerNum_H1);
        ssmParam.put("mdseCd_H1", cMsg.mdseCd_H1);
        ssmParam.put("prchReqLineTpCd_SE", cMsg.prchReqLineTpCd_SE);
        ssmParam.put("prchReqLineStsCd_SE", cMsg.prchReqLineStsCd_SE);
        ssmParam.put("prchReqCratDt_HF", cMsg.prchReqCratDt_HF);
        ssmParam.put("prchReqCratDt_HT", cMsg.prchReqCratDt_HT);
        ssmParam.put("rqstRcvDt_HF", cMsg.rqstRcvDt_HF);
        ssmParam.put("rqstRcvDt_HT", cMsg.rqstRcvDt_HT);
        ssmParam.put("shipDtTmTs_HF", cMsg.shipDt_HF);
        ssmParam.put("shipDtTmTs_HT", cMsg.shipDt_HT);
        ssmParam.put("xxChkBox_H1", cMsg.xxChkBox_H1);
        ssmParam.put("xxChkBox_H2", cMsg.xxChkBox_H2);
        ssmParam.put("poOrdNum_H1", cMsg.poOrdNum_H1);
        // Mod Start 2020/03/17 QC#56104
//        ssmParam.put("soNum_H1", cMsg.soNum_H1);
        ssmParam.put("soNum_H1", cMsg.rwsRefNum_H1);
        // Mod End 2020/03/17 QC#56104
        ssmParam.put("xxChkBox_H3", cMsg.xxChkBox_H3);
        ssmParam.put("xxChkBox_H4", cMsg.xxChkBox_H4);
        ssmParam.put("destRtlWhCd_H1", cMsg.destRtlWhCd_H1);
        ssmParam.put("destRtlSwhCd_H1", cMsg.destRtlSwhCd_H1);
        ssmParam.put("rtlWhCd_H1", cMsg.rtlWhCd_H1);
        ssmParam.put("rtlSwhCd_H1", cMsg.rtlSwhCd_H1);
        ssmParam.put("proNum_H1", cMsg.proNum_H1);
        ssmParam.put("shpgSvcLvlCd_SE", cMsg.shpgSvcLvlCd_SE);
        ssmParam.put("lineSubNumParent", PARENT_LINE_SUB_NUM);
        // 2018/12/04 QC#27093 Add Start
        ssmParam.put("xxChkBox_H5", cMsg.xxChkBox_H5);
        // 2018/12/04 QC#27093 Add End
        if (ZYPCommonFunc.hasValue(cMsg.techNm_H1)) {
            cMsg.techNm_H1.setValue(LIKE_SEARCH_CHAR + cMsg.techNm_H1.getValue().replace(LIKE_SEARCH_CHAR, "") + LIKE_SEARCH_CHAR);
        }
        ssmParam.put("techNm_H1", cMsg.techNm_H1);
        if (ZYPCommonFunc.hasValue(cMsg.dsAcctNm_H1)) {
            cMsg.dsAcctNm_H1.setValue(LIKE_SEARCH_CHAR + cMsg.dsAcctNm_H1.getValue().replace(LIKE_SEARCH_CHAR, "") + LIKE_SEARCH_CHAR);
        }
        ssmParam.put("dsAcctNm_H1", cMsg.dsAcctNm_H1);
        if (ZYPCommonFunc.hasValue(cMsg.prntVndNm_H1)) {
            cMsg.prntVndNm_H1.setValue(LIKE_SEARCH_CHAR + cMsg.prntVndNm_H1.getValue().replace(LIKE_SEARCH_CHAR, "") + LIKE_SEARCH_CHAR);
        }
        ssmParam.put("prntVndNm_H1", cMsg.prntVndNm_H1);
        if (ZYPCommonFunc.hasValue(cMsg.locNm_H1)) {
            cMsg.locNm_H1.setValue(LIKE_SEARCH_CHAR + cMsg.locNm_H1.getValue().replace(LIKE_SEARCH_CHAR, "") + LIKE_SEARCH_CHAR);
        }
        ssmParam.put("locNm_H1", cMsg.locNm_H1);
        if (ZYPCommonFunc.hasValue(cMsg.carrNm_H1)) {
            cMsg.carrNm_H1.setValue(LIKE_SEARCH_CHAR + cMsg.carrNm_H1.getValue().replace(LIKE_SEARCH_CHAR, "") + LIKE_SEARCH_CHAR);
        }
        ssmParam.put("carrNm_H1", cMsg.carrNm_H1);
        ssmParam.put("prchReqNum_H2", cMsg.prchReqNum_H2);
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        ssmParam.put("vndPoAckStsCd", VND_PO_ACK_STS_CD_IB);
        // QC#31201
        // START 2023/10/25 G.Quan [QC#61494, MOD]
//        String constValue = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_POD_UPD_STS_CD, getGlobalCompanyCode());
//        String[] podUpdStss = null;
//        List<String> podUpdStsList = null;
//        if (constValue != null) {
//            podUpdStss = constValue.split(",");
//        }
//        if (podUpdStss != null) {
//            podUpdStsList = Arrays.asList(podUpdStss);
//            ssmParam.put("podUpdStsList", podUpdStsList);
//        }
        String constValue = ZYPCodeDataUtil.getVarCharConstValue(NPAB1650_NPAL1260_TARGET_POD, getGlobalCompanyCode());
        String[] ediStsCd = null;
        List<String> ediStsCdList = null;
        if (constValue != null) {
            ediStsCd = constValue.split(",");
        }
        if (ediStsCd != null) {
            ediStsCdList = Arrays.asList(ediStsCd);
            ssmParam.put("ediStsCdList", ediStsCdList);
        }
        // END 2023/10/25 G.Quan [QC#61494, MOD]
        constValue = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_POD_ARV_STS_CD, getGlobalCompanyCode());
        String[] podArvStss = null;
        List<String> podArvStsList = null;
        if (constValue != null) {
            podArvStss = constValue.split(",");
        }
        if (podArvStss != null) {
            podArvStsList = Arrays.asList(podArvStss);
            ssmParam.put("podArvStsList", podArvStsList);
        }
        // QC#31207
        List<String> ackStsList = Arrays.asList(new String[]{VND_PO_ACK_STS.ITEM_BACKORDERED});
        ssmParam.put("ackStsList", ackStsList);
        BigDecimal ltDays = ZYPCodeDataUtil.getNumConstValue("PO_ACK_LT_DAY", getGlobalCompanyCode());
        if (!ZYPCommonFunc.hasValue(ltDays)) {
            ltDays = BigDecimal.ZERO;
        }
        ssmParam.put("days", ltDays);
        // START 2021/10/05 R.Azucena[QC#59216, ADD]
        ssmParam.put("shipConfirmationCdArray", new String[] {PRCH_REQ_SRC_TP.CHOICE_SHIP_CONFIRMATION, PRCH_REQ_SRC_TP.MNX_SHIP_CONFIRMATION });
        // END 2021/10/05 R.Azucena[QC#59216, ADD]
        // START 2023/10/25 G.Quan [QC#61494, ADD]
        ssmParam.put("prchReqTpCdPremiumRush", PRCH_REQ_TP.PREMIUM_RUSH);
        ssmParam.put("prchReqTpCdRush", PRCH_REQ_TP.RUSH);
        ssmParam.put("premiumRushConditionTplCarrNm", PREMIUM_RUSH_CONDITION_TPL_CARR_NM);
        List<String> rushConditionTplCarrNmList =
            Arrays.asList(new String[]{RUSH_CONDITION_TPL_CARR_NM_FEDEX, RUSH_CONDITION_TPL_CARR_NM_UPS});
        ssmParam.put("rushConditionTplCarrNmList", rushConditionTplCarrNmList);
        // END 2023/10/25 G.Quan [QC#61494, ADD]

        // Execute
        S21SsmEZDResult result = NPAL1260Query.getInstance().search(ssmParam);

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
            NPAL1260CommonLogic.setPagePos(cMsg, sMsg);
        } else {
            // not has search result
            cMsg.setMessageInfo(NPAM0089E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
        }
    }

    private static void setDetail(NPAL1260SMsg sMsg, Map<String, Object> map, Map<String, String> techTZIdMap, int index, String glblCmpyCd) {

        // QC#15825
        String rtlWhCd = null;
        if (PRCH_REQ_TP.TECH_RETURN.equals((String) map.get("PRCH_REQ_TP_CD"))) {
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
                Map<String, Object> locInfo =  NPAL1260CommonLogic.getLocation(glblCmpyCd, rtlWhCd);
                if (locInfo != null) {
                    postCd = (String) locInfo.get("POST_CD");
                    ctryCd = (String) locInfo.get("CTRY_CD");
                }

                if (ZYPCommonFunc.hasValue(ctryCd) && ZYPCommonFunc.hasValue(postCd)) {
                    try {
                        // QC#18922
                        postCd = NPAL1260CommonLogic.subStrPostCd(postCd);
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

        BigDecimal lineSubNum = (BigDecimal) map.get("PRCH_REQ_LINE_SUB_NUM");
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqNum_D1, (String) map.get("PRCH_REQ_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineNum_D1, (String) map.get("PRCH_REQ_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineSubNum_D1, lineSubNum);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqTpDescTxt_D1, (String) map.get("PRCH_REQ_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpDescTxt_D1, (String) map.get("PRCH_REQ_LINE_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseCd_D1, (String) map.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseDescShortTxt_D1, (String) map.get("MDSE_DESC_SHORT_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).procrTpDescTxt_D1, (String) map.get("PROCR_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).srcRtlWhCd_D1, (String) map.get("SRC_RTL_WH_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlWhNm_D1, (String) map.get("RTL_WH_NM1"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).srcRtlSwhCd_D1, (String) map.get("SRC_RTL_SWH_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlSwhNm_D1, (String) map.get("RTL_SWH_NM1"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).destRtlWhCd_D1, (String) map.get("DEST_RTL_WH_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlWhNm_D2, (String) map.get("RTL_WH_NM2"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).destRtlSwhCd_D1, (String) map.get("DEST_RTL_SWH_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlSwhNm_D2, (String) map.get("RTL_SWH_NM2"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineStsDescTxt_D1, (String) map.get("PRCH_REQ_LINE_STS_DESC_TXT"));
        // QC#27601
        if (ZYPCommonFunc.hasValue((String) map.get("BO_QTY"))) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).ordQty_D2, new BigDecimal((String) map.get("BO_QTY")));
        }

        if (PRCH_REQ_TP.TECH_RETURN.equals((String) map.get("PRCH_REQ_TP_CD"))) {
            if (BigDecimal.ZERO.compareTo(lineSubNum) == 0) {
                // Parent
                // Req QTY
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqDispQty_D1, (BigDecimal) map.get("PRCH_REQ_QTY"));
                // Fulfilled Qty
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipQty_D1, (BigDecimal) map.get("SHIP_QTY"));
                // Received Qty
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rwsPutAwayQty_D1, (BigDecimal) map.get("RWS_PUT_AWAY_QTY"));
            } else {
                // Child
                // Ord Qty
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).ordQty_D1, (BigDecimal) map.get("PRCH_REQ_QTY"));
                // Fulfilled Qty
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipQty_D1, (BigDecimal) map.get("SHIP_QTY"));
                // Received Qty
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rwsPutAwayQty_D1, (BigDecimal) map.get("RWS_PUT_AWAY_QTY"));
            }
        } else {
            if (BigDecimal.ZERO.compareTo(lineSubNum) == 0) {
                // Parent
                // Req QTY
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqDispQty_D1, (BigDecimal) map.get("PRCH_REQ_QTY"));
                // Fulfilled Qty
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipQty_D1, (BigDecimal) map.get("SUM_SHIP_QTY"));
                // Received Qty
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rwsPutAwayQty_D1, (BigDecimal) map.get("SUM_RWS_PUT_AWAY_QTY"));
            } else {
                // Child
                // Ord Qty
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).ordQty_D1, (BigDecimal) map.get("PRCH_REQ_QTY"));
                // Fulfilled Qty
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipQty_D1, (BigDecimal) map.get("SHIP_QTY"));
                // Received Qty
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rwsPutAwayQty_D1, (BigDecimal) map.get("RWS_PUT_AWAY_QTY"));
            }
        }

        // Freeze Flag
        if (ZYPConstant.FLG_ON_Y.equals((String) map.get("PRCH_REQ_FRZ_FLG"))) {
            sMsg.A.no(index).prchReqFrzFlg_D1.setValue(ZYPConstant.FLG_ON_Y);
        }
        // Insourced Flag
        if (ZYPConstant.FLG_ON_Y.equals((String) map.get("INSRC_FLG"))) {
            sMsg.A.no(index).insrcFlg_D1.setValue(ZYPConstant.FLG_ON_Y);
        }
        // Purchased Flag
        if (ZYPConstant.FLG_ON_Y.equals((String) map.get("PO_CRAT_FLG"))) {
            sMsg.A.no(index).poCratFlg_D1.setValue(ZYPConstant.FLG_ON_Y);
        }
        // CancelledFlag
        BigDecimal cancelQty = (BigDecimal) map.get("PRCH_REQ_CANC_QTY");
        if ((cancelQty != null) && (0 < cancelQty.intValue())) {
            sMsg.A.no(index).znFlg_D1.setValue(ZYPConstant.FLG_ON_Y);
        }

        // Purchased Request Line number
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).origPrchReqLineNum_D1, (String) map.get("ORIG_PRCH_REQ_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).origPrchReqLineSubNum_D1, (BigDecimal) map.get("ORIG_PRCH_REQ_LINE_SUB_NUM"));

        if (ZYPCommonFunc.hasValue((String) map.get("ORIG_PRCH_REQ_LINE_NUM"))
                || ZYPCommonFunc.hasValue((BigDecimal) map.get("ORIG_PRCH_REQ_LINE_SUB_NUM"))) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxAddInvNumTxt_D1, ".");
        }

        // Date Created
        String prchReqCratDt = (String) map.get("PRCH_REQ_CRAT_DT");
        String prchReqCratTm = (String) map.get("PRCH_REQ_CRAT_TM");
        if ((prchReqCratDt != null) && (prchReqCratTm != null)) {
            ZYPLocalTimeData lcl = NPAL1260CommonLogic.convertTimeSys2Lcl(prchReqCratDt, prchReqCratTm, lclTZId);
            if (lcl != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D1, NPAL1260CommonLogic.convertTime2ViewText(lcl, true));
            } else {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D1, ZYPDateUtil.formatEzd8ToDisp(prchReqCratDt, true));
            }
        } else if (prchReqCratDt != null) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D1, ZYPDateUtil.formatEzd8ToDisp(prchReqCratDt, true));
        }
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqCratDt_D1, prchReqCratDt);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqCratTm_D1, prchReqCratTm);
        // Date Needed
        String rqstRcvDt = (String) map.get("RQST_RCV_DT");
        String rqstRcvTm = (String) map.get("RQST_RCV_TM");
        if ((rqstRcvDt != null) && (rqstRcvTm != null)) {
            ZYPLocalTimeData lcl = NPAL1260CommonLogic.convertTimeSys2Lcl(rqstRcvDt, rqstRcvTm, lclTZId);
            if (lcl != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D2, NPAL1260CommonLogic.convertTime2ViewText(lcl, true));
            } else {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D2, ZYPDateUtil.formatEzd8ToDisp(rqstRcvDt, true));
            }
        } else if (rqstRcvDt != null) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D2, ZYPDateUtil.formatEzd8ToDisp(rqstRcvDt, true));
        }
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rqstRcvDt_D1, rqstRcvDt);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rqstRcvDispTm_D1, rqstRcvTm);
        // Ship Date
        String shipDate = (String) map.get("SHIP_DT_TM_TS");
        if (shipDate != null) {
            String shipD = null;
            String shipT = null;
            if (DATE_STR_LENGTH <= shipDate.length()) {
                shipD = shipDate.substring(0, DATE_STR_LENGTH);
            }
            if (DATE_STR_LENGTH + TIME_STR_LENGTH <= shipDate.length()) {
                shipT = shipDate.substring(DATE_STR_LENGTH, DATE_STR_LENGTH + TIME_STR_LENGTH);
            }
            if ((shipD != null) && (shipT != null)) {
                ZYPLocalTimeData lcl = NPAL1260CommonLogic.convertTimeSys2Lcl(shipD, shipT, lclTZId);
                if (lcl != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D3, NPAL1260CommonLogic.convertTime2ViewText(lcl, true));
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D3, ZYPDateUtil.formatEzd8ToDisp(shipD, true));
                }
            } else if (shipD != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D3, ZYPDateUtil.formatEzd8ToDisp(shipD, true));
            }
        }
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipDtTmTs_D1, shipDate);
        // Date Received
        String rwsDate = (String) map.get("RWS_CLO_DT_TM_TS");
        if (rwsDate != null) {
            String rwsD = null;
            String rwsT = null;
            if (DATE_STR_LENGTH <= rwsDate.length()) {
                rwsD = rwsDate.substring(0, DATE_STR_LENGTH);
            }
            if (DATE_STR_LENGTH + TIME_STR_LENGTH <= rwsDate.length()) {
                rwsT = rwsDate.substring(DATE_STR_LENGTH, DATE_STR_LENGTH + TIME_STR_LENGTH);
            }
            if ((rwsD != null) && (rwsT != null)) {
                ZYPLocalTimeData lcl = NPAL1260CommonLogic.convertTimeSys2Lcl(rwsD, rwsT, lclTZId);
                if (lcl != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D4, NPAL1260CommonLogic.convertTime2ViewText(lcl, true));
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D4, ZYPDateUtil.formatEzd8ToDisp(rwsD, true));
                }
            } else if (rwsD != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D4, ZYPDateUtil.formatEzd8ToDisp(rwsD, true));
            }
        }
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rwsCloDtTmTs_D1, rwsDate);
        // Ack Date B/O
        String ackDate = (String) map.get("ORD_LAST_UPD_TS");
        if (ackDate != null) {
            String ackD = null;
            String ackT = null;
            if (DATE_STR_LENGTH <= ackDate.length()) {
                ackD = ackDate.substring(0, DATE_STR_LENGTH);
            }
            if (DATE_STR_LENGTH + TIME_STR_LENGTH <= ackDate.length()) {
                ackT = ackDate.substring(DATE_STR_LENGTH, DATE_STR_LENGTH + TIME_STR_LENGTH);
            }
            if ((ackD != null) && (ackT != null)) {
                ZYPLocalTimeData lcl = NPAL1260CommonLogic.convertTimeSys2Lcl(ackD, ackT, lclTZId);
                if (lcl != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D5, NPAL1260CommonLogic.convertTime2ViewText(lcl, true));
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D5, ZYPDateUtil.formatEzd8ToDisp(ackD, true));
                }
            } else if (ackD != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D5, ZYPDateUtil.formatEzd8ToDisp(ackD, true));
            }
        }
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).ordLastUpdTs_D1, ackDate);
        // QC#21887
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpCd_D1, (String) map.get("PRCH_REQ_LINE_TP_CD"));
        // START 2023/10/25 G.Quan [QC#61494, ADD]
        // Delivery Date
        String delyDate = (String) map.get("DELY_DT");
        if (delyDate != null) {
            String delyD = null;
            String delyT = null;
            if (DATE_STR_LENGTH <= delyDate.length()) {
                delyD = delyDate.substring(0, DATE_STR_LENGTH);
            }
            if (DATE_STR_LENGTH + TIME_STR_LENGTH <= delyDate.length()) {
                delyT = delyDate.substring(DATE_STR_LENGTH, DATE_STR_LENGTH + TIME_STR_LENGTH);
            }
            if ((delyD != null) && (delyT != null)) {
                ZYPLocalTimeData lcl = NPAL1260CommonLogic.convertTimeSys2Lcl(delyD, delyT, lclTZId);
                if (lcl != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D6, NPAL1260CommonLogic.convertTime2ViewText(lcl, true));
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D6, ZYPDateUtil.formatEzd8ToDisp(delyD, true));
                }
            } else if (delyD != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D6, ZYPDateUtil.formatEzd8ToDisp(delyD, true));
            }
        }
        // END 2023/10/25 G.Quan [QC#61494, ADD]
        // ETA Date. QC#27601
        String etaDate = (String) map.get("ETA_DT");
        if (etaDate != null) {
            String etaD = null;
            String etaT = null;
            if (DATE_STR_LENGTH <= etaDate.length()) {
                etaD = etaDate.substring(0, DATE_STR_LENGTH);
            }
            if (DATE_STR_LENGTH + TIME_STR_LENGTH <= etaDate.length()) {
                etaT = etaDate.substring(DATE_STR_LENGTH, DATE_STR_LENGTH + TIME_STR_LENGTH);
            }
            if ((etaD != null) && (etaT != null)) {
                ZYPLocalTimeData lcl = NPAL1260CommonLogic.convertTimeSys2Lcl(etaD, etaT, lclTZId);
                if (lcl != null) {
                    // START 2023/10/25 G.Quan [QC#61494, MOD]
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D6, NPAL1260CommonLogic.convertTime2ViewText(lcl, true));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D7, NPAL1260CommonLogic.convertTime2ViewText(lcl, true));
                    // END 2023/10/25 G.Quan [QC#61494, MOD]
                } else {
                    // START 2023/10/25 G.Quan [QC#61494, MOD]
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D6, ZYPDateUtil.formatEzd8ToDisp(etaD, true));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D7, ZYPDateUtil.formatEzd8ToDisp(etaD, true));
                    // END 2023/10/25 G.Quan [QC#61494, MOD]
                }
            } else if (etaD != null) {
                // START 2023/10/25 G.Quan [QC#61494, MOD]
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D6, ZYPDateUtil.formatEzd8ToDisp(etaD, true));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D7, ZYPDateUtil.formatEzd8ToDisp(etaD, true));
                // END 2023/10/25 G.Quan [QC#61494, MOD]
            }
        }

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).lineBizTpDescTxt_D1, (String) map.get("LINE_BIZ_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqSrcTpDescTxt_D1, (String) map.get("PRCH_REQ_SRC_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).fsrNum_D1, (String) map.get("FSR_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).svcTaskNum_D1, (String) map.get("SVC_TASK_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).svcMachSerNum_D1, (String) map.get("SVC_MACH_SER_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poOrdNum_D1, (String) map.get("PO_ORD_NUM"));
        // QC#21908
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).vndSoNum_D1, (String) map.get("SO_NUM"));
        // Add Start 2020/03/12 QC#56104
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rwsRefNum_D1, (String) map.get("ALT_SO_NUM"));
        // Add End 2020/03/12 QC#56104
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqNum_D2, (String) map.get("PRCH_REQ_NUM2"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndCd_D1, (String) map.get("PRNT_VND_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndNm_D1, (String) map.get("PRNT_VND_NM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).vndCd_D1, (String) map.get("VND_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).locNm_D1, (String) map.get("LOC_NM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipToCustCd_D1, (String) map.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsAcctNm_D1, (String) map.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).techTocCd_D1, (String) map.get("RQST_TECH_TOC_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).techNm_D1, (String) map.get("TECH_NM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).carrNm_D1, (String) map.get("CARR_NM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shpgSvcLvlNm_D1, (String) map.get("SHPG_SVC_LVL_NM"));
        // QC#21913 Delete proNum
        // QC#21887
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpCd_D1, (String) map.get("PRCH_REQ_LINE_TP_CD"));
    }

    /**
     * <pre>
     * PagePrev Event
     * </pre>
     * @param cMsg NPAL1260CMsg
     * @param sMsg NPAL1260SMsg
     */
    private void doProcess_NPAL1260Scrn00_PagePrev(NPAL1260CMsg cMsg, NPAL1260SMsg sMsg) {
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());
        copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * <pre>
     * PageNext Event
     * </pre>
     * @param cMsg NPAL1260CMsg
     * @param sMsg NPAL1260SMsg
     */
    private void doProcess_NPAL1260Scrn00_PageNext(NPAL1260CMsg cMsg, NPAL1260SMsg sMsg) {
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length());
        copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * Pulldown onChange Event
     * @param cMsg NPAL1260CMsg
     * @param sMsg NPAL1260SMsg
     */
    private void doProcess_OnChange_SearchOption(NPAL1260CMsg cMsg, NPAL1260SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_SE)) {
            NPAL1260CommonLogic.callNszc0330forSearchSearchOption(cMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());

        }
    }

    /**
     * <pre>
     * Source WH/SWH Popup Event
     * </pre>
     * @param cMsg NPAL1260CMsg
     * @param sMsg NPAL1260SMsg
     */
    private void doProcess_OpenWin_WhSwh(NPAL1260CMsg cMsg, NPAL1260SMsg sMsg) {
        cMsg.xxPopPrm_P2.setValue(NMXC100001EnableWH.getLocRoleTpForPopup(getGlobalCompanyCode(), BUSINESS_APPLICATION_ID, new String[] {}));
    }

    private void doProcess_NPAL1260Scrn00_OnChange_TechRequestType(NPAL1260CMsg cMsg, NPAL1260SMsg sMsg) {
        // Line Type
        NPAL1260CommonLogic.createPullDownLineType2(cMsg, getGlobalCompanyCode());
    }

    /**
     * <pre>
     * NMAL6010 Event
     * </pre>
     * @param cMsg NPAL1260CMsg
     * @param sMsg NPAL1260SMsg
     */
    private void doProcess_NPAL1260_NMAL6760(NPAL1260CMsg cMsg, NPAL1260SMsg sMsg) {
        // Get Ship To Customer Name
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("shipToCustCd", cMsg.shipToCustCd_P1);
        // Execute
        S21SsmEZDResult result = NPAL1260Query.getInstance().findCustomerName(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            if (searchOptionList.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H1, (String) (searchOptionList.get(0)).get("DS_ACCT_NM"));
            }
        }
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NPAL1260Scrn00_CMN_Download(NPAL1260CMsg cMsg, NPAL1260SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NPAL1260Constant.FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NPAL1260Query.getInstance().getClass());
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), EXTN_CSV);

            String statementId = "search";
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
            ssmParam.put("prchReqRecTpCdRequest", PRCH_REQ_REC_TP.TECH_REQUEST);
            ssmParam.put("prchReqRecTpCdReturn", PRCH_REQ_REC_TP.TECH_RETURN);
            ssmParam.put("prchReqRecTpCdRequisition", PRCH_REQ_REC_TP.PO_REQUISITION);
            ssmParam.put("prchReqNum_H1", cMsg.prchReqNum_H1);
            ssmParam.put("prchReqTpCd_SE", cMsg.prchReqTpCd_SE);
            ssmParam.put("lineBizTpCd_SE", cMsg.lineBizTpCd_SE);
            ssmParam.put("prchReqSrcTpCd_SE", cMsg.prchReqSrcTpCd_SE);
            ssmParam.put("fsrNum_H1", cMsg.fsrNum_H1);
            ssmParam.put("svcTaskNum_H1", cMsg.svcTaskNum_H1);
            ssmParam.put("svcMachSerNum_H1", cMsg.svcMachSerNum_H1);
            ssmParam.put("mdseCd_H1", cMsg.mdseCd_H1);
            ssmParam.put("prchReqLineTpCd_SE", cMsg.prchReqLineTpCd_SE);
            ssmParam.put("prchReqLineStsCd_SE", cMsg.prchReqLineStsCd_SE);
            ssmParam.put("prchReqCratDt_HF", cMsg.prchReqCratDt_HF);
            ssmParam.put("prchReqCratDt_HT", cMsg.prchReqCratDt_HT);
            ssmParam.put("rqstRcvDt_HF", cMsg.rqstRcvDt_HF);
            ssmParam.put("rqstRcvDt_HT", cMsg.rqstRcvDt_HT);
            ssmParam.put("shipDtTmTs_HF", cMsg.shipDt_HF);
            ssmParam.put("shipDtTmTs_HT", cMsg.shipDt_HT);
            ssmParam.put("xxChkBox_H1", cMsg.xxChkBox_H1);
            ssmParam.put("xxChkBox_H2", cMsg.xxChkBox_H2);
            ssmParam.put("poOrdNum_H1", cMsg.poOrdNum_H1);
            // Mod Start 2020/03/17 QC#56104
//            ssmParam.put("soNum_H1", cMsg.soNum_H1);
            ssmParam.put("soNum_H1", cMsg.rwsRefNum_H1);
            // Mod End 2020/03/17 QC#56104
            ssmParam.put("xxChkBox_H3", cMsg.xxChkBox_H3);
            ssmParam.put("xxChkBox_H4", cMsg.xxChkBox_H4);
            ssmParam.put("destRtlWhCd_H1", cMsg.destRtlWhCd_H1);
            ssmParam.put("destRtlSwhCd_H1", cMsg.destRtlSwhCd_H1);
            ssmParam.put("rtlWhCd_H1", cMsg.rtlWhCd_H1);
            ssmParam.put("rtlSwhCd_H1", cMsg.rtlSwhCd_H1);
            ssmParam.put("proNum_H1", cMsg.proNum_H1);
            ssmParam.put("shpgSvcLvlCd_SE", cMsg.shpgSvcLvlCd_SE);
            ssmParam.put("lineSubNumParent", PARENT_LINE_SUB_NUM);
            if (ZYPCommonFunc.hasValue(cMsg.techNm_H1)) {
                cMsg.techNm_H1.setValue(LIKE_SEARCH_CHAR + cMsg.techNm_H1.getValue().replace(LIKE_SEARCH_CHAR, "") + LIKE_SEARCH_CHAR);
            }
            ssmParam.put("techNm_H1", cMsg.techNm_H1);
            if (ZYPCommonFunc.hasValue(cMsg.dsAcctNm_H1)) {
                cMsg.dsAcctNm_H1.setValue(LIKE_SEARCH_CHAR + cMsg.dsAcctNm_H1.getValue().replace(LIKE_SEARCH_CHAR, "") + LIKE_SEARCH_CHAR);
            }
            ssmParam.put("dsAcctNm_H1", cMsg.dsAcctNm_H1);
            if (ZYPCommonFunc.hasValue(cMsg.prntVndNm_H1)) {
                cMsg.prntVndNm_H1.setValue(LIKE_SEARCH_CHAR + cMsg.prntVndNm_H1.getValue().replace(LIKE_SEARCH_CHAR, "") + LIKE_SEARCH_CHAR);
            }
            ssmParam.put("prntVndNm_H1", cMsg.prntVndNm_H1);
            if (ZYPCommonFunc.hasValue(cMsg.locNm_H1)) {
                cMsg.locNm_H1.setValue(LIKE_SEARCH_CHAR + cMsg.locNm_H1.getValue().replace(LIKE_SEARCH_CHAR, "") + LIKE_SEARCH_CHAR);
            }
            ssmParam.put("locNm_H1", cMsg.locNm_H1);
            if (ZYPCommonFunc.hasValue(cMsg.carrNm_H1)) {
                cMsg.carrNm_H1.setValue(LIKE_SEARCH_CHAR + cMsg.carrNm_H1.getValue().replace(LIKE_SEARCH_CHAR, "") + LIKE_SEARCH_CHAR);
            }
            ssmParam.put("carrNm_H1", cMsg.carrNm_H1);
            ssmParam.put("prchReqNum_H2", cMsg.prchReqNum_H2);
            ssmParam.put("rowNum", MAX_DOWNLOAD_CNT);
            ssmParam.put("vndPoAckStsCd", VND_PO_ACK_STS_CD_IB);
            // QC#31201
            // START 2023/10/25 G.Quan [QC#61494, MOD]
//            String constValue = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_POD_UPD_STS_CD, getGlobalCompanyCode());
//            String[] podUpdStss = null;
//            List<String> podUpdStsList = null;
//            if (constValue != null) {
//                podUpdStss = constValue.split(",");
//            }
//            if (podUpdStss != null) {
//                podUpdStsList = Arrays.asList(podUpdStss);
//                ssmParam.put("podUpdStsList", podUpdStsList);
//            }
            String constValue = ZYPCodeDataUtil.getVarCharConstValue(NPAB1650_NPAL1260_TARGET_POD, getGlobalCompanyCode());
            String[] ediStsCd = null;
            List<String> ediStsCdList = null;
            if (constValue != null) {
                ediStsCd = constValue.split(",");
            }
            if (ediStsCd != null) {
                ediStsCdList = Arrays.asList(ediStsCd);
                ssmParam.put("ediStsCdList", ediStsCdList);
            }
            // END 2023/10/25 G.Quan [QC#61494, MOD]
            constValue = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_POD_ARV_STS_CD, getGlobalCompanyCode());
            String[] podArvStss = null;
            List<String> podArvStsList = null;
            if (constValue != null) {
                podArvStss = constValue.split(",");
            }
            if (podArvStss != null) {
                podArvStsList = Arrays.asList(podArvStss);
                ssmParam.put("podArvStsList", podArvStsList);
            }
            // QC#31207
            List<String> ackStsList = Arrays.asList(new String[]{VND_PO_ACK_STS.ITEM_BACKORDERED});
            ssmParam.put("ackStsList", ackStsList);
            BigDecimal ltDays = ZYPCodeDataUtil.getNumConstValue("PO_ACK_LT_DAY", getGlobalCompanyCode());
            if (!ZYPCommonFunc.hasValue(ltDays)) {
                ltDays = BigDecimal.ZERO;
            }
            ssmParam.put("days", ltDays);
            // START 2023/10/25 G.Quan [QC#61494, ADD]
            ssmParam.put("prchReqTpCdPremiumRush", PRCH_REQ_TP.PREMIUM_RUSH);
            ssmParam.put("prchReqTpCdRush", PRCH_REQ_TP.RUSH);
            ssmParam.put("premiumRushConditionTplCarrNm", PREMIUM_RUSH_CONDITION_TPL_CARR_NM);
            List<String> rushConditionTplCarrNmList =
                Arrays.asList(new String[]{RUSH_CONDITION_TPL_CARR_NM_FEDEX, RUSH_CONDITION_TPL_CARR_NM_UPS});
            ssmParam.put("rushConditionTplCarrNmList", rushConditionTplCarrNmList);
            // END 2023/10/25 G.Quan [QC#61494, ADD]

            stmtSelect = ssmLLClient.createPreparedStatement(statementId, ssmParam, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                cMsg.setMessageInfo(ZZZM9001E, null);
                return;
            }
            NPAL1260CommonLogic.writeCsvFileInfo(cMsg, rs, getGlobalCompanyCode());
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

    }

    /**
     * <pre>
     * copyFromSmsgOntoCmsg
     * Copy data From Smsg Onto Cmsg
     * </pre>
     * @param cMsg NPAL1260CMsg
     * @param sMsg NPAL1260SMsg
     */
    private void copyFromSMsgOntoCmsg(NPAL1260CMsg cMsg, NPAL1260SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        setPagePos(cMsg, sMsg);

        if (0 < sMsg.A.getValidCount()) {
            int cMsgCount = 0;
            for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1; i < cMsg.xxPageShowToNum.getValueInt(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cMsgCount), null);
                cMsgCount++;
            }
            cMsg.A.setValidCount(cMsgCount);
        }
    }

    /**
     * <pre>
     * Set page pos.
     * </pre>
     * @param cMsg NPAL1260CMsg
     * @param sMsg NPAL1260SMsg
     */
    private void setPagePos(NPAL1260CMsg cMsg, NPAL1260SMsg sMsg) {
        if ((cMsg.A.length() == 0) || (sMsg.A.length() == 0) || (sMsg.A.getValidCount() <= 0)) {
            cMsg.xxPageShowFromNum.setValue(0);
            cMsg.xxPageShowToNum.setValue(0);
            cMsg.xxPageShowOfNum.setValue(0);
            return;
        }
        int startRowCount = 0;
        if (ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
            startRowCount = cMsg.xxPageShowFromNum.getValueInt();
        }
        int allRowCount = sMsg.A.getValidCount();
        if (startRowCount == 0) {
            cMsg.xxPageShowFromNum.setValue(1);
        } else if ((startRowCount < 0) || ((allRowCount <= startRowCount))) {
            // last page
            cMsg.xxPageShowFromNum.setValue(getLastPageStartCount(allRowCount, cMsg.A.length()));
        } else {
            if ((startRowCount % cMsg.A.length()) != 1) {
                startRowCount = startRowCount - (startRowCount % cMsg.A.length()) + 1;
            }
            cMsg.xxPageShowFromNum.setValue(startRowCount);
        }
        if ((cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1) < allRowCount) {
            cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1);
        } else {
            cMsg.xxPageShowToNum.setValue(allRowCount);
        }
        cMsg.xxPageShowOfNum.setValue(allRowCount);
    }

    private int getLastPageStartCount(int allRowCount, int pageRowCount) {
        if ((allRowCount <= 0) || (pageRowCount <= 0)) {
            return 0;
        }
        if (allRowCount <= pageRowCount) {
            return 1;
        }
        if (allRowCount % pageRowCount == 0) {
            return allRowCount - pageRowCount + 1;
        }
        return ((int) (allRowCount / pageRowCount)) * pageRowCount + 1;
    }
}
