/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0690;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import static business.blap.NSAL0690.constant.NSAL0690Constant.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCommonConst;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0690.common.NSAL0690CommonLogic;
import business.db.CFS_CONTR_PRC_UPLFTTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFF_MTRTMsg;
import business.db.DS_CONTR_TRKTMsg;
import business.db.MDL_NMTMsg;
import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxContrDtlListPMsg;
import business.parts.NSZC046001_xxContrXsCopyListPMsg;
import business.parts.NSZC046001_xxDsContrBllgMtrListPMsg;
import business.parts.NSZC046001_xxSvcMemoListPMsg;
import business.parts.NSZC047011PMsg;
import business.parts.NSZC048001PMsg;
import business.parts.NSZC100001PMsg;
import business.parts.NWZC194001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC046001.NSZC046001;
import com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.api.NSZ.NSZC048001.NSZC048001;
import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.api.NSZ.NSZC100001.NSZC100001;
import com.canon.cusa.s21.api.NWZ.NWZC194001.NWZC194001;
import com.canon.cusa.s21.api.NWZ.NWZC194001.constant.NWZC194001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.ContrRenewalPreCheckInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrRenewalPreCheck;
import com.canon.cusa.s21.common.NSX.NSXC001001.EndOfLifeBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetEndOfLifeInfo;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcTermAmtBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcTermAmt;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_RNW_ERR_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Renew Contract or Machine on Contract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         K.Kasai         Create          N/A
 * 2015/12/15   Hitachi         T.Tsuchida      Update          QC#1659
 * 2015/12/22   Hitachi         T.Tsuchida      Update          QC#2341
 * 2015/12/23   Hitachi         T.Tsuchida      Update          QC#2384
 * 2015/12/23   Hitachi         T.Tsuchida      Update          QC#2390
 * 2015/12/24   Hitachi         T.Tsuchida      Update          QC#2429
 * 2015/12/24   Hitachi         T.Tsuchida      Update          QC#2436
 * 2016/04/08   Hitachi         T.Tomita        Update          QC#6729
 * 2016/05/24   Hitachi         K.Kasai         Update          QC#447
 * 2016/06/03   Hitachi         T.Tomita        Update          QC#1523, 4624
 * 2017/02/13   Hitachi         K.Kitachi       Update          QC#17410
 * 2017/09/08   Hitachi         K.Kitachi       Update          QC#20557
 * 2017/09/28   Hitachi         K.Kojima        Update          QC#18376
 * 2017/10/02   Hitachi         K.Kojima        Update          QC#18376
 * 2017/10/02   Hitachi         M.Kidokoro      Update          QC#18290
 * 2017/10/06   Hitachi         M.Kidokoro      Update          QC#21546
 * 2017/10/30   Hitachi         K.Kojima        Update          QC#21859
 * 2017/11/15   Hitachi         M.Kidokoro      Update          QC#21546-1
 * 2017/11/21   Hitachi         K.Kojima        Update          QC#22712
 * 2017/12/06   Hitachi         K.Yamada        Update          QC#22891
 * 2018/05/22   Hitachi         K.Kitachi       Update          QC#26070
 * 2018/05/23   Hitachi         K.Kitachi       Update          QC#26210
 * 2018/05/18   Hitachi         K.Kojima        Update          QC#26187
 * 2018/05/31   Hitachi         K.Kitachi       Update          QC#26210
 * 2018/06/15   Hitachi         K.Kojima        Update          QC#26702
 * 2018/06/18   Hitachi         U.Kim           Update          QC#24903
 * 2018/08/17   Hitachi         K.Kojima        Update          QC#27295
 * 2018/12/19   Fujitsu         W.Honda         Update          QC#29636
 * 2021/12/02   CITS            R.Cabral        Update          QC#59352
 * 2022/06/01   Hitachi         D.Yoshida       Update          QC#59973
 * 2022/12/01   CITS            R.Jin           Update          QC#60880
 * 2024/04/02   Hitachi         T.Nagae         Update          QC#63552
 *</pre>
 */
public class NSAL0690BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0690CMsg cMsg = (NSAL0690CMsg) arg0;
        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0690Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0690Scrn00_CMN_Submit(cMsg);
            }
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSAL0690Scrn00_CMN_Submit(NSAL0690CMsg cMsg) {
        //Validation
        if (!checkExistSelectCheckbox(cMsg)) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }

        // Base -> Overage Copy and Item Validation 
        NSAL0690_BCMsg curBCMsg;
        NSAL0690_BCMsg bageBCMsg = null;
        boolean validErrFlg = false;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            curBCMsg = cMsg.B.no(i);
            if (bageBCMsg != null && bageBCMsg.dsContrDtlPk_B1.getValue().equals(curBCMsg.dsContrDtlPk_B1.getValue()) && DETAIL_OVERAGE_NM.equals(curBCMsg.xxScrItem8Txt_B1.getValue())) {
                // Base -> Overage Copy
                setValue(curBCMsg.xxChkBox_B1, bageBCMsg.xxChkBox_B1);
                setValue(curBCMsg.xxThruDt_B1, bageBCMsg.xxThruDt_B1);
            }

            // Item Validation
            if (CHKBOX_ON_Y.equals(curBCMsg.xxChkBox_B1.getValue()) && validationCheck(cMsg, curBCMsg)) {
                validErrFlg = true;
            }

            if (DETAIL_BASE_NM.equals(curBCMsg.xxScrItem8Txt_B1.getValue())) {
                bageBCMsg = curBCMsg;
            }
        }
        if (validErrFlg) {
            return;
        }

        // START 2018/05/18 K.Kojima [QC#26187,ADD]
        if (!checkAccessoryThruDt(cMsg)) {
            return;
        }
        // END 2018/05/18 K.Kojima [QC#26187,ADD]

        //Update Process

        NSZC046001PMsg inPMsg = null;
        DS_CONTRTMsg dsContrTMsg;
        BigDecimal dsContrPk = null;
        boolean errFlg = false;
        // START 2017/02/08 K.Kitachi [QC#17410, ADD]
        String dsContrModTxt = ZYPDateUtil.getCurrentSystemTime(FORMAT_DS_CONTR_MOD_TXT).toUpperCase();
        String cfsDlrCd = ZYPCodeDataUtil.getVarCharConstValue(CFS_DLR_CD, cMsg.glblCmpyCd.getValue());
        MDL_NMTMsg mdlNmTMsg = getMdlNmTMsg(cMsg);
        cMsg.setMessageInfo(NSAM0200I);
        // END 2017/02/08 K.Kitachi [QC#17410, ADD]
        // START 2017/10/02 M.Kidokoro [QC#18290, ADD]
        String contrRnwErrRsnCd = "";
        // START 2018/06/15 K.Kojima [QC#26702,DEL]
        // NSZC046001_xxContrDtlListPMsg xxContrDtlListPMsg;
        // NSZC046001_xxDsContrBllgMtrListPMsg xxDsContrBllgMtrListPMsg;
        // END 2018/06/15 K.Kojima [QC#26702,DEL]
        // END 2017/10/02 M.Kidokoro [QC#18290, ADD]
        for (int a = 0; a < cMsg.A.getValidCount(); a++) {
            dsContrPk = cMsg.A.no(a).dsContrPk_A1.getValue();
            // set Header
            inPMsg = setParamForHdr(cMsg, cMsg.A.no(a));
            dsContrTMsg = getDsContr(cMsg.glblCmpyCd.getValue(), dsContrPk);

            // START 2017/02/08 K.Kitachi [QC#17410, ADD]
            errFlg = false;
            // END 2017/02/08 K.Kitachi [QC#17410, ADD]
            if (DS_CONTR_CATG.REGULAR.equals(cMsg.A.no(a).dsContrCatgCd_A1.getValue())) {
                // Non Fleet
                if (setParamForNonFleet(cMsg, dsContrTMsg, inPMsg)) {
                    errFlg = true;
                    continue;
                }
            } else if (DS_CONTR_CATG.FLEET.equals(cMsg.A.no(a).dsContrCatgCd_A1.getValue())) {
                // Fleet
                if (setParamForFleetAndAgg(cMsg, cMsg.A.no(a), dsContrTMsg, inPMsg)) {
                    errFlg = true;
                    continue;
                }
            } else if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.A.no(a).dsContrCatgCd_A1.getValue())) {
                // Aggregate
                if (setParamForFleetAndAgg(cMsg, cMsg.A.no(a), dsContrTMsg, inPMsg)) {
                    errFlg = true;
                    continue;
                }
            }

            // START 2017/10/02 M.Kidokoro [QC#18290, ADD]
            if (inPMsg.xxContrXsCopyList.getValidCount() > 0) {
                for (int contrXsCopyListCnt = 0; contrXsCopyListCnt < inPMsg.xxContrXsCopyList.getValidCount(); contrXsCopyListCnt++) {
                    // Renewal Max Count Check(Usage)
                    contrRnwErrRsnCd = inPMsg.xxContrXsCopyList.no(contrXsCopyListCnt).contrRnwErrRsnCd.getValue();
                    if (contrRnwErrRsnCd.equals(CONTR_RNW_ERR_RSN.MAX_CONTRACT_RENEWAL_COUNT_ERROR)) {
                        errFlg = true;
                        break;
                    }
                }
            }
            if (inPMsg.xxContrDtlList.getValidCount() > 0) {
                for (int contrDtlListCnt = 0; contrDtlListCnt < inPMsg.xxContrDtlList.getValidCount(); contrDtlListCnt++) {
                    // Renewal Max Count Check(Base)
                    contrRnwErrRsnCd = inPMsg.xxContrDtlList.no(contrDtlListCnt).contrRnwErrRsnCd.getValue();
                    if (contrRnwErrRsnCd.equals(CONTR_RNW_ERR_RSN.MAX_CONTRACT_RENEWAL_COUNT_ERROR)) {
                        errFlg = true;
                        break;
                    }
                    // START 2018/06/15 K.Kojima [QC#26702,DEL]
                    // // PO Expriration Date Check(Base)
                    // xxContrDtlListPMsg = inPMsg.xxContrDtlList.no(contrDtlListCnt);
                    // if (!isPoDtErrForBase(cMsg.glblCmpyCd.getValue(), dsContrPk, xxContrDtlListPMsg.dsContrDtlPk.getValue(), cMsg.B.no(a).xxThruDt_B1.getValue())) {
                    //     // START 2018/05/22 K.Kitachi [QC#26210, MOD]
                    //     // cMsg.setMessageInfo(NSAM0706E);
                    //     // errFlg = true;
                    //     // break;
                    //     if (!updateDsContrDtlSts(cMsg, xxContrDtlListPMsg.dsContrDtlPk.getValue(), DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO)) {
                    //         errFlg = true;
                    //         break;
                    //     }
                    //     // END 2018/05/22 K.Kitachi [QC#26210, MOD]
                    // }
                    // END 2018/06/15 K.Kojima [QC#26702,DEL]
                }
            }
            // START 2018/06/15 K.Kojima [QC#26702,DEL]
            // if (inPMsg.xxDsContrBllgMtrList.getValidCount() > 0) {
            //     for (int contrBllgMtrListCnt = 0; contrBllgMtrListCnt < inPMsg.xxDsContrBllgMtrList.getValidCount(); contrBllgMtrListCnt++) {
            //         // PO Expriration Date Check(Usage)
            //         xxDsContrBllgMtrListPMsg = inPMsg.xxDsContrBllgMtrList.no(contrBllgMtrListCnt);
            //         if (!isPoDtErrForUsg(cMsg.glblCmpyCd.getValue(), dsContrPk, xxDsContrBllgMtrListPMsg.dsContrDtlPk.getValue(), xxDsContrBllgMtrListPMsg.dsContrBllgMtrPk.getValue(), cMsg.B.no(a).xxThruDt_B1.getValue())) {
            //             // START 2018/05/22 K.Kitachi [QC#26210, MOD]
            //             // cMsg.setMessageInfo(NSAM0706E);
            //             // errFlg = true;
            //             // break;
            //             if (!updateDsContrDtlSts(cMsg, xxDsContrBllgMtrListPMsg.dsContrDtlPk.getValue(), DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO)) {
            //                 errFlg = true;
            //                 break;
            //             }
            //             // END 2018/05/22 K.Kitachi [QC#26210, MOD]
            //         }
            //     }
            // }
            // END 2018/06/15 K.Kojima [QC#26702,DEL]
            // END 2017/10/02 M.Kidokoro [QC#18290, ADD]

            // START 2017/02/13 K.Kitachi [QC#17410, DEL]
