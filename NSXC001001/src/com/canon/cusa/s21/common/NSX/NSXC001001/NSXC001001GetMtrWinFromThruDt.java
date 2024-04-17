/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsgArray;
import business.db.DS_WIN_DAYSTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Get Meter Window From/Thru Date
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/13/2015   Hitachi         K.Kishimoto     Create          N/A
 * 2017/08/08   Hitachi         A.Kohinata      Update          QC#18799
 * 2023/05/09   Hitachi         K.Kitachi       Update          QC#61469
 *</pre>
 */
public class NSXC001001GetMtrWinFromThruDt {

    // del start 2017/08/08 QC#18799
//    /**
//     * Start Meter Read Window Before Days
//     */
//    private static final String BLLG_MTR_READ_WINDOW_BEF_DAYS = "BLLG_MTR_READ_WINDOW_BEF_DAYS";
    // del end 2017/08/08 QC#18799

    /**
     * Start Meter Read Window After Days
     */
    private static final String BLLG_MTR_READ_WINDOW_AFT_DAYS = "BLLG_MTR_READ_WINDOW_AFT_DAYS";

    // add start 2017/08/08 QC#18799
    /**
     * S21SsmBatchClient object.
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001GetMtrWinFromThruDt.class);
    // add end 2017/08/08 QC#18799

    /**
     * Get Meter Window By Date
     * @param mtrWinInfo Meter Window Information
     */
    public static void getMtrWinByDate(MtrWinInfo mtrWinInfo) {
        clacMtrWin(mtrWinInfo);
        return;
    }

    /**
     * Get Meter Window By DsContrDtlPk
     * @param mtrWinInfo Meter Window Information
     */
    public static void getMtrWinByDsContrDtlPk(MtrWinInfo mtrWinInfo) {
        if (!hasValue(mtrWinInfo.getGlblCmpyCd()) || !hasValue(mtrWinInfo.getDsContrDtlPk()) || !hasValue(mtrWinInfo.getBaseDt())) {
            return;
        }
        DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTmsg = getDsContrBllgSchdTmsg(mtrWinInfo.getGlblCmpyCd(), mtrWinInfo.getDsContrDtlPk(), mtrWinInfo.getBaseDt());
        if (dsContrBllgSchdTmsg == null) {
            return;
        }
        mtrWinInfo.setBllgFromDt(dsContrBllgSchdTmsg.bllgSchdFromDt.getValue());
        mtrWinInfo.setBllgThruDt(dsContrBllgSchdTmsg.bllgSchdThruDt.getValue());
        clacMtrWin(mtrWinInfo);
        return;
    }

    private static void clacMtrWin(MtrWinInfo mtrWinInfo) {
        // mod start 2017/08/08 QC#18799
        if (!hasValue(mtrWinInfo.getBllgFromDt()) || !hasValue(mtrWinInfo.getBllgThruDt()) || !hasValue(mtrWinInfo.getDsContrBllgSchdPk()) || !hasValue(mtrWinInfo.getBaseDt())) {
            return;
        }
        // mod end 2017/08/08 QC#18799

        // mod start 2017/08/08 QC#18799
//        BigDecimal winBefAot = ZYPCodeDataUtil.getNumConstValue(BLLG_MTR_READ_WINDOW_BEF_DAYS, mtrWinInfo.getGlblCmpyCd());
        BigDecimal winBefAot = getWinBefAot(mtrWinInfo);
        // mod end 2017/08/08 QC#18799
        int winBefDays = 0;
        if (hasValue(winBefAot)) {
            winBefDays = winBefAot.intValue();
        }
        BigDecimal winAftAot = ZYPCodeDataUtil.getNumConstValue(BLLG_MTR_READ_WINDOW_AFT_DAYS, mtrWinInfo.getGlblCmpyCd());
        int winAftDays = 0;
        if (hasValue(winAftAot)) {
            winAftDays = winAftAot.intValue();
        }
        if (!hasValue(winBefAot) && !hasValue(winAftAot)) {
            mtrWinInfo.setMtrWinFromDt(mtrWinInfo.getBllgFromDt());
            mtrWinInfo.setMtrWinThruDt(mtrWinInfo.getBllgThruDt());
        } else {
            mtrWinInfo.setMtrWinFromDt(ZYPDateUtil.addDays(mtrWinInfo.getBllgThruDt(), -winBefDays));
            mtrWinInfo.setMtrWinThruDt(ZYPDateUtil.addDays(mtrWinInfo.getBllgThruDt(), winAftDays));
        }
        return;
    }

    private static DS_CONTR_BLLG_SCHDTMsg getDsContrBllgSchdTmsg(String glblCmpyCd, BigDecimal dsContrDtlPk, String dt) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("bllgSchdFromDt01", dt);
        inMsg.setConditionValue("bllgSchdThruDt01", dt);
        inMsg.setConditionValue("invFlg01", FLG_ON_Y);
        DS_CONTR_BLLG_SCHDTMsgArray outArray = (DS_CONTR_BLLG_SCHDTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return outArray.no(0);
    }

    // add start 2017/08/08 QC#18799
    private static BigDecimal getWinBefAot(MtrWinInfo mtrWinInfo) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", mtrWinInfo.getGlblCmpyCd());
        prmMap.put("dsContrBllgSchdPk", mtrWinInfo.getDsContrBllgSchdPk());
        prmMap.put("salesDate", mtrWinInfo.getBaseDt());
        prmMap.put("months", BLLG_CYCLE_UOM.MONTHS);
        // START 2023/05/09 K.Kitachi [QC#61469, ADD]
        BigDecimal mtrReadWinMlyDaysAot = BigDecimal.ZERO;
        BigDecimal mtrReadWinOthDaysAot = BigDecimal.ZERO;
        DS_WIN_DAYSTMsg dsWinDaysTMsg = new DS_WIN_DAYSTMsg();
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.glblCmpyCd, mtrWinInfo.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.dsWinDaysTrgtPerTxt, "*");
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.svcLineBizCd, "*");
        dsWinDaysTMsg = (DS_WIN_DAYSTMsg) S21ApiTBLAccessor.findByKey(dsWinDaysTMsg);
        if (dsWinDaysTMsg != null) {
            mtrReadWinMlyDaysAot = dsWinDaysTMsg.mtrReadWinMlyDaysAot.getValue();
            mtrReadWinOthDaysAot = dsWinDaysTMsg.mtrReadWinOthDaysAot.getValue();
        }
        prmMap.put("mtrReadWinMlyDaysAot", mtrReadWinMlyDaysAot);
        prmMap.put("mtrReadWinOthDaysAot", mtrReadWinOthDaysAot);
        // END 2023/05/09 K.Kitachi [QC#61469, ADD]
        return (BigDecimal) SSM_CLIENT.queryObject("getWinBefAot", prmMap);
    }
    // add end 2017/08/08 QC#18799
}
