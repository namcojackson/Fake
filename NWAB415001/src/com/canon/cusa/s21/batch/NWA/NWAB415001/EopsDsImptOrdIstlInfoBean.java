/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_IMPT_ORD_ISTL_INFOTMsg;
import business.db.DS_MDLTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;

import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;

/**
 * <pre>
 * EOPS Interface Batch EopsDsImptOrdIstlInfoBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 * 
 * </pre>
 */
public class EopsDsImptOrdIstlInfoBean extends DS_IMPT_ORD_ISTL_INFOTMsg implements IEopsDsImptOrd {

    /**  */
    private static final long serialVersionUID = 1L;

    /**  */
    public final NWAB415001Constant.ISTL_INFO_TYPE istlInfoType;

    /**  */
    public final EopsDsImptOrdBean dsImptOrdBean;

    /**  */
    public final EopsDsImptOrdConfigBean dsImptOrdConfigBean;

    /**
     * EopsDsImptOrdIstlInfoBean
     * @param istlInfoType          NWAB415001Constant.ISTL_INFO_TYPE
     * @param dsImptOrdConfigBean   EopsDsImptOrdConfigBean
     */
    public EopsDsImptOrdIstlInfoBean(NWAB415001Constant.ISTL_INFO_TYPE istlInfoType, EopsDsImptOrdConfigBean dsImptOrdConfigBean) {
        super();

        this.istlInfoType = istlInfoType;
        this.dsImptOrdBean = dsImptOrdConfigBean.dsImptOrdBean;
        this.dsImptOrdConfigBean = dsImptOrdConfigBean;
        dsImptOrdConfigBean.dsImptOrdIstlInfoBean = this;
    }

    /**
     * EopsDsImptOrdIstlInfoBean
     * @param istlInfoType  NWAB415001Constant.ISTL_INFO_TYPE
     * @param dsImptOrdBean EopsDsImptOrdBean
     */
    public EopsDsImptOrdIstlInfoBean(NWAB415001Constant.ISTL_INFO_TYPE istlInfoType, EopsDsImptOrdBean dsImptOrdBean) {
        super();

        this.istlInfoType = istlInfoType;
        this.dsImptOrdBean = dsImptOrdBean;
        this.dsImptOrdConfigBean = null;
        dsImptOrdBean.dsImptOrdIstlInfoBean = this;
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
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdIstlInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_ISTL_INFO_SQ));
        if (NWAB415001Constant.ISTL_INFO_TYPE.HEADER_DELY_INFO.equals(this.istlInfoType)) {
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);
            this.dsImptOrdConfigPk.clear();

            if (NWXC412001.hasValueList(this.dsImptOrdBean.dsImptOrdConfigBeanList)) {
                for (EopsDsImptOrdConfigBean configBean : this.dsImptOrdBean.dsImptOrdConfigBeanList) {
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
            this.istlAddlCmntTxt.clear();
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
            if (NWAB415001Constant.CONFIG_TYPE.OCE_PROMOTION_RMA.equals(dsImptOrdConfigBean.configType)) {
                ZYPEZDItemValueSetter.setValue(//
                        this.istlAddlCmntTxt, dsImptOrdConfigBean.nwai4150_14List.get(0).somRmvInstnTxt.getValue());

            } else if (NWAB415001Constant.CONFIG_TYPE.EOPS_RMA.equals(dsImptOrdConfigBean.configType)) {
                ZYPEZDItemValueSetter.setValue(//
                        this.istlAddlCmntTxt, dsImptOrdConfigBean.nwai4150_24List.get(0).eopsRtrnCmntTxt.getValue());
            } else {
                this.istlAddlCmntTxt.clear();
            }
        }

        return true;
    }
}