//            if (inPMsg.xxContrDtlList.getValidCount() > 0) {
//                executeApi(inPMsg);
//                if (!isApiErr(inPMsg, cMsg)) {
//                    // Success
//                    // mod start 2016/06/03 CSA Defect#1523, 4624
//                    if (callContrTrkAPI(cMsg, dsContrPk)) {
//                        setSuccessMsg(inPMsg, cMsg);
//                        EZDConnectionMgr.getInstance().commit();
//                    } else {
//                        EZDConnectionMgr.getInstance().rollback();
//                        errFlg = true;
//                    }
//                    // mod end 2016/06/03 CSA Defect#1523, 4624
//                } else {
//                    // Error
//                    EZDConnectionMgr.getInstance().rollback();
//                    errFlg = true;
//                }
//            }
            // END 2017/02/13 K.Kitachi [QC#17410, DEL]

            // START 2017/02/13 K.Kitachi [QC#17410, ADD]
            if (!errFlg) {
                if (inPMsg.xxContrDtlList.getValidCount() > 0) {
                    executeApi(inPMsg);
                    if (isApiErr(inPMsg, cMsg)) {
                        errFlg = true;
                    }
                }
            }
            // START 2018/06/15 K.Kojima [QC#26702,DEL]
            // // START 2018/05/31 K.Kitachi [QC#26210, ADD]
            // if (!errFlg) {
            //     if (!updateDsContrPrcEffSts(cMsg, dsContrPk)) {
            //         errFlg = true;
            //     }
            // }
            // // END 2018/05/31 K.Kitachi [QC#26210, ADD]
            // END 2018/06/15 K.Kojima [QC#26702,DEL]

            // START 2018/06/19 U.Kim [QC#24903, ADD]
            if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.A.no(a).dsContrCatgCd_A1.getValue())){
                if(!callSumAggregateAPI(cMsg.glblCmpyCd.getValue(), cMsg.A.no(a), ZYPDateUtil.getSalesDate())){
                    errFlg = true;
                }
            }
            // END 2018/06/19 U.Kim [QC#24903, ADD]

            if (!errFlg) {
                if (!callContrTrkAPI(cMsg, dsContrPk)) {
                    errFlg = true;
                }
            }
            if (!errFlg) {
                if (!createCfsContrPrcUplft(inPMsg, cMsg, dsContrModTxt, cfsDlrCd, mdlNmTMsg)) {
                    errFlg = true;
                }
            }
            // START 2018/08/17 K.Kojima [QC#27295,ADD]
            if (!errFlg) {
                if (!NSAL0690CommonLogic.createDsContrPrcChrgRec(cMsg.glblCmpyCd.getValue(), dsContrPk, inPMsg)) {
                    errFlg = true;
                }
            }
            // END 2018/08/17 K.Kojima [QC#27295,ADD]
            // START 2022/06/01 [QC#59973, ADD]
            if (!errFlg) {
                if (inPMsg.xxContrDtlList.getValidCount() > 0) {
                    for (int contrDtlListCnt = 0; contrDtlListCnt < inPMsg.xxContrDtlList.getValidCount(); contrDtlListCnt++) {
                        if (!callSchdAgmtAdjContrApi(cMsg.glblCmpyCd.getValue(), inPMsg.xxContrDtlList.no(contrDtlListCnt).svcMachMstrPk.getValue(), dsContrPk)) {
                            errFlg = true;
                        }
                    }
                }
            }
            // END 2022/06/01 [QC#59973, ADD]
            
            // START 2024/04/02 T.Nagae [QC#63552, ADD]
            if (!errFlg) {
                if(!createRenewalLetterWork(cMsg)){
                    errFlg = true;
                }
            }
            // END 2024/04/02 T.Nagae [QC#63552, ADD]
            
            if (!errFlg) {
                setSuccessMsg(inPMsg, cMsg);
                EZDConnectionMgr.getInstance().commit();
            } else {
                cMsg.setMessageInfo(NSAM0394W);
                EZDConnectionMgr.getInstance().rollback();
            }
            // END 2017/02/13 K.Kitachi [QC#17410, ADD]
        }

        // START 2017/02/13 K.Kitachi [QC#17410, DEL]
