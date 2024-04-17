/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1500;

import static business.blap.NWAL1500.constant.NWAL1500Constant.BIZ_ID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NWAL1500.common.NWAL1500CommonLogic;
import business.blap.NWAL1500.constant.NWAL1500Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/29   Fujitsu         M.Nakamura      Update          QC#1693/1700
 * 2016/05/10   Fujitsu         M.Yamada        Update          S21_NA#6148
 * 2016/05/11   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/05/18   Fujitsu         T.Murai         Update          S21_NA#5337
 * 2016/09/27   Fujitsu         T.Yoshida       Update          S21_NA#14698
 * 2016/10/11   Fujitsu         S.Takami        Update          S21_NA#7924-2
 * 2016/10/13   Fujitsu         N.Sugiura       Update          S21_NA#7700
 * 2016/10/21   Fujitsu         Y.Kanefusa      Update          S21_NA#15463
 * 2016/10/21   Fujitsu         S.Iidaka        Update          S21_NA#14607
 * 2017/09/21   Fujitsu         T.Murai         Update          S21_NA#18859(Sol#154)
 * 2017/10/04   Fujitsu         S.Takami        Update          S21_NA#21300
 * 2017/10/31   Fujitsu         Y.Kanefusa      Update          S21_NA#22031
 * 2017/11/02   Fujitsu         T.Aoi           Update          S21_NA#20146(Sol#92)
 * 2018/01/30   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/03/20   Fujitsu         A.Kosai         Update          S21_NA#24840
 * 2018/05/11   Fujitsu         T.Aoi           Update          S21_NA#22139
 * 2018/07/03   Fujitsu         H.Kumagai       Update          QC#26932
 * 2019/07/18   Fujitsu         K.Kato          Update          S21_NA#51327
 * 2019/10/03   Fujitsu         A.Kazuki        Update          QC#53595
 * 2019/11/27   Fujitsu         K.Kato          Update          QC#52339
 * 2023/02/07   Hitachi         S.Fujita        Update          QC#61010
 *</pre>
 */
