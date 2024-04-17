package com.canon.cusa.s21.api.NLZ.NLZC080001;

import java.math.BigDecimal;
import java.util.List;

import business.parts.NLZC080001PMsg;
import business.parts.NLZC080001_xxItemListPMsg;

import com.canon.cusa.s21.api.NLZ.NLZC080001.constant.NLZC080001Constant;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.usa.s21.integration.service.sti.integration.nlbi1110.data.STIShippingOrderRequest;
import com.canon.usa.s21.integration.service.sti.integration.nlbi1110.data.STIXMLResponse;
import com.canon.usa.s21.integration.service.sti.integration.nlbi1110.wrapper.STIShippingOrderServiceProxy;

/**
 * <pre>
 * Shipping Order to STI API
 *  
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/07/2017   CITS            Y.Iwasaki       Create          QC#20684
 * 11/17/2017   CITS            Y.Iwasaki       Update          QC#22546
 * 02/05/2019   CITS            Y.Iwasaki       Update          QC#30235
 * 06/17/2021   CITS            M.Furugoori     Update          QC#56495
 *</pre>
 */

public class NLZC080001 extends S21ApiCommonBase {

    public NLZC080001() {
        super();
    }

    public NLZC080001PMsg execute(NLZC080001PMsg pMsg) {

        // Create request parameters
        STIShippingOrderRequest req = new STIShippingOrderRequest();

        STIShippingOrderRequest.MessageHeader mhead = new STIShippingOrderRequest.MessageHeader();
        req.setMessageHeader(mhead);
        mhead.setFromPartnerId(pMsg.tplFromPtnrId.getValue());
        mhead.setToPartnerId(pMsg.tplToPtnrId.getValue());
        mhead.setMessageDateTime(formatDate(pMsg.ordDtTmTsTxt.getValue(), NLZC080001Constant.FMT_YYYYMMDDHHMMSSSSS, NLZC080001Constant.FMT_XMLDATETIMEFORMAT));

        STIShippingOrderRequest.OrderHeader ohead = new STIShippingOrderRequest.OrderHeader();
        req.setOrderHeader(ohead);
        ohead.setDeliveryNumber(pMsg.ordIdTxt.getValue());
        ohead.setIDOCNumber(pMsg.tplRefNm.getValue());
        ohead.setShippingPoint(pMsg.tplLocTxt.getValue());
        ohead.setShippingCondition(pMsg.tplCondTxt.getValue());
        ohead.setDeliveryType(pMsg.ordTpTxt.getValue());
        ohead.setSalesOrg(pMsg.tplOrgTxt.getValue());
        ohead.setControlIndicator(pMsg.tplCtrlId.getValue());
        ohead.setCarrier(pMsg.carrCd.getValue());
        ohead.setRequestedDeliveryDateTime(formatDate(pMsg.reqDtTmTsTxt.getValue(), NLZC080001Constant.FMT_YYYYMMDDHHMMSSSSS, NLZC080001Constant.FMT_XMLDATETIMEFORMAT));
        ohead.setShipDate(formatDate(pMsg.rqstShipDtTmTsTxt.getValue(), NLZC080001Constant.FMT_YYYYMMDDHHMMSSSSS, NLZC080001Constant.FMT_XMLDATEFORMAT));
        if(ZYPCommonFunc.hasValue(pMsg.shipCtacNmTxt)) {
            ohead.setContactName(pMsg.shipCtacNmTxt.getValue());
        }
        ohead.setContactPhone(pMsg.shipCtacPhoNum.getValue());
        List<String> instList = ohead.getCarrierInstructions();
        for (int n = 0; n < pMsg.xxMsgDescList.getValidCount(); ++n) {
            instList.add(pMsg.xxMsgDescList.no(n).soMsgDescTxt.getValue());
        }
        STIShippingOrderRequest.OrderHeader.ShipTo shipTo = new STIShippingOrderRequest.OrderHeader.ShipTo();
        ohead.setShipTo(shipTo);
        shipTo.setPartnerQ(pMsg.tplPtnrTxt.getValue());
        shipTo.setShipID(pMsg.shipCustTxt.getValue());
        shipTo.setName1(pMsg.shipFirstLineNm.getValue());
        if (ZYPCommonFunc.hasValue(pMsg.shipScdLineNm)) {
            shipTo.setName2(pMsg.shipScdLineNm.getValue());
        }
        if (ZYPCommonFunc.hasValue(pMsg.shipThirdLineNm)) {
            shipTo.setName3(pMsg.shipThirdLineNm.getValue());
        }
        if (ZYPCommonFunc.hasValue(pMsg.shipFrthLineNm)) {
            shipTo.setName4(pMsg.shipFrthLineNm.getValue());
        }
        shipTo.setStreet1(pMsg.shipFirstLineAddrTxt.getValue());
        if (ZYPCommonFunc.hasValue(pMsg.shipScdLineAddrTxt)) {
            shipTo.setStreet2(pMsg.shipScdLineAddrTxt.getValue());
        }
        shipTo.setPostalCode(pMsg.shipZipOrPostCdTxt.getValue());
        shipTo.setCity(pMsg.shipCtyTxt.getValue());
        shipTo.setCountry(pMsg.shipCtryTxt.getValue());
        shipTo.setState(pMsg.shipStOrProvTxt.getValue());
        shipTo.setTelephone(pMsg.shipPhoNumTxt.getValue());

        List<STIShippingOrderRequest.LineItem> itemList = req.getLineItem();
        for (int n = 0; n < pMsg.xxItemList.getValidCount(); ++n) {
            NLZC080001_xxItemListPMsg pItem = pMsg.xxItemList.no(n);

            STIShippingOrderRequest.LineItem item = new STIShippingOrderRequest.LineItem();
            itemList.add(item);
            item.setMaterial(pItem.itemCdTxt.getValue());
            item.setQty(pItem.qtyOrdTxt.getValue());
            item.setLineNumber(pItem.ordLineTxt.getValue());
            item.setItemType(pItem.tplItemTpCd.getValue());
            item.setRefOrderNum(pItem.custPoTpTxt.getValue());
            item.setRefOrderLineNum(pItem.custPoLineTxt.getValue());
            item.setPlant(pItem.tplLocTxt.getValue());
            item.setStorageLocn(pItem.tplSwhCd.getValue());
            // START 2021/06/17 [QC#56495, ADD]
            BigDecimal svcConfigMstrPk = pItem.svcConfigMstrPk.getValue();
            String configNum = null;
            if (svcConfigMstrPk == null) {
                configNum = "";
            } else {
                configNum = svcConfigMstrPk.toString();
            }
            item.setConfigNum(configNum);
            item.setInternal(pItem.itrlItemFlg.getValue());
            // END 2021/06/17 [QC#56495, ADD]

            String ordLineTxt = pItem.ordLineTxt.getValue();
            STIShippingOrderRequest.LineItem.Serials serials = new STIShippingOrderRequest.LineItem.Serials();
            List<String> serList = serials.getSerial();
            for (int m = 0; m < pMsg.xxSerNumList.getValidCount(); ++m) {
                if (ordLineTxt.equals(pMsg.xxSerNumList.no(m).ordLineTxt.getValue())) {
                    serList.add(pMsg.xxSerNumList.no(m).tplSerNum.getValue());
                }
            }
            // QC#30235
            // STI cannot process empty "Serials" element in XML message.
            // Clear empty "Serials" element when serial does not exist in each line.
            if(serList.size()>0) {
                item.setSerials(serials);
            }
        }

        if (itemList.size() > 0) {
            List<String> srvyList = itemList.get(0).getSiteSurvey();
            for (int m = 0; m < pMsg.xxSiteSrvyList.getValidCount(); ++m) {
                srvyList.add(pMsg.xxSiteSrvyList.no(m).tplSiteSrvyTxt.getValue());
            }
        }

        String keys = NLXCMsgHelper.toListedString(NLZC080001Constant.COL_ORD_ID_TXT, NLZC080001Constant.COL_TPL_REF_NM);
        String values = NLXCMsgHelper.toListedString(pMsg.ordIdTxt.getValue(), pMsg.tplRefNm.getValue());
        // Invoke service proxy
        STIXMLResponse res = null;
        try {
            String invokeMode = ZYPCodeDataUtil.getVarCharConstValue(NLZC080001Constant.VAL_NLZC080001_INVOKE_MODE, pMsg.glblCmpyCd.getValue());

            if (ZYPCommonFunc.hasValue(invokeMode) && ZYPConstant.FLG_ON_1.equals(invokeMode)) {
                // Invoke stub(for local test)
                res = stub_stiShippingOrder_Success(req);
            } else {
                // Invoke service(real call)
                STIShippingOrderServiceProxy proxy = new STIShippingOrderServiceProxy();
                res = proxy.stiShippingOrder(req);
            }
        } catch (Throwable t) {
            throw new S21AbendException(new RuntimeException(NLZC080001Constant.NLZM2516E, t), NLZC080001Constant.NLZM2516E, new String[] {NLZC080001Constant.APPLID, keys, values,
                    substring(t.getMessage(), NLZC080001Constant.VAL_MAX_PRM_LEN) });
        }

        // Handle response
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        // System error
        STIXMLResponse.ERRORMESSAGES errMsgs = res.getERRORMESSAGES();
        if (errMsgs != null) {
            List<String> errList = errMsgs.getMESSAGE();
            for (int n = 0; n < errList.size(); ++n) {
                msgMap.addXxMsgIdWithPrm(NLZC080001Constant.NLZM2516E, new String[] {NLZC080001Constant.APPLID, keys, values, substring(errList.get(n), NLZC080001Constant.VAL_MAX_PRM_LEN) });
            }
        }
        // Application error
        STIXMLResponse.NewShipmentOrders ores = res.getNewShipmentOrders();
        if (ores != null) {
            List<String> detailList = ores.getMessageDetail();
            for (int n = 0; n < detailList.size(); ++n) {
                msgMap.addXxMsgIdWithPrm(NLZC080001Constant.NLZM2515E, new String[] {NLZC080001Constant.APPLID, keys, values, substring(detailList.get(n), NLZC080001Constant.VAL_MAX_PRM_LEN) });
            }
        }
        msgMap.flush();

        return pMsg;
    }

