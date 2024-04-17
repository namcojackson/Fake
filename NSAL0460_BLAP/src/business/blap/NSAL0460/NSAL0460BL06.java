/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0460;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSAL0460.common.NSAL0460CommonLogic;
import static business.blap.NSAL0460.constant.NSAL0460Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import business.parts.NSZC010001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC010001.NSZC010001;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001SvcPhysMtrRead;

import business.db.SVC_MEMOTMsg;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.api.S21ApiInterface;

/**
 *<pre>
 * Start Read Capture
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Hitachi         T.Iwamoto       Create          N/A
 * 2015/11/27   Hitachi         T.Iwamoto       Update          QC#1235
 * 2015/12/17   Hitachi         K.Kasai         Update          QC#2076
 * 2016/01/26   Hitachi         T.Tomita        Update          QC#2883
 * 2016/02/12   Hitachi         A.Kohinata      Update          QC#2886
 * 2016/02/26   Hitachi         K.Kasai         Update          QC#2684
 * 2016/12/16   Hitachi         K.Ochiai        Update          QC#16584
 * 2017/01/04   Hitachi         K.Ochiai        Update          QC#16584
 * 2017/07/06   Hitachi         K.Kitachi       Update          QC#18292
 * 2018/02/08   Hitachi         M.Kidokoro      Update          QC#18145
 * 2022/06/22   CITS            E.Sanchez       Update          QC#59804
 * 2022/08/05   CITS            E.Sanchez       Update          QC#60357
 * 2022/11/04   CITS            E.Sanchez       Update          QC#60775
 * 2024/02/15   Hitachi         K.Watanabe      Update          QC#63529
 *</pre>
 */
