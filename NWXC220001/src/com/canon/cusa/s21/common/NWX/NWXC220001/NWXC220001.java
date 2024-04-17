package com.canon.cusa.s21.common.NWX.NWXC220001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.DS_IMPT_ORDTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_ITEM_STSTMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PRC_CATGTMsg;
import business.db.SHIP_TO_CUSTTMsg;

import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Exchange;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmount;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangePriceData;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC220001.constant.NWXC220001Constant;
import com.canon.cusa.s21.common.NWX.NWXC220001.constant.NWXC220001Constant.CUSTOMER_TABLE_ID;
import com.canon.cusa.s21.common.NWX.NWXC220001.constant.NWXC220001Constant.MSG_ID;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 * <pre>
 * ImportAttribute
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2016   Fujitsu         M.Hara          Create          N/A
 * 09/16/2016   Fujitsu         S.Ohki          Update          S21_NA#14741
 * 10/24/2016   Fujitsu         T.Ishii         Update          S21_NA#15536
 * 10/25/2016   Fujitsu         T.Ishii         Update          S21_NA#15563
 * </pre>
 */
public final class NWXC220001 {

    public static NWXC220001Result<List<Map<String, Object>>> getDsOrdLineCatgList(String glblCmpyCd, String dsOrdTpCd, String effDt, String dsOrdLineDrctnCd) {

        List<Map<String, Object>> dsOrdLineCatgList = NWXC220001Query.getInstance().getDsOrdLineCatgList(glblCmpyCd, dsOrdTpCd, effDt, dsOrdLineDrctnCd);

        NWXC220001Result<List<Map<String, Object>>> result = new NWXC220001Result<List<Map<String, Object>>>(dsOrdLineCatgList);

        return result;
    }

    public static NWXC220001Result<Map<String, Object>> getPrimDsOrdLineCatg(String glblCmpyCd, String dsOrdTpCd, String effDt, String dsOrdLineDrctnCd) {

        List<Map<String, Object>> dsOrdLineCatgList = NWXC220001Query.getInstance().getDsOrdLineCatgList(glblCmpyCd, dsOrdTpCd, effDt, dsOrdLineDrctnCd);

        for (Map<String, Object> dsOrdLineCatg : dsOrdLineCatgList) {
            if (S21StringUtil.isEquals((String) dsOrdLineCatg.get("PRIM_LINE_CATG_FLG"), ZYPConstant.FLG_ON_Y)) {
                return new NWXC220001Result<Map<String, Object>>(dsOrdLineCatg);
            }
        }
        return new NWXC220001Result<Map<String, Object>>(new HashMap<String, Object>()); // S21NA_#15536
    }

    public static NWXC220001Result<DS_ORD_TP_PROC_DFNTMsg> getDefDsOrdTpProcDfn(DS_ORD_TP_PROC_DFNTMsg inTMsg) {

        DS_ORD_TP_PROC_DFNTMsg outTMsg = (DS_ORD_TP_PROC_DFNTMsg) inTMsg.clone();

        String dsOrdTpCd = NWXC220001Query.getInstance().getDefDsOrdTpProcDfnPk(inTMsg);

        NWXC220001Result<DS_ORD_TP_PROC_DFNTMsg> result;
        if (ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            outTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(outTMsg);
            result = new NWXC220001Result<DS_ORD_TP_PROC_DFNTMsg>(outTMsg);
        } else {
            result = new NWXC220001Result<DS_ORD_TP_PROC_DFNTMsg>(outTMsg, MSG_ID.NWAM0809E, "DS_ORD_TP_PROC_DFN");
        }

        return result;
    }

