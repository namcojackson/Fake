/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.blap.NLBL3060;

import java.util.HashMap;
import java.util.Map;

import business.blap.NLBL3060.NLBL3060CMsg;
import business.blap.NLBL3060.NLBL3060SMsg;
import business.blap.NLBL3060.NLBL3060Query;
import business.blap.NPAL1500.NPAL1500CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   CSAI            K.Lee           Create          
 * 2023/10/18   Hitachi         Y.Ogura         Update          QC#61793
 *</pre>
 */
public final class NLBL3060Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLBL3060Query myInstance = new NLBL3060Query();

    /**
     * Constructor.
     */
    private NLBL3060Query() {
        super();
    }

    public static NLBL3060Query getInstance() {
        return myInstance;
    }

    public S21SsmEZDResult getMasterTable(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg, Map prmMap) {
        return getSsmEZDClient().queryEZDMsgArray("getMasterTable", prmMap, globalMsg.A);
    }

    public S21SsmEZDResult getPersonNm(Map prmMap) {
        return getSsmEZDClient().queryObject("getPersonNm", prmMap);
    }

    public S21SsmEZDResult countDuplicate(Map prmMap) {
        return getSsmEZDClient().queryObject("countDuplicate", prmMap);
    }

    // START 2023/10/18 Y.Ogura [QC#61793, ADD]
    public S21SsmEZDResult getGroupName(Map prmMap) {
        return getSsmEZDClient().queryObject("getGroupName", prmMap);
    }
    
    public S21SsmEZDResult countDuplicateForUpdate(Map prmMap) {
        return getSsmEZDClient().queryObject("countDuplicateForUpdate", prmMap);
    }
    
    public S21SsmEZDResult getWhTypeList(NLBL3060CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        return getSsmEZDClient().queryObjectList("getWhTypeList", params);
    }
    public S21SsmEZDResult getSchdCoordWhPmsnPk(Map prmMap) {
        return getSsmEZDClient().queryObject("getSchdCoordWhPmsnPk", prmMap);
    }
    public S21SsmEZDResult getRtlWhCatgCdList(String glblCmpyCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getRtlWhCatgCdList", params);
    }
    // END 2023/10/18 Y.Ogura [QC#61793, ADD]
}
