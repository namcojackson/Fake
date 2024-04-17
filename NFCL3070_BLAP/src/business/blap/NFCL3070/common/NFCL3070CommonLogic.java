/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3070.common;

import static business.blap.NFCL3070.constant.NFCL3070Constant.CONST_FRT_TAX_LINE_NUM;
import static business.blap.NFCL3070.constant.NFCL3070Constant.CONST_KEY_DEFAULT_TAX_TRX_TP;
import static business.blap.NFCL3070.constant.NFCL3070Constant.CONST_KEY_FRT_TAX_DUMMY_MDSE_CD;
import static business.blap.NFCL3070.constant.NFCL3070Constant.HIDX;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NFAM0035E;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NFCM0782E;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NFCM0791W;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NFCM0792E;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NFCM0820E;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NFCM0886E;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NFCM0895E;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NZZM0000E;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NZZM0001W;
import static business.blap.NFCL3070.constant.NFCL3070Constant.ONL_BAT_TP;
import static business.blap.NFCL3070.constant.NFCL3070Constant.PIDX;
import static business.blap.NFCL3070.constant.NFCL3070Constant.RQST_TP_BOTH;
import static business.blap.NFCL3070.constant.NFCL3070Constant.RQST_TP_CREDIT_ONLY;
import static business.blap.NFCL3070.constant.NFCL3070Constant.RQST_TP_REBILL_ONLY;
import static business.blap.NFCL3070.constant.NFCL3070Constant.SCL_4;
import static business.blap.NFCL3070.constant.NFCL3070Constant.SIDX;
import static business.blap.NFCL3070.constant.NFCL3070Constant.ZZM9037E;
import static business.blap.NFCL3070.constant.NFCL3070Constant.ZZZM9006E;
import static business.blap.NFCL3070.constant.NFCL3070Constant.COMP_PROC_STS_INCOMPLETE;
import static business.blap.NFCL3070.constant.NFCL3070Constant.INIT_INV_BOL_LINE_NUM;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL3070.NFCL3070CMsg;
import business.blap.NFCL3070.NFCL3070Query;
import business.db.AJE_INV_ACCT_DISTTMsg;
import business.db.AJE_INV_ACCT_DISTTMsgArray;
import business.db.AR_ACCT_DTTMsg;
import business.db.AR_CR_REBILTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.CCYTMsg;
import business.db.CNTYTMsg;
import business.db.DS_INVTY_LOC_VTMsg;
import business.db.DS_INV_LINE_TAX_DTLTMsg;
import business.db.DS_INV_MTR_DTLTMsg;
import business.db.DS_INV_MTR_DTLTMsgArray;
import business.db.DS_INV_SLS_CRTMsg;
import business.db.DS_INV_SLS_CRTMsgArray;
import business.db.DS_INV_TPTMsg;
import business.db.DS_TAX_GRP_EXEMTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.INVTMsg;
import business.db.INV_BOLTMsg;
import business.db.INV_BOLTMsgArray;
import business.db.INV_LINETMsg;
import business.db.INV_LINETMsgArray;
import business.db.INV_PRMO_INFOTMsg;
import business.db.INV_PRMO_INFOTMsgArray;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PMT_TERMTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.S21_PSNTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SVC_ALLOC_TPTMsg;
import business.parts.NFZC103001PMsg;
import business.parts.NWZC036101PMsg;
import business.parts.NWZC036101_taxCalculateInputLinePMsg;
import business.parts.NWZC036101_taxCalculateOutputLinePMsg;

import com.canon.cusa.s21.api.NFA.NFZC103001.NFZC103001;
import com.canon.cusa.s21.api.NWZ.NWZC036101.NWZC036101;
import com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CR_REBIL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_ACCTG_RULE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SLS_TAX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NFCL3070CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/19   Fujitsu         S.Fujita        Update          N/A
 * 2016/03/25   Fujitsu         S.Fujita        Update          QC#5915
 * 2016/04/13   Fujitsu         S.Fujita        Update          QC#6565
 * 2016/04/28   Fujitsu         S.Fujita        Update          QC#7722
 * 2016/06/28   Fujitsu         S.Fujita        Update          QC#10942
 * 2016/07/07   Fujitsu         S.Fujita        Update          QC#10458
 * 2016/08/01   Hitachi         Y.Takeno        Update          QC#9192
 * 2016/10/07   Fujitsu         S.Fujita        Update          QC#10522
 * 2016/11/16   Fujitsu         S.Fujita        Update          QC#15832
 * 2016/12/13   Hitachi         Y.Tsuchimoto    Update          QC#16573
 * 2017/01/04   Fujitsu         T.Murai         Update          QC#16842
 * 2017/01/10   Fujitsu         T.Murai         Update          QC#16983
 * 2017/01/11   Fujitsu         S.Fujita        Update          QC#17079
 * 2017/02/07   Fujitsu         T.Murai         Update          QC#17464
 * 2017/03/08   Fujitsu         T.Murai         Update          QC#17901
 * 2017/07/05   Hitachi         E.Kameishi      Update          QC#19757
 * 2017/07/18   Fujitsu         H.Ikeda         Update          QC#19781
 * 2017/10/03   Hitachi         J.Kim           Update          QC#21502
 * 2017/10/23   Fujitsu         T.Aoi           Update          QC#20719
 * 2017/11/17   Hitachi         E.Kameishi      Update          QC#19735
 * 2018/01/12   Fujitsu         T.Murai         Update          QC#21012
 * 2018/01/18   Hitachi         E.Kameishi      Update          QC#21513
 * 2018/02/08   Hitachi         E.Kameishi      Update          QC#23551
 * 2018/02/27   Fujitsu         T.Murai         Update          QC#24481
 * 2018/03/01   Fujitsu         T.Murai         Update          QC#24515
 * 2018/03/20   Hitachi         E.Kameishi      Update          QC#24915
 * 2018/04/12   Hitachi         E.Kameishi      Update          QC#25467
 * 2018/05/16   Fujitsu         H.Ikeda         Update          QC#25992
 * 2018/05/23   Hitachi         E.Kameishi      Update          QC#21841
 * 2018/05/30   Hitachi         E.Kameishi      Update          QC#26229
 * 2018/06/15   Hitachi         E.Kameishi      Update          QC#26723
 * 2018/06/27   Hitachi         E.Kameishi      Update          QC#26906
 * 2018/06/28   Hitachi         E.Kameishi      Update          QC#26900
 * 2018/07/11   Hitachi         E.Kameishi      Update          QC#27182
 * 2018/07/12   Hitachi         E.Kameishi      Update          QC#27007
 * 2018/08/21   Hitachi         E.Kameishi      Update          QC#27688
 * 2018/08/24   Hitachi         E.Kameishi      Update          QC#27848
 * 2018/08/29   Fujitsu         S.Ohki          Update          QC#27887
 * 2018/11/02   Fujitsu         T.Ogura         Update          QC#29046
 * 2019/01/21   Fujitsu         Y.Matsui        Update          QC#29961
 * 2019/03/25   Hitachi         E.Kameishi      Update          QC#30847
 * 2019/03/25   Hitachi         E.Kameishi      Update          QC#30904
 * 2019/04/09   Fujitsu         H.Ikeda         Update          QC#31077
 * 2019/06/05   Hitachi         Y.Takeno        Update          QC#50631
 * 2019/06/19   Fujitsu         H.Ikeda         Update          QC#50785
 * 2019/07/24   Hitachi         Y.Takeno        Update          QC#51934
 * 2019/08/07   Fujitsu         S.Takami        Update          QC#52447
 * 2019/08/27   Fujitsu         T.Murai         Update          QC#53032,52944
 * 2019/09/03   Fujitsu         T.Murai         Update          QC#52945
 * 2020/02/29   Fujitsu         Y.Matsui        Update          QC#55872
 * 2020/04/14   Fujitsu         H.Mizukami      Update          QC#56319,56412
 * 2020/08/25   CITS            H.Dimay         Update          QC56785
 * 2023/03/10   CITS            D.Mamaril       Update          QC#61119
 *</pre>
 */
public class NFCL3070CommonLogic {

    /**
     * existsCITicket
     * @param bizMsg NFCL3070CMsg
     * @return boolean
     */
    public static boolean existsCITicket(NFCL3070CMsg bizMsg) {
        // call EZTblAccessor
        Map<String, Object> mapRes = NFCL3070Query.getInstance().getCITicket(bizMsg);

        // has no results
        if (mapRes == null) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxInvTotAmt_CI, (BigDecimal) mapRes.get("ADJUST_AMT"));
        return true;
    }

    /**
     * equalCITicket
     * @param bizMsg NFCL3070CMsg
     * @return boolean
     */
    public static boolean equalCITicket(NFCL3070CMsg bizMsg) {
        // check equal to CITicket
        if (ZYPCommonFunc.hasValue(bizMsg.custIncdtId) && ZYPCommonFunc.hasValue(bizMsg.origInvNum)) {
            if (!bizMsg.custIncdtId.getValue().equals(bizMsg.origInvNum.getValue())) {
                return false;
            }
        }
        return true;
    }

    /**
     * setTypeOfCredit
     * @param bizMsg NFCL3070CMsg
     */
    @SuppressWarnings("unchecked")
    public static void setTypeOfCredit(NFCL3070CMsg bizMsg) {
        // call EZTblAccessor
        S21SsmEZDResult ssmResult = NFCL3070Query.getInstance().getTypeOfCredit(bizMsg);

        // has more than one results
        if (ssmResult.isCodeNormal()) {
            List<Map> resultArCrTpList = (List) ssmResult.getResultObject();
            int idx = 0;

            for (Map map : resultArCrTpList) {
                if (idx == PIDX) {
                    bizMsg.arCrTpCd.setErrorInfo(1, NZZM0001W);
                }

                if (!map.isEmpty()) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.arCrTpCd_CD.no(idx), (String) map.get("AR_CR_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.arCrTpDescTxt_DI.no(idx), (String) map.get("AR_CR_TP_DESC_TXT"));
                }
                idx++;
            }

        // has no results
        } else {
            bizMsg.arCrTpCd.setErrorInfo(1, NZZM0000E);
        }
    }

    /**
     * checkApplyOfCreditMemo
     * @param bizMsg NFCL3070CMsg
     */
    public static void checkApplyOfCreditMemo(NFCL3070CMsg bizMsg) {
        // call EZTblAccessor
        int cntApplyCrMemo = NFCL3070Query.getInstance().countAppliedCredit(bizMsg);

        // has more than one results
        if (cntApplyCrMemo > 0) {
            bizMsg.arCashApplyEligFlg.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            bizMsg.arCashApplyEligFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * isCreditAmountOverInvBalance
     * 
     * @param bizMsg NFCL3070CMsg
     * @return boolean
     */
    public static boolean isCreditAmountOverInvBalance(NFCL3070CMsg bizMsg) {
        boolean isSuccess = true;

        // AR Transaction Balance
        AR_TRX_BALTMsg arTrxBalTMsg = new AR_TRX_BALTMsg();
        arTrxBalTMsg.setSQLID("001");
        arTrxBalTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        arTrxBalTMsg.setConditionValue("arTrxNum01", bizMsg.origInvNum.getValue());

        AR_TRX_BALTMsgArray arTrxBalTMsgAry = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(arTrxBalTMsg);
        if (arTrxBalTMsgAry.length() > 0) {
            if (!isCreditAmountLessThanOrigInv(bizMsg.crRebilAmt_CO.getValue(), arTrxBalTMsgAry.no(0).dealRmngBalGrsAmt.getValue())) {
                isSuccess = false;
            }
        }

        if (!isSuccess) {
            // START 2016/06/28 S.Fujita [QC#10942,ADD]
            // if mode is the CreditOnly, crCredit Amount restore negative number
            if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y) && ZYPCommonFunc.hasValue(bizMsg.crRebilAmt_CO)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.crRebilAmt_CO, bizMsg.crRebilAmt_CO.getValue().negate());
            }
            // END   2016/06/28 S.Fujita [QC#10942,ADD]
            bizMsg.crRebilAmt_CO.setErrorInfo(1, NFCM0792E, new String[] {"Invoice balance" });
        }
        return isSuccess;
    }

    /**
     * isCalAmountUnderZero
     * 
     * @param bizMsg NFCL3070CMsg
     * @param origInvTMsg INVTMsg
     * @return boolean
     */
    public static boolean isCalAmountUnderZero(NFCL3070CMsg bizMsg, INVTMsg origInvTMsg) {
        boolean isSuccess = true;

        BigDecimal invAmt = origInvTMsg.invTotDealSlsAmt.getValue();
        BigDecimal frtAmt = origInvTMsg.invTotDealFrtAmt.getValue();
        BigDecimal taxAmt = origInvTMsg.invTotDealTaxAmt.getValue();
        BigDecimal resInvAmt = invAmt.multiply(bizMsg.arCrPct.getValue()).divide(new BigDecimal(HIDX)).setScale(bizMsg.aftDeclPntDigitNum.getValue().intValue(), HALF_UP);
        BigDecimal resFrtAmt = frtAmt.multiply(bizMsg.arCrPct.getValue()).divide(new BigDecimal(HIDX)).setScale(bizMsg.aftDeclPntDigitNum.getValue().intValue(), HALF_UP);
        BigDecimal resTaxAmt = taxAmt.multiply(bizMsg.arCrPct.getValue()).divide(new BigDecimal(HIDX)).setScale(bizMsg.aftDeclPntDigitNum.getValue().intValue(), HALF_UP);

        if (AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue())) {
            if (resInvAmt.compareTo(BigDecimal.ZERO) == 0) {
                isSuccess = false;
            }
        } else if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
            if (resFrtAmt.compareTo(BigDecimal.ZERO) == 0) {
                isSuccess = false;
            }
        } else if (AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {
            if (resTaxAmt.compareTo(BigDecimal.ZERO) == 0) {
                isSuccess = false;
            }
        }
        if (!isSuccess) {
            bizMsg.arCrPct.setErrorInfo(1, NFCM0820E);
        }
        return isSuccess;
    }

    /**
     * setOriginalInvoiceValue
     * @param bizMsg NFCL3070CMsg
     * @param origInvTMsg INVTMsg
     * @return boolean
     */
    public static boolean setOriginalInvoiceValue(NFCL3070CMsg bizMsg, INVTMsg origInvTMsg) {
        // call EZTblAccessor
        Map<String, Object> mapRes = NFCL3070Query.getInstance().getOrigInvValue(bizMsg);

        // has no results
        if (mapRes == null) {
            return false;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.dealRmngBalGrsAmt, (BigDecimal) mapRes.get("DEAL_RMNG_BAL_GRS_AMT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd, (String) mapRes.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, (String) mapRes.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd, (String) mapRes.get("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.invDt, (String) mapRes.get("INV_DT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocCd, (String) mapRes.get("SLS_REP_TOC_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.leasePrchOptCd, (String) mapRes.get("LEASE_PRCH_OPT_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsInvTpCd, (String) mapRes.get("DS_INV_TP_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.locNm, (String) mapRes.get("LOC_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsBizAreaCd, (String) mapRes.get("DS_BIZ_AREA_CD"));

        return true;
    }

    /**
     * registInvoiceTables
     * 
     * @param bizMsg NFCL3070CMsg
     * @param origInvTMsg INVTMsg
     * @return boolean
     */
    public static boolean registInvoiceTables(NFCL3070CMsg bizMsg, INVTMsg origInvTMsg) {
        
        // START 2018/05/23 E.Kameishi [QC#21841,ADD]
        String coaMdseTPCdFrt = ZYPCodeDataUtil.getVarCharConstValue("AJE_COA_MDSE_TP_FRT", bizMsg.glblCmpyCd.getValue());
        // END 2018/05/23 E.Kameishi [QC#21841,ADD]
        
        // get original INVTMsg
        INVTMsg invTMsg = NFCL3070CommonLogic.getInvTMsg(bizMsg);
        if (invTMsg == null) {
            bizMsg.setMessageInfo(ZZZM9006E, new String[] {"INV" });
            return false;
        }
        // get original DS_INV_TPTMsg
        DS_INV_TPTMsg dsInvTpTMsg = NFCL3070CommonLogic.getDsInvTpTMsg(bizMsg, invTMsg.dsInvTpCd.getValue());
        if (dsInvTpTMsg == null) {
            bizMsg.setMessageInfo(ZZZM9006E, new String[] {"DS_INV_TP" });
            return false;
        }
        // get Account Date
        String subSysCd = ZYPCodeDataUtil.getVarCharConstValue("AR_SUB_SYS_ID", bizMsg.glblCmpyCd.getValue());
        AR_ACCT_DTTMsg outArAcctDtMsg = NFCL3070CommonLogic.findArAcctDtInfo(bizMsg, subSysCd);
        if (outArAcctDtMsg == null) {
            bizMsg.setMessageInfo(ZZZM9006E, new String[] {"AR_ACCT_DT" });
            return false;
        }
        // get CopyTo Invoice Number
        // START 2017/11/17 E.Kameishi [QC#19735, MOD]
        //String invNum = ZYPExtnNumbering.getUniqueID(bizMsg.glblCmpyCd.getValue(), dsInvTpTMsg.autoSeqCd.getValue());
        //String invNum = ZYPMaxTenDigitsNumbering.getUniqueID(bizMsg.glblCmpyCd.getValue(), dsInvTpTMsg.autoSeqCd.getValue());
        String manAutoSeqCd = ZYPCodeDataUtil.getVarCharConstValue("NFCL3000_AUTO_SEQ_CD", bizMsg.glblCmpyCd.getValue());
        String invNum = "";
        if (ZYPCommonFunc.hasValue(manAutoSeqCd)) {
            invNum = ZYPMaxTenDigitsNumbering.getUniqueID(bizMsg.glblCmpyCd.getValue(), manAutoSeqCd);
        }
        if (!ZYPCommonFunc.hasValue(invNum)) {
            return false;
        }
        // set invNum to Screen Fields
        if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invNum_C, invNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.crDsInvTpCd, dsInvTpTMsg.crDsInvTpCd);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.invNum_R, invNum);
        }

        // START 2016/04/13 S.Fujita [S21 NA Unit Test QC#6565,MOD]
        // set System Source Code
        ZYPEZDItemValueSetter.setValue(bizMsg.sysSrcCd, invTMsg.sysSrcCd);
        // END 2016/04/13 S.Fujita [S21 NA Unit Test QC#6565,MOD]
        // get INV_BOLTMsg
        INV_BOLTMsg invBolTMsg = NFCL3070CommonLogic.getInvBolTMsg(bizMsg);
        // get INV_LINETMsg
        INV_LINETMsg invLineTMsg = NFCL3070CommonLogic.getInvLineTMsg(bizMsg);
        // calculate percent
        BigDecimal calPct = calculatePct(bizMsg, invLineTMsg, invTMsg, invBolTMsg);
        // START 2019/08/30 [QC#52945,ADD]
        if (BigDecimal.ZERO.compareTo(calPct) == 0) {
            if (AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {
                bizMsg.crRebilAmt_CO.setErrorInfo(1, NFCM0792E, new String[] {"Invoice Tax" });
                bizMsg.setMessageInfo(ZZM9037E);
                return false;
            } else if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
                bizMsg.crRebilAmt_CO.setErrorInfo(1, NFCM0792E, new String[] {"Invoice Freight" });
                bizMsg.setMessageInfo(ZZM9037E);
                return false;
            } else if (AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue())) {
                bizMsg.crRebilAmt_CO.setErrorInfo(1, NFCM0792E, new String[] {"Invoice Sales" });
                bizMsg.setMessageInfo(ZZM9037E);
                return false;
            }
        }
        // END 2019/08/30 [QC#52945,ADD]
        if (bizMsg.xxTotInvPct.getValue().compareTo(BigDecimal.ZERO) == 0) {
            bizMsg.crRebilAmt_CO.setErrorInfo(1, NFCM0820E);
            return false;
        }

        // START 2020/02/29 [QC#55872,ADD]
        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 || RQST_TP_BOTH.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
            if (!NFCL3070CommonLogic.checkArCrRebilValue(bizMsg, origInvTMsg)) {
                bizMsg.invNum_C.clear();
                bizMsg.setMessageInfo(NFCM0886E);
                return false;
            }
        }
        // END 2020/02/29 [QC#55872,ADD]
        // START 2020/04/14 [QC#56319,56412,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.invDt, origInvTMsg.invDt);
        String dueDt = origInvTMsg.netDueDt.getValue();
        if (AR_CR_REBIL_TP.CREDIT_ONLY.equals(bizMsg.arCrRebilTpCd.getValue())) {
            dueDt = origInvTMsg.invDt.getValue();
        } else if (AR_CR_REBIL_TP.REBILL_ONLY.equals(bizMsg.arCrRebilTpCd.getValue())) {
            dueDt = origInvTMsg.netDueDt.getValue();
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPgFlg_C.getValue())) {
                dueDt = origInvTMsg.invDt.getValue();
            } else {
                dueDt = origInvTMsg.netDueDt.getValue();
            }
        }
        // END   2020/04/14 [QC#56319,56412,ADD]

        // create insInvLineTMsgList
        List<INV_LINETMsg> insInvLineTMsgList = new ArrayList<INV_LINETMsg>();
        // create insInvBolTMsgList
        List<INV_BOLTMsg> insInvBolTMsgList = new ArrayList<INV_BOLTMsg>();
        // create dsInvLineTaxDtlTMsgList
        List<DS_INV_LINE_TAX_DTLTMsg> dsInvLineTaxDtlTMsgList = new ArrayList<DS_INV_LINE_TAX_DTLTMsg>();

        // START 2016/10/07 S.Fujita [QC#10522,MOD]

        // get INV_BOLTMsgArray
        INV_BOLTMsgArray invBolTMsgAry = NFCL3070CommonLogic.getInvBolTMsgAry(bizMsg);
        // get INV_LINETMsgArray
        INV_LINETMsgArray invLineTMsgAry = NFCL3070CommonLogic.getInvLineTMsgAry(bizMsg);

        // set Tax by TaxCalculationAPI
        if (!taxCalc(bizMsg, invNum, insInvLineTMsgList, invLineTMsg, invLineTMsgAry, insInvBolTMsgList, invBolTMsg, invBolTMsgAry, dsInvTpTMsg, dsInvLineTaxDtlTMsgList, calPct, coaMdseTPCdFrt)) {
            return false;
        }

        // END   2016/10/07 S.Fujita [QC#10522,MOD]

        // ##### INV_LINE #####
        // TargetLineLevel
        if (bizMsg.xxPgFlg_T.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            if (!insertInvLineByKey(bizMsg, insInvLineTMsgList, invLineTMsg, coaMdseTPCdFrt)) {
                return false;
            }
        // not TargetLineLevel
        } else {
            if (!insertInvLineByCondition(bizMsg, insInvLineTMsgList, invLineTMsgAry, calPct, coaMdseTPCdFrt)) {
                return false;
            }
        }
        // ##### INV_BOL #####
        // TargetLineLevel
        if (ZYPCommonFunc.hasValue(bizMsg.invBolLineNum)) {
            if (!insertInvBolByKey(bizMsg, invNum, insInvBolTMsgList, invBolTMsg)) { // ADD 2017/01/04 T.Murai [QC#16842] invBolTMsg
                return false;
            }
        // not TargetLineLevel
        } else {
            if (!insertInvBolByCondition(bizMsg, invNum, insInvBolTMsgList, invBolTMsgAry, calPct)) {
                return false;
            }
        }
        // ##### INV #####
        // START 2020/04/14 [QC#56412,ADD]
        //INVTMsg copyToInvTMsg = NFCL3070CommonLogic.insertInv(bizMsg, origInvTMsg, invNum, subSysCd, outArAcctDtMsg.acctDt.getValue());
        INVTMsg copyToInvTMsg = NFCL3070CommonLogic.insertInv(bizMsg, origInvTMsg, invNum, subSysCd, outArAcctDtMsg.acctDt.getValue(), dueDt);
        // END   2020/04/14 [QC#56412,ADD]
        if (copyToInvTMsg == null) {
            return false;
        }
        // ##### INV_PRMO_INFO #####
        if (!insertInvPrmoInfo(bizMsg, invNum)) {
            return false;
        }
        // create slsCrPkMap
        Map<BigDecimal, BigDecimal> slsCrPkMap = new HashMap<BigDecimal, BigDecimal>();
        // START 2018/05/30 E.Kameishi [QC#26229,MOD]
        Map<BigDecimal, String> dfrdSlsCrPkMap = new HashMap<BigDecimal, String>();
        // ##### DS_INV_SLS_CR #####
        if (!insertDsInvSlsCr(bizMsg, invNum, calPct, slsCrPkMap, coaMdseTPCdFrt, dfrdSlsCrPkMap)) {
            return false;
        }
        // ##### AJE_INV_ACCT_DIST #####
        if (!insertAjeInvAcctDist(bizMsg, invNum, outArAcctDtMsg.acctDt.getValue(), calPct, slsCrPkMap, dfrdSlsCrPkMap)) {
            return false;
        }
        // END 2018/05/30 E.Kameishi [QC#26229,MOD]
        // ##### DS_INV_LINE_MTR #####
        // START 2016/01/11 S.Fujita [QC#17079,MOD]
//        if (RQST_TP_REBILL_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
        if (RQST_TP_REBILL_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 || (RQST_TP_BOTH.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && ZYPConstant.FLG_OFF_N.equals(bizMsg.xxPgFlg_C.getValue()))) {
         // END   2016/01/11 S.Fujita [QC#17079,MOD]
            if (!insertMeterInvoice(bizMsg, invNum)) {
                return false;
            }
        }
        // ##### DS_INV_LINE_TAX_DTL #####
        if (!insertDsInvLineTaxDtl(bizMsg, dsInvLineTaxDtlTMsgList)) {
            return false;
        }
        // ##### AR_CR_REBILL #####
        // START 2018/08/29 S.Ohki [QC#27887, MOD]
