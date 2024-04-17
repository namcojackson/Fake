/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0410.common;

import static business.blap.NSBL0410.constant.NSBL0410Constant.*;
import static com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil.isSameTimeStamp;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDMsg;
import parts.common.EZDSDateItem;
import parts.common.EZDSStringItem;
import parts.common.EZDTDateItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSBL0410.NSBL0410CMsg;
import business.blap.NSBL0410.NSBL0410SMsg;
import business.blap.NSBL0410.NSBL0410_ACMsg;
import business.blap.NSBL0410.NSBL0410_ACMsgArray;
import business.blap.NSBL0410.NSBL0410_ASMsg;
import business.db.SVC_MODTMsg;
import business.db.SVC_MOD_DTLTMsg;
import business.db.SVC_MOD_DTL_ITEMTMsg;
import business.db.SVC_MOD_DTL_ITEMTMsgArray;
import business.db.SVC_MOD_SER_RNGTMsg;
import business.db.SVC_MOD_SER_RNGTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MNF;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MOD_PRTY;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 *<pre>
 * Mods Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Hitachi         M.Gotou         Create          N/A
 * 2016/02/26   Hitachi         M.Gotou         Update          QC#4709,4719,4722
 * 2016/03/01   Hitachi         M.Gotou         Update          QC#4709
 * 2016/04/11   Hitachi         M.Gotou         Update          QC#4716
 * 2016/04/15   Hitachi         M.Gotou         Update          QC#3425
 * 2016/07/11   Hitachi         O.Okuma         Update          QC#11645,11677,11682,11673
 * 2018/01/17   Hitachi         M.Kidokoro      Update          QC#22447
 * 2018/05/30   Hitachi         U.Kim           Update          QC#22393
 * 2018/07/05   Fujitsu         T.Ogura         Update          QC#27067
 * 2018/07/30   Hitachi         K.Kitachi       Update          QC#27434
 *</pre>
 */
