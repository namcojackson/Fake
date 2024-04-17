/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3150;

import static business.blap.NLBL3150.constant.NLBL3150Constant.ASTERISK;
import static business.blap.NLBL3150.constant.NLBL3150Constant.INVTY_HARD_ALLOC_TP_CD_20;
import static business.blap.NLBL3150.constant.NLBL3150Constant.NLAM0240E;
import static business.blap.NLBL3150.constant.NLBL3150Constant.NLBM0006I;
import static business.blap.NLBL3150.constant.NLBL3150Constant.NLBM1298W;
import static business.blap.NLBL3150.constant.NLBL3150Constant.SHIP_ORD_HLD_FLAG_N;
import static business.blap.NLBL3150.constant.NLBL3150Constant.ZZZM9004E;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3150.common.NLBL3150CommonLogic;
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
 * NLBL3150BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/21   Fujitsu         K.Ishizuka      Create          N/A
 * 2017/08/17   Fujitsu         M.Yamada        Update          QC#20556
 * 2017/08/25   Fujitsu         M.Ohno          Update          QC#20772
 * 2017/09/08   CITS            T.Hakodate      Update          QC#20772
 *</pre>
 */
public class NLBL3150BL06 extends S21BusinessHandler {
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NLBL3150SMsg glblMsg = (NLBL3150SMsg) sMsg;
            NLBL3150CMsg bizMsg = (NLBL3150CMsg) cMsg;