public class NSAL0460BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0460Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0460Scrn00_CMN_Submit((NSAL0460CMsg) cMsg, (NSAL0460SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Submit)
     * @param cMsg NSAL0460CMsg
     * @param sMsg NSAL0460SMsg
     */
    private void doProcess_NSAL0460Scrn00_CMN_Submit(NSAL0460CMsg cMsg, NSAL0460SMsg sMsg) {

        NSAL0460CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        // [QC#1235,MOD]START
        cMsg.setCommitSMsg(true);
        // [QC#1235,MOD]END

        if (!checkInputData(cMsg, sMsg)) {
            return;
        }

        // [QC#2886,MOD]START
        List<Integer> chkBoxOffList = new ArrayList<Integer>();
        doUpdateMeterRead(cMsg, sMsg, chkBoxOffList);
        // [QC#2886,MOD]END

        // START 2018/02/08 M.Kidokoro [QC#18145,DEL]
//        insertSvcMemo(cMsg, sMsg);
        // END 2018/02/08 M.Kidokoro [QC#18145,DEL]

        // [QC#2886,ADD]START
        for (int k = 0; k < chkBoxOffList.size(); k++) {
            sMsg.A.no(chkBoxOffList.get(k)).xxChkBox.setValue(ZYPConstant.FLG_OFF_N);
        }
        cMsg.setCommitSMsg(true);
        showTargetPage(cMsg, sMsg, cMsg.xxPageShowToNum.getValueInt() - 1);
        // [QC#2886,ADD]END

        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NSAM0200I);
        }
    }

    /**
     * check Input
     * @param sMsg NSAL0460SMsg
     * @return Change Line : true
     */
    private boolean checkInputData(NSAL0460CMsg cMsg, NSAL0460SMsg sMsg) {
        boolean isErrFlg = false;
        boolean isChkFlg = false;

        if (!ZYPCommonFunc.hasValue(cMsg.svcMemoRsnCd_PS) && ZYPCommonFunc.hasValue(cMsg.svcCmntTxt)) {
            cMsg.setMessageInfo(ZZM9000E, new String[] {ERR_PRAM_RSN_CD });
            cMsg.svcMemoRsnCd_PS.setErrorInfo(1, ZZM9000E, new String[] {ERR_PRAM_RSN_CD });
            isErrFlg = true;
        }

        if (ZYPCommonFunc.hasValue(cMsg.svcMemoRsnCd_PS) && !ZYPCommonFunc.hasValue(cMsg.svcCmntTxt)) {
            cMsg.setMessageInfo(ZZM9000E, new String[] {ERR_PRAM_NOTES });
            cMsg.svcCmntTxt.setErrorInfo(1, ZZM9000E, new String[] {ERR_PRAM_NOTES });
            isErrFlg = true;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox.getValue())) {
                isChkFlg = true;
            }
        }
        if (!isChkFlg) {
            cMsg.setMessageInfo(NSAM0015E);
            isErrFlg = true;
        }

        int pageIdx = cMsg.xxPageShowToNum.getValueInt() - 1;
        String chkFlg = null;
        // START 2016/01/26 T.Tomita [QC#2883, ADD]
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk;
        BigDecimal svcPhysMtrPk;
        // END 2016/01/26 T.Tomita [QC#2883, ADD]
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (sMsg.A.no(i).xxRowNum_D.getValueInt() == 1) {
                chkFlg = (String) sMsg.A.no(i).xxChkBox.getValue();
            }
            // START 2016/01/26 T.Tomita [QC#2883, ADD]
            if (!ZYPConstant.CHKBOX_ON_Y.equals(chkFlg)) {
                continue;
            }

            if (sMsg.A.no(i).xxRowNum_D.getValueInt() == 1) {
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).mtrReadDt)) {
                    cMsg.setMessageInfo(ZZM9000E, new String[] {ERR_PRAM_READ_DATE });
                    sMsg.A.no(i).mtrReadDt.setErrorInfo(1, ZZM9000E, new String[] {ERR_PRAM_READ_DATE });
                    if (!isErrFlg) {
                        pageIdx = i;
                    }
                    isErrFlg = true;
                }
            }
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).readMtrCnt)) {
                cMsg.setMessageInfo(ZZM9000E, new String[] {ERR_PRAM_READ });
                sMsg.A.no(i).readMtrCnt.setErrorInfo(1, ZZM9000E, new String[] {ERR_PRAM_READ });
                if (!isErrFlg) {
                    pageIdx = i;
                }
                isErrFlg = true;
            }

            dsContrDtlPk = sMsg.A.no(i).dsContrDtlPk.getValue();
            svcPhysMtrPk = sMsg.A.no(i).svcPhysMtrPk.getValue();
            if (!NSAL0460CommonLogic.isExistsStartMtrRead(glblCmpyCd, dsContrDtlPk, svcPhysMtrPk)) {
                continue;
            }

            if (!ZYPCommonFunc.hasValue(cMsg.svcMemoRsnCd_PS)) {
                cMsg.setMessageInfo(ZZM9000E, new String[] {ERR_PRAM_RSN_CD });
                cMsg.svcMemoRsnCd_PS.setErrorInfo(1, ZZM9000E, new String[] {ERR_PRAM_RSN_CD });
                if (!isErrFlg) {
                    pageIdx = i;
                }
                isErrFlg = true;
            }

            if (!ZYPCommonFunc.hasValue(cMsg.svcCmntTxt)) {
                cMsg.setMessageInfo(ZZM9000E, new String[] {ERR_PRAM_NOTES });
                cMsg.svcCmntTxt.setErrorInfo(1, ZZM9000E, new String[] {ERR_PRAM_NOTES });
                if (!isErrFlg) {
                    pageIdx = i;
                }
                isErrFlg = true;
            }
            // END 2016/01/26 T.Tomita [QC#2883, ADD]
        }

        showTargetPage(cMsg, sMsg, pageIdx);

        if (isErrFlg) {
            return false;
        }
        return true;
    }

    private void showTargetPage(NSAL0460CMsg cMsg, NSAL0460SMsg sMsg, int pageIdx) {
        NSAL0460_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        int pageFrom = pageIdx / acMsgArray.length();
        pageFrom = pageFrom * acMsgArray.length();

        int i = pageFrom;
        for (; i < pageFrom + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageFrom);

        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pageFrom);
        cMsg.xxPageShowToNum.setValue(pageFrom + acMsgArray.getValidCount() - 1);
    }

    /**
     * doUpdateMeterRead
     * @param cMsg NSAL0460CMsg
     * @param sMsg NSAL0460SMsg
     * @param chkBoxOffList List<Integer>
     */
    private void doUpdateMeterRead(NSAL0460CMsg cMsg, NSAL0460SMsg sMsg, List<Integer> chkBoxOffList) {
        int i = 0;
        int j = 0;
        int targetDtlNum = 0;
        boolean contrErrFlg = false;
        boolean serErrFlg = false;
        boolean apiCallFlg = false;
        boolean fstErrFlg = true;
        NSZC010001PMsg apiPMsg = new NSZC010001PMsg();
        // START 2015/12/17 K.Kasai [QC#2076, ADD]
        String mtrReadDt = null;
        // END 2015/12/17 K.Kasai [QC#2076, ADD]
        // [QC#2886,ADD]START
        List<Integer> chkBoxOnList = new ArrayList<Integer>();
        // [QC#2886,ADD]END

        // Add Start 2016/10/27 <QC#15511>
        BigDecimal targetGrpSq = null;
        // Add End   2016/10/27 <QC#15511>
        // START 2017/01/04 K.Ochiai [QC#16584, ADD]
        String dsMtrReadTpGrpCd = null;
        // END 2017/01/04 K.Ochiai [QC#16584, ADD]
        for (; i < sMsg.A.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox.getValue()) && sMsg.A.no(i).xxRowNum_D.getValueInt() == 1) {
                targetDtlNum = sMsg.A.no(i).xxRowNum_A.getValueInt();
                // Add Start 2016/10/27 <QC#15511>
                targetGrpSq = sMsg.A.no(i).svcPhysMtrReadGrpSq.getValue();
                // Add End   2016/10/27 <QC#15511>
                apiCallFlg = true;
                // START 2017/01/04 K.Ochiai [QC#16584, ADD]
                dsMtrReadTpGrpCd = sMsg.A.no(i).dsMtrReadTpGrpCd.getValue();
                // END 2017/01/04 K.Ochiai [QC#16584, ADD]
            }
            if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox.getValue()) && sMsg.A.no(i).xxRowNum_D.getValueInt() == 1) {
                apiCallFlg = false;
                // Add Start 2016/10/27 <QC#15511>
                targetGrpSq = null;
                // Add End   2016/10/27 <QC#15511>
                // START 2017/01/04 K.Ochiai [QC#16584, ADD]
                dsMtrReadTpGrpCd = null;
                // END 2017/01/04 K.Ochiai [QC#16584, ADD]
            }
            // START 2015/12/17 K.Kasai [QC#2076, ADD]
            if (j == 0) {
                mtrReadDt = sMsg.A.no(i).mtrReadDt.getValue();
            }
            // END 2015/12/17 K.Kasai [QC#2076, ADD]

            // [QC#2886,ADD]START
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox.getValue())) {
                chkBoxOnList.add(i);
            }
            // [QC#2886,ADD]END

            if (apiCallFlg) {
                if (sMsg.A.no(i).xxRowNum_D.getValueInt() == 1) {
                    apiPMsg = new NSZC010001PMsg();
                    ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, cMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(apiPMsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.S21);
                    // START 2024/02/15 K.Watanabe [QC#63529,MOD]
                    // ZYPEZDItemValueSetter.setValue(apiPMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
                    ZYPEZDItemValueSetter.setValue(apiPMsg.dsMtrReadTpCd, sMsg.A.no(i).dsMtrReadTpCd);
                    // END 2024/02/15 K.Watanabe [QC#63529,MOD]
                    ZYPEZDItemValueSetter.setValue(apiPMsg.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.BILLABLE_READS);
                    ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, cMsg.slsDt);
                    ZYPEZDItemValueSetter.setValue(apiPMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk);
                    // START 2017/07/06 K.Kitachi [QC#18292, MOD]
//                    ZYPEZDItemValueSetter.setValue(apiPMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(apiPMsg.xxWrnSkipFlg, sMsg.A.no(i).xxWrnSkipFlg);
                    // END 2017/07/06 K.Kitachi [QC#18292, MOD]
                    ZYPEZDItemValueSetter.setValue(apiPMsg.xxStartReadFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(apiPMsg.dsContrDtlPk, sMsg.A.no(i).dsContrDtlPk);
                    ZYPEZDItemValueSetter.setValue(apiPMsg.contrEffFromDt, sMsg.A.no(i).contrEffFromDt);
                }
                ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(j).svcPhysMtrPk, sMsg.A.no(i).svcPhysMtrPk);
                // START 2015/12/17 K.Kasai [QC#2076, MOD]
