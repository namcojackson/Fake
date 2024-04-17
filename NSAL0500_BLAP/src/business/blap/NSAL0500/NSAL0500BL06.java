/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0500;

import static business.blap.NSAL0500.constant.NSAL0500Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCDateItem;
import parts.common.EZDCItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0500.common.NSAL0500CommonLogic;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_SUB_CONTRTMsg;
import business.db.DS_SUB_CONTR_MTRTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MEMOTMsg;
import business.parts.NSZC001001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Sub Contract Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/07   Hitachi         T.Aoyagi        Create          N/A
 * 2016/02/10   Hitachi         K.Kasai         Update          QC#3707,3709
 * 2016/02/19   Hitachi         K.Kasai         Update          QC#3689
 * 2016/03/28   Hitachi         K.Kasai         Update          QC#4576
 * 2017/03/01   Hitachi         T.Mizuki        Update          QC#17575
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 * 2018/05/16   CITS            M.Naito         Update          QC#25892
 * 2018/05/25   Hitachi         K.Kitachi       Update          QC#25950
 * 2018/12/10   Hitachi         K.Fujimoto      Update          QC#29079
 */
public class NSAL0500BL06 extends S21BusinessHandler implements ZYPConstant {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0500CMsg cMsg = (NSAL0500CMsg) arg0;
        NSAL0500SMsg sMsg = (NSAL0500SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0500Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0500Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NSAL0500Scrn00_CMN_Submit(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg) {

        if (!checkScreenItem(cMsg)) {
            return;
        }

        // add start 2016/02/10 CSA Defect#3709
        SVC_MACH_MSTRTMsg outTMsg = getSvcMachMstr(cMsg);
        String prfTechCd = null;
        if (outTMsg != null) {
            // START 2018/05/16 M.Naito [QC#25892, MOD]
//            prfTechCd = outTMsg.prfTechCd.getValue();
            prfTechCd = outTMsg.reqTechCd.getValue();
            // END 2018/05/16 M.Naito [QC#25892, MOD]
        }
        NSZC001001PMsg pMsg = new NSZC001001PMsg();
        if (isSvcMachMstrUpdateTarget(cMsg, prfTechCd)) {
          //update Service Machine Master
            setParamForUpdate(pMsg, cMsg, outTMsg);
            new NSZC001001().execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                S21ApiMessage msg = msgList.get(0);
                cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            }
        }
        // add end 2016/02/10 CSA Defect#3709

        // DB Registration
        if (!createUpdateDsSubContr(cMsg)) {
            return;
        }
        if (hasValue(cMsg.svcCmntTxt_AD)) {
            if (!createUpdateSvcMemo(cMsg)) {
                return;
            }
        }
        cMsg.setMessageInfo(ZZM8100I);
    }

    // add start 2016/02/10 CSA Defect#3709
    /**
     * get SVC_MACH_MSTR
     * @param cMsg NSAL0500CMsg
     * @return SVC_MACH_MSTRTMsg
     */
    private SVC_MACH_MSTRTMsg getSvcMachMstr(NSAL0500CMsg cMsg) {
        SVC_MACH_MSTRTMsg inTMsg = new SVC_MACH_MSTRTMsg();
        setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inTMsg.svcMachMstrPk, cMsg.svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(inTMsg);
    }

    /**
     * set Param For Update
     * @param pMsg NSZC001001PMsg
     * @param cMsg NSAL0500CMsg
     * @param exstTMsg SVC_MACH_MSTRTMsg
     */
    private void setParamForUpdate(NSZC001001PMsg pMsg, NSAL0500CMsg cMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        EZDMsg.copy(exstTMsg, null, pMsg, null);
        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        //mode upd_attr
        setValue(pMsg.xxModeCd, ProcessMode.UPDATE_ATTRIBUTE.code);
        // START 2018/05/16 M.Naito [QC#25892, MOD]
        if (!hasValue(cMsg.contrTrmnFlg) || !FLG_ON_Y.equals(cMsg.contrTrmnFlg.getValue())) {
//            setValue(pMsg.prfTechCd, cMsg.techTocCd);
            setValue(pMsg.reqTechCd, cMsg.techTocCd);
        } else {
//            pMsg.prfTechCd.clear();
            pMsg.reqTechCd.clear();
        }
        // END 2018/05/16 M.Naito [QC#25892, MOD]
    }

