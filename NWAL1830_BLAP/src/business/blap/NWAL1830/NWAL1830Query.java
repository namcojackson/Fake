/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1830;

import static business.blap.NWAL1830.constant.NWAL1830Constant.ACTION_LBL_NOT_CONV;
import static business.blap.NWAL1830.constant.NWAL1830Constant.ORD_LINE_VAL_SET_KEY;
import static business.blap.NWAL1830.constant.NWAL1830Constant.ORD_LINE_STS_NM_PENDING_FULFILLMENT;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NWZC150001_APMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1830Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/30   Fujitsu         Y.Taoka         Create          N/A
 * 2016/10/21   Fujitsu         S.Iidaka        Update          S21_NA#14607
 * 2017/01/13   Fujitsu         W.Honda         Update          S21_NA#17076
 * 2017/09/11   Fujitsu         M.Ohno          Update          S21_NA#19800(L3)
 * 2017/12/01   Fujitsu         M.Yamada        Update          S21_NA#22641
 * 2018/01/19   Fujitsu         M.Ohno          Update          S21_NA#23338
 * 2021/09/22   CITS            K.Ogino         Update          QC#59222
 * 2022/02/04   CITS            K.Watanabe      Update          QC#59647
 * 2024/03/14   CITS            J.Cho           Update          QC#63527
 *</pre>
 */
