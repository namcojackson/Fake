/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL9910;

import static business.blap.NSAL9910.constant.NSAL9910Constant.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * General Business Master Maintenance List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Hitachi         A.Kohinata      Create          N/A
 * 2016/05/16   Hitachi         A.Kohinata      Update          QC#8147
 * 2018/06/15   CITS            M.Naito         Update          QC#24320
 * 2018/06/21   Hitachi         K.Kojima        Update          QC#24320
 *</pre>
 */
public final class NSAL9910Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL9910Query INSTANCE = new NSAL9910Query();

    /**
     * Constructor.
     */
    private NSAL9910Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL9910Query
     */
    public static NSAL9910Query getInstance() {
        return INSTANCE;
    }

    /**
     * getTrgtTbl
     * @param cMsg NSAL9910CMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrgtTbl(NSAL9910CMsg cMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        // START 2016/05/16 [QC#8147, DEL]
        //params.put("bizFuncGrpId", cMsg.bizFuncGrpId.getValue());
        // END 2016/05/16 [QC#8147, DEL]
        List<String> bizFuncIdList = new ArrayList<String>();
        // START 2018/06/15 M.Naito [QC#24320, MOD]
        // START 2018/06/21 K.Kojima [QC#24320,DEL]
        // boolean referenceUserFlg = false;
        // END 2018/06/21 K.Kojima [QC#24320,DEL]
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            bizFuncIdList.add(cMsg.B.no(i).bizFuncId.getValue());

            // START 2018/06/21 K.Kojima [QC#24320,DEL]
            // if (cMsg.B.no(i).bizFuncId.getValue().endsWith(FUNC_ID_T020)) {
            //     referenceUserFlg = true;
            // }
            // END 2018/06/21 K.Kojima [QC#24320,DEL]
        }
        // START 2018/06/21 K.Kojima [QC#24320,DEL]
        // params.put("referenceUserFlg", referenceUserFlg);
        // END 2018/06/21 K.Kojima [QC#24320,DEL]
        // // END 2018/06/15 M.Naito [QC#24320, MOD]
        params.put("bizFuncIdList", bizFuncIdList);
        params.put("rowNum", cnt);

        return getSsmEZDClient().queryEZDMsgArray("getTrgtTbl", params, cMsg.A);
    }
}
