/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0630;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_CONTR_STS_VTMsg;
import business.db.DS_CONTR_STS_VTMsgArray;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Contract On Hold
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   Hitachi         T.Tsuchida      Create          N/A
 * 2016/11/04   Hitachi         T.Tomita        Update          QC#4210
 * 2017/08/31   Hitachi         K.Kojima        Update          QC#20817
 * 2017/08/31   Hitachi         K.Kim           Update          QC#20578
 * 2023/03/02   CITS            E.Sanchez       Update          QC#61230
 * 2023/03/09   CITS            E.Sanchez       Update          QC#61230
 *</pre>
 */
public final class NSAL0630Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0630Query INSTANCE = new NSAL0630Query();

    /**
     * Constructor.
     */
    private NSAL0630Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0630Query
     */
    public static NSAL0630Query getInstance() {
        return INSTANCE;
    }

    // START 2016/11/04 T.Tomita [QC#4210, MOD]
    /**
     * get Contract Info
     * @param ssmParam Map<String, Object>
     * @param aCMsgArray NSAL0630_ASMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrInfo(Map<String, Object> ssmParam, NSAL0630_ASMsgArray aSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getContrInfo", ssmParam, aSMsgArray);
    }
    // END 2016/11/04 T.Tomita [QC#4210, MOD]

    // START 2017/08/31 K.Kim [QC#20578, ADD]
    /**
     * DS Contract Detail Info List
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrDtlList(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", dsContrPk);
        return getSsmEZDClient().queryObjectList("getContrDtlList", params);
    }

    /**
     * DS Contract Billing Meter Info List
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgMtrList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrDtlPk", dsContrDtlPk);
        return getSsmEZDClient().queryObjectList("getBllgMtrList", params);
    }

    /**
     * DS Contract Price Effectivity Info List
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcEffList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrDtlPk", dsContrDtlPk);
        return getSsmEZDClient().queryObjectList("getPrcEffList", params);
    }
    // END 2017/08/31 K.Kim [QC#20578, ADD]

    // START 2017/08/31 K.Kojima [QC#20817,ADD]
    public DS_CONTR_STS_VTMsg getDsContrStsV(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTR_STS_VTMsg prmTMsg = new DS_CONTR_STS_VTMsg();
        prmTMsg.setSQLID("002");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrPk01", dsContrPk);
        DS_CONTR_STS_VTMsgArray tMsgArray = (DS_CONTR_STS_VTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);
        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0);
        } else {
            return null;
        }
    }
    // END 2017/08/31 K.Kojima [QC#20817,ADD]

// START 2023/03/09 E.Sanchez [QC#61230, DEL]
//    // START 2023/03/02 E.Sanchez [QC#61230, ADD]
//    /**
//     * getDsContrStsCd
//     * @param glblCmpyCd String
//     * @param dsContrCtrlStsCd String
//     * @return String
//     */
//    public String getDsContrStsCd(String glblCmpyCd, String dsContrCtrlStsCd) {
//        String notFound = "";
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", glblCmpyCd);
//        params.put("dsContrCtrlStsCd", dsContrCtrlStsCd);
//        S21SsmEZDResult result = getSsmEZDClient().queryObject("getDsContrStsCd", params);
//        if (!result.isCodeNormal()) {
//            return notFound;
//        }
//        return (String) result.getResultObject();
//    }
//    // END 2023/03/02 E.Sanchez [QC#61230, ADD]
// END 2023/03/09 E.Sanchez [QC#61230, DEL]
}

