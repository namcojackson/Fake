/**
 * <pre>
 * CPO Update API Set Default Data
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 * 2017/05/09   Fujitsu         S.Takami        Update          RS#8144
 * 2017/05/15   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2 RS#8390
 * 2017/10/30   Fujitsu         R.Nakamura      Update          S21_NA#22140
 * 2018/01/18   Fujitsu         K.Ishizuka      Update          S21_NA#23555
 * 2018/01/24   Fujitsu         S.Takami        Update          S21_NA#23706 (Point out 23555 fixing)
 * 2019/08/09   Fujitsu         R.Nakamura      Update          S21_NA#52554
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.derive;

import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.CONST_KEY_CR_AND_BILL_ONLY_DUMMY_WH_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.CONST_KEY_NO_HARD_ALLOC_WH_CD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByCondition;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKey;
import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CNTYTMsg;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.S21_ORGTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_FROM_LOC_LIST_VTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.parts.NWZC045001PMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150002PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC045001.NWZC045001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouFindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.logWriter.NWZC150001CpouLogWriter;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.validation.NWZC150001CpouExistsCdInDbCheck;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.validation.NWZC150001CpouValidCheck;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Exchange;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001RateData;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXOrdTakeMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;

public class NWZC150001CpouSetData {

    /** */
    private static final String CLASS_NM = "NWZC150001CpouSetData";
    /**
     * Data setting
     * 
     * <pre>
     * The setting of necessary data is executed beforehand.
     * </pre>
     * @param cpouBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     */
    public static void setDefaultData(NWZC150001CpouBean cpouBean, //
            NWZC150001PMsg pMsg, //
            List<NWZC150002PMsg> resPMsgList, //
            NWZC150001CpouLocalCache localCache, //
            S21ApiMessageIdMgr msgIdMgr, //
            ONBATCH_TYPE onBatchType) {
        final String methodNm = "setData";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(CLASS_NM, methodNm);

        setUpperCaseforShipToAttribute(cpouBean);
        try {

            // CPO Order Timestamp
            cpouBean.setCpoOrdTs(cpouBean.getSlsDt());

            // SHIP To Info(Header)
            copyShipToDataByNoDropShip(cpouBean, localCache);

            // Ship To Customer Code --> Sell To Customer Code,Bill To
            // Customer Code
            // setSellToBillTo(cpouBean); // S21_NA#10321-27 Del

            // ********** [# 370381] - START
            if (!NWZC150001CpouConstant.CPO_CANCEL.equals(cpouBean.getRqstTpCd())) {
                // ********** [# 370381] - END
                getBillToItrlFlg(cpouBean, pMsg, localCache, msgIdMgr);
            }

            if (!hasValue(cpouBean.getPayerCustCd())) {
                cpouBean.setPayerCustCd(cpouBean.getBillToCustCd());
            }
            // 10/06/2009 Update
            if (hasValue(cpouBean.getCustIssPoNum()) && !hasValue(cpouBean.getCustIssPoDt())) {
                cpouBean.setCustIssPoDt(cpouBean.getSlsDt());
            }

            // Default Data From Sell To Cust
            boolean needSellToCust = !hasValue(cpouBean.getOrdFuflLvlCd()) //
                    || !hasValue(cpouBean.getSellToFirstRefCmntTxt()) //
                    || !hasValue(cpouBean.getSellToScdRefCmntTxt());
            SELL_TO_CUSTTMsg sellToCustTMsg = null;
            if (needSellToCust) {
                sellToCustTMsg = getSellToCustRecord(cpouBean, localCache);
            }
            if (!hasValue(cpouBean.getOrdFuflLvlCd())) {
//                cpouBean.setOrdFuflLvlCd(getOrdFuflLvlCd(cpouBean));
                if (sellToCustTMsg != null && hasValue(sellToCustTMsg.ordFuflLvlCd)) {
                    cpouBean.setOrdFuflLvlCd(sellToCustTMsg.ordFuflLvlCd.getValue());
                }
            }
            if (!hasValue(cpouBean.getAddDropShipFlg())) {
                cpouBean.setAddDropShipFlg(ZYPConstant.FLG_OFF_N);
            }
            // Del Start 2017/10/13 QC#20246(Sol#454)
//            if (!hasValue(cpouBean.getSellToFirstRefCmntTxt())) {
////                cpouBean.setSellToFirstRefCmntTxt(getSellToFirstRefCmntTxt(cpouBean));
//                if (sellToCustTMsg != null && hasValue(sellToCustTMsg.firstRefCmntTxt)) {
//                    cpouBean.setSellToFirstRefCmntTxt(sellToCustTMsg.firstRefCmntTxt.getValue());
//                }
//            }
//            if (!hasValue(cpouBean.getSellToScdRefCmntTxt())) {
////                cpouBean.setSellToScdRefCmntTxt(getSellToScdRefCmntTxt(cpouBean));
//                if (sellToCustTMsg != null && hasValue(sellToCustTMsg.scdRefCmntTxt)) {
//                    cpouBean.setSellToFirstRefCmntTxt(sellToCustTMsg.scdRefCmntTxt.getValue());
//                }
//            }
            // Del End 2017/10/13 QC#20246(Sol#454)

            // 10/14/2009 Move 683
            if (asList(NWZC150001CpouConstant.CPO_SAVE, NWZC150001CpouConstant.CPO_SUBMIT).contains(cpouBean.getRqstTpCd())) {
                // 2017/05/15 S21_NA#Review structure Lv.2 RS#8390 Mod Start
//                reNewOrderLineNumber(cpouBean);
                setReNumCpoDtlLineNumAndDsCpoLineNum(cpouBean);
                // 2017/05/15 S21_NA#Review structure Lv.2 RS#8390 Mod End
            }

            if (asList(NWZC150001CpouConstant.CPO_SAVE, NWZC150001CpouConstant.CPO_SUBMIT, NWZC150001CpouConstant.CPO_MODIFY).contains(cpouBean.getRqstTpCd())) {
                // START UPD N.Nakazawa [OM031]
                //                CPO_SRC_TPTMsg cpoSrcTpMsg = new CPO_SRC_TPTMsg();
                //                cpoSrcTpMsg.glblCmpyCd.setValue(cpouBean.getGlblCmpyCd());
                //                cpoSrcTpMsg.cpoSrcTpCd.setValue(cpouBean.getCpoSrcTpCd());
                //                cpoSrcTpMsg = (CPO_SRC_TPTMsg) findByKeyWithCache(cpoSrcTpMsg);
                //
                //                String revRecogMethCd = cpoSrcTpMsg.revRecogMethCd.getValue();
                //
                //                if (!CPO_ORD_TP.SALES.equals(cpouBean.getCpoOrdTpCd()) || !REV_RECOG_METH.BOL.equals(revRecogMethCd)) {
                //                    cpouBean.setRevRecogMethCd(revRecogMethCd);
                //                } else {
                //
                //                    final Map<String, String> ssmParam = new HashMap<String, String>();
                //                    ssmParam.put("glblCmpyCd", cpouBean.getGlblCmpyCd());
                //                    ssmParam.put("billToCustCd", cpouBean.getBillToCustCd());
                //                    ssmParam.put("slsDt", cpouBean.getSlsDt());
                //
                //                    revRecogMethCd = (String) this.ssmClient.queryObject("getRevRecogMethCd", ssmParam);
                //
                //                    if (hasValue(revRecogMethCd)) {
                //                        cpouBean.setRevRecogMethCd(revRecogMethCd);
                //                    } else {
                //                        cpouBean.setRevRecogMethCd(REV_RECOG_METH.BOL);
                //                    }
                //                }
                DS_ORD_TPTMsg dsOrdTpTMsg = new DS_ORD_TPTMsg();
                dsOrdTpTMsg.glblCmpyCd.setValue(cpouBean.getGlblCmpyCd());
                dsOrdTpTMsg.dsOrdTpCd.setValue(cpouBean.getDSOrdTpCd());
                dsOrdTpTMsg = (DS_ORD_TPTMsg) findByKey(dsOrdTpTMsg);
                if (dsOrdTpTMsg == null) {
                    msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM1273E, pMsg);
                } else {
                    cpouBean.setRevRecogMethCd(dsOrdTpTMsg.revRecogMethCd.getValue());
                }
                // END UPD N.Nakazawa [OM031]
            }

            if (asList(NWZC150001CpouConstant.CPO_MODIFY, NWZC150001CpouConstant.CPO_CANCEL).contains(cpouBean.getRqstTpCd())) {
                CPOTMsg cpoTMsg = new CPOTMsg();
                cpoTMsg.glblCmpyCd.setValue(cpouBean.getGlblCmpyCd());
                cpoTMsg.cpoOrdNum.setValue(cpouBean.getCpoOrdNum());
                cpoTMsg = (CPOTMsg) findByKey(cpoTMsg);
                cpouBean.setCpoSrcTpCd(cpoTMsg.cpoSrcTpCd.getValue());
                cpouBean.setSysSrcCd(cpoTMsg.sysSrcCd.getValue());
            }

            // 20121219 M.Fuji Defect#38 Start
            // 20121122 M.Fuji WDS Solution#123 Federal Start
            //            setFederalOffBalanceSheet(cpouBean);
            // 20121122 M.Fuji WDS Solution#123 Federal End
            // 20121219 M.Fuji Defect#38 End

            if (!hasValue(cpouBean.getDsPmtMethCd())) {
                cpouBean.setDsPmtMethCd(DS_PMT_METH.INVOICE);

                // S21_NA#9278 Delete
//            } else if (DS_PMT_METH.CREDIT_CARD.equals(cpouBean.getDsPmtMethCd())) {
//                // [RTL_CR_CARD]
//                RTL_CR_CARDTMsg rtlCrCardTMsg = new RTL_CR_CARDTMsg();
//                setValue(rtlCrCardTMsg.glblCmpyCd, cpouBean.getGlblCmpyCd());
//                setValue(rtlCrCardTMsg.sellToCustCd, cpouBean.getSellToCustCd());
//                setValue(rtlCrCardTMsg.crCardCustRefNum, cpouBean.getCustRefNum());
//                rtlCrCardTMsg = (RTL_CR_CARDTMsg) findByKeyWithCache(rtlCrCardTMsg);
//
//                if (rtlCrCardTMsg != null) {
//                    cpouBean.setCrCardTpCd(rtlCrCardTMsg.crCardTpCd.getValue());
//                } else {
//                    msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0971E, pMsg);
//                }
            }

            /**************************************************
             * CPO_DTL
             **************************************************/
            setDetailDefaultData(cpouBean, pMsg, resPMsgList, localCache, msgIdMgr, onBatchType);

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(CLASS_NM, methodNm);
        }
    }

    private static void setUpperCaseforShipToAttribute(NWZC150001CpouBean cpouBean) {
        final String methodNm = "toCpoBean";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(CLASS_NM, methodNm);
    
        try {
    
            // Extranet Capital letter conversion
            cpouBean.setAddShipToStCd(cpouBean.getAddShipToStCd().toUpperCase());
            cpouBean.setAddShipToCtryCd(cpouBean.getAddShipToCtryCd().toUpperCase());
    
            for (NWZC150001CpouDetailBean cpouDetailBean : cpouBean.getDtlBeanList()) {
                cpouDetailBean.setShipToStCd(cpouDetailBean.getShipToStCd().toUpperCase());
                cpouDetailBean.setShipToCtryCd(cpouDetailBean.getShipToCtryCd().toUpperCase());
            }
    
        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(CLASS_NM, methodNm);
        }
    }

    /**
     * Setting of Ship To information
     * 
     * <pre>
     * When SHIP_TO_CUST_CD of details is a blank, SHIP_TO information on the header is reflected in details.
     * </pre>
     * @param cpouBean NWZC150001CpouBean
     * @param cpoDtlBean NWZC150001CpouDetailBean
     */
    private static void copyShipToDataByHeader(NWZC150001CpouBean cpouBean, NWZC150001CpouDetailBean cpoDtlBean) {

        if (hasValue(cpoDtlBean.getShipToCustCd())) {
            return;
        } else {
            cpoDtlBean.setShipToCustCd(cpouBean.getAddShipToCustCd());
        }

        if (ZYPConstant.FLG_ON_Y.equals(cpouBean.getAddDropShipFlg())) {

            cpoDtlBean.setDropShipFlg(ZYPConstant.FLG_ON_Y);

            cpoDtlBean.setShipToLocNm(cpouBean.getAddShipToLocNm());
            cpoDtlBean.setShipToAddlLocNm(cpouBean.getAddShipToAddlLocNm());
            cpoDtlBean.setShipToFirstLineAddr(cpouBean.getAddShipToFirstLineAddr());
            cpoDtlBean.setShipToScdLineAddr(cpouBean.getAddShipToScdLineAddr());
            cpoDtlBean.setShipToThirdLineAddr(cpouBean.getAddShipToThirdLineAddr());
            cpoDtlBean.setShipToFrthLineAddr(cpouBean.getAddShipToFrthLineAddr());
            cpoDtlBean.setShipToFirstRefCmntTxt(cpouBean.getAddShipTo01RefCmntTxt());
            cpoDtlBean.setShipToScdRefCmntTxt(cpouBean.getAddShipTo02RefCmntTxt());
            cpoDtlBean.setShipToCtyAddr(cpouBean.getAddShipToCtyAddr());
            cpoDtlBean.setShipToStCd(cpouBean.getAddShipToStCd());
            cpoDtlBean.setShipToProvNm(cpouBean.getAddShipToProvNm());
            cpoDtlBean.setShipToPostCd(cpouBean.getAddShipToPostCd());
            cpoDtlBean.setShipToCtryCd(cpouBean.getAddShipToCtryCd());
            cpoDtlBean.setShipToCntyNm(cpouBean.getAddShipToCntyNm());

        } else {
            cpoDtlBean.setDropShipFlg(ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * Setting of Ship To information
     * 
     * <pre>
     * It retrieves to SHIP_TO_CUST when it is not DropShip and the acquired value is set to the item to which dataBean corresponds.
     * It doesn't process it for DropShip.
     * </pre>
     * @param cpouBean NWZC150001CpouBean
     * @param cpouDtlBean NWZC150001CpouDetailBean
     * @param localCache Local Cache Object
     */
    private static void copyShipToDataByNoDropShip(NWZC150001CpouBean cpouBean, NWZC150001CpouDetailBean cpouDtlBean, NWZC150001CpouLocalCache localCache) {

        if (ZYPConstant.FLG_ON_Y.equals(cpouDtlBean.getDropShipFlg())) {
            return;
        } else {
            cpouDtlBean.setDropShipFlg(ZYPConstant.FLG_OFF_N);
        }

        NWZC150001CpouFindCondition fc = null;
        if (NWZC150001CpouConstant.CPO_CANCEL.equals(cpouBean.getRqstTpCd())) {
            fc = new NWZC150001CpouFindCondition("032");
            fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
            fc.addCondition("shipToCustCd01", cpouDtlBean.getShipToCustCd());
            fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
            fc.addCondition("rgtnStsCd02", RGTN_STS.TERMINATED);
        } else {
            fc = new NWZC150001CpouFindCondition("010");
            fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
            fc.addCondition("shipToCustCd01", cpouDtlBean.getShipToCustCd());

            fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        }

        final SHIP_TO_CUSTTMsgArray resShipToCustTMsgArray = localCache.shipToCustCache.getTMsgArray(fc);

        if (resShipToCustTMsgArray.getValidCount() > 0) {

            final SHIP_TO_CUSTTMsg resShipToCustTMsg = resShipToCustTMsgArray.no(NWZC150001CpouConstant.FIRST_INDEX);

            cpouDtlBean.setShipToLocNm(resShipToCustTMsg.locNm.getValue());
            cpouDtlBean.setShipToAddlLocNm(resShipToCustTMsg.addlLocNm.getValue());
            cpouDtlBean.setShipToFirstLineAddr(resShipToCustTMsg.firstLineAddr.getValue());
            cpouDtlBean.setShipToScdLineAddr(resShipToCustTMsg.scdLineAddr.getValue());
            cpouDtlBean.setShipToThirdLineAddr(resShipToCustTMsg.thirdLineAddr.getValue());
            cpouDtlBean.setShipToFrthLineAddr(resShipToCustTMsg.frthLineAddr.getValue());
            cpouDtlBean.setShipToFirstRefCmntTxt(resShipToCustTMsg.firstRefCmntTxt.getValue());
            cpouDtlBean.setShipToScdRefCmntTxt(resShipToCustTMsg.scdRefCmntTxt.getValue());
            cpouDtlBean.setShipToCtyAddr(resShipToCustTMsg.ctyAddr.getValue());
            cpouDtlBean.setShipToStCd(resShipToCustTMsg.stCd.getValue());
            cpouDtlBean.setShipToProvNm(resShipToCustTMsg.provNm.getValue());
            cpouDtlBean.setShipToPostCd(resShipToCustTMsg.postCd.getValue());
            cpouDtlBean.setShipToCtryCd(resShipToCustTMsg.ctryCd.getValue());

            final CNTYTMsg resCntyMsg = localCache.cntyCache.getTMsgByKey(cpouBean.getGlblCmpyCd(), resShipToCustTMsg.cntyPk.getValue());
            if (resCntyMsg != null) {
                cpouDtlBean.setShipToCntyNm(resCntyMsg.cntyNm.getValue());
            }
        }
    }

    /**
     * Setting of Ship To information (Header)
     * 
     * <pre>
     * It retrieves to SHIP_TO_CUST when it is not DropShip and the acquired value is set to the item to which dataBean corresponds.
     * It doesn't process it for DropShip.
     * </pre>
     * @param cpouBean NWZC150001CpouBean
     */
    private static void copyShipToDataByNoDropShip(NWZC150001CpouBean cpouBean, NWZC150001CpouLocalCache localCache) {

        if (hasValue(cpouBean.getAddShipToCustCd()) && ZYPConstant.FLG_ON_Y.equals(cpouBean.getAddDropShipFlg())) {
            return;

        } else if (hasValue(cpouBean.getAddShipToCustCd()) && ZYPConstant.FLG_OFF_N.equals(cpouBean.getAddDropShipFlg())) {

            final NWZC150001CpouFindCondition fc;
            if (NWZC150001CpouConstant.CPO_CANCEL.equals(cpouBean.getRqstTpCd())) {
                fc = new NWZC150001CpouFindCondition("032");
                fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
                fc.addCondition("shipToCustCd01", cpouBean.getAddShipToCustCd());
                fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
                fc.addCondition("rgtnStsCd02", RGTN_STS.TERMINATED);
            } else {
                fc = new NWZC150001CpouFindCondition("010");
                fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
                fc.addCondition("shipToCustCd01", cpouBean.getAddShipToCustCd());
                fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
            }

            SHIP_TO_CUSTTMsgArray resShipToCustTMsgArray = localCache.shipToCustCache.getTMsgArray(fc);

            if (resShipToCustTMsgArray.getValidCount() > 0) {

                SHIP_TO_CUSTTMsg resShipToCustTMsg = resShipToCustTMsgArray.no(0);

                cpouBean.setAddShipToLocNm(resShipToCustTMsg.locNm.getValue());
                cpouBean.setAddShipToAddlLocNm(resShipToCustTMsg.addlLocNm.getValue());
                cpouBean.setAddShipToFirstLineAddr(resShipToCustTMsg.firstLineAddr.getValue());
                cpouBean.setAddShipToScdLineAddr(resShipToCustTMsg.scdLineAddr.getValue());
                cpouBean.setAddShipToThirdLineAddr(resShipToCustTMsg.thirdLineAddr.getValue());
                cpouBean.setAddShipToFrthLineAddr(resShipToCustTMsg.frthLineAddr.getValue());
                cpouBean.setAddShipTo01RefCmntTxt(resShipToCustTMsg.firstRefCmntTxt.getValue());
                cpouBean.setAddShipTo02RefCmntTxt(resShipToCustTMsg.scdRefCmntTxt.getValue());
                cpouBean.setAddShipToCtyAddr(resShipToCustTMsg.ctyAddr.getValue());
                cpouBean.setAddShipToStCd(resShipToCustTMsg.stCd.getValue());
                cpouBean.setAddShipToProvNm(resShipToCustTMsg.provNm.getValue());
                cpouBean.setAddShipToPostCd(resShipToCustTMsg.postCd.getValue());
                cpouBean.setAddShipToCtryCd(resShipToCustTMsg.ctryCd.getValue());

                CNTYTMsg resCntyMsg = localCache.cntyCache.getTMsgByKey(cpouBean.getGlblCmpyCd(), resShipToCustTMsg.cntyPk.getValue());
                if (resCntyMsg != null) {
                    cpouBean.setAddShipToCntyNm(resCntyMsg.cntyNm.getValue());
                }
            }

        } else if (!hasValue(cpouBean.getAddShipToCustCd()) && ZYPConstant.FLG_ON_Y.equals(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getDropShipFlg())) {

            cpouBean.setAddShipToCustCd(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToCustCd());
            cpouBean.setAddDropShipFlg(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getDropShipFlg());
            cpouBean.setAddShipToLocNm(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToLocNm());
            cpouBean.setAddShipToAddlLocNm(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToAddlLocNm());
            cpouBean.setAddShipToFirstLineAddr(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToFirstLineAddr());
            cpouBean.setAddShipToScdLineAddr(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToScdLineAddr());
            cpouBean.setAddShipToThirdLineAddr(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToThirdLineAddr());
            cpouBean.setAddShipToFrthLineAddr(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToFrthLineAddr());
            cpouBean.setAddShipToCtyAddr(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToCtyAddr());
            cpouBean.setAddShipToStCd(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToStCd());
            cpouBean.setAddShipToPostCd(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToPostCd());
            cpouBean.setAddShipToCtryCd(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToCtryCd());
            cpouBean.setAddShipToCntyNm(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToCntyNm());
            cpouBean.setAddShipToProvNm(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToProvNm());
            cpouBean.setAddShipTo01RefCmntTxt(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToFirstRefCmntTxt());
            cpouBean.setAddShipTo02RefCmntTxt(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToScdRefCmntTxt());

        } else if (!hasValue(cpouBean.getAddShipToCustCd()) && ZYPConstant.FLG_OFF_N.equals(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getDropShipFlg())) {

            cpouBean.setAddShipToCustCd(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToCustCd());
            cpouBean.setAddDropShipFlg(cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getDropShipFlg());

            final NWZC150001CpouFindCondition fc;
            if (NWZC150001CpouConstant.CPO_CANCEL.equals(cpouBean.getRqstTpCd())) {
                fc = new NWZC150001CpouFindCondition("032");
                fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
                fc.addCondition("shipToCustCd01", cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToCustCd());
                fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
                fc.addCondition("rgtnStsCd02", RGTN_STS.TERMINATED);
            } else {
                fc = new NWZC150001CpouFindCondition("010");
                fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
                fc.addCondition("shipToCustCd01", cpouBean.getDtlBeanList().get(NWZC150001CpouConstant.FIRST_INDEX).getShipToCustCd());
                fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
            }

            SHIP_TO_CUSTTMsgArray resShipToCustTMsgArray = localCache.shipToCustCache.getTMsgArray(fc);

            if (resShipToCustTMsgArray.getValidCount() > 0) {

                SHIP_TO_CUSTTMsg resShipToCustTMsg = resShipToCustTMsgArray.no(NWZC150001CpouConstant.FIRST_INDEX);

                cpouBean.setAddShipToLocNm(resShipToCustTMsg.locNm.getValue());
                cpouBean.setAddShipToAddlLocNm(resShipToCustTMsg.addlLocNm.getValue());
                cpouBean.setAddShipToFirstLineAddr(resShipToCustTMsg.firstLineAddr.getValue());
                cpouBean.setAddShipToScdLineAddr(resShipToCustTMsg.scdLineAddr.getValue());
                cpouBean.setAddShipToThirdLineAddr(resShipToCustTMsg.thirdLineAddr.getValue());
                cpouBean.setAddShipToFrthLineAddr(resShipToCustTMsg.frthLineAddr.getValue());
                cpouBean.setAddShipTo01RefCmntTxt(resShipToCustTMsg.firstRefCmntTxt.getValue());
                cpouBean.setAddShipTo02RefCmntTxt(resShipToCustTMsg.scdRefCmntTxt.getValue());
                cpouBean.setAddShipToCtyAddr(resShipToCustTMsg.ctyAddr.getValue());
                cpouBean.setAddShipToStCd(resShipToCustTMsg.stCd.getValue());
                cpouBean.setAddShipToProvNm(resShipToCustTMsg.provNm.getValue());
                cpouBean.setAddShipToPostCd(resShipToCustTMsg.postCd.getValue());
                cpouBean.setAddShipToCtryCd(resShipToCustTMsg.ctryCd.getValue());

                CNTYTMsg resCntyMsg = localCache.cntyCache.getTMsgByKey(cpouBean.getGlblCmpyCd(), resShipToCustTMsg.cntyPk.getValue());
                if (resCntyMsg != null) {
                    cpouBean.setAddShipToCntyNm(resCntyMsg.cntyNm.getValue());
                }
            }
        }
    }

    /**
     * get Internal Flag
     * 
     * <pre>
     * In Case internal cusutomer, set internal flag.
     * </pre>
     * @param cpouBean NWZC150001CpouBean
     * @param pMsg NWZC150001PMsg
     */
    private static void getBillToItrlFlg(NWZC150001CpouBean cpouBean, NWZC150001PMsg pMsg, NWZC150001CpouLocalCache localCache, S21ApiMessageIdMgr msgIdMgr) {

        final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("024");
        fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
        fc.addCondition("billToCustCd01", cpouBean.getBillToCustCd());
        fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        final BILL_TO_CUSTTMsgArray resBillToCustTMsgArray = localCache.billToCustCache.getTMsgArray(fc);

        if (resBillToCustTMsgArray.getValidCount() == 0) {
            msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0608E, pMsg);
        } else if (BIZ_RELN_TP.INTERNCUST.equals(resBillToCustTMsgArray.no(NWZC150001CpouConstant.FIRST_INDEX).bizRelnTpCd.getValue())) {
            cpouBean.setItrlFlg(ZYPConstant.FLG_ON_Y);
        } else {
            cpouBean.setItrlFlg(ZYPConstant.FLG_OFF_N);
        }
    }

//    /**
//     * Order Fulfillment Level Code acquisition
//     * 
//     * <pre>
//     * SELL_TO_CUST is retrieved, and acquired Order Fulfillment Level Code is returned.
//     * It doesn't process it when already set as a parameter.
//     * </pre>
//     * @param cpouBean NWZC150001CpouBean
//     * @return Order Fulfillment Level Code（String）
//     */
//    private String getOrdFuflLvlCd(NWZC150001CpouBean cpouBean, NWZC150001CpouLocalCache localCache) {
//
//        if (hasValue(cpouBean.getOrdFuflLvlCd())) {
//            return cpouBean.getOrdFuflLvlCd();
//        }
//
//        final NWZC150001CpouFindCondition fc;
//        if (NWZC150001CpouConstant.CPO_CANCEL.equals(cpouBean.getRqstTpCd())) {
//            fc = new NWZC150001CpouFindCondition("035");
//            fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
//            fc.addCondition("sellToCustCd01", cpouBean.getSellToCustCd());
//            fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
//            fc.addCondition("rgtnStsCd02", RGTN_STS.TERMINATED);
//        } else {
//            fc = new NWZC150001CpouFindCondition("008");
//            fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
//            fc.addCondition("sellToCustCd01", cpouBean.getSellToCustCd());
//            fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
//        }
//
//        final SELL_TO_CUSTTMsgArray resSellToCustTMsgArray = localCache.sellToCustCache.getTMsgArray(fc);
//
//        if (resSellToCustTMsgArray.getValidCount() == 0) {
//            return "";
//        }
//        return resSellToCustTMsgArray.no(NWZC150001CpouConstant.FIRST_INDEX).ordFuflLvlCd.getValue();
//    }
//
//    /**
//     * get Sell to First Reference Comment Text
//     * 
//     * <pre>
//     * SELL_TO_CUST is retrieved, and acquired First Reference Comment Text is returned.
//     * It doesn't process it when already set as a parameter.
//     * </pre>
//     * @param cpouBean NWZC150001CpouBean
//     * @return Sell to First Reference Comment Text（String）
//     */
//    private String getSellToFirstRefCmntTxt(NWZC150001CpouBean cpouBean) {
//
//        if (hasValue(cpouBean.getSellToFirstRefCmntTxt())) {
//            return cpouBean.getSellToFirstRefCmntTxt();
//        }
//
//        final NWZC150001CpouFindCondition fc;
//        if (NWZC150001CpouConstant.CPO_CANCEL.equals(cpouBean.getRqstTpCd())) {
//            fc = new NWZC150001CpouFindCondition("035");
//            fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
//            fc.addCondition("sellToCustCd01", cpouBean.getSellToCustCd());
//            fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
//            fc.addCondition("rgtnStsCd02", RGTN_STS.TERMINATED);
//        } else {
//            fc = new NWZC150001CpouFindCondition("008");
//            fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
//            fc.addCondition("sellToCustCd01", cpouBean.getSellToCustCd());
//            fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
//        }
//
//        final SELL_TO_CUSTTMsgArray resSellToCustTMsgArray = localCache.sellToCustCache.getTMsgArray(fc);
//
//        if (resSellToCustTMsgArray.getValidCount() == 0) {
//            return "";
//        }
//        return resSellToCustTMsgArray.no(NWZC150001CpouConstant.FIRST_INDEX).firstRefCmntTxt.getValue();
//    }
//
//
//    /**
//     * get Sell to Second Reference Comment Text
//     * 
//     * <pre>
//     * SELL_TO_CUST is retrieved, and acquired Second Reference Comment Text is returned.
//     * It doesn't process it when already set as a parameter.
//     * </pre>
//     * @param cpouBean NWZC150001CpouBean
//     * @return Sell to Second Reference Comment Text（String）
//     */
//    private String getSellToScdRefCmntTxt(NWZC150001CpouBean cpouBean) {
//
//        if (hasValue(cpouBean.getSellToScdRefCmntTxt())) {
//            return cpouBean.getSellToScdRefCmntTxt();
//        }
//
//        final NWZC150001CpouFindCondition fc;
//        if (NWZC150001CpouConstant.CPO_CANCEL.equals(cpouBean.getRqstTpCd())) {
//            fc = new NWZC150001CpouFindCondition("035");
//            fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
//            fc.addCondition("sellToCustCd01", cpouBean.getSellToCustCd());
//            fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
//            fc.addCondition("rgtnStsCd02", RGTN_STS.TERMINATED);
//        } else {
//            fc = new NWZC150001CpouFindCondition("008");
//            fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
//            fc.addCondition("sellToCustCd01", cpouBean.getSellToCustCd());
//            fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
//        }
//
//        final SELL_TO_CUSTTMsgArray resSellToCustTMsgArray = localCache.sellToCustCache.getTMsgArray(fc);
//
//        if (resSellToCustTMsgArray.getValidCount() == 0) {
//            return "";
//        }
//
//        return resSellToCustTMsgArray.no(NWZC150001CpouConstant.FIRST_INDEX).scdRefCmntTxt.getValue();
//    }
    private static SELL_TO_CUSTTMsg getSellToCustRecord(NWZC150001CpouBean cpouBean, NWZC150001CpouLocalCache localCache) {

        final NWZC150001CpouFindCondition fc;
        if (NWZC150001CpouConstant.CPO_CANCEL.equals(cpouBean.getRqstTpCd())) {
            fc = new NWZC150001CpouFindCondition("035");
            fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
            fc.addCondition("sellToCustCd01", cpouBean.getSellToCustCd());
            fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
            fc.addCondition("rgtnStsCd02", RGTN_STS.TERMINATED);
        } else {
            fc = new NWZC150001CpouFindCondition("008");
            fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
            fc.addCondition("sellToCustCd01", cpouBean.getSellToCustCd());
            fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        }

        final SELL_TO_CUSTTMsgArray resSellToCustTMsgArray = localCache.sellToCustCache.getTMsgArray(fc);
        if (resSellToCustTMsgArray != null && resSellToCustTMsgArray.getValidCount() > 0) {
            return resSellToCustTMsgArray.no(NWZC150001CpouConstant.FIRST_INDEX);
        } else {
            return null;
        }
    }

    /**
     * get COA Affiliate Code
     * @param cpoBean NWZC150001CpouBean
     * @return COA Affiliate Code（String）
     */
    private static String getBillToAffiliateCd(NWZC150001CpouBean cpoBean, NWZC150001CpouLocalCache localCache) {

        BILL_TO_CUSTTMsg billToCustTMsg = getBillToCustRecord(cpoBean, localCache);
        if (billToCustTMsg != null && hasValue(billToCustTMsg.coaAfflCd)) {
            return billToCustTMsg.coaAfflCd.getValue();
        } else {
            return null;
        }
    }

    private static BILL_TO_CUSTTMsg getBillToCustRecord(NWZC150001CpouBean cpoBean, NWZC150001CpouLocalCache localCache) {

        final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("024");
        fc.addCondition("glblCmpyCd01", cpoBean.getGlblCmpyCd());
        fc.addCondition("billToCustCd01", cpoBean.getBillToCustCd());
        fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        final BILL_TO_CUSTTMsgArray resBillToCustTMsgArray = localCache.billToCustCache.getTMsgArray(fc);
        if (resBillToCustTMsgArray != null && resBillToCustTMsgArray.getValidCount() > 0) {
            return resBillToCustTMsgArray.no(NWZC150001CpouConstant.FIRST_INDEX);
        } else {
            return null;
        }
    }

    // 2017/05/15 S21_NA#Review structure Lv.2 RS#8390 Del Start
//    /**
//     * CPO Order Line Number , CPO Order Line Sub Number Numbering
//     * 
//     * <pre>
//     * Numbering of Order Line Number and CPO Order Line Sub Number is done.
//     * </pre>
//     * @param cpouBean NWZC150001CpouBean
//     */
//    private static void reNewOrderLineNumber(NWZC150001CpouBean cpouBean) {
//
//        // int num = 0; S21_NA#2846#31
//        String cpoDtlLineNum = "000"; // S21_NA#2846#31
//        int subNum = 0;
//        String processLineNumber = null;
//
//        BigDecimal dsCpoLineNum = BigDecimal.ZERO; // 2016/02/05 S21_NA#3255 Add
//        // String prevDsOrdPosNum = ""; // 2016/02/05 S21_NA#3255 Add -> 2016/08/02 S21_NA#12637 Del
//        
//        List<String> dsOrdPosnNumList = new ArrayList<String>(); // 2016/08/02 S21_NA#12637 Add Start
//
//        for (int i = 0; i < cpouBean.getDtlBeanList().size(); i++) {
//
//            NWZC150001CpouDetailBean detailMsg = cpouBean.getDtlBeanList().get(i);
//            // 2016/08/02 S21_NA#12637 Del Start
////            // 2016/02/05 S21_NA#3255 Add Start
////            String curDsOrdPosNum = detailMsg.getDsOrdPosnNum();
////            if (!prevDsOrdPosNum.equals(curDsOrdPosNum)) {
////                dsCpoLineNum = BigDecimal.ZERO;
////                prevDsOrdPosNum = curDsOrdPosNum;
////            }
////            // 2016/02/05 S21_NA#3255 Add End
//            // 2016/08/02 S21_NA#12637 Del End
//
//            // 10/14/2009 Update 683
//            if (NWZC150001CpouConstant.CPO_SAVE.equals(cpouBean.getRqstTpCd()) && NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(detailMsg.getDtlRqstTpCd())) {
//                continue;
//            }
//
//            String orderLineNumber = detailMsg.getCpoDtlLineNum();
//            String orderLineSubNumber = detailMsg.getCpoDtlLineSubNum();
//
//            if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(orderLineSubNumber)) {
//                // num++; S21_NA#2846#31
//                cpoDtlLineNum = NWXC150001NumberingUtil.getNextCpoDtlLineNum(cpoDtlLineNum); // S21_NA#2846#31
//                subNum = 0;
//                processLineNumber = orderLineNumber;
////                dsCpoLineNum = dsCpoLineNum.add(BigDecimal.ONE); // 2016/02/05 S21_NA#3255 Add ->  2016/08/02 S21_NA#12637 Del
//            } else if (orderLineNumber.equals(processLineNumber)) {
//                subNum++;
//            } else {
//                // num++; S21_NA#2846#31
//                cpoDtlLineNum = NWXC150001NumberingUtil.getNextCpoDtlLineNum(cpoDtlLineNum); // S21_NA#2846#31
//                subNum = 1;
////                dsCpoLineNum = dsCpoLineNum.add(BigDecimal.ONE); // 2016/02/05 S21_NA#3255 Add ->  2016/08/02 S21_NA#12637 Del
//            }
//
//            // String strNum = ZYPCommonFunc.leftPad(String.valueOf(num), 3, "0");
//            String strNum = cpoDtlLineNum;
//            String strSubNum = ZYPCommonFunc.leftPad(String.valueOf(subNum), 3, "0");
//
//            detailMsg.setReNumCpoDtlLineNum(strNum);
//            detailMsg.setReNumCpoDtlLineSubNum(strSubNum);
//            detailMsg.setCpoDtlLineNum(detailMsg.getCpoDtlLineNum());
//            detailMsg.setCpoDtlLineSubNum(detailMsg.getCpoDtlLineSubNum());
////            detailMsg.setDsCpoLineNum(dsCpoLineNum); // 2016/02/05 S21_NA#3255 Add ->  2016/08/02 S21_NA#12637 Del
//
//            // 2016/08/02 S21_NA#12637 Add Start
//            if (ZYPCommonFunc.hasValue(detailMsg.getDsOrdPosnNum()) //
//                    && !dsOrdPosnNumList.contains(detailMsg.getDsOrdPosnNum())) {
//                dsOrdPosnNumList.add(detailMsg.getDsOrdPosnNum());
//            }
//            // 2016/08/02 S21_NA#12637 Add End
//        }
//
//        // 2016/08/02 S21_NA#12637 Add Start
//        // Re-Number DS CPO LineNum
//        for (String dsOrdPosnNum : dsOrdPosnNumList) {
//            dsCpoLineNum = BigDecimal.ZERO;
//            subNum = -1;
//            for (int i = 0; i < cpouBean.getDtlBeanList().size(); i++) {
//                NWZC150001CpouDetailBean detailMsg = cpouBean.getDtlBeanList().get(i);
//                if (!S21StringUtil.isEquals(dsOrdPosnNum, detailMsg.getDsOrdPosnNum())) {
//                    continue;
//                }
//
//                String orderLineNumber = detailMsg.getCpoDtlLineNum();
//                String orderLineSubNumber = detailMsg.getCpoDtlLineSubNum();
//
//                if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(orderLineSubNumber)) {
//                    subNum = 0;
//                    processLineNumber = orderLineNumber;
//                    dsCpoLineNum = dsCpoLineNum.add(BigDecimal.ONE);
//                } else if (orderLineNumber.equals(processLineNumber)) {
//                    subNum++;
//                } else {
//                    dsCpoLineNum = dsCpoLineNum.add(BigDecimal.ONE);
//                    subNum = -1;
//                }
//                detailMsg.setDsCpoLineNum(dsCpoLineNum);
//                if (subNum > 0) {
//                    detailMsg.setDsCpoLineSubNum(BigDecimal.valueOf(subNum));
//                }
//            }
//        }            
//        // 2016/08/02 S21_NA#12637 Add End
//
//        // S21_NA#3067 renumbering reference line number.
//        for (NWZC150001CpouDetailBean refMsg : cpouBean.getDtlBeanList()) {
//
//            String origRefCpoDtlLineNum = refMsg.getRefCpoDtlLineNum() + refMsg.getRefCpoDtlLineSubNum();
//            if (S21StringUtil.isEmpty(origRefCpoDtlLineNum)) {
//                // no reference line
//                continue;
//            }
//
//            // search new reference line
//            for (NWZC150001CpouDetailBean sourceMsg : cpouBean.getDtlBeanList()) {
//                String origCpoDtlLineNum = sourceMsg.getCpoDtlLineNum() + sourceMsg.getCpoDtlLineSubNum();
//                if (!S21StringUtil.isEquals(origRefCpoDtlLineNum, origCpoDtlLineNum)) {
//                    continue;
//                }
//
//                // original reference line is found. (include cancel
//                // request line -> new line number is empty.)
//                String newCpoDtlLineNum = sourceMsg.getReNumCpoDtlLineNum() + sourceMsg.getReNumCpoDtlLineSubNum();
//
//                if (S21StringUtil.isNotEmpty(newCpoDtlLineNum)) {
//
//                    // re:numbering reference line number.
//                    refMsg.setRefCpoDtlLineNum(sourceMsg.getReNumCpoDtlLineNum());
//                    refMsg.setRefCpoDtlLineSubNum(sourceMsg.getReNumCpoDtlLineSubNum());
//
//                    // re:numbering display reference line number(DS).
//                    String dplyLineRefNum = editDplyLineRefNum(sourceMsg.getDsOrdPosnNum(), sourceMsg.getDsCpoLineNum(), sourceMsg.getDsCpoLineSubNum());
//                    refMsg.setDplyLineRefNum(dplyLineRefNum);
//                } else {
//
//                    // clear reference line number.
//                    refMsg.setRefCpoDtlLineNum("");
//                    refMsg.setRefCpoDtlLineSubNum("");
//
//                    // clear display reference line number(DS).
//                    refMsg.setDplyLineRefNum("");
//                }
//            }
//        }
//    }
    // 2017/05/15 S21_NA#Review structure Lv.2 RS#8390 Del End

    // S21_NA#3067 edit display reference line number(DS).
    private static String editDplyLineRefNum(String dsOrdPosnNum, BigDecimal dsCpoLineNum, BigDecimal dsCpoLineSubNum) {

        if (!ZYPCommonFunc.hasValue(dsOrdPosnNum) || !ZYPCommonFunc.hasValue(dsCpoLineNum)) {
            return "";
        }

        StringBuilder dplyLineRefNum = new StringBuilder();
        concatWithSeparator(dplyLineRefNum, dsOrdPosnNum, ".");
        concatWithSeparator(dplyLineRefNum, dsCpoLineNum.toPlainString(), ".");
        if (dsCpoLineSubNum != null) {
            concatWithSeparator(dplyLineRefNum, dsCpoLineSubNum.toPlainString(), ".");
        }
        return dplyLineRefNum.toString();
    }

    // S21_NA#3067 concat util.
    private static void concatWithSeparator(StringBuilder target, String element, String separator) {

        if (!S21StringUtil.isEmpty(element)) {
            if (target.length() > 0) {
                target.append(separator);
            }
            target.append(element);
        }
    }

    /**
     * The representative commodity code is acquired.
     * 
     * <pre>
     * The commodity code is acquired by using common parts (NWXOrdTakeMdseTMsgThreadLocalCache).
     * </pre>
     * @param dataBean NWZC150001CpouBean
     * @param mdseCd String
     */
    private static String getOrdTakeMdse(String glblCmpyCd, String ordTakMdseCd) {

        String mdseCd = ordTakMdseCd;

        ORD_TAKE_MDSETMsg ordTakMdseMsg = NWXOrdTakeMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, ordTakMdseCd);
        if (ordTakMdseMsg != null) {
            mdseCd = ordTakMdseMsg.mdseCd.getValue();
        }

        return mdseCd;
    }

    // 2017/05/09 RS#8144 Del Start
//    /**
//     * Sales Rep or Sales Team TOC Code acquisition
//     * 
//     * <pre>
//     * Acquired Sales Rep or Sales Team TOC Code is returned by using common parts (NWXC001001DefaultTOC).
//     * It doesn't process it when already set as a parameter.
//     * </pre>
//     * @param cpoBean NWZC150001CpouBean
//     * @param cpoDtlBean NWZC150001CpouDetailBean
//     * @return Sales Rep or Sales Team TOC Code（String）
//     */
//    private static String getSlsRepOrSlsTeamTocCd(NWZC150001CpouBean cpoBean, //
//            NWZC150001CpouDetailBean cpoDtlBean,//
//            List<NWZC150002PMsg> resPMsgList, //
//            int idx, //
//            NWZC150001CpouLocalCache localCache) {
//
//        if (hasValue(cpoDtlBean.getSlsRepOrSlsTeamTocCd())) {
//            return cpoDtlBean.getSlsRepOrSlsTeamTocCd();
//        }
//
//        final String glblCmpyCd = cpoBean.getGlblCmpyCd();
//        final String mdseCd = cpoDtlBean.getMdseCdForMstrSrch();
//        final String sellToCustCd = cpoBean.getSellToCustCd();
//        final String billToCustCd = cpoBean.getBillToCustCd();
//
//        // cache
//        final StringBuilder cacheKeySb = new StringBuilder();
//        cacheKeySb.append(glblCmpyCd).append(",");
//        cacheKeySb.append(mdseCd).append(",");
//        cacheKeySb.append(sellToCustCd).append(",");
//        cacheKeySb.append(billToCustCd);
//
//        final String cacheKey = cacheKeySb.toString();
//
//        String tocCd = localCache.defaultTocCdCache.get(cacheKey);
//        if (tocCd == null) {
//            // START MODIFY M.Fuji [OM040]
//            // 20130121 Defect#123 M.Fuji Start
//            tocCd = NWXC001001DefaultTOC.getDefaultTOC(glblCmpyCd, mdseCd, sellToCustCd, billToCustCd);
//            //            NWZC207001PMsg othRepPMsg = new NWZC207001PMsg();
//            //            othRepPMsg.glblCmpyCd.setValue(glblCmpyCd);
//            //            othRepPMsg.mdseCd.setValue(mdseCd);
//            //            othRepPMsg.sellToCustCd.setValue(sellToCustCd);
//            //            othRepPMsg.billToCustCd.setValue(billToCustCd);
//            //
//            //            NWZC207001 othRepApi = new NWZC207001();
//            //            othRepApi.execute(othRepPMsg, ONBATCH_TYPE.ONLINE);
//            //
//            //            if (othRepPMsg.xxMsgIdList.getValidCount() > 0) {
//            //                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(othRepPMsg.xxMsgIdList.no(0).xxMsgId.getValue(), resPMsgList, idx);
//            //                return null;
//            //            }
//            //
//            //            for (int i = 0; i < othRepPMsg.A.getValidCount(); i++) {
//            //                if (ZYPConstant.FLG_ON_Y.equals(othRepPMsg.A.no(i).slsRepFlg_S1.getValue())) {
//            //                    tocCd = othRepPMsg.A.no(i).tocCd_S1.getValue();
//            //                }
//            //            }
//            // 20130121 Defect#123 M.Fuji End
//            // END MODIFY M.Fuji [OM040]
//            if (tocCd != null) {
//                localCache.defaultTocCdCache.put(cacheKey, tocCd);
//            }
//        }
//
//        return tocCd;
//    }
    // 2017/05/09 RS#8144 Del End

    private static void setDetailDefaultData(NWZC150001CpouBean cpouBean, //
            NWZC150001PMsg pMsg, //
            List<NWZC150002PMsg> resPMsgList, //
            NWZC150001CpouLocalCache localCache, //
            S21ApiMessageIdMgr msgIdMgr, //
            ONBATCH_TYPE onBatchType) {

        /**************************************************
         * CPO_DTL
         **************************************************/
        // S21_NA#2896 virtual warehouse -> flip 10digits merchandise
        // 2018/01/24 S21_NA#23706 Fixing Point Start
        // 2018/01/18 S21_NA#23555 Mod Start
        String notHardAllocWh = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_NO_HARD_ALLOC_WH_CD, cpouBean.getGlblCmpyCd());
        String crBillOnlyWhWh = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_CR_AND_BILL_ONLY_DUMMY_WH_CD, cpouBean.getGlblCmpyCd());
        // 2018/01/18 S21_NA#23555 Mod End
        List<String> crBillOnlyWhWhList = S21StringUtil.toList(crBillOnlyWhWh);
        // 2018/01/24 S21_NA#23706 Fixing Point End
        List<String> notHardAllocWhList = S21StringUtil.toList(notHardAllocWh);

        String coaAfflCd = getBillToAffiliateCd(cpouBean, localCache); // S21_NA#10321-27 Add
        NWZC150001CpouDetailBean setItemMsg = null;
        String setItemLineNum = null;

