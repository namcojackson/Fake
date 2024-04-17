/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1840.common;

import static business.blap.NWAL1840.constant.NWAL1840Constant.IDX_3;
import static business.blap.NWAL1840.constant.NWAL1840Constant.SCHD_SUB_LINE_NUM_001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1840.NWAL1840CMsg;
import business.blap.NWAL1840.NWAL1840QueryForPricing;
import business.blap.NWAL1840.NWAL1840_ACMsg;
import business.blap.NWAL1840.NWAL1840_ECMsg;
import business.db.PRC_CATGTMsg;
import business.db.PRC_COND_TPTMsg;
import business.db.SPEC_COND_PRCTMsg;
import business.db.SPEC_COND_PRCTMsgArray;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CALC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Fujitsu         T.Murai         Create          N/A
 * 2016/06/21   Fujitsu         Y.Kanefusa      Update          S21_NA#9437
 * 2016/08/05   Fujitsu         Y.Kanefusa      Update          S21_NA#11885
 * 2016/10/05   Fujitsu         H.ikeda         Update          S21_NA#14482
 * 2017/06/26   Fujitsu         A.Kosai         Update          QC#18332
 * 2018/04/05   Fujitsu         Y.Kanefusa      Update          S21_NA#24860
 * 2018/09/14   Fujitsu         M.Ohno          Update          QC#9700
 * 2019/02/25   Fujitsu         K.Kato          Update          QC#70372
 * 2019/07/31   Fujitsu         C.Hara          Update          QC#51321
 * </pre>
 */
public class NWAL1840CommonLogicForPricing {

    /**
     * Call NWZC1570 Pricing API (02:Get Base Price Mode)
     * @param bizMsg NWAL1840CMsg
     * @return NWZC157001PMsg
     */
    public static NWZC157001PMsg callPricingApiOfGetBasePriceMode(NWAL1840CMsg bizMsg) {

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        setHdrParam(bizMsg, prcApiPMsg, NWZC157001.GET_BASE_PRICE);
        setLineParamForGetBasePriceMode(bizMsg, prcApiPMsg);

        // call NWZC1570 Pricing API
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);

        return prcApiPMsg;
    }

    /**
     * Call NWZC1570 Pricing API (03:Get Line Price Mode)
     * @param bizMsg NWAL1840CMsg
     * @return NWZC157001PMsg
     */
    public static NWZC157001PMsg callPricingApiGetOrderAllMode(NWAL1840CMsg bizMsg) {

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        // QC#11885 2016/08/05 Mod Start
        //setHdrParam(bizMsg, prcApiPMsg, NWZC157001.GET_LINE_PRICE);
        setHdrParam(bizMsg, prcApiPMsg, NWZC157001.GET_ORDER_ALL);
        // QC#11885 2016/08/05 Mod End
        setLineParamForGetOrderAllMode(bizMsg, prcApiPMsg);

        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                if (msgId.endsWith("E")) {
                    return null;
                }
            }
        }

        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            if (S21ApiUtil.isXxMsgId(prcApiPMsg.NWZC157002PMsg.no(i))) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg.NWZC157002PMsg.no(i));
                for (S21ApiMessage msg : msgList) {
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);
                    if (msgId.endsWith("E")) {
                        return null;
                    }
                }
            }
        }
        return prcApiPMsg;
    }

    /**
     * Call NWZC1570 Pricing API (05:Recalc Mode)
     * @param bizMsg NWAL1840CMsg
     * @return NWZC157001PMsg
     */
    public static NWZC157001PMsg callPricingApiOfRecalcMode1(NWAL1840CMsg bizMsg) {

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        setHdrParam(bizMsg, prcApiPMsg, NWZC157001.RECALC);
        setLineParamForGetBasePriceMode(bizMsg, prcApiPMsg);
        setPriceElementForManual(bizMsg, prcApiPMsg);

        // call NWZC1570 Pricing API
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);

        return prcApiPMsg;
    }

    /**
     * Set Price API Header Parameter
     * @param bizMsg NWAL1840CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @param modeCd Mode Code
     */
    public static void setHdrParam(NWAL1840CMsg bizMsg, NWZC157001PMsg prcApiPMsg, String modeCd) {

        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, modeCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, bizMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcContrNum, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.custIssPoNum, bizMsg.custIssPoNum);
        // QC#14482 2016/10/05 Mod Start
        if (NWZC157001.GET_BASE_PRICE.equals(modeCd)) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
        }
        // QC#14482 2016/10/05 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.spclHdlgTpCd, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);
    }

    /**
     * Set Price API Line Parameter For Get Base Price Mode
     * @param bizMsg NWAL1840CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @param modeCd Mode Code
     */
    private static void setLineParamForGetBasePriceMode(NWAL1840CMsg bizMsg, NWZC157001PMsg prcApiPMsg) {

        NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(0);
        NWAL1840_ACMsg itemLineMsg = bizMsg.A.no(bizMsg.xxCellIdx.getValueInt());
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineNum, itemLineMsg.xxLineNum_A);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineSubNum, SCHD_SUB_LINE_NUM_001);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcBaseDt, bizMsg.prcBaseDt); // QC#9437 2016/06/21 Add
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.billToCustCd, bizMsg.billToCustLocCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shipToCustCd, bizMsg.shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_SH, bizMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_BL, bizMsg.billToCustAcctCd);
        // Get Price Category Code
        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgNm)) {
            String prcCatgCd = getPrcCatgCd(bizMsg, bizMsg.prcCatgNm.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd, prcCatgCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCatgCd, prcCatgCd);
        }
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcContrNum, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaBrCd, bizMsg.coaBrCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ccyCd, getCcyCd(bizMsg));
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdseCd, itemLineMsg.mdseCd_A);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.pkgUomCd, itemLineMsg.pkgUomCd_A);
//        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsOrdLineCatgCd, itemLineMsg.dsOrdLineCatgCd_B);
//        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsOrdLineCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordQty, itemLineMsg.schdAllwQty_A);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordCustUomQty, itemLineMsg.schdAllwQty_A);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdlId, bizMsg.mdlId);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctyAddr_SH, bizMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.stCd_SH, bizMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.postCd_SH, bizMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctryCd_SH, bizMsg.shipToCtryCd);
        // QC#70372 2019/02/25 Mod Start
        //ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.slsRepOrSlsTeamTocCd, getWriterRepCd(bizMsg));
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.slsRepOrSlsTeamTocCd, bizMsg.slsRepTocCd);
        // QC#70372 2019/02/25 Mod End