//        if (errFlg) {
//            cMsg.setMessageInfo(NSAM0394W);
//        } else {
//            cMsg.setMessageInfo(NSAM0200I);
//        }
        // END 2017/02/13 K.Kitachi [QC#17410, DEL]
    }

    private boolean checkExistSelectCheckbox(NSAL0690CMsg cMsg) {
        boolean existCheck = false;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (FLG_ON_Y.equals(cMsg.B.no(i).xxChkBox_B1.getValue())) {
                existCheck = true;
                break;
            }
        }
        return existCheck;
    }

    private NSZC046001PMsg setParamForHdr(NSAL0690CMsg cMsg, NSAL0690_ACMsg aCMsgIn) {
        NSZC046001PMsg inPMsg = new NSZC046001PMsg();
        setValue(inPMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(inPMsg.xxModeCd, NSZC046001Constant.MODE_RENEWAL);
        setValue(inPMsg.slsDt, ZYPDateUtil.getSalesDate());
        setValue(inPMsg.psnCd, cMsg.getUserID());
        setValue(inPMsg.dsContrPk, aCMsgIn.dsContrPk_A1);
        setValue(inPMsg.dsContrCatgCd, aCMsgIn.dsContrCatgCd_A1);
        return inPMsg;
    }

    private void setParamForSvcMemo(NSZC046001PMsg inPMsg, NSAL0690CMsg cMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        int idx = inPMsg.xxSvcMemoList.getValidCount();
        setValue(inPMsg.xxSvcMemoList.no(idx).xxModeCd, NSZC046001Constant.XX_MODE_CREATE);
        setValue(inPMsg.xxSvcMemoList.no(idx).svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(inPMsg.xxSvcMemoList.no(idx).svcMemoRsnCd, cMsg.svcMemoRsnCd_H3);
        setValue(inPMsg.xxSvcMemoList.no(idx).svcMemoTpCd, SVC_MEMO_TP.RENEW_CONTRACT_OR_MACHINE);
        setValue(inPMsg.xxSvcMemoList.no(idx).svcCmntTxt, cMsg.svcCmntTxt_H1);
        setValue(inPMsg.xxSvcMemoList.no(idx).dsContrPk, dsContrPk);
        setValue(inPMsg.xxSvcMemoList.no(idx).dsContrDtlPk, dsContrDtlPk);
        setValue(inPMsg.xxSvcMemoList.no(idx).lastUpdUsrId, cMsg.getUserID());
        setValue(inPMsg.xxSvcMemoList.no(idx).lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
        inPMsg.xxSvcMemoList.setValidCount(++idx);
    }

    private void setParamForDtl(NSAL0690CMsg cMsg, NSAL0690_BCMsg bCMsgIn, NSZC046001PMsg inPMsg, DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        if (DETAIL_BASE_NM.equals(bCMsgIn.xxScrItem8Txt_B1.getValue())) {
            // Base
            // START 2017/10/02 M.Kidokoro [QC#18290, MOD]
//            setParamForBase(bCMsgIn, inPMsg, dsContrTMsg, dsContrDtlTMsg);
            setParamForBase(bCMsgIn, inPMsg, dsContrTMsg, dsContrDtlTMsg, cMsg);
            // END 2017/10/02 M.Kidokoro [QC#18290, MOD]
        } else if (DETAIL_OVERAGE_NM.equals(bCMsgIn.xxScrItem8Txt_B1.getValue())) {
            // Usage
            // START 2017/09/08 K.Kitachi [QC#20557, MOD]
            // START 2017/09/28 K.Kojima [QC#18376,MOD]
            // setParamForBllgMtr(cMsg, inPMsg, dsContrDtlTMsg, bCMsgIn.xxDiscRatio_B1.getValue());
            // START 2018/06/18 U.Kim [QC#24903,MOD]
            // setParamForBllgMtr(cMsg, inPMsg, dsContrDtlTMsg, bCMsgIn.xxDiscRatio_B1.getValue(), bCMsgIn.rnwPrcMethCd_B1.getValue(), dsContrTMsg.dsContrCatgCd.getValue(), true);
            // START 2021/12/02 R.Cabral [QC#59352,MOD]
            // setParamForBllgMtr(cMsg, inPMsg, dsContrDtlTMsg, bCMsgIn.xxDiscRatio_B1.getValue(), bCMsgIn.rnwPrcMethCd_B1.getValue(), dsContrTMsg.dsContrCatgCd.getValue(), true, dsContrTMsg);
            setParamForBllgMtr(cMsg, inPMsg, dsContrDtlTMsg, bCMsgIn.xxDiscRatio_B1.getValue(), bCMsgIn.rnwPrcMethCd_B1.getValue(), dsContrTMsg.dsContrCatgCd.getValue(), true, dsContrTMsg, null);
            // END   2021/12/02 R.Cabral [QC#59352,MOD]
            // END 2018/06/18 U.Kim [QC#24903,MOD]
            // END 2017/09/28 K.Kojima [QC#18376,MOD]
            // END 2017/09/08 K.Kitachi [QC#20557, MOD]
        }
    }

    private boolean setParamForNonFleet(NSAL0690CMsg cMsg, DS_CONTRTMsg dsContrTMsg, NSZC046001PMsg inPMsg) {
        boolean errFlg = false;
        NSAL0690_BCMsg bCMsgIn;
        Map<BigDecimal, BigDecimal> dsContrDtlPkMap = new HashMap<BigDecimal, BigDecimal>();
        BigDecimal dsContrPk;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            bCMsgIn = cMsg.B.no(i);
            // START 2017/02/08 K.Kitachi [QC#17410, DEL]
//            if (dsContrTMsg == null || !ZYPDateUtil.isSameTimeStamp(bCMsgIn.ezUpTime_H.getValue(), bCMsgIn.ezUpTimeZone_H.getValue(), dsContrTMsg.ezUpTime.getValue(), dsContrTMsg.ezUpTimeZone.getValue())) {
//                setValue(bCMsgIn.xxGenlFldAreaTxt_B1, getRtnMsg(ZZZM9004E));
//                errFlg = true;
//                continue;
//            }
            // END 2017/02/08 K.Kitachi [QC#17410, DEL]
            dsContrPk = dsContrTMsg.dsContrPk.getValue();

            if (dsContrPk.compareTo(bCMsgIn.dsContrPk_B1.getValue()) != 0) {
                continue;
            }

            if (!FLG_ON_Y.equals(bCMsgIn.xxChkBox_B1.getValue())) {
                continue;
            }

            // START 2017/02/08 K.Kitachi [QC#17410, ADD]
            if (!ZYPDateUtil.isSameTimeStamp(bCMsgIn.ezUpTime_H.getValue(), bCMsgIn.ezUpTimeZone_H.getValue(), dsContrTMsg.ezUpTime.getValue(), dsContrTMsg.ezUpTimeZone.getValue())) {
                setValue(bCMsgIn.xxGenlFldAreaTxt_B1, getRtnMsg(ZZZM9004E));
                errFlg = true;
                continue;
            }
            // END 2017/02/08 K.Kitachi [QC#17410, ADD]

            DS_CONTR_DTLTMsg resultD =  getDsContrDtl(cMsg.glblCmpyCd.getValue(), bCMsgIn.dsContrDtlPk_B1.getValue());
            if (!ZYPDateUtil.isSameTimeStamp(bCMsgIn.ezUpTime_D.getValue(), bCMsgIn.ezUpTimeZone_D.getValue(), resultD.ezUpTime.getValue(), resultD.ezUpTimeZone.getValue())) {
                setValue(bCMsgIn.xxGenlFldAreaTxt_B1, getRtnMsg(ZZZM9004E));
                errFlg = true;
                continue;
            }

            // set Detail
            setParamForDtl(cMsg, bCMsgIn, inPMsg, dsContrTMsg, resultD);
            if (!dsContrDtlPkMap.containsKey(bCMsgIn.dsContrDtlPk_B1.getValue())) {
                // set Service Memo
                setParamForSvcMemo(inPMsg, cMsg, dsContrPk, bCMsgIn.dsContrDtlPk_B1.getValue());
                dsContrDtlPkMap.put(bCMsgIn.dsContrDtlPk_B1.getValue(), bCMsgIn.dsContrDtlPk_B1.getValue());
            }
        }
        return errFlg;
    }

    private boolean setParamForFleetAndAgg(NSAL0690CMsg cMsg, NSAL0690_ACMsg aCMsg, DS_CONTRTMsg dsContrTMsg, NSZC046001PMsg inPMsg) {
        boolean errFlg = false;
        if (dsContrTMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            errFlg = true;
            return errFlg;
        }

        int dtlCnt = inPMsg.xxContrDtlList.getValidCount();
        BigDecimal dsContrPk = aCMsg.dsContrPk_A1.getValue();
        String dsContrDtlTpCd = DS_CONTR_DTL_TP.FLEET;
        if (DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
            dsContrDtlTpCd = DS_CONTR_DTL_TP.AGGREGATE;
        }

        DS_CONTR_DTLTMsg dsContrDtlTMsg = NSAL0690Query.getInstance().getDsContrDtlByDsContrPk(cMsg.glblCmpyCd.getValue(), dsContrPk, dsContrDtlTpCd);
        String xxThruDt = getMaxThruDt(cMsg, dsContrPk);
        if (dsContrDtlTMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            errFlg = true;
            return errFlg;
        }
        if (dsContrDtlTMsg != null && dsContrDtlTMsg.contrEffThruDt.getValue().compareTo(xxThruDt) > 0) {
            cMsg.setMessageInfo(NSAM0284E, new String[]{"New Period End", "End Date"});
            errFlg = true;
            return errFlg;
        }

        // Base Renewal Data
        Map<String, Object> baseRatioMap = NSAL0690CommonLogic.getPrcUpRatio(cMsg, aCMsg, BASE);

        // START 2017/11/15 M.Kidokoro [QC#21546-1, ADD]
        boolean basePrcSetFlg = false;
        if (DS_CONTR_CATG.FLEET.equals(aCMsg.dsContrCatgCd_A1.getValue())) {
            String contrRnwErrRsnCd = callContrRenewalPreCheck(dsContrDtlTMsg.dsContrDtlPk.getValue(), aCMsg.newBaseDealAmt_A1.getValue(), null, null, null);
            if (hasValue(contrRnwErrRsnCd)) {
                // START 2018/06/18 U.Kim [QC#24903,ADD]
                DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = NSAL0690Query.getInstance().getDsContrPrcEffForLastBase(cMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
                // START 2018/12/19 W.Honda [QC#29636,MOD]
//                BigDecimal basePrcDealAmt = recalcValueForMaxRate(dsContrPrcEffTMsg.basePrcDealAmt.getValue(), dsContrTMsg, dsContrDtlTMsg, 2);
                BigDecimal basePrcDealAmt = recalcValueForMaxRate(dsContrPrcEffTMsg.basePrcDealAmt.getValue(), dsContrTMsg, dsContrDtlTMsg, 2, true);
              // END 2018/12/19 W.Honda [QC#29636,MOD]
                // END 2018/06/18 U.Kim [QC#24903,ADD]
                setValue(inPMsg.xxContrDtlList.no(dtlCnt).contrRnwErrRsnCd, contrRnwErrRsnCd);
                // START 2018/06/18 U.Kim [QC#24903,MOD]
                // setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcDealAmt, dsContrDtlTMsg.basePrcDealAmt);
                setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcDealAmt, basePrcDealAmt);
                // END 2018/06/18 U.Kim [QC#24903,MOD]
                BigDecimal newCalPrcAmtRate = BigDecimal.ZERO;
                String effFromDt = ZYPDateUtil.addDays(dsContrDtlTMsg.contrEffThruDt.getValue(), 1);
                int bllgCycleCnt = NSAL0690CommonLogic.calcBllgCycleCntFromDuration(cMsg.glblCmpyCd.getValue(), effFromDt, xxThruDt, dsContrDtlTMsg.baseBllgCycleCd.getValue());
                // START 2018/06/18 U.Kim [QC#24903,MOD]
                // if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(dsContrDtlTMsg.basePrcDealAmt)) {
                //     newCalPrcAmtRate = dsContrDtlTMsg.basePrcDealAmt.getValue().multiply(BigDecimal.valueOf(bllgCycleCnt));
                // } else {
                //     newCalPrcAmtRate = getCalPrcAmtRate(xxThruDt, dsContrDtlTMsg.basePrcDealAmt.getValue(), dsContrTMsg, dsContrDtlTMsg);
                // }
                if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(basePrcDealAmt)) {
                    newCalPrcAmtRate = basePrcDealAmt.multiply(BigDecimal.valueOf(bllgCycleCnt));
                } else {
                    newCalPrcAmtRate = getCalPrcAmtRate(xxThruDt, basePrcDealAmt, dsContrTMsg, dsContrDtlTMsg);
                }
                // END 2018/06/18 U.Kim [QC#24903,MOD]
                setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcTermDealAmtRate, newCalPrcAmtRate);
                if (contrRnwErrRsnCd.equals(CONTR_RNW_ERR_RSN.MAX_CONTRACT_RENEWAL_COUNT_ERROR)) {
                    cMsg.setMessageInfo(NSZM0844E);
                }
                basePrcSetFlg = true;
            }
        }
        // END 2017/11/15 M.Kidokoro [QC#21546-1, ADD]

        // Line Data
        setValue(inPMsg.xxContrDtlList.no(dtlCnt).dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
        setValue(inPMsg.xxContrDtlList.no(dtlCnt).dsContrDtlTpCd, dsContrDtlTpCd);
        setValue(inPMsg.xxContrDtlList.no(dtlCnt).contrEffThruDt, xxThruDt);
        // START 2017/11/15 M.Kidokoro [QC#21546-1, ADD]
        if (!basePrcSetFlg) {
        // END 2017/11/15 M.Kidokoro [QC#21546-1, ADD]
            if (baseRatioMap != null && hasValue((BigDecimal) baseRatioMap.get("BASE_PRC_UP_RATIO"))) {
                setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcDealAmt, aCMsg.newBaseDealAmt_A1);
                // START 2017/10/30 K.Kojima [QC#21859,MOD]
                // setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcTermDealAmtRate,
                // getCalPrcAmtRate(xxThruDt,
                // aCMsg.newBaseDealAmt_A1.getValue(), dsContrTMsg,
                // dsContrDtlTMsg));
                BigDecimal newCalPrcAmtRate = BigDecimal.ZERO;
                String effFromDt = ZYPDateUtil.addDays(dsContrDtlTMsg.contrEffThruDt.getValue(), 1);
                int bllgCycleCnt = NSAL0690CommonLogic.calcBllgCycleCntFromDuration(cMsg.glblCmpyCd.getValue(), effFromDt, xxThruDt, dsContrDtlTMsg.baseBllgCycleCd.getValue());
                if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(aCMsg.newBaseDealAmt_A1)) {
                    newCalPrcAmtRate = aCMsg.newBaseDealAmt_A1.getValue().multiply(BigDecimal.valueOf(bllgCycleCnt));
                } else {
                    newCalPrcAmtRate = getCalPrcAmtRate(xxThruDt, aCMsg.newBaseDealAmt_A1.getValue(), dsContrTMsg, dsContrDtlTMsg);
                }
                setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcTermDealAmtRate, newCalPrcAmtRate);
                // END 2017/10/30 K.Kojima [QC#21859,MOD]
                // START 2017/09/28 K.Kojima [QC#18376,ADD]
            } else if (hasValue(aCMsg.newBaseDealAmt_A1) && aCMsg.newBaseDealAmt_A1.getValue().compareTo(BigDecimal.ZERO) > 0) {
                setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcDealAmt, aCMsg.newBaseDealAmt_A1);
                // START 2017/10/30 K.Kojima [QC#21859,MOD]
                // setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcTermDealAmtRate,
                // getCalPrcAmtRate(xxThruDt,
                // aCMsg.newBaseDealAmt_A1.getValue(), dsContrTMsg,
                // dsContrDtlTMsg));
                BigDecimal newCalPrcAmtRate = BigDecimal.ZERO;
                String effFromDt = ZYPDateUtil.addDays(dsContrDtlTMsg.contrEffThruDt.getValue(), 1);
                int bllgCycleCnt = NSAL0690CommonLogic.calcBllgCycleCntFromDuration(cMsg.glblCmpyCd.getValue(), effFromDt, xxThruDt, dsContrDtlTMsg.baseBllgCycleCd.getValue());
                if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(aCMsg.newBaseDealAmt_A1)) {
                    newCalPrcAmtRate = aCMsg.newBaseDealAmt_A1.getValue().multiply(BigDecimal.valueOf(bllgCycleCnt));
                } else {
                    newCalPrcAmtRate = getCalPrcAmtRate(xxThruDt, aCMsg.newBaseDealAmt_A1.getValue(), dsContrTMsg, dsContrDtlTMsg);
                }
                setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcTermDealAmtRate, newCalPrcAmtRate);
                // END 2017/10/30 K.Kojima [QC#21859,MOD]
                // END 2017/09/28 K.Kojima [QC#18376,ADD]
            } else {
                setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcDealAmt, dsContrDtlTMsg.basePrcDealAmt);
                // START 2017/11/21 K.Kojima [QC#22712,MOD]
                // setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcTermDealAmtRate, dsContrDtlTMsg.basePrcTermDealAmtRate);
                BigDecimal newCalPrcAmtRate = BigDecimal.ZERO;
                String effFromDt = ZYPDateUtil.addDays(dsContrDtlTMsg.contrEffThruDt.getValue(), 1);
                int bllgCycleCnt = NSAL0690CommonLogic.calcBllgCycleCntFromDuration(cMsg.glblCmpyCd.getValue(), effFromDt, xxThruDt, dsContrDtlTMsg.baseBllgCycleCd.getValue());
                if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(dsContrDtlTMsg.basePrcDealAmt)) {
                    newCalPrcAmtRate = dsContrDtlTMsg.basePrcDealAmt.getValue().multiply(BigDecimal.valueOf(bllgCycleCnt));
                } else {
                    newCalPrcAmtRate = getCalPrcAmtRate(xxThruDt, dsContrDtlTMsg.basePrcDealAmt.getValue(), dsContrTMsg, dsContrDtlTMsg);
                }
                setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcTermDealAmtRate, newCalPrcAmtRate);
                // END 2017/11/21 K.Kojima [QC#22712,MOD]
            }
        // START 2017/11/15 M.Kidokoro [QC#21546-1, ADD]
        }
        // END 2017/11/15 M.Kidokoro [QC#21546-1, ADD]
        setValue(inPMsg.xxContrDtlList.no(dtlCnt).rnwBaseFlg, ZYPConstant.FLG_ON_Y);
        // START 2017/09/08 K.Kitachi [QC#20557, ADD]
        setValue(inPMsg.xxContrDtlList.no(dtlCnt).svcMachMstrPk, dsContrDtlTMsg.svcMachMstrPk);
        // END 2017/09/08 K.Kitachi [QC#20557, ADD]
        inPMsg.xxContrDtlList.setValidCount(++dtlCnt);
        Map<String, Object> prcUpRatioMap = NSAL0690CommonLogic.getPrcUpRatio(cMsg, aCMsg, OVERAGE);
        BigDecimal mtrPrcUpRatio = null;
        if (prcUpRatioMap != null) {
            mtrPrcUpRatio = (BigDecimal) prcUpRatioMap.get("MTR_PRC_UP_RATIO");
        }

        // START 2017/09/08 K.Kitachi [QC#20557, MOD]
        // START 2017/09/28 K.Kojima [QC#18376,MOD]
        // setParamForBllgMtr(cMsg, inPMsg, dsContrDtlTMsg, mtrPrcUpRatio);
        // START 2018/06/18 U.Kim [QC#24903,MOD]
        // setParamForBllgMtr(cMsg, inPMsg, dsContrDtlTMsg, mtrPrcUpRatio, aCMsg.rnwPrcMethCd_AU.getValue(), dsContrTMsg.dsContrCatgCd.getValue(), false);
        // START 2021/12/02 R.Cabral [QC#59352,MOD]
        // setParamForBllgMtr(cMsg, inPMsg, dsContrDtlTMsg, mtrPrcUpRatio, aCMsg.rnwPrcMethCd_AU.getValue(), dsContrTMsg.dsContrCatgCd.getValue(), false, dsContrTMsg);
        List<Map<String, Object>> aggMtrPrcList = new ArrayList<Map<String, Object>>();
        setParamForBllgMtr(cMsg, inPMsg, dsContrDtlTMsg, mtrPrcUpRatio, aCMsg.rnwPrcMethCd_AU.getValue(), dsContrTMsg.dsContrCatgCd.getValue(), false, dsContrTMsg, aggMtrPrcList);
        // END   2021/12/02 R.Cabral [QC#59352,MOD]
        // END 2018/06/18 U.Kim [QC#24903,MOD]
        // END 2017/09/28 K.Kojima [QC#18376,MOD]
        // END 2017/09/08 K.Kitachi [QC#20557, MOD]

        // Service Memo
        setParamForSvcMemo(inPMsg, cMsg, dsContrPk, dsContrDtlTMsg.dsContrDtlPk.getValue());

        // Machine Data
        // START 2017/10/02 K.Kojima [QC#18376,MOD]
        // return setParamMachForFleetAndAgg(cMsg, dsContrTMsg, inPMsg, mtrPrcUpRatio);
        // START 2021/12/02 R.Cabral [QC#59352,MOD]
        // return setParamMachForFleetAndAgg(cMsg, dsContrTMsg, inPMsg, mtrPrcUpRatio, aCMsg.rnwPrcMethCd_AU.getValue());
        return setParamMachForFleetAndAgg(cMsg, dsContrTMsg, inPMsg, mtrPrcUpRatio, aCMsg.rnwPrcMethCd_AU.getValue(), aggMtrPrcList);
        // END   2021/12/02 R.Cabral [QC#59352,MOD]
        // END 2017/10/02 K.Kojima [QC#18376,MOD]
    }

    // START 2017/10/02 K.Kojima [QC#18378,MOD]
    // private boolean setParamMachForFleetAndAgg(NSAL0690CMsg cMsg, DS_CONTRTMsg dsContrTMsg, NSZC046001PMsg inPMsg, BigDecimal mtrPrcUpRatio) {
    // START 2021/12/02 R.Cabral [QC#59352,MOD]
    // private boolean setParamMachForFleetAndAgg(NSAL0690CMsg cMsg, DS_CONTRTMsg dsContrTMsg, NSZC046001PMsg inPMsg, BigDecimal mtrPrcUpRatio, String rnwPrcMethCd) {
    private boolean setParamMachForFleetAndAgg(NSAL0690CMsg cMsg, DS_CONTRTMsg dsContrTMsg, NSZC046001PMsg inPMsg, BigDecimal mtrPrcUpRatio, String rnwPrcMethCd, List<Map<String, Object>> aggMtrPrcList) {
    // END   2021/12/02 R.Cabral [QC#59352,MOD]
    // END 2017/10/02 K.Kojima [QC#18378,MOD]
        boolean errFlg = false;
        NSAL0690_BCMsg bCMsgIn;
        Map<BigDecimal, BigDecimal> dsContrDtlPkMap = new HashMap<BigDecimal, BigDecimal>();
        BigDecimal dsContrPk;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            bCMsgIn = cMsg.B.no(i);
            // START 2017/02/08 K.Kitachi [QC#17410, DEL]
//            if (dsContrTMsg == null || !ZYPDateUtil.isSameTimeStamp(bCMsgIn.ezUpTime_H.getValue(), bCMsgIn.ezUpTimeZone_H.getValue(), dsContrTMsg.ezUpTime.getValue(), dsContrTMsg.ezUpTimeZone.getValue())) {
//                setValue(bCMsgIn.xxGenlFldAreaTxt_B1, getRtnMsg(ZZZM9004E));
//                errFlg = true;
//                continue;
//            }
            // END 2017/02/08 K.Kitachi [QC#17410, DEL]
            dsContrPk = dsContrTMsg.dsContrPk.getValue();

            if (dsContrPk.compareTo(bCMsgIn.dsContrPk_B1.getValue()) != 0) {
                continue;
            }

            if (!FLG_ON_Y.equals(bCMsgIn.xxChkBox_B1.getValue())) {
                continue;
            }

            // START 2017/02/08 K.Kitachi [QC#17410, ADD]
            if (!ZYPDateUtil.isSameTimeStamp(bCMsgIn.ezUpTime_H.getValue(), bCMsgIn.ezUpTimeZone_H.getValue(), dsContrTMsg.ezUpTime.getValue(), dsContrTMsg.ezUpTimeZone.getValue())) {
                setValue(bCMsgIn.xxGenlFldAreaTxt_B1, getRtnMsg(ZZZM9004E));
                errFlg = true;
                continue;
            }
            // END 2017/02/08 K.Kitachi [QC#17410, ADD]

            DS_CONTR_DTLTMsg dsContrDtlTMsg =  getDsContrDtl(cMsg.glblCmpyCd.getValue(), bCMsgIn.dsContrDtlPk_B1.getValue());
            if (!ZYPDateUtil.isSameTimeStamp(bCMsgIn.ezUpTime_D.getValue(), bCMsgIn.ezUpTimeZone_D.getValue(), dsContrDtlTMsg.ezUpTime.getValue(), dsContrDtlTMsg.ezUpTimeZone.getValue())) {
                setValue(bCMsgIn.xxGenlFldAreaTxt_B1, getRtnMsg(ZZZM9004E));
                errFlg = true;
                continue;
            }

            // set Base Detail
            // START 2017/10/02 M.Kidokoro [QC#18290, MOD]
//            setParamForBase(bCMsgIn, inPMsg, dsContrTMsg, dsContrDtlTMsg);
            setParamForBase(bCMsgIn, inPMsg, dsContrTMsg, dsContrDtlTMsg, cMsg);
            // END 2017/10/02 M.Kidokoro [QC#18290, MOD]

            // set Usage Detail
            // START 2017/09/08 K.Kitachi [QC#20557, MOD]
            // START 2017/09/28 K.Kojima [QC#18376,MOD]
            // setParamForBllgMtr(cMsg, inPMsg, dsContrDtlTMsg, mtrPrcUpRatio);
            // START 2018/06/18 U.Kim [QC#24903,MOD]
            // setParamForBllgMtr(cMsg, inPMsg, dsContrDtlTMsg, mtrPrcUpRatio, rnwPrcMethCd, dsContrTMsg.dsContrCatgCd.getValue(), true);
            // START 2021/12/02 R.Cabral [QC#59352,MOD]
            // setParamForBllgMtr(cMsg, inPMsg, dsContrDtlTMsg, mtrPrcUpRatio, rnwPrcMethCd, dsContrTMsg.dsContrCatgCd.getValue(), true, dsContrTMsg);
            setParamForBllgMtr(cMsg, inPMsg, dsContrDtlTMsg, mtrPrcUpRatio, rnwPrcMethCd, dsContrTMsg.dsContrCatgCd.getValue(), true, dsContrTMsg, aggMtrPrcList);
            // END   2021/12/02 R.Cabral [QC#59352,MOD]
            // END 2018/06/18 U.Kim [QC#24903,MOD]
            // END 2017/09/28 K.Kojima [QC#18376,MOD]
            // END 2017/09/08 K.Kitachi [QC#20557, MOD]

            if (!dsContrDtlPkMap.containsKey(bCMsgIn.dsContrDtlPk_B1.getValue())) {
                // set Service Memo
                setParamForSvcMemo(inPMsg, cMsg, dsContrPk, bCMsgIn.dsContrDtlPk_B1.getValue());
                dsContrDtlPkMap.put(bCMsgIn.dsContrDtlPk_B1.getValue(), bCMsgIn.dsContrDtlPk_B1.getValue());
            }
        }
        return errFlg;
    }

    // START 2017/10/02 M.Kidokoro [QC#18290, MOD]