    /**
     * check for SVC_MACH_MSTR update
     * @param cMsg
     * @param prfTechCd
     * @return boolean
     */
    private boolean isSvcMachMstrUpdateTarget(NSAL0500CMsg cMsg, String prfTechCd) {
        if ((!hasValue(cMsg.contrTrmnFlg) || !FLG_ON_Y.equals(cMsg.contrTrmnFlg.getValue())) && hasValue(cMsg.techTocCd) && !prfTechCd.equals(cMsg.techTocCd.getValue())) {
            return true;
        } else if (hasValue(cMsg.contrTrmnFlg) && FLG_ON_Y.equals(cMsg.contrTrmnFlg.getValue()) && hasValue(prfTechCd)) {
            return true;
        }
        return false;
    }
    // add end 2016/02/10 CSA Defect#3709

    private boolean checkScreenItem(NSAL0500CMsg cMsg) {

        // START 2017/12/22 [QC#22831, MOD]
        mandatoryCheck(cMsg.vndCd, "Supplier Site");
        // END   2017/12/22 [QC#22831, MOD]
        mandatoryCheck(cMsg.techTocCd, "Technician Code");
        mandatoryCheck(cMsg.effFromDt, "Effective Date From");
        mandatoryCheck(cMsg.effThruDt, "Effective Date Thru");
        // mod start 2017/03/01 CSA QC#17575
        mandatoryCheck(cMsg.bllgCycleCd, "Billing Cycle");
        // mod end 2017/03/01 CSA QC#17575

        //check Fleet#
        if (FLG_ON_Y.equals(cMsg.dlrFleetFlg.getValue())) {
            mandatoryCheck(cMsg.dlrFleetNum, "Fleet#");
        }

        // check Master
        NSAL0500CommonLogic.searchVndNm(cMsg);
        NSAL0500CommonLogic.searchTech(cMsg);

        if (NSAL0500CommonLogic.isError(cMsg)) {
            return false;
        }

        // check Effective Date
        checkEffDate(cMsg);

        // check Memo limit
        checkMemoLimit(cMsg);

        if (NSAL0500CommonLogic.isError(cMsg)) {
            return false;
        }
        return true;
    }

    private void mandatoryCheck(EZDCItem targetItem, String... args) {
        if (!hasValue(targetItem)) {
            targetItem.setErrorInfo(1, ZZM9000E, args);
        }
    }

    private void checkEffDate(NSAL0500CMsg cMsg) {

        if (compare(cMsg.effFromDt, cMsg.effThruDt) > 0) {
            cMsg.effFromDt.setErrorInfo(1, NSAM0064E, new String[]{"Effective From"});
            cMsg.effThruDt.setErrorInfo(1, NSAM0064E, new String[]{"Effective Thru"});
            return;
        }

        int dupliCount = NSAL0500Query.getInstance().countDuplicateContr(cMsg);
        if (dupliCount > 0) {
            cMsg.effFromDt.setErrorInfo(1, NSAM0035E, new String[]{"Effective period"});
            cMsg.effThruDt.setErrorInfo(1, NSAM0035E, new String[]{"Effective period"});
            return;
        }

        if (compare(cMsg.effFromDt, cMsg.contrEffFromDt) < 0) {
            cMsg.effFromDt.setErrorInfo(1, NSAM0358E, new String[]{"Effective Date From", cMsg.contrEffFromDt.getValue(), cMsg.contrEffThruDt.getValue()});
            return;
        }

        if (compare(cMsg.effThruDt, cMsg.contrEffThruDt) > 0) {
            cMsg.effThruDt.setErrorInfo(1, NSAM0358E, new String[]{"Effective Date Thru", cMsg.contrEffFromDt.getValue(), cMsg.contrEffThruDt.getValue()});
            return;
        }
        // add start 2016/02/10 CSA Defect#3707
        if (FLG_ON_Y.equals(cMsg.contrTrmnFlg.getValue()) && ZYPDateUtil.compare(cMsg.effThruDt.getValue(), ZYPDateUtil.getSalesDate()) > 0) {
            cMsg.effThruDt.setErrorInfo(1, NSAM0425E, new String[]{});
            return;
        }
        // add end 2016/02/10 CSA Defect#3707
    }

