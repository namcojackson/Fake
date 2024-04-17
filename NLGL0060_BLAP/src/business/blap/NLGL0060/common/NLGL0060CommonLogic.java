/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0060.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NLGL0060.NLGL0060CMsg;
import business.blap.NLGL0060.NLGL0060Query;
import business.blap.NLGL0060.NLGL0060SMsg;
import business.blap.NLGL0060.constant.NLGL0060Constant;
import business.db.WMS_INBD_TRXTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/01/09   CITS            M.Furugoori         Create          N/A
 * </pre>
 */
public class NLGL0060CommonLogic {

    /**
     * Set Common Values to cMsg and sMsg
     * @param cMsg NLGL0060CMsg
     * @param sMsg NLGL0060SMsg
     * @param glblCmpyCd String
     */
    public static void setCommonValues(NLGL0060CMsg cMsg, NLGL0060SMsg sMsg, String glblCmpyCd) {

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(sMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
    }

    /**
     * setPulldownTaskCode
     * @param cMsg NLGL0060CMsg
     */
    public static void setPulldownTaskCode(NLGL0060CMsg cMsg) {

        cMsg.wmsTaskCd_P.clear();
        cMsg.wmsTaskNm_P.clear();

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());

        // Execute
        S21SsmEZDResult result = NLGL0060Query.getInstance().getTaskList(param);

        if (result.isCodeNormal()) {

            List<Map<String, String>> resultMap = (List<Map<String, String>>) result.getResultObject();

            for (int i = 0; i < resultMap.size(); i++) {

                ZYPEZDItemValueSetter.setValue(cMsg.wmsTaskCd_P.no(i), (String) resultMap.get(i).get("WMS_TASK_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.wmsTaskNm_P.no(i), (String) resultMap.get(i).get("WMS_TASK_NM"));
            }
        }

    }

    /**
     * setPulldownProcessStatus
     * @param cMsg NLGL0060CMsg
     */
    public static void setPulldownProcessStatus(NLGL0060CMsg cMsg) {

        cMsg.procStsCd_P.clear();
        cMsg.procStsDescTxt_P.clear();

        ZYPCodeDataUtil.createPulldownList(NLGL0060Constant.TBL_PROC_STS, cMsg.procStsCd_P, cMsg.procStsDescTxt_P, ":");
    }

    /**
     * setDateFormatter
     * @param cMsg NLGL0060CMsg
     * @param rownum int
     */
    public static void setDateFormatter(NLGL0060CMsg cMsg, int rownum) {
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(rownum).xxTsDsp19Txt_A1 //
                , (ZYPDateUtil.DateFormatter(cMsg.A.no(rownum).ezInTime_A1.getValue() //
                        , NLGL0060Constant.YYYYMMDDHHMMSSSSS_BEFORE, NLGL0060Constant.YYYYMMDDHHMMSSSSS_AFTER)));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(rownum).xxTsDsp19Txt_A2 //
                , (ZYPDateUtil.DateFormatter(cMsg.A.no(rownum).ezUpTime_A1.getValue() //
                        , NLGL0060Constant.YYYYMMDDHHMMSSSSS_BEFORE, NLGL0060Constant.YYYYMMDDHHMMSSSSS_AFTER)));
    }

    /**
     * changedValue
     * @param newVal String
     * @param oldVal String
     * @return boolean
     */
    public static boolean changedValue(String newVal, String oldVal) {

        if (ZYPCommonFunc.hasValue(newVal)) {

            if (!newVal.equals(oldVal)) {
                // Changed
                return true;
            }

        } else if (ZYPCommonFunc.hasValue(oldVal)) {
            // Changed
            return true;
        }

        return false;
    }