//                ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(j).mtrReadDt, sMsg.A.no(i).mtrReadDt);
                ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(j).mtrReadDt, mtrReadDt);
                // END 2015/12/17 K.Kasai [QC#2076, MOD]
                ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(j).rgtnMtrDt, cMsg.slsDt);
                ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(j).readMtrCnt, sMsg.A.no(i).readMtrCnt);
                ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(j).testMtrCnt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(j).rgtnUsrId, cMsg.getUserID());
                ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(j).estFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(j).invProcFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(j).vldMtrFlg, ZYPConstant.FLG_ON_Y);
                // START 2018/02/08 M.Kidokoro [QC#18145,ADD]
                ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(j).mtrEntryCmntTxt, sMsg.A.no(i).svcCmntTxt_A);
                // END 2018/02/08 M.Kidokoro [QC#18145,ADD]

                // Once you become the next DS_CONTR_DTL_PK, to call
                // the API.
                if (i == sMsg.A.getValidCount() - 1 || sMsg.A.no(i + 1).xxRowNum_D.getValueInt() == 1) {
                    // START 2022/08/05 E.Sanchez [QC#60357,MOD]
                    serErrFlg = checkForUnbilledSchedules(cMsg, sMsg, targetDtlNum);
                    if (!serErrFlg) {
                        // START 2022/06/22 E.Sanchez [QC#59804,ADD]
                        boolean isMeterUsedForBilling = false;
                        if (ZYPCommonFunc.hasValue(targetGrpSq)) {
                            S21SsmEZDResult rslt = NSAL0460Query.getInstance().getBllgMtrSchdBySvcPhysMrReadGrpSq(cMsg.glblCmpyCd.getValue(), targetGrpSq);
                            if (rslt != null && rslt.getQueryResultCount() > 0) {
                                isMeterUsedForBilling = true;
                            }
                        }
                        // END 2022/06/22 E.Sanchez [QC#59804,ADD]
                        // Mod Start 2016/10/27 <QC#15511>
                        // START 2016/12/16 K.Ochiai [QC#16584, MOD]
                        // START 2017/01/04 K.Ochiai [QC#16584, MOD]
                        if (ZYPCommonFunc.hasValue(targetGrpSq) && DS_MTR_READ_TP_GRP.BILLABLE_READS.equals(dsMtrReadTpGrpCd)
                                // START 2022/06/22 E.Sanchez [QC#59804,ADD]
                                && !isMeterUsedForBilling) {
                                // END 2022/06/22 E.Sanchez [QC#59804,ADD]                    
                            serErrFlg = updateStartMtrRead(apiPMsg, sMsg, targetGrpSq, targetDtlNum);
                        } else {
                            apiPMsg.A.setValidCount(j + 1);
                            // START 2017/07/06 K.Kitachi [QC#18292, MOD]
//                            serErrFlg = callMeterReadApi(apiPMsg, sMsg, targetDtlNum);
                            serErrFlg = callMeterReadApi(apiPMsg, cMsg, sMsg, targetDtlNum);
                            // END 2017/07/06 K.Kitachi [QC#18292, MOD]
                        }
                        // END 2017/01/04 K.Ochiai [QC#16584, MOD]                        
                    }
                    // END 2022/08/05 E.Sanchez [QC#60357,MOD]
                    if (serErrFlg) {
                        contrErrFlg = true;
                        // [QC#2886,DEL]START
                        //cMsg.setMessageInfo(NSAM0394W);
                        // [QC#2886,DEL]END
                        if (fstErrFlg) {
                            showTargetPage(cMsg, sMsg, targetDtlNum - 1);
                            fstErrFlg = false;
                        }
                    }
                    // END 2016/12/16 K.Ochiai [QC#16584, MOD]
                    // Mod End   2016/10/27 <QC#15511>
                    j = 0;
                } else {
                    j++;
                }
            }
            // Once you become the next DS_CONTR_PK, Commit or
            // Rollback
            if (i == sMsg.A.getValidCount() - 1 || sMsg.A.no(i + 1).xxRowNum_C.getValueInt() == 1) {
                if (contrErrFlg) {
                    EZDConnectionMgr.getInstance().rollback();
                    contrErrFlg = false;
                } else {
                    // [QC#2886,ADD]START
                    chkBoxOffList.addAll(chkBoxOnList);
                    // [QC#2886,ADD]END
                    EZDConnectionMgr.getInstance().commit();
                }
                // [QC#2886,ADD]START
                chkBoxOnList = new ArrayList<Integer>();
                // [QC#2886,ADD]END
            }
        }
    }

    // Add Start 2016/10/27 <QC#15511>
    // START 2016/12/16 K.Ochiai [QC#16584, MOD]
    // START 2017/01/04 K.Ochiai [QC#16584, MOD]
    private boolean updateStartMtrRead(NSZC010001PMsg apiPMsg, NSAL0460SMsg sMsg, BigDecimal svcPhysMtrReadGrpSq, int targetDtlNum) {
        String glblCmpyCd = apiPMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = apiPMsg.dsContrDtlPk.getValue();
        BigDecimal svcMachMstrPk = apiPMsg.svcMachMstrPk.getValue();
        String mtrReadDt = null;
        // START 2022/06/22 E.Sanchez [QC#59804,ADD]
        List<BigDecimal> svcPhysMtrPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            svcPhysMtrPkList.add(sMsg.A.no(i).svcPhysMtrPk.getValue());
        }
        // END 2022/06/22 E.Sanchez [QC#59804,ADD]        
        if (hasValue(apiPMsg.A.no(0).mtrReadDt)) {
            mtrReadDt = apiPMsg.A.no(0).mtrReadDt.getValue();
        } else {
            mtrReadDt = apiPMsg.slsDt.getValue();
        }
        if (FLG_ON_Y.equals(apiPMsg.xxStartReadFlg.getValue()) && NSXC003001SvcPhysMtrRead.hasErrMachineMissingStartRead(glblCmpyCd, dsContrDtlPk, mtrReadDt)) {
            sMsg.A.no(targetDtlNum - 1).dsContrNum.setErrorInfo(1, NSZM0750E);
            sMsg.A.no(targetDtlNum - 1).serNum.setErrorInfo(1, NSZM0750E);
            sMsg.A.no(targetDtlNum - 1).contrEffFromDt.setErrorInfo(1, NSZM0750E);
            sMsg.A.no(targetDtlNum - 1).mtrLbDescTxt.setErrorInfo(1, NSZM0750E);
            return true;
        }
        if (!clearStartMtr(glblCmpyCd, dsContrDtlPk, svcMachMstrPk)) {
            sMsg.A.no(targetDtlNum - 1).dsContrNum.setErrorInfo(1, NSAM0040E, new String[] {MTR_READ });
            sMsg.A.no(targetDtlNum - 1).serNum.setErrorInfo(1, NSAM0040E, new String[] {MTR_READ });
            sMsg.A.no(targetDtlNum - 1).contrEffFromDt.setErrorInfo(1, NSAM0040E, new String[] {MTR_READ });
            sMsg.A.no(targetDtlNum - 1).mtrLbDescTxt.setErrorInfo(1, NSAM0040E, new String[] {MTR_READ });
            return true;
        }
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("007");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrReadGrpSq);
        SVC_PHYS_MTR_READTMsgArray outArray = (SVC_PHYS_MTR_READTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);
        for (int i = 0; i < outArray.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(outArray.no(i).vldMtrFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(outArray.no(i).dsContrDtlPk, dsContrDtlPk);
                // START 2022/06/22 E.Sanchez [QC#59804,ADD]
                if (svcPhysMtrPkList.contains(outArray.no(i).svcPhysMtrPk.getValue())) {
                    ZYPEZDItemValueSetter.setValue(outArray.no(i).carryOverTpCd, CARRY_OVER_TP_CD_OFF);
                }
                // END 2022/06/22 E.Sanchez [QC#59804,ADD]
                EZDTBLAccessor.update(outArray.no(i));
            } else {
                sMsg.A.no(targetDtlNum - 1).dsContrNum.setErrorInfo(1, NSAM0040E, new String[] {MTR_READ });
                sMsg.A.no(targetDtlNum - 1).serNum.setErrorInfo(1, NSAM0040E, new String[] {MTR_READ });
                sMsg.A.no(targetDtlNum - 1).contrEffFromDt.setErrorInfo(1, NSAM0040E, new String[] {MTR_READ });
                sMsg.A.no(targetDtlNum - 1).mtrLbDescTxt.setErrorInfo(1, NSAM0040E, new String[] {MTR_READ });
                return true;
            }
        }
        return false;
    }

    private boolean clearStartMtr(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal svcMachMstrPk) {
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("009");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        SVC_PHYS_MTR_READTMsgArray outArray = (SVC_PHYS_MTR_READTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);
        for (int i = 0; i < outArray.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(outArray.no(i).vldMtrFlg.getValue())) {
                outArray.no(i).dsContrDtlPk.clear();
                EZDTBLAccessor.update(outArray.no(i));
            } else {
                return false;
            }
        }
        return true;
    }
    // END 2017/01/04 K.Ochiai [QC#16584, MOD]
    // END 2016/12/16 K.Ochiai [QC#16584, MOD]
    // Add End  2016/10/27 <QC#15511>

    /**
     * callMeterReadApi
     * @param apiPMsg NSZC010001PMsg
     * @param cMsg NSAL0460CMsg
     * @param sMsg NSAL0460SMsg
     * @param targetDtlNum int
     */
    // START 2017/07/06 K.Kitachi [QC#18292, MOD]