//        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.rtlWhCd, itemLineMsg.rtlWhCd_B);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtCondCd, bizMsg.frtCondCd);
//        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtChrgToCd, bizMsg.frtChrgToCd);
  //      ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtChrgMethCd, bizMsg.frtChrgMethCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaExtnCd, bizMsg.coaExtnCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxUnitPrc, itemLineMsg.dealNetUnitPrcAmt_A); // QC#6480 2016/06/13 Add

        // QC#6480 2016/06/13 Add Start
        int prcCnt = 0;
        String cpoDtlLineNum = linePrcApiPMsg.trxLineNum.getValue();
        String cpoDtlLineSubNum = SCHD_SUB_LINE_NUM_001;
        String configCatgCd = CONFIG_CATG.OUTBOUND;
        Map<String, String> prcCondTpMap = new HashMap<String, String>();
        boolean manualFlg = false;
        for (int j = 0; j < bizMsg.E.getValidCount(); j++) {
            NWAL1840_ECMsg priceElementMsg = bizMsg.E.no(j);

            String schdAgmtLineNumE = priceElementMsg.schdAgmtLineNum_E.getValue().toString();
            String sachdAgmtLineSubNumE = SCHD_SUB_LINE_NUM_001;
            String configCatgCdLP = CONFIG_CATG.OUTBOUND;
            if ((cpoDtlLineNum.equals(schdAgmtLineNumE) && cpoDtlLineSubNum.equals(sachdAgmtLineSubNumE)) && configCatgCd.equals(configCatgCdLP)) {
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).trxLineNum, schdAgmtLineNumE);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).trxLineSubNum, sachdAgmtLineSubNumE);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).configCatgCd, CONFIG_CATG.OUTBOUND);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcCondTpCd, priceElementMsg.prcCondTpCd_E);

                String prcCondTpDescTxt = null;
                if (ZYPCommonFunc.hasValue(priceElementMsg.prcCondTpCd_E)) {
                    prcCondTpDescTxt = prcCondTpMap.get(priceElementMsg.prcCondTpCd_E.getValue());
                    if (null == prcCondTpDescTxt) {
                        prcCondTpDescTxt = getPrcCondTpDescTxt(bizMsg.glblCmpyCd.getValue(), priceElementMsg.prcCondTpCd_E.getValue());
                        if (null != prcCondTpDescTxt) {
                            prcCondTpMap.put(priceElementMsg.prcCondTpCd_E.getValue(), prcCondTpDescTxt);
                        } else {
                            prcCondTpMap.put(priceElementMsg.prcCondTpCd_E.getValue(), "");
                        }
                    }
                }

                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcCondTpDescTxt, prcCondTpDescTxt);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcDtlGrpCd, priceElementMsg.prcDtlGrpCd_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcJrnlGrpCd, priceElementMsg.prcJrnlGrpCd_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcCatgCd, bizMsg.prcCatgCd);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcCondManEntryFlg, priceElementMsg.prcCondManEntryFlg_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcCondManAddFlg, priceElementMsg.prcCondManAddFlg_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcCondManDelFlg, priceElementMsg.prcCondManDelFlg_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).pkgUomCd, priceElementMsg.pkgUomCd_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcCondUnitCd, priceElementMsg.prcCondUnitCd_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).autoPrcCcyCd, priceElementMsg.autoPrcCcyCd_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).autoPrcAmtRate, priceElementMsg.autoPrcAmtRate_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).calcPrcAmtRate, priceElementMsg.calcPrcAmtRate_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).unitPrcAmt, priceElementMsg.unitPrcAmt_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).dsMdsePrcPk, priceElementMsg.dsMdsePrcPk_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).specCondPrcPk, priceElementMsg.specCondPrcPk_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).ordPrcCalcBasePk, priceElementMsg.schdAgmtPrcCalcBasePk_E);
                // 2018/09/14 QC#9700 add start
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcRuleApplyFlg, priceElementMsg.prcRuleApplyFlg_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).ordPrcCalcBasePk, priceElementMsg.schdAgmtPrcCalcBasePk_E);
                // 2018/09/14 QC#9700 add end
                if(PRC_DTL_GRP.BASE_PRICE.equals(priceElementMsg.prcDtlGrpCd_E.getValue())){
                    if (itemLineMsg.dealNetUnitPrcAmt_A.getValue().compareTo(priceElementMsg.unitPrcAmt_E.getValue()) != 0) {
                        manualFlg =  true;
                    }
                }
                prcCnt++;
            }
        }
        linePrcApiPMsg.NWZC157003PMsg.setValidCount(prcCnt);
        if(manualFlg){
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
        }else{
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
        }
        // QC#6480 2016/06/13 Add End

        prcApiPMsg.NWZC157002PMsg.setValidCount(1);
    }

    /**
     * Set Price API PriceInfo Parameter For Get Base Price Mode
     * @param bizMsg NWAL1840CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @param modeCd Mode Code
     */
    private static void setPriceElementForManual(NWAL1840CMsg bizMsg, NWZC157001PMsg prcApiPMsg) {

        // Detail : Line
        NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(0);

        NWAL1840_ACMsg lineMsg = bizMsg.A.no(bizMsg.xxCellIdx.getValueInt());

        String xxLineNum = lineMsg.xxLineNum_A.getValue();
        String cpoDtlLineNum = lineMsg.schdAgmtLineNum_A.getValue().toString();
        String cpoDtlLineSubNum = SCHD_SUB_LINE_NUM_001;
        String configCatgCd = CONFIG_CATG.OUTBOUND;

        int prcCnt = 0;
        Map<String, String> prcCondTpMap = new HashMap<String, String>();
        for (int j = 0; j < bizMsg.E.getValidCount(); j++) {
            NWAL1840_ECMsg priceElementMsg = bizMsg.E.no(j);

            String xxLineNumE = priceElementMsg.xxLineNum_E.getValue();
            String schdAgmtLineNumE = priceElementMsg.schdAgmtLineNum_E.getValue().toString();
            String sachdAgmtLineSubNumE = SCHD_SUB_LINE_NUM_001;
            String configCatgCdLP = CONFIG_CATG.OUTBOUND;
            if ((xxLineNum.equals(xxLineNumE) || (cpoDtlLineNum.equals(schdAgmtLineNumE) && cpoDtlLineSubNum.equals(sachdAgmtLineSubNumE))) && configCatgCd.equals(configCatgCdLP)) {
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).trxLineNum, schdAgmtLineNumE);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).trxLineSubNum, sachdAgmtLineSubNumE);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).configCatgCd, CONFIG_CATG.OUTBOUND);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcCondTpCd, priceElementMsg.prcCondTpCd_E);

                String prcCondTpDescTxt = null;
                if (ZYPCommonFunc.hasValue(priceElementMsg.prcCondTpCd_E)) {
                    prcCondTpDescTxt = prcCondTpMap.get(priceElementMsg.prcCondTpCd_E.getValue());
                    if (null == prcCondTpDescTxt) {
                        prcCondTpDescTxt = getPrcCondTpDescTxt(bizMsg.glblCmpyCd.getValue(), priceElementMsg.prcCondTpCd_E.getValue());
                        if (null != prcCondTpDescTxt) {
                            prcCondTpMap.put(priceElementMsg.prcCondTpCd_E.getValue(), prcCondTpDescTxt);
                        } else {
                            prcCondTpMap.put(priceElementMsg.prcCondTpCd_E.getValue(), "");
                        }
                    }
                }

                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcCondTpDescTxt, prcCondTpDescTxt);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcDtlGrpCd, priceElementMsg.prcDtlGrpCd_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcJrnlGrpCd, priceElementMsg.prcJrnlGrpCd_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcCatgCd, bizMsg.prcCatgCd);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcCondManEntryFlg, priceElementMsg.prcCondManEntryFlg_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcCondManAddFlg, priceElementMsg.prcCondManAddFlg_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcCondManDelFlg, priceElementMsg.prcCondManDelFlg_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).pkgUomCd, priceElementMsg.pkgUomCd_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcCondUnitCd, priceElementMsg.prcCondUnitCd_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).autoPrcCcyCd, priceElementMsg.autoPrcCcyCd_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).autoPrcAmtRate, priceElementMsg.autoPrcAmtRate_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).calcPrcAmtRate, priceElementMsg.calcPrcAmtRate_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).unitPrcAmt, priceElementMsg.unitPrcAmt_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).dsMdsePrcPk, priceElementMsg.dsMdsePrcPk_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).specCondPrcPk, priceElementMsg.specCondPrcPk_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).ordPrcCalcBasePk, priceElementMsg.schdAgmtPrcCalcBasePk_E);
                // 2018/09/14 QC#9700 add start
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).prcRuleApplyFlg, priceElementMsg.prcRuleApplyFlg_E);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.NWZC157003PMsg.no(prcCnt).ordPrcCalcBasePk, priceElementMsg.schdAgmtPrcCalcBasePk_E);
                // 2018/09/14 QC#9700 add end
                prcCnt++;
            }
        }
        linePrcApiPMsg.NWZC157003PMsg.setValidCount(prcCnt);

    }

    /**
     * Set Price API Line Parameter For Get Order All Mode
     * @param bizMsg NWAL1840CMsg
     * @param prcApiPMsg NWZC157001PMsg
     */
    public static void setLineParamForGetOrderAllMode(NWAL1840CMsg bizMsg, NWZC157001PMsg prcApiPMsg) {

        int prcLineValidCnt = prcApiPMsg.NWZC157002PMsg.getValidCount();
        // QC#24860 2018/04/05 Add Start
        String dsOrdLineCatgCd =  getDsLineCatgCd(bizMsg);
        // QC#24860 2018/04/05 Add End

        for (int itemLineCnt = 0; itemLineCnt < bizMsg.A.getValidCount() && prcLineValidCnt < prcApiPMsg.NWZC157002PMsg.length(); itemLineCnt++) {
            NWAL1840_ACMsg itemLineMsg = bizMsg.A.no(itemLineCnt);

            if (ZYPConstant.FLG_ON_Y.equals(itemLineMsg.schdAgmtLineCancFlg_A.getValue())) {
                continue;
            }

            // Skip Set Component
            String[] xxLineNumArray = itemLineMsg.xxLineNum_A.getValue().split("\\.");
            if (xxLineNumArray.length == IDX_3 && BigDecimal.ZERO.compareTo(new BigDecimal(xxLineNumArray[2])) != 0) {
                continue;
            }

            NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(prcLineValidCnt);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineNum, itemLineMsg.xxLineNum_A);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineSubNum, SCHD_SUB_LINE_NUM_001);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcBaseDt, bizMsg.prcBaseDt); // QC#9437 2016/06/21 Add
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.billToCustCd, bizMsg.billToCustLocCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shipToCustCd, bizMsg.shipToCustLocCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_SH, bizMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_BL, bizMsg.billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCatgCd, getPrcCatgCd(bizMsg, bizMsg.prcCatgNm.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcContrNum, bizMsg.prcContrNum);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaBrCd, bizMsg.coaBrCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ccyCd, getCcyCd(bizMsg));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdseCd, itemLineMsg.mdseCd_A);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.pkgUomCd, itemLineMsg.pkgUomCd_A);
            // QC#24860 2018/04/05 Add Start
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsOrdLineCatgCd, dsOrdLineCatgCd);
            // QC#24860 2018/04/05 Add End
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordQty, itemLineMsg.schdAllwQty_A);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordCustUomQty, itemLineMsg.schdAllwQty_A);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdlId, bizMsg.mdlId);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctyAddr_SH, bizMsg.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.stCd_SH, bizMsg.shipToStCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.postCd_SH, bizMsg.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctryCd_SH, bizMsg.shipToCtryCd);
            // QC#70372 2019/02/25 Mod Start
            //ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.slsRepOrSlsTeamTocCd, getWriterRepCd(bizMsg));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.slsRepOrSlsTeamTocCd, bizMsg.slsRepTocCd);
            // QC#70372 2019/02/25 Mod End
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtCondCd, bizMsg.frtCondCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtChrgToCd, bizMsg.frtChrgToCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtChrgMethCd, bizMsg.frtChrgMethCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaExtnCd, bizMsg.coaExtnCd);
            // QC#6480 2016/06/13 Add Start
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxUnitPrc, itemLineMsg.dealNetUnitPrcAmt_A);
            // QC#6480 2016/06/13 Add End

            // Pricing Element
            String xxLineNum = itemLineMsg.xxLineNum_A.getValue();
            String schdAgmtLineNum = itemLineMsg.schdAgmtLineNum_A.getValue().toString();
            // String splyQuoteDtlLineSubNum = itemLineMsg.splyQuoteDtlLineSubNum_B.getValue();
            int prcElmntCnt = 0;
            Map<String, String> prcCondTpMap = new HashMap<String, String>();

            boolean manualFlg = false;
            for (int j = 0; j < bizMsg.E.getValidCount(); j++) {
                NWAL1840_ECMsg prcCalcMsg = bizMsg.E.no(j);

                if (xxLineNum.equals(prcCalcMsg.xxLineNum_E.getValue()) || (schdAgmtLineNum.equals(prcCalcMsg.schdAgmtLineNum_E.getValue().toString()))) {
                    NWZC157003PMsg prcElmntApiPMsg = linePrcApiPMsg.NWZC157003PMsg.no(prcElmntCnt);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.trxLineNum, prcCalcMsg.schdAgmtLineNum_E.getValue().toString());
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.trxLineSubNum, SCHD_SUB_LINE_NUM_001);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcCondTpCd, prcCalcMsg.prcCondTpCd_E);

                    String prcCondTpDescTxt = null;

                    if (ZYPCommonFunc.hasValue(prcCalcMsg.prcCondTpCd_E)) {
                        prcCondTpDescTxt = prcCondTpMap.get(prcCalcMsg.prcCondTpCd_E.getValue());
                        if (!ZYPCommonFunc.hasValue(prcCondTpDescTxt)) {
                            prcCondTpDescTxt = getPrcCondTpDescTxt(bizMsg.glblCmpyCd.getValue(), prcCalcMsg.prcCondTpCd_E.getValue());
                            if (null != prcCondTpDescTxt) {
                                prcCondTpMap.put(prcCalcMsg.prcCondTpCd_E.getValue(), prcCondTpDescTxt);
                            }
                        }
                    }

                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcCondTpDescTxt, prcCondTpDescTxt);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcDtlGrpCd, prcCalcMsg.prcDtlGrpCd_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcJrnlGrpCd, prcCalcMsg.prcJrnlGrpCd_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcCatgCd, bizMsg.prcCatgCd);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcCondManEntryFlg, prcCalcMsg.prcCondManEntryFlg_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcCondManAddFlg, prcCalcMsg.prcCondManAddFlg_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcCondManDelFlg, prcCalcMsg.prcCondManDelFlg_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.pkgUomCd, prcCalcMsg.pkgUomCd_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcCondUnitCd, prcCalcMsg.prcCondUnitCd_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcCalcMethCd, prcCalcMsg.prcCalcMethCd_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.autoPrcCcyCd, prcCalcMsg.autoPrcCcyCd_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.autoPrcAmtRate, prcCalcMsg.autoPrcAmtRate_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.maxPrcAmtRate, prcCalcMsg.maxPrcAmtRate_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.minPrcAmtRate, prcCalcMsg.minPrcAmtRate_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.manPrcAmtRate, prcCalcMsg.manPrcAmtRate_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.calcPrcAmtRate, prcCalcMsg.calcPrcAmtRate_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.unitPrcAmt, prcCalcMsg.unitPrcAmt_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.dsMdsePrcPk, prcCalcMsg.dsMdsePrcPk_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.specCondPrcPk, prcCalcMsg.specCondPrcPk_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.frtPerWtPk, prcCalcMsg.frtPerWtPk_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.ordPrcCalcBasePk, prcCalcMsg.schdAgmtPrcCalcBasePk_E);
                    // 2018/09/14 QC#9700 add start
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcRuleApplyFlg, prcCalcMsg.prcRuleApplyFlg_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcRulePrcdPk, prcCalcMsg.schdAgmtPrcCalcBasePk_E);
                    // 2018/09/14 QC#9700 add end
                    
                    // QC#6480 2016/06/06 Add Start
                    if(PRC_DTL_GRP.BASE_PRICE.equals(prcCalcMsg.prcDtlGrpCd_E.getValue())){
                        if (itemLineMsg.dealNetUnitPrcAmt_A.getValue().compareTo(prcCalcMsg.unitPrcAmt_E.getValue()) != 0) {
                            manualFlg =  true;
                        }
                    }
                    // QC#6480 2016/06/06 Add End
                    prcElmntCnt++;
                }
            }
            linePrcApiPMsg.NWZC157003PMsg.setValidCount(prcElmntCnt);
            // QC#6480 2016/06/06 Add Start
            if(manualFlg){
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
            }else{
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
            }
            // QC#6480 2016/06/06 Add End
            prcLineValidCnt++;
        }
        prcApiPMsg.NWZC157002PMsg.setValidCount(prcLineValidCnt);
    }

    /**
     * Get Price Category Code
     * @param bizMsg NWAL1840CMsg
     * @param prcCatgNm Price Category Name
     * @return Price Category Code
     */
    private static String getPrcCatgCd(NWAL1840CMsg bizMsg, String prcCatgNm) {

        S21SsmEZDResult ssmRslt = NWAL1840QueryForPricing.getInstance().getPrcCatgCd(bizMsg, prcCatgNm);
        if (ssmRslt.isCodeNormal()) {
            return (String) ssmRslt.getResultObject();
        }

        return null;
    }

    /**
     * Get CCY Code
     * @param bizMsg NWAL1840CMsg
     * @return CCY Code
     */
    private static String getCcyCd(NWAL1840CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd)) {
            PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(prcCatgTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgCd, bizMsg.prcCatgCd);
            prcCatgTMsg = (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prcCatgTMsg);

            if (null != prcCatgTMsg) {
                return prcCatgTMsg.ccyCd.getValue();
            }
        }

        return null;
    }

    // QC#70372 2019/02/25 Del Start
    ///**
    // * Get Writer Rep Code
    // * @param bizMsg NWAL1840CMsg
    // * @return Writer Rep Code
    // */
    //private static String getWriterRepCd(NWAL1840CMsg bizMsg) {
    //
    //    for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
    //        String roleTpCd = bizMsg.C.no(i).slsRepRoleTpCd_C.getValue();
    //        if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(roleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(roleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(roleTpCd)) {
    //            return roleTpCd;
    //        }
    //    }
    //
    //    return null;
    //}
    // QC#70372 2019/02/25 Del End

    /**
     * Get Price Condition Type Desc Text
     * @param glblCmpyCd String
     * @param prcCondTpCd Price Condition Type Code
     * @return Price Condition Type Desc Text
     */
    private static String getPrcCondTpDescTxt(String glblCmpyCd, String prcCondTpCd) {

        PRC_COND_TPTMsg prcCondTpTMsg = getPrcCondTp(glblCmpyCd, prcCondTpCd);
        if (null != prcCondTpTMsg) {
            return prcCondTpTMsg.prcCondTpDescTxt.getValue();
        }

        return null;
    }

    /**
     * Get Price Condition Type
     * @param bizMsg NWAL1840CMsg
     * @param prcCondTpCd Price Condition Type Code
     * @return Price Condition Type
     */
    private static PRC_COND_TPTMsg getPrcCondTp(String glblCmpyCd, String prcCondTpCd) {

        PRC_COND_TPTMsg prcCondTpTMsg = new PRC_COND_TPTMsg();
        ZYPEZDItemValueSetter.setValue(prcCondTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcCondTpTMsg.prcCondTpCd, prcCondTpCd);
        return (PRC_COND_TPTMsg) S21CodeTableAccessor.findByKey(prcCondTpTMsg);
    }

    /**
     * Get BASE Price Element From Business Message
     * @param bizMsg NWAL1840CMsg
     * @param itemLineMsg NWAL1840_BCMsg
     * @return BASE Price Element
     */
    public static NWAL1840_ECMsg getBasePrcData(NWAL1840CMsg bizMsg, NWAL1840_ACMsg itemLineMsg) {

        String xxLineNum = itemLineMsg.xxLineNum_A.getValue();
        BigDecimal schdAgmtLineNum = itemLineMsg.schdAgmtLineNum_A.getValue();

        for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
            NWAL1840_ECMsg prcCalcLine = bizMsg.E.no(i);
            if (xxLineNum.equals(prcCalcLine.xxLineNum_E.getValue()) || (schdAgmtLineNum.compareTo(prcCalcLine.schdAgmtLineNum_E.getValue()) == 0)
                    && PRC_DTL_GRP.BASE_PRICE.equals(prcCalcLine.prcDtlGrpCd_E.getValue())) {
                return prcCalcLine;
            }
        }

        // Set Manual Data
        NWAL1840_ECMsg prcCalcLine = bizMsg.E.no(bizMsg.E.getValidCount());
        if (ZYPCommonFunc.hasValue(itemLineMsg.dealPrcListPrcAmt_A)) {
            ZYPEZDItemValueSetter.setValue(prcCalcLine.manPrcAmtRate_E, itemLineMsg.dealPrcListPrcAmt_A);
        } else {
            ZYPEZDItemValueSetter.setValue(prcCalcLine.manPrcAmtRate_E, BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(prcCalcLine.prcCondTpCd_E, PRC_COND_TP.BASE_PRICE);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.prcCondManEntryFlg_E, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.prcCondManAddFlg_E, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.prcCondManDelFlg_E, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.prcDtlGrpCd_E, PRC_DTL_GRP.BASE_PRICE);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.prcCondUnitCd_E, PRC_COND_UNIT.AMT);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.prcCalcMethCd_E, PRC_CALC_METH.EACH_ACCOUNT);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.autoPrcCcyCd_E, CCY.US_DOLLAR);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.autoPrcAmtRate_E, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.calcPrcAmtRate_E, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.unitPrcAmt_E, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.schdAgmtLineNum_E, schdAgmtLineNum);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.pkgUomCd_E, itemLineMsg.pkgUomCd_A);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.specCondPrcPk_E, getSpecCondPrcPk(bizMsg));
        // 2018/09/14 QC#9700 Add start
        ZYPEZDItemValueSetter.setValue(prcCalcLine.prcRuleApplyFlg_E, ZYPConstant.FLG_OFF_N);
        // 2018/09/14 QC#9700 Add end
        bizMsg.E.setValidCount(bizMsg.E.getValidCount() + 1);

        return prcCalcLine;
    }

    /**
     * Get Spec Condition Price PK
     * @param bizMsg NWAL1840CMsg
     * @return Spec Condition Price PK
     */
    private static BigDecimal getSpecCondPrcPk(NWAL1840CMsg bizMsg) {

        SPEC_COND_PRCTMsg inTMsg = new SPEC_COND_PRCTMsg();
        inTMsg.setSQLID("004");
        inTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("prcCondTpCd01", PRC_COND_TP.BASE_PRICE);
        SPEC_COND_PRCTMsgArray tMsgArray = (SPEC_COND_PRCTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);

        if (tMsgArray == null || tMsgArray.length() == 0) {
            return BigDecimal.ONE;
        }

        return tMsgArray.no(0).specCondPrcPk.getValue();
    }

    /**
     * Set Price Elements
     * @param bizMsg NWAL1840CMsg
     * @param prcApiPMsg NWZC157001PMsg
     */
    public static void setPriceElement(NWAL1840CMsg bizMsg, NWZC157001PMsg prcApiPMsg) {

        ZYPTableUtil.clear(bizMsg.E);

        Map<String, NWAL1840_ACMsg> itemLineMsgMap = new HashMap<String, NWAL1840_ACMsg>();
        setAmt(bizMsg, prcApiPMsg, itemLineMsgMap);
        setCalBaseData(bizMsg, prcApiPMsg, itemLineMsgMap);
    }

    /**
     * Set Amount
     * @param bizMsg NWAL1840CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @param itemLineMsgMap Item Line Map
     */
    private static void setAmt(NWAL1840CMsg bizMsg, NWZC157001PMsg prcApiPMsg, Map<String, NWAL1840_ACMsg> itemLineMsgMap) {

        BigDecimal totNetPrcAmt = BigDecimal.ZERO;
        BigDecimal totTaxAmt = BigDecimal.ZERO;
        BigDecimal totFrtChrgAmt = BigDecimal.ZERO;
        BigDecimal totAmt = BigDecimal.ZERO;
        StringBuffer itemLineMsgKey = new StringBuffer();

        for (int i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
            NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(i);

            if (CONFIG_CATG.OUTBOUND.equals(prcTotalPMsg.configCatgCd.getValue())) {
                totNetPrcAmt = totNetPrcAmt.add(prcTotalPMsg.xxTotNetPrcAmt.getValue());
                totTaxAmt = totTaxAmt.add(prcTotalPMsg.xxTotTaxAmt.getValue());
                totFrtChrgAmt = totFrtChrgAmt.add(prcTotalPMsg.xxTotFrtAmt.getValue());
                totAmt = totAmt.add(prcTotalPMsg.xxTotAmt.getValue());

                String trxLineNum = prcTotalPMsg.trxLineNum.getValue();
                String trxLineSubNum = prcTotalPMsg.trxLineSubNum.getValue();

                itemLineMsgKey.setLength(0);
                itemLineMsgKey = itemLineMsgKey.append(trxLineNum);
                itemLineMsgKey = itemLineMsgKey.append(".");
                itemLineMsgKey = itemLineMsgKey.append(trxLineSubNum);

                // Set Item Line Amount
                NWAL1840_ACMsg itemLineMsg = getItemLineMsg(bizMsg, trxLineNum);
                if (itemLineMsg != null) {
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.dealNetUnitPrcAmt_A, prcTotalPMsg.xxUnitGrsPrcAmt);
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.dealPrcListPrcAmt_A, prcTotalPMsg.xxUnitGrsPrcAmt);
           //         ZYPEZDItemValueSetter.setValue(itemLineMsg.schdAgmtLineDealNetAmt_A, prcTotalPMsg.xxTotNetPrcAmt);
                    // QC#18332 2017/06/26 Mod Start
//                    ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotDiscAmt_A, prcTotalPMsg.xxTotDiscAmt);
                    // 2019/07/31 QC#51321 Add Start
                    if (BigDecimal.ZERO.compareTo(itemLineMsg.schdAllwQty_A.getValue()) == 0){
                        ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotDiscAmt_A, BigDecimal.ZERO);
                    } else {
                    // 2019/07/31 QC#51321 Add End
                        ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotDiscAmt_A, prcTotalPMsg.xxTotDiscAmt.getValue().divide(itemLineMsg.schdAllwQty_A.getValue(), BigDecimal.ROUND_HALF_UP));
                    }
                    // QC#18332 2017/06/26 Mod End
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.schdAgmtLineDealFrtAmt_A, prcTotalPMsg.xxTotFrtAmt);
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.schdAgmtLineDealTaxAmt_A, prcTotalPMsg.xxTotTaxAmt);
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotAmt_A, prcTotalPMsg.xxTotAmt);

                    // Set Sub Total
                    BigDecimal uomQty = itemLineMsg.schdAllwQty_A.getValue();
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.schdAgmtLineDealNetAmt_A, uomQty.multiply(prcTotalPMsg.xxUnitNetPrcAmt.getValue()));

                    // Set Line Total
                    BigDecimal totLineAmt = itemLineMsg.schdAgmtLineDealNetAmt_A.getValue();
                    totLineAmt = totLineAmt.add(itemLineMsg.schdAgmtLineDealFrtAmt_A.getValue());
                    totLineAmt = totLineAmt.add(itemLineMsg.schdAgmtLineDealTaxAmt_A.getValue());
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotAmt_A, totLineAmt);

                    itemLineMsgMap.put(itemLineMsgKey.toString(), itemLineMsg);
                }
            }
        }

        // Set Header Amount
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSubTotCalcPrcAmt, totNetPrcAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotChrgPrcAmt, totFrtChrgAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt, totTaxAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt, totAmt);
    }

    /**
     * Set Calc Base Data
     * @param bizMsg NWAL1840CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @param itemLineMsgMap Item Line Map
     */
    private static void setCalBaseData(NWAL1840CMsg bizMsg, NWZC157001PMsg prcApiPMsg, Map<String, NWAL1840_ACMsg> itemLineMsgMap) {

        int calcBaseValidCnt = bizMsg.E.getValidCount();
        int i = 0;

        for (i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg prcLinePMsg = prcApiPMsg.NWZC157002PMsg.no(i);
            if (!CONFIG_CATG.OUTBOUND.equals(prcLinePMsg.configCatgCd.getValue())) {
                continue;
            }

            for (int j = 0; j < prcLinePMsg.NWZC157003PMsg.getValidCount(); j++) {
                NWZC157003PMsg prcElementPMsg = prcLinePMsg.NWZC157003PMsg.no(j);
                String configCatgCd = prcElementPMsg.configCatgCd.getValue();
                if (!CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
                    continue;
                }

                NWAL1840_ECMsg calcBaseCMsg = bizMsg.E.no(calcBaseValidCnt);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.schdAgmtPrcCalcBasePk_E, prcElementPMsg.ordPrcCalcBasePk);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.schdAgmtLineNum_E, new BigDecimal(prcElementPMsg.trxLineNum.getValue()));
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.prcCondTpCd_E, prcElementPMsg.prcCondTpCd);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.prcDtlGrpCd_E, prcElementPMsg.prcDtlGrpCd);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.prcJrnlGrpCd_E, prcElementPMsg.prcJrnlGrpCd);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.pkgUomCd_E, prcElementPMsg.pkgUomCd);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.prcCondUnitCd_E, prcElementPMsg.prcCondUnitCd);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.prcCalcMethCd_E, prcElementPMsg.prcCalcMethCd);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.dsMdsePrcPk_E, prcElementPMsg.dsMdsePrcPk);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.specCondPrcPk_E, prcElementPMsg.specCondPrcPk);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.frtPerWtPk_E, prcElementPMsg.frtPerWtPk);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.prcCondManEntryFlg_E, prcElementPMsg.prcCondManEntryFlg);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.prcCondManAddFlg_E, prcElementPMsg.prcCondManAddFlg);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.prcCondManDelFlg_E, prcElementPMsg.prcCondManDelFlg);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.autoPrcAmtRate_E, prcElementPMsg.autoPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.maxPrcAmtRate_E, prcElementPMsg.maxPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.minPrcAmtRate_E, prcElementPMsg.minPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.manPrcAmtRate_E, prcElementPMsg.manPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.calcPrcAmtRate_E, prcElementPMsg.calcPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.unitPrcAmt_E, prcElementPMsg.unitPrcAmt);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.autoPrcCcyCd_E, prcElementPMsg.autoPrcCcyCd);
                // 2018/09/14 QC#9700 add start
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.prcRuleApplyFlg_E, prcElementPMsg.prcRuleApplyFlg);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.prcRulePrcdPk_E, prcElementPMsg.prcRulePrcdPk);
                // 2018/09/14 QC#9700 add end

                if (PRC_DTL_GRP.BASE_PRICE.equals(prcElementPMsg.prcDtlGrpCd.getValue())) {
                    StringBuffer itemLineMsgKey = new StringBuffer("");
                    itemLineMsgKey = itemLineMsgKey.append(prcElementPMsg.trxLineNum.getValue());
                    itemLineMsgKey = itemLineMsgKey.append(".");
                    itemLineMsgKey = itemLineMsgKey.append(prcElementPMsg.trxLineSubNum.getValue());
                    NWAL1840_ACMsg itemLineMsg = itemLineMsgMap.get(itemLineMsgKey.toString());
                    if (null != itemLineMsg) {
                        ZYPEZDItemValueSetter.setValue(itemLineMsg.dealPrcListPrcAmt_A, prcElementPMsg.autoPrcAmtRate);
                    }
                }
                calcBaseValidCnt++;
            }
        }
        bizMsg.E.setValidCount(calcBaseValidCnt);
    }

    /**
     * Get NWAL1840_ACMsg
     * @param bizMsg NWAL1840CMsg
     * @param schdAgmtDtlLineNum Schedule Agreement Detail Line Number
     * @return NWAL1840_ACMsg
     */
    private static NWAL1840_ACMsg getItemLineMsg(NWAL1840CMsg bizMsg, String schdAgmtDtlLineNum) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1840_ACMsg itemLineMsg = bizMsg.A.no(i);
            if (schdAgmtDtlLineNum.equals(itemLineMsg.xxLineNum_A.getValue())) {
                return itemLineMsg;
            }
        }

        return null;
    }
    // QC#24860 2018/04/05 Add Start
    private static String getDsLineCatgCd(NWAL1840CMsg bizMsg){
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd);
        ssmParam.put("dsOrdTpCd", bizMsg.dsOrdTpCd);
        ssmParam.put("dsOrdLineDrctnCd", DS_ORD_LINE_DRCTN.OUTBOUND);
        ssmParam.put("slsDt", bizMsg.slsDt);

        String result = NWAL1840QueryForPricing.getInstance().getDsLineCatgCd(ssmParam);

        return result;
    }
    // QC#24860 2018/04/05 Add End
}
