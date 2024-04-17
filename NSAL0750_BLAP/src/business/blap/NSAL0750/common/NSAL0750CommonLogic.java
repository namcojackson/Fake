/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0750.common;

import static business.blap.NSAL0750.constant.NSAL0750Constant.BIZ_ID;
import static business.blap.NSAL0750.constant.NSAL0750Constant.DT_FORMAT_TS;
import static business.blap.NSAL0750.constant.NSAL0750Constant.NSAM0013E;
import static business.blap.NSAL0750.constant.NSAL0750Constant.NSAM0200I;
import static business.blap.NSAL0750.constant.NSAL0750Constant.NSAM0394W;
import static business.blap.NSAL0750.constant.NSAL0750Constant.NZZM0003E;
import static business.blap.NSAL0750.constant.NSAL0750Constant.RTRN_MSG_FAILED;
import static business.blap.NSAL0750.constant.NSAL0750Constant.RTRN_MSG_SUCCESS;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDConnectionMgr;
import business.blap.NSAL0750.NSAL0750CMsg;
import business.blap.NSAL0750.NSAL0750Query;
import business.blap.NSAL0750.NSAL0750SMsg;
import business.blap.NSAL0750.NSAL0750_ACMsg;
import business.blap.NSAL0750.NSAL0750_ACMsgArray;
import business.blap.NSAL0750.NSAL0750_ASMsg;
import business.blap.NSAL0750.NSAL0750_PCMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;
import business.parts.NSZC047001PMsg;
import business.parts.NSZC047099PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2015/12/04   Hitachi         T.Tsuchida      Update          QC#1476
 * 2016/01/21   Hitachi         T.Tomita        Update          QC#2571
 * 2016/02/09   Hitachi         K.Kasai         Update          QC#2952
 * 2016/02/22   Hitachi         T.Aoyagi        Update          QC3694
 * 2016/03/07   Hitachi         T.Aoyagi        Update          QC#5094
 * 2016/11/16   Hitachi         T.Kanasaka      Update          QC#15942
 * 2016/11/21   Hitachi         T.Tomita        Update          QC#15942
 * 2016/12/08   Hitachi         T.Mizuki        Update          QC#4210
 * 2020/06/05   Hitachi         K.Kitachi       Update          QC#56086
 *</pre>
 */
public class NSAL0750CommonLogic {

    /**
     * Invoicing Rule Pull down
     * @param cMsg NSAL0750CMsg
     */
    public static void setInvoicingRuleInfo(NSAL0750CMsg cMsg) {

        ZYPCodeDataUtil.createPulldownList(BLLG_TMG_TP.class, cMsg.baseBllgTmgCd_H1, cMsg.bllgTmgTpNm_H2);
    }

    /**
     * Service Memo Reason Pull down
     * @param cMsg NSAL0750CMsg
     * @param sMsg NSAL0750SMsg
     */
    public static void setServiceMemoReasonInfo(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg) {

        SVC_MEMO_RSNTMsgArray smrTMsgArray = NSAL0750Query.getInstance().getServiceMemoReasonInfo(cMsg);
        if (smrTMsgArray.getValidCount() > 0) {
            Map<String, String> tMsgKeys = new HashMap<String, String>();
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnNm");
            ZYPPulldownValueSetter.set(smrTMsgArray, tMsgKeys, cMsg.svcMemoRsnCd_H1, cMsg.svcMemoRsnNm_H2);
        }
    }

    /**
     * Contract Detail Information
     * @param cMsg NSAL0750CMsg
     * @param sMsg NSAL0750SMsg
     */
    public static void setDetailListInfo(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg) {

        // Contract Detail Level
        // START 2016/02/22 T.Aoyagi [QC3694, MOD]
        S21SsmEZDResult ssmResult = NSAL0750Query.getInstance().getDSContractInfo(cMsg, sMsg, getDsContrPkList(cMsg), getDsContrDtlPkList(cMsg));
        // END 2016/02/22 T.Aoyagi [QC3694, MOD]
        if (ssmResult.isCodeNormal()) {
            setDsContractDetailInfo(cMsg, sMsg);
        } else {
            cMsg.setMessageInfo(NSAM0013E, null);
            return;
        }
        // mod start 2016/12/08 CSA QC#4210
        ZYPEZDItemValueSetter.setValue(cMsg.xxRowNum, new BigDecimal(cMsg.A.getValidCount()));
        // mod end 2016/12/08 CSA QC#4210
    }

