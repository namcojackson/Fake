/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3070;

import static business.blap.NFCL3070.constant.NFCL3070Constant.CI_OBJECT_TP_INV;
import static business.blap.NFCL3070.constant.NFCL3070Constant.CI_TICKET_STS_CLOSE;
import static business.blap.NFCL3070.constant.NFCL3070Constant.SET_PRNT_INV_LINE_SUB_NUM;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.DS_INVTY_LOC_VTMsg;
import business.db.DS_INV_LINE_TAX_DTLTMsg;
import business.db.INV_LINETMsg;
import business.db.MDSETMsg;
import business.db.MDSETMsgArray;
import business.db.S21_PSNTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SHPG_ORD_DTLTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_LINE_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CR_REBIL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NFCL3070Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/19   Fujitsu         S.Fujita        Update          N/A
 * 2016/08/01   Hitachi         Y.Takeno        Update          QC#9192
 * 2017/01/10   Fujitsu         T.Murai         Update          QC#16983
 * 2018/02/08   Hitachi         E.Kameishi      Update          QC#23551
 * 2018/05/30   Hitachi         E.Kameishi      Update          QC#26229
 * 2018/07/17   Hitachi         E.Kameishi      Update          QC#27007
 * 2018/08/24   Hitachi         E.Kameishi      Update          QC#27848
 * 2018/08/29   Fujitsu         S.Ohki          Update          QC#27887
 * 2019/03/25   Hitachi         E.Kameishi      Update          QC#30904
 * 2019/06/05   Hitachi         Y.Takeno        Update          QC#50730
 * 2019/09/17   Fujitsu         S.Takami        Update          QC#53456
 *</pre>
 */
