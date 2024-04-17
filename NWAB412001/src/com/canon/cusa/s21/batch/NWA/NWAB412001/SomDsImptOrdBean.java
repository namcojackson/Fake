/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import parts.common.EZDMsg;
import parts.common.EZDTBigDecimalItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_IMPT_ORDTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.FRT_CONDTMsg;
import business.db.NWAI4120_01TMsg;
import business.db.NWAI4120_02TMsg;
import business.db.NWAI4120_03TMsg;
import business.db.NWAI4120_04TMsg;
import business.db.NWAI4120_05TMsg;
import business.db.NWAI4120_06TMsg;
import business.db.NWAI4120_08TMsg;
import business.db.NWAI4120_10TMsg;
import business.db.NWAI4120_12TMsg;
import business.db.NWAI4120_13TMsg;
import business.db.NWAI4120_14TMsg;
import business.db.NWAI4120_16TMsg;
import business.db.NWAI4120_17TMsg;
import business.db.NWAI4120_18TMsg;
import business.db.NWAI4120_19TMsg;
import business.db.NWAI4120_20TMsg;
import business.db.NWAI4120_21TMsg;
import business.parts.NWZC180001PMsg;
import business.parts.NPZC113001PMsg;

import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.CONFIG_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.CONTRACT_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.CTAC_PSN_SUB_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.CTAC_PSN_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.DELY_INFO_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.DTL_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.INTERFACE_ID;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.ISTL_INFO_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.PRC_CALC_BASE_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.RENTAL_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.RTRN_DTL_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.SHELL_TYPE;
//import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.SITE_SRVY_TYPE; // 2017/09/13 QC#21084 Del
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.SLS_CR_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.SVC_USG_PRC_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.SOM_CONTR_IND;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Util;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
//import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
//import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptOrdBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 2016/11/29   Fujitsu         M.Yamada        Update          QC#16266
 * 12/01/2016   SRAA            K.Aratani       Update          QC#15539
 * 01/13/2017   SRAA            K.Aratani       Update          QC#17125
 * 01/20/2017   SRAA            K.Aratani       Update          QC#17122
 * 01/30/2017   SRAA            K.Aratani       Update          QC#17120
 * 02/02/2017   Fujitsu         M.Ohno          Update          QC#17119
 * 03/08/2017   SRAA            K.Aratani       Update          QC#17768
 * 03/31/2017   SRAA            K.Aratani       Update          QC#18210,QC#18211
 * 2017/06/22   Fujitsu         M.Yamada        Update          QC#19451
 * 07/26/2017   SRAA            K.Aratani       Update          QC#20189
 * 07/27/2017   SRAA            K.Aratani       Update          QC#20393
 * 08/17/2017   SRAA            K.Aratani       Update          QC#19968
 * 08/23/2017   SRAA            K.Aratani       Update          QC#20753
 * 09/11/2017   SRAA            K.Aratani       Update          QC#20988
 * 09/13/2017   Fujitsu         S.Iidaka        Update          QC#21084
 * 09/18/2017   SRAA            K.Aratani       Update          QC#21236
 * 09/27/2017   SRAA            K.Aratani       Update          QC#21442
 * 02/16/2018   SRAA            K.Aratani       Update          QC#24092
 * 02/16/2018   SRAA            K.Aratani       Update          QC#24023
 * 02/16/2018   SRAA            K.Aratani       Update          QC#24240
 * 02/16/2018   SRAA            K.Aratani       Update          QC#24101
 * 04/05/2018   SRA             K.Aratani       Update          QC#25324
 * 05/10/2018   Fujitsu         Hd.Sugawara     Update          QC#20343
 * 05/22/2018   Fujitsu         Hd.Sugawara     Update          QC#20343-1
 *</pre>
 */
public class SomDsImptOrdBean extends DS_IMPT_ORDTMsg implements ISomDsImptOrd, ISomErrorInfo {

    private static final long serialVersionUID = 1L;

    public S21SsmBatchClient ssmBatchClient;

    public final String slsDt;

    public final NWAI4120_01TMsg nwai4120_01;

    public final List<SomDsImptOrdSomApvlBean> dsImptOrdSomApvlBeanList;

    public final List<SomDsImptOrdSomPrftBean> dsImptOrdSomPrftBeanList;

    // Key : Map<somQuoteId-somConfigId, Map<addrLbTxt>,
    // List<SomDsImptOrdConfigBean>>>
    // public final LinkedHashMap<String, LinkedHashMap<String,
    // List<SomDsImptOrdConfigBean>>> dsImptOrdConfigBeanMap;

    public final List<SomDsImptOrdConfigBean> dsImptOrdConfigBeanList;

    public final List<SomDsImptOrdDtlBean> dsImptOrdDtlBeanList;

    public final List<SomDsImptOrdRtrnDtlBean> dsImptOrdRtrnDtlBean;

    public SomDsImptOrdSlsCrBean dsImptOrdSlsCrBean;

    public final List<SomDsImptOrdCtacPsnBean> dsImptOrdCtacPsnBeanList;

    public SomDsImptOrdConfigBean lastShipConfigBean;

    // Add Start 2018/05/10 QC#20343
    public SomDsImptOrdConfigBean lastEquipConfigBean;
    // Add End 2018/05/10 QC#20343

    public final List<SomDsImptSvcDtlBean> dsImptSvcDtlBeanList;
    
    //For Performance
    private static Map<String, Object> varCharConstMap;
    private static Map<String, Map<String, Object>> cacheItemMap;
    private static Map<String, Map<String, Object>> cacheSlsRepTocCdMap;
    private static Map<String, String> cachePrcCatgCdMap;
    private static Map<String, String> cachePrcCatgNmMap;
    private static Map<String, BigDecimal> cacheSpecCondPrcMap;
    private static Map<String, Map<String, Object>> cacheShipToCustMap;
    private static Map<String, String> cacheS21PsnFromInstallRepNameMap;
    private static Map<String, String> cacheSlsRepRoleTpCdMap;
    private static Map<String, BigDecimal> cacheModelMap;
    private static Map<String, BigDecimal> cachePrcMtrPkgPkMap;
    private static Map<String, NWZC180001PMsg> cacheDefaultWhMap;
    private static Map<String, NPZC113001PMsg> cacheVndMap;
    
    private String pDsOrdCatgCd;
    private String pDsOrdTpCd;
    private String pOutBndDsOrdLineCatgCd;
    private String pInBndDsOrdLineCatgCd;
    private String pDefaultPrcCatgCd;
    private String pLeaseCmpyDsAcctNum; //QC#15539-14
    private String pCsmpPrcListCd;
    private int declPntDigt;

    public SomDsImptOrdIstlInfoBean dsImptOrdIstlInfoBean;

    public SomDsImptOrdDelyInfoBean dsImptOrdDelyInfoBean;

    public SomDsImptOrdSiteSrvyBean dsImptOrdSiteSrvyBean;
    
    /** List<NWAB412001ErrorInfo> */
    final public List<NWAB412001ErrorInfo> errInfoList;

    public SomDsImptOrdBean(S21SsmBatchClient ssmBatchClient, String glblCmpyCd, String slsDt, NWAI4120_01TMsg nwai4120_01) {
        super();

        this.ssmBatchClient = ssmBatchClient;
        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        this.slsDt = slsDt;
        this.nwai4120_01 = nwai4120_01;
        this.dsImptOrdSomPrftBeanList = new ArrayList<SomDsImptOrdSomPrftBean>();
        this.dsImptOrdSomApvlBeanList = new ArrayList<SomDsImptOrdSomApvlBean>();
        // this.dsImptOrdConfigBeanMap = new LinkedHashMap<String,
        // LinkedHashMap<String, List<SomDsImptOrdConfigBean>>>();
        this.dsImptOrdConfigBeanList = new ArrayList<SomDsImptOrdConfigBean>();
        this.dsImptOrdDtlBeanList = new ArrayList<SomDsImptOrdDtlBean>();
        this.dsImptOrdRtrnDtlBean = new ArrayList<SomDsImptOrdRtrnDtlBean>();
        this.dsImptOrdCtacPsnBeanList = new ArrayList<SomDsImptOrdCtacPsnBean>();
        this.lastShipConfigBean = null;
        // Add Start 2018/05/10 QC#20343
        this.lastEquipConfigBean = null;
        // Add End 2018/05/10 QC#20343
        this.dsImptSvcDtlBeanList = new ArrayList<SomDsImptSvcDtlBean>();
        this.errInfoList = new ArrayList<NWAB412001ErrorInfo>();
        //varCharConstMap = new HashMap<String, Object>();
        this.pDsOrdCatgCd = NWXC412001.getDsOrdCatgCd(this.glblCmpyCd.getValue(), CPO_SRC_TP.SOM, this.nwai4120_01.interfaceId.getValue(), this.nwai4120_01.trxTpTxt.getValue());
        //QC#15539-14
        //this.pDsOrdTpCd = NWXC412001.getDsOrdTpCd(this.glblCmpyCd.getValue(), CPO_SRC_TP.SOM, this.nwai4120_01.interfaceId.getValue(), this.nwai4120_01.trxTpTxt.getValue(), this.nwai4120_01.leaseCmpyNm.getValue());
        Map<String, Object> dsOrdTpMap = NWXC412001.getDsOrdTpInfo(this.glblCmpyCd.getValue(), CPO_SRC_TP.SOM, this.nwai4120_01.interfaceId.getValue(), this.nwai4120_01.trxTpTxt.getValue(), this.nwai4120_01.leaseCmpyNm.getValue());
        this.pDsOrdTpCd = (String) dsOrdTpMap.get("TRGT_ATTRB_TXT_01");
        this.pLeaseCmpyDsAcctNum = (String) dsOrdTpMap.get("TRGT_ATTRB_TXT_02");
        this.pOutBndDsOrdLineCatgCd = NWXC412001.getDsLineCatgCd(this.glblCmpyCd.getValue(), this.pDsOrdTpCd, DS_ORD_LINE_DRCTN.OUTBOUND, this.slsDt);
        this.pInBndDsOrdLineCatgCd = NWXC412001.getDsLineCatgCd(this.glblCmpyCd.getValue(), this.pDsOrdTpCd, DS_ORD_LINE_DRCTN.INBOUND, this.slsDt);
        this.pCsmpPrcListCd = null;
        if (!ZYPCommonFunc.hasValue(this.pInBndDsOrdLineCatgCd)) {
            this.pInBndDsOrdLineCatgCd = DS_ORD_LINE_CATG.RMA_NO_CREDIT;
        }
        cacheItemMap = new HashMap<String, Map<String, Object>>();
        cachePrcCatgCdMap = new HashMap<String, String>();
        cachePrcCatgNmMap = new HashMap<String, String>();
        cacheSpecCondPrcMap = new HashMap<String, BigDecimal>();
        cacheShipToCustMap = new HashMap<String, Map<String, Object>>();
        cacheS21PsnFromInstallRepNameMap = new HashMap<String, String>();
        cacheDefaultWhMap = new HashMap<String, NWZC180001PMsg>();
        cacheVndMap = new HashMap<String, NPZC113001PMsg>();

        declPntDigt = NWXC412001.getDecimalPointDigit(glblCmpyCd); // QC#19451
    }

    // public List<SomDsImptOrdConfigBean>
    // getSomDsImptOrdConfigBeans() {
    // List<SomDsImptOrdConfigBean> configBeanList = new
    // ArrayList<SomDsImptOrdConfigBean>();
    //
    // for (LinkedHashMap<String, List<SomDsImptOrdConfigBean>>
    // configGroupMap : this.dsImptOrdConfigBeanMap.values()) {
    // for (List<SomDsImptOrdConfigBean> configGroupList :
    // configGroupMap.values()) {
    // configBeanList.addAll(configGroupList);
    // }
    // }
    //
    // return configBeanList;
    // }

    public List<SomDsImptOrdConfigBean> getSomDsImptOrdConfigBeansByConfigKey(String configKey) {
        List<SomDsImptOrdConfigBean> configBeanList = new ArrayList<SomDsImptOrdConfigBean>();

        configBeanList = new ArrayList<SomDsImptOrdConfigBean>();
        for (SomDsImptOrdConfigBean configBean : this.dsImptOrdConfigBeanList) {
            if (configKey.equals(configBean.configKey)) {
                configBeanList.add(configBean);
            }
        }

        return configBeanList;
    }

    // public LinkedHashMap<String, List<SomDsImptOrdConfigBean>>
    // addSomDsImptOrdConfigBean(SomDsImptOrdConfigBean
    // dsImptOrdConfigBean) {
    // String configKey = dsImptOrdConfigBean.configKey;
    // LinkedHashMap<String, List<SomDsImptOrdConfigBean>>
    // configGroupMap;
    // List<SomDsImptOrdConfigBean> configGroupList;
    //
    // // Config Level
    // if (!this.dsImptOrdConfigBeanMap.containsKey(configKey)) {
    // configGroupMap = new LinkedHashMap<String,
    // List<SomDsImptOrdConfigBean>>();
    // this.dsImptOrdConfigBeanMap.put(configKey, configGroupMap);
    // }
    // configGroupMap = this.dsImptOrdConfigBeanMap.get(configKey);
    //
    // // Group ID Level
    // String groupId = dsImptOrdConfigBean.getGroupId();
    // if (!configGroupMap.containsKey(groupId)) {
    // configGroupList = new ArrayList<SomDsImptOrdConfigBean>();
    // configGroupMap.put(groupId, configGroupList);
    // }
    // configGroupList = configGroupMap.get(groupId);
    // configGroupList.add(dsImptOrdConfigBean);
    //
    // return configGroupMap;
    // }

    public void addSomDsImptOrdConfigBean(SomDsImptOrdConfigBean dsImptOrdConfigBean) {
        this.dsImptOrdConfigBeanList.add(dsImptOrdConfigBean);
    }

