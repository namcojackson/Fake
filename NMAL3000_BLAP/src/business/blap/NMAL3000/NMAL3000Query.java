/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL3000;

import static business.blap.NMAL3000.constant.NMAL3000Constant.ACCOUNT_NUM;
import static business.blap.NMAL3000.constant.NMAL3000Constant.BIZ_ID;
import static business.blap.NMAL3000.constant.NMAL3000Constant.DEALER_CODE;
import static business.blap.NMAL3000.constant.NMAL3000Constant.EFF_DATE_FROM;
import static business.blap.NMAL3000.constant.NMAL3000Constant.EFF_DATE_TO;
import static business.blap.NMAL3000.constant.NMAL3000Constant.GLBCMPY_CD;
import static business.blap.NMAL3000.constant.NMAL3000Constant.MODEL;
import static business.blap.NMAL3000.constant.NMAL3000Constant.ROWNUM;
import static business.blap.NMAL3000.constant.NMAL3000Constant.SALES;
import static business.blap.NMAL3000.constant.NMAL3000Constant.SERVICE;

import java.util.HashMap;
import java.util.Map;

import business.db.MKT_MDLTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL3000Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         M.Suzuki        Create          N/A
 * 2016/03/09   SRAA            Y.Chen          Update          QC#5169
 * 2018/11/28   Fujitsu         R.Nakamura      Update          QC#27336
 *</pre>
 */
public final class NMAL3000Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL3000Query MY_INSTANCE = new NMAL3000Query();

    /**
     * Private constructor
     */
    private NMAL3000Query() {
        super();
    }

    /**
     * Get the NMAL3000Query instance.
     * @return NMAL3000Query instance
     */
    public static NMAL3000Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NMAL3000CMsg
     * @param glblMsg NMAL3000SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {

      Map<String, Object> params = new HashMap<String, Object>();
      setParam(bizMsg, params);
      params.put(ROWNUM, glblMsg.A.length() + 1);
      return getSsmEZDClient().queryEZDMsgArray("getDlrArzList", params, glblMsg.A);
    }

    private void setParam(NMAL3000CMsg bizMsg, Map<String, Object> params) {
        params.put(GLBCMPY_CD, getGlobalCompanyCode());
          params.put(ACCOUNT_NUM, bizMsg.dsAcctCustNum.getValue());
          params.put(MODEL, bizMsg.mktMdlCd.getValue());
          params.put(DEALER_CODE, bizMsg.dsAcctDlrCd.getValue());
          params.put(EFF_DATE_FROM, bizMsg.effFromDt.getValue());
          params.put(EFF_DATE_TO, bizMsg.effThruDt.getValue());
          if (ZYPCommonFunc.hasValue(bizMsg.slsAuthFlg_SA.getValue())) {
              params.put(SALES , bizMsg.slsAuthFlg_SA.getValue());
          }

          if (ZYPCommonFunc.hasValue(bizMsg.slsAuthFlg_SE.getValue())) {
              params.put(SERVICE, bizMsg.slsAuthFlg_SE.getValue());
          }
    }


    /**
     * createSearchCond
     * @param bizMsg NMAL3000CMsg
     * @param glblCmpyCd String
     * @return Map<String, Object>
     */
    public Map<String, Object> createSearchCond(NMAL3000CMsg bizMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        setParam(bizMsg, ssmParam);
        return ssmParam;
    }

    /**
     * getSavedSearchOptionList
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String usrId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("srchOptAplId", BIZ_ID);
        params.put("srchOptUsrId", usrId);
        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

    // Add Start 2018/11/28 QC#27336
    /**
     * getOverlapCount
     * @param glblMsg NMAL3000SMsg
     * @param i int
     * @param mkttms MKT_MDLTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOverlapCount(NMAL3000SMsg glblMsg, int i, MKT_MDLTMsg mkttms) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(GLBCMPY_CD, getGlobalCompanyCode());
        params.put(ACCOUNT_NUM, glblMsg.A.no(i).dsAcctCustNum_A.getValue());
        params.put(MODEL, mkttms.mktMdlCd.getValue());
        params.put(EFF_DATE_FROM, glblMsg.A.no(i).effFromDt_A.getValue());
        if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).effThruDt_A)) {
            params.put(EFF_DATE_TO, glblMsg.A.no(i).effThruDt_A.getValue());
        }

        return getSsmEZDClient().queryObject("getOverlapCount", params);
    }
    // Add End 2018/11/28 QC#27336

}
