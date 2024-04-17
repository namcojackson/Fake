/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFAL0210;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Manual Journal Entry Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/14   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public final class NFAL0210Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFAL0210Query INSTANCE = new NFAL0210Query();

    /**
     * Constructor.
     */
    private NFAL0210Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFAL0210Query
     */
    public static NFAL0210Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NFAL0210CMsg
     * @param sMsg NFAL0210SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NFAL0210CMsg cMsg, NFAL0210SMsg sMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.manJrnlNm.getValue())) {
            params.put("manJrnlNm", cMsg.manJrnlNm.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.glPerNm.getValue())) {
            params.put("glPerNm", cMsg.glPerNm.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxFromDt.getValue())) {
            params.put("glDtFrom", cMsg.xxFromDt.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxToDt.getValue())) {
            params.put("glDtTo", cMsg.xxToDt.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.jrnlCatgCd.getValue())) {
            params.put("jrnlCatgCd", cMsg.jrnlCatgCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.glSendCpltFlg_SV.getValue())) {
            params.put("glSendCpltFlg", cMsg.glSendCpltFlg_SV.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.rvslCpltFlg_SV.getValue())) {
            params.put("rvslCpltFlg", cMsg.rvslCpltFlg_SV.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.manJrnlCpltFlg_SV.getValue())) {
            params.put("manJrnlCpltFlg", cMsg.manJrnlCpltFlg_SV.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.autoRvslFlg_SV.getValue())) {
            params.put("autoRvslFlg", cMsg.autoRvslFlg_SV.getValue());
        }
        params.put("rowNum", cnt);
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", params, sMsg.A);
    }
}
