/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2010;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_CHRG_CPLT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL2010Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Fujitsu         C.Yokoi         Create          N/A
 * 2022/12/16   Hitachi         R.Takau         Update          QC#60823
 *</pre>
 */
public final class NWAL2010Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL2010Query MY_INSTANCE = new NWAL2010Query();

    /**
     * Private constructor
     */
    private NWAL2010Query() {
        super();
    }

    /**
     * Get the NWAL2010Query instance.
     * @return NWAL2010Query instance
     */
    public static NWAL2010Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NWAL2010CMsg
     * @param glblMsg NWAL2010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("sellToCust", bizMsg.sellToCustCd.getValue());

        if (ZYPCommonFunc.hasValue(bizMsg.crCardValidFlg)) {
            params.put("validFlg", ZYPConstant.FLG_OFF_N);
        } else {
            params.put("validFlg", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.crCardCustRefNum)) {
            params.put("refNum", bizMsg.crCardCustRefNum.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.crCardExprYrMth)) {
            params.put("exprYrMth", bizMsg.crCardExprYrMth.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.crCardTpCd)) {
            params.put("cardTpCd", bizMsg.crCardTpCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.crCardLastDigitNum)) {
            params.put("lastDigitNum", bizMsg.crCardLastDigitNum.getValue());
        }

        return getSsmEZDClient().queryEZDMsgArray("getSearchResult", params, glblMsg.A);
    }

    /**
     * getSampleList
     * @return S21SsmEZDResult
     */
    public BigDecimal getCrCardTrxPk(NWAL2010CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("authStsCd", CR_CARD_AUTH_STS.WAITING_FOR_PRE_AUTH);

        if (ZYPCommonFunc.hasValue(bizMsg.crCardTrxTpCd)) {
            params.put("trxTpCd", bizMsg.crCardTrxTpCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.firstTrxInfoTxt)) {
            params.put("firstTxt", bizMsg.firstTrxInfoTxt.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.scdTrxInfoTxt)) {
            params.put("scdTxt", bizMsg.scdTrxInfoTxt.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.thirdTrxInfoTxt)) {
            params.put("thirdTxt", bizMsg.thirdTrxInfoTxt.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.frthTrxInfoTxt)) {
            params.put("frthTxt", bizMsg.frthTrxInfoTxt.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.fifthTrxInfoTxt)) {
            params.put("fifthTxt", bizMsg.fifthTrxInfoTxt.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.firstTrxInfoNum)) {
            params.put("firstNum", bizMsg.firstTrxInfoNum.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.scdTrxInfoNum)) {
            params.put("scdNum", bizMsg.scdTrxInfoNum.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.thirdTrxInfoNum)) {
            params.put("thirdNum", bizMsg.thirdTrxInfoNum.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.frthTrxInfoNum)) {
            params.put("frthNum", bizMsg.frthTrxInfoNum.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.fifthTrxInfoNum)) {
            params.put("fifthNum", bizMsg.fifthTrxInfoNum.getValue());
        }

        return (BigDecimal) getSsmEZDClient().queryObject("getCrCardTrxPk", params).getResultObject();
    }
    
    //START 2022/12/20 R.Takau [QC#60823,ADD]
    /**
     * getOpenTRX
     * @param bizMsg NWAL2010CMsg
     * @param glblMsg NWAL2010SMsg
     * @return BigDecimal
     */
    public BigDecimal getOpenTRX(String custRefNum, NWAL2010SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
                
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("crCardCustRefNum", custRefNum);
        params.put("CrCardTrxTpCd", CR_CARD_TRX_TP.COLLECTION);
        params.put("OrdHdrStsCd", new String[] {ORD_HDR_STS.CANCELLED, ORD_HDR_STS.CLOSED});
        params.put("CrCardTrxTpCdCpo", CR_CARD_TRX_TP.CPO);
        params.put("SplyQuoteStsCd", SPLY_QUOTE_STS.CANCELLED);
        params.put("ArCashApllyStsCd", new String[] {AR_CASH_APPLY_STS.APPLIED, AR_CASH_APPLY_STS.VOID, AR_CASH_APPLY_STS.UNIDENTIFIED});
        params.put("CrCardTrxTpCdAr", CR_CARD_TRX_TP.AR);
        params.put("CrCardChrgCpltCd", new String[] {CR_CARD_CHRG_CPLT.NO_NEED_CREDIT_CARD_CHARGE, CR_CARD_CHRG_CPLT.COMPLETED_CREDIT_CARD_CHARGE, CR_CARD_CHRG_CPLT.FAILED_CREDIT_CARD_CHARGE});
        params.put("CrCardTrxTpCdInv", CR_CARD_TRX_TP.INVOICE);
        params.put("DsContrStsCd", new String[] {DS_CONTR_STS.EXPIRED, DS_CONTR_STS.CANCELLED, DS_CONTR_STS.TERMINATED});
        params.put("CrCardTrxTpCdContrH", CR_CARD_TRX_TP.CONTRACT_HEADER);   

        return (BigDecimal)getSsmEZDClient().queryObject("getOpenTRX", params).getResultObject();
    }   
    //END 2022/12/20 R.Takau [QC#60823,ADD]
}