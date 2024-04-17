/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1050;

import static business.blap.NSAL1050.constant.NSAL1050Constant.NSAM0035E;
import static business.blap.NSAL1050.constant.NSAL1050Constant.NSAM0045E;
import static business.blap.NSAL1050.constant.NSAL1050Constant.NSAM0081E;
import static business.blap.NSAL1050.constant.NSAL1050Constant.NSAM0456E;
import static business.blap.NSAL1050.constant.NSAL1050Constant.NSAM0457E;
import static business.blap.NSAL1050.constant.NSAL1050Constant.NZZM0003E;
import static business.blap.NSAL1050.constant.NSAL1050Constant.RETURN_CD_NORMAL;
import static business.blap.NSAL1050.constant.NSAL1050Constant.ZZMM0001E;
import static business.blap.NSAL1050.constant.NSAL1050Constant.ZZMM0015E;
import parts.common.EZDCMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_TERM_COND_ATTRBTMsg;
import business.db.SVC_TERM_COND_ATTRBTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TERM_ATTRB_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * T&C Attributes Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         T.Mizuki        Create          N/A
 * 2016/06/02   Hitachi         T.Tomita        Update          QC#5489
 * 2018/06/25   Hitachi         A.Kohinata      Update          QC#17369
 *</pre>
 */
