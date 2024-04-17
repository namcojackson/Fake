/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2660;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import static business.blap.NMAL2660.constant.NMAL2660Constant.GLOBAL_CMPY_CODE;
import static business.blap.NMAL2660.constant.NMAL2660Constant.ORGANIZATION_CODE;
import static business.blap.NMAL2660.constant.NMAL2660Constant.ROWNUM;
import static business.blap.NMAL2660.constant.NMAL2660Constant.SELECT_MAX_ROW_SIZE;
import static business.blap.NMAL2660.constant.NMAL2660Constant.STS_CD;
import static business.blap.NMAL2660.constant.NMAL2660Constant.RGTN_STS_CD;
import static business.blap.NMAL2660.constant.NMAL2660Constant.LIST_SIZE_2;
import static business.blap.NMAL2660.constant.NMAL2660Constant.GNRN_TP_CD_LIST;
import static business.blap.NMAL2660.constant.NMAL2660Constant.GNRN_TP_CD_2;
import static business.blap.NMAL2660.constant.NMAL2660Constant.GNRN_TP_CD_3;


/**
 *<pre>
 * NMAL2660Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public final class NMAL2660Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL2660Query MY_INSTANCE = new NMAL2660Query();

    /**
     * Private constructor
     */
    private NMAL2660Query() {
        super();
    }

    /**
     * Get the NMAL2660Query instance.
     * @return NMAL2660Query instance
     */
    public static NMAL2660Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Condition Group
     * @param bizMsg NMAL2660CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getResourceAssignList(NMAL2660CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        params.put(ORGANIZATION_CODE, bizMsg.orgCd);
        ArrayList<String> gnrnList = new ArrayList<String>(LIST_SIZE_2);
        gnrnList.add(GNRN_TP_CD_2);
        gnrnList.add(GNRN_TP_CD_3);
        params.put(GNRN_TP_CD_LIST, gnrnList);
        params.put(STS_CD, RGTN_STS_CD);
        params.put(ROWNUM, SELECT_MAX_ROW_SIZE);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryEZDMsgArray("getResourceAssignList", params, bizMsg.A);
        return ssmResult;
    }

}
