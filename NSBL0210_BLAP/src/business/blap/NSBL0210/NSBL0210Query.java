/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0210;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Labor Charge Table Maintenance
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public final class NSBL0210Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0210Query INSTANCE = new NSBL0210Query();

    /**
     * Constructor.
     */
    private NSBL0210Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0210Query
     */
    public static NSBL0210Query getInstance() {
        return INSTANCE;
    }

    /**
     * searchData
     * @param cMsg NSBL0210CMsg
     * @param sMsg NSBL0210SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchData(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("svcLineBizCd", cMsg.svcLineBizCd_H.getValue());
        params.put("mdlGrpNm", cMsg.mdlGrpNm_H.getValue());
        params.put("svcPrcShiftDescTxt", cMsg.svcPrcShiftDescTxt_H.getValue());
        params.put("sysSrcCd", SYS_SRC.S21_SERVICE);
        params.put("limitNum", sMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("searchData", params, sMsg.A);
    }

    /**
     * getSvcLineBizList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcLineBizList() {
        String glblCmpyCd = getGlobalCompanyCode();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("sysSrcCd", SYS_SRC.S21_SERVICE);
        return getSsmEZDClient().queryObjectList("getSvcLineBizList", ssmParam);
    }
}