//    private void setParamForBase(NSAL0690_BCMsg bCMsgIn, NSZC046001PMsg inPMsg, DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
    private void setParamForBase(NSAL0690_BCMsg bCMsgIn, NSZC046001PMsg inPMsg, DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, NSAL0690CMsg cMsg) {
    // END 2017/10/02 M.Kidokoro [QC#18290, MOD]
        int dtlCnt = inPMsg.xxContrDtlList.getValidCount();
        setValue(inPMsg.xxContrDtlList.no(dtlCnt).dsContrPk, bCMsgIn.dsContrPk_B1);
        setValue(inPMsg.xxContrDtlList.no(dtlCnt).dsContrDtlPk, bCMsgIn.dsContrDtlPk_B1);
        setValue(inPMsg.xxContrDtlList.no(dtlCnt).dsContrDtlTpCd, bCMsgIn.dsContrDtlTpCd_B1);
        setValue(inPMsg.xxContrDtlList.no(dtlCnt).contrEffThruDt, bCMsgIn.xxThruDt_B1);
        if (hasValue(bCMsgIn.newBaseDealAmt_B1)) {
            setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcDealAmt, bCMsgIn.newBaseDealAmt_B1);
        } else {
            setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcDealAmt, dsContrDtlTMsg.basePrcDealAmt);
        }
        // START 2017/10/30 K.Kojima [QC#21859,MOD]
        // setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcTermDealAmtRate, getCalPrcAmtRate(bCMsgIn.xxThruDt_B1.getValue(), bCMsgIn.newBaseDealAmt_B1.getValue(), dsContrTMsg, dsContrDtlTMsg));
        BigDecimal newCalPrcAmtRate = BigDecimal.ZERO;
        String effFromDt = ZYPDateUtil.addDays(dsContrDtlTMsg.contrEffThruDt.getValue(), 1);
        int bllgCycleCnt = NSAL0690CommonLogic.calcBllgCycleCntFromDuration(cMsg.glblCmpyCd.getValue(), effFromDt, bCMsgIn.xxThruDt_B1.getValue(), dsContrDtlTMsg.baseBllgCycleCd.getValue());
        // START 2017/11/21 K.Kojima [QC#22712,MOD]
        // if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(bCMsgIn.newBaseDealAmt_B1)) {
        //     newCalPrcAmtRate = bCMsgIn.newBaseDealAmt_B1.getValue().multiply(BigDecimal.valueOf(bllgCycleCnt));
        // } else {
        //     newCalPrcAmtRate = getCalPrcAmtRate(bCMsgIn.xxThruDt_B1.getValue(), bCMsgIn.newBaseDealAmt_B1.getValue(), dsContrTMsg, dsContrDtlTMsg);
        // }
        if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcDealAmt)) {
            newCalPrcAmtRate = inPMsg.xxContrDtlList.no(dtlCnt).basePrcDealAmt.getValue().multiply(BigDecimal.valueOf(bllgCycleCnt));
        } else {
            newCalPrcAmtRate = getCalPrcAmtRate(bCMsgIn.xxThruDt_B1.getValue(), inPMsg.xxContrDtlList.no(dtlCnt).basePrcDealAmt.getValue(), dsContrTMsg, dsContrDtlTMsg);
        }
        // END 2017/11/21 K.Kojima [QC#22712,MOD]
        setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcTermDealAmtRate, newCalPrcAmtRate);
        // END 2017/10/30 K.Kojima [QC#21859,MOD]
        setValue(inPMsg.xxContrDtlList.no(dtlCnt).rnwBaseFlg, ZYPConstant.FLG_ON_Y);
        setValue(inPMsg.xxContrDtlList.no(dtlCnt).rnwUsgFlg, ZYPConstant.FLG_OFF_N);

        // START 2017/10/06 M.Kidokoro [QC#21546,MOD]
