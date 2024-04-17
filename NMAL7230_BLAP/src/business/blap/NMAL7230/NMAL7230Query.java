/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7230;

import static business.blap.NMAL7230.constant.NMAL7230Constant.BIZ_ID;
import static business.blap.NMAL7230.constant.NMAL7230Constant.HIGH_DATE;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL7230Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public final class NMAL7230Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7230Query MY_INSTANCE = new NMAL7230Query();

    /**
     * Private constructor
     */
    private NMAL7230Query() {
        super();
    }

    /**
     * Get the NMAL7230Query instance.
     * @return NMAL7230Query instance
     */
    public static NMAL7230Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NMAL7230CMsg
     * @param glblMsg NMAL7230SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("lineBizTpCd", bizMsg.lineBizTpCd.getValue());
      params.put("frtZoneNum", bizMsg.frtZoneNum.getValue());
      if (ZYPCommonFunc.hasValue(bizMsg.xxDsMultMsgDplyTxt_HS.getValue())) {
          String[] shipToStCd = bizMsg.xxDsMultMsgDplyTxt_HS.getValue().split(",", 0);
          params.put("shipToStCdList", shipToStCd);
      }
      if (ZYPCommonFunc.hasValue(bizMsg.xxDsMultMsgDplyTxt_HC.getValue())) {
          String[] shipToCtryCd = bizMsg.xxDsMultMsgDplyTxt_HC.getValue().split(",", 0);
          params.put("shipToCtryCdList", shipToCtryCd);
      }
      if (ZYPCommonFunc.hasValue(bizMsg.xxDsMultMsgDplyTxt_HA.getValue())) {
          String[] dsAcctNm = bizMsg.xxDsMultMsgDplyTxt_HA.getValue().split(",", 0);
          params.put("dsAcctNmList", dsAcctNm);
          params.put("dsAcctNmFlg", ZYPConstant.FLG_ON_Y);
      }
      if (ZYPCommonFunc.hasValue(bizMsg.xxDsMultMsgDplyTxt_HG.getValue())) {
          String[] prcGrpNm = bizMsg.xxDsMultMsgDplyTxt_HG.getValue().split(",", 0);
          params.put("prcGrpNmList", prcGrpNm);
          params.put("prcGrpNmFlg", ZYPConstant.FLG_ON_Y);
      }
      params.put("shipToFromPostCd", bizMsg.shipToFromPostCd.getValue());
      params.put("shipToThruPostCd", bizMsg.shipToThruPostCd.getValue());
      params.put("effFromDt", bizMsg.effFromDt.getValue());
      params.put("effThruDt", bizMsg.effThruDt.getValue());
      params.put("actvFlg", bizMsg.actvFlg.getValue());
      params.put("prcGrpTpCust", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
      params.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
      params.put("rowNum", glblMsg.A.length() + 1);

      return getSsmEZDClient().queryObjectList("search", params);
    }

    /**
     * checkActiveDuplicationZone
     * @param bizLineMsg NMAL7230_ACMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkActiveDuplicationZone(NMAL7230_ACMsg bizLineMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("highDate", HIGH_DATE);
      params.put("lineBizTpCd", bizLineMsg.lineBizTpCd_A1.getValue());
      params.put("dsAcctNm", bizLineMsg.dsAcctNm_A1.getValue());
      params.put("prcGrpNm", bizLineMsg.prcGrpNm_A1.getValue());
      params.put("shipToCtryCd", bizLineMsg.shipToCtryCd_A1.getValue());
      if (CTRY.UNITED_STATES_OF_AMERICA.equals(bizLineMsg.shipToCtryCd_A1.getValue())) {
          params.put("shipToStCd", bizLineMsg.shipToStCd_A1.getValue());
      }
      params.put("shipToFromPostCd", bizLineMsg.shipToFromPostCd_A1.getValue());
      params.put("shipToThruPostCd", bizLineMsg.shipToThruPostCd_A1.getValue());
      params.put("effFromDt", bizLineMsg.effFromDt_A1.getValue());
      params.put("effThruDt", bizLineMsg.effThruDt_A1.getValue());
      params.put("prcGrpTpCust", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);

      return getSsmEZDClient().queryObjectList("checkActiveDuplicationZone", params);
    }

    /**
     * Search
     * @param bizMsg NMAL7230CMsg
     * @param glblMsg NMAL7230SMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public static Map<String, Object> searchForDownload(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg, String glblCmpyCd) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", glblCmpyCd);
      params.put("lineBizTpCd", bizMsg.lineBizTpCd.getValue());
      params.put("frtZoneNum", bizMsg.frtZoneNum.getValue());
      if (ZYPCommonFunc.hasValue(bizMsg.xxDsMultMsgDplyTxt_HS.getValue())) {
          String[] shipToStCd = bizMsg.xxDsMultMsgDplyTxt_HS.getValue().split(",", 0);
          params.put("shipToStCdList", shipToStCd);
      }
      if (ZYPCommonFunc.hasValue(bizMsg.xxDsMultMsgDplyTxt_HC.getValue())) {
          String[] shipToCtryCd = bizMsg.xxDsMultMsgDplyTxt_HC.getValue().split(",", 0);
          params.put("shipToCtryCdList", shipToCtryCd);
      }
      if (ZYPCommonFunc.hasValue(bizMsg.xxDsMultMsgDplyTxt_HA.getValue())) {
          String[] dsAcctNm = bizMsg.xxDsMultMsgDplyTxt_HA.getValue().split(",", 0);
          params.put("dsAcctNmList", dsAcctNm);
      }
      if (ZYPCommonFunc.hasValue(bizMsg.xxDsMultMsgDplyTxt_HG.getValue())) {
          String[] prcGrpNm = bizMsg.xxDsMultMsgDplyTxt_HG.getValue().split(",", 0);
          params.put("prcGrpNmList", prcGrpNm);
      }
      params.put("shipToFromPostCd", bizMsg.shipToFromPostCd.getValue());
      params.put("shipToThruPostCd", bizMsg.shipToThruPostCd.getValue());
      params.put("effFromDt", bizMsg.effFromDt.getValue());
      params.put("effThruDt", bizMsg.effThruDt.getValue());
      params.put("actvFlg", bizMsg.actvFlg.getValue());
      params.put("prcGrpTpCust", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
      params.put("salesDate", ZYPDateUtil.getSalesDate(glblCmpyCd));

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
