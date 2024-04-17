/**
 * <pre>
 * Other Check as CPO Update API
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/05   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 * 2017/05/09   Fujitsu         S.Takami        Update          RS#8144
 * 2017/05/16   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.1 RS#8156
 * 2017/06/06   Fujitsu         A.Kosai         Update          QC#16443
 * 2017/07/31   Fujitsu         A.Kosai         Update          S21_NA#19854
 * 2017/08/16   Fujitsu         S.Takami        Update          S21_NA#18322
 * 2017/10/20   Fujitsu         R.Nakamura      Update          S21_NA#21891
 * 2018/02/28   Fujitsu         M.Ohno          Update          S21_NA#24117
 * 2018/06/14   Fujitsu         K.Ishizuka      Update          S21_NA#24294
 * 2019/08/01   Fujitsu         M.Ohno          Update          S21_NA#52156
 * 2019/09/13   Fujitsu         R.Matsuki       Update          QC#53183
 * 2019/09/24   Fujitsu         R.Matsuki       Update          QC#53183-1
 * 2019/11/27   Fujitsu         K.Kato          Update          QC#52339
 * 2020/01/29   Fujitsu         M.Ohno          Update          S21_NA#55545
 * 2021/02/04   CITS            K.Ogino         Update          QC#58230
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.validation;

//import static com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ContractInfo.chkContractInfo;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByCondition;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByConditionForUpdate;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKey;
import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import parts.common.EZDTDateItem;
import parts.common.EZDTItem;
import parts.common.EZDTStringItem;
import business.db.AVAL_INVTY_APP_IDTMsg;
import business.db.AVAL_INVTY_APP_IDTMsgArray;
import business.db.CPO_DTLTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.MDSE_STORE_PKGTMsgArray;
import business.db.RTL_WHTMsg;
import business.db.S21_ORGTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.db.SHPG_SVC_FRT_CHRG_RELNTMsg;
import business.db.WHTMsgArray;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150002PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Common;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouFindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.logWriter.NWZC150001CpouLogWriter;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

public class NWZC150001CpouOtherCheck {

    S21SsmBatchClient ssmClient = null;

    /**
     * Singleton instance.
     */
    private static final NWZC150001CpouOtherCheck MY_INSTANCE = new NWZC150001CpouOtherCheck();

    /**
     * Constructor.
     */
    private NWZC150001CpouOtherCheck() {
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    public static NWZC150001CpouOtherCheck getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * Do Other check (CPO Update API otherCheckBase)
     * </pre>
     * @param cpouBean
     * @param pMsg
     * @param resPMsgList
     * @param msgIdMgr
     * @param localCache
     * @return true: has error, false: normal end
     */
    public boolean doOtherCheck(NWZC150001CpouBean cpouBean, NWZC150001PMsg pMsg, List<NWZC150002PMsg> resPMsgList, S21ApiMessageIdMgr msgIdMgr, NWZC150001CpouLocalCache localCache) {

        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, cpouBean.getNoValidationFlg())) {
            return false;
        }
        boolean rslt = false;
        if (isOtherCheckReqTpCd(cpouBean)) {
            otherCheck(cpouBean, pMsg, resPMsgList, msgIdMgr, localCache);
            if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                rslt = true;
            }
        } else {
            // Def#2100 Cancel line is UOM check only.
            otherCheckOfUOMComplete(cpouBean, resPMsgList, localCache);
            // 1.2WDS add start -->
            otherCheckOfBOMComponentCancel(cpouBean, pMsg, resPMsgList);
            // 1.2WDS add end <--
            if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                rslt = true;
            }
        }
        return rslt;
    }
    /**
     * Other check RequestType judgment
     * 
     * <pre>
     * It is judged whether it is other Request Type that can process the check.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @return true : The check is executed. /false : The check is not
     * executed.
     */
    private boolean isOtherCheckReqTpCd(NWZC150001CpouBean cpoBean) {

        if (NWZC150001CpouConstant.CPO_CANCEL.equals(cpoBean.getRqstTpCd())) {
            return false;
        }

        if (NWZC150001CpouConstant.CPO_SAVE.equals(cpoBean.getRqstTpCd())) {
            for (NWZC150001CpouDetailBean cpoDtlBean : cpoBean.getDtlBeanList()) {
                if (NWZC150001CpouConstant.CPO_DTL_SAVE.equals(cpoDtlBean.getDtlRqstTpCd())) {
                    return true;
                }
            }
            return false;
        }

        return true;
    }

    /**
     * Other check
     * 
     * <pre>
     * ･ CustomerPO Number Check
     * ･ Payer Relation Check
     * ･ Cancel if not delivered by Check
     * ･ Cancel if not Shipped by Check
     * ･ Cancel date Check
     * ･ Merchandise check
     * ･ UOM Complete check
     * ･ Set commodity check
     * ･ Shipping Condition check
     * ･ Ship to Relation check
     * ･ Order amount check
     * ･ Order unit price check
     * ･ Check related to RDD and RSD
     * ･ Cash Discount Term Code check
     * ･ Freight Charge check
     * ･ Expense check
     * ･ Duration date check
     * ･ Bill to Internal check
     * ･ S/O,P/O Cancel check
     * ･ Intangible commodity check
     * ･ Ship Complete shipment check
     * ･ Stock Status check
     * ･ Customer check that can be sold
     * ･ Training order amount check / Training details number check
     * ･ EMBARGO check
     * ･ Shipping Mode Check
     * ･ Freight Charge Method / Cust Account check
     * ･ EDI Line Number check
     * ･ Existence Of InvtyLoc
     * ･ Check value of Customer Installable Flag
     * </pre>
     * @param pMsg NWZC150001PMsg
     * @param resPMsgList List<NWZC150002PMsg>
     */
    public void otherCheck(NWZC150001CpouBean cpouBean, NWZC150001PMsg pMsg, List<NWZC150002PMsg> resPMsgList, S21ApiMessageIdMgr msgIdMgr, NWZC150001CpouLocalCache localCache) {
        final String methodNm = "otherCheck";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            otherCheckOfPayerRelation(cpoBean, pMsg);

//            otherCheckOfCancelDate(cpoBean, pMsg);
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End

            // 2017/03/29 S21_NA#Review structure Lv.1 Del End
//            otherCheckOfAddDslpCashDiscountTerm(cpoBean, pMsg);
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End

            otherCheckOfMdse(cpouBean, resPMsgList, localCache);

            otherCheckOfUOMComplete(cpouBean, resPMsgList, localCache);

            otherCheckOfSetCommodity(cpouBean, resPMsgList);

            otherCheckOfShippingCondition(cpouBean, resPMsgList);

            //            otherCheckOfShipToRelation(cpouBean, pMsg, resPMsgList);   2015/08/27 CSA Del

            otherCheckOfOrderAmount(cpouBean, pMsg, resPMsgList);

            otherCheckOfOrderUnitPrice(cpouBean, resPMsgList);

            otherCheckOfRddAndRsd(cpouBean, resPMsgList);

            otherCheckOfCashDiscountTerm(cpouBean, resPMsgList, localCache);

            otherCheckOfFreightCharge(cpouBean, resPMsgList, localCache);

// 2019/09/24 QC#53183-1 DEL START
//            otherCheckOfExpense(cpouBean, resPMsgList);
// 2019/09/24 QC#53183-1 DEL END

            otherCheckOfDurationDate(cpouBean, pMsg, msgIdMgr);

            // S21_NA#19854 2017/07/31 Mod Start
//            otherCheckOfBillToInternal(cpouBean, pMsg, msgIdMgr);
            otherCheckOfBillToInternal(cpouBean, pMsg, msgIdMgr, ssmClient);
            // S21_NA#19854 2017/07/31 Mod End

            otherCheckOfSOCancelAndPOCancel(cpouBean, pMsg, resPMsgList);

            otherCheckOfIntangibleCommodity(cpouBean, resPMsgList);

            otherCheckOfShipCompleteShipment(cpouBean, resPMsgList);

            // Mod Start 2017/10/20 QC#21891
//            otherCheckOfStockStatus(cpouBean, resPMsgList, localCache);
            otherCheckOfStockStatus(cpouBean, resPMsgList, localCache, ssmClient);
            // Mod End 2017/10/20 QC#21891

            otherCheckOfSellToHold(cpouBean, pMsg, msgIdMgr, localCache);

            otherCheckOfEmbargo(cpouBean, resPMsgList, localCache);

            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            otherCheckOfDslpOrderUnitPrice(cpouBean, resPMsgList);

//            otherCheckOfDslpRDDAndRSD(cpouBean, resPMsgList);

//            otherCheckOfDslpCashDiscountTerm(cpouBean, resPMsgList);

//            otherCheckOfDslpFreightCharge(cpouBean, resPMsgList);

//            otherCheckOfEdiLineNumber(cpouBean, resPMsgList);
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End

            otherCheckOfDataExistenceOfInvtyLoc(cpouBean, resPMsgList, localCache);

            // 20130131 Defect#1803 T.Nakamura Start
            //            // 1.2WDS add start -->
            //            // 20130131 Defect#509 M.Fuji Start
            //            otherCheckOfOrderAmountSoSerialRelation(cpouBean, resPMsgList);
            //            // 20130131 Defect#509 M.Fuji End
            //            // 1.2WDS add end <--
            // 20130131 Defect#1803 T.Nakamura End

            // START ADD S.Yamamoto [OM004]
            // 2018/06/14 S21_NA#24294 Mod Start
            // otherCheckAvalWarehouse(cpouBean, resPMsgList, ssmClient);
            otherCheckAvalWarehouse(cpouBean, resPMsgList, ssmClient, localCache);
            // 2018/06/14 S21_NA#24294 Mod End

            otherCheckCustInstall(cpouBean, resPMsgList);
            // END   ADD S.Yamamoto [OM004]

            // START ADD S.Yamamoto [OM003]
            otherCheckFrtCond(cpouBean, resPMsgList);
            // END   ADD S.Yamamoto [OM003]

            // START ADD M.Fuji [OM028]
            otherCheckOfBomShipCmpltCd(cpouBean, resPMsgList);

            // START   ADD M.Fuji [Defect#2549]
            // S21_NA#2846#38 otherCheckOfMainUnitReln(cpouBean, resPMsgList);
            // END   ADD M.Fuji [Defect#2549]

            otherCheckOfMainUnitQty(cpouBean, resPMsgList);

            // START DELETE S.Yamamoto [S21_NA#783]
//            otherCheckConfigNumReln(cpouBean, resPMsgList);
            // END   DELETE S.Yamamoto [S21_NA#783]

            otherCheckSupplyContrNum(cpouBean, resPMsgList);
            // END   ADD M.Fuji [OM028]

            // 2015/08/27 Del Start
            //            // START   ADD M.Fuji [Defect#2394]
            //            otherCheckOfAssetMdseOrdTypeReln(cpouBean, resPMsgList);
            //            // END   ADD M.Fuji [Defect#2394]
            // 2015/08/27 Del End

            // 2017/06/06 QC#16443 Add Start
            otherCheckIttWhCheck(cpouBean, resPMsgList);
            // 2017/06/06 QC#16443 Add End
 
            // 2019/08/01 S21_NA#52156 Add Start
            otherCheckOfPRApproved(cpouBean, resPMsgList);
            // 2019/08/01 S21_NA#52156 Add End

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//  /**
//   * <pre>
//   * Payer Relation Check
//   * </pre>
//   * @param cpoBean NWZC150001CpouBean
//   * @param pMsg NWZC150001PMsg
//   */
//  private void otherCheckOfPayerRelation(NWZC150001CpouBean cpoBean, NWZC150001PMsg pMsg) {
//
//      if (isEquals(cpoBean.getBillToCustCd(), cpoBean.getPayerCustCd()) || !hasValue(cpoBean.getPayerCustCd())) {
//          return;
//      }
//
//      if (NWZC150001CpouConstant.CPO_SAVE.equals(cpoBean.getRqstTpCd()) || NWZC150001CpouConstant.CPO_DTL_SUBMIT.equals(cpoBean.getRqstTpCd())) {
//
//          final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("005");
//          fc.addCondition("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
//          fc.addCondition("billToCustCd01", pMsg.payerCustCd.getValue());
//          fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
//
//          int dataCount = localCache.altPayerCache.getTMsgArray(fc).getValidCount();
//          if (dataCount == 0) {
//              msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0025E, pMsg);
//          }
//
//      } else {
//
//          final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("024");
//          fc.addCondition("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
//          fc.addCondition("billToCustCd01", pMsg.billToCustCd.getValue());
//          fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
//
//          int dataCount = localCache.billToCustCache.getTMsgArray(fc).getValidCount();
//          if (dataCount == 0) {
//              msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0025E, pMsg);
//          }
//      }
//  }
  // 2017/03/31 S21_NA#Review structure Lv.2 Del End

    // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//  /**
//   * <pre>
//   * Cancel date Check
//   * </pre>
//   * @param cpoBean NWZC150001CpouBean
//   * @param pMsg NWZC150001PMsg
//   */
//  private void otherCheckOfCancelDate(NWZC150001CpouBean cpoBean, NWZC150001PMsg pMsg) {
//
//      if (hasValue(cpoBean.getCancDelyLimitDt()) && hasValue(cpoBean.getCancShipLimitDt())) {
//          msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0032E, pMsg);
//      }
//  }
  // 2017/03/29 S21_NA#Review structure Lv.1 Del End

    // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//  /**
//   * Add Double Slip Cash Discount Term Code check
//   * 
//   * <pre>
//   * 
//   * </pre>
//   * @param cpoBean NWZC150001CpouBean
//   * @param pMsg NWZC150001PMsg
//   */
//  private void otherCheckOfAddDslpCashDiscountTerm(NWZC150001CpouBean cpoBean, NWZC150001PMsg pMsg) {
//
//      if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(cpoBean.getRqstTpCd())) {
//          return;
//      }
//      if (!CPO_ORD_TP.SALES.equals(cpoBean.getCpoOrdTpCd())) {
//          return;
//      }
//      if (!ZYPConstant.FLG_ON_Y.equals(cpoBean.getDslpInfoFlg())) {
//          return;
//      }
//      if (!hasValue(cpoBean.getAddDslpCashDiscTermCd())) {
//          return;
//      }
//
//      final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("001");
//      fc.addCondition("glblCmpyCd01", cpoBean.getGlblCmpyCd());
//      fc.addCondition("cashDiscTermCd01", cpoBean.getAddDslpCashDiscTermCd());
//      fc.addCondition("cashDiscTermEffFromDt01", cpoBean.getSlsDt());
//      fc.addCondition("cashDiscTermEffThruDt01", cpoBean.getSlsDt());
//
//      int dataCount = localCache.cashDiscTermCache.getTMsgArray(fc).getValidCount();
//      if (dataCount == 0) {
//          msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0052E, pMsg);
//      }
//  }
  // 2017/03/29 S21_NA#Review structure Lv.1 Del End

    /**
     * Merchandise check
     * 
     * <pre>
     * ･ Check that can be sold
     * ･ Contract check
     * ･ Component unit order check
     * ･ Country of origin check
     * </pre>
     * @param pMsg NWZC150001PMsg
     * @param resPMsgList List<NWZC150002PMsg>
     */
    @SuppressWarnings("unchecked")
    private void otherCheckOfMdse(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList, NWZC150001CpouLocalCache localCache) {

        final String rqstTpCd = cpoBean.getRqstTpCd();
        final String glblCmpyCd = cpoBean.getGlblCmpyCd();
        final String sellToCustCd = cpoBean.getSellToCustCd();
        final String billToCustCd = cpoBean.getBillToCustCd();
        final String slsDt = cpoBean.getSlsDt();

        final Map<String, Boolean> contractMap = new LinkedHashMap<String, Boolean>();

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

            final NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);

            if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(cpoDtlBean.getDtlRqstTpCd())) {
                continue;
            }

            final String shipToCustCd = cpoDtlBean.getShipToCustCd();
            final String mdseCdForMstrSrch = cpoDtlBean.getMdseCdForMstrSrch();

            // [MDSE]
            final MDSETMsg mdseMsg = NWZC150001CpouExistsCdInDbCheck.getMdse(rqstTpCd, glblCmpyCd, mdseCdForMstrSrch);

            // Check that can be sold
            if (mdseMsg != null) {
                if (ZYPConstant.FLG_ON_Y.equals(mdseMsg.sellHldFlg.getValue())) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0037E, resPMsgList, i); // The
                    // item
                    // is
                    // on
                    // selling
                    // hold.
                }
            }

            // Contract check
            final boolean isContractChk = isContractChk(contractMap, cpoBean, cpoDtlBean);
            if (isContractChk && ZYPConstant.FLG_ON_Y.equals(mdseMsg.prodAuthReqFlg.getValue())) {
                // 2017/05/09 RS#8144 Del Start
//                if (!checkContractInfo(glblCmpyCd, mdseCdForMstrSrch, shipToCustCd, sellToCustCd, billToCustCd, slsDt, localCache)) {
//                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0040E, resPMsgList, i); // The
//                    // customer
//                    // is
//                    // not
//                    // authorized
//                    // to
//                    // purchase
//                    // this
//                    // item
//                }
                // 2017/05/09 RS#8144 Del End
            }

            // // Country of origin check
            // if
            // (cpoDtlBean.getMdseCdForMstrSrch().equals(cpoDtlBean.getMdseCd()))
            // {
            // if (checkCountryOrigin(glblCmpyCd, mdseCdForMstrSrch,
            // sellToCustCd, billToCustCd)) {
            // NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0039E, resPMsgList, i); // The
            // country of origin for the specified merchandise is not
            // permitted.
            // }
            // }
            // Def#1832(Prod)
            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            boolean isCntryOriginNG = false;