    public void addDsImptOrdCtacPsnBean() {
        new SomDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.HEADER, CTAC_PSN_SUB_TYPE.H_DELIVERY, this);
        new SomDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.HEADER, CTAC_PSN_SUB_TYPE.H_BILLING, this);
        new SomDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.HEADER, CTAC_PSN_SUB_TYPE.H_IT, this);
        new SomDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.HEADER, CTAC_PSN_SUB_TYPE.H_SUBSERVICES, this);

        String admin = "ADMIN";
        String usr = "USER";

        String csaPmtTpTxt = NWXC220001Util.convToString(this.nwai4120_01.csaPmtTpTxt.getValue(), "");

        if (admin != null && csaPmtTpTxt.toUpperCase().indexOf(admin) >= 0) {
            new SomDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.HEADER, CTAC_PSN_SUB_TYPE.H_EMANAGE_ADMIN, this);
        }

        if (usr != null && csaPmtTpTxt.toUpperCase().indexOf(usr) >= 0) {
            new SomDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.HEADER, CTAC_PSN_SUB_TYPE.H_EMANAGE_USER, this);
        }
    }

    public void createImptConfigDtlForApproval(SomDsImptOrdBean dsImptOrdBean, List<NWAI4120_02TMsg> nwai4120_02List, List<NWAI4120_03TMsg> nwai4120_03List) {
        List<NWAI4120_03TMsg> targetNwai4120_03List;
        SomDsImptOrdSomApvlBean apvlBean;

        for (NWAI4120_02TMsg if02TMsg : nwai4120_02List) {
            // *****************************************************************
            // Process (2)
            // *****************************************************************
            apvlBean = new SomDsImptOrdSomApvlBean(dsImptOrdBean, if02TMsg);
            targetNwai4120_03List = getNWAI4120_03TMsgByKey(if02TMsg.somApvlId.getValue(), nwai4120_03List);

            for (NWAI4120_03TMsg if03TMsg : targetNwai4120_03List) {
                // *************************************************************
                // Process (3)
                // *************************************************************
                new SomDsImptOrdApvlNoteBean(apvlBean, if03TMsg);
            }
        }
    }

    public void createImptConfigDtlForProfitability(SomDsImptOrdBean dsImptOrdBean, List<NWAI4120_04TMsg> nwai4120_04List) {
        // *****************************************************************
        // Process (4)
        // *****************************************************************
        for (NWAI4120_04TMsg if04TMsg : nwai4120_04List) {
            new SomDsImptOrdSomPrftBean(dsImptOrdBean, if04TMsg);
        }
    }

    public void createImptConfigDtlFromNWAI4120_12(SomDsImptOrdBean dsImptOrdBean, 
            List<NWAI4120_12TMsg> nwai4120_12List, 
            List<NWAI4120_13TMsg> nwai4120_13List, 
            List<NWAI4120_14TMsg> nwai4120_14List,
            List<NWAI4120_10TMsg> nwai4120_10List) {

        // Store Map (somConfigId - somConfigQty)
        Map<BigDecimal, BigDecimal> configQtyMap = new HashMap<BigDecimal, BigDecimal>();
        if (nwai4120_10List != null && !nwai4120_10List.isEmpty()) {
            for (NWAI4120_10TMsg nwai4120Tmsg : nwai4120_10List) {
                if (!configQtyMap.containsKey(nwai4120Tmsg.somConfigId.getValue())) {
                    configQtyMap.put(nwai4120Tmsg.somConfigId.getValue(), nwai4120Tmsg.somConfigQty.getValue());
                }
            }
        }
        Map<BigDecimal, BigDecimal> cacheMap = new HashMap<BigDecimal, BigDecimal>();
        // Exists Line Data
        if (configQtyMap != null && !configQtyMap.isEmpty()) {
            Object[] key = configQtyMap.keySet().toArray();
            java.util.Arrays.sort(key);
            // Distinct Config Loop
            for(int ii = 0 ; ii < key.length ; ii ++ ) {
                // Config Qty Loop
                for (int configQtyIndex = 0; configQtyIndex < (configQtyMap.get(key[ii])).intValue(); configQtyIndex++) {
                    // All Config Record Loop
                    int configRecordIndex  = 0;
                    NWAI4120_12TMsg lastIf12TMsg = null;
                    boolean storeFlg = false;
                    for (NWAI4120_12TMsg if12TMsg : nwai4120_12List) {
                        if (if12TMsg.somConfigId.getValue().compareTo((BigDecimal)key[ii]) != 0) {
                            continue;
                        }
                        
                        if (!S21StringUtil.isEquals(if12TMsg.lastUpdTs.getValue(), dsImptOrdBean.nwai4120_01.lastUpdTs.getValue())) {
                            continue;
                        }
                        
                        if (configQtyIndex == configRecordIndex) {
                            storeConfigData(dsImptOrdBean, if12TMsg, nwai4120_10List, nwai4120_13List, nwai4120_14List, cacheMap);
                            storeFlg = true;
                        }
                        lastIf12TMsg = if12TMsg;
                        configRecordIndex++;
                    }//End of All Config Record Loop
                    // Different between Config Qty <> NWAI4120_12 record count
                    if (!storeFlg) {
                        storeConfigData(dsImptOrdBean, lastIf12TMsg, nwai4120_10List, nwai4120_13List, nwai4120_14List, cacheMap);
                    }
                }//End of Config Qty Loop
            }
        // No Exists Line Data
        } else {
            // All Config Record Loop
            for (NWAI4120_12TMsg if12TMsg : nwai4120_12List) {
                if (!S21StringUtil.isEquals(if12TMsg.lastUpdTs.getValue(), dsImptOrdBean.nwai4120_01.lastUpdTs.getValue())) {
                    continue;
                }
                
                storeConfigData(dsImptOrdBean, if12TMsg, nwai4120_10List, nwai4120_13List, nwai4120_14List, cacheMap);
            }//End of All Config Record Loop
        }

        // Add Start 2018/05/10 QC#20343
        if (this.lastEquipConfigBean != null) {
            this.lastShipConfigBean = this.lastEquipConfigBean;
        }
        // Add End 2018/05/10 QC#20343
    }

    public void createImptConfigDtlFromNWAI4120_05(SomDsImptOrdBean dsImptOrdBean, List<NWAI4120_05TMsg> nwai4120_05List, List<NWAI4120_06TMsg> nwai4120_06List) {

        SomDsImptOrdConfigBean dsImptOrdConfigBean;
        SomDsImptOrdConfigBean lastShipConfigBean = dsImptOrdBean.lastShipConfigBean;
        SomDsImptOrdDtlBean dtlBean;
        SomDsImptOrdRtrnDtlBean rtrnDtlBean;
        List<NWAI4120_06TMsg> targetNwai4120_06List = new ArrayList<NWAI4120_06TMsg>();
        String quoteLineTpTxt, somMercItemCd, glblCmpyCd;
        Map<String, Object> c4_xrefMap;
        String c4_trgtAttrbTxt01, c4_trgtAttrbTxt02;
        boolean notExistC4Xref, existC5Xref;
        glblCmpyCd = dsImptOrdBean.glblCmpyCd.getValue();
        List<String> c6XrefList = NWXC412001.getUpgradeReturnsItemCodeList(glblCmpyCd, CPO_SRC_TP.SOM, INTERFACE_ID.NWAI4120.name());

        for (NWAI4120_05TMsg if05TMsg : nwai4120_05List) {

            somMercItemCd = if05TMsg.somMercItemCd.getValue();
            quoteLineTpTxt = if05TMsg.quoteLineTpTxt.getValue();

            c4_xrefMap = NWXC412001.getSoftCostItemInfo(glblCmpyCd, CPO_SRC_TP.SOM, INTERFACE_ID.NWAI4120.name(), somMercItemCd, quoteLineTpTxt);
            c4_trgtAttrbTxt01 = "";
            c4_trgtAttrbTxt02 = "";
            if (c4_xrefMap != null) {
                c4_trgtAttrbTxt01 = (String) c4_xrefMap.get("TRGT_ATTRB_TXT_01");
                c4_trgtAttrbTxt02 = (String) c4_xrefMap.get("TRGT_ATTRB_TXT_02");
            }

            notExistC4Xref = (c4_xrefMap == null || !(ZYPConstant.FLG_OFF_N.equals(c4_trgtAttrbTxt02) && c4_trgtAttrbTxt01.equals(if05TMsg.quoteLineTpTxt.getValue())));
            existC5Xref = NWXC412001.existBuyoutItemCode(glblCmpyCd, CPO_SRC_TP.SOM, INTERFACE_ID.NWAI4120.name(), somMercItemCd);

            // *****************************************************************
            // add_softcosts
            // *****************************************************************
            dtlBean = null;
            if (NWAB412001Constant.QUOTE_LINE_TP_TRADEIN.equals(quoteLineTpTxt)) {

                if (notExistC4Xref) {

                    if (!existC5Xref) {
                        // *****************************************************
                        // Process (8) (TRADE-IN (RMA))
                        // *****************************************************
                        targetNwai4120_06List = getNWAI4120_06TMsgByKey(if05TMsg.somQuoteId.getValue(), if05TMsg.somSoftCostId.getValue(), nwai4120_06List);
                        if (targetNwai4120_06List != null && targetNwai4120_06List.size() > 0) {
                            dsImptOrdConfigBean = new SomDsImptOrdConfigBean(CONFIG_TYPE.TRADE_IN_RMA, dsImptOrdBean, if05TMsg, null, targetNwai4120_06List.get(0));
                        } else {
                            dsImptOrdConfigBean = new SomDsImptOrdConfigBean(CONFIG_TYPE.TRADE_IN_RMA, dsImptOrdBean, if05TMsg, null, null);
                        }
                        dsImptOrdBean.addSomDsImptOrdConfigBean(dsImptOrdConfigBean);
                        //dsImptOrdConfigBean.nwai4120_06List.addAll(targetNwai4120_06List);

                        new SomDsImptOrdIstlInfoBean(ISTL_INFO_TYPE.TRADE_IN_RMA, dsImptOrdConfigBean);
                        new SomDsImptOrdDelyInfoBean(DELY_INFO_TYPE.TRADE_IN_RMA, dsImptOrdConfigBean);
                        // 2017/09/13 QC#21084 Del Start
//                        new SomDsImptOrdSiteSrvyBean(SITE_SRVY_TYPE.TRADE_IN_RMA, dsImptOrdConfigBean);
                        dsImptOrdConfigBean.addDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.TRADE_IN_RMA, false);
                        // 2017/09/13 QC#21084 Del End

                        // *****************************************************
                        // Process (9) (TRADE-IN (RMA))
                        // *****************************************************
                        //TODO
                        //get all configuration lines from main machine serial#
                        rtrnDtlBean = new SomDsImptOrdRtrnDtlBean(RTRN_DTL_TYPE.TRADE_IN_RMA, dsImptOrdConfigBean, if05TMsg, null);
                        new SomDsImptRtrnPrcCalcBean(RTRN_DTL_TYPE.TRADE_IN_RMA, rtrnDtlBean);

                        // *****************************************************
                        // Process (9) (TRADE-IN)
                        // *****************************************************
                        dtlBean = new SomDsImptOrdDtlBean(DTL_TYPE.TRADE_IN, lastShipConfigBean, if05TMsg);
                        dtlBean.nwai4120_06List.addAll(targetNwai4120_06List);
                        new SomDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.TRADE_IN, dtlBean);
                    }
                }

            } else if (NWAB412001Constant.QUOTE_LINE_TP_UPGRADEBUYOUTREBATE.equals(quoteLineTpTxt)) {

                if (notExistC4Xref) {

                    if (!existC5Xref) {

                        targetNwai4120_06List = getNWAI4120_06TMsgByKey(if05TMsg.somQuoteId.getValue(), if05TMsg.somSoftCostId.getValue(), nwai4120_06List);
                        List<NWAI4120_06TMsg> serialList = new ArrayList<NWAI4120_06TMsg>();
                        if (targetNwai4120_06List != null) {

                            for (NWAI4120_06TMsg nwai4120_06 : targetNwai4120_06List) {

                                if (ZYPCommonFunc.hasValue(nwai4120_06.somDescSerNum) && !"0".equals(nwai4120_06.somDescSerNum.getValue())) {

                                    serialList.add(nwai4120_06);
                                }
                            }
                        }
                        if (serialList.isEmpty()) {

                            // *****************************************************
                            // Process (10) (UPGRADEBUYOUTREBATE)
                            // single
                            // *****************************************************
                            dtlBean = new SomDsImptOrdDtlBean(DTL_TYPE.UPGRADEBUYOUTREBATE, lastShipConfigBean, if05TMsg);
                            dtlBean.nwai4120_06List.addAll(targetNwai4120_06List);
                            new SomDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.UPGRADEBUYOUTREBATE, dtlBean);

                        } else {

                            // *****************************************************
                            // Process (10) (UPGRADEBUYOUTREBATE)
                            // partial(per serial)
                            // *****************************************************
                            for (NWAI4120_06TMsg nwai4120_06 : serialList) {

                                if (!ZYPCommonFunc.hasValue(nwai4120_06.somSoftCostItemId)) {

                                    continue;
                                }

                                List<NWAI4120_06TMsg> targetSerialList = new ArrayList<NWAI4120_06TMsg>();
                                for (NWAI4120_06TMsg target : targetNwai4120_06List) {

                                    if (!ZYPCommonFunc.hasValue(target.somSoftCostItemId)) {

                                        continue;
                                    }

                                    if (target.somSoftCostItemId.getValue().compareTo(nwai4120_06.somSoftCostItemId.getValue()) != 0) {

                                        continue;
                                    }

                                    targetSerialList.add(target);
                                }
                                dtlBean = new SomDsImptOrdDtlBean(DTL_TYPE.UPGRADEBUYOUTREBATE, lastShipConfigBean, if05TMsg);
                                dtlBean.nwai4120_06List.addAll(targetSerialList);
                                new SomDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.UPGRADEBUYOUTREBATE, dtlBean);
                            }
                        }
                    }
                }

            } else {

                if (notExistC4Xref) {
                    if (!existC5Xref) {
                        // *****************************************************
                        // Process (11) (NOT UPGRADEBUYOUTREBATE AND
                        // TRADE-IN)
                        // *****************************************************
                        //QC#24092
                        if (if05TMsg != null && "OTHEREQUIPMENT".equals(if05TMsg.quoteLineTpTxt.getValue()) && ZYPCommonFunc.hasValue(if05TMsg.somMercItemCd)) {
                            Map<String, Object> mdseMap = null;
                            if (!this.getCacheItemMap().containsKey(S21StringUtil.subStringByLength(if05TMsg.somMercItemCd.getValue(), 0, 16))) {
                                mdseMap = NWXC412001.getMdse(glblCmpyCd, S21StringUtil.subStringByLength(if05TMsg.somMercItemCd.getValue(), 0, 16));
                                this.getCacheItemMap().put(S21StringUtil.subStringByLength(if05TMsg.somMercItemCd.getValue(), 0, 16), mdseMap);
                            } else {
                                mdseMap = (Map<String, Object>) this.getCacheItemMap().get(S21StringUtil.subStringByLength(if05TMsg.somMercItemCd.getValue(), 0, 16));
                            }
                            //Serialized item
                            if (mdseMap != null && "Y".equals((String)mdseMap.get("SHPG_SER_TAKE_FLG"))) {
                                for(int i = 0; i < if05TMsg.somOrdQty.getValueInt(); i++) {
                                    dtlBean = new SomDsImptOrdDtlBean(DTL_TYPE.NOT_UPGRADEBUYOUTREBATE_AND_TRADE_IN, lastShipConfigBean, if05TMsg, true);
                                    new SomDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.NOT_UPGRADEBUYOUTREBATE_AND_TRADE_IN, dtlBean);
                                }
                            } else {
                                dtlBean = new SomDsImptOrdDtlBean(DTL_TYPE.NOT_UPGRADEBUYOUTREBATE_AND_TRADE_IN, lastShipConfigBean, if05TMsg);
                                new SomDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.NOT_UPGRADEBUYOUTREBATE_AND_TRADE_IN, dtlBean);
                            }
                        } else {
                            dtlBean = new SomDsImptOrdDtlBean(DTL_TYPE.NOT_UPGRADEBUYOUTREBATE_AND_TRADE_IN, lastShipConfigBean, if05TMsg);
                            new SomDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.NOT_UPGRADEBUYOUTREBATE_AND_TRADE_IN, dtlBean);
                        }
                    }
                }
            }

            // *****************************************************************
            // create_buyout_lines
            // *****************************************************************
            if (notExistC4Xref) {

                if (existC5Xref) {

                    targetNwai4120_06List = getNWAI4120_06TMsgByKey(if05TMsg.somQuoteId.getValue(), if05TMsg.somSoftCostId.getValue(), nwai4120_06List);
                    List<NWAI4120_06TMsg> serialList = new ArrayList<NWAI4120_06TMsg>();
                    //QC#17125
                    //NWAI4120_06TMsg noSerNwai4120_06 = null;
                    //NWAI4120_06TMsg pickUpNwai4120_06 = null;
                    if (targetNwai4120_06List != null) {

                        for (NWAI4120_06TMsg nwai4120_06 : targetNwai4120_06List) {

                            if (ZYPCommonFunc.hasValue(nwai4120_06.somDescSerNum) && !"0".equals(nwai4120_06.somDescSerNum.getValue())) {

                                serialList.add(nwai4120_06);
                            //} else {
                            //    
                            //    noSerNwai4120_06 = nwai4120_06;
                            }
                            //if (ZYPCommonFunc.hasValue(nwai4120_06.pickUpCtacFirstNm)) {
                            //    pickUpNwai4120_06 = nwai4120_06;
                            //} else if (ZYPCommonFunc.hasValue(nwai4120_06.pickUpAddr_01)) {
                            //    pickUpNwai4120_06 = nwai4120_06;
                            //}
                            
                        }
                        //if (pickUpNwai4120_06 != null) {
                        //    noSerNwai4120_06 = pickUpNwai4120_06;
                        //}
                    }
                    if (serialList.isEmpty()) {

                        // *****************************************************
                        // Process (12) (BUYOUT) single
                        // *****************************************************
                        //QC#17125
                        //createBuyoutLine(dsImptOrdBean, targetNwai4120_06List, if05TMsg, null, noSerNwai4120_06);

                        // *****************************************************
                        // Process (12) (BUYOUT)
                        // single
                        // *****************************************************
                        dtlBean = new SomDsImptOrdDtlBean(DTL_TYPE.BUYOUT, lastShipConfigBean, if05TMsg);
                        dtlBean.nwai4120_06List.addAll(targetNwai4120_06List);
                        new SomDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.BUYOUT, dtlBean);

                    } else {

                        //// *****************************************************
                        //// Process (12) (BUYOUT) partial(per serial)
                        //// *****************************************************
                        //QC#17125
                        //for (NWAI4120_06TMsg nwai4120_06 : serialList) {
                        //
                        //    if (!ZYPCommonFunc.hasValue(nwai4120_06.somSoftCostItemId)) {
                        //
                        //        continue;
                        //    }
                        //
                        //    List<NWAI4120_06TMsg> targetSerialList = new ArrayList<NWAI4120_06TMsg>();
                        //    for (NWAI4120_06TMsg target : targetNwai4120_06List) {
                        //
                        //        if (!ZYPCommonFunc.hasValue(target.somSoftCostItemId)) {
                        //
                        //            continue;
                        //        }
                        //
                        //        if (target.somSoftCostItemId.getValue().compareTo(nwai4120_06.somSoftCostItemId.getValue()) != 0) {
                        //
                        //            continue;
                        //        }
                        //
                        //        targetSerialList.add(target);
                        //    }
                        //    createBuyoutLine(dsImptOrdBean, targetSerialList, if05TMsg, nwai4120_06, noSerNwai4120_06);
                        //}

                        // *****************************************************
                        // Process (12) (BUYOUT)
                        // partial(per serial)
                        // *****************************************************
                        for (NWAI4120_06TMsg nwai4120_06 : serialList) {

                            if (!ZYPCommonFunc.hasValue(nwai4120_06.somSoftCostItemId)) {

                                continue;
                            }

                            List<NWAI4120_06TMsg> targetSerialList = new ArrayList<NWAI4120_06TMsg>();
                            for (NWAI4120_06TMsg target : targetNwai4120_06List) {

                                if (!ZYPCommonFunc.hasValue(target.somSoftCostItemId)) {

                                    continue;
                                }

                                if (target.somSoftCostItemId.getValue().compareTo(nwai4120_06.somSoftCostItemId.getValue()) != 0) {

                                    continue;
                                }

                                targetSerialList.add(target);
                            }
                            dtlBean = new SomDsImptOrdDtlBean(DTL_TYPE.BUYOUT, lastShipConfigBean, if05TMsg);
                            dtlBean.nwai4120_06List.addAll(targetSerialList);
                            new SomDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.BUYOUT, dtlBean);
                        }
                    }
                }
            }

            // *****************************************************************
            // create_upgrade_returns
            // *****************************************************************
            if (c6XrefList.contains(somMercItemCd)) {

                targetNwai4120_06List = getNWAI4120_06TMsgByKey(if05TMsg.somQuoteId.getValue(), if05TMsg.somSoftCostId.getValue(), nwai4120_06List);
                List<NWAI4120_06TMsg> serialList = new ArrayList<NWAI4120_06TMsg>();
                NWAI4120_06TMsg noSerNwai4120_06 = null;
                NWAI4120_06TMsg pickUpNwai4120_06 = null;
                if (targetNwai4120_06List != null) {

                    for (NWAI4120_06TMsg nwai4120_06 : targetNwai4120_06List) {

                        if (ZYPCommonFunc.hasValue(nwai4120_06.somDescSerNum) && !"0".equals(nwai4120_06.somDescSerNum.getValue())) {

                            serialList.add(nwai4120_06);
                        } else {
                            noSerNwai4120_06 = nwai4120_06;
                        }
                        
                        if (ZYPCommonFunc.hasValue(nwai4120_06.pickUpCtacFirstNm)) {
                        	pickUpNwai4120_06 = nwai4120_06;
                        }
                        
                    }
                    if (pickUpNwai4120_06 != null) {
                        noSerNwai4120_06 = pickUpNwai4120_06;
                    }
                }
                if (serialList.isEmpty()) {

                    // *************************************************************
                    // Process (13) (UPGRADE RETURN (RMA)) single
                    // *************************************************************
                    createUpgradeReturnRma(dsImptOrdBean, targetNwai4120_06List, if05TMsg, null, noSerNwai4120_06);

                } else {

                    // *************************************************************
                    // Process (13) (UPGRADE RETURN (RMA)) partial(per
                    // serial)
                    // *************************************************************
                    for (NWAI4120_06TMsg nwai4120_06 : serialList) {

                        if (!ZYPCommonFunc.hasValue(nwai4120_06.somSoftCostItemId)) {

                            continue;
                        }

                        List<NWAI4120_06TMsg> targetSerialList = new ArrayList<NWAI4120_06TMsg>();
                        for (NWAI4120_06TMsg target : targetNwai4120_06List) {

                            if (!ZYPCommonFunc.hasValue(target.somSoftCostItemId)) {

                                continue;
                            }

                            if (target.somSoftCostItemId.getValue().compareTo(nwai4120_06.somSoftCostItemId.getValue()) != 0) {

                                continue;
                            }

                            targetSerialList.add(target);
                        }
                        createUpgradeReturnRma(dsImptOrdBean, targetSerialList, if05TMsg, nwai4120_06, noSerNwai4120_06);
                    }
                }
            }
        }
    }
