/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2410;

import static business.blap.NWAL2410.constant.NWAL2410Constant.DISP_CODE;
import static business.blap.NWAL2410.constant.NWAL2410Constant.FORMAT_TIMESTAMP;
import static business.blap.NWAL2410.constant.NWAL2410Constant.KEY_CODE_DISP_PK;
import static business.blap.NWAL2410.constant.NWAL2410Constant.MAINTENANCE_BRANCH;
import static business.blap.NWAL2410.constant.NWAL2410Constant.NWAM0181E;
import static business.blap.NWAL2410.constant.NWAL2410Constant.NWAM0923E;
import static business.blap.NWAL2410.constant.NWAL2410Constant.NWAM0926E;
import static business.blap.NWAL2410.constant.NWAL2410Constant.NZZM0003E;
import static business.blap.NWAL2410.constant.NWAL2410Constant.PROC_MODE_CD_CRT;
import static business.blap.NWAL2410.constant.NWAL2410Constant.PROC_MODE_CD_DEL;
import static business.blap.NWAL2410.constant.NWAL2410Constant.PROC_MODE_CD_UPD;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL2410.common.NWAL2410CommonLogic;
import business.db.DOC_MGT_BRTMsg;
import business.parts.NWZC230001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC230001.NWZC230001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL2410BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/25   Fujitsu         N.Aoyama        Create          QC#16740
 * 2017/02/20   Fujitsu         N.Aoyama        Update          QC#17676
 *</pre>
 */
