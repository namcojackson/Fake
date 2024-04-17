/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */

package business.blap.NLBL3100;

import java.util.HashMap;
import java.util.Map;

import business.blap.NLBL3100.constant.NLBL3100Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Reman Labor/Expense Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/13/2015   CITS            M.Ito           Create          N/A
 * 03/05/2019   Hitachi         Y.Takeno        Update          QC#30642
 *</pre>
 */
public final class NLBL3100Query extends S21SsmEZDQuerySupport implements NLBL3100Constant {
    /**
     * Singleton instance.
     */
    private static final NLBL3100Query INSTANCE = new NLBL3100Query();

    /**
     * Constructor.
     */
    private NLBL3100Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWBL1010Query
     */
    public static NLBL3100Query getInstance() {
        return INSTANCE;
    }

    /**
     * <pre>
     * execute SSM id="getCoordinatorInfo" in [NLBL3100Query.xml]
     * </pre>
     * @param cMsg NLBL3100CMsg
     * @param sMsg NLBL3100SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCoordinatorInfo(NLBL3100CMsg cMsg, NLBL3100SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_MRP_SCHD_COORD_PSN_CD_LIKE, DB_PARAM_DATA_LIKE.concat(cMsg.schdCoordPsnCd_H1.getValue().concat(DB_PARAM_DATA_LIKE)));
        ssmParam.put(DB_PARAM_MRP_RTL_WH_CD_LIKE, DB_PARAM_DATA_LIKE.concat(cMsg.rtlWhCd_H1.getValue().concat(DB_PARAM_DATA_LIKE)));
        ssmParam.put(DB_PARAM_MRP_MGR_PSN_CD_LIKE, DB_PARAM_DATA_LIKE.concat(cMsg.mgrPsnCd_H1.getValue().concat(DB_PARAM_DATA_LIKE)));
        ssmParam.put(DB_PARAM_MRP_SUPV_PSN_CD_LIKE, DB_PARAM_DATA_LIKE.concat(cMsg.supvPsnCd_H1.getValue().concat(DB_PARAM_DATA_LIKE)));
        ssmParam.put(DB_PARAM_MRP_CARR_CD_LIKE, DB_PARAM_DATA_LIKE.concat(cMsg.carrCd_H1.getValue().concat(DB_PARAM_DATA_LIKE)));
        ssmParam.put(DB_PARAM_MRP_CARR_NM_LIKE, DB_PARAM_DATA_LIKE.concat(cMsg.xxPsnNm_H4.getValue().concat(DB_PARAM_DATA_LIKE)));
        ssmParam.put(DB_PARAM_MRP_SCHD_COORD_PSN_NM_LIKE, DB_PARAM_DATA_LIKE.concat(cMsg.xxPsnNm_H1.getValue().concat(DB_PARAM_DATA_LIKE)));
        ssmParam.put(DB_PARAM_MRP_MGR_PSN_NM_LIKE, DB_PARAM_DATA_LIKE.concat(cMsg.xxPsnNm_H2.getValue().concat(DB_PARAM_DATA_LIKE)));
        ssmParam.put(DB_PARAM_MRP_SUPV_PSN_NM_LIKE, DB_PARAM_DATA_LIKE.concat(cMsg.xxPsnNm_H3.getValue().concat(DB_PARAM_DATA_LIKE)));
        ssmParam.put(DB_PARAM_ST_CD_LIKE, DB_PARAM_DATA_LIKE.concat(cMsg.stCd_H1.getValue().concat(DB_PARAM_DATA_LIKE)));
        // START 2019/03/05 [QC#30642, ADD]
        ssmParam.put(DB_PARAM_MRP_RTL_WH_NM_LIKE, DB_PARAM_DATA_LIKE.concat(cMsg.rtlWhNm_H1.getValue().concat(DB_PARAM_DATA_LIKE)));
        // END   2019/03/05 [QC#30642, ADD]

        return getSsmEZDClient().queryEZDMsgArray("getCoordinatorInfo", ssmParam, 0, sMsg.A.length(), sMsg.A);
    }

}
