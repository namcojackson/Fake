/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7190;

import static business.blap.NMAL7190.constant.NMAL7190Constant.COMMA;
import static business.blap.NMAL7190.constant.NMAL7190Constant.HIGH_VAL_TM;

import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL7190.common.NMAL7190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_ATTRB;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL7190Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 * 2018/12/05   Fujitsu         T.Noguchi       Update          QC#29324
 * 2023/04/20   Hitachi         H.Watanabe      Update          QC#61200
 *</pre>
 */
public final class NMAL7190Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7190Query MY_INSTANCE = new NMAL7190Query();

    /**
     * Private constructor
     */
    private NMAL7190Query() {
        super();
    }

    /**
     * Get the NMAL7190Query instance.
     * @return NMAL7190Query instance
     */
    public static NMAL7190Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getPrcGrpTrgtTp
     * @param bizMsg NMAL7190CMsg
     * @param glblMsg NMAL7190SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcGrpTrgtTp(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("prcGrpTpCd", glblMsg.prcGrpTpCd.getValue());

      return getSsmEZDClient().queryObjectList("getPrcGrpTrgtTp", params);
    }

    /**
     * Search
     * @param bizMsg NMAL7190CMsg
     * @param glblMsg NMAL7190SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("rowNum", glblMsg.A.length() + 1);
      params.put("prcGrpPk", bizMsg.prcGrpPk.getValue());
      // 2018/12/05 QC#29324 Add Start
      NMAL7190CommonLogic.setFilterParam(params, bizMsg, getGlobalCompanyCode());
      // 2018/12/05 QC#29324 Add End
      // 2023/04/20 QC#61200 Add Start
      params.put("prcGrpTrgtAttrbCdDisc", PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER);
      // 2023/04/20 QC#61200 Add End

      return getSsmEZDClient().queryObjectList("getPrcGrp", params);
    }

    /**
     * Search Price Group Name
     * @param bizMsg NMAL7190CMsg
     * @param glblMsg NMAL7190SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPriceGroupName(NMAL7190CMsg bizMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("prcGrpNm", bizMsg.prcGrpNm.getValue());

      return getSsmEZDClient().queryObjectList("getPriceGroupName", params);
    }
}