//        for (int i = 0, end = cpouBean.getDtlBeanList().size(); i < end; i++) {
        int i = 0;
        for (NWZC150001CpouDetailBean cpouDtlBean : cpouBean.getDtlBeanList()) {

//            NWZC150001CpouDetailBean cpouDtlBean = cpouBean.getDtlBeanList().get(i);

            if (!hasValue(cpouDtlBean.getCpoOrdTpCd())) {
                cpouDtlBean.setCpoOrdTpCd(cpouBean.getCpoOrdTpCd());
            }

            // When Set Item
            if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpouDtlBean.getCpoDtlLineSubNum())) {
                setItemLineNum = cpouDtlBean.getCpoDtlLineNum();
            } else {
                // When Set Component
                if (hasValue(setItemLineNum) && cpouDtlBean.getCpoDtlLineNum().equals(setItemLineNum)) {
                    // Processing is continued with parents'
                    // information maintained.
                } else {
                    setItemMsg = null;
                }
            }

            cpouDtlBean.setGlblCmpyCd(cpouBean.getGlblCmpyCd());
            if (!hasValue(cpouDtlBean.getOrigMdseCd())) {
                cpouDtlBean.setOrigMdseCd(cpouDtlBean.getMdseCd());
            }

            // MdseCdForMstrSrch
            cpouDtlBean.setMdseCdForMstrSrch(getOrdTakeMdse(cpouBean.getGlblCmpyCd(), cpouDtlBean.getMdseCd()));

            // S21_NA#2846#5
            // S21_NA#2471, 2502
            // if
            // (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpouDtlBean.getCpoDtlLineSubNum()))
            // {
            // cmptMdseQtyMap = (HashMap<String, BigDecimal>)
            // getCmpsnData(cpouBean, i, cmptMdseQtyMap);
            // } else if (hasValue(setItemLineNum) &&
            // cpouDtlBean.getCpoDtlLineNum().equals(setItemLineNum))
            // {
            // BigDecimal cmptMdseQty =
            // cmptMdseQtyMap.get(cpouDtlBean.getMdseCd());
            // if (!hasValue(cmptMdseQty)) {
            // cmptMdseQty =
            // cmptMdseQtyMap.get(cpouDtlBean.getMdseCd().substring(0,
            // 8));
            // }
            // BigDecimal cmptQty =
            // setItemMsg.getOrdQty().multiply(cmptMdseQty);
            // cpouDtlBean.setOrdQty(cmptQty);
            // }

            // 2017/05/09 RS#8144 Del Start
