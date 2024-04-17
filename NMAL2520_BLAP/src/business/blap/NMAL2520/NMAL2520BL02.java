/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2520;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSMsg;
import business.blap.NMAL2520.common.NMAL2520CommonLogic;
import business.blap.NMAL2520.constant.NMAL2520Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * Org Structure Maintenance  NMAL2520BL02
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/04   Fujitsu         J.Sakamoto      Create          N/A
 * 2015/11/10   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/02/08   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/02/23   Fujitsu         C.Yokoi         Update          CSA-QC#1931
 * 2016/02/28   Fujitsu         C.Yokoi         Update          CSA-QC#4775
 * 2016/03/03   Fujitsu         W.Honda         Update          CSA-QC#4545
 * 2016/03/03   Fujitsu         C.Tanaka        Update          CSA-QC#4654
 * 2016/03/04   Fujitsu         W.Honda         Update          CSA-QC#2766
 * 2016/03/17   Fujitsu         C.Yokoi         Update          CSA-QC#5654
 * 2016/03/29   Fujitsu         C.Yokoi         Update          CSA-QC#6179
 * 2016/03/31   Fujitsu         C.Yokoi         Update          CSA-QC#5945
 * 2016/08/31   SRAA            Y.Chen          Update          QC#7966
 * 2016/10/04   Hitachi         Y.Takeno        Update          QC#14809
 * 2016/11/08   Fujitsu         M.Ohno          Update          S21_NA#2680
 * 2016/11/30   Fujitsu         M.Ohno          Update          CSA-QC#16236
 * 2017/03/07   Fujitsu         M.Ohno          Update          S21_NA#17760
 * 2017/06/14   Hitachi         J.Kim           Update          QC#18924
 * 2017/09/27   Fujitsu         H.Ikeda         Update          QC#21344
 * 2017/11/20   Fujitsu         M.Ohno          Update          QC#21344-2
 * 2017/12/05   Fujitsu         N.Sugiura       Update          QC#21270
 * 2018/01/10   Fujitsu         M.Ohno          Update          QC#20233
 * 2018/02/14   Fujitsu         Hd.Sugawara     Update          QC#23905
 * 2018/03/26   Fujitsu         R.Nakamura      Update          QC#25037
 * 2018/06/12   Fujitsu         T.Noguchi       Update          QC#20220
 * 2018/10/24   Fujitsu         K.Ishizuka      Update          QC#28351
 * </pre>
 */
