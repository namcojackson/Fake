/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB240001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_ORG_RELNTMsg;
import business.db.DS_ORG_RESRC_RELNTMsg;
import business.db.DS_ORG_RESRC_REVTMsg;
import business.db.DS_ORG_UNITTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.ORG_FUNC_ASGTMsg;
import business.db.ORG_FUNC_ROLE_TPTMsg;
import business.db.ORG_STRUTMsg;
import business.db.ORG_TOC_CHNG_RQSTTMsg;
import business.db.S21_ORGTMsg;
import business.db.S21_PSNTMsg;
import business.db.TECH_MSTRTMsg;
import business.db.TOCTMsg;
import business.db.TOC_ORG_MGR_INFOTMsg;
import business.db.TOC_ORG_STRU_RELNTMsg;
import business.db.VAR_CHAR_CONSTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_TIER;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.BIZAPL_TOCCDKEY;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_LEN_TOC_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_NM_COA_BR_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_NM_COA_CC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_NM_COA_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_NM_COA_EXTN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_NM_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_NM_EFF_FROM_DT_ASG;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_NM_EFF_THRU_DT_ASG;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_NM_EFF_THRU_DT_RELN;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_NM_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_NM_ORG_FUNC_ROLE_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_NM_ORG_LAYER_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_NM_ORG_STRU_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_NM_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_NM_PSN_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_NM_PSN_LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_NM_REV_ACCT_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.COLUMN_NM_TOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.MAX_END_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.TBL_NM_DS_ORG_RESRC_REV;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.TBL_NM_ORG_FUNC_ASG;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.TBL_NM_ORG_TOC_CHNG_RQST;
import static com.canon.cusa.s21.batch.NMA.NMAB240001.NMAB240001Constant.TBL_NM_TOC_ORG_STRU_RELN;

/**
 * <pre>
 * NMAB2400 Org Effective Data Control Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/02/09   Fujitsu         C.Yokoi         Update          S21-QC#4176
 * 2016/02/12   Fujitsu         K.Minamide      Update          S21-QC#3792
 * 2016/03/02   Fujitsu         C.Yokoi         Update          CSA-QC#4627
 * 2016/03/25   Fujitsu         C.Yokoi         Update          CSA-QC#5417
 * 2016/03/31   Fujitsu         C.Yokoi         Update          CSA-QC#5945
 * 2016/04/13   Fujitsu         C.Yokoi         Update          CSA-QC#2966
 * 2016/04/15   Fujitsu         C.Yokoi         Update          CSA-QC#6385
 * 2016/06/20   Hitach          A.Kohinata      Update          CSA-QC#10424
 * 2016/07/05   Fujitsu         R.Nakamura      Update          CSA-QC#11081
 * 2016/09/01   Fujitsu         C.Yokoi         Update          CSA-QC#11604
 * 2016/09/15   SRAA            Y.Chen          Update          CSA-QC#13847
 * 2016/10/31   Fujitsu         C.Yokoi         Update          CSA-QC#15096
 * 2017/03/07   Fujitsu         M.Ohno          Update          S21_NA#17760
 * 2017/07/07   Fujitsu         K.Ishizuka      Update          S21_NA#19686
 * 2017/02/26   Hitachi         Y.Takeno        Update          S21_NA#20013
 * 2017/10/17   Fujitsu         H.Sugawara      Update          QC#21753
 * 2017/10/20   Fujitsu         H.Ikeda         Update          QC#21359
 * 2017/12/07   Fujitsu         N.Sugiura       Update          QC#22899
 * 2018/01/10   Fujitsu         M.Ohno          Update          QC#20233
 * 2018/02/05   Fujitsu         N.Sugiura       Update          QC#23938
 * 2019/02/15   Fujitsu         Hd.Sugawara     Update          QC#29668
 * 2020/02/03   Fujitsu         M.Ohno          Update          QC#55306
 */
public class NMAB240001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Mode */
    private String mode = null;

    /** commitUnit */
    private int commitUnit = 0;

    /** process date time */
    private String procDt = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** When any updated record is exists, set Biz Area Org Code. */
    private Map<String, String> bizAreaOrgCdMap = new HashMap<String, String>();

    /** When any updated record is exists, True. */
    private boolean existUpdatedFlag = false;

    @Override
    protected void initRoutine() {
        // blank check(Global Company Code)
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NMAB240001Constant.ZZZM9025E, new String[] {NMAB240001Constant.NAME_FOR_MESSAGE_GLOBAL_COMPANY_CODE });
        }

        // value check(Global Company Code)
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg == null) {
            throw new S21AbendException(NMAB240001Constant.ZZZM9026E, new String[] {NMAB240001Constant.NAME_FOR_MESSAGE_GLOBAL_COMPANY_CODE });
        }

        // Mode (0:Day, 1:Night)
        this.mode = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(this.mode)) {
            throw new S21AbendException(NMAB240001Constant.ZZZM9025E, new String[] {NMAB240001Constant.NAME_FOR_MESSAGE_MODE });
        }

        // 2016/03/25 CSA-QC#5417 Mod Start
        this.commitUnit = ZYPCodeDataUtil.getNumConstValue(NMAB240001Constant.NMAB2400_COMMIT_COUNT, this.glblCmpyCd).intValue();
        // 2016/03/25 CSA-QC#5417 Mod End

        if (this.commitUnit <= 0 || NMAB240001Constant.DEFAULT_COMMIT_UNIT < this.commitUnit) {
            this.commitUnit = NMAB240001Constant.DEFAULT_COMMIT_UNIT;
        }

        this.procDt = ZYPDateUtil.getBatProcDate();

        // 2016/02/12 S21-QC#3792 Mod Start
        if (NMAB240001Constant.MODE_NIGHT.equals(this.mode)) {
            this.procDt = ZYPDateUtil.addDays(this.procDt, 1);
        }
        // 2016/02/12 S21-QC#3792 Mod End

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        // Add Start 2019/02/15 QC#29668
        this.createChangeRequestForResourceRevenueData();
        // Add End 2019/02/15 QC#29668

        this.activateTerritory();
        this.inactivateTerritory();
        // 2016/03/31 CSA-QC#5945 Add Start
        this.terminateTerritory();
        // 2016/03/31 CSA-QC#5945 Add End

        this.activateOrganization();
        this.inactivateOrganization();
        // 2016/03/31 CSA-QC#5945 Add Start
        this.terminateOrganization();
        // 2016/03/31 CSA-QC#5945 Add End

        // 2016/09/01 CSA-QC#11604 Add Start
        this.inactivatePerson();
        // 2016/09/01 CSA-QC#11604 Add End

        this.activateToc();
        this.inactivateToc();
        // 2016/03/31 CSA-QC#5945 Add Start
        this.terminateToc();
        // 2016/03/31 CSA-QC#5945 Add End

        if (NMAB240001Constant.MODE_DAILY.equals(this.mode)) {
            // 2016/03/02 CSA-QC4627 Add Start
            if (this.existUpdatedFlag) {
                this.updateOrgStruS21Org();
                // 2016/03/02 CSA-QC4627 Add End

                if (this.isProcessTocOrgMgrInfo()) {
                    this.deleteTocOrgMgrInfo();
                    this.createTocOrgMgrInfo();
                }
            }
        } else if (NMAB240001Constant.MODE_NIGHT.equals(this.mode)) {
            if (this.existUpdatedFlag) {
                this.updateOrgStruS21Org();

                this.deleteTocOrgMgrInfo();
                this.createTocOrgMgrInfo();
            } else if (this.isFirstBizDay()) {
                this.deleteTocOrgMgrInfo();
                this.createTocOrgMgrInfo();
            }
        }
    }

    /**
     * Activate Territory Related Table Change Generation Type Code
     * from Future to Current.
     */
    private void activateTerritory() {
        int territoryActivateCount = 0;

        Map<String, String> territoryActivateMap = new HashMap<String, String>();
        territoryActivateMap.put("glblCmpyCd", this.glblCmpyCd);
        territoryActivateMap.put("procDt", this.procDt);
        territoryActivateMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        territoryActivateMap.put("gnrnTpCd_3", GNRN_TP.FUTURE);
        territoryActivateMap.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);

        List<Map<String, Object>> territoryActivateList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTerritoryActivateList", territoryActivateMap);

        for (Map<String, Object> territoryActivateListMap : territoryActivateList) {
            this.totalCount++;
            String orgCd = (String) territoryActivateListMap.get(NMAB240001Constant.COLUMN_NM_ORG_CD);

            this.updateDsOrgUnit(orgCd, GNRN_TP.CURRENT, ORG_STRU_TP.TERRITORY_STRUCTURE);

            territoryActivateCount++;
            if (territoryActivateCount == this.commitUnit) {
                super.commit();
                territoryActivateCount = 0;
            }
            this.totalNmlCount++;
        }

        // 2016/04/13 CSA-QC#2966 Del Start
        // Map<String, String> territoryRelationSearchMap = new HashMap<String, String>();
        // territoryRelationSearchMap.put("glblCmpyCd", this.glblCmpyCd);
        // territoryRelationSearchMap.put("procDt", this.procDt);
        // territoryRelationSearchMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        // territoryRelationSearchMap.put("gnrnTpCd_3", GNRN_TP.FUTURE);
        // territoryRelationSearchMap.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
        // 2016/04/13 CSA-QC#2966 Del End

        List<Map<String, Object>> territoryRelationList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTerritoryRelationActivateList", territoryActivateMap);

        Map<String, String> parentMap = new HashMap<String, String>();
        parentMap.put("glblCmpyCd", this.glblCmpyCd);
        parentMap.put("procDt", this.procDt);
        parentMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        parentMap.put("gnrnTpCd_2", GNRN_TP.CURRENT);
        parentMap.put("gnrnTpCd_3", GNRN_TP.FUTURE);
        parentMap.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);

        for (Map<String, Object> territoryRelationMap : territoryRelationList) {
            this.totalCount++;
            String prntOrgCd = (String) territoryRelationMap.get(NMAB240001Constant.COLUMN_NM_PRNT_ORG_CD);
            String orgCd = (String) territoryRelationMap.get(NMAB240001Constant.COLUMN_NM_ORG_CD);
            parentMap.put("orgCd", prntOrgCd);

            Integer parentTerritoryCount = (Integer) this.ssmBatchClient.queryObject("getParentTerritoryActivateCount", parentMap);

            if (NMAB240001Constant.ZERO.compareTo(parentTerritoryCount) < 0) {
                String effFromDt = (String) territoryRelationMap.get(NMAB240001Constant.COLUMN_NM_EFF_FROM_DT);
                BigDecimal dsOrgRelnPk = (BigDecimal) territoryRelationMap.get(NMAB240001Constant.COLUMN_NM_DS_ORG_RELN_PK); // 2017/03/07 S21_NA#17760 Add

                this.updateDsOrgReln(orgCd, prntOrgCd, ORG_STRU_TP.TERRITORY_STRUCTURE, effFromDt, GNRN_TP.CURRENT, dsOrgRelnPk);

                territoryActivateCount++;
                if (territoryActivateCount == this.commitUnit) {
                    super.commit();
                    territoryActivateCount = 0;
                }
                this.totalNmlCount++;
            }
        }

        // 2016/04/13 CSA-QC#2966 Del Start
        // Map<String, String> territoryResourceRelationSearchMap = new HashMap<String, String>();
        // territoryResourceRelationSearchMap.put("glblCmpyCd", this.glblCmpyCd);
        // territoryResourceRelationSearchMap.put("procDt", this.procDt);
        // territoryResourceRelationSearchMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        // territoryResourceRelationSearchMap.put("gnrnTpCd_3", GNRN_TP.FUTURE);
        // territoryResourceRelationSearchMap.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
        // 2016/04/13 CSA-QC#2966 Del End

        List<Map<String, Object>> territoryResourceRelationList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTerritoryResourceRelationActivateList", territoryActivateMap);

        for (Map<String, Object> territoryResourceRelationMap : territoryResourceRelationList) {
            this.totalCount++;
            String orgCd = (String) territoryResourceRelationMap.get(NMAB240001Constant.COLUMN_NM_ORG_CD);
            String psnCd = (String) territoryResourceRelationMap.get(NMAB240001Constant.COLUMN_NM_PSN_CD);
            String orgFuncRoleTpCd = (String) territoryResourceRelationMap.get(NMAB240001Constant.COLUMN_NM_ORG_FUNC_ROLE_TP_CD);
            String effFromDt = (String) territoryResourceRelationMap.get(NMAB240001Constant.COLUMN_NM_EFF_FROM_DT);

            this.updateDsOrgResrcReln(orgCd, ORG_STRU_TP.TERRITORY_STRUCTURE, psnCd, orgFuncRoleTpCd, effFromDt, GNRN_TP.CURRENT);

            territoryActivateCount++;
            if (territoryActivateCount == this.commitUnit) {
                super.commit();
                territoryActivateCount = 0;
            }
            this.totalNmlCount++;
        }

        // 2016/04/13 CSA-QC#2966 Add Start
        if (territoryActivateCount > 0) {
            super.commit();
        }
        // 2016/04/13 CSA-QC#2966 Add Start
    }

    /**
     * Inactivate Territory Related Table Change Generation Type Code
     * from Current to Past.
     */
    private void inactivateTerritory() {
        int inactiveTerminateCount = 0;

        Map<String, String> territoryInactiveMap = new HashMap<String, String>();
        territoryInactiveMap.put("glblCmpyCd", this.glblCmpyCd);
        territoryInactiveMap.put("procDt", this.procDt);
        territoryInactiveMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        territoryInactiveMap.put("gnrnTpCd_2", GNRN_TP.CURRENT);
        // Add Start 2017/10/17 QC#21753
        territoryInactiveMap.put("gnrnTpCd_3", GNRN_TP.FUTURE);
        // Add End 2017/10/17 QC#21753
        territoryInactiveMap.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);

        List<Map<String, Object>> territoryInactiveList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTerritoryInactivateList", territoryInactiveMap);

        for (Map<String, Object> territoryInactiveListMap : territoryInactiveList) {
            this.totalCount++;
            String orgCd = (String) territoryInactiveListMap.get(NMAB240001Constant.COLUMN_NM_ORG_CD);

            this.updateDsOrgUnit(orgCd, GNRN_TP.PAST, ORG_STRU_TP.TERRITORY_STRUCTURE);

            inactiveTerminateCount++;
            if (inactiveTerminateCount == this.commitUnit) {
                super.commit();
                inactiveTerminateCount = 0;
            }
            this.totalNmlCount++;
        }

        // 2016/04/13 CSA-QC#2966 Del Start
        // Map<String, String> territoryRelationSearchMap = new HashMap<String, String>();
        // territoryRelationSearchMap.put("glblCmpyCd", this.glblCmpyCd);
        // territoryRelationSearchMap.put("procDt", this.procDt);
        // territoryRelationSearchMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        // territoryRelationSearchMap.put("gnrnTpCd_2", GNRN_TP.CURRENT);
        // territoryRelationSearchMap.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
        // 2016/04/13 CSA-QC#2966 Del End

        List<Map<String, Object>> territoryRelationList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTerritoryRelationInactiveList", territoryInactiveMap);

        Map<String, String> childMap = new HashMap<String, String>();
        childMap.put("glblCmpyCd", this.glblCmpyCd);
        childMap.put("procDt", this.procDt);
        childMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        childMap.put("gnrnTpCd_2", GNRN_TP.CURRENT);
        childMap.put("gnrnTpCd_3", GNRN_TP.FUTURE);
        childMap.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);

        for (Map<String, Object> territoryRelationMap : territoryRelationList) {
            this.totalCount++;
            String orgCd = (String) territoryRelationMap.get(NMAB240001Constant.COLUMN_NM_ORG_CD);
            childMap.put("prntOrgCd", orgCd);

            Integer childTerritoryCount = (Integer) this.ssmBatchClient.queryObject("getChildTerritoryInactiveCount", childMap);

            if (NMAB240001Constant.ZERO.compareTo(childTerritoryCount) == 0) {
                String prntOrgCd = (String) territoryRelationMap.get(NMAB240001Constant.COLUMN_NM_PRNT_ORG_CD);
                String effFromDt = (String) territoryRelationMap.get(NMAB240001Constant.COLUMN_NM_EFF_FROM_DT);
                BigDecimal dsOrgRelnPk = (BigDecimal) territoryRelationMap.get(NMAB240001Constant.COLUMN_NM_DS_ORG_RELN_PK); // 2017/03/07 S21_NA#17760 Add

                this.updateDsOrgReln(orgCd, prntOrgCd, ORG_STRU_TP.TERRITORY_STRUCTURE, effFromDt, GNRN_TP.PAST, dsOrgRelnPk);

                inactiveTerminateCount++;
                if (inactiveTerminateCount == this.commitUnit) {
                    super.commit();
                    inactiveTerminateCount = 0;
                }
                this.totalNmlCount++;
            }
        }

        // 2016/04/13 CSA-QC#2966 Del Start
        // Map<String, String> territoryResourceRelationSearchMap = new HashMap<String, String>();
        // territoryResourceRelationSearchMap.put("glblCmpyCd", this.glblCmpyCd);
        // territoryResourceRelationSearchMap.put("procDt", this.procDt);
        // territoryResourceRelationSearchMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        // territoryResourceRelationSearchMap.put("gnrnTpCd_2", GNRN_TP.CURRENT);
        // territoryResourceRelationSearchMap.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
        // 2016/04/13 CSA-QC#2966 Del End

        List<Map<String, Object>> territoryResourceRelationList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTerritoryResourceRelationInactiveList", territoryInactiveMap);

        for (Map<String, Object> territoryResourceRelationMap : territoryResourceRelationList) {
            this.totalCount++;
            String orgCd = (String) territoryResourceRelationMap.get(NMAB240001Constant.COLUMN_NM_ORG_CD);
            String psnCd = (String) territoryResourceRelationMap.get(NMAB240001Constant.COLUMN_NM_PSN_CD);
            String orgFuncRoleTpCd = (String) territoryResourceRelationMap.get(NMAB240001Constant.COLUMN_NM_ORG_FUNC_ROLE_TP_CD);
            String effFromDt = (String) territoryResourceRelationMap.get(NMAB240001Constant.COLUMN_NM_EFF_FROM_DT);

            this.updateDsOrgResrcReln(orgCd, ORG_STRU_TP.TERRITORY_STRUCTURE, psnCd, orgFuncRoleTpCd, effFromDt, GNRN_TP.PAST);

            inactiveTerminateCount++;
            if (inactiveTerminateCount == this.commitUnit) {
                super.commit();
                inactiveTerminateCount = 0;
            }
            this.totalNmlCount++;
        }

        // 2016/04/13 CSA-QC#2966 Add Start
        if (inactiveTerminateCount > 0) {
            super.commit();
        }
        // 2016/04/13 CSA-QC#2966 Add Start
    }

    /**
     * Terminate Territory Related Table Change Generation Type Code
     * from Delete to Terminated.
     */
    private void terminateTerritory() {
        // 2016/03/31 CSA-QC# Add Start
        int territoryTerminateCount = 0;

        Map<String, String> territoryRelationSearchMap = new HashMap<String, String>();
        territoryRelationSearchMap.put("glblCmpyCd", this.glblCmpyCd);
        territoryRelationSearchMap.put("procDt", this.procDt);
        territoryRelationSearchMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        territoryRelationSearchMap.put("gnrnTpCd_9", GNRN_TP.DELETE);
        territoryRelationSearchMap.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);

        List<Map<String, Object>> territoryRelationList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTerritoryRelationTerminateList", territoryRelationSearchMap);

        Map<String, String> childMap = new HashMap<String, String>();
        childMap.put("glblCmpyCd", this.glblCmpyCd);
        childMap.put("procDt", this.procDt);
        childMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        childMap.put("gnrnTpCd_2", GNRN_TP.CURRENT);
        childMap.put("gnrnTpCd_3", GNRN_TP.FUTURE);
        childMap.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);

        for (Map<String, Object> territoryRelationMap : territoryRelationList) {
            this.totalCount++;

            String orgCd = (String) territoryRelationMap.get(NMAB240001Constant.COLUMN_NM_ORG_CD);
            childMap.put("prntOrgCd", orgCd);

            Integer childTerritoryCount = (Integer) this.ssmBatchClient.queryObject("getChildTerritoryInactiveCount", childMap);

            if (NMAB240001Constant.ZERO.compareTo(childTerritoryCount) == 0) {
                String prntOrgCd = (String) territoryRelationMap.get(NMAB240001Constant.COLUMN_NM_PRNT_ORG_CD);
                String effFromDt = (String) territoryRelationMap.get(NMAB240001Constant.COLUMN_NM_EFF_FROM_DT);
                BigDecimal dsOrgRelnPk = (BigDecimal) territoryRelationMap.get(NMAB240001Constant.COLUMN_NM_DS_ORG_RELN_PK); // 2017/03/07 S21_NA#17760 Add

                this.updateDsOrgReln(orgCd, prntOrgCd, ORG_STRU_TP.TERRITORY_STRUCTURE, effFromDt, GNRN_TP.TERMINATED, dsOrgRelnPk);

                territoryTerminateCount++;
                if (territoryTerminateCount == this.commitUnit) {
                    super.commit();
                    territoryTerminateCount = 0;
                }
                this.totalNmlCount++;
            }
        }

        // 2016/04/13 CSA-QC#2966 Del Start
        // Map<String, String> territoryResourceRelationSearchMap = new HashMap<String, String>();
        // territoryResourceRelationSearchMap.put("glblCmpyCd", this.glblCmpyCd);
        // territoryResourceRelationSearchMap.put("procDt", this.procDt);
        // territoryResourceRelationSearchMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        // territoryResourceRelationSearchMap.put("gnrnTpCd_9", GNRN_TP.DELETE);
        // territoryResourceRelationSearchMap.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
        // 2016/04/13 CSA=QC#2966 Del End

        List<Map<String, Object>> territoryResourceRelationList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTerritoryResourceRelationTerminateList", territoryRelationSearchMap);

        for (Map<String, Object> territoryResourceRelationMap : territoryResourceRelationList) {
            this.totalCount++;
            String orgCd = (String) territoryResourceRelationMap.get(NMAB240001Constant.COLUMN_NM_ORG_CD);
            String psnCd = (String) territoryResourceRelationMap.get(NMAB240001Constant.COLUMN_NM_PSN_CD);
            String orgFuncRoleTpCd = (String) territoryResourceRelationMap.get(NMAB240001Constant.COLUMN_NM_ORG_FUNC_ROLE_TP_CD);
            String effFromDt = (String) territoryResourceRelationMap.get(NMAB240001Constant.COLUMN_NM_EFF_FROM_DT);

            this.updateDsOrgResrcReln(orgCd, ORG_STRU_TP.TERRITORY_STRUCTURE, psnCd, orgFuncRoleTpCd, effFromDt, GNRN_TP.TERMINATED);

            territoryTerminateCount++;
            if (territoryTerminateCount == this.commitUnit) {
                super.commit();
                territoryTerminateCount = 0;
            }
            this.totalNmlCount++;
        }
        // 2016/03/31 CSA-QC# Add End

        // 2016/04/13 CSA-QC#2966 Add Start
        if (territoryTerminateCount > 0) {
            super.commit();
        }
        // 2016/04/13 CSA-QC#2966 Add Start
    }

    /**
     * Activate Organization Related Table Change Generation Type Code
     * from Future to Current. Update ORG_STRUgetInsertOrgStruList
     */
    private void activateOrganization() {
        int organizationActivateCount = 0;

        Map<String, String> organizationActivateMap = new HashMap<String, String>();
        organizationActivateMap.put("glblCmpyCd", this.glblCmpyCd);
        organizationActivateMap.put("procDt", this.procDt);
        organizationActivateMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        organizationActivateMap.put("gnrnTpCd_3", GNRN_TP.FUTURE);
        organizationActivateMap.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);

        List<Map<String, Object>> organizationActivateList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getOrganizationActivateList", organizationActivateMap);

        for (Map<String, Object> organizationActivateListMap : organizationActivateList) {
            this.totalCount++;
            String orgCd = (String) organizationActivateListMap.get(NMAB240001Constant.COLUMN_NM_ORG_CD);

            this.updateDsOrgUnit(orgCd, GNRN_TP.CURRENT, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);

            organizationActivateCount++;
            if (organizationActivateCount == this.commitUnit) {
                super.commit();
                organizationActivateCount = 0;
            }
            this.totalNmlCount++;

            // 2016/02/09 S21-QC#4176 Mod Start
            if (ORG_TIER._1.equals((String) organizationActivateListMap.get(NMAB240001Constant.COLUMN_NM_ORG_TIER_CD))) {
                DS_ORG_UNITTMsg dsOrgUnit = new DS_ORG_UNITTMsg();
                ZYPEZDItemValueSetter.setValue(dsOrgUnit.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsOrgUnit.orgCd, orgCd);

                dsOrgUnit = (DS_ORG_UNITTMsg) EZDTBLAccessor.findByKey(dsOrgUnit);
                if (dsOrgUnit != null) {
                    // 2016/03/02 CSA-QC#4627 Delete Start
                    // if (NMAB240001Constant.MODE_DAILY.equals(this.mode)) {
                    // this.updateOrgStru(dsOrgUnit);
                    // this.existUpdatedFlag = true;
                    //
                    // } else if (NMAB240001Constant.MODE_NIGHT.equals(this.mode)) {
                    if (!this.bizAreaOrgCdMap.containsKey(dsOrgUnit.firstOrgCd.getValue())) {
                        this.bizAreaOrgCdMap.put(dsOrgUnit.firstOrgCd.getValue(), dsOrgUnit.firstOrgCd.getValue());
                        this.existUpdatedFlag = true;
                    }
                    // }
                    // 2016/03/02 CSA-QC#4627 Delete End
                }
            }
            // 2016/02/09 S21-QC#4176 Mod End
        }

        // 2016/04/13 CSA-QC#2966 Del Start
        // Map<String, String> organizationRelationSearchMap = new HashMap<String, String>();
        // organizationRelationSearchMap.put("glblCmpyCd", this.glblCmpyCd);
        // organizationRelationSearchMap.put("procDt", this.procDt);
        // organizationRelationSearchMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        // organizationRelationSearchMap.put("gnrnTpCd_3", GNRN_TP.FUTURE);
        // organizationRelationSearchMap.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        // 2016/04/13 CSA-QC#2966 Del End

        List<Map<String, Object>> organizationRelationList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getOrganizationRelationActivateList", organizationActivateMap);

        Map<String, String> parentMap = new HashMap<String, String>();
        parentMap.put("glblCmpyCd", this.glblCmpyCd);
        parentMap.put("procDt", this.procDt);
        parentMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        parentMap.put("gnrnTpCd_2", GNRN_TP.CURRENT);
        parentMap.put("gnrnTpCd_3", GNRN_TP.FUTURE);
        parentMap.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);

        for (Map<String, Object> organizationRelationMap : organizationRelationList) {
            this.totalCount++;
            String prntOrgCd = (String) organizationRelationMap.get(NMAB240001Constant.COLUMN_NM_PRNT_ORG_CD);
            parentMap.put("orgCd", prntOrgCd);

            Integer parentOrganizationCount = (Integer) this.ssmBatchClient.queryObject("getParentOrganizationActivateCount", parentMap);

            if (NMAB240001Constant.ZERO.compareTo(parentOrganizationCount) < 0) {
                String orgCd = (String) organizationRelationMap.get(NMAB240001Constant.COLUMN_NM_ORG_CD);
                String effFromDt = (String) organizationRelationMap.get(NMAB240001Constant.COLUMN_NM_EFF_FROM_DT);
                BigDecimal dsOrgRelnPk = (BigDecimal) organizationRelationMap.get(NMAB240001Constant.COLUMN_NM_DS_ORG_RELN_PK); // 2017/03/07 S21_NA#17760 Add

                this.updateDsOrgReln(orgCd, prntOrgCd, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY, effFromDt, GNRN_TP.CURRENT, dsOrgRelnPk);

                organizationActivateCount++;
                if (organizationActivateCount == this.commitUnit) {
                    super.commit();
                    organizationActivateCount = 0;
                }
                this.totalNmlCount++;

                DS_ORG_UNITTMsg dsOrgUnit = new DS_ORG_UNITTMsg();
                ZYPEZDItemValueSetter.setValue(dsOrgUnit.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsOrgUnit.orgCd, orgCd);

                dsOrgUnit = (DS_ORG_UNITTMsg) EZDTBLAccessor.findByKey(dsOrgUnit);
                if (dsOrgUnit != null) {
                    // 2016/03/02 CSA-QC#4627 Delete Start
                    // if
                    // (NMAB240001Constant.MODE_DAILY.equals(this.mode))
                    // {
                    // this.updateOrgStru(dsOrgUnit);
                    // this.existUpdatedFlag = true;
                    //
                    // } else if
                    // (NMAB240001Constant.MODE_NIGHT.equals(this.mode))
                    // {
                    // 2016/03/02 CSA-QC#4627 Delete Start
                    if (!this.bizAreaOrgCdMap.containsKey(dsOrgUnit.firstOrgCd.getValue())) {
                        this.bizAreaOrgCdMap.put(dsOrgUnit.firstOrgCd.getValue(), dsOrgUnit.firstOrgCd.getValue());
                        this.existUpdatedFlag = true;
                    }
                    // }
                }
            }
        }

        // 2016/04/13 CSA-QC#2966 Add Start
        if (organizationActivateCount > 0) {
            super.commit();
        }
        // 2016/04/13 CSA-QC#2966 Add Start
    }

    /**
     * Inactivate Organization Related Table Change Generation Type
     * Code from Current to Past. Update ORG_STRU
     */
    private void inactivateOrganization() {
        int organizationInactivateCount = 0;

        Map<String, String> organizationInactiveMap = new HashMap<String, String>();
        organizationInactiveMap.put("glblCmpyCd", this.glblCmpyCd);
        organizationInactiveMap.put("procDt", this.procDt);
        organizationInactiveMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        organizationInactiveMap.put("gnrnTpCd_2", GNRN_TP.CURRENT);
        // Add Start 2017/10/17 QC#21753
        organizationInactiveMap.put("gnrnTpCd_3", GNRN_TP.FUTURE);
        // Add End 2017/10/17 QC#21753
        organizationInactiveMap.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);

        List<Map<String, Object>> organizationInactiveList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getOrganizationTerminateList", organizationInactiveMap);

        for (Map<String, Object> organizationInactiveListMap : organizationInactiveList) {
            this.totalCount++;
            String orgCd = (String) organizationInactiveListMap.get(NMAB240001Constant.COLUMN_NM_ORG_CD);

            this.updateDsOrgUnit(orgCd, GNRN_TP.PAST, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);

            organizationInactivateCount++;
            if (organizationInactivateCount == this.commitUnit) {
                super.commit();
                organizationInactivateCount = 0;
            }
            this.totalNmlCount++;
        }

        Map<String, String> organizationRelationSearchMap = new HashMap<String, String>();
        organizationRelationSearchMap.put("glblCmpyCd", this.glblCmpyCd);
        organizationRelationSearchMap.put("procDt", this.procDt);
        organizationRelationSearchMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        organizationRelationSearchMap.put("gnrnTpCd_2", GNRN_TP.CURRENT);
        // Add Start 2017/10/17 QC#21753
        organizationRelationSearchMap.put("gnrnTpCdFtr", GNRN_TP.FUTURE);
        // Add End 2017/10/17 QC#21753
        organizationRelationSearchMap.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);

        List<Map<String, Object>> organizationRelationList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getOrganizationRelationTerminateList", organizationRelationSearchMap);

        // 2018/01/10 QC#20233 del start
