/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC055001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static parts.dbcommon.EZDTBLAccessor.RTNCD_DUPLICATE;
import static parts.dbcommon.EZDTBLAccessor.RTNCD_NOT_FOUND;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.AJE_INV_LINE_ALLOCTMsg;
import business.db.CCYTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_CPO_SLS_CRTMsg;
import business.db.DS_INV_SLS_CRTMsg;
import business.db.INVTMsg;
import business.db.INV_LINETMsg;
import business.db.INV_LINETMsgArray;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.S21_ORGTMsg;
import business.parts.NWZC055001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL_RSN_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_ACCTG_RULE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_SPL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmEZDClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * <pre>
 * Invoice Sales Credit Creation API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2015   Fujitsu         H.Nagashima     Create          N/A
 * 01/04/2016   Fujitsu         H.Nagashima     Update          QC#2652
 * 01/07/2016   Fujitsu         H.Nagashima     Update          QC#2859
 * 02/12/2016   CSAI            K.Uramori       Update          QC#4294
 * 03/24/2016   Fujitsu         H.Nagashima     Update          QC#5514
 * 05/13/2016   Fujitsu         H.Nagashima     Update          QC#8192
 * 05/17/2016   CSAI            K.Uramori       Update          QC#8289
 * 2016/09/21   Hitachi         J.Kim           Update          QC#14547
 * 10/31/2016   Fujitsu         H.Nagashima     Update          QC#14607
 * 10/31/2016   Fujitsu         H.Nagashima     Update          QC#15565
 * 10/31/2016   Fujitsu         T.Murai         Update          QC#14546
 * 11/21/2016   Fujitsu         H.Nagashima     Update          QC#16063,16151
 * 2017/08/25   Fujitsu         S.Takami        Update          S21_NA#20214
 * 2017/09/14   Fujitsu         S.Takami        Update          S21_NA#21097
 * 2017/09/21   Fujitsu         H.Nagashima     Update          S21_NA#17116
 * 2017/10/18   Fujitsu         A.Kosai         Update          S21_NA#21732
 * 2017/12/05   Hitachi         E.Kameishi      Update          QC#22740
 * 2018/01/12   Hitachi         E.Kameishi      Update          QC#23135
 * 2018/02/06   Hitachi         E.Kameishi      Update          QC#23135-2
 * 2018/08/28   Fujitsu         T.Ogura         Update          QC#27944
 * 2019/11/25   Fujitsu         M.Ishii         Update          QC#54798
 * </pre>
 */
public class NWZC055001 extends S21ApiCommonBase {

    private static final String NWZM0163E = "NWZM0163E";
    private static final String NWZM0072E = "NWZM0072E";
    private static final String NWZM0368E = "NWZM0368E";
    private static final String NWZM0634E = "NWZM0634E";
    private static final String NWZM1493E = "NWZM1493E";
    private static final String NWZM1495E = "NWZM1495E";
    private static final String NWZM0364E = "NWZM0364E";
    private static final String NWZM0263E = "NWZM0263E";

    private static final BigDecimal PCT_100 = BigDecimal.valueOf(100);

    private static final String CONST_KEY_SRT_DEVN_DUMMY_MDSE_CD  = "SRT_DEVN_DUMMY_MDSE_CD";
    private static final String CONST_KEY_SRT_ALLOC_DUMMY_MDSE_CD = "SRT_ALLOC_DUMMY_MDSE_CD";

    private final S21SsmEZDClient   ssmEzdClient;
    private final S21SsmBatchClient ssmBatchClient;

    private String trxRsnSrtAlloc;
    // 2017/08/25 S21_NA#20214 Add Start
    private String trxRsnSrtDev = "";
    // 2017/08/25 S21_NA#20214 Add End
    // START 2018/08/28 T.Ogura [QC#27944,DEL]
//    private String batProcDt;
    // END   2018/08/28 T.Ogura [QC#27944,DEL]

    /** ccyCd */
    private String ccyCd;

    /** ccyScale */
    private BigDecimal ccyScale;

    /** global company code */
    private String glblCmpyCd;

