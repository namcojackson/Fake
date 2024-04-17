/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0760;

import static business.blap.NSAL0760.constant.NSAL0760Constant.DISP_TXT_BASE;
import static business.blap.NSAL0760.constant.NSAL0760Constant.DISP_TXT_OVERAGE;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Contract Status History
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Hitachi         K.Kishimoto     Create          N/A
 * 2015/12/01   Hitachi         A.Kohinata      Update          QC1243
 * 2015/12/01   Hitachi         T.Tsuchida      Update          QC1204
 * 2015/12/14   Hitachi         T.Kanasaka      Update          QC#1965
 * 2016/02/19   Hitachi         K.Kishimoto     Update          QC#3686
 *</pre>
 */
public final class NSAL0760Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0760Query INSTANCE = new NSAL0760Query();

    /**
     * Constructor.
     */
    private NSAL0760Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0760Query
     */
    public static NSAL0760Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL0760CMsg
     * @param sMsg NSAL0760SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0760CMsg cMsg, NSAL0760SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg), sMsg.B);
    }

    private Map<String, Object> getSsmParam(NSAL0760CMsg cMsg, NSAL0760SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        // START 2015/12/14 T.Kanasaka [QC#1965, MOD]
        params.put("dsContrPk", cMsg.dsContrPk.getValue());
        // END 2015/12/14 T.Kanasaka [QC#1965, MOD]
        if (FLG_ON_Y.equals(cMsg.xxChkBox_H1.getValue())) {
            params.put("activeLine", FLG_ON_Y);
        }
        if (FLG_ON_Y.equals(cMsg.xxChkBox_H2.getValue())) {
            params.put("inclAcc", FLG_ON_Y);
        }
        params.put("base", DISP_TXT_BASE);
        params.put("overage", DISP_TXT_OVERAGE);
        params.put("dsContrDtlTpCdAcc", DS_CONTR_DTL_TP.ACCESSORIES);
        params.put("dsContrDtlTpCdFlt", DS_CONTR_DTL_TP.FLEET);
        params.put("dsContrDtlTpCdAgg", DS_CONTR_DTL_TP.AGGREGATE);
        List<String> dsContrDtlStsCdList = new ArrayList<String>();
        //Mod  Start 02/19/2016 <QC#3686>
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.ACTIVE);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.EXPIRED);
        //Mod  End   02/19/2016 <QC#3686>
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.TERMINATED);
        params.put("dsContrDtlStsCd", dsContrDtlStsCdList);
        params.put("maxCnt", sMsg.B.length() + 1);

        return params;

    }

}
