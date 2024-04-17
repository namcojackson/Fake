/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0060;


import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.ML_BIZ_ADDR_MAPTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_CTRL_REC;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/31/2022   CITS            A.Cullano       Create          QC#59828
 * 2023/03/07   Hitachi         S.Fujita        Update          QC#61169
 * </pre>
 */
public final class NWCL0060Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWCL0060Query MY_INSTANCE = new NWCL0060Query();

    /**
     * Constructor.
     */
    private NWCL0060Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWCL0060Query
     */
    public static NWCL0060Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * Get the Invoice/Bill Information
     * </pre>
     * @param cMsg NWCL0060CMsg
     * @return S21SsmEZDResult Invoice/Bill Information
     */
    public S21SsmEZDResult getInvPrtCtrl(NWCL0060CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("invPrtCtrlRecCd", INV_PRT_CTRL_REC.CONSOLIDATED_BILL);
        // START 2023/03/07 S.Fujita [QC#61169, MOD]
        // if (ZYPCommonFunc.hasValue(cMsg.A.no(0).invNum_A1)) {
        if (ZYPCommonFunc.hasValue(cMsg.A.no(0).arCustRefNum_A1)) {
            // params.put("invNum", cMsg.A.no(0).invNum_A1.getValue());
            params.put("invNum", cMsg.A.no(0).arCustRefNum_A1.getValue());
        // END 2023/03/07 S.Fujita [QC#61169, MOD]
            return getSsmEZDClient().queryObject("getInvoice", params);
        } else {
            params.put("conslBillNum", cMsg.A.no(0).conslBillNum_A1.getValue());
            return getSsmEZDClient().queryObject("getBill", params);
        }
    }

    /**
     * Get From e-mail address by business
     * @param lineBizTpCd String
     * @param dsBizAreaCd String
     * @return String
     */
    public String getFromAddrByBiz(String lineBizTpCd, String dsBizAreaCd) {
        if (!ZYPCommonFunc.hasValue(lineBizTpCd) || !ZYPCommonFunc.hasValue(dsBizAreaCd)) {
            return null;
        }

        ML_BIZ_ADDR_MAPTMsg mlBizAddrMapTmsg = new ML_BIZ_ADDR_MAPTMsg();
        ZYPEZDItemValueSetter.setValue(mlBizAddrMapTmsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(mlBizAddrMapTmsg.lineBizTpCd, lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(mlBizAddrMapTmsg.dsBizAreaCd, dsBizAreaCd);

        mlBizAddrMapTmsg = (ML_BIZ_ADDR_MAPTMsg) EZDTBLAccessor.findByKey(mlBizAddrMapTmsg);
        if (mlBizAddrMapTmsg == null || !ZYPCommonFunc.hasValue(mlBizAddrMapTmsg.mlBizAddr)) {
            return null;
        }
        return mlBizAddrMapTmsg.mlBizAddr.getValue();
    }

}
