/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL2610.common;

import static business.blap.NFCL2610.constant.NFCL2610Constant.AR_RF_RQST;
import static business.blap.NFCL2610.constant.NFCL2610Constant.AR_RF_RQST_DTL;
import static business.blap.NFCL2610.constant.NFCL2610Constant.BLANK;
import static business.blap.NFCL2610.constant.NFCL2610Constant.BUSINESS_ID;
import static business.blap.NFCL2610.constant.NFCL2610Constant.DEF_COA_AFFL_CD;
import static business.blap.NFCL2610.constant.NFCL2610Constant.DT_FORMAT_TS;
import static business.blap.NFCL2610.constant.NFCL2610Constant.FUNC_ID_CLT_REPS;
import static business.blap.NFCL2610.constant.NFCL2610Constant.NFCM0502E;
import static business.blap.NFCL2610.constant.NFCL2610Constant.NFCM0624E;
import static business.blap.NFCL2610.constant.NFCL2610Constant.NFCM0782E;
import static business.blap.NFCL2610.constant.NFCL2610Constant.NFCM0794E;
import static business.blap.NFCL2610.constant.NFCL2610Constant.NFCM0843E;
import static business.blap.NFCL2610.constant.NFCL2610Constant.NFCM0875E;
import static business.blap.NFCL2610.constant.NFCL2610Constant.NFCM0914E;
import static business.blap.NFCL2610.constant.NFCL2610Constant.NFCM0915E;
import static business.blap.NFCL2610.constant.NFCL2610Constant.NZZM0000E;
import static business.blap.NFCL2610.constant.NFCL2610Constant.NZZM0001W;
import static business.blap.NFCL2610.constant.NFCL2610Constant.NZZM0002I;
import static business.blap.NFCL2610.constant.NFCL2610Constant.NZZM0009E;
import static business.blap.NFCL2610.constant.NFCL2610Constant.NZZM0011E;
import static business.blap.NFCL2610.constant.NFCL2610Constant.SEPARATOR;
import static business.blap.NFCL2610.constant.NFCL2610Constant.NFCM0919E;
import static business.blap.NFCL2610.constant.NFCL2610Constant.ZZM9000E;

import static com.canon.cusa.s21.api.NFC.NFZC501001.constant.NFZC501001Constant.AR_REFUND_WF_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL2610.NFCL2610CMsg;
import business.blap.NFCL2610.NFCL2610Query;
import business.blap.NFCL2610.NFCL2610SMsg;
import business.blap.NFCL2610.NFCL2610_ASMsg;
import business.db.AR_CR_RF_APVL_LIMITTMsg;
import business.db.AR_RF_RQSTTMsg;
import business.db.AR_RF_RQST_DTLTMsg;
import business.db.AR_RF_TPTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AUTH_PSNTMsg;
import business.db.AUTH_PSNTMsgArray;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.GLBL_CMPYTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.api.NFD.NFZC500001.NFZC500001TokenObject;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_RF_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRNT_VND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.core.user.S21NwfUserRole;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizProcess;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizWorkItem;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.nwf.util.process.S21NwfUtilProcessFactory;
import com.canon.cusa.s21.framework.nwf.util.token.S21NwfUtilTokenObj;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * AR Refund by Check Entry
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         J.Kim           Create          N/A
 * 2016/04/12   Hitachi         K.Kojima        Update          CSA QC#6871
 * 2016/04/19   Hitachi         T.Tsuchida      Update          QC#4758
 * 2016/04/19   Hitachi         T.Tsuchida      Update          QC#7243
 * 2016/04/19   Hitachi         T.Tsuchida      Update          QC#7248
 * 2016/04/19   Hitachi         T.Tsuchida      Update          QC#7250
 * 2016/05/13   Hitachi         K.Kojima        Update          QC#7796
 * 2016/05/19   Hitachi         T.Tsuchida      Update          QC#8479
 * 2016/07/29   Hitachi         K.Kojima        Update          QC#12657
 * 2016/09/06   Hitachi         T.Tsuchida      Update          QC#13342
 * 2016/09/16   Hitachi         K.Kasai         Update          QC#14308
 * 2016/11/07   Hitachi         J.Kim           Update          QC#15758
 * 2017/01/13   Fujitsu         T.Murai         Update          QC#16941
 * 2017/11/09   Fujitsu         M.Ohno          Update          QC#20587
 * 2018/02/06   Hitachi         T.Tsuchida      Update          QC#23990
 * 2018/03/01   Hitachi         E.Kameishi      Update          QC#23164
 * 2018/03/02   Hitachi         E.Kameishi      Update          QC#22765
 * 2018/03/08   Fujitsu         H.Ikeda         Update          QC#22762
 * 2018/06/26   Hitachi         E.Kameishi      Update          QC#16941
 * 2018/07/11   Hitachi         Y.Takeno        Update          QC#26989
 * 2018/08/03   Hitachi         E.Kameishi      Update          QC#27462
 * 2019/12/16   Fujitsu         H.Ikeda         Update          QC#54927
 * 2020/05/12   Fujitsu         H.Mizukami      Update          QC#56436
 * 2020/06/19   CITS            R.Kurahashi     Update          QC#56956
 * 2020/06/29   CITS            R.Kurahashi     Update          QC#57119
 * 2020/07/14   CITS            R.Kurahashi     Update          QC#57119-1
 * 2020/07/22   CITS            R.Kurahashi     Update          QC#56956-1
 * 2020/07/27   CITS            R.Kurahashi     Update          QC#57347
 * 2020/10/28   CITS            R.Kurahashi     Update          QC#57732
 * 2021/01/25   CITS            G.Delgado       Update          QC#58064
 * 2021/09/09   CITS            G.Delgado       Update          QC#58793
 * 2022/01/26   CITS            D.Mamaril       Update          QC#59618
 * 2022/04/21   CITS            K.Suzuki        Update          QC#59333
 * 2022/06/03   CITS            D.Mamaril       Update          QC#59333-1
 * 2022/07/25   Hitachi         A.Kohinata      Update          QC#57417
 * 2022/07/27   Hitachi         A.Kohinata      Update          QC#57418
 * 2023/06/13   Hitachi         S.Fujita        Update          QC#61486
 *</pre>
 */
public class NFCL2610CommonLogic {

