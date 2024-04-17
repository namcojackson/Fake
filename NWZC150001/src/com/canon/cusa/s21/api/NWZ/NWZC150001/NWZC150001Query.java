/**<pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.api.NWZ.NWZC150001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CPO_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_rtnDtlPMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DI_CHK_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_PROC_NODE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MDL_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * DS CPO Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/28   Fujitsu         Hd.Sugawara     Update          S21_NA#29252
 * 2019/02/22   Fujitsu         K.Ishizuka      Update          S21_NA#30449
 * 2019/10/02   Fujitsu         M.Ohno          Update          S21_NA#52988
 * 2023/05/11   Hitachi         T.Doi           Update          CSA-QC#61246
 * 2023/06/09   Hitachi         A.Kohinata      Update          QC#61337
 * 2023/09/05   Hitachi         K.Watanabe      Update          QC#53408
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 * 2024/03/14   CITS            J.Cho           Update          QC#63527
 * </pre>
 */
public class NWZC150001Query {

    /**
     * Singleton instance.
     */
    private static final NWZC150001Query myInstance = new NWZC150001Query();

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /**  Cache for getConvProcStsCd() */
    private Map<Map<String, Object>, BigDecimal> getConvProcStsCdCache = new HashMap<Map<String, Object>, BigDecimal>();

    /** Cache for getInBoundLineCatg() */
    private Map<Map<String, String>, String> getInBoundLineCatgCache = new HashMap<Map<String, String>, String>();

    public static NWZC150001Query getInstance() {
        return myInstance;
    }

    public List<Map<String, String>> getForceDummyWhList(NWZC150001PMsg dsCpoUpdPMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", dsCpoUpdPMsg.glblCmpyCd.getValue());
        ssmParam.put("forceDummyWh", "FORCE_DUMMY_WH");

        @SuppressWarnings("unchecked")
        List<Map<String, String>> mapList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getForceDummyWhList", ssmParam);

        return mapList;
    }

    public String getDefaultCarrCd(NWZC150001PMsg pMsg, NWZC150001_APMsg aPMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("carrSvcLvlCd", aPMsg.carrSvcLvlCd_A1.getValue());
        ssmParam.put("rownum", "1");

        return (String) ssmBatchClient.queryObject("getDefaultCarrCd", ssmParam);
    }

    protected String getDefaultCarrSvcLvl(NWZC150001PMsg pMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
        ssmParam.put("slsDt", pMsg.slsDt.getValue());
        ssmParam.put("frtCondCd", pMsg.frtCondCd.getValue());
        ssmParam.put("shpgSvcLvlCd", pMsg.addShpgSvcLvlCd.getValue());
        ssmParam.put("rownum", "1");

        return (String) ssmBatchClient.queryObject("getDefaultCarrSvcLvl", ssmParam);
    }

    protected List<String> getCoaMdseTpList(NWZC150001PMsg pMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        // TODO
        // ssmParam.put("mainMach", MDSE_TP_CTX_TP.MAIN_MACH);
        ssmParam.put("mainMach", "MAIN_MACH");

        @SuppressWarnings("unchecked")
        List<String> list = (List<String>) ssmBatchClient.queryObjectList("getCoaMdseTpList", ssmParam);
        return list;
    }

    protected String getDefPrcCatgCd(String glblCmpyCd, String dsOrdTpCd, String slsDt) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("slsDt", slsDt);