//        Map<String, String> childMap = new HashMap<String, String>();
//        childMap.put("glblCmpyCd", this.glblCmpyCd);
//        childMap.put("procDt", this.procDt);
//        childMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
//        childMap.put("gnrnTpCd_2", GNRN_TP.CURRENT);
//        childMap.put("gnrnTpCd_3", GNRN_TP.FUTURE);
//        childMap.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        // 2018/01/10 QC#20233 del end

        for (Map<String, Object> organizationRelationMap : organizationRelationList) {
            this.totalCount++;
            String orgCd = (String) organizationRelationMap.get(NMAB240001Constant.COLUMN_NM_ORG_CD);
         // 2018/01/10 QC#20233 del start
//            childMap.put("prntOrgCd", orgCd);
//
//            Integer childOrganizationCount = (Integer) this.ssmBatchClient.queryObject("getChildOrganizationTerminateCount", childMap);
//
//            if (NMAB240001Constant.ZERO.compareTo(childOrganizationCount) == 0) {
         // 2018/01/10 QC#20233 del end

            String prntOrgCd = (String) organizationRelationMap.get(NMAB240001Constant.COLUMN_NM_PRNT_ORG_CD);
            String effFromDt = (String) organizationRelationMap.get(NMAB240001Constant.COLUMN_NM_EFF_FROM_DT);
            BigDecimal dsOrgRelnPk = (BigDecimal) organizationRelationMap.get(NMAB240001Constant.COLUMN_NM_DS_ORG_RELN_PK); // 2017/03/07 S21_NA#17760 Add

            this.updateDsOrgReln(orgCd, prntOrgCd, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY, effFromDt, GNRN_TP.PAST, dsOrgRelnPk);

            organizationInactivateCount++;
            if (organizationInactivateCount == this.commitUnit) {
                super.commit();
                organizationInactivateCount = 0;
            }
            this.totalNmlCount++;

            DS_ORG_UNITTMsg dsOrgUnit = new DS_ORG_UNITTMsg();
            ZYPEZDItemValueSetter.setValue(dsOrgUnit.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsOrgUnit.orgCd, orgCd);

            dsOrgUnit = (DS_ORG_UNITTMsg) EZDTBLAccessor.findByKey(dsOrgUnit);
            // 2016/03/02 CSA-QC#4627 Delete Start
            // if (dsOrgUnit != null) {
            // if (NMAB240001Constant.MODE_DAILY.equals(this.mode)) {
            // this.deleteOrgStru(dsOrgUnit.orgCd.getValue(), dsOrgUnit.orgTierCd.getValue(), null);
            // this.existUpdatedFlag = true;
            //
            // } else if
            // (NMAB240001Constant.MODE_NIGHT.equals(this.mode)) {
            // 2016/03/02 CSA-QC#4627 Delete End
            if (!this.bizAreaOrgCdMap.containsKey(dsOrgUnit.firstOrgCd.getValue())) {
                this.bizAreaOrgCdMap.put(dsOrgUnit.firstOrgCd.getValue(), dsOrgUnit.firstOrgCd.getValue());
                this.existUpdatedFlag = true;
            }
            // }
            // }
//          }
        }

        // 2016/04/13 CSA-QC#2966 Add Start
        if (organizationInactivateCount > 0) {
            super.commit();
        }
        // 2016/04/13 CSA-QC#2966 Add Start
    }

    /**
     * Terminate Organization Related Table Change Generation Type
     * Code from Delete to Terminate.
     */
    private void terminateOrganization() {
        int organizationTerminateCount = 0;

        Map<String, String> organizationRelationSearchMap = new HashMap<String, String>();
        organizationRelationSearchMap.put("glblCmpyCd", this.glblCmpyCd);
        organizationRelationSearchMap.put("procDt", this.procDt);
        organizationRelationSearchMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        organizationRelationSearchMap.put("gnrnTpCd_2", GNRN_TP.DELETE);
        organizationRelationSearchMap.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);

        List<Map<String, Object>> organizationRelationList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getOrganizationRelationTerminateList", organizationRelationSearchMap);

        // 2018/01/10 QC#22737 del start
