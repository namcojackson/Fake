/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7060;

import static business.blap.NMAL7060.constant.NMAL7060Constant.HIGH_VAL_DT;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL7060Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         q09947          Create          N/A
 * 2016/04/26   Fujitsu         W.Honda         Update          QC#6739
 * 2016/04/26   Fujitsu         W.Honda         Update          QC#7912
 * 2017/02/10   Fujitsu         R.Nakamura      Update          QC#17529
 *</pre>
 */
public final class NMAL7060Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7060Query MY_INSTANCE = new NMAL7060Query();

    /**
     * Private constructor
     */
    private NMAL7060Query() {
        super();
    }

    /**
     * Get the NMAL7060Query instance.
     * @return NMAL7060Query instance
     */
    public static NMAL7060Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getPrcMtrPkgMdl
     * @param bizMsg NMAL7060CMsg
     * @param glblMsg NMAL7060SMsg
     * @param filterSearchFlg boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcMtrPkgMdl(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg, boolean filterSearchFlg) {
      Map<String, Object> ssmParam = new HashMap<String, Object>();
      ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
      ssmParam.put("rowNum", bizMsg.A.length() + 1);
      if (ZYPCommonFunc.hasValue(bizMsg.prcMtrPkgNm)) {
          ssmParam.put("prcMtrPkgNm", bizMsg.prcMtrPkgNm.getValue());
      }
      if (ZYPCommonFunc.hasValue(bizMsg.prcMtrPkgPk)) {
          ssmParam.put("prcMtrPkgPk", bizMsg.prcMtrPkgPk.getValue());
      }
      // Add Start 2017/02/10 QC#17529]
      if (filterSearchFlg && ZYPCommonFunc.hasValue(bizMsg.mdlNm_F1)) {
          ssmParam.put("mdlNm", bizMsg.mdlNm_F1.getValue());
      }
      // Add End 2017/02/10 QC#17529

      return getSsmEZDClient().queryObjectList("getPrcMtrPkgMdl", ssmParam);
    }

    /**
     * getPrcMtrPkgBllgMtr
     * @param bizMsg NMAL7060CMsg
     * @param glblMsg NMAL7060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcMtrPkgBllgMtr(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
      Map<String, Object> ssmParam = new HashMap<String, Object>();
      ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
      ssmParam.put("rowNum", glblMsg.B.length() + 1);
      if (ZYPCommonFunc.hasValue(bizMsg.prcMtrPkgNm)) {
          ssmParam.put("prcMtrPkgNm", bizMsg.prcMtrPkgNm.getValue());
      }
      if (ZYPCommonFunc.hasValue(bizMsg.prcMtrPkgPk)) {
          ssmParam.put("prcMtrPkgPk", bizMsg.prcMtrPkgPk.getValue());
      }
      // Add Start 2017/02/10 QC#17529
      if (ZYPCommonFunc.hasValue(bizMsg.mdlNm_F1)) {
          ssmParam.put("mdlNm", bizMsg.mdlNm_F1.getValue());
      }
      // Add End 2017/02/10 QC#17529

      return getSsmEZDClient().queryObjectList("getPrcMtrPkgBllgMtr", ssmParam);
    }

    /**
     * getRegisteredSelectHardMeter
     * @param bizMsg NMAL7060CMsg
     * @param glblMsg NMAL7060SMsg
     * @param index int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRegisteredSelectHardMeter(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg, int index) {
      Map<String, Object> ssmParam = new HashMap<String, Object>();
      ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
      ssmParam.put("rowNum", bizMsg.C.length() + 1);
      ssmParam.put("RegularMeter", MTR_LB_TP.REGULAR_METER);
      // Mod Start 2017/02/16 QC#17529
//      String[] mdlNm = new String[bizMsg.A.getValidCount()];
      List<String> mdlNmList = new ArrayList<String>();
      // Mod End 2017/02/16 QC#17529
      for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
          // Add Start 2017/02/16 QC#17529
          if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcMtrPkgMdlPk_A1) //
                  || !ZYPCommonFunc.hasValue(bizMsg.A.no(i).mdlNm_A1) //
                  || Arrays.asList(mdlNmList).contains(bizMsg.A.no(i).mdlNm_A1.getValue())) {
              continue;
          }
          // Add End 2017/02/16 QC#17529
          // Mod Start 2017/02/16 QC#17529
//          mdlNm[i] = bizMsg.A.no(i).mdlNm_A1.getValue();
          mdlNmList.add(bizMsg.A.no(i).mdlNm_A1.getValue());
          // Mod End 2017/02/16 QC#17529
      }
      // Add Start 2017/02/16 QC#17529
      if (ZYPCommonFunc.hasValue(bizMsg.prcMtrPkgPk)) {
          ssmParam.put("prcMtrPkgPk", bizMsg.prcMtrPkgPk.getValue());
      }
      if (mdlNmList.size() > 0) {
          ssmParam.put("mdlNmExist", ZYPConstant.FLG_ON_Y);
          ssmParam.put("mdlNmList", (String[]) mdlNmList.toArray(new String[mdlNmList.size()]));
      }
      // Add End 2017/02/16 QC#17529
      // Del Start 2017/02/16 QC#17529
//      ssmParam.put("mdlNmList", mdlNm);
      // Del End 2017/02/16 QC#17529
      ssmParam.put("prcMtrPkgBllgMtrPk", bizMsg.B.no(index).prcMtrPkgBllgMtrPk_B1.getValue());

      return getSsmEZDClient().queryObjectList("getRegisteredSelectHardMeter", ssmParam);
    }

    /**
     * getUnregisteredSelectHardMeter
     * @param bizMsg NMAL7060CMsg
     * @param glblMsg NMAL7060SMsg
     * @param index int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUnregisteredSelectHardMeter(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg, int index) {
      Map<String, Object> ssmParam = new HashMap<String, Object>();
      ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
      ssmParam.put("rowNum", bizMsg.C.length() + 1);
      // Mod Start 2017/02/16 QC#17529
//      String[] mdlNm = new String[bizMsg.A.getValidCount()];
      List<String> mdlNmList = new ArrayList<String>();
      // Mod End 2017/02/16 QC#17529
      for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
          // Add Start 2017/02/16 QC#17529
          if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcMtrPkgMdlPk_A1) //
                  || !ZYPCommonFunc.hasValue(bizMsg.A.no(i).mdlNm_A1) //
                  || Arrays.asList(mdlNmList).contains(bizMsg.A.no(i).mdlNm_A1.getValue())) {
              continue;
          }
          // Add End 2017/02/16 QC#17529
          // Mod Start 2017/02/16 QC#17529
//          mdlNm[i] = bizMsg.A.no(i).mdlNm_A1.getValue();
          mdlNmList.add(bizMsg.A.no(i).mdlNm_A1.getValue());
          // Mod End 2017/02/16 QC#17529
      }
      // Add Start 2017/02/16 QC#17529
      if (ZYPCommonFunc.hasValue(bizMsg.prcMtrPkgPk)) {
          ssmParam.put("prcMtrPkgPk", bizMsg.prcMtrPkgPk.getValue());
      }
      if (mdlNmList.size() > 0) {
          ssmParam.put("mdlNmExist", ZYPConstant.FLG_ON_Y);
          ssmParam.put("mdlNmList", (String[]) mdlNmList.toArray(new String[mdlNmList.size()]));
      }
      // Add End 2017/02/16 QC#17529
      // Del Start 2017/02/16 QC#17529
//      ssmParam.put("mdlNmList", mdlNm);
      // Del End 2017/02/16 QC#17529
      ssmParam.put("mtrLbDescTxt", bizMsg.B.no(index).mtrLbDescTxt_B1.getValue());
      // QC#11509 2016/07/07 Add start
      ssmParam.put("actvFlgY", ZYPConstant.FLG_ON_Y);
      ssmParam.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
      // QC#11509 2016/07/07 Add end

      return getSsmEZDClient().queryObjectList("getUnregisteredSelectHardMeter", ssmParam);
    }

    /**
     * Get Package Name
     * @param bizMsg NMAL7060CMsg
     * @param glblMsg NMAL7060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPackageName(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
      Map<String, Object> ssmParam = new HashMap<String, Object>();
      ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
      ssmParam.put("prcMtrPkgNm", bizMsg.prcMtrPkgNm.getValue());

      return getSsmEZDClient().queryObjectList("getPackageName", ssmParam);
    }

    /**
     * Get Model Name
     * @param bizMsg NMAL7060CMsg
     * @param mdlNms String[]
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getModelName(NMAL7060CMsg bizMsg, String[] mdlNms) {
      Map<String, Object> ssmParam = new HashMap<String, Object>();
      ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
      ssmParam.put("mdlNmList", mdlNms);

      return getSsmEZDClient().queryObjectList("getModelName", ssmParam);
    }

    /**
     * Get Billing Meter Name
     * @param bizMsg NMAL7060CMsg
     * @param mdlNms String[]
     * @param bllgMtrNms String[]
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillingMeterName(NMAL7060CMsg bizMsg, String[] mdlNms, String[] bllgMtrNms) {
      Map<String, Object> ssmParam = new HashMap<String, Object>();
      ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
      ssmParam.put("mdlNmList", mdlNms);
      ssmParam.put("bllgMtrNmList", bllgMtrNms);
      // QC#11509 2016/07/07 Add start
      ssmParam.put("actvFlgY", ZYPConstant.FLG_ON_Y);
      ssmParam.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
      // QC#11509 2016/07/07 Add end
      ssmParam.put("highValDt", HIGH_VAL_DT);
      // Add Start 2017/02/16 QC#17529
      if (ZYPCommonFunc.hasValue(bizMsg.prcMtrPkgPk)) {
          ssmParam.put("prcMtrPkgPk", bizMsg.prcMtrPkgPk.getValue());
      }
      if (mdlNms.length > 0) {
          ssmParam.put("mdlNmExist", ZYPConstant.FLG_ON_Y);
      }
      // Add End 2017/02/16 QC#17529

      return getSsmEZDClient().queryObjectList("getBillingMeterName", ssmParam);
    }

    // QC#6739 2016/04/26 Add start
    /**
     * Get Billing Meter Name
     * @param bizMsg NMAL7060CMsg
     * @param mdlNms String[]
     * @param bllgMtrNms String[]
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSameLevelBillingMeter(NMAL7060CMsg bizMsg) {
      Map<String, Object> ssmParam = new HashMap<String, Object>();
      ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
      BigDecimal[] mdlId = new BigDecimal[bizMsg.A.getValidCount()];
      for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
          mdlId[i] = bizMsg.A.no(i).mdlId_A1.getValue();
      }
      ssmParam.put("mdlIdList", mdlId);
      ssmParam.put("bllgMtrLvlNum", bizMsg.B.no(0).bllgMtrLvlNum_B1.getValue());
      // QC#11509 2016/07/07 Add start
      ssmParam.put("actvFlgY", ZYPConstant.FLG_ON_Y);
      ssmParam.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
      // QC#11509 2016/07/07 Add end
      ssmParam.put("highValDt", HIGH_VAL_DT);

      return getSsmEZDClient().queryObjectList("getSameLevelBillingMeter", ssmParam);
    }

    // QC#7912 2016/04/26 Mod start
    /**
     * Get Billing Meter Name
     * @param bizMsg NMAL7060CMsg
     * @param mdlIds BigDecimal[]
     * @param mdlCnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCommonCounter(NMAL7060CMsg bizMsg, BigDecimal[] mdlIds, int mdlCnt) {
      // QC#7912 2016/04/26 Mod start
      Map<String, Object> ssmParam = new HashMap<String, Object>();
      ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
      // QC#7912 2016/04/26 Del start
//      List<BigDecimal> mdlId = new ArrayList<BigDecimal>();
//      int mdlCnt = 0;
//      for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//          if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).mtrGrpPk_A1)) {
//              mdlId.add(bizMsg.A.no(i).mdlId_A1.getValue());
//              mdlCnt++;
//          }
//      }
//      BigDecimal[] mdlIds = (BigDecimal[]) mdlId.toArray(new BigDecimal[mdlId.size()]);
      // QC#7912 2016/04/26 Del start
      ssmParam.put("mdlIdList", mdlIds);
      ssmParam.put("bllgMtrLvlNum", bizMsg.B.no(0).bllgMtrLvlNum_B1.getValue());
      ssmParam.put("mdlCnt", mdlCnt);
      // QC#11509 2016/07/07 Add start
      ssmParam.put("actvFlgY", ZYPConstant.FLG_ON_Y);
      ssmParam.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
      // QC#11509 2016/07/07 Add end

      return getSsmEZDClient().queryObjectList("getCommonCounter", ssmParam);
    }
    // QC#6739 2016/04/26 Add end

    // Add Start 2017/02/14 QC#17529
    /**
     * getPrcMtrPkgMdl
     * @param bizMsg NMAL7060CMsg
     * @param mdlNm String
     * @param prcMtrPkgMdlPk BigDecimal
     * @param effFromDt String
     * @param effThruDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCheckPrcMtrPkgMdl(NMAL7060CMsg bizMsg, String mdlNm, BigDecimal prcMtrPkgMdlPk, String effFromDt, String effThruDt) {
      Map<String, Object> ssmParam = new HashMap<String, Object>();
      ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
      ssmParam.put("rowNum", 1);
      if (ZYPCommonFunc.hasValue(bizMsg.prcMtrPkgNm)) {
          ssmParam.put("prcMtrPkgNm", bizMsg.prcMtrPkgNm.getValue());
      }
      if (ZYPCommonFunc.hasValue(bizMsg.prcMtrPkgPk)) {
          ssmParam.put("prcMtrPkgPk", bizMsg.prcMtrPkgPk.getValue());
      }
      if (ZYPCommonFunc.hasValue(mdlNm)) {
          ssmParam.put("mdlNm", mdlNm);
      }
      if (ZYPCommonFunc.hasValue(prcMtrPkgMdlPk)) {
          ssmParam.put("prcMtrPkgMdlPk", prcMtrPkgMdlPk);
      }
      if (ZYPCommonFunc.hasValue(effFromDt)) {
          ssmParam.put("effFromDt", effFromDt);
      }
      if (ZYPCommonFunc.hasValue(effThruDt)) {
          ssmParam.put("effThruDt", effThruDt);
      } else {
          ssmParam.put("effThruDt", HIGH_VAL_DT);
      }

      return getSsmEZDClient().queryObjectList("getPrcMtrPkgMdl", ssmParam);
    }
    // Add End 2017/02/14 QC#17529

    // QC#24307 2018/08/20 Add start
    /**
     * getPrcMtrPkgBllgMtr
     * @param bizMsg NMAL7060CMsg
     * @param glblMsg NMAL7060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgMtrFromMdl(BigDecimal mdlId, String slsDt) {
      Map<String, Object> ssmParam = new HashMap<String, Object>();
      ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
      ssmParam.put("mdlId", mdlId);
      ssmParam.put("slsDt", slsDt);
      ssmParam.put("actvFlgY", ZYPConstant.FLG_ON_Y);

      return getSsmEZDClient().queryObjectList("getBllgMtrFromMdl", ssmParam);
    }
    // QC#24307 2018/08/20 Add end
}
