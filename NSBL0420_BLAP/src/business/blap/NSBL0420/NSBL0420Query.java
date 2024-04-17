/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0420;

import java.math.BigDecimal;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.SVC_MOD_DTL_ITEMTMsg;
import business.db.SVC_MOD_DTL_ITEMTMsgArray;
import business.db.SVC_MOD_STSTMsg;
import business.db.SVC_MOD_STSTMsgArray;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Mods Parts Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public final class NSBL0420Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0420Query INSTANCE = new NSBL0420Query();

    /**
     * Constructor.
     */
    private NSBL0420Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0420Query
     */
    public static NSBL0420Query getInstance() {
        return INSTANCE;
    }

    /**
     * get Detail Item Information
     * @param ssmParam Map<String, Object>
     * @param aSMsgArray NSBL0420_ASMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDtlItemInfo(Map<String, Object> ssmParam, NSBL0420_ASMsgArray aSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getDtlItemInfo", ssmParam, aSMsgArray);
    }

    /**
     * get Merchandise Name
     * @param glblCmpyCd String
     * @param mdseCd BigDecimal
     * @return ALL_MDSE_VTMsg
     */
    public static ALL_MDSE_VTMsgArray getALL_MDSE_V(String glblCmpyCd, String mdseCd) {
        ALL_MDSE_VTMsg param = new ALL_MDSE_VTMsg();
        param.setSQLID("003");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("mdseCd01", mdseCd);
        ALL_MDSE_VTMsgArray result = (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(param);
        return result;
    }

    /**
     * get Service Modification Status Table
     * @param glblCmpyCd String
     * @param svcModDtlPk BigDecimal
     * @return SVC_MOD_STSTMsg
     */
    public static SVC_MOD_STSTMsgArray getSVC_MOD_STS(String glblCmpyCd, BigDecimal svcModDtlPk) {
        SVC_MOD_STSTMsg param = new SVC_MOD_STSTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("svcModDtlPk01", svcModDtlPk);

        SVC_MOD_STSTMsgArray result = (SVC_MOD_STSTMsgArray) EZDTBLAccessor.findByCondition(param);
        return result;
    }

    /**
     * get Detail Item
     * @param glblCmpyCd String
     * @param svcModDtlPk BigDecimal
     * @return SVC_MOD_DTL_ITEMTMsg
     */
    public static SVC_MOD_DTL_ITEMTMsgArray getSVC_MOD_DTL_ITEM(String glblCmpyCd, BigDecimal svcModDtlPk) {
        SVC_MOD_DTL_ITEMTMsg param = new SVC_MOD_DTL_ITEMTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("svcModDtlPk01", svcModDtlPk);
        SVC_MOD_DTL_ITEMTMsgArray result =  (SVC_MOD_DTL_ITEMTMsgArray) EZDTBLAccessor.findByCondition(param);
        return result;
    }
}
