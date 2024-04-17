/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0860;

import java.util.HashMap;
import java.util.Map;

import business.file.NSAL0860F00FMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Register & Deregister Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/06   Hitachi         Y.Osawa         Create          N/A
 * 2016/06/15   Hitachi         K.Kasai         Create          QC#9954
 * 2016/06/17   Hitachi         O.Okuma         Update          QC#9951
 * 2016/07/15   Hitachi         Y.Osawa         Update          QC#9956
 *</pre>
 */
public final class NSAL0860Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0860Query INSTANCE = new NSAL0860Query();

    /**
     * Constructor.
     */
    private NSAL0860Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0860Query
     */
    public static NSAL0860Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL0860CMsg
     * @param sMsg NSAL0860SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0860CMsg cMsg, NSAL0860SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("slsDt", cMsg.slsDt.getValue());
        params.put("serNum", cMsg.serNum.getValue());
        params.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.CANCELLED);
        params.put("dsContrCatgCd", DS_CONTR_CATG.WARRANTY);
        params.put("iwrCondCd", cMsg.iwrCondCd.getValue());
        params.put("curLocAcctNum", cMsg.curLocAcctNum.getValue());
        params.put("dsAcctNm", cMsg.dsAcctNm.getValue());
        params.put("tMdlNm", cMsg.mdlNm.getValue());
        params.put("dsContrNum", cMsg.dsContrNum.getValue());
        params.put("limitCount", sMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getSearchData", params, sMsg.A);
    }

    /**
     * getSearchData
     * @param cMsg NSAL0860CMsg
     * @param fMsg NSAL0860F00FMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMachineInfo(NSAL0860CMsg cMsg, NSAL0860F00FMsg fMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("serNum", fMsg.serNum_A.getValue());
        params.put("mdlNm", fMsg.t_MdlNm_A.getValue());

        return getSsmEZDClient().queryObjectList("getMachineInfo", params);
    }
}
