/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;

import business.db.DS_IMPT_ORD_ISTL_INFOTMsg;
import business.db.DS_MDLTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptOrdIstlInfoBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 07/21/2017   SRAA            K.Aratani       Update          QC#19993
 *</pre>
 */
public class SomDsImptOrdIstlInfoBean extends DS_IMPT_ORD_ISTL_INFOTMsg implements ISomDsImptOrd {

    private static final long serialVersionUID = 1L;

    public final NWAB412001Constant.ISTL_INFO_TYPE istlInfoType;

    public final SomDsImptOrdBean dsImptOrdBean;
    
    public final SomDsImptOrdConfigBean dsImptOrdConfigBean;

    public SomDsImptOrdIstlInfoBean(NWAB412001Constant.ISTL_INFO_TYPE istlInfoType, SomDsImptOrdConfigBean dsImptOrdConfigBean) {
        super();

        this.istlInfoType = istlInfoType;
        this.dsImptOrdBean = dsImptOrdConfigBean.dsImptOrdBean;
        this.dsImptOrdConfigBean = dsImptOrdConfigBean;
        dsImptOrdConfigBean.dsImptOrdIstlInfoBean = this;
    }

    public SomDsImptOrdIstlInfoBean(NWAB412001Constant.ISTL_INFO_TYPE istlInfoType, SomDsImptOrdBean dsImptOrdBean) {
        super();

        this.istlInfoType = istlInfoType;
        this.dsImptOrdBean = dsImptOrdBean;
        this.dsImptOrdConfigBean = null;
        dsImptOrdBean.dsImptOrdIstlInfoBean = this;
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdIstlInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_ISTL_INFO_SQ));
        if (NWAB412001Constant.ISTL_INFO_TYPE.HEADER_DELY_INFO.equals(this.istlInfoType)) {
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);
            this.dsImptOrdConfigPk.clear();

            if (this.dsImptOrdBean.dsImptOrdConfigBeanList != null && this.dsImptOrdBean.dsImptOrdConfigBeanList.size() > 0) {
                for (SomDsImptOrdConfigBean configBean : this.dsImptOrdBean.dsImptOrdConfigBeanList) {
                    DS_MDLTMsg dsMdl = NWXC412001.getActiveDsMdl(glblCmpyCd, configBean.mdlId.getValue());
                    if (dsMdl != null) {
                        ZYPEZDItemValueSetter.setValue(this.svcIstlRuleNum, dsMdl.svcIstlRuleNum);
                        break;
                    } else {
                        this.svcIstlRuleNum.clear();
                    }
                }
            } else {
                this.svcIstlRuleNum.clear();
            }
            DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfn = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfn.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfn.dsOrdTpCd, dsImptOrdBean.dsOrdTpCd.getValue());
            dsOrdTpProcDfn = (DS_ORD_TP_PROC_DFNTMsg) EZDTBLAccessor.findByKey(dsOrdTpProcDfn);
            if (dsOrdTpProcDfn != null) {
                ZYPEZDItemValueSetter.setValue(this.istlDivCd, dsOrdTpProcDfn.lineBizTpCd.getValue());
            } else {
                this.istlDivCd.clear();
            }
            this.istlTechCd.clear();
            this.rqstIstlDt.clear();
            this.rqstIstlTm.clear();
        } else {
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdConfigBean.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, this.dsImptOrdConfigBean.dsImptOrdConfigPk);
            DS_MDLTMsg dsMdl = NWXC412001.getActiveDsMdl(glblCmpyCd, this.dsImptOrdConfigBean.mdlId.getValue());
            if (dsMdl != null) {
                ZYPEZDItemValueSetter.setValue(this.svcIstlRuleNum, dsMdl.svcIstlRuleNum);
            } else {
                this.svcIstlRuleNum.clear();
            }
            DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfn = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfn.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfn.dsOrdTpCd, dsImptOrdConfigBean.dsImptOrdBean.dsOrdTpCd.getValue());
            dsOrdTpProcDfn = (DS_ORD_TP_PROC_DFNTMsg) EZDTBLAccessor.findByKey(dsOrdTpProcDfn);
            if (dsOrdTpProcDfn != null) {
                ZYPEZDItemValueSetter.setValue(this.istlDivCd, dsOrdTpProcDfn.lineBizTpCd.getValue());
            } else {
                this.istlDivCd.clear();
            }
            this.istlTechCd.clear();
            this.rqstIstlDt.clear();
            this.rqstIstlTm.clear();
            if (NWAB412001Constant.CONFIG_TYPE.OCE_PROMOTION_RMA.equals(dsImptOrdConfigBean.configType)) {
                ZYPEZDItemValueSetter.setValue(this.istlAddlCmntTxt, dsImptOrdConfigBean.nwai4120_14List.get(0).somRmvInstnTxt.getValue());
            } else {
                this.istlAddlCmntTxt.clear();
            }
        }

        return true;
    }
}
