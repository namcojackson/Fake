/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL2020;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLAL2020.common.NLAL2020CommonLogic;
import business.blap.NLAL2020.constant.NLAL2020Constant;
import business.db.SCE_ORD_TPTMsg;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NLAL2020BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         Y.Taoka         Create          N/A
 * 05/23/2016   CSAI            Y.Imazu         Update          QC#8530
 * 06/02/2016   CSAI            Y.Imazu         Update          QC#8663
 * 06/03/2016   CSAI            Y.Imazu         Update          QC#7977
 * 06/03/2016   CSAI            Y.Imazu         Update          QC#7981
 * 10/17/2016   CITS            K.Ogino         Update          QC#10406
 * 03/09/2017   CITS            M.Naito         Update          QC#17452
 * 04/13/2017   CITS            T.Tokutomi      Update          Merge DS
 * 2017/06/13   CITS            S.Endo          Update          QC#19049
 * 06/13/2017   CITS            Y.Imazu         Update          QC#19058
 * 06/14/2017   CITS            S.Endo          Update          QC#19049
 * 02/19/2019   CITS            M.Naito         Update          QC#30354
 * 04/03/2019   Fujitsu         T.Ogura         Update          QC#31000
 * 2022/10/18   Hitachi         T.Doi           Update          QC#60606
 * 08/31/2023   Hitachi         TZ.Win          Update          QC#61792
 *</pre>
 */