//        if (!NFCL3070CommonLogic.insertArCrRebill(bizMsg, origInvTMsg, copyToInvTMsg)) {
        if (!NFCL3070CommonLogic.insertArCrRebill(bizMsg, origInvTMsg, copyToInvTMsg, invLineTMsg)) {
        // END 2018/08/29 S.Ohki [QC#27887, MOD]
            return false;
        }
        return true;
    }

    // START 2020/04/14 [QC#56412,MOD]
    /**
     * insertInv
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invTMsg INVTMsg
     * @param invNum String
     * @param subSysCd String
     * @param acctDt String
     * @param dueDt String
     * @return INVTMsg
     */
    //public static INVTMsg insertInv(NFCL3070CMsg bizMsg, INVTMsg invTMsg, String invNum, String subSysCd, String acctDt) {
    public static INVTMsg insertInv(NFCL3070CMsg bizMsg, INVTMsg invTMsg, String invNum, String subSysCd, String acctDt, String dueDt) {
    // END   2020/04/14 [QC#56412,MOD]
        INVTMsg insInvTMsg = new INVTMsg();
        EZDTMsg.copy(invTMsg, null, insInvTMsg, null);

        // get INV_BOL_AMT
        Map<String, Object> mapRes = NFCL3070Query.getInstance().getInvBolValue(bizMsg, invNum);
        // has no results
        if (mapRes == null) {
            return null;
        }

        ZYPEZDItemValueSetter.setValue(insInvTMsg.invNum, invNum);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.origInvNum, bizMsg.origInvNum);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.acctDt, acctDt);
        // START 2020/04/14 [QC#56412,MOD]
        //ZYPEZDItemValueSetter.setValue(insInvTMsg.netDueDt, acctDt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.netDueDt, dueDt);
        // END   2020/04/14 [QC#56412,MOD]
        ZYPEZDItemValueSetter.setValue(insInvTMsg.sysSrcCd, subSysCd);
        insInvTMsg.grpInvNum.clear();
        BigDecimal invTotDealSlsAmt = (BigDecimal) mapRes.get("SHIP_DEAL_SLS_AMT");
        BigDecimal invTotDealFrtAmt = (BigDecimal) mapRes.get("SHIP_DEAL_FRT_AMT");
        BigDecimal invTotDealTaxAmt = (BigDecimal) mapRes.get("TOT_BOL_DEAL_TAX_AMT");
        BigDecimal invTotDealDiscAmt = (BigDecimal) mapRes.get("SHIP_DEAL_DISC_AMT");
        BigDecimal shipDealNetAmt   = (BigDecimal) mapRes.get("SHIP_DEAL_NET_AMT");
        BigDecimal invTotDealNetAmt = invTotDealSlsAmt.add(invTotDealFrtAmt).add(invTotDealTaxAmt).add(invTotDealDiscAmt);
        BigDecimal invTotFuncSlsAmt = (BigDecimal) mapRes.get("SHIP_FUNC_SLS_AMT");
        BigDecimal invTotFuncFrtAmt = (BigDecimal) mapRes.get("SHIP_FUNC_FRT_AMT");
        BigDecimal invTotFuncTaxAmt = (BigDecimal) mapRes.get("TOT_BOL_FUNC_TAX_AMT");
        BigDecimal invTotFuncDiscAmt = (BigDecimal) mapRes.get("SHIP_FUNC_DISC_AMT");
        BigDecimal shipFuncNetAmt   = (BigDecimal) mapRes.get("SHIP_FUNC_NET_AMT");
        BigDecimal invTotFuncNetAmt = invTotFuncSlsAmt.add(invTotFuncFrtAmt).add(invTotFuncTaxAmt).add(invTotFuncDiscAmt);

        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotDealNetAmt, invTotDealNetAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotDealSlsAmt, invTotDealSlsAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotDealFrtAmt, invTotDealFrtAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotDealTaxAmt, invTotDealTaxAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotDealDiscAmt, invTotDealDiscAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotFuncNetAmt, invTotFuncNetAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotFuncSlsAmt, invTotFuncSlsAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotFuncFrtAmt, invTotFuncFrtAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotFuncTaxAmt, invTotFuncTaxAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotFuncDiscAmt, invTotFuncDiscAmt);

        if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            ZYPEZDItemValueSetter.setValue(insInvTMsg.invTpCd, INV_TP.CREDIT_MEMO);
            ZYPEZDItemValueSetter.setValue(insInvTMsg.crInvTpCd, CR_INV_TP.CREDIT_MEMO);
            // START 2018/08/21 E.Kameishi [QC#27688,MOD]
            //if ((RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && (hasValue(bizMsg.arAutoCashAppFlg) && ZYPConstant.FLG_ON_Y.equals(bizMsg.arAutoCashAppFlg.getValue())))
            //        || RQST_TP_BOTH.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(insInvTMsg.fnlzInvFlg, ZYPConstant.FLG_ON_Y);
            //} else {
            //    ZYPEZDItemValueSetter.setValue(insInvTMsg.fnlzInvFlg, ZYPConstant.FLG_OFF_N);
            //}
            // END 2018/08/21 E.Kameishi [QC#27688,MOD]
            //ZYPEZDItemValueSetter.setValue(insInvTMsg.invPrintStsCd, ZYPConstant.FLG_OFF_0);
            ZYPEZDItemValueSetter.setValue(insInvTMsg.invPrintStsCd, ZYPConstant.FLG_ON_1);
            // START 2016/07/07 S.Fujita [QC#10458,ADD]
            setPmtTermCashDisc(bizMsg, insInvTMsg);
            // END   2016/07/07 S.Fujita [QC#10458,ADD]
            // START 2018/01/12 T.Murai [QC#21012,ADD]
            ZYPEZDItemValueSetter.setValue(insInvTMsg.dsInvTpCd, bizMsg.crDsInvTpCd);
            // END   2018/01/12 T.Murai [QC#21012,ADD]
            // START 2018/05/16 H.Ikeda [QC#25992,ADD]
            if (ZYPCommonFunc.hasValue(bizMsg.arAutoCashAppFlg) && bizMsg.arAutoCashAppFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                ZYPEZDItemValueSetter.setValue(insInvTMsg.arAutoPurgeOfsFlg, ZYPConstant.FLG_ON_Y);
            }
            // END   2018/05/16 H.Ikeda [QC#25992,ADD]
        } else {
            ZYPEZDItemValueSetter.setValue(insInvTMsg.invTpCd, INV_TP.INVOICE);
            insInvTMsg.crInvTpCd.clear();
            ZYPEZDItemValueSetter.setValue(insInvTMsg.fnlzInvFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(insInvTMsg.invPrintStsCd, ZYPConstant.FLG_ON_1);
            //ZYPEZDItemValueSetter.setValue(insInvTMsg.invPrintStsCd, ZYPConstant.FLG_OFF_0);
        }

        ZYPEZDItemValueSetter.setValue(insInvTMsg.custCareTktNum, bizMsg.custIncdtId);
        insInvTMsg.invPrintCratStsCd.clear();

        // insert
        S21FastTBLAccessor.insert(insInvTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insInvTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV" });
            return null;
        }
        return insInvTMsg;
    }

    /**
     * insertInvBolByKey
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @param insInvBolTMsgList List<INV_BOLTMsg>
     * @param invBolTMsg INV_BOLTMsg
     * @return boolean
     */
    public static boolean insertInvBolByKey(NFCL3070CMsg bizMsg, String invNum, List<INV_BOLTMsg> insInvBolTMsgList, //
            INV_BOLTMsg invBolTMsg) {// ADD 2017/01/04 T.Murai [QC#16842] INV_BOLTMsg invBolTMsg

        // START 2016/10/07 S.Fujita [QC#10522,DEL]
//        // get original TMsg
//        INV_BOLTMsg tMsg = new INV_BOLTMsg();
//        tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//        tMsg.invNum.setValue(bizMsg.origInvNum.getValue());
//        tMsg.invBolLineNum.setValue(bizMsg.invBolLineNum.getValue());
//
//        INV_BOLTMsg invBolTMsg = (INV_BOLTMsg) S21CacheTBLAccessor.findByKey(tMsg);
//        if (invBolTMsg == null) {
//            bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_BOL" });
//            return false;
//        }
//        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, invBolTMsg.shipToCustCd);
//        ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm, invBolTMsg.shipToLocNm);
//
//        // get INV_LINE_AMT
//        S21SsmEZDResult ssmRes = NFCL3070Query.getInstance().getInvLineValue(bizMsg, invNum);
//        // has no results
//        if (!ssmRes.isCodeNormal()) {
//            return false;
//        }
//        @SuppressWarnings("unchecked")
//        List<Map> result = (List) ssmRes.getResultObject();
//        // add one line to TMsgList
//        insInvBolTMsgList.add(invBolTMsg);
//        ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).invNum, invNum);
//
//        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
//            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipDealSlsAmt, (BigDecimal) result.get(0).get("DEAL_GRS_TOT_PRC_AMT"));
//            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipDealNetAmt, (BigDecimal) result.get(0).get("INV_LINE_DEAL_NET_AMT"));
//            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipFuncSlsAmt, (BigDecimal) result.get(0).get("FUNC_GRS_TOT_PRC_AMT"));
//            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipFuncNetAmt, (BigDecimal) result.get(0).get("INV_LINE_FUNC_NET_AMT"));
//            // calculate amount
//            BigDecimal shipDealDiscCalAmt = calculateAmount(bizMsg, invBolTMsg.shipDealDiscAmt.getValue(), calPct);
//            BigDecimal shipDealHdlgChrgCalAmt = calculateAmount(bizMsg, invBolTMsg.shipDealHdlgChrgAmt.getValue(), calPct);
//            BigDecimal shipFuncDiscCalAmt = calculateAmount(bizMsg, invBolTMsg.shipFuncDiscAmt.getValue(), calPct);
//            BigDecimal shipFuncHdlgChrgCalAmt = calculateAmount(bizMsg, invBolTMsg.shipFuncHdlgChrgAmt.getValue(), calPct);
//            // calculate amount to TMsg
//            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipDealDiscAmt, shipDealDiscCalAmt);
//            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipDealHdlgChrgAmt, shipDealHdlgChrgCalAmt);
//            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipFuncDiscAmt, shipFuncDiscCalAmt);
//            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipFuncHdlgChrgAmt, shipFuncHdlgChrgCalAmt);
//
//            if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
//                // calculate amount
//                BigDecimal shipDealFrtCalAmt = calculateAmount(bizMsg, invBolTMsg.shipDealFrtAmt.getValue(), calPct);
//                BigDecimal shipFuncFrtCalAmt = calculateAmount(bizMsg, invBolTMsg.shipFuncFrtAmt.getValue(), calPct);
//                // calculate amount to TMsg
//                ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipDealFrtAmt, shipDealFrtCalAmt);
//                ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipFuncFrtAmt, shipFuncFrtCalAmt);
//            } else {
//                ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipDealFrtAmt, BigDecimal.ZERO);
//                ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipFuncFrtAmt, BigDecimal.ZERO);
//            }
//        }
//        // get ShipToCustAcctCd
//        String shipToCustAcctCd = getShipToCustAcctCd(bizMsg, invBolTMsg.invBolLineNum.getValue());
//        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, shipToCustAcctCd);
//        // call TaxCalculation API 
//        if (!setTaxByTaxCalculationAPI(bizMsg, invNum, null, insInvBolTMsgList.get(0), invBolTMsg, dsInvTpTMsg, dsInvLineTaxDtlTMsgList)) {
//            return false;
//        }
        // END   2016/10/07 S.Fujita [QC#10522,DEL]

        // get INV_LINE_AMT
        S21SsmEZDResult ssmRes = NFCL3070Query.getInstance().getInvLineValue(bizMsg, invNum);
        // has no results
        if (!ssmRes.isCodeNormal()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        List<Map> result = (List) ssmRes.getResultObject();

        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
          ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipDealSlsAmt, (BigDecimal) result.get(0).get("DEAL_GRS_TOT_PRC_AMT"));
          ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipDealNetAmt, (BigDecimal) result.get(0).get("INV_LINE_DEAL_NET_AMT"));
          ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipFuncSlsAmt, (BigDecimal) result.get(0).get("FUNC_GRS_TOT_PRC_AMT"));
          ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipFuncNetAmt, (BigDecimal) result.get(0).get("INV_LINE_FUNC_NET_AMT"));
        }
        // START 2018/07/12 E.Kameishi [QC#27007,ADD]
        ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipDealDiscAmt, (BigDecimal) result.get(0).get("DEAL_DISC_TOT_PRC_AMT"));
        ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipFuncDiscAmt, (BigDecimal) result.get(0).get("FUNC_DISC_TOT_PRC_AMT"));
        // END 2018/07/12 E.Kameishi [QC#27007,ADD]
        // START 2018/03/20 E.Kameishi [QC#24915,ADD]
        // set SHIP_DEAL_DISC_AMT/SHIP_FUNC_DISC_AMT to Zero if Type of Credit is Tax or Freight
        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
            if (AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue()) || AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipDealDiscAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipFuncDiscAmt, BigDecimal.ZERO);
            }
        }
        // END 2018/03/20 E.Kameishi [QC#24915,ADD]
        // set Total Tax Amount
        BigDecimal totBolDealTaxAmt = (BigDecimal) result.get(0).get("INV_LINE_DEAL_TAX_AMT");
        BigDecimal totBolFuncTaxAmt = (BigDecimal) result.get(0).get("INV_LINE_FUNC_TAX_AMT");
        // START 2018/03/20 E.Kameishi [QC#24915,MOD]
        //ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).totBolDealTaxAmt, totBolDealTaxAmt.add(insInvBolTMsgList.get(0).frtDealTaxAmt.getValue()));
        //ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).totBolFuncTaxAmt, totBolFuncTaxAmt.add(insInvBolTMsgList.get(0).frtFuncTaxAmt.getValue()));
        ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).totBolDealTaxAmt, totBolDealTaxAmt);
        ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).totBolFuncTaxAmt, totBolFuncTaxAmt);
        // END 2018/03/20 E.Kameishi [QC#24915,MOD]

        // START 2018/06/28 E.Kameishi [QC#26900,MOD]
        // START 2017/01/04 T.Murai [QC#16842,ADD]
        // check percent When CreditType:Freight
        //if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
        //    bizMsg.actvFlg_E.clear();
        //    BigDecimal frtTaxPct = invBolTMsg.frtTaxPct.getValue();
        //    if (insInvBolTMsgList.get(0).frtTaxPct.getValue().compareTo(frtTaxPct) != 0) {
        //        bizMsg.actvFlg_E.setValue(ZYPConstant.FLG_ON_Y);
        //    }
        //}
        // END   2017/01/04 T.Murai [QC#16842,ADD]
        // END 2018/06/28 E.Kameishi [QC#26900,MOD]

        // START 2019/09/04 [QC#52945,ADD]
        ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).frtDealTaxAmt, (BigDecimal) result.get(0).get("FRT_DEAL_TAX_AMT"));
        ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).frtFuncTaxAmt, (BigDecimal) result.get(0).get("FRT_FUNC_TAX_AMT"));
        ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).invBolLineNum, INIT_INV_BOL_LINE_NUM);
        // END 2019/09/04 [QC#52945,ADD]

        // insert
        S21FastTBLAccessor.insert(insInvBolTMsgList.get(0));
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insInvBolTMsgList.get(0).getReturnCode())) {
            bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_BOL" });
            return false;
        }
        return true;
    }

    /**
     * insertInvBolByCondition
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @param insInvBolTMsgList List<INV_BOLTMsg>
     * @param invBolTMsgAry INV_BOLTMsgArray
     * @param calPct BigDecimal
     * @return boolean
     */
    public static boolean insertInvBolByCondition(NFCL3070CMsg bizMsg, String invNum, List<INV_BOLTMsg> insInvBolTMsgList, INV_BOLTMsgArray invBolTMsgAry, BigDecimal calPct) {

        // START 2016/10/07 S.Fujita [QC#10522,DEL]
        // get original TMsg Array
//        INV_BOLTMsg tMsg = new INV_BOLTMsg();
//        tMsg.setSQLID("001");
//        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        tMsg.setConditionValue("invNum01", bizMsg.origInvNum.getValue());
//
//        INV_BOLTMsgArray invBolTMsgAry = (INV_BOLTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
//        if (invBolTMsgAry.length() == 0) {
//            bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_BOL" });
//            return false;
//        }
//        // initialize amounts
//        BigDecimal shipDealDiscTotAmt = BigDecimal.ZERO;
//        BigDecimal shipDealDiscCalTotAmt = BigDecimal.ZERO;
//        BigDecimal shipDealHdlgChrTotgAmt = BigDecimal.ZERO;
//        BigDecimal shipDealHdlgChrCalTotgAmt = BigDecimal.ZERO;
//        BigDecimal shipFuncDiscTotAmt = BigDecimal.ZERO;
//        BigDecimal shipFuncDiscCalTotAmt = BigDecimal.ZERO;
//        BigDecimal shipFuncHdlgChrgTotAmt = BigDecimal.ZERO;
//        BigDecimal shipFuncHdlgChrgCalTotAmt = BigDecimal.ZERO;
//
//        BigDecimal shipDealFrtTotAmt = BigDecimal.ZERO;
//        BigDecimal shipDealFrtCaltotAmt = BigDecimal.ZERO;
//        BigDecimal shipFuncFrtTotAmt = BigDecimal.ZERO;
//        BigDecimal shipFuncFrtCalTotAmt = BigDecimal.ZERO;
//
//        BigDecimal frtDealTaxCalAmt = BigDecimal.ZERO;
//        BigDecimal frtFuncTaxCalAmt = BigDecimal.ZERO;
//        BigDecimal frtDealTaxTotAmt = BigDecimal.ZERO;
//        BigDecimal frtDealTaxCalTotAmt = BigDecimal.ZERO;
//        BigDecimal frtFuncTaxTotAmt = BigDecimal.ZERO;
//        BigDecimal frtFuncTaxCalTotAmt = BigDecimal.ZERO;
//        // get INV_LINE_AMT
//        S21SsmEZDResult ssmRes = NFCL3070Query.getInstance().getInvLineValue(bizMsg, invNum);
//        // has no results
//        if (!ssmRes.isCodeNormal()) {
//            return false;
//        }
//        @SuppressWarnings("unchecked")
//        List<Map> result = (List) ssmRes.getResultObject();
//        // set original TMsg Array to copy to TMsg
//        for (int i = 0; i < invBolTMsgAry.getValidCount(); i++) {
//            INV_BOLTMsg insInvBolTMsg = new INV_BOLTMsg();
//
//            EZDTMsg.copy(invBolTMsgAry.get(i), null, insInvBolTMsg, null);
//            ZYPEZDItemValueSetter.setValue(insInvBolTMsg.invNum, invNum);
//
//            if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
//                // set Shipped Amount
//                ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipDealSlsAmt, (BigDecimal) result.get(i).get("DEAL_GRS_TOT_PRC_AMT"));
//                ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipDealNetAmt, (BigDecimal) result.get(i).get("INV_LINE_DEAL_NET_AMT"));
//                ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipFuncSlsAmt, (BigDecimal) result.get(i).get("FUNC_GRS_TOT_PRC_AMT"));
//                ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipFuncNetAmt, (BigDecimal) result.get(i).get("INV_LINE_FUNC_NET_AMT"));
//                // calculate amount
//                BigDecimal shipDealDiscCalAmt = calculateAmount(bizMsg, invBolTMsgAry.no(i).shipDealDiscAmt.getValue(), calPct);
//                BigDecimal shipDealHdlgChrgCalAmt = calculateAmount(bizMsg, invBolTMsgAry.no(i).shipDealHdlgChrgAmt.getValue(), calPct);
//                BigDecimal shipFuncDiscCalAmt = calculateAmount(bizMsg, invBolTMsgAry.no(i).shipFuncDiscAmt.getValue(), calPct);
//                BigDecimal shipFuncHdlgChrgCalAmt = calculateAmount(bizMsg, invBolTMsgAry.no(i).shipFuncHdlgChrgAmt.getValue(), calPct);
//                // calculate amount to TMsg
//                ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipDealDiscAmt, shipDealDiscCalAmt);
//                ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipDealHdlgChrgAmt, shipDealHdlgChrgCalAmt);
//                ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipFuncDiscAmt, shipFuncDiscCalAmt);
//                ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipFuncHdlgChrgAmt, shipFuncHdlgChrgCalAmt);
//                // set original amount and calculated amount
//                shipDealDiscTotAmt = shipDealDiscTotAmt.add(addAmount(invBolTMsgAry.no(i).shipDealDiscAmt.getValue()));
//                shipDealDiscCalTotAmt = shipDealDiscCalTotAmt.add(shipDealDiscCalAmt);
//                shipDealHdlgChrTotgAmt = shipDealHdlgChrTotgAmt.add(addAmount(invBolTMsgAry.no(i).shipDealHdlgChrgAmt.getValue()));
//                shipDealHdlgChrCalTotgAmt = shipDealHdlgChrCalTotgAmt.add(shipDealHdlgChrgCalAmt);
//                shipFuncDiscTotAmt = shipFuncDiscTotAmt.add(addAmount(invBolTMsgAry.no(i).shipFuncDiscAmt.getValue()));
//                shipFuncDiscCalTotAmt = shipFuncDiscCalTotAmt.add(shipFuncDiscCalAmt);
//                shipFuncHdlgChrgTotAmt = shipFuncHdlgChrgTotAmt.add(addAmount(invBolTMsgAry.no(i).shipFuncHdlgChrgAmt.getValue()));
//                shipFuncHdlgChrgCalTotAmt = shipFuncHdlgChrgCalTotAmt.add(shipFuncHdlgChrgCalAmt);
//
//                if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
//                    // calculate amount
//                    BigDecimal shipDealFrtCalAmt = calculateAmount(bizMsg, invBolTMsgAry.no(i).shipDealFrtAmt.getValue(), calPct);
//                    BigDecimal shipFuncFrtCalAmt = calculateAmount(bizMsg, invBolTMsgAry.no(i).shipFuncFrtAmt.getValue(), calPct);
//                    // calculate amount to TMsg
//                    ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipDealFrtAmt, shipDealFrtCalAmt);
//                    ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipFuncFrtAmt, shipFuncFrtCalAmt);
//                    // set original amount and calculated amount
//                    shipDealFrtTotAmt = shipDealFrtTotAmt.add(addAmount(invBolTMsgAry.no(i).shipDealFrtAmt.getValue()));
//                    shipDealFrtCaltotAmt = shipDealFrtCaltotAmt.add(shipDealFrtCalAmt);
//                    shipFuncFrtTotAmt = shipFuncFrtTotAmt.add(addAmount(invBolTMsgAry.no(i).shipFuncFrtAmt.getValue()));
//                    shipFuncFrtCalTotAmt = shipFuncFrtCalTotAmt.add(shipFuncFrtCalAmt);
//
//                } else {
//                    ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipDealFrtAmt, BigDecimal.ZERO);
//                    ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipFuncFrtAmt, BigDecimal.ZERO);
//                }
//            }
//            // get ShipToCustAcctCd
//            String shipToCustAcctCd = getShipToCustAcctCd(bizMsg, invBolTMsgAry.no(i).invBolLineNum.getValue());
//            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, shipToCustAcctCd);
//            // call TaxCalculation API 
//            if (!setTaxByTaxCalculationAPI(bizMsg, invNum, null, insInvBolTMsg, invBolTMsgAry.no(i), dsInvTpTMsg, dsInvLineTaxDtlTMsgList)) {
//                return false;
//            }
            // END   2016/10/07 S.Fujita [QC#10522,DEL]

          // initialize amounts
          // START 2018/06/28 E.Kameishi [QC#26900,DEL]
          //BigDecimal frtDealTaxCalAmt = BigDecimal.ZERO;
          //BigDecimal frtFuncTaxCalAmt = BigDecimal.ZERO;
          //BigDecimal frtDealTaxTotAmt = BigDecimal.ZERO;
          //BigDecimal frtDealTaxCalTotAmt = BigDecimal.ZERO;
          //BigDecimal frtFuncTaxTotAmt = BigDecimal.ZERO;
          //BigDecimal frtFuncTaxCalTotAmt = BigDecimal.ZERO;
          // END 2018/06/28 E.Kameishi [QC#26900,DEL]

          // get INV_LINE_AMT
          S21SsmEZDResult ssmRes = NFCL3070Query.getInstance().getInvLineValue(bizMsg, invNum);
          // has no results
          if (!ssmRes.isCodeNormal()) {
              return false;
          }
          @SuppressWarnings("unchecked")
          List<Map> result = (List) ssmRes.getResultObject();

          int cnt = 0; // ADD 2017/01/04 T.Murai [QC#16842]
          for (int i = 0; i < invBolTMsgAry.getValidCount(); i++) {

              if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
                  // set Shipped Amount
                  ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).shipDealSlsAmt, (BigDecimal) result.get(i).get("DEAL_GRS_TOT_PRC_AMT"));
                  ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).shipDealNetAmt, (BigDecimal) result.get(i).get("INV_LINE_DEAL_NET_AMT"));
                  ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).shipFuncSlsAmt, (BigDecimal) result.get(i).get("FUNC_GRS_TOT_PRC_AMT"));
                  ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).shipFuncNetAmt, (BigDecimal) result.get(i).get("INV_LINE_FUNC_NET_AMT"));
              }

              // set Total Tax Amount
              BigDecimal totBolDealTaxAmt = (BigDecimal) result.get(i).get("INV_LINE_DEAL_TAX_AMT");
              BigDecimal totBolFuncTaxAmt = (BigDecimal) result.get(i).get("INV_LINE_FUNC_TAX_AMT");
              // START 2018/03/20 E.Kameishi [QC#24915,MOD]
              //ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).totBolDealTaxAmt, totBolDealTaxAmt.add(insInvBolTMsgList.get(i).frtDealTaxAmt.getValue()));
              //ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).totBolFuncTaxAmt, totBolFuncTaxAmt.add(insInvBolTMsgList.get(i).frtFuncTaxAmt.getValue()));
              ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).totBolDealTaxAmt, totBolDealTaxAmt);
              ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).totBolFuncTaxAmt, totBolFuncTaxAmt);
              // END 2018/03/20 E.Kameishi [QC#24915,MOD]
              // START 2018/06/28 E.Kameishi [QC#26900,MOD]
              //if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
                  // set original tax amount and calculated tax amount
                  //frtDealTaxCalAmt = addAmount(invBolTMsgAry.no(i).frtDealTaxAmt.getValue()).multiply(insInvBolTMsgList.get(i).frtTaxPct.getValue()).divide(new BigDecimal(HIDX)).setScale(bizMsg.aftDeclPntDigitNum.getValue().intValue(), HALF_UP);
                  //frtDealTaxTotAmt = frtDealTaxTotAmt.add(frtDealTaxCalAmt);
                  //frtDealTaxCalTotAmt = frtDealTaxCalTotAmt.add(insInvBolTMsgList.get(i).frtDealTaxAmt.getValue());
                  //frtFuncTaxCalAmt = addAmount(invBolTMsgAry.no(i).frtFuncTaxAmt.getValue()).multiply(insInvBolTMsgList.get(i).frtTaxPct.getValue()).divide(new BigDecimal(HIDX)).setScale(bizMsg.aftDeclPntDigitNum.getValue().intValue(), HALF_UP);
                  //frtFuncTaxTotAmt = frtFuncTaxTotAmt.add(frtFuncTaxCalAmt);
                  //frtFuncTaxCalTotAmt = frtFuncTaxCalTotAmt.add(insInvBolTMsgList.get(i).frtFuncTaxAmt.getValue());
              //}
              ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).frtDealTaxAmt, (BigDecimal) result.get(i).get("FRT_DEAL_TAX_AMT"));
              ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).frtFuncTaxAmt, (BigDecimal) result.get(i).get("FRT_FUNC_TAX_AMT"));
              // START 2018/07/12 E.Kameishi [QC#27007,ADD]
              ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).shipDealDiscAmt, (BigDecimal) result.get(i).get("DEAL_DISC_TOT_PRC_AMT"));
              ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).shipFuncDiscAmt, (BigDecimal) result.get(i).get("FUNC_DISC_TOT_PRC_AMT"));
              // END 2018/07/12 E.Kameishi [QC#27007,ADD]

              // START 2017/01/04 T.Murai [QC#16842,ADD]
              // check percent When CreditType:Freight
              //if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue()) && cnt == 0) {
              //    bizMsg.actvFlg_E.clear();
              //    BigDecimal frtTaxPct = invBolTMsgAry.no(i).frtTaxPct.getValue();
              //    if (insInvBolTMsgList.get(i).frtTaxPct.getValue().compareTo(frtTaxPct) != 0) {
              //        bizMsg.actvFlg_E.setValue(ZYPConstant.FLG_ON_Y);
              //        cnt++;
              //    }
              //}
              // END   2017/01/04 T.Murai [QC#16842,ADD]
              // END 2018/06/28 E.Kameishi [QC#26900,MOD]
              // START 2018/04/12 E.Kameishi [QC#25467,ADD]
              // set SHIP_DEAL_DISC_AMT/SHIP_FUNC_DISC_AMT to Zero if Type of Credit is Tax or Freight
              if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
                  if (AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue()) || AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
                      ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).shipDealDiscAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).shipFuncDiscAmt, BigDecimal.ZERO);
                  }
              }
              // END 2018/04/12 E.Kameishi [QC#25467,ADD]
          }

          // START 2016/10/07 S.Fujita [QC#10522,DEL]
//          if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
//              // adjustment of tolerance
//              BigDecimal adjustShipDealDiscAmt = calculateAmount(bizMsg, shipDealDiscTotAmt, calPct).subtract(shipDealDiscCalTotAmt);
//              BigDecimal adjustShipDealHdlgChrgAmt = calculateAmount(bizMsg, shipDealHdlgChrTotgAmt, calPct).subtract(shipDealHdlgChrCalTotgAmt);
//              BigDecimal adjustShipFuncDiscAmt = calculateAmount(bizMsg, shipFuncDiscTotAmt, calPct).subtract(shipFuncDiscCalTotAmt);
//              BigDecimal adjustShipFuncHdlgChrgAmt = calculateAmount(bizMsg, shipFuncHdlgChrgTotAmt, calPct).subtract(shipFuncHdlgChrgCalTotAmt);
//
//              INV_BOLTMsg insInvBolTMsg = insInvBolTMsgList.get(invBolTMsgAry.getValidCount() - 1);
//              // set adjusted amount to TMsg
//              if (adjustShipDealDiscAmt.compareTo(BigDecimal.ZERO) != 0) {
//                  ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipDealDiscAmt, insInvBolTMsg.shipDealDiscAmt.getValue().add(adjustShipDealDiscAmt));
//              }
//              if (adjustShipDealHdlgChrgAmt.compareTo(BigDecimal.ZERO) != 0) {
//                  ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipDealHdlgChrgAmt, insInvBolTMsg.shipDealHdlgChrgAmt.getValue().add(adjustShipDealHdlgChrgAmt));
//              }
//              if (adjustShipFuncDiscAmt.compareTo(BigDecimal.ZERO) != 0) {
//                  ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipFuncDiscAmt, insInvBolTMsg.shipFuncDiscAmt.getValue().add(adjustShipFuncDiscAmt));
//              }
//              if (adjustShipFuncHdlgChrgAmt.compareTo(BigDecimal.ZERO) != 0) {
//                  ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipFuncHdlgChrgAmt, insInvBolTMsg.shipFuncHdlgChrgAmt.getValue().add(adjustShipFuncHdlgChrgAmt));
//              }
//              if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
//                  // adjustment of tolerance
//                  BigDecimal adjustShipDealFrtAmt = calculateAmount(bizMsg, shipDealFrtTotAmt, calPct).subtract(shipDealFrtCaltotAmt);
//                  BigDecimal adjustShipFuncFrtAmt = calculateAmount(bizMsg, shipFuncFrtTotAmt, calPct).subtract(shipFuncFrtCalTotAmt);
//                  // set adjusted amount to TMsg
//                  if (adjustShipDealFrtAmt.compareTo(BigDecimal.ZERO) != 0) {
//                      ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipDealFrtAmt, insInvBolTMsg.shipDealFrtAmt.getValue().add(adjustShipDealFrtAmt));
//                  }
//                  if (adjustShipFuncFrtAmt.compareTo(BigDecimal.ZERO) != 0) {
//                      ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipFuncFrtAmt, insInvBolTMsg.shipFuncFrtAmt.getValue().add(adjustShipFuncFrtAmt));
//                  }
//              }
//          }
          // END   2016/10/07 S.Fujita [QC#10522,DEL]

            // START 2018/06/28 E.Kameishi [QC#26900,DEL]
//          if (AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {
//              // adjustment of tolerance
//              BigDecimal adjustFrtDealTaxAmt = calculateAmount(bizMsg, frtDealTaxTotAmt, calPct).subtract(frtDealTaxCalTotAmt);
//              BigDecimal adjustFrtFuncTaxAmt = calculateAmount(bizMsg, frtFuncTaxTotAmt, calPct).subtract(frtFuncTaxCalTotAmt);
//
//              INV_BOLTMsg insInvBolTMsg = insInvBolTMsgList.get(invBolTMsgAry.getValidCount() - 1);
//              // set adjusted amount to TMsg
//              if (adjustFrtDealTaxAmt.compareTo(BigDecimal.ZERO) != 0) {
//                  ZYPEZDItemValueSetter.setValue(insInvBolTMsg.frtDealTaxAmt, insInvBolTMsg.frtDealTaxAmt.getValue().add(adjustFrtDealTaxAmt));
//              }
//              if (adjustFrtFuncTaxAmt.compareTo(BigDecimal.ZERO) != 0) {
//                  ZYPEZDItemValueSetter.setValue(insInvBolTMsg.frtFuncTaxAmt, insInvBolTMsg.frtFuncTaxAmt.getValue().add(adjustFrtFuncTaxAmt));
//              }
//          }
           // END 2018/06/28 E.Kameishi [QC#26900,DEL]
           // insert
           if (!insInvBolTMsgList.isEmpty()) {
              int insCnt = S21FastTBLAccessor.insert(insInvBolTMsgList.toArray(new INV_BOLTMsg[0]));
              if (insCnt != insInvBolTMsgList.size()) {
                  bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_BOL" });
                  return false;
              }
           }
        return true;
    }

    /**
     * insertInvBolByKey
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @return boolean
     */
    public static boolean insertInvBolByKey(NFCL3070CMsg bizMsg, String invNum) {
        // get original TMsg
        INV_BOLTMsg tMsg = new INV_BOLTMsg();
        tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        tMsg.invNum.setValue(bizMsg.origInvNum.getValue());
        tMsg.invBolLineNum.setValue(bizMsg.invBolLineNum.getValue());

        INV_BOLTMsg invBolTMsg = (INV_BOLTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (invBolTMsg == null) {
            bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_BOL" });
            return false;
        }

        // set original TMsg Array to copy to TMsg
        INV_BOLTMsg insInvBolTMsg = new INV_BOLTMsg();

        EZDTMsg.copy(invBolTMsg, null, insInvBolTMsg, null);
        ZYPEZDItemValueSetter.setValue(insInvBolTMsg.invNum, invNum);

        // insert
        S21FastTBLAccessor.insert(insInvBolTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insInvBolTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_BOL" });
            return false;
        }
        return true;
    }

    /**
     * insertInvBolByCondition
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @return boolean
     */
    public static boolean insertInvBolByCondition(NFCL3070CMsg bizMsg, String invNum) {
        // get original TMsg Array
        INV_BOLTMsg tMsg = new INV_BOLTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("invNum01", bizMsg.origInvNum.getValue());

        INV_BOLTMsgArray invBolTMsgAry = (INV_BOLTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (invBolTMsgAry.length() == 0) {
            bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_BOL" });
            return false;
        }

        // set original TMsg Array to copy to TMsg
        List<INV_BOLTMsg> insInvBolTMsgList = new ArrayList<INV_BOLTMsg>();
        for (int i = 0; i < invBolTMsgAry.getValidCount(); i++) {
            INV_BOLTMsg insInvBolTMsg = new INV_BOLTMsg();

            EZDTMsg.copy(invBolTMsgAry.get(i), null, insInvBolTMsg, null);
            ZYPEZDItemValueSetter.setValue(insInvBolTMsg.invNum, invNum);

            insInvBolTMsgList.add(insInvBolTMsg);
        }

        // insert
        if (!insInvBolTMsgList.isEmpty()) {
            int insCnt = S21FastTBLAccessor.insert(insInvBolTMsgList.toArray(new INV_BOLTMsg[0]));
            if (insCnt != insInvBolTMsgList.size()) {
                bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_BOL" });
                return false;
            }
        }
        return true;
    }

    /**
     * insertInvLineByKey
     * @param bizMsg NFCL3070CMsg
     * @param insInvLineTMsgList INV_LINETMsg
     * @param InvLineTMsg INV_LINETMsg
     * @return boolean
     */
    public static boolean insertInvLineByKey(NFCL3070CMsg bizMsg, List<INV_LINETMsg> insInvLineTMsgList, INV_LINETMsg invLineTMsg, String coaMdseTPCdFrt) {

        // START 2016/10/07 S.Fujita [QC#10522,DEL]
//        // get original TMsg
//        INV_LINETMsg tMsg = new INV_LINETMsg();
//        tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//        tMsg.invNum.setValue(bizMsg.origInvNum.getValue());
//        tMsg.invBolLineNum.setValue(bizMsg.invBolLineNum.getValue());
//        tMsg.invLineNum.setValue(bizMsg.invLineNum.getValue());
//        tMsg.invLineSubNum.setValue(bizMsg.invLineSubNum.getValue());
//        tMsg.invLineSubTrxNum.setValue(bizMsg.invLineSubTrxNum.getValue());
//
//        INV_LINETMsg invLineTMsg = (INV_LINETMsg) S21CacheTBLAccessor.findByKey(tMsg);
//        if (invLineTMsg == null) {
//            bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_LINE" });
//            return false;
//        }

        // set original TMsg Array to copy to TMsg
//        INV_LINETMsg insInvLineTMsg = new INV_LINETMsg();
//        EZDTMsg.copy(invLineTMsg, null, insInvLineTMsg, null);

//        ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invNum, invNum);
//        insInvLineTMsg.shpgPlnNum.clear();
//
//        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
//             // calculate amount
//             BigDecimal dealNetUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsg.dealNetUnitPrcAmt.getValue(), calPct);
//             BigDecimal invLineDealNetCalAmt = calculateAmount(bizMsg, invLineTMsg.invLineDealNetAmt.getValue(), calPct);
//             BigDecimal dealDiscUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsg.dealDiscUnitPrcAmt.getValue(), calPct);
//             BigDecimal funcNetUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsg.funcNetUnitPrcAmt.getValue(), calPct);
//             BigDecimal invLineFuncNetCalAmt = calculateAmount(bizMsg, invLineTMsg.invLineFuncNetAmt.getValue(), calPct);
//             BigDecimal funcDiscUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsg.funcDiscUnitPrcAmt.getValue(), calPct);
//             BigDecimal unitCostCalAmt = calculateAmount(bizMsg, invLineTMsg.unitCostAmt.getValue(), calPct);
//             BigDecimal shipCmplCostCalAmt = calculateAmount(bizMsg, invLineTMsg.shipCmplCostAmt.getValue(), calPct);
//             BigDecimal dealGrsUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsg.dealGrsUnitPrcAmt.getValue(), calPct);
//             BigDecimal dealGrsTotPrcCalAmt = calculateAmount(bizMsg, invLineTMsg.dealGrsTotPrcAmt.getValue(), calPct);
//             BigDecimal funcGrsUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsg.funcGrsUnitPrcAmt.getValue(), calPct);
//             BigDecimal funcGrsTotPrcCalAmt = calculateAmount(bizMsg, invLineTMsg.funcGrsTotPrcAmt.getValue(), calPct);
//             // calculate amount to TMsg
//             ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealNetUnitPrcAmt, dealNetUnitPrcCalAmt);
//             ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invLineDealNetAmt, invLineDealNetCalAmt);
//             ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealDiscUnitPrcAmt, dealDiscUnitPrcCalAmt);
//             ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcNetUnitPrcAmt, funcNetUnitPrcCalAmt);
//             ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invLineFuncNetAmt, invLineFuncNetCalAmt);
//             ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcDiscUnitPrcAmt, funcDiscUnitPrcCalAmt);
//             ZYPEZDItemValueSetter.setValue(insInvLineTMsg.unitCostAmt, unitCostCalAmt);
//             ZYPEZDItemValueSetter.setValue(insInvLineTMsg.shipCmplCostAmt, shipCmplCostCalAmt);
//             ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealGrsUnitPrcAmt, dealGrsUnitPrcCalAmt);
//             ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealGrsTotPrcAmt, dealGrsTotPrcCalAmt);
//             ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcGrsUnitPrcAmt, funcGrsUnitPrcCalAmt);
//             ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcGrsTotPrcAmt, funcGrsTotPrcCalAmt);
//        }

        // get ShipToCustAcctCd
//        String shipToCustAcctCd = getShipToCustAcctCd(bizMsg, invLineTMsg.invBolLineNum.getValue());
//        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, shipToCustAcctCd);
//        // call TaxCalculation API 
//        if (!setTaxByTaxCalculationAPI(bizMsg, invNum, insInvLineTMsg, null, invBolTMsg, dsInvTpTMsg, dsInvLineTaxDtlTMsgList)) {
//            return false;
//        }
        // END   2016/10/07 S.Fujita [QC#10522,DEL]

        // START 2018/05/23 E.Kameishi [QC#21841,MOD]
        String coaMdseTpCd = NFCL3070Query.getInstance().getMdseValue(bizMsg, insInvLineTMsgList.get(0).mdseCd.getValue());

        // for Tax or Freight
        //if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && !AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue())) {
        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).dealNetUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).invLineDealNetAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).dealDiscUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).funcNetUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).invLineFuncNetAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).funcDiscUnitPrcAmt, BigDecimal.ZERO);
            //ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).shipCmplCostAmt, BigDecimal.ZERO); // 2017/10/23 QC#20719 Del
            ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).dealGrsUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).dealGrsTotPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).funcGrsUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).funcGrsTotPrcAmt, BigDecimal.ZERO);
            // START 2019/04/08 H.Ikeda [QC#31077, ADD]
            if (ZYPCommonFunc.hasValue(insInvLineTMsgList.get(0).ordQty)) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).ordQty, BigDecimal.ZERO);
            }
            if (ZYPCommonFunc.hasValue(insInvLineTMsgList.get(0).shipQty)) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).shipQty, BigDecimal.ZERO);
            }
            if (ZYPCommonFunc.hasValue(insInvLineTMsgList.get(0).ordCustUomQty)) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).ordCustUomQty, BigDecimal.ZERO);
            }
            if (ZYPCommonFunc.hasValue(insInvLineTMsgList.get(0).invDplyQty)) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).invDplyQty, BigDecimal.ZERO);
            }
            // END   2019/04/08 H.Ikeda [QC#31077, ADD]
        }
        // for Freight
        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
            if (!coaMdseTPCdFrt.equals(coaMdseTpCd)) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).dealNetUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).invLineDealNetAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).dealDiscUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).funcNetUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).invLineFuncNetAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).funcDiscUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).dealGrsUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).dealGrsTotPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).funcGrsUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).funcGrsTotPrcAmt, BigDecimal.ZERO);
                // START 2019/04/09 H.Ikeda [QC#31077, ADD]
                if (ZYPCommonFunc.hasValue(insInvLineTMsgList.get(0).ordQty)) {
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).ordQty, BigDecimal.ZERO);
                }
                if (ZYPCommonFunc.hasValue(insInvLineTMsgList.get(0).shipQty)) {
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).shipQty, BigDecimal.ZERO);
                }
                if (ZYPCommonFunc.hasValue(insInvLineTMsgList.get(0).ordCustUomQty)) {
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).ordCustUomQty, BigDecimal.ZERO);
                }
                if (ZYPCommonFunc.hasValue(insInvLineTMsgList.get(0).invDplyQty)) {
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).invDplyQty, BigDecimal.ZERO);
                }
                // END   2019/04/09 H.Ikeda [QC#31077, ADD]
            }
        }
        // for Revievables
        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue())) {
            if (coaMdseTPCdFrt.equals(coaMdseTpCd)) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).dealNetUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).invLineDealNetAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).dealDiscUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).funcNetUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).invLineFuncNetAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).funcDiscUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).dealGrsUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).dealGrsTotPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).funcGrsUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).funcGrsTotPrcAmt, BigDecimal.ZERO);
            }
        }
        // END 2018/05/23 E.Kameishi [QC#21841,MOD]
        
        // check percent
        if (insInvLineTMsgList.get(0).taxPct.getValue().compareTo(invLineTMsg.taxPct.getValue()) != 0) {
            bizMsg.actvFlg_E.setValue(ZYPConstant.FLG_ON_Y);
        }
        // START 2018/05/23 E.Kameishi [QC#21841,DEL]
        // START 2018/04/13 E.Kameishi [QC#25467,ADD]
        // set ORD_QTY/SHIP_QTY to Zero if Type of Credit is Freight
