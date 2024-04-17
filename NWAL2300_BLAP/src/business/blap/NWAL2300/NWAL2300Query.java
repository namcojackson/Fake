/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2300;

import static business.blap.NWAL2300.constant.NWAL2300Constant.PULLDOWN_CD_C_R;
import static business.blap.NWAL2300.constant.NWAL2300Constant.REC_TYPE_INVOICE;
import static business.blap.NWAL2300.constant.NWAL2300Constant.REC_TYPE_ORDER;
import static business.blap.NWAL2300.constant.NWAL2300Constant.REC_TYPE_RMA;
import static business.blap.NWAL2300.constant.NWAL2300Constant.STS_CLOSED;
import static business.blap.NWAL2300.constant.NWAL2300Constant.STS_OPEN;
import static business.blap.NWAL2300.constant.NWAL2300Constant.VARCHAR_CONST_SHPG_STS_CD;
import static business.blap.NWAL2300.constant.NWAL2300Constant.XX_FLG_HARD;
import static business.blap.NWAL2300.constant.NWAL2300Constant.XX_FLG_PARENT;
import static business.blap.NWAL2300.constant.NWAL2300Constant.XX_FLG_TIER;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.INV_BOLTMsgArray;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_rtnDtlPMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_SPL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL2300Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/09   Fujitsu         H.Ikeda         Create          N/A
 * 2016/10/24   Fujitsu         S.Iidaka        Update          S21_NA#14607
 * 2017/01/11   Fujitsu         N.Sugiura       Update          QC#16669
 * 2017/01/17   Fujitsu         N.Aoyama        Update          QC#16691
 * 2017/01/31   Fujitsu         H.Nagashima     Update          QC#15624
 * 2017/03/07   Fujitsu         H.Nagashima     Update          QC#17874
 * 2017/08/15   Hitachi         T.Kanasaka      Update          QC#18193
 * 2017/10/20   Fujitsu         H.Sugawara      Update          QC#21773
 * 2017/03/07   Fujitsu         Mz.Takahashi    Update          L3#430 QC#16437
 * 2017/10/27   Fujitsu         R.Nakamura      Update          QC#22125
 * 2017/10/31   Fujitsu         Y.Kanefusa      Update          S21_NA#22031
 * 2017/12/25   Fujitsu         S.Takami        Update          QC#23081
 * 2018/04/06   Fujitsu         T.Aoi           Update          QC#22122
 * 2018/05/13   Fujitsu         Hd.Sugawara     Update          QC#25594
 * 2018/05/29   Fujitsu         S.Takami        Update          QC#21841
 * 2018/06/06   Fujitsu         S.Takami        Update          QC#26081-2
 *</pre>
 */
/**
 * @author q06648
 *
 */
