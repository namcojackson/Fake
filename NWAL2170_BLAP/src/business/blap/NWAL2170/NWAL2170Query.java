/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2170;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL2170Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         M.Yamada        Create          N/A
 * 2016/07/05   Fujitsu         T.Ishii         Update          S21_NA#9283
 * 2016/08/24   Fujitsu         M.Yamada        Update          QC#13057
 * 2017/02/13   Fujitsu         N.Aoyama        Update          QC#16575
 * 2018/04/06   Fujitsu         A.Kosai         Update          QC#10374
 *</pre>
 */
public final class NWAL2170Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL2170Query MY_INSTANCE = new NWAL2170Query();

    /**
     * Private constructor
     */
    private NWAL2170Query() {
        super();
    }

    /**
     * Get the NWAL2170Query instance.
     * @return NWAL2170Query instance
     */
    public static NWAL2170Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL2170CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getInitDataFromCpoSvc(String glblCmpyCd, NWAL2170CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("refCpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("ordSrcRefNum", bizMsg.ordSrcRefNum.getValue());
        params.put("dsImptOrdPk", bizMsg.dsImptOrdPk_P.getValue());

        String stmtId = "getInitDataFromDsImpSvc";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param slsDt         slsDt
     * @param bizMsg        NWAL2170CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getBllgCycleList(String glblCmpyCd, String slsDt, NWAL2170CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("slsDt", slsDt);

        return getSsmEZDClient().queryObjectList("getBllgCycleList", params);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL2170CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getSvcPrcHdr(String glblCmpyCd, NWAL2170CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("dsImptOrdPk", bizMsg.dsImptOrdPk_P.getValue());

        String stmtId = "getImptSvcPrcHdr";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL2170CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getSvcAddlBasePrc(String glblCmpyCd, NWAL2170CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("dsImptOrdPk", bizMsg.dsImptOrdPk_P.getValue());

        String stmtId = "getImptSvcAddlBasePrc";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL2170CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getSvcAddlChrgPrc(String glblCmpyCd, NWAL2170CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());

        params.put("dsImptOrdPk", bizMsg.dsImptOrdPk_P.getValue());

        String stmtId = "getImptSvcAddlChrgPrc";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL2170CMsg
     * @param tBizMsg       NWAL2170_TCMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getBaseDtlLineNum(String glblCmpyCd, NWAL2170CMsg bizMsg, NWAL2170_TCMsg tBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        // 2018/04/24 QC#10374 Add Start
        params.put("dsOrdPosnNum", tBizMsg.dsOrdPosnNum_T.getValue());
        // 2018/04/24 QC#10374 Add End
        params.put("cpoDtlLineNum", tBizMsg.cpoDtlLineNum_T.getValue());
        params.put("cpoDtlLineSubNum", tBizMsg.cpoDtlLineSubNum_T.getValue());

        return getSsmEZDClient().queryObjectList("getBaseDtlLineNum", params);
    }

    /**
     * get Bill To Customer Info List
     * @param bizMsg NWAL2170CMsg
     * @param custLocNum Customer location Number
     * @return Bill To Customer Info List
     */
    public S21SsmEZDResult getBillToCustInfoList(NWAL2170CMsg bizMsg, String custLocNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("custCd", custLocNum);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getBillToCustInfoList", params);
    }

    /**
     * get Bill To Customer Count
     * @param bizMsg NWAL2170CMsg
     * @param billToCustCd billToCustCd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToCustCount(NWAL2170CMsg bizMsg, String billToCustCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("billToCustCd", billToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getBillToCustCount", params);
    }

    /**
     * <pre>
     * getDsAcctCustCount
     * @param bizMsg    NWAL2170CMsg
     * @param dsAcctNum dsAcctNum
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getDsAcctCustCount(NWAL2170CMsg bizMsg, String dsAcctNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsAcctNum", dsAcctNum);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getDsAcctCustCount", params);
    }

    /**
     * <pre>
     * @param cpoOrdNum cpoOrdNum
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getOrderBillToInfo(String cpoOrdNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoOrdNum", cpoOrdNum);

        return getSsmEZDClient().queryObject("getOrderBillToInfo", params);
    }

    public S21SsmEZDResult getMntBillAsEquipInfo(NWAL2170_ACMsg aBizMsg, NWAL2170CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("billByTpCd", aBizMsg.billByTpCd.getValue());
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        params.put("dsImptOrdPk", bizMsg.dsImptOrdPk_P.getValue());

        String stmtId = "getImptMntBillAsEquipInfo";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    public S21SsmEZDResult getContractIndicatorList() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getContractIndicatorList", params);
    }

    // 2018/04/06 QC#10374 Add Start
    public Integer getRentalOrderCnt(String glblCmpyCd, BigDecimal dsImptOrdPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsImptOrdPk", dsImptOrdPk);
        params.put("ordCatgCtxTpCd", "RENTAL_SHELL_REQUIRED");

        return (Integer) getSsmEZDClient().queryObject("getRentalOrderCnt", params).getResultObject();
    }
    // 2018/04/06 QC#10374 Add End

}
