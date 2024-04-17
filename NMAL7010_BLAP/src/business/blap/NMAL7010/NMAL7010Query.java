/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7010;

import static business.blap.NMAL7010.constant.NMAL7010Constant.COMMA;
import static business.blap.NMAL7010.constant.NMAL7010Constant.HIGH_VAL_DT;
import static business.blap.NMAL7010.constant.NMAL7010Constant.HIGH_VAL_TM;
import static business.blap.NMAL7010.constant.NMAL7010Constant.MAX_ROW_EQUIPMENT;
import static business.blap.NMAL7010.constant.NMAL7010Constant.STS_ACTIVE;
import static business.blap.NMAL7010.constant.NMAL7010Constant.STS_DELETED;
import static business.blap.NMAL7010.constant.NMAL7010Constant.STS_INACTIVE;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_EQUIP;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_LEASE_RATE;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_LEASE_RTRN_FEE;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_QTY_DISC;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SPLY_PGM;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_SPEC_CHRG;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_TIER;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_TI;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL7010.common.NMAL7010CommonLogic;
import business.blap.NMAL7010.constant.NMAL7010Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CUST_AUDC_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_TRX_AUDC_CATG;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL7010Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/02   SRAA            Y.Chen          Update          QC#2175
 * 2016/02/09   SRA             E.Inada         Update          QC#2174
 * 2016/03/22   Fujitsu         Y.Kanefusa      Update          QC#4767
 * 2016/04/12   SRA             E.Inada         Update          QC#6378
 * 2016/04/22   SRA             E.Inada         Update          QC#7080
 * 2016/06/07   SRA             E.Inada         Update          QC#688
 * 2016/07/15   Fujitsu         W.Honda         Update          QC#11933
 * 2016/07/19   Fujitsu         W.Honda         Update          QC#11933
 * 2016/08/03   Fujitsu         R.Nakamura      Update          QC#12165
 * 2016/08/26   Fujitsu         R.Nakamura      Update          QC#3934
 * 2016/09/12   Fujitsu         R.Nakamura      Update          QC#11615
 * 2016/09/21   Hitachi         T.Mizuki        Update          QC#13252
 * 2016/10/17   Fujitsu         W.Honda         Update          QC#15193
 * 2018/04/06   Fujitsu         R.Nakamura      Update          QC#22556
 * 2018/05/08   Fujitsu         T.Noguchi       Update          QC#20269
 * 2018/07/17   Fujitsu         H.Kumagai       Update          QC#20267
 * 2018/11/17   Fujitsu         N.Sugiura       Update          QC#29147
 * 2018/11/27   Fujitsu         K.Kato          Update          QC#29319
 *</pre>
 */
public final class NMAL7010Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7010Query MY_INSTANCE = new NMAL7010Query();

    /**
     * Private constructor
     */
    private NMAL7010Query() {
        super();
    }

    /**
     * Get the NMAL7010Query instance.
     * @return NMAL7010Query instance
     */
    public static NMAL7010Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getBllgMtr.
     * @param mdlNm String
     * @param prcMtrPkgPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgMtr(String mdlNm, BigDecimal prcMtrPkgPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdlNm", mdlNm);
        params.put("prcMtrPkgPk", prcMtrPkgPk);

        return getSsmEZDClient().queryObjectList("getBllgMtr", params);
    }

    // QC#7958 delete
