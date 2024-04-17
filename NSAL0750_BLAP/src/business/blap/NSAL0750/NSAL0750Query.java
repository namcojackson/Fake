/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0750;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2015/12/04   Hitachi         T.Tsuchida      Update          QC#1476
 * 2016/11/16   Hitachi         T.Kanasaka      Update          QC#15942
 * 2016/12/08   Hitachi         T.Mizuki        Update          QC#4210
 *</pre>
 */
public final class NSAL0750Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance
     */
    private static final NSAL0750Query INSTANCE = new NSAL0750Query();

    /**
     * Private constructor
     */
    private NSAL0750Query() {
        super();
    }

    /**
     * Singleton instance getter
     * @return NSAL0750Query
     */
    public static NSAL0750Query getInstance() {
        return INSTANCE;
    }

    /**
     * Service Memo Reason
     * @param cMsg NSAL0750CMsg
     * @return SVC_MEMO_RSNTMsgArray
     */
    public SVC_MEMO_RSNTMsgArray getServiceMemoReasonInfo(NSAL0750CMsg cMsg) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("svcMemoTpCd01", SVC_MEMO_TP.INVOICING_RULES);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    // START 2016/02/22 T.Aoyagi [QC3694, MOD]
    /**
     * DS SContract Info
     * @param cMsg NSAL0750CMsg
     * @param sMsg NSAL0750SMsg
     * @param dsContrPkList List<BigDecimal>
     * @param dsContrDtlPkList List<BigDecimal>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDSContractInfo(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg, List<BigDecimal> dsContrPkList, List<BigDecimal> dsContrDtlPkList) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", dsContrPkList);
        params.put("dsContrDtlPk", dsContrDtlPkList);
        // START 2016/11/16 T.Kanasaka [QC#15942, MOD]
//        params.put("dsContrDtlTpCdIsFLT", DS_CONTR_DTL_TP.FLEET);
        List<String> dsContrDtlTpCdList = new ArrayList<String>();
        dsContrDtlTpCdList.add(DS_CONTR_DTL_TP.FLEET);
        dsContrDtlTpCdList.add(DS_CONTR_DTL_TP.AGGREGATE);
        params.put("dsContrDtlTpCdList", dsContrDtlTpCdList);
        // END 2016/11/16 T.Kanasaka [QC#15942, MOD]
        // mod start 2016/12/08 CSA QC#4210
//        params.put("limitCount", SEARCH_LIMIT_CNT);
        // mod end 2016/12/08 CSA QC#4210
        return getSsmEZDClient().queryEZDMsgArray("getDSContractDetail", params, sMsg.A);
    }
    // END 2016/02/22 T.Aoyagi [QC3694, MOD]

    /**
     * Service Memo
     * @param cMsg NSAL0750SMsg
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMemo(NSAL0750SMsg cMsg, BigDecimal dsContrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContractPk", dsContrPk);
        params.put("contractMemo", SVC_MEMO_CATG.CONTRACT_MEMO);
        params.put("updateTp", SVC_MEMO_TP.INVOICING_RULES);
        return getSsmEZDClient().queryObject("getSvcMemo", params);
    }

    /**
     * DS_CONTR_STS_V
     * @param cMsg NSAL0750SMsg
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContStsV(NSAL0750CMsg cMsg, BigDecimal dsContrPk) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", dsContrPk);
        params.put("dsContrStsCd", new String[] {DS_CONTR_STS.EXPIRED, DS_CONTR_STS.CANCELLED, DS_CONTR_STS.TERMINATED });
        return getSsmEZDClient().queryObject("getContStsV", params);
    }

    /**
     * DS_CONTR_DTL_STS_V
     * @param cMsg NSAL075CMsg
     * @param dsContrPk BigDecimal
     * @param dsContractDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrDtlStsV(NSAL0750CMsg cMsg, BigDecimal dsContrPk, BigDecimal dsContractDtlPk) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", dsContrPk);
        params.put("dsContrDtlStsCd", new String[] {DS_CONTR_DTL_STS.EXPIRED, DS_CONTR_DTL_STS.CANCELLED, DS_CONTR_DTL_STS.TERMINATED });
        params.put("dsContractDtlPk", dsContractDtlPk);
        return getSsmEZDClient().queryObject("getContrDtlStsV", params);
    }
}
