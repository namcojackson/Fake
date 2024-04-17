/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001;

import static com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.EOPS_PREFIX_ORD_SRC_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.LEASE_PMT_FREQ_MONTHLY;
import static com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.QUOTE_LINE_TP_TRADEIN;
import static com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.QUOTE_LINE_TP_UPGRADEBUYOUTREBATE;
import static com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.TRX_TP_TXT_MONTHLY_PAYMENT;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDTBigDecimalItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_IMPT_ORDTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.FRT_CONDTMsg;
import business.db.NWAI4150_01TMsg;
import business.db.NWAI4150_02TMsg;
import business.db.NWAI4150_03TMsg;
import business.db.NWAI4150_04TMsg;
import business.db.NWAI4150_05TMsg;
import business.db.NWAI4150_06TMsg;
import business.db.NWAI4150_08TMsg;
import business.db.NWAI4150_10TMsg;
import business.db.NWAI4150_12TMsg;
import business.db.NWAI4150_13TMsg;
import business.db.NWAI4150_14TMsg;
import business.db.NWAI4150_16TMsg;
import business.db.NWAI4150_18TMsg;
import business.db.NWAI4150_19TMsg;
import business.db.NWAI4150_22TMsg;
import business.db.NWAI4150_23TMsg;
import business.db.NWAI4150_24TMsg;
import business.parts.NPZC113001PMsg;
import business.parts.NWZC180001PMsg;

import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.CONFIG_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.CONTRACT_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.CTAC_PSN_SUB_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.CTAC_PSN_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.CUTOFF_LEN;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.DELY_INFO_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.DTL_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.EOPS_CONTR_IND;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.FLG;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.INTERFACE_ID;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.ISTL_INFO_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.PRC_CALC_BASE_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.RTRN_DTL_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.SHELL_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.SLS_CR_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.SOM_SRV_MTR_TP;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.SVC_USG_PRC_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.VAR_CHAR_CONST_NM;
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

/**
 * <pre>
 * EOPS Interface to S21 Import Data Batch
 * EopsDsImptOrdBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 *</pre>
 */
public class EopsDsImptOrdBean extends DS_IMPT_ORDTMsg implements IEopsDsImptOrd, IEopsErrorInfo {

    /** default    */
    private static final long serialVersionUID = 1L;

    /**  */
    public S21SsmBatchClient ssmBatchClient;

    /**  */
    public final String slsDt;

    /**  */
    public final NWAI4150_01TMsg nwai415001;

    /**  */
    public ArrayList<EopsDsImptOrdEopsPrftBean> dsImptOrdEopsPrftBeanList;

    /**  */
    public ArrayList<EopsDsImptOrdEopsApvlBean> dsImptOrdEopsApvlBeanList;

    /**  */
    private Map<String, BigDecimal> cacheSpecCondPrcMap;

    /**  */
    public ArrayList<EopsDsImptOrdDtlBean> dsImptOrdDtlBeanList;

    /**  */
    public ArrayList<EopsDsImptOrdConfigBean> dsImptOrdConfigBeanList;

    /**  */
    private Map<String, String> cachePrcCatgNmMap;

    /**  */
    private Map<String, String> cachePrcCatgCdMap;

    /**  */
    private Map<String, Object> varCharConstMap;

    /**  */
    private String pDefaultPrcCatgCd;

    /**  */
    private Map<String, Map<String, Object>> cacheItemMap;

    /**  */
    private Map<String, Map<String, Object>> cacheSlsRepTocCdMap;

    /**  */
    public final List<EopsDsImptOrdCtacPsnBean> dsImptOrdCtacPsnBeanList;

    /**  */
    public ArrayList<EopsDsImptOrdRtrnDtlBean> dsImptOrdRtrnDtlBean;

    /**  */
    public EopsDsImptOrdConfigBean lastShipConfigBean;

    /**  */
    public ArrayList<EopsDsImptSvcDtlBean> dsImptSvcDtlBeanList;

    /**  */
    private ArrayList<NWAB415001ErrorInfo> errInfoList;

    /**  */
    private String pDsOrdCatgCd;

    /**  */
    private String pDsOrdTpCd;

    /**  */
    private String pLeaseCmpyDsAcctNum;

    //    private String pLeaseCmpyLocNum;

    /**  */
    private String pOutBndDsOrdLineCatgCd;

    /**  */
    private String pInBndDsOrdLineCatgCd;

    /**  */
    private String pCsmpPrcListCd;

    /**  */
    private HashMap<String, Map<String, Object>> cacheShipToCustMap;

    /**  */
    private Map<String, String> cacheS21PsnFromInstallRepNameMap;

    /**  */
    private Map<String, NWZC180001PMsg> cacheDefaultWhMap;

    /**  */
    private Map<String, NPZC113001PMsg> cacheVndMap;

    /**  */
    private int declPntDigt;

    /**  */
    public EopsDsImptOrdDelyInfoBean dsImptOrdDelyInfoBean;

    /**  */
    public EopsDsImptOrdSiteSrvyBean dsImptOrdSiteSrvyBean;

    /**  */
    public EopsDsImptOrdSlsCrBean dsImptOrdSlsCrBean;

    /**  */
    public EopsDsImptOrdIstlInfoBean dsImptOrdIstlInfoBean;

    /**  */
    private Map<String, String> cacheSlsRepRoleTpCdMap;

    /**  */
    private Map<String, BigDecimal> cachePrcMtrPkgPkMap;

    /**  */
    private static Map<String, BigDecimal> cacheModelMap;

    /**
     * EopsDsImptOrdBean
     * @param ssmBatchClient    S21SsmBatchClient
     * @param glblCmpyCd        String
     * @param slsDt             String
     * @param nwai415001        NWAI4150_01TMsg
     */
    public EopsDsImptOrdBean(S21SsmBatchClient ssmBatchClient, String glblCmpyCd, String slsDt, NWAI4150_01TMsg nwai415001) {
        super();

        this.ssmBatchClient = ssmBatchClient;
        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        this.slsDt = slsDt;
        this.nwai415001 = nwai415001;
        this.dsImptOrdEopsPrftBeanList = new ArrayList<EopsDsImptOrdEopsPrftBean>();
        this.dsImptOrdEopsApvlBeanList = new ArrayList<EopsDsImptOrdEopsApvlBean>();
        this.dsImptOrdConfigBeanList = new ArrayList<EopsDsImptOrdConfigBean>();
        this.dsImptOrdDtlBeanList = new ArrayList<EopsDsImptOrdDtlBean>();
        this.dsImptOrdRtrnDtlBean = new ArrayList<EopsDsImptOrdRtrnDtlBean>();
        this.dsImptOrdCtacPsnBeanList = new ArrayList<EopsDsImptOrdCtacPsnBean>();
        this.lastShipConfigBean = null;
        this.dsImptSvcDtlBeanList = new ArrayList<EopsDsImptSvcDtlBean>();
        this.errInfoList = new ArrayList<NWAB415001ErrorInfo>();
        this.pDsOrdCatgCd = NWXC412001.getDsOrdCatgCd(this.glblCmpyCd.getValue(), CPO_SRC_TP.EOPS, this.nwai415001.interfaceId.getValue(), this.nwai415001.eopsTrxTpTxt.getValue());
        Map<String, Object> dsOrdTpMap = NWXC412001.getDsOrdTpInfo(this.glblCmpyCd.getValue(), CPO_SRC_TP.EOPS, this.nwai415001.interfaceId.getValue(), this.nwai415001.eopsTrxTpTxt.getValue(), null);
        this.pDsOrdTpCd = (String) dsOrdTpMap.get("TRGT_ATTRB_TXT_01");
        this.pLeaseCmpyDsAcctNum = (String) dsOrdTpMap.get("TRGT_ATTRB_TXT_02");
        //        this.pLeaseCmpyLocNum = (String) dsOrdTpMap.get("TRGT_ATTRB_TXT_03");
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

        declPntDigt = NWXC412001.getDecimalPointDigit(glblCmpyCd);
    }

    /**
     * setCacheSpecCondPrcMap
     * @param pCacheSpecCondPrcMap Map<String, BigDecimal>
     */
    public void setCacheSpecCondPrcMap(Map<String, BigDecimal> pCacheSpecCondPrcMap) {
        cacheSpecCondPrcMap = pCacheSpecCondPrcMap;
    }

    /**
     * getCacheSpecCondPrcMap
     * @return cacheSpecCondPrcMap
     */
    public Map<String, BigDecimal> getCacheSpecCondPrcMap() {
        return cacheSpecCondPrcMap;
    }

    /**
     * setCachePrcCatgCdMap
     * @param pCachePrcCatgCdMap Map<String, String>
     */
    public void setCachePrcCatgCdMap(Map<String, String> pCachePrcCatgCdMap) {
        cachePrcCatgCdMap = pCachePrcCatgCdMap;
    }

    /**
     * getCachePrcCatgCdMap
     * @return cachePrcCatgCdMap
     */
    public Map<String, String> getCachePrcCatgCdMap() {
        return cachePrcCatgCdMap;
    }

    /**
     * setCachePrcCatgNmMap
     * @param pCachePrcCatgNmMap Map<String, String>
     */
    public void setCachePrcCatgNmMap(Map<String, String> pCachePrcCatgNmMap) {
        cachePrcCatgNmMap = pCachePrcCatgNmMap;
    }

    /**
     * getCachePrcCatgNmMap
     * @return cachePrcCatgNmMap
     */
    public Map<String, String> getCachePrcCatgNmMap() {
        return cachePrcCatgNmMap;
    }

    /**
     * setVarCharConstMap
     * @param pVarCharConstMap Map<String, Object>
     */
    public void setVarCharConstMap(Map<String, Object> pVarCharConstMap) {
        varCharConstMap = pVarCharConstMap;
        this.pDefaultPrcCatgCd = NWXC412001.getPrcCatgCdFromName(this.glblCmpyCd.getValue(), (String) varCharConstMap.get(VAR_CHAR_CONST_NM.EOPS_DEFAULT_PRICE_LIST.name()));
    }

    /**
     * getVarCharConstMap
     * @return varCharConstMap
     */
    public Map<String, Object> getVarCharConstMap() {
        return varCharConstMap;
    }

    /**
     * setCacheItemMap
     * @param pCacheItemMap Map<String, Map<String, Object>>
     */
    public void setCacheItemMap(Map<String, Map<String, Object>> pCacheItemMap) {
        cacheItemMap = pCacheItemMap;
    }

    /**
     * getCacheItemMap
     * @return cacheItemMap
     */
    public Map<String, Map<String, Object>> getCacheItemMap() {
        return cacheItemMap;
    }

    /**
     * getCacheSlsRepTocCdMap
     * @return cacheSlsRepTocCdMap
     */
    public Map<String, Map<String, Object>> getCacheSlsRepTocCdMap() {
        return cacheSlsRepTocCdMap;
    }

