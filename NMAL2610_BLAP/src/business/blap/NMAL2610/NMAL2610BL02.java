/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2610;

import static business.blap.NMAL2610.constant.NMAL2610Constant.ADD_LINE_FLG;
import static business.blap.NMAL2610.constant.NMAL2610Constant.NMAM0835E;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSMsg;
import business.blap.NMAL2610.common.NMAL2610CommonLogic;
import business.blap.NMAL2610.constant.NMAL2610Constant;
import business.db.TRTY_GRP_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Territory Detail  NMAL2610BL02
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/02/05   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/03/03   Fujitsu         W.Honda         Update          CSA-QC#4545
 * 2016/03/03   Fujitsu         C.Tanaka        Update          CSA-QC#4551
 * 2016/03/31   Fujitsu         C.Yokoi         Update          CSA-QC#5945
 * 2016/06/27   Hitachi         A.Kohinata      Update          CSA-QC#10365
 * 2016/07/27   Fujitsu         C.Yokoi         Update          CSA-QC#11453
 * 2016/08/01   Fujitsu         C.Yokoi         Update          CSA-QC#10385
 * 2016/08/24   SRAA            Y.Chen          Update          CSA-QC#5786
 * 2016/11/09   Fujitsu         M.Ohno          Update          S21_NA#2680
 * 2017/03/07   Fujitsu         M.Ohno          Update          S21_NA#17760
 * 2017/03/03   Fujitsu         R.Nakamura      Update          S21_NA#15878
 * 2017/06/06   Fujitsu         N.Aoyama        Update          QC#16677
 * 2017/12/05   Fujitsu         N.Sugiura       Update          QC#21270
 * 2018/05/18   Fujitsu         N.Sugiura       Update          QC#26233
 * 2019/05/13   Fujitsu         T.Noguchi       Update          QC#50128
 * 2019/05/28   Fujitsu         R.Nakamura      Update          S21_NA#50504
 * 2019/12/06   Fujitsu         A.Kazuki        Update          QC#53019
 * 2019/12/27   Fujitsu         A.Kazuki        Update          QC#54222-1
 * 2020/02/18   Fujistu         M.Ohno          Update          QC#54222-2
 * </pre>
 */
public class NMAL2610BL02 extends S21BusinessHandler {

    // 2019/05/13 QC#50128 Add Start
    private final S21LRUMap<String, String> fullNameCache = new S21LRUMap<String, String>();
    // 2019/05/13 QC#50128 Add End

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2610CMsg bizMsg = (NMAL2610CMsg) cMsg;
            NMAL2610SMsg glblMsg = (NMAL2610SMsg) sMsg;

