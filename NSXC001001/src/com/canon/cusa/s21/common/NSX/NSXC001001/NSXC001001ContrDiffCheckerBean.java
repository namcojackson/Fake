package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.util.List;

import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsg;
import business.db.CONTR_XS_COPYTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BR_ALLOCTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_SEG_ALLOCTMsg;

/**
 * <pre>
 * Contract difference checker bean
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/13/2015   Hitachi         K.Yamada        Create          N/A
 *</pre>
 */
public class NSXC001001ContrDiffCheckerBean {

    private DS_CONTRTMsg dsContr;

    private List<DS_CONTR_DTLTMsg> dsContrDtlList;

    private List<CONTR_PHYS_BLLG_MTR_RELNTMsg> contrPhysBllgMtrRelnList;

    private List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrList;

    private List<CONTR_XS_COPYTMsg> contrXsCopyList;

    private List<DS_CONTR_ADDL_CHRGTMsg> dsContrAddlChrgList;

    private List<DS_CONTR_BR_ALLOCTMsg> dsContrBrAllocList;

    private List<DS_CONTR_SEG_ALLOCTMsg> dsContrSegAllocList;

    public DS_CONTRTMsg getDsContr() {
        return dsContr;
    }

    public void setDsContr(DS_CONTRTMsg dsContr) {
        this.dsContr = dsContr;
    }

    public List<DS_CONTR_DTLTMsg> getDsContrDtlList() {
        return dsContrDtlList;
    }

    public void setDsContrDtlList(List<DS_CONTR_DTLTMsg> dsContrDtlList) {
        this.dsContrDtlList = dsContrDtlList;
    }

    public List<CONTR_PHYS_BLLG_MTR_RELNTMsg> getContrPhysBllgMtrRelnList() {
        return contrPhysBllgMtrRelnList;
    }

    public void setContrPhysBllgMtrRelnList(List<CONTR_PHYS_BLLG_MTR_RELNTMsg> contrPhysBllgMtrRelnList) {
        this.contrPhysBllgMtrRelnList = contrPhysBllgMtrRelnList;
    }

    public List<DS_CONTR_BLLG_MTRTMsg> getDsContrBllgMtrList() {
        return dsContrBllgMtrList;
    }

    public void setDsContrBllgMtrList(List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrList) {
        this.dsContrBllgMtrList = dsContrBllgMtrList;
    }

    public List<CONTR_XS_COPYTMsg> getContrXsCopyList() {
        return contrXsCopyList;
    }

    public void setContrXsCopyList(List<CONTR_XS_COPYTMsg> contrXsCopyList) {
        this.contrXsCopyList = contrXsCopyList;
    }

    public List<DS_CONTR_ADDL_CHRGTMsg> getDsContrAddlChrgList() {
        return dsContrAddlChrgList;
    }

    public void setDsContrAddlChrgList(List<DS_CONTR_ADDL_CHRGTMsg> dsContrAddlChrgList) {
        this.dsContrAddlChrgList = dsContrAddlChrgList;
    }

    public List<DS_CONTR_BR_ALLOCTMsg> getDsContrBrAllocList() {
        return dsContrBrAllocList;
    }

    public void setDsContrBrAllocList(List<DS_CONTR_BR_ALLOCTMsg> dsContrBrAllocList) {
        this.dsContrBrAllocList = dsContrBrAllocList;
    }

    public List<DS_CONTR_SEG_ALLOCTMsg> getDsContrSegAllocList() {
        return dsContrSegAllocList;
    }

    public void setDsContrSegAllocList(List<DS_CONTR_SEG_ALLOCTMsg> dsContrSegAllocList) {
        this.dsContrSegAllocList = dsContrSegAllocList;
    }

}
