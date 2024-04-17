/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0440;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSAL0440.common.NSAL0440CommonLogic;
import business.db.SVC_TERM_CONDTMsg;
import static business.blap.NSAL0440.constant.NSAL0440Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TERM_ATTRB_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Terms & Conditions
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Hitachi         T.Iwamoto       Create          N/A
 * 2016/03/28   Hitachi         T.Tomita        Update          QC#3018
 * 2016/06/03   Hitachi         T.Tomita        Update          QC#5489
 * 2016/08/29   Hitachi         T.Tomita        Update          QC#13899
 * 2018/06/25   Hitachi         A.Kohinata      Update          QC#17369
 * 2018/07/31   Hitachi         A.Kohinata      Update          QC#26659
 *</pre>
 */
public class NSAL0440BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0440Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0440Scrn00_CMN_Submit((NSAL0440CMsg) cMsg, (NSAL0440SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Submit)
     * @param cMsg NSAL0440CMsg
     * @param sMsg NSAL0440SMsg
     */
    private void doProcess_NSAL0440Scrn00_CMN_Submit(NSAL0440CMsg cMsg, NSAL0440SMsg sMsg) {

        NSAL0440CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        updateTermCondition(cMsg, sMsg);

        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NSAM0200I);
            return;
        }
        // BMsg->ASMsg
        NSAL0440CommonLogic.setViewData_A(sMsg);
    }

    /**
     * updateTermCondition
     * @param sMsg NSAL0440SMsg
     * @return Change Line : true
     */
    private void updateTermCondition(NSAL0440CMsg cMsg, NSAL0440SMsg sMsg) {
        String contrTerm = null;
        String lineTerm = null;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL0440_BSMsg bsMsg = (NSAL0440_BSMsg) sMsg.B.no(i);
            // mod start 2016/03/28 CSA Defect#3018
            if (NSAL0440CommonLogic.isProtectSts(bsMsg)) {
                continue;
            }
            // mod end 2016/03/28 CSA Defect#3018

            if (hasValue(bsMsg.svcTermCondPk)) {
                SVC_TERM_CONDTMsg tMsg = getTermCond(cMsg.glblCmpyCd.getValue(), bsMsg.svcTermCondPk.getValue());
                if (tMsg == null) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(bsMsg.ezUpTime.getValue(), bsMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        cMsg.setMessageInfo(ZZZM9004E);
                        return;
                    }
                    if (ZYPConstant.FLG_ON_Y.equals(bsMsg.xxDplyCtrlFlg_A.getValue())) {
                        if (SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals(bsMsg.svcTermDataTpCd_A.getValue())) {
                            lineTerm = (String) bsMsg.svcTermCondDataValCd_PS.getValue();
                        } else if (SVC_TERM_ATTRB_DATA_TP.TEXTBOX.equals(bsMsg.svcTermDataTpCd_A.getValue())) {
                            lineTerm = (String) bsMsg.svcTermAttrbMapValCd_A.getValue();
                        } else if (SVC_TERM_ATTRB_DATA_TP.DATE.equals(bsMsg.svcTermDataTpCd_A.getValue())) {
                            lineTerm = (String) bsMsg.xxTrxDt_A.getValue();
                        } else if (SVC_TERM_ATTRB_DATA_TP.NUMBER.equals(bsMsg.svcTermDataTpCd_A.getValue()) && hasValue(bsMsg.condValNum_A)) {
                            lineTerm = bsMsg.condValNum_A.getValue().toString();
                        // START 2016/06/03 T.Tomita [QC#5489, ADD]
                        } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals(bsMsg.svcTermDataTpCd_A.getValue())) {
                            lineTerm = (String) bsMsg.svcTermCondDataValCd_PS.getValue();
                        // END 2016/06/03 T.Tomita [QC#5489, ADD]
                        // add start 2018/06/25 QC#17369
                        } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(bsMsg.svcTermDataTpCd_A.getValue())) {
                            lineTerm = (String) bsMsg.svcTermCondDataValCd_PS.getValue();
                        // add end 2018/06/25 QC#17369
                        // add start 2018/07/31 QC#26659
                        } else if (SVC_TERM_ATTRB_DATA_TP.TEXT_NUMERIC.equals(bsMsg.svcTermDataTpCd_A.getValue())) {
                            lineTerm = (String) bsMsg.svcTermAttrbMapValCd_A.getValue();
                        // add end 2018/07/31 QC#26659
                        }
                        contrTerm = lineTerm;
                    } else {
                        if (SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals(bsMsg.svcTermDataTpCd_M.getValue())) {
                            lineTerm = (String) bsMsg.svcTermCondDataValCd_MS.getValue();
                        } else if (SVC_TERM_ATTRB_DATA_TP.TEXTBOX.equals(bsMsg.svcTermDataTpCd_M.getValue())) {
                            lineTerm = (String) bsMsg.svcTermAttrbMapValCd_M.getValue();
                        } else if (SVC_TERM_ATTRB_DATA_TP.DATE.equals(bsMsg.svcTermDataTpCd_M.getValue())) {
                            lineTerm = (String) bsMsg.xxTrxDt_M.getValue();
                        } else if (SVC_TERM_ATTRB_DATA_TP.NUMBER.equals(bsMsg.svcTermDataTpCd_M.getValue()) && hasValue(bsMsg.condValNum_M)) {
                            lineTerm = bsMsg.condValNum_M.getValue().toString();
                        // START 2016/06/03 T.Tomita [QC#5489, ADD]
                        } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals(bsMsg.svcTermDataTpCd_M.getValue())) {
                            lineTerm = (String) bsMsg.svcTermCondDataValCd_MS.getValue();
                        // END 2016/06/03 T.Tomita [QC#5489, ADD]
                        // add start 2018/06/25 QC#17369
                        } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(bsMsg.svcTermDataTpCd_M.getValue())) {
                            lineTerm = (String) bsMsg.svcTermCondDataValCd_MS.getValue();
                        // add end 2018/06/25 QC#17369
                        // add start 2018/07/31 QC#26659
                        } else if (SVC_TERM_ATTRB_DATA_TP.TEXT_NUMERIC.equals(bsMsg.svcTermDataTpCd_M.getValue())) {
                            lineTerm = (String) bsMsg.svcTermAttrbMapValCd_M.getValue();
                        // add end 2018/07/31 QC#26659
                        }
                    }

                    setValue(tMsg.svcTermAttrbMapValCd, lineTerm);
                    lineTerm = null;

                    S21FastTBLAccessor.update(tMsg);
                    String rtnCd = tMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0031E, new String[] {SVC_TERM_COND });
                        return;
                    }
                }
            } else {
                // insert
                if (!checkOtherInsert(cMsg, bsMsg)) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }

                SVC_TERM_CONDTMsg tMsg = new SVC_TERM_CONDTMsg();
                setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
                setValue(tMsg.svcTermCondPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TERM_COND_SQ));
                setValue(tMsg.dsContrPk, cMsg.dsContrPk);
                setValue(tMsg.dsContrDtlPk, bsMsg.dsContrDtlPk_A);
                setValue(tMsg.svcTermCondAttrbPk, bsMsg.svcTermCondAttrbPk_A);

                if (ZYPConstant.FLG_ON_Y.equals(bsMsg.xxDplyCtrlFlg_A.getValue())) {
                    if (SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals(bsMsg.svcTermDataTpCd_A.getValue())) {
                        lineTerm = (String) bsMsg.svcTermCondDataValCd_PS.getValue();
                    } else if (SVC_TERM_ATTRB_DATA_TP.TEXTBOX.equals(bsMsg.svcTermDataTpCd_A.getValue())) {
                        lineTerm = (String) bsMsg.svcTermAttrbMapValCd_A.getValue();
                    } else if (SVC_TERM_ATTRB_DATA_TP.DATE.equals(bsMsg.svcTermDataTpCd_A.getValue())) {
                        lineTerm = (String) bsMsg.xxTrxDt_A.getValue();
                    } else if (SVC_TERM_ATTRB_DATA_TP.NUMBER.equals(bsMsg.svcTermDataTpCd_A.getValue())) {
                        if (hasValue(bsMsg.condValNum_A)) {
                            lineTerm = bsMsg.condValNum_A.getValue().toString();
                        }
                    // START 2016/08/29 T.Tomita [QC#13899, ADD]
                    } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals(bsMsg.svcTermDataTpCd_A.getValue())) {
                        lineTerm = (String) bsMsg.svcTermCondDataValCd_PS.getValue();
                    // END 2016/08/29 T.Tomita [QC#13899, ADD]
                    // add start 2018/06/25 QC#17369
                    } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(bsMsg.svcTermDataTpCd_A.getValue())) {
                        lineTerm = (String) bsMsg.svcTermCondDataValCd_PS.getValue();
                    // add end 2018/06/25 QC#17369
                    // add start 2018/07/31 QC#26659
                    } else if (SVC_TERM_ATTRB_DATA_TP.TEXT_NUMERIC.equals(bsMsg.svcTermDataTpCd_A.getValue())) {
                        lineTerm = (String) bsMsg.svcTermAttrbMapValCd_A.getValue();
                    // add end 2018/07/31 QC#26659
                    }
                    contrTerm = lineTerm;
                } else {
                    if (SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals(bsMsg.svcTermDataTpCd_M.getValue())) {
                        if (hasValue(bsMsg.svcTermCondDataValCd_MS)) {
                            lineTerm = (String) bsMsg.svcTermCondDataValCd_MS.getValue();
                        } else {
                            lineTerm = contrTerm;
                        }
                    } else if (SVC_TERM_ATTRB_DATA_TP.TEXTBOX.equals(bsMsg.svcTermDataTpCd_M.getValue())) {
                        if (hasValue(bsMsg.svcTermAttrbMapValCd_M)) {
                            lineTerm = (String) bsMsg.svcTermAttrbMapValCd_M.getValue();
                        } else {
                            lineTerm = contrTerm;
                        }
                    } else if (SVC_TERM_ATTRB_DATA_TP.DATE.equals(bsMsg.svcTermDataTpCd_M.getValue())) {
                        if (hasValue(bsMsg.xxTrxDt_M)) {
                            lineTerm = (String) bsMsg.xxTrxDt_M.getValue();
                        } else {
                            lineTerm = contrTerm;
                        }
                    } else if (SVC_TERM_ATTRB_DATA_TP.NUMBER.equals(bsMsg.svcTermDataTpCd_M.getValue())) {
                        if (hasValue(bsMsg.condValNum_M)) {
                            lineTerm = bsMsg.condValNum_M.getValue().toString();
                        } else {
                            lineTerm = contrTerm;
                        }
                    // START 2016/08/29 T.Tomita [QC#13899, ADD]
                    } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals(bsMsg.svcTermDataTpCd_M.getValue())) {
                        if (hasValue(bsMsg.svcTermCondDataValCd_MS)) {
                            lineTerm = (String) bsMsg.svcTermCondDataValCd_MS.getValue();
                        }
                    // END 2016/08/29 T.Tomita [QC#13899, ADD]
                    // add start 2018/06/25 QC#17369
                    } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(bsMsg.svcTermDataTpCd_M.getValue())) {
                        lineTerm = (String) bsMsg.svcTermCondDataValCd_MS.getValue();
                    // add end 2018/06/25 QC#17369
                    // add start 2018/07/31 QC#26659
                    } else if (SVC_TERM_ATTRB_DATA_TP.TEXT_NUMERIC.equals(bsMsg.svcTermDataTpCd_M.getValue())) {
                        if (hasValue(bsMsg.svcTermAttrbMapValCd_M)) {
                            lineTerm = (String) bsMsg.svcTermAttrbMapValCd_M.getValue();
                        } else {
                            lineTerm = contrTerm;
                        }
                    // add end 2018/07/31 QC#26659
                    }
                }
                setValue(tMsg.svcTermAttrbMapValCd, lineTerm);
                lineTerm = null;

                S21FastTBLAccessor.insert(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    cMsg.setMessageInfo(NSAM0032E, new String[] {SVC_TERM_COND });
                }
            }
        }
        return;
    }

    /**
     * get ServiceTermCondition By Primary Key
     * @param glblCmpyCd String
     * @param svcTermCondPk BigDecimal
     * @return SVC_TERM_CONDTMsg
     */
    private SVC_TERM_CONDTMsg getTermCond(String glblCmpyCd, BigDecimal svcTermCondPk) {
        SVC_TERM_CONDTMsg prmTMsg = new SVC_TERM_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcTermCondPk, svcTermCondPk);
        return (SVC_TERM_CONDTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * checkOtherInsert
     * @param cMsg NSAL0440CMsg
     * @param bsMsg NSAL0440_BSMsg
     * @return true/false
     */
    private boolean checkOtherInsert(NSAL0440CMsg cMsg, NSAL0440_BSMsg bsMsg) {
        SVC_TERM_CONDTMsg inMsg = new SVC_TERM_CONDTMsg();
        if (ZYPCommonFunc.hasValue(bsMsg.dsContrDtlPk_A)) {
            inMsg.setSQLID("001");
            inMsg.setConditionValue("dsContrDtlPk01", (BigDecimal) bsMsg.dsContrDtlPk_A.getValue());
        } else {
            inMsg.setSQLID("002");
        }
        inMsg.setConditionValue("glblCmpyCd01", (String) cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrPk01", (BigDecimal) cMsg.dsContrPk.getValue());
        inMsg.setConditionValue("svcTermCondAttrbPk01", (BigDecimal) bsMsg.svcTermCondAttrbPk_A.getValue());
        int rsCnt = EZDTBLAccessor.count(inMsg);
        if (rsCnt == 0) {
            return true;
        }
        return false;
    }
}
