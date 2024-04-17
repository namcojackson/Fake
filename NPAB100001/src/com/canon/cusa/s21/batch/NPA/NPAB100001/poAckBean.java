/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB100001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <pre>
 * PO ACK<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------

 * 01/17/2016   CITS            T.Hakodate      Created         WDS NA V4.0
 * </pre>
 */

public class poAckBean implements Serializable {

    /**
     * Constructor
     */
    public poAckBean() {

    }

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    private String apiReturnedPoOrdNum;

    private String apiReturnedPoOrdDtlLineNum;

    private String apiReturnedMdseCd;

    private String poMdseCmpsnTpCd;

    private String changMdseCd;

    private String changMdseNm;

    private String prntVndCd;

    private String splyItemNum;

    private String vndCd;

    private String origVndMdseCd;

    private BigDecimal poAckDtlWrkPk;

    public String getApiReturnedPoOrdNum() {
        return apiReturnedPoOrdNum;
    }

    public void setApiReturnedPoOrdNum(String apiReturnedPoOrdNum) {
        this.apiReturnedPoOrdNum = apiReturnedPoOrdNum;
    }

    public String getApiReturnedPoOrdDtlLineNum() {
        return apiReturnedPoOrdDtlLineNum;
    }

    public void setApiReturnedPoOrdDtlLineNum(String apiReturnedPoOrdDtlLineNum) {
        this.apiReturnedPoOrdDtlLineNum = apiReturnedPoOrdDtlLineNum;
    }

    public String getPoMdseCmpsnTpCd() {
        return poMdseCmpsnTpCd;
    }

    public void setPoMdseCmpsnTpCd(String poMdseCmpsnTpCd) {
        this.poMdseCmpsnTpCd = poMdseCmpsnTpCd;
    }

    public String getChangMdseCd() {
        return changMdseCd;
    }

    public void setChangMdseCd(String changMdseCd) {
        this.changMdseCd = changMdseCd;
    }

    public String getChangMdseNm() {
        return changMdseNm;
    }

    public void setChangMdseNm(String changMdseNm) {
        this.changMdseNm = changMdseNm;
    }

    public BigDecimal getPoAckDtlWrkPk() {
        return poAckDtlWrkPk;
    }

    public void setPoAckDtlWrkPk(BigDecimal poAckDtlWrkPk) {
        this.poAckDtlWrkPk = poAckDtlWrkPk;
    }

    public String getPrntVndCd() {
        return prntVndCd;
    }

    public void setPrntVndCd(String prntVndCd) {
        this.prntVndCd = prntVndCd;
    }

    public String getSplyItemNum() {
        return splyItemNum;
    }

    public void setSplyItemNum(String splyItemNum) {
        this.splyItemNum = splyItemNum;
    }

    public String getVndCd() {
        return vndCd;
    }

    public void setVndCd(String vndCd) {
        this.vndCd = vndCd;
    }

    public String getOrigVndMdseCd() {
        return origVndMdseCd;
    }

    public void setOrigVndMdseCd(String origVndMdseCd) {
        this.origVndMdseCd = origVndMdseCd;
    }

    public String getApiReturnedMdseCd() {
        return apiReturnedMdseCd;
    }

    public void setApiReturnedMdseCd(String apiReturnedMdseCd) {
        this.apiReturnedMdseCd = apiReturnedMdseCd;
    }

}
