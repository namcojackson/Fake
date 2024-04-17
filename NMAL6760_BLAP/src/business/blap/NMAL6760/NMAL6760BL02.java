/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6760;

import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_HRCH_ACCT_CD_ALL;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_HRCH_ACCT_CD_BILL;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_HRCH_ACCT_CD_SHIP;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_HRCH_ACCT_NM_ALL;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_HRCH_ACCT_NM_BILL;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_HRCH_ACCT_NM_SHIP;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_RELN_ACCT_CD_ACCT;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_RELN_ACCT_CD_ACCT_ADDR;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_RELN_ACCT_CD_BILL;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_RELN_ACCT_CD_LEASE_ACCT;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_RELN_ACCT_CD_LEASE_BILL;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_RELN_ACCT_CD_SHIP;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_RELN_ACCT_NM_ACCT;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_RELN_ACCT_NM_ACCT_ADDR;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_RELN_ACCT_NM_BILL;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_RELN_ACCT_NM_LEASE_ACCT;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_RELN_ACCT_NM_LEASE_BILL;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DISP_RELN_ACCT_NM_SHIP;
import static business.blap.NMAL6760.constant.NMAL6760Constant.EXTERNAL;
import static business.blap.NMAL6760.constant.NMAL6760Constant.INTERNAL;
import static business.blap.NMAL6760.constant.NMAL6760Constant.SEARCH_MODE_CD_QUICK;
import static business.blap.NMAL6760.constant.NMAL6760Constant.SEARCH_MODE_NM_QUICK;
import static business.blap.NMAL6760.constant.NMAL6760Constant.STATUS_CD_ACTIVE;
import static business.blap.NMAL6760.constant.NMAL6760Constant.STATUS_CD_ALL;
import static business.blap.NMAL6760.constant.NMAL6760Constant.STATUS_NM_ACTIVE;
import static business.blap.NMAL6760.constant.NMAL6760Constant.STATUS_NM_ALL;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6760.common.NMAL6760CommonLogic;
import business.db.STTMsg;
import business.db.STTMsgArray;

import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_XREF_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NMAL6760BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/30   Fujitsu         C.Yokoi         Update          CSA-QC#14340
 * 2018/05/15   Fujitsu         M.Ohno          Update          QC#22715
 * 2018/06/18   Fujitsu         T.Noguchi       Update          QC#14307
 * 2018/07/10   Fujitsu         T.Noguchi       Update          QC#26661
 * 2018/08/25   Fujitsu         T.Aoi           Update          QC#27457
 * 2018/10/24   Fujitsu         H.Kumagai       Update          QC#28866
 *</pre>
 */
