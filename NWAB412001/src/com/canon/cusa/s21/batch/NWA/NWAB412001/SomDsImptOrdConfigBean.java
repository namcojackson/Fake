/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.db.DS_IMPT_ORD_CONFIGTMsg;
import business.db.NWAI4120_05TMsg;
import business.db.NWAI4120_06TMsg;
import business.db.NWAI4120_10TMsg;
import business.db.NWAI4120_12TMsg;
import business.db.NWAI4120_13TMsg;
import business.db.NWAI4120_14TMsg;
import business.db.NWAI4120_18TMsg;
import business.db.NWAI4120_19TMsg;

import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.CTAC_PSN_SUB_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.CTAC_PSN_TYPE;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Util;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptOrdConfigBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 12/01/2016   SRAA            K.Aratani       Update          QC#15539
 * 01/13/2017   SRAA            K.Aratani       Update          QC#17118
 * 03/08/2017   SRAA            K.Aratani       Update          QC#17768
 * 04/10/2017   SRAA            K.Aratani       Update          QC#18306
 * 08/22/2017   SRAA            K.Aratani       Update          QC#20739
 * 08/23/2017   SRAA            K.Aratani       Update          QC#20753
 * 09/13/2017   Fujitsu         S.Iidaka        Update          QC#21084
 * 02/16/2018   SRAA            K.Aratani       Update          QC#24023
 * 02/17/2018   SRAA            K.Aratani       Update          QC#24242
 * 03/16/2018   Fujitsu         R.Nakamura      Update          QC#24367
 * 04/05/2018   SRA             K.Aratani       Update          QC#25324
 * 05/10/2018   Fujitsu         Hd.Sugawara     Update          QC#20343
 *</pre>
 */
public class SomDsImptOrdConfigBean extends DS_IMPT_ORD_CONFIGTMsg implements ISomDsImptOrd, ISomErrorInfo {

    private static final long serialVersionUID = 1L;

    public static final String configKeyFormat = "%.0f-%.0f";

    public static final String configKeyFormat2 = "%.0f-%s";

    public final NWAB412001Constant.CONFIG_TYPE configType;

    /** V_CONFIGURATION_SHIPTOS */
    public NWAI4120_12TMsg nwai4120_12;

    /** V_CONFIGURATION_MACHINE_SERIALS */
    public final List<NWAI4120_13TMsg> nwai4120_13List;

    public final List<NWAI4120_14TMsg> nwai4120_14List;

    public NWAI4120_05TMsg nwai4120_05;

    public NWAI4120_18TMsg nwai4120_18;

    public NWAI4120_06TMsg serIf06TMsg;
    
    public NWAI4120_06TMsg noSerIf06TMsg;

    public final SomDsImptOrdBean dsImptOrdBean;

    public SomDsImptOrdSlsCrBean dsImptOrdSlsCrBean;

    public final List<SomDsImptOrdCtacPsnBean> dsImptOrdCtacPsnBeanList;

    public SomDsImptOrdIstlInfoBean dsImptOrdIstlInfoBean;

    public SomDsImptOrdDelyInfoBean dsImptOrdDelyInfoBean;

    public SomDsImptOrdSiteSrvyBean dsImptOrdSiteSrvyBean;

    public final List<SomDsImptOrdDtlBean> dsImptOrdDtlBeanList;

    public final List<SomDsImptOrdRtrnDtlBean> dsImptOrdDtlRtrnBeanList;

    public final List<SomDsImptSvcDtlBean> dsImptSvcDtlBeanList;

    public final List<SomDsImptSvcConfigRefBean> dsImptSvcConfigRefBeanList;

    public final List<SomDsImptSvcPrcBean> dsImptSvcPrcBeanList;

    public final List<SomDsImptSvcAddlBaseBean> dsImptSvcAddlBaseBeanList;

    public final List<SomDsImptSvcAddlChrgBean> dsImptSvcAddlChrgBeanList;

    public final List<SomDsImptSvcUsgPrcBean> dsImptSvcUsgPrcBeanList;

    public final String configKey;
    
    //QC#17768
    public boolean isExistingConfigurationFlag;
    public boolean isAddAccessoryToExistingConfigurationFlag;
    public final List<Map<String, Object>> existingConfigurationList;
    
    //For Performance
    private String pFlPrcListCd;
    private String pPrcCatgCd;
    private String pSlsRepOrSlsTeamTocCd;
    private String pDsOrdLineCatgCd;
    private static Map<String, Object> pVndMap;
    private String pRtrnRsnCd;
    private BigDecimal pSvcConfigMstrPk;
    private String pSplitTypeTxt;
    private String pPickUpDtOthDescTxt; //QC#20739
    private boolean copierDeclineMainte = false;  //QC#24023
    private boolean nonCopierDeclineMainte = false;  //QC#24023

    /** List<NWAB412001ErrorInfo> */
    final public List<NWAB412001ErrorInfo> errInfoList;

    public SomDsImptOrdConfigBean(NWAB412001Constant.CONFIG_TYPE configType, SomDsImptOrdBean dsImptOrdBean, NWAI4120_12TMsg nwai4120_12) {
        this(configType, dsImptOrdBean, createConfigKey(nwai4120_12));

        this.nwai4120_12 = nwai4120_12;
        this.pSplitTypeTxt = this.nwai4120_12.splitTpTxt.getValue();
        this.pPickUpDtOthDescTxt = null; //QC#20739
        // floor price list
        if (ZYPCommonFunc.hasValue(this.nwai4120_12.flPrcListId)) {
            if (!dsImptOrdBean.getCachePrcCatgCdMap().containsKey(this.nwai4120_12.flPrcListId.getValue().toPlainString())) {
                this.pFlPrcListCd = NWXC412001.getPrcCatgCdFromId(dsImptOrdBean.glblCmpyCd.getValue(), this.nwai4120_12.flPrcListId.getValue());
                dsImptOrdBean.getCachePrcCatgCdMap().put(this.nwai4120_12.flPrcListId.getValue().toPlainString(), this.pFlPrcListCd);
            } else {
                this.pFlPrcListCd = (String) dsImptOrdBean.getCachePrcCatgCdMap().get(this.nwai4120_12.flPrcListId.getValue().toPlainString());
            }
        }
        if (!ZYPCommonFunc.hasValue(this.pFlPrcListCd)) {
            // default price list
            if (!dsImptOrdBean.getCachePrcCatgNmMap().containsKey((String) dsImptOrdBean.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_DEFAULT_PRICE_LIST.name()))) {
                this.pFlPrcListCd = NWXC412001.getPrcCatgCdFromName(dsImptOrdBean.glblCmpyCd.getValue(), (String) dsImptOrdBean.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_DEFAULT_PRICE_LIST.name()));
                dsImptOrdBean.getCachePrcCatgNmMap().put((String) dsImptOrdBean.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_DEFAULT_PRICE_LIST.name()), this.pFlPrcListCd);
            } else {
                this.pFlPrcListCd = (String) dsImptOrdBean.getCachePrcCatgNmMap().get((String) dsImptOrdBean.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_DEFAULT_PRICE_LIST.name()));
            }
        }
        // price list
        // Del Start 2018/03/16 QC#24367
