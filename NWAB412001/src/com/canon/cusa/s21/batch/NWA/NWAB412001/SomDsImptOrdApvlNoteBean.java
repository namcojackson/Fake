/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;

import business.db.DS_IMPT_ORD_APVL_NOTETMsg;
import business.db.NWAI4120_03TMsg;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptOrdApvlNoteBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 *</pre>
 */
public class SomDsImptOrdApvlNoteBean extends DS_IMPT_ORD_APVL_NOTETMsg implements ISomDsImptOrd {

    private static final long serialVersionUID = 1L;

    public final NWAI4120_03TMsg nwai4120_03;

    public final SomDsImptOrdSomApvlBean dsImptOrdSomApvlBean;

    public SomDsImptOrdApvlNoteBean(SomDsImptOrdSomApvlBean dsImptOrdSomApvlBean, NWAI4120_03TMsg nwai4120_03) {
        super();

        this.nwai4120_03 = nwai4120_03;
        this.dsImptOrdSomApvlBean = dsImptOrdSomApvlBean;
        dsImptOrdSomApvlBean.dsImptOrdApvlNoteBeanList.add(this);
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdApvlNotePk, ZYPOracleSeqAccessor.getNumberBigDecimal("DS_IMPT_ORD_APVL_NOTE_SQ"));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdSomApvlBean.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdSomApvlPk, this.dsImptOrdSomApvlBean.dsImptOrdSomApvlPk);
        ZYPEZDItemValueSetter.setValue(this.somApvlNoteId, this.nwai4120_03.somApvlNoteId);
        ZYPEZDItemValueSetter.setValue(this.somApvlId, this.nwai4120_03.somApvlId);
        ZYPEZDItemValueSetter.setValue(this.apvlAddTs, NWXC412001.getValidTimeStamp( this.nwai4120_03.apvlAddTs.getValue()));
        ZYPEZDItemValueSetter.setValue(this.apvlNoteTxt, this.nwai4120_03.apvlNoteTxt);
        ZYPEZDItemValueSetter.setValue(this.apvlAddUsrId, this.nwai4120_03.apvlAddUsrId);

        return true;
    }
}
