/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770;

import static business.blap.NWAL1770.constant.NWAL1770Constant.DEF_VAL_ANY;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/07/20   Hitachi         T.Fukuta        Create          CSA-QC#61467
 * </pre>
 */
public class NWAL1770QueryForDefOrdCatgRsn extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1770QueryForDefOrdCatgRsn MY_INSTANCE = new NWAL1770QueryForDefOrdCatgRsn();

    /**
     * Constructor.
     */
    private NWAL1770QueryForDefOrdCatgRsn() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1770QueryForDefOrdCatgRsn
     */
    public static NWAL1770QueryForDefOrdCatgRsn getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Default Order Category Reason
     * @param bizMsg NWAL1770CMsg
     * @param usrId User ID
     * @param roleIdList Role ID List
     * @return Default Order Category Reason
     */
    public S21SsmEZDResult getDefOrdCatgRsn(NWAL1770CMsg bizMsg, String usrId, List<String> roleIdList) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("scrId", bizMsg.getBusinessID());

        params.put("usrId1", usrId);
        List<String> roleIdList1 = new ArrayList<String>();
        roleIdList1.addAll(roleIdList);
        roleIdList1.add(DEF_VAL_ANY);
        params.put("roleIdList1", roleIdList1);

        params.put("usrId2", DEF_VAL_ANY);
        params.put("roleIdList2", roleIdList);

        return getSsmEZDClient().queryObjectList("getDefOrdCatgRsn", params);
    }

    /**
     * Get Category Info
     * @param bizMsg
     * @param catgCd
     * @return
     */
    public S21SsmEZDResult getDsOrdCatg(NWAL1770CMsg bizMsg, String catgCd) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("scrId", bizMsg.getBusinessID());
        params.put("catgCd", catgCd);

        return getSsmEZDClient().queryObjectList("getDsOrdCatg", params);
    }
}
