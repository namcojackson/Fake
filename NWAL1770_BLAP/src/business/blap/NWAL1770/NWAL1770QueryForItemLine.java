/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         T.Yoshida       Create          N/A
 * 2016/09/29   Fujitsu         H.Ikeda         Update          S21_NA#11655
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 * </pre>
 */
public final class NWAL1770QueryForItemLine extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1770QueryForItemLine MY_INSTANCE = new NWAL1770QueryForItemLine();

    /**
     * Constructor.
     */
    private NWAL1770QueryForItemLine() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1770Query
     */
    public static NWAL1770QueryForItemLine getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get Manufacturer Item Code
     * @param bizMsg NWAL1770CMsg
     * @param mdseCd Merchandise Code
     * @return Manufacturer Item Code
     */
    public S21SsmEZDResult getMnfItemCd(NWAL1770CMsg bizMsg, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("mdseCd", mdseCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getMnfItemCd", params);
    }

    /**
     * get Base MDSE Code From Manufacturer Item Code
     * @param bizMsg NWAL1770CMsg
     * @param mnfItemCd Manufacturer Item Code
     * @return Base MDSE Code
     */
    public S21SsmEZDResult getBaseMdseCdFromMnfItemCd(NWAL1770CMsg bizMsg, String mnfItemCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("mnfItemCd", mnfItemCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // Mod Start 2016/09/29 S21_NA#11655
        //return getSsmEZDClient().queryObject("getBaseMdseCdFromMnfItemCd", params);
        return getSsmEZDClient().queryObjectList("getBaseMdseCdFromMnfItemCd", params);
        // Mod End   2016/09/29 S21_NA#11655
    }

    /**
     * get Package UOM Code
     * @param bizMsg NWAL1770CMsg
     * @param mdseCd Merchandise Code
     * @return Package UOM Code
     */
    public S21SsmEZDResult getPkgUomCd(NWAL1770CMsg bizMsg, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("mdseCd", mdseCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getPkgUomCd", params);
    }

    /**
     * check Exist Warehouse With DS Order Type
     * @param bizMsg NWAL1770CMsg
     * @param lineitemLineMsgMsg NWAL1770_BCMsg
     * @return true: exist
     */
    public boolean isExistWhWithDsOrdTp(NWAL1770CMsg bizMsg, NWAL1770_BCMsg lineitemLineMsgMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("rgtnStsReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("rtlWhNm", lineitemLineMsgMsg.rtlWhNm_B.getValue());
        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistWhWithDsOrdTp", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * check Exist Warehouse Without DS Order Type
     * @param bizMsg NWAL1770CMsg
     * @param itemLineMsg NWAL1770_BCMsg
     * @return true: exist
     */
    public boolean isExistWhWithOutDsOrdTp(NWAL1770CMsg bizMsg, NWAL1770_BCMsg itemLineMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("rgtnStsReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("rtlWhNm", itemLineMsg.rtlWhNm_B.getValue());
        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistWhWithOutDsOrdTp", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * Get Child MDSE List
     * @param bizMsg NWAL1770CMsg
     * @param parentMdseCd Parent MDSE Code
     * @return Child MDSE List
     */
    public S21SsmEZDResult getChildMdseList(NWAL1770CMsg bizMsg, String parentMdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("prntMdseCd", parentMdseCd);

        return getSsmEZDClient().queryObjectList("getChildMdseList", params);
    }

    // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
    public S21SsmEZDResult getHazMat(NWAL1770CMsg bizMsg, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getHazMat", params);
    }
    // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
}