//            if (!hasValue(cpouDtlBean.getSlsRepOrSlsTeamTocCd())) {
//                // 20130121 Defect#123 M.Fuji Start
//                String toc = getSlsRepOrSlsTeamTocCd(cpouBean, cpouDtlBean, resPMsgList, i, localCache);
//                // 20130121 Defect#123 M.Fuji End
//                if (!hasValue(toc)) {
//                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0642E, resPMsgList, i);
//                }
//                cpouDtlBean.setSlsRepOrSlsTeamTocCd(toc);
//            }
            // 2017/05/09 RS#8144 Del End

            // Mod Start 2017/10/30 QC#22140
//            final MDSETMsg mdseMsg = NWZC150001CpouExistsCdInDbCheck.getMdse(cpouBean.getRqstTpCd(), cpouBean.getGlblCmpyCd(), cpouDtlBean.getMdseCdForMstrSrch());
            final MDSETMsg mdseMsg = NWZC150001CpouExistsCdInDbCheck.getMdse(cpouBean.getRqstTpCd(), cpouBean.getGlblCmpyCd(), cpouDtlBean.getMdseCdForMstrSrch(), cpouBean.getCpoSrcTpCd());
            // Mod End 2017/10/30 QC#22140

            // 2016/02/18 S21_NA#2336 Del Start
            // Search MDSE(original)
