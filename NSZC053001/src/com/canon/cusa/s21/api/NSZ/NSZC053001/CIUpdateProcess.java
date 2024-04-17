/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC053001;


import static com.canon.cusa.s21.api.NSZ.NSZC053001.constant.NSZC053001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_CR_REBILTMsg;
import business.db.SVC_CR_REBIL_BASE_BLLGTMsg;
import business.db.SVC_CR_REBIL_DTLTMsg;
import business.db.SVC_CR_REBIL_MTR_READTMsg;
import business.db.SVC_CR_REBIL_XS_MTR_BLLGTMsg;
import business.parts.NSZC053001PMsg;
import business.parts.NSZC053002_xxBaseChangesListPMsg;
import business.parts.NSZC053002_xxMeterReadChangesListPMsg;
import business.parts.NSZC053002_xxPriceChangesListPMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.AddlChrgFromThruDtInfo;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_INV_TP;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Credit Rebill Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/01/2016   Hitachi         T.Aoyagi        Create          N/A
 * 03/24/2016   Hitachi         T.Aoyagi        Update          QC#5901
 * 2016/04/25   Hitachi         T.Kanasaka      Update          QC#7056
 * 2016/04/27   Hitachi         K.Yamada        Update          QC#7056
 * 05/09/2016   Hitachi         T.Aoyagi        Update          QC#7618
 * 2017/10/13   Hitachi         E.Kameishi      Update          QC#18636
 * 2018/03/27   CITS            M.Naito         Update          QC#18672
 * 2018/07/18   Hitachi         K.Kojima        Update          QC#26791
 * 2018/08/27   Hitachi         K.Kishimoto     Update          QC#24555
 * 2022/04/04   CITS            E.Sanchez       Update          QC#59914
 * 2022/04/29   CITS            E.Sanchez       Update          QC#59914-1
 * 2022/05/27   Hitachi         K.Kitachi       Update          QC#60121
 *</pre>
 */
public class CIUpdateProcess implements ZYPConstant {

    /** onBatchTp */
    private ONBATCH_TYPE onBatchTp;

