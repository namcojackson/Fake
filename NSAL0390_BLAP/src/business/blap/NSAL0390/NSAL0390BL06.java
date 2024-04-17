/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0390;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static business.blap.NSAL0390.constant.NSAL0390Constant.*;
import static business.blap.NSAL0390.common.NSAL0390CommonLogic.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDConnectionMgr;
import business.db.CR_CARD_TRXTMsg;
import business.db.DS_CONTR_CR_CARD_POTMsg;
import business.db.SVC_MEMOTMsg;
import business.parts.NWZC203001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/23   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/18   Hitachi         A.Kohinata      Update          QC#4212
 * 2016/06/10   Hitachi         T.Kanasaka      Update          QC#9708
 * 2016/08/29   Hitachi         A.Kohinata      Update          QC#11243
 * 2019/01/09   Hitachi         K.Morita        Update          QC#26928
 *</pre>
 */
public class NSAL0390BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0390Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0390Scrn00_CMN_Submit((NSAL0390CMsg) cMsg, (NSAL0390SMsg) sMsg);
            // add start 2016/08/29 CSA Defect#11243
            } else if ("NSAL0390_NWAL2010".equals(screenAplID)) {
                doProcess_NSAL0390Scrn00_NWAL2010((NSAL0390CMsg) cMsg, (NSAL0390SMsg) sMsg);
            // add end 2016/08/29 CSA Defect#11243
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Submit)
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    private void doProcess_NSAL0390Scrn00_CMN_Submit(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        copyCurrentPageToBSMsg(cMsg, sMsg);

        if (!checkSelectLine(cMsg, sMsg)) {
            return;
        }

        // mod start 2016/08/29 CSA Defect#11243
        int firstErrIndex = -1;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL0390_BSMsg bsMsg = sMsg.B.no(i);
            if (ZYPConstant.CHKBOX_ON_Y.equals(bsMsg.xxChkBox_B0.getValue()) || ZYPConstant.CHKBOX_ON_Y.equals(bsMsg.xxChkBox_B1.getValue())) {
                //if (!hasValue(bsMsg.crCardCustRefNum_B1)) {
                //    cMsg.setMessageInfo(NSAM0315E, new String[] {"New Reference#" });
                //    return;
                //}
                if (!checkNewRefNum(cMsg, bsMsg)) {
                    if (firstErrIndex == -1) {
                        firstErrIndex = i;
                    }
                }
            }
        }

        if (firstErrIndex == -1) {
            doInsOrUpd(cMsg, sMsg);
            copyFromBSMsgToASMsgForPaging(sMsg);
        } else {
            copyFromBSMsgToASMsgForError(cMsg, sMsg, firstErrIndex);
        }
        cMsg.setCommitSMsg(true);
        // mod end 2016/08/29 CSA Defect#11243

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        copyFromASMsgToACMsg(cMsg, sMsg, pageFrom);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    // add start 2016/08/29 CSA Defect#11243
    /**
     * do process (NWAL2010)
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    private void doProcess_NSAL0390Scrn00_NWAL2010(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        String scrEventNm = cMsg.xxScrEventNm.getValue();
        boolean resultNormal = true;
        NSAL0390_BSMsg bsMsg = null;
        int index = 0;

        if ("OpenWin_CreditCard".equals(scrEventNm)) {
            if (sMsg.B.getValidCount() > 0) {
                bsMsg = sMsg.B.no(index);
            }
        } else if ("Open_CC".equals(scrEventNm)) {
            NSAL0390_ACMsg acMsg = cMsg.A.no(cMsg.xxNum_EV.getValueInt());
            index = acMsg.xxNum_A0.getValueInt();
            bsMsg = sMsg.B.no(index);
        }
        if (bsMsg == null) {
            return;
        }

        Map<String, Object> diffDataMap = NSAL0390Query.getInstance().getDiffData(cMsg, bsMsg);
        if (diffDataMap == null || diffDataMap.isEmpty()) {
            return;
        }

        copyCurrentPageToBSMsg(cMsg, sMsg);

        setValue(bsMsg.crCardCustRefNum_B1, (String) diffDataMap.get("CR_CARD_CUST_REF_NUM"));
        setValue(bsMsg.crCardExprYrMth_B1, (String) diffDataMap.get("CR_CARD_EXPR_YR_MTH"));

        if (!hasValue(bsMsg.dsContrCrCardPoPk_B0)) {
            resultNormal = insertDsContrCrCardPo(cMsg, bsMsg);
        } else {
            resultNormal = updateDsContrCrCardPo(cMsg, bsMsg);
        }

        if (resultNormal) {
            setValue(bsMsg.crCardCustRefNum_B0, bsMsg.crCardCustRefNum_B1);
            setValue(bsMsg.crCardExprYrMth_B0, bsMsg.crCardExprYrMth_B1);
        }
        bsMsg.crCardCustRefNum_B1.clear();
        bsMsg.crCardExprYrMth_B1.clear();

        String dsContrMachLvlNum = bsMsg.dsContrMachLvlNum_B0.getValue();
        BigDecimal dsContrPk = bsMsg.dsContrPk_B0.getValue();
        BigDecimal dsContrDtlPk = bsMsg.dsContrDtlPk_B0.getValue();
        String crCardCustRefNum = bsMsg.crCardCustRefNum_B0.getValue();
        String crCardExprYrMth = bsMsg.crCardExprYrMth_B0.getValue();
        boolean skipFlg = false;

        index++;
        for (; index < sMsg.B.getValidCount(); index++) {
            bsMsg = sMsg.B.no(index);
            if (MACH_LVL_NUM_1.equals(dsContrMachLvlNum)) {
                if (dsContrPk.compareTo(bsMsg.dsContrPk_B0.getValue()) != 0) {
                    break;
                }
            }
            if (MACH_LVL_NUM_2.equals(dsContrMachLvlNum)) {
                if (!hasValue(bsMsg.dsContrDtlPk_B0) || dsContrDtlPk.compareTo(bsMsg.dsContrDtlPk_B0.getValue()) != 0) {
                    break;
                }
            }
            if (MACH_LVL_NUM_3.equals(dsContrMachLvlNum)) {
                break;
            }
            if (MACH_LVL_NUM_2.equals(bsMsg.dsContrMachLvlNum_B0.getValue())) {
                if (hasValue(bsMsg.dsContrCrCardPoPk_B0)) {
                    skipFlg = true;
                } else {
                    skipFlg = false;
                }
            }
            if (!skipFlg && !hasValue(bsMsg.dsContrCrCardPoPk_B0)) {
                setValue(bsMsg.crCardCustRefNum_B0, crCardCustRefNum);
                setValue(bsMsg.crCardExprYrMth_B0, crCardExprYrMth);
            }
        }

        copyFromBSMsgToASMsgForPaging(sMsg);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        copyFromASMsgToACMsg(cMsg, sMsg, pageFrom);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }
    // add end 2016/08/29 CSA Defect#11243

    /**
     * do Insert or Update
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    private void doInsOrUpd(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        boolean resultNormal = true;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL0390_BSMsg bsMsg = sMsg.B.no(i);
            bsMsg.xxDsMsgEntryTxt_B0.clear();

            if (ZYPConstant.CHKBOX_ON_Y.equals(bsMsg.xxChkBox_B0.getValue()) || ZYPConstant.CHKBOX_ON_Y.equals(bsMsg.xxChkBox_B1.getValue())) {
                // mod start 2016/08/29 CSA Defect#11243
                if (hasValue(bsMsg.crCardCustRefNum_B1)) {
                    if (!hasValue(bsMsg.dsContrCrCardPoPk_B0.getValue())) {
                        resultNormal = insertDsContrCrCardPo(cMsg, bsMsg);
                    } else {
                        resultNormal = updateDsContrCrCardPo(cMsg, bsMsg);
                    }
                    if (resultNormal) {
                        resultNormal = insOrUpdCrCardTrx(cMsg, bsMsg);
                    }
                } else {
                    resultNormal = deleteDsContrCrCardPo(cMsg, bsMsg);
                    if (resultNormal) {
                        resultNormal = voidCrCardTrx(cMsg, bsMsg);
                    }
                }
                // mod end 2016/08/29 CSA Defect#11243

                if (resultNormal) {
                    resultNormal = insertSvcMemo(cMsg, bsMsg);
                }
                if (resultNormal) {
                    EZDConnectionMgr.getInstance().commit();
                    setValue(bsMsg.xxDsMsgEntryTxt_B0, RTRN_MSG_SUCCESS);
                } else {
                    EZDConnectionMgr.getInstance().rollback();
                    setValue(bsMsg.xxDsMsgEntryTxt_B0, RTRN_MSG_FAILED);
                }
            }
        }

        if (resultNormal) {
            cMsg.setMessageInfo(NSAM0200I);
        } else {
            cMsg.setMessageInfo(NSAM0394W);
        }
    }

    /**
     * Insert DS_CONTR_CR_CARD_PO
     * @param cMsg NSAL0390CMsg
     * @param bsMsg NSAL0390_BSMsg
     * @return No Exclusion Error : true
     */
    private boolean insertDsContrCrCardPo(NSAL0390CMsg cMsg, NSAL0390_BSMsg bsMsg) {

        String machLvlNum = bsMsg.dsContrMachLvlNum_B0.getValue();

        // check exclusion
        if (!checkInsExclusion(cMsg, bsMsg, machLvlNum)) {
            return false;
        }

        DS_CONTR_CR_CARD_POTMsg insTMsg = new DS_CONTR_CR_CARD_POTMsg();
        setValue(insTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(insTMsg.dsContrCrCardPoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ));
        setValue(insTMsg.dsContrPk, bsMsg.dsContrPk_B0);
        // START 2019/01/09 K.Morita [QC#26928, MOD]
        setValue(insTMsg.crCardFlg, ZYPConstant.FLG_ON_Y);
        // END 2019/01/09 K.Morita [QC#26928, MOD]
        if (!MACH_LVL_NUM_1.equals(machLvlNum)) {
            setValue(insTMsg.dsContrDtlPk, bsMsg.dsContrDtlPk_B0);
        }

        if (MACH_LVL_NUM_3.equals(machLvlNum) && TRD_TP_BLLG_MTR.equals(bsMsg.xxDtlNm_B0.getValue())) {
            setValue(insTMsg.dsContrBllgMtrPk, bsMsg.dsContrBllgMtrPk_B0);
        }

        setValue(insTMsg.crCardCustRefNum, bsMsg.crCardCustRefNum_B1);
        setValue(insTMsg.crCardExprYrMth, bsMsg.crCardExprYrMth_B1);
        setValue(insTMsg.leaseCmpyFlg, getLeaseCmpyFlg(bsMsg.leaseCmpyFlg_B0.getValue()));
        setValue(insTMsg.dsContrMachLvlNum, machLvlNum);

        S21FastTBLAccessor.insert(insTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insTMsg.getReturnCode())) {
            return false;
        }
        setValue(bsMsg.dsContrCrCardPoPk_B0, insTMsg.dsContrCrCardPoPk);
        // add start 2016/08/29 CSA Defect#11243
        setValue(bsMsg.ezUpTimeZone_B0, insTMsg.ezUpTimeZone);
        setValue(bsMsg.ezUpTime_B0, insTMsg.ezUpTime);
        // add end 2016/08/29 CSA Defect#11243
        return true;
    }

    /**
     * Update DS_CONTR_CR_CARD_PO
     * @param cMsg NSAL0390CMsg
     * @param bsMsg NSAL0390_BSMsg
     * @return No Exclusion Error : true
     */
    private boolean updateDsContrCrCardPo(NSAL0390CMsg cMsg, NSAL0390_BSMsg bsMsg) {

        DS_CONTR_CR_CARD_POTMsg lockTMsg = new DS_CONTR_CR_CARD_POTMsg();
        setValue(lockTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(lockTMsg.dsContrCrCardPoPk, bsMsg.dsContrCrCardPoPk_B0);
        DS_CONTR_CR_CARD_POTMsg updTMsg = (DS_CONTR_CR_CARD_POTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(lockTMsg);

        if (updTMsg == null) {
            return insertDsContrCrCardPo(cMsg, bsMsg);
        }
        // check exclusion
        String findEzUpTime = bsMsg.ezUpTime_B0.getValue();
        String findEzUpTimeZone = bsMsg.ezUpTimeZone_B0.getValue();
        String currentEzUpTime = updTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = updTMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            return false;
        }

        setValue(updTMsg.crCardCustRefNum, bsMsg.crCardCustRefNum_B1);
        setValue(updTMsg.crCardExprYrMth, bsMsg.crCardExprYrMth_B1);
        setValue(updTMsg.leaseCmpyFlg, getLeaseCmpyFlg(bsMsg.leaseCmpyFlg_B0.getValue()));
        // START 2019/01/09 K.Morita [QC#26928, MOD]
        setValue(updTMsg.crCardFlg, ZYPConstant.FLG_ON_Y);
        // END 2019/01/09 K.Morita [QC#26928, MOD]
        S21FastTBLAccessor.update(updTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            return false;
        }
        // add start 2016/08/29 CSA Defect#11243
        setValue(bsMsg.ezUpTimeZone_B0, updTMsg.ezUpTimeZone);
        setValue(bsMsg.ezUpTime_B0, updTMsg.ezUpTime);
        // add end 2016/08/29 CSA Defect#11243
        return true;
    }

    // add start 2016/08/29 CSA Defect#11243
    /**
     * Delete DS_CONTR_CR_CARD_PO
     * @param cMsg NSAL0390CMsg
     * @param bsMsg NSAL0390_BSMsg
     * @return No Exclusion Error : true
     */
    private boolean deleteDsContrCrCardPo(NSAL0390CMsg cMsg, NSAL0390_BSMsg bsMsg) {

        DS_CONTR_CR_CARD_POTMsg lockTMsg = new DS_CONTR_CR_CARD_POTMsg();
        setValue(lockTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(lockTMsg.dsContrCrCardPoPk, bsMsg.dsContrCrCardPoPk_B0);
        DS_CONTR_CR_CARD_POTMsg updTMsg = (DS_CONTR_CR_CARD_POTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(lockTMsg);

        if (updTMsg == null) {
            return true;
        }
        // check exclusion
        String findEzUpTime = bsMsg.ezUpTime_B0.getValue();
        String findEzUpTimeZone = bsMsg.ezUpTimeZone_B0.getValue();
        String currentEzUpTime = updTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = updTMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            return false;
        }

        updTMsg.crCardCustRefNum.clear();
        updTMsg.crCardExprYrMth.clear();

        S21FastTBLAccessor.update(updTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            return false;
        }
        setValue(bsMsg.ezUpTimeZone_B0, updTMsg.ezUpTimeZone);
        setValue(bsMsg.ezUpTime_B0, updTMsg.ezUpTime);
        return true;
    }

    /**
     * Insert or Update CR_CARD_TR
     * @param cMsg NSAL0390CMsg
     * @param bsMsg NSAL0390_BSMsg
     * @return No Exclusion Error : true
     */
    private static boolean insOrUpdCrCardTrx(NSAL0390CMsg cMsg, NSAL0390_BSMsg bsMsg) {
        // get CrCardTrx
        List<BigDecimal> crCardTrxPkList = NSAL0390Query.getInstance().getCrCardTrxPk(cMsg, bsMsg);

        String slsDt = ZYPDateUtil.getSalesDate();
        if (crCardTrxPkList == null || crCardTrxPkList.size() == 0) {
            // insert CrCardTrx
            NWZC203001PMsg apiPMsg = new NWZC203001PMsg();
            setValue(apiPMsg.glblCmpyCd, cMsg.glblCmpyCd);
            setValue(apiPMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_TRX);
            setValue(apiPMsg.slsDt, slsDt);
            setValue(apiPMsg.crCardCustRefNum, bsMsg.crCardCustRefNum_B1);
            setValue(apiPMsg.sellToCustCd, bsMsg.sellToCustCd_B0);
            setValue(apiPMsg.crCardTrxTpCd, getCrCardTrxTp(bsMsg.dsContrMachLvlNum_B0.getValue(), bsMsg.xxDtlNm_B0.getValue()));
            setValue(apiPMsg.firstTrxInfoNum, bsMsg.dsContrPk_B0);
            setValue(apiPMsg.scdTrxInfoNum, bsMsg.dsContrDtlPk_B0);
            setValue(apiPMsg.thirdTrxInfoNum, bsMsg.dsContrBllgMtrPk_B0);
            setValue(apiPMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.SAVED);

            NWZC203001 api = new NWZC203001();
            api.execute(apiPMsg, ONBATCH_TYPE.ONLINE);
            if (S21ApiUtil.isXxMsgId(apiPMsg)) {
                bsMsg.crCardCustRefNum_B1.setErrorInfo(1, apiPMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                return false;
            }
        } else {
            // update CrCardTrx
            for (BigDecimal crCardTrxPk : crCardTrxPkList) {
                CR_CARD_TRXTMsg tMsg = NSAL0390Query.getInstance().getCrCardTrx(cMsg.glblCmpyCd.getValue(), crCardTrxPk);
                if (tMsg == null) {
                    continue;
                }
                NWZC203001PMsg apiPMsg = new NWZC203001PMsg();
                EZDMsg.copy(tMsg, null, apiPMsg, null);
                setValue(apiPMsg.glblCmpyCd, cMsg.glblCmpyCd);
                setValue(apiPMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_TRX);
                setValue(apiPMsg.slsDt, slsDt);
                setValue(apiPMsg.crCardCustRefNum, bsMsg.crCardCustRefNum_B1);
                setValue(apiPMsg.sellToCustCd, bsMsg.sellToCustCd_B0);
                setValue(apiPMsg.crCardTrxTpCd, getCrCardTrxTp(bsMsg.dsContrMachLvlNum_B0.getValue(), bsMsg.xxDtlNm_B0.getValue()));
                setValue(apiPMsg.firstTrxInfoNum, bsMsg.dsContrPk_B0);
                setValue(apiPMsg.scdTrxInfoNum, bsMsg.dsContrDtlPk_B0);
                setValue(apiPMsg.thirdTrxInfoNum, bsMsg.dsContrBllgMtrPk_B0);
                setValue(apiPMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.SAVED);
                setValue(apiPMsg.xxPmtcProcStsCd, tMsg.crCardTrxProcStsCd);
                setValue(apiPMsg.xxPmtcApvlStsNum, tMsg.crCardTrxApvlStsCd);
                setValue(apiPMsg.xxPmtcTrxRefIdxCd, tMsg.crCardRefIdxNum);

                NWZC203001 api = new NWZC203001();
                api.execute(apiPMsg, ONBATCH_TYPE.ONLINE);
                if (S21ApiUtil.isXxMsgId(apiPMsg)) {
                    bsMsg.crCardCustRefNum_B1.setErrorInfo(1, apiPMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Void CR_CARD_TR
     * @param cMsg NSAL0390CMsg
     * @param bsMsg NSAL0390_BSMsg
     * @return No Exclusion Error : true
     */
    private static boolean voidCrCardTrx(NSAL0390CMsg cMsg, NSAL0390_BSMsg bsMsg) {
        // get CrCardTrx
        List<BigDecimal> crCardTrxPkList = NSAL0390Query.getInstance().getCrCardTrxPk(cMsg, bsMsg);
        if (crCardTrxPkList == null || crCardTrxPkList.size() == 0) {
            return true;
        }

        // cancel CrCardTrx
        String slsDt = ZYPDateUtil.getSalesDate();
        for (BigDecimal crCardTrxPk : crCardTrxPkList) {
            CR_CARD_TRXTMsg tMsg = NSAL0390Query.getInstance().getCrCardTrx(cMsg.glblCmpyCd.getValue(), crCardTrxPk);
            if (tMsg == null) {
                continue;
            }
            NWZC203001PMsg apiPMsg = new NWZC203001PMsg();
            EZDMsg.copy(tMsg, null, apiPMsg, null);
            setValue(apiPMsg.glblCmpyCd, cMsg.glblCmpyCd);
            setValue(apiPMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_TRX);
            setValue(apiPMsg.slsDt, slsDt);
            setValue(apiPMsg.sellToCustCd, tMsg.billToCustAcctCd);
            setValue(apiPMsg.crCardTrxTpCd, getCrCardTrxTp(bsMsg.dsContrMachLvlNum_B0.getValue(), bsMsg.xxDtlNm_B0.getValue()));
            setValue(apiPMsg.firstTrxInfoNum, bsMsg.dsContrPk_B0);
            setValue(apiPMsg.scdTrxInfoNum, bsMsg.dsContrDtlPk_B0);
            setValue(apiPMsg.thirdTrxInfoNum, bsMsg.dsContrBllgMtrPk_B0);
            setValue(apiPMsg.crCardCancDt, slsDt);
            setValue(apiPMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.VOID_COMPLETED);
            setValue(apiPMsg.xxPmtcProcStsCd, tMsg.crCardTrxProcStsCd);
            setValue(apiPMsg.xxPmtcApvlStsNum, tMsg.crCardTrxApvlStsCd);
            setValue(apiPMsg.xxPmtcTrxRefIdxCd, tMsg.crCardRefIdxNum);

            NWZC203001 api = new NWZC203001();
            api.execute(apiPMsg, ONBATCH_TYPE.ONLINE);
            if (S21ApiUtil.isXxMsgId(apiPMsg)) {
                bsMsg.crCardCustRefNum_B1.setErrorInfo(1, apiPMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                return false;
            }
        }
        return true;
    }
    // add end 2016/08/29 CSA Defect#11243

    /**
     * Insert SVC_MEMO
     * @param cMsg NSAL0390CMsg
     * @param bsMsg NSAL0390_BSMsg
     * @return No Exclusion Error : true
     */
    private boolean insertSvcMemo(NSAL0390CMsg cMsg, NSAL0390_BSMsg bsMsg) {

        SVC_MEMOTMsg tMsg = new SVC_MEMOTMsg();
        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(tMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(tMsg.svcMemoTpCd, SVC_MEMO_TP.UPDATE_CREDIT_CARD_INFO);
        setValue(tMsg.svcCmntTxt, cMsg.svcCmntTxt_H);
        setValue(tMsg.dsContrPk, bsMsg.dsContrPk_B0);
        setValue(tMsg.lastUpdUsrId, cMsg.getUserID());
        setValue(tMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS));
        setValue(tMsg.svcMemoRsnCd, cMsg.svcMemoRsnCd_H);
        // mod start 2016/08/29 CSA Defect#11243
        if (hasValue(bsMsg.dsContrCrCardPoPk_B0)) {
            setValue(tMsg.svcMemoTrxNum, bsMsg.dsContrCrCardPoPk_B0.getValue().toString());
        }
        // mod end 2016/08/29 CSA Defect#11243
        setValue(tMsg.svcMemoTrxNm, DS_CONTR_CR_CARD_PO_PK);
        S21FastTBLAccessor.insert(tMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * get Leasing Company Flag
     * @param leaseCmpyFlg Leasing Company Flag
     * @return Leasing Company Flag
     */
    private String getLeaseCmpyFlg(String leaseCmpyFlg) {

        if (!hasValue(leaseCmpyFlg)) {
            return ZYPConstant.FLG_OFF_N;
        }
        return leaseCmpyFlg;
    }

    /**
     * check Insert Exclusion
     * @param cMsg NSAL0390CMsg
     * @param bsMsg NSAL0390_BSMsg
     * @param machLvlNum Machine Level Number
     * @return No Exclusion Error : true
     */
    private boolean checkInsExclusion(NSAL0390CMsg cMsg, NSAL0390_BSMsg bsMsg, String machLvlNum) {

        if (MACH_LVL_NUM_1.equals(machLvlNum)) {
            if (NSAL0390Query.getInstance().isExistFstData(cMsg, bsMsg)) {
                return false;
            }
        } else if (MACH_LVL_NUM_2.equals(machLvlNum)) {
            if (NSAL0390Query.getInstance().isExistScdData(cMsg, bsMsg)) {
                return false;
            }
        } else if (MACH_LVL_NUM_3.equals(machLvlNum)) {
            if (TRD_TP_CONTR_DTL.equals(bsMsg.xxDtlNm_B0.getValue())) {
                if (NSAL0390Query.getInstance().isExistTrdDataOfContrDtl(cMsg, bsMsg)) {
                    return false;
                }
            } else if (TRD_TP_BLLG_MTR.equals(bsMsg.xxDtlNm_B0.getValue())) {
                if (NSAL0390Query.getInstance().isExistTrdDataOfBllgMtr(cMsg, bsMsg)) {
                    return false;
                }
            }
        }

        return true;
    }
}