//            // [ORD_TAKE_MDSE]
//            final ORD_TAKE_MDSETMsg ordTakeMdseTMsg = NWXOrdTakeMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, cpoDtlBean.getMdseCd());
//            if (ordTakeMdseTMsg == null) {
//                isCntryOriginNG = checkCountryOrigin(glblCmpyCd, cpoDtlBean.getMdseCd(), sellToCustCd, billToCustCd);
//            } else {
//                final Map<String, Object> ssmParam = new HashMap<String, Object>();
//                ssmParam.put("ordTakeMdseTMsg", ordTakeMdseTMsg);
//                ssmParam.put("readyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
//                final List<String> mdseCdList = ssmClient.queryObjectList("getMdseCdListByOrdTakeMdseCd", ssmParam);
//                if (mdseCdList.isEmpty()) {
//                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0037E, resPMsgList, i); // The
//                    // item
//                    // is
//                    // on
//                    // selling
//                    // hold.
//                } else {
//                    for (String mdseCd : mdseCdList) {
//                        isCntryOriginNG = checkCountryOrigin(glblCmpyCd, mdseCd, sellToCustCd, billToCustCd);
//                        if (!isCntryOriginNG) {
//                            break;
//                        }
//                    }
//                }
//            }
//            if (isCntryOriginNG) {
//                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0039E, resPMsgList, i); // The
//                // country
//                // of
//                // origin
//                // for
//                // the
//                // specified
//                // merchandise
//                // is
//                // not
//                // permitted.
//            }
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        }
    }

    /**
     * UOM Complete check
     * 
     * <pre>
     * When UOM complete flag is '0' when ship complete number is things except the blank, it is assumed that it makes an error.
     * When it is time when it is not 'EA' Customer UOM Code, and UOM Complete flag is '0', it is assumed that it makes an error.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     */
    public void otherCheckOfUOMComplete(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList, NWZC150001CpouLocalCache localCache) {

        String setItemLineNum = null;

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

            if (hasValue(cpoBean.getDtlBeanList().get(i).getShipCpltCd())) {
                if (ZYPConstant.FLG_OFF_N.equals(cpoBean.getDtlBeanList().get(i).getUomCpltFlg())) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0137E, resPMsgList, i);
                }
            }

            // Def#2100
            if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpoBean.getDtlBeanList().get(i).getCpoDtlLineSubNum())) {
                setItemLineNum = cpoBean.getDtlBeanList().get(i).getCpoDtlLineNum();
            } else {
                if (hasValue(setItemLineNum) && setItemLineNum.equals(cpoBean.getDtlBeanList().get(i).getCpoDtlLineNum())) {
                    continue;
                }
            }

            if (!hasValue(cpoBean.getDtlBeanList().get(i).getCustUomCd())) {
                continue;
            }
            if (PKG_UOM.EACH.equals(cpoBean.getDtlBeanList().get(i).getCustUomCd())) {
                continue;
            }
            if (ZYPConstant.FLG_OFF_N.equals(cpoBean.getDtlBeanList().get(i).getUomCpltFlg())) {
                continue;
            }
            if (hasValue(cpoBean.getDtlBeanList().get(i).getShipCpltCd())) {
                continue;
            }

            final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("001");
            fc.addCondition("glblCmpyCd01", cpoBean.getGlblCmpyCd());
            fc.addCondition("pkgUomCd01", cpoBean.getDtlBeanList().get(i).getCustUomCd());
            fc.addCondition("mdseCd01", cpoBean.getDtlBeanList().get(i).getMdseCdForMstrSrch());
            fc.addCondition("qtyPkgApvlStsCd01", APVL_STS.SUBMITTED);

            final MDSE_STORE_PKGTMsgArray resMdseStorePkgTMsgArray = localCache.mdseStorePkgCache.getTMsgArray(fc);

            BigDecimal remainder = ZERO;
            if (resMdseStorePkgTMsgArray.getValidCount() != 0) {
                MDSE_STORE_PKGTMsg resMdseStorePkgTMsg = resMdseStorePkgTMsgArray.no(NWZC150001CpouConstant.FIRST_INDEX);
                remainder = cpoBean.getDtlBeanList().get(i).getOrdQty().remainder(resMdseStorePkgTMsg.inEachQty.getValue());
            }

            if (remainder.compareTo(ZERO) != 0) {
                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0161E, resPMsgList, i);
            }
        }
    }

    /**
     * Set commodity check
     * 
     * <pre>
     * ･ Ship complete check
     *      If same Ship Complete Code is not set to the component included in the set commodity, either it is assumed that it makes an error.
     * ･ Sales condition check (unit of Set commodity)
     *      When sales terms of the Set commodity are not equal, it is assumed that it makes an error.
     * ･ Shipment condition check (unit of Ship Complete Code)
     *      When sales terms of the commodity to which ship complete code is equal are not equal, it is assumed that it makes an error.
     * ･ Shipment condition check (unit of Set commodity)
     *      When the shipment condition of the component is not equal when ship complete number of the Set commodity is set, it is assumed that it makes an error.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     */
    private static void otherCheckOfSetCommodity(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList) {

        NWZC150001CpouDetailBean setItemMsg = null;
        String beforeLineNum = null;

        // ========================================================
        // Ship complete check
        // ========================================================
        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

            NWZC150001CpouDetailBean itemMsg = cpoBean.getDtlBeanList().get(i);

            if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(itemMsg.getDtlRqstTpCd())) {
                continue;
            }

            String lineNum = itemMsg.getCpoDtlLineNum();
            if (!lineNum.equals(beforeLineNum)) {
                beforeLineNum = lineNum;
                if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(itemMsg.getCpoDtlLineSubNum())) {
                    setItemMsg = itemMsg;
                } else {
                    setItemMsg = null;
                }
            }

            if (setItemMsg == null) {
                continue;
            }

            if (hasValue(setItemMsg.getShipCpltCd())) {
                if (!setItemMsg.getShipCpltCd().equals(itemMsg.getShipCpltCd())) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0138E, resPMsgList, i);
                }
            }
        }

        // ========================================================
        // Sales condition check (unit of Set commodity)
        // ========================================================
        setItemMsg = null;
        beforeLineNum = null;

        Set<String> setItemErrSet = new HashSet<String>();
        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

            NWZC150001CpouDetailBean itemMsg = cpoBean.getDtlBeanList().get(i);

            if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(itemMsg.getDtlRqstTpCd())) {
                continue;
            }

            String lineNum = itemMsg.getCpoDtlLineNum();
            if (!lineNum.equals(beforeLineNum)) {
                beforeLineNum = lineNum;
                if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(itemMsg.getCpoDtlLineSubNum())) {
                    setItemMsg = itemMsg;
                } else {
                    setItemMsg = null;
                }
            }

            if (setItemMsg == null) {
                continue;
            }
            // The component of the checked set commodity is off the
            // subject.
            if (setItemErrSet.contains(lineNum)) {
                continue;
            }

            if (!isEqualsSalesCondition(setItemMsg, itemMsg)) {

                boolean errMsgSetEndFlg = false;

                for (int j = 0; j < cpoBean.getDtlBeanList().size(); j++) {

                    String orderLineNumber = cpoBean.getDtlBeanList().get(j).getCpoDtlLineNum();

                    if (beforeLineNum.equals(orderLineNumber)) {
                        NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0139E, resPMsgList, j);
                        errMsgSetEndFlg = true;
                    }

                    if (errMsgSetEndFlg && !beforeLineNum.equals(orderLineNumber)) {
                        break;
                    }
                }
                setItemErrSet.add(beforeLineNum);
            }
        }
    }

    /**
     * <pre>
     * Shipping Condition check
     * </pre>
     * @param detailBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     */
    private static void otherCheckOfShippingCondition(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList) {

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

            NWZC150001CpouDetailBean detailMsg = cpoBean.getDtlBeanList().get(i);

            if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(detailMsg.getDtlRqstTpCd())) {
                continue;
            }
            // An intangible commodity is not checked.
            if (ZYPConstant.FLG_ON_Y.equals(detailMsg.getIntgFlg()) && !ZYPConstant.FLG_ON_Y.equals(detailMsg.getThirdPtyVndDropYFlg())) {
                continue;
            }
            // The set commodity is not checked.
            if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(detailMsg.getCpoDtlLineSubNum())) {
                continue;
            }

            // QC#58230
            String prodCondCd = detailMsg.getProdCondCd();
            String cpoSrcTpCd = cpoBean.getCpoSrcTpCd();
            if (!(ZYPCommonFunc.hasValue(cpoSrcTpCd) && CPO_SRC_TP.DEAL_CONFIGURATOR.equals(cpoSrcTpCd) && ZYPCommonFunc.hasValue(prodCondCd))) {
                if (!hasValue(detailMsg.getInvtyLocCd())) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0047E, resPMsgList, i);
                }
            }
            if (!hasValue(detailMsg.getShpgSvcLvlCd())) {
                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0049E, resPMsgList, i);
            }
            if (!hasValue(detailMsg.getFrtChrgToCd())) {
                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0175E, resPMsgList, i);
            }
            if (!hasValue(detailMsg.getFrtChrgMethCd())) {
                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0358E, resPMsgList, i);
            }
            // START ADD S.Yamamoto [OM003]
            if (!hasValue(detailMsg.getFrtCondCd())) {
                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM1266E, resPMsgList, i);
            }
            // END   ADD S.Yamamoto [OM003]
        }
    }

    /**
     * Order amount check
     * 
     * <pre>
     * It checks it adding up Order Quantity when details of the same commodity (When Merchandise Code is equal) exist in the order as follows.
     *
     * ･ Minimum amount check that can be received an order
     *      It is assumed that it makes an error for less than minimum amount to which it can receive an order of the input order amount.
     * ･ The maximum amount check that can be received an order
     *      When the maximum amount to which it can receive an order of the input order amount is exceeded, it is assumed that it makes an error.
     * ･ Order unit check
     *      When the input order amount is different from the unit of Order, it is assumed that it makes an error.
     * ･ Order amount change check
     *      When the order amount is changed below the amount of the shipment instruction ending, it is assumed that it makes an error.
     * ･ Order amount minus check
     *      When 0 or less is input to Quantity, it is assumed that it makes an error.
     * </pre>
     * @param pMsg NWZC150001PMsg
     * @param resPMsgList List<NWZC150002PMsg>
     */
    private static void otherCheckOfOrderAmount(NWZC150001CpouBean cpoBean, NWZC150001PMsg pMsg, List<NWZC150002PMsg> resPMsgList) {

        // // Order Quantity is added up by the unit of Merchandise
        // Code.
        // // Map<MDSE_CD, ORD_QTY>
        // Map<String, BigDecimal> ordQtyMap =
        // mergeOrdQtyByMdseCd(cpoBean);
        // debug("otherCheckOfOrderAmount Total Order Quantity : " +
        // ordQtyMap);

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);

            if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(cpoDtlBean.getDtlRqstTpCd())) {
                continue;
            }

            boolean detailModify = NWZC150001CpouConstant.CPO_DTL_MODIFY.equals(cpoDtlBean.getDtlRqstTpCd());
            boolean shippingFlag = isShpgUpdateOrdTp(cpoDtlBean.getCpoOrdTpCd());
            BigDecimal ordQty = cpoDtlBean.getOrdQty();

            if (detailModify && !shippingFlag) {

                // Amount (minimum amount) acquisition of shipment
                // instruction ending (status since SO Creating)
                BigDecimal ordQtyMin = getShpgPlanAvalCancelQty(cpoBean, cpoDtlBean);

                // ========================================================
                // Order amount change check
                // ========================================================
                if (hasValue(ordQtyMin) && ordQtyMin.compareTo(ordQty) == 1) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0145E, resPMsgList, i);
                }
            }

            // ========================================================
            // Order amount minus check
            // ========================================================
            if (ZERO.compareTo(ordQty) == 1) {
                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0042E, resPMsgList, i);
            }
        }
    }

    /**
     * Order unit price check
     * 
     * <pre>
     * ･ When Unit Price*Enter Deal Net Unit Price Amount is 0 when Order Type is Sales, it is assumed that it makes an error.
     * ･ When Unit Price is a minus when Order Type is things except Sales, it is assumed that it makes an error.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     */
    private static void otherCheckOfOrderUnitPrice(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList) {

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

            NWZC150001CpouDetailBean detailMsg = cpoBean.getDtlBeanList().get(i);

            if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(detailMsg.getDtlRqstTpCd())) {
                continue;
            }

            if (!hasValue(detailMsg.getEntDealNetUnitPrcAmt())) {
                continue;
            }

            // S21_NA#2082
            // if (ZERO.compareTo(detailMsg.getEntDealNetUnitPrcAmt())
            // == 1) {
            // NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0048E, resPMsgList, i);
            // }
        }
    }

    /**
     * Check related to RDD and RSD
     * 
     * <pre>
     * ･ When both RDD and RSD are input, it is assumed that it makes an error.
     * ･ It is assumed that it makes an error when the value of RDD is a day of the past.
     * ･ It is assumed that it makes an error when the value of RSD is a day of the past.
     * ･ When Pick up has been selected with Freight Charge Method, only RSD can be set. When RDD is set, it is assumed that it makes an error.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     */
    private static void otherCheckOfRddAndRsd(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList) {

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);

            if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(cpoDtlBean.getDtlRqstTpCd())) {
                continue;
            }

            String rddDt = cpoDtlBean.getRddDt();
            // 2017/08/16 S21_NA#18322 Del Start
