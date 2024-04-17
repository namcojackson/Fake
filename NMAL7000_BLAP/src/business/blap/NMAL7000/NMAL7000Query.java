/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7000;

import static business.blap.NMAL7000.constant.NMAL7000Constant.BIZ_ID;
import static business.blap.NMAL7000.constant.NMAL7000Constant.HIGH_VAL_DT;
import static business.blap.NMAL7000.constant.NMAL7000Constant.STS_ACTIVE;
import static business.blap.NMAL7000.constant.NMAL7000Constant.STS_DELETE;
import static business.blap.NMAL7000.constant.NMAL7000Constant.STS_INACTIVE;
import static business.blap.NMAL7000.constant.NMAL7000Constant.STS_NONE;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL7000Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/16   Fujitsu         M.Nakamura      Create          N/A
 * 2016/08/01   Fujitsu         R.Nakamura      Update          QC#10928
 *</pre>
 */
public final class NMAL7000Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7000Query MY_INSTANCE = new NMAL7000Query();

    /**
     * Private constructor
     */
    private NMAL7000Query() {
        super();
    }

    /**
     * Get the NMAL7000Query instance.
     * @return NMAL7000Query instance
     */
    public static NMAL7000Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NMAL7000CMsg
     * @param glblMsg NMAL7000SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL7000CMsg bizMsg, NMAL7000SMsg glblMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("slsDt", ZYPDateUtil.getSalesDate());
      params.put("flgY", ZYPConstant.FLG_ON_Y);
      params.put("flgN", ZYPConstant.FLG_OFF_N);
      params.put("highValDt", HIGH_VAL_DT);
      params.put("active", STS_ACTIVE);
      params.put("inactive", STS_INACTIVE);
      params.put("delete", STS_DELETE);
      params.put("none", STS_NONE);

      params.put("prcCatgNm",       bizMsg.prcCatgNm_H1.getValue());
      params.put("prcListDplyNm",   bizMsg.prcListDplyNm_H1.getValue());
      params.put("prcCatgDescTxt",  bizMsg.prcCatgDescTxt_H1.getValue());
      params.put("prcContrNum",     bizMsg.prcContrNum_H1.getValue());
      params.put("prcContrNm",      bizMsg.prcContrNm_H1.getValue());
      params.put("dsAcctNum",       bizMsg.dsAcctNum_H1.getValue());
      params.put("dsAcctNm",        bizMsg.dsAcctNm_H1.getValue());
      params.put("csmpNum",         bizMsg.csmpNum_H1.getValue());
      params.put("coaBrCd",         bizMsg.coaBrCd_H1.getValue());
      params.put("splyAgmtPlnNm",   bizMsg.splyAgmtPlnNm_H1.getValue());
      params.put("effFromDt",       bizMsg.effFromDt_H1.getValue());
      params.put("effThruDt",       bizMsg.effThruDt_H1.getValue());
      params.put("prcListTpCd",     bizMsg.prcListTpCd_H1.getValue());
      params.put("prcListStruTpCd", bizMsg.prcListStruTpCd_H1.getValue());
      params.put("prcListGrpCd",    bizMsg.prcListGrpCd_H1.getValue());
      params.put("prcDispRecTpCd",  bizMsg.prcDispRecTpCd_H1.getValue());
      // Add Start 2016/08/01 QC#10928
      params.put("itemNum",         bizMsg.prcListMdseCd_H1.getValue());
      params.put("serviseModelNm",  bizMsg.mdlNm_H1.getValue());
      params.put("qualifierTp",     PRC_QLFY_TP.ITEM_CODE);
      // Add End 2016/08/01 QC#10928

      if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_H1)
              || ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_H1)) {
          params.put("acctFlg",   ZYPConstant.FLG_ON_Y);
      } else {
          params.put("acctFlg",   ZYPConstant.FLG_OFF_N);
      }

      if (ZYPCommonFunc.hasValue(bizMsg.csmpNum_H1)) {
          params.put("csmpFlg",   ZYPConstant.FLG_ON_Y);
      } else {
          params.put("csmpFlg",   ZYPConstant.FLG_OFF_N);
      }

      if (ZYPCommonFunc.hasValue(bizMsg.coaBrCd_H1)) {
          params.put("coaBrFlg",   ZYPConstant.FLG_ON_Y);
      } else {
          params.put("coaBrFlg",   ZYPConstant.FLG_OFF_N);
      }

      if (ZYPCommonFunc.hasValue(bizMsg.splyAgmtPlnNm_H1)) {
          params.put("splyPlnFlg",   ZYPConstant.FLG_ON_Y);
      } else {
          params.put("splyPlnFlg",   ZYPConstant.FLG_OFF_N);
      }

      if (ZYPCommonFunc.hasValue(bizMsg.prcListMdseCd_H1)) {
          params.put("itemNumFlg", ZYPConstant.FLG_ON_Y);
      } else {
          params.put("itemNumFlg", ZYPConstant.FLG_OFF_N);
      }

      if (ZYPCommonFunc.hasValue(bizMsg.mdlNm_H1)) {
          params.put("modelFlg", ZYPConstant.FLG_ON_Y);
      } else {
          params.put("modelFlg", ZYPConstant.FLG_OFF_N);
      }

      return getSsmEZDClient().queryEZDMsgArray("searchPrcList", params, glblMsg.A);
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
