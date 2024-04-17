/*
 * <Pre>Copyright (c) 2017-2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.db.DS_IMPT_ORD_CONFIGTMsg;
import business.db.NWAI4150_05TMsg;
import business.db.NWAI4150_06TMsg;
import business.db.NWAI4150_10TMsg;
import business.db.NWAI4150_12TMsg;
import business.db.NWAI4150_13TMsg;
import business.db.NWAI4150_14TMsg;
import business.db.NWAI4150_18TMsg;
import business.db.NWAI4150_23TMsg;
import business.db.NWAI4150_24TMsg;

import static com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.*;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.CONFIG_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.CTAC_PSN_SUB_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.CTAC_PSN_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.CUTOFF_LEN;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Util;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * EOPS Interface to S21 Import Data Batch
 * EopsDsImptOrdDtlBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 *</pre>
 */
public class EopsDsImptOrdConfigBean extends DS_IMPT_ORD_CONFIGTMsg implements IEopsDsImptOrd, IEopsErrorInfo {

    /** default    */
    private static final long serialVersionUID = 1L;

    /**  */
    public final NWAB415001Constant.CONFIG_TYPE configType;

    /**  */
    public final String configKey;

    /**  */
    public static final String configKeyFormat = "%.0f-%.0f";

    /**  */
    public static final String configKeyFormat2 = "%.0f-%s";

    /**  */
    public EopsDsImptOrdBean dsImptOrdBean;

    /**  */
    public final ArrayList<NWAI4150_13TMsg> nwai4150_13List;

    /**  */
    public final ArrayList<NWAI4150_14TMsg> nwai4150_14List;

    /**  */
    public final ArrayList<NWAI4150_23TMsg> nwai4150_23List;

    /**  */
    public final ArrayList<NWAI4150_24TMsg> nwai4150_24List;

    /**  */
    public final ArrayList<EopsDsImptOrdDtlBean> dsImptOrdDtlBeanList;

    /**  */
    public NWAI4150_05TMsg nwai415005;

    /**  */
    public NWAI4150_12TMsg nwai415012;

    /**  */
    public NWAI4150_14TMsg nwai415014;

    /**  */
    public NWAI4150_23TMsg nwai415023;

    /**  */
    public NWAI4150_24TMsg nwai415024;

    /**  */
    private String pSplitTypeTxt;

    /**  */
    private String pPickUpDtOthDescTxt;

    /**  */
    private String pFlPrcListCd;

    /**  */
    private String pPrcCatgCd;

    /**  */
    public NWAI4150_06TMsg serIf06TMsg;

    /**  */
    public NWAI4150_06TMsg noSerIf06TMsg;

    /**  */
    private String pRtrnRsnCd;

    /**  */
    public final List<EopsDsImptSvcDtlBean> dsImptSvcDtlBeanList;

    /**  */
    public final List<Map<String, Object>> existingConfigurationList;

    /**  */
    public final List<NWAB415001ErrorInfo> errInfoList;

    /**  */
    public final List<EopsDsImptOrdRtrnDtlBean> dsImptOrdDtlRtrnBeanList;

    /**  */
    public final List<EopsDsImptOrdCtacPsnBean> dsImptOrdCtacPsnBeanList;

    //    private ArrayList<EopsDsImptSvcConfigRefBean> dsImptSvcConfigRefBeanList;
    //
    //    private ArrayList<EopsDsImptSvcPrcBean> dsImptSvcPrcBeanList;
    //
    //    private ArrayList<EopsDsImptSvcUsgPrcBean> dsImptSvcUsgPrcBeanList;

    /**  */
    boolean isExistingConfigurationFlag;

    /**  */
    boolean isAddAccessoryToExistingConfigurationFlag;

    /**  */
    public EopsDsImptOrdDelyInfoBean dsImptOrdDelyInfoBean;

    /**  */
    public EopsDsImptOrdIstlInfoBean dsImptOrdIstlInfoBean;

    /**  */
    private BigDecimal pSvcConfigMstrPk;

    /**  */
    public EopsDsImptOrdSlsCrBean dsImptOrdSlsCrBean;

    /**  */
    public EopsDsImptOrdSiteSrvyBean dsImptOrdSiteSrvyBean;

    /**  */
    private Map<String, Object> pVndMap;

    /**
     * @param configType    NWAB415001Constant.CONFIG_TYPE
     * @param dsImptOrdBean EopsDsImptOrdBean
     * @param nwai415005    NWAI4150_05TMsg
     * @param serIf06TMsg   NWAI4150_06TMsg
     * @param noSerIf06TMsg NWAI4150_06TMsg
     */
    public EopsDsImptOrdConfigBean(NWAB415001Constant.CONFIG_TYPE configType, EopsDsImptOrdBean dsImptOrdBean, NWAI4150_05TMsg nwai415005, NWAI4150_06TMsg serIf06TMsg, NWAI4150_06TMsg noSerIf06TMsg) {
        this(configType, dsImptOrdBean, createConfigKey(nwai415005));

        this.nwai415005 = nwai415005;
        this.pSplitTypeTxt = this.nwai415005.splitTpTxt.getValue();
        this.serIf06TMsg = serIf06TMsg;
        this.noSerIf06TMsg = noSerIf06TMsg;
        this.pRtrnRsnCd = NWXC412001.getRtrnRsnCd(dsImptOrdBean.glblCmpyCd.getValue(), CPO_SRC_TP.EOPS, NWAB415001Constant.INTERFACE_ID.NWAI4150.name(), this.nwai415005.quoteLineTpTxt.getValue(), this.nwai415005.somMercItemCd.getValue());
        //QC#20739
        String pickUpDtOthDescTxt = null;
        if (nwai415005 != null && ZYPCommonFunc.hasValue(nwai415005.erlstDelyTs)) {
            if (nwai415005.erlstDelyTs.getValue().length() > 8) {
                pickUpDtOthDescTxt = nwai415005.erlstDelyTs.getValue().substring(0, 8);
            } else if (nwai415005.erlstDelyTs.getValue().length() == 8) {
                pickUpDtOthDescTxt = nwai415005.erlstDelyTs.getValue();
            }
        }
        if (serIf06TMsg != null && !ZYPCommonFunc.hasValue(pickUpDtOthDescTxt) && ZYPCommonFunc.hasValue(serIf06TMsg.pickUpDtOthTs)) {
            if (serIf06TMsg.pickUpDtOthTs.getValue().length() > 8) {
                pickUpDtOthDescTxt = serIf06TMsg.pickUpDtOthTs.getValue().substring(0, 8);
            } else if (serIf06TMsg.pickUpDtOthTs.getValue().length() == 8) {
                pickUpDtOthDescTxt = serIf06TMsg.pickUpDtOthTs.getValue();
            }
        }
        if (noSerIf06TMsg != null && !ZYPCommonFunc.hasValue(pickUpDtOthDescTxt) && ZYPCommonFunc.hasValue(noSerIf06TMsg.pickUpDtOthTs)) {
            if (noSerIf06TMsg.pickUpDtOthTs.getValue().length() > 8) {
                pickUpDtOthDescTxt = noSerIf06TMsg.pickUpDtOthTs.getValue().substring(0, 8);
            } else if (noSerIf06TMsg.pickUpDtOthTs.getValue().length() == 8) {
                pickUpDtOthDescTxt = noSerIf06TMsg.pickUpDtOthTs.getValue();
            }
        }
        this.pPickUpDtOthDescTxt = pickUpDtOthDescTxt;
    }

