/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2610.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItemArray;
import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDCStringItem;
import parts.common.EZDCStringItemArray;
import parts.common.EZDCommonFunc;
import parts.common.EZDMsg;
import parts.common.EZDSItem;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import business.blap.NMAL2610.NMAL2610CMsg;
import business.blap.NMAL2610.NMAL2610Query;
import business.blap.NMAL2610.NMAL2610SMsg;
import business.blap.NMAL2610.NMAL2610_ACMsg;
import business.blap.NMAL2610.NMAL2610_ASMsg;
import business.blap.NMAL2610.NMAL2610_CCMsg;
import business.blap.NMAL2610.NMAL2610_CSMsg;
import business.blap.NMAL2610.NMAL2610_DCMsg;
import business.blap.NMAL2610.NMAL2610_DSMsg;
import business.blap.NMAL2610.NMAL2610_YCMsg;
import business.blap.NMAL2610.constant.NMAL2610Constant;
import business.db.DS_ACCT_CLSTMsg;
import business.db.DS_ACCT_GRPTMsg;
import business.db.STTMsg;
import business.db.TRTY_RULE_OPRD_TPTMsg;
import business.db.TRTY_RULE_OPRD_TPTMsgArray;
import business.db.TRTY_RULE_TPTMsg;
import business.parts.NMZC270001PMsg;
import business.parts.NMZC270001_trtyRuleListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC270001.NMZC270001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_TEAM_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_TIER;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_LOGIC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_OPRD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * <pre>
 * Business ID : NMAL2610 Territory Detail
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/02/05   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/03/03   Fujitsu         W.Honda         Update          CSA-QC#4545
 * 2016/03/03   Fujitsu         C.Tanaka        Update          CSA-QC#4551
 * 2016/03/09   Fujitsu         C.Yokoi         Update          CSA-QC#5222
 * 2016/03/10   Fujitsu         C.Yokoi         Update          CSA-QC#5221, CSA-QC#5237, CSA-QC#5371
 * 2016/03/24   Fujitsu         C.Yokoi         Update          CSA-QC#5973
 * 2016/03/30   Fujitsu         C.Yokoi         Update          CSA-QC#6255
 * 2016/04/05   Fujitsu         C.Yokoi         Update          CSA-QC#6003
 * 2016/06/24   Hitachi         A.Kohinata      Update          CSA-QC#10276
 * 2016/06/27   Hitachi         A.Kohinata      Update          CSA-QC#10365
 * 2016/06/29   Hitachi         A.Kohinata      Update          CSA-QC#11087
 * 2016/07/07   Fujitsu         C.Tanaka        Update          CSA-QC#11178
 * 2016/08/24   SRAA            Y.Chen          Update          CSA-QC#5786
 * 2016/08/24   SRAA            Y.Chen          Update          CSA-QC#7966
 * 2016/08/31   SRAA            Y.Chen          Update          QC#11728
 * 2016/09/08   Fujitsu         C.Yokoi         Update          CSA-QC#7964
 * 2016/09/14   SRAA            Y.Chen          Update          QC#11638
 * 2017/06/19   Hitachi         J.Kim           Update          QC#19029
 * 2017/06/22   Hitachi         E.Kameishi      Update          QC#18397
 * 2017/06/23   Fujitsu         N.Aoyama        Update          QC#16677
 * 2017/08/21   Fujitsu         A.Kosai         Update          QC#20273
 * 2017/10/17   Fujitsu         H.Sugawara      Update          QC#21753
 * 2017/11/16   Fujitsu         N.Sugiura       Update          CSA-QC#20597
 * 2017/11/21   Fujitsu         M.Ohno          Update          QC#21350
 * 2017/12/05   Fujitsu         N.Sugiura       Update          QC#21270
 * 2018/06/01   Fujitsu         Hd.Sugawara     Update          QC#24293
 * 2018/08/03   Fujitsu         T.Noguchi       Update          QC#26942
 * 2018/09/28   Fujitsu         H.Kumagai        Update         QC#28278
 * 2019/09/05   Fujitsu         Hd.Sugawara     Update          QC#51704
 * 2019/12/06   Fujitsu         A.Kazuki        Update          QC#53019
 * 2019/12/06   Fujitsu         A.Kazuki        Update          QC#54222
 * 2019/12/27   Fujitsu         A.Kazuki        Update          QC#54222-1
 * 2020/03/28   Fujitsu         M.Ohno          Update          QC#56217
 * 2020/05/07   Fujitsu         C.Hara          Update          QC#56510
 * 2020/05/08   Fujitsu         C.Hara          Update          QC#56810
 * 2020/06/24   CITS            K.Ogino         Update          QC#56925
 *</pre>
 */
public class NMAL2610CommonLogic {

    /**
     * createPulldownList
     * 
     * @param cd EZDCStringItemArray
     * @param value EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[]
     */
    public static void createPulldownList(EZDCStringItemArray cd, EZDCStringItemArray value, List<Map<String, Object>> pullDownList, String[] dbColumn) {

        for (int i = 0; i < pullDownList.size(); i++) {
            Map<String, Object> pullDownData = pullDownList.get(i);

            ZYPEZDItemValueSetter.setValue(cd.no(i), (String) pullDownData.get(dbColumn[0]));
            ZYPEZDItemValueSetter.setValue(value.no(i), (String) pullDownData.get(dbColumn[1]));

            if (i >= cd.length()) {
                break;
            }
        }
    }

    /**
     * The method explanation: create Pulldown List.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param globalCmpyCd String
     */
    public static void createPulldownList(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg, String globalCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(NMAL2610Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("glblCmpyCd", globalCmpyCd);
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);

        // Business Area
        S21SsmEZDResult bizAreaOrgPulldownList = NMAL2610Query.getInstance().getBizAreaOrgPulldownList(ssmParam);

        if (bizAreaOrgPulldownList.isCodeNormal()) {
            List<Map<String, Object>> bizAreaOrgList = (List<Map<String, Object>>) bizAreaOrgPulldownList.getResultObject();
            NMAL2610CommonLogic.createPulldownList(cMsg.bizAreaOrgCd_H1, cMsg.bizAreaOrgNm_H1, bizAreaOrgList, new String[] {NMAL2610Constant.BIZ_AREA_ORG_CD_DBCOLUMN, NMAL2610Constant.BIZ_AREA_ORG_NM_DBCOLUMN });
        }

        // Rank
        S21SsmEZDResult orgTierPulldownList = NMAL2610Query.getInstance().getOrgTierPulldownList(ssmParam);

        if (orgTierPulldownList.isCodeNormal()) {
            List<Map<String, Object>> orgTierList = (List<Map<String, Object>>) orgTierPulldownList.getResultObject();
            NMAL2610CommonLogic.createPulldownList(cMsg.orgTierCd_H1, cMsg.orgTierNm_H1, orgTierList, new String[] {NMAL2610Constant.ORG_TIER_CD_DBCOLUMN, NMAL2610Constant.ORG_TIER_NM_DBCOLUMN });
        }

        // Territory Type
        ZYPCodeDataUtil.createPulldownList(TRTY_TP.class, cMsg.trtyTpCd_H1, cMsg.trtyTpDescTxt_H1);

        // Territory Group Type
        ZYPCodeDataUtil.createPulldownList(TRTY_GRP_TP.class, cMsg.trtyGrpTpCd_H1, cMsg.trtyGrpTpDescTxt_H1);

        // Territory Rule Type
        ssmParam.put("orgStruTp_TS", ORG_STRU_TP.TERRITORY_STRUCTURE);
        S21SsmEZDResult trtyRulePulldownList = NMAL2610Query.getInstance().getTrtyRulePulldownList(ssmParam);

        if (trtyRulePulldownList.isCodeNormal()) {
            List<Map<String, Object>> trtyRuleList = (List<Map<String, Object>>) trtyRulePulldownList.getResultObject();
            NMAL2610CommonLogic.createPulldownList(cMsg.trtyRuleTpCd_C1, cMsg.trtyRuleTpDescTxt_C1, trtyRuleList, new String[] {NMAL2610Constant.TRTY_RULE_TP_CD_DBCOLUMN, NMAL2610Constant.TRTY_RULE_TP_DESC_TXT_DBCOLUMN });
        }

        // QC#5786
        // // Territory Rule Operand Type
        // ZYPCodeDataUtil.createPulldownList(TRTY_RULE_OPRD_TP.class, cMsg.trtyRuleOprdTpCd_C1, cMsg.trtyRuleOprdTpDescTxt_C1);

        // Territory Rule Operand Type
        ZYPCodeDataUtil.createPulldownList(TRTY_RULE_LOGIC_TP.class, cMsg.trtyRuleLogicTpCd_C1, cMsg.trtyRuleLogicTpDescTxt_C1);

        // Account Team Role Type
        ZYPCodeDataUtil.createPulldownList(ACCT_TEAM_ROLE_TP.class, cMsg.acctTeamRoleTpCd_D1, cMsg.acctTeamRoleTpDescTxt_D1);

    }

    // 2017/06/23 QC#16677 Mod START
    /**
     * <pre>
     * clear mandantory check
     * </pre>
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
//    /**
//     * <pre>
//     * clear mandantory check
//     * </pre>
//     * @param cMsg NMAL2610CMsg
//     * @param glblCmpyCd String
//     * @return boolean
//     */
//    public static boolean checkMandantoryItem(NMAL2610CMsg cMsg, String glblCmpyCd) {
//        boolean successFlg = true;
//
//        // ### Territory ###
//        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
//            // Start Date
//            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).effFromDt_A1)) {
//                cMsg.A.no(i).effFromDt_A1.setErrorInfo(1, "ZZM9000E", new String[] {"start date" });
//                successFlg = false;
//            }
//        }
//
//        if (!successFlg) {
//            cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY);
//            return successFlg;
//        }
//
//        // ## Territory Rule ##
//        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
//            // Rule Type
//            if (!ZYPCommonFunc.hasValue(cMsg.C.no(i).trtyRuleTpCd_P1)) {
//                cMsg.C.no(i).trtyRuleTpCd_P1.setErrorInfo(1, "ZZM9000E", new String[] {"Rule Type" });
//                successFlg = false;
//            }
//
//            // Operator
//            if (!ZYPCommonFunc.hasValue(cMsg.C.no(i).trtyRuleOprdTpCd_P1)) {
//                cMsg.C.no(i).trtyRuleOprdTpCd_P1.setErrorInfo(1, "ZZM9000E", new String[] {"Operator" });
//                successFlg = false;
//            }
//
//            // Value From
//            if (!ZYPCommonFunc.hasValue(cMsg.C.no(i).trtyRuleFromValTxt_C1)) {
//                cMsg.C.no(i).trtyRuleFromValTxt_C1.setErrorInfo(1, "ZZM9000E", new String[] {"Value from" });
//                successFlg = false;
//            }
//
//            // START 2017/06/22 E.Kameishi [QC#18397, MOD]
//            // Value From Data
//            if (TRTY_RULE_OPRD_TP.EQUAL.equals(cMsg.C.no(i).trtyRuleOprdTpCd_P1.getValue())) {
//                String trtyRuleTpCd = cMsg.C.no(i).trtyRuleTpCd_P1.getValue();
//                if (!checkRuleValue(glblCmpyCd, trtyRuleTpCd, cMsg.C.no(i).trtyRuleFromValTxt_C1, "Value from")) {
//                    successFlg = false;
//                }
//
//                // Value Thru Data
//                if (!checkRuleValue(glblCmpyCd, trtyRuleTpCd, cMsg.C.no(i).trtyRuleThruValTxt_C1, "Value To")) {
//                    successFlg = false;
//                }
//            }
//            // END 2017/06/22 E.Kameishi [QC#18397, MOD]
//
//            // Start Date
//            if (!ZYPCommonFunc.hasValue(cMsg.C.no(i).effFromDt_C1)) {
//                cMsg.C.no(i).effFromDt_C1.setErrorInfo(1, "ZZM9000E", new String[] {"start date" });
//                successFlg = false;
//            }
//        }
//
//        if (!successFlg) {
//            cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
//            return successFlg;
//        }
//
//        // ## Resource Assign ##
//        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
//            // Start Date
//            if (!ZYPCommonFunc.hasValue(cMsg.D.no(i).effFromDt_D1)) {
//                cMsg.D.no(i).effFromDt_D1.setErrorInfo(1, "ZZM9000E", new String[] {"start date" });
//                cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_BUILD_HIERARCHY);
//                successFlg = false;
//            }
//        }
//
//        if (!successFlg) {
//            cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_RESOURCE_ASSIGNE);
//        }
//        return successFlg;
//    }

