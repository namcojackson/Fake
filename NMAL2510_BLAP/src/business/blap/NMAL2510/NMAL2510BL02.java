/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2510;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL2510.common.NMAL2510CommonLogic;
import business.blap.NMAL2510.constant.NMAL2510Constant;
import business.db.CNTYTMsg;
import business.db.CNTYTMsgArray;
import business.db.LINE_BIZ_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TM_ZONE;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * Resource Maintenance  NMAL2510BL02
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/02/08   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/03/04   Fujitsu         C.Yokoi         Update          CSA-QC#4654
 * 2016/03/31   Fujitsu         C.Yokoi         Update          CSA-QC#5945
 * 2016/04/14   Fujitsu         C.Yokoi         Update          CSA-QC#6309
 * 2016/04/29   SRAA            Y.Chen          Update          CSA-QC#5860
 * 2016/11/08   Fujitsu         M.Ohno          Update          S21_NA#2680
 * 2017/10/02   CITS            Y.Fujii         Update          S21_NA#19408
 * 2018/09/14   Fujitsu         S.Kosaka        Update          QC#27723
 * 2019/02/13   Fujitsu         Hd.Sugawara     Update          QC#29668
 * </pre>
 */
public class NMAL2510BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2510CMsg bizMsg = (NMAL2510CMsg) cMsg;
            NMAL2510SMsg glblMsg = (NMAL2510SMsg) sMsg;

            if ("NMAL2510_INIT".equals(screenAplID)) {
                doProcess_NMAL2510_INIT(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL2510_Search(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_CMN_Clear(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_CMN_Reset".equals(screenAplID)) {
                // START 2018/09/14 S.Kosaka [QC#27723,UPD]
                //doProcess_NMAL2510Scrn00_CMN_Reset(bizMsg, glblMsg);
                doProcess_NMAL2510_Search(bizMsg, glblMsg);
                // END 2018/09/14 S.Kosaka [QC#27723,UPD]
            } else if ("NMAL2510Scrn00_OnChange_LineOfBusiness".equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_OnChange_LineOfBusiness(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_OnClick_Hierarchy".equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_OnClick_Hierarchy(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_Insert_Row_Hierarchy".equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_Insert_Row_Hierarchy(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_Insert_Row_Territory".equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_Insert_Row_Territory(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_Insert_Row_Revenue".equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_Insert_Row_Revenue(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_OpenWin_Contract".equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_OpenWin_Contract(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_TAB_Hierarchy".equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_TAB_Hierarchy(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_TAB_Territory".equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_TAB_Territory(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_TAB_Revenue".equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_TAB_Revenue(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_TAB_Misc".equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_TAB_Misc(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_CMN_Submit(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_OnChange_BusinessAreaHierarchy".equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_OnChange_BusinessAreaHierarchy(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_Delete_Row_Hierarchy".equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_Delete_Row_Hierarchy(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_Delete_Row_Territory".equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_Delete_Row_Territory(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_Delete_Row_Revenue".equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_Delete_Row_Revenue(bizMsg, glblMsg);
            } else if ("NMAL2510Scrn00_OnBlur_DeriveFromResource".equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_OnBlur_DeriveFromResource(bizMsg, glblMsg);
            } else if ("NMAL2510_NMAL2570".equals(screenAplID)) {
                doProcess_NMAL2510_Search(bizMsg, glblMsg);
            } else if ("NMAL2510_NMAL2540".equals(screenAplID)) {
                doProcess_NMAL2510_NMAL2540(bizMsg, glblMsg);
            } else {
                return;
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Init Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2510_INIT(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        createPulldownList(cMsg, sMsg);
        cMsg.xxRsltFlg_H1.setValue(ZYPConstant.FLG_OFF_N);

        if (ZYPCommonFunc.hasValue(cMsg.psnNum_H1) && NMAL2510Constant.TAB_REVENUE.equals(cMsg.xxDplyTab.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, NMAL2510Constant.TAB_REVENUE);
            doProcess_NMAL2510_Search(cMsg, sMsg);
            doProcess_NMAL2510Scrn00_TAB_Revenue(cMsg, sMsg);
            ZYPEZDItemValueSetter.setValue(cMsg.psnNum_BK, cMsg.psnNum_H1);
        } else if (ZYPCommonFunc.hasValue(cMsg.psnNum_H1)) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, NMAL2510Constant.TAB_HIERARCHY);
            doProcess_NMAL2510_Search(cMsg, sMsg);
            doProcess_NMAL2510Scrn00_OnClick_Hierarchy(cMsg, sMsg);
            // 2016/04/14 CSA-QC#6309 Add Start
            ZYPEZDItemValueSetter.setValue(cMsg.psnNum_BK, cMsg.psnNum_H1);
            // 2016/04/14 CSA-QC#6309 Add End
        } else {
            // 2016/04/14 CSA-QC#6309 Add Start
            ZYPEZDItemValueSetter.setValue(cMsg.hrPsnCmpyNm_H1, NMAL2510Constant.DEFAULT_COMPANY_NAME);
            // 2016/04/14 CSA-QC#6309 Add End
        }

    }

    /**
     * <pre>
     * Check before tab transition
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2510Scrn00_TAB_Hierarchy(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        if (NMAL2510CommonLogic.isChangedPrevTab(cMsg, sMsg)) {
            return;
        }

        cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_HIERARCHY);
        doProcess_NMAL2510_Search(cMsg, sMsg);

    }

    /**
     * <pre>
     * Check befoew tab transition
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2510Scrn00_TAB_Territory(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        if (NMAL2510CommonLogic.isChangedPrevTab(cMsg, sMsg)) {
            return;
        }

        cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_TERRITORY);
        doProcess_NMAL2510_Search(cMsg, sMsg);

    }

    /**
     * <pre>
     * Check before tab transition
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2510Scrn00_TAB_Revenue(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        if (NMAL2510CommonLogic.isChangedPrevTab(cMsg, sMsg)) {
            return;
        }

        cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_REVENUE);
        doProcess_NMAL2510_Search(cMsg, sMsg);

    }

    /**
     * <pre>
     * Check before tab transition
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2510Scrn00_TAB_Misc(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        if (NMAL2510CommonLogic.isChangedPrevTab(cMsg, sMsg)) {
            return;
        }

        cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_MISC);
        doProcess_NMAL2510_Search(cMsg, sMsg);

    }

    /**
     * <pre>
     * Change Role pull down by selected business area.
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2510Scrn00_OnChange_BusinessAreaHierarchy(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).bizAreaOrgCd_P2)) {

                cMsg.A.no(i).orgFuncRoleTpCd_A2.clear();
                cMsg.A.no(i).orgFuncRoleTpNm_A2.clear();

                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(NMAL2510Constant.DB_PARAM_CMSG, cMsg);
                ssmParam.put("globalCmpyCd", getGlobalCompanyCode());
                ssmParam.put("bizAreaOrgCd", cMsg.A.no(i).bizAreaOrgCd_P2.getValue());

                // Organization Function Role Type
                S21SsmEZDResult orgFuncRoleTpWithBusinessAreaPulldownList = NMAL2510Query.getInstance().getOrgFuncRoleTpWithBusinessAreaPulldownList(ssmParam);

                if (orgFuncRoleTpWithBusinessAreaPulldownList.isCodeNormal()) {
                    List<Map> orgFuncRoleTpWithBusinessAreaList = (List<Map>) orgFuncRoleTpWithBusinessAreaPulldownList.getResultObject();
                    NMAL2510CommonLogic.createPulldownList(cMsg.A.no(i).orgFuncRoleTpCd_A2, cMsg.A.no(i).orgFuncRoleTpNm_A2, orgFuncRoleTpWithBusinessAreaList, new String[] {NMAL2510Constant.ORG_FUNC_ROLE_TP_CD_DBCOLUMN,
                            NMAL2510Constant.ORG_FUNC_ROLE_TP_NM_DBCOLUMN });
                }
            }
        }
    }

    /**
     * <pre>
     * Init Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2510_Search(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {
        // 2016/02/08 CSA-QC#2869 Mod Start
        NMAL2510CommonLogic.clearHierarchy(cMsg, sMsg);
        S21SsmEZDResult ssmResult;

        // ### Header ###
        ssmResult = NMAL2510Query.getInstance().getPerson(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt == 1) {
                // Add Start 2016/11/08 M.Ohno S21_NA#2680
                ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistCratByNm, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistCratByNm.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistUpdByNm, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistUpdByNm.getValue()));
                // Add End   2016/11/08 M.Ohno S21_NA#2680
                EZDMsg.copy(sMsg, null, cMsg, null);
                cMsg.xxRsltFlg_H1.setValue(ZYPConstant.FLG_ON_Y); // 2016/02/08
                // QC#5860
                String dplyAddr = this.buildAddressDisplay(cMsg);
                ZYPEZDItemValueSetter.setValue(cMsg.xxAllLineAddr_H1, dplyAddr);
                ZYPEZDItemValueSetter.setValue(sMsg.xxAllLineAddr_H1, dplyAddr);
            } else {
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxRsltFlg_H1.getValue())) {
                    NMAL2510CommonLogic.clearforHierachySearch(cMsg, sMsg);
                    NMAL2510CommonLogic.clearforTerritorySearch(cMsg, sMsg);
                    NMAL2510CommonLogic.clearforRevenueSearch(cMsg, sMsg);
                    cMsg.xxRsltFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
                }
                cMsg.setMessageInfo(NMAL2510Constant.NMAM8344I);
                return;
            }
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxRsltFlg_H1.getValue())) {
                NMAL2510CommonLogic.clearforHierachySearch(cMsg, sMsg);
                NMAL2510CommonLogic.clearforTerritorySearch(cMsg, sMsg);
                NMAL2510CommonLogic.clearforRevenueSearch(cMsg, sMsg);
                cMsg.xxRsltFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
            }
            cMsg.setMessageInfo(NMAL2510Constant.NMAM8344I);
            return;
        }

        // ### Hierarchy Tab ###
        NMAL2510CommonLogic.clearforHierachySearch(cMsg, sMsg);
        ssmResult = NMAL2510Query.getInstance().getOrganization(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NMAL2510Constant.NZZM0001W);
                queryResCnt = sMsg.A.length();
            }

            // Move first page date from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }

                // Add Start 2016/11/08 M.Ohno S21_NA#2680
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(i).xxRecHistCratByNm_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(i).xxRecHistUpdByNm_A1.getValue()));
                // Add End   2016/11/08 M.Ohno S21_NA#2680

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);

                // 2016/03/04 CSA-QC#4654 Add Start
                NMAL2510CommonLogic.setBusinessAreaProtection(cMsg, i);
                // 2016/03/04 CSA-QC#4654 Add End

                NMAL2510CommonLogic.createPulldownforHierarchy(cMsg, getGlobalCompanyCode(), i);
            }
            cMsg.A.setValidCount(i);

            // Default value for Current Hierarchy
            cMsg.xxRadioBtn_A1.setValue(0);
            doProcess_NMAL2510Scrn00_OnClick_Hierarchy(cMsg, sMsg);
        }

        // ### Territory Tab ###
        NMAL2510CommonLogic.clearforTerritorySearch(cMsg, sMsg);
        ssmResult = NMAL2510Query.getInstance().getTerritory(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo(NMAL2510Constant.NZZM0001W);
                queryResCnt = sMsg.B.length();
            }

            // Move first page date from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.B.getValidCount(); i++) {
                if (i == cMsg.B.length()) {
                    break;
                }
                // Add Start 2016/11/08 M.Ohno S21_NA#2680
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRecHistCratByNm_B1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.B.no(i).xxRecHistCratByNm_B1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRecHistUpdByNm_B1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.B.no(i).xxRecHistUpdByNm_B1.getValue()));
                // Add End   2016/11/08 M.Ohno S21_NA#2680

                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);

            }
            cMsg.B.setValidCount(i);
        }

        // ### Revenue Tab ###
        NMAL2510CommonLogic.clearforRevenueSearch(cMsg, sMsg);
        ssmResult = NMAL2510Query.getInstance().getRevenue(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.C.length()) {
                cMsg.setMessageInfo(NMAL2510Constant.NZZM0001W);
                queryResCnt = sMsg.C.length();
            }

            // Move first page date from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.C.getValidCount(); i++) {
                if (i == cMsg.C.length()) {
                    break;
                }
                // Add Start 2016/11/08 M.Ohno S21_NA#2680
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).xxRecHistCratByNm_C1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.C.no(i).xxRecHistCratByNm_C1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).xxRecHistUpdByNm_C1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.C.no(i).xxRecHistUpdByNm_C1.getValue()));
                // Add End   2016/11/08 M.Ohno S21_NA#2680

                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i), null);

            }
            cMsg.C.setValidCount(i);
        }

        // ### Miscerenueous Tab ###
        // do nothing
        // 2016/02/08 CSA-QC#2869 Mod End

        // 2016/02/22 CSA-QC#4336 Add Start
        NMAL2510CommonLogic.setBackUp(cMsg);
        // 2016/02/22 CSA-QC#4336 Add End
    }

    /**
     * The method explanation: Refresh after submit.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL2510Scrn00_CMN_Submit(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        if (NMAL2510Constant.MESSAGE_KIND_WARN.equals(cMsg.getMessageKind())) {
            return;
        }

        if (NMAL2510Constant.MESSAGE_KIND_ERROR.equals(cMsg.getMessageKind())) {
            return;
        }

        doProcess_NMAL2510_Search(cMsg, sMsg);
    }

    /**
     * <pre>
     * Delete row in resource tab
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2510Scrn00_Delete_Row_Hierarchy(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        int selectedRow = cMsg.xxRadioBtn_A1.getValueInt();
        List<Integer> checkList = new ArrayList<Integer>();
        checkList.add(selectedRow);

        for (int i = 0; i < checkList.size(); i++) {
            int index = checkList.get(i);
            checkList.set(i, index);

            if (ZYPCommonFunc.hasValue(sMsg.A.no(index).tocCd_A2)) {
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).orgStruTpCd_X1, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).tocCd_X1, sMsg.A.no(index).tocCd_A2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).orgCd_X1, sMsg.A.no(index).orgCd_A2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).ezUpTime_X1, sMsg.A.no(index).ezUpTime_A3.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).ezUpTimeZone_X1, sMsg.A.no(index).ezUpTimeZone_A3.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).tocCd_X2, sMsg.A.no(index).tocCd_A2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).orgChngRqstPk_X2, sMsg.A.no(index).orgChngRqstPk_A2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).ezUpTime_X2, sMsg.A.no(index).ezUpTime_A4.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).ezUpTimeZone_X2, sMsg.A.no(index).ezUpTimeZone_A4.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).tocCd_X3, sMsg.A.no(index).tocCd_A2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).psnCd_X3, sMsg.psnCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).effFromDt_X3, sMsg.A.no(index).effFromDt_A2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).ezUpTime_X3, sMsg.A.no(index).ezUpTime_A5.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).ezUpTimeZone_X3, sMsg.A.no(index).ezUpTimeZone_A5.getValue());
                // 2016/03/31 CSA-QC#5945 Add Start
                // if (GNRN_TP.FUTURE.equals(sMsg.A.no(index).gnrnTpCd_A2.getValue())) {
                //     ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).gnrnTpCd_X1, GNRN_TP.FUTURE);
                // } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).gnrnTpCd_X1, GNRN_TP.DELETE);
                // }
                // 2016/03/31 CSA-QC#5945 Add End

                sMsg.X.setValidCount(sMsg.X.getValidCount() + 1);
            }
        }

        ZYPTableUtil.deleteRows(cMsg.A, checkList);
        ZYPTableUtil.deleteRows(sMsg.A, checkList);

    }

    /**
     * <pre>
     * Delete row in revenue tab
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2510Scrn00_Delete_Row_Territory(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.B, NMAL2510Constant.CHKBOX_B, ZYPConstant.CHKBOX_ON_Y);

        for (int i = 0; i < checkList.size(); i++) {
            int index = checkList.get(i);
            checkList.set(i, index);
            if (ZYPCommonFunc.hasValue(sMsg.B.no(index).orgCd_B2)) {
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).orgCd_Y1, cMsg.B.no(index).orgCd_B2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).orgStruTpCd_Y1, ORG_STRU_TP.TERRITORY_STRUCTURE);
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).psnCd_Y1, cMsg.psnCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).orgFuncRoleTpCd_Y1, cMsg.B.no(index).orgFuncRoleTpCd_B2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).effFromDt_Y1, cMsg.B.no(index).effFromDt_B2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).ezUpTime_Y1, cMsg.B.no(index).ezUpTime_B3.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).ezUpTimeZone_Y1, cMsg.B.no(index).ezUpTimeZone_B3.getValue());
                // 2016/03/31 CSA-QC#5945 Add Start
                // if (GNRN_TP.FUTURE.equals(cMsg.B.no(index).gnrnTpCd_B2.getValue())) {
                //     ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).gnrnTpCd_Y1, GNRN_TP.FUTURE);
                // } else {
                  ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).gnrnTpCd_Y1, GNRN_TP.DELETE);
                // }
                // 2016/03/31 CSA-QC#5945 Add End

                sMsg.Y.setValidCount(sMsg.Y.getValidCount() + 1);
            }
        }

        ZYPTableUtil.deleteRows(cMsg.B, checkList);
        ZYPTableUtil.deleteRows(sMsg.B, checkList);
    }

    /**
     * <pre>
     * Delete row in revenue tab
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2510Scrn00_Delete_Row_Revenue(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.C, NMAL2510Constant.CHKBOX_C, ZYPConstant.CHKBOX_ON_Y);

        for (int i = 0; i < checkList.size(); i++) {
            int index = checkList.get(i);
            checkList.set(i, index);
            if (ZYPCommonFunc.hasValue(sMsg.C.no(index).ezUpTime_C3)) {
                ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).psnCd_Z1, cMsg.psnCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).revAcctTpCd_Z1, cMsg.C.no(index).revAcctTpCd_P1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).effFromDt_Z1, cMsg.C.no(index).effFromDt_C2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).ezUpTime_Z1, cMsg.C.no(index).ezUpTime_C3.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).ezUpTimeZone_Z1, cMsg.C.no(index).ezUpTimeZone_C3.getValue());

                sMsg.Z.setValidCount(sMsg.Z.getValidCount() + 1);
            }
        }

        ZYPTableUtil.deleteRows(cMsg.C, checkList);
        ZYPTableUtil.deleteRows(sMsg.C, checkList);
    }

    /**
     * The method explanation: create Pulldown List.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL2510Scrn00_Insert_Row_Hierarchy(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        int index = cMsg.A.getValidCount();

        NMAL2510CommonLogic.createPulldownforHierarchy(cMsg, getGlobalCompanyCode(), index);

        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).effFromDt_A2, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        cMsg.A.setValidCount(index + 1);
        sMsg.A.setValidCount(index + 1);

    }

    /**
     * The method explanation: create Pulldown List.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL2510Scrn00_Insert_Row_Territory(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        EZDMsgArray targetArray = cMsg.B;
        int newRowIndex = targetArray.getValidCount();
        targetArray.get(newRowIndex).clear();
        ZYPEZDItemValueSetter.setValue(cMsg.B.no(newRowIndex).effFromDt_B2, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        targetArray.setValidCount(newRowIndex + 1);

    }

    /**
     * The method explanation: create Pulldown List.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL2510Scrn00_Insert_Row_Revenue(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        EZDMsgArray targetArray = cMsg.C;
        int newRowIndex = targetArray.getValidCount();
        targetArray.get(newRowIndex).clear();
        ZYPEZDItemValueSetter.setValue(cMsg.C.no(newRowIndex).effFromDt_C2, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        // Add Start 2019/02/13 QC#29668
        ZYPEZDItemValueSetter.setValue(cMsg.C.no(newRowIndex).tocRgtnReqFlg_C2, ZYPConstant.FLG_ON_Y);
        // Add End 2019/02/13 QC#29668
        targetArray.setValidCount(newRowIndex + 1);

    }

    /**
     * The method explanation: create Pulldown List.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void createPulldownList(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        S21UserProfileService profileAccessor = getUserProfileService();
        String globalCmpyCd = profileAccessor.getGlobalCompanyCode();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(NMAL2510Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("globalCmpyCd", globalCmpyCd);
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_OFF_N);

        // Pull down For Header
        // Person Type
        ZYPCodeDataUtil.createPulldownList(PSN_TP.class, cMsg.psnTpCd_H1, cMsg.psnTpNm_H1);

        // Line Of Business
        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, cMsg.lineBizTpCd_H1, cMsg.lineBizTpNm_H1);

        // Territory Group
        S21SsmEZDResult trtyGrpTpPulldownList = NMAL2510Query.getInstance().getTrtyGrpTpPulldownList(ssmParam);

        if (trtyGrpTpPulldownList.isCodeNormal()) {
            List<Map> trtyGrpTpList = (List<Map>) trtyGrpTpPulldownList.getResultObject();
            NMAL2510CommonLogic.createPulldownList(cMsg.trtyGrpTpCd_H1, cMsg.trtyGrpTpNm_H1, trtyGrpTpList, new String[] {NMAL2510Constant.TRTY_GRP_TP_CD_DBCOLUMN, NMAL2510Constant.TRTY_GRP_TP_NM_DBCOLUMN });
        }

        // Pull down For Territory Tab
        // Business Area
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        S21SsmEZDResult bizAreaOrgTerritoryPulldownList = NMAL2510Query.getInstance().getBizAreaOrgTerritoryPulldownList(ssmParam);

        if (bizAreaOrgTerritoryPulldownList.isCodeNormal()) {
            List<Map> bizAreaOrgTerritoryList = (List<Map>) bizAreaOrgTerritoryPulldownList.getResultObject();
            NMAL2510CommonLogic.createPulldownList(cMsg.bizAreaOrgCd_B1, cMsg.bizAreaOrgNm_B1, bizAreaOrgTerritoryList, new String[] {NMAL2510Constant.BIZ_AREA_ORG_CD_DBCOLUMN, NMAL2510Constant.BIZ_AREA_ORG_NM_DBCOLUMN });
        }

        // Account team Role Type
        S21SsmEZDResult acctTeamRoleTpPulldownList = NMAL2510Query.getInstance().getAcctTeamRoleTpPulldownList(ssmParam);

        if (acctTeamRoleTpPulldownList.isCodeNormal()) {
            List<Map> acctTeamRoleTpList = (List<Map>) acctTeamRoleTpPulldownList.getResultObject();
            NMAL2510CommonLogic.createPulldownList(cMsg.acctTeamRoleTpCd_B1, cMsg.acctTeamRoleTpNm_B1, acctTeamRoleTpList, new String[] {NMAL2510Constant.ACCT_TEAM_ROLE_TP_CD_DBCOLUMN, NMAL2510Constant.ACCT_TEAM_ROLE_TP_NM_DBCOLUMN });
        }

        // Pull down For Revenue Tab
        // Revenue Account Type
        S21SsmEZDResult revAcctTpPulldownList = NMAL2510Query.getInstance().getRevAcctTpPulldownList(ssmParam);

        if (revAcctTpPulldownList.isCodeNormal()) {
            List<Map> revAcctTpList = (List<Map>) revAcctTpPulldownList.getResultObject();
            NMAL2510CommonLogic.createPulldownList(cMsg.revAcctTpCd_C1, cMsg.revAcctTpNm_C1, revAcctTpList, new String[] {NMAL2510Constant.REV_ACCT_TP_CD_DBCOLUMN, NMAL2510Constant.REV_ACCT_TP_NM_DBCOLUMN });
        }

        // Pull down For Misc Tab
        // Time Zone
        ZYPCodeDataUtil.createPulldownList(TM_ZONE.class, cMsg.tmZoneCd_D1, cMsg.tmZoneNm_D1);

    }

    private void doProcess_NMAL2510Scrn00_OnClick_Hierarchy(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        int xxRadioBtn = cMsg.xxRadioBtn_A1.getValueInt();
        String targetOrgCd = null;
        String targetOrgNm = null;
        String targetBizArea = null;

        NMAL2510CommonLogic.clearHierarchy(cMsg, sMsg);

        if (!ZYPCommonFunc.hasValue(cMsg.A.no(xxRadioBtn).orgCd_A2)) {
            if (!ZYPCommonFunc.hasValue(cMsg.A.no(xxRadioBtn).orgNm_A2)) {
                return;

            } else {
                targetOrgNm = cMsg.A.no(xxRadioBtn).orgNm_A2.getValue();
            }

        } else {
            targetOrgCd = cMsg.A.no(xxRadioBtn).orgCd_A2.getValue();
        }

        if (!ZYPCommonFunc.hasValue(cMsg.A.no(xxRadioBtn).bizAreaOrgCd_P2)) {
            targetBizArea = cMsg.A.no(xxRadioBtn).bizAreaOrgCd_P2.getValue();
        }

        S21SsmEZDResult ssmResult = NMAL2510Query.getInstance().getOrganizationHierarchy(targetOrgCd, targetOrgNm, targetBizArea, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.T.length()) {
                cMsg.setMessageInfo(NMAL2510Constant.NZZM0001W);
                queryResCnt = sMsg.T.length();
            }

            // Move first page date from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.T.getValidCount(); i++) {
                if (i == cMsg.T.length()) {
                    break;
                }
                ZYPEZDItemValueSetter.setValue(sMsg.T.no(i).xxRecHistCratByNm_T, ZYPRecHistUtil.getFullNameForRecHist(sMsg.T.no(i).xxRecHistCratByNm_T.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.T.no(i).xxRecHistUpdByNm_T, ZYPRecHistUtil.getFullNameForRecHist(sMsg.T.no(i).xxRecHistUpdByNm_T.getValue()));

                EZDMsg.copy(sMsg.T.no(i), null, cMsg.T.no(i), null);
            }
            cMsg.T.setValidCount(i);
        }
    }

    private void doProcess_NMAL2510Scrn00_OnChange_LineOfBusiness(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        LINE_BIZ_TPTMsg tMsg = new LINE_BIZ_TPTMsg();
        tMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        tMsg.lineBizTpCd.setValue(cMsg.lineBizTpCd_P1.getValue());

        tMsg = (LINE_BIZ_TPTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            cMsg.otherResrcTrtyFlg_H1.setValue(tMsg.otherResrcTrtyFlg.getValue());
        }
    }

    private void doProcess_NMAL2510Scrn00_OpenWin_Contract(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        S21SsmEZDResult ssmResult = NMAL2510Query.getInstance().getContractTypeCd(cMsg);
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            Map map = (Map) resultList.get(0);

            if (ZYPCommonFunc.hasValue((String) map.get(NMAL2510Constant.CTAC_TP_CD_DBCOLUMN))) {
                cMsg.ctacTpCd_D1.setValue((String) map.get(NMAL2510Constant.CTAC_TP_CD_DBCOLUMN));
            }
        }
    }

    private void doProcess_NMAL2510Scrn00_CMN_Clear(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        String currentTab = cMsg.xxDplyTab.getValue();
        String tmpPsnNum = cMsg.psnNum_BK.getValue();

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.B.clear();
        cMsg.B.setValidCount(0);
        cMsg.C.clear();
        cMsg.C.setValidCount(0);
        cMsg.T.clear();
        cMsg.T.setValidCount(0);
        cMsg.X.clear();
        cMsg.X.setValidCount(0);
        cMsg.Y.clear();
        cMsg.Y.setValidCount(0);
        cMsg.Z.clear();
        cMsg.Z.setValidCount(0);
        cMsg.clear();

        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.B.clear();
        sMsg.B.setValidCount(0);
        sMsg.C.clear();
        sMsg.C.setValidCount(0);
        sMsg.T.clear();
        sMsg.T.setValidCount(0);
        sMsg.X.clear();
        sMsg.X.setValidCount(0);
        sMsg.Y.clear();
        sMsg.Y.setValidCount(0);
        sMsg.Z.clear();
        sMsg.Z.setValidCount(0);
        sMsg.clear();

        cMsg.xxDplyTab.setValue(currentTab);

        doProcess_NMAL2510_INIT(cMsg, sMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.psnNum_BK, tmpPsnNum);
    }

    private void doProcess_NMAL2510Scrn00_CMN_Reset(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        String currentTab = cMsg.xxDplyTab.getValue();

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.B.clear();
        cMsg.B.setValidCount(0);
        cMsg.C.clear();
        cMsg.C.setValidCount(0);
        cMsg.T.clear();
        cMsg.T.setValidCount(0);
        cMsg.X.clear();
        cMsg.X.setValidCount(0);
        cMsg.Y.clear();
        cMsg.Y.setValidCount(0);
        cMsg.Z.clear();
        cMsg.Z.setValidCount(0);
        // 2016/04/14 CSA-QC#6309 Add Start
        String tmpPsnNum = cMsg.psnNum_BK.getValue();
        cMsg.clear();
        ZYPEZDItemValueSetter.setValue(cMsg.psnNum_H1, tmpPsnNum);
        // 2016/04/14 CSA-QC#6309 Add End

        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.B.clear();
        sMsg.B.setValidCount(0);
        sMsg.C.clear();
        sMsg.C.setValidCount(0);
        sMsg.T.clear();
        sMsg.T.setValidCount(0);
        sMsg.X.clear();
        sMsg.X.setValidCount(0);
        sMsg.Y.clear();
        sMsg.Y.setValidCount(0);
        sMsg.Z.clear();
        sMsg.Z.setValidCount(0);
        sMsg.clear();

        cMsg.xxDplyTab.setValue(currentTab);

        doProcess_NMAL2510_INIT(cMsg, sMsg);
    }

    private void doProcess_NMAL2510Scrn00_OnBlur_DeriveFromResource(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {
        doProcess_NMAL2510_Search(cMsg, sMsg);

        if (ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            if (NMAL2510Constant.NMAM0038I.equals(cMsg.getMessageCode())) {
                cMsg.setMessageInfo(NMAL2510Constant.NMAM8344I);
            }
        }
    }

    // QC#5860
    private void doProcess_NMAL2510_NMAL2540(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {
        cMsg.xxAllLineAddr_H1.setValue(this.buildAddressDisplay(cMsg));
        BigDecimal cntyPk = getCntyPk(cMsg.cntyNm_H1.getValue(), cMsg.stCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.cntyPk_H1, cntyPk);
    }

    private BigDecimal getCntyPk(String cntyNm, String stCd) {
        CNTYTMsg cntyTMsg = new CNTYTMsg();
        cntyTMsg.setSQLID("002");
        cntyTMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        cntyTMsg.setConditionValue("cntyNm01", cntyNm);
        cntyTMsg.setConditionValue("stCd01", stCd);
        CNTYTMsgArray cntyTMsgArray = (CNTYTMsgArray) EZDTBLAccessor.findByCondition(cntyTMsg);
        if (cntyTMsgArray.length() > 0) {
            CNTYTMsg tMsg = cntyTMsgArray.no(0);
            return tMsg.cntyPk.getValue();
        }
        return null;
    }

    private String buildAddressDisplay(NMAL2510CMsg cMsg) {
        String firstLineAddr = cMsg.firstLineAddr_H1.getValue();
        String scdLineAddr = cMsg.scdLineAddr_H1.getValue();
        String thirdLineAddr = cMsg.thirdLineAddr_H1.getValue();
        String frthLineAddr = cMsg.frthLineAddr_H1.getValue();
        String ctyAddr = cMsg.ctyAddr_H1.getValue();
        String cntyNm = cMsg.cntyNm_H1.getValue();
        String provNm = cMsg.provNm_H1.getValue();
        String stCd = cMsg.stCd_H1.getValue();
        String postCd = cMsg.postCd_H1.getValue();
        String ctryCd = cMsg.ctryCd_H1.getValue();

        String s1 = buildAddress(NMAL2510Constant.SPACE, new String[] {firstLineAddr, scdLineAddr, thirdLineAddr, frthLineAddr, ctyAddr });
        String s2 = buildAddress(NMAL2510Constant.SPACE, new String[] {cntyNm, provNm, stCd });
        String s3 = buildAddress(NMAL2510Constant.SPACE, new String[] {postCd, ctryCd });

        String address = buildAddress(NMAL2510Constant.COMMMA_SPACE, new String[] {s1, s2, s3 });
        return address;
    }

    private String buildAddress(String splitString, String[] addressItems) {
        StringBuffer sb = new StringBuffer();
        for (String item : addressItems) {
            if (ZYPCommonFunc.hasValue(item)) {
                if (sb.length() == 0) {
                    sb.append(item);
                } else {
                    sb.append(splitString + item);
                }
            }
        }
        return sb.toString();
    }
}