    private void checkMemoLimit(NSAL0500CMsg cMsg) {

        StringBuilder sbSvcComntTxt = new StringBuilder();
        sbSvcComntTxt.append(cMsg.svcCmntTxt_71.getValue());
        sbSvcComntTxt.append(cMsg.svcCmntTxt_72.getValue());
        sbSvcComntTxt.append(cMsg.svcCmntTxt_73.getValue());
        sbSvcComntTxt.append(cMsg.svcCmntTxt_74.getValue());
        sbSvcComntTxt.append(cMsg.svcCmntTxt_75.getValue());
        sbSvcComntTxt.append(cMsg.svcCmntTxt_76.getValue());
        sbSvcComntTxt.append(cMsg.svcCmntTxt_77.getValue());
        sbSvcComntTxt.append(cMsg.svcCmntTxt_78.getValue());
        sbSvcComntTxt.append(SEPARATOR);
        sbSvcComntTxt.append(cMsg.svcCmntTxt_AD.getValue());

        if (sbSvcComntTxt.length() > SINGLE_CMNT_MAX_LENGTH * CMNT_MAX_COUNT) {
            cMsg.svcCmntTxt_AD.setErrorInfo(1, NSAM0359E, new String[]{"Addtional Comment"});
        }
    }

    private int compare(EZDCDateItem item1, EZDCDateItem item2) {

        return ZYPDateUtil.compare(item1.getValue(), item2.getValue());
    }

    private boolean createUpdateDsSubContr(NSAL0500CMsg cMsg) {
        if (hasValue(cMsg.dsSubContrPk)) {
            updateDsSubContr(cMsg);
        } else {
            createDsSubContr(cMsg);
        }
        if (NSAL0500CommonLogic.isError(cMsg)) {
            return false;
        }
        return true;
    }

    private void updateDsSubContr(NSAL0500CMsg cMsg) {

        DS_SUB_CONTRTMsg dsSubContrTMsg = NSAL0500Query.getInstance().getDsSubContrForUpdate(cMsg);

        //exclusive check
        if (dsSubContrTMsg == null) {
            cMsg.setMessageInfo(NZZM0003E);
            return;
        }
        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime.getValue(), cMsg.ezUpTimeZone.getValue(),
                dsSubContrTMsg.ezUpTime.getValue(), dsSubContrTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(NZZM0003E);
            return;
        }
        setCheckBoxValue(cMsg);
        EZDMsg.copy(cMsg, null, dsSubContrTMsg, null);

