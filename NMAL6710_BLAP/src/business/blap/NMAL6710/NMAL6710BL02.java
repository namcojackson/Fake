/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.blap.NMAL6710;

import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_HRCH_ACCT_CD_ALL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_HRCH_ACCT_CD_BILL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_HRCH_ACCT_CD_SHIP;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_HRCH_ACCT_NM_ALL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_HRCH_ACCT_NM_BILL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_HRCH_ACCT_NM_SHIP;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_CD_ACCT;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_CD_ACCT_ADDR;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_CD_BILL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_CD_LEASE_ACCT;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_CD_LEASE_BILL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_CD_SHIP;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_NM_ACCT;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_NM_ACCT_ADDR;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_NM_BILL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_NM_LEASE_ACCT;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_NM_LEASE_BILL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_NM_SHIP;
import static business.blap.NMAL6710.constant.NMAL6710Constant.EXTERNAL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.INTERNAL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.SEARCH_MODE_CD_HRCH;
import static business.blap.NMAL6710.constant.NMAL6710Constant.SEARCH_MODE_CD_QUICK;
import static business.blap.NMAL6710.constant.NMAL6710Constant.SEARCH_MODE_NM_HRCH;
import static business.blap.NMAL6710.constant.NMAL6710Constant.SEARCH_MODE_NM_QUICK;
import static business.blap.NMAL6710.constant.NMAL6710Constant.STATUS_CD_ACTIVE;
import static business.blap.NMAL6710.constant.NMAL6710Constant.STATUS_CD_ALL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.STATUS_NM_ACTIVE;
import static business.blap.NMAL6710.constant.NMAL6710Constant.STATUS_NM_ALL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.TMPL_FILE_BASE;
import static business.blap.NMAL6710.constant.NMAL6710Constant.TMPL_FILE_EXTENSION;
import static business.blap.NMAL6710.constant.NMAL6710Constant.NMAM8610W;
import static business.blap.NMAL6710.constant.NMAL6710Constant.NMAM8676W;
import static business.blap.NMAL6710.constant.NMAL6710Constant.MAX_DL_SIZE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6710.common.NMAL6710CommonLogic;
import business.blap.NMAL6710.constant.NMAL6710Constant;
import business.db.STTMsg;
import business.db.STTMsgArray;
import business.file.NMAL6710F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_XREF_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 *  Account Search BLAP 02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/20/2015   SRAA            K.Aratani       Create          N/A
 * 10/01/2015   Fujitsu         N.Sugiura       Update          CSA
 * 02/17/2016   Fujitsu         T.Murai         Update          CSA #2943
 * 02/18/2016   SRAA            Y.Chen          Update          QC#3778
 * 03/02/2016   SRAA            Y.Chen          Update          QC#3290
 * 03/17/2016   SRAA            Y.Chen          Update          QC#4506
 * 04/25/2016   SRAA            Y.Chen          Update          QC#6188
 * 05/17/2016   SRAA            Y.Chen          Update          QC#7932
 * 06/21/2016   SRAA            Y.Chen          Update          QC#6189
 * 09/21/2016   Fujitsu         Mz.Takahashi    Update          QC#11068
 * 12/05/2016   Fujitsu         C.Yokoi         Update          QC#14340
 * 02/16/2017   Fujitsu         N.Sugiura       Update          QC#17655
 * 10/03/2017   CITS            T.Tokutomi      Update          QC#21196
 * 2018/02/23   Fujitsu         Hd.Sugawara     Update          QC#22570
 * 2018/05/15   Fujitsu         M.Ohno          Update          QC#22715
 * 2018/08/25   Fujitsu         T.Aoi           Update          QC#27457
 *</pre>
 */