    public static boolean checkMandantoryItem(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg, String glblCmpyCd) {
        boolean successFlg = true;

        // ### Territory ###
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            // Start Date
            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).effFromDt_A1)) {
                cMsg.A.no(i).effFromDt_A1.setErrorInfo(1, "ZZM9000E", new String[] {"start date" });
                successFlg = false;
            }
        }

        if (!successFlg) {
            cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY);
            return successFlg;
        }

        int maxDisplayRows = cMsg.C.length();
        int maxIndex = sMsg.C.getValidCount();

        // ## Territory Rule ##
        for (int i = 0; i < maxIndex; i++) {
            // Rule Type
            if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).trtyRuleTpCd_P1)) {
                sMsg.C.no(i).trtyRuleTpCd_P1.setErrorInfo(1, "ZZM9000E", new String[] {"Rule Type" });
                if(successFlg == true){
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum_C1.setValue(errScrnInex);
                    successFlg = false;
                }
            }

            // Operator
            if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).trtyRuleOprdTpCd_P1)) {
                sMsg.C.no(i).trtyRuleOprdTpCd_P1.setErrorInfo(1, "ZZM9000E", new String[] {"Operator" });
                if(successFlg == true){
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum_C1.setValue(errScrnInex);
                    successFlg = false;
                }
            }

            // 2018/08/03 QC#26942 Add Start
            if ((!ZYPCommonFunc.hasValue(sMsg.C.no(i).effThruDt_C1.getValue()) ||
                (EZDCommonFunc.cmpDate(sMsg.C.no(i).effThruDt_C1.getValue(), ZYPDateUtil.getSalesDate()) > -1))){
            // 2018/08/03 QC#26942 Add End
                // Value From
                if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).trtyRuleFromValTxt_C1)) {
                    sMsg.C.no(i).trtyRuleFromValTxt_C1.setErrorInfo(1, "ZZM9000E", new String[] {"Value from" });
                    if(successFlg == true){
                        int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                        cMsg.xxPageShowFromNum_C1.setValue(errScrnInex);
                        successFlg = false;
                    }
                }

                // Value From Data
                if (TRTY_RULE_OPRD_TP.EQUAL.equals(sMsg.C.no(i).trtyRuleOprdTpCd_P1.getValue())) {
                    String trtyRuleTpCd = sMsg.C.no(i).trtyRuleTpCd_P1.getValue();
                    if (!checkRuleValue(glblCmpyCd, trtyRuleTpCd, sMsg.C.no(i).trtyRuleFromValTxt_C1, "Value from")) {
                        if(successFlg == true){
                            int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                            cMsg.xxPageShowFromNum_C1.setValue(errScrnInex);
                            successFlg = false;
                        }
                    }

                    // Value Thru Data
                    if (!checkRuleValue(glblCmpyCd, trtyRuleTpCd, sMsg.C.no(i).trtyRuleThruValTxt_C1, "Value To")) {
                        if(successFlg == true){
                            int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                            cMsg.xxPageShowFromNum_C1.setValue(errScrnInex);
                            successFlg = false;
                        }
                    }
                }
            // 2018/08/03 QC#26942 Add Start
            }
            // 2018/08/03 QC#26942 Add End

            // Start Date
            if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).effFromDt_C1)) {
                cMsg.C.no(i).effFromDt_C1.setErrorInfo(1, "ZZM9000E", new String[] {"start date" });
                if(successFlg == true){
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum_C1.setValue(errScrnInex);
                    successFlg = false;
                }
            }
        }

        if (!successFlg) {
            cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
            NMAL2610CommonLogic.loadOnePageToCMsg(cMsg, cMsg.C, sMsg.C);
            return successFlg;
        }

        // ## Resource Assign ##
        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
            // Start Date
            if (!ZYPCommonFunc.hasValue(cMsg.D.no(i).effFromDt_D1)) {
                cMsg.D.no(i).effFromDt_D1.setErrorInfo(1, "ZZM9000E", new String[] {"start date" });
                cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_BUILD_HIERARCHY);
                successFlg = false;
            }
        }

        if (!successFlg) {
            cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_RESOURCE_ASSIGNE);
        }
        return successFlg;
    }
 // 2017/06/23 QC#16677 Mod E N D
    
    /**
     * @param cd EZDBBigDecimalItemArray
     * @param value EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[]
     */
    public static void createPulldownList(EZDCBigDecimalItemArray cd, EZDCStringItemArray value, List<Map> pullDownList, String[] dbColumn) {

        for (int i = 0; i < pullDownList.size(); i++) {
            Map pullDownData = pullDownList.get(i);

            ZYPEZDItemValueSetter.setValue(cd.no(i), (BigDecimal) pullDownData.get(dbColumn[0]));
            ZYPEZDItemValueSetter.setValue(value.no(i), (String) pullDownData.get(dbColumn[1]));

            if (i >= cd.length()) {
                break;
            }
        }
    }

    /**
     * isChangedPrevTab
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @return boolean
     */
    public static boolean isChangedPrevTab(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        boolean changedFlg = false;

        if (ZYPCommonFunc.hasValue(cMsg.xxHldFlg) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxHldFlg.getValue())) {
            cMsg.xxHldFlg.setValue(ZYPConstant.FLG_OFF_N);
            return changedFlg;
        }

        if (ZYPCommonFunc.hasValue(sMsg.orgCd_H1)) {
            if (!cMsg.bizAreaOrgCd_P1.getValue().equals(sMsg.bizAreaOrgCd_P1.getValue())
                || !cMsg.trtyTpCd_P1.getValue().equals(sMsg.trtyTpCd_P1.getValue())
                || !cMsg.orgNm_H1.getValue().equals(sMsg.orgNm_H1.getValue())
                || !cMsg.orgTierCd_P1.getValue().equals(sMsg.orgTierCd_P1.getValue())
                || !cMsg.orgDescTxt_H1.getValue().equals(sMsg.orgDescTxt_H1.getValue())
                || !cMsg.trtyGrpTpCd_P1.getValue().equals(sMsg.trtyGrpTpCd_P1.getValue())
                || !cMsg.effFromDt_H1.getValue().equals(sMsg.effFromDt_H1.getValue())
                || !cMsg.effThruDt_H1.getValue().equals(sMsg.effThruDt_H1.getValue())
                // QC#7966
                // || !cMsg.orgNm_F1.getValue().equals(sMsg.orgNm_F1.getValue())
                // || (!ZYPCommonFunc.hasValue(cMsg.xxChkBox_H1) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_H1.getValue()))
                ) {
                changedFlg = true;
            }
        }

        if (NMAL2610Constant.TAB_TERRITORY.equals(cMsg.xxDplyTab.getValue())) {
            if (sMsg.A.getValidCount() != cMsg.A.getValidCount()) {
                changedFlg = true;
            } else {
                for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                    NMAL2610_ACMsg acMsg = cMsg.A.no(i);
                    NMAL2610_ASMsg asMsg = sMsg.A.no(i);
                    if (!acMsg.orgNm_A1.getValue().equals(asMsg.orgNm_A1.getValue())
                            || !acMsg.effFromDt_A1.getValue().equals(asMsg.effFromDt_A1.getValue())
                            || !acMsg.effThruDt_A1.getValue().equals(asMsg.effThruDt_A1.getValue())
                            || (!ZYPCommonFunc.hasValue(acMsg.xxChkBox_A1) && ZYPConstant.FLG_ON_Y.equals(asMsg.xxChkBox_A1.getValue()))) {
                        changedFlg = true;
                    }
                }
            }
        } else if (NMAL2610Constant.TAB_TERRITORY_RULES.equals(cMsg.xxDplyTab.getValue())) {
            if (sMsg.C.getValidCount() != cMsg.C.getValidCount()) {
                changedFlg = true;
            } else {
                for (int i = 0; i < cMsg.C.getValidCount(); i++) {
                    NMAL2610_CCMsg ccMsg = cMsg.C.no(i);
                    NMAL2610_CSMsg csMsg = sMsg.C.no(i);
                    if (!ccMsg.trtyRuleTpCd_P1.getValue().equals(csMsg.trtyRuleTpCd_P1.getValue())
                            || !ccMsg.trtyRuleOprdTpCd_P1.getValue().equals(csMsg.trtyRuleOprdTpCd_P1.getValue())
                            || !ccMsg.trtyRuleFromValTxt_C1.getValue().equals(csMsg.trtyRuleFromValTxt_C1.getValue())
                            || !ccMsg.trtyRuleThruValTxt_C1.getValue().equals(csMsg.trtyRuleThruValTxt_C1.getValue())
                            || !ccMsg.trtyRuleLogicTpCd_P1.getValue().equals(csMsg.trtyRuleLogicTpCd_P1.getValue())
                            || !ccMsg.effFromDt_C1.getValue().equals(csMsg.effFromDt_C1.getValue())
                            || !ccMsg.effThruDt_C1.getValue().equals(csMsg.effThruDt_C1.getValue())
                            || (!ZYPCommonFunc.hasValue(ccMsg.xxChkBox_C1) && ZYPConstant.FLG_ON_Y.equals(csMsg.xxChkBox_C1.getValue()))) {
                        changedFlg = true;
                    }
                }
            }
        } else if (NMAL2610Constant.TAB_RESOURCE_ASSIGNE.equals(cMsg.xxDplyTab.getValue())) {
            if (sMsg.D.getValidCount() != cMsg.D.getValidCount()) {
                changedFlg = true;
            } else {
                for (int i = 0; i < cMsg.D.getValidCount(); i++) {
                    NMAL2610_DCMsg dcMsg = cMsg.D.no(i);
                    NMAL2610_DSMsg dsMsg = sMsg.D.no(i);
                    if (!dcMsg.xxPsnNm_D1.getValue().equals(dsMsg.xxPsnNm_D1.getValue()) //
                            || !dcMsg.psnCd_D1.getValue().equals(dsMsg.psnCd_D1.getValue())
                            || !dcMsg.acctTeamRoleTpCd_P1.getValue().equals(dsMsg.acctTeamRoleTpCd_P1.getValue())
                            || !dcMsg.effFromDt_D1.getValue().equals(dsMsg.effFromDt_D1.getValue())
                            || !dcMsg.effThruDt_D1.getValue().equals(dsMsg.effThruDt_D1.getValue())
                            || (!ZYPCommonFunc.hasValue(dcMsg.xxChkBox_D1) && ZYPConstant.FLG_ON_Y.equals(dsMsg.xxChkBox_D1.getValue()))) {
                        changedFlg = true;
                    }
                }
            }
        }

        if (changedFlg == true) {
            cMsg.xxHldFlg.setValue(ZYPConstant.FLG_ON_Y);
            cMsg.setMessageInfo(NMAL2610Constant.NMAM8286W);
        }
        return changedFlg;
    }

// QC#7966
//    /**
//     * clearHeader
//     * @param cMsg NMAL2610CMsg
//     * @param sMsg NMAL2610SMsg
//     */
//    public static void clearHeader(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
//
//        sMsg.orgCd_H1.clear();
//        sMsg.bizAreaOrgCd_P1.clear();
//        sMsg.trtyTpCd_P1.clear();
//        sMsg.orgNm_H1.clear();
//        sMsg.orgTierCd_P1.clear();
//        sMsg.orgDescTxt_H1.clear();
//        // 2016/06/27 QC#10365 Add Start
//        cMsg.struDfnDescTxt_H1.clear();
//        // 2016/06/27 QC#10365 Add End
//        sMsg.trtyGrpTpCd_P1.clear();
//        sMsg.orgNm_F1.clear();
//        sMsg.effFromDt_H1.clear();
//        sMsg.effThruDt_H1.clear();
//        sMsg.xxChkBox_H1.clear();
//
//        cMsg.orgCd_H1.clear();
//        cMsg.bizAreaOrgCd_P1.clear();
//        cMsg.trtyTpCd_P1.clear();
//        cMsg.orgNm_H1.clear();
//        cMsg.orgTierCd_P1.clear();
//        cMsg.orgDescTxt_H1.clear();
//        cMsg.trtyGrpTpCd_P1.clear();
//        cMsg.orgNm_F1.clear();
//        cMsg.effFromDt_H1.clear();
//        cMsg.effThruDt_H1.clear();
//        cMsg.xxChkBox_H1.clear();
//
//    }

    /**
     * clearforTerritorySearch
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     */
    public static void clearforTerritorySearch(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.B.clear();
        cMsg.B.setValidCount(0);
        cMsg.X.clear();
        cMsg.X.setValidCount(0);

        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.B.clear();
        sMsg.B.setValidCount(0);
        sMsg.X.clear();
        sMsg.X.setValidCount(0);
    }

    /**
     * clearforTerritoryRulesSearch
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     */
    public static void clearforTerritoryRulesSearch(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        cMsg.C.clear();
        cMsg.C.setValidCount(0);
        cMsg.Y.clear();
        cMsg.Y.setValidCount(0);

        sMsg.C.clear();
        sMsg.C.setValidCount(0);
        sMsg.E.clear();
        sMsg.E.setValidCount(0);
        sMsg.Y.clear();
        sMsg.Y.setValidCount(0);
    }

    /**
     * clearforResourceSearch
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     */
    public static void clearforResourceSearch(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        cMsg.D.clear();
        cMsg.D.setValidCount(0);
        cMsg.Z.clear();
        cMsg.Z.setValidCount(0);

        sMsg.D.clear();
        sMsg.D.setValidCount(0);
        sMsg.Z.clear();
        sMsg.Z.setValidCount(0);
    }
    /**
     * check if from date is bigger than sales date
     * @param effFromDt String
     * @return boolean
     */
    public static boolean checkFromDate(String effFromDt) {

//        if (effFromDt.compareTo(ZYPDateUtil.getSalesDate()) >= 0) {
//            return true;
//        }
//        return false;

        //QC#3383 Specification change. Always return true, past date input is enable.
        return true;
    }

    /**
     * checkActiveDate
     * @param effFromDt String
     * @param effThruDt String
     * @param currentDt String
     * @return boolean
     */
    public static boolean checkActiveDate(String effFromDt, String effThruDt, String currentDt) {

        if (effFromDt.compareTo(currentDt) <= 0) {
            if (!ZYPCommonFunc.hasValue(effThruDt)) {
                return true;
            } else if (effThruDt.compareTo(currentDt) >= 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * checkDuplicateTerm
     * @param effFromDt String
     * @param effThruDt String
     * @param cmprEffFromDt String
     * @param cmprEffThruDt String
     * @return boolean
     */
    public static boolean checkDuplicateTerm(String effFromDt, String effThruDt, String cmprEffFromDt, String cmprEffThruDt) {

        // Mod Start 2019/09/05 QC#51704
        //if (ZYPCommonFunc.hasValue(cmprEffThruDt) && effFromDt.compareTo(cmprEffThruDt) >= 0) {
        if (ZYPCommonFunc.hasValue(cmprEffThruDt) && effFromDt.compareTo(cmprEffThruDt) > 0) {
            // Mod End 2019/09/05 QC#51704
            return true;
        } else {
            if (!ZYPCommonFunc.hasValue(effThruDt)) {
                return false;
                // Mod Start 2019/09/05 QC#51704
            //} else if (effThruDt.compareTo(cmprEffFromDt) <= 0) {
            } else if (effThruDt.compareTo(cmprEffFromDt) < 0) {
                // Mod End 2019/09/05 QC#51704
                return true;
            }
        }
        return false;
    }

    /**
     * checkInputHeaderForSubmit
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @return boolean
     */
    public static boolean checkInputHeaderForSubmit(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        boolean isSuccess = true;

        // 2018/09/28 Add Start QC#28278
        int i = 0;
        for (; i < cMsg.A.getValidCount(); i++) {
            if (!existsTerritoryName(cMsg, i)) {
                return false;
            }
        }
        // 2018/09/28 Add End QC#28278

        if (!cMsg.effFromDt_H1.getValue().equals(sMsg.effFromDt_H1.getValue())) {
            if (!NMAL2610CommonLogic.checkFromDate(cMsg.effFromDt_H1.getValue())) {
                cMsg.effFromDt_H1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT, NMAL2610Constant.NAME_FOR_MESSAGE_CURRENT_DT });
                isSuccess = false;
            }
        }

        // 2016/09/08 CSA-QC#7964 Mod Start
        if (!ORG_TIER._1.equals(cMsg.orgTierCd_P1.getValue()) && !ORG_TIER._2.equals(cMsg.orgTierCd_P1.getValue())) {
            if (!ZYPCommonFunc.hasValue(cMsg.trtyTpCd_P1)) {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.trtyTpCd_P1.setErrorInfo(1, NMAL2610Constant.NMAM0014E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY_TYPE });
                // 2016/03/03 CSA-QC#4545 Add End
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(cMsg.trtyGrpTpCd_P1)) {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.trtyGrpTpCd_P1.setErrorInfo(1, NMAL2610Constant.NMAM0014E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY_GROUP_TYPE });
                // 2016/03/03 CSA-QC#4545 Add End
                isSuccess = false;
            }
        }
        // 2016/09/08 CSA-QC#7964 Mod End

        if (!ZYPCommonFunc.hasValue(cMsg.orgTierCd_P1)) {
            // 2016/03/03 CSA-QC#4545 Add Start
            cMsg.orgTierCd_P1.setErrorInfo(1, NMAL2610Constant.NMAM0014E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_ORG_TIER_CD });
            // 2016/03/03 CSA-QC#4545 Add End
            isSuccess = false;
        }

        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1)) {
            if (cMsg.effThruDt_H1.getValue().compareTo(cMsg.effFromDt_H1.getValue()) < 0) {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.effThruDt_H1.setErrorInfo(1, NMAL2610Constant.NMAM8239E);
                cMsg.effFromDt_H1.setErrorInfo(1, NMAL2610Constant.NMAM8239E);
                // 2016/03/03 CSA-QC#4545 Add End
                isSuccess = false;
            }
        }

        if (checkDuplicateTerritoryName(cMsg)) {
            isSuccess = false;
        }

        if (checkInvalidRank(cMsg)) {
            isSuccess = false;
        }

        if (!isSuccess) {
            cMsg.setMessageInfo(NMAL2610Constant.ZZM9037E);
        }

        return isSuccess;
    }

    /**
     * checkDuplicateTerritoryName
     * @param cMsg Business Msg
     * @return boolean
     */
    public static boolean checkDuplicateTerritoryName(NMAL2610CMsg cMsg) {

        S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().checkDuplicateTerritoryName(cMsg);
        String orgCd = (String) ssmResult.getResultObject();

        // For Create
        if (!ZYPCommonFunc.hasValue(cMsg.orgCd_H1)) {
            if (orgCd != null) {
                // 2017/12/05 QC#21270 Mod Start
                // 2016/03/03 CSA-QC#4545 Add Start
                // cMsg.orgNm_H1.setErrorInfo(1, NMAL2610Constant.NMAM8392E);
                cMsg.orgNm_H1.setErrorInfo(1, NMAL2610Constant.NMAM8635E,
                        new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY, NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY, NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY});
                // 2016/03/03 CSA-QC#4545 Add End
                // 2017/12/05 QC#21270 Mod End
                return true;
            }
        // For Update
        } else if (orgCd != null && !orgCd.equals(cMsg.orgCd_H1.getValue())) {
            // 2017/12/05 QC#21270 Mod Start
            // 2016/03/03 CSA-QC#4545 Add Start
            // cMsg.orgNm_H1.setErrorInfo(1, NMAL2610Constant.NMAM8392E);
            cMsg.orgNm_H1.setErrorInfo(1, NMAL2610Constant.NMAM8635E,
                    new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY, NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY, NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY});
            // 2016/03/03 CSA-QC#4545 Add End
            // 2017/12/05 QC#21270 Mod End
            return true;
        }

        return false;
    }

    /**
     * checkInputTerritoryForSubmit
     * @param cMsg NMAL2610CMsg
     * @return boolean
     */
    public static boolean checkInputTerritoryForSubmit(NMAL2610CMsg cMsg) {

        int i = 0;
        String salesDate = ZYPDateUtil.getSalesDate();
        String activeTerritoryEffThruDt = null;

        for (; i < cMsg.A.getValidCount(); i++) {
            // 2016/06/24 QC#10276 Add Start
            if (!existsTerritoryName(cMsg, i)) {
                return false;
            }
            // 2016/06/24 QC#10276 Add End

            if (!compareWithParent(cMsg, i)) {
                return false;
            }

            if (checkActiveDate(cMsg.A.no(i).effFromDt_A1.getValue(), cMsg.A.no(i).effThruDt_A1.getValue(), ZYPDateUtil.getSalesDate())) {
                activeTerritoryEffThruDt = cMsg.A.no(i).effThruDt_A1.getValue();
            }
        }

        i = 0;
        for (; i < cMsg.B.getValidCount(); i++) {
            if (!compareWithChild(cMsg, i, activeTerritoryEffThruDt)) {
                return false;
            }
        }

        if (!isMultipleActiveTerritory(cMsg, salesDate)) {
            cMsg.setMessageInfo(NMAL2610Constant.NMAM0298E);
            return false;
        }

        return true;
    }

    // 2016/06/24 QC#10276 Add Start
    private static boolean existsTerritoryName(NMAL2610CMsg cMsg, int i) {
        S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().getTerritoryByName(cMsg, i);
        if (ssmResult.isCodeNormal()) {
            Map resultMap = (Map) ssmResult.getResultObject();
            String orgCd = (String) (resultMap.get(NMAL2610Constant.ORG_CD_DBCOLUMN));
            String bizAreaOrgCd = (String) (resultMap.get(NMAL2610Constant.FIRST_ORG_CD_TRTY_DBCOLUMN));
            String orgTierCd = (String) (resultMap.get(NMAL2610Constant.ORG_TIER_CD_TRTY_DBCOLUMN));
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).orgCd_A1, orgCd);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).bizAreaOrgCd_A1, bizAreaOrgCd);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).orgTierCd_A1, orgTierCd);
        } else {
            cMsg.A.no(i).orgNm_A1.setErrorInfo(1, NMAL2610Constant.NMAM8186E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_ORG_NM });
            cMsg.setMessageInfo(NMAL2610Constant.ZZM9037E);
            return false;
        }
        return true;
    }
    // 2016/06/24 QC#10276 Add End

    private static boolean isMultipleActiveTerritory(NMAL2610CMsg cMsg, String salesDate) {

        ArrayList<Integer> indexAry = new ArrayList<Integer>();

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (checkActiveDate(cMsg.A.no(i).effFromDt_A1.getValue(), cMsg.A.no(i).effThruDt_A1.getValue(), salesDate)) {
                indexAry.add(i);
            }
        }

        if (indexAry.size() > 1) {
            for (int i = 0; i < indexAry.size(); i++) {
                cMsg.A.no(indexAry.get(i)).xxChkBox_A1.setErrorInfo(1, NMAL2610Constant.NMAM0298E);
            }
            return false;
        }

        return true;
    }

    private static boolean checkInvalidRank(NMAL2610CMsg cMsg) {

        int i = 0;
        for (; i < cMsg.A.getValidCount(); i++) {

            // Rank Check: should be less than parent territory
            if (ZYPCommonFunc.hasValue(cMsg.orgTierCd_P1)) {
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).orgTierCd_A1.getValue())) {
                    if (new Integer(cMsg.orgTierCd_P1.getValue()).compareTo(new Integer(cMsg.A.no(i).orgTierCd_A1.getValue())) <= 0) {
                        // 2016/03/03 CSA-QC#4545 Add Start
                        cMsg.orgTierCd_P1.setErrorInfo(1, NMAL2610Constant.NMAM0299E);
                        // 2016/03/03 CSA-QC#4545 Add End
                        return true;
                    }
                }
            }
        }

        i = 0;
        for (; i < cMsg.B.getValidCount(); i++) {
            // Rank Check: should be greater than child territory
            if (ZYPCommonFunc.hasValue(cMsg.orgTierCd_P1)) {
                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).orgTierCd_B1.getValue())) {
                    if (new Integer(cMsg.orgTierCd_P1.getValue()).compareTo(new Integer(cMsg.B.no(i).orgTierCd_B1.getValue())) >= 0) {
                        // 2016/03/03 CSA-QC#4545 Add Start
                        cMsg.orgTierCd_P1.setErrorInfo(1, NMAL2610Constant.NMAM0301E);
                        // 2016/03/03 CSA-QC#4545 Add End
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean compareWithParent(NMAL2610CMsg cMsg, int i) {
        boolean isSuccess = true;

        // Start Date Check: should be greater than parent territory assignment
        if (cMsg.effFromDt_H1.getValue().compareTo(cMsg.A.no(i).effFromDt_A1.getValue()) > 0) {
            // 2016/03/03 CSA-QC#4545 Add Start
            cMsg.effFromDt_H1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT, NMAL2610Constant.NAME_FOR_MESSAGE_PARENT_TERRITORY + NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY_ASSIGNEMENT});
            // 2016/03/03 CSA-QC#4545 Add End
            isSuccess = false;
        }

        // 2016/03/10 CSA-QC#5221 Add Start
        // Start Date Check: should be less than parent territory End Date
        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1)) {
            if (cMsg.effThruDt_H1.getValue().compareTo(cMsg.A.no(i).effFromDt_A1.getValue()) < 0) {
                cMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT, NMAL2610Constant.NAME_FOR_MESSAGE_PARENT_TERRITORY + NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT});
                isSuccess = false;
            }
        }
        // 2016/03/10 CSA-QC#5221 Add End

        // End Date Check: should be less than parent territory
        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1)) {
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A1.getValue())) {
                if (cMsg.effThruDt_H1.getValue().compareTo(cMsg.A.no(i).effThruDt_A1.getValue()) < 0) {
                    // 2016/03/03 CSA-QC#4545 Add Start
                    cMsg.effThruDt_H1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2610Constant.NAME_FOR_MESSAGE_PARENT_TERRITORY + NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT  });
                    // 2016/03/03 CSA-QC#4545 Add End
                    isSuccess = false;
                }
            }
        }

        // End Date Check: should be greater than from date
        if (ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A1.getValue())) {
            if (cMsg.A.no(i).effFromDt_A1.getValue().compareTo(cMsg.A.no(i).effThruDt_A1.getValue()) > 0) {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT});
                cMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT});
                // 2016/03/03 CSA-QC#4545 Add End
                isSuccess = false;
            }
        }

        // Biz Area Check: should be match with parent territory
        if (!cMsg.bizAreaOrgCd_P1.getValue().equals(cMsg.A.no(i).bizAreaOrgCd_A1.getValue())) {
            // 2016/03/03 CSA-QC#4545 Add Start
            cMsg.A.no(i).bizAreaOrgCd_A1.setErrorInfo(1, NMAL2610Constant.NMAM0300E);
            // 2016/03/03 CSA-QC#4545 Add End
            isSuccess = false;
        }

        // 2016/03/09 CSA-QC#5222 Add Start
        for (int idx = 0; idx < cMsg.A.getValidCount(); idx++) {
            if (idx == i) {
                continue;
            }

            // Check duplicate active parent territory
            if (!checkDuplicateTerm(cMsg.A.no(i).effFromDt_A1.getValue(), cMsg.A.no(i).effThruDt_A1.getValue(), cMsg.A.no(idx).effFromDt_A1.getValue(), cMsg.A.no(idx).effThruDt_A1.getValue())) {
                cMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAL2610Constant.NMAM8391E);
                cMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAL2610Constant.NMAM8391E);
                cMsg.A.no(idx).effFromDt_A1.setErrorInfo(1, NMAL2610Constant.NMAM8391E);
                cMsg.A.no(idx).effThruDt_A1.setErrorInfo(1, NMAL2610Constant.NMAM8391E);
                isSuccess = false;
            }
        }
        // 2016/03/09 CSA-QC#5222 Add End

        // Add Start 2019/09/05 QC#51704
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_F1.getValue())) {
            S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().getTerritoryParentOverlap(//
                    cMsg.A.no(i).orgCd_A1.getValue(), cMsg.orgCd_H1.getValue(), true);

            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

                for (int j = 0; j < resultList.size(); j++) {
                    Map<String, Object> oneRec = (Map<String, Object>) resultList.get(j);
                    String effFromDt = (String) oneRec.get("EFF_FROM_DT");
                    String effThruDt = (String) oneRec.get("EFF_THRU_DT");

                    if (!checkDuplicateTerm(cMsg.A.no(i).effFromDt_A1.getValue(), cMsg.A.no(i).effThruDt_A1.getValue(), //
                            effFromDt, effThruDt)) {
                        cMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAL2610Constant.NMAM8702E);
                        cMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAL2610Constant.NMAM8702E);
                        isSuccess = false;
                        break;
                    }
                }
            }
        }
        // Add End 2019/09/05 QC#51704

        if (!isSuccess) {
            cMsg.setMessageInfo(NMAL2610Constant.ZZM9037E);
        }
        return isSuccess;
    }

    private static boolean compareWithChild(NMAL2610CMsg cMsg, int i, String activeTerritoryEffThruDt) {
        boolean isSuccess = true;

        // 2016/03/10 CSA-QC#5237 Add Start
        if (ZYPCommonFunc.hasValue(cMsg.B.no(i).effThruDt_B1.getValue())) {
            if (cMsg.B.no(i).effFromDt_B1.getValue().compareTo(cMsg.B.no(i).effThruDt_B1.getValue()) > 0) {
                cMsg.B.no(i).effFromDt_B1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
                cMsg.B.no(i).effThruDt_B1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
                isSuccess = false;
            }
        }
        // 2016/03/10 CSA-QC#5237 Add End

        // Start Date Check: should be less than child territory
        // assignment
        if (cMsg.effFromDt_H1.getValue().compareTo(cMsg.B.no(i).effFromDt_B1.getValue()) > 0) {
            // 2016/03/03 CSA-QC#4545 Add Start
            cMsg.B.no(i).effFromDt_B1.setErrorInfo(1, NMAL2610Constant.NMAM0043E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT, NMAL2610Constant.NAME_FOR_MESSAGE_CHILD_TERRITORY + NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY_ASSIGNEMENT});
            // 2016/03/03 CSA-QC#4545 Add End
            isSuccess = false;
        }

        // End Date Check: should be greater than child territory
        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1)) {
            if (ZYPCommonFunc.hasValue(cMsg.B.no(i).effThruDt_B1.getValue())) {
                if (cMsg.effThruDt_H1.getValue().compareTo(cMsg.B.no(i).effThruDt_B1.getValue()) < 0) {
                    // 2016/03/03 CSA-QC#4545 Add Start
                    cMsg.B.no(i).effThruDt_B1.setErrorInfo(1, NMAL2610Constant.NMAM0043E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2610Constant.NAME_FOR_MESSAGE_CHILD_TERRITORY });
                    // 2016/03/03 CSA-QC#4545 Add End
                    isSuccess = false;
                }
            }

            // 2016/03/10 CSA-QC#5221 Add Start
            // Start Date Check: should be less than parent territory End Date
            if (cMsg.effThruDt_H1.getValue().compareTo(cMsg.B.no(i).effFromDt_B1.getValue()) < 0) {
                cMsg.B.no(i).effFromDt_B1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT, NMAL2610Constant.NAME_FOR_MESSAGE_PARENT_TERRITORY + NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT});
                isSuccess = false;
            }
        }

        // Parent relation expire date check : child org relation should be expired before parent org relation expire
        if (ZYPCommonFunc.hasValue(activeTerritoryEffThruDt)) {
            if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).effThruDt_B1.getValue())
                    || cMsg.B.no(i).effThruDt_B1.getValue().compareTo(activeTerritoryEffThruDt) > 0) {
                cMsg.B.no(i).effThruDt_B1.setErrorInfo(1, NMAL2610Constant.NMAM8361E);
                isSuccess = false;
            }
        }

        // Child relation expire date check : child org relation should be expired before org relation expire
        if (ZYPCommonFunc.hasValue(cMsg.B.no(i).effThruDt_B1) && !cMsg.B.no(i).effThruDt_B1.getValue().equals(cMsg.B.no(i).effThruDt_BB.getValue())) {
            if (!checkActiveChildOrganization(cMsg, i)) {
                cMsg.B.no(i).effThruDt_B1.setErrorInfo(1, NMAL2610Constant.NMAM8361E);
                isSuccess = false;
            }
        }
        // 2016/03/10 CSA-QC#5221 Add End

        // Add Start 2019/09/05 QC#51704
        if (ZYPCommonFunc.hasValue(cMsg.B.no(i).effFromDt_B1.getValue())) {
            S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().getTerritoryParentOverlap(//
                    cMsg.orgCd_H1.getValue(), cMsg.B.no(i).orgCd_B1.getValue(), false);

            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

                for (int j = 0; j < resultList.size(); j++) {
                    Map<String, Object> oneRec = (Map<String, Object>) resultList.get(j);
                    String effFromDt = (String) oneRec.get("EFF_FROM_DT");
                    String effThruDt = (String) oneRec.get("EFF_THRU_DT");

                    if (!checkDuplicateTerm(cMsg.B.no(i).effFromDt_B1.getValue(), cMsg.B.no(i).effThruDt_B1.getValue(), //
                            effFromDt, effThruDt)) {
                        cMsg.B.no(i).effFromDt_B1.setErrorInfo(1, NMAL2610Constant.NMAM8703E);
                        cMsg.B.no(i).effThruDt_B1.setErrorInfo(1, NMAL2610Constant.NMAM8703E);
                        isSuccess = false;
                        break;
                    }
                }
            }
        }
        // Add End 2019/09/05 QC#51704

        if (!isSuccess) {
            cMsg.setMessageInfo(NMAL2610Constant.ZZM9037E);
        }
        return isSuccess;
    }

    // 2017/06/23 QC#16677 Mod START
    /**
     * checkInputTerritoryForSubmit
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @param glblCompanyCd String
     * @return boolean
     */