//            final MDSETMsg mdseMsgOrg = getMdse(cpouBean.getRqstTpCd(), cpouBean.getGlblCmpyCd(), getOrdTakeMdse(cpouBean.getGlblCmpyCd(), cpouDtlBean.getOrigMdseCd()));
            // 2016/02/18 S21_NA#2336 Del End

            if (ZYPConstant.FLG_OFF_N.equals(mdseMsg.invtyCtrlFlg.getValue()) || (notHardAllocWhList != null && notHardAllocWhList.contains(cpouDtlBean.getRtlWhCd()))) { // S21_NA#2896
                cpouDtlBean.setMdseCd(mdseMsg.mdseCd.getValue());
                // 2016/09/27 S21_NA#11655 Del Start
                //cpouDtlBean.setOrigMdseCd(mdseMsg.mdseCd.getValue());
                // 2016/09/27 S21_NA#11655 Del End
            }

            // 20121127 M.Fuji WDS Solution#101 Sales BOM Start
            if (MDSE_TP.SALES_BOM.equals(mdseMsg.mdseTpCd.getValue())) {
                cpouDtlBean.setBomHeaderFlg(ZYPConstant.FLG_ON_Y);
            } else {
                cpouDtlBean.setBomHeaderFlg(ZYPConstant.FLG_OFF_N);
            }
            // 20121127 M.Fuji WDS Solution#101 Sales BOM End

            // S21_NA#10321-27 Del Start