            if ("NLBL3150Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLBL3150Scrn00_Submit(bizMsg, glblMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NLBL3150Scrn00_Submit(NLBL3150CMsg bizMsg, NLBL3150SMsg sMsg) {

        try {
            NLBL3150CommonLogic.updateSMsg(bizMsg, sMsg);

            // 2017/09/08 CITS T.Hakodate Mod QC#20772 START
            // if (bizMsg.A.getValidCount() == 0 &&
            // sMsg.B.getValidCount() == 0) {
            // bizMsg.setMessageInfo(NLZM2494W);
            // return;
            // }

            // 2017/09/08 CITS T.Hakodate Mod QC#20772 END
            
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
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseWhCondPk_A) || NLBL3150CommonLogic.checkDifference(sMsg.A.no(i), sMsg.C.no(i))) {
                    if (!NLBL3150CommonLogic.checkItem(getGlobalCompanyCode(), sMsg.A.no(i))) {
                        errorFlg = false;
                    }
                    if (!NLBL3150CommonLogic.checkWhSwhCd(getGlobalCompanyCode(), sMsg.A.no(i))) {
                        errorFlg = false;
                    }
                    ZYPEZDItemValueSetter.setValue(sMsg.D.no(num).mdseCd_D, sMsg.A.no(i).mdseCd_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.D.no(num).rtlWhCd_D, sMsg.A.no(i).rtlWhCd_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.D.no(num).rtlSwhCd_D, sMsg.A.no(i).rtlSwhCd_A);
                    sMsg.D.setValidCount(num + 1);
                    num++;
                }
            }

            if (!errorFlg) {
                return;
            }

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseWhCondPk_A)) {
                    // New Input
                    // 2017/08/14 QC#20556 MOD BEGIN
//                    if (NLBL3150CommonLogic.checkRegisteredRule(sMsg.A.no(i))) {
//                        bizMsg.setMessageInfo(NLBM1298W, new String[] {"Input" });
//                        return;
//                    }
//                    if (NLBL3150CommonLogic.checkDuplicate(sMsg, i)) {
//                        bizMsg.setMessageInfo(NLAM0240E, new String[] {"Input" });
//                        return;
//                    }
                    if (NLBL3150CommonLogic.checkDuplicate(sMsg, i)) {
                        bizMsg.setMessageInfo(NLAM0240E, new String[] {"Input" });
                        return;
                    }
                    if (NLBL3150CommonLogic.checkRegisteredRule(sMsg.A.no(i))) {
                        bizMsg.setMessageInfo(NLBM1298W, new String[] {"Input" });
                        return;
                    }
                    // 2017/08/14 QC#20556 MOD END

                    MDSE_WH_CONDTMsg insTMsg = new MDSE_WH_CONDTMsg();
                    ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(insTMsg.mdseWhCondPk, ZYPOracleSeqAccessor.getNumberBigDecimal("MDSE_WH_COND_SQ"));
                    ZYPEZDItemValueSetter.setValue(insTMsg.mdseCd, sMsg.A.no(i).mdseCd_A);
                    ZYPEZDItemValueSetter.setValue(insTMsg.invtyHardAllocTpCd, INVTY_HARD_ALLOC_TP_CD_20);
                    ZYPEZDItemValueSetter.setValue(insTMsg.shpgOrdHldFlg, SHIP_ORD_HLD_FLAG_N);
                    ZYPEZDItemValueSetter.setValue(insTMsg.rtlWhCd, sMsg.A.no(i).rtlWhCd_A);
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhCd_A)) {
                        ZYPEZDItemValueSetter.setValue(insTMsg.rtlSwhCd, sMsg.A.no(i).rtlSwhCd_A);
                    } else {
                        ZYPEZDItemValueSetter.setValue(insTMsg.rtlSwhCd, ASTERISK);
                    }
                    ZYPEZDItemValueSetter.setValue(insTMsg.mdseWhCondTpCd, MDSE_WH_COND_TP.MANUAL);
                    S21FastTBLAccessor.insert(insTMsg);
                } else {
                    // Update record
                    if (NLBL3150CommonLogic.checkDifference(sMsg.A.no(i), sMsg.C.no(i))) {
                        // 2017/08/14 QC#20556 MOD BEGIN
//                      if (NLBL3150CommonLogic.checkRegisteredRule(sMsg.A.no(i))) {
//                          bizMsg.setMessageInfo(NLBM1298W, new String[] {"Input" });
//                          return;
//                      }
//                      if (NLBL3150CommonLogic.checkDuplicate(sMsg, i)) {
//                          bizMsg.setMessageInfo(NLAM0240E, new String[] {"Input" });
//                          return;
//                      }
                        if (NLBL3150CommonLogic.checkDuplicate(sMsg, i)) {
                            bizMsg.setMessageInfo(NLAM0240E, new String[] {"Input" });
                            return;
                        }
                        // 2017/08/17 QC#20556 DEL BEGIN
//                        String mdseCd = sMsg.A.no(i).mdseCd_A.getValue();
//                        if (mdseCd.length() == 10 && mdseCd.substring(0, 8).equals(sMsg.C.no(i).mdseCd_C.getValue())) {
//                            sMsg.A.no(i).rtlWhCd_A.setErrorInfo(1, NLAM0240E, new String[] {"Input" });
//                            sMsg.A.no(i).rtlSwhCd_A.setErrorInfo(1, NLAM0240E, new String[] {"Input" });
//                            sMsg.A.no(i).mdseCd_A.setErrorInfo(1, NLAM0240E, new String[] {"Input" });
//                            bizMsg.setMessageInfo(NLAM0240E, new String[] {"Input" });
//                        }
                        // 2017/08/17 QC#20556 DEL END
                        if (NLBL3150CommonLogic.checkRegisteredRule(sMsg.A.no(i))) {
                            bizMsg.setMessageInfo(NLBM1298W, new String[] {"Input" });
                            return;
                        }
                      // 2017/08/14 QC#20556 MOD END

                        MDSE_WH_CONDTMsg updTMsg = new MDSE_WH_CONDTMsg();
                        ZYPEZDItemValueSetter.setValue(updTMsg.glblCmpyCd, getGlobalCompanyCode());
                        ZYPEZDItemValueSetter.setValue(updTMsg.mdseWhCondPk, sMsg.A.no(i).mdseWhCondPk_A);
                        updTMsg = (MDSE_WH_CONDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(updTMsg);
                        if (updTMsg == null) {
                            continue;
                        }
                        if (ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime_A.getValue(), sMsg.A.no(i).ezUpTimeZone_A.getValue(), updTMsg.ezUpTime.getValue(), updTMsg.ezUpTimeZone.getValue())) {
                            ZYPEZDItemValueSetter.setValue(updTMsg.mdseCd, sMsg.A.no(i).mdseCd_A);
                            ZYPEZDItemValueSetter.setValue(updTMsg.rtlWhCd, sMsg.A.no(i).rtlWhCd_A);
                            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhCd_A)) {
                                ZYPEZDItemValueSetter.setValue(updTMsg.rtlSwhCd, sMsg.A.no(i).rtlSwhCd_A);
                            } else {
                                ZYPEZDItemValueSetter.setValue(updTMsg.rtlSwhCd, ASTERISK);
                            }
                            S21FastTBLAccessor.update(updTMsg);
                        } else {
                            bizMsg.setMessageInfo(ZZZM9004E);
                            return;
                        }
                    }
                }
            }
            // 2017/08/11 QC#20556 ADD BEGIN
            bizMsg.setMessageInfo(NLBM0006I);
            // 2017/08/11 QC#20556 ADD END
        } finally {
            NLBL3150CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, sMsg, sMsg.A);
        }
    }
}