    private String substring(String str, int len) {
        if (str == null) {
            return null;
        }

        if (str.length() > len) {
            str = str.substring(0, len);
        }

        return str;
    }

    private String formatDate(String val, String fromPattern, String toPattern) {
        if (!ZYPCommonFunc.hasValue(val)) {
            return null;
        }

        return ZYPDateUtil.DateFormatter(val, fromPattern, toPattern);
    }

    // Stub method for local test
    private STIXMLResponse stub_stiShippingOrder_Success(STIShippingOrderRequest req) {
        STIXMLResponse res = new STIXMLResponse();

        STIXMLResponse.NewShipmentOrders ores = new STIXMLResponse.NewShipmentOrders();
        res.setNewShipmentOrders(ores);
        ores.setShipmentIDReceived("0000000049967952");
        ores.setTrackingNumber("1887830");
        ores.setMessage("SHIPMENT CREATED");

        return res;
    }

    private STIXMLResponse stub_stiShippingOrder_ApplicationError(STIShippingOrderRequest req) {
        STIXMLResponse res = new STIXMLResponse();

        STIXMLResponse.NewShipmentOrders ores = new STIXMLResponse.NewShipmentOrders();
        res.setNewShipmentOrders(ores);
        ores.setMessage("ERROR");
        List<String> detailList = ores.getMessageDetail();

        detailList.add("The 'MessageDateTime' element is invalid - The value '2017-07-11 13:42:42' is invalid according to its datatype 'http://www.w3.org/2001/XMLSchema:dateTime' - The string '2017-07-11 13:42:42' is not a valid XsdDateTime value.");

        return res;
    }

    private STIXMLResponse stub_stiShippingOrder_SystemError(STIShippingOrderRequest req) {
        STIXMLResponse res = new STIXMLResponse();
        STIXMLResponse.ERRORMESSAGES errMsgs = new STIXMLResponse.ERRORMESSAGES();
        res.setERRORMESSAGES(errMsgs);
        List<String> errList = errMsgs.getMESSAGE();

        errList.add("Timeout expired.  The timeout period elapsed prior to completion of the operation or the server is not responding.");

        return res;
    }
}