//            // START ADD M.Fuji [Defect#2394]
//            // Search DS_MDSE_INFO(original)
//            final DS_MDSE_INFOTMsg dsMdseInfoMsgOrg = getDsMdseInfo(cpouBean.getGlblCmpyCd(), cpouDtlBean.getMdseCdForMstrSrch());
//            if (dsMdseInfoMsgOrg != null && hasValue(dsMdseInfoMsgOrg.assetMgtFlg)) {
//                cpouDtlBean.setAssetMgtFlg(dsMdseInfoMsgOrg.assetMgtFlg.getValue());
//            }
//            // END ADD M.Fuji [Defect#2394]
            // S21_NA#10321-27 Del End

            // S21_NA#800 delete start
            // START   ADD M.Fuji [OM0028]
            // if (hasValue(cpouDtlBean.getSvcConfigMstrPk())) {
            //    cpouDtlBean.setDsOrdPosnNum("");
            //}
            // S21_NA#800 delete end

            //                cpouDtlBean.setBaseCmptFlg(getBaseCmptFlg(cpouBean, cpouDtlBean));   // 2015/08/27 CSA Del
            // END   ADD M.Fuji [OM0028]

            // 20121126 M.Fuji WDS Solution Intangible Start
            if (hasValue(mdseMsg.thirdPtyVndDropFlg.getValue())) {
                cpouDtlBean.setMdseThirdPtyVndDropFlg(mdseMsg.thirdPtyVndDropFlg.getValue());
            } else {
                cpouDtlBean.setMdseThirdPtyVndDropFlg(ZYPConstant.FLG_OFF_N);
            }
            // 20121126 M.Fuji WDS Solution Intangible End

            // Intangible Flag
            if (ZYPConstant.FLG_OFF_N.equals(mdseMsg.invtyCtrlFlg.getValue()) && ZYPConstant.FLG_OFF_N.equals(mdseMsg.expItemFlg.getValue()) && MDSE_TP.REGULAR.equals(mdseMsg.mdseTpCd.getValue()) || ZYPConstant.FLG_ON_Y.equals(cpouDtlBean.getBomHeaderFlg())) {
                cpouDtlBean.setIntgFlg(ZYPConstant.FLG_ON_Y);
            } else {
                cpouDtlBean.setIntgFlg(ZYPConstant.FLG_OFF_N);
            }

            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            cpouDtlBean.setFifthProdCtrlCd(mdseMsg.fifthProdCtrlCd.getValue());
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End
            cpouDtlBean.setMdseNm(mdseMsg.mdseNm.getValue());
            // cpouDtlBean.setOrigMdseNm(mdseMsgOrg.mdseNm.getValue()); 2016/02/18 S21_NA#2336 Del


            // 09/21/2009 Uptete
            copyShipToDataByHeader(cpouBean, cpouDtlBean);

            copyShipToDataByNoDropShip(cpouBean, cpouDtlBean, localCache);

            // When SHIP TO information on heder is a blank, the
            // first value of details is reflected.
            if (!hasValue(cpouBean.getAddShipToCustCd())) {
                setHeaderShipTo(cpouBean);
            }

            if (!hasValue(cpouDtlBean.getRddDt()) && !hasValue(cpouDtlBean.getRsdDt())) {
                if (ZYPConstant.FLG_OFF_N.equals(cpouDtlBean.getIntgFlg())) {
                    cpouDtlBean.setRddDt(cpouBean.getAddRddDt());
                }
                cpouDtlBean.setRsdDt(cpouBean.getAddRsdDt());
            }
            if (!hasValue(cpouDtlBean.getRddDt()) && !hasValue(cpouDtlBean.getRsdDt())) {
                cpouDtlBean.setRsdDt(cpouBean.getSlsDt());
            }

            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            if (!hasValue(cpouDtlBean.getCancDelyLimitDt())) {
//                cpouDtlBean.setCancDelyLimitDt(cpouBean.getCancDelyLimitDt());
//            }
//            if (!hasValue(cpouDtlBean.getCancShipLimitDt())) {
//                cpouDtlBean.setCancShipLimitDt(cpouBean.getCancShipLimitDt());
//            }
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End
            if (!hasValue(cpouDtlBean.getStkStsCd())) {
                cpouDtlBean.setStkStsCd(STK_STS.GOOD);
            }
            cpouDtlBean.setCpoDtlCancFlg(ZYPConstant.FLG_OFF_N);
            if (!hasValue(cpouDtlBean.getCustUomCd())) {
                cpouDtlBean.setCustUomCd(PKG_UOM.EACH);
            }
            cpouDtlBean.setCpoDtlHldFlg(ZYPConstant.FLG_OFF_N);
            cpouDtlBean.setSubmtFlg(ZYPConstant.FLG_OFF_N);

            // Customer Purchase Order Type Code
            // 10/09/2009 Update
            // START MODIFY S.Yamamoto [OM004]
            // START MODIFY S.Yamamoto [#3375]
            //                if ((CPO_ORD_TP.SALES.equals(cpouBean.getCpoOrdTpCd()) || CPO_ORD_TP.RENTAL_DS.equals(cpouBean.getCpoOrdTpCd())) && hasValue(cpouDtlBean.getCoaAcctCd())) {
            //                    cpouDtlBean.setCpoOrdTpCd(CPO_ORD_TP.EXPENSE);
            //                }
            if (CPO_ORD_TP.SALES.equals(cpouBean.getCpoOrdTpCd()) && hasValue(cpouDtlBean.getCoaAcctCd())) {
                cpouDtlBean.setCpoOrdTpCd(CPO_ORD_TP.EXPENSE);
            }
            // END   MODIFY S.Yamamoto [#3375]
            // END   MODIFY S.Yamamoto [OM004]

            if (!hasValue(cpouDtlBean.getTaxFlg())) {
                cpouDtlBean.setTaxFlg(ZYPConstant.FLG_ON_Y);
            }
            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            if (DIST_TP.DISTRIBUTION.equals(mdseMsg.invtyDistTpCd.getValue()) && STK_STS.GOOD.equals(cpouDtlBean.getStkStsCd())) {
//                cpouDtlBean.setDistItemFlg(ZYPConstant.FLG_ON_Y);
//            }
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End

            // Price timing Code
            // 09/23/2009 Update
            // 20121130 M.Fuji WDS Solution#104,105 Pricing Start
            cpouDtlBean.setPrcTmgCd(PRC_COND.ORDER_TIME);
            // 20121130 M.Fuji WDS Solution#104,105 Pricing End

            // Parents of an intangible commodity and the set
            // commodity do not acquire default.
            // 09/02/2009 Update
            if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpouDtlBean.getCpoDtlLineSubNum()) && !hasValue(cpouDtlBean.getStkStsCd())) {
                cpouDtlBean.setStkStsCd(STK_STS.GOOD);
            }

            // 09/21/2009 Add Start
            if (!hasValue(cpouDtlBean.getShpgSvcLvlCd()) && hasValue(cpouBean.getAddShpgSvcLvlCd())) {
                cpouDtlBean.setShpgSvcLvlCd(cpouBean.getAddShpgSvcLvlCd());
            }

            // final NWZC046001_shipFromListPMsg shipFromListPMsg = getDefWH(cpouBean, cpouDtlBean, resPMsgList, i);

            // S21_NA#10321-27 Del Start
//            final NWZC046001_shipFromListPMsg shipFromListPMsg = null; // S21_NA#1331
//
//            if (shipFromListPMsg != null) {
//                if (!hasValue(cpouDtlBean.getInvtyLocCd())) {
//                    cpouDtlBean.setInvtyLocCd(shipFromListPMsg.xxShipFromCd.getValue());
//                }
//                if (!hasValue(cpouDtlBean.getShpgSvcLvlCd())) {
//                    cpouDtlBean.setShpgSvcLvlCd(shipFromListPMsg.shpgSvcLvlCd.getValue());
//                }
//                if (!hasValue(cpouDtlBean.getFrtChrgToCd())) {
//                    cpouDtlBean.setFrtChrgToCd(shipFromListPMsg.frtChrgToCd.getValue());
//                }
//                if (!hasValue(cpouDtlBean.getFrtChrgMethCd())) {
//                    cpouDtlBean.setFrtChrgMethCd(shipFromListPMsg.frtChrgMethCd.getValue());
//                }
//
//                cpouDtlBean.setDefInvtyLocCd(shipFromListPMsg.xxShipFromCd.getValue());
//                cpouDtlBean.setDefShpgSvcLvlCd(shipFromListPMsg.shpgSvcLvlCd.getValue());
//                cpouDtlBean.setDefFrtChrgToCd(shipFromListPMsg.frtChrgToCd.getValue());
//                cpouDtlBean.setDefFrtChrgMethCd(shipFromListPMsg.frtChrgMethCd.getValue());
//            }
            // S21_NA#10321-27 Del End

            // S21_NA#10321-27 Del Start
