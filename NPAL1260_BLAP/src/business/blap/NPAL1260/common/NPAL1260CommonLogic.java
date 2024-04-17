package business.blap.NPAL1260.common;


import static business.blap.NPAL1260.constant.NPAL1260Constant.BUSINESS_APPLICATION_ID;
import static business.blap.NPAL1260.constant.NPAL1260Constant.CSV_HEADER_INFO;
import static business.blap.NPAL1260.constant.NPAL1260Constant.DATE_STR_LENGTH;
import static business.blap.NPAL1260.constant.NPAL1260Constant.HALF_DAY_HOURS;
import static business.blap.NPAL1260.constant.NPAL1260Constant.HOUR_MINUTE_STR_LENGTH;
import static business.blap.NPAL1260.constant.NPAL1260Constant.MAX_DOWNLOAD_CNT;
import static business.blap.NPAL1260.constant.NPAL1260Constant.NZZM0007E;
import static business.blap.NPAL1260.constant.NPAL1260Constant.TIME_AM;
import static business.blap.NPAL1260.constant.NPAL1260Constant.TIME_FORMAT_YYYYMMDDHHMMSS;
import static business.blap.NPAL1260.constant.NPAL1260Constant.TIME_PM;
import static business.blap.NPAL1260.constant.NPAL1260Constant.TIME_STR_LENGTH;
import static business.blap.NPAL1260.constant.NPAL1260Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1260.NPAL1260CMsg;
import business.blap.NPAL1260.NPAL1260Query;
import business.blap.NPAL1260.NPAL1260SMsg;
import business.db.RTL_WHTMsg;
import business.file.NPAL1260F00FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeData;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeException;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request Inquiry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/02/2015   CITS       Yasushi Nomura        Create          N/A
 * 12/19/2016   CITS       K.Ogino               Update          QC#15825
 * 10/23/2017   CITS       T.Wada                Update          QC#21882
 * 12/26/2017   CITS       K.Ogino               Update          QC#21908,QC#21887
 * 02/07/2018   CITS       K.Ogino               Update          QC#18922
 * 2018/08/17   CITS       K.Ogino               Update          QC#27601
 * 03/12/2020   Fujitsu    R.Nakamura            Update          QC#56104
 * 2023/10/25   Hitachi    G.Quan                Update          QC#61494
 *</pre>
 */
public class NPAL1260CommonLogic {

