/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7220;

import static business.blap.NMAL7220.constant.NMAL7220Constant.VAR_CHAR_CONST_STD_COST_DUMMY_PRC_LIST;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_FMLA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL7220Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/27   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public final class NMAL7220Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7220Query MY_INSTANCE = new NMAL7220Query();

    /**
     * Private constructor
     */
    private NMAL7220Query() {
        super();
    }

    /**
     * Get the NMAL7220Query instance.
     * @return NMAL7220Query instance
     */
    public static NMAL7220Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * 
     * @param bizMsg NMAL7220CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL7220CMsg bizMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("prcFmlaTpCd", PRC_FMLA_TP.PRICE_LIST);
      params.put("varCharConstNm", VAR_CHAR_CONST_STD_COST_DUMMY_PRC_LIST);
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("prcFmlaPk", bizMsg.prcFmlaPk_H1.getValue());

      return getSsmEZDClient().queryObject("search", params);
    }

    /**
     * isExistFmlaNm.
     * 
     * @param bizMsg NMAL7220CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isExistFmlaNm(NMAL7220CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcFmlaNm", bizMsg.prcFmlaNm_H1);
        params.put("prcFmlaPk", bizMsg.prcFmlaPk_BK);

        return getSsmEZDClient().queryObject("isExistFmlaNm", params);
    }

    /**
     * isExistPriceList.
     * 
     * @param prcList String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isExistPriceList(String prcList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgNm", prcList);
        params.put("prcListTpCd", PRC_LIST_STRU_TP.EQUIPMENT);

        return getSsmEZDClient().queryObject("isExistPriceList", params);
    }
}