//            // START ADD M.Fuji [OM053]
//            if (!hasValue(cpouDtlBean.getFrtCondCd())) {
//                setDefaultFrtCondCd(cpouBean, cpouDtlBean);
//            }
//            // END ADD M.Fuji [OM053]
            // S21_NA#10321-27 Del End

            final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("001");
            fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
            fc.addCondition("invtyLocCd01", cpouDtlBean.getInvtyLocCd());

            final SHIP_FROM_LOC_LIST_VTMsgArray resShipFromLocListVTMsgArray = localCache.shipFromLocListVCache.getTMsgArray(fc);

            if (resShipFromLocListVTMsgArray.getValidCount() != 0) {
                if (LOC_TP.VENDOR.equals(resShipFromLocListVTMsgArray.no(NWZC150001CpouConstant.FIRST_INDEX).invtyLocTpCd.getValue())) {
                    cpouDtlBean.setThirdPtyVndDropYFlg(ZYPConstant.FLG_ON_Y);
                } else {
                    cpouDtlBean.setThirdPtyVndDropYFlg(ZYPConstant.FLG_OFF_N);
                }
            } else {
                cpouDtlBean.setThirdPtyVndDropYFlg(ZYPConstant.FLG_OFF_N);
            }

            if (ZYPConstant.FLG_ON_Y.equals(cpouDtlBean.getIntgFlg()) && ZYPConstant.FLG_OFF_N.equals(cpouDtlBean.getThirdPtyVndDropYFlg())) {
                cpouDtlBean.setRsdDt(cpouBean.getSlsDt());
                cpouDtlBean.setRddDt("");
            }

            if (!(CPO_SRC_TP.ORDER_ENTRY_SCREEN.equals(cpouBean.getCpoSrcTpCd()) || CPO_SRC_TP.TRIAL_OR_LOAN_REQUEST.equals(cpouBean.getCpoSrcTpCd()))) {
                if (hasValue(cpouDtlBean.getRddDt()) && FRT_CHRG_METH.PICK_UP_NO_CHARGE.equals(cpouDtlBean.getFrtChrgMethCd())) {
                    cpouDtlBean.setRsdDt(cpouDtlBean.getRddDt());
                    // Mod Start 2019/08/09 QC#52554
//                    cpouDtlBean.setRddDt("");
                    cpouDtlBean.setRddDt(cpouDtlBean.getRddDt());
                    // Mod End 2019/08/09 QC#52554
                }
            }

            // Compornent (The value of the set commodity is set
            // in the component).
            if (!NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpouDtlBean.getCpoDtlLineSubNum()) && setItemMsg != null) {

                cpouDtlBean.setPmtTermCashDiscCd(setItemMsg.getPmtTermCashDiscCd());
                cpouDtlBean.setPmtTermCd(setItemMsg.getPmtTermCd());
                cpouDtlBean.setCashDiscTermCd(setItemMsg.getCashDiscTermCd());

                cpouDtlBean.setDefPmtTermCd(setItemMsg.getDefPmtTermCd());
                cpouDtlBean.setDefCashDiscTermCd(setItemMsg.getDefCashDiscTermCd());

                if (!hasValue(cpouDtlBean.getCcyCd())) {
                    cpouDtlBean.setCcyCd(setItemMsg.getCcyCd());
                }
                if (!hasValue(cpouDtlBean.getTaxFlg())) {
                    cpouDtlBean.setTaxFlg(setItemMsg.getTaxFlg());
                }
                if (!hasValue(cpouDtlBean.getCoaAcctCd())) {
                    cpouDtlBean.setCoaAcctCd(setItemMsg.getCoaAcctCd());
                }
                if (!hasValue(cpouDtlBean.getCoaProjCd())) {
                    cpouDtlBean.setCoaProjCd(setItemMsg.getCoaProjCd());
                }
                // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                if (ZYPConstant.FLG_ON_Y.equals(setItemMsg.getDistItemFlg())) {
//                    cpouDtlBean.setDistItemFlg(setItemMsg.getDistItemFlg());
//                }
                // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
                // 09/23/2009 Update Start
                if (CPO_ORD_TP.EXPENSE.equals(setItemMsg.getCpoOrdTpCd())) {
                    cpouDtlBean.setCpoOrdTpCd(setItemMsg.getCpoOrdTpCd());
                }
                // 09/23/2009 Update End
                cpouDtlBean.setSetMdseCd(setItemMsg.getMdseCd());
                cpouDtlBean.setPrcTmgCd(setItemMsg.getPrcTmgCd());
                cpouDtlBean.setManPrcFlg(setItemMsg.getManPrcFlg());

                // 01/25/2010 Update Start
                if (ZYPConstant.FLG_ON_Y.equals(setItemMsg.getSetItemShipCpltFlg()) && !(ZYPConstant.FLG_ON_Y.equals(cpouDtlBean.getIntgFlg()) && ZYPConstant.FLG_OFF_N.equals(cpouDtlBean.getThirdPtyVndDropYFlg()))) {
                    cpouDtlBean.setInvtyLocCd(setItemMsg.getInvtyLocCd());
                    cpouDtlBean.setThirdPtyVndDropYFlg(setItemMsg.getThirdPtyVndDropYFlg());
                    cpouDtlBean.setShpgSvcLvlCd(setItemMsg.getShpgSvcLvlCd());
                    cpouDtlBean.setFrtChrgToCd(setItemMsg.getFrtChrgToCd());
                    cpouDtlBean.setFrtChrgMethCd(setItemMsg.getFrtChrgMethCd());
                    cpouDtlBean.setDefInvtyLocCd(setItemMsg.getInvtyLocCd());
                    cpouDtlBean.setDefShpgSvcLvlCd(setItemMsg.getShpgSvcLvlCd());
                    cpouDtlBean.setDefFrtChrgToCd(setItemMsg.getFrtChrgToCd());
                    cpouDtlBean.setDefFrtChrgMethCd(setItemMsg.getFrtChrgMethCd());
                    // RDD
                    cpouDtlBean.setRddDt(setItemMsg.getRddDt());
                    // RSD
                    cpouDtlBean.setRsdDt(setItemMsg.getRsdDt());
                    // START ADD S.Yamamoto [OM003]
                    cpouDtlBean.setFrtCondCd(setItemMsg.getFrtCondCd());
                    // END   ADD S.Yamamoto [OM003]
                }
                // 01/25/2010 Update End

                cpouDtlBean.setShipCpltCd(setItemMsg.getShipCpltCd());
            }

            setSetItemShipCpltFlg(cpouBean, cpouDtlBean, setItemMsg);

            // 2018/01/18 S21_NA#23555 Mod Start
            // if ((NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpouDtlBean.getCpoDtlLineSubNum()) && ZYPConstant.FLG_OFF_N.equals(cpouDtlBean.getSetItemShipCpltFlg())) || (ZYPConstant.FLG_ON_Y.equals(cpouDtlBean.getIntgFlg()) && ZYPConstant.FLG_OFF_N.equals(cpouDtlBean.getThirdPtyVndDropYFlg()))) {
            if ((NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpouDtlBean.getCpoDtlLineSubNum()) && // 
                    ZYPConstant.FLG_OFF_N.equals(cpouDtlBean.getSetItemShipCpltFlg())) || // 
                    (ZYPConstant.FLG_ON_Y.equals(cpouDtlBean.getIntgFlg()) && ZYPConstant.FLG_OFF_N.equals(cpouDtlBean.getThirdPtyVndDropYFlg()) //
                            && checkDummyWhCd(crBillOnlyWhWhList, cpouDtlBean.getInvtyLocCd()) )) { // 2018/01/24 S21_NA#23706 Fixing Point
            // 2018/01/18 S21_NA#23555 Mod End
                cpouDtlBean.setInvtyLocCd("");
                cpouDtlBean.setShpgSvcLvlCd("");
                cpouDtlBean.setFrtChrgToCd("");
                cpouDtlBean.setFrtChrgMethCd("");
                cpouDtlBean.setDslpFrtChrgToCd("");
                cpouDtlBean.setDslpFrtChrgMethCd("");
                // START ADD S.Yamamoto [OM006]
                cpouDtlBean.setVndInvtyLocCd("");
                // END   ADD S.Yamamoto [OM006]
                // START ADD S.Yamamoto [OM003]
                cpouDtlBean.setFrtCondCd("");
                // END   ADD S.Yamamoto [OM003]
            }

            // 20121130 M.Fuji WDS Solution#104,105 Pricing Start
            cpouDtlBean.setExchRate(getExchangeRate(cpouBean.getGlblCmpyCd(), cpouDtlBean.getCcyCd(), cpouBean.getSlsDt(), localCache));
            // 20121130 M.Fuji WDS Solution#104,105 Pricing end

            // --------------------------------------------------
            // Payment Term Cash Discount Term
            // --------------------------------------------------
            // not set sompornet
            if (!hasValue(cpouDtlBean.getSetMdseCd())) {

                // get Default PaymentTerm and CashDiscTerm
                if (!hasValue(cpouDtlBean.getPmtTermCashDiscCd())) {

                    final NWZC045001PMsg pmtTermCashDiscTermMsg = getPmtTermCashDiscTerm(cpouBean, cpouDtlBean, resPMsgList, localCache, onBatchType);

                    if (CPO_ORD_TP.TRIAL.equals(cpouDtlBean.getCpoOrdTpCd()) || CPO_ORD_TP.LOAN.equals(cpouDtlBean.getCpoOrdTpCd()) || CPO_ORD_TP.EXPENSE.equals(cpouDtlBean.getCpoOrdTpCd())) {
                        // nothing
                    } else {
                        // Def#2153
                        if (!NWZC150001CpouConstant.CPO_CANCEL.equals(cpouBean.getRqstTpCd())) {
                            for (int j = 0; j < pmtTermCashDiscTermMsg.xxMsgIdList.getValidCount(); j++) {
                                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0943E, resPMsgList, i);
                                break;
                            }
                        }
                    }

                    cpouDtlBean.setPmtTermCashDiscCd(pmtTermCashDiscTermMsg.pmtTermCashDiscCd.getValue());
                    cpouDtlBean.setPmtTermCd(pmtTermCashDiscTermMsg.pmtTermCd.getValue());
                    cpouDtlBean.setCashDiscTermCd(pmtTermCashDiscTermMsg.cashDiscTermCd.getValue());

                } else {

                    final NWZC045001PMsg pmtTermCashDiscTermMsg = getPmtTermCashDiscTerm(cpouBean, cpouDtlBean, resPMsgList, localCache, onBatchType);

                    if (cpouDtlBean.getPmtTermCashDiscCd().equals(pmtTermCashDiscTermMsg.pmtTermCashDiscCd.getValue())) {
                        cpouDtlBean.setPmtTermCd(pmtTermCashDiscTermMsg.pmtTermCd.getValue());
                        cpouDtlBean.setCashDiscTermCd(pmtTermCashDiscTermMsg.cashDiscTermCd.getValue());

                    } else {
                        PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = new PMT_TERM_CASH_DISCTMsg();
                        pmtTermCashDiscTMsg = getPmtTermCashDiscCd(cpouBean.getGlblCmpyCd(), cpouDtlBean.getPmtTermCashDiscCd(), localCache);
                        cpouDtlBean.setPmtTermCd(pmtTermCashDiscTMsg.pmtTermCd.getValue());
                        cpouDtlBean.setCashDiscTermCd(pmtTermCashDiscTMsg.cashDiscTermCd.getValue());
                    }
                }
            }

            // Def#1864
            if (asList(NWZC150001CpouConstant.CPO_DTL_SAVE, NWZC150001CpouConstant.CPO_DTL_SUBMIT).contains(cpouDtlBean.getDtlRqstTpCd())) {

                // not set sompornet
                if (!hasValue(cpouDtlBean.getSetMdseCd())) {

                    final NWZC045001PMsg pmtTermCashDiscTermMsg = getPmtTermCashDiscTerm(cpouBean, cpouDtlBean, resPMsgList, localCache, onBatchType);

                    if (pmtTermCashDiscTermMsg.xxMsgIdList.getValidCount() == 0) {
                        cpouDtlBean.setDefPmtTermCd(pmtTermCashDiscTermMsg.pmtTermCd.getValue());
                        cpouDtlBean.setDefCashDiscTermCd(pmtTermCashDiscTermMsg.cashDiscTermCd.getValue());
                    }
                }
            }

            setUomCpltFlg(cpouBean, cpouDtlBean);

            // S21_NA#2846#9 start
