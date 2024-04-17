/**
 * <Pre>Copyright (c) 2016-2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC226001;

import java.math.BigDecimal;

/** <pre>
 * ExpendMdseBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/17/2016   Fujitsu         M.Hara          Create          N/A
 * 02/05/2018   Fujitsu         T.Aoi           Update          QC#23329(Sol#387)
 * </pre>
 */
public class ExpendMdseBean {

    private final IImportBean dsImptLineBean;

    private final ExpendMdseBean parentBean;
    private String mdseTpCd;
    private String mdseCd;
    private String childMdseCd;
    private String childOrdTakeMdseCd;

    private BigDecimal childMdseQty;
    private BigDecimal calcMdseQty;
    private BigDecimal calcUomQty;

    private String instlBaseCtrlFlg;
    private String mdseTpCtxTpCd;

    private String lineNum;
    private String baseCmptFlg;
    private String dplyLineRefNum;

    private BigDecimal dsCpoLineNum;
    private BigDecimal dsCpoLineSubNum;

    private String cpoDtlLineNum;
    private String cpoDtlLineSubNum;

    private String refCpoDtlLineNum;
    private String refCpoDtlLineSubNum;

    private boolean isSpuersession;
    private BigDecimal ediPoAckDtlPk;

    private String origCpoDtlLineNum;
    private String origCpoDtlLineSubNum;
    private String serNum;

    // 2018/02/05 QC#22329 Add Start
    private boolean softwarePrnt;
    // 2018/02/05 QC#22329 Add End

    public ExpendMdseBean(IImportBean lineBean, ExpendMdseBean parent) {
        dsImptLineBean = lineBean;
        parentBean = parent;
        isSpuersession = false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("mdseTpCd:%s\n", this.mdseTpCd));
        sb.append(String.format("mdseCd:%s\n", this));
        sb.append(String.format("childMdseCd:%s\n", this.childMdseCd));
        sb.append(String.format("childOrdTakeMdseCd:%s\n", this.childOrdTakeMdseCd));

        sb.append(String.format("childMdseQty:%.0f\n", this.childMdseQty));
        sb.append(String.format("calcMdseQty:%.0f\n", this.calcMdseQty));
        sb.append(String.format("calcUomQty:%.0f\n", this.calcUomQty));

        sb.append(String.format("instlBaseCtrlFlg:%s\n", this.instlBaseCtrlFlg));
        sb.append(String.format("mdseTpCtxTpCd:%s\n", this.mdseTpCtxTpCd));
        sb.append(String.format("lineNum:%s\n", this.lineNum));
        sb.append(String.format("baseCmptFlg:%s\n", this.baseCmptFlg));
        sb.append(String.format("ordSrcRefLineNum:%s\n", this.dplyLineRefNum));
        sb.append(String.format("dsCpoLineNum:%s\n", this.dsCpoLineNum));
        sb.append(String.format("dsCpoLineSubNum:%s\n", this.dsCpoLineSubNum));
        sb.append(String.format("cpoDtlLineNum:%s\n", this.cpoDtlLineNum));
        sb.append(String.format("cpoDtlLineSubNum:%s\n", this.cpoDtlLineSubNum));
        sb.append(String.format("refCpoDtlLineNum:%s\n", this.refCpoDtlLineNum));
        sb.append(String.format("refCpoDtlLineSubNum:%s\n", this.refCpoDtlLineSubNum));
        sb.append(String.format("isSpuersession:%b\n", this.isSpuersession));
        sb.append(String.format("ediPoAckDtlPk:%.0f\n", this.ediPoAckDtlPk));