    private static void setDsContractDetailInfo(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg) {

        boolean dsContrFlg = false;
        String dsContrCatgCd = null;
        for (int index = 0; index < sMsg.A.getValidCount(); index++) {

            NSAL0750_ASMsg asMsg = sMsg.A.no(index);
            BigDecimal dsContrPk = asMsg.dsContrPk_D1.getValue();
            BigDecimal dsContrDtlPk = asMsg.dsContrDtlPk.getValue();
            BigDecimal svcMachMstrPk = asMsg.svcMachMstrPk_D1.getValue();

            if (svcMachMstrPk != null) {
                ZYPEZDItemValueSetter.setValue(asMsg.xxFlgNm_D1, ZYPConstant.CHKBOX_ON_Y);
                ZYPEZDItemValueSetter.setValue(asMsg.xxFlgNm_D2, ZYPConstant.FLG_OFF_N);
                if (dsContrFlg || setExstFlgDsContrDtl(cMsg, dsContrPk, dsContrDtlPk) || (!dsContrCatgCd.isEmpty() && DS_CONTR_CATG.FLEET.equals(dsContrCatgCd))) {
                    ZYPEZDItemValueSetter.setValue(asMsg.xxFlgNm_D4, ZYPConstant.FLG_ON_1);

                } else {
                    ZYPEZDItemValueSetter.setValue(asMsg.xxFlgNm_D4, ZYPConstant.FLG_OFF_0);
                }
                asMsg.xxScrItem34Txt.clear();
            } else {
                dsContrCatgCd = asMsg.dsContrCatgCd.getValue();
                ZYPEZDItemValueSetter.setValue(asMsg.xxFlgNm_D1, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(asMsg.xxFlgNm_D2, ZYPConstant.CHKBOX_ON_Y);
                if (setExstFlgDsContr(cMsg, dsContrPk)) {
                    dsContrFlg = true;
                    ZYPEZDItemValueSetter.setValue(asMsg.xxFlgNm_D3, ZYPConstant.FLG_ON_1);
                } else {
                    ZYPEZDItemValueSetter.setValue(asMsg.xxFlgNm_D3, ZYPConstant.FLG_OFF_0);
                }
                // del start 2016/02/09 CSA Defect#2952
//                if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
//                    ZYPEZDItemValueSetter.setValue(asMsg.bllgTmgTpNm_D1, sMsg.A.no(index + 1).bllgTmgTpNm_D1);
//                }
                // del end 2016/02/09 CSA Defect#2952
            }
        }

        boolean flt = false;
        // mod start 2016/12/08 CSA QC#4210
        int idx = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NSAL0750_ASMsg asMsg = sMsg.A.no(i);
            BigDecimal svcMachMstrPk = asMsg.svcMachMstrPk_D1.getValue();
            String contrCatgCd = asMsg.dsContrCatgCd.getValue();

            if (svcMachMstrPk == null && DS_CONTR_CATG.FLEET.equals(contrCatgCd)) {
                flt = true;
            } else if (svcMachMstrPk == null && !DS_CONTR_CATG.FLEET.equals(contrCatgCd)) {
                flt = false;
            }

            if (flt && svcMachMstrPk != null) {
                asMsg.bllgTmgTpNm_D1.clear();
            }

            if (i < cMsg.A.length()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                idx++;
            }
        }
        cMsg.A.setValidCount(idx);

        // Set page#
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        // mod end 2016/12/08 CSA QC#4210
    }

    private static boolean setExstFlgDsContr(NSAL0750CMsg cMsg, BigDecimal dsContrPk) {

        BigDecimal result = (BigDecimal) NSAL0750Query.getInstance().getContStsV(cMsg, dsContrPk).getResultObject();
        if (BigDecimal.ZERO.compareTo(result) < 0) {
            return true;
        }
        return false;
    }

