/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1150;

import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.blap.NPAL1150.constant.NPAL1150Constant;
import business.db.EDI_ASN_DTL_WRKTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NPAL1150 ASN Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/15   Hitachi         T.Kawazu        Create          N/A
 * 2013/06/13   Hitachi         T.Tomita        Update          QC1233
 * 2013/07/24   Hitachi         T.Kawazu        Update          QC1419
 * 2013/07/30   Hitachi         A.Kohinata      Update          QC1388
 * 04/28/2014   Hitachi         T.Kawazu        Update          ITG#506831
 *</pre>
 */

public final class NPAL1150Query extends S21SsmEZDQuerySupport {
    /** Singleton instance. */
    private static final NPAL1150Query MY_INSTANCE = new NPAL1150Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NPAL1150Query() {
        super();
    }

    /**
     * <pre>
     * Get the NPAL1150Query instance.
     * </pre>
     * @return NPAL1150Query instance
     */
    public static NPAL1150Query getInstance() {
        return MY_INSTANCE;
    }

    private S21SsmBatchClient getSsmBatchClient() {
        return S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getEdiAsnErrorList(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("maxRowNum", sMsg.A.length() + 1);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("vndCd", cMsg.vndCd_A1.getValue());
        ssmParam.put("asnEdiProcStsCd", cMsg.asnEdiProcStsCd_SV.getValue());
        ssmParam.put("ediPoOrdNum", cMsg.ediPoOrdNum_A1.getValue());
        // From ediRcvDateTs ADD '000000'
        if (ZYPCommonFunc.hasValue(cMsg.xxFromDt_A1)) {
            ssmParam.put("ediRcvDateTsFrom", cMsg.xxFromDt_A1.getValue() + NPAL1150Constant.FROMTIME);
        } else {
            ssmParam.put("ediRcvDateTsFrom", cMsg.xxFromDt_A1.getValue());
        }
        // To ediRcvDateTs ADD '999999'
        if (ZYPCommonFunc.hasValue(cMsg.xxToDt_A1)) {
            ssmParam.put("ediRcvDateTsTo", cMsg.xxToDt_A1.getValue() + NPAL1150Constant.TOTIME);
        } else {
            ssmParam.put("ediRcvDateTsTo", cMsg.xxToDt_A1.getValue());
        }
        // 2013/06/13 QC1233 T.Tomita Add Start
        ssmParam.put("batErrMsgTxt", cMsg.batErrMsgTxt_A1.getValue());
        // 2013/06/13 QC1233 T.Tomita Add End

        return getSsmEZDClient().queryEZDMsgArray("getEdiAsnErrorList", ssmParam, sMsg.A);
    }

    /**
     * getSoLinkHeader
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSoLinkHeader(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        int activeRowNum = cMsg.xxNum.getValueInt();
        ssmParam.put("asnSoNum", cMsg.A.no(activeRowNum).asnSoNum_B1.getValue());
        ssmParam.put("asterisk", NPAL1150Constant.ASTERISK);
        // START ITG#506831 ADD
        ssmParam.put("poOrdNumLength", String.valueOf(NPAL1150Constant.PO_ORD_NUM_LENGTH));
        // END ITG#506831 ADD

        // 2013/07/30 QC1388 A.Kohinata Update Start
        // return
        // getSsmEZDClient().queryEZDMsgArray("getSoLinkHeader",
        // ssmParam, cMsg.B);
        return getSsmEZDClient().queryObjectList("getSoLinkHeader", ssmParam);
        // 2013/07/30 QC1388 A.Kohinata Update End
    }

    // 2013/06/13 QC1233 T.Tomita Update Start
    /**
     * getSoLinkHeader
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSoLinkDetail(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
        int activeRowNum = cMsg.xxNum.getValueInt();
        String soNum = cMsg.A.no(activeRowNum).asnSoNum_B1.getValue();
        EDI_ASN_DTL_WRKTMsg inMsg = new EDI_ASN_DTL_WRKTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inMsg.setConditionValue("asnSoNum01", soNum);
        int count = EZDTBLAccessor.count(inMsg);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("asnSoNum", soNum);
        ssmParam.put("maxRowNum", cMsg.C.length() + 1);
        if (count == 0) {
            ssmParam.put("xrefUseFlg", ZYPConstant.FLG_ON_Y);
        }

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList("getSoLinkDetail", ssmParam);
        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        return ssmResult;
    }

    // 2013/06/13 QC1233 T.Tomita Update End

    /**
     * Search PO_DTL
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return result.getSearchList
     */
    public Map<String, Object> getLogicLineNum(String poOrdNum, String poOrdDtlLineNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("poOrdNum", poOrdNum);
        ssmParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
        Map<String, Object> resultList = (Map<String, Object>) getSsmBatchClient().queryObject("getLogicLineNum", ssmParam);
        if (resultList == null) {
            resultList = new HashMap<String, Object>();
        }
        return resultList;
    }

    /**
     * @param poOrdNum
     * @param poOrdDtlLineNum
     * @return
     */
    public Map<String, Object> getPoDtl(String poOrdNum, String dispPoDtlLineNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("poOrdNum", poOrdNum);
        ssmParam.put("dispPoDtlLineNum", dispPoDtlLineNum);

        Map<String, Object> result = (Map<String, Object>) getSsmBatchClient().queryObject("getPoDtl", ssmParam);

        return result;
    }

}
