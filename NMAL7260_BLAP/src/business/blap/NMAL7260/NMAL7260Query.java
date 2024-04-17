/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7260;

import static business.blap.NMAL7260.constant.NMAL7260Constant.HIGH_VAL_TM;
import static business.blap.NMAL7260.constant.NMAL7260Constant.HIGH_VAL_DT;
import static business.blap.NMAL7260.constant.NMAL7260Constant.ASTERISK;
import static business.blap.NMAL7260.constant.NMAL7260Constant.GLBLMSG_MAX_LENGTH;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NUM_100;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL7260.common.NMAL7260CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL7260Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2016/05/19   Fujitsu         Y.Kanefusa      Update          QC#5934
 * 2016/07/08   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 * 2016/09/13   Fujitsu         R.Nakamura      Update          QC#11615
 * 2016/10/13   Fujitsu         R.Nakamura      Update          QC#6931
 * 2016/10/11   Fujitsu         R.Nakamura      Update          QC#14936
 * 2016/11/10   Fujitsu         N.Aoyama        Update          QC#14548
 * 2017/08/15   Fujitsu         K.Ishizuka      Update          QC#18237(L3#161)
 * 2018/05/25   Fujitsu         T.Noguchi       Update          QC#26031
 * 2018/06/18   Fujitsu         M.Ishii         Update          QC#22594
 * 2018/12/04   Fujitsu         Hd.Sugawara     Update          QC#29321
 *</pre>
 */