//            if (FRT_CHRG_METH.PICK_UP_NO_CHARGE.equals(cpoDtlBean.getFrtChrgMethCd())) {
//                if (hasValue(rddDt)) {
//                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0147E, resPMsgList, i);
//                }
//            }
            // 2017/08/16 S21_NA#18322 Del End
        }
    }

    /**
     * Cash Discount Term Code check
     * 
     * <pre>
     * When data within the input range of Cash Discount Term Code and the sales date doesn't exist, it is assumed that it makes an error.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     */
    private static void otherCheckOfCashDiscountTerm(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList, NWZC150001CpouLocalCache localCachet) {

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

            if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(cpoBean.getDtlBeanList().get(i).getDtlRqstTpCd())) {
                continue;
            }
            if (!CPO_ORD_TP.SALES.equals(cpoBean.getDtlBeanList().get(i).getCpoOrdTpCd())) {
                continue;
            }
            if (!hasValue(cpoBean.getDtlBeanList().get(i).getCashDiscTermCd())) {
                continue;
            }

            final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("001");
            fc.addCondition("glblCmpyCd01", cpoBean.getGlblCmpyCd());
            fc.addCondition("cashDiscTermCd01", cpoBean.getDtlBeanList().get(i).getCashDiscTermCd());
            fc.addCondition("cashDiscTermEffFromDt01", cpoBean.getSlsDt());
            fc.addCondition("cashDiscTermEffThruDt01", cpoBean.getSlsDt());

            int dataCount = localCachet.cashDiscTermCache.getTMsgArray(fc).getValidCount();
            if (dataCount == 0) {
                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0052E, resPMsgList, i);
            }
        }
    }

    /**
     * Freight Charge check
     * 
     * <pre>
     * ･ When things except CUSA are input to Freight Charge to in case of the cases of except sales, Order Type Code of the header assumes the error of it.
     * ･ It is assumed that it makes an error when Shipping Service Level, the mistress the combination of Freight charge to and freight charge method or not existing.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     */
    private static void otherCheckOfFreightCharge(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList, NWZC150001CpouLocalCache localCache) {

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);

            if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(cpoDtlBean.getDtlRqstTpCd())) {
                continue;
            }
            if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpoDtlBean.getCpoDtlLineSubNum())) {
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(cpoDtlBean.getIntgFlg()) && !ZYPConstant.FLG_ON_Y.equals(cpoDtlBean.getThirdPtyVndDropYFlg())) {
                continue;
            }

            String glblCmpyCd = cpoBean.getGlblCmpyCd();
            String shpgSvcLvlCd = cpoDtlBean.getShpgSvcLvlCd();
            String frtChrgToCd = cpoDtlBean.getFrtChrgToCd();
            String frtChrgMethCd = cpoDtlBean.getFrtChrgMethCd();

            final SHPG_SVC_FRT_CHRG_RELNTMsg shpgSvcFrtChrgRelnTMsg = localCache.shpgSvcFrtChrgRelnCache.getTMsgByKey(glblCmpyCd, shpgSvcLvlCd, frtChrgToCd, frtChrgMethCd);

            if (shpgSvcFrtChrgRelnTMsg == null) {
                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0151E, resPMsgList, i);

            } else {
                if (asList(CPO_ORD_TP.EXPENSE, CPO_ORD_TP.TRIAL, CPO_ORD_TP.LOAN).contains(cpoBean.getCpoOrdTpCd())) {
                    if (ZYPConstant.FLG_OFF_N.equals(shpgSvcFrtChrgRelnTMsg.trialLoanUseFlg.getValue())) {
                        NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0149E, resPMsgList, i);
                    }
                }
            }
        }
    }


    /**
     * Expense check
     * 
     * <pre>
     * ･ When COA Account Code, COA Branch Code, Channel Code,Cost Center Code, Product Code are not input
     *   when CPO Order Type of details is EXPENSE, it is assumed that it makes an error.
     * ･ When COA Account Code is not input when SALES and the price are 0, CPO Order Type of details assumes the error of it.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     */
// 2019/09/24 QC#53183-1 DEL START
//    private void otherCheckOfExpense(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList) {

//        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

//            if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(cpoBean.getDtlBeanList().get(i).getDtlRqstTpCd())) {
//                continue;
//            }
//
//            // 01/20/2010 Add
//            String cpoOrdTpCd = cpoBean.getDtlBeanList().get(i).getCpoOrdTpCd();
//            if (CPO_ORD_TP.EXPENSE.equals(cpoOrdTpCd) || CPO_ORD_TP.LOAN.equals(cpoOrdTpCd)) {

//                if (!hasValue(cpoBean.getDtlBeanList().get(i).getCoaBrCd())) {
//                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0879E, resPMsgList, i);
//                }

//                if (!hasValue(cpoBean.getDtlBeanList().get(i).getCoaChCd())) {
//                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0880E, resPMsgList, i);
//                }

//                if (!hasValue(cpoBean.getDtlBeanList().get(i).getCoaCcCd())) {
//                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0881E, resPMsgList, i);
//                }
//                if (!hasValue(cpoBean.getDtlBeanList().get(i).getCoaProdCd())) {
//                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0882E, resPMsgList, i);
//                }

//                if (hasValue(cpoBean.getDtlBeanList().get(i).getCoaBrCd()) && hasValue(cpoBean.getDtlBeanList().get(i).getCoaChCd()) && hasValue(cpoBean.getDtlBeanList().get(i).getCoaCcCd())
//                        && hasValue(cpoBean.getDtlBeanList().get(i).getCoaProdCd())) {

//                    List<String> ssmResList = getCoaGlCmbn4Seg(cpoBean.getGlblCmpyCd(), cpoBean.getDtlBeanList().get(i).getCoaProdCd(), cpoBean.getDtlBeanList().get(i).getCoaBrCd(), cpoBean.getDtlBeanList().get(i).getCoaChCd(), cpoBean
//                            .getDtlBeanList().get(i).getCoaCcCd());

//                    if (ssmResList.isEmpty()) {
//                        NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0883E, resPMsgList, i);
//                    }
//                }

//            }

//            if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpoBean.getDtlBeanList().get(i).getCpoDtlLineSubNum())) {
//                continue;
//            }

// 2019/09/13 QC#53183 DEL START
//            if (CPO_ORD_TP.EXPENSE.equals(cpoOrdTpCd) || CPO_ORD_TP.LOAN.equals(cpoOrdTpCd)) {
//                if (!hasValue(cpoBean.getDtlBeanList().get(i).getCoaAcctCd())) {
//                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0152E, resPMsgList, i);
//                }
//            }

//            if (!(CPO_ORD_TP.SALES.equals(cpoOrdTpCd) || CPO_ORD_TP.EXPENSE.equals(cpoOrdTpCd) || CPO_ORD_TP.LOAN.equals(cpoOrdTpCd))) {
//                if (hasValue(cpoBean.getDtlBeanList().get(i).getCoaAcctCd())) {
//                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0855E, resPMsgList, i);
//                }
//            }
// 2019/09/13 QC#53183 DEL END
//        }
//    }
// 2019/09/24 QC#53183-1 DEL END

    /**
     * Duration date check
     * 
     * <pre>
     * It is assumed that it makes an error when Trial Loan Duration Through Date is a day of the past.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param pMsg NWZC150001_APMsg
     */
    private static void otherCheckOfDurationDate(NWZC150001CpouBean cpoBean, NWZC150001PMsg pMsg, S21ApiMessageIdMgr msgIdMgr) {

        // TODO 2017/03/29 S21_NA#Review structure Lv.1 Need this method for CSA???
        String orderType = cpoBean.getCpoOrdTpCd();
//        if (!(CPO_ORD_TP.TRIAL.equals(orderType) || CPO_ORD_TP.LOAN.equals(orderType)) || ONBATCH_TYPE.ONLINE.equals(onBatchType)) {
        if (!(CPO_ORD_TP.TRIAL.equals(orderType) || CPO_ORD_TP.LOAN.equals(orderType))) {
            return;
        }

        String trialLoanDurnThruDt = cpoBean.getTrialLoanDurnThruDt();
        String salesDate = cpoBean.getSlsDt();
        if (hasValue(trialLoanDurnThruDt)) {
            if (ZYPDateUtil.compare(salesDate, trialLoanDurnThruDt) == 1) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0019E, pMsg);
            }
        }
    }

    /**
     * Bill to Internal check
     * 
     * <pre>
     * When Internal is specified excluding EXPENSE, CPO Order Type assumes the error of it.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param pMsg NWZC150001PMsg
     */
    // S21_NA#19854 2017/07/31 Mod Start