public final class NWAL1500Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1500Query MY_INSTANCE = new NWAL1500Query();

    private Map<Object, S21SsmEZDResult> getDropShipInfoCache = new HashMap<Object, S21SsmEZDResult>();
    /**
     * Constructor.
     */
    private NWAL1500Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1500Query
     */
    public static NWAL1500Query getInstance() {
        return MY_INSTANCE;
    }

    public S21SsmEZDResult getConfigInfoForCopy(Map<String, Object> queryParam, NWAL1500_ASMsg msgArray) {
        return getSsmEZDClient().queryEZDMsg("getConfigInfoForCopy", queryParam, msgArray);
    }

    public S21SsmEZDResult getConfigRtrnInfoForCopy(Map<String, Object> queryParam, NWAL1500_CSMsg msgArray) {
        return getSsmEZDClient().queryEZDMsg("getConfigRtrnInfoForCopy", queryParam, msgArray);
    }

    public S21SsmEZDResult getCpoDtlViewInfoForCopy(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getCpoDtlViewInfoForCopy", queryParam);
    }

    public S21SsmEZDResult getCpoRtrnDtlViewInfoForCopy(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getCpoRtrnDtlViewInfoForCopy", queryParam);
    }

    public S21SsmEZDResult getPriceInfoInForCopy(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getPriceInfoInForCopy", queryParam);
    }

    public S21SsmEZDResult getPriceInfoOutForCopy(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getPriceInfoOutForCopy", queryParam);
    }

    public S21SsmEZDResult getShippingPlanInfoForCopy(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getShippingPlanInfoForCopy", queryParam);
    }

    public S21SsmEZDResult getSalesCreditForConfigOutForCopy(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getSalesCreditForConfigOutForCopy", queryParam);
    }

    public S21SsmEZDResult getSalesCreditForConfigInForCopy(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getSalesCreditForConfigInForCopy", queryParam);
    }

    /**
     * get DS Order Type List
     * @param bizMsg NWAL1500CMsg
     * @return DS Order Type List
     */
    public S21SsmEZDResult getDsOrdTpList(NWAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());

        return getSsmEZDClient().queryObjectList("getDsOrdTpList", params);
    }

    /**
     * get DS Order Reason List
     * @param bizMsg NWAL1500CMsg
     * @param effDt Effective Date (YYYYMMDD)
     * @return DS Order Reason List
     */
    public S21SsmEZDResult getDsOrdRsnList(NWAL1500CMsg bizMsg, String effDt) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("effDt", effDt);

        return getSsmEZDClient().queryObjectList("getDsOrdRsnList", params);
    }

    /**
     * get DS Order Category Code List
     * @param bizMsg NWAL1500CMsg
     * @return DS Order Category Code List
     */
    public S21SsmEZDResult getOrdLogTpList(NWAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        // S21_NA#5337 Mod 
        // params.put("ordLogTpDescTxt", bizMsg.ordLogTpDescTxt_NM.getValue().toUpperCase());
        params.put("ordLogTpCd", bizMsg.ordLogTpCd.getValue().toUpperCase());

        params.put("bizId", BIZ_ID);

        return getSsmEZDClient().queryObjectList("getOrdLogTpList", params);
    }

    /**
     * get DS Order Line Category List
     * @param bizMsg NWAL1500CMsg
     * @param effDt Effective Date (YYYYMMDD)
     * @param dsOrdLineDrctnCd DS Order Direction Code
     * @return DS Order Line Category List
     */
    public S21SsmEZDResult getDsOrdLineCatgList(NWAL1500CMsg bizMsg, String effDt, String dsOrdLineDrctnCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("effDt", effDt);
        params.put("dsOrdLineDrctnCd", dsOrdLineDrctnCd);

        return getSsmEZDClient().queryObjectList("getDsOrdLineCatgList", params);
    }

    // Add Start 2017/09/21 S21_NA#18859
    /**
     * get DS Order Line Category Inbound from Outbound
     * @param bizMsg NWAL1500CMsg
     * @param bizMsg NWAL1500DMsg
     * @param effDt Effective Date (YYYYMMDD)
     * @return DS Order Line Category List
     */
    public S21SsmEZDResult getDsOrdLineCatgInbound(NWAL1500CMsg bizMsg, NWAL1500_DSMsg rmaMsg, String effDt) { // 2018/01/30 S21_NA#19808 Mod

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("origCpoOrdNum", rmaMsg.origCpoOrdNum_RL.getValue());
        params.put("origCpoLineNum", rmaMsg.origCpoDtlLineNum_RL.getValue());
        params.put("origCpoLineSubNum", rmaMsg.origCpoDtlLineSubNum_RL.getValue());
        params.put("astrLineCatg", "*");
        params.put("effDt", effDt);
        params.put("dsOrdLineDrctnCd", DS_ORD_LINE_DRCTN.INBOUND);

        return getSsmEZDClient().queryObjectList("getDsOrdLineCatgInbound", params);
    }
    // Add End 2017/09/21 S21_NA#18859
    /**
     * <pre>
     * Get Base Component flag from MDSE and MDSE_TP_VAL_SET table
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @param mdseCd Merchandise Code
     * @return MDSE.INSTL_BASE_CTRL_FLG, MDSE_TP_VAL_SET.MDSE_TP_CTX_TP_CD<br>
     * MDSE is main table, MDSE_TP_VAL_SET table outer join table<br>
     * there is possibility: MDSE_TP_VAL_SET.MDSE_TP_CTX_TP_CD is null.
     */
    public S21SsmEZDResult getBaseComponentFlag(String glblCmpyCd, String mdseCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        params.put("mdseTpCtxTpCd", MDSE_TP_CTX_TP.MAIN_MACHINE);

        return getSsmEZDClient().queryObject("getBaseComponentFlag", params);
    }

    /**
     * getPkgUomPullDownList String
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPkgUomPullDownList(String glblCmpyCd, String mdseCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObjectList("getPkgUomPullDownList", params);
    }

    /**
     * get Sales Rep Info List
     * @param bizMsg NWAL1500CMsg
     * @param isCallName Called Name Field
     * @return Sales Rep Info List
     */
    public S21SsmEZDResult getSlsRepInfoList(NWAL1500CMsg bizMsg, boolean isCallName) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("slsRepFlg", ZYPConstant.FLG_ON_Y);
        params.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        // 2016/10/21 S21_NA#14607 Add Start
        params.put("rgtnStsCd", RGTN_STS.TERMINATED);
        // 2016/10/21 S21_NA#14607 Add End

        if (isCallName) {
            params.put("slsRepTocNm", bizMsg.slsRepTocNm.getValue());
        } else {
            params.put("psnNum", bizMsg.psnNum.getValue()); // 2016/05/11 S21_NA#7861 Mod slsRepPsnCdf-> psnNum
        }

        return getSsmEZDClient().queryObjectList("getSlsRepInfoList", params);
    }

    /**
     * get Primary Sales Rep Info
     * @param bizMsg NWAL1500CMsg
     * @param primRepTocCd Primary Rep TOC Code
     * @return Primary Sales Rep Info
     */
    public S21SsmEZDResult getPrimSlsRepInfo(NWAL1500CMsg bizMsg, String primRepTocCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("primRepTocCd", primRepTocCd);
        // 2016/10/21 S21_NA#14607 Add Start
        params.put("rgtnStsCd", RGTN_STS.TERMINATED);
        // 2016/10/21 S21_NA#14607 Add End

        return getSsmEZDClient().queryObject("getPrimSlsRepInfo", params);
    }

    /**
     * get Carrier SerVice Level Description Text
     * @param bizMsg NWAL1500CMsg
     * @param carrCd Carrier Code
     * @return Carrier SerVice Level Description Text
     */
    public S21SsmEZDResult getCarrSvcLvlDescTxt(NWAL1500CMsg bizMsg, String carrCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shpgSvcLvlCd", bizMsg.shpgSvcLvlCd.getValue());
        params.put("carrCd", carrCd);

        return getSsmEZDClient().queryObject("getCarrSvcLvlDescTxt", params);
    }

    /**
     * isEnableToCancelForOutbound
     * @param bizMsg NWAL1500CMsg
     * @return BigDecimal
     */
