package com.canon.cusa.s21.common.NWX.NWXC412001;

import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_BILL_BY_TP_CD;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_BLLG_CYCLE_CD;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_BUYOUT_ITEM;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_CHK_ORD;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_CTAC_PSN_TP_CD;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_DOLLAR_ONE;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_DS_CONTR_CATG_CD;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_DS_ORD_CATG_CD;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_DS_ORD_TP_CD;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_FREIGHT;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_LEASE_PRCH_OPT_CD;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_MTR_READ_METH_CD;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_NET30;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_PRC_SVC_PLN_TP_CD;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_REAL_FAIR_VAL;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_RTRN_RSN_CD;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_SOFT_COST_ITEM;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_SPEC_COND_PRC_PK_FOR_FREIGHT;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_SPLY_TP_CD;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_SVC_CONTR_TP_CD;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_SVC_MDSE_CD;
import static com.canon.cusa.s21.common.NWX.NWXC412001.constant.NWXC412001Constant.INTFC_XREF_CTX_TP_UPGRADE_RETURNS_ITEM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import business.db.CUST_CR_PRFLTMsg;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.DS_MDLTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_ISTL_RULETMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 * <pre>
 * SOM common component
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/09/2016   Fujitsu         T.Ishii         Create          N/A
 * 01/30/2017   SRA             K.Aratani       Update          QC#17372
 * 01/30/2017   SRA             K.Aratani       Update          QC#17120
 * 03/08/2017   SRA             K.Aratani       Update          QC#17768
 * 2017/06/22   Fujitsu         M.Yamada        Update          QC#19451
 * 2017/12/15   Fujitsu         M.Yamada        Update          QC#18798
 * 2018/02/08   Hitachi         K.Kojima        Update          QC#23357
 * 02/16/2018   SRA             K.Aratani       Update          QC#24206
 * 02/16/2018   SRA             K.Aratani       Update          QC#24240
 * 04/05/2018   SRA             K.Aratani       Update          QC#25324
 * </pre>
 */
public final class NWXC412001 {

    /**
     * get DS order category code
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param trxTpTxt String
     * @return DS order category code
     */
    public static String getDsOrdCatgCd(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String trxTpTxt) {

        if (S21StringUtil.isEmpty(trxTpTxt)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", trxTpTxt);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_DS_ORD_CATG_CD);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return (String) result.get(0).get("TRGT_ATTRB_TXT_01");
        }

        return null;
    }

    /**
     * get DS order type code
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param trxTpTxt String
     * @param leaseCmpyNm String
     * @return DS order type code
     */
    public static String getDsOrdTpCd(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String trxTpTxt, String leaseCmpyNm) {

        if (S21StringUtil.isEmpty(trxTpTxt)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", trxTpTxt);
        ssmParam.put("srcAttrbTxt02", leaseCmpyNm);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_DS_ORD_TP_CD);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return (String) result.get(0).get("TRGT_ATTRB_TXT_01");
        }

