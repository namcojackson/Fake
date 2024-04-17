/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001;

import static com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDBMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_IMPT_DTL_EXT_ATTRBTMsg;
import business.db.DS_IMPT_EXT_ATTRBTMsg;
import business.db.DS_IMPT_ORDTMsg;
import business.db.DS_IMPT_ORD_APVL_NOTETMsg;
import business.db.DS_IMPT_ORD_CONFIGTMsg;
import business.db.DS_IMPT_ORD_CTAC_PSNTMsg;
import business.db.DS_IMPT_ORD_DELY_INFOTMsg;
import business.db.DS_IMPT_ORD_DTLTMsg;
import business.db.DS_IMPT_ORD_ERRTMsg;
import business.db.DS_IMPT_ORD_ISTL_INFOTMsg;
import business.db.DS_IMPT_ORD_RTRN_DTLTMsg;
import business.db.DS_IMPT_ORD_SITE_SRVYTMsg;
import business.db.DS_IMPT_ORD_SLS_CRTMsg;
import business.db.DS_IMPT_ORD_SOM_APVLTMsg;
import business.db.DS_IMPT_ORD_SOM_PRFTTMsg;
import business.db.DS_IMPT_PRC_CALC_BASETMsg;
import business.db.DS_IMPT_RTRN_PRC_CALCTMsg;
import business.db.DS_IMPT_SCHD_TMPLTMsg;
import business.db.DS_IMPT_SCHD_TMPL_LINETMsg;
import business.db.DS_IMPT_SVC_ADDL_BASETMsg;
import business.db.DS_IMPT_SVC_ADDL_CHRGTMsg;
import business.db.DS_IMPT_SVC_CONFIG_REFTMsg;
import business.db.DS_IMPT_SVC_DTLTMsg;
import business.db.DS_IMPT_SVC_PRCTMsg;
import business.db.DS_IMPT_SVC_PRC_TAX_DTLTMsg;
import business.db.DS_IMPT_SVC_USG_PRCTMsg;
import business.db.EOPS_LINE_VIEW_SNAPTMsg;
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
import business.db.NWAI4150_26TMsg;

import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.CUTOFF_LEN;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.DELY_INFO_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.FLG;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.ISTL_INFO_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.MSG_ID;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.SLS_CR_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.VAR_CHAR_CONST_NM;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Result;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Util;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * EOPS Interface Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 * 
 * </pre>
 */
