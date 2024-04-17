/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0230;

import static business.blap.NMAL0230.constant.NMAL0230Constant.ACTIVE;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CUSA_GLBL_CMPY_CD;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CUSA_MDSE;
import static business.blap.NMAL0230.constant.NMAL0230Constant.FETCH_SIZE_MAX;
import static business.blap.NMAL0230.constant.NMAL0230Constant.INACTIVE;
import static business.blap.NMAL0230.constant.NMAL0230Constant.UPDATE;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL0230Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/27   Fujitsu         C.Tanaka        Create          CSA
 *</pre>
 */
public final class NMAL0230Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL0230Query MY_INSTANCE = new NMAL0230Query();

    /**
     * Private constructor
     */
    private NMAL0230Query() {
        super();
    }

    /**
     * Get the NMAL0230Query instance.
     * @return NMAL0230Query instance
     */
    public static NMAL0230Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NMAL0230CMsg
     * @param glblMsg NMAL0230SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL0230CMsg bizMsg, NMAL0230SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("active", ACTIVE);
        params.put("inActive", INACTIVE);
        params.put("histActNm", UPDATE);
        params.put("cusaGlblCmpyCd", CUSA_GLBL_CMPY_CD);
        params.put("mdseItemTpCd_Set", MDSE_ITEM_TP.SET);
        params.put("mdseItemTpCd_Kit", MDSE_ITEM_TP.KIT);
        params.put("mdseTpCd_Set", MDSE_TP.SET);

        params.put("mdseCd_BO", bizMsg.mdseCd_BO.getValue());
        params.put("mdseDescShortTxt_BO", bizMsg.mdseDescShortTxt_BO.getValue());
        params.put("mdseItemTpCd_BO", bizMsg.mdseItemTpCd_BO.getValue());
        params.put("coaMdseTpCd_BO", bizMsg.coaMdseTpCd_BO.getValue());
        params.put("coaProdCd_BO", bizMsg.coaProdCd_BO.getValue());
        params.put("effFromDt_BO", bizMsg.effFromDt_BO.getValue());
        params.put("effThruDt_BO", bizMsg.effThruDt_BO.getValue());
        params.put("cmpsnRevnNum_BO", bizMsg.cmpsnRevnNum_BO.getValue());
        params.put("actvFlg_BO", bizMsg.actvFlg_BO.getValue());

        params.put("mdseCd_CO", bizMsg.childMdseCd_CO.getValue());
        params.put("mdseDescShortTxt_CO", bizMsg.mdseDescShortTxt_CO.getValue());
        params.put("mdseCmpsnTpCd_CO", bizMsg.mdseCmpsnTpCd_CO.getValue());
        params.put("coaMdseTpCd_CO", bizMsg.coaMdseTpCd_CO.getValue());
        params.put("coaProdCd_CO", bizMsg.coaProdCd_CO.getValue());
        params.put("effFromDt_CO", bizMsg.effFromDt_CO.getValue());
        params.put("effThruDt_CO", bizMsg.effThruDt_CO.getValue());
        params.put("cmpsnRevnNum_CO", bizMsg.cmpsnRevnNum_CO.getValue());
        params.put("actvFlg_CO", bizMsg.actvFlg_CO.getValue());

        params.put("rowNum", glblMsg.A.length());

        return getSsmEZDClient().queryEZDMsgArray("getBomList", params, glblMsg.A);
    }

    /**
     * Get all Revision Info for CSV download
     * @param bizMsg NMAL0230CMsg
     * @param rsSupport S21SsmBooleanResultSetHandlerSupport
     * @return S21SsmEZDResult
     */
    public Boolean getRevisionForCsv(NMAL0230CMsg bizMsg, S21SsmBooleanResultSetHandlerSupport rsSupport) {
        Map<String, Object> params = new HashMap<String, Object>();
        S21SsmExecutionParameter ssmExecParam = new S21SsmExecutionParameter();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("active", ACTIVE);
        params.put("inActive", INACTIVE);
        params.put("histActNm", UPDATE);
        params.put("cusaGlblCmpyCd", CUSA_GLBL_CMPY_CD);
        params.put("mdseItemTpCd_Set", MDSE_ITEM_TP.SET);
        params.put("mdseItemTpCd_Kit", MDSE_ITEM_TP.KIT);
        params.put("mdseTpCd_Set", MDSE_TP.SET);

        params.put("mdseCd_BO", bizMsg.mdseCd_BO.getValue());
        params.put("mdseDescShortTxt_BO", bizMsg.mdseDescShortTxt_BO.getValue());
        params.put("mdseItemTpCd_BO", bizMsg.mdseItemTpCd_BO.getValue());
        params.put("coaMdseTpCd_BO", bizMsg.coaMdseTpCd_BO.getValue());
        params.put("coaProdCd_BO", bizMsg.coaProdCd_BO.getValue());
        params.put("effFromDt_BO", bizMsg.effFromDt_BO.getValue());
        params.put("effThruDt_BO", bizMsg.effThruDt_BO.getValue());
        params.put("cmpsnRevnNum_BO", bizMsg.cmpsnRevnNum_BO.getValue());
        params.put("actvFlg_BO", bizMsg.actvFlg_BO.getValue());

        params.put("mdseCd_CO", bizMsg.childMdseCd_CO.getValue());
        params.put("mdseDescShortTxt_CO", bizMsg.mdseDescShortTxt_CO.getValue());
        params.put("mdseCmpsnTpCd_CO", bizMsg.mdseCmpsnTpCd_CO.getValue());
        params.put("coaMdseTpCd_CO", bizMsg.coaMdseTpCd_CO.getValue());
        params.put("coaProdCd_CO", bizMsg.coaProdCd_CO.getValue());
        params.put("effFromDt_CO", bizMsg.effFromDt_CO.getValue());
        params.put("effThruDt_CO", bizMsg.effThruDt_CO.getValue());
        params.put("cmpsnRevnNum_CO", bizMsg.cmpsnRevnNum_CO.getValue());
        params.put("actvFlg_CO", bizMsg.actvFlg_CO.getValue());

        params.put("cusaMdse", ZYPCodeDataUtil.getVarCharConstValue(CUSA_MDSE, getGlobalCompanyCode()));

        ssmExecParam.setFetchSize(FETCH_SIZE_MAX);
        ssmExecParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmExecParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmExecParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        Boolean result = (Boolean) ssmBatchClient.queryObject("getRevisionForCsv", params, ssmExecParam, rsSupport);

        return result;
    }
}