//        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
//            if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
//                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).ordQty, BigDecimal.ZERO);
//                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).shipQty, BigDecimal.ZERO);
//            }
//        }
        // END 2018/04/13 E.Kameishi [QC#25467,ADD]
        // START 2018/05/23 E.Kameishi [QC#21841,DEL]

        // START 2019/09/04 [QC#52945,ADD]
        insInvLineTMsgList.get(0).invBolLineNum.setValue(INIT_INV_BOL_LINE_NUM);
        // END 2019/09/04 [QC#52945,ADD]
        // insert
        S21FastTBLAccessor.insert(insInvLineTMsgList.get(0));
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insInvLineTMsgList.get(0).getReturnCode())) {
            bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_LINE" });
            return false;
        }
        return true;
    }

    /**
     * insertInvLineByCondition
     * @param bizMsg NFCL3070CMsg
     * @param insInvLineTMsgList INV_LINETMsg
     * @param invLineTMsgAry INV_LINETMsgArray
     * @param calPct BigDecimal
     * @return boolean
     */
    public static boolean insertInvLineByCondition(NFCL3070CMsg bizMsg, List<INV_LINETMsg> insInvLineTMsgList, INV_LINETMsgArray invLineTMsgAry, BigDecimal calPct, String coaMdseTPCdFrt) {

        // START 2016/10/07 S.Fujita [QC#10522,DEL]
//        // get original TMsg Array
//        INV_LINETMsg tMsg = new INV_LINETMsg();
//
//        tMsg.setSQLID("003");
//        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        tMsg.setConditionValue("invNum01", bizMsg.origInvNum.getValue());
//
//        INV_LINETMsgArray invLineTMsgAry = (INV_LINETMsgArray) EZDTBLAccessor.findByCondition(tMsg);
//        if (invLineTMsgAry.length() == 0) {
//            bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_LINE" });
//            return false;
//        }
//        // initialize amounts
//        BigDecimal dealNetUnitPrcTotAmt = BigDecimal.ZERO;
//        BigDecimal dealNetUnitPrcCalTotAmt = BigDecimal.ZERO;
//        BigDecimal invLineDealNetTotAmt = BigDecimal.ZERO;
//        BigDecimal invLineDealNetCalTotAmt = BigDecimal.ZERO;
//        BigDecimal dealDiscUnitPrcTotAmt = BigDecimal.ZERO;
//        BigDecimal dealDiscUnitPrcCalTotAmt = BigDecimal.ZERO;
//        BigDecimal funcNetUnitPrcTotAmt = BigDecimal.ZERO;
//        BigDecimal funcNetUnitPrcCalTotAmt = BigDecimal.ZERO;
//        BigDecimal invLineFuncNetTotAmt = BigDecimal.ZERO;
//        BigDecimal invLineFuncNetCalTotAmt = BigDecimal.ZERO;
//        BigDecimal funcDiscUnitPrcTotAmt = BigDecimal.ZERO;
//        BigDecimal funcDiscUnitPrcCalTotAmt = BigDecimal.ZERO;
//        BigDecimal unitCostTotAmt = BigDecimal.ZERO;
//        BigDecimal unitCostCalTotAmt = BigDecimal.ZERO;
//        BigDecimal shipCmplCostTotAmt = BigDecimal.ZERO;
//        BigDecimal shipCmplCostCalTotAmt = BigDecimal.ZERO;
//        BigDecimal dealGrsUnitPrcTotAmt = BigDecimal.ZERO;
//        BigDecimal dealGrsUnitPrcCalTotAmt = BigDecimal.ZERO;
//        BigDecimal dealGrsTotPrcTotAmt = BigDecimal.ZERO;
//        BigDecimal dealGrsTotPrcCalTotAmt = BigDecimal.ZERO;
//        BigDecimal funcGrsUnitPrcTotAmt = BigDecimal.ZERO;
//        BigDecimal funcGrsUnitPrcCalTotAmt = BigDecimal.ZERO;
//        BigDecimal funcGrsTotPrcTotAmt = BigDecimal.ZERO;
//        BigDecimal funcGrsTotPrcCalTotAmt = BigDecimal.ZERO;
//
//        BigDecimal invLineDealTaxCalAmt = BigDecimal.ZERO;
//        BigDecimal invLineFuncTaxCalAmt = BigDecimal.ZERO;
//        BigDecimal invLineDealTaxTotAmt = BigDecimal.ZERO;
//        BigDecimal invLineDealTaxCalTotAmt = BigDecimal.ZERO;
//        BigDecimal invLineFuncTaxTotAmt = BigDecimal.ZERO;
//        BigDecimal invLineFuncTaxCalTotAmt = BigDecimal.ZERO;
//        // set original TMsg Array to copy to TMsg
//        List<INV_LINETMsg> insInvLineTMsgList = new ArrayList<INV_LINETMsg>();
//        for (int i = 0; i < invLineTMsgAry.getValidCount(); i++) {
//            INV_LINETMsg insInvLineTMsg = new INV_LINETMsg();
//
//            EZDTMsg.copy(invLineTMsgAry.get(i), null, insInvLineTMsg, null);
//            ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invNum, invNum);
//            insInvLineTMsg.shpgPlnNum.clear();
//
//            if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
//                 // calculate amount
//                 BigDecimal dealNetUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).dealNetUnitPrcAmt.getValue(), calPct);
//                 BigDecimal invLineDealNetCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).invLineDealNetAmt.getValue(), calPct);
//                 BigDecimal dealDiscUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).dealDiscUnitPrcAmt.getValue(), calPct);
//                 BigDecimal funcNetUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).funcNetUnitPrcAmt.getValue(), calPct);
//                 BigDecimal invLineFuncNetCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).invLineFuncNetAmt.getValue(), calPct);
//                 BigDecimal funcDiscUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).funcDiscUnitPrcAmt.getValue(), calPct);
//                 BigDecimal unitCostCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).unitCostAmt.getValue(), calPct);
//                 BigDecimal shipCmplCostCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).shipCmplCostAmt.getValue(), calPct);
//                 BigDecimal dealGrsUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).dealGrsUnitPrcAmt.getValue(), calPct);
//                 BigDecimal dealGrsTotPrcCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).dealGrsTotPrcAmt.getValue(), calPct);
//                 BigDecimal funcGrsUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).funcGrsUnitPrcAmt.getValue(), calPct);
//                 BigDecimal funcGrsTotPrcCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).funcGrsTotPrcAmt.getValue(), calPct);
//                 // calculate amount to TMsg
//                 ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealNetUnitPrcAmt, dealNetUnitPrcCalAmt);
//                 ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invLineDealNetAmt, invLineDealNetCalAmt);
//                 ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealDiscUnitPrcAmt, dealDiscUnitPrcCalAmt);
//                 ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcNetUnitPrcAmt, funcNetUnitPrcCalAmt);
//                 ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invLineFuncNetAmt, invLineFuncNetCalAmt);
//                 ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcDiscUnitPrcAmt, funcDiscUnitPrcCalAmt);
//                 ZYPEZDItemValueSetter.setValue(insInvLineTMsg.unitCostAmt, unitCostCalAmt);
//                 ZYPEZDItemValueSetter.setValue(insInvLineTMsg.shipCmplCostAmt, shipCmplCostCalAmt);
//                 ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealGrsUnitPrcAmt, dealGrsUnitPrcCalAmt);
//                 ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealGrsTotPrcAmt, dealGrsTotPrcCalAmt);
//                 ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcGrsUnitPrcAmt, funcGrsUnitPrcCalAmt);
//                 ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcGrsTotPrcAmt, funcGrsTotPrcCalAmt);
//                 // set original amount and calculated amount
//                 dealNetUnitPrcTotAmt = dealNetUnitPrcTotAmt.add(addAmount(invLineTMsgAry.no(i).dealNetUnitPrcAmt.getValue()));
//                 dealNetUnitPrcCalTotAmt = dealNetUnitPrcCalTotAmt.add(dealNetUnitPrcCalAmt);
//                 invLineDealNetTotAmt = invLineDealNetTotAmt.add(addAmount(invLineTMsgAry.no(i).invLineDealNetAmt.getValue()));
//                 invLineDealNetCalTotAmt = invLineDealNetCalTotAmt.add(invLineDealNetCalAmt);
//                 dealDiscUnitPrcTotAmt = dealDiscUnitPrcTotAmt.add(addAmount(invLineTMsgAry.no(i).dealDiscUnitPrcAmt.getValue()));
//                 dealDiscUnitPrcCalTotAmt = dealDiscUnitPrcCalTotAmt.add(dealDiscUnitPrcCalAmt);
//                 funcNetUnitPrcTotAmt = funcNetUnitPrcTotAmt.add(addAmount(invLineTMsgAry.no(i).funcNetUnitPrcAmt.getValue()));
//                 funcNetUnitPrcCalTotAmt = funcNetUnitPrcCalTotAmt.add(funcNetUnitPrcCalAmt);
//                 invLineFuncNetTotAmt = invLineFuncNetTotAmt.add(addAmount(invLineTMsgAry.no(i).invLineFuncNetAmt.getValue()));
//                 invLineFuncNetCalTotAmt = invLineFuncNetCalTotAmt.add(invLineFuncNetCalAmt);
//                 funcDiscUnitPrcTotAmt = funcDiscUnitPrcTotAmt.add(addAmount(invLineTMsgAry.no(i).funcDiscUnitPrcAmt.getValue()));
//                 funcDiscUnitPrcCalTotAmt = funcDiscUnitPrcCalTotAmt.add(funcDiscUnitPrcCalAmt);
//                 unitCostTotAmt = unitCostTotAmt.add(addAmount(invLineTMsgAry.no(i).unitCostAmt.getValue()));
//                 unitCostCalTotAmt = unitCostCalTotAmt.add(unitCostCalAmt);
//                 shipCmplCostTotAmt = shipCmplCostTotAmt.add(addAmount(invLineTMsgAry.no(i).shipCmplCostAmt.getValue()));
//                 shipCmplCostCalTotAmt = shipCmplCostCalTotAmt.add(shipCmplCostCalAmt);
//                 dealGrsUnitPrcTotAmt = dealGrsUnitPrcTotAmt.add(addAmount(invLineTMsgAry.no(i).dealGrsUnitPrcAmt.getValue()));
//                 dealGrsUnitPrcCalTotAmt = dealGrsUnitPrcCalTotAmt.add(dealGrsUnitPrcCalAmt);
//                 dealGrsTotPrcTotAmt = dealGrsTotPrcTotAmt.add(addAmount(invLineTMsgAry.no(i).dealGrsTotPrcAmt.getValue()));
//                 dealGrsTotPrcCalTotAmt = dealGrsTotPrcCalTotAmt.add(dealGrsTotPrcCalAmt);
//                 funcGrsUnitPrcTotAmt = funcGrsUnitPrcTotAmt.add(addAmount(invLineTMsgAry.no(i).funcGrsUnitPrcAmt.getValue()));
//                 funcGrsUnitPrcCalTotAmt = funcGrsUnitPrcCalTotAmt.add(funcGrsUnitPrcCalAmt);
//                 funcGrsTotPrcTotAmt = funcGrsTotPrcTotAmt.add(addAmount(invLineTMsgAry.no(i).funcGrsTotPrcAmt.getValue()));
//                 funcGrsTotPrcCalTotAmt = funcGrsTotPrcCalTotAmt.add(funcGrsTotPrcCalAmt);
//            }
//            // get INV_BOLTMsg
//            INV_BOLTMsg invBolTMsg = createInvBolTMsg(bizMsg, invLineTMsgAry.no(i).invBolLineNum.getValue());
//            // get ShipToCustAcctCd
//            String shipToCustAcctCd = getShipToCustAcctCd(bizMsg, invLineTMsgAry.no(i).invBolLineNum.getValue());
//            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, shipToCustAcctCd);
//            // call TaxCalculation API 
//            if (!setTaxByTaxCalculationAPI(bizMsg, invNum, insInvLineTMsg, null, invBolTMsg, dsInvTpTMsg, dsInvLineTaxDtlTMsgList)) {
//                return false;
//            }
//        
            // END   2016/10/07 S.Fujita [QC#10522,DEL]

          // initialize amounts
          BigDecimal invLineDealTaxCalAmt = BigDecimal.ZERO;
          BigDecimal invLineFuncTaxCalAmt = BigDecimal.ZERO;
          BigDecimal invLineDealTaxTotAmt = BigDecimal.ZERO;
          BigDecimal invLineDealTaxCalTotAmt = BigDecimal.ZERO;
          BigDecimal invLineFuncTaxTotAmt = BigDecimal.ZERO;
          BigDecimal invLineFuncTaxCalTotAmt = BigDecimal.ZERO;

          for (int i = 0; i < invLineTMsgAry.getValidCount(); i++) {
              String coaMdseTpCd = NFCL3070Query.getInstance().getMdseValue(bizMsg, insInvLineTMsgList.get(i).mdseCd.getValue());
              if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
                  // set original tax amount and calculated tax amount
                  invLineDealTaxCalAmt = addAmount(invLineTMsgAry.no(i).invLineDealNetAmt.getValue()).multiply(insInvLineTMsgList.get(i).taxPct.getValue()).divide(new BigDecimal(HIDX)).setScale(bizMsg.aftDeclPntDigitNum.getValue().intValue(), HALF_UP);
                  invLineDealTaxTotAmt = invLineDealTaxTotAmt.add(invLineDealTaxCalAmt);
                  invLineDealTaxCalTotAmt = invLineDealTaxCalTotAmt.add(insInvLineTMsgList.get(i).invLineDealTaxAmt.getValue());
                  invLineFuncTaxCalAmt = addAmount(invLineTMsgAry.no(i).invLineFuncNetAmt.getValue()).multiply(insInvLineTMsgList.get(i).taxPct.getValue()).divide(new BigDecimal(HIDX)).setScale(bizMsg.aftDeclPntDigitNum.getValue().intValue(), HALF_UP);
                  invLineFuncTaxTotAmt = invLineFuncTaxTotAmt.add(invLineFuncTaxCalAmt);
                  invLineFuncTaxCalTotAmt = invLineFuncTaxCalTotAmt.add(insInvLineTMsgList.get(i).invLineFuncTaxAmt.getValue());
              }
              // START 2018/05/23 E.Kameishi [QC#21841,MOD]
              // for Tax
              //if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && !AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue())) {
              if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {
                  ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealNetUnitPrcAmt, BigDecimal.ZERO);
                  ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).invLineDealNetAmt, BigDecimal.ZERO);
                  ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealDiscUnitPrcAmt, BigDecimal.ZERO);
                  ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcNetUnitPrcAmt, BigDecimal.ZERO);
                  ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).invLineFuncNetAmt, BigDecimal.ZERO);
                  ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcDiscUnitPrcAmt, BigDecimal.ZERO);
                  //ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).shipCmplCostAmt, BigDecimal.ZERO); // 2017/10/23 QC#2071 Del
                  ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealGrsUnitPrcAmt, BigDecimal.ZERO);
                  ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealGrsTotPrcAmt, BigDecimal.ZERO);
                  ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcGrsUnitPrcAmt, BigDecimal.ZERO);
                  ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcGrsTotPrcAmt, BigDecimal.ZERO);
                  // START 2019/04/09 H.Ikeda [QC#31077, ADD]
                  if (ZYPCommonFunc.hasValue(insInvLineTMsgList.get(i).ordQty)) {
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).ordQty, BigDecimal.ZERO);
                  }
                  if (ZYPCommonFunc.hasValue(insInvLineTMsgList.get(i).shipQty)) {
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).shipQty, BigDecimal.ZERO);
                  }
                  if (ZYPCommonFunc.hasValue(insInvLineTMsgList.get(i).ordCustUomQty)) {
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).ordCustUomQty, BigDecimal.ZERO);
                  }
                  if (ZYPCommonFunc.hasValue(insInvLineTMsgList.get(i).invDplyQty)) {
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).invDplyQty, BigDecimal.ZERO);
                  }
                  // END   2019/04/09 H.Ikeda [QC#31077, ADD]
              }
              // for Freight
              if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
                  if (!coaMdseTPCdFrt.equals(coaMdseTpCd)) {
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealNetUnitPrcAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).invLineDealNetAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealDiscUnitPrcAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcNetUnitPrcAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).invLineFuncNetAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcDiscUnitPrcAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealGrsUnitPrcAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealGrsTotPrcAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcGrsUnitPrcAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcGrsTotPrcAmt, BigDecimal.ZERO);
                      // START 2019/04/09 H.Ikeda [QC#31077, ADD]
                      if (ZYPCommonFunc.hasValue(insInvLineTMsgList.get(i).ordQty)) {
                          ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).ordQty, BigDecimal.ZERO);
                      }
                      if (ZYPCommonFunc.hasValue(insInvLineTMsgList.get(i).shipQty)) {
                          ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).shipQty, BigDecimal.ZERO);
                      }
                      if (ZYPCommonFunc.hasValue(insInvLineTMsgList.get(i).ordCustUomQty)) {
                          ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).ordCustUomQty, BigDecimal.ZERO);
                      }
                      if (ZYPCommonFunc.hasValue(insInvLineTMsgList.get(i).invDplyQty)) {
                          ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).invDplyQty, BigDecimal.ZERO);
                      }
                      // END   2019/04/09 H.Ikeda [QC#31077, ADD]
                  }
              }
              // for Revievables
              if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue())) {
                  if (coaMdseTPCdFrt.equals(coaMdseTpCd)) {
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealNetUnitPrcAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).invLineDealNetAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealDiscUnitPrcAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcNetUnitPrcAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).invLineFuncNetAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcDiscUnitPrcAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealGrsUnitPrcAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealGrsTotPrcAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcGrsUnitPrcAmt, BigDecimal.ZERO);
                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcGrsTotPrcAmt, BigDecimal.ZERO);
                  }
              }
              // END 2018/05/23 E.Kameishi [QC#21841,MOD]
              // check percent
              int cnt = 0;
              if (insInvLineTMsgList.get(i).taxPct.getValue().compareTo(invLineTMsgAry.no(i).taxPct.getValue()) != 0 && cnt == 0) {
                  bizMsg.actvFlg_E.setValue(ZYPConstant.FLG_ON_Y);
                  cnt++;
              }
              // START 2018/05/23 E.Kameishi [QC#21841,DEL]
              // START 2018/04/13 E.Kameishi [QC#25467,ADD]
              // set ORD_QTY/SHIP_QTY to Zero if Type of Credit is Freight
//              if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
//                  if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
//                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).ordQty, BigDecimal.ZERO);
//                      ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).shipQty, BigDecimal.ZERO);
//                  }
//              }
              // END 2018/04/13 E.Kameishi [QC#25467,ADD]
              // END 2018/05/23 E.Kameishi [QC#21841,DEL]
          }

        // START 2016/10/07 S.Fujita [QC#10522,DEL]
//        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue())) {
//            // adjustment of tolerance
//            BigDecimal adjustDealNetUnitPrcAmt = calculateAmount(bizMsg, dealNetUnitPrcTotAmt, calPct).subtract(dealNetUnitPrcCalTotAmt);
//            BigDecimal adjustInvLineDealNetAmt = calculateAmount(bizMsg, invLineDealNetTotAmt, calPct).subtract(invLineDealNetCalTotAmt);
//            BigDecimal adjustDealDiscUnitPrcAmt = calculateAmount(bizMsg, dealDiscUnitPrcTotAmt, calPct).subtract(dealDiscUnitPrcCalTotAmt);
//            BigDecimal adjustFuncNetUnitPrcAmt = calculateAmount(bizMsg, funcNetUnitPrcTotAmt, calPct).subtract(funcNetUnitPrcCalTotAmt);
//            BigDecimal adjustInvLineFuncNetAmt = calculateAmount(bizMsg, invLineFuncNetTotAmt, calPct).subtract(invLineFuncNetCalTotAmt);
//            BigDecimal adjustFuncDiscUnitPrcAmt = calculateAmount(bizMsg, funcDiscUnitPrcTotAmt, calPct).subtract(funcDiscUnitPrcCalTotAmt);
//            BigDecimal adjustUnitCostAmt = calculateAmount(bizMsg, unitCostTotAmt, calPct).subtract(unitCostCalTotAmt);
//            BigDecimal adjustShipCmplCostAmt = calculateAmount(bizMsg, shipCmplCostTotAmt, calPct).subtract(shipCmplCostCalTotAmt);
//            BigDecimal adjustDealGrsUnitPrcAmt = calculateAmount(bizMsg, dealGrsUnitPrcTotAmt, calPct).subtract(dealGrsUnitPrcCalTotAmt);
//            BigDecimal adjustDealGrsTotPrcAmt = calculateAmount(bizMsg, dealGrsTotPrcTotAmt, calPct).subtract(dealGrsTotPrcCalTotAmt);
//            BigDecimal adjustFuncGrsUnitPrcAmt = calculateAmount(bizMsg, funcGrsUnitPrcTotAmt, calPct).subtract(funcGrsUnitPrcCalTotAmt);
//            BigDecimal adjustFuncGrsTotPrcAmt = calculateAmount(bizMsg, funcGrsTotPrcTotAmt, calPct).subtract(funcGrsTotPrcCalTotAmt);
//
//            INV_LINETMsg insInvLineTMsg = insInvLineTMsgList.get(invLineTMsgAry.getValidCount() - 1);
//            // set adjusted amount to TMsg
//            if (adjustDealNetUnitPrcAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealNetUnitPrcAmt, insInvLineTMsg.dealNetUnitPrcAmt.getValue().add(adjustDealNetUnitPrcAmt));
//            }
//            if (adjustInvLineDealNetAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invLineDealNetAmt, insInvLineTMsg.invLineDealNetAmt.getValue().add(adjustInvLineDealNetAmt));
//            }
//            if (adjustDealDiscUnitPrcAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealDiscUnitPrcAmt, insInvLineTMsg.dealDiscUnitPrcAmt.getValue().add(adjustDealDiscUnitPrcAmt));
//            }
//            if (adjustFuncNetUnitPrcAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcNetUnitPrcAmt, insInvLineTMsg.funcNetUnitPrcAmt.getValue().add(adjustFuncNetUnitPrcAmt));
//            }
//            if (adjustInvLineFuncNetAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invLineFuncNetAmt, insInvLineTMsg.invLineFuncNetAmt.getValue().add(adjustInvLineFuncNetAmt));
//            }
//            if (adjustFuncDiscUnitPrcAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcDiscUnitPrcAmt, insInvLineTMsg.funcDiscUnitPrcAmt.getValue().add(adjustFuncDiscUnitPrcAmt));
//            }
//            if (adjustUnitCostAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.unitCostAmt, insInvLineTMsg.unitCostAmt.getValue().add(adjustUnitCostAmt));
//            }
//            if (adjustShipCmplCostAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.shipCmplCostAmt, insInvLineTMsg.shipCmplCostAmt.getValue().add(adjustShipCmplCostAmt));
//            }
//            if (adjustDealGrsUnitPrcAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealGrsUnitPrcAmt, insInvLineTMsg.dealGrsUnitPrcAmt.getValue().add(adjustDealGrsUnitPrcAmt));
//            }
//            if (adjustDealGrsTotPrcAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealGrsTotPrcAmt, insInvLineTMsg.dealGrsTotPrcAmt.getValue().add(adjustDealGrsTotPrcAmt));
//            }
//            if (adjustFuncGrsUnitPrcAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcGrsUnitPrcAmt, insInvLineTMsg.funcGrsUnitPrcAmt.getValue().add(adjustFuncGrsUnitPrcAmt));
//            }
//            if (adjustFuncGrsTotPrcAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealNetUnitPrcAmt, insInvLineTMsg.funcGrsTotPrcAmt.getValue().add(adjustFuncGrsTotPrcAmt));
//            }
//        }
        // END   2016/10/07 S.Fujita [QC#10522,DEL]

// START 2019/01/21 Y.Matsui [QC#29961,DEL]
//        if (AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {
//            // adjustment of tolerance
//            BigDecimal adjustInvLineFuncTaxAmt = calculateAmount(bizMsg, invLineFuncTaxTotAmt, calPct).subtract(invLineFuncTaxCalTotAmt);
//            BigDecimal adjustInvLineDealTaxAmt = calculateAmount(bizMsg, invLineDealTaxTotAmt, calPct).subtract(invLineDealTaxCalTotAmt);
//
//            INV_LINETMsg insInvLineTMsg = insInvLineTMsgList.get(invLineTMsgAry.getValidCount() - 1);
//            // set adjusted amount to TMsg
//            if (adjustInvLineFuncTaxAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invLineFuncTaxAmt, insInvLineTMsg.invLineFuncTaxAmt.getValue().add(adjustInvLineFuncTaxAmt));
//            }
//            if (adjustInvLineDealTaxAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invLineDealTaxAmt, insInvLineTMsg.invLineDealTaxAmt.getValue().add(adjustInvLineDealTaxAmt));
//            }
//        }
// END   2019/01/21 Y.Matsui [QC#29961,DEL]

        // insert
        if (!insInvLineTMsgList.isEmpty()) {
            int insCnt = S21FastTBLAccessor.insert(insInvLineTMsgList.toArray(new INV_LINETMsg[0]));
            if (insCnt != insInvLineTMsgList.size()) {
                bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_LINE" });
                return false;
            }
        }
        return true;
    }

    // START 2018/08/29 S.Ohki [QC#27887, DEL]
