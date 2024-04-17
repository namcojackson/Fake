/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7240;

import static business.blap.NMAL7240.constant.NMAL7240Constant.BIZ_ID;
import static business.blap.NMAL7240.constant.NMAL7240Constant.HIGH_DATE;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL7240Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   Fujitsu         W.Honda         Create          N/A
 * 2016/09/14   SRAA            Y.Chen          Update          QC#12938
 *</pre>
 */
public final class NMAL7240Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7240Query MY_INSTANCE = new NMAL7240Query();

    /**
     * Private constructor
     */
    private NMAL7240Query() {
        super();
    }

    /**
     * Get the NMAL7240Query instance.
     * @return NMAL7240Query instance
     */
    public static NMAL7240Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NMAL7240CMsg
     * @param glblMsg NMAL7240SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      // set ssm parameters.
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("lineBizTpCd", bizMsg.lineBizTpCd.getValue());
      params.put("frtZoneNum", bizMsg.frtZoneNum.getValue());
      if (ZYPCommonFunc.hasValue(bizMsg.xxDsMultMsgDplyTxt_HL.getValue())) {
          String[] shipSvcLvlDescTxt = bizMsg.xxDsMultMsgDplyTxt_HL.getValue().split(",", 0);
          params.put("shpgSvcLvlDescTxtList", shipSvcLvlDescTxt);
      }
      params.put("effFromDt", bizMsg.effFromDt.getValue());
      params.put("effThruDt", bizMsg.effThruDt.getValue());
      params.put("actvFlg", bizMsg.actvFlg.getValue());
      params.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
      params.put("rowNum", glblMsg.A.length() + 1);

      return getSsmEZDClient().queryObjectList("search", params);
    }

    /**
     * checkActiveDuplicationZone
     * @param bizLineMsg NMAL7240_ACMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkActiveDuplicationZone(NMAL7240_ACMsg bizLineMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("highDate", HIGH_DATE);
      params.put("lineBizTpCd", bizLineMsg.lineBizTpCd_A1.getValue());
      params.put("frtZoneNum", bizLineMsg.frtZoneNum_A1.getValue());
      params.put("shpgSvcLvlCd", bizLineMsg.shpgSvcLvlCd_A1.getValue());
      params.put("fromSclQty", bizLineMsg.fromSclQty_A1.getValue());
      params.put("qtyUnitTpCd", bizLineMsg.qtyUnitTpCd_A1.getValue());
      params.put("effFromDt", bizLineMsg.effFromDt_A1.getValue());
      params.put("effThruDt", bizLineMsg.effThruDt_A1.getValue());

      return getSsmEZDClient().queryObjectList("checkActiveDuplicationZone", params);
    }

    /**
     * searchForDownload
     * @param bizMsg NMAL7240CMsg
     * @param glblMsg NMAL7240SMsg
     * @param glblCmpyCd Global Company Code
     * @return S21SsmEZDResult
     */
    public static Map<String, Object> searchForDownload(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg, String glblCmpyCd) {
      Map<String, Object> params = new HashMap<String, Object>();
      // set ssm parameters.
      params.put("glblCmpyCd", glblCmpyCd);
      params.put("lineBizTpCd", bizMsg.lineBizTpCd.getValue());
      params.put("frtZoneNum", bizMsg.frtZoneNum.getValue());
      if (ZYPCommonFunc.hasValue(bizMsg.xxDsMultMsgDplyTxt_HL.getValue())) {
          String[] shipSvcLvlDescTxt = bizMsg.xxDsMultMsgDplyTxt_HL.getValue().split(",", 0);
          params.put("shpgSvcLvlDescTxtList", shipSvcLvlDescTxt);
      }
      params.put("effFromDt", bizMsg.effFromDt.getValue());
      params.put("effThruDt", bizMsg.effThruDt.getValue());
      params.put("actvFlg", bizMsg.actvFlg.getValue());
      params.put("salesDate", ZYPDateUtil.getSalesDate(glblCmpyCd));
      // QC#12938
      // params.put("rowNum", glblMsg.A.length() + 1);

      return params;
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

}