//QC#17125
//    private static void createBuyoutLine(SomDsImptOrdBean dsImptOrdBean, List<NWAI4120_06TMsg> targetNwai4120_06List, NWAI4120_05TMsg if05TMsg, NWAI4120_06TMsg serIf06TMsg, NWAI4120_06TMsg noSerIf06TMsg) {
//
//        // *************************************************************
//        // Process (13) (UPGRADE RETURN (RMA))
//        // *************************************************************
//        SomDsImptOrdConfigBean dsImptOrdConfigBean = new SomDsImptOrdConfigBean(CONFIG_TYPE.BUYOUT_RMA, dsImptOrdBean, if05TMsg, serIf06TMsg, noSerIf06TMsg);
//        dsImptOrdBean.addSomDsImptOrdConfigBean(dsImptOrdConfigBean);
//        //dsImptOrdConfigBean.nwai4120_06List.addAll(targetNwai4120_06List);
//
//        new SomDsImptOrdIstlInfoBean(ISTL_INFO_TYPE.BUYOUT_RMA, dsImptOrdConfigBean);
//        new SomDsImptOrdDelyInfoBean(DELY_INFO_TYPE.BUYOUT_RMA, dsImptOrdConfigBean);
//        new SomDsImptOrdSiteSrvyBean(SITE_SRVY_TYPE.BUYOUT_RMA, dsImptOrdConfigBean);
//        dsImptOrdConfigBean.addDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.BUYOUT_RMA);
//
//        // *********************************************************
//        // Process (13) (UPGRADE RETURN (RMA))
//        // *********************************************************
//        SomDsImptOrdRtrnDtlBean rtrnDtlBean = new SomDsImptOrdRtrnDtlBean(RTRN_DTL_TYPE.BUYOUT_RMA, dsImptOrdConfigBean, if05TMsg, serIf06TMsg);
//        new SomDsImptRtrnPrcCalcBean(RTRN_DTL_TYPE.BUYOUT_RMA, rtrnDtlBean);
//    }

    private static void createUpgradeReturnRma(SomDsImptOrdBean dsImptOrdBean, List<NWAI4120_06TMsg> targetNwai4120_06List, NWAI4120_05TMsg if05TMsg, NWAI4120_06TMsg serIf06TMsg, NWAI4120_06TMsg noSerIf06TMsg) {

        // *************************************************************
        // Process (13) (UPGRADE RETURN (RMA))
        // *************************************************************
        SomDsImptOrdConfigBean dsImptOrdConfigBean = new SomDsImptOrdConfigBean(CONFIG_TYPE.UPGRADE_RETURN_RMA, dsImptOrdBean, if05TMsg, serIf06TMsg, noSerIf06TMsg);
        dsImptOrdBean.addSomDsImptOrdConfigBean(dsImptOrdConfigBean);
        //dsImptOrdConfigBean.nwai4120_06List.addAll(targetNwai4120_06List);

        new SomDsImptOrdIstlInfoBean(ISTL_INFO_TYPE.UPGRADE_RETURN_RMA, dsImptOrdConfigBean);
        new SomDsImptOrdDelyInfoBean(DELY_INFO_TYPE.UPGRADE_RETURN_RMA, dsImptOrdConfigBean);
        // 2017/09/13 QC#21084 Del Start
//        new SomDsImptOrdSiteSrvyBean(SITE_SRVY_TYPE.UPGRADE_RETURN_RMA, dsImptOrdConfigBean);
        // 2017/09/13 QC#21084 Del End
        dsImptOrdConfigBean.addDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.UPGRADE_RETURN_RMA, false);

        // *********************************************************
        // Process (13) (UPGRADE RETURN (RMA))
        // *********************************************************
        //TODO
        //get all configuration lines from main machine serial#
        SomDsImptOrdRtrnDtlBean rtrnDtlBean = new SomDsImptOrdRtrnDtlBean(RTRN_DTL_TYPE.UPGRADE_RETURN_RMA, dsImptOrdConfigBean, if05TMsg, serIf06TMsg);
        new SomDsImptRtrnPrcCalcBean(RTRN_DTL_TYPE.UPGRADE_RETURN_RMA, rtrnDtlBean);
    }

    public void createImptOrdDtlForHdrRebate(SomDsImptOrdBean dsImptOrdBean) {
        if (ZYPCommonFunc.hasValue(dsImptOrdBean.nwai4120_01.rebMercCd)) {
            // *****************************************************************
            // Process (14) (HEADER REBATE)
            // *****************************************************************
            SomDsImptOrdDtlBean dtlBean = new SomDsImptOrdDtlBean(DTL_TYPE.HEADER_REBATE, dsImptOrdBean.lastShipConfigBean);
            new SomDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.HEADER_REBATE, dtlBean);
        }
    }

    public void createShellForSrvCopiers(SomDsImptOrdBean dsImptOrdBean, 
            List<NWAI4120_16TMsg> nwai4120_16List, 
            List<NWAI4120_17TMsg> nwai4120_17List, 
            List<NWAI4120_18TMsg> nwai4120_18List, 
            List<NWAI4120_20TMsg> nwai4120_20List,
            List<NWAI4120_21TMsg> nwai4120_21List) {

        //List<SomDsImptSvcPrcBean> dsImptSvcPrcBeanList = new ArrayList<SomDsImptSvcPrcBean>();
        
        //QC#18210
        //Fleet Service Detail
        SomDsImptSvcDtlBean somDsImptSvcDtlBeanFlt = null;
        //Fleet Service Price
        SomDsImptSvcPrcBean somDsImptSvcPrcBeanFlt = null;
        //Fleet Billing Meter List
        List<String> fltMtrList = new ArrayList<String>();
        
        //Aggregate Service Detail
        SomDsImptSvcDtlBean somDsImptSvcDtlBeanAgg = null;
        BigDecimal reqBaseTotAmtAgg = BigDecimal.ZERO;
        List<String> regBundleList = new ArrayList<String>(); //QC#20393
        
        //Service Header Loop
        boolean serviceFlg = false; //QC#24023
        for (NWAI4120_16TMsg service : nwai4120_16List) {

            //Make Not Decline Maintenance List
            List<NWAI4120_18TMsg> ndm_nwai4120_18list = new ArrayList<NWAI4120_18TMsg>();
            for (NWAI4120_18TMsg serviceLine : nwai4120_18List) {
                if (!S21StringUtil.isEquals(serviceLine.dclnMaintIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
                    //Make Not Decline Maintenance List
                    ndm_nwai4120_18list.add(serviceLine);
                }
            }
            serviceFlg = true;
            //Configuration Loop
            for (SomDsImptOrdConfigBean config : dsImptOrdBean.dsImptOrdConfigBeanList) {
            
                if (config.nwai4120_12 == null || !ZYPCommonFunc.hasValue(config.nwai4120_12.somConfigId)) {
                    //skip configId is null
                    continue;
                }
                
                BigDecimal prevServiceLineId = null;
                //Service Copier Loop
                
                boolean copierServiceFlg = false; //QC#24023
                for (NWAI4120_18TMsg serviceLine : nwai4120_18List) {
                    
                    if (service.somSrvId.getValue().compareTo(serviceLine.somSrvId.getValue()) != 0) {
                        //skip different shell
                        continue;
                    }
                    
                    if (config.nwai4120_12.somConfigId.getValue().compareTo(serviceLine.somSrvLineId.getValue()) != 0) {
                        //skip different configId
                        continue;
                    }
                    copierServiceFlg = true; //QC#24023
                    BigDecimal serviceLineId = serviceLine.somSrvLineId.getValue();
                    if (prevServiceLineId != null && serviceLineId.compareTo(prevServiceLineId) == 0) {
                        // skip same service line ID
                        continue;
                    } else {
                        prevServiceLineId = serviceLineId;
                    }
                    
                    //Decline Maintenance Flag
                    if (!S21StringUtil.isEquals(serviceLine.dclnMaintIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
                        config.setCopierDeclineMaintenanceFlg(false);
                        //ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_OFF_0);
                    } else {
                        config.setCopierDeclineMaintenanceFlg(true);
                        //ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_ON_1);
                    }
                    
                }//End of Service Copier Loop
                //QC#24023
                if (!copierServiceFlg) {
                    config.setCopierDeclineMaintenanceFlg(true);
                }
            }//End of Configuration Loop
            
            //QC#18210
            if (ndm_nwai4120_18list.size() > 0) {
                //QC#20393 start
                Map<String, SomDsImptSvcDtlBean> somDsImptSvcDtlBeanRegMap = new HashMap<String, SomDsImptSvcDtlBean>();
                Map<String, SomDsImptSvcPrcBean> somDsImptSvcPrcBeanRegMap = new HashMap<String, SomDsImptSvcPrcBean>();
                Map<String, BigDecimal> reqBaseAmtRegMap = new HashMap<String, BigDecimal>();
                //QC#20393 end
                //Configuration Loop
                for (SomDsImptOrdConfigBean config : dsImptOrdBean.dsImptOrdConfigBeanList) {
                    if (config.nwai4120_12 == null || !ZYPCommonFunc.hasValue(config.nwai4120_12.somConfigId)) {
                        //skip configId is null
                        continue;
                    }
                    //Service Copier Loop
                    BigDecimal prevServiceLineIdFlt = null;
                    BigDecimal prevServiceLineIdAgg = null;
                    BigDecimal prevServiceLineIdReg = null;
                    SomDsImptSvcPrcBean preSomDsImptSvcPrcBeanAgg = null;
                    //QC#20393 start
                    //SomDsImptSvcDtlBean preSomDsImptSvcDtlBeanReg = null;
                    //SomDsImptSvcPrcBean preSomDsImptSvcPrcBeanReg = null;
                    BigDecimal reqBaseAmtAgg = BigDecimal.ZERO;
                    //BigDecimal reqBaseAmtReg = BigDecimal.ZERO;
                    //QC#20393 end
                    //Loop Copiers
                    for (NWAI4120_18TMsg nmai4120_18TMsg : ndm_nwai4120_18list) {
                        if (service.somSrvId.getValue().compareTo(nmai4120_18TMsg.somSrvId.getValue()) != 0) {
                            //skip different shell
                            continue;
                        }
                        
                        if (config.nwai4120_12.somConfigId.getValue().compareTo(nmai4120_18TMsg.somSrvLineId.getValue()) != 0) {
                            //skip different configId
                            continue;
                        }
                    	//Fleet
                        if ((SOM_CONTR_IND.N.toString().equals(service.contrIndSomTxt.getValue())
                                || SOM_CONTR_IND.F.toString().equals(service.contrIndSomTxt.getValue()))
                                && S21StringUtil.isEquals(nmai4120_18TMsg.isFleetIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
                            if (somDsImptSvcDtlBeanFlt == null) {
                                somDsImptSvcDtlBeanFlt = new SomDsImptSvcDtlBean(SHELL_TYPE.COPIER, dsImptOrdBean, service, nmai4120_18TMsg, CONTRACT_TYPE.FLEET);
                            }
                            if (somDsImptSvcPrcBeanFlt == null) {
                                somDsImptSvcPrcBeanFlt = new SomDsImptSvcPrcBean(SHELL_TYPE.COPIER, dsImptOrdBean, nmai4120_18TMsg, service);
                                somDsImptSvcPrcBeanFlt.dsImptSvcDtlBean = somDsImptSvcDtlBeanFlt;
                                somDsImptSvcDtlBeanFlt.dsImptSvcPrcBeanList.add(somDsImptSvcPrcBeanFlt);
                                //dsImptSvcPrcBeanList.add(somDsImptSvcPrcBeanFlt);
                            }
                            
                            
                            // *****************************************************************
                            // service price usage
                            // NWAI4120_18.SOM_SRV_Line ID, meter package
                            // *****************************************************************
                            BigDecimal mdlId = null;
                            if (!this.getCacheModelMap().containsKey(nmai4120_18TMsg.somMdlDescTxt.getValue())) {
                                mdlId = NWXC412001.getMdlIdByName(dsImptOrdBean.glblCmpyCd.getValue(), nmai4120_18TMsg.somMdlDescTxt.getValue());
                                this.getCacheModelMap().put(nmai4120_18TMsg.somMdlDescTxt.getValue(), mdlId);
                            } else {
                                mdlId = this.getCacheModelMap().get(nmai4120_18TMsg.somMdlDescTxt.getValue());
                            }
                            BigDecimal prcMtrPkgPk = null;
                            if (!this.getCachePrcMtrPkgPkMap().containsKey(nmai4120_18TMsg.somPkgNm.getValue())) {
                                prcMtrPkgPk = NWXC412001.getPrcMtrPkgPkFromName(dsImptOrdBean.glblCmpyCd.getValue(), nmai4120_18TMsg.somPkgNm.getValue(), dsImptOrdBean.slsDt);
                                this.getCachePrcMtrPkgPkMap().put(nmai4120_18TMsg.somPkgNm.getValue(), prcMtrPkgPk);
                            } else {
                                prcMtrPkgPk = this.getCachePrcMtrPkgPkMap().get(nmai4120_18TMsg.somPkgNm.getValue());
                            }
                            //Meter Label Name = SOM Service Counter Name
                            List<Map<String, Object>> prcMtrPkgMtrStruList = NWXC412001.getPrcMtrPkgMtrStru(dsImptOrdBean.glblCmpyCd.getValue(), 
                                    prcMtrPkgPk, mdlId, nmai4120_18TMsg.somSrvCntrNm.getValue(), dsImptOrdBean.slsDt);
                            //Price Meter Package Structure List
                            if (prcMtrPkgMtrStruList != null && !prcMtrPkgMtrStruList.isEmpty()) {
                                //Meter Loop
                                for (Map<String, Object> prcMtrPkgMtrStru : prcMtrPkgMtrStruList) {
                                    if (!fltMtrList.contains((String) prcMtrPkgMtrStru.get("BLLG_MTR_LB_CD"))) {
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, somDsImptSvcPrcBeanFlt, nmai4120_18TMsg, prcMtrPkgMtrStru, service);
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, somDsImptSvcPrcBeanFlt, nmai4120_18TMsg, prcMtrPkgMtrStru, service);
                                        fltMtrList.add((String) prcMtrPkgMtrStru.get("BLLG_MTR_LB_CD"));
                                    }
                                }//End of Meter Loop
                            }//End of Price Meter Package Structure List
                            else {
                                if (ZYPCommonFunc.hasValue(nmai4120_18TMsg.somSrvCntrNm) && !fltMtrList.contains(nmai4120_18TMsg.somSrvCntrNm.getValue())) {
                                    new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, somDsImptSvcPrcBeanFlt, nmai4120_18TMsg, null, service);
                                    new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, somDsImptSvcPrcBeanFlt, nmai4120_18TMsg, null, service);
                                    fltMtrList.add(nmai4120_18TMsg.somSrvCntrNm.getValue());
                                }
                            }

                            if (prevServiceLineIdFlt != null && nmai4120_18TMsg.somSrvLineId.getValue().compareTo(prevServiceLineIdFlt) == 0) {
                                // skip same service line ID
                                continue;
                            } else {
                            	prevServiceLineIdFlt = nmai4120_18TMsg.somSrvLineId.getValue();
                            }
                            // create service configuration reference
                            new SomDsImptSvcConfigRefBean(SHELL_TYPE.COPIER, somDsImptSvcDtlBeanFlt, service, nmai4120_18TMsg, config, somDsImptSvcPrcBeanFlt);
                            
                            // *****************************************************************
                            // additional base
                            // NWAI4120_17.SOM_SRV_ID
                            // *****************************************************************
                            for (NWAI4120_17TMsg nwai4120_17TMsg : nwai4120_17List) {

                                if (service.somSrvId.getValue().compareTo(nwai4120_17TMsg.somSrvId.getValue()) != 0) {
                                    //skip different shell
                                    continue;
                                }
                                //Not Decline Maintenance List
                                if (S21StringUtil.isEquals(nwai4120_17TMsg.dclnMaintIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
                                    //skip decline maintenance
                                    continue;
                                }
                                BigDecimal baseConfigId = nwai4120_17TMsg.somConfigId.getValue();
                                if (config.nwai4120_12 == null 
                                        || !ZYPCommonFunc.hasValue(config.nwai4120_12.somConfigId)
                                        || config.nwai4120_12.somConfigId.getValue().compareTo(baseConfigId) != 0) {
                                    continue;
                                }
                                new SomDsImptSvcAddlBaseBean(SHELL_TYPE.COPIER, somDsImptSvcDtlBeanFlt, nwai4120_17TMsg, config);
                            }//End of Additional Base Only Loop
                            
                            // *****************************************************************
                            // additional charge
                            // NWAI4120_20.SOM_SRV_ID
                            // *****************************************************************
                            //Additional Charge Loop
                            for (NWAI4120_20TMsg nwai4120_20TMsg : nwai4120_20List) {

                                BigDecimal chargeServiceId = nwai4120_20TMsg.somSrvId.getValue();
                                if (service.somSrvId.getValue().compareTo(chargeServiceId) != 0) {
                                    //skip different shell
                                    continue;
                                }
                                //Not Decline Maintenance List
                                if (S21StringUtil.isEquals(nwai4120_20TMsg.dclnMaintIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
                                    //skip decline maintenance
                                    continue;
                                }
                                BigDecimal chargeConfigId = nwai4120_20TMsg.somConfigId.getValue();
                                if (config.nwai4120_12 == null 
                                        || !ZYPCommonFunc.hasValue(config.nwai4120_12.somConfigId)
                                        || config.nwai4120_12.somConfigId.getValue().compareTo(chargeConfigId) != 0) {
                                    continue;
                                }
                                new SomDsImptSvcAddlChrgBean(SHELL_TYPE.COPIER, somDsImptSvcDtlBeanFlt, nwai4120_20TMsg, config);
                            }//End of Additional Charge Loop
                            
                            // *****************************************************************
                            // rental
                            // NWAI4120_21.SOM_SRV_ID
                            // *****************************************************************
                            //Rental Loop
                            for (NWAI4120_21TMsg nwai4120_21TMsg : nwai4120_21List) {
                                if (nmai4120_18TMsg.somSrvLineId.getValue().compareTo(nwai4120_21TMsg.somConfigId.getValue()) != 0) {
                                    // skip
                                    continue;
                                }
                                BigDecimal chargeConfigId = nwai4120_21TMsg.somConfigId.getValue();
                                if (config.nwai4120_12 == null 
                                        || !ZYPCommonFunc.hasValue(config.nwai4120_12.somConfigId)
                                        || config.nwai4120_12.somConfigId.getValue().compareTo(chargeConfigId) != 0) {
                                    continue;
                                }
                                //exclude main machine
                                //QC#21442
                                //BigDecimal somSortOrdNum = somDsImptSvcDtlBeanAgg.getSomSortOrdNum();
                                //BigDecimal mainMachineSomSortOrdNum = nwai4120_21TMsg.somConfigLineId.getValue();
                                //if (somSortOrdNum.compareTo(mainMachineSomSortOrdNum) != 0) {
                                //    new SomDsImptSvcAddlBaseBean(SHELL_TYPE.COPIER, somDsImptSvcDtlBeanAgg, nwai4120_21TMsg, RENTAL_TYPE.SERVICE);
                                //}
                                
                                new SomDsImptSvcAddlBaseBean(SHELL_TYPE.COPIER, somDsImptSvcDtlBeanFlt, nwai4120_21TMsg, RENTAL_TYPE.EQUIPMENT, config);
                            }//End of Rental Loop
                            
                            
                            
                        //Aggregate
                        } else if (SOM_CONTR_IND.A.toString().equals(service.contrIndSomTxt.getValue())
                                && SOM_CONTR_IND.A.toString().equals(nmai4120_18TMsg.contrOptTxt.getValue())) {
                        //} else if ((SOM_CONTR_IND.A.toString().equals(service.contrIndSomTxt.getValue())
                        //        || SOM_CONTR_IND.P.toString().equals(service.contrIndSomTxt.getValue()))
                        //        && (SOM_CONTR_IND.A.toString().equals(nmai4120_18TMsg.contrOptTxt.getValue())
                        //            || SOM_CONTR_IND.P.toString().equals(nmai4120_18TMsg.contrOptTxt.getValue()))) {
                            if (somDsImptSvcDtlBeanAgg == null) {
                                somDsImptSvcDtlBeanAgg = new SomDsImptSvcDtlBean(SHELL_TYPE.COPIER, dsImptOrdBean, service, nmai4120_18TMsg, CONTRACT_TYPE.AGGREGATE);
                            }
                            // *****************************************************************
                            // service price usage
                            // NWAI4120_18.SOM_SRV_Line ID, meter package
                            // *****************************************************************
                            BigDecimal mdlId = null;
                            if (!this.getCacheModelMap().containsKey(nmai4120_18TMsg.somMdlDescTxt.getValue())) {
                                mdlId = NWXC412001.getMdlIdByName(dsImptOrdBean.glblCmpyCd.getValue(), nmai4120_18TMsg.somMdlDescTxt.getValue());
                                this.getCacheModelMap().put(nmai4120_18TMsg.somMdlDescTxt.getValue(), mdlId);
                            } else {
                                mdlId = this.getCacheModelMap().get(nmai4120_18TMsg.somMdlDescTxt.getValue());
                            }
                            BigDecimal prcMtrPkgPk = null;
                            if (!this.getCachePrcMtrPkgPkMap().containsKey(nmai4120_18TMsg.somPkgNm.getValue())) {
                                prcMtrPkgPk = NWXC412001.getPrcMtrPkgPkFromName(dsImptOrdBean.glblCmpyCd.getValue(), nmai4120_18TMsg.somPkgNm.getValue(), dsImptOrdBean.slsDt);
                                this.getCachePrcMtrPkgPkMap().put(nmai4120_18TMsg.somPkgNm.getValue(), prcMtrPkgPk);
                            } else {
                                prcMtrPkgPk = this.getCachePrcMtrPkgPkMap().get(nmai4120_18TMsg.somPkgNm.getValue());
                            }
                            //Billing Meter Name = SOM Service Counter Name
                            List<Map<String, Object>> prcMtrPkgMtrStruList = NWXC412001.getPrcMtrPkgMtrStru(dsImptOrdBean.glblCmpyCd.getValue(), 
                                    prcMtrPkgPk, mdlId, nmai4120_18TMsg.somSrvCntrNm.getValue(), dsImptOrdBean.slsDt);
                            BigDecimal serviceLineId = nmai4120_18TMsg.somSrvLineId.getValue();
                            if (prevServiceLineIdAgg != null && serviceLineId.compareTo(prevServiceLineIdAgg) == 0) {

                                if (ZYPCommonFunc.hasValue(nmai4120_18TMsg.reqBaseAmt)) {
                                    reqBaseAmtAgg = reqBaseAmtAgg.add(nmai4120_18TMsg.reqBaseAmt.getValue());
                                    reqBaseTotAmtAgg = reqBaseTotAmtAgg.add(nmai4120_18TMsg.reqBaseAmt.getValue());
                                }
                                somDsImptSvcDtlBeanAgg.setReqBaseAmt(reqBaseTotAmtAgg);
                                preSomDsImptSvcPrcBeanAgg.setReqBaseAmt(reqBaseAmtAgg);
                                //Price Meter Package List
                                if (prcMtrPkgMtrStruList != null && !prcMtrPkgMtrStruList.isEmpty()) {
                                    for (Map<String, Object> prcMtrPkgMtrStru : prcMtrPkgMtrStruList) {
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, preSomDsImptSvcPrcBeanAgg, nmai4120_18TMsg, prcMtrPkgMtrStru, service);
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, preSomDsImptSvcPrcBeanAgg, nmai4120_18TMsg, prcMtrPkgMtrStru, service);
                                    }
                                }//End of Price Meter Package List
                                else {
                                    if (ZYPCommonFunc.hasValue(nmai4120_18TMsg.somSrvCntrNm)) {
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, preSomDsImptSvcPrcBeanAgg, nmai4120_18TMsg, null, service);
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, preSomDsImptSvcPrcBeanAgg, nmai4120_18TMsg, null, service);
                                    }
                                }
                                
                                // skip same service line ID
                                continue;
                            } else {
                                reqBaseAmtAgg = BigDecimal.ZERO;
                                if (ZYPCommonFunc.hasValue(nmai4120_18TMsg.reqBaseAmt)) {
                                    reqBaseAmtAgg = reqBaseAmtAgg.add(nmai4120_18TMsg.reqBaseAmt.getValue());
                                    reqBaseTotAmtAgg = reqBaseTotAmtAgg.add(nmai4120_18TMsg.reqBaseAmt.getValue());
                                }
                                // create service detail
                                //SomDsImptSvcDtlBean somDsImptSvcDtlBean = new SomDsImptSvcDtlBean(SHELL_TYPE.COPIER, dsImptOrdBean, service, nmai4120_18TMsg, CONTRACT_TYPE.AGGREGATE);
                                //somDsImptSvcDtlBean.setMixCopierNonCopierFlg(false);
                                somDsImptSvcDtlBeanAgg.setReqBaseAmt(reqBaseTotAmtAgg);

                                // create service price
                                SomDsImptSvcPrcBean somDsImptSvcPrcBeanAgg = new SomDsImptSvcPrcBean(SHELL_TYPE.COPIER, dsImptOrdBean, nmai4120_18TMsg, service);
                                somDsImptSvcPrcBeanAgg.setReqBaseAmt(reqBaseAmtAgg);
                                somDsImptSvcPrcBeanAgg.dsImptSvcDtlBean = somDsImptSvcDtlBeanAgg;
                                somDsImptSvcDtlBeanAgg.dsImptSvcPrcBeanList.add(somDsImptSvcPrcBeanAgg);
                                //dsImptSvcPrcBeanList.add(somDsImptSvcPrcBeanAgg);
                                //Price Meter Package List
                                if (prcMtrPkgMtrStruList != null && !prcMtrPkgMtrStruList.isEmpty()) {
                                    for (Map<String, Object> prcMtrPkgMtrStru : prcMtrPkgMtrStruList) {
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, somDsImptSvcPrcBeanAgg, nmai4120_18TMsg, prcMtrPkgMtrStru, service);
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, somDsImptSvcPrcBeanAgg, nmai4120_18TMsg, prcMtrPkgMtrStru, service);
                                    }
                                }//End of Price Meter Package List
                                else {
                                    if (ZYPCommonFunc.hasValue(nmai4120_18TMsg.somSrvCntrNm)) {
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, somDsImptSvcPrcBeanAgg, nmai4120_18TMsg, null, service);
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, somDsImptSvcPrcBeanAgg, nmai4120_18TMsg, null, service);
                                    }
                                }
                                
                                // create service configuration reference
                                new SomDsImptSvcConfigRefBean(SHELL_TYPE.COPIER, somDsImptSvcDtlBeanAgg, service, nmai4120_18TMsg, config, somDsImptSvcPrcBeanAgg);
                                
                                // *****************************************************************
                                // additional base
                                // NWAI4120_17.SOM_SRV_ID
                                // *****************************************************************
                                for (NWAI4120_17TMsg nwai4120_17TMsg : nwai4120_17List) {

                                    if (service.somSrvId.getValue().compareTo(nwai4120_17TMsg.somSrvId.getValue()) != 0) {
                                        //skip different shell
                                        continue;
                                    }
                                    //Not Decline Maintenance List
                                    if (S21StringUtil.isEquals(nwai4120_17TMsg.dclnMaintIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
                                        //skip decline maintenance
                                        continue;
                                    }
                                    BigDecimal baseConfigId = nwai4120_17TMsg.somConfigId.getValue();
                                    if (config.nwai4120_12 == null 
                                            || !ZYPCommonFunc.hasValue(config.nwai4120_12.somConfigId)
                                            || config.nwai4120_12.somConfigId.getValue().compareTo(baseConfigId) != 0) {
                                        continue;
                                    }
                                    new SomDsImptSvcAddlBaseBean(SHELL_TYPE.COPIER, somDsImptSvcDtlBeanAgg, nwai4120_17TMsg, config);
                                }//End of Additional Base Only Loop
                                
                                // *****************************************************************
                                // additional charge
                                // NWAI4120_20.SOM_SRV_ID
                                // *****************************************************************
                                //Additional Charge Loop
                                for (NWAI4120_20TMsg nwai4120_20TMsg : nwai4120_20List) {

                                    BigDecimal chargeServiceId = nwai4120_20TMsg.somSrvId.getValue();
                                    if (service.somSrvId.getValue().compareTo(chargeServiceId) != 0) {
                                        //skip different shell
                                        continue;
                                    }
                                    //Not Decline Maintenance List
                                    if (S21StringUtil.isEquals(nwai4120_20TMsg.dclnMaintIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
                                        //skip decline maintenance
                                        continue;
                                    }
                                    BigDecimal chargeConfigId = nwai4120_20TMsg.somConfigId.getValue();
                                    if (config.nwai4120_12 == null 
                                            || !ZYPCommonFunc.hasValue(config.nwai4120_12.somConfigId)
                                            || config.nwai4120_12.somConfigId.getValue().compareTo(chargeConfigId) != 0) {
                                        continue;
                                    }
                                    new SomDsImptSvcAddlChrgBean(SHELL_TYPE.COPIER, somDsImptSvcDtlBeanAgg, nwai4120_20TMsg, config);
                                }//End of Additional Charge Loop
                                
                                // *****************************************************************
                                // rental
                                // NWAI4120_21.SOM_SRV_ID
                                // *****************************************************************
                                //Rental Loop
                                for (NWAI4120_21TMsg nwai4120_21TMsg : nwai4120_21List) {
                                    if (serviceLineId.compareTo(nwai4120_21TMsg.somConfigId.getValue()) != 0) {
                                        // skip
                                        continue;
                                    }
                                    BigDecimal chargeConfigId = nwai4120_21TMsg.somConfigId.getValue();
                                    if (config.nwai4120_12 == null 
                                            || !ZYPCommonFunc.hasValue(config.nwai4120_12.somConfigId)
                                            || config.nwai4120_12.somConfigId.getValue().compareTo(chargeConfigId) != 0) {
                                        continue;
                                    }
                                    //exclude main machine
                                    //QC#21442
                                    //BigDecimal somConfigId = config.nwai4120_12.somConfigId.getValue();
                                    //BigDecimal somSortOrdNum = somDsImptSvcDtlBeanAgg.getSomSortOrdNum();
                                    //BigDecimal mainMachineConfigId = nwai4120_21TMsg.somConfigId.getValue();
                                    //BigDecimal mainMachineSomSortOrdNum = nwai4120_21TMsg.somConfigLineId.getValue();
                                    //if (somConfigId.compareTo(mainMachineConfigId) != 0
                                    //        || somSortOrdNum.compareTo(mainMachineSomSortOrdNum) != 0) {
                                    //    new SomDsImptSvcAddlBaseBean(SHELL_TYPE.COPIER, somDsImptSvcDtlBeanAgg, nwai4120_21TMsg, RENTAL_TYPE.SERVICE);
                                    //}
                                    
                                    new SomDsImptSvcAddlBaseBean(SHELL_TYPE.COPIER, somDsImptSvcDtlBeanAgg, nwai4120_21TMsg, RENTAL_TYPE.EQUIPMENT, config);
                                }//End of Rental Loop
                                
                                prevServiceLineIdAgg = serviceLineId;
                                preSomDsImptSvcPrcBeanAgg = somDsImptSvcPrcBeanAgg;
                                //preSomDsImptSvcDtlBeanAgg = somDsImptSvcDtlBeanAgg;
                            }
                        	
                        //Regular
                        } else {
                        	
                        	//somDsImptSvcDtlBeanReg = new SomDsImptSvcDtlBean(SHELL_TYPE.COPIER, dsImptOrdBean, service, nmai4120_18TMsg, CONTRACT_TYPE.REGULAR);
                        	
                            // *****************************************************************
                            // service price usage
                            // NWAI4120_18.SOM_SRV_Line ID, meter package
                            // *****************************************************************
                            BigDecimal mdlId = null;
                            if (!this.getCacheModelMap().containsKey(nmai4120_18TMsg.somMdlDescTxt.getValue())) {
                                mdlId = NWXC412001.getMdlIdByName(dsImptOrdBean.glblCmpyCd.getValue(), nmai4120_18TMsg.somMdlDescTxt.getValue());
                                this.getCacheModelMap().put(nmai4120_18TMsg.somMdlDescTxt.getValue(), mdlId);
                            } else {
                                mdlId = this.getCacheModelMap().get(nmai4120_18TMsg.somMdlDescTxt.getValue());
                            }
                            BigDecimal prcMtrPkgPk = null;
                            if (!this.getCachePrcMtrPkgPkMap().containsKey(nmai4120_18TMsg.somPkgNm.getValue())) {
                                prcMtrPkgPk = NWXC412001.getPrcMtrPkgPkFromName(dsImptOrdBean.glblCmpyCd.getValue(), nmai4120_18TMsg.somPkgNm.getValue(), dsImptOrdBean.slsDt);
                                this.getCachePrcMtrPkgPkMap().put(nmai4120_18TMsg.somPkgNm.getValue(), prcMtrPkgPk);
                            } else {
                                prcMtrPkgPk = this.getCachePrcMtrPkgPkMap().get(nmai4120_18TMsg.somPkgNm.getValue());
                            }
                            //Billing Meter Name = SOM Service Counter Name
                            List<Map<String, Object>> prcMtrPkgMtrStruList = NWXC412001.getPrcMtrPkgMtrStru(dsImptOrdBean.glblCmpyCd.getValue(), 
                                    prcMtrPkgPk, mdlId, nmai4120_18TMsg.somSrvCntrNm.getValue(), dsImptOrdBean.slsDt);
                        	
                        	//QC#20393 start
                            String regUniqueKey = regShellContractUniqueString(service, nmai4120_18TMsg);
                            SomDsImptSvcDtlBean preSomDsImptSvcDtlBeanReg = somDsImptSvcDtlBeanRegMap.get(regUniqueKey);
                            SomDsImptSvcPrcBean preSomDsImptSvcPrcBeanReg = somDsImptSvcPrcBeanRegMap.get(regUniqueKey);
                            BigDecimal reqBaseAmtReg = reqBaseAmtRegMap.get(regUniqueKey);
                            if (!ZYPCommonFunc.hasValue(reqBaseAmtReg)) {
                                reqBaseAmtReg = BigDecimal.ZERO;
                            }
                        	//QC#20393 end
                            BigDecimal serviceLineId = nmai4120_18TMsg.somSrvLineId.getValue();
                            if (prevServiceLineIdReg != null && serviceLineId.compareTo(prevServiceLineIdReg) == 0) {

                                if (ZYPCommonFunc.hasValue(nmai4120_18TMsg.reqBaseAmt)) {
                                    reqBaseAmtReg = reqBaseAmtReg.add(nmai4120_18TMsg.reqBaseAmt.getValue());
                                }
                                preSomDsImptSvcDtlBeanReg.setReqBaseAmt(reqBaseAmtReg);
                                //QC#20393 start
                                reqBaseAmtRegMap.put(regUniqueKey, reqBaseAmtReg);
                                BigDecimal prcBaseAmt = BigDecimal.ZERO;
                                prcBaseAmt = preSomDsImptSvcPrcBeanReg.getReqBaseAmt();
                                if (prcBaseAmt == null) {
                                    prcBaseAmt = BigDecimal.ZERO;
                                }
                                preSomDsImptSvcPrcBeanReg.setReqBaseAmt(prcBaseAmt.add(nmai4120_18TMsg.reqBaseAmt.getValue()));
                                //QC#20393 end
                                //Price Meter Package List
                                if (prcMtrPkgMtrStruList != null && !prcMtrPkgMtrStruList.isEmpty()) {
                                    for (Map<String, Object> prcMtrPkgMtrStru : prcMtrPkgMtrStruList) {
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, preSomDsImptSvcPrcBeanReg, nmai4120_18TMsg, prcMtrPkgMtrStru, service);
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, preSomDsImptSvcPrcBeanReg, nmai4120_18TMsg, prcMtrPkgMtrStru, service);
                                    }
                                }//End of Price Meter Package List
                                else {
                                    if (ZYPCommonFunc.hasValue(nmai4120_18TMsg.somSrvCntrNm)) {
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, preSomDsImptSvcPrcBeanReg, nmai4120_18TMsg, null, service);
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, preSomDsImptSvcPrcBeanReg, nmai4120_18TMsg, null, service);
                                    }
                                }
                                
                                // skip same service line ID
                                continue;
                            } else {
                                //QC#20393 start
                                // create service detail
                                SomDsImptSvcDtlBean somDsImptSvcDtlBeanReg = null;
                                if (regBundleList.contains(regUniqueKey)) {
                                    somDsImptSvcDtlBeanReg = preSomDsImptSvcDtlBeanReg;
                                } else {
                                    somDsImptSvcDtlBeanReg = new SomDsImptSvcDtlBean(SHELL_TYPE.COPIER, dsImptOrdBean, service, nmai4120_18TMsg, CONTRACT_TYPE.REGULAR);
                                    reqBaseAmtReg = BigDecimal.ZERO;
                                }
                                //SomDsImptSvcDtlBean somDsImptSvcDtlBeanReg = new SomDsImptSvcDtlBean(SHELL_TYPE.COPIER, dsImptOrdBean, service, nmai4120_18TMsg, CONTRACT_TYPE.REGULAR);
                                regBundleList.add(regUniqueKey);
                                if (ZYPCommonFunc.hasValue(nmai4120_18TMsg.reqBaseAmt)) {
                                    reqBaseAmtReg = reqBaseAmtReg.add(nmai4120_18TMsg.reqBaseAmt.getValue());
                                }
                                //somDsImptSvcDtlBean.setMixCopierNonCopierFlg(false);
                                somDsImptSvcDtlBeanReg.setReqBaseAmt(reqBaseAmtReg);

                                // create service price
                                SomDsImptSvcPrcBean somDsImptSvcPrcBeanReg = new SomDsImptSvcPrcBean(SHELL_TYPE.COPIER, dsImptOrdBean, nmai4120_18TMsg, service);
                                //somDsImptSvcPrcBeanReg.setReqBaseAmt(reqBaseAmtReg);
                                somDsImptSvcPrcBeanReg.setReqBaseAmt(nmai4120_18TMsg.reqBaseAmt.getValue());
                                //QC#20393 end
                                somDsImptSvcPrcBeanReg.dsImptSvcDtlBean = somDsImptSvcDtlBeanReg;
                                somDsImptSvcDtlBeanReg.dsImptSvcPrcBeanList.add(somDsImptSvcPrcBeanReg);
                                //dsImptSvcPrcBeanList.add(somDsImptSvcPrcBeanReg);
                                //Price Meter Package List
                                if (prcMtrPkgMtrStruList != null && !prcMtrPkgMtrStruList.isEmpty()) {
                                    for (Map<String, Object> prcMtrPkgMtrStru : prcMtrPkgMtrStruList) {
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, somDsImptSvcPrcBeanReg, nmai4120_18TMsg, prcMtrPkgMtrStru, service);
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, somDsImptSvcPrcBeanReg, nmai4120_18TMsg, prcMtrPkgMtrStru, service);
                                    }
                                }//End of Price Meter Package List
                                else {
                                    if (ZYPCommonFunc.hasValue(nmai4120_18TMsg.somSrvCntrNm)) {
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, somDsImptSvcPrcBeanReg, nmai4120_18TMsg, null, service);
                                        new SomDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, somDsImptSvcPrcBeanReg, nmai4120_18TMsg, null, service);
                                    }
                                }
                                
                                // create service configuration reference
                                new SomDsImptSvcConfigRefBean(SHELL_TYPE.COPIER, somDsImptSvcDtlBeanReg, service, nmai4120_18TMsg, config, somDsImptSvcPrcBeanReg);
                                //QC#20393 start
                                somDsImptSvcDtlBeanRegMap.put(regUniqueKey, somDsImptSvcDtlBeanReg);
                                somDsImptSvcPrcBeanRegMap.put(regUniqueKey, somDsImptSvcPrcBeanReg);
                                reqBaseAmtRegMap.put(regUniqueKey, reqBaseAmtReg);
                                //QC#20393 end
                                
                                // *****************************************************************
                                // additional base
                                // NWAI4120_17.SOM_SRV_ID
                                // *****************************************************************
                                for (NWAI4120_17TMsg nwai4120_17TMsg : nwai4120_17List) {

                                    if (service.somSrvId.getValue().compareTo(nwai4120_17TMsg.somSrvId.getValue()) != 0) {
                                        //skip different shell
                                        continue;
                                    }
                                    //Not Decline Maintenance List
                                    if (S21StringUtil.isEquals(nwai4120_17TMsg.dclnMaintIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
                                        //skip decline maintenance
                                        continue;
                                    }
                                    BigDecimal baseConfigId = nwai4120_17TMsg.somConfigId.getValue();
                                    if (config.nwai4120_12 == null 
                                            || !ZYPCommonFunc.hasValue(config.nwai4120_12.somConfigId)
                                            || config.nwai4120_12.somConfigId.getValue().compareTo(baseConfigId) != 0) {
                                        continue;
                                    }
                                    new SomDsImptSvcAddlBaseBean(SHELL_TYPE.COPIER, somDsImptSvcDtlBeanReg, nwai4120_17TMsg, config);
                                }//End of Additional Base Only Loop
                                
                                // *****************************************************************
                                // additional charge
                                // NWAI4120_20.SOM_SRV_ID
                                // *****************************************************************
                                //Additional Charge Loop
                                for (NWAI4120_20TMsg nwai4120_20TMsg : nwai4120_20List) {

                                    BigDecimal chargeServiceId = nwai4120_20TMsg.somSrvId.getValue();
                                    if (service.somSrvId.getValue().compareTo(chargeServiceId) != 0) {
                                        //skip different shell
                                        continue;
                                    }
                                    //Not Decline Maintenance List
                                    if (S21StringUtil.isEquals(nwai4120_20TMsg.dclnMaintIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
                                        //skip decline maintenance
                                        continue;
                                    }
                                    BigDecimal chargeConfigId = nwai4120_20TMsg.somConfigId.getValue();
                                    if (config.nwai4120_12 == null 
                                            || !ZYPCommonFunc.hasValue(config.nwai4120_12.somConfigId)
                                            || config.nwai4120_12.somConfigId.getValue().compareTo(chargeConfigId) != 0) {
                                        continue;
                                    }
                                    new SomDsImptSvcAddlChrgBean(SHELL_TYPE.COPIER, somDsImptSvcDtlBeanReg, nwai4120_20TMsg, config);
                                }//End of Additional Charge Loop
                                
                                // *****************************************************************
                                // rental
                                // NWAI4120_21.SOM_SRV_ID
                                // *****************************************************************
                                //Rental Loop
                                for (NWAI4120_21TMsg nwai4120_21TMsg : nwai4120_21List) {
                                    if (serviceLineId.compareTo(nwai4120_21TMsg.somConfigId.getValue()) != 0) {
                                        // skip
                                        continue;
                                    }
                                    BigDecimal chargeConfigId = nwai4120_21TMsg.somConfigId.getValue();
                                    if (config.nwai4120_12 == null 
                                            || !ZYPCommonFunc.hasValue(config.nwai4120_12.somConfigId)
                                            || config.nwai4120_12.somConfigId.getValue().compareTo(chargeConfigId) != 0) {
                                        continue;
                                    }
                                    //exclude main machine
                                    //QC#21442
                                    //BigDecimal somConfigId = config.nwai4120_12.somConfigId.getValue();
                                    //BigDecimal somSortOrdNum = somDsImptSvcDtlBeanReg.getSomSortOrdNum();
                                    //BigDecimal mainMachineConfigId = nwai4120_21TMsg.somConfigId.getValue();
                                    //BigDecimal mainMachineSomSortOrdNum = nwai4120_21TMsg.somConfigLineId.getValue();
                                    //if (somConfigId.compareTo(mainMachineConfigId) != 0
                                    //        || somSortOrdNum.compareTo(mainMachineSomSortOrdNum) != 0) {
                                    //    new SomDsImptSvcAddlBaseBean(SHELL_TYPE.COPIER, somDsImptSvcDtlBeanReg, nwai4120_21TMsg, RENTAL_TYPE.SERVICE);
                                    //}
                                    
                                    new SomDsImptSvcAddlBaseBean(SHELL_TYPE.COPIER, somDsImptSvcDtlBeanReg, nwai4120_21TMsg, RENTAL_TYPE.EQUIPMENT, config);
                                }//End of Rental Loop
                                
                                
                                prevServiceLineIdReg = serviceLineId;
                                preSomDsImptSvcPrcBeanReg = somDsImptSvcPrcBeanReg;
                                preSomDsImptSvcDtlBeanReg = somDsImptSvcDtlBeanReg;
                            }
                        }//End of If Fleet/Aggregate/Regular
                    }//End of Loop Copiers
                }// End of Configuration Loop
            }// End of Configuration Loop
        }//End of Service Header Loop
        //QC#24023
        if (!serviceFlg) {
            for (SomDsImptOrdConfigBean config : dsImptOrdBean.dsImptOrdConfigBeanList) {
                config.setCopierDeclineMaintenanceFlg(true);
            }
        }
    }

    public void createShellForSrvNonCopiers(SomDsImptOrdBean dsImptOrdBean, 
            List<NWAI4120_16TMsg> nwai4120_16List, 
            List<NWAI4120_17TMsg> nwai4120_17List, 
            List<NWAI4120_18TMsg> nwai4120_18List, 
            List<NWAI4120_19TMsg> nwai4120_19List, 
            List<NWAI4120_20TMsg> nwai4120_20List,
            List<NWAI4120_21TMsg> nwai4120_21List) {

        List<SomDsImptSvcPrcBean> dsImptSvcPrcBeanList = new ArrayList<SomDsImptSvcPrcBean>();
        List<String> regBundleList = new ArrayList<String>(); //QC#20393

        boolean serviceFlg = false; //QC#24023
        // per service ID = quote ID
        for (NWAI4120_16TMsg service : nwai4120_16List) {

            serviceFlg = true;
            //Make Not Decline Maintenance List
            List<NWAI4120_19TMsg> ndm_nwai4120_19list = new ArrayList<NWAI4120_19TMsg>();
            for (NWAI4120_19TMsg serviceLine : nwai4120_19List) {
                if (!S21StringUtil.isEquals(serviceLine.dclnMaintIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
                    //Make Not Decline Maintenance List
                    ndm_nwai4120_19list.add(serviceLine);
                }
            }
            //Configuration Loop
            for (SomDsImptOrdConfigBean config : dsImptOrdBean.dsImptOrdConfigBeanList) {
            
                if (config.nwai4120_12 == null || !ZYPCommonFunc.hasValue(config.nwai4120_12.somConfigId)) {
                    //skip configId is null
                    continue;
                }
                
                //Service Non Copier Loop
                BigDecimal prevServiceLineId = null;
                boolean nonCopierserviceFlg = false;
                for (NWAI4120_19TMsg serviceLine : nwai4120_19List) {
                    
                    if (service.somSrvId.getValue().compareTo(serviceLine.somSrvId.getValue()) != 0) {
                        //skip different shell
                        continue;
                    }
                    
                    if (config.nwai4120_12.somConfigId.getValue().compareTo(serviceLine.somConfigId.getValue()) != 0) {
                        //skip different configId
                        continue;
                    }
                    nonCopierserviceFlg = true;
                    BigDecimal serviceLineId = serviceLine.somSrvLineId.getValue();
                    if (prevServiceLineId != null && serviceLineId.compareTo(prevServiceLineId) == 0) {
                        // skip same service line ID
                        continue;
                    } else {
                        prevServiceLineId = serviceLineId;
                    }
                    //Not Decline Maintenance List
                    if (!S21StringUtil.isEquals(serviceLine.dclnMaintIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
                        //ndm_nwai4120_19list.add(serviceLine);
                        config.setNonCopierDeclineMaintenanceFlg(false);
                        //ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_OFF_0);
                    } else {
                        config.setNonCopierDeclineMaintenanceFlg(true);
                        //ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_ON_1);
                    }
                }//End of Service Non Copier Loop
                if (!nonCopierserviceFlg) {
                    config.setNonCopierDeclineMaintenanceFlg(true);
                }
            }//End of Configuration Loop
            
            // *****************************************************************
            // service price per
            // NWAI4120_16.SOM_SRV_ID, model Id
            // *****************************************************************
            // *****************************************************************
            // service detail (service line) per
            // NWAI4120_18.SOM_SRV_LINE_ID
            // *****************************************************************
            // *****************************************************************
            // service configuration reference per
            // NWAI4120_18.SOM_SRV_LINE_ID
            // *****************************************************************
            //Non Decline Maintenance List
            if (ndm_nwai4120_19list.size() > 0) {
                //QC#20393 start
                Map<String, SomDsImptSvcDtlBean> somDsImptSvcDtlBeanMap = new HashMap<String, SomDsImptSvcDtlBean>();
                Map<String, BigDecimal> reqBaseAmtRegMap = new HashMap<String, BigDecimal>();
                //SomDsImptSvcDtlBean preSomDsImptSvcDtlBean = null;
                //BigDecimal reqBaseAmtReg = BigDecimal.ZERO;
                //QC#20393 end
                //Configuration Loop
                for (SomDsImptOrdConfigBean config : dsImptOrdBean.dsImptOrdConfigBeanList) {
                    
                    BigDecimal prevServiceLineId = null;
                    boolean nonCopierServiceFlg = false; //QC#24023
                    //Service Non Copier Loop
                    for (NWAI4120_19TMsg serviceLine : ndm_nwai4120_19list) {

                        if (service.somSrvId.getValue().compareTo(serviceLine.somSrvId.getValue()) != 0) {
                            //skip different shell
                            continue;
                        }
                        
                        BigDecimal serviceLineId = serviceLine.somSrvLineId.getValue();
                        if (config.nwai4120_12 == null 
                                || !ZYPCommonFunc.hasValue(config.nwai4120_12.somConfigId)
                                || config.nwai4120_12.somConfigId.getValue().compareTo(serviceLineId) != 0) {
                            continue;
                        }
                        
                        nonCopierServiceFlg = true; //QC#24023
                        if (S21StringUtil.isEquals(serviceLine.dclnMaintIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
                            config.setNonCopierDeclineMaintenanceFlg(true);
                            //ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_ON_1);
                        } else {
                            config.setNonCopierDeclineMaintenanceFlg(false);
                            //ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_OFF_0);
                        }
                        
                        if (prevServiceLineId != null && serviceLineId.compareTo(serviceLineId) == 0) {
                            // skip same service line ID
                            continue;
                        } else {

                            prevServiceLineId = serviceLineId;
                        }
                        //QC#20393 start
                        SomDsImptSvcDtlBean somDsImptSvcDtlBean = null;
                        String regUniqueKey = regShellContractUniqueString(service, serviceLine);
                        SomDsImptSvcDtlBean preSomDsImptSvcDtlBean = somDsImptSvcDtlBeanMap.get(regUniqueKey);
                        BigDecimal reqBaseAmtReg = reqBaseAmtRegMap.get(regUniqueKey);
                        if (regBundleList.contains(regUniqueKey)) {
                            somDsImptSvcDtlBean = preSomDsImptSvcDtlBean;
                        } else {
                            somDsImptSvcDtlBean = new SomDsImptSvcDtlBean(SHELL_TYPE.NON_COPIER, dsImptOrdBean, service, serviceLine, CONTRACT_TYPE.REGULAR);
                            reqBaseAmtReg = BigDecimal.ZERO;
                        }
                        regBundleList.add(regUniqueKey);
                        // create service detail
                        //SomDsImptSvcDtlBean somDsImptSvcDtlBean = new SomDsImptSvcDtlBean(SHELL_TYPE.NON_COPIER, dsImptOrdBean, service, serviceLine, CONTRACT_TYPE.REGULAR);
                        //somDsImptSvcDtlBean.setMixCopierNonCopierFlg(mixCopierNonCopierFlg);
                        //QC#17122
                        //QC#20393 end
                        BigDecimal perMthReqBaseAmt = BigDecimal.ZERO;
                        BigDecimal totPerMthReqBaseAmt = BigDecimal.ZERO;
                        if (ZYPCommonFunc.hasValue(serviceLine.reqBaseAmt) 
                                && ZYPCommonFunc.hasValue(serviceLine.somDurnId)
                                && BigDecimal.ZERO.compareTo(serviceLine.somDurnId.getValue()) != 0) {
                            //QC#20189
                            //perMthReqBaseAmt //
                            //= serviceLine.reqBaseAmt.getValue().divide(serviceLine.somDurnId.getValue(), declPntDigt, BigDecimal.ROUND_HALF_UP); // QC#19451
                            perMthReqBaseAmt //
                            = serviceLine.reqBaseAmt.getValue().divide(serviceLine.somDurnId.getValue().divide(new BigDecimal("12")), declPntDigt, BigDecimal.ROUND_HALF_UP); // QC#19451
                            //totPerMthReqBaseAmt = perMthReqBaseAmt.multiply(serviceLine.somDurnId.getValue());
                            totPerMthReqBaseAmt = serviceLine.reqBaseAmt.getValue();
                        }
                        //somDsImptSvcDtlBean.setReqBaseAmt(serviceLine.reqBaseAmt.getValue());
                        //QC#20393 start
                        reqBaseAmtReg = reqBaseAmtReg.add(totPerMthReqBaseAmt);
                        somDsImptSvcDtlBean.setReqBaseAmt(reqBaseAmtReg);
                        //QC#20393 end
                        
                        // create service price
                        SomDsImptSvcPrcBean somDsImptSvcPrcBean = new SomDsImptSvcPrcBean(SHELL_TYPE.NON_COPIER, dsImptOrdBean, serviceLine, service);
                        somDsImptSvcPrcBean.setReqBaseAmt(perMthReqBaseAmt); //QC#17122
                        somDsImptSvcPrcBean.dsImptSvcDtlBean = somDsImptSvcDtlBean;
                        somDsImptSvcDtlBean.dsImptSvcPrcBeanList.add(somDsImptSvcPrcBean);
                        dsImptSvcPrcBeanList.add(somDsImptSvcPrcBean);

                        // create service configuration reference
                        new SomDsImptSvcConfigRefBean(SHELL_TYPE.NON_COPIER, somDsImptSvcDtlBean, service, serviceLine, config, somDsImptSvcPrcBean);
                        //QC#20393 start
                        somDsImptSvcDtlBeanMap.put(regUniqueKey, somDsImptSvcDtlBean);
                        reqBaseAmtRegMap.put(regUniqueKey, reqBaseAmtReg);
                        //QC#20393 end
                        
                        // *****************************************************************
                        // additional base
                        // NWAI4120_17.SOM_SRV_ID
                        // *****************************************************************
                        //Additional Base Only Loop
                        for (NWAI4120_17TMsg base : nwai4120_17List) {
                            BigDecimal baseServiceId = base.somSrvId.getValue();
                            BigDecimal baseConfigId = base.somConfigId.getValue();
                            if (service.somSrvId.getValue().compareTo(baseServiceId) != 0) {
                                //skip different shell
                                continue;
                            }
                            if (config.nwai4120_12 == null 
                                    || !ZYPCommonFunc.hasValue(config.nwai4120_12.somConfigId)
                                    || config.nwai4120_12.somConfigId.getValue().compareTo(baseConfigId) != 0) {
                                continue;
                            }
                            //Not Decline Maintenance List
                            if (S21StringUtil.isEquals(base.dclnMaintIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
                                //skip decline maintenance
                                continue;
                            }
                            new SomDsImptSvcAddlBaseBean(SHELL_TYPE.NON_COPIER, somDsImptSvcDtlBean, base, config);
                        }//End of Additional Base Only Loop

                        // *****************************************************************
                        // additional base
                        // NWAI4120_20.SOM_SRV_ID
                        // *****************************************************************
                        //Additional Charge Loop
                        for (NWAI4120_20TMsg charge : nwai4120_20List) {
                            BigDecimal chargeServiceId = charge.somSrvId.getValue();
                            BigDecimal chargeConfigId = charge.somConfigId.getValue();
                            if (service.somSrvId.getValue().compareTo(chargeServiceId) != 0) {
                                //skip different shell
                                continue;
                            }
                            if (config.nwai4120_12 == null 
                                    || !ZYPCommonFunc.hasValue(config.nwai4120_12.somConfigId)
                                    || config.nwai4120_12.somConfigId.getValue().compareTo(chargeConfigId) != 0) {
                                continue;
                            }
                            //Not Decline Maintenance List
                            if (S21StringUtil.isEquals(charge.dclnMaintIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
                                //skip decline maintenance
                                continue;
                            }
                            
                            new SomDsImptSvcAddlChrgBean(SHELL_TYPE.NON_COPIER, somDsImptSvcDtlBean, charge, config);
                        }//End of Additional Charge Loop

                        // *****************************************************************
                        // additional base
                        // NWAI4120_21.SOM_CONFIG_LINE_ID
                        // *****************************************************************

                        //QC#15539-14
                        // *****************************************************************
                        // rental
                        // NWAI4120_21.SOM_SRV_ID
                        // *****************************************************************
                        //Rental Loop
                        for (NWAI4120_21TMsg rental : nwai4120_21List) {

                            BigDecimal chargeConfigId = rental.somConfigId.getValue();
                            if (config.nwai4120_12 == null 
                                    || !ZYPCommonFunc.hasValue(config.nwai4120_12.somConfigId)
                                    || config.nwai4120_12.somConfigId.getValue().compareTo(chargeConfigId) != 0) {
                                continue;
                            }
                            //exclude main machine
                            //BigDecimal somConfigId = somDsImptSvcDtlBean.nwai4120_19.somSrvLineId.getValue();
                            //BigDecimal somSortOrdNum = somDsImptSvcDtlBean.getSomSortOrdNum();
                            //BigDecimal mainMachineConfigId = rental.somConfigId.getValue();
                            //BigDecimal mainMachineSomSortOrdNum = rental.somConfigLineId.getValue();
                            //if (somConfigId.compareTo(mainMachineConfigId) != 0
                            //        || somSortOrdNum.compareTo(mainMachineSomSortOrdNum) != 0) {
                            //    new SomDsImptSvcAddlBaseBean(SHELL_TYPE.NON_COPIER, somDsImptSvcDtlBean, rental, RENTAL_TYPE.SERVICE, config);
                            //}
                            new SomDsImptSvcAddlBaseBean(SHELL_TYPE.NON_COPIER, somDsImptSvcDtlBean, rental, RENTAL_TYPE.EQUIPMENT, config);
                        }//End of Rental Loop
                        preSomDsImptSvcDtlBean = somDsImptSvcDtlBean;  //QC#20393
                    }                        
                    //QC#24023
                    if (!nonCopierServiceFlg) {
                        config.setNonCopierDeclineMaintenanceFlg(true);
                    }
                }//End of Configuration Loop
            }//End of Non Decline Maintenance List
            

        }
        if (!serviceFlg) {
            for (SomDsImptOrdConfigBean config : dsImptOrdBean.dsImptOrdConfigBeanList) {
                config.setNonCopierDeclineMaintenanceFlg(true);
            }
        }
    }

    public void createImptOrdDtlForSpiffItems(SomDsImptOrdBean dsImptOrdBean, List<NWAI4120_08TMsg> nwai4120_08List) {
        for (NWAI4120_08TMsg ifTMsg : nwai4120_08List) {
            // *****************************************************************
            // Process (15) (SPIFF ITEM)
            // *****************************************************************
            SomDsImptOrdDtlBean dtlBean = new SomDsImptOrdDtlBean(DTL_TYPE.SPIFF_ITEM, dsImptOrdBean.lastShipConfigBean, ifTMsg);
            new SomDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.SPIFF_ITEM, dtlBean);
        }
    }

    public void createImptOrdSlsCrForConfig(SomDsImptOrdBean dsImptOrdBean) {
        for (SomDsImptOrdConfigBean configBean : dsImptOrdBean.dsImptOrdConfigBeanList) {
            // *****************************************************************
            // Process (16) (Ship Config)
            // Process (16) (RMA Config)
            // *****************************************************************
            if (configBean.isShipConfig()) {
                new SomDsImptOrdSlsCrBean(SLS_CR_TYPE.SHIP_CONFIG, configBean);
            } else {
                new SomDsImptOrdSlsCrBean(SLS_CR_TYPE.RMA_CONFIG, configBean);
            }
        }
    }

    /**
     * For NWAI4120_03TMsg
     * @param configKey
     * @param nwai4120_03List
     * @return
     */
    public static List<NWAI4120_03TMsg> getNWAI4120_03TMsgByKey(BigDecimal somApvlId, List<NWAI4120_03TMsg> nwai4120_03List) {
        List<NWAI4120_03TMsg> retList = new ArrayList<NWAI4120_03TMsg>();

        for (NWAI4120_03TMsg ifTMsg : nwai4120_03List) {
            if (somApvlId.equals(ifTMsg.somApvlId.getValue())) {
                retList.add(ifTMsg);
            }
        }

        return retList;
    }

    /**
     * For NWAI4120_06TMsg
     * @param configKey
     * @param nwai4120_06List
     * @return
     */
    public static List<NWAI4120_06TMsg> getNWAI4120_06TMsgByKey(BigDecimal somQuoteId, BigDecimal somSoftCostId, List<NWAI4120_06TMsg> nwai4120_06List) {
        List<NWAI4120_06TMsg> retList = new ArrayList<NWAI4120_06TMsg>();

        for (NWAI4120_06TMsg ifTMsg : nwai4120_06List) {
            if (somQuoteId.compareTo(ifTMsg.somQuoteId.getValue()) == 0 && somSoftCostId.compareTo(ifTMsg.somSoftCostItemId.getValue()) == 0) {
                retList.add(ifTMsg);
            }
        }

        return retList;
    }

    /**
     * For NWAI4120_10TMsg
     * @param configKey
     * @param nwai4120_10List
     * @return
     */
    public static List<NWAI4120_10TMsg> getNWAI4120_10TMsgByConfigKey(String configKey, List<NWAI4120_10TMsg> nwai4120_10List) {
        List<NWAI4120_10TMsg> retList = new ArrayList<NWAI4120_10TMsg>();
        String ifKey;

        for (NWAI4120_10TMsg ifTMsg : nwai4120_10List) {
            ifKey = String.format(SomDsImptOrdConfigBean.configKeyFormat, ifTMsg.somQuoteId.getValue(), ifTMsg.somConfigId.getValue());
            if (S21StringUtil.isEquals(configKey, ifKey)) {
                retList.add(ifTMsg);
            }
        }

        return retList;
    }

    /**
     * For NWAI4120_13TMsg
     * @param configKey
     * @param nwai4120_13List
     * @return
     */
    public static List<NWAI4120_13TMsg> getNWAI4120_13TMsgByConfigKey(String configKey, List<NWAI4120_13TMsg> nwai4120_13List) {
        List<NWAI4120_13TMsg> retList = new ArrayList<NWAI4120_13TMsg>();
        String ifKey;

        for (NWAI4120_13TMsg ifTMsg : nwai4120_13List) {
            ifKey = String.format(SomDsImptOrdConfigBean.configKeyFormat, ifTMsg.somQuoteId.getValue(), ifTMsg.somConfigId.getValue());
            if (S21StringUtil.isEquals(configKey, ifKey)) {
                retList.add(ifTMsg);
            }
        }

        return retList;
    }

    /**
     * For NWAI4120_14TMsg
     * @param somQuoteId
     * @param somConfigId
     * @param nwai4120_14List
     * @return
     */
    public static List<NWAI4120_14TMsg> getNWAI4120_14TMsgByConfigKey(EZDTBigDecimalItem somQuoteId, EZDTBigDecimalItem somConfigId, List<NWAI4120_14TMsg> nwai4120_14List) {
        String ifKey = String.format(SomDsImptOrdConfigBean.configKeyFormat, somQuoteId.getValue(), somConfigId.getValue());
        return getNWAI4120_14TMsgByConfigKey(ifKey, nwai4120_14List);
    }

    /**
     * For NWAI4120_14TMsg
     * @param configKey
     * @param nwai4120_14List
     * @return
     */
    public static List<NWAI4120_14TMsg> getNWAI4120_14TMsgByConfigKey(String configKey, List<NWAI4120_14TMsg> nwai4120_14List) {
        List<NWAI4120_14TMsg> retList = new ArrayList<NWAI4120_14TMsg>();
        String ifKey;

        for (NWAI4120_14TMsg ifTMsg : nwai4120_14List) {
            ifKey = String.format(SomDsImptOrdConfigBean.configKeyFormat, ifTMsg.somQuoteId.getValue(), ifTMsg.somConfigId.getValue());
            if (S21StringUtil.isEquals(configKey, ifKey)) {
                retList.add(ifTMsg);
            }
        }

        return retList;
    }

    public static NWAI4120_16TMsg getNWAI4120_16TMsgByKey(BigDecimal somSrvId, List<NWAI4120_16TMsg> nwai4120_16List) {

        for (NWAI4120_16TMsg ifTMsg : nwai4120_16List) {
            if (somSrvId.equals(ifTMsg.somSrvId.getValue())) {
                return ifTMsg;
            }
        }

        return null;
    }

    public static List<NWAI4120_17TMsg> getNWAI4120_17TMsgByConfigKey(String configKey, List<NWAI4120_17TMsg> nwai4120_17List) {
        List<NWAI4120_17TMsg> retList = new ArrayList<NWAI4120_17TMsg>();
        String ifKey;

        for (NWAI4120_17TMsg ifTMsg : nwai4120_17List) {
            ifKey = String.format(SomDsImptOrdConfigBean.configKeyFormat, ifTMsg.somQuoteId.getValue(), ifTMsg.somConfigId.getValue());
            if (NWAB412001Constant.FLG.NO.name().equals(ifTMsg.dclnMaintIndSomTxt.getValue()) && S21StringUtil.isEquals(configKey, ifKey)) {
                retList.add(ifTMsg);
            }
        }

        return retList;
    }

    public static List<NWAI4120_20TMsg> getNWAI4120_20TMsgByConfigKey(String configKey, List<NWAI4120_20TMsg> nwai4120_20List) {
        List<NWAI4120_20TMsg> retList = new ArrayList<NWAI4120_20TMsg>();
        String ifKey;

        for (NWAI4120_20TMsg ifTMsg : nwai4120_20List) {
            ifKey = String.format(SomDsImptOrdConfigBean.configKeyFormat, ifTMsg.somQuoteId.getValue(), ifTMsg.somConfigId.getValue());
            if (NWAB412001Constant.FLG.NO.name().equals(ifTMsg.dclnMaintIndSomTxt.getValue()) && S21StringUtil.isEquals(configKey, ifKey)) {
                retList.add(ifTMsg);
            }
        }

        return retList;
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {
        boolean isSuccess = true;

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SQ));
        ZYPEZDItemValueSetter.setValue(this.cpoSrcTpCd, CPO_SRC_TP.SOM);
        ZYPEZDItemValueSetter.setValue(this.ordSrcImptTs, NWXC412001.getValidTimeStamp(this.nwai4120_01.lastUpdTs.getValue()));
        ZYPEZDItemValueSetter.setValue(this.ordSrcRefNum, NWXC412001.formatOrdSrcRefNum(this.nwai4120_01.somQuoteId.getValue().toPlainString()));
        ZYPEZDItemValueSetter.setValue(this.imptStsCd, IMPT_STS.PENDING_OM_REVIEW);
        ZYPEZDItemValueSetter.setValue(this.sysSrcCd, SYS_SRC.S21_ORDER);
        ZYPEZDItemValueSetter.setValue(this.dsOrdCatgCd, this.pDsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(this.dsOrdTpCd, this.pDsOrdTpCd);
        this.dsOrdRsnCd.clear();
        DS_ORD_TPTMsg dsOrdTp = new DS_ORD_TPTMsg();
        if (ZYPCommonFunc.hasValue(this.dsOrdTpCd)) {
            ZYPEZDItemValueSetter.setValue(dsOrdTp.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsOrdTp.dsOrdTpCd, this.dsOrdTpCd);
            dsOrdTp = (DS_ORD_TPTMsg) S21CodeTableAccessor.findByKey(dsOrdTp);
        }
        if (dsOrdTp != null) {
            ZYPEZDItemValueSetter.setValue(this.cpoOrdTpCd, dsOrdTp.cpoOrdTpCd);
        } else {
            this.cpoOrdTpCd.clear();
        }

        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfn = null;
        if (ZYPCommonFunc.hasValue(this.dsOrdTpCd)) {

            dsOrdTpProcDfn = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfn.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfn.dsOrdTpCd, this.dsOrdTpCd);
            dsOrdTpProcDfn = (DS_ORD_TP_PROC_DFNTMsg) EZDTBLAccessor.findByKey(dsOrdTpProcDfn);
        }

        if (ZYPCommonFunc.hasValue(this.nwai4120_01.somPoNum)) {
            ZYPEZDItemValueSetter.setValue(this.custIssPoNum, S21StringUtil.subStringByLength(this.nwai4120_01.somPoNum.getValue(), 0, 35));
            ZYPEZDItemValueSetter.setValue(this.custIssPoDt, NWXC412001.getValidDate(this.nwai4120_01.custIssPoDt.getValue()));
        } else {
            this.custIssPoNum.clear();
            this.custIssPoDt.clear();
        }

        String billToCustAcctCd = null;
        String billToCustCd = null;
        // 2017/01/27 S21_NA#17119 Add Start
        String billToLocNm = null;
        String billToAddlLocNm = null;
        String billToFirstLineAddr = null;
        String billToScdLineAddr = null;
        String billToThirdLineAddr = null;
        String billToFrthLineAddr = null;
        String billToFirstRefCmntTxt = null;
        String billToScdRefCmntTxt = null;
        String billToCtyAddr = null;
        String billToStCd = null;
        String billToProvNm = null;
        String billToCntyNm = null;
        String billToPostCd = null;
        String billToCtryCd = null;
        String billToAcctNm = null;
        // 2017/01/27 S21_NA#17119 Add End
        //QC#20988
        String soldToCustAcctCd = null;
        String soldToCustCd = null;
        String soldToLocNm = null;
        String soldToAddlLocNm = null;
        String soldToFirstLineAddr = null;
        String soldToScdLineAddr = null;
        String soldToThirdLineAddr = null;
        String soldToFrthLineAddr = null;
        String soldToFirstRefCmntTxt = null;
        String soldToScdRefCmntTxt = null;
        String soldToCtyAddr = null;
        String soldToStCd = null;
        String soldToProvNm = null;
        String soldToCntyNm = null;
        String soldToPostCd = null;
        String soldToCtryCd = null;
        String soldToAcctNm = null;
        Map<String, Object> billToInfoMap = null;
        //QC#20753
        String shipToAddrConcatTxt = 
            S21StringUtil.concatStrings(
                S21StringUtil.trimString(
                        S21StringUtil.concatStrings(
                                NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai4120_01.shipToAddrDescTxt_01.getValue())), 
                                " ",
                                NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai4120_01.shipToAddrDescTxt_02.getValue())))), 
                NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai4120_01.shipToCityTxt.getValue())), 
                NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai4120_01.shipToStTxt.getValue())), 
                NWXC412001.toUpperCase(NWXC412001.cutString(S21StringUtil.trimString(this.nwai4120_01.somShipToZipCd.getValue()), 5)));
        String billToAddrConcatTxt = 
            S21StringUtil.concatStrings(
                NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai4120_01.bllgAddrDescTxt.getValue())), 
                NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai4120_01.bllgCityDescTxt.getValue())), 
                NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai4120_01.bllgStTxt.getValue())), 
                NWXC412001.toUpperCase(NWXC412001.cutString(S21StringUtil.trimString(this.nwai4120_01.bllgZipTxt.getValue()), 5)));
        //QC#19968
        String endUsrBillToCustAcctCd = "";
        String endUsrBillToCustCd = "";
        if (ZYPCommonFunc.hasValue(this.nwai4120_01.somShipToPtyNum) && this.nwai4120_01.somShipToPtyNum.getValue().compareTo(BigDecimal.ZERO) != 0) {
            //Same with Ship to
            if (shipToAddrConcatTxt.equals(billToAddrConcatTxt)) {
                
                billToInfoMap = NWXC412001.getBillToInfoFromSomShipToPtyNum(glblCmpyCd, this.nwai4120_01.somShipToPtyNum.getValue().toString(), salesDate);
                
            //Not same with Ship to
            } else if (!shipToAddrConcatTxt.equals(billToAddrConcatTxt)) {
                
                billToInfoMap = NWXC412001.getBillToInfoFromAddress(glblCmpyCd, billToAddrConcatTxt, salesDate, this.nwai4120_01.bllgCmpyTxt.getValue());
            }
        } else if (!ZYPCommonFunc.hasValue(this.nwai4120_01.somShipToPtyNum) || this.nwai4120_01.somShipToPtyNum.getValue().compareTo(BigDecimal.ZERO) == 0) {
            billToInfoMap = NWXC412001.getBillToInfoFromAddress(glblCmpyCd, billToAddrConcatTxt, salesDate, this.nwai4120_01.bllgCmpyTxt.getValue());
        }
        if (billToInfoMap != null && !billToInfoMap.isEmpty()) {
            endUsrBillToCustAcctCd = (String) billToInfoMap.get("DS_ACCT_NUM");
            endUsrBillToCustCd = (String) billToInfoMap.get("BILL_TO_CUST_CD");
        }

