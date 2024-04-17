package com.canon.cusa.s21.batch.NWA.NWAB262001;

import business.db.DS_CPO_CTAC_PSNTMsgArray;

public class OrdConfBean {

    private String cpoOrdNum;

    private String sellToCustCd;

    private String dsAcctCustNm;

    private String lineBizTpCd;

    // START 2018/04/11 K.Kitachi [QC#11642, ADD]
    private String cpoSrcTpCd;
    // END 2018/04/11 K.Kitachi [QC#11642, ADD]

    // 2019/07/26 QC#51252 Add Start
    private String dsOrdCatgCd;
    private String dsOrdTpCd;
    // 2019/07/26 QC#51252 Add End 

    private DS_CPO_CTAC_PSNTMsgArray dsCpoCtacPsnTMsgArray;

    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    }

    public String getSellToCustCd() {
        return sellToCustCd;
    }

    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    public String getDsAcctCustNm() {
        return dsAcctCustNm;
    }

    public void setDsAcctCustNm(String dsAcctCustNm) {
        this.dsAcctCustNm = dsAcctCustNm;
    }

    public String getLineBizTpCd() {
        return lineBizTpCd;
    }

    public void setLineBizTpCd(String lineBizTpCd) {
        this.lineBizTpCd = lineBizTpCd;
    }

    // START 2018/04/11 K.Kitachi [QC#11642, ADD]
    public String getCpoSrcTpCd() {
        return cpoSrcTpCd;
    }

    public void setCpoSrcTpCd(String cpoSrcTpCd) {
        this.cpoSrcTpCd = cpoSrcTpCd;
    }
    // END 2018/04/11 K.Kitachi [QC#11642, ADD]

    public DS_CPO_CTAC_PSNTMsgArray getDsCpoCtacPsnTMsgArray() {
        return dsCpoCtacPsnTMsgArray;
    }

    public void setDsCpoCtacPsnTMsgArray(DS_CPO_CTAC_PSNTMsgArray dsCpoCtacPsnTMsgArray) {
        this.dsCpoCtacPsnTMsgArray = dsCpoCtacPsnTMsgArray;
    }

    // 2019/07/26 QC#51252 Add Start
    public String getDsOrdCatgCd() {
        return dsOrdCatgCd;
    }

    public void setDsOrdCatgCd(String dsOrdCatgCd) {
        this.dsOrdCatgCd = dsOrdCatgCd;
    }

    public String getDsOrdTpCd() {
        return dsOrdTpCd;
    }

    public void setDsOrdTpCd(String dsOrdTpCd) {
        this.dsOrdTpCd = dsOrdTpCd;
    }
    // 2019/07/26 QC#51252 Add End

}