//        Map<String, String> childMap = new HashMap<String, String>();
//        childMap.put("glblCmpyCd", this.glblCmpyCd);
//        childMap.put("procDt", this.procDt);
//        childMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
//        childMap.put("gnrnTpCd_2", GNRN_TP.CURRENT);
//        childMap.put("gnrnTpCd_3", GNRN_TP.FUTURE);
//        childMap.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
       // 2018/01/10 QC#22737 del end

        for (Map<String, Object> organizationRelationMap : organizationRelationList) {
            this.totalCount++;
            String orgCd = (String) organizationRelationMap.get(NMAB240001Constant.COLUMN_NM_ORG_CD);
            // 2018/01/10 QC#22737 del start
//            childMap.put("prntOrgCd", orgCd);
//
//            Integer childOrganizationCount = (Integer) this.ssmBatchClient.queryObject("getChildOrganizationTerminateCount", childMap);
//
//            if (NMAB240001Constant.ZERO.compareTo(childOrganizationCount) == 0) {
            // 2018/01/10 QC#22737 del end

                String prntOrgCd = (String) organizationRelationMap.get(NMAB240001Constant.COLUMN_NM_PRNT_ORG_CD);
                String effFromDt = (String) organizationRelationMap.get(NMAB240001Constant.COLUMN_NM_EFF_FROM_DT);
                BigDecimal dsOrgRelnPk = (BigDecimal) organizationRelationMap.get(NMAB240001Constant.COLUMN_NM_DS_ORG_RELN_PK); // 2017/03/07 S21_NA#17760 Add

                this.updateDsOrgReln(orgCd, prntOrgCd, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY, effFromDt, GNRN_TP.TERMINATED, dsOrgRelnPk);

                organizationTerminateCount++;
                if (organizationTerminateCount == this.commitUnit) {
                    super.commit();
                    organizationTerminateCount = 0;
                }
                this.totalNmlCount++;

                DS_ORG_UNITTMsg dsOrgUnit = new DS_ORG_UNITTMsg();
                ZYPEZDItemValueSetter.setValue(dsOrgUnit.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsOrgUnit.orgCd, orgCd);

                if (!this.bizAreaOrgCdMap.containsKey(dsOrgUnit.firstOrgCd.getValue())) {
                    this.bizAreaOrgCdMap.put(dsOrgUnit.firstOrgCd.getValue(), dsOrgUnit.firstOrgCd.getValue());
                    this.existUpdatedFlag = true;
                }
//            }
        }

        // 2016/04/13 CSA-QC#2966 Add Start
        if (organizationTerminateCount > 0) {
            super.commit();
        }
        // 2016/04/13 CSA-QC#2966 Add Start
    }

    // 2016/09/01 CSA-QC#11604 Add Start
    private void inactivatePerson() {
        int personInactivateCount = 0;

        Map<String, String> personInactivateSearchMap = new HashMap<String, String>();
        personInactivateSearchMap.put("glblCmpyCd", this.glblCmpyCd);
        personInactivateSearchMap.put("procDt", this.procDt);
        personInactivateSearchMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);

        List<Map<String, Object>> personInactivateList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPersonInactivateList", personInactivateSearchMap);

        for (Map<String, Object> personInactivateMap : personInactivateList) {
            this.totalCount++;
            String psnCd = (String) personInactivateMap.get(NMAB240001Constant.COLUMN_NM_PSN_CD);

            S21_PSNTMsg s21PsnTmsg = new S21_PSNTMsg();
            ZYPEZDItemValueSetter.setValue(s21PsnTmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(s21PsnTmsg.psnCd, psnCd);

            s21PsnTmsg = (S21_PSNTMsg) EZDTBLAccessor.findByKeyForUpdate(s21PsnTmsg);
            if (s21PsnTmsg != null) {
                ZYPEZDItemValueSetter.setValue(s21PsnTmsg.delFlg, ZYPConstant.FLG_ON_Y);
                S21FastTBLAccessor.update(s21PsnTmsg);

                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(s21PsnTmsg.getReturnCode())) {
                    this.totalErrCount++;
                    super.rollback();
                } else {
                    this.totalNmlCount++;
                    personInactivateCount++;
                    if (personInactivateCount == this.commitUnit) {
                        super.commit();
                        personInactivateCount = 0;
                    }
                }
            }
        }

        if (personInactivateCount > 0) {
            super.commit();
        }
    }

    // 2016/09/01 CSA-QC#11604 Add End

    // Add Start 2019/02/15 QC#29668
    /**
     * createChangeRequestForResourceRevenueData
     */
    private void createChangeRequestForResourceRevenueData() {
        Map<String, String> revenueMap = new HashMap<String, String>();
        revenueMap.put("glblCmpyCd", this.glblCmpyCd);
        revenueMap.put("procDt", this.procDt);
        revenueMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        revenueMap.put("revCoaFlg", ZYPConstant.FLG_ON_Y);
        revenueMap.put("tocRgtnReqFlg", ZYPConstant.FLG_ON_Y);

        // Get new revenue data(1 record for 1 person)
        List<Map<String, Object>> targetRevenue = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getNewDsOrgResrcRevenue", revenueMap);

        int targetRevenueCount = 0;
        for (Map<String, Object> targetRevenueMap : targetRevenue) {
            // Create change request
            boolean createChangeRequestFlag = this.isCreateChangeRequestForOneResourceRevenueData(targetRevenueMap);

            if (createChangeRequestFlag) {
                // Update TOC_RGTN_REQ_FLG
                this.updateDsOrgResrcRev(targetRevenueMap);
            }

            targetRevenueCount++;
            if (targetRevenueCount == this.commitUnit) {
                super.commit();
                targetRevenueCount = 0;
            }
            this.totalNmlCount++;
        }

        if (targetRevenueCount > 0) {
            super.commit();
        }
    }

    /**
     * createChangeRequestForOneResourceRevenueData
     * @param targetRevenueMap Map<String, Object>
     * @return boolean
     */
    private boolean isCreateChangeRequestForOneResourceRevenueData(Map<String, Object> targetRevenueMap) {
        String psnCd = (String) targetRevenueMap.get(COLUMN_NM_PSN_CD);

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("procDt", this.procDt);
        ssmParam.put("psnCd", psnCd);
        ssmParam.put("flgOffN", ZYPConstant.FLG_OFF_N);
        ssmParam.put("gnrnTpDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpTerminated", GNRN_TP.TERMINATED);
        ssmParam.put("gnrnTpPast", GNRN_TP.PAST);
        ssmParam.put("maxEndDate", MAX_END_DATE);

        // Get active organization for 1 person
        List<Map<String, Object>> activeOrganization = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getActiveOrganization", ssmParam);

        if (activeOrganization == null || activeOrganization.size() == 0) {
            return false;
        }

        for (Map<String, Object> activeOrganizationMap : activeOrganization) {
            String tocCd = (String) activeOrganizationMap.get(COLUMN_NM_TOC_CD);

            // TOC existing check
            TOCTMsg tocTMsg = new TOCTMsg();
            ZYPEZDItemValueSetter.setValue(tocTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tocTMsg.tocCd, tocCd);

            tocTMsg = (TOCTMsg) EZDTBLAccessor.findByKey(tocTMsg);
            if (tocTMsg == null) {
                // Already created by screen(NMAL2510).
                continue;
            }

            // Numbering new TOC_CD
            String newTocCd = ZYPExtnNumbering.getUniqueID(BIZAPL_TOCCDKEY);

            // Chage to PAST date / Insert ORG_FUNC_ASG
            this.changePastInsertOrgFuncAsg(psnCd, tocCd, newTocCd, activeOrganizationMap);

            // Chage to PAST date / Insert TOC_ORG_STRU_RELN
            this.changePastInsertTocOrgStruReln(psnCd, tocCd, newTocCd, activeOrganizationMap);

            // Insert ORG_TOC_CHNG_RQST
            this.insertOrgTocChngRqst(psnCd, tocCd, newTocCd, targetRevenueMap, activeOrganizationMap);
        }

        return true;
    }

    /**
     * changePastInsertOrgFuncAsg
     * @param psnCd String
     * @param tocCd String
     * @param newTocCd String
     * @param activeOrganizationMap Map<String, Object>
     */
    private void changePastInsertOrgFuncAsg(String psnCd, String tocCd, String newTocCd, Map<String, Object> activeOrganizationMap) {
        String effFromDtAsg = (String) activeOrganizationMap.get(COLUMN_NM_EFF_FROM_DT_ASG);
        String effThruDtAsg = (String) activeOrganizationMap.get(COLUMN_NM_EFF_THRU_DT_ASG);

        // Chage to PAST date(Update or Delete/Insert)
        ORG_FUNC_ASGTMsg insOrgFuncAsgTMsg = null;
        String pastEffThruDt = ZYPDateUtil.addDays(this.procDt, -1);

        ORG_FUNC_ASGTMsg oldOrgFuncAsgTMsg = new ORG_FUNC_ASGTMsg();
        ZYPEZDItemValueSetter.setValue(oldOrgFuncAsgTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(oldOrgFuncAsgTMsg.tocCd, tocCd);
        ZYPEZDItemValueSetter.setValue(oldOrgFuncAsgTMsg.psnCd, psnCd);
        ZYPEZDItemValueSetter.setValue(oldOrgFuncAsgTMsg.effFromDt, effFromDtAsg);

        boolean delInsFlag = false;
        if (effFromDtAsg.compareTo(pastEffThruDt) > 0) {
            delInsFlag = true;
            insOrgFuncAsgTMsg = (ORG_FUNC_ASGTMsg) oldOrgFuncAsgTMsg.clone();

            // If effFromDt > effThruDt, effFromDt is same as
            // effThruDt.
            ZYPEZDItemValueSetter.setValue(insOrgFuncAsgTMsg.effFromDt, pastEffThruDt);
            ZYPEZDItemValueSetter.setValue(insOrgFuncAsgTMsg.effThruDt, pastEffThruDt);
            ZYPEZDItemValueSetter.setValue(insOrgFuncAsgTMsg.gnrnTpCd, GNRN_TP.FUTURE);
        }

        oldOrgFuncAsgTMsg = (ORG_FUNC_ASGTMsg) EZDTBLAccessor.findByKeyForUpdate(oldOrgFuncAsgTMsg);

        if (oldOrgFuncAsgTMsg != null) {
            if (delInsFlag) {
                // Delete ORG_FUNC_ASG
                S21FastTBLAccessor.removePhysical(new ORG_FUNC_ASGTMsg[] {oldOrgFuncAsgTMsg });

                String rtCd = oldOrgFuncAsgTMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
                    this.totalErrCount++;
                    super.rollback();
                    throw new S21AbendException(NMAB240001Constant.MMAM0005E, new String[] {TBL_NM_ORG_FUNC_ASG });
                }
            } else {
                // Update ORG_FUNC_ASG
                ZYPEZDItemValueSetter.setValue(oldOrgFuncAsgTMsg.effThruDt, pastEffThruDt);
                ZYPEZDItemValueSetter.setValue(oldOrgFuncAsgTMsg.gnrnTpCd, GNRN_TP.FUTURE);

                S21FastTBLAccessor.update(oldOrgFuncAsgTMsg);

                String rtCd = oldOrgFuncAsgTMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
                    this.totalErrCount++;
                    super.rollback();
                    throw new S21AbendException(NMAB240001Constant.MMAM0004E, new String[] {TBL_NM_ORG_FUNC_ASG });
                }
            }
        }

        if (delInsFlag) {
            EZDTBLAccessor.insert(insOrgFuncAsgTMsg);

            String rtCd = insOrgFuncAsgTMsg.getReturnCode();
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(NMAB240001Constant.MMAM0003E, new String[] {TBL_NM_ORG_FUNC_ASG });
            }
        }

        // Insert
        ORG_FUNC_ASGTMsg newOrgFuncAsgTMsg = new ORG_FUNC_ASGTMsg();
        ZYPEZDItemValueSetter.setValue(newOrgFuncAsgTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(newOrgFuncAsgTMsg.tocCd, newTocCd);
        ZYPEZDItemValueSetter.setValue(newOrgFuncAsgTMsg.psnCd, psnCd);
        ZYPEZDItemValueSetter.setValue(newOrgFuncAsgTMsg.effFromDt, this.procDt);
        ZYPEZDItemValueSetter.setValue(newOrgFuncAsgTMsg.effThruDt, effThruDtAsg);
        ZYPEZDItemValueSetter.setValue(newOrgFuncAsgTMsg.gnrnTpCd, GNRN_TP.FUTURE);

        S21FastTBLAccessor.insert(newOrgFuncAsgTMsg);

        String rtCd = newOrgFuncAsgTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
            this.totalErrCount++;
            super.rollback();
            throw new S21AbendException(NMAB240001Constant.MMAM0003E, new String[] {TBL_NM_ORG_FUNC_ASG });
        }
    }

    /**
     * changePastInsertTocOrgStruReln
     * @param psnCd String
     * @param tocCd String
     * @param newTocCd String
     * @param activeOrganizationMap Map<String, Object>
     */
    private void changePastInsertTocOrgStruReln(String psnCd, String tocCd, String newTocCd, Map<String, Object> activeOrganizationMap) {
        String orgStruTpCd = (String) activeOrganizationMap.get(COLUMN_NM_ORG_STRU_TP_CD);
        String orgCd = (String) activeOrganizationMap.get(COLUMN_NM_ORG_CD);
        BigDecimal orgLayerNum = (BigDecimal) activeOrganizationMap.get(COLUMN_NM_ORG_LAYER_NUM);
        String effThruDtReln = (String) activeOrganizationMap.get(COLUMN_NM_EFF_THRU_DT_RELN);

        // Chage to PAST date
        TOC_ORG_STRU_RELNTMsg oldTocOrgStruRelnTMsg = new TOC_ORG_STRU_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(oldTocOrgStruRelnTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(oldTocOrgStruRelnTMsg.orgStruTpCd, orgStruTpCd);
        ZYPEZDItemValueSetter.setValue(oldTocOrgStruRelnTMsg.tocCd, tocCd);
        ZYPEZDItemValueSetter.setValue(oldTocOrgStruRelnTMsg.orgCd, orgCd);
        oldTocOrgStruRelnTMsg = (TOC_ORG_STRU_RELNTMsg) EZDTBLAccessor.findByKeyForUpdate(oldTocOrgStruRelnTMsg);

        if (oldTocOrgStruRelnTMsg != null) {
            String effFromDt = oldTocOrgStruRelnTMsg.effFromDt.getValue();
            String effThruDt = ZYPDateUtil.addDays(this.procDt, -1);

            if (ZYPCommonFunc.hasValue(effFromDt) && effFromDt.compareTo(effThruDt) > 0) {
                // If effFromDt > effThruDt, effFromDt is same as
                // effThruDt.
                ZYPEZDItemValueSetter.setValue(oldTocOrgStruRelnTMsg.effFromDt, effThruDt);
            }

            // Update TOC_ORG_STRU_RELN
            ZYPEZDItemValueSetter.setValue(oldTocOrgStruRelnTMsg.effThruDt, effThruDt);

            S21FastTBLAccessor.update(oldTocOrgStruRelnTMsg);

            String rtCd = oldTocOrgStruRelnTMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(NMAB240001Constant.MMAM0004E, new String[] {TBL_NM_TOC_ORG_STRU_RELN });
            }
        }

        // Insert
        TOC_ORG_STRU_RELNTMsg newTocOrgStruRelnTMsg = new TOC_ORG_STRU_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(newTocOrgStruRelnTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(newTocOrgStruRelnTMsg.orgStruTpCd, orgStruTpCd);
        ZYPEZDItemValueSetter.setValue(newTocOrgStruRelnTMsg.tocCd, newTocCd);
        ZYPEZDItemValueSetter.setValue(newTocOrgStruRelnTMsg.orgCd, orgCd);
        ZYPEZDItemValueSetter.setValue(newTocOrgStruRelnTMsg.orgLayerNum, orgLayerNum);
        ZYPEZDItemValueSetter.setValue(newTocOrgStruRelnTMsg.effFromDt, this.procDt);
        ZYPEZDItemValueSetter.setValue(newTocOrgStruRelnTMsg.effThruDt, effThruDtReln);

        S21FastTBLAccessor.insert(newTocOrgStruRelnTMsg);

        String rtCd = newTocOrgStruRelnTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
            this.totalErrCount++;
            super.rollback();
            throw new S21AbendException(NMAB240001Constant.MMAM0003E, new String[] {TBL_NM_TOC_ORG_STRU_RELN });
        }
    }

    /**
     * insertOrgTocChngRqst
     * @param psnCd String
     * @param tocCd String
     * @param newTocCd String
     * @param targetRevenueMap Map<String, Object>
     * @param activeOrganizationMap Map<String, Object>
     */
    private void insertOrgTocChngRqst(String psnCd, String tocCd, String newTocCd, //
            Map<String, Object> targetRevenueMap, Map<String, Object> activeOrganizationMap) {

        String coaCmpyCd = (String) targetRevenueMap.get(COLUMN_NM_COA_CMPY_CD);
        String coaExtnCd = (String) targetRevenueMap.get(COLUMN_NM_COA_EXTN_CD);
        String coaBrCd = (String) targetRevenueMap.get(COLUMN_NM_COA_BR_CD);
        String coaCcCd = (String) targetRevenueMap.get(COLUMN_NM_COA_CC_CD);

        String orgFuncRoleTpCd = (String) activeOrganizationMap.get(COLUMN_NM_ORG_FUNC_ROLE_TP_CD);
        String orgCd = (String) activeOrganizationMap.get(COLUMN_NM_ORG_CD);
        String psnFirstNm = (String) activeOrganizationMap.get(COLUMN_NM_PSN_FIRST_NM);
        String psnLastNm = (String) activeOrganizationMap.get(COLUMN_NM_PSN_LAST_NM);

        ORG_TOC_CHNG_RQSTTMsg orgTocChngRqstTMsg = new ORG_TOC_CHNG_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.tocCd, newTocCd);

        BigDecimal orgChngRqstPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.ORG_CHNG_RQST_SQ);
        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.orgChngRqstPk, orgChngRqstPk);

        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.tocNm, S21StringUtil.subStringByLength(//
                S21StringUtil.concatStrings(psnFirstNm, " ", psnLastNm), 0, COLUMN_LEN_TOC_NM));
        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.orgFuncRoleTpCd, orgFuncRoleTpCd);
        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.orgCd, orgCd);
        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);

        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaBrCd, coaBrCd);
        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaExtnCd, coaExtnCd);
        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaCcCd, coaCcCd);
        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaCmpyCd, coaCmpyCd);

        S21FastTBLAccessor.insert(orgTocChngRqstTMsg);

        String rtCd = orgTocChngRqstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
            this.totalErrCount++;
            super.rollback();
            throw new S21AbendException(NMAB240001Constant.MMAM0003E, new String[] {TBL_NM_ORG_TOC_CHNG_RQST });
        }
    }

    /**
     * updateDsOrgResrcRev
     * @param targetRevenueMap Map<String, Object>
     */
    private void updateDsOrgResrcRev(Map<String, Object> targetRevenueMap) {
        String psnCd = (String) targetRevenueMap.get(COLUMN_NM_PSN_CD);
        String revAcctTpCd = (String) targetRevenueMap.get(COLUMN_NM_REV_ACCT_TP_CD);
        String effFromDt = (String) targetRevenueMap.get(COLUMN_NM_EFF_FROM_DT);

        DS_ORG_RESRC_REVTMsg dsOrgResrcRevTmsg = new DS_ORG_RESRC_REVTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTmsg.psnCd, psnCd);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTmsg.revAcctTpCd, revAcctTpCd);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTmsg.effFromDt, effFromDt);

        dsOrgResrcRevTmsg = (DS_ORG_RESRC_REVTMsg) EZDTBLAccessor.findByKeyForUpdate(dsOrgResrcRevTmsg);

        if (dsOrgResrcRevTmsg != null) {
            // Update TOC_RGTN_REQ_FLG
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTmsg.tocRgtnReqFlg, ZYPConstant.FLG_OFF_N);

            S21FastTBLAccessor.update(dsOrgResrcRevTmsg);

            String rtCd = dsOrgResrcRevTmsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(NMAB240001Constant.MMAM0004E, new String[] {TBL_NM_DS_ORG_RESRC_REV });
            }
        }
    }
    // Add End 2019/02/15 QC#29668

    /**
     * Activate Toc Related Table Change Generation Type Code from
     * Future to Current.
     */
    private void activateToc() {
        Map<String, String> asgActivateMap = new HashMap<String, String>();
        asgActivateMap.put("glblCmpyCd", this.glblCmpyCd);
        asgActivateMap.put("procDt", this.procDt);
        asgActivateMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        asgActivateMap.put("gnrnTpCd_3", GNRN_TP.FUTURE);
        // 2016/06/20 CSA-QC#10424 Add Start
        asgActivateMap.put("rgtnStsCd", RGTN_STS.PENDING_COMPLETION);
        // 2016/06/20 CSA-QC#10424 Add End

        List<Map<String, Object>> asgActivateList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getAsgActivateList", asgActivateMap);

        int asgActivateCount = 0;
        for (Map<String, Object> asgActivateListMap : asgActivateList) {
            this.totalCount++;

            String psnCd = (String) asgActivateListMap.get(NMAB240001Constant.COLUMN_NM_PSN_CD);

            // Obtain Person Information
            S21_PSNTMsg s21PsnTmsg = new S21_PSNTMsg();
            ZYPEZDItemValueSetter.setValue(s21PsnTmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(s21PsnTmsg.psnCd, psnCd);

            s21PsnTmsg = (S21_PSNTMsg) EZDTBLAccessor.findByKey(s21PsnTmsg);
            if (s21PsnTmsg == null) {
                continue;
            }

            // Check Active Date of Person
            if (!this.checkActiveDate(s21PsnTmsg.effFromDt.getValue(), s21PsnTmsg.effThruDt.getValue())) {
                continue;
            }

            // 2016/06/20 CSA-QC#10424 Mod Start
            String tocReqFlg = (String) asgActivateListMap.get(NMAB240001Constant.COLUMN_TOC_REQ_FLG);
            Map<String, Object> targetRevenue = null;
            if (ZYPConstant.FLG_ON_Y.equals(tocReqFlg)) {
                Map<String, String> revenueMap = new HashMap<String, String>();
                revenueMap.put("glblCmpyCd", this.glblCmpyCd);
                revenueMap.put("procDt", this.procDt);
                revenueMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
                revenueMap.put("psnCd", psnCd);
                revenueMap.put("revCoaFlg", ZYPConstant.FLG_ON_Y);

                targetRevenue = (Map<String, Object>) this.ssmBatchClient.queryObject("getDsOrgResrcRevenue", revenueMap);
                if (targetRevenue == null) {
                    continue;
                }
            }
            // 2016/06/20 CSA-QC#10424 Mod End

            // Obtain Organization Information
            String tocCd = (String) asgActivateListMap.get(NMAB240001Constant.COLUMN_NM_TOC_CD);

            Map<String, String> orgTocMap = new HashMap<String, String>();
            orgTocMap.put("glblCmpyCd", this.glblCmpyCd);
            orgTocMap.put("procDt", this.procDt);
            orgTocMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
            orgTocMap.put("gnrnTpCd_2", GNRN_TP.CURRENT);
            orgTocMap.put("gnrnTpCd_3", GNRN_TP.FUTURE);
            orgTocMap.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
            orgTocMap.put("tocCd", tocCd);

            Map<String, Object> orgTocList = (Map<String, Object>) this.ssmBatchClient.queryObject("getActivateOrgTocList", orgTocMap);
            if (orgTocList == null) {
                continue;
            }

            String orgCd = (String) orgTocList.get(NMAB240001Constant.COLUMN_NM_ORG_CD);

            // Obtain Toc from Person and Organization Information
            String effFromDt = (String) asgActivateListMap.get(NMAB240001Constant.COLUMN_NM_EFF_FROM_DT);

            Map<String, String> orgTocChngRqstMap = new HashMap<String, String>();
            orgTocChngRqstMap.put("glblCmpyCd", this.glblCmpyCd);
            orgTocChngRqstMap.put("rgtnStsCd", RGTN_STS.PENDING_COMPLETION);
            orgTocChngRqstMap.put("orgCd", orgCd);
            orgTocChngRqstMap.put("tocCd", tocCd);

            List<Map<String, Object>> orgTocChngRqstList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getOrgTocChngRqstActivateList", orgTocChngRqstMap);

            for (Map<String, Object> orgTocChngRqstListMap : orgTocChngRqstList) {
                this.updateOrgFuncAsg(tocCd, psnCd, effFromDt, GNRN_TP.CURRENT);

                BigDecimal orgChngRqstPk = (BigDecimal) orgTocChngRqstListMap.get(NMAB240001Constant.COLUMN_NM_ORG_CHNG_RQST_PK);
                this.updateOrgTocChngRqst(tocCd, orgChngRqstPk, RGTN_STS.READY_FOR_ORDER_TAKING, targetRevenue);

                String orgFuncRoleTpCd = (String) orgTocChngRqstListMap.get(NMAB240001Constant.COLUMN_NM_ORG_FUNC_ROLE_TP_CD);

                if (this.isCreateTechMaster(orgFuncRoleTpCd)) {
                    this.updateTechMstr(s21PsnTmsg);
                }

                if (!this.isTargetS21Org(orgFuncRoleTpCd)) {
                    continue;
                }

                TOCTMsg tocTmsg = new TOCTMsg();
                ZYPEZDItemValueSetter.setValue(tocTmsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tocTmsg.tocCd, tocCd);
                tocTmsg = (TOCTMsg) EZDTBLAccessor.findByKey(tocTmsg);

                if (tocTmsg != null) {
                    DS_ORG_UNITTMsg dsOrgUnitTmsg = new DS_ORG_UNITTMsg();
                    ZYPEZDItemValueSetter.setValue(dsOrgUnitTmsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dsOrgUnitTmsg.orgCd, orgCd);
                    dsOrgUnitTmsg = (DS_ORG_UNITTMsg) EZDTBLAccessor.findByKey(dsOrgUnitTmsg);

                    if (dsOrgUnitTmsg != null) {
                        // 2016/03/02 CSA-QC#4627 Delete Start
                        // if
                        // (NMAB240001Constant.MODE_DAILY.equals(this.mode))
                        // {
                        // this.updateS21Org(tocTmsg, dsOrgUnitTmsg);
                        // this.existUpdatedFlag = true;
                        //
                        // } else if
                        // (NMAB240001Constant.MODE_NIGHT.equals(this.mode))
                        // {
                        // 2016/03/02 CSA-QC#4627 Delete End
                        if (!this.bizAreaOrgCdMap.containsKey(dsOrgUnitTmsg.firstOrgCd.getValue())) {
                            this.bizAreaOrgCdMap.put(dsOrgUnitTmsg.firstOrgCd.getValue(), dsOrgUnitTmsg.firstOrgCd.getValue());
                            this.existUpdatedFlag = true;
                        }
                        // }
                    }
                }
            }

            asgActivateCount++;
            if (asgActivateCount == this.commitUnit) {
                super.commit();
                asgActivateCount = 0;
            }
            this.totalNmlCount++;
        }

        // 2016/04/13 CSA-QC#2966 Add Start
        if (asgActivateCount > 0) {
            super.commit();
        }
        // 2016/04/13 CSA-QC#2966 Add Start
    }

    /**
     * Terminate Toc Related Table Change Generation Type Code from
     * Current to Past.
     */
    private void inactivateToc() {
        Map<String, String> asgTerminateMap = new HashMap<String, String>();
        asgTerminateMap.put("glblCmpyCd", this.glblCmpyCd);
        asgTerminateMap.put("procDt", this.procDt);
        asgTerminateMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        asgTerminateMap.put("gnrnTpCd", GNRN_TP.CURRENT);
        // Add Start 2016/07/05 Start QC#11081
        asgTerminateMap.put("gnrnTpCdFtr", GNRN_TP.FUTURE);
        // Add End 2016/07/05 Start QC#11081

        List<Map<String, Object>> asgTerminateList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getAsgTerminateList", asgTerminateMap);

        int asgTerminateCount = 0;
        for (Map<String, Object> asgTerminateListMap : asgTerminateList) {
            this.totalCount++;

            String psnCd = (String) asgTerminateListMap.get(NMAB240001Constant.COLUMN_NM_PSN_CD);

            // Obtain Person Information
            S21_PSNTMsg s21PsnTmsg = new S21_PSNTMsg();
            ZYPEZDItemValueSetter.setValue(s21PsnTmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(s21PsnTmsg.psnCd, psnCd);

            s21PsnTmsg = (S21_PSNTMsg) EZDTBLAccessor.findByKey(s21PsnTmsg);
            if (s21PsnTmsg == null) {
                continue;
            }

            // Del Start 2017/10/20 Start QC#21359
            //// Check Active Date of Person
            //if (!this.checkActiveDate(s21PsnTmsg.effFromDt.getValue(), s21PsnTmsg.effThruDt.getValue())) {
            //    continue;
            //}
            // Del End   2017/10/20 Start QC#21359

            // Obtain Organization Information
            String tocCd = (String) asgTerminateListMap.get(NMAB240001Constant.COLUMN_NM_TOC_CD);

            Map<String, String> orgTocMap = new HashMap<String, String>();
            orgTocMap.put("glblCmpyCd", this.glblCmpyCd);
            orgTocMap.put("procDt", this.procDt);
            orgTocMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
            orgTocMap.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
            orgTocMap.put("tocCd", tocCd);

            Map<String, Object> orgTocInfo = (Map<String, Object>) this.ssmBatchClient.queryObject("getTerminateOrgTocList", orgTocMap);
            if (orgTocInfo == null) {
                continue;
            }

            String orgCd = (String) orgTocInfo.get(NMAB240001Constant.COLUMN_NM_ORG_CD);

            // Obtain Toc from Person and Organization Information
            Map<String, String> orgTocChngRqstMap = new HashMap<String, String>();
            orgTocChngRqstMap.put("glblCmpyCd", this.glblCmpyCd);
            orgTocChngRqstMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            // Add Start 2016/07/05 Start QC#11081
            orgTocChngRqstMap.put("rgtnStsCdPC", RGTN_STS.PENDING_COMPLETION);
            // Add End 2016/07/05 Start QC#11081
            orgTocChngRqstMap.put("orgCd", orgCd);
            orgTocChngRqstMap.put("tocCd", tocCd);

            List<Map<String, Object>> orgTocChngRqstList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getOrgTocChngRqstTerminateList", orgTocChngRqstMap);

            for (Map<String, Object> orgTocChngRqstListMap : orgTocChngRqstList) {
                String effFromDt = (String) asgTerminateListMap.get(NMAB240001Constant.COLUMN_NM_EFF_FROM_DT);
                // Add Start 2016/07/05 QC#11081
                boolean rgtnPCflag = false;
                if (RGTN_STS.PENDING_COMPLETION.equals(orgTocChngRqstListMap.get(NMAB240001Constant.COLUMN_RGTN_STS_CD))) {
                    rgtnPCflag = true;
                }
                // Add End 2016/07/05 QC#11081

                this.updateOrgFuncAsg(tocCd, psnCd, effFromDt, GNRN_TP.PAST);

                BigDecimal orgChngRqstPk = (BigDecimal) orgTocChngRqstListMap.get(NMAB240001Constant.COLUMN_NM_ORG_CHNG_RQST_PK);
                this.deleteOrgTocChngRqst(tocCd, orgChngRqstPk, RGTN_STS.TERMINATED);

                String orgFuncRoleTpCd = (String) orgTocChngRqstListMap.get(NMAB240001Constant.COLUMN_NM_ORG_FUNC_ROLE_TP_CD);

                // 2020/02/03 QC#55306 Add Start
                this.deleteS21Org(tocCd, RGTN_STS.TERMINATED);
                // 2020/02/03 QC#55306 Add End

                // Mod Start 2016/07/05 QC#11081
                if (!this.isTargetS21Org(orgFuncRoleTpCd) || rgtnPCflag) {
                    continue;
                }
                // Mod End 2016/07/05 QC#11081

                // 2017/12/07 QC#22899 Mod Start
                // if (NMAB240001Constant.MODE_DAILY.equals(this.mode)) {
                //     this.deleteS21Org(tocCd, RGTN_STS.TERMINATED);
                // }
                // 2020/02/03 QC#55306 Del Start
//                this.deleteS21Org(tocCd, RGTN_STS.TERMINATED);
                // 2020/02/03 QC#55306 Del End
                // 2017/12/07 QC#22899 Mod End
                // 2016/03/02 CSA-QC#4672 Delete Start
                // this.existUpdatedFlag = true;
                //
                // } else if
                // (NMAB240001Constant.MODE_NIGHT.equals(this.mode)) {
                // 2016/03/02 CSA-QC#4672 Delete End
                DS_ORG_UNITTMsg dsOrgUnitTmsg = new DS_ORG_UNITTMsg();
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTmsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTmsg.orgCd, orgCd);

                dsOrgUnitTmsg = (DS_ORG_UNITTMsg) EZDTBLAccessor.findByKey(dsOrgUnitTmsg);
                if (dsOrgUnitTmsg != null) {
                    if (!this.bizAreaOrgCdMap.containsKey(dsOrgUnitTmsg.firstOrgCd.getValue())) {
                        this.bizAreaOrgCdMap.put(dsOrgUnitTmsg.firstOrgCd.getValue(), dsOrgUnitTmsg.firstOrgCd.getValue());
                        this.existUpdatedFlag = true;
                    }
                }
                // }
            }

            asgTerminateCount++;
            if (asgTerminateCount == this.commitUnit) {
                super.commit();
                asgTerminateCount = 0;
            }
            this.totalNmlCount++;
        }

        // 2016/04/13 CSA-QC#2966 Add Start
        if (asgTerminateCount > 0) {
            super.commit();
        }
        // 2016/04/13 CSA-QC#2966 Add Start
    }

    /**
     * Terminate Toc Related Table Change Generation Type Code from
     * Delete to Terminate.
     */
    private void terminateToc() {
        Map<String, String> asgTerminateMap = new HashMap<String, String>();
        asgTerminateMap.put("glblCmpyCd", this.glblCmpyCd);
        asgTerminateMap.put("procDt", this.procDt);
        asgTerminateMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        asgTerminateMap.put("gnrnTpCd", GNRN_TP.DELETE);

        List<Map<String, Object>> asgTerminateList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getAsgTerminateList", asgTerminateMap);

        int asgTerminateCount = 0;
        for (Map<String, Object> asgTerminateListMap : asgTerminateList) {

            this.totalCount++;
            String psnCd = (String) asgTerminateListMap.get(NMAB240001Constant.COLUMN_NM_PSN_CD);
            String tocCd = (String) asgTerminateListMap.get(NMAB240001Constant.COLUMN_NM_TOC_CD);

            Map<String, String> orgTocMap = new HashMap<String, String>();
            orgTocMap.put("glblCmpyCd", this.glblCmpyCd);
            orgTocMap.put("procDt", this.procDt);
            orgTocMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
            orgTocMap.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
            orgTocMap.put("tocCd", tocCd);

            Map<String, Object> orgTocInfo = (Map<String, Object>) this.ssmBatchClient.queryObject("getTerminateOrgTocList", orgTocMap);
            if (orgTocInfo == null) {
                continue;
            }

            String orgCd = (String) orgTocInfo.get(NMAB240001Constant.COLUMN_NM_ORG_CD);

            // Obtain Toc from Person and Organization Information
            Map<String, String> orgTocChngRqstMap = new HashMap<String, String>();
            orgTocChngRqstMap.put("glblCmpyCd", this.glblCmpyCd);
            orgTocChngRqstMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            orgTocChngRqstMap.put("orgCd", orgCd);
            orgTocChngRqstMap.put("tocCd", tocCd);

            List<Map<String, Object>> orgTocChngRqstList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getOrgTocChngRqstTerminateList", orgTocChngRqstMap);

            for (Map<String, Object> orgTocChngRqstListMap : orgTocChngRqstList) {
                String effFromDt = (String) asgTerminateListMap.get(NMAB240001Constant.COLUMN_NM_EFF_FROM_DT);

                BigDecimal orgChngRqstPk = (BigDecimal) orgTocChngRqstListMap.get(NMAB240001Constant.COLUMN_NM_ORG_CHNG_RQST_PK);
                this.deleteOrgTocChngRqst(tocCd, orgChngRqstPk, RGTN_STS.TERMINATED);

                String orgFuncRoleTpCd = (String) orgTocChngRqstListMap.get(NMAB240001Constant.COLUMN_NM_ORG_FUNC_ROLE_TP_CD);

                this.updateOrgFuncAsg(tocCd, psnCd, effFromDt, GNRN_TP.TERMINATED);

                if (!this.isTargetS21Org(orgFuncRoleTpCd)) {
                    continue;
                }

                // 2017/12/07 QC#22899 Mod Start
                // if (NMAB240001Constant.MODE_DAILY.equals(this.mode)) {
                //    this.deleteS21Org(tocCd, RGTN_STS.TERMINATED);
                // }
                this.deleteS21Org(tocCd, RGTN_STS.TERMINATED);
                // 2017/12/07 QC#22899 Mod End

                DS_ORG_UNITTMsg dsOrgUnitTmsg = new DS_ORG_UNITTMsg();
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTmsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTmsg.orgCd, orgCd);

                dsOrgUnitTmsg = (DS_ORG_UNITTMsg) EZDTBLAccessor.findByKey(dsOrgUnitTmsg);
                if (dsOrgUnitTmsg != null) {
                    if (!this.bizAreaOrgCdMap.containsKey(dsOrgUnitTmsg.firstOrgCd.getValue())) {
                        this.bizAreaOrgCdMap.put(dsOrgUnitTmsg.firstOrgCd.getValue(), dsOrgUnitTmsg.firstOrgCd.getValue());
                        this.existUpdatedFlag = true;
                    }
                }
                // }
            }

            asgTerminateCount++;
            if (asgTerminateCount == this.commitUnit) {
                super.commit();
                asgTerminateCount = 0;
            }
            this.totalNmlCount++;
        }

        // 2016/04/13 CSA-QC#2966 Add Start
        if (asgTerminateCount > 0) {
            super.commit();
        }
        // 2016/04/13 CSA-QC#2966 Add Start
    }

    /**
     * update DS_ORG_UNIT.GNRN_TP_CD
     * @param orgCd String
     * @param gnrnTpCd String
     */
    private void updateDsOrgUnit(String orgCd, String gnrnTpCd, String orgStruTpCd) {
        DS_ORG_UNITTMsg dsOrgUnitTmsg = new DS_ORG_UNITTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrgUnitTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrgUnitTmsg.orgCd, orgCd);

        dsOrgUnitTmsg = (DS_ORG_UNITTMsg) EZDTBLAccessor.findByKeyForUpdate(dsOrgUnitTmsg);
        if (dsOrgUnitTmsg != null) {
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTmsg.gnrnTpCd, gnrnTpCd);
            S21FastTBLAccessor.update(dsOrgUnitTmsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsOrgUnitTmsg.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                if (!ORG_STRU_TP.TERRITORY_STRUCTURE.equals(orgStruTpCd)) {
                    throw new S21AbendException(NMAB240001Constant.MMAM0004E, new String[] {NMAB240001Constant.TBL_NM_DS_ORG_UNIT });
                }
            }
        }
    }

    /**
     * update DS_ORG_RELN.GNRN_TP_CD
     * @param orgCd String
     * @param prntOrgCd String
     * @param orgStruTpCd String
     * @param effFromDt String
     * @param gnrnTpCd String
     */
    private void updateDsOrgReln(String orgCd, String prntOrgCd, String orgStruTpCd, String effFromDt, String gnrnTpCd, BigDecimal dsOrgRelnPk) {
        DS_ORG_RELNTMsg dsOrgRelnTmsg = new DS_ORG_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrgRelnTmsg.glblCmpyCd, this.glblCmpyCd);
        // 2017/03/07 S21_NA#17760 Mod Start
        //        ZYPEZDItemValueSetter.setValue(dsOrgRelnTmsg.orgCd, orgCd);
        //        ZYPEZDItemValueSetter.setValue(dsOrgRelnTmsg.prntOrgCd, prntOrgCd);
        //        ZYPEZDItemValueSetter.setValue(dsOrgRelnTmsg.orgStruTpCd, orgStruTpCd);
        //        ZYPEZDItemValueSetter.setValue(dsOrgRelnTmsg.effFromDt, effFromDt);
        ZYPEZDItemValueSetter.setValue(dsOrgRelnTmsg.dsOrgRelnPk, dsOrgRelnPk);
        // 2017/03/07 S21_NA#17760 Mod End

        dsOrgRelnTmsg = (DS_ORG_RELNTMsg) EZDTBLAccessor.findByKeyForUpdate(dsOrgRelnTmsg);
        if (dsOrgRelnTmsg != null) {
            ZYPEZDItemValueSetter.setValue(dsOrgRelnTmsg.gnrnTpCd, gnrnTpCd);
            S21FastTBLAccessor.update(dsOrgRelnTmsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsOrgRelnTmsg.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                if (!ORG_STRU_TP.TERRITORY_STRUCTURE.equals(orgStruTpCd)) {
                    throw new S21AbendException(NMAB240001Constant.MMAM0004E, new String[] {NMAB240001Constant.TBL_NM_DS_ORG_RELN });
                }
            }
        }
    }

    /**
     * update DS_ORG_RESRC_RELN.GNRN_TP_CD
     * @param orgCd String
     * @param orgStruTpCd String
     * @param psnCd String
     * @param orgFuncRoleTpCd String
     * @param effFromDt String
     * @param gnrnTpCd String
     */
    private void updateDsOrgResrcReln(String orgCd, String orgStruTpCd, String psnCd, String orgFuncRoleTpCd, String effFromDt, String gnrnTpCd) {
        DS_ORG_RESRC_RELNTMsg dsOrgResrcRelnTmsg = new DS_ORG_RESRC_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTmsg.orgCd, orgCd);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTmsg.orgStruTpCd, orgStruTpCd);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTmsg.psnCd, psnCd);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTmsg.orgFuncRoleTpCd, orgFuncRoleTpCd);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTmsg.effFromDt, effFromDt);

        dsOrgResrcRelnTmsg = (DS_ORG_RESRC_RELNTMsg) EZDTBLAccessor.findByKeyForUpdate(dsOrgResrcRelnTmsg);
        if (dsOrgResrcRelnTmsg != null) {
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTmsg.gnrnTpCd, gnrnTpCd);
            S21FastTBLAccessor.update(dsOrgResrcRelnTmsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsOrgResrcRelnTmsg.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                if (!ORG_STRU_TP.TERRITORY_STRUCTURE.equals(orgStruTpCd)) {
                    throw new S21AbendException(NMAB240001Constant.MMAM0004E, new String[] {NMAB240001Constant.TBL_NM_DS_ORG_RESRC_RELN });
                }
            }
        }
    }

    /**
     * Update/Insert ORG_STRU
     * @param dsOrgUnit DS_ORG_UNITTMsg
     */
    private void updateOrgStru(DS_ORG_UNITTMsg dsOrgUnit) {
        BigDecimal orgLayerNum = this.convertFromTiertoLayer(dsOrgUnit.orgTierCd.getValue());

        ORG_STRUTMsg orgStruTmsg = new ORG_STRUTMsg();
        ZYPEZDItemValueSetter.setValue(orgStruTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(orgStruTmsg.orgCd, dsOrgUnit.orgCd.getValue());
        ZYPEZDItemValueSetter.setValue(orgStruTmsg.orgLayerNum, orgLayerNum);

        orgStruTmsg = (ORG_STRUTMsg) EZDTBLAccessor.findByKeyForUpdate(orgStruTmsg);
        if (orgStruTmsg != null) {
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.effThruDt, dsOrgUnit.effThruDt.getValue());

            S21FastTBLAccessor.update(orgStruTmsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(orgStruTmsg.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(NMAB240001Constant.MMAM0004E, new String[] {NMAB240001Constant.TBL_NM_ORG_STRU });
            }

            this.updateOrganizationName(dsOrgUnit, orgLayerNum);

        } else {

            this.createOrgStru(dsOrgUnit, orgLayerNum);
        }
    }

    /**
     * Convert Tier to Layer Tier 0 means Layer 1, Tier 1 means Layer
     * 2....
     * @param orgTierCd is base value
     * @return calculated value
     */
    private BigDecimal convertFromTiertoLayer(String orgTierCd) {
        return new BigDecimal(orgTierCd).add(BigDecimal.ONE);
    }

    /**
     * Update Organization Name for each Layer When Layer is 1, Update
     * First Org Name, When Layer is 2, Update Second Org Name...
     * @param dsOrgUnit DS_ORG_UNITTMsg
     * @param orgLayerNum BigDecimal
     */
    private void updateOrganizationName(DS_ORG_UNITTMsg dsOrgUnit, BigDecimal orgLayerNum) {
        Map<String, String> updateOrgStruOrgNameMap = new HashMap<String, String>();
        updateOrgStruOrgNameMap.put("glblCmpyCd", this.glblCmpyCd);
        updateOrgStruOrgNameMap.put("orgCd", dsOrgUnit.orgCd.getValue());
        updateOrgStruOrgNameMap.put("orgLayerNum", orgLayerNum.toString());

        List<Map<String, Object>> updateOrgStruOrgNameList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getUpdateOrgStruOrgNameList", updateOrgStruOrgNameMap);

        for (Map<String, Object> updateOrgStruOrgNameListMap : updateOrgStruOrgNameList) {
            String updOrgCd = (String) updateOrgStruOrgNameListMap.get(NMAB240001Constant.COLUMN_NM_ORG_CD);
            BigDecimal updOrgLayerNum = (BigDecimal) updateOrgStruOrgNameListMap.get(NMAB240001Constant.COLUMN_NM_ORG_LAYER_NUM);

            ORG_STRUTMsg orgStruTmsg = new ORG_STRUTMsg();
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.orgCd, updOrgCd);
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.orgLayerNum, updOrgLayerNum);

            orgStruTmsg = (ORG_STRUTMsg) EZDTBLAccessor.findByKeyForUpdate(orgStruTmsg);
            if (orgStruTmsg != null) {

                switch (orgLayerNum.intValue()) {
                    case NMAB240001Constant.FIRST_LAYER:
                        ZYPEZDItemValueSetter.setValue(orgStruTmsg.firstOrgNm, dsOrgUnit.orgNm.getValue());
                        break;

                    case NMAB240001Constant.SECOND_LAYER:
                        ZYPEZDItemValueSetter.setValue(orgStruTmsg.scdOrgNm, dsOrgUnit.orgNm.getValue());
                        break;

                    case NMAB240001Constant.THIRD_LAYER:
                        ZYPEZDItemValueSetter.setValue(orgStruTmsg.thirdOrgNm, dsOrgUnit.orgNm.getValue());
                        break;

                    case NMAB240001Constant.FOURTH_LAYER:
                        ZYPEZDItemValueSetter.setValue(orgStruTmsg.frthOrgNm, dsOrgUnit.orgNm.getValue());
                        break;

                    case NMAB240001Constant.FIFTH_LAYER:
                        ZYPEZDItemValueSetter.setValue(orgStruTmsg.fifthOrgNm, dsOrgUnit.orgNm.getValue());
                        break;

                    case NMAB240001Constant.SIXTH_LAYER:
                        ZYPEZDItemValueSetter.setValue(orgStruTmsg.sixthOrgNm, dsOrgUnit.orgNm.getValue());
                        break;

                    case NMAB240001Constant.SEVENTH_LAYER:
                        ZYPEZDItemValueSetter.setValue(orgStruTmsg.svnthOrgNm, dsOrgUnit.orgNm.getValue());
                        break;

                    case NMAB240001Constant.EIGHTH_LAYER:
                        ZYPEZDItemValueSetter.setValue(orgStruTmsg.eighthOrgNm, dsOrgUnit.orgNm.getValue());
                        break;

                    case NMAB240001Constant.NINTH_LAYER:
                        ZYPEZDItemValueSetter.setValue(orgStruTmsg.ninthOrgNm, dsOrgUnit.orgNm.getValue());
                        break;

                    case NMAB240001Constant.TENTH_LAYER:
                        ZYPEZDItemValueSetter.setValue(orgStruTmsg.tenthOrgNm, dsOrgUnit.orgNm.getValue());
                        break;

                    case NMAB240001Constant.ELEVENTH_LAYER:
                        ZYPEZDItemValueSetter.setValue(orgStruTmsg.elvthOrgNm, dsOrgUnit.orgNm.getValue());
                        break;

                    default:
                        break;
                }

                S21FastTBLAccessor.update(orgStruTmsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(orgStruTmsg.getReturnCode())) {
                    this.totalErrCount++;
                    super.rollback();
                    throw new S21AbendException(NMAB240001Constant.MMAM0004E, new String[] {NMAB240001Constant.TBL_NM_ORG_STRU });
                }
            }
        }
    }

    /**
     * Create ORG_STRU
     * @param dsOrgUnit DS_ORG_UNITTMsg
     * @param orgLayerNum BigDecimal
     */
    private void createOrgStru(DS_ORG_UNITTMsg dsOrgUnit, BigDecimal orgLayerNum) {

        ORG_STRUTMsg orgStruTmsg = new ORG_STRUTMsg();

        // Copy parent Layer
        String orgCd = (String) dsOrgUnit.orgCd.getValue();

        Map<String, String> orgStruParentSearchMap = new HashMap<String, String>();
        orgStruParentSearchMap.put("glblCmpyCd", this.glblCmpyCd);
        orgStruParentSearchMap.put("gnrnTpCd_2", GNRN_TP.CURRENT);
        orgStruParentSearchMap.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        orgStruParentSearchMap.put("orgCd", orgCd);

        Map<String, Object> orgStruParent = (Map<String, Object>) this.ssmBatchClient.queryObject("getPreviousOrgStruFromParent", orgStruParentSearchMap);

        if (orgStruParent != null) {
            String prntOrgCd = (String) orgStruParent.get(NMAB240001Constant.COLUMN_NM_PRNT_ORG_CD);
            BigDecimal prntOrgLayerNum = convertFromTiertoLayer((String) orgStruParent.get(NMAB240001Constant.COLUMN_NM_ORG_TIER_CD));

            ORG_STRUTMsg prntTmsg = new ORG_STRUTMsg();
            ZYPEZDItemValueSetter.setValue(prntTmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prntTmsg.orgCd, prntOrgCd);
            ZYPEZDItemValueSetter.setValue(prntTmsg.orgLayerNum, prntOrgLayerNum);

            prntTmsg = (ORG_STRUTMsg) EZDTBLAccessor.findByKey(prntTmsg);
            if (prntTmsg != null) {
                EZDTMsg.copy(prntTmsg, null, orgStruTmsg, null);
            }
        }

        // Set self layer
        switch (orgLayerNum.intValue()) {
            case NMAB240001Constant.FIRST_LAYER:
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.firstOrgCd, dsOrgUnit.orgCd.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.firstOrgNm, dsOrgUnit.orgNm.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.firstOrgTierCd, dsOrgUnit.orgTierCd.getValue());
                break;

            case NMAB240001Constant.SECOND_LAYER:
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.scdOrgCd, dsOrgUnit.orgCd.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.scdOrgNm, dsOrgUnit.orgNm.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.scdOrgTierCd, dsOrgUnit.orgTierCd.getValue());
                break;

            case NMAB240001Constant.THIRD_LAYER:
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.thirdOrgCd, dsOrgUnit.orgCd.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.thirdOrgNm, dsOrgUnit.orgNm.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.thirdOrgTierCd, dsOrgUnit.orgTierCd.getValue());
                break;

            case NMAB240001Constant.FOURTH_LAYER:
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.frthOrgCd, dsOrgUnit.orgCd.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.frthOrgNm, dsOrgUnit.orgNm.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.frthOrgTierCd, dsOrgUnit.orgTierCd.getValue());
                break;

            case NMAB240001Constant.FIFTH_LAYER:
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.fifthOrgCd, dsOrgUnit.orgCd.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.fifthOrgNm, dsOrgUnit.orgNm.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.fifthOrgTierCd, dsOrgUnit.orgTierCd.getValue());
                break;

            case NMAB240001Constant.SIXTH_LAYER:
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.sixthOrgCd, dsOrgUnit.orgCd.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.sixthOrgNm, dsOrgUnit.orgNm.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.sixthOrgTierCd, dsOrgUnit.orgTierCd.getValue());
                break;

            case NMAB240001Constant.SEVENTH_LAYER:
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.svnthOrgCd, dsOrgUnit.orgCd.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.svnthOrgNm, dsOrgUnit.orgNm.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.svnthOrgTierCd, dsOrgUnit.orgTierCd.getValue());
                break;

            case NMAB240001Constant.EIGHTH_LAYER:
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.eighthOrgCd, dsOrgUnit.orgCd.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.eighthOrgNm, dsOrgUnit.orgNm.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.eighthOrgTierCd, dsOrgUnit.orgTierCd.getValue());
                break;

            case NMAB240001Constant.NINTH_LAYER:
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.ninthOrgCd, dsOrgUnit.orgCd.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.ninthOrgNm, dsOrgUnit.orgNm.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.ninthOrgTierCd, dsOrgUnit.orgTierCd.getValue());
                break;

            case NMAB240001Constant.TENTH_LAYER:
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.tenthOrgCd, dsOrgUnit.orgCd.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.tenthOrgNm, dsOrgUnit.orgNm.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.tenthOrgTierCd, dsOrgUnit.orgTierCd.getValue());
                break;

            case NMAB240001Constant.ELEVENTH_LAYER:
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.elvthOrgCd, dsOrgUnit.orgCd.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.elvthOrgNm, dsOrgUnit.orgNm.getValue());
                ZYPEZDItemValueSetter.setValue(orgStruTmsg.elvthOrgTierCd, dsOrgUnit.orgTierCd.getValue());
                break;

            default:
                break;
        }

        ZYPEZDItemValueSetter.setValue(orgStruTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(orgStruTmsg.orgCd, dsOrgUnit.orgCd.getValue());
        ZYPEZDItemValueSetter.setValue(orgStruTmsg.orgLayerNum, orgLayerNum);
        ZYPEZDItemValueSetter.setValue(orgStruTmsg.orgStruTpCd, dsOrgUnit.orgStruTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(orgStruTmsg.effFromDt, dsOrgUnit.effFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(orgStruTmsg.effThruDt, dsOrgUnit.effThruDt.getValue());

        S21FastTBLAccessor.insert(orgStruTmsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(orgStruTmsg.getReturnCode())) {
            this.totalErrCount++;
            super.rollback();
            throw new S21AbendException(NMAB240001Constant.MMAM0003E, new String[] {NMAB240001Constant.TBL_NM_ORG_STRU });
        }
    }

    /**
     * Physical Delete ORG_STRU
     * @param dsOrgUnit DS_ORG_UNITTMsg
     */
    private void deleteOrgStru(String orgCd, String orgTierCd, BigDecimal orgLayerNum) {
        if (ZYPCommonFunc.hasValue(orgTierCd) && !ZYPCommonFunc.hasValue(orgLayerNum)) {
            orgLayerNum = this.convertFromTiertoLayer(orgTierCd);
        }

        ORG_STRUTMsg deleteOrgStruMsg = new ORG_STRUTMsg();
        ZYPEZDItemValueSetter.setValue(deleteOrgStruMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(deleteOrgStruMsg.orgCd, orgCd);
        ZYPEZDItemValueSetter.setValue(deleteOrgStruMsg.orgLayerNum, orgLayerNum);

        deleteOrgStruMsg = (ORG_STRUTMsg) EZDTBLAccessor.findByKeyForUpdate(deleteOrgStruMsg);
        if (deleteOrgStruMsg != null) {
            int physicalDelOrgStruCount = S21FastTBLAccessor.removePhysical(new ORG_STRUTMsg[] {deleteOrgStruMsg });
            if (physicalDelOrgStruCount != 1) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(NMAB240001Constant.MMAM0005E, new String[] {NMAB240001Constant.TBL_NM_ORG_STRU });
            }
        }
    }

    /**
     * check if effective date is active or not.
     * @param effFromDt String
     * @param effThruDt String
     * @return True: Active, False: Inactive
     */
    private boolean checkActiveDate(String effFromDt, String effThruDt) {
        if (this.procDt.compareTo(effFromDt) >= 0) {
            if (ZYPCommonFunc.hasValue(effThruDt)) {
                if (this.procDt.compareTo(effThruDt) <= 0) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * update ORG_FUNC_ASG.GNRN_TP_CD
     * @param tocCd String
     * @param psnCd String
     * @param effFromDt String
     * @param gnrnTpCd String
     */
    private void updateOrgFuncAsg(String tocCd, String psnCd, String effFromDt, String gnrnTpCd) {
        ORG_FUNC_ASGTMsg tmsg = new ORG_FUNC_ASGTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.tocCd, tocCd);
        ZYPEZDItemValueSetter.setValue(tmsg.psnCd, psnCd);
        ZYPEZDItemValueSetter.setValue(tmsg.effFromDt, effFromDt);

        tmsg = (ORG_FUNC_ASGTMsg) EZDTBLAccessor.findByKeyForUpdate(tmsg);
        if (tmsg != null) {
            ZYPEZDItemValueSetter.setValue(tmsg.gnrnTpCd, gnrnTpCd);
            S21FastTBLAccessor.update(tmsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(NMAB240001Constant.MMAM0004E, new String[] {NMAB240001Constant.TBL_NM_ORG_FUNC_ASG });
            }
        }
    }

    /**
     * update ORG_TOC_CHNG_RQST.RGTN_STS_CD
     * @param tocCd String
     * @param orgChngRqstPk BigDecimal
     * @param rgtnstsCd String
     * @param targetRevenue Map<String, Object>
     */
    private void updateOrgTocChngRqst(String tocCd, BigDecimal orgChngRqstPk, String rgtnstsCd, Map<String, Object> targetRevenue) {
        ORG_TOC_CHNG_RQSTTMsg tmsg = new ORG_TOC_CHNG_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.tocCd, tocCd);
        ZYPEZDItemValueSetter.setValue(tmsg.orgChngRqstPk, orgChngRqstPk);

        tmsg = (ORG_TOC_CHNG_RQSTTMsg) EZDTBLAccessor.findByKeyForUpdate(tmsg);
        if (tmsg != null) {
            ZYPEZDItemValueSetter.setValue(tmsg.rgtnStsCd, rgtnstsCd);
            // 2016/06/20 CSA-QC#10424 Mod Start
            if (targetRevenue != null) {
                ZYPEZDItemValueSetter.setValue(tmsg.coaCmpyCd, (String) targetRevenue.get(NMAB240001Constant.COLUMN_NM_COA_CMPY_CD));
                ZYPEZDItemValueSetter.setValue(tmsg.coaBrCd, (String) targetRevenue.get(NMAB240001Constant.COLUMN_NM_COA_BR_CD));
                ZYPEZDItemValueSetter.setValue(tmsg.coaExtnCd, (String) targetRevenue.get(NMAB240001Constant.COLUMN_NM_COA_EXTN_CD));
                ZYPEZDItemValueSetter.setValue(tmsg.coaCcCd, (String) targetRevenue.get(NMAB240001Constant.COLUMN_NM_COA_CC_CD));
            }
            // 2016/06/20 CSA-QC#10424 Mod End
            S21FastTBLAccessor.update(tmsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(NMAB240001Constant.MMAM0004E, new String[] {NMAB240001Constant.TBL_NM_ORG_TOC_CHNG_RQST });
            }

            // 2016/06/20 CSA-QC#10424 Mod Start
            if (targetRevenue != null) {
                this.updateToc(tmsg);
            }
            // 2016/06/20 CSA-QC#10424 Mod End
        }

    }

    /**
     * Physical Delete ORG_TOC_CHNG_RQST
     * @param tocCd String
     * @param orgChngRqstPk BigDecimal
     * @param rgtnStsCd String
     */
    private void deleteOrgTocChngRqst(String tocCd, BigDecimal orgChngRqstPk, String rgtnStsCd) {
        ORG_TOC_CHNG_RQSTTMsg deleteOrgTocChngRqstMsg = new ORG_TOC_CHNG_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(deleteOrgTocChngRqstMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(deleteOrgTocChngRqstMsg.tocCd, tocCd);
        ZYPEZDItemValueSetter.setValue(deleteOrgTocChngRqstMsg.orgChngRqstPk, orgChngRqstPk);

        deleteOrgTocChngRqstMsg = (ORG_TOC_CHNG_RQSTTMsg) EZDTBLAccessor.findByKeyForUpdate(deleteOrgTocChngRqstMsg);
        if (deleteOrgTocChngRqstMsg != null) {
            ZYPEZDItemValueSetter.setValue(deleteOrgTocChngRqstMsg.rgtnStsCd, rgtnStsCd);

            S21FastTBLAccessor.update(deleteOrgTocChngRqstMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(deleteOrgTocChngRqstMsg.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(NMAB240001Constant.MMAM0004E, new String[] {NMAB240001Constant.TBL_NM_ORG_TOC_CHNG_RQST });
            }

            // QC#13847
            // this.deleteToc(deleteOrgTocChngRqstMsg);
        }
    }

    /**
     * copy ORG_TOC_CHNG_RQST to TOC
     * @param orgTocChngRqst ORG_TOC_CHNG_RQSTTMsg
     */
    private void updateToc(ORG_TOC_CHNG_RQSTTMsg orgTocChngRqst) {
        // 2016/02/09 S21-QC#4176 Mod Start
        Map<String, String> orgTocMap = new HashMap<String, String>();
        orgTocMap.put("glblCmpyCd", this.glblCmpyCd);
        orgTocMap.put("orgCd", orgTocChngRqst.orgCd.getValue());
        // 2017/07/26 QC#20013 Add Start
        orgTocMap.put("procDt", this.procDt);
        orgTocMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        orgTocMap.put("gnrnTpCd_2", GNRN_TP.CURRENT);
        orgTocMap.put("gnrnTpCd_3", GNRN_TP.FUTURE);
        orgTocMap.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        // 2017/07/26 QC#20013 Add End

        Map<String, Object> orgTocInfo = (Map<String, Object>) this.ssmBatchClient.queryObject("getOrgLayerNum", orgTocMap);
        // 2016/02/09 S21-QC#4176 Mod End

        TOCTMsg tmsg = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.tocCd, orgTocChngRqst.tocCd.getValue());

        tmsg = (TOCTMsg) EZDTBLAccessor.findByKeyForUpdate(tmsg);
        if (tmsg != null) {
            ZYPEZDItemValueSetter.setValue(tmsg.tocNm, orgTocChngRqst.tocNm.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.coaBrCd, orgTocChngRqst.coaBrCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.coaChCd, orgTocChngRqst.coaChCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.coaProdCd, orgTocChngRqst.coaProdCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.jobTtlCd, orgTocChngRqst.jobTtlCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.orgFuncRoleTpCd, orgTocChngRqst.orgFuncRoleTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.orgCd, orgTocChngRqst.orgCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.coaCmpyCd, orgTocChngRqst.coaCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.coaExtnCd, orgTocChngRqst.coaExtnCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.coaCcCd, orgTocChngRqst.coaCcCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.asgCustFromNm, orgTocChngRqst.asgCustFromNm.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.asgCustToNm, orgTocChngRqst.asgCustToNm.getValue());
            // 2016/02/09 S21-QC#4176 Mod Start
            if (orgTocInfo != null) {
                ZYPEZDItemValueSetter.setValue(tmsg.orgLayerNum, (BigDecimal) orgTocInfo.get(NMAB240001Constant.COLUMN_NM_ORG_LAYER_NUM));
            }
            // 2016/02/09 S21-QC#4176 Mod End

            S21FastTBLAccessor.update(tmsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(NMAB240001Constant.MMAM0004E, new String[] {NMAB240001Constant.TBL_NM_TOC });
            }
        } else {
            tmsg = new TOCTMsg();

            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tmsg.tocCd, orgTocChngRqst.tocCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.tocNm, orgTocChngRqst.tocNm.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.coaBrCd, orgTocChngRqst.coaBrCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.coaChCd, orgTocChngRqst.coaChCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.coaProdCd, orgTocChngRqst.coaProdCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.jobTtlCd, orgTocChngRqst.jobTtlCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.orgFuncRoleTpCd, orgTocChngRqst.orgFuncRoleTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.orgCd, orgTocChngRqst.orgCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.coaCmpyCd, orgTocChngRqst.coaCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.coaExtnCd, orgTocChngRqst.coaExtnCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.coaCcCd, orgTocChngRqst.coaCcCd.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.asgCustFromNm, orgTocChngRqst.asgCustFromNm.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.asgCustToNm, orgTocChngRqst.asgCustToNm.getValue());
            // 2016/02/09 S21-QC#4176 Mod Start
            if (orgTocInfo != null) {
                ZYPEZDItemValueSetter.setValue(tmsg.orgLayerNum, (BigDecimal) orgTocInfo.get(NMAB240001Constant.COLUMN_NM_ORG_LAYER_NUM));
            }
            // 2016/02/09 S21-QC#4176 Mod End

            S21FastTBLAccessor.insert(tmsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(NMAB240001Constant.MMAM0003E, new String[] {NMAB240001Constant.TBL_NM_TOC });
            }
        }
    }

    // QC#13847
    //    /**
    //     * Physical Delete TOC
    //     * @param orgTocChngRqst ORG_TOC_CHNG_RQSOTTMsg
    //     */
    //    private void deleteToc(ORG_TOC_CHNG_RQSTTMsg orgTocChngRqst) {
    //        TOCTMsg deleteTocMsg = new TOCTMsg();
    //        ZYPEZDItemValueSetter.setValue(deleteTocMsg.glblCmpyCd, this.glblCmpyCd);
    //        ZYPEZDItemValueSetter.setValue(deleteTocMsg.tocCd, orgTocChngRqst.tocCd.getValue());
    //
    //        deleteTocMsg = (TOCTMsg) EZDTBLAccessor.findByKeyForUpdate(deleteTocMsg);
    //        if (deleteTocMsg != null) {
    //            int physicalDelOrgStruCount = S21FastTBLAccessor.removePhysical(new TOCTMsg[] {deleteTocMsg });
    //            if (physicalDelOrgStruCount != 1) {
    //                this.totalErrCount++;
    //                super.rollback();
    //                throw new S21AbendException(NMAB240001Constant.MMAM0005E, new String[] {NMAB240001Constant.TBL_NM_TOC });
    //            }
    //        }
    //    }

    /**
     * Check if TOC is target to create TECH_MSTR
     * @param orgFuncRoleTpCd String
     */
    private boolean isCreateTechMaster(String orgFuncRoleTpCd) {
        // if (NMAB240001Constant.MODE_NIGHT.equals(this.mode)) {
        // return false;
        // }

        ORG_FUNC_ROLE_TPTMsg orgFuncRoleTp = new ORG_FUNC_ROLE_TPTMsg();
        ZYPEZDItemValueSetter.setValue(orgFuncRoleTp.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(orgFuncRoleTp.orgFuncRoleTpCd, orgFuncRoleTpCd);

        orgFuncRoleTp = (ORG_FUNC_ROLE_TPTMsg) EZDTBLAccessor.findByKey(orgFuncRoleTp);
        if (orgFuncRoleTp != null) {
            if (ZYPConstant.FLG_ON_Y.equals(orgFuncRoleTp.techMstrReqFlg.getValue())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Update TECH_MSTR using S21_PSN information
     * @param s21PsnTmsg S21_PSNTMsg
     */
    private void updateTechMstr(S21_PSNTMsg s21PsnTmsg) {
        TECH_MSTRTMsg techMstr = new TECH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(techMstr.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(techMstr.techTocCd, s21PsnTmsg.psnCd.getValue());

        techMstr = (TECH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdate(techMstr);
        if (techMstr != null) {

            ZYPEZDItemValueSetter.setValue(techMstr.techNm, getFullName(s21PsnTmsg));
            ZYPEZDItemValueSetter.setValue(techMstr.emlAddr, s21PsnTmsg.emlAddr.getValue());
            ZYPEZDItemValueSetter.setValue(techMstr.faxNum, s21PsnTmsg.faxNum.getValue());
            ZYPEZDItemValueSetter.setValue(techMstr.telNum, s21PsnTmsg.workTelNum.getValue());
            ZYPEZDItemValueSetter.setValue(techMstr.fldSvcMgrCd, s21PsnTmsg.hrSupvId.getValue());
            ZYPEZDItemValueSetter.setValue(techMstr.techCellPhoNum, s21PsnTmsg.cellTelNum.getValue());
            ZYPEZDItemValueSetter.setValue(techMstr.techTz, s21PsnTmsg.tmZoneCd.getValue());

            S21FastTBLAccessor.update(techMstr);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(techMstr.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(NMAB240001Constant.MMAM0004E, new String[] {NMAB240001Constant.TBL_NM_TECH_MSTR });
            }
        } else {
            techMstr = new TECH_MSTRTMsg();

            ZYPEZDItemValueSetter.setValue(techMstr.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(techMstr.techTocCd, s21PsnTmsg.psnCd.getValue());
            ZYPEZDItemValueSetter.setValue(techMstr.techNm, getFullName(s21PsnTmsg));
            ZYPEZDItemValueSetter.setValue(techMstr.emlAddr, s21PsnTmsg.emlAddr.getValue());
            ZYPEZDItemValueSetter.setValue(techMstr.faxNum, s21PsnTmsg.faxNum.getValue());
            ZYPEZDItemValueSetter.setValue(techMstr.telNum, s21PsnTmsg.workTelNum.getValue());
            ZYPEZDItemValueSetter.setValue(techMstr.fldSvcMgrCd, s21PsnTmsg.hrSupvId.getValue());
            ZYPEZDItemValueSetter.setValue(techMstr.techCellPhoNum, s21PsnTmsg.cellTelNum.getValue());
            ZYPEZDItemValueSetter.setValue(techMstr.techTz, s21PsnTmsg.tmZoneCd.getValue());

            S21FastTBLAccessor.insert(techMstr);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(techMstr.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(NMAB240001Constant.MMAM0003E, new String[] {NMAB240001Constant.TBL_NM_TECH_MSTR });
            }
        }
    }

    /**
     * Combine First and Last name as First, Last
     * @param s21PsnTmsg
     */
    private String getFullName(S21_PSNTMsg s21PsnTmsg) {
        // 2017/03/17 S21_NA#18072 Mod Start
        String fullName = ZYPCommonFunc.concatString(s21PsnTmsg.psnFirstNm.getValue(), ", ", s21PsnTmsg.psnLastNm.getValue());
        if (fullName.length() > NMAB240001Constant.TECH_NM_LENGTH) {
            fullName = S21StringUtil.subStringByLength(fullName, 0, NMAB240001Constant.TECH_NM_LENGTH);
        }
        //        return ZYPCommonFunc.concatString(s21PsnTmsg.psnFirstNm.getValue(), ", ", s21PsnTmsg.psnLastNm.getValue());
        return fullName;
        // 2017/03/17 S21_NA#18072 Mod End
    }

    /**
     * Check if TOC is target to create S21ORG
     * @param orgFuncRoleTpCd String
     */
    private boolean isTargetS21Org(String orgFuncRoleTpCd) {
        ORG_FUNC_ROLE_TPTMsg orgFuncRoleTp = new ORG_FUNC_ROLE_TPTMsg();
        ZYPEZDItemValueSetter.setValue(orgFuncRoleTp.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(orgFuncRoleTp.orgFuncRoleTpCd, orgFuncRoleTpCd);

        orgFuncRoleTp = (ORG_FUNC_ROLE_TPTMsg) EZDTBLAccessor.findByKey(orgFuncRoleTp);
        if (orgFuncRoleTp != null) {
            if (ZYPConstant.FLG_ON_Y.equals(orgFuncRoleTp.s21OrgReqFlg.getValue())) {
                return true;
            }
        }

        return false;
    }

    // QC#13847
    //    /**
    //     * Update/Insert S21ORG
    //     * @param tocTmsg TOCTMsg
    //     * @param dsOrgUnitTmsg DS_ORG_UNITTMsg
    //     */
    //    private void updateS21Org(TOCTMsg tocTmsg, DS_ORG_UNITTMsg dsOrgUnitTmsg) {
    //        ORG_STRUTMsg orgStruTmsg = new ORG_STRUTMsg();
    //        ZYPEZDItemValueSetter.setValue(orgStruTmsg.glblCmpyCd, this.glblCmpyCd);
    //        ZYPEZDItemValueSetter.setValue(orgStruTmsg.orgCd, dsOrgUnitTmsg.orgCd.getValue());
    //        ZYPEZDItemValueSetter.setValue(orgStruTmsg.orgLayerNum, this.convertFromTiertoLayer(dsOrgUnitTmsg.orgTierCd.getValue()));
    //
    //        orgStruTmsg = (ORG_STRUTMsg) EZDTBLAccessor.findByKey(orgStruTmsg);
    //        if (orgStruTmsg == null) {
    //            return;
    //        }
    //
    //        S21_ORGTMsg s21orgTmsg = new S21_ORGTMsg();
    //        ZYPEZDItemValueSetter.setValue(s21orgTmsg.glblCmpyCd, this.glblCmpyCd);
    //        ZYPEZDItemValueSetter.setValue(s21orgTmsg.tocCd, tocTmsg.tocCd.getValue());
    //
    //        s21orgTmsg = (S21_ORGTMsg) EZDTBLAccessor.findByKeyForUpdate(s21orgTmsg);
    //
    //        if (s21orgTmsg != null) {
    //            if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(s21orgTmsg.rgtnStsCd.getValue())) {
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.firstOrgCd, orgStruTmsg.firstOrgCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.firstOrgNm, orgStruTmsg.firstOrgNm.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.scdOrgCd, orgStruTmsg.scdOrgCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.scdOrgNm, orgStruTmsg.scdOrgNm.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.thirdOrgCd, orgStruTmsg.thirdOrgCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.thirdOrgNm, orgStruTmsg.thirdOrgNm.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.frthOrgCd, orgStruTmsg.frthOrgCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.frthOrgNm, orgStruTmsg.frthOrgNm.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.fifthOrgCd, orgStruTmsg.fifthOrgCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.fifthOrgNm, orgStruTmsg.fifthOrgNm.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.sixthOrgCd, orgStruTmsg.sixthOrgCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.sixthOrgNm, orgStruTmsg.sixthOrgNm.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.svnthOrgCd, orgStruTmsg.svnthOrgCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.svnthOrgNm, orgStruTmsg.svnthOrgNm.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.eighthOrgCd, orgStruTmsg.eighthOrgCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.eighthOrgNm, orgStruTmsg.eighthOrgNm.getValue());
    //
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.tocNm, tocTmsg.tocNm.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.coaBrCd, tocTmsg.coaBrCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.coaCcCd, tocTmsg.coaCcCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.coaProdCd, tocTmsg.coaProdCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.jobTtlCd, tocTmsg.jobTtlCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.uniqOrgLayerNum, tocTmsg.orgLayerNum.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.orgFuncRoleTpCd, tocTmsg.orgFuncRoleTpCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.coaChCd, tocTmsg.coaChCd.getValue());
    //
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.ninthOrgCd, orgStruTmsg.ninthOrgCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.ninthOrgNm, orgStruTmsg.ninthOrgNm.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.tenthOrgCd, orgStruTmsg.tenthOrgCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.tenthOrgNm, orgStruTmsg.tenthOrgNm.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.elvthOrgCd, orgStruTmsg.elvthOrgCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.elvthOrgNm, orgStruTmsg.elvthOrgNm.getValue());
    //
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.firstOrgTierCd, orgStruTmsg.firstOrgTierCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.scdOrgTierCd, orgStruTmsg.scdOrgTierCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.thirdOrgTierCd, orgStruTmsg.thirdOrgTierCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.frthOrgTierCd, orgStruTmsg.frthOrgTierCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.fifthOrgTierCd, orgStruTmsg.fifthOrgTierCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.sixthOrgTierCd, orgStruTmsg.sixthOrgTierCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.svnthOrgTierCd, orgStruTmsg.svnthOrgTierCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.eighthOrgTierCd, orgStruTmsg.eighthOrgTierCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.ninthOrgTierCd, orgStruTmsg.ninthOrgTierCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.tenthOrgTierCd, orgStruTmsg.tenthOrgTierCd.getValue());
    //                ZYPEZDItemValueSetter.setValue(s21orgTmsg.elvthOrgTierCd, orgStruTmsg.elvthOrgTierCd.getValue());
    //
    //                S21FastTBLAccessor.update(s21orgTmsg);
    //                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(s21orgTmsg.getReturnCode())) {
    //                    this.totalErrCount++;
    //                    super.rollback();
    //                    throw new S21AbendException(NMAB240001Constant.MMAM0004E, new String[] {NMAB240001Constant.TBL_NM_S21_ORG });
    //                }
    //            }
    //        } else {
    //            s21orgTmsg = new S21_ORGTMsg();
    //
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.glblCmpyCd, this.glblCmpyCd);
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.tocCd, tocTmsg.tocCd.getValue());
    //
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.firstOrgCd, orgStruTmsg.firstOrgCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.firstOrgNm, orgStruTmsg.firstOrgNm.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.scdOrgCd, orgStruTmsg.scdOrgCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.scdOrgNm, orgStruTmsg.scdOrgNm.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.thirdOrgCd, orgStruTmsg.thirdOrgCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.thirdOrgNm, orgStruTmsg.thirdOrgNm.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.frthOrgCd, orgStruTmsg.frthOrgCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.frthOrgNm, orgStruTmsg.frthOrgNm.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.fifthOrgCd, orgStruTmsg.fifthOrgCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.fifthOrgNm, orgStruTmsg.fifthOrgNm.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.sixthOrgCd, orgStruTmsg.sixthOrgCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.sixthOrgNm, orgStruTmsg.sixthOrgNm.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.svnthOrgCd, orgStruTmsg.svnthOrgCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.svnthOrgNm, orgStruTmsg.svnthOrgNm.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.eighthOrgCd, orgStruTmsg.eighthOrgCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.eighthOrgNm, orgStruTmsg.eighthOrgNm.getValue());
    //
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.tocNm, tocTmsg.tocNm.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.coaBrCd, tocTmsg.coaBrCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.coaCcCd, tocTmsg.coaCcCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.coaProdCd, tocTmsg.coaProdCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.jobTtlCd, tocTmsg.jobTtlCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.uniqOrgLayerNum, tocTmsg.orgLayerNum.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.orgFuncRoleTpCd, tocTmsg.orgFuncRoleTpCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.coaChCd, tocTmsg.coaChCd.getValue());
    //
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.ninthOrgCd, orgStruTmsg.ninthOrgCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.ninthOrgNm, orgStruTmsg.ninthOrgNm.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.tenthOrgCd, orgStruTmsg.tenthOrgCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.tenthOrgNm, orgStruTmsg.tenthOrgNm.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.elvthOrgCd, orgStruTmsg.elvthOrgCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.elvthOrgNm, orgStruTmsg.elvthOrgNm.getValue());
    //
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.firstOrgTierCd, orgStruTmsg.firstOrgTierCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.scdOrgTierCd, orgStruTmsg.scdOrgTierCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.thirdOrgTierCd, orgStruTmsg.thirdOrgTierCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.frthOrgTierCd, orgStruTmsg.frthOrgTierCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.fifthOrgTierCd, orgStruTmsg.fifthOrgTierCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.sixthOrgTierCd, orgStruTmsg.sixthOrgTierCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.svnthOrgTierCd, orgStruTmsg.svnthOrgTierCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.eighthOrgTierCd, orgStruTmsg.eighthOrgTierCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.ninthOrgTierCd, orgStruTmsg.ninthOrgTierCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.tenthOrgTierCd, orgStruTmsg.tenthOrgTierCd.getValue());
    //            ZYPEZDItemValueSetter.setValue(s21orgTmsg.elvthOrgTierCd, orgStruTmsg.elvthOrgTierCd.getValue());
    //
    //            S21FastTBLAccessor.insert(s21orgTmsg);
    //            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(s21orgTmsg.getReturnCode())) {
    //                this.totalErrCount++;
    //                super.rollback();
    //                throw new S21AbendException(NMAB240001Constant.MMAM0003E, new String[] {NMAB240001Constant.TBL_NM_S21_ORG });
    //            }
    //        }
    //    }

    /**
     * Logical Delete S21ORG
     * @param tocCd String
     * @param rgtnstsCd String set P99(Terminated)
     */
    private void deleteS21Org(String tocCd, String rgtnstsCd) {
        S21_ORGTMsg s21OrgTmsg = new S21_ORGTMsg();
        ZYPEZDItemValueSetter.setValue(s21OrgTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(s21OrgTmsg.tocCd, tocCd);

        s21OrgTmsg = (S21_ORGTMsg) EZDTBLAccessor.findByKeyForUpdate(s21OrgTmsg);
        if (s21OrgTmsg != null) {
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.rgtnStsCd, rgtnstsCd);

            S21FastTBLAccessor.update(s21OrgTmsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(s21OrgTmsg.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(NMAB240001Constant.MMAM0004E, new String[] {NMAB240001Constant.TBL_NM_S21_ORG });
            }

        }
    }

    /**
     * Physical Delete ORG_STRU
     * @param tocCd String
     */
    private void physicalDeleteS21Org(String tocCd) {

        S21_ORGTMsg s21OrgTmsg = new S21_ORGTMsg();
        ZYPEZDItemValueSetter.setValue(s21OrgTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(s21OrgTmsg.tocCd, tocCd);

        s21OrgTmsg = (S21_ORGTMsg) EZDTBLAccessor.findByKeyForUpdate(s21OrgTmsg);
        if (s21OrgTmsg != null) {
            int physicalDelS21OrgCount = S21FastTBLAccessor.removePhysical(new S21_ORGTMsg[] {s21OrgTmsg });
            if (physicalDelS21OrgCount != 1) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(NMAB240001Constant.MMAM0005E, new String[] {NMAB240001Constant.TBL_NM_S21_ORG });
            }
        }
    }

    /**
     * Determine if TOC_ORG_MGR_INFO should be created or not. It's
     * set in Var Char Const Table 0: Not Process/ 1: Process
     */
    private boolean isProcessTocOrgMgrInfo() {
        VAR_CHAR_CONSTTMsg varCharConstTmsg = new VAR_CHAR_CONSTTMsg();
        ZYPEZDItemValueSetter.setValue(varCharConstTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(varCharConstTmsg.varCharConstNm, ZYPCommonFunc.concatString(NMAB240001Constant.BIZ_ID, "_", NMAB240001Constant.TBL_NM_TOC_ORG_MGR_INFO));

        varCharConstTmsg = (VAR_CHAR_CONSTTMsg) EZDTBLAccessor.findByKey(varCharConstTmsg);
        if (varCharConstTmsg != null) {
            if (ZYPConstant.FLG_ON_1.equals(varCharConstTmsg.varCharConstVal.getValue())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determine if Process date is first biz day or not. When today
     * is first day, TOC_ORG_MGR_INFO will be created.
     */
    private boolean isFirstBizDay() {
        String batchProcessDateYYYY = this.procDt.substring(0, NMAB240001Constant.INDEX_4);
        String batchProcessDateMM = this.procDt.substring(NMAB240001Constant.INDEX_4, NMAB240001Constant.INDEX_6);
        String startOfMonthBusinessDate = ZYPDateUtil.getStartOfMonthBusinessDay(glblCmpyCd, batchProcessDateYYYY, batchProcessDateMM);
        if (startOfMonthBusinessDate.equals(this.procDt)) {
            return true;
        }

        return false;
    }

    /**
     * Delete TOC_ORG_MGR_INFO, It's physical delete from Database.
     * Target Record is Effective From date is this month.
     */
    private void deleteTocOrgMgrInfo() {
        Map<String, String> tocOrgMgrInfoThisMonthMap = new HashMap<String, String>();
        tocOrgMgrInfoThisMonthMap.put("glblCmpyCd", this.glblCmpyCd);
        tocOrgMgrInfoThisMonthMap.put("procDtYyyyMm", S21StringUtil.subStringByLength(this.procDt, 0, NMAB240001Constant.YYYYMM));
        tocOrgMgrInfoThisMonthMap.put("cutLength", String.valueOf(NMAB240001Constant.YYYYMM));

        List<Map<String, Object>> tocOrgMgrInfoThisMonthList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTocOrgMgrInfoThisMonthList", tocOrgMgrInfoThisMonthMap);

        for (Map<String, Object> tocOrgMgrInfoThisMonthListListMap : tocOrgMgrInfoThisMonthList) {
            TOC_ORG_MGR_INFOTMsg deleteTocOrgMgrInfoMsg = new TOC_ORG_MGR_INFOTMsg();
            ZYPEZDItemValueSetter.setValue(deleteTocOrgMgrInfoMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(deleteTocOrgMgrInfoMsg.orgStruTpCd, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
            ZYPEZDItemValueSetter.setValue(deleteTocOrgMgrInfoMsg.psnCd, (String) tocOrgMgrInfoThisMonthListListMap.get(NMAB240001Constant.COLUMN_NM_PSN_CD));
            ZYPEZDItemValueSetter.setValue(deleteTocOrgMgrInfoMsg.tocCd, (String) tocOrgMgrInfoThisMonthListListMap.get(NMAB240001Constant.COLUMN_NM_TOC_CD));
            ZYPEZDItemValueSetter.setValue(deleteTocOrgMgrInfoMsg.effFromDt, (String) tocOrgMgrInfoThisMonthListListMap.get(NMAB240001Constant.COLUMN_NM_EFF_FROM_DT));

            deleteTocOrgMgrInfoMsg = (TOC_ORG_MGR_INFOTMsg) EZDTBLAccessor.findByKeyForUpdate(deleteTocOrgMgrInfoMsg);
            if (deleteTocOrgMgrInfoMsg != null) {
                int physicalDelOrgStruCount = S21FastTBLAccessor.removePhysical(new TOC_ORG_MGR_INFOTMsg[] {deleteTocOrgMgrInfoMsg });
                if (physicalDelOrgStruCount != 1) {
                    this.totalErrCount++;
                    super.rollback();
                    throw new S21AbendException(NMAB240001Constant.MMAM0005E, new String[] {NMAB240001Constant.TBL_NM_TOC_ORG_MGR_INFO });
                }
            }
        }

    }

    /**
     * Create TOC_ORG_MGR_INFO
     */
    private void createTocOrgMgrInfo() {
        Map<String, String> tocOrgMgrInfoMap = new HashMap<String, String>();
        tocOrgMgrInfoMap.put("glblCmpyCd", this.glblCmpyCd);
        tocOrgMgrInfoMap.put("procDt", this.procDt);
        tocOrgMgrInfoMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        tocOrgMgrInfoMap.put("gnrnTpCd_2", GNRN_TP.CURRENT);
        tocOrgMgrInfoMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        tocOrgMgrInfoMap.put("procDtYyyyMm", S21StringUtil.subStringByLength(this.procDt, 0, NMAB240001Constant.YYYYMM));
        tocOrgMgrInfoMap.put("cutLength", String.valueOf(NMAB240001Constant.YYYYMM));
        tocOrgMgrInfoMap.put("procCd_Batch", NMAB240001Constant.PARAM_BATCH_B);

        List<Map<String, Object>> tocOrgMgrInfoList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTocOrgMgrInfoList", tocOrgMgrInfoMap);

        for (Map<String, Object> tocOrgMgrInfoListMap : tocOrgMgrInfoList) {
            TOC_ORG_MGR_INFOTMsg tocOrgMgrInfoTmsg = new TOC_ORG_MGR_INFOTMsg();

            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.orgStruTpCd, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.psnCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_PSN_CD));
            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tocCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_TOC_CD));
            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.effFromDt, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_START_DT));
            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.effThruDt, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_END_DT));
            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.psnFirstNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_PSN_FIRST_NM));
            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.psnLastNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_PSN_LAST_NM));
            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.orgFuncRoleTpCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_ORG_FUNC_ROLE_TP_CD));
            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.orgLayerNum, (BigDecimal) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_ORG_LAYER_NUM));

            // 2016/04/13 CSA-QC#2966 Mod Start
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.firstOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FIRST_ORG_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.firstOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FIRST_ORG_NM));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.scdOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SCD_ORG_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.scdOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SCD_ORG_NM));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.thirdOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_THIRD_ORG_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.thirdOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_THIRD_ORG_NM));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.frthOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FRTH_ORG_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.frthOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FRTH_ORG_NM));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.fifthOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FIFTH_ORG_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.fifthOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FIFTH_ORG_NM));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.sixthOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SIXTH_ORG_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.sixthOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SIXTH_ORG_NM));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.svnthOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SVNTH_ORG_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.svnthOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SVNTH_ORG_NM));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.eighthOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_EIGHTH_ORG_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.eighthOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_EIGHTH_ORG_NM));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.ninthOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_NINTH_ORG_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.ninthOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_NINTH_ORG_NM));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tenthOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_TENTH_ORG_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tenthOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_TENTH_ORG_NM));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.elvthOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_ELVTH_ORG_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.elvthOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_ELVTH_ORG_NM));
            // 2016/04/13 CSA-QC#2966 Mod End

            setOrgInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoListMap, NMAB240001Constant.FIRST_LAYER);
            setOrgInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoListMap, NMAB240001Constant.SECOND_LAYER);
            setOrgInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoListMap, NMAB240001Constant.THIRD_LAYER);
            setOrgInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoListMap, NMAB240001Constant.FOURTH_LAYER);
            setOrgInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoListMap, NMAB240001Constant.FIFTH_LAYER);
            setOrgInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoListMap, NMAB240001Constant.SIXTH_LAYER);
            setOrgInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoListMap, NMAB240001Constant.SEVENTH_LAYER);
            setOrgInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoListMap, NMAB240001Constant.EIGHTH_LAYER);
            setOrgInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoListMap, NMAB240001Constant.NINTH_LAYER);
            setOrgInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoListMap, NMAB240001Constant.TENTH_LAYER);
            setOrgInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoListMap, NMAB240001Constant.ELEVENTH_LAYER);

            // Get Manager Person Info
            if (ZYPCommonFunc.hasValue(tocOrgMgrInfoTmsg.firstOrgCd)) {
                setMgrPersonInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoTmsg.firstOrgCd.getValue(), NMAB240001Constant.FIRST_LAYER);
            }

            if (ZYPCommonFunc.hasValue(tocOrgMgrInfoTmsg.scdOrgCd)) {
                setMgrPersonInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoTmsg.scdOrgCd.getValue(), NMAB240001Constant.SECOND_LAYER);
            }

            if (ZYPCommonFunc.hasValue(tocOrgMgrInfoTmsg.thirdOrgCd)) {
                setMgrPersonInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoTmsg.thirdOrgCd.getValue(), NMAB240001Constant.THIRD_LAYER);
            }

            if (ZYPCommonFunc.hasValue(tocOrgMgrInfoTmsg.frthOrgCd)) {
                setMgrPersonInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoTmsg.frthOrgCd.getValue(), NMAB240001Constant.FOURTH_LAYER);
            }

            if (ZYPCommonFunc.hasValue(tocOrgMgrInfoTmsg.fifthOrgCd)) {
                setMgrPersonInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoTmsg.fifthOrgCd.getValue(), NMAB240001Constant.FIFTH_LAYER);
            }

            if (ZYPCommonFunc.hasValue(tocOrgMgrInfoTmsg.sixthOrgCd)) {
                setMgrPersonInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoTmsg.sixthOrgCd.getValue(), NMAB240001Constant.SIXTH_LAYER);
            }

            if (ZYPCommonFunc.hasValue(tocOrgMgrInfoTmsg.svnthOrgCd)) {
                setMgrPersonInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoTmsg.svnthOrgCd.getValue(), NMAB240001Constant.SEVENTH_LAYER);
            }

            if (ZYPCommonFunc.hasValue(tocOrgMgrInfoTmsg.eighthOrgCd)) {
                setMgrPersonInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoTmsg.eighthOrgCd.getValue(), NMAB240001Constant.EIGHTH_LAYER);
            }

            if (ZYPCommonFunc.hasValue(tocOrgMgrInfoTmsg.ninthOrgCd)) {
                setMgrPersonInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoTmsg.ninthOrgCd.getValue(), NMAB240001Constant.NINTH_LAYER);
            }

            if (ZYPCommonFunc.hasValue(tocOrgMgrInfoTmsg.tenthOrgCd)) {
                setMgrPersonInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoTmsg.tenthOrgCd.getValue(), NMAB240001Constant.TENTH_LAYER);
            }

            if (ZYPCommonFunc.hasValue(tocOrgMgrInfoTmsg.elvthOrgCd)) {
                setMgrPersonInfo(tocOrgMgrInfoTmsg, tocOrgMgrInfoTmsg.elvthOrgCd.getValue(), NMAB240001Constant.ELEVENTH_LAYER);
            }

            // 2016/04/13 CSA-QC#2966 Mod Start
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.firstOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FIRST_ORG_TIER_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.scdOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SCD_ORG_TIER_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.thirdOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_THIRD_ORG_TIER_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.frthOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FRTH_ORG_TIER_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.fifthOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FIFTH_ORG_TIER_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.sixthOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SIXTH_ORG_TIER_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.svnthOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SVNTH_ORG_TIER_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.eighthOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_EIGHTH_ORG_TIER_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.ninthOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_NINTH_ORG_TIER_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tenthOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_TENTH_ORG_TIER_CD));
            //            ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.elvthOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_ELVTH_ORG_TIER_CD));
            // 2016/04/13 CSA-QC#2966 Mod Start

            S21FastTBLAccessor.insert(tocOrgMgrInfoTmsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tocOrgMgrInfoTmsg.getReturnCode())) {
                S21InfoLogOutput.println("MMAM0003E", new String[] {NMAB240001Constant.TBL_NM_TOC_ORG_MGR_INFO + "] [<KEY> TOC_CD:" + tocOrgMgrInfoTmsg.tocCd.getValue() + " Return Code:" + tocOrgMgrInfoTmsg.getReturnCode() });
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(NMAB240001Constant.MMAM0003E, new String[] {NMAB240001Constant.TBL_NM_TOC_ORG_MGR_INFO });
            }
        }
    }

    // S21_NA#19686 ADD START 
    /**
     * @param tocOrgMgrInfoTmsg
     * @param tocOrgMgrInfoListMap
     * @param layerNum
     */
    private void setOrgInfo(TOC_ORG_MGR_INFOTMsg tocOrgMgrInfoTmsg, Map<String, Object> tocOrgMgrInfoListMap, int layerNum) {

        switch (layerNum) {
            case NMAB240001Constant.FIRST_LAYER:
                ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.firstOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FIRST_ORG_CD));
                ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.firstOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FIRST_ORG_NM));
                ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.firstOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FIRST_ORG_TIER_CD));
                break;

            case NMAB240001Constant.SECOND_LAYER:
                if (ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SCD_ORG_CD))) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.scdOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SCD_ORG_CD));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.scdOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SCD_ORG_NM));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.scdOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SCD_ORG_TIER_CD));
                } else if (hasValue(tocOrgMgrInfoListMap, layerNum)) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.scdOrgCd, tocOrgMgrInfoTmsg.firstOrgCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.scdOrgNm, tocOrgMgrInfoTmsg.firstOrgNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.scdOrgTierCd, tocOrgMgrInfoTmsg.firstOrgTierCd);
                }
                break;
            case NMAB240001Constant.THIRD_LAYER:
                if (ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_THIRD_ORG_CD))) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.thirdOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_THIRD_ORG_CD));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.thirdOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_THIRD_ORG_NM));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.thirdOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_THIRD_ORG_TIER_CD));
                } else if (hasValue(tocOrgMgrInfoListMap, layerNum)) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.thirdOrgCd, tocOrgMgrInfoTmsg.scdOrgCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.thirdOrgNm, tocOrgMgrInfoTmsg.scdOrgNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.thirdOrgTierCd, tocOrgMgrInfoTmsg.scdOrgTierCd);
                }
                break;

            case NMAB240001Constant.FOURTH_LAYER:
                if (ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FRTH_ORG_CD))) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.frthOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FRTH_ORG_CD));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.frthOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FRTH_ORG_NM));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.frthOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FRTH_ORG_TIER_CD));
                } else if (hasValue(tocOrgMgrInfoListMap, layerNum)) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.frthOrgCd, tocOrgMgrInfoTmsg.thirdOrgCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.frthOrgNm, tocOrgMgrInfoTmsg.thirdOrgNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.frthOrgTierCd, tocOrgMgrInfoTmsg.thirdOrgTierCd);
                }
                break;

            case NMAB240001Constant.FIFTH_LAYER:
                if (ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FIFTH_ORG_CD))) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.fifthOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FIFTH_ORG_CD));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.fifthOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FIFTH_ORG_NM));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.fifthOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FIFTH_ORG_TIER_CD));
                } else if (hasValue(tocOrgMgrInfoListMap, layerNum)) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.fifthOrgCd, tocOrgMgrInfoTmsg.frthOrgCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.fifthOrgNm, tocOrgMgrInfoTmsg.frthOrgNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.fifthOrgTierCd, tocOrgMgrInfoTmsg.frthOrgTierCd);
                }
                break;

            case NMAB240001Constant.SIXTH_LAYER:
                if (ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SIXTH_ORG_CD))) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.sixthOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SIXTH_ORG_CD));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.sixthOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SIXTH_ORG_NM));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.sixthOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SIXTH_ORG_TIER_CD));
                } else if (hasValue(tocOrgMgrInfoListMap, layerNum)) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.sixthOrgCd, tocOrgMgrInfoTmsg.fifthOrgCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.sixthOrgNm, tocOrgMgrInfoTmsg.fifthOrgNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.sixthOrgTierCd, tocOrgMgrInfoTmsg.fifthOrgTierCd);
                }
                break;

            case NMAB240001Constant.SEVENTH_LAYER:
                if (ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SVNTH_ORG_CD))) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.svnthOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SVNTH_ORG_CD));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.svnthOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SVNTH_ORG_NM));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.svnthOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SVNTH_ORG_TIER_CD));
                } else if (hasValue(tocOrgMgrInfoListMap, layerNum)) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.svnthOrgCd, tocOrgMgrInfoTmsg.sixthOrgCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.svnthOrgNm, tocOrgMgrInfoTmsg.sixthOrgNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.svnthOrgTierCd, tocOrgMgrInfoTmsg.sixthOrgTierCd);
                }
                break;

            case NMAB240001Constant.EIGHTH_LAYER:
                if (ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_EIGHTH_ORG_CD))) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.eighthOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_EIGHTH_ORG_CD));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.eighthOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_EIGHTH_ORG_NM));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.eighthOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_EIGHTH_ORG_TIER_CD));
                } else if (hasValue(tocOrgMgrInfoListMap, layerNum)) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.eighthOrgCd, tocOrgMgrInfoTmsg.svnthOrgCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.eighthOrgNm, tocOrgMgrInfoTmsg.svnthOrgNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.eighthOrgTierCd, tocOrgMgrInfoTmsg.svnthOrgTierCd);
                }
                break;

            case NMAB240001Constant.NINTH_LAYER:
                if (ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_NINTH_ORG_CD))) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.ninthOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_NINTH_ORG_CD));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.ninthOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_NINTH_ORG_NM));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.ninthOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_NINTH_ORG_TIER_CD));
                } else if (hasValue(tocOrgMgrInfoListMap, layerNum)) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.ninthOrgCd, tocOrgMgrInfoTmsg.eighthOrgCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.ninthOrgNm, tocOrgMgrInfoTmsg.eighthOrgNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.ninthOrgTierCd, tocOrgMgrInfoTmsg.eighthOrgTierCd);
                }
                break;

            case NMAB240001Constant.TENTH_LAYER:
                if (ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_TENTH_ORG_CD))) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tenthOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_TENTH_ORG_CD));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tenthOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_TENTH_ORG_NM));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tenthOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_TENTH_ORG_TIER_CD));
                } else if (hasValue(tocOrgMgrInfoListMap, layerNum)) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tenthOrgCd, tocOrgMgrInfoTmsg.ninthOrgCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tenthOrgNm, tocOrgMgrInfoTmsg.ninthOrgNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tenthOrgTierCd, tocOrgMgrInfoTmsg.ninthOrgTierCd);
                }

                break;

            case NMAB240001Constant.ELEVENTH_LAYER:
                if (ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_ELVTH_ORG_CD))) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.elvthOrgCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_ELVTH_ORG_CD));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.elvthOrgNm, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_ELVTH_ORG_NM));
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.elvthOrgTierCd, (String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_ELVTH_ORG_TIER_CD));
                }
                break;

            default:
                break;
        }
    }

    /**
     * @param tocOrgMgrInfoListMap
     * @param layerNum
     * @return
     */
    private boolean hasValue(Map<String, Object> tocOrgMgrInfoListMap, int layerNum) {

        boolean thirdValue = ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_THIRD_ORG_CD));
        boolean frthValue = ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FRTH_ORG_CD));
        boolean fifthValue = ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_FIFTH_ORG_CD));
        boolean sixthValue = ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SIXTH_ORG_CD));
        boolean svnthValue = ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_SVNTH_ORG_CD));
        boolean eighthValue = ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_EIGHTH_ORG_CD));
        boolean ninthValue = ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_NINTH_ORG_CD));
        boolean tenthValue = ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_TENTH_ORG_CD));
        boolean elvthValue = ZYPCommonFunc.hasValue((String) tocOrgMgrInfoListMap.get(NMAB240001Constant.COLUMN_NM_ELVTH_ORG_CD));

        switch (layerNum) {
            case NMAB240001Constant.SECOND_LAYER:
                if (thirdValue || frthValue || fifthValue || sixthValue || svnthValue || eighthValue || ninthValue || tenthValue || elvthValue) {
                    return true;
                }
                return false;

            case NMAB240001Constant.THIRD_LAYER:
                if (frthValue || fifthValue || sixthValue || svnthValue || eighthValue || ninthValue || tenthValue || elvthValue) {
                    return true;
                }
                return false;

            case NMAB240001Constant.FOURTH_LAYER:
                if (fifthValue || sixthValue || svnthValue || eighthValue || ninthValue || tenthValue || elvthValue) {
                    return true;
                }
                return false;

            case NMAB240001Constant.FIFTH_LAYER:
                if (sixthValue || svnthValue || eighthValue || ninthValue || tenthValue || elvthValue) {
                    return true;
                }
                return false;

            case NMAB240001Constant.SIXTH_LAYER:
                if (svnthValue || eighthValue || ninthValue || tenthValue || elvthValue) {
                    return true;
                }
                return false;

            case NMAB240001Constant.SEVENTH_LAYER:
                if (eighthValue || ninthValue || tenthValue || elvthValue) {
                    return true;
                }
                return false;

            case NMAB240001Constant.EIGHTH_LAYER:
                if (ninthValue || tenthValue || elvthValue) {
                    return true;
                }
                return false;

            case NMAB240001Constant.NINTH_LAYER:
                if (tenthValue || elvthValue) {
                    return true;
                }
                return false;

            case NMAB240001Constant.TENTH_LAYER:
                if (elvthValue) {
                    return true;
                }
                return false;

            default:
                break;
        }
        return false;
    }

    // S21_NA#19686 ADD END 

    /**
     * Set Manager Information in target organization, When the
     * organization has several managers, not set manager information.
     * @param tocOrgMgrInfoTmsg TOC_ORG_MGR_INFOTMsg
     * @param orgCd String
     * @param orgLayerNum BigDecimal
     */
    private void setMgrPersonInfo(TOC_ORG_MGR_INFOTMsg tocOrgMgrInfoTmsg, String orgCd, int layerNum) {
        Map<String, String> mgrPersonMap = new HashMap<String, String>();
        mgrPersonMap.put("glblCmpyCd", this.glblCmpyCd);
        mgrPersonMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        mgrPersonMap.put("orgCd", orgCd);
        mgrPersonMap.put("procDt", this.procDt);
        mgrPersonMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        mgrPersonMap.put("gnrnTpCd_2", GNRN_TP.CURRENT);

        List<Map<String, Object>> mgrPersonInfoList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getMgrPersonInfoList", mgrPersonMap);

        String psnCd = null;
        String psnFirstNm = null;
        String psnLastNm = null;
        String tocCd = null;

        // S21_NA#19686 MOD START 
        boolean dataEmptyFlag = false;

        if (mgrPersonInfoList != null && mgrPersonInfoList.size() == 1) {
            psnCd = (String) mgrPersonInfoList.get(0).get(NMAB240001Constant.COLUMN_NM_PSN_CD);
            psnFirstNm = (String) mgrPersonInfoList.get(0).get(NMAB240001Constant.COLUMN_NM_PSN_FIRST_NM);
            psnLastNm = (String) mgrPersonInfoList.get(0).get(NMAB240001Constant.COLUMN_NM_PSN_LAST_NM);
            tocCd = (String) mgrPersonInfoList.get(0).get(NMAB240001Constant.COLUMN_NM_TOC_CD);
        } else {
            dataEmptyFlag = true;
        }

        switch (layerNum) {
            case NMAB240001Constant.FIRST_LAYER:
                ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.firstOrgMgrPsnCd, psnCd);
                ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.firstOrgMgrFirstNm, psnFirstNm);
                ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.firstOrgMgrLastNm, psnLastNm);
                ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.firstOrgMgrTocCd, tocCd);
                break;

            case NMAB240001Constant.SECOND_LAYER:
                ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.scdOrgMgrPsnCd, psnCd);
                ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.scdOrgMgrFirstNm, psnFirstNm);
                ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.scdOrgMgrLastNm, psnLastNm);
                ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.scdOrgMgrTocCd, tocCd);
                break;

            case NMAB240001Constant.THIRD_LAYER:
                if (dataEmptyFlag) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.thirdOrgMgrPsnCd, tocOrgMgrInfoTmsg.scdOrgMgrPsnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.thirdOrgMgrFirstNm, tocOrgMgrInfoTmsg.scdOrgMgrFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.thirdOrgMgrLastNm, tocOrgMgrInfoTmsg.scdOrgMgrLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.thirdOrgMgrTocCd, tocOrgMgrInfoTmsg.scdOrgMgrTocCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.thirdOrgMgrPsnCd, psnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.thirdOrgMgrFirstNm, psnFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.thirdOrgMgrLastNm, psnLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.thirdOrgMgrTocCd, tocCd);
                }
                break;

            case NMAB240001Constant.FOURTH_LAYER:
                if (dataEmptyFlag) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.frthOrgMgrPsnCd, tocOrgMgrInfoTmsg.thirdOrgMgrPsnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.frthOrgMgrFirstNm, tocOrgMgrInfoTmsg.thirdOrgMgrFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.frthOrgMgrLastNm, tocOrgMgrInfoTmsg.thirdOrgMgrLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.frthOrgMgrTocCd, tocOrgMgrInfoTmsg.thirdOrgMgrTocCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.frthOrgMgrPsnCd, psnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.frthOrgMgrFirstNm, psnFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.frthOrgMgrLastNm, psnLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.frthOrgMgrTocCd, tocCd);
                }
                break;

            case NMAB240001Constant.FIFTH_LAYER:
                if (dataEmptyFlag) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.fifthOrgMgrPsnCd, tocOrgMgrInfoTmsg.frthOrgMgrPsnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.fifthOrgMgrFirstNm, tocOrgMgrInfoTmsg.frthOrgMgrFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.fifthOrgMgrLastNm, tocOrgMgrInfoTmsg.frthOrgMgrLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.fifthOrgMgrTocCd, tocOrgMgrInfoTmsg.frthOrgMgrTocCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.fifthOrgMgrPsnCd, psnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.fifthOrgMgrFirstNm, psnFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.fifthOrgMgrLastNm, psnLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.fifthOrgMgrTocCd, tocCd);
                }
                break;

            case NMAB240001Constant.SIXTH_LAYER:
                if (dataEmptyFlag) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.sixthOrgMgrPsnCd, tocOrgMgrInfoTmsg.fifthOrgMgrPsnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.sixthOrgMgrFirstNm, tocOrgMgrInfoTmsg.fifthOrgMgrFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.sixthOrgMgrLastNm, tocOrgMgrInfoTmsg.fifthOrgMgrLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.sixthOrgMgrTocCd, tocOrgMgrInfoTmsg.fifthOrgMgrTocCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.sixthOrgMgrPsnCd, psnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.sixthOrgMgrFirstNm, psnFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.sixthOrgMgrLastNm, psnLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.sixthOrgMgrTocCd, tocCd);
                }
                break;

            case NMAB240001Constant.SEVENTH_LAYER:
                if (dataEmptyFlag) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.svnthOrgMgrPsnCd, tocOrgMgrInfoTmsg.sixthOrgMgrPsnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.svnthOrgMgrFirstNm, tocOrgMgrInfoTmsg.sixthOrgMgrFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.svnthOrgMgrLastNm, tocOrgMgrInfoTmsg.sixthOrgMgrLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.svnthOrgMgrTocCd, tocOrgMgrInfoTmsg.sixthOrgMgrTocCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.svnthOrgMgrPsnCd, psnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.svnthOrgMgrFirstNm, psnFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.svnthOrgMgrLastNm, psnLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.svnthOrgMgrTocCd, tocCd);
                }
                break;

            case NMAB240001Constant.EIGHTH_LAYER:
                if (dataEmptyFlag) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.eighthOrgMgrPsnCd, tocOrgMgrInfoTmsg.svnthOrgMgrPsnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.eighthOrgMgrFirstNm, tocOrgMgrInfoTmsg.svnthOrgMgrFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.eighthOrgMgrLastNm, tocOrgMgrInfoTmsg.svnthOrgMgrLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.eighthOrgMgrTocCd, tocOrgMgrInfoTmsg.svnthOrgMgrTocCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.eighthOrgMgrPsnCd, psnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.eighthOrgMgrFirstNm, psnFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.eighthOrgMgrLastNm, psnLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.eighthOrgMgrTocCd, tocCd);
                }
                break;

            case NMAB240001Constant.NINTH_LAYER:
                if (dataEmptyFlag) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.ninthOrgMgrPsnCd, tocOrgMgrInfoTmsg.eighthOrgMgrPsnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.ninthOrgMgrFirstNm, tocOrgMgrInfoTmsg.eighthOrgMgrFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.ninthOrgMgrLastNm, tocOrgMgrInfoTmsg.eighthOrgMgrLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.ninthOrgMgrTocCd, tocOrgMgrInfoTmsg.eighthOrgMgrTocCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.ninthOrgMgrPsnCd, psnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.ninthOrgMgrFirstNm, psnFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.ninthOrgMgrLastNm, psnLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.ninthOrgMgrTocCd, tocCd);
                }
                break;

            case NMAB240001Constant.TENTH_LAYER:
                if (dataEmptyFlag) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tenthOrgMgrPsnCd, tocOrgMgrInfoTmsg.ninthOrgMgrPsnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tenthOrgMgrFirstNm, tocOrgMgrInfoTmsg.ninthOrgMgrFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tenthOrgMgrLastNm, tocOrgMgrInfoTmsg.ninthOrgMgrLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tenthOrgMgrTocCd, tocOrgMgrInfoTmsg.ninthOrgMgrTocCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tenthOrgMgrPsnCd, psnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tenthOrgMgrFirstNm, psnFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tenthOrgMgrLastNm, psnLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.tenthOrgMgrTocCd, tocCd);
                }
                break;

            case NMAB240001Constant.ELEVENTH_LAYER:
                if (dataEmptyFlag) {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.elvthOrgMgrPsnCd, tocOrgMgrInfoTmsg.tenthOrgMgrPsnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.elvthOrgMgrFirstNm, tocOrgMgrInfoTmsg.tenthOrgMgrFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.elvthOrgMgrLastNm, tocOrgMgrInfoTmsg.tenthOrgMgrLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.elvthOrgMgrTocCd, tocOrgMgrInfoTmsg.tenthOrgMgrTocCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.elvthOrgMgrPsnCd, psnCd);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.elvthOrgMgrFirstNm, psnFirstNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.elvthOrgMgrLastNm, psnLastNm);
                    ZYPEZDItemValueSetter.setValue(tocOrgMgrInfoTmsg.elvthOrgMgrTocCd, tocCd);
                }
                break;

            default:
                break;
        }
        // S21_NA#19686 MOD END
    }

    /**
     * For Night Process Only. Delete All data under Business Area and
     * insert.
     */
    private void updateOrgStruS21Org() {
        Map<String, String> businessAreaMap = new HashMap<String, String>();
        businessAreaMap.put("glblCmpyCd", this.glblCmpyCd);
        businessAreaMap.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);

        List<Map<String, Object>> businessAreaList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getAllBusinessAreaList", businessAreaMap);

        for (Map<String, Object> businessAreaListMap : businessAreaList) {
            String bizAreaOrgCd = (String) businessAreaListMap.get(NMAB240001Constant.COLUMN_NM_BIZ_AREA_ORG_CD);

            if (this.bizAreaOrgCdMap.containsKey(bizAreaOrgCd)) {
                // 2016/03/10 Add Start
                chkEffectiveOrgReln(bizAreaOrgCd);
                // 2016/03/10 Add End
                deleteInsertOrgStru(bizAreaOrgCd);

                // 2016/04/15 CSA-QC#6385 Add Start
                if (checkExistsTocInBizArea(bizAreaOrgCd)) {
                    deleteInsertS21Org(bizAreaOrgCd);
                }
                // 2016/04/15 CSA-QC#6385 Add End
            }
        }
    }

    /**
     * When effective organization relation is not exists, rollback
     * and abend. chkEffectiveOrgReln
     * @param bizAreaOrgCd String
     * @return boolean
     */
    private void chkEffectiveOrgReln(String bizAreaOrgCd) {

        Map<String, String> parentMap = new HashMap<String, String>();
        parentMap.put("glblCmpyCd", this.glblCmpyCd);
        parentMap.put("orgTierCd", ORG_TIER._0);
        parentMap.put("bizAreaOrgCd", bizAreaOrgCd);
        Integer effectiveOrgRelnCount = (Integer) this.ssmBatchClient.queryObject("getEffectiveOrgRelnCount", parentMap);

        if (effectiveOrgRelnCount.compareTo(NMAB240001Constant.ZERO) <= 0) {
            this.totalErrCount++;
            super.rollback();
            throw new S21AbendException(NMAB240001Constant.MMAM0003E, new String[] {NMAB240001Constant.TBL_NM_ORG_STRU });
        }
    }

    /**
     * When TOC does not exists in Business area, skip updating
     * S21_ORG. checkExistsTocInBizArea
     * @param bizAreaOrgCd String
     * @return boolean
     */
    private boolean checkExistsTocInBizArea(String bizAreaOrgCd) {
        // 2016/04/15 CSA-QC#6385 Add Start
        Map<String, String> tocCountMap = new HashMap<String, String>();
        tocCountMap.put("glblCmpyCd", this.glblCmpyCd);
        tocCountMap.put("bizAreaOrgCd", bizAreaOrgCd);
        tocCountMap.put("procDt", this.procDt);
        tocCountMap.put("maxEndDate", NMAB240001Constant.MAX_END_DATE);
        // QC#13847
        tocCountMap.put("rgtnSts_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        Integer tocInBizAreaCount = (Integer) this.ssmBatchClient.queryObject("countTocInBizArea", tocCountMap);

        if (tocInBizAreaCount.compareTo(NMAB240001Constant.ZERO) > 0) {
            return true;
        }
        return false;
        // 2016/04/15 CSA-QC#6385 Add End
    }

    /**
     * For Night Process Only. Delete All data of ORG_STRU under
     * Business Area and insert.
     */
    private void deleteInsertOrgStru(String bizAreaOrgCd) {
        // Delete All Data Under Target Business Area
        Map<String, String> deleteOrgStruMap = new HashMap<String, String>();
        deleteOrgStruMap.put("glblCmpyCd", this.glblCmpyCd);
        deleteOrgStruMap.put("firstOrgCd", bizAreaOrgCd);

        List<Map<String, Object>> deleteOrgStruList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDeleteOrgStruList", deleteOrgStruMap);

        for (Map<String, Object> deleteOrgStruListMap : deleteOrgStruList) {
            String orgCd = (String) deleteOrgStruListMap.get(NMAB240001Constant.COLUMN_NM_ORG_CD);
            BigDecimal orgLayerNum = (BigDecimal) deleteOrgStruListMap.get(NMAB240001Constant.COLUMN_NM_ORG_LAYER_NUM);

            this.deleteOrgStru(orgCd, null, orgLayerNum);
        }

        // Insert All Data Under Target Business Area
        Map<String, String> insertOrgStruMap = new HashMap<String, String>();
        insertOrgStruMap.put("glblCmpyCd", this.glblCmpyCd);
        insertOrgStruMap.put("gnrnTpCd_2", GNRN_TP.CURRENT);
        insertOrgStruMap.put("firstOrgCd", bizAreaOrgCd);

        List<Map<String, Object>> insertOrgStruList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getInsertOrgStruList", insertOrgStruMap);
        // 2016/03/10 Add Start
        if (insertOrgStruList == null || insertOrgStruList.size() <= 0) {
            S21InfoLogOutput.println("MMAM0003E", new String[] {NMAB240001Constant.TBL_NM_ORG_STRU + "(getInsertOrgStruList)" });
            this.totalErrCount++;
            super.rollback();
            throw new S21AbendException(NMAB240001Constant.MMAM0003E, new String[] {NMAB240001Constant.TBL_NM_ORG_STRU });

        }
        // 2016/03/10 Add End
        for (Map<String, Object> insertOrgStruListMap : insertOrgStruList) {
            ORG_STRUTMsg orgStruTmsg = new ORG_STRUTMsg();

            ZYPEZDItemValueSetter.setValue(orgStruTmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.orgCd, (String) insertOrgStruListMap.get(NMAB240001Constant.COLUMN_NM_ORG_CD));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.orgLayerNum, (BigDecimal) insertOrgStruListMap.get(NMAB240001Constant.COLUMN_NM_ORG_LAYER_NUM));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.effFromDt, (String) insertOrgStruListMap.get(NMAB240001Constant.COLUMN_NM_EFF_FROM_DT));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.effThruDt, (String) insertOrgStruListMap.get(NMAB240001Constant.COLUMN_NM_EFF_THRU_DT));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.orgStruTpCd, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);

            ZYPEZDItemValueSetter.setValue(orgStruTmsg.firstOrgCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_1, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.firstOrgNm, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_1, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.scdOrgCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_2, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.scdOrgNm, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_2, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.thirdOrgCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_3, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.thirdOrgNm, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_3, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.frthOrgCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_4, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.frthOrgNm, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_4, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.fifthOrgCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_5, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.fifthOrgNm, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_5, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.sixthOrgCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_6, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.sixthOrgNm, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_6, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.svnthOrgCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_7, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.svnthOrgNm, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_7, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.eighthOrgCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_8, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.eighthOrgNm, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_8, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.ninthOrgCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_9, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.ninthOrgNm, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_9, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.tenthOrgCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_10, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.tenthOrgNm, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_10, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.elvthOrgCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_11, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.elvthOrgNm, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_11, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));

            ZYPEZDItemValueSetter.setValue(orgStruTmsg.firstOrgTierCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_1, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.scdOrgTierCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_2, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.thirdOrgTierCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_3, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.frthOrgTierCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_4, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.fifthOrgTierCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_5, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.sixthOrgTierCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_6, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.svnthOrgTierCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_7, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.eighthOrgTierCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_8, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.ninthOrgTierCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_9, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.tenthOrgTierCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_10, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(orgStruTmsg.elvthOrgTierCd, (String) insertOrgStruListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_11, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));

            S21FastTBLAccessor.insert(orgStruTmsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(orgStruTmsg.getReturnCode())) {
                S21InfoLogOutput.println("MMAM0003E", new String[] {NMAB240001Constant.TBL_NM_ORG_STRU + "] [<KEY> ORG_CD:" + orgStruTmsg.orgCd.getValue() + " Return Code:" + orgStruTmsg.getReturnCode() });
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(NMAB240001Constant.MMAM0003E, new String[] {NMAB240001Constant.TBL_NM_ORG_STRU });
            }
        }
    }

    /**
     * For Night Process Only. Delete All data of S21ORG under
     * Business Area and insert.
     */
    private void deleteInsertS21Org(String bizAreaOrgCd) {
// 2017/12/07 QC#22899 Del Start
//        // Set Terminated All Data Under Target Business Area
//        Map<String, String> deleteS21OrgMap = new HashMap<String, String>();
//        deleteS21OrgMap.put("glblCmpyCd", this.glblCmpyCd);
//        deleteS21OrgMap.put("firstOrgCd", bizAreaOrgCd);
//        // 2016/10/28 CSA-QC#15096 Mod Start
//        deleteS21OrgMap.put("rgtnStsCd_P99", RGTN_STS.TERMINATED);
//        deleteS21OrgMap.put("rgtnStsCd_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
//        // deleteS21OrgMap.put("rgtnStsCd", RGTN_STS.TERMINATED);
//        // 2016/10/28 CSA-QC#15096 Mod End
//
//        List<Map<String, Object>> deleteS21OrgList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDeleteS21OrgList", deleteS21OrgMap);
//
//        for (Map<String, Object> deleteS21OrgListMap : deleteS21OrgList) {
//            String tocCd = (String) deleteS21OrgListMap.get(NMAB240001Constant.COLUMN_NM_TOC_CD);
//            this.physicalDeleteS21Org(tocCd);
//        }
// 2017/12/07 QC#22899 Del End

        // Insert All Data Under Target Business Area
        Map<String, String> insertS21OrgMap = new HashMap<String, String>();
        insertS21OrgMap.put("glblCmpyCd", this.glblCmpyCd);
        insertS21OrgMap.put("firstOrgCd", bizAreaOrgCd);
        // QC#13847
        insertS21OrgMap.put("rgtnSts_P20", RGTN_STS.READY_FOR_ORDER_TAKING);

        List<Map<String, Object>> insertS21OrgList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getInsertS21OrgList", insertS21OrgMap);
        // 2016/03/10 Add Start
        if (insertS21OrgList == null || insertS21OrgList.size() <= 0) {
            S21InfoLogOutput.println("MMAM0003E", new String[] {NMAB240001Constant.TBL_NM_S21_ORG + "(getInsertS21OrgList)" });
            this.totalErrCount++;
            super.rollback();
            throw new S21AbendException(NMAB240001Constant.MMAM0003E, new String[] {NMAB240001Constant.TBL_NM_S21_ORG });

        }
        // 2016/03/10 Add End
        for (Map<String, Object> insertS21OrgListMap : insertS21OrgList) {

            S21_ORGTMsg s21OrgTmsg = new S21_ORGTMsg();
            S21_ORGTMsg deleteS21OrgTmsg = new S21_ORGTMsg();

            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.tocCd, (String) insertS21OrgListMap.get(NMAB240001Constant.COLUMN_NM_TOC_CD));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.tocNm, (String) insertS21OrgListMap.get(NMAB240001Constant.COLUMN_NM_TOC_NM));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.coaBrCd, (String) insertS21OrgListMap.get(NMAB240001Constant.COLUMN_NM_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.coaCcCd, (String) insertS21OrgListMap.get(NMAB240001Constant.COLUMN_NM_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.coaProdCd, (String) insertS21OrgListMap.get(NMAB240001Constant.COLUMN_NM_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.jobTtlCd, (String) insertS21OrgListMap.get(NMAB240001Constant.COLUMN_NM_JOB_TTL_CD));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.uniqOrgLayerNum, (BigDecimal) insertS21OrgListMap.get(NMAB240001Constant.COLUMN_NM_ORG_LAYER_NUM));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.orgFuncRoleTpCd, (String) insertS21OrgListMap.get(NMAB240001Constant.COLUMN_NM_ORG_FUNC_ROLE_TP_CD));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.coaChCd, (String) insertS21OrgListMap.get(NMAB240001Constant.COLUMN_NM_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);

            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.firstOrgCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_1, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.firstOrgNm, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_1, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.scdOrgCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_2, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.scdOrgNm, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_2, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.thirdOrgCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_3, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.thirdOrgNm, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_3, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.frthOrgCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_4, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.frthOrgNm, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_4, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.fifthOrgCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_5, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.fifthOrgNm, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_5, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.sixthOrgCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_6, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.sixthOrgNm, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_6, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.svnthOrgCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_7, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.svnthOrgNm, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_7, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.eighthOrgCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_8, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.eighthOrgNm, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_8, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.ninthOrgCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_9, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.ninthOrgNm, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_9, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.tenthOrgCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_10, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.tenthOrgNm, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_10, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.elvthOrgCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_11, "_", NMAB240001Constant.COLUMN_NM_ORG_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.elvthOrgNm, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_11, "_", NMAB240001Constant.COLUMN_NM_ORG_NM)));

            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.firstOrgTierCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_1, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.scdOrgTierCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_2, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.thirdOrgTierCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_3, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.frthOrgTierCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_4, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.fifthOrgTierCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_5, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.sixthOrgTierCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_6, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.svnthOrgTierCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_7, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.eighthOrgTierCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_8, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.ninthOrgTierCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_9, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.tenthOrgTierCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_10, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.elvthOrgTierCd, (String) insertS21OrgListMap.get(ZYPCommonFunc.concatString(NMAB240001Constant.PREFIX_11, "_", NMAB240001Constant.COLUMN_NM_ORG_TIER_CD)));

            // 2017/12/07 QC#22899 Mod Start
            // S21FastTBLAccessor.insert(s21OrgTmsg);
            // 2018/02/05 QC#23938 Mod Start
            // deleteS21OrgTmsg = (S21_ORGTMsg) S21FastTBLAccessor.findByKey(s21OrgTmsg);
            EZDTMsg.copy(s21OrgTmsg, null, deleteS21OrgTmsg, null);
            deleteS21OrgTmsg = (S21_ORGTMsg) S21FastTBLAccessor.findByKey(deleteS21OrgTmsg);
            // 2018/02/05 QC#23938 Mod End
            if (deleteS21OrgTmsg != null) {
                int physicalDelS21OrgCount = S21FastTBLAccessor.removePhysical(new S21_ORGTMsg[] {deleteS21OrgTmsg});
                if (physicalDelS21OrgCount != 1) {
                    S21InfoLogOutput.println("MMAM0003E", new String[] {NMAB240001Constant.TBL_NM_S21_ORG + "] [<KEY> TOC_CD:" + deleteS21OrgTmsg.tocCd.getValue() + " Return Code:" + deleteS21OrgTmsg.getReturnCode() });
                    this.totalErrCount++;
                    super.rollback();
                    throw new S21AbendException(NMAB240001Constant.MMAM0005E, new String[] {NMAB240001Constant.TBL_NM_S21_ORG });
                }
                S21FastTBLAccessor.insert(s21OrgTmsg);
            } else {
                S21FastTBLAccessor.insert(s21OrgTmsg);
            }
            // 2017/12/07 QC#22899 Mod End

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(s21OrgTmsg.getReturnCode())) {
                S21InfoLogOutput.println("MMAM0003E", new String[] {NMAB240001Constant.TBL_NM_S21_ORG + "] [<KEY> TOC_CD:" + s21OrgTmsg.tocCd.getValue() + " Return Code:" + s21OrgTmsg.getReturnCode() });
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(NMAB240001Constant.MMAM0003E, new String[] {NMAB240001Constant.TBL_NM_S21_ORG });
            }
        }
    }

    @Override
    protected void termRoutine() {
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB240001().executeBatch(NMAB240001.class.getSimpleName());
    }

}
