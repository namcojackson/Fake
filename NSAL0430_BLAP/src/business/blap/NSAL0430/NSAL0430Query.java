/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0430;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   CUSA            SRAA            Create          N/A
 * 2018/12/28   Hitachi         S.Kitamura      Update          QC#29718
 *</pre>
 */
public class NSAL0430Query extends S21SsmEZDQuerySupport {

    private static final NSAL0430Query INSTANCE = new NSAL0430Query();

    private NSAL0430Query() {
    }

    public static NSAL0430Query getInstance() {
        return INSTANCE;
    }

    // START 2018/12/28 S.Kitamura [QC#29718,DEL]
    //    private List<String> getPhysMtrLbCdList(String glblCmpyCd) {
    //        Map<String, Object> ssmPrm = new HashMap<String, Object>();
    //        ssmPrm.put("glblCmpyCd", glblCmpyCd);
    //        ssmPrm.put("mtrLbTpCd", MTR_LB_TP.REGULAR_METER);
    //        S21SsmEZDResult rslt = getSsmEZDClient().queryObjectList("getAllPhysMtrLb", ssmPrm);
    //        List<String> list = new ArrayList<String>();
    //        if (rslt != null && rslt.isCodeNormal()) {
    //            int rsltCnt = rslt.getQueryResultCount();
    //            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
    //            for (int i = 0; i < rsltCnt; i++) {
    //                Map<String, Object> rsltMap = rsltList.get(i);
    //                list.add((String) rsltMap.get("MTR_LB_CD"));
    //            }
    //        }
    //        return list;
    //    }
    // END 2018/12/28 S.Kitamura [QC#29718,DEL]
    public S21SsmEZDResult getSvcMachMstrInfo(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        return getSsmEZDClient().queryObject("getSvcMachMstrInfo", ssmPrm);
    }

    public S21SsmEZDResult getDsMtrReadTpGrp(String glblCmpyCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getDsMtrReadTpGrp", ssmPrm);
    }

    public S21SsmEZDResult getPhysMtrLb(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        return getSsmEZDClient().queryObjectList("getPhysMtrLb", ssmPrm);
    }

    // START 2018/12/28 S.Kitamura [QC#29718,MOD]
    // public S21SsmEZDResult getSvcPhysMtrRead(String glblCmpyCd, BigDecimal svcMachMstrPk, int rowNum) {
    public S21SsmEZDResult getSvcPhysMtrRead(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        // ssmPrm.put("mtrLbCdList", getPhysMtrLbCdList(glblCmpyCd));
        // ssmPrm.put("rowNum", rowNum);
        return getSsmEZDClient().queryObjectList("getSvcPhysMtrRead", ssmPrm);
    }
    // END 2018/12/28 S.Kitamura [QC#29718,MOD]
}
