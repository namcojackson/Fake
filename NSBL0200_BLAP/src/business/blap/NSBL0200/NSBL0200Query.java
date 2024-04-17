package business.blap.NSBL0200;

import static business.blap.NSBL0200.constant.NSBL0200Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 *
 * Technician Recommend Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/03   Hitachi         Y.Igarashi         Create          N/A
 * 2013/08/30   WDS Team        K.Aratani          Update          QC1457
 * 2015/11/25   Hitachi         T.Harada           Update          CSA
 *</pre>
 */
public final class NSBL0200Query extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NSBL0200Query MY_INSTANCE = new NSBL0200Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NSBL0200Query() {
        super();
    }

    /**
     * <pre>
     * Get the NSBL0200Query instance.
     * </pre>
     * @return NSBL0200Query instance
     */
    public static NSBL0200Query getInstance() {
        return MY_INSTANCE;
    }

// START 2015/11/25 [CSA,ADD]
    /**
     * 
     * getMachineInfo
     * 
     * @param svcMachMstrPk BigDecimal
     * @return Map<String, Object>
     */
    public Map<String, Object> getMachineInfo(BigDecimal svcMachMstrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        String gcc = getGlobalCompanyCode();
        paramMap.put("glblCmpyCd", gcc);
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        paramMap.put("slsDt", ZYPDateUtil.getSalesDate(gcc));
        paramMap.put("flgY", ZYPConstant.FLG_ON_Y);
        paramMap.put("thruDtLimit", THRU_DT_LIMIT);

        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getMachineInfo", paramMap);
        if (rs.isCodeNotFound()) {
            return null;
        }
        return (Map<String, Object>) rs.getResultObject();
   }

    /**
     * 
     * getSkillInfo
     * 
     * @param mdlNm String
     * @return Map<String, Object>
     */
    public Map<String, Object> getSkillInfo(String mdlNm) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        String gcc = getGlobalCompanyCode();
        paramMap.put("glblCmpyCd", gcc);
        paramMap.put("mdlNm", mdlNm);

        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getSkillInfo", paramMap);
        if (rs.isCodeNotFound()) {
            return null;
        }
        return (Map<String, Object>) rs.getResultObject();
   }

    /**
     * 
     * getTechList
     * 
     * @param queryParam Search Condition
     * @param sMsg NSBL0200SMsg
     * @return Search Result
     */
    public S21SsmEZDResult getTechList(Map<String, Object> queryParam, NSBL0200SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getTechList", queryParam, sMsg.A);
    }
// END 2015/11/25 [CSA,ADD]
}