//        String contrRnwErrRsnCd = callContrRenewalPreCheck(bCMsgIn.dsContrDtlPk_B1.getValue(), bCMsgIn.basePrcDealAmt_B1.getValue(), null, null, null);
        String contrRnwErrRsnCd = callContrRenewalPreCheck(bCMsgIn.dsContrDtlPk_B1.getValue(), bCMsgIn.newBaseDealAmt_B1.getValue(), null, null, null);
        // END 2017/10/06 M.Kidokoro [QC#21546,MOD]
        if (hasValue(contrRnwErrRsnCd)) {
            // START 2018/06/18 U.Kim [QC#24903,MOD]
            // setValue(inPMsg.xxContrDtlList.no(dtlCnt).contrRnwErrRsnCd, contrRnwErrRsnCd);
            // // START 2017/10/06 M.Kidokoro [QC#21546,ADD]
            // setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcDealAmt, dsContrDtlTMsg.basePrcDealAmt);
            // // START 2017/10/30 K.Kojima [QC#21859,MOD]
            // // setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcTermDealAmtRate, getCalPrcAmtRate(bCMsgIn.xxThruDt_B1.getValue(), dsContrDtlTMsg.basePrcDealAmt.getValue(), dsContrTMsg, dsContrDtlTMsg));
            // newCalPrcAmtRate = BigDecimal.ZERO;
            // effFromDt = ZYPDateUtil.addDays(dsContrDtlTMsg.contrEffThruDt.getValue(), 1);
            // bllgCycleCnt = NSAL0690CommonLogic.calcBllgCycleCntFromDuration(cMsg.glblCmpyCd.getValue(), effFromDt, bCMsgIn.xxThruDt_B1.getValue(), dsContrDtlTMsg.baseBllgCycleCd.getValue());
            // if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(dsContrDtlTMsg.basePrcDealAmt)) {
            //     newCalPrcAmtRate = dsContrDtlTMsg.basePrcDealAmt.getValue().multiply(BigDecimal.valueOf(bllgCycleCnt));
            // } else {
            //     newCalPrcAmtRate = getCalPrcAmtRate(bCMsgIn.xxThruDt_B1.getValue(), dsContrDtlTMsg.basePrcDealAmt.getValue(), dsContrTMsg, dsContrDtlTMsg);
            // }
            // setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcTermDealAmtRate, newCalPrcAmtRate);
            // // END 2017/10/30 K.Kojima [QC#21859,MOD]
            // // END 2017/10/06 M.Kidokoro [QC#21546,ADD]
            // // START 2017/10/02 M.Kidokoro [QC#18290, ADD]
            // if (contrRnwErrRsnCd.equals(CONTR_RNW_ERR_RSN.MAX_CONTRACT_RENEWAL_COUNT_ERROR)) {
            //     cMsg.setMessageInfo(NSZM0844E);
            // }
            // // END 2017/10/02 M.Kidokoro [QC#18290, ADD]
            DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = NSAL0690Query.getInstance().getDsContrPrcEffForLastBase(cMsg.glblCmpyCd.getValue(), bCMsgIn.dsContrDtlPk_B1.getValue());
            BigDecimal basePrcDealAmt = null;
            if (dsContrPrcEffTMsg != null) {
                // START 2018/12/19 W.Honda [QC#29636,MOD]
//                basePrcDealAmt = recalcValueForMaxRate(dsContrPrcEffTMsg.basePrcDealAmt.getValue(), dsContrTMsg, dsContrDtlTMsg, 2);
                basePrcDealAmt = recalcValueForMaxRate(dsContrPrcEffTMsg.basePrcDealAmt.getValue(), dsContrTMsg, dsContrDtlTMsg, 2, true);
                // END 2018/12/19 W.Honda [QC#29636,MOD]
            }
            setValue(inPMsg.xxContrDtlList.no(dtlCnt).contrRnwErrRsnCd, contrRnwErrRsnCd);
            setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcDealAmt, basePrcDealAmt);
            newCalPrcAmtRate = BigDecimal.ZERO;
            effFromDt = ZYPDateUtil.addDays(dsContrDtlTMsg.contrEffThruDt.getValue(), 1);
            bllgCycleCnt = NSAL0690CommonLogic.calcBllgCycleCntFromDuration(cMsg.glblCmpyCd.getValue(), effFromDt, bCMsgIn.xxThruDt_B1.getValue(), dsContrDtlTMsg.baseBllgCycleCd.getValue());
            if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(basePrcDealAmt)) {
                newCalPrcAmtRate = basePrcDealAmt.multiply(BigDecimal.valueOf(bllgCycleCnt));
            } else {
                newCalPrcAmtRate = getCalPrcAmtRate(bCMsgIn.xxThruDt_B1.getValue(), basePrcDealAmt, dsContrTMsg, dsContrDtlTMsg);
            }
            setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcTermDealAmtRate, newCalPrcAmtRate);
            if (contrRnwErrRsnCd.equals(CONTR_RNW_ERR_RSN.MAX_CONTRACT_RENEWAL_COUNT_ERROR)) {
                cMsg.setMessageInfo(NSZM0844E);
            }
            // END 2018/06/18 U.Kim [QC#24903,MOD]
        }
        // START 2017/09/08 K.Kitachi [QC#20557, ADD]
        setValue(inPMsg.xxContrDtlList.no(dtlCnt).svcMachMstrPk, dsContrDtlTMsg.svcMachMstrPk);
        // END 2017/09/08 K.Kitachi [QC#20557, ADD]

        inPMsg.xxContrDtlList.setValidCount(++dtlCnt);
    }

    // START 2017/09/08 K.Kitachi [QC#20557, MOD]
    // START 2017/09/28 K.Kojima [QC#18376,MOD]
    // private void setParamForBllgMtr(NSAL0690CMsg cMsg, NSZC046001PMsg inPMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, BigDecimal mtrPrcUpRatio) {
    // START 2018/06/18 U.Kim [QC#24903,MOD]
    // private void setParamForBllgMtr(NSAL0690CMsg cMsg, NSZC046001PMsg inPMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, BigDecimal mtrPrcUpRatio, String rnwPrcMethCd, String dsContrCatgCd, boolean machineFlag) {
    // START 2021/12/02 R.Cabral [QC#59352,MOD]
    // private void setParamForBllgMtr(NSAL0690CMsg cMsg, NSZC046001PMsg inPMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, BigDecimal mtrPrcUpRatio, String rnwPrcMethCd, String dsContrCatgCd, boolean machineFlag, DS_CONTRTMsg dsContrTMsg) {
    private void setParamForBllgMtr(NSAL0690CMsg cMsg, NSZC046001PMsg inPMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, BigDecimal mtrPrcUpRatio, String rnwPrcMethCd, String dsContrCatgCd, boolean machineFlag, DS_CONTRTMsg dsContrTMsg,
        List<Map<String, Object>> aggMtrPrcList) {
    // END   2021/12/02 R.Cabral [QC#59352,MOD]
    // END 2018/06/18 U.Kim [QC#24903,MOD]
    // END 2017/09/28 K.Kojima [QC#18376,MOD]
        int bllgMtrCnt = inPMsg.xxDsContrBllgMtrList.getValidCount();
        // START 2017/10/02 M.Kidokoro [QC#18290, ADD]
        int xsCopyCnt;
        String contrRnwErrRsnCd;
        // END 2017/10/02 M.Kidokoro [QC#18290, ADD]
        NSAL0690Query query = NSAL0690Query.getInstance();
        DS_CONTR_BLLG_MTRTMsgArray tMsgArray = query.getDsContrBllgMtrList(cMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            DS_CONTR_BLLG_MTRTMsg tMsg = tMsgArray.no(i);
            setValue(inPMsg.xxDsContrBllgMtrList.no(bllgMtrCnt).dsContrDtlPk, tMsg.dsContrDtlPk);
            setValue(inPMsg.xxDsContrBllgMtrList.no(bllgMtrCnt).dsContrBllgMtrPk, tMsg.dsContrBllgMtrPk);
            setValue(inPMsg.xxDsContrBllgMtrList.no(bllgMtrCnt).svcMachMstrPk, dsContrDtlTMsg.svcMachMstrPk);
            inPMsg.xxDsContrBllgMtrList.setValidCount(++bllgMtrCnt);

            // START 2017/09/28 K.Kojima [QC#18376,MOD]
            // setParamForXsCopy(inPMsg, cMsg.glblCmpyCd.getValue(), tMsg.dsContrBllgMtrPk.getValue(), tMsg.dsContrDtlPk.getValue(), mtrPrcUpRatio);
            // START 2018/06/18 U.Kim [QC#24903,MOD]
            // setParamForXsCopy(inPMsg, cMsg.glblCmpyCd.getValue(), tMsg.dsContrBllgMtrPk.getValue(), tMsg.dsContrDtlPk.getValue(), mtrPrcUpRatio, rnwPrcMethCd, dsContrCatgCd, tMsg.bllgMtrLbCd.getValue(), machineFlag);
            // START 2021/12/02 R.Cabral [QC#59352,MOD]
            // setParamForXsCopy(inPMsg, cMsg.glblCmpyCd.getValue(), tMsg.dsContrBllgMtrPk.getValue(), tMsg.dsContrDtlPk.getValue(), mtrPrcUpRatio, rnwPrcMethCd, dsContrCatgCd, tMsg.bllgMtrLbCd.getValue(), machineFlag, dsContrTMsg, dsContrDtlTMsg);
            setParamForXsCopy(inPMsg, cMsg.glblCmpyCd.getValue(), tMsg.dsContrBllgMtrPk.getValue(), tMsg.dsContrDtlPk.getValue(), mtrPrcUpRatio, rnwPrcMethCd, dsContrCatgCd, tMsg.bllgMtrLbCd.getValue(), machineFlag, dsContrTMsg, dsContrDtlTMsg,
                aggMtrPrcList);
            // END   2021/12/02 R.Cabral [QC#59352,MOD]
            // END 2018/06/18 U.Kim [QC#24903,MOD]
            // START 2017/10/02 M.Kidokoro [QC#18290, ADD]
            xsCopyCnt = inPMsg.xxContrXsCopyList.getValidCount() - 1;
            contrRnwErrRsnCd = inPMsg.xxContrXsCopyList.no(xsCopyCnt).contrRnwErrRsnCd.getValue();
            if (contrRnwErrRsnCd.equals(CONTR_RNW_ERR_RSN.MAX_CONTRACT_RENEWAL_COUNT_ERROR)) {
                cMsg.setMessageInfo(NSZM0844E);
            }
            // END 2017/10/02 M.Kidokoro [QC#18290, ADD]
            // END 2017/09/28 K.Kojima [QC#18376,MOD]
        }
        setUsgRnwFlg(inPMsg, dsContrDtlTMsg.dsContrDtlPk.getValue());
    }
    // END 2017/09/08 K.Kitachi [QC#20557, MOD]

    // START 2017/09/28 K.Kojima [QC#18376,MOD]
    // private NSZC046001PMsg setParamForXsCopy(NSZC046001PMsg inPMsg, String glblCmpyCd, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrDtlPk, BigDecimal mtrPrcUpRatio) {
    // START 2018/06/18 U.Kim [QC#24903,MOD]
    // private NSZC046001PMsg setParamForXsCopy(NSZC046001PMsg inPMsg, String glblCmpyCd, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrDtlPk, BigDecimal mtrPrcUpRatio, String rnwPrcMethCd, String dsContrCatgCd, String bllgMtrLbCd, boolean machineFlag) {
    // START 2021/12/02 R.Cabral [QC#59352,MOD]
    // private NSZC046001PMsg setParamForXsCopy(NSZC046001PMsg inPMsg, String glblCmpyCd, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrDtlPk, BigDecimal mtrPrcUpRatio, String rnwPrcMethCd, String dsContrCatgCd, String bllgMtrLbCd, boolean machineFlag, DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
    @SuppressWarnings("unchecked")
	private NSZC046001PMsg setParamForXsCopy(NSZC046001PMsg inPMsg, String glblCmpyCd, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrDtlPk, BigDecimal mtrPrcUpRatio, String rnwPrcMethCd, String dsContrCatgCd, String bllgMtrLbCd, boolean machineFlag,
        DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, List<Map<String, Object>> aggMtrPrcList) {
    // END   2021/12/02 R.Cabral [QC#59352,MOD]
    // END 2018/06/18 U.Kim [QC#24903,MOD]
    // END 2017/09/28 K.Kojima [QC#18376,MOD]
        int xsCopyCnt = inPMsg.xxContrXsCopyList.getValidCount();
        NSAL0690Query query = NSAL0690Query.getInstance();
        // START 2018/05/22 K.Kitachi [QC#26070, MOD]
//        CONTR_XS_COPYTMsgArray tMsgArray = query.getContrXsCopyList(glblCmpyCd, dsContrBllgMtrPk);
        // START 2012/12/01 R.Jin [QC#60880,MOD]
//        List<BigDecimal> dsContrPrcEffMtrPkList = query.getDsContrPrcEffMtrPkList(glblCmpyCd, dsContrBllgMtrPk);
        S21SsmEZDResult ssmResult = query.getDsContrPrcEffMtrPkList(glblCmpyCd, dsContrBllgMtrPk);
        if (!ssmResult.isCodeNotFound()) {
            List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
//          for (int i = 0; i < tMsgArray.getValidCount(); i++) {
//            for (BigDecimal dsContrPrcEffMtrPk : dsContrPrcEffMtrPkList) {
            for (Map<String, Object> resultMap : resultMapList) {
                BigDecimal dsContrPrcEffMtrPk = (BigDecimal)resultMap.get("DS_CONTR_PRC_EFF_MTR_PK");
                String tirNum = String.valueOf(resultMap.get("TIRNUM"));
             // END 2012/12/01 R.Jin [QC#60880,MOD]
//                CONTR_XS_COPYTMsg tMsg = tMsgArray.no(i);
                DS_CONTR_PRC_EFF_MTRTMsg tMsg = query.getDsContrPrcEffMtr(glblCmpyCd, dsContrPrcEffMtrPk);
                if (tMsg == null) {
                    continue;
                }
                setValue(inPMsg.xxContrXsCopyList.no(xsCopyCnt).contrXsCopyPk, tMsg.contrXsCopyPk);
                setValue(inPMsg.xxContrXsCopyList.no(xsCopyCnt).dsContrBllgMtrPk, tMsg.dsContrBllgMtrPk);
                setValue(inPMsg.xxContrXsCopyList.no(xsCopyCnt).xsMtrCopyQty, tMsg.xsMtrCopyQty);
                // START 2017/09/28 K.Kojima [QC#18376,MOD]
                // setValue(inPMsg.xxContrXsCopyList.no(xsCopyCnt).xsMtrAmtRate, calcRnwAmt(tMsg.xsMtrAmtRate.getValue(), mtrPrcUpRatio));
                // START 2018/05/22 K.Kitachi [QC#26070, MOD]
//                setValue(inPMsg.xxContrXsCopyList.no(xsCopyCnt).xsMtrAmtRate, calcRnwAmtForUsage(glblCmpyCd, rnwPrcMethCd, dsContrCatgCd, dsContrDtlPk, bllgMtrLbCd, tMsg.xsMtrAmtRate.getValue(), mtrPrcUpRatio, machineFlag));
                setValue(inPMsg.xxContrXsCopyList.no(xsCopyCnt).xsMtrAmtRate, calcRnwAmtForUsage(glblCmpyCd, rnwPrcMethCd, dsContrCatgCd, dsContrDtlPk, bllgMtrLbCd, tMsg.xsMtrAmtRate.getValue(), mtrPrcUpRatio, machineFlag, inPMsg.xxContrXsCopyList.no(xsCopyCnt).getAttr("xsMtrAmtRate").getFracDigit()));
                // END 2018/05/22 K.Kitachi [QC#26070, MOD]
                // END 2017/09/28 K.Kojima [QC#18376,MOD]
                setValue(inPMsg.xxContrXsCopyList.no(xsCopyCnt).xsMtrFirstFlg, tMsg.xsMtrFirstFlg);
                // START 2017/10/06 M.Kidokoro [QC#21546,MOD]
//                String contrRnwErrRsnCd = callContrRenewalPreCheck(dsContrDtlPk, null, dsContrBllgMtrPk, tMsg.xsMtrCopyQty.getValue(), tMsg.xsMtrAmtRate.getValue());
                String contrRnwErrRsnCd = callContrRenewalPreCheck(dsContrDtlPk, null, dsContrBllgMtrPk, tMsg.xsMtrCopyQty.getValue(), inPMsg.xxContrXsCopyList.no(xsCopyCnt).xsMtrAmtRate.getValue());
                // END 2017/10/06 M.Kidokoro [QC#21546,MOD]
                if (hasValue(contrRnwErrRsnCd)) {
                    setValue(inPMsg.xxContrXsCopyList.no(xsCopyCnt).contrRnwErrRsnCd, contrRnwErrRsnCd);
                    // START 2017/10/06 K.Kojima [QC#21547,ADD]
                    // START 2018/06/18 U.Kim [QC#24903,MOD]
                    // setValue(inPMsg.xxContrXsCopyList.no(xsCopyCnt).xsMtrAmtRate, tMsg.xsMtrAmtRate.getValue());
                    // START 2018/12/19 W.Honda [QC#29636,MOD]
//                    setValue(inPMsg.xxContrXsCopyList.no(xsCopyCnt).xsMtrAmtRate, recalcValueForMaxRate(tMsg.xsMtrAmtRate.getValue(), dsContrTMsg, dsContrDtlTMsg, 6));
                    setValue(inPMsg.xxContrXsCopyList.no(xsCopyCnt).xsMtrAmtRate, recalcValueForMaxRate(tMsg.xsMtrAmtRate.getValue(), dsContrTMsg, dsContrDtlTMsg, 6, false));
                // END 2018/12/19 W.Honda [QC#29636,MOD]
                    // END 2018/06/18 U.Kim [QC#24903,MOD]
                    // END 2017/10/06 K.Kojima [QC#21547,ADD]
                }
                // START 2021/12/02 R.Cabral [QC#59352,ADD]
                if (isFleetOrAggMachine(dsContrTMsg.dsContrCatgCd.getValue(), dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                    setValue(inPMsg.xxContrXsCopyList.no(xsCopyCnt).xsMtrAmtRate, recalcValueForMaxRate(tMsg.xsMtrAmtRate.getValue(), dsContrTMsg, dsContrDtlTMsg, 6, false));
                }
                if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && aggMtrPrcList != null) {
                    if (!isFleetOrAggMachine(dsContrTMsg.dsContrCatgCd.getValue(), dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                        if (ZYPCommonFunc.hasValue(inPMsg.xxContrXsCopyList.no(xsCopyCnt).xsMtrAmtRate)) {
                            HashMap<String, Object> aggPrc = new HashMap<String, Object>();
                            // START 2012/12/01 R.Jin [QC#60880,MOD]
//                            aggPrc.put(bllgMtrLbCd, inPMsg.xxContrXsCopyList.no(xsCopyCnt).xsMtrAmtRate.getValue());
                            aggPrc.put(bllgMtrLbCd.concat(tirNum), inPMsg.xxContrXsCopyList.no(xsCopyCnt).xsMtrAmtRate.getValue());
                            // END 2012/12/01 R.Jin [QC#60880,MOD]
                            aggMtrPrcList.add(aggPrc);
                        }
                    } else {
                        for (int idx = 0; idx < aggMtrPrcList.size(); idx++) {
                            HashMap<String, Object> aggPrc = (HashMap<String, Object>) aggMtrPrcList.get(idx);
                            // START 2012/12/01 R.Jin [QC#60880,MOD]
//                            if (aggPrc.containsKey(bllgMtrLbCd)) {
                            if (aggPrc.containsKey(bllgMtrLbCd.concat(tirNum))) {
//                                setValue(inPMsg.xxContrXsCopyList.no(xsCopyCnt).xsMtrAmtRate, (BigDecimal) aggPrc.get(bllgMtrLbCd));
                                setValue(inPMsg.xxContrXsCopyList.no(xsCopyCnt).xsMtrAmtRate, (BigDecimal) aggPrc.get(bllgMtrLbCd.concat(tirNum)));
                             // END 2012/12/01 R.Jin [QC#60880,MOD]
                                break;
                            }
                        }
                    }
                }
                // END   2021/12/02 R.Cabral [QC#59352,ADD]
                inPMsg.xxContrXsCopyList.setValidCount(++xsCopyCnt);
            }
        // Start 2012/12/01 R.Jin [QC#60880,MOD]
        }
        // END 2012/12/01 R.Jin [QC#60880,MOD]

        // END 2018/05/22 K.Kitachi [QC#26070, MOD]
        return inPMsg;
    }

    // START 2021/12/02 R.Cabral [QC#59352,ADD]
    private boolean isFleetOrAggMachine(String dsContrCatgCd, String dsContrDtlTpCd) {
        if ((DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && !DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd))
                || (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && !DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd))) {
            return true;
        }
        return false;
    }
    // END   2021/12/02 R.Cabral [QC#59352,ADD]

    private String callContrRenewalPreCheck(BigDecimal dsContrDtlPk, BigDecimal basePrcDealAmt, BigDecimal dsContrBllgMtrPk, BigDecimal xsMtrCopyQty, BigDecimal xsMtrAmtRate) {
        List<ContrRenewalPreCheckInfo> inBeanList = new ArrayList<ContrRenewalPreCheckInfo>();
        ContrRenewalPreCheckInfo inBean = new ContrRenewalPreCheckInfo();
        inBean.setGlblCmpyCd(getGlobalCompanyCode());
        inBean.setDsContrDtlPk(dsContrDtlPk);
        inBean.setBasePrcDealAmt(basePrcDealAmt);
        inBean.setDsContrBllgMtrPk(dsContrBllgMtrPk);
        inBean.setXsMtrCopyQty(xsMtrCopyQty);
        inBean.setXsMtrAmtRate(xsMtrAmtRate);
        inBeanList.add(inBean);

        NSXC001001ContrRenewalPreCheck calculator = new NSXC001001ContrRenewalPreCheck(inBeanList);
        calculator.contrRenewalPreCheck();
        for (ContrRenewalPreCheckInfo rsBean : inBeanList) {
            if (hasValue(rsBean.getContrRnwErrRsnCd())) {
                return rsBean.getContrRnwErrRsnCd();
            }
        }
        return null;
    }

    private void executeApi(NSZC046001PMsg pMsg) {
        NSZC046001 api = new NSZC046001();
        api.execute(pMsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);
    }

    private boolean validationCheck(NSAL0690CMsg cMsg, NSAL0690_BCMsg bCMsg) {
        if (!hasValue(bCMsg.xxThruDt_B1)) {
            bCMsg.xxThruDt_B1.setErrorInfo(1, NSAM0007E, new String[]{"New Period End"});
            return true;
        }
        if (ZYPDateUtil.compare(bCMsg.contrEffThruDt_B1.getValue(), bCMsg.xxThruDt_B1.getValue()) >= 0) {
            bCMsg.xxThruDt_B1.setErrorInfo(1, NSAM0284E, new String[]{"New Period End", "End Date"});
            return true;
        }
        // add start 2016/05/24 CSA Defect#447
        EndOfLifeBean infoBean = new EndOfLifeBean();
        infoBean.setGlblCmpyCd(cMsg.glblCmpyCd.getValue());
        infoBean.setSvcMachMstrPk(bCMsg.svcMachMstrPk_B1.getValue());
        infoBean.setEolDt(ZYPDateUtil.addDays(bCMsg.contrEffThruDt_B1.getValue(), BigDecimal.ONE.intValue()));
        if (!NSXC001001GetEndOfLifeInfo.getEndOfLifeInfo(infoBean)) {
            bCMsg.xxChkBox_B1.setErrorInfo(1, infoBean.getXxMsgId());
            return true;
        } else {
            if (ZYPConstant.FLG_OFF_N.equals(infoBean.getContrAvalFlg())) {
                bCMsg.xxChkBox_B1.setErrorInfo(1, NSAM0480E, new String[] {bCMsg.serNum_B1.getValue() });
                return true;
            }
        }
        // add end 2016/05/24 CSA Defect#447
        return false;
    }

    private boolean isApiErr(NSZC046001PMsg pMsg, NSAL0690CMsg cMsg) {
        BigDecimal dsContrPk = pMsg.dsContrPk.getValue();
        BigDecimal dsContrDtlPk;
        boolean setErrFlg;
        if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                if (dsContrPk.compareTo(cMsg.B.no(i).dsContrPk_B1.getValue()) != 0) {
                    continue;
                }
                setValue(cMsg.B.no(i).xxGenlFldAreaTxt_B1, getRtnMsg(msg.getXxMsgid(), msg.getXxMsgPrmArray()));
            }
            return true;
        }

        if (hasValue(pMsg.xxMsgId_HD)) {
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                if (dsContrPk.compareTo(cMsg.B.no(i).dsContrPk_B1.getValue()) != 0) {
                    continue;
                }
                setValue(cMsg.B.no(i).xxGenlFldAreaTxt_B1, pMsg.xxDsMultMsgDplyTxt_HD.getValue());
            }
            return true;
        }

        setErrFlg = false;
        NSZC046001_xxContrDtlListPMsg contrDtlPMsg;
        for (int idx = 0; idx < pMsg.xxContrDtlList.getValidCount(); idx++) {
            contrDtlPMsg = pMsg.xxContrDtlList.no(idx);
            dsContrDtlPk = contrDtlPMsg.dsContrDtlPk.getValue();
            if (!hasValue(contrDtlPMsg.xxMsgId_DT)) {
                continue;
            }

            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                if (dsContrPk.compareTo(cMsg.B.no(i).dsContrPk_B1.getValue()) != 0 || dsContrDtlPk.compareTo(cMsg.B.no(i).dsContrDtlPk_B1.getValue()) != 0) {
                    continue;
                }
                setValue(cMsg.B.no(i).xxGenlFldAreaTxt_B1, contrDtlPMsg.xxDsMultMsgDplyTxt_DT.getValue());
                setErrFlg = true;
            }
        }
        if (setErrFlg) {
            return true;
        }

        setErrFlg = false;
        NSZC046001_xxDsContrBllgMtrListPMsg contrBllgMtrPMsg;
        for (int idx = 0; idx < pMsg.xxDsContrBllgMtrList.getValidCount(); idx++) {
            contrBllgMtrPMsg = pMsg.xxDsContrBllgMtrList.no(idx);
            dsContrDtlPk = contrBllgMtrPMsg.dsContrDtlPk.getValue();
            if (!hasValue(contrBllgMtrPMsg.xxMsgId)) {
                continue;
            }

            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                if (dsContrPk.compareTo(cMsg.B.no(i).dsContrPk_B1.getValue()) != 0 || dsContrDtlPk.compareTo(cMsg.B.no(i).dsContrDtlPk_B1.getValue()) != 0) {
                    continue;
                }
                setValue(cMsg.B.no(i).xxGenlFldAreaTxt_B1, contrBllgMtrPMsg.xxDsMultMsgDplyTxt.getValue());
                setErrFlg = true;
            }
        }
        if (setErrFlg) {
            return true;
        }

        setErrFlg = false;
        NSZC046001_xxContrXsCopyListPMsg contrXsCopyPMsg;
        BigDecimal dsContrBllgMtrPk;
        Map<BigDecimal, BigDecimal> dsContrDtlMap = new HashMap<BigDecimal, BigDecimal>();
        for (int idx = 0; idx < pMsg.xxContrXsCopyList.getValidCount(); idx++) {
            contrXsCopyPMsg = pMsg.xxContrXsCopyList.no(idx);
            dsContrBllgMtrPk = contrXsCopyPMsg.dsContrBllgMtrPk.getValue();
            if (!hasValue(contrXsCopyPMsg.xxMsgId)) {
                continue;
            }

            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                if (dsContrPk.compareTo(cMsg.B.no(i).dsContrPk_B1.getValue()) != 0) {
                    continue;
                }

                if (dsContrDtlMap.containsKey(cMsg.B.no(i).dsContrDtlPk_B1.getValue())) {
                    continue;
                }

                DS_CONTR_BLLG_MTRTMsgArray tMsgArray = NSAL0690Query.getInstance().getDsContrBllgMtrList(cMsg.glblCmpyCd.getValue(), cMsg.B.no(i).dsContrDtlPk_B1.getValue());
                if (tMsgArray.getValidCount() == 0) {
                    continue;
                }

                for (int mIdx = 0; mIdx < tMsgArray.getValidCount(); mIdx++) {
                    if (tMsgArray.no(mIdx).dsContrBllgMtrPk.getValue().compareTo(dsContrBllgMtrPk) == 0) {
                        setValue(cMsg.B.no(i).xxGenlFldAreaTxt_B1, contrXsCopyPMsg.xxDsMultMsgDplyTxt.getValue());
                        dsContrDtlMap.put(cMsg.B.no(i).dsContrDtlPk_B1.getValue(), cMsg.B.no(i).dsContrDtlPk_B1.getValue());
                        setErrFlg = true;
                        break;
                    }
                }
            }
        }
        if (setErrFlg) {
            return true;
        }

        setErrFlg = false;
        NSZC046001_xxSvcMemoListPMsg svcMemoPMsg;
        for (int idx = 0; idx < pMsg.xxSvcMemoList.getValidCount(); idx++) {
            svcMemoPMsg = pMsg.xxSvcMemoList.no(idx);
            if (!hasValue(svcMemoPMsg.xxMsgId)) {
                continue;
            }

            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                if (dsContrPk.compareTo(cMsg.B.no(i).dsContrPk_B1.getValue()) != 0) {
                    continue;
                }
                setValue(cMsg.B.no(i).xxGenlFldAreaTxt_B1, svcMemoPMsg.xxDsMultMsgDplyTxt.getValue());
            }
            return true;
        }
        return false;
    }

    /**
     * Get Return Message
     * @param msgId String
     * @return String
     */
    private static String getRtnMsg(String msgId) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    /**
     * Get Return Message
     * @param msgId String
     * @param prm String[]
     * @return String
     */
    private static String getRtnMsg(String msgId, String[] prm) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId, prm);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    private BigDecimal getCalPrcAmtRate(String newEffThruDt, BigDecimal newBasePrcDealAmt, DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        if (!hasValue(newBasePrcDealAmt)) {
            return dsContrDtlTMsg.basePrcTermDealAmtRate.getValue();
        }
        CalcTermAmtBean inBean = new CalcTermAmtBean();
        inBean.setGlblCmpyCd(getGlobalCompanyCode());
        inBean.setXxModeCd(NSXC003001CalcTermAmt.MODE01_CONTRACT);
        inBean.setSlsDt(ZYPDateUtil.getSalesDate());
        inBean.setCcyCd(dsContrTMsg.ccyCd.getValue());
        inBean.setContrPrcEffFromDt(ZYPDateUtil.addDays(dsContrDtlTMsg.contrEffThruDt.getValue(), 1));
        inBean.setContrPrcEffThruDt(newEffThruDt);
        inBean.setContrCloDay(dsContrDtlTMsg.contrCloDay.getValue());
        inBean.setBllgCycleCd(dsContrDtlTMsg.baseBllgCycleCd.getValue());
        inBean.setBasePrcDealAmt(newBasePrcDealAmt);
        BigDecimal calcDealAmt = NSXC003001CalcTermAmt.calcTermAmt(inBean);
        if (!hasValue(calcDealAmt)) {
            calcDealAmt = dsContrDtlTMsg.basePrcTermDealAmtRate.getValue();
        }
        return calcDealAmt;
    }

    private String getMaxThruDt(NSAL0690CMsg cMsg, BigDecimal dsContrPk) {
        String maxDt = "";
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (dsContrPk.compareTo(cMsg.B.no(i).dsContrPk_B1.getValue()) != 0) {
                continue;
            }

            if (ZYPDateUtil.compare(maxDt, cMsg.B.no(i).xxThruDt_B1.getValue()) > 0) {
                continue;
            }
            maxDt = cMsg.B.no(i).xxThruDt_B1.getValue();
        }
        return maxDt;
    }

    // START 2017/09/28 K.Kojima [QC#18376,MOD]
    // private static BigDecimal calcRnwAmt(BigDecimal amt, BigDecimal ratio) {
    //     if (!hasValue(ratio)) {
    //         ratio = BigDecimal.ZERO;
    //     }
    //     BigDecimal upRatio = BIGDECIMAL_100.add(ratio).divide(BIGDECIMAL_100);
    //     return amt.multiply(upRatio);
    // }
    // START 2018/05/22 K.Kitachi [QC#26070, MOD]
