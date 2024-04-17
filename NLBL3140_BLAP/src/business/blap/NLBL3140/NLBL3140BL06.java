/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3140;

import static business.blap.NLBL3140.constant.NLBL3140Constant.ASTERISK;
import static business.blap.NLBL3140.constant.NLBL3140Constant.INVTY_HARD_ALLOC_TP_CD_20;
import static business.blap.NLBL3140.constant.NLBL3140Constant.NLBM1298W;
import static business.blap.NLBL3140.constant.NLBL3140Constant.NLBM1304W;
import static business.blap.NLBL3140.constant.NLBL3140Constant.NLZM2494W;
import static business.blap.NLBL3140.constant.NLBL3140Constant.SHIP_ORD_HLD_FLAG_N;
import static business.blap.NLBL3140.constant.NLBL3140Constant.ZZZM9004E;
import static business.blap.NLBL3140.constant.NLBL3140Constant.NLBM0006I;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3140.common.NLBL3140CommonLogic;
import business.db.MDSE_WH_CONDTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_WH_COND_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NLBL3140BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/21   Fujitsu         K.Ishizuka      Create          N/A
 * 2017/10/06   CITS            T.Kikuhara      Update          QC#20772
 * 2023/07/11   Hitachi         G.Quan          Update          QC#61543
 *</pre>
 */
public class NLBL3140BL06 extends S21BusinessHandler {
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NLBL3140SMsg glblMsg = (NLBL3140SMsg) sMsg;
            NLBL3140CMsg bizMsg = (NLBL3140CMsg) cMsg;

