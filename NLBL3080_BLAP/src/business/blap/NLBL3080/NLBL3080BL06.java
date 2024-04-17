/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3080;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLBL3080.common.NLBL3080CommonLogic;
import business.blap.NLBL3080.contant.NLBL3080Constant;
import business.db.CPO_DTLTMsg;
import business.db.HARD_ALLOCTMsg;
import business.db.HARD_ALLOCTMsgArray;
import business.db.HLDTMsg;
import business.db.HLDTMsgArray;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PRC_DTLTMsg;
import business.db.PRC_DTLTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.db.SOFT_ALLOCTMsg;
import business.db.SOFT_ALLOCTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC004001PMsg;
import business.parts.NWZC102001PMsg;
import business.parts.NWZC188001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC004001.NWZC004001;
import com.canon.cusa.s21.api.NWZ.NWZC102001.NWZC102001;
import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.common.NWX.NWXC002007.NWXC002007BackOrder;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   CITS            H.Sugawara      Create          N/A
 * 2017/06/23   CITS            R.Shimamoto     Update          QC#19517
 * 2017/06/28   CITS            R.Shimamoto     Update          QC#18187
 * 2018/06/26   CITS            K.Ogino         Update          QC#25456
 * 2019/03/26   CITS            K.Ogino         Update          QC#30903
 * 2019/04/12   CTIS            K.Ogino         Update          QC#31163
 *</pre>
 */
