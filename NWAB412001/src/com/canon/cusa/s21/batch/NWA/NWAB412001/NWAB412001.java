/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import parts.common.EZDSystemEnv;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DOC_MGT_CATGTMsg;
import business.db.DOC_MGT_FLDTMsg;
import business.db.DOC_MGT_SCAN_TRXTMsg;
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

import net.therefore.schemas.webservices.interop.v0001.messages.IThereforeService;
import net.therefore.schemas.webservices.interop.v0001.types.ArrayOfWSCondition;
import net.therefore.schemas.webservices.interop.v0001.types.ArrayOfWSIndexDataItem;
import net.therefore.schemas.webservices.interop.v0001.types.ExecuteSingleQueryParams;
import net.therefore.schemas.webservices.interop.v0001.types.ExecuteSingleQueryResponse;
import net.therefore.schemas.webservices.interop.v0001.types.GetDocumentIndexDataParams;
import net.therefore.schemas.webservices.interop.v0001.types.GetDocumentIndexDataResponse;
import net.therefore.schemas.webservices.interop.v0001.types.QueryObject;
import net.therefore.schemas.webservices.interop.v0001.types.WSCondition;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataDate;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataInt;
import net.therefore.schemas.webservices.interop.v0001.types.WSIndexDataItem;
import net.therefore.schemas.webservices.interop.v0001.types.WSQueryResultRow;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.DELY_INFO_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.FLG;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.ISTL_INFO_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.MSG_ID;
//import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.SITE_SRVY_TYPE; // 2017/09/13 QC#21084 Del
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.SLS_CR_TYPE;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Result;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Util;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DOC_MGT_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.therefore.webservice.S21ThereforeWebServiceProxy;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 12/01/2016   SRAA            K.Aratani       Update          QC#15539
 * 02/07/2017   FUJITSU         H.Nagashima     Update          QC#17120
 * 04/03/2017   FUJITSU         S.Iidaka        Update          QC#18179
 * 04/11/2017   SRAA            K.Aratani       Update          QC#18306
 * 08/04/2017   FUJITSU         R.Nakamura      Update          QC#19839
 * 09/13/2017   FUJITSU         S.Iidaka        Update          QC#21084
 * 01/19/2018   FUJITSU         W.Honda         Update          QC#23598
 * 02/16/2018   SRAA            K.Aratani       Update          QC#24078, QC#24093
 * 02/16/2018   SRAA            K.Aratani       Update          QC#24023
 * 03/20/2018   Fujitsu         R.Nakamura      Update          QC#23991
 * 04/13/2018   SRAA            K.Aratani       Update          QC#25324
 *</pre>
 */
public class NWAB412001 extends S21BatchMain {

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** Interface ID */
    private String interfaceId = null;

    /** Transaction ID */
    private BigDecimal[] transactionIds;

    /** Total Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Item Cache */
    private Map<String, Map<String, Object>> cacheItemMap;

    /** Price Category Code Cache */
    private Map<String, String> cachePrcCatgCdMap;

    /** Price Category Name Cache */
    private Map<String, String> cachePrcCatgNmMap;

    /** Specific Condition Price Cache */
    private Map<String, BigDecimal> cacheSpecCondPrcMap;

    /** Ship To Customer Cache */
    private Map<String, Map<String, Object>> cacheShipToCustMap;

    /** S21 PSN From Install REP Name Cache */
    private Map<String, String> cacheS21PsnFromInstallRepNameMap;
    
    /** Sales REP Cache */
    private Map<String, Map<String, Object>> cacheSlsRepTocCdMap;

    /** Sales REP Role Type Code Cache */
    private Map<String, String> cacheSlsRepRoleTpCdMap;

    /** Model Cache */
    private Map<String, BigDecimal> cacheModelMap;

    /** Price Meter Package PK Cache */
    private Map<String, BigDecimal> cachePrcMtrPkgPkMap;

    /**
     * @param args
     */
    public static void main(String[] args) {
        new NWAB412001().executeBatch(NWAB412001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        writeStartLogLn("initRoutine");

        try {

            try {

                // for debug
                String[] userValiable5 = getUserVariable5().split(",");
                this.transactionIds = new BigDecimal[userValiable5.length];
                for (int i = 0; i < userValiable5.length; i++) {

                    this.transactionIds[i] = new BigDecimal(userValiable5[i]);
                }

            } catch (Throwable e) {

                this.transactionIds = null;
            }

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
            

            // TODO
            // if (!ZYPCommonFunc.hasValue(this.mailTemplateId)) {
            // throw new
            // S21AbendException(MSG_ID.ZZZM9025E.toString(),
            // toArray("Mail Template ID"));
            // }

        } finally {
            writeEndLogLn("initRoutine");
        }
    }

    @Override
    protected void mainRoutine() {
        writeStartLogLn("mainRoutine");

        try {
            registDsImptTable();
        } finally {
            writeEndLogLn("mainRoutine");
        }
    }

    @Override
    protected void termRoutine() {
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);

    }

