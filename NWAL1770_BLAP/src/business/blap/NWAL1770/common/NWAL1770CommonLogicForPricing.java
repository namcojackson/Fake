/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770.common;

import static business.blap.NWAL1770.constant.NWAL1770Constant.IDX_3;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.blap.NWAL1770.NWAL1770QueryForPricing;
import business.blap.NWAL1770.NWAL1770_BCMsg;
import business.blap.NWAL1770.NWAL1770_ECMsg;
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
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CALC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_STS;
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
 * 2016/01/25   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/16   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/06/21   Fujitsu         Y.Kanefusa      Update          S21_NA#9437
 * 2016/08/10   Fujitsu         Y.Kanefusa      Update          S21_NA#13173
 * 2016/09/30   Fujitsu         H.Ikeda         Update          S21_NA#14482
 * 2017/05/17   Fujitsu         M.Yamada        Update          S21_NA#18627
 * 2017/06/20   Fujitsu         N.Aoyama        Update          S21_NA#18984
 * 2017/08/07   Fujitsu         Y.Kanefusa      Update          S21_NA#10347
 * 2018/03/19   Fujitsu         A.Kosai         Update          S21_NA#24810
 * 2018/04/16   Fujitsu         N.Sugiura       Update          S21_NA#22965
 * 2018/09/18   Fujitsu         S.Kosaka        Update          QC#9700
 * 2024/04/03   CITS            A.Shimada       Update          CSA-QC#63691
 * </pre>
 */
public class NWAL1770CommonLogicForPricing {

    /**
     * Call NWZC1570 Pricing API (02:Get Base Price Mode)
     * @param bizMsg NWAL1770CMsg
     * @return NWZC157001PMsg
     */
    public static NWZC157001PMsg callPricingApiOfGetBasePriceMode(NWAL1770CMsg bizMsg) {

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        setHdrParam(bizMsg, prcApiPMsg, NWZC157001.GET_BASE_PRICE);
        setLineParamForGetBasePriceMode(bizMsg, prcApiPMsg);

        // call NWZC1570 Pricing API
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);