//    private static void otherCheckOfBillToInternal(NWZC150001CpouBean cpoBean, NWZC150001PMsg pMsg, S21ApiMessageIdMgr msgIdMgr) {
    private static void otherCheckOfBillToInternal(NWZC150001CpouBean cpoBean, NWZC150001PMsg pMsg, S21ApiMessageIdMgr msgIdMgr, S21SsmBatchClient ssmClient) {
    // S21_NA#19854 2017/07/31 Mod Start

        if (!CPO_ORD_TP.SALES.equals(cpoBean.getCpoOrdTpCd())) {
            return;
        }
        
        // S21_NA#19854 2017/07/31 Add Start
        if (isDefaultWhTmpl(cpoBean, ssmClient)) {
            return;
        }
        // S21_NA#19854 2017/07/31 Add End

        if (ZYPConstant.FLG_ON_Y.equals(cpoBean.getItrlFlg())) {
            msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0028E, pMsg);
        }
    }

    /**
     * S/O,P/O Cancel check
     * 
     * <pre>
     * When things except the amount are changed when S/O and P/O Cancel are done, it is assumed that it makes an error (It is not possible to increase it).
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param pMsg NWZC150001PMsg
     * @param resPMsgList List<NWZC150002PMsg>
     */
    private static void otherCheckOfSOCancelAndPOCancel(NWZC150001CpouBean cpoBean, NWZC150001PMsg pMsg, List<NWZC150002PMsg> resPMsgList) {
        // 1.2WDS modify start -->
        if (!NWZC150001CpouConstant.CPO_MODIFY.equals(cpoBean.getRqstTpCd())) {
            return;
        }

        // 20130131 Defect#458 M.Fuji Start
        //        List<NWZC150001CpouDetailBean> shpgStsInBOMChkList = new ArrayList<NWZC150001CpouDetailBean>();
        // 20130131 Defect#458 M.Fuji End
        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);
            // 20130131 Defect#458 M.Fuji Start
            //            if (hasValue(cpoDtlBean.getShipCpltCd())) {
            //                shpgStsInBOMChkList.add(cpoDtlBean);
            //            }
            // 20130131 Defect#458 M.Fuji End
            if (!NWZC150001CpouConstant.CPO_DTL_MODIFY.equals(cpoDtlBean.getDtlRqstTpCd())) {
                continue;
            }

            if (isShpgUpdateOrdTp(cpoDtlBean.getCpoOrdTpCd())) {
                continue;
            }

            SHPG_PLNTMsgArray shpgPlnTMsgArray;
            if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpoDtlBean.getCpoDtlLineSubNum())) {
                shpgPlnTMsgArray = getShpgPlanRelationCPOForSet(cpoBean, cpoDtlBean, pMsg, false);
            } else {
                shpgPlnTMsgArray = getShpgPlanRelationCPO(cpoBean, cpoDtlBean, pMsg, false);
            }

            boolean soFlg = false;
            String shpgStsCd = null;
            for (int j = 0; j < shpgPlnTMsgArray.getValidCount(); j++) {
                shpgStsCd = shpgPlnTMsgArray.no(j).shpgStsCd.getValue();
                if (SHPG_STS.S_OR_O_CANCELLED.equals(shpgStsCd) || SHPG_STS.P_OR_O_CANCELLED.equals(shpgStsCd)) {
                    soFlg = true;
                    break;
                }
            }
            if (!soFlg) {
                continue;
            }

            CPO_DTLTMsg reqCpoDtlTMsg = new CPO_DTLTMsg();
            setValue(reqCpoDtlTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
            setValue(reqCpoDtlTMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
            setValue(reqCpoDtlTMsg.cpoDtlLineNum, cpoDtlBean.getCpoDtlLineNum());
            setValue(reqCpoDtlTMsg.cpoDtlLineSubNum, cpoDtlBean.getCpoDtlLineSubNum());

            CPO_DTLTMsg cpoDtlTMsg = (CPO_DTLTMsg) findByKey(reqCpoDtlTMsg);

            // It makes an error if items other than Order Quantity
            // are changed.
            // 2020/01/29 S21_NA#55545 Del Start
//            if (!isChangeDataExceptForOrdQty(cpoDtlTMsg, cpoDtlBean)) {
//                if (SHPG_STS.S_OR_O_CANCELLED.equals(shpgStsCd)) {
//                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0153E, resPMsgList, i);
//                } else if (SHPG_STS.P_OR_O_CANCELLED.equals(shpgStsCd)) {
//                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0631E, resPMsgList, i);
//                }
//            }
            // 2020/01/29 S21_NA#55545 Del End

            // It makes an error if Order Quantity is increased.
            if (cpoDtlTMsg.ordQty.getValue().compareTo(pMsg.A.no(i).ordQty_A1.getValue()) == -1) {
                if (SHPG_STS.S_OR_O_CANCELLED.equals(shpgStsCd)) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0335E, resPMsgList, i);
                } else if (SHPG_STS.P_OR_O_CANCELLED.equals(shpgStsCd)) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0632E, resPMsgList, i);
                }
            }
        }

        // 20130131 Defect#458 M.Fuji Start
        // Sort by shipCpltCd(ASC)
        //        Collections.sort(shpgStsInBOMChkList, new NWZC150001CpouDetailBeanComparator());
        //
        //        boolean shpgStsChkFlg = false;
        //        String sc = "";
        //        int soPrinted = Integer.parseInt(SHPG_STS.S_OR_O_CREATING);
        //        boolean dtlReqTypNewFlg = false;
        //        for (int i = 0; i < shpgStsInBOMChkList.size(); i++) {
        //            final NWZC150001CpouDetailBean cpoDtlBean = shpgStsInBOMChkList.get(i);
        //            SHPG_PLNTMsgArray shpgPlnTMsgArray = getShpgPlanRelationCPO(cpoBean, cpoDtlBean, pMsg, false);
        //            for (int j = 0; j < shpgPlnTMsgArray.getValidCount(); j++) {
        //                String shpgStsCd = shpgPlnTMsgArray.no(j).shpgStsCd.getValue();
        //                if (soPrinted <= Integer.parseInt(shpgStsCd)) {
        //                    shpgStsChkFlg = true;
        //                }
        //            }
        //            if (CPO_DTL_SUBMIT.equals(cpoDtlBean.getDtlRqstTpCd())) {
        //                dtlReqTypNewFlg = true;
        //            }
        //            if (shpgStsChkFlg && dtlReqTypNewFlg) {
        //                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM1048E, resPMsgList, i);
        //                return;
        //            }
        //
        //            if (!sc.equals(cpoDtlBean.getShipCpltCd())) {
        //                sc = cpoDtlBean.getShipCpltCd();
        //                shpgStsChkFlg = false;
        //                dtlReqTypNewFlg = false;
        //            }
        //        }
        // 20130131 Defect#458 M.Fuji End
        // 1.2WDS modify end <--
    }

    /**
     * Intangible commodity check
     * 
     * <pre>
     * It is assumed that it makes an error when RSD is input with an intangible commodity or there is no RSD on that day.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     */
    private static void otherCheckOfIntangibleCommodity(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList) {

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

            if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(cpoBean.getDtlBeanList().get(i).getDtlRqstTpCd())) {
                continue;
            }
            if (!NWZC150001CpouConstant.CPO_DTL_SUBMIT.equals(cpoBean.getDtlBeanList().get(i).getDtlRqstTpCd())) {
                continue;
            }
            // 09/02/2009 Update
            // if
            // (ZYPConstant.FLG_OFF_N.equals(dataBean.getDetailBean().get(i).getIntgFlg())
            // &&
            // !ZYPConstant.FLG_ON_Y.equals(dataBean.getDetailBean().get(i).getThirdPtyVndDropYFlg()))
            // {
            // continue;
            // }

            if (ZYPConstant.FLG_ON_Y.equals(cpoBean.getDtlBeanList().get(i).getIntgFlg()) && !ZYPConstant.FLG_ON_Y.equals(cpoBean.getDtlBeanList().get(i).getThirdPtyVndDropYFlg())) {
                final String rsdDt = cpoBean.getDtlBeanList().get(i).getRsdDt();
                if (hasValue(rsdDt) && ZYPDateUtil.compare(rsdDt, cpoBean.getSlsDt()) != 0) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0154E, resPMsgList, i);
                }
            }
        }
    }

    /**
     * Ship Complete shipment check
     * 
     * <pre>
     * It is assumed that it makes an error when set when same Ship Complete Code as details that have already been shipped adds detailed new.
     * </pre>
     * @param daataBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     */
    private static void otherCheckOfShipCompleteShipment(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList) {
        Set<String> validAfterAllocateSts = new HashSet<String>();

        // 20121121 M.Fuji WDS Solution#101 Sales BOM Start
        validAfterAllocateSts.add(SHPG_STS.S_OR_O_PRINTED);
        validAfterAllocateSts.add(SHPG_STS.PICKED);
        validAfterAllocateSts.add(SHPG_STS.PACKED);
        validAfterAllocateSts.add(SHPG_STS.STAGED);
        validAfterAllocateSts.add(SHPG_STS.INSHED);
        validAfterAllocateSts.add(SHPG_STS.SHIPPED);
        validAfterAllocateSts.add(SHPG_STS.P_OR_O_PRINTED);
        validAfterAllocateSts.add(SHPG_STS.ARRIVED);
        validAfterAllocateSts.add(SHPG_STS.INSTALLED);
        validAfterAllocateSts.add(SHPG_STS.N_INVOICE_READY);
        validAfterAllocateSts.add(SHPG_STS.INVOICED);
        // 20121121 M.Fuji WDS Solution#101 Sales BOM End

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

            if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(cpoBean.getDtlBeanList().get(i).getDtlRqstTpCd())) {
                continue;
            }

            if (!(NWZC150001CpouConstant.CPO_MODIFY.equals(cpoBean.getRqstTpCd()) && NWZC150001CpouConstant.CPO_DTL_SUBMIT.equals(cpoBean.getDtlBeanList().get(i).getDtlRqstTpCd()))) {
                continue;
            }

            final SHPG_PLNTMsg reqShpgPlnTMsg = new SHPG_PLNTMsg();
            reqShpgPlnTMsg.setSQLID("009");
            reqShpgPlnTMsg.setConditionValue("glblCmpyCd01", cpoBean.getGlblCmpyCd());
            reqShpgPlnTMsg.setConditionValue("trxHdrNum01", cpoBean.getCpoOrdNum());
            reqShpgPlnTMsg.setConditionValue("shipCpltCd01", cpoBean.getDtlBeanList().get(i).getShipCpltCd());

            final SHPG_PLNTMsgArray shpgPlanTMsgArray = (SHPG_PLNTMsgArray) findByCondition(reqShpgPlnTMsg);

            for (int l = 0, e = shpgPlanTMsgArray.getValidCount(); l < e; l++) {
                // 20121121 M.Fuji WDS Solution#101 Sales BOM Start
                //if (isShippedSts(shpgPlanTMsgArray.no(l).shpgStsCd.getValue())) {
                if (validAfterAllocateSts.contains(shpgPlanTMsgArray.no(l).shpgStsCd.getValue())) {
                    // 20121121 M.Fuji WDS Solution#101 Sales BOM End
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0156E, resPMsgList, i);
                }
            }
        }
    }

    /**
     * Stock Status check
     * 
     * <pre>
     * When Stock Status that doesn't exist is input, it is assumed that it makes an error.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     * @param ssmClient S21SsmBatchClient
     */
    private static void otherCheckOfStockStatus(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList, NWZC150001CpouLocalCache localCache, S21SsmBatchClient ssmClient) {

        // 2016/10/25 S21_NA#4150 Add Start
        List<String> avalStkStsCdList = new ArrayList<String>(0);
        // 2016/10/27 S21_NA#4150-2 Mod Start
//        if (!ZYPCommonFunc.hasValue(cpoBean.getBizAppId())) {
//            avalStkStsCdList.add(STK_STS.GOOD);
//        }
        AVAL_INVTY_APP_IDTMsgArray resAvalInvtyAppIdTMsgArray = null;
        if (ZYPCommonFunc.hasValue(cpoBean.getBizAppId())) {
            final NWZC150001CpouFindCondition findCondition = new NWZC150001CpouFindCondition("003");
            findCondition.addCondition("glblCmpyCd01", cpoBean.getGlblCmpyCd());
            findCondition.addCondition("bizAppId01", cpoBean.getBizAppId());
            resAvalInvtyAppIdTMsgArray = localCache.avalInvtyAppIdCache.getTMsgArray(findCondition);

            if (resAvalInvtyAppIdTMsgArray == null || resAvalInvtyAppIdTMsgArray.getValidCount() == 0) {
                avalStkStsCdList.add(STK_STS.GOOD);
            } else {
                for (int i = 0; i < resAvalInvtyAppIdTMsgArray.getValidCount(); i++) {
                    AVAL_INVTY_APP_IDTMsg avalInvtyAppIdTMsg = resAvalInvtyAppIdTMsgArray.no(i);
                    if (S21StringUtil.isEquals(LOC_STS.DC_STOCK, avalInvtyAppIdTMsg.locStsCd.getValue())) {
                        avalStkStsCdList.add(avalInvtyAppIdTMsg.stkStsCd.getValue());
                    }
                }
            }
        } else {
            avalStkStsCdList.add(STK_STS.GOOD);
        }
        // 2016/10/27 S21_NA#4150-2 Mod End
        // 2016/10/25 S21_NA#4150 Add End

        // Mod Start 2017/10/20 QC#21891
        if (!isDefaultWhTmpl(cpoBean, ssmClient)) {
            for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

                if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(cpoBean.getDtlBeanList().get(i).getDtlRqstTpCd())) {
                    continue;
                }

                // 2016/10/27 S21_NA#4150-2 Add Start
                String stkStsCd = cpoBean.getDtlBeanList().get(i).getStkStsCd();
                if (!avalStkStsCdList.contains(stkStsCd)) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0248E, resPMsgList, i);
                }
            // 2016/10/27 S21_NA#4150-2 Add End
            // 2016/10/27 S21_NA#4150-2 Del Start
