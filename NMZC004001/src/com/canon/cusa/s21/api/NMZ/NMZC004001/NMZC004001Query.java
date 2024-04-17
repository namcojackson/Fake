/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC004001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.MDSETMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMZC004001Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/14/2016   Fujitsu         C.Tanaka        Create          N/A
 * 02/26/2016   SRAA            K.Aratani       Update          QC3767, QC4507, V1.5, V1.6
 * 05/18/2016   SRAA            K.Aratani       Update          QC#4203
 * 06/27/2016   SRAA            K.Aratani       Update          QC#9823, QC#10129
 * 04/03/2018   CITS            K.Ogino         Update          Sol#477(QC21587)
 * 08/09/2019   CITS            R.Shimamoto     Update          QC#52184
 * 04/07/2023   CITS            F.Komaki        Update          QC#61371
 *</pre>
 */
public final class NMZC004001Query extends S21SsmEZDQuerySupport {
    /** Singleton instance */
    private static final NMZC004001Query MY_INSTANCE = new NMZC004001Query();

    /** S21 SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Private constructor
     */
    private NMZC004001Query() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Get the NMZC004001Query instance.
     * @return NMZC004001Query instance
     */
    public static NMZC004001Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get CUSA Parts
     * @param cusaPartsTbl String
     * @param partsNum String
     * @return Map
     */
    public Map<String, Object> getCusaParts(String cusaPartsTbl, String partsNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cusaPartsTbl", cusaPartsTbl);
        params.put("partsNum", partsNum);
        return (Map<String, Object>) ssmBatchClient.queryObject("getCusaParts", params);
    }

    /**
     * Get CUSA MDSE
     * @param cusaGlblCmpyCd String
     * @param cusaMdseTbl String
     * @param mdseCd String
     * @return Map
     */
    public Map<String, Object> getCusaMdse(String cusaGlblCmpyCd, String cusaMdseTbl, String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cusaMdseTbl", cusaMdseTbl);
        params.put("cusaGlblCmpyCd", cusaGlblCmpyCd);
        params.put("mdseCd", mdseCd);
        return (Map<String, Object>) ssmBatchClient.queryObject("getCusaMdse", params);
    }

    /**
     * Get CUSA MDSE Safety Info
     * @param cusaGlblCmpyCd String
     * @param cusaSftyTbl String
     * @param mdseCd String
     * @return Map
     */
    public Map<String, Object> getCusaSfty(String cusaGlblCmpyCd, String cusaSftyTbl, String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cusaSftyTbl", cusaSftyTbl);
        params.put("cusaGlblCmpyCd", cusaGlblCmpyCd);
        params.put("mdseCd", mdseCd);
        return (Map<String, Object>) ssmBatchClient.queryObject("getCusaSfty", params);
    }

    /**
     * Get CUSA Composition
     * @param cusaGlblCmpyCd String
     * @param cusaCmpsnTbl String
     * @param mdseCd String
     * @param rowNum int
     * @return Map
     */
    public List<Map<String, Object>> getCusaSetList(String cusaGlblCmpyCd, String cusaMdseTbl, String cusaCmpsnTbl, String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cusaMdse", cusaMdseTbl);
        params.put("cusaCmpsnTbl", cusaCmpsnTbl);
        params.put("cusaGlblCmpyCd", cusaGlblCmpyCd);
        params.put("mdseCd", mdseCd);
        params.put("mdseTpCd", MDSE_TP.SET);
        
        return ssmBatchClient.queryObjectList("getCusaSetList", params);
    }

    /**
     * Get CUSA Composition
     * @param cusaGlblCmpyCd String
     * @param cusaCmpsnTbl String
     * @param mdseCd String
     * @param rowNum int
     * @return Map
     */
    public List<Map<String, Object>> getCusaCmpsnList(String cusaGlblCmpyCd, String cusaCmpsnTbl, String mdseCd, int rowNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cusaCmpsnTbl", cusaCmpsnTbl);
        params.put("cusaGlblCmpyCd", cusaGlblCmpyCd);
        params.put("mdseCd", mdseCd);
        params.put("rowNum", rowNum);
        return ssmBatchClient.queryObjectList("getCusaCmpsnList", params);
    }

