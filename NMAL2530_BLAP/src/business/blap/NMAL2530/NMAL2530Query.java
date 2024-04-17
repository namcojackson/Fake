/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2530;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/20   Fujitsu         N.Sugiura       Create          N/A
 * 2015/12/15   Fujitsu         K.Koitabashi    Update          QC#1883
 * 2016/02/29   Fujitsu         M.Suzuki        Update          QC#4540
 * 2016/02/29   Fujitsu         M.Suzuki        Update          QC#4544
 * 2017/02/23   Fujitsu         K.Ishizuka      Update          QC#16481
 *</pre>
 */
public final class NMAL2530Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL2530Query INSTANCE = new NMAL2530Query();

    /**
     * Constructor.
     */
    private NMAL2530Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return  NMAL2530Query
     */
    public static NMAL2530Query getInstance() {
        return INSTANCE;
    }

    /**
     * Organization Search.
     * @param cMsg NMAL2530CMsg
     * @param sMsg NMAL2530SMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getOrganization(NMAL2530CMsg cMsg, NMAL2530SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("orgStruTpCd", cMsg.orgStruTpCd_H1.getValue());
        ssmParam.put("orgNm", cMsg.orgNm_H1.getValue());
        ssmParam.put("prntOrgNm", cMsg.orgNm_H2.getValue());
        ssmParam.put("tocNm", cMsg.tocNm_H1.getValue());
        ssmParam.put("psnCd", cMsg.psnCd_H1.getValue());
        ssmParam.put("orgTierCd", cMsg.orgTierCd_H1.getValue());
        // 2016/02/29 S21_NA#4540 Mod Start --------------
        ssmParam.put("orgSvcPlnCd", cMsg.csrRgTpCd_H1.getValue());
        // 2016/02/29 S21_NA#4540 Mod Start --------------
        // MOD START S21_NA QC#16481
        ssmParam.put("effFromDt_FR", cMsg.effFromDt_FR.getValue());
        ssmParam.put("effFromDt_TO", cMsg.effFromDt_TO.getValue());
        // MOD END S21_NA QC#16481
        ssmParam.put("gnrnTpCd", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        ssmParam.put("orgTierCd", cMsg.orgTierCd_H1.getValue());
        // 2016/02/29 S21_NA#4540 Mod Start --------------
        ssmParam.put("orgSvcPlnCd", cMsg.csrRgTpCd_H1.getValue());
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        // 2016/02/29 S21_NA#4540 Mod Start --------------
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxEffThruDt", "99991231");

        return getSsmEZDClient().queryEZDMsgArray("getOrganization", ssmParam, sMsg.A);
    }
    /**
     * 
     * @param   cMsg NMAL2530CMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getBusinessArea(NMAL2530CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        return getSsmEZDClient().queryObjectList("getBusinessArea", ssmParam);
    }
    /**
     * 
     * @param   cMsg NMAL2530CMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getOrgTierNot0(NMAL2530CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        return getSsmEZDClient().queryObjectList("getOrgTierNot0", ssmParam);
    }
}