    /**
     * saveCurrentPageToSMsg
     * @param cMsg NLGL0060CMsg
     * @param sMsg NLGL0060SMsg
     */
    public static void saveCurrentPageToSMsg(NLGL0060CMsg cMsg, NLGL0060SMsg sMsg) {

        cMsg.clearErrorInfo();
        sMsg.clearErrorInfo();
        cMsg.A.clearErrorInfo();
        sMsg.A.clearErrorInfo();

        int fromIdx = cMsg.xxPageShowFromNum.getValueInt() - 1;

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(fromIdx + i), null);
        }
    }

    /**
     * saveSMsgToCMsg
     * @param cMsg NLGL0060CMsg
     * @param sMsg NLGL0060SMsg
     * @param fromIdx int
     */
    public static void saveSMsgToCMsg(NLGL0060CMsg cMsg, NLGL0060SMsg sMsg, int fromIdx) {

        int i = fromIdx;

        for (; i < fromIdx + cMsg.A.length(); i++) {

            if (i < sMsg.A.getValidCount()) {

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - fromIdx), null);

            } else {

                break;
            }
        }
        cMsg.A.setValidCount(i - fromIdx);
        cMsg.xxPageShowFromNum.setValue(fromIdx + 1);
        cMsg.xxPageShowToNum.setValue(fromIdx + cMsg.A.getValidCount());

    }

    /**
     * deleteRow
     * @param cMsg NLGL0060CMsg
     * @param sMsg NLGL0060SMsg
     * @param idx int
     */
    public static void deleteRow(NLGL0060CMsg cMsg, NLGL0060SMsg sMsg, int idx) {

        WMS_INBD_TRXTMsg wmsInbdTrxTmsg = new WMS_INBD_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTmsg.glblCmpyCd, sMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTmsg.wmsInbdTrxPk, sMsg.A.no(idx).wmsInbdTrxPk_A1);
        WMS_INBD_TRXTMsg resTMsg = (WMS_INBD_TRXTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(wmsInbdTrxTmsg);
        if (resTMsg == null) {
            sMsg.A.no(idx).xxChkBox_A1.setErrorInfo(1, NLGL0060Constant.NLGM0026E);
            return;
        }
        if (ZYPDateUtil.isSameTimeStamp(sMsg.A.no(idx).ezUpTime_A1.getValue(), sMsg.A.no(idx).ezUpTimeZone_A1.getValue(), resTMsg.ezUpTime.getValue(), resTMsg.ezUpTimeZone.getValue())) {
            EZDTBLAccessor.logicalRemove(resTMsg);
        } else {
            sMsg.A.no(idx).xxChkBox_A1.setErrorInfo(1, NLGL0060Constant.NLGM0026E);
            return;
        }

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(resTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NLGL0060Constant.NPAM0007E);
            return;
        }

        cMsg.setMessageInfo(NLGL0060Constant.NLGM0025I, new String[] {"Delete" });

    }

    /**
     * updateRow
     * @param cMsg NLGL0060CMsg
     * @param sMsg NLGL0060SMsg
     * @param idx int
     */
    public static void updateRow(NLGL0060CMsg cMsg, NLGL0060SMsg sMsg, int idx) {

        WMS_INBD_TRXTMsg wmsInbdTrxTmsg = new WMS_INBD_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTmsg.glblCmpyCd, sMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTmsg.wmsInbdTrxPk, sMsg.A.no(idx).wmsInbdTrxPk_A1);
        WMS_INBD_TRXTMsg resTMsg = (WMS_INBD_TRXTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(wmsInbdTrxTmsg);
        if (resTMsg == null) {
            sMsg.A.no(idx).procStsCd_A1.setErrorInfo(1, NLGL0060Constant.NLGM0026E);
            return;
        }
        if (ZYPDateUtil.isSameTimeStamp(sMsg.A.no(idx).ezUpTime_A1.getValue(), sMsg.A.no(idx).ezUpTimeZone_A1.getValue(), resTMsg.ezUpTime.getValue(), resTMsg.ezUpTimeZone.getValue())) {
            ZYPEZDItemValueSetter.setValue(resTMsg.procStsCd, sMsg.A.no(idx).procStsCd_A1);
            EZDTBLAccessor.update(resTMsg);
        } else {
            sMsg.A.no(idx).procStsCd_A1.setErrorInfo(1, NLGL0060Constant.NLGM0026E);
            return;
        }

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(resTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NLGL0060Constant.NPAM0007E);
            return;
        }

        cMsg.setMessageInfo(NLGL0060Constant.NLGM0025I, new String[] {"Update" });

    }

}
