/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;

import business.db.DS_IMPT_ORD_SOM_APVLTMsg;
import business.db.NWAI4120_02TMsg;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptSvcAddlChrgBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 *</pre>
 */
public class SomDsImptOrdSomApvlBean extends DS_IMPT_ORD_SOM_APVLTMsg implements ISomDsImptOrd {

    private static final long serialVersionUID = 1L;

    public final NWAI4120_02TMsg nwai4120_02;

    public final SomDsImptOrdBean dsImptOrdBean;

    public final List<SomDsImptOrdApvlNoteBean> dsImptOrdApvlNoteBeanList;

    public SomDsImptOrdSomApvlBean(SomDsImptOrdBean dsImptOrdBean, NWAI4120_02TMsg nwai4120_02) {
        super();

        this.nwai4120_02 = nwai4120_02;
        this.dsImptOrdBean = dsImptOrdBean;
        this.dsImptOrdApvlNoteBeanList = new ArrayList<SomDsImptOrdApvlNoteBean>();
        dsImptOrdBean.dsImptOrdSomApvlBeanList.add(this);
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdSomApvlPk, ZYPOracleSeqAccessor.getNumberBigDecimal("DS_IMPT_ORD_SOM_APVL_SQ"));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(this.somApvlId, this.nwai4120_02.somApvlId);
        ZYPEZDItemValueSetter.setValue(this.somQuoteId, this.nwai4120_02.somQuoteId);
        ZYPEZDItemValueSetter.setValue(this.somVrsnId, this.nwai4120_02.somVrsnId);
        ZYPEZDItemValueSetter.setValue(this.apvlTpId, this.nwai4120_02.apvlTpId);
        ZYPEZDItemValueSetter.setValue(this.wfStartTs, NWXC412001.getValidTimeStamp(this.nwai4120_02.wfStartTs.getValue()));
        ZYPEZDItemValueSetter.setValue(this.wfEndTs, NWXC412001.getValidTimeStamp(this.nwai4120_02.wfEndTs.getValue()));
        ZYPEZDItemValueSetter.setValue(this.somApvlTpTxt, this.nwai4120_02.somApvlTpTxt);
        ZYPEZDItemValueSetter.setValue(this.somStsTxt, this.nwai4120_02.somStsTxt);
        ZYPEZDItemValueSetter.setValue(this.somApvlDescTxt, this.nwai4120_02.somApvlDescTxt);
        ZYPEZDItemValueSetter.setValue(this.shipToAddrKeyTxt, this.nwai4120_02.shipToAddrKeyTxt);
        ZYPEZDItemValueSetter.setValue(this.splRepTxt, this.nwai4120_02.splitRepTxt);
        ZYPEZDItemValueSetter.setValue(this.somApvrNm, this.nwai4120_02.somApvrNm);
        ZYPEZDItemValueSetter.setValue(this.apvlActvTxt, this.nwai4120_02.apvlActvTxt);
        ZYPEZDItemValueSetter.setValue(this.wfStartUsrId, this.nwai4120_02.wfStartUsrId);
        ZYPEZDItemValueSetter.setValue(this.wfEndUsrId, this.nwai4120_02.wfEndUsrId);
        ZYPEZDItemValueSetter.setValue(this.apvlAddUsrId, this.nwai4120_02.apvlAddUsrId);

        for (SomDsImptOrdApvlNoteBean dsImptOrdApvlNoteBean : dsImptOrdApvlNoteBeanList) {
            dsImptOrdApvlNoteBean.doImptMapping(glblCmpyCd, salesDate);
        }

        return true;
    }
}
