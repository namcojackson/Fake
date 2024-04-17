/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2410;

import static business.blap.NWAL2410.constant.NWAL2410Constant.MAINTENANCE_BRANCH;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL2410Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/25   Fujitsu         N.Aoyama        Create          QC#16740
 *</pre>
 */
public final class NWAL2410Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL2410Query MY_INSTANCE = new NWAL2410Query();

    /**
     * Private constructor
     */
    private NWAL2410Query() {
        super();
    }

    /**
     * Get the NWAL2410Query instance.
     * @return NWAL2410Query instance
     */
    public static NWAL2410Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getThereforeBranchData
     * @param bizMsg NWAL2410CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getThereforeBranchData(NWAL2410CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("docMgtOrgCd", bizMsg.docMgtOrgCd.getValue());
        params.put("docMgtScanBrCd", bizMsg.docMgtScanBrCd.getValue());
        params.put("docMgtScanBrNm", bizMsg.docMgtScanBrNm.getValue());
        params.put("docMgtScanRgNm", bizMsg.docMgtScanRgNm.getValue());
        params.put("docMgtScanZnNm", bizMsg.docMgtScanZnNm.getValue());
        params.put("defOrdProcPsnCd", bizMsg.defOrdProcPsnCd.getValue());
        params.put("defBrAdminPsnCd", bizMsg.defBrAdminPsnCd.getValue());
        params.put("leaseCmpyProcPsnCd", bizMsg.leaseCmpyProcPsnCd.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.actvFlg.getValue())) {
            params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        }
        return getSsmEZDClient().queryObjectList("getThereforeBranchData", params);

    }

    /**
     * getSendThereforeData
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSendThereforeData() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getSendThereforeData", params);

    }

    /**
     * getdocMgtCatgNum
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getdocMgtCatgNum() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("srchKeyNm", MAINTENANCE_BRANCH);
        return getSsmEZDClient().queryObjectList("getdocMgtCatgNum", params);

    }

}