//    public BigDecimal isEnableToCancelForOutbound(NWAL1500CMsg bizMsg) { 2017/10/04 S21_NA#21300 Mod Interface
    public S21SsmEZDResult isEnableToCancelForOutbound(NWAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("trxHdrNum", bizMsg.cpoOrdNum.getValue());
        List<String> shpgStsCdList = new ArrayList<String>();
        shpgStsCdList.add(SHPG_STS.SAVED);
        shpgStsCdList.add(SHPG_STS.VALIDATED);
        shpgStsCdList.add(SHPG_STS.CANCELLED);
        shpgStsCdList.add(SHPG_STS.P_OR_O_CANCELLED);
        // 2017/10/04 S21_NA#21300 Add Start
        shpgStsCdList.add(SHPG_STS.P_OR_O_PRINTED);
        // 2017/10/04 S21_NA#21300 Add End
        params.put("shpgStsCdList", shpgStsCdList);
        // 2017/10/04 S21_NA#21300 Add Start
        params.put("shpgStsPoPrinted", SHPG_STS.P_OR_O_PRINTED);

        List<String> ittAvalCancStsList = NWAL1500CommonLogic.getAvalCancStsOfItt(bizMsg.glblCmpyCd.getValue());
        params.put("ittAvalCancStsList", ittAvalCancStsList);

        List<String> ittAvalCancOrdLineSrcList = NWAL1500CommonLogic.getAvalCancOrdLineSrcCdOfItt(bizMsg.glblCmpyCd.getValue());
        params.put("ittAvalCancOrdLineSrcList", ittAvalCancOrdLineSrcList);
        // 2017/10/04 S21_NA#21300 Add End

        // 2019/11/27 QC#52339 Add Start 
        String[] shpgStsCdIntangibleList = {SHPG_STS.INVOICED };
        params.put("shpgStsCdIntangibleList", shpgStsCdIntangibleList);
        params.put("invtyCtrlFlgY", ZYPConstant.FLG_ON_Y);
        params.put("invtyCtrlFlgN", ZYPConstant.FLG_OFF_N);
        // 2019/11/27 QC#52339 Add End

        // 2017/10/04 S21_NA#21300 Mod Start
//        return (BigDecimal) getSsmEZDClient().queryObject("isEnableToCancelForOutbound", params).getResultObject();
        return getSsmEZDClient().queryObjectList("isEnableToCancelForOutbound", params);
        // 2017/10/04 S21_NA#21300 Mod End
    }

    /**
     * isEnableToCancelForInbound
     * @param bizMsg NWAL1500CMsg
     * @return BigDecimal
     */
    public BigDecimal isEnableToCancelForInbound(NWAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        List<String> rtrnLineStsCdList = new ArrayList<String>();
        rtrnLineStsCdList.add(RTRN_LINE_STS.ENTERED);
        rtrnLineStsCdList.add(RTRN_LINE_STS.BOOKED);
        rtrnLineStsCdList.add(RTRN_LINE_STS.RWS_CANCELLED);
        rtrnLineStsCdList.add(RTRN_LINE_STS.CANCELLED);
        params.put("rtrnLineStsCdList", rtrnLineStsCdList);

        return (BigDecimal) getSsmEZDClient().queryObject("isEnableToCancelForInbound", params).getResultObject();
    }

    /**
     * getCpoForChangeOrderModification
     * @param bizMsg NWAL1500CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCpoForChangeOrderModification(NWAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());

        return getSsmEZDClient().queryObject("getCpoForChangeOrderModification", params);
    }

    /**
     * checkEnableChangeOrderModification
     * @param bizMsg NWAL1500CMsg
     * @param map Map<?, ?> getCpoForChangeOrderModification return
     * @return S21SsmEZDResult
     */
    public BigDecimal checkEnableChangeOrderModification(NWAL1500CMsg bizMsg, Map< ? , ? > map) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("ordCatgCtxTpCd", NWAL1500Constant.ORD_CATG_BIZ_CTX_CHANGE_ORDER_EXCLUSIONS);
        params.put("cpoSrcTpCd", (String) map.get("CPO_SRC_TP_CD"));
        params.put("dsOrdCatgCd", (String) map.get("DS_ORD_CATG_CD"));
        params.put("dsOrdTpCd", (String) map.get("DS_ORD_TP_CD"));
        params.put("dsOrdRsnCd", (String) map.get("DS_ORD_RSN_CD"));

        return (BigDecimal) getSsmEZDClient().queryObject("checkEnableChangeOrderModification", params).getResultObject();
    }

    /**
     * cntBackOrderForItemConfig.
     * @param bizMsg NWAL1500CMsg
     * @param config NWAL1500_ACMsg
     * @return BigDecimal
     */
    public BigDecimal cntBackOrderForItemConfig(NWAL1500CMsg bizMsg, NWAL1500_ACMsg config) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("dsOrdPosnNum", config.dsOrdPosnNum_LC.getValue());
        List<String> shpgStsList = new ArrayList<String>();
        shpgStsList.add(SHPG_STS.SAVED);
        shpgStsList.add(SHPG_STS.VALIDATED);
        shpgStsList.add(SHPG_STS.CANCELLED);
        shpgStsList.add(SHPG_STS.P_OR_O_CANCELLED);
        params.put("shpgStsList", shpgStsList);

        return (BigDecimal) getSsmEZDClient().queryObject("cntBackOrderForItem", params).getResultObject();
    }

    /**
     * cntBackOrderForItemLine.
     * @param bizMsg NWAL1500CMsg
     * @param line NWAL1500_BCMsg
     * @return BigDecimal
     */
    public BigDecimal cntBackOrderForItemLine(NWAL1500CMsg bizMsg, NWAL1500_BCMsg line) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("dsOrdPosnNum", line.dsOrdPosnNum_LL.getValue());
        params.put("cpoDtlLineNum", line.cpoDtlLineNum_LL.getValue());
        params.put("cpoDtlLineSubNum", line.cpoDtlLineSubNum_LL.getValue());
        List<String> shpgStsList = new ArrayList<String>();
        shpgStsList.add(SHPG_STS.SAVED);
        shpgStsList.add(SHPG_STS.VALIDATED);
        shpgStsList.add(SHPG_STS.CANCELLED);
        shpgStsList.add(SHPG_STS.P_OR_O_CANCELLED);
        params.put("shpgStsList", shpgStsList);

        return (BigDecimal) getSsmEZDClient().queryObject("cntBackOrderForItem", params).getResultObject();
    }

    /**
     * cntBackOrderForRMAConfig.
     * @param bizMsg NWAL1500CMsg
     * @param config NWAL1500_CCMsg
     * @return BigDecimal
     */
    public BigDecimal cntBackOrderForRMAConfig(NWAL1500CMsg bizMsg, NWAL1500_CCMsg config) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("dsOrdPosnNum", config.dsOrdPosnNum_RC.getValue());
        List<String> rtrnLineStsList = new ArrayList<String>();
        rtrnLineStsList.add(RTRN_LINE_STS.ENTERED);
        rtrnLineStsList.add(RTRN_LINE_STS.BOOKED);
        rtrnLineStsList.add(RTRN_LINE_STS.CANCELLED);
        rtrnLineStsList.add(RTRN_LINE_STS.RWS_CANCELLED);
        params.put("rtrnLineStsList", rtrnLineStsList);

        return (BigDecimal) getSsmEZDClient().queryObject("cntBackOrderForRMA", params).getResultObject();
    }

    /**
     * cntBackOrderForRMALine.
     * @param bizMsg NWAL1500CMsg
     * @param line NWAL1500_DCMsg
     * @return BigDecimal
     */
    public BigDecimal cntBackOrderForRMALine(NWAL1500CMsg bizMsg, NWAL1500_DCMsg line) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("dsOrdPosnNum", line.dsOrdPosnNum_RL.getValue());
        params.put("dsCpoRtrnLineNum", line.dsCpoLineNum_RL.getValue());
        params.put("dsCpoRtrnLineSubNum", line.cpoDtlLineSubNum_RL.getValue());
        List<String> rtrnLineStsList = new ArrayList<String>();
        rtrnLineStsList.add(RTRN_LINE_STS.ENTERED);
        rtrnLineStsList.add(RTRN_LINE_STS.BOOKED);
        rtrnLineStsList.add(RTRN_LINE_STS.CANCELLED);
        rtrnLineStsList.add(RTRN_LINE_STS.RWS_CANCELLED);
        params.put("rtrnLineStsList", rtrnLineStsList);

        return (BigDecimal) getSsmEZDClient().queryObject("cntBackOrderForRMA", params).getResultObject();
    }

    /**
     * getDropShipInfo.
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return Map<String, String>
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getDropShipInfo(String glblCmpyCd, String shipToCustCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("shipToCustCd", shipToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        S21SsmEZDResult ssmResult = getDropShipInfoCache.get(params);
        if (ssmResult == null) {
            ssmResult = getSsmEZDClient().queryObject("getDropShipInfo", params);
            if (!ssmResult.isCodeNormal()) {    // S21_NA#6148
                return null;
            }
            getDropShipInfoCache.put(params, ssmResult);
        }
        return (Map<String, String>) ssmResult.getResultObject();
    }

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

        return (String) getSsmEZDClient().queryObject("getOrdCatgBizCtx", params).getResultObject();
    }

    /**
     * get Prica Category Code List
     * @param bizMsg NWAL1500CMsg
     * @param prcCatgNm Prica Category Name
     * @return Prica Category Code List
     */
    public S21SsmEZDResult getPrcCatgCdList(NWAL1500CMsg bizMsg, String prcCatgNm) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("prcCatgNm", prcCatgNm);

        return getSsmEZDClient().queryObjectList("getPrcCatgCdList", params);
    }

    // QC#14698 Add Start
    /**
     * get Shipping Plan List
     * @param bizMsg NWAL1500CMsg
     * @return Shipping Plan List
     */
    public S21SsmEZDResult getShpgPlnList(NWAL1500CMsg bizMsg) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());

        return getSsmEZDClient().queryObjectList("getShpgPlnList", params);
    }
    // QC#14698 Add End
    // 2016/10/13 S21_NA#7700 Add Start
    /**
     * getOrdCatgBizCtx.
     * @param glblCmpyCd String
     * @param ordCatgCtxTpCd String
     * @param dsOrdCatgCd String
     * @param dsOrdTpCd String
     * @return BigDecimal
     */
    public BigDecimal isOrdEntryCancelAvailable(String glblCmpyCd, String ordCatgCtxTpCd, String dsOrdCatgCd, String dsOrdTpCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ordCatgCtxTpCd", ordCatgCtxTpCd);
        params.put("dsOrdCatgCd", dsOrdCatgCd);
        params.put("dsOrdTpCd", dsOrdTpCd);

        return (BigDecimal) getSsmEZDClient().queryObject("countOrdEntryCancelNotAvailable", params).getResultObject();
    }
    // 2016/10/13 S21_NA#7700 Add End

    // QC#15463 2016/10/21 Add Start
    /**
     * <pre>
     * Get SVC Amount
     * @param bizMsg NWAL1500CMsg
     * @return S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getSvcAmount(NWAL1500CMsg bizMsg) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("billWithEquipFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("getSvcAmount", params);
    }
    // QC#15463 2016/10/21 Add End

    // 2017/04/25 S21_NA#Review structure Lv.2 Del Start
//    // 2016/10/11 S21_NA#7924-2 Add Start
//    /**
//     * <pre>
//     * Get CPO_SVC Record
//     * @param glblCmpyCd Global Company Code (Search Key)
//     * @param cpoOrdNum CPO Order Number (SearchKey)
//     * @return S21SsmEZDResult
//     * </pre>
//     */
//    public S21SsmEZDResult getCpoSvcInfoByCpoOrdNum(String glblCmpyCd, String cpoOrdNum) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("cpoOrdNum", cpoOrdNum);
//        return getSsmEZDClient().queryObjectList("getCpoSvcInfoByCpoOrdNum", queryParam);
//    }
//
//    /**
//     * <pre>
//     * get max biz process log primary key
//     * @param queryParam
//     *  Key: subSysId
//     *       procId
//     *       eventId (available like search)
//     *       docTpCd
//     *       docId
//     *       cpoOrdNum
//     *       userId
//     *       ezUptime
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getMaxBizProcPk(Map<String, String> queryParam) {
//
//        return getSsmEZDClient().queryObject("getMaxBizProcPk", queryParam);
//    }
//    // 2016/10/11 S21_NA#7924-2 Add End
    // 2017/04/25 S21_NA#Review structure Lv.2 Del End

    // 2017/10/04 S21_NA#21300 Add Start
    /**
     * isEnableToCancelForOutbound
     * @param bizMsg NWAL1500CMsg
     * @return BigDecimal
     */
    public BigDecimal hasNoAvailableCancelPoForOutbound(NWAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("trxHdrNum", bizMsg.cpoOrdNum.getValue());

        params.put("shpgStsPoPrinted", SHPG_STS.P_OR_O_PRINTED);

        List<String> ittAvalCancStsList = NWAL1500CommonLogic.getAvalCancStsOfItt(bizMsg.glblCmpyCd.getValue());
        params.put("ittAvalCancStsList", ittAvalCancStsList);

        List<String> ittAvalCancOrdLineSrcList = NWAL1500CommonLogic.getAvalCancOrdLineSrcCdOfItt(bizMsg.glblCmpyCd.getValue());
        params.put("ittAvalCancOrdLineSrcList", ittAvalCancOrdLineSrcList);
        // 2017/10/04 S21_NA#21300 Add End

        List<BigDecimal> cntList = (List<BigDecimal>) getSsmEZDClient().queryObjectList("hasNoAvailableCancelPoForOutbound", params).getResultObject();
        BigDecimal rslt = BigDecimal.ZERO;
        for (BigDecimal cnt : cntList) {
            rslt = rslt.add(cnt);
        }
        return rslt;
    }
    // 2017/10/04 S21_NA#21300 Add Start
    // QC#22031 2017/10/31 Add Start
    public S21SsmEZDResult getRtlWh(String glblCmpyCd, String invtyLocCd){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("invtyLocCd", invtyLocCd);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getRtlWh", params);
        return ssmResult;
    }
    // QC#22031 2017/10/31 Add End

    // 2017/11/02 S21_NA#20146 Add Start
    public  S21SsmEZDResult getSalesRepList(String glblCmpyCd, String slsRepTocCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsRepTocCd", slsRepTocCd);
        return getSsmEZDClient().queryObjectList("getSalesRepList", ssmParam);
    }

    public String getOrdCatgBizCtxFstAttbTxt(String glblCmpyCd, String ordCatgCtxTpCd, String dsOrdCatgCd, String dsOrdTpCd) {
        if (!ZYPCommonFunc.hasValue(dsOrdCatgCd)) {
            return "";
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ordCatgCtxTpCd", ordCatgCtxTpCd);
        params.put("dsOrdCatgCd", dsOrdCatgCd);
        params.put("dsOrdTpCd", dsOrdTpCd);

        return (String) getSsmEZDClient().queryObject("getOrdCatgBizCtxFstAttbTxt", params).getResultObject();
    }

    // 2017/11/02 S21_NA#20146 Add End

    // 2018/03/20 S21_NA#24840 Add Start
    public String getOrdCatgBizCtxScdAttbTxt(String glblCmpyCd, String ordCatgCtxTpCd, String dsOrdCatgCd, String dsOrdTpCd) {
        if (!ZYPCommonFunc.hasValue(dsOrdCatgCd)) {
            return "";
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ordCatgCtxTpCd", ordCatgCtxTpCd);
        params.put("dsOrdCatgCd", dsOrdCatgCd);
        params.put("dsOrdTpCd", dsOrdTpCd);

        return (String) getSsmEZDClient().queryObject("getOrdCatgBizCtxScdAttbTxt", params).getResultObject();
    }
    // 2018/03/20 S21_NA#24840 Add End
    // 2023/02/07 QC#61010 Add Start
    /**
     * getSerialTakeFlag
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getSerialTakeFlag(String glblCmpyCd, String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getSerialTakeFlag", params).getResultObject();
    }
    // 2023/02/07 QC#61010 Add End
    // 2018/05/11 S21_NA#22139 Add Start
    public String getSplyQuoteNum(String glblCmpyCd, String cpoOrdNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", cpoOrdNum);

        return (String) getSsmEZDClient().queryObject("getSplyQuoteNum", params).getResultObject();
    }
    // 2018/05/11 S21_NA#22139 Add End

    // 2018/07/03 QC#26932 Add Start
    public S21SsmEZDResult getReturnAuthorizationMailAddress(String glblCmpyCd, String cpoOrdNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("ctacPsnTpCd", "ORD_CTAC");
        params.put("configCatgCd", CONFIG_CATG.INBOUND);
        return getSsmEZDClient().queryObjectList("getReturnAuthorizationMailAddress", params);
    }
    // 2018/07/03 QC#26932 Add End

    // 2019/07/18 S21_NA#51327 Add Start
//    public S21SsmEZDResult checkCreatedContract(NWAL1500CMsg bizMsg, NWAL1500_ASMsg configLineMsg) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
//        params.put("dsOrdPosnNum", configLineMsg.dsOrdPosnNum_LC.getValue());
//        params.put("dsContrStsCd", DS_CONTR_STS.ORDER);
//
//        return getSsmEZDClient().queryObjectList("checkCreatedContract", params);
//
//     }
    // 2019/07/18 S21_NA#51327 Add End
    
    // 2019/10/03 QC#53595 Add Start
    /**
     * cntOrdTpCd.
     * @param bizMsg NWAL1500CMsg
     * @return BigDecimal
     */
    public BigDecimal cntOrdTpCd(NWAL1500CMsg bizMsg) {
        boolean isISFlg = true;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        if(ZYPCommonFunc.hasValue(bizMsg.ordBookTsDplyTxt)){
            String bookedDate = bizMsg.ordBookTsDplyTxt.getValue();
            bookedDate = bookedDate.substring(6, 10).concat(bookedDate.substring(0, 2)).concat(bookedDate.substring(3, 5));
            params.put("slsDt", bookedDate);
        }else{
            params.put("slsDt", bizMsg.slsDt.getValue());
        }
        params.put("ordTpCd", bizMsg.dsOrdTpCd.getValue());
        
        return (BigDecimal) getSsmEZDClient().queryObject("cntOrdTpCd", params).getResultObject();
    }

    /**
     * cntAssignedISGroup.
     * @param bizMsg NWAL1500CMsg
     * @return BigDecimal
     */
    public BigDecimal cntAssignedISGroup(NWAL1500CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        if(ZYPCommonFunc.hasValue(bizMsg.ordBookTsDplyTxt)){
            String bookedDate = bizMsg.ordBookTsDplyTxt.getValue();
            bookedDate = bookedDate.substring(6, 10).concat(bookedDate.substring(0, 2)).concat(bookedDate.substring(3, 5));
	        params.put("slsDt", bookedDate);
        }else{
	        params.put("slsDt", bizMsg.slsDt.getValue());
        }
        params.put("psnNum", bizMsg.psnNum.getValue());

        return (BigDecimal) getSsmEZDClient().queryObject("cntAssignedISGroup", params).getResultObject();
    }
    // 2019/10/03 QC#53595 Add End
    
}