public final class NMAL7260Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7260Query MY_INSTANCE = new NMAL7260Query();

    /**
     * Private constructor
     */
    private NMAL7260Query() {
        super();
    }

    /**
     * Get the NMAL7260Query instance.
     * @return NMAL7260Query instance
     */
    public static NMAL7260Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * searchHead
     * @param bizMsg NMAL7260CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchHead(NMAL7260CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcRuleHdrPk", bizMsg.prcRuleHdrPk_H1.getValue());
        params.put("prcRuleCondTpCd", PRC_RULE_COND_TP.PRICE_ADJUSTMENT_TABLE);

        return getSsmEZDClient().queryEZDMsg("searchHead", params, bizMsg);
    }

    /**
     * searchTblDefData
     * @param bizMsg NMAL7260CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchTblDefData(NMAL7260CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcRuleHdrPk", bizMsg.prcRuleHdrPk_H1.getValue());

        return getSsmEZDClient().queryEZDMsgArray("searchTblDef_data", params, bizMsg.C);
    }

    /**
     * searchTblDataCount
     * @param bizMsg NMAL7260CMsg
     * @param glblMsg NMAL7260SMsg
     * @return S21SsmEZDResult
     */
    public BigDecimal searchTblDataCount(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcRuleHdrPk", bizMsg.prcRuleHdrPk_H1.getValue());
        // 2018/05/25 QC#26031 Add Start
        params.put("highValDt", HIGH_VAL_DT);
        params.put("highValTm", HIGH_VAL_TM);
        // Add Start 2018/12/04 QC#29321
        params.put("slsDt", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        params.put("prcDispRecTpCd", bizMsg.prcDispRecTpCd_H1.getValue());
        // Add End 2018/12/04 QC#29321

        NMAL7260CommonLogic.setFilterParam(params, bizMsg);
        // 2018/05/25 QC#26031 Add End

        return (BigDecimal) getSsmEZDClient().queryObject("searchTblDataCount", params).getResultObject();
    }

    /**
     * searchTblData
     * @param bizMsg NMAL7260CMsg
     * @param glblMsg NMAL7260SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchTblData(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcRuleHdrPk", bizMsg.prcRuleHdrPk_H1.getValue());
        
        // Mod Start 2018/06/18 QC#22594
//        params.put("rowNum", glblMsg.B.length());
        params.put("rowNum", String.valueOf(GLBLMSG_MAX_LENGTH));
        // Mod End 2018/06/18 QC#22594
        // Add Start S21_NA QC#18237(Sol#161) 
        params.put("highValDt", HIGH_VAL_DT);
        params.put("highValTm", HIGH_VAL_TM);
        // QC#28433 2018/10/03 Add Start
        params.put("rate", NUM_100);
        for (int i=0; i < bizMsg.C.length(); i++) {
            String prcRuleAttrbCd = bizMsg.C.no(i).prcRuleAttrbCd_C1.getValue();
            params.put("attrb_" + prcRuleAttrbCd, "Y");
        }
        params.put("mdseStruElmntTpCd06", MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP);
        params.put("mdseStruElmntTpCd07", MDSE_STRU_ELMNT_TP.PRODUCT_LINE);
        params.put("prcGrpTpCd14", PRC_GRP_TP.ORDER_CATEGORY_OR_REASON);
        params.put("dsAcctTpCd16", DS_ACCT_TP.CUSTOMER);
        params.put("prcGrpCd23", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
        params.put("mdseStruElmntTpCd34", MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2);
        params.put("mdseStruElmntTpCd35", MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3);
        params.put("mdseStruElmntTpCd36", MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4);
        params.put("prcGrpTpCd53", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
        params.put("dsAcctTpCd54", DS_ACCT_TP.CUSTOMER);
        params.put("prcGrpTpCd57", PRC_GRP_TP.MATERIAL_PRICING_GROUP_QTY_BREAKS);
        params.put("slsMatGrpSqNum59", "1");
        params.put("slsMatGrpSqNum60", "2");
        params.put("slsMatGrpSqNum61", "3");
        // QC#28433 2018/10/03 Add End
        // Add Start 2018/12/04 QC#29321
        params.put("slsDt", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        params.put("prcDispRecTpCd", bizMsg.prcDispRecTpCd_H1.getValue());
        // Add End 2018/12/04 QC#29321

        NMAL7260CommonLogic.setFilterParam(params, bizMsg);
        // Add END S21_NA QC#18237(Sol#161) 

        return getSsmEZDClient().queryEZDMsgArray("searchTblData", params, glblMsg.B);
    }

    public S21SsmEZDResult searchTblDataQtyBrk(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, String prcRuleAttrbCd) {
        if(prcRuleAttrbCd == null){
            return null;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcRuleHdrPk", bizMsg.prcRuleHdrPk_H1.getValue());
        params.put("rowNum", glblMsg.T.length());
        params.put("highValDt", HIGH_VAL_DT);
        params.put("highValTm", HIGH_VAL_TM);
        if (PRC_RULE_ATTRB.LINE_QTY.equals(prcRuleAttrbCd)) {
            params.put("lineQty", ZYPConstant.FLG_ON_Y);
        }
        // Add Start 2018/12/04 QC#29321
        params.put("slsDt", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        params.put("prcDispRecTpCd", bizMsg.prcDispRecTpCd_H1.getValue());
        // Add End 2018/12/04 QC#29321

        NMAL7260CommonLogic.setFilterParam(params, bizMsg);

        return getSsmEZDClient().queryEZDMsgArray("searchTblDataQtyBrk", params, glblMsg.T);
    }
    /**
     * getPriceRuleAttributeList
     * @param glblCmpyCd String
     * @param prcAdjDispFlg String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPriceRuleAttributeList(String glblCmpyCd, String prcAdjDispFlg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcAdjDispFlg", prcAdjDispFlg);

        return getSsmEZDClient().queryObjectList("getPriceRuleAttribute", params);
    }

    /**
     * getDefRulePrcdNumData
     * @param prcRulecatgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefRulePrcdNumData(String prcRulecatgCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcRulecatgCd", prcRulecatgCd);

        return getSsmEZDClient().queryObject("getDefRulePrcdNum", params);
    }

    /**
     * getUsingRuleCondGrp.
     * @param prcRuleHdrPk BigDecimal
     * @param prcRuleTrxCondPk BigDecimal
     * @return S21SsmEZDResult
     */
    //    public S21SsmEZDResult getUsingRuleCondGrp(BigDecimal prcRuleHdrPk, BigDecimal prcRuleTrxCondPk) {
    //        Map<String, Object> params = new HashMap<String, Object>();
    //        params.put("glblCmpyCd", getGlobalCompanyCode());
    //        params.put("prcRuleHdrPk", prcRuleHdrPk);
    //        params.put("prcRuleTrxCondPk", prcRuleTrxCondPk);
    //
    //        return getSsmEZDClient().queryObject("getUsingRuleCondGrp", params);
    //    }
    /**
     * isExistRuleNm.
     * @param prcRuleHdrPk BigDecimal
     * @param prcRuleNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isExistRuleNm(BigDecimal prcRuleHdrPk, String prcRuleNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcRuleHdrPk", prcRuleHdrPk);
        params.put("prcRuleNm", prcRuleNm);

        return getSsmEZDClient().queryObject("isExistRuleNm", params);
    }

    /**
     * isExistsPrcRuleCmbnExcl.
     * @param bizMsg NMAL7260_BCMsg
     * @param prcRuleAttrbCd String
     * @param prcFmlaTpCd String
     * @param prcFuncTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isExistsPrcRuleCmbnExcl(NMAL7260CMsg bizMsg, String prcRuleAttrbCd, String prcFmlaTpCd, String prcFuncTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("all", ASTERISK);
        params.put("prcRuleModTpCd", bizMsg.prcRuleModTpCd_H1.getValue());
        params.put("prcRuleAdjTpCd", bizMsg.prcRuleAdjTpCd_H1.getValue());
        params.put("prcRuleAdjLvlCd", bizMsg.prcRuleAdjLvlCd_H1.getValue());
        params.put("prcRuleAttrbCd", prcRuleAttrbCd);
        params.put("prcFmlaTpCd", prcFmlaTpCd);
        params.put("prcFuncTpCd", prcFuncTpCd);

        return getSsmEZDClient().queryObject("isExistsPrcRuleCmbnExcl", params);
    }

    /**
     * Get count for detail rows(not include dummy row)
     * @param bizMsg NMAL7260CMsg
     * @return Integer
     */
    public Integer getCountTblData(NMAL7260CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcRuleHdrPk", bizMsg.prcRuleHdrPk_H1.getValue());

        return (Integer) getSsmEZDClient().queryObject("getCountTblData", params).getResultObject();
    }

    /**
     * searchTblData
     * @param bizMsg NMAL7260CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPriceAdjDtlForDelete(NMAL7260CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcRuleHdrPk", bizMsg.prcRuleHdrPk_H1.getValue());

        return getSsmEZDClient().queryObjectList("getPriceAdjDtlForDelete", params);
    }

    /**
     * getCountDefPrcd
     * @param bizMsg NMAL7260CMsg
     * @return Integer
     */
    public Integer getCountDefPrcd(NMAL7260CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("defRulePrcd", bizMsg.defRulePrcdNum_H1.getValue());
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);

        return (Integer) getSsmEZDClient().queryObject("getCountDefPrcd", params).getResultObject();
    }

    /**
     * getCountDefPrcd
     * @param mdseCd String
     * @return Integer
     */
    public S21SsmEZDResult getMdseNm(String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("colNm", "MDSE_DESC_SHORT_TXT");
        params.put("tblNm", "ALL_MDSE_V");
        params.put("whereCol", "MDSE_CD");
        params.put("whereVal", mdseCd);

        return getSsmEZDClient().queryObject("getAnyColmn", params);
    }

    // 2018/07/18 QC#20267 Add Start
    /**
     * getMdseInfo
     * @param mdseCd String
     * @return Integer
     */
    public S21SsmEZDResult getMdseInfo(String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getMdseInfo", params);
    }

    /**
     * get Base MDSE Code From Manufacturer Item Code
     * @param bizMsg NWAL1500CMsg
     * @param mnfItemCd Manufacturer Item Code
     * @return Base MDSE Code
     */
    public S21SsmEZDResult getBaseMdseCdFromMnfItemCd(NMAL7260CMsg bizMsg, String mnfItemCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("mnfItemCd", mnfItemCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getBaseMdseCdFromMnfItemCd", params); // S21_NA#9761 2016/09/28 MOD
    }
    // 2018/07/18 QC#20267 Add End

    /**
     * getAnyColmn.
     * @param colNm String
     * @param tblNm String
     * @param whereCol String
     * @param whereVal String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAnyColmn(String colNm, String tblNm, String whereCol, String whereVal) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("colNm", colNm);
        params.put("tblNm", tblNm);
        params.put("whereCol", whereCol);
        params.put("whereVal", whereVal);

        return getSsmEZDClient().queryObject("getAnyColmn", params);
    }

    public S21SsmEZDResult countRuleRange(Map<String, Object> params) {
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObject("countRuleRange", params);
    }
}
