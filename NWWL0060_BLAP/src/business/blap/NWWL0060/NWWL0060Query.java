/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0060;

import static business.blap.NWWL0060.constant.NWWL0060Constant.MAX_DT;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWWL0060Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/05   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public final class NWWL0060Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWWL0060Query MY_INSTANCE = new NWWL0060Query();

    /**
     * Private constructor
     */
    private NWWL0060Query() {
        super();
    }

    /**
     * Get the NWWL0060Query instance.
     * @return NWWL0060Query instance
     */
    public static NWWL0060Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getNtfySbscr
     * @param bizMsg NWWL0060CMsg
     * @param glblMsg NWWL0060SMsg
     * @param tblNm String
     * @param psnColNm String
     * @param sql String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNtfySbscr(NWWL0060CMsg bizMsg, NWWL0060SMsg glblMsg, String tblNm, String psnColNm, String sql) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("psnCd", bizMsg.psnCd.getValue());
        params.put("tblNm", tblNm);
        params.put("psnColNm", psnColNm);
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("sqlTxt", sql);
        params.put("maxDt", MAX_DT);
        

        return getSsmEZDClient().queryObjectList("getNtfySbscr", params);
    }

}