    /**
     * 
     * @param configType    NWAB415001Constant.CONFIG_TYPE
     * @param dsImptOrdBean EopsDsImptOrdBean
     * @param nwai415012    NWAI4150_12TMsg
     */
    public EopsDsImptOrdConfigBean(NWAB415001Constant.CONFIG_TYPE configType, EopsDsImptOrdBean dsImptOrdBean, NWAI4150_12TMsg nwai415012) {
        this(configType, dsImptOrdBean, createConfigKey(nwai415012));

        this.nwai415012 = nwai415012;
        this.pSplitTypeTxt = this.nwai415012.splitTpTxt.getValue();
        this.pPickUpDtOthDescTxt = null;
        // floor price list
        if (ZYPCommonFunc.hasValue(this.nwai415012.flPrcListId)) {
            if (!dsImptOrdBean.getCachePrcCatgCdMap().containsKey(this.nwai415012.flPrcListId.getValue().toPlainString())) {
                this.pFlPrcListCd = NWXC412001.getPrcCatgCdFromId(dsImptOrdBean.glblCmpyCd.getValue(), this.nwai415012.flPrcListId.getValue());
                dsImptOrdBean.getCachePrcCatgCdMap().put(this.nwai415012.flPrcListId.getValue().toPlainString(), this.pFlPrcListCd);
            } else {
                this.pFlPrcListCd = (String) dsImptOrdBean.getCachePrcCatgCdMap().get(this.nwai415012.flPrcListId.getValue().toPlainString());
            }
        }
        if (!ZYPCommonFunc.hasValue(this.pFlPrcListCd)) {
            // default price list
            if (!dsImptOrdBean.getCachePrcCatgNmMap().containsKey((String) dsImptOrdBean.getVarCharConstMap().get(NWAB415001Constant.VAR_CHAR_CONST_NM.EOPS_DEFAULT_PRICE_LIST.name()))) {
                this.pFlPrcListCd = NWXC412001.getPrcCatgCdFromName(dsImptOrdBean.glblCmpyCd.getValue(), (String) dsImptOrdBean.getVarCharConstMap().get(NWAB415001Constant.VAR_CHAR_CONST_NM.EOPS_DEFAULT_PRICE_LIST.name()));
                dsImptOrdBean.getCachePrcCatgNmMap().put((String) dsImptOrdBean.getVarCharConstMap().get(NWAB415001Constant.VAR_CHAR_CONST_NM.EOPS_DEFAULT_PRICE_LIST.name()), this.pFlPrcListCd);
            } else {
                this.pFlPrcListCd = (String) dsImptOrdBean.getCachePrcCatgNmMap().get((String) dsImptOrdBean.getVarCharConstMap().get(NWAB415001Constant.VAR_CHAR_CONST_NM.EOPS_DEFAULT_PRICE_LIST.name()));
            }
        }
        // price list
        if (!this.dsImptOrdBean.nwai415001.eopsTrxTpTxt.getValue().equals(NWAB415001Constant.TRX_TP_TXT_RENTAL)) {

            this.pPrcCatgCd = NWXC412001.getPrcCatgCdForLine(dsImptOrdBean.glblCmpyCd.getValue(), this.dsImptOrdBean.nwai415001.bidPrcListNm.getValue(), this.nwai415012.bidPrcListNm.getValue(), this.nwai415012.bidPrcListId.getValue());
            if (S21StringUtil.isNotEmpty(this.dsImptOrdBean.nwai415001.bidPrcListNm.getValue())) {
                if (!dsImptOrdBean.getCachePrcCatgNmMap().containsKey(this.dsImptOrdBean.nwai415001.bidPrcListNm.getValue())) {
                    this.pPrcCatgCd = NWXC412001.getPrcCatgCdFromName(dsImptOrdBean.glblCmpyCd.getValue(), this.dsImptOrdBean.nwai415001.bidPrcListNm.getValue());
                    dsImptOrdBean.getCachePrcCatgNmMap().put(this.dsImptOrdBean.nwai415001.bidPrcListNm.getValue(), this.pPrcCatgCd);
                } else {
                    this.pPrcCatgCd = (String) dsImptOrdBean.getCachePrcCatgNmMap().get(this.dsImptOrdBean.nwai415001.bidPrcListNm.getValue());
                }
            }
        }

        if (!S21StringUtil.isNotEmpty(this.pPrcCatgCd)) {
            if (S21StringUtil.isNotEmpty(this.nwai415012.bidPrcListNm.getValue())) {
                if (!dsImptOrdBean.getCachePrcCatgNmMap().containsKey(this.nwai415012.bidPrcListNm.getValue())) {
                    this.pPrcCatgCd = NWXC412001.getPrcCatgCdFromName(dsImptOrdBean.glblCmpyCd.getValue(), this.nwai415012.bidPrcListNm.getValue());
                    dsImptOrdBean.getCachePrcCatgNmMap().put(this.nwai415012.bidPrcListNm.getValue(), this.pPrcCatgCd);
                } else {
                    this.pPrcCatgCd = (String) dsImptOrdBean.getCachePrcCatgNmMap().get(this.nwai415012.bidPrcListNm.getValue());
                }
            }
            if (!S21StringUtil.isNotEmpty(this.pPrcCatgCd)) {
                if (this.nwai415012.bidPrcListId.getValue() != null && this.nwai415012.bidPrcListId.getValue().compareTo(BigDecimal.ZERO) != 0) {
                    if (!dsImptOrdBean.getCachePrcCatgCdMap().containsKey(this.nwai415012.bidPrcListId.getValue().toPlainString())) {
                        this.pPrcCatgCd = NWXC412001.getPrcCatgCdFromId(dsImptOrdBean.glblCmpyCd.getValue(), this.nwai415012.bidPrcListId.getValue());
                        dsImptOrdBean.getCachePrcCatgCdMap().put(this.nwai415012.bidPrcListId.getValue().toPlainString(), this.pPrcCatgCd);
                    } else {
                        this.pPrcCatgCd = (String) dsImptOrdBean.getCachePrcCatgCdMap().get(this.nwai415012.flPrcListId.getValue().toPlainString());
                    }
                }
            }
            if (!S21StringUtil.isNotEmpty(this.pPrcCatgCd)) {
                if (ZYPCommonFunc.hasValue(this.nwai415012.flPrcListId) && this.nwai415012.flPrcListId.getValue().compareTo(BigDecimal.ZERO) != 0) {
                    if (!dsImptOrdBean.getCachePrcCatgCdMap().containsKey(this.nwai415012.flPrcListId.getValue().toPlainString())) {
                        this.pPrcCatgCd = NWXC412001.getPrcCatgCdFromId(dsImptOrdBean.glblCmpyCd.getValue(), this.nwai415012.flPrcListId.getValue());
                        dsImptOrdBean.getCachePrcCatgCdMap().put(this.nwai415012.flPrcListId.getValue().toPlainString(), this.pFlPrcListCd);
                    } else {
                        this.pPrcCatgCd = (String) dsImptOrdBean.getCachePrcCatgCdMap().get(this.nwai415012.flPrcListId.getValue().toPlainString());
                    }
                }
            }

        }
    }

    public EopsDsImptOrdConfigBean(CONFIG_TYPE configType, EopsDsImptOrdBean dsImptOrdBean, NWAI4150_23TMsg nwai415023) {
        this(configType, dsImptOrdBean, createConfigKey(nwai415023));

        this.nwai415023 = nwai415023;
    }

    public EopsDsImptOrdConfigBean(CONFIG_TYPE configType, EopsDsImptOrdBean dsImptOrdBean, NWAI4150_24TMsg nwai415024) {
        this(configType, dsImptOrdBean, createConfigKey(nwai415024));

        this.nwai415024 = nwai415024;
    }

