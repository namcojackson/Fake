/**
 * <pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0020.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLGL0020.NLGL0020CMsg;
import business.blap.NLGL0020.NLGL0020Query;
import business.blap.NLGL0020.NLGL0020SMsg;
import business.blap.NLGL0020.constant.NLGL0020Constant;
import business.db.WMS_INBD_PO_HDRTMsg;

import com.canon.cusa.s21.api.NLG.NLGC001001.NLGC001001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_PRCH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;

/**
 * <pre>
 * PO Maintenance
 * 
 * Date           Company         Name              Create/Update      Defect No
 * ------------------------------------------------------------------------------------
 * 08/30/2013     CSAI            N.Sekine          Create             MW Replace Initial
 * 07/03/2017     CITS            S.Endo            Update             QC#19042
 *</pre>
 */
public class NLGL0020CommonLogic implements NLGL0020Constant {

    /**
     * copyFromSmsgOntoCmsg Copy data From Smsg Onto Cmsg
     * @param bizMsg NLGL0020CMsg
     * @return true/false
     */
    public static boolean checkListCheckBoxCount(NLGL0020CMsg bizMsg) {

        List<Integer> outGetSelectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, FIELD_NAME_XXCHKBOX_A1, ZYPConstant.CHKBOX_ON_Y);

        if (outGetSelectedRows.isEmpty()) {
            bizMsg.setMessageInfo(NLGM0036E);
            return false;
        }

        if (outGetSelectedRows.size() > 1) {
            bizMsg.setMessageInfo(NLGM0035E);
            return false;
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

        WMS_INBD_PO_HDRTMsg wmsInbdPoHdrT = new WMS_INBD_PO_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(wmsInbdPoHdrT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdPoHdrT.whCd, whCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdPoHdrT.wmsSqNum, wmsSqNum);
        wmsInbdPoHdrT = (WMS_INBD_PO_HDRTMsg) EZDTBLAccessor.findByKey(wmsInbdPoHdrT);

        if (wmsInbdPoHdrT == null) {
            return false;
        }
        return true;
    }

    /**
     * get the Before 1 month of the specified date.
     * @param targetDate String date(YYYYMMDD)
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
        cal.add(Calendar.MONTH, DATA_VALUE_INT_MIN_1);
        format.applyPattern(YYYYMMDD_BEFORE);
        return format.format(cal.getTime());
    }

    /**
     * copyFromSmsgOntoCmsg Copy data From Smsg Onto Cmsg
     * @param cMsg NLGL0020CMsg
     * @param sMsg NLGL0020SMsg
     * @param glgbCmpyCd Global Company Code
     */
    public static void copyFromSMsgOntoCmsg(NLGL0020CMsg cMsg, NLGL0020SMsg sMsg, String glgbCmpyCd) {

        int pagenationFromIndex = cMsg.xxPageShowFromNum_A1.getValueInt() - 1;
        int i = pagenationFromIndex;

        for (; i < pagenationFromIndex + cMsg.A.length(); i++) {

            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFromIndex), null);

