/*
 * <Pre>Copyright(c)2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3120.common;

import static business.blap.NLBL3120.constant.NLBL3120Constant.BIZ_ID;
import static business.blap.NLBL3120.constant.NLBL3120Constant.SCRN_ID;
import static business.blap.NLBL3120.constant.NLBL3120Constant.SEARCH_RWS;
import static business.blap.NLBL3120.constant.NLBL3120Constant.SEARCH_RWS_AND_SO;
import static business.blap.NLBL3120.constant.NLBL3120Constant.SEARCH_SO;
import static parts.common.EZDCommonFunc.isNumberCheck;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCDateItem;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSDateItem;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLBL3120.NLBL3120CMsg;
import business.blap.NLBL3120.NLBL3120Query;
import business.blap.NLBL3120.NLBL3120SMsg;
import business.blap.NLBL3120.NLBL3120_ASMsg;
import business.db.SCHD_COORD_ASG_RELNTMsg;
import business.db.SCHD_COORD_ASG_RELNTMsgArray;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Workload Balancing and Monitor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         S.Yoshida       Create          N/A
 * 2017/06/28   CITS            T.Kikuhara      Update          QC#19137
 *</pre>
 */
public class NLBL3120CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * setSsearchDelyResultToGlblMsg
     * @param ssmResult S21SsmEZDResult
     * @param sMsg NLBL3120SMsg
     * @return result count
     */
    public static int setSearchSchdResultToGlblMsg(S21SsmEZDResult ssmResult, NLBL3120SMsg sMsg) {
        // SSM Result
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        sMsg.A.clear();
        sMsg.A.setValidCount(0);

        String trxHdrNum = "";

        int msgCnt = 0;
        int resIdx = 0;
        int resCnt = resultList.size();
        for (; resIdx < resultList.size() && msgCnt < sMsg.A.length(); resIdx++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(resIdx);
            NLBL3120_ASMsg sMsgALine = sMsg.A.no(msgCnt);

            ZYPEZDItemValueSetter.setValue(sMsgALine.xxNum_A1, new BigDecimal(msgCnt));
            ZYPEZDItemValueSetter.setValue(sMsgALine.trxHdrNum_A1, (String) resultMap.get("TRX_HDR_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.dsOrdCatgDescTxt_A1, (String) resultMap.get("DS_ORD_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.dsOrdTpDescTxt_A1, (String) resultMap.get("DS_ORD_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.xxSmryLineFlg_A1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sMsgALine.schdTrxOpenFlg_A1, (String) resultMap.get("SCHD_TRX_OPEN_FLG"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.delyOrPickUpRqstFlg_A1, (String) resultMap.get("DELY_OR_PICK_UP_RQST_FLG"));
            msgCnt++;

            if (!trxHdrNum.equals((String) resultMap.get("TRX_HDR_NUM"))) {
                ZYPEZDItemValueSetter.setValue(sMsgALine.xxPgFlg_A1, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(sMsgALine.xxExstFlg_A1, (String) resultMap.get("EC_BO_EXST_FLG"));
                ZYPEZDItemValueSetter.setValue(sMsgALine.xxDtlCnt_A1, (BigDecimal) resultMap.get("CONFIG_CNT"));
                trxHdrNum = (String) resultMap.get("TRX_HDR_NUM");
                resIdx--;
                resCnt++;
                continue;
            }

            ZYPEZDItemValueSetter.setValue(sMsgALine.xxPgFlg_A1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sMsgALine.soNum_A1, (String) resultMap.get("SO_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.rwsNum_A1, (String) resultMap.get("RWS_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.wmsDropFlg_A1, (String) resultMap.get("WMS_DROP_FLG"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.wmsDropRqstFlg_A1, (String) resultMap.get("WMS_DROP_RQST_FLG"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.xxDtlCnt_A1, (BigDecimal) resultMap.get("CONFIG_CNT"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.svcConfigMstrPk_A1, (BigDecimal) resultMap.get("SHIP_SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.t_MdlNm_A1, (String) resultMap.get("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.pickSvcConfigMstrPk_A1, (BigDecimal) resultMap.get("PICK_SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.rtlWhCd_A1, (String) resultMap.get("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.rtlWhNm_A1, (String) resultMap.get("RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.schdCoordPsnCd_A1, (String) resultMap.get("SCHD_COORD_PSN_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.xxPsnFirstMidLastNm_A1, (String) resultMap.get("SCHD_COORD_PSN_NM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.shipToCustAcctCd_A1, (String) resultMap.get("SHIP_TO_CUST_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.fromLocCd_A1, (String) resultMap.get("SHIP_TO_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.dsAcctNm_A1, (String) resultMap.get("SHIP_TO_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.fromLocAddr_A1, (String) resultMap.get("SHIP_TO_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.fromLocCtyAddr_A1, (String) resultMap.get("SHIP_TO_CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.fromLocStCd_A1, (String) resultMap.get("SHIP_TO_ST_CD"));
            // QC#19137 ADD START
            ZYPEZDItemValueSetter.setValue(sMsgALine.schdCoordStsCd_A1, (String) resultMap.get("SCHD_COORD_STS_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.schdCoordStsDescTxt_A1, (String) resultMap.get("SCHD_COORD_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.schdOpenFlg_A1, (String) resultMap.get("SCHD_OPEN_FLG"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.manUpdPrtyNum_A1, (BigDecimal) resultMap.get("MAN_UPD_PRTY_NUM"));
            // QC#19137 ADD END
            ZYPEZDItemValueSetter.setValue(sMsgALine.rwsStsDescTxt_A1, (String) resultMap.get("SCHD_TRX_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.shpgSvcLvlCd_A1, (String) resultMap.get("SHPG_SVC_LVL_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.carrCd_A1, (String) resultMap.get("CARR_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.carrCd_OD, (String) resultMap.get("ORD_CARR_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.locNm_A1, (String) resultMap.get("CARR_NM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.xxScrItem130Txt_A1, (String) resultMap.get("TEL_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.carrAcctNum_A1, (String) resultMap.get("CARR_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.tocNm_A1, (String) resultMap.get("TOC_NM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.rddDt_A1, (String) resultMap.get("RDD_DT"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.schdPickUpDt_A1, (String) resultMap.get("SCHD_COORD_DT"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.schdTrxCloDtTmTs_A1, (String) resultMap.get("SCHD_TRX_CLO_DT_TM_TS"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.xxSmryLineFlg_A1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sMsgALine.schdDelyDt_A1, (String) resultMap.get("SCHD_DELY_DT"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.ezUpTime_SO, (String) resultMap.get("EZUPTIME_SO"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.ezUpTimeZone_SO, (String) resultMap.get("EZUPTIMEZONE_SO"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.ezUpTime_RS, (String) resultMap.get("EZUPTIME_RS"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.ezUpTimeZone_RS, (String) resultMap.get("EZUPTIMEZONE_RS"));

            // backup
            sMsgALine.schdCoordPsnCd_AK.setValue(sMsgALine.schdCoordPsnCd_A1.getValue());
            sMsgALine.shpgSvcLvlCd_AK.setValue(sMsgALine.shpgSvcLvlCd_A1.getValue());
            sMsgALine.carrCd_AK.setValue(sMsgALine.carrCd_A1.getValue());
            sMsgALine.schdPickUpDt_AK.setValue(sMsgALine.schdPickUpDt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(sMsgALine.carrAcctNum_AK, sMsgALine.carrAcctNum_A1);
            ZYPEZDItemValueSetter.setValue(sMsgALine.tempSchdFlg_AK, (String) resultMap.get("TEMP_SCHD_FLG"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.schdCoordStsCd_AK, (String) resultMap.get("SCHD_COORD_STS_CD"));

            // API Parameter
            ZYPEZDItemValueSetter.setValue(sMsgALine.dsOrdCatgCd_AP, (String) resultMap.get("DS_ORD_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.dsOrdTpCd_AP, (String) resultMap.get("DS_ORD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.addShipToPostCd_AP, (String) resultMap.get("ADD_SHIP_TO_POST_CD"));

        }
        sMsg.A.setValidCount(msgCnt);
        return resCnt;
    }

    /**
     * saveCurrentPageToSMsg
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    public static void saveCurrentPageToSMsg(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {

        cMsg.clearErrorInfo();
        cMsg.A.clearErrorInfo();
        sMsg.clearErrorInfo();
        sMsg.A.clearErrorInfo();

        String preChkBox = "";
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxPgFlg_A1.getValue())) {
                preChkBox = cMsg.A.no(i).xxChkBox_A1.getValue();
            } else if (ZYPConstant.FLG_ON_Y.equals(preChkBox) && !ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxChkBox_A2.getValue())) {
                cMsg.A.no(i).xxChkBox_A2.setValue(ZYPConstant.FLG_ON_Y);
            }

            int sMsgIndex = cMsg.A.no(i).xxNum_A1.getValueInt();

            // check input change. release warning flag.
            if (!equalsEzdStringItem(cMsg.A.no(i).schdCoordPsnCd_A1, sMsg.A.no(sMsgIndex).schdCoordPsnCd_A1) //
                    || !equalsEzdStringItem(cMsg.A.no(i).shpgSvcLvlCd_A1, sMsg.A.no(sMsgIndex).shpgSvcLvlCd_A1) //
                    || !equalsEzdStringItem(cMsg.A.no(i).carrCd_A1, sMsg.A.no(sMsgIndex).carrCd_A1) //
                    || !equalsEzdStringItem(cMsg.A.no(i).carrAcctNum_A1, sMsg.A.no(sMsgIndex).carrAcctNum_A1) //
                    || !equalsEzdDateItem(cMsg.A.no(i).schdPickUpDt_A1, sMsg.A.no(sMsgIndex).schdPickUpDt_A1)) {
                cMsg.xxWrnSkipFlg.clear();
            }

            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(sMsgIndex), null);
        }
    }

    /**
     * page nation
     * @param bizMsg NLBL3120CMsg
     * @param globalMsg NLBL3120SMsg
     * @param nextRecIdx Next Record Index
     * @param fromNum From Number
     */
    public static void pagenation(NLBL3120CMsg bizMsg, NLBL3120SMsg globalMsg, int nextRecIdx, int fromNum) {

        int cMsgIdx = 0;
        String preTrxHdrNum = "";
        for (int i = nextRecIdx; cMsgIdx < bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {

                if (!ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxSmryLineFlg_A1.getValue())) {
                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(cMsgIdx), null);
                    cMsgIdx++;
                } else if (!preTrxHdrNum.equals(globalMsg.A.no(i).trxHdrNum_A1.getValue())) {
                    // Summary Flag == Y
                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(cMsgIdx), null);
                    cMsgIdx++;
                }
                preTrxHdrNum = globalMsg.A.no(i).trxHdrNum_A1.getValue();

            } else {
                break;
            }
        }

        // set value to pagenation items
        bizMsg.A.setValidCount(cMsgIdx);
        bizMsg.xxPageShowFromNum_A.setValue(fromNum + 1);
        bizMsg.xxPageShowToNum_A.setValue(fromNum + bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum_A.setValue(getPageShowOfNum(globalMsg));
    }

    /**
     * getPageStartRowIndexForError
     * @param index  int
     * @param bizMsg NLBL3120CMsg
     * @param globalMsg NLBL3120SMsg
     * @return int
     */
    public static int getPageStartRowIndexForError(int index, NLBL3120CMsg bizMsg, NLBL3120SMsg globalMsg) {
        int startIndex = 0;
        String preTrxHdrNum = "";
        int convIndex = 0;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            if (index == i) {
                index = convIndex;
                break;
            }
            if (!ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxSmryLineFlg_A1.getValue())) {
                convIndex++;
            } else if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxSmryLineFlg_A1.getValue())
                    && !preTrxHdrNum.equals(globalMsg.A.no(i).trxHdrNum_A1.getValue())) {
                // Summary Flag == Y
                convIndex++;
            }
            preTrxHdrNum = globalMsg.A.no(i).trxHdrNum_A1.getValue();
        }
        startIndex = (index / bizMsg.A.length()) * bizMsg.A.length();
        return startIndex;
    }

    /**
     * getPageStartRowIndex
     * @param startIdx  int
     * @param bizMsg NLBL3120CMsg
     * @param globalMsg NLBL3120SMsg
     * @return int
     */
    public static int getPageStartRowIndex(int startIdx, NLBL3120CMsg bizMsg, NLBL3120SMsg globalMsg) {

        if (startIdx == 0) {
            return 0;
        }

        int targetIdx = 0;
        int convIndex = 0;
        String preTrxHdrNum = "";
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            if (!ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxSmryLineFlg_A1.getValue())) {
                convIndex++;
            } else if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxSmryLineFlg_A1.getValue())
                    && !preTrxHdrNum.equals(globalMsg.A.no(i).trxHdrNum_A1.getValue())) {
                // Summary Flag == Y
                convIndex++;
            }

            if (convIndex == startIdx + 1) {
                targetIdx = globalMsg.A.no(i).xxNum_A1.getValueInt();
                break;
            }
            preTrxHdrNum = globalMsg.A.no(i).trxHdrNum_A1.getValue();
        }

        return targetIdx;
    }

    /**
     * getPageNum
     * @param rowNum int
     * @param bizMsg NLBL3120CMsg
     * @return int
     */
    public static int getPageNum(int rowNum, NLBL3120CMsg bizMsg) {
        return ((rowNum - 1) / bizMsg.A.length() + 1);
    }

    /**
     * allExpansion
     * @param globalMsg NLBL3120SMsg
     */
    public static void allExpansion(NLBL3120SMsg globalMsg) {
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxSmryLineFlg_A1, ZYPConstant.FLG_OFF_N);
        }
    }


    /**
     * getPageShowOfNumB
     * @param globalMsg NLBL3120SMsg
     * @return int
     */
    public static int getPageShowOfNum(NLBL3120SMsg globalMsg) {
        int num = 0;
        String preTrxHdrNum = "";
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxSmryLineFlg_A1.getValue())) {
                num++;
            } else if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxSmryLineFlg_A1.getValue())
                    && !preTrxHdrNum.equals(globalMsg.A.no(i).trxHdrNum_A1.getValue())) {
                num++;
            }
            preTrxHdrNum = globalMsg.A.no(i).trxHdrNum_A1.getValue();
        }
        return num;
    }

    /**
     * isExistSaveSearchName
     * @param bizMsg NLBL3120CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NLBL3120CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) //
                        && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }


    /**
     * callNszc0330forSaveSearch
     * @param bizMsg NLBL3120CMsg
     * @param glblMsg NLBL3120SMsg
     * @param usrId NLBL3120SMsg
     * @param glblCmpyCd NLBL3120SMsg
     */
    public static void callNszc0330forSaveSearch(NLBL3120CMsg bizMsg, NLBL3120SMsg glblMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) //
                || isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, bizMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.trxHdrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.t_MdlNm);
        if (ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.svcConfigMstrPk.getValue().toString());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.soNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.dsSoLineStsCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.rwsNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.rwsStsCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.schdCoordStsCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.schdCoordPsnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.supvPsnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.mgrPsnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.slsRepOrSlsTeamTocCd);

        if (ZYPCommonFunc.hasValue(bizMsg.rddDt_FR)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.rddDt_FR.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.rddDt_TO)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, bizMsg.rddDt_TO.getValue());
        }

        if (ZYPCommonFunc.hasValue(bizMsg.schdCoordDt_FR)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, bizMsg.schdCoordDt_FR.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.schdCoordDt_TO)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, bizMsg.schdCoordDt_TO.getValue());
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, bizMsg.xxChkBox_DS);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, bizMsg.xxChkBox_DR);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, bizMsg.xxChkBox_CA);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, bizMsg.xxChkBox_CN);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, usrId);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.setMessageInfo("ZZZM9003I" //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Save Search") });
        }
    }

    /**
     * isSameSaveSearchName
     * @param cMsg NLBL3120CMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NLBL3120CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {
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
     * @param bizMsg NLBL3120CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NLBL3120CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_L.no(i));
            }
        }
        return;
    }

    /**
     * callNszc0330
     * @param bizMsg NLBL3120CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    private static boolean callNszc0330(NLBL3120CMsg bizMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        bizMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NLBL3120CMsg
     * @param srchOptUsrId String
     */
    public static void createSavedSearchOptionsPullDown(NLBL3120CMsg bizMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NLBL3120Query.getInstance().getSavedSearchOptionList(srchOptUsrId);
        if (!ssmResult.isCodeNormal()) {
            bizMsg.srchOptPk_L.clear();
            bizMsg.srchOptNm_L.clear();
            return;
        }

        bizMsg.srchOptPk_L.clear();
        bizMsg.srchOptNm_L.clear();
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_L.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }

    }

    /**
     * callNszc0330forDeleteSearch
     * @param bizMsg NLBL3120CMsg
     * @param glblMsg NLBL3120SMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearch(NLBL3120CMsg bizMsg, NLBL3120SMsg glblMsg, String userId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, userId);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I" //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Delete Search") });
        }
    }

    /**
     * callNszc0330forSearchOption
     * @param bizMsg NLBL3120CMsg
     * @param glblMsg NLBL3120SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NLBL3120CMsg bizMsg, NLBL3120SMsg glblMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.srchOptNm_S, pMsg.srchOptNm);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxHdrNum, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.t_MdlNm, pMsg.srchOptTxt_04);
        if (isNumberCheck(pMsg.srchOptTxt_05.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.svcConfigMstrPk, new BigDecimal(pMsg.srchOptTxt_05.getValue()));
        } else {
            bizMsg.svcConfigMstrPk.clear();
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.soNum, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsSoLineStsCd, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsNum, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsStsCd, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(bizMsg.schdCoordStsCd, pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd, pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(bizMsg.schdCoordPsnCd, pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(bizMsg.supvPsnCd, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(bizMsg.mgrPsnCd, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepOrSlsTeamTocCd, pMsg.srchOptTxt_15);

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_16.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rddDt_FR, pMsg.srchOptTxt_16.getValue());
        } else {
            bizMsg.rddDt_FR.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_17.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rddDt_TO, pMsg.srchOptTxt_17.getValue());
        } else {
            bizMsg.rddDt_TO.clear();
        }

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_18.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.schdCoordDt_FR, pMsg.srchOptTxt_18.getValue());
        } else {
            bizMsg.schdCoordDt_FR.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_19.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.schdCoordDt_TO, pMsg.srchOptTxt_19.getValue());
        } else {
            bizMsg.schdCoordDt_TO.clear();
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_DS, pMsg.srchOptTxt_20);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_DR, pMsg.srchOptTxt_21);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_CA, pMsg.srchOptTxt_22);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_CN, pMsg.srchOptTxt_23);
    }

    /**
     * soOpen
     * @param openFlg String
     * @param shipQty BigDecimal
     * @param shpgQty BigDecimal
     * @return boolean
     */
    public static boolean soOpen(String openFlg, BigDecimal shipQty, BigDecimal shpgQty) {
        if (ZYPConstant.FLG_OFF_N.equals(openFlg)) {
            // Shipped or Canceled
            return false;
        } else if (ZYPConstant.FLG_ON_Y.equals(openFlg)
                && (shipQty == null
                        || shipQty.compareTo(BigDecimal.ZERO) == 0)) {
            // Not Shipped
            return true;
        }
        return false;
    }


    /**
     * convDplyDtTm
     * @param dt String
     * @param tm String
     * @return boolean
     */
    public static String convDplyDtTm(String dt, String tm) {

        if (ZYPCommonFunc.hasValue(dt) && dt.length() == 8
                && ZYPCommonFunc.hasValue(tm)
                && 6 == tm.length()) {
            return ZYPDateUtil.formatDisp14ToEzd(dt + tm);

        } else if (ZYPCommonFunc.hasValue(dt) && dt.length() == 8) {
            return ZYPDateUtil.formatDisp8ToEzd(dt);
        }
        return null;
    }

    /**
     * convDplyDtTm
     * @param timeStamp String
     * @return String
     */
    public static String convDplyDtTm(String timeStamp) {
        if (ZYPCommonFunc.hasValue(timeStamp)) {
            return ZYPDateUtil.formatEzd14ToDisp(timeStamp);
        }
        return null;
    }

    /**
     * sort
     * @param sMsgArray EZDSMsgArray
     * @param sortItemNm String
     * @param sortOrdBy String
     * @param baseContents String[][] 
     * @param nullLast boolean
     */
    public static void sort(EZDSMsgArray sMsgArray, String sortItemNm, String sortOrdBy, String[][] baseContents, boolean nullLast) {

        S21SortTarget sortTarget = new S21SortTarget(sMsgArray, baseContents);
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);
        if (nullLast) {
            S21EZDMsgArraySort.sortNullsLast(sortTarget, sortKey, 0, sMsgArray.getValidCount());
        } else {
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsgArray.getValidCount());
        }

    }

    /**
     * isAssignCoordPsn
     * @param glblCmpyCd global company code
     * @param rtlWhCd retail warehouse code
     * @param loginPsnId login person id
     * @param coordPsnCd coordinator person code
     * @return assign coordinator person
     */
    public static boolean isAssignCoordPsn(String glblCmpyCd, String rtlWhCd, String loginPsnId, String coordPsnCd) {
        SCHD_COORD_ASG_RELNTMsg schdCoordAsgReln = new SCHD_COORD_ASG_RELNTMsg();
        schdCoordAsgReln.setSQLID("001");
        schdCoordAsgReln.setConditionValue("glblCmpyCd01", glblCmpyCd);
        schdCoordAsgReln.setConditionValue("rtlWhCd01", rtlWhCd);
        schdCoordAsgReln.setConditionValue("mgrPsnCd01", loginPsnId);
        schdCoordAsgReln.setConditionValue("supvPsnCd01", loginPsnId);
        schdCoordAsgReln.setConditionValue("schdCoordPsnCd01", coordPsnCd);
        SCHD_COORD_ASG_RELNTMsgArray schdCoordAsgRelnList = (SCHD_COORD_ASG_RELNTMsgArray) EZDTBLAccessor.findByCondition(schdCoordAsgReln);
        if (schdCoordAsgRelnList.length() == 0) {
            return false;
        }
        return true;
    }

    /**
     * selectSearchCondition
     * @param cMsg NLBL3120CMsg
     * @return 0:Error / 1:SEARCH_RWS / 2: SEARCH_SO /
     * 3:SEARCH_RWS_AND_SO (int)
     */
    public static int selectSearchCondition(NLBL3120CMsg cMsg) {
        // select Search condition (Bit operation)
        int srchCond = 0;

        // Set Search Condition 1
        if (ZYPCommonFunc.hasValue(cMsg.soNum) || ZYPCommonFunc.hasValue(cMsg.dsSoLineStsCd)) {

            srchCond += SEARCH_SO;
        }
        if (ZYPCommonFunc.hasValue(cMsg.rwsNum) || ZYPCommonFunc.hasValue(cMsg.rwsStsCd)) {
            srchCond += SEARCH_RWS;
        }
        if (srchCond == 0) {
            srchCond = SEARCH_RWS_AND_SO;
        }

        // Set Search Condition 2
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_DS.getValue()) && !ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_DR.getValue())) {
            // Display SO Only
            srchCond = srchCond & SEARCH_SO;
        } else if (!ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_DS.getValue()) && ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_DR.getValue())) {
            // Display RWS Only
            srchCond = srchCond & SEARCH_RWS;
        }

        return srchCond;
    }

    /**
     * equalsEzdStringItem
     * @param c EZDCStringItem
     * @param s EZDSStringItem
     * @return true:same / false:different
     */
    public static boolean equalsEzdStringItem(EZDCStringItem c, EZDSStringItem s) {

        // Null check
        String cValue = c.getValue();
        String sValue = s.getValue();
        if (cValue == null) {
            cValue = "";
        }
        if (sValue == null) {
            sValue = "";
        }

        return cValue.equals(sValue);

    }

    /**
     * equalsEzdDateItem
     * @param c EZDCDateItem
     * @param s EZDSDateItem
     * @return true:same / false:different
     */
    public static boolean equalsEzdDateItem(EZDCDateItem c, EZDSDateItem s) {

        // Null check
        String cValue = c.getValue();
        String sValue = s.getValue();
        if (cValue == null) {
            cValue = "";
        }
        if (sValue == null) {
            sValue = "";
        }

        return cValue.equals(sValue);

    }
}