    /**
     * EopsDsImptOrdConfigBean
     * @param configType    NWAB415001Constant.CONFIG_TYPE
     * @param dsImptOrdBean EopsDsImptOrdBean
     * @param configKey     String
     */
    public EopsDsImptOrdConfigBean(NWAB415001Constant.CONFIG_TYPE configType, EopsDsImptOrdBean dsImptOrdBean, String configKey) {
        super();

        this.configType = configType;
        this.configKey = configKey;
        this.dsImptOrdBean = dsImptOrdBean;
        this.nwai4150_13List = new ArrayList<NWAI4150_13TMsg>();
        this.nwai4150_14List = new ArrayList<NWAI4150_14TMsg>();
        this.nwai4150_23List = new ArrayList<NWAI4150_23TMsg>();
        this.nwai4150_24List = new ArrayList<NWAI4150_24TMsg>();

        this.dsImptOrdDtlBeanList = new ArrayList<EopsDsImptOrdDtlBean>();
        this.dsImptOrdDtlRtrnBeanList = new ArrayList<EopsDsImptOrdRtrnDtlBean>();
        this.dsImptOrdCtacPsnBeanList = new ArrayList<EopsDsImptOrdCtacPsnBean>();

        this.dsImptSvcDtlBeanList = new ArrayList<EopsDsImptSvcDtlBean>();
        //        this.dsImptSvcConfigRefBeanList = new ArrayList<EopsDsImptSvcConfigRefBean>();
        //        this.dsImptSvcPrcBeanList = new ArrayList<EopsDsImptSvcPrcBean>();
        //        this.dsImptSvcAddlBaseBeanList = new ArrayList<EopsDsImptSvcAddlBaseBean>();
        //        this.dsImptSvcAddlChrgBeanList = new ArrayList<EopsDsImptSvcAddlChrgBean>();
        //        this.dsImptSvcUsgPrcBeanList = new ArrayList<EopsDsImptSvcUsgPrcBean>();
        this.errInfoList = new ArrayList<NWAB415001ErrorInfo>();

        this.isExistingConfigurationFlag = false;
        this.isAddAccessoryToExistingConfigurationFlag = false;
        this.existingConfigurationList = new ArrayList<Map<String, Object>>();

        String pVndCd = (String) dsImptOrdBean.getVarCharConstMap().get(NWAB415001Constant.VAR_CHAR_CONST_NM.CFS_VND_CD_FOR_EOPS.name());
        this.pVndMap //
        = NWXC412001.getVendorInfo(dsImptOrdBean.glblCmpyCd.getValue(), pVndCd, dsImptOrdBean.slsDt, (String) dsImptOrdBean.getVarCharConstMap().get(VAR_CHAR_CONST_NM.ARCS_SPLY_SITE_CD_FOR_EOPS.name()));

    }

    /**
     * createConfigKey
     * @param nwai415012    NWAI4150_12TMsg
     * @return somQuoteId-somConfigId
     */
    public static String createConfigKey(NWAI4150_12TMsg nwai415012) {
        return String.format(configKeyFormat, nwai415012.somQuoteId.getValue(), nwai415012.somConfigId.getValue());
    }

    /**
     * createConfigKey
     * @param nwai415005 NWAI4150_05TMsg
     * @return somQuoteId-somDescSerNum
     */
    private static String createConfigKey(NWAI4150_05TMsg nwai415005) {
        return String.format(configKeyFormat2, nwai415005.somQuoteId.getValue(), nwai415005.somDescSerNum.getValue());
    }

    private static String createConfigKey(NWAI4150_24TMsg nwai415024) {
        return String.format(configKeyFormat, nwai415024.somQuoteId.getValue(), nwai415024.somConfigId.getValue());
    }

    private static String createConfigKey(NWAI4150_23TMsg nwai415023) {
        return String.format(configKeyFormat, nwai415023.somQuoteId.getValue(), nwai415023.somConfigId.getValue());
    }

    /**
     * isShipConfig
     * @return if REGULAR_SHIP_CONFIG then true.
     */
    public boolean isShipConfig() {
        return this.configType.equals(NWAB415001Constant.CONFIG_TYPE.REGULAR_SHIP_CONFIG);
    }

    /**
     * addDsImptOrdDtlBean
     * @param dsImptOrdDtlBean EopsDsImptOrdDtlBean
     */
    public void addDsImptOrdDtlBean(EopsDsImptOrdDtlBean dsImptOrdDtlBean) {
        this.dsImptOrdDtlBeanList.add(dsImptOrdDtlBean);
        this.dsImptOrdBean.dsImptOrdDtlBeanList.add(dsImptOrdDtlBean);
    }

    /**
     * addDsImptOrdDtlBean
     * @param dsImptOrdRtrnDtlBean EopsDsImptOrdRtrnDtlBean
     */
    public void addDsImptOrdDtlBean(EopsDsImptOrdRtrnDtlBean dsImptOrdRtrnDtlBean) {
        this.dsImptOrdDtlRtrnBeanList.add(dsImptOrdRtrnDtlBean);
        this.dsImptOrdBean.dsImptOrdRtrnDtlBean.add(dsImptOrdRtrnDtlBean);
    }

    /**
     * addDsImptOrdCtacPsnBean
     * @param ctacPsnType CTAC_PSN_TYPE
     */
    public void addDsImptOrdCtacPsnBean(CTAC_PSN_TYPE ctacPsnType) {

        if ((this.nwai4150_14List == null || this.nwai4150_14List.size() == 0) && this.nwai415012 != null) {
            new EopsDsImptOrdCtacPsnBean(ctacPsnType, CTAC_PSN_SUB_TYPE.C_PRIMARY, this);
            new EopsDsImptOrdCtacPsnBean(ctacPsnType, CTAC_PSN_SUB_TYPE.C_SECONDARY, this);

            String admin = "ADMIN";
            String usr = "USER";

            String csaPmtTpTxt = NWXC220001Util.convToString(this.nwai415012.csaPmtTpTxt.getValue(), "");

            if (admin != null && csaPmtTpTxt.toUpperCase().indexOf(admin) >= 0) {
                new EopsDsImptOrdCtacPsnBean(ctacPsnType, CTAC_PSN_SUB_TYPE.C_EMANAGE_ADMIN, this);
            }

            if (usr != null && csaPmtTpTxt.toUpperCase().indexOf(usr) >= 0) {
                new EopsDsImptOrdCtacPsnBean(ctacPsnType, CTAC_PSN_SUB_TYPE.C_EMANAGE_USER, this);
            }
        }

        if (this.nwai415005 != null && this.noSerIf06TMsg != null) {
            new EopsDsImptOrdCtacPsnBean(ctacPsnType, CTAC_PSN_SUB_TYPE.C_PICKUP, this);
        }

        for (NWAI4150_14TMsg if14TMsg : this.nwai4150_14List) {
            new EopsDsImptOrdCtacPsnBean(ctacPsnType, CTAC_PSN_SUB_TYPE.C_PICKUP, this, if14TMsg);
        }

    }

    /**
     * getSomMdlDescTxt
     * @return somMdlDescTxt
     */
    public String getSomMdlDescTxt() {
        for (EopsDsImptOrdDtlBean dtlBean : dsImptOrdDtlBeanList) {
            if (dtlBean.nwai415010 != null && ZYPCommonFunc.hasValue(dtlBean.nwai415010.somMdlDescTxt)) {
                return dtlBean.nwai415010.somMdlDescTxt.getValue();
            }
        }

        return null;
    }

    /**
     * EopsDsImptSvcDtlBean
     * @param nwai415018 NWAI4150_18TMsg
     * @return svcDtlBean
     */
    public EopsDsImptSvcDtlBean getDsImptSvcDtlBeanByNwai4150_18(NWAI4150_18TMsg nwai415018) {
        for (EopsDsImptSvcDtlBean svcDtlBean : this.dsImptSvcDtlBeanList) {
            if (svcDtlBean.nwai4150_18 == nwai415018) {
                return svcDtlBean;
            }
        }

        return null;
    }

    //    public EopsDsImptSvcDtlBean getDsImptSvcDtlBeanByNwai4150_19(NWAI4150_19TMsg nwai4150_19) {
    //        for (EopsDsImptSvcDtlBean svcDtlBean : this.dsImptSvcDtlBeanList) {
    ////            if (svcDtlBean.nwai4150_18 == nwai4150_18) { //XXX 19??
    ////                return svcDtlBean;
    ////            }
    //        }
    //
    //        return null;
    //    }

    /**
     * doImptMapping
     * @param glblCmpyCd    glblCmpyCd
     * @param salesDate     salesDate
     * @return boolean
     */
    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {
        boolean isSuccess = true;

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CONFIG_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);

        // configuration category
        if (CONFIG_TYPE.REGULAR_SHIP_CONFIG.equals(configType)) {
            ZYPEZDItemValueSetter.setValue(this.configCatgCd, CONFIG_CATG.OUTBOUND);
        } else {
            ZYPEZDItemValueSetter.setValue(this.configCatgCd, CONFIG_CATG.INBOUND);
        }

