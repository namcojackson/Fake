/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1180;

import java.util.HashMap;
import java.util.Map;

import static business.blap.NSAL1180.constant.NSAL1180Constant.*;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Hitachi         K.Yamada        Create          N/A
 *</pre>
 */
public final class NSAL1180Query extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NSAL1180Query MY_INSTANCE = new NSAL1180Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NSAL1180Query() {
        super();
    }

    /**
     * <pre>
     * Get the NSAL0110Query instance.
     * </pre>
     * @return NSAL0110Query instance
     */
    public static NSAL1180Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * findRgAndBr
     * @param cMsg NSAL1180CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findRgAndBr(NSAL1180CMsg cMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("svcRgPk", cMsg.svcRgPk.getValue());
        queryParam.put("slsDt", ZYPDateUtil.getSalesDate());
        queryParam.put("svcContrBrCd", cMsg.svcContrBrCd.getValue());
        queryParam.put("sysSrcCd", SYS_SRC.S21_SERVICE);

        return getSsmEZDClient().queryObjectList("findRgAndBr", queryParam);
    }

    /**
     * findResource
     * @param cMsg NSAL1180CMsg
     * @param svcContrBrCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findResource(NSAL1180CMsg cMsg, String svcContrBrCd) {
        String glblCmpyCd = getGlobalCompanyCode();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("slsDt", ZYPDateUtil.getSalesDate());
        queryParam.put("svcContrBrCd", svcContrBrCd);
        queryParam.put("firstOrgCd", ZYPCodeDataUtil.getVarCharConstValue(CONTR_BR_FIRST_ORG_CD, glblCmpyCd));
        queryParam.put("rowNum", SEARCH_LIMIT_CNT);

        return getSsmEZDClient().queryObjectList("findResource", queryParam);
    }

}

