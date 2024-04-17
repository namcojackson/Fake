/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

import business.db.DS_IMPT_ORD_SLS_CRTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsg;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptOrdSlsCrBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 12/01/2016   SRAA            K.Aratani       Update          QC#15539
 * 01/13/2017   SRAA            K.Aratani       Update          QC#17123
 * 01/13/2017   SRAA            K.Aratani       Update          QC#17128
 *</pre>
 */
public class SomDsImptOrdSlsCrBean extends DS_IMPT_ORD_SLS_CRTMsg implements ISomDsImptOrd {

    private static final long serialVersionUID = 1L;

    public final NWAB412001Constant.SLS_CR_TYPE slsCrType;

    public final SomDsImptOrdBean dsImptOrdBean;

    public final SomDsImptOrdConfigBean dsImptOrdConfigBean;

    public SomDsImptOrdSlsCrBean dsImptOrdSlsCrParentBean;

    public final List<SomDsImptOrdSlsCrBean> dsImptOrdSlsCrChildBeanList;

    public SomDsImptOrdSlsCrBean(NWAB412001Constant.SLS_CR_TYPE slsCrType, SomDsImptOrdBean dsImptOrdBean) {
        this(slsCrType, dsImptOrdBean, null);

        dsImptOrdBean.dsImptOrdSlsCrBean = this;
    }

    private SomDsImptOrdSlsCrBean(NWAB412001Constant.SLS_CR_TYPE slsCrType, SomDsImptOrdSlsCrBean parentBean, SomDsImptOrdBean dsImptOrdBean) {
        this(slsCrType, dsImptOrdBean, null);

        this.dsImptOrdSlsCrParentBean = parentBean;
    }

    public SomDsImptOrdSlsCrBean(NWAB412001Constant.SLS_CR_TYPE slsCrType, SomDsImptOrdConfigBean dsImptOrdConfigBean) {
        this(slsCrType, dsImptOrdConfigBean.dsImptOrdBean, dsImptOrdConfigBean);

        dsImptOrdConfigBean.dsImptOrdSlsCrBean = this;
    }

    private SomDsImptOrdSlsCrBean(NWAB412001Constant.SLS_CR_TYPE slsCrType, SomDsImptOrdSlsCrBean parentBean, SomDsImptOrdConfigBean dsImptOrdConfigBean) {
        this(slsCrType, dsImptOrdConfigBean.dsImptOrdBean, dsImptOrdConfigBean);

        this.dsImptOrdSlsCrParentBean = parentBean;
    }

    private SomDsImptOrdSlsCrBean(NWAB412001Constant.SLS_CR_TYPE slsCrType, SomDsImptOrdBean dsImptOrdBean, SomDsImptOrdConfigBean dsImptOrdConfigBean) {
        super();

        this.slsCrType = slsCrType;
        this.dsImptOrdBean = dsImptOrdBean;
        this.dsImptOrdConfigBean = dsImptOrdConfigBean;
        this.dsImptOrdSlsCrChildBeanList = new ArrayList<SomDsImptOrdSlsCrBean>();
    }

