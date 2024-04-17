/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0240;

import static business.blap.NMAL0240.constant.NMAL0240Constant.ACTIVE;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CUSA_CMPSN;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CUSA_GLBL_CMPY_CD;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CUSA_MDSE;
import static business.blap.NMAL0240.constant.NMAL0240Constant.FETCH_SIZE_MAX;
import static business.blap.NMAL0240.constant.NMAL0240Constant.INACTIVE;
import static business.blap.NMAL0240.constant.NMAL0240Constant.UPDATE;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSA_ALL_MDSE_V;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL0240Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Fujitsu         C.Tanaka         Create          CSA
 * 2015/11/24   Fujitsu         S.Yoshida        Update          QC#809
 * 2016/03/02   SRAA            K.Aratani        Update          QC#5003
 * 2016/05/09   SRAA            K.Aratani        Update          QC#8093
 * 2016/06/16   SRAA            K.Aratani       Update          QC#10313
 *</pre>
 */
public final class NMAL0240Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL0240Query MY_INSTANCE = new NMAL0240Query();

    /**
     * Private constructor
     */
    private NMAL0240Query() {
        super();
    }

    /**
     * Get the NMAL0240Query instance.
     * @return NMAL0240Query instance
     */
    public static NMAL0240Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Header Info
     * @param bizMsg NMAL0240CMsg
     * @param glblMsg NMAL0240SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeader(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", bizMsg.mdseCd.getValue());
        return getSsmEZDClient().queryEZDMsg("getHeader", params, glblMsg);
    }

    /**
     * Get CUSA Header Info
     * @param bizMsg NMAL0240CMsg
     * @param glblMsg NMAL0240SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCusaHeader(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        String glblCmpyCd = getGlobalCompanyCode();

        params.put("cusaGlblCmpyCd", CUSA_GLBL_CMPY_CD);
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", bizMsg.mdseCd.getValue());
        params.put("cusaMdse", ZYPCodeDataUtil.getVarCharConstValue(CUSA_MDSE, glblCmpyCd));
        params.put("cusaCmpsn", ZYPCodeDataUtil.getVarCharConstValue(CUSA_CMPSN, glblCmpyCd));
        params.put("mdseTpCd", MDSE_TP.SET);
        return getSsmEZDClient().queryEZDMsg("getCusaHeader", params, glblMsg);
    }

    /**
     * Get all Revision Info
     * @param bizMsg NMAL0240CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRevision(NMAL0240CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prntMdseCd", bizMsg.mdseCd.getValue());
        params.put("active", ACTIVE);
        params.put("inActive", INACTIVE);
        params.put("rowNum", bizMsg.A.length() + bizMsg.B.length());
        // ADD Start 2015/11/24 QC#861
        params.put("histActNm_Upd", UPDATE);
        // ADD End 2015/11/24 QC#861
        return getSsmEZDClient().queryObjectList("getRevision", params);
    }

    /**
     * Get all Revision Info for CSV download
     * @param bizMsg NMAL0240CMsg
     * @param rsSupport S21SsmBooleanResultSetHandlerSupport
     * @return S21SsmEZDResult
     */
    public Boolean getRevisionForCsv(NMAL0240CMsg bizMsg, S21SsmBooleanResultSetHandlerSupport rsSupport) {
        Map<String, Object> params = new HashMap<String, Object>();
        S21SsmExecutionParameter ssmExecParam = new S21SsmExecutionParameter();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prntMdseCd", bizMsg.mdseCd.getValue());
        params.put("active", ACTIVE);
        params.put("inActive", INACTIVE);
        params.put("histActNm_Upd", UPDATE);

        ssmExecParam.setFetchSize(FETCH_SIZE_MAX);
        ssmExecParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmExecParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmExecParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        Boolean result = (Boolean) ssmBatchClient.queryObject("getRevisionForCsv", params, ssmExecParam, rsSupport);

        return result;
    }

    /**
     * Get Child
     * @param bizMsg NMAL0240CMsg
     * @param childMdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getChildren(NMAL0240CMsg bizMsg, String childMdseCd, String slsDt) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("childMdseCd", childMdseCd);
        params.put("active", ACTIVE);
        params.put("inActive", INACTIVE);
        params.put("slsDt", slsDt);
        params.put("rowNum", bizMsg.A.length() + bizMsg.B.length());
        return getSsmEZDClient().queryObjectList("getChildren", params);
    }

    /**
     * Get Component From CUSA Table
     * @param bizMsg NMAL0240CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCusaRevision(NMAL0240CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        String glblCmpyCd = getGlobalCompanyCode();

        //MOD QC5003 Start
        params.put("cusaGlblCmpyCd", CUSA_GLBL_CMPY_CD);
        params.put("glblCmpyCd", glblCmpyCd);
        //MOD QC5003 End
        params.put("prntMdseCd", bizMsg.mdseCd.getValue());
        //QC#8093
        //params.put("cusaMdse", ZYPCodeDataUtil.getVarCharConstValue(CUSA_MDSE, glblCmpyCd));
        params.put("cusaMdse", ZYPCodeDataUtil.getVarCharConstValue(CSA_ALL_MDSE_V, glblCmpyCd));
        params.put("cusaCmpsn", ZYPCodeDataUtil.getVarCharConstValue(CUSA_CMPSN, glblCmpyCd));
        params.put("rowNum", bizMsg.A.length() + bizMsg.B.length());
        return getSsmEZDClient().queryObjectList("getCusaRevision", params);
    }

    /**
     * Get MDSE
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdse(String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObject("getMdse", params);
    }

    /**
     * Find Order Take MDSE
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findOrdTakeMdse(String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObject("findOrdTakeMdse", params);
    }
    
    /**
     * Find Composition
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findCmpsn(String prntMdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prntMdseCd", prntMdseCd);
        return getSsmEZDClient().queryObject("findCmpsn", params);
    }
    
    /**
     * Find MDSE
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findMdse(String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObject("findMdse", params);
    }

    /**
     * Check Hierarchy
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkHierarchy(String mdseCd, String parentMdseCd, String slsDate) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", mdseCd);
        params.put("parentMdseCd", parentMdseCd);
        params.put("slsDt", slsDate);
        
        return getSsmEZDClient().queryObject("checkHierarchy", params);
    }

    /**
     * Find MDSE Item Status
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findMdseItemSts(String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObject("findMdseItemSts", params);
    }
    public S21SsmEZDResult getCusaMdseRgtnSts(String glblCmpyCd, String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        String cusaMdseTableNm = ZYPCodeDataUtil.getVarCharConstValue(CUSA_MDSE, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cusaMdseTableNm)) {
        	return null;
        }
        params.put("cusaGlblCmpyCd", CUSA_GLBL_CMPY_CD);
        params.put("mdseCd", mdseCd);
        params.put("cusaMdse", cusaMdseTableNm);
        return getSsmEZDClient().queryObject("getCusaMdseRgtnSts", params);
    }
    
    //QC#10313
    public S21SsmEZDResult getCmpsn(String glblCmpyCd, String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cusaGlblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObject("getCmpsn", params);
    }

    /**
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param rowNum rowNum
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoMessageList(String glblCmpyCd, String mdseCd, int rowNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prntMdseCd", mdseCd);
        params.put("rowNum", rowNum);
        return getSsmEZDClient().queryObjectList("getBomMessageList", params);
    }
}