//    private static BigDecimal calcRnwAmtForUsage(String glblCmpyCd, String rnwPrcMethCd, String dsContrCatgCd, BigDecimal dsContrDtlPk, String bllgMtrLbCd, BigDecimal amt, BigDecimal ratio, boolean machineFlag) {
    private static BigDecimal calcRnwAmtForUsage(String glblCmpyCd, String rnwPrcMethCd, String dsContrCatgCd, BigDecimal dsContrDtlPk, String bllgMtrLbCd, BigDecimal amt, BigDecimal ratio, boolean machineFlag, int scale) {
    // END 2018/05/22 K.Kitachi [QC#26070, MOD]
        BigDecimal rnwAmt = BigDecimal.ZERO;
        if (!hasValue(ratio)) {
            ratio = BigDecimal.ZERO;
        }
        if (hasValue(rnwPrcMethCd) && rnwPrcMethCd.equals(RNW_PRC_METH.MODEL_PERCENT)) {
            // START 2017/12/06 [QC#22891,MOD]
            //if (machineFlag) {
            if (machineFlag && !DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            // END 2017/12/06 [QC#22891,MOD]
                ratio = NSAL0690Query.getInstance().getUplftMtrPrcUpRatio(glblCmpyCd, dsContrDtlPk, bllgMtrLbCd);
            } else {
                // START 2017/12/06 [QC#22891,MOD]
                //ratio = NSAL0690Query.getInstance().getUplftMtrPrcUpRatioForLine(glblCmpyCd, dsContrDtlPk, bllgMtrLbCd);
                ratio = ZYPCodeDataUtil.getNumConstValue("DEF_MDL_RULE_USAGE", glblCmpyCd);
                // END 2017/12/06 [QC#22891,MOD]
            }
        }
        BigDecimal upRatio = BIGDECIMAL_100.add(ratio);
        rnwAmt =  amt.multiply(upRatio).divide(BIGDECIMAL_100);
        // START 2018/05/22 K.Kitachi [QC#26070, MOD]
//        return rnwAmt;
        return rnwAmt.setScale(scale, RoundingMode.HALF_UP);
        // END 2018/05/22 K.Kitachi [QC#26070, MOD]
    }

    // END 2017/09/28 K.Kojima [QC#18376,MOD]

    private DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    private DS_CONTR_DTLTMsg getDsContrDtl(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    private void setUsgRnwFlg(NSZC046001PMsg pMsg, BigDecimal dsContrDtlPk) {
        if (!hasValue(dsContrDtlPk)) {
            return;
        }

        for (int i = 0; i < pMsg.xxContrDtlList.getValidCount(); i++) {
            if (dsContrDtlPk.compareTo(pMsg.xxContrDtlList.no(i).dsContrDtlPk.getValue()) == 0) {
                setValue(pMsg.xxContrDtlList.no(i).rnwUsgFlg, ZYPConstant.FLG_ON_Y);
            }
        }
    }

    private void setSuccessMsg(NSZC046001PMsg pMsg, NSAL0690CMsg cMsg) {
        BigDecimal dsContrPk = pMsg.dsContrPk.getValue();
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (dsContrPk.compareTo(cMsg.B.no(i).dsContrPk_B1.getValue()) != 0) {
                continue;
            }

            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.B.no(i).xxChkBox_B1.getValue())) {
                continue;
            }

            setValue(cMsg.B.no(i).xxGenlFldAreaTxt_B1, RTRN_MSG_SUCCESS);
        }
    }

    // add start 2016/06/03 CSA Defect#1523, 4624
    private boolean callContrTrkAPI(NSAL0690CMsg cMsg, BigDecimal dsContrPk) {
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String userId = getContextUserInfo().getUserId();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        String stsMemoRsnCd = DS_CONTR_TRK_RSN.CONTRACT_MODE_CHANGE;
        String stsMemoTxt = cMsg.svcCmntTxt_H1.getValue();
        DS_CONTR_TRKTMsg dsContrTrkTMsg = new DS_CONTR_TRKTMsg();
        if (hasValue(stsMemoTxt) && stsMemoTxt.length() > dsContrTrkTMsg.getAttr("stsMemoTxt").getDigit()) {
            stsMemoTxt = stsMemoTxt.substring(0, dsContrTrkTMsg.getAttr("stsMemoTxt").getDigit());
        }
        if (!NSXC001001ContractTracking.callContrTrk(glblCmpyCd, ContrTrkProcMode.USER_OPERATION.code, dsContrPk, userId, slsDt, stsMemoRsnCd, stsMemoTxt, ONBATCH_TYPE.ONLINE)) {
            cMsg.setMessageInfo(NSXC001001ContractTracking.ERR_MSG_ID);
            return false;
        }
        return true;
    }
    // add end 2016/06/03 CSA Defect#1523, 4624

    // START 2017/02/13 K.Kitachi [QC#17410, ADD]
    private MDL_NMTMsg getMdlNmTMsg(NSAL0690CMsg cMsg) {
        String spclFltMdseCd = ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, cMsg.glblCmpyCd.getValue());
        if (!hasValue(spclFltMdseCd)) {
            return null;
        }
        NSZC048001 svcMdlApi = new NSZC048001();
        NSZC048001PMsg svcMdlPMsg = new NSZC048001PMsg();
        setValue(svcMdlPMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        setValue(svcMdlPMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue(), BUSINESS_ID));
        setValue(svcMdlPMsg.prntMdseCd, spclFltMdseCd);
        svcMdlApi.execute(svcMdlPMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(svcMdlPMsg)) {
            return null;
        }
        if (!hasValue(svcMdlPMsg.mdlId)) {
            return null;
        }
        MDL_NMTMsg inMsg = new MDL_NMTMsg();
        setValue(inMsg.t_GlblCmpyCd, cMsg.glblCmpyCd.getValue());
        setValue(inMsg.t_MdlId, svcMdlPMsg.mdlId);
        return (MDL_NMTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private boolean createCfsContrPrcUplft(NSZC046001PMsg pMsg, NSAL0690CMsg cMsg, String dsContrModTxt, String cfsDlrCd, MDL_NMTMsg mdlNmTMsg) {
        CFS_CONTR_PRC_UPLFTTMsg inMsg;
        DS_CONTR_DTLTMsg dsContrDtlTMsg;
        DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg;
        for (int i = 0; i < pMsg.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg xxContrDtl = pMsg.xxContrDtlList.no(i);
            // Base
            if (xxContrDtl.rnwBaseFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                dsContrDtlTMsg = getDsContrDtl(pMsg.glblCmpyCd.getValue(), xxContrDtl.dsContrDtlPk.getValue());
                if (dsContrDtlTMsg != null && isCfsDlr(cMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.baseBillToCustCd.getValue(), cfsDlrCd)) {
                    inMsg = new CFS_CONTR_PRC_UPLFTTMsg();
                    setCommonParam(inMsg, pMsg, xxContrDtl, dsContrModTxt);
                    if (isRegOrAgg(pMsg.dsContrCatgCd.getValue(), xxContrDtl.dsContrDtlTpCd.getValue())) {
                        setRegAndAggBaseParam(inMsg, pMsg, xxContrDtl);
                        if (!insertCfsContrPrcUplft(inMsg, pMsg, cMsg)) {
                            return false;
                        }
                    }
                    if (isFltLine(pMsg.dsContrCatgCd.getValue(), xxContrDtl.dsContrDtlTpCd.getValue())) {
                        setFltBaseParam(inMsg, pMsg, xxContrDtl, mdlNmTMsg);
                        if (!insertCfsContrPrcUplft(inMsg, pMsg, cMsg)) {
                            return false;
                        }
                    }
                }
            }
            // Usage
            if (xxContrDtl.rnwUsgFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                for (int j = 0; j < pMsg.xxDsContrBllgMtrList.getValidCount(); j++) {
                    NSZC046001_xxDsContrBllgMtrListPMsg xxDsContrBllgMtr = pMsg.xxDsContrBllgMtrList.no(j);
                    if (xxContrDtl.dsContrDtlPk.getValue().compareTo(xxDsContrBllgMtr.dsContrDtlPk.getValue()) != 0) {
                        continue;
                    }
                    dsContrBllgMtrTMsg = getDsContrBllgMtr(cMsg.glblCmpyCd.getValue(), xxDsContrBllgMtr.dsContrBllgMtrPk.getValue());
                    if (dsContrBllgMtrTMsg != null && isCfsDlr(cMsg.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue(), cfsDlrCd)) {
                        inMsg = new CFS_CONTR_PRC_UPLFTTMsg();
                        setCommonParam(inMsg, pMsg, xxContrDtl, dsContrModTxt);
                        if (isRegOrAgg(pMsg.dsContrCatgCd.getValue(), xxContrDtl.dsContrDtlTpCd.getValue())) {
                            setRegAndAggUsgParam(inMsg, pMsg, xxDsContrBllgMtr);
                            if (!insertCfsContrPrcUplft(inMsg, pMsg, cMsg)) {
                                return false;
                            }
                        }
                        if (isFltLine(pMsg.dsContrCatgCd.getValue(), xxContrDtl.dsContrDtlTpCd.getValue())) {
                            setFltUsgParam(inMsg, pMsg, xxDsContrBllgMtr, mdlNmTMsg);
                            if (!insertCfsContrPrcUplft(inMsg, pMsg, cMsg)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean isCfsDlr(String glblCmpyCd, String billToCustCd, String cfsDlrCd) {
        BigDecimal count = NSAL0690Query.getInstance().countCfsDlr(glblCmpyCd, billToCustCd, cfsDlrCd);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean isRegOrAgg(String dsContrCatgCd, String dsContrDtlTpCd) {
        if (dsContrCatgCd.equals(DS_CONTR_CATG.REGULAR) || (dsContrCatgCd.equals(DS_CONTR_CATG.AGGREGATE) && !dsContrDtlTpCd.equals(DS_CONTR_DTL_TP.AGGREGATE))) {
            return true;
        }
        return false;
    }

    private boolean isFltLine(String dsContrCatgCd, String dsContrDtlTpCd) {
        if (dsContrCatgCd.equals(DS_CONTR_CATG.FLEET) && dsContrDtlTpCd.equals(DS_CONTR_DTL_TP.FLEET)) {
            return true;
        }
        return false;
    }

    private void setCommonParam(CFS_CONTR_PRC_UPLFTTMsg inMsg, NSZC046001PMsg pMsg, NSZC046001_xxContrDtlListPMsg xxContrDtl, String dsContrModTxt) {
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        BigDecimal cfsContrPrcUplftPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_CONTR_PRC_UPLFT_SQ);
        setValue(inMsg.cfsContrPrcUplftPk, cfsContrPrcUplftPk);
        setValue(inMsg.dsContrPk, pMsg.dsContrPk);
        setValue(inMsg.dsContrNum, pMsg.dsContrNum);
        setValue(inMsg.dsContrDtlPk, xxContrDtl.dsContrDtlPk);
        setValue(inMsg.dsContrModTxt, dsContrModTxt);
        setValue(inMsg.dsContrCatgCd, pMsg.dsContrCatgCd);
        setValue(inMsg.cfsProcStsCd, CFS_PROC_STS.IN_COMPLETED);
    }

    private void setRegAndAggBaseParam(CFS_CONTR_PRC_UPLFTTMsg inMsg, NSZC046001PMsg pMsg, NSZC046001_xxContrDtlListPMsg xxContrDtl) {
        Map<String, Object> map = NSAL0690Query.getInstance().getRegAndAggBase(pMsg.glblCmpyCd.getValue(), xxContrDtl.dsContrDtlPk.getValue());
        if (map != null) {
            setValue(inMsg.uplftEffFromDt, (String) map.get("UPLFT_EFF_FROM_DT"));
            setValue(inMsg.serNum, (String) map.get("SER_NUM"));
            setValue(inMsg.mdlId, (BigDecimal) map.get("MDL_ID"));
            setValue(inMsg.mdlNm, (String) map.get("MDL_NM"));
            setValue(inMsg.prcUpRatio, (BigDecimal) map.get("PRC_UP_RATIO"));
            setValue(inMsg.dsContrDtlTpCd, (String) map.get("DS_CONTR_DTL_TP_CD"));
            setValue(inMsg.svcMachMstrPk, (BigDecimal) map.get("SVC_MACH_MSTR_PK"));
        }
        setValue(inMsg.baseChrgFlg, ZYPConstant.FLG_ON_Y);
        setValue(inMsg.usgChrgFlg, ZYPConstant.FLG_OFF_N);
    }

    private void setRegAndAggUsgParam(CFS_CONTR_PRC_UPLFTTMsg inMsg, NSZC046001PMsg pMsg, NSZC046001_xxDsContrBllgMtrListPMsg xxDsContrBllgMtr) {
        Map<String, Object> map = NSAL0690Query.getInstance().getRegAndAggUsg(pMsg.glblCmpyCd.getValue(), xxDsContrBllgMtr.dsContrDtlPk.getValue(), xxDsContrBllgMtr.dsContrBllgMtrPk.getValue());
        if (map != null) {
            setValue(inMsg.uplftEffFromDt, (String) map.get("UPLFT_EFF_FROM_DT"));
            setValue(inMsg.serNum, (String) map.get("SER_NUM"));
            setValue(inMsg.mdlId, (BigDecimal) map.get("MDL_ID"));
            setValue(inMsg.mdlNm, (String) map.get("MDL_NM"));
            setValue(inMsg.prcUpRatio, (BigDecimal) map.get("PRC_UP_RATIO"));
            setValue(inMsg.dsContrDtlTpCd, (String) map.get("DS_CONTR_DTL_TP_CD"));
            setValue(inMsg.svcMachMstrPk, (BigDecimal) map.get("SVC_MACH_MSTR_PK"));
            setValue(inMsg.dsContrBllgMtrPk, (BigDecimal) map.get("DS_CONTR_BLLG_MTR_PK"));
            setValue(inMsg.mtrLbCd, (String) map.get("MTR_LB_CD"));
        }
        setValue(inMsg.baseChrgFlg, ZYPConstant.FLG_OFF_N);
        setValue(inMsg.usgChrgFlg, ZYPConstant.FLG_ON_Y);
    }

    private void setFltBaseParam(CFS_CONTR_PRC_UPLFTTMsg inMsg, NSZC046001PMsg pMsg, NSZC046001_xxContrDtlListPMsg xxContrDtl, MDL_NMTMsg mdlNmTMsg) {
        Map<String, Object> map = NSAL0690Query.getInstance().getFltBase(pMsg.glblCmpyCd.getValue(), xxContrDtl.dsContrDtlPk.getValue());
        if (map != null) {
            setValue(inMsg.uplftEffFromDt, (String) map.get("UPLFT_EFF_FROM_DT"));
            setValue(inMsg.prcUpRatio, (BigDecimal) map.get("PRC_UP_RATIO"));
            setValue(inMsg.dsContrDtlTpCd, (String) map.get("DS_CONTR_DTL_TP_CD"));
        }
        if (mdlNmTMsg != null) {
            setValue(inMsg.mdlId, mdlNmTMsg.t_MdlId);
            setValue(inMsg.mdlNm, mdlNmTMsg.t_MdlNm);
        }
        String fleetSerNum = SFX_FLT_SER_NUM + inMsg.dsContrNum.getValue();
        setValue(inMsg.fleetSerNum, fleetSerNum);
        setValue(inMsg.baseChrgFlg, ZYPConstant.FLG_ON_Y);
        setValue(inMsg.usgChrgFlg, ZYPConstant.FLG_OFF_N);
    }

    private void setFltUsgParam(CFS_CONTR_PRC_UPLFTTMsg inMsg, NSZC046001PMsg pMsg, NSZC046001_xxDsContrBllgMtrListPMsg xxDsContrBllgMtr, MDL_NMTMsg mdlNmTMsg) {
        Map<String, Object> map = NSAL0690Query.getInstance().getFltUsg(pMsg.glblCmpyCd.getValue(), xxDsContrBllgMtr.dsContrDtlPk.getValue(), xxDsContrBllgMtr.dsContrBllgMtrPk.getValue());
        if (map != null) {
            setValue(inMsg.uplftEffFromDt, (String) map.get("UPLFT_EFF_FROM_DT"));
            setValue(inMsg.prcUpRatio, (BigDecimal) map.get("PRC_UP_RATIO"));
            setValue(inMsg.dsContrDtlTpCd, (String) map.get("DS_CONTR_DTL_TP_CD"));
            setValue(inMsg.dsContrBllgMtrPk, (BigDecimal) map.get("DS_CONTR_BLLG_MTR_PK"));
            setValue(inMsg.mtrLbCd, (String) map.get("MTR_LB_CD"));
        }
        if (mdlNmTMsg != null) {
            setValue(inMsg.mdlId, mdlNmTMsg.t_MdlId);
            setValue(inMsg.mdlNm, mdlNmTMsg.t_MdlNm);
        }
        String fleetSerNum = SFX_FLT_SER_NUM + inMsg.dsContrNum.getValue();
        setValue(inMsg.fleetSerNum, fleetSerNum);
        setValue(inMsg.baseChrgFlg, ZYPConstant.FLG_OFF_N);
        setValue(inMsg.usgChrgFlg, ZYPConstant.FLG_ON_Y);
    }

    private boolean insertCfsContrPrcUplft(CFS_CONTR_PRC_UPLFTTMsg inMsg, NSZC046001PMsg pMsg, NSAL0690CMsg cMsg) {
        EZDTBLAccessor.insert(inMsg);
        String rtnCd = inMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            setInsertErrCfsContrPrcUplft(pMsg, cMsg);
            return false;
        }
        return true;
    }

    private void setInsertErrCfsContrPrcUplft(NSZC046001PMsg pMsg, NSAL0690CMsg cMsg) {
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (pMsg.dsContrPk.getValue().compareTo(cMsg.B.no(i).dsContrPk_B1.getValue()) != 0) {
                continue;
            }
            setValue(cMsg.B.no(i).xxGenlFldAreaTxt_B1, getRtnMsg(NSAM0032E, new String[] {MSG_INFO_CFS_CONTR_PRC_UPLFT }));
        }
    }

    private DS_CONTR_BLLG_MTRTMsg getDsContrBllgMtr(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        return (DS_CONTR_BLLG_MTRTMsg) EZDTBLAccessor.findByKey(inMsg);
    }
    // END 2017/02/13 K.Kitachi [QC#17410, ADD]

    // START 2018/06/15 K.Kojima [QC#26702,DEL]
    // // START 2017/10/02 M.Kidokoro [QC#18290, ADD]
    // private boolean isPoDtErrForBase(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String contrEffThruDt) {
    //     boolean errCheck = true;
    //     List<String> poDtList = getPoDtListForBase(glblCmpyCd, dsContrPk, dsContrDtlPk);
    //     for (String poDt : poDtList) {
    //         if (ZYPCommonFunc.hasValue(poDt) && poDt.compareTo(contrEffThruDt) < 0) {
    //             errCheck = false;
    //             break;
    //         }
    //     }
    //     return errCheck;
    // }
    // 
    // private boolean isPoDtErrForUsg(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String contrEffThruDt) {
    //     boolean errCheck = true;
    //     List<String> poDtList = getPoDtListForUsg(glblCmpyCd, dsContrPk, dsContrDtlPk, dsContrBllgMtrPk);
    //     for (String poDt : poDtList) {
    //         if (ZYPCommonFunc.hasValue(poDt) && poDt.compareTo(contrEffThruDt) < 0) {
    //             errCheck = false;
    //             break;
    //         }
    //     }
    //     return errCheck;
    // }
    // 
    // private List<String> getPoDtListForBase(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
    //     List<String> poDtList = (List<String>) NSAL0690Query.getInstance().getPoDtListForBase(glblCmpyCd, dsContrPk, dsContrDtlPk);
    //     return poDtList;
    // }
    // 
    // private List<String> getPoDtListForUsg(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
    //     List<String> poDtList = (List<String>) NSAL0690Query.getInstance().getPoDtListForUsg(glblCmpyCd, dsContrPk, dsContrDtlPk, dsContrBllgMtrPk);
    //     return poDtList;
    // }
    // // END 2017/10/02 M.Kidokoro [QC#18290, ADD]
    // END 2018/06/15 K.Kojima [QC#26702,DEL]

    // START 2018/06/15 K.Kojima [QC#26702,DEL]
    // // START 2018/05/22 K.Kitachi [QC#26210, ADD]
    // private boolean updateDsContrDtlSts(NSAL0690CMsg cMsg, BigDecimal dsContrDtlPk, String dsContrDtlStsCd) {
    //     DS_CONTR_DTLTMsg updTMsg = getDsContrDtlForUpdate(cMsg.glblCmpyCd.getValue(), dsContrDtlPk);
    //     if (updTMsg == null) {
    //         cMsg.setMessageInfo(ZZZM9004E);
    //         return false;
    //     }
    //     setValue(updTMsg.dsContrDtlStsCd, dsContrDtlStsCd);
    //     EZDTBLAccessor.update(updTMsg);
    //     if (!RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
    //         cMsg.setMessageInfo(NSAM0031E, new String[] {"DS_CONTR_DTL" });
    //         return false;
    //     }
    //     return true;
    // }
    // 
    // private DS_CONTR_DTLTMsg getDsContrDtlForUpdate(String glblCmpyCd, BigDecimal dsContrDtlPk) {
    //     DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
    //     setValue(tMsg.glblCmpyCd, glblCmpyCd);
    //     setValue(tMsg.dsContrDtlPk, dsContrDtlPk);
    //     return (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
    // }
    // // END 2018/05/22 K.Kitachi [QC#26210, ADD]
    // END 2018/06/15 K.Kojima [QC#26702,DEL]

    // START 2018/05/18 K.Kojima [QC#26187,ADD]
    private boolean checkAccessoryThruDt(NSAL0690CMsg cMsg) {
        boolean result = true;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0690_BCMsg accBCMsg = cMsg.B.no(i);
            if (DS_CONTR_CATG.FLEET.equals(accBCMsg.dsContrCatgCd_B1.getValue())) {
                continue;
            }
            if (!CHKBOX_ON_Y.equals(accBCMsg.xxChkBox_B1.getValue())) {
                continue;
            }
            if (hasValue(accBCMsg.dsContrDtlTpCd_B1) && DS_CONTR_DTL_TP.ACCESSORIES.equals(accBCMsg.dsContrDtlTpCd_B1.getValue())) {
                DS_CONTR_DTLTMsg accTMsg = getDsContrDtl(cMsg.glblCmpyCd.getValue(), accBCMsg.dsContrDtlPk_B1.getValue());
                if (accTMsg == null) {
                    continue;
                }
                String machThruDt = null;
                for (int m = 0; m < cMsg.B.getValidCount(); m++) {
                    NSAL0690_BCMsg machBCMsg = cMsg.B.no(m);
                    if (machBCMsg.dsContrDtlPk_B1.getValue().compareTo(accTMsg.prntDsContrDtlPk.getValue()) != 0) {
                        continue;
                    }
                    if (!CHKBOX_ON_Y.equals(machBCMsg.xxChkBox_B1.getValue())) {
                        continue;
                    }
                    machThruDt = machBCMsg.xxThruDt_B1.getValue();
                    break;
                }
                if (machThruDt == null) {
                    DS_CONTR_DTLTMsg machTMsg = getDsContrDtl(cMsg.glblCmpyCd.getValue(), accTMsg.prntDsContrDtlPk.getValue());
                    if (machTMsg == null) {
                        continue;
                    }
                    machThruDt = machTMsg.contrEffThruDt.getValue();
                }
                if (accBCMsg.xxThruDt_B1.getValue().compareTo(machThruDt) > 0) {
                    accBCMsg.xxThruDt_B1.setErrorInfo(1, NSAM0285E, new String[] {"New Period End", "Machine End Date" });
                    result = false;
                }
            }
        }
        return result;
    }
    // END 2018/05/18 K.Kojima [QC#26187,ADD]

    // START 2018/06/15 K.Kojima [QC#26702,DEL]
    // // START 2018/05/31 K.Kitachi [QC#26210, ADD]
    // private boolean updateDsContrPrcEffSts(NSAL0690CMsg cMsg, BigDecimal dsContrPk) {
    // 
    //     // get Renewal Hold Traget
    //     List<BigDecimal> prcEffPkList = NSAL0690Query.getInstance().getRnwHoldPoTrgt(cMsg.glblCmpyCd.getValue(), dsContrPk);
    // 
    //     // update DS Contract Price Effective
    //     for (BigDecimal prcEffPk : prcEffPkList) {
    //         DS_CONTR_PRC_EFFTMsg updTMsg = NSAL0690Query.getInstance().getDsContrPrcEff(cMsg.glblCmpyCd.getValue(), prcEffPk);
    //         setValue(updTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO);
    //         EZDTBLAccessor.update(updTMsg);
    //         if (!RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
    //             cMsg.setMessageInfo(NSAM0031E, new String[] {"DS_CONTR_PRC_EFF" });
    //             return false;
    //         }
    //     }
    //     return true;
    // }
    // // END 2018/05/31 K.Kitachi [QC#26210, ADD]
    // END 2018/06/15 K.Kojima [QC#26702,DEL]

    // START 2018/06/18 U.Kim [QC#24903,ADD]
    // START 2018/12/19 W.Honda [QC#29636,MOD]
//    private BigDecimal recalcValueForMaxRate(BigDecimal value, DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, int digitNum) {
    private BigDecimal recalcValueForMaxRate(BigDecimal value, DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, int digitNum, boolean baseFlg) {
        // END 2018/12/19 W.Honda [QC#29636,MOD]
        if (value == null) {
            return null;
        }

        // START 2018/12/19 W.Honda [QC#29636,MOD]
//        BigDecimal maxPrcUpRatio = null;
//        if (hasValue(dsContrDtlTMsg.maxPrcUpRatio)) {
//            maxPrcUpRatio = dsContrDtlTMsg.maxPrcUpRatio.getValue();
//        } else if (hasValue(dsContrTMsg.maxPrcUpRatio)) {
//            maxPrcUpRatio = dsContrTMsg.maxPrcUpRatio.getValue();
//        }

        NSAL0690Query query = NSAL0690Query.getInstance();
        BigDecimal maxPrcUpRatio = query.getMaxPrcUpRatioFromRnwEscl(dsContrTMsg, dsContrDtlTMsg, baseFlg);
        // END 2018/12/19 W.Honda [QC#29636,MOD]
        if (maxPrcUpRatio == null) {
            return value;
        }
        return value.add(value.multiply(maxPrcUpRatio).divide(BigDecimal.valueOf(100), digitNum, BigDecimal.ROUND_HALF_UP));
    }

    boolean callSumAggregateAPI(String glblCmpyCd, NSAL0690_ACMsg aCMsg , String salesDate){
        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrPk, aCMsg.dsContrPk_A1.getValue());
        dsContrTMsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKey(dsContrTMsg);
        if (dsContrTMsg == null) {
            return false;
        }
        if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
            return true;
        }

        DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
        dsContrDtlTMsg.setSQLID("006");
        dsContrDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsContrDtlTMsg.setConditionValue("dsContrPk01", aCMsg.dsContrPk_A1.getValue());
        dsContrDtlTMsg.setConditionValue("dsContrDtlTpCd01", DS_CONTR_DTL_TP.AGGREGATE);
        DS_CONTR_DTLTMsgArray outTmsgArray = (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(dsContrDtlTMsg);
        if (outTmsgArray.getValidCount() == 0) {
            return false;
        }
        dsContrDtlTMsg = outTmsgArray.no(0);

        NSZC047011PMsg pMsg = new NSZC047011PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "11");
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk.getValue());
        NSZC047001 api = new NSZC047001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            return false;
        }
        return true;
    }
    // END 2018/06/18 U.Kim [QC#24903,ADD]

    // START 2022/06/01 [QC#59973, ADD]
    private boolean callSchdAgmtAdjContrApi(String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal dsContrPk) {
        // Create Api
        NWZC194001PMsg apiPMsg = new NWZC194001PMsg();
        setValue(apiPMsg.glblCmpyCd, glblCmpyCd);
        setValue(apiPMsg.xxModeCd, NWZC194001Constant.MODE_RENEWAL);
        setValue(apiPMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(apiPMsg.dsContrPk, dsContrPk);
        // Call Api
        new NWZC194001().execute(apiPMsg, ONBATCH_TYPE.ONLINE);
        // Check returns
        if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
            return false;
        }
        return true;
    }
    // END 2022/06/01 [QC#59973, ADD]

    // START 2024/04/02 T.Nagae [QC#63552, ADD]
    private boolean createRenewalLetterWork(NSAL0690CMsg cMsg) {
        NSZC100001PMsg letterForNewPMsg = new NSZC100001PMsg();
        BigDecimal preDsContrPk = BigDecimal.ZERO;
        BigDecimal curDsContrPk = BigDecimal.ZERO;
        String dsContrNum = null;
        String preDsContrNum = null;
        String printOldPrcFlg = null;
        String dsAcctNum = null;
        String billToCustCd = null;
        BigDecimal curDsContrDtlPk = BigDecimal.ZERO;
        boolean procFlg = false;
        String slsDt = ZYPDateUtil.getSalesDate();

        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (FLG_ON_Y.equals(cMsg.B.no(i).xxChkBox_B1.getValue())) {
                dsContrDtlPkList.add(cMsg.B.no(i).dsContrDtlPk_B1.getValue());
            }
        }

        // *******************************************
        // 4.1.Price Renewal Letter Work request
        // *******************************************
        List<Map<String, Object>> letterListForNew = NSAL0690Query.getInstance().getRenewalLetterList(cMsg.glblCmpyCd.getValue(), slsDt, dsContrDtlPkList);
        for (int i = 0; i < letterListForNew.size(); i++) {
            HashMap<String, Object> letterForNew = (HashMap<String, Object>) letterListForNew.get(i);
            procFlg = true;
            curDsContrPk = (BigDecimal) letterForNew.get("DS_CONTR_PK");
            dsContrNum = (String) letterForNew.get("DS_CONTR_NUM");
            curDsContrDtlPk = (BigDecimal) letterForNew.get("DS_CONTR_DTL_PK");
            printOldPrcFlg = (String) letterForNew.get("PRINT_OLD_PRC_FLG");
            dsAcctNum = (String) letterForNew.get("DS_ACCT_NUM");
            billToCustCd = (String) letterForNew.get("ALT_PAYER_CUST_CD");
            if (i != 0 && curDsContrPk.compareTo(preDsContrPk) != 0) {
                // Call Contract Agreement Letter Creation API
                if (!callLetterApi(letterForNewPMsg, cMsg, preDsContrNum)) {
                    return false;
                }
                letterForNewPMsg = new NSZC100001PMsg();
            }
            // set api param
            setLetterApiParam(letterForNewPMsg, curDsContrPk, curDsContrDtlPk, printOldPrcFlg, dsAcctNum, billToCustCd, cMsg.glblCmpyCd.getValue(), slsDt);
            preDsContrPk = curDsContrPk;
            preDsContrNum = dsContrNum;
        }

        if (procFlg) {
            // Call Contract Agreement Letter Creation API
            if (!callLetterApi(letterForNewPMsg, cMsg, preDsContrNum)) {
                return false;
            }
        }

        return true;
    }

    private boolean callLetterApi(NSZC100001PMsg pMsg, NSAL0690CMsg cMsg, String dsContrNum) {
        new NSZC100001().execute(pMsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                if (FLG_ON_Y.equals(cMsg.B.no(i).xxChkBox_B1.getValue())) {
                    cMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                }
            }
            return false;
        }
        return true;
    }

    private void setLetterApiParam(NSZC100001PMsg letterForNewPMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String printOldPrcFlg, String dsAcctNum, String billToCustCd, String glblCmpyCd, String slsDt) {
        String rptId = null;

        // check Po Required
        boolean poReq = NSXC001001ContrValidation.checkPoRequired(glblCmpyCd, slsDt, dsAcctNum, billToCustCd, ONBATCH_TYPE.ONLINE);

        setValue(letterForNewPMsg.glblCmpyCd, glblCmpyCd);
        setValue(letterForNewPMsg.slsDt, slsDt);
        setValue(letterForNewPMsg.bizAppId, NSZC100001_BIZ_APP_ID_RNW);
        setValue(letterForNewPMsg.otptOpCd, OTPT_OP_CD);
        setValue(letterForNewPMsg.dsContrPk, dsContrPk);
        if (ZYPConstant.FLG_ON_Y.equals(printOldPrcFlg)) {
            if (poReq) {
                rptId = RPT_ID_NSAF0050;
            } else {
                rptId = RPT_ID_NSAF0030;
            }
        } else {
            if (poReq) {
                rptId = RPT_ID_NSAF0060;
            } else {
                rptId = RPT_ID_NSAF0040;
            }
        }
        setValue(letterForNewPMsg.rptId, rptId);
        int cnt = letterForNewPMsg.dsContrDtlPkList.getValidCount();
        setValue(letterForNewPMsg.dsContrDtlPkList.no(cnt).dsContrDtlPk, dsContrDtlPk);
        letterForNewPMsg.dsContrDtlPkList.setValidCount(cnt + 1);
    }
    // END 2024/04/02 T.Nagae [QC#63552, ADD]
    
}