//    /**
//     * insertInvLineByKey
//     * 
//     * @param bizMsg NFCL3070CMsg
//     * @param invNum String
//     * @return boolean
//     */
//    public static boolean insertInvLineByKey(NFCL3070CMsg bizMsg, String invNum) {
//        // get original TMsg
//        INV_LINETMsg tMsg = new INV_LINETMsg();
//        tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//        tMsg.invNum.setValue(bizMsg.origInvNum.getValue());
//        tMsg.invBolLineNum.setValue(bizMsg.invBolLineNum.getValue());
//        tMsg.invLineNum.setValue(bizMsg.invLineNum.getValue());
//        tMsg.invLineSubNum.setValue(bizMsg.invLineSubNum.getValue());
//        tMsg.invLineSubTrxNum.setValue(bizMsg.invLineSubTrxNum.getValue());
//
//        INV_LINETMsg invLineTMsg = (INV_LINETMsg) S21CacheTBLAccessor.findByKey(tMsg);
//        if (invLineTMsg == null) {
//            bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_LINE" });
//            return false;
//        }
//        ZYPEZDItemValueSetter.setValue(bizMsg.dsContrNum, invLineTMsg.dsContrNum);
//
//        // set original TMsg Array to copy to TMsg
//        INV_LINETMsg insInvLineTMsg = new INV_LINETMsg();
//
//        EZDTMsg.copy(invLineTMsg, null, insInvLineTMsg, null);
//        ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invNum, invNum);
//
//        // insert
//        S21FastTBLAccessor.insert(insInvLineTMsg);
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insInvLineTMsg.getReturnCode())) {
//            bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_LINE" });
//            return false;
//        }
//        return true;
//    }
    // END 2018/08/29 S.Ohki [QC#27887, DEL]

    /**
     * insertDsInvSlsCr
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @param calPct BigDecimal
     * @param slsCrPkMap Map<BigDecimal, BigDecimal>
     * @return boolean
     */
    public static boolean insertDsInvSlsCr(NFCL3070CMsg bizMsg, String invNum, BigDecimal calPct, Map<BigDecimal, BigDecimal> slsCrPkMap, String coaMdseTPCdFrt, Map<BigDecimal, String> dfrdSlsCrPkMap) {
        // get original TMsg Array
        DS_INV_SLS_CRTMsg tMsg = new DS_INV_SLS_CRTMsg();

        if (bizMsg.xxPgFlg_T.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            // START 2018/08/29 S.Ohki [QC#27887, MOD]
//            tMsg.setSQLID("002");
            tMsg.setSQLID("004");
            // END 2018/08/29 S.Ohki [QC#27887, MOD]

            tMsg.setConditionValue("invBolLineNum01", bizMsg.invBolLineNum.getValue());
            tMsg.setConditionValue("invLineNum01", bizMsg.invLineNum.getValue());
            // START 2018/08/29 S.Ohki [QC#27887, DEL]
//            tMsg.setConditionValue("invLineSubNum01", bizMsg.invLineSubNum.getValue());
//            tMsg.setConditionValue("invTrxLineSubNum01", bizMsg.invLineSubTrxNum.getValue());
            // END 2018/08/29 S.Ohki [QC#27887, DEL]

        } else {
            tMsg.setSQLID("001");
        }

        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("invNum01", bizMsg.origInvNum.getValue());

        DS_INV_SLS_CRTMsgArray dsInvSlsCrTMsgAry = (DS_INV_SLS_CRTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (dsInvSlsCrTMsgAry.length() == 0) {
            bizMsg.setMessageInfo(NFCM0782E, new String[] {"DS_INV_SLS_CR" });
            return false;
        }

        // initialize amounts
        BigDecimal dealSlsCrTotAmt = BigDecimal.ZERO;
        BigDecimal dealSlsCrCalTotAmt = BigDecimal.ZERO;
        BigDecimal funcSlsCrTotAmt = BigDecimal.ZERO;
        BigDecimal funcSlsCrCalTotAmt = BigDecimal.ZERO;
        BigDecimal dealOrigDfrdTotAmt = BigDecimal.ZERO;
        BigDecimal dealOrigDfrdCalTotAmt = BigDecimal.ZERO;
        BigDecimal funcOrigDfrdTotAmt = BigDecimal.ZERO;
        BigDecimal funcOrigDfrdCalTotAmt = BigDecimal.ZERO;
        BigDecimal dealDfrdBalTotAmt = BigDecimal.ZERO;
        BigDecimal dealDfrdBalCalTotAmt = BigDecimal.ZERO;
        BigDecimal funcDfrdBalTotAmt = BigDecimal.ZERO;
        BigDecimal funcDfrdBalCalTotAmt = BigDecimal.ZERO;
        BigDecimal dealSchdRevTotAmt = BigDecimal.ZERO;
        BigDecimal dealSchdRevCalTotAmt = BigDecimal.ZERO;
        BigDecimal funcSchdRevTotAmt = BigDecimal.ZERO;
        BigDecimal funcSchdRevCalTotAmt = BigDecimal.ZERO;

        // set original TMsg Array to copy to TMsg
        List<DS_INV_SLS_CRTMsg> insDsInvSlsCrTMsgList = new ArrayList<DS_INV_SLS_CRTMsg>();
        for (int i = 0; i < dsInvSlsCrTMsgAry.getValidCount(); i++) {
            DS_INV_SLS_CRTMsg insDsInvSlsCrTMsg = new DS_INV_SLS_CRTMsg();

            EZDTMsg.copy(dsInvSlsCrTMsgAry.get(i), null, insDsInvSlsCrTMsg, null);
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dsInvSlsCrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_INV_SLS_CR_SQ));

            BigDecimal orgSlsCrPk = dsInvSlsCrTMsgAry.no(i).dsInvSlsCrPk.getValue();
            BigDecimal newSlsCrPk = insDsInvSlsCrTMsg.dsInvSlsCrPk.getValue();
            // put orgSlsCrPk and newSlsCrPk
            slsCrPkMap.put(orgSlsCrPk, newSlsCrPk);

            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.invNum, invNum);

            // START 2019/09/04 [QC#52945, ADD]
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPgFlg_T.getValue())) {
                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.invBolLineNum, INIT_INV_BOL_LINE_NUM);
            }
            // END 2019/09/04 [QC#52945, ADD]

            // START 2018/05/23 E.Kameishi [QC#21841,MOD]
            String coaMdseTpCd = NFCL3070Query.getInstance().getMdseValue(bizMsg, dsInvSlsCrTMsgAry.no(i).mdseCd.getValue());
            if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
                if (AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue())) {
                    if (!coaMdseTPCdFrt.equals(coaMdseTpCd)) {
                     // calculate amount
                     BigDecimal dealSlsCrCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).dealSlsCrAmt.getValue(), calPct);
                     BigDecimal funcSlsCrCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).funcSlsCrAmt.getValue(), calPct);
                     BigDecimal dealOrigDfrdCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).dealOrigDfrdAmt.getValue(), calPct);
                     BigDecimal funcOrigDfrdCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).funcOrigDfrdAmt.getValue(), calPct);
                     BigDecimal dealDfrdBalCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).dealDfrdBalAmt.getValue(), calPct);
                     BigDecimal funcDfrdBalCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).funcDfrdBalAmt.getValue(), calPct);
                     BigDecimal dealSchdRevCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).dealSchdRevAmt.getValue(), calPct);
                     BigDecimal funcSchdRevCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).funcSchdRevAmt.getValue(), calPct);
                     // calculate amount to TMsg
                     ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealSlsCrAmt, dealSlsCrCalAmt);
                     ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcSlsCrAmt, funcSlsCrCalAmt);
                     ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealOrigDfrdAmt, dealOrigDfrdCalAmt);
                     ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcOrigDfrdAmt, funcOrigDfrdCalAmt);
                     ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealDfrdBalAmt, dealDfrdBalCalAmt);
                     ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcDfrdBalAmt, funcDfrdBalCalAmt);
                     ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealSchdRevAmt, dealSchdRevCalAmt);
                     ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcSchdRevAmt, funcSchdRevCalAmt);
                     // set original amount and calculated amount
                     dealSlsCrTotAmt = dealSlsCrTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).dealSlsCrAmt.getValue()));
                     dealSlsCrCalTotAmt = dealSlsCrCalTotAmt.add(dealSlsCrCalAmt);
                     funcSlsCrTotAmt = funcSlsCrTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).funcSlsCrAmt.getValue()));
                     funcSlsCrCalTotAmt = funcSlsCrCalTotAmt.add(funcSlsCrCalAmt);
                     dealOrigDfrdTotAmt = dealOrigDfrdTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).dealOrigDfrdAmt.getValue()));
                     dealOrigDfrdCalTotAmt = dealOrigDfrdCalTotAmt.add(dealOrigDfrdCalAmt);
                     funcOrigDfrdTotAmt = funcOrigDfrdTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).funcOrigDfrdAmt.getValue()));
                     funcOrigDfrdCalTotAmt = funcOrigDfrdCalTotAmt.add(funcOrigDfrdCalAmt);
                     dealDfrdBalTotAmt = dealDfrdBalTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).dealDfrdBalAmt.getValue()));
                     dealDfrdBalCalTotAmt = dealDfrdBalCalTotAmt.add(dealDfrdBalCalAmt);
                     funcDfrdBalTotAmt = funcDfrdBalTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).funcDfrdBalAmt.getValue()));
                     funcDfrdBalCalTotAmt = funcDfrdBalCalTotAmt.add(funcDfrdBalCalAmt);
                     dealSchdRevTotAmt = dealSchdRevTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).dealSchdRevAmt.getValue()));
                     dealSchdRevCalTotAmt = dealSchdRevCalTotAmt.add(dealSchdRevCalAmt);
                     funcSchdRevTotAmt = funcSchdRevTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).funcSchdRevAmt.getValue()));
                     funcSchdRevCalTotAmt = funcSchdRevCalTotAmt.add(funcSchdRevCalAmt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealSlsCrAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcSlsCrAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealOrigDfrdAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcOrigDfrdAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealDfrdBalAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcDfrdBalAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealSchdRevAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcSchdRevAmt, BigDecimal.ZERO);
                    }
                } else if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
                    if (coaMdseTPCdFrt.equals(coaMdseTpCd)) {
                        // calculate amount
                        BigDecimal dealSlsCrCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).dealSlsCrAmt.getValue(), calPct);
                        BigDecimal funcSlsCrCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).funcSlsCrAmt.getValue(), calPct);
                        BigDecimal dealOrigDfrdCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).dealOrigDfrdAmt.getValue(), calPct);
                        BigDecimal funcOrigDfrdCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).funcOrigDfrdAmt.getValue(), calPct);
                        BigDecimal dealDfrdBalCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).dealDfrdBalAmt.getValue(), calPct);
                        BigDecimal funcDfrdBalCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).funcDfrdBalAmt.getValue(), calPct);
                        BigDecimal dealSchdRevCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).dealSchdRevAmt.getValue(), calPct);
                        BigDecimal funcSchdRevCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).funcSchdRevAmt.getValue(), calPct);
                        // calculate amount to TMsg
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealSlsCrAmt, dealSlsCrCalAmt);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcSlsCrAmt, funcSlsCrCalAmt);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealOrigDfrdAmt, dealOrigDfrdCalAmt);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcOrigDfrdAmt, funcOrigDfrdCalAmt);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealDfrdBalAmt, dealDfrdBalCalAmt);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcDfrdBalAmt, funcDfrdBalCalAmt);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealSchdRevAmt, dealSchdRevCalAmt);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcSchdRevAmt, funcSchdRevCalAmt);
                        // set original amount and calculated amount
                        dealSlsCrTotAmt = dealSlsCrTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).dealSlsCrAmt.getValue()));
                        dealSlsCrCalTotAmt = dealSlsCrCalTotAmt.add(dealSlsCrCalAmt);
                        funcSlsCrTotAmt = funcSlsCrTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).funcSlsCrAmt.getValue()));
                        funcSlsCrCalTotAmt = funcSlsCrCalTotAmt.add(funcSlsCrCalAmt);
                        dealOrigDfrdTotAmt = dealOrigDfrdTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).dealOrigDfrdAmt.getValue()));
                        dealOrigDfrdCalTotAmt = dealOrigDfrdCalTotAmt.add(dealOrigDfrdCalAmt);
                        funcOrigDfrdTotAmt = funcOrigDfrdTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).funcOrigDfrdAmt.getValue()));
                        funcOrigDfrdCalTotAmt = funcOrigDfrdCalTotAmt.add(funcOrigDfrdCalAmt);
                        dealDfrdBalTotAmt = dealDfrdBalTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).dealDfrdBalAmt.getValue()));
                        dealDfrdBalCalTotAmt = dealDfrdBalCalTotAmt.add(dealDfrdBalCalAmt);
                        funcDfrdBalTotAmt = funcDfrdBalTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).funcDfrdBalAmt.getValue()));
                        funcDfrdBalCalTotAmt = funcDfrdBalCalTotAmt.add(funcDfrdBalCalAmt);
                        dealSchdRevTotAmt = dealSchdRevTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).dealSchdRevAmt.getValue()));
                        dealSchdRevCalTotAmt = dealSchdRevCalTotAmt.add(dealSchdRevCalAmt);
                        funcSchdRevTotAmt = funcSchdRevTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).funcSchdRevAmt.getValue()));
                        funcSchdRevCalTotAmt = funcSchdRevCalTotAmt.add(funcSchdRevCalAmt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealSlsCrAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcSlsCrAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealOrigDfrdAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcOrigDfrdAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealDfrdBalAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcDfrdBalAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealSchdRevAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcSchdRevAmt, BigDecimal.ZERO);
                    }
                } else if (AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {
                     ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealSlsCrAmt, BigDecimal.ZERO);
                     ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcSlsCrAmt, BigDecimal.ZERO);
                     ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealOrigDfrdAmt, BigDecimal.ZERO);
                     ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcOrigDfrdAmt, BigDecimal.ZERO);
                     ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealDfrdBalAmt, BigDecimal.ZERO);
                     ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcDfrdBalAmt, BigDecimal.ZERO);
                     ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealSchdRevAmt, BigDecimal.ZERO);
                     ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcSchdRevAmt, BigDecimal.ZERO);
                } else {
                    // calculate amount
                    BigDecimal dealSlsCrCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).dealSlsCrAmt.getValue(), calPct);
                    BigDecimal funcSlsCrCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).funcSlsCrAmt.getValue(), calPct);
                    BigDecimal dealOrigDfrdCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).dealOrigDfrdAmt.getValue(), calPct);
                    BigDecimal funcOrigDfrdCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).funcOrigDfrdAmt.getValue(), calPct);
                    BigDecimal dealDfrdBalCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).dealDfrdBalAmt.getValue(), calPct);
                    BigDecimal funcDfrdBalCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).funcDfrdBalAmt.getValue(), calPct);
                    BigDecimal dealSchdRevCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).dealSchdRevAmt.getValue(), calPct);
                    BigDecimal funcSchdRevCalAmt = calculateAmount(bizMsg, dsInvSlsCrTMsgAry.no(i).funcSchdRevAmt.getValue(), calPct);
                    // calculate amount to TMsg
                    ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealSlsCrAmt, dealSlsCrCalAmt);
                    ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcSlsCrAmt, funcSlsCrCalAmt);
                    ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealOrigDfrdAmt, dealOrigDfrdCalAmt);
                    ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcOrigDfrdAmt, funcOrigDfrdCalAmt);
                    ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealDfrdBalAmt, dealDfrdBalCalAmt);
                    ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcDfrdBalAmt, funcDfrdBalCalAmt);
                    ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealSchdRevAmt, dealSchdRevCalAmt);
                    ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcSchdRevAmt, funcSchdRevCalAmt);
                    // set original amount and calculated amount
                    dealSlsCrTotAmt = dealSlsCrTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).dealSlsCrAmt.getValue()));
                    dealSlsCrCalTotAmt = dealSlsCrCalTotAmt.add(dealSlsCrCalAmt);
                    funcSlsCrTotAmt = funcSlsCrTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).funcSlsCrAmt.getValue()));
                    funcSlsCrCalTotAmt = funcSlsCrCalTotAmt.add(funcSlsCrCalAmt);
                    dealOrigDfrdTotAmt = dealOrigDfrdTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).dealOrigDfrdAmt.getValue()));
                    dealOrigDfrdCalTotAmt = dealOrigDfrdCalTotAmt.add(dealOrigDfrdCalAmt);
                    funcOrigDfrdTotAmt = funcOrigDfrdTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).funcOrigDfrdAmt.getValue()));
                    funcOrigDfrdCalTotAmt = funcOrigDfrdCalTotAmt.add(funcOrigDfrdCalAmt);
                    dealDfrdBalTotAmt = dealDfrdBalTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).dealDfrdBalAmt.getValue()));
                    dealDfrdBalCalTotAmt = dealDfrdBalCalTotAmt.add(dealDfrdBalCalAmt);
                    funcDfrdBalTotAmt = funcDfrdBalTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).funcDfrdBalAmt.getValue()));
                    funcDfrdBalCalTotAmt = funcDfrdBalCalTotAmt.add(funcDfrdBalCalAmt);
                    dealSchdRevTotAmt = dealSchdRevTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).dealSchdRevAmt.getValue()));
                    dealSchdRevCalTotAmt = dealSchdRevCalTotAmt.add(dealSchdRevCalAmt);
                    funcSchdRevTotAmt = funcSchdRevTotAmt.add(addAmount(dsInvSlsCrTMsgAry.no(i).funcSchdRevAmt.getValue()));
                    funcSchdRevCalTotAmt = funcSchdRevCalTotAmt.add(funcSchdRevCalAmt);
                }
            }
            // END 2018/05/23 E.Kameishi [QC#21841,MOD]
            // START 2018/06/04 E.Kameishi [QC#25191,ADD]
            if (DFRD_ACCTG_RULE.DEFERRED.equals(insDsInvSlsCrTMsg.dfrdAcctgRuleCd.getValue())
                    || DFRD_ACCTG_RULE.MAINTENANCE_DEFERRAL.equals(insDsInvSlsCrTMsg.dfrdAcctgRuleCd.getValue())
                    || DFRD_ACCTG_RULE.OPTIMA_DEFERRAL.equals(insDsInvSlsCrTMsg.dfrdAcctgRuleCd.getValue())
                    || DFRD_ACCTG_RULE.SUBSCRIPTION_SERVICE.equals(insDsInvSlsCrTMsg.dfrdAcctgRuleCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dfrdAcctgRuleCd, DFRD_ACCTG_RULE.IMMEDIATE);
                insDsInvSlsCrTMsg.dfrdAcctgRuleDurnAot.clear();
                insDsInvSlsCrTMsg.durnStartDt.clear();
                insDsInvSlsCrTMsg.dealOrigDfrdAmt.clear();
                insDsInvSlsCrTMsg.funcOrigDfrdAmt.clear();
                insDsInvSlsCrTMsg.dealDfrdBalAmt.clear();
                insDsInvSlsCrTMsg.funcDfrdBalAmt.clear();
                insDsInvSlsCrTMsg.dealSchdRevAmt.clear();
                insDsInvSlsCrTMsg.funcSchdRevAmt.clear();
                insDsInvSlsCrTMsg.revRecogCnt.clear();
                insDsInvSlsCrTMsg.firstRevRecogDt.clear();
                insDsInvSlsCrTMsg.nextRevRecogDt.clear();
                insDsInvSlsCrTMsg.revRecogProcStsCd.clear();
                insDsInvSlsCrTMsg.cpltRevRecogDt.clear();
                dfrdSlsCrPkMap.put(newSlsCrPk, insDsInvSlsCrTMsg.dfrdAcctgRuleCd.getValue());
            }
            // END 2018/06/04 E.Kameishi [QC#25191,ADD]
            insDsInvSlsCrTMsgList.add(insDsInvSlsCrTMsg);
        }

        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && !AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {
            // adjustment of tolerance
            BigDecimal adjustDealSlsCrAmt = calculateAmount(bizMsg, dealSlsCrTotAmt, calPct).subtract(dealSlsCrCalTotAmt);
            BigDecimal adjustFuncSlsCrAmt = calculateAmount(bizMsg, funcSlsCrTotAmt, calPct).subtract(funcSlsCrCalTotAmt);
            // START 2019/06/19 H.Ikeda [QC#50785, DEL]
//            BigDecimal adjustDealOrigDfrdAmt = calculateAmount(bizMsg, dealOrigDfrdTotAmt, calPct).subtract(dealOrigDfrdCalTotAmt);
//            BigDecimal adjustFuncOrigDfrdAmt = calculateAmount(bizMsg, funcOrigDfrdTotAmt, calPct).subtract(funcOrigDfrdCalTotAmt);
//            BigDecimal adjustDealDfrdBalAmt = calculateAmount(bizMsg, dealDfrdBalTotAmt, calPct).subtract(dealDfrdBalCalTotAmt);
//            BigDecimal adjustFuncDfrdBalAmt = calculateAmount(bizMsg, funcDfrdBalTotAmt, calPct).subtract(funcDfrdBalCalTotAmt);
//            BigDecimal adjustDealSchdRevAmt = calculateAmount(bizMsg, dealSchdRevTotAmt, calPct).subtract(dealSchdRevCalTotAmt);
//            BigDecimal adjustFuncSchdRevAmt = calculateAmount(bizMsg, funcSchdRevTotAmt, calPct).subtract(funcSchdRevCalTotAmt);
            // END   2019/06/19 H.Ikeda [QC#50785, DEL]

            // START 2019/06/05 [QC#50631, MOD]
            // DS_INV_SLS_CRTMsg insDsInvSlsCrTMsg = insDsInvSlsCrTMsgList.get(dsInvSlsCrTMsgAry.getValidCount() - 1);
            String coaMdseTpCdFrt = ZYPCodeDataUtil.getVarCharConstValue("AJE_COA_MDSE_TP_FRT", bizMsg.glblCmpyCd.getValue());
            int adjLineIdx = dsInvSlsCrTMsgAry.getValidCount() - 1;

            // START 2023/03/10 D.Mamaril [QC#61119,ADD]
            int largestAmtIdx = adjLineIdx;
            BigDecimal largestAmt = insDsInvSlsCrTMsgList.get(largestAmtIdx).dealSlsCrAmt.getValue();
            BigDecimal currentAmt;
            // END 2023/03/10 D.Mamaril [QC#61119,ADD]

            for (adjLineIdx = dsInvSlsCrTMsgAry.getValidCount() - 1; adjLineIdx >= 0; adjLineIdx--) {
                // START 2023/03/10 D.Mamaril [QC#61119,ADD]
                currentAmt = insDsInvSlsCrTMsgList.get(adjLineIdx).dealSlsCrAmt.getValue();
                if (currentAmt.compareTo(largestAmt) > 0) {
                    largestAmtIdx = adjLineIdx;
                    largestAmt = currentAmt;
                }
                // END 2023/03/10 D.Mamaril [QC#61119,ADD]

                String coaMdseTpCd = NFCL3070Query.getInstance().getMdseValue(bizMsg, dsInvSlsCrTMsgAry.no(adjLineIdx).mdseCd.getValue());

                if ((AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue()) && !coaMdseTpCdFrt.equals(coaMdseTpCd))
                        || (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue()) && coaMdseTpCdFrt.equals(coaMdseTpCd))) {
                    break;
                }
            }
            if (adjLineIdx < 0) {
                // START 2023/03/10 D.Mamaril [QC#61119,MOD]
                //adjLineIdx = dsInvSlsCrTMsgAry.getValidCount() - 1;
                adjLineIdx = largestAmtIdx;
                // END 2023/03/10 D.Mamaril [QC#61119,MOD]
            }
            DS_INV_SLS_CRTMsg insDsInvSlsCrTMsg = insDsInvSlsCrTMsgList.get(adjLineIdx);
            // END   2019/06/05 [QC#50631, MOD]

            // set adjusted amount to TMsg
            if (adjustDealSlsCrAmt.compareTo(BigDecimal.ZERO) != 0) {
                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealSlsCrAmt, insDsInvSlsCrTMsg.dealSlsCrAmt.getValue().add(adjustDealSlsCrAmt));
            }
            if (adjustFuncSlsCrAmt.compareTo(BigDecimal.ZERO) != 0) {
                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcSlsCrAmt, insDsInvSlsCrTMsg.funcSlsCrAmt.getValue().add(adjustFuncSlsCrAmt));
            }
            // START 2019/06/19 H.Ikeda [QC#50785, DEL]
//            if (adjustDealOrigDfrdAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealOrigDfrdAmt, insDsInvSlsCrTMsg.dealOrigDfrdAmt.getValue().add(adjustDealOrigDfrdAmt));//
//            }
//            if (adjustFuncOrigDfrdAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcOrigDfrdAmt, insDsInvSlsCrTMsg.funcOrigDfrdAmt.getValue().add(adjustFuncOrigDfrdAmt));//
//            }
//            if (adjustDealDfrdBalAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealDfrdBalAmt, insDsInvSlsCrTMsg.dealDfrdBalAmt.getValue().add(adjustDealDfrdBalAmt));//
//            }
//            if (adjustFuncDfrdBalAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcDfrdBalAmt, insDsInvSlsCrTMsg.funcDfrdBalAmt.getValue().add(adjustFuncDfrdBalAmt));//
//            }
//            if (adjustDealSchdRevAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealSchdRevAmt, insDsInvSlsCrTMsg.dealSchdRevAmt.getValue().add(adjustDealSchdRevAmt));//
//            }
//            if (adjustFuncSchdRevAmt.compareTo(BigDecimal.ZERO) != 0) {
//                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcSchdRevAmt, insDsInvSlsCrTMsg.funcSchdRevAmt.getValue().add(adjustFuncSchdRevAmt));//
//            }
            // END   2019/06/19 H.Ikeda [QC#50785, DEL]
        }
        // insert
        if (!insDsInvSlsCrTMsgList.isEmpty()) {
            int insCnt = S21FastTBLAccessor.insert(insDsInvSlsCrTMsgList.toArray(new DS_INV_SLS_CRTMsg[0]));
            if (insCnt != insDsInvSlsCrTMsgList.size()) {
                bizMsg.setMessageInfo(NFCM0782E, new String[] {"DS_INV_SLS_CR" });
                return false;
            }
        }
        return true;
    }

    /**
     * insertInvPrmoInfo
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @return boolean
     */
    public static boolean insertInvPrmoInfo(NFCL3070CMsg bizMsg, String invNum) {
        // get original TMsg Array
        INV_PRMO_INFOTMsg tMsg = new INV_PRMO_INFOTMsg();

        if (bizMsg.xxPgFlg_T.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            // START 2018/08/29 S.Ohki [QC#27887, MOD]
//            tMsg.setSQLID("003");
            tMsg.setSQLID("004");
            // END 2018/08/29 S.Ohki [QC#27887, MOD]
            tMsg.setConditionValue("invBolLineNum01", bizMsg.invBolLineNum.getValue());
            tMsg.setConditionValue("invLineNum01", bizMsg.invLineNum.getValue());
            // START 2018/08/29 S.Ohki [QC#27887, DEL]
//            tMsg.setConditionValue("invLineSubNum01", bizMsg.invLineSubNum.getValue());
//            tMsg.setConditionValue("invLineSubTrxNum01", bizMsg.invLineSubTrxNum.getValue());
            // END 2018/08/29 S.Ohki [QC#27887, DEL]

        } else {
            tMsg.setSQLID("001");
        }

        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("invNum01", bizMsg.origInvNum.getValue());

        INV_PRMO_INFOTMsgArray invPrmoInfoTMsgAry = (INV_PRMO_INFOTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (invPrmoInfoTMsgAry.length() == 0) {
            // MOD 2017/02/07 [QC#17464, START]
            // bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_PRMO_INFO" });
            // return false;
            return true;
            // MOD 2017/02/07 [QC#17464, END]
        }

        // set original TMsg Array to copy to TMsg
        List<INV_PRMO_INFOTMsg> insInvPrmoInfoTMsgList = new ArrayList<INV_PRMO_INFOTMsg>();
        for (int i = 0; i < invPrmoInfoTMsgAry.getValidCount(); i++) {
            INV_PRMO_INFOTMsg insInvPrmoInfoTMsg = new INV_PRMO_INFOTMsg();

            EZDTMsg.copy(invPrmoInfoTMsgAry.get(i), null, insInvPrmoInfoTMsg, null);
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoTMsg.invPrmoInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRMO_INFO_SQ));
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoTMsg.invNum, invNum);
            // START 2019/09/04 [QC#52945, ADD]
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPgFlg_T.getValue())) {
                ZYPEZDItemValueSetter.setValue(insInvPrmoInfoTMsg.invBolLineNum, INIT_INV_BOL_LINE_NUM);
            }
            // END   2019/09/04 [QC#52945, ADD]

            insInvPrmoInfoTMsgList.add(insInvPrmoInfoTMsg);
        }

        // insert
        if (!insInvPrmoInfoTMsgList.isEmpty()) {
            int insCnt = S21FastTBLAccessor.insert(insInvPrmoInfoTMsgList.toArray(new INV_PRMO_INFOTMsg[0]));
            if (insCnt != insInvPrmoInfoTMsgList.size()) {
                bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_PRMO_INFO" });
                return false;
            }
        }
        return true;
    }

    /**
     * insertAjeInvAcctDist
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @param acctDt String
     * @param calPct BigDecimal
     * @param slsCrPkMap Map<BigDecimal, BigDecimal>
     * @return boolean
     */
    public static boolean insertAjeInvAcctDist(NFCL3070CMsg bizMsg, String invNum, String acctDt, BigDecimal calPct, Map<BigDecimal, BigDecimal> slsCrPkMap, Map<BigDecimal, String> dfrdSlsCrPkMap) {

        // START 2019/03/25 E.Kameishi [QC#30847,MOD]
        // START 2018/06/04 E.Kameishi [QC#25191,MOD]
//        if (dfrdSlsCrPkMap.size() > 0) {
            INVTMsg invTmsg = new INVTMsg();

            setValue(invTmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            setValue(invTmsg.invNum, invNum);
            invTmsg = (INVTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(invTmsg);

            ZYPEZDItemValueSetter.setValue(invTmsg.fnlzInvFlg, ZYPConstant.FLG_OFF_N);
            EZDTBLAccessor.update(invTmsg);

            if (!callAcctDistAPI(bizMsg, invNum)) {
                return false;
            } else {
                return true;
            }
//        } else {
//            // get original TMsg Array
//            AJE_INV_ACCT_DISTTMsg tMsg = new AJE_INV_ACCT_DISTTMsg();
//
//            if (bizMsg.xxPgFlg_T.getValue().equals(ZYPConstant.FLG_ON_Y)) {
//                // START 2018/08/29 S.Ohki [QC#27887, MOD]
////                tMsg.setSQLID("002");
//                tMsg.setSQLID("005");
//                // END 2018/08/29 S.Ohki [QC#27887, MOD]
//
//                tMsg.setConditionValue("invBolLineNum01", bizMsg.invBolLineNum.getValue());
//                tMsg.setConditionValue("invLineNum01", bizMsg.invLineNum.getValue());
//                // START 2018/08/29 S.Ohki [QC#27887, DEL]
////                tMsg.setConditionValue("invLineSubNum01", bizMsg.invLineSubNum.getValue());
////                tMsg.setConditionValue("invLineSubTrxNum01", bizMsg.invLineSubTrxNum.getValue());
//                // END 2018/08/29 S.Ohki [QC#27887, DEL]
//
//            } else {
//                tMsg.setSQLID("001");
//            }
//
//            tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//            tMsg.setConditionValue("invNum01", bizMsg.origInvNum.getValue());
//
//            AJE_INV_ACCT_DISTTMsgArray ajeInvAcctDistTMsgAry = (AJE_INV_ACCT_DISTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
//            if (ajeInvAcctDistTMsgAry.length() == 0) {
//                bizMsg.setMessageInfo(NFCM0782E, new String[] {"AJE_INV_ACCT_DIST" });
//                return false;
//            }
// 
//            // initialize amounts
//            BigDecimal jrnlDealTotAmt = BigDecimal.ZERO;
//            BigDecimal jrnlDealCalTotAmt = BigDecimal.ZERO;
//            BigDecimal jrnlFuncTotAmt = BigDecimal.ZERO;
//            BigDecimal jrnlFuncCaltotAmt = BigDecimal.ZERO;
//            // set original TMsg Array to copy to TMsg
//            List<AJE_INV_ACCT_DISTTMsg> ajeInvAcctDistTMsgList = new ArrayList<AJE_INV_ACCT_DISTTMsg>();
//            // START 2018/07/11 E.Kameishi [QC#27182,MOD]
//            // START 2018/03/20 E.Kameishi [QC#24915,MOD]
//            // START 2018/05/23 E.Kameishi [QC#21841,DEL]
//            //boolean frtCratFlgDr = false;
//            //boolean frtCratFlgCr = false;
//            // END 2018/05/23 E.Kameishi [QC#21841,DEL]
//            boolean taxCratFlgDr = false;
//            boolean taxCratFlgCr = false;
//            boolean setFlg = false;
//            // START 2018/05/23 E.Kameishi [QC#21841,DEL]
//            String preInvBolNum = null;
//            // END 2018/05/23 E.Kameishi [QC#21841,DEL]
//            // END 2018/07/11 E.Kameishi [QC#27182,MOD]
//            String preInvLineNum = null;
//
//            for (int i = 0; i < ajeInvAcctDistTMsgAry.getValidCount(); i++) {
//                if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_OFF_N)
//                        || RQST_TP_REBILL_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
//                    setFlg = true;
//                } else {
//                    setFlg = false;
//                }
//                AJE_INV_ACCT_DISTTMsg ajeInvAcctDistTMsg = new AJE_INV_ACCT_DISTTMsg();
//
//                EZDTMsg.copy(ajeInvAcctDistTMsgAry.get(i), null, ajeInvAcctDistTMsg, null);
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.ajeInvAcctDistPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AJE_INV_ACCT_DIST_SQ));
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.invNum, invNum);
//
//                BigDecimal newSlsCrPk  = slsCrPkMap.get(ajeInvAcctDistTMsgAry.no(i).dsInvSlsCrPk.getValue());
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.dsInvSlsCrPk, newSlsCrPk);
//                ajeInvAcctDistTMsg.jrnlCratDt.clear();
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.glDt, acctDt);
//                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.procStsCd, ZYPConstant.FLG_OFF_0);
//                // START 2018/08/21 E.Kameishi [QC#27688,ADD]
//                if ((RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && (hasValue(bizMsg.arAutoCashAppFlg) && ZYPConstant.FLG_ON_Y.equals(bizMsg.arAutoCashAppFlg.getValue())))
//                        || (RQST_TP_BOTH.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPgFlg_C.getValue()))) {
//                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.procStsCd, PROC_STS.COMPLEATED);
//                }
//                // END 2018/08/21 E.Kameishi [QC#27688,ADD]
//                // not Tax or Freight
//                if (!(ajeInvAcctDistTMsgAry.no(i).ajeInvAcctClsCd.getValue().equals(AJE_LINE_IND_TP.FRT_19) || ajeInvAcctDistTMsgAry.no(i).ajeInvAcctClsCd.getValue().equals(AJE_LINE_IND_TP.TAX_20))) {
//                    if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y)) {
//                        ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, ajeInvAcctDistTMsgAry.no(i).jrnlDealAmt.getValue().negate());
//                        ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, ajeInvAcctDistTMsgAry.no(i).jrnlFuncAmt.getValue().negate());
//                        setFlg = true;
//                    }
//                    if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
//                        if (AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue()) || !hasValue(bizMsg.arCrTpCd.getValue())) {
//                            // calculate amount
//                            BigDecimal jrnlDealCalAmt = calculateAmount(bizMsg, ajeInvAcctDistTMsgAry.no(i).jrnlDealAmt.getValue(), calPct);
//                            BigDecimal jrnlFuncCalAmt = calculateAmount(bizMsg, ajeInvAcctDistTMsgAry.no(i).jrnlFuncAmt.getValue(), calPct);
//                            // calculate amount to TMsg
//                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, jrnlDealCalAmt.negate());
//                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, jrnlFuncCalAmt.negate());
//                            setFlg = true;
//                            // set original amount and calculated amount
//                            if ("D".equals(ajeInvAcctDistTMsgAry.no(i).drCrTpCd.getValue())) { // Add 2018/02/27 S21_NA#24481
//                                jrnlDealTotAmt = jrnlDealTotAmt.add(addAmount(ajeInvAcctDistTMsgAry.no(i).jrnlDealAmt.getValue()));
//                                jrnlDealCalTotAmt = jrnlDealCalTotAmt.add(jrnlDealCalAmt);
//                                jrnlFuncTotAmt = jrnlFuncTotAmt.add(addAmount(ajeInvAcctDistTMsgAry.no(i).jrnlFuncAmt.getValue()));
//                                jrnlFuncCaltotAmt = jrnlFuncCaltotAmt.add(jrnlFuncCalAmt);
//                                setFlg = true;
//                            } // Add 2018/02/27 S21_NA#24481
//                        } else {
//                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, BigDecimal.ZERO);
//                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, BigDecimal.ZERO);
//                            setFlg = true;
//                        }
//                    }
//                // Tax or Freight
//                } else {
//                    // get TaxAmt or FrtAmt From INV_BOL
//                    INV_BOLTMsg bolTMsg = new INV_BOLTMsg();
//                    bolTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//                    bolTMsg.invNum.setValue(invNum);
//                    bolTMsg.invBolLineNum.setValue(ajeInvAcctDistTMsgAry.no(i).invBolLineNum.getValue());
//
//                    INV_BOLTMsg invBolTMsg = (INV_BOLTMsg) S21CacheTBLAccessor.findByKey(bolTMsg);
//                    if (invBolTMsg == null) {
//                        bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_BOL" });
//                        return false;
//                    }
//                    // for Tax
//                    if (ajeInvAcctDistTMsgAry.no(i).ajeInvAcctClsCd.getValue().equals(AJE_LINE_IND_TP.TAX_20)) {
//                        INV_LINETMsg lineTMsg = new INV_LINETMsg();
//                        lineTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//                        lineTMsg.invNum.setValue(invNum);
//                        lineTMsg.invBolLineNum.setValue(ajeInvAcctDistTMsgAry.no(i).invBolLineNum.getValue());
//                        lineTMsg.invLineNum.setValue(ajeInvAcctDistTMsgAry.no(i).invLineNum.getValue());
//                        lineTMsg.invLineSubNum.setValue(ajeInvAcctDistTMsgAry.no(i).invLineSubNum.getValue());
//                        lineTMsg.invLineSubTrxNum.setValue(ajeInvAcctDistTMsgAry.no(i).invLineSubTrxNum.getValue());
//
//                        INV_LINETMsg invLineTMsg = (INV_LINETMsg) EZDTBLAccessor.findByKey(lineTMsg);
//
//                        if (invLineTMsg == null) {
//                            bizMsg.setMessageInfo(NFCM0782E, new String[] {"INV_LINE" });
//                            return false;
//                        }
//                        // START 2018/07/11 E.Kameishi [QC#27182,MOD]
//                        // START 2018/05/23 E.Kameishi [QC#21841,DEL]
//                        if (preInvBolNum == null || !preInvBolNum.equals(ajeInvAcctDistTMsgAry.no(i).invBolLineNum.getValue())) {
//                            //frtCratFlgDr = false;
//                            //frtCratFlgCr = false;
//                            taxCratFlgDr = false;
//                            taxCratFlgCr = false;
//                        }
//                        if ((preInvBolNum == null || preInvBolNum.equals(ajeInvAcctDistTMsgAry.no(i).invBolLineNum.getValue()))
//                                && (preInvLineNum == null || !preInvLineNum.equals(ajeInvAcctDistTMsgAry.no(i).invLineNum.getValue()))) {
//                        //if (preInvLineNum == null || !preInvLineNum.equals(ajeInvAcctDistTMsgAry.no(i).invLineNum.getValue())) {
//                            taxCratFlgDr = false;
//                            taxCratFlgCr = false;
//                        }
//                        // END 2018/05/23 E.Kameishi [QC#21841,DEL]
//                        // END 2018/07/11 E.Kameishi [QC#27182,MOD]
//                        // Create AJE_INV_ACCT_DIST for Tax Per INV_LINE
//                        if (!taxCratFlgDr && "D".equals(ajeInvAcctDistTMsgAry.no(i).drCrTpCd.getValue())) {
//                            if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y)) {
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, invLineTMsg.invLineDealTaxAmt.getValue().negate());
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, invLineTMsg.invLineFuncTaxAmt.getValue().negate());
//                                setFlg = true;
//                            } else {
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, invLineTMsg.invLineDealTaxAmt.getValue());
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, invLineTMsg.invLineFuncTaxAmt.getValue());
//                                setFlg = true;
//                            }
//                            taxCratFlgDr = true;
//                            // START 2018/07/11 E.Kameishi [QC#27182,MOD]
//                            // START 2018/05/23 E.Kameishi [QC#21841,DEL]
//                            preInvBolNum = ajeInvAcctDistTMsgAry.no(i).invBolLineNum.getValue();
//                            // END 2018/05/23 E.Kameishi [QC#21841,DEL]
//                            // END 2018/07/11 E.Kameishi [QC#27182,MOD]
//                            preInvLineNum = ajeInvAcctDistTMsgAry.no(i).invLineNum.getValue();
//                            ajeInvAcctDistTMsgList.add(ajeInvAcctDistTMsg);
//                            continue;
//                        }
//                        if (!taxCratFlgCr && "C".equals(ajeInvAcctDistTMsgAry.no(i).drCrTpCd.getValue())) {
//                            if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y)) {
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, invLineTMsg.invLineDealTaxAmt.getValue().negate());
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, invLineTMsg.invLineFuncTaxAmt.getValue().negate());
//                                setFlg = true;
//                            } else {
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, invLineTMsg.invLineDealTaxAmt.getValue());
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, invLineTMsg.invLineFuncTaxAmt.getValue());
//                                setFlg = true;
//                            }
//                            taxCratFlgCr = true;
//                            // START 2018/07/11 E.Kameishi [QC#27182,MOD]
//                            // START 2018/05/23 E.Kameishi [QC#21841,DEL]
//                            preInvBolNum = ajeInvAcctDistTMsgAry.no(i).invBolLineNum.getValue();
//                            // END 2018/05/23 E.Kameishi [QC#21841,DEL]
//                            // END 2018/07/11 E.Kameishi [QC#27182,MOD]
//                            preInvLineNum = ajeInvAcctDistTMsgAry.no(i).invLineNum.getValue();
//                            ajeInvAcctDistTMsgList.add(ajeInvAcctDistTMsg);
//                            continue;
//                        }
//                        // START 2018/05/23 E.Kameishi [QC#21841,DEL]
//                        // Create AJE_INV_ACCT_DIST for Freight Tax Per INV_BOL
//    //                    if (!frtCratFlgDr && taxCratFlgDr && "D".equals(ajeInvAcctDistTMsgAry.no(i).drCrTpCd.getValue()) && BigDecimal.ZERO.compareTo(invBolTMsg.frtDealTaxAmt.getValue()) < 0) {
//    //                        if ((RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue()))
//    //                                || RQST_TP_REBILL_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0
//    //                                || AR_CR_REBIL_TP.CREDIT_AND_REBILL.equals(bizMsg.arCrRebilTpCd.getValue())) {
//    //                            if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y)) {
//    //                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, invBolTMsg.frtDealTaxAmt.getValue().negate());
//    //                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, invBolTMsg.frtFuncTaxAmt.getValue().negate());
//    //                                setFlg = true;
//    //                            } else {
//    //                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, invBolTMsg.frtDealTaxAmt.getValue());
//    //                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, invBolTMsg.frtFuncTaxAmt.getValue());
//    //                                setFlg = true;
//    //                            }
//    //                            frtCratFlgDr = true;
//    //                        }
//    //                    }
//    //                    if (!frtCratFlgCr && taxCratFlgCr && "C".equals(ajeInvAcctDistTMsgAry.no(i).drCrTpCd.getValue()) && BigDecimal.ZERO.compareTo(invBolTMsg.frtDealTaxAmt.getValue()) < 0) {
//    //                        if ((RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue()))
//    //                                || RQST_TP_REBILL_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0
//    //                                || AR_CR_REBIL_TP.CREDIT_AND_REBILL.equals(bizMsg.arCrRebilTpCd.getValue())) {
//    //                            if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y)) {
//    //                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, invBolTMsg.frtDealTaxAmt.getValue().negate());
//    //                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, invBolTMsg.frtFuncTaxAmt.getValue().negate());
//    //                                setFlg = true;
//    //                            } else {
//    //                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, invBolTMsg.frtDealTaxAmt.getValue());
//    //                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, invBolTMsg.frtFuncTaxAmt.getValue());
//    //                                setFlg = true;
//    //                            }
//    //                            frtCratFlgCr = true;
//    //                        }
//    //                    }
//    //                    preInvBolNum = ajeInvAcctDistTMsgAry.no(i).invBolLineNum.getValue();
//                        // END 2018/05/23 E.Kameishi [QC#21841,DEL]
//                        preInvLineNum = ajeInvAcctDistTMsgAry.no(i).invLineNum.getValue();
//                    }
//                    // for Freight
//                    if (ajeInvAcctDistTMsgAry.no(i).ajeInvAcctClsCd.getValue().equals(AJE_LINE_IND_TP.FRT_19)) {
//                        // START 2018/05/23 E.Kameishi [QC#21841,MOD]
//                        DS_INV_SLS_CRTMsg dsInvSlsCrTMsg = new DS_INV_SLS_CRTMsg();
//                        dsInvSlsCrTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//                        // START 2018/11/02 T.Ogura [QC#29046,MOD]
////                        dsInvSlsCrTMsg.dsInvSlsCrPk.setValue(ajeInvAcctDistTMsgAry.no(i).dsInvSlsCrPk.getValue());
//                        dsInvSlsCrTMsg.dsInvSlsCrPk.setValue(newSlsCrPk);
//                        // END   2018/11/02 T.Ogura [QC#29046,MOD]
//
//                        dsInvSlsCrTMsg = (DS_INV_SLS_CRTMsg) EZDTBLAccessor.findByKey(dsInvSlsCrTMsg);
//                        if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y)) {
//                            //ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, invBolTMsg.shipDealFrtAmt.getValue().negate());
//                            //ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, invBolTMsg.shipFuncFrtAmt.getValue().negate());
//                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, dsInvSlsCrTMsg.dealSlsCrAmt.getValue().negate());
//                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, dsInvSlsCrTMsg.funcSlsCrAmt.getValue().negate());
//                            setFlg = true;
//                        } else {
//                            //ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, invBolTMsg.shipDealFrtAmt.getValue());
//                            //ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, invBolTMsg.shipFuncFrtAmt.getValue());
//                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, dsInvSlsCrTMsg.dealSlsCrAmt.getValue());
//                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, dsInvSlsCrTMsg.funcSlsCrAmt.getValue());
//                            setFlg = true;
//                        }
//                        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
//                            if (AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue()) || AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue())) {
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, BigDecimal.ZERO);
//                                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, BigDecimal.ZERO);
//                                setFlg = true;
//                            }
//                        }
//                        // END 2018/05/23 E.Kameishi [QC#21841,MOD]
//                    }
//                }
//                if (setFlg) {
//                    ajeInvAcctDistTMsgList.add(ajeInvAcctDistTMsg);
//                }
//            }
//            // END 2017/07/05 E.Kameishi [QC#19757,MOD]
//            if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
//                if (AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue()) || !hasValue(bizMsg.arCrTpCd.getValue())) {
//                    // adjustment of tolerance
//                    BigDecimal adjustJrnlDealAmt = calculateAmount(bizMsg, jrnlDealTotAmt, calPct).subtract(jrnlDealCalTotAmt);
//                    BigDecimal adjustJrnlFuncAmt = calculateAmount(bizMsg, jrnlFuncTotAmt, calPct).subtract(jrnlFuncCaltotAmt);
//
//                    AJE_INV_ACCT_DISTTMsg ajeInvAcctDistTMsg = ajeInvAcctDistTMsgList.get(ajeInvAcctDistTMsgList.size() - 1);
//                    // ADD START 2018/03/01 S21_NA#24515
//                    AJE_INV_ACCT_DISTTMsg anotherTMsg = new AJE_INV_ACCT_DISTTMsg();
//                    if (adjustJrnlDealAmt.compareTo(BigDecimal.ZERO) != 0 || adjustJrnlFuncAmt.compareTo(BigDecimal.ZERO) != 0) {
//
//                        for (int i = ajeInvAcctDistTMsgList.size() - 1; i >= 0; i--) {
//                            anotherTMsg = ajeInvAcctDistTMsgList.get(i);
//                            if (!S21StringUtil.isEquals(anotherTMsg.drCrTpCd.getValue(),                  ajeInvAcctDistTMsg.drCrTpCd.getValue()) //
//                                    && S21StringUtil.isEquals(anotherTMsg.invLineSubTrxNum.getValue(),    ajeInvAcctDistTMsg.invLineSubTrxNum.getValue()) //
//                                    && S21StringUtil.isEquals(anotherTMsg.invLineSubNum.getValue(),       ajeInvAcctDistTMsg.invLineSubNum.getValue()) //
//                                    && S21StringUtil.isEquals(anotherTMsg.invLineNum.getValue(),          ajeInvAcctDistTMsg.invLineNum.getValue()) //
//                                    && S21StringUtil.isEquals(anotherTMsg.invBolLineNum.getValue(),       ajeInvAcctDistTMsg.invBolLineNum.getValue()) //
//                                    && S21StringUtil.isEquals(anotherTMsg.invNum.getValue(),              ajeInvAcctDistTMsg.invNum.getValue())) {
//                                break;
//                            }
//                        }
//                    }
//                    // ADD END 2018/03/01 S21_NA#24515
//                    // set adjusted amount to TMsg
//                    if (adjustJrnlDealAmt.compareTo(BigDecimal.ZERO) != 0) {
//                        ZYPEZDItemValueSetter.setValue(anotherTMsg.jrnlDealAmt, anotherTMsg.jrnlDealAmt.getValue().add(adjustJrnlDealAmt.negate())); // ADD 2018/03/01 S21_NA#24515
//                        ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, ajeInvAcctDistTMsg.jrnlDealAmt.getValue().add(adjustJrnlDealAmt.negate()));
//                    }
//                    if (adjustJrnlFuncAmt.compareTo(BigDecimal.ZERO) != 0) {
//                        ZYPEZDItemValueSetter.setValue(anotherTMsg.jrnlFuncAmt, anotherTMsg.jrnlFuncAmt.getValue().add(adjustJrnlFuncAmt.negate())); // ADD 2018/03/01 S21_NA#24515
//                        ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, ajeInvAcctDistTMsg.jrnlFuncAmt.getValue().add(adjustJrnlFuncAmt.negate()));
//                    }
//                }
//            }
//            // insert
//            if (!ajeInvAcctDistTMsgList.isEmpty()) {
//                int insCnt = S21FastTBLAccessor.insert(ajeInvAcctDistTMsgList.toArray(new AJE_INV_ACCT_DISTTMsg[0]));
//                if (insCnt != ajeInvAcctDistTMsgList.size()) {
//                    bizMsg.setMessageInfo(NFCM0782E, new String[] {"AJE_INV_ACCT_DIST" });
//                    return false;
//                }
//            }
//            // START 2018/02/08 E.Kameishi [QC#23551,ADD]
//            Map<String, String> ajeLineidxMap = new HashMap<String, String>();
//            S21SsmEZDResult ssmResult = NFCL3070Query.getInstance().getAjeInvAcctDist(bizMsg, invNum);
//
//            if (ssmResult.isCodeNormal()) {
//                List<Map> ajeInvAcctDistList = (List) ssmResult.getResultObject();
//                for (Map ajeInvAcctDist : ajeInvAcctDistList) {
//                    if (!ajeInvAcctDist.isEmpty()) {
//                        BigDecimal ajeInvAcctDistPk = (BigDecimal) ajeInvAcctDist.get("AJE_INV_ACCT_DIST_PK");
//                        BigDecimal slsCrPk = (BigDecimal) ajeInvAcctDist.get("DS_INV_SLS_CR_PK");
//                        String lineNumStr = (String) ajeInvAcctDist.get("AJE_LINE_IDX_NUM");
//                        String drCrTp = (String) ajeInvAcctDist.get("DR_CR_TP_CD");
//                        String mapKey = S21StringUtil.concatStrings(slsCrPk, drCrTp);
//
//                        if (ajeLineidxMap.containsKey(mapKey)) {
//                            String prevLineNum = ajeLineidxMap.get(mapKey);
//                            String lineNum = String.format("%02d", Integer.parseInt(prevLineNum) + 1);
//
//                            AJE_INV_ACCT_DISTTMsg ajeInvAcctDistTmsg = new AJE_INV_ACCT_DISTTMsg();
//                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
//                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTmsg.ajeInvAcctDistPk, ajeInvAcctDistPk);
//                            ajeInvAcctDistTmsg = (AJE_INV_ACCT_DISTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(ajeInvAcctDistTmsg);
//                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTmsg.ajeLineIdxNum, lineNum);
//                            EZDTBLAccessor.update(ajeInvAcctDistTmsg);
//
//                            ajeLineidxMap.put(mapKey, lineNum);
//                        } else {
//                            ajeLineidxMap.put(mapKey, lineNumStr);
//                        }
//                    }
//                }
//            }
//            // END 2018/02/08 E.Kameishi [QC#23551,ADD]
//            return true;
//        }
        // END 2018/06/04 E.Kameishi [QC#25191,MOD]
        // END 2019/03/25 E.Kameishi [QC#30847,MOD]
    }

    /**
     * insertMeterInvoice
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean insertMeterInvoice(NFCL3070CMsg bizMsg, String invNum) {
        DS_INV_MTR_DTLTMsg dsInvMtrDtlTMsg = new DS_INV_MTR_DTLTMsg();
        dsInvMtrDtlTMsg.setSQLID("001");
        dsInvMtrDtlTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        dsInvMtrDtlTMsg.setConditionValue("invNum01", bizMsg.origInvNum.getValue());

        DS_INV_MTR_DTLTMsgArray dsInvMtrDtlTMsgAry = (DS_INV_MTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(dsInvMtrDtlTMsg);
        List<DS_INV_MTR_DTLTMsg> insDsInvMtrDtlTMsgList = new ArrayList<DS_INV_MTR_DTLTMsg>();

        // set DS_INV_MTR_DTL
        if (dsInvMtrDtlTMsgAry.length() > 0) {
            for (int i = 0; i < dsInvMtrDtlTMsgAry.getValidCount(); i++) {
                DS_INV_MTR_DTLTMsg insDsInvMtrDtlTMsg = new DS_INV_MTR_DTLTMsg();

                EZDTMsg.copy(dsInvMtrDtlTMsgAry.get(i), null, insDsInvMtrDtlTMsg, null);
                ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.dsInvMtrDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_INV_MTR_DTL_SQ));
                ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.invNum, invNum);

                insDsInvMtrDtlTMsgList.add(insDsInvMtrDtlTMsg);
            }

        // if DS_INV_MTR_DTL doesn't exists, set SVC_INV_LINE_MTR
        } else {
            S21SsmEZDResult ssmResult = NFCL3070Query.getInstance().getServiceInvMeter(bizMsg);

            // has more than one results
            if (ssmResult.isCodeNormal()) {
                List<Map> resultSvcInvMtrList = (List) ssmResult.getResultObject();

                for (Map map : resultSvcInvMtrList) {
                    if (!map.isEmpty()) {
                        DS_INV_MTR_DTLTMsg insDsInvMtrDtlTMsg = new DS_INV_MTR_DTLTMsg();
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.dsInvMtrDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_INV_MTR_DTL_SQ));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.invNum, invNum);
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.invBolLineNum, (String) map.get("INV_BOL_LINE_NUM"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.invLineNum, (String) map.get("INV_LINE_NUM"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.invLineSubNum, (String) map.get("INV_LINE_SUB_NUM"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.invLineSubTrxNum, (String) map.get("INV_LINE_SUB_TRX_NUM"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.bllgPerFromDt, (String) map.get("BLLG_PER_FROM_DT"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.bllgPerToDt, (String) map.get("BLLG_PER_THRU_DT"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.serNum, (String) map.get("SER_NUM"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.mtrLbDescTxt, (String) map.get("MTR_LB_DESC_TXT"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.prevTotCopyQty, (BigDecimal) map.get("PREV_TOT_COPY_QTY"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.totCopyQty, (BigDecimal) map.get("TOT_COPY_QTY"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.testCopyQty, (BigDecimal) map.get("TEST_COPY_QTY"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.contrMtrMultRate, (BigDecimal) map.get("CONTR_MTR_MULT_RATE"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.copyInclQty, (BigDecimal) map.get("COPY_INCL_QTY"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.bllgCopyQty, (BigDecimal) map.get("BLLG_COPY_QTY"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.xsMtrAmtRate, (BigDecimal) map.get("XS_MTR_AMT_RATE"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.mtrChrgDealAmt, (BigDecimal) map.get("MTR_CHRG_DEAL_AMT"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.mtrChrgFuncAmt, (BigDecimal) map.get("MTR_CHRG_FUNC_AMT"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.shipToCustCd, (String) map.get("SHIP_TO_CUST_CD"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.ccyCd, (String) map.get("CCY_CD"));
                        // START 2018/07/11 E.Kameishi [QC#26896,ADD]
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.svcContrMtrBllgPk, (BigDecimal) map.get("SVC_CONTR_MTR_BLLG_PK"));
                        ZYPEZDItemValueSetter.setValue(insDsInvMtrDtlTMsg.mtrLbCd, (String) map.get("MTR_LB_CD"));
                        // END 2018/07/11 E.Kameishi [QC#26896,ADD]

                        insDsInvMtrDtlTMsgList.add(insDsInvMtrDtlTMsg);
                    }
                }
            }
        }

        // insert
        if (!insDsInvMtrDtlTMsgList.isEmpty()) {
            int insCnt = S21FastTBLAccessor.insert(insDsInvMtrDtlTMsgList.toArray(new DS_INV_MTR_DTLTMsg[0]));
            if (insCnt != insDsInvMtrDtlTMsgList.size()) {
                bizMsg.setMessageInfo(NFCM0782E, new String[] {"DS_INV_MTR_DTL" });
                return false;
            }
        }
        return true;
    }

    /**
     * insertArCrRebill
     * 
     * @param bizMsg NFCL3070CMsg
     * @param origInvTMsg INVTMsg
     * @param copyToInvTMsg INVTMsg
     * @param invLineTMsg INV_LINETMsg
     * @return boolean
     */
    // START 2018/08/29 S.Ohki [QC#27887, MOD]