//            String glblCmpyCd = cpoBean.getGlblCmpyCd();
//            // String bizAppId = "NWAL0010";2016/10/25 S21_NA#4150 Del
//            String bizAppId = cpoBean.getBizAppId(); // 2016/10/25 S21_NA#4150 Add
//            String locStsCd = LOC_STS.DC_STOCK;
//            String stkStsCd = cpoBean.getDtlBeanList().get(i).getStkStsCd();
//
//            if (ZYPCommonFunc.hasValue(bizAppId)) { // 2016/10/25 S21_NA#4150 Add Condition Start
//                final AVAL_INVTY_APP_IDTMsg resAvalInvtyAppIdTMsg = localCache.avalInvtyAppIdCache.getTMsgByKey(glblCmpyCd, bizAppId, locStsCd, stkStsCd);
//    
//                if (resAvalInvtyAppIdTMsg == null) {
//                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0248E, resPMsgList, i);
//                }
//            } else {  // 2016/10/25 S21_NA#4150 Add Logic for no parameter Business Application Id
//                if (!avalStkStsCdList.contains(stkStsCd)) {
//                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0248E, resPMsgList, i);
//                }
//            }  // 2016/10/25 S21_NA#4150 Add Condition End
            // 2016/10/27 S21_NA#4150-2 Del End
            }
        }
        // Mod End 2017/10/20 QC#21891
    }

    /**
     * Customer check that can be sold
     * 
     * <pre>
     * When it is not possible to sell it, it is assumed to the specified customer that it makes an error.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param pMsg NWZC150001PMsg
     */
    private static void otherCheckOfSellToHold(NWZC150001CpouBean cpoBean, NWZC150001PMsg pMsg, S21ApiMessageIdMgr msgIdMgr, NWZC150001CpouLocalCache localCache) {

        final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("008");
        fc.addCondition("glblCmpyCd01", cpoBean.getGlblCmpyCd());
        fc.addCondition("sellToCustCd01", cpoBean.getSellToCustCd());
        fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        final SELL_TO_CUSTTMsgArray resSellToCustTMsgArray = localCache.sellToCustCache.getTMsgArray(fc);

        if (resSellToCustTMsgArray.getValidCount() == 0) {
            msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0024E, pMsg);

        } else if (ZYPConstant.FLG_ON_Y.equals(resSellToCustTMsgArray.no(NWZC150001CpouConstant.FIRST_INDEX).sellHldFlg.getValue())) {
            msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0417E, pMsg);
        }
    }

    /**
     * EMBARGO Check
     * 
     * <pre>
     * When the embargo country is specified at the shipment destination, it is assumed that it makes an error.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     */
    private static void otherCheckOfEmbargo(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList, NWZC150001CpouLocalCache localCache) {

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

            final NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);

            // 09/22/2010 add -->
            // 2017/03/29 S21_NA#Review structure Lv.1 Mod Start
//            if (ZYPConstant.FLG_ON_Y.equals(cpoDtlBean.getDslpInfoFlg()) && CPO_SRC_TP.EXPORT_ORDER_ENTRY_SCREEN.equals(cpoBean.getCpoSrcTpCd())) {
//                continue;
//            }
            if (CPO_SRC_TP.EXPORT_ORDER_ENTRY_SCREEN.equals(cpoBean.getCpoSrcTpCd())) {
                continue;
            }
            // 2017/03/29 S21_NA#Review structure Lv.1 Mod End
            // 09/22/2010 add <--

            // [S21_NA#798] modify start.
            // remove search conditioin of bill to customer.
            // final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("013");
            // fc.addCondition("billToCustCd01",
            // cpoBean.getBillToCustCd());
            final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("004");
            fc.addCondition("glblCmpyCd01", cpoBean.getGlblCmpyCd());
//            fc.addCondition("sellToCustCd01", cpoBean.getSellToCustCd());
            fc.addCondition("shipToCustCd01", cpoDtlBean.getShipToCustCd());
            // [S21_NA#798] modify start.
            // remove search conditioin of bill to customer.

            final SHIP_TO_CUSTTMsgArray resShipToCustTMsgArray = localCache.shipToCustCache.getTMsgArray(fc);

            if (resShipToCustTMsgArray.getValidCount() == 0) {
                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0023E, resPMsgList, i);

            } else if (ZYPConstant.FLG_ON_Y.equals(resShipToCustTMsgArray.no(NWZC150001CpouConstant.FIRST_INDEX).embgoFlg.getValue())) {
                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0420E, resPMsgList, i);
            }
        }
    }

    /**
     * Data Existence Of InvtyLoc
     * 
     * <pre>
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     */
    private static void otherCheckOfDataExistenceOfInvtyLoc(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList, NWZC150001CpouLocalCache localCache) {

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

            String thirdPtyVndDropFlg = cpoBean.getDtlBeanList().get(i).getThirdPtyVndDropYFlg();
            String invtyLocCd = cpoBean.getDtlBeanList().get(i).getInvtyLocCd();

            if (ZYPConstant.FLG_OFF_N.equals(thirdPtyVndDropFlg)) {
                if (hasValue(invtyLocCd)) {
                    final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("009");
                    fc.addCondition("glblCmpyCd01", cpoBean.getGlblCmpyCd());
                    fc.addCondition("whCd01", invtyLocCd);
                    boolean dataFlg = localCache.whCache.getTMsgArray(fc).getValidCount() > 0;
                    if (!dataFlg) {
                        NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0044E, resPMsgList, i);
                    } else {
                        // 2010/08/17 Defect 265
                        WHTMsgArray whTMsgArray = localCache.whCache.getTMsgArray(fc);
                        for (int j = 0; j < whTMsgArray.getValidCount(); j++) {
                            if (WH_SYS_TP.FULFILLMENT.equals(whTMsgArray.no(j).whSysTpCd.getValue())) {
                                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0947E, resPMsgList, i);
                                break;
                            }
                        }
                    }
                }
                // 2015/08/27 CSA Del Start
                //            } else {
                //                final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("009");
                //                fc.addCondition("glblCmpyCd01", cpoBean.getGlblCmpyCd());
                //                fc.addCondition("whCd01", invtyLocCd);
                //                boolean dataFlg = localCache.whCache.getTMsgArray(fc).getValidCount() > 0;
                //                if (dataFlg) {
                //                    continue;
                //                }
                //                final MDSETMsg mdseMsg = getMdse(cpoBean.getGlblCmpyCd(), cpoBean.getDtlBeanList().get(i).getMdseCdForMstrSrch());
                //                if (!hasValue(mdseMsg.vndCd)) {
                //                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0044E, resPMsgList, i);
                //                    continue;
                //                }
                //                dataFlg = mdseMsg.vndCd.getValue().equals(invtyLocCd);
                //                if (!dataFlg) {
                //                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0044E, resPMsgList, i);
                //                }
                // 2015/08/27 CSA Del END
            }
        }
    }
    // START ADD S.Yamamoto [OM004]
    // private void otherCheckAvalWarehouse(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList, S21SsmBatchClient ssmClient) {
    private void otherCheckAvalWarehouse(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList, S21SsmBatchClient ssmClient, NWZC150001CpouLocalCache localCache) {

        // START ADD M.FUJI [Defect#3223]
        HashMap<String, Integer> cntAvalWarehouseMap = new HashMap<String, Integer>();
        // END ADD M.FUJI [Defect#3223]

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {
            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);

            if (!hasValue(cpoDtlBean.getInvtyLocCd())) {
                continue;
            }

            final String glblCmpyCd = cpoBean.getGlblCmpyCd();
            final String invtyLocCd = cpoDtlBean.getInvtyLocCd();

            // 2018/06/14 S21_NA#24294 Mod Start
            // final WHTMsg whTMsg = new WHTMsg();
            // whTMsg.setSQLID("009");
            // whTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            // whTMsg.setConditionValue("whCd01", invtyLocCd);
            
            final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("009");
            fc.addCondition("glblCmpyCd01", glblCmpyCd);
            fc.addCondition("whCd01", invtyLocCd);
            boolean dataFlg = localCache.whCache.getTMsgArray(fc).getValidCount() > 0;
            if (dataFlg) {
            // if (count(whTMsg) > 0) {
            // 2018/06/14 S21_NA#24294 Mod End

                String dsOrdTpCd = cpoBean.getDSOrdTpCd();

                // START MODIFY M.FUJI [Defect#3223]
                String key = glblCmpyCd.concat(dsOrdTpCd).concat(invtyLocCd);
                int cntWH = 0;

                if (cntAvalWarehouseMap.containsKey(key)) {
                    Integer avalWarehouseCnt = (Integer) cntAvalWarehouseMap.get(key);
                    cntWH = avalWarehouseCnt.intValue();
                } else {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("glblCmpyCd", glblCmpyCd);
                    map.put("dsOrdTpCd", dsOrdTpCd);
                    map.put("invtyLocCd", invtyLocCd);

                    cntWH = (Integer) this.ssmClient.queryObject("cntAvalWarehouse", map);
                    cntAvalWarehouseMap.put(key, cntWH);
                }
                // END MODIFY M.FUJI [Defect#3223]

                if (cntWH <= 0) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM1262E, resPMsgList, i);
                }
            }
        }
    }

    private void otherCheckCustInstall(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList) {

        DS_ORD_TPTMsg dsOrdTpTMsg = new DS_ORD_TPTMsg();
        dsOrdTpTMsg.glblCmpyCd.setValue(cpoBean.getGlblCmpyCd());
        dsOrdTpTMsg.dsOrdTpCd.setValue(cpoBean.getDSOrdTpCd());

        dsOrdTpTMsg = (DS_ORD_TPTMsg) findByKey(dsOrdTpTMsg);

        // START   DELETE M.Fuji [OM028]
        //        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {
        //            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);
        //            if (!hasValue(cpoDtlBean.getCustIstlFlg())) {
        //                cpoDtlBean.setCustIstlFlg(dsOrdTpTMsg.custIstlFlg.getValue());
        //                if (!ZYPConstant.FLG_ON_Y.equals(dsOrdTpTMsg.custIstlFlg.getValue())) {
        //                    DS_MDSE_INFOTMsg dsMdseTMsg = new DS_MDSE_INFOTMsg();
        //                    dsMdseTMsg.glblCmpyCd.setValue(cpoBean.getGlblCmpyCd());
        //                    dsMdseTMsg.mdseCd.setValue(cpoDtlBean.getMdseCdForMstrSrch());
        //                    dsMdseTMsg = (DS_MDSE_INFOTMsg) findByKeyWithCache(dsMdseTMsg);
        //                    if (dsMdseTMsg != null) {
        //                        cpoDtlBean.setCustIstlFlg(dsMdseTMsg.custIstlFlg.getValue());
        //                    }
        //                }
        //            }
        //        }
        // END   DELETE M.Fuji [OM028]

        if (!ZYPConstant.FLG_ON_Y.equals(dsOrdTpTMsg.custIstlFlg.getValue())) {
            return;
        }

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {
            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);

            if (ZYPConstant.FLG_OFF_N.equals(cpoDtlBean.getCustIstlFlg())) {
                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM1263E, resPMsgList, i);
            }
        }
    }
    // START ADD S.Yamamoto [OM003]
    private void otherCheckFrtCond(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList) {

        DS_ORD_TPTMsg dsOrdTpTMsg = new DS_ORD_TPTMsg();
        dsOrdTpTMsg.glblCmpyCd.setValue(cpoBean.getGlblCmpyCd());
        dsOrdTpTMsg.dsOrdTpCd.setValue(cpoBean.getDSOrdTpCd());

        dsOrdTpTMsg = (DS_ORD_TPTMsg) findByKey(dsOrdTpTMsg);

        // START ADD M.FUJI [Defect#3223]
        HashMap<String, Integer> cntFrtCondCdMap = new HashMap<String, Integer>();
        // END ADD M.FUJI [Defect#3223]

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {
            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);
            if (!hasValue(cpoDtlBean.getFrtCondCd())) {
                continue;
            }

            // START MDIFY M.FUJI [Defect#3223]
            String key = cpoBean.getGlblCmpyCd().concat(dsOrdTpTMsg.dsOrdCatgCd.getValue()).concat(cpoDtlBean.getFrtCondCd());
            int cntCd = 0;

            if (cntFrtCondCdMap.containsKey(key)) {
                Integer avalWarehouseCnt = (Integer) cntFrtCondCdMap.get(key);
                cntCd = avalWarehouseCnt.intValue();
            } else {
                Map<String, String> map = new HashMap<String, String>();
                map.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
                map.put("dsOrdCatgCd", dsOrdTpTMsg.dsOrdCatgCd.getValue());
                map.put("frtCondCd", cpoDtlBean.getFrtCondCd());
                cntCd = (Integer) ssmClient.queryObject("cntFrtCondCd", map);

                cntFrtCondCdMap.put(key, cntCd);
            }
            // END MDIFY M.FUJI [Defect#3223]

            if (cntCd <= 0) {
                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM1265E, resPMsgList, i);
            }
        }
    }
    // END ADD S.Yamamoto [OM003]

    private void otherCheckOfBomShipCmpltCd(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList) {

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {
            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);
            String targetPosNum = cpoDtlBean.getDsOrdPosnNum();

            if (!ZYPCommonFunc.hasValue(targetPosNum)) {
                continue;
            }

            if (isBomHdrOrBomBaseCmpt(cpoBean, cpoDtlBean)) {

                if (!hasValue(cpoDtlBean.getShipCpltCd())) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM1291E, resPMsgList, i);
                }
            }
        }
    }

    /**
     * <pre>
     * otherCheckOfMainUnitQty
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     * @param beforeSMsgSize int
     */
    private void otherCheckOfMainUnitQty(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList) {

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {
            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);

            if (!ZYPCommonFunc.hasValue(cpoDtlBean.getSvcConfigMstrPk())) { // 2018/02/28 S21_NA#24117 add
                if (ZYPConstant.FLG_ON_Y.equals(cpoDtlBean.getBaseCmptFlg()) && BigDecimal.ONE.compareTo(cpoDtlBean.getOrdQty()) != 0) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM1298E, resPMsgList, i);
                }
            }
        }
    }

    /**
     * <pre>
     * otherCheckConfigContrNum
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     * @param beforeSMsgSize int
     */
    private void otherCheckSupplyContrNum(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList) {

        String glblCmpyCd = cpoBean.getGlblCmpyCd();

        DS_ORD_TPTMsg dsOrdTpTMsg = new DS_ORD_TPTMsg();
        dsOrdTpTMsg.glblCmpyCd.setValue(glblCmpyCd);
        dsOrdTpTMsg.dsOrdTpCd.setValue(cpoBean.getDSOrdTpCd());

        dsOrdTpTMsg = (DS_ORD_TPTMsg) findByKey(dsOrdTpTMsg);

        if (dsOrdTpTMsg == null) {
            return;
        }

        if (!dsOrdTpTMsg.splyContrChkFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            return;
        }

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {
            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);

            if (hasValue(cpoDtlBean.getSvcConfigMstrPk()) && hasValue(cpoDtlBean.getDsContrNum())) {

                // 2017/05/16 S21_NA#Review structure Lv.1 RS#8156 Del Start
//                String dsContrNum = cpoDtlBean.getDsContrNum();
//                String dsContrSqNum = cpoDtlBean.getDsContrSqNum();
//                String splyMdseCd = cpoDtlBean.getMdseCd();
//                BigDecimal svcConfigMstrPk = cpoDtlBean.getSvcConfigMstrPk();
//                String sellToCustCd = cpoBean.getSellToCustCd();
//
//                NWXC100001CheckSplyContr checkSplyContr = new NWXC100001CheckSplyContr();
//                NWXC100001CheckSplyContrBean checkSplyContrBean = checkSplyContr.checkSplyContr(glblCmpyCd, dsContrNum, dsContrSqNum, splyMdseCd, svcConfigMstrPk, sellToCustCd);
//
//                if (checkSplyContrBean != null) {
//                    cpoDtlBean.setDsContrSqNum(checkSplyContrBean.getDsContrSqNum());
//                } else {
//                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM1301E, resPMsgList, i);
//                }
                // 2017/05/16 S21_NA#Review structure Lv.1 RS#8156 Del End

            } else {
                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM1304E, resPMsgList, i);
            }
        }
    }