        return null;
    }

    /**
     * get price category code for line
     * @param glblCmpyCd String
     * @param hdrBidPrcListNm String
     * @param shipBidPrcListNm String
     * @param shipBidPrcListId BigDecimal
     * @return price category code for line
     */
    public static String getPrcCatgCdForLine(String glblCmpyCd, String hdrBidPrcListNm, String shipBidPrcListNm, BigDecimal shipBidPrcListId) {

        String prcCatgCd = null;

        if (S21StringUtil.isNotEmpty(hdrBidPrcListNm)) {

            prcCatgCd = getPrcCatgCdFromName(glblCmpyCd, hdrBidPrcListNm);
        }

        if (S21StringUtil.isNotEmpty(prcCatgCd)) {

            return prcCatgCd;
        }

        if (S21StringUtil.isNotEmpty(shipBidPrcListNm)) {

            prcCatgCd = getPrcCatgCdFromName(glblCmpyCd, shipBidPrcListNm);
        }

        if (S21StringUtil.isNotEmpty(prcCatgCd)) {

            return prcCatgCd;
        }

        if (shipBidPrcListId != null) {

            prcCatgCd = getPrcCatgCdFromId(glblCmpyCd, shipBidPrcListId);
        }

        if (S21StringUtil.isNotEmpty(prcCatgCd)) {

            return prcCatgCd;
        }

        return null;
    }

    /**
     * get price category code from name
     * @param glblCmpyCd String
     * @param bidPrcListNm String
     * @return price category code
     */
    public static String getPrcCatgCdFromName(String glblCmpyCd, String bidPrcListNm) {

        if (S21StringUtil.isEmpty(bidPrcListNm)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prcCatgNm", bidPrcListNm);

        String result = NWXC412001Query.getInstance().getPrcCatgCdFromName(ssmParam);

        return result;
    }

    /**
     * get price category code from id
     * @param glblCmpyCd String
     * @param bidPrcListId BigDecimal
     * @return price category code
     */
    public static String getPrcCatgCdFromId(String glblCmpyCd, BigDecimal bidPrcListId) {

        if (bidPrcListId == null) {

            return null;
        }

        String prcCatgCd = bidPrcListId.toPlainString();

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prcCatgCd", prcCatgCd);

        String result = NWXC412001Query.getInstance().getPrcCatgCdFromId(ssmParam);

        return result;
    }

    /**
     * get ship to information from SOM ship to party number
     * @param glblCmpyCd String
     * @param somShipToPtyNum String
     * @param slsDt String
     * @return ship to information
     */
    public static Map<String, Object> getShipToInfoFromSomShipToPtyNum(String glblCmpyCd, String somShipToPtyNum, String slsDt) {

        if (S21StringUtil.isEmpty(somShipToPtyNum)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("locNum", somShipToPtyNum);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        Map<String, Object> result = NWXC412001Query.getInstance().getShipToInfoByLocNum(ssmParam);

        return result;
    }

    /**
     * get ship to information from address
     * @param glblCmpyCd String
     * @param address String
     * @param slsDt String
     * @return ship to information
     */
    public static Map<String, Object> getShipToInfoFromAddress(String glblCmpyCd, String address, String slsDt, String locNm) {

        if (S21StringUtil.isEmpty(address)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("address", address);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("locNm", locNm);

        Map<String, Object> result = NWXC412001Query.getInstance().getShipToInfoFromAddress(ssmParam);

        return result;
    }

    /**
     * get bill to information from SOM ship to party number
     * @param glblCmpyCd String
     * @param somShipToPtyNum String
     * @param slsDt String
     * @return bill to information
     */
    public static Map<String, Object> getBillToInfoFromSomShipToPtyNum(String glblCmpyCd, String somShipToPtyNum, String slsDt) {

        if (S21StringUtil.isEmpty(somShipToPtyNum)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("locNum", somShipToPtyNum);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        Map<String, Object> result = NWXC412001Query.getInstance().getBillToInfoFromSomShipToPtyNum(ssmParam);

        return result;
    }

    /**
     * get bill to information from address
     * @param glblCmpyCd String
     * @param address String
     * @param slsDt String
     * @return bill to information
     */
    public static Map<String, Object> getBillToInfoFromAddress(String glblCmpyCd, String address, String slsDt, String locNm) {

        if (S21StringUtil.isEmpty(address)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("address", address);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("locNm", locNm);

        Map<String, Object> result = NWXC412001Query.getInstance().getBillToInfoFromAddress(ssmParam);

        return result;
    }

    //    /**
    //     * get bill to information for lease
    //     * @param glblCmpyCd String
    //     * @param cpoSrcTpCd String
    //     * @param intfcId String
    //     * @param leaseCmpyNm String
    //     * @param slsDt String
    //     * @return bill to information
    //     */
    //    public static Map<String, Object> getBillToInfoForLease(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String leaseCmpyNm, String slsDt) {
    //
    //        if (S21StringUtil.isEmpty(leaseCmpyNm)) {
    //
    //            return null;
    //        }
    //
    //        Map<String, Object> ssmParam = new HashMap<String, Object>();
    //
    //        ssmParam.put("glblCmpyCd", glblCmpyCd);
    //        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
    //        ssmParam.put("intfcId", intfcId);
    //        ssmParam.put("srcAttrbTxt01", leaseCmpyNm);
    //        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_LEASE_CMPY);
    //
    //        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
    //        if (result != null && !result.isEmpty()) {
    //
    //            String billToCustAcctCd = (String) result.get(0).get("TRGT_ATTRB_TXT_01");
    //            String billToCustCd = (String) result.get(0).get("TRGT_ATTRB_TXT_02");
    //
    //            return getBillToInfoByCode(glblCmpyCd, billToCustAcctCd, billToCustCd, slsDt);
    //
    //        }
    //
    //        return null;
    //    }

    /**
     * get bill to information by code
     * @param glblCmpyCd String
     * @param billToCustAcctCd String
     * @param billToCustCd String
     * @param slsDt String
     * @return bill to information
     */
    public static Map<String, Object> getBillToInfoByCode(String glblCmpyCd, String billToCustAcctCd, String billToCustCd, String slsDt) {

        if (S21StringUtil.isEmpty(billToCustAcctCd)) {

            return null;
        }
        if (S21StringUtil.isEmpty(billToCustCd)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", billToCustAcctCd);
        ssmParam.put("billToCustCd", billToCustCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("slsDt", slsDt);

        Map<String, Object> result = NWXC412001Query.getInstance().getBillToInfoByCode(ssmParam);

        return result;
    }

    /**
     * get sold to information from ship to customer code
     * @param glblCmpyCd String
     * @param shipToAcctCd String
     * @param shipToCustCd String
     * @param slsDt String
     * @return sold to information
     */
    public static Map<String, Object> getSoldToInfoFromShipToCustCd(String glblCmpyCd, String shipToAcctCd, String shipToCustCd, String slsDt) {

        if (S21StringUtil.isEmpty(shipToCustCd)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shipToCustCd", shipToCustCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("slsDt", slsDt);

        Map<String, Object> result = NWXC412001Query.getInstance().getSoldToInfoFromShipToCustCd(ssmParam);

        if (result == null || result.isEmpty()) {

            return getSoldToInfoFromShipToAcctCd(glblCmpyCd, shipToAcctCd, slsDt);
        }

        return result;
    }

    /**
     * get sold to information from ship to account code
     * @param glblCmpyCd String
     * @param shipToAcctCd String
     * @param slsDt String
     * @return sold to information
     */
    public static Map<String, Object> getSoldToInfoFromShipToAcctCd(String glblCmpyCd, String shipToAcctCd, String slsDt) {

        if (S21StringUtil.isEmpty(shipToAcctCd)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shipToAcctCd", shipToAcctCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("slsDt", slsDt);

        Map<String, Object> result = NWXC412001Query.getInstance().getSoldToInfoFromShipToAcctCd(ssmParam);

        return result;
    }

    /**
     * get sold to information from bill to address
     * @param glblCmpyCd String
     * @param address String
     * @param slsDt String
     * @return sold to information
     */
    public static Map<String, Object> getSoldToInfoFromAddress(String glblCmpyCd, String address, String slsDt, String locNm) {

        if (S21StringUtil.isEmpty(address)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("address", address);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("locNm", locNm);

        Map<String, Object> result = NWXC412001Query.getInstance().getSoldToInfoFromAddress(ssmParam);

        return result;
    }

    /**
     * get ship to information from SOM person count(for
     * configuration)
     * @param glblCmpyCd String
     * @param somPsnCnt BigDecimal
     * @param slsDt String
     * @return ship to information
     */
    public static Map<String, Object> getShipToInfoFromSomPsnCnt(String glblCmpyCd, BigDecimal somPsnCnt, String slsDt) {

        if (somPsnCnt == null) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("locNum", somPsnCnt.toPlainString());
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        Map<String, Object> result = NWXC412001Query.getInstance().getShipToInfoByLocNum(ssmParam);

        return result;
    }

    /**
     * get ship to information from SOM person description text(for
     * RMA)
     * @param glblCmpyCd String
     * @param somPsnDescTxt String
     * @param slsDt String
     * @return ship to information
     */
    public static Map<String, Object> getShipToInfoFromSomPsnDescTxt(String glblCmpyCd, String somPsnDescTxt, String slsDt) {

        if (S21StringUtil.isEmpty(somPsnDescTxt)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("locNum", somPsnDescTxt);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        Map<String, Object> result = NWXC412001Query.getInstance().getShipToInfoByLocNum(ssmParam);

        return result;
    }

    /**
     * get line category code
     * @param glblCmpyCd String
     * @param dsOrdTpCd String
     * @param dsOrdLineDrctnCd String
     * @param slsDt String
     * @return DS line category code
     */
    public static String getDsLineCatgCd(String glblCmpyCd, String dsOrdTpCd, String dsOrdLineDrctnCd, String slsDt) {

        if (S21StringUtil.isEmpty(dsOrdTpCd)) {

            return null;
        }

        if (S21StringUtil.isEmpty(dsOrdLineDrctnCd)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("dsOrdLineDrctnCd", dsOrdLineDrctnCd);
        ssmParam.put("slsDt", slsDt);

        String result = NWXC412001Query.getInstance().getDsLineCatgCd(ssmParam);

        return result;
    }

    /**
     * get soft cost item information
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param somMercItemCd String
     * @param quoteLineTpTxt String
     * @return soft cost item information
     */
    public static Map<String, Object> getSoftCostItemInfo(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String somMercItemCd, String quoteLineTpTxt) {

        if (S21StringUtil.isEmpty(somMercItemCd)) {

            return null;
        }

        if (S21StringUtil.isEmpty(quoteLineTpTxt)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", somMercItemCd);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_SOFT_COST_ITEM);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return result.get(0);
        }

        return null;
    }

    /**
     * get buy out item code list
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @return buy out item code list
     */
    public static List<String> getBuyoutItemCodeList(String glblCmpyCd, String cpoSrcTpCd, String intfcId) {

        List<String> list = new ArrayList<String>();

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_BUYOUT_ITEM);

        List<Map<String, Object>> resultList = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (resultList != null && !resultList.isEmpty()) {

            for (Map<String, Object> result : resultList) {

                list.add((String) result.get("TRGT_ATTRB_TXT_01"));
            }
        }

        return list;
    }

    /**
     * get buy out item code list
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param somMercItemCd String
     * @return contents somMercItemCd
     */
    public static boolean existBuyoutItemCode(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String somMercItemCd) {

        List<String> list = getBuyoutItemCodeList(glblCmpyCd, cpoSrcTpCd, intfcId);

        return (list != null && list.contains(somMercItemCd));
    }

    /**
     * get upgrade returns item code list
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @return upgrade returns item code list
     */
    public static List<String> getUpgradeReturnsItemCodeList(String glblCmpyCd, String cpoSrcTpCd, String intfcId) {

        List<String> list = new ArrayList<String>();

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_UPGRADE_RETURNS_ITEM);

        List<Map<String, Object>> resultList = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (resultList != null && !resultList.isEmpty()) {

            for (Map<String, Object> result : resultList) {

                list.add((String) result.get("TRGT_ATTRB_TXT_01"));
            }
        }

        return list;
    }

    /**
     * get contact person type code
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param somCtacTpCd String
     * @return contact person type code
     */
    public static String getCtacPsnTpCd(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String somCtacTpCd) {

        if (S21StringUtil.isEmpty(somCtacTpCd)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", somCtacTpCd);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_CTAC_PSN_TP_CD);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return (String) result.get(0).get("TRGT_ATTRB_TXT_01");
        }

        return null;
    }

    /**
     * get supply type code
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param somByotNm String
     * @return supplier information
     */
    public static String getSplyTpCd(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String somByotNm) {

        if (S21StringUtil.isEmpty(somByotNm)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", somByotNm);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_SPLY_TP_CD);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return (String) result.get(0).get("TRGT_ATTRB_TXT_01");
        }

        return null;
    }

    //    /**
    //     * get supplier information
    //     * @param glblCmpyCd String
    //     * @param cpoSrcTpCd String
    //     * @param intfcId String
    //     * @param splyTpCd String
    //     * @return supplier information
    //     */
    //    public static Map<String, Object> getSupplierInfo(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String splyTpCd) {
    //
    //        if (S21StringUtil.isEmpty(splyTpCd)) {
    //
    //            return null;
    //        }
    //
    //        Map<String, Object> ssmParam = new HashMap<String, Object>();
    //
    //        ssmParam.put("glblCmpyCd", glblCmpyCd);
    //        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
    //        ssmParam.put("intfcId", intfcId);
    //        ssmParam.put("srcAttrbTxt01", splyTpCd);
    //        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_SUPPLIER_INFO);
    //
    //        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
    //        if (result != null && !result.isEmpty()) {
    //
    //            Map<String, Object> supplierInfo = new HashMap<String, Object>();
    //
    //            for (Map<String, Object> resultMap : result) {
    //
    //                supplierInfo.put((String) resultMap.get("SRC_ATTRB_TXT_02"), (String) resultMap.get("TRGT_ATTRB_TXT_01"));
    //            }
    //
    //            return supplierInfo;
    //        }
    //
    //        return null;
    //    }

    /**
     * get special condition price PK
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param quoteLineTpTxt String
     * @param prcCondTpCd String
     * @return special condition price PK
     */
    public static BigDecimal getSpecCondPrcPk(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String quoteLineTpTxt, String prcCondTpCd) {

        if (S21StringUtil.isNotEmpty(quoteLineTpTxt)) {

            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
            ssmParam.put("intfcId", intfcId);
            ssmParam.put("srcAttrbTxt01", quoteLineTpTxt);
            ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_FREIGHT);

            List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
            if (result != null && !result.isEmpty()) {

                // freight
                return getSpecCondPrcPkForFreight(glblCmpyCd, cpoSrcTpCd, intfcId);
            } else {

                // not freight
                return getSpecCondPrcPkByPrcCondTpCd(glblCmpyCd, prcCondTpCd);
            }
        } else {

            // not freight
            return getSpecCondPrcPkByPrcCondTpCd(glblCmpyCd, prcCondTpCd);
        }
    }

    /**
     * Exist QuoteLineTpTxt
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param quoteLineTpTxt String
     * @return Exist QuoteLineTpTxt
     */
    public static boolean existQuoteLineTpTxt(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String quoteLineTpTxt) {

        if (S21StringUtil.isEmpty(quoteLineTpTxt)) {
            return false;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", quoteLineTpTxt);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_FREIGHT);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null) {
            for (Map<String, Object> attrbs : result) {
                if (quoteLineTpTxt.equals(attrbs.get("TRGT_ATTRB_TXT_01"))) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * get special condition price PK for freight
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param quoteLineTpTxt String
     * @return special condition price PK for freight
     */
    public static BigDecimal getSpecCondPrcPkForFreight(String glblCmpyCd, String cpoSrcTpCd, String intfcId) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_SPEC_COND_PRC_PK_FOR_FREIGHT);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return (BigDecimal) result.get(0).get("TRGT_ATTRB_NUM_01");
        }

        return null;
    }

    /**
     * get special condition price PK by price condition type code
     * @param glblCmpyCd String
     * @param prcCondTpCd String
     * @return special condition price PK by price condition type code
     */
    public static BigDecimal getSpecCondPrcPkByPrcCondTpCd(String glblCmpyCd, String prcCondTpCd) {

        if (S21StringUtil.isEmpty(prcCondTpCd)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prcCondTpCd", prcCondTpCd);

        BigDecimal result = NWXC412001Query.getInstance().getSpecCondPrcPkByPrcCondTpCd(ssmParam);

        return result;
    }

    /**
     * get return reason code
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param trxTpTxt String
     * @param leaseCmpyNm String
     * @param quoteLineTpTxt String
     * @return return reason code
     */
    //public static String getRtrnRsnCd(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String trxTpTxt, String leaseCmpyNm, String quoteLineTpTxt) {
    public static String getRtrnRsnCd(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String quoteLineTpTxt, String somMercItemCd) {

        //if (S21StringUtil.isEmpty(trxTpTxt)) {
        //
        //    return null;
        //}

        //String paramLeaseCmpyNm = leaseCmpyNm;
        //if (S21StringUtil.isEmpty(leaseCmpyNm)) {
        //if (!TRX_TP_CD_LEASE.equals(trxTpTxt) || S21StringUtil.isEmpty(leaseCmpyNm)) {
        //
        //    paramLeaseCmpyNm = "*";
        //}

        if (S21StringUtil.isEmpty(quoteLineTpTxt)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        //ssmParam.put("srcAttrbTxt01", trxTpTxt);
        //ssmParam.put("srcAttrbTxt02", paramLeaseCmpyNm);
        //ssmParam.put("srcAttrbTxt03", quoteLineTpTxt);
        ssmParam.put("srcAttrbTxt01", quoteLineTpTxt);
        ssmParam.put("srcAttrbTxt02", somMercItemCd);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_RTRN_RSN_CD);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return (String) result.get(0).get("TRGT_ATTRB_TXT_01");
        }

        return null;
    }

    /**
     * get MDSE
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return merchandise information
     */
    public static Map<String, Object> getMdse(String glblCmpyCd, String mdseCd) {

        if (S21StringUtil.isEmpty(mdseCd)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        Map<String, Object> result = NWXC412001Query.getInstance().getMdse(ssmParam);

        return result;
    }

    /**
     * get MDSE by serial number
     * @param glblCmpyCd String
     * @param serNum String
     * @param slsDt String
     * @return merchandise information
     */
    public static Map<String, Object> getMdseBySerNum(String glblCmpyCd, String serNum, String slsDt) {

        if (S21StringUtil.isEmpty(serNum)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("serNum", serNum);
        ssmParam.put("slsDt", slsDt);

        Map<String, Object> result = NWXC412001Query.getInstance().getMdseBySerNum(ssmParam);

        return result;
    }

    /**
     * get valid date
     * @param value String
     * @return valid date
     */
    public static String getValidDate(String value) {

        if (S21StringUtil.isEmpty(value)) {

            return null;
        }

        if (value.length() < 8) {

            return null;
        }

        if (!ZYPDateUtil.checkDate(value.substring(0, 8))) {

            return null;
        }

        return value.substring(0, 8);
    }

    /**
     * get valid time stamp
     * @param value String
     * @return valid time stamp
     */
    public static String getValidTimeStamp(String value) {

        return S21StringUtil.subStringByLength(value, 0, 17);
    }

    /**
     * get SOM amount for rebate
     * @param somItemDescTxt String
     * @param somAmt BigDecimal
     * @return
     */
    public static BigDecimal getSomAmtForRebate(String somItemDescTxt, BigDecimal somAmt) {

        if (somAmt == null) {

            return null;
        }

        if (S21StringUtil.isEmpty(somItemDescTxt)) {

            return somAmt;
        }

        String regex = ".*WAR.*CHEST.*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(somItemDescTxt);

        if (matcher.find()) {

            return somAmt.multiply(BigDecimal.ONE.negate());
        } else {

            return somAmt;
        }
    }

    /**
     * get unit net weight
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param custUomCd String
     * @param ordCustUomQty BigDecimal
     * @return unit net weight
     */
    public static BigDecimal getUnitNetWt(String glblCmpyCd, String mdseCd, String custUomCd, BigDecimal ordCustUomQty) {

        if (ordCustUomQty == null) {

            return null;
        }

        if (S21StringUtil.isEmpty(mdseCd)) {

            return null;
        }

        if (S21StringUtil.isEmpty(custUomCd)) {

            return null;
        }

        MDSETMsg mdse = getOrderMdse(glblCmpyCd, mdseCd);
        if (mdse == null) {

            return null;
        }
        MDSE_STORE_PKGTMsg mdseStorePkg = new MDSE_STORE_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(mdseStorePkg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseStorePkg.pkgUomCd, custUomCd);
        ZYPEZDItemValueSetter.setValue(mdseStorePkg.mdseCd, mdse.mdseCd);
        mdseStorePkg = (MDSE_STORE_PKGTMsg) S21FastTBLAccessor.findByKey(mdseStorePkg);
        if (mdseStorePkg == null) {

            return null;
        }

        if (!ZYPCommonFunc.hasValue(mdseStorePkg.inPoundWt)) {

            return null;
        }

        return mdseStorePkg.inPoundWt.getValue().multiply(ordCustUomQty);
    }

    /**
     * get order MDSE
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return MDSE for order take
     */
    public static MDSETMsg getOrderMdse(String glblCmpyCd, String mdseCd) {

        if (S21StringUtil.isEmpty(mdseCd)) {
            return null;
        }

        MDSETMsg mdse = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdse == null) {
            return null;
        }
        if (!S21StringUtil.isEquals(mdse.rgtnStsCd.getValue(), RGTN_STS.READY_FOR_ORDER_TAKING)) {
            return null;
        }
        return mdse;
    }

    /**
     * get service machine master PK by service configuration master
     * PK
     * @param glblCmpyCd String
     * @param svcConfigMstrPk BigDecimal
     * @return service machine master PK
     */
    public static BigDecimal getSvcMachMstrPKBySvcConfigMstrPk(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        if (svcConfigMstrPk == null) {

            return null;
        }

        SVC_CONFIG_MSTRTMsg svcConfigMstr = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcConfigMstr.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcConfigMstr.svcConfigMstrPk, svcConfigMstrPk);
        svcConfigMstr = (SVC_CONFIG_MSTRTMsg) S21FastTBLAccessor.findByKey(svcConfigMstr);
        if (svcConfigMstr == null) {

            return null;
        }
        return svcConfigMstr.svcMachMstrPk.getValue();
    }

    /**
     * get service machine master by serial number PK
     * @param glblCmpyCd String
     * @param serNum String
     * @param mdseCd String
     * @return service machine master
     */
    public static Map<String, Object> getSvcMachMstrBySerNumAndMdseCd(String glblCmpyCd, String serNum, String mdseCd, String slsDt) {

        if (serNum == null) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("serNum", serNum);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("slsDt", slsDt);

        Map<String, Object> result = NWXC412001Query.getInstance().getSvcMachMstrBySerNum(ssmParam);

        return result;
    }

    /**
     * multiply big decimal
     * @param value1 BigDecimal
     * @param value2 BigDecimal
     * @return result
     */
    public static BigDecimal multiplyBigDecimal(BigDecimal value1, BigDecimal value2) {

        if (value1 == null) {

            return null;
        }

        if (value2 == null) {

            return null;
        }

        return value1.multiply(value2);
    }

    /**
     * get model Id by model name
     * @param glblCmpyCd String
     * @param mdlNm String
     * @return model ID
     */
    public static BigDecimal getMdlIdByName(String glblCmpyCd, String mdlNm) {

        if (S21StringUtil.isEmpty(mdlNm)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdlNm", mdlNm);

        BigDecimal result = NWXC412001Query.getInstance().getMdlIdByName(ssmParam);

        return result;
    }

    /**
     * get service configuration master PK
     * @param glblCmpyCd String
     * @param serNum String
     * @param slsDt String
     * @return model ID
     */
    public static BigDecimal getSvcConfigMstrPkBySerNum(String glblCmpyCd, String serNum, String slsDt) {

        if (S21StringUtil.isEmpty(serNum)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("serNum", serNum);
        ssmParam.put("slsDt", slsDt);

        BigDecimal result = NWXC412001Query.getInstance().getSvcConfigMstrPkBySerNum(ssmParam);

        return result;
    }

    /**
     * get default payment term cash discount code
     * @param glblCmpyCd String
     * @param billToCustPk BigDecimal
     * @param dsAcctNum String
     * @return payment term cash discount code
     */
    public static String getDefaultPmtTermCashDiscCd(String glblCmpyCd, BigDecimal billToCustPk, String dsAcctNum) {

        if (billToCustPk != null) {

            CUST_CR_PRFLTMsg custCrPrfl = new CUST_CR_PRFLTMsg();
            ZYPEZDItemValueSetter.setValue(custCrPrfl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(custCrPrfl.billToCustPk, billToCustPk);
            custCrPrfl = (CUST_CR_PRFLTMsg) S21FastTBLAccessor.findByKey(custCrPrfl);
            if (custCrPrfl != null) {
                return custCrPrfl.pmtTermCashDiscCd.getValue();
            }
        }

        if (S21StringUtil.isNotEmpty(dsAcctNum)) {

            DS_ACCT_CR_PRFLTMsg dsAcctCrPrfl = new DS_ACCT_CR_PRFLTMsg();
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrfl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrfl.dsAcctNum, dsAcctNum);
            dsAcctCrPrfl = (DS_ACCT_CR_PRFLTMsg) S21FastTBLAccessor.findByKey(dsAcctCrPrfl);
            if (dsAcctCrPrfl != null) {

                return dsAcctCrPrfl.pmtTermCashDiscCd.getValue();
            }
        }

        return null;
    }

    /**
     * get service merchandise code
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param trxTpTxt String
     * @param somContrTp String
     * @return service merchandise code
     */
    public static String getDsImptSvcMdseCd(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String trxTpTxt, String shellType, String somContrTp, String dclnMaintTxt) {

        if (S21StringUtil.isEmpty(trxTpTxt)) {

            return null;
        }

        //if (S21StringUtil.isEmpty(somContrTp)) {

        //    return null;
        //}

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", trxTpTxt);
        ssmParam.put("srcAttrbTxt02", shellType);
        ssmParam.put("srcAttrbTxt03", somContrTp);
        ssmParam.put("srcAttrbTxt04", dclnMaintTxt);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_SVC_MDSE_CD);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return (String) result.get(0).get("TRGT_ATTRB_TXT_01");
        }

        return null;
    }

    /**
     * get price service contract type code
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param contrTpDescTxt String
     * @return price service contract type code
     */
    public static String getPrcSvcContrTpCd(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String contrTpDescTxt) {

        if (S21StringUtil.isEmpty(contrTpDescTxt)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", contrTpDescTxt);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_SVC_CONTR_TP_CD);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return (String) result.get(0).get("TRGT_ATTRB_TXT_01");
        }

        return null;
    }

    /**
     * get price service plan type code
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param planDescTxt String
     * @return price service plan type code
     */
    public static String getPrcSvcPlnTpCd(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String planDescTxt) {

        if (S21StringUtil.isEmpty(planDescTxt)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", planDescTxt);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_PRC_SVC_PLN_TP_CD);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return (String) result.get(0).get("TRGT_ATTRB_TXT_01");
        }

        return null;
    }

    /**
     * get contract category code
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param contrIndSomTxt String
     * @param isFleetIndSomTxt String
     * @return contract category code
     */
    public static String getDsContrCatgCd(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String contrIndSomTxt, String isFleetIndSomTxt) {

        if (S21StringUtil.isEmpty(contrIndSomTxt)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", contrIndSomTxt);
        ssmParam.put("srcAttrbTxt02", isFleetIndSomTxt);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_DS_CONTR_CATG_CD);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return (String) result.get(0).get("TRGT_ATTRB_TXT_01");
        }

        return null;
    }

    /**
     * get billing cycle code
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param bllgCycleDescTxt String
     * @return billing cycle code
     */
    public static String getBllgCycleCd(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String bllgCycleDescTxt) {

        if (S21StringUtil.isEmpty(bllgCycleDescTxt)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", bllgCycleDescTxt);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_BLLG_CYCLE_CD);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return (String) result.get(0).get("TRGT_ATTRB_TXT_01");
        }

        return null;
    }

    /**
     * get bill by type code
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param fleetBillByTxt String
     * @return bill by type code
     */
    //QC#15539-14
    public static String getBillByTpCd(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String fleetBillByTxt, String dsOrdTpCd) {

        if (S21StringUtil.isEmpty(fleetBillByTxt)) {

            return null;
        }

        if (S21StringUtil.isEmpty(dsOrdTpCd)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", fleetBillByTxt);
        ssmParam.put("srcAttrbTxt02", dsOrdTpCd);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_BILL_BY_TP_CD);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return (String) result.get(0).get("TRGT_ATTRB_TXT_01");
        }

        return null;
    }

    /**
     * get meter read method code
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param trxTpTxt String
     * @return meter read method code
     */
    public static String getMtrReadMethCd(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String mtrReadMethTxt) {

        if (S21StringUtil.isEmpty(mtrReadMethTxt)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", mtrReadMethTxt);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_MTR_READ_METH_CD);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return (String) result.get(0).get("TRGT_ATTRB_TXT_01");
        }

        return null;
    }

    /**
     * get DS model
     * @param glblCmpyCd String
     * @param mdlId BigDecimal
     * @return active model(DS_MDL)
     */
    public static DS_MDLTMsg getActiveDsMdl(String glblCmpyCd, BigDecimal mdlId) {

        if (mdlId == null) {

            return null;
        }

        DS_MDLTMsg dsMdl = new DS_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(dsMdl.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsMdl.mdlId, mdlId);
        dsMdl = (DS_MDLTMsg) S21FastTBLAccessor.findByKey(dsMdl);
        if (dsMdl == null) {

            return null;
        }

        if (!S21StringUtil.isEquals(dsMdl.rgtnStsCd.getValue(), RGTN_STS.READY_FOR_ORDER_TAKING)) {

            return null;
        }

        if (!S21StringUtil.isEquals(dsMdl.mdlActvFlg.getValue(), ZYPConstant.FLG_ON_Y)) {

            return null;
        }
        return dsMdl;
    }

    /**
     * get DS contract PK by contract number
     * @param glblCmpyCd String
     * @param dsContrNum String
     * @param slsDt String
     * @return DS contract PK
     */
    public static BigDecimal getDsContrPkByContrNum(String glblCmpyCd, String dsContrNum, String slsDt) {

        if (S21StringUtil.isEmpty(dsContrNum)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrNum", dsContrNum);
        ssmParam.put("slsDt", slsDt);

        Map<String, Object> result = NWXC412001Query.getInstance().getDsContrByContrNum(ssmParam);

        if (result != null && !result.isEmpty()) {

            return (BigDecimal) result.get("DS_CONTR_PK");
        }

        return null;
    }

    /**
     * get price meter package from name
     * @param glblCmpyCd String
     * @param prcMtrPkgNm String
     * @param slsDt String
     * @return price meter package
     */
    public static Map<String, Object> getPrcMtrPkgFromName(String glblCmpyCd, String prcMtrPkgNm, String slsDt) {

        if (S21StringUtil.isEmpty(prcMtrPkgNm)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prcMtrPkgNm", prcMtrPkgNm.toUpperCase());
        ssmParam.put("slsDt", slsDt);

        Map<String, Object> result = NWXC412001Query.getInstance().getPrcMtrPkgFromName(ssmParam);

        return result;
    }

    /**
     * get price meter package PK from name
     * @param glblCmpyCd String
     * @param prcMtrPkgNm String
     * @param slsDt String
     * @return price meter package
     */
    public static BigDecimal getPrcMtrPkgPkFromName(String glblCmpyCd, String prcMtrPkgNm, String slsDt) {

        Map<String, Object> result = getPrcMtrPkgFromName(glblCmpyCd, prcMtrPkgNm, slsDt);

        if (result != null && !result.isEmpty()) {

            return (BigDecimal) result.get("PRC_MTR_PKG_PK");
        }

        return null;
    }

    /**
     * get price meter package meter structure
     * @param glblCmpyCd String
     * @param prcMtrPkgPk BigDecimal
     * @param mdlId BigDecimal
     * @param somSrvCntrNm String
     * @param slsDt String
     * @return price meter package meter structure
     */
    public static List<Map<String, Object>> getPrcMtrPkgMtrStru(String glblCmpyCd, BigDecimal prcMtrPkgPk, BigDecimal mdlId, String somSrvCntrNm, String slsDt) {

        if (prcMtrPkgPk == null) {

            return new ArrayList<Map<String, Object>>();
        }

        if (mdlId == null) {

            return new ArrayList<Map<String, Object>>();
        }

        if (S21StringUtil.isEmpty(somSrvCntrNm)) {

            return new ArrayList<Map<String, Object>>();
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prcMtrPkgPk", prcMtrPkgPk);
        ssmParam.put("mdlId", mdlId);
        ssmParam.put("mtrLbDescTxt", somSrvCntrNm);
        ssmParam.put("slsDt", slsDt);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getPrcMtrPkgMtrStru(ssmParam);

        return result;
    }

    /**
     * divide time from to
     * @param source String
     * @return divide time from to list
     */
    public static String[] divideTimeFromTo(String source) {

        String[] divideTimeFromTo = new String[2];

        if (S21StringUtil.isEmpty(source)) {

            return divideTimeFromTo;
        }

        //String timeFromTo = source.replaceAll("[A-Z]|[a-z]", "").trim();
        //String timeFromTo = source.replaceAll("[^0123456789-:]", "").trim();
        String timeFromTo = source.replaceAll("[^0123456789-]", "").trim();

        if (S21StringUtil.isEmpty(source)) {

            return divideTimeFromTo;
        }

        String[] timeFromToArray = timeFromTo.split("-");

        divideTimeFromTo[0] = timeFormat(timeFromToArray[0]);

        int fromHour = 0;
        try {
            fromHour = Integer.parseInt(timeFromToArray[0]);
        } catch (Exception e) {
        }

        if (timeFromToArray.length > 1) {

            divideTimeFromTo[1] = timeFormat(timeFromToArray[1]);
            int toHour = 0;
            try {
                toHour = Integer.parseInt(timeFromToArray[1]);
            } catch (Exception e) {
            }

            if (fromHour >= toHour) {
                toHour = toHour + 12;
                divideTimeFromTo[1] = timeFormat(String.valueOf(toHour));
            }
        }

        return divideTimeFromTo;
    }

    private static String timeFormat(String source) {

        if (S21StringUtil.isEmpty(source)) {

            return null;
        }
        source = source.trim();
        if (S21StringUtil.isEmpty(source)) {

            return null;
        }

        // split hour and minute
        String[] hourMinute = source.split(":");
        String hour = hourMinute[0];
        String minute = null;
        if (hourMinute.length > 1) {

            minute = hourMinute[1];
        }

        // numeric
        String regex = "\\d";
        Pattern pattern = Pattern.compile(regex);

        // hours
        pattern.matcher(hour);
        if (!pattern.matcher(hour).find()) {

            return null;
        }
        if (Integer.parseInt(hour) > 24) {

            return null;
        }

        // minutes
        if (S21StringUtil.isEmpty(minute)) {

            minute = "0";
        }
        if (!pattern.matcher(minute).find()) {

            return null;
        }

        if (Integer.parseInt(minute) > 59) {

            return null;
        }

        return String.format("%02d%02d", Integer.parseInt(hour), Integer.parseInt(minute));
    }

    /**
     * get TOC by resource id
     * @param glblCmpyCd String
     * @param slsRepPsnNum String
     * @param slsDt String
     * @return price meter package
     */
    public static Map<String, Object> getSlsRepTocByResourceId(String glblCmpyCd, String slsRepPsnNum, String slsDt) {

        if (S21StringUtil.isEmpty(slsRepPsnNum)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsRepPsnCd", slsRepPsnNum);
        ssmParam.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        ssmParam.put("slsDt", slsDt);

        Map<String, Object> result = NWXC412001Query.getInstance().getSlsRepTocByResourceId(ssmParam);

        return result;
    }

    /**
     * get next address label text for out bound
     * @param currentAddrLbTxt String
     * @return next address label text
     */
    public static String getNextAddrLbTxtForOutbound(String currentAddrLbTxt) {

        if (S21StringUtil.isEmpty(currentAddrLbTxt)) {

            return "A";
        }

        String firstTxt = S21StringUtil.subStringByLength(currentAddrLbTxt, 0, 1);

        char[] digit1 = firstTxt.toCharArray();
        char[] digit2 = Character.toChars(digit1[0] + 1);
        return String.valueOf(digit2[0]);
    }

    /**
     * get next address label text for in bound
     * @param currentAddrLbTxt String
     * @return next address label text
     */
    public static String getNextAddrLbTxtForInbound(String currentAddrLbTxt) {

        if (S21StringUtil.isEmpty(currentAddrLbTxt)) {

            return "R1";
        }

        String firstTxt = S21StringUtil.subStringByLength(currentAddrLbTxt, 0, 1);
        String numberTxt = null;
        if (currentAddrLbTxt.length() > 1) {

            numberTxt = S21StringUtil.subStringByLength(currentAddrLbTxt, 1, currentAddrLbTxt.length() - 1);
        }

        if (S21StringUtil.isEmpty(numberTxt)) {

            return "R1";
        }

        int digit23 = Integer.parseInt(numberTxt);

        return firstTxt + String.valueOf(digit23 + 1);
    }

    /**
     * get lease purchase option code
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param leaseTpTxt String
     * @return lease purchase option code
     */
    public static String getLeasePrchOptCd(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String leaseTpTxt) {

        if (S21StringUtil.isEmpty(leaseTpTxt)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", leaseTpTxt);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_LEASE_PRCH_OPT_CD);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return (String) result.get(0).get("TRGT_ATTRB_TXT_01");
        }

        return null;
    }

    /**
     * get vender information
     * @param glblCmpyCd String
     * @param prntVndCd String
     * @param slsDt String
     * @return vender information
     */
    //public static Map<String, Object> getVendorInfo(String glblCmpyCd, String vndCd, String slsDt) {
    public static Map<String, Object> getVendorInfo(String glblCmpyCd, String prntVndCd, String slsDt, String arcsSplySiteCd) {

        //if (S21StringUtil.isEmpty(vndCd)) {
        if (S21StringUtil.isEmpty(prntVndCd)) {

            return null;
        }
        if (S21StringUtil.isEmpty(arcsSplySiteCd)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        //ssmParam.put("vndCd", vndCd);
        ssmParam.put("prntVndCd", prntVndCd);
        ssmParam.put("arcsSplySiteCd", arcsSplySiteCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        Map<String, Object> result = NWXC412001Query.getInstance().getVendorInfo(ssmParam);

        return result;
    }

    /**
     * get CSMP price list code
     * @param glblCmpyCd String
     * @param csmpNum String
     * @param shipToAcctNum String
     * @param slsDt String
     * @return CSMP price list code
     */
    public static String getCsmpPrcListCd(String glblCmpyCd, String csmpNum, String shipToAcctNum, String slsDt) {

        if (S21StringUtil.isEmpty(csmpNum)) {

            return null;
        }

        if (S21StringUtil.isEmpty(shipToAcctNum)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("csmpNum", csmpNum);
        ssmParam.put("shipToAcctNum", shipToAcctNum);
        ssmParam.put("slsDt", slsDt);

        Map<String, Object> result = NWXC412001Query.getInstance().getCsmpContrInfo(ssmParam);

        if (result != null && !result.isEmpty()) {

            return (String) result.get("PRC_CATG_CD");
        }

        return null;
    }

    /**
     * format OrdSrcRefNum
     * @param currentAddrLbTxt String
     * @return next address label text
     */
    public static String formatOrdSrcRefNum(String somQuoteId) {

        if (S21StringUtil.isEmpty(somQuoteId)) {

            return null;
        }

        if (somQuoteId.length() > 7) {

            return somQuoteId;
        }

        return S21StringUtil.concatStrings("S", String.format("%7s", somQuoteId).replace(" ", "0"));
    }

    /**
     * QC#18798
     * format OrdSrcRefNum
     * @param quoteId String
     * @param prefixStr String
     * @param len cutoff length
     * @return order source reference num. prefix + quiteId (cutoff by length)
     */
    public static String formatOrdSrcRefNum(String quoteId, String prefixStr, int len) {

        if (S21StringUtil.isEmpty(quoteId)) {
            return null;
        }

        if (quoteId.length() > len) {
            return quoteId;
        }

        String fmt = S21StringUtil.concatStrings("%", String.valueOf(len), "s");
        return S21StringUtil.concatStrings(prefixStr, String.format(fmt, quoteId).replace(" ", "0"));
    }

    /**
     * get Line Business Role Type Code
     * @param glblCmpyCd String
     * @param dsOrdTpCd String
     * @param primRepRoleFlg String
     * @return line business role type code
     */
    public static String getLineBizRoleTpCd(String glblCmpyCd, String dsOrdTpCd, String primRepRoleFlg) {

        if (S21StringUtil.isEmpty(dsOrdTpCd)) {

            return null;
        }

        if (S21StringUtil.isEmpty(primRepRoleFlg)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("primRepRoleFlg", primRepRoleFlg);

        Map<String, Object> result = NWXC412001Query.getInstance().getLineBizRoleTpCd(ssmParam);
        if (result != null && !result.isEmpty()) {

            return (String) result.get("LINE_BIZ_ROLE_TP_CD");
        }

        return null;
    }

    /**
     * get Is Fleet Indicator SOM Text
     * @param glblCmpyCd String
     * @param contrIndSomTxt String
     * @param fleetPlanDescTxt String
     * @param somContrInd String
     * @return Is Fleet Indicator SOM Text
     */
    public static String getIsFleetIndSomTxt(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String contrIndSomTxt, String fleetPlanDescTxt, String somContrInd) {
        String isFleetIndSomTxt = null;
        if (somContrInd.toString().equals(contrIndSomTxt)) {
            isFleetIndSomTxt = ZYPCommonFunc.hasValue(fleetPlanDescTxt) ? ZYPConstant.FLG_ON_Y : ZYPConstant.FLG_OFF_N;
        } else {
            isFleetIndSomTxt = null;
        }
        return getDsContrCatgCd(glblCmpyCd, cpoSrcTpCd, intfcId, contrIndSomTxt, isFleetIndSomTxt);
    }

    public static String toUpperCase(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return str.toUpperCase();
    }

    public static String cutString(String str, int length) {
        if (str == null || str.length() == 0 || str.length() <= length) {
            return str;
        }
        return str.substring(0, length);
    }

    /**
     * get model Id by model name
     * @param glblCmpyCd String
     * @param mdlNm String
     * @return model ID
     */
    public static BigDecimal getMdlIdByMdseForNonCopier(String glblCmpyCd, String mdseCd) {

        if (S21StringUtil.isEmpty(mdseCd)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);

        BigDecimal result = NWXC412001Query.getInstance().getMdlIdByMdse(ssmParam);

        return result;
    }

    /**
     * get MDSE
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return merchandise information
     */
    public static Map<String, Object> getPsnCdByTocNm(String glblCmpyCd, String psnNm) {

        if (S21StringUtil.isEmpty(psnNm)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("psnNm", psnNm);

        Map<String, Object> result = NWXC412001Query.getInstance().getPsnCdByTocNm(ssmParam);

        return result;
    }

    /**
     * get MDSE
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return merchandise information
     */
    public static Map<String, Object> getPsnCdByPsnFullNm(String glblCmpyCd, String psnNm) {

        if (S21StringUtil.isEmpty(psnNm)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("psnNm", psnNm);

        Map<String, Object> result = NWXC412001Query.getInstance().getPsnCdByPsnFullNm(ssmParam);

        return result;
    }

    public static Map<String, Object> getPsnCdByPsnNm(String glblCmpyCd, String psnNm) {

        if (S21StringUtil.isEmpty(psnNm)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("psnNm", psnNm);

        Map<String, Object> result = NWXC412001Query.getInstance().getPsnCdByPsnNm(ssmParam);

        return result;
    }

    /**
     * get MDSE
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return merchandise information
     */
    public static Map<String, Object> getPsnCdByPsnCd(String glblCmpyCd, String psnCd) {

        if (S21StringUtil.isEmpty(psnCd)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("psnCd", psnCd);

        Map<String, Object> result = NWXC412001Query.getInstance().getPsnCdByPsnCd(ssmParam);

        return result;
    }

    /**
     * get MDSE
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return merchandise information
     */
    // START 2018/02/08 K.Kojima [QC#22242,MOD]
    // public static Map<String, Object> getDefaultMaintenanceCustomer(String glblCmpyCd, String dsOrdCatgCd, String billByTpCd) {
    public static Map<String, Object> getDefaultMaintenanceCustomer(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd, String billByTpCd) {
    // END 2018/02/08 K.Kojima [QC#22242,MOD]

        if (S21StringUtil.isEmpty(glblCmpyCd)) {

            return null;
        }

        if (S21StringUtil.isEmpty(dsOrdCatgCd)) {

            return null;
        }

        // START 2018/02/08 K.Kojima [QC#23357,ADD]
        if (S21StringUtil.isEmpty(dsOrdTpCd)) {

            return null;
        }
        // END 2018/02/08 K.Kojima [QC#23357,ADD]

        if (S21StringUtil.isEmpty(billByTpCd)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdCatgCd", dsOrdCatgCd);
        // START 2018/02/08 K.Kojima [QC#23357,ADD]
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        // END 2018/02/08 K.Kojima [QC#23357,ADD]
        ssmParam.put("billByTpCd", billByTpCd);

        Map<String, Object> result = NWXC412001Query.getInstance().getDefaultMaintenanceCustomer(ssmParam);

        return result;
    }

    //QC#17768
    public static List<Map<String, Object>> getConfigMasterDetailList(String glblCmpyCd, String serNum, String slsDt) {
        if (serNum == null || slsDt == null) {
            return new ArrayList<Map<String, Object>>();
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("serNum", serNum);
        ssmParam.put("slsDt", slsDt);
        List<Map<String, Object>> result = NWXC412001Query.getInstance().getConfigMasterDetailList(ssmParam);
        return result;
    }
    //QC#25324
    public static List<Map<String, Object>> getConfigMasterDetailListFromConfigID(String glblCmpyCd, BigDecimal svcConfigMstrPk, String slsDt) {
        if (svcConfigMstrPk == null || slsDt == null) {
            return new ArrayList<Map<String, Object>>();
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);
        ssmParam.put("slsDt", slsDt);
        List<Map<String, Object>> result = NWXC412001Query.getInstance().getConfigMasterDetailListFromConfigID(ssmParam);
        return result;
    }

    //QC#18013
    /**
     * get Check Order payment term cash disc code and payment method
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param netThrtyIndSomTxt String
     * @return Check Order payment term cash disc code and payment method
     */
    public static Map<String, Object> getCheckOrder(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String chkOrdIndSomTxt) {

        if (S21StringUtil.isEmpty(chkOrdIndSomTxt)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", chkOrdIndSomTxt);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_CHK_ORD);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return result.get(0);
        }

        return null;
    }

    /**
     * get Net30 payment term cash disc code and payment method
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param netThrtyIndSomTxt String
     * @return Net30 payment term cash disc code and payment method
     */
    public static Map<String, Object> getNet30(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String netThrtyIndSomTxt) {

        if (S21StringUtil.isEmpty(netThrtyIndSomTxt)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", netThrtyIndSomTxt);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_NET30);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return result.get(0);
        }

        return null;
    }

    /**
     * get Real Fair Lease purchase option
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param netThrtyIndSomTxt String
     * @return Real Fair Lease purchase option
     */
    public static Map<String, Object> getRealFairInd(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String realFairIndSomTxt) {

        if (S21StringUtil.isEmpty(realFairIndSomTxt)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", realFairIndSomTxt);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_REAL_FAIR_VAL);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return result.get(0);
        }

        return null;
    }

    /**
     * get $1.00 Lease purchase option
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param netThrtyIndSomTxt String
     * @return $1.00 Lease purchase option
     */
    public static Map<String, Object> getCheckWithAgree(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String chkAgmtIndSomTxt) {

        if (S21StringUtil.isEmpty(chkAgmtIndSomTxt)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", chkAgmtIndSomTxt);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_DOLLAR_ONE);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            return result.get(0);
        }

        return null;
    }

    //QC#15539-14
    /**
     * get DS order type information
     * @param glblCmpyCd String
     * @param cpoSrcTpCd String
     * @param intfcId String
     * @param trxTpTxt String
     * @param leaseCmpyNm String
     * @return DS order type information
     */
    public static Map<String, Object> getDsOrdTpInfo(String glblCmpyCd, String cpoSrcTpCd, String intfcId, String trxTpTxt, String leaseCmpyNm) {

        if (S21StringUtil.isEmpty(trxTpTxt)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", cpoSrcTpCd);
        ssmParam.put("intfcId", intfcId);
        ssmParam.put("srcAttrbTxt01", trxTpTxt);
        ssmParam.put("srcAttrbTxt02", leaseCmpyNm);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP_DS_ORD_TP_CD);

        List<Map<String, Object>> result = NWXC412001Query.getInstance().getXtrnlIntfcXref(ssmParam);
        if (result != null && !result.isEmpty()) {

            //return (String) result.get(0).get("TRGT_ATTRB_TXT_01");
            return (Map<String, Object>) result.get(0);
        }

        return null;
    }

    //QC#15539-14
    /**
     * get bill to information from Account Number
     * @param glblCmpyCd String
     * @param somShipToPtyNum String
     * @param slsDt String
     * @return bill to information
     */
    public static Map<String, Object> getBillToInfoFromDsAcctNum(String glblCmpyCd, String dsAcctNum, String slsDt) {

        if (S21StringUtil.isEmpty(dsAcctNum)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("primUsgFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        Map<String, Object> result = NWXC412001Query.getInstance().getBillToInfoFromDsAcctNum(ssmParam);

        if (result == null) {
            ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("dsAcctNum", dsAcctNum);
            ssmParam.put("slsDt", slsDt);
            ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            result = NWXC412001Query.getInstance().getBillToInfoFromDsAcctNum(ssmParam);
        }

        return result;
    }

    /**
     * get item code
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return upgrade returns item code list
     */
    public static Map<String, Object> getAvailableRentalItem(String glblCmpyCd, String mdseCd, String[] mdseTpCtxTp) {

        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        if (mdseTpCtxTp != null) {
            ssmParam.put("mdseTpCtxTp", mdseTpCtxTp);
        }
        Map<String, Object> resultMap = NWXC412001Query.getInstance().getAvailableRentalItem(ssmParam);

        return resultMap;
    }

    /**
     * get CSA# by Account#, CSMP#
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param csmpNum String
     * @param slsDt String
     * @return price meter package
     */
    public static Map<String, Object> getCSANumberByAcctCSMPNum(String glblCmpyCd, String dsAcctNum, String csmpNum, String slsDt) {

        if (S21StringUtil.isEmpty(dsAcctNum)) {

            return null;
        }

        if (S21StringUtil.isEmpty(csmpNum)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("csmpNum", csmpNum);
        ssmParam.put("slsDt", slsDt);

        Map<String, Object> result = NWXC412001Query.getInstance().getCSANumberByAcctCSMPNum(ssmParam);

        return result;
    }

    //QC#24240
    /**
     * get CSA# by Account#, CSMP#
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param slsDt String
     * @return price meter package
     */
    public static Map<String, Object> getCSANumberByAcctCSMPNumNull(String glblCmpyCd, String dsAcctNum, String slsDt) {

        if (S21StringUtil.isEmpty(dsAcctNum)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("slsDt", slsDt);

        Map<String, Object> result = NWXC412001Query.getInstance().getCSANumberByAcctCSMPNumNull(ssmParam);

        return result;
    }

    public static List<Map<String, Object>> getMdlIdFromMdlNm(String glblCmpyCd, String mdlNm) {
        if (mdlNm == null) {
            return null;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdlNm", mdlNm);
    
        return NWXC412001Query.getInstance().getMdlIdFromMdlNm(ssmParam);
    }

    public static String getSvcCustIstlFlg(String glblCmpyCd, String svcIstlRuleNum) {
        if (!ZYPCommonFunc.hasValue(svcIstlRuleNum)) {
            return ZYPConstant.FLG_OFF_N;
        }
        SVC_ISTL_RULETMsg tMsg = new SVC_ISTL_RULETMsg();
    
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcIstlRuleNum, svcIstlRuleNum);
        tMsg = (SVC_ISTL_RULETMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return ZYPConstant.FLG_OFF_N;
        }
        return tMsg.svcCustIstlFlg.getValue();
    }

    // QC#19451
    public static int getDecimalPointDigit(String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);

        Integer dgt = NWXC412001Query.getInstance().getDecimalPointDigit(ssmParam);
        if (dgt == null) {
            return 2;
        }
        return dgt;
    }

    public static boolean isEqualBigDecimal(BigDecimal val1, BigDecimal val2) {
        if (ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {
            return (val1.compareTo(val2) == 0);
        }
        if (ZYPCommonFunc.hasValue(val1) || ZYPCommonFunc.hasValue(val2)) {
            return false;
        }
        return true;
    }

    public static boolean isEqualBigDecimal(BigDecimal val1, BigDecimal val2, boolean isBothNullTrue) {
        if (ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {
            return (val1.compareTo(val2) == 0);
        }
        if (ZYPCommonFunc.hasValue(val1) || ZYPCommonFunc.hasValue(val2)) {
            return false;
        }
        return isBothNullTrue;
    }

    /**
     * autoCast
     * @param <T> T
     * @param fromObj Object
     * @return T
     */
    public static <T> T autoCast(Object fromObj) {
        T toObj = (T) fromObj;
        return toObj;
    }

    public static <T extends List<?>> boolean hasValueList(T list) {
        return (list != null && list.size() > 0);
    }
    //QC#24206
    public static boolean isCFSOwnerInstallBase(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        if (svcMachMstrPk == null) {
            return false;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        Map<String, Object> map = NWXC412001Query.getInstance().getCFSOwner(ssmParam);
        if (map == null || map.get("SELL_TO_CUST_PK") == null) {
            return false;
        }
        return true;
    }
}
