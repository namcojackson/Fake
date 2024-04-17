/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0380;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDConnectionMgr;
import business.blap.NSAL0380.common.NSAL0380CommonLogic;
import business.blap.NSAL0380.constant.NSAL0380Constant;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_RNW_ESCLTMsg;
import business.db.SVC_MEMOTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_AUTO_RNW_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   Fujitsu         T.Yoshida       Create          N/A
 * 2015/12/15   Hitachi         T.Tsuchida      Update          QC#1964
 * 2016/02/15   Hitachi         O.Okuma         Update          QC3029
 * 2016/07/26   Hitachi         A.Kohinata      Update          QC#12199
 * 2017/11/08   Hitachi         M.Kidokoro      Update          QC#21928
 * 2017/11/15   Hitachi         M.Kidokoro      Update          QC#21928-1
 * 2017/11/20   Hitachi         M.Kidokoro      Update          QC#22694
 * 2017/12/21   Hitachi         M.Kidokoro      Update          QC#22660
 * 2018/08/14   Hitachi         K.Kojima        Update          QC#27179
 * 2018/12/20   Hitachi         K.Kim           Update          QC#29636
 * 2019/06/26   Hitachi         A.Kohinata      Update          QC#50931
 * 2023/11/01   CITS            R.Tamura        Update          QC#62046
 *</pre>
 */