    /**
     * Get CUSA Storage Package
     * @param cusaGlblCmpyCd String
     * @param cusaStoreTbl String
     * @param mdseCd String
     * @param pkgUomCd String
     * @return Map
     */
    public Map<String, Object> getCusaStore(String cusaGlblCmpyCd, String cusaStoreTbl, String mdseCd, String pkgUomCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cusaStoreTbl", cusaStoreTbl);
        params.put("cusaGlblCmpyCd", cusaGlblCmpyCd);
        params.put("mdseCd", mdseCd);
        params.put("pkgUomCd", pkgUomCd);
        return (Map<String, Object>) ssmBatchClient.queryObject("getCusaStore", params);
    }

    /**
     * Get CUSA Serial Number
     * @param cusaGlblCmpyCd String
     * @param cusaSerNumTbl String
     * @param mdseCd String
     * @param rowNum int
     * @return List
     */
    public List<Map<String, Object>> getCusaSerNumList(String cusaGlblCmpyCd, String cusaSerNumTbl, String mdseCd, int rowNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cusaSerNumTbl", cusaSerNumTbl);
        params.put("cusaGlblCmpyCd", cusaGlblCmpyCd);
        params.put("mdseCd", mdseCd);
        params.put("rowNum", rowNum);
        return ssmBatchClient.queryObjectList("getCusaSerNumList", params);
    }

    /**
     * Get AMER_MSTR and AMER_CMPY
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return Map
     */
    public Map<String, Object> getAmerMstrCmpy(String glblCmpyCd, String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        return (Map<String, Object>) ssmBatchClient.queryObject("getAmerMstrCmpy", params);
    }

    /**
     * Get Merchandise Structure
     * @param glblCmpyCd String
     * @param prodCtrlCd String[]
     * @return Map
     */
    public Map<String, Object> getMdseStru(String glblCmpyCd, String[] prodCtrlCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("zerothProdCtrlCd", prodCtrlCd[0]);
        params.put("firstProdCtrlCd", prodCtrlCd[1]);
        params.put("scdProdCtrlCd", prodCtrlCd[2]);
        params.put("thirdProdCtrlCd", prodCtrlCd[3]);
        params.put("frthProdCtrlCd", prodCtrlCd[4]);
        return (Map<String, Object>) ssmBatchClient.queryObject("getMdseStru", params);
    }

    /**
     * Check if ASL is duplicated
     * @param params Map
     * @return boolean
     */
    public boolean isDupAsl(Map<String, Object> params) {
        boolean dup = false;
        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("isDupAsl", params);
        if (map != null) {
            dup = true;
        }
        return dup;
    }

    /**
     * Check if ASL is duplicated
     * @param glblCmpyCd String
     * @param prntVndCd String
     * @param vndCd String
     * @return boolean
     */
    public boolean existPrimVnd(String glblCmpyCd, String prntVndCd, String vndCd) {
        boolean exist = false;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prntVndCd", prntVndCd);
        params.put("vndCd", vndCd);
        params.put("rgtnStsCd10", RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING);
        params.put("rgtnStsCd20", RGTN_STS.READY_FOR_ORDER_TAKING);

        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("existPrimVnd", params);
        if (map != null) {
            exist = true;
        }
        return exist;
    }

    /**
     * Check if exist second Hierarchy (10 length to 10 length)
     * @param glblCmpyCd String
     * @param prntMdseCd String
     * @return boolean
     */
    public boolean existCmpsnHierarchy10(String glblCmpyCd, String prntMdseCd) {
        boolean exist = false;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prntMdseCd", prntMdseCd);

        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("existCmpsnHierarchy10", params);
        if (map != null) {
            exist = true;
        }
        return exist;
    }

    /**
     * Check if exist second Hierarchy (8 length to 10 length)
     * @param glblCmpyCd String
     * @param ordTakeMdseCd String
     * @return boolean
     */
    public boolean existCmpsnHierarchy8to10(String glblCmpyCd, String ordTakeMdseCd) {
        boolean exist = false;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ordTakeMdseCd", ordTakeMdseCd);

        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("existCmpsnHierarchy8to10", params);
        if (map != null) {
            exist = true;
        }
        return exist;
    }