//            if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpouDtlBean.getCpoDtlLineSubNum()) || ZYPConstant.FLG_ON_Y.equals(cpouDtlBean.getIntgFlg())) {
//                cpouDtlBean.setOrdCustUomQty(cpouDtlBean.getOrdQty());
//            }
            // S21_NA#2846#9 end

            if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpouDtlBean.getCpoDtlLineSubNum())) {
                // 01/25/2010 Update by ShipComp
                if (ZYPConstant.FLG_OFF_N.equals(cpouDtlBean.getSetItemShipCpltFlg())) {
                    cpouDtlBean.setInvtyLocCd("");
                    cpouDtlBean.setShpgSvcLvlCd("");
                    cpouDtlBean.setFrtChrgToCd("");
                    cpouDtlBean.setFrtChrgMethCd("");
                    // START ADD S.Yamamoto [OM003]
                    cpouDtlBean.setFrtCondCd("");
                    // END   ADD S.Yamamoto [OM003]
                }
                setItemMsg = cpouDtlBean;
            }

            if (NWZC150001CpouConstant.CPO_MODIFY.equals(cpouBean.getRqstTpCd()) || NWZC150001CpouConstant.CPO_CANCEL.equals(cpouBean.getRqstTpCd())) {
                CPO_DTLTMsg cpoDtlMsg = NWZC150001CpouExistsCdInDbCheck.getCpoDtlByPK(cpouBean, cpouDtlBean);
                if (!hasValue(cpouDtlBean.getExchRate())) {
                    cpouDtlBean.setExchRate(cpoDtlMsg.exchRate.getValue());
                }
                if (NWZC150001CpouConstant.CPO_CANCEL.equals(cpouBean.getRqstTpCd())) {
                    cpouDtlBean.setOrdQty(cpoDtlMsg.ordQty.getValue());
                    cpouDtlBean.setOrdCustUomQty(cpoDtlMsg.ordCustUomQty.getValue());
                    // 09/02/2009 Update
                    cpouDtlBean.setEntDealNetUnitPrcAmt(cpoDtlMsg.entDealNetUnitPrcAmt.getValue());
                    cpouDtlBean.setCpoOrdSubmtTs(cpoDtlMsg.cpoOrdSubmtTs.getValue());
                    cpouDtlBean.setCcyCd(cpoDtlMsg.ccyCd.getValue());
                }
                cpouDtlBean.setShipCpltCdOld(cpoDtlMsg.shipCpltCd.getValue());

                // START DELETE S.Yamamoto [MEX-LC004]
                //                    if (N.equals(cpouDtlBean.getManPrcFlg())) {
                //                        cpouDtlBean.setPrcCatgCd(cpoDtlMsg.prcCatgCd.getValue());
                //                    }
                // END   DELETE S.Yamamoto [MEX-LC004]

                // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
                // Def#1864
//                if (NWZC150001CpouConstant.CPO_DTL_MODIFY.equals(cpouDtlBean.getDtlRqstTpCd())) {
//                    String manPmtFlg = cpoDtlMsg.manPmtFlg.getValue();
//                    if (N.equals(manPmtFlg)) {
//
//                        String pmtTermCd = cpouDtlBean.getPmtTermCd();
//                        String cashDiscTermCd = cpouDtlBean.getCashDiscTermCd();
//                        String pmtTermCdCpo = cpoDtlMsg.pmtTermCd.getValue();
//                        String cashDiscTermCdCpo = cpoDtlMsg.cashDiscTermCd.getValue();
//
//                        if (!(pmtTermCd.equals(pmtTermCdCpo) && cashDiscTermCd.equals(cashDiscTermCdCpo))) {
//                            cpouDtlBean.setManPmtFlg(ZYPConstant.FLG_ON_Y);
//                        }
//                    } else {
//                        cpouDtlBean.setManPmtFlg(ZYPConstant.FLG_ON_Y);
//                    }
//                }
                // 2017/03/29 S21_NA#Review structure Lv.1 Del End
            }

            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            // Def#1864
//            if (asList(NWZC150001CpouConstant.CPO_DTL_SAVE, CPO_DTL_SUBMIT).contains(cpouDtlBean.getDtlRqstTpCd())) {
//
//                String pmtTermCd = cpouDtlBean.getPmtTermCd();
//                String cashDiscTermCd = cpouDtlBean.getCashDiscTermCd();
//                String pmtTermCdApi = cpouDtlBean.getDefPmtTermCd();
//                String cashDiscTermCdApi = cpouDtlBean.getDefCashDiscTermCd();
//
//                if (!(pmtTermCd.equals(pmtTermCdApi) && cashDiscTermCd.equals(cashDiscTermCdApi))) {
//                    cpouDtlBean.setManPmtFlg(ZYPConstant.FLG_ON_Y);
//                }
//            }
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End

            if (CPO_SRC_TP.EXPORT_ORDER_ENTRY_SCREEN.equals(cpouBean.getCpoSrcTpCd()) || CPO_SRC_TP.S85_ORDER.equals(cpouBean.getCpoSrcTpCd())) {
                cpouDtlBean.setExptFlg(ZYPConstant.FLG_ON_Y);
            } else {
                if (NWZC150001CpouExistsCdInDbCheck.isExportForCtry(cpouDtlBean.getGlblCmpyCd(), cpouDtlBean.getShipToCtryCd(), localCache)) {
                    cpouDtlBean.setExptFlg(ZYPConstant.FLG_ON_Y);
                } else {
                    cpouDtlBean.setExptFlg(ZYPConstant.FLG_OFF_N);
                }
            }

            if (!CPO_ORD_TP.EXPENSE.equals(cpouDtlBean.getCpoOrdTpCd())) { // 2016/08/24 QC#13747 add
                // 01/20/2010 Add
                // COA_CMPY_CD
                cpouDtlBean.setCoaCmpyCd(NWZC150001CpouExistsCdInDbCheck.getVarCharConstValue(cpouDtlBean.getGlblCmpyCd(), "COA_CMPY_CD"));
                // COA_EXTN_CD
                cpouDtlBean.setCoaExtnCd(NWZC150001CpouExistsCdInDbCheck.getVarCharConstValue(cpouDtlBean.getGlblCmpyCd(), "COA_EXTN_CD"));
                // COA_AFFL_CD
                // String coaAfflCd = getBillToAffiliateCd(cpouBean); // S21_NA#10321-27 Del

                if (coaAfflCd != null) {
                    cpouDtlBean.setCoaAfflCd(coaAfflCd);
                } else {
                    // Def#2153
                    if (!NWZC150001CpouConstant.CPO_CANCEL.equals(cpouBean.getRqstTpCd())) {
                        msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0608E, pMsg);
                    }
                }
            }

            if (!CPO_ORD_TP.EXPENSE.equals(cpouDtlBean.getCpoOrdTpCd()) && !CPO_ORD_TP.LOAN.equals(cpouDtlBean.getCpoOrdTpCd())) {

                // COA_BR_CD
                // COA_CH_CD
                // COA_CC_CD
                S21_ORGTMsg s21org = NWZC150001CpouExistsCdInDbCheck.getS21OrgData(cpouDtlBean.getGlblCmpyCd(), cpouDtlBean.getSlsRepOrSlsTeamTocCd());

                if (s21org != null) {
                    cpouDtlBean.setCoaBrCd(s21org.coaBrCd.getValue());
                    cpouDtlBean.setCoaChCd(s21org.coaChCd.getValue());
                    cpouDtlBean.setCoaCcCd(s21org.coaCcCd.getValue());
                } else {
                    // Def#2153
                    if (!NWZC150001CpouConstant.CPO_CANCEL.equals(cpouBean.getRqstTpCd())) {
                        NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0263E, resPMsgList, i);
                    }
                }

                // COA_PROD_CD
                cpouDtlBean.setCoaProdCd(mdseMsg.coaProdCd.getValue());
            }

            // EDI/Extranet
            if (NWZC150001CpouConstant.CPO_DTL_MODIFY.equals(cpouDtlBean.getDtlRqstTpCd())) {
                if (hasValue(cpouDtlBean.getShpgPlnDplyLineNum())) {

                    CPO_DTLTMsg cpoDtlMsg = NWZC150001CpouExistsCdInDbCheck.getCpoDtlByPK(cpouBean, cpouDtlBean);

                    final SHPG_PLNTMsg reqShpgPlnTMsg = new SHPG_PLNTMsg();
                    reqShpgPlnTMsg.setSQLID("006");
                    reqShpgPlnTMsg.setConditionValue("glblCmpyCd01", cpouBean.getGlblCmpyCd());
                    reqShpgPlnTMsg.setConditionValue("trxSrcTpCd01", TRX_SRC_TP.WHOLE_SALES);
                    reqShpgPlnTMsg.setConditionValue("trxHdrNum01", cpouBean.getCpoOrdNum());
                    reqShpgPlnTMsg.setConditionValue("trxLineNum01", cpouDtlBean.getCpoDtlLineNum());
                    reqShpgPlnTMsg.setConditionValue("trxLineSubNum01", cpouDtlBean.getCpoDtlLineSubNum());
                    reqShpgPlnTMsg.setConditionValue("shpgStsCd01", SHPG_STS.VALIDATED);

                    final SHPG_PLNTMsgArray shpgPlnTMsgArray = (SHPG_PLNTMsgArray) findByCondition(reqShpgPlnTMsg);

                    if (shpgPlnTMsgArray.getValidCount() != 0) {
                        BigDecimal boQty = ZERO;
                        for (int j = 0; j < shpgPlnTMsgArray.getValidCount(); j++) {
                            boQty = boQty.add(shpgPlnTMsgArray.no(j).ordQty.getValue());
                        }
                        BigDecimal ordQty = cpoDtlMsg.ordQty.getValue();
                        ordQty = ordQty.subtract(boQty).add(cpouDtlBean.getOrdQty());
                        cpouDtlBean.setOrdQty(ordQty);
                    } else {
                        if (cpoDtlMsg.ordQty.getValue().compareTo(cpouDtlBean.getOrdQty()) != 0) {
                            msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0956E, pMsg);
                        }
                    }
                }
            }

            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            // Def#1992 EDI,S85Order -- CUST_MDSE_CD is set to