        return prcApiPMsg;
    }

    /**
     * Call NWZC1570 Pricing API
     * @param bizMsg NWAL1770CMsg
     * @param String mode(04:Get Order All Mode/ 06:Manual Mode)
     * @return NWZC157001PMsg
     */
    public static NWZC157001PMsg callPricingApi(NWAL1770CMsg bizMsg, String mode) {

        NWAL1770CommonLogic.numberingQuoteLineNumber(bizMsg);
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        setHdrParam(bizMsg, prcApiPMsg, mode); // S21_NA#22965 Mod
        setLineParamForGetOrderAllMode(bizMsg, prcApiPMsg);

        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
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
                // S21_NA#13173 Add START
                NWZC157002PMsg prcLinePMsg = prcApiPMsg.NWZC157002PMsg.no(i);
                NWAL1770_BCMsg itemLineMsg = getItemLineMsg(bizMsg, prcLinePMsg.trxLineNum.getValue(), prcLinePMsg.trxLineSubNum.getValue());
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxErrFlg_B, prcLinePMsg.xxErrFlg);
                itemLineMsg.dealSplyQuoteDtlSlsAmt_B.clear();
                // S21_NA#13173 Add END

                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg.NWZC157002PMsg.no(i));
                for (int j = 0; j < msgList.size(); j++) {
                    S21ApiMessage msg = msgList.get(j);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);
                    if (msgId.endsWith("E")) {
                        return null;
                    }
                }
            }

            for (int k = 0; k < prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.getValidCount(); k++) {
                NWZC157003PMsg prcElementPMsg = prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(k);
                if (S21ApiUtil.isXxMsgId(prcElementPMsg)) {
                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcElementPMsg);
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
        }

        return prcApiPMsg;
    }

    /**
     * Set Price API Header Parameter
     * @param bizMsg NWAL1770CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @param modeCd Mode Code
     */
    private static void setHdrParam(NWAL1770CMsg bizMsg, NWZC157001PMsg prcApiPMsg, String modeCd) {

        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, modeCd);
        // QC#9437 2016/06/21 Mod Start
        // ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.splyQuoteDt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.slsDt);
        // QC#9437 2016/06/21 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, bizMsg.splyQuoteCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcContrNum, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.custIssPoNum, bizMsg.custIssPoNum);
        // QC#14482 2016/09/30 Mod Start
        if (NWZC157001.GET_BASE_PRICE.equals(modeCd)) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
        }
        // QC#14482 2016/09/30 Mod End
        // 2017/06/20 QC#18984 Add Start
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsPmtMethCd, bizMsg.dsPmtMethCd);
        // 2017/06/20 QC#18984 Add End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.spclHdlgTpCd, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);
    }

    /**
     * Set Price API Line Parameter For Get Base Price Mode
     * @param bizMsg NWAL1770CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @param modeCd Mode Code
     */
    private static void setLineParamForGetBasePriceMode(NWAL1770CMsg bizMsg, NWZC157001PMsg prcApiPMsg) {

        NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(0);
        NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(bizMsg.xxCellIdx.getValueInt());
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineNum, itemLineMsg.splyQuoteDtlLineNum_B);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineSubNum, itemLineMsg.splyQuoteDtlLineSubNum_B);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
        // QC#10347 2017/07/24 Mod Start
        //ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcBaseDt, bizMsg.splyQuoteDt); // QC#9437 2016/06/21 Add
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcBaseDt, itemLineMsg.prcBaseDt_B);
        // QC#10347 2017/07/24 Mod End
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.billToCustCd, bizMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shipToCustCd, bizMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_SH, bizMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_BL, bizMsg.billToCustAcctCd);
        // Get Price Category Code
        if (ZYPCommonFunc.hasValue(itemLineMsg.prcCatgNm_B)) {
            String prcCatgCd = getPrcCatgCd(bizMsg, itemLineMsg.prcCatgNm_B.getValue());
            ZYPEZDItemValueSetter.setValue(itemLineMsg.prcCatgCd_B, prcCatgCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCatgCd, prcCatgCd);
        }
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcContrNum, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaBrCd, bizMsg.coaBrCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ccyCd, getCcyCd(bizMsg, itemLineMsg));
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdseCd, itemLineMsg.mdseCd_B);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.pkgUomCd, itemLineMsg.custUomCd_B);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsOrdLineCatgCd, itemLineMsg.dsOrdLineCatgCd_B);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordQty, itemLineMsg.ordQty_B);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordCustUomQty, itemLineMsg.ordCustUomQty_B);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.firstLineAddr_SH, bizMsg.shipToFirstLineAddr); // QC#18627
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.scdLineAddr_SH, bizMsg.shipToScdLineAddr);    // QC#18627
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctyAddr_SH, bizMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.cntyNm_SH, bizMsg.shipToCntyNm);    // QC#18627
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.stCd_SH, bizMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.postCd_SH, bizMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctryCd_SH, bizMsg.shipToCtryCd);
        //ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.slsRepOrSlsTeamTocCd, getWriterRepCd(bizMsg));
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.slsRepOrSlsTeamTocCd, bizMsg.slsRepTocCd); // QC#18627
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.rtlWhCd, itemLineMsg.rtlWhCd_B);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtCondCd, bizMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaExtnCd, bizMsg.coaExtnCd);
        // QC#6480 2016/06/16 Add Start
        ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxUnitPrc, itemLineMsg.dealSplyQuoteDtlSlsAmt_B);
        if (ZYPCommonFunc.hasValue(itemLineMsg.dealSplyQuoteDtlSlsAmt_B)) {
            if (itemLineMsg.dealSplyQuoteDtlSlsAmt_B.getValue().compareTo(itemLineMsg.dealPrcListPrcAmt_B.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
        }
        // QC#6480 2016/06/16 Add End
        itemLineMsg.xxErrFlg_B.clear();// S21_NA#13173 Add 

        prcApiPMsg.NWZC157002PMsg.setValidCount(1);
    }

    /**
     * Set Price API Line Parameter For Get Order All Mode
     * @param bizMsg NWAL1770CMsg
     * @param prcApiPMsg NWZC157001PMsg
     */
    private static void setLineParamForGetOrderAllMode(NWAL1770CMsg bizMsg, NWZC157001PMsg prcApiPMsg) {

        int prcLineValidCnt = prcApiPMsg.NWZC157002PMsg.getValidCount();
        // 2018/03/19 S21_NA#24810 Mod Start
//        String cancelStsTxt = ZYPCodeDataUtil.getVarCharConstValue(KEY_QUOTE_CANCELLED, bizMsg.glblCmpyCd.getValue());C157002PMsg.getValidCount();
        String cancelStsTxt = NWAL1770CommonLogic.getCancelStsTxt(bizMsg);
        // 2018/03/19 S21_NA#24810 Mod Emd

        for (int itemLineCnt = 0; itemLineCnt < bizMsg.B.getValidCount() && prcLineValidCnt < prcApiPMsg.NWZC157002PMsg.length(); itemLineCnt++) {
            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(itemLineCnt);

            // Skip Cancel Line
            if (cancelStsTxt.equals(itemLineMsg.splyQuoteStsDescTxt_B.getValue()) || SPLY_QUOTE_STS.CANCELLED.equals(itemLineMsg.splyQuoteLineStsCd_B.getValue())) {
                continue;
            }
          //START 2024/04/03 [CSA-QC#63691,ADD]
           //Skip Blank Line
            boolean isItemBlank = !ZYPCommonFunc.hasValue(itemLineMsg.mdseCd_B);
            boolean isQtyBlank = !ZYPCommonFunc.hasValue(itemLineMsg.ordCustUomQty_B);
            boolean isBlank = isItemBlank && isQtyBlank;
            if (isBlank) {
                continue;
            }
            //END 2024/04/03 [CSA-QC#63691,ADD]

            // Skip Set Component
            String[] xxLineNumArray = itemLineMsg.xxLineNum_B.getValue().split("\\.");
            if (xxLineNumArray.length == IDX_3 && BigDecimal.ZERO.compareTo(new BigDecimal(xxLineNumArray[2])) != 0) {
                continue;
            }

            NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(prcLineValidCnt);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineNum, itemLineMsg.splyQuoteDtlLineNum_B);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineSubNum, itemLineMsg.splyQuoteDtlLineSubNum_B);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
            // QC#10347 2017/07/24 Add Start
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcBaseDt, itemLineMsg.prcBaseDt_B);
            // QC#10347 2017/07/24 Add End
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.billToCustCd, bizMsg.billToCustCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shipToCustCd, bizMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_SH, bizMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_BL, bizMsg.billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCatgCd, itemLineMsg.prcCatgCd_B);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcContrNum, bizMsg.prcContrNum);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaBrCd, bizMsg.coaBrCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ccyCd, getCcyCd(bizMsg, itemLineMsg));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdseCd, itemLineMsg.mdseCd_B);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.pkgUomCd, itemLineMsg.custUomCd_B);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsOrdLineCatgCd, itemLineMsg.dsOrdLineCatgCd_B);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordQty, itemLineMsg.ordQty_B);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordCustUomQty, itemLineMsg.ordCustUomQty_B);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.firstLineAddr_SH, bizMsg.shipToFirstLineAddr); // QC#18627
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.scdLineAddr_SH, bizMsg.shipToScdLineAddr);    // QC#18627
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctyAddr_SH, bizMsg.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.cntyNm_SH, bizMsg.shipToCntyNm);    // QC#18627
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.stCd_SH, bizMsg.shipToStCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.postCd_SH, bizMsg.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctryCd_SH, bizMsg.shipToCtryCd);
            //ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.slsRepOrSlsTeamTocCd, getWriterRepCd(bizMsg));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.slsRepOrSlsTeamTocCd, bizMsg.slsRepTocCd); // QC#18627
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.rtlWhCd, itemLineMsg.rtlWhCd_B);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtCondCd, bizMsg.frtCondCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaExtnCd, bizMsg.coaExtnCd);
            // QC#6480 2016/06/16 Add Start
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxUnitPrc, itemLineMsg.dealSplyQuoteDtlSlsAmt_B);
            if (itemLineMsg.dealSplyQuoteDtlSlsAmt_B.getValue().compareTo(itemLineMsg.dealPrcListPrcAmt_B.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
            }
            // QC#6480 2016/06/16 Add End
            itemLineMsg.xxErrFlg_B.clear(); // S21_NA#13173 Add

            // Pricing Element
            String xxLineNum = itemLineMsg.xxLineNum_B.getValue();
            String splyQuoteDtlLineNum = itemLineMsg.splyQuoteDtlLineNum_B.getValue();
            String splyQuoteDtlLineSubNum = itemLineMsg.splyQuoteDtlLineSubNum_B.getValue();
            int prcElmntCnt = 0;

            for (int j = 0; j < bizMsg.E.getValidCount(); j++) {
                NWAL1770_ECMsg prcCalcMsg = bizMsg.E.no(j);

                if ((xxLineNum.equals(prcCalcMsg.xxLineNum_E.getValue()) || (splyQuoteDtlLineNum.equals(prcCalcMsg.splyQuoteDtlLineNum_E.getValue()) && splyQuoteDtlLineSubNum.equals(prcCalcMsg.splyQuoteDtlLineSubNum_E.getValue())))) {
                    NWZC157003PMsg prcElmntApiPMsg = linePrcApiPMsg.NWZC157003PMsg.no(prcElmntCnt);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.trxLineNum, prcCalcMsg.splyQuoteDtlLineNum_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.trxLineSubNum, prcCalcMsg.splyQuoteDtlLineSubNum_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcCondTpCd, prcCalcMsg.prcCondTpCd_E);

                    Map<String, String> prcCondTpMap = new HashMap<String, String>();
                    String prcCondTpDescTxt = null;

                    if (ZYPCommonFunc.hasValue(prcCalcMsg.prcCondTpCd_E)) {
                        prcCondTpDescTxt = prcCondTpMap.get(prcCalcMsg.prcCondTpCd_E.getValue());
                        if (!ZYPCommonFunc.hasValue(prcCondTpDescTxt)) {
                            prcCondTpDescTxt = getPrcCondTpDescTxt(bizMsg, prcCalcMsg.prcCondTpCd_E.getValue());
                            if (null != prcCondTpDescTxt) {
                                prcCondTpMap.put(prcCalcMsg.prcCondTpCd_E.getValue(), prcCondTpDescTxt);
                            }
                        }
                    }

                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcCondTpDescTxt, prcCondTpDescTxt);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcDtlGrpCd, prcCalcMsg.prcDtlGrpCd_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcJrnlGrpCd, prcCalcMsg.prcJrnlGrpCd_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcCatgCd, prcCalcMsg.prcCatgCd_E);
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
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.ordPrcCalcBasePk, prcCalcMsg.ordPrcCalcBasePk_E);
                    // QC#9700  2018/09/18 Add Start
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcRuleApplyFlg, prcCalcMsg.prcRuleApplyFlg_E);
                    ZYPEZDItemValueSetter.setValue(prcElmntApiPMsg.prcRulePrcdPk, prcCalcMsg.prcRulePrcdPk_E);
                    // QC#9700  2018/09/18 Add End
                    prcElmntCnt++;
                }
            }
            linePrcApiPMsg.NWZC157003PMsg.setValidCount(prcElmntCnt);
            prcLineValidCnt++;
        }
        prcApiPMsg.NWZC157002PMsg.setValidCount(prcLineValidCnt);
    }

    /**
     * Get Price Category Code
     * @param bizMsg NWAL1770CMsg
     * @param prcCatgNm Price Category Name
     * @return Price Category Code
     */
    private static String getPrcCatgCd(NWAL1770CMsg bizMsg, String prcCatgNm) {

        S21SsmEZDResult ssmRslt = NWAL1770QueryForPricing.getInstance().getPrcCatgCd(bizMsg, prcCatgNm);
        if (ssmRslt.isCodeNormal()) {
            return (String) ssmRslt.getResultObject();
        }

        return null;
    }

    /**
     * Get CCY Code
     * @param bizMsg NWAL1770CMsg
     * @param itemLineMsg NWAL1770_BCMsg
     * @return CCY Code
     */
    private static String getCcyCd(NWAL1770CMsg bizMsg, NWAL1770_BCMsg itemLineMsg) {

        if (ZYPCommonFunc.hasValue(itemLineMsg.prcCatgNm_B)) {
            PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(prcCatgTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgCd, itemLineMsg.prcCatgCd_B);
            prcCatgTMsg = (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prcCatgTMsg);

            if (null != prcCatgTMsg) {
                return prcCatgTMsg.ccyCd.getValue();
            }
        }

        return null;
    }