//    /**
//     * getInEachQty.
//     * @param itemCd String
//     * @param pkgUomCd String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getInEachQty(String itemCd, String pkgUomCd) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", getGlobalCompanyCode());
//        params.put("itemCd", itemCd);
//        params.put("pkgUomCd", pkgUomCd);
//
//        return getSsmEZDClient().queryObject("getInEachQty", params);
//    }

    // QC#7958 Add
    /**
     * getUomInfo
     * @param mdseCd String
     * @param pkgUomCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUomInfo(String mdseCd, String pkgUomCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", mdseCd);
        params.put("pkgUomCd", pkgUomCd);

        return getSsmEZDClient().queryObject("getUomInfo", params);
    }

    /**
     * getPrcGrpNm.
     * @param value String
     * @param prcGrpTpCd String
     * @param isPk boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcGrp(String value, String prcGrpTpCd, boolean isPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        if (isPk) {
            params.put("prcGrpPk", new BigDecimal(value));
            params.put("prcGrpNm", null);
        } else {
            params.put("prcGrpPk", null);
            params.put("prcGrpNm", value);
        }
        params.put("prcGrpTpCd", prcGrpTpCd);

        return getSsmEZDClient().queryObject("getPrcGrp", params);
    }

    /**
     * getPrcContr.
     * @param bizMsg NMAL7010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcContr(NMAL7010CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcContrNum", bizMsg.prcContrNum_H1.getValue());

        return getSsmEZDClient().queryObject("getPrcContr", params);
    }

    /**
     * getPrcList.
     * @param bizMsg NMAL7010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcList(NMAL7010CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", bizMsg.prcCatgCd_BK.getValue());
        params.put("prcCatgNm", bizMsg.prcCatgNm_H1.getValue());

        return getSsmEZDClient().queryObject("getPrcList", params);
    }

    /**
     * getPrcFmlaRelation.
     * @param prcFmlaPk BigDecimalBigDecimal
     * @param prcQlfyValTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcFmlaRelation(BigDecimal prcFmlaPk, String prcQlfyValTxt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcFmlaPk", prcFmlaPk);
        params.put("prcQlfyValTxt", prcQlfyValTxt);

        return getSsmEZDClient().queryObject("getPrcFmlaRelation", params);
    }

    /**
     * getProdCtrl.
     * @param prcQlfyTpCd String
     * @param prcQlfyValTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getProdCtrl(String prcQlfyTpCd, String prcQlfyValTxt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcQlfyTpCd", prcQlfyTpCd);
        params.put("prcQlfyValTxt", prcQlfyValTxt);
        params.put("mdseTp", PRC_QLFY_TP.MERCHANDISE_TYPE);
        params.put("prodHrch1", PRC_QLFY_TP.PRODUCT_HIERARCHY_1);
        params.put("prodHrch2", PRC_QLFY_TP.PRODUCT_HIERARCHY_2);
        params.put("prodHrch3", PRC_QLFY_TP.PRODUCT_HIERARCHY_3);
        params.put("prodHrch4", PRC_QLFY_TP.PRODUCT_HIERARCHY_4);
        params.put("prodHrch5", PRC_QLFY_TP.PRODUCT_HIERARCHY_5);
        params.put("itemCd", PRC_QLFY_TP.ITEM_CODE);
        params.put("comma", COMMA);

        return getSsmEZDClient().queryObject("getProdCtrl", params);
    }

    /**
     * searchPrcListHdr.
     * @param bizMsg NMAL7010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPrcListHdr(NMAL7010CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", bizMsg.prcCatgCd_H1.getValue());

        return getSsmEZDClient().queryEZDMsg("getPrcListHdr", params, bizMsg);
    }

    /**
     * searchSubPrcList.
     * @param bizMsg NMAL7010CMsg
     * @param prcCatgList String[]
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchSubPrcList(NMAL7010CMsg bizMsg, String[] prcCatgList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgList", prcCatgList);
        // QC#2175
        return getSsmEZDClient().queryEZDMsgArray("getSubPrcList", params, bizMsg.W);
    }

    /**
     * getPrcCatgSub.
     * @param prcCatgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcCatgSub(String prcCatgCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", prcCatgCd);
        return getSsmEZDClient().queryObjectList("getPrcCatgSub", params);
    }

    /**
     * getPrcListStruTp.
     * @param bizMsg NMAL7010CMsg
     * @param prcCatgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListStruTp(NMAL7010CMsg bizMsg, String prcCatgCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", prcCatgCd);
        return getSsmEZDClient().queryObject("getPrcListStruTp", params);
    }

    /**
     * searchSubPrcListTree.
     * @param bizMsg NMAL7010CMsg
     * @param prcCatgList String[]
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchSubPrcListTree(NMAL7010CMsg bizMsg, String[] prcCatgList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgList", prcCatgList);
        params.put("subPrcCatgCd", bizMsg.prcCatgCd_BK.getValue());
        return getSsmEZDClient().queryObjectList("getSubPrcListTree", params);
    }

    /**
     * searchPrcListCustAudc.
     * @param bizMsg NMAL7010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPrcListCustAudc(NMAL7010CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", bizMsg.prcCatgCd_H1.getValue());
        params.put("pub", PRC_CUST_AUDC_CATG.PUBLIC);
        params.put("lineBizTpCd", PRC_CUST_AUDC_CATG.PUBLIC_LOB);
        params.put("dsAcctNum", PRC_CUST_AUDC_CATG.ACCT_NUM);
        params.put("coaChCd", PRC_CUST_AUDC_CATG.ACCT_CHANNEL);
        params.put("dsAcctGrpCd", PRC_CUST_AUDC_CATG.ACCT_CUSTGROUP);
        params.put("coaBrCd", PRC_CUST_AUDC_CATG.BRANCH);
        params.put("csmpNum", PRC_CUST_AUDC_CATG.CSMP_NUM);
        params.put("prcGrpPk", PRC_CUST_AUDC_CATG.CUST_PRICE_GROUP);
        params.put("custPrcGrp", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
        // 2018/08/29 QC#27695 Add Start
        params.put("rowNum", bizMsg.X.length() + 1);
        // 2018/08/29 QC#27695 Add End

        return getSsmEZDClient().queryEZDMsgArray("getPrcListCustAudc", params, bizMsg.X);
    }

    /**
     * searchPrcListCustAudc.
     * @param bizMsg NMAL7010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPrcListTrxAudc(NMAL7010CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", bizMsg.prcCatgCd_H1.getValue());
        params.put("prcGrpPk", PRC_TRX_AUDC_CATG.TRANSACTION_PRICE_GROUP);
        params.put("dsOrdCatg", PRC_TRX_AUDC_CATG.ORDER_CATEGORY);
        params.put("dsOrdTp", PRC_TRX_AUDC_CATG.ORDER_REASON);
        params.put("trxPrcGrp", PRC_GRP_TP.ORDER_CATEGORY_OR_REASON);

        return getSsmEZDClient().queryEZDMsgArray("getPrcListTrxAudc", params, bizMsg.Y);
    }

    /**
     * searchPrcListVal.
     * *) use getSearchParameter function. QC#4486 2016/3/7
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPrcListVal(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
      Map<String, Object> params = getSearchParameter(bizMsg, glblMsg);

      S21SsmEZDResult ssmEzdResult = null;

      if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
          searchPrcListCount(glblMsg, "getPrcListValEquipCount", params);
          ssmEzdResult = getSsmEZDClient().queryEZDMsgArray("getPrcListValEquip", params, glblMsg.A);
      } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
          searchPrcListCount(glblMsg, "getPrcListValSvcCount", params);
          ssmEzdResult = getSsmEZDClient().queryEZDMsgArray("getPrcListValSvc", params, glblMsg.B);
      } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
          searchPrcListCount(glblMsg, "getPrcListValSvcTierCount", params);
          ssmEzdResult = getSsmEZDClient().queryEZDMsgArray("getPrcListValSvcTier", params, glblMsg.C);
      } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
          searchPrcListCount(glblMsg, "getPrcListValSvcSpecChrgCount", params);
          ssmEzdResult = getSsmEZDClient().queryEZDMsgArray("getPrcListValSvcSpecChrg", params, glblMsg.D);
      } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
          searchPrcListCount(glblMsg, "getPrcListValSplyPgmCount", params);
          ssmEzdResult = getSsmEZDClient().queryEZDMsgArray("getPrcListValSplyPgm", params, glblMsg.E);
      } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
          searchPrcListCount(glblMsg, "getPrcListValLeaseRateCount", params);
          ssmEzdResult = getSsmEZDClient().queryEZDMsgArray("getPrcListValLeaseRate", params, glblMsg.F);
      } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
          searchPrcListCount(glblMsg, "getPrcListValLeaseRtrnFeeCount", params);
          ssmEzdResult = getSsmEZDClient().queryEZDMsgArray("getPrcListValLeaseRtrnFee", params, glblMsg.G);
      } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
          searchPrcListCount(glblMsg, "getPrcListValTradeInCount", params);
          ssmEzdResult = getSsmEZDClient().queryEZDMsgArray("getPrcListValTradeIn", params, glblMsg.H);
      } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
          searchPrcListCount(glblMsg, "getPrcListValQtyDiscCount", params);
          ssmEzdResult = getSsmEZDClient().queryEZDMsgArray("getPrcListValQtyDisc", params, glblMsg.I);
      }

      return ssmEzdResult;
    }
    /**
     * searchPrcListVal.
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPrcListQtyDtl(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", bizMsg.prcCatgCd_H1.getValue());
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("prcDispRecTpCd", bizMsg.prcDispRecTpCd_DH.getValue());
        params.put("flgN", ZYPConstant.FLG_OFF_N);

        return getSsmEZDClient().queryEZDMsgArray("getPrcListValQtyDiscDtl", params, glblMsg.J);
    }

    /**
     * // QC#2174 Add
     * searchPrcListEquipDtl.
     * @param bizMsg NMAL7010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPrcListEquipDtl(NMAL7010CMsg bizMsg, BigDecimal prcListEquipPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcListEquipPk", prcListEquipPk);

        return getSsmEZDClient().queryEZDMsgArray("getPrcListEquipDtl", params, bizMsg.Q);
    }

    /**
     * getCodeTable.
     * @param tblNm String
     * @param cdNm String
     * @param nmFlg String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCodeTableCodeValue(String tblNm, String cdNm, String nmFlg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("tblNm", tblNm);
        params.put("cdNm", cdNm);
        params.put("nmFlg", nmFlg);

        return getSsmEZDClient().queryObject("getCodeTableCodeValue", params);
    }

    /**
     * getPrcCatgMaxSortNum.
     * @param bizMsg NMAL7010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcCatgMaxSortNum(NMAL7010CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObject("getPrcCatgMaxSortNum", params);
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

    /**
     * getAnyColmn.
     * @param colNm String
     * @param tblNm String
     * @param whereCol String
     * @param whereVal BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAnyColmn(String colNm, String tblNm, String whereCol, BigDecimal whereVal) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("colNm", colNm);
        params.put("tblNm", tblNm);
        params.put("whereCol", whereCol);
        params.put("whereVal", whereVal);

        return getSsmEZDClient().queryObject("getAnyColmn", params);
    }

    /**
     * getMdseInfo.
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseInfo(String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getMdseInfo", params);
    }

    /**
     * getCustAudcCatgAll.
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCustAudcCatgAll() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getCustAudcCatg", params);
    }

    /**
     * getTrxAudcCatgAll.
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrxAudcCatgAll() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getTrxAudcCatg", params);
    }

    /**
     * getPrcQlfyTpAll.
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcQlfyTpAll() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getPrcQlfyTp", params);
    }

    /**
     * getPrcMtrPkgBllgMtr.
     * @param prcMtrPkgPk BigDecimal
     * @param mtrLbCd String
     * @param mdlId BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcMtrPkgBllgMtr(BigDecimal prcMtrPkgPk, String mtrLbCd, BigDecimal mdlId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcMtrPkgPk", prcMtrPkgPk);
        params.put("mtrLbCd", mtrLbCd);
        params.put("mdlId", mdlId);
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("highValDt", HIGH_VAL_DT);

        return getSsmEZDClient().queryObject("getPrcMtrPkgBllgMtr", params);
    }
    
    /**
     * getBllgMtrLb.
     * @param bllgMtrLbNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgMtrLb(String bllgMtrLbNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("bllgMtrLbNm", bllgMtrLbNm);
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObject("getBllgMtrLb", params);
    }

    // 2018/11/17 QC#29147 Add Start
    /**
     * getPrcListBand.
     * @param prcListBandDescTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListBand(String prcListBandDescTxt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcListBandDescTxt", prcListBandDescTxt);

        return getSsmEZDClient().queryObject("getPrcListBand", params);
    }
    // 2018/11/17 QC#29147 Add End
    /**
     * getPrcListEquip.
     * @param prcCatgCd String
     * @param prcQlfyValTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListEquip(String prcCatgCd, String prcQlfyValTxt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", prcCatgCd);
        params.put("prcQlfyValTxt", prcQlfyValTxt);

        return getSsmEZDClient().queryObject("getPrcListEquip", params);
    }

    // 2015/12/22 CSA-QC#1125 Add Start
    /**
     * getPrcListEquipForDuplicateCheck
     * @param aSMsg NMAL7010_ASMsg
     * @param prcCatgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListEquipForDuplicateCheck(NMAL7010_ASMsg aSMsg, String prcCatgCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", prcCatgCd);
        params.put("prcListEquipPk", aSMsg.prcListEquipPk_PA.getValue());
        params.put("prcListEquipConfigNum", aSMsg.prcListEquipConfigNum_PA.getValue());
        params.put("prcListEquipConfigNm", aSMsg.prcListEquipConfigNm_PA.getValue());
        params.put("prcQlfyTpCd", aSMsg.prcQlfyTpCd_PA.getValue());
        params.put("prcQlfyValTxt", aSMsg.prcQlfyValTxt_PA.getValue());
        params.put("pkgUomCd", aSMsg.pkgUomCd_PA.getValue());
        params.put("prcTermUomCd", aSMsg.prcTermUomCd_PA.getValue());
        params.put("prcTermAot", aSMsg.prcTermAot_PA.getValue());
        params.put("effFromDt", aSMsg.effFromDt_PA.getValue());
        String effThruDt = null;
        if (ZYPCommonFunc.hasValue(aSMsg.effThruDt_PA)) {
            effThruDt = aSMsg.effThruDt_PA.getValue();
        } else {
            effThruDt = HIGH_VAL_DT;
        }
        params.put("effThruDt", effThruDt);

        return getSsmEZDClient().queryObjectList("getPrcListEquipForDuplicateCheck", params);
    }
    /**
     * getPrcListSvcForDuplicateCheck
     * @param bSMsg NMAL7010_BSMsg
     * @param prcCatgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListSvcForDuplicateCheck(NMAL7010_BSMsg bSMsg, String prcCatgCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", prcCatgCd);
        params.put("prcListSvcPk", bSMsg.prcListSvcPk_PB.getValue());
        params.put("mdlId", bSMsg.mdlId_PB.getValue());
        params.put("prcMtrPkgPk", bSMsg.prcMtrPkgPk_PB.getValue());
        params.put("prcListMdseCd", bSMsg.prcListMdseCd_PB.getValue());
        params.put("prcSvcPlnTpCd", bSMsg.prcSvcPlnTpCd_PB.getValue());
        params.put("prcSvcContrTpCd", bSMsg.prcSvcContrTpCd_PB.getValue());
        params.put("bllgMtrLbCd", bSMsg.mtrLbCd_PB.getValue());
        params.put("prcListBandCd", bSMsg.prcListBandCd_PB.getValue());
        params.put("termFromMthAot", bSMsg.termFromMthAot_PB.getValue());
        params.put("termThruMthAot", bSMsg.termThruMthAot_PB.getValue());
        params.put("effFromDt", bSMsg.effFromDt_PB.getValue());
        String effThruDt = null;
        if (ZYPCommonFunc.hasValue(bSMsg.effThruDt_PB)) {
            effThruDt = bSMsg.effThruDt_PB.getValue();
        } else {
            effThruDt = HIGH_VAL_DT;
        }
        params.put("effThruDt", effThruDt);

        return getSsmEZDClient().queryObjectList("getPrcListSvcForDuplicateCheck", params);
    }
    /**
     * getPrcListSvcTierForDuplicateCheck
     * @param cSMsg NMAL7010_CSMsg
     * @param prcCatgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListSvcTierForDuplicateCheck(NMAL7010_CSMsg cSMsg, String prcCatgCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", prcCatgCd);
        params.put("prcListSvcTierPk", cSMsg.prcListSvcTierPk_PC.getValue());
        params.put("mdlId", cSMsg.mdlId_PC.getValue());
        params.put("prcMtrPkgPk", cSMsg.prcMtrPkgPk_PC.getValue());
        params.put("prcSvcPlnTpCd", cSMsg.prcSvcPlnTpCd_PC.getValue());
        params.put("prcSvcContrTpCd", cSMsg.prcSvcContrTpCd_PC.getValue());
        params.put("bllgMtrLbCd", cSMsg.mtrLbCd_PC.getValue());
        params.put("prcListBandCd", cSMsg.prcListBandCd_PC.getValue());
        params.put("termFromMthAot", cSMsg.termFromMthAot_PC.getValue());
        params.put("termThruMthAot", cSMsg.termThruMthAot_PC.getValue());
        params.put("effFromDt", cSMsg.effFromDt_PC.getValue());
        String effThruDt = null;
        if (ZYPCommonFunc.hasValue(cSMsg.effThruDt_PC)) {
            effThruDt = cSMsg.effThruDt_PC.getValue();
        } else {
            effThruDt = HIGH_VAL_DT;
        }
        params.put("effThruDt", effThruDt);

        return getSsmEZDClient().queryObjectList("getPrcListSvcTierForDuplicateCheck", params);
    }
    /**
     * getPrcListAddlChrgForDuplicateCheck
     * @param dSMsg NMAL7010_DSMsg
     * @param prcCatgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListAddlChrgForDuplicateCheck(NMAL7010_DSMsg dSMsg, String prcCatgCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", prcCatgCd);
        params.put("prcListAddlChrgPk", dSMsg.prcListAddlChrgPk_PD.getValue());
        params.put("mdseCd", dSMsg.mdseCd_PD.getValue());
        params.put("mktMdlSegCd", dSMsg.mktMdlSegCd_PD.getValue());
        params.put("mdlId", dSMsg.mdlId_PD.getValue());
        params.put("mdlNm", dSMsg.mdlNm_PD.getValue());
        params.put("effFromDt", dSMsg.effFromDt_PD.getValue());
        String effThruDt = null;
        if (ZYPCommonFunc.hasValue(dSMsg.effThruDt_PD)) {
            effThruDt = dSMsg.effThruDt_PD.getValue();
        } else {
            effThruDt = HIGH_VAL_DT;
        }
        params.put("effThruDt", effThruDt);

        return getSsmEZDClient().queryObjectList("getPrcListAddlChrgForDuplicateCheck", params);
    }
    /**
     * getPrcListSplyPgmForDuplicateCheck
     * @param eSMsg NMAL7010_ESMsg
     * @param prcCatgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListSplyPgmForDuplicateCheck(NMAL7010_ESMsg eSMsg, String prcCatgCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", prcCatgCd);
        params.put("prcListSplyPgmPk", eSMsg.prcListSplyPgmPk_PE.getValue());
        params.put("mdlId", eSMsg.mdlId_PE.getValue());
        params.put("prcMtrPkgPk", eSMsg.prcMtrPkgPk_PE.getValue());
        params.put("prcSvcPlnTpCd", eSMsg.prcSvcPlnTpCd_PE.getValue());
        params.put("prcSvcContrTpCd", eSMsg.prcSvcContrTpCd_PE.getValue());
        params.put("bllgMtrLbCd", eSMsg.mtrLbCd_PE.getValue());
        params.put("prcListBandCd", eSMsg.prcListBandCd_PE.getValue());
        params.put("termFromMthAot", eSMsg.termFromMthAot_PE.getValue());
        params.put("termThruMthAot", eSMsg.termThruMthAot_PE.getValue());
        params.put("effFromDt", eSMsg.effFromDt_PE.getValue());
        String effThruDt = null;
        if (ZYPCommonFunc.hasValue(eSMsg.effThruDt_PE)) {
            effThruDt = eSMsg.effThruDt_PE.getValue();
        } else {
            effThruDt = HIGH_VAL_DT;
        }
        params.put("effThruDt", effThruDt);

        return getSsmEZDClient().queryObjectList("getPrcListSplyPgmForDuplicateCheck", params);
    }
    /**
     * getPrcListLeaseRateForDuplicateCheck
     * @param fSMsg NMAL7010_FSMsg
     * @param prcCatgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListLeaseRateForDuplicateCheck(NMAL7010_FSMsg fSMsg, String prcCatgCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", prcCatgCd);
        params.put("prcListLeaseRatePk", fSMsg.prcListLeaseRatePk_PF.getValue());
        params.put("prcLeaseCmpyAbbrNm", fSMsg.prcLeaseCmpyAbbrNm_PF.getValue());
        params.put("dsAcctNm", fSMsg.dsAcctNm_PF.getValue());
        params.put("prcPgmTpCd", fSMsg.prcPgmTpCd_PF.getValue());
        params.put("prcEquipTpCd", fSMsg.prcEquipTpCd_PF.getValue());
        params.put("mdlId", fSMsg.mdlId_PF.getValue());
        params.put("termFromMthAot", fSMsg.termFromMthAot_PF.getValue());
        params.put("termThruMthAot", fSMsg.termThruMthAot_PF.getValue());
        params.put("effFromDt", fSMsg.effFromDt_PF.getValue());
        String effThruDt = null;
        if (ZYPCommonFunc.hasValue(fSMsg.effThruDt_PF)) {
            effThruDt = fSMsg.effThruDt_PF.getValue();
        } else {
            effThruDt = HIGH_VAL_DT;
        }
        params.put("effThruDt", effThruDt);

        return getSsmEZDClient().queryObjectList("getPrcListLeaseRateForDuplicateCheck", params);
    }
    /**
     * getPrcListLeaseRtrnForDuplicateCheck
     * @param gSMsg NMAL7010_GSMsg
     * @param prcCatgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListLeaseRtrnForDuplicateCheck(NMAL7010_GSMsg gSMsg, String prcCatgCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", prcCatgCd);
        params.put("prcListLeaseRtrnPk", gSMsg.prcListLeaseRtrnPk_PG.getValue());
        params.put("prcLeaseCmpyAbbrNm", gSMsg.prcLeaseCmpyAbbrNm_PG.getValue());
        params.put("svcSegCd", gSMsg.svcSegCd_PG.getValue());
        params.put("prcInOutRgCd", gSMsg.prcInOutRgCd_PG.getValue());
        params.put("dstMileAmt", gSMsg.dstMileAmt_PG.getValue());
        params.put("effFromDt", gSMsg.effFromDt_PG.getValue());
        String effThruDt = null;
        if (ZYPCommonFunc.hasValue(gSMsg.effThruDt_PG)) {
            effThruDt = gSMsg.effThruDt_PG.getValue();
        } else {
            effThruDt = HIGH_VAL_DT;
        }
        params.put("effThruDt", effThruDt);

        return getSsmEZDClient().queryObjectList("getPrcListLeaseRtrnForDuplicateCheck", params);
    }
    /**
     * getPrcListTiValForDuplicateCheck
     * @param hSMsg NMAL7010_HSMsg
     * @param prcCatgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListTiValForDuplicateCheck(NMAL7010_HSMsg hSMsg, String prcCatgCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", prcCatgCd);
        params.put("prcListTiValPk", hSMsg.prcListTiValPk_PH.getValue());
        params.put("mdlId", hSMsg.mdlId_PH.getValue());
        params.put("fromMtrCopyVolCnt", hSMsg.fromMtrCopyVolCnt_PH.getValue());
        params.put("thruMtrCopyVolCnt", hSMsg.thruMtrCopyVolCnt_PH.getValue());
        params.put("effFromDt", hSMsg.effFromDt_PH.getValue());
        String effThruDt = null;
        if (ZYPCommonFunc.hasValue(hSMsg.effThruDt_PH)) {
            effThruDt = hSMsg.effThruDt_PH.getValue();
        } else {
            effThruDt = HIGH_VAL_DT;
        }
        params.put("effThruDt", effThruDt);

        return getSsmEZDClient().queryObjectList("getPrcListTiValForDuplicateCheck", params);
    }
    /**
     * getPrcListQtyDiscForDuplicateCheck
     * @param bizMsg NMAL7010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListQtyDiscForDuplicateCheck(NMAL7010CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", bizMsg.prcCatgCd_BK.getValue());

        return getSsmEZDClient().queryObjectList("getPrcListQtyDiscForDuplicateCheck", params);
    }
    // 2015/12/22 CSA-QC#1125 Add End
    
    /**
     * getMtrLb.
     * @param mtrLbNm
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMtrLb(String mtrLbNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mtrLbNm", mtrLbNm);

        return getSsmEZDClient().queryObject("getMtrLb", params);
    }

    public void searchPrcListCount(NMAL7010SMsg glblMsg, String sqlId, Map<String, Object> params) {
        String key = "rowNum";
        Integer rowNum = (Integer) params.get(key);

        params.remove(key);

        PrcListCntResultSetHandler obj = new PrcListCntResultSetHandler(glblMsg);
        S21SsmBatchClient.getClient(getClass()).queryObject(sqlId, params, obj);
        

        if (rowNum != null) {
            params.put(key, (int) rowNum);
        }
    }

    private static final class PrcListCntResultSetHandler extends S21SsmVoidResultSetHandlerSupport {
        /** shared message */
        private final NMAL7010SMsg glblMsg;

        public PrcListCntResultSetHandler(NMAL7010SMsg glblMsg) {
            this.glblMsg = glblMsg;
        }

        @Override
        protected void doProcessQueryResult(ResultSet rs) throws SQLException {

            rs.beforeFirst();
            if (rs.next()) {
                BigDecimal count = rs.getBigDecimal(1);
                this.glblMsg.xxTotCnt_SM.setValue(count);
            }
        }
    }

    public S21SsmEZDResult reloadPrcListLine(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, String prcCatgCd, BigDecimal pk, int idx) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", prcCatgCd);
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("flgN", ZYPConstant.FLG_OFF_N);
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("highValDt", HIGH_VAL_DT);
        params.put("stsActive", STS_ACTIVE);
        params.put("stsInactive", STS_INACTIVE);
        params.put("stsDeleted", STS_DELETED);
        params.put("rowNum", 1);

        S21SsmEZDResult ssmEzdResult = null;

        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            params.put("prcQlfyTpCdItem", PRC_QLFY_TP.ITEM_CODE); // QC#7958
            params.put("prcListEquipPk", pk);
            ssmEzdResult = getSsmEZDClient().queryEZDMsg("getPrcListValEquip", params, glblMsg.A.no(idx));
            glblMsg.A.no(idx).xxModeCd_A1.clear();
        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            params.put("prcListSvcPk", pk);
            ssmEzdResult = getSsmEZDClient().queryEZDMsg("getPrcListValSvc", params, glblMsg.B.no(idx));
            glblMsg.B.no(idx).xxModeCd_B1.clear();
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            params.put("prcListSvcTierPk", pk);
            ssmEzdResult = getSsmEZDClient().queryEZDMsg("getPrcListValSvcTier", params, glblMsg.C.no(idx));
            glblMsg.C.no(idx).xxModeCd_C1.clear();
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            params.put("prcListAddlChrgPk", pk);
            ssmEzdResult = getSsmEZDClient().queryEZDMsg("getPrcListValSvcSpecChrg", params, glblMsg.D.no(idx));
            glblMsg.D.no(idx).xxModeCd_D1.clear();
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            params.put("prcListSplyPgmPk", pk);
            ssmEzdResult = getSsmEZDClient().queryEZDMsg("getPrcListValSplyPgm", params, glblMsg.E.no(idx));
            glblMsg.E.no(idx).xxModeCd_E1.clear();
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            // Mod Start 2016/08/03 QC#12165
//            params.put("prcListLeaseLatePk", pk);
            params.put("prcListLeaseRatePk", pk);
            // Mod End 2016/08/03 QC#12165
            ssmEzdResult = getSsmEZDClient().queryEZDMsg("getPrcListValLeaseRate", params, glblMsg.F.no(idx));
            glblMsg.F.no(idx).xxModeCd_F1.clear();
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            params.put("prcListLeaseRtrnPk", pk);
            ssmEzdResult = getSsmEZDClient().queryEZDMsg("getPrcListValLeaseRtrnFee", params, glblMsg.G.no(idx));
            glblMsg.G.no(idx).xxModeCd_G1.clear();
        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
            params.put("prcListTiValPk", pk);
            ssmEzdResult = getSsmEZDClient().queryEZDMsg("getPrcListValTradeIn", params, glblMsg.H.no(idx));
            glblMsg.H.no(idx).xxModeCd_H1.clear();
        }

        return ssmEzdResult;
      }

    /**
     * getPrcListTpList. // QC#4763
     * @param bizMsg NMAL7010CMsg
     * @param prcListStruTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListTpList(NMAL7010CMsg bizMsg, String prcListStruTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcListStruTpCd", prcListStruTpCd);
        return getSsmEZDClient().queryObjectList("getPrcListTpList", params);
    }

    /**
     * getSearchParameter.
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     * @return Map<String, Object>
     */
    public Map<String, Object> getSearchParameter(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("prcCatgCd", bizMsg.prcCatgCd_H1.getValue());
      params.put("flgY", ZYPConstant.FLG_ON_Y);
      params.put("prcDispRecTpCd", bizMsg.prcDispRecTpCd_DH.getValue());
      params.put("flgN", ZYPConstant.FLG_OFF_N);
      params.put("slsDt", ZYPDateUtil.getSalesDate());
      params.put("highValDt", HIGH_VAL_DT);
      params.put("stsActive", STS_ACTIVE);
      params.put("stsInactive", STS_INACTIVE);
      params.put("stsDeleted", STS_DELETED);

      params.put("highValTm", HIGH_VAL_TM);
      params.put("effFromDt_Z1", bizMsg.effFromDt_Z1.getValue());
      params.put("effFromDt_Z2", bizMsg.effFromDt_Z2.getValue());
      params.put("effThruDt_Z1", bizMsg.effThruDt_Z1.getValue());
      params.put("effThruDt_Z2", bizMsg.effThruDt_Z2.getValue());
      params.put("cratDt_Z1", bizMsg.cratDt_Z1.getValue());
      params.put("cratDt_Z2", bizMsg.cratDt_Z2.getValue());
      params.put("lastUpdDt_Z1", bizMsg.lastUpdDt_Z1.getValue());
      params.put("lastUpdDt_Z2", bizMsg.lastUpdDt_Z2.getValue());
      params.put("xxFullNm_Z1", bizMsg.xxFullNm_Z1.getValue());
      params.put("xxFullNm_Z2", bizMsg.xxFullNm_Z2.getValue());

      if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
          params.put("prcQlfyTpCdItem", PRC_QLFY_TP.ITEM_CODE); // QC#7958
          if (ZYPCommonFunc.hasValue(bizMsg.prcQlfyTpCd_D1)) {
              params.put("prcQlfyTpCd_D1", bizMsg.prcQlfyTpCd_D1.getValue());
          }
          if (ZYPCommonFunc.hasValue(bizMsg.xxPrcQlfyValSrchTxt_D1)) {
              // 2018/11/27 QC#29319 Mod Start
              //params.put("prcQlfyValTxt_D1", bizMsg.prcQlfyValTxt_D1.getValue());
              params.put("xxPrcQlfyValSrchTxt_D1", S21StringUtil.toStringArray(bizMsg.xxPrcQlfyValSrchTxt_D1.getValue(), COMMA));
          }
          params.put("prcListEquipConfigNum_D1", bizMsg.prcListEquipConfigNum_D1.getValue());
          params.put("prcListEquipConfigNm_D1", bizMsg.prcListEquipConfigNm_D1.getValue());
          params.put("pkgUomCd_D1", bizMsg.pkgUomCd_D1.getValue());
          params.put("mdseDescShortTxt_Z1", bizMsg.mdseDescShortTxt_Z1.getValue());
          // Mod Start 2018/11/17 QC#20267
          // 2018/11/27 QC#29319 Mod Start
          params.put("xxMnfItemCdSrchTxt_Z1", S21StringUtil.toStringArray(bizMsg.xxMnfItemCdSrchTxt_Z1.getValue(), COMMA));
          // 2018/11/27 QC#29319 Mod End
          // Mod Start 2018/11/17 QC#20267
          // Mod Start 2016/10/17 QC#15193
//          params.put("coaMdseTpNm_Z1", bizMsg.coaMdseTpNm_Z1.getValue());
          params.put("coaProjNm_Z1", bizMsg.coaProjNm_Z1.getValue());
          // Mod End 2016/10/17 QC#15193
          params.put("mdseItemTpNm_Z1", bizMsg.mdseItemTpNm_Z1.getValue());
          params.put("coaProdNm_Z1", bizMsg.coaProdNm_Z1.getValue());
          // 2018/11/27 QC#29319 Mod Start
          //params.put("t_MdlNm_Z1", bizMsg.t_MdlNm_Z1.getValue());
          params.put("xxTMdlNmSrchTxt_Z1", S21StringUtil.toStringArray(bizMsg.xxTMdlNmSrchTxt_Z1.getValue(), COMMA));
          // 2018/11/27 QC#29319 Mod End
          params.put("zerothProdCtrlCd_Z1", bizMsg.zerothProdCtrlCd_Z1.getValue());
          params.put("firstProdCtrlCd_Z1", bizMsg.firstProdCtrlCd_Z1.getValue());
          params.put("scdProdCtrlCd_Z1", bizMsg.scdProdCtrlCd_Z1.getValue());
          params.put("thirdProdCtrlCd_Z1", bizMsg.thirdProdCtrlCd_Z1.getValue());
          params.put("frthProdCtrlCd_Z1", bizMsg.frthProdCtrlCd_Z1.getValue());
          params.put("prcTermAot_Z1", bizMsg.prcTermAot_Z1.getValue());
          params.put("prcTermUomCd_Z1", bizMsg.prcTermUomCd_Z1.getValue());
          params.put("custBidQty_Z1", bizMsg.custBidQty_Z1.getValue());
          params.put("prcFmlaNm_Z1", bizMsg.prcFmlaNm_Z1.getValue());
          params.put("openMktFlg_Z1", bizMsg.openMktFlg_Z1.getValue());
          params.put("compCd_Z1", bizMsg.compCd_Z1.getValue());
          params.put("xxYesNoCd_Z1", bizMsg.xxYesNoCd_Z1.getValue());
          params.put("rowNum", NMAL7010CommonLogic.getMaxFetchSize(glblMsg, bizMsg.A, glblMsg.A));
      } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
          if (ZYPCommonFunc.hasValue(bizMsg.mdlNm_D1)) {
              params.put("mdlNm_D1", bizMsg.mdlNm_D1.getValue());
          }
          if (ZYPCommonFunc.hasValue(bizMsg.prcMtrPkgNm_D1)) {
              params.put("prcMtrPkgNm_D1", bizMsg.prcMtrPkgNm_D1.getValue());
          }
          params.put("prcListMdseCd_Z1", bizMsg.prcListMdseCd_Z1.getValue());
          params.put("mdseDescShortTxt_Z1", bizMsg.mdseDescShortTxt_Z1.getValue());
          params.put("prcRateTpCd_D1", bizMsg.prcRateTpCd_D1.getValue());
          params.put("prcSvcPlnTpCd_D1", bizMsg.prcSvcPlnTpCd_D1.getValue());
          params.put("prcSvcContrTpCd_D1", bizMsg.prcSvcContrTpCd_D1.getValue());
          params.put("mtrLbNm_D1", bizMsg.mtrLbNm_D1.getValue());
          // 2018/11/17 QC#29147 Mod Start
          // params.put("prcListBandCd_D1", bizMsg.prcListBandCd_D1.getValue());
          params.put("prcListBandDescTxt_D1", bizMsg.prcListBandDescTxt_D1.getValue());
          // 2018/11/17 QC#29147 Mod End
          params.put("termFromMthAot_Z1", bizMsg.termFromMthAot_Z1.getValue());
          params.put("termThruMthAot_Z1", bizMsg.termThruMthAot_Z1.getValue());
          params.put("prcSvcZoneCd_Z1", bizMsg.prcSvcZoneCd_Z1.getValue());
          params.put("mdseCd_Z1", bizMsg.mdseCd_Z1.getValue());
          params.put("rowNum", NMAL7010CommonLogic.getMaxFetchSize(glblMsg, bizMsg.B, glblMsg.B));
      } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
          if (ZYPCommonFunc.hasValue(bizMsg.mdlNm_D2)) {
              params.put("mdlNm_D2", bizMsg.mdlNm_D2.getValue());
          }
          if (ZYPCommonFunc.hasValue(bizMsg.prcMtrPkgNm_D2)) {
              params.put("prcMtrPkgNm_D2", bizMsg.prcMtrPkgNm_D2.getValue());
          }
          params.put("prcSvcTierTpCd_D2", bizMsg.prcSvcTierTpCd_D2.getValue());
          params.put("prcSvcPlnTpCd_D2", bizMsg.prcSvcPlnTpCd_D2.getValue());
          params.put("prcSvcContrTpCd_D2", bizMsg.prcSvcContrTpCd_D2.getValue());
          params.put("mtrLbNm_D2", bizMsg.mtrLbNm_D2.getValue());
          // 2018/11/17 QC#29147 Mod Start
          // params.put("prcListBandCd_D2", bizMsg.prcListBandCd_D2.getValue());
          params.put("prcListBandDescTxt_D2", bizMsg.prcListBandDescTxt_D2.getValue());
          // 2018/11/17 QC#29147 Mod Start
          params.put("termFromMthAot_Z1", bizMsg.termFromMthAot_Z1.getValue());
          params.put("termThruMthAot_Z1", bizMsg.termThruMthAot_Z1.getValue());
          params.put("mdseCd_Z1", bizMsg.mdseCd_Z1.getValue());
          params.put("rowNum", NMAL7010CommonLogic.getMaxFetchSize(glblMsg, bizMsg.C, glblMsg.C));
      } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
          params.put("mdseCd_DH", bizMsg.mdseCd_DH.getValue());
          params.put("prcAddlChrgTpCd_DH", bizMsg.prcAddlChrgTpCd_DH.getValue());
          params.put("mktMdlSegCd_DH", bizMsg.mktMdlSegCd_DH.getValue());
          params.put("mdlNm_DH", bizMsg.mdlNm_DH.getValue());
          // Mod Start 2016/09/12 QC#11615