    private static boolean setExstFlgDsContrDtl(NSAL0750CMsg cMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {

        BigDecimal result = (BigDecimal) NSAL0750Query.getInstance().getContrDtlStsV(cMsg, dsContrPk, dsContrDtlPk).getResultObject();
        if (BigDecimal.ZERO.compareTo(result) < 0) {
            return true;
        }
        return false;
    }

    // START 2016/11/16 T.Kanasaka [QC#15942, MOD]
    // mod start 2016/12/08 CSA QC#4210
    /**
     * Submit Contract Info.
     * @param cMsg NSAL0750CMsg
     * @param sMsg NSAL0750SMsg
     */
    public static void submitContractDetailInfo(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg) {

        String bllgTmgTpNm = "";
        for (int i = 0; i < cMsg.baseBllgTmgCd_H1.length(); i++) {
            if (cMsg.baseBllgTmgCd_H3.getValue().equals(cMsg.baseBllgTmgCd_H1.no(i).getValue())) {
                bllgTmgTpNm = cMsg.bllgTmgTpNm_H2.no(i).getValue();
            }
        }

        boolean svcMachMstrPkFlg = false;
        boolean errFlg = false;
        BigDecimal updDsContrPk = null;
        List<BigDecimal> errDsContrPkList = new ArrayList<BigDecimal>();
        for (int idx = 0; idx < sMsg.A.getValidCount(); idx++) {
            BigDecimal dsContrPk = sMsg.A.no(idx).dsContrPk_D1.getValue();
            String xxChkBox01 = sMsg.A.no(idx).xxChkBox_D1.getValue();
            String xxChkBox02 = sMsg.A.no(idx).xxChkBox_D2.getValue();
            String flgNm4 = sMsg.A.no(idx).xxFlgNm_D4.getValue();

            if (updDsContrPk != null && updDsContrPk.compareTo(dsContrPk) != 0) {
                if (errDsContrPkList.contains(updDsContrPk)) {
                    EZDConnectionMgr.getInstance().rollback();
                } else {
                    EZDConnectionMgr.getInstance().commit();
                }
                updDsContrPk = null;
            }

            if (errDsContrPkList.contains(dsContrPk)) {
                continue;
            }

            BigDecimal svcMachMstrPk = sMsg.A.no(idx).svcMachMstrPk_D1.getValue();
            if (svcMachMstrPk == null && ZYPConstant.CHKBOX_ON_Y.equals(xxChkBox01)) {
                svcMachMstrPkFlg = true;
            } else if (svcMachMstrPk == null && !ZYPConstant.CHKBOX_ON_Y.equals(xxChkBox01)) {
                svcMachMstrPkFlg = false;
            }

//            if (!ZYPConstant.FLG_ON_1.equals(flgNm4) && (svcMachMstrPkFlg && svcMachMstrPk != null) || ZYPConstant.CHKBOX_ON_Y.equals(xxChkBox02)) {
            if (DS_CONTR_CATG.AGGREGATE.equals(sMsg.A.no(idx).dsContrCatgCd.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(xxChkBox01)) {
                    updDsContrPk = dsContrPk;
                    updateDsContractDetailForAgg(cMsg, sMsg, idx, bllgTmgTpNm);
                    if (RTRN_MSG_FAILED.equals(sMsg.A.no(idx).contrMsgTxt.getValue())) {
                        errFlg = true;
                        errDsContrPkList.add(dsContrPk);
                    }
                }
            } else if (!ZYPConstant.FLG_ON_1.equals(flgNm4) && (svcMachMstrPkFlg && svcMachMstrPk != null) || ZYPConstant.CHKBOX_ON_Y.equals(xxChkBox02)) {
                updDsContrPk = dsContrPk;
                updateDsContractDetail(cMsg, sMsg, idx, bllgTmgTpNm);
                if (RTRN_MSG_FAILED.equals(sMsg.A.no(idx).contrMsgTxt.getValue())) {
                    errFlg = true;
                    errDsContrPkList.add(dsContrPk);
                }
            } else if (ZYPConstant.FLG_ON_Y.equals(xxChkBox01) && (DS_CONTR_CATG.FLEET.equals(sMsg.A.no(idx).dsContrCatgCd.getValue()))) {
                updDsContrPk = dsContrPk;
                updateDsContractDetailForFlt(cMsg, sMsg, idx, bllgTmgTpNm);
                if (RTRN_MSG_FAILED.equals(sMsg.A.no(idx).contrMsgTxt.getValue())) {
                    errFlg = true;
                    errDsContrPkList.add(dsContrPk);
                }
            }
        }
        reflectSMsgToCMsg(cMsg, sMsg);
        if (updDsContrPk != null) {
            if (errDsContrPkList.contains(updDsContrPk)) {
                EZDConnectionMgr.getInstance().rollback();
            } else {
                EZDConnectionMgr.getInstance().commit();
            }
        }

        if (errFlg) {
            cMsg.setMessageInfo(NSAM0394W);
        } else {
            cMsg.setMessageInfo(NSAM0200I);
        }
    }
    // END 2016/11/16 T.Kanasaka [QC#15942, MOD]

    /**
     * Submit Service Memo
     * @param cMsg NSAL0750CMsg
     * @param sMsg NSAL0750SMsg
     */
    private static boolean submitSvcMemo(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg, int index) {

        BigDecimal dsContrPk = sMsg.A.no(index).dsContrPk_D1.getValue();
        S21SsmEZDResult ssmResult = NSAL0750Query.getInstance().getSvcMemo(sMsg, dsContrPk);

        SVC_MEMOTMsg svcMemoMap = (SVC_MEMOTMsg) ssmResult.getResultObject();
        if (svcMemoMap == null) {

            SVC_MEMOTMsg insertTMsg = new SVC_MEMOTMsg();
            ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoTpCd, SVC_MEMO_TP.INVOICING_RULES);
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcCmntTxt, cMsg.svcCmntTxt);
            ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrPk, dsContrPk);
            ZYPEZDItemValueSetter.setValue(insertTMsg.lastUpdUsrId, cMsg.getUserID());
            ZYPEZDItemValueSetter.setValue(insertTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS));
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoRsnCd, cMsg.svcMemoRsnCd_H3);
            S21FastTBLAccessor.insert(insertTMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).contrMsgTxt, RTRN_MSG_FAILED);
                // START 2016/11/16 T.Kanasaka [QC#15942, DEL]