// QC#18627
//    /**
//     * Get Writer Rep Code
//     * @param bizMsg NWAL1770CMsg
//     * @return Writer Rep Code
//     */
//    private static String getWriterRepCd(NWAL1770CMsg bizMsg) {
//
//        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
//            String roleTpCd = bizMsg.D.no(i).slsRepRoleTpCd_D.getValue();
//            if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(roleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(roleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(roleTpCd)) {
//                return roleTpCd;
//            }
//        }
//
//        return null;
//    }

    /**
     * Get Price Condition Type Desc Text
     * @param bizMsg NWAL1770CMsg
     * @param prcCondTpCd Price Condition Type Code
     * @return Price Condition Type Desc Text
     */
    private static String getPrcCondTpDescTxt(NWAL1770CMsg bizMsg, String prcCondTpCd) {

        PRC_COND_TPTMsg prcCondTpTMsg = getPrcCondTp(bizMsg, prcCondTpCd);
        if (null != prcCondTpTMsg) {
            return prcCondTpTMsg.prcCondTpDescTxt.getValue();
        }

        return null;
    }

    /**
     * Get Price Condition Type
     * @param bizMsg NWAL1770CMsg
     * @param prcCondTpCd Price Condition Type Code
     * @return Price Condition Type
     */
    private static PRC_COND_TPTMsg getPrcCondTp(NWAL1770CMsg bizMsg, String prcCondTpCd) {

        PRC_COND_TPTMsg prcCondTpTMsg = new PRC_COND_TPTMsg();
        ZYPEZDItemValueSetter.setValue(prcCondTpTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(prcCondTpTMsg.prcCondTpCd, prcCondTpCd);
        return (PRC_COND_TPTMsg) S21CodeTableAccessor.findByKey(prcCondTpTMsg);
    }

    /**
     * Get BASE Price Element From Business Message
     * @param bizMsg NWAL1770CMsg
     * @param itemLineMsg NWAL1770_BCMsg
     * @return BASE Price Element
     */
    public static NWAL1770_ECMsg getBasePrcData(NWAL1770CMsg bizMsg, NWAL1770_BCMsg itemLineMsg) {

        String xxLineNum = itemLineMsg.xxLineNum_B.getValue();
        String splyQuoteDtlLineNum = itemLineMsg.splyQuoteDtlLineNum_B.getValue();
        String splyQuoteDtlLineSubNum = itemLineMsg.splyQuoteDtlLineSubNum_B.getValue();

        for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
            NWAL1770_ECMsg prcCalcLine = bizMsg.E.no(i);
            if ((xxLineNum.equals(prcCalcLine.xxLineNum_E.getValue()) || (splyQuoteDtlLineNum.equals(prcCalcLine.splyQuoteDtlLineNum_E.getValue()) && splyQuoteDtlLineSubNum.equals(prcCalcLine.splyQuoteDtlLineSubNum_E.getValue())))
                    && PRC_DTL_GRP.BASE_PRICE.equals(prcCalcLine.prcDtlGrpCd_E.getValue())) {
                return prcCalcLine;
            }
        }

        // Set Manual Data
        NWAL1770_ECMsg prcCalcLine = bizMsg.E.no(bizMsg.E.getValidCount());
        if (ZYPCommonFunc.hasValue(itemLineMsg.dealSplyQuoteDtlSlsAmt_B)) {
            ZYPEZDItemValueSetter.setValue(prcCalcLine.manPrcAmtRate_E, itemLineMsg.dealSplyQuoteDtlSlsAmt_B);
        } else {
            ZYPEZDItemValueSetter.setValue(prcCalcLine.manPrcAmtRate_E, BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(prcCalcLine.prcCondTpCd_E, PRC_COND_TP.BASE_PRICE);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.prcCondManEntryFlg_E, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.prcCondManAddFlg_E, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.prcCondManDelFlg_E, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.prcDtlGrpCd_E, PRC_DTL_GRP.BASE_PRICE);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.prcCondUnitCd_E, PRC_COND_UNIT.AMT);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.prcCalcMethCd_E, PRC_CALC_METH.EACH_ACCOUNT);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.autoPrcCcyCd_E, CCY.US_DOLLAR);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.autoPrcAmtRate_E, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.calcPrcAmtRate_E, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.unitPrcAmt_E, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.splyQuoteDtlLineNum_E, splyQuoteDtlLineNum);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.splyQuoteDtlLineSubNum_E, splyQuoteDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.pkgUomCd_E, itemLineMsg.custUomCd_B);
        ZYPEZDItemValueSetter.setValue(prcCalcLine.specCondPrcPk_E, getSpecCondPrcPk(bizMsg));
        // QC#9700  2018/09/18 Add Start
        ZYPEZDItemValueSetter.setValue(prcCalcLine.prcRuleApplyFlg_E, ZYPConstant.FLG_ON_Y);
        // QC#9700  2018/09/18 Add End
        bizMsg.E.setValidCount(bizMsg.E.getValidCount() + 1);

        return prcCalcLine;
    }

    /**
     * Get Spec Condition Price PK
     * @param bizMsg NWAL1770CMsg
     * @return Spec Condition Price PK
     */
    private static BigDecimal getSpecCondPrcPk(NWAL1770CMsg bizMsg) {

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
     * @param bizMsg NWAL1770CMsg
     * @param prcApiPMsg NWZC157001PMsg
     */
    public static void setPriceElement(NWAL1770CMsg bizMsg, NWZC157001PMsg prcApiPMsg) {

        ZYPTableUtil.clear(bizMsg.E);

        Map<String, NWAL1770_BCMsg> itemLineMsgMap = new HashMap<String, NWAL1770_BCMsg>();
        setAmt(bizMsg, prcApiPMsg, itemLineMsgMap);
        setCalBaseData(bizMsg, prcApiPMsg, itemLineMsgMap);
    }

    /**
     * Set Amount
     * @param bizMsg NWAL1770CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @param itemLineMsgMap Item Line Map
     */
    private static void setAmt(NWAL1770CMsg bizMsg, NWZC157001PMsg prcApiPMsg, Map<String, NWAL1770_BCMsg> itemLineMsgMap) {

        BigDecimal totNetPrcAmt = BigDecimal.ZERO;
        BigDecimal totTaxAmt = BigDecimal.ZERO;
        BigDecimal totFrtChrgAmt = BigDecimal.ZERO;
        BigDecimal totAmt = BigDecimal.ZERO;
        BigDecimal baseAutoPrcAmtRate = BigDecimal.ZERO; // QC#6480 2016/06/16 Add
        // BigDecimal discUnitPrcAmt = BigDecimal.ZERO; // QC#6480 2016/06/16 Add
        StringBuilder itemLineMsgKey = new StringBuilder(); // QC#13106 2016/08/05 Mod

        for (int i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
            NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(i);
            NWZC157002PMsg linePMsg = prcApiPMsg.NWZC157002PMsg.no(i);

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
                NWAL1770_BCMsg itemLineMsg = getItemLineMsg(bizMsg, trxLineNum, trxLineSubNum);
                if (itemLineMsg != null) {
                    // QC#6480 2016/06/16 Mod Start
                    // BigDecimal ordQty = itemLineMsg.ordQty_B.getValue();
                    // BigDecimal discAmt = prcTotalPMsg.xxTotDiscAmt.getValue();
                    // BigDecimal unitDiscAmt = discAmt.divide(ordQty, 2, BigDecimal.ROUND_HALF_UP);
                    baseAutoPrcAmtRate = BigDecimal.ZERO;
                    // discUnitPrcAmt = BigDecimal.ZERO;
                    for (int j = 0; j < linePMsg.NWZC157003PMsg.getValidCount(); j++) {
                        if (PRC_DTL_GRP.BASE_PRICE.equals(linePMsg.NWZC157003PMsg.no(j).prcDtlGrpCd.getValue())) {
                            baseAutoPrcAmtRate = linePMsg.NWZC157003PMsg.no(j).autoPrcAmtRate.getValue();
                        }
                        // Comment out because it is unused logic.
                        // if (!ZYPCommonFunc.hasValue(linePMsg.NWZC157003PMsg.no(j).prcCondTpCd.getValue()) && PRC_DTL_GRP.DISCOUNT.equals(linePMsg.NWZC157003PMsg.no(j).prcDtlGrpCd.getValue())) {
                        //     discUnitPrcAmt = discUnitPrcAmt.add(linePMsg.NWZC157003PMsg.no(j).autoPrcAmtRate.getValue());
                        // }
                    }
                    // QC#6480 2016/06/16 Mod End
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.dealSplyQuoteDtlSlsAmt_B, prcTotalPMsg.xxUnitGrsPrcAmt);
                    // QC#6480 2016/06/16 Mod Start
                    // ZYPEZDItemValueSetter.setValue(itemLineMsg.dealPrcListPrcAmt_B,
                    // prcTotalPMsg.xxUnitNetPrcAmt);
                    // ZYPEZDItemValueSetter.setValue(itemLineMsg.xxDtlDiscAmt_B,
                    // itemLineMsg.dealSplyQuoteDtlSlsAmt_B.getValue().subtract(unitDiscAmt));
                    // ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotDiscAmt_B, unitDiscAmt);
                    // // Set Sub Total
                    // BigDecimal subTotCalcPrcAmt =
                    // ordQty.multiply(itemLineMsg.dealSplyQuoteDtlSlsAmt_B.getValue());
                    // ZYPEZDItemValueSetter.setValue(itemLineMsg.xxSubTotCalcPrcAmt_B,
                    // subTotCalcPrcAmt.subtract(discAmt));
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.dealPrcListPrcAmt_B, baseAutoPrcAmtRate);
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.dealSplyQuoteDtlNetAmt_B, prcTotalPMsg.xxUnitNetPrcAmt);
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.xxDtlDiscAmt_B, prcTotalPMsg.xxUnitNetPrcAmt);
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotDiscAmt_B, prcTotalPMsg.xxUnitGrsPrcAmt.getValue().subtract(prcTotalPMsg.xxUnitNetPrcAmt.getValue()));

                    // Set Sub Total
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.xxSubTotCalcPrcAmt_B, prcTotalPMsg.xxTotNetPrcAmt);
                    // QC#6480 2016/06/16 Mod End
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotFrtAmt_B, prcTotalPMsg.xxTotFrtAmt);
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotTaxAmt_B, prcTotalPMsg.xxTotTaxAmt);
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotAmt_B, prcTotalPMsg.xxTotAmt);

                    // Set Line Total
                    BigDecimal totLineAmt = itemLineMsg.xxSubTotCalcPrcAmt_B.getValue();
                    totLineAmt = totLineAmt.add(itemLineMsg.xxTotFrtAmt_B.getValue());
                    totLineAmt = totLineAmt.add(itemLineMsg.xxTotTaxAmt_B.getValue());
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotAmt_B, totLineAmt);

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
     * @param bizMsg NWAL1770CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @param itemLineMsgMap Item Line Map
     */
    private static void setCalBaseData(NWAL1770CMsg bizMsg, NWZC157001PMsg prcApiPMsg, Map<String, NWAL1770_BCMsg> itemLineMsgMap) {

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

                NWAL1770_ECMsg calcBaseCMsg = bizMsg.E.no(calcBaseValidCnt);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.ordPrcCalcBasePk_E, prcElementPMsg.ordPrcCalcBasePk);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.splyQuoteNum_E, bizMsg.splyQuoteNum);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.splyQuoteDtlLineNum_E, prcElementPMsg.trxLineNum);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.splyQuoteDtlLineSubNum_E, prcElementPMsg.trxLineSubNum);
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
                // QC#9700  2018/09/18 Add Start
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.prcRuleApplyFlg_E, prcElementPMsg.prcRuleApplyFlg);
                ZYPEZDItemValueSetter.setValue(calcBaseCMsg.prcRulePrcdPk_E, prcElementPMsg.prcRulePrcdPk);
                // QC#9700  2018/09/18 Add End

                if (PRC_DTL_GRP.BASE_PRICE.equals(prcElementPMsg.prcDtlGrpCd.getValue())) {
                    StringBuilder itemLineMsgKey = new StringBuilder(); // QC#13106 2016/08/05 Mod
                    itemLineMsgKey = itemLineMsgKey.append(prcElementPMsg.trxLineNum.getValue());
                    itemLineMsgKey = itemLineMsgKey.append(".");
                    itemLineMsgKey = itemLineMsgKey.append(prcElementPMsg.trxLineSubNum.getValue());
                    NWAL1770_BCMsg itemLineMsg = itemLineMsgMap.get(itemLineMsgKey.toString());
                    if (null != itemLineMsg) {
                        ZYPEZDItemValueSetter.setValue(itemLineMsg.dealPrcListPrcAmt_B, prcElementPMsg.autoPrcAmtRate);
                    }
                }
                calcBaseValidCnt++;
            }
        }
        bizMsg.E.setValidCount(calcBaseValidCnt);
    }

    /**
     * Get NWAL1770_BCMsg
     * @param bizMsg NWAL1770CMsg
     * @param splyQuoteDtlLineNum Supply Quote Detail Line Number
     * @param splyQuoteDtlLineSubNum Supply Quote Detail Line Sub Number
     * @return NWAL1770_BCMsg
     */
    private static NWAL1770_BCMsg getItemLineMsg(NWAL1770CMsg bizMsg, String splyQuoteDtlLineNum, String splyQuoteDtlLineSubNum) {

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(i);
            if (splyQuoteDtlLineNum.equals(itemLineMsg.splyQuoteDtlLineNum_B.getValue()) && splyQuoteDtlLineSubNum.equals(itemLineMsg.splyQuoteDtlLineSubNum_B.getValue())) {
                return itemLineMsg;
            }
        }

        return null;
    }
}
