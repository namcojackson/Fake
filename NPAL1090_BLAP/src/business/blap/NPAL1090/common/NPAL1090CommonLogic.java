package business.blap.NPAL1090.common;

import static business.blap.NPAL1090.constant.NPAL1090Constant.BUSINESS_APPLICATION_ID;
import static business.blap.NPAL1090.constant.NPAL1090Constant.CSV_HEADER_INFO;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DATE_STR_LENGTH;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_COLUMN_PRCH_REQ_APVL_STS_CD;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_COLUMN_PRCH_REQ_APVL_STS_DESC_TXT;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_COLUMN_PRCH_REQ_SRC_TP_CD;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_COLUMN_PRCH_REQ_SRC_TP_DESC_TXT;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_COLUMN_PRCH_REQ_STS_CD;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_COLUMN_PRCH_REQ_STS_DESC_TXT;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_COLUMN_PRCH_REQ_TP_CD;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_COLUMN_PRCH_REQ_TP_DESC_TXT;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_COLUMN_SHPG_SVC_LVL_CD;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_COLUMN_SHPG_SVC_LVL_DESC_TXT;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_COLUMN_SRCH_OPT_NM;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_COLUMN_SRCH_OPT_PK;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_PARAM_PRCH_REQ_REC_TP_CD;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_REQUEST;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_RETURN;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_PARAM_SRCH_OPT_APL_ID;
import static business.blap.NPAL1090.constant.NPAL1090Constant.DB_PARAM_SRCH_OPT_USR_ID;
import static business.blap.NPAL1090.constant.NPAL1090Constant.HALF_DAY_HOURS;
import static business.blap.NPAL1090.constant.NPAL1090Constant.HOUR_MINUTE_STR_LENGTH;
import static business.blap.NPAL1090.constant.NPAL1090Constant.MAX_DOWNLOAD_CNT;
import static business.blap.NPAL1090.constant.NPAL1090Constant.NZZM0007E;
import static business.blap.NPAL1090.constant.NPAL1090Constant.ONE_DAY_HOURS;
import static business.blap.NPAL1090.constant.NPAL1090Constant.TIME_AM;
import static business.blap.NPAL1090.constant.NPAL1090Constant.TIME_FORMAT_YYYYMMDDHHMMSS;
import static business.blap.NPAL1090.constant.NPAL1090Constant.TIME_PM;
import static business.blap.NPAL1090.constant.NPAL1090Constant.TIME_STR_LENGTH;
import static business.blap.NPAL1090.constant.NPAL1090Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCDateItem;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1090.NPAL1090CMsg;
import business.blap.NPAL1090.NPAL1090Query;
import business.blap.NPAL1090.NPAL1090SMsg;
import business.db.RTL_WHTMsg;
import business.file.NPAL1090F00FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeData;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeException;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request List
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/09/2015   CITS       Yasushi Nomura        Create          N/A
 * 12/19/2016   CITS            K.Ogino         Update          QC#15825
 * 02/07/2018   CITS            K.Ogino         Update          QC#18922
 *</pre>
 */
public class NPAL1090CommonLogic {
    /**
     * Method Name: complementFromToDate <dd>When one of from or to is
     * empty, fill empty value with the other. <dd>When from is future
     * of to, swap from and to.
     * @param from EZDCDateItem
     * @param to EZDCDateItem
     */
    public static void complementFromToDate(EZDCDateItem from, EZDCDateItem to) {
        if (ZYPCommonFunc.hasValue(from) && ZYPCommonFunc.hasValue(to)) {
            // date can be compared as string
            String fromValue = from.getValue();

            if (fromValue.compareTo(to.getValue()) > 0) {
                from.setValue(to.getValue());
                to.setValue(fromValue);
            }
        }
    }

