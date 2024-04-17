/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC134001;

import java.io.Serializable;

/**
 * <pre>
 * Handling Direct CPO Creation API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/15/2015   CITS            T.Hakodate      Create          N/A
 *</pre>
 */
public class CpoCreationBean implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    private String xxModeCd;

    private String slsDt;

    private String cpoOrdTpCd;

    private String cpoSrcTpCd;

    private String sysSrcCd;

    private String custIssPoNum;

    private String custIssPoDt;

    private String cpoOrdNum;

    private String sellToFirstRefCmntTxt;

    private String sellToScdRefCmntTxt;

    private String xxLineList;

    private String ediNum;

    private String ediSubNum;

    private String cpoDtlLineNum1;

    private String cpoDtlLineSubNum1;

    private String mdseCd1;

    private String custMdseCd;

    private String ordQty;

    private String invtyLocCd;

    private String custUomCd;

    private String rddDt;

    private String shpgSvcLvlCd;

    private String frtChrgMethCd;

    private String frtChrgToCd;

    private String dropShipFlg;

    private String shipToCustCd;

    private String shipToLocNm;

    private String shipToAddlLocNm;

    private String shipToFirstLineAddr;

    private String shipToScdLineAddr;

    private String shipToCtyAddr;

    private String shipToStCd;

    private String shipToPostCd;

    private String shipToCtryCd;

    private String shipToCntyNm;

    private String carrCd;

    private String carrAcctNum;

    private String shipCpltCd;

    private String xxOrdMsgList;

    private String txtTpCd1;

    private String msgTxtInfoTxt1;

    private String txtTpCd2;

    private String msgTxtInfoTxt2;

    private String txtTpCd3;

    private String msgTxtInfoTxt3;

    private String txtTpCd4;

    private String msgTxtInfoTxt4;

    private String xxLbReqInfoList;

    private String cpoDtlLineNum2;

    private String cpoDtlLineSubNum2;

    private String mdseCd2;

    private String vndDropPoGrpLineNum;

    private String lbReqInfoTxt;

    /**
     * @return glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * @param glblCmpyCd  glblCmpyCd
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * @return xxModeCd
     */
    public String getXxModeCd() {
        return xxModeCd;
    }

    /**
     * @param xxModeCd  xxModeCd
     */
    public void setXxModeCd(String xxModeCd) {
        this.xxModeCd = xxModeCd;
    }

    /**
     * @return slsDt
     */
    public String getSlsDt() {
        return slsDt;
    }

    /**
     * @param slsDt  slsDt
     */
    public void setSlsDt(String slsDt) {
        this.slsDt = slsDt;
    }

    /**
     * @return cpoOrdTpCd
     */
    public String getCpoOrdTpCd() {
        return cpoOrdTpCd;
    }

    /**
     * @param cpoOrdTpCd  cpoOrdTpCd
     */
    public void setCpoOrdTpCd(String cpoOrdTpCd) {
        this.cpoOrdTpCd = cpoOrdTpCd;
    }

    /**
     * @return cpoSrcTpCd
     */
    public String getCpoSrcTpCd() {
        return cpoSrcTpCd;
    }

    /**
     * @param cpoSrcTpCd  cpoSrcTpCd
     */
    public void setCpoSrcTpCd(String cpoSrcTpCd) {
        this.cpoSrcTpCd = cpoSrcTpCd;
    }

    /**
     * @return sysSrcCd
     */
    public String getSysSrcCd() {
        return sysSrcCd;
    }

    /**
     * @param sysSrcCd  sysSrcCd
     */
    public void setSysSrcCd(String sysSrcCd) {
        this.sysSrcCd = sysSrcCd;
    }

    /**
     * @return custIssPoNum
     */
    public String getCustIssPoNum() {
        return custIssPoNum;
    }

    /**
     * @param custIssPoNum  custIssPoNum
     */
    public void setCustIssPoNum(String custIssPoNum) {
        this.custIssPoNum = custIssPoNum;
    }

    /**
     * @return custIssPoDt
     */
    public String getCustIssPoDt() {
        return custIssPoDt;
    }

    /**
     * @param custIssPoDt  custIssPoDt
     */
    public void setCustIssPoDt(String custIssPoDt) {
        this.custIssPoDt = custIssPoDt;
    }

    /**
     * @return cpoOrdNum
     */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    /**
     * @param cpoOrdNum  cpoOrdNum
     */
    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    }

    /**
     * @return sellToFirstRefCmntTxt
     */
    public String getSellToFirstRefCmntTxt() {
        return sellToFirstRefCmntTxt;
    }

    /**
     * @param sellToFirstRefCmntTxt  sellToFirstRefCmntTxt
     */
    public void setSellToFirstRefCmntTxt(String sellToFirstRefCmntTxt) {
        this.sellToFirstRefCmntTxt = sellToFirstRefCmntTxt;
    }

    /**
     * @return sellToScdRefCmntTxt
     */
    public String getSellToScdRefCmntTxt() {
        return sellToScdRefCmntTxt;
    }

    /**
     * @param sellToScdRefCmntTxt  sellToScdRefCmntTxt
     */
    public void setSellToScdRefCmntTxt(String sellToScdRefCmntTxt) {
        this.sellToScdRefCmntTxt = sellToScdRefCmntTxt;
    }

    /**
     * @return xxLineList
     */
    public String getXxLineList() {
        return xxLineList;
    }

    /**
     * @param xxLineList  xxLineList
     */
    public void setXxLineList(String xxLineList) {
        this.xxLineList = xxLineList;
    }

    /**
     * @return ediNum
     */
    public String getEdiNum() {
        return ediNum;
    }

    /**
     * @param ediNum  ediNum
     */
    public void setEdiNum(String ediNum) {
        this.ediNum = ediNum;
    }

    /**
     * @return ediSubNum
     */
    public String getEdiSubNum() {
        return ediSubNum;
    }

    /**
     * @param ediSubNum  ediSubNum
     */
    public void setEdiSubNum(String ediSubNum) {
        this.ediSubNum = ediSubNum;
    }

    /**
     * @return cpoDtlLineNum1
     */
    public String getCpoDtlLineNum1() {
        return cpoDtlLineNum1;
    }

    /**
     * @param cpoDtlLineNum1  cpoDtlLineNum1
     */
    public void setCpoDtlLineNum1(String cpoDtlLineNum1) {
        this.cpoDtlLineNum1 = cpoDtlLineNum1;
    }

    /**
     * @return cpoDtlLineSubNum1
     */
    public String getCpoDtlLineSubNum1() {
        return cpoDtlLineSubNum1;
    }

    /**
     * @param cpoDtlLineSubNum1  cpoDtlLineSubNum1
     */
    public void setCpoDtlLineSubNum1(String cpoDtlLineSubNum1) {
        this.cpoDtlLineSubNum1 = cpoDtlLineSubNum1;
    }

    /**
     * @return mdseCd1
     */
    public String getMdseCd1() {
        return mdseCd1;
    }

    /**
     * @param mdseCd1  mdseCd1
     */
    public void setMdseCd1(String mdseCd1) {
        this.mdseCd1 = mdseCd1;
    }

    /**
     * @return custMdseCd
     */
    public String getCustMdseCd() {
        return custMdseCd;
    }

    /**
     * @param custMdseCd  custMdseCd
     */
    public void setCustMdseCd(String custMdseCd) {
        this.custMdseCd = custMdseCd;
    }

    /**
     * @return ordQty
     */
    public String getOrdQty() {
        return ordQty;
    }

    /**
     * @param ordQty  ordQty
     */
    public void setOrdQty(String ordQty) {
        this.ordQty = ordQty;
    }

    /**
     * @return invtyLocCd
     */
    public String getInvtyLocCd() {
        return invtyLocCd;
    }

    /**
     * @param invtyLocCd  invtyLocCd
     */
    public void setInvtyLocCd(String invtyLocCd) {
        this.invtyLocCd = invtyLocCd;
    }

    /**
     * @return custUomCd
     */
    public String getCustUomCd() {
        return custUomCd;
    }

    /**
     * @param custUomCd  custUomCd
     */
    public void setCustUomCd(String custUomCd) {
        this.custUomCd = custUomCd;
    }

    /**
     * @return rddDt
     */
    public String getRddDt() {
        return rddDt;
    }

    /**
     * @param rddDt  rddDt
     */
    public void setRddDt(String rddDt) {
        this.rddDt = rddDt;
    }

    /**
     * @return shpgSvcLvlCd
     */
    public String getShpgSvcLvlCd() {
        return shpgSvcLvlCd;
    }

    /**
     * @param shpgSvcLvlCd  shpgSvcLvlCd
     */
    public void setShpgSvcLvlCd(String shpgSvcLvlCd) {
        this.shpgSvcLvlCd = shpgSvcLvlCd;
    }

    /**
     * @return frtChrgMethCd
     */
    public String getFrtChrgMethCd() {
        return frtChrgMethCd;
    }

    /**
     * @param frtChrgMethCd  frtChrgMethCd
     */
    public void setFrtChrgMethCd(String frtChrgMethCd) {
        this.frtChrgMethCd = frtChrgMethCd;
    }

    /**
     * @return frtChrgToCd
     */
    public String getFrtChrgToCd() {
        return frtChrgToCd;
    }

    /**
     * @param frtChrgToCd  frtChrgToCd
     */
    public void setFrtChrgToCd(String frtChrgToCd) {
        this.frtChrgToCd = frtChrgToCd;
    }

    /**
     * @return dropShipFlg
     */
    public String getDropShipFlg() {
        return dropShipFlg;
    }

    /**
     * @param dropShipFlg  dropShipFlg
     */
    public void setDropShipFlg(String dropShipFlg) {
        this.dropShipFlg = dropShipFlg;
    }

    /**
     * @return shipToCustCd
     */
    public String getShipToCustCd() {
        return shipToCustCd;
    }

    /**
     * @param shipToCustCd  shipToCustCd
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
     * @param shipToLocNm  shipToLocNm
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
     * @param shipToAddlLocNm  shipToAddlLocNm
     */
    public void setShipToAddlLocNm(String shipToAddlLocNm) {
        this.shipToAddlLocNm = shipToAddlLocNm;
    }

    /**
     * @return shipToFirstLineAddr
     */
    public String getShipToFirstLineAddr() {
        return shipToFirstLineAddr;
    }

    /**
     * @param shipToFirstLineAddr  shipToFirstLineAddr
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
     * @param shipToScdLineAddr  shipToScdLineAddr
     */
    public void setShipToScdLineAddr(String shipToScdLineAddr) {
        this.shipToScdLineAddr = shipToScdLineAddr;
    }

    /**
     * @return shipToCtyAddr
     */
    public String getShipToCtyAddr() {
        return shipToCtyAddr;
    }

    /**
     * @param shipToCtyAddr  shipToCtyAddr
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
     * @param shipToStCd  shipToStCd
     */
    public void setShipToStCd(String shipToStCd) {
        this.shipToStCd = shipToStCd;
    }

    /**
     * @return shipToPostCd
     */
    public String getShipToPostCd() {
        return shipToPostCd;
    }

    /**
     * @param shipToPostCd  shipToPostCd
     */
    public void setShipToPostCd(String shipToPostCd) {
        this.shipToPostCd = shipToPostCd;
    }

    /**
     * @return shipToCtryCd
     */
    public String getShipToCtryCd() {
        return shipToCtryCd;
    }

    /**
     * @param shipToCtryCd  shipToCtryCd
     */
    public void setShipToCtryCd(String shipToCtryCd) {
        this.shipToCtryCd = shipToCtryCd;
    }

    /**
     * @return shipToCntyNm
     */
    public String getShipToCntyNm() {
        return shipToCntyNm;
    }

    /**
     * @param shipToCntyNm  shipToCntyNm
     */
    public void setShipToCntyNm(String shipToCntyNm) {
        this.shipToCntyNm = shipToCntyNm;
    }

    /**
     * @return carrCd
     */
    public String getCarrCd() {
        return carrCd;
    }

    /**
     * @param carrCd  carrCd
     */
    public void setCarrCd(String carrCd) {
        this.carrCd = carrCd;
    }

    /**
     * @return carrAcctNum
     */
    public String getCarrAcctNum() {
        return carrAcctNum;
    }

    /**
     * @param carrAcctNum  carrAcctNum
     */
    public void setCarrAcctNum(String carrAcctNum) {
        this.carrAcctNum = carrAcctNum;
    }

    /**
     * @return shipCpltCd
     */
    public String getShipCpltCd() {
        return shipCpltCd;
    }

    /**
     * @param shipCpltCd  shipCpltCd
     */
    public void setShipCpltCd(String shipCpltCd) {
        this.shipCpltCd = shipCpltCd;
    }

    /**
     * @return xxOrdMsgList
     */
    public String getXxOrdMsgList() {
        return xxOrdMsgList;
    }

    /**
     * @param xxOrdMsgList  xxOrdMsgList
     */
    public void setXxOrdMsgList(String xxOrdMsgList) {
        this.xxOrdMsgList = xxOrdMsgList;
    }

    /**
     * @return txtTpCd1
     */
    public String getTxtTpCd1() {
        return txtTpCd1;
    }

    /**
     * @param txtTpCd1  txtTpCd1
     */
    public void setTxtTpCd1(String txtTpCd1) {
        this.txtTpCd1 = txtTpCd1;
    }

    /**
     * @return msgTxtInfoTxt1
     */
    public String getMsgTxtInfoTxt1() {
        return msgTxtInfoTxt1;
    }

    /**
     * @param msgTxtInfoTxt1  msgTxtInfoTxt1
     */
    public void setMsgTxtInfoTxt1(String msgTxtInfoTxt1) {
        this.msgTxtInfoTxt1 = msgTxtInfoTxt1;
    }

    /**
     * @return txtTpCd2
     */
    public String getTxtTpCd2() {
        return txtTpCd2;
    }

    /**
     * @param txtTpCd2  txtTpCd2
     */
    public void setTxtTpCd2(String txtTpCd2) {
        this.txtTpCd2 = txtTpCd2;
    }

    /**
     * @return msgTxtInfoTxt2
     */
    public String getMsgTxtInfoTxt2() {
        return msgTxtInfoTxt2;
    }

    /**
     * @param msgTxtInfoTxt2  msgTxtInfoTxt2
     */
    public void setMsgTxtInfoTxt2(String msgTxtInfoTxt2) {
        this.msgTxtInfoTxt2 = msgTxtInfoTxt2;
    }

    /**
     * @return txtTpCd3
     */
    public String getTxtTpCd3() {
        return txtTpCd3;
    }

    /**
     * @param txtTpCd3  txtTpCd3
     */
    public void setTxtTpCd3(String txtTpCd3) {
        this.txtTpCd3 = txtTpCd3;
    }

    /**
     * @return msgTxtInfoTxt3
     */
    public String getMsgTxtInfoTxt3() {
        return msgTxtInfoTxt3;
    }

    /**
     * @param msgTxtInfoTxt3  msgTxtInfoTxt3
     */
    public void setMsgTxtInfoTxt3(String msgTxtInfoTxt3) {
        this.msgTxtInfoTxt3 = msgTxtInfoTxt3;
    }

    /**
     * @return txtTpCd4
     */
    public String getTxtTpCd4() {
        return txtTpCd4;
    }

    /**
     * @param txtTpCd4  txtTpCd4
     */
    public void setTxtTpCd4(String txtTpCd4) {
        this.txtTpCd4 = txtTpCd4;
    }

    /**
     * @return msgTxtInfoTxt4
     */
    public String getMsgTxtInfoTxt4() {
        return msgTxtInfoTxt4;
    }

    /**
     * @param msgTxtInfoTxt4  msgTxtInfoTxt4
     */
    public void setMsgTxtInfoTxt4(String msgTxtInfoTxt4) {
        this.msgTxtInfoTxt4 = msgTxtInfoTxt4;
    }

    /**
     * @return xxLbReqInfoList
     */
    public String getXxLbReqInfoList() {
        return xxLbReqInfoList;
    }

    /**
     * @param xxLbReqInfoList  xxLbReqInfoList
     */
    public void setXxLbReqInfoList(String xxLbReqInfoList) {
        this.xxLbReqInfoList = xxLbReqInfoList;
    }

    /**
     * @return cpoDtlLineNum2
     */
    public String getCpoDtlLineNum2() {
        return cpoDtlLineNum2;
    }

    /**
     * @param cpoDtlLineNum2  cpoDtlLineNum2
     */
    public void setCpoDtlLineNum2(String cpoDtlLineNum2) {
        this.cpoDtlLineNum2 = cpoDtlLineNum2;
    }

    /**
     * @return cpoDtlLineSubNum2
     */
    public String getCpoDtlLineSubNum2() {
        return cpoDtlLineSubNum2;
    }

    /**
     * @param cpoDtlLineSubNum2  cpoDtlLineSubNum2
     */
    public void setCpoDtlLineSubNum2(String cpoDtlLineSubNum2) {
        this.cpoDtlLineSubNum2 = cpoDtlLineSubNum2;
    }

    /**
     * @return mdseCd2
     */
    public String getMdseCd2() {
        return mdseCd2;
    }

    /**
     * @param mdseCd2  mdseCd2
     */
    public void setMdseCd2(String mdseCd2) {
        this.mdseCd2 = mdseCd2;
    }

    /**
     * @return vndDropPoGrpLineNum
     */
    public String getVndDropPoGrpLineNum() {
        return vndDropPoGrpLineNum;
    }

    /**
     * @param vndDropPoGrpLineNum  vndDropPoGrpLineNum
     */
    public void setVndDropPoGrpLineNum(String vndDropPoGrpLineNum) {
        this.vndDropPoGrpLineNum = vndDropPoGrpLineNum;
    }

    /**
     * @return lbReqInfoTxt
     */
    public String getLbReqInfoTxt() {
        return lbReqInfoTxt;
    }

    /**
     * @param lbReqInfoTxt  lbReqInfoTxt
     */
    public void setLbReqInfoTxt(String lbReqInfoTxt) {
        this.lbReqInfoTxt = lbReqInfoTxt;
    }

    /**
     * @return serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