    /**
     * constructor
     */
    public NWZC055001() {
        super();
        this.ssmEzdClient   = S21SsmEZDClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Invoice Sales Credit Creation API
     * @param pMsg NWZC055001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC055001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        doProcess(msgMap, onBatchType);
        msgMap.flush();
    }
    /**
     * Invoice Sales Credit Creation API
     * @param pMsgList List<NWZC055001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWZC055001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {

        if (pMsgList != null) {
            for (NWZC055001PMsg pmsg : pMsgList) {
                execute(pmsg, onBatchType);
            }
        }
    }

    protected void doProcess(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        boolean isSuccess = true;

        isSuccess = inputCheck(msgMap);
        if (!isSuccess) {
            return;
        }
        trxRsnSrtAlloc = ZYPCodeDataUtil.getVarCharConstValue("AJE_VAL_TRX_RSN_SRT", glblCmpyCd);
        // 2017/08/25 S21_NA#20214 Add Start
        trxRsnSrtDev = ZYPCodeDataUtil.getVarCharConstValue("AJE_VAL_TRX_RSN_SRT_DEV", glblCmpyCd);
        // 2017/08/25 S21_NA#20214 Add End
        // START 2018/08/28 T.Ogura [QC#27944,DEL]
//        batProcDt = ZYPDateUtil.getBatProcDate();
        // END   2018/08/28 T.Ogura [QC#27944,DEL]

        NWZC055001PMsg pMsg = (NWZC055001PMsg) msgMap.getPmsg();

        // ***** get Invoice Data *****
        // get INV table
        String invNum = pMsg.invNum.getValue();
        INVTMsg invTMsg = new INVTMsg();
        setValue(invTMsg.glblCmpyCd, glblCmpyCd);
        setValue(invTMsg.invNum,     invNum);
        invTMsg = (INVTMsg) S21FastTBLAccessor.findByKey(invTMsg);

        if (invTMsg == null) {
            msgMap.addXxMsgId(NWZM0634E);
            return;
        }
        this.ccyCd = invTMsg.dealCcyCd.getValue();
        this.ccyScale = getCcyScale(msgMap, ccyCd);
        if (ccyScale == null) {
            return;
        }
        boolean errInvFlg = false;

        // get INV_LINE table
        INV_LINETMsgArray invLineTMsgArray = getInvLineArray(invTMsg);
        BigDecimal tmpAmt;
        int trxLineNum = 0;
        for (int i = 0; i < invLineTMsgArray.length(); i++) {
            INV_LINETMsg lineTmsg = invLineTMsgArray.no(i);

            MDSETMsg mdseTmsg = getMdse(lineTmsg.mdseCd.getValue());

            if (mdseTmsg == null) {
                msgMap.addXxMsgId(NWZM0364E);
                return;
            }

            // get information required for deferred revenue setting
            DfrdRevSetupData dfrdData = getDfrdSetupInfo(lineTmsg, mdseTmsg.dfrdAcctgRuleCd.getValue());

            if (dfrdData == null) {
                return;
            }

            dfrdData.setDfrdAcctgRuleCd(mdseTmsg.dfrdAcctgRuleCd.getValue());
            dfrdData.setSvcAllocTpCd(mdseTmsg.svcAllocTpCd.getValue());
            dfrdData.setCoaMdseTpCd(mdseTmsg.coaMdseTpCd.getValue());

            // ***** Split item *****
            List<DS_INV_SLS_CRTMsg> invSlsCrTmsgSplAjeList = splitAjeInvLineAlloc(invTMsg, lineTmsg, dfrdData, msgMap);
            if (invSlsCrTmsgSplAjeList == null) {
                return;
            }

            // ***** split Salesrep *****
            List<DS_INV_SLS_CRTMsg> invSlsCrTmsgSplSlsrepList = splitSalesrepAlloc(lineTmsg, msgMap);

            // ***** create invoice sales credit *****
            DS_INV_SLS_CRTMsg targetTmsg;
            List<DS_INV_SLS_CRTMsg> targetTmsgList = new ArrayList<DS_INV_SLS_CRTMsg>();

            for (DS_INV_SLS_CRTMsg tmpAjeAllocTmsg : invSlsCrTmsgSplAjeList) {

                for (DS_INV_SLS_CRTMsg tmpSlsRepAllocTmsg : invSlsCrTmsgSplSlsrepList) {
                    targetTmsg = new DS_INV_SLS_CRTMsg();
                    setValue(targetTmsg.glblCmpyCd,       glblCmpyCd);
                    setValue(targetTmsg.invNum,           pMsg.invNum.getValue());
                    setValue(targetTmsg.invBolLineNum,    lineTmsg.invBolLineNum.getValue());
                    setValue(targetTmsg.invLineNum,       lineTmsg.invLineNum.getValue());
                    setValue(targetTmsg.invLineSubNum,    lineTmsg.invLineSubNum.getValue());
                    setValue(targetTmsg.invTrxLineSubNum, lineTmsg.invLineSubTrxNum.getValue());
                    setValue(targetTmsg.mdseCd,           lineTmsg.mdseCd.getValue());
                    setValue(targetTmsg.invLineSplTpCd,   tmpAjeAllocTmsg.invLineSplTpCd.getValue());
                    setValue(targetTmsg.invLineSplPct,    tmpAjeAllocTmsg.invLineSplPct.getValue());
                    setValue(targetTmsg.slsRepTocCd,      tmpSlsRepAllocTmsg.slsRepTocCd.getValue());
                    setValue(targetTmsg.slsRepCrPct,      tmpSlsRepAllocTmsg.slsRepCrPct.getValue());
                    setValue(targetTmsg.slsRepRoleTpCd,   tmpSlsRepAllocTmsg.slsRepRoleTpCd.getValue());
                    tmpAmt = (lineTmsg.invLineDealNetAmt.getValue()
                            .multiply(tmpAjeAllocTmsg.invLineSplPct.getValue()).divide(BigDecimal.valueOf(100))
                            .multiply(tmpSlsRepAllocTmsg.slsRepCrPct.getValue()).divide(BigDecimal.valueOf(100)))
                            .setScale(ccyScale.intValue(), HALF_UP);

                    setValue(targetTmsg.dealSlsCrAmt,     tmpAmt);
                    setValue(targetTmsg.funcSlsCrAmt,     tmpAmt);
                    setValue(targetTmsg.dealCcyCd,        ccyCd);

                    setValue(targetTmsg.ajeInvLineAllocCd,        tmpAjeAllocTmsg.ajeInvLineAllocCd.getValue());

                    // set accounting rule
                    // If accounting rule is already set by AJE_INV_LINE_ALLOC, no need to overwrite.
                    if (hasValue(tmpAjeAllocTmsg.dfrdAcctgRuleCd)) {
                        setValue(targetTmsg.dfrdAcctgRuleCd,      tmpAjeAllocTmsg.dfrdAcctgRuleCd.getValue());
                    } else {
                    
                        // If this is bill with equipment item, accounting rule to be maintenance billing. 
                        if (hasValue(lineTmsg.svcPrcCatgCd)) {
                            setValue(targetTmsg.dfrdAcctgRuleCd,      DFRD_ACCTG_RULE.MAINTENANCE_DEFERRAL);
                            
                            // If it is optima item, overwrite the accounting rule.
                            if (DFRD_ACCTG_RULE.OPTIMA_DEFERRAL.equals(mdseTmsg.dfrdAcctgRuleCd.getValue())) {
                                setValue(targetTmsg.dfrdAcctgRuleCd,      DFRD_ACCTG_RULE.OPTIMA_DEFERRAL);
                            }
                        // otherwise, accounting rule to be set by item.
                        } else {
                            setValue(targetTmsg.dfrdAcctgRuleCd,      mdseTmsg.dfrdAcctgRuleCd.getValue());
                        }
                    }

                    S21_ORGTMsg s21orgTmsg = getS21org(targetTmsg.slsRepTocCd.getValue());
                    if (s21orgTmsg == null) {
                        msgMap.addXxMsgId(NWZM0263E);
                        return;
                    }
                    setValue(targetTmsg.slsRepBrCd,       s21orgTmsg.coaBrCd.getValue());
                    setValue(targetTmsg.coaCcCd,          tmpAjeAllocTmsg.coaCcCd.getValue());
                    setValue(targetTmsg.coaAcctCd,          tmpAjeAllocTmsg.coaAcctCd.getValue());

                    if (!hasValue(tmpAjeAllocTmsg.trxCd.getValue())) {
                        setValue(targetTmsg.trxCd,            lineTmsg.trxCd.getValue());
                    } else {
                        setValue(targetTmsg.trxCd,            tmpAjeAllocTmsg.trxCd.getValue());
                    }
                    if (!hasValue(tmpAjeAllocTmsg.trxRsnCd.getValue())) {
                        setValue(targetTmsg.trxRsnCd,         lineTmsg.trxRsnCd.getValue());
                    } else {
                        setValue(targetTmsg.trxRsnCd,         tmpAjeAllocTmsg.trxRsnCd.getValue());
                    }

                    // START 2018/02/06 E.Kameishi [QC#23135,MOD]
                    if (INV_TP.CREDIT_MEMO.equals(invTMsg.invTpCd.getValue())) {
                        if (DFRD_ACCTG_RULE.OPTIMA_DEFERRAL.equals(targetTmsg.dfrdAcctgRuleCd.getValue())) {
                            setValue(targetTmsg.trxRsnCd, TRX_RSN.OPTIMA_CREDIT);
                        }
                    }
                    // START 2017/12/05 E.Kameishi [QC#22740,MOD]
                    // If this is credit memo, overwrite with 'IM'.
                    if (INV_TP.CREDIT_MEMO.equals(invTMsg.invTpCd.getValue())) {
                        if (!DFRD_ACCTG_RULE.SUBSCRIPTION_SERVICE.equals(targetTmsg.dfrdAcctgRuleCd.getValue())
                                || !CR_REBIL_RSN_CATG.EXTERNAL.equals(invTMsg.crRebilRsnCatgCd.getValue())) {
                            setValue(targetTmsg.dfrdAcctgRuleCd,      DFRD_ACCTG_RULE.IMMEDIATE);
                        }
                    }
                    // If this is rebill invoice and Subscription Service and Internal Purpose, overwrite with 'IM'
                    if (INV_TP.INVOICE.equals(invTMsg.invTpCd.getValue())
                            && DFRD_ACCTG_RULE.SUBSCRIPTION_SERVICE.equals(targetTmsg.dfrdAcctgRuleCd.getValue())
                            && CR_REBIL_RSN_CATG.INTERNAL.equals(invTMsg.crRebilRsnCatgCd.getValue())) {
                        setValue(targetTmsg.dfrdAcctgRuleCd,      DFRD_ACCTG_RULE.IMMEDIATE);
                    }
                    // END 2017/12/05 E.Kameishi [QC#22740,MOD]
                    // END 2018/02/06 E.Kameishi [QC#23135,MOD]
                    targetTmsgList.add(targetTmsg);

                }
            }
            if (!targetTmsgList.isEmpty()) {
                BigDecimal sum = ZERO;
                BigDecimal maxAmt = ZERO;
                DS_INV_SLS_CRTMsg maxAmtTmsg = null;
                for (DS_INV_SLS_CRTMsg tmsg : targetTmsgList) {
                    tmpAmt = tmsg.dealSlsCrAmt.getValue();
                    sum = sum.add(tmpAmt);
                    if (maxAmt.equals(ZERO) || maxAmt.compareTo(tmpAmt) < 0) {
                        maxAmt = tmpAmt;
                        maxAmtTmsg = tmsg;
                    }
                }
                BigDecimal diffAmt = lineTmsg.invLineDealNetAmt.getValue().subtract(sum);
                // START 2018/08/28 T.Ogura [QC#27944,MOD]
//                if (diffAmt.compareTo(ZERO) > 0) {
                if (diffAmt.compareTo(ZERO) != 0) {
                // END   2018/08/28 T.Ogura [QC#27944,MOD]
                    setValue(maxAmtTmsg.dealSlsCrAmt, maxAmtTmsg.dealSlsCrAmt.getValue().add(diffAmt));
                 // START 2019/11/25 M.Ishii [QC#54798,ADD]
                    setValue(maxAmtTmsg.funcSlsCrAmt, maxAmtTmsg.dealSlsCrAmt.getValue());
                 // END 2019/11/25 M.Ishii [QC#54798,ADD]
                }
                //insert
                for (DS_INV_SLS_CRTMsg tmsg : targetTmsgList) {
                    setValue(tmsg.dsInvSlsCrPk,     getDsInvSlsCrPk(tmsg));
                    DS_INV_SLS_CRTMsg existTmsg = (DS_INV_SLS_CRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tmsg);
                    if (existTmsg != null) {
                        S21ApiTBLAccessor.update(tmsg);
                        if (RTNCD_NOT_FOUND.equals(tmsg.getReturnCode())) {
                            msgMap.addXxMsgId(NWZM1493E);
                            return;
                        } else {
                            continue;
                        }
                    }
                    S21ApiTBLAccessor.insert(tmsg);
                    if (RTNCD_DUPLICATE.equals(tmsg.getReturnCode())) {
                        msgMap.addXxMsgId(NWZM1493E);
                        return;
                    }
                }
            }
        }

        // create Service Revenue Transfer from invoice line
        String srtDevnMdseCd = null;
        String srtAllocMdseCd = null;
        String devAcctgRule = null;
        String allocAcctgRule = null;

        for (int i = 0; i < invLineTMsgArray.length(); i++) {
            INV_LINETMsg invLineTMsg = invLineTMsgArray.no(i);

            CPO_DTLTMsg cpoDtlTMsg = getServiceRevenueTransferAmount(invLineTMsg, msgMap);
            if (cpoDtlTMsg != null) {
                BigDecimal dealSrtAmt = cpoDtlTMsg.dealSvcRevTrnsfAmt.getValue();
                BigDecimal funcSrtAmt = cpoDtlTMsg.funcSvcRevTrnsfAmt.getValue();
                if (srtDevnMdseCd == null) {
                    srtDevnMdseCd  = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_SRT_DEVN_DUMMY_MDSE_CD,  glblCmpyCd);
                    MDSETMsg dsMdseInfoTmsg = getMdseSRT(srtDevnMdseCd);
                    if (dsMdseInfoTmsg == null) {
                        msgMap.addXxMsgId(NWZM0364E);
                        return;
                    }
                    devAcctgRule = dsMdseInfoTmsg.dfrdAcctgRuleCd.getValue();
                }
                if (srtAllocMdseCd == null) {
                    srtAllocMdseCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_SRT_ALLOC_DUMMY_MDSE_CD, glblCmpyCd);
                    MDSETMsg dsMdseInfoTmsg = getMdseSRT(srtAllocMdseCd);
                    if (dsMdseInfoTmsg == null) {
                        msgMap.addXxMsgId(NWZM0364E);
                        return;
                    }
                    allocAcctgRule = dsMdseInfoTmsg.dfrdAcctgRuleCd.getValue();
                }

                // 2017/10/18 S21_NA#21732 Add Start
                if (S21StringUtil.isEquals(invTMsg.invTpCd.getValue(), INV_TP.CREDIT_MEMO)) {
                    dealSrtAmt = dealSrtAmt.negate();
                    funcSrtAmt = funcSrtAmt.negate();

                    // 2017/10/18 S21_NA#22627 Mod Start
                    allocAcctgRule = DFRD_ACCTG_RULE.IMMEDIATE;
                    // 2017/10/18 S21_NA#22627 Mod End
                }
                // 2017/10/18 S21_NA#21732 Add End

                isSuccess = insertDsInvSlsCrForSvcRevTrns(msgMap, invLineTMsg, INV_LINE_SPL_TP.DEVIATION, ++trxLineNum, srtDevnMdseCd, dealSrtAmt, funcSrtAmt, devAcctgRule);
                if (!isSuccess) {
                    return;
                }

                isSuccess = insertDsInvSlsCrForSvcRevTrns(msgMap, invLineTMsg, INV_LINE_SPL_TP.ALLOCATION, ++trxLineNum, srtAllocMdseCd, dealSrtAmt.negate(), funcSrtAmt.negate(), allocAcctgRule);
                if (!isSuccess) {
                    return;
                }
            }

        }
        if (errInvFlg) {
            updateErrFlgForDsInv(msgMap, invNum);
        }
    }

    private Boolean inputCheck(S21ApiMessageMap msgMap) {

        boolean isSuccess = true;

        NWZC055001PMsg pMsg = (NWZC055001PMsg) msgMap.getPmsg();

        if (!hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0163E);
            isSuccess = false;
        } else {
            glblCmpyCd = pMsg.glblCmpyCd.getValue();
        }

        if (!hasValue(pMsg.invNum)) {
            msgMap.addXxMsgId(NWZM0072E);
            isSuccess = false;
        }

        return isSuccess;
    }

    private INV_LINETMsgArray getInvLineArray(INVTMsg invTMsg) {

        INV_LINETMsg invLineTMsg = new INV_LINETMsg();
        invLineTMsg.setSQLID("002");
        invLineTMsg.setConditionValue("glblCmpyCd01",   glblCmpyCd);
        invLineTMsg.setConditionValue("invNum01",       invTMsg.invNum.getValue());

        INV_LINETMsgArray invLineTMsgArray = (INV_LINETMsgArray) S21ApiTBLAccessor.findByCondition(invLineTMsg);

        return invLineTMsgArray;
    }

    private List<DS_INV_SLS_CRTMsg> splitAjeInvLineAlloc(INVTMsg invTmsg, INV_LINETMsg lineTmsg, DfrdRevSetupData dfrdData, S21ApiMessageMap msgMap) {

        DS_INV_SLS_CRTMsg tmptmsg = new DS_INV_SLS_CRTMsg();
        List<DS_INV_SLS_CRTMsg> tmpTmsgList = new ArrayList<DS_INV_SLS_CRTMsg>();
        boolean existflg = false;
        // QC#17116 del Start
//        String lineBizTp = invTmsg.lineBizTpCd.getValue();
        // QC#17116 del End

        if (hasValue(dfrdData.getSvcAllocTpCd())) {  // Allocation Type is defined

            Map<String, Object> mapParam = new HashMap<String, Object>();
            mapParam.put("glblCmpyCd", glblCmpyCd);
            mapParam.put("svcAllocTpCd", dfrdData.getSvcAllocTpCd());

            // Retrieve allocation ratio
            List<AJE_INV_LINE_ALLOCTMsg> ssmResList = (List<AJE_INV_LINE_ALLOCTMsg>) ssmBatchClient.queryObjectList("queryAJE_INV_LINE_ALLOC", mapParam);
            if (ssmResList.isEmpty()) {
                existflg = false;

            } else {
                existflg = true;
                if (ssmResList.size() == 1) {
                    AJE_INV_LINE_ALLOCTMsg ajeInvLineAllocTmsg = ssmResList.get(0);

                    BigDecimal equipAllocPct = ajeInvLineAllocTmsg.equipAllocPct.getValue();
                    if (hasValue(equipAllocPct) && !equipAllocPct.equals(ZERO)) {
                        tmptmsg = new DS_INV_SLS_CRTMsg();
                        setValue(tmptmsg.invLineSplTpCd,       INV_LINE_SPL_TP.EQUIPMENT);
                        setValue(tmptmsg.invLineSplPct,        equipAllocPct);
                        setValue(tmptmsg.coaAcctCd,            ajeInvLineAllocTmsg.equipCoaAcctCd.getValue());
                        setValue(tmptmsg.trxCd,                ajeInvLineAllocTmsg.equipTrxCd.getValue());
                        setValue(tmptmsg.trxRsnCd,             ajeInvLineAllocTmsg.equipTrxRsnCd.getValue());
                        setValue(tmptmsg.ajeInvLineAllocCd, ajeInvLineAllocTmsg.ajeInvLineAllocCd.getValue());
                        setDfrdRelatedInfo(tmptmsg, ajeInvLineAllocTmsg, invTmsg, lineTmsg, dfrdData, 1);
                        tmpTmsgList.add(tmptmsg);
                    }

                    BigDecimal svcAllocPct   = ajeInvLineAllocTmsg.svcAllocPct.getValue();
                    if (hasValue(svcAllocPct) && !svcAllocPct.equals(ZERO)) {
                        tmptmsg = new DS_INV_SLS_CRTMsg();
                        setValue(tmptmsg.invLineSplTpCd,       INV_LINE_SPL_TP.SERVICE);
                        setValue(tmptmsg.invLineSplPct,        svcAllocPct);
                        // QC#17116 del Start
//                        setValue(tmptmsg.coaCcCd,              ajeInvLineAllocTmsg.svcCoaCcCd.getValue());
//                        setValue(tmptmsg.coaAcctCd,            ajeInvLineAllocTmsg.svcCoaAcctCd.getValue());
                        // QC#17116 del End
                        setValue(tmptmsg.trxCd,                ajeInvLineAllocTmsg.svcTrxCd.getValue());
                        setValue(tmptmsg.trxRsnCd,             ajeInvLineAllocTmsg.svcTrxRsnCd.getValue());
                        setValue(tmptmsg.ajeInvLineAllocCd, ajeInvLineAllocTmsg.ajeInvLineAllocCd.getValue());
                        setDfrdRelatedInfo(tmptmsg, ajeInvLineAllocTmsg, invTmsg, lineTmsg, dfrdData, 2);
                        tmpTmsgList.add(tmptmsg);
                    }

                    BigDecimal splyAllocPct  = ajeInvLineAllocTmsg.splyAllocPct.getValue();
                    if (hasValue(splyAllocPct) && !splyAllocPct.equals(ZERO)) {
                        tmptmsg = new DS_INV_SLS_CRTMsg();
                        setValue(tmptmsg.invLineSplTpCd,       INV_LINE_SPL_TP.SUPPLY);
                        setValue(tmptmsg.invLineSplPct,        splyAllocPct);
                        // QC#17116 del Start
//                        setValue(tmptmsg.coaAcctCd,            ajeInvLineAllocTmsg.splyCoaAcctCd.getValue());
                        // QC#17116 del End
                        setValue(tmptmsg.trxCd,                ajeInvLineAllocTmsg.splyTrxCd.getValue());
                        setValue(tmptmsg.trxRsnCd,             ajeInvLineAllocTmsg.splyTrxRsnCd.getValue());

                        // QC#17116 del Start
//                        if (LINE_BIZ_TP.ESS.equals(lineBizTp)) {
//                            setValue(tmptmsg.coaCcCd,              ajeInvLineAllocTmsg.splyEssCoaCcCd.getValue());
//                        } else if (LINE_BIZ_TP.PPS.equals(lineBizTp)) {
//                            setValue(tmptmsg.coaCcCd,              ajeInvLineAllocTmsg.splyPpsCoaCcCd.getValue());
//                        } else if (LINE_BIZ_TP.LFS.equals(lineBizTp)) {
//                            setValue(tmptmsg.coaCcCd,              ajeInvLineAllocTmsg.splyLfsCoaCcCd.getValue());
//                        } else {
//                            setValue(tmptmsg.coaCcCd,              ajeInvLineAllocTmsg.splyCoaCcCd.getValue());
//                        }
                        // QC#17116 del End

                        setValue(tmptmsg.ajeInvLineAllocCd, ajeInvLineAllocTmsg.ajeInvLineAllocCd.getValue());
                        setDfrdRelatedInfo(tmptmsg, ajeInvLineAllocTmsg, invTmsg, lineTmsg, dfrdData, 3);

                        tmpTmsgList.add(tmptmsg);
                    }
                } else {
                    //more than one
                    msgMap.addXxMsgId(NWZM1495E);
                    return null;
                }
            }
        }

        // no allocate
        if (!hasValue(dfrdData.getSvcAllocTpCd()) || !existflg) {

            tmptmsg = new DS_INV_SLS_CRTMsg();
            setValue(tmptmsg.invLineSplPct, PCT_100);
            tmpTmsgList.add(tmptmsg);
        }
        return tmpTmsgList;
    }

    private List<DS_INV_SLS_CRTMsg> splitSalesrepAlloc(INV_LINETMsg lineTmsg, S21ApiMessageMap msgMap) {

        DS_INV_SLS_CRTMsg tmpTmsg;
        List<DS_INV_SLS_CRTMsg> tmpTmsgList = null;
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd",       glblCmpyCd);
        mapParam.put("cpoOrdNum",        lineTmsg.cpoOrdNum.getValue());
        mapParam.put("dsOrdPosnNum",     lineTmsg.dsOrdPosnNum.getValue());
        mapParam.put("slsCrQuotFlg",     ZYPConstant.FLG_ON_Y);
        NWZC055001PMsg pMsg = (NWZC055001PMsg) msgMap.getPmsg();
        mapParam.put("dsOrdLineDrctnCd", pMsg.dsOrdLineDrctnCd.getValue());

        List<DS_CPO_SLS_CRTMsg> dsCpoSlsCrTmsgList = (List<DS_CPO_SLS_CRTMsg>) ssmBatchClient.queryObjectList("queryDS_CPO_SLS_CR", mapParam);

        tmpTmsgList = new  ArrayList<DS_INV_SLS_CRTMsg>();
        if (dsCpoSlsCrTmsgList.isEmpty()) {
            mapParam = new HashMap<String, Object>();
            mapParam.put("glblCmpyCd",       glblCmpyCd);
            mapParam.put("cpoOrdNum",        lineTmsg.cpoOrdNum.getValue());
            mapParam.put("slsCrQuotFlg",     ZYPConstant.FLG_ON_Y);

            dsCpoSlsCrTmsgList = (List<DS_CPO_SLS_CRTMsg>) ssmBatchClient.queryObjectList("queryDS_CPO_SLS_CR", mapParam);

            if (dsCpoSlsCrTmsgList.isEmpty()) {
                // sales credit not found 
                tmpTmsg = new DS_INV_SLS_CRTMsg();
                setValue(tmpTmsg.slsRepTocCd, lineTmsg.slsRepTocCd.getValue());
                setValue(tmpTmsg.slsRepCrPct, PCT_100);
                tmpTmsgList.add(tmpTmsg);
            }
        }
        if (!dsCpoSlsCrTmsgList.isEmpty()) {
            for (DS_CPO_SLS_CRTMsg cpoSlsCrTmsg : dsCpoSlsCrTmsgList) {
                tmpTmsg = new DS_INV_SLS_CRTMsg();
                setValue(tmpTmsg.slsRepTocCd,    cpoSlsCrTmsg.slsRepTocCd.getValue());
                setValue(tmpTmsg.slsRepCrPct,    cpoSlsCrTmsg.slsRepCrPct.getValue());
                setValue(tmpTmsg.slsRepRoleTpCd, cpoSlsCrTmsg.slsRepRoleTpCd.getValue());
                tmpTmsgList.add(tmpTmsg);
            }
        }
        return tmpTmsgList;
    }

    private S21_ORGTMsg getS21org(String slsRepTocCd) {

        S21_ORGTMsg tmsg = new S21_ORGTMsg();
        setValue(tmsg.glblCmpyCd, glblCmpyCd);
        setValue(tmsg.tocCd, slsRepTocCd);
        tmsg = (S21_ORGTMsg) S21FastTBLAccessor.findByKey(tmsg);

        return tmsg;
    }

    private BigDecimal getCcyScale(S21ApiMessageMap msgMap, String ccyCd) {

        CCYTMsg ccyTMsg = new CCYTMsg();
        setValue(ccyTMsg.glblCmpyCd, glblCmpyCd);
        setValue(ccyTMsg.ccyCd,      ccyCd);

        ccyTMsg = (CCYTMsg) S21CodeTableAccessor.findByKey(ccyTMsg);
        if (ccyTMsg == null || !hasValue(ccyTMsg.aftDeclPntDigitNum)) {
            msgMap.addXxMsgId(NWZM0368E);
            return null;
        }

        return ccyTMsg.aftDeclPntDigitNum.getValue();
    }

    private CPO_DTLTMsg getServiceRevenueTransferAmount(INV_LINETMsg invLineTMsg, S21ApiMessageMap msgMap) {

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd",             glblCmpyCd);
        mapParam.put("cpoOrdNum",              invLineTMsg.cpoOrdNum.getValue());
        mapParam.put("cpoDtlLineNum",          invLineTMsg.cpoDtlLineNum.getValue());
        mapParam.put("cpoDtlLineSubNum",       invLineTMsg.cpoDtlLineSubNum.getValue());
        mapParam.put("invNum",                 invLineTMsg.invNum.getValue());
        mapParam.put("invBolLineNum",          invLineTMsg.invBolLineNum.getValue());
        mapParam.put("invLineNum",             invLineTMsg.invLineNum.getValue());
        mapParam.put("invLineSubNum",          invLineTMsg.invLineSubNum.getValue());
        mapParam.put("invLineSplTpDeviation",  INV_LINE_SPL_TP.DEVIATION);
        mapParam.put("invLineSplTpAllocation", INV_LINE_SPL_TP.ALLOCATION);

        CPO_DTLTMsg cpoDtlTmsg = new CPO_DTLTMsg();
        S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("getSVC_REV_TRNSF_AMT", mapParam, cpoDtlTmsg);

        if (ssmRes.getQueryResultCount() == 0) {
            return null;
        }
        return cpoDtlTmsg;
    }

    private boolean insertDsInvSlsCrForSvcRevTrns(S21ApiMessageMap msgMap, INV_LINETMsg invLineTMsg, String invLineSplTpCd, int trxLineNum, String mdseCd, BigDecimal dealSlsCrAmt, BigDecimal funcSlsCrAmt, String acctgRule) {

        boolean isSuccess = true;

        DS_INV_SLS_CRTMsg dsInvSlsCrTMsg = new DS_INV_SLS_CRTMsg();
        setValue(dsInvSlsCrTMsg.glblCmpyCd,       glblCmpyCd);
        setValue(dsInvSlsCrTMsg.invNum,           invLineTMsg.invNum.getValue());
        setValue(dsInvSlsCrTMsg.invBolLineNum,    invLineTMsg.invBolLineNum.getValue());
        setValue(dsInvSlsCrTMsg.invLineNum,       invLineTMsg.invLineNum.getValue());
        setValue(dsInvSlsCrTMsg.invLineSubNum,    invLineTMsg.invLineSubNum.getValue());
        setValue(dsInvSlsCrTMsg.invTrxLineSubNum,    invLineTMsg.invLineSubTrxNum.getValue());
        setValue(dsInvSlsCrTMsg.mdseCd,           mdseCd);
        setValue(dsInvSlsCrTMsg.invLineSplTpCd,   invLineSplTpCd);
        setValue(dsInvSlsCrTMsg.dealCcyCd,        ccyCd);
        setValue(dsInvSlsCrTMsg.dealSlsCrAmt,     dealSlsCrAmt);
        setValue(dsInvSlsCrTMsg.funcSlsCrAmt,     funcSlsCrAmt);
        setValue(dsInvSlsCrTMsg.trxCd,            invLineTMsg.trxCd.getValue());
        if (INV_LINE_SPL_TP.ALLOCATION.equals(invLineSplTpCd)) {
            setValue(dsInvSlsCrTMsg.trxRsnCd,     trxRsnSrtAlloc);
            setValue(dsInvSlsCrTMsg.slsRepCrPct,  PCT_100);
            setValue(dsInvSlsCrTMsg.invLineSplPct,PCT_100);

        } else if (INV_LINE_SPL_TP.DEVIATION.equals(invLineSplTpCd)) {
            // 2017/08/25 S21_NA#20214 Mod Start
//            setValue(dsInvSlsCrTMsg.trxRsnCd,     invLineTMsg.trxRsnCd.getValue());
            setValue(dsInvSlsCrTMsg.trxRsnCd,     trxRsnSrtDev);
            // 2017/08/25 S21_NA#20214 Mod End
            setValue(dsInvSlsCrTMsg.slsRepCrPct,  PCT_100.negate());
            setValue(dsInvSlsCrTMsg.invLineSplPct,PCT_100.negate());   //QC#16063 add

        } else {
            setValue(dsInvSlsCrTMsg.trxRsnCd,         invLineTMsg.trxRsnCd.getValue());
        }
        setValue(dsInvSlsCrTMsg.dsInvSlsCrPk,     getDsInvSlsCrPk(dsInvSlsCrTMsg));
        setValue(dsInvSlsCrTMsg.slsRepTocCd, invLineTMsg.slsRepTocCd);
        setValue(dsInvSlsCrTMsg.dfrdAcctgRuleCd, acctgRule);

        DS_INV_SLS_CRTMsg tmpTmsg = (DS_INV_SLS_CRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsInvSlsCrTMsg);
        if (tmpTmsg != null) {
            S21ApiTBLAccessor.update(dsInvSlsCrTMsg);
            if (RTNCD_NOT_FOUND.equals(dsInvSlsCrTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NWZM1493E);
                return false;
            } else {
                return true;
            }
        }

        S21ApiTBLAccessor.insert(dsInvSlsCrTMsg);
        if (RTNCD_DUPLICATE.equals(dsInvSlsCrTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM1493E);
            isSuccess = false;
        }

        return isSuccess;
    }

    private DfrdRevSetupData getDfrdSetupInfo(INV_LINETMsg invLineTMsg, String dfrdAcctgRule) {

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", this.glblCmpyCd);
        mapParam.put("invNum", invLineTMsg.invNum.getValue());
        mapParam.put("bolLineNum", invLineTMsg.invBolLineNum.getValue());
        mapParam.put("invLineNum", invLineTMsg.invLineNum.getValue());
        mapParam.put("invLineSubNum", invLineTMsg.invLineSubNum.getValue());
        mapParam.put("invLineSubTrxNum", invLineTMsg.invLineSubTrxNum.getValue());
        if (!hasValue(dfrdAcctgRule)) {
            dfrdAcctgRule = DFRD_ACCTG_RULE.IMMEDIATE;
        }
        mapParam.put("acctgRule", dfrdAcctgRule);

        DfrdRevSetupData result = (DfrdRevSetupData) ssmBatchClient.queryObject("getDfrdSetupInfo", mapParam);

        return result;
    }

    private void setDfrdRelatedInfo(DS_INV_SLS_CRTMsg tmsg, AJE_INV_LINE_ALLOCTMsg alloc, INVTMsg invTmsg, INV_LINETMsg lineTmsg, DfrdRevSetupData dfrdData, int type) {
        if (type == 0) {  // no department allocation
            setValue(tmsg.dfrdAcctgRuleCd,      dfrdData.getDfrdAcctgRuleCd());
        } else {
            if (type == 1) {
                setValue(tmsg.dfrdAcctgRuleCd,      alloc.equipAcctgRuleCd.getValue());
            } else if (type == 2) {
                setValue(tmsg.dfrdAcctgRuleCd,      alloc.svcAcctgRuleCd.getValue());
            } else if (type == 3) {
                setValue(tmsg.dfrdAcctgRuleCd,      alloc.splyAcctgRuleCd.getValue());
            }
        }
    }

    private BigDecimal getDsInvSlsCrPk(DS_INV_SLS_CRTMsg dsInvSlsCrTMsg) {

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd",     glblCmpyCd);
        mapParam.put("invNum",         dsInvSlsCrTMsg.invNum.getValue());
        mapParam.put("invBolLineNum",  dsInvSlsCrTMsg.invBolLineNum.getValue());
        mapParam.put("invLineNum",     dsInvSlsCrTMsg.invLineNum.getValue());
        mapParam.put("invLineSubNum",  dsInvSlsCrTMsg.invLineSubNum.getValue());
        mapParam.put("invLineSplTpCd", dsInvSlsCrTMsg.invLineSplTpCd.getValue());
        mapParam.put("slsRepTocCd",    dsInvSlsCrTMsg.slsRepTocCd.getValue());
        // 2017/09/14 S21_NA#21097 Add Start
        mapParam.put("slsRepRoleTpCd", dsInvSlsCrTMsg.slsRepRoleTpCd.getValue());
        // 2017/09/14 S21_NA#21097 Add End

        BigDecimal dsInvSlsCrPk = (BigDecimal) ssmBatchClient.queryObject("getDsInvSlsCrPk", mapParam);

        if (!hasValue(dsInvSlsCrPk)) {
            dsInvSlsCrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_INV_SLS_CR_SQ);
        }
        return dsInvSlsCrPk;
    }

    private MDSETMsg getMdse(String mdseCd) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        setValue(mdseTMsg.mdseCd,     mdseCd);
        mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {
            // get mdsecd
            ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
            setValue(ordTakeMdseTMsg.glblCmpyCd, glblCmpyCd);
            setValue(ordTakeMdseTMsg.ordTakeMdseCd, mdseCd);
            ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdseTMsg);

            mdseTMsg = new MDSETMsg();
            setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
            setValue(mdseTMsg.mdseCd,     ordTakeMdseTMsg.mdseCd.getValue());
            mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
        }

        return mdseTMsg;
    }

    private MDSETMsg getMdseSRT(String mdseCd) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        setValue(mdseTMsg.mdseCd,     mdseCd);
        mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);

        return mdseTMsg;
    }

    private void updateErrFlgForDsInv(S21ApiMessageMap msgMap, String invNum) {

        INVTMsg invTMsg = new INVTMsg();
        setValue(invTMsg.glblCmpyCd,      glblCmpyCd);
        setValue(invTMsg.invNum,          invNum);
        setValue(invTMsg.itrlInvErrFlg,   ZYPConstant.FLG_ON_Y);
        S21ApiTBLAccessor.updateSelectionField(invTMsg, new String[]{"itrlInvErrFlg"});

        if (RTNCD_NOT_FOUND.equals(invTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM1493E);
        }

    }
}