        return (String) ssmBatchClient.queryObject("getDefPrcCatgCd", ssmParam);
    }

    public Map<String, String> getDefaultTrxCd(//
            NWZC150001PMsg pMsg //
            , String dsOrdLineCatgCd //
            , Map<Map<String, String> //
            , Map<String, String>> cacheMap) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
        ssmParam.put("dsOrdLineCatgCd", dsOrdLineCatgCd);

        if (cacheMap.containsKey(ssmParam)) {
            return cacheMap.get(ssmParam);
        }
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getDefaultTrxCd", ssmParam);
        cacheMap.put(ssmParam, map);

        return map;
    }

    public String getDefaultRetailSubWH(NWZC150001PMsg pMsg, NWZC150001_APMsg aPMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("rtlWhCd", aPMsg.rtlWhCd_A1.getValue());
        ssmParam.put("slsDt", pMsg.slsDt.getValue());
        ssmParam.put("prtyLocFlg", ZYPConstant.FLG_ON_Y);

        return (String) ssmBatchClient.queryObject("getDefaultRetailSubWH", ssmParam);
    }

    public BigDecimal getSvcMachMstrPk(String glblCmpyCd, String serNum, String mdseCd) {
        // 2016/06/02 S21_NA#9273 Add Start
        if (!ZYPCommonFunc.hasValue(serNum) && !ZYPCommonFunc.hasValue(mdseCd)) {
            return null;
        }
        // 2016/06/02 S21_NA#9273 Add End
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("serNum", serNum);
        ssmParam.put("mdseCd", mdseCd + "%");

        return (BigDecimal) ssmBatchClient.queryObject("getSvcMachMstrPk", ssmParam);
    }

    public Map<String, String> getDefaultShipToInfo(String glblCmpyCd, String shipToCustCd, Map<Map<String, String>, Map<String, String>> cacheMap) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shipToCustCd", shipToCustCd);

        if (cacheMap.containsKey(ssmParam)) {
            return cacheMap.get(ssmParam);
        }
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getDefaultShipToInfo", ssmParam);
        cacheMap.put(ssmParam, map);

        return map;
    }

    public Map<String, String> getMdseItemStsInfo(String glblCmpyCd, String mdseCd, Map<Map<String, String>, Map<String, String>> cacheMap) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd + "%");

        if (cacheMap.containsKey(ssmParam)) {
            return cacheMap.get(ssmParam);
        }
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getMdseItemStsInfo", ssmParam);
        cacheMap.put(ssmParam, map);

        return map;
    }

    public Map<String, String> getOrdProcTpInfo(//
            String glblCmpyCd //
            , String dsOrdTpCd //
            , String dsOrdLineCatgCd //
            , String slsDt //
            , Map<Map<String, String>, Map<String, String>> cacheMap) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("dsOrdLineCatgCd", dsOrdLineCatgCd);
        ssmParam.put("slsDt", slsDt);

        if (cacheMap != null && cacheMap.containsKey(ssmParam)) { // S21_NA#8018
            return cacheMap.get(ssmParam);
        }
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getOrdProcTpInfo", ssmParam);
        if (cacheMap != null) { // S21_NA#8018
            cacheMap.put(ssmParam, map);
        }

        return map;
    }

    public BigDecimal getCostPctInfo(String glblCmpyCd, String rtlSwhCd, String slsDt, Map<Map<String, String>, BigDecimal> cacheMap) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlSwhCd", rtlSwhCd);
        ssmParam.put("slsDt", slsDt);

        if (cacheMap.containsKey(ssmParam)) {
            return cacheMap.get(ssmParam);
        }
        BigDecimal costPct = (BigDecimal) ssmBatchClient.queryObject("getCostPctInfo", ssmParam);
        cacheMap.put(ssmParam, costPct);

        return costPct;
    }

    protected List<CPO_DTLTMsg> getDsCpoDtlByCpoOrdNumAndDsOrdPosnNum(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg cpoConfigPMsg) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", pMsg.cpoOrdNum.getValue());
        queryParam.put("dsOrdPosnNum", cpoConfigPMsg.dsOrdPosnNum.getValue());

        List<Map<String, String>> rsltList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getDsCpoDtlByCpoOrdNumAndDsOrdPosnNum", queryParam);
        List<CPO_DTLTMsg> dsCpoDtlTMsgList= new ArrayList<CPO_DTLTMsg>(0);
        for(Map<String, String> rslt : rsltList) {
            CPO_DTLTMsg dsCpoDtlTMsg = new CPO_DTLTMsg();
            dsCpoDtlTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
            dsCpoDtlTMsg.cpoOrdNum.setValue(rslt.get("CPO_ORD_NUM"));
            dsCpoDtlTMsg.cpoDtlLineNum.setValue(rslt.get("CPO_DTL_LINE_NUM"));
            dsCpoDtlTMsg.cpoDtlLineSubNum.setValue(rslt.get("CPO_DTL_LINE_SUB_NUM"));
            dsCpoDtlTMsgList.add(dsCpoDtlTMsg);
        }
        return dsCpoDtlTMsgList;
    }

    protected List<DS_CPO_RTRN_DTLTMsg> getDsCpoRtrnDtlByCpoOrdNumAndDsOrdPosnNum(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg cpoConfigPMsg) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", pMsg.cpoOrdNum.getValue());
        queryParam.put("dsOrdPosnNum", cpoConfigPMsg.dsOrdPosnNum.getValue());

        List<Map<String, String>> rsltList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getDsCpoRtrnDtlByCpoOrdNumAndDsOrdPosnNum", queryParam);
        List<DS_CPO_RTRN_DTLTMsg> dsCpoRtrnDtlTMsgList= new ArrayList<DS_CPO_RTRN_DTLTMsg>(0);
        for(Map<String, String> rslt : rsltList) {
            DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
            dsCpoRtrnDtlTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
            dsCpoRtrnDtlTMsg.cpoOrdNum.setValue(rslt.get("CPO_ORD_NUM"));
            dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum.setValue(rslt.get("DS_CPO_RTRN_LINE_NUM"));
            dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.setValue(rslt.get("DS_CPO_RTRN_LINE_SUB_NUM"));
            dsCpoRtrnDtlTMsgList.add(dsCpoRtrnDtlTMsg);
        }
        return dsCpoRtrnDtlTMsgList;
    }

    // 2018/05/20 S21_NA#25604 Del Start