    public static NWXC220001Result<Map<String, Object>> getQtyInfoForEdi(String glblCmpyCd, String pkgUomCd, String mdseCd, Object idocPoDtlOrdQty) {
        NWXC220001Result<Map<String, Object>> result = new NWXC220001Result<Map<String, Object>>();

        // 09/27/2016 S21_NA#14744 add Start
        String dsMdseStorePkgUomCd = NWXC220001Query.getInstance().getDsMdseStorePkgUomCd(glblCmpyCd, pkgUomCd, mdseCd);
        String ioPkgUomCd = pkgUomCd;

        if (ZYPCommonFunc.hasValue(dsMdseStorePkgUomCd)) {
            ioPkgUomCd = dsMdseStorePkgUomCd;
        }
        // 09/27/2016 S21_NA#14744 add End

        MDSE_STORE_PKGTMsg ioTMsg = new MDSE_STORE_PKGTMsg();

        ZYPEZDItemValueSetter.setValue(ioTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ioTMsg.pkgUomCd, ioPkgUomCd); // 09/27/2016
        // S21_NA#14744
        ZYPEZDItemValueSetter.setValue(ioTMsg.mdseCd, mdseCd);

        ioTMsg = (MDSE_STORE_PKGTMsg) S21CacheTBLAccessor.findByKey(ioTMsg);
        if (ioTMsg == null) {
            result.addErrorInfo(MSG_ID.NWAM0809E, "MDSE_STORE_PKG");
            return result;
        }

        Map<String, Object> resultObjectMap = new HashMap<String, Object>();
        result.setResultObject(resultObjectMap);

        resultObjectMap.put("MDSE_STORE_PKGTMsg", ioTMsg);

        // S21_NA#15563 modify start
        // BigDecimal ordCustUomQty =
        // NWXC220001Util.convToBigDecimal(idocPoDtlOrdQty,
        // BigDecimal.ZERO).multiply(BigDecimal.TEN);
        BigDecimal ordCustUomQty = NWXC220001Util.convToBigDecimal(idocPoDtlOrdQty, BigDecimal.ZERO);
        // S21_NA#15563 modify end

        resultObjectMap.put("ORD_CUST_UOM_QTY", ordCustUomQty);

        BigDecimal ordQty = BigDecimal.ZERO;

        if (BigDecimal.ZERO.compareTo(ioTMsg.inEachQty.getValue()) != 0) {
            // 09/23/2016 S21_NA#14741 mod Start
            // ordQty =
            // ordCustUomQty.divide(ioTMsg.inEachQty.getValue());
            ordQty = ordCustUomQty.multiply(ioTMsg.inEachQty.getValue());
            // 09/23/2016 S21_NA#14741 mod End
        }
        resultObjectMap.put("ORD_QTY", ordQty);

        return result;
    }

    public static NWXC220001Result<NWXC220001CustomerBean> getBillToCustWithDsAcctFromCd(BILL_TO_CUSTTMsg inBillTMsg, String slsDt, CUSTOMER_TABLE_ID... retTableIdParam) {
        NWXC220001Result<NWXC220001CustomerBean> result = new NWXC220001Result<NWXC220001CustomerBean>();

        if (inBillTMsg == null || !ZYPCommonFunc.hasValue(inBillTMsg.billToCustCd)) {
            result.addErrorInfo(MSG_ID.ZZZM9025E, "Bill To Cust Code");
            return result;
        }

        List<?> billInfoList = NWXC220001QueryForCustomer.getInstance().getBillToCustWithDsAcctFromCd(inBillTMsg, slsDt);

        if (!NWXC220001Util.hasValueList(billInfoList)) {
            result.addErrorInfo(MSG_ID.ZZXL0002E);
            return result;
        }

        Map<?, ?> billInfoMap = (Map<?, ?>) billInfoList.get(0);

        NWXC220001CustomerBean resultBean = new NWXC220001CustomerBean();

        BigDecimal billToCustPk = NWXC220001Util.convToBigDecimal(billInfoMap.get("BILL_TO_CUST_PK"));
        BigDecimal sellToCustPk = NWXC220001Util.convToBigDecimal(billInfoMap.get("SELL_TO_CUST_PK"));

        List<CUSTOMER_TABLE_ID> retTableIdList = Arrays.asList(retTableIdParam);
        // *********************************************************************
        // BILL_TO_CUST
        // *********************************************************************
        if (NWXC220001Util.isTargetsContentsList(retTableIdList, CUSTOMER_TABLE_ID.ALL, CUSTOMER_TABLE_ID.BILL_TO_CUST)) {
            resultBean.billToCustTMsg = getBillToCustTMsg(inBillTMsg.glblCmpyCd.getValue(), billToCustPk);
        }

        // *********************************************************************
        // SELL_TO_CUST
        // *********************************************************************
        if (NWXC220001Util.isTargetsContentsList(retTableIdList, CUSTOMER_TABLE_ID.ALL, CUSTOMER_TABLE_ID.SELL_TO_CUST)) {
            resultBean.sellToCustTMsg = getSellToCustTMsg(inBillTMsg.glblCmpyCd.getValue(), sellToCustPk);
        }

        result.setResultObject(resultBean);

        return result;
    }