public class NLAL2020BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NLAL2020CMsg bizMsg = (NLAL2020CMsg) cMsg;
            NLAL2020SMsg glblMsg = (NLAL2020SMsg) sMsg;

            if ("NLAL2020_INIT".equals(screenAplID)) {
                doProcess_NLAL2020_INIT(bizMsg, glblMsg);
                ZYPGUITableColumn.getColData(bizMsg, glblMsg, "AHEAD");

            } else if ("NLAL2020Scrn00_CMN_ColClear".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_CMN_ColClear(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_CMN_ColSave".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_CMN_ColSave(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_OnChangeSavedSearchOption(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_OnChange_ChkBoxRMALine".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_OnChange_ChkBoxRMALine(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_OnChange_ChkBoxRMANum".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_OnChange_ChkBoxRMANum(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_OnChange_RecvQty".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_OnChange_RecvQty(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_Open_MtrEnt".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_Open_MtrEnt(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_RWS_Close".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_RWS_Close(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_Receive".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_Receive(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_SaveSearch(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_Search".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_Search(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_Search_PartyInfo".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_Search_PartyInfo(bizMsg);

            } else if ("NLAL2020Scrn00_Search_RtlWHInfo".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_Search_RtlWHInfo(bizMsg);

            } else if ("NLAL2020Scrn00_Search_RcvRtlWHInfo".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_Search_RcvRtlWHInfo(bizMsg);

            } else if ("NLAL2020Scrn00_Search_RcvRtlWh".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_Search_RcvRtlWh(bizMsg);

            } else if ("NLAL2020Scrn00_Select_All".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_Select_All(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_UnSelect_All".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_UnSelect_All(bizMsg, glblMsg);

            } else if ("NLAL2020_NLBL3000".equals(screenAplID)) {
                doProcess_NLAL2020_NLBL3000(bizMsg, glblMsg);

            } else if ("NLAL2020_NSAL0150".equals(screenAplID)) {
                doProcess_NLAL2020_NSAL0150(bizMsg, glblMsg);

            } else if ("NLAL2020_NPAL1010".equals(screenAplID)) {
                doProcess_NLAL2020_NPAL1010(bizMsg, glblMsg);

            } else if ("NLAL2020_NSAL1240".equals(screenAplID)) {
                doProcess_NLAL2020_NSAL1240(bizMsg, glblMsg);

            } else if ("NLAL2020_NWAL1130".equals(screenAplID)) {
                doProcess_NLAL2020_NWAL1130(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_OpenWin_PartyCode".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_OpenWin_PartyCode(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_OpenWin_SerEntry".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_OpenWin_SerEntry(bizMsg, glblMsg);

            } else {
                return;
            }

            // START 2022/10/18 T.Doi [QC#60606, ADD]
            if (!"NLAL2020Scrn00_Receive".equals(screenAplID) && !"NLAL2020Scrn00_PageNext".equals(screenAplID) && !"NLAL2020Scrn00_PagePrev".equals(screenAplID) && !"NLAL2020Scrn00_PageJump".equals(screenAplID)) {
                bizMsg.xxWrnSkipFlg.clear();
            }
            // END 2022/10/18 T.Doi [QC#60606, ADD]
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020_INIT(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        // Clear Table
        clearAll(bizMsg, glblMsg);

        // Set Common Value
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(bizMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(bizMsg.usrId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(bizMsg.dupErrCd, ZYPCodeDataUtil.getVarCharConstValue("SER_DUP_CHK_CD", bizMsg.glblCmpyCd.getValue()));

        // Setup select box
        NLAL2020CommonLogic.createSavedSearchOptionsPullDown(bizMsg);
        ZYPCodeDataUtil.createPulldownList(RWS_STS.class, bizMsg.rwsStsCd_P, bizMsg.rwsStsDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(STK_STS.class, bizMsg.stkStsCd_P, bizMsg.stkStsDescTxt_P);
        createPulldownListSceOrdTp(bizMsg);

        boolean doSearch = false;

        if (ZYPCommonFunc.hasValue(bizMsg.rwsRefNum_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rwsRefNum_H, bizMsg.rwsRefNum_BK);
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.rwsNum_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rwsNum_H, bizMsg.rwsNum_BK);
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.bolNum_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.bolNum_H, bizMsg.bolNum_BK);
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.srcOrdNum_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.srcOrdNum_H, bizMsg.srcOrdNum_BK);
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.svcConfigMstrPk_H, bizMsg.svcConfigMstrPk_BK);
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.serNum_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.serNum_H, bizMsg.serNum_BK);
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.shipToRtlWhCd_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToRtlWhCd_H, bizMsg.shipToRtlWhCd_BK);
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.rtlWhCd_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_H, bizMsg.rtlWhCd_BK);
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.shipLocCd_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipLocCd_H, bizMsg.shipLocCd_BK);
            doSearch = true;
        }

        if (doSearch) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_CL, ZYPConstant.FLG_ON_Y);
            search(bizMsg, glblMsg);
        }
    }

    /**
     * CMN_ColClear Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_CMN_ColClear(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {
        return;
    }

    /**
     * CMN_ColSave Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_CMN_ColSave(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {
        return;
    }

    /**
     * CMN_ColSave Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_CMN_Clear(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        // Clear Table
        clearAll(bizMsg, glblMsg);

        // Set Common Value
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(bizMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(bizMsg.usrId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(bizMsg.dupErrCd, ZYPCodeDataUtil.getVarCharConstValue("SER_DUP_CHK_CD", bizMsg.glblCmpyCd.getValue()));

        // Setup select box
        NLAL2020CommonLogic.createSavedSearchOptionsPullDown(bizMsg);
        ZYPCodeDataUtil.createPulldownList(RWS_STS.class, bizMsg.rwsStsCd_P, bizMsg.rwsStsDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(STK_STS.class, bizMsg.stkStsCd_P, bizMsg.stkStsDescTxt_P);
        createPulldownListSceOrdTp(bizMsg);
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_CMN_Reset(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        doProcess_NLAL2020_INIT(bizMsg, glblMsg);
    }

    /**
     * DeleteSearch Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_DeleteSearch(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {
        return;
    }

    /**
     * OnChangeSavedSearchOption Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_OnChangeSavedSearchOption(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {

            NLAL2020CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg);
        }
    }

    /**
     * OnChange_ChkBoxRMALine Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_OnChange_ChkBoxRMALine(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        int eventLine = bizMsg.xxNum.getValueInt();
        String val = bizMsg.A.no(eventLine).xxChkBox_A2.getValue();
        int sLineNum = (bizMsg.xxPageShowFromNum.getValueInt() - 1) + eventLine;
        int rwsHdrLineNum = sLineNum;

        // ON --> OFF
        String preRwsNum = glblMsg.A.no(sLineNum).rwsNum_A1.getValue();

        if (!ZYPConstant.FLG_ON_Y.equals(val)) {

            // sMsg BackLine
            for (int i = sLineNum; i >= 0; i--) {

                NLAL2020_ASMsg sMsgALine = glblMsg.A.no(i);

                if (i == sLineNum) {

                    glblMsg.A.no(i).xxChkBox_A2.clear();
                }

                if (preRwsNum.equals(sMsgALine.rwsNum_A1.getValue())) {

                    glblMsg.A.no(i).xxChkBox_A1.clear();
                    rwsHdrLineNum = i;

                } else {

                    rwsHdrLineNum = i + 1;
                    break;
                }
            }

            // Ahead Line
            preRwsNum = glblMsg.A.no(sLineNum).rwsNum_A1.getValue();

            for (int i = sLineNum; i < glblMsg.A.getValidCount(); i++) {

                NLAL2020_ASMsg sMsgALine = glblMsg.A.no(i);

                if (preRwsNum.equals(sMsgALine.rwsNum_A1.getValue())) {

                    sMsgALine.xxChkBox_A1.clear();

                } else {

                    break;
                }
            }

            // cMsg
            if (rwsHdrLineNum - (bizMsg.xxPageShowFromNum.getValueInt() - 1) >= 0) {

                int cMsgHdrRcrd = rwsHdrLineNum - (bizMsg.xxPageShowFromNum.getValueInt() - 1);
                bizMsg.A.no(cMsgHdrRcrd).xxChkBox_A1.clear();
            }

            return;
        }

        // OFF --> ON
        int offCount = 0;
        rwsHdrLineNum = sLineNum;

        if (ZYPConstant.FLG_ON_Y.equals(val)) {

            // Back Line
            preRwsNum = glblMsg.A.no(sLineNum).rwsNum_A1.getValue();

            for (int i = sLineNum; i >= 0; i--) {

                NLAL2020_ASMsg sMsgALine = glblMsg.A.no(i);

                if (i == sLineNum) {

                    ZYPEZDItemValueSetter.setValue(sMsgALine.xxChkBox_A2, ZYPConstant.FLG_ON_Y);
                    continue;
                }

                if (preRwsNum.equals(sMsgALine.rwsNum_A1.getValue())) {

                    // Off
                    if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxChkBox_A2.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsgALine.rwsOpenFlg_A1.getValue())) {

                        offCount++;
                    }

                    rwsHdrLineNum = i;

                } else {

                    rwsHdrLineNum = i + 1;
                    break;
                }
            }

            // Ahead Line
            preRwsNum = glblMsg.A.no(sLineNum).rwsNum_A1.getValue();

            for (int i = sLineNum; i < bizMsg.A.getValidCount(); i++) {

                NLAL2020_ASMsg sMsgALine = glblMsg.A.no(i);

                if (i == sLineNum) {

                    ZYPEZDItemValueSetter.setValue(sMsgALine.xxChkBox_A2, ZYPConstant.FLG_ON_Y);
                    continue;
                }

                if (preRwsNum.equals(sMsgALine.rwsNum_A1.getValue())) {

                    // Off
                    if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxChkBox_A2.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsgALine.rwsOpenFlg_A1.getValue())) {

                        offCount++;
                    }

                } else {

                    break;
                }
            }

            if (offCount == 0) {

                for (int i = rwsHdrLineNum; i < glblMsg.A.getValidCount(); i++) {

                    NLAL2020_ASMsg sMsgALine = glblMsg.A.no(i);

                    if (preRwsNum.equals(sMsgALine.rwsNum_A1.getValue())) {

                        ZYPEZDItemValueSetter.setValue(sMsgALine.xxChkBox_A1, ZYPConstant.FLG_ON_Y);

                    } else {

                        break;
                    }
                }
            }

            // cMsg
            if (offCount == 0) {

                if (rwsHdrLineNum - (bizMsg.xxPageShowFromNum.getValueInt() - 1) >= 0) {

                    int cMsgHdrRcrd = rwsHdrLineNum - (bizMsg.xxPageShowFromNum.getValueInt() - 1);
                    bizMsg.A.no(cMsgHdrRcrd).xxChkBox_A1.setValue(ZYPConstant.FLG_ON_Y);
                }
            }

            return;
        }
    }

    /**
     * OnChange_ChkBoxRMANum Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_OnChange_ChkBoxRMANum(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        int eventLine = bizMsg.xxNum.getValueInt();
        String val = "";
        String rwsNum = "";

        for (int i = eventLine; i < bizMsg.A.getValidCount(); i++) {

            NLAL2020_ACMsg cMsgALine = bizMsg.A.no(i);

            if (eventLine == i) {

                val = cMsgALine.xxChkBox_A1.getValue();
                rwsNum = cMsgALine.rwsNum_A1.getValue();
                ZYPEZDItemValueSetter.setValue(cMsgALine.xxChkBox_A2, val);

                // START 2019/02/19 M.Naito [QC#30354,MOD]
//                if (!ZYPConstant.FLG_ON_Y.equals(cMsgALine.rwsOpenFlg_A1.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(cMsgALine.openFlg_A1.getValue())) {
                // END 2019/02/19 M.Naito [QC#30354,MOD]

                    cMsgALine.xxChkBox_A2.clear();
                }

                continue;
            }

            if (rwsNum.equals(cMsgALine.rwsNum_A1.getValue())) {

                ZYPEZDItemValueSetter.setValue(cMsgALine.xxChkBox_A2, val);

                // START 2019/02/19 M.Naito [QC#30354,MOD]
//                if (!ZYPConstant.FLG_ON_Y.equals(cMsgALine.rwsOpenFlg_A1.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(cMsgALine.openFlg_A1.getValue())) {
                // END 2019/02/19 M.Naito [QC#30354,MOD]

                    cMsgALine.xxChkBox_A2.clear();
                }

            } else {

                break;
            }
        }

        int pageNum = (bizMsg.xxPageShowFromNum.getValueInt() - 1) + eventLine;

        for (int i = pageNum; i < glblMsg.A.getValidCount(); i++) {

            NLAL2020_ASMsg sMsgALine = glblMsg.A.no(i);

            if (rwsNum.equals(sMsgALine.rwsNum_A1.getValue())) {

                ZYPEZDItemValueSetter.setValue(sMsgALine.xxChkBox_A1, val);
                ZYPEZDItemValueSetter.setValue(sMsgALine.xxChkBox_A2, val);

                if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.rwsOpenFlg_A1.getValue())) {

                    sMsgALine.xxChkBox_A2.clear();
                }

            } else {

                break;
            }
        }
    }

    /**
     * OnChange_RecvQty Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_OnChange_RecvQty(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        int eventLine = bizMsg.xxNum.getValueInt();
        NLAL2020_ACMsg bizMsgLine = bizMsg.A.no(eventLine);

        if (ZYPCommonFunc.hasValue(bizMsgLine.poBalQty_A1) && 1 < bizMsgLine.poBalQty_A1.getValueInt()) {

            if (ZYPCommonFunc.hasValue(bizMsgLine.serNum_A1) && ZYPConstant.FLG_OFF_N.equals(bizMsgLine.xxSerNumSrchTxt_A1.getValue())) {

                boolean found = false;

                for (int i = 0; i < glblMsg.S.getValidCount(); i++) {

                    NLAL2020_SSMsg serLine = glblMsg.S.no(i);

                    if (bizMsgLine.rwsNum_A1.getValue().equals(serLine.rwsNum_S1.getValue()) && bizMsgLine.rwsDtlLineNum_A1.getValue().equals(serLine.rwsLineNum_S1.getValue())
                            && bizMsgLine.serNum_A1.getValue().equals(serLine.serNum_S1.getValue())) {

                        // Exist
                        found = true;
                        break;
                    }
                }

                if (!found) {

                    NLAL2020_SSMsg serLine = glblMsg.S.no(glblMsg.S.getValidCount());
                    ZYPEZDItemValueSetter.setValue(serLine.rwsNum_S1, bizMsgLine.rwsNum_A1);
                    ZYPEZDItemValueSetter.setValue(serLine.rwsLineNum_S1, bizMsgLine.rwsDtlLineNum_A1);
                    ZYPEZDItemValueSetter.setValue(serLine.invtyStkStsCd_S1, bizMsgLine.invtyStkStsCd_A1);
                    ZYPEZDItemValueSetter.setValue(serLine.mdseCd_S1, bizMsgLine.mdseCd_A1);
                    ZYPEZDItemValueSetter.setValue(serLine.serNum_S1, bizMsgLine.serNum_A1.getValue());
                    glblMsg.S.setValidCount(glblMsg.S.getValidCount() + 1);
                }

                bizMsgLine.serNum_A1.clear();

            } else if (ZYPCommonFunc.hasValue(bizMsgLine.serNum_A1) && ZYPConstant.FLG_ON_Y.equals(bizMsgLine.xxSerNumSrchTxt_A1.getValue())) {

                String[] serList = bizMsgLine.serNum_A1.getValue().split(",");

                int maxLength = bizMsgLine.getAttr("serNum_A1").getDigit();
                StringBuilder sb = new StringBuilder();

                for (int c = 0; c < serList.length; c++) {

                    if (c == bizMsgLine.poBalQty_A1.getValueInt()) {
                        break;
                    }

                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(serList[c]);

                    if (sb.length() <= maxLength) {
                        ZYPEZDItemValueSetter.setValue(bizMsgLine.serNum_A1, sb.toString());
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsgLine.serNum_A1, sb.toString().substring(0, maxLength));
                        break;
                    }
                }
            }

        } else {

            List<Integer> serLineList = new ArrayList<Integer>();

            for (int i = 0; i < glblMsg.S.getValidCount(); i++) {

                NLAL2020_SSMsg serLine = glblMsg.S.no(i);

                if (bizMsgLine.rwsNum_A1.getValue().equals(serLine.rwsNum_S1.getValue()) && bizMsgLine.rwsDtlLineNum_A1.getValue().equals(serLine.rwsLineNum_S1.getValue())) {

                    serLineList.add(i);
                }
            }

            // 1 == Qty
            if (ZYPCommonFunc.hasValue(bizMsgLine.poBalQty_A1) && 1 == bizMsgLine.poBalQty_A1.getValueInt()) {

                if (ZYPConstant.FLG_ON_Y.equals(bizMsgLine.xxSerNumSrchTxt_A1.getValue()) || !ZYPCommonFunc.hasValue(bizMsgLine.serNum_A1) && !serLineList.isEmpty()) {

                    if (!serLineList.isEmpty()) {
                        ZYPEZDItemValueSetter.setValue(bizMsgLine.serNum_A1, glblMsg.S.no(serLineList.get(0)).serNum_S1.getValue());
                    } else if (ZYPCommonFunc.hasValue(bizMsgLine.serNum_A1)) {

                        ZYPEZDItemValueSetter.setValue(bizMsgLine.serNum_A1, bizMsgLine.serNum_A1.getValue().split(",")[0]);
                    }
                }

                ZYPEZDItemValueSetter.setValue(bizMsgLine.xxSerNumSrchTxt_A1, ZYPConstant.FLG_OFF_N);

            } else {

                bizMsgLine.serNum_A1.clear();
            }

            ZYPTableUtil.deleteRows(glblMsg.S, serLineList);
        }
    }

    /**
     * Open_MtrEnt Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_Open_MtrEnt(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        // Get Serial#
        int eventLine = bizMsg.xxNum.getValueInt();
        NLAL2020_ACMsg bizMsgLine = bizMsg.A.no(eventLine);

        ArrayList<String> serNumList = new ArrayList<String>();

        if (ZYPCommonFunc.hasValue(bizMsgLine.serNum_A1) && ZYPConstant.FLG_OFF_N.equals(bizMsgLine.xxSerNumSrchTxt_A1.getValue())) {

            serNumList.add(bizMsgLine.serNum_A1.getValue());

        } else {

            S21SsmEZDResult ssmResult = NLAL2020Query.getInstance().getSerNumList(bizMsg, bizMsgLine);

            if (ssmResult.isCodeNormal()) {

                List<Map<String, Object>> serNumRsList = (List<Map<String, Object>>) ssmResult.getResultObject();

                for (int i = 0; i < serNumRsList.size(); i++) {

                    serNumList.add((String) serNumRsList.get(i).get("SER_NUM"));
                }
            }
        }

        if (0 < serNumList.size()) {

            S21SsmEZDResult ssmResult = NLAL2020Query.getInstance().getMeterEntry(bizMsg, serNumList, bizMsgLine.mdseCd_A1.getValue());

            if (ssmResult.isCodeNormal() && 1 < ssmResult.getQueryResultCount()) {

                // Error
                bizMsgLine.xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLAM1318E);

            } else if (ssmResult.isCodeNormal() && 1 == ssmResult.getQueryResultCount()) {

                List<Map<String, Object>> serNumRsList = (List<Map<String, Object>>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.P.no(0).xxTrxRefPk, (BigDecimal) serNumRsList.get(0).get("SVC_MACH_MSTR_PK"));
                bizMsg.P.no(0).xxPopPrm.clear();
                bizMsg.P.setValidCount(1);
            } else {
                bizMsgLine.xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLAM1317E);
            }

        } else {

            bizMsgLine.xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLAM1317E);
        }
    }

    /**
     * PageJump Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_PageJump(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        NLAL2020CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PageNext Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_PageNext(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        NLAL2020CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * PagePrev Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_PagePrev(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        NLAL2020CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * RWS_Close Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_RWS_Close(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {

            searchAfterRWSClose(bizMsg, glblMsg);

            // clear MessageInfo
            // QC#17452
            bizMsg.setMessageInfo(null);
            bizMsg.setMessageInfo(NLAL2020Constant.ZZZM9003I, new String[] {"RWS Close" });
        }
    }

    /**
     * Receive Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_Receive(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {

            searchAfterRWSClose(bizMsg, glblMsg);
            // QC#17452
            bizMsg.setMessageInfo(null);
            bizMsg.setMessageInfo(NLAL2020Constant.ZZZM9003I, new String[] {"Receive" });
        }
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_SaveSearch(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {
        return;
    }

    /**
     * Search Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_Search(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        boolean errorExists = inputCheckSearch(bizMsg);

        // error exists
        if (!errorExists) {

            return;
        }

        // search
        search(bizMsg, glblMsg);
        bizMsg.xxWrnSkipFlg.clear();
    }

    /**
     * Search_PartyInfo Event
     * @param bizMsg NLAL2020CMsg
     */
    private void doProcess_NLAL2020Scrn00_Search_PartyInfo(NLAL2020CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.shipLocCd_H)) {

            NLAL2020CommonLogic.getShipFromLocNm(bizMsg);

        } else {

            bizMsg.shipLocCd_H.setErrorInfo(1, NLAL2020Constant.ZZM9000E, new String[] {"Party" });
            bizMsg.dsAcctNm_H.clear();
        }
    }

    /**
     * Search_RtlWHInfo Event
     * @param bizMsg NLAL2020CMsg
     */
    private void doProcess_NLAL2020Scrn00_Search_RtlWHInfo(NLAL2020CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.shipToRtlWhCd_H)) {

            NLAL2020CommonLogic.getRtlWhNm(bizMsg.glblCmpyCd.getValue(), bizMsg.shipToRtlWhCd_H, bizMsg.rtlWhNm_H, "Warehouse/Tech");

        } else {

            bizMsg.shipToRtlWhCd_H.setErrorInfo(1, NLAL2020Constant.ZZM9000E, new String[] {"Warehouse/Tech" });
            bizMsg.rtlWhNm_H.clear();
        }
    }

    /**
     * Search_RcvRtlWHInfo Event
     * @param bizMsg NLAL2020CMsg
     */
    private void doProcess_NLAL2020Scrn00_Search_RcvRtlWHInfo(NLAL2020CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.rtlWhCd_H)) {

            NLAL2020CommonLogic.getRtlWhNm(bizMsg.glblCmpyCd.getValue(), bizMsg.rtlWhCd_H, bizMsg.rmaSlsWhNm_H, "Received WH");

        } else {

            bizMsg.rtlWhCd_H.setErrorInfo(1, NLAL2020Constant.ZZM9000E, new String[] {"Received WH" });
            bizMsg.rmaSlsWhNm_H.clear();
        }
    }

    /**
     * Search_RcvRtlWh Event
     * @param bizMsg Business bizMsg
     */
    private void doProcess_NLAL2020Scrn00_Search_RcvRtlWh(NLAL2020CMsg bizMsg) {

        int index = bizMsg.xxNum.getValueInt();

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(index).rtlWhCd_A1)) {

            NLAL2020CommonLogic.getRcvByNm(bizMsg.glblCmpyCd.getValue(), bizMsg.A.no(index), null);

        } else {

            bizMsg.A.no(index).rtlWhCd_A1.setErrorInfo(1, NLAL2020Constant.ZZM9000E, new String[] {"Received WH" });
            bizMsg.A.no(index).rmaSlsWhNm_A1.clear();
        }
    }

    /**
     * Select_All Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_Select_All(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        NLAL2020CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NLAL2020_ASMsg glblMsgLine = glblMsg.A.no(i);

            if (ZYPConstant.FLG_ON_Y.equals(glblMsgLine.rwsOpenFlg_A1.getValue())) {

                ZYPEZDItemValueSetter.setValue(glblMsgLine.xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);

                if (ZYPConstant.FLG_ON_Y.equals(glblMsgLine.openFlg_A1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(glblMsgLine.xxChkBox_A2, ZYPConstant.CHKBOX_ON_Y);

                } else {

                    glblMsgLine.xxChkBox_A2.clear();
                }

            } else {

                glblMsgLine.xxChkBox_A1.clear();
                glblMsgLine.xxChkBox_A2.clear();
            }
        }

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValue());
        NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_TBLColumnSort(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            NLAL2020CommonLogic.updateGlblMsg(bizMsg, glblMsg);

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            // update number
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                NLAL2020_ASMsg glblMsgLine = glblMsg.A.no(i);
                ZYPEZDItemValueSetter.setValue(glblMsgLine.xxNum_A1, new BigDecimal(i));
            }

            // set pagination.
            bizMsg.xxPageShowFromNum.setValue(1);
            NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

    /**
     * UnSelect_All Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_UnSelect_All(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        NLAL2020CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NLAL2020_ASMsg glblMsgLine = glblMsg.A.no(i);

            glblMsgLine.xxChkBox_A1.clear();
            glblMsgLine.xxChkBox_A2.clear();
        }

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValue());
        NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * NLBL3000 Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020_NLBL3000(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        int eventLine = bizMsg.xxNum.getValueInt();
        NLAL2020_ACMsg bizMsgLine = bizMsg.A.no(eventLine);

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsgLine.openFlg_A1.getValue())) {

            return;
        }

        // Delete
        List<Integer> delRows = new ArrayList<Integer>();

        for (int i = 0; i < glblMsg.S.getValidCount(); i++) {

            NLAL2020_SSMsg serLine = glblMsg.S.no(i);

            if (bizMsgLine.rwsNum_A1.getValue().equals(serLine.rwsNum_S1.getValue()) && bizMsgLine.rwsDtlLineNum_A1.getValue().equals(serLine.rwsLineNum_S1.getValue())
                    && bizMsgLine.invtyStkStsCd_A1.getValue().equals(serLine.invtyStkStsCd_S1.getValue())) {

                delRows.add(i);
            }
        }

        ZYPTableUtil.deleteRows(glblMsg.S, delRows);

        int maxLength = bizMsg.A.no(eventLine).getAttr("serNum_A1").getDigit();
        StringBuilder sb = new StringBuilder();
        boolean setSerVal = false;
        // Insert
        for (int i = 0; i < bizMsg.S.getValidCount(); i++) {

            NLAL2020_SCMsg popLine = bizMsg.S.no(i);

            NLAL2020_SSMsg serLine = glblMsg.S.no(glblMsg.S.getValidCount());
            ZYPEZDItemValueSetter.setValue(serLine.rwsNum_S1, popLine.xxHdrNum_SN);
            ZYPEZDItemValueSetter.setValue(serLine.rwsLineNum_S1, popLine.xxDplyTrxNumTxt_SN);
            ZYPEZDItemValueSetter.setValue(serLine.mdseCd_S1, popLine.mdseCd_SN);
            ZYPEZDItemValueSetter.setValue(serLine.serNum_S1, popLine.serNum_SN);
            ZYPEZDItemValueSetter.setValue(serLine.invtyStkStsCd_S1, bizMsgLine.invtyStkStsCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(serLine.xxTrxRefPk_S1, popLine.xxTrxRefPk_SN);
            if (SCE_ORD_TP.RETURN.equals(bizMsgLine.sceOrdTpCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(serLine.xxEdtModeFlg_SN, popLine.xxEdtModeFlg_SN);
            }
            glblMsg.S.setValidCount(glblMsg.S.getValidCount() + 1);

            if (ZYPCommonFunc.hasValue(serLine.serNum_S1)) {

                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(serLine.serNum_S1.getValue());

                if (sb.length() <= maxLength) {
                    ZYPEZDItemValueSetter.setValue(bizMsgLine.serNum_A1, sb.toString());
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsgLine.serNum_A1, sb.toString().substring(0, maxLength));
                }

                ZYPEZDItemValueSetter.setValue(bizMsgLine.xxSerNumSrchTxt_A1, ZYPConstant.FLG_ON_Y);
                setSerVal = true;
            } else {
                if (!setSerVal) {
                    bizMsgLine.serNum_A1.clear();
                }
            }
        }
    }

    /**
     * NSAL0150 Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020_NSAL0150(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {
        return;
    }

    /**
     * NPAL1010 Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020_NPAL1010(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {
        return;
    }

    /**
     * NSAL1240 Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020_NSAL1240(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {
        return;
    }

    /**
     * NWAL1130 Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020_NWAL1130(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {
        return;
    }

    /**
     * doProcess_NLAL2020Scrn00_OpenWin_PartyCode
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_OpenWin_PartyCode(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopSqlTxt, NLAL2020CommonLogic.getPartyCdSelectSql(bizMsg, null));
    }

    /**
     * doProcess_NLAL2020Scrn00_OpenWin_SerEntry
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NLAL2020Scrn00_OpenWin_SerEntry(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        int eventLine = bizMsg.xxNum.getValueInt();
        NLAL2020_ACMsg bizMsgLine = bizMsg.A.no(eventLine);
        ZYPTableUtil.clear(bizMsg.S);
        boolean found = false;
        int serCont = 0;
        int serPopLineCnt = 0;

        if (ZYPConstant.FLG_ON_Y.equals(bizMsgLine.openFlg_A1.getValue())) {

            serPopLineCnt = bizMsgLine.poBalQty_A1.getValueInt();

            if (ZYPCommonFunc.hasValue(bizMsgLine.rtlWhCd_A1)) {

                if (!NLAL2020CommonLogic.getRcvByNm(bizMsg.glblCmpyCd.getValue(), bizMsgLine, null)) {

                    return;
                }

            } else {

                bizMsgLine.rtlWhCd_A1.setErrorInfo(1, NLAL2020Constant.ZZM9000E, new String[] {"Received WH" });
                bizMsgLine.rmaSlsWhNm_A1.clear();
                return;
            }

        } else {

            serPopLineCnt = bizMsgLine.rwsStkQty_A1.getValueInt();
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsgLine.openFlg_A1.getValue())) {

            for (int i = 0; i < glblMsg.S.getValidCount(); i++) {

                NLAL2020_SSMsg serLine = glblMsg.S.no(i);

                if (bizMsgLine.rwsNum_A1.getValue().equals(serLine.rwsNum_S1.getValue()) && bizMsgLine.rwsDtlLineNum_A1.getValue().equals(serLine.rwsLineNum_S1.getValue())) {

                    // Found
                    if (serPopLineCnt <= serCont) {

                        break;
                    }

                    NLAL2020_SCMsg popLine = bizMsg.S.no(serCont);
                    ZYPEZDItemValueSetter.setValue(popLine.xxHdrNum_SN, bizMsgLine.rwsNum_A1);
                    ZYPEZDItemValueSetter.setValue(popLine.xxDplyTrxNumTxt_SN, bizMsgLine.rwsDtlLineNum_A1);
                    ZYPEZDItemValueSetter.setValue(popLine.mdseCd_SN, bizMsgLine.mdseCd_A1);
                    ZYPEZDItemValueSetter.setValue(popLine.serNum_SN, serLine.serNum_S1);
                    ZYPEZDItemValueSetter.setValue(popLine.rtlSwhCd_SN, bizMsgLine.rtlSwhCd_A1);
                    ZYPEZDItemValueSetter.setValue(popLine.svcConfigMstrPk_SN, bizMsgLine.svcConfigMstrPk_A1);
                    ZYPEZDItemValueSetter.setValue(popLine.invtyLocCd_SN, bizMsgLine.rcvInvtyLocCd_A1);
                    ZYPEZDItemValueSetter.setValue(popLine.xxTrxRefPk_SN, serLine.xxTrxRefPk_S1);
                    ZYPEZDItemValueSetter.setValue(popLine.serNumTakeFlg_SN, bizMsgLine.serNumTakeFlg_A1);
                    if (SCE_ORD_TP.RETURN.equals(bizMsgLine.sceOrdTpCd_A1.getValue())) {
                        ZYPEZDItemValueSetter.setValue(popLine.xxEdtModeFlg_SN, serLine.xxEdtModeFlg_SN);
                    }
                    serCont++;
                    bizMsg.S.setValidCount(serCont);
                    found = true;
                }
            }
        }

        if (!found) {

            // Not Found, Search in DB
            S21SsmEZDResult ssmResult = NLAL2020Query.getInstance().getSerNumList(bizMsg, bizMsgLine);

            if (ssmResult.isCodeNormal()) {

                List<Map<String, Object>> serNumList = (List<Map<String, Object>>) ssmResult.getResultObject();

                for (int i = 0; i < serNumList.size(); i++) {

                    NLAL2020_SCMsg popLine = bizMsg.S.no(serCont);

                    if (serPopLineCnt <= serCont) {

                        break;
                    }

                    ZYPEZDItemValueSetter.setValue(popLine.xxHdrNum_SN, bizMsgLine.rwsNum_A1);
                    ZYPEZDItemValueSetter.setValue(popLine.xxDplyTrxNumTxt_SN, bizMsgLine.rwsDtlLineNum_A1);
                    ZYPEZDItemValueSetter.setValue(popLine.mdseCd_SN, bizMsgLine.mdseCd_A1);
                    ZYPEZDItemValueSetter.setValue(popLine.serNum_SN, (String) serNumList.get(i).get("SER_NUM"));
                    ZYPEZDItemValueSetter.setValue(popLine.rtlSwhCd_SN, bizMsgLine.rtlSwhCd_A1);
                    ZYPEZDItemValueSetter.setValue(popLine.svcConfigMstrPk_SN, bizMsgLine.svcConfigMstrPk_A1);
                    ZYPEZDItemValueSetter.setValue(popLine.invtyLocCd_SN, bizMsgLine.rcvInvtyLocCd_A1);
                    ZYPEZDItemValueSetter.setValue(popLine.serNumTakeFlg_SN, bizMsgLine.serNumTakeFlg_A1);
                    if (SCE_ORD_TP.RETURN.equals(bizMsgLine.sceOrdTpCd_A1.getValue())) {
                        ZYPEZDItemValueSetter.setValue(popLine.xxEdtModeFlg_SN, ZYPConstant.FLG_OFF_N);
                    }
                    serCont++;
                    bizMsg.S.setValidCount(serCont);
                }

            } else {

                // Not found
                serCont = 0;

                while (serCont < serPopLineCnt) {

                    NLAL2020_SCMsg popLine = bizMsg.S.no(serCont);
                    ZYPEZDItemValueSetter.setValue(popLine.xxHdrNum_SN, bizMsgLine.rwsNum_A1);
                    ZYPEZDItemValueSetter.setValue(popLine.xxDplyTrxNumTxt_SN, bizMsgLine.rwsDtlLineNum_A1);
                    ZYPEZDItemValueSetter.setValue(popLine.mdseCd_SN, bizMsgLine.mdseCd_A1);
                    popLine.serNum_SN.clear();
                    ZYPEZDItemValueSetter.setValue(popLine.rtlSwhCd_SN, bizMsgLine.rtlSwhCd_A1);
                    ZYPEZDItemValueSetter.setValue(popLine.svcConfigMstrPk_SN, bizMsgLine.svcConfigMstrPk_A1);
                    ZYPEZDItemValueSetter.setValue(popLine.invtyLocCd_SN, bizMsgLine.rcvInvtyLocCd_A1);
                    ZYPEZDItemValueSetter.setValue(popLine.serNumTakeFlg_SN, bizMsgLine.serNumTakeFlg_A1);
                    if (SCE_ORD_TP.RETURN.equals(bizMsgLine.sceOrdTpCd_A1.getValue())) {
                        ZYPEZDItemValueSetter.setValue(popLine.xxEdtModeFlg_SN, ZYPConstant.FLG_ON_Y);
                    }
                    serCont++;
                    bizMsg.S.setValidCount(serCont);
                }
            }
        }

        if (serCont < serPopLineCnt) {

            int balQty = serPopLineCnt - serCont;

            while (0 < balQty) {

                NLAL2020_SCMsg popLine = bizMsg.S.no(serCont);
                ZYPEZDItemValueSetter.setValue(popLine.xxHdrNum_SN, bizMsgLine.rwsNum_A1);
                ZYPEZDItemValueSetter.setValue(popLine.xxDplyTrxNumTxt_SN, bizMsgLine.rwsDtlLineNum_A1);
                ZYPEZDItemValueSetter.setValue(popLine.mdseCd_SN, bizMsgLine.mdseCd_A1);
                ZYPEZDItemValueSetter.setValue(popLine.rtlSwhCd_SN, bizMsgLine.rtlSwhCd_A1);
                ZYPEZDItemValueSetter.setValue(popLine.svcConfigMstrPk_SN, bizMsgLine.svcConfigMstrPk_A1);
                ZYPEZDItemValueSetter.setValue(popLine.invtyLocCd_SN, bizMsgLine.rcvInvtyLocCd_A1);
                ZYPEZDItemValueSetter.setValue(popLine.serNumTakeFlg_SN, bizMsgLine.serNumTakeFlg_A1);
                if (SCE_ORD_TP.RETURN.equals(bizMsgLine.sceOrdTpCd_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(popLine.xxEdtModeFlg_SN, ZYPConstant.FLG_ON_Y);
                }
                balQty--;
                serCont++;
            }
        }

        bizMsg.S.setValidCount(serPopLineCnt);
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.S);
        ZYPTableUtil.clear(bizMsg.S);

        if (ZYPCommonFunc.hasValue(bizMsg.rtlWhCd_H.getValue())) {

            if (!ZYPCommonFunc.hasValue(bizMsg.xxChkBox_CL) || !ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_CL.getValue())) {

                bizMsg.xxPageShowFromNum.clear();
                bizMsg.xxPageShowToNum.clear();
                bizMsg.xxPageShowOfNum.clear();
                bizMsg.setMessageInfo(NLAL2020Constant.NZZM0000E);
                return;
            }
        }

        S21SsmEZDResult ssmResult = NLAL2020Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            bizMsg.setMessageInfo(NLAL2020Constant.NZZM0000E);

        } else {

            NLAL2020CommonLogic.setSsearchResultToGlblMsg(ssmResult, glblMsg, bizMsg.glblCmpyCd.getValue());

            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NLAL2020Constant.NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                bizMsg.setMessageInfo(NLAL2020Constant.ZZZM9003I, new String[] {"Search" });
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

    /**
     * searchAfterRWSClose
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void searchAfterRWSClose(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        List<String> rwsNumList = new ArrayList<String>();
        for (int i = 0; i< glblMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A2.getValue())) {
                rwsNumList.add(glblMsg.A.no(i).rwsNum_A1.getValue());
            }
        }
        
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.S);
        ZYPTableUtil.clear(bizMsg.S);

        S21SsmEZDResult ssmResult = NLAL2020Query.getInstance().searchAfterCloseRWS(bizMsg, glblMsg, rwsNumList);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            bizMsg.setMessageInfo(NLAL2020Constant.NZZM0000E);

        } else {

            NLAL2020CommonLogic.setSsearchResultToGlblMsg(ssmResult, glblMsg, bizMsg.glblCmpyCd.getValue());

            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NLAL2020Constant.NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                bizMsg.setMessageInfo(NLAL2020Constant.ZZZM9003I, new String[] {"Search" });
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }
    /**
     * clearAll
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     */
    private void clearAll(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.M);
        ZYPTableUtil.clear(glblMsg.S);
        ZYPTableUtil.clear(bizMsg.S);
        ZYPTableUtil.clear(bizMsg.P);
        glblMsg.clear();

        // Search Option
        bizMsg.srchOptPk_S.clear();
        bizMsg.srchOptNm_S.clear();

        // Header
        bizMsg.rwsRefNum_H.clear();
        bizMsg.rwsNum_H.clear();
        bizMsg.bolNum_H.clear();
        bizMsg.rwsStsCd_H.clear();
        bizMsg.srcOrdNum_H.clear();
        bizMsg.sceOrdTpCd_H.clear();
        bizMsg.svcConfigMstrPk_H.clear();
        bizMsg.serNum_H.clear();
        bizMsg.shipToRtlWhCd_H.clear();
        bizMsg.rtlWhNm_H.clear();
        bizMsg.rtlWhCd_H.clear();
        bizMsg.rmaSlsWhNm_H.clear();
        bizMsg.shipLocCd_H.clear();
        bizMsg.dsAcctNm_H.clear();
        bizMsg.whInEtaDt_FR.clear();
        bizMsg.whInEtaDt_TO.clear();
        bizMsg.xxChkBox_CL.clear();
        // START 2023/08/31 TZ.Win [QC#61792,ADD]
        bizMsg.xxChkBox_WW.clear();
        // END 2023/08/31 TZ.Win [QC#61792,ADD]

        // List Box
        bizMsg.rwsStsCd_P.clear();
        bizMsg.rwsStsDescTxt_P.clear();
        bizMsg.sceOrdTpCd_P.clear();
        bizMsg.sceOrdTpNm_P.clear();
        bizMsg.stkStsCd_P.clear();
        bizMsg.stkStsDescTxt_P.clear();

        bizMsg.xxWrnSkipFlg.clear();
        bizMsg.xxWrnSkipFlg_RC.clear();    // 2019/04/03 T.Ogura [QC#31000,ADD]
    }

    /**
     * createPulldownListSceOrdTp
     * @param bizMsg NLAL2020CMsg
     */
    private void createPulldownListSceOrdTp(NLAL2020CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NLAL2020Query.getInstance().getSceOrdTpList(bizMsg);

        if (ssmResult.isCodeNotFound()) {

            return;
        }

        List<SCE_ORD_TPTMsg> outMsg = (List<SCE_ORD_TPTMsg>) ssmResult.getResultObject();

        for (int i = 0; i < outMsg.size(); i++) {

            if (i >= bizMsg.sceOrdTpCd_P.length()) {

                break;
            }

            SCE_ORD_TPTMsg sceOrdTpTMsg = (SCE_ORD_TPTMsg) outMsg.get(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.sceOrdTpCd_P.no(i), sceOrdTpTMsg.sceOrdTpCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.sceOrdTpNm_P.no(i), sceOrdTpTMsg.sceOrdTpNm);
        }
    }

    /**
     * inputCheckSearch
     * @param bizMsg NLAL2020CMsg
     * @return boolean
     */
    private boolean inputCheckSearch(NLAL2020CMsg bizMsg) {

        // Clear
        bizMsg.rtlWhNm_H.clear();
        bizMsg.rmaSlsWhNm_H.clear();
        bizMsg.dsAcctNm_H.clear();

        // Master check
        boolean checkOk = true;

        if (ZYPCommonFunc.hasValue(bizMsg.shipToRtlWhCd_H) && !NLAL2020CommonLogic.getRtlWhNm(bizMsg.glblCmpyCd.getValue(), bizMsg.shipToRtlWhCd_H, bizMsg.rtlWhNm_H, "Warehouse/Tech")) {

            checkOk = false;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.rtlWhCd_H) && !NLAL2020CommonLogic.getRtlWhNm(bizMsg.glblCmpyCd.getValue(), bizMsg.rtlWhCd_H, bizMsg.rmaSlsWhNm_H, "Received WH")) {

            checkOk = false;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.shipLocCd_H) && !NLAL2020CommonLogic.getShipFromLocNm(bizMsg)) {

            checkOk = false;
        }

        return checkOk;
    }
}