                if (ZYPCommonFunc.hasValue(cMsg.A.no(i - pagenationFromIndex).poFromDtTmTs_A1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i - pagenationFromIndex).xxDtTm_A1 //
                            , (ZYPDateUtil.DateFormatter(cMsg.A.no(i - pagenationFromIndex).poFromDtTmTs_A1.getValue()//
                                    , YYYYMMDDHHMMSS_BEFORE, YYYYMMDDHHMMSS_AFTER)));
                }

                if (ZYPCommonFunc.hasValue(cMsg.A.no(i - pagenationFromIndex).ezInTime_A1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i - pagenationFromIndex).xxDtTm_A2 //
                            , (ZYPDateUtil.DateFormatter(cMsg.A.no(i - pagenationFromIndex).ezInTime_A1.getValue()//
                                    , YYYYMMDDHHMMSSSSS_BEFORE, YYYYMMDDHHMMSS_AFTER)));
                }
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
                ssmParam.put(DB_PARAM_WH_CD, cMsg.whCd_02.getValue());
                ssmParam.put(DB_PARAM_PO_ID, cMsg.A.no(i - pagenationFromIndex).wmsPoId_A1.getValue());
                S21SsmEZDResult dsTlansSTS = NLGL0020Query.getInstance().getTranslateStatus(ssmParam);

                List<Map> dsTlansSTSList = (List<Map>) dsTlansSTS.getResultObject();

                if (dsTlansSTSList.size() == DATA_VALUE_INT_0) {
                    cMsg.A.no(i - pagenationFromIndex).xxDtlCd_A1.setValue(PROCESSING);
                    continue;
                }
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i - pagenationFromIndex).wmsCloDtTmTs_A1)) {
                    cMsg.A.no(i - pagenationFromIndex).xxDtlCd_A1.setValue(PROCEESSED);
                } else {
                    for (int h = 0; h < dsTlansSTSList.size(); h++) {
                        Map listData = dsTlansSTSList.get(h);

                        if (PROC_STS.ERROR.equals((String) listData.get(DB_INTFC_PROC_STS_CD)) || PROC_STS.ERROR.equals((String) listData.get(DB_PROC_STS_CD))) {
                            cMsg.A.no(i - pagenationFromIndex).xxDtlCd_A1.setValue(ERROR);
                        } else {
                            cMsg.A.no(i - pagenationFromIndex).xxDtlCd_A1.setValue(PROCESSING);
                        }
                    }
                }
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFromIndex);
        cMsg.xxPageShowToNum_A1.setValue(pagenationFromIndex + cMsg.A.getValidCount());
    }

    /**
     * copyFromSmsgOntoCmsg Copy data From Smsg Onto Cmsg
     * @param cMsg NLGL0020CMsg
     * @param sMsg NLGL0020SMsg
     */
    public static void copyFromSMsgOntoCmsgSTATUS(NLGL0020CMsg cMsg, NLGL0020SMsg sMsg) {

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {

            if (i < sMsg.B.getValidCount()) {
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);

                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).ezInTime_B1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxDtTm_B1 //
                            , (ZYPDateUtil.DateFormatter(cMsg.B.no(i).ezInTime_B1.getValue()//
                                    , YYYYMMDDHHMMSSSSS_BEFORE, YYYYMMDDHHMMSS_AFTER)));
                }

                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).wmsCloDtTmTs_B1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxDtTm_B2 //
                            , (ZYPDateUtil.DateFormatter(cMsg.B.no(i).wmsCloDtTmTs_B1.getValue()//
                                    , YYYYMMDDHHMMSS_BEFORE, YYYYMMDDHHMMSS_AFTER)));
                }
            } else {
                break;
            }
        }

        for (int i = 0; i < cMsg.D.getValidCount(); i++) {

            if (i < sMsg.D.getValidCount()) {
                EZDMsg.copy(sMsg.D.no(i), null, cMsg.D.no(i), null);

                if (ZYPCommonFunc.hasValue(cMsg.D.no(i).wmsTrxDtTmTs_D1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).xxDtTm_D1 //
                            , (ZYPDateUtil.DateFormatter(cMsg.D.no(i).wmsTrxDtTmTs_D1.getValue()//
                                    , YYYYMMDDHHMMSS_BEFORE, YYYYMMDDHHMMSS_AFTER)));
                }

                if (ZYPCommonFunc.hasValue(cMsg.D.no(i).ezInTime_D1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).xxDtTm_D2 //
                            , (ZYPDateUtil.DateFormatter(cMsg.D.no(i).ezInTime_D1.getValue()//
                                    , YYYYMMDDHHMMSSSSS_BEFORE, YYYYMMDDHHMMSS_AFTER)));
                }
            } else {
                break;
            }
        }
    }

    /**
     * copyFromSmsgOntoCmsg Copy data From Smsg Onto Cmsg
     * @param cMsg NLGL0020CMsg
     */
    public static void copyFromSMsgOntoCmsgDNLD(NLGL0020CMsg cMsg) {

        int rowCnt = 0;

        for (int i = 0; i < cMsg.G.getValidCount(); i++) {

            if (i < cMsg.G.getValidCount()) {

                if (ZYPCommonFunc.hasValue(cMsg.G.no(i).ezInTime_G1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.G.no(i).xxDtTm_G1 //
                            , (ZYPDateUtil.DateFormatter(cMsg.G.no(i).ezInTime_G1.getValue()//
                                    , YYYYMMDDHHMMSSSSS_BEFORE, YYYYMMDDHHMMSS_AFTER)));
                }

                if (ZYPCommonFunc.hasValue(cMsg.G.no(i).wmsCloDtTmTs_G1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.G.no(i).xxDtTm_G2 //
                            , (ZYPDateUtil.DateFormatter(cMsg.G.no(i).wmsCloDtTmTs_G1.getValue()//
                                    , YYYYMMDDHHMMSS_BEFORE, YYYYMMDDHHMMSS_AFTER)));
                }
                rowCnt++;
            } else {
                break;
            }
        }
        cMsg.G.setValidCount(rowCnt);
    }

    /**
     * copyFromSmsgOntoCmsg Copy data From Smsg Onto Cmsg
     * @param cMsg NLGL0020CMsg
     * @param sMsg NLGL0020SMsg
     * @param glblCmpyCd Global Company Code
     */
    public static void copyFromSMsgOntoCmsgUPD(NLGL0020CMsg cMsg, NLGL0020SMsg sMsg, String glblCmpyCd) {

        int rowCnt = 0;

        for (int i = 0; i < cMsg.I.getValidCount(); i++) {

            if (i < sMsg.I.getValidCount()) {
                EZDMsg.copy(sMsg.I.no(i), null, cMsg.I.no(i), null);

                if (ZYPCommonFunc.hasValue(cMsg.I.no(i).wmsTrxDtTmTs_I1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.I.no(i).xxDtTm_I1 //
                            , (ZYPDateUtil.DateFormatter(cMsg.I.no(i).wmsTrxDtTmTs_I1.getValue()//
                                    , YYYYMMDDHHMMSS_BEFORE, YYYYMMDDHHMMSS_AFTER)));
                }

                if (ZYPCommonFunc.hasValue(cMsg.I.no(i).ezInTime_I1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.I.no(i).xxDtTm_I2 //
                            , (ZYPDateUtil.DateFormatter(cMsg.I.no(i).ezInTime_I1.getValue()//
                                    , YYYYMMDDHHMMSSSSS_BEFORE, YYYYMMDDHHMMSS_AFTER)));
                }
                cMsg.I.no(i).wmsTaskCd_I1.clear();
                cMsg.I.no(i).crsSvcTaskNm_I1.clear();
                cMsg.I.no(i).wmsStkStsCd_I1.clear();
                cMsg.I.no(i).wmsStkStsNm_I1.clear();
                cMsg.I.no(i).wmsOrdTpCd_I1.clear();
                cMsg.I.no(i).wmsPrchTpNm_I2.clear();
                createWmsTaskCodePulldownList(cMsg.I.no(i).wmsTaskCd_I1, cMsg.I.no(i).crsSvcTaskNm_I1, glblCmpyCd, false);
                ZYPCodeDataUtil.createCodePulldownList(WMS_STK_STS.class, cMsg.I.no(i).wmsStkStsCd_I1, cMsg.I.no(i).wmsStkStsNm_I1);
                //ZYPCodeDataUtil.createCodePulldownList(WMS_PRCH_TP.class, cMsg.I.no(i).wmsOrdTpCd_I1, cMsg.I.no(i).fillerL075If_I2);
                NLGL0020CommonLogic.createOrderTypePulldownList(cMsg, glblCmpyCd, TAB_ID_UPD);
                cMsg.I.no(i).wmsTaskCd_I2.setValue(cMsg.I.no(i).wmsTaskCd_I3.getValue());
                cMsg.I.no(i).wmsStkStsCd_I2.setValue(cMsg.I.no(i).wmsStkStsCd_I3.getValue());
                
                //cMsg.I.no(i).sceOrdTpCd_I2.setValue(cMsg.I.no(i).sceOrdTpCd_I1.getValue());

                if (PROC_STS.COMPLEATED.equals(cMsg.I.no(i).procStsCd_I1.getValue())) {
                    cMsg.I.no(i).xxProcFlgNm_I1.setValue(PROCEESSED);
                } else if (PROC_STS.IN_COMPLETED.equals(cMsg.I.no(i).procStsCd_I1.getValue())) {
                    cMsg.I.no(i).xxProcFlgNm_I1.setValue(UNPROCESSED);
                } else if (PROC_STS.ERROR.equals(cMsg.I.no(i).procStsCd_I1.getValue())) {
                    cMsg.I.no(i).xxProcFlgNm_I1.setValue(ERROR);
                } else {
                    cMsg.I.no(i).xxProcFlgNm_I1.setValue(PROCESSING);
                }

                if (ZYPConstant.FLG_OFF_0.equals(sMsg.I.no(i).ezCancelFlag_I1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).xxExstFlg_I1, VAL_Actv_);
                    ZYPEZDItemValueSetter.setValue(cMsg.I.no(i).xxExstFlg_I1, VAL_Actv_);
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).xxExstFlg_I1, VAL_InActv_);
                    ZYPEZDItemValueSetter.setValue(cMsg.I.no(i).xxExstFlg_I1, VAL_InActv_);
                }

                rowCnt++;
            } else {
                break;
            }
        }
        cMsg.I.setValidCount(rowCnt);
    }

    /**
     * The method explanation: This method gets the PO List Data of
     * header.
     * @param bizMsg NLGL0020CMsgmethod
     * @param globalMsg NLGL0020SMsgmethod
     * @param glgbCmpyCd Global Company Code
     */
    public static void getPoList(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg, String glgbCmpyCd) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(globalMsg.A);
        bizMsg.xxPageShowFromNum_A1.setValue(BigDecimal.ZERO);
        bizMsg.xxPageShowToNum_A1.setValue(BigDecimal.ZERO);
        bizMsg.xxPageShowOfNum_A1.setValue(BigDecimal.ZERO);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        if (!WH_ALL_SELECTION_VALUE.equals(bizMsg.whCd_02.getValue())) {
            ssmParam.put(DB_PARAM_WH_CD, bizMsg.whCd_02.getValue());
        }
        ssmParam.put(DB_PARAM_PO_ID, bizMsg.wmsPoId_01.getValue());
        ssmParam.put(DB_PARAM_START_DT, bizMsg.xxSoSrchFromDt_01.getValue());
        ssmParam.put(DB_PARAM_END_DT, bizMsg.xxSoSrchThruDt_01.getValue());
        ssmParam.put(DB_PARAM_PRCH_CD, bizMsg.sceOrdTpCd_02.getValue());
        ssmParam.put(DB_PARAM_MDSE_CD, bizMsg.wmsMdseCd_01.getValue());
        ssmParam.put(DB_PARAM_TRX_CD, bizMsg.wmsTrxCd_02.getValue());
        ssmParam.put(DB_PARAM_CHECK, bizMsg.xxChkBox_01.getValue());
        ssmParam.put(DB_PARAM_DATE_LIST, bizMsg.xxSrchRqstDtTpCd_02.getValue());
        ssmParam.put(DB_PARAM_SEPARATE, SQL_DELIMITER);
        ssmParam.put(DB_PARAM_ROWNUM, globalMsg.A.length() + 1);
        if (!WH_ALL_SELECTION_VALUE.equals(bizMsg.rtlWhCd_01.getValue())) {
            ssmParam.put(DB_PARAM_RTL_WH_CD, bizMsg.rtlWhCd_01.getValue());
        }
        ssmParam.put(DB_PARAM_INVTY_OWNR_CD, bizMsg.invtyOwnrCd_01.getValue());

        S21SsmEZDResult resultQuery = NLGL0020Query.getInstance().getPoList(globalMsg, ssmParam);

        if (!resultQuery.isCodeNotFound()) {
            // 200over
            int queryResCnt = resultQuery.getQueryResultCount();

            if (queryResCnt > globalMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                queryResCnt = globalMsg.A.length();
            }
            // 1page copy��lobalMsg -> bizMsg��
            int rowCnt = globalMsg.A.getValidCount();

            if (globalMsg.A.getValidCount() > bizMsg.A.length()) {
                rowCnt = bizMsg.A.length();
            }
            StringBuffer sb;
            for (int i =0 ; i< rowCnt;i++){
                sb=new StringBuffer();
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).inbdPoMsgTxt_A1)) {
                    sb.append(globalMsg.A.no(i).inbdPoMsgTxt_A1.getValue());
                    sb.append(",");
                }
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).inbdPoMsgTxt_A2)) {
                    sb.append(globalMsg.A.no(i).inbdPoMsgTxt_A2.getValue());
                    sb.append(",");
                }
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).inbdPoMsgTxt_A3)) {
                    sb.append(globalMsg.A.no(i).inbdPoMsgTxt_A3.getValue());
                    sb.append(",");
                }
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).inbdPoMsgTxt_A4)) {
                    sb.append(globalMsg.A.no(i).inbdPoMsgTxt_A4.getValue());
                }
                if (sb.length() > 0){
                    if (",".equals(sb.substring(sb.length()-1, sb.length())))
                    {
                        globalMsg.A.no(i).xxMsgTxt_A1.setValue(sb.substring(0, sb.length()-1));
                    } else {
                        globalMsg.A.no(i).xxMsgTxt_A1.setValue(sb.toString().trim());
                    }
                    
                } else {
                    globalMsg.A.no(i).xxMsgTxt_A1.setValue("");
                }
            }

            for (int i = 0; i < rowCnt; i++) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.xxPageShowFromNum_A1.setValue(BigDecimal.ONE);
            bizMsg.xxPageShowOfNum_A1.setValue(queryResCnt);
