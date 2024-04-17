/*
 *  <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL7050;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Class name: NMAL7050Query
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 2015/11/26   Fujitsu         W.Honda         Create          N/A                                                         
 *</pre>
 */
public final class NMAL7050Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL7050Query myInstance = new NMAL7050Query();

    /**
     * Constructor.
     */
    private NMAL7050Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return  NMAL7050Query
     */
    public static NMAL7050Query getInstance() {
        return myInstance;
    }

    /**
     * Singleton instance getter.
     * @param cMsg NMAL7050CMsg
     * @param sMsg NMAL7050SMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getServiceMeterPackageDetail(NMAL7050CMsg cMsg, NMAL7050SMsg sMsg) {
        // Create Parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        if (ZYPCommonFunc.hasValue(cMsg.prcMtrPkgNm.getValue())) {
            ssmParam.put("prcMtrPkgNm", cMsg.prcMtrPkgNm.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxDsMultMsgDplyTxt.getValue())) {
            String[] mdlNm = cMsg.xxDsMultMsgDplyTxt.getValue().split(",", 0);
            ssmParam.put("mdlNmList", mdlNm);
        }
        if (ZYPCommonFunc.hasValue(cMsg.mtrLbDescTxt_BG.getValue())) {
            ssmParam.put("mtrLbDescTxtBllg", cMsg.mtrLbDescTxt_BG.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.mtrLbDescTxt_PH.getValue())) {
            ssmParam.put("mtrLbDescTxtPhys", cMsg.mtrLbDescTxt_PH.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.effFromDt.getValue())) {
            ssmParam.put("effFromDt", cMsg.effFromDt.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.effThruDt.getValue())) {
            ssmParam.put("effThruDt", cMsg.effThruDt.getValue());
        }

        return getSsmEZDClient().queryObjectList("getServiceMeterPackageDetail", ssmParam);
    }
}