public class NMAL2520BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2520CMsg bizMsg = (NMAL2520CMsg) cMsg;
            NMAL2520SMsg glblMsg = (NMAL2520SMsg) sMsg;

            if ("NMAL2520_INIT".equals(screenAplID)) {
                doProcess_NMAL2520_INIT(bizMsg, glblMsg);
                // START 2017/09/27 H.Ikeda [QC#21344,ADD]
                onChange_Tier(bizMsg, glblMsg);
                // End   2017/09/27 H.Ikeda [QC#21344,ADD]
            } else if ("NMAL2520Scrn00_Search".equals(screenAplID)) {
                onChange_BusinessArea(bizMsg, glblMsg);
                doProcess_NMAL2520_Search(bizMsg, glblMsg);
                onChange_Tier(bizMsg, glblMsg);
            } else if ("NMAL2520Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2520Scrn00_CMN_Clear(bizMsg, glblMsg);
            } else if ("NMAL2520Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL2520Scrn00_CMN_Reset(bizMsg, glblMsg);
// 2017/12/05 QC#21270 Del Start
//            } else if ("NMAL2520Scrn00_TAB_Build".equals(screenAplID)) {
//                doProcess_NMAL2520Scrn00_TAB_Build(bizMsg, glblMsg);
//            } else if ("NMAL2520Scrn00_TAB_View".equals(screenAplID)) {
//                doProcess_NMAL2520Scrn00_TAB_View(bizMsg, glblMsg);
//            } else if ("NMAL2520Scrn00_TAB_Asign".equals(screenAplID)) {
//                doProcess_NMAL2520Scrn00_TAB_Asign(bizMsg, glblMsg);
// 2017/12/05 QC#21270 Del End
            } else if ("NMAL2520Scrn00_Insert_Row_Hierarchy".equals(screenAplID)) {
                doProcess_NMAL2520Scrn00_Insert_Row_Hierarchy(bizMsg, glblMsg);
            } else if ("NMAL2520Scrn00_Insert_Row_Resrc".equals(screenAplID)) {
                doProcess_NMAL2520Scrn00_Insert_Row_Resrc(bizMsg, glblMsg);
            } else if ("NMAL2520_NMAL2510".equals(screenAplID)) {
                doProcess_NMAL2520Scrn00_NMAL2520_NMAL2510(bizMsg, glblMsg);
            } else if ("NMAL2520_NMAL2570".equals(screenAplID)) {
                doProcess_NMAL2520_NMAL2570(bizMsg, glblMsg);
            } else if ("NMAL2520_NMAL2530".equals(screenAplID)) {
                doProcess_NMAL2520_NMAL2530(bizMsg, glblMsg);
                onChange_BusinessArea(bizMsg, glblMsg);
                onChange_Tier(bizMsg, glblMsg);
            } else if ("NMAL2520Scrn00_OnChange_Tier".equals(screenAplID)) {
                doProcess_NMAL2520Scrn00_OnChange_Tier(bizMsg, glblMsg);
            } else if ("NMAL2520Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2520Scrn00_CMN_Submit(bizMsg, glblMsg);
// QC#7966
//            } else if ("NMAL2520Scrn00_Filter_Hierarchy".equals(screenAplID)) {
//                doProcess_NMAL2520Scrn00_Filter_Hierarchy(bizMsg, glblMsg);
            } else if ("NMAL2520Scrn00_Delete_Row_Hierarchy".equals(screenAplID)) {
                doProcess_NMAL2520Scrn00_Delete_Row_Hierarchy(bizMsg, glblMsg);
            } else if ("NMAL2520Scrn00_Delete_Row_Resrc".equals(screenAplID)) {
                doProcess_NMAL2520Scrn00_Delete_Row_Resrc(bizMsg, glblMsg);
            } else if ("NMAL2520Scrn00_OnChange_BusinessArea".equals(screenAplID)) {
                doProcess_NMAL2520Scrn00_OnChange_BusinessArea(bizMsg, glblMsg);
                onChange_Tier(bizMsg, glblMsg);
// 2017/12/05 QC#21270 Del Start
//            } else if ("NMAL2520Scrn00_OnBlur_DeriveOrganization".equals(screenAplID)) {
//                doProcess_NMAL2520Scrn00_OnBlur_DeriveOrganization(bizMsg, glblMsg);
// 2017/12/05 QC#21270 Del End
            // QC#7966
            } else if ("NMAL2520Scrn00_OnChange_OrgActvFlg".equals(screenAplID)) {
                doProcess_NMAL2520Scrn00_OnChange_OrgActvFlg(bizMsg, glblMsg);
            } else if ("NMAL2520Scrn00_OnChange_ResrcActvFlg".equals(screenAplID)) {
                doProcess_NMAL2520Scrn00_OnChange_ResrcActvFlg(bizMsg, glblMsg);
                // Add Start 2018/02/14 QC#23905
            } else if ("NMAL2520Scrn00_Link_Resource".equals(screenAplID)) {
                doProcess_NMAL2520Scrn00_Link_Resource(bizMsg, glblMsg);
                // Add End 2018/02/14 QC#23905
            // START 2017/06/14 J.Kim [QC#18924,ADD]
            } else if ("NMAL2520Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL2520Scrn00_PageJump(bizMsg, glblMsg);
            } else if ("NMAL2520Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL2520Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NMAL2520Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL2520Scrn00_PagePrev(bizMsg, glblMsg);
            // END 2017/06/14 J.Kim [QC#18924,ADD]
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
    private void doProcess_NMAL2520Scrn00_CMN_Clear(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        String currentTab = cMsg.xxDplyTab.getValue();

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.B.clear();
        cMsg.B.setValidCount(0);
        cMsg.C.clear();
        cMsg.C.setValidCount(0);
        cMsg.T.clear();
        cMsg.T.setValidCount(0);
        cMsg.clear();
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.B.clear();
        sMsg.B.setValidCount(0);
        sMsg.C.clear();
        sMsg.C.setValidCount(0);
        sMsg.T.clear();
        sMsg.T.setValidCount(0);
        sMsg.Z.clear();
        sMsg.Z.setValidCount(0);
        sMsg.clear();

        cMsg.xxDplyTab.setValue(currentTab);

        doProcess_NMAL2520_INIT(cMsg, sMsg);
    }

    /**
     * <pre>
     * Return from NMAL2510
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2520Scrn00_NMAL2520_NMAL2510(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        // Add Start 2018/02/14 QC#23905
        cMsg.xxHldFlg_H0.clear();

        // Display initial data
        if (ZYPCommonFunc.hasValue(cMsg.orgCd_H1)) {
            searchResource(cMsg, sMsg);
            NMAL2520CommonLogic.setBackUpResource(cMsg, sMsg);
        } else {
            NMAL2520CommonLogic.clearResouceAssignTab(cMsg, sMsg);
        }
        // Add End 2018/02/14 QC#23905

        // 2016/02/08  CSA-QC#2869 Mod Start
        //doProcess_NMAL2520_Search(cMsg, sMsg);
        // Del Start 2018/02/14 QC#23905
//        S21SsmEZDResult ssmResult;
//
//        // ### Resouce Assign ###
//        // new record is escape and create psnCd List
//        ZYPTableUtil.clear(cMsg.Y);
//        ZYPTableUtil.clear(sMsg.Y);
//        int idxY = 0;
//        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
//            if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).psnNum_CB)) {
//                idxY++;
//            }
//        }
//
//        ssmResult = NMAL2520Query.getInstance().getResourceForBackPopup(cMsg, sMsg);
//
//        if (ssmResult.isCodeNormal()) {
//            int queryResCnt = ssmResult.getQueryResultCount();
//            if (queryResCnt + idxY > sMsg.C.length()) {
//                cMsg.setMessageInfo(NMAL2520Constant.NZZM0001W);
//                queryResCnt = sMsg.C.length();
//            }
//
//            // SSM Result
//            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
//            int idxResult = 0;
//            for (; idxResult < resultList.size() && idxResult < sMsg.C.length(); idxResult++) {
//                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(idxResult);
//                NMAL2520_CSMsg sMsgCLine = sMsg.C.no(idxResult);
//
//                if (!ZYPDateUtil.isSameTimeStamp(sMsg.C.no(idxResult).ezUpTime_U1.getValue(), sMsg.C.no(idxResult).ezUpTimeZone_U1.getValue(), (String) resultMap.get("EZUPTIME_U1"), (String) resultMap.get("EZUPTIMEZONE_U1"))
//                        || !ZYPDateUtil.isSameTimeStamp(sMsg.C.no(idxResult).ezUpTime_U2.getValue(), sMsg.C.no(idxResult).ezUpTimeZone_U2.getValue(), (String) resultMap.get("EZUPTIME_U2"), (String) resultMap.get("EZUPTIMEZONE_U2"))
//                        || !ZYPDateUtil.isSameTimeStamp(sMsg.C.no(idxResult).ezUpTime_U3.getValue(), sMsg.C.no(idxResult).ezUpTimeZone_U3.getValue(), (String) resultMap.get("EZUPTIME_U3"), (String) resultMap.get("EZUPTIMEZONE_U3"))) {
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.psnNum_C1, (String) resultMap.get("PSN_NUM"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.orgFuncRoleTpCd_P1, (String) resultMap.get("ORG_FUNC_ROLE_TP_CD"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.psnLastNm_C1, (String) resultMap.get("PSN_LAST_NM"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.psnFirstNm_C1, (String) resultMap.get("PSN_FIRST_NM"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.asgCustFromNm_C1, (String) resultMap.get("ASG_CUST_FROM_NM"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.asgCustToNm_C1, (String) resultMap.get("ASG_CUST_TO_NM"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.effFromDt_C1, (String) resultMap.get("EFF_FROM_DT"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.effThruDt_C1, (String) resultMap.get("EFF_THRU_DT"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.psnCd_C1, (String) resultMap.get("PSN_CD"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.gnrnTpCd_C1, (String) resultMap.get("GNRN_TP_CD"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.ezUpTime_U1, (String) resultMap.get("EZUPTIME_U1"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.ezUpTimeZone_U1, (String) resultMap.get("EZUPTIMEZONE_U1"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.orgStruTpCd_U1, (String) resultMap.get("RELN_ORG_STRU_TP_CD"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.tocCd_U1, (String) resultMap.get("RELN_TOC_CD"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.orgCd_U1, (String) resultMap.get("RELN_ORG_CD"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.ezUpTime_U2, (String) resultMap.get("EZUPTIME_U2"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.ezUpTimeZone_U2, (String) resultMap.get("EZUPTIMEZONE_U2"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.orgChngRqstPk_U2, (BigDecimal) resultMap.get("RQST_ORG_CHNG_RQST_PK"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.ezUpTime_U3, (String) resultMap.get("EZUPTIME_U3"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.ezUpTimeZone_U3, (String) resultMap.get("EZUPTIMEZONE_U3"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.psnCd_U3, (String) resultMap.get("ASG_PSN_CD"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.effFromDt_U3, (String) resultMap.get("ASG_EFF_FROM_DT"));
//                    ZYPEZDItemValueSetter.setValue(sMsgCLine.tocCd_U3, (String) resultMap.get("GNRN_TP_CD"));
//                } else {
//                    EZDMsg.copy(sMsg.C.no(idxResult), null, sMsgCLine, null);
//                }
//            }
//            sMsg.C.setValidCount(idxResult);
//
//            // new record add
//            int idxNew = 0;
//            for (int i = 0; i < sMsg.C.getValidCount(); i++) {
//                if (sMsg.C.getValidCount() + idxNew == sMsg.C.length()) {
//                    break;
//                }
//
//                if (!ZYPCommonFunc.hasValue(cMsg.C.no(i).orgChngRqstPk_U2)) {
//                    EZDMsg.copy(cMsg.C.no(i), null, sMsg.C.no(sMsg.C.getValidCount() + idxNew), null);
//                    idxNew++;
//                }
//            }
//            sMsg.C.setValidCount(sMsg.C.getValidCount() + idxNew);
//            ZYPTableUtil.clear(cMsg.C);
//
//            // Move first page date from SMsg to CMsg
//            int idx = 0;
//            for (; idx < sMsg.C.getValidCount(); idx++) {
//                if (idx == cMsg.C.length()) {
//                    break;
//                }
//
//                EZDMsg.copy(sMsg.C.no(idx), null, cMsg.C.no(idx), null);
//            }
//            cMsg.C.setValidCount(idx);
//        }
//
//        NMAL2520CommonLogic.setBackUp(cMsg, sMsg);
        // Del End 2018/02/14 QC#23905
        // 2016/02/08  CSA-QC#2869 Mod End
    }

    /**
     * <pre>
     * Reset Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2520Scrn00_CMN_Reset(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        // doProcess_NMAL2520Scrn00_OnChange_BusinessArea(cMsg, sMsg);
        doProcess_NMAL2520_INIT(cMsg, sMsg);
    }

    /**
     * <pre>
     * Init Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2520_INIT(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        // QC#7966
        cMsg.xxChkBox_F1.clear();
        cMsg.xxChkBox_F2.clear();
        // Add Start 2018/02/14 QC#23905
        cMsg.xxHldFlg_H0.clear();
        // Add End 2018/02/14 QC#23905

        // 2017/11/20 QC#21344-2 add start
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
        // 2017/11/20 QC#21344-2 add start

        onChange_BusinessArea(cMsg, sMsg);
        createPulldownList(cMsg, sMsg);

        if (NMAL2520Constant.ADD_CHILD_FROM_NMAL2500.equals(cMsg.xxEventFlgTxt.getValue())) {
            NMAL2520CommonLogic.initialforAddChild(cMsg, sMsg);

        } else if (ZYPCommonFunc.hasValue(cMsg.orgNm_H1)) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, NMAL2520Constant.TAB_BUILD_HIERARCHY);
            doProcess_NMAL2520_Search(cMsg, sMsg);
        }

    }

    /**
     * <pre>
     * Delete row in hierarchy tab
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2520Scrn00_Delete_Row_Hierarchy(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.A, NMAL2520Constant.CHKBOX_A, ZYPConstant.CHKBOX_ON_Y);

        // 2016/03/31 CSA-QC#5945 Add Start
        // 2018/01/10 QC#20233 del start
//        boolean hasActiveParentOrg = false;
//        boolean isDelete = false;
//        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
//            for (int idx = 0; idx < checkList.size(); idx++) {
//                if (i == idx) {
//                    isDelete = true;
//                    break;
//                }
//            }
//
//            if (isDelete) {
//                continue;
//            }
//
//            if (NMAL2520CommonLogic.checkActiveDate(cMsg.B.no(i).effFromDt_B1.getValue(), cMsg.B.no(i).effThruDt_B1.getValue(), ZYPDateUtil.getSalesDate())) {
//                hasActiveParentOrg = true;
//                break;
//            }
//            isDelete = false;
//        }
//
//
//        if (!hasActiveParentOrg) {
//            for (int idx = 0; idx < cMsg.B.getValidCount(); idx++) {
//                if (NMAL2520CommonLogic.checkActiveDate(cMsg.B.no(idx).effFromDt_B1.getValue(), cMsg.B.no(idx).effThruDt_B1.getValue(), ZYPDateUtil.getSalesDate())) {
//                    cMsg.setMessageInfo(NMAL2520Constant.NMAM8361E);
//                    return;
//                }
//            }
//        }
        // 2018/01/10 QC#20233 del end
        // 2016/03/31 CSA-QC#5945 Add End

        for (int i = 0; i < checkList.size(); i++) {
            int index = checkList.get(i);
            checkList.set(i, index);

            if (ZYPCommonFunc.hasValue(sMsg.A.no(index).gnrnTpCd_A1)) {
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).orgCd_X1, cMsg.orgCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).prntOrgCd_X1, cMsg.A.no(index).orgCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).orgStruTpCd_X1, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).effFromDt_X1, cMsg.A.no(index).effFromDt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).ezUpTime_X1, cMsg.A.no(index).ezUpTime_A1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).ezUpTimeZone_X1, cMsg.A.no(index).ezUpTimeZone_A1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).dsOrgRelnPk_X1, cMsg.A.no(index).dsOrgRelnPk_A1); // 2017/03/07 S21_NA#17760 Add
                // 2016/03/31 CSA-QC#5945 Add Start
                // Mod Start 2018/03/26 QC#25037
//                if (GNRN_TP.FUTURE.equals(cMsg.A.no(index).gnrnTpCd_A1.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).gnrnTpCd_X1, GNRN_TP.FUTURE);
//                } else {
//                    ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).gnrnTpCd_X1, GNRN_TP.DELETE);
//                }
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).gnrnTpCd_X1, GNRN_TP.DELETE);
                // Mod End 2018/03/26 QC#25037
                // 2016/03/31 CSA-QC#5945 Add End

                sMsg.X.setValidCount(sMsg.X.getValidCount() + 1);
            }
        }

        ZYPTableUtil.deleteRows(cMsg.A, checkList);
        ZYPTableUtil.deleteRows(sMsg.A, checkList);
    }

    /**
     * <pre>
     * Delete row in resource tab
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2520Scrn00_Delete_Row_Resrc(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        // START 2017/06/14 J.Kim [QC#18924,ADD]
        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NMAL2520CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(sMsg.C, NMAL2520Constant.CHKBOX_C, ZYPConstant.CHKBOX_ON_Y);

        if (checkList.size() <= 0) {
            for (int i = 0; i < cMsg.C.getValidCount(); i++) {
                cMsg.C.no(i).xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NZZM0011E);
            }
            return;
        }

        for (int index : checkList) {
            if (ZYPCommonFunc.hasValue(sMsg.C.no(index).gnrnTpCd_C1)) {
                // can not delete data which already deleted or expired
                if (GNRN_TP.PAST.equals(sMsg.C.no(index).gnrnTpCd_C1.getValue())) {
                    sMsg.C.no(index).xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NMAM8230E);
                    NMAL2520CommonLogic.jumpToErrorPage(cMsg, sMsg);
                    return;
                }
            }
        }
        // END 2017/06/14 J.Kim [QC#18924,ADD]

        for (int i = 0; i < checkList.size(); i++) {
            int index = checkList.get(i);
            checkList.set(i, index);
            if (ZYPCommonFunc.hasValue(sMsg.C.no(index).tocCd_U1)) {
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).orgStruTpCd_Y1, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).tocCd_Y1, sMsg.C.no(index).tocCd_U1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).orgCd_Y1, sMsg.C.no(index).orgCd_U1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).ezUpTime_Y1, sMsg.C.no(index).ezUpTime_U1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).ezUpTimeZone_Y1, sMsg.C.no(index).ezUpTimeZone_U1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).tocCd_Y2, sMsg.C.no(index).tocCd_U1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).orgChngRqstPk_Y2, sMsg.C.no(index).orgChngRqstPk_U2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).ezUpTime_Y2, sMsg.C.no(index).ezUpTime_U2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).ezUpTimeZone_Y2, sMsg.C.no(index).ezUpTimeZone_U2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).tocCd_Y3, sMsg.C.no(index).tocCd_U3.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).psnCd_Y3, sMsg.C.no(index).psnCd_U3.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).effFromDt_Y3, sMsg.C.no(index).effFromDt_U3.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).ezUpTime_Y3, sMsg.C.no(index).ezUpTime_U3.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).ezUpTimeZone_Y3, sMsg.C.no(index).ezUpTimeZone_U3.getValue());
                // 2016/03/31 CSA-QC#5945 Add Start
                // if (GNRN_TP.FUTURE.equals(cMsg.C.no(index).gnrnTpCd_C1.getValue())) {
                //     ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).gnrnTpCd_Y1, GNRN_TP.FUTURE);
                // } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).gnrnTpCd_Y1, GNRN_TP.DELETE);
                // }
                // 2016/03/31 CSA-QC#5945 Add End

                sMsg.Y.setValidCount(sMsg.Y.getValidCount() + 1);
            }
        }

        // START 2017/06/14 J.Kim [QC#18924,MOD]
        //ZYPTableUtil.deleteRows(cMsg.C, checkList);
        ZYPTableUtil.deleteRows(sMsg.C, checkList);

        int beforePageNum = NMAL2520CommonLogic.getPageNum(cMsg.C.length(), cMsg.xxPageShowFromNum.getValueInt());
        int afterMaxPageNum = NMAL2520CommonLogic.getPageNum(cMsg.C.length(), sMsg.C.getValidCount());
        if (afterMaxPageNum < beforePageNum) {
            pageFromNum = cMsg.C.length() * (afterMaxPageNum - 1);
        }
        NMAL2520CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
        cMsg.xxPageShowOfNum.setValue(sMsg.C.getValidCount());
        // END 2017/06/14 J.Kim [QC#18924,ADD]
    }

    /**
     * <pre>
     * Onchange Business Area Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2520Scrn00_OnChange_BusinessArea(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        onChange_BusinessArea(cMsg, sMsg);
// 2017/12/05 QC#21270 Del Start
//        if (!ZYPCommonFunc.hasValue(cMsg.orgCd_H1) && ZYPCommonFunc.hasValue(cMsg.orgNm_H1)) {
//            doProcess_NMAL2520_Search(cMsg, sMsg);
//        }
// 2017/12/05 QC#21270 Del End
    }

    /**
     * <pre>
     * Onchange Business Area Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void onChange_BusinessArea(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        cMsg.orgFuncRoleTpCd_C1.clear();
        cMsg.orgFuncRoleTpNm_C1.clear();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(NMAL2520Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("globalCmpyCd", getGlobalCompanyCode());

        // Organization Function Role Type
        S21SsmEZDResult orgFuncRoleTpWithBusinessAreaPulldownList = NMAL2520Query.getInstance().getOrgFuncRoleTpWithBusinessAreaPulldownList(ssmParam);

        if (orgFuncRoleTpWithBusinessAreaPulldownList.isCodeNormal()) {
            List<Map> orgFuncRoleTpWithBusinessAreaList = (List<Map>) orgFuncRoleTpWithBusinessAreaPulldownList.getResultObject();
            NMAL2520CommonLogic.createPulldownList(cMsg.orgFuncRoleTpCd_C1, cMsg.orgFuncRoleTpNm_C1, orgFuncRoleTpWithBusinessAreaList, new String[] {NMAL2520Constant.ORG_FUNC_ROLE_TP_CD_DBCOLUMN,
                    NMAL2520Constant.ORG_FUNC_ROLE_TP_NM_DBCOLUMN });
        }
    }
// 2017/12/05 QC#21270 Del Start
//    /**
//     * <pre>
//     * Check modification before transition
//     * </pre>
//     * @param bizMsg Business Msg
//     * @param sMsg Global Msg
//     */
//    private void doProcess_NMAL2520Scrn00_TAB_Build(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
//
//        if (NMAL2520CommonLogic.isChangedPrevTab(cMsg, sMsg)) {
//            return;
//        }
//
//        cMsg.xxDplyTab.setValue(NMAL2520Constant.TAB_BUILD_HIERARCHY);
//        doProcess_NMAL2520_Search(cMsg, sMsg);
//    }
//
//    /**
//     * <pre>
//     * Check modification before transition
//     * </pre>
//     * @param bizMsg Business Msg
//     * @param sMsg Global Msg
//     */
//    private void doProcess_NMAL2520Scrn00_TAB_View(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
//
//        if (NMAL2520CommonLogic.isChangedPrevTab(cMsg, sMsg)) {
//            return;
//        }
//
//        cMsg.xxDplyTab.setValue(NMAL2520Constant.TAB_VIEW_HIERARCHY);
//        doProcess_NMAL2520_Search(cMsg, sMsg);
//    }
//
//    /**
//     * <pre>
//     * Check modification before transition
//     * </pre>
//     * @param bizMsg Business Msg
//     * @param sMsg Global Msg
//     */
//    private void doProcess_NMAL2520Scrn00_TAB_Asign(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
//
//        if (NMAL2520CommonLogic.isChangedPrevTab(cMsg, sMsg)) {
//            return;
//        }
//
//        cMsg.xxDplyTab.setValue(NMAL2520Constant.TAB_RESRC_ASIGN);
//        doProcess_NMAL2520_Search(cMsg, sMsg);
//    }
// 2017/12/05 QC#21270 Del End
    /**
     * <pre>
     * Add for Build
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2520Scrn00_Insert_Row_Hierarchy(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        EZDMsgArray targetArray = cMsg.A;
        int newRowIndex = targetArray.getValidCount();
        targetArray.get(newRowIndex).clear();
        targetArray.setValidCount(newRowIndex + 1);
    }

    /**
     * <pre>
     * Add for Resrc
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2520Scrn00_Insert_Row_Resrc(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

       // START 2017/06/14 J.Kim [QC#18924,DEL]
       // EZDMsgArray targetArray = cMsg.C;
       // int newRowIndex = targetArray.getValidCount();
       // targetArray.get(newRowIndex).clear();
       // targetArray.setValidCount(newRowIndex + 1);
       // END 2017/06/14 J.Kim [QC#18924,DEL]

        // START 2017/06/14 J.Kim [QC#18924,MOD]
        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NMAL2520CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        EZDMsgArray targetArray = sMsg.C;
        int newRowIndex = targetArray.getValidCount();
        targetArray.get(newRowIndex).clear();
        targetArray.setValidCount(newRowIndex + 1);

        int rowIndex = NMAL2520CommonLogic.convertFirstRowIndex(cMsg.C.length(), newRowIndex);
        NMAL2520CommonLogic.pagenation(cMsg, sMsg, rowIndex);
        cMsg.xxPageShowOfNum.setValue(sMsg.C.getValidCount());
        // END 2017/06/14 J.Kim [QC#18924,MOD]

        // 2016/03/17 CSA-QC#5654 Add Start
        NMAL2520CommonLogic.createOrgFuncRoleTpPullDown(cMsg, getGlobalCompanyCode(), cMsg.bizAreaOrgCd_P1.getValue());
        // 2016/03/17 CSA-QC#5654 Add End
    }

    /**
     * <pre>
     * Init Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2520Scrn00_CMN_Submit(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        if (NMAL2520Constant.MESSAGE_KIND_WARN.equals(cMsg.getMessageKind())) {
            return;
        }

        if (NMAL2520Constant.MESSAGE_KIND_ERROR.equals(cMsg.getMessageKind())) {
            return;
        }

        doProcess_NMAL2520_Search(cMsg, sMsg);
    }

    /**
     * <pre>
     * OnChange Tier
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2520Scrn00_OnChange_Tier(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        onChange_Tier(cMsg, sMsg);
// 2017/12/05 QC#21270 Del Start
//        if (!ZYPCommonFunc.hasValue(cMsg.orgCd_H1)) {
//            doProcess_NMAL2520_Search(cMsg, sMsg);
//        }
// 2017/12/05 QC#21270 Del End
    }

    /**
     * <pre>
     * OnChange Tier
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void onChange_Tier(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        // 2016/03/29 CSA-QC#6179 Mod Start
        S21SsmEZDResult ssmResult;
        ssmResult = NMAL2520Query.getInstance().getTierDescription(cMsg, sMsg);

        if (!ssmResult.isCodeNormal()) {
            cMsg.struDfnDescTxt_H1.clear();
        } else {
            List resultList = (List) ssmResult.getResultObject();

            Map map = (Map) resultList.get(0);

            if (map.get(NMAL2520Constant.STRU_DFN_DESC_TXT_DBCOLUMN) != null) {
                cMsg.struDfnDescTxt_H1.setValue((String) map.get(NMAL2520Constant.STRU_DFN_DESC_TXT_DBCOLUMN));
            } else {
                cMsg.struDfnDescTxt_H1.clear();
            }
        }
        // 2016/03/29 CSA-QC#6179 Mod End
    }

    /**
     * <pre>
     * Add for Resrc
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2520_NMAL2570(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        int index = cMsg.xxCellIdx.getValueInt();

        S21SsmEZDResult ssmResult;
        ssmResult = NMAL2520Query.getInstance().getResourceAdditional(cMsg, index);
        if (!ssmResult.isCodeNormal()) {
            cMsg.setMessageInfo(NMAL2520Constant.NMAM0038I);
        } else {
            List resultList = (List) ssmResult.getResultObject();

            Map map = (Map) resultList.get(0);

            if (map.get(NMAL2520Constant.PSN_CD_DBCOLUMN) != null) {
                // 2018/06/12 QC#20220 Mod Start
                //sMsg.C.no(index).psnCd_U3.setValue((String) map.get(NMAL2520Constant.PSN_CD_DBCOLUMN));
                cMsg.C.no(index).psnCd_U3.setValue((String) map.get(NMAL2520Constant.PSN_CD_DBCOLUMN));
                // 2018/06/12 QC#20220 Mod End
            }

            if (map.get(NMAL2520Constant.PSN_FIRST_NM_DBCOLUMN) != null) {
                // 2018/06/12 QC#20220 Mod Start
                //sMsg.C.no(index).psnFirstNm_C1.setValue((String) map.get(NMAL2520Constant.PSN_FIRST_NM_DBCOLUMN));
                cMsg.C.no(index).psnFirstNm_C1.setValue((String) map.get(NMAL2520Constant.PSN_FIRST_NM_DBCOLUMN));
                // 2018/06/12 QC#20220 Mod End
            }

            if (map.get(NMAL2520Constant.PSN_LAST_NM_DBCOLUMN) != null) {
                // 2018/06/12 QC#20220 Mod Start
                //sMsg.C.no(index).psnLastNm_C1.setValue((String) map.get(NMAL2520Constant.PSN_LAST_NM_DBCOLUMN));
                cMsg.C.no(index).psnLastNm_C1.setValue((String) map.get(NMAL2520Constant.PSN_LAST_NM_DBCOLUMN));
                // 2018/06/12 QC#20220 Mod End
            }

            if (map.get(NMAL2520Constant.COA_CMPY_CD_DBCOLUMN) != null) {
                // 2018/06/12 QC#20220 Mod Start
                //sMsg.C.no(index).coaCmpyCd_U2.setValue((String) map.get(NMAL2520Constant.COA_CMPY_CD_DBCOLUMN));
                cMsg.C.no(index).coaCmpyCd_U2.setValue((String) map.get(NMAL2520Constant.COA_CMPY_CD_DBCOLUMN));
                // 2018/06/12 QC#20220 Mod End
            }

            if (map.get(NMAL2520Constant.COA_EXTN_CD_DBCOLUMN) != null) {
                // 2018/06/12 QC#20220 Mod Start
                //sMsg.C.no(index).coaExtnCd_U2.setValue((String) map.get(NMAL2520Constant.COA_EXTN_CD_DBCOLUMN));
                cMsg.C.no(index).coaExtnCd_U2.setValue((String) map.get(NMAL2520Constant.COA_EXTN_CD_DBCOLUMN));
                // 2018/06/12 QC#20220 Mod End
            }

            if (map.get(NMAL2520Constant.COA_BR_CD_DBCOLUMN) != null) {
                // 2018/06/12 QC#20220 Mod Start
                //sMsg.C.no(index).coaBrCd_U2.setValue((String) map.get(NMAL2520Constant.COA_BR_CD_DBCOLUMN));
                cMsg.C.no(index).coaBrCd_U2.setValue((String) map.get(NMAL2520Constant.COA_BR_CD_DBCOLUMN));
                // 2018/06/12 QC#20220 Mod End
            }

            if (map.get(NMAL2520Constant.COA_CC_CD_DBCOLUMN) != null) {
                // 2018/06/12 QC#20220 Mod Start
                //sMsg.C.no(index).coaCcCd_U2.setValue((String) map.get(NMAL2520Constant.COA_CC_CD_DBCOLUMN));
                cMsg.C.no(index).coaCcCd_U2.setValue((String) map.get(NMAL2520Constant.COA_CC_CD_DBCOLUMN));
                // 2018/06/12 QC#20220 Mod End
            }
            // 2016/10/04  QC#14809 Add Start
            if (map.get(NMAL2520Constant.EFF_FROM_DT_DBCOLUMN) != null) {
                // 2018/06/12 QC#20220 Mod Start
                //sMsg.C.no(index).effFromDt_U4.setValue((String) map.get(NMAL2520Constant.EFF_FROM_DT_DBCOLUMN));
                cMsg.C.no(index).effFromDt_U4.setValue((String) map.get(NMAL2520Constant.EFF_FROM_DT_DBCOLUMN));
                // 2018/06/12 QC#20220 Mod End
            }

            if (map.get(NMAL2520Constant.EFF_THRU_DT_DBCOLUMN) != null) {
                // 2018/06/12 QC#20220 Mod Start
                //sMsg.C.no(index).effThruDt_U4.setValue((String) map.get(NMAL2520Constant.EFF_THRU_DT_DBCOLUMN));
                cMsg.C.no(index).effThruDt_U4.setValue((String) map.get(NMAL2520Constant.EFF_THRU_DT_DBCOLUMN));
                // 2018/06/12 QC#20220 Mod End
            }
            // 2016/10/04  QC#14809 Add End
        }

    }

    /**
     * <pre>
     * Add for Org
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2520_NMAL2530(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        String event = cMsg.xxEventFlgTxt.getValue();

        if (NMAL2520Constant.OPEN_WIN_ORGANIZATION_LOOKUP.equals(event)) {

            doProcess_NMAL2520_Search(cMsg, sMsg);
            if (ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
                return;
            }

            S21SsmEZDResult ssmResult;
            ssmResult = NMAL2520Query.getInstance().getOrganizationAdditional(cMsg);
            if (!ssmResult.isCodeNormal()) {
                cMsg.setMessageInfo(NMAL2520Constant.NMAM0038I);
            } else {
                List resultList = (List) ssmResult.getResultObject();

                String orgTierCd = (String) ((Map) resultList.get(0)).get(NMAL2520Constant.ORG_TIER_CD_DBCOLUMN);
                if (orgTierCd != null) {
                    cMsg.orgTierCd_P1.setValue(orgTierCd);
                }

                String firstOrgCd = (String) ((Map) resultList.get(0)).get(NMAL2520Constant.FIRST_ORG_CD_DBCOLUMN);
                if (firstOrgCd != null) {
                    cMsg.bizAreaOrgCd_P1.setValue(firstOrgCd);
                }
            }
        } else if (NMAL2520Constant.OPEN_WIN_ORGANIZATION_LOOKUP_DETAIL.equals(event)) {

            int index = cMsg.xxCellIdx.getValueInt();

            S21SsmEZDResult ssmResult;
            ssmResult = NMAL2520Query.getInstance().getOrganizationAdditional(cMsg, index);
            if (!ssmResult.isCodeNormal()) {
                cMsg.setMessageInfo(NMAL2520Constant.NMAM0038I);
            } else {
                List resultList = (List) ssmResult.getResultObject();

                String orgTierCd = (String) ((Map) resultList.get(0)).get(NMAL2520Constant.ORG_TIER_CD_DBCOLUMN);
                if (orgTierCd != null) {
                    cMsg.A.no(index).orgTierCd_A1.setValue(orgTierCd);
                }

                String firstOrgCd = (String) ((Map) resultList.get(0)).get(NMAL2520Constant.FIRST_ORG_CD_DBCOLUMN);
                if (firstOrgCd != null) {
                    cMsg.A.no(index).firstOrgCd_A1.setValue(firstOrgCd);
                }
            }
        }
    }

// QC#7966
//    /**
//     * <pre>
//     * Add for Org
//     * </pre>
//     * @param bizMsg Business Msg
//     * @param sMsg Global Msg
//     */
//    private void doProcess_NMAL2520Scrn00_Filter_Hierarchy(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
//
//        S21SsmEZDResult ssmResult;
//        int queryResCnt = 0;
//
//        cMsg.A.clear();
//        cMsg.A.setValidCount(0);
//
//        ssmResult = NMAL2520Query.getInstance().getOrganizationParentWithFilter(cMsg, sMsg);
//
//        if (ssmResult.isCodeNormal()) {
//
//            queryResCnt = ssmResult.getQueryResultCount();
//            if (queryResCnt > sMsg.A.length()) {
//                cMsg.setMessageInfo(NMAL2520Constant.NZZM0001W);
//            }
//
//            // Move first page date from SMsg to CMsg
//            int i = 0;
//            for (; i < sMsg.A.getValidCount(); i++) {
//                if (i == cMsg.A.length()) {
//                    break;
//                }
//
//                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
//            }
//            cMsg.A.setValidCount(i);
//
//        } else {
//            cMsg.setMessageInfo(NMAL2520Constant.NMAM0038I);
//        }
//
//         EZDMsg.copy(sMsg, null, cMsg, null);
//    }

    /**
     * <pre>
     * Search Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2520_Search(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        // 2016/02/08  CSA-QC#2869 Mod Start
        cMsg.xxWrnSkipFlg_H1.clear();
        // Add Start 2018/02/14 QC#23905
        cMsg.xxHldFlg_H0.clear();
        // Add End 2018/02/14 QC#23905
        S21SsmEZDResult ssmResult;

        // ### Header ###
        ssmResult = NMAL2520Query.getInstance().getOrganization(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt == 1) {
                sMsg.xxRsltFlg.setValue(ZYPConstant.FLG_ON_Y);
                // Add Start 2016/11/08 M.Ohno S21_NA#2680
                ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistCratByNm, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistCratByNm.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistUpdByNm, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistUpdByNm.getValue()));
                // Add End   2016/11/08 M.Ohno S21_NA#2680
                EZDMsg.copy(sMsg, null, cMsg, null);
            } else {
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxRsltFlg.getValue())) {
                    NMAL2520CommonLogic.clearAllDetails(cMsg, sMsg);
                    cMsg.xxRsltFlg.setValue(ZYPConstant.FLG_OFF_N);
                }
                cMsg.setMessageInfo(NMAL2520Constant.NMAM8344I);
                return;
            }
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxRsltFlg.getValue())) {
                NMAL2520CommonLogic.clearAllDetails(cMsg, sMsg);
                cMsg.xxRsltFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            cMsg.setMessageInfo(NMAL2520Constant.NMAM8344I);
            return;
        }

        // 2016/03/04 CSA-QC#4654 Add Start
        NMAL2520CommonLogic.setBusinessAreaProtection(cMsg);
        // 2016/03/04 CSA-QC#4654 Add End

        // ### Buid Hierarchy ###
        // Parent Organization/Team
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.X.clear();
        cMsg.X.setValidCount(0);
        sMsg.X.clear();
        sMsg.X.setValidCount(0);

        ssmResult = NMAL2520Query.getInstance().getOrganizationParent(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NMAL2520Constant.NZZM0001W);
                queryResCnt = sMsg.A.length();
            }

            // Move first page date from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                // Add Start 2016/11/07 M.Ohno S21_NA#2680
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(i).xxRecHistCratByNm_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(i).xxRecHistUpdByNm_A1.getValue()));
                // Add End   2016/11/08 M.Ohno S21_NA#2680
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);
        }

        // Child Organization/Team
        cMsg.B.clear();
        cMsg.B.setValidCount(0);

        ssmResult = NMAL2520Query.getInstance().getOrganizationChildren(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo(NMAL2520Constant.NZZM0001W);
                queryResCnt = sMsg.B.length();
            }

            // Move first page date from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.B.getValidCount(); i++) {
                if (i == cMsg.B.length()) {
                    break;
                }

                // Add Start 2016/11/07 M.Ohno S21_NA#2680
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRecHistCratByNm_B1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.B.no(i).xxRecHistCratByNm_B1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRecHistUpdByNm_B1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.B.no(i).xxRecHistUpdByNm_B1.getValue()));
                // Add End   2016/11/08 M.Ohno S21_NA#2680
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }
            cMsg.B.setValidCount(i);
        }

        // ### View Hierarchy ###
        cMsg.T.clear();
        cMsg.T.setValidCount(0);

        ssmResult = NMAL2520Query.getInstance().getOrganizationHierarchy(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.T.length()) {
                cMsg.setMessageInfo(NMAL2520Constant.NZZM0001W);
                queryResCnt = sMsg.T.length();
            }

            // Move first page date from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.T.getValidCount(); i++) {
                if (i == cMsg.T.length()) {
                    break;
                }

                // 2016/02/23 CSA-QC#1931 Delete Start
                // convDotToOrgName(sMsg, i);
                // 2016/02/23 CSA-QC#1931 Delete End
                EZDMsg.copy(sMsg.T.no(i), null, cMsg.T.no(i), null);
            }
            cMsg.T.setValidCount(i);
        }

        // ### Resouce Assign ###
        cMsg.C.clear();
        cMsg.C.setValidCount(0);
        // START 2017/06/14 J.Kim [QC#18924,ADD]
        sMsg.C.clear();
        sMsg.C.setValidCount(0);
        // END 2017/06/14 J.Kim [QC#18924,ADD]
        cMsg.Y.clear();
        cMsg.Y.setValidCount(0);
        sMsg.Y.clear();
        sMsg.Y.setValidCount(0);
        // 2018/10/24 S21_NA#28351 Add Start
        cMsg.Z.clear();
        cMsg.Z.setValidCount(0);
        sMsg.Z.clear();
        sMsg.Z.setValidCount(0);
        // 2018/10/24 S21_NA#28351 Add End

        ssmResult = NMAL2520Query.getInstance().getResource(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.C.length()) {
                cMsg.setMessageInfo(NMAL2520Constant.NZZM0001W);
                queryResCnt = sMsg.C.length();
            }

            // Move first page date from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.C.getValidCount(); i++) {
                if (i == cMsg.C.length()) {
                    break;
                }

                // Add Start 2016/11/07 M.Ohno S21_NA#2680
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).xxRecHistCratByNm_C1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.C.no(i).xxRecHistCratByNm_C1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).xxRecHistUpdByNm_C1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.C.no(i).xxRecHistUpdByNm_C1.getValue()));
                // Add End   2016/11/08 M.Ohno S21_NA#2680
                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i), null);
            }
            cMsg.C.setValidCount(i);

            // 2016/03/17 CSA-QC#5654 Add Start
            NMAL2520CommonLogic.createOrgFuncRoleTpPullDown(cMsg, getGlobalCompanyCode(), cMsg.bizAreaOrgCd_P1.getValue());
            // 2016/03/17 CSA-QC#5654 Add End
        }
        // 2016/02/08  CSA-QC#2869 Mod End

        // START 2017/06/14 J.Kim [QC#18924,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt, BigDecimal.valueOf(sMsg.C.getValidCount()));

        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.C.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.C.getValidCount());
        // END 2017/06/14 J.Kim [QC#18924,ADD]

        // 2016/02/23 CSA-QC#4336 Add Start
        NMAL2520CommonLogic.setBackUp(cMsg, sMsg);
        // 2016/02/23 CSA-QC#4336 Add End

    }

    /**
     * OnBlur event Derive Organization
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL2520Scrn00_OnBlur_DeriveOrganization(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        doProcess_NMAL2520_Search(cMsg, sMsg);

        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
           onChange_Tier(cMsg, sMsg);
        }
    }

    /**
     * The method explanation: create Pulldown List.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void createPulldownList(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        S21UserProfileService profileAccessor = getUserProfileService();
        String globalCmpyCd = profileAccessor.getGlobalCompanyCode();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(NMAL2520Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("globalCmpyCd", globalCmpyCd);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());

        // Business Area
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_OFF_N);
        S21SsmEZDResult bizAreaOrgPulldownList = NMAL2520Query.getInstance().getBizAreaOrgPulldownList(ssmParam);

        if (bizAreaOrgPulldownList.isCodeNormal()) {
            List<Map> bizAreaOrgList = (List<Map>) bizAreaOrgPulldownList.getResultObject();
            NMAL2520CommonLogic.createPulldownList(cMsg.bizAreaOrgCd_H1, cMsg.bizAreaOrgNm_H1, bizAreaOrgList, new String[] {NMAL2520Constant.BIZ_AREA_ORG_CD_DBCOLUMN, NMAL2520Constant.BIZ_AREA_ORG_NM_DBCOLUMN });
        }

        // Org Tier
        S21SsmEZDResult orgTierpPulldownList = NMAL2520Query.getInstance().getOrgTierPulldownList(ssmParam);
        if (orgTierpPulldownList.isCodeNormal()) {
            List<Map> orgTierList = (List<Map>) orgTierpPulldownList.getResultObject();
            NMAL2520CommonLogic.createPulldownList(cMsg.orgTierCd_H1, cMsg.orgTierNm_H1, orgTierList, new String[] {NMAL2520Constant.ORG_TIER_CD_DBCOLUMN, NMAL2520Constant.ORG_TIER_NM_DBCOLUMN });
        }

        // CSR Region
        S21SsmEZDResult csrRgTpPulldownList = NMAL2520Query.getInstance().getCsrRgTpPulldownList(ssmParam);
        if (csrRgTpPulldownList.isCodeNormal()) {
            List<Map> csrRgTpList = (List<Map>) csrRgTpPulldownList.getResultObject();
            NMAL2520CommonLogic.createPulldownList(cMsg.csrRgTpCd_H1, cMsg.csrRgTpNm_H1, csrRgTpList, new String[] {NMAL2520Constant.CSR_RG_TP_CD_DBCOLUMN, NMAL2520Constant.CSR_RG_TP_NM_DBCOLUMN });
        }

        // Line Of Business
        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, cMsg.lineBizTpCd_H1, cMsg.lineBizTpNm_H1);

        // 2016/03/17 CSA-QC#5654 Mod Start
        // Organization Function Role Type
        if (!ZYPCommonFunc.hasValue(cMsg.bizAreaOrgCd_P1)) { // Add 2016/11/30 M.Ohno S21_NA#16236
            NMAL2520CommonLogic.createOrgFuncRoleTpPullDown(cMsg, getGlobalCompanyCode(), NMAL2520Constant.FIRST_TIER_CD);
        } // Add 2016/11/30 M.Ohno S21_NA#16236
        // 2016/03/17 CSA-QC#5654 Mod End

    }

    // 2016/02/23 CSA-QC#1931 Delete Start
//    private void convDotToOrgName(NMAL2520SMsg sMsg, int i) {
//
//        String targetTier = sMsg.T.no(i).orgTierCd_T.getValue();
//
//        if ("1".equals(targetTier)) {
//            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
//                sMsg.T.no(i).orgNm_0.setValue(NMAL2520Constant.DOT);
//            }
//        } else if ("2".equals(targetTier)) {
//            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
//                clearDotForNoParentOrg(sMsg, i);
//            } else {
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
//                    sMsg.T.no(i).orgNm_0.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
//                    sMsg.T.no(i).orgNm_1.setValue(NMAL2520Constant.DOT);
//                }
//            }
//        } else if ("3".equals(targetTier)) {
//            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
//                clearDotForNoParentOrg(sMsg, i);
//            } else {
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
//                    sMsg.T.no(i).orgNm_0.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
//                    sMsg.T.no(i).orgNm_1.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
//                    sMsg.T.no(i).orgNm_2.setValue(NMAL2520Constant.DOT);
//                }
//            }
//        } else if ("4".equals(targetTier)) {
//            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
//                clearDotForNoParentOrg(sMsg, i);
//            } else {
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
//                    sMsg.T.no(i).orgNm_0.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
//                    sMsg.T.no(i).orgNm_1.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
//                    sMsg.T.no(i).orgNm_2.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
//                    sMsg.T.no(i).orgNm_3.setValue(NMAL2520Constant.DOT);
//                }
//            }
//        } else if ("5".equals(targetTier)) {
//            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_5)) {
//                clearDotForNoParentOrg(sMsg, i);
//            } else {
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
//                    sMsg.T.no(i).orgNm_0.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
//                    sMsg.T.no(i).orgNm_1.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
//                    sMsg.T.no(i).orgNm_2.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
//                    sMsg.T.no(i).orgNm_3.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
//                    sMsg.T.no(i).orgNm_4.setValue(NMAL2520Constant.DOT);
//                }
//            }
//        } else if ("6".equals(targetTier)) {
//            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_6)) {
//                clearDotForNoParentOrg(sMsg, i);
//            } else {
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
//                    sMsg.T.no(i).orgNm_0.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
//                    sMsg.T.no(i).orgNm_1.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
//                    sMsg.T.no(i).orgNm_2.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
//                    sMsg.T.no(i).orgNm_3.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
//                    sMsg.T.no(i).orgNm_4.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_5)) {
//                    sMsg.T.no(i).orgNm_5.setValue(NMAL2520Constant.DOT);
//                }
//            }
//        } else if ("7".equals(targetTier)) {
//            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_7)) {
//                clearDotForNoParentOrg(sMsg, i);
//            } else {
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
//                    sMsg.T.no(i).orgNm_0.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
//                    sMsg.T.no(i).orgNm_1.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
//                    sMsg.T.no(i).orgNm_2.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
//                    sMsg.T.no(i).orgNm_3.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
//                    sMsg.T.no(i).orgNm_4.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_5)) {
//                    sMsg.T.no(i).orgNm_5.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_6)) {
//                    sMsg.T.no(i).orgNm_6.setValue(NMAL2520Constant.DOT);
//                }
//            }
//        } else if ("8".equals(targetTier)) {
//            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_8)) {
//                clearDotForNoParentOrg(sMsg, i);
//            } else {
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
//                    sMsg.T.no(i).orgNm_0.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
//                    sMsg.T.no(i).orgNm_1.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
//                    sMsg.T.no(i).orgNm_2.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
//                    sMsg.T.no(i).orgNm_3.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
//                    sMsg.T.no(i).orgNm_4.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_5)) {
//                    sMsg.T.no(i).orgNm_5.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_6)) {
//                    sMsg.T.no(i).orgNm_6.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_7)) {
//                    sMsg.T.no(i).orgNm_7.setValue(NMAL2520Constant.DOT);
//                }
//            }
//
//        } else if ("9".equals(targetTier)) {
//            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_9)) {
//                clearDotForNoParentOrg(sMsg, i);
//            } else {
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
//                    sMsg.T.no(i).orgNm_0.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
//                    sMsg.T.no(i).orgNm_1.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
//                    sMsg.T.no(i).orgNm_2.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
//                    sMsg.T.no(i).orgNm_3.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
//                    sMsg.T.no(i).orgNm_4.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_5)) {
//                    sMsg.T.no(i).orgNm_5.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_6)) {
//                    sMsg.T.no(i).orgNm_6.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_7)) {
//                    sMsg.T.no(i).orgNm_7.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_8)) {
//                    sMsg.T.no(i).orgNm_8.setValue(NMAL2520Constant.DOT);
//                }
//            }
//        } else if ("10".equals(targetTier)) {
//            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_10)) {
//                clearDotForNoParentOrg(sMsg, i);
//            } else {
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
//                    sMsg.T.no(i).orgNm_0.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
//                    sMsg.T.no(i).orgNm_1.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
//                    sMsg.T.no(i).orgNm_2.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
//                    sMsg.T.no(i).orgNm_3.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
//                    sMsg.T.no(i).orgNm_4.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_5)) {
//                    sMsg.T.no(i).orgNm_5.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_6)) {
//                    sMsg.T.no(i).orgNm_6.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_7)) {
//                    sMsg.T.no(i).orgNm_7.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_8)) {
//                    sMsg.T.no(i).orgNm_8.setValue(NMAL2520Constant.DOT);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_9)) {
//                    sMsg.T.no(i).orgNm_9.setValue(NMAL2520Constant.DOT);
//                }
//            }
//        }
//    }

//    private void clearDotForNoParentOrg(NMAL2520SMsg sMsg, int i) {
//        sMsg.T.no(i).orgNm_2.clear();
//        sMsg.T.no(i).orgNm_3.clear();
//        sMsg.T.no(i).orgNm_4.clear();
//        sMsg.T.no(i).orgNm_5.clear();
//        sMsg.T.no(i).orgNm_6.clear();
//        sMsg.T.no(i).orgNm_7.clear();
//        sMsg.T.no(i).orgNm_8.clear();
//        sMsg.T.no(i).orgNm_9.clear();
//        sMsg.T.no(i).orgNm_10.clear();
//    }
    // 2016/02/23 CSA-QC#1931 Delete End
    
    // QC#7966
    private void doProcess_NMAL2520Scrn00_OnChange_OrgActvFlg(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        searchOrganizationParent(cMsg, sMsg);
        searchOrganizationChild(cMsg, sMsg);

        NMAL2520CommonLogic.setBackUpParent(cMsg);
        NMAL2520CommonLogic.setBackUpChild(cMsg);
    }
    
    private void doProcess_NMAL2520Scrn00_OnChange_ResrcActvFlg(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        searchResource(cMsg, sMsg);

        NMAL2520CommonLogic.setBackUpResource(cMsg, sMsg);
    }

    // Add Start 2018/02/14 QC#23905
    /**
     * doProcess_NMAL2520Scrn00_Link_Resource
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     */
    private void doProcess_NMAL2520Scrn00_Link_Resource(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NMAL2520CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxHldFlg_H0.getValue())) {
            cMsg.xxHldFlg_H0.setValue(ZYPConstant.FLG_OFF_N);
            return;
        }

        boolean isResrcTableChange = false;
        boolean isResrcTableDelete = false;
        int insUpdCount = sMsg.C.getValidCount();
        int delCount = sMsg.Y.getValidCount();

        for (int i = 0; i < insUpdCount; i++) {
            NMAL2520_CSMsg resrcRec = sMsg.C.no(i);
            if (NMAL2520CommonLogic.isChangeResrcRecord(resrcRec)) {
                isResrcTableChange = true;
                resrcRec.xxChkBox_C1.setErrorInfo(2, NMAL2520Constant.NMAM8286W);
            }
        }

        if (delCount > 0) {
            isResrcTableDelete = true;
        }

        if (isResrcTableChange || isResrcTableDelete) {
            cMsg.xxHldFlg_H0.setValue(ZYPConstant.FLG_ON_Y);
            cMsg.setMessageInfo(NMAL2520Constant.NMAM8286W);
        }

        if (isResrcTableChange) {
            NMAL2520CommonLogic.jumpToErrorOrWarningPage(cMsg, sMsg);
        }
    }
    // Add End 2018/02/14 QC#23905

    // START 2017/06/14 J.Kim [QC#18924,ADD]
    private void doProcess_NMAL2520Scrn00_PageJump(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        int rowIndex = NMAL2520CommonLogic.convertPageNumToFirstRowIndex(cMsg.C.length(), cMsg.xxPageShowCurNum.getValueInt());
        NMAL2520CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NMAL2520Scrn00_PageNext(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NMAL2520CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.C.length() - 1;
        NMAL2520CommonLogic.pagenation(cMsg, sMsg, pageFrom);
    }

    private void doProcess_NMAL2520Scrn00_PagePrev(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NMAL2520CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.C.length() - 1;
        NMAL2520CommonLogic.pagenation(cMsg, sMsg, pageFrom);
    }
    // END 2017/06/14 J.Kim [QC#18924,ADD]

    private void searchOrganizationParent(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.X);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.X);

        S21SsmEZDResult ssmResult = NMAL2520Query.getInstance().getOrganizationParent(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NMAL2520Constant.NZZM0001W);
                queryResCnt = sMsg.A.length();
            }
            // Move first page date from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }

                // Add Start 2018/02/14 QC#23905
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(i).xxRecHistCratByNm_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(i).xxRecHistUpdByNm_A1.getValue()));
                // Add End 2018/02/14 QC#23905
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);
        }
    }
    
    private void searchOrganizationChild(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);

        S21SsmEZDResult ssmResult = NMAL2520Query.getInstance().getOrganizationChildren(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo(NMAL2520Constant.NZZM0001W);
                queryResCnt = sMsg.B.length();
            }
            // Move first page date from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.B.getValidCount(); i++) {
                if (i == cMsg.B.length()) {
                    break;
                }

                // Add Start 2018/02/14 QC#23905
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRecHistCratByNm_B1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.B.no(i).xxRecHistCratByNm_B1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRecHistUpdByNm_B1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.B.no(i).xxRecHistUpdByNm_B1.getValue()));
                // Add End 2018/02/14 QC#23905
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }
            cMsg.B.setValidCount(i);
        }
    }
    
    private void searchResource(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(cMsg.Y);
        ZYPTableUtil.clear(sMsg.C);
        ZYPTableUtil.clear(sMsg.Y);

        S21SsmEZDResult ssmResult = NMAL2520Query.getInstance().getResource(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.C.length()) {
                cMsg.setMessageInfo(NMAL2520Constant.NZZM0001W);
                queryResCnt = sMsg.C.length();
            }
            // Move first page date from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.C.getValidCount(); i++) {
                if (i == cMsg.C.length()) {
                    break;
                }

                // Add Start 2018/02/14 QC#23905
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).xxRecHistCratByNm_C1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.C.no(i).xxRecHistCratByNm_C1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).xxRecHistUpdByNm_C1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.C.no(i).xxRecHistUpdByNm_C1.getValue()));
                // Add End 2018/02/14 QC#23905
                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i), null);
            }
            cMsg.C.setValidCount(i);
            NMAL2520CommonLogic.createOrgFuncRoleTpPullDown(cMsg, getGlobalCompanyCode(), cMsg.bizAreaOrgCd_P1.getValue());
            // START 2017/06/14 J.Kim [QC#18924,ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotCnt, BigDecimal.valueOf(sMsg.C.getValidCount()));

            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.C.getValidCount());
            cMsg.xxPageShowOfNum.setValue(sMsg.C.getValidCount());
            // END 2017/06/14 J.Kim [QC#18924,ADD]
        }
    }
    
}
