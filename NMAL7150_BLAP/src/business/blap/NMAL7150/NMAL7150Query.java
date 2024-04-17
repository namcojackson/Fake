/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CSMP_PROC_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * CSMP Contract Synchronization  Errors Correction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public final class NMAL7150Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7150Query MY_INSTANCE = new NMAL7150Query();

    /**
     * Private constructor
     */
    private NMAL7150Query() {
        super();
    }

    /**
     * Get the NMAL7150Query instance.
     * @return NMAL7150Query instance
     */
    public static NMAL7150Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getSupplyAgreementPlan
     * @param bizMsg NMAL7150SMsg
     * @param glblMsg NMAL7150SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCsmpContrIntfc(NMAL7150CMsg bizMsg, NMAL7150SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dateFrom", bizMsg.effFromDt_TD.getValue());
        params.put("dateTo", bizMsg.effThruDt_TD.getValue());
        List<String> flagList = new ArrayList<String>(3);
        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_E)) {
            flagList.add(CSMP_PROC_STS.ERROR);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_R)) {
            flagList.add(CSMP_PROC_STS.REPROCESS);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_D)) {
            flagList.add(CSMP_PROC_STS.DELETED);
        }
        if (flagList != null) {
            String[] flagCd = (String[]) flagList.toArray(new String[0]);
            params.put("flagCd", flagCd);
        } else {
            params.put("flagCd", null);
        }
        params.put("rowNum", glblMsg.A.length() + 1);

        return getSsmEZDClient().queryObjectList("getCsmpContrIntfc", params);
    }

}