//                EZDConnectionMgr.getInstance().rollback();
                // END 2016/11/16 T.Kanasaka [QC#15942, DEL]
                return false;
            }
        } else {

            SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, svcMemoMap.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.svcMemoPk, svcMemoMap.svcMemoPk);

            SVC_MEMOTMsg outMsg = (SVC_MEMOTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
            ZYPEZDItemValueSetter.setValue(outMsg.svcCmntTxt, cMsg.svcCmntTxt);
            ZYPEZDItemValueSetter.setValue(outMsg.lastUpdUsrId, cMsg.getUserID());
            ZYPEZDItemValueSetter.setValue(outMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS));
            ZYPEZDItemValueSetter.setValue(outMsg.svcMemoRsnCd, cMsg.svcMemoRsnCd_H3);
            S21FastTBLAccessor.update(outMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).contrMsgTxt, RTRN_MSG_FAILED);
                // START 2016/11/16 T.Kanasaka [QC#15942, DEL]
//                EZDConnectionMgr.getInstance().rollback();
                // END 2016/11/16 T.Kanasaka [QC#15942, DEL]
                return false;
            }
        }
        return true;
    }

    /**
     * check Select
     * @param cMsg NSAL0750CMsg
     * @return boolean
     */
    public static boolean checkSelect(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg) {

        boolean rtnVal = true;
        List<Integer> selectedRowsContract = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_D1", ZYPConstant.CHKBOX_ON_Y);
        List<Integer> selectedRowsSerial = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_D2", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRowsContract.isEmpty() && selectedRowsSerial.isEmpty()) {
            rtnVal = false;
        }
        return rtnVal;
    }

    /**
     * Update DS_CONTR_DTL
     * @param cMsg NSAL0750CMsg
     * @param sMsg NSAL0750SMsg
     * @param index integer
     * @param bllgTmgTpNm String
     */
    private static void updateDsContractDetail(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg, int index, String bllgTmgTpNm) {

        NSAL0750_ASMsg asMsg = sMsg.A.no(index);

        // START 2016/01/21 T.Tomita [QC#2571, ADD]
        if (!hasValue(asMsg.dsContrDtlPk)) {
            return;
        }
        // END 2016/01/21 T.Tomita [QC#2571, ADD]

        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrDtlPk, asMsg.dsContrDtlPk);

        DS_CONTR_DTLTMsg outMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
        if (isSameTimeStamp(asMsg, outMsg, cMsg)) {
            ZYPEZDItemValueSetter.setValue(outMsg.baseBllgTmgCd, cMsg.baseBllgTmgCd_H3);
            S21FastTBLAccessor.update(outMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).contrMsgTxt, RTRN_MSG_FAILED);
                // START 2016/11/16 T.Kanasaka [QC#15942, DEL]