    private SomDsImptOrdSlsCrBean addChildList() {
        SomDsImptOrdSlsCrBean childBean;
        if (NWAB412001Constant.SLS_CR_TYPE.HEADER.equals(this.slsCrType)) {
            childBean = new SomDsImptOrdSlsCrBean(this.slsCrType, this, this.dsImptOrdBean);
        } else {
            childBean = new SomDsImptOrdSlsCrBean(this.slsCrType, this, this.dsImptOrdConfigBean);
        }

//        copy((DS_IMPT_ORD_SLS_CRTMsg) this, "", (DS_IMPT_ORD_SLS_CRTMsg) childBean, "");
        this.dsImptOrdSlsCrChildBeanList.add(childBean);

        return childBean;
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        if (NWAB412001Constant.SLS_CR_TYPE.HEADER.equals(this.slsCrType)) {

            createSlsCr(glblCmpyCd, salesDate, this, this.dsImptOrdBean.nwai4120_01.somSlsRepId.getValue(), BigDecimal.TEN.multiply(BigDecimal.TEN), ZYPConstant.FLG_ON_Y);

        } else {

            BigDecimal istlRepPercent = null;
            BigDecimal primRepPercent = null;
            BigDecimal acctMgrRepPercent = null;

            //QC#17123
            String primSlsRepPsnCd = this.dsImptOrdConfigBean.dsImptOrdBean.nwai4120_01.somSlsRepId.getValue();
            String acctMgrSlsRepPsnCd = this.dsImptOrdConfigBean.dsImptOrdBean.nwai4120_01.somAcctMgrTxt.getValue();
            String istlSlsRepPsnCd = null;
            //QC#17128
            String splitTpCd = null;
            if (this.dsImptOrdConfigBean.nwai4120_12 != null && ZYPCommonFunc.hasValue(this.dsImptOrdConfigBean.nwai4120_12.splitTpTxt)) {
                splitTpCd = this.dsImptOrdConfigBean.nwai4120_12.splitTpTxt.getValue();
            }

            if (S21StringUtil.isEquals(this.dsImptOrdConfigBean.configCatgCd.getValue(), CONFIG_CATG.OUTBOUND)
                    && "Inter-Regional".equals(splitTpCd)) {

                //ASIS from jtf_rs_salesreps by name
                //istlSlsRepPsnNum = ZYPCommonFunc.hasValue(this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt) ? this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt.getValue() : this.dsImptOrdConfigBean.dsImptOrdBean.nwai4120_01.istlRepTxt.getValue();
                if (ZYPCommonFunc.hasValue(this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt)) {
                    String psnCd = (String) this.dsImptOrdConfigBean.dsImptOrdBean.getCacheS21PsnFromInstallRepNameMap().get(this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt.getValue());
                    if (ZYPCommonFunc.hasValue(psnCd)) {
                        istlSlsRepPsnCd = psnCd;
                    } else {
                        Map<String, Object> psnMap = NWXC412001.getPsnCdByPsnFullNm(glblCmpyCd, this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt.getValue());
                        if (psnMap != null && psnMap.get("PSN_CD") != null) {
                            istlSlsRepPsnCd = (String) psnMap.get("PSN_CD");
                            this.dsImptOrdConfigBean.dsImptOrdBean.getCacheS21PsnFromInstallRepNameMap().put(this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt.getValue(), istlSlsRepPsnCd);
                        } else {
                            psnMap = NWXC412001.getPsnCdByPsnCd(glblCmpyCd, this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt.getValue());
                            if (psnMap != null && psnMap.get("PSN_CD") != null) {
                                istlSlsRepPsnCd = (String) psnMap.get("PSN_CD");
                                this.dsImptOrdConfigBean.dsImptOrdBean.getCacheS21PsnFromInstallRepNameMap().put(this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt.getValue(), istlSlsRepPsnCd);
                            } else {
                                psnMap = NWXC412001.getPsnCdByTocNm(glblCmpyCd, this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt.getValue());
                                if (psnMap != null && psnMap.get("PSN_CD") != null) {
                                    istlSlsRepPsnCd = (String) psnMap.get("PSN_CD");
                                    this.dsImptOrdConfigBean.dsImptOrdBean.getCacheS21PsnFromInstallRepNameMap().put(this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt.getValue(), istlSlsRepPsnCd);
                                } else {
                                    istlSlsRepPsnCd = this.dsImptOrdConfigBean.dsImptOrdBean.nwai4120_01.istlRepTxt.getValue();
                                }
                            }
                        }
                    }
                } else {
                    istlSlsRepPsnCd = this.dsImptOrdConfigBean.dsImptOrdBean.nwai4120_01.istlRepTxt.getValue();
                }
                
            } else if(S21StringUtil.isEquals(this.dsImptOrdConfigBean.configCatgCd.getValue(), CONFIG_CATG.INBOUND)
                    && this.dsImptOrdConfigBean.nwai4120_05 == null && "Inter-Regional".equals(splitTpCd)) {

                //ASIS from jtf_rs_salesreps by name
                //istlSlsRepPsnNum = ZYPCommonFunc.hasValue(this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt) ? this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt.getValue() : this.dsImptOrdConfigBean.dsImptOrdBean.nwai4120_01.istlRepTxt.getValue();
                if (ZYPCommonFunc.hasValue(this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt)) {
                    String psnCd = (String) this.dsImptOrdConfigBean.dsImptOrdBean.getCacheS21PsnFromInstallRepNameMap().get(this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt.getValue());
                    if (ZYPCommonFunc.hasValue(psnCd)) {
                        istlSlsRepPsnCd = psnCd;
                    } else {
                        Map<String, Object> psnMap = NWXC412001.getPsnCdByPsnFullNm(glblCmpyCd, this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt.getValue());
                        if (psnMap != null && psnMap.get("PSN_CD") != null) {
                            istlSlsRepPsnCd = (String) psnMap.get("PSN_CD");
                            this.dsImptOrdConfigBean.dsImptOrdBean.getCacheS21PsnFromInstallRepNameMap().put(this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt.getValue(), istlSlsRepPsnCd);
                        } else {
                            psnMap = NWXC412001.getPsnCdByPsnCd(glblCmpyCd, this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt.getValue());
                            if (psnMap != null && psnMap.get("PSN_CD") != null) {
                                istlSlsRepPsnCd = (String) psnMap.get("PSN_CD");
                                this.dsImptOrdConfigBean.dsImptOrdBean.getCacheS21PsnFromInstallRepNameMap().put(this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt.getValue(), istlSlsRepPsnCd);
                            } else {
                                psnMap = NWXC412001.getPsnCdByTocNm(glblCmpyCd, this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt.getValue());
                                if (psnMap != null && psnMap.get("PSN_CD") != null) {
                                    istlSlsRepPsnCd = (String) psnMap.get("PSN_CD");
                                    this.dsImptOrdConfigBean.dsImptOrdBean.getCacheS21PsnFromInstallRepNameMap().put(this.dsImptOrdConfigBean.nwai4120_12.istlRepDescTxt.getValue(), istlSlsRepPsnCd);
                                } else {
                                    istlSlsRepPsnCd = this.dsImptOrdConfigBean.dsImptOrdBean.nwai4120_01.istlRepTxt.getValue();
                                }
                            }
                        }
                    }
                } else {
                    istlSlsRepPsnCd = this.dsImptOrdConfigBean.dsImptOrdBean.nwai4120_01.istlRepTxt.getValue();
                }
                
            } else if ("Inter-Regional".equals(splitTpCd)) {

                istlSlsRepPsnCd = ZYPCommonFunc.hasValue(this.dsImptOrdConfigBean.nwai4120_05.istlRepTxt) ? this.dsImptOrdConfigBean.nwai4120_05.istlRepTxt.getValue() : this.dsImptOrdConfigBean.dsImptOrdBean.nwai4120_01.istlRepTxt
                        .getValue();
            }

            //BigDecimal writinigRepSplitPercent = ZYPCodeDataUtil.getNumConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_WRITING_REP_SPLIT_PERCENT.name(), glblCmpyCd);
            BigDecimal writinigRepSplitPercent = (BigDecimal) this.dsImptOrdConfigBean.dsImptOrdBean.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_WRITING_REP_SPLIT_PERCENT.name());

            if (S21StringUtil.isEmpty(acctMgrSlsRepPsnCd)) {

                // no account manager
                if (S21StringUtil.isNotEmpty(istlSlsRepPsnCd) && !S21StringUtil.isEquals(primSlsRepPsnCd, istlSlsRepPsnCd)) {

                    // primary 60%
                    primRepPercent = writinigRepSplitPercent;

                    // install 40%
                    istlRepPercent = BigDecimal.TEN.multiply(BigDecimal.TEN).subtract(writinigRepSplitPercent);
                } else {

                    // primary 100%
                    primRepPercent = BigDecimal.TEN.multiply(BigDecimal.TEN);
                }
            } else {

                // exists account manager
                if (S21StringUtil.isNotEmpty(istlSlsRepPsnCd)) {

                    if (S21StringUtil.isEquals(acctMgrSlsRepPsnCd, primSlsRepPsnCd)) {

                        if (S21StringUtil.isEquals(primSlsRepPsnCd, istlSlsRepPsnCd)) {
                            // primary 100%
                            primRepPercent = BigDecimal.TEN.multiply(BigDecimal.TEN);
                        } else {
                            // primary 60%
                            primRepPercent = writinigRepSplitPercent;

                            // install 40%
                            istlRepPercent = BigDecimal.TEN.multiply(BigDecimal.TEN).subtract(writinigRepSplitPercent);
                        }
                    } else {

                        // primary 40%
                        primRepPercent = BigDecimal.TEN.multiply(BigDecimal.TEN).subtract(writinigRepSplitPercent);

                        // install 40%
                        istlRepPercent = BigDecimal.TEN.multiply(BigDecimal.TEN).subtract(writinigRepSplitPercent);

                        // account manager 20 %
                        acctMgrRepPercent = BigDecimal.TEN.multiply(BigDecimal.TEN).subtract(primRepPercent).subtract(istlRepPercent);

                        if (S21StringUtil.isEquals(primSlsRepPsnCd, istlSlsRepPsnCd)) {
                            // primary 80%
                            primRepPercent = primRepPercent.add(istlRepPercent);

                            // install nothing
                            istlRepPercent = null;

                            // account manager 20 %
                        }
                    }
                } else {

                    if (!S21StringUtil.isEquals(acctMgrSlsRepPsnCd, primSlsRepPsnCd)) {

                        // primary 80%
                        primRepPercent = BigDecimal.TEN.multiply(BigDecimal.TEN).subtract(writinigRepSplitPercent).multiply(BigDecimal.valueOf(2));

                        // account manager 20 %
                        acctMgrRepPercent = BigDecimal.TEN.multiply(BigDecimal.TEN).subtract(primRepPercent);
                    } else {

                        // primary 100%
                        primRepPercent = BigDecimal.TEN.multiply(BigDecimal.TEN);
                    }
                }
            }

            boolean hasParent = false;
            if (primRepPercent != null) {
                SomDsImptOrdSlsCrBean slsRepBean = null;
                if (hasParent) {

                    slsRepBean = addChildList();
                } else {

                    slsRepBean = this;
                }

                createSlsCr(glblCmpyCd, salesDate, slsRepBean, primSlsRepPsnCd, primRepPercent, ZYPConstant.FLG_ON_Y);
                hasParent = true;
            }

            if (istlRepPercent != null) {
                SomDsImptOrdSlsCrBean slsRepBean = null;
                if (hasParent) {

                    slsRepBean = addChildList();
                } else {

                    slsRepBean = this;
                }

                createSlsCr(glblCmpyCd, salesDate, slsRepBean, istlSlsRepPsnCd, istlRepPercent, ZYPConstant.FLG_OFF_N);
                hasParent = true;
            }

            if (acctMgrRepPercent != null) {
                SomDsImptOrdSlsCrBean slsRepBean = null;
                if (hasParent) {

                    slsRepBean = addChildList();
                } else {

                    slsRepBean = this;
                }

                createSlsCr(glblCmpyCd, salesDate, slsRepBean, acctMgrSlsRepPsnCd, acctMgrRepPercent, null);
                hasParent = true;
            }

        }