//    public static boolean insertArCrRebill(NFCL3070CMsg bizMsg, INVTMsg origInvTMsg, INVTMsg copyToInvTMsg) {
    public static boolean insertArCrRebill(NFCL3070CMsg bizMsg, INVTMsg origInvTMsg, INVTMsg copyToInvTMsg, INV_LINETMsg invLineTMsg) {
    // END 2018/08/29 S.Ohki [QC#27887, MOD]
        AR_CR_REBILTMsg insArCrRebilTMsg = new AR_CR_REBILTMsg();

        ZYPEZDItemValueSetter.setValue(bizMsg.arCrRebilPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_CR_REBIL_SQ));
        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.arCrRebilPk, bizMsg.arCrRebilPk);

        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.invNum, copyToInvTMsg.invNum);
        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.invTpCd, copyToInvTMsg.invTpCd);
        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.invBolLineNum, bizMsg.invBolLineNum);
        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.invLineNum, bizMsg.invLineNum);
        // START 2018/08/29 S.Ohki [QC#27887, MOD]
//        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.invLineSubNum, bizMsg.invLineSubNum);
//        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.invLineSubTrxNum, bizMsg.invLineSubTrxNum);
        if (invLineTMsg != null) {
        	ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.invLineSubNum, invLineTMsg.invLineSubNum);
            ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.invLineSubTrxNum, invLineTMsg.invLineSubTrxNum);
        }
        // END 2018/08/29 S.Ohki [QC#27887, MOD]

        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.billToCustCd, bizMsg.billToCustCd);

        if (ZYPCommonFunc.hasValue(bizMsg.billToCustCd)) {
            String billToLocNm = NFCL3070Query.getInstance().getBillToLocNm(bizMsg);
            if (ZYPCommonFunc.hasValue(billToLocNm)) {
                ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.billToLocNm, billToLocNm);
            }
        }

        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.shipToCustCd, bizMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.shipToLocNm, bizMsg.shipToLocNm);
        if (ZYPCommonFunc.hasValue(bizMsg.crRebilAmt_CO)) {
            ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.crRebilAmt, bizMsg.crRebilAmt_CO);
        } else {
            ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.crRebilAmt, bizMsg.crRebilAmt_CA);
        }

        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.arCrPct, bizMsg.xxTotInvPct);
        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.origInvNum, bizMsg.origInvNum);
        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.origInvAmt, bizMsg.origInvAmt);

        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.arCrTpCd, bizMsg.arCrTpCd);
        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.arCrRebilRsnCd, bizMsg.arCrRebilRsnCd);
        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.arCrRebilCmntTxt, bizMsg.arCrRebilCmntTxt);
        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.arCrRebilAddlCmntTxt, bizMsg.arCrRebilAddlCmntTxt);
        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.custIncdtId, bizMsg.custIncdtId);

        // START 2017/10/04 J.Kim [QC#21502, MOD]
        if (ZYPCommonFunc.hasValue(bizMsg.arAutoCashAppFlg)) {
            ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.arAutoCashAppFlg, bizMsg.arAutoCashAppFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.arAutoCashAppFlg, ZYPConstant.FLG_OFF_N);
        }
        // END 2017/10/04 J.Kim [QC#21502, MOD]
        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.prmCmntTxt, editComments(bizMsg));
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.arCrRebilRqstUsrId, userProfSvc.getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.arCrRebilRqstTs, ZYPDateUtil.getCurrentSystemTime(ZYPDateUtil.TYPE_YYYYMMDD));
        ZYPEZDItemValueSetter.setValue(insArCrRebilTMsg.arCrRebilTpCd, bizMsg.arCrRebilTpCd);

        // set arCrRebilPk
        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.arCrRebilPk_C, bizMsg.arCrRebilPk.getValue());
        } else if (RQST_TP_REBILL_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.arCrRebilPk_C, bizMsg.arCrRebilPk.getValue());
        // BOTH
        } else {
            if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.arCrRebilPk_C, bizMsg.arCrRebilPk.getValue());
            }
        }
        // insert
        S21FastTBLAccessor.insert(insArCrRebilTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insArCrRebilTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NFCM0782E, new String[] {"AR_CR_REBIL" });
            return false;
        }

        return true;
    }

    /**
     * getDsInvTpTMsg
     * 
     * @param bizMsg NFCL3070CMsg
     * @param dsInvTpCd String
     * @return DS_INV_TPTMsg
     */
    public static DS_INV_TPTMsg getDsInvTpTMsg(NFCL3070CMsg bizMsg, String dsInvTpCd) {
        DS_INV_TPTMsg tMsg = new DS_INV_TPTMsg();
        tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        tMsg.dsInvTpCd.setValue(dsInvTpCd);

        return (DS_INV_TPTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

    /**
     * getInvTMsg
     * 
     * @param bizMsg NFCL3070CMsg
     * @return INVTMsg
     */
    public static INVTMsg getInvTMsg(NFCL3070CMsg bizMsg) {
        INVTMsg tMsg = new INVTMsg();
        tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        tMsg.invNum.setValue(bizMsg.origInvNum.getValue());

        return (INVTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

    /**
     * getInvBolTMsg
     * 
     * @param bizMsg NFCL3070CMsg
     * @return tMsg INV_BOLTMsg
     */
    public static INV_BOLTMsg getInvBolTMsg(NFCL3070CMsg bizMsg) {
        INV_BOLTMsg tMsg = new INV_BOLTMsg();
        tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        tMsg.invNum.setValue(bizMsg.origInvNum.getValue());
        tMsg.invBolLineNum.setValue(bizMsg.invBolLineNum.getValue());

        return (INV_BOLTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

    /**
     * getInvLineTMsg
     * 
     * @param bizMsg NFCL3070CMsg
     * @return tMsg INV_LINETMsg
     */
    public static INV_LINETMsg getInvLineTMsg(NFCL3070CMsg bizMsg) {
    	 // START 2018/08/29 S.Ohki [QC#27887, MOD]
//        INV_LINETMsg tMsg = new INV_LINETMsg();
//        tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//        tMsg.invNum.setValue(bizMsg.origInvNum.getValue());
//        tMsg.invBolLineNum.setValue(bizMsg.invBolLineNum.getValue());
//        tMsg.invLineNum.setValue(bizMsg.invLineNum.getValue());
//        tMsg.invLineSubNum.setValue(bizMsg.invLineSubNum.getValue());
//        tMsg.invLineSubTrxNum.setValue(bizMsg.invLineSubTrxNum.getValue());
//
//        return (INV_LINETMsg) S21FastTBLAccessor.findByKey(tMsg);

        INV_LINETMsg tMsg = new INV_LINETMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("invNum01", bizMsg.origInvNum.getValue());
        tMsg.setConditionValue("invBolLineNum01", bizMsg.invBolLineNum.getValue());
        tMsg.setConditionValue("invLineNum01", bizMsg.invLineNum.getValue());

        INV_LINETMsgArray invLineTMsgAry = (INV_LINETMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (invLineTMsgAry == null || invLineTMsgAry.length() == 0) {
        	return null;
        }
        return invLineTMsgAry.no(0);
        // END 2018/08/29 S.Ohki [QC#27887, MOD]
    }

    /**
     * findArAcctDtInfo
     * 
     * @param bizMsg NFCL3070CMsg
     * @param subSysCd String
     * @return AR_ACCT_DTTMsg
     */
    public static AR_ACCT_DTTMsg findArAcctDtInfo(NFCL3070CMsg bizMsg, String subSysCd) {

        AR_ACCT_DTTMsg tMsg = new AR_ACCT_DTTMsg();

        tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        tMsg.subSysCd.setValue(subSysCd);
        tMsg.onlBatTpCd.setValue(ONL_BAT_TP);

        AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) S21FastTBLAccessor.findByKey(tMsg);

        return outMsg;
    }

    /**
     * isInvoiceLineNum
     * 
     * @param bizMsg NFCL3070CMsg
     * @return boolean
     */
    public static boolean isInvoiceLineNum(NFCL3070CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NFCL3070Query.getInstance().getPositionOfInvLine(bizMsg);

        if (ssmResult.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map> result = (List) ssmResult.getResultObject();

            if (result.size() == 1) {
                ZYPEZDItemValueSetter.setValue(bizMsg.invLineNum, (String) result.get(0).get("INV_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.invBolLineNum, (String) result.get(0).get("INV_BOL_LINE_NUM"));
                // START 2018/08/29 S.Ohki [QC#27887, DEL]
//                ZYPEZDItemValueSetter.setValue(bizMsg.invLineSubNum, (String) result.get(0).get("INV_LINE_SUB_NUM"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.invLineSubTrxNum, (String) result.get(0).get("INV_LINE_SUB_TRX_NUM"));
                // END 2018/08/29 S.Ohki [QC#27887, DEL]
                return true;
            }

            // Error if result is more than one
            if (result.size() > 1) {
                bizMsg.invLineNum.setErrorInfo(2, NFCM0791W);
                bizMsg.invBolLineNum.setErrorInfo(2, NFCM0791W);
                // START 2018/08/29 S.Ohki [QC#27887, DEL]
//                bizMsg.invLineSubNum.setErrorInfo(2, NFCM0791W);
//                bizMsg.invLineSubTrxNum.setErrorInfo(2, NFCM0791W);
                // END 2018/08/29 S.Ohki [QC#27887, DEL]
                bizMsg.actvFlg.setValue(ZYPConstant.FLG_ON_Y);
                return false;
            }

        }

        bizMsg.invLineNum.setErrorInfo(1, ZZZM9006E, new String[] {"Invoice line" });
        // START 2019/08/30 [QC#52945,MOD]
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.actvFlg.getValue())) {
//      if (ZYPConstant.FLG_ON_Y.equals(bizMsg.actvFlg)) {
        // END 2019/08/30 [QC#52945,MOD]
            bizMsg.invBolLineNum.setErrorInfo(1, ZZZM9006E, new String[] {"Invoice line" });
            // START 2018/08/29 S.Ohki [QC#27887, DEL]
//            bizMsg.invLineSubNum.setErrorInfo(1, ZZZM9006E, new String[] {"Invoice line" });
//            bizMsg.invLineSubTrxNum.setErrorInfo(1, ZZZM9006E, new String[] {"Invoice line" });
            // END 2018/08/29 S.Ohki [QC#27887, DEL]
        }
        return false;
    }

    /**
     * isCreditAmountLessThanOrigInv
     * 
     * @param crAmt BigDecimal
     * @param origInv BigDecimal
     * @return boolean
     */
    public static boolean isCreditAmountLessThanOrigInv(BigDecimal crAmt, BigDecimal origInv) {
        if (!ZYPCommonFunc.hasValue(crAmt) || !ZYPCommonFunc.hasValue(origInv)) {
            return false;
        }

        if (crAmt.compareTo(BigDecimal.ZERO) != 0) {
            if (crAmt.compareTo(origInv) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * isEqual
     * 
     * @param amt1 BigDecimal
     * @param amt2 BigDecimal
     * @return boolean
     */
    public static boolean isEqual(BigDecimal amt1, BigDecimal amt2) {
        if (!ZYPCommonFunc.hasValue(amt1) || !ZYPCommonFunc.hasValue(amt2)) {
            return false;
        }

        if (amt1.compareTo(amt2) > 0) {
            return false;
        }
        return true;
    }

    /**
     * isArCrRebilRsnCd
     * 
     * @param bizMsg NFCL3070CMsg
     * @return boolean
     */
    public static boolean isArCrRebilRsnCd(NFCL3070CMsg bizMsg) {
        //START 2018/05/30 E.Kameishi [QC#26229, MOD]
        S21SsmEZDResult ssmResult = NFCL3070Query.getInstance().getArCrRebilRsn(bizMsg);
        List<Map> arCrRebilRsnList = (List) ssmResult.getResultObject();
        if (arCrRebilRsnList.size() == 0) {
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.arCrRebilRsnCd, (String) arCrRebilRsnList.get(0).get("AR_CR_REBIL_RSN_CD"));
            return true;
        }
        
        //AR_CR_REBIL_RSNTMsg tMsg = new AR_CR_REBIL_RSNTMsg();
        //tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        //tMsg.arCrRebilRsnCd.setValue(bizMsg.arCrRebilRsnCd.getValue());

        //return S21CacheTBLAccessor.findByKey(tMsg) != null;
        //END 2018/05/30 E.Kameishi [QC#26229, MOD]
    }

    /**
     * editComments
     * 
     * @param bizMsg NFCL3070CMsg
     * @return String
     */
    public static String editComments(NFCL3070CMsg bizMsg) {
        StringBuilder sb = new StringBuilder();

        // edit comments
        if (DS_BIZ_AREA.CONTRACTS.equals(bizMsg.dsBizAreaCd.getValue())) {
            sb.append(bizMsg.dsContrNum.getValue());
            sb.append("/");
        }
        sb.append(bizMsg.custIncdtId.getValue());
        sb.append("/");
        sb.append(bizMsg.origInvNum.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.arCrRebilCmntTxt)) {
            sb.append("/");
            sb.append(bizMsg.arCrRebilCmntTxt.getValue());
        }
        // cut end of comments if its over max digits
        int lengthEventId = new AR_CR_REBILTMsg().getAttr("prmCmntTxt").getDigit();
        if (sb.toString().length() > lengthEventId) {
            return sb.toString().substring(0, lengthEventId);
        }
        return sb.toString();
    }

    /**
     * getFuncCcyScale
     * 
     * @param bizMsg NFCL3070CMsg
     * @return boolean
     */
    public static boolean getFuncCcyScale(NFCL3070CMsg bizMsg) {
        GLBL_CMPYTMsg glblTMsg = new GLBL_CMPYTMsg();
        glblTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());

        glblTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblTMsg);
        if (glblTMsg == null) {
            return false;
        }
        // getFuncCcyScale
        CCYTMsg ccyTMsg = new CCYTMsg();
        ccyTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        ccyTMsg.ccyCd.setValue(glblTMsg.stdCcyCd.getValue());

        ccyTMsg = (CCYTMsg) S21CodeTableAccessor.findByKey(ccyTMsg);
        if (ccyTMsg == null || ccyTMsg.aftDeclPntDigitNum.getValue() == null) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.aftDeclPntDigitNum, ccyTMsg.aftDeclPntDigitNum.getValue());
        return true;
    }

    /**
     * calculatePct
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invLineTMsg INV_LINETMsg
     * @param invTMsg INVTMsg
     * @param invBolTMsg INV_BOLTMsg
     * @return calPct BigDecimal
     */
    public static BigDecimal calculatePct(NFCL3070CMsg bizMsg, INV_LINETMsg invLineTMsg, INVTMsg invTMsg, INV_BOLTMsg invBolTMsg) {
        // set original totalAmount
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal calPct = BigDecimal.ZERO;
        BigDecimal totPct = BigDecimal.ZERO;
        if (invLineTMsg == null) { // is Not TargetLineLevel
            if (AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue())) {
                // MOD START 2019/09/11 QC#53032
             // totalAmount = invTMsg.invTotDealSlsAmt.getValue();
                totalAmount = invTMsg.invTotDealSlsAmt.getValue().add(invTMsg.invTotDealDiscAmt.getValue());
                // MOD START 2019/09/11 QC#53032
            } else if (AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {
                totalAmount = invTMsg.invTotDealTaxAmt.getValue();
            } else if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
                totalAmount = invTMsg.invTotDealFrtAmt.getValue();
            // ADD START 2019/08/28 QC#52944
            } else {
                totalAmount = invTMsg.invTotDealNetAmt.getValue();
            // ADD END 2019/08/28 QC#52944
            }
        } else { // is TargetLineLevel
            if (AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue())) {
                totalAmount = invLineTMsg.invLineDealNetAmt.getValue();
            } else if (AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {
                totalAmount = invLineTMsg.invLineDealTaxAmt.getValue();
            } else if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
                totalAmount = invBolTMsg.shipDealFrtAmt.getValue();
            // ADD START 2019/08/28 QC#52944
            } else {
                totalAmount = totalAmount.add(invLineTMsg.invLineDealNetAmt.getValue()).add(invLineTMsg.invLineDealTaxAmt.getValue());
            // ADD END 2019/08/28 QC#52944
            }
        }
        // START 2019/08/30 [QC#52945,ADD]
        if (BigDecimal.ZERO.compareTo(totalAmount) == 0) {
            // START 2020/08/24 [QC#56785, ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotInvPct, BigDecimal.valueOf(HIDX));
            ZYPEZDItemValueSetter.setValue(bizMsg.crRebilAmt_CA, totalAmount);
            // END 2020/08/24 [QC#56785, ADD]
            return BigDecimal.ZERO;
        }
        // END 2019/08/30 [QC#52945,ADD]
        // set Percent
        int scale = bizMsg.aftDeclPntDigitNum.getValue().intValue();
        if (ZYPCommonFunc.hasValue(bizMsg.crRebilAmt_CO)) {
            calPct = bizMsg.crRebilAmt_CO.getValue().divide(totalAmount, SIDX, HALF_UP);
            totPct = bizMsg.crRebilAmt_CO.getValue().divide(totalAmount, SCL_4, HALF_UP);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotInvPct, totPct.multiply(new BigDecimal(HIDX)).setScale(scale));
        } else if (ZYPCommonFunc.hasValue(bizMsg.arCrPct)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotInvPct, bizMsg.arCrPct.getValue());
            calPct = bizMsg.arCrPct.getValue().divide(new BigDecimal(HIDX));
        } else {
            calPct = BigDecimal.ONE;
        }
        // set crRebilAmt for AR_CR_REBIL
        ZYPEZDItemValueSetter.setValue(bizMsg.crRebilAmt_TO, totalAmount);
        ZYPEZDItemValueSetter.setValue(bizMsg.origInvAmt, totalAmount);
        if (ZYPCommonFunc.hasValue(bizMsg.crRebilAmt_CO) || ZYPCommonFunc.hasValue(bizMsg.arCrPct)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.crRebilAmt_CA, calculateAmount(bizMsg, totalAmount, calPct));
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.crRebilAmt_CA, totalAmount);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotInvPct, BigDecimal.valueOf(HIDX));
        }
        return calPct;
    }

    /**
     * calculateAmount
     * 
     * @param bizMsg NFCL3070CMsg
     * @param calAmount BigDecimal
     * @param calPct BigDecimal
     * @return amount BigDecimal
     */
    public static BigDecimal calculateAmount(NFCL3070CMsg bizMsg, BigDecimal calAmount, BigDecimal calPct) {

        if (ZYPCommonFunc.hasValue(calAmount)) {
            return calAmount.multiply(calPct).setScale(bizMsg.aftDeclPntDigitNum.getValue().intValue(), HALF_UP);
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * addAmount
     * 
     * @param addAmount BigDecimal
     * @return amount BigDecimal
     */
    public static BigDecimal addAmount(BigDecimal addAmount) {

        if (ZYPCommonFunc.hasValue(addAmount)) {
            return addAmount;
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * setTaxByTaxCalculationAPI
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @param insInvLineTMsgList List<INV_LINETMsg>
     * @param insInvBolTMsgList List<INV_BOLTMsg>
     * @param DS_INV_TPTMsg dsInvTpTMsg
     * @param dsInvLineTaxDtlTMsgList List<DS_INV_LINE_TAX_DTLTMsg>
     * @return Total Tax Amount
     */
    private static boolean setTaxByTaxCalculationAPI(NFCL3070CMsg bizMsg, String invNum, List<INV_LINETMsg> insInvLineTMsgList, List<INV_BOLTMsg> insInvBolTMsgList, DS_INV_TPTMsg dsInvTpTMsg, List<DS_INV_LINE_TAX_DTLTMsg> dsInvLineTaxDtlTMsgList, String coaMdseTpCdFrt) {

        boolean isSuccess = true;
         // START 2016/10/07 S.Fujita [QC#10522,DEL]
//        if (ZYPCommonFunc.hasValue(invBolTMsg.shipToCustCd.getValue()) || SYS_SRC.S21_SERVICE.equals(bizMsg.sysSrcCd.getValue())) {
         // END   2016/10/07 S.Fujita [QC#10522,DEL]
            // Call Tax Calculation API
            List<NWZC036101PMsg> taxcalcPMsgList = makeTaxCalculationAPIParam(bizMsg, invNum, insInvLineTMsgList, insInvBolTMsgList, dsInvTpTMsg);
    
            if (taxcalcPMsgList.size() > 0) {
                NWZC036101 taxCalculationAPI = new NWZC036101();
                for (NWZC036101PMsg taxcalcPMsg : taxcalcPMsgList) {
                    taxCalculationAPI.execute(taxcalcPMsg, ONBATCH_TYPE.ONLINE);
                }

                // START 2016/10/07 S.Fujita [QC#10522,DEL]
//                if (!setMessage(bizMsg, taxcalcPMsg)) {
//                    isSuccess = false;
//                    return isSuccess;
//                }
                // END   2016/10/07 S.Fujita [QC#10522,DEL]

                Iterator<NWZC036101PMsg> taxcalcPMsgItr = taxcalcPMsgList.iterator();
                // Set Tax Amount and Percent
                if (!setTaxCalculationAPIResult(bizMsg, invNum, insInvLineTMsgList, insInvBolTMsgList, taxcalcPMsgItr, dsInvLineTaxDtlTMsgList, coaMdseTpCdFrt)) {
                    isSuccess = false;
                }
            }
         // START 2016/10/07 S.Fujita [QC#10522,DEL]
//        }
         // END   2016/10/07 S.Fujita [QC#10522,DEL]
        return isSuccess;
    }

    /**
     * makeTaxCalculationAPIParam
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @param insInvLineTMsgList List<INV_LINETMsg>
     * @param insInvBolTMsgList List<INV_BOLTMsg>
     * @param dsInvTpTMsg DS_INV_TPTMsg 
     * @return List<NWZC036101PMsg>
     */
    private static List<NWZC036101PMsg> makeTaxCalculationAPIParam(NFCL3070CMsg bizMsg, String invNum, List<INV_LINETMsg> insInvLineTMsgList, List<INV_BOLTMsg> insInvBolTMsgList, DS_INV_TPTMsg dsInvTpTMsg) {

        List<NWZC036101PMsg> taxcalcPMsgList = new ArrayList<NWZC036101PMsg>();
        String frtTaxDummyMdseCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_FRT_TAX_DUMMY_MDSE_CD, bizMsg.glblCmpyCd.getValue());
        if (frtTaxDummyMdseCd == null) {
            throw new S21AbendException("[Error]Not Found 'VAR_CHAR_CONST' : varCharConstNm=" + CONST_KEY_FRT_TAX_DUMMY_MDSE_CD);
        }
        NWZC036101_taxCalculateInputLinePMsg taxCalcInputLinePMsg;

        String sellToCustCd = bizMsg.sellToCustCd.getValue();
        String billToCustAcctCd = bizMsg.billToCustAcctCd.getValue();
        String billToCustLocCd = bizMsg.billToCustCd.getValue();

        // get billToTaxExemGrpCd
        String billToTaxExemGrpCd = NFCL3070Query.getInstance().getTaxGrpExemCdFromBillToCust(bizMsg);
        if (billToTaxExemGrpCd == null) {
            billToTaxExemGrpCd = NFCL3070Query.getInstance().getTaxGrpExemCdFromSellToCust(bizMsg);
        }
        String billToTaxGrpExemReslFlg = null;
        if (billToTaxExemGrpCd != null) {
            DS_TAX_GRP_EXEMTMsg dsTaxGrpExemTmsg = new DS_TAX_GRP_EXEMTMsg();
            ZYPEZDItemValueSetter.setValue(dsTaxGrpExemTmsg.glblCmpyCd,     bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(dsTaxGrpExemTmsg.dsTaxGrpExemCd, billToTaxExemGrpCd);
            dsTaxGrpExemTmsg = (DS_TAX_GRP_EXEMTMsg) S21CacheTBLAccessor.findByKey(dsTaxGrpExemTmsg);
            if (dsTaxGrpExemTmsg != null) {
                billToTaxGrpExemReslFlg = dsTaxGrpExemTmsg.dsTaxGrpExemReslFlg.getValue();
            }
        }

        // The parameter of Tax Calculation API is made by the unit of INV_BOL.
        for (int i = 0; i < insInvBolTMsgList.size(); i++) {

            // START 2016/04/28 S.Fujita [QC#7722,MOD]
            if (!ZYPCommonFunc.hasValue(insInvBolTMsgList.get(i).shipToCustCd.getValue()) && !SYS_SRC.S21_SERVICE.equals(bizMsg.sysSrcCd.getValue())) {
                continue;
            }
            // END 2016/04/28 S.Fujita [QC#7722,MOD]

            // The parameter of Tax Calculation API
            NWZC036101PMsg taxcalcPMsg = new NWZC036101PMsg();
            int taxCalcLineNum = 0;
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.slsDt, ZYPDateUtil.getSalesDate());
            // START 2018/05/30 E.Kameishi [QC#26229,ADD]
            // START 2018/01/18 E.Kameishi [QC#21513,MOD]
            if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.xxModeCd,  NWZC036101Constant.PROC_MODE_DISTRIBUTE_TAX);
            } else {
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPgFlg_C.getValue())) {
                    ZYPEZDItemValueSetter.setValue(taxcalcPMsg.xxModeCd,  NWZC036101Constant.PROC_MODE_INVOICE);
                } else {
                    ZYPEZDItemValueSetter.setValue(taxcalcPMsg.xxModeCd,  NWZC036101Constant.PROC_MODE_QUOTATION);
                }
            }
            // END 2018/01/18 E.Kameishi [QC#21513,MOD]
            // END 2018/05/30 E.Kameishi [QC#26229,ADD]

            //Sell To Account Number
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.dsAcctNum_SE, sellToCustCd);
            //Bill To Account Number
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.billToAcctNum, billToCustAcctCd);
            //Bill To Location Number
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.billToLocNum, billToCustLocCd);
            //Bill to  Vertex Group Exemption
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.dsTaxGrpExemCd_B, billToTaxExemGrpCd);
            //Bill to  Vertex Group Exemption Resale Flg
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.dsTaxGrpExemReslFlg_B, billToTaxGrpExemReslFlg);
            //Ship To Account Number
            // get ShipToCustAcctCd
            String shipToCustAcctCd = getShipToCustAcctCd(bizMsg, insInvBolTMsgList.get(i).invBolLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.dsAcctNum_ST, bizMsg.shipToCustAcctCd.getValue());

            //Ship to Customer Code
            String shipToCustCd = null;
            //Ship to Location Name
            String shipToLocNm = null;
            //Ship to City Address
            String shipToCtyAddr = null;
            //Ship to State Code
            String shipToStCd = null;
            //Ship to First Line Address
            String shipToFirstLineAddr = null;
            //Ship to Second Line Address
            String shipToScdLineAddr = null;
            //Ship To County Name
            String shipToCntyNm = null;
            //Ship To Post Code
            String shipToPostCd = null;
            //Ship To Country Code
            String shipToCtryCd = null;
            // START 2019/08/07 S.Takami [QC#52447,MOD]