public class NMAL6760BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL6760CMsg bizMsg = (NMAL6760CMsg) cMsg;
            NMAL6760SMsg glblMsg = (NMAL6760SMsg) sMsg;

            if ("NMAL6760_INIT".equals(screenAplID)) {
                doProcess_NMAL6760_INIT(bizMsg, glblMsg);
            } else if ("NMAL6760Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL6760Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NMAL6760Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL6760Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL6760Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL6760Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NMAL6760Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL6760Scrn00_Search(bizMsg, glblMsg);

            } else if ("NMAL6760_NWAL1130".equals(screenAplID)) {
                doProcess_NMAL6760_NWAL1130(bizMsg, glblMsg);
            // 2018/05/15 QC#22715 add start
            } else if ("NMAL6760Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL6760Scrn00_TBLColumnSort((NMAL6760CMsg) cMsg, (NMAL6760SMsg) sMsg);
            // 2018/05/15 QC#22715 add end
            // 2018/06/18 QC#14307 Add Start
            } else if ("NMAL6760Scrn00_SelectAcct".equals(screenAplID)) {
                doProcess_NMAL6760Scrn00_SelectAcct(bizMsg, glblMsg);
            } else if ("NMAL6760_NMAL3300".equals(screenAplID)) {
                doProcess_NMAL6760_NMAL3300(bizMsg, glblMsg);
            // 2018/06/18 QC#14307 Add End
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL6760_INIT(NMAL6760CMsg bizMsg, NMAL6760SMsg glblMsg) {
        createPullDownList(bizMsg);
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_01) || ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_01) || ZYPCommonFunc.hasValue(bizMsg.locNum_01) || ZYPCommonFunc.hasValue(bizMsg.dsLocNm_01)
                || ZYPCommonFunc.hasValue(bizMsg.xxAllLineAddr_01) || ZYPCommonFunc.hasValue(bizMsg.ctyAddr_01) || ZYPCommonFunc.hasValue(bizMsg.stCd_DP) || ZYPCommonFunc.hasValue(bizMsg.postCd_01)) {
            searchQuickLookup(bizMsg, glblMsg);
        }
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL6760Scrn00_PageJump(NMAL6760CMsg bizMsg, NMAL6760SMsg glblMsg) {
        bizMsg.xxPageShowFromNum.setValue((bizMsg.C.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NMAL6760CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.C, glblMsg.C);

    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL6760Scrn00_PageNext(NMAL6760CMsg bizMsg, NMAL6760SMsg glblMsg) {
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL6760CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.C, glblMsg.C);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL6760Scrn00_PagePrev(NMAL6760CMsg bizMsg, NMAL6760SMsg glblMsg) {
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.C.length());
        NMAL6760CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.C, glblMsg.C);

    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL6760Scrn00_Search(NMAL6760CMsg bizMsg, NMAL6760SMsg glblMsg) {
        // 2018/10/24 Add Start QC#28866
        NMAL6760CommonLogic.outputSearchLog(bizMsg);
        // 2018/10/24 Add End QC#28866
        searchQuickLookup(bizMsg, glblMsg);
    }

    /**
     * NWAL1130 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL6760_NWAL1130(NMAL6760CMsg bizMsg, NMAL6760SMsg glblMsg) {
        //
    }

    private void createPullDownList(NMAL6760CMsg bizMsg) {

        // Search Mode
        bizMsg.xxAcctSrchModeCd_D1.clear();
        bizMsg.xxAcctSrchModeCd_01.clear();
        bizMsg.xxModeNm23Txt_01.clear();
        bizMsg.xxAcctSrchModeCd_01.no(0).setValue(SEARCH_MODE_CD_QUICK);
        bizMsg.xxModeNm23Txt_01.no(0).setValue(SEARCH_MODE_NM_QUICK);

        // Status
        bizMsg.xxAcctSrchStsCd_02.clear();
        bizMsg.xxModeNm23Txt_02.clear();
        bizMsg.xxAcctSrchStsCd_02.no(0).setValue(STATUS_CD_ACTIVE);
        bizMsg.xxAcctSrchStsCd_02.no(1).setValue(STATUS_CD_ALL);
        bizMsg.xxModeNm23Txt_02.no(0).setValue(STATUS_NM_ACTIVE);
        bizMsg.xxModeNm23Txt_02.no(1).setValue(STATUS_NM_ALL);

        // State
        bizMsg.stCd_01.clear();
        bizMsg.stNm_01.clear();
        // 2018/08/25 QC#27457 Mod Start
        //ZYPCodeDataUtil.createPulldownList(ST.class, bizMsg.stCd_01, bizMsg.stNm_01);
        //for (int i = 0; i < bizMsg.stCd_01.length(); i++) {
        //    bizMsg.stNm_01.no(i).setValue(bizMsg.stCd_01.no(i).getValue());
        //}
        STTMsg condition = new STTMsg();
        condition.setSQLID("003");
        condition.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        condition.setConditionValue("ctryCd01", CTRY.UNITED_STATES_OF_AMERICA);

        STTMsgArray tMsgArray = (STTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                bizMsg.stCd_01.no(i).setValue(tMsgArray.no(i).stCd.getValue());
                bizMsg.stNm_01.no(i).setValue(tMsgArray.no(i).stCd.getValue());
            }
        }
        // 2018/08/25 QC#27457 Mod End

        // Category
        if (!ZYPCommonFunc.hasValue(bizMsg.dsAcctTpCd_DP)) {
            bizMsg.dsAcctTpCd_DP.setValue(DS_ACCT_TP.CUSTOMER);
        }
        bizMsg.dsAcctTpCd_01.clear();
        bizMsg.dsAcctTpNm_01.clear();
        ZYPCodeDataUtil.createPulldownList(DS_ACCT_TP.class, bizMsg.dsAcctTpCd_01, bizMsg.dsAcctTpNm_01);

        // Account Classification
        bizMsg.dsAcctClsCd_DP.clear();
        bizMsg.dsAcctClsCd_01.clear();
        bizMsg.dsAcctClsNm_01.clear();
        ZYPCodeDataUtil.createPulldownList(DS_ACCT_CLS.class, bizMsg.dsAcctClsCd_01, bizMsg.dsAcctClsNm_01);

        // Cross Reference Category
        bizMsg.dsXrefAcctTpCd_DP.clear();
        bizMsg.dsXrefAcctTpCd_01.clear();
        bizMsg.dsXrefAcctTpNm_01.clear();
        ZYPCodeDataUtil.createPulldownList(DS_XREF_ACCT_TP.class, bizMsg.dsXrefAcctTpCd_01, bizMsg.dsXrefAcctTpNm_01);

        // Internal/External
        bizMsg.dsAcctItrlFlg_01.clear();
        bizMsg.xxCtlNm_01.clear();
        bizMsg.dsAcctItrlFlg_C1.clear();
        bizMsg.dsAcctItrlFlg_01.no(0).setValue(ZYPConstant.FLG_ON_Y);
        bizMsg.dsAcctItrlFlg_01.no(1).setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.xxCtlNm_01.no(0).setValue(INTERNAL);
        bizMsg.xxCtlNm_01.no(1).setValue(EXTERNAL);

        // Display Hierarchy Accounts
        bizMsg.xxAcctSrchDplyHrchCd_03.clear();
        bizMsg.xxModeNm23Txt_03.clear();
        bizMsg.xxAcctSrchDplyHrchCd_03.no(0).setValue(DISP_HRCH_ACCT_CD_ALL);
        bizMsg.xxAcctSrchDplyHrchCd_03.no(1).setValue(DISP_HRCH_ACCT_CD_BILL);
        bizMsg.xxAcctSrchDplyHrchCd_03.no(2).setValue(DISP_HRCH_ACCT_CD_SHIP);
        bizMsg.xxModeNm23Txt_03.no(0).setValue(DISP_HRCH_ACCT_NM_ALL);
        bizMsg.xxModeNm23Txt_03.no(1).setValue(DISP_HRCH_ACCT_NM_BILL);
        bizMsg.xxModeNm23Txt_03.no(2).setValue(DISP_HRCH_ACCT_NM_SHIP);

        // Display Related Account#
        bizMsg.xxAcctSrchDplyRelnCd_04.clear();
        bizMsg.xxModeNm23Txt_04.clear();
        bizMsg.xxAcctSrchDplyRelnCd_04.no(0).setValue(DISP_RELN_ACCT_CD_ACCT);
        bizMsg.xxAcctSrchDplyRelnCd_04.no(1).setValue(DISP_RELN_ACCT_CD_ACCT_ADDR);
        bizMsg.xxAcctSrchDplyRelnCd_04.no(2).setValue(DISP_RELN_ACCT_CD_BILL);
        bizMsg.xxAcctSrchDplyRelnCd_04.no(3).setValue(DISP_RELN_ACCT_CD_SHIP);
        bizMsg.xxAcctSrchDplyRelnCd_04.no(4).setValue(DISP_RELN_ACCT_CD_LEASE_ACCT);
        bizMsg.xxAcctSrchDplyRelnCd_04.no(5).setValue(DISP_RELN_ACCT_CD_LEASE_BILL);
        bizMsg.xxModeNm23Txt_04.no(0).setValue(DISP_RELN_ACCT_NM_ACCT);
        bizMsg.xxModeNm23Txt_04.no(1).setValue(DISP_RELN_ACCT_NM_ACCT_ADDR);
        bizMsg.xxModeNm23Txt_04.no(2).setValue(DISP_RELN_ACCT_NM_BILL);
        bizMsg.xxModeNm23Txt_04.no(3).setValue(DISP_RELN_ACCT_NM_SHIP);
        bizMsg.xxModeNm23Txt_04.no(4).setValue(DISP_RELN_ACCT_NM_LEASE_ACCT);
        bizMsg.xxModeNm23Txt_04.no(5).setValue(DISP_RELN_ACCT_NM_LEASE_BILL);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_01, ZYPConstant.FLG_ON_Y);
    }

    @SuppressWarnings("unchecked")
    private void searchQuickLookup(EZDCMsg cMsg, EZDSMsg sMsg) {

        NMAL6760CMsg bizMsg = (NMAL6760CMsg) cMsg;
        NMAL6760SMsg sharedMsg = (NMAL6760SMsg) sMsg;

        bizMsg.C.clear();
        bizMsg.C.setValidCount(0);
        sharedMsg.C.clear();
        sharedMsg.C.setValidCount(0);
        NMAL6760CommonLogic.clearPageNum(bizMsg);
        S21SsmEZDResult ssmResult = null;
        int queryResCnt = 0;
        ssmResult = NMAL6760CommonLogic.getDsAccountList(bizMsg, sharedMsg);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            queryResCnt = ssmResult.getQueryResultCount();
            if (!NMAL6760CommonLogic.checkMaxCount(bizMsg, queryResCnt, sharedMsg.C.length())) {
                queryResCnt = sharedMsg.C.length();
            }
            if (queryResCnt <= sharedMsg.C.length()
                    && ZYPCommonFunc.hasValue(bizMsg.xxAcctSrchDplyRelnCd_D4)) {

                // get Include Related Accounts
                NMAL6760CommonLogic.getInclRelnAcct(bizMsg, sharedMsg, queryResCnt, resultList);
                queryResCnt = Integer.valueOf(sharedMsg.C.getValidCount()).intValue();

            } else {
                int idx = 0;
                for (int i = 0; i < resultList.size(); i++) {
                    if (i >= sharedMsg.C.length()) {
                        break;
                    }
                    Map map = (Map) resultList.get(i);
                    NMAL6760CommonLogic.setAccountInfo(bizMsg, sharedMsg.C.no(i), map);
                    idx++;
                }
                sharedMsg.C.setValidCount(idx);
            }
        }

        if (ssmResult.isCodeNormal()) {
            if (queryResCnt == 0) {
                cMsg.setMessageInfo("NZZM0000E");
            } else {
                if (queryResCnt > sharedMsg.C.length()) {
                    cMsg.setMessageInfo("ZZZM9002W");
                }
                NMAL6760CommonLogic.copyFromSMsgOntoCmsg(bizMsg, sharedMsg);
            }
        } else {
            cMsg.setMessageInfo("NZZM0000E");
        }
        return;
    }

    // 2018/05/15 QC#22715 add start
    private void doProcess_NMAL6760Scrn00_TBLColumnSort(NMAL6760CMsg bizMsg, NMAL6760SMsg glblMsg) {

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:C
        if ("C".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(glblMsg.C, glblMsg.C.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.C.getValidCount());

            if (bizMsg.C.getValidCount() > 0) {
                int i = 0;
                for (; i < glblMsg.C.getValidCount(); i++) {
                    if (i == bizMsg.C.length()) {
                        break;
                    }
                    EZDMsg.copy(glblMsg.C.no(i), null, bizMsg.C.no(i), null);
                }
                bizMsg.C.setValidCount(i);

                bizMsg.xxPageShowFromNum.setValue(1);
                bizMsg.xxPageShowToNum.setValue(bizMsg.C.getValidCount());
            }
        }
    }
    // 2018/05/15 QC#22715 add end

    // 2018/06/18 QC#14307 Add Start
    /**
     * SelectAcct Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL6760Scrn00_SelectAcct(NMAL6760CMsg bizMsg, NMAL6760SMsg glblMsg) {

        bizMsg.dsAcctNum_SP.clear();
        bizMsg.billToCustCd_SP.clear();
        bizMsg.shipToCustCd_SP.clear();

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDplyCtrlFlg_SP.getValue())) {
            int selectIndex = bizMsg.xxCellIdx_01.getValueInt();
            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.C.no(selectIndex).xxMstChkFlg_C1.getValue())) {
                // Acct#
                if (ZYPCommonFunc.hasValue(bizMsg.C.no(selectIndex).dsAcctNum_C1) &&
                    NWXC150001DsCheck.spclInstnDisplayDetermination(getGlobalCompanyCode(),
                        bizMsg.dsTrxRuleTpCd_SP.getValue(),
                        bizMsg.dsBizAreaCd_SP.getValue(),
                        bizMsg.C.no(selectIndex).dsAcctNum_C1.getValue(),
                        null,
                        null,
                        bizMsg.funcMstrId_SP.getValue(),
                        // 2018/07/10 QC#26661 Mod Start
                        // bizMsg.funcMstrIdDescTxt_SP.getValue())) {
                        bizMsg.funcMstrIdDescTxt_SP.getValue(),
                        bizMsg.lineBizTpCd_SP.getValue())) {
                        // 2018/07/10 QC#26661 Mod End
                    ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum_SP, bizMsg.C.no(selectIndex).dsAcctNum_C1);
                }
                // Bill To
                if (ZYPCommonFunc.hasValue(bizMsg.C.no(selectIndex).billToCustCd_C1) &&
                    NWXC150001DsCheck.spclInstnDisplayDetermination(getGlobalCompanyCode(),
                        bizMsg.dsTrxRuleTpCd_SP.getValue(),
                        bizMsg.dsBizAreaCd_SP.getValue(),
                        null,
                        bizMsg.C.no(selectIndex).billToCustCd_C1.getValue(),
                        null,
                        bizMsg.funcMstrId_SP.getValue(),
                        // 2018/07/10 QC#26661 Mod Start
                        // bizMsg.funcMstrIdDescTxt_SP.getValue())) {
                        bizMsg.funcMstrIdDescTxt_SP.getValue(),
                        bizMsg.lineBizTpCd_SP.getValue())) {
                        // 2018/07/10 QC#26661 Mod End
                    ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_SP, bizMsg.C.no(selectIndex).billToCustCd_C1);
                }
                // Ship To
                if (ZYPCommonFunc.hasValue(bizMsg.C.no(selectIndex).shipToCustCd_C1) &&
                    NWXC150001DsCheck.spclInstnDisplayDetermination(getGlobalCompanyCode(),
                        bizMsg.dsTrxRuleTpCd_SP.getValue(),
                        bizMsg.dsBizAreaCd_SP.getValue(),
                        null,
                        null,
                        bizMsg.C.no(selectIndex).shipToCustCd_C1.getValue(),
                        bizMsg.funcMstrId_SP.getValue(),
                        // 2018/07/10 QC#26661 Mod Start
                        // bizMsg.funcMstrIdDescTxt_SP.getValue())) {
                        bizMsg.funcMstrIdDescTxt_SP.getValue(),
                        bizMsg.lineBizTpCd_SP.getValue())) {
                        // 2018/07/10 QC#26661 Mod End
                    ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd_SP, bizMsg.C.no(selectIndex).shipToCustCd_C1);
                }
            } 
        }
    }

    /**
     * NMAL3300 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL6760_NMAL3300(NMAL6760CMsg bizMsg, NMAL6760SMsg glblMsg) {

        int selectIndex = bizMsg.xxCellIdx_01.getValueInt();
        int copyToIndex = selectIndex + bizMsg.xxPageShowFromNum.getValueInt() - 1;
        bizMsg.C.no(selectIndex).xxMstChkFlg_C1.setValue(ZYPConstant.FLG_ON_Y);
        EZDMsg.copy(bizMsg.C.no(selectIndex), null, glblMsg.C.no(copyToIndex), null);
    }
    // 2018/06/18 QC#14307 Add End
}