//    /**
//     * check Exist Order Category
//     * @param pMsg NWZC150001PMsg
//     * @param ordCatgCtxTpCd Order Category Context Type Code
//     * @return true: exist
//     */
//    public boolean isExistOrdCatg(NWZC150001PMsg pMsg, String ordCatgCtxTpCd) {
//
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        params.put("ordCatgCtxTpCd", ordCatgCtxTpCd);
//        params.put("dsOrdCatgCd", pMsg.dsOrdCatgCd.getValue());
//        params.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
//        params.put("dsOrdRsnCd", pMsg.dsOrdRsnCd.getValue());
//
//        return 0 < (Integer) ssmBatchClient.queryObject("isExistOrdCatg", params);
//    }
    // 2018/05/20 S21_NA#25604 Del End

    // 2016/03/16 S21_NA#5519 Add Start
    /**
     * <pre>
     * get config mstr pk related to Serial Number List.
     * If there are 2 or more config master pk, this method return true.
     * other case return false;
     * @param pMsg API Message
     * @param serNumList Serial Number List
     * @return true: multiple service config master pk false: single service config master pk
     * </pre>
     */
    public boolean isMultipleConfigId(NWZC150001PMsg pMsg, List<Map<String, String>>serNumList) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        params.put("serNumList", serNumList);

        List<BigDecimal> svcConfigMstrPkList = (List<BigDecimal>) ssmBatchClient.queryObjectList("isMultipleConfigId", params);
        return 1 < svcConfigMstrPkList.size();
    }
    // 2016/03/16 S21_NA#5519 Add End
    // 2016/04/18 S21_NA#5321-2 Add Start
    /**
     * <pre>
     * Get Model ID and Model Desc Text from SVC_CONFIG_MSTR and DS_MDL
     * @param glblCmpyCd Global Company Code
     * @param svcConfigMstrPk Service Config Master PK
     * @return MAP<String, Object> "MDL_ID": Model Id, "MDL_DESC_TXT": Model Description Text
     *  If there is no record, return NULL
     *  </pre>
     */
    public Map<String, Object> getMdlIdNmBySvcConfigMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcConfigMstrPk", svcConfigMstrPk);

        return (Map<String, Object>) ssmBatchClient.queryObject("getMdlIdNmBySvcConfigMstr", params);
    }
    // 2016/04/18 S21_NA#5321-2 Add End

    // 2016/04/19 Add #7263 Start
    /**
     * <pre>
     * Get CPO_DTL Count without Cacelled Line Order
     * @param glblCmpyCd Global Company Code
     * @param cpoOrdNum  CPO_ORD_NUM
     * @return BigDecimal CPO_DTL Count
     *  If there is no record, return NULL
     *  </pre>
     */
    public BigDecimal getCpoDtlCount(NWZC150001PMsg pMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", pMsg.cpoOrdNum.getValue());
        params.put("ordLineStsCd", ORD_LINE_STS.CANCELLED);
        return (BigDecimal) ssmBatchClient.queryObject("getCpoDtlCount", params);
    }
    // 2016/04/19 Add #7263 End

    // 2016/05/09 Add S21_NA#7750 Start
    /**
     * getComponentExistsFlg
     * @param dsCpoUpdPMsg NWZC150001PMsg
     * @return List<Map<String, String>>
     */
    public List<Map<String, String>> getComponentExistsFlg(NWZC150001PMsg dsCpoUpdPMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", dsCpoUpdPMsg.glblCmpyCd.getValue());
        ssmParam.put("ComponentExistsFlg", "COMPONENT_EXISTS_FLG");

        List<Map<String, String>> mapList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getComponentExistsFlg", ssmParam);

        return mapList;
    }
    // 2016/05/09 Add S21_NA#7750 End

    // 2016/07/12 S21_NA#9426-4 Add Start
    /**
     * get shpg_pln data count
     * @param pMsg NWZC150001PMsg. Using Global Company Code and cpoOrdNum
     * @param shpgStsCd Shipping Plan Status Code for counting shpg_pln
     * @return counting number of shpg_pln data.
     */
    public BigDecimal getShpgPlnCountByStatus(NWZC150001PMsg pMsg, String shpgStsCd) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", pMsg.cpoOrdNum.getValue());
        params.put("shpgStsCd", shpgStsCd);
        return (BigDecimal) ssmBatchClient.queryObject("getShpgPlnCountByStatus", params);
    }
    // 2016/07/12 S21_NA#9426-4 Add End

    // 2016/07/21 S21_NA#9228 Add Start
    /**
     * <pre>
     * Get Order Amout: CPO_V.ORD_TOT_DEAL_NET_AMT
     * </pre>
     * @param pMsg DS CPO Update API PMessage
     * @return Map<String, Object>:
     *  Key: ORD_HDR_STS_CD (Value->Order Header Status Code)
     *       ORD_TOT_DEAL_NET_AMT (Value->Order Total Deal Net Amount)
     */
    public Map<String, Object> getOrdTotDealNetAmt(NWZC150001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", pMsg.cpoOrdNum.getValue());

        return (Map<String, Object>) ssmBatchClient.queryObject("getOrdTotDealNetAmt", ssmParam);
    }
    // 2016/07/21 S21_NA#9228 Add End

    /**
     * <pre>
     * get ITT out bound hold for release list
     * </pre>
     * @param ssmParam
     * @return ListMap<String, Object> : ITT out bound hold for
     * release list
     */
    public List<Map<String, Object>> getITTOutboundHoldListForRelease(Map<String, Object> ssmParam) { // S21_NA#11280

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getITTOutboundHoldListForRelease", ssmParam);
    }

    // 2016/08/10 S21_NA#5394 Add Start
    /**
     * get workflow hold count
     * @param pMsg DS CPO Update API PMessage
     * @return workflow hold count
     */
    public BigDecimal getWfTargetCount(NWZC150001PMsg pMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",  pMsg.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum",   pMsg.cpoOrdNum.getValue());
        ssmParam.put("slsDt",       pMsg.slsDt.getValue());
        ssmParam.put("relFlgN",     ZYPConstant.FLG_OFF_N);

        List<String> ordProcNodeCdList = new ArrayList<String>(0);
        ordProcNodeCdList.add(ORD_PROC_NODE.VALIDATION_APPROVAL);
        ordProcNodeCdList.add(ORD_PROC_NODE.PROFITABILITY_OR_APPRV);
        ordProcNodeCdList.add(ORD_PROC_NODE.SUPPLY_ABUSE);
        ordProcNodeCdList.add(ORD_PROC_NODE.CREDIT_APPROVAL);
        ordProcNodeCdList.add(ORD_PROC_NODE.BUYOUT_APPROVAL);
        // add start 2023/06/09 QC#61337
        ordProcNodeCdList.add(ORD_PROC_NODE.MAN_PRC_APPROVAL);
        // add end 2023/06/09 QC#61337

        ssmParam.put("ordProcNodeCdList",     ordProcNodeCdList);

        return (BigDecimal) ssmBatchClient.queryObject("getWfTarget", ssmParam);
    }
    // 2016/08/10 S21_NA#5394 Add End

    // S21_NA#11630 ADD START
    public BigDecimal isEnableToCancelForOutbound(NWZC150001PMsg pMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("trxHdrNum", pMsg.reBillPairCpoOrdNum.getValue());
        List<String> shpgStsCdList = new ArrayList<String>();
        shpgStsCdList.add(SHPG_STS.SAVED);
        shpgStsCdList.add(SHPG_STS.VALIDATED);
        shpgStsCdList.add(SHPG_STS.CANCELLED);
        shpgStsCdList.add(SHPG_STS.P_OR_O_CANCELLED);
        ssmParam.put("shpgStsCdList", shpgStsCdList);

        return (BigDecimal) ssmBatchClient.queryObject("isEnableToCancelForOutbound", ssmParam);
    }

    public BigDecimal isEnableToCancelForInbound(NWZC150001PMsg pMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", pMsg.reBillPairCpoOrdNum.getValue());
        List<String> rtrnLineStsCdList = new ArrayList<String>();
        rtrnLineStsCdList.add(RTRN_LINE_STS.ENTERED);
        rtrnLineStsCdList.add(RTRN_LINE_STS.BOOKED);
        rtrnLineStsCdList.add(RTRN_LINE_STS.RWS_CANCELLED);
        rtrnLineStsCdList.add(RTRN_LINE_STS.CANCELLED);
        ssmParam.put("rtrnLineStsCdList", rtrnLineStsCdList);

        return (BigDecimal) ssmBatchClient.queryObject("isEnableToCancelForInbound", ssmParam);
    }
    // S21_NA#11630 ADD END
    // QC#16425 2016/12/14 Add Start
    public BigDecimal getConvProcStsCd(NWZC150001PMsg pMsg, NWZC150001CpouDetailBean cpouDtlBean){
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
        ssmParam.put("dsOrdLineCatgCd", cpouDtlBean.getDsOrdLineCatgCd());
        ssmParam.put("loanToSales", NWZC150001Constant.LOAN_TO_SLS);
        ssmParam.put("rentalToSales", NWZC150001Constant.RENTAL_TO_SLS);
        ssmParam.put("slsDt",       pMsg.slsDt.getValue());

        // 2018/03/09 S21_NA#19808 with Cache Start
        BigDecimal rslt = getConvProcStsCdCache.get(ssmParam);
        if (rslt == null) {
            rslt = (BigDecimal) ssmBatchClient.queryObject("getConvProcStsCd", ssmParam);
            getConvProcStsCdCache.put(ssmParam, rslt);
        }
        return rslt;
        // 2018/03/09 S21_NA#19808 with Cache End
    }
    // QC#16425 2016/12/14 Add End
    // 2017/06/20 S21_NA#19288-2 Add Start
    /**
     * check exists oepn ds_cpo_rtrn_dtl
     * @param cpouBean  NWZC150001CpouCpoBean
     * @return true: there are open DS_CPO_RTRN_DTLs, false: No Open DS_CPO_RTRN_DTL
     */
    public boolean hasOpenReturnDtl(NWZC150001CpouBean cpouBean) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", cpouBean.getGlblCmpyCd());
        map.put("cpoOrdNum", cpouBean.getCpoOrdNum());
        map.put("closed", RTRN_LINE_STS.CLOSED);
        map.put("cancelled", RTRN_LINE_STS.CANCELLED);

        Integer cnt = (Integer) ssmBatchClient.queryObject("hasOpenReturnDtl", map);

        return (cnt > 0);
    }
    /**
     * check exists open cpo_dtl
     * @param cpouBean NWZC150001CpouCpoBean
     * @return true: there are open CPO_DTLs, false: No Open CPO_DTL
     */
    public boolean hasOpenCpoDtl(NWZC150001CpouBean cpouBean) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", cpouBean.getGlblCmpyCd());
        map.put("cpoOrdNum", cpouBean.getCpoOrdNum());
        map.put("closed", ORD_LINE_STS.CLOSED);
        map.put("cancelled", ORD_LINE_STS.CANCELLED);

        Integer cnt = (Integer) ssmBatchClient.queryObject("hasOpenCpoDtl", map);

        return (cnt > 0);
    }
    // 2017/06/20 S21_NA#19288-2 Add End

    // 2017/09/13 S21_NA#16346(Sol#373) Add Start
    /**
     * get InBound Order Line Category(Sol#373)
     * @param pMsg NWZC150001CpouCpoBean
     * @return String InBound Order Line Category Code
     */
    public String getInBoundLineCatg(NWZC150001PMsg pMsg, NWZC150001_rtnDtlPMsg rPMsg) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        map.put("ordCatgCd", pMsg.dsOrdCatgCd.getValue());
        map.put("ordLineCatgCd", rPMsg.dsOrdLineCatgCd_B1.getValue());
        map.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
        map.put("astrCatgCd", "*");
        map.put("rowNum", "1");

        // 2018/03/09 S21_NA#19808 Add Start
        String catgCd = getInBoundLineCatgCache.get(map);
        if (catgCd == null) {
            catgCd = (String) ssmBatchClient.queryObject("getInBoundLineCatg", map);
            getInBoundLineCatgCache.put(map, catgCd);
        }
        // 2018/03/09 S21_NA#19808 Add End

        return catgCd;
    }

    /**
     * get InBound Order Line Category(Sol#373)
     * @param pMsg NWZC150001CpouCpoBean
     * @return String InBound Order Line Category Code
     */
    public String getInBoundLineCatg(NWZC150001PMsg pMsg, String dsOrdCatgCd, String dsOrdLineCatgCd, String dsOrdTpCd) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        map.put("ordCatgCd", dsOrdCatgCd);
        map.put("ordLineCatgCd", dsOrdLineCatgCd);
        map.put("dsOrdTpCd", dsOrdTpCd);
        map.put("astrCatgCd", "*");
        map.put("rowNum", "1");

        String catgCd = (String) ssmBatchClient.queryObject("getInBoundLineCatg", map);

        return catgCd;
    }

    /**
     * CodeDescription Text
     * @param bizMsg NWAL1500CMsg
     * @param carrCd Carrier Code
     * @return Carrier SerVice Level Description Text
     */
    public String getCarrSvcLvlCd(NWZC150001PMsg bizMsg, String carrCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shpgSvcLvlCd", bizMsg.addShpgSvcLvlCd.getValue());
        params.put("carrCd", carrCd);
        params.put("rowNum", "1");

        return (String) ssmBatchClient.queryObject("getCarrSvcLvlCd", params);
    }
    // 2017/09/13 S21_NA#16346(Sol#373) Add End
    // 2017/10/18 S21_NA#16347 Add Start
    /**
     * <pre>
     * Get Conversion Transaction Code
     * @param glblCmpyCd Global Company Code
     * @param Transaction Code
     * @param Transaction Reason Code
     * @return MAP<String, Object> "TO_TRX_CD": Transaction Code, "TO_TRX_RSN_CD": Transaction Reason Code
     *  </pre>
     */
    public Map<String, String> getConvTrxCd(String glblCmpyCd, String fromTrxCd, String fromTrxRsnCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("fromTrxCd", fromTrxCd);
        params.put("fromTrxRsnCd", fromTrxRsnCd);

        return (Map<String, String>) ssmBatchClient.queryObject("getConvTrxCd", params);
    }
    // 2017/10/18 S21_NA#16347 Add End
    // 2017/11/09 S21_NA#22091 Add Start
    /**
     * <pre>
     * Get DI Check Mandatory Table, Column List.
     * @param pMsg NWZC150001 Api Parameter
     * @return Mandatory Table.Column Map List ("DI_MND_CHK_TBL_NM": Table Name, "DI_MND_CHK_COL_NM": Column Name)
     * </pre>
     */
    public List<Map<String, String>> getDiChkMandtRec(NWZC150001PMsg pMsg) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
        params.put("slsDt", pMsg.slsDt.getValue());
        params.put("dsOrdCatgCd", pMsg.dsOrdCatgCd.getValue());
        params.put("diChkLvlCdLine", DI_CHK_LVL.LINE);

        return (List<Map<String, String>>) ssmBatchClient.queryObjectList("getDiChkMandtRec", params);
    }
    // 2017/11/09 S21_NA#22091 Add End
    /**
     * <pre>
     * check listed Items are child of Model or not
     * @param glblCmpyCd Global Company Code
     * @param childMdseCdList list of child item
     * @return true: has child item, false: not
     * </pre>
     */
    public boolean isModelChildItem(String glblCmpyCd, List<String> childMdseCdList) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("childMdseList", childMdseCdList);

        BigDecimal rslt = (BigDecimal) ssmBatchClient.queryObject("isModelChildItem", params);
        if (rslt == null) {
            return false;
        } else {
            return BigDecimal.ZERO.compareTo(rslt) < 0;
        }
    }

    // 2018/01/31 QC#23563 add start
    public String getDefaultIstlDiv(NWZC150001PMsg pMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());

        return (String) ssmBatchClient.queryObject("getDefaultIstlDiv", params);
    }
    // 2018/01/31 QC#23563 add end
    // 2018/03/20 S21_NA#24698 Add Start
    /**
     * <pre>
     * Get SVC_CONF_MSTR Item Data.
     * @param glblCmpyCd Global Company Code
     * @param svcConfigMstrPk Config ID (Service Config Master PK)
     * @return List of Map(SVC_MACH_TP_CD, MDSE_CD, ISTL_BASE_FLG, MDSE_TP_CTX_TP_CD)
     * </pre>
     */
    public List<Map<String, String>> getSvcConfItemInfo(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("configId", svcConfigMstrPk);
        params.put("mdseTpCtxTpCd", MDSE_TP_CTX_TP.MAIN_MACHINE);

        return (List<Map<String, String>>) ssmBatchClient.queryObjectList("getSvcConfItemInfo", params);
    }

    /**
     * <pre>
     * check detected Item Code is parent of software model or not.
     * @param pMsg NWZC150001 PMsg
     * @param mdseCdFull item code
     * @return true: item is parent of software model, false: not
     * </pre>
     */
    public boolean asItemSoftModelParent(NWZC150001PMsg pMsg, String mdseCdFull) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        params.put("mdseCdFull", mdseCdFull);
        String mdseCd = mdseCdFull;
        int ordTakeSize = new ORD_TAKE_MDSETMsg().getAttr("ordTakeMdseCd").getDigit();
        if (mdseCd.length() > ordTakeSize) {
            mdseCd = mdseCd.substring(0, ordTakeSize);
        }
        params.put("mdseCd", mdseCd);
        params.put("slsDt", pMsg.slsDt.getValue());
        params.put("mdlTpSoft", SVC_MDL_TP.SOFTWARE);

        BigDecimal rsltCnt = (BigDecimal) ssmBatchClient.queryObject("asItemSoftModelParent", params);

        if (rsltCnt == null) {
            return false;
        }
        if (rsltCnt.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        } else {
            return false;
        }
    }
    // 2018/03/20 S21_NA#24698 Add End
    
    // 2018/06/14 S21_NA#24294 Add Mod Start 
    // public Map<String, Map<String, String>> getMdseItemStsFlagInfo(String glblCmpyCd) {
    public Map<String, Map<String, String>> getMdseItemStsFlagInfo(String glblCmpyCd, Map<String, Map<String, String>> mdseItemFlagInfoMap) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);

        @SuppressWarnings("unchecked")
        List<Map<String, String>> map = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getMdseItemStsFlagInfo", ssmParam);
        // Map<String, Map<String, String>> resultMap = new HashMap<String, Map<String, String>>();
        
        for(Map<String, String> infoMap : map){
            if(ZYPCommonFunc.hasValue(infoMap.get("MDSE_ITEM_STS_CD"))){
                // resultMap.put(infoMap.get("MDSE_ITEM_STS_CD"), infoMap);
                mdseItemFlagInfoMap.put(infoMap.get("MDSE_ITEM_STS_CD"), infoMap);
            }
        }
        // return resultMap;
        return mdseItemFlagInfoMap;
    }
    // 2018/06/14 S21_NA#24294 Add Mod End
    // QC#28772 2018/10/16 Add Start
    public List<Map<String, Object>> getSvcConfig(NWZC150001PMsg pMsg, BigDecimal svcConfigMstrPk){
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("svcConfigMstrPk", svcConfigMstrPk);
        queryParam.put("dsContrDtlStsCanc", DS_CONTR_DTL_STS.CANCELLED);
        queryParam.put("warranty", DS_CONTR_CATG.WARRANTY);
        queryParam.put("slsDate", pMsg.slsDt.getValue());
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcConfig", queryParam);
    }
    // QC#28772 2018/10/16 Add End

    // Add Start 2018/11/28 QC#29252
    /**
     * @param pMsg NWZC150001PMsg
     * @return Map
     */
    public Map<String, Object> getDsTrxRuleTpCd(NWZC150001PMsg pMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("ordCatgCtxTpCd01", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        queryParam.put("ordCatgCtxTpCd02", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        queryParam.put("dsOrdCatgCd", pMsg.dsOrdCatgCd.getValue());
        queryParam.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
        queryParam.put("dsOrdRsnCd", pMsg.dsOrdRsnCd.getValue());

        return (Map<String, Object>) ssmBatchClient.queryObject("getDsTrxRuleTpCd", queryParam);
    }
    // Add End 2018/11/28 QC#29252
    
    // 2019/02/22 S21_NA#30449 Add Start
    /**
     * getOrdCatgBizCtx.
     * @param glblCmpyCd String
     * @param ordCatgCtxTpCd String
     * @param dsOrdCatgCd String
     * @param dsOrdTpCd String
     * @return String (Y or Null)
     */
    public String getOrdCatgBizCtx(String glblCmpyCd, String ordCatgCtxTpCd, String dsOrdCatgCd, String dsOrdTpCd) {
        if (!ZYPCommonFunc.hasValue(dsOrdCatgCd)) {
            return "";
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ordCatgCtxTpCd", ordCatgCtxTpCd);
        params.put("dsOrdCatgCd", dsOrdCatgCd);
        params.put("dsOrdTpCd", dsOrdTpCd);
        params.put("flgY", ZYPConstant.FLG_ON_Y);

        return (String) ssmBatchClient.queryObject("getOrdCatgBizCtx", params);
    }
    // 2019/02/22 S21_NA#30449 Add End

    // 2019/10/02 S21_NA#52988 Add Start
    public Boolean isExistsSplyReln(NWZC150001PMsg pMsg, NWZC150001_APMsg dtlPMsg, NWZC150001_cpoConfigPMsg cpoConfigPMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("mdseCd", dtlPMsg.mdseCd_A1.getValue());
        ssmParam.put("mdlId", cpoConfigPMsg.mdlId.getValue());
        BigDecimal count = (BigDecimal) ssmBatchClient.queryObject("isExistsSplyReln", ssmParam);
        return BigDecimal.ZERO.compareTo(count) < 0;
    }

    public Boolean isExistsSplyRelnForOrdTakeMdse(NWZC150001PMsg pMsg, NWZC150001_APMsg dtlPMsg, NWZC150001_cpoConfigPMsg cpoConfigPMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("mdseCd", dtlPMsg.mdseCd_A1.getValue());
        ssmParam.put("mdlId", cpoConfigPMsg.mdlId.getValue());
        BigDecimal count = (BigDecimal) ssmBatchClient.queryObject("isExistsSplyRelnForOrdTakeMdse", ssmParam);
        return BigDecimal.ZERO.compareTo(count) < 0;
    }
    // 2019/10/02 S21_NA#52988 Add End

    // START 2023/05/11 T.Doi [CSA-QC#61246, ADD]
    public List<Map<String, Object>> getPrchReqDtlKey(NWZC150001PMsg pMsg) {
        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", pMsg.cpoOrdNum.getValue());
        queryParam.put("crdLineStsCd", ORD_LINE_STS.CANCELLED);
        queryParam.put("cpoSrcTpCd", CPO_SRC_TP.INVENTORY_REQUEST_ENTRY);
        queryParam.put("prchReqLineStsCd01", PRCH_REQ_LINE_STS.CLOSED);
        queryParam.put("prchReqLineStsCd02", PRCH_REQ_LINE_STS.CANCELLED);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPrchReqDtlKey", queryParam);
    }
    // END 2023/05/11 T.Doi [CSA-QC#61246, ADD]

    // START 2023/09/05 K.Watanabe [QC#53408, ADD]
    /**
     * Get Default Accessory Install Type
     * @param cdMsg CPO_DTLTMsg
     * @return Map<String, Object>
     */
    public Map<String, Object> getDefaultAccessoryInstTypeWithConfNum(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg cpoConfigPMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", pMsg.cpoOrdNum);
        ssmParam.put("dsOrdPosnNum", cpoConfigPMsg.dsOrdPosnNum);
        ssmParam.put("ConfigCatgCd", cpoConfigPMsg.configCatgCd);
        List<String> configTpCdList = new ArrayList<String>();
        configTpCdList.add(CONFIG_TP.ADD_TO_CONFIG);
        configTpCdList.add(CONFIG_TP.SERVICE_EXCHANGE);
        ssmParam.put("configTpCdList", configTpCdList);
        ssmParam.put("baseCmptFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("svcMachTpCd", SVC_MACH_TP.MACHINE);

        return (Map<String, Object>) ssmBatchClient.queryObject("getDefaultAccessoryInstTypeWithConfNum", ssmParam);
    }
    // END 2023/09/05 K.Watanabe [QC#53408, ADD]

    // START 2023/12/12 K.Watanabe [QC#61300, ADD]
    /**
     * Get Deinstall Information
     * @param pMsg NWZC150001PMsg
     * @param cpoConfigPMsg NWZC150001_cpoConfigPMsg
     * @return Map<String, Object>
     */
    public Map<String, Object> getDeinstallInfo(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg cpoConfigPMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", pMsg.cpoOrdNum);
        ssmParam.put("dsOrdPosnNum", cpoConfigPMsg.dsOrdPosnNum);
        ssmParam.put("ConfigCatgCd", cpoConfigPMsg.configCatgCd);

        return (Map<String, Object>) ssmBatchClient.queryObject("getDeinstallInfo", ssmParam);
    }

    /**
     * Get Model Service Deinstall Rule Number
     * @param String glblCmpyCd
     * @param BigDecimal dsCpoConfigPk
     * @param BigDecimal svcMachMstrPk
     * @return Map<String, Object>
     */
    public Map<String, Object> getMdlSvcDeinsRuleNum(String glblCmpyCd, BigDecimal dsCpoConfigPk, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsCpoConfigPk", dsCpoConfigPk);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("svcMachTpCd", SVC_MACH_TP.MACHINE);

        return (Map<String, Object>) ssmBatchClient.queryObject("getSvcDeinsRuleNum", ssmParam);
    }

    /**
     * Get Mdse Service Deinstall Rule Number
     * @param String glblCmpyCd
     * @param BigDecimal dsCpoConfigPk
     * @return Map<String, Object>
     */
    public Map<String, Object> getMdseSvcDeinsRuleNum(String glblCmpyCd, BigDecimal dsCpoConfigPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsCpoConfigPk", dsCpoConfigPk);

        return (Map<String, Object>) ssmBatchClient.queryObject("getSvcDeinsRuleNum", ssmParam);
    }
    // END 2023/12/12 K.Watanabe [QC#61300, ADD]

    // QC#63527 2024/03/14 Start
    public List<Map<String, Object>> getDsOrdCatgListForValSet(String glblCmpyCd, String firstBizCtxAttrbTxt) {

        Map<String, String> ssmParams = new HashMap<String, String>();
        ssmParams.put("glblCmpyCd", glblCmpyCd);
        ssmParams.put("ordLineCtxCd", "LOAN_CONV_LINE_CRAT");
        ssmParams.put("firstBizCtxAttrbTxt", firstBizCtxAttrbTxt);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDsOrdCatgListForValSet", ssmParams);
    }

    public String getRtlWh(String glblCmpyCd, String physWhCd) {

        Map<String, String> ssmParams = new HashMap<String, String>();
        ssmParams.put("glblCmpyCd", glblCmpyCd);
        ssmParams.put("physWhCd", physWhCd);

        return (String) ssmBatchClient.queryObject("getRtlWh", ssmParams);
    }

    public String getCpoDetailInvLocCd(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        Map<String, String> ssmParams = new HashMap<String, String>();
        ssmParams.put("glblCmpyCd", glblCmpyCd);
        ssmParams.put("cplOrdNum", cpoOrdNum);
        ssmParams.put("cplDtlLineNum", cpoDtlLineNum);
        ssmParams.put("cplDtlLineSubNum",cpoDtlLineSubNum);

        return (String) ssmBatchClient.queryObject("getCpoDetailInvLocCd", ssmParams);
    }

    public String getInvtyOwnrCd(String rtlWhCd, String glblCmpyCd) {

        Map<String, String> ssmParams = new HashMap<String, String>();
        ssmParams.put("glblCmpyCd", glblCmpyCd);
        ssmParams.put("whCd", rtlWhCd);

        return (String) ssmBatchClient.queryObject("getInvtyOwnrCd", ssmParams);
    }
    // QC#63527 2024/03/14 End
}
