package business.blap.NWAL1630;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/22   Fujitsu         T.Ogura         Create          S21_NA#18859(Sol#154)
 * 2019/01/23   Fujitsu         K.Ishizuka      Update          S21_NA#29446
 *</pre>
 */
public final class NWAL1630Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1630Query MY_INSTANCE = new NWAL1630Query();
    
    /**
     * Constructor.
     */
    private NWAL1630Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1630Query
     */
    public static NWAL1630Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get Service Machine Master Primary Key
     * @param bizMsg NWAL1630CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMachMstrPk(NWAL1630CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("serNum", bizMsg.serNum.getValue());
        ssmParam.put("dsContrNum", bizMsg.dsContrNum.getValue());
        ssmParam.put("machine", SVC_MACH_TP.MACHINE);
        return getSsmEZDClient().queryObject("getSvcMachMstrPk", ssmParam);
    }
    
    // 2019/01/22 S21_NA#29446 Add Start
    public S21SsmEZDResult getContrCapInfo(NWAL1630CMsg bizMsg, List<String> svcTermCondAttrbNmList) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        String glblCmpyCd = getGlobalCompanyCode();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrNum", bizMsg.dsContrNum);
        ssmParam.put("svcMachMstrPk", bizMsg.svcMachMstrPk);
        ssmParam.put("svcTermCondAttrbNmList", svcTermCondAttrbNmList);
        return getSsmEZDClient().queryObjectList("getContrCapInfo", ssmParam);
    }
    
    public S21SsmEZDResult getMachInfo(NWAL1630CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("serNum", bizMsg.serNum.getValue());
        ssmParam.put("dsContrNum", bizMsg.dsContrNum.getValue());
        ssmParam.put("machine", SVC_MACH_TP.MACHINE);
        return getSsmEZDClient().queryObjectList("getMachInfo", ssmParam);
    }
    // 2019/01/22 S21_NA#29446 Add End
}