//QC#19968
//        //QC#15539-14
//        //Not Lease
//        if (!this.nwai4120_01.trxTpTxt.getValue().equals(NWAB412001Constant.TRX_TP_TXT_LEASE)) {
//            if (ZYPCommonFunc.hasValue(this.nwai4120_01.somShipToPtyNum) && this.nwai4120_01.somShipToPtyNum.getValue().compareTo(BigDecimal.ZERO) != 0) {
//                //Same with Ship to
//                if (shipToAddrConcatTxt.equals(billToAddrConcatTxt)) {
//                    
//                    billToInfoMap = NWXC412001.getBillToInfoFromSomShipToPtyNum(glblCmpyCd, this.nwai4120_01.somShipToPtyNum.getValue().toString(), salesDate);
//                    
//                //Not same with Ship to
//                } else if (!shipToAddrConcatTxt.equals(billToAddrConcatTxt)) {
//                    
//                    billToInfoMap = NWXC412001.getBillToInfoFromAddress(glblCmpyCd, billToAddrConcatTxt, salesDate, this.nwai4120_01.bllgCmpyTxt.getValue());
//                }
//            } else if (!ZYPCommonFunc.hasValue(this.nwai4120_01.somShipToPtyNum) || this.nwai4120_01.somShipToPtyNum.getValue().compareTo(BigDecimal.ZERO) == 0) {
//                billToInfoMap = NWXC412001.getBillToInfoFromAddress(glblCmpyCd, billToAddrConcatTxt, salesDate, this.nwai4120_01.bllgCmpyTxt.getValue());
//            }
//        //Lease
//        } else {
//            if (ZYPCommonFunc.hasValue(this.pLeaseCmpyDsAcctNum)) {
//                billToInfoMap = NWXC412001.getBillToInfoFromDsAcctNum(glblCmpyCd, this.pLeaseCmpyDsAcctNum, salesDate);
//            } else {
//                if (dsOrdTpProcDfn != null) {
//
//                    billToCustAcctCd = dsOrdTpProcDfn.defBillToCustAcctCd.getValue();
//                    billToCustCd = dsOrdTpProcDfn.defBillToCustCd.getValue();
//                    billToInfoMap = NWXC412001.getBillToInfoByCode(glblCmpyCd, billToCustAcctCd, billToCustCd, salesDate);
//                }
//            }
//        }
        
        //QC#20988
        //Lease
        if (this.nwai4120_01.trxTpTxt.getValue().equals(NWAB412001Constant.TRX_TP_TXT_LEASE)) {
            if (ZYPCommonFunc.hasValue(this.pLeaseCmpyDsAcctNum)) {
                billToInfoMap = NWXC412001.getBillToInfoFromDsAcctNum(glblCmpyCd, this.pLeaseCmpyDsAcctNum, salesDate);
                if (billToInfoMap != null && !billToInfoMap.isEmpty()) {
                    billToCustAcctCd = (String) billToInfoMap.get("DS_ACCT_NUM");
                    billToCustCd = (String) billToInfoMap.get("BILL_TO_CUST_CD");
                }
            } else {
                if (dsOrdTpProcDfn != null) {
                    billToCustAcctCd = dsOrdTpProcDfn.defBillToCustAcctCd.getValue();
                    billToCustCd = dsOrdTpProcDfn.defBillToCustCd.getValue();
                    billToInfoMap = NWXC412001.getBillToInfoByCode(glblCmpyCd, billToCustAcctCd, billToCustCd, salesDate);
                }
            }
            if (billToInfoMap == null || billToInfoMap.isEmpty()) {
                billToCustAcctCd = endUsrBillToCustAcctCd;
                billToCustCd = endUsrBillToCustCd;
                billToLocNm = S21StringUtil.subStringByLength(this.nwai4120_01.billToCustLegalNm.getValue(), 0, 60);
                billToAddlLocNm = null;

                if (ZYPCommonFunc.hasValue(this.nwai4120_01.bllgAddrDescTxt)) {

                    if (this.nwai4120_01.bllgAddrDescTxt.getValue().length() <= 60) {
                        billToFirstLineAddr = this.nwai4120_01.bllgAddrDescTxt.getValue();

                    } else {
                        String[] billToLineAddrs = this.nwai4120_01.bllgAddrDescTxt.getValue().split(" ");
                        StringBuffer[] addrs = {new StringBuffer(""),new StringBuffer(""),new StringBuffer(""),new StringBuffer("")};
                        int j=0;

                        for (int i=0; i<billToLineAddrs.length;i++) {
                            if (addrs[j].length() + billToLineAddrs[i].length() + 1 > 60) {
                                j++;
                            }

                            if (addrs[j].length() > 0) {
                                addrs[j] = addrs[j].append(" ");
                            }
                            addrs[j] = addrs[j].append(billToLineAddrs[i]);
                        }
                        billToFirstLineAddr = addrs[0].toString();
                        billToScdLineAddr = addrs[1].toString();
                        billToThirdLineAddr = addrs[2].toString();
                        billToFrthLineAddr = addrs[3].toString();
                    }
                }
                billToCtyAddr = S21StringUtil.subStringByLength(this.nwai4120_01.bllgCityDescTxt.getValue(), 0, 25);
                billToStCd = S21StringUtil.subStringByLength(this.nwai4120_01.bllgStTxt.getValue(), 0, 2);
                billToProvNm = null;
                billToCntyNm = null;
                billToPostCd = S21StringUtil.subStringByLength(this.nwai4120_01.bllgZipTxt.getValue(), 0, 15);
                billToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;
                billToAcctNm = this.nwai4120_01.bllgCmpyTxt.getValue();
                
                soldToCustAcctCd = billToCustAcctCd;
                soldToCustCd = billToCustCd;
                soldToLocNm = billToLocNm;
                soldToAddlLocNm = billToAddlLocNm;
                soldToFirstLineAddr = billToFirstLineAddr;
                soldToScdLineAddr = billToScdLineAddr;
                soldToThirdLineAddr = billToThirdLineAddr;
                soldToFrthLineAddr = billToFrthLineAddr;
                soldToFirstRefCmntTxt = billToFirstRefCmntTxt;
                soldToScdRefCmntTxt = billToScdRefCmntTxt;
                soldToCtyAddr = billToCtyAddr;
                soldToStCd = billToStCd;
                soldToProvNm = billToProvNm;
                soldToCntyNm = billToCntyNm;
                soldToPostCd = billToPostCd;
                soldToCtryCd = billToCtryCd;
                soldToAcctNm = billToAcctNm;
            } else {
                soldToCustAcctCd = endUsrBillToCustAcctCd;
                soldToCustCd = endUsrBillToCustCd;
                soldToLocNm = S21StringUtil.subStringByLength(this.nwai4120_01.billToCustLegalNm.getValue(), 0, 60);
                soldToAddlLocNm = null;

                if (ZYPCommonFunc.hasValue(this.nwai4120_01.bllgAddrDescTxt)) {

                    if (this.nwai4120_01.bllgAddrDescTxt.getValue().length() <= 60) {
                        soldToFirstLineAddr = this.nwai4120_01.bllgAddrDescTxt.getValue();

                    } else {
                        String[] soldToLineAddrs = this.nwai4120_01.bllgAddrDescTxt.getValue().split(" ");
                        StringBuffer[] addrs = {new StringBuffer(""),new StringBuffer(""),new StringBuffer(""),new StringBuffer("")};
                        int j=0;

                        for (int i=0; i<soldToLineAddrs.length;i++) {
                            if (addrs[j].length() + soldToLineAddrs[i].length() + 1 > 60) {
                                j++;
                            }

                            if (addrs[j].length() > 0) {
                                addrs[j] = addrs[j].append(" ");
                            }
                            addrs[j] = addrs[j].append(soldToLineAddrs[i]);
                        }
                        soldToFirstLineAddr = addrs[0].toString();
                        soldToScdLineAddr = addrs[1].toString();
                        soldToThirdLineAddr = addrs[2].toString();
                        soldToFrthLineAddr = addrs[3].toString();
                    }
                }
                soldToCtyAddr = S21StringUtil.subStringByLength(this.nwai4120_01.bllgCityDescTxt.getValue(), 0, 25);
                soldToStCd = S21StringUtil.subStringByLength(this.nwai4120_01.bllgStTxt.getValue(), 0, 2);
                soldToProvNm = null;
                soldToCntyNm = null;
                soldToPostCd = S21StringUtil.subStringByLength(this.nwai4120_01.bllgZipTxt.getValue(), 0, 15);
                soldToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;
                soldToAcctNm = this.nwai4120_01.bllgCmpyTxt.getValue();
            }
            
        //Not Lease
        } else {
            if (billToInfoMap != null && !billToInfoMap.isEmpty()) {
                billToCustAcctCd = (String) billToInfoMap.get("DS_ACCT_NUM");
                billToCustCd = (String) billToInfoMap.get("BILL_TO_CUST_CD");
                
                if ("PROSPECT".equals((String) billToInfoMap.get("CATEGORY"))) {
                    billToLocNm = (String) billToInfoMap.get("LOC_NM");
                    billToAddlLocNm = (String) billToInfoMap.get("ADDL_LOC_NM");
                    billToFirstLineAddr = (String) billToInfoMap.get("FIRST_LINE_ADDR");
                    billToScdLineAddr = (String) billToInfoMap.get("SCD_LINE_ADDR");
                    billToThirdLineAddr = (String) billToInfoMap.get("THIRD_LINE_ADDR");
                    billToFrthLineAddr = (String) billToInfoMap.get("FRTH_LINE_ADDR");
                    //billToFirstRefCmntTxt = (String) billToInfoMap.get("LOC_NM");
                    //billToScdRefCmntTxt = (String) billToInfoMap.get("LOC_NM");
                    billToCtyAddr = (String) billToInfoMap.get("CTY_ADDR");
                    billToStCd = (String) billToInfoMap.get("ST_CD");
                    billToProvNm = (String) billToInfoMap.get("PROV_NM");
                    billToCntyNm = (String) billToInfoMap.get("CNTY_NM");
                    billToPostCd = (String) billToInfoMap.get("POST_CD");
                    billToCtryCd = (String) billToInfoMap.get("CTRY_CD");
                    billToAcctNm = (String) billToInfoMap.get("DS_ACCT_NM");
                }
            // 2017/01/27 S21_NA#17119 Add Start
            } else {
                billToLocNm = S21StringUtil.subStringByLength(this.nwai4120_01.billToCustLegalNm.getValue(), 0, 60);
                billToAddlLocNm = null;

                if (ZYPCommonFunc.hasValue(this.nwai4120_01.bllgAddrDescTxt)) {

                    if (this.nwai4120_01.bllgAddrDescTxt.getValue().length() <= 60) {
                        billToFirstLineAddr = this.nwai4120_01.bllgAddrDescTxt.getValue();

                    } else {
                        String[] billToLineAddrs = this.nwai4120_01.bllgAddrDescTxt.getValue().split(" ");
                        StringBuffer[] addrs = {new StringBuffer(""),new StringBuffer(""),new StringBuffer(""),new StringBuffer("")};
                        int j=0;

                        for (int i=0; i<billToLineAddrs.length;i++) {
                            if (addrs[j].length() + billToLineAddrs[i].length() + 1 > 60) {
                                j++;
                            }

                            if (addrs[j].length() > 0) {
                                addrs[j] = addrs[j].append(" ");
                            }
                            addrs[j] = addrs[j].append(billToLineAddrs[i]);
                        }
                        billToFirstLineAddr = addrs[0].toString();
                        billToScdLineAddr = addrs[1].toString();
                        billToThirdLineAddr = addrs[2].toString();
                        billToFrthLineAddr = addrs[3].toString();
                    }
                }
                billToCtyAddr = S21StringUtil.subStringByLength(this.nwai4120_01.bllgCityDescTxt.getValue(), 0, 25);
                billToStCd = S21StringUtil.subStringByLength(this.nwai4120_01.bllgStTxt.getValue(), 0, 2);
                billToProvNm = null;
                billToCntyNm = null;
                billToPostCd = S21StringUtil.subStringByLength(this.nwai4120_01.bllgZipTxt.getValue(), 0, 15);
                billToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;
                billToAcctNm = this.nwai4120_01.bllgCmpyTxt.getValue();
            }

            soldToCustAcctCd = billToCustAcctCd;
            soldToCustCd = billToCustCd;
            soldToLocNm = billToLocNm;
            soldToAddlLocNm = billToAddlLocNm;
            soldToFirstLineAddr = billToFirstLineAddr;
            soldToScdLineAddr = billToScdLineAddr;
            soldToThirdLineAddr = billToThirdLineAddr;
            soldToFrthLineAddr = billToFrthLineAddr;
            soldToFirstRefCmntTxt = billToFirstRefCmntTxt;
            soldToScdRefCmntTxt = billToScdRefCmntTxt;
            soldToCtyAddr = billToCtyAddr;
            soldToStCd = billToStCd;
            soldToProvNm = billToProvNm;
            soldToCntyNm = billToCntyNm;
            soldToPostCd = billToPostCd;
            soldToCtryCd = billToCtryCd;
            soldToAcctNm = billToAcctNm;
        }
        // 2017/01/27 S21_NA#17119 Add End
        ZYPEZDItemValueSetter.setValue(this.billToCustAcctCd, billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(this.billToCustCd, billToCustCd);
        // 2017/01/27 S21_NA#17119 Add Start
        ZYPEZDItemValueSetter.setValue(this.billToLocNm, billToLocNm);
        ZYPEZDItemValueSetter.setValue(this.billToAddlLocNm, billToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(this.billToFirstLineAddr, billToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(this.billToScdLineAddr, billToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(this.billToThirdLineAddr, billToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(this.billToFrthLineAddr, billToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(this.billToFirstRefCmntTxt, billToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(this.billToScdRefCmntTxt, billToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(this.billToCtyAddr, billToCtyAddr);
        ZYPEZDItemValueSetter.setValue(this.billToStCd, billToStCd);
        ZYPEZDItemValueSetter.setValue(this.billToProvNm, billToProvNm);
        ZYPEZDItemValueSetter.setValue(this.billToCntyNm, billToCntyNm);
        ZYPEZDItemValueSetter.setValue(this.billToPostCd, billToPostCd);
        ZYPEZDItemValueSetter.setValue(this.billToCtryCd, billToCtryCd);
        ZYPEZDItemValueSetter.setValue(this.billToAcctNm, billToAcctNm);
        // 2017/01/27 S21_NA#17119 Add End

        String shipToCustAcctCd = null;
        String shipToCustCd = null;
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
        Map<String, Object> shipToInfoMap = null;
        if (ZYPCommonFunc.hasValue(this.nwai4120_01.somShipToPtyNum) && this.nwai4120_01.somShipToPtyNum.getValue().compareTo(BigDecimal.ZERO) != 0) {

            if (!this.getCacheShipToCustMap().containsKey("FROMPSN" + this.nwai4120_01.somShipToPtyNum.getValue().toPlainString())) {
                shipToInfoMap = NWXC412001.getShipToInfoFromSomPsnCnt(glblCmpyCd, this.nwai4120_01.somShipToPtyNum.getValue(), salesDate);
                this.getCacheShipToCustMap().put("FROMPSN" + this.nwai4120_01.somShipToPtyNum.getValue().toPlainString(), shipToInfoMap);
            } else {
                shipToInfoMap = this.getCacheShipToCustMap().get("FROMPSN" + this.nwai4120_01.somShipToPtyNum.getValue().toPlainString());
            }
        } else {
            
            if (!this.getCacheShipToCustMap().containsKey("FROMADDR" + this.nwai4120_01.somShipToPtyNum.getValue() + shipToAddrConcatTxt)) {
                shipToInfoMap = NWXC412001.getShipToInfoFromAddress(glblCmpyCd, shipToAddrConcatTxt, salesDate, this.nwai4120_01.somShipToCustNm.getValue());
                this.getCacheShipToCustMap().put("FROMADDR" + this.nwai4120_01.somShipToPtyNum.getValue() + shipToAddrConcatTxt, shipToInfoMap);
            } else {
                shipToInfoMap = this.getCacheShipToCustMap().get("FROMADDR" + this.nwai4120_01.somShipToPtyNum.getValue() + shipToAddrConcatTxt);
            }
        }
        if (shipToInfoMap != null && !shipToInfoMap.isEmpty()) {
            shipToCustAcctCd = (String) shipToInfoMap.get("DS_ACCT_NUM");
            shipToCustCd = (String) shipToInfoMap.get("SHIP_TO_CUST_CD");
            shipToLocNm = (String) shipToInfoMap.get("LOC_NM");
            shipToAddlLocNm = (String) shipToInfoMap.get("ADDL_LOC_NM");
            shipToFirstLineAddr = (String) shipToInfoMap.get("FIRST_LINE_ADDR");
            shipToScdLineAddr = (String) shipToInfoMap.get("SCD_LINE_ADDR");
            shipToThirdLineAddr = (String) shipToInfoMap.get("THIRD_LINE_ADDR");
            shipToFrthLineAddr = (String) shipToInfoMap.get("FRTH_LINE_ADDR");
            shipToCtyAddr = (String) shipToInfoMap.get("CTY_ADDR");
            shipToStCd = (String) shipToInfoMap.get("ST_CD");
            shipToProvNm = (String) shipToInfoMap.get("PROV_NM");
            shipToCntyNm = (String) shipToInfoMap.get("CNTY_NM");
            shipToPostCd = (String) shipToInfoMap.get("POST_CD");
            shipToCtryCd = (String) shipToInfoMap.get("CTRY_CD");
            //QC#20988
            shipToAcctNm = (String) shipToInfoMap.get("DS_ACCT_NM");
        } else {
            shipToCustAcctCd = S21StringUtil.subStringByLength(this.nwai4120_01.shipToAcctNum.getValue(), 0, 20);
            shipToCustCd = null;
            shipToLocNm = this.nwai4120_01.shipToCustLegalNm.getValue();
            shipToAddlLocNm = null;
            String shipToLineAddr = S21StringUtil.concatStrings(this.nwai4120_01.shipToAddrDescTxt_01.getValue(), this.nwai4120_01.shipToAddrDescTxt_02.getValue());
            shipToFirstLineAddr = S21StringUtil.subStringByLength(shipToLineAddr, 0, 60);
            shipToScdLineAddr = S21StringUtil.subStringByLength(shipToLineAddr, 60, 60);
            shipToThirdLineAddr = S21StringUtil.subStringByLength(shipToLineAddr, 120, 60);
            shipToFrthLineAddr = S21StringUtil.subStringByLength(shipToLineAddr, 180, 20);
            shipToCtyAddr = S21StringUtil.subStringByLength(this.nwai4120_01.shipToCityTxt.getValue(), 0, 25);
            shipToStCd = this.nwai4120_01.shipToStTxt.getValue();
            shipToProvNm = null;
            shipToCntyNm = S21StringUtil.subStringByLength(this.nwai4120_01.shipToCntyTxt.getValue(), 0, 30);
            shipToPostCd = this.nwai4120_01.somShipToZipCd.getValue();
            shipToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;
            // 2017/01/30 S21_NA#17119 Add Start
            shipToAcctNm = this.nwai4120_01.somShipToCustNm.getValue();
            // 2017/01/30 S21_NA#17119 Add End
        }
        ZYPEZDItemValueSetter.setValue(this.shipToCustAcctCd, shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(this.shipToCustCd, shipToCustCd);
        ZYPEZDItemValueSetter.setValue(this.dropShipFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(this.shipToLocNm, shipToLocNm);
        ZYPEZDItemValueSetter.setValue(this.shipToAddlLocNm, shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(this.shipToFirstLineAddr, shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(this.shipToScdLineAddr, shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(this.shipToThirdLineAddr, shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(this.shipToFrthLineAddr, shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(this.shipToCtyAddr, shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(this.shipToStCd, shipToStCd);
        ZYPEZDItemValueSetter.setValue(this.shipToProvNm, shipToProvNm);
        ZYPEZDItemValueSetter.setValue(this.shipToCntyNm, shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(this.shipToPostCd, shipToPostCd);
        ZYPEZDItemValueSetter.setValue(this.shipToCtryCd, shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(this.shipToAcctNm, shipToAcctNm);
        this.shipTo01RefCmntTxt.clear();
        this.shipTo02RefCmntTxt.clear();

//        Map<String, Object> soldToInfoMap = NWXC412001.getSoldToInfoFromShipToCustCd(glblCmpyCd, shipToCustAcctCd, shipToCustCd, salesDate);
//        String sellToCustCd = null;
//        String soldToCustLocCd = null;
//        // 2017/01/30 S21_NA#17119 Add Start
//        String sldToLocNm = null;
//        String sldToAddlLocNm = null;
//        String sldToFirstLineAddr = null;
//        String sldToScdLineAddr = null;
//        String sldToThirdLineAddr = null;
//        String sldToFrthLineAddr = null;
//        String sldToFirstRefCmntTxt = null;
//        String sldToScdRefCmntTxt = null;
//        String sldToCtyAddr = null;
//        String sldToStCd = null;
//        String sldToProvNm = null;
//        String sldToCntyNm = null;
//        String sldToPostCd = null;
//        String sldToCtryCd = null;
//        String sldToAcctNm = null;
//        // 2017/01/30 S21_NA#17119 Add End
//        if (soldToInfoMap != null && !soldToInfoMap.isEmpty()) {
//            sellToCustCd = (String) soldToInfoMap.get("DS_ACCT_NUM");
//            soldToCustLocCd = (String) soldToInfoMap.get("BILL_TO_CUST_CD");
//        } else {
//            // 2017/01/30 S21_NA#17119 Add Start
//            sldToLocNm = S21StringUtil.subStringByLength(this.nwai4120_01.billToCustLegalNm.getValue(), 0, 60);
//            sldToAddlLocNm = null;
//
//            if (ZYPCommonFunc.hasValue(this.nwai4120_01.bllgAddrDescTxt)) {
//
//                if (this.nwai4120_01.bllgAddrDescTxt.getValue().length() <= 60) {
//                    sldToFirstLineAddr = this.nwai4120_01.bllgAddrDescTxt.getValue();
//
//                } else {
//                    String[] sldToLineAddrs = this.nwai4120_01.bllgAddrDescTxt.getValue().split(" ");
//                    StringBuffer[] addrs = {new StringBuffer(""),new StringBuffer(""),new StringBuffer(""),new StringBuffer("")};
//                    int j=0;
//
//                    for (int i=0; i<sldToLineAddrs.length;i++) {
//                        if (addrs[j].length() + sldToLineAddrs[i].length() + 1 > 60) {
//                            j++;
//                        }
//
//                        if (addrs[j].length() > 0) {
//                            addrs[j] = addrs[j].append(" ");
//                        }
//                        addrs[j] = addrs[j].append(sldToLineAddrs[i]);
//                    }
//                    sldToFirstLineAddr = addrs[0].toString();
//                    sldToScdLineAddr = addrs[1].toString();
//                    sldToThirdLineAddr = addrs[2].toString();
//                    sldToFrthLineAddr = addrs[3].toString();
//                }
//            }
//            sldToCtyAddr = S21StringUtil.subStringByLength(this.nwai4120_01.bllgCityDescTxt.getValue(), 0, 25);
//            sldToStCd = S21StringUtil.subStringByLength(this.nwai4120_01.bllgStTxt.getValue(), 0, 2);
//            sldToProvNm = null;
//            sldToCntyNm = null;
//            sldToPostCd = S21StringUtil.subStringByLength(this.nwai4120_01.bllgZipTxt.getValue(), 0, 15);
//            sldToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;
//            sldToAcctNm = this.nwai4120_01.bllgCmpyTxt.getValue();
//            // 2017/01/30 S21_NA#17119 Add End
//        }
//        ZYPEZDItemValueSetter.setValue(this.sellToCustCd, sellToCustCd);
//        ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, soldToCustLocCd);
//        // 2017/01/30 S21_NA#17119 Add Start
//        ZYPEZDItemValueSetter.setValue(this.sldToLocNm, sldToLocNm);
//        ZYPEZDItemValueSetter.setValue(this.sldToAddlLocNm, sldToAddlLocNm);
//        ZYPEZDItemValueSetter.setValue(this.sldToFirstLineAddr, sldToFirstLineAddr);
//        ZYPEZDItemValueSetter.setValue(this.sldToScdLineAddr, sldToScdLineAddr);
//        ZYPEZDItemValueSetter.setValue(this.sldToThirdLineAddr, sldToThirdLineAddr);
//        ZYPEZDItemValueSetter.setValue(this.sldToFrthLineAddr, sldToFrthLineAddr);
//        ZYPEZDItemValueSetter.setValue(this.sldToFirstRefCmntTxt, sldToFirstRefCmntTxt);
//        ZYPEZDItemValueSetter.setValue(this.sldToScdRefCmntTxt, sldToScdRefCmntTxt);
//        ZYPEZDItemValueSetter.setValue(this.sldToCtyAddr, sldToCtyAddr);
//        ZYPEZDItemValueSetter.setValue(this.sldToStCd, sldToStCd);
//        ZYPEZDItemValueSetter.setValue(this.sldToProvNm, sldToProvNm);
//        ZYPEZDItemValueSetter.setValue(this.sldToCntyNm, sldToCntyNm);
//        ZYPEZDItemValueSetter.setValue(this.sldToPostCd, sldToPostCd);
//        ZYPEZDItemValueSetter.setValue(this.sldToCtryCd, sldToCtryCd);
//        ZYPEZDItemValueSetter.setValue(this.sldToAcctNm, sldToAcctNm);
//        // 2017/01/30 S21_NA#17119 Add End
        
        //QC#20988
        //QC#19968
        ZYPEZDItemValueSetter.setValue(this.sellToCustCd, soldToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, soldToCustCd);
        // 2017/01/27 S21_NA#17119 Add Start
        ZYPEZDItemValueSetter.setValue(this.sldToLocNm, soldToLocNm);
        ZYPEZDItemValueSetter.setValue(this.sldToAddlLocNm, soldToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(this.sldToFirstLineAddr, soldToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToScdLineAddr, soldToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToThirdLineAddr, soldToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToFrthLineAddr, soldToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToFirstRefCmntTxt, soldToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(this.sldToScdRefCmntTxt, soldToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(this.sldToCtyAddr, soldToCtyAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToStCd, soldToStCd);
        ZYPEZDItemValueSetter.setValue(this.sldToProvNm, soldToProvNm);
        ZYPEZDItemValueSetter.setValue(this.sldToCntyNm, soldToCntyNm);
        ZYPEZDItemValueSetter.setValue(this.sldToPostCd, soldToPostCd);
        ZYPEZDItemValueSetter.setValue(this.sldToCtryCd, soldToCtryCd);
        ZYPEZDItemValueSetter.setValue(this.sldToAcctNm, soldToAcctNm);

        this.adminPsnCd.clear();
        ZYPEZDItemValueSetter.setValue(this.rddDt, NWXC412001.getValidDate(this.nwai4120_01.erlstDelyTs.getValue()));
        if (dsOrdTpProcDfn != null) {
            ZYPEZDItemValueSetter.setValue(this.frtCondCd, dsOrdTpProcDfn.frtCondCd);
        } else {
            this.frtCondCd.clear();
        }
        this.spclHdlgTpCd.clear();
        this.carrCd.clear();
        this.carrSvcLvlCd.clear();
        if (dsOrdTpProcDfn != null) {
            ZYPEZDItemValueSetter.setValue(this.shpgSvcLvlCd, dsOrdTpProcDfn.defShpgSvcLvlCd);
        } else {
            this.shpgSvcLvlCd.clear();
        }
        FRT_CONDTMsg frtCond = new FRT_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(frtCond.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(frtCond.frtCondCd, this.frtCondCd);
        if (ZYPCommonFunc.hasValue(this.frtCondCd)) {
            frtCond = (FRT_CONDTMsg) S21CodeTableAccessor.findByKey(frtCond);
        }
        if (frtCond != null) {
            ZYPEZDItemValueSetter.setValue(this.frtChrgToCd, frtCond.frtChrgToCd);
            ZYPEZDItemValueSetter.setValue(this.frtChrgMethCd, frtCond.frtChrgMethCd);
        } else {
            this.frtChrgToCd.clear();
            this.frtChrgMethCd.clear();
        }
        this.carrAcctNum.clear();
        //QC#18013
        //ZYPEZDItemValueSetter.setValue(this.addPmtTermCashDiscCd, (String)this.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_DEF_PMT_TERM_CASH_DISC_CD.name()));
        //this.dsPmtMethCd.clear();
        Map<String, Object> net30Map = NWXC412001.getNet30(glblCmpyCd, CPO_SRC_TP.SOM, INTERFACE_ID.NWAI4120.name(), this.nwai4120_01.netThrtyIndSomTxt.getValue());
        if (net30Map != null) {
            ZYPEZDItemValueSetter.setValue(this.addPmtTermCashDiscCd, (String) net30Map.get("TRGT_ATTRB_TXT_01"));
            ZYPEZDItemValueSetter.setValue(this.dsPmtMethCd, (String) net30Map.get("TRGT_ATTRB_TXT_02"));
        }
        Map<String, Object> chkOrdMap = NWXC412001.getCheckOrder(glblCmpyCd, CPO_SRC_TP.SOM, INTERFACE_ID.NWAI4120.name(), this.nwai4120_01.chkOrdIndSomTxt.getValue());
        if (chkOrdMap != null) {
            ZYPEZDItemValueSetter.setValue(this.addPmtTermCashDiscCd, (String) chkOrdMap.get("TRGT_ATTRB_TXT_01"));
            ZYPEZDItemValueSetter.setValue(this.dsPmtMethCd, (String) chkOrdMap.get("TRGT_ATTRB_TXT_02"));
        }
        if (!ZYPCommonFunc.hasValue(this.addPmtTermCashDiscCd)) {
            ZYPEZDItemValueSetter.setValue(this.addPmtTermCashDiscCd, (String)this.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_DEF_PMT_TERM_CASH_DISC_CD.name()));
            this.dsPmtMethCd.clear();
        }
        this.prePmtChkNum.clear();
        this.prePmtAmt.clear();
        this.prePmtTpCd.clear();
        ZYPEZDItemValueSetter.setValue(this.prcBaseDt, NWXC412001.getValidDate(this.nwai4120_01.cratTs.getValue()));
        if (ZYPCommonFunc.hasValue(this.nwai4120_01.negoDealAmt) && BigDecimal.ZERO.compareTo(this.nwai4120_01.negoDealAmt.getValue()) != 0) {
            ZYPEZDItemValueSetter.setValue(this.negoDealAmt, this.nwai4120_01.negoDealAmt);
        } else {
            this.negoDealAmt.clear();
        }
        
        List<SomDsImptOrdConfigBean> somDsImptOrdConfigBeanList = this.dsImptOrdConfigBeanList;
        String tmpPrcCatgCd = null;
        if (this.nwai4120_01.trxTpTxt.getValue().equals(NWAB412001Constant.TRX_TP_TXT_RENTAL)) {
            if (ZYPCommonFunc.hasValue(this.nwai4120_01.somPrcListId)) {
                tmpPrcCatgCd = this.nwai4120_01.somPrcListId.getValue().toPlainString();
            } else if (ZYPCommonFunc.hasValue(this.nwai4120_01.prcListDescTxt)) {
                tmpPrcCatgCd = NWXC412001.getPrcCatgCdFromName(glblCmpyCd, this.nwai4120_01.prcListDescTxt.getValue());
            } else {
                if (somDsImptOrdConfigBeanList.size() > 0) {
                    if (ZYPCommonFunc.hasValue(somDsImptOrdConfigBeanList.get(0).getPrcCatgCd())) {
                        tmpPrcCatgCd = somDsImptOrdConfigBeanList.get(0).getPrcCatgCd();
                    }
                }
                if (ZYPCommonFunc.hasValue(tmpPrcCatgCd)) {
                    if (somDsImptOrdConfigBeanList.size() > 0) {
                        if (ZYPCommonFunc.hasValue(somDsImptOrdConfigBeanList.get(0).getFlPrcListCd())) {
                            tmpPrcCatgCd = somDsImptOrdConfigBeanList.get(0).getFlPrcListCd();
                        }
                    }
                }
            }
        } else {
            if (ZYPCommonFunc.hasValue(this.nwai4120_01.bidPrcListNm)) {
                tmpPrcCatgCd = NWXC412001.getPrcCatgCdFromName(glblCmpyCd, this.nwai4120_01.bidPrcListNm.getValue());
                if (ZYPCommonFunc.hasValue(prcCatgCd)) {
                    ZYPEZDItemValueSetter.setValue(this.prcCatgCd, prcCatgCd);
                }
            //} else if (ZYPCommonFunc.hasValue(this.nwai4120_01.somPrcListId)) {
            //    tmpPrcCatgCd = this.nwai4120_01.somPrcListId.getValue().toPlainString();
            //} else if (ZYPCommonFunc.hasValue(this.nwai4120_01.prcListDescTxt)) {
            //    tmpPrcCatgCd = NWXC412001.getPrcCatgCdFromName(glblCmpyCd, this.nwai4120_01.prcListDescTxt.getValue());
            } else {
                if (somDsImptOrdConfigBeanList.size() > 0) {
                    if (ZYPCommonFunc.hasValue(somDsImptOrdConfigBeanList.get(0).getPrcCatgCd())) {
                        tmpPrcCatgCd = somDsImptOrdConfigBeanList.get(0).getPrcCatgCd();
                    }
                }
                if (ZYPCommonFunc.hasValue(tmpPrcCatgCd)) {
                    if (somDsImptOrdConfigBeanList.size() > 0) {
                        if (ZYPCommonFunc.hasValue(somDsImptOrdConfigBeanList.get(0).getFlPrcListCd())) {
                            tmpPrcCatgCd = somDsImptOrdConfigBeanList.get(0).getFlPrcListCd();
                        }
                    }
                }
            }
        }

        if (ZYPCommonFunc.hasValue(tmpPrcCatgCd)) {
            ZYPEZDItemValueSetter.setValue(this.prcCatgCd, tmpPrcCatgCd);
        } else {
            // default price list
            ZYPEZDItemValueSetter.setValue(this.prcCatgCd, getDefaultPrcCatgCd());
        }

        String flPrcListCd = null;
        if (somDsImptOrdConfigBeanList.size() > 0) {
            if (ZYPCommonFunc.hasValue(somDsImptOrdConfigBeanList.get(0).getFlPrcListCd())) {
                flPrcListCd = somDsImptOrdConfigBeanList.get(0).getFlPrcListCd().toString();
            }
        }
        ZYPEZDItemValueSetter.setValue(this.flPrcListCd, flPrcListCd);
        if (!ZYPCommonFunc.hasValue(this.flPrcListCd)) {

            // default price list
            ZYPEZDItemValueSetter.setValue(this.flPrcListCd, getDefaultPrcCatgCd());
        }

        if (ZYPCommonFunc.hasValue(this.nwai4120_01.prcContrNum) && !"0".equals(this.nwai4120_01.prcContrNum)) {
            ZYPEZDItemValueSetter.setValue(this.prcContrNum, this.nwai4120_01.prcContrNum.getValue());
        } else {
            this.prcContrNum.clear();
        }
        if (ZYPCommonFunc.hasValue(this.nwai4120_01.somCsmpNum) && !"0".equals(this.nwai4120_01.somCsmpNum)) {
            ZYPEZDItemValueSetter.setValue(this.csmpContrNum, this.nwai4120_01.somCsmpNum.getValue());
            Map<String, Object> dlrRefNumMap = NWXC412001.getCSANumberByAcctCSMPNum(glblCmpyCd, this.sellToCustCd.getValue(), this.csmpContrNum.getValue(), this.slsDt);
            if (dlrRefNumMap != null) {
                ZYPEZDItemValueSetter.setValue(this.dlrRefNum, (String) dlrRefNumMap.get("DLR_REF_NUM"));
                pCsmpPrcListCd = (String) dlrRefNumMap.get("PRC_CATG_CD");
            } else {
                this.dlrRefNum.clear();
            }
        } else {
            //QC#24240
        	if (ZYPCommonFunc.hasValue(this.nwai4120_01.csmpIndSomTxt) && "YES".equals(this.nwai4120_01.csmpIndSomTxt.getValue())) {
                Map<String, Object> dlrRefNumMap = NWXC412001.getCSANumberByAcctCSMPNumNull(glblCmpyCd, this.sellToCustCd.getValue(), this.slsDt);
                if (dlrRefNumMap != null) {
                    ZYPEZDItemValueSetter.setValue(this.dlrRefNum, (String) dlrRefNumMap.get("DLR_REF_NUM"));
                    pCsmpPrcListCd = (String) dlrRefNumMap.get("PRC_CATG_CD");
                    this.csmpContrNum.clear();
                } else {
                    this.csmpContrNum.clear();
                    this.dlrRefNum.clear();
                }
        	} else {
                this.csmpContrNum.clear();
                this.dlrRefNum.clear();
        	}
        }

        this.ordSgnDt.clear();
        ZYPEZDItemValueSetter.setValue(this.aquNum, this.ordSrcRefNum);
        
        if (!getCacheSlsRepTocCdMap().containsKey(this.nwai4120_01.somSlsRepId.getValue())) {
            Map<String, Object> slsRepTocCdMap = NWXC412001.getSlsRepTocByResourceId(glblCmpyCd, this.nwai4120_01.somSlsRepId.getValue(), this.slsDt);
            if (slsRepTocCdMap != null) {
                ZYPEZDItemValueSetter.setValue(this.slsRepTocCd, (String) slsRepTocCdMap.get("SLS_REP_TOC_CD"));
                getCacheSlsRepTocCdMap().put(this.nwai4120_01.somSlsRepId.getValue(), slsRepTocCdMap);
            } else {
                getCacheSlsRepTocCdMap().put(this.nwai4120_01.somSlsRepId.getValue(), new HashMap<String, Object>());
            }
        } else {
            Map<String, Object> slsRepTocCdMap = getCacheSlsRepTocCdMap().get(this.nwai4120_01.somSlsRepId.getValue());
            ZYPEZDItemValueSetter.setValue(this.slsRepTocCd, (String) slsRepTocCdMap.get("SLS_REP_TOC_CD"));
        }
        this.loanPerDaysAot.clear();
        String leaseCmpyPoNum = null;
        String leasePmtFreqCd = null;
        BigDecimal leaseTermMthAot = BigDecimal.ZERO;
        BigDecimal leaseTotPmtAmt = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(this.dsOrdCatgCd) && this.dsOrdCatgCd.getValue().equals(DS_ORD_CATG.LEASE_CSA)) {
            leaseCmpyPoNum = S21StringUtil.subStringByLength(this.nwai4120_01.leasePrchOrderTxt.getValue(), 0, 35);
            leasePmtFreqCd = NWAB412001Constant.LEASE_PMT_FREQ_MONTHLY;
            leaseTermMthAot = this.nwai4120_01.somLeaseTermAot.getValue();
            leaseTotPmtAmt = this.nwai4120_01.leasePmtAmt.getValue();
        }
        ZYPEZDItemValueSetter.setValue(this.leaseCmpyPoNum, leaseCmpyPoNum);
        this.leaseTermCd.clear();
        ZYPEZDItemValueSetter.setValue(this.leasePmtFreqCd, leasePmtFreqCd);
        ZYPEZDItemValueSetter.setValue(this.leaseTermMthAot, leaseTermMthAot);
        this.ordLogTpCd.clear();
        this.crRebilRsnCatgCd.clear();
        this.origOrdNum.clear();
        ZYPEZDItemValueSetter.setValue(this.sendInvFlg, ZYPConstant.FLG_ON_Y);
        this.reBillPairCpoOrdNum.clear();
        ZYPEZDItemValueSetter.setValue(this.ordHdrEdtblFlg, ZYPConstant.FLG_ON_Y);
        //QC#18023
        //ZYPEZDItemValueSetter.setValue(this.leasePrchOptCd, NWXC412001.getLeasePrchOptCd(glblCmpyCd, this.cpoSrcTpCd.getValue(), this.nwai4120_01.interfaceId.getValue(), this.nwai4120_01.leaseTpTxt.getValue()));
        Map<String, Object> realFairIndMap = NWXC412001.getRealFairInd(glblCmpyCd, CPO_SRC_TP.SOM, INTERFACE_ID.NWAI4120.name(), this.nwai4120_01.realFairIndSomTxt.getValue());
        if (realFairIndMap != null) {
            ZYPEZDItemValueSetter.setValue(this.leasePrchOptCd, (String) realFairIndMap.get("TRGT_ATTRB_TXT_01"));
        }
        Map<String, Object> checkWithAgreeMap = NWXC412001.getCheckWithAgree(glblCmpyCd, CPO_SRC_TP.SOM, INTERFACE_ID.NWAI4120.name(), this.nwai4120_01.chkAgmtIndSomTxt.getValue());
        if (checkWithAgreeMap != null) {
            ZYPEZDItemValueSetter.setValue(this.leasePrchOptCd, (String) checkWithAgreeMap.get("TRGT_ATTRB_TXT_01"));
        }
        if (!ZYPCommonFunc.hasValue(this.leasePrchOptCd)) {
            ZYPEZDItemValueSetter.setValue(this.leasePrchOptCd, NWXC412001.getLeasePrchOptCd(glblCmpyCd, this.cpoSrcTpCd.getValue(), this.nwai4120_01.interfaceId.getValue(), this.nwai4120_01.leaseTpTxt.getValue()));
        }
        this.invCmntTxt.clear();
        ZYPEZDItemValueSetter.setValue(this.leaseTotPmtAmt, leaseTotPmtAmt);
        if (S21StringUtil.isEquals(this.nwai4120_01.dclnMaintIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
            ZYPEZDItemValueSetter.setValue(this.dclnSvcCd, ZYPConstant.FLG_ON_1);
        } else {
            ZYPEZDItemValueSetter.setValue(this.dclnSvcCd, ZYPConstant.FLG_OFF_0);
        }
        ZYPEZDItemValueSetter.setValue(this.maintOnlyFlg, ZYPConstant.FLG_OFF_N); // QC#16266

        for (SomDsImptOrdSomApvlBean apvlBean : dsImptOrdSomApvlBeanList) {
            apvlBean.doImptMapping(glblCmpyCd, salesDate);
        }

        for (SomDsImptOrdSomPrftBean prftBean : dsImptOrdSomPrftBeanList) {
            prftBean.doImptMapping(glblCmpyCd, salesDate);
        }

        //this.dsImptOrdSlsCrBean.doImptMapping(glblCmpyCd, salesDate);
        //this.dsImptOrdIstlInfoBean.doImptMapping(glblCmpyCd, salesDate);
        this.dsImptOrdDelyInfoBean.doImptMapping(glblCmpyCd, salesDate);
//        this.dsImptOrdSiteSrvyBean.doImptMapping(glblCmpyCd, salesDate); // 2017/09/13 QC#21084 Del

        for (SomDsImptOrdCtacPsnBean ctacPsnBean : this.dsImptOrdCtacPsnBeanList) {
            ctacPsnBean.doImptMapping(glblCmpyCd, salesDate);
        }

        // for (LinkedHashMap<String, List<SomDsImptOrdConfigBean>>
        // configGroupMap : this.dsImptOrdConfigBeanMap.values()) {
        // for (List<SomDsImptOrdConfigBean> configGroupList :
        // configGroupMap.values()) {
        // for (SomDsImptOrdConfigBean configBean : configGroupList) {
        // if (!configBean.doImptMapping(glblCmpyCd, salesDate)) {
        // isSuccess = false;
        // }
        // }
        // }
        // }

        // import order mapping
        for (SomDsImptOrdConfigBean configBean : this.dsImptOrdConfigBeanList) {
            if (!configBean.doImptMapping(glblCmpyCd, salesDate)) {
                isSuccess = false;
            }
        }
        this.dsImptOrdSlsCrBean.doImptMapping(glblCmpyCd, salesDate);
        this.dsImptOrdIstlInfoBean.doImptMapping(glblCmpyCd, salesDate);

        // import service price mapping
        for (SomDsImptSvcDtlBean svcDtl : this.dsImptSvcDtlBeanList) {
            if (!svcDtl.doImptMapping(glblCmpyCd, salesDate)) {
                isSuccess = false;
            }
        }

        // extension mapping
        isSuccess = doImptMppingExt(glblCmpyCd, salesDate);

        return isSuccess;
    }

    private boolean doImptMppingExt(String glblCmpyCd, String slsDt) {

        doImptOrdConfigMppingExt(glblCmpyCd, slsDt);

        doImptOrdMppingExt(glblCmpyCd, slsDt);

        doImptOrdDtlMppingExt(glblCmpyCd, slsDt);

        doImptOrdRtrnDtlMppingExt(glblCmpyCd, slsDt);

        doImptSvcDtlMappingExt(glblCmpyCd, slsDt);

        return true;
    }

    private void doImptOrdMppingExt(String glblCmpyCd, String slsDt) {

        setDclnSvcCdForHeader();
    }

    private void setDclnSvcCdForHeader() {

        boolean exsistOutbound = false;
        for (SomDsImptOrdConfigBean config : this.dsImptOrdConfigBeanList) {

            if (S21StringUtil.isEquals(config.configCatgCd.getValue(), CONFIG_CATG.OUTBOUND)) {

                exsistOutbound = true;
                if (!S21StringUtil.isEquals(config.dclnSvcCd.getValue(), ZYPConstant.FLG_ON_1)) {

                    ZYPEZDItemValueSetter.setValue(this.dclnSvcCd, ZYPConstant.FLG_OFF_0);
                    return;
                }
            }
        }

        if (exsistOutbound) {

            ZYPEZDItemValueSetter.setValue(this.dclnSvcCd, ZYPConstant.FLG_ON_1);
        }
    }

    private void doImptOrdConfigMppingExt(String glblCmpyCd, String slsDt) {

        // out bound
        int dsOrdPosnNumIndex = 0;
        for (SomDsImptOrdConfigBean config : this.dsImptOrdConfigBeanList) {

            if (S21StringUtil.isEquals(config.configCatgCd.getValue(), CONFIG_CATG.OUTBOUND)) {

                ZYPEZDItemValueSetter.setValue(config.dsOrdPosnNum, String.valueOf(dsOrdPosnNumIndex + 1));

                dsOrdPosnNumIndex++;
            }
        }

        // in bound
        dsOrdPosnNumIndex = 0;
        for (SomDsImptOrdConfigBean config : this.dsImptOrdConfigBeanList) {

            if (S21StringUtil.isEquals(config.configCatgCd.getValue(), CONFIG_CATG.INBOUND)) {

                ZYPEZDItemValueSetter.setValue(config.dsOrdPosnNum, String.valueOf(dsOrdPosnNumIndex + 1));

                dsOrdPosnNumIndex++;
            }
        }

        for (SomDsImptOrdConfigBean config : this.dsImptOrdConfigBeanList) {

            ZYPEZDItemValueSetter.setValue(config.configTpCd, getConfigTpCd(glblCmpyCd, slsDt, config));
        }

        // address label text
        setAddrLbTxt(CONFIG_CATG.OUTBOUND);
        setAddrLbTxt(CONFIG_CATG.INBOUND);

        // decline service code
        //setDclnSvcCd();

    }

    private void setAddrLbTxt(String configCatgCd) {

        String maxAddrLblTxt = "";
        String currentAddrLblTxt = "";
        for (SomDsImptOrdConfigBean config : this.dsImptOrdConfigBeanList) {

            if (!S21StringUtil.isEquals(config.configCatgCd.getValue(), configCatgCd)) {

                continue;
            }

            currentAddrLblTxt = config.addrLbTxt.getValue();
            if (S21StringUtil.isEmpty(currentAddrLblTxt)) {

                continue;
            }

            if (maxAddrLblTxt.compareTo(currentAddrLblTxt) < 0) {

                maxAddrLblTxt = currentAddrLblTxt;
            }

        }
        currentAddrLblTxt = "";
        for (SomDsImptOrdConfigBean config : this.dsImptOrdConfigBeanList) {

            if (!S21StringUtil.isEquals(config.configCatgCd.getValue(), configCatgCd)) {

                continue;
            }
            if (ZYPCommonFunc.hasValue(config.addrLbTxt)) {

                continue;
            }

            String currentAddr;
            if (S21StringUtil.isEquals(configCatgCd, CONFIG_CATG.OUTBOUND)) {

                currentAddr = S21StringUtil.concatStrings(config.nwai4120_12.somShipToAddr_01.getValue(), config.nwai4120_12.somShipToAddr_02.getValue(), config.nwai4120_12.somCityAddr.getValue(), config.nwai4120_12.somStCd.getValue());
            } else {

                if (NWAB412001Constant.CONFIG_TYPE.OCE_PROMOTION_RMA.equals(config.configType)) {

                    if (config.nwai4120_14List != null && !config.nwai4120_14List.isEmpty()) {

                        currentAddr = S21StringUtil.concatStrings(config.nwai4120_14List.get(0).somPrmoSerNum.getValue());
                    } else {

                        currentAddr = "";
                    }

                } else {

                    if (ZYPCommonFunc.hasValue(config.nwai4120_05.somDescSerNum)) {
	                    currentAddr = S21StringUtil.concatStrings(config.nwai4120_05.somSoftCostId.getValue(), config.nwai4120_05.somDescSerNum.getValue());
                    } else if (config.serIf06TMsg != null && ZYPCommonFunc.hasValue(config.serIf06TMsg.somDescSerNum)) {
	                    currentAddr = S21StringUtil.concatStrings(config.nwai4120_05.somSoftCostId.getValue(), config.serIf06TMsg.somDescSerNum.getValue());
                    } else {
	                    currentAddr = S21StringUtil.concatStrings(config.nwai4120_05.somSoftCostId.getValue(), "");
                    }
                }
            }
            for (SomDsImptOrdConfigBean config2 : this.dsImptOrdConfigBeanList) {

                if (!S21StringUtil.isEquals(config2.configCatgCd.getValue(), configCatgCd)) {
                    continue;
                }
                if (!ZYPCommonFunc.hasValue(config2.addrLbTxt)) {
                    continue;
                }

                String lbAddr;
                if (S21StringUtil.isEquals(configCatgCd, CONFIG_CATG.OUTBOUND)) {

                    lbAddr = S21StringUtil.concatStrings(config2.nwai4120_12.somShipToAddr_01.getValue(), config2.nwai4120_12.somShipToAddr_02.getValue(), config2.nwai4120_12.somCityAddr.getValue(), config2.nwai4120_12.somStCd.getValue());

                } else {

                    //QC#15539
                    //if (NWAB412001Constant.CONFIG_TYPE.OCE_PROMOTION_RMA.equals(config.configType)) {
                    if (NWAB412001Constant.CONFIG_TYPE.OCE_PROMOTION_RMA.equals(config2.configType)) {

                        if (config2.nwai4120_14List == null || config2.nwai4120_14List.isEmpty()) {

                            continue;
                        }
                        lbAddr = S21StringUtil.concatStrings(config2.nwai4120_14List.get(0).somPrmoSerNum.getValue());
                    } else {

                        if (ZYPCommonFunc.hasValue(config2.nwai4120_05.somDescSerNum)) {
	                        lbAddr = S21StringUtil.concatStrings(config2.nwai4120_05.somSoftCostId.getValue(), config2.nwai4120_05.somDescSerNum.getValue());
                        } else if (config2.serIf06TMsg != null && ZYPCommonFunc.hasValue(config2.serIf06TMsg.somDescSerNum)) {
	                        lbAddr = S21StringUtil.concatStrings(config2.nwai4120_05.somSoftCostId.getValue(), config2.serIf06TMsg.somDescSerNum.getValue());
                        } else {
	                        lbAddr = S21StringUtil.concatStrings(config2.nwai4120_05.somSoftCostId.getValue(), "");
                        }
                    }
                }
                if (S21StringUtil.isEquals(currentAddr, lbAddr)) {

                    ZYPEZDItemValueSetter.setValue(config.addrLbTxt, config2.addrLbTxt);
                    break;
                }
            }

            if (!ZYPCommonFunc.hasValue(config.addrLbTxt)) {

                if (S21StringUtil.isEquals(configCatgCd, CONFIG_CATG.OUTBOUND)) {

                    maxAddrLblTxt = NWXC412001.getNextAddrLbTxtForOutbound(maxAddrLblTxt);
                    ZYPEZDItemValueSetter.setValue(config.addrLbTxt, maxAddrLblTxt);
                } else {
                    
                    maxAddrLblTxt = NWXC412001.getNextAddrLbTxtForInbound(maxAddrLblTxt);
                    ZYPEZDItemValueSetter.setValue(config.addrLbTxt, maxAddrLblTxt);
                }
            }
        }
    }

    private String getConfigTpCd(String glblCmpyCd, String slsDt, SomDsImptOrdConfigBean config) {

        if (S21StringUtil.isEquals(config.configCatgCd.getValue(), CONFIG_CATG.OUTBOUND)) {
            //QC#17768
//            if (ZYPCommonFunc.hasValue(config.svcConfigMstrPk)) {
//
//                for (NWAI4120_13TMsg nwai4120_13 : config.nwai4120_13List) {
//
//                    if (!ZYPCommonFunc.hasValue(nwai4120_13.somSerNum)) {
//
//                        continue;
//                    }
//
//                    return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.ADD_TO_CONFIG;
//                }
//            }
            if (config.isAddAccessoryToExistingConfigurationFlag) {
                return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.ADD_TO_CONFIG;
            } else if (config.isExistingConfigurationFlag) {
                return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.EXISTING;
            }

            return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.NEW;

        } else {

            if (CONFIG_TYPE.TRADE_IN_RMA.equals(config.configType)) {
                
                //Non Canon Equipment
            	//QC#24101
                //String somNonCanonEquipment = (String)this.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_TRADE_IN_NON_CANON.name());
                //if (ZYPCommonFunc.hasValue(somNonCanonEquipment) 
                //        && !somNonCanonEquipment.equals(config.nwai4120_05.somMdlTxt.getValue()) //SOM_TRADE_IN_NON_CANON
                //        && ZYPCommonFunc.hasValue(config.nwai4120_05.somDescSerNum)) {
                if (ZYPCommonFunc.hasValue(config.nwai4120_05.somDescSerNum)) {
                    
                    //QC#21236
                    List<Map<String, Object>> existslist = NWXC412001.getConfigMasterDetailList(
                            glblCmpyCd, 
                            config.nwai4120_05.somDescSerNum.getValue(), 
                            slsDt);
                    if (existslist != null && existslist.size() > 0) {
                        return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.RETURN_EXISTING_IB;
                    }
                    return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.RETURN_NEW;
                    
                //} else if (ZYPCommonFunc.hasValue(somNonCanonEquipment) 
                //    && somNonCanonEquipment.equals(config.nwai4120_05.somMdlTxt.getValue())) {
                //    
                //    return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.RETURN_NEW;
                //    
                }
            }

            for (SomDsImptOrdRtrnDtlBean detail : config.dsImptOrdDtlRtrnBeanList) {

                if (detail.nwai4120_06 != null) {

                    if (ZYPCommonFunc.hasValue(detail.nwai4120_06.somDescSerNum)) {

                        //QC#21236
                        List<Map<String, Object>> existslist = NWXC412001.getConfigMasterDetailList(
                                glblCmpyCd, 
                                detail.nwai4120_06.somDescSerNum.getValue(), 
                                slsDt);
                        if (existslist != null && existslist.size() > 0) {
                            return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.RETURN_EXISTING_IB;
                        }
                        return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.RETURN_NEW;
                    }
                }

            }

            return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.RETURN_NEW;
        }
    }

//    private void setDclnSvcCd() {
//        for (SomDsImptOrdConfigBean config : this.dsImptOrdConfigBeanList) {
//            ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_OFF_0);
//            for (SomDsImptOrdDtlBean dtl : config.dsImptOrdDtlBeanList) {
//                BigDecimal dtlPk = dtl.dsImptOrdDtlPk.getValue();
//                if (dtlPk == null) {  //QC#15539
//                    continue;
//                }
//                boolean noServiceFlag = false;
//                for (SomDsImptSvcDtlBean svcDtl : config.dsImptOrdBean.dsImptSvcDtlBeanList) {
//                    for (SomDsImptSvcConfigRefBean svcConfRef : svcDtl.dsImptSvcConfigRefBeanList) {
//                        BigDecimal refDtlPk = svcConfRef.dsImptOrdDtlPk.getValue();
//                        if (refDtlPk == null) {  //QC#15539
//                            continue;
//                        }
//                        if (dtlPk.compareTo(refDtlPk) == 0) {
//                            noServiceFlag = true;
//                            if (svcConfRef.shellType.equals(NWAB412001Constant.SHELL_TYPE.COPIER)) {
//                                if (S21StringUtil.isEquals(NWAB412001Constant.FLG.YES.name(), svcConfRef.nwai4120_18.dclnMaintIndSomTxt.getValue())) {
//                                    ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_ON_1);
//                                }
//                            } else if (svcConfRef.shellType.equals(NWAB412001Constant.SHELL_TYPE.NON_COPIER)) {
//                                if (S21StringUtil.isEquals(NWAB412001Constant.FLG.YES.name(), svcConfRef.nwai4120_19.dclnMaintIndSomTxt.getValue())) {
//                                    ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_ON_1);
//                                }
//                            }
//                        }
//                    }
//                    for (SomDsImptSvcAddlBaseBean svcAddlBase : svcDtl.dsImptSvcAddlBaseBeanList) {
//                        BigDecimal refDtlPk = svcAddlBase.dsImptOrdDtlPk.getValue();
//                        if (dtlPk.compareTo(refDtlPk) == 0) {
//                            noServiceFlag = true;
//                            if (S21StringUtil.isEquals(NWAB412001Constant.FLG.YES.name(), svcAddlBase.nwai4120_17.dclnMaintIndSomTxt.getValue())) {
//                                ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_ON_1);
//                            }
//                        }
//                    }
//                    for (SomDsImptSvcAddlChrgBean svcAddlChr : svcDtl.dsImptSvcAddlChrgBeanList) {
//                        BigDecimal refDtlPk = svcAddlChr.dsImptOrdDtlPk.getValue();
//                        if (dtlPk.compareTo(refDtlPk) == 0) {
//                            noServiceFlag = true;
//                            if (S21StringUtil.isEquals(NWAB412001Constant.FLG.YES.name(), svcAddlChr.nwai4120_20.dclnMaintIndSomTxt.getValue())) {
//                                ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_ON_1);
//                            }
//                        }
//                    }
//                }
//                
//                if (!noServiceFlag) {
//                    ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_ON_1);
//                }
//                
//            }
//        }
//    }

    private void doImptOrdDtlMppingExt(String glblCmpyCd, String slsDt) {

        // position number
        // order source reference line number
        for (SomDsImptOrdConfigBean config : this.dsImptOrdConfigBeanList) {

            if (S21StringUtil.isEquals(config.configCatgCd.getValue(), CONFIG_CATG.OUTBOUND)) {

                int ordSrcRefLineNumIndex = 0;
                for (SomDsImptOrdDtlBean detail : config.dsImptOrdDtlBeanList) {

                    ZYPEZDItemValueSetter.setValue(detail.dsOrdPosnNum, config.dsOrdPosnNum);
                    ZYPEZDItemValueSetter.setValue(detail.ordSrcRefLineNum, String.valueOf(ordSrcRefLineNumIndex + 1));
                    ordSrcRefLineNumIndex++;
                }
            }
        }
    }

    private void doImptOrdRtrnDtlMppingExt(String glblCmpyCd, String slsDt) {

        // position number
        // order source reference line number
        for (SomDsImptOrdConfigBean config : this.dsImptOrdConfigBeanList) {

            if (S21StringUtil.isEquals(config.configCatgCd.getValue(), CONFIG_CATG.INBOUND)) {

                int ordSrcRefLineNumIndex = 0;
                for (SomDsImptOrdRtrnDtlBean detail : config.dsImptOrdDtlRtrnBeanList) {

                    ZYPEZDItemValueSetter.setValue(detail.dsOrdPosnNum, config.dsOrdPosnNum);
                    ZYPEZDItemValueSetter.setValue(detail.ordSrcRefLineNum, String.valueOf(ordSrcRefLineNumIndex + 1));
                    ordSrcRefLineNumIndex++;
                }
            }
        }
    }

    private void doImptSvcDtlMappingExt(String glblCmpyCd, String slsDt2) {

        int dsImptSvcLineNumIndex = 0;
        for (SomDsImptSvcDtlBean svcDtail : this.dsImptSvcDtlBeanList) {

            if (!ZYPCommonFunc.hasValue(svcDtail.dsImptSvcLineNum)) {

                ZYPEZDItemValueSetter.setValue(svcDtail.dsImptSvcLineNum, BigDecimal.valueOf(dsImptSvcLineNumIndex + 1));
                dsImptSvcLineNumIndex++;
            }
        }
    }

    @Override
    public void addErrorInfo(NWXC220001ErrorInfo errorInfo) {
        this.errInfoList.add(new NWAB412001ErrorInfo(this.nwai4120_01.transactionId.getValue(), this.nwai4120_01.unitId.getValue(), this.nwai4120_01.seqNumber.getValue(), errorInfo));
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

        for (SomDsImptOrdConfigBean configBean : this.dsImptOrdConfigBeanList) {
            errList.addAll(configBean.getErrorInfo());
        }

        return errList;
    }

    @Override
    public boolean hasError() {
        return (this.getErrorInfo().size() > 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Error Info]\n\n");
        sb.append("[nwai4120_01]\n");
        sb.append(this.nwai4120_01.toString()).append("\n");
        sb.append("[this]\n");
        sb.append(this.toString()).append("\n");

        return sb.toString();
    }

    public static void storeConfigData(SomDsImptOrdBean dsImptOrdBean, 
            NWAI4120_12TMsg if12TMsg, 
            List<NWAI4120_10TMsg> nwai4120_10List, 
            List<NWAI4120_13TMsg> nwai4120_13List, 
            List<NWAI4120_14TMsg> nwai4120_14List,
            Map<BigDecimal, BigDecimal> cacheMap) {

        SomDsImptOrdConfigBean dsImptOrdConfigBean = new SomDsImptOrdConfigBean(CONFIG_TYPE.REGULAR_SHIP_CONFIG, dsImptOrdBean, if12TMsg);
        List<NWAI4120_10TMsg> if10TMsgList = getNWAI4120_10TMsgByConfigKey(dsImptOrdConfigBean.configKey, nwai4120_10List);
        //Main Item(SOM_MDSE_TP_TXT=10)
        BigDecimal mainMachineQty = BigDecimal.ONE;
        BigDecimal somSortOrdNum = BigDecimal.ZERO;
        boolean isExistsMachine = false;
        for (NWAI4120_10TMsg if10TMsg : if10TMsgList) {
            if (COA_MDSE_TP.MACHINE.equals(if10TMsg.somMdseTpTxt.getValue()) || "11".equals(if10TMsg.somMdseTpTxt.getValue())) {
                if (ZYPCommonFunc.hasValue(if10TMsg.somOrdQty)) {
                    mainMachineQty = if10TMsg.somOrdQty.getValue().abs();
                    somSortOrdNum = if10TMsg.somSortOrdNum.getValue();
                    isExistsMachine = true;
                    break;
                }
            }
        }
        boolean isExistsSubSrv = false;
        for (NWAI4120_10TMsg if10TMsg : if10TMsgList) {
            if ("50".equals(if10TMsg.somMdseTpTxt.getValue())) {
                isExistsSubSrv = true;
                break;
            }
        }
        
        // Add Start 2018/05/10 QC#20343
        String glblCmpyCd = dsImptOrdBean.glblCmpyCd.getValue();
        // Add End 2018/05/10 QC#20343

        // Main Item Qty Loop
        for (int machineIndex = 0; machineIndex < mainMachineQty.intValue(); machineIndex++) {
            dsImptOrdConfigBean = new SomDsImptOrdConfigBean(CONFIG_TYPE.REGULAR_SHIP_CONFIG, dsImptOrdBean, if12TMsg);
            dsImptOrdBean.addSomDsImptOrdConfigBean(dsImptOrdConfigBean);
            dsImptOrdBean.lastShipConfigBean = dsImptOrdConfigBean;
            
            if10TMsgList = getNWAI4120_10TMsgByConfigKey(dsImptOrdConfigBean.configKey, nwai4120_10List);
            
            List<NWAI4120_13TMsg> if13TMsgList = getNWAI4120_13TMsgByConfigKey(dsImptOrdConfigBean.configKey, nwai4120_13List);
            dsImptOrdConfigBean.nwai4120_13List.addAll(if13TMsgList);
            if (!isExistsMachine && if13TMsgList != null && if13TMsgList.size() > 0) { //add Accessory to existing configuration
                //QC#17768
                dsImptOrdConfigBean.addExsistingCondigurationListForAddAcc(); //set configuration list from serial
                //dsImptOrdConfigBean.isAddAccessoryToExistingConfigurationFlag = true;
                //dsImptOrdConfigBean.isExistingConfigurationFlag = false;
            //} else if (isExistsMachine && if13TMsgList != null && if13TMsgList.size() > 0) {
            //QC#25324
            //} else if (isExistsMachine && if10TMsgList != null && if10TMsgList.size() > 0) {
            } else if (if10TMsgList != null && if10TMsgList.size() > 0) {
                //QC#17768
                dsImptOrdConfigBean.addExsistingCondigurationListForPreOwned(if10TMsgList); //set configuration list from serial
                //dsImptOrdConfigBean.isAddAccessoryToExistingConfigurationFlag = false;
                //dsImptOrdConfigBean.isExistingConfigurationFlag = true;
            }

            new SomDsImptOrdIstlInfoBean(ISTL_INFO_TYPE.REGULAR_SHIP_CONFIG, dsImptOrdConfigBean);
            new SomDsImptOrdDelyInfoBean(DELY_INFO_TYPE.REGULAR_SHIP_CONFIG, dsImptOrdConfigBean);
            // 2017/09/13 QC#21084 Del Start
//            new SomDsImptOrdSiteSrvyBean(SITE_SRVY_TYPE.REGULAR_SHIP_CONFIG, dsImptOrdConfigBean);
            dsImptOrdConfigBean.addDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.REGULAR_SHIP_CONFIG, isExistsSubSrv);
            // 2017/09/13 QC#21084 Del End

            // Line Loop
            for (NWAI4120_10TMsg if10TMsg : if10TMsgList) {
                //QC#24115
                //Main Item Line
                if (isExistsMachine && somSortOrdNum.compareTo(if10TMsg.somSortOrdNum.getValue()) == 0) {
                    NWAI4120_10TMsg copyif10TMsg = new NWAI4120_10TMsg();
                    EZDMsg.copy(if10TMsg, null, copyif10TMsg, null);
                    ZYPEZDItemValueSetter.setValue(copyif10TMsg.somOrdQty, BigDecimal.ONE);
                    ZYPEZDItemValueSetter.setValue(copyif10TMsg.sellPrcListAmt, copyif10TMsg.sellPrcListAmt);
                    ZYPEZDItemValueSetter.setValue(copyif10TMsg.extSellPrcAmt, copyif10TMsg.sellPrcListAmt);
                    SomDsImptOrdDtlBean dtlBean = new SomDsImptOrdDtlBean(DTL_TYPE.REGULAR_SHIP_LINE, dsImptOrdConfigBean, copyif10TMsg);
                    dtlBean.setTempBaseCmptFlg(ZYPConstant.FLG_ON_Y);//QC#18211
                    new SomDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.REGULAR_SHIP_LINE, dtlBean);
                //Other Lines
                } else {
                    // *************************************************************
                    // Process (6) (REGULAR SHIP LINE)
                    // *************************************************************
                    BigDecimal somOrdQty = BigDecimal.ONE;
                    if (ZYPCommonFunc.hasValue(if10TMsg.somOrdQty)) {
                        somOrdQty = if10TMsg.somOrdQty.getValue().abs();
                    }
                    BigDecimal otherQty = BigDecimal.ONE;
                    //Last Loop
                    if ((BigDecimal.ONE.add(new BigDecimal(String.valueOf(machineIndex)))).compareTo(mainMachineQty) == 0) {
                        otherQty = somOrdQty.subtract(somOrdQty.divide(mainMachineQty, 0, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(String.valueOf(machineIndex))));
                        if (otherQty.compareTo(BigDecimal.ZERO) <= 0) {
                            otherQty = BigDecimal.ZERO;
                        }
                    //Not Last Loop
                    } else {
                        if (somOrdQty.compareTo(mainMachineQty) >= 0) {
                            otherQty = somOrdQty.divide(mainMachineQty, 0, BigDecimal.ROUND_DOWN);
                        } else {
                            if ((somOrdQty.subtract(new BigDecimal(String.valueOf(machineIndex)))).compareTo(BigDecimal.ZERO)== 0) {
                                otherQty = BigDecimal.ZERO;
                            } else {
                                otherQty = BigDecimal.ONE;
                            }
                        }
                    }

                    //Qty is not ZERO
                    if (otherQty.compareTo(BigDecimal.ZERO) != 0) {
                        Map<String, Object> mdse = null;
                        if (!cacheItemMap.containsKey(if10TMsg.somMercCd.getValue())) {
                            mdse = NWXC412001.getMdse(dsImptOrdBean.glblCmpyCd.getValue(), if10TMsg.somMercCd.getValue());
                            cacheItemMap.put(if10TMsg.somMercCd.getValue(), mdse);
                        } else {
                            mdse = (Map<String, Object>) cacheItemMap.get(if10TMsg.somMercCd.getValue());
                        }
                        //Same order entry logic
                        //if (("10".equals(if10TMsg.somMdseTpTxt.getValue()) //Machine
                        //        || "11".equals(if10TMsg.somMdseTpTxt.getValue()) //Set Machine
                        //        || "20".equals(if10TMsg.somMdseTpTxt.getValue())) //Accessory
                        //        && mdse != null && ZYPConstant.FLG_ON_Y.equals((String)mdse.get("SHPG_SER_TAKE_FLG"))) {
                        if (mdse != null && ZYPConstant.FLG_ON_Y.equals((String)mdse.get("SHPG_SER_TAKE_FLG"))) {
                            for (int i = 0; i < otherQty.intValue(); i++) {
                                // per order quantity
                                NWAI4120_10TMsg copyif10TMsg = new NWAI4120_10TMsg();
                                EZDMsg.copy(if10TMsg, null, copyif10TMsg, null);
                                ZYPEZDItemValueSetter.setValue(copyif10TMsg.somOrdQty, BigDecimal.ONE);
                                ZYPEZDItemValueSetter.setValue(copyif10TMsg.sellPrcListAmt, copyif10TMsg.sellPrcListAmt);
                                ZYPEZDItemValueSetter.setValue(copyif10TMsg.extSellPrcAmt, copyif10TMsg.sellPrcListAmt);
                                SomDsImptOrdDtlBean dtlBean = new SomDsImptOrdDtlBean(DTL_TYPE.REGULAR_SHIP_LINE, dsImptOrdConfigBean, copyif10TMsg);
                                new SomDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.REGULAR_SHIP_LINE, dtlBean);
                            }
                        } else {
                            NWAI4120_10TMsg copyif10TMsg = new NWAI4120_10TMsg();
                            EZDMsg.copy(if10TMsg, null, copyif10TMsg, null);
                            ZYPEZDItemValueSetter.setValue(copyif10TMsg.somOrdQty, new BigDecimal(String.valueOf(otherQty)));
                            ZYPEZDItemValueSetter.setValue(copyif10TMsg.sellPrcListAmt, copyif10TMsg.sellPrcListAmt);
                            ZYPEZDItemValueSetter.setValue(copyif10TMsg.extSellPrcAmt, copyif10TMsg.sellPrcListAmt.getValue().multiply(new BigDecimal(String.valueOf(otherQty))));
                            SomDsImptOrdDtlBean dtlBean = new SomDsImptOrdDtlBean(DTL_TYPE.REGULAR_SHIP_LINE, dsImptOrdConfigBean, copyif10TMsg);
                            new SomDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.REGULAR_SHIP_LINE, dtlBean);
                        }
                    }
                }
            }//End of Line Loop
            List<NWAI4120_14TMsg> if14TMsgList = getNWAI4120_14TMsgByConfigKey(if12TMsg.somQuoteId, if12TMsg.somConfigId, nwai4120_14List);

            if (if14TMsgList.size() > 0) {
                for (NWAI4120_14TMsg if14TMsg : if14TMsgList) {
                    // *************************************************************
                    // Process (7) (OCE-PROMO)
                    // *************************************************************
                	SomDsImptOrdDtlBean dtlBean = new SomDsImptOrdDtlBean(DTL_TYPE.OCE_PROMO, dsImptOrdConfigBean, if14TMsg);
                    new SomDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.OCE_PROMO, dtlBean);
                }
            }

            // Add Start 2018/05/10 QC#20343
            // Mod Start 2018/05/22 QC#20343-1
            //String swMdlFlg = dsImptOrdBean.getSwMdlFlg(glblCmpyCd, dsImptOrdConfigBean);
            //
            //if(ZYPConstant.FLG_OFF_N.equals(swMdlFlg)){
            BigDecimal mdlId = dsImptOrdConfigBean.getMdlId(glblCmpyCd);

            if (ZYPCommonFunc.hasValue(mdlId)) {
                String swMdlFlg = dsImptOrdBean.getSwMdlFlg(glblCmpyCd, mdlId);

                if(ZYPConstant.FLG_OFF_N.equals(swMdlFlg) || !ZYPCommonFunc.hasValue(swMdlFlg)) {
                // Mod End 2018/05/22 QC#20343-1

                // If equipment config
                dsImptOrdBean.lastEquipConfigBean = dsImptOrdConfigBean;

                // Add Start 2018/05/22 QC#20343-1
                }
                // Add End 2018/05/22 QC#20343-1
            }
            // Add End 2018/05/10 QC#20343

            if (if14TMsgList.size() > 0) {
                for (NWAI4120_14TMsg if14TMsg : if14TMsgList) {
                    // *************************************************************
                    // Process (21) (OCE PROMOTION (RMA))
                    // *************************************************************
                    dsImptOrdConfigBean = new SomDsImptOrdConfigBean(CONFIG_TYPE.OCE_PROMOTION_RMA, dsImptOrdBean, if12TMsg);
                    dsImptOrdBean.addSomDsImptOrdConfigBean(dsImptOrdConfigBean);
                    dsImptOrdConfigBean.nwai4120_14List.add(if14TMsg);

                    new SomDsImptOrdIstlInfoBean(ISTL_INFO_TYPE.OCE_PROMOTION_RMA, dsImptOrdConfigBean);
                    new SomDsImptOrdDelyInfoBean(DELY_INFO_TYPE.OCE_PROMOTION_RMA, dsImptOrdConfigBean);
                    // 2017/09/13 QC#21084 Del Start
//                    new SomDsImptOrdSiteSrvyBean(SITE_SRVY_TYPE.OCE_PROMOTION_RMA, dsImptOrdConfigBean);
                    // 2017/09/13 QC#21084 Del End
                    dsImptOrdConfigBean.addDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.OCE_PROMOTION_RMA, false);

                    // *************************************************************
                    // Process (7) (OCE-PROMO)
                    // *************************************************************
                    //TODO
                    //get all configuration lines from main machine serial#
                    SomDsImptOrdRtrnDtlBean rtrnDtlBean = new SomDsImptOrdRtrnDtlBean(RTRN_DTL_TYPE.OCE_PROMO, dsImptOrdConfigBean, if14TMsg);
                    new SomDsImptRtrnPrcCalcBean(RTRN_DTL_TYPE.OCE_PROMO, rtrnDtlBean);
                }
            }
        }//End of Main Item Qty Loop
        
    }
    
    // Add Start 2018/05/10 QC#20343
    // Mod Start 2018/05/22 QC#20343-1
    ///**
    // * @param glblCmpyCd String
    // * @param dsImptOrdConfigBean SomDsImptOrdConfigBean
    // * @return String
    // */
    //public String getSwMdlFlg(String glblCmpyCd, SomDsImptOrdConfigBean dsImptOrdConfigBean) {
    //    String result = null;
    //
    //    BigDecimal mdlId = dsImptOrdConfigBean.getMdlId(glblCmpyCd);
    //
    //    if (ZYPCommonFunc.hasValue(mdlId)) {
    /**
     * @param glblCmpyCd String
     * @param mdlId BigDecimal
     * @return String
     */
    public String getSwMdlFlg(String glblCmpyCd, BigDecimal mdlId) {
        String result = null;

        // Mod End 2018/05/22 QC#20343-1
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("mdlId", mdlId);

            result = (String) ssmBatchClient.queryObject("getSwMdlFlg", ssmParam);

            // Del Start 2018/05/22 QC#20343-1
        //}
        // Del End 2018/05/22 QC#20343-1

        return result;
    }
    // Add End 2018/05/10 QC#20343

    //    /**
//     * writeEndLogLn
//     * @param methodNm
//     */
//    private static void writeEndLogLn(String methodNm) {
//        writeLogLn("[END] %s\r\n", methodNm);
//    }
//
//    /**
//     * WriteLogLn
//     * @param format
//     * @param param
//     */
//    private static void writeLogLn(String format, Object... param) {
//        StringBuilder sb = new StringBuilder();
//
//        sb.append(String.format("[%s]", NWAB412001Constant.PROGRAM_ID));
//
//        if (param.length > 0) {
//            sb.append(String.format(format, param));
//        } else {
//            sb.append(format);
//        }
//
//        S21InfoLogOutput.println(sb.toString());
//    }
    
    public void setVarCharConstMap(Map<String, Object> pVarCharConstMap) {
        varCharConstMap = pVarCharConstMap;
        this.pDefaultPrcCatgCd = NWXC412001.getPrcCatgCdFromName(this.glblCmpyCd.getValue(), (String) varCharConstMap.get(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_DEFAULT_PRICE_LIST.name()));
    }
    public Map<String, Object> getVarCharConstMap() {
        return varCharConstMap;
    }
    public Map<String, Map<String, Object>> getCacheSlsRepTocCdMap() {
        return cacheSlsRepTocCdMap;
    }
    public void setCacheSlsRepTocCdMap(Map<String, Map<String, Object>> pSlsRepTocCdMap) {
        cacheSlsRepTocCdMap = pSlsRepTocCdMap;
    }
    
    public String getOutBndDsOrdLineCatgCd() {
        return this.pOutBndDsOrdLineCatgCd;
    }
    public String getInBndDsOrdLineCatgCd() {
        return this.pInBndDsOrdLineCatgCd;
    }
    public void setCacheItemMap(Map<String, Map<String, Object>> pCacheItemMap) {
        cacheItemMap = pCacheItemMap;
    }
    public Map<String, Map<String, Object>> getCacheItemMap() {
        return cacheItemMap;
    }
    public String getDefaultPrcCatgCd() {
        return this.pDefaultPrcCatgCd;
    }
    public void setCachePrcCatgCdMap(Map<String, String> pCachePrcCatgCdMap) {
        cachePrcCatgCdMap = pCachePrcCatgCdMap;
    }
    public Map<String, String> getCachePrcCatgCdMap() {
        return cachePrcCatgCdMap;
    }
    public void setCachePrcCatgNmMap(Map<String, String> pCachePrcCatgNmMap) {
        cachePrcCatgNmMap = pCachePrcCatgNmMap;
    }
    public Map<String, String> getCachePrcCatgNmMap() {
        return cachePrcCatgNmMap;
    }
    public void setCacheSpecCondPrcMap(Map<String, BigDecimal> pCacheSpecCondPrcMap) {
        cacheSpecCondPrcMap = pCacheSpecCondPrcMap;
    }
    public Map<String, BigDecimal> getCacheSpecCondPrcMap() {
        return cacheSpecCondPrcMap;
    }
    
    public void setCacheShipToCustMap(Map<String, Map<String, Object>> pCacheShipToCustMap) {
        cacheShipToCustMap = pCacheShipToCustMap;
    }
    public Map<String, Map<String, Object>> getCacheShipToCustMap() {
        return cacheShipToCustMap;
    }
    public void setCacheS21PsnFromInstallRepNameMap(Map<String, String> pCacheS21PsnFromInstallRepNameMap) {
        cacheS21PsnFromInstallRepNameMap = pCacheS21PsnFromInstallRepNameMap;
    }
    public Map<String, String> getCacheS21PsnFromInstallRepNameMap() {
        return cacheS21PsnFromInstallRepNameMap;
    }
    public void setCacheSlsRepRoleTpCdMap(Map<String, String> pCacheSlsRepRoleTpCdMap) {
        cacheSlsRepRoleTpCdMap = pCacheSlsRepRoleTpCdMap;
    }
    public Map<String, String> getCacheSlsRepRoleTpCdMap() {
        return cacheSlsRepRoleTpCdMap;
    }
    public void setCacheModelMap(Map<String, BigDecimal> pCacheModelMap) {
        cacheModelMap = pCacheModelMap;
    }
    public Map<String, BigDecimal> getCacheModelMap() {
        return cacheModelMap;
    }
    public void setCachePrcMtrPkgPkMap(Map<String, BigDecimal> pCachePrcMtrPkgPkMap) {
    	cachePrcMtrPkgPkMap = pCachePrcMtrPkgPkMap;
    }
    public Map<String, BigDecimal> getCachePrcMtrPkgPkMap() {
        return cachePrcMtrPkgPkMap;
    }
    
    public String getDsOrdCatgCd() {
        return this.pDsOrdCatgCd;
    }
    public String getDsOrdTpCd() {
        return this.pDsOrdTpCd;
    }
    
    public void setCacheDefaultWHMap(Map<String, NWZC180001PMsg> pCacheDefaultWhMap) {
        cacheDefaultWhMap = pCacheDefaultWhMap;
    }
    public Map<String, NWZC180001PMsg> getCacheDefaultWHMap() {
        return cacheDefaultWhMap;
    }
    public void setCacheVndMap(Map<String, NPZC113001PMsg> pCacheVndMap) {
        cacheVndMap = pCacheVndMap;
    }
    public Map<String, NPZC113001PMsg> getCacheVndMap() {
        return cacheVndMap;
    }

    public String getCsmpPrcListCd() {
        return this.pCsmpPrcListCd;
    }
    
    //QC#20393 start
    private String regShellContractUniqueString(NWAI4120_16TMsg nwai4120_16, NWAI4120_18TMsg nwai4120_18) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(nwai4120_18.dclnMaintIndSomTxt.getValue());  //dsImptSvcMdseCd
        sb.append(nwai4120_18.contrTpDescTxt.getValue());  //prcSvcContrTpCd
        sb.append(nwai4120_18.planDescTxt.getValue());  //prcSvcPlnTpCd
        sb.append(nwai4120_18.somBllgCycleDescTxt.getValue());  //baseBllgCycleCd
        sb.append(nwai4120_18.usgCycleDescTxt.getValue());  //usgBllgCycleCd
        sb.append(nwai4120_18.somSrvTermId.getValue());  //toPerNum
        sb.append(nwai4120_18.svcContrIndSomTxt.getValue());  //billWithEquipFlg
        sb.append(nwai4120_16.fleetBillByTxt.getValue());  //billByTpCd

        return sb.toString();
    }
    private String regShellContractUniqueString(NWAI4120_16TMsg nwai4120_16, NWAI4120_19TMsg nwai4120_19) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(nwai4120_19.dclnMaintIndSomTxt.getValue());  //dsImptSvcMdseCd
        sb.append(nwai4120_19.somDurnId.getValue());  //toPerNum
        sb.append(nwai4120_19.svcContrIndSomTxt.getValue());  //billWithEquipFlg
        sb.append(nwai4120_16.fleetBillByTxt.getValue());  //billByTpCd

        return sb.toString();
    }
    //QC#20393 end
}
