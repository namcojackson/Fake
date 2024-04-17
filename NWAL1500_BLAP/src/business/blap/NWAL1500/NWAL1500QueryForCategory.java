/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500;

import static business.blap.NWAL1500.constant.NWAL1500Constant.BIZ_ID;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         T.Yoshida        Create          n/a
 * </pre>
 */
public final class NWAL1500QueryForCategory extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NWAL1500QueryForCategory MY_INSTANCE = new NWAL1500QueryForCategory();

    /**
     * Constructor.
     */
    private NWAL1500QueryForCategory() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1500Query
     */
    public static NWAL1500QueryForCategory getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get DS Order Category Code List
     * @param bizMsg NWAL1500CMsg
     * @return DS Order Category Code List
     */
    public S21SsmEZDResult getDsOrdCatgCdList(NWAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdCatgDescTxt", bizMsg.dsOrdCatgDescTxt.getValue());
        params.put("bizId", BIZ_ID);

        return getSsmEZDClient().queryObjectList("getDsOrdCatgCdList", params);
    }

    /**
     * Check Exist DS Order Category
     * @param bizMsg NWAL1500CMsg
     * @return true: exist
     */
    public boolean isExistDsOrdCatg(NWAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdCatgDescTxt", bizMsg.dsOrdCatgDescTxt.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistDsOrdCatg", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * get DS Order Type List
     * @param bizMsg NWAL1500CMsg
     * @return DS Order Type List
     */
    public S21SsmEZDResult getDsOrdTpList(NWAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());
        params.put("dsOrdTpDescTxt", bizMsg.dsOrdTpDescTxt.getValue());

        return getSsmEZDClient().queryObjectList("getDsOrdTpList", params);
    }

    /**
     * get DS Order Type Code
     * @param bizMsg NWAL1500CMsg
     * @return DS Order Type Code
     */
    public S21SsmEZDResult getDsOrdTpCd(NWAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());

        return getSsmEZDClient().queryObject("getDsOrdTpCd", params);
    }

    /**
     * get Customer And Additional Data
     * @param bizMsg NWAL1500CMsg
     * @param effDt Effective Date (YYYYMMDD)
     * @return Customer And Additional Data
     */
    public S21SsmEZDResult getCustAddlInfo(NWAL1500CMsg bizMsg, String effDt) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("effDt", effDt);
        params.put("rgtnSts", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getCustAddlInfo", params);
    }

    /**
     * Get Payment Term Cash Discount Code
     * @param bizMsg NWAL1500CMsg
     * @return Payment Term Cash Discount Code
     */
    public S21SsmEZDResult getPmtTermCashDiscCd(NWAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("billToCustCd", bizMsg.billToCustCd.getValue());

        return getSsmEZDClient().queryObject("getPmtTermCashDiscCd", params);
    }

    /**
     * <pre>
     * get Service Level Pulldown Data
     * @param glblCmpyCd Global Company Code
     * @param lineBizTpCd Line of Business Code
     * @param frtCondCd Freight Condition COde (Option)
     * @return S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getShpgSvcLvlDataList(String glblCmpyCd, String lineBizTpCd, String frtCondCd) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("lineBizTpCd", lineBizTpCd);
        if (ZYPCommonFunc.hasValue(frtCondCd)) {
            params.put("frtCondCd", frtCondCd);
        } else {
            params.remove("frtCondCd");
        }
        return getSsmEZDClient().queryObjectList("getShpgSvcLvlDataList", params);
    }
}
