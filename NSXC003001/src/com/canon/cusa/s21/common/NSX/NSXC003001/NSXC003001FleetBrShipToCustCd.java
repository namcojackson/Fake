package com.canon.cusa.s21.common.NSX.NSXC003001;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Hitachi        K.Kishimoto      Create          N/A
 *</pre>
 */
public class NSXC003001FleetBrShipToCustCd {

    /**
     * S21SsmBatchClient object.
     */
    private static final S21SsmBatchClient SSM_CLNT = S21SsmBatchClient.getClient(NSXC003001FleetBrShipToCustCd.class);

    /**
     * Get fleet branch ship to customer code
     * @param bean Fleet branch ship to customer code information bean
     * @return Fleet branch ship to customer code
     */
    public static FleetBrShipToCustCdInfoBean getFleetBrShipToCustCd(FleetBrShipToCustCdInfoBean bean) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", bean.getGlblCmpyCd());
        prm.put("billToCustCd", bean.getBillToCustCd());
        @SuppressWarnings("unchecked")
        Map<String, Object> rsltMap = (Map<String, Object>) SSM_CLNT.queryObject("getFleetBrShipToCustCd", prm);
        if (rsltMap == null || rsltMap.isEmpty()) {
            return null;
        }
        bean.setDsDefShipToCd((String) rsltMap.get("DS_DEF_SHIP_TO_CD"));
        bean.setSvcBrCd((String) rsltMap.get("SVC_BR_CD"));
        return bean;
    }
}