// 2017/06/06 QC#16443 Add Start
    /**
     * <pre>
     * otherCheckIttWhCheck
     * </pre>
     * @param cpouBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     */
    private void otherCheckIttWhCheck(NWZC150001CpouBean cpouBean, List<NWZC150002PMsg> resPMsgList) {

        String dsWhCd = ZYPCodeDataUtil.getVarCharConstValue("DROP_SHIP_RTL_WH_CD", cpouBean.getGlblCmpyCd());
        if (!ZYPCommonFunc.hasValue(dsWhCd)) {
            dsWhCd = "DS";
        }

        for (int i = 0; i < cpouBean.getDtlBeanList().size(); i++) {

            final NWZC150001CpouDetailBean cpouDtlBean = cpouBean.getDtlBeanList().get(i); 

            if (ORD_LINE_SRC.ITT_DROP_SHIP.equals(cpouDtlBean.getOrdLineSrcCd())) {
                if (ZYPCommonFunc.hasValue(cpouDtlBean.getRtlWhCd()) && !dsWhCd.equals(cpouDtlBean.getRtlWhCd())) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM2229E, resPMsgList, i);
                }
            }

            if (ORD_LINE_SRC.ITT_LOCAL_SHIP.equals(cpouDtlBean.getOrdLineSrcCd())) {

                RTL_WHTMsg tMsg = new RTL_WHTMsg();
                setValue(tMsg.glblCmpyCd, cpouDtlBean.getGlblCmpyCd());
                setValue(tMsg.rtlWhCd, cpouDtlBean.getRtlWhCd());
                RTL_WHTMsg rtlWhTMsg = (RTL_WHTMsg) S21CacheTBLAccessor.findByKey(tMsg);

                if (rtlWhTMsg != null && !INVTY_ACCT.INVENTORY.equals(rtlWhTMsg.invtyAcctCd.getValue())) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM2230E, resPMsgList, i);
                }
            }
        }
    }
// 2017/06/06 QC#16443 Add End

    @SuppressWarnings("unchecked")
    private boolean isContractChk(Map<String, Boolean> contractMap, NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean) {

        // cache
        final StringBuilder cacheKeySb = new StringBuilder();
        cacheKeySb.append(cpoBean.getGlblCmpyCd()).append(",");
        cacheKeySb.append(HLD_RSN.CONTRACT_RESTRICTION).append(",");
        cacheKeySb.append(cpoBean.getCpoSrcTpCd()).append(",");
        cacheKeySb.append(cpoBean.getCpoOrdTpCd()).append(",");
        cacheKeySb.append(cpoDtlBean.getSlsRepOrSlsTeamTocCd());

        final String cacheKey = cacheKeySb.toString();

        if (contractMap.containsKey(cacheKey)) {
            return contractMap.get(cacheKey);
        }

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ssmParam.put("hldRsnCd", HLD_RSN.CONTRACT_RESTRICTION);
        ssmParam.put("ordSrcTpCd", cpoBean.getCpoSrcTpCd());
        ssmParam.put("ordTpCd", cpoBean.getCpoOrdTpCd());
        int cnt = (Integer) ssmClient.queryObject("isContractInfo", ssmParam);
        if (cnt == 0) {
            contractMap.put(cacheKey, false);
            return false;
        }

        ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ssmParam.put("hldRsnCd", HLD_RSN.CONTRACT_RESTRICTION);
        List<Map<String, String>> hldCtrlOrgList = ssmClient.queryObjectList("getHldCtrlOrgList", ssmParam);
        if (hldCtrlOrgList == null) {
            contractMap.put(cacheKey, false);
            return false;
        }

        if (isHldCtrlOrg(cpoDtlBean, hldCtrlOrgList)) {
            contractMap.put(cacheKey, true);
            return true;
        }

        contractMap.put(cacheKey, false);
        return false;
    }

    // 2017/05/09 RS#8144 Del Start
