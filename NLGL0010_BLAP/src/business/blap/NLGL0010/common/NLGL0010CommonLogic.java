/**
 * <pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0010.common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLGL0010.NLGL0010CMsg;
import business.blap.NLGL0010.NLGL0010Query;
import business.blap.NLGL0010.NLGL0010SMsg;
import business.blap.NLGL0010.constant.NLGL0010Constant;
import business.db.WMS_FRT_OUTTMsg;
import business.db.WMS_INBD_SO_BILL_TOTMsg;
import business.db.WMS_INBD_SO_CHRG_TOTMsg;
import business.db.WMS_INBD_SO_DTLTMsg;
import business.db.WMS_INBD_SO_HDRTMsg;
import business.db.WMS_INBD_SO_SHIP_TOTMsg;
import business.db.WMS_INBD_SO_TEXTTMsg;
import business.db.WMS_SHIP_VIA_RTE_MAPTMsg;
import business.db.WMS_WHTMsg;

import com.canon.cusa.s21.api.NLG.NLGC001001.NLGC001001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TASK;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TXT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * SO Mainenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/20/2013   CSAI            Y.Miyauchi      Create          MW Replace Initial
 * 05/30/2017   CITS            S.Endo          Update          RS#3168
 * 06/21/2017   CITS            S.Endo          Update          QC#19042
 * 07/03/2017   CITS            S.Endo          Update          QC#19042
 *</pre>
 */
public class NLGL0010CommonLogic implements NLGL0010Constant {

    /**
     * The method explanation: This method create dropdownlist for WH.
     * @param cMsg NLGL0010CMsg
     * @param glblCmpyCd String
     */
    public static void createWHPulldownList(NLGL0010CMsg cMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_WH_ALL_VAL, ASTERRISK);
        ssmParam.put(DB_WH_ALL_VAL, WH_ALL_VALUE);
        ssmParam.put(DB_WH_ALL_SEL_VAL, WH_ALL_SELECTION_VALUE);
        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getWHPullDownList(ssmParam);