//                EZDConnectionMgr.getInstance().rollback();
                // END 2016/11/16 T.Kanasaka [QC#15942, DEL]
                return;
            }
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).bllgTmgTpNm_D2, bllgTmgTpNm);
        // START 2016/11/21 T.Tomita [QC#15942, ADD]
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).contrMsgTxt, RTRN_MSG_FAILED);
            return;
        // END 2016/11/21 T.Tomita [QC#15942, ADD]
        }

        if (!submitSvcMemo(cMsg, sMsg, index)) {
            return;
        }

        // START 2016/11/16 T.Kanasaka [QC#15942, ADD]
        if (DS_CONTR_CATG.REGULAR.equals(sMsg.A.no(index).dsContrCatgCd.getValue())) {
            if (!callContractBillingScheduleAPI(cMsg, sMsg, index)) {
                return;
            }
        }
        // END 2016/11/16 T.Kanasaka [QC#15942, ADD]

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).contrMsgTxt, RTRN_MSG_SUCCESS);
        // START 2016/11/16 T.Kanasaka [QC#15942, DEL]
//        EZDConnectionMgr.getInstance().commit();
        // END 2016/11/16 T.Kanasaka [QC#15942, DEL]
    }

    /**
     * Update DS_CONTR_DTL For Fleet
     * @param cMsg NSAL0750CMsg
     * @param sMsg NSAL0750SMsg
     * @param index integer
     * @param bllgTmgTpNm String
     * @return boolean
     */
    private static boolean updateDsContractDetailForFlt(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg, int index, String bllgTmgTpNm) {

        boolean errFlg = false;
        NSAL0750_ASMsg asMsg = sMsg.A.no(index);
        NSAL0750_ASMsg asMsgDtl = null;
        updateDsContractDetail(cMsg, sMsg, index, bllgTmgTpNm);
        if (RTRN_MSG_FAILED.equals(sMsg.A.no(index).contrMsgTxt.getValue())) {
            errFlg = true;
        }

        for (int indexDtl = index + 1; indexDtl < sMsg.A.getValidCount(); indexDtl++) {
            asMsgDtl = sMsg.A.no(indexDtl);
            if (asMsg.dsContrPk_D1.getValue().compareTo(asMsgDtl.dsContrPk_D1.getValue()) == 0) {
                updateDsContractDetail(cMsg, sMsg, indexDtl, bllgTmgTpNm);
                if (RTRN_MSG_FAILED.equals(sMsg.A.no(index).contrMsgTxt.getValue())) {
                    errFlg = true;
                }
            }
        }

        // START 2016/11/16 T.Kanasaka [QC#15942, ADD]
        if (!callContractBillingScheduleAPI(cMsg, sMsg, index)) {
            errFlg = true;
        }

        for (int indexDtl = index + 1; indexDtl < sMsg.A.getValidCount(); indexDtl++) {
            asMsgDtl = sMsg.A.no(indexDtl);
            if (asMsg.dsContrPk_D1.getValue().compareTo(asMsgDtl.dsContrPk_D1.getValue()) == 0) {
                if (!callContractBillingScheduleAPI(cMsg, sMsg, indexDtl)) {
                    errFlg = true;
                }
            }
        }
        // END 2016/11/16 T.Kanasaka [QC#15942, ADD]

        return errFlg;
    }

    // START 2016/11/16 T.Kanasaka [QC#15942, ADD]
    /**
     * Update DS_CONTR_DTL For Aggregate
     * @param cMsg NSAL0750CMsg
     * @param sMsg NSAL0750SMsg
     * @param index integer
     * @param bllgTmgTpNm String
     * @return boolean
     */
    private static boolean updateDsContractDetailForAgg(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg, int index, String bllgTmgTpNm) {
        return updateDsContractDetailForFlt(cMsg, sMsg, index, bllgTmgTpNm);
    }
    // END 2016/11/16 T.Kanasaka [QC#15942, ADD]

    /**
     * @param asMsg NSAL0750_ASMsg
     * @param tMsg DS_CONTR_CR_CARD_POTMsg
     * @param cMsg NSAL0750CMsg
     * @return true/false
     */
    private static boolean isSameTimeStamp(NSAL0750_ASMsg asMsg, DS_CONTR_DTLTMsg tMsg, NSAL0750CMsg cMsg) {

        String preUpTime = asMsg.ezUpTime.getValue();
        String preTimeZone = asMsg.ezUpTimeZone.getValue();
        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, currUpTime, currTimeZone)) {
            cMsg.setMessageInfo(NZZM0003E);
            return false;
        }
        return true;
    }

    private static List<BigDecimal> getDsContrPkList(NSAL0750CMsg cMsg) {

        List<BigDecimal> dsContrPkList = new ArrayList<BigDecimal>();
        NSAL0750_PCMsgArray cMsgP = cMsg.P;
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            dsContrPkList.add(cMsgP.no(i).dsContrPk_P1.getValue());
        }
        return dsContrPkList;
    }

    // START 2016/02/22 T.Aoyagi [QC3694, ADD]
    private static List<BigDecimal> getDsContrDtlPkList(NSAL0750CMsg cMsg) {

        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();
        NSAL0750_PCMsgArray cMsgP = cMsg.P;
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            // START 2016/03/07 T.Aoyagi [QC#5094, MOD]
            if (hasValue(cMsgP.no(i).dsContrDtlPk_P1)) {
                dsContrDtlPkList.add(cMsgP.no(i).dsContrDtlPk_P1.getValue());
            }
            // END 2016/03/07 T.Aoyagi [QC#5094, MOD]
        }
        return dsContrDtlPkList;
    }
    // END 2016/02/22 T.Aoyagi [QC3694, ADD]

    // START 2016/11/16 T.Kanasaka [QC#15942, ADD]
    public static boolean callContractBillingScheduleAPI(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg, int index) {
        String slsDt = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue(), BIZ_ID);

        NSZC047001PMsg pMsg = new NSZC047001PMsg();
        // START 2016/11/21 T.Tomita [QC#15942, MOD]
        DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, sMsg.A.no(index).dsContrDtlPk);
        dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(dsContrDtlTMsg);
        if (dsContrDtlTMsg == null) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).contrMsgTxt, RTRN_MSG_FAILED);
            return false;
        }
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "01");
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, sMsg.A.no(index).dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(pMsg.baseChrgFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.usgChrgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.contrCloDay, dsContrDtlTMsg.contrCloDay);
        ZYPEZDItemValueSetter.setValue(pMsg.baseBllgTmgCd, cMsg.baseBllgTmgCd_H3);
        ZYPEZDItemValueSetter.setValue(pMsg.contrBllgDay, dsContrDtlTMsg.contrBllgDay);
        ZYPEZDItemValueSetter.setValue(pMsg.contrEffFromDt, dsContrDtlTMsg.contrEffFromDt);
        ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, dsContrDtlTMsg.contrEffThruDt);

        pMsg.xxBaseLineList.setValidCount(1);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).dsContrPrcEffPk_BL, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).dsContrPrcEffSqNum_BL, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).effFromDt_BL, dsContrDtlTMsg.contrEffFromDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).effThruDt_BL, dsContrDtlTMsg.contrEffThruDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).baseBllgCycleCd_BL, dsContrDtlTMsg.baseBllgCycleCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).basePrcDealAmt_BL, dsContrDtlTMsg.basePrcDealAmt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).basePrcTermDealAmtRate_BL, dsContrDtlTMsg.basePrcTermDealAmtRate);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).dsContrPrcEffStsCd_BL, dsContrDtlTMsg.dsContrDtlStsCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).qltyAsrnHldFlg_BL, dsContrDtlTMsg.qltyAsrnHldFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).mtrHldFlg_BL, dsContrDtlTMsg.mtrHldFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).contrHldFlg_BL, dsContrDtlTMsg.contrHldFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).bllgHldFlg_BL, dsContrDtlTMsg.bllgHldFlg);
        // END 2016/11/21 T.Tomita [QC#15942, MOD]

        NSZC047001 api = new NSZC047001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).contrMsgTxt, RTRN_MSG_FAILED);
            return false;
        }

        // START 2020/06/05 K.Kitachi [QC#56086, ADD]
        if (!callBllgSchdApiRecovMode(cMsg.glblCmpyCd.getValue(), slsDt, dsContrDtlTMsg.dsContrPk.getValue(), sMsg.A.no(index))) {
            return false;
        }
        // END 2020/06/05 K.Kitachi [QC#56086, ADD]

        return true;
    }
    // END 2016/11/16 T.Kanasaka [QC#15942, ADD]

    /**
     * copy To ASMsg for Current Page Info
     * @param cMsg NSAL0750CMsg
     * @param sMsg NSAL0750SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg) {

        // NSAL0750_ACMsg -> NSAL0750_ASMsg
        NSAL0750_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0750_ACMsg acMsg = (NSAL0750_ACMsg) acMsgArray.no(i);
            int targetIdxNum = acMsg.xxRowNum_A.getValueInt() - 1;

            NSAL0750_ASMsg asMsg = sMsg.A.no(targetIdxNum);
            EZDMsg.copy(acMsg, null, asMsg, null);
        }
    }

    private static void reflectSMsgToCMsg(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg) {

        for (int cIndex = 0; cIndex < cMsg.A.getValidCount(); cIndex++) {
            for (int sIndex = 0; sIndex < sMsg.A.getValidCount(); sIndex++) {
                NSAL0750_ACMsg aCMsg = cMsg.A.no(cIndex);
                NSAL0750_ASMsg aSMsg = sMsg.A.no(sIndex);
                if (aCMsg.xxRowNum_A.getValue().equals(aSMsg.xxRowNum_A.getValue())) {
                    EZDMsg.copy(aSMsg, null, aCMsg, null);
                    break;
                }
            }
        }
    }
    // mod end 2016/12/08 CSA QC#4210

    // START 2020/06/05 K.Kitachi [QC#56086, ADD]
    private static boolean callBllgSchdApiRecovMode(String glblCmpyCd, String slsDt, BigDecimal dsContrPk, NSAL0750_ASMsg aSMsg) {
        NSZC047099PMsg pMsg = new NSZC047099PMsg();
        setValue(pMsg.glblCmpyCd, glblCmpyCd);
        setValue(pMsg.xxModeCd, "99");
        setValue(pMsg.slsDt, slsDt);
        setValue(pMsg.dsContrPk, dsContrPk);
        NSZC047001 api = new NSZC047001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            setValue(aSMsg.contrMsgTxt, getRtnMsg(msg.getXxMsgid(), msg.getXxMsgPrmArray()));
            return false;
        }
        return true;
    }

    private static String getRtnMsg(String msgId, String[] prm) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId, prm);
        }
        return rtnVal;
    }
    // END 2020/06/05 K.Kitachi [QC#56086, ADD]
}