    /**
     * Check Parameter of Multiple Refund Flag
     * @param cMsg NFCL2610CMsg
     * @param sMsg NFCL2610SMsg
     * @return boolean
     */
    public static boolean checkMultipleRefundFlag(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {

        AR_RF_TPTMsg outTMsg = NFCL2610Query.findArRfTp(cMsg);
        if (outTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.arRfMultCratFlg, outTMsg.arRfMultCratFlg);
        }

        // START 2021/09/09 G.Delgado [QC#58793, MOD]
        // List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox_3", ZYPConstant.CHKBOX_ON_Y);
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_3", ZYPConstant.CHKBOX_ON_Y);
        // END 2021/09/09 G.Delgado [QC#58793, MOD]
        if (!ZYPConstant.CHKBOX_ON_Y.equals(cMsg.arRfMultCratFlg.getValue())) {
            if (selectedRows.size() > 1) {
                cMsg.setMessageInfo(NZZM0009E);
                return false;
            }
        }

        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NZZM0011E);
            return false;
        }

        // add start 2022/07/25 QC#57417
        boolean result = true;
        int firstErrorIndex = -1;
        int pageFrom = -1;
        int nextPageIndex = -1;
        for (int checkRow : selectedRows) {
            if (!result && checkRow >= nextPageIndex) {
                break;
            }

            NFCL2610_ASMsg aSMsg = sMsg.A.no(checkRow);
            if (!ZYPCommonFunc.hasValue(aSMsg.arRcptRfRsnCd_A)) {
                result = false;
                aSMsg.xxChkBox_3.setErrorInfo(1, ZZM9000E, new String[] {"Refund Reason" });
                aSMsg.arRcptRfRsnCd_A.setErrorInfo(1, ZZM9000E, new String[] {"Refund Reason" });

                if (firstErrorIndex < 0) {
                    firstErrorIndex = checkRow;
                    pageFrom = convertIndexToPageFirstRowIndex(cMsg.A.length(), firstErrorIndex);
                    nextPageIndex = pageFrom + cMsg.A.length();
                }
            }
        }
        if (!result) {
            // Show first page with error
            pagenation(cMsg, sMsg, pageFrom);
            return false;
        }
        // add end 2022/07/25 QC#57417

        return true;
    }

    // START 2016/05/13 K.Kojima [QC#7796,ADD]
    /**
     * 
     */
    public static boolean checkSupplier(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {
        String vndCd = NFCL2610CommonLogic.getPrntVndCd(cMsg);
        if (vndCd == null || vndCd.length() == 0) {
            cMsg.setMessageInfo(NFCM0843E);
            return false;
        }
        return true;
    }

    // END 2016/05/13 K.Kojima [QC#7796,ADD]

    /**
     * searchInit
     * @param cMsg NFCL2610CMsg
     * @param sMsg NFCL2610SMsg
     */
    public static void searchInit(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {

        searchHeaderInfo(cMsg, sMsg);
        // Refund Type
        setRefundType(cMsg);
    }

    private static void setRefundType(NFCL2610CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.arRfTpCd_H)) {
            cMsg.arRfTpCd_H.setValue(AR_RF_TP.CUSTOMER);
        }

        AR_RF_TPTMsg outTMsg = NFCL2610Query.findArRfTp(cMsg);
        if (outTMsg != null) {
            if (outTMsg.selRfTrxTpTxt.getValue().indexOf(AR_TRX_TP.RECEIPT) >= 0) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_1, ZYPConstant.CHKBOX_ON_Y);
                // START 2016/08/01 K.Kojima [QC#12657,ADD]
            } else {
                cMsg.xxChkBox_1.clear();
                // END 2016/08/01 K.Kojima [QC#12657,ADD]
            }
            if (outTMsg.selRfTrxTpTxt.getValue().indexOf(AR_TRX_TP.CREDIT_MEMO) >= 0) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_2, ZYPConstant.CHKBOX_ON_Y);
                // START 2016/08/01 K.Kojima [QC#12657,ADD]
            } else {
                cMsg.xxChkBox_2.clear();
                // END 2016/08/01 K.Kojima [QC#12657,ADD]
            }
            ZYPEZDItemValueSetter.setValue(cMsg.arRfMultCratFlg, outTMsg.arRfMultCratFlg);
        }
    }

    /**
     * Search AR Refund Request Info
     * @param cMsg NFCL2610CMsg
     * @param sMsg NFCL2610SMsg
     */
    public static void searchArRfRqstInfo(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {

        // START 2021/01/25 G.Delgado [QC#58064,ADD]
        String vndCd = null;
        // END 2021/01/25 G.Delgado [QC#58064,ADD]

        AR_RF_RQSTTMsg outTMsg = NFCL2610Query.findArRfRqstInfo(cMsg);
        if (outTMsg != null) {
            // START 2016/08/01 K.Kojima [QC#12657,ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.arRfTpCd_H, outTMsg.arRfTpCd);
            setRefundType(cMsg);
            // END 2016/08/01 K.Kojima [QC#12657,ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.arRfRqstPk, outTMsg.arRfRqstPk);
            ZYPEZDItemValueSetter.setValue(cMsg.arRcptRfCmntTxt, outTMsg.arRcptRfCmntTxt);
            // del start 2022/07/25 QC#57417
            //ZYPEZDItemValueSetter.setValue(cMsg.arRcptRfRsnDescTxt_H, outTMsg.arRcptRfRsnCd);
            // del end 2022/07/25 QC#57417
            ZYPEZDItemValueSetter.setValue(cMsg.arRfChkCmntTxt, outTMsg.arRfChkCmntTxt);
            ZYPEZDItemValueSetter.setValue(cMsg.xxTotRfAmt, outTMsg.funcRfAmt);
            // add start 2022/07/27 QC#57418
            ZYPEZDItemValueSetter.setValue(cMsg.arRfStsCd, outTMsg.arRfStsCd);
            // add end 2022/07/27 QC#57418
            // START 2018/03/02 E.Kameishi [QC#22765,ADD]
            String arRfSts = NFCL2610Query.getInstance().getRfSts(cMsg.glblCmpyCd.getValue(), outTMsg.arRfRqstPk.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.arRfStsDescTxt, arRfSts);
            // END 2018/03/02 E.Kameishi [QC#22765,ADD]
            // START 2021/01/25 G.Delgado [QC#58064,ADD]
            vndCd = outTMsg.vndCd.getValue();
            // END 2021/01/25 G.Delgado [QC#58064,ADD]
        }

        S21SsmEZDResult ssmResult = getArRfRqstInfoQuery(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > 1000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                sMsg.A.setValidCount(sMsg.A.length());
            }
        } else {
            cMsg.setMessageInfo(NZZM0000E);
            return;
        }

        if (ZYPCommonFunc.hasValue(cMsg.billToCustAcctCd)) {
            S21SsmEZDResult ssmResultB = getSupplierQuery(cMsg);
            // START 2020/05/12 [QC#56436, ADD]
            if (ssmResultB == null) {
                // START 2020/06/29 R.Kurahashi [QC#57119,MOD]
                if (cMsg.getMessageInfo() == null) {
                    cMsg.setMessageInfo(NFCM0914E);
                }
                return;
                // START 2020/06/29 R.Kurahashi [QC#57119,MOD]
            }
            // END   2020/05/12 [QC#56436, ADD]
            if (ssmResultB.isCodeNormal()) {
                // START 2020/10/28 R.Kurahashi [QC#57732,MOD]
                // START 2016/05/13 K.Kojima [QC#7796,ADD]
                //cMsg.xxRadioBtn.setValue(0);
                //sMsg.xxRadioBtn.setValue(0);
                // END 2016/05/13 K.Kojima [QC#7796,ADD]
                int index = 0;
                // START 2021/01/25 G.Delgado [QC#58064,MOD]
                // if (cMsg.B.getValidCount() > 0) {
                // index = cMsg.xxRadioBtn.getValueInt();
                // }
                if (cMsg.B.getValidCount() > 0 && ZYPCommonFunc.hasValue(vndCd)) {
                    for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                        if (vndCd.equals(cMsg.B.no(i).vndCd.getValue())) {
                            index = i;
                            break;
                        }
                    }
                }
                // END 2021/01/25 G.Delgado [QC#58064,MOD]
                cMsg.xxRadioBtn.setValue(index);
                sMsg.xxRadioBtn.setValue(index);
                // END 2020/10/28 R.Kurahashi [QC#57732,MOD]
                // Result > 200
                int queryResCnt = ssmResultB.getQueryResultCount();
                if (queryResCnt > sMsg.B.length()) {
                    cMsg.setMessageInfo(NZZM0001W);
                    sMsg.B.setValidCount(sMsg.B.length());
                }
            }
        }
    }

    /**
     * Search Header Info
     * @param cMsg NFCL2610CMsg
     * @param sMsg NFCL2610SMsg
     */
    private static void searchHeaderInfo(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {

        // Refund Type
        // START 2016/07/29 K.Kojima [QC#12657,MOD]
        // ZYPCodeDataUtil.createPulldownList(AR_RF_TP.class,
        // cMsg.arRfTpCd_C, cMsg.arRfTpDescTxt_D);
        S21SsmEZDResult ssmResult = null;
        // START 2023/06/13 S.Fujita [QC#61486,MOD]
        // if (!ZYPCommonFunc.hasValue(cMsg.arRfRqstPk)) {
        //     ssmResult = NFCL2610Query.getInstance().getPulldownArRfTpCd(cMsg.glblCmpyCd.getValue(), AR_RF_TP.CREDIT_CARD_REFUND);
        // } else {
        //    ssmResult = NFCL2610Query.getInstance().getPulldownArRfTpCd(cMsg.glblCmpyCd.getValue(), null);
        // }
        if (!ZYPCommonFunc.hasValue(cMsg.arRfRqstPk)) {
            ssmResult = NFCL2610Query.getInstance().getPulldownArRfTpCd(cMsg.glblCmpyCd.getValue(), AR_RF_TP.CREDIT_CARD_REFUND, isCollectionReps());
        } else {
            ssmResult = NFCL2610Query.getInstance().getPulldownArRfTpCd(cMsg.glblCmpyCd.getValue(), null, isCollectionReps());
        }
        // END 2023/06/13 S.Fujita [QC#61486,MOD]
        if (!ssmResult.isCodeNotFound()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.arRfTpCd_C.no(i), (String) resultMap.get("AR_RF_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.arRfTpDescTxt_D.no(i), (String) resultMap.get("AR_RF_TP_DESC_TXT"));
            }
        }
        // END 2016/07/29 K.Kojima [QC#12657,MOD]

        // Refund Reason
        ZYPCodeDataUtil.createPulldownList(AR_RCPT_RF_RSN.class, cMsg.arRcptRfRsnCd_C, cMsg.arRcptRfRsnDescTxt_D);

        // Address
        getAddress(cMsg);

        // Refund Eligible User
        getRefundEligibleUser(cMsg);
    }

    // START 2023/06/13 S.Fujita [QC#61486,ADD]
    private static boolean isCollectionReps() {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            return false;
        }
        if (funcList.contains(FUNC_ID_CLT_REPS)) {
            return true;
        }
        return false;
    }
    // END 2023/06/13 S.Fujita [QC#61486,ADD]

    /**
     * Address
     * @param cMsg NFCL2610CMsg
     */
    public static void getAddress(NFCL2610CMsg cMsg) {

        // Address
        // 2017/11/09 QC#20587 del start
//        if (ZYPCommonFunc.hasValue(cMsg.billToCustAcctCd)) {
//            SELL_TO_CUSTTMsgArray outSellToCustTMsg = NFCL2610Query.findBillToAcctCust(cMsg);
//            if (outSellToCustTMsg.length() == 1) {
//                cMsg.xxAllLineAddr.setValue(editAddress(outSellToCustTMsg.no(0)));
//            }
//        } else 
        // 2017/11/09 QC#20587 del end
        if (ZYPCommonFunc.hasValue(cMsg.billToCustLocCd)) {
            BILL_TO_CUSTTMsgArray outBillToCustMsg = NFCL2610Query.findBillToCustList(cMsg);
            if (outBillToCustMsg.length() == 1) {
                cMsg.xxAllLineAddr.setValue(editAddress(outBillToCustMsg.no(0)));
            }
        }
    }

    /**
     * Search Bill To Customer Account Name
     * @param cMsg NFCL2610CMsg
     */
    public static void searchAddressForBillToCustomerAccount(NFCL2610CMsg cMsg) {

        SELL_TO_CUSTTMsgArray outSellToCustTMsg = NFCL2610Query.findBillToAcctCust(cMsg);
        if (outSellToCustTMsg.length() == 0) {
            cMsg.billToCustAcctCd.setErrorInfo(1, NFCM0502E, new String[] {"Customer" });
            return;
        }
        cMsg.dsAcctNm.setValue(outSellToCustTMsg.no(0).dsAcctNm.getValue());
    }

    /**
     * Search Bill To Customer Name
     * @param cMsg NFCL2610CMsg
     */
    public static void searchBillToCustomerName(NFCL2610CMsg cMsg) {

        BILL_TO_CUSTTMsgArray outBillToCustMsg = NFCL2610Query.findBillToCustList(cMsg);
        if (outBillToCustMsg.length() == 0) {
            cMsg.billToCustLocCd.setErrorInfo(1, NFCM0502E, new String[] {"Bill To Loc" });
            return;
        }
        cMsg.billToCustNm.setValue(outBillToCustMsg.no(0).locNm.getValue());
        // 2017/11/09 QC#20587 add start
        ZYPEZDItemValueSetter.setValue(cMsg.billToCustAcctCd, outBillToCustMsg.no(0).sellToCustCd);
        searchAddressForBillToCustomerAccount(cMsg);
        // 2017/11/09 QC#20587 add start
    }

    // del start 2022/07/27 QC#57418
