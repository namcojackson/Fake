/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLGL0050;

import java.util.Map;

import business.blap.NLGL0050.constant.NLGL0050Constant;

import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Ship Via Mapping from WMS to HOST
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/16   CSAI            Y.Miyauchi      Create          MW Replace Initial
 *</pre>
 */
public final class NLGL0050Query extends S21SsmEZDQuerySupport implements NLGL0050Constant {

    /**
     * Singleton instance.
     */
    private static final NLGL0050Query MY_INSTANCE = new NLGL0050Query();

    /**
     * Constructor.
     */
    private NLGL0050Query() {

        super();
    }

    /**
     * Singleton instance getter.
     * @return NLGL0050Query
     */
    public static NLGL0050Query getInstance() {

        return MY_INSTANCE;
    }

    /**
     * The method explanation: This method get data of CarrCodeList by wmsOrgHostId, wmsTrnspTpCd, CarrScacCd from WMS_SHIP_VIA.
     * execute SSM id="getCarrCodeList" in [NLGL0050Query.xml]
     * @param cMsg ALGL050CMsg
     * @param ssmParam Map<String, Object>
     * @param sMsg NLGL0050SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCarrCodeList(NLGL0050CMsg cMsg, Map<String, Object> ssmParam, NLGL0050SMsg sMsg) {

        if (PULL_DOWN_ALL.equals(cMsg.wmsTrnspTpCd_P1.getValue())) {
            cMsg.wmsTrnspTpCd_P1.setValue(DB_FIELD_BLANK);
        }
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());

        ssmParam.put("cMsg", cMsg);

        return getSsmEZDClient().queryEZDMsgArray("getCarrCodeList", ssmParam, sMsg.A);
    }

    /**
    * The method explanation: This method get data of HostSystemList from WMS_SHIP_VIA.
     * execute SSM id="getCarrCodeList" in [NLGL0050Query.xml]
     * @param cMsg ALGL050CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHostSystemList(NLGL0050CMsg cMsg) {

        return getSsmEZDClient().queryObjectList("getHostSystemList", cMsg);
    }

    /**
     * The method explanation: This method get data of TransportModeList from WMS_TRNSP_TP.
     * execute SSM id="getCarrCodeList" in [NLGL0050Query.xml]
     * @param cMsg ALGL050CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTransportModeList(NLGL0050CMsg cMsg) {

        return getSsmEZDClient().queryObjectList("getTransportModeList", cMsg);
    }

    /**
     * The method explanation: This method get data of CarrCd by PrimaryKey from WMS_SHIP_VIA.
     * execute SSM id="getCarrCodeList" in [NLGL0050Query.xml]
     * @param cMsg ALGL050CMsg
     * @param ssmParam Map<String, Object>
     * @param sMsg NLGL0050SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCarrCdEdit(NLGL0050CMsg cMsg, Map<String, Object> ssmParam, NLGL0050SMsg sMsg) {

        ssmParam.put("cMsg", cMsg);

        return getSsmEZDClient().queryEZDMsg("getCarrCdEdit", ssmParam, sMsg);
    }

}
