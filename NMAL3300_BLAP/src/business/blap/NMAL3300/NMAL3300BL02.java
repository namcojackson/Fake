/*
 * <pre>Copyright (c) 2014 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL3300;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NMAL3300.common.NMAL3300CommonLogic;

import static business.blap.NMAL3300.constant.NMAL3300Constant.TABLE_D;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         N.Sugiura       Create          N/A
 * 2016/02/25   Fujitsu         H.Ikeda         Update          QC#2823
 * 2018/07/25   Fujitsu         M.Ishii         Update          QC#26712
 * 2018/11/12   Fujitsu         Hd.Sugawara     Update          QC#28683
 * </pre>
 */

public class NMAL3300BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL3300_INIT".equals(screenAplID)) {
                doProcess_NMAL3300_INIT(cMsg, sMsg);

            } else if ("NMAL3300Scrn00_OnChange_locNum".equals(screenAplID)) {
                    doProcess_NMAL3300Scrn00_OnChange_locNum(cMsg, sMsg);

            } else if ("NMAL3300Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL3300Scrn00_CMN_Clear(cMsg, sMsg);

                // Del Start 2018/11/12 QC#28683
            //} else if ("NMAL3300Scrn00_BtnApply_TrxDriver".equals(screenAplID)) {
            //    doProcess_NMAL3300Scrn00_BtnApply_TrxDriver(cMsg, sMsg);

            //} else if ("NMAL3300Scrn00_BtnApply_CustomerRef".equals(screenAplID)) {
            //    doProcess_NMAL3300Scrn00_BtnApply_CustomerRef(cMsg, sMsg);

            //} else if ("NMAL3300Scrn00_BtnApply_SpecialHandling".equals(screenAplID)) {
            //    doProcess_NMAL3300Scrn00_BtnApply_SpecialHandling(cMsg, sMsg);

            //} else if ("NMAL3300Scrn00_BtnApply_SpecialMessage".equals(screenAplID)) {
            //    doProcess_NMAL3300Scrn00_BtnApply_SpecialMessage(cMsg, sMsg);
                // Del End 2018/11/12 QC#28683

            } else if ("NMAL3300Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL3300Scrn00_PageNext(cMsg, sMsg);

            } else if ("NMAL3300Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL3300Scrn00_PagePrev(cMsg, sMsg);

                // Add Start 2018/11/12 QC#28683
            } else if ("NMAL3300Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL3300Scrn00_TBLColumnSort(cMsg, sMsg);
                // Add End 2018/11/12 QC#28683
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NMAL3300_INIT
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NMAL3300_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        NMAL3300CMsg bizMsg = (NMAL3300CMsg) cMsg;
        NMAL3300SMsg sharedMsg = (NMAL3300SMsg) sMsg;

        // Del Start 2018/11/12 QC#28683
        //ZYPTableUtil.clear(bizMsg.A);
        //ZYPTableUtil.clear(bizMsg.B);
        //ZYPTableUtil.clear(bizMsg.C);
        // Del End 2018/11/12 QC#28683
        ZYPTableUtil.clear(bizMsg.D);
        // Del Start 2018/11/12 QC#28683
        //initCheckbox(bizMsg);
        // Del End 2018/11/12 QC#28683
        createPullDownList(bizMsg);

        // Search
        bizMsg.S.setValidCount(0);
        search(bizMsg, sharedMsg);
    }
    /**
     * doProcess_NMAL3300Scrn00_CMN_Clear
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NMAL3300Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        NMAL3300CMsg bizMsg = (NMAL3300CMsg) cMsg;
        NMAL3300SMsg sharedMsg = (NMAL3300SMsg) sMsg;

        // Search
        bizMsg.locNum_D.setValue(bizMsg.locNum_01.no(0).getValue());
        bizMsg.S.setValidCount(0);
        search(bizMsg, sharedMsg);
    }
    /**
     * doProcess_NMAL3300Scrn00_OnChange_locNum
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NMAL3300Scrn00_OnChange_locNum(EZDCMsg cMsg, EZDSMsg sMsg) {
        NMAL3300CMsg bizMsg = (NMAL3300CMsg) cMsg;
        NMAL3300SMsg sharedMsg = (NMAL3300SMsg) sMsg;

        // Set Search Row
        NMAL3300CommonLogic.setSearchRow(bizMsg);

        // Search
        search(bizMsg, sharedMsg);
    }

    // Del Start 2018/11/12 QC#28683
//    /**
//     * doProcess_NMAL3300Scrn00_BtnApply_TrxDriver
//     * @param cMsg EZDCMsg
//     * @param sMsg EZDSMsg
//     */
//    private void doProcess_NMAL3300Scrn00_BtnApply_TrxDriver(EZDCMsg cMsg, EZDSMsg sMsg) {
//        NMAL3300CMsg bizMsg = (NMAL3300CMsg) cMsg;
//        NMAL3300SMsg sharedMsg = (NMAL3300SMsg) sMsg;
//
//        // Search
//        NMAL3300CommonLogic.getSearchRec(bizMsg, sharedMsg);
//        // 2016/2/25 QC#2823 MOD Start
//        //NMAL3300CommonLogic.getTrxDriverSection(bizMsg, sharedMsg);
//        NMAL3300CommonLogic.getTrxDriverSection(bizMsg, sharedMsg, sharedMsg.R.no(0).getBaseContents());
//        // 2016/2/25 QC#2823 MOD End
//    }
//    /**
//     * doProcess_NMAL3300Scrn00_BtnApply_CustomerRef
//     * @param cMsg EZDCMsg
//     * @param sMsg EZDSMsg
//     */
//    private void doProcess_NMAL3300Scrn00_BtnApply_CustomerRef(EZDCMsg cMsg, EZDSMsg sMsg) {
//        NMAL3300CMsg bizMsg = (NMAL3300CMsg) cMsg;
//        NMAL3300SMsg sharedMsg = (NMAL3300SMsg) sMsg;
//
//        // Search
//        NMAL3300CommonLogic.getSearchRec(bizMsg, sharedMsg);
//        // 2016/2/25 QC#2823 MOD Start
//        //NMAL3300CommonLogic.getCustReferenceSection(bizMsg, sharedMsg);
//        NMAL3300CommonLogic.getCustReferenceSection(bizMsg, sharedMsg, sharedMsg.R.no(0).getBaseContents());
//        // 2016/2/25 QC#2823 MOD End
//    }
//    /**
//     * doProcess_NMAL3300Scrn00_BtnApply_SpecialHandling
//     * @param cMsg EZDCMsg
//     * @param sMsg EZDSMsg
//     */
//    private void doProcess_NMAL3300Scrn00_BtnApply_SpecialHandling(EZDCMsg cMsg, EZDSMsg sMsg) {
//        NMAL3300CMsg bizMsg = (NMAL3300CMsg) cMsg;
//        NMAL3300SMsg sharedMsg = (NMAL3300SMsg) sMsg;
//
//        // Search
//        NMAL3300CommonLogic.getSearchRec(bizMsg, sharedMsg);
//        // 2016/2/25 QC#2823 MOD Start
//        //NMAL3300CommonLogic.getSpclHandlingSection(bizMsg, sharedMsg);
//        NMAL3300CommonLogic.getSpclHandlingSection(bizMsg, sharedMsg, sharedMsg.R.no(0).getBaseContents());
//        // 2016/2/25 QC#2823 MOD End
//    }
//    /**
//     * doProcess_NMAL3300Scrn00_BtnApply_SpecialHandling
//     * @param cMsg EZDCMsg
//     * @param sMsg EZDSMsg
//     */
//    private void doProcess_NMAL3300Scrn00_BtnApply_SpecialMessage(EZDCMsg cMsg, EZDSMsg sMsg) {
//        NMAL3300CMsg bizMsg = (NMAL3300CMsg) cMsg;
//        NMAL3300SMsg sharedMsg = (NMAL3300SMsg) sMsg;
//
//        // Search
//        NMAL3300CommonLogic.getSearchRec(bizMsg, sharedMsg);
//        // 2016/2/25 QC#2823 MOD Start
//        //NMAL3300CommonLogic.getSpclMessageSection(bizMsg, sharedMsg);
//        NMAL3300CommonLogic.getSpclMessageSection(bizMsg, sharedMsg, sharedMsg.R.no(0).getBaseContents());
//        // 2016/2/25 QC#2823 MOD End
//    }
//
//    /**
//     * initCheckbox
//     * @param cMsg NMAL3300CMsg
//     */
//    private void initCheckbox(NMAL3300CMsg cMsg) {
//        cMsg.xxChkBox_VM.setValue(ZYPConstant.CHKBOX_ON_Y);
//    }
    // Del End 2018/11/12 QC#28683

    /**
     * createPullDownList
     * @param bizMsg NMAL3300CMsg
     */
    private void createPullDownList(NMAL3300CMsg bizMsg) {

        // Header
        // Account/Location level(Bill to/Ship to)
        int idx = bizMsg.S.getValidCount();
        String glblCmpyCd = bizMsg.glblCmpyCd_S.getValue();
        List<Map<String, String>> resultList = null;
        Map<String, String> map = null;

        bizMsg.locNum_D.clear();
        bizMsg.locNum_01.clear();
        bizMsg.xxScrItem130Txt_01.clear();
        // 2018/07/30 QC#26712 Add Start
        bizMsg.custHeadCnt_S1.setValue(0);
        int cnt = 0;
        // 2018/07/30 QC#26712 Add End
        for (int i = 0; i < idx; i++) {
            NMAL3300_SCMsg scmsg = bizMsg.S.no(i);
            if (ZYPCommonFunc.hasValue(scmsg.dsAcctNum_S1)) {

                S21SsmEZDResult acctRslt = NMAL3300Query.getInstance().getAcctNum(scmsg.dsAcctNum_S1.getValue(), glblCmpyCd);
                if (acctRslt.isCodeNormal()) {
                    resultList = (List<Map<String, String>>) acctRslt.getResultObject();
                    map = (Map<String, String>) resultList.get(0);
                    ZYPEZDItemValueSetter.setValue(scmsg.dsAcctNum_S2, map.get("SELL_TO_CUST_CD"));
                    // 2018/07/30 QC#26712 Mod Start
//                    bizMsg.locNum_01.no(i).setValue(map.get("SELL_TO_CUST_CD"));
                    bizMsg.locNum_01.no(i).setValue(Integer.toString(i));
                    // 2018/07/30 QC#26712 Mod End
                    bizMsg.xxScrItem130Txt_01.no(i).setValue(S21StringUtil.concatStrings("Account#"
                                                                                         , scmsg.dsAcctNum_S1.getValue()));
                    // 2018/07/30 QC#26712 Add Start
                    cnt = bizMsg.custHeadCnt_S1.getValueInt() + 1;
                    bizMsg.custHeadCnt_S1.setValue(cnt);
                    // 2018/07/30 QC#26712 Add End
                }

            }
            if (ZYPCommonFunc.hasValue(scmsg.billToCustCd_S1)) {
                // Get Account Number and Location Number from Bill To
                S21SsmEZDResult billRslt = NMAL3300Query.getInstance().getLocNumFromBill(scmsg.billToCustCd_S1.getValue(), glblCmpyCd);

                if (billRslt.isCodeNormal()) {
                    resultList = (List<Map<String, String>>) billRslt.getResultObject();
                    map = (Map<String, String>) resultList.get(0);
                    ZYPEZDItemValueSetter.setValue(scmsg.dsAcctNum_S2, map.get("SELL_TO_CUST_CD"));
                    ZYPEZDItemValueSetter.setValue(scmsg.locNum_S2, map.get("LOC_NUM"));
                    // 2018/07/30 QC#26712 Mod Start
//                    bizMsg.locNum_01.no(i).setValue(map.get("LOC_NUM"));
                    bizMsg.locNum_01.no(i).setValue(Integer.toString(i));
                    // 2018/07/30 QC#26712 Mod End
                    // 2018/07/25 QC#26712 Mod Start 
//                    bizMsg.xxScrItem130Txt_01.no(i).setValue(S21StringUtil.concatStrings("Account#"
//                                                                                         ,map.get("SELL_TO_CUST_CD")
//                                                                                         ,", Location#"
//                                                                                         ,map.get("LOC_NUM")));
                    bizMsg.xxScrItem130Txt_01.no(i).setValue(S21StringUtil.concatStrings("Bill To:Account#"
                                                                                         ,map.get("SELL_TO_CUST_CD")
                                                                                         ,", Location#"
                                                                                         ,map.get("LOC_NUM")));

                    cnt = bizMsg.custHeadCnt_S1.getValueInt() + 1;
                    bizMsg.custHeadCnt_S1.setValue(cnt);
                    // 2018/07/25 QC#26712 Mod End
                }
            }
            if (ZYPCommonFunc.hasValue(scmsg.shipToCustCd_S1)) {
                // Get Account Number and Location Number from Ship To
                S21SsmEZDResult shipRslt = NMAL3300Query.getInstance().getLocNumFromShip(scmsg.shipToCustCd_S1.getValue(), glblCmpyCd);

                if (shipRslt.isCodeNormal()) {
                    resultList = (List<Map<String, String>>) shipRslt.getResultObject();
                    map = (Map<String, String>) resultList.get(0);
                    ZYPEZDItemValueSetter.setValue(scmsg.dsAcctNum_S2, map.get("SELL_TO_CUST_CD"));
                    ZYPEZDItemValueSetter.setValue(scmsg.locNum_S2, map.get("LOC_NUM"));
                    // 2018/07/30 QC#26712 Mod Start
//                    bizMsg.locNum_01.no(i).setValue(map.get("LOC_NUM"));
                    bizMsg.locNum_01.no(i).setValue(Integer.toString(i));
                    // 2018/07/30 QC#26712 Mod End
                    // 2018/07/25 QC#26712 Mod Start
//                    bizMsg.xxScrItem130Txt_01.no(i).setValue(S21StringUtil.concatStrings("Account#"
//                                                                                         ,map.get("SELL_TO_CUST_CD")
//                                                                                         ,", Location#"
//                                                                                         ,map.get("LOC_NUM")));
                    bizMsg.xxScrItem130Txt_01.no(i).setValue(S21StringUtil.concatStrings("Ship To:Account#"
                                                                                         ,map.get("SELL_TO_CUST_CD")
                                                                                         ,", Location#"
                                                                                         ,map.get("LOC_NUM")));

                    cnt = bizMsg.custHeadCnt_S1.getValueInt() + 1;
                    bizMsg.custHeadCnt_S1.setValue(cnt);
                    // 2018/07/25 QC#26712 Mod End
                }
            }
        }

        // Del Start 2018/11/12 QC#28683
//        // Detail
//        // Transaction Section
//        // Hierarchy Effective Level Type (Transaction)
//        bizMsg.hrchEffLvlTpCd_D.clear();
//        bizMsg.hrchEffLvlTpCd_01.clear();
//        bizMsg.hrchEffLvlTpNm_01.clear();
//        ZYPCodeDataUtil.createPulldownList(HRCH_EFF_LVL_TP.class, bizMsg.hrchEffLvlTpCd_01, bizMsg.hrchEffLvlTpNm_01);
//
//        // Customer Reference Section
//        // Hierarchy Effective Level Type (Customer Reference)
//        bizMsg.hrchEffLvlTpCd_D2.clear();
//        bizMsg.hrchEffLvlTpCd_02.clear();
//        bizMsg.hrchEffLvlTpNm_02.clear();
//        ZYPCodeDataUtil.createPulldownList(HRCH_EFF_LVL_TP.class, bizMsg.hrchEffLvlTpCd_02, bizMsg.hrchEffLvlTpNm_02);
//
//        // Special Handling Section
//        // Hierarchy Effective Level Type (Special Handling)
//        bizMsg.hrchEffLvlTpCd_D3.clear();
//        bizMsg.hrchEffLvlTpCd_03.clear();
//        bizMsg.hrchEffLvlTpNm_03.clear();
//        ZYPCodeDataUtil.createPulldownList(HRCH_EFF_LVL_TP.class, bizMsg.hrchEffLvlTpCd_03, bizMsg.hrchEffLvlTpNm_03);
//
//        // Special Handling Type
//        bizMsg.dsSpclHdlgTpCd_D.clear();
//        bizMsg.dsSpclHdlgTpCd_01.clear();
//        bizMsg.dsSpclHdlgTpNm_01.clear();
//
////        ZYPCodeDataUtil.createPulldownList(DS_SPCL_HDLG_TP.class, bizMsg.dsSpclHdlgTpCd_01, bizMsg.dsSpclHdlgTpNm_01);
//        bizMsg.dsSpclHdlgTpCd_01.no(0).setValue("HDER");
//        bizMsg.dsSpclHdlgTpCd_01.no(1).setValue("HDRM");
//        bizMsg.dsSpclHdlgTpNm_01.no(0).setValue("HARD DRIVE ERASE");
//        bizMsg.dsSpclHdlgTpNm_01.no(1).setValue("HARD DRIVE REMOVAL");
//
//        // Effective Level
//        bizMsg.custEffLvlCd_D.clear();
//        bizMsg.custEffLvlCd_01.clear();
//        bizMsg.custEffLvlNm_01.clear();
//        ZYPCodeDataUtil.createPulldownList(CUST_EFF_LVL.class, bizMsg.custEffLvlCd_01, bizMsg.custEffLvlNm_01);
//
//        // Special Message Section
//        // Hierarchy Effective Level Type (Special Message)
//        bizMsg.hrchEffLvlTpCd_D4.clear();
//        bizMsg.hrchEffLvlTpCd_04.clear();
//        bizMsg.hrchEffLvlTpNm_04.clear();
//        ZYPCodeDataUtil.createPulldownList(HRCH_EFF_LVL_TP.class, bizMsg.hrchEffLvlTpCd_04, bizMsg.hrchEffLvlTpNm_04);
//
//        // LOB
//        bizMsg.lineBizTpCd_D.clear();
//        bizMsg.lineBizTpCd_01.clear();
//        bizMsg.lineBizTpNm_01.clear();
//        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.lineBizTpCd_01, bizMsg.lineBizTpNm_01);
//
//        // Biz Area
//        bizMsg.dsBizAreaCd_D.clear();
//        bizMsg.dsBizAreaCd_01.clear();
//        bizMsg.dsBizAreaNm_01.clear();
//        ZYPCodeDataUtil.createPulldownList(DS_BIZ_AREA.class, bizMsg.dsBizAreaCd_01, bizMsg.dsBizAreaNm_01);
//
//        // Customer Message Type
//        bizMsg.dsCustMsgTpCd_D.clear();
//        bizMsg.dsCustMsgTpCd_01.clear();
//        bizMsg.dsCustMsgTpNm_01.clear();
//        ZYPCodeDataUtil.createPulldownList(DS_CUST_MSG_TP.class, bizMsg.dsCustMsgTpCd_01, bizMsg.dsCustMsgTpNm_01);
//
//        // Customer Message Type
//        bizMsg.dsCustMsgTpCd_D.clear();
//        bizMsg.dsCustMsgTpCd_01.clear();
//        bizMsg.dsCustMsgTpNm_01.clear();
//        ZYPCodeDataUtil.createPulldownList(DS_CUST_MSG_TP.class, bizMsg.dsCustMsgTpCd_01, bizMsg.dsCustMsgTpNm_01);
//
//        // Customer Effective Level
//        bizMsg.custEffLvlCd_D2.clear();
//        bizMsg.custEffLvlCd_02.clear();
//        bizMsg.custEffLvlNm_02.clear();
//        ZYPCodeDataUtil.createPulldownList(CUST_EFF_LVL.class, bizMsg.custEffLvlCd_02, bizMsg.custEffLvlNm_02);
        // Del End 2018/11/12 QC#28683
    }

    /**
     * search
     * @param bizMsg NMAL3300CMsg
     * @param sharedMsg NMAL3300SMsg
     */
    private void search(NMAL3300CMsg bizMsg, NMAL3300SMsg sharedMsg) {

        NMAL3300CommonLogic.getSearchRec(bizMsg, sharedMsg);
        // 2016/2/25 QC#2823 ADD Start
        //NMAL3300CommonLogic.getTrxDriverSection(bizMsg, sharedMsg);
        // Del Start 2018/11/12 QC#28683
        //NMAL3300CommonLogic.getTrxDriverSection(bizMsg, sharedMsg, sharedMsg.R.no(0).getBaseContents());
        // Del End 2018/11/12 QC#28683
        //NMAL3300CommonLogic.getCustReferenceSection(bizMsg, sharedMsg);
        // Del Start 2018/11/12 QC#28683
        //NMAL3300CommonLogic.getCustReferenceSection(bizMsg, sharedMsg, sharedMsg.R.no(0).getBaseContents());
        // Del End 2018/11/12 QC#28683
        //NMAL3300CommonLogic.getSpclHandlingSection(bizMsg, sharedMsg);
        // Del Start 2018/11/12 QC#28683
        //NMAL3300CommonLogic.getSpclHandlingSection(bizMsg, sharedMsg, sharedMsg.R.no(0).getBaseContents());
        // Del End 2018/11/12 QC#28683
        //NMAL3300CommonLogic.getSpclMessageSection(bizMsg, sharedMsg);
        NMAL3300CommonLogic.getSpclMessageSection(bizMsg, sharedMsg, sharedMsg.R.no(0).getBaseContents());
        // 2016/2/25 QC#2823 ADD End
    }

    /**
     * Method name: doProcess_NMAL3300Scrn00_PageNext <dd>The method
     * explanation: PagePrev. <dd>Remarks:
     * @param cMsg NMAL3300CMsg
     * @param sMsg NMAL3300SMsg
     */
    private void doProcess_NMAL3300Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {

        NMAL3300CMsg bizMsg = (NMAL3300CMsg) cMsg;
        NMAL3300SMsg sharedMsg = (NMAL3300SMsg) sMsg;
        // Set Pagenation
        // Del Start 2018/11/12 QC#28683
        //int pagenationFrom = 0;
        //if (bizMsg.xxModeInd.equals(TABLE_A)) {
        //    ZYPTableUtil.clear(bizMsg.A);
        //    bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowToNum_A.getValueInt());
        //    bizMsg.xxPageShowToNum_A.clear();
        //
        //    NMAL3300CommonLogic.copyFromSMsgOntoCmsg(bizMsg, sharedMsg, TABLE_A);
        //}
        // Del End 2018/11/12 QC#28683

        // Add Start 2018/11/12 QC#28683
        ZYPTableUtil.clear(bizMsg.D);
        bizMsg.xxPageShowFromNum_D.setValue(bizMsg.xxPageShowToNum_D.getValueInt());
        bizMsg.xxPageShowToNum_D.clear();

        NMAL3300CommonLogic.copyFromSMsgOntoCmsg(bizMsg, sharedMsg);
        // Add End 2018/11/12 QC#28683

    }
    /**
     * Method name: doProcess_NMAL3300Scrn00_PagePrev <dd>The method
     * explanation: PagePrev. <dd>Remarks:
     * @param cMsg NMAL3300CMsg
     * @param sMsg NMAL3300SMsg
     */
    private void doProcess_NMAL3300Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {

        NMAL3300CMsg bizMsg = (NMAL3300CMsg) cMsg;
        NMAL3300SMsg sharedMsg = (NMAL3300SMsg) sMsg;
        // set values to items of pageNation for next page pageNation
        // Del Start 2018/11/12 QC#28683
        //if (bizMsg.xxModeInd.equals(TABLE_A)) {
        //    ZYPTableUtil.clear(bizMsg.A);
        //    bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() - bizMsg.A.length() - 1);
        //    bizMsg.xxPageShowToNum_A.clear();
        //
        //    NMAL3300CommonLogic.copyFromSMsgOntoCmsg(bizMsg, sharedMsg, TABLE_B);
        //}
        // Del End 2018/11/12 QC#28683

        // Add Start 2018/11/12 QC#28683
        ZYPTableUtil.clear(bizMsg.D);
        bizMsg.xxPageShowFromNum_D.setValue(bizMsg.xxPageShowFromNum_D.getValueInt() - bizMsg.D.length() - 1);
        bizMsg.xxPageShowToNum_D.clear();

        NMAL3300CommonLogic.copyFromSMsgOntoCmsg(bizMsg, sharedMsg);
        // Add End 2018/11/12 QC#28683

    }

    // Add Start 2018/11/12 QC#28683
    /**
     * doProcess_NMAL3300Scrn00_TBLColumnSort
     * @param cMsg NMAL3300CMsg
     * @param sMsg NMAL3300SMsg
     */
    private void doProcess_NMAL3300Scrn00_TBLColumnSort(EZDCMsg cMsg, EZDSMsg sMsg) {

        NMAL3300CMsg bizMsg = (NMAL3300CMsg) cMsg;
        NMAL3300SMsg sharedMsg = (NMAL3300SMsg) sMsg;

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:D
        if (TABLE_D.equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sharedMsg.D, sharedMsg.D.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sharedMsg.D.getValidCount());

            if (bizMsg.D.getValidCount() > 0) {
                int i = 0;

                for (; i < sharedMsg.D.getValidCount(); i++) {
                    if (i == bizMsg.D.length()) {
                        break;
                    }

                    EZDMsg.copy(sharedMsg.D.no(i), null, bizMsg.D.no(i), null);
                }

                bizMsg.D.setValidCount(i);

                bizMsg.xxPageShowFromNum_D.setValue(1);
                bizMsg.xxPageShowToNum_D.setValue(bizMsg.D.getValidCount());
            }
        }
    }
    // Add End 2018/11/12 QC#28683

    /**
     * Get String Value from Map. (With Conversion from Null to "")
     * @param map Map<String, String> 
     * @param key String
     * @return String
     */
    private String getString(Map<String, String> map, String key) {
        String ret = (String) map.get(key);
        if (ZYPCommonFunc.hasValue(ret)) {
            return ret;
        }
        return "";
    }
}