public class NSAL0380BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0380Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0380Scrn00_CMN_Submit((NSAL0380CMsg) cMsg, (NSAL0380SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Submit)
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     */
    private void doProcess_NSAL0380Scrn00_CMN_Submit(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {

        cMsg.setCommitSMsg(true);
        NSAL0380CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        // add start 2019/06/26 QC#50931
        int errIndex = checkRDaysRequiredBaseUsage(sMsg);
        if (errIndex != -1) {
            copySMsgToCMsgForError(cMsg, sMsg, errIndex);
            return;
        }
        // add end 2019/06/26 QC#50931

        doInsOrUpd(cMsg, sMsg);

        NSAL0380CommonLogic.copyASMsgToBSMsg(sMsg);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);

        if (!"E".equals(cMsg.getMessageKind())
                && !"W".equals(cMsg.getMessageKind())) {
            cMsg.setMessageInfo(NSAL0380Constant.ZZZM9003I, new String[] {NSAL0380Constant.SUBMIT });
        }

    }

    /**
     * do Insert or Update
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     */
    private void doInsOrUpd(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {

        List<NSAL0380_BSMsg> submitList = new ArrayList<NSAL0380_BSMsg>();
        // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
        List<NSAL0380_BSMsg> machLvlNum1List = new ArrayList<NSAL0380_BSMsg>();
        List<NSAL0380_BSMsg> machLvlNum2List = new ArrayList<NSAL0380_BSMsg>();
        // END 2017/11/08 M.Kidokoro [QC#21928, ADD]

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B1.getValue())
                    || ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B2.getValue())) {
                submitList.add(sMsg.B.no(i));
            }
            // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
            if (NSAL0380Constant.MACH_LVL_NUM_1.equals(getMachLvlNum(sMsg.B.no(i)))) {
                machLvlNum1List.add(sMsg.B.no(i));
            } else if (NSAL0380Constant.MACH_LVL_NUM_2.equals(getMachLvlNum(sMsg.B.no(i)))) {
                machLvlNum2List.add(sMsg.B.no(i));
            }
            // END 2017/11/08 M.Kidokoro [QC#21928, ADD]
        }

        if (submitList.isEmpty()) {
            cMsg.setMessageInfo(NSAL0380Constant.NSAM0015E);
            return;
        }

        // add start 2016/07/26 CSA Defect#12199
        if (!validation(cMsg.glblCmpyCd.getValue(), submitList)) {
            cMsg.setMessageInfo(NSAL0380Constant.NSAM0394W);
            return;
        }
        // add end 2016/07/26 CSA Defect#12199

        boolean commitFlg = true;
        BigDecimal befEndRnwDaysAot = submitList.get(0).befEndRnwDaysAot_B.getValue();
        BigDecimal parrentDsContrPk = submitList.get(0).dsContrPk_B.getValue();
        int parrnetCnt = 0;

        // START 2018/08/14 K.Kojima [QC#27179,DEL]
        // if (NSAL0380Constant.MACH_LVL_NUM_3.equals(getMachLvlNum(submitList.get(0)))) {
        //     befEndRnwDaysAot = getParrentBefEndRnwDaysAot(sMsg, submitList.get(0).dsContrPk_B.getValue(), submitList.get(0).dsContrDtlPk_B.getValue());
        // }
        // END 2018/08/14 K.Kojima [QC#27179,DEL]

        // START 2018/08/14 K.Kojima [QC#27179,ADD]
        List<String> contrAutoRnwTpForAutoRnw = NSAL0380Query.getInstance().getContrAutoRnwTpForAutoRnw(cMsg.glblCmpyCd.getValue());
        // END 2018/08/14 K.Kojima [QC#27179,ADD]

        // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
        NSAL0380_BSMsg machLvlNum1BSMsg = new NSAL0380_BSMsg();
        NSAL0380_BSMsg machLvlNum2BSMsg = new NSAL0380_BSMsg();
        // END 2017/11/08 M.Kidokoro [QC#21928, ADD]
        for (int i = 0; i < submitList.size(); i++) {

            NSAL0380_BSMsg bsMsg = submitList.get(i);
            String machLvlNum = getMachLvlNum(bsMsg);
            BigDecimal dsContrPk = bsMsg.dsContrPk_B.getValue();
            BigDecimal dsContrRnwEsclPk = bsMsg.dsContrRnwEsclPk_B.getValue();
            befEndRnwDaysAot = bsMsg.befEndRnwDaysAot_B.getValue();

            if (parrentDsContrPk.compareTo(dsContrPk) != 0) {

                if (commitFlg) {
                    EZDConnectionMgr.getInstance().commit();
                } else {
                    EZDConnectionMgr.getInstance().rollback();
                }
                commitFlg = true;
                // START 2018/08/14 K.Kojima [QC#27179,DEL]
                // if (NSAL0380Constant.MACH_LVL_NUM_3.equals(getMachLvlNum(bsMsg))) {
                //     befEndRnwDaysAot = getParrentBefEndRnwDaysAot(sMsg, dsContrPk, bsMsg.dsContrDtlPk_B.getValue());
                // }
                // END 2018/08/14 K.Kojima [QC#27179,DEL]
                parrnetCnt = i;

            } else if (!commitFlg) {
                continue;
            }

            // START 2018/08/14 K.Kojima [QC#27179,ADD]
            if (NSAL0380Constant.MACH_LVL_NUM_3.equals(getMachLvlNum(bsMsg))) {
                befEndRnwDaysAot = getParrentBefEndRnwDaysAot(sMsg, dsContrPk, bsMsg.dsContrDtlPk_B.getValue(), bsMsg.contrAutoRnwTpCd_B.getValue(), contrAutoRnwTpForAutoRnw);
            }
            // END 2018/08/14 K.Kojima [QC#27179,ADD]

            if (NSAL0380Constant.MACH_LVL_NUM_1.equals(machLvlNum)) {
                parrentDsContrPk = dsContrPk;
                if (!updDsContr(cMsg, bsMsg)) {
                    setXxDsMsgEntryTxt(sMsg, parrnetCnt, parrentDsContrPk);
                    cMsg.setMessageInfo(NSAL0380Constant.NSAM0394W);
                    commitFlg = false;
                    continue;
                }
            } else if (NSAL0380Constant.MACH_LVL_NUM_2.equals(machLvlNum)) {
                if (!updDsContrDtl(cMsg, bsMsg)) {
                    setXxDsMsgEntryTxt(sMsg, parrnetCnt, parrentDsContrPk);
                    cMsg.setMessageInfo(NSAL0380Constant.NSAM0394W);
                    commitFlg = false;
                    continue;
                }
            }

            if (ZYPCommonFunc.hasValue(bsMsg.dsContrRnwEsclPk_B.getValue())) {

                if (!updDsContrRnwEscl(cMsg, bsMsg, machLvlNum, befEndRnwDaysAot)) {
                    setXxDsMsgEntryTxt(sMsg, parrnetCnt, parrentDsContrPk);
                    cMsg.setMessageInfo(NSAL0380Constant.NSAM0394W);
                    commitFlg = false;
                    continue;
                }
                dsContrRnwEsclPk = bsMsg.dsContrRnwEsclPk_B.getValue();

            } else {
                DS_CONTR_RNW_ESCLTMsg tMsg = new DS_CONTR_RNW_ESCLTMsg();

                // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
                if (NSAL0380Constant.MACH_LVL_NUM_2.equals(getMachLvlNum(bsMsg))) {
                    for (int index = 0; index < machLvlNum1List.size(); index++) {
                        if (machLvlNum1List.get(index).dsContrPk_B.getValue().compareTo(bsMsg.dsContrPk_B.getValue()) == 0) {
                            machLvlNum1BSMsg = machLvlNum1List.get(index);
                        }
                    }
                    if (!checkOverlapValue(bsMsg, machLvlNum1BSMsg)) {
                        // START 2017/11/15 M.Kidokoro [QC#21928-1, DEL]
//                        setXxDsMsgEntryTxt(sMsg, parrnetCnt, parrentDsContrPk);
//                        cMsg.setMessageInfo(NSAL0380Constant.NSAM0394W);
//                        commitFlg = false;
                        // END 2017/11/15 M.Kidokoro [QC#21928-1, DEL]
                        continue;
                    }
                } else if (NSAL0380Constant.MACH_LVL_NUM_3.equals(getMachLvlNum(bsMsg))) {
                    for (int index = 0; index < machLvlNum2List.size(); index++) {
                        if (machLvlNum2List.get(index).dsContrPk_B.getValue().compareTo(bsMsg.dsContrPk_B.getValue()) == 0
                                && machLvlNum2List.get(index).dsContrDtlPk_B.getValue().compareTo(bsMsg.dsContrDtlPk_B.getValue()) == 0) {
                            machLvlNum2BSMsg = machLvlNum2List.get(index);
                        }
                    }
                    if (!checkOverlapValue(bsMsg, machLvlNum2BSMsg)) {
                        // START 2017/11/15 M.Kidokoro [QC#21928-1, DEL]
//                        setXxDsMsgEntryTxt(sMsg, parrnetCnt, parrentDsContrPk);
//                        cMsg.setMessageInfo(NSAL0380Constant.NSAM0394W);
//                        commitFlg = false;
                        // END 2017/11/15 M.Kidokoro [QC#21928-1, DEL]
                        // START 2018/12/20 [QC#29636, ADD]
                        ZYPEZDItemValueSetter.setValue(bsMsg.xxDsMsgEntryTxt_B, NSAL0380Constant.RTRN_MSG_SUCCESS);
                        // END 2018/12/20 [QC#29636, ADD]
                        continue;
                    }
                }
                // END 2017/11/08 M.Kidokoro [QC#21928, ADD]

                if (!insDsContrRnwExcl(cMsg, bsMsg, tMsg, machLvlNum, befEndRnwDaysAot)) {
                    dsContrRnwEsclPk = tMsg.dsContrRnwEsclPk.getValue();
                    setXxDsMsgEntryTxt(sMsg, parrnetCnt, parrentDsContrPk);
                    cMsg.setMessageInfo(NSAL0380Constant.NSAM0394W);
                    commitFlg = false;
                    continue;
                }
                dsContrRnwEsclPk = tMsg.dsContrRnwEsclPk.getValue();
            }

            if (!insertSvcMemo(cMsg, bsMsg, dsContrRnwEsclPk)) {
                setXxDsMsgEntryTxt(sMsg, parrnetCnt, parrentDsContrPk);
                cMsg.setMessageInfo(NSAL0380Constant.NSAM0394W);
                commitFlg = false;
                continue;
            }
        }
        if (commitFlg) {
            EZDConnectionMgr.getInstance().commit();
        } else {
            EZDConnectionMgr.getInstance().rollback();
        }
    }

    private void setXxDsMsgEntryTxt(NSAL0380SMsg sMsg, int startCnt, BigDecimal dsContrPk) {

        NSAL0380_BSMsgArray bsMsgArry = sMsg.B;

        for (int i = startCnt; i < bsMsgArry.getValidCount(); i++) {
            NSAL0380_BSMsg bsMsg = bsMsgArry.no(i);
            if (bsMsg.dsContrPk_B.getValue().compareTo(dsContrPk) != 0) {
                return;
            }
            if (ZYPConstant.CHKBOX_ON_Y.equals(bsMsg.xxChkBox_B1.getValue())
                    || ZYPConstant.CHKBOX_ON_Y.equals(bsMsg.xxChkBox_B2.getValue())) {
                ZYPEZDItemValueSetter.setValue(bsMsg.xxDsMsgEntryTxt_B, NSAL0380Constant.RTRN_MSG_FAILED);
            }

        }
    }

    private DS_CONTR_RNW_ESCLTMsg setDsContrRnwEsclTMsgForIns(NSAL0380CMsg cMsg, NSAL0380_BSMsg bsMsg, DS_CONTR_RNW_ESCLTMsg insTMsg, String machLvlNum, BigDecimal befEndRnwDaysAot) {

        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.dsContrRnwEsclPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_RNW_ESCL_SQ));
        ZYPEZDItemValueSetter.setValue(insTMsg.dsContrPk, bsMsg.dsContrPk_B);

        if (!NSAL0380Constant.MACH_LVL_NUM_1.equals(machLvlNum)) {
            ZYPEZDItemValueSetter.setValue(insTMsg.dsContrDtlPk, bsMsg.dsContrDtlPk_B);
        }

        ZYPEZDItemValueSetter.setValue(insTMsg.dsContrMachLvlNum, machLvlNum);

        if (NSAL0380Constant.MACH_LVL_NUM_3.equals(machLvlNum)) {
            ZYPEZDItemValueSetter.setValue(insTMsg.dsContrBaseUsgNm, bsMsg.dsContrBaseUsgNm_B);
        }

        ZYPEZDItemValueSetter.setValue(insTMsg.contrAutoRnwTpCd, bsMsg.contrAutoRnwTpCd_B);
        ZYPEZDItemValueSetter.setValue(insTMsg.rnwPrcMethCd, bsMsg.rnwPrcMethCd_B);
        ZYPEZDItemValueSetter.setValue(insTMsg.befEndRnwDaysAot, befEndRnwDaysAot);
        ZYPEZDItemValueSetter.setValue(insTMsg.basePrcUpRatio, bsMsg.basePrcUpRatio_B);
        ZYPEZDItemValueSetter.setValue(insTMsg.mtrPrcUpRatio, bsMsg.mtrPrcUpRatio_B);

        ZYPEZDItemValueSetter.setValue(insTMsg.firstYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insTMsg.scdYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insTMsg.thirdYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insTMsg.frthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insTMsg.fifthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insTMsg.sixthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insTMsg.svnthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insTMsg.eighthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insTMsg.ninthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insTMsg.tenthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);

        // START 2018/12/20 [QC#29636, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.rnwMaxPrcUpRatio, bsMsg.maxPrcUpRatio_B);
        // END 2018/12/20 [QC#29636, ADD]
        return insTMsg;
    }

    private void setDsContrRnwEsclTMsgForUpd(NSAL0380_BSMsg bsMsg, DS_CONTR_RNW_ESCLTMsg updTMsg, String machLvlNum, BigDecimal befEndRnwDaysAot) {

      ZYPEZDItemValueSetter.setValue(updTMsg.contrAutoRnwTpCd, bsMsg.contrAutoRnwTpCd_B);
      ZYPEZDItemValueSetter.setValue(updTMsg.rnwPrcMethCd, bsMsg.rnwPrcMethCd_B);
      ZYPEZDItemValueSetter.setValue(updTMsg.befEndRnwDaysAot, befEndRnwDaysAot);
      ZYPEZDItemValueSetter.setValue(updTMsg.basePrcUpRatio, bsMsg.basePrcUpRatio_B);
      ZYPEZDItemValueSetter.setValue(updTMsg.mtrPrcUpRatio, bsMsg.mtrPrcUpRatio_B);
      // START 2018/12/20 [QC#29636, ADD]
      ZYPEZDItemValueSetter.setValue(updTMsg.rnwMaxPrcUpRatio, bsMsg.maxPrcUpRatio_B);
      // END 2018/12/20 [QC#29636, ADD]
    }

    private boolean updDsContr(NSAL0380CMsg cMsg, NSAL0380_BSMsg bsMsg) {

        DS_CONTRTMsg tMsg = getDsContr(bsMsg, cMsg);

        if (tMsg == null) {
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(bsMsg.ezUpTime_B1.getValue(), bsMsg.ezUpTimeZone_B1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            return false;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.maxContrRnwCnt, bsMsg.maxContrRnwCnt_B);
        // START 2018/12/20 [QC#29636, DEL]
        // ZYPEZDItemValueSetter.setValue(tMsg.maxPrcUpRatio, bsMsg.maxPrcUpRatio_B);
        // END 2018/12/20 [QC#29636, DEL]
        S21FastTBLAccessor.update(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return false;
        }
        ZYPEZDItemValueSetter.setValue(bsMsg.xxDsMsgEntryTxt_B, NSAL0380Constant.RTRN_MSG_SUCCESS);
        return true;
    }

    private boolean updDsContrDtl(NSAL0380CMsg cMsg, NSAL0380_BSMsg bsMsg) {

        DS_CONTR_DTLTMsg tMsg = getDsContrDtl(cMsg, bsMsg);

        if (tMsg == null) {
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(bsMsg.ezUpTime_B2.getValue(), bsMsg.ezUpTimeZone_B2.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(NSAL0380Constant.ZZZM9004E);
            return false;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.maxContrRnwCnt, bsMsg.maxContrRnwCnt_B);
        // START 2018/12/20 [QC#29636, DEL]
        // ZYPEZDItemValueSetter.setValue(tMsg.maxPrcUpRatio, bsMsg.maxPrcUpRatio_B);
        // END 2018/12/20 [QC#29636, DEL]
        S21FastTBLAccessor.update(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return false;
        }
        ZYPEZDItemValueSetter.setValue(bsMsg.xxDsMsgEntryTxt_B, NSAL0380Constant.RTRN_MSG_SUCCESS);
        return true;
    }

    private boolean insDsContrRnwExcl(NSAL0380CMsg cMsg, NSAL0380_BSMsg bsMsg, DS_CONTR_RNW_ESCLTMsg insTMsg, String machLvlNum, BigDecimal befEndRnwDaysAot) {

        // check exclusion
        if (!checkInsExclusion(cMsg, bsMsg, machLvlNum)) {
            return false;
        }

        setDsContrRnwEsclTMsgForIns(cMsg, bsMsg, insTMsg, machLvlNum, befEndRnwDaysAot);
        S21FastTBLAccessor.insert(insTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insTMsg.getReturnCode())) {
            return false;
        }
        ZYPEZDItemValueSetter.setValue(bsMsg.xxDsMsgEntryTxt_B, NSAL0380Constant.RTRN_MSG_SUCCESS);
        return true;
    }

    private boolean updDsContrRnwEscl(NSAL0380CMsg cMsg, NSAL0380_BSMsg bsMsg, String machLvlNum, BigDecimal befEndRnwDaysAot) {

        DS_CONTR_RNW_ESCLTMsg updTMsg = getsDsContrRnwExcl(cMsg, bsMsg);

        if (updTMsg == null) {
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(bsMsg.ezUpTime_B3.getValue(), bsMsg.ezUpTimeZone_B3.getValue(), updTMsg.ezUpTime.getValue(), updTMsg.ezUpTimeZone.getValue())) {
            return false;
        }

        if (NSAL0380Constant.MACH_LVL_NUM_3.equals(machLvlNum)) {
            setDsContrRnwEsclTMsgForUpd(bsMsg, updTMsg, machLvlNum, befEndRnwDaysAot);
        } else {
            setDsContrRnwEsclTMsgForUpd(bsMsg, updTMsg, machLvlNum, bsMsg.befEndRnwDaysAot_B.getValue());
        }

        S21FastTBLAccessor.update(updTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            return false;
        }
        ZYPEZDItemValueSetter.setValue(bsMsg.xxDsMsgEntryTxt_B, NSAL0380Constant.RTRN_MSG_SUCCESS);
        return true;
    }

    private DS_CONTRTMsg getDsContr(NSAL0380_BSMsg bsMsg, NSAL0380CMsg cMsg) {

        DS_CONTRTMsg tMsg = new DS_CONTRTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, bsMsg.dsContrPk_B);

        return (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsg);
    }

    private DS_CONTR_DTLTMsg getDsContrDtl(NSAL0380CMsg cMsg, NSAL0380_BSMsg bsMsg) {

        DS_CONTR_DTLTMsg lockTMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(lockTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(lockTMsg.dsContrDtlPk, bsMsg.dsContrDtlPk_B);
        return (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(lockTMsg);
    }

    private DS_CONTR_RNW_ESCLTMsg getsDsContrRnwExcl(NSAL0380CMsg cMsg, NSAL0380_BSMsg bsMsg) {

        DS_CONTR_RNW_ESCLTMsg lockTMsg = new DS_CONTR_RNW_ESCLTMsg();

        ZYPEZDItemValueSetter.setValue(lockTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(lockTMsg.dsContrRnwEsclPk, bsMsg.dsContrRnwEsclPk_B);
        return (DS_CONTR_RNW_ESCLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(lockTMsg);
    }

    /**
     * get Machine Level Number
     * @param cMsg NSAL0380CMsg
     * @return Machine Level Number
     */
    private String getMachLvlNum(NSAL0380_BSMsg bsMsg) {

        if (ZYPCommonFunc.hasValue(bsMsg.dsContrNum_B.getValue())) {
            return NSAL0380Constant.MACH_LVL_NUM_1;
        } else if (NSAL0380Constant.MACH_LVL_NUM_2.equals(bsMsg.xxDplyByCtrlAncrLvlCd_B.getValue())) {
            return NSAL0380Constant.MACH_LVL_NUM_2;
        } else {
            return NSAL0380Constant.MACH_LVL_NUM_3;
        }
    }

    /**
     * check Insert Exclusion
     * @param cMsg NSAL0380CMsg
     * @param bsMsg NSAL0380_BSMsg
     * @param machLvlNum Machine Level Number
     * @return No Exclusion Error : true
     */
    private boolean checkInsExclusion(NSAL0380CMsg cMsg, NSAL0380_BSMsg bsMsg, String machLvlNum) {

        if (NSAL0380Constant.MACH_LVL_NUM_1.equals(machLvlNum)) {
            if (NSAL0380Query.getInstance().isExistFstData(cMsg, bsMsg)) {
                return false;
            }
        } else if (NSAL0380Constant.MACH_LVL_NUM_2.equals(machLvlNum)) {
            if (NSAL0380Query.getInstance().isExistScdData(cMsg, bsMsg)) {
                return false;
            }
        } else if (NSAL0380Constant.MACH_LVL_NUM_3.equals(machLvlNum)) {
            if (NSAL0380Constant.BASE_NM.equals(bsMsg.dsContrBaseUsgNm_B.getValue())) {
                if (NSAL0380Query.getInstance().isExistTrdData(cMsg, bsMsg, true)) {
                    return false;
                }
            } else if (NSAL0380Constant.USG_NM.equals(bsMsg.dsContrBaseUsgNm_B.getValue())) {
                if (NSAL0380Query.getInstance().isExistTrdData(cMsg, bsMsg, false)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Submit Service Memo
     * @param cMsg NSAL0380CMsg
     * @param bsMsg NSAL0380_BSMsg
     */
    private boolean insertSvcMemo(NSAL0380CMsg cMsg, NSAL0380_BSMsg bsMsg, BigDecimal dsContrRnwEsclPk) {

        SVC_MEMOTMsg tMsg = new SVC_MEMOTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMemoTpCd, SVC_MEMO_TP.RENEWAL_RULES);
        ZYPEZDItemValueSetter.setValue(tMsg.svcCmntTxt, cMsg.svcCmntTxt_H);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, bsMsg.dsContrPk_B);
        ZYPEZDItemValueSetter.setValue(tMsg.lastUpdUsrId, cMsg.getUserID());
        ZYPEZDItemValueSetter.setValue(tMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(NSAL0380Constant.DT_FORMAT_TS));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMemoRsnCd, cMsg.svcMemoRsnCd_H);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMemoTrxNum, dsContrRnwEsclPk.toString());
        ZYPEZDItemValueSetter.setValue(tMsg.svcMemoTrxNm, NSAL0380Constant.DS_CONTR_RNW_ESCL_PK);
        S21FastTBLAccessor.insert(tMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    // START 2018/08/14 K.Kojima [QC#27179,MOD]
    // private BigDecimal getParrentBefEndRnwDaysAot(NSAL0380SMsg sMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
    private BigDecimal getParrentBefEndRnwDaysAot(NSAL0380SMsg sMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String contrAutoRnwTpCd, List<String> contrAutoRnwTpForAutoRnw) {
    // END 2018/08/14 K.Kojima [QC#27179,MOD]

        // START 2018/08/14 K.Kojima [QC#27179,ADD]
        if (!ZYPCommonFunc.hasValue(contrAutoRnwTpCd) || contrAutoRnwTpForAutoRnw == null) {
            return null;
        }
        if (!contrAutoRnwTpForAutoRnw.contains(contrAutoRnwTpCd)) {
            return null;
        }
        // END 2018/08/14 K.Kojima [QC#27179,ADD]

        NSAL0380_BSMsgArray bsMsgArry = sMsg.B;
        BigDecimal befEndRnwDaysAotLvl1 = null;
        for (int i = 0; i < bsMsgArry.getValidCount(); i++) {
            NSAL0380_BSMsg bsMsg = bsMsgArry.no(i);
            if (bsMsg.dsContrPk_B.getValue().compareTo(dsContrPk) == 0) {

                if (NSAL0380Constant.MACH_LVL_NUM_1.equals(getMachLvlNum(bsMsg))) {
                    befEndRnwDaysAotLvl1 = bsMsg.befEndRnwDaysAot_B.getValue();
                } else if (NSAL0380Constant.MACH_LVL_NUM_2.equals(getMachLvlNum(bsMsg))
                        && bsMsg.dsContrDtlPk_B.getValue().compareTo(dsContrDtlPk) == 0) {

                    if (ZYPCommonFunc.hasValue(bsMsg.befEndRnwDaysAot_B)) {
                        return bsMsg.befEndRnwDaysAot_B.getValue();
                    } else {
                        return befEndRnwDaysAotLvl1;
                    }
                }
            }
        }
        return null;
    }

    // add start 2016/07/26 CSA Defect#12199
    private boolean validation(String glblCmpyCd, List<NSAL0380_BSMsg> submitList) {
        boolean result = true;
        // START 2017/12/21 M.Kidokoro [QC#22660, ADD]
        Map<BigDecimal, Boolean> diffThruDtMap = new HashMap<BigDecimal, Boolean>();
        // END 2017/12/21 M.Kidokoro [QC#22660, ADD]

        for (int i = 0; i < submitList.size(); i++) {
            NSAL0380_BSMsg bsMsg = submitList.get(i);
            String machLvlNum = getMachLvlNum(bsMsg);

            if (!NSXC001001ContrValidation.checkAutoRnwMeth(glblCmpyCd, bsMsg.contrAutoRnwTpCd_B.getValue(), bsMsg.rnwPrcMethCd_B.getValue())) {
                bsMsg.rnwPrcMethCd_B.setErrorInfo(1, NSAL0380Constant.NSAM0081E, new String[] {"Renwewal Type", "Renwewal Method" });
                result = false;
            } else {
                if (ZYPCommonFunc.hasValue(bsMsg.rnwPrcMethCd_B)) {
                    // START 2017/12/21 M.Kidokoro [QC#22660, MOD]
//                    if (NSAL0380Constant.MACH_LVL_NUM_1.equals(machLvlNum) || NSAL0380Constant.MACH_LVL_NUM_2.equals(machLvlNum)
//                            || (NSAL0380Constant.MACH_LVL_NUM_3.equals(machLvlNum) && NSAL0380Constant.BASE_NM.equals(bsMsg.dsContrBaseUsgNm_B.getValue()))) {
                    if (NSAL0380Constant.MACH_LVL_NUM_1.equals(machLvlNum) || (!DS_CONTR_CATG.AGGREGATE.equals(bsMsg.dsContrCatgCd_B.getValue()) && NSAL0380Constant.MACH_LVL_NUM_2.equals(machLvlNum))
                            || (NSAL0380Constant.MACH_LVL_NUM_3.equals(machLvlNum) && NSAL0380Constant.BASE_NM.equals(bsMsg.dsContrBaseUsgNm_B.getValue()))) {
                    // END 2017/12/21 M.Kidokoro [QC#22660, MOD]
                        if (!NSXC001001ContrValidation.checkBasePrcUpRatioMandatory(glblCmpyCd, bsMsg.contrAutoRnwTpCd_B.getValue(), bsMsg.rnwPrcMethCd_B.getValue(), bsMsg.basePrcUpRatio_B.getValue())) {
                            bsMsg.basePrcUpRatio_B.setErrorInfo(1, NSAL0380Constant.NSAM0081E, new String[] {ZYPCodeDataUtil.getName(RNW_PRC_METH.class, glblCmpyCd, RNW_PRC_METH.MARKUP_PERCENT), "Renwewal Base Price Up Ratio" });
                            result = false;
                        }
                    }

                    if (!NSXC001001ContrValidation.checkBasePrcUpRatioNotAcceptable(glblCmpyCd, bsMsg.contrAutoRnwTpCd_B.getValue(), bsMsg.rnwPrcMethCd_B.getValue(), bsMsg.basePrcUpRatio_B.getValue())) {
                        bsMsg.basePrcUpRatio_B.setErrorInfo(1, NSAL0380Constant.NSZM0684E, new String[] {"Renwewal Base Price Up Ratio", ZYPCodeDataUtil.getName(RNW_PRC_METH.class, glblCmpyCd, RNW_PRC_METH.MARKUP_PERCENT) });
                        result = false;
                    }

                    // START 2017/12/21 M.Kidokoro [QC#22660, MOD]
//                    if (NSAL0380Constant.MACH_LVL_NUM_1.equals(machLvlNum) || NSAL0380Constant.MACH_LVL_NUM_2.equals(machLvlNum)
//                            || (NSAL0380Constant.MACH_LVL_NUM_3.equals(machLvlNum) && NSAL0380Constant.USG_NM.equals(bsMsg.dsContrBaseUsgNm_B.getValue()))) {
                    if (NSAL0380Constant.MACH_LVL_NUM_1.equals(machLvlNum) || (!DS_CONTR_CATG.AGGREGATE.equals(bsMsg.dsContrCatgCd_B.getValue()) && NSAL0380Constant.MACH_LVL_NUM_2.equals(machLvlNum))
                            || (NSAL0380Constant.MACH_LVL_NUM_3.equals(machLvlNum) && NSAL0380Constant.USG_NM.equals(bsMsg.dsContrBaseUsgNm_B.getValue()))) {
                    // END 2017/12/21 M.Kidokoro [QC#22660, MOD]
                        if (!NSXC001001ContrValidation.checkUsgPrcUpRatioMandatory(glblCmpyCd, bsMsg.contrAutoRnwTpCd_B.getValue(), bsMsg.rnwPrcMethCd_B.getValue(), bsMsg.mtrPrcUpRatio_B.getValue())) {
                            bsMsg.mtrPrcUpRatio_B.setErrorInfo(1, NSAL0380Constant.NSAM0081E, new String[] {ZYPCodeDataUtil.getName(RNW_PRC_METH.class, glblCmpyCd, RNW_PRC_METH.MARKUP_PERCENT), "Renwewal Meter Price Up Ratio" });
                            result = false;
                        }
                    }

                    if (!NSXC001001ContrValidation.checkUsgPrcUpRatioNotAcceptable(glblCmpyCd, bsMsg.contrAutoRnwTpCd_B.getValue(), bsMsg.rnwPrcMethCd_B.getValue(), bsMsg.mtrPrcUpRatio_B.getValue())) {
                        bsMsg.mtrPrcUpRatio_B.setErrorInfo(1, NSAL0380Constant.NSZM0684E, new String[] {"Renwewal Meter Price Up Ratio", ZYPCodeDataUtil.getName(RNW_PRC_METH.class, glblCmpyCd, RNW_PRC_METH.MARKUP_PERCENT) });
                        result = false;
                    }

                    // START 2017/12/21 M.Kidokoro [QC#22660, ADD]
                    if (diffThruDtMap.containsKey(bsMsg.dsContrPk_B.getValue())) {
                        if (!diffThruDtMap.get(bsMsg.dsContrPk_B.getValue())) {
                            bsMsg.contrAutoRnwTpCd_B.setErrorInfo(1, NSAL0380Constant.NSAM0710E, new String[] {"Renewal Type", "Original End Date" });
                        }
                    } else {
                        if (DS_CONTR_CATG.AGGREGATE.equals(bsMsg.dsContrCatgCd_B.getValue())
                                && (CONTR_AUTO_RNW_TP.AUTO_RENEW_BASE_OVERAGE.equals(bsMsg.contrAutoRnwTpCd_B.getValue())
                                        || CONTR_AUTO_RNW_TP.AUTO_RENEW_OVERAGE_ONLY.equals(bsMsg.contrAutoRnwTpCd_B.getValue())
                                        || CONTR_AUTO_RNW_TP.MANUAL_RENEW_BASE_OVERAGE.equals(bsMsg.contrAutoRnwTpCd_B.getValue())
                                        || CONTR_AUTO_RNW_TP.MANUAL_RENEW_OVERAGE_ONLY.equals(bsMsg.contrAutoRnwTpCd_B.getValue()))) {
                            if (NSAL0380Query.getInstance().isExistDiffThruDate(glblCmpyCd, bsMsg)) {
                                diffThruDtMap.put(bsMsg.dsContrPk_B.getValue(), false);
                                bsMsg.contrAutoRnwTpCd_B.setErrorInfo(1, NSAL0380Constant.NSAM0710E, new String[] {"Renewal Type", "Original End Date" });
                                result = false;
                            } else {
                                diffThruDtMap.put(bsMsg.dsContrPk_B.getValue(), true);
                            }
                        } else {
                            diffThruDtMap.put(bsMsg.dsContrPk_B.getValue(), true);
                        }
                    }
                    // END 2017/12/21 M.Kidokoro [QC#22660, ADD]
                }
            }
        }
        return result;
    }
    // add end 2016/07/26 CSA Defect#12199

    // START 2017/11/15 M.Kidokoro [QC#21928-1, ADD]
    private boolean checkOverlapValue(NSAL0380_BSMsg curBSMsg, NSAL0380_BSMsg upperBSMsg) {
        boolean result = true;
        int ovrlpCnt = 0;
        int chkObjCnt = 0;

        if (CONTR_AUTO_RNW_TP.DO_NOT_RENEW.equals(curBSMsg.contrAutoRnwTpCd_B.getValue())) {
            // Check_1:Type
            if (upperBSMsg.contrAutoRnwTpCd_B.getValue().equals(curBSMsg.contrAutoRnwTpCd_B.getValue())) {
                result = false;
                return result;
            }
        }

        // Check_1:Type
        chkObjCnt++;
        if (upperBSMsg.contrAutoRnwTpCd_B.getValue().equals(curBSMsg.contrAutoRnwTpCd_B.getValue())) {
            ovrlpCnt++;
        }

        // Check_2:Method
        // START 2017/11/20 M.Kidokoro [QC#22694, MOD]
//        chkObjCnt++;
//        if (upperBSMsg.rnwPrcMethCd_B.getValue().equals(curBSMsg.rnwPrcMethCd_B.getValue())) {
//            ovrlpCnt++;
//        }
        if (ZYPCommonFunc.hasValue(upperBSMsg.rnwPrcMethCd_B.getValue()) && ZYPCommonFunc.hasValue(curBSMsg.rnwPrcMethCd_B.getValue())) {
            chkObjCnt++;
            if (upperBSMsg.rnwPrcMethCd_B.getValue().equals(curBSMsg.rnwPrcMethCd_B.getValue())) {
                ovrlpCnt++;
            }
        }
        // END 2017/11/20 M.Kidokoro [QC#22694, MOD]

        if (NSAL0380Constant.MACH_LVL_NUM_2.equals(getMachLvlNum(curBSMsg))) {
            // Check_3:#Days
            // START 2017/11/20 M.Kidokoro [QC#22694, MOD]
//            if (hasValue(upperBSMsg.befEndRnwDaysAot_B.getValue()) && hasValue(curBSMsg.befEndRnwDaysAot_B.getValue())) {
            if (ZYPCommonFunc.hasValue(upperBSMsg.befEndRnwDaysAot_B.getValue()) && ZYPCommonFunc.hasValue(curBSMsg.befEndRnwDaysAot_B.getValue())) {
            // END 2017/11/20 M.Kidokoro [QC#22694, MOD]
                chkObjCnt++;
                if (upperBSMsg.befEndRnwDaysAot_B.getValue().compareTo(curBSMsg.befEndRnwDaysAot_B.getValue()) == 0) {
                    ovrlpCnt++;
                }
            }

            // Check_4:Max# Renew
            // START 2017/11/20 M.Kidokoro [QC#22694, MOD]
//            if (hasValue(upperBSMsg.maxContrRnwCnt_B.getValue()) && hasValue(curBSMsg.maxContrRnwCnt_B.getValue())) {
            if (ZYPCommonFunc.hasValue(upperBSMsg.maxContrRnwCnt_B.getValue()) && ZYPCommonFunc.hasValue(curBSMsg.maxContrRnwCnt_B.getValue())) {
            // END 2017/11/20 M.Kidokoro [QC#22694, MOD]
                chkObjCnt++;
                if (upperBSMsg.maxContrRnwCnt_B.getValue().compareTo(curBSMsg.maxContrRnwCnt_B.getValue()) == 0) {
                    ovrlpCnt++;
                }
            }

            // Check_5:Max%
            // START 2017/11/20 M.Kidokoro [QC#22694, MOD]
//            if (hasValue(upperBSMsg.maxPrcUpRatio_B.getValue()) && hasValue(curBSMsg.maxPrcUpRatio_B.getValue())) {
            if (ZYPCommonFunc.hasValue(upperBSMsg.maxPrcUpRatio_B.getValue()) && ZYPCommonFunc.hasValue(curBSMsg.maxPrcUpRatio_B.getValue())) {
            // END 2017/11/20 M.Kidokoro [QC#22694, MOD]
                chkObjCnt++;
                if (upperBSMsg.maxPrcUpRatio_B.getValue().compareTo(curBSMsg.maxPrcUpRatio_B.getValue()) == 0) {
                    ovrlpCnt++;
                }
            }

            if ((CONTR_AUTO_RNW_TP.AUTO_RENEW_BASE_OVERAGE.equals(curBSMsg.contrAutoRnwTpCd_B.getValue())
                    || CONTR_AUTO_RNW_TP.AUTO_RENEW_BASE_ONLY.equals(curBSMsg.contrAutoRnwTpCd_B.getValue())
                    || CONTR_AUTO_RNW_TP.MANUAL_RENEW_BASE_OVERAGE.equals(curBSMsg.contrAutoRnwTpCd_B.getValue())
                    || CONTR_AUTO_RNW_TP.MANUAL_RENEW_BASE_ONLY.equals(curBSMsg.contrAutoRnwTpCd_B.getValue()))
                    && RNW_PRC_METH.MARKUP_PERCENT.equals(curBSMsg.rnwPrcMethCd_B.getValue())) {
                // Check_6:Base%
                // START 2017/11/20 M.Kidokoro [QC#22694, MOD]
//                chkObjCnt++;
//                if (upperBSMsg.basePrcUpRatio_B.getValue().compareTo(curBSMsg.basePrcUpRatio_B.getValue()) == 0) {
//                    ovrlpCnt++;
//                }
                if (ZYPCommonFunc.hasValue(upperBSMsg.basePrcUpRatio_B.getValue()) && ZYPCommonFunc.hasValue(curBSMsg.basePrcUpRatio_B.getValue())) {
                    chkObjCnt++;
                    if (upperBSMsg.basePrcUpRatio_B.getValue().compareTo(curBSMsg.basePrcUpRatio_B.getValue()) == 0) {
                        ovrlpCnt++;
                    }
                }
                // END 2017/11/20 M.Kidokoro [QC#22694, MOD]
            }

            if ((CONTR_AUTO_RNW_TP.AUTO_RENEW_BASE_OVERAGE.equals(curBSMsg.contrAutoRnwTpCd_B.getValue())
                    || CONTR_AUTO_RNW_TP.AUTO_RENEW_OVERAGE_ONLY.equals(curBSMsg.contrAutoRnwTpCd_B.getValue())
                    || CONTR_AUTO_RNW_TP.MANUAL_RENEW_BASE_OVERAGE.equals(curBSMsg.contrAutoRnwTpCd_B.getValue())
                    || CONTR_AUTO_RNW_TP.MANUAL_RENEW_OVERAGE_ONLY.equals(curBSMsg.contrAutoRnwTpCd_B.getValue()))
                    && RNW_PRC_METH.MARKUP_PERCENT.equals(curBSMsg.rnwPrcMethCd_B.getValue())) {
                // Check_7:Ovrg%
                // START 2017/11/20 M.Kidokoro [QC#22694, MOD]
//                chkObjCnt++;
//                if (upperBSMsg.mtrPrcUpRatio_B.getValue().compareTo(curBSMsg.mtrPrcUpRatio_B.getValue()) == 0) {
//                    ovrlpCnt++;
//                }
                if (ZYPCommonFunc.hasValue(upperBSMsg.mtrPrcUpRatio_B.getValue()) && ZYPCommonFunc.hasValue(curBSMsg.mtrPrcUpRatio_B.getValue())) {
                    chkObjCnt++;
                    if (upperBSMsg.mtrPrcUpRatio_B.getValue().compareTo(curBSMsg.mtrPrcUpRatio_B.getValue()) == 0) {
                        ovrlpCnt++;
                    }
                }
                // END 2017/11/20 M.Kidokoro [QC#22694, MOD]
            }
        } else if (NSAL0380Constant.MACH_LVL_NUM_3.equals(getMachLvlNum(curBSMsg))) {
            if (NSAL0380Constant.BASE_NM.equals(curBSMsg.dsContrBaseUsgNm_B.getValue())) {
                if ((CONTR_AUTO_RNW_TP.AUTO_RENEW_BASE_OVERAGE.equals(curBSMsg.contrAutoRnwTpCd_B.getValue())
                        || CONTR_AUTO_RNW_TP.AUTO_RENEW_BASE_ONLY.equals(curBSMsg.contrAutoRnwTpCd_B.getValue())
                        || CONTR_AUTO_RNW_TP.MANUAL_RENEW_BASE_OVERAGE.equals(curBSMsg.contrAutoRnwTpCd_B.getValue())
                        || CONTR_AUTO_RNW_TP.MANUAL_RENEW_BASE_ONLY.equals(curBSMsg.contrAutoRnwTpCd_B.getValue()))
                        && RNW_PRC_METH.MARKUP_PERCENT.equals(curBSMsg.rnwPrcMethCd_B.getValue())) {
                    // Check_3:Base%
                    // START 2017/11/20 M.Kidokoro [QC#22694, MOD]
//                    chkObjCnt++;
//                    if (upperBSMsg.basePrcUpRatio_B.getValue().compareTo(curBSMsg.basePrcUpRatio_B.getValue()) == 0) {
//                        ovrlpCnt++;
//                    }
                    if (ZYPCommonFunc.hasValue(upperBSMsg.basePrcUpRatio_B.getValue()) && ZYPCommonFunc.hasValue(curBSMsg.basePrcUpRatio_B.getValue())) {
                        chkObjCnt++;
                        if (upperBSMsg.basePrcUpRatio_B.getValue().compareTo(curBSMsg.basePrcUpRatio_B.getValue()) == 0) {
                            ovrlpCnt++;
                        }
                    }
                    // END 2017/11/20 M.Kidokoro [QC#22694, MOD]
                    // START 2018/12/20 [QC#29636, ADD]
                    // Check_4:Max%
                    if (ZYPCommonFunc.hasValue(upperBSMsg.maxPrcUpRatio_B.getValue()) && ZYPCommonFunc.hasValue(curBSMsg.maxPrcUpRatio_B.getValue())) {
                        chkObjCnt++;
                        if (upperBSMsg.maxPrcUpRatio_B.getValue().compareTo(curBSMsg.maxPrcUpRatio_B.getValue()) == 0) {
                            ovrlpCnt++;
                        }
                    }
                    // END 2018/12/20 [QC#29636, ADD]
                }
            } else if (NSAL0380Constant.USG_NM.equals(curBSMsg.dsContrBaseUsgNm_B.getValue())) {
                if ((CONTR_AUTO_RNW_TP.AUTO_RENEW_BASE_OVERAGE.equals(curBSMsg.contrAutoRnwTpCd_B.getValue())
                        || CONTR_AUTO_RNW_TP.AUTO_RENEW_OVERAGE_ONLY.equals(curBSMsg.contrAutoRnwTpCd_B.getValue())
                        || CONTR_AUTO_RNW_TP.MANUAL_RENEW_BASE_OVERAGE.equals(curBSMsg.contrAutoRnwTpCd_B.getValue())
                        || CONTR_AUTO_RNW_TP.MANUAL_RENEW_OVERAGE_ONLY.equals(curBSMsg.contrAutoRnwTpCd_B.getValue()))
                        && RNW_PRC_METH.MARKUP_PERCENT.equals(curBSMsg.rnwPrcMethCd_B.getValue())) {
                    // Check_3:Ovrg%
                    // START 2017/11/20 M.Kidokoro [QC#22694, MOD]
//                    chkObjCnt++;
//                    if (upperBSMsg.mtrPrcUpRatio_B.getValue().compareTo(curBSMsg.mtrPrcUpRatio_B.getValue()) == 0) {
//                        ovrlpCnt++;
//                    }
                    if (ZYPCommonFunc.hasValue(upperBSMsg.mtrPrcUpRatio_B.getValue()) && ZYPCommonFunc.hasValue(curBSMsg.mtrPrcUpRatio_B.getValue())) {
                        chkObjCnt++;
                        if (upperBSMsg.mtrPrcUpRatio_B.getValue().compareTo(curBSMsg.mtrPrcUpRatio_B.getValue()) == 0) {
                            ovrlpCnt++;
                        }
                    }
                    // END 2017/11/20 M.Kidokoro [QC#22694, MOD]
                    // START 2018/12/20 [QC#29636, ADD]
                    // Check_4:Max%
                    if (ZYPCommonFunc.hasValue(upperBSMsg.maxPrcUpRatio_B.getValue()) && ZYPCommonFunc.hasValue(curBSMsg.maxPrcUpRatio_B.getValue())) {
                        chkObjCnt++;
                        if (upperBSMsg.maxPrcUpRatio_B.getValue().compareTo(curBSMsg.maxPrcUpRatio_B.getValue()) == 0) {
                            ovrlpCnt++;
                        }
                    }
                    // END 2018/12/20 [QC#29636, ADD]
                }
            }
        }

        if (chkObjCnt == ovrlpCnt) {
            result = false;
        }

        return result;
    }
    // END 2017/11/15 M.Kidokoro [QC#21928-1, ADD]

    // add start 2019/06/26 QC#50931
    private int checkRDaysRequiredBaseUsage(NSAL0380SMsg sMsg) {
        int firstErrIndex = -1;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL0380_BSMsg bsMsg = sMsg.B.no(i);
            if (NSAL0380Constant.MACH_LVL_NUM_1.equals(bsMsg.xxDplyByCtrlAncrLvlCd_B.getValue()) || NSAL0380Constant.MACH_LVL_NUM_2.equals(bsMsg.xxDplyByCtrlAncrLvlCd_B.getValue())) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(bsMsg.autoRnwFlg_B.getValue())) {
                continue;
            }
            if (ZYPConstant.FLG_OFF_N.equals(bsMsg.autoRnwFlg_B.getValue())) {
                continue;
            }
            // START 2023/11/01 R.Tamura [QC#62046, ADD]
            if (DS_CONTR_DTL_STS.TERMINATED.equals(bsMsg.dsContrCtrlStsCd_B2.getValue()) || DS_CONTR_DTL_STS.EXPIRED.equals(bsMsg.dsContrCtrlStsCd_B2.getValue()) || DS_CONTR_DTL_STS.CANCELLED.equals(bsMsg.dsContrCtrlStsCd_B2.getValue())) {
                continue;
            }
            // END 2023/11/01 R.Tamura [QC#62046, ADD]

            boolean err = true;
            int dsContrPk = bsMsg.dsContrPk_B.getValueInt();
            int dsContrDtlPk = bsMsg.dsContrDtlPk_B.getValueInt();
            int errIndex = -1;
            for (int j = 0; j < sMsg.B.getValidCount(); j++) {
                NSAL0380_BSMsg prntBsMsg = sMsg.B.no(j);
                if (prntBsMsg.dsContrPk_B.getValueInt() == dsContrPk && NSAL0380Constant.MACH_LVL_NUM_1.equals(prntBsMsg.xxDplyByCtrlAncrLvlCd_B.getValue())) {
                    errIndex = j;
                    if (ZYPCommonFunc.hasValue(prntBsMsg.befEndRnwDaysAot_B)) {
                        err = false;
                        break;
                    }
                } else if (prntBsMsg.dsContrPk_B.getValueInt() == dsContrPk && NSAL0380Constant.MACH_LVL_NUM_2.equals(prntBsMsg.xxDplyByCtrlAncrLvlCd_B.getValue())) {
                    if (prntBsMsg.dsContrDtlPk_B.getValueInt() != dsContrDtlPk) {
                        continue;
                    }
                    errIndex = j;
                    if (ZYPCommonFunc.hasValue(prntBsMsg.befEndRnwDaysAot_B)) {
                        err = false;
                        break;
                    }
                }
            }
            if (err) {
                if (errIndex != -1) {
                    sMsg.B.no(errIndex).befEndRnwDaysAot_B.setErrorInfo(1, NSAL0380Constant.NSAM0330E);
                    if (firstErrIndex == -1) {
                        firstErrIndex = errIndex;
                    }
                }
                sMsg.B.no(i).contrAutoRnwTpCd_B.setErrorInfo(1, NSAL0380Constant.NSAM0330E);
                if (firstErrIndex == -1) {
                    firstErrIndex = i;
                }
            }
        }
        return firstErrIndex;
    }

    private void copySMsgToCMsgForError(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg, int errIndex) {
        // expand the error line
        NSAL0380_BSMsg bsMsg = sMsg.B.no(errIndex);
        String machLvlNum = bsMsg.xxDplyByCtrlAncrLvlCd_B.getValue();
        BigDecimal dsContrPk = bsMsg.dsContrPk_B.getValue();
        BigDecimal dsContrDtlPk = bsMsg.dsContrDtlPk_B.getValue();

        if (!NSAL0380Constant.MACH_LVL_NUM_1.equals(machLvlNum) || !NSAL0380Constant.MACH_LVL_NUM_2.equals(machLvlNum)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.xxSmryLineFlg_B, ZYPConstant.FLG_ON_Y);
        }
        if (!NSAL0380Constant.MACH_LVL_NUM_1.equals(machLvlNum)) {
            for (int i = 0; i < sMsg.B.getValidCount(); i++) {
                bsMsg = sMsg.B.no(i);
                if (NSAL0380Constant.MACH_LVL_NUM_1.equals(bsMsg.xxDplyByCtrlAncrLvlCd_B.getValue()) && dsContrPk.compareTo(bsMsg.dsContrPk_B.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(bsMsg.xxSmryLineFlg_B, ZYPConstant.FLG_ON_Y);
                    break;
                }
            }
        }
        if (NSAL0380Constant.MACH_LVL_NUM_3.equals(machLvlNum) || NSAL0380Constant.MACH_LVL_NUM_4.equals(machLvlNum)) {
            for (int i = 0; i < sMsg.B.getValidCount(); i++) {
                bsMsg = sMsg.B.no(i);
                if (NSAL0380Constant.MACH_LVL_NUM_2.equals(bsMsg.xxDplyByCtrlAncrLvlCd_B.getValue()) && dsContrPk.compareTo(bsMsg.dsContrPk_B.getValue()) == 0 && dsContrDtlPk.compareTo(bsMsg.dsContrDtlPk_B.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(bsMsg.xxSmryLineFlg_B, ZYPConstant.FLG_ON_Y);
                    break;
                }
            }
        }

        // copy NSAL0380_BSMsg -> NSAL0380_ASMsg
        NSAL0380CommonLogic.copyToASMsgForExpansionAndContraction(sMsg);

        // set fromNum
        BigDecimal len = BigDecimal.valueOf(cMsg.A.length());
        BigDecimal fromNum = BigDecimal.ONE;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (sMsg.A.no(i).xxNum_A.getValueInt() == errIndex) {
                fromNum = BigDecimal.valueOf(i).divide(len, 0, BigDecimal.ROUND_DOWN).multiply(len).add(BigDecimal.ONE);
                break;
            }
        }
        cMsg.xxPageShowFromNum.setValue(fromNum);

        // copy NSAL0380_ASMsg -> NSAL0380_ACMsg
        NSAL0380CommonLogic.copyToACMsgForDisplay(cMsg, sMsg);

        // set Paging Data
        cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - 1 + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }
    // add end 2019/06/26 QC#50931
}
