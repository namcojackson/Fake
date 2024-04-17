/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0270.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.blap.NSBL0270.constant.NSBL0270Constant.*;

import java.util.List;
import java.util.Map;

import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import business.blap.NSBL0270.NSBL0270CMsg;
import business.blap.NSBL0270.NSBL0270Query;
import business.blap.NSBL0270.NSBL0270SMsg;
import business.db.SVC_PRC_SHIFTTMsg;
import business.db.SVC_PRC_SHIFTTMsgArray;

/**
 *<pre>
 * Service Pricing Shift Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/18   Hitachi         K.Kasai         Create          N/A
 * 2016/12/01   Hitachi         N.Arai          Create          QC#14204
 *</pre>
 */
public class NSBL0270CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSBL0270CMsg
     * @param sMsg NSBL0270SMsg
     */
    public static void clearMsg(NSBL0270CMsg cMsg, NSBL0270SMsg sMsg) {
        cMsg.clear();
        cMsg.A.setValidCount(0);
        sMsg.clear();
        sMsg.A.setValidCount(0);
    }

    /**
     * Create Pull Down
     * @param cMsg NSBL0270CMsg
     */
    public static void createPullDown(NSBL0270CMsg cMsg) {
        createSvcLineBizPulldownList(cMsg);
    }

    private static void createSvcLineBizPulldownList(NSBL0270CMsg cMsg) {

        S21SsmEZDResult ssmResult = NSBL0270Query.getInstance().getSvcLineBiz(cMsg.glblCmpyCd.getValue());

        if (ssmResult != null && ssmResult.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
            int i = 0;
            for (Map<String, Object> rsltMap : rsltList) {
                setValue(cMsg.lineBizTpCd_H1.no(i), (String) rsltMap.get(LINE_BIZ_TP_CD));
                setValue(cMsg.lineBizTpDescTxt_H2.no(i), (String) rsltMap.get(LINE_BIZ_TP_DESC_TXT));
                i++;
            }
        }
    }

    /**
     * getSvcPrcShiftInfo
     * @param cMsg NSBL0270CMsg
     * @return SVC_PRC_SHIFTTMsgArray
     */
    public static SVC_PRC_SHIFTTMsgArray getSvcPrcShiftInfo(NSBL0270CMsg cMsg) {

        SVC_PRC_SHIFTTMsg inTMsg = new SVC_PRC_SHIFTTMsg();
        inTMsg.setSQLID("010");
        inTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        return (SVC_PRC_SHIFTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
    }

    /**
     * setDetailInfo
     * @param cMsg NSBL0270CMsg
     * @param resultArray SVC_PRC_SHIFTTMsgArray
     */
    public static void setDetailInfo(NSBL0270CMsg cMsg, SVC_PRC_SHIFTTMsgArray resultArray) {

        if (resultArray != null) {
            setParam(cMsg, resultArray);
        }
        if (resultArray.getValidCount() > cMsg.A.length()) {
            cMsg.setMessageInfo(NZZM0001W);
        }
        if (resultArray.getValidCount() == 0) {
            cMsg.setMessageInfo(NZZM0000E);
        }
    }

    /**
     * getMaxSvcPrcShiftNum
     * @param cMsg NSBL0270CMsg
     * @return SVC_PRC_SHIFTTMsgArray
     */
    public static SVC_PRC_SHIFTTMsgArray getMaxSvcPrcShiftNum(NSBL0270CMsg cMsg) {

        SVC_PRC_SHIFTTMsg inTMsg = new SVC_PRC_SHIFTTMsg();
        inTMsg.setSQLID("011");
        inTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        return  (SVC_PRC_SHIFTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
    }

    private static void setParam(NSBL0270CMsg cMsg, SVC_PRC_SHIFTTMsgArray resultArray) {

        for (int i = 0; i < resultArray.getValidCount(); i++) {
            if (i == cMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                break;
            }
            setValue(cMsg.A.no(i).ezUpTime_A, resultArray.no(i).ezUpTime);
            setValue(cMsg.A.no(i).ezUpTimeZone_A, resultArray.no(i).ezUpTimeZone);
            setValue(cMsg.A.no(i).svcPrcShiftPk_A, resultArray.no(i).svcPrcShiftPk);
            setValue(cMsg.A.no(i).svcPrcShiftNum_A, resultArray.no(i).svcPrcShiftNum);
            setValue(cMsg.A.no(i).xxChkBox_A1, resultArray.no(i).svcPrcShiftActvFlg);
            setValue(cMsg.A.no(i).xxChkBox_A2, resultArray.no(i).svcPrcShiftAhsFlg);
            setValue(cMsg.A.no(i).svcLineBizCd_A, resultArray.no(i).svcLineBizCd);
            setValue(cMsg.A.no(i).svcPrcShiftDescTxt_A, resultArray.no(i).svcPrcShiftDescTxt);
            setValue(cMsg.A.no(i).xxStartDplyTmTxt_A1, resultArray.no(i).svcPrcMonStartValTxt);
            setValue(cMsg.A.no(i).xxEndDplyTmTxt_A1, resultArray.no(i).svcPrcMonEndValTxt);
            setValue(cMsg.A.no(i).xxStartDplyTmTxt_A2, resultArray.no(i).svcPrcTueStartValTxt);
            setValue(cMsg.A.no(i).xxEndDplyTmTxt_A2, resultArray.no(i).svcPrcTueEndValTxt);
            setValue(cMsg.A.no(i).xxStartDplyTmTxt_A3, resultArray.no(i).svcPrcWedStartValTxt);
            setValue(cMsg.A.no(i).xxEndDplyTmTxt_A3, resultArray.no(i).svcPrcWedEndValTxt);
            setValue(cMsg.A.no(i).xxStartDplyTmTxt_A4, resultArray.no(i).svcPrcThuStartValTxt);
            setValue(cMsg.A.no(i).xxEndDplyTmTxt_A4, resultArray.no(i).svcPrcThuEndValTxt);
            setValue(cMsg.A.no(i).xxStartDplyTmTxt_A5, resultArray.no(i).svcPrcFriStartValTxt);
            setValue(cMsg.A.no(i).xxEndDplyTmTxt_A5, resultArray.no(i).svcPrcFriEndValTxt);
            setValue(cMsg.A.no(i).xxStartDplyTmTxt_A6, resultArray.no(i).svcPrcSatStartValTxt);
            setValue(cMsg.A.no(i).xxEndDplyTmTxt_A6, resultArray.no(i).svcPrcSatEndValTxt);
            setValue(cMsg.A.no(i).xxStartDplyTmTxt_A7, resultArray.no(i).svcPrcSunStartValTxt);
            setValue(cMsg.A.no(i).xxEndDplyTmTxt_A7, resultArray.no(i).svcPrcSunEndValTxt);
            setValue(cMsg.A.no(i).xxStartDplyTmTxt_A8, resultArray.no(i).svcPrcHolStartValTxt);
            setValue(cMsg.A.no(i).xxEndDplyTmTxt_A8, resultArray.no(i).svcPrcHolEndValTxt);
// START 2016/12/01 N.Arai [QC#14204, MOD]
            setValue(cMsg.A.no(i).xxRecHistCratTs_A, resultArray.no(i).ezInTime);
            setValue(cMsg.A.no(i).xxRecHistCratByNm_A, resultArray.no(i).ezInUserID);
            setValue(cMsg.A.no(i).xxRecHistUpdTs_A, resultArray.no(i).ezUpTime);
            setValue(cMsg.A.no(i).xxRecHistUpdByNm_A, resultArray.no(i).ezUpUserID);
            setValue(cMsg.A.no(i).xxRecHistTblNm_A, resultArray.no(i).ezTableID);
// END 2016/12/01 N.Arai [QC#14204, MOD]
            cMsg.A.setValidCount(i + 1);
        }
    }

    /**
     * formatHHmmss
     * @param item EZDTStringItem
     * @return EZDTStringItem
     */
    public static String formatHHmmss(EZDTStringItem item) {
        if (!hasValue(item)) {
            return null;
        }
        String itemStr = item.getValue();
        if (itemStr.indexOf(COLON) != -1) {
            return itemStr;
        }
        if (itemStr.length() == LENGTH_HHMMSS) {
            itemStr = ZYPDateUtil.formatDispHHmmss(itemStr.substring(0, 2), itemStr.substring(2, POINT_HHMM_S), itemStr.substring(POINT_HHMM_S, POINT_HHMMSS_S), true);
            if (itemStr.substring(POINT_HHMMSS_S, POINT_HHMMSS_E).equals(VALUE_SS)) {
                return itemStr.substring(0, POINT_HH_MM_E);
            }
            return itemStr;
        }
        itemStr = ZYPDateUtil.formatDispHHmmss(itemStr.substring(0, 2), itemStr.substring(2, POINT_HHMM_S), null, true);
        return itemStr.substring(0, POINT_HH_MM_E);
    }
}