public class NLBL3080BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL3080Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_CMN_Submit((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_Alloc_Cancel".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_Alloc_Cancel((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_SaveSearch((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_DeleteSearch((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NLBL3080Scrn00_CMN_Submit
     * <dd>The method explanation: submit button event
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NLBL3080Scrn00_CMN_Submit(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        NLBL3080CommonLogic.saveCurrentPageToSMsgOrdLine(cMsg, sMsg);

        // Input Check
        if (!inputCheckSubmit(cMsg, sMsg)) {

            ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
            return;
        }

        int errIndex = -1;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            NLBL3080_BSMsg sMsgBLine = sMsg.B.no(i);

            // START 2017/06/28 R.Shimamoto QC#18187 Mod.
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgBLine.xxChkBox_B2.getValue())
            		&& !ZYPCommonFunc.hasValue(sMsgBLine.svcMachMstrPk_B1)) {

            	// Inventory Allocation
                NWZC102001PMsg invtyAllocPMsg = getInvtyAllocPMsg(cMsg.glblCmpyCd.getValue(), sMsgBLine);
                NWZC102001 invtyAllocApi = new NWZC102001();
                invtyAllocApi.execute(invtyAllocPMsg, ONBATCH_TYPE.ONLINE);

                if (!chkApiRslt(cMsg, sMsgBLine.xxChkBox_B2, S21ApiUtil.getXxMsgIdList(invtyAllocPMsg))) {

                    if (errIndex == -1) {

                        errIndex = i;
                    }
                }

            } else if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgBLine.xxChkBox_B2.getValue())
            		&& ZYPCommonFunc.hasValue(sMsgBLine.svcMachMstrPk_B1)) {

            	if (ZYPConstant.FLG_ON_Y.equals(sMsgBLine.allocReqFlg_B1.getValue())) {

                    // QC#20008 Add Start
                    SVC_MACH_MSTRTMsg smmTMsg = new SVC_MACH_MSTRTMsg();
                    ZYPEZDItemValueSetter.setValue(smmTMsg.glblCmpyCd, cMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(smmTMsg.svcMachMstrPk, sMsgBLine.svcMachMstrPk_B1);

                    smmTMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(smmTMsg);

                    if (smmTMsg == null) {
                        sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3080Constant.NMAM8421E);
                        if (errIndex == -1) {

                            errIndex = i;
                        }
                    }

                    if (ZYPCommonFunc.hasValue(sMsgBLine.stkStsCd_B1)) {
                        if (!sMsgBLine.stkStsCd_B1.getValue().equals(smmTMsg.stkStsCd.getValue())) {
                            sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3080Constant.NLZM2414E);
                            if (errIndex == -1) {

                                errIndex = i;
                            }
                        }
                    }
                    // QC#20008 Add End

                    // QC#30903
                    // Inventory Allocation
                    NWZC102001PMsg invtyAllocPMsg = getInvtyAllocPMsg(cMsg.glblCmpyCd.getValue(), sMsgBLine);
                    NWZC102001 invtyAllocApi = new NWZC102001();
                    invtyAllocApi.execute(invtyAllocPMsg, ONBATCH_TYPE.ONLINE);

                    if (!chkApiRslt(cMsg, sMsgBLine.xxChkBox_B2, S21ApiUtil.getXxMsgIdList(invtyAllocPMsg))) {

                        if (errIndex == -1) {

                            errIndex = i;
                        }
                    } else {

                        // Check Shpping Plan
                        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                        ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                        ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.shpgPlnNum, sMsgBLine.shpgPlnNum_B1);
                        shpgPlnTMsg = (SHPG_PLNTMsg) EZDFastTBLAccessor.findByKeyForUpdate(shpgPlnTMsg);

                        if (shpgPlnTMsg == null) {
                            sMsgBLine.xxChkBox_B2.setErrorInfo(1, NLBL3080Constant.NLBM0009E);
                        }

                        // Shipping Status Code
                        if (SHPG_STS.VALIDATED.equals(shpgPlnTMsg.shpgStsCd.getValue())) {
                            sMsgBLine.xxChkBox_B2.setErrorInfo(1, NLBL3080Constant.NLBM1371E);
                            if (errIndex == -1) {

                                errIndex = i;
                            }
                        } else {
                            // Machine Master Update
                            NSZC001001PMsg machMstrPMsg = getAllocMachMstrUpdPMsg(cMsg.glblCmpyCd.getValue(), sMsgBLine);
                            NSZC001001 machMstrUpdApi = new NSZC001001();
                            machMstrUpdApi.execute(machMstrPMsg, ONBATCH_TYPE.ONLINE);

                            if (!chkApiRslt(cMsg, sMsgBLine.serNum_B1, S21ApiUtil.getXxMsgIdList(machMstrPMsg))) {

                                if (errIndex == -1) {

                                    errIndex = i;
                                }

                            }
                        }
                    }

                } else {

                    sMsgBLine.xxChkBox_B2.setErrorInfo(1, NLBL3080Constant.NLCM0037E);
                    errIndex = i;

                }
            }
         	// END 2017/06/28 R.Shimamoto QC#18187 Mod.	

        }

        // Having Error
        if (0 <= errIndex) {

            // All Expansion
            NLBL3080CommonLogic.allExpansion(sMsg);
            NLBL3080CommonLogic.pagenation(cMsg, sMsg, NLBL3080CommonLogic.getPageStartRowIndex(errIndex, cMsg, sMsg));
            cMsg.setMessageInfo(NLBL3080Constant.ZZM9037E);
            return;
        }

        // Normal End
        cMsg.setMessageInfo(NLBL3080Constant.ZZZM9003I, new String[]{"Submit"});
    }

    /**
     * Check API Result
     * @param cMsg NLBL3080CMsg
     * @param errStringItem EZDSStringItem
     * @param msgIdList List<String>
     * @return boolean true : OK, false : NG
     */
    private boolean chkApiRslt(NLBL3080CMsg cMsg, EZDSStringItem errStringItem, List<String> msgIdList) {

        if (!msgIdList.isEmpty()) {

            for (String msgId : msgIdList) {

                if (ZYPCommonFunc.hasValue(msgId)) {

                    cMsg.setMessageInfo(msgId, null);

                    if (msgId.endsWith("E")) {

                        errStringItem.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Method name: doProcess_NLBL3080Scrn00_Alloc_Cancel
     * <dd>The method explanation: submit button event
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NLBL3080Scrn00_Alloc_Cancel(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        NLBL3080CommonLogic.saveCurrentPageToSMsgOrdLine(cMsg, sMsg);

        // Input Check
        if (!inputCheckAllocCancel(cMsg, sMsg)) {

            ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
            return;
        }

        int errIndex = -1;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            NLBL3080_BSMsg sMsgBLine = sMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgBLine.xxChkBox_B2.getValue())) {

                final List<String> delShplgPlnNumList = new ArrayList<String>();

                // update shipping plan validated
                // 2017/06/23 QC#19517 Mod.
//                final Map<String, Object> newShpgPlnNum = updateShpgPlnValidated(cMsg, i, delShplgPlnNumList);
                final Map<String, Object> newShpgPlnNum = updateShpgPlnValidated(cMsg, sMsg, i, delShplgPlnNumList);

                if ("E".equals(cMsg.getMessageKind())) {

                    if (errIndex == -1) {

                        errIndex = i;
                    }

                    continue;
                }

                if (newShpgPlnNum == null) {

                    if (errIndex == -1) {

                        errIndex = i;
                    }

                    continue;
                }

                // delete price deteil validated
                updPrcDtl(cMsg, delShplgPlnNumList, newShpgPlnNum);

                if ("E".equals(cMsg.getMessageKind())) {

                    if (errIndex == -1) {

                        errIndex = i;
                    }

                    continue;
                }

                // delete hold
                deleteHld(cMsg, delShplgPlnNumList);

                // Allocation API
                NWZC102001PMsg invtyAllocPMsg = getInvtyAllocCancelPMsg(cMsg.glblCmpyCd.getValue(), sMsgBLine);
                NWZC102001 invtyAllocApi = new NWZC102001();
                invtyAllocApi.execute(invtyAllocPMsg, ONBATCH_TYPE.ONLINE);

                if (!chkApiRslt(cMsg, sMsgBLine.xxChkBox_B2, S21ApiUtil.getXxMsgIdList(invtyAllocPMsg))) {

                    if (errIndex == -1) {

                        errIndex = i;
                    }

                    continue;
                }

                // Validation Process Manager
                NWZC004001PMsg validProcMgrPMsg = getValidProcMgrPMsg(cMsg.glblCmpyCd.getValue(), (String) newShpgPlnNum.get("NEW_SHPG_PLN_NUM"));
                NWZC004001 validProcMgrApi = new NWZC004001();
                validProcMgrApi.execute(validProcMgrPMsg, ONBATCH_TYPE.ONLINE);

                if (!chkApiRslt(cMsg, sMsgBLine.xxChkBox_B2, S21ApiUtil.getXxMsgIdList(validProcMgrPMsg))) {

                    if (errIndex == -1) {

                        errIndex = i;
                    }

                    continue;
                }

                // START 2017/05/19 M.Naito
                // Display Order Status Update API
                NWZC188001PMsg displayOrderStatusPMsg = getdisplayOrderStatusPMsg(cMsg.glblCmpyCd.getValue(), sMsgBLine.cpoOrdNum_B1.getValue(), (String) newShpgPlnNum.get("NEW_SHPG_PLN_NUM"));
                NWZC188001 displayOrderStatusApi = new NWZC188001();
                displayOrderStatusApi.execute(displayOrderStatusPMsg, ONBATCH_TYPE.ONLINE);

                if (!chkApiRslt(cMsg, sMsgBLine.xxChkBox_B2, S21ApiUtil.getXxMsgIdList(displayOrderStatusPMsg))) {

                    if (errIndex == -1) {

                        errIndex = i;
                    }

                    continue;
                }
                // END 2017/05/19 M.Naito

                // QC#30903. MM Allocation off
                ArrayList<String> machMstrErrMsgList = callMachMstrAPIForAllocOff(cMsg, sMsgBLine);

                if (!chkApiRslt(cMsg, sMsgBLine.xxChkBox_B2, machMstrErrMsgList)) {

                    if (errIndex == -1) {

                        errIndex = i;
                    }

                    continue;
                }
            }
        }

        // Having Error
        if (0 <= errIndex) {

            // All Expansion
            NLBL3080CommonLogic.allExpansion(sMsg);
            NLBL3080CommonLogic.pagenation(cMsg, sMsg, NLBL3080CommonLogic.getPageStartRowIndex(errIndex, cMsg, sMsg));
            cMsg.setMessageInfo(NLBL3080Constant.NLBM0049E);
            return;
        }

        // Normal End
        cMsg.setMessageInfo(NLBL3080Constant.ZZZM9003I, new String[]{"Allocation Cancel"});
    }

    /**
     * doProcess_NLBL3080Scrn00_SaveSearch
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_SaveSearch(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S) && !ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {

            cMsg.srchOptNm_S.setErrorInfo(1, "ZZM9000E", new String[] {converter.convLabel2i18nLabel(NLBL3080Constant.SCREEN_ID, "Search Option Name") });
            return;
        }

        if (NLBL3080CommonLogic.isExistSaveSearchName(cMsg)) {

            cMsg.srchOptNm_S.setErrorInfo(1, "NLZM2273E", new String[] {converter.convLabel2i18nLabel(NLBL3080Constant.SCREEN_ID, "Save")
                    , converter.convLabel2i18nLabel(NLBL3080Constant.SCREEN_ID, "Search Option Name") });
            return;
        }

        NLBL3080CommonLogic.callNszc0330forSaveSearch(cMsg, sMsg);
    }

    /**
     * doProcess_NLBL3080Scrn00_DeleteSearch
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_DeleteSearch(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {

            cMsg.srchOptPk_S.setErrorInfo(1, "NLZM2274E", new String[] {converter.convLabel2i18nLabel(NLBL3080Constant.SCREEN_ID, "Saved Search Options") });
            return;
        }

        NLBL3080CommonLogic.callNszc0330forDeleteSearch(cMsg, sMsg);
    }

    /**
     * inputCheckSubmit
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     * @return boolean
     */
    private boolean inputCheckSubmit(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        boolean chkBoxOn = false;
        boolean hasErr = false;
        int firstErrIdx = -1;

        // Already Checked
        List<BigDecimal> pickSvcConfigMstrPkList = new ArrayList<BigDecimal>();

        // Check count checking-On
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            NLBL3080_BSMsg sMsgBLine = sMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgBLine.xxChkBox_B2.getValue())) {

                chkBoxOn = true;

                // Add QC#25456 Check Hold
                if (!checkHld(cMsg, sMsgBLine)) {
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }
                // Check Shipping Plan
                if (!checkShpgPln(cMsg, sMsgBLine, SHPG_STS.VALIDATED)) {

                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // Check Qty
                if (!checkAvalQty(cMsg, sMsg, sMsgBLine)) {

                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // Check Machine Master
                if (ZYPCommonFunc.hasValue(sMsgBLine.svcMachMstrPk_B1) || ZYPCommonFunc.hasValue(sMsgBLine.serNum_B1)) {

                    if (!checkSerial(cMsg, sMsgBLine)) {

                        hasErr = true;

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }

                        continue;
                    }
                }

                // Check Config / Set
                if (ZYPCommonFunc.hasValue(sMsgBLine.pickSvcConfigMstrPk_B1) || ZYPCommonFunc.hasValue(sMsgBLine.setShpgPlnNum_B1)) {

                    boolean chkPickConfig = false;
                    boolean chkSetItem = false;

                    if (ZYPCommonFunc.hasValue(sMsgBLine.pickSvcConfigMstrPk_B1)) {

                    	// START QC#18187 Add.
                    	//  check PickSvcConfig for Non-Serial.
                    	if (!checkPickSvcConfigForNonSerial(cMsg, sMsgBLine)) {

                    		hasErr = true;
                    	}

                        if (pickSvcConfigMstrPkList.isEmpty() || !pickSvcConfigMstrPkList.contains(sMsgBLine.pickSvcConfigMstrPk_B1.getValue())) {

                            chkPickConfig = true;
                            pickSvcConfigMstrPkList.add(sMsgBLine.pickSvcConfigMstrPk_B1.getValue());
                        }
                        // END QC#18187 Add.
                    }

                    if (!checkPickConfigAndSet(cMsg, sMsg, sMsgBLine, chkPickConfig, chkSetItem, SHPG_STS.VALIDATED, true)) {

                        hasErr = true;

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }

                        continue;
                    }
                }
            }
        }

        if (!chkBoxOn) {

            cMsg.setMessageInfo(NLBL3080Constant.NLBM0002E);
            return false;
        }

        if (hasErr) {

            NLBL3080CommonLogic.allExpansion(sMsg);
            NLBL3080CommonLogic.pagenation(cMsg, sMsg, NLBL3080CommonLogic.getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
            cMsg.setMessageInfo(NLBL3080Constant.ZZM9037E);
            return false;
        }

        return true;
    }

    /**
     * inputCheckSubmit
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     * @return boolean
     */
    private boolean inputCheckSubmitWarning(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        boolean hasWrng = false;
        int firstWrngIdx = -1;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            NLBL3080_BSMsg sMsgBLine = sMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgBLine.xxChkBox_B2.getValue()) && ZYPCommonFunc.hasValue(sMsgBLine.svcMachMstrPk_B1)) {

                if (!sMsgBLine.invtyLocCd_B1.getValue().equals(sMsgBLine.curLocNum_B1.getValue())) {

                    sMsgBLine.serNum_B1.setErrorInfo(2, NLBL3080Constant.NLBM1299W);

                    hasWrng = true;

                    if (firstWrngIdx == -1) {

                        firstWrngIdx = i;
                    }
                }
            }
        }

        if (hasWrng) {

            NLBL3080CommonLogic.allExpansion(sMsg);
            NLBL3080CommonLogic.pagenation(cMsg, sMsg, NLBL3080CommonLogic.getPageStartRowIndex(firstWrngIdx, cMsg, sMsg));
            cMsg.setMessageInfo(NLBL3080Constant.NATM0001W);
            return false;
        }

        return true;
    }

    /**
     * Check Shipping Plan
     * @param cMsg NLBL3080CMsg
     * @param sMsgBLine NLBL3080_BSMsg
     * @param chkShpgStsCd String
     * @return boolean true : OK, false : NG
     */
    private boolean checkShpgPln(NLBL3080CMsg cMsg, NLBL3080_BSMsg sMsgBLine, String chkShpgStsCd) {

        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.shpgPlnNum, sMsgBLine.shpgPlnNum_B1);
        shpgPlnTMsg = (SHPG_PLNTMsg) EZDFastTBLAccessor.findByKeyForUpdate(shpgPlnTMsg);

        if (shpgPlnTMsg == null) {

            sMsgBLine.xxChkBox_B2.setErrorInfo(1, NLBL3080Constant.NLBM0009E);
            return false;
        }

        // Check time stamp
        if (!ZYPDateUtil.isSameTimeStamp(sMsgBLine.ezUpTime_SP.getValue(), sMsgBLine.ezUpTimeZone_SP.getValue(), shpgPlnTMsg.ezUpTime.getValue(), shpgPlnTMsg.ezUpTimeZone.getValue())) {

            sMsgBLine.xxChkBox_B2.setErrorInfo(1, NLBL3080Constant.NLBM0009E);
            return false;
        }

        // Shipping Status Code
        if (!chkShpgStsCd.equals(shpgPlnTMsg.shpgStsCd.getValue())) {

            sMsgBLine.xxChkBox_B2.setErrorInfo(1, NLBL3080Constant.NLZM2283E);
            return false;
        }

        return true;
    }

    /**
     * Check Available Plan
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     * @param sMsgBLine NLBL3080_BSMsg
     * @return boolean true : OK, false : NG
     */
    private boolean checkAvalQty(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg, NLBL3080_BSMsg sMsgBLine) {

        // BO Qty > Available Qty
        if (sMsgBLine.ordQty_B1.getValue().compareTo(sMsgBLine.invtyAvalQty_B1.getValue()) > 0) {

            sMsgBLine.ordQty_B1.setErrorInfo(1, NLBL3080Constant.NLZM2316E, new String[] {"Back Order Qty", "Current Available Qty" });
            return false;
        }

        // BO Qty (Summary) > Available Qty
        int cnt = 0;
        BigDecimal smryBoQty = BigDecimal.ZERO;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            NLBL3080_BSMsg bLine = sMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(bLine.xxChkBox_B2.getValue())) {

                if (sMsgBLine.mdseCd_B1.getValue().equals(bLine.mdseCd_B1.getValue())
                        && sMsgBLine.stkStsCd_B1.getValue().equals(bLine.stkStsCd_B1.getValue())
                        && sMsgBLine.invtyLocCd_B1.getValue().equals(bLine.invtyLocCd_B1.getValue())) {

                    cnt++;
                    smryBoQty.add(bLine.ordQty_B1.getValue());
                }
            }
        }

        if (cnt > 1 && smryBoQty.compareTo(sMsgBLine.invtyAvalQty_B1.getValue()) > 0) {

            sMsgBLine.ordQty_B1.setErrorInfo(1, NLBL3080Constant.NLBM1321E);
            return false;
        }

        return true;
    }

    /**
     * Check Serial
     * @param cMsg NLBL3080CMsg
     * @param sMsgBLine NLBL3080_BSMsg
     * @return boolean
     */
    private boolean checkSerial(NLBL3080CMsg cMsg, NLBL3080_BSMsg sMsgBLine) {

        if (ZYPCommonFunc.hasValue(sMsgBLine.svcMachMstrPk_B1)) {

            SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, sMsgBLine.svcMachMstrPk_B1.getValue());
            svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) EZDFastTBLAccessor.findByKeyForUpdate(svcMachMstrTMsg);

            if (svcMachMstrTMsg != null) {

                ZYPEZDItemValueSetter.setValue(sMsgBLine.svcMachMstrLocStsCd_B1, svcMachMstrTMsg.svcMachMstrLocStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(sMsgBLine.curLocNum_B1, svcMachMstrTMsg.curLocNum.getValue());

                // Set Map
                Map<String, Object> svcMachMstrMap = new HashMap<String, Object>();
                svcMachMstrMap.put("TRX_HDR_NUM", svcMachMstrTMsg.trxHdrNum.getValue());
                // QC#18187 Mod.
                svcMachMstrMap.put("SVC_MACH_MSTR_LOC_STS_CD", svcMachMstrTMsg.svcMachMstrLocStsCd.getValue());
                svcMachMstrMap.put("SVC_MACH_MSTR_STS_CD", svcMachMstrTMsg.svcMachMstrStsCd.getValue());
                svcMachMstrMap.put("SVC_CONFIG_MSTR_PK", svcMachMstrTMsg.svcConfigMstrPk.getValue());

                // Check Machine Master
                if (!chkSvcMachMstr(sMsgBLine, svcMachMstrMap)) {

                    return false;
                }

                // Check Asset Master
                if (!chkAssetMstr(cMsg.glblCmpyCd.getValue(), sMsgBLine)) {

                    return false;
                }

                return true;
            }
        }

        if (ZYPCommonFunc.hasValue(sMsgBLine.serNum_B1)) {

            // Get Machine Master
            List<Map<String, Object>> svcMachMstrList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> mdseInfo = new ArrayList<Map<String, Object>>();
            S21SsmEZDResult ssmResult = NLBL3080Query.getInstance().getSvcMachMstrList(cMsg.glblCmpyCd.getValue(), sMsgBLine);

            if (ssmResult.isCodeNormal()) {

                svcMachMstrList = (List<Map<String, Object>>) ssmResult.getResultObject();
            }

            // Machine Master dosen't exist
            if (svcMachMstrList == null || svcMachMstrList.isEmpty()) {

                // Config
                if (ZYPCommonFunc.hasValue(sMsgBLine.pickSvcConfigMstrPk_B1)) {

                    sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3080Constant.NLZM2319E);
                    return false;
                }
                
                // IB check
                ssmResult = NLBL3080Query.getInstance().getInstlBaseCtrlFlg(cMsg.glblCmpyCd.getValue(), sMsgBLine);
                if (ssmResult.isCodeNormal()) {

                	mdseInfo = (List<Map<String, Object>>) ssmResult.getResultObject();
                	String instlBaseCtrlFlg = (String) mdseInfo.get(0).get("INSTL_BASE_CTRL_FLG");

                	if (ZYPConstant.FLG_ON_Y.equals(instlBaseCtrlFlg)) {

                		sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3080Constant.NMAM8421E);
                        return false;

                	}
                }

                // Serial Only
                if (NLBL3080Constant.DUP_CHK_SERIAL.equals(cMsg.dupErrCd.getValue())) {

                    ssmResult = NLBL3080Query.getInstance().getDefMdseSvcMachMstrList(cMsg.glblCmpyCd.getValue(), sMsgBLine);

                // Serial and Model
                } else if (NLBL3080Constant.DUP_CHK_MDL.equals(cMsg.dupErrCd.getValue())) {

                    ssmResult = NLBL3080Query.getInstance().getDefMdseSvcMachMstrListForMdlChk(cMsg.glblCmpyCd.getValue(), sMsgBLine);
                }

                if (ssmResult.isCodeNormal()) {

                    svcMachMstrList = (List<Map<String, Object>>) ssmResult.getResultObject();
                }

                // Exist Duplicated Machine master
                if (svcMachMstrList != null && !svcMachMstrList.isEmpty()) {

                    // Get Active Machine Master
                    List<Map<String, Object>> actvSvcMachMstrList = getActvSvcMachMstrList(svcMachMstrList);

                    if (actvSvcMachMstrList != null && !actvSvcMachMstrList.isEmpty()) {

                        sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3080Constant.NLZM2450E);
                        return false;
                    }

                    Map<String, Object> svcMachMstrMap = getAvalSvcMachMstrMap(svcMachMstrList, sMsgBLine.cpoOrdNum_B1.getValue());

                    if (svcMachMstrMap != null) {

                        ZYPEZDItemValueSetter.setValue(sMsgBLine.svcMachMstrPk_B1, (BigDecimal) svcMachMstrMap.get("SVC_MACH_MSTR_PK"));
                        ZYPEZDItemValueSetter.setValue(sMsgBLine.svcMachMstrLocStsCd_B1, (String) svcMachMstrMap.get("SVC_MACH_MSTR_LOC_STS_CD"));
                        ZYPEZDItemValueSetter.setValue(sMsgBLine.curLocNum_B1, (String) svcMachMstrMap.get("CUR_LOC_NUM"));

                        if (!ZYPCommonFunc.hasValue((String) svcMachMstrMap.get("TRX_HDR_NUM"))) {

                            ZYPEZDItemValueSetter.setValue(sMsgBLine.allocReqFlg_B1, ZYPConstant.FLG_ON_Y);
                        }
                    }
                }

            // Specify Machine Master
            } else {

                Map<String, Object> svcMachMstrMap = new HashMap<String, Object>();

                // Get Active Machine Master
                List<Map<String, Object>> actvSvcMachMstrList = getActvSvcMachMstrList(svcMachMstrList);

                if (actvSvcMachMstrList != null && !actvSvcMachMstrList.isEmpty()) {

                	//  2017/06/28 R.Shimamoto QC#18187 Delete.
                    // Machine Master Unique Check
//                    if (actvSvcMachMstrList.size() > 1) {
//
//                        sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3080Constant.NLZM2436E);
//                        return false;
//                    }

                    svcMachMstrMap = actvSvcMachMstrList.get(0);

                // All Terminate Machine Master
                } else {

                    svcMachMstrMap = getAvalSvcMachMstrMap(svcMachMstrList, sMsgBLine.cpoOrdNum_B1.getValue());
                }

                if (svcMachMstrMap == null) {

                    sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3080Constant.NLZM2317E);
                    return false;
                }

                ZYPEZDItemValueSetter.setValue(sMsgBLine.svcMachMstrPk_B1, (BigDecimal) svcMachMstrMap.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(sMsgBLine.svcMachMstrLocStsCd_B1, (String) svcMachMstrMap.get("SVC_MACH_MSTR_LOC_STS_CD"));
                ZYPEZDItemValueSetter.setValue(sMsgBLine.curLocNum_B1, (String) svcMachMstrMap.get("CUR_LOC_NUM"));
                //  2017/06/28 R.Shimamoto QC#18187 Add.
                ZYPEZDItemValueSetter.setValue(sMsgBLine.mdseCd_B1, (String) svcMachMstrMap.get("MDSE_CD"));

                // Check Machine Master
                if (!chkSvcMachMstr(sMsgBLine, svcMachMstrMap)) {

                    return false;
                }
            }

            // Check Asset Master
            if (!chkAssetMstr(cMsg.glblCmpyCd.getValue(), sMsgBLine)) {

                return false;
            }
        }

        return true;
    }

    /**
     * getActvSvcMachMstrList
     * @param svcMachMstrList List<Map<String, Object>>
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getActvSvcMachMstrList(List<Map<String, Object>> svcMachMstrList) {

        List<Map<String, Object>> actvSvcMachMstrList = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> svcMachMstrMap : svcMachMstrList) {

            String svcMachMstrStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_STS_CD");

            if (!SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {

                actvSvcMachMstrList.add(svcMachMstrMap);
            }
        }

        return actvSvcMachMstrList;
    }

    /**
     * getAvalSvcMachMstrMap
     * @param svcMachMstrList List<Map<String, Object>>
     * @param cpoOrdNum String
     * @return Map<String, Object>
     */
    private Map<String, Object> getAvalSvcMachMstrMap(List<Map<String, Object>> svcMachMstrList, String cpoOrdNum) {

        Map<String, Object> avalSvcMachMstrMap = new HashMap<String, Object>();

        int avalSvcMachMstrCnt = 0;

        for (Map<String, Object> svcMachMstrMap : svcMachMstrList) {

            String trxHdrNum = (String) svcMachMstrMap.get("TRX_HDR_NUM");

            if (ZYPCommonFunc.hasValue(trxHdrNum)) {

                if (trxHdrNum.equals(cpoOrdNum)) {

                    return svcMachMstrMap;

                } else {

                    continue;
                }
            }

            avalSvcMachMstrMap = svcMachMstrMap;
            avalSvcMachMstrCnt++;
        }

        if (avalSvcMachMstrCnt > 0) {

            return avalSvcMachMstrMap;
        }

        return null;
    }

    /**
     * chkSvcMachMstr
     * @param sMsgBLine NLBL3080_BSMsg
     * @param svcMachMstrMap Map<String, Object>
     * @return boolean
     */
    private boolean chkSvcMachMstr(NLBL3080_BSMsg sMsgBLine, Map<String, Object> svcMachMstrMap) {

        String trxHdrNum = (String) svcMachMstrMap.get("TRX_HDR_NUM");
        String svcMachMstrStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_STS_CD");
        BigDecimal svcConfigMstrPk = (BigDecimal) svcMachMstrMap.get("SVC_CONFIG_MSTR_PK");

        // Allocation Check
        if (ZYPCommonFunc.hasValue(trxHdrNum) && !trxHdrNum.equals(sMsgBLine.cpoOrdNum_B1.getValue())) {

            sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3080Constant.NLZM2317E);
            return false;

        } else if (!ZYPCommonFunc.hasValue(trxHdrNum)) {

            ZYPEZDItemValueSetter.setValue(sMsgBLine.allocReqFlg_B1, ZYPConstant.FLG_ON_Y);
        }

        // Config Check
        if (ZYPCommonFunc.hasValue(svcConfigMstrPk) && !svcConfigMstrPk.equals(sMsgBLine.pickSvcConfigMstrPk_B1.getValue())) {

            sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3080Constant.NLZM2324E);
            return false;

        } else if (ZYPCommonFunc.hasValue(sMsgBLine.pickSvcConfigMstrPk_B1) && !sMsgBLine.pickSvcConfigMstrPk_B1.getValue().equals(svcConfigMstrPk)) {

            sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3080Constant.NLZM2319E);
            return false;
        }

        // Status Check
        if (!SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {

            if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {

                sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3080Constant.NLZM2318E);
                return false;
            }
        }

        // Location Check. QC#31163
        if (!sMsgBLine.invtyLocCd_B1.getValue().equals(sMsgBLine.curLocNum_B1.getValue())) {

            sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3080Constant.NLBM1372E);
            sMsgBLine.xxChkBox_B2.setErrorInfo(1, NLBL3080Constant.NLBM1372E);
            
            return false;

        }

        return true;
    }

    /**
     * chkAssetMstr
     * @param glblCmpyCd String
     * @param sMsgBLine NLBL3080_BSMsg
     * @return boolean
     */
    private boolean chkAssetMstr(String glblCmpyCd, NLBL3080_BSMsg sMsgBLine) {

        if (INVTY_ACCT.ASSET.equals(sMsgBLine.invtyAcctCd_B1.getValue())) {

            if (ZYPCommonFunc.hasValue(sMsgBLine.svcMachMstrPk_B1)) {

                S21SsmEZDResult ssmResult = NLBL3080Query.getInstance().getActvAssetCnt(glblCmpyCd, sMsgBLine.svcMachMstrPk_B1.getValue());

                if (ssmResult.isCodeNormal()) {

                    BigDecimal actvAssetCnt  = (BigDecimal) ssmResult.getResultObject();

                    if (actvAssetCnt != null && actvAssetCnt.intValue() > 0) {

                        return true;
                    }
                }
            }

            sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3080Constant.NLBM1290E);
            return false;
        }

        return true;
    }

    /**
     * Check Configuration And Set
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     * @param sMsgBLine NLBL3080_BSMsg
     * @param chkPickConfig boolean
     * @param chkSetItem boolean
     * @param shpgStsCd String
     * @return boolean true : OK, false : NG
     */
    private boolean checkPickConfigAndSet(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg, NLBL3080_BSMsg sMsgBLine, boolean chkPickConfig, boolean chkSetItem, String shpgStsCd, boolean submitFlg) {

        if (!chkPickConfig && !chkSetItem) {

            return true;
        }

        boolean hasErr = false;
        List<Integer> chkConfigLineList = new ArrayList<Integer>();
        List<Integer> chkSetLineList = new ArrayList<Integer>();

        // Check all Component in Screen
        boolean hasConfigErr = false;
        boolean hasSetErr = false;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            NLBL3080_BSMsg bLine = sMsg.B.no(i);
            // QC#30903
            if (submitFlg) {
                if (bLine.ordQty_B1.getValue().compareTo(BigDecimal.ZERO) < 1) {
                    continue;
                }   
            } else {
                if (bLine.ordQty_B2.getValue().compareTo(BigDecimal.ZERO) < 1) {
                    continue;
                }
            }

            // Config
            if (chkPickConfig && ZYPCommonFunc.hasValue(sMsgBLine.pickSvcConfigMstrPk_B1)) {

                if (sMsgBLine.pickSvcConfigMstrPk_B1.getValue().equals(bLine.pickSvcConfigMstrPk_B1.getValue())) {

                    chkConfigLineList.add(i);

                    if (!ZYPConstant.CHKBOX_ON_Y.equals(bLine.xxChkBox_B2.getValue())) {

                        bLine.xxChkBox_B2.setErrorInfo(1, NLBL3080Constant.NLZM2284E, new String[] {sMsgBLine.pickSvcConfigMstrPk_B1.getValue().toString() });
                        hasConfigErr = true;
                        hasErr = true;
                    }
                }
            }

            // Set
            if (chkSetItem && ZYPCommonFunc.hasValue(sMsgBLine.setShpgPlnNum_B1)) {

                if (sMsgBLine.setShpgPlnNum_B1.getValue().equals(bLine.setShpgPlnNum_B1.getValue())) {

                    chkSetLineList.add(i);

                    if (!ZYPConstant.CHKBOX_ON_Y.equals(bLine.xxChkBox_B2.getValue())) {

                        bLine.xxChkBox_B2.setErrorInfo(1, NLBL3080Constant.NLBM1320E);
                        hasSetErr = true;
                        hasErr = true;
                    }
                }
            }
        }

        if (hasConfigErr) {

            for (int chkConfigLine : chkConfigLineList) {

                sMsg.B.no(chkConfigLine).xxChkBox_B2.setErrorInfo(1, NLBL3080Constant.NLZM2284E, new String[] {sMsgBLine.pickSvcConfigMstrPk_B1.getValue().toString() });
            }
        }

        if (hasSetErr) {

            for (int chkSetLine : chkSetLineList) {

                sMsg.B.no(chkSetLine).xxChkBox_B2.setErrorInfo(1, NLBL3080Constant.NLBM1320E);
            }
        }

        // Check Config Component in DB
        if (!hasErr && chkPickConfig && ZYPCommonFunc.hasValue(sMsgBLine.pickSvcConfigMstrPk_B1)) {

            S21SsmEZDResult ssmResult = NLBL3080Query.getInstance().getConfigCompLineCnt(cMsg.glblCmpyCd.getValue(), sMsgBLine, shpgStsCd);

            if (ssmResult.isCodeNormal()) {

                BigDecimal configCompLineCnt  = (BigDecimal) ssmResult.getResultObject();

                if (configCompLineCnt != null && configCompLineCnt.intValue() != chkConfigLineList.size()) {

                    for (int chkConfigLine : chkConfigLineList) {

                        sMsg.B.no(chkConfigLine).xxChkBox_B2.setErrorInfo(1, NLBL3080Constant.NLZM2284E, new String[] {sMsgBLine.pickSvcConfigMstrPk_B1.getValue().toString() });
                    }

                    hasErr = true;
                }
            }
        }

        // Check Set Component in DB
        if (!hasErr && chkSetItem && ZYPCommonFunc.hasValue(sMsgBLine.setShpgPlnNum_B1)) {

            S21SsmEZDResult ssmResult = NLBL3080Query.getInstance().getSetCompLineCnt(cMsg.glblCmpyCd.getValue(), sMsgBLine, shpgStsCd);

            if (ssmResult.isCodeNormal()) {

                BigDecimal setCompLineCnt  = (BigDecimal) ssmResult.getResultObject();

                if (setCompLineCnt != null && setCompLineCnt.intValue() != chkSetLineList.size()) {

                    for (int chkSetLine : chkSetLineList) {

                        sMsg.B.no(chkSetLine).xxChkBox_B2.setErrorInfo(1, NLBL3080Constant.NLBM1320E);
                    }

                    hasErr = true;
                }
            }
        }

        if (hasErr) {

            return false;
        }

        return true;
    }

    /**
     * getInvtyAllocPMsg
     * @param glblCmpyCd String
     * @param sMsgBLine NLBL3080_BSMsg
     * @return NWZC102001PMsg
     */
    private NWZC102001PMsg getInvtyAllocPMsg(String glblCmpyCd, NLBL3080_BSMsg sMsgBLine) {

        // Get Shipping Plan
        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.shpgPlnNum, sMsgBLine.shpgPlnNum_B1.getValue());
        shpgPlnTMsg = (SHPG_PLNTMsg) EZDTBLAccessor.findByKey(shpgPlnTMsg);

        if (!RTNCD_NORMAL.equals(shpgPlnTMsg.getReturnCode())) {

            return null;
        }

        NWZC102001PMsg invtyAllocPMsg = new NWZC102001PMsg();
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.xxOrigFuncTpCd, NWZC102001.ORG_FUNC_CD_HARD_ALLOC);
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.xxRqstTpCd, NWZC102001.REQ_TP_NEW);
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.xxAllocTpCd, NWZC102001.ALLOC_TP_CD_HARD_ALLOC);
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.xxPrtlAcptFlg, NLBL3080Constant.PRTL_ACPT_FLG_NO);

        // SUPD_LOCK_FLG
        // QC#18187 Mod.
        if (ZYPCommonFunc.hasValue(sMsgBLine.svcMachMstrPk_B1)) {

        	ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.xxItemFlipAcptFlg, NLBL3080Constant.ITEM_FLIPT_FLG_NO);

        } else {

        	String ordTakemdseCd = sMsgBLine.mdseCd_B1.getValue();
        	if (NLBL3080Constant.MDSE_8_DIGIT < ordTakemdseCd.length()) {
        		ordTakemdseCd = ordTakemdseCd.substring(0, NLBL3080Constant.MDSE_8_DIGIT);
        	}

        	ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, ordTakemdseCd);
            ORD_TAKE_MDSETMsg outMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(tMsg);

            if (outMsg != null && ZYPCommonFunc.hasValue(outMsg.ordTakeMdseCd)) {

            	ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.xxItemFlipAcptFlg, NLBL3080Constant.ITEM_FLIPT_FLG_ON);

            } else {

            	if (ZYPConstant.FLG_ON_Y.equals(sMsgBLine.supdLockFlg_B1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.xxItemFlipAcptFlg, NLBL3080Constant.ITEM_FLIPT_FLG_NO);

                } else {

                    ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.xxItemFlipAcptFlg, NLBL3080Constant.ITEM_FLIPT_FLG_ON);
                }

            }
        }

        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.xxWhFlipAcptFlg, NLBL3080Constant.WH_FLIP_FLG_NO);
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.trxSrcTpCd, sMsgBLine.trxSrcTpCd_B1.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.trxHdrNum, sMsgBLine.cpoOrdNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.trxLineNum, sMsgBLine.cpoDtlLineNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.trxLineSubNum, sMsgBLine.cpoDtlLineSubNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.mdseCd, sMsgBLine.mdseCd_B1.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.cpoOrdTs, sMsgBLine.cpoOrdTs_B1.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.rddDt, shpgPlnTMsg.rddDt.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.rsdDt, shpgPlnTMsg.rsdDt.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.expdShipDt, getExpdShipDt(glblCmpyCd, sMsgBLine));
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.slsRepTocCd, shpgPlnTMsg.slsRepTocCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.billToCustCd, shpgPlnTMsg.billToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.sellToCustCd, shpgPlnTMsg.sellToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.shipToCustCd, shpgPlnTMsg.shipToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.shipToStCd, shpgPlnTMsg.shipToStCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.shipToPostCd, shpgPlnTMsg.shipToPostCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.invtyLocCd, sMsgBLine.invtyLocCd_B1.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.stkStsCd, shpgPlnTMsg.stkStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.frtChrgMethCd, shpgPlnTMsg.reqFrtChrgMethCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.frtChrgToCd, shpgPlnTMsg.reqFrtChrgToCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.shpgSvcLvlCd, sMsgBLine.reqShpgSvcLvlCd_B1.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.shipCpltCd, shpgPlnTMsg.shipCpltCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.setItemShipCpltFlg, shpgPlnTMsg.setItemShipCpltFlg.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.uomCpltFlg, shpgPlnTMsg.uomCpltFlg.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.uomCd, shpgPlnTMsg.custUomCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.xxRqstQty, sMsgBLine.ordQty_B1.getValue());
        ZYPEZDItemValueSetter.setValue(invtyAllocPMsg.slsDt, ZYPDateUtil.getSalesDate());
        return invtyAllocPMsg;
    }

    /**
     * getExpdShipDt
     * @param glblCmpyCd String
     * @param sMsgBLine NLBL3080_BSMsg
     * @return String
     */
    private String getExpdShipDt(String glblCmpyCd, NLBL3080_BSMsg sMsgBLine) {

        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, sMsgBLine.cpoOrdNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, sMsgBLine.cpoDtlLineNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, sMsgBLine.cpoDtlLineSubNum_B1.getValue());
        cpoDtlTMsg = (CPO_DTLTMsg) EZDTBLAccessor.findByKey(cpoDtlTMsg);

        if (RTNCD_NORMAL.equals(cpoDtlTMsg.getReturnCode())) {

            return cpoDtlTMsg.expdShipDt.getValue();
        }

        return null;
    }

    /**
     * getInvtyAcctCd
     * @param glblCmpyCd String
     * @param sMsgBLine NLBL3080_BSMsg
     * @return NSZC001001PMsg
     */
    private NSZC001001PMsg getAllocMachMstrUpdPMsg(String glblCmpyCd, NLBL3080_BSMsg sMsgBLine) {

        NSZC001001PMsg machMstrPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.xxModeCd, ProcessMode.ALLOCATION_ON.code);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrPk, sMsgBLine.svcMachMstrPk_B1.getValue());
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.trxHdrNum, sMsgBLine.cpoOrdNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.trxLineNum, sMsgBLine.cpoDtlLineNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.trxLineSubNum, sMsgBLine.cpoDtlLineSubNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrLocStsCd, sMsgBLine.svcMachMstrLocStsCd_B1.getValue());
        return machMstrPMsg;
    }

    /**
     * inputCheckAllocCancel
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     * @return boolean
     */
    private boolean inputCheckAllocCancel(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        boolean chkBoxOn = false;
        boolean hasErr = false;
        int firstErrIdx = -1;

        // Already Checked
        List<BigDecimal> pickSvcConfigMstrPkList = new ArrayList<BigDecimal>();

        // Check count checking-On
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            NLBL3080_BSMsg sMsgBLine = sMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgBLine.xxChkBox_B2.getValue())) {

                chkBoxOn = true;

                // Check Shipping Plan
                if (!checkShpgPln(cMsg, sMsgBLine, SHPG_STS.HARD_ALLOCATED)) {

                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // Check Config / Set
                if (ZYPCommonFunc.hasValue(sMsgBLine.pickSvcConfigMstrPk_B1) || ZYPCommonFunc.hasValue(sMsgBLine.setShpgPlnNum_B1)) {

                    boolean chkPickConfig = false;
                    boolean chkSetItem = false;

                    if (ZYPCommonFunc.hasValue(sMsgBLine.pickSvcConfigMstrPk_B1)) {

                        if (pickSvcConfigMstrPkList.isEmpty() || !pickSvcConfigMstrPkList.contains(sMsgBLine.pickSvcConfigMstrPk_B1.getValue())) {

                            chkPickConfig = true;
                            pickSvcConfigMstrPkList.add(sMsgBLine.pickSvcConfigMstrPk_B1.getValue());
                        }
                    }

                    if (!checkPickConfigAndSet(cMsg, sMsg, sMsgBLine, chkPickConfig, chkSetItem, SHPG_STS.HARD_ALLOCATED, false)) {

                        hasErr = true;

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }

                        continue;
                    }
                }
            }
        }

        if (hasErr) {

            NLBL3080CommonLogic.allExpansion(sMsg);
            NLBL3080CommonLogic.pagenation(cMsg, sMsg, NLBL3080CommonLogic.getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
            cMsg.setMessageInfo(NLBL3080Constant.ZZM9037E);
            return false;
        }

        if (!chkBoxOn) {

            cMsg.setMessageInfo(NLBL3080Constant.NLBM0002E);
            return false;
        }

        return true;
    }

    /**
     * update shipping plan validated
     * @param bizMsg NLBL3080CMsg
	 * @param sMsg NLBL3080SMsg
     * @param idx Integer
     * @param delShplgPlnNumLst List<String>
     * @return Map<String, Object>
     */
    private Map<String, Object> updateShpgPlnValidated(NLBL3080CMsg bizMsg, NLBL3080SMsg sMsg, Integer idx, List<String> delShplgPlnNumLst) {

        // search shipping plan (H-table)
        S21SsmEZDResult ssmResult = NLBL3080Query.getInstance().updateShpgPlnValidated(sMsg, bizMsg.glblCmpyCd.getValue(), idx);
        SHPG_PLNTMsg edtShpgPlnRec = new SHPG_PLNTMsg();
        boolean validatedFlg = false;
        String delShpgPlnNum = null;
        int wkSoftAllocQty = 0;
        int wkOrdQty = 0;
        BigDecimal wkSpTotDealFrtAmt = BigDecimal.ZERO;
        BigDecimal wkSpTotFuncFrtAmt = BigDecimal.ZERO;

        // insert info set
        Map<String, Object> wkNewShpgRec = new HashMap<String, Object>();
        Map<String, Object> setShpgPlnNumMap = new HashMap<String, Object>();
        String newSetShpgPlnNum = null;

        if (ssmResult.isCodeNormal()) {

            List<SHPG_PLNTMsg> getShpgPlnLst = (List<SHPG_PLNTMsg>) ssmResult.getResultObject();

            for (SHPG_PLNTMsg getShpgPln : getShpgPlnLst) {

                String wkShpgStsCd = getShpgPln.shpgStsCd.getValue();

                if (S21StringUtil.isEquals(SHPG_STS.VALIDATED, wkShpgStsCd)) {

                    // copy shpg_pln
                    EZDMsg.copy(getShpgPln, null, edtShpgPlnRec, null);

                    // insert info set
                    wkNewShpgRec.put("DEL_ORIG_SHPG_PLN_NUM", getShpgPln.shpgPlnNum.getValue());
                    delShpgPlnNum = getShpgPln.shpgPlnNum.getValue();

                    // validated flag true
                    validatedFlg = true;

                    // ship_pln lock
                    S21FastTBLAccessor.findByKeyForUpdateNoWait(getShpgPln);

                    // logicalRemove shpg_pln
                    EZDTBLAccessor.logicalRemove(getShpgPln);

                    // SoftAllocQty and ORD QTY save
                    wkSoftAllocQty += getShpgPln.softAllocQty.getValueInt();
                    wkOrdQty += getShpgPln.ordQty.getValueInt();

                    wkSpTotDealFrtAmt = wkSpTotDealFrtAmt.add(getShpgPln.spTotDealFrtAmt.getValue());
                    wkSpTotFuncFrtAmt = wkSpTotFuncFrtAmt.add(getShpgPln.spTotFuncFrtAmt.getValue());

                    // delete ShpgPlnNum save
                    delShplgPlnNumLst.add(getShpgPln.shpgPlnNum.getValue());

                } else {

                    if (validatedFlg == false) {

                        // copy shpg_pln
                        EZDMsg.copy(getShpgPln, null, edtShpgPlnRec, null);

                        // insert info set
                        wkNewShpgRec.put("DEL_ORIG_SHPG_PLN_NUM", getShpgPln.shpgPlnNum.getValue());
                        delShpgPlnNum = getShpgPln.shpgPlnNum.getValue();
                    }

                    // logicalRemove shpg_pln
                    EZDTBLAccessor.logicalRemove(getShpgPln);

                    // SoftAllocQty and ORD QTY save
                    wkSoftAllocQty += getShpgPln.softAllocQty.getValueInt();
                    wkOrdQty += getShpgPln.ordQty.getValueInt();

                    wkSpTotDealFrtAmt = wkSpTotDealFrtAmt.add(getShpgPln.spTotDealFrtAmt.getValue());
                    wkSpTotFuncFrtAmt = wkSpTotFuncFrtAmt.add(getShpgPln.spTotFuncFrtAmt.getValue());

                    // delete ShpgPlnNum save
                    delShplgPlnNumLst.add(getShpgPln.shpgPlnNum.getValue());

                    // set component Process
                    if (ZYPCommonFunc.hasValue(getShpgPln.setMdseCd) && ZYPCommonFunc.hasValue(getShpgPln.setShpgPlnNum)) {

                        String setShpgPlnNum = getShpgPln.setShpgPlnNum.getValue();

                        if (setShpgPlnNumMap.containsKey(setShpgPlnNum)) {

                            // none
                            continue;

                        } else {

                            newSetShpgPlnNum = setShpgPlnProcess(bizMsg, setShpgPlnNum);

                            if (newSetShpgPlnNum == null) {

                                return null;
                            }

                            edtShpgPlnRec.setShpgPlnNum.setValue(newSetShpgPlnNum);
                            setShpgPlnNumMap.put(setShpgPlnNum, setShpgPlnNum);
                        }
                    }
                }
            }

            String wkShpgPlnNum = insertShpgPln(bizMsg, edtShpgPlnRec, wkOrdQty, wkSoftAllocQty, wkSpTotDealFrtAmt, wkSpTotFuncFrtAmt);

            if (wkShpgPlnNum == null) {

                return wkNewShpgRec;
            }

            // set component
            if (ZYPCommonFunc.hasValue(edtShpgPlnRec.setMdseCd) && ZYPCommonFunc.hasValue(edtShpgPlnRec.setShpgPlnNum)) {

                S21SsmEZDResult ssmResult1 = NLBL3080Query.getInstance().checkSetShpgPln(edtShpgPlnRec.glblCmpyCd.getValue(), newSetShpgPlnNum);

                if (ssmResult1.isCodeNormal()) {

                    List resultList = (List) ssmResult1.getResultObject();

                    if (resultList != null && !resultList.isEmpty()) {

                        Map map = (Map) resultList.get(0);
                        String invtyDistTp = (String) map.get("INVTY_DIST_TP_CD");
                        BigDecimal setSoftAllocQty = (BigDecimal) map.get("SOFT_ALLOC_QTY");

                        if (S21StringUtil.isEquals(DIST_TP.DISTRIBUTION, invtyDistTp) && BigDecimal.ZERO.compareTo(setSoftAllocQty) != 0) {

                            S21SsmEZDResult ssmResult2 = NLBL3080Query.getInstance().getSoftAllocTotQty(edtShpgPlnRec);

                            if (ssmResult2.isCodeNormal()) {

                                List resultSoftAllocList = (List) ssmResult2.getResultObject();

                                if (resultSoftAllocList != null && !resultSoftAllocList.isEmpty()) {

                                    Map mapSoftAlloc = (Map) resultSoftAllocList.get(0);
                                    BigDecimal totSoftAllocQty = (BigDecimal) mapSoftAlloc.get("SUM_SOFT_ALLOC_QTY");

                                    if (setSoftAllocQty.compareTo(totSoftAllocQty) > 0) {

                                        bizMsg.setMessageInfo("NWDM0226E");
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // insert info set
            wkNewShpgRec.put("NEW_SHPG_PLN_NUM", wkShpgPlnNum);
            wkNewShpgRec.put("ORD_QTY", new BigDecimal(wkOrdQty));

            // **** Update SOFT_ALLOC
            if (delShpgPlnNum != null) {

                updShpgPlnNumByPartialValue(getGlobalCompanyCode(), delShpgPlnNum, wkShpgPlnNum);
            }

            // re-insert hld
            S21SsmEZDResult ssmResult3 = NLBL3080Query.getInstance().getHldRsnCd(bizMsg);

            if (ssmResult3.isCodeNormal()) {

                List<String> hldRsnCdLst = (List<String>) ssmResult3.getResultObject();

                S21SsmEZDResult ssmResult2 = NLBL3080Query.getInstance().getHldInfo(bizMsg, hldRsnCdLst, delShplgPlnNumLst);

                if (ssmResult2.isCodeNormal()) {

                    List<HLDTMsg> hldInfoLst = (List<HLDTMsg>) ssmResult2.getResultObject();

                    Set<String> hldRec = new HashSet<String>();

                    for (HLDTMsg hldInfoTMsg : hldInfoLst) {

                        if (hldRec.contains(hldInfoTMsg.hldRsnCd.getValue())) {

                            continue;

                        } else {

                            hldRec.add(hldInfoTMsg.hldRsnCd.getValue());
                        }

                        HLDTMsg insertMsg = new HLDTMsg();
                        EZDMsg.copy((HLDTMsg) hldInfoTMsg, null, insertMsg, null);

                        ZYPEZDItemValueSetter.setValue(insertMsg.hldPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.HLD_SQ));
                        ZYPEZDItemValueSetter.setValue(insertMsg.shpgPlnNum, wkShpgPlnNum);
                        ZYPEZDItemValueSetter.setValue(insertMsg.hldQty, edtShpgPlnRec.ordQty);
                        EZDTBLAccessor.insert(insertMsg);
                    }
                }
            }

        } else {

            // illigal case.
            bizMsg.setMessageInfo("NLBM0049E");
        }

        return wkNewShpgRec;
    }

    /**
     * update updByPartialValue
     */
    private int updShpgPlnNumByPartialValue(final String glblCmpyCd, final String shpgPlnNum, final String newShpgPlnNum) {

        SOFT_ALLOCTMsg condSoftAllocMsg = new SOFT_ALLOCTMsg();
        SOFT_ALLOCTMsg inSoftAllocMsg = new SOFT_ALLOCTMsg();

        condSoftAllocMsg.glblCmpyCd.setValue(glblCmpyCd);
        condSoftAllocMsg.shpgPlnNum.setValue(shpgPlnNum);
        inSoftAllocMsg.shpgPlnNum.setValue(newShpgPlnNum);

        return EZDTBLAccessor.updateByPartialValue(condSoftAllocMsg, new String[] {"glblCmpyCd", "shpgPlnNum" }, inSoftAllocMsg, new String[] {"shpgPlnNum" });
    }

    /**
     * get setShpgPlnNum
     * @param bizMsg NLBL3080CMsg
     * @param setShpgPlnNum String
     */
    private String setShpgPlnProcess(NLBL3080CMsg bizMsg, final String setShpgPlnNum) {

        // ----------------------------------------------
        // originalShpgPlnMsg -> SetItem to ComponentItem
        //
        // originalShpgPlnMsg.Sts = 'Validated'
        // none
        //
        // originalShpgPlnMsg.Sts <> 'Validated'
        // Search The record of 'Validated'
        // ----------------------------------------------

        String glblCmpyCd = getGlobalCompanyCode();
        SHPG_PLNTMsg originalSetShpgPlnMsg = getShpgPlnFindByKey(glblCmpyCd, setShpgPlnNum);

        if (originalSetShpgPlnMsg == null) {

            bizMsg.setMessageInfo("NLBM0049E");
            return null;
        }

        String createShpgPlnNum = null;

        if (S21StringUtil.isEquals(SHPG_STS.VALIDATED, originalSetShpgPlnMsg.shpgStsCd.getValue())) {

            createShpgPlnNum = setShpgPlnNum;

        } else {

            SHPG_PLNTMsgArray vaildSetShpgPlnMsgData = searchShpgPlnValidatedForUpdate(originalSetShpgPlnMsg);

            int setSoftAllocQty = originalSetShpgPlnMsg.softAllocQty.getValueInt();
            int setOrdQty = originalSetShpgPlnMsg.ordQty.getValueInt();

            if (vaildSetShpgPlnMsgData.length() == 0) {

                // Creates it if there is no 'Validaed' status.

                // ----------------------
                // Create Process (Set Item)
                // ----------------------

                createShpgPlnNum = setItemCreateProcess(bizMsg, originalSetShpgPlnMsg, setOrdQty, setSoftAllocQty, null, setShpgPlnNum);

                if ("E".equals(bizMsg.getMessageKind())) {

                    return null;
                }

            } else if (vaildSetShpgPlnMsgData.length() == 1) {

                // Both are deleted if there are 'Validated' and 'HardAllocated' in the status of the setItem. And Create.

                // ----------------------
                // validSetShplgPln
                // -> It is setItem and status is 'Validated'
                // ----------------------

                SHPG_PLNTMsg validSetShpgPln = vaildSetShpgPlnMsgData.no(0);

                setSoftAllocQty += validSetShpgPln.softAllocQty.getValueInt();
                setOrdQty += validSetShpgPln.ordQty.getValueInt();

                // ----------------------
                // CreateProcess (Set Item)
                // ----------------------
                createShpgPlnNum = setItemCreateProcess(bizMsg, validSetShpgPln, setOrdQty, setSoftAllocQty, validSetShpgPln.shpgPlnNum.getValue(), setShpgPlnNum);

                if ("E".equals(bizMsg.getMessageKind())) {

                    return null;
                }

                // ----------------------
                // originalSetShpgPlnMsg -> It is setItem and status is 'Hard Allocated'
                // ----------------------

                // ----------------------
                // delete SHPG_PLN
                // ----------------------
                EZDTBLAccessor.logicalRemove(originalSetShpgPlnMsg);

                // ----------------------
                // delete PRC_DTL
                // ----------------------
                deletePrcDtl(bizMsg, setShpgPlnNum);

                // ----------------------
                // delete HLD
                // ----------------------
                deleteHld(bizMsg, setShpgPlnNum);

                // ----------------------
                // update SOFT_ALLOC (Set)
                // ----------------------
                updSetSoftAlloc(bizMsg, glblCmpyCd, setShpgPlnNum, createShpgPlnNum, ZYPConstant.FLG_ON_Y);

                if ("E".equals(bizMsg.getMessageKind())) {

                    return null;
                }

                // --------------------------
                // update SHPG_PLN (The value of setShpgPlnNum is updated.)
                // --------------------------
                SHPG_PLNTMsgArray shpgPlnMsgRcdList = searchShpgPlnNumForUpdate(originalSetShpgPlnMsg, setShpgPlnNum);

                if (shpgPlnMsgRcdList.getValidCount() > 0) {

                    int updCount = updSetShpgPlnNumByPartialValue(glblCmpyCd, setShpgPlnNum, createShpgPlnNum);

                    if (updCount != shpgPlnMsgRcdList.getValidCount()) {

                        bizMsg.setMessageInfo("NWDM0007E");
                        return null;
                    }
                }

            } else {

                bizMsg.setMessageInfo("NWDM0007E");
                return null;
            }
        }

        // ReMake
        if (!remakeProcess(bizMsg, originalSetShpgPlnMsg, createShpgPlnNum)) {

            return null;
        }

        // update AVAL SO/PO Qty
        // Status is 'Validated' --> 0 is set to SO/PO Qty.
        SHPG_PLNTMsgArray shpgPlnMsgRcdList = searchShpgPlnNumForUpdate(originalSetShpgPlnMsg, createShpgPlnNum);

        if (shpgPlnMsgRcdList.getValidCount() > 0) {

            int updCount = updAvalQtyByPartialValue(glblCmpyCd, createShpgPlnNum);

            if (updCount != shpgPlnMsgRcdList.getValidCount()) {

                bizMsg.setMessageInfo("NWDM0007E");
                return null;
            }
        }

        return createShpgPlnNum;
    }

    /**
     * update updAvalQtyByPartialValue
     * @param glblCmpyCd String
     * @param setShpgPlnNum String
     */
    private int updAvalQtyByPartialValue(final String glblCmpyCd, final String setShpgPlnNum) {

        SHPG_PLNTMsg condShpgPlnMsg = new SHPG_PLNTMsg();
        SHPG_PLNTMsg inShpgPlnMsg = new SHPG_PLNTMsg();

        condShpgPlnMsg.glblCmpyCd.setValue(glblCmpyCd);
        condShpgPlnMsg.setShpgPlnNum.setValue(setShpgPlnNum);
        inShpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);
        inShpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);

        return EZDTBLAccessor.updateByPartialValue(condShpgPlnMsg, new String[] {"glblCmpyCd", "setShpgPlnNum" }, inShpgPlnMsg, new String[] {"avalSoQty", "avalPoQty" });
    }

    /**
     * update updByPartialValue
     * @param glblCmpyCd String
     * @param setShpgPlnNum String
     * @param newSetShpgPlnNum String
     */
    private int updSetShpgPlnNumByPartialValue(final String glblCmpyCd, final String setShpgPlnNum, final String newSetShpgPlnNum) {

        SHPG_PLNTMsg condShpgPlnMsg = new SHPG_PLNTMsg();
        SHPG_PLNTMsg inShpgPlnMsg = new SHPG_PLNTMsg();

        condShpgPlnMsg.glblCmpyCd.setValue(glblCmpyCd);
        condShpgPlnMsg.setShpgPlnNum.setValue(setShpgPlnNum);
        inShpgPlnMsg.setShpgPlnNum.setValue(newSetShpgPlnNum);

        return EZDTBLAccessor.updateByPartialValue(condShpgPlnMsg, new String[] {"glblCmpyCd", "setShpgPlnNum" }, inShpgPlnMsg, new String[] {"setShpgPlnNum" });
    }

    /**
     * remakeProcess
     * @param bizMsg NLBL3080CMsg
     * @param shpgPlnMsgRcd SHPG_PLNTMsg
     * @param createShpgPlnNum String
     */
    private boolean remakeProcess(NLBL3080CMsg bizMsg, SHPG_PLNTMsg shpgPlnMsgRcd, final String createShpgPlnNum) {

        // Remake
        String newSetShpgPlnNum = null;
        BigDecimal newSetSoftAllocPk = null;
        String glblCmpyCd = shpgPlnMsgRcd.glblCmpyCd.getValue();

        // -----------------------------------------------
        // The quantity to divide as a set is calculated.
        // -----------------------------------------------
        Map<String, Object> compQtyMap = NWXC002007BackOrder.setPossibleQty(glblCmpyCd, shpgPlnMsgRcd.trxSrcTpCd.getValue(), shpgPlnMsgRcd.trxHdrNum.getValue(), shpgPlnMsgRcd.trxLineNum.getValue(), createShpgPlnNum);

        if (compQtyMap == null || compQtyMap.isEmpty()) {

            return true;

        } else {

            BigDecimal setPossibleQty = (BigDecimal) compQtyMap.get(NWXC002007BackOrder.SET_AVAL_QTY);

            if (setPossibleQty == null) {

                return true;

            } else if (BigDecimal.ZERO.compareTo(setPossibleQty) == 0) {

                return true;
            }

            // SHPG_PLN information on each ComponentItem.
            S21SsmEZDResult ssmResult = NLBL3080Query.getInstance().getShpgPlnEachComp(glblCmpyCd, shpgPlnMsgRcd.trxSrcTpCd.getValue(), shpgPlnMsgRcd.trxHdrNum.getValue(), shpgPlnMsgRcd.trxLineNum.getValue(), createShpgPlnNum);

            if (ssmResult.isCodeNormal()) {

                // ---------------------------------------------
                // SetItem Divide/Update Process
                // ---------------------------------------------
                SHPG_PLNTMsg origSetShpgPlnMsg = getShpgPlnFindByKey(glblCmpyCd, createShpgPlnNum);

                if (origSetShpgPlnMsg == null || !S21StringUtil.isEquals(SHPG_STS.VALIDATED, origSetShpgPlnMsg.shpgStsCd.getValue())) {

                    bizMsg.setMessageInfo("NWDM0007E");
                    return false;
                }

                if (setPossibleQty.compareTo(origSetShpgPlnMsg.ordQty.getValue()) < 0) {

                    // setPossibleQty < OrdQty --> Divide

                    // SHPG_PLN
                    // PRC_DTL
                    // HLD
                    String divideSetShpgPlnNum = divideProcess(origSetShpgPlnMsg, setPossibleQty, null, SHPG_STS.HARD_ALLOCATED);

                    if (divideSetShpgPlnNum == null) {

                        return false;
                    }

                    // SOFT_ALLOC
                    newSetSoftAllocPk = setSoftAllocDivideProcess(glblCmpyCd, setPossibleQty, divideSetShpgPlnNum, origSetShpgPlnMsg.shpgPlnNum.getValue());
                    newSetShpgPlnNum = divideSetShpgPlnNum;
                }

                // ---------------------------------------------
                // ComponentItem Divide/Update Process
                // ---------------------------------------------
                String beforelineSubNum = "";
                BigDecimal eachCompTotalQty = BigDecimal.ZERO;
                List<Map<String, Object>> shpgPlnEachCompList = (List<Map<String, Object>>) ssmResult.getResultObject();

                for (Map<String, Object> shpgPlnEachComp : shpgPlnEachCompList) {

                    String lineSubNum = (String) shpgPlnEachComp.get("TRX_LINE_SUB_NUM");
                    String compShpgPlnNum = (String) shpgPlnEachComp.get("SHPG_PLN_NUM");
                    BigDecimal ordQty = (BigDecimal) shpgPlnEachComp.get("ORD_QTY");
                    String mdseCd = (String) shpgPlnEachComp.get("MDSE_CD");
                    String poReqFlg = (String) shpgPlnEachComp.get("PO_REQ_FLG");

                    if (!S21StringUtil.isEquals(beforelineSubNum, lineSubNum)) {

                        // ---------------------------------------------
                        // eachCompTotalQty = Amount of each component that can be divided.
                        // ---------------------------------------------
                        beforelineSubNum = lineSubNum;
                        BigDecimal eachCompQty = (BigDecimal) compQtyMap.get(lineSubNum);

                        if (eachCompQty == null) {

                            return false;
                        }
                        eachCompTotalQty = multiply(eachCompQty, setPossibleQty);
                    }

                    if (BigDecimal.ZERO.compareTo(eachCompTotalQty) == 0) {
                        continue;
                    }

                    // Original SHPG_PLN
                    SHPG_PLNTMsg origCompShpgPlnMsg = getShpgPlnFindByKey(glblCmpyCd, compShpgPlnNum);

                    if (eachCompTotalQty.compareTo(ordQty) < 0) {
                        // -------------------------------
                        // eachCompTotalQty < OrdQty -->Divide
                        // -------------------------------

                        BigDecimal divideQty = subtract(ordQty, eachCompTotalQty);

                        String sts = getSts(glblCmpyCd, mdseCd, poReqFlg);

                        if (sts == null) {
                            return false;
                        }

                        BigDecimal updOrdQty = subtract(origCompShpgPlnMsg.ordQty.getValue(), divideQty);

                        // SHPG_PLN
                        // PRC_DTL
                        // HLD
                        String divideCompShpgPlnNum = divideProcess(origCompShpgPlnMsg, divideQty, newSetShpgPlnNum, sts);

                        // SOFT_ALLOC
                        SOFT_ALLOCTMsg softAllocMsg = softAllocDivideProcess(glblCmpyCd, divideQty, divideCompShpgPlnNum, origCompShpgPlnMsg.shpgPlnNum.getValue(), newSetSoftAllocPk);

                        // HARD_ALLOC
                        hardAllocDivideProcess(bizMsg, glblCmpyCd, origCompShpgPlnMsg.shpgPlnNum.getValue(), divideCompShpgPlnNum, updOrdQty, divideQty, softAllocMsg);

                        eachCompTotalQty = BigDecimal.ZERO;

                    } else {
                        // -------------------------------
                        // eachCompTotalQty >= OrdQty -->Update
                        // -------------------------------

                        origCompShpgPlnMsg.setShpgPlnNum.setValue(newSetShpgPlnNum);
                        EZDTBLAccessor.update(origCompShpgPlnMsg);

                        // SOFT_ALLOC
                        updSoftAllocPk(bizMsg, glblCmpyCd, origCompShpgPlnMsg.shpgPlnNum.getValue(), newSetSoftAllocPk);

                        if (eachCompTotalQty.compareTo(ordQty) == 0) {
                            eachCompTotalQty = BigDecimal.ZERO;
                        } else {
                            eachCompTotalQty = subtract(eachCompTotalQty, ordQty);
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * SetItem TransactionData merge
     * @param bizMsg NLBL3080CMsg
     * @param originalShplgPln SHPG_PLNTMsg
     * @param setOrdQty Integer
     * @param setSoftAllocQty Integer
     * @param validShpgPlnNum String
     * @param setShpgPlnNum String
     */
    private String setItemCreateProcess(NLBL3080CMsg bizMsg, SHPG_PLNTMsg originalShplgPln, int setOrdQty, int setSoftAllocQty, final String validShpgPlnNum, final String setShpgPlnNum) {

        Map<String, Object> newShplgPlnNum = new HashMap<String, Object>();
        SHPG_PLNTMsg edtShpgPlnRec = new SHPG_PLNTMsg();
        List<String> delShplgPlnNumLst = new ArrayList<String>();
        String newSetShpgPlnNum = null;

        String trxHdrNum = originalShplgPln.trxHdrNum.getValue();
        String trxLineNum = originalShplgPln.trxLineNum.getValue();
        String glblCmpyCd = originalShplgPln.glblCmpyCd.getValue();
        String originalShpgPlnNum = originalShplgPln.shpgPlnNum.getValue();
        BigDecimal wkSpTotDealFrtAmt = BigDecimal.ZERO;
        BigDecimal wkSpTotFuncFrtAmt = BigDecimal.ZERO;

        EZDMsg.copy(originalShplgPln, null, edtShpgPlnRec, null);

        // --------------------------
        // logicalRemove SHPG_PLN
        // --------------------------
        EZDTBLAccessor.logicalRemove(originalShplgPln);
        delShplgPlnNumLst.add(originalShpgPlnNum);

        // --------------------------
        // create SHPG_PLN
        // --------------------------
        newSetShpgPlnNum = insertShpgPln(bizMsg, edtShpgPlnRec, setOrdQty, setSoftAllocQty, wkSpTotDealFrtAmt, wkSpTotFuncFrtAmt);

        if (newSetShpgPlnNum == null) {

            return null;
        }

        newShplgPlnNum.put("DEL_ORIG_SHPG_PLN_NUM", originalShplgPln.shpgPlnNum.getValue());
        newShplgPlnNum.put("NEW_SHPG_PLN_NUM", newSetShpgPlnNum);
        newShplgPlnNum.put("ORD_QTY", new BigDecimal(setOrdQty));

        // --------------------------
        // update PRC_DTL
        // --------------------------
        updPrcDtl(bizMsg, delShplgPlnNumLst, newShplgPlnNum);

        // --------------------------
        // update HOLD
        // --------------------------
        updateHold(glblCmpyCd, originalShpgPlnNum, newSetShpgPlnNum, setOrdQty);

        // --------------------------
        // update SOFT_ALLOC
        // --------------------------
        updSetSoftAlloc(bizMsg, glblCmpyCd, originalShpgPlnNum, newSetShpgPlnNum, null);

        if ("E".equals(bizMsg.getMessageKind())) {

            return null;
        }

        // --------------------------
        // CreateProcess VendorDrop (ComponentItem)
        // --------------------------
        compCreateProcess(bizMsg, glblCmpyCd, NLBL3080Query.getInstance().getVendorDrop(glblCmpyCd, trxHdrNum, trxLineNum, setShpgPlnNum), validShpgPlnNum);

        // --------------------------
        // CreateProcess Intangible (ComponentItem)
        // --------------------------
        compCreateProcess(bizMsg, glblCmpyCd, NLBL3080Query.getInstance().getIntangible(glblCmpyCd, trxHdrNum, trxLineNum, setShpgPlnNum), validShpgPlnNum);

        if ("E".equals(bizMsg.getMessageKind())) {
            return null;
        }

        // --------------------------
        // update SHPG_PLN
        // (The value of setShpgPlnNum is updated.)
        // --------------------------
        SHPG_PLNTMsgArray shpgPlnMsgRcdList = searchShpgPlnNumForUpdate(edtShpgPlnRec, originalShpgPlnNum);

        if (shpgPlnMsgRcdList.getValidCount() > 0) {

            int updCount = updSetShpgPlnNumByPartialValue(glblCmpyCd, originalShpgPlnNum, newSetShpgPlnNum);

            if (updCount != shpgPlnMsgRcdList.getValidCount()) {
                bizMsg.setMessageInfo("NWDM0007E");
                return null;
            }
        }

        return newSetShpgPlnNum;

    }

    /**
     * VendorDrop TransactionData Create Intangible TransactionData Create
     * @param bizMsg NLBL3080CMsg
     * @param glblCmpyCd String
     * @param ssmResult S21SsmEZDResult
     * @param setShpgPlnNum String
     */
    private void compCreateProcess(NLBL3080CMsg bizMsg, final String glblCmpyCd, final S21SsmEZDResult ssmResult, final String setShpgPlnNum) {

        int ordQty = 0;
        int softAllocQty = 0;
        String newSetShpgPlnNum = null;
        String lineSubNum = "";
        SHPG_PLNTMsg edtShpgPlnRec = new SHPG_PLNTMsg();
        List<String> delShplgPlnNumLst = new ArrayList<String>();
        Map<String, Object> newShplgPlnNum = new HashMap<String, Object>();
        BigDecimal wkSpTotDealFrtAmt = BigDecimal.ZERO;
        BigDecimal wkSpTotFuncFrtAmt = BigDecimal.ZERO;

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> getCompLst = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (Map<String, Object> comp : getCompLst) {

                String lineSubNumComp = (String) comp.get("TRX_LINE_SUB_NUM");
                String deleteShpgPlnNum = (String) comp.get("SHPG_PLN_NUM");

                if (lineSubNum.equals(lineSubNumComp)) {

                    continue;
                }

                lineSubNum = lineSubNumComp;

                SHPG_PLNTMsg originalShpgPlnMsg = getShpgPlnFindByKey(glblCmpyCd, deleteShpgPlnNum);

                ordQty += originalShpgPlnMsg.ordQty.getValueInt();
                wkSpTotDealFrtAmt = wkSpTotDealFrtAmt.add(originalShpgPlnMsg.spTotDealFrtAmt.getValue());
                wkSpTotFuncFrtAmt = wkSpTotFuncFrtAmt.add(originalShpgPlnMsg.spTotFuncFrtAmt.getValue());

                // --------------------
                // logicalRemove SHPG_PLN
                // --------------------
                EZDTBLAccessor.logicalRemove(originalShpgPlnMsg);
                delShplgPlnNumLst.add(deleteShpgPlnNum);

                if (setShpgPlnNum == null) {

                    EZDMsg.copy(originalShpgPlnMsg, null, edtShpgPlnRec, null);

                    // --------------------
                    // create SHPG_PLN
                    // --------------------
                    newSetShpgPlnNum = insertShpgPln(bizMsg, edtShpgPlnRec, ordQty, softAllocQty, wkSpTotDealFrtAmt, wkSpTotFuncFrtAmt);

                    newShplgPlnNum.put("DEL_ORIG_SHPG_PLN_NUM", deleteShpgPlnNum);
                    newShplgPlnNum.put("NEW_SHPG_PLN_NUM", newSetShpgPlnNum);
                    newShplgPlnNum.put("ORD_QTY", new BigDecimal(ordQty));

                } else {

                    SHPG_PLNTMsgArray shpgPlnMsgArray = getSetShpgPlnFindByCondition(originalShpgPlnMsg, setShpgPlnNum, lineSubNum);

                    if (shpgPlnMsgArray.getValidCount() > 0) {

                        EZDMsg.copy(shpgPlnMsgArray.no(0), null, edtShpgPlnRec, null);

                        ordQty += shpgPlnMsgArray.no(0).ordQty.getValueInt();
                        wkSpTotDealFrtAmt = wkSpTotDealFrtAmt.add(originalShpgPlnMsg.spTotDealFrtAmt.getValue());
                        wkSpTotFuncFrtAmt = wkSpTotFuncFrtAmt.add(originalShpgPlnMsg.spTotFuncFrtAmt.getValue());

                        // --------------------
                        // logicalRemove SHPG_PLN
                        // --------------------
                        EZDTBLAccessor.logicalRemove(shpgPlnMsgArray.no(0));
                        delShplgPlnNumLst.add(shpgPlnMsgArray.no(0).shpgPlnNum.getValue());

                        // --------------------
                        // create SHPG_PLN
                        // --------------------
                        newSetShpgPlnNum = insertShpgPln(bizMsg, edtShpgPlnRec, ordQty, softAllocQty, wkSpTotDealFrtAmt, wkSpTotFuncFrtAmt);

                        newShplgPlnNum.put("DEL_ORIG_SHPG_PLN_NUM", shpgPlnMsgArray.no(0).shpgPlnNum.getValue());
                        newShplgPlnNum.put("NEW_SHPG_PLN_NUM", newSetShpgPlnNum);
                        newShplgPlnNum.put("ORD_QTY", new BigDecimal(ordQty));

                    } else {

                        EZDMsg.copy(originalShpgPlnMsg, null, edtShpgPlnRec, null);

                        // --------------------
                        // create SHPG_PLN
                        // --------------------
                        newSetShpgPlnNum = insertShpgPln(bizMsg, edtShpgPlnRec, ordQty, softAllocQty, wkSpTotDealFrtAmt, wkSpTotFuncFrtAmt);

                        newShplgPlnNum.put("DEL_ORIG_SHPG_PLN_NUM", deleteShpgPlnNum);
                        newShplgPlnNum.put("NEW_SHPG_PLN_NUM", newSetShpgPlnNum);
                        newShplgPlnNum.put("ORD_QTY", new BigDecimal(ordQty));
                    }
                }

                if (newSetShpgPlnNum == null) {

                    return;
                }

                // --------------------
                // update PRC_DTL
                // --------------------
                updPrcDtl(bizMsg, delShplgPlnNumLst, newShplgPlnNum);

                // --------------------
                // update HOLD
                // --------------------
                updateHold(glblCmpyCd, originalShpgPlnMsg.shpgPlnNum.getValue(), newSetShpgPlnNum, ordQty);

                if ("E".equals(bizMsg.getMessageKind())) {

                    return;
                }
            }
        }
    }

    /**
     * divideProcess
     * @param originalShpgPlnMsg SHPG_PLNTMsg
     * @param divideQty BigDecimal
     * @param newSetShpgPlnNum String
     * @param sts String
     */
    private String divideProcess(final SHPG_PLNTMsg originalShpgPlnMsg, final BigDecimal divideQty, final String newSetShpgPlnNum, final String sts) {

        String glblCmpyCd = originalShpgPlnMsg.glblCmpyCd.getValue();
        String originalShpgPlnNum = originalShpgPlnMsg.shpgPlnNum.getValue();
        String trxHdrNum = originalShpgPlnMsg.trxHdrNum.getValue();
        String trxLineNum = originalShpgPlnMsg.trxLineNum.getValue();
        String trxLineSubNum = originalShpgPlnMsg.trxLineSubNum.getValue();
        String shpgPlnNumHA;
        PRC_DTLTMsgArray prcDtlData = null;
        SHPG_PLNTMsg haShpgPlnMsg = new SHPG_PLNTMsg();

        EZDMsg.copy(originalShpgPlnMsg, null, haShpgPlnMsg, null);

        // ---------------------------
        // Update SHPG_PLN
        // ---------------------------
        this.setTotalAmount(originalShpgPlnMsg, subtract(originalShpgPlnMsg.ordQty.getValue(), divideQty));

        if (originalShpgPlnMsg.softAllocQty.getValue().compareTo(BigDecimal.ZERO) > 0) {

            if (originalShpgPlnMsg.softAllocQty.getValue().compareTo(divideQty) == 0) {

                // softAllocQty = AllocatedQty
                haShpgPlnMsg.softAllocQty.setValue(divideQty);
                originalShpgPlnMsg.softAllocQty.setValue(BigDecimal.ZERO);

            } else if (originalShpgPlnMsg.softAllocQty.getValue().compareTo(divideQty) > 0) {

                // softAllocQty > AllocatedQty
                haShpgPlnMsg.softAllocQty.setValue(divideQty);
                originalShpgPlnMsg.softAllocQty.setValue(subtract(originalShpgPlnMsg.softAllocQty.getValue(), divideQty));

            } else {

                // softAllocQty < AllocatedQty
                haShpgPlnMsg.softAllocQty.setValue(originalShpgPlnMsg.softAllocQty.getValue());
                originalShpgPlnMsg.softAllocQty.setValue(BigDecimal.ZERO);
            }
        }

        if (originalShpgPlnMsg.slsHldQty.getValue().compareTo(BigDecimal.ZERO) == 0) {

            originalShpgPlnMsg.slsHldQty.setValue(BigDecimal.ZERO);

        } else {

            originalShpgPlnMsg.slsHldQty.setValue(subtract(haShpgPlnMsg.slsHldQty.getValue(), divideQty));
        }

        if (originalShpgPlnMsg.crHldQty.getValue().compareTo(BigDecimal.ZERO) == 0) {

            originalShpgPlnMsg.crHldQty.setValue(BigDecimal.ZERO);

        } else {

            originalShpgPlnMsg.crHldQty.setValue(subtract(haShpgPlnMsg.crHldQty.getValue(), divideQty));
        }

        BigDecimal calcCrChkQty = BigDecimal.ZERO;

        if (originalShpgPlnMsg.crChkQty.getValue().compareTo(BigDecimal.ZERO) == 0) {

            originalShpgPlnMsg.crChkQty.setValue(BigDecimal.ZERO);

        } else {

            calcCrChkQty = subtract(haShpgPlnMsg.crChkQty.getValue(), divideQty);

            if (BigDecimal.ZERO.compareTo(calcCrChkQty) > 0) {

                originalShpgPlnMsg.crChkQty.setValue(BigDecimal.ZERO);

            } else {

                originalShpgPlnMsg.crChkQty.setValue(calcCrChkQty);
            }
        }

        BigDecimal calcavalSoQty = BigDecimal.ZERO;

        if (originalShpgPlnMsg.avalSoQty.getValue().compareTo(BigDecimal.ZERO) == 0) {

            originalShpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);

        } else {

            calcavalSoQty = subtract(haShpgPlnMsg.avalSoQty.getValue(), divideQty);

            if (BigDecimal.ZERO.compareTo(calcavalSoQty) > 0) {

                originalShpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);

            } else {

                originalShpgPlnMsg.avalSoQty.setValue(calcavalSoQty);
            }
        }

        BigDecimal calcavalPoQty = BigDecimal.ZERO;

        if (originalShpgPlnMsg.avalPoQty.getValue().compareTo(BigDecimal.ZERO) == 0) {

            originalShpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);

        } else {

            calcavalPoQty = subtract(haShpgPlnMsg.avalPoQty.getValue(), divideQty);

            if (BigDecimal.ZERO.compareTo(calcavalPoQty) > 0) {

                originalShpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);

            } else {

                originalShpgPlnMsg.avalPoQty.setValue(calcavalPoQty);
            }
        }

        if (newSetShpgPlnNum != null) {

            originalShpgPlnMsg.setShpgPlnNum.setValue(newSetShpgPlnNum);
        }

        // **** update
        EZDTBLAccessor.update(originalShpgPlnMsg);

        // ---------------------------
        // Update PRC_DTL
        // ---------------------------
        if (TRX_SRC_TP.WHOLE_SALES.equals(originalShpgPlnMsg.trxSrcTpCd.getValue())) {

            prcDtlData = this.updatePrcDtlFromshpgPlnNum(glblCmpyCd, originalShpgPlnNum, multiply(divideQty, BigDecimal.valueOf(-1)));

            if (prcDtlData == null) {

                return null;
            }
        }

        // ---------------------------
        // Update HLD
        // ---------------------------
        HLDTMsgArray hldData = this.updateHldFromshpgPlnNum(glblCmpyCd, originalShpgPlnNum, trxHdrNum, trxLineNum, trxLineSubNum, multiply(divideQty, BigDecimal.valueOf(-1)));

        // ---------------------------
        // Insert SHPG_PLN (Shipping Plan Status 'Hard Allocated' Data)
        // ---------------------------
        haShpgPlnMsg.shpgPlnNum.setValue(getShipgPlnSQ());
        String shpgPlnDplyLineNum = this.getDplyLineNum(haShpgPlnMsg);

        if (shpgPlnDplyLineNum == null) {

            return null;
        }

        haShpgPlnMsg.shpgPlnDplyLineNum.setValue(shpgPlnDplyLineNum);
        haShpgPlnMsg.shpgStsCd.setValue(sts);
        haShpgPlnMsg.ordQty.setValue(divideQty);

        this.setTotalAmount(haShpgPlnMsg, divideQty);

        if (originalShpgPlnMsg.slsHldQty.getValue().compareTo(BigDecimal.ZERO) == 0) {

            haShpgPlnMsg.slsHldQty.setValue(BigDecimal.ZERO);

        } else {

            haShpgPlnMsg.slsHldQty.setValue(divideQty);
        }

        if (originalShpgPlnMsg.crHldQty.getValue().compareTo(BigDecimal.ZERO) == 0) {

            haShpgPlnMsg.crHldQty.setValue(BigDecimal.ZERO);

        } else {

            haShpgPlnMsg.crHldQty.setValue(divideQty);
        }

        if (originalShpgPlnMsg.crChkQty.getValue().compareTo(BigDecimal.ZERO) == 0) {

            haShpgPlnMsg.crChkQty.setValue(BigDecimal.ZERO);

        } else {

            if (BigDecimal.ZERO.compareTo(calcCrChkQty) == 0) {

                haShpgPlnMsg.crChkQty.setValue(BigDecimal.ZERO);

            } else {

                haShpgPlnMsg.crChkQty.setValue(divideQty);
            }
        }

        if (originalShpgPlnMsg.avalSoQty.getValue().compareTo(BigDecimal.ZERO) == 0) {

            haShpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);

        } else {

            if (BigDecimal.ZERO.compareTo(calcavalSoQty) == 0) {

                haShpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);

            } else {

                haShpgPlnMsg.avalSoQty.setValue(divideQty);
            }
        }

        if (originalShpgPlnMsg.avalPoQty.getValue().compareTo(BigDecimal.ZERO) == 0) {

            haShpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);

        } else {

            if (BigDecimal.ZERO.compareTo(calcavalPoQty) == 0) {

                haShpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);

            } else {

                haShpgPlnMsg.avalPoQty.setValue(divideQty);
            }
        }

        // **** Insert
        EZDTBLAccessor.insert(haShpgPlnMsg);

        shpgPlnNumHA = haShpgPlnMsg.shpgPlnNum.getValue();

        // ---------------------------
        // Insert PRC_DTL
        // ---------------------------
        if (S21StringUtil.isEquals(TRX_SRC_TP.WHOLE_SALES, originalShpgPlnMsg.trxSrcTpCd.getValue())) {

            this.insertPrcDtlFromshpgPlnNum(prcDtlData, glblCmpyCd, shpgPlnNumHA, divideQty);
        }

        // ---------------------------
        // Insert HLD
        // ---------------------------
        if (hldData.length() > 0) {

            this.insertHldFromshpgPlnNum(hldData, glblCmpyCd, shpgPlnNumHA, divideQty);
        }

        return shpgPlnNumHA;
    }

    /**
     * hardAllocDivideProcess
     * @param bizMsg NLBL3080CMsg
     * @param glblCmpyCd String
     * @param origShpgPlnNum String
     * @param newShpgPlnNum String
     * @param divideQty BigDecimal
     * @param newQty BigDecimal
     * @param softAllocMsg SOFT_ALLOCTMsg
     */
    private void hardAllocDivideProcess(NLBL3080CMsg bizMsg, final String glblCmpyCd, final String origShpgPlnNum, final String newShpgPlnNum, final BigDecimal divideQty, final BigDecimal newQty, final SOFT_ALLOCTMsg softAllocMsg) {

        HARD_ALLOCTMsgArray hardAllocMsgData = searchHardAlloc(glblCmpyCd, origShpgPlnNum);

        if (hardAllocMsgData.getValidCount() > 0) {

            // Update
            HARD_ALLOCTMsg originalHardAllocMsg = hardAllocMsgData.no(0);
            HARD_ALLOCTMsg newHardAllocMsg = new HARD_ALLOCTMsg();
            EZDMsg.copy(originalHardAllocMsg, null, newHardAllocMsg, null);

            originalHardAllocMsg.hardAllocQty.setValue(divideQty);
            originalHardAllocMsg.shpgPlnNum.setValue(origShpgPlnNum);

            if (softAllocMsg != null && S21StringUtil.isEquals(softAllocMsg.shpgPlnNum.getValue(), origShpgPlnNum)) {

                originalHardAllocMsg.softAllocPk.setValue(softAllocMsg.softAllocPk.getValue());
            }

            updateHardAlloc(bizMsg, originalHardAllocMsg);

            newHardAllocMsg.hardAllocQty.setValue(newQty);
            newHardAllocMsg.shpgPlnNum.setValue(newShpgPlnNum);
            newHardAllocMsg.hardAllocPk.setValue(getHardAllocSQ());

            if (softAllocMsg != null && S21StringUtil.isEquals(softAllocMsg.shpgPlnNum.getValue(), newShpgPlnNum)) {

                newHardAllocMsg.softAllocPk.setValue(softAllocMsg.softAllocPk.getValue());
            }

            insertHardAlloc(bizMsg, newHardAllocMsg);
        }
    }

    /**
     * softAllocDivideProcess
     * @param glblCmpyCd String
     * @param divideQty BigDecimal
     * @param divideShpgPlnNum String
     * @param origShpgPlnNum String
     * @param newSetSoftAllocPk BigDecimal
     */
    private SOFT_ALLOCTMsg softAllocDivideProcess(final String glblCmpyCd, final BigDecimal divideQty, final String divideShpgPlnNum, final String origShpgPlnNum, final BigDecimal newSetSoftAllocPk) {

        if (BigDecimal.ZERO.compareTo(divideQty) == 0) {

            return null;
        }

        SOFT_ALLOCTMsgArray softAllocMsgArray = this.searchSoftAllocForUpdate(glblCmpyCd, origShpgPlnNum);

        BigDecimal calcQty = BigDecimal.ZERO;
        calcQty = divideQty;
        SOFT_ALLOCTMsg softAllocMsg = new SOFT_ALLOCTMsg();

        // divideQty
        int softAllocSize = softAllocMsgArray.getValidCount();

        for (int i = 0; i < softAllocSize; i++) {

            if (calcQty.compareTo(softAllocMsgArray.no(i).softAllocQty.getValue()) < 0) {

                // Devide
                BigDecimal calcSoftAllocQty = BigDecimal.ZERO;
                SOFT_ALLOCTMsg newsoftAllocMsg = new SOFT_ALLOCTMsg();
                EZDMsg.copy(softAllocMsgArray.no(i), null, newsoftAllocMsg, null);

                calcSoftAllocQty = subtract(softAllocMsgArray.no(i).softAllocQty.getValue(), calcQty);

                newsoftAllocMsg.softAllocPk.setValue(getSoftAllocSQ());
                newsoftAllocMsg.hardAllocAvalQty.setValue(BigDecimal.ZERO);
                newsoftAllocMsg.softAllocQty.setValue(calcQty);
                newsoftAllocMsg.shpgPlnNum.setValue(divideShpgPlnNum);

                if (ZYPCommonFunc.hasValue(newSetSoftAllocPk)) {

                    newsoftAllocMsg.setSoftAllocPk.setValue(newSetSoftAllocPk);
                }

                // **** Insert
                EZDTBLAccessor.insert(newsoftAllocMsg);

                // update
                SOFT_ALLOCTMsg updsoftAllocMsg = softAllocMsgArray.no(i);
                updsoftAllocMsg.softAllocQty.setValue(calcSoftAllocQty);

                if (calcSoftAllocQty.compareTo(updsoftAllocMsg.hardAllocAvalQty.getValue()) < 0) {

                    updsoftAllocMsg.hardAllocAvalQty.setValue(calcSoftAllocQty);
                }

                // **** Update
                EZDTBLAccessor.update(updsoftAllocMsg);

                calcQty = BigDecimal.ZERO;
                softAllocMsg = newsoftAllocMsg;
                break;

            } else {

                // update
                SOFT_ALLOCTMsg updsoftAllocMsg = softAllocMsgArray.no(i);
                calcQty = subtract(calcQty, softAllocMsgArray.no(i).softAllocQty.getValue());
                updsoftAllocMsg.hardAllocAvalQty.setValue(BigDecimal.ZERO);
                updsoftAllocMsg.shpgPlnNum.setValue(divideShpgPlnNum);

                if (ZYPCommonFunc.hasValue(newSetSoftAllocPk)) {

                    updsoftAllocMsg.setSoftAllocPk.setValue(newSetSoftAllocPk);
                }

                // **** Update
                EZDTBLAccessor.update(updsoftAllocMsg);

                if (BigDecimal.ZERO.compareTo(calcQty) == 0) {

                    softAllocMsg = updsoftAllocMsg;
                    break;
                }
            }
        }

        return softAllocMsg;
    }

    /**
     * setSoftAllocDivideProcess
     * @param glblCmpyCd String
     * @param divideQty BigDecimal
     * @param divideShpgPlnNum String
     * @param origShpgPlnNum String
     */
    private BigDecimal setSoftAllocDivideProcess(final String glblCmpyCd, final BigDecimal divideQty, final String divideShpgPlnNum, final String origShpgPlnNum) {

        BigDecimal calcQty = BigDecimal.ZERO;
        calcQty = divideQty;
        BigDecimal newSoftAllocPk = null;

        SOFT_ALLOCTMsgArray softAllocMsgArray = this.searchSoftAllocForUpdate(glblCmpyCd, origShpgPlnNum);

        int softAllocSize = softAllocMsgArray.getValidCount();

        for (int i = 0; i < softAllocSize; i++) {

            if (BigDecimal.ZERO.compareTo(calcQty) == 0) {

                break;
            }

            if (calcQty.compareTo(softAllocMsgArray.no(i).softAllocQty.getValue()) == 0) {

                SOFT_ALLOCTMsg updsoftAllocMsg = softAllocMsgArray.no(i);
                updsoftAllocMsg.shpgPlnNum.setValue(divideShpgPlnNum);
                updsoftAllocMsg.setSoftAllocPk.setValue(updsoftAllocMsg.softAllocPk.getValue());

                // **** Update
                EZDTBLAccessor.update(updsoftAllocMsg);
                break;

            } else if (calcQty.compareTo(softAllocMsgArray.no(i).softAllocQty.getValue()) < 0) {

                // Divide
                BigDecimal calcSoftAllocQty = BigDecimal.ZERO;
                SOFT_ALLOCTMsg newsoftAllocMsg = new SOFT_ALLOCTMsg();
                EZDMsg.copy(softAllocMsgArray.no(i), null, newsoftAllocMsg, null);

                newSoftAllocPk = getSoftAllocSQ();
                newsoftAllocMsg.softAllocPk.setValue(newSoftAllocPk);
                newsoftAllocMsg.hardAllocAvalQty.setValue(BigDecimal.ZERO);
                newsoftAllocMsg.softAllocQty.setValue(calcQty);
                newsoftAllocMsg.shpgPlnNum.setValue(divideShpgPlnNum);
                newsoftAllocMsg.setSoftAllocPk.setValue(newSoftAllocPk);

                // **** Insert
                EZDTBLAccessor.insert(newsoftAllocMsg);

                SOFT_ALLOCTMsg updsoftAllocMsg = softAllocMsgArray.no(i);
                calcSoftAllocQty = subtract(softAllocMsgArray.no(i).softAllocQty.getValue(), calcQty);
                updsoftAllocMsg.softAllocQty.setValue(calcSoftAllocQty);
                updsoftAllocMsg.setSoftAllocPk.setValue(updsoftAllocMsg.softAllocPk.getValue());

                // **** Update
                EZDTBLAccessor.update(updsoftAllocMsg);

                break;

            } else {

                // none
                calcQty = subtract(calcQty, softAllocMsgArray.no(i).softAllocQty.getValue());
                continue;
            }
        }

        return newSoftAllocPk;
    }

    /**
     * delete price deteil
     * @param bizMsg NLBL3080CMsg
     * @param String <String> delShpgPlnNum
     */
    private void deletePrcDtl(NLBL3080CMsg bizMsg, final String delShpgPlnNum) {

        PRC_DTLTMsg prcDtlTMsg = new PRC_DTLTMsg();
        prcDtlTMsg.setSQLID("007");
        prcDtlTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        prcDtlTMsg.setConditionValue("shpgPlnNum01", delShpgPlnNum);
        PRC_DTLTMsgArray prcDtlTMsgArray = (PRC_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prcDtlTMsg);

        if (prcDtlTMsgArray.getValidCount() > 0) {

            for (int i = 0; i < prcDtlTMsgArray.getValidCount(); i++) {

                PRC_DTLTMsg edtPrcDtlRec = prcDtlTMsgArray.no(i);
                EZDTBLAccessor.logicalRemove(edtPrcDtlRec);
            }

        } else {

            bizMsg.setMessageInfo("NWDM0007E");
        }
    }

    /**
     * Update Hold
     * @param glblCmpyCd String
     * @param originalShpgPlnNum String
     * @param newShpgPlnNum String
     * @param ordQty int
     */
    private void updateHold(String glblCmpyCd, String originalShpgPlnNum, String newShpgPlnNum, int ordQty) {

        S21SsmEZDResult ssmResult = NLBL3080Query.getInstance().getShpgLvlSalesHold(glblCmpyCd, originalShpgPlnNum);

        if (ssmResult.isCodeNormal()) {

            List<BigDecimal> hldPkList = (List<BigDecimal>) ssmResult.getResultObject();

            for (BigDecimal hldPk : hldPkList) {

                HLDTMsg hldMsg = new HLDTMsg();
                hldMsg.glblCmpyCd.setValue(glblCmpyCd);
                hldMsg.hldPk.setValue(hldPk);
                hldMsg = (HLDTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(hldMsg);

                if (hldMsg != null) {

                    hldMsg.shpgPlnNum.setValue(newShpgPlnNum);
                    hldMsg.hldQty.setValue(new BigDecimal(ordQty));
                    EZDTBLAccessor.update(hldMsg);
                }
            }
        }
    }

    /**
     * delete hold
     * @param String NLBL3080CMsg
     * @param delShpgPlnNum String
     */
    private void deleteHld(NLBL3080CMsg bizMsg, String delShpgPlnNum) {

        HLDTMsg hldTMsg = new HLDTMsg();
        hldTMsg.setSQLID("022");
        hldTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        hldTMsg.setConditionValue("shpgPlnNum01", delShpgPlnNum);
        HLDTMsgArray hldTMsgArray = (HLDTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(hldTMsg);

        if (hldTMsgArray.getValidCount() > 0) {

            for (int i = 0; i < hldTMsgArray.getValidCount(); i++) {

                EZDTBLAccessor.logicalRemove(hldTMsgArray.no(i));
            }
        }
    }

    /**
     * delete hold
     * @param bizMsg NLBL3080CMsg
     * @param delShpgPlnNumLst List<String>
     */
    private void deleteHld(NLBL3080CMsg bizMsg, List<String> delShpgPlnNumLst) {

        // Shipping plan number loop
        for (String delShpgPlnNum : delShpgPlnNumLst) {

            // [HLD]
            final HLDTMsg hldTMsg = new HLDTMsg();
            hldTMsg.setSQLID("022");
            hldTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
            hldTMsg.setConditionValue("shpgPlnNum01", delShpgPlnNum);

            final HLDTMsgArray hldTMsgArray = (HLDTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(hldTMsg);

            for (int i = 0; i < hldTMsgArray.getValidCount(); i++) {

                // logicalRemove
                EZDTBLAccessor.logicalRemove(hldTMsgArray.no(i));
            }
        }
    }

    /**
     * get setShpgPln(Validated)
     * @param edtShpgPlnRec SHPG_PLNTMsg
     * @return SHPG_PLNTMsgArray
     */
    private SHPG_PLNTMsgArray searchShpgPlnValidatedForUpdate(SHPG_PLNTMsg edtShpgPlnRec) {

        SHPG_PLNTMsg vaShpgPlnMsg = new SHPG_PLNTMsg();
        vaShpgPlnMsg.setSQLID("020");
        vaShpgPlnMsg.setConditionValue("glblCmpyCd01", edtShpgPlnRec.glblCmpyCd.getValue());
        vaShpgPlnMsg.setConditionValue("trxHdrNum01", edtShpgPlnRec.trxHdrNum.getValue());
        vaShpgPlnMsg.setConditionValue("trxLineNum01", edtShpgPlnRec.trxLineNum.getValue());
        vaShpgPlnMsg.setConditionValue("trxLineSubNum01", edtShpgPlnRec.trxLineSubNum.getValue());
        vaShpgPlnMsg.setConditionValue("trxSrcTpCd01", edtShpgPlnRec.trxSrcTpCd.getValue());
        vaShpgPlnMsg.setConditionValue("shpgStsCd01", SHPG_STS.VALIDATED);
        vaShpgPlnMsg.setMaxCount(2);

        SHPG_PLNTMsgArray shpgPlnMsgRcd = (SHPG_PLNTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(vaShpgPlnMsg);

        return shpgPlnMsgRcd;
    }

    /**
     * get setShpgPln
     * @param shpgPlnMsgRcd SHPG_PLNTMsg
     * @param setShpgPlnNum String
     * @param trxLineSubNum String
     * @return SHPG_PLNTMsgArray
     */
    private SHPG_PLNTMsgArray getSetShpgPlnFindByCondition(SHPG_PLNTMsg shpgPlnMsgRcd, final String setShpgPlnNum, final String trxLineSubNum) {

        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        shpgPlnMsg.setSQLID("035");
        shpgPlnMsg.setConditionValue("glblCmpyCd01", shpgPlnMsgRcd.glblCmpyCd.getValue());
        shpgPlnMsg.setConditionValue("trxHdrNum01", shpgPlnMsgRcd.trxHdrNum.getValue());
        shpgPlnMsg.setConditionValue("trxLineNum01", shpgPlnMsgRcd.trxLineNum.getValue());
        shpgPlnMsg.setConditionValue("trxLineSubNum01", trxLineSubNum);
        shpgPlnMsg.setConditionValue("trxSrcTpCd01", shpgPlnMsgRcd.trxSrcTpCd.getValue());
        shpgPlnMsg.setConditionValue("setShpgPlnNum01", setShpgPlnNum);
        SHPG_PLNTMsgArray shpgPlnMsgArray = (SHPG_PLNTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(shpgPlnMsg);

        return shpgPlnMsgArray;
    }

    /**
     * get SetShpgPlnNum
     * @param edtShpgPlnRec SHPG_PLNTMsg
     * @param setShpgPlnNum String
     * @return SHPG_PLNTMsgArray
     */
    private SHPG_PLNTMsgArray searchShpgPlnNumForUpdate(SHPG_PLNTMsg edtShpgPlnRec, final String setShpgPlnNum) {

        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        shpgPlnMsg.setSQLID("033");
        shpgPlnMsg.setConditionValue("glblCmpyCd01", edtShpgPlnRec.glblCmpyCd.getValue());
        shpgPlnMsg.setConditionValue("trxHdrNum01", edtShpgPlnRec.trxHdrNum.getValue());
        shpgPlnMsg.setConditionValue("trxLineNum01", edtShpgPlnRec.trxLineNum.getValue());
        shpgPlnMsg.setConditionValue("trxSrcTpCd01", edtShpgPlnRec.trxSrcTpCd.getValue());
        shpgPlnMsg.setConditionValue("setShpgPlnNum01", setShpgPlnNum);

        SHPG_PLNTMsgArray shpgPlnMsgRcd = (SHPG_PLNTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(shpgPlnMsg);

        return shpgPlnMsgRcd;
    }

    /**
     * get setShpgPln
     * @param glblCmpyCd String
     * @param setShpgPlnNum String
     * @return SHPG_PLNTMsg
     */
    private SHPG_PLNTMsg getShpgPlnFindByKey(String glblCmpyCd, String setShpgPlnNum) {

        // Get SetItem SHPG_PLN
        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        shpgPlnMsg.glblCmpyCd.setValue(glblCmpyCd);
        shpgPlnMsg.shpgPlnNum.setValue(setShpgPlnNum);

        SHPG_PLNTMsg shpgPlnMsgRcd = (SHPG_PLNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(shpgPlnMsg);

        return shpgPlnMsgRcd;
    }

    /**
     * get searchHardAlloc
     * @param glblCmpyCd String
     * @param setShpgPlnNum String
     * @return HARD_ALLOCTMsgArray
     */
    private HARD_ALLOCTMsgArray searchHardAlloc(final String glblCmpyCd, final String shpgPlnNum) {

        HARD_ALLOCTMsg msg = new HARD_ALLOCTMsg();
        msg.setSQLID("009");
        msg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        msg.setConditionValue("shpgPlnNum01", shpgPlnNum);

        HARD_ALLOCTMsgArray msgArray = (HARD_ALLOCTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(msg);
        return msgArray;
    }

    /**
     * insertShpgPln
     * @param bizMsg NLBL3080CMsg
     * @param edtShpgPlnRec SHPG_PLNTMsg
     * @param wkOrdQty int
     * @param wkSoftAllocQty int
     * @param spTotDealFrtAmt BigDecimal
     * @param spTotFuncFrtAmt BigDecimal
     * @return String
     */
    private String insertShpgPln(NLBL3080CMsg bizMsg, SHPG_PLNTMsg edtShpgPlnRec, int wkOrdQty, int wkSoftAllocQty, BigDecimal spTotDealFrtAmt, BigDecimal spTotFuncFrtAmt) {

        // total calculation
        BigDecimal wkSpDealUnitPrcAmt = edtShpgPlnRec.spDealUnitPrcAmt.getValue();
        BigDecimal wkSpDealNetUnitPrcAmt = edtShpgPlnRec.spDealNetUnitPrcAmt.getValue();
        BigDecimal wkSpTotDealSlsAmt = wkSpDealUnitPrcAmt.multiply(new BigDecimal(wkOrdQty));
        BigDecimal wkSpTotDealNetAmt = wkSpDealNetUnitPrcAmt.multiply(new BigDecimal(wkOrdQty));
        BigDecimal wkSpFuncUnitPrcAmt = edtShpgPlnRec.spFuncUnitPrcAmt.getValue();
        BigDecimal wkSpFuncNetUnitPrcAmt = edtShpgPlnRec.spFuncNetUnitPrcAmt.getValue();
        BigDecimal wkSpTotFuncSlsAmt = wkSpFuncUnitPrcAmt.multiply(new BigDecimal(wkOrdQty));
        BigDecimal wkSpTotFuncNetAmt = wkSpFuncNetUnitPrcAmt.multiply(new BigDecimal(wkOrdQty));
        BigDecimal wkSpTotDealFrtAmt = spTotDealFrtAmt;
        BigDecimal wkSpTotFuncFrtAmt = spTotFuncFrtAmt;

        if (!ZYPCommonFunc.hasValue(wkSpTotDealFrtAmt)) {

            wkSpTotDealFrtAmt = BigDecimal.ZERO;
        }

        if (!ZYPCommonFunc.hasValue(wkSpTotFuncFrtAmt)) {

            wkSpTotFuncFrtAmt = BigDecimal.ZERO;
        }

        CPO_DTLTMsg cpoDtlMsg = getCpoDtlCache(edtShpgPlnRec);

        if (cpoDtlMsg == null) {

            bizMsg.setMessageInfo("NLBM0049E");
            return null;
        }

        // insert data edit
        String wkShpgPlnNum = getShipgPlnSQ();
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.shpgPlnNum, wkShpgPlnNum);

        String shpgPlnDplyLineNum = getDplyLineNum(edtShpgPlnRec);

        if (shpgPlnDplyLineNum == null) {

            bizMsg.setMessageInfo("NLBM0049E");
            return null;
        }

        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.shpgPlnDplyLineNum, shpgPlnDplyLineNum);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.trxSrcTpCd, TRX_SRC_TP.WHOLE_SALES);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.shpgStsCd, SHPG_STS.VALIDATED);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.rteStsCd, RTE_STS.UN_ROUTED);

        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.reqShpgSvcLvlCd, cpoDtlMsg.shpgSvcLvlCd.getValue());
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.origShpgSvcLvlCd, cpoDtlMsg.shpgSvcLvlCd.getValue());
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.mdseCd, cpoDtlMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.invtyLocCd, cpoDtlMsg.invtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.reqFrtChrgMethCd, cpoDtlMsg.frtChrgMethCd.getValue());
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.reqFrtChrgToCd, cpoDtlMsg.frtChrgToCd.getValue());

        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.spTotDealSlsAmt, wkSpTotDealSlsAmt);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.spTotDealNetAmt, wkSpTotDealNetAmt);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.spTotDealFrtAmt, wkSpTotDealFrtAmt);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.spTotFuncSlsAmt, wkSpTotFuncSlsAmt);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.spTotFuncNetAmt, wkSpTotFuncNetAmt);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.spTotFuncFrtAmt, wkSpTotFuncFrtAmt);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.avalHardAllocQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.avalSoftAllocQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.avalSoQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.avalPoQty, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.shipAvalFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.soCloseFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.shipPlnHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.shipPlnCancFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.softAllocQty, new BigDecimal(wkSoftAllocQty));
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.ordQty, new BigDecimal(wkOrdQty));
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.crHldQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.slsHldQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.crChkQty, edtShpgPlnRec.ordQty);

        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.setPrcDetGrpSq, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(edtShpgPlnRec.sendEmlCpltFlg, ZYPConstant.FLG_OFF_N);

        edtShpgPlnRec.soNum.clear();
        edtShpgPlnRec.poOrdNum.clear();
        edtShpgPlnRec.thirdPtyInvNum.clear();
        edtShpgPlnRec.actlShpgMethCd.clear();
        edtShpgPlnRec.actlShpgTermCd.clear();
        edtShpgPlnRec.actlShpgSvcLvlCd.clear();
        edtShpgPlnRec.actlFrtChrgToCd.clear();
        edtShpgPlnRec.actlFrtChrgMethCd.clear();
        edtShpgPlnRec.carrCd.clear();
        edtShpgPlnRec.carrNm.clear();
        edtShpgPlnRec.bolNum.clear();
        edtShpgPlnRec.proNum.clear();
        edtShpgPlnRec.invNum.clear();
        edtShpgPlnRec.psdDt.clear();
        edtShpgPlnRec.pddDt.clear();
        edtShpgPlnRec.etaDt.clear();
        edtShpgPlnRec.actlShipDt.clear();
        edtShpgPlnRec.actlArvDt.clear();
        edtShpgPlnRec.shipCmntFirstLineTxt.clear();
        edtShpgPlnRec.shipCmntScdLineTxt.clear();
        edtShpgPlnRec.shipCmntThirdLineTxt.clear();
        edtShpgPlnRec.shipCmntFrthLineTxt.clear();
        edtShpgPlnRec.reqShpgMethCd.clear();
        edtShpgPlnRec.reqShpgTermCd.clear();
        edtShpgPlnRec.doNum.clear();
        edtShpgPlnRec.shipPlnCancDt.clear();

        EZDTBLAccessor.insert(edtShpgPlnRec);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(edtShpgPlnRec.getReturnCode())) {

            bizMsg.setMessageInfo("NLBM0049E");
            return null;
        }

        return wkShpgPlnNum;
    }

    /**
     * get shipg pln seq
     */
    private String getShipgPlnSQ() {

        String shpgPlnCd;
        shpgPlnCd = ZYPOracleSeqAccessor.getNumberString(ZYPOracleSeqAccessor.SHPG_PLN_SQ,NLBL3080Constant.SHPG_PLN_NUM_DIGIT);
        return shpgPlnCd;
    }

    /**
     * get dply line number
     * @param bizMsg DADL0010CMsg
     * @param Integer idx
     */
    private String getDplyLineNum(SHPG_PLNTMsg edtShpgPlnRec) {

        String glblCmpyCd = edtShpgPlnRec.glblCmpyCd.getValue();
        String trxHdrNum = edtShpgPlnRec.trxHdrNum.getValue();
        String trxLineNum = edtShpgPlnRec.trxLineNum.getValue();
        String trxLineSubNum = edtShpgPlnRec.trxLineSubNum.getValue();

        // get merchandise .
        S21SsmEZDResult ssmResult = NLBL3080Query.getInstance().getShpgPlnDplyLineNum(glblCmpyCd, trxHdrNum, trxLineNum, trxLineSubNum);
        Integer resNum = (Integer) ssmResult.getResultObject();

        if (resNum == 0) {

            return null;

        } else {

            return String.format("%03d", resNum + 1);
        }
    }

    /**
     * get CpoDtl
     * @param edtShpgPlnRec SHPG_PLNTMsg
     * @return CPO_DTLTMsg
     */
    private CPO_DTLTMsg getCpoDtlCache(SHPG_PLNTMsg edtShpgPlnRec) {

        // Get CPO_DTL
        CPO_DTLTMsg setCpoDtlMsg = new CPO_DTLTMsg();

        setCpoDtlMsg.glblCmpyCd.setValue(edtShpgPlnRec.glblCmpyCd.getValue());
        setCpoDtlMsg.cpoOrdNum.setValue(edtShpgPlnRec.trxHdrNum.getValue());
        setCpoDtlMsg.cpoDtlLineNum.setValue(edtShpgPlnRec.trxLineNum.getValue());
        setCpoDtlMsg.cpoDtlLineSubNum.setValue(edtShpgPlnRec.trxLineSubNum.getValue());

        CPO_DTLTMsg cpoDtlMsgRcd = (CPO_DTLTMsg) S21CacheTBLAccessor.findByKey(setCpoDtlMsg);

        return cpoDtlMsgRcd;
    }

    /**
     * delete price deteil
     * @param bizMsg NLBL3080CMsg
     * @param List <String> delShpgPlnNumLst
     * @param Map <String> newShpgPlnNum
     */
    private void updPrcDtl(NLBL3080CMsg bizMsg, List<String> delShpgPlnNumLst, Map<String, Object> newShpgPlnNum) {

        // Shipping plan number loop
        for (String delShpgPlnNum : delShpgPlnNumLst) {

            // price deteil
            PRC_DTLTMsg prcDtlTMsg = new PRC_DTLTMsg();
            prcDtlTMsg.setSQLID("007");
            prcDtlTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
            prcDtlTMsg.setConditionValue("shpgPlnNum01", delShpgPlnNum);
            PRC_DTLTMsgArray prcDtlTMsgArray = (PRC_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prcDtlTMsg);

            if (prcDtlTMsgArray.getValidCount() > 0) {

                for (int i = 0; i < prcDtlTMsgArray.getValidCount(); i++) {

                    PRC_DTLTMsg edtPrcDtlRec = prcDtlTMsgArray.no(i);

                    // logicalRemove
                    EZDTBLAccessor.logicalRemove(edtPrcDtlRec);

                    String wkDelShpgPlnNum = (String) newShpgPlnNum.get("DEL_ORIG_SHPG_PLN_NUM");

                    if (wkDelShpgPlnNum.equals(edtPrcDtlRec.shpgPlnNum.getValue())) {

                        BigDecimal wkShipUnitQtyNew = (BigDecimal) newShpgPlnNum.get("ORD_QTY");
                        BigDecimal wkDealLastNetUnitPrcAmt = edtPrcDtlRec.dealLastNetUnitPrcAmt.getValue();
                        BigDecimal wkFuncLastNetUnitPrcAmt = edtPrcDtlRec.funcLastNetUnitPrcAmt.getValue();
                        BigDecimal wkDealNetAmt = wkDealLastNetUnitPrcAmt.multiply(wkShipUnitQtyNew);
                        BigDecimal wkFuncNetAmt = wkFuncLastNetUnitPrcAmt.multiply(wkShipUnitQtyNew);

                        ZYPEZDItemValueSetter.setValue(edtPrcDtlRec.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                        ZYPEZDItemValueSetter.setValue(edtPrcDtlRec.prcDtlPk, getPrcDtlPk());
                        ZYPEZDItemValueSetter.setValue(edtPrcDtlRec.shpgPlnNum, (String) newShpgPlnNum.get("NEW_SHPG_PLN_NUM"));
                        ZYPEZDItemValueSetter.setValue(edtPrcDtlRec.shipUnitQty, (BigDecimal) newShpgPlnNum.get("ORD_QTY"));
                        ZYPEZDItemValueSetter.setValue(edtPrcDtlRec.dealNetAmt, wkDealNetAmt);
                        ZYPEZDItemValueSetter.setValue(edtPrcDtlRec.funcNetAmt, wkFuncNetAmt);

                        if (ZYPCommonFunc.hasValue(edtPrcDtlRec.dealPerUnitFixAmt)) {

                            BigDecimal wkDealPerUnitFixAmt = edtPrcDtlRec.dealPerUnitFixAmt.getValue();
                            BigDecimal wkDealDiscAmt = wkDealPerUnitFixAmt.multiply(wkShipUnitQtyNew);
                            ZYPEZDItemValueSetter.setValue(edtPrcDtlRec.dealDiscAmt, wkDealDiscAmt);
                        }

                        if (ZYPCommonFunc.hasValue(edtPrcDtlRec.funcPerUnitFixAmt)) {

                            BigDecimal wkFuncPerUnitFixAmt = edtPrcDtlRec.funcPerUnitFixAmt.getValue();
                            BigDecimal wkFuncDiscAmt = wkFuncPerUnitFixAmt.multiply(wkShipUnitQtyNew);
                            ZYPEZDItemValueSetter.setValue(edtPrcDtlRec.funcDiscAmt, wkFuncDiscAmt);
                        }

                        // insert
                        EZDTBLAccessor.insert(edtPrcDtlRec);
                    }
                }

            } else {

                bizMsg.setMessageInfo(NLBL3080Constant.NLBM0049E);
            }
        }
    }

    /**
     * update SOFT_ALLOC (Set)
     * @param bizMsg NLBL3080CMsg
     * @param glblCmpyCd String
     * @param setShpgPlnNum String
     * @param newSetShpgPlnNum String
     * @param margeFlg String
     */
    private void updSetSoftAlloc(NLBL3080CMsg bizMsg, final String glblCmpyCd, final String setShpgPlnNum, final String newSetShpgPlnNum, final String margeFlg) {

        SOFT_ALLOCTMsgArray softAllocRcdList = searchSoftAllocForUpdate(glblCmpyCd, setShpgPlnNum);

        if (softAllocRcdList.length() == 0) {

            return;
        }

        if (margeFlg != null) {

            // Marge
            SOFT_ALLOCTMsgArray softAllocValitList = searchSoftAllocForUpdate(glblCmpyCd, newSetShpgPlnNum);

            for (int k = 0; k < softAllocRcdList.length(); k++) {

                boolean updateFlg = true;
                String softAllocTs = softAllocRcdList.no(k).softAllocTs.getValue();
                String distPlnNum = softAllocRcdList.no(k).distPlnNum.getValue();
                BigDecimal distPk = softAllocRcdList.no(k).distPk.getValue();
                BigDecimal distStruSegPk = softAllocRcdList.no(k).distStruSegPk.getValue();
                BigDecimal softAllocPk = softAllocRcdList.no(k).softAllocPk.getValue();
                String distTmFrameNum = softAllocRcdList.no(k).distTmFrameNum.getValue();

                for (int j = 0; j < softAllocValitList.length(); j++) {

                    if (S21StringUtil.isEquals(softAllocTs, softAllocValitList.no(j).softAllocTs.getValue()) && S21StringUtil.isEquals(distPlnNum, softAllocValitList.no(j).distPlnNum.getValue())
                            && S21StringUtil.isEquals(distTmFrameNum, softAllocValitList.no(j).distTmFrameNum.getValue()) && distPk.compareTo(softAllocValitList.no(j).distPk.getValue()) == 0
                            && distStruSegPk.compareTo(softAllocValitList.no(j).distStruSegPk.getValue()) == 0 && softAllocPk.compareTo(softAllocValitList.no(j).softAllocPk.getValue()) != 0) {

                        softAllocValitList.no(j).softAllocQty.setValue(softAllocValitList.no(j).softAllocQty.getValue().add(softAllocRcdList.no(k).softAllocQty.getValue()));
                        softAllocValitList.no(j).hardAllocAvalQty.setValue(softAllocValitList.no(j).hardAllocAvalQty.getValue().add(softAllocRcdList.no(k).hardAllocAvalQty.getValue()));

                        // Delete SOFT_ALLOC
                        EZDTBLAccessor.logicalRemove(softAllocRcdList.no(k));

                        // Update SOFT_ALLOC (The value of SET_SOFT_ALLOC_PK is updated.)
                        updSetSoftAllocPkByPartialValue(glblCmpyCd, softAllocRcdList.no(k).softAllocPk.getValue(), softAllocValitList.no(j).softAllocPk.getValue());

                        updateFlg = false;
                        break;

                    }
                }

                if (updateFlg) {

                    // ***** Update
                    softAllocRcdList.no(k).shpgPlnNum.setValue(newSetShpgPlnNum);

                    if (ZYPCommonFunc.hasValue(softAllocRcdList.no(k).setSoftAllocPk)) {

                        softAllocRcdList.no(k).setSoftAllocPk.setValue(softAllocRcdList.no(k).softAllocPk.getValue());
                    }

                    SOFT_ALLOCTMsg softAllocRec = updSoftAlloc(softAllocRcdList.no(k));

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(softAllocRec.getReturnCode())) {

                        bizMsg.setMessageInfo("NWDM0007E");
                        return;

                    }
                }
            }

            for (int j = 0; j < softAllocValitList.length(); j++) {

                SOFT_ALLOCTMsg softAllocRec = updSoftAlloc(softAllocValitList.no(j));

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(softAllocRec.getReturnCode())) {
                    bizMsg.setMessageInfo("NWDM0007E");
                    return;
                }
            }

        } else {

            // Update
            for (int i = 0; i < softAllocRcdList.length(); i++) {

                softAllocRcdList.no(i).shpgPlnNum.setValue(newSetShpgPlnNum);
                if (ZYPCommonFunc.hasValue(softAllocRcdList.no(i).setSoftAllocPk)) {
                    softAllocRcdList.no(i).setSoftAllocPk.setValue(softAllocRcdList.no(i).softAllocPk.getValue());
                }

                // ***** Update
                SOFT_ALLOCTMsg softAllocRec = updSoftAlloc(softAllocRcdList.no(i));

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(softAllocRec.getReturnCode())) {
                    bizMsg.setMessageInfo("NWDM0007E");
                    return;

                }
            }
        }
    }

    /**
     * update SOFT_ALLOC
     * @param softAllocMsg SOFT_ALLOCTMsg
     */
    private SOFT_ALLOCTMsg updSoftAlloc(SOFT_ALLOCTMsg softAllocMsg) {

        EZDTBLAccessor.update(softAllocMsg);
        return softAllocMsg;
    }

    /**
     * update SOFT_ALLOC
     * @param bizMsg NLBL3080CMsg
     * @param hardAllocMsg HARD_ALLOCTMsg
     */
    private void updateHardAlloc(NLBL3080CMsg bizMsg, HARD_ALLOCTMsg hardAllocMsg) {

        EZDTBLAccessor.update(hardAllocMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hardAllocMsg.getReturnCode())) {

            bizMsg.setMessageInfo("NWDM0007E");
        }
    }

    /**
     * update SOFT_ALLOC
     * @param bizMsg NLBL3080CMsg
     * @param hardAllocMsg HARD_ALLOCTMsg
     */
    private void insertHardAlloc(NLBL3080CMsg bizMsg, HARD_ALLOCTMsg hardAllocMsg) {

        EZDTBLAccessor.insert(hardAllocMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hardAllocMsg.getReturnCode())) {

            bizMsg.setMessageInfo("NWDM0007E");
        }
    }

    /**
     * update updByPartialValue
     * @param glblCmpyCd String
     * @param setSoftAllocPk BigDecimal
     * @param newSetSoftAllocPk BigDecimal
     */
    private int updSetSoftAllocPkByPartialValue(final String glblCmpyCd, final BigDecimal setSoftAllocPk, final BigDecimal newSetSoftAllocPk) {

        SOFT_ALLOCTMsg condSoftAllocMsg = new SOFT_ALLOCTMsg();
        SOFT_ALLOCTMsg inSoftAllocMsg = new SOFT_ALLOCTMsg();

        condSoftAllocMsg.glblCmpyCd.setValue(glblCmpyCd);
        condSoftAllocMsg.setSoftAllocPk.setValue(setSoftAllocPk);
        inSoftAllocMsg.setSoftAllocPk.setValue(newSetSoftAllocPk);

        return EZDTBLAccessor.updateByPartialValue(condSoftAllocMsg, new String[] {"glblCmpyCd", "setSoftAllocPk" }, inSoftAllocMsg, new String[] {"setSoftAllocPk" });
    }

    /**
     * get setSoftAlloc
     * @param glblCmpyCd String
     * @param setShpgPlnNum String
     */
    private SOFT_ALLOCTMsgArray searchSoftAllocForUpdate(final String glblCmpyCd, final String setShpgPlnNum) {

        SOFT_ALLOCTMsg setSoftAllocMsg = new SOFT_ALLOCTMsg();
        setSoftAllocMsg.setSQLID("003");
        setSoftAllocMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        setSoftAllocMsg.setConditionValue("shpgPlnNum01", setShpgPlnNum);

        SOFT_ALLOCTMsgArray softAllocRcd = (SOFT_ALLOCTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(setSoftAllocMsg);

        return softAllocRcd;
    }

    /**
     * update SOFT_ALLOC
     * @param bizMsg NLBL3080CMsg
     * @param glblCmpyCd String
     * @param shpgPlnNum String
     * @param setSoftAllocPk BigDecimal
     */
    private void updSoftAllocPk(NLBL3080CMsg bizMsg, final String glblCmpyCd, final String shpgPlnNum, final BigDecimal setSoftAllocPk) {

        SOFT_ALLOCTMsgArray softAllocRcdList = searchSoftAllocForUpdate(glblCmpyCd, shpgPlnNum);

        for (int i = 0; i < softAllocRcdList.length(); i++) {

            if (ZYPCommonFunc.hasValue(setSoftAllocPk) && ZYPCommonFunc.hasValue(softAllocRcdList.no(i).setSoftAllocPk)) {

                softAllocRcdList.no(i).setSoftAllocPk.setValue(setSoftAllocPk);
            }

            // ***** Update
            SOFT_ALLOCTMsg softAllocRec = updSoftAlloc(softAllocRcdList.no(i));

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(softAllocRec.getReturnCode())) {

                bizMsg.setMessageInfo("NWDM0007E");
                return;
            }
        }
    }

    /**
     * getInvtyAllocCancelPMsg
     * @param glblCmpyCd String
     * @param sMsgBLine NLBL3080_BSMsg
     * @return NWZC102001PMsg
     */
    private NWZC102001PMsg getInvtyAllocCancelPMsg(String glblCmpyCd, NLBL3080_BSMsg sMsgBLine) {

        NWZC102001PMsg allocPMsg = new NWZC102001PMsg();
        ZYPEZDItemValueSetter.setValue(allocPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(allocPMsg.xxOrigFuncTpCd, NWZC102001.ORG_FUNC_CD_HARD_ALLOC);
        ZYPEZDItemValueSetter.setValue(allocPMsg.xxRqstTpCd, NWZC102001.REQ_TP_SHIPPING_CANCEL);
        ZYPEZDItemValueSetter.setValue(allocPMsg.xxAllocTpCd, NWZC102001.ALLOC_TP_CD_HARD_ALLOC);
        ZYPEZDItemValueSetter.setValue(allocPMsg.trxSrcTpCd, sMsgBLine.trxSrcTpCd_B1);
        ZYPEZDItemValueSetter.setValue(allocPMsg.trxHdrNum, sMsgBLine.cpoOrdNum_B1);
        ZYPEZDItemValueSetter.setValue(allocPMsg.trxLineNum, sMsgBLine.cpoDtlLineNum_B1);
        ZYPEZDItemValueSetter.setValue(allocPMsg.trxLineSubNum, sMsgBLine.cpoDtlLineSubNum_B1);
        ZYPEZDItemValueSetter.setValue(allocPMsg.mdseCd, sMsgBLine.mdseCd_B1);
        ZYPEZDItemValueSetter.setValue(allocPMsg.invtyLocCd, sMsgBLine.invtyLocCd_B1);
        ZYPEZDItemValueSetter.setValue(allocPMsg.shpgSvcLvlCd, sMsgBLine.reqShpgSvcLvlCd_B1);
        ZYPEZDItemValueSetter.setValue(allocPMsg.slsDt, ZYPDateUtil.getSalesDate());
        return allocPMsg;
    }

    /**
     * getValidProcMgrPMsg
     * @param glblCmpyCd String
     * @param newShpgPlnNum String
     * @return NWZC004001PMsg
     */
    private NWZC004001PMsg getValidProcMgrPMsg(String glblCmpyCd, String newShpgPlnNum) {

        NWZC004001PMsg validProcMgrPMsg = new NWZC004001PMsg();
        ZYPEZDItemValueSetter.setValue(validProcMgrPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(validProcMgrPMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(validProcMgrPMsg.xxRqstTpCd, NWZC004001.VAL_TP_RC);
        ZYPEZDItemValueSetter.setValue(validProcMgrPMsg.shpgPlnNum_I, newShpgPlnNum);
        return validProcMgrPMsg;
    }

    /**
     * getdisplayOrderStatusPMsg
     * @param cMsg NLBL3080CMsg
     * @param glblCmpyCd String
     * @param newShpgPlnNum String
     * @return NWZC188001PMsg
     */
    private NWZC188001PMsg getdisplayOrderStatusPMsg(String glblCmpyCd, String cpoOrdNum, String newShpgPlnNum) {

        NWZC188001PMsg displayOrderStatusPMsg = new NWZC188001PMsg();
        ZYPEZDItemValueSetter.setValue(displayOrderStatusPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(displayOrderStatusPMsg.cpoOrdNum, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(displayOrderStatusPMsg.shpgPlnNumList.no(0).shpgPlnNum, newShpgPlnNum);
        return displayOrderStatusPMsg;
    }

    /**
     * searchPrcDtlForUpdate
     * @param glblCmpyCd String
     * @param shpgPlnNum String
     * @return PRC_DTLTMsgArray
     */
    private PRC_DTLTMsgArray searchPrcDtlForUpdate(final String glblCmpyCd, final String shpgPlnNum) {

        PRC_DTLTMsg inMsg = new PRC_DTLTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("shpgPlnNum01", shpgPlnNum);

        PRC_DTLTMsgArray retMsg = (PRC_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);

        return retMsg;
    }

    /**
     * searchHldForUpdate
     * @param glblCmpyCd String
     * @param shpgPlnNum String
     * @param cpoOrdNum String
     * @param cpoLineMum String
     * @param cpoLineSubMum String
     * @return HLDTMsgArray
     */
    private HLDTMsgArray searchHldForUpdate(final String glblCmpyCd, final String shpgPlnNum, final String cpoOrdNum, final String cpoLineMum, final String cpoLineSubMum) {

        HLDTMsg inMsg = new HLDTMsg();
        inMsg.setSQLID("017");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("shpgPlnNum01", shpgPlnNum);
        inMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        inMsg.setConditionValue("cpoDtlLineNum01", cpoLineMum);
        inMsg.setConditionValue("cpoDtlLineSubNum01", cpoLineSubMum);

        HLDTMsgArray retMsg = (HLDTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);

        return retMsg;
    }

    /**
     * getSoftAllocSQ
     * @return BigDecimal
     */
    private BigDecimal getSoftAllocSQ() {

        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SOFT_ALLOC_SQ);
    }

    /**
     * get price deteil pk
     * @return BigDecimal
     */
    private BigDecimal getPrcDtlPk() {

        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRC_DTL_SQ);
    }

    /**
     * getSts
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param poReqFlg String
     * @return BigDecimal
     */
    private String getSts(final String glblCmpyCd, final String mdseCd, final String poReqFlg) {

        String sts = "";

        MDSETMsg mdseMsg = getMdse(glblCmpyCd, mdseCd);

        if (mdseMsg == null) {

            return null;
        }

        if (ZYPConstant.FLG_ON_Y.equals(poReqFlg)) {

            sts = SHPG_STS.VALIDATED;

        } else if (S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, mdseMsg.invtyCtrlFlg.getValue())) {

            sts = SHPG_STS.VALIDATED;

        } else {

            sts = SHPG_STS.HARD_ALLOCATED;
        }

        return sts;
    }

    /**
     * getHardAllocSQ
     * @return BigDecimal
     */
    private static BigDecimal getHardAllocSQ() {

        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.HARD_ALLOC_SQ);
    }

    /**
     * getHldSQ
     * @return BigDecimal
     */
    private static BigDecimal getHldSQ() {

        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.HLD_SQ);
    }

    /**
     * getMdse
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return MDSETMsg
     */
    private static MDSETMsg getMdse(final String glblCmpyCd, final String mdseCd) {

        return NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
    }

    /**
     * roundHalfUp
     * @param big BigDecimal
     * @return BigDecimal
     */
    private static BigDecimal roundHalfUp(BigDecimal big) {

        return big.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * add
     * @param big1 BigDecimal
     * @param big2 BigDecimal
     * @return BigDecimal
     */
    private static BigDecimal add(BigDecimal big1, BigDecimal big2) {

        BigDecimal b1 = big1;
        BigDecimal b2 = big2;

        if (!ZYPCommonFunc.hasValue(b1)) {

            b1 = BigDecimal.ZERO;
        }

        if (!ZYPCommonFunc.hasValue(b2)) {

            b2 = BigDecimal.ZERO;
        }

        return b1.add(b2);
    }

    /**
     * multiply
     * @param big1 BigDecimal
     * @param big2 BigDecimal
     * @return BigDecimal
     */
    private static BigDecimal multiply(BigDecimal big1, BigDecimal big2) {

        BigDecimal b1 = big1;
        BigDecimal b2 = big2;

        if (!ZYPCommonFunc.hasValue(b1)) {

            b1 = BigDecimal.ZERO;
        }

        if (!ZYPCommonFunc.hasValue(b2)) {

            b2 = BigDecimal.ZERO;
        }

        return b1.multiply(b2);
    }

    /**
     * subtract
     * @param big1 BigDecimal
     * @param big2 BigDecimal
     * @return BigDecimal
     */
    private static BigDecimal subtract(BigDecimal big1, BigDecimal big2) {

        BigDecimal b1 = big1;
        BigDecimal b2 = big2;

        if (!ZYPCommonFunc.hasValue(b1)) {

            b1 = BigDecimal.ZERO;
        }

        if (!ZYPCommonFunc.hasValue(b2)) {

            b2 = BigDecimal.ZERO;
        }

        return b1.subtract(b2);
    }

    /**
     * setTotalAmount
     * @param shpgPlnMsg SHPG_PLNTMsg
     * @param setOrdQty BigDecimal
     */
    private void setTotalAmount(SHPG_PLNTMsg shpgPlnMsg, final BigDecimal setOrdQty) {

        shpgPlnMsg.ordQty.setValue(setOrdQty);
        shpgPlnMsg.spTotDealSlsAmt.setValue(roundHalfUp(multiply(shpgPlnMsg.spDealUnitPrcAmt.getValue(), setOrdQty)));
        shpgPlnMsg.spTotDealNetAmt.setValue(roundHalfUp(multiply(shpgPlnMsg.spDealNetUnitPrcAmt.getValue(), setOrdQty)));
        shpgPlnMsg.spTotFuncSlsAmt.setValue(roundHalfUp(multiply(shpgPlnMsg.spFuncUnitPrcAmt.getValue(), setOrdQty)));
        shpgPlnMsg.spTotFuncNetAmt.setValue(roundHalfUp(multiply(shpgPlnMsg.spFuncNetUnitPrcAmt.getValue(), setOrdQty)));
    }

    /**
     * updatePrcDtlFromshpgPlnNum
     * @param glblCmpyCd String
     * @param shpgPlnNum String
     * @param qty BigDecimal
     */
    private PRC_DTLTMsgArray updatePrcDtlFromshpgPlnNum(final String glblCmpyCd, final String shpgPlnNum, final BigDecimal qty) {

        PRC_DTLTMsgArray updPrcDtlData = searchPrcDtlForUpdate(glblCmpyCd, shpgPlnNum);

        if (updPrcDtlData.length() == 0) {

            return null;
        }

        for (int i = 0; i < updPrcDtlData.length(); i++) {

            BigDecimal updShipUnitQty = BigDecimal.ZERO;

            updShipUnitQty = add(updPrcDtlData.no(i).shipUnitQty.getValue(), qty);
            updPrcDtlData.no(i).shipUnitQty.setValue(updShipUnitQty);
            updPrcDtlData.no(i).dealNetAmt.setValue(roundHalfUp(multiply(updPrcDtlData.no(i).dealLastNetUnitPrcAmt.getValue(), updPrcDtlData.no(i).shipUnitQty.getValue())));
            updPrcDtlData.no(i).funcNetAmt.setValue(roundHalfUp(multiply(updPrcDtlData.no(i).funcLastNetUnitPrcAmt.getValue(), updPrcDtlData.no(i).shipUnitQty.getValue())));

            if (ZYPCommonFunc.hasValue(updPrcDtlData.no(i).dealPerUnitFixAmt)) {

                updPrcDtlData.no(i).dealDiscAmt.setValue(roundHalfUp(multiply(updPrcDtlData.no(i).dealPerUnitFixAmt.getValue(), updPrcDtlData.no(i).shipUnitQty.getValue())));
            }

            if (ZYPCommonFunc.hasValue(updPrcDtlData.no(i).funcPerUnitFixAmt)) {

                updPrcDtlData.no(i).funcDiscAmt.setValue(roundHalfUp(multiply(updPrcDtlData.no(i).funcPerUnitFixAmt.getValue(), updPrcDtlData.no(i).shipUnitQty.getValue())));
            }

            if (updShipUnitQty.compareTo(BigDecimal.ZERO) == 0) {

                // remove
                EZDTBLAccessor.logicalRemove(updPrcDtlData.no(i));

            } else {

                EZDTBLAccessor.update(updPrcDtlData.no(i));

            }
        }

        return updPrcDtlData;
    }

    /**
     * updateHldFromshpgPlnNum
     * @param glblCmpyCd String
     * @param shpgPlnNum String
     * @param cpoOrdNum String
     * @param cpoLineMum String
     * @param cpoLineSubMum String
     * @param qty BigDecimal
     */
    private HLDTMsgArray updateHldFromshpgPlnNum(final String glblCmpyCd, final String shpgPlnNum, final String cpoOrdNum, final String cpoLineMum, final String cpoLineSubMum, final BigDecimal qty) {

        HLDTMsgArray updHld = this.searchHldForUpdate(glblCmpyCd, shpgPlnNum, cpoOrdNum, cpoLineMum, cpoLineSubMum);

        if (updHld.length() == 0) {

            return updHld;

        } else {

            int updHldSize = updHld.length();

            for (int i = 0; i < updHldSize; i++) {

                BigDecimal hldQty = BigDecimal.ZERO;
                hldQty = add(updHld.no(i).hldQty.getValue(), qty);
                updHld.no(i).hldQty.setValue(hldQty);

                if (hldQty.compareTo(BigDecimal.ZERO) == 0) {

                    // remove
                    EZDTBLAccessor.logicalRemove(updHld.no(i));

                } else {

                    EZDTBLAccessor.update(updHld.no(i));
                }
            }
        }

        return updHld;
    }

    /**
     * insertHldFromshpgPlnNum
     * @param hldData HLDTMsgArray
     * @param glblCmpyCd String
     * @param shpgPlnNum String
     * @param qty BigDecimal
     */
    private void insertHldFromshpgPlnNum(HLDTMsgArray hldData, final String glblCmpyCd, final String shpgPlnNum, final BigDecimal qty) {

        for (int i = 0; i < hldData.length(); i++) {

            hldData.no(i).glblCmpyCd.setValue(glblCmpyCd);
            hldData.no(i).hldPk.setValue(getHldSQ());
            hldData.no(i).shpgPlnNum.setValue(shpgPlnNum);
            hldData.no(i).hldQty.setValue(qty);

            EZDTBLAccessor.insert(hldData.no(i));
        }
    }

    /**
     * insertPrcDtlFromshpgPlnNum
     * @param prcDtlData PRC_DTLTMsgArray
     * @param glblCmpyCd String
     * @param shpgPlnNum String
     * @param updQty BigDecimal
     */
    private void insertPrcDtlFromshpgPlnNum(PRC_DTLTMsgArray prcDtlData, final String glblCmpyCd, final String shpgPlnNum, final BigDecimal updQty) {

        for (int i = 0; i < prcDtlData.length(); i++) {

            prcDtlData.no(i).glblCmpyCd.setValue(glblCmpyCd);
            prcDtlData.no(i).prcDtlPk.setValue(getPrcDtlPk());
            prcDtlData.no(i).shpgPlnNum.setValue(shpgPlnNum);
            prcDtlData.no(i).shipUnitQty.setValue(updQty);
            prcDtlData.no(i).dealNetAmt.setValue(multiply(prcDtlData.no(i).dealLastNetUnitPrcAmt.getValue(), updQty));
            prcDtlData.no(i).funcNetAmt.setValue(multiply(prcDtlData.no(i).funcLastNetUnitPrcAmt.getValue(), updQty));

            if (ZYPCommonFunc.hasValue(prcDtlData.no(i).dealPerUnitFixAmt)) {

                prcDtlData.no(i).dealDiscAmt.setValue(multiply(prcDtlData.no(i).dealPerUnitFixAmt.getValue(), updQty));
            }

            if (ZYPCommonFunc.hasValue(prcDtlData.no(i).funcPerUnitFixAmt)) {
                prcDtlData.no(i).funcDiscAmt.setValue(multiply(prcDtlData.no(i).funcPerUnitFixAmt.getValue(), updQty));

            }

            EZDTBLAccessor.insert(prcDtlData.no(i));
        }
    }

    /**
     * checkPickSvcConfigForNonSerial
     * @param cMsg NLBL3080CMsg
     * @param sMsgBLine NLBL3080_BSMsg
     * @return boolean
     */
    private boolean checkPickSvcConfigForNonSerial(NLBL3080CMsg cMsg, NLBL3080_BSMsg sMsgBLine) {

        if (!ZYPCommonFunc.hasValue(sMsgBLine.serNum_B1)) {

            List<Map<String, Object>> svcMachMstrList = new ArrayList<Map<String, Object>>();
            S21SsmEZDResult ssmResult = NLBL3080Query.getInstance().getSvcMachMstrList(cMsg.glblCmpyCd.getValue(), sMsgBLine);

            if (ssmResult.isCodeNormal()) {

                  // START 2017/08/15 Hisashi QC#20622 Del.
//                svcMachMstrList = (List<Map<String, Object>>) ssmResult.getResultObject();
//                if (ZYPCommonFunc.hasValue( (String) svcMachMstrList.get(0).get("SVC_MACH_MSTR_PK"))) {

                return true;

//                } else {
//
//                	sMsgBLine.mdseCd_B1.setErrorInfo(1, NLBL3080Constant.NACM0008E);
//                    return false;
//
//                }
                // END 2017/08/15 Hisashi QC#20622 Del.

            } else {

            	sMsgBLine.mdseCd_B1.setErrorInfo(1, NLBL3080Constant.NACM0008E);
                return false;

            }
    	}

    	return true;
    }

    /**
     * Add QC#25456 Check Hld
     * @param cMsg NLBL3080CMsg
     * @param sMsgBLine NLBL3080_BSMsg
     * @return boolean
     */
    private boolean checkHld(NLBL3080CMsg cMsg, NLBL3080_BSMsg sMsgBLine) {

        S21SsmEZDResult ssmResult = NLBL3080Query.getInstance().countHld(cMsg.glblCmpyCd.getValue(), sMsgBLine);

        if (ssmResult.isCodeNormal()) {
            BigDecimal cnt = (BigDecimal) ssmResult.getResultObject();
            if (BigDecimal.ZERO.compareTo(cnt) == 0) {
                return true;
            } else {
                sMsgBLine.xxChkBox_B2.setErrorInfo(1, "NWEM0089E");
                return false;
            }
        }

        sMsgBLine.xxChkBox_B2.setErrorInfo(1, "NWEM0089E");
        return false;
    }

    /**
     * Add QC#30903
     * Call Mach Master Update API for Allocation Off
     * @param cMsg NLBL3080CMsg
     * @param sMsgBLine NLBL3080_BSMsg
     * @return ArrayList<String>
     */
    private ArrayList<String> callMachMstrAPIForAllocOff(NLBL3080CMsg cMsg, NLBL3080_BSMsg sMsgBLine) {

        ArrayList<String> msgIdList = new ArrayList<String>();
        S21SsmEZDResult ssmResult = NLBL3080Query.getInstance().getMachMstrAllocOffList(cMsg.glblCmpyCd.getValue(), sMsgBLine);

        if (ssmResult.isCodeNormal()) {

            for (BigDecimal machMstrAllocOffPk : (List<BigDecimal>) ssmResult.getResultObject()) {

                NSZC001001PMsg pMsg = new NSZC001001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, machMstrAllocOffPk);

                NSZC001001 api = new NSZC001001();
                api.execute(pMsg, ONBATCH_TYPE.ONLINE);

                for (String xxMsgId : S21ApiUtil.getXxMsgIdList(pMsg)) {

                    msgIdList.add(xxMsgId);
                }
            }
        }

        return msgIdList;
    }
}
