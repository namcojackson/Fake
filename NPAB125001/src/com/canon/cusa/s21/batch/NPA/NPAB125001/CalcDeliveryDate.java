/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.batch.NPA.NPAB125001;

import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.ACTL_ARV_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.ACTL_SHIP_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_CAL_MULT_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_CAL_SUB_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_CUSA_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_FROM_LOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_FRT_CHRG_METH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_FRT_CHRG_TO_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_INVTY_TRX_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_RWS_REF_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_SHIP_TO_POST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_SHPG_MODE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_SHPG_SVC_LVL_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_SLS_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_SO_CUST_DATA_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_ST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_TRX_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_TRX_RSN_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.BIND_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.CAL_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.DELIVERY_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.ETA_DAYS_AOT;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.FRT_CHRG_METH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.FRT_CHRG_TO_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.NPAM1501E;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.NPAM1502E;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.NPAM1503E;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.NPAM1504E;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.POST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.PO_REQ_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SHIP_DT_TM_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SHPG_MODE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SHPG_SVC_LVL_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.ST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.THIRD_PTY_INV_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.VAR_CUSA_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB125001.constant.NPAB125001Constant.DUMMY_ST_CD;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;

import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_SUB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * NPAB125001:Receiving ASN for CUSA Domestic
 * 
 * Date         Company      Name             Create/Update    Defect No
 * ---------------------------------------------------------------------
 * 2016/02/06   CITS         T.Hakodate       All Update       CSA
 * 2018/10/05   CITS         T.Hakodate        Update          QC#28621
 * 2019/09/10   CITS         T.Wada           Update           QC#53323
 * 
 * <pre>
 */
public class CalcDeliveryDate {

    /** User Profile */
    private S21UserProfileService profileService = null;

    /** SQL Access Parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** cusaGlblCmpyCd. */
    private String cusaGlblCmpyCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /**
     * CalcDeliveryDate
     * @param profileService S21UserProfileService
     * @param ssmBatchClient S21SsmBatchClient
     */
    public CalcDeliveryDate(S21UserProfileService profileService, S21SsmBatchClient ssmBatchClient) {

        this.profileService = profileService;

        this.ssmBatchClient = ssmBatchClient;

    }

    /**
     * calcDeliveryDate
     * @param asnRecordList List<Map>
     * @return String
     */
    public String calcDeliveryDate(List<Map> asnRecordList) {

        this.cusaGlblCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_GLBL_CMPY_CD, profileService.getGlobalCompanyCode());

        // Get the Global Company Code.
        this.glblCmpyCd = profileService.getGlobalCompanyCode();

        String rtnmsg = null;

        for (Map<String, Object> asnRecord : asnRecordList) {

            rtnmsg = calcDeliveryDate(asnRecord);

            if (ZYPCommonFunc.hasValue(rtnmsg)) {

                return rtnmsg;

            }
        }

