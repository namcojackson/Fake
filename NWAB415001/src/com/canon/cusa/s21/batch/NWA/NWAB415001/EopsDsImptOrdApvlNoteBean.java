/*
 * <Pre>Copyright (c) 2017-2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001;

import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;

import business.db.DS_IMPT_ORD_APVL_NOTETMsg;
import business.db.NWAI4150_03TMsg;

/**
 * <pre>
 * EOPS Interface to S21 Import Data Batch
 * EopsDsImptOrdApvlNoteBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 *</pre>
 */
public class EopsDsImptOrdApvlNoteBean extends DS_IMPT_ORD_APVL_NOTETMsg implements IEopsDsImptOrd {

    /** default */
    private static final long serialVersionUID = 1L;

    /**  */
    public final NWAI4150_03TMsg nwai415003;

    /**  */
    public final EopsDsImptOrdEopsApvlBean dsImptOrdEopsApvlBean;

    /**
     * EopsDsImptOrdApvlNoteBean
     * @param dsImptOrdEopsApvlBean EopsDsImptOrdEopsApvlBean
     * @param nwai415003            NWAI4150_03TMsg
     */
    public EopsDsImptOrdApvlNoteBean(EopsDsImptOrdEopsApvlBean dsImptOrdEopsApvlBean, NWAI4150_03TMsg nwai415003) {
        super();

        this.nwai415003 = nwai415003;
        this.dsImptOrdEopsApvlBean = dsImptOrdEopsApvlBean;
        dsImptOrdEopsApvlBean.dsImptOrdApvlNoteBeanList.add(this);
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
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdApvlNotePk, ZYPOracleSeqAccessor.getNumberBigDecimal("DS_IMPT_ORD_APVL_NOTE_SQ"));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdEopsApvlBean.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdSomApvlPk, this.dsImptOrdEopsApvlBean.dsImptOrdSomApvlPk);
        ZYPEZDItemValueSetter.setValue(this.somApvlNoteId, this.nwai415003.somApvlNoteId);
        ZYPEZDItemValueSetter.setValue(this.somApvlId, this.nwai415003.somApvlId);
        ZYPEZDItemValueSetter.setValue(this.apvlAddTs, NWXC412001.getValidTimeStamp(this.nwai415003.apvlAddTs.getValue()));
        ZYPEZDItemValueSetter.setValue(this.apvlNoteTxt, this.nwai415003.eopsApvlNoteTxt);
        ZYPEZDItemValueSetter.setValue(this.apvlAddUsrId, this.nwai415003.apvlAddUsrId);

        return true;
    }
}