    public static NWXC220001Result<NWXC220001CustomerBean> getShipToCustWithDsAcctFromCd(SHIP_TO_CUSTTMsg inShipTMsg, String slsDt, CUSTOMER_TABLE_ID... retTableIdParam) {
        NWXC220001Result<NWXC220001CustomerBean> result = new NWXC220001Result<NWXC220001CustomerBean>();

        if (inShipTMsg == null || !ZYPCommonFunc.hasValue(inShipTMsg.shipToCustCd)) {
            result.addErrorInfo(MSG_ID.ZZZM9025E, "Ship To Cust Code");
            return result;
        }

        List<?> shipInfoList = NWXC220001QueryForCustomer.getInstance().getShipToCustWithDsAcctFromCd(inShipTMsg, slsDt);

        if (!NWXC220001Util.hasValueList(shipInfoList)) {
            result.addErrorInfo(MSG_ID.ZZXL0002E);
            return result;
        }

        Map<?, ?> shipInfoMap = (Map<?, ?>) shipInfoList.get(0);

        NWXC220001CustomerBean resultBean = new NWXC220001CustomerBean();

        BigDecimal shipToCustPk = NWXC220001Util.convToBigDecimal(shipInfoMap.get("SHIP_TO_CUST_PK"));
        BigDecimal sellToCustPk = NWXC220001Util.convToBigDecimal(shipInfoMap.get("SELL_TO_CUST_PK"));

        List<CUSTOMER_TABLE_ID> retTableIdList = Arrays.asList(retTableIdParam);
        // *********************************************************************
        // SHIP_TO_CUST
        // *********************************************************************
        if (NWXC220001Util.isTargetsContentsList(retTableIdList, CUSTOMER_TABLE_ID.ALL, CUSTOMER_TABLE_ID.SHIP_TO_CUST)) {
            resultBean.shipToCustTMsg = getShipToCustTMsg(inShipTMsg.glblCmpyCd.getValue(), shipToCustPk);
        }

        // *********************************************************************
        // SELL_TO_CUST
        // *********************************************************************
        if (NWXC220001Util.isTargetsContentsList(retTableIdList, CUSTOMER_TABLE_ID.ALL, CUSTOMER_TABLE_ID.SELL_TO_CUST)) {
            resultBean.sellToCustTMsg = getSellToCustTMsg(inShipTMsg.glblCmpyCd.getValue(), sellToCustPk);
        }

        resultBean.shipCntyNm = NWXC220001Util.convToString(shipInfoMap.get("CNTY_NM"));

        result.setResultObject(resultBean);

        return result;
    }

    private static SELL_TO_CUSTTMsg getSellToCustTMsg(String glblCmpyCd, BigDecimal sellToCustPk) {
        SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();

        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sellToCustPk, sellToCustPk);

        sellToCustTMsg = (SELL_TO_CUSTTMsg) S21FastTBLAccessor.findByKey(sellToCustTMsg);
        NWXC220001Util.checkTMsgDbAccess(sellToCustTMsg);

