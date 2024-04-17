/*
 * <Pre>Copyright (c) 2017-2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001;

import static com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.HUNDRED_PCT;
import static com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.INTER_REGIONAL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.db.DS_IMPT_ORD_SLS_CRTMsg;
import business.parts.NMZC260001PMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC260001.NMZC260001;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.SLS_CR_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.VAR_CHAR_CONST_NM;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * EOPS Interface Batch EopsDsImptOrdSlsCrBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 * 
 * </pre>
 */
public class EopsDsImptOrdSlsCrBean extends DS_IMPT_ORD_SLS_CRTMsg implements IEopsDsImptOrd {

    /**  */
    private static final long serialVersionUID = 1L;

    /**  */
    private final SLS_CR_TYPE slsCrType;

    /**  */
    private final EopsDsImptOrdBean dsImptOrdBean;

    /**  */
    private final EopsDsImptOrdConfigBean dsImptOrdConfigBean;

    /**  */
    public EopsDsImptOrdSlsCrBean dsImptOrdSlsCrParentBean;

    /**  */
    public final List<EopsDsImptOrdSlsCrBean> dsImptOrdSlsCrChildBeanList;

    /**
     * EopsDsImptOrdSlsCrBean
     * @param slsCrType     NWAB415001Constant.SLS_CR_TYPE
     * @param dsImptOrdBean EopsDsImptOrdBean
     */
    public EopsDsImptOrdSlsCrBean(NWAB415001Constant.SLS_CR_TYPE slsCrType, EopsDsImptOrdBean dsImptOrdBean) {
        this(slsCrType, dsImptOrdBean, null);

        dsImptOrdBean.dsImptOrdSlsCrBean = this;
    }

    private EopsDsImptOrdSlsCrBean(NWAB415001Constant.SLS_CR_TYPE slsCrType, EopsDsImptOrdSlsCrBean parentBean, EopsDsImptOrdBean dsImptOrdBean) {
        this(slsCrType, dsImptOrdBean, null);

        this.dsImptOrdSlsCrParentBean = parentBean;
    }

    /**
     * EopsDsImptOrdSlsCrBean
     * @param slsCrType             NWAB415001Constant.SLS_CR_TYPE
     * @param dsImptOrdConfigBean   EopsDsImptOrdConfigBean
     */
    public EopsDsImptOrdSlsCrBean(NWAB415001Constant.SLS_CR_TYPE slsCrType, EopsDsImptOrdConfigBean dsImptOrdConfigBean) {
        this(slsCrType, dsImptOrdConfigBean.dsImptOrdBean, dsImptOrdConfigBean);

        dsImptOrdConfigBean.dsImptOrdSlsCrBean = this;
    }

    private EopsDsImptOrdSlsCrBean(NWAB415001Constant.SLS_CR_TYPE slsCrType, EopsDsImptOrdSlsCrBean parentBean, EopsDsImptOrdConfigBean dsImptOrdConfigBean) {
        this(slsCrType, dsImptOrdConfigBean.dsImptOrdBean, dsImptOrdConfigBean);

        this.dsImptOrdSlsCrParentBean = parentBean;
    }

    private EopsDsImptOrdSlsCrBean(NWAB415001Constant.SLS_CR_TYPE slsCrType, EopsDsImptOrdBean dsImptOrdBean, EopsDsImptOrdConfigBean dsImptOrdConfigBean) {
        super();

        this.slsCrType = slsCrType;
        this.dsImptOrdBean = dsImptOrdBean;
        this.dsImptOrdConfigBean = dsImptOrdConfigBean;
        this.dsImptOrdSlsCrChildBeanList = new ArrayList<EopsDsImptOrdSlsCrBean>();
    }

    private EopsDsImptOrdSlsCrBean addChildList() {
        EopsDsImptOrdSlsCrBean childBean;
        if (NWAB415001Constant.SLS_CR_TYPE.HEADER.equals(this.slsCrType)) {
            childBean = new EopsDsImptOrdSlsCrBean(this.slsCrType, this, this.dsImptOrdBean);
        } else {
            childBean = new EopsDsImptOrdSlsCrBean(this.slsCrType, this, this.dsImptOrdConfigBean);
        }
        this.dsImptOrdSlsCrChildBeanList.add(childBean);

        return childBean;
    }