//    public static boolean checkInputRulesForSubmit(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg, String glblCompanyCd) {
//        if (cMsg.C.getValidCount() == 0) {
//            return true;
//        }
//
//        boolean isSuccess = true;
//        String trtyLogicTp = null;
//        List<Integer> idxActiveRuleList = new ArrayList<Integer>();
//        List<Integer> idxComplementLogicTpList = new ArrayList<Integer>();
//
//        // 2016/03/10 CSA-QC#5237 Add Start
//        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
//
//            // Add Start 2017/03/08 QC#15878
//            if (!checkDiffLine(cMsg, sMsg, i)) {
//                continue;
//            }
//            // Add End 2017/03/08 QC#15878
//
//            if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1.getValue())) {
//                if (cMsg.effThruDt_H1.getValue().compareTo(cMsg.C.no(i).effFromDt_C1.getValue()) < 0) {
//                    cMsg.C.no(i).effFromDt_C1.setErrorInfo(1, NMAL2610Constant.NMAM0043E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY_RULE + NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT, NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY + NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT });
//                    isSuccess = false;
//                }
//
//                //2016/03/24 CSA-QC#5973 Add Start
//                if (ZYPCommonFunc.hasValue(cMsg.C.no(i).effThruDt_C1.getValue())) {
//                        if (cMsg.effThruDt_H1.getValue().compareTo(cMsg.C.no(i).effThruDt_C1.getValue()) < 0) {
//                            cMsg.C.no(i).effThruDt_C1.setErrorInfo(1, NMAL2610Constant.NMAM0043E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY_RULE + NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY + NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT });
//                            isSuccess = false;
//                        }
//                }
//                //2016/03/24 CSA-QC#5973 Add End
//            }
//
//            if (ZYPCommonFunc.hasValue(cMsg.C.no(i).effThruDt_C1.getValue())) {
//                if (cMsg.C.no(i).effFromDt_C1.getValue().compareTo(cMsg.C.no(i).effThruDt_C1.getValue()) > 0) {
//                    cMsg.C.no(i).effFromDt_C1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
//                    cMsg.C.no(i).effThruDt_C1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
//                    isSuccess = false;
//                }
//            }
//
//            // 2016/03/30 CSA-QC#6255 Add Start
//            if (checkActiveDate(cMsg.C.no(i).effFromDt_C1.getValue(), cMsg.C.no(i).effThruDt_C1.getValue(), ZYPDateUtil.getSalesDate())) {
//                idxActiveRuleList.add(i);
//                if (!ZYPCommonFunc.hasValue(cMsg.C.no(i).trtyRuleLogicTpCd_P1)) {
//                    idxComplementLogicTpList.add(i);
//                } else {
//                    if (!ZYPCommonFunc.hasValue(trtyLogicTp)) {
//                        trtyLogicTp = cMsg.C.no(i).trtyRuleLogicTpCd_P1.getValue();
//                    }
//                }
//            } else {
//                continue;
//            }
//            // 2016/03/30 CSA-QC#6255 Add End
//        }
//        // 2016/03/10 CSA-QC#5237 Add End
//
//        if (!isSuccess) {
//            cMsg.setMessageInfo(NMAL2610Constant.ZZM9037E);
//            return false;
//        }
//
//        // 2016/03/30 CSA-QC#6255 Add Start
//// QC#11638
////        if (idxActiveRuleList.size() == 0) {
////            return true;
////        }
//
//        if (idxActiveRuleList.size() > 1) {
//            if (idxActiveRuleList.size() == idxComplementLogicTpList.size()) {
//                cMsg.setMessageInfo(NMAL2610Constant.NMAM8442E);
//                return false;
//            }
//
//            for (int i = 0; i < idxComplementLogicTpList.size(); i++) {
//                ZYPEZDItemValueSetter.setValue(cMsg.C.no(idxComplementLogicTpList.get(i)).trtyRuleLogicTpCd_P1, trtyLogicTp);
//            }
//        }
//        // 2016/03/30 CSA-QC#6255 Add End
//
//        // NMZC2700 Territory Rule Validation API
//        NMZC270001 nmzc270001 = new NMZC270001();
//        NMZC270001PMsg paramMsg = new NMZC270001PMsg();
//
//        paramMsg.glblCmpyCd.setValue(glblCompanyCd);
//        paramMsg.orgCd.setValue(cMsg.orgCd_H1.getValue());
//        paramMsg.firstOrgCd.setValue(cMsg.bizAreaOrgCd_P1.getValue());
//        paramMsg.trtyTpCd.setValue(cMsg.trtyTpCd_P1.getValue());
//        paramMsg.orgTierCd.setValue(cMsg.orgTierCd_P1.getValue());
//        paramMsg.orgStruTpCd.setValue(ORG_STRU_TP.TERRITORY_STRUCTURE);
//        paramMsg.trtyGrpTpCd.setValue(cMsg.trtyGrpTpCd_P1.getValue());
//        paramMsg.effFromDt_T.setValue(cMsg.effFromDt_H1.getValue());
//
//        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1)) {
//            paramMsg.effThruDt_T.setValue(cMsg.effThruDt_H1.getValue());
//        }
//
//        paramMsg.slsDt.setValue(ZYPDateUtil.getSalesDate());
//
//        paramMsg.trtyRuleList.setValidCount(0);
//        
//        // QC#7966 BEG
//        int i = 0;
////        for (; i < cMsg.C.getValidCount(); i++) {
////            //idx = idxActiveRuleList.get(i);
////
////            paramMsg.trtyRuleList.no(i).trtyRuleTpCd.setValue(cMsg.C.no(i).trtyRuleTpCd_P1.getValue());
////            paramMsg.trtyRuleList.no(i).trtyRuleOprdTpCd.setValue(cMsg.C.no(i).trtyRuleOprdTpCd_P1.getValue());
////            paramMsg.trtyRuleList.no(i).trtyRuleFromValTxt.setValue(cMsg.C.no(i).trtyRuleFromValTxt_C1.getValue());
////
////            if (ZYPCommonFunc.hasValue(cMsg.C.no(i).trtyRuleThruValTxt_C1)) {
////                paramMsg.trtyRuleList.no(i).trtyRuleThruValTxt.setValue(cMsg.C.no(i).trtyRuleThruValTxt_C1.getValue());
////            }
////
////            if (ZYPCommonFunc.hasValue(cMsg.C.no(i).trtyRuleLogicTpCd_P1)) {
////                paramMsg.trtyRuleList.no(i).trtyRuleLogicTpCd.setValue(cMsg.C.no(i).trtyRuleLogicTpCd_P1.getValue());
////            }
////
////            paramMsg.trtyRuleList.no(i).effFromDt_R.setValue(cMsg.C.no(i).effFromDt_C1.getValue());
////
////            if (ZYPCommonFunc.hasValue(cMsg.C.no(i).effThruDt_C1)) {
////                paramMsg.trtyRuleList.no(i).effThruDt_R.setValue(cMsg.C.no(i).effThruDt_C1.getValue());
////            }
////        }
//        S21SsmEZDResult result = NMAL2610Query.getInstance().getTerritoryRulesWithoutFilter(cMsg);
//        List<Integer> listDeleteIndex = new ArrayList<Integer>();
//        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
//        if (result.isCodeNormal()) {
//            listMap = (List<Map<String, Object>>) result.getResultObject();
//            for (int r = 0; r < listMap.size(); r++) {
//                Map<String, Object> map = (Map<String, Object>) listMap.get(r);
//
//                // rows update from screen
//                BigDecimal trtyRulePk = (BigDecimal) map.get("TRTY_RULE_PK_RULE");
//                for (int c = 0; c < cMsg.C.getValidCount(); c++) {
//                    NMAL2610_CCMsg ccMsg = cMsg.C.no(c);
//                    if (ZYPCommonFunc.hasValue(ccMsg.trtyRulePk_X3) && ccMsg.trtyRulePk_X3.getValue().compareTo(trtyRulePk) == 0) {
//                        map.put("TRTY_RULE_TP_CD", ccMsg.trtyRuleTpCd_P1.getValue());
//                        map.put("TRTY_RULE_OPRD_TP_CD", ccMsg.trtyRuleOprdTpCd_P1.getValue());
//                        map.put("TRTY_RULE_FROM_VAL_TXT", ccMsg.trtyRuleFromValTxt_C1.getValue());
//                        map.put("TRTY_RULE_THRU_VAL_TXT", ccMsg.trtyRuleThruValTxt_C1.getValue());
//                        map.put("TRTY_RULE_LOGIC_TP_CD", ccMsg.trtyRuleLogicTpCd_P1.getValue());
//                        map.put("EFF_FROM_DT", ccMsg.effFromDt_C1.getValue());
//                        map.put("EFF_THRU_DT", ccMsg.effThruDt_C1.getValue());
//                        // QC#11728
//                        map.put("XX_ROW_NUM_IN_SCRN", c);
//                        break;
//                    }
//                }
//                // rows delete from screen
//                for (int c = 0; c < sMsg.Y.getValidCount(); c++) {
//                    NMAL2610_YSMsg ysMsg = sMsg.Y.no(c);
//                    if (ZYPCommonFunc.hasValue(ysMsg.trtyRulePk_R2) && ysMsg.trtyRulePk_R2.getValue().compareTo(trtyRulePk) == 0) {
//                        listDeleteIndex.add(r);
//                        break;
//                    }
//                }
//            }
//        }
//        // rows delete from screen
//        if(listDeleteIndex.size() > 0){
//            List<Map<String, Object>> listMapAfterDelete = new ArrayList<Map<String, Object>>();
//            for (int r = 0; r < listMap.size(); r++) {
//                Map<String, Object> map = (Map<String, Object>) listMap.get(r);
//                if(listDeleteIndex.indexOf(r) < 0){
//                    listMapAfterDelete.add(map);
//                }
//            }
//            listMap = listMapAfterDelete;
//        }
//        // rows add from screen
//        for (int c = 0; c < cMsg.C.getValidCount(); c++) {
//            NMAL2610_CCMsg ccMsg = cMsg.C.no(c);
//            if (!ZYPCommonFunc.hasValue(ccMsg.trtyRulePk_X3)) {
//                Map<String, Object> map = new HashMap<String, Object>();
//                map.put("TRTY_RULE_TP_CD", ccMsg.trtyRuleTpCd_P1.getValue());
//                map.put("TRTY_RULE_OPRD_TP_CD", ccMsg.trtyRuleOprdTpCd_P1.getValue());
//                map.put("TRTY_RULE_FROM_VAL_TXT", ccMsg.trtyRuleFromValTxt_C1.getValue());
//                map.put("TRTY_RULE_THRU_VAL_TXT", ccMsg.trtyRuleThruValTxt_C1.getValue());
//                map.put("TRTY_RULE_LOGIC_TP_CD", ccMsg.trtyRuleLogicTpCd_P1.getValue());
//                map.put("EFF_FROM_DT", ccMsg.effFromDt_C1.getValue());
//                map.put("EFF_THRU_DT", ccMsg.effThruDt_C1.getValue());
//                // QC#11728
//                map.put("XX_ROW_NUM_IN_SCRN", c);
//                listMap.add(map);
//            }
//        }
//        // set search result to API parameter
//        for (int r = 0; r < listMap.size(); r++) {
//            if (r >= paramMsg.trtyRuleList.length()) {
//                break;
//            }
//            Map<String, Object> map = (Map<String, Object>) listMap.get(r);
//            paramMsg.trtyRuleList.no(i).trtyRuleTpCd.setValue((String) map.get("TRTY_RULE_TP_CD"));
//            paramMsg.trtyRuleList.no(i).trtyRuleOprdTpCd.setValue((String) map.get("TRTY_RULE_OPRD_TP_CD"));
//            paramMsg.trtyRuleList.no(i).trtyRuleFromValTxt.setValue((String) map.get("TRTY_RULE_FROM_VAL_TXT"));
//            if (ZYPCommonFunc.hasValue((String) map.get("TRTY_RULE_THRU_VAL_TXT"))) {
//                paramMsg.trtyRuleList.no(i).trtyRuleThruValTxt.setValue((String) map.get("TRTY_RULE_THRU_VAL_TXT"));
//            }
//            if (ZYPCommonFunc.hasValue((String) map.get("TRTY_RULE_LOGIC_TP_CD"))) {
//                paramMsg.trtyRuleList.no(i).trtyRuleLogicTpCd.setValue((String) map.get("TRTY_RULE_LOGIC_TP_CD"));
//            }
//            paramMsg.trtyRuleList.no(i).effFromDt_R.setValue((String) map.get("EFF_FROM_DT"));
//
//            if (ZYPCommonFunc.hasValue((String) map.get("EFF_THRU_DT"))) {
//                paramMsg.trtyRuleList.no(i).effThruDt_R.setValue((String) map.get("EFF_THRU_DT"));
//            }
//            i++;
//        }
//        // QC#7966 END
//
//        paramMsg.trtyRuleList.setValidCount(i);
//        nmzc270001.execute(paramMsg, ONBATCH_TYPE.ONLINE);
//        
//        // QC#11728
//        for(int r=0; r<paramMsg.trtyRuleList.getValidCount(); r++){
//            Map<String, Object> map = (Map<String, Object>) listMap.get(r);
//            if(map.containsKey("XX_ROW_NUM_IN_SCRN")){
//                int rowNumInScrn = (Integer)map.get("XX_ROW_NUM_IN_SCRN");
//                NMZC270001_trtyRuleListPMsg subPMsg = paramMsg.trtyRuleList.no(r);
//                if(ZYPCommonFunc.hasValue(subPMsg.xxMsgId_R.getValue())){
//                    String errItemNm = subPMsg.xxTblItemTxt_R.getValue();
//                    String messageCode = subPMsg.xxMsgId_R.getValue();
//                    String messageArg = subPMsg.xxMsgPrmTxt_R1.getValue();
//
//                    String[] messageArgs = null;
//                    if (ZYPCommonFunc.hasValue(messageArg)) {
//                        messageArgs = new String[] {messageArg };
//                    }
//
//                    int errorCode = 1;
//                    if (messageCode.endsWith("W")) {
//                        errorCode = 2;
//                    }
//
//                    EZDCItem errItem = cMsg.C.no(rowNumInScrn).trtyRuleFromValTxt_C1;
//                    if (ZYPCommonFunc.hasValue(errItemNm)) {
//                        Map<String, EZDCItem> mapItem = new HashMap<String, EZDCItem>();
//                        mapItem.put(NMAL2610Constant.TRTY_RULE_TP_CD, cMsg.C.no(rowNumInScrn).trtyRuleTpCd_P1);
//                        mapItem.put(NMAL2610Constant.TRTY_RULE_OPRD_TP_CD, cMsg.C.no(rowNumInScrn).trtyRuleOprdTpCd_P1);
//                        mapItem.put(NMAL2610Constant.TRTY_RULE_FROM_VAL_TXT, cMsg.C.no(rowNumInScrn).trtyRuleFromValTxt_C1);
//                        mapItem.put(NMAL2610Constant.TRTY_RULE_THRU_VAL_TXT, cMsg.C.no(rowNumInScrn).trtyRuleThruValTxt_C1);
//                        mapItem.put(NMAL2610Constant.EFF_FROM_DT, cMsg.C.no(rowNumInScrn).effFromDt_C1);
//                        mapItem.put(NMAL2610Constant.EFF_THRU_DT, cMsg.C.no(rowNumInScrn).effThruDt_C1);
//                        mapItem.put(NMAL2610Constant.TRTY_RULE_LOGIC_TP_CD, cMsg.C.no(rowNumInScrn).trtyRuleLogicTpCd_P1);
//                        if (mapItem.containsKey(errItemNm)) {
//                            errItem = mapItem.get(errItemNm);
//                        }
//                    }
//
//                    errItem.setErrorInfo(errorCode, messageCode, messageArgs);
//                }
//            }
//        }
//
//        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
//        for (int j = 0; j < msgList.size(); j++) {
//            S21ApiMessage msg = msgList.get(j);
//            String msgId = msg.getXxMsgid();
//            String[] msgPrms = msg.getXxMsgPrmArray();
//            if (msgPrms != null && msgPrms.length > 0) {
//                cMsg.setMessageInfo(msgId, msgPrms);
//                cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
//                return false;
//            }
//        }
//
//        List<String> msgIdList = S21ApiUtil.getXxMsgIdList(paramMsg);
//        if (msgIdList != null && msgIdList.size() > 0) {
//            for (int j = 0; j < msgIdList.size(); j++) {
//                cMsg.setMessageInfo((String) msgIdList.get(0));
//                cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
//                return false;
//            }
//        }
//
//        return true;
//    }
    public static boolean checkInputRulesForSubmit(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg, String glblCompanyCd) {
        int maxDisplayRows = cMsg.C.length();
        int maxIndex = sMsg.C.getValidCount();

        if (maxIndex == 0) {
            return true;
        }

        boolean isSuccess = true;
        String trtyLogicTp = null;
        List<Integer> idxActiveRuleList = new ArrayList<Integer>();
        List<Integer> idxComplementLogicTpList = new ArrayList<Integer>();

        for (int i = 0; i < maxIndex; i++) {

            if (!checkDiffLine(cMsg, sMsg, i)) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(sMsg.effThruDt_H1.getValue())) {
                if (sMsg.effThruDt_H1.getValue().compareTo(sMsg.C.no(i).effFromDt_C1.getValue()) < 0) {
                    sMsg.C.no(i).effFromDt_C1.setErrorInfo(1, NMAL2610Constant.NMAM0043E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY_RULE + NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT, NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY + NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT });
                    if(isSuccess == true){
                        int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                        cMsg.xxPageShowFromNum_C1.setValue(errScrnInex);
                        isSuccess = false;
                    }
                }

                if (ZYPCommonFunc.hasValue(sMsg.C.no(i).effThruDt_C1.getValue())) {
                        if (sMsg.effThruDt_H1.getValue().compareTo(sMsg.C.no(i).effThruDt_C1.getValue()) < 0) {
                            sMsg.C.no(i).effThruDt_C1.setErrorInfo(1, NMAL2610Constant.NMAM0043E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY_RULE + NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY + NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT });
                            if(isSuccess == true){
                                int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                                cMsg.xxPageShowFromNum_C1.setValue(errScrnInex);
                                isSuccess = false;
                            }
                        }
                }
            }

            if (ZYPCommonFunc.hasValue(sMsg.C.no(i).effThruDt_C1.getValue())) {
                if (sMsg.C.no(i).effFromDt_C1.getValue().compareTo(sMsg.C.no(i).effThruDt_C1.getValue()) > 0) {
                    sMsg.C.no(i).effFromDt_C1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
                    sMsg.C.no(i).effThruDt_C1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
                    if(isSuccess == true){
                        int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                        cMsg.xxPageShowFromNum_C1.setValue(errScrnInex);
                        isSuccess = false;
                    }
                }
            }

            if (checkActiveDate(sMsg.C.no(i).effFromDt_C1.getValue(), sMsg.C.no(i).effThruDt_C1.getValue(), ZYPDateUtil.getSalesDate())) {
                idxActiveRuleList.add(i);
                if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).trtyRuleLogicTpCd_P1)) {
                    idxComplementLogicTpList.add(i);
                } else {
                    if (!ZYPCommonFunc.hasValue(trtyLogicTp)) {
                        trtyLogicTp = sMsg.C.no(i).trtyRuleLogicTpCd_P1.getValue();
                    }
                }
            } else {
                continue;
            }
        }

        if (!isSuccess) {
            cMsg.setMessageInfo(NMAL2610Constant.ZZM9037E);
            NMAL2610CommonLogic.loadOnePageToCMsg(cMsg, cMsg.C, sMsg.C);
            return false;
        }

        if (idxActiveRuleList.size() > 1) {
            if (idxActiveRuleList.size() == idxComplementLogicTpList.size()) {
                cMsg.setMessageInfo(NMAL2610Constant.NMAM8442E);
                return false;
            }

            for (int i = 0; i < idxComplementLogicTpList.size(); i++) {
                ZYPEZDItemValueSetter.setValue(cMsg.C.no(idxComplementLogicTpList.get(i)).trtyRuleLogicTpCd_P1, trtyLogicTp);
            }
        }

        // 2017/11/16 CSA-QC#20597 Add Start
        if (checkWarningTrtyRule(cMsg, sMsg)) {
            cMsg.xxWrnSkipFlg.setValue(ZYPConstant.FLG_OFF_N);
            return true;
        }
        // 2017/11/16 CSA-QC#20597 Add End
        // NMZC2700 Territory Rule Validation API
        NMZC270001 nmzc270001 = new NMZC270001();
        NMZC270001PMsg paramMsg = new NMZC270001PMsg();

        paramMsg.glblCmpyCd.setValue(glblCompanyCd);
        paramMsg.orgCd.setValue(cMsg.orgCd_H1.getValue());
        paramMsg.firstOrgCd.setValue(cMsg.bizAreaOrgCd_P1.getValue());
        paramMsg.trtyTpCd.setValue(cMsg.trtyTpCd_P1.getValue());
        paramMsg.orgTierCd.setValue(cMsg.orgTierCd_P1.getValue());
        paramMsg.orgStruTpCd.setValue(ORG_STRU_TP.TERRITORY_STRUCTURE);
        paramMsg.trtyGrpTpCd.setValue(cMsg.trtyGrpTpCd_P1.getValue());
        paramMsg.effFromDt_T.setValue(cMsg.effFromDt_H1.getValue());

        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1)) {
            paramMsg.effThruDt_T.setValue(cMsg.effThruDt_H1.getValue());
        }

        paramMsg.slsDt.setValue(ZYPDateUtil.getSalesDate());

        paramMsg.trtyRuleList.setValidCount(0);
        
        int i = 0;

        S21SsmEZDResult result = NMAL2610Query.getInstance().getTerritoryRulesWithoutFilter(cMsg);
        List<Integer> listDeleteIndex = new ArrayList<Integer>();
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        if (result.isCodeNormal()) {
            listMap = (List<Map<String, Object>>) result.getResultObject();
            for (int r = 0; r < listMap.size(); r++) {
                Map<String, Object> map = (Map<String, Object>) listMap.get(r);

                // rows update from screen
                BigDecimal trtyRulePk = (BigDecimal) map.get("TRTY_RULE_PK_RULE");
                // Delete Start 2019/12/27 QC#54222-1
//                for (int c = 0; c < cMsg.C.getValidCount(); c++) {
                // Delete End   2019/12/27 QC#54222-1
                // Add Start 2019/12/27 QC#54222-1
                for (int c = 0; c < sMsg.C.getValidCount(); c++) {
                // Add End   2019/12/27 QC#54222-1
                    NMAL2610_CSMsg csMsg = sMsg.C.no(c);
                    if (ZYPCommonFunc.hasValue(csMsg.trtyRulePk_X3) && csMsg.trtyRulePk_X3.getValue().compareTo(trtyRulePk) == 0) {
                        map.put("TRTY_RULE_TP_CD", csMsg.trtyRuleTpCd_P1.getValue());
                        map.put("TRTY_RULE_OPRD_TP_CD", csMsg.trtyRuleOprdTpCd_P1.getValue());
                        map.put("TRTY_RULE_FROM_VAL_TXT", csMsg.trtyRuleFromValTxt_C1.getValue());
                        map.put("TRTY_RULE_THRU_VAL_TXT", csMsg.trtyRuleThruValTxt_C1.getValue());
                        map.put("TRTY_RULE_LOGIC_TP_CD", csMsg.trtyRuleLogicTpCd_P1.getValue());
                        map.put("EFF_FROM_DT", csMsg.effFromDt_C1.getValue());
                        map.put("EFF_THRU_DT", csMsg.effThruDt_C1.getValue());
                        // QC#11728
                        map.put("XX_ROW_NUM_IN_SCRN", c);
                        break;
                    }
                }
                // rows delete from screen
                // Mod Start 2019/12/27 QC#54222-1
//                for (int c = 0; c < sMsg.Y.getValidCount(); c++) {
//                    NMAL2610_YSMsg ysMsg = sMsg.Y.no(c);
//                    if (ZYPCommonFunc.hasValue(ysMsg.trtyRulePk_R2) && ysMsg.trtyRulePk_R2.getValue().compareTo(trtyRulePk) == 0) {
                for (int c = 0; c < cMsg.Y.getValidCount(); c++) {
                    NMAL2610_YCMsg ycMsg = cMsg.Y.no(c);
                    if (ZYPCommonFunc.hasValue(ycMsg.trtyRulePk_R2) && ycMsg.trtyRulePk_R2.getValue().compareTo(trtyRulePk) == 0) {
                // Mod End   2019/12/27 QC#54222-1
                        listDeleteIndex.add(r);
                        break;
                    }
                }
            }
        }
        // rows delete from screen
        if(listDeleteIndex.size() > 0){
            List<Map<String, Object>> listMapAfterDelete = new ArrayList<Map<String, Object>>();
            for (int r = 0; r < listMap.size(); r++) {
                Map<String, Object> map = (Map<String, Object>) listMap.get(r);
                if(listDeleteIndex.indexOf(r) < 0){
                    listMapAfterDelete.add(map);
                }
            }
            listMap = listMapAfterDelete;
        }
        // rows add from screen
        for (int c = 0; c < maxIndex; c++) {
            NMAL2610_CSMsg csMsg = sMsg.C.no(c);
            if (!ZYPCommonFunc.hasValue(csMsg.trtyRulePk_X3)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("TRTY_RULE_TP_CD", csMsg.trtyRuleTpCd_P1.getValue());
                map.put("TRTY_RULE_OPRD_TP_CD", csMsg.trtyRuleOprdTpCd_P1.getValue());
                map.put("TRTY_RULE_FROM_VAL_TXT", csMsg.trtyRuleFromValTxt_C1.getValue());
                map.put("TRTY_RULE_THRU_VAL_TXT", csMsg.trtyRuleThruValTxt_C1.getValue());
                map.put("TRTY_RULE_LOGIC_TP_CD", csMsg.trtyRuleLogicTpCd_P1.getValue());
                map.put("EFF_FROM_DT", csMsg.effFromDt_C1.getValue());
                map.put("EFF_THRU_DT", csMsg.effThruDt_C1.getValue());
                // QC#11728
                map.put("XX_ROW_NUM_IN_SCRN", c);
                listMap.add(map);
            }
        }
        // 2018/08/03 QC#26942 Add Start
        Map<Integer, Integer> mapRowNum = new HashMap<Integer, Integer>();
        // 2018/08/03 QC#26942 Add End
        // set search result to API parameter
        int skipCnt = 0;
        for (int r = 0; r < listMap.size(); r++) {
            // 2020/03/13 QC#56217 Mod Start
//            if (r >= paramMsg.trtyRuleList.length()) {
            if (i >= paramMsg.trtyRuleList.length()) {
                break;
            }
            // 2020/03/13 QC#56217 Mod End
            Map<String, Object> map = (Map<String, Object>) listMap.get(r);
            // 2018/08/03 QC#26942 Add Start
            String effThruDt = (String) map.get("EFF_THRU_DT");
            if (!ZYPCommonFunc.hasValue(effThruDt) || (EZDCommonFunc.cmpDate(effThruDt, ZYPDateUtil.getSalesDate()) > -1)){
                mapRowNum.put(i, r);
            // 2018/08/03 QC#26942 Add End
                paramMsg.trtyRuleList.no(i).trtyRuleTpCd.setValue((String) map.get("TRTY_RULE_TP_CD"));
                paramMsg.trtyRuleList.no(i).trtyRuleOprdTpCd.setValue((String) map.get("TRTY_RULE_OPRD_TP_CD"));
                paramMsg.trtyRuleList.no(i).trtyRuleFromValTxt.setValue((String) map.get("TRTY_RULE_FROM_VAL_TXT"));
                if (ZYPCommonFunc.hasValue((String) map.get("TRTY_RULE_THRU_VAL_TXT"))) {
                    paramMsg.trtyRuleList.no(i).trtyRuleThruValTxt.setValue((String) map.get("TRTY_RULE_THRU_VAL_TXT"));
                }
                if (ZYPCommonFunc.hasValue((String) map.get("TRTY_RULE_LOGIC_TP_CD"))) {
                    paramMsg.trtyRuleList.no(i).trtyRuleLogicTpCd.setValue((String) map.get("TRTY_RULE_LOGIC_TP_CD"));
                }
                paramMsg.trtyRuleList.no(i).effFromDt_R.setValue((String) map.get("EFF_FROM_DT"));

                if (ZYPCommonFunc.hasValue((String) map.get("EFF_THRU_DT"))) {
                    paramMsg.trtyRuleList.no(i).effThruDt_R.setValue((String) map.get("EFF_THRU_DT"));
                }
                i++;
            // 2018/08/03 QC#26942 Add Start
            } else {
                skipCnt++;
            }
            // 2018/08/03 QC#26942 Add End
        }

        paramMsg.trtyRuleList.setValidCount(i);
        // 2020/05/08 QC#56810 Add Start
        if (i < 1) {
            return true;
        }
        // 2020/05/08 QC#56810 Add End
        nmzc270001.execute(paramMsg, ONBATCH_TYPE.ONLINE);
        // Add Start 2019/12/27 QC#54222-1
        String duplicateTeritorryRule = null;
        String duplicateTeritorry = null;
        int duplicateRow = -1;
        // Add End   2019/12/27 QC#54222-1

        for(int r=0; r<paramMsg.trtyRuleList.getValidCount(); r++){
            // 2018/08/03 QC#26942 Mod Start
            //Map<String, Object> map = (Map<String, Object>) listMap.get(r);
            Map<String, Object> map = (Map<String, Object>) listMap.get(mapRowNum.get(r));
            // 2018/08/03 QC#26942 Mod End
            // Add Start 2019/12/27 QC#54222-1
            if (!ZYPCommonFunc.hasValue(duplicateTeritorry) && paramMsg.trtyRuleList.no(r).xxMsgId_R.getValue().equals(NMAL2610Constant.NMZM0360W)) {
                duplicateTeritorry = paramMsg.trtyRuleList.no(r).xxMsgPrmTxt_R1.getValue();
                duplicateTeritorryRule = paramMsg.trtyRuleList.no(r).trtyRuleFromValTxt.getValue();
            }
            // Add End   2019/12/27 QC#54222-1
            // QC#56925 Mod Start
            int rowNumInScrn = r;
            NMZC270001_trtyRuleListPMsg subPMsg = paramMsg.trtyRuleList.no(r);
            boolean isErr = ZYPCommonFunc.hasValue(subPMsg.xxMsgId_R);
            boolean isScrn = true;
            if (map.containsKey("XX_ROW_NUM_IN_SCRN")) {
                rowNumInScrn = (Integer) map.get("XX_ROW_NUM_IN_SCRN");
            } else if (isErr){
                rowNumInScrn += skipCnt;
                isScrn = false;
            }
            if (isErr) {
                String errItemNm = subPMsg.xxTblItemTxt_R.getValue();
                String messageCode = subPMsg.xxMsgId_R.getValue();
                String messageArg = subPMsg.xxMsgPrmTxt_R1.getValue();

                String[] messageArgs = null;
                if (ZYPCommonFunc.hasValue(messageArg)) {
                    messageArgs = new String[] {messageArg };
                }

                int errorCode = 1;
                if (messageCode.endsWith("W")) {
                    errorCode = 2;
                    // 2017/11/16 CSA-QC#20597 Add Start
                    cMsg.xxWrnSkipFlg.setValue(ZYPConstant.FLG_ON_Y);
                    // 2017/11/16 CSA-QC#20597 Add End
                    // Add Start 2019/12/27 QC#54222-1
                    if ((duplicateRow < 0 || rowNumInScrn < duplicateRow) && paramMsg.trtyRuleList.no(r).xxMsgId_R.getValue().equals(NMAL2610Constant.NMZM0360W)) {
                        duplicateRow = rowNumInScrn;
                        duplicateTeritorry = paramMsg.trtyRuleList.no(r).xxMsgPrmTxt_R1.getValue();
                        duplicateTeritorryRule = paramMsg.trtyRuleList.no(r).trtyRuleFromValTxt.getValue();
                    }
                    // Add End 2019/12/27 QC#54222-1
                }

                if (isScrn) {
                    EZDSItem errItem = sMsg.C.no(rowNumInScrn).trtyRuleFromValTxt_C1;
                    if (ZYPCommonFunc.hasValue(errItemNm)) {
                        Map<String, EZDSItem> mapItem = new HashMap<String, EZDSItem>();
                        mapItem.put(NMAL2610Constant.TRTY_RULE_TP_CD, sMsg.C.no(rowNumInScrn).trtyRuleTpCd_P1);
                        mapItem.put(NMAL2610Constant.TRTY_RULE_OPRD_TP_CD, sMsg.C.no(rowNumInScrn).trtyRuleOprdTpCd_P1);
                        mapItem.put(NMAL2610Constant.TRTY_RULE_FROM_VAL_TXT, sMsg.C.no(rowNumInScrn).trtyRuleFromValTxt_C1);
                        mapItem.put(NMAL2610Constant.TRTY_RULE_THRU_VAL_TXT, sMsg.C.no(rowNumInScrn).trtyRuleThruValTxt_C1);
                        mapItem.put(NMAL2610Constant.EFF_FROM_DT, sMsg.C.no(rowNumInScrn).effFromDt_C1);
                        mapItem.put(NMAL2610Constant.EFF_THRU_DT, sMsg.C.no(rowNumInScrn).effThruDt_C1);
                        mapItem.put(NMAL2610Constant.TRTY_RULE_LOGIC_TP_CD, sMsg.C.no(rowNumInScrn).trtyRuleLogicTpCd_P1);
                        if (mapItem.containsKey(errItemNm)) {
                            errItem = mapItem.get(errItemNm);
                        }
                    }
                    errItem.setErrorInfo(errorCode, messageCode, messageArgs);
                }
                cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
            }
            // QC#56925 Mod End
        }
        // 2017/11/16 CSA-QC#20597 Add Start
        setWarningBackup(cMsg, sMsg);
        // 2017/11/16 CSA-QC#20597 Add End

        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
        for (int j = 0; j < msgList.size(); j++) {
            S21ApiMessage msg = msgList.get(j);
            String msgId = msg.getXxMsgid();
            // Mod Start 2019/12/27 QC#54222-1
            String[] msgPrms = new String[]{null};
            if (msgId.equals(NMAL2610Constant.NMZM0359W)) {
                msgPrms = new String[]{duplicateTeritorryRule, duplicateTeritorry};
            } else {
                msgPrms = msg.getXxMsgPrmArray();
            }
            // Mod End   2019/12/27 QC#54222-1
            if (msgPrms != null && msgPrms.length > 0) {
                // Add Start 2019/12/06 QC#53019
                setDisplayFirstErrorPage(cMsg, sMsg);
                // Add End   2019/12/06 QC#53019
                cMsg.setMessageInfo(msgId, msgPrms);
                cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
                return false;
            }
        }

        List<String> msgIdList = S21ApiUtil.getXxMsgIdList(paramMsg);
        if (msgIdList != null && msgIdList.size() > 0) {
            for (int j = 0; j < msgIdList.size(); j++) {
                // Add Start 2019/12/06 QC#53019
                setDisplayFirstErrorPage(cMsg, sMsg);
                // Add End   2019/12/06 QC#53019
                cMsg.setMessageInfo((String) msgIdList.get(0));
                cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
                return false;
            }
        }

        return true;
    }
    // 2017/06/23 QC#16677 Mod END

    // Add Start 2019/12/06 QC#54222
    /**
     * checkInputNewDispRulesForSubmit This method is for the
     * territory rule, that rows over 4000+. In most cases, this is
     * for the IS Territory rule.
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @param glblCompanyCd String
     * @return boolean
     */
    public static boolean checkInputNewDispRulesForSubmit(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg, String glblCompanyCd) {
        int maxDisplayRows = cMsg.C.length();
        int maxIndex = sMsg.C.getValidCount();
        String slsDt = ZYPDateUtil.getSalesDate();
        int i = 0;

        if (maxIndex == 0) {
            return true;
        }

        boolean isSuccess = true;
        String trtyLogicTp = null;
        List<Integer> idxActiveRuleList = new ArrayList<Integer>();
        List<Integer> idxComplementLogicTpList = new ArrayList<Integer>();

        for (i = 0; i < maxIndex; i++) {
            if (!checkDiffLine(cMsg, sMsg, i)) {
                continue;
            }

            // ----- Date valid check.
            if (ZYPCommonFunc.hasValue(sMsg.effThruDt_H1.getValue())) {
                if (sMsg.effThruDt_H1.getValue().compareTo(sMsg.C.no(i).effFromDt_C1.getValue()) < 0) {
                    sMsg.C.no(i).effFromDt_C1.setErrorInfo(1, NMAL2610Constant.NMAM0043E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY_RULE + NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT,
                            NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY + NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT });
                    if (isSuccess == true) {
                        int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                        cMsg.xxPageShowFromNum_C1.setValue(errScrnInex);
                        isSuccess = false;
                    }
                }
                if (ZYPCommonFunc.hasValue(sMsg.C.no(i).effThruDt_C1.getValue())) {
                    if (sMsg.effThruDt_H1.getValue().compareTo(sMsg.C.no(i).effThruDt_C1.getValue()) < 0) {
                        sMsg.C.no(i).effThruDt_C1.setErrorInfo(1, NMAL2610Constant.NMAM0043E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY_RULE + NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT,
                                NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY + NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT });
                        if (isSuccess == true) {
                            int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                            cMsg.xxPageShowFromNum_C1.setValue(errScrnInex);
                            isSuccess = false;
                        }
                    }
                    if (sMsg.C.no(i).effFromDt_C1.getValue().compareTo(sMsg.C.no(i).effThruDt_C1.getValue()) > 0) {
                        sMsg.C.no(i).effFromDt_C1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
                        sMsg.C.no(i).effThruDt_C1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
                        if (isSuccess == true) {
                            int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                            cMsg.xxPageShowFromNum_C1.setValue(errScrnInex);
                            isSuccess = false;
                        }
                    }
                }
            }

            // ----- Create "active rule list" (idxActiveRuleList).
            if (checkActiveDate(sMsg.C.no(i).effFromDt_C1.getValue(), sMsg.C.no(i).effThruDt_C1.getValue(), slsDt)) {
                idxActiveRuleList.add(i);
                if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).trtyRuleLogicTpCd_P1)) {
                    idxComplementLogicTpList.add(i);
                } else {
                    if (!ZYPCommonFunc.hasValue(trtyLogicTp)) {
                        trtyLogicTp = sMsg.C.no(i).trtyRuleLogicTpCd_P1.getValue();
                    }
                }
            } else {
                continue;
            }
        } // cMsg check loop End.

        if (!isSuccess) {
            cMsg.setMessageInfo(NMAL2610Constant.ZZM9037E);
            setDisplayFirstErrorPage(cMsg, sMsg);
            NMAL2610CommonLogic.loadOnePageToCMsg(cMsg, cMsg.C, sMsg.C);
            return false;
        }

        // ----- Active Rule and territory rule logic type check.
        if (idxActiveRuleList.size() > 1) {
            if (idxActiveRuleList.size() == idxComplementLogicTpList.size()) {
                cMsg.setMessageInfo(NMAL2610Constant.NMAM8442E);
                setDisplayFirstErrorPage(cMsg, sMsg);
                return false;
            }

            for (i = 0; i < idxComplementLogicTpList.size(); i++) {
                ZYPEZDItemValueSetter.setValue(cMsg.C.no(idxComplementLogicTpList.get(i)).trtyRuleLogicTpCd_P1, trtyLogicTp);
            }
        }

        if (checkWarningTrtyRule(cMsg, sMsg)) {
            cMsg.xxWrnSkipFlg.setValue(ZYPConstant.FLG_OFF_N);
            return true;
        }

        // ----- Get territory rule with no filter.
        S21SsmEZDResult result = NMAL2610Query.getInstance().getTerritoryRulesWithoutFilter(cMsg);
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> listDb = new ArrayList<Map<String, Object>>();

        // ----- Add to listMap from original data from DB.
        if (result.isCodeNormal()) {
            listDb = (List<Map<String, Object>>) result.getResultObject();
        }

        // ----- Add new data from screen.
        for (int c = 0; c < maxIndex; c++) {
            NMAL2610_CSMsg csMsg = sMsg.C.no(c);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("TRTY_RULE_TP_CD", csMsg.trtyRuleTpCd_P1.getValue());
            map.put("TRTY_RULE_OPRD_TP_CD", csMsg.trtyRuleOprdTpCd_P1.getValue());
            map.put("TRTY_RULE_FROM_VAL_TXT", csMsg.trtyRuleFromValTxt_C1.getValue());
            map.put("TRTY_RULE_THRU_VAL_TXT", csMsg.trtyRuleThruValTxt_C1.getValue());
            map.put("TRTY_RULE_LOGIC_TP_CD", csMsg.trtyRuleLogicTpCd_P1.getValue());
            map.put("EFF_FROM_DT", csMsg.effFromDt_C1.getValue());
            map.put("EFF_THRU_DT", csMsg.effThruDt_C1.getValue());
            map.put("XX_ROW_NUM_IN_SCRN", c);
            listMap.add(map);
        }
        // 2020/05/07 QC#56510 Del Start