public class NSBL0410CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSBL0410CMsg
     * @param sMsg NSBL0410SMsg
     * @param isClearHeader boolean
     */
    public static void clearMsg(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg, boolean isClearHeader) {
        BigDecimal svcModPk = cMsg.svcModPk.getValue();
        // add start 2016/04/15 CSA Defect#3425
        String mdseCd_F = cMsg.mdseCd_F.getValue();
        // add end 2016/04/15 CSA Defect#3425
        // mod start 2016/07/11 CSA Defect#11682
        if (isClearHeader) {
            cMsg.clear();
        }
        // mod end 2016/07/11 CSA Defect#11682
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        cMsg.svcModPk.setValue(svcModPk);
        // add start 2016/04/15 CSA Defect#3425
        cMsg.mdseCd_F.setValue(mdseCd_F);
        // add end 2016/04/15 CSA Defect#3425
    }

    /**
     * Create Pull Down
     * @param cMsg NSBL0410CMsg
     */
    public static void createPullDown(NSBL0410CMsg cMsg) {
        // mod start 2016/04/11 CSA Defect#4716
        ZYPCodeDataUtil.createPulldownList(SVC_MNF.class, cMsg.svcMnfCd_01, cMsg.xxDplyByCdNmCnctTxt_01, ":");
        //for (int i = 0; i < cMsg.svcMnfCd_01.length(); i++) {
        //    setValue(cMsg.svcMnfDescTxt_01.no(i), cMsg.svcMnfCd_01.no(i));
        //}
        // mod end 2016/04/11 CSA Defect#4716

        ZYPCodeDataUtil.createPulldownList(SVC_MOD_PRTY.class, cMsg.svcModPrtyCd_01, cMsg.svcModPrtyDescTxt_01);

        // START 2018/05/30 U.Kim [QC#22393, ADD]
        ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_TP.class, cMsg.mdseItemTpCd_01, cMsg.mdseItemTpDescTxt_01);
        // END 2018/05/30 U.Kim [QC#22393, ADD]

        ZYPPulldownValueSetter.insertFirstData(ZYPConstant.FLG_OFF_N, cMsg.xxDplyByCtrlItemCd_01, ZYPConstant.FLG_OFF_N, cMsg.xxDplyByCtrlItemCdNm_01);
        ZYPPulldownValueSetter.insertFirstData(ZYPConstant.FLG_ON_Y, cMsg.xxDplyByCtrlItemCd_01, ZYPConstant.FLG_ON_Y, cMsg.xxDplyByCtrlItemCdNm_01);
   }

    /**
     * Set Initialize Parameters
     * @param cMsg NSBL0410CMsg
     * @param sMsg NSBL0410SMsg
     */
    public static void setInitParams(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSBL0410CMsg
     * @param sMsg NSBL0410SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
    }

    /**
     * copy To ASMsg for Current Page Info
     * @param cMsg NSBL0410CMsg
     * @param sMsg NSBL0410SMsg
     */
    public static void copyCurrentPageToSMsg(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        // NSBL0410_ACMsg -> NSBL0410_ASMsg
        NSBL0410_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSBL0410_ACMsg acMsg = (NSBL0410_ACMsg) acMsgArray.no(i);
            int targetIdxNum = cMsg.xxPageShowFromNum.getValueInt() + acMsg.xxRowNum_A.getValue().intValue() - 2;
            NSBL0410_ASMsg asMsg = sMsg.A.no(targetIdxNum);
            setValue(asMsg.xxChkBox_A , acMsg.xxChkBox_A);
            setValue(asMsg.svcMnfCd_A , acMsg.svcMnfCd_A);
            setValue(asMsg.svcMnfModNum_A , acMsg.svcMnfModNum_A);
            setValue(asMsg.svcModDocNum_A , acMsg.svcModDocNum_A);
            setValue(asMsg.xxMdseCdAncr_A , acMsg.xxMdseCdAncr_A);
            setValue(asMsg.xxDplyByCtrlItemCd_A , acMsg.xxDplyByCtrlItemCd_A);
            setValue(asMsg.mdseCd_A , acMsg.mdseCd_A);
            setValue(asMsg.svcModPrtyDescTxt_A , acMsg.svcModPrtyDescTxt_A);
            setValue(asMsg.svcModRptTrkFlg_A , acMsg.svcModRptTrkFlg_A);
            setValue(asMsg.svcModIssDt_A , acMsg.svcModIssDt_A);
            setValue(asMsg.svcModStartDt_A , acMsg.svcModStartDt_A);
            setValue(asMsg.svcModOnHldDt_A , acMsg.svcModOnHldDt_A);
            setValue(asMsg.svcModCancDt_A , acMsg.svcModCancDt_A);
            setValue(asMsg.svcModObslDt_A , acMsg.svcModObslDt_A);
            setValue(asMsg.svcModObslNum_A , acMsg.svcModObslNum_A);
        }
    }

    private static SVC_MODTMsg getsvcMod(String glblCmpyCd, BigDecimal svcModPk) {
        SVC_MODTMsg prmTMsg = new SVC_MODTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.svcModPk, svcModPk);
        return (SVC_MODTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    private static SVC_MOD_DTLTMsg getsvcModDtl(String glblCmpyCd, BigDecimal svcModDtlPk) {
        SVC_MOD_DTLTMsg prmTMsg = new SVC_MOD_DTLTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.svcModDtlPk, svcModDtlPk);
        return (SVC_MOD_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }


    /**
     * updateMod
     * @param cMsg NSBL0410CMsg
     * @param sMsg NSBL0410SMsg
     */
    public static void updateMod(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        if( !(updateSvcMod(cMsg))) {
            return;
        }
        updateSvcModDtl(cMsg, sMsg);

        // START 2018/07/05 T.Ogura [QC#27067,ADD]
        int errIndex = getFirstErrorIndex(cMsg, sMsg);
        if (errIndex > -1) {
            int errPageFrom = (errIndex / cMsg.A.length()) * cMsg.A.length();
            pagenation(cMsg, sMsg, errPageFrom);
        }
        // END   2018/07/05 T.Ogura [QC#27067,ADD]
    }

    // START 2018/07/05 T.Ogura [QC#27067,ADD]
    /**
     * getFirstErrorIndex
     * @param cMsg NSBL0410CMsg
     * @param sMsg NSBL0410SMsg
     * @return int
     */
    public static final int getFirstErrorIndex(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        int errIndex = -1;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (isError(sMsg, i)) {
                errIndex = i;
                break;
            }
        }
      return errIndex;
    }
    // END   2018/07/05 T.Ogura [QC#27067,ADD]

    // START 2018/07/05 T.Ogura [QC#27067,ADD]
    /**
     * isError
     * @param sMsg NSBL0410SMsg
     * @param index index
     * @return boolean
     */
    public static final boolean isError(NSBL0410SMsg sMsg, int index) {
        NSBL0410_ASMsg asMsg = sMsg.A.no(index);

        if (asMsg.svcModRptTrkFlg_A.isError()) {
            return true;
        }

        if (asMsg.svcModObslDt_A.isError()) {
            return true;
        }

        if (asMsg.svcModObslNum_A.isError()) {
            return true;
        }

        if (asMsg.svcModCancDt_A.isError()) {
            return true;
        }

        return false;
    }
    // END   2018/07/05 T.Ogura [QC#27067,ADD]

    /**
     * updateSvcMod
     * @param cMsg NSBL0410CMsg
     * @return Change Line : true
     */
    private static boolean updateSvcMod(NSBL0410CMsg cMsg) {
        SVC_MODTMsg tMsg = getsvcMod(cMsg.glblCmpyCd.getValue(), cMsg.svcModPk.getValue());
        if (tMsg == null) {
            cMsg.setMessageInfo(NSBM0075E, new String[] {TBL_SVCMOD, FIELD_SVCMOD, cMsg.svcModPk.getValue().toString() });
            return false;
        } else {
            if (!isSameTimeStamp(cMsg.ezUpTime.getValue(), cMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(NSBM0075E, new String[] {TBL_SVCMOD, FIELD_SVCMOD, cMsg.svcModPk.getValue().toString() });
                return false;
            }
            setValue(tMsg.svcModNm, cMsg.svcModNm.getValue());
            setValue(tMsg.svcModCmntTxt, cMsg.svcModCmntTxt.getValue());
            // mod start 2016/07/11 CSA Defect#11677
            setValue(tMsg.estSvcLborHourMn, getTm(cMsg.xxEndDplyTmTxt.getValue()));
            // mod end 2016/07/11 CSA Defect#11677

            S21FastTBLAccessor.update(tMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {"TBL_SVCMOD" });
                return false;
            }
        }
        return true;
    }

    /**
     * updateSvcModDtl
     * @param sMsg NSBL0410SMsg
     * @return Change Line : true
     */
    private static void updateSvcModDtl(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        // START 2018/07/05 T.Ogura [QC#27067,ADD]
        boolean errFlg = false;
        // END   2018/07/05 T.Ogura [QC#27067,ADD]

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // START 2018/01/17 M.Kidokoro [QC#22447, ADD]
            if (!checkChangeLine(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i))) {
                continue;
            }
            // END 2018/01/17 M.Kidokoro [QC#22447, ADD]

            // START 2018/07/05 T.Ogura [QC#27067,ADD]
            if ((SVC_MOD_PRTY.SAFETY.equals(sMsg.A.no(i).svcModPrtyDescTxt_A.getValue()) || SVC_MOD_PRTY.NEXT_VISIT.equals(sMsg.A.no(i).svcModPrtyDescTxt_A.getValue()))
                    && ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).svcModRptTrkFlg_A.getValue())) {
                sMsg.A.no(i).svcModRptTrkFlg_A.setErrorInfo(1, NSBM0188E);
                errFlg = true;
            }
            // END   2018/07/05 T.Ogura [QC#27067,ADD]

            // add start 2016/03/08 CSA Defect#4990
            if (hasValue(sMsg.A.no(i).svcModObslNum_A) && !hasValue(sMsg.A.no(i).svcModObslDt_A)) {
                // START 2018/07/05 T.Ogura [QC#27067,MOD]
//                cMsg.A.no(i).svcModObslDt_A.setErrorInfo(1, NSBM0134E, new String[] {"Obsoleted By Mod#", "Obsoleted Date"});
//                continue;
                sMsg.A.no(i).svcModObslDt_A.setErrorInfo(1, NSBM0134E, new String[] {"Obsoleted By Mod#", "Obsoleted Date"});
                errFlg = true;
                // END   2018/07/05 T.Ogura [QC#27067,MOD]
            } else if (!hasValue(sMsg.A.no(i).svcModObslNum_A) && hasValue(sMsg.A.no(i).svcModObslDt_A)) {
                // START 2018/07/05 T.Ogura [QC#27067,MOD]
//                cMsg.A.no(i).svcModObslNum_A.setErrorInfo(1, NSBM0134E, new String[] {"Obsoleted Date", "Obsoleted By Mod#"});
//                continue;
                sMsg.A.no(i).svcModObslNum_A.setErrorInfo(1, NSBM0134E, new String[] {"Obsoleted Date", "Obsoleted By Mod#"});
                errFlg = true;
                // END   2018/07/05 T.Ogura [QC#27067,MOD]
            }
            // add end 2016/03/08 CSA Defect#4990

            // START 2018/07/05 T.Ogura [QC#27067,ADD]
            if (hasValue(sMsg.A.no(i).svcModCancDt_A)) {
                if (ZYPDateUtil.isPastDate(sMsg.A.no(i).svcModCancDt_A.getValue())) {
                    sMsg.A.no(i).svcModCancDt_A.setErrorInfo(1, NSBM0168E, new String[] {"Cancel Date", "Sales Date" });
                    errFlg = true;
                }
            }
            // END   2018/07/05 T.Ogura [QC#27067,ADD]

            // START 2018/07/05 T.Ogura [QC#27067,ADD]
            if (errFlg) {
                continue;
            }
            // END   2018/07/05 T.Ogura [QC#27067,ADD]

            if (sMsg.A.no(i).ezUpTime_A.getValue().isEmpty()) {
                // START 2018/07/05 T.Ogura [QC#27067,DEL]
//                if (!hasValue(sMsg.A.no(i).svcModRptTrkFlg_A)) {
//                    cMsg.A.no(i).svcModRptTrkFlg_A.setErrorInfo(1, ZZZM9007E, new String[] {RPT_TRAC });
//                    return;
//                }
//                if (hasValue(sMsg.A.no(i).svcModCancDt_A)) {
//                    if (ZYPDateUtil.isPastDate(sMsg.A.no(i).svcModCancDt_A.getValue())) {
//                        // mod start 2016/07/11 CSA Defect#11673
//                        cMsg.A.no(i).svcModCancDt_A.setErrorInfo(1, NSBM0168E, new String[] {"Cancel Date", "Sales Date" });
//                        // mod end 2016/07/11 CSA Defect#11673
//                        return;
//                    }
//                }
                // END   2018/07/05 T.Ogura [QC#27067,DEL]
                SVC_MOD_DTLTMsg insertTMsg = new SVC_MOD_DTLTMsg();
                setValue(insertTMsg.glblCmpyCd, cMsg.glblCmpyCd);
                setValue(insertTMsg.ezCancelFlag, "0");
                setValue(insertTMsg.svcModPk, cMsg.svcModPk);
                setValue(insertTMsg.svcModDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MOD_DTL_SQ));
                setValue(insertTMsg.svcMnfCd, sMsg.A.no(i).svcMnfCd_A.getValue());
                setValue(insertTMsg.svcMnfModNum, sMsg.A.no(i).svcMnfModNum_A.getValue());
                setValue(insertTMsg.svcModDocNum, sMsg.A.no(i).svcModDocNum_A.getValue());
                setValue(insertTMsg.mdseCd, sMsg.A.no(i).mdseCd_A.getValue());
                setValue(insertTMsg.svcModPrtyCd, sMsg.A.no(i).svcModPrtyDescTxt_A.getValue());
                setValue(insertTMsg.svcModRptTrkFlg, sMsg.A.no(i).svcModRptTrkFlg_A.getValue());

                setValue(insertTMsg.svcModIssDt, sMsg.A.no(i).svcModIssDt_A.getValue());
                setValue(insertTMsg.svcModStartDt, sMsg.A.no(i).svcModStartDt_A.getValue());
                setValue(insertTMsg.svcModOnHldDt, sMsg.A.no(i).svcModOnHldDt_A.getValue());
                setValue(insertTMsg.svcModCancDt, sMsg.A.no(i).svcModCancDt_A.getValue());
                setValue(insertTMsg.svcModObslDt, sMsg.A.no(i).svcModObslDt_A.getValue());
                setValue(insertTMsg.svcModObslNum, sMsg.A.no(i).svcModObslNum_A.getValue());
                S21FastTBLAccessor.insert(insertTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NSAM0032E, new String[] {"TBL_SVCMODDTL" });
                    return;
                }
                continue;
            }
            SVC_MOD_DTLTMsg tMsg = getsvcModDtl(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).svcModDtlPk_A.getValue());
            if (tMsg == null) {
                cMsg.setMessageInfo(NSBM0075E, new String[] {TBL_SVCMODDTL, FIELD_SVCMODDTL, sMsg.A.no(i).svcModDtlPk_A.getValue().toString() });
                return;
            } else {
                if (!isSameTimeStamp(sMsg.A.no(i).ezUpTime_A.getValue(), sMsg.A.no(i).ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NSBM0075E, new String[] {TBL_SVCMODDTL, FIELD_SVCMODDTL, sMsg.A.no(i).svcModDtlPk_A.getValue().toString() });
                    return;
                }
                // START 2018/07/05 T.Ogura [QC#27067,DEL]
//                if (hasValue(sMsg.A.no(i).svcModCancDt_A)) {
//                    if (ZYPDateUtil.isPastDate(sMsg.A.no(i).svcModCancDt_A.getValue())) {
//                        // mod start 2016/07/11 CSA Defect#11673
//                        cMsg.A.no(i).svcModCancDt_A.setErrorInfo(1, NSBM0168E, new String[] {"Cancel Date", "Sales Date" });
//                        // mod end 2016/07/11 CSA Defect#11673
//                        return;
//                    }
//                }
                // END   2018/07/05 T.Ogura [QC#27067,DEL]
                setValue(tMsg.svcMnfCd, sMsg.A.no(i).svcMnfCd_A.getValue());
                setValue(tMsg.svcMnfModNum, sMsg.A.no(i).svcMnfModNum_A.getValue());
                setValue(tMsg.svcModDocNum, sMsg.A.no(i).svcModDocNum_A.getValue());
                setValue(tMsg.mdseCd, sMsg.A.no(i).mdseCd_A.getValue());
                setValue(tMsg.svcModPrtyCd, sMsg.A.no(i).svcModPrtyDescTxt_A.getValue());
                setValue(tMsg.svcModRptTrkFlg, sMsg.A.no(i).svcModRptTrkFlg_A.getValue());

                setValue(tMsg.svcModIssDt, sMsg.A.no(i).svcModIssDt_A.getValue());
                setValue(tMsg.svcModStartDt, sMsg.A.no(i).svcModStartDt_A.getValue());
                setValue(tMsg.svcModOnHldDt, sMsg.A.no(i).svcModOnHldDt_A.getValue());
                setValue(tMsg.svcModCancDt, sMsg.A.no(i).svcModCancDt_A.getValue());
                setValue(tMsg.svcModObslDt, sMsg.A.no(i).svcModObslDt_A.getValue());
                setValue(tMsg.svcModObslNum, sMsg.A.no(i).svcModObslNum_A.getValue());

                S21FastTBLAccessor.update(tMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NSAM0031E, new String[] {"TBL_SVCMODDTL" });
                    return;
                }
            }
        }
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            SVC_MOD_DTLTMsg tMsg = getsvcModDtl(cMsg.glblCmpyCd.getValue(), sMsg.B.no(i).svcModDtlPk_B.getValue());
            if (tMsg == null) {
                cMsg.setMessageInfo(NSBM0075E, new String[] {TBL_SVCMODDTL, FIELD_SVCMODDTL, sMsg.B.no(i).svcModDtlPk_B.getValue().toString() });
                return;
            }
            // START 2018/07/30 K.Kitachi [QC#27434, ADD]
            SVC_MOD_DTL_ITEMTMsgArray svcModDtlItemList = getSvcModDtlItemList(cMsg.glblCmpyCd.getValue(), sMsg.B.no(i).svcModDtlPk_B.getValue());
            for (int dtlItemIdx = 0; dtlItemIdx < svcModDtlItemList.getValidCount(); dtlItemIdx++) {
                EZDTBLAccessor.logicalRemove(svcModDtlItemList.no(dtlItemIdx));
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcModDtlItemList.no(dtlItemIdx).getReturnCode())) {
                    cMsg.setMessageInfo(NSAM0031E, new String[] {"SVC_MOD_DTL_ITEM" });
                    return;
                }
            }
            SVC_MOD_SER_RNGTMsgArray svcModSerRngList = getSvcModSerRngList(cMsg.glblCmpyCd.getValue(), sMsg.B.no(i).svcModDtlPk_B.getValue());
            for (int serRngIdx = 0; serRngIdx < svcModSerRngList.getValidCount(); serRngIdx++) {
                EZDTBLAccessor.logicalRemove(svcModSerRngList.no(serRngIdx));
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcModSerRngList.no(serRngIdx).getReturnCode())) {
                    cMsg.setMessageInfo(NSAM0031E, new String[] {"SVC_MOD_SER_RNG" });
                    return;
                }
            }
            // END 2018/07/30 K.Kitachi [QC#27434, ADD]
            S21FastTBLAccessor.removeLogical(new SVC_MOD_DTLTMsg[] {tMsg});
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {"TBL_SVCMODDTL" });
                return;
            }
        }
    }

    // add start 2016/03/28 CSA Defect#11677
    private static String getTm(String tm) {
        if (ZYPCommonFunc.hasValue(tm)) {
            return tm.replace(":", "");
        }
        return tm;
    }
    // add end 2016/03/28 CSA Defect#11677

    // START 2018/01/17 M.Kidokoro [QC#22447, ADD]
    private static boolean checkChangeLine(String glblCmpyCd, NSBL0410_ASMsg aSMsg) {
        if (!hasValue(aSMsg.svcModDtlPk_A)) {
            return true;
        }
        NSBL0410_ASMsg cmpDstASMsg = aSMsg;
        SVC_MOD_DTLTMsg cmpSrcTMsg = getsvcModDtl(glblCmpyCd, cmpDstASMsg.svcModDtlPk_A.getValue());

        if (cmpSrcTMsg != null) {
            if (checkChangeLine(cmpSrcTMsg, cmpDstASMsg)) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    private static boolean checkChangeLine(SVC_MOD_DTLTMsg cmpSrc, NSBL0410_ASMsg cmpDst) {
        // svcMnfCd
        if (checkChangeItem(cmpSrc.svcMnfCd, cmpDst.svcMnfCd_A)) {
            return true;
        }
        // svcMnfModNum
        if (checkChangeItem(cmpSrc.svcMnfModNum, cmpDst.svcMnfModNum_A)) {
            return true;
        }
        // svcModDocNum
        if (checkChangeItem(cmpSrc.svcModDocNum, cmpDst.svcModDocNum_A)) {
            return true;
        }
        // mdseCd
        if (checkChangeItem(cmpSrc.mdseCd, cmpDst.mdseCd_A)) {
            return true;
        }
        // svcModPrtyDescTxt
        if (checkChangeItem(cmpSrc.svcModPrtyCd, cmpDst.svcModPrtyDescTxt_A)) {
            return true;
        }
        // svcModRptTrkFlg
        if (checkChangeItem(cmpSrc.svcModRptTrkFlg, cmpDst.svcModRptTrkFlg_A)) {
            return true;
        }
        // svcModIssDt
        if (checkChangeItem(cmpSrc.svcModIssDt, cmpDst.svcModIssDt_A)) {
            return true;
        }
        // svcModStartDt
        if (checkChangeItem(cmpSrc.svcModStartDt, cmpDst.svcModStartDt_A)) {
            return true;
        }
        // svcModOnHldDt
        if (checkChangeItem(cmpSrc.svcModOnHldDt, cmpDst.svcModOnHldDt_A)) {
            return true;
        }
        // svcModCancDt
        if (checkChangeItem(cmpSrc.svcModCancDt, cmpDst.svcModCancDt_A)) {
            return true;
        }
        // svcModObslDt
        if (checkChangeItem(cmpSrc.svcModObslDt, cmpDst.svcModObslDt_A)) {
            return true;
        }
        // svcModObslNum
        if (checkChangeItem(cmpSrc.svcModObslNum, cmpDst.svcModObslNum_A)) {
            return true;
        }

        return false;
    }

    private static boolean checkChangeItem(EZDTStringItem item1, EZDSStringItem item2) {
        if (hasValue(item1)) {
            if (hasValue(item2)) {
                if (item1.getValue().equals(item2.getValue())) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            if (hasValue(item2)) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static boolean checkChangeItem(EZDTDateItem item1, EZDSDateItem item2) {
        if (hasValue(item1)) {
            if (hasValue(item2)) {
                if (item1.getValue().equals(item2.getValue())) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            if (hasValue(item2)) {
                return true;
            } else {
                return false;
            }
        }
    }
    // END 2018/01/17 M.Kidokoro [QC#22447, ADD]

    // START 2018/06/07 U.Kim [QC#22393, ADD]
    public static int getLastPageFromNum(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        int prevPageNum = (sMsg.A.getValidCount() - 1) / cMsg.A.length();
        return (prevPageNum) * cMsg.A.length() + 1;
    }

    public static void copyGlblMsgToBizMsg(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {
        int ixS = (cMsg.xxPageShowFromNum.getValueInt() - 1);
        int i = 0;
        for (; i < cMsg.A.length() && ixS < sMsg.A.getValidCount(); i++) {
            EZDMsg.copy(sMsg.A.no(ixS++), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(i);
    }

    public static void setPageShowNum(EZDCBigDecimalItem xxPageShowOfNum, EZDCBigDecimalItem xxPageShowToNum, int xxPageShowFromNum, int pageRecs, int totalRecs) {
        if (xxPageShowOfNum == null //
                || xxPageShowToNum == null) {
            return;
        }
        if (pageRecs == 0 || totalRecs == 0) {
            return;
        }
        xxPageShowToNum.setValue(xxPageShowFromNum + pageRecs - 1);
        xxPageShowOfNum.setValue(totalRecs);
    }
    // END 2018/06/07 U.Kim [QC#22393, ADD]

    // START 2018/07/30 K.Kitachi [QC#27434, ADD]
    private static SVC_MOD_DTL_ITEMTMsgArray getSvcModDtlItemList(String glblCmpyCd, BigDecimal svcModDtlPk) {
        SVC_MOD_DTL_ITEMTMsg inMsg = new SVC_MOD_DTL_ITEMTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcModDtlPk01", svcModDtlPk);
        return (SVC_MOD_DTL_ITEMTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);
    }

    private static SVC_MOD_SER_RNGTMsgArray getSvcModSerRngList(String glblCmpyCd, BigDecimal svcModDtlPk) {
        SVC_MOD_SER_RNGTMsg inMsg = new SVC_MOD_SER_RNGTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcModDtlPk01", svcModDtlPk);
        return (SVC_MOD_SER_RNGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);
    }
    // END 2018/07/30 K.Kitachi [QC#27434, ADD]
}