//            // START 2016/04/13 S.Fujita [S21 NA Unit Test QC#6565,MOD]
//            if (!SYS_SRC.S21_SERVICE.equals(bizMsg.sysSrcCd.getValue())) {
//                shipToCustCd = insInvBolTMsgList.get(i).shipToCustCd.getValue();
//                shipToCtyAddr =  insInvBolTMsgList.get(i).shipToCtyAddr.getValue();
//                shipToStCd =  insInvBolTMsgList.get(i).shipToStCd.getValue();
//                shipToFirstLineAddr =  insInvBolTMsgList.get(i).shipToFirstLineAddr.getValue();
//                shipToScdLineAddr =  insInvBolTMsgList.get(i).shipToScdLineAddr.getValue();
//                shipToCntyNm =  insInvBolTMsgList.get(i).shipToCntyNm.getValue();
//                shipToPostCd =  insInvBolTMsgList.get(i).shipToPostCd.getValue();
//                shipToCtryCd =  insInvBolTMsgList.get(i).shipToCtryCd.getValue();
//            } else { // for Service Invoice
//                Map<String, Object> mapResSvc = (Map<String, Object>) NFCL3070Query.getInstance().getShipToCustForSvcInv(bizMsg);
//                if (mapResSvc != null) {
//                    shipToCustCd = (String) mapResSvc.get("SHIP_TO_CUST_CD");
//                    shipToLocNm = (String) mapResSvc.get("LOC_NM");
//                    shipToCtyAddr = (String) mapResSvc.get("CTY_ADDR");
//                    shipToStCd = (String) mapResSvc.get("ST_CD");
//                    shipToFirstLineAddr = (String) mapResSvc.get("FIRST_LINE_ADDR");
//                    shipToScdLineAddr = (String) mapResSvc.get("SCD_LINE_ADDR");
//                    shipToPostCd = (String) mapResSvc.get("POST_CD");
//                    shipToCtryCd = (String) mapResSvc.get("CTRY_CD");
//
//                    ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, shipToCustCd);
//                    ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm, shipToLocNm);
//                    // START 2018/08/24 E.Kameishi [QC#27848,ADD]
//                    ZYPEZDItemValueSetter.setValue(taxcalcPMsg.invTpCd, (String) mapResSvc.get("INV_TP_CD"));
//                    // END 2018/08/24 E.Kameishi [QC#27848,ADD]
//                }
//            }
//            // END 2016/04/13 S.Fujita [S21 NA Unit Test QC#6565,MOD]
            shipToCustCd = insInvBolTMsgList.get(i).shipToCustCd.getValue();
            shipToCtyAddr =  insInvBolTMsgList.get(i).shipToCtyAddr.getValue();
            shipToStCd =  insInvBolTMsgList.get(i).shipToStCd.getValue();
            shipToFirstLineAddr =  insInvBolTMsgList.get(i).shipToFirstLineAddr.getValue();
            shipToScdLineAddr =  insInvBolTMsgList.get(i).shipToScdLineAddr.getValue();
            shipToCntyNm =  insInvBolTMsgList.get(i).shipToCntyNm.getValue();
            shipToPostCd =  insInvBolTMsgList.get(i).shipToPostCd.getValue();
            shipToCtryCd =  insInvBolTMsgList.get(i).shipToCtryCd.getValue();
            // END 2019/08/07 S.Takami [QC#52447,MOD]
            //Ship To Location Number
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.shipToLocNum, shipToCustCd);
            //Ship to Vertex Group Exemption
            String shipToTaxExemGrpCd = NFCL3070Query.getInstance().getTaxGrpExemCdFromShipToCust(bizMsg, shipToCustCd);
            if (shipToTaxExemGrpCd == null) {
                shipToTaxExemGrpCd = NFCL3070Query.getInstance().getTaxGrpExemCdFromSellToCust(bizMsg);
            }
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.dsTaxGrpExemCd_ST, shipToTaxExemGrpCd);

            //get SHIP_TO_CUST
            String geoCd = null;
            String dsInsdCtyLimitFlg = null;
            BigDecimal shipToCntyPk = null;
            SHIP_TO_CUSTTMsg shipToCustTMsg = (SHIP_TO_CUSTTMsg) NFCL3070Query.getInstance().getShipToCust(bizMsg, shipToCustCd);
            if (shipToCustTMsg != null) {
                shipToCntyPk = shipToCustTMsg.cntyPk.getValue();
                geoCd = shipToCustTMsg.geoCd.getValue();
                dsInsdCtyLimitFlg = shipToCustTMsg.dsInsdCtyLimitFlg.getValue();
            }
             //Ship To Tax Area ID
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.geoCd_ST, geoCd);
            //Ship To Inside City Limit Flag
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.dsInsdCtyLimitFlg_ST, dsInsdCtyLimitFlg);
            //Ship To Special Tax Area ID
            // START 2016/08/01 [QC#9192,MOD]
//            TAX_AREATMsg taxAreaTMsg = new TAX_AREATMsg();
//            ZYPEZDItemValueSetter.setValue(taxAreaTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
//            ZYPEZDItemValueSetter.setValue(taxAreaTMsg.ctyAddr,    shipToCtyAddr);
//            ZYPEZDItemValueSetter.setValue(taxAreaTMsg.cntyPk,     shipToCntyPk);
//            ZYPEZDItemValueSetter.setValue(taxAreaTMsg.stCd,       shipToStCd);
//            taxAreaTMsg = (TAX_AREATMsg) S21CacheTBLAccessor.findByKey(taxAreaTMsg);
//            if (taxAreaTMsg != null) {
//                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.taxAreaId_ST, taxAreaTMsg.taxAreaId.getValue());
//            }
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.taxAreaId_ST, getTaxAreaId(bizMsg.glblCmpyCd.getValue(), shipToCtyAddr, shipToCntyPk, shipToStCd));
            // END 2016/08/01 [QC#9192,MOD]

            //Tax Calculation Flag
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.taxCalcFlg, dsInvTpTMsg.taxCalcFlg.getValue());
            //Tax Exemption
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.taxExemFlg, dsInvTpTMsg.taxExemFlg.getValue());
            //Tax Exemption Reason Code
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.taxExemRsnCd, dsInvTpTMsg.taxExemRsnCd.getValue());
            //Transaction Date
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.trxDt, bizMsg.invDt.getValue());
            //Tax Calculate Header Num
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.xxTaxCalcHdrNum, invNum);

            // START 2018/08/24 E.Kameishi [QC#27848,MOD]
            if (!SYS_SRC.S21_SERVICE.equals(bizMsg.sysSrcCd.getValue())) {
                //Lease Option Code
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.leasePrchOptCd, bizMsg.leasePrchOptCd.getValue());
            }
            // END 2018/08/24 E.Kameishi [QC#27848,MOD]
            //Ship to First Line Address
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.firstLineAddr_ST, shipToFirstLineAddr);
            //Ship to Second Line Address
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.scdLineAddr_ST, shipToScdLineAddr);
            //Ship to City Address
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.ctyAddr_ST, shipToCtyAddr);
            //Ship to State Code
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.stCd_ST, shipToStCd);
            //Ship To County Name
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.cntyNm_ST, shipToCntyNm);
            //Ship To Post Code
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.postCd_ST, shipToPostCd);
            //Ship To Country Code
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.ctryCd_ST, shipToCtryCd); 

            // get salesrep address from S21_PSN
            S21_PSNTMsg s21PsnTMsg = new S21_PSNTMsg();
//            S21SsmEZDResult ssmRes = NFCL3070Query.getInstance().getSalesRepAdress(bizMsg, s21PsnTMsg); // ADD 2017/01/10 [QC#16983] s21PsnTMsg
            S21SsmEZDResult ssmRes = NFCL3070Query.getInstance().getSalesRepAdress(bizMsg, s21PsnTMsg, bizMsg.slsRepTocCd.getValue()); // mod QC#19781

            int resultCount = ssmRes.getQueryResultCount();
            // get county name
            String slsRepCntyNm = null;
            if (hasValue(s21PsnTMsg.cntyPk)) {
                slsRepCntyNm = getCountyName(bizMsg, s21PsnTMsg.cntyPk.getValue());
            }
            if (resultCount != 0) {
                //Sales Rep Tax Area ID
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.geoCd_SR, s21PsnTMsg.geoCd.getValue());
                //Sales Rep Inside City Limit Flag
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.dsInsdCtyLimitFlg_SR, s21PsnTMsg.dsInsdCtyLimitFlg.getValue());
                //Sales Rep First Line Address
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.firstLineAddr_SR, s21PsnTMsg.firstLineAddr.getValue());
                //Sales Rep Second Line Address
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.scdLineAddr_SR, s21PsnTMsg.scdLineAddr.getValue());
                //Sales Rep City Address
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.ctyAddr_SR, s21PsnTMsg.ctyAddr.getValue());
                //Sales Rep State Code
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.stCd_SR, s21PsnTMsg.stCd.getValue());
                //Sales Rep County Name
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.cntyNm_SR, slsRepCntyNm);
                //Sales Rep Post Code
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.postCd_SR, s21PsnTMsg.postCd.getValue());
                //Sales Rep Country Code
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.ctryCd_SR, s21PsnTMsg.ctryCd.getValue());
            }
            // DEL End    2017/07/18 CSA Defect#19781

            // get ship from
            // START 2018/08/24 E.Kameishi [QC#27848,MOD]
            if (!SYS_SRC.S21_SERVICE.equals(bizMsg.sysSrcCd.getValue())) {
                DS_INVTY_LOC_VTMsg dsInvtyLocVTMsg = new DS_INVTY_LOC_VTMsg();
                S21SsmEZDResult ssmShipRes = NFCL3070Query.getInstance().getDsInvtyLoc(//
                        bizMsg, insInvBolTMsgList.get(i).shipFromInvtyLocCd.getValue(), dsInvtyLocVTMsg); // ADD 2017/01/10 [QC#16983] dsInvtyLocVTMsg

                 resultCount = ssmShipRes.getQueryResultCount();
                if (resultCount != 0) {
                    //Ship from Tax Area ID
                    ZYPEZDItemValueSetter.setValue(taxcalcPMsg.geoCd_SF, dsInvtyLocVTMsg.geoCd.getValue());
                    //Ship from First Line Address
                    ZYPEZDItemValueSetter.setValue(taxcalcPMsg.firstLineAddr_SF, dsInvtyLocVTMsg.firstLineAddr.getValue());
                    //Ship from Second Line Address
                    ZYPEZDItemValueSetter.setValue(taxcalcPMsg.scdLineAddr_SF, dsInvtyLocVTMsg.scdLineAddr.getValue());
                    //Ship from City Address
                    ZYPEZDItemValueSetter.setValue(taxcalcPMsg.ctyAddr_SF, dsInvtyLocVTMsg.ctyAddr.getValue());
                    //Ship from State Code
                    ZYPEZDItemValueSetter.setValue(taxcalcPMsg.stCd_SF, dsInvtyLocVTMsg.stCd.getValue());
                    //Ship from County Name
                    ZYPEZDItemValueSetter.setValue(taxcalcPMsg.cntyNm_SF, getCountyName(bizMsg, dsInvtyLocVTMsg.cntyPk.getValue()));
                    //Ship from Post Code
                    ZYPEZDItemValueSetter.setValue(taxcalcPMsg.postCd_SF, dsInvtyLocVTMsg.postCd.getValue());
                    //Ship from Country Code
                    ZYPEZDItemValueSetter.setValue(taxcalcPMsg.ctryCd_SF, dsInvtyLocVTMsg.ctryCd.getValue());
                }
                //Ship from WH Code
                String rtlWhCd = NFCL3070Query.getInstance().getRtlWh(bizMsg, insInvBolTMsgList.get(i).soNum.getValue());
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.whCd_SF, rtlWhCd);
            }
            // END 2018/08/24 E.Kameishi [QC#27848,MOD]
            // START 2018/05/23 E.Kameishi [QC#21841,DEL]
            // first line is freight
//            taxCalcInputLinePMsg = (NWZC036101_taxCalculateInputLinePMsg) taxcalcPMsg.taxCalculateInputLine.no(taxCalcLineNum++);
//            //Tax Calculate Line Number
//            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.xxTaxCalcLineNum_A, Integer.toString(taxCalcLineNum));
//            //Merchandise Code
//            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.mdseCd_A, frtTaxDummyMdseCd);
//            //Shipped Quantity
//            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.shipQty_A, BigDecimal.ONE);
//
//            if (bizMsg.xxPgFlg_P.getValue().equals(ZYPConstant.FLG_ON_Y) && AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue())) {
//                //Function Net Unit Price Amount
//                ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.funcNetUnitPrcAmt_A, BigDecimal.ZERO);
//                //Sales Amount
//                ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.slsAmt_A, BigDecimal.ZERO);
//            } else if (bizMsg.xxPgFlg_T.getValue().equals(ZYPConstant.FLG_ON_Y) && !AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
//                //Function Net Unit Price Amount
//                ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.funcNetUnitPrcAmt_A, BigDecimal.ZERO);
//                //Sales Amount
//                ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.slsAmt_A, BigDecimal.ZERO);
//            } else {
//                if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y)) {
//                    //Function Net Unit Price Amount
//                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.funcNetUnitPrcAmt_A, insInvBolTMsgList.get(i).shipFuncFrtAmt.getValue());
//                    //Sales Amount
//                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.slsAmt_A, insInvBolTMsgList.get(i).shipFuncFrtAmt.getValue().negate());
//                } else {
//                    //Function Net Unit Price Amount
//                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.funcNetUnitPrcAmt_A, insInvBolTMsgList.get(i).shipFuncFrtAmt.getValue());
//                    //Sales Amount
//                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.slsAmt_A, insInvBolTMsgList.get(i).shipFuncFrtAmt.getValue());
//                }
//            }
//
//            // Add Start  2017/07/18 CSA Defect#19781
//            String slsRepTocCd = null;
//            for (int k = 0; k < insInvLineTMsgList.size(); k++) {
//                if (insInvBolTMsgList.get(i).invBolLineNum.getValue().equals(insInvLineTMsgList.get(k).invBolLineNum.getValue())) {
//                    INV_LINETMsg invLineTmsg = insInvLineTMsgList.get(k);
//                    slsRepTocCd = NFCL3070Query.getInstance().getSlsRepTocCd(bizMsg,invLineTmsg);
//                    break;
//                }
//            }
//            if (slsRepTocCd == null) {
//                slsRepTocCd = bizMsg.slsRepTocCd.getValue();
//            }
//
//            // get salesrep address from S21_PSN
//            s21PsnTMsg = new S21_PSNTMsg();
//            // Mod Start  2017/07/18 CSA Defect#19781
//            //S21SsmEZDResult ssmRes = NFCL3070Query.getInstance().getSalesRepAdress(bizMsg, s21PsnTMsg); // ADD 2017/01/10 [QC#16983] s21PsnTMsg
//            ssmRes = NFCL3070Query.getInstance().getSalesRepAdress(bizMsg, s21PsnTMsg, slsRepTocCd); // ADD 2017/01/10 [QC#16983] s21PsnTMsg
//            // Mod End    2017/07/18 CSA Defect#19781
//            resultCount = ssmRes.getQueryResultCount();
//            // get county name
//            slsRepCntyNm = null;
//            if (hasValue(s21PsnTMsg.cntyPk)) {
//                slsRepCntyNm = getCountyName(bizMsg, s21PsnTMsg.cntyPk.getValue());
//            }
//            //Sales Rep Tax Area ID
//            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.geoCd_AR, s21PsnTMsg.geoCd.getValue());
//            //Sales Rep Inside City Limit Flag
//            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.dsInsdCtyLimitFlg_AR, s21PsnTMsg.dsInsdCtyLimitFlg.getValue());
//            //Sales Rep First Line Address
//            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.firstLineAddr_AR, s21PsnTMsg.firstLineAddr.getValue());
//            //Sales Rep Second Line Address
//            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.scdLineAddr_AR, s21PsnTMsg.scdLineAddr.getValue());
//            //Sales Rep City Address
//            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.ctyAddr_AR, s21PsnTMsg.ctyAddr.getValue());
//            //Sales Rep State Code
//            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.stCd_AR, s21PsnTMsg.stCd.getValue());
//            //Sales Rep County Name
//            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.cntyNm_AR, slsRepCntyNm);
//            //Sales Rep Post Code
//            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.postCd_AR, s21PsnTMsg.postCd.getValue());
//            //Sales Rep Country Code
//            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.ctryCd_AR, s21PsnTMsg.ctryCd.getValue());
//            // Add End    2017/07/18 CSA Defect#19781
//
//            //get mdse info
//            getMdseInfoForTaxParam(bizMsg, frtTaxDummyMdseCd, taxCalcInputLinePMsg);
//
//            taxcalcPMsg.taxCalculateInputLine.setValidCount(taxCalcLineNum);
            // END 2018/05/23 E.Kameishi [QC#21841,DEL]
            // Tax Calculate Input Line
            for (int j = 0; j < insInvLineTMsgList.size(); j++) {
                if (insInvBolTMsgList.get(i).invBolLineNum.getValue().equals(insInvLineTMsgList.get(j).invBolLineNum.getValue())) {
                    // Tax Calculate Input Line
                    taxCalcInputLinePMsg = (NWZC036101_taxCalculateInputLinePMsg) taxcalcPMsg.taxCalculateInputLine.no(taxCalcLineNum++);
                    //Tax Calculate Line Number
                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.xxTaxCalcLineNum_A, Integer.toString(taxCalcLineNum));
                    // START 2018/08/24 E.Kameishi [QC#27848,MOD]
                    //Merchandise Code
                    if (!SYS_SRC.S21_SERVICE.equals(bizMsg.sysSrcCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.mdseCd_A, insInvLineTMsgList.get(j).mdseCd.getValue());
                    } else {
                        String svcPgmMdseCd = NFCL3070Query.getInstance().getSvcPgmMdseCd(bizMsg, insInvLineTMsgList.get(j).svcInvLinePk.getValue());
                        // START 2019/03/25 E.Kameishi [QC#30904,MOD]
                        if (svcPgmMdseCd == null) {
                            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.mdseCd_A, insInvLineTMsgList.get(j).mdseCd.getValue());
                        } else {
                            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.mdseCd_A, svcPgmMdseCd);
                        }
                        // END 2018/08/24 E.Kameishi [QC#30904,MOD]
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.trxDt_A, taxcalcPMsg.trxDt);
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.billToAcctNum_A, taxcalcPMsg.billToAcctNum);
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.billToLocNum_A, taxcalcPMsg.billToLocNum);
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.shipToLocNum_A, taxcalcPMsg.shipToLocNum);
                    }
                    //get mdse info
                    getMdseInfoForTaxParam(bizMsg, taxCalcInputLinePMsg.mdseCd_A.getValue(), taxCalcInputLinePMsg);
                    // END 2018/08/24 E.Kameishi [QC#27848,MOD]
                    //Shipped Quantity
                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.shipQty_A, insInvLineTMsgList.get(j).shipQty.getValue());

                    // START 2018/05/23 E.Kameishi [QC#21841,MOD]
                    //if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
                        //Function Net Unit Price Amount
                        //ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.funcNetUnitPrcAmt_A, BigDecimal.ZERO);
                        //Sales Amount
                        //ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.slsAmt_A, BigDecimal.ZERO);
                        //Function Net Unit Price Amount
                    //} else {
                    if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                        //Function Net Unit Price Amount
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.funcNetUnitPrcAmt_A, insInvLineTMsgList.get(j).funcNetUnitPrcAmt.getValue());
                        //Sales Amount
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.slsAmt_A, insInvLineTMsgList.get(j).invLineFuncNetAmt.getValue().negate());
                        // START 2018/05/30 E.Kameishi [QC#26299,ADD]
                        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {
                            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.taxAmt_A, insInvLineTMsgList.get(j).invLineFuncTaxAmt.getValue().negate());
                        }
                        // END 2018/05/30 E.Kameishi [QC#26299,ADD]
                    } else {
                        //Function Net Unit Price Amount
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.funcNetUnitPrcAmt_A, insInvLineTMsgList.get(j).funcNetUnitPrcAmt.getValue());
                        //Sales Amount
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.slsAmt_A, insInvLineTMsgList.get(j).invLineFuncNetAmt.getValue());
                        // START 2018/05/30 E.Kameishi [QC#26299,ADD]
                        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {
                            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.taxAmt_A, insInvLineTMsgList.get(j).invLineFuncTaxAmt.getValue());
                        }
                        // END 2018/05/30 E.Kameishi [QC#26299,ADD]
                    }
                    //}
                    // END 2018/05/23 E.Kameishi [QC#21841,MOD]
                    // add QC19781
                    //Sales Rep Tax Area ID
                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.geoCd_AR, s21PsnTMsg.geoCd.getValue());
                    //Sales Rep Inside City Limit Flag
                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.dsInsdCtyLimitFlg_AR, s21PsnTMsg.dsInsdCtyLimitFlg.getValue());
                    //Sales Rep First Line Address
                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.firstLineAddr_AR, s21PsnTMsg.firstLineAddr.getValue());
                    //Sales Rep Second Line Address
                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.scdLineAddr_AR, s21PsnTMsg.scdLineAddr.getValue());
                    //Sales Rep City Address
                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.ctyAddr_AR, s21PsnTMsg.ctyAddr.getValue());
                    //Sales Rep State Code
                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.stCd_AR, s21PsnTMsg.stCd.getValue());
                    //Sales Rep County Name
                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.cntyNm_AR, slsRepCntyNm);
                    //Sales Rep Post Code
                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.postCd_AR, s21PsnTMsg.postCd.getValue());
                    //Sales Rep Country Code
                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.ctryCd_AR, s21PsnTMsg.ctryCd.getValue());
                    // add QC19781

                    taxcalcPMsg.taxCalculateInputLine.setValidCount(taxCalcLineNum);
                }
            }
            taxcalcPMsgList.add(taxcalcPMsg);
        }
        return taxcalcPMsgList;
    }

    /**
     * getMdseInfoForTaxParam
     * 
     * @param bizMsg NFCL3070CMsg
     * @param mdseCd String
     * @param taxCalcInputLinePMsg NWZC036101_taxCalculateInputLinePMsg
     */
    private static void getMdseInfoForTaxParam(NFCL3070CMsg bizMsg, String mdseCd, NWZC036101_taxCalculateInputLinePMsg taxCalcInputLinePMsg) {

        MDSETMsg mdseTMsg = getMdseInfo(bizMsg, mdseCd);

        String svcAllocTpCd = null;
        String svcAllocTrxTpNm = null;
        String taxExemTpCd  = null;
        if (mdseTMsg != null) {
            svcAllocTpCd = mdseTMsg.svcAllocTpCd.getValue();
            taxExemTpCd  = mdseTMsg.taxExemTpCd.getValue();
        }
        if (svcAllocTpCd != null) {
            SVC_ALLOC_TPTMsg svcAllocTpTMsg = new SVC_ALLOC_TPTMsg();
            ZYPEZDItemValueSetter.setValue(svcAllocTpTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(svcAllocTpTMsg.svcAllocTpCd, svcAllocTpCd);
            svcAllocTpTMsg = (SVC_ALLOC_TPTMsg) S21CacheTBLAccessor.findByKey(svcAllocTpTMsg);
            if (svcAllocTpTMsg != null) {
                svcAllocTrxTpNm = svcAllocTpTMsg.svcAllocTrxTpNm.getValue();
            }
        }
        if (!hasValue(svcAllocTrxTpNm)) {
            // default set if value is null
            svcAllocTrxTpNm = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_DEFAULT_TAX_TRX_TP, bizMsg.glblCmpyCd.getValue());
        }
        //Service Allocation Type
        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.svcAllocTpCd_A, svcAllocTpCd);
        //Trx Type
        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.svcAllocTrxTpNm_A, svcAllocTrxTpNm);
        //Product Tax Code
        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.taxExemTpCd_A, taxExemTpCd);

    }

    /**
     * getMdseInfo
     * 
     * @param bizMsg NFCL3070CMsg
     * @param mdseCd String
     * @return MDSETMsg mdseTMsg
     */
    private static MDSETMsg getMdseInfo(NFCL3070CMsg bizMsg, String mdseCd) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {
            // get mdsecd
            ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
            ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.ordTakeMdseCd, mdseCd);
            ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdseTMsg);

            mdseTMsg = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, ordTakeMdseTMsg.mdseCd.getValue());
            mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
        }
        return mdseTMsg;
    }

    /**
     * setTaxCalculationAPIResult
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @param insInvLineTMsgList List<INV_LINETMsg>
     * @param insInvBolTMsgList List<INV_BOLTMsg>
     * @param taxcalcPMsgItr Iterator<NWZC036101PMsg>
     * @param dsInvLineTaxDtlTMsgList List<DS_INV_LINE_TAX_DTLTMsg>
     * @return boolean
     */
    private static boolean setTaxCalculationAPIResult(NFCL3070CMsg bizMsg, String invNum, List<INV_LINETMsg> insInvLineTMsgList, List<INV_BOLTMsg> insInvBolTMsgList,
            Iterator<NWZC036101PMsg> taxcalcPMsgItr, List<DS_INV_LINE_TAX_DTLTMsg> dsInvLineTaxDtlTMsgList, String coaMdseTpCdFrt) {

        boolean isSuccess = true;

        NWZC036101_taxCalculateOutputLinePMsg taxcalcOutputLinePMsg;
        DS_INV_LINE_TAX_DTLTMsg dsInvLineTaxDtlTMsg;
        BigDecimal taxAmt;
        BigDecimal taxPct;
        List<BigDecimal> taxAmtList;
        List<BigDecimal> taxPctList;
        List<String> taxResultList;
        List<String> frtTaxTpList = new ArrayList<String>();
        frtTaxTpList.add(DS_SLS_TAX_TP.FREIGHT_STATE_TAX);
        frtTaxTpList.add(DS_SLS_TAX_TP.FREIGHT_COUNTY_TAX);
        frtTaxTpList.add(DS_SLS_TAX_TP.FREIGHT_CITY_TAX);
        List<String> slsTaxTpList = new ArrayList<String>();
        slsTaxTpList.add(DS_SLS_TAX_TP.STATE_TAX);
        slsTaxTpList.add(DS_SLS_TAX_TP.COUNTY_TAX);
        slsTaxTpList.add(DS_SLS_TAX_TP.CITY_TAX);

        for (int i = 0; i < insInvBolTMsgList.size(); i++) {
            NWZC036101PMsg taxcalcPMsg = taxcalcPMsgItr.next();

            if (taxcalcPMsg.xxMsgIdList.getValidCount() > 0) {
                for (int j = 0; j < taxcalcPMsg.xxMsgIdList.getValidCount(); j++) {
                    String errId =taxcalcPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    bizMsg.setMessageInfo(errId);
                    return false;
                }
            }

            // START 2018/05/23 E.Kameishi [QC#21841,DEL]
            //boolean firstLineFlg = true;
            // END 2018/05/23 E.Kameishi [QC#21841,DEL]
            int taxCalcLineNum = 0;
            String invBolLineNum = insInvBolTMsgList.get(i).invBolLineNum.getValue();

            for (int j = 0; j < insInvLineTMsgList.size(); j++) {
                if (insInvBolTMsgList.get(i).invBolLineNum.getValue().equals(insInvLineTMsgList.get(j).invBolLineNum.getValue())) {
                    // START 2018/05/23 E.Kameishi [QC#21841,DEL]
                    // tax for InvBol
//                    if (firstLineFlg) {
//                        firstLineFlg = false;
//
//                        taxcalcOutputLinePMsg = (NWZC036101_taxCalculateOutputLinePMsg) taxcalcPMsg.taxCalculateOutputLine.no(taxCalcLineNum++);
//                        // START 2016/11/16 S.Fujita [QC#15832,MOD]
//                        taxAmt  = taxcalcOutputLinePMsg.invLineFuncTaxAmt.getValue();
//
//                        if (taxAmt == null) {
//                            taxAmt = BigDecimal.ZERO;
//                        }
//                        if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y)) {
//                            taxAmt  = taxAmt.negate();
//                        }
//                        // END   2016/11/16 S.Fujita [QC#15832,MOD]
//
//                        taxPct  = taxcalcOutputLinePMsg.xxTaxCalcLineTaxPct.getValue();
//                        if (taxPct == null) {
//                            taxPct = BigDecimal.ZERO;
//                        }
//                        // set TaxAmount to TMsg
//                        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue())) {
//                            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).frtTaxPct, BigDecimal.ZERO);
//                            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).frtFuncTaxAmt, BigDecimal.ZERO);
//                            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).frtDealTaxAmt, BigDecimal.ZERO);
//                        } else {
//                            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).frtTaxPct, taxPct);
//                            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).frtFuncTaxAmt, taxAmt);
//                            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(i).frtDealTaxAmt, taxAmt);
//                        }
//
//                        // tax detail
//                        taxAmtList = new ArrayList<BigDecimal>();
//                        taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_01.getValue());
//                        taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_02.getValue());
//                        taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_03.getValue());
//                        taxPctList = new ArrayList<BigDecimal>();
//                        taxPctList.add(taxcalcOutputLinePMsg.taxPct_01.getValue());
//                        taxPctList.add(taxcalcOutputLinePMsg.taxPct_02.getValue());
//                        taxPctList.add(taxcalcOutputLinePMsg.taxPct_03.getValue());
//                        taxResultList = new ArrayList<String>();
//                        taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_01.getValue());
//                        taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_02.getValue());
//                        taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_03.getValue());
//
//                        for (int k = 0; k < taxResultList.size(); k++) {
//                            taxAmt = taxAmtList.get(k);
//                            if (!ZYPCommonFunc.hasValue(taxAmt)) {
//                                taxAmt = BigDecimal.ZERO;
//                            }
//                            if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y)) {
//                                taxAmt = taxAmt.negate();
//                            }
//                            taxPct = taxPctList.get(k);
//                            if (taxAmt != null && taxAmt.compareTo(BigDecimal.ZERO) != 0) {
//                                dsInvLineTaxDtlTMsg = createTaxDtlTMsg(bizMsg, invNum, invBolLineNum, null, null, null);
//                                ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.dsSlsTaxTpCd, frtTaxTpList.get(k));
//                                ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.funcSlsTaxAmt, taxAmt);
//                                ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.dealSlsTaxAmt, taxAmt);
//                                ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.slsTaxPct, taxPct);
//                                // START 2017/03/09 [QC#17901,ADD]
//                                ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.taxAreaId, taxcalcOutputLinePMsg.taxAreaId);
//                                ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.taxRsltDescTxt, taxResultList.get(k));
//                                // END 2017/03/09 [QC#17901,ADD]
//                                dsInvLineTaxDtlTMsgList.add(dsInvLineTaxDtlTMsg);
//                            }
//                        }
//                    }
                    // END 2018/05/23 E.Kameishi [QC#21841,DEL]

                    // tax for InvLine
                    String invLineNum = insInvLineTMsgList.get(j).invLineNum.getValue();
                    String invLineSubNum = insInvLineTMsgList.get(j).invLineSubNum.getValue();
                    String invLineSubTrxNum = insInvLineTMsgList.get(j).invLineSubTrxNum.getValue();

                    taxcalcOutputLinePMsg = (NWZC036101_taxCalculateOutputLinePMsg) taxcalcPMsg.taxCalculateOutputLine.no(taxCalcLineNum++);
                    // START 2016/11/16 S.Fujita [QC#15832,MOD]
                    taxAmt  = taxcalcOutputLinePMsg.invLineFuncTaxAmt.getValue();

                    if (taxAmt == null) {
                        taxAmt = BigDecimal.ZERO;
                    }
                    if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                        taxAmt  = taxAmt.negate();
                    }
                    // END   2016/11/16 S.Fujita [QC#15832,MOD]

                    taxPct  = taxcalcOutputLinePMsg.xxTaxCalcLineTaxPct.getValue();
                    if (taxPct == null) {
                        taxPct = BigDecimal.ZERO;
                    }
                    // set TaxAmount to TMsg
                    // START 2018/05/23 E.Kameishi [QC#21841,DEL]
//                    if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(j).taxPct, BigDecimal.ZERO);
//                        ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(j).invLineFuncTaxAmt, BigDecimal.ZERO);
//                        ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(j).invLineDealTaxAmt, BigDecimal.ZERO);
//                    } else {
                        ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(j).taxPct, taxPct);
                        ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(j).invLineFuncTaxAmt, taxAmt);
                        ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(j).invLineDealTaxAmt, taxAmt);
