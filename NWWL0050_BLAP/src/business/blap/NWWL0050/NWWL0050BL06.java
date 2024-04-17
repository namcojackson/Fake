/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0050;

import static business.blap.NWWL0050.constant.NWWL0050Constant.NWWM0020E;
import static business.blap.NWWL0050.constant.NWWL0050Constant.NWWM0021E;
import static business.blap.NWWL0050.constant.NWWL0050Constant.NWWM0030I;
import static business.blap.NWWL0050.constant.NWWL0050Constant.NWWM0031E;
import static business.blap.NWWL0050.constant.NWWL0050Constant.NZZM0003E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWWL0050.common.NWWL0050CommonLogic;
import business.db.NTFY_DIST_LISTTMsg;
import business.db.NTFY_DIST_LIST_ASGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWWL0050BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/05   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0050BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWWL0050CMsg bizMsg = (NWWL0050CMsg) cMsg;
            NWWL0050SMsg glblMsg = (NWWL0050SMsg) sMsg;

            if ("NWWL0050Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWWL0050Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NWWL0050Scrn00_Del_Line".equals(screenAplID)) {
                doProcess_NWWL0050Scrn00_Del_Line(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0050Scrn00_CMN_Submit(NWWL0050CMsg bizMsg, NWWL0050SMsg glblMsg) {
        // duplicate check
        S21SsmEZDResult ssmResult = NWWL0050Query.getInstance().getDupDistList(bizMsg);
        BigDecimal dupPk = (BigDecimal) ssmResult.getResultObject();
        if (dupPk != null) {
            bizMsg.ntfyDistListNm.setErrorInfo(1, NWWM0031E, new String[] {"Distribution List Name" });
            return;
        }

        BigDecimal distListPk = null;

        // Distribution List
        if (ZYPCommonFunc.hasValue(bizMsg.ntfyDistListPk)) {
            distListPk = bizMsg.ntfyDistListPk.getValue();

            if (NWWL0050CommonLogic.isChangeDistList(bizMsg, glblMsg)) {
                // Distribution List Update
                NTFY_DIST_LISTTMsg distTMsg = new NTFY_DIST_LISTTMsg();
                ZYPEZDItemValueSetter.setValue(distTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(distTMsg.ntfyDistListPk, bizMsg.ntfyDistListPk.getValue());

                distTMsg = (NTFY_DIST_LISTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(distTMsg);

                // Check time stamp
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime.getValue(), bizMsg.ezUpTimeZone.getValue(), distTMsg.ezUpTime.getValue(), distTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.ntfyDistListNm.setErrorInfo(1, NZZM0003E);
                    return;
                }

                NWWL0050CommonLogic.setDistListTMsg(bizMsg, distTMsg);

                EZDTBLAccessor.update(distTMsg);
                if (!RTNCD_NORMAL.equals(distTMsg.getReturnCode())) {
                    bizMsg.ntfyDistListNm.setErrorInfo(1, NWWM0020E, new String[] {"NTFY_DIST_LIST" });
                    return;
                }
            }

        } else {
            // Distribution List Insert
            NTFY_DIST_LISTTMsg distTMsg = new NTFY_DIST_LISTTMsg();
            distListPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.NTFY_DIST_LIST_SQ);
            ZYPEZDItemValueSetter.setValue(distTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(distTMsg.ntfyDistListPk, distListPk);
            String distListId = ZYPMaxTenDigitsNumbering.getUniqueID(getGlobalCompanyCode(), "NTFY_DIST_LIST_ID");
            ZYPEZDItemValueSetter.setValue(distTMsg.ntfyDistListId, distListId);
            NWWL0050CommonLogic.setDistListTMsg(bizMsg, distTMsg);

            EZDTBLAccessor.insert(distTMsg);

            if (!RTNCD_NORMAL.equals(distTMsg.getReturnCode())) {
                bizMsg.ntfyDistListNm.setErrorInfo(1, NWWM0021E, new String[] {"NTFY_DIST_LIST" });
                return;
            }

            // set Distribution List ID
            ZYPEZDItemValueSetter.setValue(bizMsg.ntfyDistListId, distListId);

        }

        // Assignments
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWWL0050_ACMsg bizAMsg = bizMsg.A.no(i);

            if (ZYPCommonFunc.hasValue(bizAMsg.ntfyDistListAsgPk_A)) {
                // Assignments Update
                if (NWWL0050CommonLogic.isChangeDistListAsg(bizAMsg, glblMsg)) {
                    NTFY_DIST_LIST_ASGTMsg asgTMsg = new NTFY_DIST_LIST_ASGTMsg();
                    ZYPEZDItemValueSetter.setValue(asgTMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(asgTMsg.ntfyDistListAsgPk, bizAMsg.ntfyDistListAsgPk_A.getValue());

                    asgTMsg = (NTFY_DIST_LIST_ASGTMsg) EZDTBLAccessor.findByKeyForUpdate(asgTMsg);

                    // Check time stamp
                    if (!ZYPDateUtil.isSameTimeStamp(bizAMsg.ezUpTime_A.getValue(), bizAMsg.ezUpTimeZone_A.getValue(), asgTMsg.ezUpTime.getValue(), asgTMsg.ezUpTimeZone.getValue())) {
                        bizAMsg.ntfyDistQlfyCd_A.setErrorInfo(1, NZZM0003E);
                        return;
                    }

                    NWWL0050CommonLogic.setDistListAsgTMsg(bizAMsg, asgTMsg);

                    EZDTBLAccessor.update(asgTMsg);

                    if (!RTNCD_NORMAL.equals(asgTMsg.getReturnCode())) {
                        bizMsg.ntfyDistListNm.setErrorInfo(1, NWWM0020E, new String[] {"NTFY_DIST_LIST_ASG" });
                        return;
                    }
                }

            } else {
                // Assignments Insert
                NTFY_DIST_LIST_ASGTMsg asgTMsg = new NTFY_DIST_LIST_ASGTMsg();
                ZYPEZDItemValueSetter.setValue(asgTMsg.glblCmpyCd, getGlobalCompanyCode());
                BigDecimal asgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.NTFY_DIST_LIST_ASG_SQ);
                ZYPEZDItemValueSetter.setValue(asgTMsg.ntfyDistListAsgPk, asgPk);
                ZYPEZDItemValueSetter.setValue(asgTMsg.ntfyDistListPk, distListPk);
                NWWL0050CommonLogic.setDistListAsgTMsg(bizAMsg, asgTMsg);

                EZDTBLAccessor.insert(asgTMsg);

                if (!RTNCD_NORMAL.equals(asgTMsg.getReturnCode())) {
                    bizMsg.ntfyDistListNm.setErrorInfo(1, NWWM0021E, new String[] {"NTFY_DIST_LIST_ASG" });
                    return;
                }

                ZYPEZDItemValueSetter.setValue(bizAMsg.ntfyDistListAsgPk_A, asgPk);
            }
        }
    }

    /**
     * Del_Line Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWWL0050Scrn00_Del_Line(NWWL0050CMsg bizMsg, NWWL0050SMsg glblMsg) {

        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.xxYesNoCd.getValue())) {
            bizMsg.setMessageInfo(NWWM0030I);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn, bizMsg.xxRadioBtn.getValue());
            return;

        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd, ZYPConstant.FLG_OFF_N);
        }

        int selectRow = bizMsg.xxRadioBtn.getValueInt();

        NWWL0050_ACMsg bizAMsg = bizMsg.A.no(selectRow);

        if (ZYPCommonFunc.hasValue(bizAMsg.ntfyDistListAsgPk_A)) {
            NTFY_DIST_LIST_ASGTMsg delTMsg = new NTFY_DIST_LIST_ASGTMsg();
            ZYPEZDItemValueSetter.setValue(delTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(delTMsg.ntfyDistListAsgPk, bizAMsg.ntfyDistListAsgPk_A.getValue());

            delTMsg = (NTFY_DIST_LIST_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(delTMsg);

            // Check time stamp
            if (!ZYPDateUtil.isSameTimeStamp(bizAMsg.ezUpTime_A.getValue(), bizAMsg.ezUpTimeZone_A.getValue(), delTMsg.ezUpTime.getValue(), delTMsg.ezUpTimeZone.getValue())) {
                bizMsg.xxRadioBtn.setErrorInfo(1, NZZM0003E);
                return;
            }

            int delCount = S21FastTBLAccessor.removeLogical(new NTFY_DIST_LIST_ASGTMsg[] {delTMsg });

            if (delCount <= 0) {
                bizMsg.xxRadioBtn.setErrorInfo(1, NWWM0021E, new String[] {"NTFY_DIST_LIST_ASG" });
                return;
            }

        }

        List<Integer> selectRows = new ArrayList<Integer>();
        selectRows.add(selectRow);
        ZYPTableUtil.deleteRows(bizMsg.A, selectRows);
    }

}