        EZDTBLAccessor.update(dsSubContrTMsg);
        if (!RTNCD_NORMAL.equals(dsSubContrTMsg.getReturnCode())) {
            cMsg.setMessageInfo(ZZZM9013E , new String[]{dsSubContrTMsg.getReturnCode()});
        }
        // Add Start 2018/12/10 K.Fujimoto QC#29079
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            NSAL0500_ECMsg ecMsg = cMsg.E.no(i);
            if (hasValue(ecMsg.dsSubContrMtrPk_E0)) {
                updateDsSubContrMtr(cMsg, ecMsg);
            } else {
                createDsSubContrMtr(cMsg, ecMsg);
            }
        }
        // delete DS Sub Contract
        deleteDsSubContr(cMsg);
        // Add End   2018/12/10 K.Fujimoto QC#29079
    }

    private void createDsSubContr(NSAL0500CMsg cMsg) {

        DS_SUB_CONTRTMsg dsSubContrTMsg = new DS_SUB_CONTRTMsg();
        setCheckBoxValue(cMsg);
        EZDMsg.copy(cMsg, null, dsSubContrTMsg, null);
        BigDecimal dsSubContrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_SUB_CONTR_SQ);
        setValue(dsSubContrTMsg.dsSubContrPk, dsSubContrPk);

        EZDTBLAccessor.insert(dsSubContrTMsg);
        if (!RTNCD_NORMAL.equals(dsSubContrTMsg.getReturnCode())) {
            cMsg.setMessageInfo(ZZZM9012E , new String[]{dsSubContrTMsg.getReturnCode()});
        }
        setValue(cMsg.dsSubContrPk, dsSubContrPk);
        setValue(cMsg.dsSubContrPk_BK, dsSubContrPk);
        // Add Start 2018/12/10 K.Fujimoto QC#29079
        createDsSubContrMtrAll(cMsg);
        // Add End   2018/12/10 K.Fujimoto QC#29079
        return;
    }
    
    // Add Start 2018/12/10 K.Fujimoto QC#29079
    private void createDsSubContrMtrAll(NSAL0500CMsg cMsg) {
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            createDsSubContrMtr(cMsg, cMsg.E.no(i));
        }
    }

    private void createDsSubContrMtr(NSAL0500CMsg cMsg, NSAL0500_ECMsg ecMsg) {
        DS_SUB_CONTR_MTRTMsg inTMsg = setDsSubContrMtrTMsg(cMsg, ecMsg);
        BigDecimal dsSubContrMtrtPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_SUB_CONTR_MTR_SQ);
        setValue(inTMsg.dsSubContrMtrPk, dsSubContrMtrtPk);
        EZDTBLAccessor.insert(inTMsg);
        if (!RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            cMsg.setMessageInfo(ZZZM9012E , new String[]{inTMsg.getReturnCode()});
            return;
        }
    }

    private void updateDsSubContrMtr(NSAL0500CMsg cMsg, NSAL0500_ECMsg ecMsg) {
        DS_SUB_CONTR_MTRTMsg inTMsg = setDsSubContrMtrTMsg(cMsg, ecMsg);
        EZDTBLAccessor.update(inTMsg);
        if (!RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            cMsg.setMessageInfo(ZZZM9012E , new String[]{inTMsg.getReturnCode()});
            return;
        }
    }

    private void deleteDsSubContr(NSAL0500CMsg cMsg) {
        S21SsmEZDResult ssmResult = NSAL0500Query.getInstance().getContrDtlInfo(cMsg);
        if (!ssmResult.isCodeNormal()) {
            return;
        }
        BigDecimal dsContrDtlPk = NSAL0500CommonLogic.getDsContrDtlPk(ssmResult, cMsg);
        
        S21SsmEZDResult rslt = NSAL0500Query.getInstance().getDsSubContrMtrForDelete(cMsg, dsContrDtlPk);
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();
            for (int i = 0; i < rsltCnt; i++) {
                DS_SUB_CONTR_MTRTMsg inTMsg = new DS_SUB_CONTR_MTRTMsg();
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, (String) rsltMap.get("GLBL_CMPY_CD"));
                ZYPEZDItemValueSetter.setValue(inTMsg.dsSubContrMtrPk, (BigDecimal) rsltMap.get("DS_SUB_CONTR_MTR_PK"));
                EZDTBLAccessor.logicalRemove(inTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Sub Contract Meter" });
                    return;
                }
            }
        }
    }

    private DS_SUB_CONTR_MTRTMsg setDsSubContrMtrTMsg(NSAL0500CMsg cMsg, NSAL0500_ECMsg ecMsg) {
        DS_SUB_CONTR_MTRTMsg inTMsg = new DS_SUB_CONTR_MTRTMsg();
        setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inTMsg.dsSubContrMtrPk, ecMsg.dsSubContrMtrPk_E0);
        setValue(inTMsg.dsSubContrPk, cMsg.dsSubContrPk);
        setValue(inTMsg.bllgMtrLbCd, ecMsg.bllgMtrLbCd_E0);
        setValue(inTMsg.mtrAlwncCnt, ecMsg.mtrAlwncCnt_E0);
        setValue(inTMsg.prcMtrRate, ecMsg.prcMtrRate_E0);
        return inTMsg;
    }
    // Add End   2018/12/10 K.Fujimoto QC#29079

    private boolean createUpdateSvcMemo(NSAL0500CMsg cMsg) {

        // mod start 2016/03/28 CSA Defect#4576
        //String svcMemoTp = SVC_MEMO_TP_SEQ_71;
        String svcMemoTp = SVC_MEMO_TP.UPDATE_SUB_CONTRACT1;
        // mod end 2016/03/28 CSA Defect#4576
        String sysTime = ZYPDateUtil.getCurrentSystemTime(TS_STORE_PATTERN);
        SVC_MEMOTMsg svcMemoTMsg = NSAL0500Query.getInstance().getLtstSvcMemo(cMsg);

        // Latest Comment + separator + Additional Comment
        StringBuilder svcCmntTxt = getSvcComntTxt(cMsg, svcMemoTMsg);

        if (svcMemoTMsg == null) {
            // new
            if (!createSvcMemo(cMsg, svcCmntTxt, svcMemoTp, sysTime)) {
                return false;
            }
            deleteSvcCmntTxt(svcCmntTxt);
        }

        if (svcMemoTMsg != null) {
            svcMemoTp = svcMemoTMsg.svcMemoTpCd.getValue();

            if (svcMemoTMsg.svcCmntTxt.getValue().length() < SINGLE_CMNT_MAX_LENGTH) {
                // update latest SVC_MEMO
                if (!updateSvcMemo(cMsg, svcMemoTMsg, svcCmntTxt, sysTime)) {
                    return false;
                }
                deleteSvcCmntTxt(svcCmntTxt);
            }
        }

        while (svcCmntTxt.length() > 0) {
            svcMemoTp = incrementSeq(svcMemoTp);

            // create SVC_MEMO
            if (!createSvcMemo(cMsg, svcCmntTxt, svcMemoTp, sysTime)) {
                return false;
            }
            deleteSvcCmntTxt(svcCmntTxt);
        }

        return true;
    }

    private StringBuilder getSvcComntTxt(NSAL0500CMsg cMsg, SVC_MEMOTMsg svcMemoTMsg) {

        StringBuilder sbSvcCmntTxt = new StringBuilder();
        if (svcMemoTMsg != null) {
            sbSvcCmntTxt.append(svcMemoTMsg.svcCmntTxt.getValue());
            // START 2018/05/25 K.Kitachi [QC#25950, ADD]
            sbSvcCmntTxt.append("\r\n");
            // END 2018/05/25 K.Kitachi [QC#25950, ADD]
        }
        sbSvcCmntTxt.append(SEPARATOR);
        sbSvcCmntTxt.append(cMsg.svcCmntTxt_AD.getValue());
        return sbSvcCmntTxt;
    }

    private boolean createSvcMemo(NSAL0500CMsg cMsg, StringBuilder svcCmntTxt, String svcMemoTp, String sysTime) {

        SVC_MEMOTMsg inTMsg = new SVC_MEMOTMsg();
        BigDecimal svcMemoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ);
        setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inTMsg.svcMemoPk, svcMemoPk);
        setValue(inTMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(inTMsg.svcMemoTpCd, svcMemoTp);
        // add start 2016/02/22 CSA Defect#3689
        String svcMemoRsnCd = ZYPCodeDataUtil.getVarCharConstValue(SVC_MEMO_RSN_FOR_SUB_CONTR, cMsg.glblCmpyCd.getValue());
        setValue(inTMsg.svcMemoRsnCd, svcMemoRsnCd);
        // add end 2016/02/22 CSA Defect#3689
        setValue(inTMsg.svcCmntTxt, cutSvcCmnt(svcCmntTxt, SINGLE_CMNT_MAX_LENGTH));
        setValue(inTMsg.dsContrDtlPk, cMsg.dsContrDtlPk);
        setValue(inTMsg.dsSubContrPk, cMsg.dsSubContrPk);
        setValue(inTMsg.lastUpdUsrId, getContextUserInfo().getUserId());
        setValue(inTMsg.lastUpdTs, sysTime);

        EZDTBLAccessor.insert(inTMsg);
        if (!RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            cMsg.setMessageInfo(ZZZM9012E , new String[]{inTMsg.getReturnCode()});
            return false;
        }
        return true;
    }

    private boolean updateSvcMemo(NSAL0500CMsg cMsg, SVC_MEMOTMsg svcMemoTMsg, StringBuilder svcCmntTxt, String sysTime) {

        setValue(svcMemoTMsg.svcCmntTxt, cutSvcCmnt(svcCmntTxt, SINGLE_CMNT_MAX_LENGTH));
        setValue(svcMemoTMsg.lastUpdUsrId, getContextUserInfo().getUserId());
        setValue(svcMemoTMsg.lastUpdTs, sysTime);

        EZDTBLAccessor.update(svcMemoTMsg);
        if (!RTNCD_NORMAL.equals(svcMemoTMsg.getReturnCode())) {
            cMsg.setMessageInfo(ZZZM9013E , new String[]{svcMemoTMsg.getReturnCode()});
            return false;
        }
        return true;
    }

    private void setCheckBoxValue(NSAL0500CMsg cMsg) {

        changeBlankToN(cMsg.contrTrmnFlg
                    , cMsg.prepdMaintFlg
                    , cMsg.splyInclFlg
                    , cMsg.dlrFleetFlg);
    }

    private void changeBlankToN(EZDCStringItem... items) {

        for (EZDCStringItem item : items) {
            if (!hasValue(item)) {
                setValue(item, FLG_OFF_N);
            }
        }
    }

    private void deleteSvcCmntTxt(StringBuilder sb) {

        if (sb.length() <= SINGLE_CMNT_MAX_LENGTH) {
            sb.delete(0, sb.length());
        } else {
            sb.delete(0, SINGLE_CMNT_MAX_LENGTH);
        }
    }

    private String incrementSeq(String svcMemoTp) {

        int seq = Integer.parseInt(svcMemoTp);
        return String.valueOf(seq + 1);
    }

    private String cutSvcCmnt(StringBuilder sb, int len) {

        if (sb.length() <= len) {
            return sb.toString();
        }
        return sb.substring(0, len);
    }
}
