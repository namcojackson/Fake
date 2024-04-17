/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0630.common;

import static business.blap.NSAL0630.constant.NSAL0630Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0630.NSAL0630CMsg;
import business.blap.NSAL0630.NSAL0630Query;
import business.blap.NSAL0630.NSAL0630SMsg;
import business.blap.NSAL0630.NSAL0630_ASMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_STS_VTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;
import business.parts.NSZC077001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.api.NSZ.NSZC077001.NSZC077001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * Contract On Hold
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   Hitachi         T.Tsuchida      Create          N/A
 * 2016/11/04   Hitachi         T.Tomita        Update          QC#4210
 * 2017/08/31   Hitachi         K.Kojima        Update          QC#20817
 * 2017/08/31   Hitachi         K.Kim           Update          QC#20578
 * 2023/03/02   CITS            E.Sanchez       Update          QC#61230
 * 2023/03/09   CITS            E.Sanchez       Update          QC#61230
 *</pre>
 */
public class NSAL0630CommonLogic {

    /**
     * Get Return Message
     * @param msgId String
     * @return String
     */
    public static String getRtnMsg(String msgId) {
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
    public static String getRtnMsg(String msgId, String[] prm) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId, prm);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    // START 2016/11/04 T.Tomita [QC#4210, ADD]
    /**
     * check Select
     * @param cMsg NSAL0630CMsg
     * @param sMsg NSAL0630SMsg
     * @return boolean
     */
    public static boolean checkSelect(NSAL0630CMsg cMsg, NSAL0630SMsg sMsg) {
        boolean rtnVal = true;
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, XX_CHK_BOX_A, ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSAM0015E);
            rtnVal = false;
        }
        return rtnVal;
    }

    /**
     * Create Pull Down
     * @param cMsg NSAL0630CMsg
     */
    public static void createPullDown(NSAL0630CMsg cMsg) {
        createSvcMemoRsnPullDown(cMsg);
    }

    private static void createSvcMemoRsnPullDown(NSAL0630CMsg cMsg) {
        SVC_MEMO_RSNTMsgArray tMsgAry = getSvcMemoRsnPulldownList(cMsg.glblCmpyCd.getValue(), SVC_MEMO_TP.PUT_CONTRACTS_ON_HOLD);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnNm");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoRsnCd_L, cMsg.svcMemoRsnNm_L);
    }

    private static SVC_MEMO_RSNTMsgArray getSvcMemoRsnPulldownList(String glblCmpyCd, String svcMemoTpCd) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMemoTpCd01", svcMemoTpCd);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * Update Contract
     * @param cMsg NSAL0630CMsg
     * @param sMsg NSAL0630SMsg
     */
    public static void updateContract(NSAL0630CMsg cMsg, NSAL0630SMsg sMsg) {
        boolean checkWarn = true;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0630_ASMsg asMsg = (NSAL0630_ASMsg) sMsg.A.no(i);
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, sMsg.A.no(i).dsContrPk_A);
                DS_CONTRTMsg result = (DS_CONTRTMsg) S21CacheTBLAccessor.findByKey(tMsg);
                if (result == null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsMsgTxt_A, getRtnMsg(NSAM0045E, new String[] {DS_CONTR }));
                    checkWarn = false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(asMsg.ezUpTime_A.getValue(), asMsg.ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsMsgTxt_A, getRtnMsg(ZZZM9004E));
                        checkWarn = false;
                        continue;
                    }
                    ZYPEZDItemValueSetter.setValue(result.dsContrPk, sMsg.A.no(i).dsContrPk_A);
                    ZYPEZDItemValueSetter.setValue(result.contrHldFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(result.dsContrLastUpdDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
                    ZYPEZDItemValueSetter.setValue(result.dsContrLastUpdPsnCd, cMsg.getUserID());
                    S21FastTBLAccessor.update(result);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(result.getReturnCode())) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsMsgTxt_A, getRtnMsg(NSAM0031E, new String[] {DS_CONTR }));
                        checkWarn = false;
                        EZDConnectionMgr.getInstance().rollback();
                    } else if (!callContractTrackingAPI(cMsg.glblCmpyCd.getValue(), sMsg, i, sMsg.A.no(i).dsContrPk_A.getValue(), null, null, null, null, null, cMsg.svcMemoRsnCd_H.getValue(), cMsg.getUserID(), cMsg.svcCmntTxt.getValue(), null)) {
                        checkWarn = false;
                        EZDConnectionMgr.getInstance().rollback();
                    // START 2017/08/30 K.Kim [QC#20578, ADD]
                    } else if(!updateContrDtl(cMsg, sMsg, i, sMsg.A.no(i).dsContrPk_A.getValue(), sMsg.A.no(i).dsContrCtrlStsCd_A.getValue())){
                        checkWarn = false;
                        EZDConnectionMgr.getInstance().rollback();
                    // END 2017/08/30 K.Kim [QC#20578, ADD]
                    } else {
                        SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();
                        setValue(svcMemoTMsg.glblCmpyCd, cMsg.glblCmpyCd);
                        setValue(svcMemoTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
                        setValue(svcMemoTMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
                        setValue(svcMemoTMsg.svcMemoTpCd, SVC_MEMO_TP.PUT_CONTRACTS_ON_HOLD);
                        setValue(svcMemoTMsg.svcCmntTxt, cMsg.svcCmntTxt);
                        setValue(svcMemoTMsg.dsContrPk, sMsg.A.no(i).dsContrPk_A);
                        setValue(svcMemoTMsg.lastUpdUsrId, cMsg.getUserID());
                        setValue(svcMemoTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
                        setValue(svcMemoTMsg.svcMemoRsnCd, cMsg.svcMemoRsnCd_H);
                        S21FastTBLAccessor.insert(svcMemoTMsg);

                        if (S21FastTBLAccessor.RTNCD_NORMAL.equals(svcMemoTMsg.getReturnCode())) {
                            // START 2017/08/31 K.Kojima [QC#20817,MOD]
                            // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsMsgTxt_A, getRtnMsg(NSAM0200I));
                            DS_CONTR_STS_VTMsg stsMsg = NSAL0630Query.getInstance().getDsContrStsV(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).dsContrPk_A.getValue());
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsContrStsDescTxt_A, stsMsg.dsContrCtrlStsNm);
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsMsgTxt_A, RTRN_MSG_SUCCESS);
                            // END 2017/08/31 K.Kojima [QC#20817,MOD]
                            EZDConnectionMgr.getInstance().commit();
                        } else {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsMsgTxt_A, getRtnMsg(NSAM0032E, new String[] {SVC_MEMO }));
                            checkWarn = false;
                            EZDConnectionMgr.getInstance().rollback();
                        }
                    }
                }
            }
        }
        if (!checkWarn) {
            cMsg.setMessageInfo(NSAM0394W);
        }
        cMsg.setMessageInfo(NSAM0200I);
    }

    /**
     * callContractTrackingAPI
     * @param glblCmpyCd glblCmpyCd
     * @param sMsg NSAL0630SMsg
     * @param index integer
     * @param dsContrPk DS_CONTR_PK
     * @param dsContrDtlPk DS_CONTR_DTL_PK
     * @param dsContrBllgMtrPk DS_CONTR_BLLG_MTR_PK
     * @param dsContrPrcEffPk DS_CONTR_PRC_EFF_PK
     * @param contrPrcEffFromDt CONTR_PRC_EFF_FROM_DT
     * @param contrPrcEffThruDt CONTR_PRC_EFF_THRU_DT
     * @param svcMemoRsnCd SVC_MEMO_RSN_CD
     * @param svcMemoUpdPsnCd SVC_MEMO_UPD_PSN_CD
     * @param svcMemoTxt SVC_MEMO_TXT
     * @param baseChrgFlg BASE_CHAR_FLG
     * @return result (true:OK, false:NG)
     */
    private static boolean callContractTrackingAPI(String glblCmpyCd, NSAL0630SMsg sMsg, int index, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrPrcEffPk, String contrPrcEffFromDt, String contrPrcEffThruDt, String svcMemoRsnCd, String svcMemoUpdPsnCd, String svcMemoTxt, String baseChrgFlg) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);

        if (hasValue(dsContrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.CONTRACT_HEADER);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrPk, dsContrPk);
        }

        if (hasValue(dsContrDtlPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
        }

        if (hasValue(dsContrBllgMtrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        }

        if (hasValue(dsContrPrcEffPk)) {
            if (ZYPConstant.FLG_ON_Y.equals(baseChrgFlg)) {
                ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE_PE);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE_PE);
            }
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrPrcEffPk, dsContrPrcEffPk);
            ZYPEZDItemValueSetter.setValue(pMsg.contrPrcEffFromDt, contrPrcEffFromDt);
            ZYPEZDItemValueSetter.setValue(pMsg.contrPrcEffThruDt, contrPrcEffThruDt);
        }

        if (hasValue(svcMemoRsnCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.stsMemoRsnCd, svcMemoRsnCd);
            ZYPEZDItemValueSetter.setValue(pMsg.stsMemoUpdPsnCd, svcMemoUpdPsnCd);
            ZYPEZDItemValueSetter.setValue(pMsg.stsMemoTxt, svcMemoTxt);
        }

        NSZC077001 api = new NSZC077001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsMsgTxt_A, getRtnMsg(msgId, msgPrms));
            return false;
        }

        return true;
    }

    /**
     * copy To SMsg for Current Page Info
     * @param cMsg NSAL0630CMsg
     * @param sMsg NSAL0630SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0630CMsg cMsg, NSAL0630SMsg sMsg) {

        // NSAL0630_ACMsg -> NSAL0630_ASMsg
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            int targetIdxNum = cMsg.xxPageShowFromNum.getValueInt() + i - 1;
            if (targetIdxNum < sMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(i), null, sMsg.A.get(targetIdxNum), null);
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
    public static void pagenation(NSAL0630CMsg cMsg, NSAL0630SMsg sMsg, int pageFrom) {

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
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }
    // END 2016/11/04 T.Tomita [QC#4210, MOD]

    // START 2017/08/30 K.Kim [QC#20578, ADD]
    private static boolean updateContrDtl(NSAL0630CMsg cMsg, NSAL0630SMsg sMsg, int index, BigDecimal dsContrPk, String dsContrCtrlStsCd) {
        S21SsmEZDResult ssmResult = NSAL0630Query.getInstance().getContrDtlList(cMsg.glblCmpyCd.getValue(), dsContrPk);
        if (ssmResult == null || !ssmResult.isCodeNormal()) {
            return true;
        } else {
            List<Map<String, Object>> contrDtlList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (Map<String, Object> contrDtl : contrDtlList) {
                if (!updateContrDtlSts(cMsg, sMsg, index, contrDtl, dsContrPk, dsContrCtrlStsCd)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean updateContrDtlSts(NSAL0630CMsg cMsg, NSAL0630SMsg sMsg, int index, Map<String, Object>contrDtl, BigDecimal dsContrPk, String dsContrCtrlStsCd) {
        // START 2023/03/09 E.Sanchez [QC#61230, MOD]
        // START 2023/03/02 E.Sanchez [QC#61230, MOD]
        // String dsContrStsCd = NSAL0630Query.getInstance().getDsContrStsCd(cMsg.glblCmpyCd.getValue(), dsContrCtrlStsCd);
        // if (dsContrCtrlStsCd.equals((String) contrDtl.get("DS_CONTR_CTRL_STS_CD"))) {
        // if (hasValue((String) contrDtl.get("DS_CONTR_STS_CD")) && dsContrStsCd.equals((String) contrDtl.get("DS_CONTR_STS_CD"))) {
        // END 2023/03/02 E.Sanchez [QC#61230, MOD]
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrDtlPk, (BigDecimal)contrDtl.get("DS_CONTR_DTL_PK"));
        inMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return true;
        }
        ZYPEZDItemValueSetter.setValue(inMsg.contrHldFlg, ZYPConstant.FLG_ON_Y);
        S21FastTBLAccessor.update(inMsg);
        resultUpdate(cMsg, inMsg.getReturnCode(), "DS_CONTR_DTL");
        if (!callContractTrackingAPI(cMsg.glblCmpyCd.getValue(), sMsg, index, dsContrPk, inMsg.dsContrDtlPk.getValue(), null, null, null, null, cMsg.svcMemoRsnCd_H.getValue(), cMsg.getUserID(), cMsg.svcCmntTxt.getValue(), null)) {
            return false;
        }
        // }
        // END 2023/03/09 E.Sanchez [QC#61230, MOD]

        S21SsmEZDResult bllgMtrResult = NSAL0630Query.getInstance().getBllgMtrList(cMsg.glblCmpyCd.getValue(), (BigDecimal)contrDtl.get("DS_CONTR_DTL_PK"));
        if (bllgMtrResult != null && bllgMtrResult.isCodeNormal()) {
            List<Map<String, Object>> bllgMtrList = (List<Map<String, Object>>) bllgMtrResult.getResultObject();
            for (Map<String, Object> bllgMtr : bllgMtrList) {
                // START 2023/03/09 E.Sanchez [QC#61230, DEL]
                // START 2023/03/02 E.Sanchez [QC#61230, MOD]
                // if (!dsContrCtrlStsCd.equals((String) bllgMtr.get("DS_CONTR_CTRL_STS_CD"))) {
                // if (!hasValue((String) bllgMtr.get("DS_CONTR_STS_CD")) || !dsContrStsCd.equals((String) bllgMtr.get("DS_CONTR_STS_CD"))) {
                // END 2023/03/02 E.Sanchez [QC#61230, MOD]
                //    continue;
                // }
                // END 2023/03/09 E.Sanchez [QC#61230, DEL]
                if (!updateBllgMtrSts(cMsg, sMsg, index, bllgMtr, dsContrPk)) {
                    return false;
                }
            }
        }

        S21SsmEZDResult prcEffResult = NSAL0630Query.getInstance().getPrcEffList(cMsg.glblCmpyCd.getValue(), (BigDecimal)contrDtl.get("DS_CONTR_DTL_PK"));
        if (prcEffResult != null && prcEffResult.isCodeNormal()) {
            List<Map<String, Object>> prcEffList = (List<Map<String, Object>>) prcEffResult.getResultObject();
            for (Map<String, Object> prcEff : prcEffList) {
                // START 2023/03/09 E.Sanchez [QC#61230, DEL]
                // START 2023/03/02 E.Sanchez [QC#61230, MOD]
                // if (!dsContrCtrlStsCd.equals((String) prcEff.get("DS_CONTR_CTRL_STS_CD"))) {
                // if (!hasValue((String) prcEff.get("DS_CONTR_STS_CD")) || !dsContrStsCd.equals((String) prcEff.get("DS_CONTR_STS_CD"))) {
                // END 2023/03/02 E.Sanchez [QC#61230, MOD]
                //    continue;
                // }
                // END 2023/03/09 E.Sanchez [QC#61230, DEL]
                if (!updatePrcEffSts(cMsg, sMsg, index, prcEff, dsContrPk)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean updateBllgMtrSts(NSAL0630CMsg cMsg, NSAL0630SMsg sMsg, int index, Map<String, Object>bllgMtr, BigDecimal dsContrPk) {
        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrBllgMtrPk, (BigDecimal)bllgMtr.get("DS_CONTR_BLLG_MTR_PK"));
        inMsg = (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return true;
        }
        ZYPEZDItemValueSetter.setValue(inMsg.contrHldFlg, ZYPConstant.FLG_ON_Y);
        S21FastTBLAccessor.update(inMsg);
        resultUpdate(cMsg, inMsg.getReturnCode(), "DS_CONTR_BLLG_MTR");
        if (!callContractTrackingAPI(cMsg.glblCmpyCd.getValue(), sMsg, index, dsContrPk, inMsg.dsContrDtlPk.getValue(), inMsg.dsContrBllgMtrPk.getValue(), null, null, null, cMsg.svcMemoRsnCd_H.getValue(), cMsg.getUserID(), cMsg.svcCmntTxt.getValue(), null)) {
            return false;
        }

        return true;
    }

    private static boolean updatePrcEffSts(NSAL0630CMsg cMsg, NSAL0630SMsg sMsg, int index, Map<String, Object> prcEff, BigDecimal dsContrPk) {
        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrPrcEffPk, (BigDecimal) prcEff.get("DS_CONTR_PRC_EFF_PK"));
        inMsg = (DS_CONTR_PRC_EFFTMsg) S21FastTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return true;
        }
        ZYPEZDItemValueSetter.setValue(inMsg.contrHldFlg, ZYPConstant.FLG_ON_Y);
        S21FastTBLAccessor.update(inMsg);
        resultUpdate(cMsg, inMsg.getReturnCode(), "DS_CONTR_PRC_EFF");
        if (!callContractTrackingAPI(cMsg.glblCmpyCd.getValue(), sMsg, index, dsContrPk, inMsg.dsContrDtlPk.getValue(), inMsg.dsContrBllgMtrPk.getValue(), inMsg.dsContrPrcEffPk.getValue(),
                inMsg.contrPrcEffFromDt.getValue(),inMsg.contrPrcEffThruDt.getValue(), cMsg.svcMemoRsnCd_H.getValue(), cMsg.getUserID(), cMsg.svcCmntTxt.getValue(), inMsg.baseChrgFlg.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * Return Result Code.
     * @param cMsg
     * @param returnCode
     * @param msg
     */
    private static void resultUpdate(NSAL0630CMsg cMsg, String returnCode, String msg) {

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(returnCode)) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {msg });
            return;
        }
    }
    // END 2017/08/30 K.Kim [QC#20578, ADD]
}