        if (NWAB415001Constant.CONFIG_TYPE.REGULAR_SHIP_CONFIG.equals(configType)) {

            if (this.existingConfigurationList != null && this.existingConfigurationList.size() > 0) {
                Map<String, Object> existingConfigurationMap = this.existingConfigurationList.get(0);
                if (existingConfigurationMap != null) {
                    ZYPEZDItemValueSetter.setValue(this.svcConfigMstrPk, (BigDecimal) existingConfigurationMap.get("SVC_CONFIG_MSTR_PK"));
                    ZYPEZDItemValueSetter.setValue(this.mdlId, (BigDecimal) existingConfigurationMap.get("MDL_ID"));
                    ZYPEZDItemValueSetter.setValue(this.mdlDescTxt, (String) existingConfigurationMap.get("MDL_NM"));
                }
            }

            if (!ZYPCommonFunc.hasValue(this.mdlId)) {
                //QC#17118
                BigDecimal mdlId = null;
                if (!this.dsImptOrdBean.getCacheModelMap().containsKey(this.getSomMdlDescTxt())) {
                    mdlId = NWXC412001.getMdlIdByName(glblCmpyCd, this.getSomMdlDescTxt());
                    this.dsImptOrdBean.getCacheModelMap().put(this.getSomMdlDescTxt(), mdlId);
                } else {
                    mdlId = this.dsImptOrdBean.getCacheModelMap().get(this.getSomMdlDescTxt());
                }
                if (ZYPCommonFunc.hasValue(mdlId)) {
                    ZYPEZDItemValueSetter.setValue(this.mdlId, mdlId);
                } else {
                    this.mdlId.clear();
                }
            }
            if (!ZYPCommonFunc.hasValue(this.mdlDescTxt)) {
                ZYPEZDItemValueSetter.setValue(//
                        this.mdlDescTxt, S21StringUtil.subStringByLength(this.getSomMdlDescTxt(), 0, CUTOFF_LEN.MDL_DESC_TXT.getLen()));
            }

        } else if (NWAB415001Constant.CONFIG_TYPE.OCE_PROMOTION_RMA.equals(configType)) {

            if (!this.nwai4150_14List.isEmpty()) {

                for (NWAI4150_14TMsg i14TMsg : this.nwai4150_14List) {

                    if (ZYPCommonFunc.hasValue(i14TMsg.somPrmoSerNum) && !ZYPCommonFunc.hasValue(this.svcConfigMstrPk)) {

                        Map<String, Object> machine //
                        = NWXC412001.getSvcMachMstrBySerNumAndMdseCd(glblCmpyCd, i14TMsg.somPrmoSerNum.getValue(), null, salesDate);
                        if (machine != null && !machine.isEmpty()) {

                            ZYPEZDItemValueSetter.setValue(//
                                    this.svcConfigMstrPk, NWXC412001.getSvcConfigMstrPkBySerNum(glblCmpyCd, i14TMsg.somPrmoSerNum.getValue(), salesDate));
                        }
                    }

                    BigDecimal mdlId = null;
                    String somPrmoMdlNm = i14TMsg.somPrmoMdlNm.getValue();
                    if (!this.dsImptOrdBean.getCacheModelMap().containsKey(somPrmoMdlNm)) {
                        mdlId = NWXC412001.getMdlIdByName(glblCmpyCd, somPrmoMdlNm);
                        this.dsImptOrdBean.getCacheModelMap().put(somPrmoMdlNm, mdlId);
                    } else {
                        mdlId = this.dsImptOrdBean.getCacheModelMap().get(somPrmoMdlNm);
                    }
                    if (ZYPCommonFunc.hasValue(mdlId)) {
                        ZYPEZDItemValueSetter.setValue(this.mdlId, mdlId);
                        ZYPEZDItemValueSetter.setValue(//
                                this.mdlDescTxt, S21StringUtil.subStringByLength(i14TMsg.somPrmoMdlNm.getValue(), 0, CUTOFF_LEN.MDL_DESC_TXT.getLen()));
                    } else {
                        this.mdlId.clear();
                        this.mdlDescTxt.clear();
                    }
                }
            }
        } else if (NWAB415001Constant.CONFIG_TYPE.EOPS_RMA.equals(configType)) {
            if (!this.nwai4150_24List.isEmpty()) {

                for (NWAI4150_24TMsg nwai415024 : this.nwai4150_24List) {

                    if (ZYPCommonFunc.hasValue(nwai415024.somDescSerNum) && !ZYPCommonFunc.hasValue(this.svcConfigMstrPk)) {

                        Map<String, Object> machine //
                        = NWXC412001.getSvcMachMstrBySerNumAndMdseCd(glblCmpyCd, nwai415024.somDescSerNum.getValue(), null, salesDate);
                        if (machine != null && !machine.isEmpty()) {

                            ZYPEZDItemValueSetter.setValue(//
                                    this.svcConfigMstrPk, NWXC412001.getSvcConfigMstrPkBySerNum(glblCmpyCd, nwai415024.somDescSerNum.getValue(), salesDate));
                        }
                    }

                    BigDecimal mdlId = null;
                    String somMdlDescTxt = nwai415024.somMdlDescTxt.getValue();
                    if (!this.dsImptOrdBean.getCacheModelMap().containsKey(somMdlDescTxt)) {
                        mdlId = NWXC412001.getMdlIdByName(glblCmpyCd, somMdlDescTxt);
                        this.dsImptOrdBean.getCacheModelMap().put(somMdlDescTxt, mdlId);
                    } else {
                        mdlId = this.dsImptOrdBean.getCacheModelMap().get(somMdlDescTxt);
                    }
                    if (ZYPCommonFunc.hasValue(mdlId)) {
                        ZYPEZDItemValueSetter.setValue(this.mdlId, mdlId);
                        ZYPEZDItemValueSetter.setValue(//
                                this.mdlDescTxt, S21StringUtil.subStringByLength(nwai415024.somMdlDescTxt.getValue(), 0, CUTOFF_LEN.MDL_DESC_TXT.getLen()));
                    } else {
                        this.mdlId.clear();
                        this.mdlDescTxt.clear();
                    }
                }
            }

        } else {

            // RMA
            String somDescSerNum = null;
            if (NWAB415001Constant.CONFIG_TYPE.TRADE_IN_RMA.equals(configType)) {
                if (this.nwai415005 != null) {
                    somDescSerNum = this.nwai415005.somDescSerNum.getValue();
                }
            } else {
                if (this.serIf06TMsg != null) {
                    somDescSerNum = this.serIf06TMsg.somDescSerNum.getValue();
                }
            }
            if (ZYPCommonFunc.hasValue(somDescSerNum)) {

                Map<String, Object> machine = NWXC412001.getSvcMachMstrBySerNumAndMdseCd(glblCmpyCd, somDescSerNum, null, salesDate);
                if (machine != null && !machine.isEmpty()) {

                    ZYPEZDItemValueSetter.setValue(this.svcConfigMstrPk, (BigDecimal) machine.get("SVC_CONFIG_MSTR_PK"));
                    ZYPEZDItemValueSetter.setValue(this.mdlId, (BigDecimal) machine.get("MDL_ID"));
                    ZYPEZDItemValueSetter.setValue(this.mdlDescTxt, (String) machine.get("T_MDL_NM"));
                }
            }

            //QC#20606
            if (!ZYPCommonFunc.hasValue(this.mdlId)) {
                BigDecimal mdlId = null;
                if (!this.dsImptOrdBean.getCacheModelMap().containsKey(this.nwai415005.somMdlTxt.getValue())) {
                    mdlId = NWXC412001.getMdlIdByName(glblCmpyCd, this.nwai415005.somMdlTxt.getValue());
                    this.dsImptOrdBean.getCacheModelMap().put(this.nwai415005.somMdlTxt.getValue(), mdlId);
                } else {
                    mdlId = this.dsImptOrdBean.getCacheModelMap().get(this.nwai415005.somMdlTxt.getValue());
                }
                if (ZYPCommonFunc.hasValue(mdlId)) {
                    ZYPEZDItemValueSetter.setValue(this.mdlId, mdlId);
                    ZYPEZDItemValueSetter.setValue(//
                            this.mdlDescTxt, S21StringUtil.subStringByLength(this.nwai415005.somMdlTxt.getValue(), 0, CUTOFF_LEN.MDL_DESC_TXT.getLen()));
                } else {
                    this.mdlId.clear();
                    this.mdlDescTxt.clear();
                }
            }
        }