        return true;
    }

    private void createSlsCr(String glblCmpyCd, String salesDate, SomDsImptOrdSlsCrBean slsRepBean, String slsRepPsnCd, BigDecimal slsRepCrPct, String primRepRoleFlg) {

        ZYPEZDItemValueSetter.setValue(slsRepBean.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(slsRepBean.dsImptOrdSlsCrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SLS_CR_SQ));
        if (this.dsImptOrdConfigBean == null) {

            // header level
            ZYPEZDItemValueSetter.setValue(slsRepBean.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);
            slsRepBean.dsImptOrdConfigPk.clear();
        } else {

            // configuration level
            ZYPEZDItemValueSetter.setValue(slsRepBean.dsImptOrdPk, this.dsImptOrdConfigBean.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(slsRepBean.dsImptOrdConfigPk, this.dsImptOrdConfigBean.dsImptOrdConfigPk);
        }

        if (!this.dsImptOrdBean.getCacheSlsRepTocCdMap().containsKey(slsRepPsnCd)) {
            Map<String, Object> slsRepTocCdMap = NWXC412001.getSlsRepTocByResourceId(glblCmpyCd, slsRepPsnCd, this.dsImptOrdBean.slsDt);
            if (slsRepTocCdMap != null) {
                ZYPEZDItemValueSetter.setValue(slsRepBean.slsRepTocCd, (String) slsRepTocCdMap.get("SLS_REP_TOC_CD"));
                this.dsImptOrdBean.getCacheSlsRepTocCdMap().put(slsRepPsnCd, slsRepTocCdMap);
            } else {
                this.dsImptOrdBean.getCacheSlsRepTocCdMap().put(slsRepPsnCd, new java.util.HashMap<String, Object>());
            }
        } else {
            Map<String, Object> slsRepTocCdMap = this.dsImptOrdBean.getCacheSlsRepTocCdMap().get(slsRepPsnCd);
            if (slsRepTocCdMap != null) {
                ZYPEZDItemValueSetter.setValue(slsRepBean.slsRepTocCd, (String) slsRepTocCdMap.get("SLS_REP_TOC_CD"));
            }
            
        }
        ZYPEZDItemValueSetter.setValue(slsRepBean.slsRepCrPct, slsRepCrPct);

        if (S21StringUtil.isEmpty(primRepRoleFlg)) {
            ZYPEZDItemValueSetter.setValue(slsRepBean.slsRepRoleTpCd, "AM");
        } else {
            String key = this.dsImptOrdBean.dsOrdTpCd.getValue() + primRepRoleFlg;
            if (!this.dsImptOrdBean.getCacheSlsRepRoleTpCdMap().containsKey(key)) {
                String slsRepRoleTpCd = NWXC412001.getLineBizRoleTpCd(glblCmpyCd, this.dsImptOrdBean.dsOrdTpCd.getValue(), primRepRoleFlg);
                ZYPEZDItemValueSetter.setValue(slsRepBean.slsRepRoleTpCd, slsRepRoleTpCd);
                this.dsImptOrdBean.getCacheSlsRepRoleTpCdMap().put(key, slsRepRoleTpCd);
            } else {
                String slsRepRoleTpCd = this.dsImptOrdBean.getCacheSlsRepRoleTpCdMap().get(key);
                ZYPEZDItemValueSetter.setValue(slsRepBean.slsRepRoleTpCd, slsRepRoleTpCd);
            }
            
        }

        ZYPEZDItemValueSetter.setValue(slsRepBean.slsCrQuotFlg, getSlsCrQuotFlg(glblCmpyCd, slsRepBean.slsRepRoleTpCd.getValue()));
    }

    private static String getSlsCrQuotFlg(String glblCmpyCd, String slsRepRoleTpCd) {

        String slsCrQuotFlg = null;

        if (ZYPCommonFunc.hasValue(slsRepRoleTpCd)) {

            LINE_BIZ_ROLE_TPTMsg lineBizRoleTp = new LINE_BIZ_ROLE_TPTMsg();
            ZYPEZDItemValueSetter.setValue(lineBizRoleTp.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(lineBizRoleTp.lineBizRoleTpCd, slsRepRoleTpCd);
            lineBizRoleTp = (LINE_BIZ_ROLE_TPTMsg) S21FastTBLAccessor.findByKey(lineBizRoleTp);
            if (lineBizRoleTp != null) {

                slsCrQuotFlg = lineBizRoleTp.slsCrQuotFlg.getValue();
            }
        }

        if (S21StringUtil.isEmpty(slsCrQuotFlg)) {

            return ZYPConstant.FLG_ON_Y;
        }

        return slsCrQuotFlg;
    }
}