//            // additional line.
//            if (NWZC150001CpouConstant.CPO_MODIFY.equals(cpouBean.getRqstTpCd())) {
//                if (CPO_DTL_SUBMIT.equals(cpouDtlBean.getDtlRqstTpCd())) {
//                    if (!hasValue(cpouDtlBean.getCustMdseCd())) {
//                        if (hasValue(cpouDtlBean.getEdiNum())) {
//
//                            Map<String, String> map = new HashMap<String, String>();
//                            map.put("glblCmpyCd", cpouBean.getGlblCmpyCd());
//                            map.put("cpoOrdNum", cpouBean.getCpoOrdNum());
//                            map.put("ediNum", cpouDtlBean.getEdiNum());
//                            map.put("ediSubNum", cpouDtlBean.getEdiSubNum());
//
//                            String custMdseCd = (String) ssmClient.queryObject("getCustMdseCd", map);
//
//                            cpouDtlBean.setCustMdseCd(custMdseCd);
//                        }
//                    }
//                }
//            }
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End
            i++;
        }
    }

    /**
     * setHeaderShiptoInfo
     * 
     * <pre>
     * When ShipTo information on the header is a blank, the value of details is set.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     */
    private static void setHeaderShipTo(NWZC150001CpouBean cpoBean) {

        NWZC150001CpouDetailBean cpouDtlBean = cpoBean.getDtlBeanList().get(0);

        if (!hasValue(cpoBean.getAddDropShipFlg())) {
            cpoBean.setAddDropShipFlg(cpouDtlBean.getDropShipFlg());
        }

        if (!hasValue(cpoBean.getAddShipToLocNm())) {
            cpoBean.setAddShipToLocNm(cpouDtlBean.getShipToLocNm());
        }

        if (!hasValue(cpoBean.getAddShipToAddlLocNm())) {
            cpoBean.setAddShipToAddlLocNm(cpouDtlBean.getShipToAddlLocNm());
        }

        if (!hasValue(cpoBean.getAddShipToFirstLineAddr())) {
            cpoBean.setAddShipToFirstLineAddr(cpouDtlBean.getShipToFirstLineAddr());
        }

        if (!hasValue(cpoBean.getAddShipToScdLineAddr())) {
            cpoBean.setAddShipToScdLineAddr(cpouDtlBean.getShipToScdLineAddr());
        }

        if (!hasValue(cpoBean.getAddShipToThirdLineAddr())) {
            cpoBean.setAddShipToThirdLineAddr(cpouDtlBean.getShipToThirdLineAddr());
        }

        if (!hasValue(cpoBean.getAddShipToFrthLineAddr())) {
            cpoBean.setAddShipToFrthLineAddr(cpouDtlBean.getShipToFrthLineAddr());
        }

        if (!hasValue(cpoBean.getAddShipToCtyAddr())) {
            cpoBean.setAddShipToCtyAddr(cpouDtlBean.getShipToCtyAddr());
        }

        if (!hasValue(cpoBean.getAddShipToStCd())) {
            cpoBean.setAddShipToStCd(cpouDtlBean.getShipToStCd());
        }

        if (!hasValue(cpoBean.getAddShipToPostCd())) {
            cpoBean.setAddShipToPostCd(cpouDtlBean.getShipToPostCd());
        }

        if (!hasValue(cpoBean.getAddShipToCtryCd())) {
            cpoBean.setAddShipToCtryCd(cpouDtlBean.getShipToCtryCd());
        }

        if (!hasValue(cpoBean.getAddShipToCntyNm())) {
            cpoBean.setAddShipToCntyNm(cpouDtlBean.getShipToCntyNm());
        }

        if (!hasValue(cpoBean.getAddShipTo01RefCmntTxt())) {
            cpoBean.setAddShipTo01RefCmntTxt(cpouDtlBean.getShipToFirstRefCmntTxt());
        }

        if (!hasValue(cpoBean.getAddShipTo02RefCmntTxt())) {
            cpoBean.setAddShipTo02RefCmntTxt(cpouDtlBean.getShipToScdRefCmntTxt());
        }

        if (!hasValue(cpoBean.getAddShipToCustCd())) {
            cpoBean.setAddShipToCustCd(cpouDtlBean.getShipToCustCd());
        }
    }

    /**
     * Set Item Ship Complete Flag setting
     * 
     * <pre>
     * Set processing of Set Item Ship Complete Flag is done.
     * Set details have not already been processed.
     * The one that is not the component of the set commodity is processed.
     *
     * ・CPO Level    - MDSE.setMdseShipCpltFlg - ON 、Set Item Ship Complete Flag = 1
     * ・Line Level   - MDSE.setMdseShipCpltFlg - ON 、Set Item Ship Complete Flag = 1
     * ・UOM Level    - SELL_TO_CUST.canRcvInvAtSetCmptFlg - OFF or MDSE.setMdseShipCpltFlg - ON、Set Item Ship Complete Flag = 1
     * ・Unrestricted - SELL_TO_CUST.canRcvInvAtSetCmptFlg - OFF or MDSE.setMdseShipCpltFlg - ON、Set Item Ship Complete Flag = 1
     *
     * As for the component of the set commodity, the same value as parents' commodities is set.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param detailMsg NWZC150001CpouDetailBean
     * @param setItemMsg NWZC150001CpouDetailBean
     */
    private static void setSetItemShipCpltFlg(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean detailMsg, NWZC150001CpouDetailBean setItemMsg) {

        // 10/07/2009 Update
        // Set
        if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(detailMsg.getCpoDtlLineSubNum())) {
            if (hasValue(detailMsg.getSetItemShipCpltFlg())) {
                return;
            }

            MDSETMsg mdseMsg = NWZC150001CpouExistsCdInDbCheck.getMdse(cpoBean.getGlblCmpyCd(), detailMsg.getMdseCdForMstrSrch());

            if (ZYPConstant.FLG_ON_Y.equals(mdseMsg.setMdseShipCpltFlg.getValue())) {
                detailMsg.setSetItemShipCpltFlg(ZYPConstant.FLG_ON_Y);
                return;
            }

            detailMsg.setSetItemShipCpltFlg(ZYPConstant.FLG_OFF_N);
            // Compornent
        } else if (hasValue(detailMsg.getSetMdseCd())) {
            detailMsg.setSetItemShipCpltFlg(setItemMsg.getSetItemShipCpltFlg());
        } else {
            detailMsg.setSetItemShipCpltFlg(ZYPConstant.FLG_OFF_N);
        }
    }

    private static BigDecimal getExchangeRate(String glblCmpyCd, String ccyCd, String slsDt, NWZC150001CpouLocalCache localCache) {

        // cache
        final StringBuilder cacheKeySb = new StringBuilder();
        cacheKeySb.append(glblCmpyCd).append(",");
        cacheKeySb.append(ccyCd).append(",");
        cacheKeySb.append(slsDt);

        final String cacheKey = cacheKeySb.toString();

        BigDecimal exchangeRate = localCache.exchangeRateCache.get(cacheKey);
        if (exchangeRate == null) {
            NWXC001001RateData rateData = NWXC001001Exchange.getRate(glblCmpyCd, ccyCd, slsDt);
            if (rateData != null) {
                exchangeRate = rateData.getActlExchRate();
                localCache.exchangeRateCache.put(cacheKey, exchangeRate);
            }
        }

        return exchangeRate;
    }

    /**
     * Get Payment Term Cash Discount
     * 
     * <pre>
     * Default Payment Term is acquired.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param cpouDtlBean NWZC150001CpouDetailBean
     * @param resPMsgList List<NWZC150002PMsg>
     * @return NWZC045001PMsg
     */
    private static NWZC045001PMsg getPmtTermCashDiscTerm(NWZC150001CpouBean cpouBean, //
            NWZC150001CpouDetailBean cpouDtlBean, //
            List<NWZC150002PMsg> resPMsgList, //
            NWZC150001CpouLocalCache localCache, //
            ONBATCH_TYPE onBatchType) {

        final String glblCmpyCd = cpouBean.getGlblCmpyCd();
        final String mdseCd = cpouDtlBean.getMdseCdForMstrSrch();
        final String billToCustCd = cpouBean.getBillToCustCd();
        final String sellToCustCd = cpouBean.getSellToCustCd();
        final String shipToCustCd = cpouDtlBean.getShipToCustCd();
        final String payerCustCd = cpouBean.getPayerCustCd();

        // cache
        final StringBuilder cacheKeySb = new StringBuilder();
        cacheKeySb.append(glblCmpyCd).append(",");
        cacheKeySb.append(mdseCd).append(",");
        cacheKeySb.append(billToCustCd).append(",");
        cacheKeySb.append(sellToCustCd).append(",");
        cacheKeySb.append(shipToCustCd).append(",");
        cacheKeySb.append(payerCustCd);

        final String cacheKey = cacheKeySb.toString();

        NWZC045001PMsg pMsg = localCache.pmtTermCashDiscTermCache.get(cacheKey);

        if (pMsg == null) {

            pMsg = new NWZC045001PMsg();
            pMsg.glblCmpyCd.setValue(glblCmpyCd);
            pMsg.mdseCd.setValue(mdseCd);
            pMsg.billToCustCd.setValue(billToCustCd);
            pMsg.sellToCustCd.setValue(sellToCustCd);
            pMsg.shipToCustCd.setValue(shipToCustCd);
            pMsg.payerCustCd.setValue(payerCustCd);

            new NWZC045001().execute(pMsg, onBatchType);

            localCache.pmtTermCashDiscTermCache.put(cacheKey, pMsg);
        }

        return pMsg;
    }

    private static PMT_TERM_CASH_DISCTMsg getPmtTermCashDiscCd(String glblCmpyCd, String payTermCashDiscCd, NWZC150001CpouLocalCache localCache) {

        return localCache.pmtTermCashDiscCache.getTMsgByKey(glblCmpyCd, payTermCashDiscCd);
    }

    /**
     * UOM Complete Code setting
     * 
     * <pre>
     * Set processing of UOM Complete Code is done.
     * When the value is set to Ship Complete Code, one is set and returned to UOM Complete Code in the place where CPO Order Source Type is not EDI.
     * As follows at EDI CPO Order Source Type.
     * ・CPO Level    - UOM Complete Code = 1
     * ・Line Level   - UOM Complete Code = 1
     * ・UOM Level    - UOM Complete Code = 1
     * ・Unrestricted - UOM Complete Code = 0
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param cpoDtlBean NWZC150001CpouDetailBean
     */
    private static void setUomCpltFlg(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean) {

        if (hasValue(cpoDtlBean.getShipCpltCd())) {
            cpoDtlBean.setUomCpltFlg(ZYPConstant.FLG_ON_Y);
        }

        if (!hasValue(cpoDtlBean.getUomCpltFlg())) {
            cpoDtlBean.setUomCpltFlg(ZYPConstant.FLG_OFF_N);
        }
    }

    // 2017/05/15 S21_NA#Review structure Lv.2 RS#8390 Add Start
    /**
     * DS CPO Line Number , DS CPO LIne Sub Number
     * This methos will replace reNewOrderLineNumber()
     * 
     * <pre>
     * Numbering of DS CPO Line Number and DS CPO Line Sub Number is done.
     * </pre>
     * @param cpouBean NWZC150001CpouBean
     */
    private static void setReNumCpoDtlLineNumAndDsCpoLineNum(NWZC150001CpouBean cpouBean) {

        int subNum = 0;
        String processLineNumber = null;

        BigDecimal dsCpoLineNum = BigDecimal.ZERO;
        
        List<String> dsOrdPosnNumList = new ArrayList<String>();

        for (NWZC150001CpouDetailBean detailMsg : cpouBean.getDtlBeanList()) {

            detailMsg.setReNumCpoDtlLineNum(detailMsg.getCpoDtlLineNum());
            detailMsg.setReNumCpoDtlLineSubNum(detailMsg.getCpoDtlLineSubNum());
            detailMsg.setCpoDtlLineNum(detailMsg.getCpoDtlLineNum());
            detailMsg.setCpoDtlLineSubNum(detailMsg.getCpoDtlLineSubNum());

            if (ZYPCommonFunc.hasValue(detailMsg.getDsOrdPosnNum()) //
                    && !dsOrdPosnNumList.contains(detailMsg.getDsOrdPosnNum())) {
                dsOrdPosnNumList.add(detailMsg.getDsOrdPosnNum());
            }
        }

        // 2016/08/02 S21_NA#12637 Add Start
        // Re-Number DS CPO LineNum
        for (String dsOrdPosnNum : dsOrdPosnNumList) {
            dsCpoLineNum = BigDecimal.ZERO;
            subNum = -1;
            for (int i = 0; i < cpouBean.getDtlBeanList().size(); i++) {
                NWZC150001CpouDetailBean detailMsg = cpouBean.getDtlBeanList().get(i);
                if (!S21StringUtil.isEquals(dsOrdPosnNum, detailMsg.getDsOrdPosnNum())) {
                    continue;
                }

                String orderLineNumber = detailMsg.getCpoDtlLineNum();
                String orderLineSubNumber = detailMsg.getCpoDtlLineSubNum();

                if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(orderLineSubNumber)) {
                    subNum = 0;
                    processLineNumber = orderLineNumber;
                    dsCpoLineNum = dsCpoLineNum.add(BigDecimal.ONE);
                } else if (orderLineNumber.equals(processLineNumber)) {
                    subNum++;
                } else {
                    dsCpoLineNum = dsCpoLineNum.add(BigDecimal.ONE);
                    subNum = -1;
                }
                detailMsg.setDsCpoLineNum(dsCpoLineNum);
                if (subNum > 0) {
                    detailMsg.setDsCpoLineSubNum(BigDecimal.valueOf(subNum));
                }
            }
        }            
        // 2016/08/02 S21_NA#12637 Add End

        // S21_NA#3067 renumbering reference line number.
        for (NWZC150001CpouDetailBean refMsg : cpouBean.getDtlBeanList()) {

            String origRefCpoDtlLineNum = refMsg.getRefCpoDtlLineNum() + refMsg.getRefCpoDtlLineSubNum();
            if (S21StringUtil.isEmpty(origRefCpoDtlLineNum)) {
                // no reference line
                continue;
            }

            // search new reference line
            for (NWZC150001CpouDetailBean sourceMsg : cpouBean.getDtlBeanList()) {
                String origCpoDtlLineNum = sourceMsg.getCpoDtlLineNum() + sourceMsg.getCpoDtlLineSubNum();
                if (!S21StringUtil.isEquals(origRefCpoDtlLineNum, origCpoDtlLineNum)) {
                    continue;
                }

                // original reference line is found. (include cancel
                // request line -> new line number is empty.)
                String newCpoDtlLineNum = sourceMsg.getReNumCpoDtlLineNum() + sourceMsg.getReNumCpoDtlLineSubNum();

                if (S21StringUtil.isNotEmpty(newCpoDtlLineNum)) {

                    // re:numbering reference line number.
                    refMsg.setRefCpoDtlLineNum(sourceMsg.getReNumCpoDtlLineNum());
                    refMsg.setRefCpoDtlLineSubNum(sourceMsg.getReNumCpoDtlLineSubNum());

                    // re:numbering display reference line number(DS).
                    String dplyLineRefNum = editDplyLineRefNum(sourceMsg.getDsOrdPosnNum(), sourceMsg.getDsCpoLineNum(), sourceMsg.getDsCpoLineSubNum());
                    refMsg.setDplyLineRefNum(dplyLineRefNum);
                } else {

                    // clear reference line number.
                    refMsg.setRefCpoDtlLineNum("");
                    refMsg.setRefCpoDtlLineSubNum("");

                    // clear display reference line number(DS).
                    refMsg.setDplyLineRefNum("");
                }
            }
        }
    }
    // 2017/05/15 S21_NA#Review structure Lv.2 RS#8390 Add End
    
    // 2018/01/18 S21_NA#23555 Add Start
    private static boolean checkDummyWhCd(List<String> checkWhList, String invtyLocCd){
        
        if(checkWhList == null || invtyLocCd == null){
            return true;
        } else if (!checkWhList.contains(invtyLocCd)){
            return true;
        }
        return false;
    }
    // 2018/01/18 S21_NA#23555 Add End
}
