/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL1120;

import java.util.HashMap;
import java.util.Map;

import business.blap.NFBL1120.NFBL1120CMsg;
import business.blap.NFBL1120.NFBL1120Query;
import business.blap.NFBL1120.NFBL1120SMsg;
import business.blap.NFBL1120.constant.NFBL1120Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Invoice Maintenance Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/13   Hitachi         K.Kojima        Update          QC#12725
 * </pre>
 */
public final class NFBL1120Query extends S21SsmEZDQuerySupport implements NFBL1120Constant {
    /**
     * Singleton instance.
     */
    private static final NFBL1120Query INSTANCE = new NFBL1120Query();
    /**
     * User Profile
     */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
    /**
     * Global Company Code.
     */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();
    /**
     * Constructor.
     */
    private NFBL1120Query() {
        super();
    }
    /**
     * Singleton instance getter.
     * @return NFBL1120Query
     */
    public static NFBL1120Query getInstance() {
        return INSTANCE;
    }

    /**
     * NFBL1120Query.xml id="checkLocNm"
     * @param bizMsg NFBL1120CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkLocNm(NFBL1120CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("locNm", bizMsg.locNm.getValue());

        return getSsmEZDClient().queryObjectList("checkLocNm", ssmParam, -1, -1);
    }

    /**
     * NFBL1120Query.xml id="checkPrntVnd"
     * @param bizMsg NFBL1120CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkPrntVnd(NFBL1120CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("prntVndCd", bizMsg.prntVndCd.getValue());

        return getSsmEZDClient().queryObjectList("checkPrntVnd", ssmParam, -1, -1);
    }

    /**
     * NFBL1120Query.xml id="searchRecord"
     * @param bizMsg NFBL1120CMsg
     * @param globalMsg NFBL1120SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchRecord(NFBL1120CMsg bizMsg, NFBL1120SMsg globalMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("apBatNum", bizMsg.apBatNum.getValue());
        ssmParam.put("prntVndCd", bizMsg.prntVndCd.getValue());
        ssmParam.put("apBatDt", bizMsg.apBatDt.getValue());
        ssmParam.put("locNm", bizMsg.locNm.getValue());
        ssmParam.put("apMaintInvStsCd", bizMsg.apMaintInvStsCd_S.getValue());
        // START 2016/09/13 K.Kojima [QC#12725,MOD]
        // ssmParam.put("apvrUsrId", bizMsg.apvrUsrId.getValue());
        ssmParam.put("apvrUsrId", bizMsg.varCharConstNm_S.getValue());
        // END 2016/09/13 K.Kojima [QC#12725,MOD]
        ssmParam.put("apInvNum", bizMsg.apInvNum.getValue());
        ssmParam.put("invDt", bizMsg.invDt.getValue());

        return getSsmEZDClient().queryObjectList("searchRecord", ssmParam, -1, -1);
    }

    /**
     * NFBL1120Query.xml id="getPrntVndNm"
     * @param bizMsg NFBL1120CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrntVndNm(NFBL1120CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("prntVndCd", bizMsg.prntVndCd.getValue());

        return getSsmEZDClient().queryObjectList("getPrntVndNm", ssmParam, -1, -1);
    }

    // START 2016/09/13 K.Kojima [QC#12725,DEL]
    // /**
    // * NFBL1120Query.xml id="getApvrUsrNm"
    // * @param bizMsg NFBL1120CMsg
    // * @return S21SsmEZDResult
    // */
    // public S21SsmEZDResult getApvrUsrNm(NFBL1120CMsg bizMsg) {
    //
    // Map<String, Object> ssmParam = new HashMap<String, Object>();
    // ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
    // ssmParam.put("apvrUsrId", bizMsg.apvrUsrId.getValue());
    //
    // return getSsmEZDClient().queryObjectList("getApvrUsrNm",
    // ssmParam, -1, -1);
    // }
    // END 2016/09/13 K.Kojima [QC#12725,DEL]

    /**
     * NFBL1120Query.xml id="getInvoiceStatusPulldownValue"
     * 
     * <pre>
     * Get records for [Invoice Status] pulldown.
     * </pre>
     * 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvoiceStatusPulldownValue() {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        return getSsmEZDClient().queryObjectList("getInvoiceStatusPulldownValue", queryParam, -1, -1);

    }

    /**
     * NFBL1120Query.xml id="getLocNm"
     * 
     * <pre>
     * Get Supplier Name.
     * </pre>
     * 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocNm(String vndCd) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("vndCd", vndCd);
        return getSsmEZDClient().queryObjectList("getLocNm", queryParam, -1, -1);

    }
}