    /**
     * Check if exist second Hierarchy (8 length to 8 length)
     * @param glblCmpyCd String
     * @param ordTakeMdseCd String
     * @return boolean
     */
    public boolean existCmpsnHierarchy8(String glblCmpyCd, String ordTakeMdseCd) {
        boolean exist = false;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ordTakeMdseCd", ordTakeMdseCd);

        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("existCmpsnHierarchy8", params);
        if (map != null) {
            exist = true;
        }
        return exist;
    }

    /**
     * Check if exist second Hierarchy (10 length to 8 length)
     * @param glblCmpyCd String
     * @param prntMdseCd String
     * @return boolean
     */
    public boolean existCmpsnHierarchy10to8(String glblCmpyCd, String prntMdseCd) {
        boolean exist = false;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prntMdseCd", prntMdseCd);

        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("existCmpsnHierarchy10to8", params);
        if (map != null) {
            exist = true;
        }
        return exist;
    }

    /**
     * Check if exist CUSA Component
     * @param params Map
     * @return boolean
     */
    public boolean existCusaCmpsn(Map<String, Object> params) {
        boolean exist = false;
        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("existCusaCmpsn", params);
        if (map != null) {
            exist = true;
        }
        return exist;
    }

    /**
     * Check if exist Price Category
     * @param glblCmpyCd String
     * @param slsDt String
     * @param prcCatgCd String
     * @param prcListTpCd String
     * @return boolean
     */
    public boolean existPrcCatg(String glblCmpyCd, String slsDt, String prcCatgCd, String prcListTpCd) {
        boolean exist = false;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", prcCatgCd);
        params.put("slsDt", slsDt);
        params.put("prcListTpCd", prcListTpCd);

        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("existPrcCatg", params);
        if (map != null) {
            exist = true;
        }
        return exist;
    }

    /**
     * Get Price List Type
     * @param glblCmpyCd String
     * @param slsDt String
     * @param prcCatgCd String
     * @return Map
     */
    public Map<String, Object> getPrcListStruTpCd(String glblCmpyCd, String slsDt, String prcCatgCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", prcCatgCd);
        params.put("slsDt", slsDt);
        params.put("prcListStruTpCd", PRC_LIST_STRU_TP.EQUIPMENT);

        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getPrcListStruTpCd", params);
        return map;
    }

    /**
     * Get Model ID
     * @param glblCmpyCd String
     * @param svcMdlNm String
     * @return Map
     */
    public Map<String, Object> getMdlId(String glblCmpyCd, String svcMdlNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdlNm", svcMdlNm);

        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getMdlId", params);
        return map;
    }