    /** NSZC0530Query */
    private NSZC0530Query query = NSZC0530Query.getInstance();

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        this.onBatchTp = onBatchType;
        cIUpdateProcess(msgMap);
    }

    private void cIUpdateProcess(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal svcCrRebilPk = pMsg.svcCrRebilPk.getValue();

        // Get SVC_CR_REBIL
        SVC_CR_REBILTMsg crRebilTMsg = query.getSvcCrRebilTMsg(msgMap);
        if (crRebilTMsg == null) {
            msgMap.addXxMsgId(NSZM0890E);
            return;
        }

        List<String> svcInvNumList = NSZC053001CommonLogic.getSvcInvNumListFromPMsg(msgMap);
        for (String svcInvNum : svcInvNumList) {

            BigDecimal svcCrRebilDtlPk = NSZC053001CommonLogic.getCrRebilDtlPkFromPMsg(msgMap, svcInvNum);
            if (!hasValue(svcCrRebilDtlPk)) {
                // Get SVC_CR_REBIL_DTL
                SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg = query.getSvcCrRebilDtlTMsg(glblCmpyCd, svcCrRebilPk, svcInvNum);
                if (crRebilDtlTMsg != null) {
                    svcCrRebilDtlPk = crRebilDtlTMsg.svcCrRebilDtlPk.getValue();
                }
            }

            List<SVC_CR_REBIL_MTR_READTMsg> fixMtrReadList = new ArrayList<SVC_CR_REBIL_MTR_READTMsg>();

            if (!hasValue(svcCrRebilDtlPk)) {

                // ----------------------------------------
                // Create SVC_CR_REBIL Info
                // ----------------------------------------
                NSZC053001CommonLogic.createCrRebilInfo(msgMap, svcInvNum, crRebilTMsg, fixMtrReadList);

                // START 2018/07/18 K.Kojima [QC#26791,ADD]
                NSZC053001CommonLogic.adjustMtrFmla(msgMap, crRebilTMsg.svcCrRebilPk.getValue(), svcInvNum, fixMtrReadList, true);
                // END 2018/07/18 K.Kojima [QC#26791,ADD]

            } else {
                // ----------------------------------------
                // Update SVC_CR_REBIL_BASE_BLLG
                // ----------------------------------------
                List<NSZC053002_xxBaseChangesListPMsg> baseInfoList = NSZC053001CommonLogic.getBaseInfoFromPMsg(msgMap, svcInvNum);
                for (NSZC053002_xxBaseChangesListPMsg baseInfo : baseInfoList) {

                    String baseBllgFromDt = baseInfo.baseBllgFromDt.getValue();
                    String serNum = baseInfo.serNum.getValue();
                    // START 2022/04/04 E.Sanchez [QC#59914,ADD]
                    BigDecimal dsContrDtlPk = baseInfo.dsContrDtlPk.getValue();
                    // END 2022/04/04 E.Sanchez [QC#59914,ADD]
                    // START 2022/04/04 E.Sanchez [QC#59914,MOD]
//                    BigDecimal crRebilBasePk = query.getCrRebilBasePk(msgMap, svcCrRebilDtlPk, baseBllgFromDt, serNum);
                    BigDecimal crRebilBasePk = query.getCrRebilBasePk(msgMap, svcCrRebilDtlPk, baseBllgFromDt, serNum, dsContrDtlPk);
                    // END 2022/04/04 E.Sanchez [QC#59914,MOD]
                    if (crRebilBasePk != null) {
                        updateSvcCrRebilBase(msgMap, crRebilBasePk, baseInfo);

                        // START 2018/03/27 M.Naito [QC#18672, ADD]
                        // ----------------------------------------
                        // Update SVC_CR_REBIL_ADDL_BLLG
                        // ----------------------------------------
                        // START 2022/05/27 K.Kitachi [QC#60121, DEL]
//                        List<Map<String, Object>> svcContrAddlChrgList = query.getCrRebilAddlInfoForUpdate(glblCmpyCd, svcCrRebilPk, svcCrRebilDtlPk, ADDL_CHRG_INV_TP.BASE);
                        // END 2022/05/27 K.Kitachi [QC#60121, DEL]
                        SVC_CR_REBIL_BASE_BLLGTMsg svcCrRebilBaseBllgTMsg = new SVC_CR_REBIL_BASE_BLLGTMsg();
                        setValue(svcCrRebilBaseBllgTMsg.glblCmpyCd, glblCmpyCd);
                        setValue(svcCrRebilBaseBllgTMsg.svcCrRebilBaseBllgPk, crRebilBasePk);
                        svcCrRebilBaseBllgTMsg = (SVC_CR_REBIL_BASE_BLLGTMsg) S21ApiTBLAccessor.findByKey(svcCrRebilBaseBllgTMsg);
                        BigDecimal prntsvcInvLinePk = svcCrRebilBaseBllgTMsg.svcInvLinePk.getValue();
                        if (!hasValue(prntsvcInvLinePk)) {
                            continue;
                        }
                        // START 2022/05/27 K.Kitachi [QC#60121, ADD]
                        List<Map<String, Object>> svcContrAddlChrgList = query.getCrRebilAddlInfoForUpdate(glblCmpyCd, svcCrRebilPk, svcCrRebilDtlPk, ADDL_CHRG_INV_TP.BASE, prntsvcInvLinePk);
                        // END 2022/05/27 K.Kitachi [QC#60121, ADD]
                        for (Map<String, Object> svcContrAddlChrgInfo : svcContrAddlChrgList) {
                            NSZC053001CommonLogic.updateCrRebilAddlBllg(msgMap, svcCrRebilBaseBllgTMsg.svcInvLinePk.getValue(), baseInfo.newBaseDealAmt.getValue(), svcContrAddlChrgInfo);
                        }

                        // Create SVC_CR_REBIL_DTL for Additional Charge(Invoice not exist)
                        List<Map<String, Object>> addlChrgNotExistSvcInvLineList = query.getAddlChrgNotExistSvcInvLine(pMsg.glblCmpyCd.getValue(), svcCrRebilBaseBllgTMsg.svcInvLinePk.getValue(), ADDL_CHRG_INV_TP.BASE);
                        boolean createAddlBllgFlg = true;
                        BigDecimal newSvcCrRebilDtlPk = null;
                        // Create SVC_CR_REBIL_ADDL_BLLG (Invoice not exist)
                        for (Map<String, Object> addlChrgNotExistSvcInvLineMap : addlChrgNotExistSvcInvLineList) {
                            Map<String, Object> bllgSchdMap = query.getBllgSchdInfoForCalcAddlChrg(glblCmpyCd, svcCrRebilBaseBllgTMsg.svcInvLinePk.getValue());
                            AddlChrgFromThruDtInfo addlChrgFromThruDtInfo = NSZC053001CommonLogic.getAddlChrgFromThruDtInfo(glblCmpyCd, bllgSchdMap, addlChrgNotExistSvcInvLineMap);
                            if (addlChrgFromThruDtInfo == null) {
                                continue;
                            }
                            if (createAddlBllgFlg) {
                                // Create SVC_CR_REBIL_DTL for Additional Charge(Invoice not exist)
                                Map<String, Object> crRebilDtlForAddlChrgMap = addlChrgNotExistSvcInvLineList.get(0);
                                newSvcCrRebilDtlPk = NSZC053001CommonLogic.createCrRebilDtlForAddlChrg(msgMap, svcCrRebilPk, svcInvNum, crRebilDtlForAddlChrgMap, svcCrRebilBaseBllgTMsg.svcCrRebilDtlPk.getValue());
                            }
                            BigDecimal newAddlChrgDealAmt = NSZC053001CommonLogic.getNewAddlChrgDealAmt(glblCmpyCd, addlChrgNotExistSvcInvLineMap, bllgSchdMap, addlChrgFromThruDtInfo, baseInfo.newBaseDealAmt.getValue());
                            NSZC053001CommonLogic.createCrRebilAddlBllg(msgMap, newSvcCrRebilDtlPk, addlChrgNotExistSvcInvLineMap, newAddlChrgDealAmt);
                            createAddlBllgFlg = false;
                        }
                        // END 2018/03/27 M.Naito [QC#18672, ADD]
                    }
                }

                // ----------------------------------------
                // Update SVC_CR_REBIL_XS_MTR_BLLG
                // ----------------------------------------
                List<NSZC053002_xxPriceChangesListPMsg> xsMtrInfoList = NSZC053001CommonLogic.getXsInfoFromPMsg(msgMap, svcInvNum);
                for (NSZC053002_xxPriceChangesListPMsg xsMtrInfo : xsMtrInfoList) {

                    String bllgMtrLbCd = xsMtrInfo.bllgMtrLbCd.getValue();
                    BigDecimal oldXsCopyQty = xsMtrInfo.oldXsCopyQty.getValue();
                    String serNum = xsMtrInfo.serNum.getValue();
                    BigDecimal crRebilXsMtrPk = query.getCrRebilXsMtrPk(msgMap, svcCrRebilDtlPk, bllgMtrLbCd, oldXsCopyQty, serNum);
                    if (crRebilXsMtrPk != null) {
                        updateSvcCrRebilXsMtr(msgMap, crRebilXsMtrPk, xsMtrInfo);

                        // START 2018/03/27 M.Naito [QC#18672, ADD]
                        // Create SVC_CR_REBIL_ADDL_BLLG (Invoice not exist)
                        SVC_CR_REBIL_XS_MTR_BLLGTMsg svcCrRebilXsMtrBllgTMsg = new SVC_CR_REBIL_XS_MTR_BLLGTMsg();
                        setValue(svcCrRebilXsMtrBllgTMsg.glblCmpyCd, glblCmpyCd);
                        setValue(svcCrRebilXsMtrBllgTMsg.svcCrRebilXsMtrBllgPk, crRebilXsMtrPk);
                        svcCrRebilXsMtrBllgTMsg = (SVC_CR_REBIL_XS_MTR_BLLGTMsg) S21ApiTBLAccessor.findByKey(svcCrRebilXsMtrBllgTMsg);
                        BigDecimal prntsvcInvLinePk = svcCrRebilXsMtrBllgTMsg.svcInvLinePk.getValue();
                        if (!hasValue(prntsvcInvLinePk)) {
                            continue;
                        }
                        List<Map<String, Object>> addlChrgNotExistSvcInvLineList = query.getAddlChrgNotExistSvcInvLine(pMsg.glblCmpyCd.getValue(), svcCrRebilXsMtrBllgTMsg.svcInvLinePk.getValue(), ADDL_CHRG_INV_TP.USAGE);

                        boolean createAddlBllgFlg = true;
                        BigDecimal newSvcCrRebilDtlPk = null;
                        // Create SVC_CR_REBIL_DTL for Additional Charge(Invoice not exist)
                        for (Map<String, Object> addlChrgNotExistSvcInvLineMap : addlChrgNotExistSvcInvLineList) {
                            Map<String, Object> bllgSchdMap = query.getBllgSchdInfoForCalcAddlChrg(glblCmpyCd, svcCrRebilXsMtrBllgTMsg.svcInvLinePk.getValue());
                            AddlChrgFromThruDtInfo addlChrgFromThruDtInfo = NSZC053001CommonLogic.getAddlChrgFromThruDtInfo(glblCmpyCd, bllgSchdMap, addlChrgNotExistSvcInvLineMap);
                            if (addlChrgFromThruDtInfo == null) {
                                continue;
                            }
                            if (createAddlBllgFlg) {
                                // Create SVC_CR_REBIL_DTL for Additional Charge(Invoice not exist)
                                Map<String, Object> crRebilDtlForAddlChrgMap = addlChrgNotExistSvcInvLineList.get(0);
                                newSvcCrRebilDtlPk = NSZC053001CommonLogic.createCrRebilDtlForAddlChrg(msgMap, svcCrRebilPk, svcInvNum, crRebilDtlForAddlChrgMap, svcCrRebilXsMtrBllgTMsg.svcCrRebilDtlPk.getValue());
                            }
                            NSZC053001CommonLogic.createCrRebilAddlBllg(msgMap, newSvcCrRebilDtlPk, addlChrgNotExistSvcInvLineMap, null);
                            createAddlBllgFlg = false;
                        }
                        // END 2018/03/27 M.Naito [QC#18672, ADD]
                    }
                }

                // ----------------------------------------
                // Update SVC_CR_REBIL_MTR_READ
                // ----------------------------------------
                List<NSZC053002_xxMeterReadChangesListPMsg> mtrReadInfoList = NSZC053001CommonLogic.getMtrReadInfoFromPMsg(msgMap, svcInvNum);
                for (NSZC053002_xxMeterReadChangesListPMsg mtrReadInfo : mtrReadInfoList) {

                    // START 2016/04/25 T.Kanasaka [QC#7056, MOD]
                    String physMtrLbCd = mtrReadInfo.physMtrLbCd.getValue();
                    String serNum = mtrReadInfo.serNum.getValue();
                    BigDecimal crRebilMtrReadPk = query.getCrRebilMtrReadPk(msgMap, svcCrRebilDtlPk, physMtrLbCd, serNum);
                    // START 2016/04/25 T.Kanasaka [QC#7056, MOD]
                    if (crRebilMtrReadPk != null) {
                        SVC_CR_REBIL_MTR_READTMsg crRebilMtrReadTMsg = updateSvcCrRebilMtrRead(msgMap, crRebilMtrReadPk, mtrReadInfo);
                        fixMtrReadList.add(crRebilMtrReadTMsg);
                    }
                }

                // START 2018/07/18 K.Kojima [QC#26791,ADD]
                if (mtrReadInfoList.size() > 0) {
                    NSZC053001CommonLogic.adjustMtrFmla(msgMap, crRebilTMsg.svcCrRebilPk.getValue(), svcInvNum, fixMtrReadList, true);
                }
                // END 2018/07/18 K.Kojima [QC#26791,ADD]

                // add start 2016/04/27 CSA Defect#7056
                NSZC053001CommonLogic.syncAggLineXsRateToAggMach(msgMap, svcCrRebilDtlPk);
                // add end 2016/04/27 CSA Defect#7056
            }

            // ----------------------------------------
            // Adjust Meter Read Info
            // ----------------------------------------
            NSZC053001CommonLogic.adjustMtrRead(msgMap, crRebilTMsg, fixMtrReadList);

            // START 2016/04/08 T.Aoyagi [QC#5901, MOD]
            NSZC053001CommonLogic.callApi(msgMap, this.onBatchTp, crRebilTMsg, FLG_ON_Y, FLG_OFF_N);
            // END 2016/04/08 T.Aoyagi [QC#5901, MOD]
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            NSZC053001CommonLogic.updateCrRebilInfo(msgMap, crRebilTMsg);
        }
    }

    private boolean updateSvcCrRebilBase(S21ApiMessageMap msgMap, BigDecimal crRebilBasePk, NSZC053002_xxBaseChangesListPMsg baseInfo) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        SVC_CR_REBIL_BASE_BLLGTMsg inMsg = query.getSvcCrRebilBaseBllgTMsgForUpdate(pMsg.glblCmpyCd.getValue(), crRebilBasePk);
        if (inMsg == null) {
            msgMap.addXxMsgId(NSZM0891E);
            return false;
        }
        setValue(inMsg.newBaseDealAmt, baseInfo.newBaseDealAmt);
        // START 04/29/2022 E. Sanchez [QC#59914-1, ADD]
        setValue(inMsg.newBaseUnitAmt, baseInfo.newBaseUnitAmt);
        // END 04/29/2022 E. Sanchez [QC#59914-1, ADD]
        // START 05/09/2016 T.Aoyagi [QC#7618, ADD]
        if (hasValue(inMsg.oldBaseDealAmt) && hasValue(inMsg.newBaseDealAmt)) {
            setValue(inMsg.invBaseDealNetAmt, (inMsg.newBaseDealAmt.getValue().subtract(inMsg.oldBaseDealAmt.getValue())).abs());
        }
        // END 05/09/2016 T.Aoyagi [QC#7618, ADD]
        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0878E);
            return false;
        }
        return true;
    }

    private boolean updateSvcCrRebilXsMtr(S21ApiMessageMap msgMap, BigDecimal crRebilXsMtrPk, NSZC053002_xxPriceChangesListPMsg xsMtrInfo) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        SVC_CR_REBIL_XS_MTR_BLLGTMsg inMsg = query.getSvcCrRebilXsMtrBllgTMsgForUpdate(pMsg.glblCmpyCd.getValue(), crRebilXsMtrPk);
        if (inMsg == null) {
            msgMap.addXxMsgId(NSZM0895E);
            return false;
        }
        setValue(inMsg.newXsCopyQty, xsMtrInfo.newXsCopyQty);
        setValue(inMsg.newXsMtrAmtRate, xsMtrInfo.newXsMtrAmtRate);
        // START 2018/08/27 [QC#24555, ADD]
        setValue(inMsg.newUnitXsCopyQty, xsMtrInfo.newUnitXsCopyQty);
        // END   2018/08/27 [QC#24555, ADD]
        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0882E);
            return false;
        }
        return true;
    }

    private SVC_CR_REBIL_MTR_READTMsg updateSvcCrRebilMtrRead(S21ApiMessageMap msgMap, BigDecimal crRebilMtrReadPk, NSZC053002_xxMeterReadChangesListPMsg mtrReadInfo) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        SVC_CR_REBIL_MTR_READTMsg inMsg = query.getSvcCrRebilMtrReadBllgTMsgForUpdate(pMsg.glblCmpyCd.getValue(), crRebilMtrReadPk);
        if (inMsg == null) {
            msgMap.addXxMsgId(NSZM0894E);
            return inMsg;
        }
        setValue(inMsg.newStartReadMtrCnt, mtrReadInfo.newStartReadMtrCnt);
        setValue(inMsg.newEndReadMtrCnt, mtrReadInfo.newEndReadMtrCnt);
        //START 2017/09/19 E.Kameishi [QC#18636,MOD]
        setValue(inMsg.newTestMtrCnt, mtrReadInfo.newTestMtrCnt);
//        setValue(inMsg.newStartTestMtrCnt, mtrReadInfo.newStartTestMtrCnt);
//        setValue(inMsg.newEndTestMtrCnt, mtrReadInfo.newEndTestMtrCnt);
        //END 2017/09/15 E.Kameishi [QC#18636,MOD]
        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0881E);
        }
        return inMsg;
    }
}
