/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7130;

import static business.blap.NMAL7130.constant.NMAL7130Constant.HIGH_VAL_DT;
import static business.blap.NMAL7130.constant.NMAL7130Constant.NMAL7130_MDSE_TP_CTX_TP;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL7130.constant.NMAL7130Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL7130Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public final class NMAL7130Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7130Query MY_INSTANCE = new NMAL7130Query();

    /**
     * Private constructor
     */
    private NMAL7130Query() {
        super();
    }

    /**
     * Get the NMAL7130Query instance.
     * @return NMAL7130Query instance
     */
    public static NMAL7130Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getCoaMdseTp
     * @param bizMsg NMAL7130CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCoaMdseTp(NMAL7130CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseTpCtxTpCd", ZYPCodeDataUtil.getVarCharConstValue(NMAL7130_MDSE_TP_CTX_TP, getGlobalCompanyCode()));

        return getSsmEZDClient().queryObject("getCoaMdseTp", params);
    }

    /**
     * searchPrcContr.
     * @param bizMsg NMAL7130CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPrcContr(NMAL7130CMsg bizMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("prcContrPk", bizMsg.prcContrPk_H1);

      return getSsmEZDClient().queryEZDMsg("searchPrcContr", params, bizMsg);
    }

    /**
     * getPrcTermCondVrsn.
     * @param bizMsg NMAL7130CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcTermCondVrsn(NMAL7130CMsg bizMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("prcContrPk", bizMsg.prcContrPk_H1);
      return getSsmEZDClient().queryObjectList("getPrcTermCondVrsn", params);
    }

    /**
     * searchRegdAcct.
     * @param bizMsg NMAL7130CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchRegdAcct(NMAL7130CMsg bizMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("prcContrPk", bizMsg.prcContrPk_H1.getValue());
      params.put("check", bizMsg.xxChkBox_AH.getValue());
      params.put("flgY", ZYPConstant.FLG_ON_Y);
      params.put("slsDt", ZYPDateUtil.getSalesDate());
      params.put("highValDt", HIGH_VAL_DT);

      return getSsmEZDClient().queryEZDMsgArray("searchRegdAcct", params, bizMsg.A);
    }

    /**
     * searchRelnPrcList.
     * @param bizMsg NMAL7130CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchRelnPrcList(NMAL7130CMsg bizMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("prcContrPk", bizMsg.prcContrPk_H1.getValue());
      params.put("check", bizMsg.xxChkBox_BH.getValue());
      params.put("flgY", ZYPConstant.FLG_ON_Y);
      params.put("slsDt", ZYPDateUtil.getSalesDate());
      params.put("highValDt", HIGH_VAL_DT);
      params.put("actvStatus", NMAL7130Constant.STS_ACTV);
      params.put("inactvStatus", NMAL7130Constant.STS_INACTV);

      return getSsmEZDClient().queryEZDMsgArray("searchRelnPrcList", params, bizMsg.B);
    }

    /**
     * searchTrxChrg.
     * @param bizMsg NMAL7130CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchTrxChrg(NMAL7130CMsg bizMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("septChar", bizMsg.addCharTxt.getValue());
      params.put("prcContrPk", bizMsg.prcContrPk_H1.getValue());

      return getSsmEZDClient().queryEZDMsgArray("searchTrxChrg", params, bizMsg.D);
    }

    /**
     * searchPrcTermCond.
     * @param bizMsg NMAL7130CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPrcTermCond(NMAL7130CMsg bizMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("prcContrPk", bizMsg.prcContrPk_H1.getValue());
      params.put("prcTermCondVrsnNum", bizMsg.prcTermCondVrsnNum_E1.getValue());

      return getSsmEZDClient().queryObject("searchPrcTermCond", params);
    }

    /**
     * getLeaseAcct.
     * @param bizMsg NMAL7130CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLeaseAcct(NMAL7130CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsAcctNum", bizMsg.dsAcctNum_C1.getValue());
        params.put("dsAcctClsCd", DS_ACCT_CLS.LEASE_CO);
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("highValDt", HIGH_VAL_DT);

        return getSsmEZDClient().queryObjectList("getLeaseAcct", params);
    }

    /**
     * isExistContrNum.
     * @param prcContrPk BigDecimal
     * @param prcContrNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isExistContrNum(BigDecimal prcContrPk, String prcContrNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcContrPk", prcContrPk);
        params.put("prcContrNum", prcContrNum);
        params.put("flgY", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("isExistContrNum", params);
    }

    /**
     * isExistAcctNum.
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isExistAcctNum(String dsAcctNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsAcctNum", dsAcctNum);
        params.put("flgY", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("isExistAcctNum", params);
    }

    /**
     * isRebTpMdse.
     * @param mdseCd String
     * @param coaMdseTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isRebTpMdse(String mdseCd, String coaMdseTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", mdseCd);
        params.put("coaMdseTpCd", coaMdseTpCd);
        params.put("flgY", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("isRebTpMdse", params);
    }

    /**
     * getPrcContrOrdCatg
     * @param prcContrPk BigDecimal
     * @param prcContrTrxChrgPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcContrOrdCatg(BigDecimal prcContrPk, BigDecimal prcContrTrxChrgPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcContrPk", prcContrPk);
        params.put("PrcContrTrxChrgPk", prcContrTrxChrgPk);

        return getSsmEZDClient().queryObjectList("getPrcContrOrdCatg", params);
    }

    /**
     * getAnyColmn.
     * @param colNm String
     * @param tblNm String
     * @param whereCol String
     * @param whereVal String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAnyColmn(String colNm, String tblNm, String whereCol, String whereVal) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("colNm", colNm);
        params.put("tblNm", tblNm);
        params.put("whereCol", whereCol);
        params.put("whereVal", whereVal);

        return getSsmEZDClient().queryObject("getAnyColmn", params);
    }
}