    /**
     * doImptMapping
     * @param glblCmpyCd    glblCmpyCd
     * @param salesDate     salesDate
     * @return boolean      result
     */
    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        if (NWAB415001Constant.SLS_CR_TYPE.HEADER.equals(this.slsCrType)) {

            createSlsCr(glblCmpyCd, salesDate, this, this.dsImptOrdBean.nwai415001.somSlsRepId.getValue(), HUNDRED_PCT, ZYPConstant.FLG_ON_Y);

        } else {

            BigDecimal istlRepPercent = null;
            BigDecimal primRepPercent = null;
            BigDecimal acctMgrRepPercent = null;

            String primSlsRepPsnCd = this.dsImptOrdConfigBean.dsImptOrdBean.nwai415001.somSlsRepId.getValue();
            String acctMgrSlsRepPsnCd = this.dsImptOrdConfigBean.dsImptOrdBean.nwai415001.somAcctMgrTxt.getValue();
            String istlSlsRepPsnCd = null;
            String splitTpCd = null;
            if (!NWAB415001Constant.SLS_CR_TYPE.RMA_CONFIG.equals(this.slsCrType)) {
                istlSlsRepPsnCd = this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt.getValue();
                if (!ZYPCommonFunc.hasValue(istlSlsRepPsnCd)) {
                    istlSlsRepPsnCd = this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt.getValue();
                }

                if (this.dsImptOrdConfigBean.nwai415012 != null //
                        && ZYPCommonFunc.hasValue(this.dsImptOrdConfigBean.nwai415012.splitTpTxt)) {
                    splitTpCd = this.dsImptOrdConfigBean.nwai415012.splitTpTxt.getValue();
                }
            }

            //            if (S21StringUtil.isEquals(this.dsImptOrdConfigBean.configCatgCd.getValue(), CONFIG_CATG.OUTBOUND) //
            //                    && "Inter-Regional".equals(splitTpCd)) {
            //
            //                //ASIS from jtf_rs_salesreps by name
            //                if (ZYPCommonFunc.hasValue(this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt)) {
            //                    String psnCd = (String) this.dsImptOrdConfigBean.dsImptOrdBean.getCacheS21PsnFromInstallRepNameMap().get(this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt.getValue());
            //                    if (ZYPCommonFunc.hasValue(psnCd)) {
            //                        istlSlsRepPsnCd = psnCd;
            //                    } else {
            //                        Map<String, Object> psnMap = NWXC412001.getPsnCdByPsnFullNm(glblCmpyCd, this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt.getValue());
            //                        if (psnMap != null && psnMap.get("PSN_CD") != null) {
            //                            istlSlsRepPsnCd = (String) psnMap.get("PSN_CD");
            //                            this.dsImptOrdConfigBean.dsImptOrdBean.getCacheS21PsnFromInstallRepNameMap().put(this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt.getValue(), istlSlsRepPsnCd);
            //                        } else {
            //                            psnMap = NWXC412001.getPsnCdByPsnCd(glblCmpyCd, this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt.getValue());
            //                            if (psnMap != null && psnMap.get("PSN_CD") != null) {
            //                                istlSlsRepPsnCd = (String) psnMap.get("PSN_CD");
            //                                this.dsImptOrdConfigBean.dsImptOrdBean.getCacheS21PsnFromInstallRepNameMap().put(this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt.getValue(), istlSlsRepPsnCd);
            //                            } else {
            //                                psnMap = NWXC412001.getPsnCdByTocNm(glblCmpyCd, this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt.getValue());
            //                                if (psnMap != null && psnMap.get("PSN_CD") != null) {
            //                                    istlSlsRepPsnCd = (String) psnMap.get("PSN_CD");
            //                                    this.dsImptOrdConfigBean.dsImptOrdBean.getCacheS21PsnFromInstallRepNameMap().put(this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt.getValue(), istlSlsRepPsnCd);
            //                                } else {
            //                                    istlSlsRepPsnCd = this.dsImptOrdConfigBean.dsImptOrdBean.nwai415001.istlRepTxt.getValue();
            //                                }
            //                            }
            //                        }
            //                    }
            //                } else {
            //                    istlSlsRepPsnCd = this.dsImptOrdConfigBean.dsImptOrdBean.nwai415001.istlRepTxt.getValue();
            //                }
            //
            //            } else if (S21StringUtil.isEquals(this.dsImptOrdConfigBean.configCatgCd.getValue(), CONFIG_CATG.INBOUND) //
            //                    && this.dsImptOrdConfigBean.nwai415005 == null //
            //                    && "Inter-Regional".equals(splitTpCd)) {
            //
            //                //ASIS from jtf_rs_salesreps by name
            //                if (ZYPCommonFunc.hasValue(this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt)) {
            //                    String psnCd = (String) this.dsImptOrdConfigBean.dsImptOrdBean.getCacheS21PsnFromInstallRepNameMap().get(this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt.getValue());
            //                    if (ZYPCommonFunc.hasValue(psnCd)) {
            //                        istlSlsRepPsnCd = psnCd;
            //                    } else {
            //                        Map<String, Object> psnMap = NWXC412001.getPsnCdByPsnFullNm(glblCmpyCd, this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt.getValue());
            //                        if (psnMap != null && psnMap.get("PSN_CD") != null) {
            //                            istlSlsRepPsnCd = (String) psnMap.get("PSN_CD");
            //                            this.dsImptOrdConfigBean.dsImptOrdBean.getCacheS21PsnFromInstallRepNameMap().put(this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt.getValue(), istlSlsRepPsnCd);
            //                        } else {
            //                            psnMap = NWXC412001.getPsnCdByPsnCd(glblCmpyCd, this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt.getValue());
            //                            if (psnMap != null && psnMap.get("PSN_CD") != null) {
            //                                istlSlsRepPsnCd = (String) psnMap.get("PSN_CD");
            //                                this.dsImptOrdConfigBean.dsImptOrdBean.getCacheS21PsnFromInstallRepNameMap().put(this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt.getValue(), istlSlsRepPsnCd);
            //                            } else {
            //                                psnMap = NWXC412001.getPsnCdByTocNm(glblCmpyCd, this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt.getValue());
            //                                if (psnMap != null && psnMap.get("PSN_CD") != null) {
            //                                    istlSlsRepPsnCd = (String) psnMap.get("PSN_CD");
            //                                    this.dsImptOrdConfigBean.dsImptOrdBean.getCacheS21PsnFromInstallRepNameMap().put(this.dsImptOrdConfigBean.nwai415012.istlRepDescTxt.getValue(), istlSlsRepPsnCd);
            //                                } else {
            //                                    istlSlsRepPsnCd = this.dsImptOrdConfigBean.dsImptOrdBean.nwai415001.istlRepTxt.getValue();
            //                                }
            //                            }
            //                        }
            //                    }
            //                } else {
            //                    istlSlsRepPsnCd = this.dsImptOrdConfigBean.dsImptOrdBean.nwai415001.istlRepTxt.getValue();
            //                }
            //
            //            } else if ("Inter-Regional".equals(splitTpCd)) {
            //
            //                istlSlsRepPsnCd //
            //                = ZYPCommonFunc.hasValue(this.dsImptOrdConfigBean.nwai415005.eopsIstlRepTxt) //
            //                ? this.dsImptOrdConfigBean.nwai415005.eopsIstlRepTxt.getValue() //
            //                        : this.dsImptOrdConfigBean.dsImptOrdBean.nwai415001.istlRepTxt.getValue();
            //            }

            if (INTER_REGIONAL.equals(splitTpCd)) {

                BigDecimal writinigRepSplitPercent //
                = (BigDecimal) this.dsImptOrdConfigBean.dsImptOrdBean.getVarCharConstMap().get(VAR_CHAR_CONST_NM.EOPS_WRITING_REP_SPLIT_PERCENT.name());
                BigDecimal acctMgrSplitPercent //
                = (BigDecimal) this.dsImptOrdConfigBean.dsImptOrdBean.getVarCharConstMap().get(VAR_CHAR_CONST_NM.EOPS_ACCT_MGR_SPLIT_PERCENT.name());

                if (S21StringUtil.isEmpty(acctMgrSlsRepPsnCd)) {
                    //
                    // no account manager
                    if (S21StringUtil.isNotEmpty(istlSlsRepPsnCd) && !S21StringUtil.isEquals(primSlsRepPsnCd, istlSlsRepPsnCd)) {

                        // primary 60%
                        primRepPercent = writinigRepSplitPercent;

                        // install 40%
                        istlRepPercent = HUNDRED_PCT.subtract(writinigRepSplitPercent);
                    } else {

                        // primary 100%
                        primRepPercent = HUNDRED_PCT;
                    }
                } else {

                    // exists account manager
                    if (S21StringUtil.isNotEmpty(istlSlsRepPsnCd)) {

                        if (S21StringUtil.isEquals(acctMgrSlsRepPsnCd, primSlsRepPsnCd)) {

                            if (S21StringUtil.isEquals(primSlsRepPsnCd, istlSlsRepPsnCd)) {
                                // primary 100%
                                primRepPercent = HUNDRED_PCT;
                            } else {
                                // primary 60%
                                primRepPercent = writinigRepSplitPercent;

                                // install 40%
                                istlRepPercent = HUNDRED_PCT.subtract(writinigRepSplitPercent);
                            }
                        } else {

                            // primary 40%
                            primRepPercent = (HUNDRED_PCT.subtract(acctMgrSplitPercent)).divide(BigDecimal.valueOf(2));

                            // install 40%
                            istlRepPercent = primRepPercent;

                            // account manager 20 %
                            acctMgrRepPercent = acctMgrSplitPercent;

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
                            primRepPercent = HUNDRED_PCT.subtract(acctMgrSplitPercent);

                            // account manager 20 %
                            acctMgrRepPercent = acctMgrSplitPercent;
                        } else {

                            // primary 100%
                            primRepPercent = HUNDRED_PCT;
                        }
                    }
                }
            } else {
                // primary 100%
                primRepPercent = HUNDRED_PCT;
            }

            boolean hasParent = false;
            if (primRepPercent != null) {
                EopsDsImptOrdSlsCrBean slsRepBean = null;
                if (hasParent) {

                    slsRepBean = addChildList();
                } else {

                    slsRepBean = this;
                }

                createSlsCr(glblCmpyCd, salesDate, slsRepBean, primSlsRepPsnCd, primRepPercent, ZYPConstant.FLG_ON_Y);
                hasParent = true;
            }

            if (istlRepPercent != null) {
                EopsDsImptOrdSlsCrBean slsRepBean = null;
                if (hasParent) {

                    slsRepBean = addChildList();
                } else {

                    slsRepBean = this;
                }

                createSlsCr(glblCmpyCd, salesDate, slsRepBean, istlSlsRepPsnCd, istlRepPercent, ZYPConstant.FLG_OFF_N);
                hasParent = true;
            }

            if (acctMgrRepPercent != null) {
                EopsDsImptOrdSlsCrBean slsRepBean = null;
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

    private void createSlsCr(//
            String glblCmpyCd //
            , String salesDate //
            , EopsDsImptOrdSlsCrBean slsRepBean //
            , String slsRepPsnCd //
            , BigDecimal slsRepCrPct //
            , String primRepRoleFlg) {

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

        //        if (S21StringUtil.isEmpty(primRepRoleFlg)) {
        //            ZYPEZDItemValueSetter.setValue(slsRepBean.slsRepRoleTpCd, LINE_BIZ_ROLE_TP.ACCOUNT_MANAGER);
        //        } else {
        //            String key = this.dsImptOrdBean.dsOrdTpCd.getValue() + primRepRoleFlg;
        //            if (!this.dsImptOrdBean.getCacheSlsRepRoleTpCdMap().containsKey(key)) {
        //                String slsRepRoleTpCd = NWXC412001.getLineBizRoleTpCd(glblCmpyCd, this.dsImptOrdBean.dsOrdTpCd.getValue(), primRepRoleFlg);
        //                ZYPEZDItemValueSetter.setValue(slsRepBean.slsRepRoleTpCd, slsRepRoleTpCd);
        //                this.dsImptOrdBean.getCacheSlsRepRoleTpCdMap().put(key, slsRepRoleTpCd);
        //            } else {
        //                String slsRepRoleTpCd = this.dsImptOrdBean.getCacheSlsRepRoleTpCdMap().get(key);
        //                ZYPEZDItemValueSetter.setValue(slsRepBean.slsRepRoleTpCd, slsRepRoleTpCd);
        //            }
        //
        //        }
        //        ZYPEZDItemValueSetter.setValue(slsRepBean.slsCrQuotFlg, getSlsCrQuotFlg(glblCmpyCd, slsRepBean.slsRepRoleTpCd.getValue()));

        //---
        ZYPEZDItemValueSetter.setValue(slsRepBean.slsCrQuotFlg, ZYPConstant.FLG_ON_Y); // default value
        if (!ZYPCommonFunc.hasValue(slsRepBean.slsRepTocCd)) {
            return;
        }
        String slsRepTocCd = slsRepBean.slsRepTocCd.getValue();
        NMZC260001PMsg pMsg = callDefSlsReqApi(slsRepBean);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return;
        }
        for (int i = 0; i < pMsg.defSlsRepList.getValidCount(); i++) {
            NMZC260001_defSlsRepListPMsg defSlsRepMsg = pMsg.defSlsRepList.no(i);
            if (!slsRepTocCd.equals(defSlsRepMsg.tocCd.getValue())) {
                return;
            }
            ZYPEZDItemValueSetter.setValue(slsRepBean.slsRepRoleTpCd, defSlsRepMsg.lineBizRoleTpCd);
            ZYPEZDItemValueSetter.setValue(slsRepBean.slsCrQuotFlg, defSlsRepMsg.slsCrQuotFlg);
        }
        //---
    }

    private NMZC260001PMsg callDefSlsReqApi(EopsDsImptOrdSlsCrBean slsRepBean) {

        NMZC260001PMsg pMsg = new NMZC260001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, slsRepBean.dsImptOrdBean.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, slsRepBean.dsImptOrdBean.dsOrdTpCd);

        // call NMZC2600 Default Sales Rep API
        new NMZC260001().execute(pMsg, ONBATCH_TYPE.BATCH);
        return pMsg;
    }

    //    private static String getSlsCrQuotFlg(String glblCmpyCd, String slsRepRoleTpCd) {
    //
    //        String slsCrQuotFlg = null;
    //
    //        if (ZYPCommonFunc.hasValue(slsRepRoleTpCd)) {
    //
    //            LINE_BIZ_ROLE_TPTMsg lineBizRoleTp = new LINE_BIZ_ROLE_TPTMsg();
    //            ZYPEZDItemValueSetter.setValue(lineBizRoleTp.glblCmpyCd, glblCmpyCd);
    //            ZYPEZDItemValueSetter.setValue(lineBizRoleTp.lineBizRoleTpCd, slsRepRoleTpCd);
    //            lineBizRoleTp = (LINE_BIZ_ROLE_TPTMsg) S21FastTBLAccessor.findByKey(lineBizRoleTp);
    //            if (lineBizRoleTp != null) {
    //
    //                slsCrQuotFlg = lineBizRoleTp.slsCrQuotFlg.getValue();
    //            }
    //        }
    //
    //        if (S21StringUtil.isEmpty(slsCrQuotFlg)) {
    //
    //            return ZYPConstant.FLG_ON_Y;
    //        }
    //
    //        return slsCrQuotFlg;
    //    }
}
