/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0670;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Cancel Contract or Machine on Contract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         K.Kasai         Create          N/A
 * 2016/11/28   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/05/08   Hitachi         K.Kitachi       Update          QC18268
 * 2017/05/24   Hitachi         K.Kitachi       Update          QC18268
 *</pre>
 */
public final class NSAL0670Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0670Query INSTANCE = new NSAL0670Query();

    /**
     * Constructor.
     */
    private NSAL0670Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0670Query
     */
    public static NSAL0670Query getInstance() {
        return INSTANCE;
    }
    // mod start 2016/11/28 CSA QC#4210
    /**
     * get Contract Info
     * @param ssmParam Map<String, Object>
     * @param aSMsgArray NSAL0670_ASMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrInfo(Map<String, Object> ssmParam, NSAL0670_ASMsgArray aSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getContrInfo", ssmParam, aSMsgArray);
        // mod end 2016/11/28 CSA QC#4210
    }

    public boolean existNotUpdateDsContrDtl(NSAL0670CMsg cMsg, NSAL0670_ACMsg aCMsg) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("dsContrPk", aCMsg.dsContrPk_A1.getValue().toString());
        String[] dsContrCtrlStsList = {DS_CONTR_CTRL_STS.ENTERED, DS_CONTR_CTRL_STS.DRAFT};
        ssmParam.put("dsContrCtrlStsCdArray", dsContrCtrlStsList);
        return (Integer) getSsmEZDClient().queryObject("cntNotUpdateDsContrDtl", ssmParam).getResultObject() > 0;
    }

    // START 2017/05/08 K.Kitachi [QC#18268, ADD]
    public List<BigDecimal> getDsContrDtlPkList(NSAL0670CMsg cMsg, NSAL0670_ASMsg aSMsgIn) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("dsContrPk", aSMsgIn.dsContrPk_A1.getValue());
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDsContrDtlPkList", ssmParam).getResultObject();
    }
    // END 2017/05/08 K.Kitachi [QC#18268, ADD]

    // START 2017/05/24 K.Kitachi [QC#18268, ADD]
    public List<BigDecimal> getDsContrDtlPkListFromDtlStsV(NSAL0670CMsg cMsg, NSAL0670_ASMsg aSMsgIn) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("dsContrPk", aSMsgIn.dsContrPk_A1.getValue());
        String[] dsContrCtrlStsCdList = {DS_CONTR_CTRL_STS.ENTERED, DS_CONTR_CTRL_STS.DRAFT };
        ssmParam.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDsContrDtlPkListFromDtlStsV", ssmParam).getResultObject();
    }
    // END 2017/05/24 K.Kitachi [QC#18268, ADD]
}