public class NSAL1050BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSAL1050CMsg cMsg = (NSAL1050CMsg) arg0;
        NSAL1050SMsg sMsg = (NSAL1050SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1050Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1050Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSAL1050Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(cMsg, sMsg);
            } else if ("NSAL1050Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1050Scrn00_CMN_Submit(NSAL1050CMsg cMsg, NSAL1050SMsg sMsg) {

        // check
        if (cMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NSAM0456E);
            return;
        }

        boolean duplicateChk = false;
        for (int k = 0; k < cMsg.A.getValidCount(); k++) {
            int category = cMsg.A.no(k).svcTermCondCatgPk_1V.getValueInt();
            int seq = cMsg.A.no(k).svcTermAttrbSortNum_A.getValueInt();
            String attribute = cMsg.A.no(k).svcTermAttrbDescTxt_A.getValue();
            for (int j = k + 1; j < cMsg.A.getValidCount(); j++) {
                if (category == cMsg.A.no(j).svcTermCondCatgPk_1V.getValueInt() && seq == cMsg.A.no(j).svcTermAttrbSortNum_A.getValueInt()) {
                    cMsg.A.no(j).svcTermAttrbSortNum_A.setErrorInfo(1, NSAM0035E, new String[] {"Seq#" });
                    cMsg.A.no(k).svcTermAttrbSortNum_A.setErrorInfo(1, NSAM0035E, new String[] {"Seq#" });
                    duplicateChk = true;
                }
                if (attribute.equals(cMsg.A.no(j).svcTermAttrbDescTxt_A.getValue())) {
                    cMsg.A.no(j).svcTermAttrbDescTxt_A.setErrorInfo(1, NSAM0035E, new String[] {"T&C Attribute" });
                    cMsg.A.no(k).svcTermAttrbDescTxt_A.setErrorInfo(1, NSAM0035E, new String[] {"T&C Attribute" });
                    duplicateChk = true;
                }
            }

            SVC_TERM_COND_ATTRBTMsg stcaTMsg = new SVC_TERM_COND_ATTRBTMsg();
            stcaTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            stcaTMsg.setConditionValue("svcTermAttrbDescTxt01", attribute);
            stcaTMsg.setConditionValue("ezCancelFlag01", ZYPConstant.FLG_OFF_0);
            stcaTMsg.setSQLID("002");
            SVC_TERM_COND_ATTRBTMsgArray inMsgList = (SVC_TERM_COND_ATTRBTMsgArray) EZDTBLAccessor.findByCondition(stcaTMsg);

            if (inMsgList.length() > 1) {
                cMsg.A.no(k).svcTermAttrbDescTxt_A.setErrorInfo(1, NSAM0035E, new String[] {"T&C Attribute" });
                duplicateChk = true;
            } else if (inMsgList.length() == 1 && ZYPCommonFunc.hasValue(cMsg.A.no(k).svcTermCondAttrbPk_A)) {
                if (!cMsg.A.no(k).svcTermCondAttrbPk_A.getValue().equals(inMsgList.no(0).svcTermCondAttrbPk.getValue())) {
                    cMsg.A.no(k).svcTermAttrbDescTxt_A.setErrorInfo(1, NSAM0035E, new String[] {"T&C Attribute" });
                    duplicateChk = true;
                }
            }
        }
        if (duplicateChk) {
            return;
        }

        for (int m = 0; m < cMsg.A.getValidCount(); m++) {
            // START 2016/06/02 T.Tomita [QC#5489, MOD]
            String svcTermDataTpCd = cMsg.A.no(m).svcTermDataTpCd_1V.getValue();
            String svcTermCondDataSrcCd = cMsg.A.no(m).svcTermCondDataSrcCd_A.getValue();
            if (SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals(svcTermDataTpCd) && !ZYPCommonFunc.hasValue(svcTermCondDataSrcCd)) {
                cMsg.A.no(m).svcTermCondSrcDescTxt_A.setErrorInfo(1, NSAM0081E, new String[] {"DataType=Dropdown", "LOV DDF Mapping" });
                return;
            } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals(svcTermDataTpCd) && !ZYPCommonFunc.hasValue(svcTermCondDataSrcCd)) {
                cMsg.A.no(m).svcTermCondSrcDescTxt_A.setErrorInfo(1, NSAM0081E, new String[] {"DataType=Lookup", "LOV DDF Mapping" });
                return;
            // add start 2018/06/25 QC#17369
            } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(svcTermDataTpCd) && !ZYPCommonFunc.hasValue(svcTermCondDataSrcCd)) {
                cMsg.A.no(m).svcTermCondSrcDescTxt_A.setErrorInfo(1, NSAM0081E, new String[] {"DataType=Lookup(Popup)", "LOV DDF Mapping" });
                return;
            }
            // add end 2018/06/25 QC#17369
            if (ZYPCommonFunc.hasValue(cMsg.A.no(m).effToDt_A)) {
                int start = Integer.parseInt(cMsg.A.no(m).effFromDt_A.getValue());
                int end = Integer.parseInt(cMsg.A.no(m).effToDt_A.getValue());
                if (start > end) {
                    cMsg.A.no(m).effToDt_A.setErrorInfo(1, NSAM0457E, new String[] {"End Date", "Start Date" });
                    return;
                }
            } else {
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(m).xxChkBox_AA)) {
                    cMsg.A.no(m).effToDt_A.setErrorInfo(1, NSAM0081E, new String[] {"Active Checkbox OFF", "End Date" });
                    return;
                }
            }
        }
        // insert/update
        for (int l = 0; l < cMsg.A.getValidCount(); l++) {
            SVC_TERM_COND_ATTRBTMsg tMsg = saveSvcTermCondAttrb(cMsg, l);
            if (ZYPCommonFunc.hasValue(cMsg.A.no(l).svcTermCondAttrbPk_A)) {

                SVC_TERM_COND_ATTRBTMsg stcaTMsg = new SVC_TERM_COND_ATTRBTMsg();
                ZYPEZDItemValueSetter.setValue(stcaTMsg.glblCmpyCd, cMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(stcaTMsg.svcTermCondAttrbPk, cMsg.A.no(l).svcTermCondAttrbPk_A);
                stcaTMsg = (SVC_TERM_COND_ATTRBTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(stcaTMsg);
                if (stcaTMsg == null || !RTNCD_NORMAL.equals(stcaTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NSAM0045E, new String[] {"SVC_TERM_COND_ATTRB" });
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(cMsg.A.no(l).ezUpTime.getValue(), cMsg.A.no(l).ezUpTimeZone.getValue(), stcaTMsg.ezUpTime.getValue(), stcaTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                ZYPEZDItemValueSetter.setValue(tMsg.svcTermCondAttrbPk, cMsg.A.no(l).svcTermCondAttrbPk_A.getValue());
                S21FastTBLAccessor.update(tMsg);
                if (!RETURN_CD_NORMAL.equals(tMsg.getReturnCode())) {
                    cMsg.setMessageInfo(ZZMM0015E, new String[] {"SVC_TERM_COND_ATTRB", "SVC_TERM_ATTRB_DESC_TXT", tMsg.svcTermAttrbDescTxt.getValue() });
                    return;
                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.svcTermCondAttrbPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TERM_COND_ATTRB_SQ));
                S21FastTBLAccessor.insert(tMsg);
                if (!RETURN_CD_NORMAL.equals(tMsg.getReturnCode())) {
                    cMsg.setMessageInfo(ZZMM0001E, new String[] {"SVC_TERM_COND_ATTRB", "SVC_TERM_ATTRB_DESC_TXT", tMsg.svcTermAttrbDescTxt.getValue() });
                    return;
                }
            }
        }
        cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
    }

    private SVC_TERM_COND_ATTRBTMsg saveSvcTermCondAttrb(NSAL1050CMsg cMsg, int l) {

        SVC_TERM_COND_ATTRBTMsg stcaTMsg = new SVC_TERM_COND_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(stcaTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(stcaTMsg.svcTermCondCatgPk, cMsg.A.no(l).svcTermCondCatgPk_1V);
        ZYPEZDItemValueSetter.setValue(stcaTMsg.svcTermCondAttrbNm, cMsg.A.no(l).svcTermCondAttrbNm_A);
        ZYPEZDItemValueSetter.setValue(stcaTMsg.svcTermAttrbSortNum, cMsg.A.no(l).svcTermAttrbSortNum_A);
        ZYPEZDItemValueSetter.setValue(stcaTMsg.svcTermAttrbDescTxt, cMsg.A.no(l).svcTermAttrbDescTxt_A);
        if (ZYPCommonFunc.hasValue(cMsg.A.no(l).xxChkBox_AA)) {
            ZYPEZDItemValueSetter.setValue(stcaTMsg.svcTermAttrbActvFlg, cMsg.A.no(l).xxChkBox_AA);
        } else {
            ZYPEZDItemValueSetter.setValue(stcaTMsg.svcTermAttrbActvFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(stcaTMsg.effFromDt, cMsg.A.no(l).effFromDt_A);
        ZYPEZDItemValueSetter.setValue(stcaTMsg.effThruDt, cMsg.A.no(l).effToDt_A);
        ZYPEZDItemValueSetter.setValue(stcaTMsg.svcTermDataTpCd, cMsg.A.no(l).svcTermDataTpCd_1V);
        if (ZYPCommonFunc.hasValue(cMsg.A.no(l).xxChkBox_AC)) {
            ZYPEZDItemValueSetter.setValue(stcaTMsg.svcTermAttrbContrFlg, cMsg.A.no(l).xxChkBox_AC);
        } else {
            ZYPEZDItemValueSetter.setValue(stcaTMsg.svcTermAttrbContrFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cMsg.A.no(l).xxChkBox_AC)) {
            ZYPEZDItemValueSetter.setValue(stcaTMsg.svcTermAttrbMachFlg, cMsg.A.no(l).xxChkBox_AM);
        } else {
            ZYPEZDItemValueSetter.setValue(stcaTMsg.svcTermAttrbMachFlg, ZYPConstant.FLG_OFF_N);
        }
        // START 2016/06/02 T.Tomita [QC#5489, MOD]
        String svcTermDataTpCd = cMsg.A.no(l).svcTermDataTpCd_1V.getValue();
        if (SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals(svcTermDataTpCd)) {
            ZYPEZDItemValueSetter.setValue(stcaTMsg.svcTermCondDataSrcCd, cMsg.A.no(l).svcTermCondDataSrcCd_A);
        } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals(svcTermDataTpCd)) {
            ZYPEZDItemValueSetter.setValue(stcaTMsg.physMaintTrgtTblNm, cMsg.A.no(l).svcTermCondDataSrcCd_A);
        // add start 2018/06/25 QC#17369
        } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(svcTermDataTpCd)) {
            ZYPEZDItemValueSetter.setValue(stcaTMsg.physMaintTrgtTblNm, cMsg.A.no(l).svcTermCondDataSrcCd_A);
        // add end 2018/06/25 QC#17369
        }
        // END 2016/06/02 T.Tomita [QC#5489, MOD]

        return stcaTMsg;
    }
}
