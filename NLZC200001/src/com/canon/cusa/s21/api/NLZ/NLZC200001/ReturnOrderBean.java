/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC200001;

import java.math.BigDecimal;

/**
 *<pre>
 * Return RWS
 * return order bean.
 *</pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/16/2015   CITS            T.Tokutomi      Create
 * 07/08/2016   CSAI            Y.Imazu         Update          QC#7755
 * 10/17/2017   CITS            S.Katsuma       Update          SOL#454
 * 12/14/2017   CITS            K.Ogino         Update          QC#22101
 * 05/17/2019   CITS            T.Tokutomi      Update          QC#50392
 *</pre>
 */

class ReturnOrderBean {

    /**
     * CPO_ORD_NUM
     */
    private String cpoOrdNum;

    /**
     * DS_CPO_RTRN_LINE_NUM
     */
    private String dsCpoRtrnLineNum;

    /**
     * DS_CPO_RTRN_LINE_SUB_NUM
     */
    private String dsCpoRtrnLineSubNum;

    /**
     * DS_ORD_POSN_NUM
     */
    private String dsOrdPosnNum;

    /**
     * DS_CPO_CONFIG_PK
     */
    private BigDecimal dsCpoConfigPk;

    /**
     * RTL_WH_CD
     */
    private String rtlWhCd;

    /**
     * RTL_SWH_CD
     */
    private String rtlSwhCd;

    /**
     * INVTY_LOC_CD
     */
    private String invtyLocCd;

    /**
     * SHIP_TO_CUST_CD
     */
    private String shipToCustCd;

    /**
     * SHIP_TO_LOC_NM
     */
    private String shipToLocNm;

    /**
     * SHIP_TO_ADDL_LOC_NM
     */
    private String shipToAddlLocNm;

    /**
     * SHIP_TO_CTY_ADDR
     */
    private String shipToCtyAddr;

    /**
     * SHIP_TO_ST_CD
     */
    private String shipToStCd;

    /**
     * SHIP_TO_CTRY_CD
     */
    private String shipToCtryCd;

    /**
     * SHIP_TO_FIRST_LINE_ADDR
     */
    private String shipToFirstLineAddr;

    /**
     * SHIP_TO_SCD_LINE_ADDR
     */
    private String shipToScdLineAddr;

    /**
     * SHIP_TO_THIRD_LINE_ADDR
     */
    private String shipToThirdLineAddr;

    /**
     * SHIP_TO_FRTH_LINE_ADDR
     */
    private String shipToFrthLineAddr;

    /**
     * SHIP_TO_POST_CD
     */
    private String shipToPostCd;

    /**
     * TRX_CD
     */
    private String trxCd;

    /**
     * TRX_RSN_CD
     */
    private String trxRsnCd;

    /**
     * SVC_CONFIG_MSTR_PK
     */
    private BigDecimal svcConfigMstrPk;

    /**
     * BASE_CMPT_FLG
     */
    private String baseCmptFlg;

    /**
     * STK_STS_CD
     */
    private String stkStsCd;

    /**
     * MDSE_CD
     */
    private String mdseCd;

    /**
     * RQST_PICK_UP_DT
     */
    private String rqstPickUpDt;

    /**
     * THIRD_PTY_DSP_TP_CD
     */
    private String thirdPtyDspTpCd;

    /**
     * SHPG_SER_TAKE_FLG
     */
    private String shpgSerTakeFlg;

    /**
     * RCV_SER_TAKE_FLG
     */
    private String rcvSerTakeFlg;

    /**
     * ADD_SHPG_SVC_LVL_CD
     */
    private String addShpgSvcLvlCd;

    /**
     * ADD_FRT_CHRG_TO_CD
     */
    private String addFrtChrgToCd;

    /**
     * ADD_FRT_CHRG_METH_CD
     */
    private String addFrtChrgMethCd;

    /**
     * DELY_ADDL_CMNT_TXT
     */
    private String delyAddlCmntTxt;

