/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB414001;

import static com.canon.cusa.s21.batch.NWA.NWAB414001.constant.NWAB414001Constant.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.math.BigDecimal;
import parts.dbcommon.EZDFastTBLAccessor;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import business.db.SOM_HDR_VIEW_SNAPTMsg;
import business.db.SOM_CONFIG_VIEW_SNAPTMsg;
import business.db.SOM_LINE_VIEW_SNAPTMsg;
import business.db.SOM_SOFT_COST_VIEW_SNAPTMsg;
import business.db.SOM_SVC_HDR_VIEW_SNAPTMsg;
import business.db.SOM_SVC_COPR_VIEW_SNAPTMsg;
import business.db.SOM_PRFT_VIEW_SNAPTMsg;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;


/**
 * <pre>
 * Create Snapshot for SOM CFS View
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/23/2016   SRAA            K.Aratani       Create          
 * 02/14/2017   SRAA            K.Aratani       Update          QC#17389
 * 04/14/2017   SRAA            K.Aratani       Update          QC#18306
 * 
 * </pre>
 */
public class NWAB414001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Interface Id */
    private String intfcId = null;

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Normal Count */
    private int normalAllCount;

    /** Error Count */
    private int errorAllCount;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * A main method for batch application start.
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB414001().executeBatch(NWAB414001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }
        // Interface Id
        this.intfcId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.intfcId)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Interface ID" });
        }

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        
        this.termCd = TERM_CD.NORMAL_END;
        this.normalAllCount = 0;
        this.errorAllCount = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void mainRoutine() {
        
        Map<String, Object> param0 = new HashMap<String, Object>();
        param0.put("intfcId", this.intfcId);
        param0.put("glblCmpyCd", this.glblCmpyCd);
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getMaxTransactionPerSomQuoteID", param0);
        for (Map<String, Object> map : list) {
            //Exists SOM_HDR_VIEW_SNAP by Som Quote Id?
            //If No  -->  Insert tables
            //    SOM_HDR_VIEW_SNAP, SOM_CONFIG_VIEW_SNAP, SOM_LINE_VIEW_SNAP, SOM_SOFT_COST_VIEW_SNAP
            //    SOM_SVC_HDR_VIEW_SNAP, SOM_SVC_COPR_VIEW_SNAP, SOM_PRFT_VIEW_SNAP
            if (map.get("SOM_HDR_VIEW_SNAP_PK") == null) {
                
                if (!insertSOM_HDR_VIEW_SNAP((BigDecimal) map.get("SOM_QUOTE_ID"), (BigDecimal) map.get("TRANSACTION_ID"), false)) {
                    //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                    throw new S21AbendException("ZZMM0001E", new String[]{"SOM_HDR_VIEW_SNAP", "TRANSACTION_ID,SOM_QUOTE_ID", (BigDecimal) map.get("TRANSACTION_ID")+","+(BigDecimal) map.get("SOM_QUOTE_ID")});
                }
                if (!insertSOM_CONFIG_VIEW_SNAP((BigDecimal) map.get("SOM_QUOTE_ID"), (BigDecimal) map.get("TRANSACTION_ID"), false)) {
                    //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                    throw new S21AbendException("ZZMM0001E", new String[]{"SOM_CONFIG_VIEW_SNAP", "TRANSACTION_ID,SOM_QUOTE_ID", (BigDecimal) map.get("TRANSACTION_ID")+","+(BigDecimal) map.get("SOM_QUOTE_ID")});
                }
                if (!insertSOM_LINE_VIEW_SNAP((BigDecimal) map.get("SOM_QUOTE_ID"), (BigDecimal) map.get("TRANSACTION_ID"), false)) {
                    //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                    throw new S21AbendException("ZZMM0001E", new String[]{"SOM_LINE_VIEW_SNAP", "TRANSACTION_ID,SOM_QUOTE_ID", (BigDecimal) map.get("TRANSACTION_ID")+","+(BigDecimal) map.get("SOM_QUOTE_ID")});
                }
                if (!insertSOM_SOFT_COST_VIEW_SNAP((BigDecimal) map.get("SOM_QUOTE_ID"), (BigDecimal) map.get("TRANSACTION_ID"), false)) {
                    //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                    throw new S21AbendException("ZZMM0001E", new String[]{"SOM_SOFT_COST_VIEW_SNAP", "TRANSACTION_ID,SOM_QUOTE_ID", (BigDecimal) map.get("TRANSACTION_ID")+","+(BigDecimal) map.get("SOM_QUOTE_ID")});
                }
                if (!insertSOM_SVC_HDR_VIEW_SNAP((BigDecimal) map.get("SOM_QUOTE_ID"), (BigDecimal) map.get("TRANSACTION_ID"), false)) {
                    //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                    throw new S21AbendException("ZZMM0001E", new String[]{"SOM_SVC_HDR_VIEW_SNAP", "TRANSACTION_ID,SOM_QUOTE_ID", (BigDecimal) map.get("TRANSACTION_ID")+","+(BigDecimal) map.get("SOM_QUOTE_ID")});
                }
                if (!insertSOM_SVC_COPR_VIEW_SNAP((BigDecimal) map.get("SOM_QUOTE_ID"), (BigDecimal) map.get("TRANSACTION_ID"), false)) {
                    //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                    throw new S21AbendException("ZZMM0001E", new String[]{"SOM_SVC_COPR_VIEW_SNAP", "TRANSACTION_ID,SOM_QUOTE_ID", (BigDecimal) map.get("TRANSACTION_ID")+","+(BigDecimal) map.get("SOM_QUOTE_ID")});
                }
                if (!insertSOM_PRFT_VIEW_SNAP((BigDecimal) map.get("SOM_QUOTE_ID"), (BigDecimal) map.get("TRANSACTION_ID"), false)) {
                    //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                    throw new S21AbendException("ZZMM0001E", new String[]{"SOM_PRFT_VIEW_SNAP", "TRANSACTION_ID,SOM_QUOTE_ID", (BigDecimal) map.get("TRANSACTION_ID")+","+(BigDecimal) map.get("SOM_QUOTE_ID")});
                }
                
            //Else If SUBMT_TO_OM_TS <> SUBMT_TO_OM_TS
            //    Delete Tables -->  Insert Tables
            //        SOM_HDR_VIEW_SNAP, SOM_CONFIG_VIEW_SNAP, SOM_LINE_VIEW_SNAP, SOM_SOFT_COST_VIEW_SNAP
            //        SOM_SVC_HDR_VIEW_SNAP, SOM_SVC_COPR_VIEW_SNAP
            } else {

                if (!insertSOM_HDR_VIEW_SNAP((BigDecimal) map.get("SOM_QUOTE_ID"), (BigDecimal) map.get("TRANSACTION_ID"), true)) {
                    //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                    throw new S21AbendException("ZZMM0001E", new String[]{"SOM_HDR_VIEW_SNAP", "TRANSACTION_ID,SOM_QUOTE_ID", (BigDecimal) map.get("TRANSACTION_ID")+","+(BigDecimal) map.get("SOM_QUOTE_ID")});
                }
                if (!insertSOM_CONFIG_VIEW_SNAP((BigDecimal) map.get("SOM_QUOTE_ID"), (BigDecimal) map.get("TRANSACTION_ID"), true)) {
                    //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                    throw new S21AbendException("ZZMM0001E", new String[]{"SOM_CONFIG_VIEW_SNAP", "TRANSACTION_ID,SOM_QUOTE_ID", (BigDecimal) map.get("TRANSACTION_ID")+","+(BigDecimal) map.get("SOM_QUOTE_ID")});
                }
                if (!insertSOM_LINE_VIEW_SNAP((BigDecimal) map.get("SOM_QUOTE_ID"), (BigDecimal) map.get("TRANSACTION_ID"), true)) {
                    //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                    throw new S21AbendException("ZZMM0001E", new String[]{"SOM_LINE_VIEW_SNAP", "TRANSACTION_ID,SOM_QUOTE_ID", (BigDecimal) map.get("TRANSACTION_ID")+","+(BigDecimal) map.get("SOM_QUOTE_ID")});
                }
                if (!insertSOM_SOFT_COST_VIEW_SNAP((BigDecimal) map.get("SOM_QUOTE_ID"), (BigDecimal) map.get("TRANSACTION_ID"), true)) {
                    //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                    throw new S21AbendException("ZZMM0001E", new String[]{"SOM_SOFT_COST_VIEW_SNAP", "TRANSACTION_ID,SOM_QUOTE_ID", (BigDecimal) map.get("TRANSACTION_ID")+","+(BigDecimal) map.get("SOM_QUOTE_ID")});
                }
                if (!insertSOM_SVC_HDR_VIEW_SNAP((BigDecimal) map.get("SOM_QUOTE_ID"), (BigDecimal) map.get("TRANSACTION_ID"), true)) {
                    //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                    throw new S21AbendException("ZZMM0001E", new String[]{"SOM_SVC_HDR_VIEW_SNAP", "TRANSACTION_ID,SOM_QUOTE_ID", (BigDecimal) map.get("TRANSACTION_ID")+","+(BigDecimal) map.get("SOM_QUOTE_ID")});
                }
                if (!insertSOM_SVC_COPR_VIEW_SNAP((BigDecimal) map.get("SOM_QUOTE_ID"), (BigDecimal) map.get("TRANSACTION_ID"), true)) {
                    //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                    throw new S21AbendException("ZZMM0001E", new String[]{"SOM_SVC_COPR_VIEW_SNAP", "TRANSACTION_ID,SOM_QUOTE_ID", (BigDecimal) map.get("TRANSACTION_ID")+","+(BigDecimal) map.get("SOM_QUOTE_ID")});
                }
                if (!insertSOM_PRFT_VIEW_SNAP((BigDecimal) map.get("SOM_QUOTE_ID"), (BigDecimal) map.get("TRANSACTION_ID"), true)) {
                    //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                    throw new S21AbendException("ZZMM0001E", new String[]{"SOM_PRFT_VIEW_SNAP", "TRANSACTION_ID,SOM_QUOTE_ID", (BigDecimal) map.get("TRANSACTION_ID")+","+(BigDecimal) map.get("SOM_QUOTE_ID")});
                }
            }
            commit();
        }
    }

    @Override
    protected void termRoutine() {
        if (this.errorAllCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalAllCount, this.errorAllCount);
    }
    
    @SuppressWarnings("unchecked")
    private boolean insertSOM_HDR_VIEW_SNAP(BigDecimal somQuoteId, BigDecimal transactionId, boolean delFlg) {
        if (delFlg) {
            Map<String, Object> pDel = new HashMap<String, Object>();
            pDel.put("glblCmpyCd", this.glblCmpyCd);
            pDel.put("somQuoteId", somQuoteId);
            List<Map<String, Object>> somConfigViewSnapList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSomHdrViewSnapPk", pDel);
            for (Map<String, Object> somConfigViewSnapMap : somConfigViewSnapList) {
                SOM_HDR_VIEW_SNAPTMsg hdr = new SOM_HDR_VIEW_SNAPTMsg();
                ZYPEZDItemValueSetter.setValue(hdr.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(hdr.somHdrViewSnapPk, (BigDecimal) somConfigViewSnapMap.get("SOM_HDR_VIEW_SNAP_PK"));
                hdr = (SOM_HDR_VIEW_SNAPTMsg) S21FastTBLAccessor.findByKey(hdr);
                if (hdr != null) {
                    List<SOM_HDR_VIEW_SNAPTMsg> delSomHdrViewSnapList = new ArrayList<SOM_HDR_VIEW_SNAPTMsg>();
                    delSomHdrViewSnapList.add(hdr);
                    S21FastTBLAccessor.removePhysical(delSomHdrViewSnapList.toArray(new SOM_HDR_VIEW_SNAPTMsg[0]));
                    this.normalAllCount++;
                }
            }
        }
        
        Map<String, Object> pCrat = new HashMap<String, Object>();
        pCrat.put("somQuoteId", somQuoteId);
        pCrat.put("intfcId", this.intfcId);
        pCrat.put("transactionId", transactionId);
        List<Map<String, Object>> nwai4120_01List = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getNWAI4120_01", pCrat);
        for (Map<String, Object> nwai4120_01Map : nwai4120_01List) {
            SOM_HDR_VIEW_SNAPTMsg hdr = new SOM_HDR_VIEW_SNAPTMsg();
            ZYPEZDItemValueSetter.setValue(hdr.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(hdr.somHdrViewSnapPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SOM_HDR_VIEW_SNAP_SQ));
            ZYPEZDItemValueSetter.setValue(hdr.somQuoteId, (BigDecimal) nwai4120_01Map.get("SOM_QUOTE_ID"));  //SOM_QUOTE_ID
            ZYPEZDItemValueSetter.setValue(hdr.somQuoteNum, (BigDecimal) nwai4120_01Map.get("SOM_QUOTE_NUM"));  //SOM_QUOTE_NUM
            ZYPEZDItemValueSetter.setValue(hdr.somShipToCustId, (BigDecimal) nwai4120_01Map.get("SOM_SHIP_TO_CUST_ID"));  //SOM_SHIP_TO_CUST_ID
            ZYPEZDItemValueSetter.setValue(hdr.somStpNum, (BigDecimal) nwai4120_01Map.get("SOM_SHIP_TO_PTY_NUM"));  //SOM_STP_NUM
            ZYPEZDItemValueSetter.setValue(hdr.somFmDeptId, (BigDecimal) nwai4120_01Map.get("SOM_FM_DEPT_ID"));  //SOM_FM_DEPT_ID
            ZYPEZDItemValueSetter.setValue(hdr.somDiscHdrAmt, (BigDecimal) nwai4120_01Map.get("SOM_DISC_AMT"));  //SOM_DISC_HDR_AMT
            ZYPEZDItemValueSetter.setValue(hdr.somLeaseTermAot, (BigDecimal) nwai4120_01Map.get("SOM_LEASE_TERM_AOT"));
            ZYPEZDItemValueSetter.setValue(hdr.somStepCnt, (BigDecimal) nwai4120_01Map.get("SOM_STEP_CNT"));
            ZYPEZDItemValueSetter.setValue(hdr.somSubSvcAot, (BigDecimal) nwai4120_01Map.get("SOM_SUB_SVC_AOT"));
            ZYPEZDItemValueSetter.setValue(hdr.somPrcListId, (BigDecimal) nwai4120_01Map.get("SOM_PRC_LIST_ID"));
            ZYPEZDItemValueSetter.setValue(hdr.negoDealAmt, (BigDecimal) nwai4120_01Map.get("NEGO_DEAL_AMT"));
            ZYPEZDItemValueSetter.setValue(hdr.leasePmtAmt, (BigDecimal) nwai4120_01Map.get("LEASE_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseSvcPmtAmt, (BigDecimal) nwai4120_01Map.get("LEASE_SVC_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseEquipPmtAmt, (BigDecimal) nwai4120_01Map.get("LEASE_EQUIP_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseUpgAmt, (BigDecimal) nwai4120_01Map.get("LEASE_UPG_AMT"));
            ZYPEZDItemValueSetter.setValue(hdr.somRevAmt, (BigDecimal) nwai4120_01Map.get("SOM_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(hdr.istlFeeAmt, (BigDecimal) nwai4120_01Map.get("ISTL_FEE_AMT"));
            ZYPEZDItemValueSetter.setValue(hdr.somRebAmt, (BigDecimal) nwai4120_01Map.get("SOM_REB_AMT"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseDownPmtAmt, (BigDecimal) nwai4120_01Map.get("LEASE_DOWN_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseInEligFinAmt, (BigDecimal) nwai4120_01Map.get("LEASE_IN_ELIG_FIN_AMT"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseCrAmt, (BigDecimal) nwai4120_01Map.get("LEASE_CR_AMT"));
            ZYPEZDItemValueSetter.setValue(hdr.delyChrgAmt, (BigDecimal) nwai4120_01Map.get("DELY_CHRG_AMT"));
            ZYPEZDItemValueSetter.setValue(hdr.somDepAmt, (BigDecimal) nwai4120_01Map.get("SOM_DEP_AMT"));
            ZYPEZDItemValueSetter.setValue(hdr.secDepAmt, (BigDecimal) nwai4120_01Map.get("SEC_DEP_AMT"));
            ZYPEZDItemValueSetter.setValue(hdr.somCratTs, (String) nwai4120_01Map.get("CRAT_TS"));  //SOM_CRAT_TS
            ZYPEZDItemValueSetter.setValue(hdr.erlstDelyTs, (String) nwai4120_01Map.get("ERLST_DELY_TS"));
            ZYPEZDItemValueSetter.setValue(hdr.lastUpdTs, (String) nwai4120_01Map.get("LAST_UPD_TS"));
            ZYPEZDItemValueSetter.setValue(hdr.somLeaseRate, (BigDecimal) nwai4120_01Map.get("SOM_LEASE_RATE"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseTaxRate, (BigDecimal) nwai4120_01Map.get("LEASE_TAX_RATE"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseSoftCostRate, (BigDecimal) nwai4120_01Map.get("LEASE_SOFT_COST_RATE"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseInEligRate, (BigDecimal) nwai4120_01Map.get("LEASE_IN_ELIG_RATE"));
            ZYPEZDItemValueSetter.setValue(hdr.somSlsTaxRate, (BigDecimal) nwai4120_01Map.get("SALES_TAX_RATE"));  //SOM_SLS_TAX_RATE
            ZYPEZDItemValueSetter.setValue(hdr.somRebRate, (BigDecimal) nwai4120_01Map.get("SOM_REB_RATE"));
            ZYPEZDItemValueSetter.setValue(hdr.somQuoteNm, (String) nwai4120_01Map.get("SOM_QUOTE_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.somQuoteStsNm, (String) nwai4120_01Map.get("SOM_QUOTE_STS_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.somSlsRepId, (String) nwai4120_01Map.get("SOM_SLS_REP_ID"));
            ZYPEZDItemValueSetter.setValue(hdr.somSlsRepLastNm, (String) nwai4120_01Map.get("SOM_SLS_REP_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.somSlsRepFirstNm, (String) nwai4120_01Map.get("SOM_SLS_REP_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.somBrNm, (String) nwai4120_01Map.get("SOM_BR_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.somAcctMgrTxt, (String) nwai4120_01Map.get("SOM_ACCT_MGR_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.rebMercCd, (String) nwai4120_01Map.get("REB_MERC_CD"));
            ZYPEZDItemValueSetter.setValue(hdr.csaPmtFirstNm, (String) nwai4120_01Map.get("CSA_PMT_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.csaPmtLastNm, (String) nwai4120_01Map.get("CSA_PMT_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.csaPmtPhoTxt, (String) nwai4120_01Map.get("CSA_PMT_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.csaPmtPhoExtnTxt, (String) nwai4120_01Map.get("CSA_PMT_PHO_EXT_TXT"));  //CSA_PMT_PHO_EXTN_TXT
            ZYPEZDItemValueSetter.setValue(hdr.csaPmtEmlTxt, (String) nwai4120_01Map.get("CSA_PMT_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.csaPmtTpTxt, (String) nwai4120_01Map.get("CSA_PMT_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.maintBllgStTxt, (String) nwai4120_01Map.get("MAINT_BLLG_ST_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.maintBllgZipTxt, (String) nwai4120_01Map.get("MAINT_BLLG_ZIP_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.maintBllgCntyTxt, (String) nwai4120_01Map.get("MAINT_BLLG_CNTY_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.bidPrcListNm, (String) nwai4120_01Map.get("BID_PRC_LIST_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.delyFaxTxt, (String) nwai4120_01Map.get("DELY_FAX_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.maintPoVrsnTxt, (String) nwai4120_01Map.get("MAINT_PO_VRSN_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.maintBillToLastNm, (String) nwai4120_01Map.get("MAINT_BILL_TO_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.maintBillToPhoTxt, (String) nwai4120_01Map.get("MAINT_BILL_TO_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.maintBillToPhoExtnTxt, (String) nwai4120_01Map.get("MAINT_BILL_TO_PHO_EXT_TXT"));  //MAINT_BILL_TO_PHO_EXTN_TXT
            ZYPEZDItemValueSetter.setValue(hdr.maintBillToEmlTxt, (String) nwai4120_01Map.get("MAINT_BILL_TO_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.maintBllgAddrDescTxt, (String) nwai4120_01Map.get("MAINT_BLLG_ADDR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.maintBllgCtyDescTxt, (String) nwai4120_01Map.get("MAINT_BLLG_CITY_DESC_TXT"));  //MAINT_BLLG_CTY_DESC_TXT
            ZYPEZDItemValueSetter.setValue(hdr.subSvcCtacPhoTxt, (String) nwai4120_01Map.get("SUB_SVC_CTAC_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.subSvcCtacEmlTxt, (String) nwai4120_01Map.get("SUB_SVC_CTAC_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.markViewBrcdTxt, (String) nwai4120_01Map.get("MARK_VIEW_BRCD_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.somSpiffFirstDtlTxt, (String) nwai4120_01Map.get("SOM_SPIFF_DTL_TXT_01"));  //SOM_SPIFF_FIRST_DTL_TXT
            ZYPEZDItemValueSetter.setValue(hdr.maintBillIndSomTxt, (String) nwai4120_01Map.get("MAINT_BILL_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.maintBillToFirstNm, (String) nwai4120_01Map.get("MAINT_BILL_TO_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.chkAgmtIndSomTxt, (String) nwai4120_01Map.get("CHK_AGMT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.realFairIndSomTxt, (String) nwai4120_01Map.get("REAL_FAIR_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.subSvcPmtTermTxt, (String) nwai4120_01Map.get("SUB_SVC_PMT_TERM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.subSvcAgmtTermTxt, (String) nwai4120_01Map.get("SUB_SVC_AGMT_TERM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.subSvcCtacFirstNm, (String) nwai4120_01Map.get("SUB_SVC_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.subSvcCtacLastNm, (String) nwai4120_01Map.get("SUB_SVC_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.qtlyPmtTxt, (String) nwai4120_01Map.get("QTLY_PMT_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.othLeaseIndSomTxt, (String) nwai4120_01Map.get("OTH_LEASE_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.othPmtLeaseTxt, (String) nwai4120_01Map.get("OTH_PMT_LEASE_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.achvRteCd, (String) nwai4120_01Map.get("ACH_RTE_CD"));  //ACHV_RTE_CD
            ZYPEZDItemValueSetter.setValue(hdr.achvAcctNum, (String) nwai4120_01Map.get("ACH_ACCT_NUM"));  //ACHV_ACCT_NUM
            ZYPEZDItemValueSetter.setValue(hdr.nonRfIndSomTxt, (String) nwai4120_01Map.get("NON_RF_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.delyInstnTxt, (String) nwai4120_01Map.get("DELY_INSTN_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.zeroDueIndSomTxt, (String) nwai4120_01Map.get("ZERO_DUE_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.svcPrvdTxt, (String) nwai4120_01Map.get("SVC_PRVD_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseTpTxt, (String) nwai4120_01Map.get("LEASE_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseFaxIndSomTxt, (String) nwai4120_01Map.get("LEASE_FAX_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.mthPmtIndSomTxt, (String) nwai4120_01Map.get("MTH_PMT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.taxExemIndSomTxt, (String) nwai4120_01Map.get("TAX_EXEM_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.dclnMaintIndSomTxt, (String) nwai4120_01Map.get("DCLN_MAINT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.shipViaInstnTxt, (String) nwai4120_01Map.get("SHIP_VIA_INSTN_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.hrsOpTxt, (String) nwai4120_01Map.get("HRS_OP_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.elevIndSomTxt, (String) nwai4120_01Map.get("ELEV_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.loadDockIndSomTxt, (String) nwai4120_01Map.get("LOAD_DOCK_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.itPhoTxt, (String) nwai4120_01Map.get("IT_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.itPhoExtnTxt, (String) nwai4120_01Map.get("IT_PHO_EXT_TXT"));  //IT_PHO_EXTN_TXT
            ZYPEZDItemValueSetter.setValue(hdr.itEmlTxt, (String) nwai4120_01Map.get("IT_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.itCtacDeptTxt, (String) nwai4120_01Map.get("IT_CTAC_DEPT_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.poReqIndSomTxt, (String) nwai4120_01Map.get("PO_REQ_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.somPoNum, (String) nwai4120_01Map.get("SOM_PO_NUM"));
            ZYPEZDItemValueSetter.setValue(hdr.delyFirstNm, (String) nwai4120_01Map.get("DELY_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.delyPhoTxt, (String) nwai4120_01Map.get("DELY_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.delyPhoExtnTxt, (String) nwai4120_01Map.get("DELY_PHO_EXT_TXT"));  //DELY_PHO_EXTN_TXT
            ZYPEZDItemValueSetter.setValue(hdr.delyEmlTxt, (String) nwai4120_01Map.get("DELY_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.itLastNm, (String) nwai4120_01Map.get("IT_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.itFirstNm, (String) nwai4120_01Map.get("IT_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.othPmtTxtAquTxt, (String) nwai4120_01Map.get("OTH_PMT_TXT_AQU_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.somChkNum, (String) nwai4120_01Map.get("SOM_CHK_NUM"));
            ZYPEZDItemValueSetter.setValue(hdr.somCrCardNum, (String) nwai4120_01Map.get("SOM_CR_CARD_NUM"));
            ZYPEZDItemValueSetter.setValue(hdr.somCrCardExprTxt, (String) nwai4120_01Map.get("SOM_CR_CARD_EXPR_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.somCrCardNm, (String) nwai4120_01Map.get("SOM_CR_CARD_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.delyLastNm, (String) nwai4120_01Map.get("DELY_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.billToFaxTxt, (String) nwai4120_01Map.get("BILL_TO_FAX_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.billToEmlTxt, (String) nwai4120_01Map.get("BILL_TO_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.chkOrdIndSomTxt, (String) nwai4120_01Map.get("CHK_ORD_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.trxTpTxt, (String) nwai4120_01Map.get("TRX_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.netIndSomTxt, (String) nwai4120_01Map.get("NET_THRTY_IND_SOM_TXT"));  //NET_IND_SOM_TXT
            ZYPEZDItemValueSetter.setValue(hdr.othAquIndSomTxt, (String) nwai4120_01Map.get("OTH_AQU_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.istlBrDescTxt, (String) nwai4120_01Map.get("ISTL_BR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.somSpiffScdDtlTxt, (String) nwai4120_01Map.get("SOM_SPIFF_DTL_TXT_02"));  //SOM_SPIFF_SCD_DTL_TXT
            ZYPEZDItemValueSetter.setValue(hdr.billToLastNm, (String) nwai4120_01Map.get("BILL_TO_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.billToFirstNm, (String) nwai4120_01Map.get("BILL_TO_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.billToPhoTxt, (String) nwai4120_01Map.get("BILL_TO_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.billToPhoExtnTxt, (String) nwai4120_01Map.get("BILL_TO_PHO_EXT_TXT"));  //BILL_TO_PHO_EXTN_TXT
            ZYPEZDItemValueSetter.setValue(hdr.leasePoTxt, (String) nwai4120_01Map.get("LEASE_PRCH_ORDER_TXT"));  //LEASE_PO_TXT
            ZYPEZDItemValueSetter.setValue(hdr.leaseCrApplyToTxt, (String) nwai4120_01Map.get("LEASE_CR_APPLY_TO_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.istlRepNm, (String) nwai4120_01Map.get("ISTL_REP_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.istlRepTxt, (String) nwai4120_01Map.get("ISTL_REP_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.istlBrDrctrNm, (String) nwai4120_01Map.get("ISTL_BR_DRCTR_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.istlBrDrctrNum, (String) nwai4120_01Map.get("ISTL_BR_DRCTR_NUM"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseEquipTpTxt, (String) nwai4120_01Map.get("LEASE_EQUIP_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.cpcRntlIndSomTxt, (String) nwai4120_01Map.get("CPC_RNTL_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseRgIndSomTxt, (String) nwai4120_01Map.get("LEASE_RG_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseTrtyIndSomTxt, (String) nwai4120_01Map.get("LEASE_TRTY_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseCusaChTxt, (String) nwai4120_01Map.get("LEASE_CUSA_CH_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseAppTxt, (String) nwai4120_01Map.get("LEASE_APP_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.bllgCtyDescTxt, (String) nwai4120_01Map.get("BLLG_CITY_DESC_TXT"));  //BLLG_CTY_DESC_TXT
            ZYPEZDItemValueSetter.setValue(hdr.bllgStTxt, (String) nwai4120_01Map.get("BLLG_ST_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.bllgZipTxt, (String) nwai4120_01Map.get("BLLG_ZIP_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseCmpyNm, (String) nwai4120_01Map.get("LEASE_CMPY_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseSvcIndSomTxt, (String) nwai4120_01Map.get("LEASE_SVC_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.leaseTaxIndSomTxt, (String) nwai4120_01Map.get("LEASE_TAX_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.cusaChTxt, (String) nwai4120_01Map.get("CUSA_CH_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.svcInclIndSomTxt, (String) nwai4120_01Map.get("SVC_INCL_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.billToCustLongNm, (String) nwai4120_01Map.get("BILL_TO_CUST_LEGAL_NM"));  //BILL_TO_CUST_LONG_NM
            ZYPEZDItemValueSetter.setValue(hdr.billToDbaTxt, (String) nwai4120_01Map.get("BILL_TO_DBA_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.bllgCmpyTxt, (String) nwai4120_01Map.get("BLLG_CMPY_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.bllgAddrDescTxt, (String) nwai4120_01Map.get("BLLG_ADDR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.shipToCntyTxt, (String) nwai4120_01Map.get("SHIP_TO_CNTY_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.somShipToZipCd, (String) nwai4120_01Map.get("SOM_SHIP_TO_ZIP_CD"));
            ZYPEZDItemValueSetter.setValue(hdr.shipToCustPtySiteNum, (String) nwai4120_01Map.get("SHIP_TO_CUST_PTY_SITE_NUM"));
            ZYPEZDItemValueSetter.setValue(hdr.shipToCrmSlsId, (String) nwai4120_01Map.get("SHIP_TO_CRM_SLS_ID"));
            ZYPEZDItemValueSetter.setValue(hdr.shipToCrmSlsNum, (String) nwai4120_01Map.get("SHIP_TO_CRM_SLS_NUM"));
            ZYPEZDItemValueSetter.setValue(hdr.prcListDescTxt, (String) nwai4120_01Map.get("PRC_LIST_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.shipToAcctNum, (String) nwai4120_01Map.get("SHIP_TO_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(hdr.shipToCustLongNm, (String) nwai4120_01Map.get("SHIP_TO_CUST_LEGAL_NM"));  //SHIP_TO_CUST_LONG_NM
            ZYPEZDItemValueSetter.setValue(hdr.somShipToCustNm, (String) nwai4120_01Map.get("SOM_SHIP_TO_CUST_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.somShipFirstLineAddr, (String) nwai4120_01Map.get("SHIP_TO_ADDR_DESC_TXT_01"));  //SOM_SHIP_FIRST_LINE_ADDR
            ZYPEZDItemValueSetter.setValue(hdr.somShipScdLineAddr, (String) nwai4120_01Map.get("SHIP_TO_ADDR_DESC_TXT_02"));  //SOM_SHIP_SCD_LINE_ADDR
            ZYPEZDItemValueSetter.setValue(hdr.shipToCtyTxt, (String) nwai4120_01Map.get("SHIP_TO_CITY_TXT"));  //SHIP_TO_CTY_TXT
            ZYPEZDItemValueSetter.setValue(hdr.cratByTxt, (String) nwai4120_01Map.get("CRAT_BY_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.shipToStTxt, (String) nwai4120_01Map.get("SHIP_TO_ST_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.prcContrNum, (String) nwai4120_01Map.get("PRC_CONTR_NUM"));
            ZYPEZDItemValueSetter.setValue(hdr.prcContrNm, (String) nwai4120_01Map.get("PRC_CONTR_NM"));
            ZYPEZDItemValueSetter.setValue(hdr.prcContrPk, (BigDecimal) nwai4120_01Map.get("PRC_CONTR_PK"));
            ZYPEZDItemValueSetter.setValue(hdr.somCsmpNum, (String) nwai4120_01Map.get("SOM_CSMP_NUM"));
            ZYPEZDItemValueSetter.setValue(hdr.csmpCrAmt, (BigDecimal) nwai4120_01Map.get("CSMP_CR_AMT"));
            ZYPEZDItemValueSetter.setValue(hdr.csmpIndSomTxt, (String) nwai4120_01Map.get("CSMP_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(hdr.custIssPoDt, (String) nwai4120_01Map.get("CUST_ISS_PO_DT"));
            ZYPEZDItemValueSetter.setValue(hdr.submtToOmTs, (String) nwai4120_01Map.get("SUBMT_TO_OM_TS"));
            ZYPEZDItemValueSetter.setValue(hdr.somMaintPoNum, (String) nwai4120_01Map.get("SOM_MAINT_PO_NUM"));

            List<SOM_HDR_VIEW_SNAPTMsg> somHdrViewSnapList = new ArrayList<SOM_HDR_VIEW_SNAPTMsg>();
            somHdrViewSnapList.add(hdr);
            int result = EZDFastTBLAccessor.insert(somHdrViewSnapList.toArray(new SOM_HDR_VIEW_SNAPTMsg[somHdrViewSnapList.size()]));
            if (result != somHdrViewSnapList.size()) {
                //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                S21InfoLogOutput.println("ZZMM0001E", new String[]{"SOM_HDR_VIEW_SNAP", "SOM_QUOTE_ID", ((BigDecimal) nwai4120_01Map.get("SOM_QUOTE_ID")).toString()});
                return false;
            }
            this.normalAllCount++;
        }
        return true;
    }
    @SuppressWarnings("unchecked")
    private boolean insertSOM_CONFIG_VIEW_SNAP(BigDecimal somQuoteId, BigDecimal transactionId, boolean delFlg) {
        if (delFlg) {
            Map<String, Object> pDel = new HashMap<String, Object>();
            pDel.put("glblCmpyCd", this.glblCmpyCd);
            pDel.put("somQuoteId", somQuoteId);
            List<Map<String, Object>> somConfigViewSnapList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSomConfigViewSnapPk", pDel);
            for (Map<String, Object> somConfigViewSnapMap : somConfigViewSnapList) {
                SOM_CONFIG_VIEW_SNAPTMsg config = new SOM_CONFIG_VIEW_SNAPTMsg();
                ZYPEZDItemValueSetter.setValue(config.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(config.somConfigViewSnapPk, (BigDecimal) somConfigViewSnapMap.get("SOM_CONFIG_VIEW_SNAP_PK"));
                config = (SOM_CONFIG_VIEW_SNAPTMsg) S21FastTBLAccessor.findByKey(config);
                if (config != null) {
                    List<SOM_CONFIG_VIEW_SNAPTMsg> delSomConfigViewSnapList = new ArrayList<SOM_CONFIG_VIEW_SNAPTMsg>();
                    delSomConfigViewSnapList.add(config);
                    S21FastTBLAccessor.removePhysical(delSomConfigViewSnapList.toArray(new SOM_CONFIG_VIEW_SNAPTMsg[0]));
                    this.normalAllCount++;
                }
            }
        }
        Map<String, Object> pCrat = new HashMap<String, Object>();
        pCrat.put("somQuoteId", somQuoteId);
        pCrat.put("intfcId", this.intfcId);
        pCrat.put("transactionId", transactionId);
        List<Map<String, Object>> nwai4120_12List = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getNWAI4120_12", pCrat);
        for (Map<String, Object> nwai4120_12Map : nwai4120_12List) {
            SOM_CONFIG_VIEW_SNAPTMsg config = new SOM_CONFIG_VIEW_SNAPTMsg();
            ZYPEZDItemValueSetter.setValue(config.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(config.somConfigViewSnapPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SOM_CONFIG_VIEW_SNAP_SQ));
            ZYPEZDItemValueSetter.setValue(config.somQuoteId, (BigDecimal) nwai4120_12Map.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(config.somConfigId, (BigDecimal) nwai4120_12Map.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(config.somConfigLtrTxt, (String) nwai4120_12Map.get("SOM_CONFIG_LTR_TXT"));
            ZYPEZDItemValueSetter.setValue(config.somOrdQty, (BigDecimal) nwai4120_12Map.get("SOM_ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(config.somPsnCnt, (BigDecimal) nwai4120_12Map.get("SOM_PSN_CNT"));
            ZYPEZDItemValueSetter.setValue(config.somStepCnt, (BigDecimal) nwai4120_12Map.get("SOM_STEP_CNT"));
            ZYPEZDItemValueSetter.setValue(config.flPrcListId, (BigDecimal) nwai4120_12Map.get("FL_PRC_LIST_ID"));
            ZYPEZDItemValueSetter.setValue(config.bidPrcListId, (BigDecimal) nwai4120_12Map.get("BID_PRC_LIST_ID"));
            ZYPEZDItemValueSetter.setValue(config.lastUpdTs, (String) nwai4120_12Map.get("LAST_UPD_TS"));
            ZYPEZDItemValueSetter.setValue(config.erlstDelyTs, (String) nwai4120_12Map.get("ERLST_DELY_TS"));
            ZYPEZDItemValueSetter.setValue(config.somElevCnt, (BigDecimal) nwai4120_12Map.get("SOM_ELEV_CNT"));
            ZYPEZDItemValueSetter.setValue(config.loadDockCnt, (BigDecimal) nwai4120_12Map.get("LOAD_DOCK_CNT"));
            ZYPEZDItemValueSetter.setValue(config.somTaxRate, (BigDecimal) nwai4120_12Map.get("SOM_TAX_RATE"));
            ZYPEZDItemValueSetter.setValue(config.somCrmSlsTxt, (String) nwai4120_12Map.get("SOM_CRM_SLS_TXT"));
            ZYPEZDItemValueSetter.setValue(config.somAcctNum, (String) nwai4120_12Map.get("SOM_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(config.somShipFirstLineAddr, (String) nwai4120_12Map.get("SOM_SHIP_TO_ADDR_01"));  //SOM_SHIP_FIRST_LINE_ADDR
            ZYPEZDItemValueSetter.setValue(config.somShipScdLineAddr, (String) nwai4120_12Map.get("SOM_SHIP_TO_ADDR_02"));  //SOM_SHIP_SCD_LINE_ADDR
            ZYPEZDItemValueSetter.setValue(config.somCtyAddr, (String) nwai4120_12Map.get("SOM_CITY_ADDR"));  //SOM_CTY_ADDR
            ZYPEZDItemValueSetter.setValue(config.somStCd, (String) nwai4120_12Map.get("SOM_ST_CD"));
            ZYPEZDItemValueSetter.setValue(config.csaPmtTpTxt, (String) nwai4120_12Map.get("CSA_PMT_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(config.bidPrcListNm, (String) nwai4120_12Map.get("BID_PRC_LIST_NM"));
            ZYPEZDItemValueSetter.setValue(config.csaPmtFirstNm, (String) nwai4120_12Map.get("CSA_PMT_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(config.csaPmtLastNm, (String) nwai4120_12Map.get("CSA_PMT_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(config.csaPmtPhoExtnTxt, (String) nwai4120_12Map.get("CSA_PMT_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(config.csaPmtPhoExtnTxt, (String) nwai4120_12Map.get("CSA_PMT_PHO_EXT_DESC_TXT"));  //CSA_PMT_PHO_EXTN_TXT
            ZYPEZDItemValueSetter.setValue(config.csaPmtEmlTxt, (String) nwai4120_12Map.get("CSA_PMT_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(config.somDlrTxt, (String) nwai4120_12Map.get("SOM_DLR_TXT"));
            ZYPEZDItemValueSetter.setValue(config.mtrMethTxt, (String) nwai4120_12Map.get("MTR_METH_TXT"));
            ZYPEZDItemValueSetter.setValue(config.splTpTxt, (String) nwai4120_12Map.get("SPLIT_TP_TXT"));  //SPL_TP_TXT
            ZYPEZDItemValueSetter.setValue(config.addrLbTxt, (String) nwai4120_12Map.get("ADDR_LB_TXT"));
            ZYPEZDItemValueSetter.setValue(config.prcBookNm, (String) nwai4120_12Map.get("PRC_BOOK_NM"));
            ZYPEZDItemValueSetter.setValue(config.somFlPrcListNm, (String) nwai4120_12Map.get("SOM_FL_PRC_LIST_NM"));
            ZYPEZDItemValueSetter.setValue(config.scdPhoTxt, (String) nwai4120_12Map.get("SCD_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(config.scdPhoExtnTxt, (String) nwai4120_12Map.get("SCD_PHO_EXT_TXT"));  //SCD_PHO_EXTN_TXT
            ZYPEZDItemValueSetter.setValue(config.scdEmlTxt, (String) nwai4120_12Map.get("SCD_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(config.istlRepDescTxt, (String) nwai4120_12Map.get("ISTL_REP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(config.istlBrDescTxt, (String) nwai4120_12Map.get("ISTL_BR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(config.brDrctrDescTxt, (String) nwai4120_12Map.get("BR_DRCTR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(config.primCtacLastNm, (String) nwai4120_12Map.get("PRIM_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(config.primPhoTxt, (String) nwai4120_12Map.get("PRIM_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(config.primPhoExtnTxt, (String) nwai4120_12Map.get("PRIM_PHO_EXT_TXT"));  //PRIM_PHO_EXTN_TXT
            ZYPEZDItemValueSetter.setValue(config.primEmlTxt, (String) nwai4120_12Map.get("PRIM_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(config.scdCtacFirstNm, (String) nwai4120_12Map.get("SCD_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(config.scdCtacLastNm, (String) nwai4120_12Map.get("SCD_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(config.somCntyNm, (String) nwai4120_12Map.get("SOM_CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(config.somZipCd, (String) nwai4120_12Map.get("SOM_ZIP_CD"));
            ZYPEZDItemValueSetter.setValue(config.somShipViaTxt, (String) nwai4120_12Map.get("SOM_SHIP_VIA_TXT"));
            ZYPEZDItemValueSetter.setValue(config.hrsOpTxt, (String) nwai4120_12Map.get("HRS_OP_TXT"));
            ZYPEZDItemValueSetter.setValue(config.somInstnTxt, (String) nwai4120_12Map.get("SOM_INSTN_TXT"));
            ZYPEZDItemValueSetter.setValue(config.primCtacFirstNm, (String) nwai4120_12Map.get("PRIM_CTAC_FIRST_NM"));
            
            List<SOM_CONFIG_VIEW_SNAPTMsg> somConfigViewSnapList = new ArrayList<SOM_CONFIG_VIEW_SNAPTMsg>();
            somConfigViewSnapList.add(config);
            int result = EZDFastTBLAccessor.insert(somConfigViewSnapList.toArray(new SOM_CONFIG_VIEW_SNAPTMsg[somConfigViewSnapList.size()]));
            if (result != somConfigViewSnapList.size()) {
                //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                S21InfoLogOutput.println("ZZMM0001E", new String[]{"SOM_CONFIG_VIEW_SNAP", "SOM_QUOTE_ID", ((BigDecimal) nwai4120_12Map.get("SOM_QUOTE_ID")).toString()});
                return false;
            }
            this.normalAllCount++;
        }
        return true;
    }
    @SuppressWarnings("unchecked")
    private boolean insertSOM_LINE_VIEW_SNAP(BigDecimal somQuoteId, BigDecimal transactionId, boolean delFlg) {
        if (delFlg) {
            Map<String, Object> pDel = new HashMap<String, Object>();
            pDel.put("glblCmpyCd", this.glblCmpyCd);
            pDel.put("somQuoteId", somQuoteId);
            List<Map<String, Object>> somLineViewSnapList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSomLineViewSnapPk", pDel);
            for (Map<String, Object> somLineViewSnapMap : somLineViewSnapList) {
                SOM_LINE_VIEW_SNAPTMsg line = new SOM_LINE_VIEW_SNAPTMsg();
                ZYPEZDItemValueSetter.setValue(line.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(line.somLineViewSnapPk, (BigDecimal) somLineViewSnapMap.get("SOM_LINE_VIEW_SNAP_PK"));
                line = (SOM_LINE_VIEW_SNAPTMsg) S21FastTBLAccessor.findByKey(line);
                if (line != null) {
                    List<SOM_LINE_VIEW_SNAPTMsg> delSomLineViewSnapList = new ArrayList<SOM_LINE_VIEW_SNAPTMsg>();
                    delSomLineViewSnapList.add(line);
                    S21FastTBLAccessor.removePhysical(delSomLineViewSnapList.toArray(new SOM_LINE_VIEW_SNAPTMsg[0]));
                    this.normalAllCount++;
                }
            }
        }
        Map<String, Object> pCrat = new HashMap<String, Object>();
        pCrat.put("somQuoteId", somQuoteId);
        pCrat.put("intfcId", this.intfcId);
        pCrat.put("transactionId", transactionId);
        List<Map<String, Object>> nwai4120_10List = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getNWAI4120_10", pCrat);
        for (Map<String, Object> nwai4120_10Map : nwai4120_10List) {
            SOM_LINE_VIEW_SNAPTMsg line = new SOM_LINE_VIEW_SNAPTMsg();
            ZYPEZDItemValueSetter.setValue(line.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(line.somLineViewSnapPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SOM_LINE_VIEW_SNAP_SQ));
            ZYPEZDItemValueSetter.setValue(line.somQuoteId, (BigDecimal) nwai4120_10Map.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(line.somConfigId, (BigDecimal) nwai4120_10Map.get("SOM_CONFIG_ID"));
            ZYPEZDItemValueSetter.setValue(line.somSortOrdNum, (BigDecimal) nwai4120_10Map.get("SOM_SORT_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(line.somOrdQty, (BigDecimal) nwai4120_10Map.get("SOM_ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(line.somConfigQty, (BigDecimal) nwai4120_10Map.get("SOM_CONFIG_QTY"));
            ZYPEZDItemValueSetter.setValue(line.usedDemoId, (BigDecimal) nwai4120_10Map.get("USED_DEMO_ID"));
            ZYPEZDItemValueSetter.setValue(line.flPrcAmt, (BigDecimal) nwai4120_10Map.get("FL_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(line.prfPrcAmt, (BigDecimal) nwai4120_10Map.get("PRF_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(line.msrpListPrcAmt, (BigDecimal) nwai4120_10Map.get("MSRP_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(line.sellPrcListAmt, (BigDecimal) nwai4120_10Map.get("SELL_PRC_LIST_AMT"));
            ZYPEZDItemValueSetter.setValue(line.extSellPrcAmt, (BigDecimal) nwai4120_10Map.get("EXT_SELL_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(line.ltstPrcAmt, (BigDecimal) nwai4120_10Map.get("LTST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(line.extFlPrcAmt, (BigDecimal) nwai4120_10Map.get("EXT_FL_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(line.engnrBdlCnt, (BigDecimal) nwai4120_10Map.get("ENGNR_BDL_CNT"));
            ZYPEZDItemValueSetter.setValue(line.somMercCd, (String) nwai4120_10Map.get("SOM_MERC_CD"));
            ZYPEZDItemValueSetter.setValue(line.somMercNm, (String) nwai4120_10Map.get("SOM_MERC_NM"));
            ZYPEZDItemValueSetter.setValue(line.somMdlDescTxt, (String) nwai4120_10Map.get("SOM_MDL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(line.somMdseTpTxt, (String) nwai4120_10Map.get("SOM_MDSE_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(line.somSegTxt, (String) nwai4120_10Map.get("SOM_SEG_TXT"));
            ZYPEZDItemValueSetter.setValue(line.nonCoprIndSomTxt, "N");
            
            List<SOM_LINE_VIEW_SNAPTMsg> somLineViewSnapList = new ArrayList<SOM_LINE_VIEW_SNAPTMsg>();
            somLineViewSnapList.add(line);
            int result = EZDFastTBLAccessor.insert(somLineViewSnapList.toArray(new SOM_LINE_VIEW_SNAPTMsg[somLineViewSnapList.size()]));
            if (result != somLineViewSnapList.size()) {
                //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                S21InfoLogOutput.println("ZZMM0001E", new String[]{"SOM_LINE_VIEW_SNAP", "SOM_QUOTE_ID", ((BigDecimal) nwai4120_10Map.get("SOM_QUOTE_ID")).toString()});
                return false;
            }
            this.normalAllCount++;
        }
        return true;
    }
    @SuppressWarnings("unchecked")
    private boolean insertSOM_SOFT_COST_VIEW_SNAP(BigDecimal somQuoteId, BigDecimal transactionId, boolean delFlg) {
        if (delFlg) {
            Map<String, Object> pDel = new HashMap<String, Object>();
            pDel.put("glblCmpyCd", this.glblCmpyCd);
            pDel.put("somQuoteId", somQuoteId);
            List<Map<String, Object>> somLineViewSnapList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSomSoftCostViewSnapPk", pDel);
            for (Map<String, Object> somSoftCostViewSnapMap : somLineViewSnapList) {
                SOM_SOFT_COST_VIEW_SNAPTMsg softCost = new SOM_SOFT_COST_VIEW_SNAPTMsg();
                ZYPEZDItemValueSetter.setValue(softCost.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(softCost.somSoftCostViewSnapPk, (BigDecimal) somSoftCostViewSnapMap.get("SOM_SOFT_COST_VIEW_SNAP_PK"));
                softCost = (SOM_SOFT_COST_VIEW_SNAPTMsg) S21FastTBLAccessor.findByKey(softCost);
                if (softCost != null) {
                    List<SOM_SOFT_COST_VIEW_SNAPTMsg> delSomSoftCostViewSnapList = new ArrayList<SOM_SOFT_COST_VIEW_SNAPTMsg>();
                    delSomSoftCostViewSnapList.add(softCost);
                    S21FastTBLAccessor.removePhysical(delSomSoftCostViewSnapList.toArray(new SOM_SOFT_COST_VIEW_SNAPTMsg[0]));
                    this.normalAllCount++;
                }
            }
        }
        Map<String, Object> pCrat = new HashMap<String, Object>();
        pCrat.put("somQuoteId", somQuoteId);
        pCrat.put("intfcId", this.intfcId);
        pCrat.put("transactionId", transactionId);
        List<Map<String, Object>> nwai4120_05List = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getNWAI4120_05", pCrat);
        for (Map<String, Object> nwai4120_05Map : nwai4120_05List) {
            SOM_SOFT_COST_VIEW_SNAPTMsg softCost = new SOM_SOFT_COST_VIEW_SNAPTMsg();
            ZYPEZDItemValueSetter.setValue(softCost.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(softCost.somSoftCostViewSnapPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SOM_SOFT_COST_VIEW_SNAP_SQ));
            ZYPEZDItemValueSetter.setValue(softCost.somQuoteId, (BigDecimal) nwai4120_05Map.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(softCost.somSoftCostId, (BigDecimal) nwai4120_05Map.get("SOM_SOFT_COST_ID"));
            ZYPEZDItemValueSetter.setValue(softCost.somOrdQty, (BigDecimal) nwai4120_05Map.get("SOM_ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(softCost.mtrReadCnt, (BigDecimal) nwai4120_05Map.get("MTR_READ_CNT"));
            ZYPEZDItemValueSetter.setValue(softCost.somStepCnt, (BigDecimal) nwai4120_05Map.get("SOM_STEP_CNT"));
            ZYPEZDItemValueSetter.setValue(softCost.somPrcAmt, (BigDecimal) nwai4120_05Map.get("SOM_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(softCost.somExtPrcAmt, (BigDecimal) nwai4120_05Map.get("SOM_EXT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(softCost.erlstDelyTs, (String) nwai4120_05Map.get("ERLST_DELY_TS"));
            ZYPEZDItemValueSetter.setValue(softCost.somElevCnt, (BigDecimal) nwai4120_05Map.get("SOM_ELEV_CNT"));
            ZYPEZDItemValueSetter.setValue(softCost.loadDockCnt, (BigDecimal) nwai4120_05Map.get("LOAD_DOCK_CNT"));
            ZYPEZDItemValueSetter.setValue(softCost.somTaxRate, (BigDecimal) nwai4120_05Map.get("SOM_TAX_RATE"));
            ZYPEZDItemValueSetter.setValue(softCost.somMercItemCd, (String) nwai4120_05Map.get("SOM_MERC_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(softCost.somItemDescTxt, (String) nwai4120_05Map.get("SOM_ITEM_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.somDescTxt, (String) nwai4120_05Map.get("SOM_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.somMdlTxt, (String) nwai4120_05Map.get("SOM_MDL_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.quoteLineTpTxt, (String) nwai4120_05Map.get("QUOTE_LINE_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.somDescSerNum, (String) nwai4120_05Map.get("SOM_DESC_SER_NUM"));
            ZYPEZDItemValueSetter.setValue(softCost.somByotStCd, (String) nwai4120_05Map.get("SOM_BYOT_ST_CD"));
            ZYPEZDItemValueSetter.setValue(softCost.somByotZipCd, (String) nwai4120_05Map.get("SOM_BYOT_ZIP_CD"));
            ZYPEZDItemValueSetter.setValue(softCost.somByotLastNm, (String) nwai4120_05Map.get("SOM_BYOT_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(softCost.somByotPhoTxt, (String) nwai4120_05Map.get("SOM_BYOT_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.somByotPhoExtnTxt, (String) nwai4120_05Map.get("SOM_BYOT_PHO_EXT_TXT"));  //SOM_BYOT_PHO_EXTN_TXT
            ZYPEZDItemValueSetter.setValue(softCost.somByotFirstLineAddr, (String) nwai4120_05Map.get("SOM_BYOT_ADDR_01"));  //SOM_BYOT_FIRST_LINE_ADDR
            ZYPEZDItemValueSetter.setValue(softCost.somByotScdLineAddr, (String) nwai4120_05Map.get("SOM_BYOT_ADDR_02"));  //SOM_BYOT_SCD_LINE_ADDR
            ZYPEZDItemValueSetter.setValue(softCost.somByotCtyTxt, (String) nwai4120_05Map.get("SOM_BYOT_CITY_TXT"));  //SOM_BYOT_CTY_TXT
            ZYPEZDItemValueSetter.setValue(softCost.somSoftCostDlrTxt, (String) nwai4120_05Map.get("SOM_SOFT_COST_DLR_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.mtrMethTxt, (String) nwai4120_05Map.get("MTR_METH_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.splTpTxt, (String) nwai4120_05Map.get("SPLIT_TP_TXT"));  //SPL_TP_TXT
            ZYPEZDItemValueSetter.setValue(softCost.somByotNm, (String) nwai4120_05Map.get("SOM_BYOT_NM"));
            ZYPEZDItemValueSetter.setValue(softCost.somMnfTxt, (String) nwai4120_05Map.get("SOM_MNF_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.somByotFirstNm, (String) nwai4120_05Map.get("SOM_BYOT_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(softCost.scdPhoTxt, (String) nwai4120_05Map.get("SCD_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.scdPhoExtnTxt, (String) nwai4120_05Map.get("SCD_PHO_EXT_TXT"));  //SCD_PHO_EXTN_TXT
            ZYPEZDItemValueSetter.setValue(softCost.scdEmlTxt, (String) nwai4120_05Map.get("SCD_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.istlRepTxt, (String) nwai4120_05Map.get("ISTL_REP_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.istlBrTxt, (String) nwai4120_05Map.get("ISTL_BR_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.brDrctrTxt, (String) nwai4120_05Map.get("BR_DRCTR_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.primCtacLastNm, (String) nwai4120_05Map.get("PRIM_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(softCost.primPhoTxt, (String) nwai4120_05Map.get("PRIM_PHO_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.primPhoExtnTxt, (String) nwai4120_05Map.get("PRIM_PHO_EXT_TXT"));  //PRIM_PHO_EXTN_TXT
            ZYPEZDItemValueSetter.setValue(softCost.primEmlTxt, (String) nwai4120_05Map.get("PRIM_EML_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.scdCtacFirstNm, (String) nwai4120_05Map.get("SCD_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(softCost.scdCtacLastNm, (String) nwai4120_05Map.get("SCD_CTAC_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(softCost.somSoftCostCntyNm, (String) nwai4120_05Map.get("SOM_SOFT_COST_CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(softCost.somSoftCostZipCd, (String) nwai4120_05Map.get("SOM_SOFT_COST_ZIP_CD"));
            ZYPEZDItemValueSetter.setValue(softCost.shipViaTxt, (String) nwai4120_05Map.get("SHIP_VIA_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.hrsOpTxt, (String) nwai4120_05Map.get("HRS_OP_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.somSoftCostInstnTxt, (String) nwai4120_05Map.get("SOM_SOFT_COST_INSTN_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.primCtacFirstNm, (String) nwai4120_05Map.get("PRIM_CTAC_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(softCost.somCrmSlsTxt, (String) nwai4120_05Map.get("SOM_CRM_SLS_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.somAcctNum, (String) nwai4120_05Map.get("SOM_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(softCost.somSoftFirstLineAddr, (String) nwai4120_05Map.get("SOM_SOFT_COST_ADDR_01"));  //SOM_SOFT_FIRST_LINE_ADDR
            ZYPEZDItemValueSetter.setValue(softCost.somSoftScdLineAddr, (String) nwai4120_05Map.get("SOM_SOFT_COST_ADDR_02"));  //SOM_SOFT_SCD_LINE_ADDR
            ZYPEZDItemValueSetter.setValue(softCost.somSoftCostCtyTxt, (String) nwai4120_05Map.get("SOM_SOFT_COST_CITY_TXT"));  //SOM_SOFT_COST_CTY_TXT
            ZYPEZDItemValueSetter.setValue(softCost.somSoftCostStCd, (String) nwai4120_05Map.get("SOM_SOFT_COST_ST_CD"));
            ZYPEZDItemValueSetter.setValue(softCost.rtrnTpTxt, (String) nwai4120_05Map.get("RTRN_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.rtrnIstlTxt, (String) nwai4120_05Map.get("RTRN_ISTL_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.dtlDescTxt, (String) nwai4120_05Map.get("DTL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.cfsIndSomTxt, (String) nwai4120_05Map.get("CFS_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.nonCfsLeaseCmpyTxt, (String) nwai4120_05Map.get("NON_CFS_LEASE_CMPY_TXT"));
            ZYPEZDItemValueSetter.setValue(softCost.somPsnTxt, (String) nwai4120_05Map.get("SOM_PSN_TXT"));
            
            List<SOM_SOFT_COST_VIEW_SNAPTMsg> somSoftCostViewSnapList = new ArrayList<SOM_SOFT_COST_VIEW_SNAPTMsg>();
            somSoftCostViewSnapList.add(softCost);
            int result = EZDFastTBLAccessor.insert(somSoftCostViewSnapList.toArray(new SOM_SOFT_COST_VIEW_SNAPTMsg[somSoftCostViewSnapList.size()]));
            if (result != somSoftCostViewSnapList.size()) {
                //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                S21InfoLogOutput.println("ZZMM0001E", new String[]{"SOM_SOFT_COST_VIEW_SNAP", "SOM_QUOTE_ID", ((BigDecimal) nwai4120_05Map.get("SOM_QUOTE_ID")).toString()});
                return false;
            }
            this.normalAllCount++;
        }
        return true;
    }
    @SuppressWarnings("unchecked")
    private boolean insertSOM_SVC_HDR_VIEW_SNAP(BigDecimal somQuoteId, BigDecimal transactionId, boolean delFlg) {
        if (delFlg) {
            Map<String, Object> pDel = new HashMap<String, Object>();
            pDel.put("glblCmpyCd", this.glblCmpyCd);
            pDel.put("somQuoteId", somQuoteId);
            List<Map<String, Object>> somSvcHdrViewSnapList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSomSvcHdrViewSnapPk", pDel);
            for (Map<String, Object> somSvcHdrViewSnapMap : somSvcHdrViewSnapList) {
                SOM_SVC_HDR_VIEW_SNAPTMsg svcHdr = new SOM_SVC_HDR_VIEW_SNAPTMsg();
                ZYPEZDItemValueSetter.setValue(svcHdr.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(svcHdr.somSvcHdrViewSnapPk, (BigDecimal) somSvcHdrViewSnapMap.get("SOM_SVC_HDR_VIEW_SNAP_PK"));
                svcHdr = (SOM_SVC_HDR_VIEW_SNAPTMsg) S21FastTBLAccessor.findByKey(svcHdr);
                if (svcHdr != null) {
                    List<SOM_SVC_HDR_VIEW_SNAPTMsg> delSomSvcHdrViewSnapList = new ArrayList<SOM_SVC_HDR_VIEW_SNAPTMsg>();
                    delSomSvcHdrViewSnapList.add(svcHdr);
                    S21FastTBLAccessor.removePhysical(delSomSvcHdrViewSnapList.toArray(new SOM_SVC_HDR_VIEW_SNAPTMsg[0]));
                    this.normalAllCount++;
                }
            }
        }
        Map<String, Object> pCrat = new HashMap<String, Object>();
        pCrat.put("somQuoteId", somQuoteId);
        pCrat.put("intfcId", this.intfcId);
        pCrat.put("transactionId", transactionId);
        List<Map<String, Object>> nwai4120_16List = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getNWAI4120_16", pCrat);
        for (Map<String, Object> nwai4120_16Map : nwai4120_16List) {
            SOM_SVC_HDR_VIEW_SNAPTMsg svcHdr = new SOM_SVC_HDR_VIEW_SNAPTMsg();
            ZYPEZDItemValueSetter.setValue(svcHdr.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcHdr.somSvcHdrViewSnapPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SOM_SVC_HDR_VIEW_SNAP_SQ));
            ZYPEZDItemValueSetter.setValue(svcHdr.somSrvId, (BigDecimal) nwai4120_16Map.get("SOM_SRV_ID"));
            ZYPEZDItemValueSetter.setValue(svcHdr.somQuoteId, (BigDecimal) nwai4120_16Map.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(svcHdr.fleetTermNum, (BigDecimal) nwai4120_16Map.get("FLEET_TERM_NUM"));
            ZYPEZDItemValueSetter.setValue(svcHdr.fleetVolClrCnt, (BigDecimal) nwai4120_16Map.get("FLEET_VOL_CLR_CNT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.fleetVolBwCnt, (BigDecimal) nwai4120_16Map.get("FLEET_VOL_BW_CNT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.svcCostId, (BigDecimal) nwai4120_16Map.get("SVC_COST_ID"));
            ZYPEZDItemValueSetter.setValue(svcHdr.svcCostTrnsfAmt, (BigDecimal) nwai4120_16Map.get("SVC_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.bwOvagCostTrnsfAmt, (BigDecimal) nwai4120_16Map.get("CLR_OVAG_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.finSvcCostAmt, (BigDecimal) nwai4120_16Map.get("BW_OVAG_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.finSvcCostAmt, (BigDecimal) nwai4120_16Map.get("FIN_SVC_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.totSvcCostAmt, (BigDecimal) nwai4120_16Map.get("TOT_SVC_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.totSvcCostTrnsfAmt, (BigDecimal) nwai4120_16Map.get("TOT_SVC_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.totSvcRevTrnsfAmt, (BigDecimal) nwai4120_16Map.get("TOT_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.fleetBwReqBaseAmt, (BigDecimal) nwai4120_16Map.get("FLEET_BW_REQ_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.fleetClrReqBaseAmt, (BigDecimal) nwai4120_16Map.get("FLEET_CLR_REQ_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.addlPrmoId, (BigDecimal) nwai4120_16Map.get("ADDL_PRMO_ID"));
            ZYPEZDItemValueSetter.setValue(svcHdr.fleetClrCpcCnt, (BigDecimal) nwai4120_16Map.get("FLEET_CLR_CPC_CNT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.fleetBwCpcCnt, (BigDecimal) nwai4120_16Map.get("FLEET_BW_CPC_CNT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.fleetPlnDescTxt, (String) nwai4120_16Map.get("FLEET_PLAN_DESC_TXT"));  //FLEET_PLN_DESC_TXT
            ZYPEZDItemValueSetter.setValue(svcHdr.fleetBillByTxt, (String) nwai4120_16Map.get("FLEET_BILL_BY_TXT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.fleetBllgCycleTxt, (String) nwai4120_16Map.get("FLEET_BLLG_CYCLE_TXT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.contrTpTxt, (String) nwai4120_16Map.get("CONTR_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.svcCostTrnsfTxt, (String) nwai4120_16Map.get("SVC_COST_TRNSF_TXT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.svcCostApvlTxt, (String) nwai4120_16Map.get("SVC_COST_APVL_TXT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.stplIndSomTxt, (String) nwai4120_16Map.get("STPL_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.ctWavIndSomTxt, (String) nwai4120_16Map.get("CT_WAV_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.contrIndSomTxt, (String) nwai4120_16Map.get("CONTR_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.somContrNum, (String) nwai4120_16Map.get("SOM_CONTR_NUM"));
            ZYPEZDItemValueSetter.setValue(svcHdr.trnsfOvrdIndSomTxt, (String) nwai4120_16Map.get("TRNSF_OVRD_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(svcHdr.somMaintPoDt, (String) nwai4120_16Map.get("SOM_MAINT_PO_DT"));
            //QC#18306
            //ZYPEZDItemValueSetter.setValue(svcHdr.somBllgFromOmTxt, (String) nwai4120_16Map.get("SOM_BLLG_FROM_OM_TXT"));

            List<SOM_SVC_HDR_VIEW_SNAPTMsg> somSvcHdrViewSnapList = new ArrayList<SOM_SVC_HDR_VIEW_SNAPTMsg>();
            somSvcHdrViewSnapList.add(svcHdr);
            int result = EZDFastTBLAccessor.insert(somSvcHdrViewSnapList.toArray(new SOM_SVC_HDR_VIEW_SNAPTMsg[somSvcHdrViewSnapList.size()]));
            if (result != somSvcHdrViewSnapList.size()) {
                //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                S21InfoLogOutput.println("ZZMM0001E", new String[]{"SOM_SVC_HDR_VIEW_SNAP", "SOM_QUOTE_ID", ((BigDecimal) nwai4120_16Map.get("SOM_QUOTE_ID")).toString()});
                return false;
            }
            this.normalAllCount++;
        }
        return true;
    }
    @SuppressWarnings("unchecked")
    private boolean insertSOM_SVC_COPR_VIEW_SNAP(BigDecimal somQuoteId, BigDecimal transactionId, boolean delFlg) {
        if (delFlg) {
            Map<String, Object> pDel = new HashMap<String, Object>();
            pDel.put("glblCmpyCd", this.glblCmpyCd);
            pDel.put("somQuoteId", somQuoteId);
            List<Map<String, Object>> somSvcCoprViewSnapList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSomSvcCoprViewSnapPk", pDel);
            for (Map<String, Object> somSvcCoprViewSnapMap : somSvcCoprViewSnapList) {
                SOM_SVC_COPR_VIEW_SNAPTMsg svcCopr = new SOM_SVC_COPR_VIEW_SNAPTMsg();
                ZYPEZDItemValueSetter.setValue(svcCopr.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(svcCopr.somSvcCoprViewSnapPk, (BigDecimal) somSvcCoprViewSnapMap.get("SOM_SVC_COPR_VIEW_SNAP_PK"));
                svcCopr = (SOM_SVC_COPR_VIEW_SNAPTMsg) S21FastTBLAccessor.findByKey(svcCopr);
                if (svcCopr != null) {
                    List<SOM_SVC_COPR_VIEW_SNAPTMsg> delSomSvcCoprViewSnapList = new ArrayList<SOM_SVC_COPR_VIEW_SNAPTMsg>();
                    delSomSvcCoprViewSnapList.add(svcCopr);
                    S21FastTBLAccessor.removePhysical(delSomSvcCoprViewSnapList.toArray(new SOM_SVC_COPR_VIEW_SNAPTMsg[0]));
                    this.normalAllCount++;
                }
            }
        }
        Map<String, Object> pCrat = new HashMap<String, Object>();
        pCrat.put("somQuoteId", somQuoteId);
        pCrat.put("intfcId", this.intfcId);
        pCrat.put("transactionId", transactionId);
        List<Map<String, Object>> nwai4120_18List = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getNWAI4120_18", pCrat);
        for (Map<String, Object> nwai4120_18Map : nwai4120_18List) {
            SOM_SVC_COPR_VIEW_SNAPTMsg svcCopr = new SOM_SVC_COPR_VIEW_SNAPTMsg();
            ZYPEZDItemValueSetter.setValue(svcCopr.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcCopr.somSvcCoprViewSnapPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SOM_SVC_COPR_VIEW_SNAP_SQ));
            ZYPEZDItemValueSetter.setValue(svcCopr.somSrvId, (BigDecimal) nwai4120_18Map.get("SOM_SRV_ID"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somSrvLineId, (BigDecimal) nwai4120_18Map.get("SOM_SRV_LINE_ID"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somSrvTermId, (BigDecimal) nwai4120_18Map.get("SOM_SRV_TERM_ID"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somSrvUnitId, (BigDecimal) nwai4120_18Map.get("SOM_SRV_UNIT_ID"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somVolMthSrvCnt, (BigDecimal) nwai4120_18Map.get("SOM_VOL_MTH_SRV_CNT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somVolTotSrvCnt, (BigDecimal) nwai4120_18Map.get("SOM_VOL_TOT_SRV_CNT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somQuoteId, (BigDecimal) nwai4120_18Map.get("SOM_QUOTE_ID"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somSrvVolComitCnt, (BigDecimal) nwai4120_18Map.get("SOM_SRV_VOL_COMIT_CNT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.baseClickReqBaseAmt, (BigDecimal) nwai4120_18Map.get("BASE_CLICK_REQ_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.svcPrmoId, (BigDecimal) nwai4120_18Map.get("SVC_PRMO_ID"));
            ZYPEZDItemValueSetter.setValue(svcCopr.bandMaxCnt, (BigDecimal) nwai4120_18Map.get("BAND_MAX_CNT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.termMinCnt, (BigDecimal) nwai4120_18Map.get("TERM_MIN_CNT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.termMaxCnt, (BigDecimal) nwai4120_18Map.get("TERM_MAX_CNT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.reqBaseAmt, (BigDecimal) nwai4120_18Map.get("REQ_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.costTrnsfAmt, (BigDecimal) nwai4120_18Map.get("COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.ovagCostTrnsfAmt, (BigDecimal) nwai4120_18Map.get("OVAG_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.totCostTrnsfAmt, (BigDecimal) nwai4120_18Map.get("TOT_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.baseClickPubBaseAmt, (BigDecimal) nwai4120_18Map.get("BASE_CLICK_PUB_BASE_AMT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.baseClickCostTrnsfAmt, (BigDecimal) nwai4120_18Map.get("BASE_CLICK_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.baseSvcRevTrnsfAmt, (BigDecimal) nwai4120_18Map.get("BASE_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.ovagSvcRevTrnsfAmt, (BigDecimal) nwai4120_18Map.get("OVAG_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.totSvcRevTrnsfAmt, (BigDecimal) nwai4120_18Map.get("TOT_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.origCostTrnsfAmt, (BigDecimal) nwai4120_18Map.get("ORIG_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.origSvcRevTrnsfAmt, (BigDecimal) nwai4120_18Map.get("ORIG_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.reqCpcCnt, (BigDecimal) nwai4120_18Map.get("REQ_CPC_CNT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.svcPubBaseRate, (BigDecimal) nwai4120_18Map.get("SVC_PUB_BASE_AMT"));  //SVC_PUB_BASE_RATE
            ZYPEZDItemValueSetter.setValue(svcCopr.pubCpcCnt, (BigDecimal) nwai4120_18Map.get("PUB_CPC_CNT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somMultCnt, (BigDecimal) nwai4120_18Map.get("SOM_MULT_CNT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.bandMinCnt, (BigDecimal) nwai4120_18Map.get("BAND_MIN_CNT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.contrOptTxt, (String) nwai4120_18Map.get("CONTR_OPT_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.prmoBtIndSomTxt, (String) nwai4120_18Map.get("PRMO_BT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.isClrBtIndSomTxt, (String) nwai4120_18Map.get("IS_CLR_BT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.plnDescTxt, (String) nwai4120_18Map.get("PLAN_DESC_TXT"));  //PLN_DESC_TXT
            ZYPEZDItemValueSetter.setValue(svcCopr.somBllgCycleDescTxt, (String) nwai4120_18Map.get("SOM_BLLG_CYCLE_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.usgCycleDescTxt, (String) nwai4120_18Map.get("USG_CYCLE_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.svcContrIndSomTxt, (String) nwai4120_18Map.get("SVC_CONTR_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somPkgNm, (String) nwai4120_18Map.get("SOM_PKG_NM"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somSrvMtrTpNm, (String) nwai4120_18Map.get("SOM_SRV_MTR_TP_NM"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somSrvCntrNm, (String) nwai4120_18Map.get("SOM_SRV_CNTR_NM"));
            ZYPEZDItemValueSetter.setValue(svcCopr.svcClickTpTxt, (String) nwai4120_18Map.get("SVC_CLICK_TP_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.clickTpDescTxt, (String) nwai4120_18Map.get("CLICK_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.svcPrcBookDescTxt, (String) nwai4120_18Map.get("SVC_PRC_BOOK_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somBandDescTxt, (String) nwai4120_18Map.get("SOM_BAND_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.orclMdlNm, (String) nwai4120_18Map.get("ORCL_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somSvcItemCd, (String) nwai4120_18Map.get("SOM_SVC_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somSrvPrmoCd, (String) nwai4120_18Map.get("SOM_SRV_PRMO_CD"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somSrvPrmoNm, (String) nwai4120_18Map.get("SOM_SRV_PRMO_NM"));
            ZYPEZDItemValueSetter.setValue(svcCopr.svcPrmoTxt, (String) nwai4120_18Map.get("SVC_PRMO_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.dblClickIndSomTxt, (String) nwai4120_18Map.get("DBL_CLICK_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.rateIndSomTxt, (String) nwai4120_18Map.get("RATE_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.svcPrcBookTxt, (String) nwai4120_18Map.get("SVC_PRC_BOOK_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.withRmtIndSomTxt, (String) nwai4120_18Map.get("WITH_RMT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somBandCd, (String) nwai4120_18Map.get("SOM_BAND_CD"));
            ZYPEZDItemValueSetter.setValue(svcCopr.dclnMaintIndSomTxt, (String) nwai4120_18Map.get("DCLN_MAINT_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.mtrReadMethTxt, (String) nwai4120_18Map.get("MTR_READ_METH_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.prmoIndSomTxt, (String) nwai4120_18Map.get("PRMO_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.isPrmoIndSomTxt, (String) nwai4120_18Map.get("IS_PRMO_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.contrTpDescTxt, (String) nwai4120_18Map.get("CONTR_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somMdlId, (String) nwai4120_18Map.get("SOM_MDL_ID"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somMdlGrpId, (String) nwai4120_18Map.get("SOM_MDL_GRP_ID"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somMdlDescTxt, (String) nwai4120_18Map.get("SOM_MDL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.isFleetIndSomTxt, (String) nwai4120_18Map.get("IS_FLEET_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.isBaseClickIndSomTxt, (String) nwai4120_18Map.get("IS_BASE_CLICK_IND_SOM_TXT"));
            ZYPEZDItemValueSetter.setValue(svcCopr.prcCatgCd, (String) nwai4120_18Map.get("PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(svcCopr.somPrcCatgNm, (String) nwai4120_18Map.get("SOM_PRC_CATG_NM"));
            
            BigDecimal positiveExistsCnt = (BigDecimal) nwai4120_18Map.get("POSITIVE_EXISTS_CNT") == null ? BigDecimal.ZERO : (BigDecimal) nwai4120_18Map.get("POSITIVE_EXISTS_CNT");
            BigDecimal srtExistsCnt = (BigDecimal) nwai4120_18Map.get("SRT_EXISTS_CNT") == null ? BigDecimal.ZERO : (BigDecimal) nwai4120_18Map.get("SRT_EXISTS_CNT");
            String trnsfOvrdIndSomTxt = (String) nwai4120_18Map.get("TRNSF_OVRD_IND_SOM_TXT") == null ? "Y" : (String) nwai4120_18Map.get("TRNSF_OVRD_IND_SOM_TXT");
            String totsIntoOmCol = "N";
            if ("N".equals(trnsfOvrdIndSomTxt)) {
                if (positiveExistsCnt.intValue() > 0 && srtExistsCnt.intValue() > 0) {
                    totsIntoOmCol = "Y";
                }
            }
            BigDecimal omTotCostTrnsfAmt = BigDecimal.ZERO;
            BigDecimal omTotSvcRevTrnsfAmt = BigDecimal.ZERO;
            if ("Y".equals(totsIntoOmCol)) {
                //ORIG_COST_TRNSF_AMT
                omTotCostTrnsfAmt = nwai4120_18Map.get("ORIG_COST_TRNSF_AMT") == null ? BigDecimal.ZERO : (BigDecimal) nwai4120_18Map.get("ORIG_COST_TRNSF_AMT");
            } else {
                //TOT_COST_TRNSF_AMT
                omTotCostTrnsfAmt = nwai4120_18Map.get("TOT_COST_TRNSF_AMT") == null ? BigDecimal.ZERO : (BigDecimal) nwai4120_18Map.get("TOT_COST_TRNSF_AMT");
            }
            ZYPEZDItemValueSetter.setValue(svcCopr.omTotCostTrnsfAmt, omTotCostTrnsfAmt);
            if ("Y".equals(totsIntoOmCol)) {
                //ORIG_SVC_REV_TRNSF_AMT
                omTotSvcRevTrnsfAmt = nwai4120_18Map.get("ORIG_SVC_REV_TRNSF_AMT") == null ? BigDecimal.ZERO : (BigDecimal) nwai4120_18Map.get("ORIG_SVC_REV_TRNSF_AMT");
            } else {
                //TOT_SVC_REV_TRNSF_AMT
                omTotSvcRevTrnsfAmt = nwai4120_18Map.get("TOT_SVC_REV_TRNSF_AMT") == null ? BigDecimal.ZERO : (BigDecimal) nwai4120_18Map.get("TOT_SVC_REV_TRNSF_AMT");
            }
            ZYPEZDItemValueSetter.setValue(svcCopr.omTotSvcRevTrnsfAmt, omTotSvcRevTrnsfAmt);
            
            List<SOM_SVC_COPR_VIEW_SNAPTMsg> somSvcCoprViewSnapList = new ArrayList<SOM_SVC_COPR_VIEW_SNAPTMsg>();
            somSvcCoprViewSnapList.add(svcCopr);
            int result = EZDFastTBLAccessor.insert(somSvcCoprViewSnapList.toArray(new SOM_SVC_COPR_VIEW_SNAPTMsg[somSvcCoprViewSnapList.size()]));
            if (result != somSvcCoprViewSnapList.size()) {
                //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                S21InfoLogOutput.println("ZZMM0001E", new String[]{"SOM_SVC_COPR_VIEW_SNAP", "SOM_QUOTE_ID", ((BigDecimal) nwai4120_18Map.get("SOM_QUOTE_ID")).toString()});
                return false;
            }
            this.normalAllCount++;
        }
        return true;
    }
    @SuppressWarnings("unchecked")
    private boolean insertSOM_PRFT_VIEW_SNAP(BigDecimal somQuoteId, BigDecimal transactionId, boolean delFlg) {
        if (delFlg) {
            Map<String, Object> pDel = new HashMap<String, Object>();
            pDel.put("glblCmpyCd", this.glblCmpyCd);
            pDel.put("somQuoteId", somQuoteId);
            List<Map<String, Object>> somPrftViewSnapList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSomPrftViewSnapPk", pDel);
            for (Map<String, Object> somPrftViewSnapMap : somPrftViewSnapList) {
                SOM_PRFT_VIEW_SNAPTMsg prft = new SOM_PRFT_VIEW_SNAPTMsg();
                ZYPEZDItemValueSetter.setValue(prft.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(prft.somPrftViewSnapPk, (BigDecimal) somPrftViewSnapMap.get("SOM_PRFT_VIEW_SNAP_PK"));
                prft = (SOM_PRFT_VIEW_SNAPTMsg) S21FastTBLAccessor.findByKey(prft);
                if (prft != null) {
                    List<SOM_PRFT_VIEW_SNAPTMsg> delSomPrftViewSnapList = new ArrayList<SOM_PRFT_VIEW_SNAPTMsg>();
                    delSomPrftViewSnapList.add(prft);
                    S21FastTBLAccessor.removePhysical(delSomPrftViewSnapList.toArray(new SOM_PRFT_VIEW_SNAPTMsg[0]));
                    this.normalAllCount++;
                }
            }
        }
        
        Map<String, Object> pCrat = new HashMap<String, Object>();
        pCrat.put("somQuoteId", somQuoteId);
        pCrat.put("intfcId", this.intfcId);
        pCrat.put("transactionId", transactionId);
        List<Map<String, Object>> nwai4120_04List = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getNWAI4120_04", pCrat);
        for (Map<String, Object> nwai4120_04Map : nwai4120_04List) {
        	SOM_PRFT_VIEW_SNAPTMsg prft = new SOM_PRFT_VIEW_SNAPTMsg();
            ZYPEZDItemValueSetter.setValue(prft.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prft.somPrftViewSnapPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SOM_PRFT_VIEW_SNAP_SQ));
            ZYPEZDItemValueSetter.setValue(prft.somQuoteId, (BigDecimal) nwai4120_04Map.get("SOM_QUOTE_ID"));  //SOM_QUOTE_ID
            ZYPEZDItemValueSetter.setValue(prft.somCmsnAmt, (BigDecimal) nwai4120_04Map.get("SOM_CMSN_AMT"));  //SOM_CMSN_AMT
            ZYPEZDItemValueSetter.setValue(prft.svcCostAmt, (BigDecimal) nwai4120_04Map.get("SVC_COST_AMT"));  //SVC_COST_AMT
            ZYPEZDItemValueSetter.setValue(prft.svcCostPmtAmt, (BigDecimal) nwai4120_04Map.get("SVC_COST_PMT_AMT"));  //SVC_COST_PMT_AMT
            ZYPEZDItemValueSetter.setValue(prft.svcCostTrnsfAmt, (BigDecimal) nwai4120_04Map.get("SVC_COST_TRNSF_AMT"));  //SVC_COST_TRNSF_AMT
            ZYPEZDItemValueSetter.setValue(prft.somMkupAmt, (BigDecimal) nwai4120_04Map.get("SOM_MKUP_AMT"));  //SOM_MKUP_AMT
            ZYPEZDItemValueSetter.setValue(prft.somPctMkupRate, (BigDecimal) nwai4120_04Map.get("SOM_PCT_MKUP_RATE"));  //SOM_PCT_MKUP_RATE
            ZYPEZDItemValueSetter.setValue(prft.ordTotAmt, (BigDecimal) nwai4120_04Map.get("ORD_TOT_AMT"));  //ORD_TOT_AMT
            ZYPEZDItemValueSetter.setValue(prft.somTotFinAmt, (BigDecimal) nwai4120_04Map.get("SOM_TOT_FIN_AMT"));  //SOM_TOT_FIN_AMT
            ZYPEZDItemValueSetter.setValue(prft.somEquipFinAmt, (BigDecimal) nwai4120_04Map.get("SOM_EQUIP_FIN_AMT"));  //SOM_EQUIP_FIN_AMT
            ZYPEZDItemValueSetter.setValue(prft.somEquipPayAmt, (BigDecimal) nwai4120_04Map.get("SOM_EQUIP_PAY_AMT"));  //SOM_EQUIP_PAY_AMT
            ZYPEZDItemValueSetter.setValue(prft.somSvcFinAmt, (BigDecimal) nwai4120_04Map.get("SOM_SVC_FIN_AMT"));  //SOM_SVC_FIN_AMT
            ZYPEZDItemValueSetter.setValue(prft.somSplyFinAmt, (BigDecimal) nwai4120_04Map.get("SOM_SPLY_FIN_AMT"));  //SOM_SPLY_FIN_AMT
            ZYPEZDItemValueSetter.setValue(prft.somUpgFinAmt, (BigDecimal) nwai4120_04Map.get("SOM_UPG_FIN_AMT"));  //SOM_UPG_FIN_AMT
            ZYPEZDItemValueSetter.setValue(prft.somByotFinAmt, (BigDecimal) nwai4120_04Map.get("SOM_BYOT_FIN_AMT"));  //SOM_BYOT_FIN_AMT
            ZYPEZDItemValueSetter.setValue(prft.miscNonTaxAbleAmt, (BigDecimal) nwai4120_04Map.get("MISC_NON_TAX_ABLE_AMT"));  //MISC_NON_TAX_ABLE_AMT
            ZYPEZDItemValueSetter.setValue(prft.somOthEquipAmt, (BigDecimal) nwai4120_04Map.get("SOM_OTH_EQUIP_AMT"));  //SOM_OTH_EQUIP_AMT
            ZYPEZDItemValueSetter.setValue(prft.msrpListPrcAmt, (BigDecimal) nwai4120_04Map.get("MSRP_LIST_PRC_AMT"));  //MSRP_LIST_PRC_AMT
            ZYPEZDItemValueSetter.setValue(prft.somSoftPctCostRate, (BigDecimal) nwai4120_04Map.get("SOM_SOFT_PCT_COST_RATE"));  //SOM_SOFT_PCT_COST_RATE
            ZYPEZDItemValueSetter.setValue(prft.somFinVsListRate, (BigDecimal) nwai4120_04Map.get("SOM_FIN_VS_LIST_RATE"));  //SOM_FIN_VS_LIST_RATE
            ZYPEZDItemValueSetter.setValue(prft.somTaxAbleAmt, (BigDecimal) nwai4120_04Map.get("SOM_TAX_ABLE_AMT"));  //SOM_TAX_ABLE_AMT
            ZYPEZDItemValueSetter.setValue(prft.nonTaxAbleAmt, (BigDecimal) nwai4120_04Map.get("NON_TAX_ABLE_AMT"));  //NON_TAX_ABLE_AMT
            ZYPEZDItemValueSetter.setValue(prft.somTaxAmt, (BigDecimal) nwai4120_04Map.get("SOM_TAX_AMT"));  //SOM_TAX_AMT
            ZYPEZDItemValueSetter.setValue(prft.somCbsInvAmt, (BigDecimal) nwai4120_04Map.get("SOM_CBS_INV_AMT"));  //SOM_CBS_INV_AMT
            ZYPEZDItemValueSetter.setValue(prft.somFinalFlAmt, (BigDecimal) nwai4120_04Map.get("SOM_FINAL_FL_AMT"));  //SOM_FINAL_FL_AMT
            ZYPEZDItemValueSetter.setValue(prft.gpCostTrnsfAmt, (BigDecimal) nwai4120_04Map.get("GP_COST_TRNSF_AMT"));  //GP_COST_TRNSF_AMT
            ZYPEZDItemValueSetter.setValue(prft.gpCostPctTrnsfRate, (BigDecimal) nwai4120_04Map.get("GP_COST_PCT_TRNSF_RATE"));  //GP_COST_PCT_TRNSF_RATE
            ZYPEZDItemValueSetter.setValue(prft.totRepRevAmt, (BigDecimal) nwai4120_04Map.get("TOT_REP_REV_AMT"));  //TOT_REP_REV_AMT
            ZYPEZDItemValueSetter.setValue(prft.sbscrSvcRepRevAmt, (BigDecimal) nwai4120_04Map.get("SBSCR_SVC_REP_REV_AMT"));  //SBSCR_SVC_REP_REV_AMT
            ZYPEZDItemValueSetter.setValue(prft.repEquipRevAmt, (BigDecimal) nwai4120_04Map.get("REP_EQUIP_REV_AMT"));  //REP_EQUIP_REV_AMT
            ZYPEZDItemValueSetter.setValue(prft.gpEquipAmt, (BigDecimal) nwai4120_04Map.get("GP_EQUIP_AMT"));  //GP_EQUIP_AMT
            ZYPEZDItemValueSetter.setValue(prft.gpPctEquipRate, (BigDecimal) nwai4120_04Map.get("GP_PCT_EQUIP_RATE"));  //GP_PCT_EQUIP_RATE
            ZYPEZDItemValueSetter.setValue(prft.extFlPrcAmt, (BigDecimal) nwai4120_04Map.get("EXT_FL_PRC_AMT"));  //EXT_FL_PRC_AMT
            ZYPEZDItemValueSetter.setValue(prft.somTotCostTrnsfAmt, (BigDecimal) nwai4120_04Map.get("SOM_TOT_COST_TRNSF_AMT"));  //SOM_TOT_COST_TRNSF_AMT
            ZYPEZDItemValueSetter.setValue(prft.somPrmoAmt, (BigDecimal) nwai4120_04Map.get("SOM_PRMO_AMT"));  //SOM_PRMO_AMT
            ZYPEZDItemValueSetter.setValue(prft.somTradeInAmt, (BigDecimal) nwai4120_04Map.get("SOM_TRADE_IN_AMT"));  //SOM_TRADE_IN_AMT
            ZYPEZDItemValueSetter.setValue(prft.sbscrSvcAmt, (BigDecimal) nwai4120_04Map.get("SBSCR_SVC_AMT"));  //SBSCR_SVC_AMT
            ZYPEZDItemValueSetter.setValue(prft.gpEquipRevAmt, (BigDecimal) nwai4120_04Map.get("GP_EQUIP_REV_AMT"));  //GP_EQUIP_REV_AMT
            ZYPEZDItemValueSetter.setValue(prft.gpEquipEarnAmt, (BigDecimal) nwai4120_04Map.get("GP_EQUIP_EARN_AMT"));  //GP_EQUIP_EARN_AMT
            ZYPEZDItemValueSetter.setValue(prft.sbscrSvcRevAmt, (BigDecimal) nwai4120_04Map.get("SBSCR_SVC_REV_AMT"));  //SBSCR_SVC_REV_AMT
            ZYPEZDItemValueSetter.setValue(prft.sbscrSvcEarnAmt, (BigDecimal) nwai4120_04Map.get("SBSCR_SVC_EARN_AMT"));  //SBSCR_SVC_EARN_AMT
            ZYPEZDItemValueSetter.setValue(prft.interRgRevAmt, (BigDecimal) nwai4120_04Map.get("INTER_RG_REV_AMT"));  //INTER_RG_REV_AMT
            ZYPEZDItemValueSetter.setValue(prft.interRgEarnAmt, (BigDecimal) nwai4120_04Map.get("INTER_RG_EARN_AMT"));  //INTER_RG_EARN_AMT
            ZYPEZDItemValueSetter.setValue(prft.interTrtyRevAmt, (BigDecimal) nwai4120_04Map.get("INTER_TRTY_REV_AMT"));  //INTER_TRTY_REV_AMT
            ZYPEZDItemValueSetter.setValue(prft.interTrtyEarnAmt, (BigDecimal) nwai4120_04Map.get("INTER_TRTY_EARN_AMT"));  //INTER_TRTY_EARN_AMT
            ZYPEZDItemValueSetter.setValue(prft.spiffRevAmt, (BigDecimal) nwai4120_04Map.get("SPIFF_REV_AMT"));  //SPIFF_REV_AMT
            ZYPEZDItemValueSetter.setValue(prft.spiffEarnAmt, (BigDecimal) nwai4120_04Map.get("SPIFF_EARN_AMT"));  //SPIFF_EARN_AMT
            ZYPEZDItemValueSetter.setValue(prft.unitSldRevAmt, (BigDecimal) nwai4120_04Map.get("UNIT_SOLD_REV_AMT"));  //UNIT_SOLD_REV_AMT
            ZYPEZDItemValueSetter.setValue(prft.unitSldEarnAmt, (BigDecimal) nwai4120_04Map.get("UNIT_SOLD_EARN_AMT"));  //UNIT_SOLD_EARN_AMT
            ZYPEZDItemValueSetter.setValue(prft.somTotRevAmt, (BigDecimal) nwai4120_04Map.get("SOM_TOT_REV_AMT"));  //SOM_TOT_REV_AMT
            ZYPEZDItemValueSetter.setValue(prft.somTotEarnAmt, (BigDecimal) nwai4120_04Map.get("SOM_TOT_EARN_AMT"));  //SOM_TOT_EARN_AMT
            //ZYPEZDItemValueSetter.setValue(prft.totSlsQuoteCrAmt, (BigDecimal) nwai4120_04Map.get("TOT_SLS_QUOTE_CR_AMT"));  //TOT_SLS_QUOTE_CR_AMT
            //ZYPEZDItemValueSetter.setValue(prft.totRebAmt, (BigDecimal) nwai4120_04Map.get("TOT_REB_AMT"));  //TOT_REB_AMT
            //ZYPEZDItemValueSetter.setValue(prft.prcColRebAmt, (BigDecimal) nwai4120_04Map.get("PRC_COL_REB_AMT"));  //PRC_COL_REB_AMT
            //ZYPEZDItemValueSetter.setValue(prft.leaseMaintSvcTermAot, (BigDecimal) nwai4120_04Map.get("LEASE_MAINT_SVC_TERM_AOT"));  //LEASE_MAINT_SVC_TERM_AOT
            ZYPEZDItemValueSetter.setValue(prft.gpWotCostTrnsfAmt, (BigDecimal) nwai4120_04Map.get("GP_WOT_COST_TRNSF_AMT"));  //GP_WOT_COST_TRNSF_AMT
            ZYPEZDItemValueSetter.setValue(prft.flAdjAmt, (BigDecimal) nwai4120_04Map.get("FL_ADJ_AMT"));  //FL_ADJ_AMT
            ZYPEZDItemValueSetter.setValue(prft.somSvcRevTrnsfAmt, (BigDecimal) nwai4120_04Map.get("SOM_SVC_REV_TRNSF_AMT"));  //SOM_SVC_REV_TRNSF_AMT
            ZYPEZDItemValueSetter.setValue(prft.proSvcAmt, (BigDecimal) nwai4120_04Map.get("PRO_SVC_AMT"));  //PRO_SVC_AMT
            //ZYPEZDItemValueSetter.setValue(prft.leaseCmpyNm, (String) nwai4120_04Map.get("LEASE_CMPY_NM"));  //LEASE_CMPY_NM
            //ZYPEZDItemValueSetter.setValue(prft.leasePmtAmt, (BigDecimal) nwai4120_04Map.get("LEASE_PMT_AMT"));  //LEASE_PMT_AMT
            //ZYPEZDItemValueSetter.setValue(prft.leaseTpTxt, (String) nwai4120_04Map.get("LEASE_TP_TXT"));  //LEASE_TP_TXT
            //ZYPEZDItemValueSetter.setValue(prft.csmpIndSomTxt, (String) nwai4120_04Map.get("CSMP_IND_SOM_TXT"));  //CSMP_IND_SOM_TXT
            //ZYPEZDItemValueSetter.setValue(prft.csmpCrAmt, (BigDecimal) nwai4120_04Map.get("CSMP_CR_AMT"));  //CSMP_CR_AMT
            ZYPEZDItemValueSetter.setValue(prft.gpWotCostTrnsfRate, (BigDecimal) nwai4120_04Map.get("GP_WOT_COST_TRNSF_RATE"));  //GP_WOT_COST_TRNSF_RATE

            List<SOM_PRFT_VIEW_SNAPTMsg> somPrftViewSnapList = new ArrayList<SOM_PRFT_VIEW_SNAPTMsg>();
            somPrftViewSnapList.add(prft);
            int result = EZDFastTBLAccessor.insert(somPrftViewSnapList.toArray(new SOM_PRFT_VIEW_SNAPTMsg[somPrftViewSnapList.size()]));
            if (result != somPrftViewSnapList.size()) {
                //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                S21InfoLogOutput.println("ZZMM0001E", new String[]{"SOM_PRFT_VIEW_SNAP", "SOM_QUOTE_ID", ((BigDecimal) nwai4120_04Map.get("SOM_QUOTE_ID")).toString()});
                return false;
            }
            this.normalAllCount++;
        }
        return true;
    }
}
