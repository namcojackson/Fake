/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC300001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.MDSETMsg;
import business.parts.AWZC100001PMsg;
import business.parts.AWZC135001PMsg;
import business.parts.NLZC300001PMsg;
import business.parts.NLZC300001_xxDetailListPMsg;
import business.parts.NWZC135001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC300001.constant.NLZC300001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC135001.NWZC135001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_XREF_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21IntBizApiProxy;
import com.canon.cusa.s21.framework.log.S21AbendException;


/**
 * <pre>
 * Inventory Reference API.
 *
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2013   CITS            T.Tokutomi      Create          N/A
 * 12/15/2015   CSAI            Y.Imazu         Update          QC#2001
 * 04/19/2016   CSAI            K.Lee           Update          QC#7096
 * 05/20/2016   CSAI            K.Lee           Update          QC#7441
 * 09/09/2016   CSAI            K.Lee           Update          QC#14455
 * 09/13/2016   CSAI            K.Lee           Update          QC#12022
 * 12/08/2016   CSAI            Y.Imazu         Update          QC#16505
 * 01/12/2018   CITS            K.Ogino         Update          QC#22405
 * 11/18/2020   CITS            J.Evangelista   Update          QC#57890
 * 03/01/2023   CITS            R.Kurahashi     Update          QC#61128
 * 09/04/2023   CITS            F.Komaki        Update          QC#61703
 * 10/03/2023   CITS            K.Ogino         Update          QC#61703
 *</pre>
 */
public class NLZC300001 extends S21ApiCommonBase implements NLZC300001Constant {

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    /** Message Item List of called API */
    private ArrayList<String> messageItemList;

    /** Message Item List of called API */
    private int errItemCount;

    /* 12/15/2015 CSAI Y.Imazu Add QC2001 START */
    private int errMsgListSize;
    /* 12/15/2015 CSAI Y.Imazu Add QC2001 END */

    /** CUSA Parent Vendor CodeI */
    private String cusaPrntVndCd;

    /** Default Canon Sell To Ship To Code **/
    private String defCanonSellToCode;

    /** Default Retail Warehouse Code **/
    private String defRtlWhCode;

    /* 12/08/2016 CSAI Y.Imazu Add QC#16505 START */
    /** Inventory Data Count **/
    int invtyDataCount;
    /* 12/08/2016 CSAI Y.Imazu Add QC#16505 END */

    /**
     * Initialize
     */
    public NLZC300001() {

        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        messageItemList = new ArrayList<String>();
        errItemCount = 0;
        /* 12/15/2015 CSAI Y.Imazu Add QC2001 START */
        errMsgListSize = 0;
        /* 12/15/2015 CSAI Y.Imazu Add QC2001 END */
        /* 12/08/2016 CSAI Y.Imazu Add QC#16505 START */
        invtyDataCount = 0;
        /* 12/08/2016 CSAI Y.Imazu Add QC#16505 END */
    }

    /**
     * <pre>
     * Query specified product inventory information.
     * </pre>
     * @param param NLZC300001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NLZC300001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = null;

        try {

            msgMap = new S21ApiMessageMap(param);

            /* 12/15/2015 CSAI Y.Imazu Add QC2001 START */
            errMsgListSize = param.xxMsgIdList.length();
            /* 12/15/2015 CSAI Y.Imazu Add QC2001 END */

            cusaPrntVndCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_NLZC3000_CUSA_PRNT_CMPY_CD, param.glblCmpyCd.getValue());
            defCanonSellToCode = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_NLZC300001_DEF_SELL_TO_CUST_CD, param.glblCmpyCd.getValue());
            defRtlWhCode = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_NLZC300001_DEF_RTL_WH_CD, param.glblCmpyCd.getValue());

            if (!ZYPCommonFunc.hasValue(cusaPrntVndCd)) {   
                setMsgItemList(NLZM2362E);
                return;
            }

            if (checkInputParameter(msgMap)) {
                return;
            }

            ArrayList<NLZC300001_xxDetailListPMsg> csaInvtyAvlbltyList = new ArrayList<NLZC300001_xxDetailListPMsg>();
            ArrayList<NLZC300001_xxDetailListPMsg> cusaInvtyAvlbltyList = new ArrayList<NLZC300001_xxDetailListPMsg>();
            ArrayList<NLZC300001_xxDetailListPMsg> cusaPartsInvtyAvlbltyList = new ArrayList<NLZC300001_xxDetailListPMsg>();
            ArrayList<NLZC300001_xxDetailListPMsg> cviInvtyAvlbltyList = new ArrayList<NLZC300001_xxDetailListPMsg>();
            // QC#61128 Add Start
            ArrayList<NLZC300001_xxDetailListPMsg> thirdPartyInvtyAvlbltyList = new ArrayList<NLZC300001_xxDetailListPMsg>();
            // QC#61128 Add End

            // CSA Inventory Availability
            if (ZYPConstant.FLG_ON_Y.equals(param.xxRqstFlg_01.getValue()) && invtyDataCount <= MAX_RECORD) { // 12/08/2016 CSAI Y.Imazu Mod QC#16505

                csaInvtyAvlbltyList = getCSAInventoryAvailability(msgMap);
            }

            // CUSA WS Inventory Availability
            if (ZYPConstant.FLG_ON_Y.equals(param.xxRqstFlg_02.getValue()) && invtyDataCount <= MAX_RECORD) { // 12/08/2016 CSAI Y.Imazu Mod QC#16505

                cusaInvtyAvlbltyList = getCusaWsInventory(msgMap);
            }

            // CUSA Parts Inventory Availability
            if (ZYPConstant.FLG_ON_Y.equals(param.xxRqstFlg_03.getValue()) && invtyDataCount <= MAX_RECORD) { // 12/08/2016 CSAI Y.Imazu Mod QC#16505

                cusaPartsInvtyAvlbltyList = getCusaPartsInventory(msgMap);
            }

            // CVI Inventory Availability
            if (ZYPConstant.FLG_ON_Y.equals(param.xxRqstFlg_04.getValue()) && invtyDataCount <= MAX_RECORD) { // 12/08/2016 CSAI Y.Imazu Mod QC#16505

                cviInvtyAvlbltyList = getCviInventory(msgMap);
            }
            
            // QC#61128 Add Start
            // Third Party Inventory Availability
            if (ZYPConstant.FLG_ON_Y.equals(param.xxRqstFlg_05.getValue()) && invtyDataCount <= MAX_RECORD) {

                thirdPartyInvtyAvlbltyList = getThirdPartyInventory(msgMap);
            }
            // QC#61128 Add End

            // QC#61128 Mod Start
            //outputResponse(csaInvtyAvlbltyList, cusaInvtyAvlbltyList, cusaPartsInvtyAvlbltyList, cviInvtyAvlbltyList, msgMap);
            outputResponse(csaInvtyAvlbltyList, cusaInvtyAvlbltyList, cusaPartsInvtyAvlbltyList, cviInvtyAvlbltyList, thirdPartyInvtyAvlbltyList, msgMap);
            // QC#61128 Mod End

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            msgMap.flush();
        }
    }

    /**
     * Get CSA Inventory Availability
     * @param msgMap S21ApiMessageMap
     * @return CSA Inventory ArrayList<NLZC300001_xxDetailListPMsg>
     */
    private ArrayList<NLZC300001_xxDetailListPMsg> getCSAInventoryAvailability(S21ApiMessageMap msgMap) {

        NLZC300001PMsg param = (NLZC300001PMsg) msgMap.getPmsg();

        ArrayList<NLZC300001_xxDetailListPMsg> csaInvtyAvlbltyList = new ArrayList<NLZC300001_xxDetailListPMsg>();

        paramList : for (int i = 0; i < param.xxDetailList.getValidCount(); i++) { // 12/08/2016 CSAI Y.Imazu Mod QC#16505

            List<Map<String, Object>> itemStockList = getItemStockFromInvty(param.glblCmpyCd.getValue() //
                    , param.xxDetailList.no(i).mdseCd.getValue(), //
                    param.xxDetailList.no(i).invtyLocCd.getValue(), //
                    param.xxDetailList.no(i).rtlWhCd.getValue(), //
                    param.xxDetailList.no(i).rtlSwhCd.getValue(), //
                    param.xxRqstFlg_WH.getValue(), //
                    param.xxRqstFlg_TC.getValue());

            // set out put param
            for (Map<String, Object> invty : itemStockList) {

                NLZC300001_xxDetailListPMsg outParam = new NLZC300001_xxDetailListPMsg();

                ZYPEZDItemValueSetter.setValue(outParam.invtyLocCd, (String) invty.get(INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(outParam.mdseCd, (String) invty.get(MDSE_CD));
                ZYPEZDItemValueSetter.setValue(outParam.ordQty, param.xxDetailList.no(i).ordQty);
                ZYPEZDItemValueSetter.setValue(outParam.locNm, (String) invty.get(RTL_WH_NM));
                ZYPEZDItemValueSetter.setValue(outParam.rtlWhCd, (String) invty.get(RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(outParam.rtlSwhCd, (String) invty.get(RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(outParam.locTpCd, (String) invty.get(LOC_TP_CD));
                ZYPEZDItemValueSetter.setValue(outParam.xxRsltFlg_EX, ZYPConstant.FLG_OFF_N);

                if ((BigDecimal) invty.get(INVTY_AVAL_QTY) == null || ((BigDecimal) invty.get(INVTY_AVAL_QTY)).equals(BigDecimal.ZERO)) {

                    ZYPEZDItemValueSetter.setValue(outParam.xxAvalQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(outParam.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);

                } else {

                    // 2023/09/04 QC#61703 START
                    BigDecimal avalQty = (BigDecimal) invty.get(INVTY_AVAL_QTY);
                    if (ZYPCommonFunc.hasValue(param.xxDetailList.no(i).allocQty) && BigDecimal.ZERO.compareTo(avalQty.subtract(param.xxDetailList.no(i).allocQty.getValue())) > 0) {
                        ZYPEZDItemValueSetter.setValue(outParam.xxAvalQty, BigDecimal.ZERO);
                    } else if (ZYPCommonFunc.hasValue(param.xxDetailList.no(i).allocQty)) {
                        ZYPEZDItemValueSetter.setValue(outParam.xxAvalQty, avalQty.subtract(param.xxDetailList.no(i).allocQty.getValue()));
                    } else {
                        ZYPEZDItemValueSetter.setValue(outParam.xxAvalQty, avalQty);
                    }
                    // 2023/09/04 QC#61703 END

                    // 11/05/2015 mod start
                    // ZYPEZDItemValueSetter.setValue(outParam.xxAvalOrdFlg, ZYPConstant.FLG_ON_Y);

                    //QC#14455 Start
                    if (!ZYPCommonFunc.hasValue(param.xxDetailList.no(i).ordQty)) {

                        ZYPEZDItemValueSetter.setValue(param.xxDetailList.no(i).ordQty, BigDecimal.ONE);
                    }
                    //QC#14455 End

                    // 2023/09/04 QC#61703 START
                    if (param.xxDetailList.no(i).ordQty.getValue().compareTo(outParam.xxAvalQty.getValue()) > 0) {
                    // if (param.xxDetailList.no(i).ordQty.getValue().compareTo((BigDecimal) invty.get(INVTY_AVAL_QTY)) > 0) {
                    // 2023/09/04 QC#61703 END

                        ZYPEZDItemValueSetter.setValue(outParam.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);

                    } else {

                        ZYPEZDItemValueSetter.setValue(outParam.xxAvalOrdFlg, ZYPConstant.FLG_ON_Y);
                    }
                    // 11/05/2015 mod end
                }

                // copy Input param
                ZYPEZDItemValueSetter.setValue(outParam.shipToCustCd, param.xxDetailList.no(i).shipToCustCd);
                ZYPEZDItemValueSetter.setValue(outParam.rsdDt, param.xxDetailList.no(i).rsdDt);
                ZYPEZDItemValueSetter.setValue(outParam.rddDt, param.xxDetailList.no(i).rddDt);

                csaInvtyAvlbltyList.add(outParam);

                /* 12/08/2016 CSAI Y.Imazu Add QC#16505 START */
                invtyDataCount++;

                if (invtyDataCount > MAX_RECORD) {

                    break paramList;
                }
                /* 12/08/2016 CSAI Y.Imazu Add QC#16505 END */
            }
        }

        Map<String, Map<String, Object>> shipToLocMap = new HashMap<String, Map<String, Object>>(); 
        List<NWZC135001PMsg> apiParamList = new ArrayList<NWZC135001PMsg>();

        int seqNum = 0;
        // ATP Inquiry by Item
        for (NLZC300001_xxDetailListPMsg detail : csaInvtyAvlbltyList) {

            if (hasValue(detail.shipToCustCd)) {

                Map<String, Object> shipToLoc = null;

                if (shipToLocMap.containsKey(detail.shipToCustCd.getValue())) {

                    shipToLoc = shipToLocMap.get(detail.shipToCustCd.getValue());

                } else {

                    shipToLoc = getShipToLocation(param.glblCmpyCd.getValue(), detail.shipToCustCd.getValue());
                    shipToLocMap.put(detail.shipToCustCd.getValue(), shipToLoc);
                }

                if (shipToLoc != null) {

                    // Parameter of ATP Inquiry by Item API
                    NWZC135001PMsg apiParam = new NWZC135001PMsg();

                    // set param
                    ZYPEZDItemValueSetter.setValue(apiParam.glblCmpyCd, param.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(apiParam.batDt, param.procDt);
                    ZYPEZDItemValueSetter.setValue(apiParam.startSeqNum, String.valueOf(seqNum));
                    ZYPEZDItemValueSetter.setValue(apiParam.invtyLocCd, detail.invtyLocCd);
                    ZYPEZDItemValueSetter.setValue(apiParam.mdseCd, detail.mdseCd);
                    ZYPEZDItemValueSetter.setValue(apiParam.ordQty, detail.ordQty);
                    ZYPEZDItemValueSetter.setValue(apiParam.rddDt, detail.rddDt);
                    ZYPEZDItemValueSetter.setValue(apiParam.rsdDt, detail.rsdDt);
                    ZYPEZDItemValueSetter.setValue(apiParam.shipToCustCd, detail.shipToCustCd);

                    if (shipToLoc.get(BILL_TO_CUST_CD) != null) {

                        ZYPEZDItemValueSetter.setValue(apiParam.billToCustCd, (String) shipToLoc.get(BILL_TO_CUST_CD));
                    }

                    if (shipToLoc.get(SELL_TO_CUST_CD) != null) {

                        ZYPEZDItemValueSetter.setValue(apiParam.sellToCustCd, (String) shipToLoc.get(SELL_TO_CUST_CD));
                    }

                    if (shipToLoc.get(ST_CD) != null) {

                        ZYPEZDItemValueSetter.setValue(apiParam.shipToStCd, (String) shipToLoc.get(ST_CD));
                    }

                    if (shipToLoc.get(POST_CD) != null) {

                        ZYPEZDItemValueSetter.setValue(apiParam.toZipCd, (String) shipToLoc.get(POST_CD));
                    }

                    apiParamList.add(apiParam);

                } else {

                    detail.xxWhInEtaDtTxt.clear();
                }

            } else {

                detail.xxWhInEtaDtTxt.clear();
            }

            seqNum++;
        }

        // Call ATP Inquiry by Item API
        NWZC135001 api = new NWZC135001();
        api.execute(apiParamList, S21ApiInterface.ONBATCH_TYPE.BATCH);

        for (NWZC135001PMsg apiParam : apiParamList) {

            if (!S21ApiUtil.isXxMsgId(apiParam)) {

                int seqNo = Integer.parseInt(apiParam.OrderList.no(0).endSeqNum.getValue());
                NLZC300001_xxDetailListPMsg detail = csaInvtyAvlbltyList.get(seqNo);
                // success.
                ZYPEZDItemValueSetter.setValue(detail.xxWhInEtaDtTxt, apiParam.OrderList.no(0).xxEtaDtTxt);

            } else {

                List<String> msgList = S21ApiUtil.getXxMsgIdList(apiParam);

                for (String msg : msgList) {
                    /* 12/15/2015 CSAI Y.Imazu Mod QC2001 START */
                    // msgMap.addXxMsgId(msg);
                    // errItemCount++;
                    setMsgItemList(msg);
                    /* 12/15/2015 CSAI Y.Imazu Mod QC2001 END */
                }
            }
        }

        return csaInvtyAvlbltyList;
    }

    /**
     * Get Item stock list from Inventory table.
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param invtyLocCd String
     * @return Item Stock List Map<String, Object>
     */
    private List<Map<String, Object>> getItemStockFromInvty(String glblCmpyCd, String mdseCd, String invtyLocCd, String rtlWhCd, String rtlSwhCd, String whFlg, String techFlg) {

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        queryParams.put(BIND_MDSE_CD, mdseCd);
        queryParams.put(BIND_INVTY_LOC_CD, invtyLocCd);
        queryParams.put(BIND_RTL_WH_CD, rtlWhCd);
        queryParams.put(BIND_RTL_SWH_CD, rtlSwhCd);
        queryParams.put(BIND_LOC_STS_CD, LOC_STS.DC_STOCK);
        queryParams.put(BIND_STK_STS_CD, STK_STS.GOOD);

        String excludeInvtyOwnr = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_NLZC3000_EXCLUDE_OWNR_LIST, glblCmpyCd);

        if (ZYPCommonFunc.hasValue(excludeInvtyOwnr)) {

            String[] excludeInvtyOwnrArray = excludeInvtyOwnr.split(",");
            queryParams.put(BIND_EXCLUDE_INVTY_OWNR_LIST, excludeInvtyOwnrArray);
        }

        List<String> locTpList = new ArrayList<String>();

        if (!whFlg.equals(ZYPConstant.FLG_OFF_N)) {

            locTpList.add(LOC_TP.WAREHOUSE);
        }

        if (!techFlg.equals(ZYPConstant.FLG_OFF_N)) {

            locTpList.add(LOC_TP.TECHNICIAN);
        }

        queryParams.put(BIND_LOC_TP_LIST, locTpList.toArray(new String[locTpList.size()]));

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getItemStockFromInvty", queryParams);
    }

    /**
     * Get Ship to Location Info.
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return Ship to Location Info Map<String, Object>
     */
    private Map<String, Object> getShipToLocation(String glblCmpyCd, String shipToCustCd) {

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        queryParams.put(BIND_SHIP_TO_CUST_CD, shipToCustCd);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getShipToLocation", queryParams);
    }

    /**
     * Get CUSA WS Inventory Availability.
     * @param msgMap
     * @return CUSA WS Inventory
     * ArrayList<NLZC300001_xxDetailListPMsg>
     */
    private ArrayList<NLZC300001_xxDetailListPMsg> getCusaWsInventory(S21ApiMessageMap msgMap) {

        NLZC300001PMsg param = (NLZC300001PMsg) msgMap.getPmsg();

        ArrayList<NLZC300001_xxDetailListPMsg> cusaWsInvtyAvlList = new ArrayList<NLZC300001_xxDetailListPMsg>();

        paramList : for (int i = 0; i < param.xxDetailList.getValidCount(); i++) { // 12/08/2016 CSAI Y.Imazu Add QC#16505

            List<Map<String, Object>> cusaInvtyLocCdList = new ArrayList<Map<String,Object>>();

            try {

                if (ZYPCommonFunc.hasValue(param.xxDetailList.no(i).invtyLocCd)) {

                    Map<String, Object> cusaInvtyLocMap = getCusaInvtyLocMap(param.xxDetailList.no(i).invtyLocCd.getValue());

                    if (cusaInvtyLocMap != null) {

                        Map<String, Object> mapInvtyLocCd = new HashMap<String, Object>();
                        mapInvtyLocCd.put("INVTY_LOC_CD", (String) cusaInvtyLocMap.get(INVTY_LOC_CD));
                        mapInvtyLocCd.put("INVTY_LOC_NM", (String) cusaInvtyLocMap.get(INVTY_LOC_NM));
                        cusaInvtyLocCdList.add(mapInvtyLocCd);

                    } else {

                        /* 12/15/2015 CSAI Y.Imazu Mod QC2001 START */
                        // msgMap.addXxMsgId(NLZM2075E);
                        // errItemCount++;
                        setMsgItemList(NLZM2075E);
                        /* 12/15/2015 CSAI Y.Imazu Mod QC2001 END */
                        continue;
                    }

                } else {

                    cusaInvtyLocCdList = getCusaInvtyLocCdList(param.glblCmpyCd.getValue());
                }

            } catch (S21AbendException e) {

                /* 12/15/2015 CSAI Y.Imazu Mod QC2001 START */
                // messageItemList.add(ZZBM0065E);
                setMsgItemList(ZZBM0065E);
                /* 12/15/2015 CSAI Y.Imazu Mod QC2001 END */
                return new ArrayList<NLZC300001_xxDetailListPMsg>();
            }
            // 10/23/2015 mod end

            Map<String, String> sellToShipToMap = getCanonSellToShipToCode(param.glblCmpyCd.getValue(), param.xxDetailList.no(i).shipToCustCd.getValue(), param.xxDetailList.no(i).techTocCd.getValue(), param.xxDetailList.no(i).mdseCd.getValue(), param.procDt.getValue());
            ArrayList<NLZC300001_xxDetailListPMsg> cusaWsList = callCusaWsApi(param.glblCmpyCd.getValue(), param.procDt.getValue(), param.xxDetailList.no(i), msgMap, cusaInvtyLocCdList, sellToShipToMap);

            BigDecimal xxAvalQty = param.xxDetailList.no(i).xxAvalQty.getValue();

            if (!ZYPCommonFunc.hasValue(xxAvalQty)) {

                xxAvalQty = BigDecimal.ZERO;
            }

            BigDecimal ordQty = param.xxDetailList.no(i).ordQty.getValue();

            if (!ZYPCommonFunc.hasValue(ordQty)) {

                ordQty = BigDecimal.ZERO;
            }

            BigDecimal diffQty = ordQty.subtract(xxAvalQty);

            if (hasValue(param.xxDetailList.no(i).shipToCustCd) && diffQty.compareTo(BigDecimal.ZERO) > 0) {

                int seqNo = 0;
                List<AWZC135001PMsg> apiParamList = new ArrayList<AWZC135001PMsg>();

                // get CUSA Ship to / Sell to
                for (NLZC300001_xxDetailListPMsg detail : cusaWsList) {

                    if (sellToShipToMap != null && sellToShipToMap.get(SHIP_TO_CUST_CD) != null) {

                        // Set API Parameter
                        AWZC135001PMsg apiParam = new AWZC135001PMsg();

                        ZYPEZDItemValueSetter.setValue(apiParam.glblCmpyCd, GLBL_CMPY_CD_CUSA);
                        ZYPEZDItemValueSetter.setValue(apiParam.batDt, param.procDt.getValue());
                        ZYPEZDItemValueSetter.setValue(apiParam.startSeqNum, String.valueOf(seqNo));
                        ZYPEZDItemValueSetter.setValue(apiParam.invtyLocCd, detail.invtyLocCd);
                        ZYPEZDItemValueSetter.setValue(apiParam.mdseCd, detail.mdseCd);
                        ZYPEZDItemValueSetter.setValue(apiParam.ordQty, diffQty);
                        ZYPEZDItemValueSetter.setValue(apiParam.rddDt, detail.rddDt);
                        ZYPEZDItemValueSetter.setValue(apiParam.rsdDt, detail.rsdDt);
                        ZYPEZDItemValueSetter.setValue(apiParam.billToCustCd, sellToShipToMap.get(BILL_TO_CUST_CD));
                        ZYPEZDItemValueSetter.setValue(apiParam.sellToCustCd, sellToShipToMap.get(SELL_TO_CUST_CD));
                        ZYPEZDItemValueSetter.setValue(apiParam.shipToCustCd, sellToShipToMap.get(SHIP_TO_CUST_CD));
                        ZYPEZDItemValueSetter.setValue(apiParam.shipToStCd, sellToShipToMap.get(ST_CD));
                        ZYPEZDItemValueSetter.setValue(apiParam.toZipCd, sellToShipToMap.get(POST_CD));
                        apiParamList.add(apiParam);
                    }

                    seqNo++;
                }

                if (apiParamList.size() > 0) {
                    S21IntBizApiProxy api = new S21IntBizApiProxy(ITRL_INTFC_ID_WS);

                    try {

                        api.execute(API_ID_WS_ATP, INTERFACE_ID_FOR_WS_API, apiParamList);

                        for (AWZC135001PMsg apiParam : apiParamList) {

                            if (!S21ApiUtil.isXxMsgId(apiParam)) {

                                NLZC300001_xxDetailListPMsg detail = cusaWsList.get(seqNo);
                                ZYPEZDItemValueSetter.setValue(detail.xxWhInEtaDtTxt, apiParam.OrderList.no(0).xxEtaDtTxt);

                            } else {

                                List<String> msgList = S21ApiUtil.getXxMsgIdList(apiParam);

                                for (String msg : msgList) {

                                    String msgId = convertMsgIdFromCusaWsToWds(msg);
                                    /* 12/15/2015 CSAI Y.Imazu Mod QC2001 START */
                                    // msgMap.addXxMsgId(msgId);
                                    // errItemCount++;
                                    setMsgItemList(msgId);
                                    /* 12/15/2015 CSAI Y.Imazu Mod QC2001 END */
                                }
                            }

                        }

                    } catch (S21AbendException e) {

                        /* 12/15/2015 CSAI Y.Imazu Mod QC2001 START */
                        // messageItemList.add(NLZM2057E);
                        setMsgItemList(NLZM2057E);
                        // QC#22405
                        if (ZYPCommonFunc.hasValue(param.xxRsltFlg_WS) && ZYPConstant.FLG_ON_Y.endsWith(param.xxRsltFlg_WS.getValue())) {
                            break;
                        }
                        /* 12/15/2015 CSAI Y.Imazu Mod QC2001 END */
                    }
                }
            }

            for (NLZC300001_xxDetailListPMsg detail : cusaWsList) {

                cusaWsInvtyAvlList.add(detail);

                /* 12/08/2016 CSAI Y.Imazu Add QC#16505 START */
                invtyDataCount++;

                if (invtyDataCount > MAX_RECORD) {

                    break paramList;
                }
                /* 12/08/2016 CSAI Y.Imazu Add QC#16505 END */
            }

        }

        return cusaWsInvtyAvlList;
    }

    /**
     * Call CUSA WS API(AWZC1000 Inventory Availability Inquiry for
     * CBS API)
     * @param glblCmpyCd String
     * @param procDt String
     * @param detail NLZC300001_xxDetailListPMsg
     * @param msgMap S21ApiMessageMap
     * @param cusaInvtyLocCdList List<Map<String, Object>>
     * @return boolean
     */
    private ArrayList<NLZC300001_xxDetailListPMsg> callCusaWsApi(String glblCmpyCd, String procDt, NLZC300001_xxDetailListPMsg detail, S21ApiMessageMap msgMap, List<Map<String, Object>> cusaInvtyLocCdList, Map<String,String> sellToShipToMap) {

        // QC#22405
        NLZC300001PMsg param = (NLZC300001PMsg) msgMap.getPmsg();

        // return data
        ArrayList<NLZC300001_xxDetailListPMsg> resultList = new ArrayList<NLZC300001_xxDetailListPMsg>();

        for (int i = 0; i < cusaInvtyLocCdList.size(); i++) {

            String invtyLocCd = (String) cusaInvtyLocCdList.get(i).get(INVTY_LOC_CD);
            String invtyLocNm = (String) cusaInvtyLocCdList.get(i).get(INVTY_LOC_NM);

            // Set API Parameter
            AWZC100001PMsg apiParam = new AWZC100001PMsg();

            ZYPEZDItemValueSetter.setValue(apiParam.glblCmpyCd, GLBL_CMPY_CD_CUSA);
            ZYPEZDItemValueSetter.setValue(apiParam.procDt, procDt);
            ZYPEZDItemValueSetter.setValue(apiParam.whCd, invtyLocCd);
            ZYPEZDItemValueSetter.setValue(apiParam.shipDt, detail.rsdDt);
            ZYPEZDItemValueSetter.setValue(apiParam.sellToCustCd, sellToShipToMap.get("SELL_TO_CUST_CD"));

            // Add 1 request item info
            apiParam.RequestItemInfo.setValidCount(1);
            String splyItemNum = getSplyItemNum(glblCmpyCd, cusaPrntVndCd, detail.mdseCd.getValue());

            if (ZYPCommonFunc.hasValue(splyItemNum)) {

                ZYPEZDItemValueSetter.setValue(apiParam.RequestItemInfo.no(0).mdseCd_IN, splyItemNum);

            } else {

                ZYPEZDItemValueSetter.setValue(apiParam.RequestItemInfo.no(0).mdseCd_IN, detail.mdseCd);
            }

            if (!ZYPCommonFunc.hasValue(detail.ordQty)) {

                ZYPEZDItemValueSetter.setValue(detail.ordQty, BigDecimal.ONE);
            }

            ZYPEZDItemValueSetter.setValue(apiParam.RequestItemInfo.no(0).xxQty10Num, detail.ordQty);

            // Call API
            S21IntBizApiProxy api = new S21IntBizApiProxy(ITRL_INTFC_ID_WS);

            try {

                api.execute(API_ID_WS, INTERFACE_ID_FOR_WS_API, apiParam);

                if (!S21ApiUtil.isXxMsgId(apiParam)) {

                    for (int j = 0; j < apiParam.ResponseItemInfo.getValidCount(); j++) {

                        NLZC300001_xxDetailListPMsg result = new NLZC300001_xxDetailListPMsg();

                        ZYPEZDItemValueSetter.setValue(result.invtyLocCd, invtyLocCd);
                        ZYPEZDItemValueSetter.setValue(result.locNm, invtyLocNm);
                        ZYPEZDItemValueSetter.setValue(result.mdseCd, detail.mdseCd);
                        ZYPEZDItemValueSetter.setValue(result.ordQty, detail.ordQty);
                        ZYPEZDItemValueSetter.setValue(result.xxAvalQty, apiParam.ResponseItemInfo.no(j).xxQty10Num);

                        if (apiParam.ResponseItemInfo.no(j).xxAvalQtyStsTxt.getValue().equals(AVAL_QTY_STS_TXT_ENOUGH)) {

                            ZYPEZDItemValueSetter.setValue(result.xxAvalOrdFlg, ZYPConstant.FLG_ON_Y);

                        } else {

                            ZYPEZDItemValueSetter.setValue(result.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
                        }

                        ZYPEZDItemValueSetter.setValue(result.xxRsltFlg_EX, ZYPConstant.FLG_ON_Y);

                        resultList.add(result);
                    }

                } else {

                    List<String> msgList = S21ApiUtil.getXxMsgIdList(apiParam);

                    for (String msg : msgList) {

                        String msgId = convertMsgIdFromCusaWsToWds(msg);
                        /* 12/15/2015 CSAI Y.Imazu Mod QC2001 START */
                        // msgMap.addXxMsgId(msgId);
                        // errItemCount++;
                        setMsgItemList(msgId);
                        /* 12/15/2015 CSAI Y.Imazu Mod QC2001 END */

                        // QC#18418 T.Hakodate ADD START
                        if (msgId.equals("NLZM2077E")) {
                            return resultList;
                        }
                        // QC#18418 T.Hakodate ADD START
                    }
                }

            } catch (S21AbendException e) {

                /* 12/15/2015 CSAI Y.Imazu Mod QC2001 START */
                // messageItemList.add(NLZM2057E);
                setMsgItemList(NLZM2057E);
                // QC#22405
                if (ZYPCommonFunc.hasValue(param.xxRsltFlg_WS) && ZYPConstant.FLG_ON_Y.endsWith(param.xxRsltFlg_WS.getValue())) {
                    break;
                }
                /* 12/15/2015 CSAI Y.Imazu Mod QC2001 END */
            }
        }
        // 10/23/2015 mod end

        return resultList;
    }

    //  ATP Inquiry(AWZC135001) 2015.10.13
    /**
     * Get Ship to Info from CUSA.
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return Ship to Location Info Map<String, String>
     */
    private Map<String, String> getShipToCustFromCUSA(String glblCmpyCd, String shipToCustCd) {

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(BIND_CUSA_CMPY_CD, GLBL_CMPY_CD_CUSA);
        queryParams.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        queryParams.put(BIND_INVTY_LOC_CD, shipToCustCd);
        queryParams.put(BIND_VND_XREF_TP_CD, VND_XREF_TP.CSA_TO_CUSA); // CSA=>CUSA
        queryParams.put(BIND_VND_SYS_TP_CD, "W"); // CUSA WS

        return (Map<String, String>) this.ssmBatchClient.queryObject("getShipToCustFromCUSA", queryParams);
    }

    /**
     * Convert CUSA WS Message Id to WDS Message Id
     * @param String msgId CUSA WS Message Id
     */
    private String convertMsgIdFromCusaWsToWds(String msgId) {

        if (WS_AWZM0188E.equals(msgId)) {

            return NLZM2052E;

        } else if (WS_AWZM0301E.equals(msgId)) {

            return NLZM2053E;

        } else if (WS_AWZM0302E.equals(msgId)) {

            return NLZM2054E;

        } else if (WS_AWZM0303E.equals(msgId)) {

            return NLZM2064E;

        } else if (WS_AWZM0304E.equals(msgId)) {

            return NLZM2065E;

        } else if (WS_AWZM0305E.equals(msgId)) {

            return NLZM2074E;

        } else if (WS_AWZM0306E.equals(msgId)) {

            return NLZM2066E;

        } else if (WS_AWZM0307E.equals(msgId)) {

            return NLZM2067E;

        } else if (WS_AWZM0308E.equals(msgId)) {

            return NLZM2068E;

        } else if (WS_AWZM0309E.equals(msgId)) {

            return NLZM2076E;

        } else if (WS_AWZM0036E.equals(msgId)) {

            return NLZM2077E;

        } else if (WS_AWZM0610E.equals(msgId)) {

            return NLZM2069E;

        } else if (WS_AWZM0775E.equals(msgId)) {

            return NLZM2070E;

        } else if (WS_AWZM0785E.equals(msgId)) {

            return NLZM2071E;

        } else if (WS_AZZM0007E.equals(msgId)) {

            return NLZM2072E;

        } else if (WS_AWZM0199E.equals(msgId)) {

            return NLZM2073E;

        } else {

            return NLZM2062E;
        }
    }

    /**
     * Get CUSA Parts Inventory Availability.
     * @param msgMap S21ApiMessageMap
     * @return cusa parts Inventory
     * ArrayList<NLZC300001_xxDetailListPMsg>
     */
    private ArrayList<NLZC300001_xxDetailListPMsg> getCusaPartsInventory(S21ApiMessageMap msgMap) {

        NLZC300001PMsg param = (NLZC300001PMsg) msgMap.getPmsg();

        ArrayList<NLZC300001_xxDetailListPMsg> cusaPartsInvtyAvlList = new ArrayList<NLZC300001_xxDetailListPMsg>();

        paramList : for (int i = 0; i < param.xxDetailList.getValidCount(); i++) { // 12/08/2016 CSAI Y.Imazu Mod QC#16505

            // parameter check
            // 10/23/2015 mod start
//            if (!hasValue(param.xxDetailList.no(i).invtyLocCd)) {
//                msgMap.addXxMsgId(NLXM1032E);
//                continue;
//            }

            List<Map<String, Object>> cusaPartsList = new ArrayList<Map<String, Object>>();

            try {

                if (ZYPCommonFunc.hasValue(param.xxDetailList.no(i).invtyLocCd)) {

                    if (!checkCusaPartsInvtyLocCd(param.xxDetailList.no(i).invtyLocCd.getValue())) {

                        /* 12/15/2015 CSAI Y.Imazu Mod QC2001 START */
                        // msgMap.addXxMsgId(NLZM2075E);
                        // errItemCount++;
                        setMsgItemList(NLZM2075E);
                        /* 12/15/2015 CSAI Y.Imazu Mod QC2001 END */
                        continue;
                    }
                }

                String splyPartsNum = getSplyItemNum(param.glblCmpyCd.getValue(), cusaPrntVndCd, param.xxDetailList.no(i).mdseCd.getValue());

                if (!ZYPCommonFunc.hasValue(splyPartsNum)) {

                    splyPartsNum = param.xxDetailList.no(i).mdseCd.getValue();
                }

                // get cusa parts sql
                cusaPartsList = getCusaPartsInvty(param.glblCmpyCd.getValue(), param.xxDetailList.no(i).invtyLocCd.getValue(), splyPartsNum);

            } catch (S21AbendException e) {

                /* 12/15/2015 CSAI Y.Imazu Mod QC2001 START */
                // messageItemList.add(ZZBM0065E);
                setMsgItemList(ZZBM0065E);
                /* 12/15/2015 CSAI Y.Imazu Mod QC2001 END */
                return new ArrayList<NLZC300001_xxDetailListPMsg>();
            }
            // 10/23/2015 mod end

            // create cusa parts list.
            // 11/05/2015 add start
            String currWhCd = "";
            String prevWhCd = "";
            boolean isCurrWhCdCompleted = false;
            BigDecimal subTotPositionQty  = BigDecimal.ZERO;
            NLZC300001_xxDetailListPMsg cusaPartInvty = new NLZC300001_xxDetailListPMsg();
            int iListCnt = 0;
            // 11/05/2015 add end

            for (Map<String, Object> partInvty : cusaPartsList) {

                currWhCd = (String) partInvty.get(P_CODE_WH);

                if (!currWhCd.equals(prevWhCd)) {

                    isCurrWhCdCompleted = false;
                    subTotPositionQty = BigDecimal.ZERO;
                    cusaPartInvty = new NLZC300001_xxDetailListPMsg();

                    if (partInvty.get(P_CODE_WH) != null) {

                        ZYPEZDItemValueSetter.setValue(cusaPartInvty.invtyLocCd, (String) partInvty.get(P_CODE_WH));
                        ZYPEZDItemValueSetter.setValue(cusaPartInvty.locNm, (String) partInvty.get(P_NAME_WH));
                    }

                    if (partInvty.get(P_PARTS_NUM) != null) {

                        ZYPEZDItemValueSetter.setValue(cusaPartInvty.mdseCd, (String) partInvty.get(P_PARTS_NUM));
                    }

                    //QC#14455 Start
                    if (!ZYPCommonFunc.hasValue(param.xxDetailList.no(i).ordQty)) {
                        ZYPEZDItemValueSetter.setValue(param.xxDetailList.no(i).ordQty, BigDecimal.ONE);
                    }
                    //QC#14455 End

                    if (param.xxDetailList.no(i).ordQty.getValue().compareTo((BigDecimal) partInvty.get(P_QTY_ALLOCBL)) > 0) {

                        ZYPEZDItemValueSetter.setValue(cusaPartInvty.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);

                    } else {

                        ZYPEZDItemValueSetter.setValue(cusaPartInvty.xxAvalOrdFlg, ZYPConstant.FLG_ON_Y);
                    }

                    ZYPEZDItemValueSetter.setValue(cusaPartInvty.xxAvalQty, (BigDecimal) partInvty.get(P_QTY_ALLOCBL));
                    // copy parameter.
                    ZYPEZDItemValueSetter.setValue(cusaPartInvty.ordQty, param.xxDetailList.no(i).ordQty);
                    ZYPEZDItemValueSetter.setValue(cusaPartInvty.rsdDt, param.xxDetailList.no(i).rsdDt);
                    ZYPEZDItemValueSetter.setValue(cusaPartInvty.rddDt, param.xxDetailList.no(i).rddDt);
                    ZYPEZDItemValueSetter.setValue(cusaPartInvty.shipToCustCd, param.xxDetailList.no(i).shipToCustCd);
                    ZYPEZDItemValueSetter.setValue(cusaPartInvty.xxRsltFlg_EX, ZYPConstant.FLG_ON_Y);

                    if (param.xxDetailList.no(i).ordQty.getValue().compareTo((BigDecimal) partInvty.get(P_QTY_ALLOCBL)) > 0) {

                        // Not Available
                        cusaPartsInvtyAvlList.add(cusaPartInvty);
                        isCurrWhCdCompleted = true;
                        cusaPartInvty = new NLZC300001_xxDetailListPMsg();

                        /* 12/08/2016 CSAI Y.Imazu Add QC#16505 START */
                        invtyDataCount++;

                        if (invtyDataCount > MAX_RECORD) {

                            break paramList;
                        }
                        /* 12/08/2016 CSAI Y.Imazu Add QC#16505 END */
                    }
                }

                //QC#14455 Start
                if (!ZYPCommonFunc.hasValue(param.xxDetailList.no(i).ordQty)) {

                    ZYPEZDItemValueSetter.setValue(param.xxDetailList.no(i).ordQty, BigDecimal.ONE);
                }

                //QC#14455 End
                if (param.xxDetailList.no(i).ordQty.getValue().compareTo((BigDecimal) partInvty.get(P_QTY_ALLOCBL)) <= 0) {

                    // Available
                    // START 2020/11/18 J.Evangelista [QC#57890,DEL]
//                    if (!ZYPCommonFunc.hasValue(cusaPartInvty.xxWhInEtaDtTxt)) {
                    // END   2020/11/18 J.Evangelista [QC#57890,DEL]

                        subTotPositionQty = subTotPositionQty.add((BigDecimal) partInvty.get(P_QTY_POSITION));

                        // START 2020/11/18 J.Evangelista [QC#57890,MOD]
//                        if (subTotPositionQty.compareTo(param.xxDetailList.no(i).ordQty.getValue()) >= 0 ) {
                        if (subTotPositionQty.compareTo(param.xxDetailList.no(i).ordQty.getValue()) >= 0
                                || ZYPConstant.FLG_ON_Y.equals(cusaPartInvty.xxAvalOrdFlg.getValue())) {
                        // END   2020/11/18 J.Evangelista [QC#57890,MOD]

                            ZYPEZDItemValueSetter.setValue(cusaPartInvty.xxWhInEtaDtTxt, (String) partInvty.get(P_DATE_STOCK_IN_EST));
                            cusaPartsInvtyAvlList.add(cusaPartInvty);
                            isCurrWhCdCompleted = true;
                            cusaPartInvty = new NLZC300001_xxDetailListPMsg();

                            /* 12/08/2016 CSAI Y.Imazu Add QC#16505 START */
                            invtyDataCount++;

                            if (invtyDataCount > MAX_RECORD) {

                                break paramList;
                            }
                            /* 12/08/2016 CSAI Y.Imazu Add QC#16505 END */
                        }
                    // START 2020/11/18 J.Evangelista [QC#57890,DEL]
//                    }
                    // END   2020/11/18 J.Evangelista [QC#57890,DEL]
                }

                // Last record
                if (iListCnt == (cusaPartsList.size()- 1) && !isCurrWhCdCompleted) {

                    cusaPartsInvtyAvlList.add(cusaPartInvty);

                    /* 12/08/2016 CSAI Y.Imazu Add QC#16505 START */
                    invtyDataCount++;

                    if (invtyDataCount > MAX_RECORD) {

                        break paramList;
                    }
                    /* 12/08/2016 CSAI Y.Imazu Add QC#16505 END */
                }

                prevWhCd = (String)partInvty.get(P_CODE_WH);
                iListCnt++;
                // 11/05/2015 mod end
            }
        }

        return cusaPartsInvtyAvlList;
    }

    /**
     * Get CUSA Parts Inventory.
     * @param invtyLocCd String
     * @param mdseCd String
     * @return CUSA Parts Inventory List<Map<String,Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getCusaPartsInvty(String glblCmpyCd, String invtyLocCd, String mdseCd) {

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(BIND_P_CODE_WH, invtyLocCd);
        queryParams.put(BIND_P_PARTS_NUM, mdseCd);
        String avalLocCd = ZYPCodeDataUtil.getVarCharConstValue("NLZC3000_PARTS_LOC_LIST", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(avalLocCd)) {
            String[] avalLocCdArray = avalLocCd.split(",");
            queryParams.put(BIND_AVAL_LOC_CD_ARRAY, avalLocCdArray);
        }

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCusaPartsInvty", queryParams);
    }

    /**
     * Get CVI Inventory Availability.
     * @param msgMap S21ApiMessageMap
     * @return CVI Inventory Availability
     * ArrayList<NLZC300001_xxDetailListPMsg>
     */
    private ArrayList<NLZC300001_xxDetailListPMsg> getCviInventory(S21ApiMessageMap msgMap) {

        NLZC300001PMsg param = (NLZC300001PMsg) msgMap.getPmsg();

        ArrayList<NLZC300001_xxDetailListPMsg> cviInvtyAvlList = new ArrayList<NLZC300001_xxDetailListPMsg>();

        paramList : for (int i = 0; i < param.xxDetailList.getValidCount(); i++) { // 12/08/2016 CSAI Y.Imazu Mod QC#16505

            // get cvi inventory.
            List<Map<String, Object>> cviInvtyList = getCviInvty(param.glblCmpyCd.getValue(), param.xxDetailList.no(i).mdseCd.getValue());

            // create cvi list
            for (Map<String, Object> cviInvty : cviInvtyList) {

                NLZC300001_xxDetailListPMsg cviInvtyAvl = new NLZC300001_xxDetailListPMsg();

                if (cviInvty.get(PART_NUMBER) != null) {

                    ZYPEZDItemValueSetter.setValue(cviInvtyAvl.mdseCd, (String) cviInvty.get(PART_NUMBER));
                }

                if (cviInvty.get(QUANTITY) != null) {

                    ZYPEZDItemValueSetter.setValue(cviInvtyAvl.xxAvalQty, (BigDecimal) cviInvty.get(QUANTITY));
                }

                //QC#14455 Start
                if (!ZYPCommonFunc.hasValue(param.xxDetailList.no(i).ordQty)) {

                    ZYPEZDItemValueSetter.setValue(param.xxDetailList.no(i).ordQty, BigDecimal.ONE);
                }
                //QC#14455 End

                // 11/05/2015 add start
                if (param.xxDetailList.no(i).ordQty.getValue().compareTo((BigDecimal) cviInvty.get(QUANTITY)) > 0) {

                    ZYPEZDItemValueSetter.setValue(cviInvtyAvl.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);

                } else {

                    ZYPEZDItemValueSetter.setValue(cviInvtyAvl.xxAvalOrdFlg, ZYPConstant.FLG_ON_Y);
                }
                // 11/05/2015 add end

                String cviLocCd = ZYPCodeDataUtil.getVarCharConstValue(NLZC3000_CVI_LOC_CD, param.glblCmpyCd.getValue());
                String cviLocNm = ZYPCodeDataUtil.getVarCharConstValue(NLZC3000_CVI_LOC_NM, param.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(cviInvtyAvl.invtyLocCd, cviLocCd);
                ZYPEZDItemValueSetter.setValue(cviInvtyAvl.locNm, cviLocNm);
                cviInvtyAvl.xxAvalOrdFlg.clear();
                cviInvtyAvl.xxWhInEtaDtTxt.clear();

                // copy input param
                ZYPEZDItemValueSetter.setValue(cviInvtyAvl.ordQty, param.xxDetailList.no(i).ordQty);
                ZYPEZDItemValueSetter.setValue(cviInvtyAvl.shipToCustCd, param.xxDetailList.no(i).shipToCustCd);
                ZYPEZDItemValueSetter.setValue(cviInvtyAvl.rsdDt, param.xxDetailList.no(i).rsdDt);
                ZYPEZDItemValueSetter.setValue(cviInvtyAvl.rddDt, param.xxDetailList.no(i).rddDt);
                ZYPEZDItemValueSetter.setValue(cviInvtyAvl.xxRsltFlg_EX, ZYPConstant.FLG_ON_Y);

                cviInvtyAvlList.add(cviInvtyAvl);

                /* 12/08/2016 CSAI Y.Imazu Add QC#16505 START */
                invtyDataCount++;

                if (invtyDataCount > MAX_RECORD) {

                    break paramList;
                }
                /* 12/08/2016 CSAI Y.Imazu Add QC#16505 END */
            }
        }

        return cviInvtyAvlList;
    }

    /**
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return CVI Inventory List<Map<String,Object>>
     */
    private List<Map<String, Object>> getCviInvty(String glblCmpyCd, String mdseCd) {

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(BIND_GLBL_CMPY_CD, glblCmpyCd); // 10/26/2015 add
        queryParams.put(BIND_PART_NUMBER, mdseCd);
        queryParams.put(BIND_CVI_INVTY_TP_DESC_TXT_LIST, getCviStkStsCdGoodList(glblCmpyCd)); // 12/08/2015 add

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCviInvty", queryParams);
    }

    /**
     * Output API Response
     * @param msgMap
     */
     private void outputResponse(ArrayList<NLZC300001_xxDetailListPMsg> csaInvtyAvlbltyList, ArrayList<NLZC300001_xxDetailListPMsg> cusaInvtyAvlbltyList, ArrayList<NLZC300001_xxDetailListPMsg> cusaPartsInvtyAvlbltyList,
             // QC#61128 Mod Start
             //ArrayList<NLZC300001_xxDetailListPMsg> cviInvtyAvlbltyList, S21ApiMessageMap msgMap) {
             ArrayList<NLZC300001_xxDetailListPMsg> cviInvtyAvlbltyList, ArrayList<NLZC300001_xxDetailListPMsg> thirdPartyInvtyAvlbltyList, S21ApiMessageMap msgMap) {
             // QC#61128 Mod End

        NLZC300001PMsg param = (NLZC300001PMsg) msgMap.getPmsg();

        ArrayList<NLZC300001_xxDetailListPMsg> resultList = new ArrayList<NLZC300001_xxDetailListPMsg>();

        // List Join
        resultList.addAll(csaInvtyAvlbltyList);
        resultList.addAll(cusaInvtyAvlbltyList);
        resultList.addAll(cusaPartsInvtyAvlbltyList);
        resultList.addAll(cviInvtyAvlbltyList);
        // QC#61128 Add Start
        resultList.addAll(thirdPartyInvtyAvlbltyList);
        // QC#61128 Add End

        // output clear
        param.xxDetailList.clear();

        // set result;
        int cnt = 0;

        for (NLZC300001_xxDetailListPMsg result : resultList) {

            EZDMsg.copy(result, null, param.xxDetailList.no(cnt), null);
            cnt++;

            if (cnt == MAX_RECORD) {

                /* 12/15/2015 CSAI Y.Imazu Mod QC2001 START */
                // msgMap.addXxMsgId(NLZM1019E);
                // errItemCount++;
                setMsgItemList(NLZM1019E);
                /* 12/15/2015 CSAI Y.Imazu Mod QC2001 END */
                break;
            }
        }

        param.xxDetailList.setValidCount(cnt);

        // Set Error Message
        for (String messageItem : messageItemList) {

            msgMap.addXxMsgId(messageItem);
            /* 12/15/2015 CSAI Y.Imazu Del QC2001 START */
            // errItemCount++;
            /* 12/15/2015 CSAI Y.Imazu Del QC2001 END */
        }

        param.xxMsgIdList.setValidCount(errItemCount);
    }

    /**
     * Check Input Parameter
     * @param msgMap S21ApiMessageMap
     * @return boolean true=ERROR false=NON_ERROR
     */
    private boolean checkInputParameter(S21ApiMessageMap msgMap) {

        NLZC300001PMsg param = (NLZC300001PMsg) msgMap.getPmsg();

        boolean err = false;

        if (!hasValue(param.glblCmpyCd)) {

            /* 12/15/2015 CSAI Y.Imazu Mod QC2001 START */
            // msgMap.addXxMsgId(NLZM2052E);
            // errItemCount++;
            setMsgItemList(NLZM2052E);
            /* 12/15/2015 CSAI Y.Imazu Mod QC2001 END */
            err = true;
        }

        if (!hasValue(param.procDt)) {

            /* 12/15/2015 CSAI Y.Imazu Mod QC2001 START */
            // msgMap.addXxMsgId(NLZM2053E);
            // errItemCount++;
            setMsgItemList(NLZM2053E);
            /* 12/15/2015 CSAI Y.Imazu Mod QC2001 END */
            err = true;
        }

        if (!hasValue(param.xxRqstFlg_01)) {

            /* 12/15/2015 CSAI Y.Imazu Mod QC2001 START */
            // msgMap.addXxMsgId(NLZM1001E);
            // errItemCount++;
            setMsgItemList(NLZM1001E);
            /* 12/15/2015 CSAI Y.Imazu Mod QC2001 END */
            err = true;
        }

        if (!hasValue(param.xxRqstFlg_02)) {

            /* 12/15/2015 CSAI Y.Imazu Mod QC2001 START */
            // msgMap.addXxMsgId(NLZM1001E);
            // errItemCount++;
            setMsgItemList(NLZM1001E);
            /* 12/15/2015 CSAI Y.Imazu Mod QC2001 END */
            err = true;
        }

        if (!hasValue(param.xxRqstFlg_03)) {

            /* 12/15/2015 CSAI Y.Imazu Mod QC2001 START */
            // msgMap.addXxMsgId(NLZM1001E);
            // errItemCount++;
            setMsgItemList(NLZM1001E);
            /* 12/15/2015 CSAI Y.Imazu Mod QC2001 END */
            err = true;
        }

        if (!hasValue(param.xxRqstFlg_04)) {

            /* 12/15/2015 CSAI Y.Imazu Mod QC2001 START */
            // msgMap.addXxMsgId(NLZM1001E);
            // errItemCount++;
            setMsgItemList(NLZM1001E);
            /* 12/15/2015 CSAI Y.Imazu Mod QC2001 END */
            err = true;
        }

        if (!hasValue(param.xxRqstFlg_WH)) {
            ZYPEZDItemValueSetter.setValue(param.xxRqstFlg_WH, ZYPConstant.FLG_ON_Y);
        }

        if (!hasValue(param.xxRqstFlg_TC)) {
            ZYPEZDItemValueSetter.setValue(param.xxRqstFlg_TC, ZYPConstant.FLG_ON_Y);
        }

        for (int i = 0; i < param.xxDetailList.getValidCount(); i++) {

            if (!hasValue(param.xxDetailList.no(i).mdseCd)) {

                /* 12/15/2015 CSAI Y.Imazu Mod QC2001 START */
                // msgMap.addXxMsgId(NLZM0005E);
                // errItemCount++;
                setMsgItemList(NLZM0005E);
                /* 12/15/2015 CSAI Y.Imazu Mod QC2001 END */
                err = true;
                break;
            }
        }

        return err;
    }
 
    // 10/23/2015 add start
    /**
     * @return CUSA Inventory Location Code List<Map<String,Object>>
     */
    private List<Map<String, Object>> getCusaInvtyLocCdList(String glblCmpyCd) {

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(BIND_CUSA_CMPY_CD, GLBL_CMPY_CD_CUSA);
        queryParams.put(BIND_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);

        String avalLocCd = ZYPCodeDataUtil.getVarCharConstValue("NLZC3000_AVAL_WS_LOC_LIST", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(avalLocCd)) {
            String[] avalLocCdArray = avalLocCd.split(",");
            queryParams.put(BIND_AVAL_LOC_CD_ARRAY, avalLocCdArray);
        }

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCusaInvtyLocCdList", queryParams);
    }

    /**
     * @param invtyLocCd String
     * @return boolean
     */
    private Map<String, Object> getCusaInvtyLocMap(String invtyLocCd) {

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(BIND_CUSA_CMPY_CD, GLBL_CMPY_CD_CUSA);
        queryParams.put(BIND_INVTY_LOC_CD, invtyLocCd);
        queryParams.put(BIND_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);

        List<Map<String, Object>> listCusaInvtyLocCd = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCusaInvtyLocCd", queryParams);

        if (listCusaInvtyLocCd.size() > 0) {

            return listCusaInvtyLocCd.get(0);

        } else {

            return null;
        }
    }

    /**
     * @param invtyLocCd String
     * @return boolean
     */
    private boolean checkCusaPartsInvtyLocCd(String invtyLocCd) {

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(BIND_INVTY_LOC_CD, invtyLocCd);

        List<Map<String, Object>> listCusaPartsInvtyLocCd = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("checkCusaPartsInvtyLocCd", queryParams);

        if (listCusaPartsInvtyLocCd.size() > 0) {

            return true;

        } else {

            return false;
        }
    }
    // 10/23/2015 add end

    // 12/07/2015 add start
    /**
     * @param glblCmpyCd String
     * @return List<String>
     */
    private List<String> getCviStkStsCdGoodList(String glblCmpyCd) {

        List<String> cviStkStsCdGoodList = new ArrayList<String>();

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        queryParams.put(BIND_DS_COND_CONST_GRP_ID, DS_COND_CONST_GRP_ID_NLZC3000);
        queryParams.put(BIND_DS_COND_CONST_CD, DS_COND_CONST_CD_CVI_STK_STS_CD_GOOD);

        List<Map<String, Object>> listCviStkStsCdGood = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCviStkStsCdGood", queryParams);

        if (listCviStkStsCdGood.size() > 0) {

            Map<String, Object> mapCviStkStsCdGood= (Map<String, Object>)listCviStkStsCdGood.get(0);
            String cviStkStsCdGood = (String)mapCviStkStsCdGood.get(DS_COND_CONST_VAL_TXT_01);

            if (ZYPCommonFunc.hasValue(cviStkStsCdGood)) {

                cviStkStsCdGoodList.add(cviStkStsCdGood);
            }

            cviStkStsCdGood = (String)mapCviStkStsCdGood.get(DS_COND_CONST_VAL_TXT_02);

            if (ZYPCommonFunc.hasValue(cviStkStsCdGood)) {

                cviStkStsCdGoodList.add(cviStkStsCdGood);
            }

            cviStkStsCdGood = (String)mapCviStkStsCdGood.get(DS_COND_CONST_VAL_TXT_03);

            if (ZYPCommonFunc.hasValue(cviStkStsCdGood)) {

                cviStkStsCdGoodList.add(cviStkStsCdGood);
            }

            cviStkStsCdGood = (String)mapCviStkStsCdGood.get(DS_COND_CONST_VAL_TXT_04);

            if (ZYPCommonFunc.hasValue(cviStkStsCdGood)) {

                cviStkStsCdGoodList.add(cviStkStsCdGood);
            }

            cviStkStsCdGood = (String)mapCviStkStsCdGood.get(DS_COND_CONST_VAL_TXT_05);

            if (ZYPCommonFunc.hasValue(cviStkStsCdGood)) {

                cviStkStsCdGoodList.add(cviStkStsCdGood);
            }

            cviStkStsCdGood = (String)mapCviStkStsCdGood.get(DS_COND_CONST_VAL_TXT_06);

            if (ZYPCommonFunc.hasValue(cviStkStsCdGood)) {

                cviStkStsCdGoodList.add(cviStkStsCdGood);
            }

            cviStkStsCdGood = (String)mapCviStkStsCdGood.get(DS_COND_CONST_VAL_TXT_07);

            if (ZYPCommonFunc.hasValue(cviStkStsCdGood)) {

                cviStkStsCdGoodList.add(cviStkStsCdGood);
            }

            cviStkStsCdGood = (String)mapCviStkStsCdGood.get(DS_COND_CONST_VAL_TXT_08);

            if (ZYPCommonFunc.hasValue(cviStkStsCdGood)) {

                cviStkStsCdGoodList.add(cviStkStsCdGood);
            }

            cviStkStsCdGood = (String)mapCviStkStsCdGood.get(DS_COND_CONST_VAL_TXT_09);

            if (ZYPCommonFunc.hasValue(cviStkStsCdGood)) {

                cviStkStsCdGoodList.add(cviStkStsCdGood);
            }

            cviStkStsCdGood = (String)mapCviStkStsCdGood.get(DS_COND_CONST_VAL_TXT_10);

            if (ZYPCommonFunc.hasValue(cviStkStsCdGood)) {

                cviStkStsCdGoodList.add(cviStkStsCdGood);
            }
        }

        return cviStkStsCdGoodList;
    }
    // 12/07/2015 add end

    /* 12/15/2015 CSAI Y.Imazu Add QC2001 START */
    /**
     * Set Error Message ID to Error Message List
     * @param errMsgId String
     */
    private void setMsgItemList (String errMsgId) {

        if (messageItemList.size() < errMsgListSize && !messageItemList.contains(errMsgId)) {

            messageItemList.add(errMsgId);
            errItemCount++;
        }
    }
    /* 12/15/2015 CSAI Y.Imazu Add QC2001 END */

    /**
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return CVI Inventory List<Map<String,Object>>
     */
    private String getSplyItemNum(String glblCmpyCd, String prntVndCd, String mdseCd) {

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        queryParams.put(BIND_PRNT_VND_CD, prntVndCd);
        queryParams.put(BIND_MDSE_CD, mdseCd);
        queryParams.put(BIND_SLS_DT, ZYPDateUtil.getSalesDate());

        return (String) this.ssmBatchClient.queryObject("getSplyItemNum", queryParams);
    }

    /**
     * 
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @param techTocCd String
     * @param mdseCd String
     * @param slsDt String
     * @return canonSellToShipToCodeString
     */
    private Map<String, String> getCanonSellToShipToCode(String glblCmpyCd, String rtlWhCd, String techTocCd, String mdseCd, String slsDt) {

        Map<String, String> sellToShipToMap = new HashMap<String, String>();
        sellToShipToMap.put("SELL_TO_CUST_CD", defCanonSellToCode);

        if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
            if (ZYPCommonFunc.hasValue(techTocCd)) {
                Map<String, Object> queryParams = new HashMap<String, Object>();
                queryParams.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
                queryParams.put(BIND_TECH_TOC_CD, techTocCd);
                rtlWhCd = (String) this.ssmBatchClient.queryObject("getRtlWhCodeByTechCode", queryParams);
                if (rtlWhCd == null) {
                    rtlWhCd = defRtlWhCode;
                }
            } else {
                rtlWhCd = defRtlWhCode;
            }
        }

        if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
            return sellToShipToMap;
        }

        MDSETMsg mdseInMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseInMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseInMsg.mdseCd, mdseCd);

        MDSETMsg mdseOutMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseInMsg);
        String canonLocationCode = null;

        if (mdseOutMsg != null) {
            Map<String, Object> queryParams = new HashMap<String, Object>();
            queryParams.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
            queryParams.put(BIND_SLS_DT, ZYPDateUtil.getSalesDate());
            queryParams.put(BIND_TECH_TOC_CD, techTocCd);
            queryParams.put(BIND_RTL_WH_CD, rtlWhCd);
            queryParams.put(BIND_PRCH_GRP_CD, mdseOutMsg.prchGrpCd.getValue());
            queryParams.put(BIND_VND_XREF_TP_CD, VND_XREF_TP.CSA_TO_CUSA);
            queryParams.put(BIND_ASTERISK, ASTERISK);
            queryParams.put(BIND_SLS_DT, slsDt);

            canonLocationCode = (String) this.ssmBatchClient.queryObject("getCanonLocationCode", queryParams);
        }

        if (!ZYPCommonFunc.hasValue(canonLocationCode)) {
            Map<String, Object> queryParams = new HashMap<String, Object>();
            queryParams.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
            queryParams.put(BIND_SLS_DT, ZYPDateUtil.getSalesDate());
            queryParams.put(BIND_TECH_TOC_CD, techTocCd);
            queryParams.put(BIND_RTL_WH_CD, rtlWhCd);
            queryParams.put(BIND_PRCH_GRP_CD, ASTERISK);
            queryParams.put(BIND_VND_XREF_TP_CD, VND_XREF_TP.CSA_TO_CUSA);
            queryParams.put(BIND_ASTERISK, ASTERISK);
            queryParams.put(BIND_SLS_DT, slsDt);

            canonLocationCode = (String) this.ssmBatchClient.queryObject("getCanonLocationCode", queryParams);
        }

        if (canonLocationCode == null) {
            return sellToShipToMap;
        }

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(BIND_GLBL_CMPY_CD, GLBL_CMPY_CD_CUSA);
        queryParams.put("trdPtnrShipToCustCd", canonLocationCode);
        queryParams.put("ediCustTpCdList", new String[]{"1","2"});
        queryParams.put(BIND_SLS_DT, ZYPDateUtil.getSalesDate());

        Map<String, Object> resultMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getCanonSellToShipToCode", queryParams);

        if (resultMap != null) {
            sellToShipToMap.put("SELL_TO_CUST_CD", (String) resultMap.get("SELL_TO_CUST_CD"));
            sellToShipToMap.put("SHIP_TO_CUST_CD", (String) resultMap.get("SHIP_TO_CUST_CD"));
            sellToShipToMap.put("BILL_TO_CUST_CD", (String) resultMap.get("BILL_TO_CUST_CD"));
            return sellToShipToMap;
        }
        return sellToShipToMap;
    }
    // QC#61128 Add Start
    /**
     * Get Third Party Inventory Availability.
     * @param msgMap S21ApiMessageMap
     * @return Third Party Inventory Availability
     * ArrayList<NLZC300001_xxDetailListPMsg>
     */
    private ArrayList<NLZC300001_xxDetailListPMsg> getThirdPartyInventory(S21ApiMessageMap msgMap) {

        NLZC300001PMsg param = (NLZC300001PMsg) msgMap.getPmsg();

        ArrayList<NLZC300001_xxDetailListPMsg> thirdPartyInvtyAvlList = new ArrayList<NLZC300001_xxDetailListPMsg>();

        paramList : for (int i = 0; i < param.xxDetailList.getValidCount(); i++) {

            // get third party inventory.
            List<Map<String, Object>> thirdPartyInvtyList = getThirdPartyInvty(param.glblCmpyCd.getValue(), param.xxDetailList.no(i).mdseCd.getValue(), param.xxDetailList.no(i).vndCd.getValue());

            // create third party list
            for (Map<String, Object> thirdPartyInvty : thirdPartyInvtyList) {

                NLZC300001_xxDetailListPMsg thirdPartyInvtyAvl = new NLZC300001_xxDetailListPMsg();

                if (thirdPartyInvty.get(PART_NUMBER) != null) {

                    ZYPEZDItemValueSetter.setValue(thirdPartyInvtyAvl.mdseCd, (String) thirdPartyInvty.get(PART_NUMBER));
                }

                if (thirdPartyInvty.get(QUANTITY) != null) {

                    // 2023/09/04 QC#61703 START
                    BigDecimal avalQty = (BigDecimal) thirdPartyInvty.get(QUANTITY);
                    if (ZYPCommonFunc.hasValue(param.xxDetailList.no(i).allocQty) && (BigDecimal.ZERO.compareTo(avalQty.subtract(param.xxDetailList.no(i).allocQty.getValue())) > 0)){
                        ZYPEZDItemValueSetter.setValue(thirdPartyInvtyAvl.xxAvalQty, BigDecimal.ZERO);
                    } else if (ZYPCommonFunc.hasValue(param.xxDetailList.no(i).allocQty)) {
                        ZYPEZDItemValueSetter.setValue(thirdPartyInvtyAvl.xxAvalQty, avalQty.subtract(param.xxDetailList.no(i).allocQty.getValue()));
                    } else {
                        ZYPEZDItemValueSetter.setValue(thirdPartyInvtyAvl.xxAvalQty, avalQty);
                    }
                    // 2023/09/04 QC#61703 END
                }

                if (!ZYPCommonFunc.hasValue(param.xxDetailList.no(i).ordQty)) {

                    ZYPEZDItemValueSetter.setValue(param.xxDetailList.no(i).ordQty, BigDecimal.ONE);
                }

                // copy input param
                ZYPEZDItemValueSetter.setValue(thirdPartyInvtyAvl.ordQty, param.xxDetailList.no(i).ordQty);

                thirdPartyInvtyAvlList.add(thirdPartyInvtyAvl);

                invtyDataCount++;

                if (invtyDataCount > MAX_RECORD) {

                    break paramList;
                }
            }
        }

        return thirdPartyInvtyAvlList;
    }

    /**
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return prntVndCd String
     */
    private String getPrntVndCd(String glblCmpyCd, String vndCd) {

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        queryParams.put(BIND_VND_CD, vndCd);

        return (String) this.ssmBatchClient.queryObject("getPrntVndCd", queryParams);
    }

    /**
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return CVI Inventory List<Map<String,Object>>
     */
    private List<Map<String, Object>> getThirdPartyInvty(String glblCmpyCd, String mdseCd, String vndCd) {

        String prntVndCd = getPrntVndCd(glblCmpyCd, vndCd);

        if (ZYPCommonFunc.hasValue(prntVndCd)) {

            Map<String, Object> queryParams = new HashMap<String, Object>();
            queryParams.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
            queryParams.put(BIND_MDSE_CD, mdseCd);
            queryParams.put(BIND_PRNT_VND_CD, prntVndCd);
            queryParams.put(BIND_VND_CD, vndCd);
            // 10/03/2023 QC#61703 Add Start
            String stockStatusCds = ZYPCodeDataUtil.getVarCharConstValue("NLZC300001_3PL_SS_" + vndCd, glblCmpyCd);
            String stockStatusCd[] = null;
            if (stockStatusCds != null) {
                stockStatusCd = stockStatusCds.split(",");
            } else {
                stockStatusCd = new String[]{STK_STS.GOOD};
            }
            List<String> ssList = Arrays.asList(stockStatusCd);
            queryParams.put(BIND_STK_STS_CD, ssList);
//            queryParams.put(BIND_STK_STS_CD, STK_STS.GOOD);
            // 10/03/2023 QC#61703 Add End

            return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getThirdPtyInvty", queryParams);
        } else {
            return null;
        }
    }
    // QC#61128 Add End
}