//    private boolean callMeterReadApi(NSZC010001PMsg apiPMsg, NSAL0460SMsg sMsg, int targetDtlNum) {
    private boolean callMeterReadApi(NSZC010001PMsg apiPMsg, NSAL0460CMsg cMsg, NSAL0460SMsg sMsg, int targetDtlNum) {
        NSZC010001 api = new NSZC010001();
        api.execute(apiPMsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);

        if (apiPMsg.xxMsgIdList.getValidCount() != 0) {
//            // [QC#2886,MOD]START
//            //sMsg.A.no(targetDtlNum - 1).dsMsgTxt.setValue(S21MessageFunc.clspGetMessage(apiPMsg.xxMsgIdList.no(0).xxMsgId.getValue()));
//            sMsg.A.no(targetDtlNum - 1).dsContrNum.setErrorInfo(1, apiPMsg.xxMsgIdList.no(0).xxMsgId.getValue());
//            sMsg.A.no(targetDtlNum - 1).serNum.setErrorInfo(1, apiPMsg.xxMsgIdList.no(0).xxMsgId.getValue());
//            sMsg.A.no(targetDtlNum - 1).contrEffFromDt.setErrorInfo(1, apiPMsg.xxMsgIdList.no(0).xxMsgId.getValue());
//            // mod start 2016/02/25 CSA Defect#2684
//            sMsg.A.no(targetDtlNum - 1).mtrLbDescTxt.setErrorInfo(1, apiPMsg.xxMsgIdList.no(0).xxMsgId.getValue());
//            // mod end 2016/02/25 CSA Defect#2684
//            // [QC#2886,MOD]END
            String messageCode = apiPMsg.xxMsgIdList.no(0).xxMsgId.getValue();
            // START 2024/02/15 K.Watanabe [QC#63529,ADD]
            if (messageCode.equals(NSZM0541E)) {
                messageCode = NSAM0784W;
            }
            // END   2024/02/15 K.Watanabe [QC#63529,ADD]
            int errorCode = getErrorCode(messageCode);
            sMsg.A.no(targetDtlNum - 1).dsContrNum.setErrorInfo(errorCode, messageCode);
            sMsg.A.no(targetDtlNum - 1).serNum.setErrorInfo(errorCode, messageCode);
            sMsg.A.no(targetDtlNum - 1).contrEffFromDt.setErrorInfo(errorCode, messageCode);
            sMsg.A.no(targetDtlNum - 1).mtrLbDescTxt.setErrorInfo(errorCode, messageCode);
            // START 2024/02/15 K.Watanabe [QC#63529,MOD]
            if (messageCode.equals(NSAM0784W)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(targetDtlNum - 1).dsMtrReadTpCd, DS_MTR_READ_TP.ADJUSTMENT);
            } else {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(targetDtlNum - 1).dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
            }
            if (errorCode == 2) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(targetDtlNum - 1).xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                cMsg.setMessageInfo(NSAM0686W);
//            }
            } else {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(targetDtlNum - 1).xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
            }
            // END   2024/02/15 K.Watanabe [QC#63529,MOD]
            return true;
        }
        return false;
    }
    // END 2017/07/06 K.Kitachi [QC#18292, MOD]

    // START 2018/02/08 M.Kidokoro [QC#18145,DEL]