//                    }
                    // END 2018/05/23 E.Kameishi [QC#21841,DEL]
                    // tax detail
                    taxAmtList = new ArrayList<BigDecimal>();
                    taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_01.getValue());
                    taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_02.getValue());
                    taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_03.getValue());
                    taxPctList = new ArrayList<BigDecimal>();
                    taxPctList.add(taxcalcOutputLinePMsg.taxPct_01.getValue());
                    taxPctList.add(taxcalcOutputLinePMsg.taxPct_02.getValue());
                    taxPctList.add(taxcalcOutputLinePMsg.taxPct_03.getValue());
                    taxResultList = new ArrayList<String>();
                    taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_01.getValue());
                    taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_02.getValue());
                    taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_03.getValue());

                    // START 2018/05/23 E.Kameishi [QC#21841,MOD]
                    String coaMdseTpCd = NFCL3070Query.getInstance().getMdseValue(bizMsg, insInvLineTMsgList.get(j).mdseCd.getValue());
                    // END 2018/05/23 E.Kameishi [QC#21841,MOD]
                    for (int k = 0; k < taxResultList.size(); k++) {
                        taxAmt = taxAmtList.get(k);
                        if (!ZYPCommonFunc.hasValue(taxAmt)) {
                            taxAmt = BigDecimal.ZERO;
                        }
                        if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                            taxAmt = taxAmt.negate();
                        }
                        taxPct = taxPctList.get(k);
                        if (taxAmt != null && taxAmt.compareTo(BigDecimal.ZERO) != 0) {
                            dsInvLineTaxDtlTMsg = createTaxDtlTMsg(bizMsg, invNum, invBolLineNum, invLineNum, invLineSubNum, invLineSubTrxNum);
                            // START 2018/05/23 E.Kameishi [QC#21841,MOD]
                            if (coaMdseTpCdFrt.equals(coaMdseTpCd)) {
                                ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.dsSlsTaxTpCd, frtTaxTpList.get(k));
                            } else {
                                ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.dsSlsTaxTpCd, slsTaxTpList.get(k));
                            }
                            // END 2018/05/23 E.Kameishi [QC#21841,MOD]
                            ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.funcSlsTaxAmt, taxAmt);
                            ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.dealSlsTaxAmt, taxAmt);
                            ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.slsTaxPct, taxPct);
                            // START 2017/03/09 [QC#17901,ADD]
                            ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.taxAreaId, taxcalcOutputLinePMsg.taxAreaId);
                            ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.taxRsltDescTxt, taxResultList.get(k));
                          	// END   2017/03/09 [QC#17901,ADD]
                            dsInvLineTaxDtlTMsgList.add(dsInvLineTaxDtlTMsg);
                        }
                    }
                }
            }
        }
        return isSuccess;
    }

    /**
     * createTaxDtlTMsg
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @param invBolLineNum String
     * @param invLineNum String
     * @param invLineSubNum String
     * @param invLineSubTrxNum String
     * @return dsInvLineTaxDtlTMsg DS_INV_LINE_TAX_DTLTMsg
     */
    private static DS_INV_LINE_TAX_DTLTMsg createTaxDtlTMsg(NFCL3070CMsg bizMsg, String invNum, String invBolLineNum, String invLineNum, String invLineSubNum, String invLineSubTrxNum) {

        DS_INV_LINE_TAX_DTLTMsg dsInvLineTaxDtlTMsg = new DS_INV_LINE_TAX_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.invNum,           invNum);
        ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.invBolLineNum,    invBolLineNum);
        if (invLineNum == null) {
            ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.invLineNum,       CONST_FRT_TAX_LINE_NUM);
        } else {
            ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.invLineNum,       invLineNum);
        }
        if (invLineSubNum == null) {
            ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.invLineSubNum,    CONST_FRT_TAX_LINE_NUM);
        } else {
            ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.invLineSubNum,    invLineSubNum);
        }
        if (invLineSubTrxNum == null) {
            ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.invTrxLineSubNum, CONST_FRT_TAX_LINE_NUM);
        } else {
            ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.invTrxLineSubNum, invLineSubTrxNum);
        }
        return dsInvLineTaxDtlTMsg;
    }

    // START 2016/10/07 S.Fujita [QC#10522,DEL]
//    /**
//     * The message set to PMsg is set to the message map.<br>
//     * @param bizMsg NFCL3070CMsg
//     * @param pMsg PMsg
//     * @return true:Error message none false:There is an error
//     * message.
//     */
//    public static boolean setMessage(NFCL3070CMsg bizMsg, EZDPMsg pMsg) {
//
//        List<String> errList = S21ApiUtil.getXxMsgIdList(pMsg);
//
//        if (!errList.isEmpty()) {
//            for (String xxMsgId : errList) {
//                bizMsg.setMessageInfo(xxMsgId);
//            }
//            return false;
//        }
//        return true;
//    }
    // END   2016/10/07 S.Fujita [QC#10522,DEL]

    /**
     * getCountyName
     * 
     * @param bizMsg NFCL3070CMsg
     * @param cntyPk BigDecimal
     * @return cntyNm String
     */
    private static String getCountyName(NFCL3070CMsg bizMsg, BigDecimal cntyPk) {

        String cntyNm = null;
        CNTYTMsg cntyTMsg = new CNTYTMsg();
        ZYPEZDItemValueSetter.setValue(cntyTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(cntyTMsg.cntyPk, cntyPk);
        cntyTMsg = (CNTYTMsg) S21CacheTBLAccessor.findByKey(cntyTMsg);
        if (cntyTMsg != null) {
            cntyNm = cntyTMsg.cntyNm.getValue();
        }
        return cntyNm;
    }

    /**
     * getShipToCustAcctCd
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invBolLineNum String
     * @return shipToCustAcctCd String
     */
    private static String getShipToCustAcctCd(NFCL3070CMsg bizMsg, String invBolLineNum) {

        String shipToCustAcctCd = null;
        INV_BOLTMsg invBolMsg = new INV_BOLTMsg();
        invBolMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        invBolMsg.invNum.setValue(bizMsg.origInvNum.getValue());
        invBolMsg.invBolLineNum.setValue(invBolLineNum);

        INV_BOLTMsg invBolTMsg = (INV_BOLTMsg) S21CacheTBLAccessor.findByKey(invBolMsg);
        if (invBolTMsg != null) {
            shipToCustAcctCd = invBolTMsg.shipToCustAcctCd.getValue();
        }
        return shipToCustAcctCd;
    }

    /**
     * insertDsInvLineTaxDtl
     * 
     * @param bizMsg NFCL3070CMsg
     * @param dsInvLineTaxDtlTMsgList List<DS_INV_LINE_TAX_DTLTMsg>
     * @return boolean
     */
    private static boolean insertDsInvLineTaxDtl(NFCL3070CMsg bizMsg, List<DS_INV_LINE_TAX_DTLTMsg> dsInvLineTaxDtlTMsgList) {
        int i = 0;
        for (DS_INV_LINE_TAX_DTLTMsg dsInvLineTaxDtlTMsg : dsInvLineTaxDtlTMsgList) {
            // START 2019/09/04 [QC#52945,ADD]
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPgFlg_T.getValue())) {
                ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.invBolLineNum, INIT_INV_BOL_LINE_NUM);
            }
            // START 2019/09/04 [QC#52945,ADD]
            //get DS_INV_LINE_TAX_DTL_PK
            BigDecimal pk = NFCL3070Query.getInstance().getDsInvLineTaxDtlPk(bizMsg, dsInvLineTaxDtlTMsgList.get(i));

            if (pk != null) {
                //update
                ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.dsInvLineTaxDtlPk, pk);
                S21ApiTBLAccessor.update(dsInvLineTaxDtlTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsInvLineTaxDtlTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NFCM0782E, new String[] {"DS_INV_LINE_TAX_DTL" });
                    return false;
                }
            } else {
                //insert
                ZYPEZDItemValueSetter.setValue(dsInvLineTaxDtlTMsg.dsInvLineTaxDtlPk,
                        ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_INV_LINE_TAX_DTL_SQ));
                S21ApiTBLAccessor.insert(dsInvLineTaxDtlTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsInvLineTaxDtlTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NFCM0782E, new String[] {"DS_INV_LINE_TAX_DTL" });
                    return false;
                }
            }
            i++;
        }
        return true;
    }

    // START 2016/07/07 S.Fujita [QC#10458,ADD]
    /**
     * setPmtTermCashDisc
     * @param bizMsg NFCL3070CMsg
     * @param insInvTMsg INVTMsg
     */
    private static void setPmtTermCashDisc(NFCL3070CMsg bizMsg, INVTMsg insInvTMsg) {

        String pmtTermCashDiscCd = ZYPCodeDataUtil.getVarCharConstValue("PMT_TERM_IM", bizMsg.glblCmpyCd.getValue());
        String pmtTermCd = "";

        PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = new PMT_TERM_CASH_DISCTMsg();
        pmtTermCashDiscTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        pmtTermCashDiscTMsg.pmtTermCashDiscCd.setValue(pmtTermCashDiscCd);

        PMT_TERM_CASH_DISCTMsg outMsg = (PMT_TERM_CASH_DISCTMsg) S21CacheTBLAccessor.findByKey(pmtTermCashDiscTMsg);
        if (outMsg != null) {
            pmtTermCd = outMsg.pmtTermCd.getValue();
            ZYPEZDItemValueSetter.setValue(insInvTMsg.pmtTermCashDiscCd, pmtTermCashDiscCd);
            ZYPEZDItemValueSetter.setValue(insInvTMsg.pmtTermCashDiscDescTxt, outMsg.pmtTermCashDiscDescTxt.getValue());
        }

        if (ZYPCommonFunc.hasValue(pmtTermCd)) {
            PMT_TERMTMsg pmtTermTMsg = new PMT_TERMTMsg();
            pmtTermTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            pmtTermTMsg.pmtTermCd.setValue(pmtTermCd);

            PMT_TERMTMsg outMsg1 = (PMT_TERMTMsg) S21CacheTBLAccessor.findByKey(pmtTermTMsg);
            if (outMsg1 != null) {
                ZYPEZDItemValueSetter.setValue(insInvTMsg.pmtTermCd, pmtTermCd);
                ZYPEZDItemValueSetter.setValue(insInvTMsg.pmtTermNm, outMsg1.pmtTermNm.getValue());
            }
        }
    }
    // END   2016/07/07 S.Fujita [QC#10458,ADD]

    // START 2016/08/01 [QC#9192, ADD]
    /**
     * getTaxAreaId
     * 
     * @param glblCmpyCd String
     * @param ctyAddr String
     * @param cntyPk BigDecimal
     * @param stCd String
     * @return taxAreaId
     */
    private static String getTaxAreaId(String glblCmpyCd, String ctyAddr, BigDecimal cntyPk, String stCd) {
        String taxAreaId = null;

        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", glblCmpyCd);
        inParam.put("ctyAddr", ctyAddr);
        inParam.put("cntyPk", cntyPk);
        inParam.put("stCd", stCd);

        taxAreaId = NFCL3070Query.getInstance().getTaxAreaId(inParam);
        if (taxAreaId == null) {
            inParam.put("ctyAddr", null);
            taxAreaId = NFCL3070Query.getInstance().getTaxAreaId(inParam);
        }
        if (taxAreaId == null) {
            inParam.put("cntyPk", null);
            taxAreaId = NFCL3070Query.getInstance().getTaxAreaId(inParam);
        }

        return taxAreaId;
    }
    // END   2016/08/01 [QC#9192, ADD]

    // START 2016/10/07 S.Fujita [QC#10522,ADD]
    /**
     * taxCalc
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @param insInvLineTMsgList List<INV_LINETMsg>
     * @param InvLineTMsg INV_LINETMsg
     * @param invLineTMsgAry INV_LINETMsgArray
     * @param insInvBolTMsgList List<INV_BOLTMsg>
     * @param invBolTMsg INV_BOLTMsg
     * @param invBolTMsgAry INV_BOLTMsgArray
     * @param DS_INV_TPTMsg dsInvTpTMsg
     * @param dsInvLineTaxDtlTMsgList List<DS_INV_LINE_TAX_DTLTMsg>
     * @param calPct BigDecimal
     * @return boolean
     */
    private static boolean taxCalc(NFCL3070CMsg bizMsg, String invNum, List<INV_LINETMsg> insInvLineTMsgList, INV_LINETMsg invLineTMsg, INV_LINETMsgArray invLineTMsgAry, List<INV_BOLTMsg> insInvBolTMsgList, INV_BOLTMsg invBolTMsg, INV_BOLTMsgArray invBolTMsgAry, DS_INV_TPTMsg dsInvTpTMsg, List<DS_INV_LINE_TAX_DTLTMsg> dsInvLineTaxDtlTMsgList, BigDecimal calPct, String coaMdseTpCdFrt) {

        // TargetLineLevel
        if (bizMsg.xxPgFlg_T.getValue().equals(ZYPConstant.FLG_ON_Y)) {

            // set amount for invLineByKey
            setAmtInvLineByKey(bizMsg, invNum, insInvLineTMsgList, invLineTMsg, calPct);
            // START 2018/06/27 E.Kameishi [QC#26906,ADD]
            String coaMdseTpCd = NFCL3070Query.getInstance().getMdseValue(bizMsg, insInvLineTMsgList.get(0).mdseCd.getValue());
            if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0)
                if ((AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue()) && coaMdseTpCdFrt.equals(coaMdseTpCd))
                        || (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue()) && !coaMdseTpCdFrt.equals(coaMdseTpCd))) {
                    bizMsg.invLineNum.setErrorInfo(1, NFCM0895E);
                    bizMsg.arCrTpCd.setErrorInfo(1, NFCM0895E);
                    return false;
            }
            // END 2018/06/27 E.Kameishi [QC#26906,ADD]
            // set amount for invBolByKey
            // START 2018/05/23 E.Kameishi [QC#21841,ADD]
            setAmtInvBolByKey(bizMsg, invNum, insInvBolTMsgList, invBolTMsg, calPct, invLineTMsg);
            // END 2018/05/23 E.Kameishi [QC#21841,ADD]
        } else {
            // set amount for invLineByCondition
            setAmtInvLineByCondition(bizMsg, invNum, insInvLineTMsgList, invLineTMsgAry, calPct, coaMdseTpCdFrt);

            // set amount for invBolByCondition
            setAmtInvBolByCondition(bizMsg, invNum, insInvBolTMsgList, invBolTMsgAry, calPct);
        }

        // call TaxCalculation API 
        if (!setTaxByTaxCalculationAPI(bizMsg, invNum, insInvLineTMsgList, insInvBolTMsgList, dsInvTpTMsg, dsInvLineTaxDtlTMsgList, coaMdseTpCdFrt)) {
            return false;
        }
        return true;
        
    }

    /**
     * setAmtInvLineByKey
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @param insInvLineTMsgList List<INV_LINETMsg>
     * @param InvLineTMsg INV_LINETMsg
     * @param calPct BigDecimal
     * @return void
     */
    private static void setAmtInvLineByKey(NFCL3070CMsg bizMsg, String invNum, List<INV_LINETMsg> insInvLineTMsgList, INV_LINETMsg invLineTMsg, BigDecimal calPct) {

        // add one line to TMsgList
        // START 2017/01/04 T.Murai [QC#16842,MOD]
        //insInvLineTMsgList.add(invLineTMsg);
        INV_LINETMsg newInvLineTMsg = new INV_LINETMsg();
        EZDTMsg.copy(invLineTMsg, null, newInvLineTMsg, null);
        insInvLineTMsgList.add(newInvLineTMsg);
        // END   2017/01/04 T.Murai [QC#16842,MOD]

        ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).invNum, invNum);
        insInvLineTMsgList.get(0).shpgPlnNum.clear();
        // START 2019/07/24 [QC#51934, ADD]
        ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).compProcStsCd, COMP_PROC_STS_INCOMPLETE);
        // END   2019/07/24 [QC#51934, ADD]

        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
             // calculate amount
             BigDecimal dealNetUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsg.dealNetUnitPrcAmt.getValue(), calPct);
             BigDecimal invLineDealNetCalAmt = calculateAmount(bizMsg, invLineTMsg.invLineDealNetAmt.getValue(), calPct);
             BigDecimal dealDiscUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsg.dealDiscUnitPrcAmt.getValue(), calPct);
             BigDecimal funcNetUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsg.funcNetUnitPrcAmt.getValue(), calPct);
             BigDecimal invLineFuncNetCalAmt = calculateAmount(bizMsg, invLineTMsg.invLineFuncNetAmt.getValue(), calPct);
             BigDecimal funcDiscUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsg.funcDiscUnitPrcAmt.getValue(), calPct);
             //BigDecimal shipCmplCostCalAmt = calculateAmount(bizMsg, invLineTMsg.shipCmplCostAmt.getValue(), calPct); // 2017/10/23 QC#20719 Del
             BigDecimal dealGrsUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsg.dealGrsUnitPrcAmt.getValue(), calPct);
             BigDecimal dealGrsTotPrcCalAmt = calculateAmount(bizMsg, invLineTMsg.dealGrsTotPrcAmt.getValue(), calPct);
             BigDecimal funcGrsUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsg.funcGrsUnitPrcAmt.getValue(), calPct);
             BigDecimal funcGrsTotPrcCalAmt = calculateAmount(bizMsg, invLineTMsg.funcGrsTotPrcAmt.getValue(), calPct);
             // START 2019/09/03 [QC#52945,ADD]
             BigDecimal invLineDealTaxCalAmt = calculateAmount(bizMsg, invLineTMsg.invLineDealTaxAmt.getValue(), calPct);
             BigDecimal invLineFuncTaxCalAmt = calculateAmount(bizMsg, invLineTMsg.invLineFuncTaxAmt.getValue(), calPct);
             // END 2019/09/03 [QC#52945,ADD]
             // calculate amount to TMsg
             ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).dealNetUnitPrcAmt, dealNetUnitPrcCalAmt);
             ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).invLineDealNetAmt, invLineDealNetCalAmt);
             ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).dealDiscUnitPrcAmt, dealDiscUnitPrcCalAmt);
             ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).funcNetUnitPrcAmt, funcNetUnitPrcCalAmt);
             ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).invLineFuncNetAmt, invLineFuncNetCalAmt);
             ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).funcDiscUnitPrcAmt, funcDiscUnitPrcCalAmt);
             //ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).shipCmplCostAmt, shipCmplCostCalAmt); // 2017/10/23 QC#20719 Del
             ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).dealGrsUnitPrcAmt, dealGrsUnitPrcCalAmt);
             ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).dealGrsTotPrcAmt, dealGrsTotPrcCalAmt);
             ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).funcGrsUnitPrcAmt, funcGrsUnitPrcCalAmt);
             ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).funcGrsTotPrcAmt, funcGrsTotPrcCalAmt);
             // START 2019/09/03 [QC#52945,ADD]
             ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).invLineDealTaxAmt, invLineDealTaxCalAmt);
             ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(0).invLineFuncTaxAmt, invLineFuncTaxCalAmt);
             // END 2019/09/03 [QC#52945,ADD]
        }
    }

    /**
     * setAmtInvBolByKey
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @param insInvBolTMsgList List<INV_BOLTMsg>
     * @param invBolTMsg INV_BOLTMsg
     * @param calPct BigDecimal
     * @return void
     */
    private static void setAmtInvBolByKey(NFCL3070CMsg bizMsg, String invNum,  List<INV_BOLTMsg> insInvBolTMsgList, INV_BOLTMsg invBolTMsg, BigDecimal calPct, INV_LINETMsg origInvLintTMsg) {

        // set shipTo
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, invBolTMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm, invBolTMsg.shipToLocNm);

        // add one line to TMsgList
        // START 2017/01/04 T.Murai [QC#16842,MOD]
        // insInvBolTMsgList.add(invBolTMsg);
        INV_BOLTMsg newInvBolTMsg = new INV_BOLTMsg();
        EZDTMsg.copy(invBolTMsg, null, newInvBolTMsg, null);
        insInvBolTMsgList.add(newInvBolTMsg);
        // END   2017/01/04 T.Murai [QC#16842,MOD]

        ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).invNum, invNum);

        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
            // calculate amount
            BigDecimal shipDealDiscCalAmt = calculateAmount(bizMsg, invBolTMsg.shipDealDiscAmt.getValue(), calPct);
            BigDecimal shipDealHdlgChrgCalAmt = calculateAmount(bizMsg, invBolTMsg.shipDealHdlgChrgAmt.getValue(), calPct);
            BigDecimal shipFuncDiscCalAmt = calculateAmount(bizMsg, invBolTMsg.shipFuncDiscAmt.getValue(), calPct);
            BigDecimal shipFuncHdlgChrgCalAmt = calculateAmount(bizMsg, invBolTMsg.shipFuncHdlgChrgAmt.getValue(), calPct);
            // calculate amount to TMsg
            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipDealDiscAmt, shipDealDiscCalAmt);
            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipDealHdlgChrgAmt, shipDealHdlgChrgCalAmt);
            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipFuncDiscAmt, shipFuncDiscCalAmt);
            ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipFuncHdlgChrgAmt, shipFuncHdlgChrgCalAmt);

            // START 2018/05/23 E.Kameishi [QC#21841,ADD]
            if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue()) || !hasValue(bizMsg.arCrTpCd.getValue())) {
                if (INV_LINE_CATG.FREIGHT.equals(origInvLintTMsg.invLineCatgCd.getValue())) {
                    // calculate amount
                    BigDecimal shipDealFrtCalAmt = calculateAmount(bizMsg, invBolTMsg.shipDealFrtAmt.getValue(), calPct);
                    BigDecimal shipFuncFrtCalAmt = calculateAmount(bizMsg, invBolTMsg.shipFuncFrtAmt.getValue(), calPct);
                    // calculate amount to TMsg
                    ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipDealFrtAmt, shipDealFrtCalAmt);
                    ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipFuncFrtAmt, shipFuncFrtCalAmt);
                } else {
                    ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipDealFrtAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipFuncFrtAmt, BigDecimal.ZERO);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipDealFrtAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvBolTMsgList.get(0).shipFuncFrtAmt, BigDecimal.ZERO);
            }
            // END 2018/05/23 E.Kameishi [QC#21841,ADD]
        }
    }

    /**
     * setAmtInvLineByCondition
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @param insInvLineTMsgList INV_LINETMsg
     * @param invLineTMsgAry INV_LINETMsgArray
     * @param calPct BigDecimal
     * @return void
     */
    private static void setAmtInvLineByCondition(NFCL3070CMsg bizMsg, String invNum, List<INV_LINETMsg> insInvLineTMsgList, INV_LINETMsgArray invLineTMsgAry, BigDecimal calPct, String coaMdseTpCdFrt) {

        // initialize amounts
        BigDecimal dealNetUnitPrcTotAmt = BigDecimal.ZERO;
        BigDecimal dealNetUnitPrcCalTotAmt = BigDecimal.ZERO;
        BigDecimal invLineDealNetTotAmt = BigDecimal.ZERO;
        BigDecimal invLineDealNetCalTotAmt = BigDecimal.ZERO;
        BigDecimal dealDiscUnitPrcTotAmt = BigDecimal.ZERO;
        BigDecimal dealDiscUnitPrcCalTotAmt = BigDecimal.ZERO;
        BigDecimal funcNetUnitPrcTotAmt = BigDecimal.ZERO;
        BigDecimal funcNetUnitPrcCalTotAmt = BigDecimal.ZERO;
        BigDecimal invLineFuncNetTotAmt = BigDecimal.ZERO;
        BigDecimal invLineFuncNetCalTotAmt = BigDecimal.ZERO;
        BigDecimal funcDiscUnitPrcTotAmt = BigDecimal.ZERO;
        BigDecimal funcDiscUnitPrcCalTotAmt = BigDecimal.ZERO;
        BigDecimal shipCmplCostTotAmt = BigDecimal.ZERO;
        BigDecimal shipCmplCostCalTotAmt = BigDecimal.ZERO;
        BigDecimal dealGrsUnitPrcTotAmt = BigDecimal.ZERO;
        BigDecimal dealGrsUnitPrcCalTotAmt = BigDecimal.ZERO;
        BigDecimal dealGrsTotPrcTotAmt = BigDecimal.ZERO;
        BigDecimal dealGrsTotPrcCalTotAmt = BigDecimal.ZERO;
        BigDecimal funcGrsUnitPrcTotAmt = BigDecimal.ZERO;
        BigDecimal funcGrsUnitPrcCalTotAmt = BigDecimal.ZERO;
        BigDecimal funcGrsTotPrcTotAmt = BigDecimal.ZERO;
        BigDecimal funcGrsTotPrcCalTotAmt = BigDecimal.ZERO;
        // START 2019/09/03 [QC#52945,ADD]
        BigDecimal invLineDealTaxTotAmt = BigDecimal.ZERO;
        BigDecimal invLineDealTaxCalTotAmt = BigDecimal.ZERO;
        BigDecimal invLineFuncTaxTotAmt = BigDecimal.ZERO;
        BigDecimal invLineFuncTaxCalTotAmt = BigDecimal.ZERO;
        // END 2019/09/03 [QC#52945,ADD]

        // set original TMsg Array to copy to TMsg
        for (int i = 0; i < invLineTMsgAry.getValidCount(); i++) {
            INV_LINETMsg insInvLineTMsg = new INV_LINETMsg();

            EZDTMsg.copy(invLineTMsgAry.get(i), null, insInvLineTMsg, null);
            ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invNum, invNum);
            insInvLineTMsg.shpgPlnNum.clear();
            // START 2019/07/24 [QC#51934, ADD]
            ZYPEZDItemValueSetter.setValue(insInvLineTMsg.compProcStsCd, COMP_PROC_STS_INCOMPLETE);
            // END   2019/07/24 [QC#51934, ADD]

            if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
                // START 2018/07/13 E.Kameishi [QC#27007,MOD]
                // calculate amount
                BigDecimal dealNetUnitPrcCalAmt = BigDecimal.ZERO;
                BigDecimal invLineDealNetCalAmt = BigDecimal.ZERO;
                BigDecimal dealDiscUnitPrcCalAmt = BigDecimal.ZERO;
                BigDecimal funcNetUnitPrcCalAmt = BigDecimal.ZERO;
                BigDecimal invLineFuncNetCalAmt = BigDecimal.ZERO;
                BigDecimal funcDiscUnitPrcCalAmt = BigDecimal.ZERO;
                BigDecimal dealGrsUnitPrcCalAmt = BigDecimal.ZERO;
                BigDecimal dealGrsTotPrcCalAmt = BigDecimal.ZERO;
                BigDecimal funcGrsUnitPrcCalAmt = BigDecimal.ZERO;
                BigDecimal funcGrsTotPrcCalAmt = BigDecimal.ZERO;

                String coaMdseTpCd = NFCL3070Query.getInstance().getMdseValue(bizMsg, invLineTMsgAry.no(i).mdseCd.getValue());
                if (AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue()) && !coaMdseTpCdFrt.equals(coaMdseTpCd)
                        || AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue()) && coaMdseTpCdFrt.equals(coaMdseTpCd)
//                        || AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue()) // DEL 2019/09/05 [QC#52945]
                        || !hasValue(bizMsg.arCrTpCd.getValue())) {
                    // calculate amount
                    dealNetUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).dealNetUnitPrcAmt.getValue(), calPct);
                    invLineDealNetCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).invLineDealNetAmt.getValue(), calPct);
                    dealDiscUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).dealDiscUnitPrcAmt.getValue(), calPct);
                    funcNetUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).funcNetUnitPrcAmt.getValue(), calPct);
                    invLineFuncNetCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).invLineFuncNetAmt.getValue(), calPct);
                    funcDiscUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).funcDiscUnitPrcAmt.getValue(), calPct);
                    dealGrsUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).dealGrsUnitPrcAmt.getValue(), calPct);
                    dealGrsTotPrcCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).dealGrsTotPrcAmt.getValue(), calPct);
                    funcGrsUnitPrcCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).funcGrsUnitPrcAmt.getValue(), calPct);
                    funcGrsTotPrcCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).funcGrsTotPrcAmt.getValue(), calPct);
                }
                // END 2018/07/13 E.Kameishi [QC#27007,MOD]
                // calculate amount to TMsg
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealNetUnitPrcAmt, dealNetUnitPrcCalAmt);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invLineDealNetAmt, invLineDealNetCalAmt);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealDiscUnitPrcAmt, dealDiscUnitPrcCalAmt);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcNetUnitPrcAmt, funcNetUnitPrcCalAmt);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invLineFuncNetAmt, invLineFuncNetCalAmt);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcDiscUnitPrcAmt, funcDiscUnitPrcCalAmt);
                //ZYPEZDItemValueSetter.setValue(insInvLineTMsg.shipCmplCostAmt, shipCmplCostCalAmt); // 2017/10/23 QC#20719 Del
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealGrsUnitPrcAmt, dealGrsUnitPrcCalAmt);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealGrsTotPrcAmt, dealGrsTotPrcCalAmt);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcGrsUnitPrcAmt, funcGrsUnitPrcCalAmt);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcGrsTotPrcAmt, funcGrsTotPrcCalAmt);
                // set original amount and calculated amount

                // START 2019/06/06 [QC#50631, MOD]
                if (AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue()) && !coaMdseTpCdFrt.equals(coaMdseTpCd)
                        || AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue()) && coaMdseTpCdFrt.equals(coaMdseTpCd)