//          params.put("mdseNm_Z1", bizMsg.mdseNm_Z1.getValue());
          params.put("mdseDescShortTxt_Z2", bizMsg.mdseDescShortTxt_Z2.getValue());
          // Mod End 2016/09/12 QC#11615
          params.put("rowNum", NMAL7010CommonLogic.getMaxFetchSize(glblMsg, bizMsg.D, glblMsg.D));
      } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
          if (ZYPCommonFunc.hasValue(bizMsg.mdlNm_D3)) {
              params.put("mdlNm_D3", bizMsg.mdlNm_D3.getValue());
          }
          if (ZYPCommonFunc.hasValue(bizMsg.prcMtrPkgNm_D3)) {
              params.put("prcMtrPkgNm_D3", bizMsg.prcMtrPkgNm_D3.getValue());
          }
          params.put("prcSvcPlnTpCd_D3", bizMsg.prcSvcPlnTpCd_D3.getValue());
          params.put("prcSvcContrTpCd_D3", bizMsg.prcSvcContrTpCd_D3.getValue());
          params.put("mtrLbNm_D3", bizMsg.mtrLbNm_D3.getValue());
          // 2018/11/17 QC#29147 Mod Start
          // params.put("prcListBandCd_D3", bizMsg.prcListBandCd_D3.getValue());
          params.put("prcListBandDescTxt_D3", bizMsg.prcListBandDescTxt_D3.getValue());
          // 2018/11/17 QC#29147 Mod End
          params.put("splyAgmtPlnNm_D3", bizMsg.splyAgmtPlnNm_D3.getValue());
          params.put("termFromMthAot_Z1", bizMsg.termFromMthAot_Z1.getValue());
          params.put("termThruMthAot_Z1", bizMsg.termThruMthAot_Z1.getValue());
          params.put("prcSvcZoneCd_Z1", bizMsg.prcSvcZoneCd_Z1.getValue());
          params.put("mdseCd_Z1", bizMsg.mdseCd_Z1.getValue());
          params.put("rowNum", NMAL7010CommonLogic.getMaxFetchSize(glblMsg, bizMsg.E, glblMsg.E));
      } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
          if (ZYPCommonFunc.hasValue(bizMsg.mdlNm_D4)) {
              params.put("mdlNm_D4", bizMsg.mdlNm_D4.getValue());
          }
          if (ZYPCommonFunc.hasValue(bizMsg.prcEquipTpCd_DH)) {
              params.put("prcEquipTpCd_DH", bizMsg.prcEquipTpCd_DH.getValue());
          }
          if (ZYPCommonFunc.hasValue(bizMsg.prcPgmTpCd_DH)) {
              params.put("prcPgmTpCd_DH", bizMsg.prcPgmTpCd_DH.getValue());
          }
          params.put("prcLeaseCmpyAbbrNm_D4", bizMsg.prcLeaseCmpyAbbrNm_D4.getValue());
          params.put("dsAcctNm_D4", bizMsg.dsAcctNm_D4.getValue());
          params.put("termFromMthAot_Z1", bizMsg.termFromMthAot_Z1.getValue());
          params.put("termThruMthAot_Z1", bizMsg.termThruMthAot_Z1.getValue());
          params.put("rowNum", NMAL7010CommonLogic.getMaxFetchSize(glblMsg, bizMsg.F, glblMsg.F));
      } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
          if (ZYPCommonFunc.hasValue(bizMsg.svcSegCd_DH)) {
              params.put("svcSegCd_DH", bizMsg.svcSegCd_DH.getValue());
          }
          if (ZYPCommonFunc.hasValue(bizMsg.prcInOutRgCd_DH)) {
              params.put("prcInOutRgCd_DH", bizMsg.prcInOutRgCd_DH.getValue());
          }
          params.put("prcLeaseCmpyAbbrNm_D5", bizMsg.prcLeaseCmpyAbbrNm_D5.getValue());
          params.put("rowNum", NMAL7010CommonLogic.getMaxFetchSize(glblMsg, bizMsg.G, glblMsg.G));
      } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
          if (ZYPCommonFunc.hasValue(bizMsg.mdlNm_D5)) {
              params.put("mdlNm_D5", bizMsg.mdlNm_D5.getValue());
          }
          params.put("rowNum", NMAL7010CommonLogic.getMaxFetchSize(glblMsg, bizMsg.H, glblMsg.H));
      } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
          if (ZYPCommonFunc.hasValue(bizMsg.prcQlfyTpCd_D2)) {
              params.put("prcQlfyTpCd_D2", bizMsg.prcQlfyTpCd_D2.getValue());
          }
          if (ZYPCommonFunc.hasValue(bizMsg.prcQlfyValTxt_D2)) {
              params.put("prcQlfyValTxt_D2", bizMsg.prcQlfyValTxt_D2.getValue());
          }
          params.put("rowNum", NMAL7010CommonLogic.getMaxFetchSize(glblMsg, bizMsg.I, glblMsg.I));
      }

      return params;
    }

    // Add #4767 2016/03/22 Start
    /**
     * getProductLevelName
     * @param bizMsg NMAL7010CMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getProductLevelName(NMAL7010CMsg bizMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getProductLevelName", ssmParam);
    }
    // Add #4767 2016/03/22 End

    /**
     * downloadPrcListVal.
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     * @return S21SsmEZDResult
     */
    public void downloadPrcListVal(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, NMAL7010CMsg hdrMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        Map<String, Object> params = getSearchParameter(bizMsg, glblMsg);
        params.remove("rowNum");
        params.put("isDownload", ZYPConstant.FLG_ON_Y);

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NMAL7010Constant.DOWNLOAD_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());

            if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
                ps = ssmLLClient.createPreparedStatement("getPrcListValEquip", params, execParam);
            } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
                ps = ssmLLClient.createPreparedStatement("getPrcListValSvc", params, execParam);
            } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
                ps = ssmLLClient.createPreparedStatement("getPrcListValSvcTier", params, execParam);
            } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
                ps = ssmLLClient.createPreparedStatement("getPrcListValSvcSpecChrg", params, execParam);
            } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
                ps = ssmLLClient.createPreparedStatement("getPrcListValSplyPgm", params, execParam);
            } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
                ps = ssmLLClient.createPreparedStatement("getPrcListValLeaseRate", params, execParam);
            } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
                ps = ssmLLClient.createPreparedStatement("getPrcListValLeaseRtrnFee", params, execParam);
            } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
                ps = ssmLLClient.createPreparedStatement("getPrcListValTradeIn", params, execParam);
            }

            rs = ps.executeQuery();

            NMAL7010CommonLogic.writeCsvFile(bizMsg, rs, getGlobalCompanyCode(), hdrMsg);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    // QC#7080
    public S21SsmEZDResult getPrcCatgCd(String prcCatgNm, String prcListStruTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("prcCatgNm", prcCatgNm);
        ssmParam.put("prcListStruTpCd", prcListStruTpCd);
        return getSsmEZDClient().queryObject("getPrcCatgCd", ssmParam);
    }

    // QC#688
    public S21SsmEZDResult getSvcItemTpList(String itemTpCtxTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("itemTpCtxTpCd", itemTpCtxTpCd);
        return getSsmEZDClient().queryObjectList("getSvcItemTpList", ssmParam);
    }

    // Add Start 2016/08/26 QC#3934
    /**
     * getEquipmentMdseList.
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getEquipmentMdseList(String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", mdseCd);
        params.put("rowNum", MAX_ROW_EQUIPMENT);

        return getSsmEZDClient().queryObjectList("getEquipmentMdseInfo", params);
    }

    // Add Start 2018/07/17 QC#20267
    /**
     * getEquipmentMdseList.
     * @param mnfItemCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getEquipmentMnfItemList(String mnfItemCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mnfItemCd", mnfItemCd);

        return getSsmEZDClient().queryObjectList("getEquipmentMdseInfo", params);
    }
    // Add End 2018/07/17 QC#20267

    /**
     * getMdseInfo.
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPriceMdseInfo(String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", mdseCd);
        params.put("rowNum", MAX_ROW_EQUIPMENT);

        return getSsmEZDClient().queryObjectList("getPriceMdseInfo", params);
    }
    // Add Start 2016/08/26 QC#3934
    // Add start 2016/09/21 QC#13252
    /**
     * getSplyAgmtPln.
     * @param splyAgmtPlnPk BigDecimal
     * @return boolean
     */
    public boolean getSplyAgmtPln(BigDecimal splyAgmtPlnPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("highValDt", HIGH_VAL_DT);
        params.put("splyAgmtPlnPk", splyAgmtPlnPk);
        params.put("flgY", ZYPConstant.FLG_ON_Y);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList("getSplyAgmtPln", params);
        if (ssmResult.isCodeNotFound()) {
            return false;
        }

        return true;
    }
    // Add end 2016/09/21 QC#13252

    // Add Start 2018/04/06 QC#22556
    /**
     * getUOMPulldownList.
     * @param splyAgmtPlnPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUOMPulldownList(String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObjectList("getUOMPulldownList", params);
    }
    // Add End 2018/04/06 QC#22556

    // 2018/05/08 QC#20269 Add Start
    /**
     * searchPrcListHdrAsTemplate.
     * @param bizMsg NMAL7010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPrcListHdrAsTemplate(NMAL7010CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", bizMsg.prcCatgCd_H1.getValue());

        return getSsmEZDClient().queryObjectList("getPrcListHdrAsTemplate", params);
    }

    /**
     * downloadAsTemplatePrcListVal.
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     * @param hdrMsg NMAL7010CMsg
     */
    public void downloadAsTemplatePrcListVal(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, NMAL7010CMsg hdrMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        Map<String, Object> params = getSearchParameter(bizMsg, glblMsg);
        params.remove("rowNum");

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());

            if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
                ps = ssmLLClient.createPreparedStatement("getPrcListValEquipAsTemplate", params, execParam);
            } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
                ps = ssmLLClient.createPreparedStatement("getPrcListValSvcAsTemplate", params, execParam);
            } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
                ps = ssmLLClient.createPreparedStatement("getPrcListValSvcTierAsTemplate", params, execParam);
            } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
                ps = ssmLLClient.createPreparedStatement("getPrcListValSvcSpecChrgAsTemplate", params, execParam);
            } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
                ps = ssmLLClient.createPreparedStatement("getPrcListValSplyPgmAsTemplate", params, execParam);
            } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
                ps = ssmLLClient.createPreparedStatement("getPrcListValLeaseRateAsTemplate", params, execParam);
            } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
                ps = ssmLLClient.createPreparedStatement("getPrcListValLeaseRtrnFeeAsTemplate", params, execParam);
            } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
                ps = ssmLLClient.createPreparedStatement("getPrcListValTradeInAsTemplate", params, execParam);
            }

            rs = ps.executeQuery();

            NMAL7010CommonLogic.writeCsvFileAsTemplate(bizMsg, rs, getGlobalCompanyCode(), hdrMsg);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }
    // 2018/05/08 QC#20269 Add End
}
