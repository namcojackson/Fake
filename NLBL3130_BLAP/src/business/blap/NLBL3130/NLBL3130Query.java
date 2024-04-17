/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */

package business.blap.NLBL3130;

import java.util.HashMap;
import java.util.Map;

import business.blap.NLBL3130.constant.NLBL3130Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_ORD_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Delivery Instruction
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/18/2015   CITS            M.Ito           Create          N/A
 * 04/13/2016   CSAI            Y.Imazu         Update          QC#5868
 * 02/07/2017   CITS            T.Kikuhara      Update          QC#15586
 * 01/12/2018   CITS            T.Tokutomi      Update          QC#18460_Sol#087
 * 06/05/2018   CITS            K.Ogino         Update          QC#25233
 *</pre>
 */
public final class NLBL3130Query extends S21SsmEZDQuerySupport implements NLBL3130Constant {
    /**
     * Singleton instance.
     */
    private static final NLBL3130Query INSTANCE = new NLBL3130Query();

    /**
     * Constructor.
     */
    private NLBL3130Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWBL1010Query
     */
    public static NLBL3130Query getInstance() {
        return INSTANCE;
    }

    /**
     * <pre>
     * execute SSM id="getDelyInstnInfo" in [NLBL3130Query.xml]
     * </pre>
     * @param cMsg NLBL3130CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDelyInstnInfo(NLBL3130CMsg cMsg, String ssmId) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);

        return getSsmEZDClient().queryObjectList(ssmId, ssmParam);
    }

    /**
     * getShpgInstnCmntTxt
     * @param cMsg NLBL3130CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShpgInstnCmntTxt(NLBL3130CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("soMsgTpCd", SHPG_ORD_MSG_TP.CPO_INFORMATION);

        return getSsmEZDClient().queryObjectList("getShpgInstnCmntTxt", ssmParam);
    }

    // QC#15586 add start
    /**
     * getMaxSqNumBySoMsg
     * @param cMsg NLBL3130CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxSqNumBySoMsg(NLBL3130CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("soMsgTpCd", SHPG_ORD_MSG_TP.CPO_INFORMATION);

        return getSsmEZDClient().queryObject("getMaxSqNumBySoMsg", ssmParam);
    }
    // QC#15586 add end

    /**
     * getMaxSqNumBySoMsg
     * QC#18460_Sol#087 Add method.
     * @param cMsg NLBL3130CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToPost(NLBL3130CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("soCustDataTpShipTo", SO_CUST_DATA_TP.SHIP_TO);

        return getSsmEZDClient().queryObject("getShipToPost", ssmParam);
    }

    /**
     * Add QC#25233 getMaxSqNumByRwsMsg
     * @param cMsg NLBL3130CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxSqNumByRwsMsg(NLBL3130CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);

        return getSsmEZDClient().queryObject("getMaxSqNumByRwsMsg", ssmParam);
    }
}