    /**
     * Get Price List Equipment
     * @param glblCmpyCd String
     * @param slsDt String
     * @param prcCatgCd String
     * @param prcQltyValTxt String
     * @param current String
     * @return Map
     */
    public Map<String, Object> getPrcListEquip(String glblCmpyCd, String slsDt, String prcCatgCd, String prcQltyValTxt, String current) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", prcCatgCd);
        params.put("prcQlfyTpCd", PRC_QLFY_TP.ITEM_CODE);
        params.put("prcQltyValTxt", prcQltyValTxt);
        params.put("slsDt", slsDt);
        params.put("current", current);

        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getPrcListEquip", params);
        return map;
    }
    //QC3767
    public Map<String, Object> getDefaultItemStatus(String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("defFlg", "Y");
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getItemStatusList", ssmParam);
		if (result.isCodeNormal()) {
			Map<String, Object> map = (Map<String, Object>) result.getResultObject();
			return map;
		}
		return null;
        
    }

    //V1.5
    public Map<String, Object> getSlsMatGrp(String glblCmpyCd, String slsMatGrpCd, String slsMatGrpSqNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsMatGrpCd", slsMatGrpCd);
        ssmParam.put("slsMatGrpSqNum", slsMatGrpSqNum);
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getSlsMatGrp", ssmParam);
		if (result.isCodeNormal()) {
			Map<String, Object> map = (Map<String, Object>) result.getResultObject();
			return map;
		}
        return null;
    }
    
    //V1.5
    public Map<String, Object> getDsCmsnGrp(String glblCmpyCd, String dsCmsnGrpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsCmsnGrpCd", dsCmsnGrpCd);
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getDsCmsnGrp", ssmParam);
		if (result.isCodeNormal()) {
			Map<String, Object> map = (Map<String, Object>) result.getResultObject();
			return map;
		}
        return null;
    }
    //QC#4203, QC#10449
    public Map<String, Object> getRtrnCtrlTpVndReln(String glblCmpyCd, String rtrnCtrlTpCd, String rtrnToVndCd, String rtrnWhCd, String slsDt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtrnCtrlTpCd", rtrnCtrlTpCd);
        params.put("rtrnToVndCd", rtrnToVndCd);
        params.put("rtrnWhCd", rtrnWhCd);
        params.put("slsDt", slsDt);

        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getRtrnCtrlTpVndReln", params);
        return map;
    }
    //QC#4203
    public Map<String, Object> getPoVndV(String glblCmpyCd, String rtrnToVndCd, String rtrnToPrntVndCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtrnToVndCd", rtrnToVndCd);
        params.put("rtrnToPrntVndCd", rtrnToPrntVndCd);

        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getPoVndV", params);
        return map;
    }
    
    public Map<String, Object> getMdseTpValSet(String glblCmpyCd, String mdseTpCtxTpCd, String mdseItemTpCd, String coaMdseTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseTpCtxTpCd", mdseTpCtxTpCd);
        params.put("mdseItemTpCd", mdseItemTpCd);
        params.put("coaMdseTpCd", coaMdseTpCd);
        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getMdseTpValSet", params);
        return map;
    }
    //QC#10449
    public Map<String, Object> getRtlWh(String glblCmpyCd, String rtlWhCd, String slsDt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtlWhCd", rtlWhCd);
        params.put("slsDt", slsDt);
        return (Map<String, Object>) ssmBatchClient.queryObject("getRtlWh", params);
    }

    public Map<String, Object> getRtlWhSubWh(String glblCmpyCd, String rtlWhCd, String rtlSwhCd, String slsDt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtlWhCd", rtlWhCd);
        params.put("rtlSwhCd", rtlSwhCd);
        params.put("slsDt", slsDt);
        return (Map<String, Object>) ssmBatchClient.queryObject("getRtlWhSubWh", params);
    }
    //QC#9823, QC#10129
    public S21SsmEZDResult getCmpsn(String glblCmpyCd, String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObject("getCmpsn", params);
    }
    //QC#16628
    public S21SsmEZDResult getPkgUomAndPkgUomClsReln(String glblCmpyCd, String pkgUomClsCd, String pkgUomCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("pkgUomClsCd", pkgUomClsCd);
        params.put("pkgUomCd", pkgUomCd);
        return getSsmEZDClient().queryObject("getPkgUomAndPkgUomClsReln", params);
    }
    // Sol#477(QC#21587)
    public List<Map<String, String>> getMrpDefItemPln(String glblCmpyCd, String slsDt, MDSETMsg mdseTMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("coaProjCd", mdseTMsg.coaMdseTpCd.getValue());
        params.put("lineBizTpCd", mdseTMsg.lineBizTpCd.getValue());
        params.put("firstProdCtrlCd", mdseTMsg.firstProdCtrlCd.getValue());
        params.put("slsDt", slsDt);
        return ssmBatchClient.queryObjectList("getMrpDefItemPln", params);
    }
    public BigDecimal getMrpInfoPk(String glblCmpyCd, String mrpPlnNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mrpPlnNm", mrpPlnNm);
        return (BigDecimal) ssmBatchClient.queryObject("getMrpInfoPk", params);
    }
    // QC#52184
    public String getSupplierName(String glblCmpyCd, String prntVndCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prntVndCd", prntVndCd);
        return (String) ssmBatchClient.queryObject("getSupplierName", params);
    }

    // 2023/04/07 QC#61371 START
    /**
     * Get Supplier Item Code Max Length
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSupplierItemCodeMaxLength(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getSupplierItemCodeMaxLength", ssmParam);
    }
    // 2023/04/07 QC#61371 END
}