//                        || AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue()) // DEL 2019/09/05 [QC#52945]
                        || !hasValue(bizMsg.arCrTpCd.getValue())) {

                    dealNetUnitPrcTotAmt = dealNetUnitPrcTotAmt.add(addAmount(invLineTMsgAry.no(i).dealNetUnitPrcAmt.getValue()));
                    dealNetUnitPrcCalTotAmt = dealNetUnitPrcCalTotAmt.add(dealNetUnitPrcCalAmt);
                    invLineDealNetTotAmt = invLineDealNetTotAmt.add(addAmount(invLineTMsgAry.no(i).invLineDealNetAmt.getValue()));
                    invLineDealNetCalTotAmt = invLineDealNetCalTotAmt.add(invLineDealNetCalAmt);
                    dealDiscUnitPrcTotAmt = dealDiscUnitPrcTotAmt.add(addAmount(invLineTMsgAry.no(i).dealDiscUnitPrcAmt.getValue()));
                    dealDiscUnitPrcCalTotAmt = dealDiscUnitPrcCalTotAmt.add(dealDiscUnitPrcCalAmt);
                    funcNetUnitPrcTotAmt = funcNetUnitPrcTotAmt.add(addAmount(invLineTMsgAry.no(i).funcNetUnitPrcAmt.getValue()));
                    funcNetUnitPrcCalTotAmt = funcNetUnitPrcCalTotAmt.add(funcNetUnitPrcCalAmt);
                    invLineFuncNetTotAmt = invLineFuncNetTotAmt.add(addAmount(invLineTMsgAry.no(i).invLineFuncNetAmt.getValue()));
                    invLineFuncNetCalTotAmt = invLineFuncNetCalTotAmt.add(invLineFuncNetCalAmt);
                    funcDiscUnitPrcTotAmt = funcDiscUnitPrcTotAmt.add(addAmount(invLineTMsgAry.no(i).funcDiscUnitPrcAmt.getValue()));
                    funcDiscUnitPrcCalTotAmt = funcDiscUnitPrcCalTotAmt.add(funcDiscUnitPrcCalAmt);
                    //shipCmplCostTotAmt = shipCmplCostTotAmt.add(addAmount(invLineTMsgAry.no(i).shipCmplCostAmt.getValue())); // 2017/10/23 QC#20719 Del
                    //shipCmplCostCalTotAmt = shipCmplCostCalTotAmt.add(shipCmplCostCalAmt); // 2017/10/23 QC#20719 Del
                    dealGrsUnitPrcTotAmt = dealGrsUnitPrcTotAmt.add(addAmount(invLineTMsgAry.no(i).dealGrsUnitPrcAmt.getValue()));
                    dealGrsUnitPrcCalTotAmt = dealGrsUnitPrcCalTotAmt.add(dealGrsUnitPrcCalAmt);
                    dealGrsTotPrcTotAmt = dealGrsTotPrcTotAmt.add(addAmount(invLineTMsgAry.no(i).dealGrsTotPrcAmt.getValue()));
                    dealGrsTotPrcCalTotAmt = dealGrsTotPrcCalTotAmt.add(dealGrsTotPrcCalAmt);
                    funcGrsUnitPrcTotAmt = funcGrsUnitPrcTotAmt.add(addAmount(invLineTMsgAry.no(i).funcGrsUnitPrcAmt.getValue()));
                    funcGrsUnitPrcCalTotAmt = funcGrsUnitPrcCalTotAmt.add(funcGrsUnitPrcCalAmt);
                    funcGrsTotPrcTotAmt = funcGrsTotPrcTotAmt.add(addAmount(invLineTMsgAry.no(i).funcGrsTotPrcAmt.getValue()));
                    funcGrsTotPrcCalTotAmt = funcGrsTotPrcCalTotAmt.add(funcGrsTotPrcCalAmt);
                }
                // END   2019/06/06 [QC#50631, MOD]

                // START 2019/09/05 [QC#52945,ADD]
                if (AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {
                    BigDecimal invLineDealTaxCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).invLineDealTaxAmt.getValue(), calPct);
                    BigDecimal invLineFuncTaxCalAmt = calculateAmount(bizMsg, invLineTMsgAry.no(i).invLineFuncTaxAmt.getValue(), calPct);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invLineDealTaxAmt, invLineDealTaxCalAmt);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invLineFuncTaxAmt, invLineFuncTaxCalAmt);
                    invLineDealTaxTotAmt = invLineDealTaxTotAmt.add(addAmount(invLineTMsgAry.no(i).invLineDealTaxAmt.getValue()));
                    invLineDealTaxCalTotAmt = invLineDealTaxCalTotAmt.add(invLineDealTaxCalAmt);
                    invLineFuncTaxTotAmt = invLineFuncTaxTotAmt.add(addAmount(invLineTMsgAry.no(i).invLineFuncTaxAmt.getValue()));
                    invLineFuncTaxCalTotAmt = invLineFuncTaxCalTotAmt.add(invLineFuncTaxCalAmt);
                } 
                // END 2019/09/05 [QC#52945,ADD]
            }
            // add insInvLineTMsgList
            insInvLineTMsgList.add(insInvLineTMsg);
        }

        // START 2019/06/05 [QC#50631, MOD]
        // if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue())) {
        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0
        // START 2023/03/10 D.Mamaril [QC#61119,MOD]
        //        && (AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue()) || AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue()))) {
                && (AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue()) || AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue()) || !hasValue(bizMsg.arCrTpCd.getValue()))) {
        // END 2023/03/10 D.Mamaril [QC#61119,MOD]
        // END   2019/06/05 [QC#50631, MOD]
            // adjustment of tolerance
            BigDecimal adjustDealNetUnitPrcAmt = calculateAmount(bizMsg, dealNetUnitPrcTotAmt, calPct).subtract(dealNetUnitPrcCalTotAmt);
            BigDecimal adjustInvLineDealNetAmt = calculateAmount(bizMsg, invLineDealNetTotAmt, calPct).subtract(invLineDealNetCalTotAmt);
            BigDecimal adjustDealDiscUnitPrcAmt = calculateAmount(bizMsg, dealDiscUnitPrcTotAmt, calPct).subtract(dealDiscUnitPrcCalTotAmt);
            BigDecimal adjustFuncNetUnitPrcAmt = calculateAmount(bizMsg, funcNetUnitPrcTotAmt, calPct).subtract(funcNetUnitPrcCalTotAmt);
            BigDecimal adjustInvLineFuncNetAmt = calculateAmount(bizMsg, invLineFuncNetTotAmt, calPct).subtract(invLineFuncNetCalTotAmt);
            BigDecimal adjustFuncDiscUnitPrcAmt = calculateAmount(bizMsg, funcDiscUnitPrcTotAmt, calPct).subtract(funcDiscUnitPrcCalTotAmt);
            BigDecimal adjustShipCmplCostAmt = calculateAmount(bizMsg, shipCmplCostTotAmt, calPct).subtract(shipCmplCostCalTotAmt);
            BigDecimal adjustDealGrsUnitPrcAmt = calculateAmount(bizMsg, dealGrsUnitPrcTotAmt, calPct).subtract(dealGrsUnitPrcCalTotAmt);
            BigDecimal adjustDealGrsTotPrcAmt = calculateAmount(bizMsg, dealGrsTotPrcTotAmt, calPct).subtract(dealGrsTotPrcCalTotAmt);
            BigDecimal adjustFuncGrsUnitPrcAmt = calculateAmount(bizMsg, funcGrsUnitPrcTotAmt, calPct).subtract(funcGrsUnitPrcCalTotAmt);
            BigDecimal adjustFuncGrsTotPrcAmt = calculateAmount(bizMsg, funcGrsTotPrcTotAmt, calPct).subtract(funcGrsTotPrcCalTotAmt);

            // START 2019/06/05 [QC#50631, MOD]
            // INV_LINETMsg insInvLineTMsg = insInvLineTMsgList.get(invLineTMsgAry.getValidCount() - 1);
            int adjLineIdx = invLineTMsgAry.getValidCount() - 1;

            // START 2023/03/10 D.Mamaril [QC#61119,ADD]
            int largestAmtIdx = adjLineIdx;
            BigDecimal largestAmt = insInvLineTMsgList.get(largestAmtIdx).dealNetUnitPrcAmt.getValue();
            BigDecimal currentAmt;
            // END 2023/03/10 D.Mamaril [QC#61119,ADD]

            for (adjLineIdx = invLineTMsgAry.getValidCount() - 1; adjLineIdx >= 0; --adjLineIdx) {
                // START 2023/03/10 D.Mamaril [QC#61119,ADD]
                currentAmt = insInvLineTMsgList.get(adjLineIdx).dealNetUnitPrcAmt.getValue();
                if (currentAmt.compareTo(largestAmt) > 0) {
                    largestAmtIdx = adjLineIdx;
                    largestAmt = currentAmt;
                }
                // END 2023/03/10 D.Mamaril [QC#61119,ADD]

                String coaMdseTpCd = NFCL3070Query.getInstance().getMdseValue(bizMsg, invLineTMsgAry.no(adjLineIdx).mdseCd.getValue());

                if ((AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue()) && !coaMdseTpCdFrt.equals(coaMdseTpCd))
                        || (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue()) && coaMdseTpCdFrt.equals(coaMdseTpCd))) {
                    break;
                }
            }
            if (adjLineIdx < 0) {
                // START 2023/03/10 D.Mamaril [QC#61119,MOD]
                //adjLineIdx = invLineTMsgAry.getValidCount() - 1;
                adjLineIdx = largestAmtIdx;
                // END 2023/03/10 D.Mamaril [QC#61119,MOD]
            }
            INV_LINETMsg insInvLineTMsg = insInvLineTMsgList.get(adjLineIdx);
            // END   2019/06/05 [QC#50631, MOD]

            // set adjusted amount to TMsg
            if (adjustDealNetUnitPrcAmt.compareTo(BigDecimal.ZERO) != 0) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealNetUnitPrcAmt, insInvLineTMsg.dealNetUnitPrcAmt.getValue().add(adjustDealNetUnitPrcAmt));
            }
            if (adjustInvLineDealNetAmt.compareTo(BigDecimal.ZERO) != 0) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invLineDealNetAmt, insInvLineTMsg.invLineDealNetAmt.getValue().add(adjustInvLineDealNetAmt));
            }
            if (adjustDealDiscUnitPrcAmt.compareTo(BigDecimal.ZERO) != 0) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealDiscUnitPrcAmt, insInvLineTMsg.dealDiscUnitPrcAmt.getValue().add(adjustDealDiscUnitPrcAmt));
            }
            if (adjustFuncNetUnitPrcAmt.compareTo(BigDecimal.ZERO) != 0) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcNetUnitPrcAmt, insInvLineTMsg.funcNetUnitPrcAmt.getValue().add(adjustFuncNetUnitPrcAmt));
            }
            if (adjustInvLineFuncNetAmt.compareTo(BigDecimal.ZERO) != 0) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invLineFuncNetAmt, insInvLineTMsg.invLineFuncNetAmt.getValue().add(adjustInvLineFuncNetAmt));
            }
            if (adjustFuncDiscUnitPrcAmt.compareTo(BigDecimal.ZERO) != 0) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcDiscUnitPrcAmt, insInvLineTMsg.funcDiscUnitPrcAmt.getValue().add(adjustFuncDiscUnitPrcAmt));
            }
            // 2017/10/23 QC#20719 Del Start
            //if (adjustShipCmplCostAmt.compareTo(BigDecimal.ZERO) != 0) {
            //    ZYPEZDItemValueSetter.setValue(insInvLineTMsg.shipCmplCostAmt, insInvLineTMsg.shipCmplCostAmt.getValue().add(adjustShipCmplCostAmt));
            //}
            // 2017/10/23 QC#20719 Del End
            if (adjustDealGrsUnitPrcAmt.compareTo(BigDecimal.ZERO) != 0) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealGrsUnitPrcAmt, insInvLineTMsg.dealGrsUnitPrcAmt.getValue().add(adjustDealGrsUnitPrcAmt));
            }
            if (adjustDealGrsTotPrcAmt.compareTo(BigDecimal.ZERO) != 0) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealGrsTotPrcAmt, insInvLineTMsg.dealGrsTotPrcAmt.getValue().add(adjustDealGrsTotPrcAmt));
            }
            if (adjustFuncGrsUnitPrcAmt.compareTo(BigDecimal.ZERO) != 0) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcGrsUnitPrcAmt, insInvLineTMsg.funcGrsUnitPrcAmt.getValue().add(adjustFuncGrsUnitPrcAmt));
            }
            if (adjustFuncGrsTotPrcAmt.compareTo(BigDecimal.ZERO) != 0) {
                // START 2018/06/15 E.Kameishi [QC#26723,MOD]
                //ZYPEZDItemValueSetter.setValue(insInvLineTMsg.dealNetUnitPrcAmt, insInvLineTMsg.funcGrsTotPrcAmt.getValue().add(adjustFuncGrsTotPrcAmt));
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.funcGrsTotPrcAmt, insInvLineTMsg.funcGrsTotPrcAmt.getValue().add(adjustFuncGrsTotPrcAmt));
                // START 2018/06/15 E.Kameishi [QC#26723,MOD]
            }
        }

        // START 2019/09/05 [QC#52945,ADD]
        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {

            BigDecimal adjustInvLineDealTaxAmt = calculateAmount(bizMsg, invLineDealTaxTotAmt, calPct).subtract(invLineDealTaxCalTotAmt);
            BigDecimal adjustInvLineFuncTaxAmt = calculateAmount(bizMsg, invLineFuncTaxTotAmt, calPct).subtract(invLineFuncTaxCalTotAmt);

            int adjLineIdx = invLineTMsgAry.getValidCount() - 1;
            for (adjLineIdx = invLineTMsgAry.getValidCount() - 1; adjLineIdx >= 0; --adjLineIdx) {

                if (BigDecimal.ZERO.compareTo(invLineTMsgAry.no(adjLineIdx).invLineDealTaxAmt.getValue()) != 0 && //
                        BigDecimal.ZERO.compareTo(invLineTMsgAry.no(adjLineIdx).invLineDealTaxAmt.getValue().add(adjustInvLineDealTaxAmt)) < 0) {
                    break;
                }
            }
            if (adjLineIdx < 0) {
                adjLineIdx = invLineTMsgAry.getValidCount() - 1;
            }
            INV_LINETMsg insInvLineTMsg = insInvLineTMsgList.get(adjLineIdx);

            if (adjustInvLineDealTaxAmt.compareTo(BigDecimal.ZERO) != 0) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invLineDealTaxAmt, insInvLineTMsg.invLineDealTaxAmt.getValue().add(adjustInvLineDealTaxAmt));
            }
            if (adjustInvLineFuncTaxAmt.compareTo(BigDecimal.ZERO) != 0) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsg.invLineFuncTaxAmt, insInvLineTMsg.invLineFuncTaxAmt.getValue().add(adjustInvLineFuncTaxAmt));
            }
        }
        // END 2019/09/05 [QC#52945,ADD]

        // START 2019/03/27 E.Kameishi [QC#30904,ADD]
        for (int i = 0; i < invLineTMsgAry.getValidCount(); i++) {
            String coaMdseTpCd = NFCL3070Query.getInstance().getMdseValue(bizMsg, insInvLineTMsgList.get(i).mdseCd.getValue());
            if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
                if (!coaMdseTpCdFrt.equals(coaMdseTpCd)) {
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealNetUnitPrcAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).invLineDealNetAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealDiscUnitPrcAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcNetUnitPrcAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).invLineFuncNetAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcDiscUnitPrcAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealGrsUnitPrcAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealGrsTotPrcAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcGrsUnitPrcAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcGrsTotPrcAmt, BigDecimal.ZERO);
                }
            }
            // for Revievables
            if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue())) {
                if (coaMdseTpCdFrt.equals(coaMdseTpCd)) {
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealNetUnitPrcAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).invLineDealNetAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealDiscUnitPrcAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcNetUnitPrcAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).invLineFuncNetAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcDiscUnitPrcAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealGrsUnitPrcAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealGrsTotPrcAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcGrsUnitPrcAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcGrsTotPrcAmt, BigDecimal.ZERO);
                }
            }
            // START 2019/09/05 [QC#52945,ADD]
            // for Tax
            if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealNetUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).invLineDealNetAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealDiscUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcNetUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).invLineFuncNetAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcDiscUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealGrsUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).dealGrsTotPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcGrsUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insInvLineTMsgList.get(i).funcGrsTotPrcAmt, BigDecimal.ZERO);
            }
            // END 2019/09/05 [QC#52945,ADD]
        }
        // END 2019/03/27 E.Kameishi [QC#30904,ADD]
    }

    /**
     * setAmtInvBolByCondition
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @param insInvBolTMsgList INV_BOLTMsg
     * @param invBolTMsgAry INV_BOLTMsgArray
     * @param calPct BigDecimal
     * @return void
     */
    private static void setAmtInvBolByCondition(NFCL3070CMsg bizMsg, String invNum, List<INV_BOLTMsg> insInvBolTMsgList, INV_BOLTMsgArray invBolTMsgAry, BigDecimal calPct) {

        // initialize amounts
        BigDecimal shipDealDiscTotAmt = BigDecimal.ZERO;
        BigDecimal shipDealDiscCalTotAmt = BigDecimal.ZERO;
        BigDecimal shipDealHdlgChrTotgAmt = BigDecimal.ZERO;
        BigDecimal shipDealHdlgChrCalTotgAmt = BigDecimal.ZERO;
        BigDecimal shipFuncDiscTotAmt = BigDecimal.ZERO;
        BigDecimal shipFuncDiscCalTotAmt = BigDecimal.ZERO;
        BigDecimal shipFuncHdlgChrgTotAmt = BigDecimal.ZERO;
        BigDecimal shipFuncHdlgChrgCalTotAmt = BigDecimal.ZERO;

        BigDecimal shipDealFrtTotAmt = BigDecimal.ZERO;
        BigDecimal shipDealFrtCaltotAmt = BigDecimal.ZERO;
        BigDecimal shipFuncFrtTotAmt = BigDecimal.ZERO;
        BigDecimal shipFuncFrtCalTotAmt = BigDecimal.ZERO;

        // set original TMsg Array to copy to TMsg
        for (int i = 0; i < invBolTMsgAry.getValidCount(); i++) {
            INV_BOLTMsg insInvBolTMsg = new INV_BOLTMsg();

            EZDTMsg.copy(invBolTMsgAry.get(i), null, insInvBolTMsg, null);
            ZYPEZDItemValueSetter.setValue(insInvBolTMsg.invNum, invNum);

            if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
                // calculate amount
                BigDecimal shipDealDiscCalAmt = calculateAmount(bizMsg, invBolTMsgAry.no(i).shipDealDiscAmt.getValue(), calPct);
                BigDecimal shipDealHdlgChrgCalAmt = calculateAmount(bizMsg, invBolTMsgAry.no(i).shipDealHdlgChrgAmt.getValue(), calPct);
                BigDecimal shipFuncDiscCalAmt = calculateAmount(bizMsg, invBolTMsgAry.no(i).shipFuncDiscAmt.getValue(), calPct);
                BigDecimal shipFuncHdlgChrgCalAmt = calculateAmount(bizMsg, invBolTMsgAry.no(i).shipFuncHdlgChrgAmt.getValue(), calPct);
                // calculate amount to TMsg
                ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipDealDiscAmt, shipDealDiscCalAmt);
                ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipDealHdlgChrgAmt, shipDealHdlgChrgCalAmt);
                ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipFuncDiscAmt, shipFuncDiscCalAmt);
                ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipFuncHdlgChrgAmt, shipFuncHdlgChrgCalAmt);
                // set original amount and calculated amount
                shipDealDiscTotAmt = shipDealDiscTotAmt.add(addAmount(invBolTMsgAry.no(i).shipDealDiscAmt.getValue()));
                shipDealDiscCalTotAmt = shipDealDiscCalTotAmt.add(shipDealDiscCalAmt);
                shipDealHdlgChrTotgAmt = shipDealHdlgChrTotgAmt.add(addAmount(invBolTMsgAry.no(i).shipDealHdlgChrgAmt.getValue()));
                shipDealHdlgChrCalTotgAmt = shipDealHdlgChrCalTotgAmt.add(shipDealHdlgChrgCalAmt);
                shipFuncDiscTotAmt = shipFuncDiscTotAmt.add(addAmount(invBolTMsgAry.no(i).shipFuncDiscAmt.getValue()));
                shipFuncDiscCalTotAmt = shipFuncDiscCalTotAmt.add(shipFuncDiscCalAmt);
                shipFuncHdlgChrgTotAmt = shipFuncHdlgChrgTotAmt.add(addAmount(invBolTMsgAry.no(i).shipFuncHdlgChrgAmt.getValue()));
                shipFuncHdlgChrgCalTotAmt = shipFuncHdlgChrgCalTotAmt.add(shipFuncHdlgChrgCalAmt);

                if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue()) || !hasValue(bizMsg.arCrTpCd.getValue())) {
                    // calculate amount
                    BigDecimal shipDealFrtCalAmt = calculateAmount(bizMsg, invBolTMsgAry.no(i).shipDealFrtAmt.getValue(), calPct);
                    BigDecimal shipFuncFrtCalAmt = calculateAmount(bizMsg, invBolTMsgAry.no(i).shipFuncFrtAmt.getValue(), calPct);
                    // calculate amount to TMsg
                    ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipDealFrtAmt, shipDealFrtCalAmt);
                    ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipFuncFrtAmt, shipFuncFrtCalAmt);
                    // set original amount and calculated amount
                    shipDealFrtTotAmt = shipDealFrtTotAmt.add(addAmount(invBolTMsgAry.no(i).shipDealFrtAmt.getValue()));
                    shipDealFrtCaltotAmt = shipDealFrtCaltotAmt.add(shipDealFrtCalAmt);
                    shipFuncFrtTotAmt = shipFuncFrtTotAmt.add(addAmount(invBolTMsgAry.no(i).shipFuncFrtAmt.getValue()));
                    shipFuncFrtCalTotAmt = shipFuncFrtCalTotAmt.add(shipFuncFrtCalAmt);

                } else {
                    ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipDealFrtAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipFuncFrtAmt, BigDecimal.ZERO);
                }
            }

            // add insInvLineTMsgList
            insInvBolTMsgList.add(insInvBolTMsg);
        }

        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
            // adjustment of tolerance
            BigDecimal adjustShipDealDiscAmt = calculateAmount(bizMsg, shipDealDiscTotAmt, calPct).subtract(shipDealDiscCalTotAmt);
            BigDecimal adjustShipDealHdlgChrgAmt = calculateAmount(bizMsg, shipDealHdlgChrTotgAmt, calPct).subtract(shipDealHdlgChrCalTotgAmt);
            BigDecimal adjustShipFuncDiscAmt = calculateAmount(bizMsg, shipFuncDiscTotAmt, calPct).subtract(shipFuncDiscCalTotAmt);
            BigDecimal adjustShipFuncHdlgChrgAmt = calculateAmount(bizMsg, shipFuncHdlgChrgTotAmt, calPct).subtract(shipFuncHdlgChrgCalTotAmt);

            // START 2023/03/10 D.Mamaril [QC#61119,MOD]
            //INV_BOLTMsg insInvBolTMsg = insInvBolTMsgList.get(invBolTMsgAry.getValidCount() - 1);
            int largestAmtIdx = invBolTMsgAry.getValidCount() - 1;
            BigDecimal largestAmt = insInvBolTMsgList.get(largestAmtIdx).shipDealSlsAmt.getValue();
            BigDecimal currentAmt;

            for (int i = largestAmtIdx; i >= 0; i--) {
                currentAmt = insInvBolTMsgList.get(i).shipDealSlsAmt.getValue();
                if (currentAmt.compareTo(largestAmt) > 0) {
                    largestAmtIdx = i;
                    largestAmt = currentAmt;
                }
            }
            INV_BOLTMsg insInvBolTMsg = insInvBolTMsgList.get(largestAmtIdx);
            // END 2023/03/10 D.Mamaril [QC#61119,MOD]

            // set adjusted amount to TMsg
            if (adjustShipDealDiscAmt.compareTo(BigDecimal.ZERO) != 0) {
                ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipDealDiscAmt, insInvBolTMsg.shipDealDiscAmt.getValue().add(adjustShipDealDiscAmt));
            }
            if (adjustShipDealHdlgChrgAmt.compareTo(BigDecimal.ZERO) != 0) {
                ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipDealHdlgChrgAmt, insInvBolTMsg.shipDealHdlgChrgAmt.getValue().add(adjustShipDealHdlgChrgAmt));
            }
            if (adjustShipFuncDiscAmt.compareTo(BigDecimal.ZERO) != 0) {
                ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipFuncDiscAmt, insInvBolTMsg.shipFuncDiscAmt.getValue().add(adjustShipFuncDiscAmt));
            }
            if (adjustShipFuncHdlgChrgAmt.compareTo(BigDecimal.ZERO) != 0) {
                ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipFuncHdlgChrgAmt, insInvBolTMsg.shipFuncHdlgChrgAmt.getValue().add(adjustShipFuncHdlgChrgAmt));
            }
            if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue()) || !hasValue(bizMsg.arCrTpCd.getValue())) {
                // adjustment of tolerance
                BigDecimal adjustShipDealFrtAmt = calculateAmount(bizMsg, shipDealFrtTotAmt, calPct).subtract(shipDealFrtCaltotAmt);
                BigDecimal adjustShipFuncFrtAmt = calculateAmount(bizMsg, shipFuncFrtTotAmt, calPct).subtract(shipFuncFrtCalTotAmt);
                // set adjusted amount to TMsg
                if (adjustShipDealFrtAmt.compareTo(BigDecimal.ZERO) != 0) {
                    ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipDealFrtAmt, insInvBolTMsg.shipDealFrtAmt.getValue().add(adjustShipDealFrtAmt));
                }
                if (adjustShipFuncFrtAmt.compareTo(BigDecimal.ZERO) != 0) {
                    ZYPEZDItemValueSetter.setValue(insInvBolTMsg.shipFuncFrtAmt, insInvBolTMsg.shipFuncFrtAmt.getValue().add(adjustShipFuncFrtAmt));
                }
            }
        }
    }

    /**
     * getInvLineTMsgAry
     * 
     * @param bizMsg NFCL3070CMsg
     * @return tMsg INV_LINETMsgArray
     */
    private static INV_LINETMsgArray getInvLineTMsgAry(NFCL3070CMsg bizMsg) {
        // get original TMsg Array
        INV_LINETMsg tMsg = new INV_LINETMsg();

        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("invNum01", bizMsg.origInvNum.getValue());

        return (INV_LINETMsgArray) EZDTBLAccessor.findByCondition(tMsg);
    }

    /**
     * getInvBolTMsgAry
     * 
     * @param bizMsg NFCL3070CMsg
     * @return tMsg INV_BOLTMsgArray
     */
    private static INV_BOLTMsgArray getInvBolTMsgAry(NFCL3070CMsg bizMsg) {
        // get original TMsg Array
        INV_BOLTMsg tMsg = new INV_BOLTMsg();

        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("invNum01", bizMsg.origInvNum.getValue());

        return (INV_BOLTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

    }
    // END   2016/10/07 S.Fujita [QC#10522,MOD]
    //START 2018/05/30 E.Kameishi [QC#26229,MOD]
    /**
     * existsCITicket
     * @param bizMsg NFCL3070CMsg
     * @return boolean
     */
    public static boolean checkCITicket(NFCL3070CMsg bizMsg) {
        // call EZTblAccessor
        Map<String, Object> mapRes = NFCL3070Query.getInstance().checkCINum(bizMsg);

        // has no results
        if (mapRes == null) {
            return false;
        }

        return true;
    }

    /**
     * checkArCrRebilValue
     * @param bizMsg NFCL3070CMsg
     * @return boolean
     */
    public static boolean checkArCrRebilValue(NFCL3070CMsg bizMsg, INVTMsg origInvTMsg) {

        // START 2020/02/29 [QC#55872,ADD]
        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.xxPgFlg_C.getValue())) {
            // only credit memo should be checked
            return true;
        }
        // END 2020/02/29 [QC#55872,ADD]

        // call EZTblAccessor
        Map<String, Object> mapRes = NFCL3070Query.getInstance().getArCrRebilValue(bizMsg);

        // has no results
        if (mapRes == null || mapRes.size() == 0) {
            return true;
        }

        BigDecimal sumCrRebilAmt = (BigDecimal) mapRes.get("CR_REBIL_AMT");
        // MOD START 2019/08/27 QC#53032
        // BigDecimal sumOrigInvAmt = origInvTMsg.invTotDealSlsAmt.getValue();
        BigDecimal sumOrigInvAmt = origInvTMsg.invTotDealNetAmt.getValue();
        // MOD END 2019/08/27 QC#53032
        BigDecimal sumRcvCrRebilAmt = (BigDecimal) mapRes.get("RCV_CR_REBIL_AMT");
        // MOD END 2019/09/11 QC#53032
//      BigDecimal sumRcvOrigInvAmt = origInvTMsg.invTotDealSlsAmt.getValue();
        BigDecimal sumRcvOrigInvAmt = origInvTMsg.invTotDealSlsAmt.getValue().add(origInvTMsg.invTotDealDiscAmt.getValue());
        // MOD END 2019/09/11 QC#53032
        BigDecimal sumTaxCrRebilAmt = (BigDecimal) mapRes.get("TAX_CR_REBIL_AMT");
        BigDecimal sumTaxOrigInvAmt = origInvTMsg.invTotDealTaxAmt.getValue();
        BigDecimal sumFrtCrRebilAmt = (BigDecimal) mapRes.get("FRT_CR_REBIL_AMT");
        BigDecimal sumFrtOrigInvAmt = origInvTMsg.invTotDealFrtAmt.getValue();

        // START 2020/02/29 [QC#55872,ADD]
        sumCrRebilAmt = sumCrRebilAmt.add(bizMsg.crRebilAmt_CA.getValue());
        if (AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {
            sumTaxCrRebilAmt = sumTaxCrRebilAmt.add(bizMsg.crRebilAmt_CA.getValue());
        } else if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
            sumFrtCrRebilAmt = sumFrtCrRebilAmt.add(bizMsg.crRebilAmt_CA.getValue());
        } else if (AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue())) {
            sumRcvCrRebilAmt = sumRcvCrRebilAmt.add(bizMsg.crRebilAmt_CA.getValue());
        }
        // END 2020/02/29 [QC#55872,ADD]

        if (sumCrRebilAmt.compareTo(sumOrigInvAmt) > 0
                || sumRcvCrRebilAmt.compareTo(sumRcvOrigInvAmt) > 0
                || sumTaxCrRebilAmt.compareTo(sumTaxOrigInvAmt) > 0
                || sumFrtCrRebilAmt.compareTo(sumFrtOrigInvAmt) > 0) {
            return false;
        } else {
            return true;
        }
    }

    //START 2018/05/30 E.Kameishi [QC#26229,ADD]
    public static boolean callAcctDistAPI(NFCL3070CMsg bizMsg, String invNum) {

        NFZC103001 invDistAPI = new NFZC103001();

        NFZC103001PMsg pMsg = new NFZC103001PMsg();
        setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        setValue(pMsg.invNum, invNum);
        setValue(pMsg.procDt, ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
        
        // call one by one to update INV.
        invDistAPI.execute(pMsg, ONBATCH_TYPE.ONLINE);
        
        if (pMsg.xxMsgIdList.getValidCount() == 0) {
            
            // no error
            INVTMsg invTmsg = new INVTMsg();
            
            setValue(invTmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            setValue(invTmsg.invNum, invNum);
            invTmsg = (INVTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(invTmsg);

            // START 2018/08/21 E.Kameishi [QC#27688,MOD]
            if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            //if ((RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && (hasValue(bizMsg.arAutoCashAppFlg) && ZYPConstant.FLG_ON_Y.equals(bizMsg.arAutoCashAppFlg.getValue())))
            //        || (RQST_TP_BOTH.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPgFlg_C.getValue()))) {
                // update FNLZ_INV_FLG of INV table
                ZYPEZDItemValueSetter.setValue(invTmsg.fnlzInvFlg, ZYPConstant.FLG_ON_Y);
                EZDTBLAccessor.update(invTmsg);
            }
            // END 2018/08/21 E.Kameishi [QC#27688,MOD]

            AJE_INV_ACCT_DISTTMsg inMsg = new AJE_INV_ACCT_DISTTMsg();

            inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("invNum01", invTmsg.invNum.getValue());
            inMsg.setSQLID("001");

            AJE_INV_ACCT_DISTTMsgArray outMsgArray = (AJE_INV_ACCT_DISTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

            if (outMsgArray == null || outMsgArray.length() <= 0) {
                // Basically it can not be an error.
                bizMsg.setMessageInfo(NFAM0035E, new String[] {"Invoice Accounting Date Update", "account distribution cannot be retrieved."});
                return false;
            } else {
                // START 2018/08/21 E.Kameishi [QC#27688,ADD]
                for (int i = 0; i < outMsgArray.getValidCount(); i++) {
                    if ((RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && (hasValue(bizMsg.arAutoCashAppFlg) && ZYPConstant.FLG_ON_Y.equals(bizMsg.arAutoCashAppFlg.getValue())))
                            || (RQST_TP_BOTH.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPgFlg_C.getValue()))) {
                        ZYPEZDItemValueSetter.setValue(outMsgArray.no(i).procStsCd, PROC_STS.COMPLEATED);
                    } else {
                        ZYPEZDItemValueSetter.setValue(outMsgArray.no(i).procStsCd, PROC_STS.IN_COMPLETED);
                    }
                }
                // END 2018/08/21 E.Kameishi [QC#27688,ADD]
            }
        } else {
            // has error
            bizMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            return false;
            
        }
        return true;
    }
    //END 2018/05/30 E.Kameishi [QC#26229,ADD]
}