    /**
     * setCacheSlsRepTocCdMap
     * @param pSlsRepTocCdMap Map<String, Map<String, Object>>
     */
    public void setCacheSlsRepTocCdMap(Map<String, Map<String, Object>> pSlsRepTocCdMap) {
        cacheSlsRepTocCdMap = pSlsRepTocCdMap;
    }

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
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SQ));
        ZYPEZDItemValueSetter.setValue(this.cpoSrcTpCd, CPO_SRC_TP.EOPS);
        ZYPEZDItemValueSetter.setValue(this.ordSrcImptTs, NWXC412001.getValidTimeStamp(this.nwai415001.lastUpdTs.getValue()));
        //
        ZYPEZDItemValueSetter.setValue(//
                this.ordSrcRefNum, NWXC412001.formatOrdSrcRefNum(this.nwai415001.somQuoteId.getValue().toPlainString(), EOPS_PREFIX_ORD_SRC_NUM, CUTOFF_LEN.ORD_SRC_REF_NUM.getLen()));
        ZYPEZDItemValueSetter.setValue(this.imptStsCd, IMPT_STS.PENDING_OM_REVIEW);
        ZYPEZDItemValueSetter.setValue(this.sysSrcCd, SYS_SRC.S21_ORDER);
        ZYPEZDItemValueSetter.setValue(this.dsOrdCatgCd, this.pDsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(this.dsOrdTpCd, this.pDsOrdTpCd);
        this.dsOrdRsnCd.clear();
        DS_ORD_TPTMsg dsOrdTpTMsg = new DS_ORD_TPTMsg();
        if (ZYPCommonFunc.hasValue(this.dsOrdTpCd)) {
            ZYPEZDItemValueSetter.setValue(dsOrdTpTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsOrdTpTMsg.dsOrdTpCd, this.dsOrdTpCd);
            dsOrdTpTMsg = (DS_ORD_TPTMsg) S21CodeTableAccessor.findByKey(dsOrdTpTMsg);
        }
        if (dsOrdTpTMsg != null) {
            ZYPEZDItemValueSetter.setValue(this.cpoOrdTpCd, dsOrdTpTMsg.cpoOrdTpCd);
        } else {
            this.cpoOrdTpCd.clear();
        }

        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg = null;
        if (ZYPCommonFunc.hasValue(this.dsOrdTpCd)) {

            dsOrdTpProcDfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.dsOrdTpCd, this.dsOrdTpCd);
            dsOrdTpProcDfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) EZDTBLAccessor.findByKey(dsOrdTpProcDfnTMsg);
        }

        if (ZYPCommonFunc.hasValue(this.nwai415001.somPoNum)) {
            ZYPEZDItemValueSetter.setValue(//
                    this.custIssPoNum //
                    , S21StringUtil.subStringByLength(this.nwai415001.somPoNum.getValue(), 0, CUTOFF_LEN.CUST_ISS_PO_NUM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.custIssPoDt, NWXC412001.getValidDate(this.nwai415001.custIssPoDt.getValue()));
        } else {
            this.custIssPoNum.clear();
            this.custIssPoDt.clear();
        }

        String billToCustAcctCd = null;
        String billToCustCd = null;
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
        //        String billToProvNm = null;
        //        String billToCntyNm = null;
        String billToPostCd = null;
        //        String billToCtryCd = null;
        String billToAcctNm = null;
        String soldToCustAcctCd = null;
        String soldToCustCd = null;
        //        String soldToLocNm = null;
        //        String soldToAddlLocNm = null;
        //        String soldToFirstLineAddr = null;
        //        String soldToScdLineAddr = null;
        //        String soldToThirdLineAddr = null;
        //        String soldToFrthLineAddr = null;
        //        String soldToFirstRefCmntTxt = null;
        //        String soldToScdRefCmntTxt = null;
        //        String soldToCtyAddr = null;
        //        String soldToStCd = null;
        //        String soldToProvNm = null;
        //        String soldToCntyNm = null;
        //        String soldToPostCd = null;
        //        String soldToCtryCd = null;
        //        String soldToAcctNm = null;
        Map<String, Object> billToInfoMap = null;
        //QC#20753
        String shipToAddrConcatTxt //
        = S21StringUtil.concatStrings(//
                S21StringUtil.trimString(S21StringUtil.concatStrings(NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai415001.shipToAddrDescTxt_01.getValue())) //
                        , " " //
                        , NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai415001.shipToAddrDescTxt_02.getValue())))) //
                , NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai415001.shipToCityTxt.getValue())) //
                , NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai415001.shipToStTxt.getValue())) //
                , NWXC412001.toUpperCase(NWXC412001.cutString(S21StringUtil.trimString(this.nwai415001.somShipToZipCd.getValue()), 5)));
        String billToAddrConcatTxt //
        = S21StringUtil.concatStrings(//
                NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai415001.bllgAddrDescTxt.getValue())) //
                , NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai415001.bllgCityDescTxt.getValue())) //
                , NWXC412001.toUpperCase(S21StringUtil.trimString(this.nwai415001.bllgStTxt.getValue())) //
                , NWXC412001.toUpperCase(NWXC412001.cutString(S21StringUtil.trimString(this.nwai415001.bllgZipTxt.getValue()), 5)));
        //QC#19968
        String endUsrBillToCustAcctCd = "";
        String endUsrBillToCustCd = "";
        if (ZYPCommonFunc.hasValue(this.nwai415001.somShipToPtyNum) //
                && this.nwai415001.somShipToPtyNum.getValue().compareTo(BigDecimal.ZERO) != 0) {
            //Same with Ship to
            if (shipToAddrConcatTxt.equals(billToAddrConcatTxt)) {

                billToInfoMap //
                = NWXC412001.getBillToInfoFromSomShipToPtyNum(glblCmpyCd, this.nwai415001.somShipToPtyNum.getValue().toString(), salesDate);

                //Not same with Ship to
            } else if (!shipToAddrConcatTxt.equals(billToAddrConcatTxt)) {

                billToInfoMap //
                = NWXC412001.getBillToInfoFromAddress(//
                        glblCmpyCd, billToAddrConcatTxt, salesDate, this.nwai415001.bllgCmpyTxt.getValue());
            }
        } else if (!ZYPCommonFunc.hasValue(this.nwai415001.somShipToPtyNum) //
                || this.nwai415001.somShipToPtyNum.getValue().compareTo(BigDecimal.ZERO) == 0) {
            billToInfoMap //
            = NWXC412001.getBillToInfoFromAddress(//
                    glblCmpyCd, billToAddrConcatTxt, salesDate, this.nwai415001.bllgCmpyTxt.getValue());
        }
        if (billToInfoMap != null && !billToInfoMap.isEmpty()) {
            endUsrBillToCustAcctCd = (String) billToInfoMap.get("DS_ACCT_NUM");
            endUsrBillToCustCd = (String) billToInfoMap.get("BILL_TO_CUST_CD");
        }

        //Lease(MONTHLY PAYMENT)
        if (this.nwai415001.eopsTrxTpTxt.getValue().equals(TRX_TP_TXT_MONTHLY_PAYMENT)) {
            if (ZYPCommonFunc.hasValue(this.pLeaseCmpyDsAcctNum)) {
                billToInfoMap = NWXC412001.getBillToInfoFromDsAcctNum(glblCmpyCd, this.pLeaseCmpyDsAcctNum, salesDate);
                if (billToInfoMap != null && !billToInfoMap.isEmpty()) {
                    billToCustAcctCd = (String) billToInfoMap.get("DS_ACCT_NUM");
                    billToCustCd = (String) billToInfoMap.get("BILL_TO_CUST_CD");
                }
            } else {
                if (dsOrdTpProcDfnTMsg != null) {
                    billToCustAcctCd = dsOrdTpProcDfnTMsg.defBillToCustAcctCd.getValue();
                    billToCustCd = dsOrdTpProcDfnTMsg.defBillToCustCd.getValue();
                    billToInfoMap = NWXC412001.getBillToInfoByCode(glblCmpyCd, billToCustAcctCd, billToCustCd, salesDate);
                }
            }
            if (billToInfoMap == null || billToInfoMap.isEmpty()) {
                billToCustAcctCd = endUsrBillToCustAcctCd;
                billToCustCd = endUsrBillToCustCd;
                billToLocNm = S21StringUtil.subStringByLength(this.nwai415001.billToCustLegalNm.getValue(), 0, 60);
                billToAddlLocNm = null;

                if (ZYPCommonFunc.hasValue(this.nwai415001.bllgAddrDescTxt)) {

                    if (this.nwai415001.bllgAddrDescTxt.getValue().length() <= 60) {
                        billToFirstLineAddr = this.nwai415001.bllgAddrDescTxt.getValue();

                    } else {
                        String[] billToLineAddrs = this.nwai415001.bllgAddrDescTxt.getValue().split(" ");
                        StringBuffer[] addrs = {new StringBuffer(""), new StringBuffer(""), new StringBuffer(""), new StringBuffer("") };
                        int j = 0;

                        for (int i = 0; i < billToLineAddrs.length; i++) {
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
                billToCtyAddr = S21StringUtil.subStringByLength(this.nwai415001.bllgCityDescTxt.getValue(), 0, 25);
                billToStCd = S21StringUtil.subStringByLength(this.nwai415001.bllgStTxt.getValue(), 0, 2);
                //                billToProvNm = null;
                //                billToCntyNm = null;
                //                billToPostCd = S21StringUtil.subStringByLength(this.nwai4150_01.bllgZipTxt.getValue(), 0, 15);
                //                billToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;
                billToAcctNm = this.nwai415001.bllgCmpyTxt.getValue();

                soldToCustAcctCd = billToCustAcctCd;
                soldToCustCd = billToCustCd;
                //                soldToLocNm = billToLocNm;
                //                soldToAddlLocNm = billToAddlLocNm;
                //                soldToFirstLineAddr = billToFirstLineAddr;
                //                soldToScdLineAddr = billToScdLineAddr;
                //                soldToThirdLineAddr = billToThirdLineAddr;
                //                soldToFrthLineAddr = billToFrthLineAddr;
                //                soldToFirstRefCmntTxt = billToFirstRefCmntTxt;
                //                soldToScdRefCmntTxt = billToScdRefCmntTxt;
                //                soldToCtyAddr = billToCtyAddr;
                //                soldToStCd = billToStCd;
                //                soldToProvNm = billToProvNm;
                //                soldToCntyNm = billToCntyNm;
                //                soldToPostCd = billToPostCd;
                //                soldToCtryCd = billToCtryCd;
                //                soldToAcctNm = billToAcctNm;
            } else {
                soldToCustAcctCd = endUsrBillToCustAcctCd;
                soldToCustCd = endUsrBillToCustCd;
                //                soldToLocNm = S21StringUtil.subStringByLength(this.nwai4150_01.billToCustLegalNm.getValue(), 0, 60);
                //                soldToAddlLocNm = null;

                if (ZYPCommonFunc.hasValue(this.nwai415001.bllgAddrDescTxt)) {

                    if (this.nwai415001.bllgAddrDescTxt.getValue().length() <= 60) {
                        //                        soldToFirstLineAddr = this.nwai4150_01.bllgAddrDescTxt.getValue();

                    } else {
                        String[] soldToLineAddrs = this.nwai415001.bllgAddrDescTxt.getValue().split(" ");
                        StringBuffer[] addrs = {new StringBuffer(""), new StringBuffer(""), new StringBuffer(""), new StringBuffer("") };
                        int j = 0;

                        for (int i = 0; i < soldToLineAddrs.length; i++) {
                            if (addrs[j].length() + soldToLineAddrs[i].length() + 1 > 60) {
                                j++;
                            }

                            if (addrs[j].length() > 0) {
                                addrs[j] = addrs[j].append(" ");
                            }
                            addrs[j] = addrs[j].append(soldToLineAddrs[i]);
                        }
                        //                        soldToFirstLineAddr = addrs[0].toString();
                        //                        soldToScdLineAddr = addrs[1].toString();
                        //                        soldToThirdLineAddr = addrs[2].toString();
                        //                        soldToFrthLineAddr = addrs[3].toString();
                    }
                }
                //                soldToCtyAddr = S21StringUtil.subStringByLength(this.nwai4150_01.bllgCityDescTxt.getValue(), 0, 25);
                //                soldToStCd = S21StringUtil.subStringByLength(this.nwai4150_01.bllgStTxt.getValue(), 0, 2);
                //                soldToProvNm = null;
                //                soldToCntyNm = null;
                //                soldToPostCd = S21StringUtil.subStringByLength(this.nwai4150_01.bllgZipTxt.getValue(), 0, CUTOFF_LEN.POST_CD.getLen());
                //                soldToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;
                //                soldToAcctNm = this.nwai4150_01.bllgCmpyTxt.getValue();
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
                    billToCtyAddr = (String) billToInfoMap.get("CTY_ADDR");
                    billToStCd = (String) billToInfoMap.get("ST_CD");
                    //                    billToProvNm = (String) billToInfoMap.get("PROV_NM");
                    //                    billToCntyNm = (String) billToInfoMap.get("CNTY_NM");
                    //                    billToPostCd = (String) billToInfoMap.get("POST_CD");
                    //                    billToCtryCd = (String) billToInfoMap.get("CTRY_CD");
                    billToAcctNm = (String) billToInfoMap.get("DS_ACCT_NM");
                }
                // 2017/01/27 S21_NA#17119 Add Start
            } else {
                billToLocNm = S21StringUtil.subStringByLength(//
                        this.nwai415001.billToCustLegalNm.getValue(), 0, 60);
                billToAddlLocNm = null;

                if (ZYPCommonFunc.hasValue(this.nwai415001.bllgAddrDescTxt)) {

                    if (this.nwai415001.bllgAddrDescTxt.getValue().length() <= 60) {
                        billToFirstLineAddr = this.nwai415001.bllgAddrDescTxt.getValue();

                    } else {
                        String[] billToLineAddrs = this.nwai415001.bllgAddrDescTxt.getValue().split(" ");
                        StringBuffer[] addrs = {new StringBuffer(""), new StringBuffer(""), new StringBuffer(""), new StringBuffer("") };
                        int j = 0;

                        for (int i = 0; i < billToLineAddrs.length; i++) {
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
                billToCtyAddr = S21StringUtil.subStringByLength(this.nwai415001.bllgCityDescTxt.getValue(), 0, 25);
                billToStCd = S21StringUtil.subStringByLength(this.nwai415001.bllgStTxt.getValue(), 0, 2);
                //                billToProvNm = null;
                //                billToCntyNm = null;
                billToPostCd //
                = S21StringUtil.subStringByLength(this.nwai415001.bllgZipTxt.getValue(), 0, CUTOFF_LEN.POST_CD.getLen());
                //                billToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;
                billToAcctNm = this.nwai415001.bllgCmpyTxt.getValue();
            }

            soldToCustAcctCd = billToCustAcctCd;
            soldToCustCd = billToCustCd;
            //            soldToLocNm = billToLocNm;
            //            soldToAddlLocNm = billToAddlLocNm;
            //            soldToFirstLineAddr = billToFirstLineAddr;
            //            soldToScdLineAddr = billToScdLineAddr;
            //            soldToThirdLineAddr = billToThirdLineAddr;
            //            soldToFrthLineAddr = billToFrthLineAddr;
            //            soldToFirstRefCmntTxt = billToFirstRefCmntTxt;
            //            soldToScdRefCmntTxt = billToScdRefCmntTxt;
            //            soldToCtyAddr = billToCtyAddr;
            //            soldToStCd = billToStCd;
            //            soldToProvNm = billToProvNm;
            //            soldToCntyNm = billToCntyNm;
            //            soldToPostCd = billToPostCd;
            //            soldToCtryCd = billToCtryCd;
            //            soldToAcctNm = billToAcctNm;
        }
        ZYPEZDItemValueSetter.setValue(this.billToCustAcctCd, billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(this.billToCustCd, billToCustCd);

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
        this.billToProvNm.clear();
        this.billToCntyNm.clear();
        ZYPEZDItemValueSetter.setValue(this.billToPostCd, billToPostCd);
        ZYPEZDItemValueSetter.setValue(this.billToCtryCd, CTRY.UNITED_STATES_OF_AMERICA);
        ZYPEZDItemValueSetter.setValue(this.billToAcctNm, billToAcctNm);

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
        if (ZYPCommonFunc.hasValue(this.nwai415001.somShipToPtyNum) //
                && this.nwai415001.somShipToPtyNum.getValue().compareTo(BigDecimal.ZERO) != 0) {

            if (!this.getCacheShipToCustMap().containsKey("FROMPSN" + this.nwai415001.somShipToPtyNum.getValue().toPlainString())) {
                shipToInfoMap = NWXC412001.getShipToInfoFromSomPsnCnt(glblCmpyCd, this.nwai415001.somShipToPtyNum.getValue(), salesDate);
                this.getCacheShipToCustMap().put("FROMPSN" + this.nwai415001.somShipToPtyNum.getValue().toPlainString(), shipToInfoMap);
            } else {
                shipToInfoMap = this.getCacheShipToCustMap().get("FROMPSN" + this.nwai415001.somShipToPtyNum.getValue().toPlainString());
            }
        } else {

            if (!this.getCacheShipToCustMap().containsKey("FROMADDR" + this.nwai415001.somShipToPtyNum.getValue() + shipToAddrConcatTxt)) {
                shipToInfoMap //
                = NWXC412001.getShipToInfoFromAddress(//
                        glblCmpyCd, shipToAddrConcatTxt, salesDate, this.nwai415001.somShipToCustNm.getValue());
                this.getCacheShipToCustMap().put("FROMADDR" + this.nwai415001.somShipToPtyNum.getValue() + shipToAddrConcatTxt, shipToInfoMap);
            } else {
                shipToInfoMap = this.getCacheShipToCustMap().get("FROMADDR" + this.nwai415001.somShipToPtyNum.getValue() + shipToAddrConcatTxt);
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
            shipToAcctNm = (String) shipToInfoMap.get("DS_ACCT_NM");
        } else {
            shipToCustAcctCd = S21StringUtil.subStringByLength(this.nwai415001.shipToAcctNum.getValue(), 0, 20);
            shipToCustCd = null;
            shipToLocNm = this.nwai415001.shipToCustLegalNm.getValue();
            shipToAddlLocNm = null;
            String shipToLineAddr //
            = S21StringUtil.concatStrings(this.nwai415001.shipToAddrDescTxt_01.getValue(), this.nwai415001.shipToAddrDescTxt_02.getValue());
            shipToFirstLineAddr = S21StringUtil.subStringByLength(shipToLineAddr, 0, 60);
            shipToScdLineAddr = S21StringUtil.subStringByLength(shipToLineAddr, 60, 60);
            shipToThirdLineAddr = S21StringUtil.subStringByLength(shipToLineAddr, 120, 60);
            shipToFrthLineAddr = S21StringUtil.subStringByLength(shipToLineAddr, 180, 20);
            shipToCtyAddr = S21StringUtil.subStringByLength(this.nwai415001.shipToCityTxt.getValue(), 0, 25);
            shipToStCd = this.nwai415001.shipToStTxt.getValue();
            shipToProvNm = null;
            shipToCntyNm = S21StringUtil.subStringByLength(this.nwai415001.shipToCntyTxt.getValue(), 0, 30);
            shipToPostCd = this.nwai415001.somShipToZipCd.getValue();
            shipToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;
            shipToAcctNm = this.nwai415001.somShipToCustNm.getValue();
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

        ZYPEZDItemValueSetter.setValue(this.sellToCustCd, soldToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, soldToCustCd);

        ZYPEZDItemValueSetter.setValue(this.sldToLocNm, this.billToLocNm);
        ZYPEZDItemValueSetter.setValue(this.sldToAddlLocNm, this.billToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(this.sldToFirstLineAddr, this.billToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToScdLineAddr, this.billToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToThirdLineAddr, this.billToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToFrthLineAddr, this.billToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToFirstRefCmntTxt, this.billToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(this.sldToScdRefCmntTxt, this.billToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(this.sldToCtyAddr, this.billToCtyAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToStCd, this.billToStCd);
        ZYPEZDItemValueSetter.setValue(this.sldToProvNm, this.billToProvNm);
        ZYPEZDItemValueSetter.setValue(this.sldToCntyNm, this.billToCntyNm);
        ZYPEZDItemValueSetter.setValue(this.sldToPostCd, this.billToPostCd);
        ZYPEZDItemValueSetter.setValue(this.sldToCtryCd, this.billToCtryCd);
        ZYPEZDItemValueSetter.setValue(this.sldToAcctNm, this.billToAcctNm);

        this.adminPsnCd.clear();

        ZYPEZDItemValueSetter.setValue(this.rddDt, NWXC412001.getValidDate(this.nwai415001.erlstDelyTs.getValue()));
        if (dsOrdTpProcDfnTMsg != null) {
            ZYPEZDItemValueSetter.setValue(this.frtCondCd, dsOrdTpProcDfnTMsg.frtCondCd);
        } else {
            this.frtCondCd.clear();
        }
        this.spclHdlgTpCd.clear();
        this.carrCd.clear();
        this.carrSvcLvlCd.clear();
        if (dsOrdTpProcDfnTMsg != null) {
            ZYPEZDItemValueSetter.setValue(this.shpgSvcLvlCd, dsOrdTpProcDfnTMsg.defShpgSvcLvlCd);
        } else {
            this.shpgSvcLvlCd.clear();
        }
        FRT_CONDTMsg frtCondTMsg = new FRT_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(frtCondTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(frtCondTMsg.frtCondCd, this.frtCondCd);
        if (ZYPCommonFunc.hasValue(this.frtCondCd)) {
            frtCondTMsg = (FRT_CONDTMsg) S21CodeTableAccessor.findByKey(frtCondTMsg);
        }
        if (frtCondTMsg != null) {
            ZYPEZDItemValueSetter.setValue(this.frtChrgToCd, frtCondTMsg.frtChrgToCd);
            ZYPEZDItemValueSetter.setValue(this.frtChrgMethCd, frtCondTMsg.frtChrgMethCd);
        } else {
            this.frtChrgToCd.clear();
            this.frtChrgMethCd.clear();
        }
        this.carrAcctNum.clear();
        //        Map<String, Object> net30Map = NWXC412001.getNet30(glblCmpyCd, CPO_SRC_TP.EOPS, INTERFACE_ID.NWAI4150.name(), this.nwai4150_01.netThrtyIndSomTxt.getValue());
        //        if (net30Map != null) {
        //            ZYPEZDItemValueSetter.setValue(this.addPmtTermCashDiscCd, (String) net30Map.get("TRGT_ATTRB_TXT_01"));
        //            ZYPEZDItemValueSetter.setValue(this.dsPmtMethCd, (String) net30Map.get("TRGT_ATTRB_TXT_02"));
        //        }
        //        Map<String, Object> chkOrdMap = NWXC412001.getCheckOrder(glblCmpyCd, CPO_SRC_TP.EOPS, INTERFACE_ID.NWAI4150.name(), this.nwai4150_01.chkOrdIndSomTxt.getValue());
        //        if (chkOrdMap != null) {
        //            ZYPEZDItemValueSetter.setValue(this.addPmtTermCashDiscCd, (String) chkOrdMap.get("TRGT_ATTRB_TXT_01"));
        //            ZYPEZDItemValueSetter.setValue(this.dsPmtMethCd, (String) chkOrdMap.get("TRGT_ATTRB_TXT_02"));
        //        }
        //        if (!ZYPCommonFunc.hasValue(this.addPmtTermCashDiscCd)) {
        ZYPEZDItemValueSetter.setValue(//
                this.addPmtTermCashDiscCd //
                , (String) this.getVarCharConstMap().get(VAR_CHAR_CONST_NM.EOPS_DEF_PMT_TERM_CASH_DISC_CD.name()));
        this.dsPmtMethCd.clear();
        //        }
        this.prePmtChkNum.clear();
        this.prePmtAmt.clear();
        this.prePmtTpCd.clear();
        ZYPEZDItemValueSetter.setValue(//
                this.prcBaseDt, NWXC412001.getValidDate(this.nwai415001.cratTs.getValue()));

        if (ZYPCommonFunc.hasValue(this.nwai415001.negoDealAmt) //
                && BigDecimal.ZERO.compareTo(this.nwai415001.negoDealAmt.getValue()) != 0) {
            ZYPEZDItemValueSetter.setValue(this.negoDealAmt, this.nwai415001.negoDealAmt);
        } else {
            this.negoDealAmt.clear();
        }

        String tmpPrcCatgCd = null;
        if (ZYPCommonFunc.hasValue(this.nwai415001.bidPrcListNm)) {
            tmpPrcCatgCd = this.nwai415001.bidPrcListNm.getValue();
        }
        List<EopsDsImptOrdConfigBean> eopsDsImptOrdConfigBeanList = this.dsImptOrdConfigBeanList;
        //        if (this.nwai4150_01.eopsTrxTpTxt.getValue().equals(NWAB415001Constant.TRX_TP_TXT_RENTAL)) {
        //            if (ZYPCommonFunc.hasValue(this.nwai4150_01.somPrcListId)) {
        //                tmpPrcCatgCd = this.nwai4150_01.somPrcListId.getValue().toPlainString();
        //            } else if (ZYPCommonFunc.hasValue(this.nwai4150_01.prcListDescTxt)) {
        //                tmpPrcCatgCd = NWXC412001.getPrcCatgCdFromName(glblCmpyCd, this.nwai4150_01.prcListDescTxt.getValue());
        //            } else {
        //                if (eopsDsImptOrdConfigBeanList.size() > 0) {
        //                    if (ZYPCommonFunc.hasValue(eopsDsImptOrdConfigBeanList.get(0).getPrcCatgCd())) {
        //                        tmpPrcCatgCd = eopsDsImptOrdConfigBeanList.get(0).getPrcCatgCd();
        //                    }
        //                }
        //                if (ZYPCommonFunc.hasValue(tmpPrcCatgCd)) {
        //                    if (eopsDsImptOrdConfigBeanList.size() > 0) {
        //                        if (ZYPCommonFunc.hasValue(eopsDsImptOrdConfigBeanList.get(0).getFlPrcListCd())) {
        //                            tmpPrcCatgCd = eopsDsImptOrdConfigBeanList.get(0).getFlPrcListCd();
        //                        }
        //                    }
        //                }
        //            }
        //        } else {
        if (ZYPCommonFunc.hasValue(this.nwai415001.bidPrcListNm)) {
            tmpPrcCatgCd = NWXC412001.getPrcCatgCdFromName(glblCmpyCd, this.nwai415001.bidPrcListNm.getValue());
            if (ZYPCommonFunc.hasValue(prcCatgCd)) {
                ZYPEZDItemValueSetter.setValue(this.prcCatgCd, prcCatgCd);
            }
        } else {
            if (eopsDsImptOrdConfigBeanList.size() > 0) {
                if (ZYPCommonFunc.hasValue(eopsDsImptOrdConfigBeanList.get(0).getPrcCatgCd())) {
                    tmpPrcCatgCd = eopsDsImptOrdConfigBeanList.get(0).getPrcCatgCd();
                }
            }
            if (ZYPCommonFunc.hasValue(tmpPrcCatgCd)) {
                if (eopsDsImptOrdConfigBeanList.size() > 0) {
                    if (ZYPCommonFunc.hasValue(eopsDsImptOrdConfigBeanList.get(0).getFlPrcListCd())) {
                        tmpPrcCatgCd = eopsDsImptOrdConfigBeanList.get(0).getFlPrcListCd();
                    }
                }
            }
        }
        //        }

        if (ZYPCommonFunc.hasValue(tmpPrcCatgCd)) {
            ZYPEZDItemValueSetter.setValue(this.prcCatgCd, tmpPrcCatgCd);
        } else {
            // default price list
            ZYPEZDItemValueSetter.setValue(this.prcCatgCd, getDefaultPrcCatgCd());
        }

        String flPrcListCd = null;
        if (eopsDsImptOrdConfigBeanList.size() > 0) {
            if (ZYPCommonFunc.hasValue(eopsDsImptOrdConfigBeanList.get(0).getFlPrcListCd())) {
                flPrcListCd = eopsDsImptOrdConfigBeanList.get(0).getFlPrcListCd().toString();
            }
        }
        ZYPEZDItemValueSetter.setValue(this.flPrcListCd, flPrcListCd);
        if (!ZYPCommonFunc.hasValue(this.flPrcListCd)) {

            // default price list
            ZYPEZDItemValueSetter.setValue(this.flPrcListCd, getDefaultPrcCatgCd());
        }

        //        if (ZYPCommonFunc.hasValue(this.nwai4150_01.prcContrNum) && !"0".equals(this.nwai4150_01.prcContrNum)) {
        ZYPEZDItemValueSetter.setValue(this.prcContrNum, this.nwai415001.prcContrNum.getValue());
        //        } else {
        //            this.prcContrNum.clear();
        //        }

        if (ZYPCommonFunc.hasValue(this.nwai415001.somCsmpNum) && !"0".equals(this.nwai415001.somCsmpNum)) {
            ZYPEZDItemValueSetter.setValue(this.csmpContrNum, this.nwai415001.somCsmpNum.getValue());
            Map<String, Object> dlrRefNumMap = NWXC412001.getCSANumberByAcctCSMPNum(glblCmpyCd, this.sellToCustCd.getValue(), this.csmpContrNum.getValue(), this.slsDt);
            if (dlrRefNumMap != null) {
                ZYPEZDItemValueSetter.setValue(this.dlrRefNum, (String) dlrRefNumMap.get("DLR_REF_NUM"));
                pCsmpPrcListCd = (String) dlrRefNumMap.get("PRC_CATG_CD");
            } else {
                this.dlrRefNum.clear();
            }
        } else {
            this.csmpContrNum.clear();
            this.dlrRefNum.clear();
        }

        this.ordSgnDt.clear();
        ZYPEZDItemValueSetter.setValue(this.aquNum, this.ordSrcRefNum);

        String slsRepId = S21StringUtil.subStringByLength(this.nwai415001.somSlsRepId.getValue(), 0, CUTOFF_LEN.SLS_REP_ID.getLen());
        if (!getCacheSlsRepTocCdMap().containsKey(slsRepId)) {
            Map<String, Object> slsRepTocCdMap //
            = NWXC412001.getSlsRepTocByResourceId(glblCmpyCd, slsRepId, this.slsDt);
            if (slsRepTocCdMap != null) {
                ZYPEZDItemValueSetter.setValue(this.slsRepTocCd, (String) slsRepTocCdMap.get("SLS_REP_TOC_CD"));
                getCacheSlsRepTocCdMap().put(slsRepId, slsRepTocCdMap);
            } else {
                getCacheSlsRepTocCdMap().put(slsRepId, new HashMap<String, Object>());
            }
        } else {
            Map<String, Object> slsRepTocCdMap = getCacheSlsRepTocCdMap().get(slsRepId);
            ZYPEZDItemValueSetter.setValue(this.slsRepTocCd, (String) slsRepTocCdMap.get("SLS_REP_TOC_CD"));
        }
        this.loanPerDaysAot.clear();
        String leaseCmpyPoNum = null;
        String leasePmtFreqCd = null;
        BigDecimal leaseTermMthAot = BigDecimal.ZERO;
        BigDecimal leaseTotPmtAmt = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(this.dsOrdCatgCd) //
                && this.dsOrdCatgCd.getValue().equals(DS_ORD_CATG.LEASE_CSA)) {
            leaseCmpyPoNum = S21StringUtil.subStringByLength(this.nwai415001.leasePrchOrderTxt.getValue(), 0, CUTOFF_LEN.LEASE_PRCH_ORDER_TXT.getLen());
            leasePmtFreqCd = LEASE_PMT_FREQ_MONTHLY;
            leaseTermMthAot = this.nwai415001.somLeaseTermAot.getValue();
            leaseTotPmtAmt = this.nwai415001.leasePmtAmt.getValue();
        }
        ZYPEZDItemValueSetter.setValue(this.leaseCmpyPoNum, leaseCmpyPoNum);
        this.leaseTermCd.clear();
        ZYPEZDItemValueSetter.setValue(this.leasePmtFreqCd, leasePmtFreqCd);
        ZYPEZDItemValueSetter.setValue(//
                this.leaseTermMthAot //
                , leaseTermMthAot.remainder(BigDecimal.ONE.movePointRight(CUTOFF_LEN.LEASE_TERM_MTH_AOT.getLen())));
        this.ordLogTpCd.clear();
        this.crRebilRsnCatgCd.clear();
        this.origOrdNum.clear();
        ZYPEZDItemValueSetter.setValue(this.sendInvFlg, ZYPConstant.FLG_ON_Y);
        this.reBillPairCpoOrdNum.clear();
        ZYPEZDItemValueSetter.setValue(this.ordHdrEdtblFlg, ZYPConstant.FLG_ON_Y);

        String leasePrchOptCd //
        = NWXC412001.getLeasePrchOptCd(glblCmpyCd, CPO_SRC_TP.EOPS, INTERFACE_ID.NWAI4150.name(), this.nwai415001.leaseTpTxt.getValue());
        if (leasePrchOptCd != null) {
            ZYPEZDItemValueSetter.setValue(//
                    this.leasePrchOptCd, (String) leasePrchOptCd);
        }

        //        Map<String, Object> realFairIndMap //
        //        = NWXC412001.getRealFairInd(glblCmpyCd, CPO_SRC_TP.EOPS, INTERFACE_ID.NWAI4150.name(), this.nwai4150_01.realFairIndSomTxt.getValue());
        //        if (realFairIndMap != null) {
        //            ZYPEZDItemValueSetter.setValue(//
        //                    this.leasePrchOptCd, (String) realFairIndMap.get("TRGT_ATTRB_TXT_01"));
        //        }
        //        Map<String, Object> checkWithAgreeMap //
        //        = NWXC412001.getCheckWithAgree(glblCmpyCd, CPO_SRC_TP.EOPS, INTERFACE_ID.NWAI4150.name(), this.nwai4150_01.chkAgmtIndSomTxt.getValue());
        //        if (checkWithAgreeMap != null) {
        //            ZYPEZDItemValueSetter.setValue(//
        //                    this.leasePrchOptCd, (String) checkWithAgreeMap.get("TRGT_ATTRB_TXT_01"));
        //        }
        //        if (!ZYPCommonFunc.hasValue(this.leasePrchOptCd)) {
        //            ZYPEZDItemValueSetter.setValue(//
        //                    this.leasePrchOptCd //
        //                    , NWXC412001.getLeasePrchOptCd(glblCmpyCd, this.cpoSrcTpCd.getValue(), this.nwai4150_01.interfaceId.getValue(), this.nwai4150_01.leaseTpTxt.getValue()));
        //        }
        this.invCmntTxt.clear();
        ZYPEZDItemValueSetter.setValue(this.leaseTotPmtAmt, leaseTotPmtAmt);

        if (S21StringUtil.isEquals(this.nwai415001.dclnMaintIndSomTxt.getValue(), FLG.YES.name())) {
            ZYPEZDItemValueSetter.setValue(this.dclnSvcCd, ZYPConstant.FLG_ON_1);
        } else {
            ZYPEZDItemValueSetter.setValue(this.dclnSvcCd, ZYPConstant.FLG_OFF_0);
        }
        ZYPEZDItemValueSetter.setValue(this.maintOnlyFlg, ZYPConstant.FLG_OFF_N); // QC#16266

        for (EopsDsImptOrdEopsApvlBean apvlBean : dsImptOrdEopsApvlBeanList) {
            apvlBean.doImptMapping(glblCmpyCd, salesDate);
        }

        for (EopsDsImptOrdEopsPrftBean prftBean : dsImptOrdEopsPrftBeanList) {
            prftBean.doImptMapping(glblCmpyCd, salesDate);
        }

        this.dsImptOrdDelyInfoBean.doImptMapping(glblCmpyCd, salesDate);

        for (EopsDsImptOrdCtacPsnBean ctacPsnBean : this.dsImptOrdCtacPsnBeanList) {
            ctacPsnBean.doImptMapping(glblCmpyCd, salesDate);
        }

        // import order mapping
        for (EopsDsImptOrdConfigBean configBean : this.dsImptOrdConfigBeanList) {
            if (!configBean.doImptMapping(glblCmpyCd, salesDate)) {
                isSuccess = false;
            }
        }
        this.dsImptOrdSlsCrBean.doImptMapping(glblCmpyCd, salesDate);
        this.dsImptOrdIstlInfoBean.doImptMapping(glblCmpyCd, salesDate);
        String custIstlFlg = NWXC412001.getSvcCustIstlFlg(glblCmpyCd, this.dsImptOrdIstlInfoBean.svcIstlRuleNum.getValue());
        for (EopsDsImptOrdDtlBean bean : this.dsImptOrdDtlBeanList) {
            ZYPEZDItemValueSetter.setValue(bean.custIstlFlg, custIstlFlg);
        }

        // import service price mapping
        for (EopsDsImptSvcDtlBean svcDtl : this.dsImptSvcDtlBeanList) {
            if (!svcDtl.doImptMapping(glblCmpyCd, salesDate)) {
                isSuccess = false;
            }
        }

        // extension mapping
        isSuccess = doImptMppingExt(glblCmpyCd, salesDate);

        return isSuccess;
    }

    /**
     * getDefaultPrcCatgCd
     * @return pDefaultPrcCatgCd
     */
    public String getDefaultPrcCatgCd() {
        return this.pDefaultPrcCatgCd;
    }

    /**
     * setCacheShipToCustMap
     * @param pCacheShipToCustMap Map<String, Map<String, Object>>
     */
    public void setCacheShipToCustMap(Map<String, Map<String, Object>> pCacheShipToCustMap) {
        cacheShipToCustMap = (HashMap<String, Map<String, Object>>) pCacheShipToCustMap;
    }

    /**
     * getCacheShipToCustMap
     * @return cacheShipToCustMap
     */
    public Map<String, Map<String, Object>> getCacheShipToCustMap() {
        return cacheShipToCustMap;
    }

    private boolean doImptMppingExt(String glblCmpyCd, String salesDate) {

        doImptOrdConfigMppingExt(glblCmpyCd, salesDate);

        doImptOrdMppingExt(glblCmpyCd, salesDate);

        doImptOrdDtlMppingExt(glblCmpyCd, salesDate);

        doImptOrdRtrnDtlMppingExt(glblCmpyCd, salesDate);

        doImptSvcDtlMappingExt(glblCmpyCd, salesDate);

        return true;
    }

    private void doImptOrdMppingExt(String glblCmpyCd, String salesDate) {
        setDclnSvcCdForHeader();
    }

    private void setDclnSvcCdForHeader() {

        boolean exsistOutbound = false;
        for (EopsDsImptOrdConfigBean config : this.dsImptOrdConfigBeanList) {

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

    private void doImptOrdConfigMppingExt(String glblCmpyCd, String salesDate) {

        // out bound
        int dsOrdPosnNumIndex = 0;
        for (EopsDsImptOrdConfigBean config : this.dsImptOrdConfigBeanList) {

            if (S21StringUtil.isEquals(config.configCatgCd.getValue(), CONFIG_CATG.OUTBOUND)) {

                ZYPEZDItemValueSetter.setValue(config.dsOrdPosnNum, String.valueOf(dsOrdPosnNumIndex + 1));
                dsOrdPosnNumIndex++;
            }
        }

        // in bound
        dsOrdPosnNumIndex = 0;
        for (EopsDsImptOrdConfigBean config : this.dsImptOrdConfigBeanList) {

            if (S21StringUtil.isEquals(config.configCatgCd.getValue(), CONFIG_CATG.INBOUND)) {

                ZYPEZDItemValueSetter.setValue(config.dsOrdPosnNum, String.valueOf(dsOrdPosnNumIndex + 1));

                dsOrdPosnNumIndex++;
            }
        }

        for (EopsDsImptOrdConfigBean config : this.dsImptOrdConfigBeanList) {
            ZYPEZDItemValueSetter.setValue(config.configTpCd, getConfigTpCd(glblCmpyCd, salesDate, config));
        }

        // address label text
        setAddrLbTxt(CONFIG_CATG.OUTBOUND);
        setAddrLbTxt(CONFIG_CATG.INBOUND);

    }

    private void setAddrLbTxt(String configCatgCd) {

        String maxAddrLblTxt = "";
        String currentAddrLblTxt = "";
        for (EopsDsImptOrdConfigBean config : this.dsImptOrdConfigBeanList) {

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
        for (EopsDsImptOrdConfigBean config : this.dsImptOrdConfigBeanList) {

            if (!S21StringUtil.isEquals(config.configCatgCd.getValue(), configCatgCd)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(config.addrLbTxt)) {
                continue;
            }

            String currentAddr;
            if (S21StringUtil.isEquals(configCatgCd, CONFIG_CATG.OUTBOUND)) {
                currentAddr //
                = S21StringUtil.concatStrings(//
                        config.nwai415012.somShipToAddr_01.getValue() //
                        , config.nwai415012.somShipToAddr_02.getValue() //
                        , config.nwai415012.somCityAddr.getValue() //
                        , config.nwai415012.somStCd.getValue());
            } else {

                if (CONFIG_TYPE.OCE_PROMOTION_RMA.equals(config.configType)) {
                    if (config.nwai4150_14List != null && !config.nwai4150_14List.isEmpty()) {

                        currentAddr //
                        = S21StringUtil.concatStrings(config.nwai4150_14List.get(0).somPrmoSerNum.getValue());
                    } else {

                        currentAddr = "";
                    }

                } else if (CONFIG_TYPE.EOPS_RMA.equals(config.configType)) {
                    if (config.nwai4150_24List != null && !config.nwai4150_24List.isEmpty()) {

                        currentAddr //
                        = S21StringUtil.concatStrings(config.nwai4150_24List.get(0).somDescSerNum.getValue());
                    } else {

                        currentAddr = "";
                    }
                } else {

                    if (ZYPCommonFunc.hasValue(config.nwai415005.somDescSerNum)) {
                        currentAddr //
                        = S21StringUtil.concatStrings(//
                                config.nwai415005.somSoftCostId.getValue() //
                                , config.nwai415005.somDescSerNum.getValue());
                    } else if (config.serIf06TMsg != null //
                            && ZYPCommonFunc.hasValue(config.serIf06TMsg.somDescSerNum)) {
                        currentAddr //
                        = S21StringUtil.concatStrings(config.nwai415005.somSoftCostId.getValue(), config.serIf06TMsg.somDescSerNum.getValue());
                    } else {
                        currentAddr = S21StringUtil.concatStrings(config.nwai415005.somSoftCostId.getValue(), "");
                    }
                }
            }
            for (EopsDsImptOrdConfigBean config2 : this.dsImptOrdConfigBeanList) {

                if (!S21StringUtil.isEquals(config2.configCatgCd.getValue(), configCatgCd)) {
                    continue;
                }
                if (!ZYPCommonFunc.hasValue(config2.addrLbTxt)) {
                    continue;
                }

                String lbAddr;
                if (S21StringUtil.isEquals(configCatgCd, CONFIG_CATG.OUTBOUND)) {
                    lbAddr //
                    = S21StringUtil.concatStrings(//
                            config2.nwai415012.somShipToAddr_01.getValue() //
                            , config2.nwai415012.somShipToAddr_02.getValue() //
                            , config2.nwai415012.somCityAddr.getValue() //
                            , config2.nwai415012.somStCd.getValue());

                } else {

                    if (CONFIG_TYPE.OCE_PROMOTION_RMA.equals(config2.configType)) {

                        if (config2.nwai4150_14List == null || config2.nwai4150_14List.isEmpty()) {
                            continue;
                        }
                        lbAddr = S21StringUtil.concatStrings(config2.nwai4150_14List.get(0).somPrmoSerNum.getValue());

                    } else if (CONFIG_TYPE.EOPS_RMA.equals(config2.configType)) {
                        if (config2.nwai4150_24List == null || config2.nwai4150_24List.isEmpty()) {
                            continue;
                        }
                        lbAddr = S21StringUtil.concatStrings(config2.nwai4150_24List.get(0).somDescSerNum.getValue());

                    } else {
                        if (ZYPCommonFunc.hasValue(config2.nwai415005.somDescSerNum)) {
                            lbAddr //
                            = S21StringUtil.concatStrings(//
                                    config2.nwai415005.somSoftCostId.getValue() //
                                    , config2.nwai415005.somDescSerNum.getValue());
                        } else if (config2.serIf06TMsg != null && ZYPCommonFunc.hasValue(config2.serIf06TMsg.somDescSerNum)) {
                            lbAddr //
                            = S21StringUtil.concatStrings(//
                                    config2.nwai415005.somSoftCostId.getValue() //
                                    , config2.serIf06TMsg.somDescSerNum.getValue());
                        } else {
                            lbAddr //
                            = S21StringUtil.concatStrings(config2.nwai415005.somSoftCostId.getValue(), "");
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

    private String getConfigTpCd(String glblCmpyCd, String salesDate, EopsDsImptOrdConfigBean config) {

        if (S21StringUtil.isEquals(config.configCatgCd.getValue(), CONFIG_CATG.OUTBOUND)) {
            if (config.isAddAccessoryToExistingConfigurationFlag) {
                return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.ADD_TO_CONFIG;
            } else if (config.isExistingConfigurationFlag) {
                return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.EXISTING;
            }

            return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.NEW;

        } else {

            if (CONFIG_TYPE.TRADE_IN_RMA.equals(config.configType)) {

                //Non Canon Equipment
                String somNonCanonEquipment //
                = (String) this.getVarCharConstMap().get(VAR_CHAR_CONST_NM.EOPS_TRADE_IN_NON_CANON.name());
                if (ZYPCommonFunc.hasValue(somNonCanonEquipment) //
                        && !somNonCanonEquipment.equals(config.nwai415005.somMdlTxt.getValue()) && ZYPCommonFunc.hasValue(config.nwai415005.somDescSerNum)) {

                    List<Map<String, Object>> existslist //
                    = NWXC412001.getConfigMasterDetailList(glblCmpyCd, config.nwai415005.somDescSerNum.getValue(), salesDate);
                    if (existslist != null && existslist.size() > 0) {
                        return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.RETURN_EXISTING_IB;
                    }
                    return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.RETURN_NEW;

                } else if (ZYPCommonFunc.hasValue(somNonCanonEquipment) //
                        && somNonCanonEquipment.equals(config.nwai415005.somMdlTxt.getValue())) {

                    return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.RETURN_NEW;
                }
            }

            if (CONFIG_TYPE.OCE_PROMOTION_RMA.equals(config.configType)) {

                List<Map<String, Object>> existslist //
                = NWXC412001.getConfigMasterDetailList(glblCmpyCd, config.nwai415014.somPrmoSerNum.getValue(), salesDate);
                if (existslist != null && existslist.size() > 0) {
                    return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.RETURN_EXISTING_IB;
                }
                return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.RETURN_NEW;
            }

            if (CONFIG_TYPE.EOPS_RMA.equals(config.configType)) {

                List<Map<String, Object>> existslist //
                = NWXC412001.getConfigMasterDetailList(glblCmpyCd, config.nwai415024.somDescSerNum.getValue(), salesDate);
                if (existslist != null && existslist.size() > 0) {
                    return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.RETURN_EXISTING_IB;
                }
                return com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP.RETURN_NEW;
            }

            for (EopsDsImptOrdRtrnDtlBean detail : config.dsImptOrdDtlRtrnBeanList) {

                if (detail.nwai415006 != null) {

                    if (ZYPCommonFunc.hasValue(detail.nwai415006.somDescSerNum)) {

                        List<Map<String, Object>> existslist //
                        = NWXC412001.getConfigMasterDetailList(glblCmpyCd, detail.nwai415006.somDescSerNum.getValue(), salesDate);
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

    private void doImptOrdDtlMppingExt(String glblCmpyCd, String slsDt) {

        // position number
        // order source reference line number
        for (EopsDsImptOrdConfigBean config : this.dsImptOrdConfigBeanList) {

            if (S21StringUtil.isEquals(config.configCatgCd.getValue(), CONFIG_CATG.OUTBOUND)) {

                int ordSrcRefLineNumIndex = 0;
                for (EopsDsImptOrdDtlBean detail : config.dsImptOrdDtlBeanList) {

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
        for (EopsDsImptOrdConfigBean config : this.dsImptOrdConfigBeanList) {

            if (S21StringUtil.isEquals(config.configCatgCd.getValue(), CONFIG_CATG.INBOUND)) {

                int ordSrcRefLineNumIndex = 0;
                for (EopsDsImptOrdRtrnDtlBean detail : config.dsImptOrdDtlRtrnBeanList) {

                    ZYPEZDItemValueSetter.setValue(detail.dsOrdPosnNum, config.dsOrdPosnNum);
                    ZYPEZDItemValueSetter.setValue(detail.ordSrcRefLineNum, String.valueOf(ordSrcRefLineNumIndex + 1));
                    ordSrcRefLineNumIndex++;
                }
            }
        }
    }

    private void doImptSvcDtlMappingExt(String glblCmpyCd, String slsDt2) {

        int dsImptSvcLineNumIndex = 0;
        for (EopsDsImptSvcDtlBean svcDtail : this.dsImptSvcDtlBeanList) {

            if (!ZYPCommonFunc.hasValue(svcDtail.dsImptSvcLineNum)) {

                ZYPEZDItemValueSetter.setValue(svcDtail.dsImptSvcLineNum, BigDecimal.valueOf(dsImptSvcLineNumIndex + 1));
                dsImptSvcLineNumIndex++;
            }
        }
    }

    @Override
    public void addErrorInfo(NWXC220001ErrorInfo errorInfo) {
        this.errInfoList.add(new NWAB415001ErrorInfo(this.nwai415001.transactionId.getValue(), this.nwai415001.unitId.getValue(), this.nwai415001.seqNumber.getValue(), errorInfo));
    }

    @Override
    public void addErrorInfo(List<NWXC220001ErrorInfo> errorInfoList) {
        for (NWXC220001ErrorInfo errorInfo : errorInfoList) {
            addErrorInfo(errorInfo);
        }
    }

    @Override
    public List<NWAB415001ErrorInfo> getErrorInfo() {
        List<NWAB415001ErrorInfo> errList = new ArrayList<NWAB415001ErrorInfo>();

        errList.addAll(this.errInfoList);

        for (EopsDsImptOrdConfigBean configBean : this.dsImptOrdConfigBeanList) {
            errList.addAll(configBean.getErrorInfo());
        }
        return errList;
    }

    @Override
    public boolean hasError() {
        return (this.getErrorInfo().size() > 0);
    }

    public String getDsOrdTpCd() {
        return this.pDsOrdTpCd;
    }

    public void setCacheModelMap(Map<String, BigDecimal> pCacheModelMap) {
        cacheModelMap = pCacheModelMap;
    }

    public Map<String, BigDecimal> getCacheModelMap() {
        return cacheModelMap;
    }

    public void setCacheVndMap(Map<String, NPZC113001PMsg> pCacheVndMap) {
        cacheVndMap = pCacheVndMap;
    }

    public Map<String, NPZC113001PMsg> getCacheVndMap() {
        return cacheVndMap;
    }

    public String getOutBndDsOrdLineCatgCd() {
        return this.pOutBndDsOrdLineCatgCd;
    }

    public String getDsOrdCatgCd() {
        return this.pDsOrdCatgCd;
    }

    public void setCacheDefaultWHMap(Map<String, NWZC180001PMsg> pCacheDefaultWhMap) {
        cacheDefaultWhMap = pCacheDefaultWhMap;
    }

    public Map<String, NWZC180001PMsg> getCacheDefaultWHMap() {
        return cacheDefaultWhMap;
    }

    public String getCsmpPrcListCd() {
        return this.pCsmpPrcListCd;
    }

    public String getInBndDsOrdLineCatgCd() {
        return this.pInBndDsOrdLineCatgCd;
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

    public void setCachePrcMtrPkgPkMap(Map<String, BigDecimal> pCachePrcMtrPkgPkMap) {
        cachePrcMtrPkgPkMap = pCachePrcMtrPkgPkMap;
    }

    public Map<String, BigDecimal> getCachePrcMtrPkgPkMap() {
        return cachePrcMtrPkgPkMap;
    }

    public void addDsImptOrdCtacPsnBean() {
        new EopsDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.HEADER, CTAC_PSN_SUB_TYPE.H_DELIVERY, this);
        new EopsDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.HEADER, CTAC_PSN_SUB_TYPE.H_BILLING, this);
        new EopsDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.HEADER, CTAC_PSN_SUB_TYPE.H_IT, this);
        new EopsDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.HEADER, CTAC_PSN_SUB_TYPE.H_SUBSERVICES, this);

        String admin = "ADMIN";
        String usr = "USER";

        String csaPmtTpTxt = NWXC220001Util.convToString(this.nwai415001.csaPmtCtacTpTxt.getValue(), "");

        if (admin != null && csaPmtTpTxt.toUpperCase().indexOf(admin) >= 0) {
            new EopsDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.HEADER, CTAC_PSN_SUB_TYPE.H_EMANAGE_ADMIN, this);
        }

        if (usr != null && csaPmtTpTxt.toUpperCase().indexOf(usr) >= 0) {
            new EopsDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.HEADER, CTAC_PSN_SUB_TYPE.H_EMANAGE_USER, this);
        }
    }

    public void createImptConfigDtlForApproval(EopsDsImptOrdBean dsImptOrdBean, List<NWAI4150_02TMsg> nwai415002msgList, List<NWAI4150_03TMsg> nwai415003msgList) {
        List<NWAI4150_03TMsg> targetNwai4150_03List;
        EopsDsImptOrdEopsApvlBean apvlBean;

        for (NWAI4150_02TMsg if02TMsg : nwai415002msgList) {
            // *****************************************************************
            // Process (2)
            // *****************************************************************
            apvlBean = new EopsDsImptOrdEopsApvlBean(dsImptOrdBean, if02TMsg);
            targetNwai4150_03List = getNWAI4150_03TMsgByKey(if02TMsg.somApvlId.getValue(), nwai415003msgList);

            for (NWAI4150_03TMsg if03TMsg : targetNwai4150_03List) {
                // *************************************************************
                // Process (3)
                // *************************************************************
                new EopsDsImptOrdApvlNoteBean(apvlBean, if03TMsg);
            }
        }
    }

    public void createImptConfigDtlForProfitability(EopsDsImptOrdBean dsImptOrdBean, List<NWAI4150_04TMsg> nwai415004msgList) {
        // *****************************************************************
        // Process (4)
        // *****************************************************************
        for (NWAI4150_04TMsg if04TMsg : nwai415004msgList) {
            new EopsDsImptOrdEopsPrftBean(dsImptOrdBean, if04TMsg);
        }
    }

    public void createImptConfigDtlFromNWAI415005(//
            EopsDsImptOrdBean dsImptOrdBean, List<NWAI4150_05TMsg> nwai4150_05List, List<NWAI4150_06TMsg> nwai4150_06List) {

        EopsDsImptOrdConfigBean dsImptOrdConfigBean;
        EopsDsImptOrdConfigBean lastShipConfigBean = dsImptOrdBean.lastShipConfigBean;
        EopsDsImptOrdDtlBean dtlBean;
        EopsDsImptOrdRtrnDtlBean rtrnDtlBean;
        List<NWAI4150_06TMsg> targetNwai4150_06List = new ArrayList<NWAI4150_06TMsg>();
        String quoteLineTpTxt, somMercItemCd, glblCmpyCd;
        Map<String, Object> c4_xrefMap;
        String c4_trgtAttrbTxt01, c4_trgtAttrbTxt02;
        boolean notExistC4Xref, existC5Xref;
        glblCmpyCd = dsImptOrdBean.glblCmpyCd.getValue();
        List<String> c6XrefList = NWXC412001.getUpgradeReturnsItemCodeList(glblCmpyCd, CPO_SRC_TP.EOPS, INTERFACE_ID.NWAI4150.name());

        for (NWAI4150_05TMsg if05TMsg : nwai4150_05List) {

            somMercItemCd = if05TMsg.somMercItemCd.getValue();
            quoteLineTpTxt = if05TMsg.quoteLineTpTxt.getValue();

            c4_xrefMap = NWXC412001.getSoftCostItemInfo(glblCmpyCd, CPO_SRC_TP.EOPS, INTERFACE_ID.NWAI4150.name(), somMercItemCd, quoteLineTpTxt);
            c4_trgtAttrbTxt01 = "";
            c4_trgtAttrbTxt02 = "";
            if (c4_xrefMap != null) {
                c4_trgtAttrbTxt01 = (String) c4_xrefMap.get("TRGT_ATTRB_TXT_01");
                c4_trgtAttrbTxt02 = (String) c4_xrefMap.get("TRGT_ATTRB_TXT_02");
            }

            notExistC4Xref = (c4_xrefMap == null || !(ZYPConstant.FLG_OFF_N.equals(c4_trgtAttrbTxt02) && c4_trgtAttrbTxt01.equals(if05TMsg.quoteLineTpTxt.getValue())));
            existC5Xref = NWXC412001.existBuyoutItemCode(glblCmpyCd, CPO_SRC_TP.EOPS, INTERFACE_ID.NWAI4150.name(), somMercItemCd);

            // *****************************************************************
            // add_softcosts
            // *****************************************************************
            dtlBean = null;
            if (QUOTE_LINE_TP_TRADEIN.equals(quoteLineTpTxt)) {

                if (notExistC4Xref) {

                    if (!existC5Xref) {
                        // *****************************************************
                        // Process (8) (TRADE-IN (RMA))
                        // *****************************************************
                        targetNwai4150_06List = getNWAI4150_06TMsgByKey(if05TMsg.somQuoteId.getValue(), if05TMsg.somSoftCostId.getValue(), nwai4150_06List);
                        if (targetNwai4150_06List != null && targetNwai4150_06List.size() > 0) {
                            dsImptOrdConfigBean = new EopsDsImptOrdConfigBean(CONFIG_TYPE.TRADE_IN_RMA, dsImptOrdBean, if05TMsg, null, targetNwai4150_06List.get(0));
                        } else {
                            dsImptOrdConfigBean = new EopsDsImptOrdConfigBean(CONFIG_TYPE.TRADE_IN_RMA, dsImptOrdBean, if05TMsg, null, null);
                        }
                        dsImptOrdBean.addEopsDsImptOrdConfigBean(dsImptOrdConfigBean);
                        //dsImptOrdConfigBean.nwai4120_06List.addAll(targetNwai4120_06List);

                        new EopsDsImptOrdIstlInfoBean(ISTL_INFO_TYPE.TRADE_IN_RMA, dsImptOrdConfigBean);
                        new EopsDsImptOrdDelyInfoBean(DELY_INFO_TYPE.TRADE_IN_RMA, dsImptOrdConfigBean);
                        dsImptOrdConfigBean.addDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.TRADE_IN_RMA);

                        // *****************************************************
                        // Process (9) (TRADE-IN (RMA))
                        // *****************************************************
                        //get all configuration lines from main machine serial#
                        rtrnDtlBean = new EopsDsImptOrdRtrnDtlBean(RTRN_DTL_TYPE.TRADE_IN_RMA, dsImptOrdConfigBean, if05TMsg, null);
                        new EopsDsImptRtrnPrcCalcBean(RTRN_DTL_TYPE.TRADE_IN_RMA, rtrnDtlBean);

                        // *****************************************************
                        // Process (9) (TRADE-IN)
                        // *****************************************************
                        dtlBean = new EopsDsImptOrdDtlBean(DTL_TYPE.TRADE_IN, lastShipConfigBean, if05TMsg);
                        dtlBean.nwai4150_06List.addAll(targetNwai4150_06List);
                        new EopsDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.TRADE_IN, dtlBean);
                    }
                }

            } else if (QUOTE_LINE_TP_UPGRADEBUYOUTREBATE.equals(quoteLineTpTxt)) {

                if (notExistC4Xref) {

                    if (!existC5Xref) {

                        targetNwai4150_06List //
                        = getNWAI4150_06TMsgByKey(if05TMsg.somQuoteId.getValue(), if05TMsg.somSoftCostId.getValue(), nwai4150_06List);
                        List<NWAI4150_06TMsg> serialList = new ArrayList<NWAI4150_06TMsg>();
                        if (targetNwai4150_06List != null) {

                            for (NWAI4150_06TMsg nwai4120_06 : targetNwai4150_06List) {

                                if (ZYPCommonFunc.hasValue(nwai4120_06.somDescSerNum) //
                                        && !"0".equals(nwai4120_06.somDescSerNum.getValue())) {

                                    serialList.add(nwai4120_06);
                                }
                            }
                        }
                        if (serialList.isEmpty()) {

                            // *****************************************************
                            // Process (10) (UPGRADEBUYOUTREBATE)
                            // single
                            // *****************************************************
                            dtlBean = new EopsDsImptOrdDtlBean(DTL_TYPE.UPGRADEBUYOUTREBATE, lastShipConfigBean, if05TMsg);
                            dtlBean.nwai4150_06List.addAll(targetNwai4150_06List);
                            new EopsDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.UPGRADEBUYOUTREBATE, dtlBean);

                        } else {

                            // *****************************************************
                            // Process (10) (UPGRADEBUYOUTREBATE)
                            // partial(per serial)
                            // *****************************************************
                            for (NWAI4150_06TMsg nwai4150_06 : serialList) {

                                if (!ZYPCommonFunc.hasValue(nwai4150_06.somSoftCostItemId)) {

                                    continue;
                                }

                                List<NWAI4150_06TMsg> targetSerialList = new ArrayList<NWAI4150_06TMsg>();
                                for (NWAI4150_06TMsg target : targetNwai4150_06List) {

                                    if (!ZYPCommonFunc.hasValue(target.somSoftCostItemId)) {

                                        continue;
                                    }

                                    if (target.somSoftCostItemId.getValue().compareTo(nwai4150_06.somSoftCostItemId.getValue()) != 0) {

                                        continue;
                                    }

                                    targetSerialList.add(target);
                                }
                                dtlBean = new EopsDsImptOrdDtlBean(DTL_TYPE.UPGRADEBUYOUTREBATE, lastShipConfigBean, if05TMsg);
                                dtlBean.nwai4150_06List.addAll(targetSerialList);
                                new EopsDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.UPGRADEBUYOUTREBATE, dtlBean);
                            }
                        }
                    }
                }

            } else {

                if (notExistC4Xref) {
                    if (!existC5Xref) {
                        // *****************************************************
                        // Process (11) (NOT UPGRADEBUYOUTREBATE AND TRADE-IN)
                        // *****************************************************
                        dtlBean = new EopsDsImptOrdDtlBean(DTL_TYPE.NOT_UPGRADEBUYOUTREBATE_AND_TRADE_IN, lastShipConfigBean, if05TMsg);
                        new EopsDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.NOT_UPGRADEBUYOUTREBATE_AND_TRADE_IN, dtlBean);
                    }
                }
            }

            // *****************************************************************
            // create_buyout_lines
            // *****************************************************************
            if (notExistC4Xref) {

                if (existC5Xref) {

                    targetNwai4150_06List = getNWAI4150_06TMsgByKey(if05TMsg.somQuoteId.getValue(), if05TMsg.somSoftCostId.getValue(), nwai4150_06List);
                    List<NWAI4150_06TMsg> serialList = new ArrayList<NWAI4150_06TMsg>();
                    if (targetNwai4150_06List != null) {

                        for (NWAI4150_06TMsg nwai412006 : targetNwai4150_06List) {

                            if (ZYPCommonFunc.hasValue(nwai412006.somDescSerNum) && !"0".equals(nwai412006.somDescSerNum.getValue())) {

                                serialList.add(nwai412006);
                            }
                        }
                    }
                    if (serialList.isEmpty()) {

                        // *****************************************************
                        // Process (12) (BUYOUT) single
                        // *****************************************************
                        dtlBean = new EopsDsImptOrdDtlBean(DTL_TYPE.BUYOUT, lastShipConfigBean, if05TMsg);
                        dtlBean.nwai4150_06List.addAll(targetNwai4150_06List);
                        new EopsDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.BUYOUT, dtlBean);

                    } else {

                        // *****************************************************
                        // Process (12) (BUYOUT)
                        // partial(per serial)
                        // *****************************************************
                        for (NWAI4150_06TMsg nwai415006 : serialList) {

                            if (!ZYPCommonFunc.hasValue(nwai415006.somSoftCostItemId)) {

                                continue;
                            }

                            List<NWAI4150_06TMsg> targetSerialList = new ArrayList<NWAI4150_06TMsg>();
                            for (NWAI4150_06TMsg target : targetNwai4150_06List) {

                                if (!ZYPCommonFunc.hasValue(target.somSoftCostItemId)) {

                                    continue;
                                }

                                if (target.somSoftCostItemId.getValue().compareTo(nwai415006.somSoftCostItemId.getValue()) != 0) {

                                    continue;
                                }

                                targetSerialList.add(target);
                            }
                            dtlBean = new EopsDsImptOrdDtlBean(DTL_TYPE.BUYOUT, lastShipConfigBean, if05TMsg);
                            dtlBean.nwai4150_06List.addAll(targetSerialList);
                            new EopsDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.BUYOUT, dtlBean);
                        }
                    }
                }
            }

            // *****************************************************************
            // create_upgrade_returns
            // *****************************************************************
            if (c6XrefList.contains(somMercItemCd)) {

                targetNwai4150_06List = getNWAI4150_06TMsgByKey(if05TMsg.somQuoteId.getValue(), if05TMsg.somSoftCostId.getValue(), nwai4150_06List);
                List<NWAI4150_06TMsg> serialList = new ArrayList<NWAI4150_06TMsg>();
                NWAI4150_06TMsg noSerNwai415006 = null;
                NWAI4150_06TMsg pickUpNwai415006 = null;
                if (targetNwai4150_06List != null) {

                    for (NWAI4150_06TMsg nwai415006 : targetNwai4150_06List) {

                        if (ZYPCommonFunc.hasValue(nwai415006.somDescSerNum) //
                                && !"0".equals(nwai415006.somDescSerNum.getValue())) {
                            serialList.add(nwai415006);
                        } else {
                            noSerNwai415006 = nwai415006;
                        }

                        if (ZYPCommonFunc.hasValue(nwai415006.pickUpFirstNm)) {
                            pickUpNwai415006 = nwai415006;
                        }

                    }
                    if (pickUpNwai415006 != null) {
                        noSerNwai415006 = pickUpNwai415006;
                    }
                }
                if (serialList.isEmpty()) {

                    // *************************************************************
                    // Process (13) (UPGRADE RETURN (RMA)) single
                    // *************************************************************
                    createUpgradeReturnRma(dsImptOrdBean, targetNwai4150_06List, if05TMsg, null, noSerNwai415006);

                } else {

                    // *************************************************************
                    // Process (13) (UPGRADE RETURN (RMA)) partial(per
                    // serial)
                    // *************************************************************
                    for (NWAI4150_06TMsg nwai415006 : serialList) {

                        if (!ZYPCommonFunc.hasValue(nwai415006.somSoftCostItemId)) {

                            continue;
                        }

                        List<NWAI4150_06TMsg> targetSerialList = new ArrayList<NWAI4150_06TMsg>();
                        for (NWAI4150_06TMsg target : targetNwai4150_06List) {

                            if (!ZYPCommonFunc.hasValue(target.somSoftCostItemId)) {
                                continue;
                            }

                            if (target.somSoftCostItemId.getValue().compareTo(nwai415006.somSoftCostItemId.getValue()) != 0) {
                                continue;
                            }

                            targetSerialList.add(target);
                        }
                        createUpgradeReturnRma(dsImptOrdBean, targetSerialList, if05TMsg, nwai415006, noSerNwai415006);
                    }
                }
            }
        }
    }

    public void createImptConfigDtlFromNWAI415012(//
            EopsDsImptOrdBean dsImptOrdBean //
            , List<NWAI4150_12TMsg> nwai415012List //
            , List<NWAI4150_13TMsg> nwai415013List //
            , List<NWAI4150_14TMsg> nwai415014List //
            , List<NWAI4150_10TMsg> nwai415010List //
            , List<NWAI4150_23TMsg> nwai415023List) {
        // Store Map (somConfigId - somConfigQty)
        Map<BigDecimal, BigDecimal> configQtyMap = new HashMap<BigDecimal, BigDecimal>();
        if (nwai415010List != null && !nwai415010List.isEmpty()) {
            for (NWAI4150_10TMsg nwai4150Tmsg : nwai415010List) {
                if (!configQtyMap.containsKey(nwai4150Tmsg.somConfigId.getValue())) {
                    configQtyMap.put(nwai4150Tmsg.somConfigId.getValue(), nwai4150Tmsg.somConfigQty.getValue());
                }
            }
        }
        Map<BigDecimal, BigDecimal> cacheMap = new HashMap<BigDecimal, BigDecimal>();
        // Exists Line Data
        if (configQtyMap != null && !configQtyMap.isEmpty()) {
            Object[] key = configQtyMap.keySet().toArray();
            java.util.Arrays.sort(key);
            // Distinct Config Loop
            for (int ii = 0; ii < key.length; ii++) {
                // Config Qty Loop
                for (int configQtyIndex = 0; configQtyIndex < (configQtyMap.get(key[ii])).intValue(); configQtyIndex++) {
                    // All Config Record Loop
                    int configRecordIndex = 0;
                    NWAI4150_12TMsg lastIf12TMsg = null;
                    boolean storeFlg = false;
                    for (NWAI4150_12TMsg if12TMsg : nwai415012List) {
                        if (if12TMsg.somConfigId.getValue().compareTo((BigDecimal) key[ii]) != 0) {
                            continue;
                        }

                        if (!S21StringUtil.isEquals(if12TMsg.lastUpdTs.getValue(), dsImptOrdBean.nwai415001.lastUpdTs.getValue())) {
                            continue;
                        }

                        if (configQtyIndex == configRecordIndex) {
                            storeConfigData(//
                                    dsImptOrdBean, if12TMsg, nwai415010List, nwai415013List, nwai415014List, nwai415023List, cacheMap);
                            storeFlg = true;
                        }
                        lastIf12TMsg = if12TMsg;
                        configRecordIndex++;
                    } //End of All Config Record Loop
                    // Different between Config Qty <> NWAI4150_12 record count
                    if (!storeFlg) {
                        storeConfigData(//
                                dsImptOrdBean, lastIf12TMsg, nwai415010List, nwai415013List, nwai415014List, nwai415023List, cacheMap);
                    }
                } //End of Config Qty Loop
            }
            // No Exists Line Data
        } else {
            // All Config Record Loop
            for (NWAI4150_12TMsg if12TMsg : nwai415012List) {
                if (!S21StringUtil.isEquals(if12TMsg.lastUpdTs.getValue(), dsImptOrdBean.nwai415001.lastUpdTs.getValue())) {
                    continue;
                }

                storeConfigData(//
                        dsImptOrdBean, if12TMsg, nwai415010List, nwai415013List, nwai415014List, nwai415023List, cacheMap);
            } //End of All Config Record Loop
        }

    }

    public void createImptConfigDtlFromNWAI415023(//
            EopsDsImptOrdBean dsImptOrdBean //
            , List<NWAI4150_23TMsg> nwai415023List //
            , List<NWAI4150_24TMsg> nwai415024List) {

        for (NWAI4150_24TMsg if24TMsg : nwai415024List) {
            //            storeConfigData(//
            //                    dsImptOrdBean, if24TMsg, nwai415023List);
            EopsDsImptOrdConfigBean dsImptOrdConfigBean //
            = new EopsDsImptOrdConfigBean(CONFIG_TYPE.EOPS_RMA, dsImptOrdBean, if24TMsg);
            // *************************************************************
            // Process (29) (EOPS RMA)
            // *************************************************************
            dsImptOrdConfigBean = new EopsDsImptOrdConfigBean(CONFIG_TYPE.EOPS_RMA, dsImptOrdBean, if24TMsg);
            dsImptOrdBean.addEopsDsImptOrdConfigBean(dsImptOrdConfigBean);
            dsImptOrdConfigBean.nwai4150_24List.add(if24TMsg);

            new EopsDsImptOrdIstlInfoBean(ISTL_INFO_TYPE.EOPS_RMA, dsImptOrdConfigBean);
            new EopsDsImptOrdDelyInfoBean(DELY_INFO_TYPE.EOPS_RMA, dsImptOrdConfigBean);
            dsImptOrdConfigBean.addDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.EOPS_RMA);

            // *************************************************************
            // Process (30) (EOPS Return)
            // *************************************************************
            //get all configuration lines from main machine serial#
            EopsDsImptOrdRtrnDtlBean rtrnDtlBean //
            = new EopsDsImptOrdRtrnDtlBean(RTRN_DTL_TYPE.EOPS_RETURN, dsImptOrdConfigBean, if24TMsg);
            new EopsDsImptRtrnPrcCalcBean(RTRN_DTL_TYPE.EOPS_RETURN, rtrnDtlBean);
        }

        //        for (NWAI4150_23TMsg if23TMsg : nwai415023List) {
        //            storeConfigData(//
        //                    dsImptOrdBean, if23TMsg, nwai415024List);
        //        }

    }

    //    private void storeConfigData(//
    //            EopsDsImptOrdBean dsImptOrdBean //
    //            , NWAI4150_24TMsg if24TMsg, List<NWAI4150_23TMsg> nwai415023List) {
    //
    //        EopsDsImptOrdConfigBean dsImptOrdConfigBean //
    //        = new EopsDsImptOrdConfigBean(CONFIG_TYPE.EOPS_RMA, dsImptOrdBean, if24TMsg);
    //
    //        //        dsImptOrdConfigBean = new EopsDsImptOrdConfigBean(CONFIG_TYPE.EOPS_RMA, dsImptOrdBean, if23TMsg);
    //        //        dsImptOrdBean.addEopsDsImptOrdConfigBean(dsImptOrdConfigBean);
    //        //        dsImptOrdBean.lastShipConfigBean = dsImptOrdConfigBean;
    //
    //        NWAI4150_23TMsg if23TMsg //
    //        = getNWAI415023TMsgByConfigKey(if24TMsg.somQuoteId, if24TMsg.somConfigId, nwai415023List);
    //
    //        // *************************************************************
    //        // Process (29) (EOPS RMA)
    //        // *************************************************************
    //        dsImptOrdConfigBean = new EopsDsImptOrdConfigBean(CONFIG_TYPE.EOPS_RMA, dsImptOrdBean, if24TMsg);
    //        dsImptOrdBean.addEopsDsImptOrdConfigBean(dsImptOrdConfigBean);
    //        dsImptOrdConfigBean.nwai4150_24List.add(if24TMsg);
    //
    //        new EopsDsImptOrdIstlInfoBean(ISTL_INFO_TYPE.EOPS_RMA, dsImptOrdConfigBean);
    //        new EopsDsImptOrdDelyInfoBean(DELY_INFO_TYPE.EOPS_RMA, dsImptOrdConfigBean);
    //        dsImptOrdConfigBean.addDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.EOPS_RMA);
    //
    //        // *************************************************************
    //        // Process (30) (EOPS Return)
    //        // *************************************************************
    //        //get all configuration lines from main machine serial#
    //        EopsDsImptOrdRtrnDtlBean rtrnDtlBean //
    //        = new EopsDsImptOrdRtrnDtlBean(RTRN_DTL_TYPE.EOPS_RETURN, dsImptOrdConfigBean, if24TMsg);
    //        new EopsDsImptRtrnPrcCalcBean(RTRN_DTL_TYPE.EOPS_RETURN, rtrnDtlBean);
    //
    //    }

    private void storeConfigData(//
            EopsDsImptOrdBean dsImptOrdBean //
            , NWAI4150_12TMsg if12TMsg //
            , List<NWAI4150_10TMsg> nwai415010List //
            , List<NWAI4150_13TMsg> nwai415013List //
            , List<NWAI4150_14TMsg> nwai415014List //
            , List<NWAI4150_23TMsg> nwai415023List //
            , Map<BigDecimal, BigDecimal> cacheMap) {

        EopsDsImptOrdConfigBean dsImptOrdConfigBean = new EopsDsImptOrdConfigBean(CONFIG_TYPE.REGULAR_SHIP_CONFIG, dsImptOrdBean, if12TMsg);
        List<NWAI4150_10TMsg> if10TMsgList = getNWAI415010TMsgByConfigKey(dsImptOrdConfigBean.configKey, nwai415010List);
        //Main Item(SOM_MDSE_TP_TXT=10)
        BigDecimal mainMachineQty = BigDecimal.ONE;
        BigDecimal somSortOrdNum = BigDecimal.ZERO;
        boolean isExistsMachine = false;
        for (NWAI4150_10TMsg if10TMsg : if10TMsgList) {
            if (COA_PROJ.MACHINE.equals(if10TMsg.somMdseTpTxt.getValue()) //
                    || COA_PROJ.MERCHANDISE_SET.equals(if10TMsg.somMdseTpTxt.getValue())) {
                if (ZYPCommonFunc.hasValue(if10TMsg.somOrdQty)) {
                    mainMachineQty = if10TMsg.somOrdQty.getValue().abs();
                    somSortOrdNum = if10TMsg.eopsSortOrdNum.getValue();
                    isExistsMachine = true;
                    break;
                }
            }
        }

        // Main Item Qty Loop
        for (int machineIndex = 0; machineIndex < mainMachineQty.intValue(); machineIndex++) {
            dsImptOrdConfigBean = new EopsDsImptOrdConfigBean(CONFIG_TYPE.REGULAR_SHIP_CONFIG, dsImptOrdBean, if12TMsg);
            dsImptOrdBean.addEopsDsImptOrdConfigBean(dsImptOrdConfigBean);
            dsImptOrdBean.lastShipConfigBean = dsImptOrdConfigBean;

            if10TMsgList = getNWAI415010TMsgByConfigKey(dsImptOrdConfigBean.configKey, nwai415010List);

            List<NWAI4150_13TMsg> if13TMsgList = getNWAI4150_13TMsgByConfigKey(dsImptOrdConfigBean.configKey, nwai415013List);
            dsImptOrdConfigBean.nwai4150_13List.addAll(if13TMsgList);
            if (!isExistsMachine && if13TMsgList != null && if13TMsgList.size() > 0) { //add Accessory to existing configuration
                //QC#17768
                dsImptOrdConfigBean.addExsistingCondigurationListForAddAcc(); //set configuration list from serial
            } else if (isExistsMachine && if10TMsgList != null && if10TMsgList.size() > 0) {
                //QC#17768
                dsImptOrdConfigBean.addExsistingCondigurationListForPreOwned(if10TMsgList); //set configuration list from serial
            }

            new EopsDsImptOrdIstlInfoBean(ISTL_INFO_TYPE.REGULAR_SHIP_CONFIG, dsImptOrdConfigBean);
            new EopsDsImptOrdDelyInfoBean(DELY_INFO_TYPE.REGULAR_SHIP_CONFIG, dsImptOrdConfigBean);
            dsImptOrdConfigBean.addDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.REGULAR_SHIP_CONFIG);

            // Line Loop
            for (NWAI4150_10TMsg if10TMsg : if10TMsgList) {
                NWAI4150_23TMsg if23TMsg = getNwai4150_23From10(nwai415023List, if10TMsg);
                //Main Item Line
                if (somSortOrdNum.compareTo(if10TMsg.eopsSortOrdNum.getValue()) == 0) {
                    NWAI4150_10TMsg copyif10TMsg = new NWAI4150_10TMsg();
                    EZDMsg.copy(if10TMsg, null, copyif10TMsg, null);
                    ZYPEZDItemValueSetter.setValue(copyif10TMsg.somOrdQty, BigDecimal.ONE);
                    ZYPEZDItemValueSetter.setValue(copyif10TMsg.sellPrcListAmt, copyif10TMsg.sellPrcListAmt);
                    ZYPEZDItemValueSetter.setValue(copyif10TMsg.extSellPrcAmt, copyif10TMsg.sellPrcListAmt);
                    EopsDsImptOrdDtlBean dtlBean //
                    = new EopsDsImptOrdDtlBean(DTL_TYPE.REGULAR_SHIP_LINE, dsImptOrdConfigBean, copyif10TMsg, if23TMsg);
                    dtlBean.setTempBaseCmptFlg(ZYPConstant.FLG_ON_Y);
                    new EopsDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.REGULAR_SHIP_LINE, dtlBean);
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
                            if ((somOrdQty.subtract(new BigDecimal(String.valueOf(machineIndex)))).compareTo(BigDecimal.ZERO) == 0) {
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
                        if ((COA_PROJ.MACHINE.equals(if10TMsg.somMdseTpTxt.getValue()) //Machine
                                || COA_PROJ.MERCHANDISE_SET.equals(if10TMsg.somMdseTpTxt.getValue()) //Set Machine
                        || COA_PROJ.ACCESSORY.equals(if10TMsg.somMdseTpTxt.getValue())) //Accessory
                                && mdse != null //
                                && ZYPConstant.FLG_ON_Y.equals((String) mdse.get("SHPG_SER_TAKE_FLG"))) {
                            for (int i = 0; i < otherQty.intValue(); i++) {
                                // per order quantity
                                NWAI4150_10TMsg copyif10TMsg = new NWAI4150_10TMsg();
                                EZDMsg.copy(if10TMsg, null, copyif10TMsg, null);
                                ZYPEZDItemValueSetter.setValue(copyif10TMsg.somOrdQty, BigDecimal.ONE);
                                ZYPEZDItemValueSetter.setValue(copyif10TMsg.sellPrcListAmt, copyif10TMsg.sellPrcListAmt);
                                ZYPEZDItemValueSetter.setValue(copyif10TMsg.extSellPrcAmt, copyif10TMsg.sellPrcListAmt);
                                EopsDsImptOrdDtlBean dtlBean = new EopsDsImptOrdDtlBean(DTL_TYPE.REGULAR_SHIP_LINE, dsImptOrdConfigBean, copyif10TMsg, if23TMsg);
                                new EopsDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.REGULAR_SHIP_LINE, dtlBean);
                            }
                        } else {
                            NWAI4150_10TMsg copyif10TMsg = new NWAI4150_10TMsg();
                            EZDMsg.copy(if10TMsg, null, copyif10TMsg, null);
                            ZYPEZDItemValueSetter.setValue(copyif10TMsg.somOrdQty, new BigDecimal(String.valueOf(otherQty)));
                            ZYPEZDItemValueSetter.setValue(copyif10TMsg.sellPrcListAmt, copyif10TMsg.sellPrcListAmt);
                            ZYPEZDItemValueSetter.setValue(copyif10TMsg.extSellPrcAmt, copyif10TMsg.sellPrcListAmt.getValue().multiply(new BigDecimal(String.valueOf(otherQty))));
                            EopsDsImptOrdDtlBean dtlBean = new EopsDsImptOrdDtlBean(DTL_TYPE.REGULAR_SHIP_LINE, dsImptOrdConfigBean, copyif10TMsg, if23TMsg);
                            new EopsDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.REGULAR_SHIP_LINE, dtlBean);
                        }
                    }
                }
            } //End of Line Loop
            List<NWAI4150_14TMsg> if14TMsgList = getNWAI4150_14TMsgByConfigKey(if12TMsg.somQuoteId, if12TMsg.somConfigId, nwai415014List);

            if (if14TMsgList.size() > 0) {
                for (NWAI4150_14TMsg if14TMsg : if14TMsgList) {
                    // *************************************************************
                    // Process (7) (OCE-PROMO)
                    // *************************************************************
                    EopsDsImptOrdDtlBean dtlBean = new EopsDsImptOrdDtlBean(DTL_TYPE.OCE_PROMO, dsImptOrdConfigBean, if14TMsg);
                    new EopsDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.OCE_PROMO, dtlBean);
                }
            }

            if (if14TMsgList.size() > 0) {
                for (NWAI4150_14TMsg if14TMsg : if14TMsgList) {
                    // *************************************************************
                    // Process (21) (OCE PROMOTION (RMA))
                    // *************************************************************
                    dsImptOrdConfigBean = new EopsDsImptOrdConfigBean(CONFIG_TYPE.OCE_PROMOTION_RMA, dsImptOrdBean, if12TMsg);
                    dsImptOrdBean.addEopsDsImptOrdConfigBean(dsImptOrdConfigBean);
                    dsImptOrdConfigBean.nwai4150_14List.add(if14TMsg);

                    new EopsDsImptOrdIstlInfoBean(ISTL_INFO_TYPE.OCE_PROMOTION_RMA, dsImptOrdConfigBean);
                    new EopsDsImptOrdDelyInfoBean(DELY_INFO_TYPE.OCE_PROMOTION_RMA, dsImptOrdConfigBean);
                    dsImptOrdConfigBean.addDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.OCE_PROMOTION_RMA);

                    // *************************************************************
                    // Process (7) (OCE-PROMO)
                    // *************************************************************
                    //get all configuration lines from main machine serial#
                    EopsDsImptOrdRtrnDtlBean rtrnDtlBean //
                    = new EopsDsImptOrdRtrnDtlBean(RTRN_DTL_TYPE.OCE_PROMO, dsImptOrdConfigBean, if14TMsg);
                    new EopsDsImptRtrnPrcCalcBean(RTRN_DTL_TYPE.OCE_PROMO, rtrnDtlBean);
                }
            }

        } //End of Main Item Qty Loop

    }

    private List<NWAI4150_03TMsg> getNWAI4150_03TMsgByKey(BigDecimal eopsApvlId, List<NWAI4150_03TMsg> nwai415003msgList) {
        List<NWAI4150_03TMsg> retList = new ArrayList<NWAI4150_03TMsg>();

        for (NWAI4150_03TMsg ifTMsg : nwai415003msgList) {
            if (eopsApvlId.equals(ifTMsg.somApvlId.getValue())) {
                retList.add(ifTMsg);
            }
        }

        return retList;
    }

    private List<NWAI4150_06TMsg> getNWAI4150_06TMsgByKey(//
            BigDecimal somQuoteId, BigDecimal somSoftCostId, List<NWAI4150_06TMsg> nwai4150_06List) {

        List<NWAI4150_06TMsg> retList = new ArrayList<NWAI4150_06TMsg>();

        for (NWAI4150_06TMsg ifTMsg : nwai4150_06List) {
            if (somQuoteId.compareTo(ifTMsg.somQuoteId.getValue()) == 0 //
                    && somSoftCostId.compareTo(ifTMsg.somSoftCostItemId.getValue()) == 0) {
                retList.add(ifTMsg);
            }
        }
        return retList;
    }

    private List<NWAI4150_10TMsg> getNWAI415010TMsgByConfigKey(String configKey, List<NWAI4150_10TMsg> nwai4150_10List) {
        List<NWAI4150_10TMsg> retList = new ArrayList<NWAI4150_10TMsg>();
        String ifKey;

        for (NWAI4150_10TMsg ifTMsg : nwai4150_10List) {

            ifKey = String.format(EopsDsImptOrdConfigBean.configKeyFormat, ifTMsg.somQuoteId.getValue(), ifTMsg.somConfigId.getValue());
            if (S21StringUtil.isEquals(configKey, ifKey)) {
                retList.add(ifTMsg);
            }
        }

        return retList;
    }

    private List<NWAI4150_13TMsg> getNWAI4150_13TMsgByConfigKey(String configKey, List<NWAI4150_13TMsg> nwai4150_13List) {
        List<NWAI4150_13TMsg> retList = new ArrayList<NWAI4150_13TMsg>();
        String ifKey;

        for (NWAI4150_13TMsg ifTMsg : nwai4150_13List) {
            ifKey = String.format(EopsDsImptOrdConfigBean.configKeyFormat, ifTMsg.somQuoteId.getValue(), ifTMsg.somConfigId.getValue());
            if (S21StringUtil.isEquals(configKey, ifKey)) {
                retList.add(ifTMsg);
            }
        }

        return retList;
    }

    private List<NWAI4150_14TMsg> getNWAI4150_14TMsgByConfigKey(EZDTBigDecimalItem somQuoteId, EZDTBigDecimalItem somConfigId, List<NWAI4150_14TMsg> nwai4150_14List) {
        String ifKey = String.format(EopsDsImptOrdConfigBean.configKeyFormat, somQuoteId.getValue(), somConfigId.getValue());
        return getNWAI4150_14TMsgByConfigKey(ifKey, nwai4150_14List);
    }

    private List<NWAI4150_14TMsg> getNWAI4150_14TMsgByConfigKey(String configKey, List<NWAI4150_14TMsg> nwai4150_14List) {
        List<NWAI4150_14TMsg> retList = new ArrayList<NWAI4150_14TMsg>();
        String ifKey;

        for (NWAI4150_14TMsg ifTMsg : nwai4150_14List) {
            ifKey = String.format(EopsDsImptOrdConfigBean.configKeyFormat, ifTMsg.somQuoteId.getValue(), ifTMsg.somConfigId.getValue());
            if (S21StringUtil.isEquals(configKey, ifKey)) {
                retList.add(ifTMsg);
            }
        }

        return retList;
    }

    private List<NWAI4150_24TMsg> getNWAI415024TMsgByConfigKey(EZDTBigDecimalItem somQuoteId, EZDTBigDecimalItem somConfigId, List<NWAI4150_24TMsg> nwai415024List) {
        String ifKey = String.format(EopsDsImptOrdConfigBean.configKeyFormat, somQuoteId.getValue(), somConfigId.getValue());
        return getNWAI415024TMsgByConfigKey(ifKey, nwai415024List);
    }

    private List<NWAI4150_24TMsg> getNWAI415024TMsgByConfigKey(String configKey, List<NWAI4150_24TMsg> nwai415024List) {
        List<NWAI4150_24TMsg> retList = new ArrayList<NWAI4150_24TMsg>();
        String ifKey;

        for (NWAI4150_24TMsg ifTMsg : nwai415024List) {
            ifKey = String.format(EopsDsImptOrdConfigBean.configKeyFormat, ifTMsg.somQuoteId.getValue(), ifTMsg.somConfigId.getValue());
            if (S21StringUtil.isEquals(configKey, ifKey)) {
                retList.add(ifTMsg);
            }
        }

        return retList;
    }

    private NWAI4150_23TMsg getNwai4150_23From10(List<NWAI4150_23TMsg> nwai4150_23List, NWAI4150_10TMsg i10TMsg) {
        for (NWAI4150_23TMsg i23TMsg : nwai4150_23List) {
            if (isSameKey(i23TMsg, i10TMsg)) {
                return i23TMsg;
            }
        }
        return new NWAI4150_23TMsg();
    }

    private NWAI4150_22TMsg getNwai4150_22From18(List<NWAI4150_22TMsg> nwai4150_22List, NWAI4150_18TMsg i18TMsg) {
        for (NWAI4150_22TMsg i22TMsg : nwai4150_22List) {
            if (isSameKey(i22TMsg, i18TMsg)) {
                return i22TMsg;
            }
        }
        return new NWAI4150_22TMsg();
    }

    private NWAI4150_22TMsg getNwai4150_22From19(List<NWAI4150_22TMsg> nwai415022List, NWAI4150_19TMsg i19TMsg) {
        for (NWAI4150_22TMsg i22TMsg : nwai415022List) {
            if (isSameKey(i22TMsg, i19TMsg)) {
                return i22TMsg;
            }
        }
        return new NWAI4150_22TMsg();
    }

    private boolean isSameKey(NWAI4150_22TMsg i22TMsg, NWAI4150_19TMsg i19TMsg) {
        if (!NWXC412001.isEqualBigDecimal(i22TMsg.somQuoteId.getValue(), i19TMsg.somQuoteId.getValue(), false)) {
            return false;
        }
        if (!NWXC412001.isEqualBigDecimal(i22TMsg.somConfigId.getValue(), i19TMsg.somSrvLineId.getValue(), false)) {
            return false;
        }
        return true;
    }

    private boolean isSameKey(NWAI4150_22TMsg i22TMsg, NWAI4150_18TMsg i18TMsg) {
        if (!NWXC412001.isEqualBigDecimal(i22TMsg.somQuoteId.getValue(), i18TMsg.somQuoteId.getValue(), false)) {
            return false;
        }
        if (!NWXC412001.isEqualBigDecimal(i22TMsg.somConfigId.getValue(), i18TMsg.somSrvLineId.getValue(), false)) {
            return false;
        }
        if (SOM_SRV_MTR_TP.COLOR.getValue().equals(i22TMsg.somSrvMtrTpNm.getValue()) //
                && FLG.YES.equals(i18TMsg.isClrBtIndSomTxt.getValue())) {
            return false;
        }
        return true;
    }

    private boolean isSameKey(NWAI4150_23TMsg i23TMsg, NWAI4150_10TMsg i10TMsg) {
        if (!NWXC412001.isEqualBigDecimal(i23TMsg.somQuoteId.getValue(), i10TMsg.somQuoteId.getValue(), false)) {
            return false;
        }
        if (!NWXC412001.isEqualBigDecimal(i23TMsg.somConfigId.getValue(), i10TMsg.somConfigId.getValue(), false)) {
            return false;
        }
        if (!NWXC412001.isEqualBigDecimal(i23TMsg.somSortOrdNum.getValue(), i10TMsg.eopsSortOrdNum.getValue(), false)) {
            return false;
        }

        return true;
    }

    private void addEopsDsImptOrdConfigBean(EopsDsImptOrdConfigBean dsImptOrdConfigBean) {
        this.dsImptOrdConfigBeanList.add(dsImptOrdConfigBean);
    }

    private void createUpgradeReturnRma(//
            EopsDsImptOrdBean dsImptOrdBean, List<NWAI4150_06TMsg> targetNwai415006List, NWAI4150_05TMsg if05TMsg, NWAI4150_06TMsg serIf06TMsg, NWAI4150_06TMsg noSerIf06TMsg) {
        // *************************************************************
        // Process (13) (UPGRADE RETURN (RMA))
        // *************************************************************
        EopsDsImptOrdConfigBean dsImptOrdConfigBean //
        = new EopsDsImptOrdConfigBean(CONFIG_TYPE.UPGRADE_RETURN_RMA, dsImptOrdBean, if05TMsg, serIf06TMsg, noSerIf06TMsg);
        dsImptOrdBean.addEopsDsImptOrdConfigBean(dsImptOrdConfigBean);

        new EopsDsImptOrdIstlInfoBean(ISTL_INFO_TYPE.UPGRADE_RETURN_RMA, dsImptOrdConfigBean);
        new EopsDsImptOrdDelyInfoBean(DELY_INFO_TYPE.UPGRADE_RETURN_RMA, dsImptOrdConfigBean);
        dsImptOrdConfigBean.addDsImptOrdCtacPsnBean(CTAC_PSN_TYPE.UPGRADE_RETURN_RMA);

        // *********************************************************
        // Process (13) (UPGRADE RETURN (RMA))
        // *********************************************************
        //get all configuration lines from main machine serial#
        EopsDsImptOrdRtrnDtlBean rtrnDtlBean = new EopsDsImptOrdRtrnDtlBean(RTRN_DTL_TYPE.UPGRADE_RETURN_RMA, dsImptOrdConfigBean, if05TMsg, serIf06TMsg);
        new EopsDsImptRtrnPrcCalcBean(RTRN_DTL_TYPE.UPGRADE_RETURN_RMA, rtrnDtlBean);
    }

    /**
     * 
     * @param dsImptOrdBean EopsDsImptOrdBean
     */
    public void createImptOrdDtlForHdrRebate(EopsDsImptOrdBean dsImptOrdBean) {
        if (ZYPCommonFunc.hasValue(dsImptOrdBean.nwai415001.rebMercCd)) {
            // *****************************************************************
            // Process (14) (HEADER REBATE)
            // *****************************************************************
            EopsDsImptOrdDtlBean dtlBean = new EopsDsImptOrdDtlBean(DTL_TYPE.HEADER_REBATE, dsImptOrdBean.lastShipConfigBean);
            new EopsDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.HEADER_REBATE, dtlBean);
        }
    }

    /**
     * createShellForSrvNonCopiers
     * @param dsImptOrdBean     EopsDsImptOrdBean
     * @param nwai415016List    List<NWAI4150_16TMsg>
     * @param nwai415018List    List<NWAI4150_18TMsg>
     * @param nwai415019List    List<NWAI4150_19TMsg>
     * @param nwai415022List    List<NWAI4150_22TMsg>
     */
    public void createShellForSrvNonCopiers(//
            EopsDsImptOrdBean dsImptOrdBean //
            , List<NWAI4150_16TMsg> nwai415016List //
            , List<NWAI4150_18TMsg> nwai415018List //
            , List<NWAI4150_19TMsg> nwai415019List //
            , List<NWAI4150_22TMsg> nwai415022List) {

        List<EopsDsImptSvcPrcBean> dsImptSvcPrcBeanList = new ArrayList<EopsDsImptSvcPrcBean>();
        List<String> regBundleList = new ArrayList<String>();

        // per service ID = quote ID
        for (NWAI4150_16TMsg service : nwai415016List) {

            //Make Not Decline Maintenance List
            List<NWAI4150_19TMsg> ndm_nwai415019list = new ArrayList<NWAI4150_19TMsg>();
            for (NWAI4150_19TMsg serviceLine : nwai415019List) {
                if (!S21StringUtil.isEquals(serviceLine.dclnMaintIndSomTxt.getValue(), FLG.YES.name())) {
                    //Make Not Decline Maintenance List
                    ndm_nwai415019list.add(serviceLine);
                }
            }
            //Configuration Loop
            for (EopsDsImptOrdConfigBean config : dsImptOrdBean.dsImptOrdConfigBeanList) {

                if (config.nwai415012 == null || !ZYPCommonFunc.hasValue(config.nwai415012.somConfigId)) {
                    //skip configId is null
                    continue;
                }

                //Service Non Copier Loop
                BigDecimal prevServiceLineId = null;
                for (NWAI4150_19TMsg serviceLine : nwai415019List) {

                    if (service.somSrvId.getValue().compareTo(serviceLine.somSrvId.getValue()) != 0) {
                        //skip different shell
                        continue;
                    }
                    if (config.nwai415012.somConfigId.getValue().compareTo(serviceLine.somConfigId.getValue()) != 0) {
                        //skip different configId
                        continue;
                    }

                    BigDecimal serviceLineId = serviceLine.somSrvLineId.getValue();
                    if (prevServiceLineId != null && serviceLineId.compareTo(prevServiceLineId) == 0) {
                        // skip same service line ID
                        continue;
                    } else {
                        prevServiceLineId = serviceLineId;
                    }
                    //Not Decline Maintenance List
                    if (!S21StringUtil.isEquals(serviceLine.dclnMaintIndSomTxt.getValue(), FLG.YES.name())) {
                        ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_OFF_0);
                    } else {
                        ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_ON_1);
                    }
                } //End of Service Non Copier Loop
            } //End of Configuration Loop

            // *****************************************************************
            // service price per NWAI4150_16.SOM_SRV_ID, model Id
            // *****************************************************************
            // *****************************************************************
            // service detail (service line) per NWAI4150_18.SOM_SRV_LINE_ID
            // *****************************************************************
            // *****************************************************************
            // service configuration reference per NWAI4150_18.SOM_SRV_LINE_ID
            // *****************************************************************
            //Non Decline Maintenance List
            if (ndm_nwai415019list.size() > 0) {
                Map<String, EopsDsImptSvcDtlBean> somDsImptSvcDtlBeanMap = new HashMap<String, EopsDsImptSvcDtlBean>();
                Map<String, BigDecimal> reqBaseAmtRegMap = new HashMap<String, BigDecimal>();
                //Configuration Loop
                for (EopsDsImptOrdConfigBean config : dsImptOrdBean.dsImptOrdConfigBeanList) {

                    BigDecimal prevServiceLineId = null;
                    //Service Non Copier Loop
                    for (NWAI4150_19TMsg serviceLine : ndm_nwai415019list) {

                        if (service.somSrvId.getValue().compareTo(serviceLine.somSrvId.getValue()) != 0) {
                            //skip different shell
                            continue;
                        }

                        BigDecimal serviceLineId = serviceLine.somSrvLineId.getValue();
                        if (config.nwai415012 == null || !ZYPCommonFunc.hasValue(config.nwai415012.somConfigId) || config.nwai415012.somConfigId.getValue().compareTo(serviceLineId) != 0) {
                            continue;
                        }

                        if (S21StringUtil.isEquals(serviceLine.dclnMaintIndSomTxt.getValue(), FLG.YES.name())) {
                            ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_ON_1);
                        } else {
                            ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_OFF_0);
                        }

                        if (prevServiceLineId != null && serviceLineId.compareTo(serviceLineId) == 0) {
                            // skip same service line ID
                            continue;
                        } else {

                            prevServiceLineId = serviceLineId;
                        }
                        EopsDsImptSvcDtlBean somDsImptSvcDtlBean = null;
                        String regUniqueKey = regShellContractUniqueString(service, serviceLine);
                        EopsDsImptSvcDtlBean preSomDsImptSvcDtlBean = somDsImptSvcDtlBeanMap.get(regUniqueKey);
                        BigDecimal reqBaseAmtReg = reqBaseAmtRegMap.get(regUniqueKey);
                        if (regBundleList.contains(regUniqueKey)) {
                            somDsImptSvcDtlBean = preSomDsImptSvcDtlBean;
                        } else {
                            somDsImptSvcDtlBean = new EopsDsImptSvcDtlBean(SHELL_TYPE.NON_COPIER, dsImptOrdBean, service, serviceLine, CONTRACT_TYPE.REGULAR);
                            reqBaseAmtReg = BigDecimal.ZERO;
                        }
                        regBundleList.add(regUniqueKey);
                        // create service detail
                        BigDecimal perMthReqBaseAmt = BigDecimal.ZERO;
                        BigDecimal totPerMthReqBaseAmt = BigDecimal.ZERO;
                        if (ZYPCommonFunc.hasValue(serviceLine.reqBaseAmt) //
                                && ZYPCommonFunc.hasValue(serviceLine.somDurnId) //
                                && BigDecimal.ZERO.compareTo(serviceLine.somDurnId.getValue()) != 0) {
                            perMthReqBaseAmt //
                            = serviceLine.reqBaseAmt.getValue().divide(serviceLine.somDurnId.getValue().divide(new BigDecimal("12")), declPntDigt, BigDecimal.ROUND_HALF_UP); // QC#19451
                            totPerMthReqBaseAmt = serviceLine.reqBaseAmt.getValue();
                        }
                        reqBaseAmtReg = reqBaseAmtReg.add(totPerMthReqBaseAmt);
                        somDsImptSvcDtlBean.setReqBaseAmt(reqBaseAmtReg);

                        NWAI4150_22TMsg nmai415022TMsg = getNwai4150_22From19(nwai415022List, serviceLine);
                        // create service price
                        EopsDsImptSvcPrcBean somDsImptSvcPrcBean //
                        = new EopsDsImptSvcPrcBean(SHELL_TYPE.NON_COPIER, dsImptOrdBean, serviceLine, service, nmai415022TMsg);
                        somDsImptSvcPrcBean.setReqBaseAmt(perMthReqBaseAmt); //QC#17122
                        somDsImptSvcPrcBean.dsImptSvcDtlBean = somDsImptSvcDtlBean;
                        somDsImptSvcDtlBean.dsImptSvcPrcBeanList.add(somDsImptSvcPrcBean);
                        dsImptSvcPrcBeanList.add(somDsImptSvcPrcBean);

                        // create service configuration reference
                        new EopsDsImptSvcConfigRefBean(SHELL_TYPE.NON_COPIER, somDsImptSvcDtlBean, service, serviceLine, config, somDsImptSvcPrcBean);
                        somDsImptSvcDtlBeanMap.put(regUniqueKey, somDsImptSvcDtlBean);
                        reqBaseAmtRegMap.put(regUniqueKey, reqBaseAmtReg);

                        // *****************************************************************
                        // additional base NWAI4150_17.SOM_SRV_ID
                        // *****************************************************************
                        // N/A

                        // *****************************************************************
                        // additional base NWAI4120_20.SOM_SRV_ID
                        // *****************************************************************
                        // N/A

                        // *****************************************************************
                        // additional base NWAI4120_21.SOM_CONFIG_LINE_ID
                        // *****************************************************************
                        // N/A

                        preSomDsImptSvcDtlBean = somDsImptSvcDtlBean; //QC#20393
                    }
                } //End of Configuration Loop
            } //End of Non Decline Maintenance List

        }
    }

    private String regShellContractUniqueString(NWAI4150_16TMsg nwai415016, NWAI4150_19TMsg nwai415019) {
        StringBuffer sb = new StringBuffer();

        sb.append(nwai415019.dclnMaintIndSomTxt.getValue()); //dsImptSvcMdseCd
        sb.append(nwai415019.somDurnId.getValue()); //toPerNum
        sb.append(nwai415019.svcContrIndSomTxt.getValue()); //billWithEquipFlg
        sb.append(nwai415016.fleetBillByTxt.getValue()); //billByTpCd

        return sb.toString();
    }

    /**
     * createShellForSrvCopiers
     * @param dsImptOrdBean     EopsDsImptOrdBean
     * @param nwai415016List    List<NWAI4150_16TMsg>
     * @param nwai415018List    List<NWAI4150_18TMsg>
     * @param nwai415022List    List<NWAI4150_22TMsg>
     */
    public void createShellForSrvCopiers(//
            EopsDsImptOrdBean dsImptOrdBean //
            , List<NWAI4150_16TMsg> nwai415016List //
            , List<NWAI4150_18TMsg> nwai415018List //
            , List<NWAI4150_22TMsg> nwai415022List) {
        //Fleet Service Detail
        EopsDsImptSvcDtlBean eopsDsImptSvcDtlBeanFlt = null;
        //Fleet Service Price
        EopsDsImptSvcPrcBean eopsDsImptSvcPrcBeanFlt = null;
        //Fleet Billing Meter List
        List<String> fltMtrList = new ArrayList<String>();

        //Aggregate Service Detail
        EopsDsImptSvcDtlBean somDsImptSvcDtlBeanAgg = null;
        BigDecimal reqBaseTotAmtAgg = BigDecimal.ZERO;
        List<String> regBundleList = new ArrayList<String>(); //QC#20393

        //Service Header Loop
        for (NWAI4150_16TMsg service : nwai415016List) {

            //Make Not Decline Maintenance List
            List<NWAI4150_18TMsg> ndm_nwai415018list = new ArrayList<NWAI4150_18TMsg>();
            for (NWAI4150_18TMsg serviceLine : nwai415018List) {
                if (!S21StringUtil.isEquals(serviceLine.dclnMaintIndSomTxt.getValue(), FLG.YES.name())) {
                    //Make Not Decline Maintenance List
                    ndm_nwai415018list.add(serviceLine);
                }
            }
            //Configuration Loop
            for (EopsDsImptOrdConfigBean config : dsImptOrdBean.dsImptOrdConfigBeanList) {

                if (config.nwai415012 == null || !ZYPCommonFunc.hasValue(config.nwai415012.somConfigId)) {
                    //skip configId is null
                    continue;
                }

                BigDecimal prevServiceLineId = null;
                //Service Copier Loop
                for (NWAI4150_18TMsg serviceLine : nwai415018List) {

                    if (service.somSrvId.getValue().compareTo(serviceLine.somSrvId.getValue()) != 0) {
                        //skip different shell
                        continue;
                    }

                    if (config.nwai415012.somConfigId.getValue().compareTo(serviceLine.somSrvLineId.getValue()) != 0) {
                        //skip different configId
                        continue;
                    }

                    BigDecimal serviceLineId = serviceLine.somSrvLineId.getValue();
                    if (prevServiceLineId != null && serviceLineId.compareTo(prevServiceLineId) == 0) {
                        // skip same service line ID
                        continue;
                    } else {
                        prevServiceLineId = serviceLineId;
                    }

                    //Decline Maintenance Flag
                    if (!S21StringUtil.isEquals(serviceLine.dclnMaintIndSomTxt.getValue(), FLG.YES.name())) {
                        ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_OFF_0);
                    } else {
                        ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_ON_1);
                    }

                } //End of Service Copier Loop
            } //End of Configuration Loop

            //QC#18210
            if (ndm_nwai415018list.size() > 0) {
                Map<String, EopsDsImptSvcDtlBean> eopsDsImptSvcDtlBeanRegMap = new HashMap<String, EopsDsImptSvcDtlBean>();
                Map<String, EopsDsImptSvcPrcBean> eopsDsImptSvcPrcBeanRegMap = new HashMap<String, EopsDsImptSvcPrcBean>();
                Map<String, BigDecimal> reqBaseAmtRegMap = new HashMap<String, BigDecimal>();

                //Configuration Loop
                for (EopsDsImptOrdConfigBean config : dsImptOrdBean.dsImptOrdConfigBeanList) {
                    if (config.nwai415012 == null || !ZYPCommonFunc.hasValue(config.nwai415012.somConfigId)) {
                        //skip configId is null
                        continue;
                    }
                    //Service Copier Loop
                    BigDecimal prevServiceLineIdFlt = null;
                    BigDecimal prevServiceLineIdAgg = null;
                    BigDecimal prevServiceLineIdReg = null;
                    EopsDsImptSvcPrcBean preSomDsImptSvcPrcBeanAgg = null;
                    BigDecimal reqBaseAmtAgg = BigDecimal.ZERO;

                    //Loop Copiers
                    for (NWAI4150_18TMsg nmai415018TMsg : ndm_nwai415018list) {
                        if (service.somSrvId.getValue().compareTo(nmai415018TMsg.somSrvId.getValue()) != 0) {
                            //skip different shell
                            continue;
                        }

                        if (config.nwai415012.somConfigId.getValue().compareTo(nmai415018TMsg.somSrvLineId.getValue()) != 0) {
                            //skip different configId
                            continue;
                        }
                        NWAI4150_22TMsg nmai415022TMsg = getNwai4150_22From18(nwai415022List, nmai415018TMsg);
                        //Fleet
                        if ((EOPS_CONTR_IND.N.toString().equals(service.contrIndSomTxt.getValue()) //
                                || EOPS_CONTR_IND.F.toString().equals(service.contrIndSomTxt.getValue())) //
                                && S21StringUtil.isEquals(nmai415018TMsg.isFleetIndSomTxt.getValue(), FLG.YES.name())) {
                            if (eopsDsImptSvcDtlBeanFlt == null) {
                                eopsDsImptSvcDtlBeanFlt //
                                = new EopsDsImptSvcDtlBean(SHELL_TYPE.COPIER, dsImptOrdBean, service, nmai415018TMsg, nmai415022TMsg, CONTRACT_TYPE.FLEET);
                            }
                            if (eopsDsImptSvcPrcBeanFlt == null) {
                                eopsDsImptSvcPrcBeanFlt = new EopsDsImptSvcPrcBean(SHELL_TYPE.COPIER, dsImptOrdBean, nmai415018TMsg, service, nmai415022TMsg);
                                eopsDsImptSvcPrcBeanFlt.dsImptSvcDtlBean = eopsDsImptSvcDtlBeanFlt;
                                eopsDsImptSvcDtlBeanFlt.dsImptSvcPrcBeanList.add(eopsDsImptSvcPrcBeanFlt);
                            }

                            // *****************************************************************
                            // service price usage NWAI4150_18.SOM_SRV_Line ID, meter package
                            // *****************************************************************
                            BigDecimal mdlId = null;
                            if (!this.getCacheModelMap().containsKey(nmai415018TMsg.somMdlDescTxt.getValue())) {
                                mdlId = NWXC412001.getMdlIdByName(dsImptOrdBean.glblCmpyCd.getValue(), nmai415018TMsg.somMdlDescTxt.getValue());
                                this.getCacheModelMap().put(nmai415018TMsg.somMdlDescTxt.getValue(), mdlId);
                            } else {
                                mdlId = this.getCacheModelMap().get(nmai415018TMsg.somMdlDescTxt.getValue());
                            }
                            BigDecimal prcMtrPkgPk = null;
                            if (!this.getCachePrcMtrPkgPkMap().containsKey(nmai415018TMsg.eopsPkgNm.getValue())) {
                                prcMtrPkgPk = NWXC412001.getPrcMtrPkgPkFromName(dsImptOrdBean.glblCmpyCd.getValue(), nmai415018TMsg.eopsPkgNm.getValue(), dsImptOrdBean.slsDt);
                                this.getCachePrcMtrPkgPkMap().put(nmai415018TMsg.eopsPkgNm.getValue(), prcMtrPkgPk);
                            } else {
                                prcMtrPkgPk = this.getCachePrcMtrPkgPkMap().get(nmai415018TMsg.eopsPkgNm.getValue());
                            }
                            //Meter Label Name = SOM Service Counter Name
                            List<Map<String, Object>> prcMtrPkgMtrStruList = NWXC412001.getPrcMtrPkgMtrStru(dsImptOrdBean.glblCmpyCd.getValue(), prcMtrPkgPk, mdlId, nmai415018TMsg.somSrvCntrNm.getValue(), dsImptOrdBean.slsDt);
                            //Price Meter Package Structure List
                            if (prcMtrPkgMtrStruList != null && !prcMtrPkgMtrStruList.isEmpty()) {
                                //Meter Loop
                                for (Map<String, Object> prcMtrPkgMtrStru : prcMtrPkgMtrStruList) {
                                    if (!fltMtrList.contains((String) prcMtrPkgMtrStru.get("BLLG_MTR_LB_CD"))) {
                                        new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, eopsDsImptSvcPrcBeanFlt, nmai415018TMsg, prcMtrPkgMtrStru, service, nmai415022TMsg);
                                        new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, eopsDsImptSvcPrcBeanFlt, nmai415018TMsg, prcMtrPkgMtrStru, service, nmai415022TMsg);
                                        fltMtrList.add((String) prcMtrPkgMtrStru.get("BLLG_MTR_LB_CD"));
                                    }
                                } //End of Meter Loop
                            } //End of Price Meter Package Structure List
                            else {
                                if (ZYPCommonFunc.hasValue(nmai415018TMsg.somSrvCntrNm) && !fltMtrList.contains(nmai415018TMsg.somSrvCntrNm.getValue())) {
                                    new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, eopsDsImptSvcPrcBeanFlt, nmai415018TMsg, null, service, nmai415022TMsg);
                                    new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, eopsDsImptSvcPrcBeanFlt, nmai415018TMsg, null, service, nmai415022TMsg);
                                    fltMtrList.add(nmai415018TMsg.somSrvCntrNm.getValue());
                                }
                            }

                            if (prevServiceLineIdFlt != null && nmai415018TMsg.somSrvLineId.getValue().compareTo(prevServiceLineIdFlt) == 0) {
                                // skip same service line ID
                                continue;
                            } else {
                                prevServiceLineIdFlt = nmai415018TMsg.somSrvLineId.getValue();
                            }
                            // create service configuration reference
                            new EopsDsImptSvcConfigRefBean(SHELL_TYPE.COPIER, eopsDsImptSvcDtlBeanFlt, service, nmai415018TMsg, config, eopsDsImptSvcPrcBeanFlt);

                            // *****************************************************************
                            // additional base NWAI4150_17.SOM_SRV_ID
                            // *****************************************************************
                            // N/A
                            // *****************************************************************
                            // additional charge NWAI4150_20.SOM_SRV_ID
                            // *****************************************************************
                            // N/A
                            // *****************************************************************
                            // rental NWAI4150_21.SOM_SRV_ID
                            // *****************************************************************
                            // N/A

                            //Aggregate
                        } else {
                            BigDecimal reqBaseAmt = new BigDecimal(nmai415018TMsg.reqBaseTxt.getValue());
                            if (EOPS_CONTR_IND.A.toString().equals(service.contrIndSomTxt.getValue()) //
                                    && EOPS_CONTR_IND.A.toString().equals(nmai415018TMsg.contrOptTxt.getValue())) {
                                if (somDsImptSvcDtlBeanAgg == null) {
                                    somDsImptSvcDtlBeanAgg //
                                    = new EopsDsImptSvcDtlBean(SHELL_TYPE.COPIER, dsImptOrdBean, service, nmai415018TMsg, nmai415022TMsg, CONTRACT_TYPE.AGGREGATE);
                                }
                                // *****************************************************************
                                // service price usage NWAI4120_18.SOM_SRV_Line ID, meter package
                                // *****************************************************************
                                BigDecimal mdlId = null;
                                if (!this.getCacheModelMap().containsKey(nmai415018TMsg.somMdlDescTxt.getValue())) {
                                    mdlId = NWXC412001.getMdlIdByName(dsImptOrdBean.glblCmpyCd.getValue(), nmai415018TMsg.somMdlDescTxt.getValue());
                                    this.getCacheModelMap().put(nmai415018TMsg.somMdlDescTxt.getValue(), mdlId);
                                } else {
                                    mdlId = this.getCacheModelMap().get(nmai415018TMsg.somMdlDescTxt.getValue());
                                }
                                BigDecimal prcMtrPkgPk = null;
                                if (!this.getCachePrcMtrPkgPkMap().containsKey(nmai415018TMsg.eopsPkgNm.getValue())) {
                                    prcMtrPkgPk = NWXC412001.getPrcMtrPkgPkFromName(dsImptOrdBean.glblCmpyCd.getValue(), nmai415018TMsg.eopsPkgNm.getValue(), dsImptOrdBean.slsDt);
                                    this.getCachePrcMtrPkgPkMap().put(nmai415018TMsg.eopsPkgNm.getValue(), prcMtrPkgPk);
                                } else {
                                    prcMtrPkgPk = this.getCachePrcMtrPkgPkMap().get(nmai415018TMsg.eopsPkgNm.getValue());
                                }
                                //Billing Meter Name = SOM Service Counter Name
                                List<Map<String, Object>> prcMtrPkgMtrStruList = NWXC412001.getPrcMtrPkgMtrStru(dsImptOrdBean.glblCmpyCd.getValue(), prcMtrPkgPk, mdlId, nmai415018TMsg.somSrvCntrNm.getValue(), dsImptOrdBean.slsDt);
                                BigDecimal serviceLineId = nmai415018TMsg.somSrvLineId.getValue();
                                if (prevServiceLineIdAgg != null && serviceLineId.compareTo(prevServiceLineIdAgg) == 0) {

                                    if (ZYPCommonFunc.hasValue(nmai415018TMsg.reqBaseTxt)) {
                                        reqBaseAmtAgg = reqBaseAmtAgg.add(reqBaseAmt);
                                        reqBaseTotAmtAgg = reqBaseTotAmtAgg.add(reqBaseAmt);
                                    }
                                    somDsImptSvcDtlBeanAgg.setReqBaseAmt(reqBaseTotAmtAgg);
                                    preSomDsImptSvcPrcBeanAgg.setReqBaseAmt(reqBaseAmtAgg);
                                    //Price Meter Package List
                                    if (prcMtrPkgMtrStruList != null && !prcMtrPkgMtrStruList.isEmpty()) {
                                        for (Map<String, Object> prcMtrPkgMtrStru : prcMtrPkgMtrStruList) {
                                            new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, preSomDsImptSvcPrcBeanAgg, nmai415018TMsg, prcMtrPkgMtrStru, service, nmai415022TMsg);
                                            new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, preSomDsImptSvcPrcBeanAgg, nmai415018TMsg, prcMtrPkgMtrStru, service, nmai415022TMsg);
                                        }
                                    } //End of Price Meter Package List
                                    else {
                                        if (ZYPCommonFunc.hasValue(nmai415018TMsg.somSrvCntrNm)) {
                                            new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, preSomDsImptSvcPrcBeanAgg, nmai415018TMsg, null, service, nmai415022TMsg);
                                            new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, preSomDsImptSvcPrcBeanAgg, nmai415018TMsg, null, service, nmai415022TMsg);
                                        }
                                    }

                                    // skip same service line ID
                                    continue;
                                } else {
                                    reqBaseAmtAgg = BigDecimal.ZERO;
                                    if (ZYPCommonFunc.hasValue(nmai415018TMsg.reqBaseTxt)) {
                                        reqBaseAmtAgg = reqBaseAmtAgg.add(reqBaseAmt);
                                        reqBaseTotAmtAgg = reqBaseTotAmtAgg.add(reqBaseAmt);
                                    }
                                    // create service detail
                                    somDsImptSvcDtlBeanAgg.setReqBaseAmt(reqBaseTotAmtAgg);

                                    // create service price
                                    EopsDsImptSvcPrcBean eopsDsImptSvcPrcBeanAgg = new EopsDsImptSvcPrcBean(SHELL_TYPE.COPIER, dsImptOrdBean, nmai415018TMsg, service, nmai415022TMsg);
                                    eopsDsImptSvcPrcBeanAgg.setReqBaseAmt(reqBaseAmtAgg);
                                    eopsDsImptSvcPrcBeanAgg.dsImptSvcDtlBean = somDsImptSvcDtlBeanAgg;
                                    somDsImptSvcDtlBeanAgg.dsImptSvcPrcBeanList.add(eopsDsImptSvcPrcBeanAgg);
                                    //Price Meter Package List
                                    if (prcMtrPkgMtrStruList != null && !prcMtrPkgMtrStruList.isEmpty()) {
                                        for (Map<String, Object> prcMtrPkgMtrStru : prcMtrPkgMtrStruList) {
                                            new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, eopsDsImptSvcPrcBeanAgg, nmai415018TMsg, prcMtrPkgMtrStru, service, nmai415022TMsg);
                                            new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, eopsDsImptSvcPrcBeanAgg, nmai415018TMsg, prcMtrPkgMtrStru, service, nmai415022TMsg);
                                        }
                                    } //End of Price Meter Package List
                                    else {
                                        if (ZYPCommonFunc.hasValue(nmai415018TMsg.somSrvCntrNm)) {
                                            new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, eopsDsImptSvcPrcBeanAgg, nmai415018TMsg, null, service, nmai415022TMsg);
                                            new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, eopsDsImptSvcPrcBeanAgg, nmai415018TMsg, null, service, nmai415022TMsg);
                                        }
                                    }

                                    // create service configuration reference
                                    new EopsDsImptSvcConfigRefBean(SHELL_TYPE.COPIER, somDsImptSvcDtlBeanAgg, service, nmai415018TMsg, config, eopsDsImptSvcPrcBeanAgg);

                                    // *****************************************************************
                                    // additional base NWAI4120_17.SOM_SRV_ID
                                    // *****************************************************************

                                    // *****************************************************************
                                    // additional charge NWAI4120_20.SOM_SRV_ID
                                    // *****************************************************************

                                    // *****************************************************************
                                    // rental NWAI4120_21.SOM_SRV_ID
                                    // *****************************************************************

                                    prevServiceLineIdAgg = serviceLineId;
                                    preSomDsImptSvcPrcBeanAgg = eopsDsImptSvcPrcBeanAgg;
                                }

                                //Regular
                            } else {

                                // *****************************************************************
                                // service price usage NWAI4120_18.SOM_SRV_Line ID, meter package
                                // *****************************************************************
                                BigDecimal mdlId = null;
                                if (!this.getCacheModelMap().containsKey(nmai415018TMsg.somMdlDescTxt.getValue())) {
                                    mdlId = NWXC412001.getMdlIdByName(dsImptOrdBean.glblCmpyCd.getValue(), nmai415018TMsg.somMdlDescTxt.getValue());
                                    this.getCacheModelMap().put(nmai415018TMsg.somMdlDescTxt.getValue(), mdlId);
                                } else {
                                    mdlId = this.getCacheModelMap().get(nmai415018TMsg.somMdlDescTxt.getValue());
                                }
                                BigDecimal prcMtrPkgPk = null;
                                if (!this.getCachePrcMtrPkgPkMap().containsKey(nmai415018TMsg.eopsPkgNm.getValue())) {
                                    prcMtrPkgPk = NWXC412001.getPrcMtrPkgPkFromName(dsImptOrdBean.glblCmpyCd.getValue(), nmai415018TMsg.eopsPkgNm.getValue(), dsImptOrdBean.slsDt);
                                    this.getCachePrcMtrPkgPkMap().put(nmai415018TMsg.eopsPkgNm.getValue(), prcMtrPkgPk);
                                } else {
                                    prcMtrPkgPk = this.getCachePrcMtrPkgPkMap().get(nmai415018TMsg.eopsPkgNm.getValue());
                                }
                                //Billing Meter Name = SOM Service Counter Name
                                List<Map<String, Object>> prcMtrPkgMtrStruList = NWXC412001.getPrcMtrPkgMtrStru(dsImptOrdBean.glblCmpyCd.getValue(), prcMtrPkgPk, mdlId, nmai415018TMsg.somSrvCntrNm.getValue(), dsImptOrdBean.slsDt);

                                //QC#20393 start
                                String regUniqueKey = regShellContractUniqueString(service, nmai415018TMsg);
                                EopsDsImptSvcDtlBean preSomDsImptSvcDtlBeanReg = eopsDsImptSvcDtlBeanRegMap.get(regUniqueKey);
                                EopsDsImptSvcPrcBean preSomDsImptSvcPrcBeanReg = eopsDsImptSvcPrcBeanRegMap.get(regUniqueKey);
                                BigDecimal reqBaseAmtReg = reqBaseAmtRegMap.get(regUniqueKey);
                                if (!ZYPCommonFunc.hasValue(reqBaseAmtReg)) {
                                    reqBaseAmtReg = BigDecimal.ZERO;
                                }
                                //QC#20393 end
                                BigDecimal serviceLineId = nmai415018TMsg.somSrvLineId.getValue();
                                if (prevServiceLineIdReg != null && serviceLineId.compareTo(prevServiceLineIdReg) == 0) {

                                    if (ZYPCommonFunc.hasValue(nmai415018TMsg.reqBaseTxt)) {
                                        reqBaseAmtReg = reqBaseAmtReg.add(reqBaseAmt);
                                    }
                                    preSomDsImptSvcDtlBeanReg.setReqBaseAmt(reqBaseAmtReg);
                                    //QC#20393 start
                                    reqBaseAmtRegMap.put(regUniqueKey, reqBaseAmtReg);
                                    BigDecimal prcBaseAmt = BigDecimal.ZERO;
                                    prcBaseAmt = preSomDsImptSvcPrcBeanReg.getReqBaseAmt();
                                    if (prcBaseAmt == null) {
                                        prcBaseAmt = BigDecimal.ZERO;
                                    }
                                    preSomDsImptSvcPrcBeanReg.setReqBaseAmt(prcBaseAmt.add(reqBaseAmt));
                                    //QC#20393 end
                                    //Price Meter Package List
                                    if (prcMtrPkgMtrStruList != null && !prcMtrPkgMtrStruList.isEmpty()) {
                                        for (Map<String, Object> prcMtrPkgMtrStru : prcMtrPkgMtrStruList) {
                                            new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, preSomDsImptSvcPrcBeanReg, nmai415018TMsg, prcMtrPkgMtrStru, service, nmai415022TMsg);
                                            new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, preSomDsImptSvcPrcBeanReg, nmai415018TMsg, prcMtrPkgMtrStru, service, nmai415022TMsg);
                                        }
                                    } //End of Price Meter Package List
                                    else {
                                        if (ZYPCommonFunc.hasValue(nmai415018TMsg.somSrvCntrNm)) {
                                            new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, preSomDsImptSvcPrcBeanReg, nmai415018TMsg, null, service, nmai415022TMsg);
                                            new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, preSomDsImptSvcPrcBeanReg, nmai415018TMsg, null, service, nmai415022TMsg);
                                        }
                                    }

                                    // skip same service line ID
                                    continue;
                                } else {
                                    //QC#20393 start
                                    // create service detail
                                    EopsDsImptSvcDtlBean somDsImptSvcDtlBeanReg = null;
                                    if (regBundleList.contains(regUniqueKey)) {
                                        somDsImptSvcDtlBeanReg = preSomDsImptSvcDtlBeanReg;
                                    } else {
                                        somDsImptSvcDtlBeanReg //
                                        = new EopsDsImptSvcDtlBean(SHELL_TYPE.COPIER, dsImptOrdBean, service, nmai415018TMsg, nmai415022TMsg, CONTRACT_TYPE.REGULAR);
                                        reqBaseAmtReg = BigDecimal.ZERO;
                                    }
                                    regBundleList.add(regUniqueKey);
                                    if (ZYPCommonFunc.hasValue(nmai415018TMsg.reqBaseTxt)) {
                                        reqBaseAmtReg = reqBaseAmtReg.add(reqBaseAmt);
                                    }
                                    somDsImptSvcDtlBeanReg.setReqBaseAmt(reqBaseAmtReg);

                                    // create service price
                                    EopsDsImptSvcPrcBean eopsDsImptSvcPrcBeanReg = new EopsDsImptSvcPrcBean(SHELL_TYPE.COPIER, dsImptOrdBean, nmai415018TMsg, service, nmai415022TMsg);
                                    eopsDsImptSvcPrcBeanReg.setReqBaseAmt(reqBaseAmt);
                                    //QC#20393 end
                                    eopsDsImptSvcPrcBeanReg.dsImptSvcDtlBean = somDsImptSvcDtlBeanReg;
                                    somDsImptSvcDtlBeanReg.dsImptSvcPrcBeanList.add(eopsDsImptSvcPrcBeanReg);
                                    //Price Meter Package List
                                    if (prcMtrPkgMtrStruList != null && !prcMtrPkgMtrStruList.isEmpty()) {
                                        for (Map<String, Object> prcMtrPkgMtrStru : prcMtrPkgMtrStruList) {
                                            new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, eopsDsImptSvcPrcBeanReg, nmai415018TMsg, prcMtrPkgMtrStru, service, nmai415022TMsg);
                                            new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, eopsDsImptSvcPrcBeanReg, nmai415018TMsg, prcMtrPkgMtrStru, service, nmai415022TMsg);
                                        }
                                    } //End of Price Meter Package List
                                    else {
                                        if (ZYPCommonFunc.hasValue(nmai415018TMsg.somSrvCntrNm)) {
                                            new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.BILLABLE, eopsDsImptSvcPrcBeanReg, nmai415018TMsg, null, service, nmai415022TMsg);
                                            new EopsDsImptSvcUsgPrcBean(SVC_USG_PRC_TYPE.PHYSICAL, eopsDsImptSvcPrcBeanReg, nmai415018TMsg, null, service, nmai415022TMsg);
                                        }
                                    }

                                    // create service configuration reference
                                    new EopsDsImptSvcConfigRefBean(SHELL_TYPE.COPIER, somDsImptSvcDtlBeanReg, service, nmai415018TMsg, config, eopsDsImptSvcPrcBeanReg);
                                    //QC#20393 start
                                    eopsDsImptSvcDtlBeanRegMap.put(regUniqueKey, somDsImptSvcDtlBeanReg);
                                    eopsDsImptSvcPrcBeanRegMap.put(regUniqueKey, eopsDsImptSvcPrcBeanReg);
                                    reqBaseAmtRegMap.put(regUniqueKey, reqBaseAmtReg);
                                    //QC#20393 end

                                    // *****************************************************************
                                    // additional base NWAI4120_17.SOM_SRV_ID
                                    // *****************************************************************

                                    // *****************************************************************
                                    // additional charge NWAI4120_20.SOM_SRV_ID
                                    // *****************************************************************

                                    // *****************************************************************
                                    // rental NWAI4120_21.SOM_SRV_ID
                                    // *****************************************************************

                                    prevServiceLineIdReg = serviceLineId;
                                    preSomDsImptSvcPrcBeanReg = eopsDsImptSvcPrcBeanReg;
                                    preSomDsImptSvcDtlBeanReg = somDsImptSvcDtlBeanReg;
                                }
                            }
                        } //End of If Fleet/Aggregate/Regular
                    } //End of Loop Copiers
                }// End of Configuration Loop
            }// End of Configuration Loop
        } //End of Service Header Loop
    }

    private String regShellContractUniqueString(NWAI4150_16TMsg nwai415016, NWAI4150_18TMsg nwai415018) {
        StringBuffer sb = new StringBuffer();

        sb.append(nwai415018.dclnMaintIndSomTxt.getValue()); //dsImptSvcMdseCd
        sb.append(nwai415018.eopsContrTpDescTxt.getValue()); //prcSvcContrTpCd
        sb.append(nwai415018.eopsPlanDescTxt.getValue()); //prcSvcPlnTpCd
        sb.append(nwai415018.somBllgCycleDescTxt.getValue()); //baseBllgCycleCd
        sb.append(nwai415018.usgCycleDescTxt.getValue()); //usgBllgCycleCd
        sb.append(nwai415018.somSrvTermId.getValue()); //toPerNum
        sb.append(nwai415018.svcContrIndSomTxt.getValue()); //billWithEquipFlg
        sb.append(nwai415016.fleetBillByTxt.getValue()); //billByTpCd

        return sb.toString();
    }

    /**
     * createImptOrdDtlForSpiffItems
     * @param dsImptOrdBean     EopsDsImptOrdBean
     * @param nwai415008List   List<NWAI4150_08TMsg>
     */
    public void createImptOrdDtlForSpiffItems(EopsDsImptOrdBean dsImptOrdBean, List<NWAI4150_08TMsg> nwai415008List) {
        for (NWAI4150_08TMsg ifTMsg : nwai415008List) {
            // *****************************************************************
            // Process (15) (SPIFF ITEM)
            // *****************************************************************
            EopsDsImptOrdDtlBean dtlBean = new EopsDsImptOrdDtlBean(DTL_TYPE.SPIFF_ITEM, dsImptOrdBean.lastShipConfigBean, ifTMsg);
            new EopsDsImptPrcCalcBaseBean(PRC_CALC_BASE_TYPE.SPIFF_ITEM, dtlBean);
        }
    }

    /**
     * createImptOrdSlsCrForConfig
     * @param dsImptOrdBean EopsDsImptOrdBean
     */
    public void createImptOrdSlsCrForConfig(EopsDsImptOrdBean dsImptOrdBean) {
        for (EopsDsImptOrdConfigBean configBean : dsImptOrdBean.dsImptOrdConfigBeanList) {
            // *****************************************************************
            // Process (16) (Ship Config)
            // Process (16) (RMA Config)
            // *****************************************************************
            if (configBean.isShipConfig()) {
                new EopsDsImptOrdSlsCrBean(SLS_CR_TYPE.SHIP_CONFIG, configBean);
            } else {
                new EopsDsImptOrdSlsCrBean(SLS_CR_TYPE.RMA_CONFIG, configBean);
            }
        }
    }

}