//        if (maxIndex == 1) {
//            Map<String, Object> map = (Map<String, Object>) listDb.get(0);
//            map.put("TRTY_RULE_TP_CD", map.get("TRTY_RULE_TP_CD"));
//            map.put("TRTY_RULE_OPRD_TP_CD", map.get("TRTY_RULE_OPRD_TP_CD"));
//            map.put("TRTY_RULE_FROM_VAL_TXT", map.get("TRTY_RULE_FROM_VAL_TXT"));
//            map.put("TRTY_RULE_THRU_VAL_TXT", map.get("TRTY_RULE_THRU_VAL_TXT"));
//            map.put("TRTY_RULE_LOGIC_TP_CD", map.get("TRTY_RULE_LOGIC_TP_CD"));
//            map.put("EFF_FROM_DT", map.get("EFF_FROM_DT"));
//            map.put("EFF_THRU_DT", map.get("EFF_THRU_DT"));
//            listMap.add(map);
//        }
        // 2020/05/07 QC#56510 Del End
        NMZC270001 nmzc270001 = new NMZC270001();
        NMZC270001PMsg paramMsg = new NMZC270001PMsg();
        i = 0;
        paramMsg = new NMZC270001PMsg();
        for (int r = 0; r < listMap.size(); r++) {
            paramMsg.glblCmpyCd.setValue(glblCompanyCd);
            paramMsg.orgCd.setValue(cMsg.orgCd_H1.getValue());
            paramMsg.firstOrgCd.setValue(cMsg.bizAreaOrgCd_P1.getValue());
            paramMsg.trtyTpCd.setValue(cMsg.trtyTpCd_P1.getValue());
            paramMsg.orgTierCd.setValue(cMsg.orgTierCd_P1.getValue());
            paramMsg.orgStruTpCd.setValue(ORG_STRU_TP.TERRITORY_STRUCTURE);
            paramMsg.trtyGrpTpCd.setValue(cMsg.trtyGrpTpCd_P1.getValue());
            paramMsg.effFromDt_T.setValue(cMsg.effFromDt_H1.getValue());
            if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1)) {
                paramMsg.effThruDt_T.setValue(cMsg.effThruDt_H1.getValue());
            }
            paramMsg.slsDt.setValue(slsDt);
            paramMsg.trtyRuleList.setValidCount(0);

            Map<String, Object> map = (Map<String, Object>) listMap.get(r);

            // ----- Add a territory rule to the parameter list.
            String effThruDt = (String) map.get("EFF_THRU_DT");
            if (!ZYPCommonFunc.hasValue(effThruDt) || (EZDCommonFunc.cmpDate(effThruDt, ZYPDateUtil.getSalesDate()) > -1)) {
                paramMsg.trtyRuleList.no(i).trtyRuleTpCd.setValue((String) map.get("TRTY_RULE_TP_CD"));
                paramMsg.trtyRuleList.no(i).trtyRuleOprdTpCd.setValue((String) map.get("TRTY_RULE_OPRD_TP_CD"));
                paramMsg.trtyRuleList.no(i).trtyRuleFromValTxt.setValue((String) map.get("TRTY_RULE_FROM_VAL_TXT"));
                if (ZYPCommonFunc.hasValue((String) map.get("TRTY_RULE_THRU_VAL_TXT"))) {
                    paramMsg.trtyRuleList.no(i).trtyRuleThruValTxt.setValue((String) map.get("TRTY_RULE_THRU_VAL_TXT"));
                }
                if (ZYPCommonFunc.hasValue((String) map.get("TRTY_RULE_LOGIC_TP_CD"))) {
                    paramMsg.trtyRuleList.no(i).trtyRuleLogicTpCd.setValue((String) map.get("TRTY_RULE_LOGIC_TP_CD"));
                }
                paramMsg.trtyRuleList.no(i).effFromDt_R.setValue((String) map.get("EFF_FROM_DT"));

                if (ZYPCommonFunc.hasValue((String) map.get("EFF_THRU_DT"))) {
                    paramMsg.trtyRuleList.no(i).effThruDt_R.setValue((String) map.get("EFF_THRU_DT"));
                }
            // 2020/05/08 QC#56810 Mod Start
                i++;
            }
            // 2020/05/08 QC#56810 Mod End
        }

        // 2020/05/08 QC#56810 Mod Start
        // paramMsg.trtyRuleList.setValidCount(listMap.size());
        paramMsg.trtyRuleList.setValidCount(i);
        if (i < 1) {
            return true;
        }
        // 2020/05/08 QC#56810 Mod End
        nmzc270001.execute(paramMsg, ONBATCH_TYPE.ONLINE);
        if (isErrorTrtyRuleApiReturn(paramMsg, cMsg, sMsg, maxIndex)) {
            setDisplayFirstErrorPage(cMsg, sMsg);
            setWarningBackup(cMsg, sMsg);
            return false;
        }

        // ----- Check Duplicate.
        for (i = 0; i < maxIndex; i++) {
            NMAL2610_CSMsg csMsg = sMsg.C.no(i);

            for (int r = 0; r < listDb.size(); r++) {
                Map<String, Object> map = (Map<String, Object>) listDb.get(r);

                // Rule Type duplicate check.
                if (ZYPCommonFunc.hasValue((String) map.get("TRTY_RULE_TP_CD")) && !((String) map.get("TRTY_RULE_TP_CD")).equals(csMsg.trtyRuleTpCd_P1.getValue())) {
                    continue;
                }
                // Operator duplicate check.
                if (ZYPCommonFunc.hasValue((String) map.get("TRTY_RULE_OPRD_TP_CD")) && !((String) map.get("TRTY_RULE_OPRD_TP_CD")).equals(csMsg.trtyRuleOprdTpCd_P1.getValue())) {
                    continue;
                }
                // Value From duplicate check.
                if (ZYPCommonFunc.hasValue((String) map.get("TRTY_RULE_FROM_VAL_TXT")) && !((String) map.get("TRTY_RULE_FROM_VAL_TXT")).equals(csMsg.trtyRuleFromValTxt_C1.getValue())) {
                    continue;
                }
                // Value To duplicate check.
                if (ZYPCommonFunc.hasValue((String) map.get("TRTY_RULE_THRU_VAL_TXT")) && !((String) map.get("TRTY_RULE_THRU_VAL_TXT")).equals(csMsg.trtyRuleThruValTxt_C1.getValue())) {
                    continue;
                } else if (!ZYPCommonFunc.hasValue((String) map.get("TRTY_RULE_THRU_VAL_TXT")) && ZYPCommonFunc.hasValue(csMsg.trtyRuleThruValTxt_C1.getValue())) {
                    continue;
                }
                // Start Date, End Date duplicate check.
                if (isDuplicateDate((String) map.get("EFF_FROM_DT"), (String) map.get("TRTY_RULE_THRU_VAL_TXT"), csMsg.effFromDt_C1.getValue(), csMsg.effThruDt_C1.getValue())) {

                    sMsg.C.no(i).trtyRuleFromValTxt_C1.setErrorInfo(1, NMAL2610Constant.NMZM0088E);
                    cMsg.setMessageInfo(NMAL2610Constant.ZZM9037E);
                    setDisplayFirstErrorPage(cMsg, sMsg);
                    return false;
                }
            }
        }
        return true;
    }
    // Add End 2019/12/06 QC#54222
    // Add Start 2019/12/06 QC#54222
    /**
     * setDisplayFirstErrorPage
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @return None
     */
    public static void setDisplayFirstErrorPage(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        // Find first error position.
        int i = 0;
        for (; i < sMsg.C.getValidCount(); i++) {
            if (sMsg.C.no(i).trtyRuleFromValTxt_C1.getErrorCode() > 0) {
                int displayPageNum = i / cMsg.C.length() + 1;
                ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowCurNum_C1, BigDecimal.valueOf(displayPageNum));
                ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_C1, BigDecimal.valueOf((displayPageNum - 1) * cMsg.C.length()));
                return;
            }
        }
    }
    
    // Add Start 2019/12/06 QC#54222
    /**
     * isDuplicateDate
     * @param targetStartDate String
     * @param targetEndDate   String
     * @param checkStartDate  String
     * @param checkEndDate    String
     * @return boolean
     */
    public static boolean isDuplicateDate(String targetStartDate, String targetEndDate, String checkStartDate, String checkEndDate) {
        if (!ZYPCommonFunc.hasValue(targetEndDate)) {
            targetEndDate = NMAL2610Constant.DEFAULT_VALUE_FOR_END_DATE;
        }
        if (!ZYPCommonFunc.hasValue(checkEndDate)) {
            checkEndDate = NMAL2610Constant.DEFAULT_VALUE_FOR_END_DATE;
        }
        if (targetStartDate.compareTo(checkEndDate) > 0) {
            return false;
        }
        if (targetEndDate.compareTo(checkStartDate) < 0) {
            return false;
        }
        return true;
    }
        
     // Add End   2019/12/06 QC#54222
    
     // Add Start 2019/12/06 QC#54222
    /**
     * isErrorTrtyRuleApiReturn
     * This method checks the error message, what returned from NMZC270001.
     * And set error message to the screeen.
     * @param paramMsg NMZC270001PMsg
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @param maxIndex int
     * @return boolean
     */
    public static boolean isErrorTrtyRuleApiReturn(NMZC270001PMsg paramMsg, NMAL2610CMsg cMsg, NMAL2610SMsg sMsg, int maxIndex) {
        boolean returnCode = false;
        for (int r = 0; r < paramMsg.trtyRuleList.getValidCount(); r++) {
            if (maxIndex == 1 && r > 0) {
                break;
            }
            NMZC270001_trtyRuleListPMsg subPMsg = paramMsg.trtyRuleList.no(r);
            if (ZYPCommonFunc.hasValue(subPMsg.xxMsgId_R.getValue())) {
                String errItemNm = subPMsg.xxTblItemTxt_R.getValue();
                String messageCode = subPMsg.xxMsgId_R.getValue();
                String messageArg = subPMsg.xxMsgPrmTxt_R1.getValue();

                String[] messageArgs = null;
                if (ZYPCommonFunc.hasValue(messageArg)) {
                    messageArgs = new String[] {messageArg };
                }

                int errorCode = 1;
                if (messageCode.endsWith("W")) {
                    errorCode = 2;
                    cMsg.xxWrnSkipFlg.setValue(ZYPConstant.FLG_ON_Y);
                }

                EZDSItem errItem = sMsg.C.no(r).trtyRuleFromValTxt_C1;
                if (ZYPCommonFunc.hasValue(errItemNm)) {
                    Map<String, EZDSItem> mapItem = new HashMap<String, EZDSItem>();
                    mapItem.put(NMAL2610Constant.TRTY_RULE_TP_CD, sMsg.C.no(r).trtyRuleTpCd_P1);
                    mapItem.put(NMAL2610Constant.TRTY_RULE_OPRD_TP_CD, sMsg.C.no(r).trtyRuleOprdTpCd_P1);
                    mapItem.put(NMAL2610Constant.TRTY_RULE_FROM_VAL_TXT, sMsg.C.no(r).trtyRuleFromValTxt_C1);
                    mapItem.put(NMAL2610Constant.TRTY_RULE_THRU_VAL_TXT, sMsg.C.no(r).trtyRuleThruValTxt_C1);
                    mapItem.put(NMAL2610Constant.EFF_FROM_DT, sMsg.C.no(r).effFromDt_C1);
                    mapItem.put(NMAL2610Constant.EFF_THRU_DT, sMsg.C.no(r).effThruDt_C1);
                    mapItem.put(NMAL2610Constant.TRTY_RULE_LOGIC_TP_CD, sMsg.C.no(r).trtyRuleLogicTpCd_P1);
                    if (mapItem.containsKey(errItemNm)) {
                        errItem = mapItem.get(errItemNm);
                    }
                }
                errItem.setErrorInfo(errorCode, messageCode, messageArgs);
                returnCode = true;
            }
        }

        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
        for (int j = 0; j < msgList.size(); j++) {
            S21ApiMessage msg = msgList.get(j);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            if (msgPrms != null && msgPrms.length > 0) {
                cMsg.setMessageInfo(msgId, msgPrms);
                cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
                return true;
            }
        }

        List<String> msgIdList = S21ApiUtil.getXxMsgIdList(paramMsg);
        if (msgIdList != null && msgIdList.size() > 0) {
            for (int j = 0; j < msgIdList.size(); j++) {
                cMsg.setMessageInfo((String) msgIdList.get(0));
                cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
                return true;
            }
        }

        return returnCode;
    }

    // Add End 2019/12/06 QC#54222

    /**
     * checkInputResourceForSubmit
     * @param cMsg NMAL2610CMsg
     * @return boolean
     */
    public static boolean checkInputResourceForSubmit(NMAL2610CMsg cMsg) {

        int i = 0;
        String salesDate = ZYPDateUtil.getSalesDate();

        if (!isMultipleActiveResource(cMsg, salesDate)) {
            cMsg.setMessageInfo(NMAL2610Constant.NMAM8281E);
            return false;
        }

        boolean isTerritorySales = checkTerritoryBizArea(cMsg);
        List<Integer> idxList = new ArrayList<Integer>();

        for (; i < cMsg.D.getValidCount(); i++) {

            // check effective date with territory
            if (!compareDateWithTerritory(cMsg, i)) {
                return false;
            }

            // 2016/03/14 CSA-QC#5371 Add Start
            // check if resource is active
            if (checkActiveDate(cMsg.D.no(i).effFromDt_D1.getValue(), cMsg.D.no(i).effThruDt_D1.getValue(), salesDate)) {
                if (!checkActiveResource(cMsg, i)) {
                    cMsg.D.no(i).xxChkBox_D1.setErrorInfo(1, NMAL2610Constant.NMAM8396E);
                    cMsg.setMessageInfo(NMAL2610Constant.NMAM8396E);
                    return false;
                }
            }
            // 2016/03/14 CSA-QC#5371 Add Start

            // 2016/03/15 CSA-QC#5413 Add Start
            if (isTerritorySales) {
                if (!hasSalesRole(cMsg, i)) {
                    // 2016/03/03 CSA-QC#4545 Add Start
                    cMsg.D.no(i).xxChkBox_D1.setErrorInfo(1, NMAL2610Constant.NMAM8285E);
                    // 2016/03/03 CSA-QC#4545 Add End
                    cMsg.setMessageInfo(NMAL2610Constant.NMAM8285E);
                    return false;
                }

                // 2016/06/29 CSA-QC#11087 Add Start
                if (!ZYPCommonFunc.hasValue(cMsg.D.no(i).acctTeamRoleTpCd_P1) && !isSalesRep(cMsg, i)) {
                    cMsg.D.no(i).xxChkBox_D1.setErrorInfo(1, NMAL2610Constant.NMAM8282E);
                    cMsg.setMessageInfo(NMAL2610Constant.NMAM8282E);
                    return false;
                }
                // 2016/06/29 CSA-QC#11087 Add End
            }
            // 2016/03/15 CSA-QC#5413 Add End

            // 2016/04/05 CSA-QC#6003 Add Start
            if (!ZYPCommonFunc.hasValue(cMsg.D.no(i).effThruDt_D1)
                    || cMsg.D.no(i).effThruDt_D1.getValue().compareTo(ZYPDateUtil.getSalesDate()) >= 0) {
                if (!ZYPCommonFunc.hasValue(cMsg.D.no(i).acctTeamRoleTpCd_P1)) {
                    for (int idx = 0; idx < cMsg.D.getValidCount(); idx++) {
                        if (idx == i) {
                            continue;
                        }

                        if (!checkDuplicateTerm(cMsg.D.no(i).effFromDt_D1.getValue(), cMsg.D.no(i).effThruDt_D1.getValue(), cMsg.D.no(idx).effFromDt_D1.getValue(), cMsg.D.no(idx).effThruDt_D1.getValue())) {
                            idxList.add(i);
                            break;
                        }
                    }
                }
            }
            // 2016/04/05 CSA-QC#6003 Add End
        }

        // 2016/04/05 CSA-QC#6003 Add Start
        if (isSalesRepNeedForTrtyGrp(cMsg)) {
            if (idxList.size() > 1) {
                for (int idx = 0; idx < idxList.size(); idx++) {
                    cMsg.D.no(idxList.get(idx)).acctTeamRoleTpCd_P1.setErrorInfo(1, NMAL2610Constant.NMAM8423E);
                }
                cMsg.setMessageInfo(NMAL2610Constant.NMAM8285E);
                return false;
            }
        }
        // 2016/04/05 CSA-QC#6003 Add Start

        return true;
    }

    private static boolean isMultipleActiveResource(NMAL2610CMsg cMsg, String salesDate) {

        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
            for (int j = 0; j < cMsg.D.getValidCount(); j++) {
                if (cMsg.D.no(i).psnCd_D1.getValue().equals(cMsg.D.no(j).psnCd_D1.getValue())) {
                    if (i != j) {
                        // Mod Start 2017/10/17 QC#21753
                        //if (checkActiveDate(cMsg.D.no(i).effFromDt_D1.getValue(), cMsg.D.no(i).effThruDt_D1.getValue(), salesDate)) {
                        //    if (checkActiveDate(cMsg.D.no(j).effFromDt_D1.getValue(), cMsg.D.no(j).effThruDt_D1.getValue(), salesDate)) {
                        if (!checkDuplicateTerm(cMsg.D.no(i).effFromDt_D1.getValue(), cMsg.D.no(i).effThruDt_D1.getValue(), cMsg.D.no(j).effFromDt_D1.getValue(), cMsg.D.no(j).effThruDt_D1.getValue())) {
                            // Mod End 2017/10/17 QC#21753
                                // 2016/03/03 CSA-QC#4545 Add Start
                                cMsg.D.no(i).xxChkBox_D1.setErrorInfo(1, NMAL2610Constant.NMAM8281E);
                                cMsg.D.no(j).xxChkBox_D1.setErrorInfo(1, NMAL2610Constant.NMAM8281E);
                                // 2016/03/03 CSA-QC#4545 Add Start
                                return false;
                            // Del Start 2017/10/17 QC#21753
                            //}
                        // Del End 2017/10/17 QC#21753
                        }
                    }
                }
            }
        }

        return true;
    }

    private static boolean compareDateWithTerritory(NMAL2610CMsg cMsg, int i) {
        boolean isSuccess = true;

        // Start Date Check: should be greater than territory
        if (cMsg.effFromDt_H1.getValue().compareTo(cMsg.D.no(i).effFromDt_D1.getValue()) > 0) {
            // 2016/03/03 CSA-QC#4545 Add Start
            cMsg.D.no(i).effFromDt_D1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT, NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY + NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT});
            // 2016/03/03 CSA-QC#4545 Add End
            isSuccess = false;
        }

        // End Date Check: should be less than territory
        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1)) {
            if (ZYPCommonFunc.hasValue(cMsg.D.no(i).effThruDt_D1.getValue())) {
                if (cMsg.effThruDt_H1.getValue().compareTo(cMsg.D.no(i).effThruDt_D1.getValue()) < 0) {
                    // 2016/03/03 CSA-QC#4545 Add Start
                    cMsg.D.no(i).effThruDt_D1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY + NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT});
                    // 2016/03/03 CSA-QC#4545 Add End
                    isSuccess = false;
                }
            }
        }

        // End Date Check: should be greater than from date
        if (ZYPCommonFunc.hasValue(cMsg.D.no(i).effThruDt_D1.getValue())) {
            if (cMsg.D.no(i).effFromDt_D1.getValue().compareTo(cMsg.D.no(i).effThruDt_D1.getValue()) > 0) {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.D.no(i).effFromDt_D1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT});
                cMsg.D.no(i).effThruDt_D1.setErrorInfo(1, NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT});
                // 2016/03/03 CSA-QC#4545 Add End
                isSuccess = false;
            }
        }

        if (!isSuccess) {
            cMsg.setMessageInfo(NMAL2610Constant.ZZM9037E);
        }
        return isSuccess;
    }

    private static boolean hasSalesRole(NMAL2610CMsg cMsg, int i) {

        S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().checkActiveSalesRoll(cMsg, i);
        Integer resCnt = (Integer) ssmResult.getResultObject();
        if (resCnt == 0) {
            return false;
        }
        return true;
    }

    private static boolean isSalesRepNeedForTrtyGrp(NMAL2610CMsg cMsg) {
        S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().checkSalesRepFlgOfTrtyGrp(cMsg);
        Integer resCnt = (Integer) ssmResult.getResultObject();
        if (resCnt == 0) {
            return false;
        }
        return true;
    }

    private static boolean checkActiveResource(NMAL2610CMsg cMsg, int i) {

        S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().checkActiveResource(cMsg, i);
        Integer resCnt = (Integer) ssmResult.getResultObject();
        if (resCnt == 0) {
            return false;
        }
        return true;
    }

    private static boolean checkTerritoryBizArea(NMAL2610CMsg cMsg) {

        S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().checkTerritoryBizArea(cMsg);
        Integer resCnt = (Integer) ssmResult.getResultObject();
        if (resCnt == 0) {
            return false;
        }
        return true;
    }

    /**
     * checkChangedFieldsForDsOrgReln
     * @param cMsg NMAL2610CMsg
     * @return boolean
     */
    public static boolean checkChangedFieldsForDsOrgUnit(NMAL2610CMsg cMsg) {
        if (isNotEquals(cMsg.bizAreaOrgCd_HB.getValue(), cMsg.bizAreaOrgCd_P1.getValue())
                || isNotEquals(cMsg.trtyTpCd_HB.getValue(), cMsg.trtyTpCd_P1.getValue())
                || isNotEquals(cMsg.orgNm_HB.getValue(), cMsg.orgNm_H1.getValue())
                || isNotEquals(cMsg.orgTierCd_HB.getValue(), cMsg.orgTierCd_P1.getValue())
                || isNotEquals(cMsg.orgDescTxt_HB.getValue(), cMsg.orgDescTxt_H1.getValue())
                || isNotEquals(cMsg.trtyGrpTpCd_HB.getValue(), cMsg.trtyGrpTpCd_P1.getValue())
                || isNotEquals(cMsg.effFromDt_HB.getValue(), cMsg.effFromDt_H1.getValue())
                || isNotEquals(cMsg.effThruDt_HB.getValue(), cMsg.effThruDt_H1.getValue())) {
            return true;
        }
        return false;
    }

    // 2017/06/23 QC#16677 MOD START
    /**
     * checkChangedFieldsForTrtyRule
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @param i int
     * @return boolean
     */
    public static boolean checkChangedFieldsForTrtyRule(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg, int i) {
        if (isNotEquals(cMsg.orgCd_HB.getValue(), cMsg.orgCd_H1.getValue())
                || isNotEquals(sMsg.C.no(i).trtyRuleTpCd_CB.getValue(), sMsg.C.no(i).trtyRuleTpCd_P1.getValue())
                || isNotEquals(sMsg.C.no(i).trtyRuleFromValTxt_CB.getValue(), sMsg.C.no(i).trtyRuleFromValTxt_C1.getValue())
                || isNotEquals(sMsg.C.no(i).trtyRuleThruValTxt_CB.getValue(), sMsg.C.no(i).trtyRuleThruValTxt_C1.getValue())
                || isNotEquals(sMsg.C.no(i).effFromDt_CB.getValue(), sMsg.C.no(i).effFromDt_C1.getValue())
                || isNotEquals(sMsg.C.no(i).effThruDt_CB.getValue(), sMsg.C.no(i).effThruDt_C1.getValue())
                || isNotEquals(cMsg.orgTierCd_HB.getValue(), cMsg.orgTierCd_P1.getValue())
                || isNotEquals(sMsg.C.no(i).trtyRuleOprdTpCd_CB.getValue(), sMsg.C.no(i).trtyRuleOprdTpCd_P1.getValue())
                || isNotEquals(sMsg.C.no(i).trtyRuleLogicTpCd_CB.getValue(), sMsg.C.no(i).trtyRuleLogicTpCd_P1.getValue())) {
            return true;
        }
        return false;
    }
    // 2017/06/23 QC#16677 MOD E N D

    /**
     * checkChangedFieldsForRsrcAsgn
     * @param cMsg NMAL2610CMsg
     * @param i int
     * @return boolean
     */
    public static boolean checkChangedFieldsForRsrcAsgn(NMAL2610CMsg cMsg, int i) {
        if (isNotEquals(cMsg.D.no(i).effThruDt_DB.getValue(), cMsg.D.no(i).effThruDt_D1.getValue())
                || isNotEquals(cMsg.D.no(i).acctTeamRoleTpCd_DB.getValue(), cMsg.D.no(i).acctTeamRoleTpCd_P1.getValue())
                || isNotEquals(cMsg.D.no(i).psnCd_DB.getValue(), cMsg.D.no(i).psnCd_D1.getValue())
                || isNotEquals(cMsg.D.no(i).orgFuncRoleTpCd_DB.getValue(), cMsg.D.no(i).orgFuncRoleTpCd_X4.getValue())
                || isNotEquals(cMsg.D.no(i).effFromDt_DB.getValue(), cMsg.D.no(i).effFromDt_D1.getValue())) {
            return true;
        }
        return false;
    }
    /**
     * setBackUp
     * @param cMsg NMAL2610CMsg
     */
    public static void setBackUp(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        // QC#7966
        setBackUpHeader(cMsg);
        setBackUpParent(cMsg);
        setBackUpChild(cMsg);
        setBackUpRule(sMsg);
        setBackUpResource(cMsg);
    }

    // QC#7966
    public static void setBackUpHeader(NMAL2610CMsg cMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.orgCd_HB, cMsg.orgCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.bizAreaOrgCd_HB, cMsg.bizAreaOrgCd_P1);
        ZYPEZDItemValueSetter.setValue(cMsg.trtyTpCd_HB, cMsg.trtyTpCd_P1);
        ZYPEZDItemValueSetter.setValue(cMsg.orgNm_HB, cMsg.orgNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.orgTierCd_HB, cMsg.orgTierCd_P1);
        ZYPEZDItemValueSetter.setValue(cMsg.orgDescTxt_HB, cMsg.orgDescTxt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.trtyGrpTpCd_HB, cMsg.trtyGrpTpCd_P1);
        ZYPEZDItemValueSetter.setValue(cMsg.otherResrcTrtyFlg_HB, cMsg.otherResrcTrtyFlg_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.effFromDt_HB, cMsg.effFromDt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.effThruDt_HB, cMsg.effThruDt_H1);
    }

    public static void setBackUpParent(NMAL2610CMsg cMsg) {
        int i = 0;
        for (; i < cMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).orgNm_AB, cMsg.A.no(i).orgNm_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).orgCd_AB, cMsg.A.no(i).orgCd_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).effFromDt_AB, cMsg.A.no(i).effFromDt_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).effThruDt_AB, cMsg.A.no(i).effThruDt_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).gnrnTpCd_AB, cMsg.A.no(i).gnrnTpCd_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).orgTierCd_AB, cMsg.A.no(i).orgTierCd_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).bizAreaOrgCd_AB, cMsg.A.no(i).bizAreaOrgCd_A1);
        }
    }

    public static void setBackUpChild(NMAL2610CMsg cMsg) {
        int i = 0;
        for (; i < cMsg.B.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).orgNm_BB, cMsg.B.no(i).orgNm_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).orgCd_BB, cMsg.B.no(i).orgCd_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).effFromDt_BB, cMsg.B.no(i).effFromDt_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).effThruDt_BB, cMsg.B.no(i).effThruDt_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).orgTierCd_BB, cMsg.B.no(i).orgTierCd_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bizAreaOrgCd_BB, cMsg.B.no(i).bizAreaOrgCd_B1);
        }
    }

    public static void setBackUpRule(NMAL2610SMsg sMsg) {
        int i = 0;
        for (; i < sMsg.C.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).trtyRuleTpCd_CB, sMsg.C.no(i).trtyRuleTpCd_P1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).trtyRuleOprdTpCd_CB, sMsg.C.no(i).trtyRuleOprdTpCd_P1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).trtyRuleFromValTxt_CB, sMsg.C.no(i).trtyRuleFromValTxt_C1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).trtyRuleThruValTxt_CB, sMsg.C.no(i).trtyRuleThruValTxt_C1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).trtyRuleLogicTpCd_CB, sMsg.C.no(i).trtyRuleLogicTpCd_P1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).effFromDt_CB, sMsg.C.no(i).effFromDt_C1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).effThruDt_CB, sMsg.C.no(i).effThruDt_C1);
        }

    }

    public static void setBackUpResource(NMAL2610CMsg cMsg) {
        int i = 0;
        for (; i < cMsg.D.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).psnFirstNm_DB, cMsg.D.no(i).psnFirstNm_D1);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).psnLastNm_DB, cMsg.D.no(i).psnLastNm_D1);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).xxPsnNm_DB, cMsg.D.no(i).xxPsnNm_D1);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).psnCd_DB, cMsg.D.no(i).psnCd_D1);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).psnNum_DB, cMsg.D.no(i).psnNum_D1);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).acctTeamRoleTpCd_DB, cMsg.D.no(i).acctTeamRoleTpCd_P1);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).effFromDt_DB, cMsg.D.no(i).effFromDt_D1);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).effThruDt_DB, cMsg.D.no(i).effThruDt_D1);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).gnrnTpCd_DB, cMsg.D.no(i).gnrnTpCd_D1);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).orgFuncRoleTpCd_DB, cMsg.D.no(i).orgFuncRoleTpCd_X4);
        }
    }

    /**
     * isNotEquals
     * @param orig String
     * @param backUp String
     * @return boolean
     */
    public static boolean isNotEquals(String orig, String backUp) {
        if (!nvl(orig).equals(nvl(backUp))) {
            return true;
        }
        return false;
    }

    /**
     * isNotEquals
     * @param orig BigDecimal
     * @param backUp BigDecimal
     * @return boolean
     */
    public static boolean isNotEquals(BigDecimal orig, BigDecimal backUp) {
        if (orig == null) {
            if (backUp == null) {
                return false;
            } else {
                return true;
            }
        } else {
            if (backUp == null) {
                return true;
            }
        }
        if (orig.compareTo(backUp) != 0) {
            return true;
        }
        return false;
    }

    /**
     * nvl
     * @param val String
     * @return String
     */
    private static String nvl(String val) {
        if (!ZYPCommonFunc.hasValue(val)) {
            return "";
        }
        return val;
    }

    /**
     * checkActiveChildOrganization
     * @param cMsg Business Msg
     * @param i int
     * @return boolean
     */
    public static boolean checkActiveChildOrganization(NMAL2610CMsg cMsg, int i) {
        // 2016/03/10 CSA-QC#5221 Add Start
        S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().checkActiveChildOrganization(cMsg, i);
        Integer resCnt = (Integer) ssmResult.getResultObject();

        if (resCnt > 0) {
            return false;
        }
        return true;
        // 2016/03/10 CSA-QC#5221 Add Start
    }

    /**
     * checkResource
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @param showErr boolean
     * @param submit boolean
     * @return boolean
     */
    public static boolean checkResource(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg, boolean showErr, boolean submit) {

        boolean err = true;
        int i = 0;
        int cnt = cMsg.D.getValidCount();
        if (!submit) {
            i = cMsg.xxCellIdx.getValueInt();
            cnt = i + 1;
        }

        NMAL2610_DCMsg dCMsg;
        NMAL2610_DSMsg dSMsg;
        S21SsmEZDResult ssmResult;
        for (; i < cnt; i++) {
            dCMsg = cMsg.D.no(i);
            dSMsg = sMsg.D.no(i);

            // 2016/07/07 CSA-QC#11178 Add Start
            if (!isSalesRoleAsgResrc(cMsg, i)) {
                dCMsg.xxChkBox_D1.setErrorInfo(1, NMAL2610Constant.NMAM8623E);
                cMsg.setMessageInfo(NMAL2610Constant.NMAM8623E);
                return false;
            }
            // 2016/07/07 CSA-QC#11178 End Start

            ssmResult = NMAL2610Query.getInstance().getResourceAdditional(cMsg, sMsg, i);
            if (!ssmResult.isCodeNormal()) {
                err = false;
                if (showErr) {
                    cMsg.setMessageInfo(NMAL2610Constant.NMAM8334E, new String[] {(String) (NMAL2610Constant.NAME_FOR_MESSAGE_EMPLOYEE_ID + dCMsg.psnCd_D1.getValue()) });
                    // dCMsg.clear();
                    // dSMsg.clear();
                }
            } else {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

                Map<String, Object> map = (Map<String, Object>) resultList.get(0);

                String xxPsnNm = (String) map.get(NMAL2610Constant.XX_PSN_NM_DBCOLUMN);
                if (xxPsnNm != null) {
                    dCMsg.xxPsnNm_D1.setValue(xxPsnNm);
                }

                String orgFuncRoleTpCd = (String) map.get(NMAL2610Constant.ORG_FUNC_ROLE_TP_CD_DBCOLUMN);
                if (orgFuncRoleTpCd != null) {
                    dCMsg.orgFuncRoleTpCd_X4.setValue(orgFuncRoleTpCd);
                } else {
                    err = false;
                    if (showErr) {
                        cMsg.setMessageInfo(NMAL2610Constant.NMAM8334E, new String[] {(String) (NMAL2610Constant.NAME_FOR_MESSAGE_EMPLOYEE_ID + dCMsg.psnCd_D1.getValue()) });
                        dCMsg.clear();
                        dSMsg.clear();
                    }
                }

                String effFromDt = (String) map.get(NMAL2610Constant.EFF_FROM_DT_PSN_DBCOLUMN);
                if (effFromDt != null) {
                    dCMsg.effFromDt_D2.setValue(effFromDt);
                }

                String effThruDt = (String) map.get(NMAL2610Constant.EFF_THRU_DT_PSN_DBCOLUMN);
                if (effThruDt != null) {
                    dCMsg.effThruDt_D2.setValue(effThruDt);
                }
            }
        }
        return err;
    }

    // 2016/06/29 CSA-QC#11087 Add Start
    private static boolean isSalesRep(NMAL2610CMsg cMsg, int i) {
        S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().checkSalesRep(cMsg, i);
        Integer resCnt = (Integer) ssmResult.getResultObject();
        if (resCnt == 0) {
            return false;
        }
        return true;
    }
    // 2016/06/29 CSA-QC#11087 Add End

    // 2016/07/07 CSA-QC#11178 Add Start
    private static boolean isSalesRoleAsgResrc(NMAL2610CMsg cMsg, int i) {
        S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().checkSalesRoleAsgResrc(cMsg, i);
        Integer resCnt = (Integer) ssmResult.getResultObject();
        if (resCnt == 0) {
            return false;
        }
        return true;
    }
    // 2016/07/07 CSA-QC#11178 Add End

    // QC#5786
    /**
     * @param glblCmpyCd String
     * @param trtyRuleTpCd String
     * @param codeItems EZDCStringItemArray
     * @param valueItems EZDCStringItemArray
     */
    public static void createTerritoryRuleOperandPulldownList(String glblCmpyCd, String trtyRuleTpCd, EZDCStringItemArray codeItems, EZDCStringItemArray valueItems) {
        codeItems.clear();
        valueItems.clear();

        if (!ZYPCommonFunc.hasValue(trtyRuleTpCd)) {
            return;
        }

        TRTY_RULE_TPTMsg tMsgRule = new TRTY_RULE_TPTMsg();
        tMsgRule.glblCmpyCd.setValue(glblCmpyCd);
        tMsgRule.trtyRuleTpCd.setValue(trtyRuleTpCd);
        tMsgRule = (TRTY_RULE_TPTMsg) S21CodeTableAccessor.findByKey(tMsgRule);
        if (tMsgRule == null) {
            return;
        }

        List<TRTY_RULE_OPRD_TPTMsg> tMsgOprdList = new ArrayList<TRTY_RULE_OPRD_TPTMsg>();
        TRTY_RULE_OPRD_TPTMsg tMsgOprd = new TRTY_RULE_OPRD_TPTMsg();
        tMsgOprd.glblCmpyCd.setValue(glblCmpyCd);
        TRTY_RULE_OPRD_TPTMsgArray tMsgOprdArray = (TRTY_RULE_OPRD_TPTMsgArray) S21CodeTableAccessor.findAll(tMsgOprd);
        for (int i = 0; i < tMsgOprdArray.length(); i++) {
            tMsgOprd = tMsgOprdArray.no(i);
            if (TRTY_RULE_OPRD_TP.IN.equals(tMsgOprd.trtyRuleOprdTpCd.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(tMsgRule.trtyRuleInOprdAvalFlg.getValue())) {
                    tMsgOprdList.add(tMsgOprdArray.no(i));
                }
                // Add Start 2018/06/01 QC#24293
            } else if (TRTY_RULE_OPRD_TP.BETWEEN.equals(tMsgOprd.trtyRuleOprdTpCd.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(tMsgRule.trtyRuleBtwOprdAvalFlg.getValue())) {
                    tMsgOprdList.add(tMsgOprdArray.no(i));
                }
                // Add End 2018/06/01 QC#24293
            } else {
                tMsgOprdList.add(tMsgOprdArray.no(i));
            }
        }

        for (int i = 0; i < tMsgOprdList.size(); i++) {
            tMsgOprd = tMsgOprdList.get(i);
            ZYPEZDItemValueSetter.setValue(codeItems.no(i), tMsgOprd.trtyRuleOprdTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(valueItems.no(i), tMsgOprd.trtyRuleOprdTpDescTxt.getValue());
        }
    }

    // Add Start 2017/03/03 QC#15878
    /**
     * @param glblCmpyCd String
     * @param trtyRuleTpCd String
     * @param valueItem EZDSStringItem
     * @param itemNm String
     * @return exist Value
     */
    public static boolean checkRuleValue(String glblCmpyCd, String trtyRuleTpCd, EZDSStringItem valueItem, String itemNm) {

        if (!ZYPCommonFunc.hasValue(valueItem)) {
            return true;
        }

        String getValue = valueItem.getValue();
        if (!checkInputLength(glblCmpyCd, trtyRuleTpCd, valueItem, itemNm)) {
            return false;
        }

        boolean existValueFlg = true;
        boolean postalFormatFlg = true;
        if (TRTY_RULE_TP.STATE.equals(trtyRuleTpCd)) {
            STTMsg tMsg = new STTMsg();
            tMsg.glblCmpyCd.setValue(glblCmpyCd);
            tMsg.stCd.setValue(getValue);
            tMsg = (STTMsg) S21CodeTableAccessor.findByKey(tMsg);
            if (null == tMsg) {
                existValueFlg = false;
            }
        } else if (TRTY_RULE_TP.POSTAL_CODE.equals(trtyRuleTpCd)) {
            // START 2017/06/19 J.Kim [QC#19029,MOD]
            // POSTTMsg tMsg = new POSTTMsg();
            // tMsg.setSQLID("001");
            // tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            // tMsg.setConditionValue("postCd01", getValue);
            // POSTTMsgArray tMsgArray = (POSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
            // if (tMsgArray.length() == 0) {
            //     existValueFlg = false;
            // }
            if(checkPostalFormat(getValue)) {
                postalFormatFlg = false;
            }
            // END 2017/06/19 J.Kim [QC#19029,MOD]
            // Mod Start 2018/06/01 QC#24293
        //} else if (TRTY_RULE_TP.ACCOUNT_NAME.equals(trtyRuleTpCd)) {
        //    S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().checkExistAcctNm(getValue);
        } else if (TRTY_RULE_TP.ACCOUNT_NUMBER.equals(trtyRuleTpCd)) {
            // Mod End 2018/06/01 QC#24293
            S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().checkExistAcctNum(getValue);
            Integer resCnt = (Integer) ssmResult.getResultObject();
            if (resCnt == 0) {
                existValueFlg = false;
            }
        } else if (TRTY_RULE_TP.LOCATION_NUMBER.equals(trtyRuleTpCd)) {
            // 2017/08/21 QC#20273 Mod Start
            //PTY_LOC_WRKTMsg tMsg = new PTY_LOC_WRKTMsg();
            //tMsg.setSQLID("013");
            //tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            //tMsg.setConditionValue("locNum01", getValue);
            //tMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
            //PTY_LOC_WRKTMsgArray tMsgArray = (PTY_LOC_WRKTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
            //if (tMsgArray.length() == 0) {
            //    existValueFlg = false;
            //}
            S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().checkExistLocNum(getValue, ZYPDateUtil.getSalesDate());
            Integer resCnt = (Integer) ssmResult.getResultObject();
            if (resCnt == 0) {
                existValueFlg = false;
            }
            // 2017/08/21 QC#20273 Mod End
        } else if (TRTY_RULE_TP.ACCOUNT_CLASSIFICATION.equals(trtyRuleTpCd)) {
            DS_ACCT_CLSTMsg tMsg = new DS_ACCT_CLSTMsg();
            tMsg.glblCmpyCd.setValue(glblCmpyCd);
            tMsg.dsAcctClsCd.setValue(getValue);
            tMsg = (DS_ACCT_CLSTMsg) S21CodeTableAccessor.findByKey(tMsg);
            if (null == tMsg) {
                existValueFlg = false;
            }
        } else if (TRTY_RULE_TP.CUSTOMERGROUP.equals(trtyRuleTpCd)) {
            DS_ACCT_GRPTMsg tMsg = new DS_ACCT_GRPTMsg();
            tMsg.glblCmpyCd.setValue(glblCmpyCd);
            tMsg.dsAcctGrpCd.setValue(getValue);
            tMsg = (DS_ACCT_GRPTMsg) S21CodeTableAccessor.findByKey(tMsg);
            if (null == tMsg) {
                existValueFlg = false;
            }
            // Del Start 2018/06/01 QC#24293
        //} else if (TRTY_RULE_TP.COUNTY.equals(trtyRuleTpCd)) {
        //    S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().checkExistCntyNm(getValue);
        //    Integer resCnt = (Integer) ssmResult.getResultObject();
        //    if (resCnt == 0) {
        //        existValueFlg = false;
        //    }
        //} else if (TRTY_RULE_TP.CITY.equals(trtyRuleTpCd)) {
        //    S21SsmEZDResult ssmResult = NMAL2610Query.getInstance().checkExistCtyAddr(getValue);
        //    Integer resCnt = (Integer) ssmResult.getResultObject();
        //    if (resCnt == 0) {
        //        existValueFlg = false;
        //    }
            // Del End 2018/06/01 QC#24293
        }

        if (!existValueFlg) {
            valueItem.setErrorInfo(1, "NMAM0009E", new String[] {getValue });
        }

        // START 2017/06/19 J.Kim [QC#19029,ADD]
        if (!postalFormatFlg) {
            valueItem.setErrorInfo(1, NMAL2610Constant.NMAM8075E, new String[] {NMAL2610Constant.ZIP_CODE_FORMAT });
            existValueFlg = false;
        }
        // END 2017/06/19 J.Kim [QC#19029,ADD]

        return existValueFlg;
    }

    /**
     * @param glblCmpyCd String
     * @param trtyRuleTpCd String
     * @param valueItem EZDCStringItem
     * @param itemNm String
     * @return check Length
     */
    public static boolean checkInputLength(String glblCmpyCd, String trtyRuleTpCd, EZDCStringItem valueItem, String itemNm) {

        TRTY_RULE_TPTMsg tMsg = new TRTY_RULE_TPTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.trtyRuleTpCd.setValue(trtyRuleTpCd);
        tMsg = (TRTY_RULE_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);

        int inputValueLen = valueItem.getValue().length();
        int getValueLen = tMsg.charDigitNum.getValueInt();

        if (0 == getValueLen) {
            return true;
        } else if (inputValueLen > getValueLen) {
            valueItem.setErrorInfo(1, "NMAM8350E", new String[] {itemNm, String.valueOf(getValueLen) });
            return false;
        }

        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param trtyRuleTpCd String
     * @param valueItem EZDSStringItem
     * @param itemNm String
     * @return check Length
     */
    public static boolean checkInputLength(String glblCmpyCd, String trtyRuleTpCd, EZDSStringItem valueItem, String itemNm) {

        TRTY_RULE_TPTMsg tMsg = new TRTY_RULE_TPTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.trtyRuleTpCd.setValue(trtyRuleTpCd);
        tMsg = (TRTY_RULE_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);

        int inputValueLen = valueItem.getValue().length();
        int getValueLen = tMsg.charDigitNum.getValueInt();

        if (0 == getValueLen) {
            return true;
        } else if (inputValueLen > getValueLen) {
            valueItem.setErrorInfo(1, "NMAM8350E", new String[] {itemNm, String.valueOf(getValueLen) });
            return false;
        }

        return true;
    }

    public static boolean checkDiffLine(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg, int i) {

        if (!S21StringUtil.isEquals(sMsg.C.no(i).trtyRuleTpCd_P1.getValue(), sMsg.E.no(i).trtyRuleTpCd_P1.getValue())) {
            return true;
        }

        if (!S21StringUtil.isEquals(sMsg.C.no(i).trtyRuleOprdTpCd_P1.getValue(), sMsg.E.no(i).trtyRuleOprdTpCd_P1.getValue())) {
            return true;
        }

        if (!S21StringUtil.isEquals(sMsg.C.no(i).trtyRuleFromValTxt_C1.getValue(), sMsg.E.no(i).trtyRuleFromValTxt_C1.getValue())) {
            return true;
        }

        if (!S21StringUtil.isEquals(sMsg.C.no(i).trtyRuleThruValTxt_C1.getValue(), sMsg.E.no(i).trtyRuleThruValTxt_C1.getValue())) {
            return true;
        }

        if (!S21StringUtil.isEquals(sMsg.C.no(i).trtyRuleLogicTpCd_P1.getValue(), sMsg.E.no(i).trtyRuleLogicTpCd_P1.getValue())) {
            return true;
        }

        if (!S21StringUtil.isEquals(sMsg.C.no(i).effFromDt_C1.getValue(), sMsg.E.no(i).effFromDt_C1.getValue())) {
            return true;
        }

        if (!S21StringUtil.isEquals(sMsg.C.no(i).effThruDt_C1.getValue(), sMsg.E.no(i).effThruDt_C1.getValue())) {
            return true;
        }

        return false;
    }
    // Add End 2017/03/03 QC#15878

    // START 2017/06/19 J.Kim [QC#19029,ADD]
    private static boolean checkPostalFormat(String postCd) {

        boolean errorFlg = false;
        if (postCd.length() != 5 && postCd.length() != 10) {
            errorFlg = true;
        } else if (postCd.length() == 10) {
            if (!NMAL2610Constant.HYPHEN.equals(postCd.substring(5, 6))) {
                errorFlg = true;
            }
        } else if (postCd.length() == 5) {
            try {
                new BigDecimal(String.valueOf(postCd.substring(0, 5)));
            } catch (Exception e) {
                errorFlg = true;
            }
        }
        return errorFlg;
    }
    // END 2017/06/19 J.Kim [QC#19029,ADD]
    // 2017/06/23 QC#16677 ADD START
    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NMAL2610CMsg bizMsg = (NMAL2610CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum_C1.clear();
        bizMsg.xxPageShowOfNum_C1.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum_C1.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);

            } else {

                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum_C1.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum_C1.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum_C1.setValue(sMsgArray.getValidCount());
    }

    /**
     * Update the global Message.
     * @param bizMsg NMAL2610CMsg
     * @param glblMsg NMAL2610SMsg
     */
    public static void updateGlblMsg(NMAL2610CMsg bizMsg, NMAL2610SMsg glblMsg) {

        int ixG = bizMsg.xxPageShowFromNum_C1.getValueInt() - 1;
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.C.no(i), null, glblMsg.C.no(ixG + i), null);
        }
    }

    // 2017/06/23 QC#16677 ADD E N D
    // 2017/11/16 CSA-QC#20597 Add Start
    public static boolean checkWarningTrtyRule(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        boolean skipFlg = true;

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue())) {

          // Territory ID
          if (!cMsg.orgCd_H1.getValue().equals(cMsg.orgCd_BW.getValue())) {
              skipFlg= false;
          }
          // Territory Type
          if (!cMsg.trtyTpCd_P1.getValue().equals(cMsg.trtyTpCd_BW.getValue())) {
              skipFlg= false;
          }
          // Territory Group
          if (!cMsg.trtyGrpTpCd_P1.getValue().equals(cMsg.trtyGrpTpCd_BW.getValue())) {
              skipFlg= false;
          }

          for (int i = 0; i < sMsg.C.getValidCount(); i++) {

              // Rule Type
              if (!sMsg.C.no(i).trtyRuleTpCd_P1.getValue().equals(sMsg.C.no(i).trtyRuleTpCd_BW.getValue())) {
                  skipFlg= false;
              }
              // Operand Type
              if (!sMsg.C.no(i).trtyRuleOprdTpCd_P1.getValue().equals(sMsg.C.no(i).trtyRuleOprdTpCd_BW.getValue())) {
                  skipFlg= false;
              }
              // From Value
              if (!sMsg.C.no(i).trtyRuleFromValTxt_C1.getValue().equals(sMsg.C.no(i).trtyRuleFromValTxt_BW.getValue())) {
                  skipFlg= false;
              }
              // Thru Value
              if (!sMsg.C.no(i).trtyRuleThruValTxt_C1.getValue().equals(sMsg.C.no(i).trtyRuleThruValTxt_BW.getValue())) {
                  skipFlg= false;
              }
              // Logic Type
              if (!sMsg.C.no(i).trtyRuleLogicTpCd_P1.getValue().equals(sMsg.C.no(i).trtyRuleLogicTpCd_BW.getValue())) {
                  skipFlg= false;
              }
              // Effective From
              if (!sMsg.C.no(i).effFromDt_C1.getValue().equals(sMsg.C.no(i).effFromDt_BW.getValue())) {
                  skipFlg= false;
              }
              // Effective Thru
              if (!sMsg.C.no(i).effThruDt_C1.getValue().equals(sMsg.C.no(i).effThruDt_BW.getValue())) {
                  skipFlg= false;
              }
          }
        } else {
            skipFlg = false;
        }

        return skipFlg;
    }
    public static void setWarningBackup(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.orgCd_BW, cMsg.orgCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.trtyTpCd_BW, cMsg.trtyTpCd_P1);
        ZYPEZDItemValueSetter.setValue(cMsg.trtyGrpTpCd_BW, cMsg.trtyGrpTpCd_P1);

        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).trtyRuleTpCd_BW, sMsg.C.no(i).trtyRuleTpCd_P1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).trtyRuleOprdTpCd_BW, sMsg.C.no(i).trtyRuleOprdTpCd_P1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).trtyRuleFromValTxt_BW, sMsg.C.no(i).trtyRuleFromValTxt_C1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).trtyRuleThruValTxt_BW, sMsg.C.no(i).trtyRuleThruValTxt_C1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).trtyRuleLogicTpCd_BW, sMsg.C.no(i).trtyRuleLogicTpCd_P1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).effFromDt_BW, sMsg.C.no(i).effFromDt_C1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).effThruDt_BW, sMsg.C.no(i).effThruDt_C1);
        }
    }
    // 2017/11/16 CSA-QC#20597 Add End
    // 2017/11/21 QC#21350 add start
    public static String copyHdrEndDt(String hdrThruDt, String dtlFromDt, String dtlThruDt) {
        if (hdrThruDt.compareTo(dtlFromDt) < 0) {
            return "";
        } else if (!ZYPCommonFunc.hasValue(dtlThruDt) || hdrThruDt.compareTo(dtlThruDt) <= 0) {
            return hdrThruDt;
        }

        return dtlThruDt;
    }
    // 2017/11/21 QC#21350 add end
    /**
     * Check that any line of Terittory Rule was changed.
     * @param sMsg NMAL2610SMsg
     */
    // Add Start 2019/12/06 QC#53019
    public static boolean isChangedTrtyRule(NMAL2610SMsg sMsg) {
        // Check : Any lines were deleted.
        if(sMsg.Y.getValidCount() > 0){
            return true;
        } 

        // Check : Any lines were changed.
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (!S21StringUtil.isEquals(sMsg.C.no(i).trtyRuleTpCd_P1.getValue(), sMsg.E.no(i).trtyRuleTpCd_P1.getValue())) {
                return true;
            }

            if (!S21StringUtil.isEquals(sMsg.C.no(i).trtyRuleOprdTpCd_P1.getValue(), sMsg.E.no(i).trtyRuleOprdTpCd_P1.getValue())) {
                return true;
            }

            if (!S21StringUtil.isEquals(sMsg.C.no(i).trtyRuleFromValTxt_C1.getValue(), sMsg.E.no(i).trtyRuleFromValTxt_C1.getValue())) {
                return true;
            }

            if (!S21StringUtil.isEquals(sMsg.C.no(i).trtyRuleThruValTxt_C1.getValue(), sMsg.E.no(i).trtyRuleThruValTxt_C1.getValue())) {
                return true;
            }

            if (!S21StringUtil.isEquals(sMsg.C.no(i).trtyRuleLogicTpCd_P1.getValue(), sMsg.E.no(i).trtyRuleLogicTpCd_P1.getValue())) {
                return true;
            }

            if (!S21StringUtil.isEquals(sMsg.C.no(i).effFromDt_C1.getValue(), sMsg.E.no(i).effFromDt_C1.getValue())) {
                return true;
            }

            if (!S21StringUtil.isEquals(sMsg.C.no(i).effThruDt_C1.getValue(), sMsg.E.no(i).effThruDt_C1.getValue())) {
                return true;
            }
        }
        return false;
    }
    // Add End   2019/12/06 QC#53019
}