//        if (!this.dsImptOrdBean.nwai4120_01.trxTpTxt.getValue().equals(NWAB412001Constant.TRX_TP_TXT_RENTAL)) {
//
//            this.pPrcCatgCd = NWXC412001.getPrcCatgCdForLine(dsImptOrdBean.glblCmpyCd.getValue(), this.dsImptOrdBean.nwai4120_01.bidPrcListNm.getValue(), this.nwai4120_12.bidPrcListNm.getValue(), this.nwai4120_12.bidPrcListId.getValue());
//            if (S21StringUtil.isNotEmpty(this.dsImptOrdBean.nwai4120_01.bidPrcListNm.getValue())) {
//                if (!dsImptOrdBean.getCachePrcCatgNmMap().containsKey(this.dsImptOrdBean.nwai4120_01.bidPrcListNm.getValue())) {
//                    this.pPrcCatgCd = NWXC412001.getPrcCatgCdFromName(dsImptOrdBean.glblCmpyCd.getValue(), this.dsImptOrdBean.nwai4120_01.bidPrcListNm.getValue());
//                    dsImptOrdBean.getCachePrcCatgNmMap().put(this.dsImptOrdBean.nwai4120_01.bidPrcListNm.getValue(), this.pPrcCatgCd);
//                } else {
//                    this.pPrcCatgCd = (String) dsImptOrdBean.getCachePrcCatgNmMap().get(this.dsImptOrdBean.nwai4120_01.bidPrcListNm.getValue());
//                }
//            }
//        }

//        if (!S21StringUtil.isNotEmpty(this.pPrcCatgCd)) {
        // Del End 2018/03/16 QC#24367
        if (S21StringUtil.isNotEmpty(this.nwai4120_12.bidPrcListNm.getValue())) {
            if (!dsImptOrdBean.getCachePrcCatgNmMap().containsKey(this.nwai4120_12.bidPrcListNm.getValue())) {
                this.pPrcCatgCd = NWXC412001.getPrcCatgCdFromName(dsImptOrdBean.glblCmpyCd.getValue(), this.nwai4120_12.bidPrcListNm.getValue());
                dsImptOrdBean.getCachePrcCatgNmMap().put(this.nwai4120_12.bidPrcListNm.getValue(), this.pPrcCatgCd);
            } else {
                this.pPrcCatgCd = (String) dsImptOrdBean.getCachePrcCatgNmMap().get(this.nwai4120_12.bidPrcListNm.getValue());
            }
        }
        if (!S21StringUtil.isNotEmpty(this.pPrcCatgCd)) {
            if (this.nwai4120_12.bidPrcListId.getValue() != null && this.nwai4120_12.bidPrcListId.getValue().compareTo(BigDecimal.ZERO) != 0) {
                if (!dsImptOrdBean.getCachePrcCatgCdMap().containsKey(this.nwai4120_12.bidPrcListId.getValue().toPlainString())) {
                    this.pPrcCatgCd = NWXC412001.getPrcCatgCdFromId(dsImptOrdBean.glblCmpyCd.getValue(), this.nwai4120_12.bidPrcListId.getValue());
                    dsImptOrdBean.getCachePrcCatgCdMap().put(this.nwai4120_12.bidPrcListId.getValue().toPlainString(), this.pPrcCatgCd);
                } else {
                    this.pPrcCatgCd = (String) dsImptOrdBean.getCachePrcCatgCdMap().get(this.nwai4120_12.flPrcListId.getValue().toPlainString());
                }
            }
        }
        if (!S21StringUtil.isNotEmpty(this.pPrcCatgCd)) {
            if (ZYPCommonFunc.hasValue(this.nwai4120_12.flPrcListId) && this.nwai4120_12.flPrcListId.getValue().compareTo(BigDecimal.ZERO) != 0) {
                if (!dsImptOrdBean.getCachePrcCatgCdMap().containsKey(this.nwai4120_12.flPrcListId.getValue().toPlainString())) {
                    this.pPrcCatgCd = NWXC412001.getPrcCatgCdFromId(dsImptOrdBean.glblCmpyCd.getValue(), this.nwai4120_12.flPrcListId.getValue());
                    dsImptOrdBean.getCachePrcCatgCdMap().put(this.nwai4120_12.flPrcListId.getValue().toPlainString(), this.pFlPrcListCd);
                } else {
                    this.pPrcCatgCd = (String) dsImptOrdBean.getCachePrcCatgCdMap().get(this.nwai4120_12.flPrcListId.getValue().toPlainString());
                }
            }
        }
        // Add Start 2018/03/16 QC#24367
        if (!S21StringUtil.isNotEmpty(this.pPrcCatgCd)) {
            this.pPrcCatgCd = NWXC412001.getPrcCatgCdForLine(dsImptOrdBean.glblCmpyCd.getValue(), this.dsImptOrdBean.nwai4120_01.bidPrcListNm.getValue(), this.nwai4120_12.bidPrcListNm.getValue(), this.nwai4120_12.bidPrcListId.getValue());
            if (S21StringUtil.isNotEmpty(this.dsImptOrdBean.nwai4120_01.bidPrcListNm.getValue())) {
                if (!dsImptOrdBean.getCachePrcCatgNmMap().containsKey(this.dsImptOrdBean.nwai4120_01.bidPrcListNm.getValue())) {
                    this.pPrcCatgCd = NWXC412001.getPrcCatgCdFromName(dsImptOrdBean.glblCmpyCd.getValue(), this.dsImptOrdBean.nwai4120_01.bidPrcListNm.getValue());
                    dsImptOrdBean.getCachePrcCatgNmMap().put(this.dsImptOrdBean.nwai4120_01.bidPrcListNm.getValue(), this.pPrcCatgCd);
                } else {
                    this.pPrcCatgCd = (String) dsImptOrdBean.getCachePrcCatgNmMap().get(this.dsImptOrdBean.nwai4120_01.bidPrcListNm.getValue());
                }
            }
        }
        // Add End 2018/03/16 QC#24367
        // Del Start 2018/03/16 QC#24367
