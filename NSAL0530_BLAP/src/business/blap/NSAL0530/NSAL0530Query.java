/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0530;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.HashMap;
import java.util.Map;

import business.blap.NSAL0530.common.NSAL0530CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_SRCH_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Solution Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Hitachi         T.Tomita        Create          N/A
 * 2016/05/10   Hitachi         T.Tomita        Update          QC#7976
 *</pre>
 */
public final class NSAL0530Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0530Query INSTANCE = new NSAL0530Query();

    /**
     * Private constructor
     */
    private NSAL0530Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0530Query singleton instance
     */
    public static NSAL0530Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL0530CMsg
     * @param sMsg NSAL0530SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0530CMsg cMsg, NSAL0530SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg, cnt), sMsg.A);
    }

    /**
     * getSsmParam
     * @param cMsg NSAL0530CMsg
     * @param sMsg NSAL0530SMsg
     * @param cnt int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(NSAL0530CMsg cMsg, NSAL0530SMsg sMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();

        // START 2016/05/10 T.Tomita [QC#7976, MOD]
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcSlnsq", cMsg.xxScrItem29Txt_HS.getValue());
        params.put("svcSlnNm", cMsg.svcSlnNm_H.getValue());
        params.put("svcConfigMstrPk", cMsg.xxScrItem29Txt_HC.getValue());
        params.put("svcMachMstrPk", cMsg.svcMachMstrPk_H.getValue());
        params.put("istlDt", cMsg.istlDt_H.getValue());
        params.put("serNum", cMsg.serNum_H.getValue());
        params.put("custMachCtrlNum", cMsg.custMachCtrlNum_H.getValue());
        params.put("custIssPoNum", cMsg.custIssPoNum_H.getValue());
        params.put("mdseCd", cMsg.mdseCd_H.getValue());
        params.put("cpoOrdNum", cMsg.cpoOrdNum_H.getValue());
        // END 2016/05/10 T.Tomita [QC#7976, MOD]

        if (DS_ACCT_SRCH_TP.ACCOUNT_NUMBER.equals(cMsg.dsAcctSrchTpCd_H1.getValue())) {
            params.put("billToCustCd", cMsg.xxLocNm_H1.getValue());
        }
        if (DS_ACCT_SRCH_TP.ACCOUNT_NAME.equals(cMsg.dsAcctSrchTpCd_H1.getValue())) {
            params.put("billToCustLocNm", cMsg.xxLocNm_H1.getValue());
        }
        if (DS_ACCT_SRCH_TP.ACCOUNT_ADDRESS.equals(cMsg.dsAcctSrchTpCd_H1.getValue())) {
            params.put("billToCustAddr", cMsg.xxLocNm_H1.getValue());
        }

        if (DS_ACCT_SRCH_TP.ACCOUNT_NUMBER.equals(cMsg.dsAcctSrchTpCd_H2.getValue())) {
            params.put("sellToCustCd", cMsg.xxLocNm_H2.getValue());
        }
        if (DS_ACCT_SRCH_TP.ACCOUNT_NAME.equals(cMsg.dsAcctSrchTpCd_H2.getValue())) {
            params.put("sellToCustLocNm", cMsg.xxLocNm_H2.getValue());
        }
        if (DS_ACCT_SRCH_TP.ACCOUNT_ADDRESS.equals(cMsg.dsAcctSrchTpCd_H2.getValue())) {
            params.put("sellToCustAddr", cMsg.xxLocNm_H2.getValue());
        }

        if (DS_ACCT_SRCH_TP.ACCOUNT_NUMBER.equals(cMsg.dsAcctSrchTpCd_H3.getValue())) {
            params.put("shipToCustCd", cMsg.xxLocNm_H3.getValue());
        }
        if (DS_ACCT_SRCH_TP.ACCOUNT_NAME.equals(cMsg.dsAcctSrchTpCd_H3.getValue())) {
            params.put("shipToCustLocNm", cMsg.xxLocNm_H3.getValue());
        }
        if (DS_ACCT_SRCH_TP.ACCOUNT_ADDRESS.equals(cMsg.dsAcctSrchTpCd_H3.getValue())) {
            params.put("shipToCustAddr", cMsg.xxLocNm_H3.getValue());
        }

        params.put("tMdlNm", cMsg.mdlNm_H.getValue());
        params.put("coaMdseTpCd", cMsg.coaMdseTpCd_HS.getValue());
        params.put("dsContrNum", cMsg.dsContrNum_H.getValue());
        params.put("rowNum", cnt);

        // SVC_MACH_MSTR Search Flag
        String srchMachFlg = ZYPConstant.FLG_OFF_N;
        if (NSAL0530CommonLogic.hasValueItems(
                cMsg.svcMachMstrPk_H
              , cMsg.istlDt_H
              , cMsg.serNum_H
              , cMsg.custMachCtrlNum_H
              , cMsg.custIssPoNum_H
              , cMsg.mdseCd_H
              , cMsg.cpoOrdNum_H
              , cMsg.xxLocNm_H3)) {
            srchMachFlg = ZYPConstant.FLG_ON_Y;
        } else if (DS_ACCT_SRCH_TP.ACCOUNT_NUMBER.equals(cMsg.dsAcctSrchTpCd_H1.getValue()) && hasValue(cMsg.xxLocNm_H1)) {
            srchMachFlg = ZYPConstant.FLG_ON_Y;
        } else if (DS_ACCT_SRCH_TP.ACCOUNT_NUMBER.equals(cMsg.dsAcctSrchTpCd_H2.getValue()) && hasValue(cMsg.xxLocNm_H2)) {
            srchMachFlg = ZYPConstant.FLG_ON_Y;
        }
        params.put("srchMachFlg", srchMachFlg);

        // BILL_TO_CUST Search Flag
        String srchBillFlg = ZYPConstant.FLG_OFF_N;
        if ((DS_ACCT_SRCH_TP.ACCOUNT_NAME.equals(cMsg.dsAcctSrchTpCd_H1.getValue()) || DS_ACCT_SRCH_TP.ACCOUNT_ADDRESS.equals(cMsg.dsAcctSrchTpCd_H1.getValue())) && hasValue(cMsg.xxLocNm_H1)) {
            srchBillFlg = ZYPConstant.FLG_ON_Y;
        }
        params.put("srchBillFlg", srchBillFlg);

        // SELL_TO_CUST Search Flag
        String srchSellFlg = ZYPConstant.FLG_OFF_N;
        if ((DS_ACCT_SRCH_TP.ACCOUNT_NAME.equals(cMsg.dsAcctSrchTpCd_H2.getValue()) || DS_ACCT_SRCH_TP.ACCOUNT_ADDRESS.equals(cMsg.dsAcctSrchTpCd_H2.getValue())) && hasValue(cMsg.xxLocNm_H2)) {
            srchSellFlg = ZYPConstant.FLG_ON_Y;
        }
        params.put("srchSellFlg", srchSellFlg);

        return params;
    }
}