            if ("NMAL2610_INIT".equals(screenAplID)) {
                doProcess_NMAL2610_INIT(bizMsg, glblMsg);
            } else if ("NMAL2610Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL2610_Search(bizMsg, glblMsg);
                onChange_TerritoryGroup(bizMsg, glblMsg);
            } else if ("NMAL2610Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_CMN_Clear(bizMsg, glblMsg);
            } else if ("NMAL2610Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_CMN_Reset(bizMsg, glblMsg);
            // Add Start 2019/05/28 QC#50504
            } else if ("NMAL2610Scrn00_CMN_Return".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_CMN_Return(bizMsg, glblMsg);
            // Add End 2019/05/28 QC#50504
            } else if ("NMAL2610Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_CMN_Submit(bizMsg, glblMsg);
            } else if ("NMAL2610Scrn00_DeleteRow_Territory".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_DeleteRow_Territory(bizMsg, glblMsg);
            } else if ("NMAL2610Scrn00_DeleteRow_Rules".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_DeleteRow_Rules(bizMsg, glblMsg);
            } else if ("NMAL2610Scrn00_DeleteRow_Resource".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_DeleteRow_Resource(bizMsg, glblMsg);
            } else if ("NMAL2610Scrn00_InsertRow_Territory".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_InsertRow_Territory(bizMsg, glblMsg);
            } else if ("NMAL2610Scrn00_InsertRow_Rules".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_InsertRow_Rules(bizMsg, glblMsg);
            } else if ("NMAL2610Scrn00_InsertRow_Resource".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_InsertRow_Resource(bizMsg, glblMsg);
// 2017/12/05 QC#21270 Del Start
//            } else if ("NMAL2610Scrn00_TAB_Territory".equals(screenAplID)) {
//                doProcess_NMAL2610Scrn00_TAB_Territory(bizMsg, glblMsg);
//            } else if ("NMAL2610Scrn00_TAB_Rules".equals(screenAplID)) {
//                doProcess_NMAL2610Scrn00_TAB_Rules(bizMsg, glblMsg);
//            } else if ("NMAL2610Scrn00_TAB_Resource".equals(screenAplID)) {
//                doProcess_NMAL2610Scrn00_TAB_Resource(bizMsg, glblMsg);
//                onChange_TerritoryGroup(bizMsg, glblMsg);
// 2017/12/05 QC#21270 Del End
            } else if ("NMAL2610_NMAL2570".equals(screenAplID)) {
                doProcess_NMAL2610_NMAL2570(bizMsg, glblMsg);
            } else if ("NMAL2610_NMAL2630".equals(screenAplID)) {
                doProcess_NMAL2610_NMAL2630(bizMsg, glblMsg);
                onChange_TerritoryGroup(bizMsg, glblMsg);
            } else if ("NMAL2610Scrn00_Filter".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_Filter(bizMsg, glblMsg);
                // QC#7966
            } else if ("NMAL2610Scrn00_ClearFilter".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_ClearFilter(bizMsg, glblMsg);
            } else if ("NMAL2610Scrn00_OnChange_TrtyActvFlg".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_OnChange_TrtyActvFlg(bizMsg, glblMsg);
            } else if ("NMAL2610Scrn00_OnChange_RuleActvFlg".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_OnChange_RuleActvFlg(bizMsg, glblMsg);
            } else if ("NMAL2610Scrn00_OnChange_ResrcActvFlg".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_OnChange_ResrcActvFlg(bizMsg, glblMsg);

            } else if ("NMAL2610Scrn00_OnChange_TerritoryGroup".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_OnChange_TerritoryGroup(bizMsg, glblMsg);
                // 2016/06/27 QC#10365 Add Start
            } else if ("NMAL2610Scrn00_OnChange_BusinessArea".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_OnChange_BusinessArea(bizMsg, glblMsg);
            } else if ("NMAL2610Scrn00_OnChange_Tier".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_OnChange_Tier(bizMsg, glblMsg);
                // QC#5786
            } else if ("NMAL2610Scrn00_OnChange_TerritoryRuleType".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_OnChange_TerritoryRuleType(bizMsg, glblMsg);
                // 2016/06/27 QC#10365 Add End
// 2017/12/05 QC#21270 Del Start
//            } else if ("NMAL2610Scrn00_OnBlur_DeriveTerritory".equals(screenAplID)) {
//                doProcess_NMAL2610Scrn00_OnBlur_DeriveTerritory(bizMsg, glblMsg);
// 2017/12/05 QC#21270 Del End
                // Add Start 2017/03/09 QC#15878
            } else if ("NMAL2610Scrn00_OpenWin_TargetFrom".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_OpenWin_TargetFrom(bizMsg, glblMsg);
            } else if ("NMAL2610Scrn00_OpenWin_TargetTo".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_OpenWin_TargetTo(bizMsg, glblMsg);
                // Add End 2017/03/09 QC#15878
                // ADD START 2017/06/06 QC#16677
            } else if ("NMAL2610Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NMAL2610Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL2610Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_PagePrev(bizMsg, glblMsg);

                // ADD E N D 2017/06/06 QC#16677
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
    private void doProcess_NMAL2610_INIT(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        // Add Start 2018/05/18 QC#26233
        // Initial Screen Objects.
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(cMsg.X);

        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.X);

        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(cMsg.Y);

        ZYPTableUtil.clear(sMsg.C);
        ZYPTableUtil.clear(sMsg.E);
        ZYPTableUtil.clear(sMsg.Y);

        ZYPTableUtil.clear(cMsg.D);
        ZYPTableUtil.clear(cMsg.Z);

        ZYPTableUtil.clear(sMsg.D);
        ZYPTableUtil.clear(sMsg.Z);
        // Add End 2018/05/18 QC#26233

        String globalCmpyCd = getGlobalCompanyCode();
        NMAL2610CommonLogic.createPulldownList(cMsg, sMsg, globalCmpyCd);

        if (ZYPCommonFunc.hasValue(cMsg.orgCd_H1)) {
            if (NMAL2610Constant.LINK_COPY_TERRITORY.equals(cMsg.xxEventFlgTxt.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, NMAL2610Constant.TAB_TERRITORY_RULES);
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, NMAL2610Constant.TAB_TERRITORY);
            }

            doProcess_NMAL2610_Search(cMsg, sMsg);
        }
        onChange_TerritoryGroup(cMsg, sMsg);
    }

    /**
     * <pre>
     * Submit Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2610Scrn00_CMN_Submit(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        if (NMAL2610Constant.MESSAGE_KIND_WARN.equals(cMsg.getMessageKind())) {
            return;
        }

        if (NMAL2610Constant.MESSAGE_KIND_ERROR.equals(cMsg.getMessageKind())) {
            return;
        }

        doProcess_NMAL2610_Search(cMsg, sMsg);
    }

    private void searchTerritoryParent(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        NMAL2610CommonLogic.clearforTerritorySearch(cMsg, sMsg);
        S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().getTerritoryParent(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NMAL2610Constant.NZZM0001W);
            }

            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }

                // Add Start 2016/11/09 M.Ohno S21_NA#2680
                // 2019/05/13 QC#50128 Mod Start
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistCratByNm_A1, getFullNameForRecHist(sMsg.A.no(i).xxRecHistCratByNm_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistUpdByNm_A1, getFullNameForRecHist(sMsg.A.no(i).xxRecHistUpdByNm_A1.getValue()));
                //ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(i).xxRecHistCratByNm_A1.getValue()));
                //ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(i).xxRecHistUpdByNm_A1.getValue()));
                // 2019/05/13 QC#50128 Mod End
                // Add End 2016/11/09 M.Ohno S21_NA#2680

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

        } else {
            NMAL2610CommonLogic.clearforTerritorySearch(cMsg, sMsg);
        }

        // QC#7966
        // EZDMsg.copy(sMsg, null, cMsg, null);
    }

    private void searchTerritoryChild(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        cMsg.B.clear();
        cMsg.B.setValidCount(0);

        S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().getTerritoryChild(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo(NMAL2610Constant.NZZM0001W);
            }

            int i = 0;
            for (; i < sMsg.B.getValidCount(); i++) {
                if (i == cMsg.B.length()) {
                    break;
                }

                // Add Start 2016/11/09 M.Ohno S21_NA#2680
                // 2019/05/13 QC#50128 Mod Start
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRecHistCratByNm_B1, getFullNameForRecHist(sMsg.B.no(i).xxRecHistCratByNm_B1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRecHistUpdByNm_B1, getFullNameForRecHist(sMsg.B.no(i).xxRecHistUpdByNm_B1.getValue()));
                //ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRecHistCratByNm_B1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.B.no(i).xxRecHistCratByNm_B1.getValue()));
                //ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRecHistUpdByNm_B1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.B.no(i).xxRecHistUpdByNm_B1.getValue()));
                // 2019/05/13 QC#50128 Mod End
                // Add End 2016/11/09 M.Ohno S21_NA#2680

                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }
            cMsg.B.setValidCount(i);
        }
    }


    private void searchTerritoryRules(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        NMAL2610CommonLogic.clearforTerritoryRulesSearch(cMsg, sMsg);
        S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().getTerritoryRules(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.C.length()) {
                cMsg.setMessageInfo(NMAL2610Constant.NZZM0001W);
            }

            for (int i = 0; i < sMsg.C.getValidCount(); i++) {
                // DEL START 2017/06/06 QC#16677
                // if (i == sMsg.C.length()) {
                // break;
                // }
                // DEL E N D 2017/06/06 QC#16677

                // Add Start 2016/11/09 M.Ohno S21_NA#2680
                // 2019/05/13 QC#50128 Mod Start
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).xxRecHistCratByNm_C1, getFullNameForRecHist(sMsg.C.no(i).xxRecHistCratByNm_C1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).xxRecHistUpdByNm_C1, getFullNameForRecHist(sMsg.C.no(i).xxRecHistUpdByNm_C1.getValue()));
                //ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).xxRecHistCratByNm_C1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.C.no(i).xxRecHistCratByNm_C1.getValue()));
                //ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).xxRecHistUpdByNm_C1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.C.no(i).xxRecHistUpdByNm_C1.getValue()));
                // 2019/05/13 QC#50128 Mod End
                // Add End 2016/11/09 M.Ohno S21_NA#2680

                // DEL START 2017/06/06 QC#16677
                // EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i),
                // null);
                // DEL E N D 2017/06/06 QC#16677
                // ADD START 2017/06/23 QC#16677
                if (!NMAL2610Constant.LINK_COPY_TERRITORY.equals(cMsg.xxEventFlgTxt.getValue())) {
                    EZDMsg.copy(sMsg.C.no(i), null, sMsg.E.no(i), null);
                }
                // ADD START 2017/06/23 QC#16677

            }
            // DEL START 2017/06/06 QC#16677
            // cMsg.C.setValidCount(i);
            // DEL E N D 2017/06/06 QC#16677

            // ADD START 2017/06/06 QC#16677
            if (queryResCnt > sMsg.C.length()) {
                sMsg.C.setValidCount(sMsg.C.length());
            } else {
                sMsg.C.setValidCount(queryResCnt);
            }

            cMsg.xxPageShowFromNum_C1.setValue(1);
            NMAL2610CommonLogic.loadOnePageToCMsg(cMsg, cMsg.C, sMsg.C);
            // ADD E N D 2017/06/06 QC#16677

        } else {
            // ADD START 2017/06/06 QC#16677
            cMsg.xxPageShowFromNum_C1.clear();
            cMsg.xxPageShowToNum_C1.clear();
            cMsg.xxPageShowOfNum_C1.clear();
            // ADD E N D 2017/06/06 QC#16677
            NMAL2610CommonLogic.clearforTerritoryRulesSearch(cMsg, sMsg);
        }

        // QC#5786
        resetTerritoryRuleOperandPulldownList(cMsg);
    }

    private void searchTerritoryResource(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        NMAL2610CommonLogic.clearforResourceSearch(cMsg, sMsg);
        S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().getResource(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.D.length()) {
                cMsg.setMessageInfo(NMAL2610Constant.NZZM0001W);
            }

            int i = 0;
            for (; i < sMsg.D.getValidCount(); i++) {
                if (i == cMsg.D.length()) {
                    break;
                }

                // Add Start 2016/11/09 M.Ohno S21_NA#2680
                // 2019/05/13 QC#50128 Mod Start
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).xxRecHistCratByNm_D1, getFullNameForRecHist(sMsg.D.no(i).xxRecHistCratByNm_D1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).xxRecHistUpdByNm_D1, getFullNameForRecHist(sMsg.D.no(i).xxRecHistUpdByNm_D1.getValue()));
                //ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).xxRecHistCratByNm_D1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.D.no(i).xxRecHistCratByNm_D1.getValue()));
                //ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).xxRecHistUpdByNm_D1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.D.no(i).xxRecHistUpdByNm_D1.getValue()));
                // 2019/05/13 QC#50128 Mod End
                // Add End 2016/11/09 M.Ohno S21_NA#2680

                EZDMsg.copy(sMsg.D.no(i), null, cMsg.D.no(i), null);

            }
            cMsg.D.setValidCount(i);

        } else {
            NMAL2610CommonLogic.clearforResourceSearch(cMsg, sMsg);
        }

    }

    /**
     * <pre>
     * Search Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2610_Search(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        // 2016/02/12 CSA-QC#2869 Del Start
        // if
        // (NMAL2610Constant.TAB_TERRITORY.equals(cMsg.xxDplyTab.getValue()))
        // {
        // 2016/02/12 CSA-QC#2869 Del End

        S21SsmEZDResult ssmResult;
        EZDMsg.copy(cMsg, null, sMsg, null);

        ssmResult = NMAL2610Query.getInstance().getTerritory(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt == 1) {
                sMsg.xxRsltFlg.setValue(ZYPConstant.FLG_ON_Y);

                // Add Start 2016/11/09 M.Ohno S21_NA#2680
                // 2019/05/13 QC#50128 Mod Start
                ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistCratByNm, getFullNameForRecHist(sMsg.xxRecHistCratByNm.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistUpdByNm, getFullNameForRecHist(sMsg.xxRecHistUpdByNm.getValue()));
                //ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistCratByNm, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistCratByNm.getValue()));
                //ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistUpdByNm, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistUpdByNm.getValue()));
                // 2019/05/13 QC#50128 Mod End
                // Add End 2016/11/09 M.Ohno S21_NA#2680

                EZDMsg.copy(sMsg, null, cMsg, null);

                searchTerritoryParent(cMsg, sMsg);
                searchTerritoryChild(cMsg, sMsg);
                searchTerritoryRules(cMsg, sMsg);
                searchTerritoryResource(cMsg, sMsg);

                // 2016/07/27 CSA-QC#11453 Delete Start
                // if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode()))
                // {
                // cMsg.setMessageInfo(NMAL2610Constant.NMAM8182I, new
                // String[] {NMAL2610Constant.SEARCH});
                // }
                // 2016/07/27 CSA-QC#11453 Delete End

            } else {
                // NMAL2610CommonLogic.clearHeader(cMsg, sMsg);
                if (ZYPConstant.FLG_ON_Y.equals(sMsg.xxRsltFlg.getValue())) {
                    NMAL2610CommonLogic.clearforTerritorySearch(cMsg, sMsg);
                    NMAL2610CommonLogic.clearforTerritoryRulesSearch(cMsg, sMsg);
                    NMAL2610CommonLogic.clearforResourceSearch(cMsg, sMsg);

                    sMsg.xxRsltFlg.setValue(ZYPConstant.FLG_OFF_N);
                    cMsg.xxRsltFlg.setValue(ZYPConstant.FLG_OFF_N);
                }
                cMsg.setMessageInfo(NMAL2610Constant.NMAM8344I);
                return;
            }
        } else {
            // NMAL2610CommonLogic.clearHeader(cMsg, sMsg);
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.xxRsltFlg.getValue())) {
                NMAL2610CommonLogic.clearforTerritorySearch(cMsg, sMsg);
                NMAL2610CommonLogic.clearforTerritoryRulesSearch(cMsg, sMsg);
                NMAL2610CommonLogic.clearforResourceSearch(cMsg, sMsg);

                sMsg.xxRsltFlg.setValue(ZYPConstant.FLG_OFF_N);
                cMsg.xxRsltFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            cMsg.setMessageInfo(NMAL2610Constant.NMAM8344I);
        }

        // 2016/06/27 QC#10365 Add Start
        if (ZYPCommonFunc.hasValue(cMsg.orgTierCd_P1)) {
            onChange_Tier(cMsg);
        }
        // 2016/06/27 QC#10365 Add End

        // 2016/02/12 CSA-QC#4336 Mod Start
        NMAL2610CommonLogic.setBackUp(cMsg, sMsg);
        // 2016/02/12 CSA-QC#4336 Mod Start

        // 2016/02/12 CSA-QC#2869 Del Start
        // } else if
        // (NMAL2610Constant.TAB_TERRITORY_RULES.equals(cMsg.xxDplyTab.getValue()))
        // {
        //
        // S21SsmEZDResult ssmResult;
        //
        // ssmResult = NMAL2610Query.getInstance().getTerritory(cMsg,
        // sMsg);
        //
        // if (ssmResult.isCodeNormal()) {
        // int queryResCnt = ssmResult.getQueryResultCount();
        // if (queryResCnt == 1) {
        // EZDMsg.copy(sMsg, null, cMsg, null);
        //
        // searchTerritoryRules(cMsg, sMsg);
        //
        // } else {
        // NMAL2610CommonLogic.clearHeader(cMsg, sMsg);
        // NMAL2610CommonLogic.clearforTerritoryRulesSearch(cMsg,
        // sMsg);
        // return;
        // }
        // } else {
        // NMAL2610CommonLogic.clearHeader(cMsg, sMsg);
        // NMAL2610CommonLogic.clearforTerritoryRulesSearch(cMsg,
        // sMsg);
        // cMsg.setMessageInfo(NMAL2610Constant.NMAM0038I);
        // }

        // 2016/02/12 CSA-QC#2869 Mod Start
        // } else if
        // (NMAL2610Constant.TAB_RESOURCE_ASSIGNE.equals(cMsg.xxDplyTab.getValue()))
        // {

        // S21SsmEZDResult ssmResult;
        //
        // ssmResult = NMAL2610Query.getInstance().getTerritory(cMsg,
        // sMsg);
        //
        // if (ssmResult.isCodeNormal()) {
        // int queryResCnt = ssmResult.getQueryResultCount();
        // if (queryResCnt == 1) {
        // EZDMsg.copy(sMsg, null, cMsg, null);
        //
        // searchTerritoryResource(cMsg, sMsg);
        //
        // } else {
        // NMAL2610CommonLogic.clearHeader(cMsg, sMsg);
        // NMAL2610CommonLogic.clearforResourceSearch(cMsg, sMsg);
        // return;
        // }
        // } else {
        // NMAL2610CommonLogic.clearHeader(cMsg, sMsg);
        // NMAL2610CommonLogic.clearforResourceSearch(cMsg, sMsg);
        // cMsg.setMessageInfo(NMAL2610Constant.NMAM0038I);
        // }
        // }
        // 2016/02/12 CSA-QC#2869 Del End

    }

    private void doProcess_NMAL2610Scrn00_Filter(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        // QC#7966
        // NMAL2610CommonLogic.clearforTerritorySearch(cMsg, sMsg);
        // S21SsmEZDResult ssmResult =
        // NMAL2610Query.getInstance().getTerritoryParentWithFilter(cMsg,
        // sMsg);
        //
        // if (ssmResult.isCodeNormal()) {
        //
        // int queryResCnt = ssmResult.getQueryResultCount();
        // if (queryResCnt > sMsg.A.length()) {
        // cMsg.setMessageInfo(NMAL2610Constant.NZZM0001W);
        // }
        //
        // int i = 0;
        // for (; i < sMsg.A.getValidCount(); i++) {
        // if (i == cMsg.A.length()) {
        // break;
        // }
        //
        // EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
        // }
        // cMsg.A.setValidCount(i);
        //
        // } else {
        // NMAL2610CommonLogic.clearforTerritorySearch(cMsg, sMsg);
        // cMsg.setMessageInfo(NMAL2610Constant.NMAM0038I);
        // }
        //
        // // EZDMsg.copy(sMsg, null, cMsg, null);
        searchTerritoryRules(cMsg, sMsg);
    }

    /**
     * <pre>
     * Common Clear Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2610Scrn00_CMN_Clear(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        String currentTab = cMsg.xxDplyTab.getValue();

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.B.clear();
        cMsg.B.setValidCount(0);
        cMsg.C.clear();
        cMsg.C.setValidCount(0);
        cMsg.D.clear();
        cMsg.D.setValidCount(0);
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
        sMsg.D.clear();
        sMsg.D.setValidCount(0);
        // ADD START 2017/06/25 QC#16677
        sMsg.E.clear();
        sMsg.E.setValidCount(0);
        // ADD E N D 2017/06/25 QC#16677
        sMsg.X.clear();
        sMsg.X.setValidCount(0);
        sMsg.Y.clear();
        sMsg.Y.setValidCount(0);
        sMsg.Z.clear();
        sMsg.Z.setValidCount(0);
        sMsg.clear();

        cMsg.xxDplyTab.setValue(currentTab);

        doProcess_NMAL2610_INIT(cMsg, sMsg);
        // QC#5786
        resetTerritoryRuleOperandPulldownList(cMsg);
    }

    /**
     * <pre>
     * Common Reset Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2610Scrn00_CMN_Reset(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        String currentTab = cMsg.xxDplyTab.getValue();

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.B.clear();
        cMsg.B.setValidCount(0);
        cMsg.C.clear();
        cMsg.C.setValidCount(0);
        cMsg.D.clear();
        cMsg.D.setValidCount(0);
        cMsg.X.clear();
        cMsg.X.setValidCount(0);
        cMsg.Y.clear();
        cMsg.Y.setValidCount(0);
        cMsg.Z.clear();
        cMsg.Z.setValidCount(0);
        String tmpOrgCd = cMsg.orgCd_H1.getValue();
        cMsg.clear();
        ZYPEZDItemValueSetter.setValue(cMsg.orgCd_H1, tmpOrgCd);

        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.B.clear();
        sMsg.B.setValidCount(0);
        sMsg.C.clear();
        sMsg.C.setValidCount(0);
        sMsg.D.clear();
        sMsg.D.setValidCount(0);
        // ADD START 2017/06/25 QC#16677
        sMsg.E.clear();
        sMsg.E.setValidCount(0);
        // ADD E N D 2017/06/25 QC#16677
        sMsg.X.clear();
        sMsg.X.setValidCount(0);
        sMsg.Y.clear();
        sMsg.Y.setValidCount(0);
        sMsg.Z.clear();
        sMsg.Z.setValidCount(0);
        sMsg.clear();

        cMsg.xxDplyTab.setValue(currentTab);

        doProcess_NMAL2610_INIT(cMsg, sMsg);
        // QC#5786
        resetTerritoryRuleOperandPulldownList(cMsg);
    }

    // Add Start 2019/05/28 QC#50504
    /**
     * <pre>
     * Common Return Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2610Scrn00_CMN_Return(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        String currentTab = cMsg.xxDplyTab.getValue();

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.B.clear();
        cMsg.B.setValidCount(0);
        cMsg.C.clear();
        cMsg.C.setValidCount(0);
        cMsg.D.clear();
        cMsg.D.setValidCount(0);
        cMsg.X.clear();
        cMsg.X.setValidCount(0);
        cMsg.Y.clear();
        cMsg.Y.setValidCount(0);
        cMsg.Z.clear();
        cMsg.Z.setValidCount(0);
        String tmpOrgCd = cMsg.orgCd_H1.getValue();
        cMsg.clear();
        ZYPEZDItemValueSetter.setValue(cMsg.orgCd_H1, tmpOrgCd);

        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.B.clear();
        sMsg.B.setValidCount(0);
        sMsg.C.clear();
        sMsg.C.setValidCount(0);
        sMsg.D.clear();
        sMsg.D.setValidCount(0);
        sMsg.E.clear();
        sMsg.E.setValidCount(0);
        sMsg.X.clear();
        sMsg.X.setValidCount(0);
        sMsg.Y.clear();
        sMsg.Y.setValidCount(0);
        sMsg.Z.clear();
        sMsg.Z.setValidCount(0);
        sMsg.clear();

        cMsg.xxDplyTab.setValue(currentTab);

    }
    // Add End 2019/05/28 QC#50504

    /**
     * <pre>
     * Delete for Territory
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2610Scrn00_DeleteRow_Territory(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.A, NMAL2610Constant.CHKBOX_A, ZYPConstant.CHKBOX_ON_Y);

        boolean hasActiveParentOrg = false;
        boolean isDelete = false;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            for (int idx = 0; idx < checkList.size(); idx++) {
                if (i == idx) {
                    isDelete = true;
                    break;
                }
            }

            if (isDelete) {
                continue;
            }

            if (NMAL2610CommonLogic.checkActiveDate(cMsg.B.no(i).effFromDt_B1.getValue(), cMsg.B.no(i).effThruDt_B1.getValue(), ZYPDateUtil.getSalesDate())) {
                hasActiveParentOrg = true;
                break;
            }
            isDelete = false;
        }

        if (!hasActiveParentOrg) {
            for (int idx = 0; idx < cMsg.B.getValidCount(); idx++) {
                if (NMAL2610CommonLogic.checkActiveDate(cMsg.B.no(idx).effFromDt_B1.getValue(), cMsg.B.no(idx).effThruDt_B1.getValue(), ZYPDateUtil.getSalesDate())) {
                    cMsg.setMessageInfo(NMAL2610Constant.NMAM8361E);
                    return;
                }
            }
        }
        for (int i = 0; i < checkList.size(); i++) {
            int index = checkList.get(i);
            checkList.set(i, index);
            if (ZYPCommonFunc.hasValue(sMsg.A.no(index).ezUpTime_X2)) {
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).orgCd_R1, cMsg.A.no(index).orgCd_X2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).prntOrgCd_R1, cMsg.A.no(index).prntOrgCd_X2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).orgStruTpCd_R1, cMsg.A.no(index).orgStruTpCd_X2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).effFromDt_R1, cMsg.A.no(index).effFromDt_X2.getValue());
                // 2016/03/31 CSA-QC#5945 Add Start
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).ezUpTime_R1, cMsg.A.no(index).ezUpTime_X2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).ezUpTimeZone_R1, cMsg.A.no(index).ezUpTimeZone_X2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).gnrnTpCd_R1, GNRN_TP.DELETE);
                // 2016/03/31 CSA-QC#5945 Add End
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).dsOrgRelnPk_R1, cMsg.A.no(index).dsOrgRelnPk_X2.getValue()); // 2017/03/07
                // S21_NA#17760
                // Add

                sMsg.X.setValidCount(sMsg.X.getValidCount() + 1);
            }
        }

        ZYPTableUtil.deleteRows(cMsg.A, checkList);
        ZYPTableUtil.deleteRows(sMsg.A, checkList);

    }

    /**
     * <pre>
     * Delete for Rule
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2610Scrn00_DeleteRow_Rules(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        // MOD START 2017/06/26 QC#16677
        // the input data have to copy to glblMsg
        NMAL2610CommonLogic.updateGlblMsg(cMsg, sMsg);

        // 2020/02/18 QC#54222-2 Add Start
        cMsg.setCommitSMsg(true);
        // 2020/02/18 QC#54222-2 Add End
        // List<Integer> checkList =
        // ZYPTableUtil.getSelectedRows(cMsg.C,
        // NMAL2610Constant.CHKBOX_C, ZYPConstant.CHKBOX_ON_Y);
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(sMsg.C, NMAL2610Constant.CHKBOX_C, ZYPConstant.CHKBOX_ON_Y);
        // Mod Start 2019/12/27 QC#54222-1
//        if (checkList.size() == 0) {
//            if (cMsg.C.getValidCount() == 0) {
//                cMsg.setMessageInfo(NMAM0835E);
//                return;
//            }
//            for (int i = 0; i < sMsg.C.getValidCount(); i++) {
//                sMsg.C.no(i).xxChkBox_C1.setErrorInfo(1, NMAM0835E);
//            }
//        }
        if (checkList.size() == 0) {
            cMsg.setMessageInfo(NMAL2610Constant.NMAM0835E);
            for (int i = 0; i < cMsg.C.getValidCount(); i++) {
                cMsg.C.no(i).xxChkBox_C1.setErrorInfo(1, NMAM0835E);
            }
//            NMAL2610CommonLogic.loadOnePageToCMsg(cMsg, cMsg.C, sMsg.C);
            resetTerritoryRuleOperandPulldownList(cMsg);
            return;
        }
        // Mod End   2019/12/27 QC#54222-1

        for (int i = 0; i < checkList.size(); i++) {
            int index = checkList.get(i);
            checkList.set(i, index);
            if (ZYPCommonFunc.hasValue(sMsg.C.no(index).trtyRulePk_X3)) {
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).trtyRulePk_R2, sMsg.C.no(index).trtyRulePk_X3.getValue());

                sMsg.Y.setValidCount(sMsg.Y.getValidCount() + 1);
                // Add Start 2019/12/27 QC#54222-1
                ZYPEZDItemValueSetter.setValue(cMsg.Y.no(cMsg.Y.getValidCount()).trtyRulePk_R2, sMsg.C.no(index).trtyRulePk_X3.getValue());
                cMsg.Y.setValidCount(cMsg.Y.getValidCount() + 1);
                // Add End   2019/12/27 QC#54222-1
            }
        }
        // MOD E N D 2017/06/26 QC#16677

//        ZYPTableUtil.deleteRows(cMsg.C, checkList);
        ZYPTableUtil.deleteRows(sMsg.C, checkList);

        // MOD E N D 2017/06/26 QC#16677
        if (cMsg.xxPageShowFromNum_C1.getValueInt() > sMsg.C.getValidCount()) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_C1, BigDecimal.valueOf(sMsg.C.getValidCount() - 1));
        }
        NMAL2610CommonLogic.loadOnePageToCMsg(cMsg, cMsg.C, sMsg.C);
        resetTerritoryRuleOperandPulldownList(cMsg);
        // MOD E N D 2017/06/26 QC#16677

    }

    /**
     * <pre>
     * Delete for Resource
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2610Scrn00_DeleteRow_Resource(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.D, NMAL2610Constant.CHKBOX_D, ZYPConstant.CHKBOX_ON_Y);

        for (int i = 0; i < checkList.size(); i++) {
            int index = checkList.get(i);
            checkList.set(i, index);
            if (ZYPCommonFunc.hasValue(sMsg.D.no(index).ezUpTime_X4)) {
                ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).orgCd_R3, cMsg.D.no(index).orgCd_X4.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).orgStruTpCd_R3, cMsg.D.no(index).orgStruTpCd_X4.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).psnCd_R3, cMsg.D.no(index).psnCd_X4.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).orgFuncRoleTpCd_R3, cMsg.D.no(index).orgFuncRoleTpCd_X4.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).effFromDt_R3, cMsg.D.no(index).effFromDt_X4.getValue());
                // 2016/03/31 CSA-QC#5945 Add Start
                ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).ezUpTime_R3, cMsg.D.no(index).ezUpTime_X4.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).ezUpTimeZone_R3, cMsg.D.no(index).ezUpTimeZone_X4.getValue());
                // if
                // (GNRN_TP.FUTURE.equals(cMsg.D.no(index).gnrnTpCd_D1.getValue()))
                // {
                // ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).gnrnTpCd_R3,
                // GNRN_TP.FUTURE);
                // } else {
                ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).gnrnTpCd_R3, GNRN_TP.DELETE);
                // }
                // 2016/03/31 CSA-QC#5945 Add End

                sMsg.Z.setValidCount(sMsg.Z.getValidCount() + 1);
            }
        }

        ZYPTableUtil.deleteRows(cMsg.D, checkList);
        ZYPTableUtil.deleteRows(sMsg.D, checkList);

    }

    /**
     * <pre>
     * Add for Territory
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2610Scrn00_InsertRow_Territory(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        EZDMsgArray targetArray = cMsg.A;
        int newRowIndex = targetArray.getValidCount();
        targetArray.get(newRowIndex).clear();
        targetArray.setValidCount(newRowIndex + 1);
    }

    /**
     * <pre>
     * Add for Rule
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2610Scrn00_InsertRow_Rules(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        // 2020/02/18 QC#54222-2 Add Start
        cMsg.setCommitSMsg(true);
        // 2020/02/18 QC#54222-2 Add End
        // Add Start 2019/12/06 QC#53019
        // Before adding a row, check that the last row will exceeded.
        if (sMsg.C.getValidCount() + 1 > sMsg.C.length()) {
//        if (true){
            // Over maximum row size. To be a "New Line mode".
            ZYPEZDItemValueSetter.setValue(sMsg.xxRsltFlg, ADD_LINE_FLG);

            // Check : Any lines were changed.
            if (NMAL2610CommonLogic.isChangedTrtyRule(sMsg)) {
                return;
            }

            // Clear screen.
            ZYPTableUtil.clear(sMsg.C);
            ZYPTableUtil.clear(sMsg.E);
            ZYPTableUtil.clear(cMsg.C);
        }
        // Add End   2019/12/06 QC#53019

        // Mod START 2017/06/26 QC#16677
        // EZDMsgArray targetArray = cMsg.C;
        // int newRowIndex = targetArray.getValidCount();
        // targetArray.get(newRowIndex).clear();
        // targetArray.setValidCount(newRowIndex + 1);
        // QC#5786
        // resetTerritoryRuleOperandPulldownList(cMsg, newRowIndex);

        // the input data have to copy to glblMsg
        NMAL2610CommonLogic.updateGlblMsg(cMsg, sMsg);
        EZDMsgArray targetArray = sMsg.C;
        int newRowIndex = targetArray.getValidCount();
        targetArray.get(newRowIndex).clear();
        targetArray.setValidCount(newRowIndex + 1);

        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_C1, BigDecimal.valueOf(newRowIndex));

        NMAL2610CommonLogic.loadOnePageToCMsg(cMsg, cMsg.C, sMsg.C);
        resetTerritoryRuleOperandPulldownList(cMsg);
        // Mod START 2017/06/26 QC#16677

    }

    /**
     * <pre>
     * Add for Resource
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2610Scrn00_InsertRow_Resource(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        EZDMsgArray targetArray = cMsg.D;
        int newRowIndex = targetArray.getValidCount();
        targetArray.get(newRowIndex).clear();
        targetArray.setValidCount(newRowIndex + 1);
    }
// 2017/12/05 QC#21270 Del Start
//    /**
//     * <pre>
//     * Check modification before transition
//     * </pre>
//     * @param bizMsg Business Msg
//     * @param sMsg Global Msg
//     */
//    private void doProcess_NMAL2610Scrn00_TAB_Territory(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
//
//        if (NMAL2610CommonLogic.isChangedPrevTab(cMsg, sMsg)) {
//            return;
//        }
//
//        cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY);
//        doProcess_NMAL2610_Search(cMsg, sMsg);
//    }
//
//    /**
//     * <pre>
//     * Check modification before transition
//     * </pre>
//     * @param bizMsg Business Msg
//     * @param sMsg Global Msg
//     */
//    private void doProcess_NMAL2610Scrn00_TAB_Rules(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
//
//        if (NMAL2610CommonLogic.isChangedPrevTab(cMsg, sMsg)) {
//            return;
//        }
//
//        cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
//        doProcess_NMAL2610_Search(cMsg, sMsg);
//    }
//
//    /**
//     * <pre>
//     * Check modification before transition
//     * </pre>
//     * @param bizMsg Business Msg
//     * @param sMsg Global Msg
//     */
//    private void doProcess_NMAL2610Scrn00_TAB_Resource(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
//
//        if (NMAL2610CommonLogic.isChangedPrevTab(cMsg, sMsg)) {
//            return;
//        }
//
//        cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_RESOURCE_ASSIGNE);
//        doProcess_NMAL2610_Search(cMsg, sMsg);
//    }
// 2017/12/05 QC#21270 Del End
    /**
     * <pre>
     * Obtain additional data for manual input
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2610_NMAL2570(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        NMAL2610CommonLogic.checkResource(cMsg, sMsg, false, false);
    }

    /**
     * <pre>
     * Obtain additional data for manual input
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2610_NMAL2630(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        int index = cMsg.xxCellIdx.getValueInt();

        // 2016/02/12 CSA-QC#2869 Add Start
        // ## Header ##
        if (NMAL2610Constant.OPEN_WIN_TERRITORY_LOOKUP.equals(cMsg.xxEventFlgTxt.getValue())) {
            doProcess_NMAL2610_Search(cMsg, sMsg);
            return;
        }
        // 2016/02/12 CSA-QC#2869 Add End

        // ## Detail ##
        S21SsmEZDResult ssmResult;
        ssmResult = NMAL2610Query.getInstance().getTerritoryAdditional(cMsg, sMsg, index);
        if (!ssmResult.isCodeNormal()) {
            cMsg.A.no(index).clear();
            sMsg.A.no(index).clear();
            // 2016/03/03 CSA-QC#4545 Add Start
            cMsg.A.no(index).xxChkBox_A1.setErrorInfo(1, NMAL2610Constant.ZZZM9006E, new String[] {NMAL2610Constant.FIRST_ORG_CD_TRTY_DBCOLUMN });
            // 2016/03/03 CSA-QC#4545 Add End
            cMsg.setMessageInfo(NMAL2610Constant.ZZZM9006E, new String[] {NMAL2610Constant.FIRST_ORG_CD_TRTY_DBCOLUMN });
        } else {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            Map<String, Object> map = (Map<String, Object>) resultList.get(0);

            String bizAreaOrgCd = (String) map.get(NMAL2610Constant.FIRST_ORG_CD_TRTY_DBCOLUMN);
            if (bizAreaOrgCd != null) {
                cMsg.A.no(index).bizAreaOrgCd_A1.setValue(bizAreaOrgCd);
            } else {
                cMsg.A.no(index).clear();
                sMsg.A.no(index).clear();
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.A.no(index).xxChkBox_A1.setErrorInfo(1, NMAL2610Constant.ZZZM9006E, new String[] {NMAL2610Constant.FIRST_ORG_CD_TRTY_DBCOLUMN });
                // 2016/03/03 CSA-QC#4545 Add End
                cMsg.setMessageInfo(NMAL2610Constant.ZZZM9006E, new String[] {NMAL2610Constant.FIRST_ORG_CD_TRTY_DBCOLUMN });
            }

            String orgTierCd = (String) map.get(NMAL2610Constant.ORG_TIER_CD_TRTY_DBCOLUMN);
            if (orgTierCd != null) {
                cMsg.A.no(index).orgTierCd_A1.setValue(orgTierCd);
            } else {
                cMsg.A.no(index).clear();
                sMsg.A.no(index).clear();
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.A.no(index).xxChkBox_A1.setErrorInfo(1, NMAL2610Constant.ZZZM9006E, new String[] {NMAL2610Constant.ORG_TIER_CD_TRTY_DBCOLUMN });
                // 2016/03/03 CSA-QC#4545 Add End
                cMsg.setMessageInfo(NMAL2610Constant.ZZZM9006E, new String[] {NMAL2610Constant.ORG_TIER_CD_TRTY_DBCOLUMN });
            }

            String effFromDt = (String) map.get(NMAL2610Constant.EFF_FROM_DT_TRTY_DBCOLUMN);
            if (effFromDt != null) {
                cMsg.A.no(index).effFromDt_A2.setValue(effFromDt);
            }

            String effThruDt = (String) map.get(NMAL2610Constant.EFF_THRU_DT_TRTY_DBCOLUMN);
            if (effThruDt != null) {
                cMsg.A.no(index).effThruDt_A2.setValue(effThruDt);
            }
        }
    }

    /**
     * <pre>
     * Check modification before transition
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2610Scrn00_OnChange_TerritoryGroup(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        onChange_TerritoryGroup(cMsg, sMsg);

// 2017/12/05 QC#21270 Del Start
//        if (!ZYPCommonFunc.hasValue(cMsg.orgCd_H1) && !NMAL2610Constant.LINK_COPY_TERRITORY.equals(cMsg.xxEventFlgTxt.getValue()) && ZYPCommonFunc.hasValue(cMsg.orgNm_H1)) {
//            doProcess_NMAL2610_Search(cMsg, sMsg);
//        }
// 2017/12/05 QC#21270 Del End

        // 2016/08/01 CSA-QC#10385 Add Start
        boolean isChangeRole = false;
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.otherResrcTrtyFlg_H1.getValue())) {
            for (int i = 0; i <= cMsg.D.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(cMsg.D.no(i).acctTeamRoleTpCd_P1)) {
                    cMsg.D.no(i).acctTeamRoleTpCd_P1.clear();
                    isChangeRole = true;
                }
            }

            if (isChangeRole) {
                cMsg.setMessageInfo(NMAL2610Constant.NMAM8636I);
            }
        }
        // 2016/08/01 CSA-QC#10385 Add End
    }

    /**
     * <pre>
     * Check modification before transition
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void onChange_TerritoryGroup(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        TRTY_GRP_TPTMsg tMsg = new TRTY_GRP_TPTMsg();
        tMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        tMsg.trtyGrpTpCd.setValue(cMsg.trtyGrpTpCd_P1.getValue());

        tMsg = (TRTY_GRP_TPTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            cMsg.otherResrcTrtyFlg_H1.setValue(tMsg.otherResrcTrtyFlg.getValue());
        }
    }

    // 2016/06/27 QC#10365 Add Start
    /**
     * <pre>
     * OnChange BusinessArea
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2610Scrn00_OnChange_BusinessArea(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        doProcess_NMAL2610Scrn00_OnChange_Tier(cMsg, sMsg);
    }

    /**
     * <pre>
     * OnChange Tier
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2610Scrn00_OnChange_Tier(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        onChange_Tier(cMsg);

// 2017/12/05 QC#21270 Del Start
//        if (!ZYPCommonFunc.hasValue(cMsg.orgCd_H1) && !NMAL2610Constant.LINK_COPY_TERRITORY.equals(cMsg.xxEventFlgTxt.getValue()) && ZYPCommonFunc.hasValue(cMsg.orgNm_H1)) {
//            doProcess_NMAL2610_Search(cMsg, sMsg);
//        }
// 2017/12/05 QC#21270 Del End
    }

    /**
     * <pre>
     * OnChange Tier
     * </pre>
     * @param bizMsg Business Msg
     */
    private void onChange_Tier(NMAL2610CMsg cMsg) {
        S21SsmEZDResult ssmResult;
        ssmResult = NMAL2610Query.getInstance().getTierDescription(cMsg);

        if (ssmResult.isCodeNormal()) {
            String struDfnDescTxt = (String) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(cMsg.struDfnDescTxt_H1, struDfnDescTxt);
        } else {
            cMsg.struDfnDescTxt_H1.clear();
        }
    }

    // 2016/06/27 QC#10365 Add End

