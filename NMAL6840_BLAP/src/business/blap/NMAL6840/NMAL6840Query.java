/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6840;

import static business.blap.NMAL6840.constant.NMAL6840Constant.SQL_PARAM_GLBL_CMPY_CD;
import static business.blap.NMAL6840.constant.NMAL6840Constant.SQL_PARAM_RTL_SWH_CD;
import static business.blap.NMAL6840.constant.NMAL6840Constant.SQL_PARAM_RTL_WH_CATG_CD;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCStringItem;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <p>
 * NMAL6840 Sub Warehouse Setup.
 * </p>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/27/2015   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC# 2380
 * </pre>
 */
public final class NMAL6840Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6840Query INSTANCE = new NMAL6840Query();

    /**
     * Constructor.
     */
    private NMAL6840Query() {
        super();
    }

    /**
     * Gets the singleton instance.
     * @return the instance of NMAL6840Query.
     */
    public static NMAL6840Query getInstance() {
        return INSTANCE;
    }

    /**
     * <p>
     * Searches WH Category and Sub WH Relations.
     * </p>
     * @param cMsg CMsg
     * @param sMsg SMsg
     * @return search results
     */
    public S21SsmEZDResult searchSubWarehouseRelations(NMAL6840CMsg cMsg, NMAL6840SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(SQL_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd_C1);
        params.put(SQL_PARAM_RTL_WH_CATG_CD, cMsg.rtlWhCatgCd_H1);

        return getSsmEZDClient().queryEZDMsgArray("searchSubWarehouseRelations", params, sMsg.A);
    }

    /**
     * <p>
     * Searches the count of Sub WH.
     * </p>
     * @param cMsg CMsg
     * @param subWhCd Sub WH Code
     * @return search result
     */
    public S21SsmEZDResult searchSubWarehouseRecordCount(NMAL6840CMsg cMsg, EZDCStringItem subWhCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(SQL_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd_C1);
        params.put(SQL_PARAM_RTL_WH_CATG_CD, cMsg.rtlWhCatgCd_H1);
        params.put(SQL_PARAM_RTL_SWH_CD, subWhCd);

        return getSsmEZDClient().queryObject("searchSubWarehouseRecordCount", params);
    }

    /**
     * <p>
     * Searches WH Category and Sub WH.
     * </p>
     * @param cMsg CMsg
     * @param sMsg SMsg
     * @return search results
     */
    public S21SsmEZDResult searchSubWarehouse(NMAL6840CMsg cMsg, NMAL6840SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(SQL_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd_C1);

        return getSsmEZDClient().queryObjectList("searchSubWarehouse", params);
    }
}