    /**
     * CARR_CD
     */
    private String carrCd;

    /**
     * SHPG_SVC_LVL_CD
     */
    private String shpgSvcLvlCd;

    /**
     * ORD_QTY
     */
    private BigDecimal ordQty;

    /**
     * SER_NUM
     */
    private String serNum;

    /**
     * SELL_TO_CUST_CD
     */
    private String sellToCustCd;

    /**
     * ORD_BOOK_TS
     */
    private String ordBookTs;

    /**
     * DS_ORD_CATG_CD
     */
    private String dsOrdCatgCd;

    // START 2017/10/17 S.Katsuma [Sol#454,ADD]
    /**
     * SELL_TO_FIRST_REF_CMNT_TXT
     */
    private String sellToFirstRefCmntTxt;
    // END 2017/10/17 S.Katsuma [Sol#454,ADD]

    // QC#22101
    private BigDecimal svcMachMstrPk;

    // QC#50392 Add.
    /**
     * RQST_ISTL_DT
     */
    private String rqstIstlDt;

    /**
     * RQST_ISTL_TM
     */
    private String rqstIstlTm;

    /**
     * TECH_MEET_TRUCK_DEF_FLG
     */
    private String techMeetTruckDefFlg;

    /**
     * @return cpoOrdNum
     */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    /**
     * @param cpoOrdNum set cpoOrdNum
     */
    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    }

    /**
     * @return dsCpoRtrnLineNum
     */
    public String getDsCpoRtrnLineNum() {
        return dsCpoRtrnLineNum;
    }

    /**
     * @param dsCpoRtrnLineNum set dsCpoRtrnLineNum
     */
    public void setDsCpoRtrnLineNum(String dsCpoRtrnLineNum) {
        this.dsCpoRtrnLineNum = dsCpoRtrnLineNum;
    }

    /**
     * @return dsCpoRtrnLineSubNum
     */
    public String getDsCpoRtrnLineSubNum() {
        return dsCpoRtrnLineSubNum;
    }

    /**
     * @param dsCpoRtrnLineSubNum set dsCpoRtrnLineSubNum
     */
    public void setDsCpoRtrnLineSubNum(String dsCpoRtrnLineSubNum) {
        this.dsCpoRtrnLineSubNum = dsCpoRtrnLineSubNum;
    }

    /**
     * @return dsOrdPosnNum
     */
    public String getDsOrdPosnNum() {
        return dsOrdPosnNum;
    }

    /**
     * @param dsOrdPosnNum set dsOrdPosnNum
     */
    public void setDsOrdPosnNum(String dsOrdPosnNum) {
        this.dsOrdPosnNum = dsOrdPosnNum;
    }

    /**
     * @return dsCpoConfigPk
     */
    public BigDecimal getDsCpoConfigPk() {
        return dsCpoConfigPk;
    }

    /**
     * @param dsCpoConfigPk set dsCpoConfigPk
     */
    public void setDsCpoConfigPk(BigDecimal dsCpoConfigPk) {
        this.dsCpoConfigPk = dsCpoConfigPk;
    }

    /**
     * @return rtlWhCd
     */
    public String getRtlWhCd() {
        return rtlWhCd;
    }

    /**
     * @param rtlWhCd set rtlWhCd
     */
    public void setRtlWhCd(String rtlWhCd) {
        this.rtlWhCd = rtlWhCd;
    }

    /**
     * @return rtlSwhCd
     */
    public String getRtlSwhCd() {
        return rtlSwhCd;
    }

    /**
     * @param rtlSwhCd set rtlSwhCd
     */
    public void setRtlSwhCd(String rtlSwhCd) {
        this.rtlSwhCd = rtlSwhCd;
    }

    /**
     * @return invtyLocCd
     */
    public String getInvtyLocCd() {
        return invtyLocCd;
    }

    /**
     * @param invtyLocCd set invtyLocCd
     */
    public void setInvtyLocCd(String invtyLocCd) {
        this.invtyLocCd = invtyLocCd;
    }

    /**
     * @return shipToCustCd
     */
    public String getShipToCustCd() {
        return shipToCustCd;
    }

    /**
     * @param shipToCustCd set shipToCustCd
     */
    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    /**
     * @return shipToLocNm
     */
    public String getShipToLocNm() {
        return shipToLocNm;
    }

    /**
     * @param shipToLocNm set shipToLocNm
     */
    public void setShipToLocNm(String shipToLocNm) {
        this.shipToLocNm = shipToLocNm;
    }

    /**
     * @return shipToAddlLocNm
     */
    public String getShipToAddlLocNm() {
        return shipToAddlLocNm;
    }

    /**
     * @param shipToAddlLocNm set shipToAddlLocNm
     */
    public void setShipToAddlLocNm(String shipToAddlLocNm) {
        this.shipToAddlLocNm = shipToAddlLocNm;
    }

    /**
     * @return shipToCtyAddr
     */
    public String getShipToCtyAddr() {
        return shipToCtyAddr;
    }

    /**
     * @param shipToCtyAddr set shipToCtyAddr
     */
    public void setShipToCtyAddr(String shipToCtyAddr) {
        this.shipToCtyAddr = shipToCtyAddr;
    }

    /**
     * @return shipToStCd
     */
    public String getShipToStCd() {
        return shipToStCd;
    }

    /**
     * @param shipToStCd set shipToStCd
     */
    public void setShipToStCd(String shipToStCd) {
        this.shipToStCd = shipToStCd;
    }

    /**
     * @return shipToCtryCd
     */
    public String getShipToCtryCd() {
        return shipToCtryCd;
    }

    /**
     * @param shipToCtryCd set shipToCtryCd
     */
    public void setShipToCtryCd(String shipToCtryCd) {
        this.shipToCtryCd = shipToCtryCd;
    }

    /**
     * @return shipToFirstLineAddr
     */
    public String getShipToFirstLineAddr() {
        return shipToFirstLineAddr;
    }

    /**
     * @param shipToFirstLineAddr set shipToFirstLineAddr
     */
    public void setShipToFirstLineAddr(String shipToFirstLineAddr) {
        this.shipToFirstLineAddr = shipToFirstLineAddr;
    }

    /**
     * @return shipToScdLineAddr
     */
    public String getShipToScdLineAddr() {
        return shipToScdLineAddr;
    }

    /**
     * @param shipToScdLineAddr set shipToScdLineAddr
     */
    public void setShipToScdLineAddr(String shipToScdLineAddr) {
        this.shipToScdLineAddr = shipToScdLineAddr;
    }

    /**
     * @return shipToThirdLineAddr
     */
    public String getShipToThirdLineAddr() {
        return shipToThirdLineAddr;
    }

    /**
     * @param shipToThirdLineAddr set shipToThirdLineAddr
     */
    public void setShipToThirdLineAddr(String shipToThirdLineAddr) {
        this.shipToThirdLineAddr = shipToThirdLineAddr;
    }

    /**
     * @return shipToFrthLineAddr
     */
    public String getShipToFrthLineAddr() {
        return shipToFrthLineAddr;
    }

    /**
     * @param shipToFrthLineAddr set shipToFrthLineAddr
     */
    public void setShipToFrthLineAddr(String shipToFrthLineAddr) {
        this.shipToFrthLineAddr = shipToFrthLineAddr;
    }

    /**
     * @return shipToPostCd
     */
    public String getShipToPostCd() {
        return shipToPostCd;
    }

    /**
     * @param shipToPostCd set shipToPostCd
     */
    public void setShipToPostCd(String shipToPostCd) {
        this.shipToPostCd = shipToPostCd;
    }

    /**
     * @return trxCd
     */
    public String getTrxCd() {
        return trxCd;
    }

    /**
     * @param trxCd set trxCd
     */
    public void setTrxCd(String trxCd) {
        this.trxCd = trxCd;
    }

    /**
     * @return trxRsnCd
     */
    public String getTrxRsnCd() {
        return trxRsnCd;
    }

    /**
     * @param trxRsnCd set trxRsnCd
     */
    public void setTrxRsnCd(String trxRsnCd) {
        this.trxRsnCd = trxRsnCd;
    }

    /**
     * @return svcConfigMstrPk
     */
    public BigDecimal getSvcConfigMstrPk() {
        return svcConfigMstrPk;
    }

    /**
     * @param svcConfigMstrPk set svcConfigMstrPk
     */
    public void setSvcConfigMstrPk(BigDecimal svcConfigMstrPk) {
        this.svcConfigMstrPk = svcConfigMstrPk;
    }

    /**
     * @return baseCmptFlg
     */
    public String getBaseCmptFlg() {
        return baseCmptFlg;
    }

    /**
     * @param baseCmptFlg set baseCmptFlg
     */
    public void setBaseCmptFlg(String baseCmptFlg) {
        this.baseCmptFlg = baseCmptFlg;
    }

    /**
     * @return stkStsCd
     */
    public String getStkStsCd() {
        return stkStsCd;
    }

    /**
     * @param stkStsCd set stkStsCd
     */
    public void setStkStsCd(String stkStsCd) {
        this.stkStsCd = stkStsCd;
    }

    /**
     * @return mdseCd
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * @param mdseCd set mdseCd
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * @return rqstPickUpDt
     */
    public String getRqstPickUpDt() {
        return rqstPickUpDt;
    }

    /**
     * @param rqstPickUpDt set rqstPickUpDt
     */
    public void setRqstPickUpDt(String rqstPickUpDt) {
        this.rqstPickUpDt = rqstPickUpDt;
    }

    /**
     * @param thirdPtyDspTpCd set thirdPtyDspTpCd
     */
    public void setThirdPtyDspTpCd(String thirdPtyDspTpCd) {
        this.thirdPtyDspTpCd = thirdPtyDspTpCd;
    }

    /**
     * @return thirdPtyDspTpCd
     */
    public String getThirdPtyDspTpCd() {
        return thirdPtyDspTpCd;
    }

    /**
     * @return shpgSerTakeFlg
     */
    public String getShpgSerTakeFlg() {
        return shpgSerTakeFlg;
    }

    /**
     * @param shpgSerTakeFlg String
     */
    public void setShpgSerTakeFlg(String shpgSerTakeFlg) {
        this.shpgSerTakeFlg = shpgSerTakeFlg;
    }

    public String getRcvSerTakeFlg() {
        return rcvSerTakeFlg;
    }

    public void setRcvSerTakeFlg(String rcvSerTakeFlg) {
        this.rcvSerTakeFlg = rcvSerTakeFlg;
    }

    /**
     * @return addShpgSvcLvlCd
     */
    public String getAddShpgSvcLvlCd() {
        return addShpgSvcLvlCd;
    }

    /**
     * @param addShpgSvcLvlCd set addShpgSvcLvlCd
     */
    public void setAddShpgSvcLvlCd(String addShpgSvcLvlCd) {
        this.addShpgSvcLvlCd = addShpgSvcLvlCd;
    }

    /**
     * @return addFrtChrgToCd
     */
    public String getAddFrtChrgToCd() {
        return addFrtChrgToCd;
    }

    /**
     * @param addFrtChrgToCd set addFrtChrgToCd
     */
    public void setAddFrtChrgToCd(String addFrtChrgToCd) {
        this.addFrtChrgToCd = addFrtChrgToCd;
    }

    /**
     * @return addFrtChrgMethCd
     */
    public String getAddFrtChrgMethCd() {
        return addFrtChrgMethCd;
    }

    /**
     * @param addFrtChrgMethCd set addFrtChrgMethCd
     */
    public void setAddFrtChrgMethCd(String addFrtChrgMethCd) {
        this.addFrtChrgMethCd = addFrtChrgMethCd;
    }

    /**
     * @return delyAddlCmntTxt
     */
    public String getDelyAddlCmntTxt() {
        return delyAddlCmntTxt;
    }

    /**
     * @param delyAddlCmntTxt set delyAddlCmntTxt
     */
    public void setDelyAddlCmntTxt(String delyAddlCmntTxt) {
        this.delyAddlCmntTxt = delyAddlCmntTxt;
    }

    /**
     * @return carrCd
     */
    public String getCarrCd() {
        return carrCd;
    }

    /**
     * @param carrCd set carrCd
     */
    public void setCarrCd(String carrCd) {
        this.carrCd = carrCd;
    }

    /**
     * @return shpgSvcLvlCd
     */
    public String getShpgSvcLvlCd() {
        return shpgSvcLvlCd;
    }

    /**
     * @param shpgSvcLvlCd set shpgSvcLvlCd
     */
    public void setShpgSvcLvlCd(String shpgSvcLvlCd) {
        this.shpgSvcLvlCd = shpgSvcLvlCd;
    }

    /**
     * @param ordQty set ordQty
     */
    public void setOrdQty(BigDecimal ordQty) {
        this.ordQty = ordQty;
    }

    /**
     * @return ordQty
     */
    public BigDecimal getOrdQty() {
        return ordQty;
    }

    /**
     * @param serNum set serNum
     */
    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    /**
     * @return serNum
     */
    public String getSerNum() {
        return serNum;
    }

    /**
     * @param sellToCustCd set sellToCustCd
     */
    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    /**
     * @return sellToCustCd
     */
    public String getSellToCustCd() {
        return sellToCustCd;
    }

    /**
     * @param ordBookTs set ordBookTs
     */
    public void setOrdBookTs(String ordBookTs) {
        this.ordBookTs = ordBookTs;
    }

    /**
     * @return ordBookTs
     */
    public String getOrdBookTs() {
        return ordBookTs;
    }

    /**
     * @return dsOrdCatgCd
     */
    public String getDsOrdCatgCd() {
        return dsOrdCatgCd;
    }

    /**
     * @param dsOrdCatgCd String
     */
    public void setDsOrdCatgCd(String dsOrdCatgCd) {
        this.dsOrdCatgCd = dsOrdCatgCd;
    }

    // START 2017/10/17 S.Katsuma [Sol#454,ADD]
    /**
     * @return sellToFirstRefCmntTxt
     */
    public String getSellToFirstRefCmntTxt() {
        return sellToFirstRefCmntTxt;
    }

    /**
     * @param sellToFirstRefCmntTxt set sellToFirstRefCmntTxt
     */
    public void setSellToFirstRefCmntTxt(String sellToFirstRefCmntTxt) {
        this.sellToFirstRefCmntTxt = sellToFirstRefCmntTxt;
    }
    // END 2017/10/17 S.Katsuma [Sol#454,ADD]

    // QC#22101
    /**
     * @return svcMachMstrPk
     */
    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }

    /**
     * @param svcMachMstrPk set svcMachMstrPk
     */
    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    }

    /**
     * @param rqstIstlDt
     */
    public void setRqstIstlDt(String rqstIstlDt) {
        this.rqstIstlDt = rqstIstlDt;
    }

    /**
     * @return rqstIstlDt
     */
    public String getRqstIstlDt() {
        return rqstIstlDt;
    }

    /**
     * @param rqstIstlTm
     */
    public void setRqstIstlTm(String rqstIstlTm) {
        this.rqstIstlTm = rqstIstlTm;
    }

    /**
     * @return rqstIstlTm
     */
    public String getRqstIstlTm() {
        return rqstIstlTm;
    }

    /**
     * @param techMeetTruckDefFlg
     */
    public void setTechMeetTruckDefFlg(String techMeetTruckDefFlg) {
        this.techMeetTruckDefFlg = techMeetTruckDefFlg;
    }

    /**
     * @return techMeetTruckDefFlg
     */
    public String getTechMeetTruckDefFlg() {
        return techMeetTruckDefFlg;
    }

}