//        }
        // Del End 2018/03/16 QC#24367
    }

    public SomDsImptOrdConfigBean(NWAB412001Constant.CONFIG_TYPE configType, SomDsImptOrdBean dsImptOrdBean, NWAI4120_05TMsg nwai4120_05, NWAI4120_06TMsg serIf06TMsg, NWAI4120_06TMsg noSerIf06TMsg) {
        this(configType, dsImptOrdBean, createConfigKey(nwai4120_05));

        this.nwai4120_05 = nwai4120_05;
        this.pSplitTypeTxt = this.nwai4120_05.splitTpTxt.getValue();
        this.serIf06TMsg = serIf06TMsg;
        this.noSerIf06TMsg = noSerIf06TMsg;
        this.pRtrnRsnCd = NWXC412001.getRtrnRsnCd(dsImptOrdBean.glblCmpyCd.getValue(), 
                CPO_SRC_TP.SOM, NWAB412001Constant.INTERFACE_ID.NWAI4120.name(), 
                this.nwai4120_05.quoteLineTpTxt.getValue(), 
                this.nwai4120_05.somMercItemCd.getValue());
        //QC#20739
        String pickUpDtOthDescTxt = null;
        if (nwai4120_05 != null && ZYPCommonFunc.hasValue(nwai4120_05.erlstDelyTs)) {
            if (nwai4120_05.erlstDelyTs.getValue().length() > 8) {
                pickUpDtOthDescTxt = nwai4120_05.erlstDelyTs.getValue().substring(0, 8);
            } else if (nwai4120_05.erlstDelyTs.getValue().length() == 8) {
                pickUpDtOthDescTxt = nwai4120_05.erlstDelyTs.getValue();
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

    private SomDsImptOrdConfigBean(NWAB412001Constant.CONFIG_TYPE configType, SomDsImptOrdBean dsImptOrdBean, String configKey) {
        super();

        this.configType = configType;
        this.configKey = configKey;
        this.dsImptOrdBean = dsImptOrdBean;
        this.nwai4120_13List = new ArrayList<NWAI4120_13TMsg>();
        this.nwai4120_14List = new ArrayList<NWAI4120_14TMsg>();

        this.dsImptOrdDtlBeanList = new ArrayList<SomDsImptOrdDtlBean>();
        this.dsImptOrdDtlRtrnBeanList = new ArrayList<SomDsImptOrdRtrnDtlBean>();
        this.dsImptOrdCtacPsnBeanList = new ArrayList<SomDsImptOrdCtacPsnBean>();

        this.dsImptSvcDtlBeanList = new ArrayList<SomDsImptSvcDtlBean>();
        this.dsImptSvcConfigRefBeanList = new ArrayList<SomDsImptSvcConfigRefBean>();
        this.dsImptSvcPrcBeanList = new ArrayList<SomDsImptSvcPrcBean>();
        this.dsImptSvcAddlBaseBeanList = new ArrayList<SomDsImptSvcAddlBaseBean>();
        this.dsImptSvcAddlChrgBeanList = new ArrayList<SomDsImptSvcAddlChrgBean>();
        this.dsImptSvcUsgPrcBeanList = new ArrayList<SomDsImptSvcUsgPrcBean>();
        this.errInfoList = new ArrayList<NWAB412001ErrorInfo>();
        //QC#17768
        this.isExistingConfigurationFlag = false;
        this.isAddAccessoryToExistingConfigurationFlag = false;
        this.existingConfigurationList = new ArrayList<Map<String, Object>>();
        //QC#18306
        String pVndCd = (String) dsImptOrdBean.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.CFS_VND_CD_FOR_SOM.name());
        pVndMap = NWXC412001.getVendorInfo(dsImptOrdBean.glblCmpyCd.getValue(), pVndCd, dsImptOrdBean.slsDt, 
            (String) dsImptOrdBean.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.ARCS_SPLY_SITE_CD_FOR_SOM.name()));
        
    }

    public static String createConfigKey(NWAI4120_12TMsg nwai4120_12) {
        return String.format(configKeyFormat, nwai4120_12.somQuoteId.getValue(), nwai4120_12.somConfigId.getValue());
    }

    public static String createConfigKey(NWAI4120_05TMsg nwai4120_05) {
        return String.format(configKeyFormat2, nwai4120_05.somQuoteId.getValue(), nwai4120_05.somDescSerNum.getValue());
    }

    public boolean isShipConfig() {
        return this.configType.equals(NWAB412001Constant.CONFIG_TYPE.REGULAR_SHIP_CONFIG);
    }

    public void addDsImptOrdDtlBean(SomDsImptOrdDtlBean dsImptOrdDtlBean) {
        this.dsImptOrdDtlBeanList.add(dsImptOrdDtlBean);
        this.dsImptOrdBean.dsImptOrdDtlBeanList.add(dsImptOrdDtlBean);
    }

    public void addDsImptOrdDtlBean(SomDsImptOrdRtrnDtlBean dsImptOrdRtrnDtlBean) {
        this.dsImptOrdDtlRtrnBeanList.add(dsImptOrdRtrnDtlBean);
        this.dsImptOrdBean.dsImptOrdRtrnDtlBean.add(dsImptOrdRtrnDtlBean);
    }

    public void addDsImptOrdCtacPsnBean(CTAC_PSN_TYPE ctacPsnType, boolean isExistsSubSrv) {

        //QC#17996
        //if (this.nwai4120_14List == null && this.nwai4120_12 != null) {
        if ((this.nwai4120_14List == null || this.nwai4120_14List.size() ==0) && this.nwai4120_12 != null) {
            new SomDsImptOrdCtacPsnBean(ctacPsnType, CTAC_PSN_SUB_TYPE.C_PRIMARY, this);
            new SomDsImptOrdCtacPsnBean(ctacPsnType, CTAC_PSN_SUB_TYPE.C_SECONDARY, this);

            String admin = "ADMIN";
            String usr = "USER";

            String csaPmtTpTxt = NWXC220001Util.convToString(this.nwai4120_12.csaPmtTpTxt.getValue(), "");

            if (admin != null && csaPmtTpTxt.toUpperCase().indexOf(admin) >= 0) {
                new SomDsImptOrdCtacPsnBean(ctacPsnType, CTAC_PSN_SUB_TYPE.C_EMANAGE_ADMIN, this);
            }

            if (usr != null && csaPmtTpTxt.toUpperCase().indexOf(usr) >= 0) {
                new SomDsImptOrdCtacPsnBean(ctacPsnType, CTAC_PSN_SUB_TYPE.C_EMANAGE_USER, this);
            }
            
            //QC#23965
            if (isExistsSubSrv && ZYPCommonFunc.hasValue(this.dsImptOrdBean.nwai4120_01.subSvcCtacLastNm)) {
                new SomDsImptOrdCtacPsnBean(ctacPsnType, CTAC_PSN_SUB_TYPE.H_SUBSERVICES, this);
            }
        }

        if (this.nwai4120_05 != null && this.noSerIf06TMsg != null) {
            new SomDsImptOrdCtacPsnBean(ctacPsnType, CTAC_PSN_SUB_TYPE.C_PICKUP, this);
        }

        for (NWAI4120_14TMsg if14TMsg : this.nwai4120_14List) {
            new SomDsImptOrdCtacPsnBean(ctacPsnType, CTAC_PSN_SUB_TYPE.C_PICKUP, this, if14TMsg);
        }

    }

    public String getSomMdlDescTxt() {
        for (SomDsImptOrdDtlBean dtlBean : dsImptOrdDtlBeanList) {
            if (dtlBean.nwai4120_10 != null && ZYPCommonFunc.hasValue(dtlBean.nwai4120_10.somMdlDescTxt)) {
                return dtlBean.nwai4120_10.somMdlDescTxt.getValue();
            }
        }

        return null;
    }

    public SomDsImptSvcDtlBean getDsImptSvcDtlBeanByNwai4120_18(NWAI4120_18TMsg nwai4120_18) {
        for (SomDsImptSvcDtlBean svcDtlBean : this.dsImptSvcDtlBeanList) {
            if (svcDtlBean.nwai4120_18 == nwai4120_18) {
                return svcDtlBean;
            }
        }

        return null;
    }

    public SomDsImptSvcDtlBean getDsImptSvcDtlBeanByNwai4120_19(NWAI4120_19TMsg nwai4120_19) {
        for (SomDsImptSvcDtlBean svcDtlBean : this.dsImptSvcDtlBeanList) {
            if (svcDtlBean.nwai4120_18 == nwai4120_18) {
                return svcDtlBean;
            }
        }

        return null;
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        boolean isSuccess = true;

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CONFIG_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);

        // configuration category
        if (NWAB412001Constant.CONFIG_TYPE.REGULAR_SHIP_CONFIG.equals(configType)) {

            ZYPEZDItemValueSetter.setValue(this.configCatgCd, CONFIG_CATG.OUTBOUND);
        } else {

            ZYPEZDItemValueSetter.setValue(this.configCatgCd, CONFIG_CATG.INBOUND);
        }

        if (NWAB412001Constant.CONFIG_TYPE.REGULAR_SHIP_CONFIG.equals(configType)) {

            //QC#17768
//            if (!this.nwai4120_13List.isEmpty()) {
//
//                for (NWAI4120_13TMsg nwai4120_13 : this.nwai4120_13List) {
//
//                    if (ZYPCommonFunc.hasValue(nwai4120_13.somSerNum)) {
//
//                        Map<String, Object> machine = NWXC412001.getSvcMachMstrBySerNumAndMdseCd(glblCmpyCd, nwai4120_13.somSerNum.getValue(), nwai4120_13.somItemCd.getValue(), salesDate);
//                        if (machine != null && !machine.isEmpty()) {
//
//                            ZYPEZDItemValueSetter.setValue(this.svcConfigMstrPk, (BigDecimal) machine.get("SVC_CONFIG_MSTR_PK"));
//                            ZYPEZDItemValueSetter.setValue(this.mdlId, (BigDecimal) machine.get("MDL_ID"));
//                            ZYPEZDItemValueSetter.setValue(this.mdlDescTxt, (String) machine.get("T_MDL_NM"));
//                            break;
//                        }
//                    }
//                }
//            }
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
                ZYPEZDItemValueSetter.setValue(this.mdlDescTxt, S21StringUtil.subStringByLength(this.getSomMdlDescTxt(), 0, 90));
            }

        } else if (NWAB412001Constant.CONFIG_TYPE.OCE_PROMOTION_RMA.equals(configType)) {

            if (!this.nwai4120_14List.isEmpty()) {

                for (NWAI4120_14TMsg nwai4120_14 : this.nwai4120_14List) {

                    if (ZYPCommonFunc.hasValue(nwai4120_14.somPrmoSerNum) && !ZYPCommonFunc.hasValue(this.svcConfigMstrPk)) {

                        Map<String, Object> machine = NWXC412001.getSvcMachMstrBySerNumAndMdseCd(glblCmpyCd, nwai4120_14.somPrmoSerNum.getValue(), null, salesDate);
                        if (machine != null && !machine.isEmpty()) {

                            ZYPEZDItemValueSetter.setValue(this.svcConfigMstrPk, NWXC412001.getSvcConfigMstrPkBySerNum(glblCmpyCd, nwai4120_14.somPrmoSerNum.getValue(), salesDate));
                        }
                    }

                    BigDecimal mdlId = null;
                    if (!this.dsImptOrdBean.getCacheModelMap().containsKey(nwai4120_14.somPrmoMdlNm.getValue())) {
                        mdlId = NWXC412001.getMdlIdByName(glblCmpyCd, nwai4120_14.somPrmoMdlNm.getValue());
                        this.dsImptOrdBean.getCacheModelMap().put(nwai4120_14.somPrmoMdlNm.getValue(), mdlId);
                    } else {
                        mdlId = this.dsImptOrdBean.getCacheModelMap().get(nwai4120_14.somPrmoMdlNm.getValue());
                    }
                    if (ZYPCommonFunc.hasValue(mdlId)) {
                        ZYPEZDItemValueSetter.setValue(this.mdlId, mdlId);
                        ZYPEZDItemValueSetter.setValue(this.mdlDescTxt, nwai4120_14.somPrmoMdlNm.getValue());
                    } else {
                        this.mdlId.clear();
                        this.mdlDescTxt.clear();
                    }
                }
            }
        } else {

            // RMA
            //if (this.serIf06TMsg != null) {

                String somDescSerNum = null;
                if (NWAB412001Constant.CONFIG_TYPE.TRADE_IN_RMA.equals(configType)) {
                    if (this.nwai4120_05 != null) {
                        somDescSerNum = this.nwai4120_05.somDescSerNum.getValue();
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
            //}

            //QC#20606
            if (!ZYPCommonFunc.hasValue(this.mdlId)) {
                BigDecimal mdlId = null;
                if (!this.dsImptOrdBean.getCacheModelMap().containsKey(this.nwai4120_05.somMdlTxt.getValue())) {
                    mdlId = NWXC412001.getMdlIdByName(glblCmpyCd, this.nwai4120_05.somMdlTxt.getValue());
                    this.dsImptOrdBean.getCacheModelMap().put(this.nwai4120_05.somMdlTxt.getValue(), mdlId);
                } else {
                    mdlId = this.dsImptOrdBean.getCacheModelMap().get(this.nwai4120_05.somMdlTxt.getValue());
                }
                if (ZYPCommonFunc.hasValue(mdlId)) {
                    ZYPEZDItemValueSetter.setValue(this.mdlId, mdlId);
                    ZYPEZDItemValueSetter.setValue(this.mdlDescTxt, S21StringUtil.subStringByLength(this.nwai4120_05.somMdlTxt.getValue(), 0, 90));
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
        if (NWAB412001Constant.CONFIG_TYPE.REGULAR_SHIP_CONFIG.equals(configType)) {

            if (!ZYPCommonFunc.hasValue(this.nwai4120_12.somPsnCnt) || BigDecimal.ZERO.compareTo(this.nwai4120_12.somPsnCnt.getValue()) == 0) {

                //QC#20753
                // from address
                String address = 
                    S21StringUtil.concatStrings(
                        S21StringUtil.trimString(
                                S21StringUtil.concatStrings(
                                        NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai4120_12.somShipToAddr_01.getValue())), 
                                        " ",
                                        NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai4120_12.somShipToAddr_02.getValue())))), 
                        NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai4120_12.somCityAddr.getValue())), 
                        NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai4120_12.somStCd.getValue())),
                        NWXC412001.toUpperCase(NWXC412001.cutString(S21StringUtil.trimString(this.nwai4120_12.somZipCd.getValue()), 5)));
                
                if (!this.dsImptOrdBean.getCacheShipToCustMap().containsKey("FROMADDR" + this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue() + address)) {
                    shipTo = NWXC412001.getShipToInfoFromAddress(glblCmpyCd, address, salesDate, this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue());
                    this.dsImptOrdBean.getCacheShipToCustMap().put("FROMADDR" + this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue() + address, shipTo);
                } else {
                    shipTo = this.dsImptOrdBean.getCacheShipToCustMap().get("FROMADDR" + this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue() + address);
                }

            } else {

                if (!this.dsImptOrdBean.getCacheShipToCustMap().containsKey("FROMPSN" + this.nwai4120_12.somPsnCnt.getValue().toPlainString())) {
                    shipTo = NWXC412001.getShipToInfoFromSomPsnCnt(glblCmpyCd, this.nwai4120_12.somPsnCnt.getValue(), salesDate);
                    this.dsImptOrdBean.getCacheShipToCustMap().put("FROMPSN" + this.nwai4120_12.somPsnCnt.getValue().toPlainString(), shipTo);
                } else {
                    shipTo = this.dsImptOrdBean.getCacheShipToCustMap().get("FROMPSN" + this.nwai4120_12.somPsnCnt.getValue().toPlainString());
                }

            }
            if (shipTo == null || shipTo.isEmpty()) {

                shipToCustAcctCd = S21StringUtil.subStringByLength(this.nwai4120_12.somAcctNum.getValue(), 0, 20);
                shipToCustLocCd = null;
                //shipToLocNm = null;
                shipToLocNm = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue(), 0, 60);
                shipToAddlLocNm = null;
                String address = S21StringUtil.concatStrings(this.nwai4120_12.somShipToAddr_01.getValue(), this.nwai4120_12.somShipToAddr_02.getValue());
                shipToFirstLineAddr = S21StringUtil.subStringByLength(address, 0, 60);
                shipToScdLineAddr = S21StringUtil.subStringByLength(address, 60, 60);
                shipToThirdLineAddr = S21StringUtil.subStringByLength(address, 120, 60);
                shipToFrthLineAddr = S21StringUtil.subStringByLength(address, 240, 60);
                shipToCtyAddr = S21StringUtil.subStringByLength(this.nwai4120_12.somCityAddr.getValue(), 0, 25);
                shipToStCd = S21StringUtil.subStringByLength(this.nwai4120_12.somStCd.getValue(), 0, 2);
                shipToProvNm = null;
                shipToCntyNm = S21StringUtil.subStringByLength(this.nwai4120_12.somCntyNm.getValue(), 0, 30);
                shipToPostCd = S21StringUtil.subStringByLength(this.nwai4120_12.somZipCd.getValue(), 0, 15);
                shipToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;
                shipToAcctNm = null;
            }

        } else if (NWAB412001Constant.CONFIG_TYPE.OCE_PROMOTION_RMA.equals(configType)) {

            NWAI4120_14TMsg nwai4120_14 = null;
            if (!this.nwai4120_14List.isEmpty()) {

                nwai4120_14 = this.nwai4120_14List.get(0);
                //QC#20753
                // from address
                String address = 
                    S21StringUtil.concatStrings(
                        S21StringUtil.trimString(
                                S21StringUtil.concatStrings(
                                        NWXC412001.toUpperCase(S21StringUtil.trimString(nwai4120_14.somPrmoAddr_01.getValue())), 
                                        " ",
                                        NWXC412001.toUpperCase(S21StringUtil.trimString(nwai4120_14.somPrmoAddr_02.getValue())))), 
                        NWXC412001.toUpperCase(S21StringUtil.trimString(nwai4120_14.somPrmoCityTxt.getValue())), 
                        NWXC412001.toUpperCase(S21StringUtil.trimString(nwai4120_14.somPrmoStCd.getValue())), 
                        NWXC412001.toUpperCase(NWXC412001.cutString(S21StringUtil.trimString(nwai4120_14.somPrmoZipCd.getValue()), 5)));
                
                if (!this.dsImptOrdBean.getCacheShipToCustMap().containsKey("FROMADDR" + this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue() + address)) {
                    shipTo = NWXC412001.getShipToInfoFromAddress(glblCmpyCd, address, salesDate, this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue());
                    this.dsImptOrdBean.getCacheShipToCustMap().put("FROMADDR" + this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue() + address, shipTo);
                } else {
                    shipTo = this.dsImptOrdBean.getCacheShipToCustMap().get("FROMADDR" + this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue() + address);
                }

            }
            if (shipTo == null || shipTo.isEmpty()) {

                if (nwai4120_14 != null) {

                    shipToCustAcctCd = null;
                    shipToCustLocCd = null;
                    //shipToLocNm = null;
                    shipToLocNm = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue(), 0, 60);
                    shipToAddlLocNm = null;
                    shipToFirstLineAddr = S21StringUtil.subStringByLength(nwai4120_14.somPrmoAddr_01.getValue(), 0, 60);
                    shipToScdLineAddr = S21StringUtil.subStringByLength(nwai4120_14.somPrmoAddr_02.getValue(), 61, 60);
                    shipToThirdLineAddr = null;
                    shipToFrthLineAddr = null;
                    shipToCtyAddr = S21StringUtil.subStringByLength(nwai4120_14.somPrmoCityTxt.getValue(), 0, 25);
                    shipToStCd = S21StringUtil.subStringByLength(nwai4120_14.somPrmoStCd.getValue(), 0, 2);
                    shipToProvNm = null;
                    shipToCntyNm = S21StringUtil.subStringByLength(nwai4120_14.somPrmoCntyNm.getValue(), 0, 30);
                    shipToPostCd = S21StringUtil.subStringByLength(nwai4120_14.somPrmoZipCd.getValue(), 0, 15);
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
                    String address = 
                        S21StringUtil.concatStrings(
                            S21StringUtil.trimString(
                                    S21StringUtil.concatStrings(
                                            NWXC412001.toUpperCase(S21StringUtil.trimString(this.serIf06TMsg.pickUpAddr_01.getValue())), 
                                            " ",
                                            NWXC412001.toUpperCase(S21StringUtil.trimString(this.serIf06TMsg.pickUpAddr_02.getValue())))), 
                            NWXC412001.toUpperCase(S21StringUtil.trimString(this.serIf06TMsg.pickUpCityAddr.getValue())), 
                            NWXC412001.toUpperCase(S21StringUtil.trimString(this.serIf06TMsg.pickUpStCd.getValue())), 
                            NWXC412001.toUpperCase(NWXC412001.cutString(S21StringUtil.trimString(this.serIf06TMsg.pickUpZipCd.getValue()), 5)));
                    
                    if (!this.dsImptOrdBean.getCacheShipToCustMap().containsKey("FROMADDR" + this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue() + address)) {
                        shipTo = NWXC412001.getShipToInfoFromAddress(glblCmpyCd, address, salesDate, this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue());
                        this.dsImptOrdBean.getCacheShipToCustMap().put("FROMADDR" + this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue() + address, shipTo);
                    } else {
                        shipTo = this.dsImptOrdBean.getCacheShipToCustMap().get("FROMADDR" + this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue() + address);
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
                    shipToLocNm = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue(), 0, 60);
                    shipToAddlLocNm = null;
                    shipToFirstLineAddr = S21StringUtil.subStringByLength(this.serIf06TMsg.pickUpAddr_01.getValue(), 0, 60);
                    shipToScdLineAddr = S21StringUtil.subStringByLength(this.serIf06TMsg.pickUpAddr_02.getValue(), 61, 60);
                    shipToThirdLineAddr = null;
                    shipToFrthLineAddr = null;
                    shipToCtyAddr = S21StringUtil.subStringByLength(this.serIf06TMsg.pickUpCityAddr.getValue(), 0, 25);
                    shipToStCd = S21StringUtil.subStringByLength(this.serIf06TMsg.pickUpStCd.getValue(), 0, 2);
                    shipToProvNm = null;
                    shipToCntyNm = S21StringUtil.subStringByLength(this.serIf06TMsg.pickUpCntyNm.getValue(), 0, 30);
                    shipToPostCd = S21StringUtil.subStringByLength(this.serIf06TMsg.pickUpZipCd.getValue(), 0, 15);
                    shipToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;
                    shipToAcctNm = null;
                }
                
            }
            //QC#24242
            //if ((shipTo == null || shipTo.isEmpty()) && this.noSerIf06TMsg != null) {
            if ((shipTo == null || shipTo.isEmpty()) && this.noSerIf06TMsg != null && !ZYPCommonFunc.hasValue(shipToFirstLineAddr)) {

                if (!ZYPCommonFunc.hasValue(this.noSerIf06TMsg.somPsnDescTxt) || "0".equals(this.noSerIf06TMsg.somPsnDescTxt.getValue())) {
                    //QC#20753
                    // from address
                    String address = 
                        S21StringUtil.concatStrings(
                            S21StringUtil.trimString(
                                    S21StringUtil.concatStrings(
                                            NWXC412001.toUpperCase(
                                            		S21StringUtil.trimString(this.noSerIf06TMsg.pickUpAddr_01.getValue())), 
                                                    " ",
                                                    NWXC412001.toUpperCase(S21StringUtil.trimString(this.noSerIf06TMsg.pickUpAddr_02.getValue())))), 
                            NWXC412001.toUpperCase(S21StringUtil.trimString(this.noSerIf06TMsg.pickUpCityAddr.getValue())), 
                            NWXC412001.toUpperCase(S21StringUtil.trimString(this.noSerIf06TMsg.pickUpStCd.getValue())), 
                            NWXC412001.toUpperCase(NWXC412001.cutString(S21StringUtil.trimString(this.noSerIf06TMsg.pickUpZipCd.getValue()), 5)));
                    
                    if (!this.dsImptOrdBean.getCacheShipToCustMap().containsKey("FROMADDR" + this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue() + address)) {
                        shipTo = NWXC412001.getShipToInfoFromAddress(glblCmpyCd, address, salesDate, this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue());
                        this.dsImptOrdBean.getCacheShipToCustMap().put("FROMADDR" + this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue() + address, shipTo);
                    } else {
                        shipTo = this.dsImptOrdBean.getCacheShipToCustMap().get("FROMADDR" + this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue() + address);
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
                    shipToLocNm = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai4120_01.somShipToCustNm.getValue(), 0, 60);
                    shipToAddlLocNm = null;
                    shipToFirstLineAddr = S21StringUtil.subStringByLength(this.noSerIf06TMsg.pickUpAddr_01.getValue(), 0, 60);
                    shipToScdLineAddr = S21StringUtil.subStringByLength(this.noSerIf06TMsg.pickUpAddr_02.getValue(), 61, 60);
                    shipToThirdLineAddr = null;
                    shipToFrthLineAddr = null;
                    shipToCtyAddr = S21StringUtil.subStringByLength(this.noSerIf06TMsg.pickUpCityAddr.getValue(), 0, 25);
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

        ZYPEZDItemValueSetter.setValue(this.configCratTs, NWXC412001.getValidDate(this.dsImptOrdBean.nwai4120_01.cratTs.getValue()));
        this.svcSlnSq.clear();
        this.svcSlnNm.clear();

        // SOM configuration ID
        if (NWAB412001Constant.CONFIG_TYPE.REGULAR_SHIP_CONFIG.equals(configType)) {

            ZYPEZDItemValueSetter.setValue(this.somConfigId, this.nwai4120_12.somConfigId);

        } else if (NWAB412001Constant.CONFIG_TYPE.TRADE_IN_RMA.equals(configType)) {

            ZYPEZDItemValueSetter.setValue(this.somConfigId, this.nwai4120_05.somSoftCostId);
        } else if (NWAB412001Constant.CONFIG_TYPE.BUYOUT_RMA.equals(configType)) {

            ZYPEZDItemValueSetter.setValue(this.somConfigId, this.nwai4120_05.somSoftCostId);

        } else if (NWAB412001Constant.CONFIG_TYPE.UPGRADE_RETURN_RMA.equals(configType)) {

            ZYPEZDItemValueSetter.setValue(this.somConfigId, this.nwai4120_05.somSoftCostId);

        } else if (NWAB412001Constant.CONFIG_TYPE.OCE_PROMOTION_RMA.equals(configType)) {

            if (!this.nwai4120_14List.isEmpty()) {

                ZYPEZDItemValueSetter.setValue(this.somConfigId, this.nwai4120_14List.get(0).somConfigId);
            }

        } else {

            // none
        }

        // address group
        if (NWAB412001Constant.CONFIG_TYPE.REGULAR_SHIP_CONFIG.equals(configType)) {

            ZYPEZDItemValueSetter.setValue(this.addrLbTxt, this.nwai4120_12.addrLbTxt);

        } else if (NWAB412001Constant.CONFIG_TYPE.TRADE_IN_RMA.equals(configType)) {

            this.addrLbTxt.clear();
        }

        ZYPEZDItemValueSetter.setValue(this.shipToAcctNm, shipToAcctNm);

        // *******************************
        // prepare child elements
        // *******************************

        for (SomDsImptOrdCtacPsnBean ctacPsnBean : this.dsImptOrdCtacPsnBeanList) {
            ctacPsnBean.doImptMapping(glblCmpyCd, salesDate);
        }

        this.dsImptOrdIstlInfoBean.doImptMapping(glblCmpyCd, salesDate);
        this.dsImptOrdDelyInfoBean.doImptMapping(glblCmpyCd, salesDate);
//        this.dsImptOrdSiteSrvyBean.doImptMapping(glblCmpyCd, salesDate); // 2017/09/13 QC#21084 Del

        for (SomDsImptOrdDtlBean dtlBean : this.dsImptOrdDtlBeanList) {
            if (!dtlBean.doImptMapping(glblCmpyCd, salesDate)) {
                isSuccess = false;
            }
        }

        for (SomDsImptOrdRtrnDtlBean rtrnDtlBean : this.dsImptOrdDtlRtrnBeanList) {
            if (!rtrnDtlBean.doImptMapping(glblCmpyCd, salesDate)) {
                isSuccess = false;
            }
        }
        //
        // for (SomDsImptSvcDtlBean svcDtlBean :
        // this.dsImptSvcDtlBeanList) {
        // svcDtlBean.doImptMapping(glblCmpyCd, salesDate);
        // }
        //
        // for (SomDsImptSvcPrcBean svcPrcBean :
        // this.dsImptSvcPrcBeanList) {
        // svcPrcBean.doImptMapping(glblCmpyCd, salesDate);
        // }
        //
        // for (SomDsImptSvcConfigRefBean svcConfigRefBean :
        // this.dsImptSvcConfigRefBeanList) {
        // svcConfigRefBean.doImptMapping(glblCmpyCd, salesDate);
        // }
        //
        // for (SomDsImptSvcAddlBaseBean svcAddlBaseBean :
        // this.dsImptSvcAddlBaseBeanList) {
        // svcAddlBaseBean.doImptMapping(glblCmpyCd, salesDate);
        // }
        //
        // for (SomDsImptSvcAddlChrgBean svcAddlChrgBean :
        // this.dsImptSvcAddlChrgBeanList) {
        // svcAddlChrgBean.doImptMapping(glblCmpyCd, salesDate);
        // }
        //
        // for (SomDsImptSvcUsgPrcBean svcUsgPrcBean :
        // this.dsImptSvcUsgPrcBeanList) {
        // svcUsgPrcBean.doImptMapping(glblCmpyCd, salesDate);
        // }

        this.dsImptOrdSlsCrBean.doImptMapping(glblCmpyCd, salesDate);

        return isSuccess;
    }

    @Override
    public void addErrorInfo(NWXC220001ErrorInfo errorInfo) {

        BigDecimal transactionId = null;
        BigDecimal unitId = null;
        BigDecimal seqNumber = null;

        if (this.nwai4120_12 != null) {
            transactionId = this.nwai4120_12.transactionId.getValue();
            unitId = this.nwai4120_12.transactionId.getValue();
            seqNumber = this.nwai4120_12.transactionId.getValue();
        } else if (this.nwai4120_05 != null) {
            transactionId = this.nwai4120_05.transactionId.getValue();
            unitId = this.nwai4120_05.transactionId.getValue();
            seqNumber = this.nwai4120_05.transactionId.getValue();
        }

        this.errInfoList.add(new NWAB412001ErrorInfo(transactionId, unitId, seqNumber, errorInfo));
    }

    @Override
    public void addErrorInfo(List<NWXC220001ErrorInfo> errorInfoList) {
        for (NWXC220001ErrorInfo errorInfo : errorInfoList) {
            addErrorInfo(errorInfo);
        }
    }

    @Override
    public List<NWAB412001ErrorInfo> getErrorInfo() {
        List<NWAB412001ErrorInfo> errList = new ArrayList<NWAB412001ErrorInfo>();

        errList.addAll(this.errInfoList);

        for (SomDsImptOrdDtlBean dtlBean : this.dsImptOrdDtlBeanList) {
            errList.addAll(dtlBean.getErrorInfo());
        }

        for (SomDsImptOrdRtrnDtlBean rtrnDtlBean : this.dsImptOrdDtlRtrnBeanList) {
            errList.addAll(rtrnDtlBean.getErrorInfo());
        }

        return errList;
    }

    @Override
    public boolean hasError() {
        return (this.errInfoList.size() > 0);
    }
    
    public String getFlPrcListCd() {
        return this.pFlPrcListCd;
    }
    public String getPrcCatgCd() {
        return this.pPrcCatgCd;
    }
    public String getSlsRepOrSlsTeamTocCd() {
        return this.pSlsRepOrSlsTeamTocCd;
    }
    public String getDsOrdLineCatgCd() {
        return this.pDsOrdLineCatgCd;
    }
    public Map<String, Object> getVndMap() {
        return pVndMap;
    }
    public String getRtrnRsnCd() {
        return this.pRtrnRsnCd;
    }
    public BigDecimal getSvcConfigMstrPk() {
        return this.pSvcConfigMstrPk;
    }
    public BigDecimal setSvcConfigMstrPk(BigDecimal pSvcConfigMstrPk) {
        return this.pSvcConfigMstrPk = pSvcConfigMstrPk;
    }
    public String getSplitTypeTxt() {
        return this.pSplitTypeTxt;
    }
    //QC#17768
    public boolean isExistingConfiguration() {
        return this.isExistingConfigurationFlag;
    }
    public boolean isAddAccessoryToExistingConfiguration() {
        return this.isAddAccessoryToExistingConfigurationFlag;
    }
    //QC#20739
    public String getPickUpDtOthDescTxt() {
        return this.pPickUpDtOthDescTxt;
    }
    public void addExsistingCondigurationListForPreOwned(List<NWAI4120_10TMsg> if10TMsgList) {
        //have configuration list from serial information
        if (if10TMsgList != null && if10TMsgList.size() > 0) {
            //Machine Configuration from Pre-Owned
            for (NWAI4120_10TMsg nwai4120_10 : if10TMsgList) {
                if (ZYPCommonFunc.hasValue(nwai4120_10.somDescSerNum) 
                        && ("10".equals(nwai4120_10.somMdseTpTxt.getValue())
                        || "11".equals(nwai4120_10.somMdseTpTxt.getValue()))) {
                    //QC#25324
                    //get configuration from Configuration ID from Pre-Owned
                    if (ZYPCommonFunc.hasValue(nwai4120_10.somInvtyConfigId) 
                            && BigDecimal.ZERO.compareTo(nwai4120_10.somInvtyConfigId.getValue()) != 0) {
                        List<Map<String, Object>> existslist = NWXC412001.getConfigMasterDetailListFromConfigID(
                                dsImptOrdBean.glblCmpyCd.getValue(), 
                                nwai4120_10.somInvtyConfigId.getValue(), 
                                dsImptOrdBean.slsDt);
                        if (existslist != null && existslist.size() > 0) {
                            this.isAddAccessoryToExistingConfigurationFlag = false;
                            this.isExistingConfigurationFlag = true;
                            existingConfigurationList.addAll(existslist);
                            break;
                        }
                    }
                    //get configuration from serial# from Pre-Owned
                    List<Map<String, Object>> existslist = NWXC412001.getConfigMasterDetailList(
                            dsImptOrdBean.glblCmpyCd.getValue(), 
                            nwai4120_10.somDescSerNum.getValue(), 
                            dsImptOrdBean.slsDt);
                    if (existslist != null && existslist.size() > 0) {
                        this.isAddAccessoryToExistingConfigurationFlag = false;
                        this.isExistingConfigurationFlag = true;
                        existingConfigurationList.addAll(existslist);
                        break;
                    }
                }
            }
            //QC#25324
            //No Machine Configuration from Pre-Owned
            if (this.existingConfigurationList == null || existingConfigurationList.size() == 0) {
                for (NWAI4120_10TMsg nwai4120_10 : if10TMsgList) {
                    if (ZYPCommonFunc.hasValue(nwai4120_10.somDescSerNum)) {
                        //QC#25324
                      //get configuration from Configuration ID from Pre-Owned
                      if (ZYPCommonFunc.hasValue(nwai4120_10.somInvtyConfigId) 
                              && BigDecimal.ZERO.compareTo(nwai4120_10.somInvtyConfigId.getValue()) != 0) {
                          List<Map<String, Object>> existslist = NWXC412001.getConfigMasterDetailListFromConfigID(
                                  dsImptOrdBean.glblCmpyCd.getValue(), 
                                  nwai4120_10.somInvtyConfigId.getValue(), 
                                  dsImptOrdBean.slsDt);
                          if (existslist != null && existslist.size() > 0) {
                              this.isAddAccessoryToExistingConfigurationFlag = false;
                              this.isExistingConfigurationFlag = true;
                              existingConfigurationList.addAll(existslist);
                              break;
                          }
                      }
                        
                        //get configuration on warehouse
                        List<Map<String, Object>> existslist = NWXC412001.getConfigMasterDetailList(
                                dsImptOrdBean.glblCmpyCd.getValue(), 
                                nwai4120_10.somDescSerNum.getValue(), 
                                dsImptOrdBean.slsDt);
                        if (existslist != null && existslist.size() > 0) {
                            this.isAddAccessoryToExistingConfigurationFlag = false;
                            this.isExistingConfigurationFlag = true;
                            existingConfigurationList.addAll(existslist);
                            break;
                        }
                    }
                }
            }
        }
    }
    public void addExsistingCondigurationListForAddAcc() {
        //have configuration list from serial information
        if (this.nwai4120_13List != null && this.nwai4120_13List.size() > 0) {
            for (NWAI4120_13TMsg serialTMsg : this.nwai4120_13List) {
                if (serialTMsg != null) {
                    //get configuration on customer site
                    List<Map<String, Object>> existslist = NWXC412001.getConfigMasterDetailList(
                            dsImptOrdBean.glblCmpyCd.getValue(), 
                            serialTMsg.somSerNum.getValue(), 
                            dsImptOrdBean.slsDt);
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
    //QC#24023
    public void setCopierDeclineMaintenanceFlg(boolean flg) {
        this.copierDeclineMainte = flg;
    }
    public void setNonCopierDeclineMaintenanceFlg(boolean flg) {
        this.nonCopierDeclineMainte = flg;
    }
    public boolean getCopierDeclineMaintenanceFlg() {
        return this.copierDeclineMainte;
    }
    public boolean getNonCopierDeclineMaintenanceFlg() {
        return this.nonCopierDeclineMainte;
    }

    // Add Start 2018/05/10 QC#20343
    /**
     * @param glblCmpyCd String
     * @return BigDecimal
     */
    public BigDecimal getMdlId(String glblCmpyCd) {
        if (!NWAB412001Constant.CONFIG_TYPE.REGULAR_SHIP_CONFIG.equals(configType)) {
            return null;
        }

        BigDecimal result = null;

        if (this.existingConfigurationList != null && this.existingConfigurationList.size() > 0) {
            Map<String, Object> existingConfigurationMap = this.existingConfigurationList.get(0);
            if (existingConfigurationMap != null) {
                result = (BigDecimal) existingConfigurationMap.get("MDL_ID");
            }
        }

        if (!ZYPCommonFunc.hasValue(result)) {
            String somMdlDescTxt = this.getSomMdlDescTxt();

            if (ZYPCommonFunc.hasValue(somMdlDescTxt)) {
                if (!this.dsImptOrdBean.getCacheModelMap().containsKey(somMdlDescTxt)) {
                    result = NWXC412001.getMdlIdByName(glblCmpyCd, somMdlDescTxt);
                    this.dsImptOrdBean.getCacheModelMap().put(somMdlDescTxt, result);
                } else {
                    result = this.dsImptOrdBean.getCacheModelMap().get(somMdlDescTxt);
                }
            }
        }

        return result;
    }
    // Add End 2018/05/10 QC#20343
}
