/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1420.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1410.NPAL1410Query;
import business.blap.NPAL1420.NPAL1420CMsg;
import business.blap.NPAL1420.NPAL1420Query;
import business.blap.NPAL1420.NPAL1420SMsg;
import business.db.NLGI5100_01TMsg;
import business.db.RMNF_ORDTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMNF_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
/**
 * <pre>
 * Business ID : NPAL1420 Reman Task Entry
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 5/17/2016   CITS       Yasushi Nomura       Create          N/A
 * 1/29/2018   CITS       T.Wada               Update          QC#23072
 * 06/14/2018  CITS       T.Tokutomi           Update          QC#26640
 *</pre>
 */
public class NPAL1420CommonLogic {
    /**
     * <pre>
     * copyFromCmsgOntoSmsg
     * Copy data From CMsg Onto SMsg
     * </pre>
     * @param sMsg NPAL1420SMsg
     * @param cMsg NPAL1420CMsg
     */
    public static void copyFromCMsgOntoSmsg(NPAL1420CMsg cMsg, NPAL1420SMsg sMsg) {
        // task
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfTaskDescTxt, cMsg.rmnfTaskDescTxt);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfTaskStartDt, cMsg.rmnfTaskStartDt);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfTaskEndDt, cMsg.rmnfTaskEndDt);
        ZYPEZDItemValueSetter.setValue(sMsg.spclInstnCmntTxt, cMsg.spclInstnCmntTxt);
        // Labor
        ZYPEZDItemValueSetter.setValue(sMsg.techTocCd, cMsg.techTocCd);
        ZYPEZDItemValueSetter.setValue(sMsg.techNm_L, cMsg.techNm_L);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfLborAot, cMsg.rmnfLborAot);
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfLborCmntTxt, cMsg.rmnfLborCmntTxt);
        // Parts Usage
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(i), null);
        }
        sMsg.A.setValidCount(cMsg.A.getValidCount());
    }

    /**
     * <pre>
     * copyFromSmsgOntoCmsg
     * Copy data From Smsg Onto Cmsg
     * </pre>
     * @param cMsg NPAL1420CMsg
     * @param sMsg NPAL1420SMsg
     */
    public static void copyFromSMsgOntoCmsg(NPAL1420CMsg cMsg, NPAL1420SMsg sMsg) {
        // Header info
        ZYPEZDItemValueSetter.setValue(cMsg.xxMsgTxt_WH, sMsg.xxMsgTxt_WH);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfMainUnitSerNum, sMsg.rmnfMainUnitSerNum);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdNum, sMsg.rmnfOrdNum);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdStsDescTxt, sMsg.rmnfOrdStsDescTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.techNm_H, sMsg.techNm_H);
        // task
        ZYPEZDItemValueSetter.setValue(cMsg.xxMsgTxt_TS, sMsg.xxMsgTxt_TS);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfTaskNum, sMsg.rmnfTaskNum);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfTaskDescTxt, sMsg.rmnfTaskDescTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfTaskStartDt, sMsg.rmnfTaskStartDt);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfTaskEndDt, sMsg.rmnfTaskEndDt);
        ZYPEZDItemValueSetter.setValue(cMsg.spclInstnCmntTxt, sMsg.spclInstnCmntTxt);
        // Labor
        ZYPEZDItemValueSetter.setValue(cMsg.techTocCd, sMsg.techTocCd);
        ZYPEZDItemValueSetter.setValue(cMsg.techNm_L, sMsg.techNm_L);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfLborAot, sMsg.rmnfLborAot);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfLborCmntTxt, sMsg.rmnfLborCmntTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfCostPerHourAmt, sMsg.rmnfCostPerHourAmt);
        ZYPEZDItemValueSetter.setValue(cMsg.stdCcyCd_PH, sMsg.stdCcyCd_PH);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfLborCostAmt, sMsg.rmnfLborCostAmt);
        ZYPEZDItemValueSetter.setValue(cMsg.stdCcyCd_LC, sMsg.stdCcyCd_LC);
        // Parts Usage
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd, sMsg.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd, sMsg.rtlSwhCd);

        ZYPEZDItemValueSetter.setValue(cMsg.prtUnitCostAmt, sMsg.prtUnitCostAmt);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(sMsg.A.getValidCount());
    }
    /**
     * getPartsUsage
     * @param rmnfOrdNum
     * @param rtlWhCd
     * @param glblCmpyCd
     * @param rmnfTaskNum
     * @return
     */
    public static S21SsmEZDResult getPartsUsage(String rmnfOrdNum, String rtlWhCd, String glblCmpyCd, String rmnfTaskNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("rmnfTaskNum", rmnfTaskNum);
        ssmParam.put("rtlWhCd", rtlWhCd);

        return NPAL1420Query.getInstance().getPartsUsage(ssmParam);
    }

    /**
     * chkApiRslt
     * @param cMsg
     * @param msgIdList
     * @return
     */
    public static boolean chkApiRslt(NPAL1420CMsg cMsg, List<String> msgIdList) {

        if (!msgIdList.isEmpty()) {

            for (String msgId : msgIdList) {

                if (ZYPCommonFunc.hasValue(msgId)) {

                    cMsg.setMessageInfo(msgId);

                    if (msgId.endsWith("E")) {

                        return false;
                    }
                }
            }
        }

        return true;
    }
    /**
     * getShippingPlanForPartsUsage
     * @param trxHdrNum
     * @param trxLineNum
     * @param trxLineSubNum
     * @param glblCmpyCd
     * @return
     */
    public static List<String> getShippingPlanForPartsUsage(String trxHdrNum, String trxLineNum, String trxLineSubNum, String glblCmpyCd) {

        List<String> ret = new ArrayList<String>();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxSrcTpCd", TRX_SRC_TP.REMAN_PARTS_USAGE);
        ssmParam.put("trxHdrNum", trxHdrNum);
        ssmParam.put("trxLineNum", trxLineNum);
        ssmParam.put("trxLineSubNum", trxLineSubNum);

        S21SsmEZDResult result = NPAL1420Query.getInstance().getShippingPlanForSubmit(ssmParam);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMap = (List<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < resultMap.size(); i++) {

                ret.add((String) ((Map<String, Object>) resultMap.get(i)).get("SHPG_PLN_NUM"));
            }
        }

        return ret;
    }
    /**
     * getShippingOrderForSubmit
     * @param soNum
     * @param glblCmpyCd
     * @return
     */
    public static List<Map<String, Object>> getShippingOrderForSubmit(String soNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("soNum", soNum);

        S21SsmEZDResult result = NPAL1420Query.getInstance().getShippingOrderForSubmit(ssmParam);

        if (result.isCodeNormal()) {

            return (List<Map<String, Object>>) result.getResultObject();
        }

        return null;
    }
    /**
     * getUnusedParts
     * @param rmnfOrdNum
     * @param glblCmpyCd
     * @param rmnfTaskNum
     * @param rtlWhCd
     * @return
     */
    public static List<Map<String, Object>> getUnusedParts(String rmnfOrdNum, String glblCmpyCd, String rmnfTaskNum, String rtlWhCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("rmnfTaskNum", rmnfTaskNum);
        ssmParam.put("rtlWhCd", rtlWhCd);
        S21SsmEZDResult result = NPAL1420Query.getInstance().getUnusedParts(ssmParam);

        if (result.isCodeNormal()) {

            return (List<Map<String, Object>>) result.getResultObject();
        }

        return null;
    }
    /**
     * getShippingPlan
     * @param trxSrcTpCd
     * @param trxHdrNum
     * @param glblCmpyCd
     * @return
     */
    public static Map<String, String> getShippingPlan(String trxSrcTpCd, String trxHdrNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxSrcTpCd", trxSrcTpCd);
        ssmParam.put("trxHdrNum", trxHdrNum);
        ssmParam.put("shpgStsCd", SHPG_STS.CANCELLED);

        S21SsmEZDResult result = NPAL1420Query.getInstance().getShippingPlan(ssmParam);

        Map<String, String> ret = new HashMap<String, String>();

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) result.getResultObject();

            for (Map<String, Object> resultMap : resultMapList) {

                ret.put((String) resultMap.get("TRX_LINE_NUM"), (String) resultMap.get("SHPG_PLN_NUM"));
            }
        }

        return ret;
    }
    /**
     * getRmnfPrtUsgCostAmt
     * @param glblCmpyCd
     * @param rmnfOrdNum
     * @param lineNum
     * @return
     */
    public static BigDecimal getRmnfPrtUsgCostAmt(String glblCmpyCd, String rmnfOrdNum, String lineNum) {

        BigDecimal rmnfPrtUsgCostAmt = BigDecimal.ZERO;

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("rmnfTaskNum", lineNum);

        S21SsmEZDResult resultMach = NPAL1420Query.getInstance().getRmnfPrtUsgCostAmt(ssmParam);

        if (resultMach.isCodeNormal()) {

            rmnfPrtUsgCostAmt = (BigDecimal) resultMach.getResultObject();
        }

        return rmnfPrtUsgCostAmt;

    }
    /**
     * getRmnTotLborCostAmt
     * @param glblCmpyCd
     * @param rmnfOrdNum
     * @param lineNum
     * @return
     */
    public static BigDecimal getRmnTotLborCostAmt(String glblCmpyCd, String rmnfOrdNum, String lineNum) {

        BigDecimal rmnfLborCostAmt = BigDecimal.ZERO;

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("rmnfTaskNum", lineNum);
        // QC#15221
        ssmParam.put("cancel", RMNF_TASK_STS.CANCEL);

        S21SsmEZDResult resultMach = NPAL1420Query.getInstance().getRmnTotLborCostAmt(ssmParam);

        if (resultMach.isCodeNormal()) {

            rmnfLborCostAmt = (BigDecimal) resultMach.getResultObject();
        }

        return rmnfLborCostAmt;

    }
    /**
     * getRmnfToCmptMdseCd
     * @param tMsg
     * @return
     */
    public static String getRmnfToCmptMdseCd(RMNF_ORDTMsg tMsg) {

        String rmnfToCmptMdseCd = "";

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", tMsg.glblCmpyCd.getValue());
        ssmParam.put("rmnfOrdNum", tMsg.rmnfOrdNum.getValue());

        S21SsmEZDResult resultMach = NPAL1420Query.getInstance().getRmnfToCmptMdseCd(ssmParam);

        if (resultMach.isCodeNormal()) {

            rmnfToCmptMdseCd = (String) resultMach.getResultObject();
        }

        return rmnfToCmptMdseCd;

    }
    /**
     * getRmnfTotCostAmt
     * @param tMsg
     * @return
     */
    public static BigDecimal getRmnfTotCostAmt(RMNF_ORDTMsg tMsg) {

        BigDecimal rmnfTotCostAmt = BigDecimal.ZERO;
        BigDecimal assetRecovCostAmt = BigDecimal.ZERO;

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", tMsg.glblCmpyCd.getValue());
        ssmParam.put("rmnfOrdNum", tMsg.rmnfOrdNum.getValue());

        S21SsmEZDResult resultMach = NPAL1410Query.getInstance().getAssetRecovCostAmt(ssmParam);

        if (resultMach.isCodeNormal()) {

            assetRecovCostAmt = (BigDecimal) resultMach.getResultObject();
        }

        rmnfTotCostAmt = tMsg.rmnfPrtUsgCostAmt.getValue().add(tMsg.rmnfTotLborCostAmt.getValue()).add(tMsg.rmnfMachCostAmt.getValue()).subtract(assetRecovCostAmt);

        return rmnfTotCostAmt;

    }
    /**
     * S21SsmEZDResult
     * @param glblCmpyCd
     * @param invtyLocCd
     * @return
     */
    public static S21SsmEZDResult getWmsAdjInfo(String glblCmpyCd, String invtyLocCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invtyLocCd", invtyLocCd);

        return NPAL1420Query.getInstance().getWmsAdjInfo(ssmParam);
    }
    /**
     * createAdjIf
     * @param cMsg
     * @param glblCmpyCd
     * @param rmnfOrdNum
     * @param mdseCd
     * @param qtyOrdTxt
     * @param prtInfo
     * @return
     */
    public static boolean createAdjIf(NPAL1420CMsg cMsg, String glblCmpyCd, String rmnfOrdNum, String mdseCd, BigDecimal qtyOrdTxt, Map<String, Object> prtInfo) {

        BigDecimal tranId = null;
        int seqNumber = 1;
        NLGI5100_01TMsg tMsg = new NLGI5100_01TMsg();
        S21TransactionTableAccessor trxAccess = new S21TransactionTableAccessor();

        List<Map<String, Object>> transList = new ArrayList<Map<String, Object>>();

        String curIfId = "";

        String interfaceId = (String) prtInfo.get("WMS_ADJ_DNLD_INTFC_ID");

        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            return false;
        }

        if (!ZYPCommonFunc.hasValue(tranId) || !interfaceId.equals(curIfId)) {
            tranId = new S21TransactionTableAccessor().getNextTransactionId();
            curIfId = interfaceId;
            Map<String, Object> tran = new HashMap<String, Object>();
            tran.put("TR", tranId);
            tran.put("IF", interfaceId);
            transList.add(tran);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, tranId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, new BigDecimal(seqNumber));

        // WMS_WH_CD
        ZYPEZDItemValueSetter.setValue(tMsg.wmsWhCd, (String) prtInfo.get("WMS_WH_CD"));
        // STAGE_ACT_CD
        ZYPEZDItemValueSetter.setValue(tMsg.stageActCd, "1");
        // STAGE_REC_STS_CD
        ZYPEZDItemValueSetter.setValue(tMsg.stageRecStsCd, "2");
        // ITEM_CD_TXT
        ZYPEZDItemValueSetter.setValue(tMsg.itemCdTxt, mdseCd);
        // PACK_CD_TXT
        ZYPEZDItemValueSetter.setValue(tMsg.packCdTxt, (String) prtInfo.get("PACK_CD_TXT"));
        // QC#26640 06/14/2018 if STK_STS is good, it is not needed to WMS.
        // HLD_CD_TXT
        // ZYPEZDItemValueSetter.setValue(tMsg.hldCdTxt, STK_STS.GOOD);
        // QTY_ORD_TXT
        // BigDecimal qtyOrdTxt = (BigDecimal) prtInfo.get("RMNF_PRT_QTY");

        if (ZYPCommonFunc.hasValue(qtyOrdTxt)) {
            // QC#15449
            String strQtyOrdTxt = qtyOrdTxt.multiply(new BigDecimal("-1")).toPlainString();
            ZYPEZDItemValueSetter.setValue(tMsg.qtyOrdTxt, strQtyOrdTxt);
        }
        // WMS_LOC_CD
        ZYPEZDItemValueSetter.setValue(tMsg.wmsLocCd, getLoctrCd(glblCmpyCd, rmnfOrdNum));
        // ADJ_SRC_ORD_NUM
        ZYPEZDItemValueSetter.setValue(tMsg.adjSrcOrdNum, rmnfOrdNum);
        // SER_NUM(Set Null)

        EZDTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo("NPAM1172E", new String[] {"NLGI5100_01" });
            return false;
        }
        seqNumber++;
        String ifId = null;
        BigDecimal trxId = null;
        for (Map<String, Object> trans : transList) {
            ifId = (String) trans.get("IF");
            trxId = (BigDecimal) trans.get("TR");
            trxAccess.createIntegrationRecordForBatch(ifId, trxId);
        }

        return true;
    }
    /**
     * getLoctrCd
     * @param glblCmpyCd
     * @param rmnfOrdNum
     * @return
     */
    public static String getLoctrCd(String glblCmpyCd, String rmnfOrdNum) {
	    RMNF_ORDTMsg oldMsg = new RMNF_ORDTMsg();
	    ZYPEZDItemValueSetter.setValue(oldMsg.glblCmpyCd, glblCmpyCd);
	    ZYPEZDItemValueSetter.setValue(oldMsg.rmnfOrdNum, rmnfOrdNum);
	    RMNF_ORDTMsg newMsg = (RMNF_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(oldMsg);
	    
	    return newMsg.whLoctrCd.getValue();
    	
    }
    /**
     * getUsgPartsForStsUpd
     * @param rmnfOrdNum
     * @param glblCmpyCd
     * @param rmnfTaskNum
     * @return
     */
    public static List<Map<String, Object>> getUsgPartsForStsUpd(String rmnfOrdNum, String glblCmpyCd, String rmnfTaskNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("rmnfTaskNum", rmnfTaskNum);
        S21SsmEZDResult result = NPAL1420Query.getInstance().getUsgPartsForStsUpd(ssmParam);

        if (result.isCodeNormal()) {

            return (List<Map<String, Object>>) result.getResultObject();
        }

        return null;
    }

}
