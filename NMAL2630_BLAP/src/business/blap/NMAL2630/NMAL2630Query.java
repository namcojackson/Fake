/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2630;

import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL2630.constant.NMAL2630Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/20   Fujitsu         N.Sugiura       Create          N/A
 * 2015/12/15   Fujitsu         K.Koitabashi    Update          QC#2023
 * 2016/05/31   SRAA            Y.Chen          Update          QC#9182
 * 2016/06/23   Hitach          A.Kohinata      Update          CSA-QC#10280
 * 2017/02/23   Fujitsu         K.Ishizuka      Update          QC#16481
 * 2017/11/28   Fujitsu         Hd.Sugawara     Update          QC#21044
 *</pre>
 */
public final class NMAL2630Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL2630Query INSTANCE = new NMAL2630Query();

    /**
     * Constructor.
     */
    private NMAL2630Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return  NMAL2630Query
     */
    public static NMAL2630Query getInstance() {
        return INSTANCE;
    }

    /**
     * Organization Search.
     * @param cMsg NMAL2630CMsg
     * @param sMsg NMAL2630SMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritory(NMAL2630CMsg cMsg, NMAL2630SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("orgStruTpCd", cMsg.orgStruTpCd_H1.getValue());
        ssmParam.put("orgNm", cMsg.orgNm_H1.getValue());
        // QC#9182
        ssmParam.put("orgCd", cMsg.orgCd_H1.getValue());
        ssmParam.put("prntOrgNm", cMsg.orgNm_H2.getValue());
        ssmParam.put("xxPsnNm", cMsg.xxPsnNm_H1.getValue());
        ssmParam.put("psnCd", cMsg.psnCd_H1.getValue());
        ssmParam.put("orgTierCd", cMsg.orgTierCd_H1.getValue());
        // 2016/06/23 CSA-QC#10280 Mod Start
//        ssmParam.put("lineBizTpCd", cMsg.lineBizTpCd_H1.getValue());
        ssmParam.put("trtyTpCd", cMsg.trtyTpCd_H1.getValue());
        ssmParam.put("trtyGrpTpCd", cMsg.trtyGrpTpCd_H1.getValue());
        // 2016/06/23 CSA-QC#10280 Mod End
        // MOD START S21_NA QC#16481
        ssmParam.put("effFromDt_FR", cMsg.effFromDt_FR.getValue());
        ssmParam.put("effFromDt_TO", cMsg.effFromDt_TO.getValue());
        // MOD END S21_NA QC16481
        ssmParam.put("organizationOrgNm", cMsg.orgNm_H3.getValue());
        ssmParam.put("gnrnTpCd", GNRN_TP.CURRENT);
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxEffectiveDate", NMAL2630Constant.MAX_EFFECTIVE_DATE);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        // Add Start 2017/11/28 QC#21044
        if (ZYPCommonFunc.hasValue(cMsg.nonSlsRepFlg_H1.getValue())) {
            ssmParam.put("nonSlsRepFlg", cMsg.nonSlsRepFlg_H1.getValue());
        }
        // Add End 2017/11/28 QC#21044

        if (ZYPCommonFunc.hasValue(cMsg.xxDplyTab.getValue())) {

            for (int l = 0; l < NMAL2630Constant.ADV_MAX_CNT; l++) {
                ssmParam.put("trtyRuleTpCd" + String.valueOf(l), "");
                ssmParam.put("trtyRuleOprdTpCd" + String.valueOf(l), "");
                ssmParam.put("trtyRuleFromVal" + String.valueOf(l), "");
                ssmParam.put("trtyRuleThruVal" + String.valueOf(l), "");
                ssmParam.put("AdvSearchFlg" + String.valueOf(l), "");
            }

            int j = 1;
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                NMAL2630_BCMsg line = cMsg.B.no(i);
                String trtyRuleTpCd = line.trtyRuleTpCd_B1.getValue();
                String trtyRuleOprdTpCd = line.trtyRuleOprdTpCd_B1.getValue();
                String trtyRuleFromValTx = line.trtyRuleFromValTxt_B1.getValue();
                String trtyRuleThruValTxt = line.trtyRuleThruValTxt_B1.getValue();

                if (ZYPCommonFunc.hasValue(trtyRuleTpCd)
                        || ZYPCommonFunc.hasValue(trtyRuleOprdTpCd)
                        || ZYPCommonFunc.hasValue(trtyRuleFromValTx)
                        || ZYPCommonFunc.hasValue(trtyRuleThruValTxt)) {


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

        }

        return getSsmEZDClient().queryEZDMsgArray("getTerritory", ssmParam, sMsg.A);
    }
    /**
     * @param cMsg NMAL2630CMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getBusinessArea(NMAL2630CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        return getSsmEZDClient().queryObjectList("getBusinessArea", ssmParam);
    }
}