            if ("NLBL3140Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLBL3140Scrn00_Submit(bizMsg, glblMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NLBL3140Scrn00_Submit(NLBL3140CMsg bizMsg, NLBL3140SMsg sMsg) {

        // 2017/08/14 QC#20555 MOD BEGIN
        try {
            NLBL3140CommonLogic.updateSMsg(bizMsg, sMsg);

            // QC#20772 DEL START
            //if (bizMsg.A.getValidCount() == 0 && sMsg.B.getValidCount() == 0) {
            //    bizMsg.setMessageInfo(NLZM2494W);
            //    return;
            //}
            // QC#20772 DEL END

            if (sMsg.B.getValidCount() > 0) {
                for (int index = 0; index < sMsg.B.getValidCount(); index++) {
                    MDSE_WH_CONDTMsg mdseWhCondTmsg = new MDSE_WH_CONDTMsg();
                    ZYPEZDItemValueSetter.setValue(mdseWhCondTmsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(mdseWhCondTmsg.mdseWhCondPk, sMsg.B.no(index).mdseWhCondPk_B);
                    MDSE_WH_CONDTMsg resTMsg = (MDSE_WH_CONDTMsg) EZDTBLAccessor.findByKey(mdseWhCondTmsg);
                    if (resTMsg == null) {
                        continue;
                    }
                    if (ZYPDateUtil.isSameTimeStamp(sMsg.B.no(index).ezUpTime_B.getValue(), sMsg.B.no(index).ezUpTimeZone_B.getValue(), resTMsg.ezUpTime.getValue(), resTMsg.ezUpTimeZone.getValue())) {
                        EZDTBLAccessor.logicalRemove(resTMsg);
                    } else {
                        bizMsg.setMessageInfo(ZZZM9004E);
                        return;
                    }
                }
            }

            boolean errorFlg = true;
            int num = 0;
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                // 2017/08/14 QC#20555 ADD BEGIN
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).dsOrdCatgDescTxt_A)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsOrdCatgDescTxt_A, ASTERISK);
                }
                // 2017/08/14 QC#20555 ADD END
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseWhCondPk_A) || NLBL3140CommonLogic.checkDifference(sMsg.A.no(i), sMsg.C.no(i))) {
                    if (!NLBL3140CommonLogic.checkOrdCatg(sMsg.A.no(i))) {
                        errorFlg = false;
                    }
                    if (!NLBL3140CommonLogic.checkWhSwhCd(getGlobalCompanyCode(), sMsg.A.no(i))) {
                        errorFlg = false;
                    }
                    ZYPEZDItemValueSetter.setValue(sMsg.D.no(num).lineBizTpCd_D, sMsg.A.no(i).lineBizTpCd_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.D.no(num).rtlWhCd_D, sMsg.A.no(i).rtlWhCd_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.D.no(num).rtlSwhCd_D, sMsg.A.no(i).rtlSwhCd_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.D.no(num).dsOrdCatgCd_D, sMsg.A.no(i).dsOrdCatgCd_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.D.no(num).hardAllocTpCd_D, sMsg.A.no(i).hardAllocTpCd_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.D.no(num).tmFenceDaysAot_D, sMsg.A.no(i).tmFenceDaysAot_A);
                    // START 2023/07/11 G.Quan [QC#61543, ADD]
                    ZYPEZDItemValueSetter.setValue(sMsg.D.no(num).coaProdCd_D, sMsg.A.no(i).coaProdCd_A);
                    // START 2023/07/11 G.Quan [QC#61543, ADD]
                    sMsg.D.setValidCount(num + 1);
                    num++;
                }
            }

            if (!errorFlg) {
                // 2017/08/14 QC#20555 DEL BEGIN
//                NLBL3140CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, sMsg, sMsg.A);
                // 2017/08/14 QC#20555 DEL END
                return;
            }

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                // START 2023/07/10 G.Quan [QC#61543, ADD]
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).coaProdCd_A.getValue()) && !NLBL3140CommonLogic.checkProductCode(sMsg.A.no(i))) {
                    return; 
                }
                // END 2023/07/10 G.Quan [QC#61543, ADD]
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseWhCondPk_A)) {

                    if (NLBL3140CommonLogic.checkDuplicate(sMsg, i)) {
                        bizMsg.setMessageInfo(NLBM1304W, new String[] {"Input" });
                        return;
                    }
                    if (NLBL3140CommonLogic.checkRegisteredRule(sMsg.A.no(i))) {
                        bizMsg.setMessageInfo(NLBM1298W, new String[] {"Input" });
                        return;
                    }

                    MDSE_WH_CONDTMsg insTMsg = new MDSE_WH_CONDTMsg();
                    ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(insTMsg.mdseWhCondPk, ZYPOracleSeqAccessor.getNumberBigDecimal("MDSE_WH_COND_SQ"));
                    ZYPEZDItemValueSetter.setValue(insTMsg.mdseCd, ASTERISK);
                    ZYPEZDItemValueSetter.setValue(insTMsg.invtyHardAllocTpCd, sMsg.A.no(i).hardAllocTpCd_A);
                    ZYPEZDItemValueSetter.setValue(insTMsg.shpgOrdHldFlg, SHIP_ORD_HLD_FLAG_N);
                    ZYPEZDItemValueSetter.setValue(insTMsg.rtlWhCd, sMsg.A.no(i).rtlWhCd_A);
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhCd_A)) {
                        ZYPEZDItemValueSetter.setValue(insTMsg.rtlSwhCd, sMsg.A.no(i).rtlSwhCd_A);
                    } else {
                        ZYPEZDItemValueSetter.setValue(insTMsg.rtlSwhCd, ASTERISK);
                    }
                    if (INVTY_HARD_ALLOC_TP_CD_20.equals(sMsg.A.no(i).hardAllocTpCd_A.getValue())) {
                        sMsg.A.no(i).tmFenceDaysAot_A.clear();
                    }
                    ZYPEZDItemValueSetter.setValue(insTMsg.dsOrdCatgCd, sMsg.A.no(i).dsOrdCatgCd_A);
                    ZYPEZDItemValueSetter.setValue(insTMsg.lineBizTpCd, sMsg.A.no(i).lineBizTpCd_A);
                    ZYPEZDItemValueSetter.setValue(insTMsg.mdseWhCondTpCd, MDSE_WH_COND_TP.AUTO);
                    ZYPEZDItemValueSetter.setValue(insTMsg.tmFenceDaysAot, sMsg.A.no(i).tmFenceDaysAot_A);
                    // START 2023/07/21 G.Quan [QC#61543, ADD]
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(i).coaProdCd_A)) {
                        ZYPEZDItemValueSetter.setValue(insTMsg.coaProdCd, sMsg.A.no(i).coaProdCd_A);
                    } else {
                        ZYPEZDItemValueSetter.setValue(insTMsg.coaProdCd, ASTERISK);
                    }
                    // END 2023/07/21 G.Quan [QC#61543, ADD]
                    S21FastTBLAccessor.insert(insTMsg);
                } else {
                    if (NLBL3140CommonLogic.checkDifference(sMsg.A.no(i), sMsg.C.no(i))) {
                        if (NLBL3140CommonLogic.checkDuplicate(sMsg, i)) {
                            bizMsg.setMessageInfo(NLBM1304W, new String[] {"Input" });
                            return;
                        }
                        if (NLBL3140CommonLogic.checkRegisteredRule(sMsg.A.no(i)) && NLBL3140CommonLogic.checkCombination(sMsg.A.no(i), sMsg.C.no(i))) {
                            bizMsg.setMessageInfo(NLBM1298W, new String[] {"Input" });
                            return;
                        }

                        MDSE_WH_CONDTMsg updTMsg = new MDSE_WH_CONDTMsg();
                        ZYPEZDItemValueSetter.setValue(updTMsg.glblCmpyCd, getGlobalCompanyCode());
                        ZYPEZDItemValueSetter.setValue(updTMsg.mdseWhCondPk, sMsg.A.no(i).mdseWhCondPk_A);
                        updTMsg = (MDSE_WH_CONDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(updTMsg);
                        if (updTMsg == null) {
                            continue;
                        }
                        if (ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime_A.getValue(), sMsg.A.no(i).ezUpTimeZone_A.getValue(), updTMsg.ezUpTime.getValue(), updTMsg.ezUpTimeZone.getValue())) {
                            ZYPEZDItemValueSetter.setValue(updTMsg.invtyHardAllocTpCd, sMsg.A.no(i).hardAllocTpCd_A);
                            ZYPEZDItemValueSetter.setValue(updTMsg.lineBizTpCd, sMsg.A.no(i).lineBizTpCd_A);
                            ZYPEZDItemValueSetter.setValue(updTMsg.rtlWhCd, sMsg.A.no(i).rtlWhCd_A);
                            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhCd_A)) {
                                ZYPEZDItemValueSetter.setValue(updTMsg.rtlSwhCd, sMsg.A.no(i).rtlSwhCd_A);
                            } else {
                                ZYPEZDItemValueSetter.setValue(updTMsg.rtlSwhCd, ASTERISK);
                            }
                            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).dsOrdCatgCd_A)) {
                                ZYPEZDItemValueSetter.setValue(updTMsg.dsOrdCatgCd, sMsg.A.no(i).dsOrdCatgCd_A);
                            } else {
                                ZYPEZDItemValueSetter.setValue(updTMsg.dsOrdCatgCd, ASTERISK);
                            }
                            if (INVTY_HARD_ALLOC_TP_CD_20.equals(sMsg.A.no(i).hardAllocTpCd_A.getValue())) {
                                sMsg.A.no(i).tmFenceDaysAot_A.clear();
                            }
                            ZYPEZDItemValueSetter.setValue(updTMsg.tmFenceDaysAot, sMsg.A.no(i).tmFenceDaysAot_A);
                            // START 2023/07/21 G.Quan [QC#61543, ADD]
                            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).coaProdCd_A)) {
                                ZYPEZDItemValueSetter.setValue(updTMsg.coaProdCd, sMsg.A.no(i).coaProdCd_A);
                            } else {
                                ZYPEZDItemValueSetter.setValue(updTMsg.coaProdCd, ASTERISK);
                            }
                            // END 2023/07/21 G.Quan [QC#61543, ADD]
                            S21FastTBLAccessor.update(updTMsg);
                        } else {
                            bizMsg.setMessageInfo(ZZZM9004E);
                            return;
                        }
                    }
                }
            }

            // 2017/08/14 QC#20555 ADD BEGIN
            bizMsg.setMessageInfo(NLBM0006I);
            // 2017/08/14 QC#20555 ADD END
        } finally {
            NLBL3140CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, sMsg, sMsg.A);
        }
        // 2017/08/14 QC#20555 MOD END
    }
}
