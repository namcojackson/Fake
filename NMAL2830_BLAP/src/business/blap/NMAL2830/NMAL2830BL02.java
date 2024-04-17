/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2830;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL2830.common.NMAL2830CommonLogic;
import business.blap.NMAL2830.constant.NMAL2830Constant;
import business.db.STTMsg;
import business.db.STTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL2830BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   Fujitsu         T.Ogura         Create          N/A
 * 2018/08/25   Fujitsu         T.Aoi           Update          QC#27457
 *</pre>
 */
public class NMAL2830BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2830CMsg bizMsg = (NMAL2830CMsg) cMsg;
            NMAL2830SMsg glblMsg = (NMAL2830SMsg) sMsg;

            if ("NMAL2830_INIT".equals(screenAplID)) {
                doProcess_NMAL2830_INIT(bizMsg, glblMsg);

            } else if ("NMAL2830Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2830Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL2830Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2830Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NMAL2830Scrn00_MergeAllProspectsToThisCustomer".equals(screenAplID)) {
                doProcess_NMAL2830Scrn00_MergeAllProspectsToThisCustomer(bizMsg, glblMsg);

            } else if ("NMAL2830Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NMAL2830Scrn00_OnChangeSavedSearchOption(bizMsg, glblMsg);

            } else if ("NMAL2830Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL2830Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NMAL2830Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL2830Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL2830Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL2830Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NMAL2830Scrn00_RetrieveRequestedMergeTo".equals(screenAplID)) {
                doProcess_NMAL2830Scrn00_RetrieveRequestedMergeTo(bizMsg, glblMsg);

            } else if ("NMAL2830Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL2830Scrn00_Search(bizMsg, glblMsg);

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
    private void doProcess_NMAL2830_INIT(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg) {

        // clear
        clearAll(bizMsg, glblMsg);

        // create Pulldown(Location State)
        // 2018/08/25 QC#27457 Mod Start
        //ZYPCodeDataUtil.createPulldownList(ST.class, bizMsg.stCd_L, bizMsg.stDescTxt_L);
        STTMsg condition = new STTMsg();
        condition.setSQLID("003");
        condition.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        condition.setConditionValue("ctryCd01", CTRY.UNITED_STATES_OF_AMERICA);

        STTMsgArray tMsgArray = (STTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                bizMsg.stCd_L.no(i).setValue(tMsgArray.no(i).stCd.getValue());
                bizMsg.stDescTxt_L.no(i).setValue(tMsgArray.no(i).stDescTxt.getValue());
            }
        }
        // 2018/08/25 QC#27457 Mod End

        // create Pulldown(Display Territory Visibility)
        bizMsg.xxTpCd_DL.clear();
        bizMsg.xxTpNm_DL.clear();
        for (int idx = 0; idx < NMAL2830Constant.DTV_PULLDOWN_CD.length; idx++) {
            bizMsg.xxTpCd_DL.no(idx).setValue(NMAL2830Constant.DTV_PULLDOWN_CD[idx]);
            bizMsg.xxTpNm_DL.no(idx).setValue(NMAL2830Constant.DTV_PULLDOWN_NM[idx]);
        }

        // create Pulldown(Save Search Options)
        NMAL2830CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId());
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2830Scrn00_CMN_Submit(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg) {
        // do nothing
        // Not carried the Search event
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2830Scrn00_CMN_Clear(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg) {
        doProcess_NMAL2830_INIT(bizMsg, glblMsg);
    }

    /**
     * MergeAllProspectsToThisCustomer Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2830Scrn00_MergeAllProspectsToThisCustomer(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg) {

        NMAL2830CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        bizMsg.setCommitSMsg(true);

        // Check Input Row Number(for glblMsg)
        int checkInputRowNum = bizMsg.xxPageShowFromNum.getValueInt() - 1 + bizMsg.xxRowNum_CH.getValueInt();

        if (ZYPCommonFunc.hasValue(glblMsg.A.no(checkInputRowNum).xxChkBox_AM)) {

            // Prospect Row Number(for glblMsg)
            int prospectRowNum = checkInputRowNum;

            do {
                --prospectRowNum;
            } while (!NMAL2830Constant.ROW_TP_PROSPECT.equals(glblMsg.A.no(prospectRowNum).xxRowId_AT.getValue()));

            for (int i = prospectRowNum; i < glblMsg.A.getValidCount(); i++) {

                if (i != prospectRowNum && NMAL2830Constant.ROW_TP_PROSPECT.equals(glblMsg.A.no(i).xxRowId_AT.getValue())) {
                    break;
                }

                if (NMAL2830Constant.ROW_TP_PROSPECT.equals(glblMsg.A.no(i).xxRowId_AT.getValue())) {
                    glblMsg.A.no(i).locNum_M.clear();
                } else if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(glblMsg.A.no(i).xxRowId_AT.getValue())) {

                    if (i != checkInputRowNum) {
                        glblMsg.A.no(i).xxChkBox_AM.clear();
                    }
                    glblMsg.A.no(i).xxChkBox_M.clear();
                }
            }
        }

        NMAL2830CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * OnChangeSavedSearchOption Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2830Scrn00_OnChangeSavedSearchOption(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            NMAL2830CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2830Scrn00_PageJump(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg) {

        NMAL2830CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NMAL2830CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2830Scrn00_PageNext(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg) {

        NMAL2830CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL2830CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2830Scrn00_PagePrev(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg) {

        NMAL2830CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NMAL2830CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * RetrieveRequestedMergeTo Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2830Scrn00_RetrieveRequestedMergeTo(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg) {

        NMAL2830CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        bizMsg.setCommitSMsg(true);

        // Event Row Number(for glblMsg)
        int eventRowNum = bizMsg.xxPageShowFromNum.getValueInt() - 1 + bizMsg.xxRowNum_CH.getValueInt();

        // Prospect Row Number(for glblMsg)
        int prospectRowNum = eventRowNum - 1;

        int checkOnRowNum = -1;
        int multipleCheckCnt = 0;
        boolean errFlg = false;
        int errRow = -1;
        List<EZDSStringItem> errItemList = new ArrayList<EZDSStringItem>();

        // ////////////
        // multiple check
        // ////////////
        for (int i = prospectRowNum; i < glblMsg.A.getValidCount(); i++) {

            if (i != prospectRowNum && NMAL2830Constant.ROW_TP_PROSPECT.equals(glblMsg.A.no(i).xxRowId_AT.getValue())) {
                break;
            }

            if (NMAL2830Constant.ROW_TP_PROSPECT.equals(glblMsg.A.no(i).xxRowId_AT.getValue())) {

                if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).locNum_M)) {
                    ++multipleCheckCnt;
                    errItemList.add(glblMsg.A.no(i).locNum_M);
                    if (errRow == -1) {
                        errRow = i;
                    }
                }
            }

            if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(glblMsg.A.no(i).xxRowId_AT.getValue())) {

                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).xxChkBox_M.getValue())) {
                    ++multipleCheckCnt;
                    errItemList.add(glblMsg.A.no(i).xxChkBox_M);
                    checkOnRowNum = i;
                    if (errRow == -1) {
                        errRow = i;
                    }
                }
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).xxChkBox_AM.getValue())) {
                    ++multipleCheckCnt;
                    errItemList.add(glblMsg.A.no(i).xxChkBox_AM);
                    checkOnRowNum = i;
                    if (errRow == -1) {
                        errRow = i;
                    }
                }
            }
        }
        EZDSStringItem errItem;

        if (multipleCheckCnt == 0) {
            errFlg = true;
            for (int i = prospectRowNum; i < glblMsg.A.getValidCount(); i++) {
                if (i != prospectRowNum && NMAL2830Constant.ROW_TP_PROSPECT.equals(glblMsg.A.no(i).xxRowId_AT.getValue())) {
                    break;
                }
                if (NMAL2830Constant.ROW_TP_PROSPECT.equals(glblMsg.A.no(i).xxRowId_AT.getValue())) {
                    glblMsg.A.no(i).locNum_M.setErrorInfo(1, NMAL2830Constant.NMAM8461E, new String[] {NMAL2830Constant.MSG_ARGS_NMAM8461E });
                    errRow = i;
                }
                if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(glblMsg.A.no(i).xxRowId_AT.getValue())) {
                    glblMsg.A.no(i).xxChkBox_M.setErrorInfo(1, NMAL2830Constant.NMAM8461E, new String[] {NMAL2830Constant.MSG_ARGS_NMAM8461E });
                    glblMsg.A.no(i).xxChkBox_AM.setErrorInfo(1, NMAL2830Constant.NMAM8461E, new String[] {NMAL2830Constant.MSG_ARGS_NMAM8461E });
                }
            }
        } else if (multipleCheckCnt > 1) {
            errFlg = true;
            for (int i = 0; i < errItemList.size(); i++) {
                errItem = errItemList.get(i);
                errItem.setErrorInfo(1, NMAL2830Constant.NMAM8461E, new String[] {NMAL2830Constant.MSG_ARGS_NMAM8461E });
            }
        }

        if (errFlg) {
            bizMsg.xxPageShowFromNum.setValue(errRow);
            NMAL2830CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            return;
        }

        if (ZYPCommonFunc.hasValue(glblMsg.A.no(prospectRowNum).locNum_M)) {

            S21SsmEZDResult getRequestedMergeToResult = NMAL2830Query.getInstance().getRequestedMergeTo(glblMsg.A.no(prospectRowNum));

            if (getRequestedMergeToResult.isCodeNormal()) {

                List<Map<String, Object>> resultList = (List<Map<String, Object>>) getRequestedMergeToResult.getResultObject();
                Map<String, Object> resultMap = resultList.get(0);

                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(eventRowNum).dsAcctNum_A2, (String) resultMap.get("DS_ACCT_NUM"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(eventRowNum).locNum_A2, (String) resultMap.get("LOC_NUM"));
                // set Prospect #
                String sfdcProspectNum = (String) resultMap.get("SFDC_DS_XREF_ACCT_CD");
                String oasisProspectNum = (String) resultMap.get("OASIS_DS_XREF_ACCT_CD");
                if ((ZYPCommonFunc.hasValue(sfdcProspectNum) && ZYPCommonFunc.hasValue(oasisProspectNum)) || (ZYPCommonFunc.hasValue(sfdcProspectNum) && !ZYPCommonFunc.hasValue(oasisProspectNum))) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(eventRowNum).dsXrefAcctCd_A2, sfdcProspectNum);
                } else {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(eventRowNum).dsXrefAcctCd_A2, oasisProspectNum);
                }
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(eventRowNum).dsAcctTpNm_A2, (String) resultMap.get("DS_ACCT_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(eventRowNum).dsAcctNm_A2, (String) resultMap.get("DS_ACCT_NM"));
                // set Address
                StringBuilder sb = new StringBuilder();
                String add1 = (String) resultMap.get("FIRST_LINE_ADDR");
                String add2 = (String) resultMap.get("SCD_LINE_ADDR");
                String add3 = (String) resultMap.get("THIRD_LINE_ADDR");
                String add4 = (String) resultMap.get("FRTH_LINE_ADDR");
                editAddress(sb, add1, add2, add3, add4, true);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(eventRowNum).xxAllLineAddr_A2, sb.toString());
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(eventRowNum).ctyAddr_A2, (String) resultMap.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(eventRowNum).stCd_A2, (String) resultMap.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(eventRowNum).postCd_A2, (String) resultMap.get("POST_CD"));
            } else if (getRequestedMergeToResult.isCodeNotFound()) {
                glblMsg.A.no(prospectRowNum).locNum_M.setErrorInfo(1, NMAL2830Constant.NMAM8461E, new String[] {NMAL2830Constant.MSG_ARGS_NMAM8461E_NOT_FUND });
                errFlg = true;
            }
        } else if (checkOnRowNum != -1) {
            EZDMsg.copy(glblMsg.A.no(checkOnRowNum), "A3", glblMsg.A.no(eventRowNum), "A2");
            EZDMsg.copy(glblMsg.A.no(checkOnRowNum), "E3", glblMsg.A.no(eventRowNum), "E2");
            EZDMsg.copy(glblMsg.A.no(checkOnRowNum), "P3", glblMsg.A.no(eventRowNum), "P2");
            EZDMsg.copy(glblMsg.A.no(checkOnRowNum), "D3", glblMsg.A.no(eventRowNum), "D2");
        }

        if (errFlg) {
            bizMsg.xxPageShowFromNum.setValue(prospectRowNum);
        } else {
            bizMsg.xxPageShowFromNum.setValue(eventRowNum);
        }
        NMAL2830CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2830Scrn00_Search(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg) {

        // search
        search(bizMsg, glblMsg);
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);

        // getProspectList
        S21SsmEZDResult ssmProspectListResult = NMAL2830Query.getInstance().getProspectList(bizMsg, glblMsg);

        boolean limitDispFlg = false;
        boolean cntNotFoundFlg = true;
        int rowCnt = 0;

        if (ssmProspectListResult.isCodeNormal()) {

            S21SsmEZDResult ssmDuplicateListResult;
            StringBuilder sb = new StringBuilder();
            String prospectNum;

            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

                if (NMAL2830Constant.LIMIT_ROW_DISP < rowCnt + 1) {
                    limitDispFlg = true;
                    break;
                }

                if (ZYPCommonFunc.hasValue(glblMsg.A.no(rowCnt).xxRowId_AT)) {
                    ++rowCnt;
                }

                NMAL2830_BSMsg bsMsg = glblMsg.B.no(i);

                // getDuplicateList
                if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_RT)) {
                    // getDuplicateList(Real Time)
                    sb.setLength(0);
                    editAddress(sb, bsMsg.firstLineAddr_B, bsMsg.scdLineAddr_B, bsMsg.thirdLineAddr_B, bsMsg.frthLineAddr_B, false);
                    ssmDuplicateListResult = NMAL2830Query.getInstance().getDuplicateList_realTime(bizMsg, bsMsg, glblMsg, sb.toString());
                } else {
                    // getDuplicateList(Non Real Time)
                    ssmDuplicateListResult = NMAL2830Query.getInstance().getProspectList_nonRealTime(bizMsg, bsMsg, glblMsg);
                }

                if (ssmDuplicateListResult.isCodeNormal()) {

                    cntNotFoundFlg = false;

                    // ////////
                    // set row(Prospect)
                    // ////////
                    EZDMsg.copy(glblMsg.B.no(i), "B", glblMsg.A.no(rowCnt), "A1");

                    NMAL2830_ASMsg asMsg = glblMsg.A.no(rowCnt);

                    // set Row Type
                    ZYPEZDItemValueSetter.setValue(asMsg.xxRowId_AT, NMAL2830Constant.ROW_TP_PROSPECT);

                    // set Prospect #
                    if ((ZYPCommonFunc.hasValue(bsMsg.dsXrefAcctCd_BS) && ZYPCommonFunc.hasValue(bsMsg.dsXrefAcctCd_BO)) || (ZYPCommonFunc.hasValue(bsMsg.dsXrefAcctCd_BS) && !ZYPCommonFunc.hasValue(bsMsg.dsXrefAcctCd_BO))) {
                        prospectNum = bsMsg.dsXrefAcctCd_BS.getValue();
                    } else {
                         prospectNum = bsMsg.dsXrefAcctCd_BO.getValue();
                    }
                    ZYPEZDItemValueSetter.setValue(asMsg.dsXrefAcctCd_A1, prospectNum);

                    // set Address
                    sb.setLength(0);
                    editAddress(sb, bsMsg.firstLineAddr_B, bsMsg.scdLineAddr_B, bsMsg.thirdLineAddr_B, bsMsg.frthLineAddr_B, true);
                    ZYPEZDItemValueSetter.setValue(asMsg.xxAllLineAddr_A1, sb.toString());

                    // set Assigned Territory
                    sb.setLength(0);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_1, bsMsg.lineBizRoleTpDescTxt_1, bsMsg.fill73Txt_1, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_2, bsMsg.lineBizRoleTpDescTxt_2, bsMsg.fill73Txt_2, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_3, bsMsg.lineBizRoleTpDescTxt_3, bsMsg.fill73Txt_3, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_4, bsMsg.lineBizRoleTpDescTxt_4, bsMsg.fill73Txt_4, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_5, bsMsg.lineBizRoleTpDescTxt_5, bsMsg.fill73Txt_5, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_6, bsMsg.lineBizRoleTpDescTxt_6, bsMsg.fill73Txt_6, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_7, bsMsg.lineBizRoleTpDescTxt_7, bsMsg.fill73Txt_7, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_8, bsMsg.lineBizRoleTpDescTxt_8, bsMsg.fill73Txt_8, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_9, bsMsg.lineBizRoleTpDescTxt_9, bsMsg.fill73Txt_9, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_10, bsMsg.lineBizRoleTpDescTxt_10, bsMsg.fill73Txt_10, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_11, bsMsg.lineBizRoleTpDescTxt_11, bsMsg.fill73Txt_11, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_12, bsMsg.lineBizRoleTpDescTxt_12, bsMsg.fill73Txt_12, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_13, bsMsg.lineBizRoleTpDescTxt_13, bsMsg.fill73Txt_13, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_14, bsMsg.lineBizRoleTpDescTxt_14, bsMsg.fill73Txt_14, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_15, bsMsg.lineBizRoleTpDescTxt_15, bsMsg.fill73Txt_15, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_16, bsMsg.lineBizRoleTpDescTxt_16, bsMsg.fill73Txt_16, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_17, bsMsg.lineBizRoleTpDescTxt_17, bsMsg.fill73Txt_17, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_18, bsMsg.lineBizRoleTpDescTxt_18, bsMsg.fill73Txt_18, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_19, bsMsg.lineBizRoleTpDescTxt_19, bsMsg.fill73Txt_19, true);
                    setAssignedTerritory(sb, bsMsg.acctTrtyOrgCd_20, bsMsg.lineBizRoleTpDescTxt_20, bsMsg.fill73Txt_20, false);
                    ZYPEZDItemValueSetter.setValue(asMsg.xxAsgTrtyNm_A1, sb.toString());

                    // ////////
                    // set row(Requested Merge To)
                    // ////////
                    asMsg = glblMsg.A.no(++rowCnt);

                    // set Row Type
                    ZYPEZDItemValueSetter.setValue(asMsg.xxRowId_AT, NMAL2830Constant.ROW_TP_REQUESTED_MERGE_TO);

                    // ////////
                    // set row(Duplicate)
                    // ////////
                    int duplidateCnt = 1;
                    String wkLocNum = "";
                    for (int j = 0; j < glblMsg.C.getValidCount(); j++) {

                        NMAL2830_CSMsg csMsg = glblMsg.C.no(j);

                        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_RT.getValue())) {
                            if (NMAL2830Constant.LIMIT_ROW_DUPLICATE_FOR_DISP < duplidateCnt) {
                                break;
                            }
                            if (j != 0 && wkLocNum.equals(csMsg.locNum_C.getValue())) {
                                continue;
                            }
                            wkLocNum = csMsg.locNum_C.getValue();
                        }

                        ++rowCnt;
                        EZDMsg.copy(glblMsg.C.no(j), "C", glblMsg.A.no(rowCnt), "A3");

                        asMsg = glblMsg.A.no(rowCnt);

                        // set Row Type
                        ZYPEZDItemValueSetter.setValue(asMsg.xxRowId_AT, NMAL2830Constant.ROW_TP_DUPLICATE);

                        // set Row Name
                        String rowName = NMAL2830Constant.ROW_NM_DUPLICATE + String.valueOf(duplidateCnt);
                        ZYPEZDItemValueSetter.setValue(asMsg.xxRowId_AN, rowName);
                        ++duplidateCnt;

                        // set Prospect #
                        if ((ZYPCommonFunc.hasValue(csMsg.dsXrefAcctCd_CS) && ZYPCommonFunc.hasValue(csMsg.dsXrefAcctCd_CO)) || (ZYPCommonFunc.hasValue(csMsg.dsXrefAcctCd_CS) && !ZYPCommonFunc.hasValue(csMsg.dsXrefAcctCd_CO))) {
                            ZYPEZDItemValueSetter.setValue(asMsg.dsXrefAcctCd_A3, csMsg.dsXrefAcctCd_CS);
                        } else {
                            ZYPEZDItemValueSetter.setValue(asMsg.dsXrefAcctCd_A3, csMsg.dsXrefAcctCd_CO);
                        }

                        // set Address
                        sb.setLength(0);
                        editAddress(sb, csMsg.firstLineAddr_C, csMsg.scdLineAddr_C, csMsg.thirdLineAddr_C, csMsg.frthLineAddr_C, true);
                        ZYPEZDItemValueSetter.setValue(asMsg.xxAllLineAddr_A3, sb.toString());

                        // set Assigned Territory
                        sb.setLength(0);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_1, csMsg.lineBizRoleTpDescTxt_1, csMsg.fill73Txt_1, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_2, csMsg.lineBizRoleTpDescTxt_2, csMsg.fill73Txt_2, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_3, csMsg.lineBizRoleTpDescTxt_3, csMsg.fill73Txt_3, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_4, csMsg.lineBizRoleTpDescTxt_4, csMsg.fill73Txt_4, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_5, csMsg.lineBizRoleTpDescTxt_5, csMsg.fill73Txt_5, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_6, csMsg.lineBizRoleTpDescTxt_6, csMsg.fill73Txt_6, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_7, csMsg.lineBizRoleTpDescTxt_7, csMsg.fill73Txt_7, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_8, csMsg.lineBizRoleTpDescTxt_8, csMsg.fill73Txt_8, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_9, csMsg.lineBizRoleTpDescTxt_9, csMsg.fill73Txt_9, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_10, csMsg.lineBizRoleTpDescTxt_10, csMsg.fill73Txt_10, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_11, csMsg.lineBizRoleTpDescTxt_11, csMsg.fill73Txt_11, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_12, csMsg.lineBizRoleTpDescTxt_12, csMsg.fill73Txt_12, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_13, csMsg.lineBizRoleTpDescTxt_13, csMsg.fill73Txt_13, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_14, csMsg.lineBizRoleTpDescTxt_14, csMsg.fill73Txt_14, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_15, csMsg.lineBizRoleTpDescTxt_15, csMsg.fill73Txt_15, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_16, csMsg.lineBizRoleTpDescTxt_16, csMsg.fill73Txt_16, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_17, csMsg.lineBizRoleTpDescTxt_17, csMsg.fill73Txt_17, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_18, csMsg.lineBizRoleTpDescTxt_18, csMsg.fill73Txt_18, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_19, csMsg.lineBizRoleTpDescTxt_19, csMsg.fill73Txt_19, true);
                        setAssignedTerritory(sb, csMsg.acctTrtyOrgCd_20, csMsg.lineBizRoleTpDescTxt_20, csMsg.fill73Txt_20, false);
                        ZYPEZDItemValueSetter.setValue(asMsg.xxAsgTrtyNm_A3, sb.toString());

                        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_RT.getValue())) {

                            for (int condCnt = j; condCnt < glblMsg.C.getValidCount(); condCnt++) {

                                if (wkLocNum.equals(glblMsg.C.no(condCnt).locNum_C.getValue())) {
                                    // Real Time
                                    if (NMAL2830Constant.COND_EXACT_MATCH.equals(glblMsg.C.no(condCnt).xxTpCd_C.getValue())) {
                                        ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_E3, ZYPConstant.CHKBOX_ON_Y);
                                    }
                                    if (NMAL2830Constant.COND_PARTIAL_MATCH.equals(glblMsg.C.no(condCnt).xxTpCd_C.getValue())) {
                                        if (!ZYPCommonFunc.hasValue(asMsg.xxChkBox_E3)) {
                                            ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_P3, ZYPConstant.CHKBOX_ON_Y);
                                        }
                                    }
                                    if (NMAL2830Constant.COND_DUNS_MATCH.equals(glblMsg.C.no(condCnt).xxTpCd_C.getValue())) {
                                        ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_D3, ZYPConstant.CHKBOX_ON_Y);
                                    }
                                }
                            }
                        } else {
                            // Non Real Time
                            if (ZYPConstant.CHKBOX_ON_Y.equals(csMsg.xxChkBox_EM.getValue())) {
                                ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_E3, ZYPConstant.CHKBOX_ON_Y);
                            }
                            if (ZYPConstant.CHKBOX_ON_Y.equals(csMsg.xxChkBox_PM.getValue())) {
                                ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_P3, ZYPConstant.CHKBOX_ON_Y);
                            }
                            if (ZYPConstant.CHKBOX_ON_Y.equals(csMsg.xxChkBox_DM.getValue())) {
                                ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_D3, ZYPConstant.CHKBOX_ON_Y);
                            }
                        }
                    }
                }
            }
        }

        if (limitDispFlg) {
            bizMsg.setMessageInfo(NMAL2830Constant.NZZM0007E);
            glblMsg.A.setValidCount(rowCnt + 1);
        } else if (cntNotFoundFlg) {
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            bizMsg.setMessageInfo(NMAL2830Constant.NMAM0007I);
            glblMsg.A.setValidCount(0);
        } else {
            glblMsg.A.setValidCount(rowCnt + 1);
        }

        bizMsg.xxPageShowFromNum.setValue(1);
        NMAL2830CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * editAddress
     * @param sb StringBuilder
     * @param add1 EZDSStringItem
     * @param add2 EZDSStringItem
     * @param add3 EZDSStringItem
     * @param add4 EZDSStringItem
     * @param speceFlg boolean
     */
    private void editAddress(StringBuilder sb, EZDSStringItem add1, EZDSStringItem add2, EZDSStringItem add3, EZDSStringItem add4, boolean speceFlg) {
        editAddress(sb, add1.getValue(), add2.getValue(), add3.getValue(), add4.getValue(), speceFlg);
    }

    /**
     * editAddress
     * @param sb StringBuilder
     * @param add1 String
     * @param add2 String
     * @param add3 String
     * @param add4 String
     * @param speceFlg boolean
     */
    private void editAddress(StringBuilder sb, String add1, String add2, String add3, String add4, boolean speceFlg) {

        if (ZYPCommonFunc.hasValue(add1)) {
            sb.append(add1);
        }
        if (ZYPCommonFunc.hasValue(add2)) {
            if (speceFlg) {
                sb.append(NMAL2830Constant.SPECE_HALF_SIZE);
            }
            sb.append(add2);
        }
        if (ZYPCommonFunc.hasValue(add3)) {
            if (speceFlg) {
                sb.append(NMAL2830Constant.SPECE_HALF_SIZE);
            }
            sb.append(add3);
        }
        if (ZYPCommonFunc.hasValue(add4)) {
            if (speceFlg) {
                sb.append(NMAL2830Constant.SPECE_HALF_SIZE);
            }
            sb.append(add4);
        }
    }

    /**
     * setAssignedTerritory
     * @param sb StringBuilder
     * @param orgCd EZDSStringItem
     * @param roleNm EZDSStringItem
     * @param psnNm EZDSStringItem
     * @param commaFlg boolean
     */
    private void setAssignedTerritory(StringBuilder sb, EZDSStringItem orgCd, EZDSStringItem roleNm, EZDSStringItem psnNm, boolean commaFlg) {

        if (ZYPCommonFunc.hasValue(orgCd) || ZYPCommonFunc.hasValue(roleNm) || ZYPCommonFunc.hasValue(psnNm)) {
            sb.append(orgCd.getValue());
            sb.append(NMAL2830Constant.ARROW_LEFT);
            sb.append(roleNm.getValue());
            sb.append(NMAL2830Constant.PARENTHESIS_START);
            sb.append(psnNm.getValue());
            sb.append(NMAL2830Constant.PARENTHESIS_END);
            if (commaFlg) {
                sb.append(NMAL2830Constant.COMMA);
            }
        }
    }

    /**
     * clearAll
     * @param bizMsg NMAL2830CMsg
     * @param glblMsg NMAL2830SMsg
     */
    private void clearAll(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg) {

        // Clear Table
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);

        // Header
        bizMsg.srchOptNm_S.clear();
        bizMsg.srchOptPk_S.clear();
        bizMsg.srchOptPk_L.clear();
        bizMsg.srchOptNm_L.clear();
        bizMsg.xxFromDt.clear();
        bizMsg.dsAcctNm.clear();
        bizMsg.fill65Txt_RN.clear();
        bizMsg.orgNm_TN.clear();
        bizMsg.xxTpCd_D.clear();
        bizMsg.xxTpCd_DL.clear();
        bizMsg.xxTpNm_DL.clear();
        bizMsg.xxToDt.clear();
        bizMsg.dsXrefAcctCd.clear();
        bizMsg.psnCd.clear();
        bizMsg.orgNm_ON.clear();
        bizMsg.xxChkBox_RT.clear();
        bizMsg.xxAllLineAddr.clear();
        bizMsg.ctyAddr.clear();
        bizMsg.stCd.clear();
        bizMsg.stCd_L.clear();
        bizMsg.stDescTxt_L.clear();
        bizMsg.postCd.clear();
        bizMsg.dsAcctNum.clear();
        bizMsg.locNum.clear();
        bizMsg.xxPageShowFromNum.clear();
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();
        bizMsg.xxPageShowCurNum.clear();
        bizMsg.xxPageShowTotNum.clear();
    }
}