//    private static boolean checkContractInfo(String glblCmpyCd, String mdseCd, String shipToCustCd, String sellToCustCd, String billToCustCd, String slsDt, NWZC150001CpouLocalCache localCache) {
//
//        // cache
//        final StringBuilder cacheKeySb = new StringBuilder();
//        cacheKeySb.append(glblCmpyCd).append(",");
//        cacheKeySb.append(mdseCd).append(",");
//        cacheKeySb.append(shipToCustCd).append(",");
//        cacheKeySb.append(sellToCustCd).append(",");
//        cacheKeySb.append(billToCustCd).append(",");
//        cacheKeySb.append(slsDt);
//
//        final String cacheKey = cacheKeySb.toString();
//
//        Boolean checkRes = localCache.contractCheckResultCache.get(cacheKey);
//        if (checkRes == null) {
//            checkRes = chkContractInfo(glblCmpyCd, mdseCd, shipToCustCd, sellToCustCd, billToCustCd, HLD_RSN.CONTRACT_RESTRICTION, null, slsDt);
//            localCache.contractCheckResultCache.put(cacheKey, checkRes);
//        }
//
//        return checkRes;
//    }
    // 2017/05/09 RS#8144 Del End

    /**
     * Sales condition difference judgment
     * 
     * <pre>
     * False is returned when the following item values of detailMsg1 and detailMsg2 are compared, and there is a difference.
     * ・Ship To Customer Code
     * ・Payment Term Code
     * ・Cash Discount Term Code
     * ・Currency Code
     * ・US Tax Flag
     * ・Sales Rep or Sales Term TOC Code
     * ・COA Account Code
     * ・COA Project Code
     * </pre>
     * @param cpoDtlBean1 NWZC150001CpouDetailBean
     * @param cpoDtlBean2 NWZC150001CpouDetailBean
     * @return True : There is no difference. ／False : There is a
     * difference.
     */
    private static boolean isEqualsSalesCondition(NWZC150001CpouDetailBean cpoDtlBean1, NWZC150001CpouDetailBean cpoDtlBean2) {

        if (!(cpoDtlBean1.getShipToCustCd().equals(cpoDtlBean2.getShipToCustCd()))) {
            return false;
        }
        if (!(cpoDtlBean1.getPmtTermCashDiscCd().equals(cpoDtlBean2.getPmtTermCashDiscCd()))) {
            return false;
        }
        if (!(cpoDtlBean1.getPmtTermCd().equals(cpoDtlBean2.getPmtTermCd()))) {
            return false;
        }
        if (!(cpoDtlBean1.getCashDiscTermCd().equals(cpoDtlBean2.getCashDiscTermCd()))) {
            return false;
        }
        if (!(cpoDtlBean1.getCcyCd().equals(cpoDtlBean2.getCcyCd()))) {
            return false;
        }
        if (!(cpoDtlBean1.getTaxFlg().equals(cpoDtlBean2.getTaxFlg()))) {
            return false;
        }
        if (!(cpoDtlBean1.getCoaAcctCd().equals(cpoDtlBean2.getCoaAcctCd()))) {
            return false;
        }
        if (!(cpoDtlBean1.getCoaProjCd().equals(cpoDtlBean2.getCoaProjCd()))) {
            return false;
        }
        return true;
    }

    /**
     * Judgment for Shipping Plan making
     * 
     * <pre>
     * CPO Order Type Code of the argument is Shipping Plan making object or it judges.
     * </pre>
     * @param CPO Order Type Code（String）
     * @return True : Off the subject／False : Off the subject
     */
    private static boolean isShpgUpdateOrdTp(String cpoOrdTpCd) {

        final Set<String> mustCheckCpoOrdTp = new HashSet<String>();

        mustCheckCpoOrdTp.add(CPO_ORD_TP.TRIAL_TO_SALES);
        mustCheckCpoOrdTp.add(CPO_ORD_TP.LOAN_TO_SALES);
        mustCheckCpoOrdTp.add(CPO_ORD_TP.DEBIT);
        mustCheckCpoOrdTp.add(CPO_ORD_TP.CREDIT);

        return mustCheckCpoOrdTp.contains(cpoOrdTpCd);
    }

    /**
     * Shipping Plan data minimum amount acquisition
     * 
     * <pre>
     * Shipping Plan is retrieved, and it returns it for several [haka] or more of possible Cancel.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param cpoDtlBean NWZC150001CpouDetailBean
     * @return Minimum amount
     */
    private static BigDecimal getShpgPlanAvalCancelQty(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean) {

        final SHPG_PLNTMsg reqShpgPlnTMsg = new SHPG_PLNTMsg();
        reqShpgPlnTMsg.setSQLID("008");
        reqShpgPlnTMsg.setConditionValue("glblCmpyCd01", cpoBean.getGlblCmpyCd());
        reqShpgPlnTMsg.setConditionValue("trxSrcTpCd01", TRX_SRC_TP.WHOLE_SALES);
        reqShpgPlnTMsg.setConditionValue("trxHdrNum01", cpoBean.getCpoOrdNum());
        reqShpgPlnTMsg.setConditionValue("trxLineNum01", cpoDtlBean.getCpoDtlLineNum());
        reqShpgPlnTMsg.setConditionValue("trxLineSubNum01", cpoDtlBean.getCpoDtlLineSubNum());

        final SHPG_PLNTMsgArray shpgPlnTMsgArray = (SHPG_PLNTMsgArray) findByCondition(reqShpgPlnTMsg);

        // 2019/11/27 QC#52339 Add Start
        MDSETMsg mdseTMsg = new MDSETMsg();
        if (shpgPlnTMsgArray.getValidCount() > 0) {
            mdseTMsg = NWZC150001Common.getMdse(shpgPlnTMsgArray.no(0).glblCmpyCd.getValue(), shpgPlnTMsgArray.no(0).mdseCd.getValue());
        }
        // 2019/11/27 QC#52339 Add End
        
        BigDecimal ordQtyMin = null;
        for (int i = 0; i < shpgPlnTMsgArray.getValidCount(); i++) {
            // 2019/11/27 QC#52339 Mod Start
            //if (NWZC150001CpouValidCheck.isShippedSts(shpgPlnTMsgArray.no(i).shpgStsCd.getValue())) {
            if (NWZC150001CpouValidCheck.isShippedSts(shpgPlnTMsgArray.no(i).shpgStsCd.getValue(), mdseTMsg.invtyCtrlFlg.getValue())) {
            // 2019/11/27 QC#52339 Mod End
                BigDecimal ordQty = shpgPlnTMsgArray.no(i).ordQty.getValue();
                if (!hasValue(ordQtyMin) || ordQtyMin.compareTo(ordQty) == 1) {
                    ordQtyMin = ordQty;
                }
            }
        }

        return ordQtyMin;
    }

    @SuppressWarnings("unchecked")
    private List<String> getCoaGlCmbn4Seg(String glblCmpyCd, String coaProdCd, String coaBrCd, String coaChCd, String coaCcCd) {

        final Map<String, Serializable> ssmParam = new HashMap<String, Serializable>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("coaProdCd", coaProdCd);
        ssmParam.put("coaBrCd", coaBrCd);
        ssmParam.put("coaChCd", coaChCd);
        ssmParam.put("coaCcCd", coaCcCd);
        ssmParam.put("coaGlCmbnActvFlg", ZYPConstant.FLG_ON_Y);

        return (List<String>) this.ssmClient.queryObjectList("getCoaGlCmbn4Seg", ssmParam);

    }

    /**
     * Shipping Plan retrieval for set
     * 
     * <pre>
     * Shipping Plan is retrieved, and the acquisition result is returned.
     * At exclusion mode (True), an exclusive retrieval is done.
     * ＜Search condition＞
     * ・Global Company Code
     * ・Order Line Number
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param cpoDtlBean NWZC150001CpouDetailBean
     * @param pMsg NWZC150001PMsg
     * @param forupdate Exclusive mode（boolean）
     * @return Retrieval result（SHPG_PLNTMsgArray）
     */
    private static SHPG_PLNTMsgArray getShpgPlanRelationCPOForSet(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean, NWZC150001PMsg pMsg, boolean forupdate) {

        final SHPG_PLNTMsg reqShpgPlnTMsg = new SHPG_PLNTMsg();
        reqShpgPlnTMsg.setSQLID("030");
        reqShpgPlnTMsg.setConditionValue("glblCmpyCd01", cpoBean.getGlblCmpyCd());
        reqShpgPlnTMsg.setConditionValue("trxSrcTpCd01", TRX_SRC_TP.WHOLE_SALES);
        reqShpgPlnTMsg.setConditionValue("trxHdrNum01", NWZC150001Common.getCpoOrdNumFromBean(cpoBean));
        reqShpgPlnTMsg.setConditionValue("trxLineNum01", NWZC150001Common.getCpoDtlLineNumFromBean(cpoDtlBean));

        if (forupdate) {
            return (SHPG_PLNTMsgArray) findByConditionForUpdate(reqShpgPlnTMsg);
        } else {
            return (SHPG_PLNTMsgArray) findByCondition(reqShpgPlnTMsg);
        }
    }

    /**
     * Shipping Plan retrieval
     * 
     * <pre>
     * Shipping Plan is retrieved, and the acquisition result is returned.
     * ＜Search condition＞
     * ・Global Company Code
     * ・Order Line Number
     * ・Order Line Sub Number
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param cpoDtlBean NWZC150001CpouDetailBean
     * @param pMsg NWZC150001PMsg
     * @return Retrieval result（SHPG_PLNTMsgArray）
     */
    public static SHPG_PLNTMsgArray getShpgPlanRelationCPO(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean, NWZC150001PMsg pMsg) {
        return getShpgPlanRelationCPO(cpoBean, cpoDtlBean, pMsg, false);
    }

    /**
     * Shipping Plan retrieval
     * 
     * <pre>
     * Shipping Plan is retrieved, and the acquisition result is returned.
     * At exclusion mode (True), an exclusive retrieval is done.
     * ＜Search condition＞
     * ・Global Company Code
     * ・Order Line Number
     * ・Order Line Sub Number
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param cpoDtlBean NWZC150001CpouDetailBean
     * @param pMsg NWZC150001PMsg
     * @param forupdate Exclusive mode（boolean）
     * @return Retrieval result（SHPG_PLNTMsgArray）
     */
    public static SHPG_PLNTMsgArray getShpgPlanRelationCPO(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean, NWZC150001PMsg pMsg, boolean forupdate) {

        final SHPG_PLNTMsg reqShpgPlnTMsg = new SHPG_PLNTMsg();
        reqShpgPlnTMsg.setSQLID("008");
        reqShpgPlnTMsg.setConditionValue("glblCmpyCd01", cpoBean.getGlblCmpyCd());
        reqShpgPlnTMsg.setConditionValue("trxSrcTpCd01", TRX_SRC_TP.WHOLE_SALES);
        reqShpgPlnTMsg.setConditionValue("trxHdrNum01", NWZC150001Common.getCpoOrdNumFromBean(cpoBean));
        reqShpgPlnTMsg.setConditionValue("trxLineNum01", NWZC150001Common.getCpoDtlLineNumFromBean(cpoDtlBean));
        reqShpgPlnTMsg.setConditionValue("trxLineSubNum01", NWZC150001Common.getCpoDtlLineSubNumFromBean(cpoDtlBean));

        if (forupdate) {
            return (SHPG_PLNTMsgArray) findByConditionForUpdate(reqShpgPlnTMsg);
        } else {
            return (SHPG_PLNTMsgArray) findByCondition(reqShpgPlnTMsg);
        }
    }

    /**
     * Order information change confirmation
     * 
     * <pre>
     * False is returned when CPO Detail (retrieval result) and the screen input value are compared, and there is a difference in the value and True is returned when not is.
     * ＜Comparison item＞
     * ・Ship To Customer Code
     * ・Inventory Location Code
     * ・RDD Date
     * ・RSD Date
     * ・Payment Term Code
     * ・Cash Discount Term Code
     * ・Sales Rep or Sales Team TOC Code
     * ・Ship Complete Code
     * ・UOM Complete Flag
     * ・Freight Charge To Code
     * ・Freight Charge Method Code
     * ・Shipping Service Level Code
     * ・Carrier Account Number
     * ・Carrier Code
     * ・COA Account Code
     * ・COA Project Code
     * ・Currency Code
     * ・US Tax Code
     * </pre>
     * @param cpoDtlTMsg CPO_DTLTMsg
     * @param cpoDtlBean NWZC150001CpouDetailBean
     * @return True : There is a no difference. ／False : There is a
     * difference.
     */
    private static boolean isChangeDataExceptForOrdQty(CPO_DTLTMsg cpoDtlTMsg, NWZC150001CpouDetailBean cpoDtlBean) {

        Map<EZDTItem, String> compareMap = new LinkedHashMap<EZDTItem, String>();
        compareMap.put(cpoDtlTMsg.shipToCustCd, cpoDtlBean.getShipToCustCd());
        compareMap.put(cpoDtlTMsg.invtyLocCd, cpoDtlBean.getInvtyLocCd());
        compareMap.put(cpoDtlTMsg.rddDt, cpoDtlBean.getRddDt());
        compareMap.put(cpoDtlTMsg.rsdDt, cpoDtlBean.getRsdDt());
        compareMap.put(cpoDtlTMsg.pmtTermCashDiscCd, cpoDtlBean.getPmtTermCashDiscCd());
        compareMap.put(cpoDtlTMsg.pmtTermCd, cpoDtlBean.getPmtTermCd());
        compareMap.put(cpoDtlTMsg.cashDiscTermCd, cpoDtlBean.getCashDiscTermCd());
        compareMap.put(cpoDtlTMsg.slsRepOrSlsTeamTocCd, cpoDtlBean.getSlsRepOrSlsTeamTocCd());
        compareMap.put(cpoDtlTMsg.shipCpltCd, cpoDtlBean.getShipCpltCd());
        compareMap.put(cpoDtlTMsg.uomCpltFlg, cpoDtlBean.getUomCpltFlg());
        compareMap.put(cpoDtlTMsg.frtChrgToCd, cpoDtlBean.getFrtChrgToCd());
        compareMap.put(cpoDtlTMsg.frtChrgMethCd, cpoDtlBean.getFrtChrgMethCd());
        compareMap.put(cpoDtlTMsg.shpgSvcLvlCd, cpoDtlBean.getShpgSvcLvlCd());
        compareMap.put(cpoDtlTMsg.carrAcctNum, cpoDtlBean.getCarrAcctNum());
        compareMap.put(cpoDtlTMsg.carrCd, cpoDtlBean.getCarrCd());
        compareMap.put(cpoDtlTMsg.coaAcctCd, cpoDtlBean.getCoaAcctCd());
        compareMap.put(cpoDtlTMsg.coaProjCd, cpoDtlBean.getCoaProjCd());
        compareMap.put(cpoDtlTMsg.ccyCd, cpoDtlBean.getCcyCd());
        compareMap.put(cpoDtlTMsg.taxFlg, cpoDtlBean.getTaxFlg());

        Set<Entry<EZDTItem, String>> entrySet = compareMap.entrySet();
        for (Entry<EZDTItem, String> entry : entrySet) {

            EZDTItem ezdITtem = entry.getKey();

            String cpoDtlTMsgValue;
            if (ezdITtem instanceof EZDTStringItem) {
                cpoDtlTMsgValue = ((EZDTStringItem) ezdITtem).getValue();
            } else if (ezdITtem instanceof EZDTDateItem) {
                cpoDtlTMsgValue = ((EZDTDateItem) ezdITtem).getValue();
            } else {
                continue;
            }

            if (!isEquals(cpoDtlTMsgValue, entry.getValue())) {
                return false;
            }
        }

        return true;
    }

    private boolean isBomHdrOrBomBaseCmpt(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean) {

        if (cpoDtlBean.getBomHeaderFlg() == ZYPConstant.FLG_ON_Y) {
            // if true, Sales BOM Header
            return true;

        } else if (NWZC150001Common.isBomCmptLine(cpoBean, cpoDtlBean) && cpoDtlBean.getBaseCmptFlg() == ZYPConstant.FLG_ON_Y) {
            // Base Component of Sales BOM
            return true;
        }
        return false;
    }

    private boolean isHldCtrlOrg(NWZC150001CpouDetailBean cpoDtlBean, List<Map<String, String>> hldCtrlOrgList) {

        // ---------------------------------------------------------------------
        // If data doesn't exist in the HLD_CTRL_ORG table, Hold is
        // not created.
        // ---------------------------------------------------------------------
        if (isNullOrEmpty(hldCtrlOrgList)) {
            return false;
        }

        // HLD_CTRL_ORG
        for (Map<String, String> hldCtrlOrg : hldCtrlOrgList) {
            if (isEquals("*", hldCtrlOrg.get("FIRST_ORG_CD"))) {
                return true;
            }
            if (!hasValue(hldCtrlOrg.get("FIRST_ORG_CD"))) {
                return false;
            }
        }

        // TOC_CD
        final S21_ORGTMsg s21Org = NWZC150001CpouExistsCdInDbCheck.getS21OrgData(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getSlsRepOrSlsTeamTocCd());
        if (s21Org == null) {
            return false;
        }

        for (Map<String, String> hldCtrlOrg : hldCtrlOrgList) {

            final String tocCd = hldCtrlOrg.get("TOC_CD");
            final String eighthOrgCd = hldCtrlOrg.get("EIGHTH_ORG_CD");
            final String svnthOrgCd = hldCtrlOrg.get("SVNTH_ORG_CD");
            final String sixthOrgCd = hldCtrlOrg.get("SIXTH_ORG_CD");
            final String fifthOrgCd = hldCtrlOrg.get("FIFTH_ORG_CD");
            final String frthOrgCd = hldCtrlOrg.get("FRTH_ORG_CD");
            final String thirdOrgCd = hldCtrlOrg.get("THIRD_ORG_CD");
            final String scdOrgCd = hldCtrlOrg.get("SCD_ORG_CD");
            final String firstOrgCd = hldCtrlOrg.get("FIRST_ORG_CD");

            // Toc
            if (hasValue(tocCd)) {
                if (isEquals(tocCd, s21Org.tocCd.getValue())) {
                    return true;
                }

                // 8th
            } else if (hasValue(eighthOrgCd)) {
                if (isEquals(eighthOrgCd, s21Org.eighthOrgCd.getValue()) && isEquals(svnthOrgCd, s21Org.svnthOrgCd.getValue()) && isEquals(sixthOrgCd, s21Org.sixthOrgCd.getValue()) && isEquals(fifthOrgCd, s21Org.fifthOrgCd.getValue())
                        && isEquals(frthOrgCd, s21Org.frthOrgCd.getValue()) && isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue()) && isEquals(scdOrgCd, s21Org.scdOrgCd.getValue()) && isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 7th
            } else if (hasValue(svnthOrgCd)) {
                if (isEquals(svnthOrgCd, s21Org.svnthOrgCd.getValue()) && isEquals(sixthOrgCd, s21Org.sixthOrgCd.getValue()) && isEquals(fifthOrgCd, s21Org.fifthOrgCd.getValue()) && isEquals(frthOrgCd, s21Org.frthOrgCd.getValue())
                        && isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue()) && isEquals(scdOrgCd, s21Org.scdOrgCd.getValue()) && isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 6th
            } else if (hasValue(sixthOrgCd)) {
                if (isEquals(sixthOrgCd, s21Org.sixthOrgCd.getValue()) && isEquals(fifthOrgCd, s21Org.fifthOrgCd.getValue()) && isEquals(frthOrgCd, s21Org.frthOrgCd.getValue()) && isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue())
                        && isEquals(scdOrgCd, s21Org.scdOrgCd.getValue()) && isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 5th
            } else if (hasValue(fifthOrgCd)) {
                if (isEquals(fifthOrgCd, s21Org.fifthOrgCd.getValue()) && isEquals(frthOrgCd, s21Org.frthOrgCd.getValue()) && isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue()) && isEquals(scdOrgCd, s21Org.scdOrgCd.getValue())
                        && isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 4th
            } else if (hasValue(frthOrgCd)) {
                if (isEquals(frthOrgCd, s21Org.frthOrgCd.getValue()) && isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue()) && isEquals(scdOrgCd, s21Org.scdOrgCd.getValue()) && isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 3rd
            } else if (hasValue(thirdOrgCd)) {
                if (isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue()) && isEquals(scdOrgCd, s21Org.scdOrgCd.getValue()) && isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 2nd
            } else if (hasValue(scdOrgCd)) {
                if (isEquals(scdOrgCd, s21Org.scdOrgCd.getValue()) && isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 1st
            } else if (hasValue(firstOrgCd)) {
                if (isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isNullOrEmpty(Collection col) {
        return col == null || col.isEmpty();
    }

    private static boolean isEquals(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 != null && str2 != null) {
            return str1.equals(str2);
        }
        return false;
    }

    // START   ADD M.Fuji [Defect#2549]
    /**
     * <pre>
     * otherCheckOfMainUnitReln
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     * @param beforeSMsgSize int
     */
    private void otherCheckOfMainUnitReln(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList) {

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {
            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);

            if (ZYPConstant.FLG_ON_Y.equals(cpoDtlBean.getBaseCmptFlg()) && ZYPConstant.FLG_OFF_N.equals(getMachMstrCratFlg(cpoBean.getGlblCmpyCd(), cpoDtlBean.getMdseCdForMstrSrch()))) {
                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM1312E, resPMsgList, i);
            }
        }

        Map<String, String> baseCmptIncludeMap = new HashMap<String, String>();

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {
            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);

            String dsOrdPosnNum = cpoDtlBean.getDsOrdPosnNum();
            if (baseCmptIncludeMap.containsKey(dsOrdPosnNum)) {
                //when this Line is Set Component

                if (ZYPConstant.FLG_ON_Y.equals(cpoDtlBean.getBaseCmptFlg())) {
                    baseCmptIncludeMap.put(dsOrdPosnNum, cpoDtlBean.getBaseCmptFlg());
                }
            } else {
                baseCmptIncludeMap.put(dsOrdPosnNum, cpoDtlBean.getBaseCmptFlg());
            }
        }

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {
            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);

            if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpoDtlBean.getCpoDtlLineSubNum())) {

                String dsOrdPosnNum = cpoDtlBean.getDsOrdPosnNum();
                if (hasValue(dsOrdPosnNum) && baseCmptIncludeMap.containsKey(dsOrdPosnNum)) {
                    String baseCmptIncludeFlag = baseCmptIncludeMap.get(dsOrdPosnNum);

                    if (ZYPConstant.FLG_OFF_N.equals(baseCmptIncludeFlag)) {
                        NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM1312E, resPMsgList, i);
                    }
                }
            }
        }
    }

    /**
     * <pre>
     * chkMachMstrCrat
     * </pre>
     * @param glblCmpyCd String
     * @param mdseCd String
     */
    private String getMachMstrCratFlg(String glblCmpyCd, String mdseCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);

        return (String) ssmClient.queryObject("getMachMstrCratFlg", ssmParam);
    }

    // END   ADD M.Fuji [Defect#2549]

    /**
     * <pre>
     * otherCheckConfigContrNum
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     * @param beforeSMsgSize int
     */
    private void otherCheckConfigNumReln(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList) {

        String glblCmpyCd = cpoBean.getGlblCmpyCd();

        DS_ORD_TPTMsg dsOrdTpTMsg = new DS_ORD_TPTMsg();
        dsOrdTpTMsg.glblCmpyCd.setValue(glblCmpyCd);
        dsOrdTpTMsg.dsOrdTpCd.setValue(cpoBean.getDSOrdTpCd());

        dsOrdTpTMsg = (DS_ORD_TPTMsg) findByKey(dsOrdTpTMsg);

        if (dsOrdTpTMsg == null) {
            return;
        }

        if (!dsOrdTpTMsg.splyContrChkFlg.getValue().equals(ZYPConstant.FLG_OFF_N)) {
            return;
        }

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {
            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);

            if (hasValue(cpoDtlBean.getSvcConfigMstrPk()) && hasValue(cpoDtlBean.getShipToCustCd())) {

                Map<String, String> ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                ssmParam.put("svcConfigMstrPk", cpoDtlBean.getSvcConfigMstrPk().toString());
                ssmParam.put("shipToCustCd", cpoDtlBean.getShipToCustCd());

                BigDecimal svcMachMstrPK = (BigDecimal) ssmClient.queryObject("getSvcMachMstr", ssmParam);

                if (!hasValue(svcMachMstrPK)) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM1305E, resPMsgList, i);
                }
            }

            if (hasValue(NWZC150001Common.getCpoOrdNumFromBean(cpoBean)) && !hasValue(cpoDtlBean.getSvcConfigMstrPk())) {
                CPO_DTLTMsg reqCpoDtlTMsg = new CPO_DTLTMsg();
                setValue(reqCpoDtlTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
                setValue(reqCpoDtlTMsg.cpoOrdNum, NWZC150001Common.getCpoOrdNumFromBean(cpoBean));
                setValue(reqCpoDtlTMsg.cpoDtlLineNum, cpoDtlBean.getCpoDtlLineNum());
                setValue(reqCpoDtlTMsg.cpoDtlLineSubNum, cpoDtlBean.getCpoDtlLineSubNum());

                CPO_DTLTMsg cpoDtlTMsg = (CPO_DTLTMsg) findByKey(reqCpoDtlTMsg);

                if (cpoDtlTMsg != null && hasValue(cpoDtlTMsg.svcConfigMstrPk)) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM1307E, resPMsgList, i);
                }
            }
        }
    }

    // END   ADD M.Fuji [OM0028]

    /**
     * BOM Component Cancel check
     * 
     * <pre>
     *  When shipping status is over S/O printed.
     * </pre>
     * @param cpoBean NWZC150001CpouBean NWZC150001PMsg
     * @param resPMsgList List<NWZC150002PMsg>
     */
    public void otherCheckOfBOMComponentCancel(NWZC150001CpouBean cpoBean, NWZC150001PMsg pMsg, List<NWZC150002PMsg> resPMsgList) {
        // 1.2WDS add start -->
        if (NWZC150001CpouConstant.CPO_CANCEL.equals(cpoBean.getRqstTpCd())) {
            int soPrinted = Integer.parseInt(SHPG_STS.S_OR_O_PRINTED);

            for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {
                NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);
                if (!NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(cpoDtlBean.getDtlRqstTpCd())) {
                    continue;
                }
                if (hasValue(cpoDtlBean.getShipCpltCd())) {

                    SHPG_PLNTMsgArray shpgPlnTMsgArray = NWZC150001CpouOtherCheck.getShpgPlanRelationCPO(cpoBean, cpoDtlBean, pMsg, false);
                    for (int j = 0; j < shpgPlnTMsgArray.getValidCount(); j++) {
                        String shpgStsCd = shpgPlnTMsgArray.no(j).shpgStsCd.getValue();
                        if (soPrinted <= Integer.parseInt(shpgStsCd)) {
                            NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM1083E, resPMsgList, i);
                            return;
                        }
                    }
                }
            }
        }
        // 1.2WDS add end <--
    }
    
    // S21_NA#19854 2017/07/31 Add Start
    /**
     * <pre>
     * isDefaultWhTmpl
     * </pre>
     * @param NWZC150001CpouBean cpoBean
     * @param S21SsmBatchClient ssmClient
     */
    private static Boolean isDefaultWhTmpl(NWZC150001CpouBean cpoBean, S21SsmBatchClient ssmClient) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ssmParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.DEFAULT_WAREHOUSE_TEMPLATE);
        ssmParam.put("dsOrdCatgCd", cpoBean.getDsOrdCatgCd());
        ssmParam.put("dsOrdTpCd", cpoBean.getDSOrdTpCd());
        ssmParam.put("dsOrdRsnCd", cpoBean.getDSOrdRsnCd());

        BigDecimal cnt = (BigDecimal) ssmClient.queryObject("isDefaultWhTmpl", ssmParam);
        
        return cnt.compareTo(BigDecimal.ZERO) == 1;
    }
    // S21_NA#19854 2017/07/31 Add End

    // 2019/08/01 S21_NA#52156 Add Start
    /**
     * PR Apporved Change Check
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     */
    private void otherCheckOfPRApproved(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList) {
        // 1.2WDS modify start -->
        if (!NWZC150001CpouConstant.CPO_MODIFY.equals(cpoBean.getRqstTpCd())) {
            return;
        }

        List<Map<String, String>> prApprovedLineNumList = getApprovedPRLineNum(cpoBean);

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);

            if (!NWZC150001CpouConstant.CPO_DTL_MODIFY.equals(cpoDtlBean.getDtlRqstTpCd())) {
                continue;
            }

            for (Map<String, String> prApprovedLineNum : prApprovedLineNumList) {
                if (prApprovedLineNum.get("TRX_LINE_NUM").equals(cpoDtlBean.getCpoDtlLineNum()) //
                        && prApprovedLineNum.get("TRX_LINE_SUB_NUM").equals((cpoDtlBean.getCpoDtlLineSubNum()))) {
                    CPO_DTLTMsg reqCpoDtlTMsg = new CPO_DTLTMsg();
                    setValue(reqCpoDtlTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
                    setValue(reqCpoDtlTMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
                    setValue(reqCpoDtlTMsg.cpoDtlLineNum, cpoDtlBean.getCpoDtlLineNum());
                    setValue(reqCpoDtlTMsg.cpoDtlLineSubNum, cpoDtlBean.getCpoDtlLineSubNum());

                    CPO_DTLTMsg cpoDtlTMsg = (CPO_DTLTMsg) findByKey(reqCpoDtlTMsg);

                    if (!isChangeDataApprovedPR(cpoDtlTMsg, cpoDtlBean)) {
                        NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM2311E, resPMsgList, i);
                    }
                }
            }

        }
    }
    
    /**
     * <pre>
     * getApprovedPRLineNum
     * </pre>
     * @param NWZC150001CpouBean cpoBean
     * @param S21SsmBatchClient ssmClient
     */
    private List<Map<String, String>> getApprovedPRLineNum(NWZC150001CpouBean cpoBean) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ssmParam.put("cpoOrdNum", cpoBean.getCpoOrdNum());
        ssmParam.put("shpgStsCd", SHPG_STS.VALIDATED);
        ssmParam.put("prchReqStsCancel", PRCH_REQ_STS.CANCELLED);
        ssmParam.put("prchReqLineStsCancel", PRCH_REQ_LINE_STS.CANCELLED);
        ssmParam.put("poStsCancel", PO_STS.CANCELLED);
        List<String> stsList = new ArrayList<String>();
        stsList.add(ORD_LINE_DPLY_STS.PENDING_ALLOCATION);
        stsList.add(ORD_LINE_DPLY_STS.BACK_ORDER);
        stsList.add(ORD_LINE_DPLY_STS.WAITING_PICK);
        stsList.add(ORD_LINE_DPLY_STS.WAITING_RECEIPT); //CD:200 AWAITING DROP SHIP

        ssmParam.put("stsList", stsList);

        List<Map<String, String>> result = (List<Map<String, String>>) ssmClient.queryObjectList("getApprovedPRLineNum", ssmParam);

        return result;
    }
    
    private static boolean isChangeDataApprovedPR(CPO_DTLTMsg cpoDtlTMsg, NWZC150001CpouDetailBean cpoDtlBean) {

        Map<EZDTItem, String> compareMap = new LinkedHashMap<EZDTItem, String>();
        compareMap.put(cpoDtlTMsg.mdseCd, cpoDtlBean.getMdseCd());
        compareMap.put(cpoDtlTMsg.custUomCd, cpoDtlBean.getCustUomCd());
        compareMap.put(cpoDtlTMsg.dsOrdLineCatgCd, cpoDtlBean.getDsOrdLineCatgCd());
        compareMap.put(cpoDtlTMsg.ordLineSrcCd, cpoDtlBean.getOrdLineSrcCd());
        compareMap.put(cpoDtlTMsg.rtlWhCd, cpoDtlBean.getRtlWhCd());
        compareMap.put(cpoDtlTMsg.rtlSwhCd, cpoDtlBean.getRtlSwhCd());
        compareMap.put(cpoDtlTMsg.supdLockFlg, cpoDtlBean.getSupdLockFlg());
        compareMap.put(cpoDtlTMsg.vndInvtyLocCd, cpoDtlBean.getVndInvtyLocCd());
        compareMap.put(cpoDtlTMsg.rddDt, cpoDtlBean.getRddDt());

        Set<Entry<EZDTItem, String>> entrySet = compareMap.entrySet();
        for (Entry<EZDTItem, String> entry : entrySet) {

            EZDTItem ezdITtem = entry.getKey();

            String cpoDtlTMsgValue;
            if (ezdITtem instanceof EZDTStringItem) {
                cpoDtlTMsgValue = ((EZDTStringItem) ezdITtem).getValue();
            } else if (ezdITtem instanceof EZDTDateItem) {
                cpoDtlTMsgValue = ((EZDTDateItem) ezdITtem).getValue();
            } else {
                continue;
            }

            if (!isEquals(cpoDtlTMsgValue, entry.getValue())) {
                return false;
            }
        }

        if (cpoDtlTMsg.ordQty.getValue().compareTo(cpoDtlBean.getOrdQty()) != 0) {
            return false;
        }

        return true;
    }
    // 2019/08/01 S21_NA#52156 Add End
}
