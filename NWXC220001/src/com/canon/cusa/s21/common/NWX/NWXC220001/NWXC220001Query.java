package com.canon.cusa.s21.common.NWX.NWXC220001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.XTRNL_INTFC_XREFTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

public class NWXC220001Query {

    /**
     * Singleton instance.
     */

    private static final NWXC220001Query MY_INSTANCE = new NWXC220001Query();

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /**
     * @return MY_INSTANCE
     */
    protected static NWXC220001Query getInstance() {
        return MY_INSTANCE;
    }

    public List< ? > getEdiAttrb(String glblCmpyCd, BigDecimal dsImptOrdPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsImptOrdPk", dsImptOrdPk);

        return ssmBatchClient.queryObjectList("getEdiAttrb", ssmParam);
    }

    public List< ? > getShpgSvcLvlCarrReln(String glblCmpyCd, String shpgSvcLvlCd, String carrSvcLvlCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
        ssmParam.put("carrSvcLvlCd", carrSvcLvlCd);

        return ssmBatchClient.queryObjectList("getShpgSvcLvlCarrReln", ssmParam);
    }

    public List< ? > getXtrnlIntfcXref(XTRNL_INTFC_XREFTMsg inTMsg, List<String> srcAttrbList) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", inTMsg.glblCmpyCd.getValue());
        ssmParam.put("cpoSrcTpCd", inTMsg.cpoSrcTpCd.getValue());
        ssmParam.put("intfcId", inTMsg.intfcId.getValue());
        ssmParam.put("intfcXrefCtxTpCd", inTMsg.intfcXrefCtxTpCd.getValue());

        for (int i = 1; i <= srcAttrbList.size(); i++) {
            ssmParam.put("srcAttrbTxt0" + i, srcAttrbList.get(i - 1));
        }

        return ssmBatchClient.queryObjectList("getXtrnlIntfcXref", ssmParam);
    }

    public String getPmtTermCashDiscCd1(String glblCmpyCd, String billToCustCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustCd", billToCustCd);

        return (String) ssmBatchClient.queryObject("getPmtTermCashDiscCd1", ssmParam);
    }

    public String getPmtTermCashDiscCd2(String glblCmpyCd, String billToCustAcctCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", billToCustAcctCd);

        return (String) ssmBatchClient.queryObject("getPmtTermCashDiscCd2", ssmParam);
    }

    public List< ? > getDuplicatePo(String sqlNm, String glblCmpyCd, String ordNum, String custIssPoNum, String ordHdrStsCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordNum", NWXC220001Util.convToString(ordNum, ""));
        ssmParam.put("custIssPoNum", NWXC220001Util.convToString(custIssPoNum, ""));
        ssmParam.put("ordHdrStsCd", NWXC220001Util.convToString(ordHdrStsCd, ""));

        return (List< ? >) ssmBatchClient.queryObjectList(sqlNm, ssmParam);
    }

    public List< ? > getDefMdse(String sqlNm, String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", NWXC220001Util.convToString(glblCmpyCd, ""));
        ssmParam.put("mdseCd", NWXC220001Util.convToString(mdseCd, ""));

        return (List< ? >) ssmBatchClient.queryObjectList(sqlNm, ssmParam);
    }

    // Add Start 2017/10/25 QC#22012
    public List< ? > getDefMdseFromCustMdseXref(String glblCmpyCd, String custMdseCd, String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", NWXC220001Util.convToString(glblCmpyCd, ""));
        ssmParam.put("custMdseCd", NWXC220001Util.convToString(custMdseCd, ""));
        ssmParam.put("dsAcctNum", NWXC220001Util.convToString(dsAcctNum, ""));

        return (List< ? >) ssmBatchClient.queryObjectList("getDefMdseFromCustMdseXref", ssmParam);
    }
    // Add End 2017/10/25 QC#22012

    public List<Map<String, Object>> getDsOrdLineCatgList(String glblCmpyCd, String dsOrdTpCd, String effDt, String dsOrdLineDrctnCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", NWXC220001Util.convToString(glblCmpyCd, ""));
        ssmParam.put("dsOrdTpCd", NWXC220001Util.convToString(dsOrdTpCd, ""));
        ssmParam.put("effDt", NWXC220001Util.convToString(effDt, ""));
        ssmParam.put("dsOrdLineDrctnCd", NWXC220001Util.convToString(dsOrdLineDrctnCd, ""));
        ssmParam.put("actvFlg", ZYPConstant.FLG_ON_Y);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDsOrdLineCatgList", ssmParam);
    }

    public String getDefDsOrdTpProcDfnPk(DS_ORD_TP_PROC_DFNTMsg tMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", tMsg.glblCmpyCd.getValue());
        ssmParam.put("dsOrdTpCd", tMsg.dsOrdTpCd.getValue());
        ssmParam.put("effFromDt", tMsg.effFromDt.getValue());
        ssmParam.put("effThruDt", tMsg.effThruDt.getValue());

        return (String) ssmBatchClient.queryObject("getDefDsOrdTpProcDfnPk", ssmParam);
    }

    // 09/27/2016 S21_NA#14744 add Start
    public String getDsMdseStorePkgUomCd(String glblCmpyCd, String basePkgUomCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("basePkgUomCd", basePkgUomCd);
        ssmParam.put("mdseCd", mdseCd);

        return (String) ssmBatchClient.queryObject("getDsMdseStorePkgUomCd", ssmParam);
    }
    // 09/27/2016 S21_NA#14744 add End

    public List<Map<String, Object>> getDuplicateDsImptOrdList(Map<String, Object> ssmParam) {

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDuplicateDsImptOrdList", ssmParam);
    }

    public List<Map<String, Object>> getDsImptPkList(Map<String, Object> ssmParam) {

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDsImptPkList", ssmParam);
    }

    public List< ? > getValidDsOrdCatgCdList(Map<String, Object> ssmParam) {
        return (List< ? >) ssmBatchClient.queryObjectList("getValidDsOrdCatgCdList", ssmParam);
    }

    public Map<String, Object> getBaseComponentFlag(Map<String, Object> ssmParam) {

        return (Map<String, Object>) ssmBatchClient.queryObject("getBaseComponentFlag", ssmParam);
    }

    public Map<String, Object> getOrdProcTpInfo(Map<String, Object> ssmParam) {

        return (Map<String, Object>) ssmBatchClient.queryObject("getOrdProcTpInfo", ssmParam);
    }

    public BigDecimal getCostPctInfo(Map<String, Object> ssmParam) {

        return (BigDecimal) ssmBatchClient.queryObject("getCostPctInfo", ssmParam);
    }

    public Map<String, Object> getMdlIdNmBySvcConfigMstr(Map<String, Object> ssmParam) {

        return (Map<String, Object>) ssmBatchClient.queryObject("getMdlIdNmBySvcConfigMstr", ssmParam);
    }

    public Map<String, Object> getSlsRepInfo(Map<String, Object> ssmParam) {

        return (Map<String, Object>) ssmBatchClient.queryObject("getSlsRepInfo", ssmParam);
    }

    public Integer isExistOrdCatg(Map<String, Object> ssmParam) {

        return (Integer) ssmBatchClient.queryObject("isExistOrdCatg", ssmParam);
    }
}
