/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1860;

import static business.blap.NWAL1860.constant.NWAL1860Constant.DAY_MAX;
import static business.blap.NWAL1860.constant.NWAL1860Constant.YEAR_MAX;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_COND_CONSTTMsg;
import business.db.DS_COND_CONSTTMsgArray;
import business.db.SHPG_INTVLTMsg;
import business.db.SHPG_INTVLTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_INTVL;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1860BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Fujitsu         Y.Taoka         Create          N/A
 * 2018/04/03   Fujitsu         K.Ishizuka      Update          QC#23809
 *</pre>
 */
public class NWAL1860BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1860CMsg bizMsg = (NWAL1860CMsg) cMsg;

            if ("NWAL1860_INIT".equals(screenAplID)) {
                doProcess_NWAL1860_INIT(bizMsg);

            } else if ("NWAL1860Scrn00_CMN_Close".equals(screenAplID)) {
                doProcess_NWAL1860Scrn00_CMN_Close(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1860_INIT(NWAL1860CMsg bizMsg) {

        /// Create PullDown[Shipping Interval]
        // 2018/04/03 S21_NA#23809 Mod Start
        // ZYPCodeDataUtil.createPulldownList(SHPG_INTVL.class, bizMsg.shpgIntvlCd_P, bizMsg.shpgIntvlDescTxt_P);
        Map<String, String> conditionMap = new HashMap<String, String>();
        conditionMap.put("EZCANCELFLAG", "0");
        conditionMap.put("GLBL_CMPY_CD", getGlobalCompanyCode());
        SHPG_INTVLTMsgArray shpgIntvlList = (SHPG_INTVLTMsgArray) S21CodeTableAccessor.findByCondition("SHPG_INTVL", conditionMap);
        if (shpgIntvlList != null && 0 < shpgIntvlList.length()) {
            int cnt = 0;
            for (int i = 0; i < shpgIntvlList.length(); i++) {
                if (SHPG_INTVL.UPON_REQUEST_BY_CUSTOMER.equals(shpgIntvlList.no(i).shpgIntvlCd.getValue())) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(bizMsg.shpgIntvlCd_P.no(cnt), shpgIntvlList.no(i).shpgIntvlCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.shpgIntvlDescTxt_P.no(cnt), shpgIntvlList.no(i).shpgIntvlDescTxt);
                cnt++;
            }
        }
        // 2018/04/03 S21_NA#23809 Mod End
        

        // Create PullDown[Day]
        int day = 0;
        while (day < DAY_MAX) {
            day++;
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDay_P1.no(day - 1), String.format("%1$02d", day));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDay_P2.no(day - 1), String.format("%1$02d", day));
            if (bizMsg.xxDay_P1.length() <= day) {
                break;
            }
        }
        // Create PullDown[Month]
        DS_COND_CONSTTMsg dsCondConst = new DS_COND_CONSTTMsg();
        dsCondConst = new DS_COND_CONSTTMsg();
        dsCondConst.setSQLID("003");
        dsCondConst.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        dsCondConst.setConditionValue("dsCondConstGrpId01", "NWAL1860_MONTH");
        DS_COND_CONSTTMsgArray dsCondConstList = (DS_COND_CONSTTMsgArray) EZDTBLAccessor.findByCondition(dsCondConst);
        if (dsCondConstList != null &&  0 < dsCondConstList.getValidCount()) {
            for (int i = 0; i < dsCondConstList.getValidCount(); i++) {
                Map<String, String> tMsgKeys = new HashMap<String, String>();
                tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "dsCondConstCd");
                tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "dsCondConstDescTxt");
                ZYPPulldownValueSetter.set(dsCondConstList, tMsgKeys, bizMsg.xxMthDt_P, bizMsg.xxMthDplyDescTxt_P);
            }
        }

        // Create PullDown[Yaer]
        int thisYear = Integer.parseInt(ZYPDateUtil.getSalesDate().substring(0, 4));
        int year = thisYear;
        int yearMax = year + YEAR_MAX;
        int index = 0;
        while (year < yearMax) {
            if (bizMsg.xxYrDt_P1.length() <= index) {
                break;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.xxYrDt_P1.no(index), String.valueOf(year));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxYrDt_P2.no(index), String.valueOf(year));
            year++;
            index++;
        }
    }

    /**
     * CMN_Close Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1860Scrn00_CMN_Close(NWAL1860CMsg bizMsg) {

        //Get SHPG_INTVL_MTH_NUM
        SHPG_INTVLTMsg shpgIntvl = new SHPG_INTVLTMsg();
        ZYPEZDItemValueSetter.setValue(shpgIntvl.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(shpgIntvl.shpgIntvlCd, bizMsg.shpgIntvlCd);
        shpgIntvl = (SHPG_INTVLTMsg) S21CodeTableAccessor.findByKey(shpgIntvl);
        if (shpgIntvl == null || !ZYPCommonFunc.hasValue(shpgIntvl.shpgIntvlMthNum) || shpgIntvl.shpgIntvlMthNum.getValueInt() <= 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.shpgIntvlMthNum, BigDecimal.ONE);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.shpgIntvlMthNum, shpgIntvl.shpgIntvlMthNum);
        }
    }

}
