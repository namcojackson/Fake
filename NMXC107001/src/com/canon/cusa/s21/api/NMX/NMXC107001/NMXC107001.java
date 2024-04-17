/**
 * <pre>
 * Geo code check by Vertex
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/11   Fujitsu         C.Yokoi         Create          N/A
 * 2017/02/10   Fujitsu         T.Aoi           Update          QC#17199
 *</pre>
 */
package com.canon.cusa.s21.api.NMX.NMXC107001;

import java.math.BigDecimal;
import java.util.Calendar;

import business.parts.NMXC107001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.usa.s21.integration.service.S21ibVertexCalculateTaxService.S21ibVertexCalculateTaxServiceProxy;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.LookupResultType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.NewOrderRequestElement;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.NewOrderResponseElement;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.PostalAddressType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.Status;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.TaxAreaLookupType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.TaxAreaRequestType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.TaxAreaResultType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.VertexEnvelopeType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.exception.TargetSystemException;

public class NMXC107001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /** Input Parameter Mode is mandatory field. */
    private static final String NMZM0029E = "NMZM0029E";

    /** Mode is invalid. */
    private static final String NMZM0024E = "NMZM0024E";

    /**
     * Input Parameter City, State, County or post is mandatory field.
     */
    private static final String NMZM0338E = "NMZM0338E";

    /** Input Parameter Geo Codes mandatory field. */
    private static final String NMZM0339E = "NMZM0339E";

    //
    /** Geo Code does not exist on Vertex API. */
    private static final String NMZM0340E = "NMZM0340E";

    // QC#17199 Mod Start
    // /** Vertex API abnormally end. */
    /** GEO Code is invalid.Please enter a valid Code. */
    // QC#17199 Mod End
    private static final String NMZM0342E = "NMZM0342E";

    /** Vertex result : Normal */
    public static final String VERTEX_RESULT_NORMAL = "NORMAL";

    /** MODE : Get Geo Code(01) */
    public static final String GET_GEO_CODE_MODE = "01";

    /** MODE : Check Exists Geo Code Mode(02) */
    public static final String CHECK_EXISTS_GEO_CODE_MODE = "02";

    /**
     * Initialize
     */
    public NMXC107001() {
        super();
    }

    /**
     * Execute
     * 
     * <pre>
     * Get Geocode Mode: Use address to get Geo code by calling Vertex API. 
     * Check Exists Geocode Mode : Check Geocode existence. Parameter Geocode is mandantory.
     * </pre>
     * @param param NMXC107001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NMXC107001PMsg param, final ONBATCH_TYPE onBatchType) {
        this.msgMap = new S21ApiMessageMap(param);
        doProcess(param, onBatchType);
        msgMap.flush();
    }

    /**
     * Do Process
     * @param param NMXC107001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    private void doProcess(final NMXC107001PMsg param, final ONBATCH_TYPE onBatchType) {

        inputParameterCheck(param);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        String mode = param.xxModeCd.getValue();

        if (GET_GEO_CODE_MODE.equals(mode)) {
            callVertexAPIForGetGeoCode(param, mode);
        } else {
            callVertexAPIForCheckExistsGeoCd(param, mode);
        }
    }

    /**
     * Check parameter
     * @param param NMXC107001PMsg
     */
    private void inputParameterCheck(final NMXC107001PMsg param) {

        if (!ZYPCommonFunc.hasValue(param.xxModeCd)) {
            msgMap.addXxMsgId(NMZM0029E);
        }

        if (GET_GEO_CODE_MODE.equals(param.xxModeCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(param.ctyAddr) && !ZYPCommonFunc.hasValue(param.stCd) && !ZYPCommonFunc.hasValue(param.postCd) && !ZYPCommonFunc.hasValue(param.cntyNm)) {
                msgMap.addXxMsgId(NMZM0338E);
            }
        } else if (CHECK_EXISTS_GEO_CODE_MODE.equals(param.xxModeCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(param.geoCd)) {
                msgMap.addXxMsgId(NMZM0339E);
            }
        } else {
            msgMap.addXxMsgId(NMZM0024E);
        }
    }

    /**
     * call Vertex API For Mode : Get Geo Code
     * @param param NMXC107001PMsg
     * @param mode String
     */
    private void callVertexAPIForGetGeoCode(NMXC107001PMsg param, String mode) {
        try {
            S21ibVertexCalculateTaxServiceProxy vtxProxy = new S21ibVertexCalculateTaxServiceProxy();

            // Get Geo Code From Address
            NewOrderRequestElement request = new NewOrderRequestElement();
            VertexEnvelopeType envelop = new VertexEnvelopeType();
            PostalAddressType pat = new PostalAddressType();
            TaxAreaLookupType taxLookupType = new TaxAreaLookupType();
            TaxAreaRequestType taxRequestType = new TaxAreaRequestType();

            if (ZYPCommonFunc.hasValue(param.ctyAddr)) {
                pat.setCity(param.ctyAddr.getValue()); // City
            }
            if (ZYPCommonFunc.hasValue(param.postCd)) {
                pat.setPostalCode(param.postCd.getValue()); // PostCode
            }
            if (ZYPCommonFunc.hasValue(param.stCd)) {
                pat.setMainDivision(param.stCd.getValue()); // State
            }
            if (ZYPCommonFunc.hasValue(param.cntyNm)) {
                pat.setSubDivision(param.cntyNm.getValue()); // County
            }

            taxLookupType.setPostalAddress(pat);
            taxRequestType.setTaxAreaLookup(taxLookupType);
            envelop.setTaxAreaRequest(taxRequestType);
            request.setVertexEnvelope(envelop);

            NewOrderResponseElement response = vtxProxy.newOrder(request);
            ZYPEZDItemValueSetter.setValue(param.geoCd, response.getVertexEnvelope().getTaxAreaResponse().getTaxAreaResult()[0].getTaxAreaId().toString());

            if (!ZYPCommonFunc.hasValue(param.geoCd)) {
                msgMap.addXxMsgId(NMZM0340E);
            }
        } catch (Throwable e) {
            msgMap.addXxMsgId(NMZM0342E);
        }
    }

    /**
     * call Vertex API For Mode : Check Exists Geo Code
     * @param param NMXC107001PMsg
     * @param mode String
     */
    private void callVertexAPIForCheckExistsGeoCd(NMXC107001PMsg param, String mode) {
        try {
            S21ibVertexCalculateTaxServiceProxy vtxProxy = new S21ibVertexCalculateTaxServiceProxy();

            NewOrderRequestElement request2 = new NewOrderRequestElement();
            VertexEnvelopeType envelop2 = new VertexEnvelopeType();
            TaxAreaLookupType taxLookupType2 = new TaxAreaLookupType();
            taxLookupType2.setTaxAreaId(new BigDecimal(param.geoCd.getValue()).toBigInteger()); // GeoCode
            Calendar calendar = Calendar.getInstance(); // Date
            taxLookupType2.setAsOfDate(calendar);
            TaxAreaRequestType tar = new TaxAreaRequestType();
            tar.setTaxAreaLookup(taxLookupType2);
            envelop2.setTaxAreaRequest(tar);
            request2.setVertexEnvelope(envelop2);
            NewOrderResponseElement response2 = vtxProxy.newOrder(request2);

            TaxAreaResultType[] taxAreaResultArray = response2.getVertexEnvelope().getTaxAreaResponse().getTaxAreaResult();
            boolean isExistsGeoCode = false;
            if (taxAreaResultArray != null && taxAreaResultArray.length > 0) {
                for (int i = 0; i < taxAreaResultArray.length; i++) {
                    if (param.geoCd.getValue().equals(taxAreaResultArray[i].getTaxAreaId().toString())) {
                        Status[] status = taxAreaResultArray[i].getStatus();
                        LookupResultType lookupResult = status[0].getLookupResult();
                        if (VERTEX_RESULT_NORMAL.equals(lookupResult.getValue().toString())) {
                            isExistsGeoCode = true;
                            break;
                        }
                    }

                }
            }

            if (!isExistsGeoCode) {
                msgMap.addXxMsgId(NMZM0340E);
            }
        } catch (TargetSystemException t) {
            msgMap.addXxMsgId(NMZM0340E);
        } catch (Throwable e) {
            msgMap.addXxMsgId(NMZM0342E);
        }
    }
}
