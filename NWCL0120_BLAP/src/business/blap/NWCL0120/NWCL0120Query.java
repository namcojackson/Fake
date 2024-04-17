/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0120;

import static business.blap.NWCL0120.constant.NWCL0120Constant.SQL_ROW_NUM_ONE;
import static business.blap.NWCL0120.constant.NWCL0120Constant.VAR_CHAR_CONST_NM_INV_PRINT_SHORT_FALL_ITEM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWCL0120Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public final class NWCL0120Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWCL0120Query MY_INSTANCE = new NWCL0120Query();

    /**
     * Private constructor
     */
    private NWCL0120Query() {
        super();
    }

    /**
     * Get the NWCL0120Query instance.
     * @return NWCL0120Query instance
     */
    public static NWCL0120Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get EasyPAC Invoice List
     * @param cMsg NWCL0120CMsg
     * @param sMsg NWCL0120SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getEasyPacInvoiceList(NWCL0120CMsg cMsg, NWCL0120SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("invDtFrom", cMsg.invDt_FM);
        ssmParam.put("invDtTo", cMsg.invDt_TO);
        ssmParam.put("mdseCd", ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_INV_PRINT_SHORT_FALL_ITEM, getGlobalCompanyCode()));
        ssmParam.put("splyPgmInvRvwFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("cpoHldFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("openFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("fnlzInvFlgOn", ZYPConstant.FLG_ON_Y);
        ssmParam.put("fnlzInvFlgOff", ZYPConstant.FLG_OFF_N);
        ssmParam.put("itrlInvErrFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.EASY_PAC1);
        ssmParam.put("rowNumOne", SQL_ROW_NUM_ONE);
        ssmParam.put("rowNum", sMsg.A.length() + 1);

        List<String> shpgStsCdList = new ArrayList<String>();
        shpgStsCdList.add(SHPG_STS.SHIPPED);
        shpgStsCdList.add(SHPG_STS.S_OR_O_CANCELLED);
        shpgStsCdList.add(SHPG_STS.P_OR_O_PRINTED);
        shpgStsCdList.add(SHPG_STS.P_OR_O_CANCELLED);
        shpgStsCdList.add(SHPG_STS.ARRIVED);
        shpgStsCdList.add(SHPG_STS.N_INVOICE_READY);
        shpgStsCdList.add(SHPG_STS.INSTALLED);
        ssmParam.put("shpgStsCdList", shpgStsCdList);

        return getSsmEZDClient().queryEZDMsgArray("getEasyPacInvoiceList", ssmParam, sMsg.A);
    }

    /**
     * Get Invoice Error
     * @param cMsg NWCL0120CMsg
     * @param invNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvoiceErr(NWCL0120CMsg cMsg, String invNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("invNum", invNum);
        return getSsmEZDClient().queryObjectList("getInvoiceErr", ssmParam);
    }

    /**
     * Get Hold Reason Text
     * @param cMsg NWCL0120CMsg
     * @param cpoOrdNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHldRsnTxt(NWCL0120CMsg cMsg, String cpoOrdNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("relFlg", ZYPConstant.FLG_OFF_N);
        return getSsmEZDClient().queryObjectList("getHldRsnTxt", ssmParam);
    }

    /**
     * Get Invoice Print Control Pk
     * @param cMsg NWCL0120CMsg
     * @param invNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvPrtCtrlPk(NWCL0120CMsg cMsg, String invNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("invNum", invNum);
        return getSsmEZDClient().queryObjectList("getInvPrtCtrlPk", ssmParam);
    }
}