        return sellToCustTMsg;
    }

    public static BILL_TO_CUSTTMsg getBillToCustTMsg(String glblCmpyCd, BigDecimal billToCustPk) {
        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();

        ZYPEZDItemValueSetter.setValue(billToCustTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.billToCustPk, billToCustPk);

        billToCustTMsg = (BILL_TO_CUSTTMsg) S21FastTBLAccessor.findByKey(billToCustTMsg);
        NWXC220001Util.checkTMsgDbAccess(billToCustTMsg);

        return billToCustTMsg;
    }

    public static SHIP_TO_CUSTTMsg getShipToCustTMsg(String glblCmpyCd, BigDecimal shipToCustPk) {
        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();

        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.shipToCustPk, shipToCustPk);

        shipToCustTMsg = (SHIP_TO_CUSTTMsg) S21FastTBLAccessor.findByKey(shipToCustTMsg);
        NWXC220001Util.checkTMsgDbAccess(shipToCustTMsg);

        return shipToCustTMsg;
    }

    public static <T extends EZDTMsg> NWXC220001Result<T> getTMsg(T tMsg) {

        T outTMsg = (T) tMsg.clone();

        outTMsg = (T) S21FastTBLAccessor.findByKey(outTMsg);

        NWXC220001Result<T> result;
        if (outTMsg != null) {
            result = new NWXC220001Result<T>(outTMsg);
        } else {
            result = new NWXC220001Result<T>(outTMsg, MSG_ID.NWAM0809E, tMsg.getTableName());
        }

        return result;
    }

    public static NWXC220001Result<PRC_CATGTMsg> getPrcCatgTMsg(String glblCmpyCd, String prcCatgCd) {

        PRC_CATGTMsg outTMsg = new PRC_CATGTMsg();

        ZYPEZDItemValueSetter.setValue(outTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(outTMsg.prcCatgCd, prcCatgCd);

        return getTMsg(outTMsg);
    }

    public static NWXC220001Result<MDSE_STORE_PKGTMsg> getMdseStorePkgTMsg(String glblCmpyCd, String pkgUomCd, String mdseCd) {

        MDSE_STORE_PKGTMsg outTMsg = new MDSE_STORE_PKGTMsg();
        String searchMdse = mdseCd;

        if (mdseCd.length() == 8) {
            NWXC220001Result<ORD_TAKE_MDSETMsg> ordTakeMdse = getOrdTakeMdseTMsg(glblCmpyCd, mdseCd);
            if (ordTakeMdse.hasResultObject()) {
                searchMdse = ordTakeMdse.getResultObject().mdseCd.getValue();
            }
        }
        ZYPEZDItemValueSetter.setValue(outTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(outTMsg.pkgUomCd, pkgUomCd);
        ZYPEZDItemValueSetter.setValue(outTMsg.mdseCd, searchMdse);

        return getTMsg(outTMsg);
    }

    public static NWXC220001Result<ORD_TAKE_MDSETMsg> getOrdTakeMdseTMsg(String glblCmpyCd, String ordTakeMdseCd) {

        ORD_TAKE_MDSETMsg outTMsg = new ORD_TAKE_MDSETMsg();

        ZYPEZDItemValueSetter.setValue(outTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(outTMsg.ordTakeMdseCd, ordTakeMdseCd);

        return getTMsg(outTMsg);
    }

    /**
     * checkDuplicateOrdSrcRefNum
     * @param glblCmpyCd String
     * @param ordSrcRefNum String
     * @param cpoSrcTpCd String
     * @param dsImptOrdPkOwn BigDecimal
     * @return NWXC220001Result(needs to delete PK list map & error
     * information)
     */
    public static NWXC220001Result<Map<String, List<BigDecimal>>> checkDuplicateOrdSrcRefNum(String glblCmpyCd, String ordSrcRefNum, String cpoSrcTpCd, BigDecimal dsImptOrdPkOwn) {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("ordSrcRefNum", ordSrcRefNum);
        queryParam.put("cpoSrcTpCd", cpoSrcTpCd);
        queryParam.put("dsImptOrdPkOwn", dsImptOrdPkOwn);

        List<Map<String, Object>> duplicateDsImptOrdList = NWXC220001Query.getInstance().getDuplicateDsImptOrdList(queryParam);
        List<BigDecimal> deleteDsImptOrdPkList = new ArrayList<BigDecimal>();
        List<BigDecimal> deleteDsImptSvcDtlPkList = new ArrayList<BigDecimal>();
        List<BigDecimal> deleteDsImptSchdTmplPkList = new ArrayList<BigDecimal>();

        boolean hasCpo = false;
        String cpoOrdNum = null;
        for (Map<String, Object> duplicateDsImptOrd : duplicateDsImptOrdList) {

            cpoOrdNum = (String) duplicateDsImptOrd.get("CPO_ORD_NUM");
            String imptOvrdMsgFlg = (String) duplicateDsImptOrd.get("IMPT_OVRD_MSG_FLG");
            BigDecimal dsImptOrdPk = (BigDecimal) duplicateDsImptOrd.get("DS_IMPT_ORD_PK");

            if (S21StringUtil.isNotEmpty(cpoOrdNum)) {

                // CPO has already created
                hasCpo = true;
            } else if (S21StringUtil.isEquals(imptOvrdMsgFlg, ZYPConstant.FLG_OFF_N)) {

                // exclude "Success" -> invalid case
                hasCpo = true;
            } else {

                // needs to delete
                if (lockDsImptOrd(glblCmpyCd, dsImptOrdPk) != null) {

                    deleteDsImptOrdPkList.add(dsImptOrdPk);

                    queryParam.clear();
                    queryParam.put("glblCmpyCd", glblCmpyCd);
                    queryParam.put("dsImptOrdPk", dsImptOrdPk);
                    List<Map<String, Object>> duplicateDsImptPkList = NWXC220001Query.getInstance().getDsImptPkList(queryParam);
                    for (Map<String, Object> duplicateDsImptPk : duplicateDsImptPkList) {

                        BigDecimal dsImptSvcDtlPk = (BigDecimal) duplicateDsImptPk.get("DS_IMPT_SVC_DTL_PK");
                        BigDecimal dsImptSchdTmplPk = (BigDecimal) duplicateDsImptPk.get("DS_IMPT_SCHD_TMPL_PK");

                        if (dsImptSvcDtlPk != null && !deleteDsImptSvcDtlPkList.contains(dsImptSvcDtlPk)) {

                            deleteDsImptSvcDtlPkList.add(dsImptSvcDtlPk);
                        }
                        if (dsImptSchdTmplPk != null && !deleteDsImptSchdTmplPkList.contains(dsImptSchdTmplPk)) {

                            deleteDsImptSchdTmplPkList.add(dsImptSchdTmplPk);
                        }
                    }
                }
            }
        }

        NWXC220001Result<Map<String, List<BigDecimal>>> result;

        Map<String, List<BigDecimal>> resultMap = new HashMap<String, List<BigDecimal>>();
        resultMap.put("dsImptOrdPk", deleteDsImptOrdPkList);
        resultMap.put("dsImptSvcDtlPk", deleteDsImptSvcDtlPkList);
        resultMap.put("dsImptSchdTmplPk", deleteDsImptSchdTmplPkList);

        if (hasCpo) {
            // The target data has already been processed. CPO#@,
            // Reference#@
            result = new NWXC220001Result<Map<String, List<BigDecimal>>>(resultMap, MSG_ID.NWAM2200E, cpoOrdNum, ordSrcRefNum);
        } else {
            result = new NWXC220001Result<Map<String, List<BigDecimal>>>(resultMap);
        }

        return result;
    }

    private static BigDecimal lockDsImptOrd(String glblCmpyCd, BigDecimal deleteDsImptOrdPk) {

        DS_IMPT_ORDTMsg dsImptOrd = new DS_IMPT_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(dsImptOrd.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrd.dsImptOrdPk, deleteDsImptOrdPk);
        dsImptOrd = (DS_IMPT_ORDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsImptOrd);
        if (dsImptOrd != null && S21StringUtil.isEquals(dsImptOrd.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {
            return deleteDsImptOrdPk;
        } else {
            return null;
        }
    }

    /**
     * getValidDsOrdCatgCdList
     * @param glblCmpyCd String
     * @param bizId String
     * @return <Map<DsOrdCatgCd, DsOrdCatgNm>
     */
    public static NWXC220001Result<Map<String, String>> getValidDsOrdCatgCdMap(String glblCmpyCd, String bizId) {

        NWXC220001Result<Map<String, String>> result = new NWXC220001Result<Map<String, String>>();

        Map<String, String> retList = new HashMap<String, String>();
        result.setResultObject(retList);

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("bizId", bizId);

        List<?> validDsOrdCatgCdList = NWXC220001Query.getInstance().getValidDsOrdCatgCdList(queryParam);

        if (!NWXC220001Util.hasValueList(validDsOrdCatgCdList)) {
            result.addErrorInfo(MSG_ID.ZZXL0002E);
            return result;
        }

        Map<?, ?> validMap;
        for (int i = 0; i < validDsOrdCatgCdList.size(); i++) {
            validMap = (Map<?, ?>) validDsOrdCatgCdList.get(i);

            retList.put(NWXC220001Util.convToString(validMap.get("DS_ORD_CATG_CD")), NWXC220001Util.convToString(validMap.get("DS_ORD_CATG_DESC_TXT")));
        }

        return result;
    }

    public static Map<String, List<String>> getBaseComponent(String glblCmpyCd, List<String> mdseList, String baseComponentMdseCd) {

        Map<String, List<String>> resultMap = new HashMap<String, List<String>>();

        List<String> parent = new ArrayList<String>();
        List<String> children = new ArrayList<String>();

        resultMap.put(NWXC220001Constant.BASE_MDSE, parent);
        resultMap.put(NWXC220001Constant.CHILD_MDSE, children);

        if (mdseList == null) {
            return resultMap;
        }

        String baseComponentMdseCdCompare = baseComponentMdseCd;
        if (S21StringUtil.isEmpty(baseComponentMdseCd)) {

            baseComponentMdseCdCompare = getBaseComponentMdseCd(glblCmpyCd, mdseList);
        }

        for (String mdseCd : mdseList) {

            if (S21StringUtil.isNotEmpty(baseComponentMdseCdCompare)) {

                if (S21StringUtil.isEquals(mdseCd, baseComponentMdseCdCompare)) {

                    parent.add(mdseCd);
                } else {

                    children.add(mdseCd);
                }

            } else {

                children.add(mdseCd);
            }
        }

        return resultMap;
    }

    public static String getBaseComponentMdseCd(String glblCmpyCd, List<String> mdseList) {

        String firstInstallBaseMdseCd = null;
        for (String mdseCd : mdseList) {

            MDSETMsg mdse = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
            if (mdse == null) {

                continue;
            }
            if (S21StringUtil.isEquals(mdse.mdseTpCd.getValue(), MDSE_TP.SET)) {

                continue;
            }

            // get base component flag
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("mdseCd", mdse.mdseCd.getValue());
            ssmParam.put("mdseTpCtxTpCd", "MAIN_MACH");
            Map<String, Object> baseComponentFlag = NWXC220001Query.getInstance().getBaseComponentFlag(ssmParam);
            if (baseComponentFlag == null) {
                continue;
            }

            String instlBaseCtrlFlg = (String) baseComponentFlag.get("INSTL_BASE_CTRL_FLG");
            String mdseTpCtxTpCd = (String) baseComponentFlag.get("MDSE_TP_CTX_TP_CD");

            if (S21StringUtil.isNotEmpty(mdseTpCtxTpCd)) {

                // main machine
                return mdseCd;
            }

            if (S21StringUtil.isNotEmpty(firstInstallBaseMdseCd)) {

                continue;
            }

            if (S21StringUtil.isEquals(instlBaseCtrlFlg, ZYPConstant.FLG_ON_Y)) {

                // first install base
                firstInstallBaseMdseCd = mdseCd;
            }
        }
        return firstInstallBaseMdseCd;
    }

    public static boolean needsWarehouseMdse(String glblCmpyCd, String mdseCd) {

        if (S21StringUtil.isEmpty(mdseCd)) {
            return false;
        }

        MDSETMsg mdse = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdse == null) {
            return false;
        }

        if (!S21StringUtil.isEquals(mdse.invtyCtrlFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
            // QC#26584
            if (S21StringUtil.isEquals(MDSE_TP.SET, mdse.mdseTpCd.getValue())) {
                return true;
            }
            return false;
        }
        return true;
    }

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

    public static MDSE_ITEM_STSTMsg getMdseItemStatus(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdse = getOrderMdse(glblCmpyCd, mdseCd);
        if (mdse == null) {
            return null;
        }
        MDSE_ITEM_STSTMsg mdseItemSts = new MDSE_ITEM_STSTMsg();
        ZYPEZDItemValueSetter.setValue(mdseItemSts.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseItemSts.mdseItemStsCd, mdse.mdseItemStsCd);
        return (MDSE_ITEM_STSTMsg) S21FastTBLAccessor.findByKey(mdseItemSts);
    }

    public static Map<String, Object> getOrdProcTpInfo(String glblCmpyCd, String dsOrdTpCd, String dsOrdLineCatgCd, String slsDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("dsOrdLineCatgCd", dsOrdLineCatgCd);
        ssmParam.put("slsDt", slsDt);
        return NWXC220001Query.getInstance().getOrdProcTpInfo(ssmParam);
    }

    public static BigDecimal getCostPctInfo(String glblCmpyCd, String rtlSwhCd, String slsDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlSwhCd", rtlSwhCd);
        ssmParam.put("slsDt", slsDt);

        BigDecimal costPct = (BigDecimal) NWXC220001Query.getInstance().getCostPctInfo(ssmParam);

        return costPct;
    }

    public static BigDecimal exchFuncUnitPrice(String glblCmpyCd, String slsDt, String ccyCd, BigDecimal dealAmt, BigDecimal defaultAmt) {

        BigDecimal funcAmt = defaultAmt;
        NWXC001001ExchangeData exchData = new NWXC001001ExchangeData();
        exchData.setGlblCmpyCd(glblCmpyCd);
        exchData.setSlsDt(slsDt);
        exchData.setCcyCd(ccyCd);
        List<NWXC001001ExchangePriceData> priceDataList = new ArrayList<NWXC001001ExchangePriceData>();
        NWXC220001ExchangeAmoutData exchAmtData = new NWXC220001ExchangeAmoutData();

        NWXC001001ExchangeAmount grsAmt = new NWXC001001ExchangeAmount();
        grsAmt.setDealAmt(dealAmt);
        exchAmtData.setGrsAmt(grsAmt);
        priceDataList.add(exchAmtData);
        exchData.setPriceData(priceDataList);

        NWXC001001Exchange.exchFuncUnitPrice(exchData);
        if (exchData.getXxMsgIdList().isEmpty()) {
            funcAmt = grsAmt.getFuncAmt();
        }
        return funcAmt;
    }

    public static Map<String, Object> getMdlIdNmBySvcConfigMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);

        return NWXC220001Query.getInstance().getMdlIdNmBySvcConfigMstr(ssmParam);
    }

    public static Map<String, Object> getSlsRepInfo(String glblCmpyCd, String slsRepTocNm, String slsRepTocCd, String slsRepPsnNum, String slsDt) {

        boolean doSearch = false;
        if (S21StringUtil.isNotEmpty(slsRepTocNm)) {

            doSearch = true;
        }
        if (S21StringUtil.isNotEmpty(slsRepTocCd)) {

            doSearch = true;
        }
        if (S21StringUtil.isNotEmpty(slsRepPsnNum)) {

            doSearch = true;
        }
        if (!doSearch) {

            return null;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsRepTocNm", slsRepTocNm);
        ssmParam.put("slsRepTocCd", slsRepTocCd);
        ssmParam.put("slsRepPsnNum", slsRepPsnNum);
        ssmParam.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        ssmParam.put("slsDt", slsDt);

        return NWXC220001Query.getInstance().getSlsRepInfo(ssmParam);
    }

    // S21_NA#15578 add start
    public static boolean isExistOrdCatg(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd, String dsOrdRsnCd, String ordCatgCtxTpCd, boolean isCatgOnly) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ordCatgCtxTpCd", ordCatgCtxTpCd);
        params.put("dsOrdCatgCd", dsOrdCatgCd);
        if (!isCatgOnly) {
            params.put("dsOrdTpCd", dsOrdTpCd);
            params.put("dsOrdRsnCd", dsOrdRsnCd);
        }

        return 0 < (Integer) NWXC220001Query.getInstance().isExistOrdCatg(params);
    }
    // S21_NA#15578 add end

}