public final class NWAL2300Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL2300Query MY_INSTANCE = new NWAL2300Query();

    /**
     * Private constructor
     */
    private NWAL2300Query() {
        super();
    }

    /**
     * Get the NWAL2300Query instance.
     * @return NWAL2300Query instance
     */
    public static NWAL2300Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getReasonCodeList
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReasonCodeList(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryEZDMsgArray("getReasonCodeList", params, bizMsg.A);

    }

    /**
     * getIneligibleOrder
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getIneligibleOrder(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
        params.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.CREDIT_REBILL_INELIGIBLE_ORDER);
        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        return getSsmEZDClient().queryObjectList("checkOrdCatgValueSet", params);
    }

    /**
     * getCreditRebillData
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCreditData(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
        params.put("crRebilCd", CR_REBIL.CREDIT);
        params.put("ordHdrStsCancel", ORD_HDR_STS.CANCELLED);

        return getSsmEZDClient().queryObjectList("getCreditData", params);
    }

    /**
     * getReturnData
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOutboundData(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
//        params.put("ordLineStsCd", ORD_LINE_STS.CANCELLED);
        params.put("dsOrdLineDrctnOut", DS_ORD_LINE_DRCTN.OUTBOUND);
        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        return getSsmEZDClient().queryObjectList("getOutboundData", params);
    }

    /**
     * getRwsData
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRwsData(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
        List<String> rtrnLineStsList = new ArrayList<String>();
        rtrnLineStsList.add(RTRN_LINE_STS.RWS_CREATED);
        rtrnLineStsList.add(RTRN_LINE_STS.PARTIALLY_RECEIVED);
        rtrnLineStsList.add(RTRN_LINE_STS.RECEIVED);
        params.put("rtrnLineStsList", rtrnLineStsList);

        return getSsmEZDClient().queryObjectList("getRwsData", params);
    }

    /**
     * getAllocatedData
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAllocatedData(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("trxHdrNum", bizMsg.cpoOrdNum_H1.getValue());

        String shpgStsStr = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_SHPG_STS_CD, bizMsg.glblCmpyCd.getValue());
        List<String> shpgStsList = S21StringUtil.toList(shpgStsStr);

        params.put("shpgStsList", shpgStsList);

        return getSsmEZDClient().queryObjectList("getAllocatedData", params);
    }

    /**
     * getNonFinaliedInvoice
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNonFinaliedInvoice(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
        params.put("fnlzInvFlgNonFnlz", ZYPConstant.FLG_OFF_N);
        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        return getSsmEZDClient().queryObjectList("getNonFinaliedInvoice", params);
    }

    /**
     * searchCreditRebillInfo
     * @param bizMsg NMAL7260CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchCreditRebillInfo(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
        // Mod Start 2017/10/20 QC#21773
        //params.put("cpoSrcTpCdCreditRebill", CPO_SRC_TP.CREDIT_AND_REBILL_ENTRY);
        params.put("cpoSrcTpCdCredit", CPO_SRC_TP.CREDIT);
        params.put("cpoSrcTpCdRebill", CPO_SRC_TP.REBILL);
        // Mod End 2017/10/20 QC#21773
        params.put("crRebilCredit", CR_REBIL.CREDIT);
        params.put("crRebilRebill", CR_REBIL.REBILL);
        params.put("ordHdrStsCancel", ORD_HDR_STS.CANCELLED);
        params.put("configCatgOutbound", CONFIG_CATG.OUTBOUND);     //QC#17874 add
        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        if (ZYPCommonFunc.hasValue(bizMsg.xxScrItem130Txt)) {
            String[] exDsInvTpStr = bizMsg.xxScrItem130Txt.getValue().split(",");
            List<String> exDsInvTpList = new ArrayList<String>();
            for (String exDsInvTp : exDsInvTpStr) {
                exDsInvTpList.add(exDsInvTp);
            }
            if (0 < exDsInvTpList.size()) {
                params.put("exDsInvTpList", exDsInvTpList);
            }
        }

        return getSsmEZDClient().queryEZDMsg("searchCreRebiInfo", params, bizMsg);
    }

    /**
     * searchComment
     * @param bizMsg NWAL2300CMsg
     * @param txtTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchComment(NWAL2300CMsg bizMsg, String txtTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H2.getValue());
        params.put("txtTpCd", txtTpCd);

        return getSsmEZDClient().queryObjectList("searchComment", params);
    }

    /**
     * searchTotalInvoiced.
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchTotalInvoiced(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
        params.put("invTpCredit", INV_TP.CREDIT_MEMO);
        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        if (ZYPCommonFunc.hasValue(bizMsg.xxScrItem130Txt)) {
            String[] exDsInvTpStr = bizMsg.xxScrItem130Txt.getValue().split(",");
            List<String> exDsInvTpList = new ArrayList<String>();
            for (String exDsInvTp : exDsInvTpStr) {
                exDsInvTpList.add(exDsInvTp);
            }
            if (0 < exDsInvTpList.size()) {
                params.put("exDsInvTpList", exDsInvTpList);
            }
        }

        return getSsmEZDClient().queryObject("searchTotalInvoiced", params);
    }

    /**
     * isOpenOrder.
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isOpenOrder(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
        params.put("ordLineStsCdClosed", ORD_LINE_STS.CLOSED);
        params.put("ordLineStsCdCanceled", ORD_LINE_STS.CANCELLED);

        return getSsmEZDClient().queryObject("isOpenOrder", params);
    }

    /**
     * isOpenRma.
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isOpenRma(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
        params.put("rtrnLineStsCdClosed", ORD_LINE_STS.CLOSED);
        params.put("rtrnLineStsCdCanceled", ORD_LINE_STS.CANCELLED);

        return getSsmEZDClient().queryObject("isOpenRma", params);
    }

    /**
     * searchOrdPrft
     * @param bizMsg NMAL7260CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchOrdPrft(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("trxHdrNum", bizMsg.cpoOrdNum_H1.getValue());

        return getSsmEZDClient().queryEZDMsg("searchOrdPrft", params, bizMsg);
    }

    /**
     * searchInvSummary
     * @param bizMsg NMAL7260CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchInvSummary(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
        params.put("arTrxTpCd", AR_TRX_TP.INVOICE);
        params.put("invTpCredit", INV_TP.CREDIT_MEMO);
        // 2018/04/06 QC#22122 Add Start
        params.put("ordHdrStsCancel", ORD_HDR_STS.CANCELLED);
        params.put("cpoSrcTpCdCredit", CPO_SRC_TP.CREDIT);
        params.put("cpoSrcTpCdRebill", CPO_SRC_TP.REBILL);
        // 2018/04/06 QC#22122 Add End
        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        if (ZYPCommonFunc.hasValue(bizMsg.xxScrItem130Txt)) {
            String[] exDsInvTpStr = bizMsg.xxScrItem130Txt.getValue().split(",");
            List<String> exDsInvTpList = new ArrayList<String>();
            for (String exDsInvTp : exDsInvTpStr) {
                exDsInvTpList.add(exDsInvTp);
            }
            if (0 < exDsInvTpList.size()) {
                params.put("exDsInvTpList", exDsInvTpList);
            }
        }

        return getSsmEZDClient().queryEZDMsgArray("searchInvSummary", params, bizMsg.B);
    }

    /**
     * searchInvLine
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchInvLine(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
        params.put("stsOpen", STS_OPEN);
        params.put("stsClosed", STS_CLOSED);
        params.put("recTypeInvoice", REC_TYPE_INVOICE);
        params.put("recTypeOrder", REC_TYPE_ORDER);
        params.put("recTypeRMA", REC_TYPE_RMA);
        params.put("dsOrdLineDrctnOut", DS_ORD_LINE_DRCTN.OUTBOUND);
        params.put("dsOrdLineDrctnIn", DS_ORD_LINE_DRCTN.INBOUND);
        params.put("pulldownCdCR", PULLDOWN_CD_C_R);
        params.put("cpoOrdNumCredit", bizMsg.cpoOrdNum_H2.getValue());
        params.put("rowNum", bizMsg.C.length() + 1);
        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        List<String> stsList1 = new ArrayList<String>();
        stsList1.add(SHPG_STS.CANCELLED);
        stsList1.add(SHPG_STS.INVOICED);
        List<String> stsList2 = new ArrayList<String>();
        stsList2.add(RTRN_LINE_STS.CANCELLED);
        stsList2.add(RTRN_LINE_STS.CLOSED);

        params.put("stsList1", stsList1);
        params.put("stsList2", stsList2);

        if (ZYPCommonFunc.hasValue(bizMsg.xxScrItem130Txt)) {
            String[] exDsInvTpStr = bizMsg.xxScrItem130Txt.getValue().split(",");
            List<String> exDsInvTpList = new ArrayList<String>();
            for (String exDsInvTp : exDsInvTpStr) {
                exDsInvTpList.add(exDsInvTp);
            }
            if (0 < exDsInvTpList.size()) {
                params.put("exDsInvTpList", exDsInvTpList);
            }
        }
        // 2018/05/29 QC#21841 Add Start
        params.put("invLineCatgItem", INV_LINE_CATG.ITEM);
        // 2018/05/29 QC#21841 Add End

        return getSsmEZDClient().queryEZDMsgArray("searchInvLine", params, bizMsg.C);
    }

    /**
     * searchReasonCode
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchReasonCode(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H2.getValue());

        return getSsmEZDClient().queryObjectList("searchReasonCode", params);
    }

    /**
     * searchCpoCnt.
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult existCreditRebill(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("addOrigCpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
        // Mod Start 2017/10/20 QC#21773
        //params.put("cpoSrcTpCdRebill", CPO_SRC_TP.CREDIT_AND_REBILL_ENTRY);
        params.put("cpoSrcTpCdCredit", CPO_SRC_TP.CREDIT);
        params.put("cpoSrcTpCdRebill", CPO_SRC_TP.REBILL);
        // Mod End 2017/10/20 QC#21773
        params.put("ordHdrStsCancel", ORD_HDR_STS.CANCELLED);


        return getSsmEZDClient().queryObject("existCreditRebill", params);
    }

    /**
     * checkDetail.
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkDetail(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
        params.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS);
        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        return getSsmEZDClient().queryObject("checkOrdCatgValueSet", params);
    }

    /**
     * checkMainMachine.
     * @param cMsg NWAL2300_CCMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkMainMachine(NWAL2300_CCMsg cMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("invNum", cMsg.invNum_C1.getValue());
        params.put("invBolLineNum", cMsg.invBolLineNum_C1.getValue());
        params.put("invLineNum", cMsg.invLineNum_C1.getValue());
        params.put("invLineSubNum", cMsg.invLineSubNum_C1.getValue());
        params.put("invLineSubTrxNum", cMsg.invLineSubTrxNum_C1.getValue());
        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        return getSsmEZDClient().queryObject("checkMainMachine", params);
    }

    /**
     * getCrRebilRsnCatgCd
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCrRebilRsnCatgCd(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        List<String> crRebillRsnList = new ArrayList<String>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A1.getValue())) {
                crRebillRsnList.add(bizMsg.A.no(i).crRebilRsnCd_A1.getValue());
            }
        }
        params.put("crRebillRsnList", crRebillRsnList);

        return getSsmEZDClient().queryObjectList("getCrRebilRsnCatgCd", params);
    }

    /**
     * getCsmpContrNum
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCsmpContrNum(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("trxHdrNum", bizMsg.cpoOrdNum_H1.getValue());

        return getSsmEZDClient().queryObject("getCsmpContrNum", params);
    }

    /**
     * getDlrRefNum
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDlrRefNum(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("trxHdrNum", bizMsg.cpoOrdNum_H1.getValue());

        return getSsmEZDClient().queryObject("getDlrRefNum", params);
    }

    /**
     * getMdlDescTxt
     * @param glblCmpyCd String
     * @param mdlId BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdlDescTxt(String glblCmpyCd, BigDecimal mdlId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdlId", mdlId);

        return getSsmEZDClient().queryObject("getMdlDescTxt", params);
    }

    /**
     * getFrtCondCdFcc
     * @param bizMsg NWAL2300CMsg
     * @param invBolList INV_BOLTMsgArray 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFrtCondCdFcc(NWAL2300CMsg bizMsg, INV_BOLTMsgArray invBolList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        params.put("invNum", invNum);
        List<String> invNumList = new ArrayList<String>();
        for (int i = 0; i < invBolList.getValidCount(); i++) {
            invNumList.add(invBolList.no(i).invNum.getValue());
        }
        params.put("invNumList", invNumList);
        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        return getSsmEZDClient().queryObject("getFrtCondCdFcc", params);
    }

    /**
     * getLineBizTpCd
     * @param bizMsg NWAL2300CMsg
     * @param glblCmpyCd String
     * @param effDt String
     * @param cpoOrdNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineBizTpCd(NWAL2300CMsg bizMsg, String glblCmpyCd, String effDt, String cpoOrdNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        params.put("effFromDt", effDt);
        params.put("effThruDt", effDt);
        params.put("cpoOrdNum", cpoOrdNum);
        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        if (ZYPCommonFunc.hasValue(bizMsg.xxScrItem130Txt)) {
            String[] exDsInvTpStr = bizMsg.xxScrItem130Txt.getValue().split(",");
            List<String> exDsInvTpList = new ArrayList<String>();
            for (String exDsInvTp : exDsInvTpStr) {
                exDsInvTpList.add(exDsInvTp);
            }
            if (0 < exDsInvTpList.size()) {
                params.put("exDsInvTpList", exDsInvTpList);
            }
        }

        return getSsmEZDClient().queryObject("getLineBizTpCd", params);
    }

    /**
     * getInPoundWt
     * @param glblCmpyCd String
     * @param pkgUomCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInPoundWt(String glblCmpyCd, String pkgUomCd, String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("pkgUomCd", pkgUomCd);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getInPoundWt", params);
    }

    /**
     * getMdlId
     * @param glblCmpyCd String
     * @param svcConfigMstrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdlId(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcConfigMstrPk", svcConfigMstrPk);

        return getSsmEZDClient().queryObject("getMdlId", params);
    }

    /**
     * getSvcMachMstrPk
     * @param glblCmpyCd String
     * @param svcConfigMstrPk BigDecimal
     * @param mdseCd String
     * @param serNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMachMstrPk(String glblCmpyCd, BigDecimal svcConfigMstrPk, String mdseCd, String serNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcConfigMstrPk", svcConfigMstrPk);
        params.put("mdseCd", mdseCd);
        params.put("serNum", serNum);

        return getSsmEZDClient().queryObject("getSvcMachMstrPk", params);
    }

    /**
     * getSvcMachMstrPk
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlWhCd(String glblCmpyCd, String invtyLocCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("invtyLocCd", invtyLocCd);

        return getSsmEZDClient().queryObject("getRtlWhCd", params);
    }

    /**
     * getSerNum
     * @param glblCmpyCd String
     * @param invNum String
     * @param invBolLineNum String
     * @param invLineNum String
     * @param invLineSubNum String
     * @param invLineSubTrxNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSerNum(String glblCmpyCd, String invNum, String invBolLineNum, String invLineNum, String invLineSubNum, String invLineSubTrxNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("invNum", invNum);
        params.put("invBolLineNum", invBolLineNum);
        params.put("invLineNum", invLineNum);
        params.put("invLineSubNum", invLineSubNum);
        params.put("invLineSubTrxNum", invLineSubTrxNum);
        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        return getSsmEZDClient().queryObject("getSerNum", params);
    }

    /**
     * getPriceElementOutbound
     * @param bizMsg NWAL2300CMsg
     * @param invLineMsg NWAL2300_CCMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPriceElementOutbound(NWAL2300CMsg bizMsg, NWAL2300_CCMsg invLineMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("invTpInvoice", INV_TP.INVOICE);
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
        params.put("cpoDtlLineNum", invLineMsg.cpoDtlLineNum_C1.getValue());
        params.put("cpoDtlLineSubNum", invLineMsg.cpoDtlLineSubNum_C1.getValue());
        //QC#156242 add Start
        params.put("invNum", invLineMsg.invNum_C1.getValue());
        params.put("invBolLineNum", invLineMsg.invBolLineNum_C1.getValue());
        // 2018/06/06 QC#26081-2 Add Start
        if (!ZYPCommonFunc.hasValue(invLineMsg.cpoDtlLineNum_C1)) {
            params.put("invLineNum", invLineMsg.invLineNum_C1.getValue());
            params.put("invLineSubNum", invLineMsg.invLineSubNum_C1.getValue());
        }
        // 2018/06/06 QC#26081-2 Add End
        // 2018/05/29 QC#21841 Del Start
//        params.put("invLineNum", invLineMsg.invLineNum_C1.getValue());
//        params.put("invLineSubNum", invLineMsg.invLineSubNum_C1.getValue());
        // 2018/05/29 QC#21841 Del End
        //QC#156242 add End
        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        if (ZYPCommonFunc.hasValue(bizMsg.xxScrItem130Txt)) {
            String[] exDsInvTpStr = bizMsg.xxScrItem130Txt.getValue().split(",");
            List<String> exDsInvTpList = new ArrayList<String>();
            for (String exDsInvTp : exDsInvTpStr) {
                exDsInvTpList.add(exDsInvTp);
            }
            if (0 < exDsInvTpList.size()) {
                params.put("exDsInvTpList", exDsInvTpList);
            }
        }

        return getSsmEZDClient().queryObjectList("getPriceElementOutbound", params);
    }

    /**
     * getReasonCodeCategory
     * @param glblCmpyCd String
     * @param invBolList INV_BOLTMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReasonCodeCategory(String glblCmpyCd, INV_BOLTMsgArray invBolList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        List<String> invNumList = new ArrayList<String>();
        for (int i = 0; i < invBolList.getValidCount(); i++) {
            invNumList.add(invBolList.no(i).invNum.getValue());
        }
        params.put("invNumList", invNumList);
        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        return getSsmEZDClient().queryObject("getReasonCodeCategory", params);
    }

    /**
     * getINV_BOL
     * @param bizMsg NWAL2300CMsg
     * @param cpoOrdNum String
     * @return S21SsmEZDResult
     */
    // 2018/04/06 QC#22122 Mod Start
    //public S21SsmEZDResult getINV_BOL(NWAL2300CMsg bizMsg, String cpoOrdNum) {
    public S21SsmEZDResult getINV_BOL(NWAL2300CMsg bizMsg, String cpoOrdNum, String invNum) {
    // 2018/04/06 QC#22122 Mod End

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        // 2018/04/06 QC#22122 Mod Start
        //params.put("cpoOrdNum", cpoOrdNum);
        if (!ZYPCommonFunc.hasValue(invNum)) {
            params.put("cpoOrdNum", cpoOrdNum);
            params.put("invNum", null);
        } else {
            params.put("cpoOrdNum", null);
            params.put("invNum", invNum);
        }
        // 2018/04/06 QC#22122 Mod End

        if (ZYPCommonFunc.hasValue(bizMsg.xxScrItem130Txt)) {
            String[] exDsInvTpStr = bizMsg.xxScrItem130Txt.getValue().split(",");
            List<String> exDsInvTpList = new ArrayList<String>();
            for (String exDsInvTp : exDsInvTpStr) {
                exDsInvTpList.add(exDsInvTp);
            }
            if (0 < exDsInvTpList.size()) {
                params.put("exDsInvTpList", exDsInvTpList);
            }
        }

        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        return getSsmEZDClient().queryObjectList("getINV_BOL", params);
    }

    /**
     * getShipQtyAmt
     * @param bizMsg NWAL2300CMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param cpoDtlLineNum String
     * @param cpoDtlLineSubNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipQtyAmt(NWAL2300CMsg bizMsg, String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("cpoDtlLineNum", cpoDtlLineNum);
        params.put("cpoDtlLineSubNum", cpoDtlLineSubNum);
        params.put("invTpInvoice", INV_TP.INVOICE);
        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        if (ZYPCommonFunc.hasValue(bizMsg.xxScrItem130Txt)) {
            String[] exDsInvTpStr = bizMsg.xxScrItem130Txt.getValue().split(",");
            List<String> exDsInvTpList = new ArrayList<String>();
            for (String exDsInvTp : exDsInvTpStr) {
                exDsInvTpList.add(exDsInvTp);
            }
            if (0 < exDsInvTpList.size()) {
                params.put("exDsInvTpList", exDsInvTpList);
            }
        }

        // 2018/05/29 QC#21841 Add Start
        params.put("invLineCatgItem", INV_LINE_CATG.ITEM);
        // 2018/05/29 QC#21841 Add End

        return getSsmEZDClient().queryObject("getShipQtyAmt", params);
    }

    /**
     * getSpecCondPrcPk
     * @param bizMsg NWAL2300CMsg
     * @param prcCondTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSpecCondPrcPk(NWAL2300CMsg bizMsg, String prcCondTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("prcCondTpCd", prcCondTpCd);

        return getSsmEZDClient().queryObject("getSpecCondPrcPk", params);
    }

    /**
     * getPmtTermCashDiscCdHder
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPmtTermCashDiscCdHder(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        return getSsmEZDClient().queryObject("getPmtTermCashDiscCdHder", params);
    }

    /**
     * getClosedLineInConfig4Shell
     * @param bizMsg NWAL2300CMsg
     * @param dsOrdPosnNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getClosedLineInConfig4Shell(NWAL2300CMsg bizMsg, String dsOrdPosnNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
        params.put("dsOrdPosnNum", dsOrdPosnNum);
        List<String> coaMdseTpList = new ArrayList<String>();
        coaMdseTpList.add(COA_MDSE_TP.MACHINE);
        coaMdseTpList.add(COA_MDSE_TP.ACCESSORY);
        params.put("coaMdseTpList", coaMdseTpList);

        return getSsmEZDClient().queryObject("getClosedLineInConfig4Shell", params);
    }

    // 2017/10/19 QC#21723 ADD START
    /**
     * getCntManufactureItem
     * @param bizMsg NWAL2300CMsg
     * @param origOrCustMdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCntManufactureItem(NWAL2300CMsg bizMsg, String origOrCustMdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("mnfItemCd", origOrCustMdseCd);

        return getSsmEZDClient().queryObject("getCntManufactureItem", params);
    }
    // 2017/10/19 QC#21723 ADD End

    // QC#12875 ADD
    /**
     * getCntCustMdseXref
     * @param bizMsg NWAL2300CMsg
     * @param origOrCustMdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCntCustMdseXref(NWAL2300CMsg bizMsg, String origOrCustMdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("custMdseCd", origOrCustMdseCd);
        params.put("custMdseXrefEnblFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("getCntCustMdseXref", params);
    }

    // 2016/10/24 S21_NA#14607 Add Start
    /**
     * getSlsRep
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSlsRep(NWAL2300CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsRepTocNm", bizMsg.slsRepTocNm_H1.getValue());
        params.put("slsRepFlg", ZYPConstant.FLG_ON_Y);
        params.put("slsFlg", ZYPConstant.FLG_ON_Y);
        params.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        params.put("rgtnStsCd", RGTN_STS.TERMINATED);

        return getSsmEZDClient().queryObjectList("getSlsRep", params);
    }
    // 2016/10/24 S21_NA#14607 Add End
    // 2017/01/11 QC#16669 ADD START
    /**
     * getSalesCreditForConfigOut
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSalesCreditForConfigOut(NWAL2300CMsg bizMsg) {

        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
        params.put("configCatgCd", CONFIG_CATG.OUTBOUND);
        return getSsmEZDClient().queryObjectList("getSalesCreditForConfigOut", params);
    }
    /**
     * getPriceInfo
     * @param bizMsg NWAL2300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPriceInfo(NWAL2300CMsg bizMsg) {

        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
        return getSsmEZDClient().queryObjectList("getPriceInfo", params);
    }
    // 2017/01/11 QC#16669 ADD END
    // 2017/01/17 QC#16691 ADD START
    /**
     * getCntDropShip
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param orgOrdNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCntDropShip(String glblCmpyCd, String mdseCd, String orgOrdNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        params.put("orgOrdNum", orgOrdNum);

        return getSsmEZDClient().queryObject("getCntDropShip", params);
    }

    /**
     * getMdseInfo
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseInfo(String glblCmpyCd, String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObjectList("getMdseInfo", params);
    }
    // 2017/01/17 QC#16691 ADD E N D

    /**
     * getSvcConfigRefList
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param cpoDtlLineNum String
     * @param cpoDtlLineSubNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcConfigRefList(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("cpoDtlLineNum", cpoDtlLineNum);
        params.put("cpoDtlLineSubNum", cpoDtlLineSubNum);

        return getSsmEZDClient().queryObjectList("getSvcConfigRefList", params);
    }

    /**
     * getSvcDtl
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcDtl(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", dsContrPk);
        List<String> dsContrDtlTpList = new ArrayList<String>();
        dsContrDtlTpList.add(DS_CONTR_DTL_TP.FLEET);
        dsContrDtlTpList.add(DS_CONTR_DTL_TP.AGGREGATE);
        params.put("dsContrDtlTpList", dsContrDtlTpList);
        params.put("svcMemoTpCd", SVC_MEMO_TP.OVERRIDE_SHELL_CONTRACT);

        return getSsmEZDClient().queryObject("getSvcDtl", params);
    }

    /**
     * getSvcPrc
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPrc(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("dsContrPk", dsContrPk);

        return getSsmEZDClient().queryObject("getSvcPrc", params);
    }

    /**
     * getSvcAddlChrgPrcList
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param cpoOrdNum String
     * @param cpoDtlLineNum String
     * @param cpoDtlLineSubNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcAddlChrgPrcList(String glblCmpyCd, BigDecimal dsContrPk, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", dsContrPk);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("cpoDtlLineNum", cpoDtlLineNum);
        params.put("cpoDtlLineSubNum", cpoDtlLineSubNum);
        params.put("addlChrgCatgCd", ADDL_CHRG_CATG.RENTAL);

        return getSsmEZDClient().queryObjectList("getSvcAddlChrgPrcList", params);
    }

    /**
     * getSvcAddlBasePrcRentalList
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param cpoOrdNum String
     * @param cpoDtlLineNum String
     * @param cpoDtlLineSubNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcAddlBasePrcRentalList(String glblCmpyCd, BigDecimal dsContrPk, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", dsContrPk);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("cpoDtlLineNum", cpoDtlLineNum);
        params.put("cpoDtlLineSubNum", cpoDtlLineSubNum);
        params.put("addlChrgCatgCd", ADDL_CHRG_CATG.RENTAL);

        return getSsmEZDClient().queryObjectList("getSvcAddlBasePrcRentalList", params);
    }

    /**
     * getSvcAddlBasePrcAccList
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param cpoOrdNum String
     * @param cpoDtlLineNum String
     * @param cpoDtlLineSubNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcAddlBasePrcAccList(String glblCmpyCd, BigDecimal dsContrPk, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", dsContrPk);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("cpoDtlLineNum", cpoDtlLineNum);
        params.put("cpoDtlLineSubNum", cpoDtlLineSubNum);
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.ACCESSORIES);

        return getSsmEZDClient().queryObjectList("getSvcAddlBasePrcAccList", params);
    }

    /**
     * getsvcUsgPrcList
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @param dsContrCatgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getsvcUsgPrcList(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String dsContrCatgCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", dsContrPk);
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("flgNmP", XX_FLG_PARENT);
        params.put("flgNmH", XX_FLG_HARD);
        params.put("flgNmT", XX_FLG_TIER);
        // START 2017/08/15 T.Kanasaka [QC#18193,DEL]
//        params.put("isRegular" , DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd) ? ZYPConstant.FLG_ON_Y : null);
//        params.put("isFleet" , DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) ? ZYPConstant.FLG_ON_Y : null);
//        params.put("isAggregate", DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) ? ZYPConstant.FLG_ON_Y : null);
        // END 2017/08/15 T.Kanasaka [QC#18193,DEL]
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);

        return getSsmEZDClient().queryObjectList("getSvcUsgPrcList", params);
    }

    public Integer getCountVendor(String shipFromInvtyLocCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("invtyLocCd", shipFromInvtyLocCd);
        params.put("locTpVendor", LOC_TP.VENDOR);

        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getCountVendor", params);

        return (Integer) rslt.getResultObject();
    }

    /**
     * get InBound Order Line Category(Sol#430)
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

        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getInBoundLineCatg", map);

        return (String) rslt.getResultObject();
    }

    // Add Start 2017/10/27 QC#22125
    /**
     * get InBound Order Line Category(Sol#430)
     * @param pMsg NWZC150001CpouCpoBean
     * @return String InBound Order Line Category Code
     */
    public String getSetCompornentChild(String glblCmpyCd, NWAL2300_CCMsg invLineMsg) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", glblCmpyCd);
        map.put("invNum", invLineMsg.invNum_C1.getValue());
        map.put("invLineNum", invLineMsg.invLineNum_C1.getValue());
        map.put("invLineSubNum", invLineMsg.invLineSubNum_C1.getValue());
        map.put("cpoDtlLineNum", invLineMsg.cpoDtlLineNum_C1.getValue());
        map.put("cpoDtlLineSubNum", invLineMsg.cpoDtlLineSubNum_C1.getValue());
        // Add Start 2018/05/13 QC#25594
        map.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getSetCompornentChild", map);

        return (String) rslt.getResultObject();
    }
    // Add End 2017/10/27 QC#22125
    // QC#22031 2017/10/31 Add Start
    public String getRtlWh(String glblCmpyCd, String invtyLocCd){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("invtyLocCd", invtyLocCd);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getRtlWh", params);
        if (ssmResult.isCodeNormal()) {
            return (String) ssmResult.getResultObject();
        }
        return null;
    }
    // QC#22031 2017/10/31 Add End

    // 2017/12/25 QC#23081 Add Start
    public S21SsmEZDResult getDsInvSlsCrTMsgList(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg) {

        Map<String, Object> params = new HashMap<String, Object>(0);
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("invNum", detailDataMsg.invNum_C1.getValue());
        params.put("invBolLineNum", detailDataMsg.invBolLineNum_C1.getValue());
        params.put("invLineNum", detailDataMsg.invLineNum_C1.getValue());
        params.put("invLineSubNum", detailDataMsg.invLineSubNum_C1.getValue());
        params.put("invTrxLineSubNum", detailDataMsg.invLineSubTrxNum_C1.getValue());
        List<String> invLineSplTpCdList = new ArrayList<String>(0);
        invLineSplTpCdList.add(INV_LINE_SPL_TP.DEVIATION);
        invLineSplTpCdList.add(INV_LINE_SPL_TP.ALLOCATION);
        params.put("invLineSplTpCdList", invLineSplTpCdList);
        // Add Start 2018/05/13 QC#25594
        params.put("sysSrcCd", SYS_SRC.S21_ORDER);
        // Add End 2018/05/13 QC#25594

        return getSsmEZDClient().queryObjectList("getDsInvSlsCrTMsgList", params);
    }
    // 2017/12/25 QC#23081 Add End
}
