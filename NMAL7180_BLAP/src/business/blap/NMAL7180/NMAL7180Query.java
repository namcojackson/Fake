/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7180;

import static business.blap.NMAL7180.constant.NMAL7180Constant.*;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL7180Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Fujitsu         W.Honda         Create          N/A
 * 2017/08/21   Fujitsu         M.Yamada        Update          QC#18785(L3)
 *</pre>
 */
public final class NMAL7180Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7180Query MY_INSTANCE = new NMAL7180Query();

    /**
     * Private constructor
     */
    private NMAL7180Query() {
        super();
    }

    /**
     * Get the NMAL7180Query instance.
     * @return NMAL7180Query instance
     */
    public static NMAL7180Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NMAL7180CMsg
     * @param glblMsg NMAL7180SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL7180CMsg bizMsg, NMAL7180SMsg glblMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("rowNum", bizMsg.A.length() + 1);
      params.put("highValDt", HIGH_VAL_DT);
      params.put("prcGrpNm", bizMsg.prcGrpNm.getValue());
      params.put("prcGrpDescTxt", bizMsg.prcGrpDescTxt.getValue());
      params.put("prcGrpTpCd", bizMsg.prcGrpTpCd.getValue());
      params.put("prcGrpTrgtTpCd", bizMsg.prcGrpTrgtTpCd.getValue());
      params.put("prcGrpTrgtAttrbCd", bizMsg.prcGrpTrgtAttrbCd.getValue());
      params.put("prcGrpFromTxt", bizMsg.prcGrpFromTxt.getValue());
      params.put("prcGrpThruTxt", bizMsg.prcGrpThruTxt);
      params.put("actvFlg", bizMsg.actvFlg.getValue());
      params.put("effFromDt", bizMsg.effFromDt.getValue());
      params.put("effThruDt", bizMsg.effThruDt.getValue());
      params.put("prcGrpTrxTpCd", bizMsg.prcGrpTrxTpCd.getValue()); // QC#18785

      return getSsmEZDClient().queryObjectList("getPriceGroup", params);
    }
}
