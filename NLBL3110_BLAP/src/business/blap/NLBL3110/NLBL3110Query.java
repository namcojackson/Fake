/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3110;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 *</pre>
 */
public final class NLBL3110Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLBL3110Query myInstance = new NLBL3110Query();

    /**
     * Constructor.
     */
    private NLBL3110Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL3110Query
     */
    public static NLBL3110Query getInstance() {
        return myInstance;
    }

    /**
     * search
     * @param cMsg NLBL3110CMsg
     * @param sMsg NLBL3110SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NLBL3110CMsg cMsg, NLBL3110SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("soCustDataTpCc", SO_CUST_DATA_TP.SHIP_TO);
        params.put("custLocTpCd", LOC_TP.CUSTOMER);
        params.put("cMsg", cMsg);
        params.put("rowNum", sMsg.A.length());

        if (INBD_OTBD.INBOUND.equals(cMsg.inbdOtbdCd.getValue())) {

            return getSsmEZDClient().queryEZDMsgArray("searchRws", params, sMsg.A);

        } else {

            return getSsmEZDClient().queryEZDMsgArray("searchSo", params, sMsg.A);
        }
    }
}