public class NWAB415001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** System date */
    private final String sysdt = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");

    /** Interface Id */
    private String interfaceId = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Normal Count */
    private int totalNmlCount = 0;

    /** Error Count */
    private int totalErrCount = 0;

    /** Total Count */
    private int totalCount = 0;

    /** transaction id array */
    private BigDecimal[] transactionIds;

    /**  */
    private S21SsmBatchClient ssmBatchClient;

    /**  */
    private HashMap<String, Map<String, Object>> cacheItemMap;

    /**  */
    private HashMap<String, String> cachePrcCatgCdMap;

    /**  */
    private HashMap<String, String> cachePrcCatgNmMap;

    /**  */
    private HashMap<String, BigDecimal> cacheSpecCondPrcMap;

    /**  */
    private HashMap<String, Map<String, Object>> cacheShipToCustMap;

    /**  */
    private HashMap<String, String> cacheS21PsnFromInstallRepNameMap;

    /**  */
    private HashMap<String, Map<String, Object>> cacheSlsRepTocCdMap;

    /**  */
    private HashMap<String, String> cacheSlsRepRoleTpCdMap;

    /**  */
    private HashMap<String, BigDecimal> cacheModelMap;

    /**  */
    private HashMap<String, BigDecimal> cachePrcMtrPkgPkMap;

    @Override
    protected void initRoutine() {

        //        try {
        //
        //            // for debug
        //            String[] userValiable5 = getUserVariable5().split(",");
        //            this.transactionIds = new BigDecimal[userValiable5.length];
        //            for (int i = 0; i < userValiable5.length; i++) {
        //
        //                this.transactionIds[i] = new BigDecimal(userValiable5[i]);
        //            }
        //
        //        } catch (Throwable e) {

        this.transactionIds = null;
        //        }

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Global Company Code"));
        }

        // Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // InterfaceId
        this.interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.interfaceId)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("InterfaceID"));
        }

        this.cacheItemMap = new HashMap<String, Map<String, Object>>();
        this.cachePrcCatgCdMap = new HashMap<String, String>();
        this.cachePrcCatgNmMap = new HashMap<String, String>();
        this.cacheSpecCondPrcMap = new HashMap<String, BigDecimal>();
        this.cacheShipToCustMap = new HashMap<String, Map<String, Object>>();
        this.cacheS21PsnFromInstallRepNameMap = new HashMap<String, String>();
        this.cacheSlsRepTocCdMap = new HashMap<String, Map<String, Object>>();
        this.cacheSlsRepRoleTpCdMap = new HashMap<String, String>();
        this.cacheModelMap = new HashMap<String, BigDecimal>();
        this.cachePrcMtrPkgPkMap = new HashMap<String, BigDecimal>();

    }

    /**
     * Create Array.
     * @param <T>
     * @param param
     * @return
     */
    private static <T> T[] toArray(T... param) {
        return param;
    }

    @Override
    protected void mainRoutine() {
        registerDsImptTable();

    }

    private void registerDsImptTable() {
        // *************************************************************
        // Deriving TransactionID
        // *************************************************************
        S21TransactionTableAccessor s21tta = new S21TransactionTableAccessor();
        if (this.transactionIds == null) {

            this.transactionIds = s21tta.getIntegrationRecord(this.interfaceId);
        }
        if (this.transactionIds == null || this.transactionIds.length == 0) {
            return;
        }

        BigDecimal eopsQuoteId;

        List<NWAI4150_02TMsg> nwai415002msgList;
        List<NWAI4150_03TMsg> nwai415003msgList;
        List<NWAI4150_04TMsg> nwai415004msgList;
        List<NWAI4150_06TMsg> nwai415006msgList;

        List<NWAI4150_10TMsg> nwai415010msgList;

        List<NWAI4150_13TMsg> nwai415013msgList;
        List<NWAI4150_14TMsg> nwai415014msgList;
        List<NWAI4150_16TMsg> nwai415016msgList;

        List<NWAI4150_18TMsg> nwai415018msgList;
        List<NWAI4150_19TMsg> nwai415019msgList;

        List<NWAI4150_22TMsg> nwai415022msgList;
        List<NWAI4150_23TMsg> nwai415023msgList;
        List<NWAI4150_24TMsg> nwai415024msgList;

        //VarCharConst
        Map<String, Object> varCharConstMap = getVarCharConstMap();

        for (int i = 0; i < transactionIds.length; i++) {
            // *************************************************************
            // Deriving NWAI4150_01
            // *************************************************************
            List<NWAI4150_01TMsg> nwai415001msgList = this.getNwai415001(transactionIds[i]);
            if (!NWXC412001.hasValueList(nwai415001msgList)) {
                s21tta.endIntegrationProcess(this.interfaceId, transactionIds[i]);
                continue;
            }

            for (int j = 0; j < nwai415001msgList.size(); j++) {
                this.totalCount++;
                boolean isError = false;

                EopsDsImptOrdBean eopsDsImptOrdBean = new EopsDsImptOrdBean(this.ssmBatchClient, this.glblCmpyCd, this.salesDate, nwai415001msgList.get(j));

                eopsDsImptOrdBean.setVarCharConstMap(varCharConstMap); //VarCharConst Cache
                eopsDsImptOrdBean.setCacheItemMap(cacheItemMap); //Item Cache
                eopsDsImptOrdBean.setCachePrcCatgCdMap(cachePrcCatgCdMap); //Price List Cache
                eopsDsImptOrdBean.setCachePrcCatgNmMap(cachePrcCatgNmMap); //Price List Name Cache
                eopsDsImptOrdBean.setCacheShipToCustMap(cacheShipToCustMap); //Ship To Customer Cache
                eopsDsImptOrdBean.setCacheSpecCondPrcMap(cacheSpecCondPrcMap); //Specific Condition Cache
                eopsDsImptOrdBean.setCacheSlsRepTocCdMap(cacheSlsRepTocCdMap); //Sales REP Cache
                eopsDsImptOrdBean.setCacheS21PsnFromInstallRepNameMap(cacheS21PsnFromInstallRepNameMap); //S21 Person Cache
                eopsDsImptOrdBean.setCacheSlsRepRoleTpCdMap(cacheSlsRepRoleTpCdMap); //Sales REP Role Type Code Cache
                eopsDsImptOrdBean.setCacheModelMap(cacheModelMap); //Model Cache
                eopsDsImptOrdBean.setCachePrcMtrPkgPkMap(cachePrcMtrPkgPkMap); //Service Machine Master PK Cache
                eopsQuoteId = eopsDsImptOrdBean.nwai415001.somQuoteId.getValue();

                // validate order source reference number.
                NWXC220001Result<Map<String, List<BigDecimal>>> result //
                = NWXC220001.checkDuplicateOrdSrcRefNum(//
                        this.glblCmpyCd, NWXC412001.formatOrdSrcRefNum(eopsQuoteId.toPlainString(), EOPS_PREFIX_ORD_SRC_NUM, CUTOFF_LEN.ORD_SRC_REF_NUM.getLen()), CPO_SRC_TP.EOPS, null);
                Map<String, List<BigDecimal>> duplicateDsImptPkMap = result.getResultObject();

                // delete import staging.
                if (!deleteDuplicateDsImpt(duplicateDsImptPkMap)) {
                    rollback();
                    this.totalErrCount++;
                    continue;
                } else {
                    commit();
                }

                // *********************************************************
                // Deriving NWAI4150_12(V_QUOTE_CONFIGURATION_SHIPTO)
                // *********************************************************
                List<NWAI4150_12TMsg> nwai415012msgList = this.getNwai415012(transactionIds[i], eopsQuoteId);
                String lastUpdTs01 = nwai415001msgList.get(j).lastUpdTs.getValue();
                boolean skipOrdFlg = true;
                for (NWAI4150_12TMsg nwai415012msg : nwai415012msgList) {
                    if (S21StringUtil.isEquals(lastUpdTs01, nwai415012msg.lastUpdTs.getValue())) {
                        skipOrdFlg = false;
                        break;
                    }
                }
                if (skipOrdFlg) {
                    continue;
                }

                //Install Information
                new EopsDsImptOrdIstlInfoBean(ISTL_INFO_TYPE.HEADER_DELY_INFO, eopsDsImptOrdBean);
                //Delivery Information
                new EopsDsImptOrdDelyInfoBean(DELY_INFO_TYPE.HEADER_DELY_INFO, eopsDsImptOrdBean);

                // *********************************************************
                // Process (1) (HEADER)
                // *********************************************************
                new EopsDsImptOrdSlsCrBean(SLS_CR_TYPE.HEADER, eopsDsImptOrdBean);
                eopsDsImptOrdBean.addDsImptOrdCtacPsnBean();

                // *********************************************************
                // Deriving NWAI4150_02(V_APPROVAL)
                // *********************************************************
                nwai415002msgList = this.getNwai415002(transactionIds[i], eopsQuoteId);

                if (NWXC412001.hasValueList(nwai415002msgList)) {
                    // *****************************************************
                    // Deriving NWAI4150_03(V_APPROVAL_NOTE)
                    // *****************************************************
                    nwai415003msgList = this.getNwai415003(transactionIds[i], eopsQuoteId);

                    eopsDsImptOrdBean.createImptConfigDtlForApproval(eopsDsImptOrdBean, nwai415002msgList, nwai415003msgList);
                }

                // *********************************************************
                // Deriving NWAI4150_04(V_QUOTE_CALC)
                // *********************************************************
                nwai415004msgList = this.getNwai415004(transactionIds[i], eopsQuoteId);

                if (NWXC412001.hasValueList(nwai415004msgList)) {
                    eopsDsImptOrdBean.createImptConfigDtlForProfitability(eopsDsImptOrdBean, nwai415004msgList);
                }

                if (NWXC412001.hasValueList(nwai415012msgList)) {

                    // *****************************************************
                    // Deriving NWAI4150_13(V_QUOTE_SERIAL)
                    // *****************************************************
                    nwai415013msgList = this.getNwai415013(transactionIds[i], eopsQuoteId);

                    // *****************************************************
                    // Deriving NWAI4150_10(V_QUOTE_LINES)
                    // *****************************************************
                    nwai415010msgList = this.getNwai415010(transactionIds[i], eopsQuoteId);

                    // *****************************************************
                    // Deriving NWAI4150_23(V_QUOTE_CONFIG_LINE_COSTS)
                    // *****************************************************
                    nwai415023msgList = this.getNwai415023(transactionIds[i], eopsQuoteId);

                    // *****************************************************
                    // Deriving NWAI4150_14(V_QUOTE_OCE_PROMO)
                    // *****************************************************
                    nwai415014msgList = this.getNwai415014(transactionIds[i], eopsQuoteId);

                    // *********************************************************
                    // Deriving NWAI4150_24(V_QUOTE_LINE_RETURN)
                    // *********************************************************
                    nwai415024msgList = this.getNwai415024(transactionIds[i], eopsQuoteId);

                    eopsDsImptOrdBean.createImptConfigDtlFromNWAI415012(//
                            eopsDsImptOrdBean //
                            , nwai415012msgList //
                            , nwai415013msgList //
                            , nwai415014msgList //
                            , nwai415010msgList //
                            , nwai415023msgList);
                    eopsDsImptOrdBean.createImptConfigDtlFromNWAI415023(//
                            eopsDsImptOrdBean //
                            , nwai415023msgList //
                            , nwai415024msgList);
                }

                // *********************************************************
                // Deriving NWAI4150_05(V_LINE_SOFTCOSTS)
                // *********************************************************
                List<NWAI4150_05TMsg> nwai415005msgList = this.getNwai415005(transactionIds[i], eopsQuoteId);

                if (NWXC412001.hasValueList(nwai415005msgList)) {
                    // *****************************************************
                    // Deriving NWAI4150_06(V_LINE_TRADE_INS)
                    // *****************************************************
                    nwai415006msgList = this.getNwai415006(transactionIds[i], eopsQuoteId);

                    eopsDsImptOrdBean.createImptConfigDtlFromNWAI415005(eopsDsImptOrdBean, nwai415005msgList, nwai415006msgList);
                }

                eopsDsImptOrdBean.createImptOrdDtlForHdrRebate(eopsDsImptOrdBean);

                //Only Not Decline Maintenance in Header Level
                if (!FLG.YES.name().equals(eopsDsImptOrdBean.nwai415001.dclnMaintIndSomTxt.getValue())) {
                    // *********************************************************
                    // Deriving NWAI4150_16(V_SRV_HEADERS)
                    // *********************************************************
                    nwai415016msgList = this.getNwai415016(transactionIds[i], eopsQuoteId);

                    if (NWXC412001.hasValueList(nwai415016msgList)) {
                        //                        // *********************************************************
                        //                        // Deriving NWAI4150_17 (V_SRV_BASEONLYS)
                        //                        // *********************************************************
                        //                        nwai415017msgList = this.getNwai415017(transactionIds[i], eopsQuoteId);

                        // *********************************************************
                        // Deriving NWAI4150_18 (V_SRV_COPIERS)
                        // *********************************************************
                        nwai415018msgList = this.getNwai415018(transactionIds[i], eopsQuoteId);
                        nwai415022msgList = this.getNwai415022(transactionIds[i], eopsQuoteId);

                        // *********************************************************
                        // Deriving NWAI4150_19 (V_SRV_NONCOPIERS)
                        // *********************************************************
                        nwai415019msgList = this.getNwai415019(transactionIds[i], eopsQuoteId);

                        //                        // *********************************************************
                        //                        // Deriving NWAI4150_20 (V_SRV_SPECIALSERVICE_CHARGES)
                        //                        // *********************************************************
                        //                        nwai415020msgList = this.getNwai415020(transactionIds[i], eopsQuoteId);
                        //
                        //                        // *********************************************************
                        //                        // Deriving NWAI4150_21 (V_SRV_RENTAL)
                        //                        // *********************************************************
                        //                        nwai415021msgList = this.getNwai415021(transactionIds[i], eopsQuoteId);

                        if (NWXC412001.hasValueList(nwai415018msgList)) {
                            eopsDsImptOrdBean.createShellForSrvCopiers(eopsDsImptOrdBean, nwai415016msgList, nwai415018msgList, nwai415022msgList);
                        }

                        if (NWXC412001.hasValueList(nwai415019msgList)) {
                            eopsDsImptOrdBean.createShellForSrvNonCopiers(eopsDsImptOrdBean, nwai415016msgList, nwai415018msgList, nwai415019msgList, nwai415022msgList);
                        }
                    }
                }

                // *********************************************************
                // Deriving NWAI4150_08(V_SPIFF_DETAILS)
                // *********************************************************
                List<NWAI4150_08TMsg> nwai415008msgList = this.getNwai415008(transactionIds[i], eopsQuoteId);

                if (NWXC412001.hasValueList(nwai415008msgList)) {
                    eopsDsImptOrdBean.createImptOrdDtlForSpiffItems(eopsDsImptOrdBean, nwai415008msgList);
                }
                // *********************************************************
                // Process (16) (Ship Config)
                // Process (16) (RMA Config)
                // *********************************************************
                eopsDsImptOrdBean.createImptOrdSlsCrForConfig(eopsDsImptOrdBean);

                eopsDsImptOrdBean.doImptMapping(this.glblCmpyCd, this.salesDate);

                // *********************************************************
                // Deriving NWAI4150_26(V_EMSD_QUOTE_CONFIG_SERVICE_DETAILS)
                // *********************************************************
                List<NWAI4150_26TMsg> nwai415026msgList = this.getNwai415026(transactionIds[i], eopsQuoteId);
                insertEopsLineViewSnap(nwai415026msgList);

                if (isError || checkMdlId(eopsDsImptOrdBean)) {
                    isError = true;
                } else if (insertImptTables(eopsDsImptOrdBean)) {
                    commit();
                } else {
                    rollback();
                    isError = true;
                }

                if (isError) {
                    this.totalErrCount++;
                } else {
                    this.totalNmlCount++;
                }
            }
            s21tta.endIntegrationProcess(this.interfaceId, transactionIds[i]);
        }
    }

    private void insertEopsLineViewSnap(List<NWAI4150_26TMsg> nwai415026msgList) {
        List<EOPS_LINE_VIEW_SNAPTMsg> insTMsgList = new ArrayList<EOPS_LINE_VIEW_SNAPTMsg>();
        List<EOPS_LINE_VIEW_SNAPTMsg> rmvTMsgList = new ArrayList<EOPS_LINE_VIEW_SNAPTMsg>();
        for (NWAI4150_26TMsg i26TMsg : nwai415026msgList) {
            List<BigDecimal> pkList = getExistsEopsLineViewSnapPk(i26TMsg);
            if (NWXC412001.hasValueList(pkList)) {
                for (BigDecimal pk : pkList) {
                    EOPS_LINE_VIEW_SNAPTMsg rmvTMsg = new EOPS_LINE_VIEW_SNAPTMsg();
                    rmvTMsg.glblCmpyCd.setValue(glblCmpyCd);
                    rmvTMsg.eopsLineViewSnapPk.setValue(pk);
                    rmvTMsgList.add(rmvTMsg);
                }
            }
            EOPS_LINE_VIEW_SNAPTMsg insTMsg = new EOPS_LINE_VIEW_SNAPTMsg();
            EZDBMsg.copy(i26TMsg, null, insTMsg, null);
            ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(insTMsg.eopsLineViewSnapPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EOPS_LINE_VIEW_SNAP_SQ));
            ZYPEZDItemValueSetter.setValue(insTMsg.eopsQuoteId, i26TMsg.somQuoteId);
            ZYPEZDItemValueSetter.setValue(insTMsg.eopsConfigLtrTxt, i26TMsg.eopsConfigLtrTxt);
            ZYPEZDItemValueSetter.setValue(insTMsg.eopsOrdQty, i26TMsg.somOrdQty);
            ZYPEZDItemValueSetter.setValue(insTMsg.sellPrcListAmt, i26TMsg.sellPrcListAmt);
            ZYPEZDItemValueSetter.setValue(insTMsg.extMthPrcAmt, i26TMsg.extMthPrcAmt);
            ZYPEZDItemValueSetter.setValue(insTMsg.extSellPrcAmt, i26TMsg.extSellPrcAmt);
            ZYPEZDItemValueSetter.setValue(insTMsg.eopsContrTpDescTxt, i26TMsg.contrTpTxt);
            ZYPEZDItemValueSetter.setValue(insTMsg.eopsPlnDescTxt, i26TMsg.planDescTxt);
            ZYPEZDItemValueSetter.setValue(insTMsg.eopsSrvTermId, i26TMsg.somSrvTermId);
            ZYPEZDItemValueSetter.setValue(insTMsg.eopsBllgCycleDescTxt, i26TMsg.somBllgCycleDescTxt);
            ZYPEZDItemValueSetter.setValue(insTMsg.eopsUsgCycleDescTxt, i26TMsg.usgCycleDescTxt);
            ZYPEZDItemValueSetter.setValue(insTMsg.extPmtAmt, i26TMsg.extPmtAmt);
            ZYPEZDItemValueSetter.setValue(insTMsg.basePrcAmt, i26TMsg.reqBaseAmt);
            ZYPEZDItemValueSetter.setValue(insTMsg.bwMtrTpDescTxt, i26TMsg.bwMtrTpNm);
            ZYPEZDItemValueSetter.setValue(insTMsg.bwVolComitCnt, i26TMsg.bwVolComitCnt);
            ZYPEZDItemValueSetter.setValue(insTMsg.bwBaseAmt, i26TMsg.bwBaseAmt);
            ZYPEZDItemValueSetter.setValue(insTMsg.bwCpcAmtEopsRate, i26TMsg.bwCpcAmt);
            ZYPEZDItemValueSetter.setValue(insTMsg.bwPmtAmt, i26TMsg.bwPmtAmt);
            ZYPEZDItemValueSetter.setValue(insTMsg.clrMtrTpDescTxt, i26TMsg.clrMtrTpNm);
            ZYPEZDItemValueSetter.setValue(insTMsg.clrVolComitCnt, i26TMsg.clrVolComitCnt);
            ZYPEZDItemValueSetter.setValue(insTMsg.clrBaseAmt, i26TMsg.clrBaseAmt);
            ZYPEZDItemValueSetter.setValue(insTMsg.clrCpcAmtEopsRate, i26TMsg.clrCpcAmt);
            ZYPEZDItemValueSetter.setValue(insTMsg.clrPmtAmt, i26TMsg.clrPmtAmt);
            ZYPEZDItemValueSetter.setValue(insTMsg.mthBaseAmt, i26TMsg.mthBaseAmt);
            ZYPEZDItemValueSetter.setValue(insTMsg.eopsCratTs, sysdt);
            insTMsg.cratByUsrId.clear();
            ZYPEZDItemValueSetter.setValue(insTMsg.eopsUpdTs, sysdt);
            insTMsg.updByUsrId.clear();
            insTMsgList.add(insTMsg);
        }
        if (NWXC412001.hasValueList(rmvTMsgList)) {
            S21FastTBLAccessor.removeLogical(rmvTMsgList.toArray(new EOPS_LINE_VIEW_SNAPTMsg[rmvTMsgList.size()]));
        }
        if (NWXC412001.hasValueList(insTMsgList)) {
            S21FastTBLAccessor.insert(insTMsgList.toArray(new EOPS_LINE_VIEW_SNAPTMsg[insTMsgList.size()]));
        }
    }

    private List<BigDecimal> getExistsEopsLineViewSnapPk(NWAI4150_26TMsg i26TMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("eopsQuoteId", i26TMsg.somQuoteId);
        ssmParam.put("eopsConfigLtrTxt", i26TMsg.eopsConfigLtrTxt);

        return NWXC412001.autoCast(this.ssmBatchClient.queryObjectList("getExistsEopsLineViewSnapPk", ssmParam));
    }

    private boolean insertImptTables(EopsDsImptOrdBean eopsDsImptOrdBean) {
        // *********************************************************************
        // Header
        // *********************************************************************
        insertImptTable((DS_IMPT_ORDTMsg) eopsDsImptOrdBean);

        insertImptTable((DS_IMPT_ORD_DELY_INFOTMsg) eopsDsImptOrdBean.dsImptOrdDelyInfoBean);
        insertImptTable((DS_IMPT_ORD_ISTL_INFOTMsg) eopsDsImptOrdBean.dsImptOrdIstlInfoBean);
        //        insertImptTable((DS_IMPT_ORD_SITE_SRVYTMsg) somDsImptOrdBean.dsImptOrdSiteSrvyBean); // 2017/09/13 QC#21084 Del

        for (EopsDsImptOrdEopsApvlBean apvlBean : eopsDsImptOrdBean.dsImptOrdEopsApvlBeanList) {
            insertImptTable((DS_IMPT_ORD_SOM_APVLTMsg) apvlBean);

            for (EopsDsImptOrdApvlNoteBean apvlNoteBean : apvlBean.dsImptOrdApvlNoteBeanList) {
                insertImptTable((DS_IMPT_ORD_APVL_NOTETMsg) apvlNoteBean);
            }
        }

        for (EopsDsImptOrdEopsPrftBean prftBean : eopsDsImptOrdBean.dsImptOrdEopsPrftBeanList) {
            insertImptTable((DS_IMPT_ORD_SOM_PRFTTMsg) prftBean);
        }

        if (eopsDsImptOrdBean.dsImptOrdSlsCrBean != null) {
            insertImptTable((DS_IMPT_ORD_SLS_CRTMsg) eopsDsImptOrdBean.dsImptOrdSlsCrBean);

            for (EopsDsImptOrdSlsCrBean slsCrBean : eopsDsImptOrdBean.dsImptOrdSlsCrBean.dsImptOrdSlsCrChildBeanList) {
                insertImptTable((DS_IMPT_ORD_SLS_CRTMsg) slsCrBean);
            }
        }

        for (EopsDsImptOrdCtacPsnBean ctacPsnBean : eopsDsImptOrdBean.dsImptOrdCtacPsnBeanList) {
            if (ZYPCommonFunc.hasValue(ctacPsnBean.ctacPsnFirstNm)) {
                insertImptTable((DS_IMPT_ORD_CTAC_PSNTMsg) ctacPsnBean);
            }
        }

        for (EopsDsImptOrdConfigBean configBean : eopsDsImptOrdBean.dsImptOrdConfigBeanList) {
            // *****************************************************************
            // Config
            // *****************************************************************
            insertImptTable((DS_IMPT_ORD_CONFIGTMsg) configBean);

            if (configBean.dsImptOrdSlsCrBean != null) {
                insertImptTable((DS_IMPT_ORD_SLS_CRTMsg) configBean.dsImptOrdSlsCrBean);

                for (EopsDsImptOrdSlsCrBean slsCrBean : configBean.dsImptOrdSlsCrBean.dsImptOrdSlsCrChildBeanList) {
                    insertImptTable((DS_IMPT_ORD_SLS_CRTMsg) slsCrBean);
                }
            }

            for (EopsDsImptOrdCtacPsnBean ctacPsnBean : configBean.dsImptOrdCtacPsnBeanList) {
                if (ZYPCommonFunc.hasValue(ctacPsnBean.ctacPsnFirstNm)) {
                    insertImptTable((DS_IMPT_ORD_CTAC_PSNTMsg) ctacPsnBean);
                }
            }

            if (configBean.dsImptOrdIstlInfoBean != null) {
                insertImptTable((DS_IMPT_ORD_ISTL_INFOTMsg) configBean.dsImptOrdIstlInfoBean);
            }

            if (configBean.dsImptOrdDelyInfoBean != null) {
                insertImptTable((DS_IMPT_ORD_DELY_INFOTMsg) configBean.dsImptOrdDelyInfoBean);
            }

            //            if (configBean.dsImptOrdIstlInfoBean != null) {
            //                insertImptTable((DS_IMPT_ORD_SITE_SRVYTMsg) configBean.dsImptOrdSiteSrvyBean); // 2017/09/13 QC#21084 Del
            //            }

            // *****************************************************************
            // Detail
            // *****************************************************************
            for (EopsDsImptOrdDtlBean dtlBean : configBean.dsImptOrdDtlBeanList) {
                insertImptTable((DS_IMPT_ORD_DTLTMsg) dtlBean);

                for (EopsDsImptPrcCalcBaseBean prcCalcBaseBean : dtlBean.dsImptPrcCalcBaseBeanList) {
                    insertImptTable((DS_IMPT_PRC_CALC_BASETMsg) prcCalcBaseBean);
                }
            }

            // *****************************************************************
            // RMA
            // *****************************************************************
            for (EopsDsImptOrdRtrnDtlBean rtrnDtlBean : configBean.dsImptOrdDtlRtrnBeanList) {
                insertImptTable((DS_IMPT_ORD_RTRN_DTLTMsg) rtrnDtlBean);

                for (EopsDsImptRtrnPrcCalcBean rtrnPrcCalcBean : rtrnDtlBean.dsImptRtrnPrcCalcBeanList) {
                    insertImptTable((DS_IMPT_RTRN_PRC_CALCTMsg) rtrnPrcCalcBean);
                }
            }
        }

        // *****************************************************************
        // Shell
        // *****************************************************************
        for (EopsDsImptSvcDtlBean svcDtlBean : eopsDsImptOrdBean.dsImptSvcDtlBeanList) {
            insertImptTable((DS_IMPT_SVC_DTLTMsg) svcDtlBean);

            for (EopsDsImptSvcConfigRefBean svcConfigRefBean : svcDtlBean.dsImptSvcConfigRefBeanList) {
                insertImptTable((DS_IMPT_SVC_CONFIG_REFTMsg) svcConfigRefBean);
            }

            for (EopsDsImptSvcPrcBean svcPrcBean : svcDtlBean.dsImptSvcPrcBeanList) {
                insertImptTable((DS_IMPT_SVC_PRCTMsg) svcPrcBean);

                for (EopsDsImptSvcUsgPrcBean svcUsgPrcBean : svcPrcBean.dsImptSvcUsgPrcBeanList) {
                    insertImptTable((DS_IMPT_SVC_USG_PRCTMsg) svcUsgPrcBean);
                }
            }

            //            for (EopsDsImptSvcAddlBaseBean svcAddlBaseBean : svcDtlBean.dsImptSvcAddlBaseBeanList) {
            //                insertImptTable((DS_IMPT_SVC_ADDL_BASETMsg) svcAddlBaseBean);
            //            }
            //
            //            for (EopsDsImptSvcAddlChrgBean svcAddlBaseBean : svcDtlBean.dsImptSvcAddlChrgBeanList) {
            //                insertImptTable((DS_IMPT_SVC_ADDL_CHRGTMsg) svcAddlBaseBean);
            //            }
        }

        return (eopsDsImptOrdBean.getErrorInfo().size() == 0);
    }

    private void insertImptTable(EZDTMsg inMsg) {
        S21FastTBLAccessor.insert(inMsg);
        NWXC220001Util.checkTMsgDbAccess(inMsg, false);
    }

    private boolean checkMdlId(EopsDsImptOrdBean eopsDsImptOrdBean) {
        List<BigDecimal> mdlIdList = new ArrayList<BigDecimal>();
        for (EopsDsImptOrdConfigBean configBean : eopsDsImptOrdBean.dsImptOrdConfigBeanList) {
            if (ZYPCommonFunc.hasValue(configBean.mdlId)) {
                mdlIdList.add(configBean.mdlId.getValue());
            }
        }
        for (EopsDsImptSvcDtlBean svcDtlBean : eopsDsImptOrdBean.dsImptSvcDtlBeanList) {
            for (EopsDsImptSvcPrcBean svcPrcBean : svcDtlBean.dsImptSvcPrcBeanList) {
                if (ZYPCommonFunc.hasValue(svcPrcBean.mdlId) && !mdlIdList.contains(svcPrcBean.mdlId.getValue())) {
                    S21InfoLogOutput.println(MSG_ID.NWAM0937E.toString());
                    return true;
                }
            }
        }
        return false;
    }

    private List<NWAI4150_01TMsg> getNwai415001(BigDecimal transactionId) {
        List<NWAI4150_01TMsg> msgList = new ArrayList<NWAI4150_01TMsg>();
        Map<String, Object> ssmParamNWAI415001 = new HashMap<String, Object>();
        ssmParamNWAI415001.put("interfaceId", this.interfaceId);
        ssmParamNWAI415001.put("transactionId", transactionId);
        ssmParamNWAI415001.put("glblCmpyCd", glblCmpyCd);
        ssmParamNWAI415001.put("cpoSrcTpEOSP", CPO_SRC_TP.EOPS);

        List<Map<String, Object>> nwai415001TMsgList //
        = NWXC412001.autoCast(this.ssmBatchClient.queryObjectList("getNWAI4150_01", ssmParamNWAI415001));

        for (Map<String, Object> nwai415001TMsgMap : nwai415001TMsgList) {
            NWAI4150_01TMsg nwai415001TMsg = new NWAI4150_01TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.interfaceId, (String) nwai415001TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.transactionId, (BigDecimal) nwai415001TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.segmentId, (BigDecimal) nwai415001TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.unitId, (BigDecimal) nwai415001TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.seqNumber, (BigDecimal) nwai415001TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.intfcRecId, (String) nwai415001TMsgMap.get("INTFC_REC_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somQuoteNm, (String) nwai415001TMsgMap.get("SOM_QUOTE_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somQuoteId, (BigDecimal) nwai415001TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somQuoteNum, (BigDecimal) nwai415001TMsgMap.get("SOM_QUOTE_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somQuoteStsNm, (String) nwai415001TMsgMap.get("SOM_QUOTE_STS_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somSlsRepId, (String) nwai415001TMsgMap.get("SOM_SLS_REP_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somSlsRepLastNm, (String) nwai415001TMsgMap.get("SOM_SLS_REP_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somSlsRepFirstNm, (String) nwai415001TMsgMap.get("SOM_SLS_REP_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somBrNm, (String) nwai415001TMsgMap.get("SOM_BR_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.cratByTxt, (String) nwai415001TMsgMap.get("CRAT_BY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.cratTs, (String) nwai415001TMsgMap.get("CRAT_TS"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somShipToCustId, (BigDecimal) nwai415001TMsgMap.get("SOM_SHIP_TO_CUST_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.shipToAcctNum, (String) nwai415001TMsgMap.get("SHIP_TO_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.shipToCustLegalNm, (String) nwai415001TMsgMap.get("SHIP_TO_CUST_LEGAL_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somShipToCustNm, (String) nwai415001TMsgMap.get("SOM_SHIP_TO_CUST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.shipToAddrDescTxt_01, (String) nwai415001TMsgMap.get("SHIP_TO_ADDR_DESC_TXT_01"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.shipToAddrDescTxt_02, (String) nwai415001TMsgMap.get("SHIP_TO_ADDR_DESC_TXT_02"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.shipToCityTxt, (String) nwai415001TMsgMap.get("SHIP_TO_CITY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.shipToCntyTxt, (String) nwai415001TMsgMap.get("SHIP_TO_CNTY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.shipToStTxt, (String) nwai415001TMsgMap.get("SHIP_TO_ST_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somShipToZipCd, (String) nwai415001TMsgMap.get("SOM_SHIP_TO_ZIP_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somShipToPtyNum, (BigDecimal) nwai415001TMsgMap.get("SOM_SHIP_TO_PTY_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.shipToCustPtySiteNum, (String) nwai415001TMsgMap.get("SHIP_TO_CUST_PTY_SITE_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.shipToCrmSlsId, (String) nwai415001TMsgMap.get("SHIP_TO_CRM_SLS_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.shipToCrmSlsNum, (String) nwai415001TMsgMap.get("SHIP_TO_CRM_SLS_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.eopsFmDeptCd, (String) nwai415001TMsgMap.get("EOPS_FM_DEPT_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somDiscAmt, (BigDecimal) nwai415001TMsgMap.get("SOM_DISC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.prcListDescTxt, (String) nwai415001TMsgMap.get("PRC_LIST_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.cusaChTxt, (String) nwai415001TMsgMap.get("CUSA_CH_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.negoDealAmt, (BigDecimal) nwai415001TMsgMap.get("NEGO_DEAL_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.svcInclIndSomTxt, (String) nwai415001TMsgMap.get("SVC_INCL_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.billToCustLegalNm, (String) nwai415001TMsgMap.get("BILL_TO_CUST_LEGAL_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.billToDbaTxt, (String) nwai415001TMsgMap.get("BILL_TO_DBA_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.bllgCmpyTxt, (String) nwai415001TMsgMap.get("BLLG_CMPY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.bllgAddrDescTxt, (String) nwai415001TMsgMap.get("BLLG_ADDR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.bllgCityDescTxt, (String) nwai415001TMsgMap.get("BLLG_CITY_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.bllgStTxt, (String) nwai415001TMsgMap.get("BLLG_ST_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.bllgZipTxt, (String) nwai415001TMsgMap.get("BLLG_ZIP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseCmpyNm, (String) nwai415001TMsgMap.get("LEASE_CMPY_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somLeaseRate, (BigDecimal) nwai415001TMsgMap.get("SOM_LEASE_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseSvcIndSomTxt, (String) nwai415001TMsgMap.get("LEASE_SVC_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseTaxIndSomTxt, (String) nwai415001TMsgMap.get("LEASE_TAX_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseTaxRate, (BigDecimal) nwai415001TMsgMap.get("LEASE_TAX_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somLeaseTermAot, (BigDecimal) nwai415001TMsgMap.get("SOM_LEASE_TERM_AOT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leasePmtAmt, (BigDecimal) nwai415001TMsgMap.get("LEASE_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseSvcPmtAmt, (BigDecimal) nwai415001TMsgMap.get("LEASE_SVC_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseEquipPmtAmt, (BigDecimal) nwai415001TMsgMap.get("LEASE_EQUIP_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseEquipTpTxt, (String) nwai415001TMsgMap.get("LEASE_EQUIP_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.cpcRntlIndSomTxt, (String) nwai415001TMsgMap.get("CPC_RNTL_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseRgIndSomTxt, (String) nwai415001TMsgMap.get("LEASE_RG_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseTrtyIndSomTxt, (String) nwai415001TMsgMap.get("LEASE_TRTY_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseCusaChTxt, (String) nwai415001TMsgMap.get("LEASE_CUSA_CH_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseAppTxt, (String) nwai415001TMsgMap.get("LEASE_APP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leasePrchOrderTxt, (String) nwai415001TMsgMap.get("LEASE_PRCH_ORDER_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseUpgAmt, (BigDecimal) nwai415001TMsgMap.get("LEASE_UPG_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somRevAmt, (BigDecimal) nwai415001TMsgMap.get("SOM_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseDownPmtAmt, (BigDecimal) nwai415001TMsgMap.get("LEASE_DOWN_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseSoftCostRate, (BigDecimal) nwai415001TMsgMap.get("LEASE_SOFT_COST_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseInEligFinAmt, (BigDecimal) nwai415001TMsgMap.get("LEASE_IN_ELIG_FIN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseInEligRate, (BigDecimal) nwai415001TMsgMap.get("LEASE_IN_ELIG_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseCrAmt, (BigDecimal) nwai415001TMsgMap.get("LEASE_CR_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseCrApplyToTxt, (String) nwai415001TMsgMap.get("LEASE_CR_APPLY_TO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.istlRepNm, (String) nwai415001TMsgMap.get("ISTL_REP_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.istlRepTxt, (String) nwai415001TMsgMap.get("ISTL_REP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.istlBrDrctrNm, (String) nwai415001TMsgMap.get("ISTL_BR_DRCTR_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.istlBrDrctrNum, (String) nwai415001TMsgMap.get("ISTL_BR_DRCTR_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.istlBrDescTxt, (String) nwai415001TMsgMap.get("ISTL_BR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somSpiffDtlTxt_02, (String) nwai415001TMsgMap.get("SOM_SPIFF_DTL_TXT_02"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.billToCtacLastNm, (String) nwai415001TMsgMap.get("BILL_TO_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.billToCtacFirstNm, (String) nwai415001TMsgMap.get("BILL_TO_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.billToCtacPhoTxt, (String) nwai415001TMsgMap.get("BILL_TO_CTAC_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.billToCtacPhoExtTxt, (String) nwai415001TMsgMap.get("BILL_TO_CTAC_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.billToCtacFaxTxt, (String) nwai415001TMsgMap.get("BILL_TO_CTAC_FAX_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.billToCtacEmlTxt, (String) nwai415001TMsgMap.get("BILL_TO_CTAC_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.chkOrdIndSomTxt, (String) nwai415001TMsgMap.get("CHK_ORD_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.eopsTrxTpTxt, (String) nwai415001TMsgMap.get("EOPS_TRX_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.netThrtyIndSomTxt, (String) nwai415001TMsgMap.get("NET_THRTY_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.othAquIndSomTxt, (String) nwai415001TMsgMap.get("OTH_AQU_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.othPmtTxtAquTxt, (String) nwai415001TMsgMap.get("OTH_PMT_TXT_AQU_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somChkNum, (String) nwai415001TMsgMap.get("SOM_CHK_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somCrCardNum, (String) nwai415001TMsgMap.get("SOM_CR_CARD_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somCrCardExprTxt, (String) nwai415001TMsgMap.get("SOM_CR_CARD_EXPR_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somCrCardNm, (String) nwai415001TMsgMap.get("SOM_CR_CARD_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.delyCtacLastNm, (String) nwai415001TMsgMap.get("DELY_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.delyCtacFirstNm, (String) nwai415001TMsgMap.get("DELY_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.delyCtacPhoTxt, (String) nwai415001TMsgMap.get("DELY_CTAC_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.delyCtacPhoExtTxt, (String) nwai415001TMsgMap.get("DELY_CTAC_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.delyCtacEmlTxt, (String) nwai415001TMsgMap.get("DELY_CTAC_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.erlstDelyTs, (String) nwai415001TMsgMap.get("ERLST_DELY_TS"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.salesTaxRate, (BigDecimal) nwai415001TMsgMap.get("SALES_TAX_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.itCtacLastNm, (String) nwai415001TMsgMap.get("IT_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.itCtacFirstNm, (String) nwai415001TMsgMap.get("IT_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.itCtacPhoTxt, (String) nwai415001TMsgMap.get("IT_CTAC_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.itCtacPhoExtTxt, (String) nwai415001TMsgMap.get("IT_CTAC_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.itCtacEmlTxt, (String) nwai415001TMsgMap.get("IT_CTAC_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.itCtacDeptTxt, (String) nwai415001TMsgMap.get("IT_CTAC_DEPT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.poReqIndSomTxt, (String) nwai415001TMsgMap.get("PO_REQ_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somPoNum, (String) nwai415001TMsgMap.get("SOM_PO_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.taxExemIndSomTxt, (String) nwai415001TMsgMap.get("TAX_EXEM_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.dclnMaintIndSomTxt, (String) nwai415001TMsgMap.get("DCLN_MAINT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.shipViaInstnTxt, (String) nwai415001TMsgMap.get("SHIP_VIA_INSTN_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.hrsOpTxt, (String) nwai415001TMsgMap.get("HRS_OP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somStepCnt, (BigDecimal) nwai415001TMsgMap.get("SOM_STEP_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.elevIndSomTxt, (String) nwai415001TMsgMap.get("ELEV_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.loadDockIndSomTxt, (String) nwai415001TMsgMap.get("LOAD_DOCK_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.delyInstnTxt, (String) nwai415001TMsgMap.get("DELY_INSTN_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.delyChrgAmt, (BigDecimal) nwai415001TMsgMap.get("DELY_CHRG_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somDepAmt, (BigDecimal) nwai415001TMsgMap.get("SOM_DEP_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.zeroDueIndSomTxt, (String) nwai415001TMsgMap.get("ZERO_DUE_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.secDepAmt, (BigDecimal) nwai415001TMsgMap.get("SEC_DEP_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.svcPrvdTxt, (String) nwai415001TMsgMap.get("SVC_PRVD_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseTpTxt, (String) nwai415001TMsgMap.get("LEASE_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.leaseFaxIndSomTxt, (String) nwai415001TMsgMap.get("LEASE_FAX_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.mthPmtIndSomTxt, (String) nwai415001TMsgMap.get("MTH_PMT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.qtlyPmtTxt, (String) nwai415001TMsgMap.get("QTLY_PMT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.othLeaseIndSomTxt, (String) nwai415001TMsgMap.get("OTH_LEASE_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.othPmtLeaseTxt, (String) nwai415001TMsgMap.get("OTH_PMT_LEASE_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.achRteCd, (String) nwai415001TMsgMap.get("ACH_RTE_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.achAcctNum, (String) nwai415001TMsgMap.get("ACH_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.nonRfIndSomTxt, (String) nwai415001TMsgMap.get("NON_RF_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.chkAgmtIndSomTxt, (String) nwai415001TMsgMap.get("CHK_AGMT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.realFairIndSomTxt, (String) nwai415001TMsgMap.get("REAL_FAIR_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.subSvcPmtTermTxt, (String) nwai415001TMsgMap.get("SUB_SVC_PMT_TERM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.subSvcAgmtTermTxt, (String) nwai415001TMsgMap.get("SUB_SVC_AGMT_TERM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somSubSvcAot, (BigDecimal) nwai415001TMsgMap.get("SOM_SUB_SVC_AOT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.subSvcFirstNm, (String) nwai415001TMsgMap.get("SUB_SVC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.subSvcLastNm, (String) nwai415001TMsgMap.get("SUB_SVC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.subSvcPhoTxt, (String) nwai415001TMsgMap.get("SUB_SVC_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.subSvcEmlTxt, (String) nwai415001TMsgMap.get("SUB_SVC_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.markViewBrcdTxt, (String) nwai415001TMsgMap.get("MARK_VIEW_BRCD_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.lastUpdTs, (String) nwai415001TMsgMap.get("LAST_UPD_TS"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.istlFeeAmt, (BigDecimal) nwai415001TMsgMap.get("ISTL_FEE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somSpiffDtlTxt_01, (String) nwai415001TMsgMap.get("SOM_SPIFF_DTL_TXT_01"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.maintBillIndSomTxt, (String) nwai415001TMsgMap.get("MAINT_BILL_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.maintBillCtacFirstNm, (String) nwai415001TMsgMap.get("MAINT_BILL_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.maintBillCtacLastNm, (String) nwai415001TMsgMap.get("MAINT_BILL_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.maintBillCtacPhoTxt, (String) nwai415001TMsgMap.get("MAINT_BILL_CTAC_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.maintBillCtacPhoExtTxt, (String) nwai415001TMsgMap.get("MAINT_BILL_CTAC_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.maintBillCtacEmlTxt, (String) nwai415001TMsgMap.get("MAINT_BILL_CTAC_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.maintBllgAddrDescTxt, (String) nwai415001TMsgMap.get("MAINT_BLLG_ADDR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.maintBllgCityDescTxt, (String) nwai415001TMsgMap.get("MAINT_BLLG_CITY_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.maintBllgStTxt, (String) nwai415001TMsgMap.get("MAINT_BLLG_ST_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.maintBllgZipTxt, (String) nwai415001TMsgMap.get("MAINT_BLLG_ZIP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.maintBllgCntyTxt, (String) nwai415001TMsgMap.get("MAINT_BLLG_CNTY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.bidPrcListNm, (String) nwai415001TMsgMap.get("BID_PRC_LIST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.delyFaxTxt, (String) nwai415001TMsgMap.get("DELY_FAX_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.maintPoVrsnTxt, (String) nwai415001TMsgMap.get("MAINT_PO_VRSN_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somPrcListId, (BigDecimal) nwai415001TMsgMap.get("SOM_PRC_LIST_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.csaPmtCtacFirstNm, (String) nwai415001TMsgMap.get("CSA_PMT_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.csaPmtCtacLastNm, (String) nwai415001TMsgMap.get("CSA_PMT_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.csaPmtCtacPhoTxt, (String) nwai415001TMsgMap.get("CSA_PMT_CTAC_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.csaPmtPhoExtTxt, (String) nwai415001TMsgMap.get("CSA_PMT_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.csaPmtCtacEmlTxt, (String) nwai415001TMsgMap.get("CSA_PMT_CTAC_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.csaPmtCtacTpTxt, (String) nwai415001TMsgMap.get("CSA_PMT_CTAC_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somAcctMgrTxt, (String) nwai415001TMsgMap.get("SOM_ACCT_MGR_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.rebMercCd, (String) nwai415001TMsgMap.get("REB_MERC_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somRebRate, (BigDecimal) nwai415001TMsgMap.get("SOM_REB_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somRebAmt, (BigDecimal) nwai415001TMsgMap.get("SOM_REB_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somVrsnId, (BigDecimal) nwai415001TMsgMap.get("SOM_VRSN_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somMaintPoNum, (String) nwai415001TMsgMap.get("SOM_MAINT_PO_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.custIssPoDt, (String) nwai415001TMsgMap.get("CUST_ISS_PO_DT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.prcContrNum, (String) nwai415001TMsgMap.get("PRC_CONTR_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.prcContrNm, (String) nwai415001TMsgMap.get("PRC_CONTR_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.prcContrPk, (BigDecimal) nwai415001TMsgMap.get("PRC_CONTR_PK"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.csmpIndSomTxt, (String) nwai415001TMsgMap.get("CSMP_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.somCsmpNum, (String) nwai415001TMsgMap.get("SOM_CSMP_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.csmpCrAmt, (BigDecimal) nwai415001TMsgMap.get("CSMP_CR_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415001TMsg.submtToOmTs, (String) nwai415001TMsgMap.get("SUBMT_TO_OM_TS "));

            msgList.add(nwai415001TMsg);
        }
        return msgList;
    }

    private List<NWAI4150_02TMsg> getNwai415002(BigDecimal transactionId, BigDecimal eopsQuoteId) {
        List<NWAI4150_02TMsg> msgList = new ArrayList<NWAI4150_02TMsg>();
        Map<String, Object> ssmParamNWAI415002 = new HashMap<String, Object>();
        ssmParamNWAI415002.put("interfaceId", this.interfaceId);
        ssmParamNWAI415002.put("transactionId", transactionId);
        ssmParamNWAI415002.put("somQuoteId", eopsQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai415002TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4150_02", ssmParamNWAI415002);

        for (Map<String, Object> nwai415002TMsgMap : nwai415002TMsgList) {
            NWAI4150_02TMsg nwai415002TMsg = new NWAI4150_02TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.interfaceId, (String) nwai415002TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.transactionId, (BigDecimal) nwai415002TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.segmentId, (BigDecimal) nwai415002TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.unitId, (BigDecimal) nwai415002TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.seqNumber, (BigDecimal) nwai415002TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.somApvlId, (BigDecimal) nwai415002TMsgMap.get("SOM_APVL_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.somQuoteId, (BigDecimal) nwai415002TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.somVrsnId, (BigDecimal) nwai415002TMsgMap.get("SOM_VRSN_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.apvlTpId, (BigDecimal) nwai415002TMsgMap.get("APVL_TP_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.wfStartTs, (String) nwai415002TMsgMap.get("WF_START_TS"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.wfEndTs, (String) nwai415002TMsgMap.get("WF_END_TS"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.somApvlTpTxt, (String) nwai415002TMsgMap.get("SOM_APVL_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.somStsTxt, (String) nwai415002TMsgMap.get("SOM_STS_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.somApvlDescTxt, (String) nwai415002TMsgMap.get("SOM_APVL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.shipToAddrKeyTxt, (String) nwai415002TMsgMap.get("SHIP_TO_ADDR_KEY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.splitRepTxt, (String) nwai415002TMsgMap.get("SPLIT_REP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.somApvrNm, (String) nwai415002TMsgMap.get("SOM_APVR_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.apvlActvTxt, (String) nwai415002TMsgMap.get("APVL_ACTV_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.wfStartUsrId, (String) nwai415002TMsgMap.get("WF_START_USR_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.wfEndUsrId, (String) nwai415002TMsgMap.get("WF_END_USR_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415002TMsg.apvlAddUsrId, (String) nwai415002TMsgMap.get("APVL_ADD_USR_ID"));

            msgList.add(nwai415002TMsg);
        }
        return msgList;
    }

    private List<NWAI4150_03TMsg> getNwai415003(BigDecimal transactionId, BigDecimal eopsQuoteId) {
        List<NWAI4150_03TMsg> msgList = new ArrayList<NWAI4150_03TMsg>();
        Map<String, Object> ssmParamNWAI415003 = new HashMap<String, Object>();
        ssmParamNWAI415003.put("interfaceId", this.interfaceId);
        ssmParamNWAI415003.put("transactionId", transactionId);
        ssmParamNWAI415003.put("somQuoteId", eopsQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai415003TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4150_03", ssmParamNWAI415003);

        for (Map<String, Object> nwai415003TMsgMap : nwai415003TMsgList) {
            NWAI4150_03TMsg nwai415003TMsg = new NWAI4150_03TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415003TMsg.interfaceId, (String) nwai415003TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415003TMsg.transactionId, (BigDecimal) nwai415003TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415003TMsg.segmentId, (BigDecimal) nwai415003TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415003TMsg.unitId, (BigDecimal) nwai415003TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415003TMsg.seqNumber, (BigDecimal) nwai415003TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai415003TMsg.somApvlNoteId, (BigDecimal) nwai415003TMsgMap.get("SOM_APVL_NOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415003TMsg.somApvlId, (BigDecimal) nwai415003TMsgMap.get("SOM_APVL_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415003TMsg.apvlAddTs, (String) nwai415003TMsgMap.get("APVL_ADD_TS"));
            ZYPEZDItemValueSetter.setValue(nwai415003TMsg.eopsApvlNoteTxt, (String) nwai415003TMsgMap.get("EOPS_APVL_NOTE_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415003TMsg.apvlAddUsrId, (String) nwai415003TMsgMap.get("APVL_ADD_USR_ID"));

            msgList.add(nwai415003TMsg);
        }
        return msgList;
    }

    private List<NWAI4150_04TMsg> getNwai415004(BigDecimal transactionId, BigDecimal somQuoteId) {
        List<NWAI4150_04TMsg> msgList = new ArrayList<NWAI4150_04TMsg>();
        Map<String, Object> ssmParamNWAI415004 = new HashMap<String, Object>();
        ssmParamNWAI415004.put("interfaceId", this.interfaceId);
        ssmParamNWAI415004.put("transactionId", transactionId);
        ssmParamNWAI415004.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai415004TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4150_04", ssmParamNWAI415004);

        for (Map<String, Object> nwai415004TMsgMap : nwai415004TMsgList) {
            NWAI4150_04TMsg nwai415004TMsg = new NWAI4150_04TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.interfaceId, (String) nwai415004TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.transactionId, (BigDecimal) nwai415004TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.segmentId, (BigDecimal) nwai415004TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.unitId, (BigDecimal) nwai415004TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.seqNumber, (BigDecimal) nwai415004TMsgMap.get("SEQ_NUMBER"));

            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somQuoteId, (BigDecimal) nwai415004TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somCmsnAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_CMSN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.svcCostAmt, (BigDecimal) nwai415004TMsgMap.get("SVC_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.svcCostPmtAmt, (BigDecimal) nwai415004TMsgMap.get("SVC_COST_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.svcCostTrnsfAmt, (BigDecimal) nwai415004TMsgMap.get("SVC_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somMkupAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_MKUP_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somPctMkupRate, (BigDecimal) nwai415004TMsgMap.get("SOM_PCT_MKUP_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.ordTotAmt, (BigDecimal) nwai415004TMsgMap.get("ORD_TOT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somTotFinAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_TOT_FIN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somEquipFinAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_EQUIP_FIN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somEquipPayAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_EQUIP_PAY_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somSvcFinAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_SVC_FIN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somSplyFinAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_SPLY_FIN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somUpgFinAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_UPG_FIN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somByotFinAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_BYOT_FIN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.miscNonTaxAbleAmt, (BigDecimal) nwai415004TMsgMap.get("MISC_NON_TAX_ABLE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somOthEquipAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_OTH_EQUIP_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.msrpListPrcAmt, (BigDecimal) nwai415004TMsgMap.get("MSRP_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somSoftPctCostRate, (BigDecimal) nwai415004TMsgMap.get("SOM_SOFT_PCT_COST_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somFinVsListRate, (BigDecimal) nwai415004TMsgMap.get("SOM_FIN_VS_LIST_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somTaxAbleAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_TAX_ABLE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.nonTaxAbleAmt, (BigDecimal) nwai415004TMsgMap.get("NON_TAX_ABLE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somTaxAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_TAX_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somCbsInvAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_CBS_INV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somFinalFlAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_FINAL_FL_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.gpCostTrnsfAmt, (BigDecimal) nwai415004TMsgMap.get("GP_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.gpCostPctTrnsfRate, (BigDecimal) nwai415004TMsgMap.get("GP_COST_PCT_TRNSF_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.totRepRevAmt, (BigDecimal) nwai415004TMsgMap.get("TOT_REP_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.sbscrSvcRepRevAmt, (BigDecimal) nwai415004TMsgMap.get("SBSCR_SVC_REP_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.repEquipRevAmt, (BigDecimal) nwai415004TMsgMap.get("REP_EQUIP_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.gpEquipAmt, (BigDecimal) nwai415004TMsgMap.get("GP_EQUIP_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.gpPctEquipRate, (BigDecimal) nwai415004TMsgMap.get("GP_PCT_EQUIP_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.extFlPrcAmt, (BigDecimal) nwai415004TMsgMap.get("EXT_FL_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somTotCostTrnsfAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_TOT_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somPrmoAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_PRMO_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somTradeInAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_TRADE_IN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.sbscrSvcAmt, (BigDecimal) nwai415004TMsgMap.get("SBSCR_SVC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.gpEquipRevAmt, (BigDecimal) nwai415004TMsgMap.get("GP_EQUIP_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.gpEquipEarnAmt, (BigDecimal) nwai415004TMsgMap.get("GP_EQUIP_EARN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.sbscrSvcRevAmt, (BigDecimal) nwai415004TMsgMap.get("SBSCR_SVC_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.sbscrSvcEarnAmt, (BigDecimal) nwai415004TMsgMap.get("SBSCR_SVC_EARN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.interRgRevAmt, (BigDecimal) nwai415004TMsgMap.get("INTER_RG_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.interRgEarnAmt, (BigDecimal) nwai415004TMsgMap.get("INTER_RG_EARN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.interTrtyRevAmt, (BigDecimal) nwai415004TMsgMap.get("INTER_TRTY_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.interTrtyEarnAmt, (BigDecimal) nwai415004TMsgMap.get("INTER_TRTY_EARN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.spiffRevAmt, (BigDecimal) nwai415004TMsgMap.get("SPIFF_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.spiffEarnAmt, (BigDecimal) nwai415004TMsgMap.get("SPIFF_EARN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.unitSoldRevAmt, (BigDecimal) nwai415004TMsgMap.get("UNIT_SOLD_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.unitSoldEarnAmt, (BigDecimal) nwai415004TMsgMap.get("UNIT_SOLD_EARN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somTotRevAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_TOT_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somTotEarnAmt, (BigDecimal) nwai415004TMsgMap.get("SOM_TOT_EARN_AMT"));

            //XXX there are no items below.
            //                        ZYPEZDItemValueSetter.setValue(nwai415004TMsg.gpCostTrnsfAmt, (BigDecimal) nwai415004TMsgMap.get(""));
            //                        ZYPEZDItemValueSetter.setValue(nwai415004TMsg.flAdjAmt, (BigDecimal) nwai415004TMsgMap.get(""));
            //                        ZYPEZDItemValueSetter.setValue(nwai415004TMsg.somSvcRevTrnsfAmt, (BigDecimal) nwai415004TMsgMap.get(""));
            //            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.proSvcAmt, (BigDecimal) nwai415004TMsgMap.get(""));
            //            ZYPEZDItemValueSetter.setValue(nwai415004TMsg.gpWotCostTrnsfRate, (BigDecimal) nwai415004TMsgMap.get(""));

            msgList.add(nwai415004TMsg);
        }
        return msgList;
    }

    private List<NWAI4150_05TMsg> getNwai415005(BigDecimal transactionId, BigDecimal eopsQuoteId) {
        List<NWAI4150_05TMsg> msgList = new ArrayList<NWAI4150_05TMsg>();
        Map<String, Object> ssmParamNWAI415005 = new HashMap<String, Object>();
        ssmParamNWAI415005.put("interfaceId", this.interfaceId);
        ssmParamNWAI415005.put("transactionId", transactionId);
        ssmParamNWAI415005.put("somQuoteId", eopsQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai415005TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4150_05", ssmParamNWAI415005);

        for (Map<String, Object> nwai415005TMsgMap : nwai415005TMsgList) {
            NWAI4150_05TMsg nwai415005TMsg = new NWAI4150_05TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.interfaceId, (String) nwai415005TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.transactionId, (BigDecimal) nwai415005TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.segmentId, (BigDecimal) nwai415005TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.unitId, (BigDecimal) nwai415005TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.seqNumber, (BigDecimal) nwai415005TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somQuoteId, (BigDecimal) nwai415005TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somSoftCostId, (BigDecimal) nwai415005TMsgMap.get("SOM_SOFT_COST_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somMercItemCd, (String) nwai415005TMsgMap.get("SOM_MERC_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somItemDescTxt, (String) nwai415005TMsgMap.get("SOM_ITEM_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somDescTxt, (String) nwai415005TMsgMap.get("SOM_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somOrdQty, (BigDecimal) nwai415005TMsgMap.get("SOM_ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somMdlTxt, (String) nwai415005TMsgMap.get("SOM_MDL_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.quoteLineTpTxt, (String) nwai415005TMsgMap.get("QUOTE_LINE_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somPrcAmt, (BigDecimal) nwai415005TMsgMap.get("SOM_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somDescSerNum, (String) nwai415005TMsgMap.get("SOM_DESC_SER_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.mtrReadCnt, (BigDecimal) nwai415005TMsgMap.get("MTR_READ_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.rtrnTpTxt, (String) nwai415005TMsgMap.get("RTRN_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.rtrnIstlTxt, (String) nwai415005TMsgMap.get("RTRN_ISTL_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.dtlDescTxt, (String) nwai415005TMsgMap.get("DTL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.cfsIndSomTxt, (String) nwai415005TMsgMap.get("CFS_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.nonCfsLeaseCmpyTxt, (String) nwai415005TMsgMap.get("NON_CFS_LEASE_CMPY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somPsnTxt, (String) nwai415005TMsgMap.get("SOM_PSN_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somCrmSlsTxt, (String) nwai415005TMsgMap.get("SOM_CRM_SLS_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somAcctNum, (String) nwai415005TMsgMap.get("SOM_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.eopsSoftCostAddr_01, (String) nwai415005TMsgMap.get("EOPS_SOFT_COST_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.eopsSoftCostAddr_02, (String) nwai415005TMsgMap.get("EOPS_SOFT_COST_ADDR_02"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.eopsSoftCostCityTxt, (String) nwai415005TMsgMap.get("SOM_SOFT_COST_CITY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somSoftCostStCd, (String) nwai415005TMsgMap.get("SOM_SOFT_COST_ST_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somSoftCostCntyNm, (String) nwai415005TMsgMap.get("SOM_SOFT_COST_CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somSoftCostZipCd, (String) nwai415005TMsgMap.get("SOM_SOFT_COST_ZIP_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somStepCnt, (BigDecimal) nwai415005TMsgMap.get("SOM_STEP_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somElevCnt, (BigDecimal) nwai415005TMsgMap.get("SOM_ELEV_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.shipViaTxt, (String) nwai415005TMsgMap.get("SHIP_VIA_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.hrsOpTxt, (String) nwai415005TMsgMap.get("HRS_OP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.loadDockCnt, (BigDecimal) nwai415005TMsgMap.get("LOAD_DOCK_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.eopsSoftCostInstnTxt, (String) nwai415005TMsgMap.get("EOPS_SOFT_COST_INSTN_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.erlstDelyTs, (String) nwai415005TMsgMap.get("ERLST_DELY_TS"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.primEopsCtacFirstNm, (String) nwai415005TMsgMap.get("PRIM_EOPS_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.primEopsCtacLastNm, (String) nwai415005TMsgMap.get("PRIM_EOPS_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.primCtacPhoTxt, (String) nwai415005TMsgMap.get("PRIM_CTAC_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.primPhoExtTxt, (String) nwai415005TMsgMap.get("PRIM_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.eopsPrimEmlTxt, (String) nwai415005TMsgMap.get("EOPS_PRIM_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.scdEopsCtacFirstNm, (String) nwai415005TMsgMap.get("SCD_EOPS_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.scdEopsCtacLastNm, (String) nwai415005TMsgMap.get("SCD_EOPS_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.scdCtacPhoTxt, (String) nwai415005TMsgMap.get("SCD_CTAC_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.scdPhoExtTxt, (String) nwai415005TMsgMap.get("SCD_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.scdCtacEmlTxt, (String) nwai415005TMsgMap.get("SCD_CTAC_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.eopsIstlRepTxt, (String) nwai415005TMsgMap.get("EOPS_ISTL_REP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.eopsIstlBrTxt, (String) nwai415005TMsgMap.get("EOPS_ISTL_BR_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.eopsBrDrctrTxt, (String) nwai415005TMsgMap.get("EOPS_BR_DRCTR_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somTaxRate, (BigDecimal) nwai415005TMsgMap.get("SOM_TAX_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somSoftCostDlrTxt, (String) nwai415005TMsgMap.get("SOM_SOFT_COST_DLR_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.mtrMethTxt, (String) nwai415005TMsgMap.get("MTR_METH_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.splitTpTxt, (String) nwai415005TMsgMap.get("SPLIT_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somByotNm, (String) nwai415005TMsgMap.get("SOM_BYOT_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somMnfTxt, (String) nwai415005TMsgMap.get("SOM_MNF_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.somExtPrcAmt, (BigDecimal) nwai415005TMsgMap.get("SOM_EXT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.eopsByotFirstNm, (String) nwai415005TMsgMap.get("EOPS_BYOT_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.eopsByotLastNm, (String) nwai415005TMsgMap.get("EOPS_BYOT_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.eopsByotPhoTxt, (String) nwai415005TMsgMap.get("EOPS_BYOT_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.eopsByotPhoExtTxt, (String) nwai415005TMsgMap.get("EOPS_BYOT_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.eopsByotAddr_01, (String) nwai415005TMsgMap.get("EOPS_BYOT_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.eopsByotAddr_02, (String) nwai415005TMsgMap.get("EOPS_BYOT_ADDR_02"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.eopsByotCityTxt, (String) nwai415005TMsgMap.get("EOPS_BYOT_CITY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.eopsByotStCd, (String) nwai415005TMsgMap.get("EOPS_BYOT_ST_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415005TMsg.eopsByotZipCd, (String) nwai415005TMsgMap.get("EOPS_BYOT_ZIP_CD"));

            msgList.add(nwai415005TMsg);
        }
        return msgList;
    }

    private List<NWAI4150_06TMsg> getNwai415006(BigDecimal transactionId, BigDecimal eopsQuoteId) {
        List<NWAI4150_06TMsg> msgList = new ArrayList<NWAI4150_06TMsg>();
        Map<String, Object> ssmParamNWAI415006 = new HashMap<String, Object>();
        ssmParamNWAI415006.put("interfaceId", this.interfaceId);
        ssmParamNWAI415006.put("transactionId", transactionId);
        ssmParamNWAI415006.put("somQuoteId", eopsQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai415006TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4150_06", ssmParamNWAI415006);

        for (Map<String, Object> nwai415006TMsgMap : nwai415006TMsgList) {
            NWAI4150_06TMsg nwai415006TMsg = new NWAI4150_06TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.interfaceId, (String) nwai415006TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.transactionId, (BigDecimal) nwai415006TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.segmentId, (BigDecimal) nwai415006TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.unitId, (BigDecimal) nwai415006TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.seqNumber, (BigDecimal) nwai415006TMsgMap.get("SEQ_NUMBER"));

            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.somQuoteId, (BigDecimal) nwai415006TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.somSoftCostItemId, (BigDecimal) nwai415006TMsgMap.get("SOM_SOFT_COST_ITEM_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.rtrnCmpyId, (BigDecimal) nwai415006TMsgMap.get("RTRN_CMPY_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.rtrnOthTxt, (String) nwai415006TMsgMap.get("RTRN_OTH_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.origOrdTs, (String) nwai415006TMsgMap.get("ORIG_ORD_TS"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.pickUpDtSameTxt, (String) nwai415006TMsgMap.get("PICK_UP_DT_SAME_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.pickUpDtOthTxt, (String) nwai415006TMsgMap.get("PICK_UP_DT_OTH_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.pickUpDtOthTs, (String) nwai415006TMsgMap.get("PICK_UP_DT_OTH_TS"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.pickUpFirstNm, (String) nwai415006TMsgMap.get("PICK_UP_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.pickUpLastNm, (String) nwai415006TMsgMap.get("PICK_UP_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.pickUpCtacPhoTxt, (String) nwai415006TMsgMap.get("PICK_UP_CTAC_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.pickUpPhoExtTxt, (String) nwai415006TMsgMap.get("PICK_UP_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.pickUpCtacEmlTxt, (String) nwai415006TMsgMap.get("PICK_UP_CTAC_EML_TXT"));

            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.rmvInstnTxt, (String) nwai415006TMsgMap.get("RMV_INSTN_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.pickUpEopsAddr_01, (String) nwai415006TMsgMap.get("PICK_UP_EOPS_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.pickUpEopsAddr_02, (String) nwai415006TMsgMap.get("PICK_UP_EOPS_ADDR_02"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.pickUpEopsCityAddr, (String) nwai415006TMsgMap.get("PICK_UP_EOPS_CITY_ADDR"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.pickUpStCd, (String) nwai415006TMsgMap.get("PICK_UP_ST_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.pickUpCntyNm, (String) nwai415006TMsgMap.get("PICK_UP_CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.pickUpZipCd, (String) nwai415006TMsgMap.get("PICK_UP_ZIP_CD"));

            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.somDescSerNum, (String) nwai415006TMsgMap.get("SOM_DESC_SER_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.somPsnDescTxt, (String) nwai415006TMsgMap.get("SOM_PSN_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.somTpDescTxt, (String) nwai415006TMsgMap.get("SOM_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.somMdlTxt, (String) nwai415006TMsgMap.get("SOM_MDL_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.somLeaseNum, (String) nwai415006TMsgMap.get("SOM_LEASE_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.mtrReadCnt, (BigDecimal) nwai415006TMsgMap.get("MTR_READ_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415006TMsg.somLeaseCompTxt, (String) nwai415006TMsgMap.get("SOM_LEASE_COMP_TXT"));

            msgList.add(nwai415006TMsg);
        }
        return msgList;
    }

    private List<NWAI4150_08TMsg> getNwai415008(BigDecimal transactionId, BigDecimal somQuoteId) {
        List<NWAI4150_08TMsg> msgList = new ArrayList<NWAI4150_08TMsg>();
        Map<String, Object> ssmParamNWAI415008 = new HashMap<String, Object>();
        ssmParamNWAI415008.put("interfaceId", this.interfaceId);
        ssmParamNWAI415008.put("transactionId", transactionId);
        ssmParamNWAI415008.put("somQuoteId", somQuoteId);

        List<Map<String, Object>> nwai415008TMsgList = NWXC412001.autoCast(this.ssmBatchClient.queryObjectList("getNWAI4150_08", ssmParamNWAI415008));

        for (Map<String, Object> nwai415008TMsgMap : nwai415008TMsgList) {
            NWAI4150_08TMsg nwai415008TMsg = new NWAI4150_08TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415008TMsg.interfaceId, (String) nwai415008TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415008TMsg.transactionId, (BigDecimal) nwai415008TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415008TMsg.segmentId, (BigDecimal) nwai415008TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415008TMsg.unitId, (BigDecimal) nwai415008TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415008TMsg.seqNumber, (BigDecimal) nwai415008TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai415008TMsg.somDtlId, (BigDecimal) nwai415008TMsgMap.get("SOM_DTL_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415008TMsg.somQuoteId, (BigDecimal) nwai415008TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415008TMsg.spiffDtlAmt, (BigDecimal) nwai415008TMsgMap.get("SPIFF_DTL_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415008TMsg.somItemCd, (String) nwai415008TMsgMap.get("SOM_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415008TMsg.somItemTxt, (String) nwai415008TMsgMap.get("SOM_ITEM_TXT"));

            msgList.add(nwai415008TMsg);
        }
        return msgList;
    }

    private List<NWAI4150_10TMsg> getNwai415010(BigDecimal transactionId, BigDecimal somQuoteId) {
        List<NWAI4150_10TMsg> msgList = new ArrayList<NWAI4150_10TMsg>();
        Map<String, Object> ssmParamNWAI415010 = new HashMap<String, Object>();
        ssmParamNWAI415010.put("interfaceId", this.interfaceId);
        ssmParamNWAI415010.put("transactionId", transactionId);
        ssmParamNWAI415010.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai415010TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4150_10", ssmParamNWAI415010);

        for (Map<String, Object> nwai415010TMsgMap : nwai415010TMsgList) {
            NWAI4150_10TMsg nwai415010TMsg = new NWAI4150_10TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.interfaceId, (String) nwai415010TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.transactionId, (BigDecimal) nwai415010TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.segmentId, (BigDecimal) nwai415010TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.unitId, (BigDecimal) nwai415010TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.seqNumber, (BigDecimal) nwai415010TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.somQuoteId, (BigDecimal) nwai415010TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.somConfigId, (BigDecimal) nwai415010TMsgMap.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.somMercCd, (String) nwai415010TMsgMap.get("SOM_MERC_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.eopsSortOrdNum, (BigDecimal) nwai415010TMsgMap.get("EOPS_SORT_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.somMercNm, (String) nwai415010TMsgMap.get("SOM_MERC_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.somMdlDescTxt, (String) nwai415010TMsgMap.get("SOM_MDL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.somOrdQty, (BigDecimal) nwai415010TMsgMap.get("SOM_ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.somConfigQty, (BigDecimal) nwai415010TMsgMap.get("SOM_CONFIG_QTY"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.usedDemoId, (BigDecimal) nwai415010TMsgMap.get("USED_DEMO_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.flPrcAmt, (BigDecimal) nwai415010TMsgMap.get("FL_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.prfPrcAmt, (BigDecimal) nwai415010TMsgMap.get("PRF_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.msrpListPrcAmt, (BigDecimal) nwai415010TMsgMap.get("MSRP_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.sellPrcListAmt, (BigDecimal) nwai415010TMsgMap.get("SELL_PRC_LIST_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.extSellPrcAmt, (BigDecimal) nwai415010TMsgMap.get("EXT_SELL_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.somMdseTpTxt, (String) nwai415010TMsgMap.get("SOM_MDSE_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.ltstPrcAmt, (BigDecimal) nwai415010TMsgMap.get("LTST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.somSegTxt, (String) nwai415010TMsgMap.get("SOM_SEG_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.engnrBdlCnt, (BigDecimal) nwai415010TMsgMap.get("ENGNR_BDL_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.eopsNonCoprTxt, (String) nwai415010TMsgMap.get("EOPS_NON_COPR_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.extFlPrcAmt, (BigDecimal) nwai415010TMsgMap.get("EXT_FL_PRC_AMT"));

            //XXX
            //            ZYPEZDItemValueSetter.setValue(nwai415010TMsg.somDescSerNum, (String) nwai415010TMsgMap.get(""));

            msgList.add(nwai415010TMsg);
        }
        return msgList;
    }

    private List<NWAI4150_12TMsg> getNwai415012(BigDecimal transactionId, BigDecimal somQuoteId) {
        List<NWAI4150_12TMsg> msgList = new ArrayList<NWAI4150_12TMsg>();
        Map<String, Object> ssmParamNWAI415012 = new HashMap<String, Object>();
        ssmParamNWAI415012.put("interfaceId", this.interfaceId);
        ssmParamNWAI415012.put("transactionId", transactionId);
        ssmParamNWAI415012.put("somQuoteId", somQuoteId);

        List<Map<String, Object>> nwai415012TMsgList //
        = NWXC412001.autoCast(this.ssmBatchClient.queryObjectList("getNWAI4150_12", ssmParamNWAI415012));

        for (Map<String, Object> nwai415012TMsgMap : nwai415012TMsgList) {
            NWAI4150_12TMsg nwai415012TMsg = new NWAI4150_12TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.interfaceId, (String) nwai415012TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.transactionId, (BigDecimal) nwai415012TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.segmentId, (BigDecimal) nwai415012TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.unitId, (BigDecimal) nwai415012TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.seqNumber, (BigDecimal) nwai415012TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somQuoteId, (BigDecimal) nwai415012TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somConfigId, (BigDecimal) nwai415012TMsgMap.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.eopsConfigLtrTxt, (String) nwai415012TMsgMap.get("EOPS_CONFIG_LTR_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somOrdQty, (BigDecimal) nwai415012TMsgMap.get("SOM_ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somPsnCnt, (BigDecimal) nwai415012TMsgMap.get("SOM_PSN_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somStepCnt, (BigDecimal) nwai415012TMsgMap.get("SOM_STEP_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.flPrcListId, (BigDecimal) nwai415012TMsgMap.get("FL_PRC_LIST_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.bidPrcListId, (BigDecimal) nwai415012TMsgMap.get("BID_PRC_LIST_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.lastUpdTs, (String) nwai415012TMsgMap.get("LAST_UPD_TS"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.erlstDelyTs, (String) nwai415012TMsgMap.get("ERLST_DELY_TS"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somElevCnt, (BigDecimal) nwai415012TMsgMap.get("SOM_ELEV_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.loadDockCnt, (BigDecimal) nwai415012TMsgMap.get("LOAD_DOCK_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somTaxRate, (BigDecimal) nwai415012TMsgMap.get("SOM_TAX_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somCrmSlsTxt, (String) nwai415012TMsgMap.get("SOM_CRM_SLS_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somAcctNum, (String) nwai415012TMsgMap.get("SOM_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somShipToAddr_01, (String) nwai415012TMsgMap.get("SOM_SHIP_TO_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somShipToAddr_02, (String) nwai415012TMsgMap.get("SOM_SHIP_TO_ADDR_02"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somCityAddr, (String) nwai415012TMsgMap.get("SOM_CITY_ADDR"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somStCd, (String) nwai415012TMsgMap.get("SOM_ST_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.csaPmtTpTxt, (String) nwai415012TMsgMap.get("CSA_PMT_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.bidPrcListNm, (String) nwai415012TMsgMap.get("BID_PRC_LIST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.csaPmtCtacFirstNm, (String) nwai415012TMsgMap.get("CSA_PMT_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.csaPmtCtacLastNm, (String) nwai415012TMsgMap.get("CSA_PMT_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.csaPmtCtacPhoTxt, (String) nwai415012TMsgMap.get("CSA_PMT_CTAC_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.csaPmtPhoExtDescTxt, (String) nwai415012TMsgMap.get("CSA_PMT_PHO_EXT_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.csaPmtCtacEmlTxt, (String) nwai415012TMsgMap.get("CSA_PMT_CTAC_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somDlrTxt, (String) nwai415012TMsgMap.get("SOM_DLR_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.mtrMethTxt, (String) nwai415012TMsgMap.get("MTR_METH_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.splitTpTxt, (String) nwai415012TMsgMap.get("SPLIT_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.addrLbTxt, (String) nwai415012TMsgMap.get("ADDR_LB_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.prcBookNm, (String) nwai415012TMsgMap.get("PRC_BOOK_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somFlPrcListNm, (String) nwai415012TMsgMap.get("SOM_FL_PRC_LIST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.scdCtacPhoTxt, (String) nwai415012TMsgMap.get("SCD_CTAC_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.scdPhoExtTxt, (String) nwai415012TMsgMap.get("SCD_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.scdCtacEmlTxt, (String) nwai415012TMsgMap.get("SCD_CTAC_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.istlRepDescTxt, (String) nwai415012TMsgMap.get("ISTL_REP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.istlBrDescTxt, (String) nwai415012TMsgMap.get("ISTL_BR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.brDrctrDescTxt, (String) nwai415012TMsgMap.get("BR_DRCTR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.primEopsCtacLastNm, (String) nwai415012TMsgMap.get("PRIM_EOPS_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.primCtacPhoTxt, (String) nwai415012TMsgMap.get("PRIM_CTAC_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.primPhoExtTxt, (String) nwai415012TMsgMap.get("PRIM_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.primCtacEmlTxt, (String) nwai415012TMsgMap.get("PRIM_CTAC_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.scdEopsCtacFirstNm, (String) nwai415012TMsgMap.get("SCD_EOPS_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.scdEopsCtacLastNm, (String) nwai415012TMsgMap.get("SCD_EOPS_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somCntyNm, (String) nwai415012TMsgMap.get("SOM_CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somZipCd, (String) nwai415012TMsgMap.get("SOM_ZIP_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.somShipViaTxt, (String) nwai415012TMsgMap.get("SOM_SHIP_VIA_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.hrsOpTxt, (String) nwai415012TMsgMap.get("HRS_OP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.eopsInstnTxt, (String) nwai415012TMsgMap.get("EOPS_INSTN_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415012TMsg.primEopsCtacFirstNm, (String) nwai415012TMsgMap.get("PRIM_EOPS_CTAC_FIRST_NM"));

            msgList.add(nwai415012TMsg);
        }
        return msgList;
    }

    private List<NWAI4150_13TMsg> getNwai415013(BigDecimal transactionId, BigDecimal somQuoteId) {
        List<NWAI4150_13TMsg> msgList = new ArrayList<NWAI4150_13TMsg>();
        Map<String, Object> ssmParamNWAI415013 = new HashMap<String, Object>();
        ssmParamNWAI415013.put("interfaceId", this.interfaceId);
        ssmParamNWAI415013.put("transactionId", transactionId);
        ssmParamNWAI415013.put("somQuoteId", somQuoteId);

        List<Map<String, Object>> nwai415013TMsgList //
        = NWXC412001.autoCast(this.ssmBatchClient.queryObjectList("getNWAI4150_13", ssmParamNWAI415013));

        for (Map<String, Object> nwai415013TMsgMap : nwai415013TMsgList) {
            NWAI4150_13TMsg nwai415013TMsg = new NWAI4150_13TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415013TMsg.interfaceId, (String) nwai415013TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415013TMsg.transactionId, (BigDecimal) nwai415013TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415013TMsg.segmentId, (BigDecimal) nwai415013TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415013TMsg.unitId, (BigDecimal) nwai415013TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415013TMsg.seqNumber, (BigDecimal) nwai415013TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai415013TMsg.somQuoteId, (BigDecimal) nwai415013TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415013TMsg.somConfigId, (BigDecimal) nwai415013TMsgMap.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415013TMsg.somItemCd, (String) nwai415013TMsgMap.get("SOM_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415013TMsg.somSerNum, (String) nwai415013TMsgMap.get("SOM_SER_NUM"));

            msgList.add(nwai415013TMsg);
        }
        return msgList;
    }

    private List<NWAI4150_14TMsg> getNwai415014(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4150_14TMsg> msgList = new ArrayList<NWAI4150_14TMsg>();
        Map<String, Object> ssmParamNWAI415014 = new HashMap<String, Object>();
        ssmParamNWAI415014.put("interfaceId", this.interfaceId);
        ssmParamNWAI415014.put("transactionId", transactionId);
        ssmParamNWAI415014.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai415014TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4150_14", ssmParamNWAI415014);

        for (Map<String, Object> nwai415014TMsgMap : nwai415014TMsgList) {
            NWAI4150_14TMsg nwai415014TMsg = new NWAI4150_14TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.interfaceId, (String) nwai415014TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.transactionId, (BigDecimal) nwai415014TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.segmentId, (BigDecimal) nwai415014TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.unitId, (BigDecimal) nwai415014TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.seqNumber, (BigDecimal) nwai415014TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.rtrnCmpyId, (BigDecimal) nwai415014TMsgMap.get("RTRN_CMPY_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.somQuoteId, (BigDecimal) nwai415014TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.somConfigId, (BigDecimal) nwai415014TMsgMap.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.equipCondId, (BigDecimal) nwai415014TMsgMap.get("EQUIP_COND_ID"));
            //            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.ackDescId, (BigDecimal) nwai415014TMsgMap.get(""));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.somPrmoAmt, (BigDecimal) nwai415014TMsgMap.get("SOM_PRMO_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.origOrdTs, (String) nwai415014TMsgMap.get("ORIG_ORD_TS"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.pickUpDtOthDescTxt, (String) nwai415014TMsgMap.get("PICK_UP_DT_OTH_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.pickUpDtSameCnt, (BigDecimal) nwai415014TMsgMap.get("PICK_UP_DT_SAME_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.pickUpDtOthCnt, (BigDecimal) nwai415014TMsgMap.get("PICK_UP_DT_OTH_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.somPrmoSerNum, (String) nwai415014TMsgMap.get("SOM_PRMO_SER_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.somPrmoCd, (String) nwai415014TMsgMap.get("SOM_PRMO_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.somPrmoMdlNm, (String) nwai415014TMsgMap.get("SOM_PRMO_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.rtrnOthTxt, (String) nwai415014TMsgMap.get("RTRN_OTH_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.pickUpCtacFirstNm, (String) nwai415014TMsgMap.get("PICK_UP_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.pickUpCtacLastNm, (String) nwai415014TMsgMap.get("PICK_UP_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.somPrmoCityTxt, (String) nwai415014TMsgMap.get("SOM_PRMO_CITY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.somPrmoStCd, (String) nwai415014TMsgMap.get("SOM_PRMO_ST_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.somPrmoCntyNm, (String) nwai415014TMsgMap.get("SOM_PRMO_CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.somPrmoZipCd, (String) nwai415014TMsgMap.get("SOM_PRMO_ZIP_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.pickUpPhoTxt, (String) nwai415014TMsgMap.get("PICK_UP_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.pickUpPhoExtTxt, (String) nwai415014TMsgMap.get("PICK_UP_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.pickUpEmlTxt, (String) nwai415014TMsgMap.get("PICK_UP_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.somRmvInstnTxt, (String) nwai415014TMsgMap.get("SOM_RMV_INSTN_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.somPrmoAddr_01, (String) nwai415014TMsgMap.get("SOM_PRMO_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(nwai415014TMsg.somPrmoAddr_02, (String) nwai415014TMsgMap.get("SOM_PRMO_ADDR_02"));

            msgList.add(nwai415014TMsg);
        }
        return msgList;
    }

    private List<NWAI4150_16TMsg> getNwai415016(BigDecimal transactionId, BigDecimal somQuoteId) {
        List<NWAI4150_16TMsg> msgList = new ArrayList<NWAI4150_16TMsg>();
        Map<String, Object> ssmParamNWAI415016 = new HashMap<String, Object>();
        ssmParamNWAI415016.put("interfaceId", this.interfaceId);
        ssmParamNWAI415016.put("transactionId", transactionId);
        ssmParamNWAI415016.put("somQuoteId", somQuoteId);

        List<Map<String, Object>> nwai415016TMsgList = NWXC412001.autoCast(this.ssmBatchClient.queryObjectList("getNWAI4150_16", ssmParamNWAI415016));

        for (Map<String, Object> nwai415016TMsgMap : nwai415016TMsgList) {
            NWAI4150_16TMsg nwai415016TMsg = new NWAI4150_16TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.interfaceId, (String) nwai415016TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.transactionId, (BigDecimal) nwai415016TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.segmentId, (BigDecimal) nwai415016TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.unitId, (BigDecimal) nwai415016TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.seqNumber, (BigDecimal) nwai415016TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.somSrvId, (BigDecimal) nwai415016TMsgMap.get("SOM_SRV_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.somQuoteId, (BigDecimal) nwai415016TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.fleetTermNum, (BigDecimal) nwai415016TMsgMap.get("FLEET_TERM_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.fleetVolClrCnt, (BigDecimal) nwai415016TMsgMap.get("FLEET_VOL_CLR_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.fleetVolBwCnt, (BigDecimal) nwai415016TMsgMap.get("FLEET_VOL_BW_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.svcCostId, (BigDecimal) nwai415016TMsgMap.get("SVC_COST_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.svcCostTrnsfAmt, (BigDecimal) nwai415016TMsgMap.get("SVC_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.clrOvagCostTrnsfAmt, (BigDecimal) nwai415016TMsgMap.get("CLR_OVAG_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.bwOvagCostTrnsfAmt, (BigDecimal) nwai415016TMsgMap.get("BW_OVAG_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.finSvcCostAmt, (BigDecimal) nwai415016TMsgMap.get("FIN_SVC_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.totSvcCostAmt, (BigDecimal) nwai415016TMsgMap.get("TOT_SVC_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.totSvcCostTrnsfAmt, (BigDecimal) nwai415016TMsgMap.get("TOT_SVC_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.totSvcRevTrnsfAmt, (BigDecimal) nwai415016TMsgMap.get("TOT_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.fleetBwReqBaseAmt, (BigDecimal) nwai415016TMsgMap.get("FLEET_BW_REQ_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.fleetClrReqBaseAmt, (BigDecimal) nwai415016TMsgMap.get("FLEET_CLR_REQ_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.addlPrmoId, (BigDecimal) nwai415016TMsgMap.get("ADDL_PRMO_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.fleetClrCpcCnt, (BigDecimal) nwai415016TMsgMap.get("FLEET_CLR_CPC_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.fleetBwCpcCnt, (BigDecimal) nwai415016TMsgMap.get("FLEET_BW_CPC_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.fleetPlanDescTxt, (String) nwai415016TMsgMap.get("FLEET_PLAN_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.fleetBillByTxt, (String) nwai415016TMsgMap.get("FLEET_BILL_BY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.fleetBllgCycleTxt, (String) nwai415016TMsgMap.get("FLEET_BLLG_CYCLE_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.contrTpTxt, (String) nwai415016TMsgMap.get("CONTR_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.svcCostTrnsfTxt, (String) nwai415016TMsgMap.get("SVC_COST_TRNSF_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.svcCostApvlTxt, (String) nwai415016TMsgMap.get("SVC_COST_APVL_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.stplIndSomTxt, (String) nwai415016TMsgMap.get("STPL_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.ctWavIndSomTxt, (String) nwai415016TMsgMap.get("CT_WAV_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.contrIndSomTxt, (String) nwai415016TMsgMap.get("CONTR_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.somContrNum, (String) nwai415016TMsgMap.get("SOM_CONTR_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.trnsfOvrdIndSomTxt, (String) nwai415016TMsgMap.get("TRNSF_OVRD_IND_SOM_TXT"));
            //            ZYPEZDItemValueSetter.setValue(nwai415016TMsg.somMaintPoDt, (String) nwai415016TMsgMap.get("SOM_MAINT_PO_DT"));

            msgList.add(nwai415016TMsg);
        }
        return msgList;
    }

    private List<NWAI4150_18TMsg> getNwai415018(BigDecimal transactionId, BigDecimal somQuoteId) {
        List<NWAI4150_18TMsg> msgList = new ArrayList<NWAI4150_18TMsg>();
        Map<String, Object> ssmParamNWAI415018 = new HashMap<String, Object>();
        ssmParamNWAI415018.put("interfaceId", this.interfaceId);
        ssmParamNWAI415018.put("transactionId", transactionId);
        ssmParamNWAI415018.put("somQuoteId", somQuoteId);

        List<Map<String, Object>> nwai415018TMsgList //
        = NWXC412001.autoCast(this.ssmBatchClient.queryObjectList("getNWAI4150_18", ssmParamNWAI415018));

        for (Map<String, Object> nwai415018TMsgMap : nwai415018TMsgList) {
            NWAI4150_18TMsg nwai415018TMsg = new NWAI4150_18TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.interfaceId, (String) nwai415018TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.transactionId, (BigDecimal) nwai415018TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.segmentId, (BigDecimal) nwai415018TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.unitId, (BigDecimal) nwai415018TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.seqNumber, (BigDecimal) nwai415018TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somSrvId, (BigDecimal) nwai415018TMsgMap.get("SOM_SRV_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somSrvLineId, (BigDecimal) nwai415018TMsgMap.get("SOM_SRV_LINE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somSrvTermId, (BigDecimal) nwai415018TMsgMap.get("SOM_SRV_TERM_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somSrvUnitId, (BigDecimal) nwai415018TMsgMap.get("SOM_SRV_UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somVolMthSrvCnt, (BigDecimal) nwai415018TMsgMap.get("SOM_VOL_MTH_SRV_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somVolTotSrvCnt, (BigDecimal) nwai415018TMsgMap.get("SOM_VOL_TOT_SRV_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somQuoteId, (BigDecimal) nwai415018TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somSrvVolComitTxt, (String) nwai415018TMsgMap.get("SOM_SRV_VOL_COMIT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.baseClickReqBaseAmt, (BigDecimal) nwai415018TMsgMap.get("BASE_CLICK_REQ_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.svcPrmoId, (BigDecimal) nwai415018TMsgMap.get("SVC_PRMO_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.bandMaxCnt, (BigDecimal) nwai415018TMsgMap.get("BAND_MAX_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.termMinCnt, (BigDecimal) nwai415018TMsgMap.get("TERM_MIN_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.termMaxCnt, (BigDecimal) nwai415018TMsgMap.get("TERM_MAX_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.reqBaseTxt, (String) nwai415018TMsgMap.get("REQ_BASE_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.costTrnsfAmt, (BigDecimal) nwai415018TMsgMap.get("COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.ovagCostTrnsfAmt, (BigDecimal) nwai415018TMsgMap.get("OVAG_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.totCostTrnsfAmt, (BigDecimal) nwai415018TMsgMap.get("TOT_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.baseClickPubBaseAmt, (BigDecimal) nwai415018TMsgMap.get("BASE_CLICK_PUB_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.baseClickCostTrnsfAmt, (BigDecimal) nwai415018TMsgMap.get("BASE_CLICK_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.baseSvcRevTrnsfAmt, (BigDecimal) nwai415018TMsgMap.get("BASE_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.ovagSvcRevTrnsfAmt, (BigDecimal) nwai415018TMsgMap.get("OVAG_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.totSvcRevTrnsfAmt, (BigDecimal) nwai415018TMsgMap.get("TOT_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.origCostTrnsfAmt, (BigDecimal) nwai415018TMsgMap.get("ORIG_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.origSvcRevTrnsfAmt, (BigDecimal) nwai415018TMsgMap.get("ORIG_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.reqCpcTxt, (String) nwai415018TMsgMap.get("REQ_CPC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.pubBaseTxt, (String) nwai415018TMsgMap.get("PUB_BASE_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.pubCpcTxt, (String) nwai415018TMsgMap.get("PUB_CPC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somMultCnt, (BigDecimal) nwai415018TMsgMap.get("SOM_MULT_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.bandMinCnt, (BigDecimal) nwai415018TMsgMap.get("BAND_MIN_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.contrOptTxt, (String) nwai415018TMsgMap.get("CONTR_OPT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.prmoBtIndSomTxt, (String) nwai415018TMsgMap.get("PRMO_BT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.isClrBtIndSomTxt, (String) nwai415018TMsgMap.get("IS_CLR_BT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.eopsPlanDescTxt, (String) nwai415018TMsgMap.get("EOPS_PLAN_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somBllgCycleDescTxt, (String) nwai415018TMsgMap.get("SOM_BLLG_CYCLE_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.usgCycleDescTxt, (String) nwai415018TMsgMap.get("USG_CYCLE_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.svcContrIndSomTxt, (String) nwai415018TMsgMap.get("SVC_CONTR_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.eopsPkgNm, (String) nwai415018TMsgMap.get("EOPS_PKG_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.eopsSrvMtrTpNm, (String) nwai415018TMsgMap.get("EOPS_SRV_MTR_TP_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somSrvCntrNm, (String) nwai415018TMsgMap.get("SOM_SRV_CNTR_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.svcClickTpTxt, (String) nwai415018TMsgMap.get("SVC_CLICK_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.clickTpTxt, (String) nwai415018TMsgMap.get("CLICK_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.eopsPrcBookDescTxt, (String) nwai415018TMsgMap.get("EOPS_PRC_BOOK_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somBandDescTxt, (String) nwai415018TMsgMap.get("SOM_BAND_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.orclMdlNm, (String) nwai415018TMsgMap.get("ORCL_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somSvcItemCd, (String) nwai415018TMsgMap.get("SOM_SVC_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somSrvPrmoCd, (String) nwai415018TMsgMap.get("SOM_SRV_PRMO_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somSrvPrmoNm, (String) nwai415018TMsgMap.get("SOM_SRV_PRMO_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.svcPrmoTxt, (String) nwai415018TMsgMap.get("SVC_PRMO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.dblClickIndSomTxt, (String) nwai415018TMsgMap.get("DBL_CLICK_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.rateIndSomTxt, (String) nwai415018TMsgMap.get("RATE_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.eopsPrcBookTxt, (String) nwai415018TMsgMap.get("EOPS_PRC_BOOK_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.withRmtIndSomTxt, (String) nwai415018TMsgMap.get("WITH_RMT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somBandCd, (String) nwai415018TMsgMap.get("SOM_BAND_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.dclnMaintIndSomTxt, (String) nwai415018TMsgMap.get("DCLN_MAINT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.mtrReadMethTxt, (String) nwai415018TMsgMap.get("MTR_READ_METH_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.prmoIndSomTxt, (String) nwai415018TMsgMap.get("PRMO_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.isPrmoIndSomTxt, (String) nwai415018TMsgMap.get("IS_PRMO_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.eopsContrTpDescTxt, (String) nwai415018TMsgMap.get("EOPS_CONTR_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somMdlId, (String) nwai415018TMsgMap.get("SOM_MDL_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somMdlGrpId, (String) nwai415018TMsgMap.get("SOM_MDL_GRP_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.somMdlDescTxt, (String) nwai415018TMsgMap.get("SOM_MDL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.isFleetIndSomTxt, (String) nwai415018TMsgMap.get("IS_FLEET_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.isBaseClickIndSomTxt, (String) nwai415018TMsgMap.get("IS_BASE_CLICK_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415018TMsg.prcCatgCd, (String) nwai415018TMsgMap.get("PRC_CATG_CD"));

            msgList.add(nwai415018TMsg);
        }
        return msgList;
    }

    private List<NWAI4150_19TMsg> getNwai415019(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4150_19TMsg> msgList = new ArrayList<NWAI4150_19TMsg>();
        Map<String, Object> ssmParamNWAI415019 = new HashMap<String, Object>();
        ssmParamNWAI415019.put("interfaceId", this.interfaceId);
        ssmParamNWAI415019.put("transactionId", transactionId);
        ssmParamNWAI415019.put("somQuoteId", somQuoteId);

        List<Map<String, Object>> nwai415019TMsgList = NWXC412001.autoCast(this.ssmBatchClient.queryObjectList("getNWAI4150_19", ssmParamNWAI415019));

        for (Map<String, Object> nwai415019TMsgMap : nwai415019TMsgList) {
            NWAI4150_19TMsg nwai415019TMsg = new NWAI4150_19TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.interfaceId, (String) nwai415019TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.transactionId, (BigDecimal) nwai415019TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.segmentId, (BigDecimal) nwai415019TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.unitId, (BigDecimal) nwai415019TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.seqNumber, (BigDecimal) nwai415019TMsgMap.get("SEQ_NUMBER"));

            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.somSrvId, (BigDecimal) nwai415019TMsgMap.get("SOM_SRV_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.somSrvLineId, (BigDecimal) nwai415019TMsgMap.get("SOM_SRV_LINE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.somItemCd, (String) nwai415019TMsgMap.get("SOM_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.somMdlDescLongTxt, (String) nwai415019TMsgMap.get("SOM_MDL_DESC_LONG_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.somDurnId, (BigDecimal) nwai415019TMsgMap.get("SOM_DURN_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.reqBaseAmt, (BigDecimal) nwai415019TMsgMap.get("REQ_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.pubBaseAmt, (BigDecimal) nwai415019TMsgMap.get("PUB_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.reqSvcPrcAmt, (BigDecimal) nwai415019TMsgMap.get("REQ_SVC_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.pubSvcPrcAmt, (BigDecimal) nwai415019TMsgMap.get("PUB_SVC_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.totCostTrnsfAmt, (BigDecimal) nwai415019TMsgMap.get("TOT_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.dclnMaintIndSomTxt, (String) nwai415019TMsgMap.get("DCLN_MAINT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.somUnitId, (BigDecimal) nwai415019TMsgMap.get("SOM_UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.orclMdlNm, (String) nwai415019TMsgMap.get("ORCL_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.svcContrIndSomTxt, (String) nwai415019TMsgMap.get("SVC_CONTR_IND_SOM_TXT"));

            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.somQuoteId, (BigDecimal) nwai415019TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.somConfigId, (BigDecimal) nwai415019TMsgMap.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.somPkgNm, (String) nwai415019TMsgMap.get("SOM_PKG_NM"));

            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.totSvcRevTrnsfAmt, (BigDecimal) nwai415019TMsgMap.get("TOT_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.origCostTrnsfAmt, (BigDecimal) nwai415019TMsgMap.get("ORIG_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.origSvcRevTrnsfAmt, (BigDecimal) nwai415019TMsgMap.get("ORIG_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.prcCatgCd, (String) nwai415019TMsgMap.get("PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415019TMsg.somPrcCatgNm, (String) nwai415019TMsgMap.get("SOM_PRC_CATG_NM"));

            msgList.add(nwai415019TMsg);
        }
        return msgList;
    }

    private List<NWAI4150_22TMsg> getNwai415022(BigDecimal transactionId, BigDecimal somQuoteId) {
        List<NWAI4150_22TMsg> msgList = new ArrayList<NWAI4150_22TMsg>();
        Map<String, Object> ssmParamNWAI415022 = new HashMap<String, Object>();
        ssmParamNWAI415022.put("interfaceId", this.interfaceId);
        ssmParamNWAI415022.put("transactionId", transactionId);
        ssmParamNWAI415022.put("somQuoteId", somQuoteId);

        List<Map<String, Object>> nwai415022TMsgList //
        = NWXC412001.autoCast(this.ssmBatchClient.queryObjectList("getNWAI4150_22", ssmParamNWAI415022));

        for (Map<String, Object> nwai415022TMsgMap : nwai415022TMsgList) {
            NWAI4150_22TMsg nwai415022TMsg = new NWAI4150_22TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415022TMsg.interfaceId, (String) nwai415022TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415022TMsg.transactionId, (BigDecimal) nwai415022TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415022TMsg.segmentId, (BigDecimal) nwai415022TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415022TMsg.unitId, (BigDecimal) nwai415022TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415022TMsg.seqNumber, (BigDecimal) nwai415022TMsgMap.get("SEQ_NUMBER"));

            ZYPEZDItemValueSetter.setValue(nwai415022TMsg.intfcRecId, (String) nwai415022TMsgMap.get("INTFC_REC_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415022TMsg.somQuoteId, (BigDecimal) nwai415022TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415022TMsg.somConfigId, (BigDecimal) nwai415022TMsgMap.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415022TMsg.somSrvMtrTpNm, (String) nwai415022TMsgMap.get("SOM_SRV_MTR_TP_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415022TMsg.pubBaseTxt, (String) nwai415022TMsgMap.get("PUB_BASE_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415022TMsg.reqBaseTxt, (String) nwai415022TMsgMap.get("REQ_BASE_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415022TMsg.pubCpcTxt, (String) nwai415022TMsgMap.get("PUB_CPC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415022TMsg.reqCpcTxt, (String) nwai415022TMsgMap.get("REQ_CPC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415022TMsg.somSrvVolComitTxt, (String) nwai415022TMsgMap.get("SOM_SRV_VOL_COMIT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415022TMsg.somSrvVolAvgCnt, (BigDecimal) nwai415022TMsgMap.get("SOM_SRV_VOL_AVG_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415022TMsg.mthReqPmtAmt, (BigDecimal) nwai415022TMsgMap.get("MTH_REQ_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415022TMsg.mthPubPmtAmt, (BigDecimal) nwai415022TMsgMap.get("MTH_PUB_PMT_AMT"));

            msgList.add(nwai415022TMsg);
        }
        return msgList;
    }

    private List<NWAI4150_23TMsg> getNwai415023(BigDecimal transactionId, BigDecimal somQuoteId) {
        List<NWAI4150_23TMsg> msgList = new ArrayList<NWAI4150_23TMsg>();
        Map<String, Object> ssmParamNWAI415023 = new HashMap<String, Object>();
        ssmParamNWAI415023.put("interfaceId", this.interfaceId);
        ssmParamNWAI415023.put("transactionId", transactionId);
        ssmParamNWAI415023.put("somQuoteId", somQuoteId);

        List<Map<String, Object>> nwai415023TMsgList //
        = NWXC412001.autoCast(this.ssmBatchClient.queryObjectList("getNWAI4150_23", ssmParamNWAI415023));

        for (Map<String, Object> nwai415023TMsgMap : nwai415023TMsgList) {
            NWAI4150_23TMsg nwai415023TMsg = new NWAI4150_23TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.interfaceId, (String) nwai415023TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.transactionId, (BigDecimal) nwai415023TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.segmentId, (BigDecimal) nwai415023TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.unitId, (BigDecimal) nwai415023TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.seqNumber, (BigDecimal) nwai415023TMsgMap.get("SEQ_NUMBER"));

            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.intfcRecId, (String) nwai415023TMsgMap.get("INTFC_REC_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.somConfigId, (BigDecimal) nwai415023TMsgMap.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.somQuoteId, (BigDecimal) nwai415023TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.somMercItemCd, (String) nwai415023TMsgMap.get("SOM_MERC_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.somOrdQty, (BigDecimal) nwai415023TMsgMap.get("SOM_ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.somSortOrdNum, (BigDecimal) nwai415023TMsgMap.get("SOM_SORT_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.bidPmtAmt, (BigDecimal) nwai415023TMsgMap.get("BID_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.bidPrcAmt, (BigDecimal) nwai415023TMsgMap.get("BID_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.prfPrcAmt, (BigDecimal) nwai415023TMsgMap.get("PRF_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.sellPrchAmt, (BigDecimal) nwai415023TMsgMap.get("SELL_PRCH_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.sellLeaseAmt, (BigDecimal) nwai415023TMsgMap.get("SELL_LEASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.mthPrcAmt, (BigDecimal) nwai415023TMsgMap.get("MTH_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.somLeaseRate, (BigDecimal) nwai415023TMsgMap.get("SOM_LEASE_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.dlrCstAmt, (BigDecimal) nwai415023TMsgMap.get("DLR_CST_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.aftMktCrAmt, (BigDecimal) nwai415023TMsgMap.get("AFT_MKT_CR_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.csmpCrAmt, (BigDecimal) nwai415023TMsgMap.get("CSMP_CR_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.canonCashAmt, (BigDecimal) nwai415023TMsgMap.get("CANON_CASH_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.canonCashPct, (BigDecimal) nwai415023TMsgMap.get("CANON_CASH_PCT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.eopsPromoAmt, (BigDecimal) nwai415023TMsgMap.get("EOPS_PROMO_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.eopsUplftAmt, (BigDecimal) nwai415023TMsgMap.get("EOPS_UPLFT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.eopsMiscAmt, (BigDecimal) nwai415023TMsgMap.get("EOPS_MISC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.eopsMiscPct, (BigDecimal) nwai415023TMsgMap.get("EOPS_MISC_PCT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.eopsTotCstAmt, (BigDecimal) nwai415023TMsgMap.get("EOPS_TOT_CST_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.eopsGpAmt, (BigDecimal) nwai415023TMsgMap.get("EOPS_GP_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.eopsGpPct, (BigDecimal) nwai415023TMsgMap.get("EOPS_GP_PCT"));
            ZYPEZDItemValueSetter.setValue(nwai415023TMsg.eopsCstAmt, (BigDecimal) nwai415023TMsgMap.get("EOPS_CST_AMT"));

            msgList.add(nwai415023TMsg);
        }
        return msgList;
    }

    private List<NWAI4150_24TMsg> getNwai415024(BigDecimal transactionId, BigDecimal somQuoteId) {
        List<NWAI4150_24TMsg> msgList = new ArrayList<NWAI4150_24TMsg>();
        Map<String, Object> ssmParamNWAI415024 = new HashMap<String, Object>();
        ssmParamNWAI415024.put("interfaceId", this.interfaceId);
        ssmParamNWAI415024.put("transactionId", transactionId);
        ssmParamNWAI415024.put("somQuoteId", somQuoteId);

        List<Map<String, Object>> nwai415024TMsgList //
        = NWXC412001.autoCast(this.ssmBatchClient.queryObjectList("getNWAI4150_24", ssmParamNWAI415024));

        for (Map<String, Object> nwai415024TMsgMap : nwai415024TMsgList) {
            NWAI4150_24TMsg nwai415024TMsg = new NWAI4150_24TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.interfaceId, (String) nwai415024TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.transactionId, (BigDecimal) nwai415024TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.segmentId, (BigDecimal) nwai415024TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.unitId, (BigDecimal) nwai415024TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.seqNumber, (BigDecimal) nwai415024TMsgMap.get("SEQ_NUMBER"));

            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.somQuoteId, (BigDecimal) nwai415024TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.eopsRtrnId, (BigDecimal) nwai415024TMsgMap.get("EOPS_RTRN_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.somConfigId, (BigDecimal) nwai415024TMsgMap.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.somMnfTxt, (String) nwai415024TMsgMap.get("SOM_MNF_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.somMdlDescTxt, (String) nwai415024TMsgMap.get("SOM_MDL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.somDescSerNum, (String) nwai415024TMsgMap.get("SOM_DESC_SER_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.somItemCd, (String) nwai415024TMsgMap.get("SOM_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.eopsRtrnCmntTxt, (String) nwai415024TMsgMap.get("EOPS_RTRN_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.origLeaseCmpyTxt, (String) nwai415024TMsgMap.get("ORIG_LEASE_CMPY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.otbdConfigTxt, (String) nwai415024TMsgMap.get("OTBD_CONFIG_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.rtrnDspTxt, (String) nwai415024TMsgMap.get("RTRN_DSP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.rtrnPickUpTs, (String) nwai415024TMsgMap.get("RTRN_PICK_UP_TS"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.rtrnPickUpAddr, (String) nwai415024TMsgMap.get("RTRN_PICK_UP_ADDR"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.somPsnTxt, (String) nwai415024TMsgMap.get("SOM_PSN_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.rtrnPickUpAddr_01, (String) nwai415024TMsgMap.get("RTRN_PICK_UP_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.rtrnPickUpAddr_02, (String) nwai415024TMsgMap.get("RTRN_PICK_UP_ADDR_02"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.rtrnPickUpCtyTxt, (String) nwai415024TMsgMap.get("RTRN_PICK_UP_CTY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.pickUpStCd, (String) nwai415024TMsgMap.get("PICK_UP_ST_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.pickUpCntyNm, (String) nwai415024TMsgMap.get("PICK_UP_CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.pickUpZipCd, (String) nwai415024TMsgMap.get("PICK_UP_ZIP_CD"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.rtrnCtacFirstNm, (String) nwai415024TMsgMap.get("RTRN_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.rtrnCtacLastNm, (String) nwai415024TMsgMap.get("RTRN_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.pickUpCtacPhoTxt, (String) nwai415024TMsgMap.get("PICK_UP_CTAC_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.rtrnPhoExtTxt, (String) nwai415024TMsgMap.get("RTRN_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415024TMsg.rtrnPickUpEmlTxt, (String) nwai415024TMsgMap.get("RTRN_PICK_UP_EML_TXT"));

            msgList.add(nwai415024TMsg);
        }
        return msgList;
    }

    private List<NWAI4150_26TMsg> getNwai415026(BigDecimal transactionId, BigDecimal somQuoteId) {
        List<NWAI4150_26TMsg> msgList = new ArrayList<NWAI4150_26TMsg>();
        Map<String, Object> ssmParamNWAI415026 = new HashMap<String, Object>();
        ssmParamNWAI415026.put("interfaceId", this.interfaceId);
        ssmParamNWAI415026.put("transactionId", transactionId);
        ssmParamNWAI415026.put("somQuoteId", somQuoteId);

        List<Map<String, Object>> nwai415026TMsgList //
        = NWXC412001.autoCast(this.ssmBatchClient.queryObjectList("getNWAI4150_26", ssmParamNWAI415026));

        for (Map<String, Object> nwai415026TMsgMap : nwai415026TMsgList) {
            NWAI4150_26TMsg nwai415026TMsg = new NWAI4150_26TMsg();

            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.interfaceId, (String) nwai415026TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.transactionId, (BigDecimal) nwai415026TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.segmentId, (BigDecimal) nwai415026TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.unitId, (BigDecimal) nwai415026TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.seqNumber, (BigDecimal) nwai415026TMsgMap.get("SEQ_NUMBER"));

            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.somQuoteId, (BigDecimal) nwai415026TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.eopsConfigLtrTxt, (String) nwai415026TMsgMap.get("EOPS_CONFIG_LTR_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.somOrdQty, (BigDecimal) nwai415026TMsgMap.get("SOM_ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.sellPrcListAmt, (BigDecimal) nwai415026TMsgMap.get("SELL_PRC_LIST_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.extMthPrcAmt, (BigDecimal) nwai415026TMsgMap.get("EXT_MTH_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.extSellPrcAmt, (BigDecimal) nwai415026TMsgMap.get("EXT_SELL_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.contrTpTxt, (String) nwai415026TMsgMap.get("CONTR_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.planDescTxt, (String) nwai415026TMsgMap.get("PLAN_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.somSrvTermId, (BigDecimal) nwai415026TMsgMap.get("SOM_SRV_TERM_ID"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.somBllgCycleDescTxt, (String) nwai415026TMsgMap.get("SOM_BLLG_CYCLE_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.usgCycleDescTxt, (String) nwai415026TMsgMap.get("USG_CYCLE_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.extPmtAmt, (BigDecimal) nwai415026TMsgMap.get("EXT_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.reqBaseAmt, (BigDecimal) nwai415026TMsgMap.get("REQ_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.bwMtrTpNm, (String) nwai415026TMsgMap.get("BW_MTR_TP_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.bwVolComitCnt, (BigDecimal) nwai415026TMsgMap.get("BW_VOL_COMIT_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.bwBaseAmt, (BigDecimal) nwai415026TMsgMap.get("BW_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.bwCpcAmt, (BigDecimal) nwai415026TMsgMap.get("BW_CPC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.bwPmtAmt, (BigDecimal) nwai415026TMsgMap.get("BW_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.clrMtrTpNm, (String) nwai415026TMsgMap.get("CLR_MTR_TP_NM"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.clrVolComitCnt, (BigDecimal) nwai415026TMsgMap.get("CLR_VOL_COMIT_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.clrBaseAmt, (BigDecimal) nwai415026TMsgMap.get("CLR_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.clrCpcAmt, (BigDecimal) nwai415026TMsgMap.get("CLR_CPC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.clrPmtAmt, (BigDecimal) nwai415026TMsgMap.get("CLR_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai415026TMsg.mthBaseAmt, (BigDecimal) nwai415026TMsgMap.get("MTH_BASE_AMT"));

            msgList.add(nwai415026TMsg);
        }
        return msgList;
    }

    private boolean deleteDuplicateDsImpt(Map<String, List<BigDecimal>> duplicateDsImptPkMap) {
        List<BigDecimal> dsImptOrdPkList = duplicateDsImptPkMap.get("dsImptOrdPk");
        for (BigDecimal dsImptOrdPk : dsImptOrdPkList) {

            DS_IMPT_ORDTMsg dsImptOrd = new DS_IMPT_ORDTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrd.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrd.dsImptOrdPk, dsImptOrdPk);
            dsImptOrd = (DS_IMPT_ORDTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsImptOrd);
            if (dsImptOrd == null) {
                return false;
            }
            if (!S21StringUtil.isEquals(dsImptOrd.imptStsCd.getValue(), IMPT_STS.NOT_PROCESSED) && !S21StringUtil.isEquals(dsImptOrd.imptStsCd.getValue(), IMPT_STS.PENDING_OM_REVIEW)
                    && !S21StringUtil.isEquals(dsImptOrd.imptStsCd.getValue(), IMPT_STS.REJECT)) {
                return false;
            }
            EZDTBLAccessor.remove(dsImptOrd);
            if (!S21StringUtil.isEquals(dsImptOrd.getReturnCode(), EZDTBLAccessor.RTNCD_NORMAL)) {
                return false;
            }

            DS_IMPT_ORD_CONFIGTMsg dsImptOrdConfig = new DS_IMPT_ORD_CONFIGTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdConfig.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdConfig.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdConfig, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_DTLTMsg dsImptOrdDtl = new DS_IMPT_ORD_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdDtl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdDtl.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdDtl, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_RTRN_DTLTMsg dsImptOrdRtrnDtl = new DS_IMPT_ORD_RTRN_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdRtrnDtl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdRtrnDtl.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdRtrnDtl, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_SLS_CRTMsg dsImptOrdSlsCr = new DS_IMPT_ORD_SLS_CRTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdSlsCr, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_CTAC_PSNTMsg dsImptOrdCtacPsn = new DS_IMPT_ORD_CTAC_PSNTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdCtacPsn.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdCtacPsn.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdCtacPsn, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_SITE_SRVYTMsg dsImptOrdSiteSrvy = new DS_IMPT_ORD_SITE_SRVYTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdSiteSrvy.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdSiteSrvy.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdSiteSrvy, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_DELY_INFOTMsg dsImptOrdDelyInfo = new DS_IMPT_ORD_DELY_INFOTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdDelyInfo.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdDelyInfo.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdDelyInfo, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_ISTL_INFOTMsg dsImptOrdIstlInfo = new DS_IMPT_ORD_ISTL_INFOTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdIstlInfo.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdIstlInfo.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdIstlInfo, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_PRC_CALC_BASETMsg dsImptPrcCalcBase = new DS_IMPT_PRC_CALC_BASETMsg();
            ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBase.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBase.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptPrcCalcBase, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_RTRN_PRC_CALCTMsg dsImptRtrnPrcCalc = new DS_IMPT_RTRN_PRC_CALCTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptRtrnPrcCalc.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptRtrnPrcCalc.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptRtrnPrcCalc, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_SVC_DTLTMsg dsImptSvcDtl = new DS_IMPT_SVC_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSvcDtl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSvcDtl.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSvcDtl, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_SVC_CONFIG_REFTMsg dsImptSvcConfigRef = new DS_IMPT_SVC_CONFIG_REFTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSvcConfigRef.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSvcConfigRef.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSvcConfigRef, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_SVC_ADDL_BASETMsg dsImptSvcAddlBase = new DS_IMPT_SVC_ADDL_BASETMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSvcAddlBase.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSvcAddlBase.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSvcAddlBase, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_SVC_ADDL_CHRGTMsg dsImptSvcAddlChrg = new DS_IMPT_SVC_ADDL_CHRGTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSvcAddlChrg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSvcAddlChrg.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSvcAddlChrg, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_SVC_PRC_TAX_DTLTMsg dsImptSvcPrcTaxDtl = new DS_IMPT_SVC_PRC_TAX_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSvcPrcTaxDtl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSvcPrcTaxDtl.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSvcPrcTaxDtl, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_EXT_ATTRBTMsg dsImptExtAttrb = new DS_IMPT_EXT_ATTRBTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptExtAttrb.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptExtAttrb.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptExtAttrb, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_DTL_EXT_ATTRBTMsg dsImptDtlExtAttrb = new DS_IMPT_DTL_EXT_ATTRBTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptDtlExtAttrb.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptDtlExtAttrb.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptDtlExtAttrb, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_ERRTMsg dsImptOrdErr = new DS_IMPT_ORD_ERRTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdErr.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdErr, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_SOM_APVLTMsg dsImptOrdSomApvl = new DS_IMPT_ORD_SOM_APVLTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdSomApvl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdSomApvl.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdSomApvl, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_APVL_NOTETMsg dsImptOrdApvlNote = new DS_IMPT_ORD_APVL_NOTETMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdApvlNote.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdApvlNote.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdApvlNote, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_SOM_PRFTTMsg dsImptOrdSomPrft = new DS_IMPT_ORD_SOM_PRFTTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdSomPrft.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdSomPrft.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdSomPrft, new String[] {"glblCmpyCd", "dsImptOrdPk" });
        }

        List<BigDecimal> dsImptSvcDtlPkList = duplicateDsImptPkMap.get("dsImptSvcDtlPk");
        for (BigDecimal dsImptSvcDtlPk : dsImptSvcDtlPkList) {

            DS_IMPT_SVC_PRCTMsg dsImptSvcPrc = new DS_IMPT_SVC_PRCTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSvcPrc.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSvcPrc.dsImptSvcDtlPk, dsImptSvcDtlPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSvcPrc, new String[] {"glblCmpyCd", "dsImptSvcDtlPk" });

            DS_IMPT_SVC_USG_PRCTMsg dsImptSvcUsgPrc = new DS_IMPT_SVC_USG_PRCTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSvcUsgPrc.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSvcUsgPrc.dsImptSvcDtlPk, dsImptSvcDtlPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSvcUsgPrc, new String[] {"glblCmpyCd", "dsImptSvcDtlPk" });

            DS_IMPT_SCHD_TMPLTMsg dsImptSchdTmpl = new DS_IMPT_SCHD_TMPLTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSchdTmpl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSchdTmpl.dsImptSvcDtlPk, dsImptSvcDtlPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSchdTmpl, new String[] {"glblCmpyCd", "dsImptSvcDtlPk" });
        }

        List<BigDecimal> dsImptSchdTmplPkList = duplicateDsImptPkMap.get("dsImptSchdTmplPk");
        for (BigDecimal dsImptSchdTmplPk : dsImptSchdTmplPkList) {

            DS_IMPT_SCHD_TMPL_LINETMsg dsImptSchdTmplLine = new DS_IMPT_SCHD_TMPL_LINETMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSchdTmplLine.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSchdTmplLine.dsImptSchdTmplPk, dsImptSchdTmplPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSchdTmplLine, new String[] {"glblCmpyCd", "dsImptSchdTmplPk" });
        }
        return true;
    }

    private Map<String, Object> getVarCharConstMap() {

        HashMap<String, Object> varCharConstMap = new HashMap<String, Object>();
        for (VAR_CHAR_CONST_NM enumValue : NWAB415001Constant.VAR_CHAR_CONST_NM.values()) {
            varCharConstMap.put(//
                    enumValue.name() //
                    , ZYPCodeDataUtil.getVarCharConstValue(enumValue.name(), glblCmpyCd));
        }
        //        varCharConstMap.put(//
        //                NWAB415001Constant.VAR_CHAR_CONST_NM.ARCS_SPLY_SITE_CD_FOR_EOPS.name() //
        //                , ZYPCodeDataUtil.getVarCharConstValue(NWAB415001Constant.VAR_CHAR_CONST_NM.ARCS_SPLY_SITE_CD_FOR_EOPS.name(), glblCmpyCd));
        //        varCharConstMap.put(//
        //                NWAB415001Constant.VAR_CHAR_CONST_NM.CFS_VND_CD_FOR_EOPS.name() //
        //                , ZYPCodeDataUtil.getVarCharConstValue(NWAB415001Constant.VAR_CHAR_CONST_NM.CFS_VND_CD_FOR_EOPS.name(), glblCmpyCd));
        //        varCharConstMap.put(//
        //                NWAB415001Constant.VAR_CHAR_CONST_NM.EOPS_DEFAULT_PRICE_LIST.name() //
        //                , ZYPCodeDataUtil.getVarCharConstValue(NWAB415001Constant.VAR_CHAR_CONST_NM.EOPS_DEFAULT_PRICE_LIST.name(), glblCmpyCd));
        //        varCharConstMap.put(//
        //                NWAB415001Constant.VAR_CHAR_CONST_NM.EOPS_DEF_PMT_TERM_CASH_DISC_CD.name() //
        //                , ZYPCodeDataUtil.getVarCharConstValue(NWAB415001Constant.VAR_CHAR_CONST_NM.EOPS_DEF_PMT_TERM_CASH_DISC_CD.name(), glblCmpyCd));
        //        varCharConstMap.put(//
        //                NWAB415001Constant.VAR_CHAR_CONST_NM.EOPS_MDSE_TRADE_IN.name() //
        //                , ZYPCodeDataUtil.getVarCharConstValue(NWAB415001Constant.VAR_CHAR_CONST_NM.EOPS_MDSE_TRADE_IN.name(), glblCmpyCd));
        //        varCharConstMap.put(//
        //                NWAB415001Constant.VAR_CHAR_CONST_NM.EOPS_MDSE_UPGRADE_RETURN_RMA.name() //
        //                , ZYPCodeDataUtil.getVarCharConstValue(NWAB415001Constant.VAR_CHAR_CONST_NM.EOPS_MDSE_UPGRADE_RETURN_RMA.name(), glblCmpyCd));
        //        varCharConstMap.put(//
        //                NWAB415001Constant.VAR_CHAR_CONST_NM.EOPS_TRADE_IN_NON_CANON.name() //
        //                , ZYPCodeDataUtil.getVarCharConstValue(NWAB415001Constant.VAR_CHAR_CONST_NM.EOPS_TRADE_IN_NON_CANON.name(), glblCmpyCd));
        //        varCharConstMap.put(//
        //                NWAB415001Constant.VAR_CHAR_CONST_NM.EOPS_WRITING_REP_SPLIT_PERCENT.name() //
        //                , ZYPCodeDataUtil.getVarCharConstValue(NWAB415001Constant.VAR_CHAR_CONST_NM.EOPS_WRITING_REP_SPLIT_PERCENT.name(), glblCmpyCd));
        //        varCharConstMap.put(//
        //                NWAB415001Constant.VAR_CHAR_CONST_NM.LEASE_BYOT_MDSE_CD.name() //
        //                , ZYPCodeDataUtil.getVarCharConstValue(NWAB415001Constant.VAR_CHAR_CONST_NM.LEASE_BYOT_MDSE_CD.name(), glblCmpyCd));
        //        varCharConstMap.put(//
        //                NWAB415001Constant.VAR_CHAR_CONST_NM.DROP_SHIP_RTL_WH_CD.name() //
        //                , ZYPCodeDataUtil.getVarCharConstValue(NWAB415001Constant.VAR_CHAR_CONST_NM.DROP_SHIP_RTL_WH_CD.name(), glblCmpyCd));
        return varCharConstMap;
    }

    @Override
    protected void termRoutine() {
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new NWAB415001().executeBatch(NWAB415001.class.getSimpleName());

    }

}