public final class NFCL3070Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NFCL3070Query MY_INSTANCE = new NFCL3070Query();

    /**
     * Private constructor
     */
    private NFCL3070Query() {
        super();
    }

    /**
     * Get the NFCL3070Query instance.
     * @return NFCL3070Query instance
     */
    public static NFCL3070Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getTypeOfCredit
     * @param bizMsg NFCL3070CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTypeOfCredit(NFCL3070CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryObjectList("getTypeOfCredit", ssmParam);
    }

    /**
     * getPositionOfInvLine
     * @param bizMsg NFCL3070CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPositionOfInvLine(NFCL3070CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.origInvNum.getValue());
        ssmParam.put("invLineNum", bizMsg.invLineNum.getValue());
        ssmParam.put("invBolLineNum", bizMsg.invBolLineNum.getValue());
        // START 2018/08/29 S.Ohki [QC#27887, DEL]
//        ssmParam.put("invLineSubNum", bizMsg.invLineSubNum.getValue());
//        ssmParam.put("invLineSubTrxNum", bizMsg.invLineSubTrxNum.getValue());
        // END 2018/08/29 S.Ohki [QC#27887, DEL]

        return getSsmEZDClient().queryObjectList("getPositionOfInvLine", ssmParam);
    }

    /**
     * getBillToLocNm
     * @param bizMsg NFCL3070CMsg
     * @return String
     */
    public String getBillToLocNm(NFCL3070CMsg bizMsg) {

        BILL_TO_CUSTTMsg inTMsg = new BILL_TO_CUSTTMsg();
        inTMsg.setSQLID("003");
        inTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("billToCustCd01", bizMsg.billToCustCd.getValue());
        BILL_TO_CUSTTMsgArray outMsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (outMsgArray == null || outMsgArray.getValidCount() == 0) {
            return null;
        } else {
            return outMsgArray.no(0).dsLocNm.getValue();
        }
    }

    /**
     * checkApplyOfCreditMemo
     * @param bizMsg NFCL3070CMsg
     * @return Integer
     */
    public Integer countAppliedCredit(NFCL3070CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.origInvNum.getValue());
        ssmParam.put("creditMemo", AR_TRX_TP.CREDIT_MEMO);
        ssmParam.put("cash", AR_APPLY_TP.CASH);
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);
        ssmParam.put("offset", AR_RCPT_TP.OFFSET);
        ssmParam.put("applied", AR_CASH_APPLY_STS.APPLIED);
        ssmParam.put("partial", AR_CASH_APPLY_STS.PARTIAL);

        return (Integer) getSsmEZDClient().queryObject("countAppliedCredit", ssmParam).getResultObject();
    }

    /**
     * getServiceInvMeter
     * @param bizMsg NFCL3070CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getServiceInvMeter(NFCL3070CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.origInvNum.getValue());
        ssmParam.put("creditMemo", INV_TP.CREDIT_MEMO);
        ssmParam.put("meterCharge", SVC_INV_CHRG_TP.METER_CHARGE);
        // START 2019/06/05 [QC#50730, ADD]
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        // END   2019/06/05 [QC#50730, ADD]

        return getSsmEZDClient().queryObjectList("getServiceInvMeter", ssmParam);
    }

    /**
     * getCITicket
     * @param bizMsg NFCL3070CMsg
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getCITicket(NFCL3070CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("ciNumber", bizMsg.custIncdtId.getValue());
        ssmParam.put("close", CI_TICKET_STS_CLOSE);
        ssmParam.put("objectType", CI_OBJECT_TP_INV);
        ssmParam.put("rowNum", BigDecimal.ONE);

        return (Map<String, Object>) getSsmEZDClient().queryObject("getCITicket", ssmParam).getResultObject();
    }

    /**
     * getOrigInvValue
     * @param bizMsg NFCL3070CMsg
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getOrigInvValue(NFCL3070CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.origInvNum.getValue());
        ssmParam.put("inv", AR_TRX_TP.INVOICE);
        ssmParam.put("rowNum", BigDecimal.ONE);

        return (Map<String, Object>) getSsmEZDClient().queryObject("getOrigInvValue", ssmParam).getResultObject();
    }

    /**
     * getInvLineValue
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @return Map<String, Object>
     */
    public S21SsmEZDResult getInvLineValue(NFCL3070CMsg bizMsg, String invNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", invNum);
        // START 2018/05/23 E.Kameishi [QC#21841,ADD]
        ssmParam.put("freight", INV_LINE_CATG.FREIGHT);
        // END 2018/05/23 E.Kameishi [QC#21841,ADD]

        return getSsmEZDClient().queryObjectList("getInvLineValue", ssmParam);
    }

    // START 2018/05/23 E.Kameishi [QC#21841,ADD]
    /**
     * getMdseValue
     * @param bizMsg NFCL3070CMsg
     * @return String
     */
    public String getMdseValue(NFCL3070CMsg bizMsg, String mdseCd) {

        MDSETMsg inTMsg = new MDSETMsg();
        inTMsg.setSQLID("009");
        inTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("mdseCd01", mdseCd);
        MDSETMsgArray outMsgArray = (MDSETMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (outMsgArray == null || outMsgArray.getValidCount() == 0) {
            return null;
        } else {
            return outMsgArray.no(0).coaMdseTpCd.getValue();
        }
    }
    // END 2018/05/23 E.Kameishi [QC#21841,ADD]
    /**
     * getInvBolValue
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getInvBolValue(NFCL3070CMsg bizMsg, String invNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", invNum);

        return (Map<String, Object>) getSsmEZDClient().queryObject("getInvBolValue", ssmParam).getResultObject();
    }

    /**
     * getTaxGrpExemCdFromBillToCust
     * @param bizMsg NFCL3070CMsg
     * @return DS_TAX_GRP_EXEM_CD String
     */
    public String getTaxGrpExemCdFromBillToCust(NFCL3070CMsg bizMsg) {

        BILL_TO_CUSTTMsg inTMsg = new BILL_TO_CUSTTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("billToCustCd01", bizMsg.billToCustCd.getValue());
        inTMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        BILL_TO_CUSTTMsgArray outMsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (outMsgArray == null || outMsgArray.getValidCount() == 0) {
            return null;
        } else {
            return outMsgArray.no(0).dsTaxGrpExemCd.getValue();
        }
    }

    /**
     * getTaxGrpExemCdFromSellToCust
     * @param bizMsg NFCL3070CMsg
     * @return DS_TAX_GRP_EXEM_CD String
     */
    public String getTaxGrpExemCdFromSellToCust(NFCL3070CMsg bizMsg) {

        SELL_TO_CUSTTMsg inTMsg = new SELL_TO_CUSTTMsg();
        inTMsg.setSQLID("004");
        inTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        inTMsg.setConditionValue("sellToCustCd01", bizMsg.sellToCustCd.getValue());
        SELL_TO_CUSTTMsgArray outMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (outMsgArray == null || outMsgArray.getValidCount() == 0) {
            return null;
        } else {
            return outMsgArray.no(0).dsTaxGrpExemCd.getValue();
        }
    }

    /**
     * getSalesRepAdress
     * @param bizMsg NFCL3070CMsg
     * @param s21PsnTMsg S21_PSNTMsg
     * @param slsRepTocCd String
     * @return S21SsmEZDResult
     */
    // MOD Start 2017/07/19 [QC#19781]
    //public S21SsmEZDResult getSalesRepAdress(NFCL3070CMsg bizMsg, S21_PSNTMsg s21PsnTMsg) {
    public S21SsmEZDResult getSalesRepAdress(NFCL3070CMsg bizMsg, S21_PSNTMsg s21PsnTMsg, String slsRepTocCd) {
    // MOD End   2017/07/19 [QC#19781]
        Map<String, String> ssmParam = new HashMap<String, String>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        // MOD Start 2017/07/19 [QC#19781]
        //ssmParam.put("tocCd", bizMsg.slsRepTocCd.getValue());
        ssmParam.put("tocCd", slsRepTocCd);
        // MOD End   2017/07/19 [QC#19781]
        ssmParam.put("invDt", bizMsg.invDt.getValue());

        // MOD 2017/01/10 [QC#16983]
        // return getSsmEZDClient().queryObject("getSalesRepAdress", ssmParam);
        return getSsmEZDClient().queryEZDMsg("getSalesRepAdress", ssmParam, s21PsnTMsg);
    }

    // ADD Start 2017/07/19 [QC#19781]
    /**
     * getSlsRepTocCd
     * @param bizMsg NFCL3070CMsg
     * @param invLineTmsg INV_LINETMsg
     * @return String
     */
    public String getSlsRepTocCd(NFCL3070CMsg bizMsg, INV_LINETMsg invLineTmsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", invLineTmsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.origInvNum.getValue());
   
        String slsRepTocCd = null;
        slsRepTocCd = (String) getSsmEZDClient().queryObject("getSlsRepTocCd", ssmParam).getResultObject();
        return slsRepTocCd;
    }
    // ADD End   2017/07/19 [QC#19781]

    /**
     * getTaxGrpExemCdFromShipToCust
     * @param bizMsg NFCL3070CMsg
     * @param shipToCustCd String
     * @return DS_TAX_GRP_EXEM_CD String
     */
    public String getTaxGrpExemCdFromShipToCust(NFCL3070CMsg bizMsg, String shipToCustCd) {

        SHIP_TO_CUSTTMsg inTMsg = new SHIP_TO_CUSTTMsg();
        inTMsg.setSQLID("002");
        inTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        inTMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        SHIP_TO_CUSTTMsgArray outTMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (outTMsgArray == null || outTMsgArray.getValidCount() == 0) {
            return null;
        } else {
            return outTMsgArray.no(0).dsTaxGrpExemCd.getValue();
        }
    }

    /**
     * getShipToCust
     * @param bizMsg NFCL3070CMsg
     * @param shipToCustCd String
     * @return SHIP_TO_CUSTTMsg
     */
    public SHIP_TO_CUSTTMsg getShipToCust(NFCL3070CMsg bizMsg, String shipToCustCd) {

        SHIP_TO_CUSTTMsg inTMsg = new SHIP_TO_CUSTTMsg();
        inTMsg.setSQLID("002");
        inTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        inTMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        SHIP_TO_CUSTTMsgArray outTMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (outTMsgArray == null || outTMsgArray.getValidCount() == 0) {
            return null;
        } else {
            return outTMsgArray.no(0);
        }
    }

    /**
     * getShipToCustForSvcInv
     * @param bizMsg NFCL3070CMsg
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getShipToCustForSvcInv(NFCL3070CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.origInvNum.getValue());

        return (Map<String, Object>) getSsmEZDClient().queryObject("getShipToCustForSvcInv", ssmParam).getResultObject();
    }

    /**
     * getDsInvtyLoc
     * @param bizMsg NFCL3070CMsg
     * @param shipFromInvtyLocCd String
     * @param dsInvtyLocVTMsg DS_INVTY_LOC_VTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsInvtyLoc(NFCL3070CMsg bizMsg, String shipFromInvtyLocCd, DS_INVTY_LOC_VTMsg dsInvtyLocVTMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        // MOD 2017/01/10 [QC#16983]
        //ssmParam.put("invtyLocCd", bizMsg.slsRepTocCd.getValue());
        ssmParam.put("invtyLocCd", shipFromInvtyLocCd);

        // MOD 2017/01/10 [QC#16983]
        //return getSsmEZDClient().queryObject("getDsInvtyLoc", ssmParam);
        return getSsmEZDClient().queryEZDMsg("getDsInvtyLoc", ssmParam, dsInvtyLocVTMsg);
    }

    /**
     * getRtlWh
     * @param bizMsg NFCL3070CMsg
     * @param soNum String
     * @return RTL_WH_CD String
     */
    public String getRtlWh(NFCL3070CMsg bizMsg, String soNum) {

        SHPG_ORD_DTLTMsg inTMsg = new SHPG_ORD_DTLTMsg();
        inTMsg.setSQLID("002");
        inTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("soNum01", soNum);
        SHPG_ORD_DTLTMsgArray outTMsgArray = (SHPG_ORD_DTLTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (outTMsgArray == null || outTMsgArray.getValidCount() == 0) {
            return null;
        } else {
            return outTMsgArray.no(0).rtlWhCd.getValue();
        }
    }

    /**
     * getDsInvLineTaxDtlPk
     * @param bizMsg NFCL3070CMsg
     * @param dsInvLineTaxDtlTMsg DS_INV_LINE_TAX_DTLTMsg
     * @return pk BigDecimal
     */
    public BigDecimal getDsInvLineTaxDtlPk(NFCL3070CMsg bizMsg, DS_INV_LINE_TAX_DTLTMsg dsInvLineTaxDtlTMsg) {
        Map<String, String> ssmParam = new HashMap<String, String>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum",           dsInvLineTaxDtlTMsg.invNum.getValue());
        ssmParam.put("invBolLineNum",    dsInvLineTaxDtlTMsg.invBolLineNum.getValue());
        ssmParam.put("invLineNum",       dsInvLineTaxDtlTMsg.invLineNum.getValue());
        ssmParam.put("invLineSubNum",    dsInvLineTaxDtlTMsg.invLineSubNum.getValue());
        ssmParam.put("invTrxLineSubNum", dsInvLineTaxDtlTMsg.invTrxLineSubNum.getValue());
        ssmParam.put("dsSlsTaxTpCd",     dsInvLineTaxDtlTMsg.dsSlsTaxTpCd.getValue());

        return (BigDecimal) getSsmEZDClient().queryObject("getDsInvLineTaxDtlPk", ssmParam).getResultObject();
    }

    // START 2016/08/01 [QC#9192, ADD]
    /**
     * getTaxAreaId
     * @param inParam Map<String, Object>
     * @return String
     */
    public String getTaxAreaId(Map<String, Object> inParam) {
        return (String) getSsmEZDClient().queryObject("getTaxAreaId", inParam).getResultObject();
    }
    // END   2016/08/01 [QC#9192, ADD]

    // START 2018/02/08 E.Kameishi [QC#23551,ADD]
    /**
     * getAjeInvAcctDist
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAjeInvAcctDist(NFCL3070CMsg bizMsg, String invNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", invNum);
        ssmParam.put("dfrdRev", AJE_LINE_IND_TP.DFRD_REV_RECOG_88);

        return getSsmEZDClient().queryObjectList("getAjeInvAcctDist", ssmParam);
    }
    // END 2018/02/08 E.Kameishi [QC#23551,ADD]

    // START 2018/05/30 E.Kameishi [QC#26229,ADD]
    /**
     * getAjeInvAcctDist
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArCrRebilRsn(NFCL3070CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("arCrRebilRsnNm", bizMsg.arCrRebilRsnNm.getValue());

        return getSsmEZDClient().queryObjectList("getArCrRebilRsn", ssmParam);
    }

    /**
     * checkCITicket
     * @param bizMsg NFCL3070CMsg
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> checkCINum(NFCL3070CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("ciNumber", bizMsg.custIncdtId.getValue());
        ssmParam.put("close", CI_TICKET_STS_CLOSE);
        // START 2019/03/25 E.Kameishi [QC#30904,DEL]
        // START 2018/07/13 E.Kameishi [QC#27007,ADD]
        //ssmParam.put("invNum", bizMsg.origInvNum.getValue());
        // END 2018/07/13 E.Kameishi [QC#27007,ADD]
        // END 2019/03/25 E.Kameishi [QC#30904,DEL]

        return (Map<String, Object>) getSsmEZDClient().queryObject("checkCINum", ssmParam).getResultObject();
    }
    /**
     * getArCrRebilValue
     * @param bizMsg NFCL3070CMsg
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getArCrRebilValue(NFCL3070CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("origInvNum", bizMsg.origInvNum.getValue());
        ssmParam.put("receivables", AR_CR_TP.RECEIVABLES);
        ssmParam.put("tax", AR_CR_TP.TAX);
        ssmParam.put("freight", AR_CR_TP.FREIGHT);
        ssmParam.put("cr", AR_CR_REBIL_TP.CREDIT_AND_REBILL);
        ssmParam.put("co", AR_CR_REBIL_TP.CREDIT_ONLY);
        ssmParam.put("cm", INV_TP.CREDIT_MEMO);

        return (Map<String, Object>) getSsmEZDClient().queryObject("getArCrRebilValue", ssmParam).getResultObject();
    }
    // END 2018/05/30 E.Kameishi [QC#26229,ADD]
    // START 2018/08/24 E.Kameishi [QC#27848,MOD]
    /**
     * getSvcPgmMdseCd
     * @param bizMsg NFCL3070CMsg
     * @param BigDecimal svcInvLinePk
     * @return String
     */
    public String getSvcPgmMdseCd(NFCL3070CMsg bizMsg, BigDecimal svcInvLinePk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("svcInvLinePk", svcInvLinePk);

        String svcPgmMdseCd = null;
        svcPgmMdseCd = (String) getSsmEZDClient().queryObject("getSvcPgmMdseCd", ssmParam).getResultObject();
        return svcPgmMdseCd;
    }
    // END 2018/08/24 E.Kameishi [QC#27848,MOD]
    // START 2019/09/17 S.Takami [QC#53456,ADD]
    /**
     * <pre>
     * Check specified Invoice line# is related Set.
     * @param bizMsg Biz. Message.
     * @return BigDecimal.
     * if return value > 0, specified Invoice Line# is related Set.
     * </pre>
     */
    public BigDecimal hasSetParentLine(NFCL3070CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.invLineNum)) {
            return BigDecimal.ZERO;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.origInvNum.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.invBolLineNum)) {
            ssmParam.put("invBolLineNum", bizMsg.invBolLineNum);
        }
        ssmParam.put("invLineNum", bizMsg.invLineNum.getValue());
        ssmParam.put("setPrntInvLIneSubNum", SET_PRNT_INV_LINE_SUB_NUM);
        BigDecimal rslt = (BigDecimal ) getSsmEZDClient().queryObject("hasSetParentLine", ssmParam).getResultObject();
        if (rslt == null) {
            return BigDecimal.ZERO;
        }
        return rslt;
    }
    // END 2019/09/17 S.Takami [QC#53456,ADD]
}