    /**
     * Create Pulldown Search Option
     * @param cMsg NPAL1090CMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownSearchOption(NPAL1090CMsg cMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        cMsg.srchOptPk_CD.clear();
        cMsg.srchOptNm_DI.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SRCH_OPT_APL_ID, BUSINESS_APPLICATION_ID);
        ssmParam.put(DB_PARAM_SRCH_OPT_USR_ID, cMsg.srchOptUsrId_U1.getValue());

        // Execute
        S21SsmEZDResult result = NPAL1090Query.getInstance().getSearchOptionPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_CD.no(i), (BigDecimal) recode.get(DB_COLUMN_SRCH_OPT_PK));
                ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_DI.no(i), (String) recode.get(DB_COLUMN_SRCH_OPT_NM));

                if (i >= cMsg.srchOptPk_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown RequisitionType
     * @param cMsg NPAL1090CMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownRequisitionType(NPAL1090CMsg cMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        cMsg.prchReqTpCd_CD.clear();
        cMsg.prchReqTpDescTxt_DI.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_REQUEST, PRCH_REQ_REC_TP.TECH_REQUEST);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_RETURN, PRCH_REQ_REC_TP.TECH_RETURN);
        // Execute
        S21SsmEZDResult result = NPAL1090Query.getInstance().getRequisitionTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_CD.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_TP_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpDescTxt_DI.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_TP_DESC_TXT));

                if (i >= cMsg.prchReqTpCd_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown HeaderStatus
     * @param cMsg NPAL1090CMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownHeaderStatus(NPAL1090CMsg cMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        cMsg.prchReqStsCd_CD.clear();
        cMsg.prchReqStsDescTxt_DI.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        // Execute
        S21SsmEZDResult result = NPAL1090Query.getInstance().getHeaderStatusPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchReqStsCd_CD.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_STS_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqStsDescTxt_DI.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_STS_DESC_TXT));

                if (i >= cMsg.prchReqStsCd_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown Approval Status
     * @param cMsg NPAL1090CMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownApprovalStatus(NPAL1090CMsg cMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        cMsg.prchReqApvlStsCd_CD.clear();
        cMsg.prchReqApvlStsDescTxt_DI.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        // Execute
        S21SsmEZDResult result = NPAL1090Query.getInstance().getApprovalStatusPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlStsCd_CD.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_APVL_STS_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlStsDescTxt_DI.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_APVL_STS_DESC_TXT));

                if (i >= cMsg.prchReqApvlStsCd_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown Document Source Type
     * @param cMsg NPAL1090CMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownDocumentSourceType(NPAL1090CMsg cMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        cMsg.prchReqSrcTpCd_CD.clear();
        cMsg.prchReqSrcTpDescTxt_DI.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.TECH_REQUEST);
        // Execute
        S21SsmEZDResult result = NPAL1090Query.getInstance().getDocumentSourceTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpCd_CD.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_SRC_TP_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpDescTxt_DI.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_SRC_TP_DESC_TXT));

                if (i >= cMsg.prchReqSrcTpCd_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown Req Service Level
     * @param cMsg NPAL1090CMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownRequestedShipMethod(NPAL1090CMsg cMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        cMsg.shpgSvcLvlCd_CD.clear();
        cMsg.shpgSvcLvlDescTxt_DI.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        // Execute
        S21SsmEZDResult result = NPAL1090Query.getInstance().getRequestedShipMethodPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_CD.no(i), (String) recode.get(DB_COLUMN_SHPG_SVC_LVL_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlDescTxt_DI.no(i), (String) recode.get(DB_COLUMN_SHPG_SVC_LVL_DESC_TXT));

                if (i >= cMsg.shpgSvcLvlCd_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * <pre>
     * copyFromSmsgOntoCmsg
     * Copy data From Smsg Onto Cmsg
     * </pre>
     * @param cMsg NPAL1090CMsg
     * @param sMsg NPAL1090SMsg
     */
    public static void copyFromSMsgOntoCmsg(NPAL1090CMsg cMsg, NPAL1090SMsg sMsg) {
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
     * @param cMsg NPAL1090CMsg
     * @param sMsg NPAL1090SMsg
     */
    public static void setPagePos(NPAL1090CMsg cMsg, NPAL1090SMsg sMsg) {
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
     * Execute API NSZC033001
     * @param bizMsg NPAL1090CMsg
     * @param pMsg NSZC033001PMsg
     * @return
     */
    private static boolean executeNszc0330(NPAL1090CMsg cMsg, NSZC033001PMsg pMsg) {
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
     * @param cMsg NPAL1090CMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearchOption(NPAL1090CMsg cMsg, String usrId, String glblCmpyCd) {
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
     * @param cMsg NPAL1090CMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchSearchOption(NPAL1090CMsg cMsg, String usrId, String glblCmpyCd) {
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
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_SE, pMsg.srchOptPk);

        ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum_H1, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_SE, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqStsCd_SE, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlStsCd_SE, pMsg.srchOptTxt_04);
        cMsg.prchReqCratDt_HF.setValue(pMsg.srchOptTxt_05.getValue());
        cMsg.prchReqCratDt_HT.setValue(pMsg.srchOptTxt_06.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpCd_SE, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(cMsg.fsrNum_H1, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(cMsg.svcTaskNum_H1, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(cMsg.svcMachSerNum_H1, pMsg.srchOptTxt_10);
        cMsg.rqstRcvDt_HF.setValue(pMsg.srchOptTxt_11.getValue());
        cMsg.rqstRcvDt_HT.setValue(pMsg.srchOptTxt_12.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.techNm_H1, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_H1, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd_H1, pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(cMsg.locNm_H1, pMsg.srchOptTxt_16);
        ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SE, pMsg.srchOptTxt_17);
        ZYPEZDItemValueSetter.setValue(cMsg.carrNm_H1, pMsg.srchOptTxt_18);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd_H1, pMsg.srchOptTxt_19);
    }

    /**
     * Call API NSZC033001 (Save SearchOption)
     * @param cMsg NPAL1090CMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearchOption(NPAL1090CMsg cMsg, String usrId, String glblCmpyCd) {
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
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.prchReqStsCd_SE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.prchReqApvlStsCd_SE);
        pMsg.srchOptTxt_05.setValue(cMsg.prchReqCratDt_HF.getValue());
        pMsg.srchOptTxt_06.setValue(cMsg.prchReqCratDt_HT.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.prchReqSrcTpCd_SE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.fsrNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.svcTaskNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.svcMachSerNum_H1);
        pMsg.srchOptTxt_11.setValue(cMsg.rqstRcvDt_HF.getValue());
        pMsg.srchOptTxt_12.setValue(cMsg.rqstRcvDt_HT.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.techNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.rtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.rtlSwhCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.locNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, cMsg.shpgSvcLvlCd_SE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, cMsg.carrNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, cMsg.shipToCustCd_H1);

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
     * @param cMsg NPAL1090CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NPAL1090CMsg cMsg) {
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
     * @param cMsg NPAL1090CMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NPAL1090CMsg cMsg) {
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
     * @param cMsg NPAL1090CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NPAL1090CMsg cMsg) {
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
     * <pre>
     * Write csv file Contract Info
     * @param bizMsg NPAL1090CMsg
     * @param rs     ResultSet
     * @param glblCmpyCd     String
     * @throws SQLException
     * </pre>
     */
    public static void writeCsvFileInfo(NPAL1090CMsg bizMsg, ResultSet rs, String glblCmpyCd) throws SQLException {
        Map<String, String> techTZIdMap = new HashMap<String, String>();

        NPAL1090F00FMsg fMsg = new NPAL1090F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
        csvOutFile.writeHeader(CSV_HEADER_INFO, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

        do {
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
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqTpDescTxt_D1, rs.getString("PRCH_REQ_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqStsDescTxt_D1, rs.getString("PRCH_REQ_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqApvlStsDescTxt_D1, rs.getString("PRCH_REQ_APVL_STS_DESC_TXT"));
            // Date Created
            String prchReqCratDt = rs.getString("PRCH_REQ_CRAT_DT");
            String prchReqCratTm = rs.getString("PRCH_REQ_CRAT_TM");
            if ((prchReqCratDt != null) && (prchReqCratTm != null)) {
                ZYPLocalTimeData lcl = NPAL1090CommonLogic.convertTimeSys2Lcl(prchReqCratDt, prchReqCratTm, lclTZId);
                if (lcl != null) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D1, NPAL1090CommonLogic.convertTime2ViewText(lcl, true));
                } else {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D1, ZYPDateUtil.formatEzd8ToDisp(prchReqCratDt, true));
                }
            } else if (prchReqCratDt != null) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D1, ZYPDateUtil.formatEzd8ToDisp(prchReqCratDt, true));
            }
            // Date & Time Needed
            String rqstRcvDt = rs.getString("RQST_RCV_DT");
            String rqstRcvTm = rs.getString("RQST_RCV_TM");
            if ((rqstRcvDt != null) && (rqstRcvTm != null)) {
                ZYPLocalTimeData lcl = NPAL1090CommonLogic.convertTimeSys2Lcl(rqstRcvDt, rqstRcvTm, lclTZId);
                if (lcl != null) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D2, NPAL1090CommonLogic.convertTime2ViewText(lcl, true));
                } else {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D2, ZYPDateUtil.formatEzd8ToDisp(rqstRcvDt, true));
                }
            } else if (rqstRcvDt != null) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxPopPrm_D2, ZYPDateUtil.formatEzd8ToDisp(rqstRcvDt, true));
            }
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqSrcTpDescTxt_D1, rs.getString("PRCH_REQ_SRC_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.trxRefNum_D1, rs.getString("TRX_REF_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqCratByPsnCd_D1, rs.getString("PRCH_REQ_CRAT_BY_PSN_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.fullPsnNm_D1, rs.getString("FULL_PSN_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.lineBizTpCd_D1, rs.getString("LINE_BIZ_TP_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.shpgSvcLvlDescTxt_D1, rs.getString("SHPG_SVC_LVL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.carrNm_D1, rs.getString("CARR_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.rqstTechTocCd_D1, rs.getString("RQST_TECH_TOC_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.techNm_D1, rs.getString("TECH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.destRtlWhCd_D1, rs.getString("DEST_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_D1, rs.getString("RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.destRtlSwhCd_D1, rs.getString("DEST_RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhNm_D1, rs.getString("RTL_SWH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.shipToCustCd_D1, rs.getString("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.locNm_D1, rs.getString("LOC_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.fsrNum_D1, rs.getString("FSR_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcTaskNum_D1, rs.getString("SVC_TASK_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcMachSerNum_D1, rs.getString("SVC_MACH_SER_NUM"));

            csvOutFile.write();
        } while (rs.next());
        csvOutFile.close();
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
        if (time.length() == HOUR_MINUTE_STR_LENGTH - 1) {
            time = "0" + time + "00";
        } else if (time.length() == HOUR_MINUTE_STR_LENGTH) {
            time = time + "00";
        }
        date = date + time;
        if (date.length() != DATE_STR_LENGTH + TIME_STR_LENGTH) {
            return null;
        }
        return ZYPLocalTimeUtil.convertTimeSys2Lcl(lclTZId, date, TIME_FORMAT_YYYYMMDDHHMMSS);
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
            if ((time.length() != HOUR_MINUTE_STR_LENGTH - 1) && (time.length() != HOUR_MINUTE_STR_LENGTH) && (time.length() != TIME_STR_LENGTH)) {
                return null;
            }
            if (time.length() == HOUR_MINUTE_STR_LENGTH - 1) {
                time = "0" + time;
            } else if (time.length() == TIME_STR_LENGTH) {
                time = time.substring(0, HOUR_MINUTE_STR_LENGTH);
            }
            sHh = time.substring(0, 2);
            sMm = time.substring(2, HOUR_MINUTE_STR_LENGTH);
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
        if ((iHh < 0) || (ONE_DAY_HOURS <= iHh)) {
            return null;
        }
        if ((iMm < 0) || (59 < iMm)) {
            return null;
        }
        return new int[] {iHh, iMm };
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