        if (ssmResult.isCodeNormal()) {

            List mapWhList = (List) ssmResult.getResultObject();
            cMsg.whCd_01.clear();
            cMsg.xxEdtCdNm_01.clear();

//            // Set ALL Value
//            cMsg.whCd_01.no(0).setValue(WH_ALL_SELECTION_VALUE);
//            cMsg.xxEdtCdNm_01.no(0).setValue(ZYPCommonFunc.concatString(WH_ALL_SELECTION_VALUE, DELIMITER_COLON, VAL_WH_CD_NM_ALL));

            for (int i = 0; i < mapWhList.size(); i++) {
                Map pullDownData = (Map) mapWhList.get(i);
//                cMsg.whCd_01.no(i + 1).setValue((String) pullDownData.get(DB_WH_CD));
//                cMsg.xxEdtCdNm_01.no(i + 1).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(DB_WH_CD), DELIMITER_COLON, (String) pullDownData.get(DB_WMS_DESC_NM)));
                cMsg.whCd_01.no(i).setValue((String) pullDownData.get(DB_WH_CD));
                cMsg.xxEdtCdNm_01.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(DB_WH_CD), DELIMITER_COLON, (String) pullDownData.get(DB_WMS_DESC_NM)));

            }
        }
    }

    /**
     * The method explanation: This method create dropdownlist for
     * Date.
     * @param cMsg NLGL0010CMsg
     * @param glgbCmpyCd String
     */
    public static void createDTPulldownList(NLGL0010CMsg cMsg, String glgbCmpyCd) {

        cMsg.dateEntryL14If_01.clear();
        cMsg.xxScrItem14Txt_01.clear();

        for (int i = 0; i < DATE_TYPE_CODE_LIST.length; i++) {
            cMsg.dateEntryL14If_01.no(i).setValue(DATE_TYPE_CODE_LIST[i]);
            cMsg.xxScrItem14Txt_01.no(i).setValue(DATE_TYPE_NAME_LIST[i]);
        }
    }

    /**
     * The method explanation: This method create dropdownlist for WH
     * of DownLoad Tab.
     * @param bizMsg NLGL0010CMsg
     * @param glgbCmpyCd String
     */
    public static void createWHPulldownList_DNLD(NLGL0010CMsg bizMsg, String glgbCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_WH_ALL_VAL, ASTERRISK);
        ssmParam.put(DB_WH_ALL_SEL_VAL, WH_ALL_SELECTION_VALUE);
        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getWHPullDownList(ssmParam);

        if (ssmResult.isCodeNormal()) {
            List mapWhList = (List) ssmResult.getResultObject();
            bizMsg.whCd_J1.clear();
            bizMsg.xxEdtCdNm_J1.clear();

            for (int i = 0; i < mapWhList.size(); i++) {
                Map pullDownData = (Map) mapWhList.get(i);
                bizMsg.whCd_J1.no(i).setValue((String) pullDownData.get(DB_WH_CD));
                bizMsg.xxEdtCdNm_J1.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(DB_WH_CD), DELIMITER_COLON, (String) pullDownData.get(DB_WMS_DESC_NM)));
            }
        }
    }

    /**
     * The method explanation: This method create dropdownlist for
     * WMS_ORD_TP.
     * @param cdArr Code array
     * @param nmArr Name array
     * @param glgbCmpyCd Global Company Code
     * @param blInOut Inbound or OutBound Flag
     * @param delimiter Name delimiter
     */
    public static void createWmsOrdTpPulldownList(EZDCStringItemArray cdArr, EZDCStringItemArray nmArr, String glgbCmpyCd, boolean blInOut, String delimiter) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        if (blInOut) {
            ssmParam.put(DB_PARAM_INBD_OTBD_CD, INBD_OTBD_CD_OTBD);
        } else {
            ssmParam.put(DB_PARAM_INBD_OTBD_CD, INBD_OTBD_CD_INBD);
        }
        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getWmsOrdTpPullDownList(ssmParam);

        if (ssmResult.isCodeNormal()) {
            List mapList = (List) ssmResult.getResultObject();
            cdArr.clear();
            nmArr.clear();

            for (int i = 0; i < mapList.size(); i++) {
                Map pullDownData = (Map) mapList.get(i);
                cdArr.no(i).setValue((String) pullDownData.get(DB_WMS_ORD_TP_CD));
                nmArr.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(DB_WMS_ORD_TP_CD), delimiter, (String) pullDownData.get(DB_WMS_ORD_TP_NM)));
            }
        }
    }

    /**
     * The method explanation: This method create dropdownlist for
     * WMS_ORD_TP.
     * @param cdArr Code array
     * @param nmArr Name array
     * @param glgbCmpyCd Global Company Code
     * @param blInOut Inbound or OutBound Flag
     */
    public static void createWmsOrdTpCodePulldownList(EZDCStringItemArray cdArr, EZDCStringItemArray nmArr, String glgbCmpyCd, boolean blInOut) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        if (blInOut) {
            ssmParam.put(DB_PARAM_INBD_OTBD_CD, INBD_OTBD_CD_OTBD);
        } else {
            ssmParam.put(DB_PARAM_INBD_OTBD_CD, INBD_OTBD_CD_INBD);
        }
        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getWmsOrdTpPullDownList(ssmParam);

        if (ssmResult.isCodeNormal()) {
            List mapList = (List) ssmResult.getResultObject();
            cdArr.clear();
            nmArr.clear();

            for (int i = 0; i < mapList.size(); i++) {
                Map pullDownData = (Map) mapList.get(i);
                cdArr.no(i).setValue((String) pullDownData.get(DB_WMS_ORD_TP_CD));
                nmArr.no(i).setValue((String) pullDownData.get(DB_WMS_ORD_TP_CD));
            }
        }
    }

    /**
     * The method explanation: This method create dropdownlist for
     * WMS_TRX.
     * @param cdArr Code array  
     * @param nmArr Name array
     * @param glgbCmpyCd Global Company Code
     */
    public static void createWmsTrxPulldownList(EZDCStringItemArray cdArr, EZDCStringItemArray nmArr, String glgbCmpyCd) {

        String[][] wmsTrxArr = NLGC001001.getWmsTrxList(glgbCmpyCd, NLGC001001.WMS_INBD_OTBD_CD_OTBD);

        if (wmsTrxArr != null) {
            cdArr.clear();
            nmArr.clear();

            for (int i = 0; i < wmsTrxArr.length; i++) {
                cdArr.no(i).setValue(wmsTrxArr[i][0]);
                nmArr.no(i).setValue(ZYPCommonFunc.concatString(wmsTrxArr[i][0], DELIMITER_COLON, wmsTrxArr[i][1]));
            }
        }
    }

    /**
     * The method explanation: This method create dropdownlist for
     * WMS_TASK.
     * @param cdArr Code array
     * @param nmArr Name array
     * @param glgbCmpyCd Global Company Code
     * @param addAllFlg where add ALL value
     * @param delimiter Name delimiter
     */
    public static void createWmsTaskPulldownList(EZDCStringItemArray cdArr, EZDCStringItemArray nmArr, String glgbCmpyCd, boolean addAllFlg, String delimiter) {

        String[][] wmsTaskArr = NLGC001001.getWmsTaskList(glgbCmpyCd, NLGC001001.WMS_INBD_OTBD_CD_BOTH);

        if (wmsTaskArr != null) {
            cdArr.clear();
            nmArr.clear();

            int basePos = 0;
            if (addAllFlg) {
                cdArr.no(0).setValue(VAL_WMS_TASK_CD_ALL);
                nmArr.no(0).setValue(VAL_WMS_TASK_NM_ALL);
                basePos = 1;
            }
            for (int i = 0; i < wmsTaskArr.length; i++) {
                cdArr.no(i + basePos).setValue(wmsTaskArr[i][0]);
                nmArr.no(i + basePos).setValue(ZYPCommonFunc.concatString(wmsTaskArr[i][0], delimiter, wmsTaskArr[i][1]));
            }
        }
    }

    /**
     * The method explanation: This method create dropdownlist for
     * WMS_TASK.
     * @param cdArr Code array
     * @param nmArr Name array
     * @param glgbCmpyCd Global Company Code
     * @param addAllFlg where add ALL value
     */
    public static void createWmsTaskCodePulldownList(EZDCStringItemArray cdArr, EZDCStringItemArray nmArr, String glgbCmpyCd, boolean addAllFlg) {

        String[][] wmsTaskArr = NLGC001001.getWmsTaskList(glgbCmpyCd, NLGC001001.WMS_INBD_OTBD_CD_BOTH);

        if (wmsTaskArr != null) {
            cdArr.clear();
            nmArr.clear();

            int basePos = 0;
            if (addAllFlg) {
                cdArr.no(0).setValue(VAL_WMS_TASK_CD_ALL);
                nmArr.no(0).setValue(VAL_WMS_TASK_NM_ALL);
                basePos = 1;
            }
            for (int i = 0; i < wmsTaskArr.length; i++) {
                cdArr.no(i + basePos).setValue(wmsTaskArr[i][0]);
                nmArr.no(i + basePos).setValue(wmsTaskArr[i][0]);
            }
        }
    }

    /**
     * The method explanation: This method gets the SO List Data of SO
     * List Tab.
     * @param cMsg NLGL0010CMsg
     * @param sMsg NLGL0010SMsg
     * @param glgbCmpyCd String
     */
    public static void getSOList(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg, String glgbCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        if (!WH_ALL_SELECTION_VALUE.equals(cMsg.whCd_02.getValue())) {
            ssmParam.put(DB_PARAM_WH_CD, cMsg.whCd_02.getValue());
        }
        ssmParam.put(DB_PARAM_WMS_SO_ID, cMsg.wmsSoId_01.getValue());
        ssmParam.put(DB_PARAM_CUST_ORD_NM, cMsg.custOrdNum_01.getValue());
        ssmParam.put(DB_PARAM_XX_SO_SEARCH_FROM_DT, cMsg.xxSoSrchFromDt_01.getValue());
        ssmParam.put(DB_PARAM_XX_SO_SEARCH_THRU_DT, cMsg.xxSoSrchThruDt_01.getValue());
        ssmParam.put(DB_PARAM_SCE_ORD_TP_CD, cMsg.sceOrdTpCd_02.getValue());
        ssmParam.put(DB_PARAM_WMS_FRT_OUT_CD, cMsg.wmsFrtOutCd_02.getValue());
        ssmParam.put(DB_PARAM_SHIP_TO_CUST_CD, cMsg.shipToCustCd_01.getValue());
        ssmParam.put(DB_PARAM_BILL_TO_CUST_CD, cMsg.billToCustCd_01.getValue());
        ssmParam.put(DB_PARAM_WMS_TRX_CD, cMsg.wmsTrxCd_02.getValue());
        ssmParam.put(DB_PARAM_WMS_SHIP_VIA_TP_ID, cMsg.tplSvcLvlCd_J2.getValue());
        ssmParam.put(DB_PARAM_CHECK_BOX, cMsg.xxChkBox_01.getValue());
        ssmParam.put(DB_PARAM_DT_TP, cMsg.dateEntryL14If_02.getValue());
        ssmParam.put(DB_PARAM_WMS_ORD_STS, WMS_ORD_STS.SHIP);
        ssmParam.put(DB_PARAM_WMS_TXT_CD, WMS_TXT.CPO_INFORMATION);
        ssmParam.put(DB_PARAM_INBD_OTBD_CD, INBD_OTBD_CD_OTBD);
        ssmParam.put(DB_PARAM_PROC_STS_CD_ERROR, PROC_STS.ERROR);
        ssmParam.put(DB_PARAM_WMS_TASK_CD_OSC, WMS_TASK.OSC);
        // Conditions of WMS_INBD_SO_DTL
        ssmParam.put(DB_PARAM_WMS_MDSE_CD, cMsg.wmsMdseCd_01.getValue());
        // Conditions of Inboud Transaction
        ssmParam.put(DB_PARAM_WMS_SHIP_ID, cMsg.wmsShipId_01.getValue());
        ssmParam.put(DB_PARAM_BOL_NUM, cMsg.bolNum_01.getValue());
        ssmParam.put(DB_PARAM_WMS_WAVE_ID, cMsg.wmsWaveId_01.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.wmsShipId_01.getValue()) //
                || ZYPCommonFunc.hasValue(cMsg.bolNum_01.getValue()) //
                || ZYPCommonFunc.hasValue(cMsg.wmsWaveId_01.getValue())) {
            ssmParam.put(DB_PARAM_COND_WMS_INBD_TRX, ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put(DB_PARAM_COND_WMS_INBD_TRX, ZYPConstant.FLG_OFF_N);
        }
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd_01);
        ssmParam.put(DB_PARAM_INVTY_OWNR_CD, cMsg.invtyOwnrCd_01);
        ssmParam.put(DB_PARAM_ROWNUM, sMsg.A.length() + 1);

        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getSOList(sMsg, ssmParam);

        if (!ssmResult.isCodeNotFound()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.A.length();
            }

            int iRoopCount = 0;
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }

                // set Processing Status
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).shipDtTmTs_A1.getValue()) //
                        || ZYPCommonFunc.hasValue(sMsg.A.no(i).wmsCancDtTmTs_A1.getValue())) {
                    sMsg.A.no(i).xxDtlCd_A1.setValue(DB_PARAM_PROCESSED);
                } else if (ZYPCommonFunc.hasValue(sMsg.A.no(i).procStsCd_A1.getValue()) //
                        && sMsg.A.no(i).procStsCd_A1.getValue().equals(PROC_STS.ERROR)) {
                    sMsg.A.no(i).xxDtlCd_A1.setValue(DB_PARAM_ERROR);
                } else {
                    sMsg.A.no(i).xxDtlCd_A1.setValue(DB_PARAM_PROCESSING);
                }

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                iRoopCount++;
            }

            cMsg.A.setValidCount(iRoopCount);
            cMsg.xxPageShowFromNum_A1.setValue(1);
            cMsg.xxPageShowToNum_A1.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum_A1.setValue(queryResCnt);
            cMsg.whCd_02.setValue(sMsg.A.no(0).whCd_A1.getValue());
            copyFromSMsgOntoCmsg(cMsg, sMsg);
        } else {
            cMsg.setMessageInfo(NZZM0000E);
            cMsg.xxPageShowFromNum_A1.clear();
            cMsg.xxPageShowToNum_A1.clear();
            cMsg.xxPageShowOfNum_A1.clear();
        }
    }

    /**
     * The method explanation: This method copy the SO List Data
     * @param cMsg NLGL0010CMsg
     * @param sMsg NLGL0010SMsg
     */
    public static void copyFromSMsgOntoCmsg(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        int pagenationFromIndex = cMsg.xxPageShowFromNum_A1.getValueInt() - 1;
        int iRoopCount = 0;
        for (int i = pagenationFromIndex; i < pagenationFromIndex + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFromIndex), null);

                if (ZYPCommonFunc.hasValue(cMsg.A.no(i - pagenationFromIndex).wmsPrintDtTmTs_A1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i - pagenationFromIndex).xxDtTm_A1 //
                            , changeDateformat(cMsg.A.no(i - pagenationFromIndex).wmsPrintDtTmTs_A1.getValue(), SET_MMSS));
                }
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i - pagenationFromIndex).estShipDtTmTs_A1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i - pagenationFromIndex).xxDtTm_A2 //
                            , changeDateformat(cMsg.A.no(i - pagenationFromIndex).estShipDtTmTs_A1.getValue(), SET_MMSS));
                }
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i - pagenationFromIndex).wmsRqstDtTmTs_A1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i - pagenationFromIndex).xxDtTm_A3 //
                            , changeDateformat(cMsg.A.no(i - pagenationFromIndex).wmsRqstDtTmTs_A1.getValue(), SET_MMSS));
                }
                iRoopCount++;
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(iRoopCount);
        cMsg.xxPageShowToNum_A1.setValue(pagenationFromIndex + cMsg.A.getValidCount());
        sMsg.xxPageShowFromNum_A1.setValue(cMsg.xxPageShowFromNum_A1.getValue());
        sMsg.xxPageShowToNum_A1.setValue(cMsg.xxPageShowToNum_A1.getValue());
        sMsg.xxPageShowOfNum_A1.setValue(cMsg.xxPageShowOfNum_A1.getValue());
    }

    /**
     * The method explanation: This method set the Check to Item of
     * DownLoad
     * @param cMsg NLGL0010CMsg
     * @param chkValue String
     */
    public static void setDNLDItemInputChkValue(NLGL0010CMsg cMsg, String chkValue) {

        for (int iKListCount = 0; iKListCount < cMsg.K.getValidCount(); iKListCount++) {
            cMsg.K.no(iKListCount).xxChkBox_K1.setValue(chkValue);
        }
    }

    /**
     * The method explanation: This method set the Check to Item of
     * DownLoad
     * @param cMsg NLGL0010CMsg
     * @param chkValue String
     */
    public static void setUPDListChkValue(NLGL0010CMsg cMsg, String chkValue) {

        for (int iOListCount = 0; iOListCount < cMsg.O.getValidCount(); iOListCount++) {
            cMsg.O.no(iOListCount).xxChkBox_O1.setValue(chkValue);
        }
    }

    /**
     * The method explanation: This method get to was checked WMS SO
     * ID.
     * @param cMsg NLGL0010CMsg
     * @return String
     */
    public static String getChksceOrdTpCdFromSOList(NLGL0010CMsg cMsg) {

        if (!getChkNumOfCheckList(cMsg)) {
            return "";
        }

        String returnValue = "";
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxChkBox_A1.getValue())) {
                returnValue = cMsg.A.no(i).sceOrdTpCd_A1.getValue();
            }
        }
        return returnValue;
    }

    /**
     * The method explanation: This method get ORG HOST ID that was
     * checked at WMS SO From WMS_WH By Warehouse Code ID.
     * @param cMsg NLGL0010CMsg
     * @param glgbCmpyCd String
     * @return String
     */
    public static String getOrgHostIdFromWH(NLGL0010CMsg cMsg, String glgbCmpyCd) {

        if (!getChkNumOfCheckList(cMsg)) {
            return "";
        }

        String returnValue = "";
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxChkBox_A1.getValue())) {
                WMS_WHTMsg whTMsg = new WMS_WHTMsg();
                ZYPEZDItemValueSetter.setValue(whTMsg.glblCmpyCd, glgbCmpyCd);
                ZYPEZDItemValueSetter.setValue(whTMsg.whCd, cMsg.A.no(i).whCd_A1.getValue());
                whTMsg = (WMS_WHTMsg) EZDTBLAccessor.findByKey(whTMsg);
                if (EZDTBLAccessor.RTNCD_NORMAL.equals(whTMsg.getReturnCode())) {
                    returnValue = whTMsg.wmsOrgHostId.getValue();
                }
            }
        }
        return returnValue;
    }

    /**
     * The method explanation: This method check only one is checked.
     * @param cMsg NLGL0010CMsg
     * @return boolean
     */
    public static boolean getChkNumOfCheckList(NLGL0010CMsg cMsg) {

        List<Integer> outGetSelectedRows = ZYPTableUtil.getSelectedRows(cMsg.A, FIELD_NAME_CHKBOX_A1, ZYPConstant.CHKBOX_ON_Y);

        if (outGetSelectedRows.isEmpty()) {
            cMsg.setMessageInfo(NLGM0036E);
            return false;
        }

        if (outGetSelectedRows.size() > 1) {
            cMsg.setMessageInfo(NLGM0035E);
            return false;
        }
        return true;
    }

    /**
     * The method explanation: This method gets/sets the SO Header of
     * SO Status Tab.
     * @param cMsg NLGL0010CMsg
     * @param sMsg NLGL0010SMsg
     * @param glgbCmpyCd String
     * @param selectCnt int
     */
    public static void getSOHdrOfSts(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg, String glgbCmpyCd, int selectCnt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_PARAM_WMS_SO_ID, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSoId_A1.getValue());
        ssmParam.put(DB_PARAM_WH_CD, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).whCd_A1.getValue());
        ssmParam.put(DB_PARAM_WMS_SQ_NUM, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSqNum_A1.getValue());
        ssmParam.put(DB_PARAM_SCE_ORD_TP_CD, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).sceOrdTpCd_A1.getValue());
        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getSOHdr(sMsg, ssmParam);

        if (!ssmResult.isCodeNotFound()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.B.length();
            }

            int iRoopCount = 0;
            for (int i = 0; i < sMsg.B.getValidCount(); i++) {
                if (i == queryResCnt) {
                    break;
                }

                // sets MixPack
                sMsg.B.no(i).mixPltPltNoteTxt_B1.setValue(NLGL0010CommonLogic.getMixPack(sMsg, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).whCd_A1.getValue(), //
                        sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSoId_A1.getValue(), glgbCmpyCd, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSqNum_A1.getValue()));

                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).wmsPrintDtTmTs_B1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxDtTm_B1 //
                            , changeDateformat(sMsg.B.no(i).wmsPrintDtTmTs_B1.getValue(), SET_MMSS));
                }

                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).wmsRqstDtTmTs_B1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxDtTm_B2 //
                            , changeDateformat(sMsg.B.no(i).wmsRqstDtTmTs_B1.getValue(), SET_MMSS));
                }

                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).estShipDtTmTs_B1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxDtTm_B3 //
                            , changeDateformat(sMsg.B.no(i).estShipDtTmTs_B1.getValue(), SET_MMSS));
                }

                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).shipDtTmTs_B1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxDtTm_B4 //
                            , changeDateformat(sMsg.B.no(i).shipDtTmTs_B1.getValue(), SET_MMSS));
                }

                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).wmsCancDtTmTs_B1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxDtTm_B5 //
                            , changeDateformat(sMsg.B.no(i).wmsCancDtTmTs_B1.getValue(), SET_MMSS));
                }
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
                iRoopCount++;
            }
            sMsg.B.setValidCount(iRoopCount);
            cMsg.B.setValidCount(iRoopCount);
        }
    }

    /**
     * The method explanation: This method gets/sets Address of SO
     * Status Tab.
     * @param cMsg NLGL0010CMsg
     * @param sMsg NLGL0010SMsg
     * @param glgbCmpyCd String
     * @param selectCnt int
     */
    public static void getSODtlAddr(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg, String glgbCmpyCd, int selectCnt) {

        sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).xxChkBox_A1.setValue(cMsg.A.no(selectCnt).xxChkBox_A1.getValue());
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_PARAM_WMS_SO_ID, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSoId_A1.getValue());
        ssmParam.put(DB_PARAM_WH_CD, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).whCd_A1.getValue());
        ssmParam.put(DB_PARAM_WMS_SQ_NUM, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSqNum_A1.getValue());
        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getSODtlAddr(sMsg, ssmParam);

        if (!ssmResult.isCodeNotFound()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.C.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.C.length();
            }

            int iRoopCount = 0;
            for (int i = 0; i < sMsg.C.getValidCount(); i++) {
                if (i == queryResCnt) {
                    break;
                }
                StringBuffer sb = new StringBuffer();
                if (ZYPCommonFunc.hasValue(sMsg.C.no(i).firstLineAddr_C1)) {
                    sb.append(sMsg.C.no(i).firstLineAddr_C1.getValue());
                    sb.append(",");
                }
                if (ZYPCommonFunc.hasValue(sMsg.C.no(i).scdLineAddr_C1)) {
                    sb.append(sMsg.C.no(i).scdLineAddr_C1.getValue());
                    sb.append(",");
                }
                if (ZYPCommonFunc.hasValue(sMsg.C.no(i).thirdLineAddr_C1)) {
                    sb.append(sMsg.C.no(i).thirdLineAddr_C1.getValue());
                    sb.append(",");
                }
                if (ZYPCommonFunc.hasValue(sMsg.C.no(i).frthLineAddr_C1)) {
                    sb.append(sMsg.C.no(i).frthLineAddr_C1.getValue());
                }
                if (sb.length() > 0) {
                    if (",".equals(sb.substring(sb.length() - 1, sb.length()))) {
                        sMsg.C.no(i).fill256Txt_C1.setValue(sb.substring(0, sb.length() - 1));
                    } else {
                        sMsg.C.no(i).fill256Txt_C1.setValue(sb.toString().trim());
                    }
                } else {
                    sMsg.C.no(i).fill256Txt_C1.setValue("");
                }
                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i), null);
                iRoopCount++;
            }
            sMsg.C.setValidCount(iRoopCount);
            cMsg.C.setValidCount(iRoopCount);
        }
    }

    /**
     * The method explanation: This method gets/sets Detail of SO
     * Status Tab.
     * @param cMsg NLGL0010CMsg
     * @param sMsg NLGL0010SMsg
     * @param glgbCmpyCd String
     * @param selectCnt int
     */
    public static void getSODtlInstn(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg, String glgbCmpyCd, int selectCnt) {

        sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).xxChkBox_A1.setValue(cMsg.A.no(selectCnt).xxChkBox_A1.getValue());
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_PARAM_WMS_SO_ID, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSoId_A1.getValue());
        ssmParam.put(DB_PARAM_WH_CD, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).whCd_A1.getValue());
        ssmParam.put(DB_PARAM_WMS_SQ_NUM, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSqNum_A1.getValue());
        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getSODtlInstn(sMsg, ssmParam);

        if (!ssmResult.isCodeNotFound()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.D.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.D.length();
            }

            int iRoopCount = 0;
            for (int i = 0; i < sMsg.D.getValidCount(); i++) {
                if (i == queryResCnt) {
                    break;
                }
                StringBuffer sb = new StringBuffer();
                if (ZYPCommonFunc.hasValue(sMsg.D.no(i).inbdSoMsgTxt_D1)) {
                    sb.append(sMsg.D.no(i).inbdSoMsgTxt_D1.getValue());
                    sb.append(",");
                }
                if (ZYPCommonFunc.hasValue(sMsg.D.no(i).inbdSoMsgTxt_D2)) {
                    sb.append(sMsg.D.no(i).inbdSoMsgTxt_D2.getValue());
                    sb.append(",");
                }
                if (ZYPCommonFunc.hasValue(sMsg.D.no(i).inbdSoMsgTxt_D3)) {
                    sb.append(sMsg.D.no(i).inbdSoMsgTxt_D3.getValue());
                    sb.append(",");
                }
                if (ZYPCommonFunc.hasValue(sMsg.D.no(i).inbdSoMsgTxt_D4)) {
                    sb.append(sMsg.D.no(i).inbdSoMsgTxt_D4.getValue());
                    sb.append(",");
                }
                if (sb.length() > 0) {
                    if (",".equals(sb.substring(sb.length() - 1, sb.length()))) {
                        sMsg.D.no(i).xxMsgTxt_D1.setValue(sb.substring(0, sb.length() - 1));
                    } else {
                        sMsg.D.no(i).xxMsgTxt_D1.setValue(sb.toString().trim());
                    }
                } else {
                    sMsg.D.no(i).xxMsgTxt_D1.setValue("");
                }
                EZDMsg.copy(sMsg.D.no(i), null, cMsg.D.no(i), null);
                iRoopCount++;
            }
            sMsg.D.setValidCount(iRoopCount);
            cMsg.D.setValidCount(iRoopCount);
        }
    }

    /**
     * The method explanation: This method gets/sets Detail of SO
     * Status Tab.
     * @param cMsg NLGL0010CMsg
     * @param sMsg NLGL0010SMsg
     * @param glblCmpyCd String
     * @param selectCnt int
     */
    public static void getSODtlDtl(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg, String glblCmpyCd, int selectCnt) {

        sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).xxChkBox_A1.setValue(cMsg.A.no(selectCnt).xxChkBox_A1.getValue());
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_WMS_SO_ID, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSoId_A1.getValue());
        ssmParam.put(DB_PARAM_WH_CD, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).whCd_A1.getValue());
        ssmParam.put(DB_PARAM_WMS_SQ_NUM, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSqNum_A1.getValue());
        ssmParam.put(DB_PARAM_WMS_UOM_CD_EA, WMS_UOM.EACH);
        String flg = ZYPCodeDataUtil.getVarCharConstValue(WMS_PACK_CD_SET_OWNER_CD_FLG, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(flg)) {
            ssmParam.put(DB_PARAM_WMS_PACK_CD_SET_OWNER_CD_FLG, flg);
        } else {
            ssmParam.put(DB_PARAM_WMS_PACK_CD_SET_OWNER_CD_FLG, ZYPConstant.FLG_OFF_N);
        }

        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getSODtlDtl(sMsg, ssmParam);

        if (!ssmResult.isCodeNotFound()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.E.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.E.length();
            }

            int iRoopCount = 0;
            for (int i = 0; i < sMsg.E.getValidCount(); i++) {
                if (i == queryResCnt) {
                    break;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).mdseDescShortTxt_E2)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).mdseDescShortTxt_E2, sMsg.E.no(i).mdseDescShortTxt_E1.getValue());
                }
                //check 3PL Wh
                Map<String, Object> params = new HashMap<String, Object>();
                params.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                params.put(DB_PARAM_INVTY_OWNR_CD, cMsg.whCd_02.getValue());
                S21SsmEZDResult result = NLGL0010Query.getInstance().get3PLWhCode(params);
                if (!result.isCodeNotFound()) {
                    ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).wmsUpcCd_E1, sMsg.E.no(i).upcCd_E1.getValue());
                }
                EZDMsg.copy(sMsg.E.no(i), null, cMsg.E.no(i), null);
                iRoopCount++;
            }
            sMsg.E.setValidCount(iRoopCount);
            cMsg.E.setValidCount(iRoopCount);
        }
    }

    /**
     * The method explanation: This method gets/sets Detail of SO
     * Status Tab.
     * @param cMsg NLGL0010CMsg
     * @param sMsg NLGL0010SMsg
     * @param glgbCmpyCd String
     * @param selectCnt int
     */
    public static void getSODtlUpldTrn(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg, String glgbCmpyCd, int selectCnt) {

        sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).xxChkBox_A1.setValue(cMsg.A.no(selectCnt).xxChkBox_A1.getValue());
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_PARAM_WMS_SO_ID, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSoId_A1.getValue());
        ssmParam.put(DB_PARAM_WH_CD, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).whCd_A1.getValue());
        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getSODtlUpldTrn(sMsg, ssmParam);

        if (!ssmResult.isCodeNotFound()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.F.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.F.length();
            }

            int iRoopCount = 0;
            for (int i = 0; i < sMsg.F.getValidCount(); i++) {
                if (i == queryResCnt) {
                    break;
                }

                if (ZYPCommonFunc.hasValue(sMsg.F.no(i).wmsTrxDtTmTs_F1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.F.no(i).xxDtTm_F1 //
                            , changeDateformat(sMsg.F.no(i).wmsTrxDtTmTs_F1.getValue(), SET_MMSS));
                }

                if (ZYPCommonFunc.hasValue(sMsg.F.no(i).estDockDtTmTs_F1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.F.no(i).xxDtTm_F2 //
                            , changeDateformat(sMsg.F.no(i).estDockDtTmTs_F1.getValue(), SET_MMSS));
                }

                if (ZYPCommonFunc.hasValue(sMsg.F.no(i).ezInTime_F1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.F.no(i).xxDtTm_F3 //
                            , changeDateformat(sMsg.F.no(i).ezInTime_F1.getValue(), SET_MMSS));
                }
                EZDMsg.copy(sMsg.F.no(i), null, cMsg.F.no(i), null);
                iRoopCount++;
            }
            sMsg.F.setValidCount(iRoopCount);
            cMsg.F.setValidCount(iRoopCount);
        }
    }

    /**
     * The method explanation: This method gets/sets ASN of SO Status
     * Tab.
     * @param cMsg NLGL0010CMsg
     * @param sMsg NLGL0010SMsg
     * @param glgbCmpyCd String
     * @param selectCnt int
     */
    public static void getSODtlAsn(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg, String glgbCmpyCd, int selectCnt) {

        sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).xxChkBox_A1.setValue(cMsg.A.no(selectCnt).xxChkBox_A1.getValue());
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_PARAM_WMS_SO_ID, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSoId_A1.getValue());
        ssmParam.put(DB_PARAM_WH_CD, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).whCd_A1.getValue());
        ssmParam.put(DB_PARAM_WMS_CONSL_FLG, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsConslFlg_A1.getValue());
        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getSODtlAsn(sMsg, ssmParam);

        if (!ssmResult.isCodeNotFound()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.G.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.G.length();
            }

            int iRoopCount = 0;
            for (int i = 0; i < sMsg.G.getValidCount(); i++) {
                if (i == queryResCnt) {
                    break;
                }

                if (ZYPCommonFunc.hasValue(sMsg.G.no(i).wmsShipDtTmTs_G1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.G.no(i).xxDtTm_G1 //
                            , changeDateformat(sMsg.G.no(i).wmsShipDtTmTs_G1.getValue(), SET_MMSS));
                }

                if (ZYPCommonFunc.hasValue(sMsg.G.no(i).ezInTime_G1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.G.no(i).xxDtTm_G2 //
                            , changeDateformat(sMsg.G.no(i).ezInTime_G1.getValue(), SET_MMSS));
                }

                if (ZYPCommonFunc.hasValue(sMsg.G.no(i).wmsProcDtTmTs_G1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.G.no(i).xxDtTm_G3 //
                            , changeDateformat(sMsg.G.no(i).wmsProcDtTmTs_G1.getValue(), SET_MMSS));
                }
                EZDMsg.copy(sMsg.G.no(i), null, cMsg.G.no(i), null);
                iRoopCount++;
            }
            sMsg.G.setValidCount(iRoopCount);
            cMsg.G.setValidCount(iRoopCount);
        }
    }

    /**
     * The method explanation: This method gets/sets ASN Error of SO
     * Status Tab.
     * @param cMsg NLGL0010CMsg
     * @param sMsg NLGL0010SMsg
     * @param glgbCmpyCd String
     * @param selectCnt int
     */
    public static void getSODtlAsnErr(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg, String glgbCmpyCd, int selectCnt) {

        sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).xxChkBox_A1.setValue(cMsg.A.no(selectCnt).xxChkBox_A1.getValue());
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_PARAM_WMS_SO_ID, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSoId_A1.getValue());
        ssmParam.put(DB_PARAM_WH_CD, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).whCd_A1.getValue());
        ssmParam.put(DB_PARAM_WMS_TASK_CD_SHIP, WMS_TASK.SHIP);
        ssmParam.put(DB_PARAM_WMS_TASK_CD_ASN, WMS_TASK.ASN);
        ssmParam.put(DB_PARAM_PROC_STS_CD_ERROR, PROC_STS.ERROR);
        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getSODtlAsnErr(sMsg, ssmParam);

        if (!ssmResult.isCodeNotFound()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.H.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.H.length();
            }

            int iRoopCount = 0;
            for (int i = 0; i < sMsg.H.getValidCount(); i++) {
                if (i == queryResCnt) {
                    break;
                }

                if (ZYPCommonFunc.hasValue(sMsg.H.no(i).errMsgCd_H1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.H.no(i).xxLogDtlTxt_H1 //
                            , S21MessageFunc.clspGetMessage(sMsg.H.no(i).errMsgCd_H1.getValue()));
                }

                if (ZYPCommonFunc.hasValue(sMsg.H.no(i).ezInTime_H1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.H.no(i).xxDtTm_H1 //
                            , changeDateformat(sMsg.H.no(i).ezInTime_H1.getValue(), SET_MMSS));
                }

                if (ZYPCommonFunc.hasValue(sMsg.H.no(i).ezUpTime_H1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.H.no(i).xxDtTm_H2 //
                            , changeDateformat(sMsg.H.no(i).ezUpTime_H1.getValue(), SET_MMSS));
                }
                EZDMsg.copy(sMsg.H.no(i), null, cMsg.H.no(i), null);
                iRoopCount++;
            }
            sMsg.H.setValidCount(iRoopCount);
            cMsg.H.setValidCount(iRoopCount);
        }
    }

    /**
     * The method explanation: This method gets/sets HDR of SO
     * DownLoad Tab.
     * @param cMsg NLGL0010CMsg
     * @param sMsg NLGL0010SMsg
     * @param glgbCmpyCd String
     * @param selectCnt int
     */
    public static void getSOHdrOfDNLD(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg, String glgbCmpyCd, int selectCnt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_PARAM_WMS_SO_ID, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSoId_A1.getValue());
        ssmParam.put(DB_PARAM_WH_CD, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).whCd_A1.getValue());
        ssmParam.put(DB_PARAM_WMS_SQ_NUM, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSqNum_A1.getValue());
        ssmParam.put(DB_PARAM_SCE_ORD_TP_CD, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).sceOrdTpCd_A1.getValue());
        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getSOHdr(sMsg, ssmParam);

        if (!ssmResult.isCodeNotFound()) {

            int rowCnt = sMsg.B.getValidCount();

            if (sMsg.B.getValidCount() > cMsg.B.length()) {
                rowCnt = cMsg.B.length();
            }

            for (int i = 0; i < rowCnt; i++) {
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
                EZDMsg.copy(cMsg.B.no(i), null, sMsg.I.no(i), null);
            }

            for (int i = 0; i < rowCnt; i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).wmsSoId_I1, cMsg.B.no(i).wmsSoId_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).fill40Txt_I1, cMsg.B.no(i).fill40Txt_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).wmsOrdNum_I1, cMsg.B.no(i).wmsOrdNum_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).altDocNum_I1, cMsg.B.no(i).altDocNum_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).wmsPrintDtTmTs_I1, cMsg.B.no(i).wmsPrintDtTmTs_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).estShipDtTmTs_I1, cMsg.B.no(i).estShipDtTmTs_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).wmsRqstDtTmTs_I1, cMsg.B.no(i).wmsRqstDtTmTs_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).shipToCustCd_I1, cMsg.B.no(i).shipToCustCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).chrgToCustCd_I1, cMsg.B.no(i).chrgToCustCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).custOrdNum_I1, cMsg.B.no(i).custOrdNum_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).frtOutCd_I1, cMsg.B.no(i).frtOutCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).soShipViaCd_I1, cMsg.B.no(i).soShipViaCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).ediTrnspTpCd_I1, cMsg.B.no(i).ediTrnspTpCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).wmsDeptCd_I1, cMsg.B.no(i).wmsDeptCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).indAsnFlg_I1, cMsg.B.no(i).indAsnFlg_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).indScc14Flg_I1, cMsg.B.no(i).indScc14Flg_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).indUccFlg_I1, cMsg.B.no(i).indUccFlg_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).wmsConslFlg_I1, cMsg.B.no(i).wmsConslFlg_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).conslSoId_I1, cMsg.B.no(i).conslSoId_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).totShipPrcAmtNum_I1, cMsg.B.no(i).totShipPrcAmtNum_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).wmsResrcTxt_I1, cMsg.B.no(i).wmsResrcTxt_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).shipDtTmTs_I1, cMsg.B.no(i).shipDtTmTs_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).wmsCancDtTmTs_I1, cMsg.B.no(i).wmsCancDtTmTs_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).mixPltPltNoteTxt_I1, cMsg.B.no(i).mixPltPltNoteTxt_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).asgShipViaCd_I1, cMsg.B.no(i).asgShipViaCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).asgPrtyCd_I1, cMsg.B.no(i).asgPrtyCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).indSgnReqFlg_I1, cMsg.B.no(i).indSgnReqFlg_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).rtrnLbCd_I1, cMsg.B.no(i).rtrnLbCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).rtlWhCd_I1, cMsg.B.no(i).rtlWhCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).invtyOwnrCd_I1, cMsg.B.no(i).invtyOwnrCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).schdDelyDt_I1, cMsg.B.no(i).schdDelyDt_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).carrCd_I1, cMsg.B.no(i).carrCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).shpgSvcLvlCd_I1, cMsg.B.no(i).shpgSvcLvlCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).rtrnItemInclFlg_I1, cMsg.B.no(i).rtrnItemInclFlg_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).svcConfigMstrPk_I1, cMsg.B.no(i).svcConfigMstrPk_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).asmReqFlg_I1, cMsg.B.no(i).asmReqFlg_B1);

                // sets MixPack
                sMsg.I.no(i).mixPltPltNoteTxt_I1.setValue(NLGL0010CommonLogic.getMixPack(sMsg, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).whCd_A1.getValue(), //
                        sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSoId_A1.getValue(), glgbCmpyCd, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSqNum_A1.getValue()));

                if (ZYPCommonFunc.hasValue(sMsg.I.no(i).wmsPrintDtTmTs_I1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).xxDtTm_I1 //
                            , changeDateformat(sMsg.I.no(i).wmsPrintDtTmTs_I1.getValue(), SET_MMSS));
                }
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).wmsPrintDtTmTs_I1, cMsg.B.no(i).wmsPrintDtTmTs_B1);

                if (ZYPCommonFunc.hasValue(sMsg.I.no(i).wmsRqstDtTmTs_I1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).xxDtTm_I2 //
                            , changeDateformat(sMsg.I.no(i).wmsRqstDtTmTs_I1.getValue(), SET_MMSS));
                }
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).wmsRqstDtTmTs_I1, cMsg.B.no(i).wmsRqstDtTmTs_B1);

                if (ZYPCommonFunc.hasValue(sMsg.I.no(i).estShipDtTmTs_I1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).xxDtTm_I3 //
                            , changeDateformat(sMsg.I.no(i).estShipDtTmTs_I1.getValue(), SET_MMSS));
                }
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).estShipDtTmTs_I1, cMsg.B.no(i).estShipDtTmTs_B1);

                if (ZYPCommonFunc.hasValue(sMsg.I.no(i).shipDtTmTs_I1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).xxDtTm_I4 //
                            , changeDateformat(sMsg.I.no(i).shipDtTmTs_I1.getValue(), SET_MMSS));
                }
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).shipDtTmTs_I1, cMsg.B.no(i).shipDtTmTs_B1);

                if (ZYPCommonFunc.hasValue(sMsg.I.no(i).wmsCancDtTmTs_I1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).xxDtTm_I5 //
                            , changeDateformat(sMsg.I.no(i).wmsCancDtTmTs_I1.getValue(), SET_MMSS));
                }
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).wmsCancDtTmTs_I1, cMsg.B.no(i).wmsCancDtTmTs_B1);
                EZDMsg.copy(sMsg.I, null, cMsg.I, null);
            }
            sMsg.I.setValidCount(rowCnt);
            cMsg.B.setValidCount(rowCnt);
            cMsg.I.setValidCount(rowCnt);
        }
    }

    /**
     * The method explanation: This method gets/sets Address of SO
     * DownLoad Tab.
     * @param cMsg NLGL0010CMsg
     * @param sMsg NLGL0010SMsg
     * @param glgbCmpyCd String
     * @param selectCnt int
     */
    public static void getSODtlAddrOfDNLD(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg, String glgbCmpyCd, int selectCnt) {

        sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).xxChkBox_A1.setValue(cMsg.A.no(selectCnt).xxChkBox_A1.getValue());
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_PARAM_WMS_SO_ID, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSoId_A1.getValue());
        ssmParam.put(DB_PARAM_WH_CD, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).whCd_A1.getValue());
        ssmParam.put(DB_PARAM_WMS_SQ_NUM, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSqNum_A1.getValue());
        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getSODtlAddr(sMsg, ssmParam);

        if (!ssmResult.isCodeNotFound()) {

            int rowCnt = sMsg.C.getValidCount();

            if (sMsg.C.getValidCount() > cMsg.C.length()) {
                rowCnt = cMsg.C.length();
            }

            for (int i = 0; i < rowCnt; i++) {
                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i), null);
                EZDMsg.copy(cMsg.C.no(i), null, sMsg.L.no(i), null);
            }

            for (int i = 0; i < rowCnt; i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.L.no(i).wmsShipToNm_L1, cMsg.C.no(i).wmsShipToNm_C1);
                ZYPEZDItemValueSetter.setValue(sMsg.L.no(i).wmsShipToNm_L2, cMsg.C.no(i).wmsShipToNm_C2);
                ZYPEZDItemValueSetter.setValue(sMsg.L.no(i).wmsShipToCtacNm_L1, cMsg.C.no(i).wmsShipToCtacNm_C1);
                ZYPEZDItemValueSetter.setValue(sMsg.L.no(i).fill256Txt_L1, cMsg.C.no(i).fill256Txt_C1);
                ZYPEZDItemValueSetter.setValue(sMsg.L.no(i).ctyAddr_L1, cMsg.C.no(i).ctyAddr_C1);
                ZYPEZDItemValueSetter.setValue(sMsg.L.no(i).stCd_L1, cMsg.C.no(i).stCd_C1);
                ZYPEZDItemValueSetter.setValue(sMsg.L.no(i).postCd_L1, cMsg.C.no(i).postCd_C1);
                ZYPEZDItemValueSetter.setValue(sMsg.L.no(i).shipToCtacNum_L1, cMsg.C.no(i).shipToCtacNum_C1);
                EZDMsg.copy(sMsg.L, null, cMsg.L, null);
            }
            sMsg.L.setValidCount(rowCnt);
            cMsg.C.setValidCount(rowCnt);
            cMsg.L.setValidCount(rowCnt);
        }
    }

    /**
     * The method explanation: This method gets/sets Address of SO
     * DownLoad Tab.
     * @param cMsg NLGL0010CMsg
     * @param sMsg NLGL0010SMsg
     * @param glgbCmpyCd String
     * @param selectCnt int
     */
    public static void getSODtlInstnOfDNLD(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg, String glgbCmpyCd, int selectCnt) {

        sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).xxChkBox_A1.setValue(cMsg.A.no(selectCnt).xxChkBox_A1.getValue());
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_PARAM_WMS_SO_ID, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSoId_A1.getValue());
        ssmParam.put(DB_PARAM_WH_CD, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).whCd_A1.getValue());
        ssmParam.put(DB_PARAM_WMS_SQ_NUM, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSqNum_A1.getValue());
        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getSODtlInstn(sMsg, ssmParam);

        if (!ssmResult.isCodeNotFound()) {

            int rowCnt = sMsg.D.getValidCount();

            if (sMsg.D.getValidCount() > cMsg.D.length()) {
                rowCnt = cMsg.D.length();
            }

            for (int i = 0; i < rowCnt; i++) {
                EZDMsg.copy(sMsg.D.no(i), null, cMsg.D.no(i), null);
                EZDMsg.copy(cMsg.D.no(i), null, sMsg.M.no(i), null);
            }

            for (int i = 0; i < rowCnt; i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.M.no(i).xxMsgTxt_M1, cMsg.D.no(i).xxMsgTxt_D1);
                EZDMsg.copy(sMsg.M, null, cMsg.M, null);
            }
            sMsg.M.setValidCount(rowCnt);
            cMsg.D.setValidCount(rowCnt);
            cMsg.M.setValidCount(rowCnt);
        }
    }

    /**
     * The method explanation: This method gets/sets detail of SO
     * DownLoad Tab.
     * @param cMsg NLGL0010CMsg
     * @param sMsg NLGL0010SMsg
     * @param glgbCmpyCd String
     * @param selectCnt int
     */
    public static void getSODtlDtlOfDNLD(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg, String glgbCmpyCd, int selectCnt) {

        sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).xxChkBox_A1.setValue(cMsg.A.no(selectCnt).xxChkBox_A1.getValue());
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_PARAM_WMS_SO_ID, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSoId_A1.getValue());
        ssmParam.put(DB_PARAM_WH_CD, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).whCd_A1.getValue());
        ssmParam.put(DB_PARAM_WMS_SQ_NUM, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSqNum_A1.getValue());
        ssmParam.put(DB_PARAM_WMS_UOM_CD_EA, WMS_UOM.EACH);
        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getSODtlDtl(sMsg, ssmParam);

        if (!ssmResult.isCodeNotFound()) {

            int rowCnt = sMsg.E.getValidCount();

            if (sMsg.E.getValidCount() > cMsg.E.length()) {
                rowCnt = cMsg.E.length();
            }

            for (int i = 0; i < rowCnt; i++) {
                EZDMsg.copy(sMsg.E.no(i), null, cMsg.E.no(i), null);
                EZDMsg.copy(cMsg.E.no(i), null, sMsg.N.no(i), null);
            }

            for (int i = 0; i < rowCnt; i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).wmsLineNum_N1, cMsg.E.no(i).wmsLineNum_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).wmsMdseCd_N1, cMsg.E.no(i).wmsMdseCd_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).s80StkStsCd_N1, cMsg.E.no(i).s80StkStsCd_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).s80StkStsCdToCd_N1, cMsg.E.no(i).s80StkStsCdToCd_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).mdseDescShortTxt_N1, cMsg.E.no(i).mdseDescShortTxt_E2);
                if (!ZYPCommonFunc.hasValue(cMsg.E.no(i).mdseDescShortTxt_E2)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).mdseDescShortTxt_N1, sMsg.E.no(i).mdseDescShortTxt_E1);
                }
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).wmsShipQty_N1, cMsg.E.no(i).wmsShipQty_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).indSerId_N1, cMsg.E.no(i).indSerId_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).mdseCdSetCd_N1, cMsg.E.no(i).mdseCdSetCd_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).shipSetQty_N1, cMsg.E.no(i).shipSetQty_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).custMdseCd_N1, cMsg.E.no(i).custMdseCd_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).origSoId_N1, cMsg.E.no(i).origSoId_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).origLineNum_N1, cMsg.E.no(i).origLineNum_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).totWtAmtNum_N1, cMsg.E.no(i).totWtAmtNum_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).totVolAmtNum_N1, cMsg.E.no(i).totVolAmtNum_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).unitCrtnQty_N1, cMsg.E.no(i).unitCrtnQty_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).unitPltQty_N1, cMsg.E.no(i).unitPltQty_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).estCseAmtNum_N1, cMsg.E.no(i).estCseAmtNum_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).estPltAmtNum_N1, cMsg.E.no(i).estPltAmtNum_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).wmsUpcCd_N1, cMsg.E.no(i).wmsUpcCd_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).fill20Txt_N1, cMsg.E.no(i).fill20Txt_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).estCseAmtNum_N2, cMsg.E.no(i).estCseAmtNum_E2);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).estPltAmtNum_N2, cMsg.E.no(i).estPltAmtNum_E2);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).fill40Txt_N1, cMsg.E.no(i).fill40Txt_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).svcConfigMstrPk_N1, cMsg.E.no(i).svcConfigMstrPk_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).backOrdFlg_N1, cMsg.E.no(i).backOrdFlg_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).backOrdImpctTpCd_N1, cMsg.E.no(i).backOrdImpctTpCd_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).rmvConfigFlg_N1, cMsg.E.no(i).rmvConfigFlg_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).indConfigFlg_N1, cMsg.E.no(i).indConfigFlg_E1);
                ZYPEZDItemValueSetter.setValue(sMsg.N.no(i).serNum_N1, cMsg.E.no(i).serNum_E1);
                EZDMsg.copy(sMsg.N, null, cMsg.N, null);
            }
            sMsg.N.setValidCount(rowCnt);
            cMsg.E.setValidCount(rowCnt);
            cMsg.N.setValidCount(rowCnt);
        }
    }

    /**
     * The method explanation: This method gets/sets upload date of SO
     * Upload Tab.
     * @param cMsg NLGL0010CMsg
     * @param sMsg NLGL0010SMsg
     * @param glgbCmpyCd String
     * @param selectCnt int
     */
    public static void getUploadEditList(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg, String glgbCmpyCd, int selectCnt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_PARAM_WMS_SO_ID, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSoId_A1.getValue());
        ssmParam.put(DB_PARAM_WH_CD, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).whCd_A1.getValue());
        ssmParam.put(DB_PARAM_WMS_SQ_NUM, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSqNum_A1.getValue());
        ssmParam.put(DB_PARAM_PROC_STS_CD_IN_COMPLEATED, PROC_STS.IN_COMPLETED);
        ssmParam.put(DB_PARAM_PROC_STS_CD_COMPLEATED, PROC_STS.COMPLEATED);
        ssmParam.put(DB_PARAM_PROC_STS_CD_ERROR, PROC_STS.ERROR);
        ssmParam.put(DB_PARAM_PROCESSED, PROCESSED);
        ssmParam.put(DB_PARAM_UN_PROCESSED, UN_PROCESSED);
        ssmParam.put(DB_PARAM_ERROR, ERROR);
        ssmParam.put(DB_PARAM_WMS_TASK_CD_SHIP, WMS_TASK.SHIP);
        ssmParam.put(DB_PARAM_WMS_TASK_CD_ASN, WMS_TASK.ASN);

        // Set ssm parameter to select 'ALL' or 'ASN' or other data.
        String wmsTaskCd = cMsg.wmsTaskCd_O4.getValue();
        if (!ZYPCommonFunc.hasValue(cMsg.wmsTaskCd_O4.getValue())) {
            wmsTaskCd = VAL_WMS_TASK_CD_ALL;
        }
        ssmParam.put(DB_PARAM_WMS_TASK_CD, wmsTaskCd);

        if (VAL_WMS_TASK_CD_ALL.equals(wmsTaskCd) || WMS_TASK.ASN.equals(wmsTaskCd)) {
            ssmParam.put(DB_PARAM_IS_SPECIFIC_TASK_COND, ZYPConstant.FLG_OFF_N);
            ssmParam.put(DB_PARAM_IS_SEARCH_TASK_ASN, ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put(DB_PARAM_IS_SPECIFIC_TASK_COND, ZYPConstant.FLG_ON_Y);
            ssmParam.put(DB_PARAM_IS_SEARCH_TASK_ASN, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_O2.getValue()) && //
                !ZYPCommonFunc.hasValue(cMsg.xxChkBox_O3.getValue())) {
            ssmParam.put(DB_PARAM_EZCANCELFLAG, ZYPConstant.FLG_OFF_0);
        } else if (!ZYPCommonFunc.hasValue(cMsg.xxChkBox_O2.getValue()) && //
                ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_O3.getValue())) {
            ssmParam.put(DB_PARAM_EZCANCELFLAG, ZYPConstant.FLG_ON_1);
        } else {
            ssmParam.put(DB_PARAM_EZCANCELFLAG, "");
        }

        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getUpdateEdit(sMsg, ssmParam);

        if (!ssmResult.isCodeNotFound()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.O.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.O.length();
            }

            int iRoopCount = 0;
            for (int i = 0; i < sMsg.O.getValidCount(); i++) {
                if (i == queryResCnt) {
                    break;
                }

                if (ZYPConstant.FLG_OFF_0.equals(sMsg.O.no(i).ezCancelFlag_O1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(i).xxExstFlg_O1, VAL_Actv_);
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(i).xxExstFlg_O1, VAL_InActv_);
                }

                createWmsTaskCodePulldownList(cMsg.O.no(i).wmsTaskCd_O1, cMsg.O.no(i).xxEdtCdNm_O1, glgbCmpyCd, false);
                ZYPCodeDataUtil.createCodePulldownList(WMS_ORD_STS.class, cMsg.O.no(i).wmsOrdStsCd_O1, cMsg.O.no(i).xxEdtCdNm_O2);
                ZYPCodeDataUtil.createCodePulldownList(WMS_STK_STS.class, cMsg.O.no(i).wmsStkStsCd_O1, cMsg.O.no(i).xxEdtCdNm_O3);
                NLGL0010CommonLogic.createWmsOrdTpCodePulldownList(cMsg.O.no(i).otbdOrdTpCd_O1, cMsg.O.no(i).xxEdtCdNm_O4, glgbCmpyCd, true);
                NLGL0010CommonLogic.createWmsOrdTpCodePulldownList(cMsg.O.no(i).inbdOrdTpCd_O1, cMsg.O.no(i).xxEdtCdNm_O5, glgbCmpyCd, false);

                if (ZYPCommonFunc.hasValue(sMsg.O.no(i).wmsTrxDtTmTs_O1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(i).xxDtTm_O1 //
                            , changeDateformat(sMsg.O.no(i).wmsTrxDtTmTs_O1.getValue(), SET_MMSS));
                }
                if (ZYPCommonFunc.hasValue(sMsg.O.no(i).estDockDtTmTs_O1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(i).xxDt10Dt_O1 //
                            , (ZYPDateUtil.DateFormatter(sMsg.O.no(i).estDockDtTmTs_O1.getValue() //
                                    , YYYYMMDDHHMMSS_BEFORE, FMT_YYYYMMDD)));
                }
                if (ZYPCommonFunc.hasValue(sMsg.O.no(i).ezInTime_O1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(i).xxDtTm_O2 //
                            , changeDateformat(sMsg.O.no(i).ezInTime_O1.getValue(), SET_MMSS));
                }
                EZDMsg.copy(sMsg.O.no(i), null, cMsg.O.no(i), null);
                iRoopCount++;
            }
            sMsg.O.setValidCount(iRoopCount);
            cMsg.O.setValidCount(iRoopCount);
        }
        ZYPEZDItemValueSetter.setValue(cMsg.wmsSoId_O1, sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).wmsSoId_A1.getValue());
    }

    /**
     * The method explanation: This method sets to set Flag Off of
     * Check Box.
     * @param cMsg NLGL0010CMsg
     * @param sMsg NLGL0010SMsg
     */
    public static void setUnChk_SOList(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        for (int selectCnt = 0; selectCnt < cMsg.A.getValidCount(); selectCnt++) {
            sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + selectCnt - 1).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * The method explanation: This method confirm whether move or
     * not.
     * @param cMsg NLGL0010CMsg
     * @param strTabName String
     * @return boolean
     */
    public static boolean isMoveTab(NLGL0010CMsg cMsg, String strTabName) {

        if (TAB_SO_DNLD_EDT.equals(cMsg.xxDplyTab.getValue()) && strTabName.equals(TAB_SO_UPLD_EDT)) {
            return false;
        } else if (TAB_SO_UPLD_EDT.equals(cMsg.xxDplyTab.getValue()) && strTabName.equals(TAB_SO_DNLD_EDT)) {
            return false;
        }
        return true;
    }

    /**
     * The method explanation: This method create Send Type
     * PullDownList of DownLoad Tab.
     * @param cMsg NLGL0010CMsg
     */
    public static void createSendTypePulldownList(NLGL0010CMsg cMsg) {

        cMsg.xxTpCd_J1.clear();
        cMsg.xxEdtCdNm_J0.clear();

        for (int i = 0; i < SEND_TYPE_CODE_LIST.length; i++) {
            cMsg.xxTpCd_J1.no(i).setValue(SEND_TYPE_CODE_LIST[i]);
            cMsg.xxEdtCdNm_J0.no(i).setValue(SEND_TYPE_NAME_LIST[i]);
        }
    }

    /**
     * The method explanation: This method create SoWorkData of Header
     * Data.
     * @param soHdrTMsg WMS_INBD_SO_HDRTMsg
     * @param shipViaRteTMsg WMS_SHIP_VIA_RTE_MAPTMsg
     * @param frtOutTMsg WMS_FRT_OUTTMsg
     * @param cMsg NLGL0010CMsg
     * @param iSelCnt int
     * @param strTrxSeq String
     * @param strShipOdr String
     * @return String
     */
    public static String createSoToWmsDataTxtFromHdr(WMS_INBD_SO_HDRTMsg soHdrTMsg, WMS_SHIP_VIA_RTE_MAPTMsg shipViaRteTMsg, WMS_FRT_OUTTMsg frtOutTMsg, NLGL0010CMsg cMsg, int iSelCnt, String strTrxSeq, String strShipOdr) {

        boolean isCopy = false;
        if (TP_CD_COPY.equals(cMsg.xxTpCd_J2.getValue())) {
            isCopy = true;
        }
        StringBuilder strBilReturn = new StringBuilder();
        if (isCopy) {
            //strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_J1.getValue(), LEN_WH_CD));
        } else {
            //strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.whCd.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.rtlWhCd.getValue(), LEN_WH_CD));
        }
        strBilReturn.append(strTrxSeq);
        strBilReturn.append(RECORD_TYPE_VALUE);
        strBilReturn.append(DTL_TP_HDR);
        strBilReturn.append(SPACE);
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.wmsCmpyCd.getValue(), LEN_WMS_CMPY_CD));
        if (isCopy) {
            //strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_J1.getValue(), LEN_WH_CD));
        } else {
            //strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.whCd.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.rtlWhCd.getValue(), LEN_WH_CD));
        }
        strBilReturn.append(convFixedLgLeftAlign(strShipOdr, LEN_WMS_SO_NUM));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.wmsOrdNum.getValue(), LEN_WMS_ORD_NUM));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.altDocNum.getValue(), LEN_ALT_DOC_NUM));
        if (isCopy) {
            strBilReturn.append(convFixedLgLeftAlign(cMsg.custOrdNum_J1.getValue(), LEN_CUST_ORD_NUM));
        } else {
            strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.custOrdNum.getValue(), LEN_CUST_ORD_NUM));
        }
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.chrgToCustCd.getValue(), LEN_CHRG_TO_CUST_CD));

        if (isCopy) {
            String wmsOrdTpCd = soHdrTMsg.wmsOrdTpCd.getValue();
            if (WMS_ORD_TP.OUTBOUND_STOCK_STATUS_CHANGE.equals(wmsOrdTpCd) || //
                    WMS_ORD_TP.OUTBOUND_ITEM_CHANGE.equals(wmsOrdTpCd) || //
                    WMS_ORD_TP.OUTBOUND_DIPOSAL.equals(wmsOrdTpCd)) {
//                strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_BILL_TO_CUST_CD));
//                strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_SHIP_TO_CUST_CD));
                  strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_J1.getValue(), LEN_BILL_TO_CUST_CD));
                  strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_J1.getValue(), LEN_SHIP_TO_CUST_CD));
            } else {
                strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.billToCustCd.getValue(), LEN_BILL_TO_CUST_CD));
                strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.shipToCustCd.getValue(), LEN_SHIP_TO_CUST_CD));
            }
        } else {
            strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.billToCustCd.getValue(), LEN_BILL_TO_CUST_CD));
            strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.shipToCustCd.getValue(), LEN_SHIP_TO_CUST_CD));
        }

        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.wmsPrtyCd.getValue(), LEN_WMS_PRTY_CD));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.wmsOrdTpCd.getValue(), LEN_WMS_ORD_TP_CD));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.wmsTrxCd.getValue(), LEN_WMS_TRX_CD));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.wmsOrdSrcCd.getValue(), LEN_WMS_ORD_SRC_CD));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.wmsSoStsCd.getValue(), LEN_WMS_SO_STS_CD));
        if (isCopy) {
            strBilReturn.append(convFixedLgLeftAlign(cMsg.tplSvcLvlCd_J2.getValue(), LEN_WMS_SHIP_VIA_TP_CD));
        } else {
            strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.soShipViaCd.getValue(), LEN_WMS_SHIP_VIA_TP_CD));
        }
        if (isCopy) {
            if (shipViaRteTMsg == null || !ZYPCommonFunc.hasValue(shipViaRteTMsg.wmsDescTxt)) {
                if (ZYPCommonFunc.hasValue(cMsg.tplSvcLvlCd_J2)) {
                    strBilReturn.append(convFixedLgLeftAlign(cMsg.tplSvcLvlCd_J2.getValue(), LEN_WMS_DESC_TXT));
                } else {
                    strBilReturn.append(convFixedLgLeftAlign("", LEN_WMS_DESC_TXT));
                }
            } else {
                strBilReturn.append(convFixedLgLeftAlign(shipViaRteTMsg.wmsDescTxt.getValue(), LEN_WMS_DESC_TXT));
            }
        } else {
            strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.shipViaDescTxt.getValue(), LEN_WMS_DESC_TXT));
        }
        if (isCopy) {
            strBilReturn.append(ZYPDateUtil.getCurrentSystemTime(FMT_YYYY_MM_DD));
            strBilReturn.append(convFixedLgLeftAlign(dateFormat(cMsg.xxRsdDt_J1.getValue(), FMT_YYYYMMDD, FMT_YYYY_MM_DD), LEN_EST_SHIP_DT_TM_TS));
            strBilReturn.append(convFixedLgLeftAlign(dateFormat(cMsg.xxRddDt_J1.getValue(), FMT_YYYYMMDD, FMT_YYYY_MM_DD), LEN_WMS_RQST_DT_TM_TS));
        } else {
            strBilReturn.append(convFixedLgLeftAlign(dateFormat(soHdrTMsg.cratDtTmTs.getValue(), FMT_YYYYMMDDHHMMSS, FMT_YYYY_MM_DD), LEN_CRAT_DT_TM_TS));
            strBilReturn.append(convFixedLgLeftAlign(dateFormat(soHdrTMsg.estShipDtTmTs.getValue(), FMT_YYYYMMDDHHMMSS, FMT_YYYY_MM_DD), LEN_EST_SHIP_DT_TM_TS));
            strBilReturn.append(convFixedLgLeftAlign(dateFormat(soHdrTMsg.wmsRqstDtTmTs.getValue(), FMT_YYYYMMDDHHMMSS, FMT_YYYY_MM_DD), LEN_WMS_RQST_DT_TM_TS));
        }
        strBilReturn.append(ZYPDateUtil.getCurrentSystemTime(FMT_YYYY_MM_DD));
        strBilReturn.append(ZYPDateUtil.getCurrentSystemTime(FMT_HH_MM_SS));
        if (isCopy) {
            strBilReturn.append(convFixedLgLeftAlign(cMsg.wmsFrtOutCd_J2.getValue(), LEN_WMS_FRT_OUT_CD));
            if (frtOutTMsg == null || !ZYPCommonFunc.hasValue(frtOutTMsg.wmsFrtOutDescTxt)) {
                if (ZYPCommonFunc.hasValue(cMsg.wmsFrtOutCd_J2)) {
                    strBilReturn.append(convFixedLgLeftAlign(cMsg.wmsFrtOutCd_J2.getValue(), LEN_WMS_FRT_OUT_DESC_TXT));
                } else {
                    strBilReturn.append(convFixedLgLeftAlign("", LEN_WMS_FRT_OUT_DESC_TXT));
                }
            } else {
                strBilReturn.append(convFixedLgLeftAlign(frtOutTMsg.wmsFrtOutDescTxt.getValue(), LEN_WMS_FRT_OUT_DESC_TXT));
            }
        } else {
            strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.frtOutCd.getValue(), LEN_WMS_FRT_OUT_CD));
            strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.frtOutDescTxt.getValue(), LEN_WMS_FRT_OUT_DESC_TXT));
        }
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.wmsDeptCd.getValue(), LEN_WMS_DEPT_CD));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.payTermCd.getValue(), LEN_PAY_TERM_CD));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.wmsSoCarrCd.getValue(), LEN_WMS_SO_CARR_CD));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.indOtmAddrSwthFlg.getValue(), LEN_IND_OTM_ADD_SWTH_FLG));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.indAsnFlg.getValue(), LEN_IND_ASN_FLG));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.indScc14Flg.getValue(), LEN_IND_SCC_14_FLG));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.indUccFlg.getValue(), LEN_IND_UCC_FLG));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.indMixedPltFlg.getValue(), LEN_IND_MIXED_PLT_FLG));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.indPltLbFlg.getValue(), LEN_IND_PLT_LB_FLG));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.indUccNumFlg.getValue(), LEN_IND_UCC_NUM_FLG));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.indNonAsnFlg.getValue(), LEN_IND_NON_ASN_FLG));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.wmsLbNum.getValue(), LEN_WMS_LB_NUM));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.custStoreNum.getValue(), LEN_CUST_STORE_NUM));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.custDcNum.getValue(), LEN_CUST_DC_NUM));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.wmsCustDeptNum.getValue(), LEN_WMS_CUST_DEPT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soHdrTMsg.totShipPrcAmtNum.getValue(), LEN_TOT_SHIP_PRC_AMT_NUM, LEN_DEC_TOT_SHIP_PRC_AMT_NUM));
        strBilReturn.append(convFixedLgLeftAlign("", 2));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.tpVndCd.getValue(), LEN_TP_VND_CD));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.ediTrnspTpCd.getValue(), LEN_EDI_TRNSP_TP_CD));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.wmsPmtTermCd.getValue(), LEN_WMS_PMT_TERM_CD));
        strBilReturn.append(convFixedLgLeftAlign(dateFormat(soHdrTMsg.cancByDtTmTs.getValue(), FMT_YYYYMMDDHHMMSS, FMT_YYYY_MM_DD), LEN_CANC_BY_DT_TM_TS));
        //strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.indConfigFlg.getValue(), LEN_IND_CONFIG_FLG));

        if (isCopy) {
            strBilReturn.append(convFixedLgLeftAlign(cMsg.asmReqFlg_J1.getValue(), LEN_IND_CONFIG_FLG));
        } else {
            strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.asmReqFlg.getValue(), LEN_IND_CONFIG_FLG));
        }
        strBilReturn.append(convFixedLgLeftAlign(dateFormat(soHdrTMsg.hostOrdDtTmTs.getValue(), FMT_YYYYMMDDHHMMSS, FMT_YYYY_MM_DD), LEN_HOST_ORD_DT_TM_TS));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soHdrTMsg.wmsNetAmtNum.getValue(), LEN_WMS_NET_AMT_NUM, LEN_DEC_WMS_NET_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soHdrTMsg.wmsNetDiscAmtNum.getValue(), LEN_WMS_NET_DISC_AMT_NUM, LEN_DEC_WMS_NET_DISC_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soHdrTMsg.shpgHdlgAmtNum.getValue(), LEN_SHPG_HDLG_AMT_NUM, LEN_DEC_SHPG_HDLG_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soHdrTMsg.totDiscAmtNum.getValue(), LEN_TOT_DISC_AMT_NUM, LEN_DEC_TOT_DISC_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soHdrTMsg.shpgHdlgDiscAmtNum.getValue(), LEN_SHPG_HDLG_DISC_AMT_NUM, LEN_DEC_SHPG_HDLG_DISC_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soHdrTMsg.netTaxAmtNum.getValue(), LEN_NET_TAX_AMT_NUM, LEN_DEC_NET_TAX_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soHdrTMsg.shpgHdlgTaxAmtNum.getValue(), LEN_SHPG_HDLG_TAX_AMT_NUM, LEN_DEC_SHPG_HDLG_TAX_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soHdrTMsg.totTaxAmtNum.getValue(), LEN_TOT_TAX_AMT_NUM, LEN_DEC_TOT_TAX_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soHdrTMsg.totOrdAmtNum.getValue(), LEN_TOT_ORD_AMT_NUM, LEN_DEC_TOT_ORD_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soHdrTMsg.totOrdQty.getValue(), LEN_TOT_ORD_QTY, LEN_DEC_TOT_ORD_QTY));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.indSgnReqFlg.getValue(), LEN_IND_SGN_REQ_FLG));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.billAcctNum.getValue(), LEN_BILL_ACCT_NUM));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.endCustOrdNum.getValue(), LEN_END_CUST_ORD_NUM));
        strBilReturn.append(convFixedLgLeftAlign(soHdrTMsg.altCustOrdNum.getValue(), LEN_ALT_CUST_ORD_NUM));

        strBilReturn.append(convFixedLgLeftAlign("", LEN_HDR_FILLER));

        return strBilReturn.toString();
    }

    /**
     * The method explanation: This method create SoWorkData of Ship
     * To Data.
     * @param soShipToTMsg WMS_INBD_SO_SHIP_TOTMsg
     * @param cMsg NLGL0010CMsg
     * @param iSelCnt int
     * @param strTrxSeq String
     * @param strShipOdr String
     * @return String
     */
    public static String createSoToWmsDataTxtFromShipTo(WMS_INBD_SO_SHIP_TOTMsg soShipToTMsg, NLGL0010CMsg cMsg, int iSelCnt, String strTrxSeq, String strShipOdr) {

        StringBuilder strBilReturn = new StringBuilder();
        if (TP_CD_COPY.equals(cMsg.xxTpCd_J2.getValue())) {
            //strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_J1.getValue(), LEN_WH_CD));
        } else {
            //strBilReturn.append(convFixedLgLeftAlign(soShipToTMsg.whCd.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_01.getValue(), LEN_WH_CD));
        }
        strBilReturn.append(strTrxSeq);
        strBilReturn.append(RECORD_TYPE_VALUE);
        strBilReturn.append(DTL_TP_SHIP);
        strBilReturn.append(SPACE);
        strBilReturn.append(CMPY_CD_01);
        if (TP_CD_COPY.equals(cMsg.xxTpCd_J2.getValue())) {
            //strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_J1.getValue(), LEN_WH_CD));
        } else {
            //strBilReturn.append(convFixedLgLeftAlign(soShipToTMsg.whCd.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_01.getValue(), LEN_WH_CD));
        }
        strBilReturn.append(convFixedLgLeftAlign(strShipOdr, LEN_WMS_SO_NUM));

        if (TP_CD_COPY.equals(cMsg.xxTpCd_J2.getValue())) {
            String sceOrdTpCd = cMsg.A.no(iSelCnt).sceOrdTpCd_A1.getValue();
            if (WMS_ORD_TP.OUTBOUND_STOCK_STATUS_CHANGE.equals(sceOrdTpCd) || //
                    WMS_ORD_TP.OUTBOUND_ITEM_CHANGE.equals(sceOrdTpCd) || //
                    WMS_ORD_TP.OUTBOUND_DIPOSAL.equals(sceOrdTpCd)) {
                strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_WMS_CUST_CD));
            } else {
                strBilReturn.append(convFixedLgLeftAlign(soShipToTMsg.wmsCustCd.getValue(), LEN_WMS_CUST_CD));
            }
        } else {
            strBilReturn.append(convFixedLgLeftAlign(soShipToTMsg.wmsCustCd.getValue(), LEN_WMS_CUST_CD));
        }

        strBilReturn.append(convFixedLgLeftAlign(soShipToTMsg.wmsShipToNm_01.getValue(), LEN_WMS_TO_NM_01));
        strBilReturn.append(convFixedLgLeftAlign(soShipToTMsg.wmsShipToNm_02.getValue(), LEN_WMS_TO_NM_02));
        strBilReturn.append(convFixedLgLeftAlign(soShipToTMsg.firstLineAddr.getValue(), LEN_FIRST_LINE_ADDR));
        strBilReturn.append(convFixedLgLeftAlign(soShipToTMsg.scdLineAddr.getValue(), LEN_SCD_LINE_ADDR));
        strBilReturn.append(convFixedLgLeftAlign(soShipToTMsg.thirdLineAddr.getValue(), LEN_THIRD_LINE_ADDR));
        strBilReturn.append(convFixedLgLeftAlign(soShipToTMsg.frthLineAddr.getValue(), LEN_FRTH_LINE_ADDR));
        strBilReturn.append(convFixedLgLeftAlign(soShipToTMsg.ctyAddr.getValue(), LEN_CTY_ADDR));
        strBilReturn.append(convFixedLgLeftAlign(soShipToTMsg.stCd.getValue(), LEN_ST_CD));
        strBilReturn.append(convFixedLgLeftAlign(soShipToTMsg.postCd.getValue(), LEN_POST_CD));
        strBilReturn.append(convFixedLgLeftAlign(soShipToTMsg.ctryCd.getValue(), LEN_CTRY_CD));
        strBilReturn.append(convFixedLgLeftAlign(soShipToTMsg.wmsShipToCtacNm.getValue(), LEN_WMS_CTAC_NM));
        strBilReturn.append(convFixedLgLeftAlign(soShipToTMsg.shipToCtacNum.getValue(), LEN_CTAC_NUM));
        strBilReturn.append(convFixedLgLeftAlign("", LEN_SHIP_FILLER));

        return strBilReturn.toString();
    }

    /**
     * The method explanation: This method create SoWorkData of Bill
     * To Data.
     * @param soBillToTMsg WMS_INBD_SO_BILL_TOTMsg
     * @param cMsg NLGL0010CMsg
     * @param iSelCnt int
     * @param strTrxSeq String
     * @param strShipOdr String
     * @return String
     */
    public static String createSoToWmsDataTxtFromBillTo(WMS_INBD_SO_BILL_TOTMsg soBillToTMsg, NLGL0010CMsg cMsg, int iSelCnt, String strTrxSeq, String strShipOdr) {

        StringBuilder strBilReturn = new StringBuilder();
        if (TP_CD_COPY.equals(cMsg.xxTpCd_J2.getValue())) {
            //strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_J1.getValue(), LEN_WH_CD));
        } else {
            //strBilReturn.append(convFixedLgLeftAlign(soBillToTMsg.whCd.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_01.getValue(), LEN_WH_CD));
        }
        strBilReturn.append(strTrxSeq);
        strBilReturn.append(RECORD_TYPE_VALUE);
        strBilReturn.append(DTL_TP_BILL);
        strBilReturn.append(SPACE);
        strBilReturn.append(CMPY_CD_01);
        if (TP_CD_COPY.equals(cMsg.xxTpCd_J2.getValue())) {
            //strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_J1.getValue(), LEN_WH_CD));
        } else {
            //strBilReturn.append(convFixedLgLeftAlign(soBillToTMsg.whCd.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_01.getValue(), LEN_WH_CD));
        }
        strBilReturn.append(convFixedLgLeftAlign(strShipOdr, LEN_WMS_SO_NUM));
        strBilReturn.append(convFixedLgLeftAlign(soBillToTMsg.wmsCustCd.getValue(), LEN_WMS_CUST_CD));
        strBilReturn.append(convFixedLgLeftAlign(soBillToTMsg.wmsBillToNm_01.getValue(), LEN_WMS_TO_NM_01));
        strBilReturn.append(convFixedLgLeftAlign(soBillToTMsg.wmsBillToNm_02.getValue(), LEN_WMS_TO_NM_02));
        strBilReturn.append(convFixedLgLeftAlign(soBillToTMsg.firstLineAddr.getValue(), LEN_FIRST_LINE_ADDR));
        strBilReturn.append(convFixedLgLeftAlign(soBillToTMsg.scdLineAddr.getValue(), LEN_SCD_LINE_ADDR));
        strBilReturn.append(convFixedLgLeftAlign(soBillToTMsg.thirdLineAddr.getValue(), LEN_THIRD_LINE_ADDR));
        strBilReturn.append(convFixedLgLeftAlign(soBillToTMsg.frthLineAddr.getValue(), LEN_FRTH_LINE_ADDR));
        strBilReturn.append(convFixedLgLeftAlign(soBillToTMsg.ctyAddr.getValue(), LEN_CTY_ADDR));
        strBilReturn.append(convFixedLgLeftAlign(soBillToTMsg.stCd.getValue(), LEN_ST_CD));
        strBilReturn.append(convFixedLgLeftAlign(soBillToTMsg.postCd.getValue(), LEN_POST_CD));
        strBilReturn.append(convFixedLgLeftAlign(soBillToTMsg.ctryCd.getValue(), LEN_CTRY_CD));
        strBilReturn.append(convFixedLgLeftAlign(soBillToTMsg.wmsBillToCtacNm.getValue(), LEN_WMS_CTAC_NM));
        strBilReturn.append(convFixedLgLeftAlign(soBillToTMsg.billToCtacNum.getValue(), LEN_CTAC_NUM));
        strBilReturn.append(convFixedLgLeftAlign("", LEN_BILL_FILLER));

        return strBilReturn.toString();
    }

    /**
     * The method explanation: This method create SoWorkData of Chrg
     * To Data.
     * @param soChrgToTMsg WMS_INBD_SO_CHRG_TOTMsg
     * @param cMsg NLGL0010CMsg
     * @param iSelCnt int
     * @param strTrxSeq String
     * @param strShipOdr String
     * @return String
     */
    public static String createSoToWmsDataTxtFromChrgTo(WMS_INBD_SO_CHRG_TOTMsg soChrgToTMsg, NLGL0010CMsg cMsg, int iSelCnt, String strTrxSeq, String strShipOdr) {

        StringBuilder strBilReturn = new StringBuilder();
        if (TP_CD_COPY.equals(cMsg.xxTpCd_J2.getValue())) {
            //strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_J1.getValue(), LEN_WH_CD));
        } else {
            //strBilReturn.append(convFixedLgLeftAlign(soChrgToTMsg.whCd.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_01.getValue(), LEN_WH_CD));
        }
        strBilReturn.append(strTrxSeq);
        strBilReturn.append(RECORD_TYPE_VALUE);
        strBilReturn.append(DTL_TP_CHRG);
        strBilReturn.append(SPACE);
        strBilReturn.append(CMPY_CD_01);
        if (TP_CD_COPY.equals(cMsg.xxTpCd_J2.getValue())) {
            //strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_J1.getValue(), LEN_WH_CD));
        } else {
            //strBilReturn.append(convFixedLgLeftAlign(soChrgToTMsg.whCd.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_01.getValue(), LEN_WH_CD));
        }
        strBilReturn.append(convFixedLgLeftAlign(strShipOdr, LEN_WMS_SO_NUM));

        if (TP_CD_COPY.equals(cMsg.xxTpCd_J2.getValue())) {
            String sceOrdTpCd = cMsg.A.no(iSelCnt).sceOrdTpCd_A1.getValue();
            if (WMS_ORD_TP.OUTBOUND_STOCK_STATUS_CHANGE.equals(sceOrdTpCd) || //
                    WMS_ORD_TP.OUTBOUND_ITEM_CHANGE.equals(sceOrdTpCd) || //
                    WMS_ORD_TP.OUTBOUND_DIPOSAL.equals(sceOrdTpCd)) {
                strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_WMS_CUST_CD));
            } else {
                strBilReturn.append(convFixedLgLeftAlign(soChrgToTMsg.wmsCustCd.getValue(), LEN_WMS_CUST_CD));
            }
        } else {
            strBilReturn.append(convFixedLgLeftAlign(soChrgToTMsg.wmsCustCd.getValue(), LEN_WMS_CUST_CD));
        }

        strBilReturn.append(convFixedLgLeftAlign(soChrgToTMsg.wmsChrgToNm_01.getValue(), LEN_WMS_TO_NM_01));
        strBilReturn.append(convFixedLgLeftAlign(soChrgToTMsg.wmsChrgToNm_02.getValue(), LEN_WMS_TO_NM_02));
        strBilReturn.append(convFixedLgLeftAlign(soChrgToTMsg.firstLineAddr.getValue(), LEN_FIRST_LINE_ADDR));
        strBilReturn.append(convFixedLgLeftAlign(soChrgToTMsg.scdLineAddr.getValue(), LEN_SCD_LINE_ADDR));
        strBilReturn.append(convFixedLgLeftAlign(soChrgToTMsg.thirdLineAddr.getValue(), LEN_THIRD_LINE_ADDR));
        strBilReturn.append(convFixedLgLeftAlign(soChrgToTMsg.frthLineAddr.getValue(), LEN_FRTH_LINE_ADDR));
        strBilReturn.append(convFixedLgLeftAlign(soChrgToTMsg.ctyAddr.getValue(), LEN_CTY_ADDR));
        strBilReturn.append(convFixedLgLeftAlign(soChrgToTMsg.stCd.getValue(), LEN_ST_CD));
        strBilReturn.append(convFixedLgLeftAlign(soChrgToTMsg.postCd.getValue(), LEN_POST_CD));
        strBilReturn.append(convFixedLgLeftAlign(soChrgToTMsg.ctryCd.getValue(), LEN_CTRY_CD));
        strBilReturn.append(convFixedLgLeftAlign(soChrgToTMsg.wmsChrgToCtacNm.getValue(), LEN_WMS_CTAC_NM));
        strBilReturn.append(convFixedLgLeftAlign(soChrgToTMsg.chrgToCtacNum.getValue(), LEN_CTAC_NUM));
        strBilReturn.append(convFixedLgLeftAlign("", LEN_CHRG_FILLER));

        return strBilReturn.toString();
    }

    /**
     * The method explanation: This method create SoWorkData of So
     * Detail Data.
     * @param soDtlTMsg WMS_INBD_SO_DTLTMsg
     * @param cMsg NLGL0010CMsg
     * @param iSelCnt int
     * @param strTrxSeq String
     * @param strShipOdr String
     * @param iMaxSqNum BigDecimal
     * @return String
     */
    public static String createSoWmsDataTxtFromSoDtl(WMS_INBD_SO_DTLTMsg soDtlTMsg, NLGL0010CMsg cMsg, int iSelCnt, String strTrxSeq, String strShipOdr, BigDecimal iMaxSqNum) {

        StringBuilder strBilReturn = new StringBuilder();
        if (TP_CD_COPY.equals(cMsg.xxTpCd_J2.getValue())) {
            //strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_J1.getValue(), LEN_WH_CD));
        } else {
            //strBilReturn.append(convFixedLgLeftAlign(soDtlTMsg.whCd.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_01.getValue(), LEN_WH_CD));
        }
        strBilReturn.append(strTrxSeq);
        strBilReturn.append(RECORD_TYPE_VALUE);
        strBilReturn.append(DTL_TP_DTL);
        strBilReturn.append(SPACE);
        strBilReturn.append(CMPY_CD_01);
        if (TP_CD_COPY.equals(cMsg.xxTpCd_J2.getValue())) {
            //strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.K.no(iSelCnt).packCdTxt_K1.getValue().substring(3), LEN_WH_CD));
        } else {
            //strBilReturn.append(convFixedLgLeftAlign(soDtlTMsg.whCd.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(soDtlTMsg.rtlSwhCd.getValue(), LEN_WH_CD));
        }
        strBilReturn.append(convFixedLgLeftAlign(strShipOdr, LEN_WMS_SO_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.wmsLineNum.getValue(), LEN_WMS_LINE_NUM, LEN_DEC_WMS_LINE_NUM));
        strBilReturn.append(convFixedLgLeftAlign(soDtlTMsg.wmsMdseCd.getValue(), LEN_WMS_MDSE_CD));
        strBilReturn.append(convFixedLgLeftAlign(soDtlTMsg.s80StkStsCd.getValue(), LEN_S80_STK_STS_CD));
        strBilReturn.append(convFixedLgLeftAlign(soDtlTMsg.custMdseCd.getValue(), LEN_CUST_MDSE_CD));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.wmsOrdQty.getValue(), LEN_WMS_ORD_QTY, LEN_DEC_WMS_ORD_QTY));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.backOrdQtyNum.getValue(), LEN_BACK_ORD_QTY_NUM, LEN_DEC_BACK_ORD_QTY_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.wmsShipQty.getValue(), LEN_WMS_SHIP_QTY, LEN_DEC_WMS_SHIP_QTY));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.unitPrcAmtNum.getValue(), LEN_UNIT_PRC_AMT_NUM, LEN_DEC_UNIT_PRC_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.unitDiscAmtNum.getValue(), LEN_UNIT_DISC_AMT_NUM, LEN_DEC_UNIT_DISC_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.unitDiscPrcAmtNum.getValue(), LEN_UNIT_DISC_PRC_AMT_NUM, LEN_DEC_UNIT_DISC_PRC_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.wmsTotAmtNum.getValue(), LEN_WMS_TOT_AMT_NUM, LEN_DEC_WMS_TOT_AMT_NUM));
        strBilReturn.append(convFixedLgLeftAlign(soDtlTMsg.indSerId.getValue(), LEN_IND_SER_ID));
        strBilReturn.append(convFixedLgLeftAlign(soDtlTMsg.indVoidAllwFlg.getValue(), LEN_IND_VOID_ALLW_FLG));
        strBilReturn.append(convFixedLgLeftAlign(soDtlTMsg.s80StkStsCdToCd.getValue(), LEN_S80_STK_STS_CD_TO_CD));
        strBilReturn.append(convFixedLgLeftAlign(soDtlTMsg.mdseCdSetCd.getValue(), LEN_MDSE_CD_SET_CD));
        strBilReturn.append(convFixedLgLeftAlign(soDtlTMsg.mdseCdSetDescTxt.getValue(), LEN_MDSE_CD_SET_DSEC_TXT));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.shipSetQty.getValue(), LEN_SHIP_SET_QTY, LEN_DEC_SHIP_SET_QTY));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.unitInsdQty.getValue(), LEN_UNIT_INSD_QTY, LEN_DEC_UNIT_INSD_QTY));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.essPoSqNum.getValue(), LEN_ESS_PO_SQ_NUM, LEN_DEC_ESS_PO_SQ_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.essMdseLineNum.getValue(), LEN_ESS_MDSE_LINE_NUM, LEN_DEC_ESS_MDSE_LINE_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.essLineNum.getValue(), LEN_ESS_LINE_NUM, LEN_DEC_ESS_LINE_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.essMsgLineNum.getValue(), LEN_ESS_MSG_LINE_NUM, LEN_DEC_ESS_MSG_LINE_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.totWtAmtNum.getValue(), LEN_TOT_WT_AMT_NUM, LEN_DEC_TOT_WT_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.totVolAmtNum.getValue(), LEN_TOT_VOL_AMT_NUM, LEN_DEC_TOT_VOL_AMT_NUM));
        strBilReturn.append(convFixedLgLeftAlign(soDtlTMsg.soMdseTpCd.getValue(), LEN_SO_MDSE_TP_CD));
        strBilReturn.append(convFixedLgLeftAlign(soDtlTMsg.wmsPackTpCd.getValue(), LEN_WMS_PACK_TP_CD));
        strBilReturn.append(convFixedLgLeftAlign(soDtlTMsg.batCptrReqFlg.getValue(), LEN_BAT_CPTR_REQ_FLG));
        strBilReturn.append(convFixedLgLeftAlign(soDtlTMsg.indConfigFlg.getValue(), LEN_IND_CONFIG_FLG));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.wmsNetAmtNum.getValue(), LEN_WMS_NET_AMT_NUM, LEN_DEC_WMS_NET_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.wmsDiscAmtNum.getValue(), LEN_WMS_DISC_AMT_NUM, LEN_DEC_WMS_DISC_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(soDtlTMsg.wmsTaxAmtNum.getValue(), LEN_WMS_TAX_AMT_NUM, LEN_DEC_WMS_TAX_AMT_NUM));
        strBilReturn.append(convFixedLgLeftAlign("", LEN_DTL_FILLER));

        return strBilReturn.toString();
    }

    /**
     * The method explanation: This method create SoWorkData of So
     * Detail Data.
     * @param cMsg NLGL0010CMsg
     * @param iSelCnt int
     * @param strTrxSeq String
     * @param strShipOdr String
     * @param glblCmpyCd Global Company Code
     * @param map HashMap<String, Object>
     * @param serNumTakeFlg serNumTakeFlg
     * @return String
     */
    public static String createSoWmsDataTxtFromNewData(NLGL0010CMsg cMsg, int iSelCnt, String strTrxSeq, String strShipOdr, String glblCmpyCd, Map map, boolean serNumTakeFlg) {

        BigDecimal iUomQty = null;
        BigDecimal iWeight = null;
        BigDecimal iVolume = null;
        if (map == null) {
            iUomQty = MDSE_EA_BASE_UOM_QTY;
            iWeight = MDSE_EA_BASE_WEIGHT;
            iVolume = MDSE_EA_BASE_VOLUME;
        } else {
            iUomQty = (BigDecimal) map.get(DB_WMS_BASE_UOM_QTY);
            iWeight = (BigDecimal) map.get(DB_WMS_MDSE_WT);
            iVolume = (BigDecimal) map.get(DB_WMS_MDSE_VOL);
        }

        String strSer = ZYPConstant.FLG_OFF_N;
        if (serNumTakeFlg) {
            Map<String, Object> ssmParamSerial = new HashMap<String, Object>();
            ssmParamSerial.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParamSerial.put(DB_PARAM_WH_CD, cMsg.whCd_J2.getValue());
            ssmParamSerial.put(DB_PARAM_WMS_MDSE_CD, cMsg.K.no(iSelCnt).wmsMdseCd_K1.getValue());
            S21SsmEZDResult ssmResultSerial = NLGL0010Query.getInstance().getSerFromMdse(ssmParamSerial);
            if (ssmResultSerial.isCodeNormal()) {
                strSer = (String) ((List) ssmResultSerial.getResultObject()).get(0);
            }
        }

        StringBuilder strBilReturn = new StringBuilder();
        //strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_WH_CD));
        strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_J1.getValue(), LEN_WH_CD));
        strBilReturn.append(strTrxSeq);
        strBilReturn.append(RECORD_TYPE_VALUE);
        strBilReturn.append(DTL_TP_DTL);
        strBilReturn.append(SPACE);
        strBilReturn.append(CMPY_CD_01);
        //strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_WH_CD));
        strBilReturn.append(convFixedLgLeftAlign(cMsg.K.no(iSelCnt).packCdTxt_K1.getValue().substring(3), LEN_WH_CD));
        strBilReturn.append(convFixedLgLeftAlign(strShipOdr, LEN_WMS_SO_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(cMsg.K.no(iSelCnt).wmsLineNum_K1.getValue(), LEN_WMS_LINE_NUM, LEN_DEC_WMS_LINE_NUM));
        strBilReturn.append(convFixedLgLeftAlign(cMsg.K.no(iSelCnt).wmsMdseCd_K1.getValue(), LEN_WMS_MDSE_CD));
        strBilReturn.append(convFixedLgLeftAlign(VAL_STK_STS_CD_PFX + cMsg.K.no(iSelCnt).wmsStkStsCd_K2.getValue(), LEN_S80_STK_STS_CD));
        strBilReturn.append(convFixedLgLeftAlign(CUST_SKU + cMsg.K.no(iSelCnt).wmsLineNum_K1.getValue().toString(), LEN_CUST_MDSE_CD));
        strBilReturn.append(convZeroPaddingAddDeclPnt(cMsg.K.no(iSelCnt).wmsShipQty_K1.getValue().multiply(iUomQty), LEN_WMS_ORD_QTY, LEN_DEC_WMS_ORD_QTY));
        strBilReturn.append(convZeroPaddingAddDeclPnt(BigDecimal.ZERO, LEN_BACK_ORD_QTY_NUM, LEN_DEC_BACK_ORD_QTY_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(cMsg.K.no(iSelCnt).wmsShipQty_K1.getValue().multiply(iUomQty), LEN_WMS_SHIP_QTY, LEN_DEC_WMS_SHIP_QTY));
        strBilReturn.append(convZeroPaddingAddDeclPnt(BigDecimal.ZERO, LEN_UNIT_PRC_AMT_NUM, LEN_DEC_UNIT_PRC_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(BigDecimal.ZERO, LEN_UNIT_DISC_AMT_NUM, LEN_DEC_UNIT_DISC_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(BigDecimal.ZERO, LEN_UNIT_DISC_PRC_AMT_NUM, LEN_DEC_UNIT_DISC_PRC_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(BigDecimal.ZERO, LEN_WMS_TOT_AMT_NUM, LEN_DEC_WMS_TOT_AMT_NUM));
        strBilReturn.append(convFixedLgLeftAlign(strSer, LEN_IND_SER_ID));
        strBilReturn.append(ZYPConstant.FLG_OFF_N);
        if (ZYPCommonFunc.hasValue(cMsg.K.no(iSelCnt).wmsStkStsCd_K4)) {
            strBilReturn.append(convFixedLgLeftAlign(VAL_STK_STS_CD_PFX + cMsg.K.no(iSelCnt).wmsStkStsCd_K4.getValue(), LEN_S80_STK_STS_CD_TO_CD));
        } else {
            strBilReturn.append(convFixedLgLeftAlign(SPACE, LEN_S80_STK_STS_CD_TO_CD));
        }
        strBilReturn.append(convFixedLgLeftAlign(SPACE, LEN_MDSE_CD_SET_CD));
        strBilReturn.append(convFixedLgLeftAlign(SPACE, LEN_MDSE_CD_SET_DSEC_TXT));
        strBilReturn.append(convZeroPaddingAddDeclPnt(BigDecimal.ZERO, LEN_SHIP_SET_QTY, LEN_DEC_SHIP_SET_QTY));
        strBilReturn.append(convZeroPaddingAddDeclPnt(BigDecimal.ZERO, LEN_UNIT_INSD_QTY, LEN_DEC_UNIT_INSD_QTY));
        strBilReturn.append(convZeroPaddingAddDeclPnt(BigDecimal.ZERO, LEN_ESS_PO_SQ_NUM, LEN_DEC_ESS_PO_SQ_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(BigDecimal.ZERO, LEN_ESS_MDSE_LINE_NUM, LEN_DEC_ESS_MDSE_LINE_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(BigDecimal.ZERO, LEN_ESS_LINE_NUM, LEN_DEC_ESS_LINE_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(BigDecimal.ZERO, LEN_ESS_MSG_LINE_NUM, LEN_DEC_ESS_MSG_LINE_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(cMsg.K.no(iSelCnt).wmsShipQty_K1.getValue().multiply(iWeight), LEN_TOT_WT_AMT_NUM, LEN_DEC_TOT_WT_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(cMsg.K.no(iSelCnt).wmsShipQty_K1.getValue().multiply(iVolume), LEN_TOT_VOL_AMT_NUM, LEN_DEC_TOT_VOL_AMT_NUM));
        strBilReturn.append(convFixedLgLeftAlign("", LEN_SO_MDSE_TP_CD));
        strBilReturn.append(convFixedLgLeftAlign("", LEN_WMS_PACK_TP_CD));
        strBilReturn.append(convFixedLgLeftAlign(SPACE, LEN_BAT_CPTR_REQ_FLG));
        strBilReturn.append(convFixedLgLeftAlign(SPACE, LEN_IND_CONFIG_FLG));
        strBilReturn.append(convZeroPaddingAddDeclPnt(BigDecimal.ZERO, LEN_WMS_NET_AMT_NUM, LEN_DEC_WMS_NET_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(BigDecimal.ZERO, LEN_WMS_DISC_AMT_NUM, LEN_DEC_WMS_DISC_AMT_NUM));
        strBilReturn.append(convZeroPaddingAddDeclPnt(BigDecimal.ZERO, LEN_WMS_TAX_AMT_NUM, LEN_DEC_WMS_TAX_AMT_NUM));
        strBilReturn.append(convFixedLgLeftAlign("", LEN_DTL_FILLER));

        return strBilReturn.toString();
    }

    /**
     * The method explanation: This method create SoWorkData of So
     * Detail Data.
     * @param soTextTMsg WMS_INBD_SO_TEXTTMsg
     * @param cMsg NLGL0010CMsg
     * @param iSelCnt int
     * @param strTrxSeq String
     * @param strShipOdr String
     * @return String
     */
    public static String createSoWmsDataTxtFromSoTxt(WMS_INBD_SO_TEXTTMsg soTextTMsg, NLGL0010CMsg cMsg, int iSelCnt, String strTrxSeq, String strShipOdr) {

        StringBuilder strBilReturn = new StringBuilder();
        if (TP_CD_COPY.equals(cMsg.xxTpCd_J2.getValue())) {
            //strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_J1.getValue(), LEN_WH_CD));
        } else {
            //strBilReturn.append(convFixedLgLeftAlign(soTextTMsg.whCd.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_01.getValue(), LEN_WH_CD));
        }
        strBilReturn.append(strTrxSeq);
        strBilReturn.append(RECORD_TYPE_VALUE);
        strBilReturn.append(DTL_TP_TXT);
        strBilReturn.append(SPACE);
        strBilReturn.append(CMPY_CD_01);
        if (TP_CD_COPY.equals(cMsg.xxTpCd_J2.getValue())) {
            //strBilReturn.append(convFixedLgLeftAlign(cMsg.whCd_J2.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_J1.getValue(), LEN_WH_CD));
        } else {
            //strBilReturn.append(convFixedLgLeftAlign(soTextTMsg.whCd.getValue(), LEN_WH_CD));
            strBilReturn.append(convFixedLgLeftAlign(cMsg.rtlWhCd_01.getValue(), LEN_WH_CD));
        }
        strBilReturn.append(convFixedLgLeftAlign(strShipOdr, LEN_WMS_SO_NUM));
        strBilReturn.append(convFixedLgLeftAlign(soTextTMsg.wmsTxtCd.getValue(), LEN_WMS_TXT_CD));
        strBilReturn.append(convFixedLgLeftAlign(soTextTMsg.wmsPrintTpCd.getValue(), LEN_WMS_PRINT_TP_CD));
        strBilReturn.append(convFixedLgLeftAlign(soTextTMsg.inbdSoMsgTxt_01.getValue(), LEN_INBD_SO_MSG_TXT));
        strBilReturn.append(convFixedLgLeftAlign(soTextTMsg.inbdSoMsgTxt_02.getValue(), LEN_INBD_SO_MSG_TXT));
        strBilReturn.append(convFixedLgLeftAlign(soTextTMsg.inbdSoMsgTxt_03.getValue(), LEN_INBD_SO_MSG_TXT));
        strBilReturn.append(convFixedLgLeftAlign(soTextTMsg.inbdSoMsgTxt_04.getValue(), LEN_INBD_SO_MSG_TXT));
        strBilReturn.append(convFixedLgLeftAlign("", LEN_TXT_FILLER));

        return strBilReturn.toString();
    }

    /**
     * The method explanation: This method get Trx Seq case by
     * DropDownList of DownLoad.
     * @param cMsg NLGL0010CMsg
     * @param glblCmpyCd String
     * @return String
     */
    public static String getTrxSeqByRadio(NLGL0010CMsg cMsg,  String glblCmpyCd) {

        //if (TP_CD_COPY.equals(cMsg.xxTpCd_J2.getValue())) {
            return convFixedLgLeftAlign(WMS_SO_ID_PFX + cMsg.whCd_J2.getValue() + ZYPNumbering.getUniqueID(glblCmpyCd, SEQ_WMS_ORD_ID).substring(LEN_UNIQUE_SO_ID_START, LEN_UNIQUE_SO_ID_END), LEN_WMS_SO_NUM);
        //}
        //return convFixedLgLeftAlignOverSizeToSpace(cMsg.A.no(iSelCnt).wmsSoId_A1.getValue(), LEN_WMS_SO_NUM);
    }

    /**
     * Convert zero padding decimal(include decimal point)
     * @param val Value
     * @param int FixedLength
     * @param int DecimalLength
     * @return String
     */
    private static String convZeroPaddingAddDeclPnt(final BigDecimal val, final int lg, final int declLg) {

        String str = convZeroPaddingDeclPntNone(val, lg, declLg);

        if (declLg == 0) {
            return str;
        } else if (ZYPCommonFunc.hasValue(str)) {
            return str.substring(1, lg - declLg) + "." + str.substring(lg - declLg, str.length());
        } else {
            return String.format("%" + Integer.toString(lg + 1) + "s", "");
        }
    }

    /**
     * Convert zero padding decimal point none
     * @param val Value
     * @param int FixedLength
     * @param int DecimalLength
     * @return String
     */
    private static String convZeroPaddingDeclPntNone(final BigDecimal val, final int lg, final int declLg) {

        BigDecimal bigDeclVal = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(val)) {
            bigDeclVal = val;
        }
        bigDeclVal = bigDeclVal.movePointRight(declLg);
        bigDeclVal = bigDeclVal.setScale(0, BigDecimal.ROUND_DOWN);
        BigInteger bigIntVal = bigDeclVal.toBigInteger();

        String strVal = String.format("%0" + Integer.toString(lg) + "d", bigIntVal);

        if (strVal.length() > lg) {
            return String.format("%" + Integer.toString(lg) + "s", "");
        } else {
            return strVal;
        }
    }

    /**
     * The method explanation: Convert fixed length left align.
     * @param str String
     * @param lg FixedLength
     * @return String
     */
    private static String convFixedLgLeftAlign(final String str, final int lg) {

        if (str != null && str.length() >= lg) {
            return str.substring(0, lg);
        } else {
            return String.format("%-" + Integer.toString(lg) + "s", str);
        }
    }

    /**
     * The method explanation: Convert fixed length left align. If
     * Over Size then Space.
     * @param str String
     * @param lg FixedLength
     * @return String
     */
    private static String convFixedLgLeftAlignOverSizeToSpace(final String str, final int lg) {

        if (str != null && str.length() <= lg) {
            return String.format("%-" + Integer.toString(lg) + "s", str);
        } else {
            return String.format("%-" + Integer.toString(lg) + "s", "");
        }
    }

    /**
     * The method explanation: Convert date format.
     * @param date date string
     * @param inFormat input format
     * @param outFormat output format
     * @return
     */
    private static String dateFormat(final String date, final String inFormat, final String outFormat) {

        if (!ZYPCommonFunc.hasValue(date)) {
            return "";
        }
        return ZYPDateUtil.DateFormatter(date, inFormat, outFormat);
    }

    /**
     * The method explanation: This method get MixPack String.
     * @param sMsg NLGL0010SMsg
     * @param strWhCd String
     * @param strSoId String
     * @param glblCmpyCd String
     * @param wmsSqNum BigDecimal
     * @return String
     */
    private static String getMixPack(NLGL0010SMsg sMsg, String strWhCd, String strSoId, String glblCmpyCd, BigDecimal wmsSqNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_WH_CD, strWhCd);
        ssmParam.put(DB_PARAM_WMS_SO_ID, strSoId);
        ssmParam.put(DB_PARAM_WMS_SQ_NUM, wmsSqNum);
        ssmParam.put(DB_PARAM_WMS_UOM_CD_EA, WMS_UOM.EACH);
        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getMixPack(sMsg, ssmParam);

        if (!ssmResult.isCodeNormal()) {
            return BigDecimal.ZERO.toString();
        }

        BigDecimal bdVolSML = BigDecimal.ZERO;
        BigDecimal bdVolMED = BigDecimal.ZERO;
        BigDecimal bdVolLRG = BigDecimal.ZERO;
        boolean blUseSML = true;
        boolean blUseMED = true;
        boolean blUseLRG = true;
        int iVCount = 0;
        int numSML = 0;
        int numMED = 0;
        int numLRG = 0;
        int numOVER = 0;

        for (int i = 0; i < ssmResult.getQueryResultCount(); i++) {

            blUseSML = true;
            blUseMED = true;
            blUseLRG = true;
            if (fixBoxData(BOX_TYPE_SML, sMsg.Z.no(i).wmsMdseLgNum_Z1.getValue(), sMsg.Z.no(i).wmsMdseWdtNum_Z1.getValue(), sMsg.Z.no(i).wmsMdseHgtNum_Z1.getValue(), sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue())) {
                if (blUseSML && (iVCount == 0 || (BOX_TYPE_SML_VOL.multiply(BOX_TYPE_PCT_FILL).compareTo(bdVolSML.add(sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue())) >= 0))) {
                    bdVolSML = bdVolSML.add(sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue());
                    bdVolMED = bdVolMED.add(sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue());
                    bdVolLRG = bdVolLRG.add(sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue());
                    numSML++;
                    iVCount++;
                } else if (blUseMED && (iVCount == 0 || (BOX_TYPE_MED_VOL.multiply(BOX_TYPE_PCT_FILL).compareTo(bdVolMED.add(sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue())) >= 0))) {
                    blUseSML = false;
                    bdVolMED = bdVolMED.add(sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue());
                    bdVolLRG = bdVolLRG.add(sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue());
                    numMED++;
                    iVCount++;
                } else if (blUseLRG && (iVCount == 0 || (BOX_TYPE_LRG_VOL.multiply(BOX_TYPE_PCT_FILL).compareTo(bdVolLRG.add(sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue())) >= 0))) {
                    blUseSML = false;
                    blUseMED = false;
                    bdVolLRG = bdVolLRG.add(sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue());
                    numLRG++;
                    iVCount++;
                }
            } else if (fixBoxData(BOX_TYPE_MED, sMsg.Z.no(i).wmsMdseLgNum_Z1.getValue(), sMsg.Z.no(i).wmsMdseWdtNum_Z1.getValue(), sMsg.Z.no(i).wmsMdseHgtNum_Z1.getValue(), sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue())) {
                blUseSML = false;
                if (blUseMED && (iVCount == 0 || (BOX_TYPE_MED_VOL.multiply(BOX_TYPE_PCT_FILL).compareTo(bdVolMED.add(sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue())) >= 0))) {
                    bdVolMED = bdVolMED.add(sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue());
                    bdVolLRG = bdVolLRG.add(sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue());
                    numMED++;
                    iVCount++;
                } else if (blUseLRG && (iVCount == 0 || (BOX_TYPE_LRG_VOL.multiply(BOX_TYPE_PCT_FILL).compareTo(bdVolLRG.add(sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue())) >= 0))) {
                    blUseMED = false;
                    bdVolLRG = bdVolLRG.add(sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue());
                    numLRG++;
                    iVCount++;
                }
            } else if (fixBoxData(BOX_TYPE_LRG, sMsg.Z.no(i).wmsMdseLgNum_Z1.getValue(), sMsg.Z.no(i).wmsMdseWdtNum_Z1.getValue(), sMsg.Z.no(i).wmsMdseHgtNum_Z1.getValue(), sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue())) {
                blUseSML = false;
                blUseMED = false;
                if (blUseLRG && (iVCount == 0 || (BOX_TYPE_LRG_VOL.multiply(BOX_TYPE_PCT_FILL).compareTo(bdVolLRG.add(sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue())) >= 0))) {
                    bdVolLRG = bdVolLRG.add(sMsg.Z.no(i).wmsMdseVolNum_Z1.getValue());
                    numLRG++;
                    iVCount++;
                }
            } else {
                numOVER++;
            }
        }

        sMsg.Z.clear();
        return numSML + BOX_TYPE_SML + SPACE + numMED + BOX_TYPE_MED + SPACE + numLRG + BOX_TYPE_LRG + SPACE + numOVER + BOX_TYPE_OVR;
    }

    /**
     * The method explanation: This method judge which box includes
     * item.
     * @param bType String
     * @param pl BigDecimal
     * @param pw BigDecimal
     * @param ph BigDecimal
     * @param pv BigDecimal
     * @return boolean
     */
    private static boolean fixBoxData(String bType, BigDecimal pl, BigDecimal pw, BigDecimal ph, BigDecimal pv) {

        BigDecimal bl = BigDecimal.ZERO;
        BigDecimal bw = BigDecimal.ZERO;
        BigDecimal bh = BigDecimal.ZERO;
        BigDecimal bv = BigDecimal.ZERO;
        BigDecimal v = BigDecimal.ZERO;
        BigDecimal l = pl;
        BigDecimal w = pw;
        BigDecimal h = ph;

        if (BOX_TYPE_SML.equals(bType)) {
            bl = BOX_TYPE_SML_LG;
            bw = BOX_TYPE_SML_WDT;
            bh = BOX_TYPE_SML_HGT;
            bv = BOX_TYPE_SML_VOL;
        } else if (BOX_TYPE_MED.equals(bType)) {
            bl = BOX_TYPE_MED_LG;
            bw = BOX_TYPE_MED_WDT;
            bh = BOX_TYPE_MED_HGT;
            bv = BOX_TYPE_MED_VOL;
        } else if (BOX_TYPE_LRG.equals(bType)) {
            bl = BOX_TYPE_LRG_LG;
            bw = BOX_TYPE_LRG_WDT;
            bh = BOX_TYPE_LRG_HGT;
            bv = BOX_TYPE_LRG_VOL;
        }

        if (w.compareTo(l) > 0) {
            v = l;
            l = w;
            w = v;
        }
        if (h.compareTo(w) > 0) {
            v = h;
            h = w;
            w = v;
        }
        if (w.compareTo(l) > 0) {
            v = l;
            l = w;
            w = v;
        }
        if (l.compareTo(bl) > 0 || w.compareTo(bw) > 0 || h.compareTo(bh) > 0 || pv.compareTo(bv) > 0) {
            return false;
        }
        return true;
    }

    /**
     * get the Before 1 month of the specified date.
     * @param targetDate targetDate(YYYYMMDD)
     * @return the before 1 month(YYYYMMDD)
     */
    public static String getBeforeMonth(String targetDate) {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(S21CalendarUtilConstants.TYPE_YYYYMMDD);
        Date dt = null;
        try {
            dt = format.parse(targetDate);
        } catch (ParseException e) {
            return null;
        }
        cal.setTime(dt);
        cal.add(Calendar.MONTH, -1);
        format.applyPattern(FMT_YYYYMMDD);
        return format.format(cal.getTime());
    }

    /**
     * To change date format (yyyymmddhhmmss or yyyymmddhhmmssSSSS to
     * mm/dd/yyyy hh:mm:ss or mm/dd/yyyy)
     * @param targetDate yyyymmddhhmmss or yyyymmddhhmmssSSSS
     * @param setMMSS true = change to mm/dd/yyyy hh:mm:ss false =
     * change to mm/dd/yyyy
     * @return change date
     */
    public static String changeDateformat(String targetDate, boolean setMMSS) {

        String rtnDate = "";

        if (!ZYPCommonFunc.hasValue(targetDate)) {
            return rtnDate;
        }

        if (setMMSS) {

            if (targetDate.length() == LEN_YMD_17) {
                rtnDate = ZYPDateUtil.DateFormatter(targetDate, YYYYMMDDHHMMSSSSS_BEFORE, YYYYMMDDHHMMSS_AFTER);
            } else {
                rtnDate = ZYPDateUtil.DateFormatter(targetDate, YYYYMMDDHHMMSS_BEFORE, YYYYMMDDHHMMSS_AFTER);
            }
        } else {
            rtnDate = ZYPDateUtil.DateFormatter(targetDate, YYYYMMDDHHMMSS_BEFORE, YYYYMMDD_AFTER);
        }
        return rtnDate;
    }

    /**
     * The method explanation: This method Check "Submit Button" Click
     * Number and Check Data before upload.
     * @param sMsg NLGL0010SMsg
     * @param cMsg NLGL0010CMsg
     * @return Check Error or Not Error
     */
    public static boolean isErrorBeforUpdate(NLGL0010SMsg sMsg, NLGL0010CMsg cMsg) {

        if (ZYPConstant.FLG_ON_1.equals(sMsg.xxBtnFlg_01.getValue())) {
            return true;
        }
        ZYPEZDItemValueSetter.setValue(sMsg.xxBtnFlg_01, ZYPConstant.FLG_ON_1);

        for (int i = 0; i < cMsg.O.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.O.no(i).otbdOrdNum_O1) && !ZYPCommonFunc.hasValue(cMsg.O.no(i).inbdOrdNum_O1)) {
                cMsg.setMessageInfo(NLGM0076W);
                return false;
            }
        }

        return true;
    }

    /**
     * Check the order is exist or not.
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param whCd WH_CD
     * @param wmsSqNum WMS_SQ_NUM
     * @return true/exist, false/not exist
     */
    public static boolean isExistOrder(String glblCmpyCd, String whCd, BigDecimal wmsSqNum) {

        WMS_INBD_SO_HDRTMsg wmsInbdSoHdrT = new WMS_INBD_SO_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(wmsInbdSoHdrT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdSoHdrT.whCd, whCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdSoHdrT.wmsSqNum, wmsSqNum);
        wmsInbdSoHdrT = (WMS_INBD_SO_HDRTMsg) EZDTBLAccessor.findByKey(wmsInbdSoHdrT);

        if (wmsInbdSoHdrT == null) {
            return false;
        }
        return true;
    }

    /**
     * To create Pull down List(WHcode List for Search)
     * @param bizMsg NLGL0020CMsg
     * @param glblCmpyCd String
     */
    public static void createRtlWhPulldownList(NLGL0010CMsg bizMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        S21SsmEZDResult rtlWh = NLGL0010Query.getInstance().getRtlWhPullDownList(ssmParam);

        List<Map> rtlWhList = (List<Map>) rtlWh.getResultObject();

        bizMsg.rtlWhCd_LC.clear();
        bizMsg.xxEdtCdNm_LD.clear();

        for (int i = 0; i < rtlWhList.size(); i++) {
            Map pullDownData = rtlWhList.get(i);
            bizMsg.rtlWhCd_LC.no(i).setValue((String) pullDownData.get(DB_RTL_WH_CD));
            bizMsg.xxEdtCdNm_LD.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(DB_RTL_WH_CD) //
                    , DELIMITER_COLON, (String) pullDownData.get(DB_RTL_WH_NM)));
        }
    }

    /**
     * To create Pull down List(ShipVia List for 3PL)
     * @param bizMsg NLGL0020CMsg
     * @param glblCmpyCd String
     * @param whCd String
     */
    public static void create3PLShipViaPulldownList(NLGL0010CMsg bizMsg, String glblCmpyCd, String whCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_INVTY_OWNR_CD, whCd);
        S21SsmEZDResult result = NLGL0010Query.getInstance().get3PLShipVia(ssmParam);

        bizMsg.tplSvcLvlCd_J1.clear();
        bizMsg.xxEdtCdNm_J2.clear();

        List<Map<String, String>> shipViaList = (List<Map<String, String>>) result.getResultObject();
        for (int i = 0; i < shipViaList.size(); i++) {
            Map<String, String> pulldownData = shipViaList.get(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.tplSvcLvlCd_J1.no(i), pulldownData.get(DB_TPL_SVC_LVL_CD));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxEdtCdNm_J2.no(i), ZYPCommonFunc.concatString(pulldownData.get(DB_TPL_SVC_LVL_CD)
                    , DELIMITER_COLON, pulldownData.get(DB_TPL_SVC_LVL_NM)));

        }

    }

    /**
     * To get Retail Warehouse Name
     * @param bizMsg NLGL0020CMsg
     * @param glblCmpyCd String
     */
    public static void getRetailWhNm(NLGL0010CMsg bizMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, bizMsg.rtlWhCd_01.getValue());
        S21SsmEZDResult rtlWh = NLGL0010Query.getInstance().getRetailWhNm(ssmParam);

        String rtlWhNm = (String) rtlWh.getResultObject();

        if (rtlWhNm == null) {
            bizMsg.setMessageInfo(NLZM2278E, new String[] {"Wh Code"});
            return;
        }

        bizMsg.rtlWhNm_01.setValue(rtlWhNm);

    }

    /**
     * To create Pull down List(WHcode List for Search)
     * @param bizMsg NLGL0020CMsg
     * @param glblCmpyCd String
     */
    public static void createPackCdPulldownList(NLGL0010CMsg bizMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(bizMsg.rtlWhCd_J1)) {
            bizMsg.fill40Txt_LC.clear();
            bizMsg.fill40Txt_LD.clear();
            return;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, bizMsg.rtlWhCd_J1.getValue());
        String flg = ZYPCodeDataUtil.getVarCharConstValue(WMS_PACK_CD_SET_OWNER_CD_FLG, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(flg)) {
            ssmParam.put(DB_PARAM_WMS_PACK_CD_SET_OWNER_CD_FLG, flg);
        } else {
            ssmParam.put(DB_PARAM_WMS_PACK_CD_SET_OWNER_CD_FLG, ZYPConstant.FLG_OFF_N);
        }

        S21SsmEZDResult packCd = NLGL0010Query.getInstance().getPackCdPullDownList(ssmParam);

        List<Map> packCdList = (List<Map>) packCd.getResultObject();

        for (int i = 0; i < packCdList.size() && i < bizMsg.fill40Txt_LC.length(); i++) {
            Map pullDownData = packCdList.get(i);
            bizMsg.fill40Txt_LC.no(i).setValue((String) pullDownData.get(DB_PACK_CD_TXT));
            bizMsg.fill40Txt_LD.no(i).setValue((String) pullDownData.get(DB_PACK_CD_TXT));
        }
    }

    /**
     * To create Pull down List(WHcode List for Search)
     * @param bizMsg NLGL0020CMsg
     * @param glblCmpyCd String
     */
    public static void  createFlgPulldownList(NLGL0010CMsg bizMsg, String glblCmpyCd) {

        bizMsg.xxPgFlg_LC.clear();
        bizMsg.xxPgFlg_LD.clear();

        // Set ALL Value
        bizMsg.xxPgFlg_LC.no(0).setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.xxPgFlg_LD.no(0).setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.xxPgFlg_LC.no(1).setValue(ZYPConstant.FLG_ON_Y);
        bizMsg.xxPgFlg_LD.no(1).setValue(ZYPConstant.FLG_ON_Y);
    }
}