    /**
     * Create Pulldown Search Option
     * @param cMsg NPAL1260CMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownSearchOption(NPAL1260CMsg cMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        cMsg.srchOptPk_CD.clear();
        cMsg.srchOptNm_DI.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("srchOptAplId", BUSINESS_APPLICATION_ID);
        ssmParam.put("srchOptUsrId", cMsg.srchOptUsrId_U1.getValue());

        // Execute
        S21SsmEZDResult result = NPAL1260Query.getInstance().getSearchOptionPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_CD.no(i), (BigDecimal) recode.get("SRCH_OPT_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_DI.no(i), (String) recode.get("SRCH_OPT_NM"));

                if (i >= cMsg.srchOptPk_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown RequisitionType
     * @param cMsg NPAL1260CMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownRequisitionType(NPAL1260CMsg cMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        cMsg.prchReqTpCd_CD.clear();
        cMsg.prchReqTpDescTxt_DI.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prchReqRecTpCdRequest", PRCH_REQ_REC_TP.TECH_REQUEST);
        ssmParam.put("prchReqRecTpCdReturn", PRCH_REQ_REC_TP.TECH_RETURN);
        // Execute
        S21SsmEZDResult result = NPAL1260Query.getInstance().getRequisitionTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_CD.no(i), (String) recode.get("PRCH_REQ_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpDescTxt_DI.no(i), (String) recode.get("PRCH_REQ_TP_DESC_TXT"));

                if (i >= cMsg.prchReqTpCd_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown LineStatus
     * @param cMsg NPAL1260CMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownLineStatus(NPAL1260CMsg cMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        cMsg.prchReqLineStsCd_CD.clear();
        cMsg.prchReqLineStsDescTxt_DI.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prchReqRecTpCdRequest", PRCH_REQ_REC_TP.TECH_REQUEST);
        ssmParam.put("prchReqRecTpCdReturn", PRCH_REQ_REC_TP.TECH_RETURN);
        ssmParam.put("scrEntAvalFlg", ZYPConstant.FLG_ON_Y);
        // Execute
        S21SsmEZDResult result = NPAL1260Query.getInstance().getLineStatusPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineStsCd_CD.no(i), (String) recode.get("PRCH_REQ_LINE_STS_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineStsDescTxt_DI.no(i), (String) recode.get("PRCH_REQ_LINE_STS_DESC_TXT"));

                if (i >= cMsg.prchReqLineStsCd_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown LineType
     * @param cMsg NPAL1260CMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownLineType(NPAL1260CMsg cMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        cMsg.prchReqLineTpCd_CD.clear();
        cMsg.prchReqLineTpDescTxt_DI.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prchReqRecTpCdRequest", PRCH_REQ_REC_TP.TECH_REQUEST);
        ssmParam.put("prchReqRecTpCdReturn", PRCH_REQ_REC_TP.TECH_RETURN);
        // QC#21882
        //ssmParam.put("scrEntAvalFlg", ZYPConstant.FLG_ON_Y);
        // Execute
        S21SsmEZDResult result = NPAL1260Query.getInstance().getLineTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineTpCd_CD.no(i), (String) recode.get("PRCH_REQ_LINE_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineTpDescTxt_DI.no(i), (String) recode.get("PRCH_REQ_LINE_TP_DESC_TXT"));

                if (i >= cMsg.prchReqLineTpCd_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown LineType
     * @param cMsg NPAL1260CMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownLineType2(NPAL1260CMsg cMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        cMsg.prchReqLineTpCd_CD.clear();
        cMsg.prchReqLineTpDescTxt_DI.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prchReqTpCd", cMsg.prchReqTpCd_SE);
        // Execute
        S21SsmEZDResult result = NPAL1260Query.getInstance().getLineTypePulldownList2(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                if (PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(cMsg.prchReqTpCd_SE.getValue())) {
                    if (((String) recode.get("PRCH_REQ_LINE_TP_CD")).equals(PRCH_REQ_LINE_TP.LOCAL_RETURN)) {
                        ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineTpCd_CD.no(i), (String) recode.get("PRCH_REQ_LINE_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineTpDescTxt_DI.no(i), (String) recode.get("PRCH_REQ_LINE_TP_DESC_TXT"));
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineTpCd_CD.no(i), (String) recode.get("PRCH_REQ_LINE_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineTpDescTxt_DI.no(i), (String) recode.get("PRCH_REQ_LINE_TP_DESC_TXT"));
                }

                if (i >= cMsg.prchReqLineTpCd_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown TechTerritory
     * @param cMsg NPAL1260CMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownTechTerritory(NPAL1260CMsg cMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        cMsg.lineBizTpCd_CD.clear();
        cMsg.lineBizTpDescTxt_DI.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("otherResrcTrtyFlg", ZYPConstant.FLG_ON_Y);
        // Execute
        S21SsmEZDResult result = NPAL1260Query.getInstance().getTechTerritoryPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.lineBizTpCd_CD.no(i), (String) recode.get("LINE_BIZ_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.lineBizTpDescTxt_DI.no(i), (String) recode.get("LINE_BIZ_TP_DESC_TXT"));

                if (i >= cMsg.lineBizTpCd_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown Document Source Type
     * @param cMsg NPAL1260CMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownDocumentSourceType(NPAL1260CMsg cMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        cMsg.prchReqSrcTpCd_CD.clear();
        cMsg.prchReqSrcTpDescTxt_DI.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prchReqRecTpCd", PRCH_REQ_REC_TP.TECH_REQUEST);
        // Execute
        S21SsmEZDResult result = NPAL1260Query.getInstance().getDocumentSourceTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpCd_CD.no(i), (String) recode.get("PRCH_REQ_SRC_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpDescTxt_DI.no(i), (String) recode.get("PRCH_REQ_SRC_TP_DESC_TXT"));

                if (i >= cMsg.prchReqSrcTpCd_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown Req Service Level
     * @param cMsg NPAL1260CMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownRequestedShipMethod(NPAL1260CMsg cMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        cMsg.shpgSvcLvlCd_CD.clear();
        cMsg.shpgSvcLvlDescTxt_DI.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // Execute
        S21SsmEZDResult result = NPAL1260Query.getInstance().getRequestedShipMethodPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_CD.no(i), (String) recode.get("SHPG_SVC_LVL_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlDescTxt_DI.no(i), (String) recode.get("SHPG_SVC_LVL_NM"));

                if (i >= cMsg.shpgSvcLvlCd_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Execute API NSZC033001
     * @param bizMsg NPAL1260CMsg
     * @param pMsg NSZC033001PMsg
     * @return
     */
    private static boolean executeNszc0330(NPAL1260CMsg cMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    cMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        cMsg.srchOptPk_SE.setErrorInfo(1, msgId);
                        cMsg.srchOptNm_TX.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Call API NSZC033001 (Delete SearchOption)
     * @param cMsg NPAL1260CMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearchOption(NPAL1260CMsg cMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_APPLICATION_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (executeNszc0330(cMsg, pMsg)) {
            cMsg.srchOptUsrId_U1.setValue(usrId);
            createPullDownSearchOption(cMsg, glblCmpyCd);
            cMsg.srchOptNm_TX.clear();
            cMsg.srchOptPk_SE.clear();
            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Delete Search" });
        }
    }

    /**
     * Call API NSZC033001 (Search SearchOption)
     * @param cMsg NPAL1260CMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchSearchOption(NPAL1260CMsg cMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_APPLICATION_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (!executeNszc0330(cMsg, pMsg)) {
            return; // Error
        }

        // Set Result
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_TX, pMsg.srchOptNm);

        ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum_H1, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_SE, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd_H1, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineTpCd_SE, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineStsCd_SE, pMsg.srchOptTxt_05);
        cMsg.prchReqCratDt_HF.setValue(pMsg.srchOptTxt_06.getValue());
        cMsg.prchReqCratDt_HT.setValue(pMsg.srchOptTxt_07.getValue());
        cMsg.rqstRcvDt_HF.setValue(pMsg.srchOptTxt_08.getValue());
        cMsg.rqstRcvDt_HT.setValue(pMsg.srchOptTxt_09.getValue());
        cMsg.shipDt_HF.setValue(pMsg.srchOptTxt_10.getValue());
        cMsg.shipDt_HT.setValue(pMsg.srchOptTxt_11.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.lineBizTpCd_SE, pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpCd_SE, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(cMsg.fsrNum_H1, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(cMsg.svcTaskNum_H1, pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(cMsg.svcMachSerNum_H1, pMsg.srchOptTxt_16);
        ZYPEZDItemValueSetter.setValue(cMsg.poOrdNum_H1, pMsg.srchOptTxt_17);
        // Mod Start 2020/03/17 QC#56104
//        ZYPEZDItemValueSetter.setValue(cMsg.soNum_H1, pMsg.srchOptTxt_18);
        ZYPEZDItemValueSetter.setValue(cMsg.rwsRefNum_H1, pMsg.srchOptTxt_18);
        // Mod End 2020/03/17 QC#56104
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum_H2, pMsg.srchOptTxt_19);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_H1, pMsg.srchOptTxt_20);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd_H1, pMsg.srchOptTxt_21);
        ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm_H1, pMsg.srchOptTxt_22);
        ZYPEZDItemValueSetter.setValue(cMsg.locNm_H1, pMsg.srchOptTxt_23);
        ZYPEZDItemValueSetter.setValue(cMsg.destRtlWhCd_H1, pMsg.srchOptTxt_24);
        ZYPEZDItemValueSetter.setValue(cMsg.destRtlSwhCd_H1, pMsg.srchOptTxt_25);
        ZYPEZDItemValueSetter.setValue(cMsg.techNm_H1, pMsg.srchOptTxt_26);
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H1, pMsg.srchOptTxt_27);
        ZYPEZDItemValueSetter.setValue(cMsg.carrNm_H1, pMsg.srchOptTxt_28);
        ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SE, pMsg.srchOptTxt_29);
        ZYPEZDItemValueSetter.setValue(cMsg.proNum_H1, pMsg.srchOptTxt_30);
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_H1, pMsg.srchOptTxt_31);
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_H2, pMsg.srchOptTxt_32);
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_H3, pMsg.srchOptTxt_33);
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_H4, pMsg.srchOptTxt_34);
    }

    /**
     * Call API NSZC033001 (Save SearchOption)
     * @param cMsg NPAL1260CMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearchOption(NPAL1260CMsg cMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX) || isSameSaveSearchName(cMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SE);
        }
        if (ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_TX);
        } else {
            setSelectSaveSearchName(pMsg, cMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_APPLICATION_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.prchReqNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.prchReqTpCd_SE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.prchReqLineTpCd_SE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.prchReqLineStsCd_SE);
        pMsg.srchOptTxt_06.setValue(cMsg.prchReqCratDt_HF.getValue());
        pMsg.srchOptTxt_07.setValue(cMsg.prchReqCratDt_HT.getValue());
        pMsg.srchOptTxt_08.setValue(cMsg.rqstRcvDt_HF.getValue());
        pMsg.srchOptTxt_09.setValue(cMsg.rqstRcvDt_HT.getValue());
        pMsg.srchOptTxt_10.setValue(cMsg.shipDt_HF.getValue());
        pMsg.srchOptTxt_11.setValue(cMsg.shipDt_HT.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, cMsg.lineBizTpCd_SE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.prchReqSrcTpCd_SE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.fsrNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.svcTaskNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.svcMachSerNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, cMsg.poOrdNum_H1);
        // Mod Start 2020/03/17 QC#56104
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, cMsg.soNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, cMsg.rwsRefNum_H1);
        // Mod End 2020/03/17 QC#56104
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, cMsg.prchReqNum_H2);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, cMsg.rtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, cMsg.rtlSwhCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, cMsg.prntVndNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, cMsg.locNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, cMsg.destRtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_25, cMsg.destRtlSwhCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_26, cMsg.techNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_27, cMsg.dsAcctNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_28, cMsg.carrNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_29, cMsg.shpgSvcLvlCd_SE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_30, cMsg.proNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_31, cMsg.xxChkBox_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_32, cMsg.xxChkBox_H2);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_33, cMsg.xxChkBox_H3);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_34, cMsg.xxChkBox_H4);

        if (executeNszc0330(cMsg, pMsg)) {
            cMsg.srchOptUsrId_U1.setValue(usrId);
            createPullDownSearchOption(cMsg, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_SE, pMsg.srchOptPk);
            // Message ; The process [@] has been successfully
            // completed.
            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Save Search" });
        }
    }

    /**
     * check duplicate search name
     * @param cMsg NPAL1260CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NPAL1260CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_DI.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_DI.no(i))) {
                return false;
            }
            if (cMsg.srchOptNm_TX.getValue().equals(cMsg.srchOptNm_DI.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_SE) //
                        && cMsg.srchOptPk_SE.getValue().compareTo(cMsg.srchOptPk_CD.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * isSameSaveSearchName
     * @param cMsg NPAL1260CMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NPAL1260CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SE)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_DI.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_DI.no(i))) {
                return false;
            }
            if (cMsg.srchOptPk_SE.getValue().compareTo(cMsg.srchOptPk_CD.no(i).getValue()) == 0) {
                if (cMsg.srchOptNm_TX.getValue().equals(cMsg.srchOptNm_DI.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param cMsg NPAL1260CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NPAL1260CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SE)) {
            return;
        }

        for (int i = 0; i < cMsg.srchOptNm_DI.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_DI.no(i))) {
                return;
            }
            if (cMsg.srchOptPk_SE.getValue().compareTo(cMsg.srchOptPk_CD.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_DI.no(i));
            }
        }
        return;
    }

    /**
     * getLocation
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return Map<String, Object>
     */
    public static Map<String, Object> getLocation(String glblCmpyCd, String rtlWhCd) {

        if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
            return null;
        }

        RTL_WHTMsg tMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rtlWhCd);
        tMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.ctryCd) && ZYPCommonFunc.hasValue(tMsg.postCd)) {
            Map<String, Object> rslt = new HashMap<String, Object>();
            rslt.put("POST_CD", tMsg.postCd.getValue());
            rslt.put("CTRY_CD", tMsg.ctryCd.getValue());
            // Get Location Info!
            return rslt;
        }

        return null;
    }

    /**
     * set page pos
     * @param cMsg NPAL1260CMsg
     * @param sMsg NPAL1260SMsg
     */
    public static void setPagePos(NPAL1260CMsg cMsg, NPAL1260SMsg sMsg) {
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

    private static int getLastPageStartCount(int allRowCount, int pageRowCount) {
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

    /**
     * convert system time to local time
     * @param date String
     * @param time String
     * @param lclTZId String
     * @return local time
     */
    public static ZYPLocalTimeData convertTimeSys2Lcl(String date, String time, String lclTZId) {
        if ((!ZYPCommonFunc.hasValue(date)) || (!ZYPCommonFunc.hasValue(lclTZId))) {
            return null;
        }
        if (time.length() == 3) {
            time = "0" + time + "00";
        } else if (time.length() == 4) {
            time = time + "00";
        }
        date = date + time;
        if (date.length() != 14) {
            return null;
        }
        return ZYPLocalTimeUtil.convertTimeSys2Lcl(lclTZId, date, TIME_FORMAT_YYYYMMDDHHMMSS);
    }

    /**
     * get int time from string
     * @param time String
     * @return int array
     */
    public static int[] getTimeIntValue(String time) {
        if (time == null) {
            return null;
        }
        String sHh, sMm;
        if (0 <= time.indexOf(":")) {
            String[] temp = time.split(":");
            if (temp.length != 2) {
                return null;
            }
            sHh = temp[0];
            sMm = temp[1];
        } else {
            if ((time.length() != 3) && (time.length() != 4) && (time.length() != 6)) {
                return null;
            }
            if (time.length() == 3) {
                time = "0" + time;
            } else if (time.length() == 6) {
                time = time.substring(0, 4);
            }
            sHh = time.substring(0, 2);
            sMm = time.substring(2, 4);
        }
        int iHh = -1;
        int iMm = -1;
        try {
            iHh = Integer.valueOf(sHh);
        } catch (NumberFormatException e) {
            return null;
        }
        try {
            iMm = Integer.valueOf(sMm);
        } catch (NumberFormatException e) {
            return null;
        }
        if ((iHh < 0) || (23 < iHh)) {
            return null;
        }
        if ((iMm < 0) || (59 < iMm)) {
            return null;
        }
        return new int[] {iHh, iMm };
    }

    /**
     * convert time to view text
     * @param date ZYPLocalTimeData
     * @param timeZoneFlg boolean
     * @return view text
     */
    public static String convertTime2ViewText(ZYPLocalTimeData date, boolean timeZoneFlg) {
        String d = ZYPDateUtil.formatEzd8ToDisp(date.getTime().substring(0, DATE_STR_LENGTH), true);
        int[] temp = getTimeIntValue(date.getTime().substring(DATE_STR_LENGTH, DATE_STR_LENGTH + HOUR_MINUTE_STR_LENGTH));
        if (temp == null) {
            return d;
        }
        String tempString;
        if (HALF_DAY_HOURS <= temp[0]) {
            tempString = String.format("%s %02d:%02d %s", d, (temp[0] - HALF_DAY_HOURS), temp[1], TIME_PM);
        } else {
            tempString = String.format("%s %02d:%02d %s", d, temp[0], temp[1], TIME_AM);
        }
        if (timeZoneFlg) {
            return String.format("%s %s", tempString, date.getTZDspName());
        }
        return tempString;
    }

    /**
     * <pre>
     * Write csv file Contract Info
     * @param bizMsg NPAL1260CMsg
     * @param rs     ResultSet
     * @param glblCmpyCd String
     * @throws SQLException 
     * </pre>
     */
    public static void writeCsvFileInfo(NPAL1260CMsg bizMsg, ResultSet rs, String glblCmpyCd) throws SQLException {
        Map<String, String> techTZIdMap = new HashMap<String, String>();

        NPAL1260F00FMsg fMsg = new NPAL1260F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
        csvOutFile.writeHeader(CSV_HEADER_INFO, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

        // QC#21887
        fMsg.addExclusionItem("prchReqLineTpCd_D1");

        do {
            fMsg.clear();
            if (rs.getRow() > MAX_DOWNLOAD_CNT) {
                bizMsg.setMessageInfo(NZZM0007E, null);
                break;
            }

            // QC#15825
            String rtlWhCd = rs.getString("DEST_RTL_WH_CD");
            String lclTZId = null;
            if (ZYPCommonFunc.hasValue(rtlWhCd)) {
                if (techTZIdMap.containsKey(rtlWhCd)) {
                    lclTZId = techTZIdMap.get(rtlWhCd);
                } else {
                    String postCd = null;
                    String ctryCd = null;
                    Map<String, Object> locInfo =  getLocation(glblCmpyCd, rtlWhCd);
                    if (locInfo != null) {
                        postCd = (String) locInfo.get("POST_CD");
                        ctryCd = (String) locInfo.get("CTRY_CD");
                    }

                    if (ZYPCommonFunc.hasValue(ctryCd) && ZYPCommonFunc.hasValue(postCd)) {
                        try {
                            // QC#18922
                            postCd = subStrPostCd(postCd);
                            lclTZId = ZYPLocalTimeUtil.getTZId(ctryCd, postCd);
                        } catch (ZYPLocalTimeException e) {
                            lclTZId = null;
                        }
                    } else {
                        S21InfoLogOutput.println("Address information of the technician is not registered. TECH_TOC_CD = " + rs.getString("RQST_TECH_TOC_CD"));
                    }

                    techTZIdMap.put(rtlWhCd, lclTZId);
                }
            }

            ZYPEZDItemValueSetter.setValue(fMsg.prchReqNum_D1, rs.getString("PRCH_REQ_NUM"));
            String prchReqLineNum = rs.getString("PRCH_REQ_LINE_NUM");
            BigDecimal prchReqLineSubNum = rs.getBigDecimal("PRCH_REQ_LINE_SUB_NUM");
            if (ZYPCommonFunc.hasValue(prchReqLineSubNum)) {
                prchReqLineNum += "." + prchReqLineSubNum.toString();
            }
            fMsg.xxPopPrm_N1.setValue(prchReqLineNum);
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqTpDescTxt_D1, rs.getString("PRCH_REQ_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqLineTpDescTxt_D1, rs.getString("PRCH_REQ_LINE_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_D1, rs.getString("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_D1, rs.getString("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.procrTpDescTxt_D1, rs.getString("PROCR_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.srcRtlWhCd_D1, rs.getString("SRC_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_D1, rs.getString("RTL_WH_NM1"));
            ZYPEZDItemValueSetter.setValue(fMsg.srcRtlSwhCd_D1, rs.getString("SRC_RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhNm_D1, rs.getString("RTL_SWH_NM1"));
            ZYPEZDItemValueSetter.setValue(fMsg.destRtlWhCd_D1, rs.getString("DEST_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_D2, rs.getString("RTL_WH_NM2"));
            ZYPEZDItemValueSetter.setValue(fMsg.destRtlSwhCd_D1, rs.getString("DEST_RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhNm_D2, rs.getString("RTL_SWH_NM2"));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqLineStsDescTxt_D1, rs.getString("PRCH_REQ_LINE_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.ordQty_D2, rs.getBigDecimal("BO_QTY"));
            // QC#11142
            if (BigDecimal.ZERO.compareTo(rs.getBigDecimal("PRCH_REQ_LINE_SUB_NUM")) == 0) {
                // Parent
                fMsg.ordQty_D1.clear();
                ZYPEZDItemValueSetter.setValue(fMsg.prchReqDispQty_D1, rs.getBigDecimal("PRCH_REQ_QTY"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipQty_D1, rs.getBigDecimal("SUM_SHIP_QTY"));
                ZYPEZDItemValueSetter.setValue(fMsg.rwsPutAwayQty_D1, rs.getBigDecimal("SUM_RWS_PUT_AWAY_QTY"));

            } else {
                // Child
                fMsg.prchReqDispQty_D1.clear();
                ZYPEZDItemValueSetter.setValue(fMsg.ordQty_D1, rs.getBigDecimal("ORD_QTY"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipQty_D1, rs.getBigDecimal("SHIP_QTY"));
                ZYPEZDItemValueSetter.setValue(fMsg.rwsPutAwayQty_D1, rs.getBigDecimal("RWS_PUT_AWAY_QTY"));

            }
            // Freeze Flag
            if (ZYPConstant.FLG_ON_Y.equals(rs.getString("PRCH_REQ_FRZ_FLG"))) {
                fMsg.prchReqFrzFlg_D1.setValue(ZYPConstant.FLG_ON_Y);
            }
            // Insourced Flag
            if (ZYPConstant.FLG_ON_Y.equals(rs.getString("INSRC_FLG"))) {
                fMsg.insrcFlg_D1.setValue(ZYPConstant.FLG_ON_Y);
            }
            // Purchased Flag
            if (ZYPConstant.FLG_ON_Y.equals(rs.getString("PO_CRAT_FLG"))) {
                fMsg.poCratFlg_D1.setValue(ZYPConstant.FLG_ON_Y);
            }
            // CancelledFlag
            BigDecimal cancelQty = rs.getBigDecimal("PRCH_REQ_CANC_QTY");
            if ((cancelQty != null) && (0 < cancelQty.intValue())) {
                fMsg.znFlg_D1.setValue(ZYPConstant.FLG_ON_Y);
            }
            String origPrchReqLineNum = rs.getString("ORIG_PRCH_REQ_LINE_NUM");
            BigDecimal origPrchReqLineSubNum = rs.getBigDecimal("ORIG_PRCH_REQ_LINE_SUB_NUM");
            if (ZYPCommonFunc.hasValue(origPrchReqLineSubNum)) {
                origPrchReqLineNum += "." + origPrchReqLineSubNum.toString();
            }
            if (origPrchReqLineNum != null) {
                fMsg.xxPopPrm_N2.setValue(origPrchReqLineNum);
            }
            // Date Created
            String prchReqCratDt = rs.getString("PRCH_REQ_CRAT_DT");
            String prchReqCratTm = rs.getString("PRCH_REQ_CRAT_TM");
            if ((prchReqCratDt != null) && (prchReqCratTm != null)) {
                ZYPLocalTimeData lcl = convertTimeSys2Lcl(prchReqCratDt, prchReqCratTm, lclTZId);
                if (lcl != null) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D1, convertTime2ViewText(lcl, true));
                } else {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D1, ZYPDateUtil.formatEzd8ToDisp(prchReqCratDt, true));
                }
            } else if (prchReqCratDt != null) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D1, ZYPDateUtil.formatEzd8ToDisp(prchReqCratDt, true));
            }
            // Date Needed
            String rqstRcvDt = rs.getString("RQST_RCV_DT");
            String rqstRcvTm = rs.getString("RQST_RCV_TM");
            if ((rqstRcvDt != null) && (rqstRcvTm != null)) {
                ZYPLocalTimeData lcl = NPAL1260CommonLogic.convertTimeSys2Lcl(rqstRcvDt, rqstRcvTm, lclTZId);
                if (lcl != null) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D2, NPAL1260CommonLogic.convertTime2ViewText(lcl, true));
                } else {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D2, ZYPDateUtil.formatEzd8ToDisp(rqstRcvDt, true));
                }
            } else if (rqstRcvDt != null) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D2, ZYPDateUtil.formatEzd8ToDisp(rqstRcvDt, true));
            }
            // Ship Date
            String shipDate = rs.getString("SHIP_DT_TM_TS");
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
                        ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D3, NPAL1260CommonLogic.convertTime2ViewText(lcl, true));
                    } else {
                        ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D3, ZYPDateUtil.formatEzd8ToDisp(shipD, true));
                    }
                } else if (shipD != null) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D3, ZYPDateUtil.formatEzd8ToDisp(shipD, true));
                }
            }
            // Date Received
            String rwsDate = rs.getString("RWS_CLO_DT_TM_TS");
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
                        ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D4, NPAL1260CommonLogic.convertTime2ViewText(lcl, true));
                    } else {
                        ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D4, ZYPDateUtil.formatEzd8ToDisp(rwsD, true));
                    }
                } else if (rwsD != null) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D4, ZYPDateUtil.formatEzd8ToDisp(rwsD, true));
                }
            }
            // Ack Date B/O
            String ackDate = rs.getString("ORD_LAST_UPD_TS");
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
                        ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D5, NPAL1260CommonLogic.convertTime2ViewText(lcl, true));
                    } else {
                        ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D5, ZYPDateUtil.formatEzd8ToDisp(ackD, true));
                    }
                } else if (ackD != null) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D5, ZYPDateUtil.formatEzd8ToDisp(ackD, true));
                }
            }

            // START 2023/10/25 G.Quan [QC#61494, ADD]
            // Delivery Date
            String delyDate = (String) rs.getString("DELY_DT");
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
                        ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D6, NPAL1260CommonLogic.convertTime2ViewText(lcl, true));
                    } else {
                        ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D6, ZYPDateUtil.formatEzd8ToDisp(delyD, true));
                    }
                } else if (delyD != null) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D6, ZYPDateUtil.formatEzd8ToDisp(delyD, true));
                }
            }
            // END 2023/10/25 G.Quan [QC#61494, ADD]
            // ETA Date Add QC#21887. Mod QC#27601
            String etaDate = (String) rs.getString("ETA_DT");
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
//                        ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D6, NPAL1260CommonLogic.convertTime2ViewText(lcl, true));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D7, NPAL1260CommonLogic.convertTime2ViewText(lcl, true));
                        // END 2023/10/25 G.Quan [QC#61494, MOD]
                    } else {
                        // START 2023/10/25 G.Quan [QC#61494, MOD]
//                        ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D6, ZYPDateUtil.formatEzd8ToDisp(etaD, true));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D7, ZYPDateUtil.formatEzd8ToDisp(etaD, true));
                        // END 2023/10/25 G.Quan [QC#61494, MOD]
                    }
                } else if (etaD != null) {
                    // START 2023/10/25 G.Quan [QC#61494, MOD]
//                    ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D6, ZYPDateUtil.formatEzd8ToDisp(etaD, true));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D7, ZYPDateUtil.formatEzd8ToDisp(etaD, true));
                    // END 2023/10/25 G.Quan [QC#61494, MOD]
                }
            }

            ZYPEZDItemValueSetter.setValue(fMsg.lineBizTpDescTxt_D1, rs.getString("LINE_BIZ_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqSrcTpDescTxt_D1, rs.getString("PRCH_REQ_SRC_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.fsrNum_D1, rs.getString("FSR_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcTaskNum_D1, rs.getString("SVC_TASK_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcMachSerNum_D1, rs.getString("SVC_MACH_SER_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.poOrdNum_D1, rs.getString("PO_ORD_NUM"));
            // QC#21908
            ZYPEZDItemValueSetter.setValue(fMsg.vndSoNum_D1, rs.getString("SO_NUM"));
            // Add Start 2020/03/12 QC#56104
            ZYPEZDItemValueSetter.setValue(fMsg.rwsRefNum_D1, rs.getString("ALT_SO_NUM"));
            // Add End 2020/03/12 QC#56104
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqNum_D2, rs.getString("PRCH_REQ_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.prntVndCd_D1, rs.getString("PRNT_VND_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.prntVndNm_D1, rs.getString("PRNT_VND_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.vndCd_D1, rs.getString("VND_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.locNm_D1, rs.getString("LOC_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.shipToCustCd_D1, rs.getString("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_D1, rs.getString("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.techTocCd_D1, rs.getString("RQST_TECH_TOC_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.techNm_D1, rs.getString("TECH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.carrNm_D1, rs.getString("CARR_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.shpgSvcLvlNm_D1, rs.getString("SHPG_SVC_LVL_NM"));

            // QC#21913 Modify. Search proNum
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("rwsRefNum", fMsg.vndSoNum_D1.getValue());
            S21SsmEZDResult result = NPAL1260Query.getInstance().getProNum(ssmParam);
            List<Map<String, Object>> proNumList = (List<Map<String, Object>>) result.getResultObject();

            boolean isCsvWrite = false;
            if (result.isCodeNormal()) {
                for (int i = 0; i < proNumList.size(); i++) {
                    Map<String, Object> m = proNumList.get(i);
                    ZYPEZDItemValueSetter.setValue(fMsg.proNum_D1, (String) m.get("PRO_NUM"));
                    csvOutFile.write();
                    isCsvWrite = true;
                }
            }

            if (!isCsvWrite) {
                csvOutFile.write();
            }
        } while (rs.next());
        csvOutFile.close();
    }

    /**
     * QC#18922
     * subStrPostCd
     * @param postCd String
     * @return String
     */
    public static String subStrPostCd(String postCd) {
        if (!ZYPCommonFunc.hasValue(postCd)) {
            return null;
        }
        if (postCd.length() > 5) {
            return postCd.substring(0, 5);
        }
        return postCd;
    }
}
