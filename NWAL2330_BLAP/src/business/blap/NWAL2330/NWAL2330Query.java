/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2330;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL2330Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public final class NWAL2330Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL2330Query MY_INSTANCE = new NWAL2330Query();

    /**
     * Private constructor
     */
    private NWAL2330Query() {
        super();
    }

    /**
     * Get the NWAL2330Query instance.
     * @return NWAL2330Query instance
     */
    public static NWAL2330Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NWAL2330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NWAL2330CMsg bizMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      // set ssm parameters.
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("cpoOrdNum", bizMsg.cpoOrdNum_H1.getValue());
      params.put("invNum", bizMsg.invNum_H1.getValue());
      params.put("ordDtFrom", bizMsg.ordDt_H1.getValue());
      params.put("ordDtTo", bizMsg.ordDt_H2.getValue());
      params.put("invDtFrom", bizMsg.invDt_H1.getValue());
      params.put("invDtTo", bizMsg.invDt_H2.getValue());
      params.put("sellToCustCd", bizMsg.sellToCustCd_H1.getValue());
      params.put("billToCustAcctCd", bizMsg.billToCustAcctCd_H1.getValue());
      params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd_H1.getValue());
      params.put("dsOrdTpCd", bizMsg.dsOrdTpCd_H1.getValue());
      params.put("soNum", bizMsg.soNum_H1.getValue());
      params.put("shipToCustAcctCd", bizMsg.shipToCustAcctCd_H1.getValue());
      params.put("cpoSrcTpCd", bizMsg.cpoSrcTpCd_H1.getValue());
      params.put("dsAcctNm1", bizMsg.dsAcctNm_H1.getValue());
      params.put("dsAcctNm2", bizMsg.dsAcctNm_H2.getValue());
      params.put("dsAcctNm3", bizMsg.dsAcctNm_H3.getValue());
      params.put("rowNum", bizMsg.A.length());

      return getSsmEZDClient().queryEZDMsgArray("search", params, bizMsg.A);
    }

    /**
     * getPullDownDataList
     * 
     * @param dsOrdCatgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPullDownDataList(String dsOrdCatgCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsOrdCatgCd", dsOrdCatgCd);

        return getSsmEZDClient().queryObjectList("getPullDownData", params);
    }

}