//            bizMsg.whCd_02.setValue(globalMsg.A.no(0).whCd_A1.getValue());
//            bizMsg.rtlWhCd_01.setValue(globalMsg.A.no(0).rtlWhCd_A1.getValue());
//            bizMsg.invtyOwnrCd_01.setValue(globalMsg.A.no(0).invtyOwnrCd_A1.getValue());
            copyFromSMsgOntoCmsg(bizMsg, globalMsg, glgbCmpyCd);
        } else {
            bizMsg.setMessageInfo(NZZM0000E);
            bizMsg.xxPageShowFromNum_A1.clear();
            bizMsg.xxPageShowToNum_A1.clear();
            bizMsg.xxPageShowOfNum_A1.clear();
            bizMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
        }
    }

    /**
     * The method explanation: This method gets the PO Edit Data of
     * Order Header(PO Status)
     * @param bizMsg NLGL0020CMsgmethod
     * @param globalMsg NLGL0020SMsgmethod
     * @param glgbCmpyCd Global Company Code
     */
    public static void getSTSOrderHeader(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg, String glgbCmpyCd) {

        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(globalMsg.B);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_PARAM_WH_CD, bizMsg.whCd_02.getValue());
        ssmParam.put(DB_PARAM_SEPARATE, SQL_DELIMITER);
        ssmParam.put(DB_PARAM_SQ_NUM, bizMsg.wmsSqNum_B1.getValue());
        ssmParam.put(DB_PARAM_PO_ID, bizMsg.wmsPoId_B1.getValue());
        ssmParam.put(DB_PARAM_TASK_CD, TASK_CD_RCVD);
        ssmParam.put(DB_PARAM_OPEN, STATUS_VAL_OPEN);
        ssmParam.put(DB_PARAM_PARTIAL, STATUS_VAL_PARTIAL);
        ssmParam.put(DB_PARAM_CLOSED, STATUS_VAL_CLOSED);
        if (!WH_ALL_SELECTION_VALUE.equals(bizMsg.rtlWhCd_01.getValue())) {
            ssmParam.put(DB_PARAM_RTL_WH_CD, bizMsg.rtlWhCd_01.getValue());
        }
        ssmParam.put(DB_PARAM_INVTY_OWNR_CD, bizMsg.invtyOwnrCd_01.getValue());
        S21SsmEZDResult resultQuery = NLGL0020Query.getInstance().getOrderHeader(globalMsg, ssmParam);

        if (!resultQuery.isCodeNotFound()) {
            // copy��lobalMsg -> bizMsg��
            int rowCnt = globalMsg.B.getValidCount();

            if (globalMsg.B.getValidCount() > bizMsg.B.length()) {
                rowCnt = bizMsg.B.length();
            }

            StringBuffer sb;
            for (int i =0 ; i< rowCnt;i++){
                sb=new StringBuffer();
                if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).inbdPoMsgTxt_B1)) {
                    sb.append(globalMsg.B.no(i).inbdPoMsgTxt_B1.getValue());
                    sb.append(",");
                }
                if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).inbdPoMsgTxt_B2)) {
                    sb.append(globalMsg.B.no(i).inbdPoMsgTxt_B2.getValue());
                    sb.append(",");
                }
                if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).inbdPoMsgTxt_B3)) {
                    sb.append(globalMsg.B.no(i).inbdPoMsgTxt_B3.getValue());
                    sb.append(",");
                }
                if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).inbdPoMsgTxt_B4)) {
                    sb.append(globalMsg.B.no(i).inbdPoMsgTxt_B4.getValue());
                }
                if (sb.length() > 0){
                    if (",".equals(sb.substring(sb.length()-1, sb.length())))
                    {
                        globalMsg.B.no(i).xxMsgTxt_B1.setValue(sb.substring(0, sb.length()-1));
                    } else {
                        globalMsg.B.no(i).xxMsgTxt_B1.setValue(sb.toString().trim());
                    }
                    
                } else {
                    globalMsg.B.no(i).xxMsgTxt_B1.setValue("");
                }
            }
            
            for (int i = 0; i < rowCnt; i++) {
                EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(i), null);
            }
            bizMsg.B.setValidCount(rowCnt);
        } else {
            bizMsg.setMessageInfo(NZZM0000E);
            bizMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
        }
    }

    /**
     * The method explanation: This method gets the PO Edit Data of
     * Order Detail(PO Status)
     * @param bizMsg NLGL0020CMsgmethod
     * @param globalMsg NLGL0020SMsgmethod
     * @param glgbCmpyCd Global Company Code
     */
    public static void getSTSOrderDetail(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg, String glgbCmpyCd) {

        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(globalMsg.C);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_PARAM_WH_CD, bizMsg.whCd_02.getValue());
        ssmParam.put(DB_PARAM_SQ_NUM, bizMsg.wmsSqNum_B1.getValue());
        ssmParam.put(DB_PARAM_PO_ID, bizMsg.wmsPoId_B1.getValue());
        String flg = ZYPCodeDataUtil.getVarCharConstValue(WMS_PACK_CD_SET_OWNER_CD_FLG, glgbCmpyCd);
        if (ZYPCommonFunc.hasValue(flg)) {
            ssmParam.put(DB_PARAM_WMS_PACK_CD_SET_OWNER_CD_FLG, flg);
        } else {
            ssmParam.put(DB_PARAM_WMS_PACK_CD_SET_OWNER_CD_FLG, ZYPConstant.FLG_OFF_N);
        }

        S21SsmEZDResult resultQuery = NLGL0020Query.getInstance().getOrderDetail(globalMsg, ssmParam);

        if (!resultQuery.isCodeNotFound()) {
            // copy��lobalMsg -> bizMsg��
            int rowCnt = globalMsg.C.getValidCount();

            if (globalMsg.C.getValidCount() > bizMsg.C.length()) {
                rowCnt = bizMsg.C.length();
            }

            for (int i = 0; i < rowCnt; i++) {
                EZDMsg.copy(globalMsg.C.no(i), null, bizMsg.C.no(i), null);
            }
            bizMsg.C.setValidCount(rowCnt);
        }
    }

    /**
     * The method explanation: This method gets the PO Edit Data of
     * Order Upload Transaction(PO Status)
     * @param bizMsg NLGL0020CMsgmethod
     * @param globalMsg NLGL0020SMsgmethod
     * @param glgbCmpyCd Global Company Code
     */
    public static void getSTSUPDTransaction(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg, String glgbCmpyCd) {

        ZYPTableUtil.clear(bizMsg.D);
        ZYPTableUtil.clear(globalMsg.D);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_PARAM_WH_CD, bizMsg.whCd_02.getValue());
        ssmParam.put(DB_PARAM_PO_ID, bizMsg.wmsPoId_B1.getValue());
        ssmParam.put(DB_PARAM_EZINTIME, bizMsg.B.no(0).ezInTime_B1.getValue());
        ssmParam.put(DB_PARAM_SQ_NUM, bizMsg.wmsSqNum_B1.getValue());
        S21SsmEZDResult resultQuery = NLGL0020Query.getInstance().getSTSUPDTransaction(globalMsg, ssmParam);

        if (!resultQuery.isCodeNotFound()) {
            // copy��lobalMsg -> bizMsg��
            int rowCnt = globalMsg.D.getValidCount();

            if (globalMsg.D.getValidCount() > bizMsg.D.length()) {
                rowCnt = bizMsg.D.length();
            }

            for (int i = 0; i < rowCnt; i++) {
                EZDMsg.copy(globalMsg.D.no(i), null, bizMsg.D.no(i), null);
            }
            bizMsg.D.setValidCount(rowCnt);
        }
    }

    /**
     * The method explanation: This method gets the PO Edit Data of
     * Order Header(PO Download)
     * @param bizMsg NLGL0020CMsgmethod
     * @param globalMsg NLGL0020SMsgmethod
     * @param glgbCmpyCd Global Company Code
     */
    public static void getDNLDOrderHeader(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg, String glgbCmpyCd) {

        ZYPTableUtil.clear(bizMsg.G);
        ZYPTableUtil.clear(globalMsg.G);
        ZYPTableUtil.clear(bizMsg.F);
        ZYPTableUtil.clear(globalMsg.F);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_PARAM_WH_CD, bizMsg.whCd_02.getValue());
        ssmParam.put(DB_PARAM_SEPARATE, SQL_DELIMITER);
        ssmParam.put(DB_PARAM_SQ_NUM, bizMsg.wmsSqNum_G1.getValue());
        ssmParam.put(DB_PARAM_PO_ID, bizMsg.wmsPoId_E1.getValue());
        ssmParam.put(DB_PARAM_TASK_CD, TASK_CD_RCVD);
        ssmParam.put(DB_PARAM_OPEN, STATUS_VAL_OPEN);
        ssmParam.put(DB_PARAM_PARTIAL, STATUS_VAL_PARTIAL);
        ssmParam.put(DB_PARAM_CLOSED, STATUS_VAL_CLOSED);
        if (!WH_ALL_SELECTION_VALUE.equals(bizMsg.rtlWhCd_01.getValue())) {
            ssmParam.put(DB_PARAM_RTL_WH_CD, bizMsg.rtlWhCd_01.getValue());
        }
        ssmParam.put(DB_PARAM_INVTY_OWNR_CD, bizMsg.invtyOwnrCd_01.getValue());
        S21SsmEZDResult resultQuery = NLGL0020Query.getInstance().getOrderHeader(globalMsg, ssmParam);

        if (!resultQuery.isCodeNotFound()) {
            // copy��lobalMsg -> bizMsg��
            int rowCnt = globalMsg.B.getValidCount();

            if (globalMsg.B.getValidCount() > bizMsg.B.length()) {
                rowCnt = bizMsg.B.length();
            }

            for (int i = 0; i < rowCnt; i++) {
                EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(i), null);
                EZDMsg.copy(bizMsg.B.no(i), null, bizMsg.G.no(i), null);
            }

            for (int i = 0; i < rowCnt; i++) {
                ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).wmsPoId_G2, bizMsg.B.no(i).wmsPoId_B2);
                ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).wmsSqNum_G2, bizMsg.B.no(i).wmsSqNum_B2);
                ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).wmsPrchTpNm_G1, bizMsg.B.no(i).wmsPrchTpNm_B1);
                ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).vndCd_G1, bizMsg.B.no(i).vndCd_B1);
                ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).wmsVeslNm_G1, bizMsg.B.no(i).wmsVeslNm_B1);
                ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).wmsBolNum_G1, bizMsg.B.no(i).wmsBolNum_B1);
                ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).wmsSerNum_G1, bizMsg.B.no(i).wmsSerNum_B1);
                ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).xxIntfcItemStsDescTxt_G1, bizMsg.B.no(i).xxIntfcItemStsDescTxt_B1);
                ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).wmsTrxCd_G1, bizMsg.B.no(i).wmsTrxCd_B1);
                ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).ezInTime_G1, bizMsg.B.no(i).ezInTime_B1);
                ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).wmsResrcTxt_G1, bizMsg.B.no(i).wmsResrcTxt_B1);
                ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).wmsCloDtTmTs_G1, bizMsg.B.no(i).wmsCloDtTmTs_B1);
                ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).fill2Txt_G1, bizMsg.B.no(i).fill2Txt_B1);
                ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).rtlWhCd_G1, bizMsg.B.no(i).rtlWhCd_B1);
                ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).invtyOwnrCd_G1, bizMsg.B.no(i).invtyOwnrCd_B1);
                ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).svcConfigMstrPk_G1, bizMsg.B.no(i).svcConfigMstrPk_B1);
            }
            bizMsg.G.setValidCount(rowCnt);
        } else {
            bizMsg.setMessageInfo(NZZM0000E);
            bizMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
        }
    }

    /**
     * The method explanation: This method gets the PO Edit Data of
     * Order Detail(PO Download)
     * @param bizMsg NLGL0020CMsgmethod
     * @param globalMsg NLGL0020SMsgmethod
     * @param glgbCmpyCd Global Company Code
     */
    public static void getDNLDOrderDetail(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg, String glgbCmpyCd) {

        ZYPTableUtil.clear(bizMsg.H);
        ZYPTableUtil.clear(globalMsg.H);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_PARAM_WH_CD, bizMsg.whCd_02.getValue());
        ssmParam.put(DB_PARAM_SQ_NUM, bizMsg.wmsSqNum_G1.getValue());
        ssmParam.put(DB_PARAM_PO_ID, bizMsg.wmsPoId_E1.getValue());
        S21SsmEZDResult resultQuery = NLGL0020Query.getInstance().getOrderDetail(globalMsg, ssmParam);

        if (!resultQuery.isCodeNotFound()) {
            // copy��lobalMsg -> bizMsg��
            int rowCnt = globalMsg.C.getValidCount();

            if (globalMsg.C.getValidCount() > bizMsg.C.length()) {
                rowCnt = bizMsg.C.length();
            }

            for (int i = 0; i < rowCnt; i++) {
                EZDMsg.copy(globalMsg.C.no(i), null, bizMsg.C.no(i), null);
                EZDMsg.copy(bizMsg.C.no(i), null, bizMsg.H.no(i), null);
            }

            for (int i = 0; i < rowCnt; i++) {
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).wmsLineNum_H1, bizMsg.C.no(i).wmsLineNum_C1);
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).wmsMdseCd_H1, bizMsg.C.no(i).wmsMdseCd_C1);
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).s80StkStsCd_H1, bizMsg.C.no(i).s80StkStsCd_C1);
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).wmsInvId_H1, bizMsg.C.no(i).wmsInvId_C1);
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).wmsDoId_H1, bizMsg.C.no(i).wmsDoId_C1);
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).wmsOpenQty_H1, bizMsg.C.no(i).wmsOpenQty_C1);
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).xxIntfcOrdQty_H1, bizMsg.C.no(i).xxIntfcOrdQty_C1);
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).fill40Txt_H1, bizMsg.C.no(i).fill40Txt_C1);
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).serApvlReqFlg_H1, bizMsg.C.no(i).serApvlReqFlg_C1);
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).usrCdRefTxt_H1, bizMsg.C.no(i).usrCdRefTxt_C1);
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).invtyOwnrCd_H1, bizMsg.C.no(i).invtyOwnrCd_C1);
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).serNum_H1, bizMsg.C.no(i).serNum_C1);
            }
            bizMsg.H.setValidCount(rowCnt);
        }
    }

    /**
     * The method explanation: This method gets the PO Edit Data of
     * Order Detail(PO Upload)
     * @param bizMsg NLGL0020CMsgmethod
     * @param globalMsg NLGL0020SMsgmethod
     * @param glgbCmpyCd Global Company Code
     */
    public static void getUPDDetail(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg, String glgbCmpyCd) {

        ZYPTableUtil.clear(bizMsg.I);
        ZYPTableUtil.clear(globalMsg.I);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        ssmParam.put(DB_PARAM_WH_CD, bizMsg.whCd_02.getValue());
        ssmParam.put(DB_PARAM_PO_ID, bizMsg.wmsPoId_I1.getValue());
        ssmParam.put(DB_PARAM_CHECK, bizMsg.xxChkBox_01.getValue());
        if ("".equals(bizMsg.wmsTaskCd_I4.getValue())) {
            ssmParam.put(DB_PARAM_WMS_TASK_CD, VAL_WMS_TASK_CD_ALL);
        } else {
            ssmParam.put(DB_PARAM_WMS_TASK_CD, bizMsg.wmsTaskCd_I4.getValue());
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_I2.getValue()) && "".equals(bizMsg.xxChkBox_I3.getValue())) {
            ssmParam.put(DB_PARAM_EZCANCELFLAG, ZYPConstant.FLG_OFF_0);
        } else if ("".equals(bizMsg.xxChkBox_I2.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_I3.getValue())) {
            ssmParam.put(DB_PARAM_EZCANCELFLAG, ZYPConstant.FLG_ON_1);
        } else {
            ssmParam.put(DB_PARAM_EZCANCELFLAG, "");
        }

        S21SsmEZDResult resultQuery = NLGL0020Query.getInstance().getUPDDetail(globalMsg, ssmParam);

        if (!resultQuery.isCodeNotFound()) {
            // copy��lobalMsg -> bizMsg��
            int rowCnt = globalMsg.I.getValidCount();

            if (globalMsg.I.getValidCount() > bizMsg.I.length()) {
                rowCnt = bizMsg.I.length();
            }

            for (int i = 0; i < rowCnt; i++) {
                EZDMsg.copy(globalMsg.I.no(i), null, bizMsg.I.no(i), null);
            }
            bizMsg.I.setValidCount(rowCnt);

            for (int i = 0; i < bizMsg.I.getValidCount(); i++) {

                if (i == bizMsg.I.length()) {
                    break;
                }
                EZDMsg.copy(globalMsg.I.no(i), null, bizMsg.I.no(i), null);
            }
        }
    }

    /**
     * To Put slush("/") for Date
     * @param val String
     * @return val
     */
    public static String setDateSlush(String val) {

        if (ZYPCommonFunc.hasValue(val)) {

            if (val.length() == DATA_VALUE_INT_8) {
                val = ZYPDateUtil.DateFormatter(val, YYYYMMDD_BEFORE, YYYYMMDD_AFTER);
            } else {
                val = ZYPDateUtil.DateFormatter(val, YYYYMMDDHHMMSS_BEFORE, YYYYMMDD_AFTER);
            }
        }
        return val;
    }

    /**
     * Formatting value for fixed layout.
     * <p>
     * (supported type is String and BigDecimal)
     * @param colVals column values
     * @param sizeArray the layouting columns size array
     * @return a layouted value
     */
    public static String formatFixedLayout(Object[] colVals, int[] sizeArray) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < sizeArray.length; i++) {
            int sz = sizeArray[i];
            Object val = colVals[i];

            if (val == null) {
                val = BLANK;
            }

            if (val instanceof BigDecimal) {
                sb.append(String.format("%0" + sz + ".0f", val));
            } else {
                sb.append(String.format("%-" + sz + "." + sz + "s", val));
            }
        }
        return sb.toString();
    }

    /**
     * Check ALL check box(Item Input List)
     * @param bizMsg NLGL0020CMsg
     * @param check true = check All / fasle = true = UNcheck All
     */
    public static void setScrnItemValue_Check_UNCheck(NLGL0020CMsg bizMsg, boolean check) {

        if (check) {

            for (int i = 0; i < bizMsg.F.length(); i++) {
                bizMsg.F.no(i).xxChkBox_F1.setValue(ZYPConstant.CHKBOX_ON_Y);
            }
        } else {

            for (int i = 0; i < bizMsg.F.length(); i++) {
                bizMsg.F.no(i).xxChkBox_F1.clear();
            }
        }
    }

    /**
     * Check ALL check box(Uplaod Edit)
     * @param bizMsg NLGL0020CMsg
     * @param check true = check All / fasle = true = UNcheck All
     */
    public static void setScrnItemValue_UPD_Check_UNCheck(NLGL0020CMsg bizMsg, boolean check) {

        if (check) {

            for (int i = 0; i < bizMsg.I.length(); i++) {
                bizMsg.I.no(i).xxChkBox_I1.setValue(ZYPConstant.CHKBOX_ON_Y);
            }
        } else {

            for (int i = 0; i < bizMsg.I.length(); i++) {
                bizMsg.I.no(i).xxChkBox_I1.clear();
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

        String[][] wmsTrxArr = NLGC001001.getWmsTrxList(glgbCmpyCd, NLGC001001.WMS_INBD_OTBD_CD_INBD);

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
     * The method explanation: This method create Drop down list for
     * WMS_TASK.
     * @param cdArr Code array
     * @param nmArr Name array
     * @param glgbCmpyCd Global Company Code
     * @param addAllFlg where add ALL value
     * @param delimiter Name delimiter
     */
    public static void createWmsTaskPulldownList(EZDCStringItemArray cdArr, EZDCStringItemArray nmArr, String glgbCmpyCd, boolean addAllFlg, String delimiter) {

        String[][] wmsTaskArr = NLGC001001.getWmsTaskList(glgbCmpyCd, NLGC001001.WMS_INBD_OTBD_CD_INBD);

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
     * @param delimiter Name delimiter
     */
    public static void createWmsTaskCodePulldownList(EZDCStringItemArray cdArr, EZDCStringItemArray nmArr, String glgbCmpyCd, boolean addAllFlg) {

        String[][] wmsTaskArr = NLGC001001.getWmsTaskList(glgbCmpyCd, NLGC001001.WMS_INBD_OTBD_CD_INBD);

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
     * To create Pull down List(Order Type List for Search)
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     * @param glgbCmpyCd Global Company Code
     * @param tabname String
     */
    public static void createOrderTypePulldownList(NLGL0020CMsg bizMsg, String glgbCmpyCd, String tabName) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        S21SsmEZDResult ordType = NLGL0020Query.getInstance().getOrderTypePulldownList(ssmParam);

        List<Map> ordTypeList = (List<Map>) ordType.getResultObject();

        if (TAB_ID_LIST.equals(tabName)) {
            bizMsg.fill40Txt_01.clear();
            bizMsg.sceOrdTpCd_01.clear();
            for (int i = 0; i < ordTypeList.size(); i++) {
                Map pullDownData = ordTypeList.get(i);
                bizMsg.fill40Txt_01.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(DB_SCE_ORD_TP_CD) //
                        , DELIMITER_COLON, (String) pullDownData.get(DB_SCE_ORD_TP_NM)));
                bizMsg.sceOrdTpCd_01.no(i).setValue((String) pullDownData.get(DB_SCE_ORD_TP_CD));
            }
        } else if (TAB_ID_UPD.equals(tabName)){
            for (int i = 0; i<bizMsg.I.getValidCount(); i++) {
                bizMsg.I.no(i).wmsPrchTpNm_I2.clear();
                bizMsg.I.no(i).sceOrdTpCd_I1.clear();
                for (int j = 0; j <ordTypeList.size(); j++){
                    Map pullDownData = ordTypeList.get(j);
                    bizMsg.I.no(i).wmsPrchTpNm_I2.no(j).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(DB_SCE_ORD_TP_CD) //
                            , DELIMITER_COLON, (String) pullDownData.get(DB_SCE_ORD_TP_NM)));
                    bizMsg.I.no(i).sceOrdTpCd_I1.no(j).setValue((String) pullDownData.get(DB_SCE_ORD_TP_CD));
                    
                }
            }
            
        }
    }
}