        return null;

    }

    /**
     * calcDeliveryDate
     * @param asnRecord
     * @return
     */
    private String calcDeliveryDate(Map<String, Object> asnRecord) {

        String rtnmsg = null;

        if (ZYPCommonFunc.hasValue((String) asnRecord.get(ACTL_ARV_DT))) {

            asnRecord.put(DELIVERY_DATE, (String) asnRecord.get(ACTL_ARV_DT));

            return null;

        }

        // intangible
        if (!ZYPCommonFunc.hasValue((String) asnRecord.get(INVTY_LOC_CD))) {

            return null;
        }

        // vendor
        if (ZYPCommonFunc.hasValue((String) asnRecord.get(PO_REQ_FLG)) && ZYPConstant.FLG_ON_Y.equals((String) asnRecord.get(PO_REQ_FLG))) {

            String asnDeliveryDate = getLatestWarehouseEstimatedTimeOfArrivalDate(asnRecord);

            if (ZYPCommonFunc.hasValue(asnDeliveryDate)) {

                asnRecord.put(DELIVERY_DATE, asnDeliveryDate);

                return null;

            } else {

                rtnmsg = NPAM1501E;
                S21InfoLogOutput.println(rtnmsg);
                return rtnmsg;
            }

        }

        Map<String, Object> shpgOrdCustDtlT = getShpgOrdCustDtl(asnRecord);

        if (shpgOrdCustDtlT == null) {

            rtnmsg = NPAM1502E;
            S21InfoLogOutput.println(rtnmsg);
            return rtnmsg;

        }

        String modeCode = getShpgModeCd(asnRecord);

        if (ZYPCommonFunc.hasValue(modeCode)) {

            asnRecord.put(SHPG_MODE_CD, modeCode);

        } else {

            rtnmsg = NPAM1503E;
            S21InfoLogOutput.println(rtnmsg);
            return rtnmsg;

        }

        String pddDt = getAsnPlnDelyDt(asnRecord, shpgOrdCustDtlT);

        if (ZYPCommonFunc.hasValue(pddDt)) {

            asnRecord.put(DELIVERY_DATE, pddDt);

        } else {

            asnRecord.put(DELIVERY_DATE, getPddDt(asnRecord));

        }

        return null;
    }

    /**
     * get EtaDaysAot
     * @param asnRecord Map<String, String>
     * @return etaDaysAot(if value is not null), or "0(ZERO)"(if value
     * is null)
     */
    private int getEtaDaysAot(Map<String, Object> asnRecord) {

        Map<String, Object> mdseMap = getMdse(asnRecord);

        if (mdseMap != null && ZYPCommonFunc.hasValue((BigDecimal) mdseMap.get(ETA_DAYS_AOT))) {

            return ((BigDecimal) mdseMap.get(ETA_DAYS_AOT)).intValue();

        }

        return 0;
    }

    /**
     * get MdseTmsg
     * @param asnRecord Map<String, String>
     * @return MDSETMsg
     */
    private Map<String, Object> getMdse(Map<String, Object> asnRecord) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        ssmParam.put(BIND_MDSE_CD, (String) asnRecord.get(MDSE_CD));

        Map<String, Object> mdseResult = (Map<String, Object>) ssmBatchClient.queryObject("searchCsaMdseItemV", ssmParam);

        if (mdseResult == null) {

            return null;
        }

        return mdseResult;
    }

    /**
     * calculate shipping date
     * @param asnRecord Map<String, String>
     * @return shipDt
     */
    private String calcShipDt(Map<String, Object> asnRecord) {

        String asnShipDt = (String) asnRecord.get(SHIP_DT_TM_TS);

        if (asnShipDt.length() > 8) {

            asnShipDt = asnShipDt.substring(0, 8);

        }

        return asnShipDt;
    }

    /**
     * add EtaDaysAot to ShipDt
     * @param asnRecord Map<String, String>
     * @param etaDaysAot String
     * @return addEtaDaysAotToShipDt String
     */
    private String addEtaDaysAotToShipDt(Map<String, Object> asnRecord, int etaDaysAot) {

        String calculatedDate = ZYPDateUtil.addBusinessDay(this.glblCmpyCd, calcShipDt(asnRecord), etaDaysAot);

        if (!ZYPCommonFunc.hasValue(calculatedDate)) {

            throw new S21AbendException(NPAM1504E);
        }

        return calculatedDate;
    }

    /**
     * Map<String, Object>
     * @param asnRecord
     * @return Map<String, Object>
     */
    private Map<String, Object> getShpgOrdCustDtl(Map<String, Object> asnRecord) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        ssmParam.put(BIND_SO_NUM, (String) asnRecord.get(SO_NUM));
        ssmParam.put(BIND_SO_CUST_DATA_TP_CD, SO_CUST_DATA_TP.SHIP_TO);

        Map<String, Object> shpgOrdCustDtl = (Map<String, Object>) ssmBatchClient.queryObject("searchCsaShpgOrdCustDtlV", ssmParam);

        if (shpgOrdCustDtl == null) {

            return shpgOrdCustDtl;

        }

        return shpgOrdCustDtl;
    }

    /**
     * getShpgModeCd
     * @param asnRecord
     * @return
     */
    private String getShpgModeCd(Map<String, Object> asnRecord) {

        String shpgModeCd = (String) asnRecord.get(SHPG_MODE_CD);

        if (!ZYPCommonFunc.hasValue(shpgModeCd)) {

            shpgModeCd = getShpgModeCdFromShpgSvc(asnRecord);

            if (!ZYPCommonFunc.hasValue(shpgModeCd)) {

                shpgModeCd = getShpgModeCdFromShpgWt(asnRecord);

            }

        }

        return shpgModeCd;

    }

    /**
     * Get Shipping Mode Code
     * @param shpgOrdT ShippingOrderTable
     * @return MaxEDITransactionID
     */
    private String getShpgModeCdFromShpgSvc(Map<String, Object> asnRecord) {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        queryParam.put(BIND_SHPG_SVC_LVL_CD, (String) asnRecord.get(SHPG_SVC_LVL_CD));
        queryParam.put(BIND_FRT_CHRG_TO_CD, (String) asnRecord.get(FRT_CHRG_TO_CD));
        queryParam.put(BIND_FRT_CHRG_METH_CD, (String) asnRecord.get(FRT_CHRG_METH_CD));

        return (String) ssmBatchClient.queryObject("getMinShpgModeCd", queryParam);
    }

    /**
     * Get Shipping Mode Code
     * @param shpgOrdT ShippingOrderTable
     * @return MaxEDITransactionID
     */
    private String getShpgModeCdFromShpgWt(Map<String, Object> asnRecord) {

        String shpgModeCd = null;

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        queryParam.put(BIND_FRT_CHRG_TO_CD, (String) asnRecord.get(FRT_CHRG_TO_CD));
        queryParam.put(BIND_FRT_CHRG_METH_CD, (String) asnRecord.get(FRT_CHRG_METH_CD));
        queryParam.put(BIND_SLS_DATE, ZYPDateUtil.getSalesDate(profileService.getGlobalCompanyCode()));

        List<Map<String, Object>> shpgModeCdList = ssmBatchClient.queryObjectList("getShpgModeCdListFromShpgWt", queryParam);

        // QC#28621 mod start
        if (!shpgModeCdList.isEmpty()) {
            Map<String, Object> shpgModeCdMap = shpgModeCdList.get(0);
            shpgModeCd = (String) shpgModeCdMap.get("SHPG_MODE_CD");
            // shpgModeCd = (String) shpgModeCdList.get(0);
        }
        // QC#28621 mod end
        return shpgModeCd;
    }

    /**
     * Get Shipping Mode Code
     * @param shpgOrdT ShippingOrderTable
     * @return MaxEDITransactionID
     */
    private String getLatestWarehouseEstimatedTimeOfArrivalDate(Map<String, Object> asnRecord) {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        queryParam.put(BIND_RWS_REF_NUM, (String) asnRecord.get(THIRD_PTY_INV_NUM));
        queryParam.put(BIND_FROM_LOC_CD, (String) asnRecord.get(VND_CD));
        queryParam.put(BIND_TRX_CD, TRX.PURCHASE_STOCK_IN);
        queryParam.put(BIND_TRX_RSN_CD, TRX_RSN.PURCHASE_STOCK_IN);
        queryParam.put(BIND_INVTY_TRX_DT, (String) asnRecord.get(ACTL_SHIP_DT));
        queryParam.put(BIND_MDSE_CD, (String) asnRecord.get(MDSE_CD));

        List<String> dateList = (List<String>) ssmBatchClient.queryObjectList("getLatestWarehouseEstimatedTimeOfArrivalDate", queryParam);

        if (dateList.size() == 0) {

            return addedEtaDaysAot(asnRecord);

        } else if (dateList.size() == 1) {

            if (ZYPCommonFunc.hasValue(dateList.get(0))) {

                return dateList.get(0);

            } else {

                return addedEtaDaysAot(asnRecord);

            }

        } else {

            return null;

        }

    }

    /**
     * return added value
     * @param asnRecord Map<String, String>
     * @return String
     */
    private String addedEtaDaysAot(Map<String, Object> asnRecord) {

        int etaDaysAot = getEtaDaysAot(asnRecord);

        if (etaDaysAot != 0) {

            return addEtaDaysAotToShipDt(asnRecord, etaDaysAot);
        }

        return calcShipDt(asnRecord);
    }

    /**
     * Get Advanced Shipping Notice Planned Delivery Date
     * @param rs ResultSet
     * @param shpgOrdT ShippingOrderTable
     * @param shpgOrdCustDtlT ShippingOrderCustomerDetailTable
     * @param shpgModeCd ShippingModeCode
     * @return ShippingOrderCustomerDetailTable
     */
    private String getAsnPlnDelyDt(Map<String, Object> asnRecord, Map<String, Object> shpgOrdCustDtlT) {

        String asnShipDt = (String) asnRecord.get(SHIP_DT_TM_TS);

        if (asnShipDt.length() > 8) {

            asnShipDt = asnShipDt.substring(0, 8);

        }

        String asnPlnDelyDt = calculatePdd(asnShipDt, (String) asnRecord.get(WH_CD), (String) shpgOrdCustDtlT.get(ST_CD), (String) shpgOrdCustDtlT.get(POST_CD), (String) asnRecord.get(SHPG_MODE_CD), (String) asnRecord.get(SHPG_SVC_LVL_CD),
                asnShipDt);

        return asnPlnDelyDt;
    }

    /**
     * getPddDt
     * @param asnRecord
     * @return String
     */
    private String getPddDt(Map<String, Object> asnRecord) {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("cusaGlblCmpyCd", this.cusaGlblCmpyCd);
        queryParam.put("shpgPlnNum", (String) asnRecord.get("SHPG_PLN_NUM"));

        String pddDt = (String) ssmBatchClient.queryObject("getPddDt", queryParam);

        return pddDt;

    }

    /**
     * calculatePdd
     * @param glblCmpyCd
     * @param psdDt
     * @param whCd
     * @param shipToStCd
     * @param shipToPostCd
     * @param shpgModeCd
     * @param shpgSvcLvlCd
     * @param salesDate
     * @return
     */
    private String calculatePdd(String psdDt, String whCd, String shipToStCd, String shipToPostCd, String shpgModeCd, String shpgSvcLvlCd, String salesDate) {
        EZDDebugOutput.println(1, "[ calculatePdd ] start", this);

        // QC#53323 Start
        if(!ZYPCommonFunc.hasValue(shipToStCd) ) {
            shipToStCd = DUMMY_ST_CD;
        }
        // QC#53323 End

        String pddDt = null;
        String carrCalCd;

        Map<String, Object> calReln = getCalReln(CAL_SUB_TP.CARRIER_CALENDAR, shpgModeCd);

        if (calReln == null) {

            carrCalCd = this.cusaGlblCmpyCd;

        } else {

            carrCalCd = (String) calReln.get(CAL_TP_CD);
        }

        BigDecimal transportationLt = getTransportationLT(whCd, shipToStCd, shipToPostCd, shpgModeCd, shpgSvcLvlCd, salesDate);

        if (!ZYPCommonFunc.hasValue(transportationLt)) {

            return null;

        }

        EZDDebugOutput.println(1, ">>>>> transportationLt:" + transportationLt, this);

        if (ZYPDateUtil.isBusinessDay(carrCalCd, psdDt)) {

            pddDt = ZYPDateUtil.addBusinessDay(carrCalCd, psdDt, hourToDay(transportationLt).intValue());

        } else {

            pddDt = ZYPDateUtil.addBusinessDay(carrCalCd, psdDt, hourToDay(transportationLt).intValue() + 1);
        }

        EZDDebugOutput.println(1, "[ calculatePdd ] end", this);

        return pddDt;
    }

    /**
     * getCalReln
     * @param glblCmpyCd
     * @param calSubTpCd
     * @param calMultCd
     * @return
     */
    private Map<String, Object> getCalReln(String calSubTpCd, String calMultCd) {

        EZDDebugOutput.println(1, "[ getCalReln ] start", this);

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        ssmParam.put(BIND_CAL_SUB_TP_CD, calSubTpCd);
        ssmParam.put(BIND_CAL_MULT_CD, calMultCd);

        Map<String, Object> calReln = (Map<String, Object>) ssmBatchClient.queryObject("searchCsaCalRelnV", ssmParam);

        EZDDebugOutput.println(1, "[ getCalReln ] end", this);

        return calReln;

    }

    /**
     * getTransportationLT
     * @param whCd
     * @param shipToStCd
     * @param shipToPostCd
     * @param shpgModeCd
     * @param shpgSvcLvlCd
     * @param salesDate
     * @return BigDecimal
     */
    private BigDecimal getTransportationLT(String whCd, String shipToStCd, String shipToPostCd, String shpgModeCd, String shpgSvcLvlCd, String salesDate) {
        EZDDebugOutput.println(1, "[ getTransportationLT ] start", this);

        BigDecimal transportationLt;

        ShpgSvcInfo shpgSvcInfo = getShpgSvcLeadTm(shpgSvcLvlCd);

        if (shpgSvcInfo == null) {
            return null;
        }

        if (SHPG_SVC_TP.GUARANTEED_DAYS_DELIVERY.equals(shpgSvcInfo.getShpgSvcTpCd())) {

            transportationLt = shpgSvcInfo.getDelyLeadAot();

        } else {

            BigDecimal trnspLt = getTrnspLt(whCd, shipToStCd, shipToPostCd, shpgModeCd, salesDate);

            if (trnspLt == null) {

                BigDecimal areaLeadTm = getAreaLeadTm(whCd, shipToStCd, shpgModeCd, salesDate);

                if (areaLeadTm == null) {

                    return null;
                }

                transportationLt = areaLeadTm;

            } else {

                transportationLt = trnspLt;
            }

        }

        EZDDebugOutput.println(1, "[ getTransportationLT ] end", this);
        return transportationLt;
    }

    /**
     * getShpgSvcLeadTm
     * @param glblCmpyCd
     * @param shpgSvcLvlCd
     * @return
     */
    private ShpgSvcInfo getShpgSvcLeadTm(String shpgSvcLvlCd) {

        EZDDebugOutput.println(1, "[ getShpgSvcLeadTm ] start", this);

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        queryParam.put(BIND_SHPG_SVC_LVL_CD, shpgSvcLvlCd);

        List<ShpgSvcInfo> shpgSvcInfoList = (List<ShpgSvcInfo>) this.ssmBatchClient.queryObjectList("getShpgSvcLeadTm", queryParam);

        ShpgSvcInfo shpgSvcInfo;

        if (shpgSvcInfoList.isEmpty()) {

            shpgSvcInfo = null;

        } else {

            shpgSvcInfo = shpgSvcInfoList.get(0);
        }

        EZDDebugOutput.println(1, "[ getShpgSvcLeadTm ] end", this);
        return shpgSvcInfo;

    }

    /**
     * getTrnspLt
     * @param glblCmpyCd
     * @param whCd
     * @param shipToStCd
     * @param shipToPostCd
     * @param shpgModeCd
     * @param salesDate
     * @return
     */
    private BigDecimal getTrnspLt(String whCd, String shipToStCd, String shipToPostCd, String shpgModeCd, String salesDate) {

        EZDDebugOutput.println(1, "[ getTrnspLt ] start", this);

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        queryParam.put(BIND_WH_CD, whCd);
        // QC#53323 Mod Start
//        queryParam.put(BIND_ST_CD, shpgModeCd);
//        queryParam.put(BIND_SHPG_MODE_CD, shipToStCd);
        queryParam.put(BIND_ST_CD, shipToStCd);
        queryParam.put(BIND_SHPG_MODE_CD, shpgModeCd);
        // QC#53323 Mod End
        queryParam.put(BIND_SHIP_TO_POST_CD, shipToPostCd);
        queryParam.put(BIND_SLS_DATE, salesDate);

        List<BigDecimal> trnspLtList = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getTrnspLt", queryParam);

        BigDecimal trnspLt;

        if (trnspLtList.isEmpty()) {

            trnspLt = null;

        } else {

            trnspLt = trnspLtList.get(0);
        }

        EZDDebugOutput.println(1, "[ getTrnspLt ] end", this);

        return trnspLt;

    }

    /**
     * getAreaLeadTm
     * @param glblCmpyCd
     * @param whCd
     * @param shipToStCd
     * @param shpgModeCd
     * @param salesDate
     * @return
     */
    private BigDecimal getAreaLeadTm(String whCd, String shipToStCd, String shpgModeCd, String salesDate) {

        EZDDebugOutput.println(1, "[ getAreaLeadTm ] start", this);

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put(BIND_CUSA_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        queryParam.put(BIND_WH_CD, whCd);
        queryParam.put(BIND_SHPG_MODE_CD, shpgModeCd);
        queryParam.put(BIND_ST_CD, shipToStCd);
        queryParam.put(BIND_SLS_DATE, salesDate);

        List<BigDecimal> areaLeadTmList = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getAreaLeadTm", queryParam);

        BigDecimal areaLeadTm;

        if (areaLeadTmList.isEmpty()) {

            areaLeadTm = null;

        } else {

            areaLeadTm = areaLeadTmList.get(0);
        }

        EZDDebugOutput.println(1, "[ getAreaLeadTm ] end", this);

        return areaLeadTm;
    }

    /**
     * hourToDay
     * @param hourValue
     * @return
     */
    private BigDecimal hourToDay(BigDecimal hourValue) {

        BigDecimal dayValue = hourValue.divide(new BigDecimal(24), 0, BigDecimal.ROUND_UP);

        return dayValue;

    }
}