        //
        if (ZYPCommonFunc.hasValue(this.svcConfigMstrPk)) {
            setSvcConfigMstrPk(NWXC412001.getSvcMachMstrPKBySvcConfigMstrPk(glblCmpyCd, this.svcConfigMstrPk.getValue()));
        }

        ZYPEZDItemValueSetter.setValue(this.billToCustAcctCd, this.dsImptOrdBean.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(this.billToCustLocCd, this.dsImptOrdBean.billToCustCd);

        // ship to information
        Map<String, Object> shipTo = null;
        String shipToCustAcctCd = null;
        String shipToCustLocCd = null;
        String shipToLocNm = null;
        String shipToAddlLocNm = null;
        String shipToFirstLineAddr = null;
        String shipToScdLineAddr = null;
        String shipToThirdLineAddr = null;
        String shipToFrthLineAddr = null;
        String shipToCtyAddr = null;
        String shipToStCd = null;
        String shipToProvNm = null;
        String shipToCntyNm = null;
        String shipToPostCd = null;
        String shipToCtryCd = null;
        String shipToAcctNm = null;
        if (NWAB415001Constant.CONFIG_TYPE.REGULAR_SHIP_CONFIG.equals(configType)) {

            if (!ZYPCommonFunc.hasValue(this.nwai415012.somPsnCnt) //
                    || BigDecimal.ZERO.compareTo(this.nwai415012.somPsnCnt.getValue()) == 0) {

                //QC#20753
                // from address
                String address = S21StringUtil.concatStrings(S21StringUtil.trimString(S21StringUtil.concatStrings(NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai415012.somShipToAddr_01.getValue())), " ", NWXC412001
                        .toUpperCase(S21StringUtil.trimString(this.nwai415012.somShipToAddr_02.getValue())))), NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai415012.somCityAddr.getValue())), NWXC412001.toUpperCase(S21StringUtil
                        .trimString(this.nwai415012.somStCd.getValue())), NWXC412001.toUpperCase(NWXC412001.cutString(S21StringUtil.trimString(this.nwai415012.somZipCd.getValue()), 5)));

                if (!this.dsImptOrdBean.getCacheShipToCustMap().containsKey("FROMADDR" + this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue() + address)) {
                    shipTo = NWXC412001.getShipToInfoFromAddress(glblCmpyCd, address, salesDate, this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue());
                    this.dsImptOrdBean.getCacheShipToCustMap().put("FROMADDR" + this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue() + address, shipTo);
                } else {
                    shipTo = this.dsImptOrdBean.getCacheShipToCustMap().get("FROMADDR" + this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue() + address);
                }

            } else {

                if (!this.dsImptOrdBean.getCacheShipToCustMap().containsKey("FROMPSN" + this.nwai415012.somPsnCnt.getValue().toPlainString())) {
                    shipTo = NWXC412001.getShipToInfoFromSomPsnCnt(glblCmpyCd, this.nwai415012.somPsnCnt.getValue(), salesDate);
                    this.dsImptOrdBean.getCacheShipToCustMap().put("FROMPSN" + this.nwai415012.somPsnCnt.getValue().toPlainString(), shipTo);
                } else {
                    shipTo = this.dsImptOrdBean.getCacheShipToCustMap().get("FROMPSN" + this.nwai415012.somPsnCnt.getValue().toPlainString());
                }

            }
            if (shipTo == null || shipTo.isEmpty()) {

                shipToCustAcctCd = S21StringUtil.subStringByLength(this.nwai415012.somAcctNum.getValue(), 0, 20);
                shipToCustLocCd = null;
                //shipToLocNm = null;
                shipToLocNm = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue(), 0, 60);
                shipToAddlLocNm = null;
                String address = S21StringUtil.concatStrings(this.nwai415012.somShipToAddr_01.getValue(), this.nwai415012.somShipToAddr_02.getValue());
                shipToFirstLineAddr = S21StringUtil.subStringByLength(address, 0, 60);
                shipToScdLineAddr = S21StringUtil.subStringByLength(address, 60, 60);
                shipToThirdLineAddr = S21StringUtil.subStringByLength(address, 120, 60);
                shipToFrthLineAddr = S21StringUtil.subStringByLength(address, 240, 60);
                shipToCtyAddr = S21StringUtil.subStringByLength(this.nwai415012.somCityAddr.getValue(), 0, 25);
                shipToStCd = S21StringUtil.subStringByLength(this.nwai415012.somStCd.getValue(), 0, 2);
                shipToProvNm = null;
                shipToCntyNm = S21StringUtil.subStringByLength(this.nwai415012.somCntyNm.getValue(), 0, 30);
                shipToPostCd = S21StringUtil.subStringByLength(this.nwai415012.somZipCd.getValue(), 0, 15);
                shipToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;
                shipToAcctNm = null;
            }

        } else if (NWAB415001Constant.CONFIG_TYPE.OCE_PROMOTION_RMA.equals(configType)) {

            NWAI4150_14TMsg nwai415014 = null;
            if (!this.nwai4150_14List.isEmpty()) {

                nwai415014 = this.nwai4150_14List.get(0);
                //QC#20753
                // from address
                String address = S21StringUtil.concatStrings(S21StringUtil.trimString(S21StringUtil.concatStrings(NWXC412001.toUpperCase(S21StringUtil.trimString(nwai415014.somPrmoAddr_01.getValue())), " ", NWXC412001
                        .toUpperCase(S21StringUtil.trimString(nwai415014.somPrmoAddr_02.getValue())))), NWXC412001.toUpperCase(S21StringUtil.trimString(nwai415014.somPrmoCityTxt.getValue())), NWXC412001.toUpperCase(S21StringUtil
                        .trimString(nwai415014.somPrmoStCd.getValue())), NWXC412001.toUpperCase(NWXC412001.cutString(S21StringUtil.trimString(nwai415014.somPrmoZipCd.getValue()), 5)));

                if (!this.dsImptOrdBean.getCacheShipToCustMap().containsKey("FROMADDR" + this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue() + address)) {
                    shipTo = NWXC412001.getShipToInfoFromAddress(glblCmpyCd, address, salesDate, this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue());
                    this.dsImptOrdBean.getCacheShipToCustMap().put("FROMADDR" + this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue() + address, shipTo);
                } else {
                    shipTo = this.dsImptOrdBean.getCacheShipToCustMap().get("FROMADDR" + this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue() + address);
                }

            }
            if (shipTo == null || shipTo.isEmpty()) {

                if (nwai415014 != null) {

                    shipToCustAcctCd = null;
                    shipToCustLocCd = null;
                    //shipToLocNm = null;
                    shipToLocNm = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue(), 0, 60);
                    shipToAddlLocNm = null;
                    shipToFirstLineAddr = S21StringUtil.subStringByLength(nwai415014.somPrmoAddr_01.getValue(), 0, 60);
                    shipToScdLineAddr = S21StringUtil.subStringByLength(nwai415014.somPrmoAddr_02.getValue(), 61, 60);
                    shipToThirdLineAddr = null;
                    shipToFrthLineAddr = null;
                    shipToCtyAddr = S21StringUtil.subStringByLength(nwai415014.somPrmoCityTxt.getValue(), 0, 25);
                    shipToStCd = S21StringUtil.subStringByLength(nwai415014.somPrmoStCd.getValue(), 0, 2);
                    shipToProvNm = null;
                    shipToCntyNm = S21StringUtil.subStringByLength(nwai415014.somPrmoCntyNm.getValue(), 0, 30);
                    shipToPostCd = S21StringUtil.subStringByLength(nwai415014.somPrmoZipCd.getValue(), 0, 15);
                    shipToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;
                    shipToAcctNm = null;
                }
            }

        } else if (NWAB415001Constant.CONFIG_TYPE.EOPS_RMA.equals(configType)) {

            NWAI4150_24TMsg nwai415024 = null;
            if (!this.nwai4150_24List.isEmpty()) {

                nwai415024 = this.nwai4150_24List.get(0);
                // from address
                String address //
                = S21StringUtil.concatStrings(//
                        S21StringUtil.trimString(//
                                S21StringUtil.concatStrings(//
                                        NWXC412001.toUpperCase(S21StringUtil.trimString(//
                                                nwai415024.rtrnPickUpAddr_01.getValue())) //
                                        , " " //
                                        , NWXC412001.toUpperCase(S21StringUtil.trimString(//
                                                nwai415024.rtrnPickUpAddr_02.getValue())))) //
                        , NWXC412001.toUpperCase(S21StringUtil.trimString(//
                                nwai415024.rtrnPickUpCtyTxt.getValue())) //
                        , NWXC412001.toUpperCase(S21StringUtil.trimString(nwai415024.pickUpStCd.getValue())) //
                        , NWXC412001.toUpperCase(NWXC412001.cutString(S21StringUtil.trimString(//
                                nwai415024.pickUpZipCd.getValue()), CUTOFF_LEN.ZIP_CD.getLen())));

                if (!this.dsImptOrdBean.getCacheShipToCustMap().containsKey("FROMADDR" + this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue() + address)) {
                    shipTo = NWXC412001.getShipToInfoFromAddress(glblCmpyCd, address, salesDate, this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue());
                    this.dsImptOrdBean.getCacheShipToCustMap().put("FROMADDR" + this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue() + address, shipTo);
                } else {
                    shipTo = this.dsImptOrdBean.getCacheShipToCustMap().get("FROMADDR" + this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue() + address);
                }

            }
            if (shipTo == null || shipTo.isEmpty()) {

                if (nwai415024 != null) {

                    shipToCustAcctCd = null;
                    shipToCustLocCd = null;
                    //shipToLocNm = null;
                    shipToLocNm = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue(), 0, 60);
                    shipToAddlLocNm = null;
                    String addr = S21StringUtil.concatStrings(nwai415024.rtrnPickUpAddr_01.getValue(), " ", nwai415024.rtrnPickUpAddr_02.getValue());
                    shipToFirstLineAddr = S21StringUtil.subStringByLength(addr, 0, 60);
                    shipToScdLineAddr = S21StringUtil.subStringByLength(addr, 61, 60);
                    shipToThirdLineAddr = null;
                    shipToFrthLineAddr = null;
                    shipToCtyAddr = S21StringUtil.subStringByLength(nwai415024.rtrnPickUpCtyTxt.getValue(), 0, 25);
                    shipToStCd = S21StringUtil.subStringByLength(nwai415024.pickUpStCd.getValue(), 0, 2);
                    shipToProvNm = null;
                    shipToCntyNm = S21StringUtil.subStringByLength(nwai415024.pickUpCntyNm.getValue(), 0, 30);
                    shipToPostCd = S21StringUtil.subStringByLength(nwai415024.pickUpZipCd.getValue(), 0, 15);
                    shipToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;
                    shipToAcctNm = null;
                }
            }

        } else {

            // RMA
            if (this.serIf06TMsg != null) {

                if (!ZYPCommonFunc.hasValue(this.serIf06TMsg.somPsnDescTxt) || "0".equals(this.serIf06TMsg.somPsnDescTxt.getValue())) {
                    //QC#20753
                    // from address
                    String address = S21StringUtil.concatStrings(S21StringUtil.trimString(S21StringUtil.concatStrings(NWXC412001.toUpperCase(S21StringUtil.trimString(this.serIf06TMsg.pickUpEopsAddr_01.getValue())), " ", NWXC412001
                            .toUpperCase(S21StringUtil.trimString(this.serIf06TMsg.pickUpEopsAddr_02.getValue())))), NWXC412001.toUpperCase(S21StringUtil.trimString(this.serIf06TMsg.pickUpEopsCityAddr.getValue())), NWXC412001
                            .toUpperCase(S21StringUtil.trimString(this.serIf06TMsg.pickUpStCd.getValue())), NWXC412001.toUpperCase(NWXC412001.cutString(S21StringUtil.trimString(this.serIf06TMsg.pickUpZipCd.getValue()), 5)));

                    if (!this.dsImptOrdBean.getCacheShipToCustMap().containsKey("FROMADDR" + this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue() + address)) {
                        shipTo = NWXC412001.getShipToInfoFromAddress(glblCmpyCd, address, salesDate, this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue());
                        this.dsImptOrdBean.getCacheShipToCustMap().put("FROMADDR" + this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue() + address, shipTo);
                    } else {
                        shipTo = this.dsImptOrdBean.getCacheShipToCustMap().get("FROMADDR" + this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue() + address);
                    }

                } else {

                    if (!this.dsImptOrdBean.getCacheShipToCustMap().containsKey("FROMPSN" + this.serIf06TMsg.somPsnDescTxt.getValue())) {
                        shipTo = NWXC412001.getShipToInfoFromSomPsnDescTxt(glblCmpyCd, this.serIf06TMsg.somPsnDescTxt.getValue(), salesDate);
                        this.dsImptOrdBean.getCacheShipToCustMap().put("FROMPSN" + this.serIf06TMsg.somPsnDescTxt.getValue(), shipTo);
                    } else {
                        shipTo = this.dsImptOrdBean.getCacheShipToCustMap().get("FROMPSN" + this.serIf06TMsg.somPsnDescTxt.getValue());
                    }

                }
                if (shipTo == null || shipTo.isEmpty()) {

                    shipToCustAcctCd = null;
                    shipToCustLocCd = null;
                    //shipToLocNm = null;
                    shipToLocNm = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue(), 0, 60);
                    shipToAddlLocNm = null;
                    shipToFirstLineAddr = S21StringUtil.subStringByLength(this.serIf06TMsg.pickUpEopsAddr_01.getValue(), 0, 60);
                    shipToScdLineAddr = S21StringUtil.subStringByLength(this.serIf06TMsg.pickUpEopsAddr_02.getValue(), 61, 60);
                    shipToThirdLineAddr = null;
                    shipToFrthLineAddr = null;
                    shipToCtyAddr = S21StringUtil.subStringByLength(this.serIf06TMsg.pickUpEopsCityAddr.getValue(), 0, 25);
                    shipToStCd = S21StringUtil.subStringByLength(this.serIf06TMsg.pickUpStCd.getValue(), 0, 2);
                    shipToProvNm = null;
                    shipToCntyNm = S21StringUtil.subStringByLength(this.serIf06TMsg.pickUpCntyNm.getValue(), 0, 30);
                    shipToPostCd = S21StringUtil.subStringByLength(this.serIf06TMsg.pickUpZipCd.getValue(), 0, 15);
                    shipToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;
                    shipToAcctNm = null;
                }

            }

            if ((shipTo == null || shipTo.isEmpty()) && this.noSerIf06TMsg != null) {

                if (!ZYPCommonFunc.hasValue(this.noSerIf06TMsg.somPsnDescTxt) || "0".equals(this.noSerIf06TMsg.somPsnDescTxt.getValue())) {
                    //QC#20753
                    // from address
                    String address = S21StringUtil.concatStrings(S21StringUtil.trimString(S21StringUtil.concatStrings(NWXC412001.toUpperCase(S21StringUtil.trimString(this.noSerIf06TMsg.pickUpEopsAddr_01.getValue())), " ", NWXC412001
                            .toUpperCase(S21StringUtil.trimString(this.noSerIf06TMsg.pickUpEopsAddr_02.getValue())))), NWXC412001.toUpperCase(S21StringUtil.trimString(this.noSerIf06TMsg.pickUpEopsCityAddr.getValue())), NWXC412001
                            .toUpperCase(S21StringUtil.trimString(this.noSerIf06TMsg.pickUpStCd.getValue())), NWXC412001.toUpperCase(NWXC412001.cutString(S21StringUtil.trimString(this.noSerIf06TMsg.pickUpZipCd.getValue()), 5)));

                    if (!this.dsImptOrdBean.getCacheShipToCustMap().containsKey("FROMADDR" + this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue() + address)) {
                        shipTo = NWXC412001.getShipToInfoFromAddress(glblCmpyCd, address, salesDate, this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue());
                        this.dsImptOrdBean.getCacheShipToCustMap().put("FROMADDR" + this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue() + address, shipTo);
                    } else {
                        shipTo = this.dsImptOrdBean.getCacheShipToCustMap().get("FROMADDR" + this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue() + address);
                    }

                } else {

                    if (!this.dsImptOrdBean.getCacheShipToCustMap().containsKey("FROMPSN" + this.noSerIf06TMsg.somPsnDescTxt.getValue())) {
                        shipTo = NWXC412001.getShipToInfoFromSomPsnDescTxt(glblCmpyCd, this.noSerIf06TMsg.somPsnDescTxt.getValue(), salesDate);
                        this.dsImptOrdBean.getCacheShipToCustMap().put("FROMPSN" + this.noSerIf06TMsg.somPsnDescTxt.getValue(), shipTo);
                    } else {
                        shipTo = this.dsImptOrdBean.getCacheShipToCustMap().get("FROMPSN" + this.noSerIf06TMsg.somPsnDescTxt.getValue());
                    }

                }
                if (shipTo == null || shipTo.isEmpty()) {

                    shipToCustAcctCd = null;
                    shipToCustLocCd = null;
                    //shipToLocNm = null;
                    shipToLocNm = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai415001.somShipToCustNm.getValue(), 0, 60);
                    shipToAddlLocNm = null;
                    shipToFirstLineAddr = S21StringUtil.subStringByLength(this.noSerIf06TMsg.pickUpEopsAddr_01.getValue(), 0, 60);
                    shipToScdLineAddr = S21StringUtil.subStringByLength(this.noSerIf06TMsg.pickUpEopsAddr_02.getValue(), 61, 60);
                    shipToThirdLineAddr = null;
                    shipToFrthLineAddr = null;
                    shipToCtyAddr = S21StringUtil.subStringByLength(this.noSerIf06TMsg.pickUpEopsCityAddr.getValue(), 0, 25);
                    shipToStCd = S21StringUtil.subStringByLength(this.noSerIf06TMsg.pickUpStCd.getValue(), 0, 2);
                    shipToProvNm = null;
                    shipToCntyNm = S21StringUtil.subStringByLength(this.noSerIf06TMsg.pickUpCntyNm.getValue(), 0, 30);
                    shipToPostCd = S21StringUtil.subStringByLength(this.noSerIf06TMsg.pickUpZipCd.getValue(), 0, 15);
                    shipToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;
                    shipToAcctNm = null;
                }
            }
        }
        if (shipTo != null && !shipTo.isEmpty()) {

            shipToCustAcctCd = (String) shipTo.get("DS_ACCT_NUM");
            shipToAcctNm = (String) shipTo.get("DS_ACCT_NM");
            shipToCustLocCd = (String) shipTo.get("SHIP_TO_CUST_CD");
            shipToLocNm = (String) shipTo.get("LOC_NM");
            shipToAddlLocNm = (String) shipTo.get("ADDL_LOC_NM");
            shipToFirstLineAddr = (String) shipTo.get("FIRST_LINE_ADDR");
            shipToScdLineAddr = (String) shipTo.get("SCD_LINE_ADDR");
            shipToThirdLineAddr = (String) shipTo.get("THIRD_LINE_ADDR");
            shipToFrthLineAddr = (String) shipTo.get("FRTH_LINE_ADDR");
            shipToCtyAddr = (String) shipTo.get("CTY_ADDR");
            shipToStCd = (String) shipTo.get("ST_CD");
            shipToProvNm = (String) shipTo.get("PROV_NM");
            shipToCntyNm = (String) shipTo.get("CNTY_NM");
            shipToPostCd = (String) shipTo.get("POST_CD");
            shipToCtryCd = (String) shipTo.get("CTRY_CD");
        }

        ZYPEZDItemValueSetter.setValue(this.shipToCustAcctCd, shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(this.shipToCustLocCd, shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(this.dropShipFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(this.shipToLocNm, shipToLocNm);
        ZYPEZDItemValueSetter.setValue(this.shipToAddlLocNm, shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(this.shipToFirstLineAddr, shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(this.shipToScdLineAddr, shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(this.shipToThirdLineAddr, shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(this.shipToFrthLineAddr, shipToFrthLineAddr);
        this.shipToFirstRefCmntTxt.clear();
        this.shipToScdRefCmntTxt.clear();
        ZYPEZDItemValueSetter.setValue(this.shipToCtyAddr, shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(this.shipToStCd, shipToStCd);
        ZYPEZDItemValueSetter.setValue(this.shipToProvNm, shipToProvNm);
        ZYPEZDItemValueSetter.setValue(this.shipToCntyNm, shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(this.shipToPostCd, shipToPostCd);
        ZYPEZDItemValueSetter.setValue(this.shipToCtryCd, shipToCtryCd);

        ZYPEZDItemValueSetter.setValue(this.configCratTs, NWXC412001.getValidDate(this.dsImptOrdBean.nwai415001.cratTs.getValue()));
        this.svcSlnSq.clear();
        this.svcSlnNm.clear();

        // SOM configuration ID
        if (NWAB415001Constant.CONFIG_TYPE.REGULAR_SHIP_CONFIG.equals(configType)) {

            ZYPEZDItemValueSetter.setValue(this.somConfigId, this.nwai415012.somConfigId);

        } else if (NWAB415001Constant.CONFIG_TYPE.TRADE_IN_RMA.equals(configType)) {

            ZYPEZDItemValueSetter.setValue(this.somConfigId, this.nwai415005.somSoftCostId);
        } else if (NWAB415001Constant.CONFIG_TYPE.BUYOUT_RMA.equals(configType)) {

            ZYPEZDItemValueSetter.setValue(this.somConfigId, this.nwai415005.somSoftCostId);

        } else if (NWAB415001Constant.CONFIG_TYPE.UPGRADE_RETURN_RMA.equals(configType)) {

            ZYPEZDItemValueSetter.setValue(this.somConfigId, this.nwai415005.somSoftCostId);

        } else if (NWAB415001Constant.CONFIG_TYPE.OCE_PROMOTION_RMA.equals(configType)) {

            if (!this.nwai4150_14List.isEmpty()) {

                ZYPEZDItemValueSetter.setValue(this.somConfigId, this.nwai4150_14List.get(0).somConfigId);
            }

        } else if (NWAB415001Constant.CONFIG_TYPE.EOPS_RMA.equals(configType)) {
            ZYPEZDItemValueSetter.setValue(this.somConfigId, this.nwai415024.eopsRtrnId);

        }

        // address group
        if (NWAB415001Constant.CONFIG_TYPE.REGULAR_SHIP_CONFIG.equals(configType)) {

            ZYPEZDItemValueSetter.setValue(this.addrLbTxt, this.nwai415012.addrLbTxt);

        } else if (NWAB415001Constant.CONFIG_TYPE.TRADE_IN_RMA.equals(configType)) {

            this.addrLbTxt.clear();
        } else if (NWAB415001Constant.CONFIG_TYPE.BUYOUT_RMA.equals(configType)) {
            this.addrLbTxt.clear();
        } else if (NWAB415001Constant.CONFIG_TYPE.UPGRADE_RETURN_RMA.equals(configType)) {
            this.addrLbTxt.clear();
        } else if (NWAB415001Constant.CONFIG_TYPE.OCE_PROMOTION_RMA.equals(configType)) {
            this.addrLbTxt.clear();
        } else if (NWAB415001Constant.CONFIG_TYPE.EOPS_RMA.equals(configType)) {
            this.addrLbTxt.clear();
        }

        ZYPEZDItemValueSetter.setValue(this.shipToAcctNm, shipToAcctNm);

        ZYPEZDItemValueSetter.setValue(this.emsdImptStsCd, IMPT_STS.PENDING_OM_REVIEW);

        // eops config letter text
        if (NWAB415001Constant.CONFIG_TYPE.REGULAR_SHIP_CONFIG.equals(configType)) {
            ZYPEZDItemValueSetter.setValue(this.eopsConfigLtrTxt, this.nwai415012.eopsConfigLtrTxt);
        } else if (NWAB415001Constant.CONFIG_TYPE.EOPS_RMA.equals(configType)) {
            ZYPEZDItemValueSetter.setValue(this.eopsConfigLtrTxt, this.nwai415024.otbdConfigTxt);
        }

        // *******************************
        // prepare child elements
        // *******************************

        for (EopsDsImptOrdCtacPsnBean ctacPsnBean : this.dsImptOrdCtacPsnBeanList) {
            ctacPsnBean.doImptMapping(glblCmpyCd, salesDate);
        }

        this.dsImptOrdIstlInfoBean.doImptMapping(glblCmpyCd, salesDate);
        String custIstlFlg = NWXC412001.getSvcCustIstlFlg(glblCmpyCd, this.dsImptOrdIstlInfoBean.svcIstlRuleNum.getValue());
        for (EopsDsImptOrdDtlBean bean : this.dsImptOrdDtlBeanList) {
            ZYPEZDItemValueSetter.setValue(bean.custIstlFlg, custIstlFlg);
        }

        this.dsImptOrdDelyInfoBean.doImptMapping(glblCmpyCd, salesDate);

        for (EopsDsImptOrdDtlBean dtlBean : this.dsImptOrdDtlBeanList) {
            if (!dtlBean.doImptMapping(glblCmpyCd, salesDate)) {
                isSuccess = false;
            }
        }

        for (EopsDsImptOrdRtrnDtlBean rtrnDtlBean : this.dsImptOrdDtlRtrnBeanList) {
            if (!rtrnDtlBean.doImptMapping(glblCmpyCd, salesDate)) {
                isSuccess = false;
            }
        }

        this.dsImptOrdSlsCrBean.doImptMapping(glblCmpyCd, salesDate);

        return isSuccess;
    }

    /**
     * getSvcConfigMstrPk
     * @return pSvcConfigMstrPk
     */
    public BigDecimal getSvcConfigMstrPk() {
        return this.pSvcConfigMstrPk;
    }

    /**
     * setSvcConfigMstrPk
     * @param pSvcConfigMstrPk BigDecimal
     * @return pSvcConfigMstrPk
     */
    public BigDecimal setSvcConfigMstrPk(BigDecimal pSvcConfigMstrPk) {
        return this.pSvcConfigMstrPk = pSvcConfigMstrPk;
    }

    /**
     * @param errorInfo NWXC220001ErrorInfo
     */
    @Override
    public void addErrorInfo(NWXC220001ErrorInfo errorInfo) {
        BigDecimal transactionId = null;
        BigDecimal unitId = null;
        BigDecimal seqNumber = null;

        if (this.nwai415012 != null) {
            transactionId = this.nwai415012.transactionId.getValue();
            unitId = this.nwai415012.transactionId.getValue();
            seqNumber = this.nwai415012.transactionId.getValue();
        } else if (this.nwai415005 != null) {
            transactionId = this.nwai415005.transactionId.getValue();
            unitId = this.nwai415005.transactionId.getValue();
            seqNumber = this.nwai415005.transactionId.getValue();
        }
        this.errInfoList.add(new NWAB415001ErrorInfo(transactionId, unitId, seqNumber, errorInfo));
    }

    /**
     * @param errorInfoList List<NWXC220001ErrorInfo>
     */
    @Override
    public void addErrorInfo(List<NWXC220001ErrorInfo> errorInfoList) {
        for (NWXC220001ErrorInfo errorInfo : errorInfoList) {
            addErrorInfo(errorInfo);
        }
    }

    /**
     * @return errList
     */
    @Override
    public List<NWAB415001ErrorInfo> getErrorInfo() {
        List<NWAB415001ErrorInfo> errList = new ArrayList<NWAB415001ErrorInfo>();

        errList.addAll(this.errInfoList);

        for (EopsDsImptOrdDtlBean dtlBean : this.dsImptOrdDtlBeanList) {
            errList.addAll(dtlBean.getErrorInfo());
        }

        for (EopsDsImptOrdRtrnDtlBean rtrnDtlBean : this.dsImptOrdDtlRtrnBeanList) {
            errList.addAll(rtrnDtlBean.getErrorInfo());
        }
        return errList;
    }

    /**
     * @return if error then true.
     */
    @Override
    public boolean hasError() {
        return (this.errInfoList.size() > 0);
    }

    /**
     * getFlPrcListCd
     * @return pFlPrcListCd
     */
    public String getFlPrcListCd() {
        return this.pFlPrcListCd;
    }

    /**
     * getPickUpDtOthDescTxt
     * @return pPickUpDtOthDescTxt
     */
    public String getPickUpDtOthDescTxt() {
        return this.pPickUpDtOthDescTxt;
    }

    /**
     * getPrcCatgCd
     * @return pPrcCatgCd
     */
    public String getPrcCatgCd() {
        return this.pPrcCatgCd;
    }

    /**
     * getSplitTypeTxt
     * @return pSplitTypeTxt
     */
    public String getSplitTypeTxt() {
        return this.pSplitTypeTxt;
    }

    /**
     * getVndMap
     * @return pVndMap
     */
    public Map<String, Object> getVndMap() {
        return pVndMap;
    }

    /**
     * getRtrnRsnCd
     * @return  pRtrnRsnCd
     */
    public String getRtrnRsnCd() {
        return this.pRtrnRsnCd;
    }

    /**
     * addExsistingCondigurationListForAddAcc
     */
    public void addExsistingCondigurationListForAddAcc() {
        //have configuration list from serial information
        if (this.nwai4150_13List != null && this.nwai4150_13List.size() > 0) {
            for (NWAI4150_13TMsg serialTMsg : this.nwai4150_13List) {
                if (serialTMsg != null) {
                    List<Map<String, Object>> existslist = NWXC412001.getConfigMasterDetailList(dsImptOrdBean.glblCmpyCd.getValue(), serialTMsg.somSerNum.getValue(), dsImptOrdBean.slsDt);
                    if (existslist != null && existslist.size() > 0) {
                        this.isAddAccessoryToExistingConfigurationFlag = true;
                        this.isExistingConfigurationFlag = false;
                        existingConfigurationList.addAll(existslist);
                        break;
                    }
                }
            }
        }
    }

    /**
     * addExsistingCondigurationListForPreOwned
     * @param if10TMsgList List<NWAI4150_10TMsg>
     */
    public void addExsistingCondigurationListForPreOwned(List<NWAI4150_10TMsg> if10TMsgList) {
        //have configuration list from serial information
        if (if10TMsgList != null && if10TMsgList.size() > 0) {
            for (NWAI4150_10TMsg nwai415010 : if10TMsgList) {
                //
                if (ZYPCommonFunc.hasValue(nwai415010.somMdlDescTxt) //
                        && (COA_PROJ.MACHINE.equals(nwai415010.somMdseTpTxt.getValue()) //
                        || COA_PROJ.MERCHANDISE_SET.equals(nwai415010.somMdseTpTxt.getValue()))) {
                    List<Map<String, Object>> mdlIdList //
                    = NWXC412001.getMdlIdFromMdlNm(dsImptOrdBean.glblCmpyCd.getValue(), nwai415010.somMdlDescTxt.getValue());
                    if (mdlIdList != null && mdlIdList.size() > 0) {
                        this.isAddAccessoryToExistingConfigurationFlag = false;
                        this.isExistingConfigurationFlag = true;
                        existingConfigurationList.addAll(mdlIdList);
                        break;
                    }
                }
            }
        }
    }

}