// 2017/12/05 QC#21270 Del Start
//    /**
//     * <pre>
//     * Onblur Derive Territory
//     * </pre>
//     * @param bizMsg Business Msg
//     * @param sMsg Global Msg
//     */
//    private void doProcess_NMAL2610Scrn00_OnBlur_DeriveTerritory(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
//        doProcess_NMAL2610_Search(cMsg, sMsg);
//        onChange_TerritoryGroup(cMsg, sMsg);
//    }
// 2017/12/05 QC#21270 Del End

    // QC#5786
    private void doProcess_NMAL2610Scrn00_OnChange_TerritoryRuleType(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        int row = cMsg.xxRowNum_C1.getValueInt();
        resetTerritoryRuleOperandPulldownList(cMsg, row);
    }

    private void resetTerritoryRuleOperandPulldownList(NMAL2610CMsg cMsg) {
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            resetTerritoryRuleOperandPulldownList(cMsg, i);
        }
    }

    private void resetTerritoryRuleOperandPulldownList(NMAL2610CMsg cMsg, int row) {
        String glblCmpyCd = this.getGlobalCompanyCode();
        String trtyRuleTpCd = cMsg.C.no(row).trtyRuleTpCd_P1.getValue();
        if (!ZYPCommonFunc.hasValue(trtyRuleTpCd)) {
            trtyRuleTpCd = cMsg.trtyRuleTpCd_C1.no(0).getValue();
        }
        EZDCStringItemArray codeItems = cMsg.C.no(row).trtyRuleOprdTpCd_C1;
        EZDCStringItemArray valueItems = cMsg.C.no(row).trtyRuleOprdTpDescTxt_C1;
        NMAL2610CommonLogic.createTerritoryRuleOperandPulldownList(glblCmpyCd, trtyRuleTpCd, codeItems, valueItems);
        // Add Start 2017/03/03 QC#15878
        ZYPEZDItemValueSetter.setValue(cMsg.C.no(row).trtyRuleTpCd_P1, trtyRuleTpCd);
        // Add End 2017/03/03 QC#15878
    }

    // QC#7966
    private void doProcess_NMAL2610Scrn00_OnChange_TrtyActvFlg(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        searchTerritoryParent(cMsg, sMsg);
        searchTerritoryChild(cMsg, sMsg);

        NMAL2610CommonLogic.setBackUpParent(cMsg);
        NMAL2610CommonLogic.setBackUpChild(cMsg);
    }

    private void doProcess_NMAL2610Scrn00_OnChange_RuleActvFlg(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        searchTerritoryRules(cMsg, sMsg);

        NMAL2610CommonLogic.setBackUpRule(sMsg);
    }

    private void doProcess_NMAL2610Scrn00_ClearFilter(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        searchTerritoryRules(cMsg, sMsg);

        NMAL2610CommonLogic.setBackUpRule(sMsg);
    }

    private void doProcess_NMAL2610Scrn00_OnChange_ResrcActvFlg(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        searchTerritoryResource(cMsg, sMsg);

        NMAL2610CommonLogic.setBackUpResource(cMsg);
    }

    // Add Start 2017/03/09 QC#15878
    private void doProcess_NMAL2610Scrn00_OpenWin_TargetFrom(NMAL2610CMsg bizMsg, NMAL2610SMsg glblMsg) {

        int idx = bizMsg.xxCellIdx.getValueInt();
        String trtyRuleTpCd = bizMsg.C.no(idx).trtyRuleTpCd_P1.getValue();
        EZDCStringItem valueItem = bizMsg.C.no(idx).trtyRuleFromValTxt_C1;

        if (!NMAL2610CommonLogic.checkInputLength(getGlobalCompanyCode(), trtyRuleTpCd, valueItem, "Value From")) {
            bizMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
            bizMsg.setMessageInfo(NMAL2610Constant.ZZM9037E);
        }
    }

    private void doProcess_NMAL2610Scrn00_OpenWin_TargetTo(NMAL2610CMsg bizMsg, NMAL2610SMsg glblMsg) {

        int idx = bizMsg.xxCellIdx.getValueInt();
        String trtyRuleTpCd = bizMsg.C.no(idx).trtyRuleTpCd_P1.getValue();
        EZDCStringItem valueItem = bizMsg.C.no(idx).trtyRuleThruValTxt_C1;

        if (!NMAL2610CommonLogic.checkInputLength(getGlobalCompanyCode(), trtyRuleTpCd, valueItem, "Value To")) {
            bizMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
            bizMsg.setMessageInfo(NMAL2610Constant.ZZM9037E);
        }
    }

    // Add End 2017/03/09 QC#15878
    // ADD START 2017/06/06 QC#16677
    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2610Scrn00_PageJump(NMAL2610CMsg bizMsg, NMAL2610SMsg glblMsg) {
        // 2020/02/18 QC#54222-2 Add Start
        bizMsg.setCommitSMsg(true);
        // 2020/02/18 QC#54222-2 Add End

        // the input data have to copy to glblMsg
        NMAL2610CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum_C1.setValue((bizMsg.C.length() * (bizMsg.xxPageShowCurNum_C1.getValueInt() - 1)) + 1);
        NMAL2610CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.C, glblMsg.C);

        resetTerritoryRuleOperandPulldownList(bizMsg);
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2610Scrn00_PageNext(NMAL2610CMsg bizMsg, NMAL2610SMsg glblMsg) {
        // 2020/02/18 QC#54222-2 Add Start
        bizMsg.setCommitSMsg(true);
        // 2020/02/18 QC#54222-2 Add End

        // the input data have to copy to glblMsg
        NMAL2610CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum_C1.setValue(bizMsg.xxPageShowToNum_C1.getValueInt() + 1);
        NMAL2610CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.C, glblMsg.C);

        resetTerritoryRuleOperandPulldownList(bizMsg);
    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2610Scrn00_PagePrev(NMAL2610CMsg bizMsg, NMAL2610SMsg glblMsg) {
        // 2020/02/18 QC#54222-2 Add Start
        bizMsg.setCommitSMsg(true);
        // 2020/02/18 QC#54222-2 Add End

        // the input data have to copy to glblMsg
        NMAL2610CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum_C1.setValue(bizMsg.xxPageShowFromNum_C1.getValueInt() - bizMsg.C.length());
        NMAL2610CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.C, glblMsg.C);

        resetTerritoryRuleOperandPulldownList(bizMsg);
    }
    // ADD E N D 2017/06/06 QC#16677

    // 2019/05/13 QC#50128 Add Start
    private String getFullNameForRecHist(String xxRecHistByNm) {

        String fullName = fullNameCache.get(xxRecHistByNm);
        if (fullName == null) {
            fullName = ZYPRecHistUtil.getFullNameForRecHist(xxRecHistByNm);
            if (fullName != null ) {
                fullNameCache.put(xxRecHistByNm, fullName);
            }
        }
        return fullName;
    }
    // 2019/05/13 QC#50128 Add End
}