public final class NWAL1830Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1830Query MY_INSTANCE = new NWAL1830Query();

    /**
     * Private constructor
     */
    private NWAL1830Query() {
        super();
    }

    /**
     * Get the NWAL1830Query instance.
     * @return NWAL1830Query instance
     */
    public static NWAL1830Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Action List
     * @param bizMsg NWAL1830CMsg
     * @return DS Order Category List
     */
    public S21SsmEZDResult getActionList(NWAL1830CMsg bizMsg, String actionFlag) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("ordLineCtxCd", ORD_LINE_VAL_SET_KEY);
        params.put("actionFlag", actionFlag); // 2017/09/11 S21_NA#19800 Add

        return getSsmEZDClient().queryObjectList("getActionList", params);
    }

    /**
     * Get DS Order Category List
     * @param bizMsg NWAL1830CMsg
     * @return DS Order Category List
     */
    public S21SsmEZDResult getDsOrdCatgList(NWAL1830CMsg bizMsg, List<Map<String, String>> ordLineValSetList) { // 2017/09/11 S21_NA#19800 Mod

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        params.put("slsDt", bizMsg.slsDt.getValue());

        List<String> dsOrdLineCatgCdList = new ArrayList<String>();
        // 2017/09/11 S21_NA#19800 Mod Start
//      for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
//      NWAL1830_CCMsg catgList =  bizMsg.C.no(i);
//      if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(catgList.dsOrdLineDrctnCd.getValue())) {
//          dsOrdLineCatgCdList.add(catgList.dsOrdLineCatgCd.getValue());
//      }
//  }
        for (Map<String,String> ordLineValSet : ordLineValSetList) {
                dsOrdLineCatgCdList.add(ordLineValSet.get("DS_ORD_LINE_CATG_CD"));
        }
        // 2017/09/11 S21_NA#19800 Mod End
        params.put("dsOrdLineCatgCdList", dsOrdLineCatgCdList);


        return getSsmEZDClient().queryObjectList("getDsOrdCatgList", params);
    }
    /**
     * Get DS Order Type List
     * @param bizMsg NWAL1820CMsg
     * @return DS Order Type List
     */
    public S21SsmEZDResult getDsOrdTpList(NWAL1830CMsg bizMsg, List<Map<String, String>> ordLineValSetList) { // 2017/09/11 S21_NA#19800 Mod

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        params.put("slsDt", bizMsg.slsDt.getValue());

        List<String> dsOrdLineCatgCdList = new ArrayList<String>();
        // 2017/09/11 S21_NA#19800 Mod Start
//        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
//            NWAL1830_CCMsg catgList =  bizMsg.C.no(i);
//            if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(catgList.dsOrdLineDrctnCd.getValue())) {
//                dsOrdLineCatgCdList.add(catgList.dsOrdLineCatgCd.getValue());
//            }
//        }
        for (Map<String,String> ordLineValSet : ordLineValSetList) {
            dsOrdLineCatgCdList.add(ordLineValSet.get("DS_ORD_LINE_CATG_CD"));
        }
        // 2017/09/11 S21_NA#19800 Mod End
        params.put("dsOrdLineCatgCdList", dsOrdLineCatgCdList);

        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd_OL)) {
            params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd_OL.getValue());
        }

        return getSsmEZDClient().queryObjectList("getDsOrdTpList", params);
    }

    /**
     * getConfigList
     * @param bizMsg NWAL1830CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConfigList(NWAL1830CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_OH.getValue());
        params.put("configCatgCd", CONFIG_CATG.OUTBOUND);
        params.put("rowNum", bizMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getConfigList", params, bizMsg.A);
    }

    /**
     * getDetailList
     * @param bizMsg NWAL1830CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailList(NWAL1830CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_OH.getValue());
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("configCatgCd", CONFIG_CATG.OUTBOUND);
        params.put("baseCmptFlg", ZYPConstant.FLG_ON_Y);
        params.put("ordLineStsCdCancel", ORD_LINE_STS.CANCELLED);
        params.put("rtrnLineStsCdCancel", RTRN_LINE_STS.CANCELLED);
        params.put("ordHdrStsCdCancel", ORD_HDR_STS.CANCELLED);
        params.put("ordLineCtxCd", ORD_LINE_VAL_SET_KEY);
        params.put("ordLineStsNmLoan", bizMsg.xxScrStsTxt_LO.getValue());
        params.put("ordLineStsNmClose", bizMsg.xxScrStsTxt_CL.getValue());
        params.put("ordLineStsNmPending", ORD_LINE_STS_NM_PENDING_FULFILLMENT);
        params.put("actionLabelNotConv", ACTION_LBL_NOT_CONV);
        params.put("dsOrdLineDrctnIn", DS_ORD_LINE_DRCTN.INBOUND);
        params.put("dsOrdLineDrctnOut", DS_ORD_LINE_DRCTN.OUTBOUND);
        params.put("rowNum", bizMsg.B.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getDetailList", params, bizMsg.B);
    }

    /**
     * getWhInfoWithRsn
     * @param bizMsg NWAL1830CMsg
     * @param rtlWhNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWhInfoWithRsn(NWAL1830CMsg bizMsg, String rtlWhNm) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd_OH.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("rtlWhNm", rtlWhNm);

        return getSsmEZDClient().queryObject("getWhInfoWithRsn", params);
    }

    /**
     * getSlsRep
     * @param bizMsg NWAL1830CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSlsRep(NWAL1830CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsRepTocNm", bizMsg.slsRepTocNm_OL.getValue());
        params.put("slsRepFlg", ZYPConstant.FLG_ON_Y);
        params.put("slsFlg", ZYPConstant.FLG_ON_Y);
        params.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        // 2016/10/21 S21_NA#14607 Add Start
        params.put("rgtnStsCd", RGTN_STS.TERMINATED);
        // 2016/10/21 S21_NA#14607 Add End

        return getSsmEZDClient().queryObject("getSlsRep", params);
    }

    /**
     * getOrdHdr
     * @param bizMsg NWAL1830CMsg
     * @param cpoOrdNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdHdr(NWAL1830CMsg bizMsg, String cpoOrdNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", cpoOrdNum);

        return getSsmEZDClient().queryObject("getOrdHdr", params);
    }

    /**
     * getOrdConfig
     * @param bizMsg NWAL1830CMsg
     * @param cpoOrdNum String
     * @param configPkList List<BigDecimal>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdConfig(NWAL1830CMsg bizMsg, String cpoOrdNum, List<BigDecimal> configPkList) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", cpoOrdNum);
        if (configPkList != null && !configPkList.isEmpty()) {
            params.put("configPkList", configPkList);
        }
        return getSsmEZDClient().queryObjectList("getOrdConfig", params);
    }

    /**
     * getOrdDtl
     * @param bizMsg NWAL1830CMsg
     * @param cpoOrdNum String
     * @param lineNumList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdDtl(NWAL1830CMsg bizMsg, String cpoOrdNum, List<String> lineNumList, boolean isReturnFlg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("InbdOtbdOut", INBD_OTBD.OUTBOUND);
        params.put("InbdOtbdIn", INBD_OTBD.INBOUND);
        if (lineNumList != null && !lineNumList.isEmpty()) {
            params.put("lineNumList", lineNumList);
        }
        if (lineNumList == null) {
            params.put("existingOrder", ZYPConstant.FLG_ON_Y);
        }
        // 2017/1/13 S21_NA#17076 Add Start
        if (isReturnFlg) {
            params.put("isReturnFlg", ZYPConstant.FLG_ON_Y);
        }
        // 2017/1/13 S21_NA#17076 Add End
        return getSsmEZDClient().queryObjectList("getOrdDtl", params);
    }

    /**
     * getOrdPrc
     * @param bizMsg NWAL1830CMsg
     * @param cpoOrdNum String
     * @param lineNumList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdPrc(NWAL1830CMsg bizMsg, String cpoOrdNum, List<String> lineNumList) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("InbdOtbdOut", INBD_OTBD.OUTBOUND);
        params.put("InbdOtbdIn", INBD_OTBD.INBOUND);
        params.put("prcdtlgrpCdBASE", PRC_DTL_GRP.BASE_PRICE); // QC#50548 2019/05/28 Add
        if (lineNumList != null && !lineNumList.isEmpty()) {
            params.put("lineNumList", lineNumList);
        }
        if (lineNumList == null) {
            params.put("existingOrder", ZYPConstant.FLG_ON_Y);
        }
        return getSsmEZDClient().queryObjectList("getOrdPrc", params);
    }

    /**
     * getOrdPrc
     * @param bizMsg NWAL1830CMsg
     * @param cpoOrdNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdSlsCr(NWAL1830CMsg bizMsg, String cpoOrdNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", cpoOrdNum);
        return getSsmEZDClient().queryObjectList("getOrdSlsCr", params);
    }

    /**
     * getOrdCatgBizCtx
     * @param bizMsg NWAL1830CMsg
     * @return  String
     */
    public String getOrdCatgBizCtx(NWAL1830CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.OM_END_LEASE_FIN_OPTIONS);
        params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd_OL.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd_OL.getValue());
        params.put("flgY", ZYPConstant.FLG_ON_Y);

        return (String) getSsmEZDClient().queryObject("getOrdCatgBizCtx", params).getResultObject();
    }

    /**
     * getMachMstrList
     * @param bizMsg NWAL1830CMsg
     * @return  lineMsg
     */
    public S21SsmEZDResult getMachMstrList(NWAL1830CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_OH.getValue());

        return getSsmEZDClient().queryEZDMsgArray("getMachMstrList", params, bizMsg.M);
    }

    /**
     * getMaxPosnNum
     * @param bizMsg NWAL1830CMsg
     * @param configCatg String
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxPosnNum(NWAL1830CMsg bizMsg, String configCatg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("configCatg", configCatg);
        if (CONFIG_CATG.OUTBOUND.equals(configCatg)) {
            // Existing Order
            params.put("cpoOrdNum", bizMsg.cpoOrdNum_OL.getValue());
        } else {
            // Original Loan Order
            params.put("cpoOrdNum", bizMsg.cpoOrdNum_OH.getValue());
        }

        return getSsmEZDClient().queryObject("getMaxPosnNum", params);
    }

    /**
     * getMaxLineNumOutbound
     * @param bizMsg NWAL1830CMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxLineNumOutbound(NWAL1830CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_OL.getValue());

        return getSsmEZDClient().queryObject("getMaxLineNumOutbound", params);
    }

    /**
     * getMaxLineNumInbound
     * @param bizMsg NWAL1830CMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxLineNumInbound(NWAL1830CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_OH.getValue());

        return getSsmEZDClient().queryObject("getMaxLineNumInbound", params);
    }

    // 2017/09/11 S21_NA#19800 add start
    /**
     * getDetailList
     * @param bizMsg NWAL1830CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConvInfoForNotIB(NWAL1830CMsg bizMsg, NWAL1830_BCMsg lineMsg, NWAL1830_BCMsg mainMach) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", mainMach.cpoOrdNum_B2.getValue());
        params.put("mdseCd", lineMsg.mdseCd_SP.getValue());
        params.put("ordLineStsCdCancel", ORD_LINE_STS.CANCELLED);
        params.put("ordHdrStsCdCancel", ORD_HDR_STS.CANCELLED);
        params.put("ordLineCtxCd", ORD_LINE_VAL_SET_KEY);
        params.put("dsOrdLineDrctnOut", DS_ORD_LINE_DRCTN.OUTBOUND);

        return getSsmEZDClient().queryObject("getConvInfoForNotIB", params);
    }

    /**
     * getDsOrdCatgListForCpoUpdateApi
     * @param bizMsg NWAL1830CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsOrdCatgListForValSet(NWAL1830CMsg bizMsg, String actionNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("ordLineCtxCd", ORD_LINE_VAL_SET_KEY);
        params.put("firstBizCtxAttrbTxt", actionNm);

        return getSsmEZDClient().queryObjectList("getDsOrdCatgListForValSet", params);
    }
    // 2017/09/11 S21_NA#19800 add end

    // QC#22641
    public S21SsmEZDResult getOrdDely(NWAL1830CMsg bizMsg, String cpoOrdNum, List<BigDecimal> configPkList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", cpoOrdNum);
        if (configPkList != null && !configPkList.isEmpty()) {
            params.put("configPkList", configPkList);
        }
        return getSsmEZDClient().queryObjectList("getOrdDely", params);
    }

    // QC#22641
    public S21SsmEZDResult getOrdIstl(NWAL1830CMsg bizMsg, String cpoOrdNum, List<BigDecimal> configPkList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", cpoOrdNum);
        if (configPkList != null && !configPkList.isEmpty()) {
            params.put("configPkList", configPkList);
        }
        return getSsmEZDClient().queryObjectList("getOrdIstl", params);
    }

    // QC#22641
    public S21SsmEZDResult getOrdSite(NWAL1830CMsg bizMsg, String cpoOrdNum, List<BigDecimal> configPkList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", cpoOrdNum);
        if (configPkList != null && !configPkList.isEmpty()) {
            params.put("configPkList", configPkList);
        }
        return getSsmEZDClient().queryObjectList("getOrdSite", params);
    }

    // QC#22641
    public S21SsmEZDResult getOrdCtac(NWAL1830CMsg bizMsg, String cpoOrdNum, List<BigDecimal> configPkList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", cpoOrdNum);
        if (configPkList != null && !configPkList.isEmpty()) {
            params.put("configPkList", configPkList);
        }
        return getSsmEZDClient().queryObjectList("getOrdCtac", params);
    }

    // QC#22641
    public Integer isRetailEquipmentOrders(NWAL1830CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS);
        params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd_OH.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd_OH.getValue());
        params.put("dsOrdRsnCd", bizMsg.dsOrdRsnCd_OH.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getCountOrdCatgBizCtx", params);

        return (Integer) result.getResultObject();
    }

    // QC#22641
    // 2018/01/19 QC#23338 mod start
//    public S21SsmEZDResult getDefaultInstTypeWithConfigNum(NWAL1830CMsg bizMsg, String dsOrdPosnNum) {
    public S21SsmEZDResult getDefaultInstTypeWithConfigNum(NWAL1830CMsg bizMsg, String dsOrdPosnNum, BigDecimal origDsCpoConfigPk) {
    // 2018/01/19 QC#23338 mod end
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", bizMsg.cpoOrdNum_OH);
        // 2018/01/19 QC#23338 mod start
//        ssmParam.put("dsOrdPosnNum", dsOrdPosnNum);
        ssmParam.put("origDsCpoConfigPk", origDsCpoConfigPk);
        // 2018/01/19 QC#23338 mod end
        ssmParam.put("ConfigCatgCd", CONFIG_CATG.OUTBOUND);

        return getSsmEZDClient().queryObject("getDefaultInstTypeWithConfigNum", ssmParam);
    }

    public String getRmaReqTpCd(String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("mdseCd", mdseCd);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getRmaReqTpCd", ssmParam);

        return (String) result.getResultObject();
    }

    /**
     * QC#59222. isAllRtrnAccCheck
     * @param glblCmpyCd String
     * @param svcConfigMstrPk BigDecimal
     * @param svcMachMstrPkList List<BigDecimal>
     * @return
     */
    public S21SsmEZDResult isAllRtrnAccCheck(String glblCmpyCd, BigDecimal svcConfigMstrPk, List<BigDecimal> svcMachMstrPkList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcConfigMstrPk", svcConfigMstrPk);
        params.put("svcMachMstrPkList", svcMachMstrPkList);
        params.put("install", SVC_MACH_MSTR_STS.INSTALLED);

        return getSsmEZDClient().queryObject("isAllRtrnAccCheck", params);
    }
    
    // 2022/02/02 QC#59647 Add Start
    public S21SsmEZDResult getSvcMachMstrPk(NWAL1830_BCMsg bizLineMsg) {
        Map<String, Object> ssmParams = new HashMap<String, Object>();
        ssmParams.put("cpoOrdNum", bizLineMsg.cpoOrdNum_B1);
        ssmParams.put("cpoDtlLineNum", bizLineMsg.cpoDtlLineNum_B1);
        ssmParams.put("cpoDtlLineSubNum", bizLineMsg.cpoDtlLineSubNum_B1);
        
        return getSsmEZDClient().queryObjectList("getSvcMachMstrPkList", ssmParams);
    }
    // 2022/02/02 QC#59647 Add End

    // QC#63527 2024/03/14 Start
    public S21SsmEZDResult getCpoDetailInvLocCd(String glblCmpyCd, String cplOrdNum, String cplDtlLineNum, String cplDtlLineSubNum) {

        Map<String, String> ssmParams = new HashMap<String, String>();
        ssmParams.put("glblCmpyCd", glblCmpyCd);
        ssmParams.put("cplOrdNum", cplOrdNum);
        ssmParams.put("cplDtlLineNum", cplDtlLineNum);
        ssmParams.put("cplDtlLineSubNum", cplDtlLineSubNum);

        return getSsmEZDClient().queryObject("getCpoDetailInvLocCd", ssmParams);
    }

    public S21SsmEZDResult getInvtyOwnrCd(String rtlWhCd, String glblCmpyCd) {

        Map<String, String> ssmParams = new HashMap<String, String>();
        ssmParams.put("glblCmpyCd", glblCmpyCd);
        ssmParams.put("whCd", rtlWhCd);

        return getSsmEZDClient().queryObject("getInvtyOwnrCd", ssmParams);
    }

    public S21SsmEZDResult getRtlWh(String glblCmpyCd, String physWhCd) {

        Map<String, String> ssmParams = new HashMap<String, String>();
        ssmParams.put("glblCmpyCd", glblCmpyCd);
        ssmParams.put("physWhCd", physWhCd);

        return getSsmEZDClient().queryObject("getRtlWh", ssmParams);
    }
    // QC#63527 2024/03/14 End
}
