/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1840;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         T.Murai         Create          N/A
 * </pre>
 */
public final class NWAL1840QueryForItemLine extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1840QueryForItemLine MY_INSTANCE = new NWAL1840QueryForItemLine();

    /**
     * Constructor.
     */
    private NWAL1840QueryForItemLine() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1840Query
     */
    public static NWAL1840QueryForItemLine getInstance() {
        return MY_INSTANCE;
    }
    /**
     * get Base MDSE Code From Manufacturer Item Code
     * @param bizMsg NWAL1840CMsg
     * @param mnfItemCd Manufacturer Item Code
     * @return Base MDSE Code
     */
    public S21SsmEZDResult getBaseMdseCdFromMnfItemCd(NWAL1840CMsg bizMsg, String mnfItemCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("mnfItemCd", mnfItemCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getBaseMdseCdFromMnfItemCd", params);
    }

    /**
     * get Package UOM Code
     * @param bizMsg NWAL1840CMsg
     * @param mdseCd Merchandise Code
     * @return Package UOM Code
     */
    public S21SsmEZDResult getPkgUomCd(NWAL1840CMsg bizMsg, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("mdseCd", mdseCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getPkgUomCd", params);
    }

    /**
     * check Exist merchandise
     * @param bizMsg NWAL1840CMsg
     * @param mdseCd String
     * @return true: exist
     */
    public S21SsmEZDResult checkContractNum(NWAL1840CMsg bizMsg, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("contrNum", bizMsg.dsContrNum.getValue());
        params.put("contrSqNum", bizMsg.dsContrSqNum.getValue());
        params.put("mdseCd", mdseCd);
        params.put("slsDt", bizMsg.slsDt.getValue());
        S21SsmEZDResult result = getSsmEZDClient().queryObject("checkContractNum", params);

        return result;
    }

    /**
     * Get Child MDSE List
     * @param bizMsg NWAL1840CMsg
     * @param parentMdseCd Parent MDSE Code
     * @return Child MDSE List
     */
    public S21SsmEZDResult getChildMdseList(NWAL1840CMsg bizMsg, String parentMdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("prntMdseCd", parentMdseCd);

        return getSsmEZDClient().queryObjectList("getChildMdseList", params);
    }
}
