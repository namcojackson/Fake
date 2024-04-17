/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7210;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import static business.blap.NMAL7210.constant.NMAL7210Constant.SEARCH_MAX_CNT;
import static business.blap.NMAL7210.constant.NMAL7210Constant.EFF_THRU_MAX_DT;

/**
 *<pre>
 * NMAL7210Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public final class NMAL7210Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7210Query MY_INSTANCE = new NMAL7210Query();

    /**
     * Private constructor
     */
    private NMAL7210Query() {
        super();
    }

    /**
     * Get the NMAL7210Query instance.
     * @return NMAL7210Query instance
     */
    public static NMAL7210Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * 
     * @param bizMsg NMAL7210CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL7210CMsg bizMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("effFromDt", bizMsg.effFromDt_H1.getValue());
      if (ZYPCommonFunc.hasValue(bizMsg.prcFmlaNm_H1)) {
          params.put("prcFmlaNm", bizMsg.prcFmlaNm_H1.getValue());
      }
      if (ZYPCommonFunc.hasValue(bizMsg.prcFmlaDescTxt_H1)) {
          params.put("prcFmlaDesctxt", bizMsg.prcFmlaDescTxt_H1.getValue());
      }
      if (ZYPCommonFunc.hasValue(bizMsg.prcFmlaTpCd_H1)) {
          params.put("prcFmlaTpCd", bizMsg.prcFmlaTpCd_H1.getValue());
      }
      if (ZYPCommonFunc.hasValue(bizMsg.effThruDt_H1)) {
          params.put("effThruDt", bizMsg.effThruDt_H1.getValue());
      }
      if (ZYPCommonFunc.hasValue(bizMsg.actvFlg_H1)) {
          params.put("actvFlg", bizMsg.actvFlg_H1.getValue());
      } else {
          params.put("actvFlg", ZYPConstant.FLG_OFF_N);
      }
      params.put("rowNum", SEARCH_MAX_CNT);
      params.put("effThruMaxDt", EFF_THRU_MAX_DT);
      return getSsmEZDClient().queryEZDMsgArray("search",  params, bizMsg.A);
    }
}