    private void registDsImptTable() {
        writeStartLogLn("registDsImptTable", interfaceId);

        try {
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

            BigDecimal somQuoteId;
            List<NWAI4120_02TMsg> nwai412002msgList;
            List<NWAI4120_03TMsg> nwai412003msgList;
            List<NWAI4120_04TMsg> nwai412004msgList;
            List<NWAI4120_06TMsg> nwai412006msgList;
            List<NWAI4120_10TMsg> nwai412010msgList;
            List<NWAI4120_13TMsg> nwai412013msgList;
            List<NWAI4120_14TMsg> nwai412014msgList;
            List<NWAI4120_16TMsg> nwai412016msgList;
            List<NWAI4120_17TMsg> nwai412017msgList;
            List<NWAI4120_18TMsg> nwai412018msgList;
            List<NWAI4120_19TMsg> nwai412019msgList;
            List<NWAI4120_20TMsg> nwai412020msgList;
            List<NWAI4120_21TMsg> nwai412021msgList;

            //VarCharConst
            Map<String, Object> varCharConstMap = new HashMap<String, Object>();
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_DEF_PMT_TERM_CASH_DISC_CD.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_DEF_PMT_TERM_CASH_DISC_CD.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_DEFAULT_PRICE_LIST.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_DEFAULT_PRICE_LIST.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_MDSE_TRADE_IN.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_MDSE_TRADE_IN.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.CFS_VND_CD_FOR_SOM.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.CFS_VND_CD_FOR_SOM.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.ARCS_SPLY_SITE_CD_FOR_SOM.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.ARCS_SPLY_SITE_CD_FOR_SOM.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.CIT_VND_CD_FOR_SOM.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.CIT_VND_CD_FOR_SOM.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_MDSE_UPGRADE_RETURN_RMA.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_MDSE_UPGRADE_RETURN_RMA.name(), glblCmpyCd));
            //varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.CANON_E22_CFS_INFO_BILL_TO.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.CANON_E22_CFS_INFO_BILL_TO.name(), glblCmpyCd));
            //varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.CANON_E22_CFS_INFO_ACCT.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.CANON_E22_CFS_INFO_ACCT.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_WRITING_REP_SPLIT_PERCENT.name(), ZYPCodeDataUtil.getNumConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_WRITING_REP_SPLIT_PERCENT.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_FIRST_NAME_FLG.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_FIRST_NAME_FLG.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_LAST_NAME_FLG.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_LAST_NAME_FLG.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_PHONE_FLG.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_PHONE_FLG.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_PHONE_EXTN_FLG.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_PHONE_EXTN_FLG.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_FAX_FLG.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_FAX_FLG.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_EMAIL_FLG.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_EMAIL_FLG.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_NOTE_FLG.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_NOTE_FLG.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_HRS_FROM_FLG.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_HRS_FROM_FLG.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_HRS_TO_FLG.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_HRS_TO_FLG.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_STEPS_FLG.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_STEPS_FLG.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_ELEV_FLG.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_ELEV_FLG.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_LDOCK_FLG.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_LDOCK_FLG.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_HRS_FROM_NM.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_HRS_FROM_NM.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_HRS_TO_NM.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_HRS_TO_NM.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_STEPS_NM.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_STEPS_NM.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_ELEV_NM.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_ELEV_NM.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_LDOCK_NM.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DELY_CMNT_LDOCK_NM.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.DROP_SHIP_RTL_WH_CD.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.DROP_SHIP_RTL_WH_CD.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_TRADE_IN_NON_CANON.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_TRADE_IN_NON_CANON.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.LEASE_BYOT_MDSE_CD.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.LEASE_BYOT_MDSE_CD.name(), glblCmpyCd)); // QC#18179 2017/04/03 ADD
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_NEED_RENTAL_AVAIL_ITEM_CHK.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_NEED_RENTAL_AVAIL_ITEM_CHK.name(), glblCmpyCd));
            varCharConstMap.put(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_RMA_DEF_PRC_LIST.name(), ZYPCodeDataUtil.getVarCharConstValue(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_RMA_DEF_PRC_LIST.name(), glblCmpyCd)); //QC#24078, QC#24093
            

            for (int i = 0; i < transactionIds.length; i++) {
                // *************************************************************
                // Deriving NWAI4120_01
                // *************************************************************
                List<NWAI4120_01TMsg> nwai412001msgList = this.getNwai412001(transactionIds[i]);

                if (!hasValueList(nwai412001msgList)) {
                    s21tta.endIntegrationProcess(this.interfaceId, transactionIds[i]);
                    continue;
                }

                for (int j = 0; j < nwai412001msgList.size(); j++) {
                    this.totalCount++;
                    // Add Start 2017/08/04 QC#19839
                    boolean isError = false;
                    // Add End 2017/08/04 QC#19839
                    SomDsImptOrdBean somDsImptOrdBean = new SomDsImptOrdBean(this.ssmBatchClient, this.glblCmpyCd, this.salesDate, nwai412001msgList.get(j));
                    somDsImptOrdBean.setVarCharConstMap(varCharConstMap);  //VarCharConst Cache
                    somDsImptOrdBean.setCacheItemMap(cacheItemMap);  //Item Cache
                    somDsImptOrdBean.setCachePrcCatgCdMap(cachePrcCatgCdMap);  //Price List Cache
                    somDsImptOrdBean.setCachePrcCatgNmMap(cachePrcCatgNmMap);  //Price List Name Cache
                    somDsImptOrdBean.setCacheShipToCustMap(cacheShipToCustMap);  //Ship To Customer Cache
                    somDsImptOrdBean.setCacheSpecCondPrcMap(cacheSpecCondPrcMap);  //Specific Condition Cache
                    somDsImptOrdBean.setCacheSlsRepTocCdMap(cacheSlsRepTocCdMap);  //Sales REP Cache
                    somDsImptOrdBean.setCacheS21PsnFromInstallRepNameMap(cacheS21PsnFromInstallRepNameMap);  //S21 Person Cache
                    somDsImptOrdBean.setCacheSlsRepRoleTpCdMap(cacheSlsRepRoleTpCdMap);  //Sales REP Role Type Code Cache
                    somDsImptOrdBean.setCacheModelMap(cacheModelMap);  //Model Cache
                    somDsImptOrdBean.setCachePrcMtrPkgPkMap(cachePrcMtrPkgPkMap);  //Service Machine Master PK Cache
                    somQuoteId = somDsImptOrdBean.nwai4120_01.somQuoteId.getValue();

                    // validate order source reference number.
                    NWXC220001Result<Map<String, List<BigDecimal>>> result = NWXC220001.checkDuplicateOrdSrcRefNum(this.glblCmpyCd, NWXC412001.formatOrdSrcRefNum(somQuoteId.toPlainString()), CPO_SRC_TP.SOM, null);
                    Map<String, List<BigDecimal>> duplicateDsImptPkMap = result.getResultObject();
                    // delete import staging.
                    if (!deleteDuplicateDsImpt(this.glblCmpyCd, duplicateDsImptPkMap)) {
                        // this.addError(MSG_ID.NWAM0730E, new
                        // String[] {"DS_IMPT_ORD" },
                        // transactionIds[i],
                        // somDsImptOrdBean.nwai4120_01.unitId.getValue(),
                        // somDsImptOrdBean.nwai4120_01.seqNumber.getValue());
                        rollback();
                        this.totalErrCount++;
                        continue;
                    } else {
                        commit();
                    }

                    // *********************************************************
                    // Deriving NWAI4120_12(V_CONFIGURATION_SHIPTOS)
                    // *********************************************************
                    List<NWAI4120_12TMsg> nwai412012msgList = this.getNwai412012(transactionIds[i], somQuoteId);
                    String lastUpdTs01 = nwai412001msgList.get(j).lastUpdTs.getValue();
                    boolean skipOrdFlg = true;
                    for (NWAI4120_12TMsg nwai412012msg : nwai412012msgList) {
                        if (S21StringUtil.isEquals(lastUpdTs01, nwai412012msg.lastUpdTs.getValue())) {
                            skipOrdFlg = false;
                            break;
                        }
                    }
                    if (skipOrdFlg) {
                        continue;
                    }

                    //Install Information
                    new SomDsImptOrdIstlInfoBean(ISTL_INFO_TYPE.HEADER_DELY_INFO, somDsImptOrdBean);
                    //Delivery Information
                    new SomDsImptOrdDelyInfoBean(DELY_INFO_TYPE.HEADER_DELY_INFO, somDsImptOrdBean);
                    //Site Survey Information
                    // 2017/09/13 QC#21084 Del Start
//                    new SomDsImptOrdSiteSrvyBean(SITE_SRVY_TYPE.HEADER_DELY_INFO, somDsImptOrdBean);
                    // 2017/09/13 QC#21084 Del End

                    // *********************************************************
                    // Process (1) (HEADER)
                    // *********************************************************
                    new SomDsImptOrdSlsCrBean(SLS_CR_TYPE.HEADER, somDsImptOrdBean);
                    somDsImptOrdBean.addDsImptOrdCtacPsnBean();

                    // *********************************************************
                    // Deriving NWAI4120_02(V_APPROVALS)
                    // *********************************************************
                    nwai412002msgList = this.getNwai412002(transactionIds[i], somQuoteId);

                    if (hasValueList(nwai412002msgList)) {
                        // *****************************************************
                        // Deriving NWAI4120_03(V_APPROVAL_NOTES)
                        // *****************************************************
                        nwai412003msgList = this.getNwai412003(transactionIds[i], somQuoteId);

                        somDsImptOrdBean.createImptConfigDtlForApproval(somDsImptOrdBean, nwai412002msgList, nwai412003msgList);
                    }

                    // *********************************************************
                    // Deriving NWAI4120_04(DS_IMPT_ORD_SOM_PRFT)
                    // *********************************************************
                    nwai412004msgList = this.getNwai412004(transactionIds[i], somQuoteId);

                    if (hasValueList(nwai412004msgList)) {
                    	somDsImptOrdBean.createImptConfigDtlForProfitability(somDsImptOrdBean, nwai412004msgList);
                    }

                    if (hasValueList(nwai412012msgList)) {

                        // *****************************************************
                        // Deriving
                        // NWAI4120_13(V_CONFIGURATION_MACHINE_SERIALS)
                        // *****************************************************
                        nwai412013msgList = this.getNwai412013(transactionIds[i], somQuoteId);

                        // *****************************************************
                        // Deriving NWAI4120_10(V_QUOTE_LINES)
                        // *****************************************************
                        nwai412010msgList = this.getNwai412010(transactionIds[i], somQuoteId);

                        // *****************************************************
                        // Deriving NWAI4120_14(V_OCE_PROMOTIONS)
                        // *****************************************************
                        nwai412014msgList = this.getNwai412014(transactionIds[i], somQuoteId);

                        somDsImptOrdBean.createImptConfigDtlFromNWAI4120_12(somDsImptOrdBean, nwai412012msgList, nwai412013msgList, nwai412014msgList, nwai412010msgList);
                    }

                    // *********************************************************
                    // Deriving NWAI4120_05(V_LINE_SOFTCOSTS)
                    // *********************************************************
                    List<NWAI4120_05TMsg> nwai412005msgList = this.getNwai412005(transactionIds[i], somQuoteId);

                    if (hasValueList(nwai412005msgList)) {
                        // *****************************************************
                        // Deriving NWAI4120_06(V_LINE_TRADE_INS)
                        // *****************************************************
                        nwai412006msgList = this.getNwai412006(transactionIds[i], somQuoteId);

                        somDsImptOrdBean.createImptConfigDtlFromNWAI4120_05(somDsImptOrdBean, nwai412005msgList, nwai412006msgList);
                    }

                    somDsImptOrdBean.createImptOrdDtlForHdrRebate(somDsImptOrdBean);

                    //Only Not Decline Maintenance in Header Level
                    if (!FLG.YES.name().equals(somDsImptOrdBean.nwai4120_01.dclnMaintIndSomTxt.getValue())) {
                        // *********************************************************
                        // Deriving NWAI4120_16(V_SRV_HEADERS)
                        // *********************************************************
                        nwai412016msgList = this.getNwai412016(transactionIds[i], somQuoteId);

                        if (hasValueList(nwai412016msgList)) {
                            // *********************************************************
                            // Deriving NWAI4120_17 (V_SRV_BASEONLYS)
                            // *********************************************************
                            nwai412017msgList = this.getNwai412017(transactionIds[i], somQuoteId);

                            // *********************************************************
                            // Deriving NWAI4120_18 (V_SRV_COPIERS)
                            // *********************************************************
                            nwai412018msgList = this.getNwai412018(transactionIds[i], somQuoteId);

                            // *********************************************************
                            // Deriving NWAI4120_19 (V_SRV_NONCOPIERS)
                            // *********************************************************
                            nwai412019msgList = this.getNwai412019(transactionIds[i], somQuoteId);

                            // *********************************************************
                            // Deriving NWAI4120_20
                            // (V_SRV_SPECIALSERVICE_CHARGES)
                            // *********************************************************
                            nwai412020msgList = this.getNwai412020(transactionIds[i], somQuoteId);

                            // *********************************************************
                            // Deriving NWAI4120_21
                            // (V_SRV_RENTAL)
                            // *********************************************************
                            nwai412021msgList = this.getNwai412021(transactionIds[i], somQuoteId);

                            //if (hasValueList(nwai412018msgList)) {
                            	somDsImptOrdBean.createShellForSrvCopiers(somDsImptOrdBean, nwai412016msgList, nwai412017msgList, nwai412018msgList, nwai412020msgList, nwai412021msgList);
                            //}

                            //if (hasValueList(nwai412019msgList)) {
                            	somDsImptOrdBean.createShellForSrvNonCopiers(somDsImptOrdBean, nwai412016msgList, nwai412017msgList, nwai412018msgList, nwai412019msgList, nwai412020msgList, nwai412021msgList);
                            //}
                            
                            //QC#24023
                            if (somDsImptOrdBean.dsImptOrdConfigBeanList != null) {
                                for (SomDsImptOrdConfigBean config : somDsImptOrdBean.dsImptOrdConfigBeanList) {
                                    //if (config.getCopierDeclineMaintenanceFlg() || config.getNonCopierDeclineMaintenanceFlg()) {
                                    if (config.getCopierDeclineMaintenanceFlg() && config.getNonCopierDeclineMaintenanceFlg()) {
                                        ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_ON_1);
                                    } else {
                                        ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_OFF_0);
                                    }
                                }
                            }
                            
                        } else {
                            if (somDsImptOrdBean.dsImptOrdConfigBeanList != null) {
                                for (SomDsImptOrdConfigBean config : somDsImptOrdBean.dsImptOrdConfigBeanList) {
                                    ZYPEZDItemValueSetter.setValue(config.dclnSvcCd, ZYPConstant.FLG_ON_1);
                                }
                            }
                        }
                    }

                    // *********************************************************
                    // Deriving NWAI4120_08(V_SPIFF_DETAILS)
                    // *********************************************************
                    List<NWAI4120_08TMsg> nwai412008msgList = this.getNwai412008(transactionIds[i], somQuoteId);

                    if (hasValueList(nwai412008msgList)) {
                    	somDsImptOrdBean.createImptOrdDtlForSpiffItems(somDsImptOrdBean, nwai412008msgList);
                    }

                    // *********************************************************
                    // Process (16) (Ship Config)
                    // Process (16) (RMA Config)
                    // *********************************************************
                    somDsImptOrdBean.createImptOrdSlsCrForConfig(somDsImptOrdBean);

                    somDsImptOrdBean.doImptMapping(this.glblCmpyCd, this.salesDate);

                    // Mod Start 2017/08/04 QC#19839
                    if (isError || checkMdlId(somDsImptOrdBean)) {
                        isError = true;
                    } else if (insertImptTables(somDsImptOrdBean)) {
                        commit();
                        // Add Start 2018/01/19 QC#23598
                        // somDsImptOrdBean.aquNum is equal somDsImptOrdBean.ordSrcRefNum.
                        if (ZYPCommonFunc.hasValue(somDsImptOrdBean.aquNum)) {
                            insertDocMgtScanTrx(somDsImptOrdBean.aquNum.getValue());
                        }
                        // Add End 2018/01/19 QC#23598
                    } else {
                        rollback();
                        isError = true;
                    }

                    if (isError) {
                        this.totalErrCount++;
                    } else {
                        this.totalNmlCount++;
                    }
                    // Mod End 2017/08/04 QC#19839
                }
                s21tta.endIntegrationProcess(this.interfaceId, transactionIds[i]);
            }
        } finally {
            writeEndLogLn("registDsImptTable", interfaceId);
        }
    }

    private boolean insertImptTables(SomDsImptOrdBean somDsImptOrdBean) {

        // *********************************************************************
        // Header
        // *********************************************************************
        insertImptTable((DS_IMPT_ORDTMsg) somDsImptOrdBean);

        insertImptTable((DS_IMPT_ORD_DELY_INFOTMsg) somDsImptOrdBean.dsImptOrdDelyInfoBean);
        insertImptTable((DS_IMPT_ORD_ISTL_INFOTMsg) somDsImptOrdBean.dsImptOrdIstlInfoBean);
//        insertImptTable((DS_IMPT_ORD_SITE_SRVYTMsg) somDsImptOrdBean.dsImptOrdSiteSrvyBean); // 2017/09/13 QC#21084 Del

        for (SomDsImptOrdSomApvlBean apvlBean : somDsImptOrdBean.dsImptOrdSomApvlBeanList) {
            insertImptTable((DS_IMPT_ORD_SOM_APVLTMsg) apvlBean);

            for (SomDsImptOrdApvlNoteBean apvlNoteBean : apvlBean.dsImptOrdApvlNoteBeanList) {
                insertImptTable((DS_IMPT_ORD_APVL_NOTETMsg) apvlNoteBean);
            }
        }

        for (SomDsImptOrdSomPrftBean prftBean : somDsImptOrdBean.dsImptOrdSomPrftBeanList) {
            insertImptTable((DS_IMPT_ORD_SOM_PRFTTMsg) prftBean);
        }

        if (somDsImptOrdBean.dsImptOrdSlsCrBean != null) {
            insertImptTable((DS_IMPT_ORD_SLS_CRTMsg) somDsImptOrdBean.dsImptOrdSlsCrBean);

            for (SomDsImptOrdSlsCrBean slsCrBean : somDsImptOrdBean.dsImptOrdSlsCrBean.dsImptOrdSlsCrChildBeanList) {
                insertImptTable((DS_IMPT_ORD_SLS_CRTMsg) slsCrBean);
            }
        }

        for (SomDsImptOrdCtacPsnBean ctacPsnBean : somDsImptOrdBean.dsImptOrdCtacPsnBeanList) {
            if (ZYPCommonFunc.hasValue(ctacPsnBean.ctacPsnFirstNm)) {
                insertImptTable((DS_IMPT_ORD_CTAC_PSNTMsg) ctacPsnBean);
            }
        }

        for (SomDsImptOrdConfigBean configBean : somDsImptOrdBean.dsImptOrdConfigBeanList) {
            // *****************************************************************
            // Config
            // *****************************************************************
            insertImptTable((DS_IMPT_ORD_CONFIGTMsg) configBean);

            if (configBean.dsImptOrdSlsCrBean != null) {
                insertImptTable((DS_IMPT_ORD_SLS_CRTMsg) configBean.dsImptOrdSlsCrBean);

                for (SomDsImptOrdSlsCrBean slsCrBean : configBean.dsImptOrdSlsCrBean.dsImptOrdSlsCrChildBeanList) {
                    insertImptTable((DS_IMPT_ORD_SLS_CRTMsg) slsCrBean);
                }
            }

            for (SomDsImptOrdCtacPsnBean ctacPsnBean : configBean.dsImptOrdCtacPsnBeanList) {
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

            if (configBean.dsImptOrdIstlInfoBean != null) {
//                insertImptTable((DS_IMPT_ORD_SITE_SRVYTMsg) configBean.dsImptOrdSiteSrvyBean); // 2017/09/13 QC#21084 Del
            }

            // *****************************************************************
            // Detail
            // *****************************************************************
            for (SomDsImptOrdDtlBean dtlBean : configBean.dsImptOrdDtlBeanList) {
                insertImptTable((DS_IMPT_ORD_DTLTMsg) dtlBean);

                for (SomDsImptPrcCalcBaseBean prcCalcBaseBean : dtlBean.dsImptPrcCalcBaseBeanList) {
                    insertImptTable((DS_IMPT_PRC_CALC_BASETMsg) prcCalcBaseBean);
                }
            }

            // *****************************************************************
            // RMA
            // *****************************************************************
            for (SomDsImptOrdRtrnDtlBean rtrnDtlBean : configBean.dsImptOrdDtlRtrnBeanList) {
                insertImptTable((DS_IMPT_ORD_RTRN_DTLTMsg) rtrnDtlBean);

                for (SomDsImptRtrnPrcCalcBean rtrnPrcCalcBean : rtrnDtlBean.dsImptRtrnPrcCalcBeanList) {
                    insertImptTable((DS_IMPT_RTRN_PRC_CALCTMsg) rtrnPrcCalcBean);
                }
            }
        }

        // *****************************************************************
        // Shell
        // *****************************************************************
        for (SomDsImptSvcDtlBean svcDtlBean : somDsImptOrdBean.dsImptSvcDtlBeanList) {
            insertImptTable((DS_IMPT_SVC_DTLTMsg) svcDtlBean);

            for (SomDsImptSvcConfigRefBean svcConfigRefBean : svcDtlBean.dsImptSvcConfigRefBeanList) {
                insertImptTable((DS_IMPT_SVC_CONFIG_REFTMsg) svcConfigRefBean);
            }

            for (SomDsImptSvcPrcBean svcPrcBean : svcDtlBean.dsImptSvcPrcBeanList) {
                insertImptTable((DS_IMPT_SVC_PRCTMsg) svcPrcBean);

                for (SomDsImptSvcUsgPrcBean svcUsgPrcBean : svcPrcBean.dsImptSvcUsgPrcBeanList) {
                    insertImptTable((DS_IMPT_SVC_USG_PRCTMsg) svcUsgPrcBean);
                }
            }

            for (SomDsImptSvcAddlBaseBean svcAddlBaseBean : svcDtlBean.dsImptSvcAddlBaseBeanList) {
                insertImptTable((DS_IMPT_SVC_ADDL_BASETMsg) svcAddlBaseBean);
            }

            for (SomDsImptSvcAddlChrgBean svcAddlBaseBean : svcDtlBean.dsImptSvcAddlChrgBeanList) {
                insertImptTable((DS_IMPT_SVC_ADDL_CHRGTMsg) svcAddlBaseBean);
            }
        }

        return (somDsImptOrdBean.getErrorInfo().size() == 0);
    }

    private void insertImptTable(EZDTMsg inMsg) {
        S21FastTBLAccessor.insert(inMsg);
        NWXC220001Util.checkTMsgDbAccess(inMsg, false);
    }

    /**
     * @param transactionId
     * @return
     */
    private List<NWAI4120_01TMsg> getNwai412001(BigDecimal transactionId) {

        List<NWAI4120_01TMsg> msgList = new ArrayList<NWAI4120_01TMsg>();
        Map<String, Object> ssmParamNWAI412001 = new HashMap<String, Object>();
        ssmParamNWAI412001.put("interfaceId", this.interfaceId);
        ssmParamNWAI412001.put("transactionId", transactionId);
        ssmParamNWAI412001.put("glblCmpyCd", glblCmpyCd);
        ssmParamNWAI412001.put("cpoSrcTpCd", CPO_SRC_TP.SOM);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai412001TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4120_01", ssmParamNWAI412001);

        for (Map<String, Object> nwai412001TMsgMap : nwai412001TMsgList) {
            NWAI4120_01TMsg nwai412001TMsg = new NWAI4120_01TMsg();

            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.interfaceId, (String) nwai412001TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.transactionId, (BigDecimal) nwai412001TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.segmentId, (BigDecimal) nwai412001TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.unitId, (BigDecimal) nwai412001TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.seqNumber, (BigDecimal) nwai412001TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somQuoteId, (BigDecimal) nwai412001TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somQuoteNum, (BigDecimal) nwai412001TMsgMap.get("SOM_QUOTE_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somShipToCustId, (BigDecimal) nwai412001TMsgMap.get("SOM_SHIP_TO_CUST_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somShipToPtyNum, (BigDecimal) nwai412001TMsgMap.get("SOM_SHIP_TO_PTY_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somFmDeptId, (BigDecimal) nwai412001TMsgMap.get("SOM_FM_DEPT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somDiscAmt, (BigDecimal) nwai412001TMsgMap.get("SOM_DISC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somLeaseTermAot, (BigDecimal) nwai412001TMsgMap.get("SOM_LEASE_TERM_AOT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somStepCnt, (BigDecimal) nwai412001TMsgMap.get("SOM_STEP_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somSubSvcAot, (BigDecimal) nwai412001TMsgMap.get("SOM_SUB_SVC_AOT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somPrcListId, (BigDecimal) nwai412001TMsgMap.get("SOM_PRC_LIST_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.negoDealAmt, (BigDecimal) nwai412001TMsgMap.get("NEGO_DEAL_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leasePmtAmt, (BigDecimal) nwai412001TMsgMap.get("LEASE_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseSvcPmtAmt, (BigDecimal) nwai412001TMsgMap.get("LEASE_SVC_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseEquipPmtAmt, (BigDecimal) nwai412001TMsgMap.get("LEASE_EQUIP_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseUpgAmt, (BigDecimal) nwai412001TMsgMap.get("LEASE_UPG_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somRevAmt, (BigDecimal) nwai412001TMsgMap.get("SOM_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.istlFeeAmt, (BigDecimal) nwai412001TMsgMap.get("ISTL_FEE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somRebAmt, (BigDecimal) nwai412001TMsgMap.get("SOM_REB_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseDownPmtAmt, (BigDecimal) nwai412001TMsgMap.get("LEASE_DOWN_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseInEligFinAmt, (BigDecimal) nwai412001TMsgMap.get("LEASE_IN_ELIG_FIN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseCrAmt, (BigDecimal) nwai412001TMsgMap.get("LEASE_CR_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.delyChrgAmt, (BigDecimal) nwai412001TMsgMap.get("DELY_CHRG_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somDepAmt, (BigDecimal) nwai412001TMsgMap.get("SOM_DEP_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.secDepAmt, (BigDecimal) nwai412001TMsgMap.get("SEC_DEP_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.cratTs, (String) nwai412001TMsgMap.get("CRAT_TS"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.erlstDelyTs, (String) nwai412001TMsgMap.get("ERLST_DELY_TS"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.lastUpdTs, (String) nwai412001TMsgMap.get("LAST_UPD_TS"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somLeaseRate, (BigDecimal) nwai412001TMsgMap.get("SOM_LEASE_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseTaxRate, (BigDecimal) nwai412001TMsgMap.get("LEASE_TAX_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseSoftCostRate, (BigDecimal) nwai412001TMsgMap.get("LEASE_SOFT_COST_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseInEligRate, (BigDecimal) nwai412001TMsgMap.get("LEASE_IN_ELIG_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.salesTaxRate, (BigDecimal) nwai412001TMsgMap.get("SALES_TAX_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somRebRate, (BigDecimal) nwai412001TMsgMap.get("SOM_REB_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somQuoteNm, (String) nwai412001TMsgMap.get("SOM_QUOTE_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somQuoteStsNm, (String) nwai412001TMsgMap.get("SOM_QUOTE_STS_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somSlsRepId, (String) nwai412001TMsgMap.get("SOM_SLS_REP_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somSlsRepLastNm, (String) nwai412001TMsgMap.get("SOM_SLS_REP_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somSlsRepFirstNm, (String) nwai412001TMsgMap.get("SOM_SLS_REP_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somBrNm, (String) nwai412001TMsgMap.get("SOM_BR_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somAcctMgrTxt, (String) nwai412001TMsgMap.get("SOM_ACCT_MGR_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.rebMercCd, (String) nwai412001TMsgMap.get("REB_MERC_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.csaPmtFirstNm, (String) nwai412001TMsgMap.get("CSA_PMT_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.csaPmtLastNm, (String) nwai412001TMsgMap.get("CSA_PMT_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.csaPmtPhoTxt, (String) nwai412001TMsgMap.get("CSA_PMT_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.csaPmtPhoExtTxt, (String) nwai412001TMsgMap.get("CSA_PMT_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.csaPmtEmlTxt, (String) nwai412001TMsgMap.get("CSA_PMT_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.csaPmtTpTxt, (String) nwai412001TMsgMap.get("CSA_PMT_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.maintBllgStTxt, (String) nwai412001TMsgMap.get("MAINT_BLLG_ST_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.maintBllgZipTxt, (String) nwai412001TMsgMap.get("MAINT_BLLG_ZIP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.maintBllgCntyTxt, (String) nwai412001TMsgMap.get("MAINT_BLLG_CNTY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.bidPrcListNm, (String) nwai412001TMsgMap.get("BID_PRC_LIST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.delyFaxTxt, (String) nwai412001TMsgMap.get("DELY_FAX_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.maintPoVrsnTxt, (String) nwai412001TMsgMap.get("MAINT_PO_VRSN_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.maintBillToLastNm, (String) nwai412001TMsgMap.get("MAINT_BILL_TO_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.maintBillToPhoTxt, (String) nwai412001TMsgMap.get("MAINT_BILL_TO_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.maintBillToPhoExtTxt, (String) nwai412001TMsgMap.get("MAINT_BILL_TO_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.maintBillToEmlTxt, (String) nwai412001TMsgMap.get("MAINT_BILL_TO_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.maintBllgAddrDescTxt, (String) nwai412001TMsgMap.get("MAINT_BLLG_ADDR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.maintBllgCityDescTxt, (String) nwai412001TMsgMap.get("MAINT_BLLG_CITY_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.subSvcCtacPhoTxt, (String) nwai412001TMsgMap.get("SUB_SVC_CTAC_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.subSvcCtacEmlTxt, (String) nwai412001TMsgMap.get("SUB_SVC_CTAC_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.markViewBrcdTxt, (String) nwai412001TMsgMap.get("MARK_VIEW_BRCD_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somSpiffDtlTxt_01, (String) nwai412001TMsgMap.get("SOM_SPIFF_DTL_TXT_01"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.maintBillIndSomTxt, (String) nwai412001TMsgMap.get("MAINT_BILL_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.maintBillToFirstNm, (String) nwai412001TMsgMap.get("MAINT_BILL_TO_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.chkAgmtIndSomTxt, (String) nwai412001TMsgMap.get("CHK_AGMT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.realFairIndSomTxt, (String) nwai412001TMsgMap.get("REAL_FAIR_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.subSvcPmtTermTxt, (String) nwai412001TMsgMap.get("SUB_SVC_PMT_TERM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.subSvcAgmtTermTxt, (String) nwai412001TMsgMap.get("SUB_SVC_AGMT_TERM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.subSvcCtacFirstNm, (String) nwai412001TMsgMap.get("SUB_SVC_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.subSvcCtacLastNm, (String) nwai412001TMsgMap.get("SUB_SVC_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.qtlyPmtTxt, (String) nwai412001TMsgMap.get("QTLY_PMT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.othLeaseIndSomTxt, (String) nwai412001TMsgMap.get("OTH_LEASE_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.othPmtLeaseTxt, (String) nwai412001TMsgMap.get("OTH_PMT_LEASE_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.achRteCd, (String) nwai412001TMsgMap.get("ACH_RTE_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.achAcctNum, (String) nwai412001TMsgMap.get("ACH_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.nonRfIndSomTxt, (String) nwai412001TMsgMap.get("NON_RF_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.delyInstnTxt, (String) nwai412001TMsgMap.get("DELY_INSTN_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.zeroDueIndSomTxt, (String) nwai412001TMsgMap.get("ZERO_DUE_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.svcPrvdTxt, (String) nwai412001TMsgMap.get("SVC_PRVD_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseTpTxt, (String) nwai412001TMsgMap.get("LEASE_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseFaxIndSomTxt, (String) nwai412001TMsgMap.get("LEASE_FAX_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.mthPmtIndSomTxt, (String) nwai412001TMsgMap.get("MTH_PMT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.taxExemIndSomTxt, (String) nwai412001TMsgMap.get("TAX_EXEM_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.dclnMaintIndSomTxt, (String) nwai412001TMsgMap.get("DCLN_MAINT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.shipViaInstnTxt, (String) nwai412001TMsgMap.get("SHIP_VIA_INSTN_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.hrsOpTxt, (String) nwai412001TMsgMap.get("HRS_OP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.elevIndSomTxt, (String) nwai412001TMsgMap.get("ELEV_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.loadDockIndSomTxt, (String) nwai412001TMsgMap.get("LOAD_DOCK_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.itPhoTxt, (String) nwai412001TMsgMap.get("IT_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.itPhoExtTxt, (String) nwai412001TMsgMap.get("IT_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.itEmlTxt, (String) nwai412001TMsgMap.get("IT_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.itCtacDeptTxt, (String) nwai412001TMsgMap.get("IT_CTAC_DEPT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.poReqIndSomTxt, (String) nwai412001TMsgMap.get("PO_REQ_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somPoNum, (String) nwai412001TMsgMap.get("SOM_PO_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.delyFirstNm, (String) nwai412001TMsgMap.get("DELY_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.delyPhoTxt, (String) nwai412001TMsgMap.get("DELY_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.delyPhoExtTxt, (String) nwai412001TMsgMap.get("DELY_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.delyEmlTxt, (String) nwai412001TMsgMap.get("DELY_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.itLastNm, (String) nwai412001TMsgMap.get("IT_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.itFirstNm, (String) nwai412001TMsgMap.get("IT_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.othPmtTxtAquTxt, (String) nwai412001TMsgMap.get("OTH_PMT_TXT_AQU_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somChkNum, (String) nwai412001TMsgMap.get("SOM_CHK_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somCrCardNum, (String) nwai412001TMsgMap.get("SOM_CR_CARD_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somCrCardExprTxt, (String) nwai412001TMsgMap.get("SOM_CR_CARD_EXPR_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somCrCardNm, (String) nwai412001TMsgMap.get("SOM_CR_CARD_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.delyLastNm, (String) nwai412001TMsgMap.get("DELY_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.billToFaxTxt, (String) nwai412001TMsgMap.get("BILL_TO_FAX_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.billToEmlTxt, (String) nwai412001TMsgMap.get("BILL_TO_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.chkOrdIndSomTxt, (String) nwai412001TMsgMap.get("CHK_ORD_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.trxTpTxt, (String) nwai412001TMsgMap.get("TRX_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.netThrtyIndSomTxt, (String) nwai412001TMsgMap.get("NET_THRTY_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.othAquIndSomTxt, (String) nwai412001TMsgMap.get("OTH_AQU_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.istlBrDescTxt, (String) nwai412001TMsgMap.get("ISTL_BR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somSpiffDtlTxt_01, (String) nwai412001TMsgMap.get("SOM_SPIFF_DTL_TXT_02"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.billToLastNm, (String) nwai412001TMsgMap.get("BILL_TO_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.billToFirstNm, (String) nwai412001TMsgMap.get("BILL_TO_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.billToPhoTxt, (String) nwai412001TMsgMap.get("BILL_TO_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.billToPhoExtTxt, (String) nwai412001TMsgMap.get("BILL_TO_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leasePrchOrderTxt, (String) nwai412001TMsgMap.get("LEASE_PRCH_ORDER_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseCrApplyToTxt, (String) nwai412001TMsgMap.get("LEASE_CR_APPLY_TO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.istlRepNm, (String) nwai412001TMsgMap.get("ISTL_REP_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.istlRepTxt, (String) nwai412001TMsgMap.get("ISTL_REP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.istlBrDrctrNm, (String) nwai412001TMsgMap.get("ISTL_BR_DRCTR_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.istlBrDrctrNum, (String) nwai412001TMsgMap.get("ISTL_BR_DRCTR_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseEquipTpTxt, (String) nwai412001TMsgMap.get("LEASE_EQUIP_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.cpcRntlIndSomTxt, (String) nwai412001TMsgMap.get("CPC_RNTL_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseRgIndSomTxt, (String) nwai412001TMsgMap.get("LEASE_RG_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseTrtyIndSomTxt, (String) nwai412001TMsgMap.get("LEASE_TRTY_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseCusaChTxt, (String) nwai412001TMsgMap.get("LEASE_CUSA_CH_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseAppTxt, (String) nwai412001TMsgMap.get("LEASE_APP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.bllgCityDescTxt, (String) nwai412001TMsgMap.get("BLLG_CITY_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.bllgStTxt, (String) nwai412001TMsgMap.get("BLLG_ST_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.bllgZipTxt, (String) nwai412001TMsgMap.get("BLLG_ZIP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseCmpyNm, (String) nwai412001TMsgMap.get("LEASE_CMPY_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseSvcIndSomTxt, (String) nwai412001TMsgMap.get("LEASE_SVC_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.leaseTaxIndSomTxt, (String) nwai412001TMsgMap.get("LEASE_TAX_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.cusaChTxt, (String) nwai412001TMsgMap.get("CUSA_CH_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.svcInclIndSomTxt, (String) nwai412001TMsgMap.get("SVC_INCL_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.billToCustLegalNm, (String) nwai412001TMsgMap.get("BILL_TO_CUST_LEGAL_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.billToDbaTxt, (String) nwai412001TMsgMap.get("BILL_TO_DBA_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.bllgCmpyTxt, (String) nwai412001TMsgMap.get("BLLG_CMPY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.bllgAddrDescTxt, (String) nwai412001TMsgMap.get("BLLG_ADDR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.shipToCntyTxt, (String) nwai412001TMsgMap.get("SHIP_TO_CNTY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somShipToZipCd, (String) nwai412001TMsgMap.get("SOM_SHIP_TO_ZIP_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.shipToCustPtySiteNum, (String) nwai412001TMsgMap.get("SHIP_TO_CUST_PTY_SITE_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.shipToCrmSlsId, (String) nwai412001TMsgMap.get("SHIP_TO_CRM_SLS_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.shipToCrmSlsNum, (String) nwai412001TMsgMap.get("SHIP_TO_CRM_SLS_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.prcListDescTxt, (String) nwai412001TMsgMap.get("PRC_LIST_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.shipToAcctNum, (String) nwai412001TMsgMap.get("SHIP_TO_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.shipToCustLegalNm, (String) nwai412001TMsgMap.get("SHIP_TO_CUST_LEGAL_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somShipToCustNm, (String) nwai412001TMsgMap.get("SOM_SHIP_TO_CUST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.shipToAddrDescTxt_01, (String) nwai412001TMsgMap.get("SHIP_TO_ADDR_DESC_TXT_01"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.shipToAddrDescTxt_02, (String) nwai412001TMsgMap.get("SHIP_TO_ADDR_DESC_TXT_02"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.shipToCityTxt, (String) nwai412001TMsgMap.get("SHIP_TO_CITY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.cratByTxt, (String) nwai412001TMsgMap.get("CRAT_BY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.shipToStTxt, (String) nwai412001TMsgMap.get("SHIP_TO_ST_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.prcContrNum, (String) nwai412001TMsgMap.get("PRC_CONTR_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.prcContrNm, (String) nwai412001TMsgMap.get("PRC_CONTR_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.prcContrPk, (BigDecimal) nwai412001TMsgMap.get("PRC_CONTR_PK"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somCsmpNum, (String) nwai412001TMsgMap.get("SOM_CSMP_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.csmpCrAmt, (BigDecimal) nwai412001TMsgMap.get("CSMP_CR_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.custIssPoDt, (String) nwai412001TMsgMap.get("CUST_ISS_PO_DT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.csmpIndSomTxt, (String) nwai412001TMsgMap.get("CSMP_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412001TMsg.somMaintPoNum, (String) nwai412001TMsgMap.get("SOM_MAINT_PO_NUM")); //QC#19768

            msgList.add(nwai412001TMsg);
        }
        return msgList;
    }

    /**
     * @param transactionId
     * @param somQuoteId
     * @return
     */
    private List<NWAI4120_02TMsg> getNwai412002(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4120_02TMsg> msgList = new ArrayList<NWAI4120_02TMsg>();
        Map<String, Object> ssmParamNWAI412002 = new HashMap<String, Object>();
        ssmParamNWAI412002.put("interfaceId", this.interfaceId);
        ssmParamNWAI412002.put("transactionId", transactionId);
        ssmParamNWAI412002.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai412002TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4120_02", ssmParamNWAI412002);

        for (Map<String, Object> nwai412002TMsgMap : nwai412002TMsgList) {
            NWAI4120_02TMsg nwai412002TMsg = new NWAI4120_02TMsg();

            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.interfaceId, (String) nwai412002TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.transactionId, (BigDecimal) nwai412002TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.segmentId, (BigDecimal) nwai412002TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.unitId, (BigDecimal) nwai412002TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.seqNumber, (BigDecimal) nwai412002TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.somApvlId, (BigDecimal) nwai412002TMsgMap.get("SOM_APVL_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.somQuoteId, (BigDecimal) nwai412002TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.somVrsnId, (BigDecimal) nwai412002TMsgMap.get("SOM_VRSN_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.apvlTpId, (BigDecimal) nwai412002TMsgMap.get("APVL_TP_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.wfStartTs, (String) nwai412002TMsgMap.get("WF_START_TS"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.wfEndTs, (String) nwai412002TMsgMap.get("WF_END_TS"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.somApvlTpTxt, (String) nwai412002TMsgMap.get("SOM_APVL_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.somStsTxt, (String) nwai412002TMsgMap.get("SOM_STS_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.somApvlDescTxt, (String) nwai412002TMsgMap.get("SOM_APVL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.shipToAddrKeyTxt, (String) nwai412002TMsgMap.get("SHIP_TO_ADDR_KEY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.splitRepTxt, (String) nwai412002TMsgMap.get("SPLIT_REP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.somApvrNm, (String) nwai412002TMsgMap.get("SOM_APVR_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.apvlActvTxt, (String) nwai412002TMsgMap.get("APVL_ACTV_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.wfStartUsrId, (String) nwai412002TMsgMap.get("WF_START_USR_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.wfEndUsrId, (String) nwai412002TMsgMap.get("WF_END_USR_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412002TMsg.apvlAddUsrId, (String) nwai412002TMsgMap.get("APVL_ADD_USR_ID"));

            msgList.add(nwai412002TMsg);
        }
        return msgList;
    }

    /**
     * @param transactionId
     * @param somQuoteId
     * @return
     */
    private List<NWAI4120_03TMsg> getNwai412003(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4120_03TMsg> msgList = new ArrayList<NWAI4120_03TMsg>();
        Map<String, Object> ssmParamNWAI412003 = new HashMap<String, Object>();
        ssmParamNWAI412003.put("interfaceId", this.interfaceId);
        ssmParamNWAI412003.put("transactionId", transactionId);
        ssmParamNWAI412003.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai412003TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4120_03", ssmParamNWAI412003);

        for (Map<String, Object> nwai412003TMsgMap : nwai412003TMsgList) {
            NWAI4120_03TMsg nwai412003TMsg = new NWAI4120_03TMsg();

            ZYPEZDItemValueSetter.setValue(nwai412003TMsg.interfaceId, (String) nwai412003TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412003TMsg.transactionId, (BigDecimal) nwai412003TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412003TMsg.segmentId, (BigDecimal) nwai412003TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412003TMsg.unitId, (BigDecimal) nwai412003TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412003TMsg.seqNumber, (BigDecimal) nwai412003TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai412003TMsg.somApvlNoteId, (BigDecimal) nwai412003TMsgMap.get("SOM_APVL_NOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412003TMsg.somApvlId, (BigDecimal) nwai412003TMsgMap.get("SOM_APVL_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412003TMsg.apvlAddTs, (String) nwai412003TMsgMap.get("APVL_ADD_TS"));
            ZYPEZDItemValueSetter.setValue(nwai412003TMsg.apvlNoteTxt, (String) nwai412003TMsgMap.get("APVL_NOTE_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412003TMsg.apvlAddUsrId, (String) nwai412003TMsgMap.get("APVL_ADD_USR_ID"));

            msgList.add(nwai412003TMsg);
        }
        return msgList;
    }

    /**
     * @param transactionId
     * @param somQuoteId
     * @return
     */
    private List<NWAI4120_04TMsg> getNwai412004(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4120_04TMsg> msgList = new ArrayList<NWAI4120_04TMsg>();
        Map<String, Object> ssmParamNWAI412004 = new HashMap<String, Object>();
        ssmParamNWAI412004.put("interfaceId", this.interfaceId);
        ssmParamNWAI412004.put("transactionId", transactionId);
        ssmParamNWAI412004.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai412004TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4120_04", ssmParamNWAI412004);

        for (Map<String, Object> nwai412004TMsgMap : nwai412004TMsgList) {
            NWAI4120_04TMsg nwai412004TMsg = new NWAI4120_04TMsg();

            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.interfaceId, (String) nwai412004TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.transactionId, (BigDecimal) nwai412004TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.segmentId, (BigDecimal) nwai412004TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.unitId, (BigDecimal) nwai412004TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.seqNumber, (BigDecimal) nwai412004TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somQuoteId, (BigDecimal) nwai412004TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somCmsnAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_CMSN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.svcCostAmt, (BigDecimal) nwai412004TMsgMap.get("SVC_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.svcCostPmtAmt, (BigDecimal) nwai412004TMsgMap.get("SVC_COST_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.svcCostTrnsfAmt, (BigDecimal) nwai412004TMsgMap.get("SVC_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somMkupAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_MKUP_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.ordTotAmt, (BigDecimal) nwai412004TMsgMap.get("ORD_TOT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somTotRevAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_TOT_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somTotEarnAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_TOT_EARN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.interRgEarnAmt, (BigDecimal) nwai412004TMsgMap.get("INTER_RG_EARN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.interTrtyRevAmt, (BigDecimal) nwai412004TMsgMap.get("INTER_TRTY_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.interTrtyEarnAmt, (BigDecimal) nwai412004TMsgMap.get("INTER_TRTY_EARN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.spiffRevAmt, (BigDecimal) nwai412004TMsgMap.get("SPIFF_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.spiffEarnAmt, (BigDecimal) nwai412004TMsgMap.get("SPIFF_EARN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.unitSoldEarnAmt, (BigDecimal) nwai412004TMsgMap.get("UNIT_SOLD_EARN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.sbscrSvcAmt, (BigDecimal) nwai412004TMsgMap.get("SBSCR_SVC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.gpEquipRevAmt, (BigDecimal) nwai412004TMsgMap.get("GP_EQUIP_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.gpEquipEarnAmt, (BigDecimal) nwai412004TMsgMap.get("GP_EQUIP_EARN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.sbscrSvcRevAmt, (BigDecimal) nwai412004TMsgMap.get("SBSCR_SVC_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.sbscrSvcEarnAmt, (BigDecimal) nwai412004TMsgMap.get("SBSCR_SVC_EARN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.interRgRevAmt, (BigDecimal) nwai412004TMsgMap.get("INTER_RG_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.repEquipRevAmt, (BigDecimal) nwai412004TMsgMap.get("REP_EQUIP_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.gpEquipAmt, (BigDecimal) nwai412004TMsgMap.get("GP_EQUIP_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.extFlPrcAmt, (BigDecimal) nwai412004TMsgMap.get("EXT_FL_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somPrmoAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_PRMO_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somTradeInAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_TRADE_IN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somTaxAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_TAX_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somCbsInvAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_CBS_INV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somFinalFlAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_FINAL_FL_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.gpCostTrnsfAmt, (BigDecimal) nwai412004TMsgMap.get("GP_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.totRepRevAmt, (BigDecimal) nwai412004TMsgMap.get("TOT_REP_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.sbscrSvcRepRevAmt, (BigDecimal) nwai412004TMsgMap.get("SBSCR_SVC_REP_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somByotFinAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_BYOT_FIN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.miscNonTaxAbleAmt, (BigDecimal) nwai412004TMsgMap.get("MISC_NON_TAX_ABLE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somOthEquipAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_OTH_EQUIP_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.msrpListPrcAmt, (BigDecimal) nwai412004TMsgMap.get("MSRP_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somTaxAbleAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_TAX_ABLE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.nonTaxAbleAmt, (BigDecimal) nwai412004TMsgMap.get("NON_TAX_ABLE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somTotFinAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_TOT_FIN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somEquipFinAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_EQUIP_FIN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somEquipPayAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_EQUIP_PAY_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somSvcFinAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_SVC_FIN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somSplyFinAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_SPLY_FIN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somUpgFinAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_UPG_FIN_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somPctMkupRate, (BigDecimal) nwai412004TMsgMap.get("SOM_PCT_MKUP_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somSoftPctCostRate, (BigDecimal) nwai412004TMsgMap.get("SOM_SOFT_PCT_COST_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somFinVsListRate, (BigDecimal) nwai412004TMsgMap.get("SOM_FIN_VS_LIST_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.gpCostPctTrnsfRate, (BigDecimal) nwai412004TMsgMap.get("GP_COST_PCT_TRNSF_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.gpPctEquipRate, (BigDecimal) nwai412004TMsgMap.get("GP_PCT_EQUIP_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.unitSoldRevAmt, (BigDecimal) nwai412004TMsgMap.get("UNIT_SOLD_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.gpWotCostTrnsfAmt, (BigDecimal) nwai412004TMsgMap.get("GP_WOT_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.flAdjAmt, (BigDecimal) nwai412004TMsgMap.get("FL_ADJ_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somSvcRevTrnsfAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.somTotCostTrnsfAmt, (BigDecimal) nwai412004TMsgMap.get("SOM_TOT_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.proSvcAmt, (BigDecimal) nwai412004TMsgMap.get("PRO_SVC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412004TMsg.gpWotCostTrnsfRate, (BigDecimal) nwai412004TMsgMap.get("GP_WOT_COST_TRNSF_RATE")); //QC#17120 add

            msgList.add(nwai412004TMsg);
        }
        return msgList;
    }

    /**
     * @param transactionId
     * @param somQuoteId
     * @return
     */
    private List<NWAI4120_05TMsg> getNwai412005(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4120_05TMsg> msgList = new ArrayList<NWAI4120_05TMsg>();
        Map<String, Object> ssmParamNWAI412005 = new HashMap<String, Object>();
        ssmParamNWAI412005.put("interfaceId", this.interfaceId);
        ssmParamNWAI412005.put("transactionId", transactionId);
        ssmParamNWAI412005.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai412005TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4120_05", ssmParamNWAI412005);

        for (Map<String, Object> nwai412005TMsgMap : nwai412005TMsgList) {
            NWAI4120_05TMsg nwai412005TMsg = new NWAI4120_05TMsg();

            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.interfaceId, (String) nwai412005TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.transactionId, (BigDecimal) nwai412005TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.segmentId, (BigDecimal) nwai412005TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.unitId, (BigDecimal) nwai412005TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.seqNumber, (BigDecimal) nwai412005TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somQuoteId, (BigDecimal) nwai412005TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somSoftCostId, (BigDecimal) nwai412005TMsgMap.get("SOM_SOFT_COST_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somOrdQty, (BigDecimal) nwai412005TMsgMap.get("SOM_ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.mtrReadCnt, (BigDecimal) nwai412005TMsgMap.get("MTR_READ_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somStepCnt, (BigDecimal) nwai412005TMsgMap.get("SOM_STEP_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somPrcAmt, (BigDecimal) nwai412005TMsgMap.get("SOM_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somExtPrcAmt, (BigDecimal) nwai412005TMsgMap.get("SOM_EXT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.erlstDelyTs, (String) nwai412005TMsgMap.get("ERLST_DELY_TS"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somElevCnt, (BigDecimal) nwai412005TMsgMap.get("SOM_ELEV_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.loadDockCnt, (BigDecimal) nwai412005TMsgMap.get("LOAD_DOCK_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somTaxRate, (BigDecimal) nwai412005TMsgMap.get("SOM_TAX_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somMercItemCd, (String) nwai412005TMsgMap.get("SOM_MERC_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somItemDescTxt, (String) nwai412005TMsgMap.get("SOM_ITEM_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somDescTxt, (String) nwai412005TMsgMap.get("SOM_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somMdlTxt, (String) nwai412005TMsgMap.get("SOM_MDL_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.quoteLineTpTxt, (String) nwai412005TMsgMap.get("QUOTE_LINE_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somDescSerNum, (String) nwai412005TMsgMap.get("SOM_DESC_SER_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somByotStCd, (String) nwai412005TMsgMap.get("SOM_BYOT_ST_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somByotZipCd, (String) nwai412005TMsgMap.get("SOM_BYOT_ZIP_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somByotLastNm, (String) nwai412005TMsgMap.get("SOM_BYOT_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somByotPhoTxt, (String) nwai412005TMsgMap.get("SOM_BYOT_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somByotPhoExtTxt, (String) nwai412005TMsgMap.get("SOM_BYOT_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somByotAddr_01, (String) nwai412005TMsgMap.get("SOM_BYOT_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somByotAddr_02, (String) nwai412005TMsgMap.get("SOM_BYOT_ADDR_02"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somByotCityTxt, (String) nwai412005TMsgMap.get("SOM_BYOT_CITY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somSoftCostDlrTxt, (String) nwai412005TMsgMap.get("SOM_SOFT_COST_DLR_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.mtrMethTxt, (String) nwai412005TMsgMap.get("MTR_METH_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.splitTpTxt, (String) nwai412005TMsgMap.get("SPLIT_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somByotNm, (String) nwai412005TMsgMap.get("SOM_BYOT_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somMnfTxt, (String) nwai412005TMsgMap.get("SOM_MNF_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somByotFirstNm, (String) nwai412005TMsgMap.get("SOM_BYOT_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.scdPhoTxt, (String) nwai412005TMsgMap.get("SCD_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.scdPhoExtTxt, (String) nwai412005TMsgMap.get("SCD_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.scdEmlTxt, (String) nwai412005TMsgMap.get("SCD_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.istlRepTxt, (String) nwai412005TMsgMap.get("ISTL_REP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.istlBrTxt, (String) nwai412005TMsgMap.get("ISTL_BR_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.brDrctrTxt, (String) nwai412005TMsgMap.get("BR_DRCTR_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.primCtacLastNm, (String) nwai412005TMsgMap.get("PRIM_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.primPhoTxt, (String) nwai412005TMsgMap.get("PRIM_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.primPhoExtTxt, (String) nwai412005TMsgMap.get("PRIM_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.primEmlTxt, (String) nwai412005TMsgMap.get("PRIM_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.scdCtacFirstNm, (String) nwai412005TMsgMap.get("SCD_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.scdCtacLastNm, (String) nwai412005TMsgMap.get("SCD_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somSoftCostCntyNm, (String) nwai412005TMsgMap.get("SOM_SOFT_COST_CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somSoftCostZipCd, (String) nwai412005TMsgMap.get("SOM_SOFT_COST_ZIP_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.shipViaTxt, (String) nwai412005TMsgMap.get("SHIP_VIA_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.hrsOpTxt, (String) nwai412005TMsgMap.get("HRS_OP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somSoftCostInstnTxt, (String) nwai412005TMsgMap.get("SOM_SOFT_COST_INSTN_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.primCtacFirstNm, (String) nwai412005TMsgMap.get("PRIM_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somCrmSlsTxt, (String) nwai412005TMsgMap.get("SOM_CRM_SLS_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somAcctNum, (String) nwai412005TMsgMap.get("SOM_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somSoftCostAddr_01, (String) nwai412005TMsgMap.get("SOM_SOFT_COST_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somSoftCostAddr_02, (String) nwai412005TMsgMap.get("SOM_SOFT_COST_ADDR_02"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somSoftCostCityTxt, (String) nwai412005TMsgMap.get("SOM_SOFT_COST_CITY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somSoftCostStCd, (String) nwai412005TMsgMap.get("SOM_SOFT_COST_ST_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.rtrnTpTxt, (String) nwai412005TMsgMap.get("RTRN_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.rtrnIstlTxt, (String) nwai412005TMsgMap.get("RTRN_ISTL_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.dtlDescTxt, (String) nwai412005TMsgMap.get("DTL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.cfsIndSomTxt, (String) nwai412005TMsgMap.get("CFS_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.nonCfsLeaseCmpyTxt, (String) nwai412005TMsgMap.get("NON_CFS_LEASE_CMPY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somPsnTxt, (String) nwai412005TMsgMap.get("SOM_PSN_TXT"));
            //QC#18306
            ZYPEZDItemValueSetter.setValue(nwai412005TMsg.somPrcTpTxt, (String) nwai412005TMsgMap.get("SOM_PRC_TP_TXT"));

            msgList.add(nwai412005TMsg);
        }
        return msgList;
    }

    /**
     * @param transactionId
     * @param somQuoteId
     * @return
     */
    private List<NWAI4120_06TMsg> getNwai412006(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4120_06TMsg> msgList = new ArrayList<NWAI4120_06TMsg>();
        Map<String, Object> ssmParamNWAI412006 = new HashMap<String, Object>();
        ssmParamNWAI412006.put("interfaceId", this.interfaceId);
        ssmParamNWAI412006.put("transactionId", transactionId);
        ssmParamNWAI412006.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai412006TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4120_06", ssmParamNWAI412006);

        for (Map<String, Object> nwai412006TMsgMap : nwai412006TMsgList) {
            NWAI4120_06TMsg nwai412006TMsg = new NWAI4120_06TMsg();

            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.interfaceId, (String) nwai412006TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.transactionId, (BigDecimal) nwai412006TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.segmentId, (BigDecimal) nwai412006TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.unitId, (BigDecimal) nwai412006TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.seqNumber, (BigDecimal) nwai412006TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.rtrnCmpyId, (BigDecimal) nwai412006TMsgMap.get("RTRN_CMPY_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.somQuoteId, (BigDecimal) nwai412006TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.somSoftCostItemId, (BigDecimal) nwai412006TMsgMap.get("SOM_SOFT_COST_ITEM_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.origOrdTs, (String) nwai412006TMsgMap.get("ORIG_ORD_TS"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.pickUpDtOthTs, (String) nwai412006TMsgMap.get("PICK_UP_DT_OTH_TS"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.mtrReadCnt, (BigDecimal) nwai412006TMsgMap.get("MTR_READ_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.rtrnOthTxt, (String) nwai412006TMsgMap.get("RTRN_OTH_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.pickUpDtSameTxt, (String) nwai412006TMsgMap.get("PICK_UP_DT_SAME_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.pickUpDtOthTxt, (String) nwai412006TMsgMap.get("PICK_UP_DT_OTH_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.pickUpCtacFirstNm, (String) nwai412006TMsgMap.get("PICK_UP_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.pickUpCtacLastNm, (String) nwai412006TMsgMap.get("PICK_UP_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.pickUpPhoTxt, (String) nwai412006TMsgMap.get("PICK_UP_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.somMdlTxt, (String) nwai412006TMsgMap.get("SOM_MDL_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.somLeaseNum, (String) nwai412006TMsgMap.get("SOM_LEASE_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.somLeaseCompTxt, (String) nwai412006TMsgMap.get("SOM_LEASE_COMP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.pickUpStCd, (String) nwai412006TMsgMap.get("PICK_UP_ST_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.pickUpCntyNm, (String) nwai412006TMsgMap.get("PICK_UP_CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.pickUpZipCd, (String) nwai412006TMsgMap.get("PICK_UP_ZIP_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.somDescSerNum, (String) nwai412006TMsgMap.get("SOM_DESC_SER_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.somPsnDescTxt, (String) nwai412006TMsgMap.get("SOM_PSN_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.somTpDescTxt, (String) nwai412006TMsgMap.get("SOM_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.pickUpPhoExtTxt, (String) nwai412006TMsgMap.get("PICK_UP_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.pickUpEmlTxt, (String) nwai412006TMsgMap.get("PICK_UP_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.rmvInstnTxt, (String) nwai412006TMsgMap.get("RMV_INSTN_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.pickUpAddr_01, (String) nwai412006TMsgMap.get("PICK_UP_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.pickUpAddr_02, (String) nwai412006TMsgMap.get("PICK_UP_ADDR_02"));
            ZYPEZDItemValueSetter.setValue(nwai412006TMsg.pickUpCityAddr, (String) nwai412006TMsgMap.get("PICK_UP_CITY_ADDR"));

            msgList.add(nwai412006TMsg);
        }
        return msgList;
    }

    /**
     * @param transactionId
     * @param somQuoteId
     * @return
     */
    private List<NWAI4120_08TMsg> getNwai412008(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4120_08TMsg> msgList = new ArrayList<NWAI4120_08TMsg>();
        Map<String, Object> ssmParamNWAI412008 = new HashMap<String, Object>();
        ssmParamNWAI412008.put("interfaceId", this.interfaceId);
        ssmParamNWAI412008.put("transactionId", transactionId);
        ssmParamNWAI412008.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai412008TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4120_08", ssmParamNWAI412008);

        for (Map<String, Object> nwai412008TMsgMap : nwai412008TMsgList) {
            NWAI4120_08TMsg nwai412008TMsg = new NWAI4120_08TMsg();

            ZYPEZDItemValueSetter.setValue(nwai412008TMsg.interfaceId, (String) nwai412008TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412008TMsg.transactionId, (BigDecimal) nwai412008TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412008TMsg.segmentId, (BigDecimal) nwai412008TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412008TMsg.unitId, (BigDecimal) nwai412008TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412008TMsg.seqNumber, (BigDecimal) nwai412008TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai412008TMsg.somDtlId, (BigDecimal) nwai412008TMsgMap.get("SOM_DTL_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412008TMsg.somQuoteId, (BigDecimal) nwai412008TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412008TMsg.spiffDtlAmt, (BigDecimal) nwai412008TMsgMap.get("SPIFF_DTL_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412008TMsg.somItemCd, (String) nwai412008TMsgMap.get("SOM_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412008TMsg.somItemTxt, (String) nwai412008TMsgMap.get("SOM_ITEM_TXT"));

            msgList.add(nwai412008TMsg);
        }
        return msgList;
    }

    /**
     * @param transactionId
     * @param somQuoteId
     * @return
     */
    private List<NWAI4120_10TMsg> getNwai412010(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4120_10TMsg> msgList = new ArrayList<NWAI4120_10TMsg>();
        Map<String, Object> ssmParamNWAI412010 = new HashMap<String, Object>();
        ssmParamNWAI412010.put("interfaceId", this.interfaceId);
        ssmParamNWAI412010.put("transactionId", transactionId);
        ssmParamNWAI412010.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai412010TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4120_10", ssmParamNWAI412010);

        for (Map<String, Object> nwai412010TMsgMap : nwai412010TMsgList) {
            NWAI4120_10TMsg nwai412010TMsg = new NWAI4120_10TMsg();

            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.interfaceId, (String) nwai412010TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.transactionId, (BigDecimal) nwai412010TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.segmentId, (BigDecimal) nwai412010TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.unitId, (BigDecimal) nwai412010TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.seqNumber, (BigDecimal) nwai412010TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.somQuoteId, (BigDecimal) nwai412010TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.somConfigId, (BigDecimal) nwai412010TMsgMap.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.somSortOrdNum, (BigDecimal) nwai412010TMsgMap.get("SOM_SORT_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.somOrdQty, (BigDecimal) nwai412010TMsgMap.get("SOM_ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.somConfigQty, (BigDecimal) nwai412010TMsgMap.get("SOM_CONFIG_QTY"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.usedDemoId, (BigDecimal) nwai412010TMsgMap.get("USED_DEMO_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.flPrcAmt, (BigDecimal) nwai412010TMsgMap.get("FL_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.prfPrcAmt, (BigDecimal) nwai412010TMsgMap.get("PRF_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.msrpListPrcAmt, (BigDecimal) nwai412010TMsgMap.get("MSRP_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.sellPrcListAmt, (BigDecimal) nwai412010TMsgMap.get("SELL_PRC_LIST_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.extSellPrcAmt, (BigDecimal) nwai412010TMsgMap.get("EXT_SELL_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.ltstPrcAmt, (BigDecimal) nwai412010TMsgMap.get("LTST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.extFlPrcAmt, (BigDecimal) nwai412010TMsgMap.get("EXT_FL_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.engnrBdlCnt, (BigDecimal) nwai412010TMsgMap.get("ENGNR_BDL_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.somMercCd, (String) nwai412010TMsgMap.get("SOM_MERC_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.somMercNm, (String) nwai412010TMsgMap.get("SOM_MERC_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.somMdlDescTxt, (String) nwai412010TMsgMap.get("SOM_MDL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.somMdseTpTxt, (String) nwai412010TMsgMap.get("SOM_MDSE_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.somSegTxt, (String) nwai412010TMsgMap.get("SOM_SEG_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.somDescSerNum, (String) nwai412010TMsgMap.get("SOM_DESC_SER_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412010TMsg.somInvtyConfigId, (BigDecimal) nwai412010TMsgMap.get("SOM_INVTY_CONFIG_ID"));  //QC#25324

            msgList.add(nwai412010TMsg);
        }
        return msgList;
    }

    /**
     * @param transactionId
     * @param somQuoteId
     * @return
     */
    private List<NWAI4120_12TMsg> getNwai412012(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4120_12TMsg> msgList = new ArrayList<NWAI4120_12TMsg>();
        Map<String, Object> ssmParamNWAI412012 = new HashMap<String, Object>();
        ssmParamNWAI412012.put("interfaceId", this.interfaceId);
        ssmParamNWAI412012.put("transactionId", transactionId);
        ssmParamNWAI412012.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai412012TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4120_12", ssmParamNWAI412012);

        for (Map<String, Object> nwai412012TMsgMap : nwai412012TMsgList) {
            NWAI4120_12TMsg nwai412012TMsg = new NWAI4120_12TMsg();

            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.interfaceId, (String) nwai412012TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.transactionId, (BigDecimal) nwai412012TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.segmentId, (BigDecimal) nwai412012TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.unitId, (BigDecimal) nwai412012TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.seqNumber, (BigDecimal) nwai412012TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somQuoteId, (BigDecimal) nwai412012TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somConfigId, (BigDecimal) nwai412012TMsgMap.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somConfigLtrTxt, (String) nwai412012TMsgMap.get("SOM_CONFIG_LTR_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somOrdQty, (BigDecimal) nwai412012TMsgMap.get("SOM_ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somPsnCnt, (BigDecimal) nwai412012TMsgMap.get("SOM_PSN_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somStepCnt, (BigDecimal) nwai412012TMsgMap.get("SOM_STEP_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.flPrcListId, (BigDecimal) nwai412012TMsgMap.get("FL_PRC_LIST_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.bidPrcListId, (BigDecimal) nwai412012TMsgMap.get("BID_PRC_LIST_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.lastUpdTs, (String) nwai412012TMsgMap.get("LAST_UPD_TS"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.erlstDelyTs, (String) nwai412012TMsgMap.get("ERLST_DELY_TS"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somElevCnt, (BigDecimal) nwai412012TMsgMap.get("SOM_ELEV_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.loadDockCnt, (BigDecimal) nwai412012TMsgMap.get("LOAD_DOCK_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somTaxRate, (BigDecimal) nwai412012TMsgMap.get("SOM_TAX_RATE"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somCrmSlsTxt, (String) nwai412012TMsgMap.get("SOM_CRM_SLS_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somAcctNum, (String) nwai412012TMsgMap.get("SOM_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somShipToAddr_01, (String) nwai412012TMsgMap.get("SOM_SHIP_TO_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somShipToAddr_02, (String) nwai412012TMsgMap.get("SOM_SHIP_TO_ADDR_02"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somCityAddr, (String) nwai412012TMsgMap.get("SOM_CITY_ADDR"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somStCd, (String) nwai412012TMsgMap.get("SOM_ST_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.csaPmtTpTxt, (String) nwai412012TMsgMap.get("CSA_PMT_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.bidPrcListNm, (String) nwai412012TMsgMap.get("BID_PRC_LIST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.csaPmtFirstNm, (String) nwai412012TMsgMap.get("CSA_PMT_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.csaPmtLastNm, (String) nwai412012TMsgMap.get("CSA_PMT_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.csaPmtPhoTxt, (String) nwai412012TMsgMap.get("CSA_PMT_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.csaPmtPhoExtDescTxt, (String) nwai412012TMsgMap.get("CSA_PMT_PHO_EXT_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.csaPmtEmlTxt, (String) nwai412012TMsgMap.get("CSA_PMT_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somDlrTxt, (String) nwai412012TMsgMap.get("SOM_DLR_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.mtrMethTxt, (String) nwai412012TMsgMap.get("MTR_METH_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.splitTpTxt, (String) nwai412012TMsgMap.get("SPLIT_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.addrLbTxt, (String) nwai412012TMsgMap.get("ADDR_LB_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.prcBookNm, (String) nwai412012TMsgMap.get("PRC_BOOK_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somFlPrcListNm, (String) nwai412012TMsgMap.get("SOM_FL_PRC_LIST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.scdPhoTxt, (String) nwai412012TMsgMap.get("SCD_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.scdPhoExtTxt, (String) nwai412012TMsgMap.get("SCD_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.scdEmlTxt, (String) nwai412012TMsgMap.get("SCD_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.istlRepDescTxt, (String) nwai412012TMsgMap.get("ISTL_REP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.istlBrDescTxt, (String) nwai412012TMsgMap.get("ISTL_BR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.brDrctrDescTxt, (String) nwai412012TMsgMap.get("BR_DRCTR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.primCtacLastNm, (String) nwai412012TMsgMap.get("PRIM_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.primPhoTxt, (String) nwai412012TMsgMap.get("PRIM_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.primPhoExtTxt, (String) nwai412012TMsgMap.get("PRIM_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.primEmlTxt, (String) nwai412012TMsgMap.get("PRIM_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.scdCtacFirstNm, (String) nwai412012TMsgMap.get("SCD_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.scdCtacLastNm, (String) nwai412012TMsgMap.get("SCD_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somCntyNm, (String) nwai412012TMsgMap.get("SOM_CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somZipCd, (String) nwai412012TMsgMap.get("SOM_ZIP_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somShipViaTxt, (String) nwai412012TMsgMap.get("SOM_SHIP_VIA_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.hrsOpTxt, (String) nwai412012TMsgMap.get("HRS_OP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.somInstnTxt, (String) nwai412012TMsgMap.get("SOM_INSTN_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412012TMsg.primCtacFirstNm, (String) nwai412012TMsgMap.get("PRIM_CTAC_FIRST_NM"));

            msgList.add(nwai412012TMsg);
        }
        return msgList;
    }

    /**
     * @param transactionId
     * @param somQuoteId
     * @return
     */
    private List<NWAI4120_13TMsg> getNwai412013(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4120_13TMsg> msgList = new ArrayList<NWAI4120_13TMsg>();
        Map<String, Object> ssmParamNWAI412013 = new HashMap<String, Object>();
        ssmParamNWAI412013.put("interfaceId", this.interfaceId);
        ssmParamNWAI412013.put("transactionId", transactionId);
        ssmParamNWAI412013.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai412013TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4120_13", ssmParamNWAI412013);

        for (Map<String, Object> nwai412013TMsgMap : nwai412013TMsgList) {
            NWAI4120_13TMsg nwai412013TMsg = new NWAI4120_13TMsg();

            ZYPEZDItemValueSetter.setValue(nwai412013TMsg.interfaceId, (String) nwai412013TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412013TMsg.transactionId, (BigDecimal) nwai412013TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412013TMsg.segmentId, (BigDecimal) nwai412013TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412013TMsg.unitId, (BigDecimal) nwai412013TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412013TMsg.seqNumber, (BigDecimal) nwai412013TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai412013TMsg.somQuoteId, (BigDecimal) nwai412013TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412013TMsg.somConfigId, (BigDecimal) nwai412013TMsgMap.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412013TMsg.somItemCd, (String) nwai412013TMsgMap.get("SOM_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412013TMsg.somSerNum, (String) nwai412013TMsgMap.get("SOM_SER_NUM"));

            msgList.add(nwai412013TMsg);
        }
        return msgList;
    }

    /**
     * @param transactionId
     * @param somQuoteId
     * @return
     */
    private List<NWAI4120_14TMsg> getNwai412014(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4120_14TMsg> msgList = new ArrayList<NWAI4120_14TMsg>();
        Map<String, Object> ssmParamNWAI412014 = new HashMap<String, Object>();
        ssmParamNWAI412014.put("interfaceId", this.interfaceId);
        ssmParamNWAI412014.put("transactionId", transactionId);
        ssmParamNWAI412014.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai412014TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4120_14", ssmParamNWAI412014);

        for (Map<String, Object> nwai412014TMsgMap : nwai412014TMsgList) {
            NWAI4120_14TMsg nwai412014TMsg = new NWAI4120_14TMsg();

            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.interfaceId, (String) nwai412014TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.transactionId, (BigDecimal) nwai412014TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.segmentId, (BigDecimal) nwai412014TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.unitId, (BigDecimal) nwai412014TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.seqNumber, (BigDecimal) nwai412014TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.rtrnCmpyId, (BigDecimal) nwai412014TMsgMap.get("RTRN_CMPY_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.somQuoteId, (BigDecimal) nwai412014TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.somConfigId, (BigDecimal) nwai412014TMsgMap.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.equipCondId, (BigDecimal) nwai412014TMsgMap.get("EQUIP_COND_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.ackDescId, (BigDecimal) nwai412014TMsgMap.get("ACK_DESC_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.somPrmoAmt, (BigDecimal) nwai412014TMsgMap.get("SOM_PRMO_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.origOrdTs, (String) nwai412014TMsgMap.get("ORIG_ORD_TS"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.pickUpDtOthDescTxt, (String) nwai412014TMsgMap.get("PICK_UP_DT_OTH_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.pickUpDtSameCnt, (BigDecimal) nwai412014TMsgMap.get("PICK_UP_DT_SAME_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.pickUpDtOthCnt, (BigDecimal) nwai412014TMsgMap.get("PICK_UP_DT_OTH_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.somPrmoSerNum, (String) nwai412014TMsgMap.get("SOM_PRMO_SER_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.somPrmoCd, (String) nwai412014TMsgMap.get("SOM_PRMO_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.somPrmoMdlNm, (String) nwai412014TMsgMap.get("SOM_PRMO_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.rtrnOthTxt, (String) nwai412014TMsgMap.get("RTRN_OTH_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.pickUpCtacFirstNm, (String) nwai412014TMsgMap.get("PICK_UP_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.pickUpCtacLastNm, (String) nwai412014TMsgMap.get("PICK_UP_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.somPrmoCityTxt, (String) nwai412014TMsgMap.get("SOM_PRMO_CITY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.somPrmoStCd, (String) nwai412014TMsgMap.get("SOM_PRMO_ST_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.somPrmoCntyNm, (String) nwai412014TMsgMap.get("SOM_PRMO_CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.somPrmoZipCd, (String) nwai412014TMsgMap.get("SOM_PRMO_ZIP_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.pickUpPhoTxt, (String) nwai412014TMsgMap.get("PICK_UP_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.pickUpPhoExtTxt, (String) nwai412014TMsgMap.get("PICK_UP_PHO_EXT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.pickUpEmlTxt, (String) nwai412014TMsgMap.get("PICK_UP_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.somRmvInstnTxt, (String) nwai412014TMsgMap.get("SOM_RMV_INSTN_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.somPrmoAddr_01, (String) nwai412014TMsgMap.get("SOM_PRMO_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(nwai412014TMsg.somPrmoAddr_02, (String) nwai412014TMsgMap.get("SOM_PRMO_ADDR_02"));

            msgList.add(nwai412014TMsg);
        }
        return msgList;
    }

    /**
     * @param transactionId
     * @param somQuoteId
     * @return
     */
    private List<NWAI4120_16TMsg> getNwai412016(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4120_16TMsg> msgList = new ArrayList<NWAI4120_16TMsg>();
        Map<String, Object> ssmParamNWAI412016 = new HashMap<String, Object>();
        ssmParamNWAI412016.put("interfaceId", this.interfaceId);
        ssmParamNWAI412016.put("transactionId", transactionId);
        ssmParamNWAI412016.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai412016TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4120_16", ssmParamNWAI412016);

        for (Map<String, Object> nwai412016TMsgMap : nwai412016TMsgList) {
            NWAI4120_16TMsg nwai412016TMsg = new NWAI4120_16TMsg();

            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.interfaceId, (String) nwai412016TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.transactionId, (BigDecimal) nwai412016TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.segmentId, (BigDecimal) nwai412016TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.unitId, (BigDecimal) nwai412016TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.seqNumber, (BigDecimal) nwai412016TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.somSrvId, (BigDecimal) nwai412016TMsgMap.get("SOM_SRV_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.somQuoteId, (BigDecimal) nwai412016TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.fleetTermNum, (BigDecimal) nwai412016TMsgMap.get("FLEET_TERM_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.fleetVolClrCnt, (BigDecimal) nwai412016TMsgMap.get("FLEET_VOL_CLR_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.fleetVolBwCnt, (BigDecimal) nwai412016TMsgMap.get("FLEET_VOL_BW_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.svcCostId, (BigDecimal) nwai412016TMsgMap.get("SVC_COST_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.svcCostTrnsfAmt, (BigDecimal) nwai412016TMsgMap.get("SVC_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.clrOvagCostTrnsfAmt, (BigDecimal) nwai412016TMsgMap.get("CLR_OVAG_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.bwOvagCostTrnsfAmt, (BigDecimal) nwai412016TMsgMap.get("BW_OVAG_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.finSvcCostAmt, (BigDecimal) nwai412016TMsgMap.get("FIN_SVC_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.totSvcCostAmt, (BigDecimal) nwai412016TMsgMap.get("TOT_SVC_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.totSvcCostTrnsfAmt, (BigDecimal) nwai412016TMsgMap.get("TOT_SVC_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.totSvcRevTrnsfAmt, (BigDecimal) nwai412016TMsgMap.get("TOT_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.fleetBwReqBaseAmt, (BigDecimal) nwai412016TMsgMap.get("FLEET_BW_REQ_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.fleetClrReqBaseAmt, (BigDecimal) nwai412016TMsgMap.get("FLEET_CLR_REQ_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.addlPrmoId, (BigDecimal) nwai412016TMsgMap.get("ADDL_PRMO_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.fleetClrCpcCnt, (BigDecimal) nwai412016TMsgMap.get("FLEET_CLR_CPC_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.fleetBwCpcCnt, (BigDecimal) nwai412016TMsgMap.get("FLEET_BW_CPC_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.fleetPlanDescTxt, (String) nwai412016TMsgMap.get("FLEET_PLAN_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.fleetBillByTxt, (String) nwai412016TMsgMap.get("FLEET_BILL_BY_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.fleetBllgCycleTxt, (String) nwai412016TMsgMap.get("FLEET_BLLG_CYCLE_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.contrTpTxt, (String) nwai412016TMsgMap.get("CONTR_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.svcCostTrnsfTxt, (String) nwai412016TMsgMap.get("SVC_COST_TRNSF_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.svcCostApvlTxt, (String) nwai412016TMsgMap.get("SVC_COST_APVL_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.stplIndSomTxt, (String) nwai412016TMsgMap.get("STPL_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.ctWavIndSomTxt, (String) nwai412016TMsgMap.get("CT_WAV_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.contrIndSomTxt, (String) nwai412016TMsgMap.get("CONTR_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.somContrNum, (String) nwai412016TMsgMap.get("SOM_CONTR_NUM"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.trnsfOvrdIndSomTxt, (String) nwai412016TMsgMap.get("TRNSF_OVRD_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412016TMsg.somMaintPoDt, (String) nwai412016TMsgMap.get("SOM_MAINT_PO_DT"));
            //QC#18306
            //ZYPEZDItemValueSetter.setValue(nwai412016TMsg.somBllgFromOmTxt, (String) nwai412016TMsgMap.get("SOM_BLLG_FROM_OM_TXT"));

            msgList.add(nwai412016TMsg);
        }
        return msgList;
    }

    /**
     * @param transactionId
     * @param somQuoteId
     * @return
     */
    private List<NWAI4120_17TMsg> getNwai412017(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4120_17TMsg> msgList = new ArrayList<NWAI4120_17TMsg>();
        Map<String, Object> ssmParamNWAI412017 = new HashMap<String, Object>();
        ssmParamNWAI412017.put("interfaceId", this.interfaceId);
        ssmParamNWAI412017.put("transactionId", transactionId);
        ssmParamNWAI412017.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai412017TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4120_17", ssmParamNWAI412017);

        for (Map<String, Object> nwai412017TMsgMap : nwai412017TMsgList) {
            NWAI4120_17TMsg nwai412017TMsg = new NWAI4120_17TMsg();

            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.interfaceId, (String) nwai412017TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.transactionId, (BigDecimal) nwai412017TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.segmentId, (BigDecimal) nwai412017TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.unitId, (BigDecimal) nwai412017TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.seqNumber, (BigDecimal) nwai412017TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.somQuoteId, (BigDecimal) nwai412017TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.somSrvId, (BigDecimal) nwai412017TMsgMap.get("SOM_SRV_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.somConfigId, (BigDecimal) nwai412017TMsgMap.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.somDurnId, (BigDecimal) nwai412017TMsgMap.get("SOM_DURN_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.somUnitId, (BigDecimal) nwai412017TMsgMap.get("SOM_UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.svcContrId, (BigDecimal) nwai412017TMsgMap.get("SVC_CONTR_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.quoteStsId, (BigDecimal) nwai412017TMsgMap.get("QUOTE_STS_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.reqBaseAmt, (BigDecimal) nwai412017TMsgMap.get("REQ_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.pubBaseAmt, (BigDecimal) nwai412017TMsgMap.get("PUB_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.engnrItemCd, (String) nwai412017TMsgMap.get("ENGNR_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.somMdlDescLongTxt, (String) nwai412017TMsgMap.get("SOM_MDL_DESC_LONG_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.dclnMaintIndSomTxt, (String) nwai412017TMsgMap.get("DCLN_MAINT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.orclMdlNm, (String) nwai412017TMsgMap.get("ORCL_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.somSvcItemCd, (String) nwai412017TMsgMap.get("SOM_SVC_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.somPkgNm, (String) nwai412017TMsgMap.get("SOM_PKG_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.bllgCycleTxt, (String) nwai412017TMsgMap.get("BLLG_CYCLE_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.prcCatgCd, (String) nwai412017TMsgMap.get("PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412017TMsg.svcContrIndSomTxt, (String) nwai412017TMsgMap.get("SVC_CONTR_IND_SOM_TXT"));

            msgList.add(nwai412017TMsg);
        }
        return msgList;
    }

    /**
     * @param transactionId
     * @param somQuoteId
     * @return
     */
    private List<NWAI4120_18TMsg> getNwai412018(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4120_18TMsg> msgList = new ArrayList<NWAI4120_18TMsg>();
        Map<String, Object> ssmParamNWAI412018 = new HashMap<String, Object>();
        ssmParamNWAI412018.put("interfaceId", this.interfaceId);
        ssmParamNWAI412018.put("transactionId", transactionId);
        ssmParamNWAI412018.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai412018TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4120_18", ssmParamNWAI412018);

        for (Map<String, Object> nwai412018TMsgMap : nwai412018TMsgList) {
            NWAI4120_18TMsg nwai412018TMsg = new NWAI4120_18TMsg();

            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.interfaceId, (String) nwai412018TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.transactionId, (BigDecimal) nwai412018TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.segmentId, (BigDecimal) nwai412018TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.unitId, (BigDecimal) nwai412018TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.seqNumber, (BigDecimal) nwai412018TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somSrvId, (BigDecimal) nwai412018TMsgMap.get("SOM_SRV_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somSrvLineId, (BigDecimal) nwai412018TMsgMap.get("SOM_SRV_LINE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somSrvTermId, (BigDecimal) nwai412018TMsgMap.get("SOM_SRV_TERM_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somSrvUnitId, (BigDecimal) nwai412018TMsgMap.get("SOM_SRV_UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somVolMthSrvCnt, (BigDecimal) nwai412018TMsgMap.get("SOM_VOL_MTH_SRV_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somVolTotSrvCnt, (BigDecimal) nwai412018TMsgMap.get("SOM_VOL_TOT_SRV_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somQuoteId, (BigDecimal) nwai412018TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somSrvVolComitCnt, (BigDecimal) nwai412018TMsgMap.get("SOM_SRV_VOL_COMIT_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.baseClickReqBaseAmt, (BigDecimal) nwai412018TMsgMap.get("BASE_CLICK_REQ_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.svcPrmoId, (BigDecimal) nwai412018TMsgMap.get("SVC_PRMO_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.bandMaxCnt, (BigDecimal) nwai412018TMsgMap.get("BAND_MAX_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.termMinCnt, (BigDecimal) nwai412018TMsgMap.get("TERM_MIN_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.termMaxCnt, (BigDecimal) nwai412018TMsgMap.get("TERM_MAX_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.reqBaseAmt, (BigDecimal) nwai412018TMsgMap.get("REQ_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.costTrnsfAmt, (BigDecimal) nwai412018TMsgMap.get("COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.ovagCostTrnsfAmt, (BigDecimal) nwai412018TMsgMap.get("OVAG_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.totCostTrnsfAmt, (BigDecimal) nwai412018TMsgMap.get("TOT_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.baseClickPubBaseAmt, (BigDecimal) nwai412018TMsgMap.get("BASE_CLICK_PUB_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.baseClickCostTrnsfAmt, (BigDecimal) nwai412018TMsgMap.get("BASE_CLICK_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.baseSvcRevTrnsfAmt, (BigDecimal) nwai412018TMsgMap.get("BASE_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.ovagSvcRevTrnsfAmt, (BigDecimal) nwai412018TMsgMap.get("OVAG_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.totSvcRevTrnsfAmt, (BigDecimal) nwai412018TMsgMap.get("TOT_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.origCostTrnsfAmt, (BigDecimal) nwai412018TMsgMap.get("ORIG_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.origSvcRevTrnsfAmt, (BigDecimal) nwai412018TMsgMap.get("ORIG_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.reqCpcCnt, (BigDecimal) nwai412018TMsgMap.get("REQ_CPC_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.svcPubBaseAmt, (BigDecimal) nwai412018TMsgMap.get("SVC_PUB_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.pubCpcCnt, (BigDecimal) nwai412018TMsgMap.get("PUB_CPC_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somMultCnt, (BigDecimal) nwai412018TMsgMap.get("SOM_MULT_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.bandMinCnt, (BigDecimal) nwai412018TMsgMap.get("BAND_MIN_CNT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.contrOptTxt, (String) nwai412018TMsgMap.get("CONTR_OPT_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.prmoBtIndSomTxt, (String) nwai412018TMsgMap.get("PRMO_BT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.isClrBtIndSomTxt, (String) nwai412018TMsgMap.get("IS_CLR_BT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.planDescTxt, (String) nwai412018TMsgMap.get("PLAN_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somBllgCycleDescTxt, (String) nwai412018TMsgMap.get("SOM_BLLG_CYCLE_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.usgCycleDescTxt, (String) nwai412018TMsgMap.get("USG_CYCLE_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.svcContrIndSomTxt, (String) nwai412018TMsgMap.get("SVC_CONTR_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somPkgNm, (String) nwai412018TMsgMap.get("SOM_PKG_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somSrvMtrTpNm, (String) nwai412018TMsgMap.get("SOM_SRV_MTR_TP_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somSrvCntrNm, (String) nwai412018TMsgMap.get("SOM_SRV_CNTR_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.svcClickTpTxt, (String) nwai412018TMsgMap.get("SVC_CLICK_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.clickTpDescTxt, (String) nwai412018TMsgMap.get("CLICK_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.svcPrcBookDescTxt, (String) nwai412018TMsgMap.get("SVC_PRC_BOOK_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somBandDescTxt, (String) nwai412018TMsgMap.get("SOM_BAND_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.orclMdlNm, (String) nwai412018TMsgMap.get("ORCL_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somSvcItemCd, (String) nwai412018TMsgMap.get("SOM_SVC_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somSrvPrmoCd, (String) nwai412018TMsgMap.get("SOM_SRV_PRMO_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somSrvPrmoNm, (String) nwai412018TMsgMap.get("SOM_SRV_PRMO_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.svcPrmoTxt, (String) nwai412018TMsgMap.get("SVC_PRMO_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.dblClickIndSomTxt, (String) nwai412018TMsgMap.get("DBL_CLICK_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.rateIndSomTxt, (String) nwai412018TMsgMap.get("RATE_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.svcPrcBookTxt, (String) nwai412018TMsgMap.get("SVC_PRC_BOOK_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.withRmtIndSomTxt, (String) nwai412018TMsgMap.get("WITH_RMT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somBandCd, (String) nwai412018TMsgMap.get("SOM_BAND_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.dclnMaintIndSomTxt, (String) nwai412018TMsgMap.get("DCLN_MAINT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.mtrReadMethTxt, (String) nwai412018TMsgMap.get("MTR_READ_METH_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.prmoIndSomTxt, (String) nwai412018TMsgMap.get("PRMO_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.isPrmoIndSomTxt, (String) nwai412018TMsgMap.get("IS_PRMO_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.contrTpDescTxt, (String) nwai412018TMsgMap.get("CONTR_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somMdlId, (String) nwai412018TMsgMap.get("SOM_MDL_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somMdlGrpId, (String) nwai412018TMsgMap.get("SOM_MDL_GRP_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.somMdlDescTxt, (String) nwai412018TMsgMap.get("SOM_MDL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.isFleetIndSomTxt, (String) nwai412018TMsgMap.get("IS_FLEET_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.isBaseClickIndSomTxt, (String) nwai412018TMsgMap.get("IS_BASE_CLICK_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412018TMsg.prcCatgCd, (String) nwai412018TMsgMap.get("PRC_CATG_CD"));

            msgList.add(nwai412018TMsg);
        }
        return msgList;
    }

    /**
     * @param transactionId
     * @param somQuoteId
     * @return
     */
    private List<NWAI4120_19TMsg> getNwai412019(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4120_19TMsg> msgList = new ArrayList<NWAI4120_19TMsg>();
        Map<String, Object> ssmParamNWAI412019 = new HashMap<String, Object>();
        ssmParamNWAI412019.put("interfaceId", this.interfaceId);
        ssmParamNWAI412019.put("transactionId", transactionId);
        ssmParamNWAI412019.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai412019TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4120_19", ssmParamNWAI412019);

        for (Map<String, Object> nwai412019TMsgMap : nwai412019TMsgList) {
            NWAI4120_19TMsg nwai412019TMsg = new NWAI4120_19TMsg();

            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.interfaceId, (String) nwai412019TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.transactionId, (BigDecimal) nwai412019TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.segmentId, (BigDecimal) nwai412019TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.unitId, (BigDecimal) nwai412019TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.seqNumber, (BigDecimal) nwai412019TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.somSrvId, (BigDecimal) nwai412019TMsgMap.get("SOM_SRV_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.somSrvLineId, (BigDecimal) nwai412019TMsgMap.get("SOM_SRV_LINE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.somDurnId, (BigDecimal) nwai412019TMsgMap.get("SOM_DURN_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.somUnitId, (BigDecimal) nwai412019TMsgMap.get("SOM_UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.somQuoteId, (BigDecimal) nwai412019TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.somConfigId, (BigDecimal) nwai412019TMsgMap.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.reqBaseAmt, (BigDecimal) nwai412019TMsgMap.get("REQ_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.pubBaseAmt, (BigDecimal) nwai412019TMsgMap.get("PUB_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.reqSvcPrcAmt, (BigDecimal) nwai412019TMsgMap.get("REQ_SVC_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.pubSvcPrcAmt, (BigDecimal) nwai412019TMsgMap.get("PUB_SVC_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.totCostTrnsfAmt, (BigDecimal) nwai412019TMsgMap.get("TOT_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.totSvcRevTrnsfAmt, (BigDecimal) nwai412019TMsgMap.get("TOT_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.origCostTrnsfAmt, (BigDecimal) nwai412019TMsgMap.get("ORIG_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.origSvcRevTrnsfAmt, (BigDecimal) nwai412019TMsgMap.get("ORIG_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.somItemCd, (String) nwai412019TMsgMap.get("SOM_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.somMdlDescLongTxt, (String) nwai412019TMsgMap.get("SOM_MDL_DESC_LONG_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.dclnMaintIndSomTxt, (String) nwai412019TMsgMap.get("DCLN_MAINT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.orclMdlNm, (String) nwai412019TMsgMap.get("ORCL_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.svcContrIndSomTxt, (String) nwai412019TMsgMap.get("SVC_CONTR_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.somPkgNm, (String) nwai412019TMsgMap.get("SOM_PKG_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412019TMsg.prcCatgCd, (String) nwai412019TMsgMap.get("PRC_CATG_CD"));

            msgList.add(nwai412019TMsg);
        }
        return msgList;
    }

    /**
     * @param transactionId
     * @param somQuoteId
     * @return
     */
    private List<NWAI4120_20TMsg> getNwai412020(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4120_20TMsg> msgList = new ArrayList<NWAI4120_20TMsg>();
        Map<String, Object> ssmParamNWAI412020 = new HashMap<String, Object>();
        ssmParamNWAI412020.put("interfaceId", this.interfaceId);
        ssmParamNWAI412020.put("transactionId", transactionId);
        ssmParamNWAI412020.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai412020TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4120_20", ssmParamNWAI412020);

        for (Map<String, Object> nwai412020TMsgMap : nwai412020TMsgList) {
            NWAI4120_20TMsg nwai412020TMsg = new NWAI4120_20TMsg();

            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.interfaceId, (String) nwai412020TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.transactionId, (BigDecimal) nwai412020TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.segmentId, (BigDecimal) nwai412020TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.unitId, (BigDecimal) nwai412020TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.seqNumber, (BigDecimal) nwai412020TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.somQuoteId, (BigDecimal) nwai412020TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.somSrvId, (BigDecimal) nwai412020TMsgMap.get("SOM_SRV_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.somConfigId, (BigDecimal) nwai412020TMsgMap.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.somUnitId, (BigDecimal) nwai412020TMsgMap.get("SOM_UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.quoteStsId, (BigDecimal) nwai412020TMsgMap.get("QUOTE_STS_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.reqSvcPrcAmt, (BigDecimal) nwai412020TMsgMap.get("REQ_SVC_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.pubSvcPrcAmt, (BigDecimal) nwai412020TMsgMap.get("PUB_SVC_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.engnrItemCd, (String) nwai412020TMsgMap.get("ENGNR_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.somMdlDescLongTxt, (String) nwai412020TMsgMap.get("SOM_MDL_DESC_LONG_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.dclnMaintIndSomTxt, (String) nwai412020TMsgMap.get("DCLN_MAINT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.orclMdlNm, (String) nwai412020TMsgMap.get("ORCL_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.somSvcItemCd, (String) nwai412020TMsgMap.get("SOM_SVC_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.somPkgNm, (String) nwai412020TMsgMap.get("SOM_PKG_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.bllgCycleTxt, (String) nwai412020TMsgMap.get("BLLG_CYCLE_TXT"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.prcCatgCd, (String) nwai412020TMsgMap.get("PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(nwai412020TMsg.svcContrIndSomTxt, (String) nwai412020TMsgMap.get("SVC_CONTR_IND_SOM_TXT"));

            msgList.add(nwai412020TMsg);
        }
        return msgList;
    }

    /**
     * @param transactionId
     * @param somQuoteId
     * @return
     */
    private List<NWAI4120_21TMsg> getNwai412021(BigDecimal transactionId, BigDecimal somQuoteId) {

        List<NWAI4120_21TMsg> msgList = new ArrayList<NWAI4120_21TMsg>();
        Map<String, Object> ssmParamNWAI412021 = new HashMap<String, Object>();
        ssmParamNWAI412021.put("interfaceId", this.interfaceId);
        ssmParamNWAI412021.put("transactionId", transactionId);
        ssmParamNWAI412021.put("somQuoteId", somQuoteId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nwai412021TMsgList = this.ssmBatchClient.queryObjectList("getNWAI4120_21", ssmParamNWAI412021);

        for (Map<String, Object> nwai412021TMsgMap : nwai412021TMsgList) {
            NWAI4120_21TMsg nwai412021TMsg = new NWAI4120_21TMsg();

            ZYPEZDItemValueSetter.setValue(nwai412021TMsg.interfaceId, (String) nwai412021TMsgMap.get("INTERFACE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412021TMsg.transactionId, (BigDecimal) nwai412021TMsgMap.get("TRANSACTION_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412021TMsg.segmentId, (BigDecimal) nwai412021TMsgMap.get("SEGMENT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412021TMsg.unitId, (BigDecimal) nwai412021TMsgMap.get("UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412021TMsg.seqNumber, (BigDecimal) nwai412021TMsgMap.get("SEQ_NUMBER"));
            ZYPEZDItemValueSetter.setValue(nwai412021TMsg.somQuoteId, (BigDecimal) nwai412021TMsgMap.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412021TMsg.somConfigId, (BigDecimal) nwai412021TMsgMap.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412021TMsg.somConfigLineId, (BigDecimal) nwai412021TMsgMap.get("SOM_CONFIG_LINE_ID"));
            ZYPEZDItemValueSetter.setValue(nwai412021TMsg.somEquipPrcListNm, (String) nwai412021TMsgMap.get("SOM_EQUIP_PRC_LIST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412021TMsg.somSvcPrcListNm, (String) nwai412021TMsgMap.get("SOM_SVC_PRC_LIST_NM"));
            ZYPEZDItemValueSetter.setValue(nwai412021TMsg.somRepRevAmt, (BigDecimal) nwai412021TMsgMap.get("SOM_REP_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412021TMsg.somEquipBaseAmt, (BigDecimal) nwai412021TMsgMap.get("SOM_EQUIP_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412021TMsg.somEquipPmtAmt, (BigDecimal) nwai412021TMsgMap.get("SOM_EQUIP_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412021TMsg.somSvcBaseAmt, (BigDecimal) nwai412021TMsgMap.get("SOM_SVC_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(nwai412021TMsg.somSvcPmtAmt, (BigDecimal) nwai412021TMsgMap.get("SOM_SVC_PMT_AMT"));

            msgList.add(nwai412021TMsg);
        }
        return msgList;
    }
    
    private boolean deleteDuplicateDsImpt(String glblCmpyCd, Map<String, List<BigDecimal>> duplicateDsImptPkMap) {

        List<BigDecimal> dsImptOrdPkList = duplicateDsImptPkMap.get("dsImptOrdPk");
        for (BigDecimal dsImptOrdPk : dsImptOrdPkList) {

            DS_IMPT_ORDTMsg dsImptOrd = new DS_IMPT_ORDTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrd.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrd.dsImptOrdPk, dsImptOrdPk);
            dsImptOrd = (DS_IMPT_ORDTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsImptOrd);
            if (dsImptOrd == null) {
                return false;
            }
            if (!S21StringUtil.isEquals(dsImptOrd.imptStsCd.getValue(), IMPT_STS.NOT_PROCESSED) 
                    && !S21StringUtil.isEquals(dsImptOrd.imptStsCd.getValue(), IMPT_STS.PENDING_OM_REVIEW)
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

    /**
     * Create Array.
     * @param <T>
     * @param param
     * @return
     */
    private static <T> T[] toArray(T... param) {
        return param;
    }

    private static <T extends List<?>> boolean hasValueList(T list) {
        return (list != null && list.size() > 0);
    }

    /**
     * writeStartLogLn
     * @param methodNm
     */
    private static void writeStartLogLn(String methodNm) {
        writeLogLn("[START] %s", methodNm);
    }

    private static void writeStartLogLn(String methodNm, Object target) {
        // writeLogLn("[START] %s(%s)", methodNm);
    }

    /**
     * writeEndLogLn
     * @param methodNm
     */
    private static void writeEndLogLn(String methodNm) {
        writeLogLn("[END] %s\r\n", methodNm);
    }

    private static void writeEndLogLn(String methodNm, Object target) {
        // writeLogLn("[END] %s(%s)\r\n", methodNm);
    }

    /**
     * WriteLogLn
     * @param format
     * @param param
     */
    private static void writeLogLn(String format, Object... param) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("[%s]", NWAB412001Constant.PROGRAM_ID));

        if (param.length > 0) {
            sb.append(String.format(format, param));
        } else {
            sb.append(format);
        }

        S21InfoLogOutput.println(sb.toString());
    }

    // Add Start 2017/08/04 QC#19839
    private static boolean checkMdlId(SomDsImptOrdBean somDsImptOrdBean) {

        List<BigDecimal> mdlIdList = new ArrayList<BigDecimal>();
        for (SomDsImptOrdConfigBean configBean : somDsImptOrdBean.dsImptOrdConfigBeanList) {
            if (ZYPCommonFunc.hasValue(configBean.mdlId)) {
                mdlIdList.add(configBean.mdlId.getValue());
            }
        }
        for (SomDsImptSvcDtlBean svcDtlBean : somDsImptOrdBean.dsImptSvcDtlBeanList) {
            for (SomDsImptSvcPrcBean svcPrcBean : svcDtlBean.dsImptSvcPrcBeanList) {
                if (ZYPCommonFunc.hasValue(svcPrcBean.mdlId) && !mdlIdList.contains(svcPrcBean.mdlId.getValue())) {
                    S21InfoLogOutput.println(MSG_ID.NWAM0937E.toString());
                    return true;
                }
            }
        }
        return false;
    }
    // Add End 2017/08/04 QC#19839

    // Add Start 2018/01/19 QC#23598
    private void insertDocMgtScanTrx(String aquNum) {
        writeStartLogLn("thereforeDocumentAttach", aquNum);
        try {

            String thereforeConnAvalFlg = ZYPCodeDataUtil.getVarCharConstValue("THEREFORE_CONN_AVAL_FLG", this.glblCmpyCd);
            if (!ZYPConstant.FLG_ON_Y.equals(thereforeConnAvalFlg)) {

                S21InfoLogOutput.println("NWAB412001 Therefore process was skipped. THEREFORE_CONN_AVAL_FLG is 'N'.");
                return;
            }

            Integer thereforeDocId;
            IThereforeService thereforeWebService = S21ThereforeWebServiceProxy.getInstance().getThereforeService();

            if (ZYPCommonFunc.hasValue(aquNum)) {

                thereforeDocId = callThereforeWebService(thereforeWebService, aquNum);

                if (thereforeDocId != null) {
                    writeLogLn("therefore Doc Id : %d", thereforeDocId);
                    //QC#23439
                    //String scanTime = getScanTimeFromTherefore(thereforeWebService, thereforeDocId);
                    String scanTime = "";
                    BigDecimal leaseDocId = null;  //Lease Document ID
                    Map<String, Object> thereforeValMap = getScanTimeFromTherefore(thereforeWebService, thereforeDocId);
                    if (thereforeValMap != null) {
                        if (thereforeValMap.get("SCANNED_DATE") != null) {
                            scanTime = (String) thereforeValMap.get("SCANNED_DATE");
                        }
                        if (thereforeValMap.get("LEASE_DOC_ID") != null) {
                            leaseDocId = (BigDecimal) thereforeValMap.get("LEASE_DOC_ID");
                        }
                    }
System.out.println("Out:SCANNED_DATE" + " : " + scanTime);
System.out.println("Out:LEASE_DOC_ID" + " : " + leaseDocId);
                    
                    DOC_MGT_SCAN_TRXTMsg docMgtScanTrxTMsg = new DOC_MGT_SCAN_TRXTMsg();
    
                    ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.glblCmpyCd, this.glblCmpyCd);
                    BigDecimal docMgtScanTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DOC_MGT_SCAN_TRX_SQ);
                    ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.docMgtScanTrxPk, docMgtScanTrxPk);
                    // docMgtScanTrxTMsg.cpoOrdNum is not set.
                    ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.docMgtDocId, new BigDecimal(thereforeDocId));
                    ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.docMgtScanTs, scanTime);
                    /** Therefore URL */
                    String thereforeUrl = EZDSystemEnv.getString("S21.therfore.attachment.url");
                    ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.othSysUrl, thereforeUrl + thereforeDocId);
                    ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.docMgtCatgCd, DOC_MGT_CATG.SALES_ORDER);
                    // docMgtScanTrxTMsg.docMgtBizDocNum is not set.
                    ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.aquNum, aquNum);

                    //QC#23439 Waiting for DA Release
                    if (ZYPCommonFunc.hasValue(leaseDocId)) {
                        ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.poRvwDocId, leaseDocId);
                        ZYPEZDItemValueSetter.setValue(docMgtScanTrxTMsg.poRvwSysUrl, thereforeUrl + leaseDocId);
                    }

                    EZDTBLAccessor.insert(docMgtScanTrxTMsg);
                } else {
                    writeLogLn("DOC_MGT_SCAN_TRX is not create because THEREFORE Document ID can't get");
                }
            }

        } finally {

            writeEndLogLn("thereforeDocumentAttach", aquNum);
        }
    }

    private Integer callThereforeWebService(IThereforeService thereforWebSerive, String aquNum) {

        writeStartLogLn("callThereforeWebService", aquNum);

        try {

            // call Therefore Web Service
            Integer thereforeDocId = null;

            DOC_MGT_CATGTMsg catg = new DOC_MGT_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(catg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(catg.docMgtCatgCd, DOC_MGT_CATG.SALES_ORDER);
            catg = (DOC_MGT_CATGTMsg) S21FastTBLAccessor.findByKey(catg);
            if (catg == null || !ZYPCommonFunc.hasValue(catg.docMgtCatgNum)) {

                // null return
                return thereforeDocId;
            }

            // Mod Start 2018/03/20 QC#23991
//            String searchCondFldCd = ZYPCodeDataUtil.getVarCharConstValue("NWAB4120_THEREFORE_COND_FLD", this.glblCmpyCd);
            String searchCondFldCd = ZYPCodeDataUtil.getVarCharConstValue("THEREFORE_COND_FLD", this.glblCmpyCd);
            // Mod End 2018/03/20 QC#23991
            if (!ZYPCommonFunc.hasValue(searchCondFldCd)) {

                // null return
                return thereforeDocId;
            }

            DOC_MGT_FLDTMsg fld = new DOC_MGT_FLDTMsg();
            ZYPEZDItemValueSetter.setValue(fld.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(fld.docMgtFldCd, searchCondFldCd);
            fld = (DOC_MGT_FLDTMsg) S21FastTBLAccessor.findByKey(fld);
            if (fld == null || !ZYPCommonFunc.hasValue(fld.docMgtFldNum)) {

                // null return
                return thereforeDocId;
            }

            // Set parameters
            ExecuteSingleQueryParams params = new ExecuteSingleQueryParams();
            // category
            QueryObject queryObject = new QueryObject();
            ArrayOfWSCondition conditions = new ArrayOfWSCondition();
            queryObject.setConditions(conditions);
            queryObject.setCategoryNo(catg.docMgtCatgNum.getValueInt());
            params.setQuery(queryObject);

            // condition
            WSCondition condition = new WSCondition();
            condition.setCondition(aquNum);

            condition.setFieldNoOrName(fld.docMgtFldNum.getValue().toPlainString());
            conditions.getWSCondition().add(condition);

            // Call Web Service
            ExecuteSingleQueryResponse response = thereforWebSerive.executeSingleQuery(params);

            java.util.List<WSQueryResultRow> rows = response.getQueryResult().getResultRows().getWSQueryResultRow();
            if (rows.size() > 0) {

                thereforeDocId = rows.get(0).getDocNo();
            }

            // null return
            return thereforeDocId;
        } finally {

            writeEndLogLn("callThereforeWebService", aquNum);
        }
    }
    @SuppressWarnings("unchecked")
    //private String getScanTimeFromTherefore(IThereforeService thereforeWebService, int docMgtDocId) {
    private Map<String, Object> getScanTimeFromTherefore(IThereforeService thereforeWebService, int docMgtDocId) {
        GetDocumentIndexDataParams docParam = new GetDocumentIndexDataParams();
        docParam.setDocNo(docMgtDocId);

        GetDocumentIndexDataResponse docRes = thereforeWebService.getDocumentIndexData(docParam);
        ArrayOfWSIndexDataItem arrayItems = docRes.getIndexData().getIndexDataItems();
        List<WSIndexDataItem> ItemList = arrayItems.getWSIndexDataItem();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("docMgtCatgCd", DOC_MGT_CATG.SALES_ORDER);
        ssmParam.put("docMgtFldDescTxtList", new String[] {"Scan Date", "Scanned Date", "Lease Document ID"});

        List<Map<String, Object>> resultSsmResult = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSearchDocMgtFld", ssmParam);
        if (resultSsmResult == null || resultSsmResult.isEmpty()) {
            return null;
        }

        Map<String, Object> retMap = new HashMap<String, Object>();
        String scanTime = null;
        for (Map<String, Object> result : resultSsmResult) {
            for (WSIndexDataItem item : ItemList) {
                //QC#23439
                if ("Lease Document ID".equals(result.get("DOC_MGT_FLD_DESC_TXT"))) {
                    WSIndexDataInt leaseDocIdData = item.getIntIndexData();
                    if (leaseDocIdData == null) {
                        continue;
                    }
                    int leaseDocIdFldNum = leaseDocIdData.getFieldNo();
                    int defFldNum  = Integer.valueOf(result.get("DOC_MGT_FLD_NUM").toString());
                    if (leaseDocIdFldNum != defFldNum) {
                        continue;
                    }
                    if (leaseDocIdData.getDataValue() != null) {
                        BigDecimal val = new BigDecimal(leaseDocIdData.getDataValue().toString());
System.out.println("Lease Doc ID[" + val + "]");
                        retMap.put("LEASE_DOC_ID", val);
                    } else {
System.out.println("Lease Doc ID[No Data]");
                    }
                } else if ("Scan Date".equals(result.get("DOC_MGT_FLD_DESC_TXT")) || "Scanned Date".equals(result.get("DOC_MGT_FLD_DESC_TXT"))) {
                    WSIndexDataDate date = item.getDateIndexData();
                    if (date == null) {
                        continue;
                    }

                    int dateFldNum = date.getFieldNo();
                    int defFldNum  = Integer.valueOf(result.get("DOC_MGT_FLD_NUM").toString());
                    if (dateFldNum != defFldNum) {
                        continue;
                    }

                    XMLGregorianCalendar greCalDate = date.getDataValue();
                    if (greCalDate == null) {
                        continue;
                    }

                    StringBuilder sb = new StringBuilder();
                    sb.append(ZYPCommonFunc.leftPad(String.valueOf(greCalDate.getYear()), 4, "0"));
                    sb.append(ZYPCommonFunc.leftPad(String.valueOf(greCalDate.getMonth()), 2, "0"));
                    sb.append(ZYPCommonFunc.leftPad(String.valueOf(greCalDate.getDay()), 2, "0"));
                    sb.append(ZYPCommonFunc.leftPad(String.valueOf(greCalDate.getHour()), 2, "0"));
                    sb.append(ZYPCommonFunc.leftPad(String.valueOf(greCalDate.getMinute()), 2, "0"));
                    sb.append(ZYPCommonFunc.rightPad(ZYPCommonFunc.leftPad(String.valueOf(greCalDate.getSecond()), 2, "0"), 5, "0"));
                    scanTime = sb.toString();
System.out.println("Scan Date[" + scanTime + "]");
                    retMap.put("SCANNED_DATE", scanTime);
                }
            }
        }

        return retMap;
    }
    // Add End 2018/01/19 QC#23598
}