public class NWAL2410BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2410CMsg bizMsg = (NWAL2410CMsg) cMsg;
            NWAL2410SMsg glblMsg = (NWAL2410SMsg) sMsg;

            if ("NWAL2410Scrn00_CMN_Submit".equals(screenAplID)) {
                // root change first or second
                if (ZYPConstant.FLG_OFF_N.equals(bizMsg.xxRqstFlg.getValue())) {
                    // FLG_OFF_N
                    // first logic
                    doProcess_NWAL2410Scrn00_CMN_Submit1_UPD(bizMsg, glblMsg);
                } else {
                    // FLG_ON_Y
                    // second logic
                    doProcess_NWAL2410Scrn00_CMN_Submit2_API(bizMsg, glblMsg);
                }
            } else if ("NWAL2200Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event 1 DB UPDATE
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2410Scrn00_CMN_Submit1_UPD(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg) {

        NWAL2410CommonLogic.saveCurrentPageToSMsg(bizMsg, glblMsg);
        bizMsg.setCommitSMsg(true);

        // Input date is Common Check and Duplicate Check on Screen
        if (!NWAL2410CommonLogic.inputCheck(bizMsg, glblMsg, getGlobalCompanyCode())) {
            return;
        }

        // create insert/update/delete object
        List<DOC_MGT_BRTMsg> insList = new ArrayList<DOC_MGT_BRTMsg>();
        List<DOC_MGT_BRTMsg> updList = new ArrayList<DOC_MGT_BRTMsg>();
        List<DOC_MGT_BRTMsg> delList = new ArrayList<DOC_MGT_BRTMsg>();
        // 2017/02/20 QC#17676 ADD START
        List<DOC_MGT_BRTMsg> removeList = new ArrayList<DOC_MGT_BRTMsg>();
        // 2017/02/20 QC#17676 ADD E N D
        // 2017/02/20 QC#17676 UPD START
        // createRegistObject(glblMsg, insList, updList, delList);
        createRegistObject(glblMsg, insList, updList, delList, removeList);

        // if (!insList.isEmpty() || !updList.isEmpty() ||
        // !delList.isEmpty()) {
        if (!insList.isEmpty() || !updList.isEmpty() || !delList.isEmpty() || !removeList.isEmpty()) {
            // 2017/02/20 QC#17676 UPD E N D

            // input(three object) check for DB
            if (isDbDuplicateCheck(bizMsg, glblMsg, insList, updList, delList)) {
                return;
            }

            // DB UPDATE
            if (!delList.isEmpty()) {
                deleteDocMgtBrTable(bizMsg, delList);
                if ("E".equals(bizMsg.getMessageKind())) {
                    return;
                }
            }

            // 2017/02/20 QC#17676 ADD START
            if (!removeList.isEmpty()) {
                logicalRemoveDocMgtBrTable(bizMsg, removeList);
                if ("E".equals(bizMsg.getMessageKind())) {
                    return;
                }
            }
            // 2017/02/20 QC#17676 ADD E N D

            if (!insList.isEmpty()) {
                for (DOC_MGT_BRTMsg insTmsg : insList) {
                    // clear
                    insTmsg.docMgtIntfcProcTs.clear();
                    insTmsg.docMgtIntfcStsCd.clear();
                    ZYPEZDItemValueSetter.setValue(insTmsg.procModeCd, PROC_MODE_CD_CRT);
                    // Insert
                    EZDTBLAccessor.create(insTmsg);
                    if (RTNCD_DUPLICATE.equals(insTmsg.getReturnCode())) {
                        setInsertErrorInfo(bizMsg, glblMsg, insTmsg.docMgtOrgCd.getValue(), insTmsg.docMgtScanBrCd.getValue());
                        return;
                    }
                }
            }

            if (!updList.isEmpty()) {
                updateDocMgtBrTable(bizMsg, updList);
                if ("E".equals(bizMsg.getMessageKind())) {
                    return;
                }
            }
        }

    }

    /**
     * CMN_Submit Event 2 call API Therefore
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2410Scrn00_CMN_Submit2_API(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg) {

        // ran for API NWZC2300
        List<NWZC230001PMsg> pMsgList = new ArrayList<NWZC230001PMsg>();
        NWZC230001 thereforeApi = new NWZC230001();

        S21SsmEZDResult ssmResult = NWAL2410Query.getInstance().getSendThereforeData();

        if (!ssmResult.isCodeNotFound()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

            BigDecimal catgNum = null;
            S21SsmEZDResult catgNumResult = NWAL2410Query.getInstance().getdocMgtCatgNum();
            if (!catgNumResult.isCodeNotFound()) {
                List<Map<String, String>> catgtList = (List<Map<String, String>>) catgNumResult.getResultObject();
                for (int i = 0; i < catgtList.size(); i++) {
                    Map<String, String> map = catgtList.get(i);
                    catgNum = new BigDecimal((String) map.get("DOC_MGT_CATG_CD"));
                }
            }

            if (catgNum == null) {
                bizMsg.setMessageInfo(NWAM0181E, new String[] {MAINTENANCE_BRANCH });
                return;
            }

            int i = 0;
            for (; i < resultList.size(); i++) {

                NWZC230001PMsg apiMsg = new NWZC230001PMsg();
                Map<String, String> map = resultList.get(i);
                ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, map.get("GLBL_CMPY_CD"));
                ZYPEZDItemValueSetter.setValue(apiMsg.docMgtCatgNum, catgNum);
                ZYPEZDItemValueSetter.setValue(apiMsg.docMgtBizDocNum, map.get("DOC_MGT_SCAN_BR_CD"));
                ZYPEZDItemValueSetter.setValue(apiMsg.docMgtPrntDocNum, map.get("DOC_MGT_ORG_CD"));
                ZYPEZDItemValueSetter.setValue(apiMsg.procModeCd, map.get("PROC_MODE_CD"));
                pMsgList.add(apiMsg);

            }

            if (i > 0) {
                thereforeApi.execute(pMsgList, ONBATCH_TYPE.ONLINE);

                boolean apiErrorFlg = false;
                for (NWZC230001PMsg pMsg : pMsgList) {
                    DOC_MGT_BRTMsg updStatusMsg = new DOC_MGT_BRTMsg();
                    ZYPEZDItemValueSetter.setValue(updStatusMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(updStatusMsg.docMgtOrgCd, pMsg.docMgtPrntDocNum.getValue());
                    ZYPEZDItemValueSetter.setValue(updStatusMsg.docMgtScanBrCd, pMsg.docMgtBizDocNum.getValue());

                    DOC_MGT_BRTMsg docMgtBrTMsg = findDocMgtBrTableByKeyForUpdateNoCheck(updStatusMsg);
                    if (docMgtBrTMsg == null) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return;
                    }

                    // DOC_MGT_INTFC_STS_CD 00
                    if (DOC_MGT_INTFC_STS.NEW.equals(pMsg.docMgtIntfcStsCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtIntfcStsCd, pMsg.docMgtIntfcStsCd.getValue());
                        if (PROC_MODE_CD_DEL.equals(pMsg.procModeCd.getValue())) {
                            ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtIntfcProcTs, ZYPDateUtil.getCurrentSystemTime(FORMAT_TIMESTAMP));
                        } else {
                            ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtIntfcProcTs, pMsg.docMgtIntfcProcTs.getValue());
                        }
                    } else {
                        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtIntfcStsCd, DOC_MGT_INTFC_STS.ERROR);
                        apiErrorFlg = true;
                    }

                    S21FastTBLAccessor.update(docMgtBrTMsg);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(docMgtBrTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NWAM0926E, new String[] {DISP_CODE });
                        return;
                    }

                    if (PROC_MODE_CD_DEL.equals(pMsg.procModeCd.getValue()) && DOC_MGT_INTFC_STS.NEW.equals(pMsg.docMgtIntfcStsCd.getValue())) {

                        S21FastTBLAccessor.logicalRemoveByPartialValue(updStatusMsg, new String[] {"glblCmpyCd", "docMgtOrgCd", "docMgtScanBrCd" });
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(docMgtBrTMsg.getReturnCode())) {
                            bizMsg.setMessageInfo(NWAM0926E, new String[] {DISP_CODE });
                            return;
                        }

                    }

                }

                if (apiErrorFlg) {
                    bizMsg.xxEdtModeFlg.setValue(ZYPConstant.FLG_ON_Y);
                }
            }
        }

    }

    /**
     * deleteDocMgtBrTable
     * @param bizMsg NWAL2410CMsg
     * @param delList List<DOC_MGT_BRTMsg>
     */
    private void deleteDocMgtBrTable(NWAL2410CMsg bizMsg, List<DOC_MGT_BRTMsg> delList) {

        // for delete
        for (DOC_MGT_BRTMsg delMsg : delList) {

            DOC_MGT_BRTMsg docMgtBrTMsg = findDocMgtBrTableByKeyForUpdateCheckEzTime(delMsg);
            if (docMgtBrTMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            }
            // clear
            delMsg.docMgtIntfcProcTs.clear();
            delMsg.docMgtIntfcStsCd.clear();
            ZYPEZDItemValueSetter.setValue(delMsg.procModeCd, PROC_MODE_CD_DEL);

        }

        int updCnt = delList.size();
        int rsltCnt = S21FastTBLAccessor.update(delList.toArray(new DOC_MGT_BRTMsg[updCnt]));
        if (rsltCnt != updCnt) {
            bizMsg.setMessageInfo(NWAM0926E, new String[] {DISP_CODE });
            return;
        }
        return;

    }

    // 2017/02/20 QC#17676 ADD START
    /**
     * logicalRemoveDocMgtBrTable
     * @param bizMsg NWAL2410CMsg
     * @param removeList List<DOC_MGT_BRTMsg>
     */
    private void logicalRemoveDocMgtBrTable(NWAL2410CMsg bizMsg, List<DOC_MGT_BRTMsg> removeList) {

        // for delete
        for (DOC_MGT_BRTMsg removeMsg : removeList) {

            DOC_MGT_BRTMsg retRemoveDocMgtBrTMsg = findDocMgtBrTableByKeyForUpdateCheckEzTime(removeMsg);
            if (retRemoveDocMgtBrTMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            }
            S21FastTBLAccessor.logicalRemoveByPartialValue(retRemoveDocMgtBrTMsg, new String[] {"glblCmpyCd", "docMgtOrgCd", "docMgtScanBrCd" });
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(retRemoveDocMgtBrTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NWAM0926E, new String[] {DISP_CODE });
                return;
            }
        }
        return;

    }

    // 2017/02/20 QC#17676 ADD E N D

    /**
     * createRegistObject
     * @param glblMsg Global Msg
     * @param insList List<DOC_MGT_BRTMsg>
     * @param updList List<DOC_MGT_BRTMsg>
     * @param delList List<DOC_MGT_BRTMsg>
     * @param removeList List<DOC_MGT_BRTMsg>
     */
    private void createRegistObject(NWAL2410SMsg glblMsg, List<DOC_MGT_BRTMsg> insList, List<DOC_MGT_BRTMsg> updList, List<DOC_MGT_BRTMsg> delList, List<DOC_MGT_BRTMsg> removeList) {

        NWAL2410_ASMsgArray inMsgArray = glblMsg.A;
        NWAL2410_BSMsgArray origMsgArray = glblMsg.B;

        for (int i = 0; i < origMsgArray.getValidCount(); i++) {
            NWAL2410_BSMsg origMsg = origMsgArray.no(i);
            if (ZYPCommonFunc.hasValue(origMsg.ezUpTime_A)) {
                NWAL2410_ASMsg keyMsg = getKeyMsg(inMsgArray, origMsg);
                if (keyMsg == null) {
                    DOC_MGT_BRTMsg delTMsg = new DOC_MGT_BRTMsg();
                    setNWAL2410BSMsgToDocMgtBrTMsg(origMsg, delTMsg);
                    // 2017/02/20 QC#17676 UPD START
                    if (ZYPConstant.FLG_ON_Y.equals(delTMsg.actvFlg.getValue())) {
                        delList.add(delTMsg);
                    } else {
                        removeList.add(delTMsg);
                    }
                    // 2017/02/20 QC#17676 UPD E N D
                }
            }
        }

        for (int i = 0; i < inMsgArray.getValidCount(); i++) {
            NWAL2410_ASMsg inMsg = inMsgArray.no(i);
            if (!ZYPCommonFunc.hasValue(inMsg.ezUpTime_A)) {
                NWAL2410_BSMsg origMsg = getOrigMsg(origMsgArray, inMsg);
                if (origMsg == null) {
                    DOC_MGT_BRTMsg insTMsg = new DOC_MGT_BRTMsg();
                    setNWAL2410ASMsgToDocMgtBrTMsg(inMsg, insTMsg);
                    insList.add(insTMsg);
                    continue;
                } else {
                    ZYPEZDItemValueSetter.setValue(inMsg.ezUpTime_A, origMsg.ezUpTime_A);
                    ZYPEZDItemValueSetter.setValue(inMsg.ezUpTimeZone_A, origMsg.ezUpTimeZone_A);
                }
            }

            if (isUpdate(origMsgArray, inMsg)) {
                DOC_MGT_BRTMsg updTMsg = new DOC_MGT_BRTMsg();
                setNWAL2410ASMsgToDocMgtBrTMsg(inMsg, updTMsg);
                updList.add(updTMsg);
            }
        }

    }

    /**
     * set NWAL2410ASMsg To DocMgtBrTMsg
     * @param inMsg NWAL2410_ASMsg
     * @param docMgtBrTMsg DOC_MGT_BRTMsg
     */
    private void setNWAL2410ASMsgToDocMgtBrTMsg(NWAL2410_ASMsg inMsg, DOC_MGT_BRTMsg docMgtBrTMsg) {

        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtOrgCd, inMsg.docMgtOrgCd_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtScanBrCd, inMsg.docMgtScanBrCd_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtScanBrNm, inMsg.docMgtScanBrNm_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtScanRgNm, inMsg.docMgtScanRgNm_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtScanZnNm, inMsg.docMgtScanZnNm_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.defOrdProcPsnCd, inMsg.defOrdProcPsnCd_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.defBrAdminPsnCd, inMsg.defBrAdminPsnCd_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.leaseCmpyProcPsnCd, inMsg.leaseCmpyProcPsnCd_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.poPendEmlAddr, inMsg.poPendEmlAddr_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.poIssEmlAddr, inMsg.poIssEmlAddr_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.invPkgPendEmlAddr, inMsg.invPkgPendEmlAddr_A);
        if (ZYPConstant.FLG_ON_Y.equals(inMsg.actvFlg_A.getValue())) {
            docMgtBrTMsg.actvFlg.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            docMgtBrTMsg.actvFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtIntfcProcTs, inMsg.docMgtIntfcProcTs_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtIntfcStsCd, inMsg.docMgtIntfcStsCd_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.procModeCd, inMsg.procModeCd_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.ezUpTime, inMsg.ezUpTime_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.ezUpTimeZone, inMsg.ezUpTimeZone_A);
    }

    /**
     * set NWAL2410BSMsg To DocMgtBrTMsg
     * @param inMsg NWAL2410_BSMsg
     * @param docMgtBrTMsg DOC_MGT_BRTMsg
     */
    private void setNWAL2410BSMsgToDocMgtBrTMsg(NWAL2410_BSMsg inMsg, DOC_MGT_BRTMsg docMgtBrTMsg) {

        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtOrgCd, inMsg.docMgtOrgCd_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtScanBrCd, inMsg.docMgtScanBrCd_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtScanBrNm, inMsg.docMgtScanBrNm_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtScanRgNm, inMsg.docMgtScanRgNm_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtScanZnNm, inMsg.docMgtScanZnNm_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.defOrdProcPsnCd, inMsg.defOrdProcPsnCd_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.defBrAdminPsnCd, inMsg.defBrAdminPsnCd_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.leaseCmpyProcPsnCd, inMsg.leaseCmpyProcPsnCd_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.poPendEmlAddr, inMsg.poPendEmlAddr_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.poIssEmlAddr, inMsg.poIssEmlAddr_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.invPkgPendEmlAddr, inMsg.invPkgPendEmlAddr_A);
        if (ZYPConstant.FLG_ON_Y.equals(inMsg.actvFlg_A.getValue())) {
            docMgtBrTMsg.actvFlg.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            docMgtBrTMsg.actvFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtIntfcProcTs, inMsg.docMgtIntfcProcTs_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.docMgtIntfcStsCd, inMsg.docMgtIntfcStsCd_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.procModeCd, inMsg.procModeCd_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.ezUpTime, inMsg.ezUpTime_A);
        ZYPEZDItemValueSetter.setValue(docMgtBrTMsg.ezUpTimeZone, inMsg.ezUpTimeZone_A);

    }

    /**
     * isUpdate (update target is true / no target is false)
     * @param inMsg NWAL2410_BSMsg
     * @param docMgtBrTMsg DOC_MGT_BRTMsg
     * @return boolean
     */
    private boolean isUpdate(NWAL2410_BSMsgArray origMsgArray, NWAL2410_ASMsg inMsg) {

        NWAL2410_BSMsg origMsg = getOrigMsg(origMsgArray, inMsg);
        if (origMsg == null) {
            return false;
        }

        if (NWAL2410CommonLogic.isNotCompareString(origMsg.docMgtScanBrNm_A, inMsg.docMgtScanBrNm_A)) {
            return true;
        }

        if (NWAL2410CommonLogic.isNotCompareString(origMsg.docMgtScanRgNm_A, inMsg.docMgtScanRgNm_A)) {
            return true;
        }

        if (NWAL2410CommonLogic.isNotCompareString(origMsg.docMgtScanZnNm_A, inMsg.docMgtScanZnNm_A)) {
            return true;
        }

        if (NWAL2410CommonLogic.isNotCompareString(origMsg.defOrdProcPsnCd_A, inMsg.defOrdProcPsnCd_A)) {
            return true;
        }

        if (NWAL2410CommonLogic.isNotCompareString(origMsg.defBrAdminPsnCd_A, inMsg.defBrAdminPsnCd_A)) {
            return true;
        }

        if (NWAL2410CommonLogic.isNotCompareString(origMsg.leaseCmpyProcPsnCd_A, inMsg.leaseCmpyProcPsnCd_A)) {
            return true;
        }

        if (NWAL2410CommonLogic.isNotCompareString(origMsg.poPendEmlAddr_A, inMsg.poPendEmlAddr_A)) {
            return true;
        }

        if (NWAL2410CommonLogic.isNotCompareString(origMsg.poIssEmlAddr_A, inMsg.poIssEmlAddr_A)) {
            return true;
        }

        if (NWAL2410CommonLogic.isNotCompareString(origMsg.invPkgPendEmlAddr_A, inMsg.invPkgPendEmlAddr_A)) {
            return true;
        }

        if (NWAL2410CommonLogic.isNotCompareString(origMsg.docMgtIntfcProcTs_A, inMsg.docMgtIntfcProcTs_A)) {
            return true;
        }

        if (NWAL2410CommonLogic.isNotCompareString(origMsg.docMgtIntfcStsCd_A, inMsg.docMgtIntfcStsCd_A)) {
            return true;
        }

        if (NWAL2410CommonLogic.isNotCompareString(origMsg.actvFlg_A, inMsg.actvFlg_A)) {
            return true;
        }

        if (NWAL2410CommonLogic.isNotCompareString(origMsg.ezUpTime_A, inMsg.ezUpTime_A)) {
            return true;
        }

        if (NWAL2410CommonLogic.isNotCompareString(origMsg.ezUpTimeZone_A, inMsg.ezUpTimeZone_A)) {
            return true;
        }

        return false;

    }

    /**
     * getOrigMsg (Search inMsg from origMsgArray)
     * @param origMsgArray NWAL2410_BSMsgArray
     * @param inMsg NWAL2410_ASMsg
     * @return NWAL2410_BSMsg
     */
    private NWAL2410_BSMsg getOrigMsg(NWAL2410_BSMsgArray origMsgArray, NWAL2410_ASMsg inMsg) {

        for (int i = 0; i < origMsgArray.getValidCount(); i++) {
            if (inMsg.docMgtOrgCd_A.getValue().equals(origMsgArray.no(i).docMgtOrgCd_A.getValue()) && inMsg.docMgtScanBrCd_A.getValue().equals(origMsgArray.no(i).docMgtScanBrCd_A.getValue())) {
                return origMsgArray.no(i);
            }
        }
        return null;
    }

    /**
     * getKeyMsg (Search inMsg from screenMsgArray)
     * @param screenMsgArray NWAL2410_ASMsgArray
     * @param inMsg NWAL2410_BSMsg
     * @return NWAL2410_ASMsg
     */
    private NWAL2410_ASMsg getKeyMsg(NWAL2410_ASMsgArray screenMsgArray, NWAL2410_BSMsg inMsg) {

        for (int i = 0; i < screenMsgArray.getValidCount(); i++) {
            if (inMsg.docMgtOrgCd_A.getValue().equals(screenMsgArray.no(i).docMgtOrgCd_A.getValue()) && inMsg.docMgtScanBrCd_A.getValue().equals(screenMsgArray.no(i).docMgtScanBrCd_A.getValue())) {
                return screenMsgArray.no(i);
            }
        }
        return null;
    }

    /**
     * setInsertErrorInfo
     * @param bizMsg NWAL2410CMsg
     * @param glblMsg NWAL2410SMsg
     * @param docMgtOrgCd String
     * @param docMgtScanBrCd String
     */
    private void setInsertErrorInfo(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg, String docMgtOrgCd, String docMgtScanBrCd) {

        NWAL2410_ASMsgArray asMsgArray = glblMsg.A;
        bizMsg.setMessageInfo(NWAM0923E, new String[] {DISP_CODE });
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            NWAL2410_ASMsg asMsg = asMsgArray.no(i);
            if (docMgtOrgCd.equals(asMsg.docMgtOrgCd_A.getValue()) && docMgtScanBrCd.equals(asMsg.docMgtScanBrCd_A.getValue())) {
                bizMsg.xxPageShowFromNum.setValue(NWAL2410CommonLogic.getPageStartRowIndex(bizMsg, i));
                NWAL2410CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
                break;
            }
        }

    }

    /**
     * updateDocMgtBrTable
     * @param bizMsg NWAL2410CMsg
     * @param updList List<DOC_MGT_BRTMsg>
     */
    private void updateDocMgtBrTable(NWAL2410CMsg bizMsg, List<DOC_MGT_BRTMsg> updList) {

        // for update
        for (DOC_MGT_BRTMsg updMsg : updList) {

            DOC_MGT_BRTMsg docMgtBrTMsg = findDocMgtBrTableByKeyForUpdateCheckEzTime(updMsg);
            if (docMgtBrTMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            }
            // clear
            // 2017/02/20 QC#17676 UPD START
            if (ZYPConstant.FLG_ON_Y.equals(updMsg.actvFlg.getValue())) {
                updMsg.docMgtIntfcProcTs.clear();
                if (!ZYPCommonFunc.hasValue(updMsg.docMgtIntfcStsCd.getValue()) || DOC_MGT_INTFC_STS.ERROR.equals(updMsg.docMgtIntfcStsCd.getValue())) {
                    if (!PROC_MODE_CD_CRT.equals(updMsg.procModeCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(updMsg.procModeCd, PROC_MODE_CD_UPD);
                    }
                } else {
                    updMsg.docMgtIntfcStsCd.clear();
                    ZYPEZDItemValueSetter.setValue(updMsg.procModeCd, PROC_MODE_CD_UPD);
                }
            }
            // 2017/02/20 QC#17676 UPD E N D
        }

        int updCnt = updList.size();
        int rsltCnt = S21FastTBLAccessor.update(updList.toArray(new DOC_MGT_BRTMsg[updCnt]));
        if (rsltCnt != updCnt) {
            bizMsg.setMessageInfo(NWAM0926E, new String[] {DISP_CODE });
            return;
        }

    }

    /**
     * isDbDuplicateCheck(List is Duplicate Check to DB)
     * @param bizMsg
     * @param glblMsg
     * @param insList List<DOC_MGT_BRTMsg>
     * @param updList List<DOC_MGT_BRTMsg>
     * @param delList List<DOC_MGT_BRTMsg>
     * @return
     */
    private boolean isDbDuplicateCheck(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg, List<DOC_MGT_BRTMsg> insList, List<DOC_MGT_BRTMsg> updList, List<DOC_MGT_BRTMsg> delList) {

        // true is Duplicate / false is not Duplicate
        boolean isDuplicate = false;
        int firstErrIdx = -1;
        NWAL2410_ASMsgArray asMsgArray = glblMsg.A;

        // insList check
        if (!insList.isEmpty()) {
            for (DOC_MGT_BRTMsg insTmsg : insList) {
                DOC_MGT_BRTMsg selectTMsg = new DOC_MGT_BRTMsg();
                selectTMsg.glblCmpyCd.setValue(insTmsg.glblCmpyCd.getValue());
                selectTMsg.docMgtOrgCd.setValue(insTmsg.docMgtOrgCd.getValue());
                selectTMsg.docMgtScanBrCd.setValue(insTmsg.docMgtScanBrCd.getValue());
                DOC_MGT_BRTMsg docMgtBrTMsg = findDocMgtBrTableByKey(selectTMsg);
                if (docMgtBrTMsg != null) {
                    int duplIdx = getKeyId(asMsgArray, selectTMsg);
                    asMsgArray.no(duplIdx).docMgtScanBrCd_A.setErrorInfo(1, NWAM0923E, new String[] {KEY_CODE_DISP_PK });
                    if (firstErrIdx == -1) {
                        firstErrIdx = duplIdx;
                    }
                    isDuplicate = true;
                }
            }
        }

        // updList check
        if (!updList.isEmpty()) {
            for (DOC_MGT_BRTMsg updTmsg : updList) {
                DOC_MGT_BRTMsg selectTMsg = new DOC_MGT_BRTMsg();
                selectTMsg.glblCmpyCd.setValue(updTmsg.glblCmpyCd.getValue());
                selectTMsg.docMgtOrgCd.setValue(updTmsg.docMgtOrgCd.getValue());
                selectTMsg.docMgtScanBrCd.setValue(updTmsg.docMgtScanBrCd.getValue());
                DOC_MGT_BRTMsg docMgtBrTMsg = findDocMgtBrTableByKey(selectTMsg);
                if (docMgtBrTMsg == null) {
                    int duplIdx = getKeyId(asMsgArray, selectTMsg);
                    asMsgArray.no(duplIdx).docMgtScanBrCd_A.setErrorInfo(1, NWAM0926E, new String[] {KEY_CODE_DISP_PK });
                    if (firstErrIdx == -1) {
                        firstErrIdx = duplIdx;
                    }
                    isDuplicate = true;
                }
            }
        }

        if (isDuplicate) {
            bizMsg.xxPageShowFromNum.setValue(NWAL2410CommonLogic.getPageStartRowIndex(bizMsg, firstErrIdx));
            NWAL2410CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
        return isDuplicate;
    }

    /**
     * getKeyId (Search inMsgId from screenMsgArray)
     * @param screenMsgArray NWAL2410_ASMsgArray
     * @param selectTMsg DOC_MGT_BRTMsg
     * @return NWAL2410_ASMsg
     */
    private int getKeyId(NWAL2410_ASMsgArray screenMsgArray, DOC_MGT_BRTMsg selectTMsg) {

        for (int i = 0; i < screenMsgArray.getValidCount(); i++) {
            if (selectTMsg.docMgtOrgCd.getValue().equals(screenMsgArray.no(i).docMgtOrgCd_A.getValue()) && selectTMsg.docMgtScanBrCd.getValue().equals(screenMsgArray.no(i).docMgtScanBrCd_A.getValue())) {
                return i;
            }
        }
        return 0;
    }

    /**
     * findDocMgtBrTableByKey
     * @param selectTMsg DOC_MGT_BRTMsg
     * @return DOC_MGT_BRTMsg
     */
    private DOC_MGT_BRTMsg findDocMgtBrTableByKey(DOC_MGT_BRTMsg selectTMsg) {
        DOC_MGT_BRTMsg wrkMsg = (DOC_MGT_BRTMsg) selectTMsg.clone();
        DOC_MGT_BRTMsg docMgtBrTMsg = (DOC_MGT_BRTMsg) S21FastTBLAccessor.findByKey(wrkMsg);
        if (docMgtBrTMsg == null) {
            return null;
        }
        return docMgtBrTMsg;
    }

    /**
     * findDocMgtBrTableByKeyForUpdateNoCheck
     * @param updDocMgtBrTMsg DOC_MGT_BRTMsg
     * @return DOC_MGT_BRTMsg
     */
    private DOC_MGT_BRTMsg findDocMgtBrTableByKeyForUpdateNoCheck(DOC_MGT_BRTMsg updDocMgtBrTMsg) {
        DOC_MGT_BRTMsg wrkMsg = (DOC_MGT_BRTMsg) updDocMgtBrTMsg.clone();
        DOC_MGT_BRTMsg docMgtBrTMsg = (DOC_MGT_BRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(wrkMsg);
        if (docMgtBrTMsg == null) {
            return null;
        }
        return docMgtBrTMsg;
    }

    /**
     * findDocMgtBrTableByKeyForUpdateCheckEzTime
     * @param updDocMgtBrTMsg DOC_MGT_BRTMsg
     * @return DOC_MGT_BRTMsg
     */
    private DOC_MGT_BRTMsg findDocMgtBrTableByKeyForUpdateCheckEzTime(DOC_MGT_BRTMsg updDocMgtBrTMsg) {

        DOC_MGT_BRTMsg docMgtBrTMsg = findDocMgtBrTableByKeyForUpdateNoCheck(updDocMgtBrTMsg);
        if (docMgtBrTMsg == null) {
            return null;
        }

        String findEzUpTime = updDocMgtBrTMsg.ezUpTime.getValue();
        String findEzUpTimeZone = updDocMgtBrTMsg.ezUpTimeZone.getValue();
        String currentEzUpTime = docMgtBrTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = docMgtBrTMsg.ezUpTimeZone.getValue();

        if ("*".equals(findEzUpTime)) {
            if (ZYPCommonFunc.hasValue(currentEzUpTime)) {
                return null;
            }
        } else if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            return null;
        }

        return docMgtBrTMsg;
    }

}
