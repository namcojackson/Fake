/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2600.common;

import static business.blap.NMAL2600.constant.NMAL2600Constant.BIZ_ID;
import static business.blap.NMAL2600.constant.NMAL2600Constant.SCRN_ID_00;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItemArray;
import parts.common.EZDCStringItemArray;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL2600.NMAL2600CMsg;
import business.blap.NMAL2600.NMAL2600Query;
import business.blap.NMAL2600.NMAL2600SMsg;
import business.blap.NMAL2600.NMAL2600_ACMsg;
import business.blap.NMAL2600.constant.NMAL2600Constant;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002Query;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_OPRD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NMAL2600 Territory Search
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/04/25   Fujitsu         C.Yokoi         Update          CSA-QC#7574
 * 2016/09/21   Fujitsu         Mz.Takahashi    Update          QC#11068
 *</pre>
 */
public class NMAL2600CommonLogic {

    private static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * clear Msg
     * @param cMsg NMAL2600CMsg
     * @param sMsg NMAL2600SMsg
     */
    public static void clearMsg(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {

    }

    /**
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
    public static void createPulldownList(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg, String globalCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(NMAL2600Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("glblCmpyCd", globalCmpyCd);
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);

        // Business Area
        S21SsmEZDResult bizAreaOrgPulldownList = NMAL2600Query.getInstance().getBizAreaOrgPulldownList(ssmParam);

        if (bizAreaOrgPulldownList.isCodeNormal()) {

            List<Map<String, Object>> bizAreaOrgList = (List<Map<String, Object>>) bizAreaOrgPulldownList.getResultObject();
            NMAL2600CommonLogic.createPulldownList(cMsg.bizAreaOrgCd_H1, cMsg.bizAreaOrgNm_H1, bizAreaOrgList, new String[] {NMAL2600Constant.BIZ_AREA_ORG_CD_DBCOLUMN, NMAL2600Constant.BIZ_AREA_ORG_NM_DBCOLUMN });
        }

        // Territory Type
        S21SsmEZDResult trtyTpPulldownList = NMAL2600Query.getInstance().getTrtyTpPulldownList(ssmParam);

        if (trtyTpPulldownList.isCodeNormal()) {

            List<Map<String, Object>> trtyTpList = (List<Map<String, Object>>) trtyTpPulldownList.getResultObject();
            NMAL2600CommonLogic.createPulldownList(cMsg.trtyTpCd_H1, cMsg.trtyTpNm_H1, trtyTpList, new String[] {NMAL2600Constant.TRTY_TP_CD_DBCOLUMN, NMAL2600Constant.TRTY_TP_NM_DBCOLUMN });
        }

        // Organization Tier
        S21SsmEZDResult orgTierPulldownList = NMAL2600Query.getInstance().getOrgTierPulldownList(ssmParam);

        if (orgTierPulldownList.isCodeNormal()) {

            List<Map<String, Object>> orgTierList = (List<Map<String, Object>>) orgTierPulldownList.getResultObject();
            NMAL2600CommonLogic.createPulldownList(cMsg.orgTierCd_H1, cMsg.orgTierNm_H1, orgTierList, new String[] {NMAL2600Constant.ORG_TIER_CD_DBCOLUMN, NMAL2600Constant.ORG_TIER_NM_DBCOLUMN });
        }

        // Territory Group Type
        S21SsmEZDResult trtyGrpTpPulldownList = NMAL2600Query.getInstance().getTrtyGrpTpPulldownList(ssmParam);

        if (trtyGrpTpPulldownList.isCodeNormal()) {

            List<Map<String, Object>> trtyGrpTpList = (List<Map<String, Object>>) trtyGrpTpPulldownList.getResultObject();
            NMAL2600CommonLogic.createPulldownList(cMsg.trtyGrpTpCd_H1, cMsg.trtyGrpTpNm_H1, trtyGrpTpList, new String[] {NMAL2600Constant.TRTY_GRP_TP_CD_DBCOLUMN, NMAL2600Constant.TRTY_GRP_TP_NM_DBCOLUMN });
        }

        // Territory Rule Type
        ZYPCodeDataUtil.createPulldownList(TRTY_RULE_TP.class, cMsg.trtyRuleTpCd_A1, cMsg.trtyRuleTpNm_A1);

        // Territory Rule Operand Type
        ZYPCodeDataUtil.createPulldownList(TRTY_RULE_OPRD_TP.class, cMsg.trtyRuleOprdTpCd_A1, cMsg.trtyRuleOprdTpNm_A1);

    }

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
     * getAdditionalOrg
     * @param cMsg NMAL2600CMsg
     * @param sMsg NMAL2600SMsg
     */
    public static boolean getAdditionalOrg(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {

        S21SsmEZDResult ssmResult;
        ssmResult = NMAL2600Query.getInstance().getAdditionalOrg(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            Map<String, Object> map = (Map<String, Object>) resultList.get(0);

            if (map.get(NMAL2600Constant.ORG_CD_DBCOLUMN) != null) {
                cMsg.orgCd_P.setValue((String) map.get(NMAL2600Constant.ORG_CD_DBCOLUMN));
            }
        // 2016/04/25 CSA-QC#7547 Add Start
            return true;
        }
        return false;
        // 2016/04/25 CSA-QC#7547 Add End
    }

    /**
     * setAdvancedSearchCriteria
     * @param ssmParam Map<String, Object>
     * @param cMsg NMAL2600CMsg
     * @return ssmParam Map<String, Object>
     */
    public static Map<String, Object> setAdvancedSearchCriteria(Map<String, Object> ssmParam, NMAL2600CMsg cMsg) {

        for (int l = 0; l < NMAL2600Constant.ADV_MAX_CNT; l++) {
            ssmParam.put("trtyRuleTpCd" + String.valueOf(l), "");
            ssmParam.put("trtyRuleOprdTpCd" + String.valueOf(l), "");
            ssmParam.put("trtyRuleFromVal" + String.valueOf(l), "");
            ssmParam.put("trtyRuleThruVal" + String.valueOf(l), "");
            ssmParam.put("AdvSearchFlg" + String.valueOf(l), "");
        }

        int j = 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NMAL2600_ACMsg line = cMsg.A.no(i);
            String trtyRuleTpCd = line.trtyRuleTpCd_P1.getValue();
            String trtyRuleOprdTpCd = line.trtyRuleOprdTpCd_P1.getValue();
            String trtyRuleFromValTx = line.trtyRuleFromValTxt_A1.getValue();
            String trtyRuleThruValTxt = line.trtyRuleThruValTxt_A1.getValue();

            if (ZYPCommonFunc.hasValue(trtyRuleTpCd) || ZYPCommonFunc.hasValue(trtyRuleOprdTpCd) || ZYPCommonFunc.hasValue(trtyRuleFromValTx) || ZYPCommonFunc.hasValue(trtyRuleThruValTxt)) {

                if (ZYPCommonFunc.hasValue(trtyRuleTpCd)) {
                    ssmParam.put("trtyRuleTpCd" + String.valueOf(j), trtyRuleTpCd);
                }
                if (ZYPCommonFunc.hasValue(trtyRuleOprdTpCd)) {
                    ssmParam.put("trtyRuleOprdTpCd" + String.valueOf(j), trtyRuleOprdTpCd);
                }
                if (ZYPCommonFunc.hasValue(trtyRuleFromValTx)) {
                    ssmParam.put("trtyRuleFromVal" + String.valueOf(j), trtyRuleFromValTx);
                }
                if (ZYPCommonFunc.hasValue(trtyRuleThruValTxt)) {
                    ssmParam.put("trtyRuleThruVal" + String.valueOf(j), trtyRuleThruValTxt);
                }
                ssmParam.put("AdvSearchFlg" + String.valueOf(j), ZYPConstant.FLG_ON_Y);

                j++;

            }
        }

        return ssmParam;
    }

    /**
     * convDotToOrgName
     * @param sMsg NMAL2600SMsg
     * @param i int
     */
    public static void convDotToOrgName(NMAL2600SMsg sMsg, int i) {

        String targetTier = sMsg.T.no(i).orgTierCd_T.getValue();

        if ("1".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                sMsg.T.no(i).orgNm_0.setValue(NMAL2600Constant.DOT);
            }
        } else if ("2".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                sMsg.T.no(i).orgNm_0.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                sMsg.T.no(i).orgNm_1.setValue(NMAL2600Constant.DOT);
            }
        } else if ("3".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                sMsg.T.no(i).orgNm_0.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                sMsg.T.no(i).orgNm_1.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
                sMsg.T.no(i).orgNm_2.setValue(NMAL2600Constant.DOT);
            }
        } else if ("4".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                sMsg.T.no(i).orgNm_0.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                sMsg.T.no(i).orgNm_1.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
                sMsg.T.no(i).orgNm_2.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
                sMsg.T.no(i).orgNm_3.setValue(NMAL2600Constant.DOT);
            }
        } else if ("5".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                sMsg.T.no(i).orgNm_0.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                sMsg.T.no(i).orgNm_1.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
                sMsg.T.no(i).orgNm_2.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
                sMsg.T.no(i).orgNm_3.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
                sMsg.T.no(i).orgNm_4.setValue(NMAL2600Constant.DOT);
            }
        } else if ("6".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                sMsg.T.no(i).orgNm_0.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                sMsg.T.no(i).orgNm_1.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
                sMsg.T.no(i).orgNm_2.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
                sMsg.T.no(i).orgNm_3.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
                sMsg.T.no(i).orgNm_4.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_5)) {
                sMsg.T.no(i).orgNm_5.setValue(NMAL2600Constant.DOT);
            }
        } else if ("7".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                sMsg.T.no(i).orgNm_0.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                sMsg.T.no(i).orgNm_1.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
                sMsg.T.no(i).orgNm_2.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
                sMsg.T.no(i).orgNm_3.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
                sMsg.T.no(i).orgNm_4.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_5)) {
                sMsg.T.no(i).orgNm_5.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_6)) {
                sMsg.T.no(i).orgNm_6.setValue(NMAL2600Constant.DOT);
            }
        } else if ("8".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                sMsg.T.no(i).orgNm_0.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                sMsg.T.no(i).orgNm_1.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
                sMsg.T.no(i).orgNm_2.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
                sMsg.T.no(i).orgNm_3.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
                sMsg.T.no(i).orgNm_4.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_5)) {
                sMsg.T.no(i).orgNm_5.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_6)) {
                sMsg.T.no(i).orgNm_6.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_7)) {
                sMsg.T.no(i).orgNm_7.setValue(NMAL2600Constant.DOT);
            }
        } else if ("9".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                sMsg.T.no(i).orgNm_0.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                sMsg.T.no(i).orgNm_1.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
                sMsg.T.no(i).orgNm_2.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
                sMsg.T.no(i).orgNm_3.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
                sMsg.T.no(i).orgNm_4.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_5)) {
                sMsg.T.no(i).orgNm_5.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_6)) {
                sMsg.T.no(i).orgNm_6.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_7)) {
                sMsg.T.no(i).orgNm_7.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_8)) {
                sMsg.T.no(i).orgNm_8.setValue(NMAL2600Constant.DOT);
            }
        } else if ("10".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                sMsg.T.no(i).orgNm_0.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                sMsg.T.no(i).orgNm_1.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
                sMsg.T.no(i).orgNm_2.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
                sMsg.T.no(i).orgNm_3.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
                sMsg.T.no(i).orgNm_4.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_5)) {
                sMsg.T.no(i).orgNm_5.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_6)) {
                sMsg.T.no(i).orgNm_6.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_7)) {
                sMsg.T.no(i).orgNm_7.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_8)) {
                sMsg.T.no(i).orgNm_8.setValue(NMAL2600Constant.DOT);
            }
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_9)) {
                sMsg.T.no(i).orgNm_9.setValue(NMAL2600Constant.DOT);
            }
        }
    }

    /*
     * Save Search Option
     */
    public static void callNszc0330forDeleteSearch(NMAL2600CMsg bizMsg, NMAL2600SMsg glblMsg, String userId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, userId, glblCmpyCd);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I" //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Delete Search") });
        }
    }

    public static boolean isExistSaveSearchName(NMAL2600CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) //
                        && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public static void callNszc0330forSaveSearch(NMAL2600CMsg bizMsg, NMAL2600SMsg glblMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) //
                || isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, bizMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.bizAreaOrgCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.orgNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.xxPsnNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.psnCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.psnNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.effFromDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.trtyTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.orgTierCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.trtyGrpTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.xxChkBox_H1);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.A.no(0).xxChkBox_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.A.no(0).trtyRuleTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.A.no(0).trtyRuleOprdTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.A.no(0).trtyRuleFromValTxt_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.A.no(0).trtyRuleThruValTxt_A1);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.A.no(1).xxChkBox_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, bizMsg.A.no(1).trtyRuleTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, bizMsg.A.no(1).trtyRuleOprdTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, bizMsg.A.no(1).trtyRuleFromValTxt_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, bizMsg.A.no(1).trtyRuleThruValTxt_A1);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, bizMsg.A.no(2).xxChkBox_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, bizMsg.A.no(2).trtyRuleTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, bizMsg.A.no(2).trtyRuleOprdTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_25, bizMsg.A.no(2).trtyRuleFromValTxt_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_26, bizMsg.A.no(2).trtyRuleThruValTxt_A1);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_27, bizMsg.A.no(3).xxChkBox_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_28, bizMsg.A.no(3).trtyRuleTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_29, bizMsg.A.no(3).trtyRuleOprdTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_30, bizMsg.A.no(3).trtyRuleFromValTxt_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_31, bizMsg.A.no(3).trtyRuleThruValTxt_A1);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_32, bizMsg.A.no(4).xxChkBox_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_33, bizMsg.A.no(4).trtyRuleTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_34, bizMsg.A.no(4).trtyRuleOprdTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_35, bizMsg.A.no(4).trtyRuleFromValTxt_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_36, bizMsg.A.no(4).trtyRuleThruValTxt_A1);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_37, bizMsg.A.no(5).xxChkBox_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_38, bizMsg.A.no(5).trtyRuleTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_39, bizMsg.A.no(5).trtyRuleOprdTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_40, bizMsg.A.no(5).trtyRuleFromValTxt_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_41, bizMsg.A.no(5).trtyRuleThruValTxt_A1);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_42, bizMsg.A.no(6).xxChkBox_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_43, bizMsg.A.no(6).trtyRuleTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_44, bizMsg.A.no(6).trtyRuleOprdTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_45, bizMsg.A.no(6).trtyRuleFromValTxt_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_46, bizMsg.A.no(6).trtyRuleThruValTxt_A1);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_47, bizMsg.A.no(7).xxChkBox_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_48, bizMsg.A.no(7).trtyRuleTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_49, bizMsg.A.no(7).trtyRuleOprdTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_50, bizMsg.A.no(7).trtyRuleFromValTxt_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_51, bizMsg.A.no(7).trtyRuleThruValTxt_A1);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_52, bizMsg.A.no(8).xxChkBox_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_53, bizMsg.A.no(8).trtyRuleTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_54, bizMsg.A.no(8).trtyRuleOprdTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_55, bizMsg.A.no(8).trtyRuleFromValTxt_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_56, bizMsg.A.no(8).trtyRuleThruValTxt_A1);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_57, bizMsg.A.no(9).xxChkBox_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_58, bizMsg.A.no(9).trtyRuleTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_59, bizMsg.A.no(9).trtyRuleOprdTpCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_60, bizMsg.A.no(9).trtyRuleFromValTxt_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_61, bizMsg.A.no(9).trtyRuleThruValTxt_A1);

        if (!bizMsg.xxRadioBtn_H1.isClear()){
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_62, bizMsg.xxRadioBtn_H1.getValue().toPlainString());
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_63, bizMsg.xxDplyTab);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_99, String.valueOf( bizMsg.A.getValidCount()));

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, usrId, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I" //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Save Search") });
        }
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param bizMsg NYEL8810CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NMAL2600CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_L.no(i));
            }
        }
        return;
    }


    private static boolean isSameSaveSearchName(NMAL2600CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    
    private static boolean callNszc0330(NMAL2600CMsg bizMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        bizMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NMAL2600CMsg
     * @param srchOptUsrId user id
     */
    public static void createSavedSearchOptionsPullDown(NMAL2600CMsg bizMsg, String srchOptUsrId, String glblCmpyCd) {
        S21SsmEZDResult ssmResult = NYXC880002Query.getInstance().getSavedSearchOptionList(srchOptUsrId, BIZ_ID, glblCmpyCd);
        if (!ssmResult.isCodeNormal()) {
            bizMsg.srchOptPk_L.clear();
            bizMsg.srchOptNm_L.clear();
            return;
        }

        bizMsg.srchOptPk_L.clear();
        bizMsg.srchOptNm_L.clear();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_L.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }

    }

    /**
     * callNszc0330forSearchOption
     * @param cMsg NMAL2600CMsg
     * @param sMsg NMAL2600SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NMAL2600CMsg bizMsg, NMAL2600SMsg glblMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.bizAreaOrgCd_P1,  pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.orgNm_H1, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPsnNm_H1,   pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.psnCd_H1, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.psnNum_H1,    pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_H1, pMsg.srchOptTxt_06.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.trtyTpCd_P1,  pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.orgTierCd_P1, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(bizMsg.trtyGrpTpCd_P1,   pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_H1,  pMsg.srchOptTxt_10);

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).xxChkBox_A1,  pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).trtyRuleTpCd_P1,  pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).trtyRuleOprdTpCd_P1,  pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).trtyRuleFromValTxt_A1,    pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).trtyRuleThruValTxt_A1,    pMsg.srchOptTxt_15);

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(1).xxChkBox_A1,  pMsg.srchOptTxt_16);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(1).trtyRuleTpCd_P1,  pMsg.srchOptTxt_17);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(1).trtyRuleOprdTpCd_P1,  pMsg.srchOptTxt_18);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(1).trtyRuleFromValTxt_A1,    pMsg.srchOptTxt_19);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(1).trtyRuleThruValTxt_A1,    pMsg.srchOptTxt_20);

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(2).xxChkBox_A1,  pMsg.srchOptTxt_21);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(2).trtyRuleTpCd_P1,  pMsg.srchOptTxt_23);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(2).trtyRuleOprdTpCd_P1,  pMsg.srchOptTxt_24);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(2).trtyRuleFromValTxt_A1,    pMsg.srchOptTxt_25);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(2).trtyRuleThruValTxt_A1,    pMsg.srchOptTxt_26);

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(3).xxChkBox_A1,  pMsg.srchOptTxt_27);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(3).trtyRuleTpCd_P1,  pMsg.srchOptTxt_28);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(3).trtyRuleOprdTpCd_P1,  pMsg.srchOptTxt_29);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(3).trtyRuleFromValTxt_A1,    pMsg.srchOptTxt_30);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(3).trtyRuleThruValTxt_A1,    pMsg.srchOptTxt_31);

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(4).xxChkBox_A1,  pMsg.srchOptTxt_32);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(4).trtyRuleTpCd_P1,  pMsg.srchOptTxt_33);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(4).trtyRuleOprdTpCd_P1,  pMsg.srchOptTxt_34);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(4).trtyRuleFromValTxt_A1,    pMsg.srchOptTxt_35);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(4).trtyRuleThruValTxt_A1,    pMsg.srchOptTxt_36);

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(5).xxChkBox_A1,  pMsg.srchOptTxt_37);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(5).trtyRuleTpCd_P1,  pMsg.srchOptTxt_38);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(5).trtyRuleOprdTpCd_P1,  pMsg.srchOptTxt_39);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(5).trtyRuleFromValTxt_A1,    pMsg.srchOptTxt_40);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(5).trtyRuleThruValTxt_A1,    pMsg.srchOptTxt_41);

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(6).xxChkBox_A1,  pMsg.srchOptTxt_42);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(6).trtyRuleTpCd_P1,  pMsg.srchOptTxt_43);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(6).trtyRuleOprdTpCd_P1,  pMsg.srchOptTxt_44);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(6).trtyRuleFromValTxt_A1,    pMsg.srchOptTxt_45);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(6).trtyRuleThruValTxt_A1,    pMsg.srchOptTxt_46);

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(7).xxChkBox_A1,  pMsg.srchOptTxt_47);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(7).trtyRuleTpCd_P1,  pMsg.srchOptTxt_48);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(7).trtyRuleOprdTpCd_P1,  pMsg.srchOptTxt_49);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(7).trtyRuleFromValTxt_A1,    pMsg.srchOptTxt_50);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(7).trtyRuleThruValTxt_A1,    pMsg.srchOptTxt_51);

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(8).xxChkBox_A1,  pMsg.srchOptTxt_52);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(8).trtyRuleTpCd_P1,  pMsg.srchOptTxt_53);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(8).trtyRuleOprdTpCd_P1,  pMsg.srchOptTxt_54);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(8).trtyRuleFromValTxt_A1,    pMsg.srchOptTxt_55);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(8).trtyRuleThruValTxt_A1,    pMsg.srchOptTxt_56);

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(9).xxChkBox_A1,  pMsg.srchOptTxt_57);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(9).trtyRuleTpCd_P1,  pMsg.srchOptTxt_58);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(9).trtyRuleOprdTpCd_P1,  pMsg.srchOptTxt_59);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(9).trtyRuleFromValTxt_A1,    pMsg.srchOptTxt_60);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(9).trtyRuleThruValTxt_A1,    pMsg.srchOptTxt_61);

        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_62)){
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_H1, new BigDecimal(pMsg.srchOptTxt_62.getValue()));
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab,    pMsg.srchOptTxt_63);

        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_99)){
            bizMsg.A.setValidCount(Integer.parseInt(pMsg.srchOptTxt_99.getValue()));
        } else {
            bizMsg.A.setValidCount(NMAL2600Constant.ADV_DEF_CNT);
        }
    }
}