public class NMAL6710BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6710_INIT".equals(screenAplID)) {
                doProcess_NMAL6710_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData((NMAL6710CMsg) cMsg, (NMAL6710SMsg) sMsg);
            } else if ("NMAL6710Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL6710Scrn00_Search_New((NMAL6710CMsg) cMsg, (NMAL6710SMsg) sMsg);
            // QC#6189
            } else if ("NMAL6710Scrn00_GoToAcct".equals(screenAplID)) {
                doProcess_NMAL6710Scrn00_GoToAcct((NMAL6710CMsg) cMsg, (NMAL6710SMsg) sMsg);
            } else if ("NMAL6710Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL6710_INIT(cMsg, sMsg);
            } else if ("NMAL6710Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL6710_Download((NMAL6710CMsg) cMsg, (NMAL6710SMsg) sMsg);
            } else if ("NMAL6710Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL6710Scrn00_PagePrev((NMAL6710CMsg) cMsg, (NMAL6710SMsg) sMsg);
            } else if ("NMAL6710Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL6710Scrn00_PageNext((NMAL6710CMsg) cMsg, (NMAL6710SMsg) sMsg);
            } else if ("NMAL6710Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL6710Scrn00_PageJump((NMAL6710CMsg) cMsg, (NMAL6710SMsg) sMsg);
            } else if ("NMAL6710Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NMAL6710Scrn00_OnChangeSavedSearchOption((NMAL6710CMsg) cMsg, (NMAL6710SMsg) sMsg);
            // 2018/05/15 QC#22715 add start
            } else if ("NMAL6710Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL6710Scrn00_TBLColumnSort((NMAL6710CMsg) cMsg, (NMAL6710SMsg) sMsg);
            // 2018/05/15 QC#22715 add end
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Initial setup
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NMAL6710_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        NMAL6710CMsg bizMsg = (NMAL6710CMsg) cMsg;

        createPullDownList(bizMsg);
        NMAL6710CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());

    }
    private void createPullDownList(NMAL6710CMsg bizMsg) {

        bizMsg.xxAcctSrchModeCd_D1.clear();
        bizMsg.xxAcctSrchModeCd_01.clear();
        bizMsg.xxModeNm23Txt_01.clear();
        bizMsg.xxAcctSrchModeCd_01.no(0).setValue(SEARCH_MODE_CD_HRCH);
        bizMsg.xxAcctSrchModeCd_01.no(1).setValue(SEARCH_MODE_CD_QUICK);
        bizMsg.xxModeNm23Txt_01.no(0).setValue(SEARCH_MODE_NM_HRCH);
        bizMsg.xxModeNm23Txt_01.no(1).setValue(SEARCH_MODE_NM_QUICK);

        // Status
        bizMsg.xxAcctSrchStsCd_D2.clear();
        bizMsg.xxAcctSrchStsCd_02.clear();
        bizMsg.xxModeNm23Txt_02.clear();
        bizMsg.xxAcctSrchStsCd_02.no(0).setValue(STATUS_CD_ACTIVE);
        bizMsg.xxAcctSrchStsCd_02.no(1).setValue(STATUS_CD_ALL);
        bizMsg.xxModeNm23Txt_02.no(0).setValue(STATUS_NM_ACTIVE);
        bizMsg.xxModeNm23Txt_02.no(1).setValue(STATUS_NM_ALL);

        // 02/17/2016 CSA #2943 Add Start 
        // Category
        bizMsg.dsAcctTpCd_DP.clear();
        bizMsg.dsAcctTpCd_01.clear();
        bizMsg.dsAcctTpNm_01.clear();
        ZYPCodeDataUtil.createPulldownList(DS_ACCT_TP.class, bizMsg.dsAcctTpCd_01, bizMsg.dsAcctTpNm_01);
        // 02/17/2016 CSA #2943 Add Start 
        
        // State
        bizMsg.stCd_DP.clear();
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

        // 02/17/2016 CSA #2943 Del Start 
//        // Account Group Name
//        bizMsg.dsAcctGrpCd_DP.clear();
//        bizMsg.dsAcctGrpCd_01.clear();
//        bizMsg.dsAcctGrpNm_01.clear();
//        ZYPCodeDataUtil.createPulldownList(DS_ACCT_GRP.class, bizMsg.dsAcctGrpCd_01, bizMsg.dsAcctGrpNm_01);
        // 02/17/2016 CSA #2943 Del Start 

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
        bizMsg.xxAcctSrchDplyHrchCd_D3.clear();
        bizMsg.xxAcctSrchDplyHrchCd_03.clear();
        bizMsg.xxModeNm23Txt_03.clear();
        bizMsg.xxAcctSrchDplyHrchCd_03.no(0).setValue(DISP_HRCH_ACCT_CD_ALL);
        bizMsg.xxAcctSrchDplyHrchCd_03.no(1).setValue(DISP_HRCH_ACCT_CD_BILL);
        bizMsg.xxAcctSrchDplyHrchCd_03.no(2).setValue(DISP_HRCH_ACCT_CD_SHIP);
        bizMsg.xxModeNm23Txt_03.no(0).setValue(DISP_HRCH_ACCT_NM_ALL);
        bizMsg.xxModeNm23Txt_03.no(1).setValue(DISP_HRCH_ACCT_NM_BILL);
        bizMsg.xxModeNm23Txt_03.no(2).setValue(DISP_HRCH_ACCT_NM_SHIP);

        // Display Related Account#
        bizMsg.xxAcctSrchDplyRelnCd_D4.clear();
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
    /**
     * Search
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NMAL6710Scrn00_Search_New(NMAL6710CMsg cMsg, NMAL6710SMsg sMsg) {
        // Input Check
        if (!NMAL6710CommonLogic.checkSearchCondition(cMsg)) {
            return;
        }

        if (ZYPCommonFunc.hasValue(cMsg.xxAcctSrchModeCd_D1) && SEARCH_MODE_CD_HRCH.equals(cMsg.xxAcctSrchModeCd_D1.getValue())) {
            searchDisplayAccountHierarchy(cMsg, sMsg);
        } else {
            searchQuickLookup(cMsg, sMsg);
        }
    }
    
    // QC#6189
    private void doProcess_NMAL6710Scrn00_GoToAcct(NMAL6710CMsg cMsg, NMAL6710SMsg sMsg) {
        searchDisplayAccountHierarchy(cMsg, sMsg);
    }
    
    private void searchDisplayAccountHierarchy(NMAL6710CMsg cMsg, NMAL6710SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        int idx = 0;

        // QC#6189
        clearQuickLookupResult(cMsg, sMsg);

        S21SsmEZDResult ssmResult = null;
        ssmResult = NMAL6710CommonLogic.getDsAccountList(cMsg, sMsg, getGlobalCompanyCode());
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            // QC#6189
            if(hasMultipleAccount(resultList)){
                cMsg.xxDplyTab.setValue(NMAL6710Constant.TAB_NM_HRCH_QUICK);

                setHierarchyQuickLookupResult(cMsg, sMsg, ssmResult);
                
                cMsg.setMessageInfo(NMAM8610W);
                return;

            } else {
                cMsg.xxDplyTab.setValue(NMAL6710Constant.TAB_NM_HRCH);
                
                resultList = getFirstRecord(resultList);
            }
            
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                String parentDsAcctNum = null;
                String tagetDsAcctNum = (String) map.get("DS_ACCT_NUM");
                List resultMostParentList = NMAL6710CommonLogic.getMostParentNode(cMsg, glblCmpyCd, tagetDsAcctNum);
                // Normal case
                if (ZYPCommonFunc.hasValue(cMsg.xxAcctSrchDplyRelnCd_D4)
                        && resultMostParentList != null && resultMostParentList.size() == 1) {
                    Map mapMost = (Map) resultMostParentList.get(0);
                    parentDsAcctNum = (String) mapMost.get("DS_ACCT_NUM");
                } else {
                    parentDsAcctNum = tagetDsAcctNum;
                }
                List resultTreeList = NMAL6710CommonLogic.getTreeListParent(cMsg, glblCmpyCd, parentDsAcctNum, tagetDsAcctNum);
                List resultTreeListLeaseReln = NMAL6710CommonLogic.getTreeListLeaseReln(cMsg, glblCmpyCd, parentDsAcctNum, tagetDsAcctNum);

                if (resultTreeList != null && resultTreeList.size() > 0) {

                    for (int j = 0; j < resultTreeList.size(); j++) {
                        if (idx >= cMsg.A.length()) {
                            break;
                        }
                        Map mapTree = (Map) resultTreeList.get(j);
                        if (!chkLeaseOnly(cMsg, mapTree, tagetDsAcctNum)) {
                            break;
                        }
                        
                        // QC#4506
                        // setTreeView(cMsg.A.no(idx), mapTree);
                        // idx++;
                        List resultTreeListLoc = NMAL6710CommonLogic.getTreeListLocation(cMsg, glblCmpyCd, (String) mapTree.get("DS_ACCT_NUM"), tagetDsAcctNum);
                        if (resultTreeListLoc != null && resultTreeListLoc.size() > 0) {
                            int avalLocIndex = 0;
                            for (int k = 0; k < resultTreeListLoc.size(); k++) {
                                if (idx >= cMsg.A.length()) {
                                    break;
                                }
                                Map mapTreeLoc = (Map) resultTreeListLoc.get(k);
                                if (chkSetSkip(cMsg, mapTree, mapTreeLoc, cMsg.xxAcctSrchDplyHrchCd_D3.getValue(), cMsg.xxAcctSrchDplyRelnCd_D4.getValue())) {
                                    // QC#4506
                                    // setTreeViewLoc(cMsg.A.no(idx), mapTree, mapTreeLoc);
                                    // QC#5890
                                    setTreeViewLoc(cMsg.A.no(idx), mapTree, mapTreeLoc, avalLocIndex, cMsg.xxAcctSrchDplyRelnCd_D4.getValue());
                                    idx++;
                                    avalLocIndex++;
                                }
                            }
                        } else {
                            // QC#4506
                            if (idx >= cMsg.A.length()) {
                                break;
                            }
                            // 2016/12/05 CSA-QC#14340 Mod Start
                            if (setTreeView(cMsg.A.no(idx), mapTree, cMsg.xxAcctSrchDplyHrchCd_D3.getValue())) {
                                idx++;
                            }
                            // setTreeView(cMsg.A.no(idx), mapTree);
                            // idx++;
                            // 2016/12/05 CSA-QC#14340 Mod End
                        }
                        
                        // QC#7932
                        String treeLowLvlAcctNum = getTreeLowestLevelFolderAccountNumber(mapTree);
                        if(ZYPCommonFunc.hasValue(treeLowLvlAcctNum)){
                            if(tagetDsAcctNum.equals(treeLowLvlAcctNum)){
                                if (ZYPCommonFunc.hasValue(cMsg.xxAcctSrchDplyRelnCd_D4) && resultTreeListLeaseReln != null && resultTreeListLeaseReln.size() > 0) {
                                    for (int l = 0; l < resultTreeListLeaseReln.size(); l++) {
                                        if (idx >= cMsg.A.length()) {
                                            break;
                                        }
                                        Map mapTreeReln = (Map) resultTreeListLeaseReln.get(l);
                                        String currentDsAcctNum = (String)mapTreeReln.get("DSACCTNUM_T2");
                                        copyTreeFolderStructure(mapTree, mapTreeReln, tagetDsAcctNum, currentDsAcctNum);
                                        List resultTreeListLocReln = NMAL6710CommonLogic.getTreeListLocation(cMsg, glblCmpyCd, (String) mapTreeReln.get("DS_ACCT_NUM"), tagetDsAcctNum);
                                        if (resultTreeListLocReln != null && resultTreeListLocReln.size() > 0) {
                                            int avalLocIndex = 0;
                                            for (int m = 0; m < resultTreeListLocReln.size(); m++) {
                                                if (idx >= cMsg.A.length()) {
                                                    break;
                                                }
                                                Map mapTreeLocReln = (Map) resultTreeListLocReln.get(m);
                                                if (chkSetSkip(cMsg, mapTreeReln, mapTreeLocReln, cMsg.xxAcctSrchDplyHrchCd_D3.getValue(), cMsg.xxAcctSrchDplyRelnCd_D4.getValue())) {
                                                    setTreeViewLoc(cMsg.A.no(idx), mapTreeReln, mapTreeLocReln, avalLocIndex, cMsg.xxAcctSrchDplyRelnCd_D4.getValue());
                                                    idx++;
                                                    avalLocIndex++;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
//                if (ZYPCommonFunc.hasValue(cMsg.xxAcctSrchDplyRelnCd_D4)
//                        && resultTreeListLeaseReln != null && resultTreeListLeaseReln.size() > 0) {
//                    for (int j = 0; j < resultTreeListLeaseReln.size(); j++) {
//                        if (idx >= cMsg.A.length()) {
//                            break;
//                        }
//                        Map mapTree = (Map) resultTreeListLeaseReln.get(j);
//                        
//                        // QC#4506
//                        // setTreeView(cMsg.A.no(idx), mapTree);
//                        // idx++;
//                        List resultTreeListLoc = NMAL6710CommonLogic.getTreeListLocation(cMsg, glblCmpyCd, (String) mapTree.get("DS_ACCT_NUM"), tagetDsAcctNum);
//                        if (resultTreeListLoc != null && resultTreeListLoc.size() > 0) {
//                            int avalLocIndex = 0;
//                            for (int k = 0; k < resultTreeListLoc.size(); k++) {
//                                if (idx >= cMsg.A.length()) {
//                                    break;
//                                }
//                                Map mapTreeLoc = (Map) resultTreeListLoc.get(k);
//                                if (chkSetSkip(cMsg, mapTree, mapTreeLoc)) {
//                                    // QC#4506
//                                    // setTreeViewLoc(cMsg.A.no(idx), mapTree, mapTreeLoc);
//                                    // QC5890
//                                    setTreeViewLoc(cMsg.A.no(idx), mapTree, mapTreeLoc, avalLocIndex);
//                                    idx++;
//                                    avalLocIndex++;
//                                }
//                            }
//                        } else {
//                            // QC#5890
//                            // // QC#4506
//                            // setTreeView(cMsg.A.no(idx), mapTree);
//                            // idx++;
//                        }
//                    }
//                }
            }
            cMsg.A.setValidCount(idx);
        }
        if (idx == 0) {
            cMsg.setMessageInfo("NZZM0000E");
        } else if (idx >= cMsg.A.length() - 1) {
            cMsg.setMessageInfo("ZZZM9002W");
        }
//        NMAL6710_ACMsg acMsg = cMsg.A.no(0);
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T1, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T2, "0000069");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T3, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T4, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T5, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T6, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T7, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T8, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T9, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_TA, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_TB, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.locNum_TC, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T, "");
//
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNm_T, "QC#940 ACCT4");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctRelnTpNm_T, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.xxAllLineAddr_T, "QC#940 LOC11    QC#940 LOC11 AK" );
//        ZYPEZDItemValueSetter.setValue(acMsg.locNum_T, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.xxYesNoCd_T, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctTpNm_T, "CUSTOMER");
//        ZYPEZDItemValueSetter.setValue(acMsg.xxCtlNm_T, "");
//
//        ZYPEZDItemValueSetter.setValue(acMsg.relnDsAcctNum_T, "0000069");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_TO, "0000063");
//        ZYPEZDItemValueSetter.setValue(acMsg.billToCustCd_T, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.shipToCustCd_T, "");
//        
//        acMsg = cMsg.A.no(1);
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T1, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T2, "0000069");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T3, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T4, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T5, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T6, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T7, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T8, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T9, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_TA, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_TB, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.locNum_TC, "0000078");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T, "");
//
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNm_T, "QC#940 ACCT4");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctRelnTpNm_T, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.xxAllLineAddr_T, "QC#940 LOC11    QC#940 LOC11 AK" );
//        ZYPEZDItemValueSetter.setValue(acMsg.locNum_T, "0000078");
//        ZYPEZDItemValueSetter.setValue(acMsg.xxYesNoCd_T, "");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctTpNm_T, "CUSTOMER");
//        ZYPEZDItemValueSetter.setValue(acMsg.xxCtlNm_T, "");
//
//        ZYPEZDItemValueSetter.setValue(acMsg.relnDsAcctNum_T, "0000069");
//        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_TO, "0000063");
//        ZYPEZDItemValueSetter.setValue(acMsg.billToCustCd_T, "0000064");
//        ZYPEZDItemValueSetter.setValue(acMsg.shipToCustCd_T, "0000047");
//        
//        cMsg.A.setValidCount(2);
    }
    
    // QC#6189
    private boolean hasMultipleAccount(List<Map<String, Object>> resultList){
        List<String> listDsAcctNum = new ArrayList<String>();
        for(Map<String, Object> map : resultList){
            String dsAcctNum = (String)map.get("DS_ACCT_NUM");
            if(!listDsAcctNum.contains(dsAcctNum)){
                listDsAcctNum.add(dsAcctNum);
                if(listDsAcctNum.size() > 1){
                    return true;
                }
            }
        }
        return false;
    }
    
    private List<Map<String, Object>> getFirstRecord(List<Map<String, Object>> resultList){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if(resultList.size() > 0){
            list.add(resultList.get(0));
        }
        return list;
    }
    
    // QC#7932
    private String getTreeLowestLevelFolderAccountNumber(Map mapTree) {
        List<String> list = new ArrayList<String>();
        list.add((String) mapTree.get("DSACCTNUM_T1"));
        list.add((String) mapTree.get("DSACCTNUM_T2"));
        list.add((String) mapTree.get("DSACCTNUM_T3"));
        list.add((String) mapTree.get("DSACCTNUM_T4"));
        list.add((String) mapTree.get("DSACCTNUM_T5"));
        list.add((String) mapTree.get("DSACCTNUM_T6"));
        list.add((String) mapTree.get("DSACCTNUM_T7"));
        list.add((String) mapTree.get("DSACCTNUM_T8"));
        list.add((String) mapTree.get("DSACCTNUM_T9"));
        list.add((String) mapTree.get("DSACCTNUM_TA"));
        for (int i = list.size() - 1; i >= 0; i--) {
            String dsAcctNum = list.get(i);
            if (ZYPCommonFunc.hasValue(dsAcctNum)) {
                return dsAcctNum;
            }
        }
        return null;
    }

    private void copyTreeFolderStructure(Map mapTreeFrom, Map mapTreeTo, String tagetDsAcctNum, String currentDsAcctNum) {
        List<String> list = new ArrayList<String>();
        list.add("DSACCTNUM_T1");
        list.add("DSACCTNUM_T2");
        list.add("DSACCTNUM_T3");
        list.add("DSACCTNUM_T4");
        list.add("DSACCTNUM_T5");
        list.add("DSACCTNUM_T6");
        list.add("DSACCTNUM_T7");
        list.add("DSACCTNUM_T8");
        list.add("DSACCTNUM_T9");
        list.add("DSACCTNUM_TA");
        for (String key : list) {
            String acctNum = (String) mapTreeFrom.get(key);
            if (tagetDsAcctNum.equals(acctNum)) {
                mapTreeTo.put(key, currentDsAcctNum);
            } else {
                mapTreeTo.put(key, acctNum);
            }
        }
    }

    private void doProcess_NMAL6710_Download(NMAL6710CMsg bizMsg, NMAL6710SMsg glblMsg) {

        NMAL6710F00FMsg fMsg = new NMAL6710F00FMsg();

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(TMPL_FILE_BASE), TMPL_FILE_EXTENSION);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        /** CSV Header */
        final String[] csvHeader = new String[] {//
        "Acct#" //
                , "Relation"//
                , "Acct Name"//
                , "Address" //
                , "Loc#"//
                , "Primary"//
                , "Type"//
                , "Relationship(s)"//
                // QC#3778
                , "Parent Acct#"//
                , "Top Acct#"//
                , "Bill To"//
                , "Ship To" };
        csvOutFile.writeHeader(csvHeader);

        // QC#21196 Mod
        ResultSet rs = null;
        PreparedStatement ps = null;
        ResultSet rsRelation = null;
        PreparedStatement psRelation = null;

        try {
            String befAccountNum = "";
            ps = NMAL6710Query.getInstance().getDsAccountListForDownload(bizMsg, glblMsg, getGlobalCompanyCode(), rs, ps);
            rs = ps.executeQuery();
            if (!rs.next()) {
                // Error : No search results found.
                bizMsg.setMessageInfo("NZZM0000E");
            } else {
                int i = 0;
                do {

                    if (MAX_DL_SIZE <= i) {
                        // Mod Start 2018/02/23 QC#22570
                        //bizMsg.setMessageInfo("NZZM0001W");
                        bizMsg.setMessageInfo(NMAM8676W, new String[] {String.valueOf(MAX_DL_SIZE) });
                        // Mod End 2018/02/23 QC#22570
                        break;
                    }

                    if (ZYPCommonFunc.hasValue(bizMsg.xxAcctSrchDplyRelnCd_D4)) {
                        String orgAccountNum = rs.getString("ORIG_ACCT_NUM");

                        if (!befAccountNum.equals(orgAccountNum)) {
                            psRelation = NMAL6710Query.getInstance().getRelatedAccountListForDownload(bizMsg, glblMsg, orgAccountNum, rsRelation, psRelation);
                            rsRelation = psRelation.executeQuery();
                            int j = 0;

                            while (rsRelation.next()) {
                                if (MAX_DL_SIZE <= j) {
                                    // Mod Start 2018/02/23 QC#22570
                                    //bizMsg.setMessageInfo("NZZM0001W");
                                    bizMsg.setMessageInfo(NMAM8676W, new String[] {String.valueOf(MAX_DL_SIZE) });
                                    // Mod End 2018/02/23 QC#22570
                                    break;
                                }
                                // set fMsg.
                                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum_A, rsRelation.getString("DS_ACCT_NUM"));
                                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctRelnTpNm_A, NMAL6710CommonLogic.getDsAcctRelnTpNm(rsRelation.getString("DS_ACCT_RELN_TP_CD")));
                                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A, rsRelation.getString("DS_ACCT_NM"));
                                ZYPEZDItemValueSetter.setValue(fMsg.xxAllLineAddr_A, rsRelation.getString("SHIP_FROM_FULL_CMPY_ADDR"));
                                ZYPEZDItemValueSetter.setValue(fMsg.locNum_A, rsRelation.getString("LOC_NUM"));
                                ZYPEZDItemValueSetter.setValue(fMsg.xxYesNoCd_A, rsRelation.getString("XX_YES_NO_CD"));
                                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctTpNm_A, rsRelation.getString("DS_ACCT_TP_NM"));
                                ZYPEZDItemValueSetter.setValue(fMsg.xxCtlNm_A, //
                                        NMAL6710CommonLogic.getRelationship(rsRelation.getString("BILL_TO_CUST_CD"), rsRelation.getString("SHIP_TO_CUST_CD")));
                                ZYPEZDItemValueSetter.setValue(fMsg.relnDsAcctNum_A, rsRelation.getString("RELN_DS_ACCT_NUM"));
                                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum_A2, rsRelation.getString("ORIG_ACCT_NUM"));
                                ZYPEZDItemValueSetter.setValue(fMsg.billToCustCd_A, rsRelation.getString("BILL_TO_CUST_CD"));
                                ZYPEZDItemValueSetter.setValue(fMsg.shipToCustCd_A, rsRelation.getString("SHIP_TO_CUST_CD"));

                                j++;
                                csvOutFile.write();
                            }
                            i = i + j;
                            befAccountNum = orgAccountNum;
                            S21SsmLowLevelCodingClient.closeResource(psRelation, rsRelation);
                        }
                    } else {
                        // set fMsg.
                        ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum_A, rs.getString("DS_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(fMsg.dsAcctRelnTpNm_A, NMAL6710CommonLogic.getDsAcctRelnTpNm(rs.getString("DS_ACCT_RELN_TP_CD")));
                        ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A, rs.getString("DS_ACCT_NM"));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxAllLineAddr_A, rs.getString("SHIP_FROM_FULL_CMPY_ADDR"));
                        ZYPEZDItemValueSetter.setValue(fMsg.locNum_A, rs.getString("LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxYesNoCd_A, rs.getString("XX_YES_NO_CD"));
                        ZYPEZDItemValueSetter.setValue(fMsg.dsAcctTpNm_A, rs.getString("DS_ACCT_TP_NM"));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxCtlNm_A, //
                                NMAL6710CommonLogic.getRelationship(rs.getString("BILL_TO_CUST_CD"), rs.getString("SHIP_TO_CUST_CD")));
                        ZYPEZDItemValueSetter.setValue(fMsg.relnDsAcctNum_A, rs.getString("RELN_DS_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum_A2, rs.getString("ORIG_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(fMsg.billToCustCd_A, rs.getString("BILL_TO_CUST_CD"));
                        ZYPEZDItemValueSetter.setValue(fMsg.shipToCustCd_A, rs.getString("SHIP_TO_CUST_CD"));

                        csvOutFile.write();
                    }

                    i++;
                } while (rs.next());
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
            if (rsRelation != null) {
                S21SsmLowLevelCodingClient.closeResource(psRelation, rsRelation);
            }
        }

        csvOutFile.close();
    }
    
    // QC#6189
    private void searchQuickLookup(EZDCMsg cMsg, EZDSMsg sMsg) {
        NMAL6710CMsg bizMsg = (NMAL6710CMsg) cMsg;
        NMAL6710SMsg sharedMsg = (NMAL6710SMsg) sMsg;

        clearQuickLookupResult(bizMsg, sharedMsg);
        S21SsmEZDResult ssmResult = NMAL6710CommonLogic.getDsAccountList(bizMsg, sharedMsg, getGlobalCompanyCode());
        setQuickLookupResult(bizMsg, sharedMsg, ssmResult);
    }

    private void clearQuickLookupResult(NMAL6710CMsg bizMsg, NMAL6710SMsg sharedMsg) {
        bizMsg.C.clear();
        bizMsg.C.setValidCount(0);
        sharedMsg.C.clear();
        sharedMsg.C.setValidCount(0);
        NMAL6710CommonLogic.clearPageNum(bizMsg);
        bizMsg.xxRadioBtn.clear();
    }
    
    private void setQuickLookupResult(NMAL6710CMsg bizMsg, NMAL6710SMsg sharedMsg, S21SsmEZDResult ssmResult) {
        int queryResCnt = 0;
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            queryResCnt = ssmResult.getQueryResultCount();
            if (!NMAL6710CommonLogic.checkMaxCount(bizMsg, queryResCnt, sharedMsg.C.length())) {
                queryResCnt = sharedMsg.C.length();
            }
            if (queryResCnt <= sharedMsg.C.length() && ZYPCommonFunc.hasValue(bizMsg.xxAcctSrchDplyRelnCd_D4)) {

                // get Include Related Accounts
                NMAL6710CommonLogic.getInclRelnAcct(bizMsg, sharedMsg, queryResCnt, resultList);
                queryResCnt = Integer.valueOf(sharedMsg.C.getValidCount()).intValue();

            } else {
                int idx = 0;
                for (int i = 0; i < resultList.size(); i++) {
                    if (i >= sharedMsg.C.length()) {
                        break;
                    }
                    Map map = (Map) resultList.get(i);
                    NMAL6710CommonLogic.setAccountInfo(bizMsg, sharedMsg.C.no(i), map);
                    idx++;
                }
                sharedMsg.C.setValidCount(idx);
            }
        }

        if (ssmResult.isCodeNormal()) {
        // 2016/11/30 CSA-QC#14340 Add start
            if (queryResCnt == 0) {
                bizMsg.setMessageInfo("NZZM0000E");
            } else {
        // 2016/11/30 CSA-QC#14340 Add End
                if (queryResCnt > sharedMsg.C.length()) {
                    bizMsg.setMessageInfo("ZZZM9002W");
                }
                NMAL6710CommonLogic.copyFromSMsgOntoCmsg(bizMsg, sharedMsg);
            }
        } else {
            bizMsg.setMessageInfo("NZZM0000E");
        }
        
        // QC#6188
        if(bizMsg.C.getValidCount() > 0){
            bizMsg.xxRadioBtn.setValue(0);
        }
    }
    
    private void setHierarchyQuickLookupResult(NMAL6710CMsg bizMsg, NMAL6710SMsg sharedMsg, S21SsmEZDResult ssmResult) {
        int queryResCnt = 0;
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            queryResCnt = ssmResult.getQueryResultCount();
            if (!NMAL6710CommonLogic.checkMaxCount(bizMsg, queryResCnt, sharedMsg.C.length())) {
                queryResCnt = sharedMsg.C.length();
            }
           
            int idx = 0;
            for (int i = 0; i < resultList.size(); i++) {
                if (i >= sharedMsg.C.length()) {
                    break;
                }
                Map map = (Map) resultList.get(i);
                NMAL6710CommonLogic.setAccountInfo(bizMsg, sharedMsg.C.no(i), map);
                // clear origin account# column 
                sharedMsg.C.no(i).dsAcctNum_C2.clear();

                idx++;
            }
            sharedMsg.C.setValidCount(idx);
        }
        if (ssmResult.isCodeNormal()) {
            NMAL6710CommonLogic.copyFromSMsgOntoCmsg(bizMsg, sharedMsg);
        }
        if(bizMsg.C.getValidCount() > 0){
            bizMsg.xxRadioBtn.setValue(0);
        }
    }
    
    
    /**
     * Method name: doProcess_NMAL6710Scrn00_PagePrev <dd>The method
     * explanation: PagePrev. <dd>Remarks:
     * @param cMsg NMAL6710CMsg
     * @param sMsg NMAL6710SMsg
     */
    private void doProcess_NMAL6710Scrn00_PagePrev(NMAL6710CMsg cMsg, NMAL6710SMsg sMsg) {
        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.C);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.C.length() - 1);
        cMsg.xxPageShowToNum.clear();

        NMAL6710CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

    }
    /**
     * Method name: doProcess_NMAL6710Scrn00_PageNext <dd>The method
     * explanation: PagePrev. <dd>Remarks:
     * @param cMsg NMAL6710CMsg
     * @param sMsg NMAL6710SMsg
     */
    private void doProcess_NMAL6710Scrn00_PageNext(NMAL6710CMsg cMsg, NMAL6710SMsg sMsg) {
        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.C);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt());
        cMsg.xxPageShowToNum.clear();

        NMAL6710CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * Method name: doProcess_NMAL6710Scrn00_PageJump <dd>The method
     * explanation: PagePrev. <dd>Remarks:
     * @param cMsg NMAL6710CMsg
     * @param sMsg NMAL6710SMsg
     */
    private void doProcess_NMAL6710Scrn00_PageJump(NMAL6710CMsg cMsg, NMAL6710SMsg sMsg) {
        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.C);
        cMsg.xxPageShowFromNum.setValue(cMsg.C.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1));
        cMsg.xxPageShowToNum.clear();

        NMAL6710CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * OnChangeSavedSearchOption
     * @param cMsg NMAL6710CMsg
     * @param sMsg NMAL6710SMsg
     */
    private void doProcess_NMAL6710Scrn00_OnChangeSavedSearchOption(NMAL6710CMsg bizMsg, NMAL6710SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            NMAL6710CommonLogic.callNszc0330forSearchOption(bizMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    private boolean chkLeaseOnly(NMAL6710CMsg cMsg, Map mapTree, String tagetDsAcctNum) {

        if (DISP_RELN_ACCT_CD_LEASE_ACCT.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())
                || DISP_RELN_ACCT_CD_LEASE_BILL.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())) {
            String parentDsAcctNum = (String) mapTree.get("P_DS_ACCT_NUM");
            String dsAcctRelnTpCd = (String) mapTree.get("DS_ACCT_RELN_TP_CD");
            if (tagetDsAcctNum.equals(parentDsAcctNum) && DS_ACCT_RELN_TP.PARENT_ACCOUNT.equals(dsAcctRelnTpCd)) {
                return false;
            }
        }
        return true;
    }
    /**
     * chkSetSkip
     * @param cMsg NMAL6710CMsg
     * @param mapTree Map
     * @param mapTreeLoc Map
     * @return boolean
     */
    private boolean chkSetSkip(NMAL6710CMsg cMsg, Map mapTree, Map mapTreeLoc, String dplyHrchAcct, String dplyRelnAcct) {

        String dsAcctRelnBillToFlg = (String) mapTree.get("DS_ACCT_RELN_BILL_TO_FLG");
        if (!ZYPCommonFunc.hasValue(dsAcctRelnBillToFlg)) {
            dsAcctRelnBillToFlg = ZYPConstant.FLG_ON_Y;
        }
        String dsAcctRelnShipToFlg = (String) mapTree.get("DS_ACCT_RELN_SHIP_TO_FLG");
        if (!ZYPCommonFunc.hasValue(dsAcctRelnShipToFlg)) {
            dsAcctRelnShipToFlg = ZYPConstant.FLG_ON_Y;
        }
        String billToCustCd = (String) mapTreeLoc.get("BILL_TO_CUST_CD");
        String shipToCustCd = (String) mapTreeLoc.get("SHIP_TO_CUST_CD");

        // 2016/12/05 CSA-QC#14340 Add Start
        if (DISP_HRCH_ACCT_CD_BILL.equals(dplyHrchAcct)) {
            if (!ZYPCommonFunc.hasValue(billToCustCd)) {
                return false;
            }
        } else if (DISP_HRCH_ACCT_CD_SHIP.equals(dplyHrchAcct)) {
            if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue((String) mapTree.get("DS_ACCT_RELN_TP_CD"))) {
        // 2016/12/05 CSA-QC#14340 Add End
            // Bill To's Only, Lease  Bill To's Only
            if (DISP_RELN_ACCT_CD_BILL.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())
                    || DISP_RELN_ACCT_CD_LEASE_BILL.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())) {
                if (!(ZYPConstant.FLG_ON_Y.equals(dsAcctRelnBillToFlg) && ZYPCommonFunc.hasValue(billToCustCd))) {
                    return false;
                }
            }
            // Ship To's Only
            if (DISP_RELN_ACCT_CD_SHIP.equals(cMsg.xxAcctSrchDplyRelnCd_D4.getValue())) {
                if (!(ZYPConstant.FLG_ON_Y.equals(dsAcctRelnShipToFlg) && ZYPCommonFunc.hasValue(shipToCustCd))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * setTreeView
     * @param acMsg NMAL6710_ACMsg
     * @param mapTree Map
     */
    private boolean setTreeView(NMAL6710_ACMsg acMsg, Map mapTree, String dplyHrchAcct) {
        // 2016/12/05 CSA-QC#14340 Add Start
        if (DISP_HRCH_ACCT_CD_BILL.equals(dplyHrchAcct)) {
            if (!ZYPCommonFunc.hasValue((String) mapTree.get("BILL_TO_CUST_CD"))) {
                return false;
            }
        } else if (DISP_HRCH_ACCT_CD_SHIP.equals(dplyHrchAcct)) {
            if (!ZYPCommonFunc.hasValue((String) mapTree.get("SHIP_TO_CUST_CD"))) {
                return false;
            }
        }
        // 2016/12/05 CSA-QC#14340 Add End
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T1, "");
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T2, (String) mapTree.get("DSACCTNUM_T1"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T3, (String) mapTree.get("DSACCTNUM_T2"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T4, (String) mapTree.get("DSACCTNUM_T3"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T5, (String) mapTree.get("DSACCTNUM_T4"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T6, (String) mapTree.get("DSACCTNUM_T5"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T7, (String) mapTree.get("DSACCTNUM_T6"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T8, (String) mapTree.get("DSACCTNUM_T7"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T9, (String) mapTree.get("DSACCTNUM_T8"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_TA, (String) mapTree.get("DSACCTNUM_T9"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_TB, (String) mapTree.get("DSACCTNUM_TA"));
//        ZYPEZDItemValueSetter.setValue(acMsg.locNum_TC, (String) mapTree.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T, (String) mapTree.get("DS_ACCT_NUM"));

        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNm_T, (String) mapTree.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctRelnTpNm_T, NMAL6710CommonLogic.getDsAcctRelnTpNm((String) mapTree.get("DS_ACCT_RELN_TP_CD")));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNm_T, (String) mapTree.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(acMsg.xxAllLineAddr_T, (String) mapTree.get("SHIP_FROM_FULL_CMPY_ADDR"));
        ZYPEZDItemValueSetter.setValue(acMsg.locNum_T, (String) mapTree.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(acMsg.xxYesNoCd_T, (String) mapTree.get("XX_YES_NO_CD"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctTpNm_T, (String) mapTree.get("DS_ACCT_TP_NM"));
        ZYPEZDItemValueSetter.setValue(acMsg.xxCtlNm_T, NMAL6710CommonLogic.getRelationship((String) mapTree.get("BILL_TO_CUST_CD"), (String) mapTree.get("SHIP_TO_CUST_CD")));

        ZYPEZDItemValueSetter.setValue(acMsg.relnDsAcctNum_T, (String) mapTree.get("RELN_DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_TO, (String) mapTree.get("ORIG_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(acMsg.billToCustCd_T, (String) mapTree.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(acMsg.shipToCustCd_T, (String) mapTree.get("SHIP_TO_CUST_CD"));

        // QC#3290
        ZYPEZDItemValueSetter.setValue(acMsg.xxRgtnStsTxt_A1, NMAL6710CommonLogic.getStatusDescriptionByFlag((String) mapTree.get("DS_ACCT_ACTV_CUST_FLG")));

        return true;
    }

    // QC#4506
    /**
     * setTreeView
     * @param acMsg NMAL6710_ACMsg
     * @param mapTree Map
     */
    // private void setTreeViewLoc(NMAL6710_ACMsg acMsg, Map mapTree, Map mapTreeLoc) {
    private void setTreeViewLoc(NMAL6710_ACMsg acMsg, Map mapTree, Map mapTreeLoc, int locSortNumInAcct, String dplyRelnAcct) {
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T1, "");
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T2, (String) mapTree.get("DSACCTNUM_T1"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T3, (String) mapTree.get("DSACCTNUM_T2"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T4, (String) mapTree.get("DSACCTNUM_T3"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T5, (String) mapTree.get("DSACCTNUM_T4"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T6, (String) mapTree.get("DSACCTNUM_T5"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T7, (String) mapTree.get("DSACCTNUM_T6"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T8, (String) mapTree.get("DSACCTNUM_T7"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T9, (String) mapTree.get("DSACCTNUM_T8"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_TA, (String) mapTree.get("DSACCTNUM_T9"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_TB, (String) mapTree.get("DSACCTNUM_TA"));
        // QC#4506
        // ZYPEZDItemValueSetter.setValue(acMsg.locNum_TC, (String) mapTreeLoc.get("LOC_NUM"));
        if(locSortNumInAcct > 0){
            ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_TL, (String) mapTreeLoc.get("DS_ACCT_NUM"));
        }
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_T, (String) mapTreeLoc.get("DS_ACCT_NUM"));

        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNm_T, (String) mapTreeLoc.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctRelnTpNm_T, NMAL6710CommonLogic.getDsAcctRelnTpNm((String) mapTree.get("DS_ACCT_RELN_TP_CD")));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNm_T, (String) mapTreeLoc.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctTpNm_T, (String) mapTreeLoc.get("DS_ACCT_TP_NM"));
        ZYPEZDItemValueSetter.setValue(acMsg.xxCtlNm_T, NMAL6710CommonLogic.getRelationship((String) mapTreeLoc.get("BILL_TO_CUST_CD"), (String) mapTreeLoc.get("SHIP_TO_CUST_CD")));

        ZYPEZDItemValueSetter.setValue(acMsg.relnDsAcctNum_T, (String) mapTreeLoc.get("RELN_DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsAcctNum_TO, (String) mapTreeLoc.get("ORIG_ACCT_NUM"));

        // 2016/12/05 CSA-QC#14340 Add Start
        if (!DISP_RELN_ACCT_CD_ACCT.equals(dplyRelnAcct)) {
        // 2016/12/05 CSA-QC#14340 Add End
            ZYPEZDItemValueSetter.setValue(acMsg.xxAllLineAddr_T, (String) mapTreeLoc.get("SHIP_FROM_FULL_CMPY_ADDR"));
            ZYPEZDItemValueSetter.setValue(acMsg.xxYesNoCd_T, (String) mapTreeLoc.get("XX_YES_NO_CD"));
            ZYPEZDItemValueSetter.setValue(acMsg.billToCustCd_T, (String) mapTreeLoc.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(acMsg.shipToCustCd_T, (String) mapTreeLoc.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(acMsg.locNum_T, (String) mapTreeLoc.get("LOC_NUM"));
            if (ZYPCommonFunc.hasValue(acMsg.locNum_T)) {
                ZYPEZDItemValueSetter.setValue(acMsg.xxRgtnStsTxt_A2, NMAL6710CommonLogic.getStatusDescriptionByCode((String) mapTreeLoc.get("PTY_RGTN_STS_CD")));
                ZYPEZDItemValueSetter.setValue(acMsg.xxRgtnStsTxt_A5, NMAL6710CommonLogic.getStatusDescriptionByCode((String) mapTreeLoc.get("LOC_RGTN_STS_CD"))); // QC#15431 Add
            }
            if (ZYPCommonFunc.hasValue(acMsg.billToCustCd_T)) {
                ZYPEZDItemValueSetter.setValue(acMsg.xxRgtnStsTxt_A3, NMAL6710CommonLogic.getStatusDescriptionByCode((String) mapTreeLoc.get("BILL_RGTN_STS_CD")));
            }
            if (ZYPCommonFunc.hasValue(acMsg.shipToCustCd_T)) {
                ZYPEZDItemValueSetter.setValue(acMsg.xxRgtnStsTxt_A4, NMAL6710CommonLogic.getStatusDescriptionByCode((String) mapTreeLoc.get("SHIP_RGTN_STS_CD")));
            }
        }

        // QC#3290
        ZYPEZDItemValueSetter.setValue(acMsg.xxRgtnStsTxt_A1, NMAL6710CommonLogic.getStatusDescriptionByFlag((String) mapTree.get("DS_ACCT_ACTV_CUST_FLG")));
    }
 
    // 2018/05/15 QC#22715 add start
    private void doProcess_NMAL6710Scrn00_TBLColumnSort(NMAL6710CMsg bizMsg, NMAL6710SMsg glblMsg) {

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
}