        return sb.toString();
    }

    public ExpendMdseBean getParentBean() {
        return parentBean;
    }

    public DsImptLineBean getDsImptLineBean() {
        return (DsImptLineBean) dsImptLineBean;
    }

    public DsImptRtnLineBean getDsImptRtnLineBean() {
        return (DsImptRtnLineBean) dsImptLineBean;
    }

    public String getMdseTpCd() {
        return mdseTpCd;
    }

    public void setMdseTpCd(String mdseTpCd) {
        this.mdseTpCd = mdseTpCd;
    }

    public String getMdseCd() {
        return mdseCd;
    }

    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    public String getChildMdseCd() {
        return childMdseCd;
    }

    public void setChildMdseCd(String childMdseCd) {
        this.childMdseCd = childMdseCd;
    }

    public String getChildOrdTakeMdseCd() {
        return childOrdTakeMdseCd;
    }

    public void setChildOrdTakeMdseCd(String childOrdTakeMdseCd) {
        this.childOrdTakeMdseCd = childOrdTakeMdseCd;
    }

    public BigDecimal getChildMdseQty() {
        return childMdseQty;
    }

    public void setChildMdseQty(BigDecimal childMdseQty) {
        this.childMdseQty = childMdseQty;
    }

    public BigDecimal getCalcMdseQty() {
        return calcMdseQty;
    }

    public void setCalcMdseQty(BigDecimal calcMdseQty) {
        this.calcMdseQty = calcMdseQty;
    }

    public BigDecimal getCalcUomQty() {
        return calcUomQty;
    }

    public void setCalcUomQty(BigDecimal calcUomQty) {
        this.calcUomQty = calcUomQty;
    }

    public String getInstlBaseCtrlFlg() {
        return instlBaseCtrlFlg;
    }

    public void setInstlBaseCtrlFlg(String instlBaseCtrlFlg) {
        this.instlBaseCtrlFlg = instlBaseCtrlFlg;
    }

    public String getMdseTpCtxTpCd() {
        return mdseTpCtxTpCd;
    }

    public void setMdseTpCtxTpCd(String mdseTpCtxTpCd) {
        this.mdseTpCtxTpCd = mdseTpCtxTpCd;
    }

    public String getLineNum() {
        return lineNum;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    public String getBaseCmptFlg() {
        return baseCmptFlg;
    }

    public void setBaseCmptFlg(String baseCmptFlg) {
        this.baseCmptFlg = baseCmptFlg;
    }

    public String getDplyLineRefNum() {
        return dplyLineRefNum;
    }

    public void setDplyLineRefNum(String dplyLineRefNum) {
        this.dplyLineRefNum = dplyLineRefNum;
    }

    public BigDecimal getDsCpoLineNum() {
        return dsCpoLineNum;
    }

    public void setDsCpoLineNum(BigDecimal dsCpoLineNum) {
        this.dsCpoLineNum = dsCpoLineNum;
    }

    public BigDecimal getDsCpoLineSubNum() {
        return dsCpoLineSubNum;
    }

    public void setDsCpoLineSubNum(BigDecimal dsCpoLineSubNum) {
        this.dsCpoLineSubNum = dsCpoLineSubNum;
    }

    public String getCpoDtlLineNum() {
        return cpoDtlLineNum;
    }

    public void setCpoDtlLineNum(String cpoDtlLineNum) {
        this.cpoDtlLineNum = cpoDtlLineNum;
    }

    public String getCpoDtlLineSubNum() {
        return cpoDtlLineSubNum;
    }

    public void setCpoDtlLineSubNum(String cpoDtlLineSubNum) {
        this.cpoDtlLineSubNum = cpoDtlLineSubNum;
    }

    public String getRefCpoDtlLineNum() {
        return refCpoDtlLineNum;
    }

    public void setRefCpoDtlLineNum(String refCpoDtlLineNum) {
        this.refCpoDtlLineNum = refCpoDtlLineNum;
    }

    public String getRefCpoDtlLineSubNum() {
        return refCpoDtlLineSubNum;
    }

    public void setRefCpoDtlLineSubNum(String refCpoDtlLineSubNum) {
        this.refCpoDtlLineSubNum = refCpoDtlLineSubNum;
    }

    public boolean isSpuersession() {
        return isSpuersession;
    }

    public void setSpuersession(boolean isSpuersession) {
        this.isSpuersession = isSpuersession;
    }

    public BigDecimal getEdiPoAckDtlPk() {
        return ediPoAckDtlPk;
    }

    public void setEdiPoAckDtlPk(BigDecimal ediPoAckDtlPk) {
        this.ediPoAckDtlPk = ediPoAckDtlPk;
    }

    public String getOrigCpoDtlLineNum() {
        return origCpoDtlLineNum;
    }

    public void setOrigCpoDtlLineNum(String origCpoDtlLineNum) {
        this.origCpoDtlLineNum = origCpoDtlLineNum;
    }

    public String getOrigCpoDtlLineSubNum() {
        return origCpoDtlLineSubNum;
    }

    public void setOrigCpoDtlLineSubNum(String origCpoDtlLineSubNum) {
        this.origCpoDtlLineSubNum = origCpoDtlLineSubNum;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public void setSoftwarePrnt(boolean softwarePrnt) {
        this.softwarePrnt = softwarePrnt;
    }

    public boolean getSoftwarePrnt() {
        return softwarePrnt;
    }

}
