/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001;

import java.util.ArrayList;
import java.util.List;

import business.db.DS_IMPT_ORD_SOM_APVLTMsg;
import business.db.NWAI4150_02TMsg;

import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;

/**
 * <pre>
 * EOPS Interface to S21 Import Data Batch
 * EopsDsImptSvcAddlChrgBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 *</pre>
 */
public class EopsDsImptOrdEopsApvlBean extends DS_IMPT_ORD_SOM_APVLTMsg implements IEopsDsImptOrd {

    /** default */
    private static final long serialVersionUID = 1L;

    /**  */
    public final NWAI4150_02TMsg nwai415002;

    /**  */
    public final EopsDsImptOrdBean dsImptOrdBean;

    /**  */
    public final List<EopsDsImptOrdApvlNoteBean> dsImptOrdApvlNoteBeanList;

    /**
     * EopsDsImptOrdEopsApvlBean
     * @param dsImptOrdBean EopsDsImptOrdBean
     * @param nwai415002    NWAI4150_02TMsg
     */
    public EopsDsImptOrdEopsApvlBean(EopsDsImptOrdBean dsImptOrdBean, NWAI4150_02TMsg nwai415002) {
        super();

        this.nwai415002 = nwai415002;
        this.dsImptOrdBean = dsImptOrdBean;
        this.dsImptOrdApvlNoteBeanList = new ArrayList<EopsDsImptOrdApvlNoteBean>();
        dsImptOrdBean.dsImptOrdEopsApvlBeanList.add(this);
    }

    /**
     * doImptMapping
     * @param glblCmpyCd    glblCmpyCd
     * @param salesDate     salesDate
     * @return boolean
     */
    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {
        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdSomApvlPk, ZYPOracleSeqAccessor.getNumberBigDecimal("DS_IMPT_ORD_SOM_APVL_SQ"));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(this.somApvlId, this.nwai415002.somApvlId);
        ZYPEZDItemValueSetter.setValue(this.somQuoteId, this.nwai415002.somQuoteId);
        ZYPEZDItemValueSetter.setValue(this.somVrsnId, this.nwai415002.somVrsnId);
        ZYPEZDItemValueSetter.setValue(this.apvlTpId, this.nwai415002.apvlTpId);
        ZYPEZDItemValueSetter.setValue(this.wfStartTs, NWXC412001.getValidTimeStamp(this.nwai415002.wfStartTs.getValue()));
        ZYPEZDItemValueSetter.setValue(this.wfEndTs, NWXC412001.getValidTimeStamp(this.nwai415002.wfEndTs.getValue()));
        ZYPEZDItemValueSetter.setValue(this.somApvlTpTxt, this.nwai415002.somApvlTpTxt);
        ZYPEZDItemValueSetter.setValue(this.somStsTxt, this.nwai415002.somStsTxt);
        ZYPEZDItemValueSetter.setValue(this.somApvlDescTxt, this.nwai415002.somApvlDescTxt);
        ZYPEZDItemValueSetter.setValue(this.shipToAddrKeyTxt, this.nwai415002.shipToAddrKeyTxt);
        ZYPEZDItemValueSetter.setValue(this.splRepTxt, this.nwai415002.splitRepTxt);
        ZYPEZDItemValueSetter.setValue(this.somApvrNm, this.nwai415002.somApvrNm);
        ZYPEZDItemValueSetter.setValue(this.apvlActvTxt, this.nwai415002.apvlActvTxt);
        ZYPEZDItemValueSetter.setValue(this.wfStartUsrId, this.nwai415002.wfStartUsrId);
        ZYPEZDItemValueSetter.setValue(this.wfEndUsrId, this.nwai415002.wfEndUsrId);
        ZYPEZDItemValueSetter.setValue(this.apvlAddUsrId, this.nwai415002.apvlAddUsrId);

        for (EopsDsImptOrdApvlNoteBean dsImptOrdApvlNoteBean : dsImptOrdApvlNoteBeanList) {
            dsImptOrdApvlNoteBean.doImptMapping(glblCmpyCd, salesDate);
        }
        return true;
    }

}