//    /**
//     * Submit ArRefund Request
//     * @param cMsg NFCL2610CMsg
//     * @param sMsg NFCL2610SMsg
//     */
//    public static void submitArRefundRequest(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {
//
//        String stdCcyCd = null;
//        GLBL_CMPYTMsg outBlblCmpyTMsg = NFCL2610Query.findGlblCmpy(cMsg);
//        if (outBlblCmpyTMsg != null) {
//            stdCcyCd = outBlblCmpyTMsg.stdCcyCd.getValue();
//        }
//
//        ZYPEZDItemValueSetter.setValue(cMsg.arRfRqstPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_RF_RQST_SQ));
//        // AR Refund Request Detail
//        // START 2021/09/09 G.Delgado [QC#58793, MOD]
//        // if (!createArRefundRequestDetail(cMsg, stdCcyCd)) {
//        if (!createArRefundRequestDetail(cMsg, sMsg, stdCcyCd)) {
//        // END 2021/09/09 G.Delgado [QC#58793, MOD]
//            return;
//        }
//
//        // AR Refund Request
//        createArRefundRequest(cMsg, stdCcyCd);
//        // START 2020/06/19 R.Kurahashi [QC#56956,MOD]
//        // START 2016/04/12 K.Kojima [QC#6871,ADD]
//        // Workflow start
//        //startWorkflow(cMsg);
//        // START 2020/07/27 R.Kurahashi [QC#57347,MOD]
//        String defCoaAfflCd = ZYPCodeDataUtil.getVarCharConstValue(DEF_COA_AFFL_CD, cMsg.glblCmpyCd.getValue());
//        if (!ZYPCommonFunc.hasValue(defCoaAfflCd)) {
//            cMsg.setMessageInfo("NFCM0856E", new String[] {DEF_COA_AFFL_CD});
//            return;
//        }
//        String coaAfflCd = getCoaAfflCd(cMsg.glblCmpyCd.getValue(), cMsg.arRfTpCd_H.getValue());
//        if (ZYPCommonFunc.hasValue(coaAfflCd) && defCoaAfflCd.equals(coaAfflCd)) {
//            // Workflow according to HR hierarchy
//            startWorkflowAccToHR(cMsg);
//        }
//        else {
//            updateWfRqstForApprove(cMsg, cMsg.getUserID(), cMsg.arRfRqstPk.getValue());
//        }
//        // END 2020/07/27 R.Kurahashi [QC#57347,MOD]
//        // END 2016/04/12 K.Kojima [QC#6871,ADD]
//        // END 2020/06/19 R.Kurahashi [QC#56956,MOD]
//
//        cMsg.setMessageInfo(NZZM0002I);
//    }
    // del end 2022/07/27 QC#57418


    // mod start 2022/07/27 QC#57418
    //private static void createArRefundRequest(NFCL2610CMsg cMsg, String ccyCd) {
    private static void createArRefundRequest(NFCL2610CMsg cMsg, String ccyCd, String arRfStsCd) {
    // mod end 2022/07/27 QC#57418

        AR_RF_RQSTTMsg inTMsg = new AR_RF_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.arRfRqstPk, cMsg.arRfRqstPk);
        ZYPEZDItemValueSetter.setValue(inTMsg.arRfTpCd, cMsg.arRfTpCd_H);
        ZYPEZDItemValueSetter.setValue(inTMsg.billToCustAcctCd, cMsg.billToCustAcctCd);
        // del start 2022/07/25 QC#57417
        //ZYPEZDItemValueSetter.setValue(inTMsg.arRcptRfRsnCd, cMsg.arRcptRfRsnDescTxt_H);
        // del end 2022/07/25 QC#57417
        ZYPEZDItemValueSetter.setValue(inTMsg.arRcptRfCmntTxt, cMsg.arRcptRfCmntTxt);
        ZYPEZDItemValueSetter.setValue(inTMsg.arRfChkCmntTxt, cMsg.arRfChkCmntTxt);
        ZYPEZDItemValueSetter.setValue(inTMsg.vndCd, getVndCd(cMsg));
        ZYPEZDItemValueSetter.setValue(inTMsg.arRfRqstUsrId, cMsg.getUserID());
        ZYPEZDItemValueSetter.setValue(inTMsg.arRfCratDt, ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS));
        ZYPEZDItemValueSetter.setValue(inTMsg.dealCcyCd, ccyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.arDsWfStsCd, AR_DS_WF_STS.PENDING);
        ZYPEZDItemValueSetter.setValue(inTMsg.dealRfAmt, cMsg.dealRfAmt);
        ZYPEZDItemValueSetter.setValue(inTMsg.funcRfAmt, cMsg.xxTotRfAmt);
        // add start 2022/07/27 QC#57418
        if (ZYPCommonFunc.hasValue(arRfStsCd)) {
            ZYPEZDItemValueSetter.setValue(inTMsg.arRfStsCd, arRfStsCd);
        }
        // add end 2022/07/27 QC#57418
        S21FastTBLAccessor.insert(inTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NFCM0782E, new String[] {AR_RF_RQST});
            return;
        }
    }

    // START 2021/09/09 G.Delgado [QC#58793, MOD]
    /**
     * createArRefundRequestDetail
     * @param cMsg NFCL2610CMsg
     * @param sMsg NFCL2610SMsg
     * @param ccyCd String
     * @return true if no error, else false
     */
    // private static boolean createArRefundRequestDetail(NFCL2610CMsg cMsg, String ccyCd) {
    private static boolean createArRefundRequestDetail(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg, String ccyCd) {
    // END 2021/09/09 G.Delgado [QC#58793, MOD]

        BigDecimal dealRfAmt = BigDecimal.ZERO;
        BigDecimal actlExchRate = findAcctDlyActlExchRatest(cMsg, ccyCd);

        // START 2021/09/09 G.Delgado [QC#58793, MOD]
//        for (int index = 0; index < cMsg.A.getValidCount(); index++) {
//            NFCL2610_ACMsg record = cMsg.A.no(index);
        for (int index = 0; index < sMsg.A.getValidCount(); index++) {
            NFCL2610_ASMsg record = sMsg.A.no(index);
            // END 2021/09/09 G.Delgado [QC#58793, MOD]
            if (ZYPConstant.CHKBOX_ON_Y.equals(record.xxChkBox_3.getValue())) {
                AR_TRX_BALTMsg inTMsg = new AR_TRX_BALTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.arTrxBalPk, record.arTrxBalPk_A);
                AR_TRX_BALTMsg outTMsg = (AR_TRX_BALTMsg) S21FastTBLAccessor.findByKey(inTMsg);
                // START 2021/09/09 G.Delgado [QC#58793, MOD]
                // if (!isSameTimeStamp(cMsg, record, outTMsg)) {
                if (!isSameTimeStamp(record, outTMsg)) {
                // END 2021/09/09 G.Delgado [QC#58793, MOD]
                    record.xxChkBox_3.setErrorInfo(1, NFCM0794E, new String[] {record.arTrxNum_A.getValue() });

                    // START 2021/09/09 G.Delgado [QC#58793, ADD]
                    // Show page with error
                    int pageFrom = convertIndexToPageFirstRowIndex(cMsg.A.length(), index);
                    pagenation(cMsg, sMsg, pageFrom);
                    // END 2021/09/09 G.Delgado [QC#58793, ADD]
                    return false;
                } else {
                    createArRefundRequestDetail(cMsg, record, ccyCd, actlExchRate);
                }
                dealRfAmt = dealRfAmt.add(record.dealRmngBalGrsAmt_A.getValue());
            }
        }
        ZYPEZDItemValueSetter.setValue(cMsg.dealRfAmt, dealRfAmt);
        return true;
    }

    // START 2021/09/09 G.Delgado [QC#58793, MOD]
    /**
     * createArRefundRequestDetail
     * @param cMsg NFCL2610CMsg
     * @param record NFCL2610_ASMsg
     * @param ccyCd String
     * @param exchRate BigDecimal
     */
    // private static void createArRefundRequestDetail(NFCL2610CMsg cMsg, NFCL2610_ACMsg record, String ccyCd, BigDecimal exchRate) {
    private static void createArRefundRequestDetail(NFCL2610CMsg cMsg, NFCL2610_ASMsg record, String ccyCd, BigDecimal exchRate) {
    // END 2021/09/09 G.Delgado [QC#58793, MOD]

        AR_RF_RQST_DTLTMsg inArRfRqstDtlTMsg = new AR_RF_RQST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.arRfRqstDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_RF_RQST_DTL_SQ));
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.arRfRqstPk, cMsg.arRfRqstPk);
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.arTrxTpCd, record.arTrxTpCd_A);
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.billToCustCd, record.billToCustCd_A);
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.arTrxNum, record.arTrxNum_A);
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.arTrxBalPk, record.arTrxBalPk_A);
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.arTrxBalLastUpdTs, record.ezUpTime_A);
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.arTrxBalTz, record.ezUpTimeZone_A);
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.arOrigRcptNum, record.origInvNum_A);
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.dealOrigGrsAmt, record.dealOrigGrsAmt_A);
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.dealRmngBalGrsAmt, record.dealRmngBalGrsAmt_A);
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.dealRfAmt, record.dealRmngBalGrsAmt_A);
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.dealCcyCd, ccyCd);
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.rfExchRate, exchRate);
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.funcOrigGrsAmt, record.funcOrigGrsAmt_A);
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.funcRmngBalGrsAmt, record.funcRmngBalGrsAmt_A);
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.funcRfAmt, exchRate.multiply(record.dealRmngBalGrsAmt_A.getValue()));
        // add start 2022/07/25 QC#57417
        ZYPEZDItemValueSetter.setValue(inArRfRqstDtlTMsg.arRcptRfRsnCd, record.arRcptRfRsnCd_A);
        // add end 2022/07/25 QC#57417
        S21FastTBLAccessor.insert(inArRfRqstDtlTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inArRfRqstDtlTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NFCM0782E, new String[] {AR_RF_RQST_DTL});
            return;
        }
    }

    // START 2021/09/09 G.Delgado [QC#58793, MOD]
    /**
     * isSameTimeStamp
     * @param asMsg NFCL2610_ASMsg
     * @param tMsg AR_TRX_BALTMsg
     * @return boolean
     */
    // private static boolean isSameTimeStamp(NFCL2610CMsg cMsg, NFCL2610_ACMsg acMsg, AR_TRX_BALTMsg tMsg) {
    private static boolean isSameTimeStamp(NFCL2610_ASMsg asMsg, AR_TRX_BALTMsg tMsg) {
    // END 2021/09/09 G.Delgado [QC#58793, MOD]

        // START 2021/09/09 G.Delgado [QC#58793, MOD]
        // String preUpTime = acMsg.ezUpTime_A.getValue();
        // String preTimeZone = acMsg.ezUpTimeZone_A.getValue();
        String preUpTime = asMsg.ezUpTime_A.getValue();
        String preTimeZone = asMsg.ezUpTimeZone_A.getValue();
        // END 2021/09/09 G.Delgado [QC#58793, MOD]
        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, currUpTime, currTimeZone)) {
            return false;
        }
        return true;
    }

    private static String getPrntVndCd(NFCL2610CMsg cMsg) {

        String prntVndCd = null;
        if (cMsg.B.getValidCount() > 0) {
            int index = cMsg.xxRadioBtn.getValueInt();
            prntVndCd = cMsg.B.no(index).prntVndCd.getValue();
        }
        return prntVndCd;
    }

    // START 2016/09/06 T.Tsuchida [QC#13342,ADD]
    private static String getVndCd(NFCL2610CMsg cMsg) {

        String vndCd = null;
        if (cMsg.B.getValidCount() > 0) {
            int index = cMsg.xxRadioBtn.getValueInt();
            vndCd = cMsg.B.no(index).vndCd.getValue();
        }
        return vndCd;
    }
    // END 2016/09/06 T.Tsuchida [QC#13342,ADD]

    private static BigDecimal findAcctDlyActlExchRatest(NFCL2610CMsg cMsg, String ccyCd) {

        String slsDt = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());
        BigDecimal exchRate = (BigDecimal) NFCL2610Query.getInstance().findAcctDlyActlExchRatest(cMsg, ccyCd, slsDt).getResultObject();
        if (exchRate == null) {
            return BigDecimal.ZERO;
        }
        return exchRate;
    }

    /**
     * setPagenation
     * @param cMsg NFCL2610CMsg
     * @param sMsg NFCL2610SMsg
     * @param pageFrom int
     */
    public static void setPagenation(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg, int pageFrom) {

        int cnt = pageFrom;
        for (; cnt < pageFrom + cMsg.A.length(); cnt++) {
            if (cnt < pageFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pageFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * Pagenation
     * @param cMsg NFCL2610CMsg
     * @param sMsg NFCL2610SMsg
     * @param pageFrom int
     */
    public static void pagenation(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);

        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param rowsPerPage int
     * @param page int
     * @return page
     */
    public static int convertPageNumToFirstRowIndex(int rowsPerPage, int page) {
        if (page <= 0) {
            return 0;
        }
        return rowsPerPage * (page - 1);
    }

    // START 2021/09/09 G.Delgado [QC#58793, ADD]
    /**
     * convertIndexToPageFirstRowIndex
     * @param rowsPerPage int
     * @param index int
     * @return first row index
     */
    public static int convertIndexToPageFirstRowIndex(int rowsPerPage, int index) {
        if (index <= 0 || rowsPerPage <= 0) {
            return 0;
        }
        return index - (index % rowsPerPage);
    }
    // END 2021/09/09 G.Delgado [QC#58793, ADD]

    /**
     * searchArTrxBalInfo
     * @param cMsg NFCL2610CMsg
     * @param sMsg NFCL2610SMsg
     */
    public static void searchArTrxBalInfo(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {

        S21SsmEZDResult ssmResult = getArTrxBalInfoQuery(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > 1000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                sMsg.A.setValidCount(sMsg.A.length());
            }
        } else {
            cMsg.setMessageInfo(NZZM0000E);
            return;
        }

        if (ZYPCommonFunc.hasValue(cMsg.billToCustAcctCd)) {
            S21SsmEZDResult ssmResultB = getSupplierQuery(cMsg);
            // START 2020/05/12 [QC#56436, ADD]
            if (ssmResultB == null) {
             // START 2020/06/29 R.Kurahashi [QC#57119,MOD]
                if (cMsg.getMessageInfo() == null) {
                    cMsg.setMessageInfo(NFCM0914E);
                }
                return;
                // START 2020/06/29 R.Kurahashi [QC#57119,MOD]
            }
            // END   2020/05/12 [QC#56436, ADD]
            if (ssmResultB.isCodeNormal()) {
                // START 2020/10/28 R.Kurahashi [QC#57732,MOD]
                // START 2016/05/13 K.Kojima [QC#7796,ADD]
                //cMsg.xxRadioBtn.setValue(0);
                //sMsg.xxRadioBtn.setValue(0);
                // END 2016/05/13 K.Kojima [QC#7796,ADD]
                int index = 0;
                if (cMsg.B.getValidCount() > 0) {
                    index = cMsg.xxRadioBtn.getValueInt();
                }
                cMsg.xxRadioBtn.setValue(index);
                sMsg.xxRadioBtn.setValue(index);
                // END 2020/10/28 R.Kurahashi [QC#57732,MOD]
                // Result > 200
                int queryResCnt = ssmResultB.getQueryResultCount();
                if (queryResCnt > sMsg.B.length()) {
                    cMsg.setMessageInfo(NZZM0001W);
                    sMsg.B.setValidCount(sMsg.B.length());
                }
            }
        }
    }

    /**
     * Search Notes
     * @param cMsg NSAL1160CMsg
     * @param sMsg NSAL1160SMsg
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult getArTrxBalInfoQuery(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {
        Map<String, Object> params = setTransactionSeachParam(cMsg, sMsg.A.length() + 1);
        S21SsmEZDResult ssmResult = NFCL2610Query.getInstance().getArTrxBalInfo(sMsg, params);
        return ssmResult;
    }

    /**
     * Search ArRfRqstInfo
     * @param cMsg NSAL1160CMsg
     * @param sMsg NSAL1160SMsg
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult getArRfRqstInfoQuery(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {
        Map<String, Object> params = setArRfRqstSeachParam(cMsg, sMsg.A.length() + 1);
        S21SsmEZDResult ssmResult = NFCL2610Query.getInstance().getArRfRqstlInfo(sMsg, params);
        return ssmResult;
    }

    private static String editAddress(SELL_TO_CUSTTMsg tMsg) {

        StringBuffer sb = new StringBuffer();
        sb.append(tMsg.firstLineAddr.getValue());
        if (ZYPCommonFunc.hasValue(tMsg.scdLineAddr)) {
            sb.append(SEPARATOR);
            sb.append(tMsg.scdLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(tMsg.ctyAddr)) {
            sb.append(SEPARATOR);
            sb.append(tMsg.ctyAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(tMsg.stCd)) {
            sb.append(SEPARATOR);
            sb.append(tMsg.stCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(tMsg.postCd)) {
            sb.append(SEPARATOR);
            sb.append(tMsg.postCd.getValue());
        }
        return sb.toString();
    }

    private static String editAddress(BILL_TO_CUSTTMsg tMsg) {

        StringBuffer sb = new StringBuffer();
        sb.append(tMsg.firstLineAddr.getValue());
        if (ZYPCommonFunc.hasValue(tMsg.scdLineAddr)) {
            sb.append(SEPARATOR);
            sb.append(tMsg.scdLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(tMsg.ctyAddr)) {
            sb.append(SEPARATOR);
            sb.append(tMsg.ctyAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(tMsg.stCd)) {
            sb.append(SEPARATOR);
            sb.append(tMsg.stCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(tMsg.postCd)) {
            sb.append(SEPARATOR);
            sb.append(tMsg.postCd.getValue());
        }
        return sb.toString();
    }

    /**
     * Refund Eligible User
     * @param cMsg NFCL2610CMsg
     */
    public static void getRefundEligibleUser(NFCL2610CMsg cMsg) {

        AR_CR_RF_APVL_LIMITTMsg outTMsg = NFCL2610Query.getRefundEligibleUser(cMsg);
        if (outTMsg != null) {
            cMsg.znFlg.setValue(ZYPConstant.FLG_ON_Y);
        }
    }

    private static String[] getArTrxTpCd(String arRfTpCd, String receiptFlg, String creditMemoFlg) {
        // START 2018/02/06 T.Tsuchida [QC#23990,MOD]
//        if (AR_RF_TP.CUSTOMER.equals(arRfTpCd)) {
//            if (ZYPConstant.FLG_ON_Y.equals(receiptFlg)
//                    && !ZYPConstant.FLG_ON_Y.equals(creditMemoFlg)) {
//                return new String[] {AR_TRX_TP.RECEIPT };
//            } else if (!ZYPConstant.FLG_ON_Y.equals(receiptFlg)
//                    && ZYPConstant.FLG_ON_Y.equals(creditMemoFlg)) {
//                return new String[] {AR_TRX_TP.CREDIT_MEMO };
//            }
//            return new String[] {AR_TRX_TP.CREDIT_MEMO, AR_TRX_TP.RECEIPT };
//        } else {
//            return new String[] {AR_TRX_TP.RECEIPT };
//        }
        if (ZYPConstant.FLG_ON_Y.equals(receiptFlg)
                && !ZYPConstant.FLG_ON_Y.equals(creditMemoFlg)) {
        // START 2018/03/01 E.Kameishi [QC#23164,MOD]
            return new String[] {AR_TRX_TP.RECEIPT, AR_TRX_TP.ON_ACCOUNT };
        } else if (!ZYPConstant.FLG_ON_Y.equals(receiptFlg)
                && ZYPConstant.FLG_ON_Y.equals(creditMemoFlg)) {
            return new String[] {AR_TRX_TP.CREDIT_MEMO };
        }
        return new String[] {AR_TRX_TP.CREDIT_MEMO, AR_TRX_TP.RECEIPT, AR_TRX_TP.ON_ACCOUNT };
        // END 2018/03/01 E.Kameishi [QC#23164,MOD]
        // END 2018/02/06 T.Tsuchida [QC#23990,MOD]
    }

    /**
     * Search Notes
     * @param cMsg NSAL1160CMsg
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult getSupplierQuery(NFCL2610CMsg cMsg) {
        Map<String, Object> params = setSupplierInfoParam(cMsg, cMsg.B.length() + 1);
        // START 2020/05/12 [QC#56436, ADD]
        if (params == null) {
            return null;
        }
        // END   2020/05/12 [QC#56436, ADD]
        S21SsmEZDResult ssmResult = NFCL2610Query.getInstance().getSupplierInfo(cMsg, params);
        return ssmResult;
    }

    private static Map<String, Object> setArRfRqstSeachParam(NFCL2610CMsg cMsg, int limitCount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("arRfRqstPk", cMsg.arRfRqstPk.getValue());
        params.put("limitCount", limitCount);
        return params;
    }

    private static Map<String, Object> setTransactionSeachParam(NFCL2610CMsg cMsg, int limitCount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("arTrxTpCd", getArTrxTpCd(cMsg.arRfTpCd_H.getValue(), cMsg.xxChkBox_1.getValue(), cMsg.xxChkBox_2.getValue()));
        // START 2016/04/19 T.Tsuchida [QC#7248,MOD]
        params.put("arTrxTpCdIsRCP", AR_TRX_TP.RECEIPT);
        // END 2016/04/19 T.Tsuchida [QC#7248,MOD]
        params.put("billToCustAcctCd", cMsg.billToCustAcctCd.getValue());
        params.put("billToCustLocCd", cMsg.billToCustLocCd.getValue());
        params.put("arCashApplyStsCd", new String[] {AR_CASH_APPLY_STS.UNAPPLIED, AR_CASH_APPLY_STS.PARTIAL });
        params.put("limitCount", limitCount);
        // START 2018/03/08 H.Ikeda [QC#22762,ADD]
        params.put("parentAccount", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        params.put("relatedAccount", DS_ACCT_RELN_TP.RELATED_ACCOUNT);
        // START 2022/01/26 D.Mamaril [QC#59618, ADD]
        params.put("arDsWfStsApproved", AR_DS_WF_STS.APPROVED);
        params.put("arDsWfStsNoApprover", AR_DS_WF_STS.NOAPPROVER);
        // END 2022/01/26 D.Mamaril [QC#59618, ADD]
        // START 2022/06/03 D.Mamaril [QC#59333-1, ADD]
        params.put("arRfStsError", AR_RF_STS.ERROR);
        // END 2022/06/03 D.Mamaril [QC#59333-1, ADD]
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_5.getValue())) {
            params.put("relatedCustomer", ZYPConstant.CHKBOX_ON_Y);
        } else {
            params.put("relatedCustomer", ZYPConstant.FLG_OFF_N);
        }
        // END   2018/03/08 H.Ikeda [QC#22762,ADD]
        // START 2018/06/27 E.Kameishi [QC#16941,ADD]
        params.put("rejected", AR_DS_WF_STS.REJECTED);
        // END 2018/06/27 E.Kameishi [QC#16941,ADD]
        // START 2018/07/11 [QC#26989, ADD]
        if (ZYPCommonFunc.hasValue(cMsg.xxQueryFltrTxt)) {
            String[] arTrxNumList = cMsg.xxQueryFltrTxt.getValue().split(",");
            params.put("arTrxNumList", arTrxNumList);
        } else {
            params.put("arTrxNumList", null);
        }
        // END   2018/07/11 [QC#26989, ADD]

        return params;
    }

    private static Map<String, Object> setSupplierInfoParam(NFCL2610CMsg cMsg, int limitCount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        // START 2019/12/16 H.Ikeda [QC#54297, MOD]
        //params.put("dsAcctNum", cMsg.billToCustAcctCd.getValue());
        // START 2020/10/28 R.Kurahashi [QC#57732,ADD]
        params.put("dsAcctNum", cMsg.billToCustAcctCd.getValue());
        // END 2020/10/28 R.Kurahashi [QC#57732,ADD]
        params.put("prntVndNm", cMsg.dsAcctNm.getValue());
        // END   2019/12/16 H.Ikeda [QC#54297, MOD]
        params.put("prntVndTpCd", PRNT_VND_TP.ARREFUND);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("effDt", ZYPDateUtil.getCurrentSystemTime("yyyyMMdd"));
        // START 2016/11/07 J.Kim [QC#15758,ADD]
        // START 2020/05/12 [QC#56436, MOD]
        String defCoaAfflCd = ZYPCodeDataUtil.getVarCharConstValue(DEF_COA_AFFL_CD, cMsg.glblCmpyCd.getValue());
        String coaAfflCd = getCoaAfflCd(cMsg.glblCmpyCd.getValue(), cMsg.arRfTpCd_H.getValue());
        // START 2020/06/29 R.Kurahashi [QC#57119,ADD]
        String vndSiteTxt = getVndSiteTxt(cMsg.glblCmpyCd.getValue(), cMsg.arRfTpCd_H.getValue());
        // START 2020/07/14 R.Kurahashi [QC#57119-1,DEL]
        //if(!ZYPCommonFunc.hasValue(vndSiteTxt)) {
        //    cMsg.setMessageInfo(NFCM0915E);
        //    return null;
        //}
        //params.put("vndSiteTxt", vndSiteTxt);
        // END 2020/07/14 R.Kurahashi [QC#57119-1,DEL]
        // END 2020/06/29 R.Kurahashi [QC#57119,ADD]
        if (coaAfflCd.equals(BLANK)) {
            return null;
        }
        //if (AR_RF_TP.CFS.equals(cMsg.arRfTpCd_H.getValue())) {
        if (!defCoaAfflCd.equals(coaAfflCd)) {
        // END   2020/05/12 [QC#56436, MOD]
            params.put("cfsCoaAfflCd", coaAfflCd);
            // START 2020/07/14 R.Kurahashi [QC#57119-1,ADD]
            if (!ZYPCommonFunc.hasValue(vndSiteTxt)) {
                cMsg.setMessageInfo(NFCM0915E);
                return null;
            }
            params.put("vndSiteTxt", vndSiteTxt);
            // END 2020/07/14 R.Kurahashi [QC#57119-1,ADD]
        } else {
            params.put("customerCoaAfflCd", coaAfflCd);
        }
        // END 2016/11/07 J.Kim [QC#15758,ADD]
        params.put("limitCount", limitCount);
        return params;
    }

    // START 2016/04/12 K.Kojima [QC#6871,ADD]
    /**
     * startWorkflow
     * @param cMsg NFCL2610CMsg
     */
    private static void startWorkflow(NFCL2610CMsg cMsg) {
        BigDecimal arRfRqstPk = cMsg.arRfRqstPk.getValue();

        S21NwfUtilTokenObj tokenBiz = new S21NwfUtilTokenObj();
        tokenBiz.setBizId("NFCL2610");
        tokenBiz.setGlblCmpyCd(cMsg.glblCmpyCd.getValue());
        tokenBiz.setCondStr1("AR Refund");
        // START 2016/04/19 T.Tsuchida [QC#7243,MOD]
        //tokenBiz.setCondNum1(cMsg.xxTotRfAmt.getValue());
        tokenBiz.setCondNum1(cMsg.xxTotRfAmt.getValue().negate());
        // END 2016/04/19 T.Tsuchida [QC#7243,MOD]
        tokenBiz.setCondNum2(arRfRqstPk);

        tokenBiz.setAttribute1Lbl("Refund Number");
        tokenBiz.setAttribute1(cMsg.arRfRqstPk.getValue().toString());
        tokenBiz.setAttribute2Lbl("Customer");
        tokenBiz.setAttribute2(cMsg.billToCustAcctCd.getValue());
        tokenBiz.setAttribute3Lbl("Bill To Loc");
        tokenBiz.setAttribute3(cMsg.billToCustLocCd.getValue());
        tokenBiz.setAttribute4Lbl("Total Refund Amount");
        tokenBiz.setAttribute4(formatAmount(cMsg.xxTotRfAmt.getValue()));
        tokenBiz.setAttribute5Lbl("Refund Comment");
        tokenBiz.setAttribute5(cMsg.arRcptRfCmntTxt.getValue());

        tokenBiz.setBizScreenId("NFCL2610");
        tokenBiz.setBizScreenParams(new Object[] {cMsg.arRfRqstPk.getValue().toString(), cMsg.billToCustAcctCd.getValue() });

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        S21NwfProcess process = null;

        try {
            context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            process = context.newProcess(ZYPCodeDataUtil.getVarCharConstValue(AR_REFUND_WF_ID, cMsg.glblCmpyCd.getValue()));
            process.setDocumentId(arRfRqstPk.toString());
        } catch (S21NwfSystemException e) {
            throw new S21AbendException(NFCM0624E);
        }
        S21NwfToken token = process.getToken();
        token.setTokenObj(tokenBiz);

        try {
            token.start();
            // START 2018/08/03 E.Kameishi [QC#27462,ADD]
            String apvrUsrId = getWfGroups(ZYPCodeDataUtil.getVarCharConstValue(AR_REFUND_WF_ID, cMsg.glblCmpyCd.getValue()), arRfRqstPk.toString());
            updateWfRqst(cMsg, apvrUsrId, arRfRqstPk);
            // END 2018/08/03 E.Kameishi [QC#27462,ADD]
            
        } catch (S21NwfBizException e) {
            throw new S21AbendException(NFCM0624E);
        } catch (S21NwfException e) {
            throw new S21AbendException(NFCM0624E);
        }
    }

    // START 2020/06/19 R.Kurahashi [QC#56956,ADD]
    /**
     * startWorkflowAccToHR
     * @param cMsg NFCL2610CMsg
     */
    private static void startWorkflowAccToHR(NFCL2610CMsg cMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
        String userId = userProfSvc.getContextUserInfo().getUserId();
        BigDecimal arRfRqstPk = cMsg.arRfRqstPk.getValue();

        NFZC500001TokenObject tokenBiz = new NFZC500001TokenObject();
        tokenBiz.setPsnNum(userId);
        tokenBiz.setBizId("NFCL2610");
        tokenBiz.setGlblCmpyCd(cMsg.glblCmpyCd.getValue());
        tokenBiz.setCondStr1(userId);
        tokenBiz.setCondNum1(cMsg.xxTotRfAmt.getValue().negate());
        tokenBiz.setCondNum2(arRfRqstPk);

        tokenBiz.setAttribute1Lbl("Refund Number");
        tokenBiz.setAttribute1(cMsg.arRfRqstPk.getValue().toString());
        tokenBiz.setAttribute2Lbl("Customer");
        tokenBiz.setAttribute2(cMsg.billToCustAcctCd.getValue());
        tokenBiz.setAttribute3Lbl("Bill To Loc");
        tokenBiz.setAttribute3(cMsg.billToCustLocCd.getValue());
        tokenBiz.setAttribute4Lbl("Total Refund Amount");
        tokenBiz.setAttribute4(formatAmount(cMsg.xxTotRfAmt.getValue()));
        tokenBiz.setAttribute5Lbl("Refund Comment");
        tokenBiz.setAttribute5(cMsg.arRcptRfCmntTxt.getValue());

        tokenBiz.setBizScreenId("NFCL2610");
        tokenBiz.setBizScreenParams(new Object[] {cMsg.arRfRqstPk.getValue().toString(), cMsg.billToCustAcctCd.getValue() });

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        S21NwfProcess process = null;
        try {
            context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            process = context.newProcess(ZYPCodeDataUtil.getVarCharConstValue(AR_REFUND_WF_ID, cMsg.glblCmpyCd.getValue()));
            process.setDocumentId(arRfRqstPk.toString());

            S21NwfToken token = process.getToken();
            token.setTokenObj(tokenBiz);

            token.start();
            String apvrUsrId = getApvrUsrId(ZYPCodeDataUtil.getVarCharConstValue(AR_REFUND_WF_ID, cMsg.glblCmpyCd.getValue()), arRfRqstPk.toString());
            // START 2020/07/22 R.Kurahashi [QC#56956-1,MOD]
            //if (!ZYPCommonFunc.hasValue(apvrUsrId)) {
            if (!ZYPCommonFunc.hasValue(apvrUsrId) && !checkApproved(cMsg, arRfRqstPk)) {
            // END 2020/07/22 R.Kurahashi [QC#56956-1,MOD]
                cMsg.arRfRqstPk.clear();
                throw new S21NwfBizException("NFCM0531E", new String[] {"Approver" });
            }
            updateWfRqst(cMsg, apvrUsrId, arRfRqstPk);
        } catch (S21NwfSystemException e) {
            cMsg.setMessageInfo(NFCM0624E);
        } catch (S21NwfBizException e) {
            if ("NFCM0531E".equals(e.getMessageInfo().getCode())) {
                cMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
            } else {
                cMsg.setMessageInfo(NFCM0624E);
            }
        } catch (S21NwfException e) {
            cMsg.setMessageInfo(NFCM0624E);
        }
    }
    // END 2020/06/19 R.Kurahashi [QC#56956,ADD]

    private static String formatAmount(BigDecimal amt) {
        if (!ZYPCommonFunc.hasValue(amt)) {
            return null;
        }
        NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
        return df.format(amt);
    }
    // END 2016/04/12 K.Kojima [QC#6871,ADD]

    // START 2017/01/13 [QC#16941,ADD]
    // START 2021/09/09 G.Delgado [QC#58793, MOD]
    /**
     * checkArRqst
     * @param cMsg NFCL2610CMsg
     * @param sMsg NFCL2610SMsg
     * @return true if no error, else false
     */
    // public static boolean checkArRqst(NFCL2610CMsg cMsg) {
    public static boolean checkArRqst(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {
    // END 2021/09/09 G.Delgado [QC#58793, MOD]

        boolean result = true;
        // START 2021/09/09 G.Delgado [QC#58793, MOD]
        // List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox_3", ZYPConstant.CHKBOX_ON_Y);
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_3", ZYPConstant.CHKBOX_ON_Y);
        int firstErrorIndex = -1;
        int pageFrom = -1;
        int nextPageIndex = -1;
        // END 2021/09/09 G.Delgado [QC#58793, MOD]

        for (int checkRow : selectedRows) {
            // START 2021/09/09 G.Delgado [QC#58793, ADD]
            if (!result && checkRow >= nextPageIndex) {
                break;
            }
            // END 2021/09/09 G.Delgado [QC#58793, ADD]

            // START 2021/09/09 G.Delgado [QC#58793, MOD]
            // NFCL2610_ACMsg aCMsg = cMsg.A.no(checkRow);
            // int resultCnt = NFCL2610Query.getInstance().countAlreadyRqstData(aCMsg, cMsg.glblCmpyCd.getValue());
            NFCL2610_ASMsg aSMsg = sMsg.A.no(checkRow);
            // START 2022/04/21 K.Suzuki [QC#59333,ADD]
            if (AR_TRX_TP.ON_ACCOUNT.equals(aSMsg.arTrxTpCd_A.getValue())) {
                if (!checkOnAccountStatusPartial(cMsg, aSMsg)) {
                    result = false;
                    aSMsg.xxChkBox_3.setErrorInfo(1, NFCM0919E);

                    if (firstErrorIndex < 0) {
                        firstErrorIndex = checkRow;
                        pageFrom = convertIndexToPageFirstRowIndex(cMsg.A.length(), firstErrorIndex);
                        nextPageIndex = pageFrom + cMsg.A.length();
                    }
                }
            }
            // END   2022/04/21 K.Suzuki [QC#59333,ADD]
            int resultCnt = NFCL2610Query.getInstance().countAlreadyRqstData(aSMsg.arTrxNum_A.getValue(), cMsg.glblCmpyCd.getValue());
            // END 2021/09/09 G.Delgado [QC#58793, MOD]
            if (resultCnt > 0) {
                result = false;
                // START 2021/09/09 G.Delgado [QC#58793, MOD]
                // aCMsg.xxChkBox_3.setErrorInfo(1, NFCM0875E);
                // continue;
                aSMsg.xxChkBox_3.setErrorInfo(1, NFCM0875E);

                if (firstErrorIndex < 0) {
                    firstErrorIndex = checkRow;
                    pageFrom = convertIndexToPageFirstRowIndex(cMsg.A.length(), firstErrorIndex);
                    nextPageIndex = pageFrom + cMsg.A.length();
                }
                // END 2021/09/09 G.Delgado [QC#58793, MOD]
            }
        }

        // START 2021/09/09 G.Delgado [QC#58793, ADD]
        if (!result) {
            // Show first page with error
            pagenation(cMsg, sMsg, pageFrom);
        }
        // END 2021/09/09 G.Delgado [QC#58793, ADD]
        return result;
    }
    // END   2017/01/13 [QC#16941,ADD]
    // START 2018/08/03 E.Kameishi [QC#27462,ADD]
    /**
     * getWfGroups
     * @param wfId String
     * @param documentId String
     * @return String
     * @throws S21NwfException S21NwfException
     */
    private static String getWfGroups(String wfId, String documentId) throws S21NwfException {
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = factory.getContex();
        List<S21NwfProcess> procs = context.getProcessForBiz(wfId, documentId);
        String wfGroups = "";
        for (S21NwfProcess proc : procs) {
            S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
            List<S21NwfUtilBizWorkItem> tasks = p.getTasks();
            for (S21NwfUtilBizWorkItem wi : tasks) {
                if (wi.isApprovable() && wi.isComplete() == false) {
                        wfGroups = wi.getGroups().get(0);
                        break;
                }
            }
        }
        return wfGroups;
    }

    // START 2020/06/19 R.Kurahashi [QC#56956,ADD]
    /**
     * getApvrUsrId
     * @param wfId String
     * @param documentId String
     * @return String
     * @throws S21NwfException S21NwfException
     */
    private static String getApvrUsrId(String wfId, String documentId) throws S21NwfException {
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = factory.getContex();
        List<S21NwfProcess> procs = context.getProcessForBiz(wfId, documentId);
        String apvrUsrId = "";
        for (S21NwfProcess proc : procs) {
            S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
            List<S21NwfUtilBizWorkItem> tasks = p.getTasks();
            for (S21NwfUtilBizWorkItem wi : tasks) {
                if (ZYPCommonFunc.hasValue(apvrUsrId)) {
                    break;
                }
                if (wi.isApprovable() && wi.isComplete() == false) {
                    List<S21NwfUserRole> users = wi.getToUsers();
                    for (S21NwfUserRole user : users) {
                        apvrUsrId = user.getUserRole();
                        break;
                    }
                }
            }
        }
        return apvrUsrId;
    }
    // END 2020/06/19 R.Kurahashi [QC#56956,ADD]
    
    private static void updateWfRqst(NFCL2610CMsg cMsg, String apvrUsrId, BigDecimal arRfRqstPk) {
        AR_RF_RQSTTMsg tmsg = new AR_RF_RQSTTMsg();
        setValue(tmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        setValue(tmsg.arRfRqstPk, arRfRqstPk);
        tmsg = (AR_RF_RQSTTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tmsg);
        if (tmsg != null) {
            if (apvrUsrId != null && !apvrUsrId.isEmpty()) {
                setValue(tmsg.arRfApvlUsrId, apvrUsrId);
                // START 2020/06/19 R.Kurahashi [QC#56956,MOD]
                // setValue(tmsg.arRfApvlUsrNm, ZYPCodeDataUtil.getVarCharConstValue(apvrUsrId, cMsg.glblCmpyCd.getValue()));
                String userNm = null;
                AUTH_PSNTMsg inMsg = new AUTH_PSNTMsg();
                inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
                inMsg.setConditionValue("usrNm01", apvrUsrId);
                inMsg.setMaxCount(1);
                inMsg.setSQLID("053");
                AUTH_PSNTMsgArray outMsg = (AUTH_PSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
                if (outMsg.length() != 0) {
                    userNm = ZYPCommonFunc.concatString(outMsg.no(0).firstNm.getValue(), " ", outMsg.no(0).lastNm.getValue());
                }
                setValue(tmsg.arRfApvlUsrNm, userNm);
                // END 2020/06/19 R.Kurahashi [QC#56956,MOD]
                EZDTBLAccessor.update(tmsg);
            }
        }
    }
    // END 2018/08/03 E.Kameishi [QC#27462,ADD]

    // START 2020/05/08 [QC#56436, MOD]
    public static String getCoaAfflCd(String glblCmpyCd, String arRfTpCd) {
        AR_RF_TPTMsg tMsg = new AR_RF_TPTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.arRfTpCd.setValue(arRfTpCd);
        AR_RF_TPTMsg resTMsg = (AR_RF_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);
        if (resTMsg == null) {
            return null;
        } else {
            return resTMsg.coaAfflCd.getValue();
        }
    }
    // END   2020/05/08 [QC#56436, MOD]
    
    // START 2020/06/29 R.Kurahashi [QC#57119,ADD]
    public static String getVndSiteTxt(String glblCmpyCd, String arRfTpCd) {
        AR_RF_TPTMsg tMsg = new AR_RF_TPTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.arRfTpCd.setValue(arRfTpCd);
        AR_RF_TPTMsg resTMsg = (AR_RF_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);
        if (resTMsg == null) {
            return null;
        } else {
            return resTMsg.vndSiteTxt.getValue();
        }
    }
    // END 2020/06/29 R.Kurahashi [QC#57119,ADD]
    // START 2020/07/22 R.Kurahashi [QC#56956-1,ADD]
    private static boolean checkApproved(NFCL2610CMsg cMsg, BigDecimal arRfRqstPk) {
        AR_RF_RQSTTMsg tmsg = new AR_RF_RQSTTMsg();
        setValue(tmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        setValue(tmsg.arRfRqstPk, arRfRqstPk);
        tmsg = (AR_RF_RQSTTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tmsg);
        String arDsWfStsCd = tmsg.arDsWfStsCd.getValue();
        if (ZYPCommonFunc.hasValue(arDsWfStsCd) && AR_DS_WF_STS.APPROVED.equals(arDsWfStsCd)) {
            return true;
        } else {
            return false;
        }
    }
    // END 2020/07/22 R.Kurahashi [QC#56956-1,ADD]
    // START 2020/07/27 R.Kurahashi [QC#57347,ADD]
    private static void updateWfRqstForApprove(NFCL2610CMsg cMsg, String usrId, BigDecimal arRfRqstPk) {
        AR_RF_RQSTTMsg tmsg = new AR_RF_RQSTTMsg();
        setValue(tmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        setValue(tmsg.arRfRqstPk, arRfRqstPk);
        tmsg = (AR_RF_RQSTTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tmsg);
        if (tmsg != null) {
            setValue(tmsg.arDsWfStsCd, AR_DS_WF_STS.APPROVED);
            setValue(tmsg.arRfApvlUsrId, usrId);
            String userNm = null;
            AUTH_PSNTMsg inMsg = new AUTH_PSNTMsg();
            inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("usrNm01", usrId);
            inMsg.setMaxCount(1);
            inMsg.setSQLID("053");
            AUTH_PSNTMsgArray outMsg = (AUTH_PSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
            if (outMsg.length() != 0) {
                userNm = ZYPCommonFunc.concatString(outMsg.no(0).firstNm.getValue(), " ", outMsg.no(0).lastNm.getValue());
            }
            setValue(tmsg.arRfApvlUsrNm, userNm);
            EZDTBLAccessor.update(tmsg);
        }
    }
    // END 2020/07/27 R.Kurahashi [QC#57347,ADD]

    // START 2022/04/21 K.Suzuki [QC#59333,ADD]
    /**
     * checkOnAccountStatusPartial
     * @param NFCL2610CMsg
     * @param NFCL2610_ASMsg
     * @return boolean
     */
    private static boolean checkOnAccountStatusPartial(NFCL2610CMsg cMsg, NFCL2610_ASMsg aSMsg) {

        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.arTrxBalPk, aSMsg.arTrxBalPk_A);
        AR_TRX_BALTMsg outArTrxBalMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKey(inArTrxBalMsg);

        if (AR_CASH_APPLY_STS.PARTIAL.equals(outArTrxBalMsg.arCashApplyStsCd.getValue())) {
            return false;
        }
        return true;
    }
    // END   2022/04/21 K.Suzuki [QC#59333,ADD]

    // add start 2022/07/27 QC#57418
    /**
     * saveProcess
     * @param cMsg NFCL2610CMsg
     * @param sMsg NFCL2610SMsg
     * @param arRfStsCd String
     * @return boolean
     */
    public static boolean saveProcess(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg, String arRfStsCd) {
        // Update SMsg
        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        setPagenation(cMsg, sMsg, pageFromNum);
        cMsg.setCommitSMsg(true);

        if (!checkMultipleRefundFlag(cMsg, sMsg)) {
            return false;
        }

        if (!checkSupplier(cMsg, sMsg)) {
            return false;
        }

        // Check Already requested
        if (!checkArRqst(cMsg, sMsg)) {
            return false;
        }

        // Create AR Refund Request
        if (!saveArRefundRequest(cMsg, sMsg, arRfStsCd)) {
            return false;
        }
        return true;
    }

    private static boolean saveArRefundRequest(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg, String arRfStsCd) {
        String stdCcyCd = null;
        GLBL_CMPYTMsg outBlblCmpyTMsg = NFCL2610Query.findGlblCmpy(cMsg);
        if (outBlblCmpyTMsg != null) {
            stdCcyCd = outBlblCmpyTMsg.stdCcyCd.getValue();
        }

        ZYPEZDItemValueSetter.setValue(cMsg.arRfRqstPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_RF_RQST_SQ));
        if (!createArRefundRequestDetail(cMsg, sMsg, stdCcyCd)) {
            return false;
        }

        // AR Refund Request
        createArRefundRequest(cMsg, stdCcyCd, arRfStsCd);

        if (ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            return false;
        }
        return true;
    }

    /**
     * workflowProcess
     * @param cMsg NFCL2610CMsg
     * @param sMsg NFCL2610SMsg
     * @return boolean
     */
    public static boolean workflowProcess(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {
        String defCoaAfflCd = ZYPCodeDataUtil.getVarCharConstValue(DEF_COA_AFFL_CD, cMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(defCoaAfflCd)) {
            cMsg.setMessageInfo("NFCM0856E", new String[] {DEF_COA_AFFL_CD });
            return false;
        }

        String coaAfflCd = getCoaAfflCd(cMsg.glblCmpyCd.getValue(), cMsg.arRfTpCd_H.getValue());

        if (ZYPCommonFunc.hasValue(coaAfflCd) && defCoaAfflCd.equals(coaAfflCd)) {
            // Workflow according to HR hierarchy
            startWorkflowAccToHR(cMsg);
        } else {
            updateWfRqstForApprove(cMsg, cMsg.getUserID(), cMsg.arRfRqstPk.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            return false;
        }
        return true;
    }

    /**
     * clearArRfStsCd
     * @param glblCmpyCd String
     * @param arRfRqstPk String
     */
    public static void clearArRfStsCd(String glblCmpyCd, BigDecimal arRfRqstPk) {
        AR_RF_RQSTTMsg tmsg = new AR_RF_RQSTTMsg();
        setValue(tmsg.glblCmpyCd, glblCmpyCd);
        setValue(tmsg.arRfRqstPk, arRfRqstPk);
        tmsg = (AR_RF_RQSTTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tmsg);
        if (tmsg != null && AR_RF_STS.SAVED.equals(tmsg.arRfStsCd.getValue())) {
            tmsg.arRfStsCd.clear();
            EZDTBLAccessor.update(tmsg);
        }
    }
    // add end 2022/07/27 QC#57418
}
