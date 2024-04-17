/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1390.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NSAL1390.NSAL1390CMsg;
import business.blap.NSAL1390.NSAL1390Query;
import business.blap.NSAL1390.NSAL1390SMsg;
import business.blap.NSAL1390.NSAL1390_ASMsg;
import business.blap.NSAL1390.NSAL1390_BSMsg;
import business.db.SVC_CONTR_BRTMsg;
import business.db.SVC_RGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/21   Fujitsu         T.Murai         Create          S21_NA#8661(Sol#004)
 * 2017/10/27   Hitachi         K.Kojima        Update          QC#21586
 *</pre>
 */
public class NSAL1390CommonLogic {

    /**
     * Pagenation sMsg -> cMsg
     * @param cMsg NSAL1390CMsg
     * @param sMsg NSAL1390SMsg
     * @param pageFrom int
     */
    public static void pagenation(NSAL1390CMsg cMsg, NSAL1390SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);

        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * setPagenation cMsg -> sMsg
     * @param cMsg NSAL1390CMsg
     * @param sMsg NSAL1390SMsg
     * @param pageFrom int
     */
    public static void setPagenation(NSAL1390CMsg cMsg, NSAL1390SMsg sMsg, int pageFrom) {

        int cnt = pageFrom;
        for (; cnt < pageFrom + cMsg.A.length(); cnt++) {
            if (cnt < pageFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pageFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * copy delete message
     * @param asMsg NSAL1390_ASMsg
     * @param bsMsg NSAL1390_BSMsg
     */
    public static void copyDelMsg(NSAL1390_ASMsg asMsg, NSAL1390_BSMsg bsMsg) {

        EZDMsg.copy(asMsg, "A", bsMsg, "B");
        ZYPEZDItemValueSetter.setValue(bsMsg.xxChkBox_B2, asMsg.xxChkBox_A2);
        ZYPEZDItemValueSetter.setValue(bsMsg.xxChkBox_B3, asMsg.xxChkBox_A3);
        ZYPEZDItemValueSetter.setValue(bsMsg.xxChkBox_B4, asMsg.xxChkBox_A4);
    }

    /**
     * Conversion CheckBox Value
     * @param checkValue String
     * @return Y or N
     */
    public static String convCheckBoxValue(String checkValue) {
        if (S21StringUtil.isEmpty(checkValue)) {
            return ZYPConstant.FLG_OFF_N;
        }
        return ZYPConstant.FLG_ON_Y;
    }

    /**
     * get Region Name
     * @param bizMsg NSAL1390CMsg
     * @param svcRgPk BigDecimal
     * @return Region Name
     */
    public static String getSvcRgNm(NSAL1390CMsg bizMsg, BigDecimal svcRgPk) {
        String svcRgDescTxt = null;

        SVC_RGTMsg tMsg = new SVC_RGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.svcRgPk, svcRgPk);

        SVC_RGTMsg result = (SVC_RGTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (result != null) {
            // ACTV_FLG ='Y'
            // AND FROM_DT <= SLS_DT
            // AND (THRU_DT IS NULL OR THRU_DT > SLS_DT)
            if (ZYPConstant.FLG_ON_Y.equals(result.svcRgActvFlg.getValue()) //
                    && ZYPDateUtil.compare(result.effFromDt.getValue(), bizMsg.slsDt.getValue()) <= 0 //
                    && (!ZYPCommonFunc.hasValue(result.effThruDt) || ZYPDateUtil.compare(result.effThruDt.getValue(), bizMsg.slsDt.getValue()) > 0)) {
                svcRgDescTxt = result.svcRgDescTxt.getValue();
            }
        }
        return svcRgDescTxt;
    }

    /**
     * get Branch Name
     * @param bizMsg NSAL1390CMsg
     * @param svcContrBrCd String
     * @return Branch Name
     */
    public static String getSvcContrBrNm(NSAL1390CMsg bizMsg, String svcContrBrCd) {
        String svcContrBrDescTxt = null;

        SVC_CONTR_BRTMsg tMsg = new SVC_CONTR_BRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.svcContrBrCd, svcContrBrCd);

        SVC_CONTR_BRTMsg result = (SVC_CONTR_BRTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (result != null) {
            // ACTV_FLG ='Y'
            // AND FROM_DT <= SLS_DT
            // AND (THRU_DT IS NULL OR THRU_DT > SLS_DT)
            if (ZYPConstant.FLG_ON_Y.equals(result.svcContrBrActvFlg.getValue()) //
                    && ZYPDateUtil.compare(result.effFromDt.getValue(), bizMsg.slsDt.getValue()) <= 0 //
                    && (!ZYPCommonFunc.hasValue(result.effThruDt) || ZYPDateUtil.compare(result.effThruDt.getValue(), bizMsg.slsDt.getValue()) > 0)) {
                svcContrBrDescTxt = result.svcContrBrDescTxt.getValue();
            }
        }
        return svcContrBrDescTxt;
    }

    // START 2017/10/27 K.Kojima [QC#21586,ADD]
    /**
     * setSvcLineBizPulldown
     * @param cMsg NSAL1390CMsg
     */
    public static void setSvcLineBizPulldown(NSAL1390CMsg cMsg) {
        cMsg.lineBizTpCd_PL.clear();
        cMsg.lineBizTpDescTxt_PL.clear();

        List<Map<String, Object>> dataList = NSAL1390Query.getInstance().getSvcLineBiz(cMsg.glblCmpyCd.getValue());
        int i = 0;
        for (Map<String, Object> data : dataList) {
            ZYPEZDItemValueSetter.setValue(cMsg.lineBizTpCd_PL.no(i), (String) data.get("LINE_BIZ_TP_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.lineBizTpDescTxt_PL.no(i), (String) data.get("LINE_BIZ_TP_DESC_TXT"));
            i++;
        }
    }
    // END 2017/10/27 K.Kojima [QC#21586,ADD]
}