//    /**
//     * Insert Service Memo
//     * @param cMsg NSAL0660CMsg
//     */
//    private static void insertSvcMemo(NSAL0460CMsg cMsg, NSAL0460SMsg sMsg) {
//        int preDsContrPk = 0;
//        int preDsContrDtlPk = 0;
//        if (ZYPCommonFunc.hasValue(cMsg.svcMemoRsnCd_PS) && ZYPCommonFunc.hasValue(cMsg.svcCmntTxt)) {
//            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
//                if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox.getValue()) && sMsg.A.no(i).xxRowNum_D.getValueInt() == 1) {
//                    if (sMsg.A.no(i).dsContrPk.getValueInt() != preDsContrPk || sMsg.A.no(i).dsContrDtlPk.getValueInt() != preDsContrDtlPk) {
//                        SVC_MEMOTMsg tmsg = new SVC_MEMOTMsg();
//                        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, cMsg.glblCmpyCd);
//                        ZYPEZDItemValueSetter.setValue(tmsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
//                        ZYPEZDItemValueSetter.setValue(tmsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
//                        ZYPEZDItemValueSetter.setValue(tmsg.svcMemoTpCd, SVC_MEMO_TP.UPDATE_START_METER_READ);
//                        ZYPEZDItemValueSetter.setValue(tmsg.svcCmntTxt, cMsg.svcCmntTxt);
//                        ZYPEZDItemValueSetter.setValue(tmsg.dsContrPk, sMsg.A.no(i).dsContrPk);
//                        ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, sMsg.A.no(i).dsContrDtlPk);
//                        ZYPEZDItemValueSetter.setValue(tmsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk);
//                        ZYPEZDItemValueSetter.setValue(tmsg.lastUpdUsrId, cMsg.getUserID());
//                        ZYPEZDItemValueSetter.setValue(tmsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
//                        ZYPEZDItemValueSetter.setValue(tmsg.svcMemoRsnCd, cMsg.svcMemoRsnCd_PS);
//                        S21FastTBLAccessor.insert(tmsg);
//                    }
//                }
//                preDsContrPk = sMsg.A.no(i).dsContrPk.getValueInt();
//                preDsContrDtlPk = sMsg.A.no(i).dsContrDtlPk.getValueInt();
//            }
//        }
//
//        // [QC#2886,ADD]START
//        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
//            if (ZYPCommonFunc.hasValue((sMsg.A.no(i).svcCmntTxt_A))) {
//                SVC_MEMOTMsg tmsg = new SVC_MEMOTMsg();
//                ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, cMsg.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(tmsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
//                ZYPEZDItemValueSetter.setValue(tmsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
//                ZYPEZDItemValueSetter.setValue(tmsg.svcMemoTpCd, SVC_MEMO_TP.UPDATE_START_METER_READ);
//                ZYPEZDItemValueSetter.setValue(tmsg.svcCmntTxt, sMsg.A.no(i).svcCmntTxt_A);
//                ZYPEZDItemValueSetter.setValue(tmsg.dsContrPk, sMsg.A.no(i).dsContrPk);
//                ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, sMsg.A.no(i).dsContrDtlPk);
//                ZYPEZDItemValueSetter.setValue(tmsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk);
//                ZYPEZDItemValueSetter.setValue(tmsg.lastUpdUsrId, cMsg.getUserID());
//                ZYPEZDItemValueSetter.setValue(tmsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
//                ZYPEZDItemValueSetter.setValue(tmsg.svcMemoRsnCd, SVC_MEMO_RSN.START_READ_MANUAL_INSERT);
//                ZYPEZDItemValueSetter.setValue(tmsg.svcMemoTrxNum, sMsg.A.no(i).svcPhysMtrPk.getValue().toString());
//                ZYPEZDItemValueSetter.setValue(tmsg.svcMemoTrxNm, SVC_MEMO_TRX_NM_SVC_PHYS_MTR_PK);
//                S21FastTBLAccessor.insert(tmsg);
//            }
//        }
//        // [QC#2886,ADD]END
//    }
    // END 2018/02/08 M.Kidokoro [QC#18145,DEL]

    // START 2017/07/06 K.Kitachi [QC#18292, ADD]
    private int getErrorCode(String messageCode) {
        char type = messageCode.charAt(messageCode.length() - 1);
        if (type == 'E') {
            return 1;
        }
        if (type == 'W') {
            return 2;
        }
        return 0;
    }
    // END 2017/07/06 K.Kitachi [QC#18292, ADD]

    // START 2022/08/05 E.Sanchez [QC#60357, ADD]
    private boolean checkForUnbilledSchedules(NSAL0460CMsg cMsg, NSAL0460SMsg sMsg, int targetDtlNum) {
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        BigDecimal svcMachMstrPk = sMsg.A.no(targetDtlNum - 1).svcMachMstrPk.getValue();
        BigDecimal dsContrDtlPk = sMsg.A.no(targetDtlNum - 1).dsContrDtlPk.getValue();
        String mtrReadDt = sMsg.A.no(targetDtlNum - 1).mtrReadDt.getValue();
        // START 2022/11/04 E.Sanchez [QC#60775, MOD]
        String contrEffFromDt = sMsg.A.no(targetDtlNum - 1).contrEffFromDt.getValue();
        // List<Map<String, Object>> unbilledSchedules = NSAL0460Query.getInstance().getUnbilledSchedules(glblCmpyCd, svcMachMstrPk, dsContrDtlPk, mtrReadDt);
        List<Map<String, Object>> unbilledSchedules = NSAL0460Query.getInstance().getUnbilledSchedules(glblCmpyCd, svcMachMstrPk, dsContrDtlPk, mtrReadDt, contrEffFromDt);
        // END 2022/11/04 E.Sanchez [QC#60775, MOD]
        if (unbilledSchedules != null && unbilledSchedules.size() > 0) {
            int errorCode = getErrorCode(NSAM0763E);
            String serNum = sMsg.A.no(targetDtlNum - 1).serNum.getValue();
            String contrNum = (String) unbilledSchedules.get(0).get("DS_CONTR_NUM");
            String billSchdThruDt = ZYPDateUtil.convertFormat((String) unbilledSchedules.get(0).get("BLLG_SCHD_THRU_DT"), ZYPDateUtil.TYPE_YYYYMMDD, 
                                                               ZYPDateUtil.TYPE_MMDDYYYY, DATE_SEPARATOR);
            String [] messageParams = new String[] { serNum, contrNum, billSchdThruDt };
            sMsg.A.no(targetDtlNum - 1).mtrReadDt.setErrorInfo(errorCode, NSAM0763E, messageParams);
            return true;
        }
        return false;
    }
    // END 2022/08/05 E.Sanchez [QC#60357, ADD]
}
