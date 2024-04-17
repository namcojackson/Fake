/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB045001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.NSAI0450_01TMsg;
import business.db.SVC_PHYS_MTR_READTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_GRP_TP;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NSAB0450 Send Meter Read To Sys85 Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 06/17/2016   CITS           S.Endo          Create          N/A
 * 02/16/2018   CITS           M.Naito         Update          QC#23855
 *</pre>
 */
public class NSAB045001 extends S21BatchMain {

    // -- Input parameters ----------------------
    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface ID */
    private String interfaceId;

    // -- Count of processing -------------------
    /** The number of cases : Select */
    private int selectCount;

    /** The number of cases : Insert */
    private int insertCount = 0;

    /** The number of case : Skip */
    private int skipCount;

    // -- Other Internal Variable ---------------
    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Termination code */
    private TERM_CD termCd;

    /** Transaction table accessor */
    private S21TransactionTableAccessor trxAccess;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** TransactionId */
    private BigDecimal trxId = BigDecimal.ZERO;

    @Override
    protected void initRoutine() {
        // Initialization of variable
        selectCount = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        // The Transaction ID is obtained
        trxAccess = new S21TransactionTableAccessor();

        // Global Company Code
        glblCmpyCd = profileService.getGlobalCompanyCode();

        // Interface ID
        interfaceId = getInterfaceID();

        // Check on input parameter
        checkParameter();
    }

    @Override
    protected void mainRoutine() {

        List<Map<String, Object>> meterInfoMapList = null;
        meterInfoMapList = getMeterInformationList();
        selectCount = meterInfoMapList.size();

        if (selectCount > 0) {
            // Create InterfaceData.
            insertCount = createInterface(meterInfoMapList);
            // Update SVC_PHYS_MTR_READ table.
            updateSvcPhysMtrRead(meterInfoMapList);
            skipCount = selectCount - insertCount;
            // Create Transaction table.
            trxAccess.createIntegrationRecordForBatch(interfaceId, trxId);
            commit();
        }
    }

    @Override
    protected void termRoutine() {
        // Setting of termination code
        setTermState(termCd, selectCount, skipCount, insertCount);
    }

    /**
     * @param args execution parameters
     */
    public static void main(String[] args) {
        new NSAB045001().executeBatch(NSAB045001.class.getSimpleName());
    }

    /**
     * <pre>
     * Check processing of input parameters.
     * </pre>
     */
    private void checkParameter() {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NSAB045001Constant.MSG_ID_ZZZM9025E, new String[] {NSAB045001Constant.MSG_STR_COMP_CODE });
        }
        // check table:GLBL_CMPY
        String registeredGlobalCompanyCode = getRegisteredGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(registeredGlobalCompanyCode)) {
            throw new S21AbendException(NSAB045001Constant.MSG_ID_ZZZM9025E, new String[] {NSAB045001Constant.MSG_STR_COMP_CODE });
        }

        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NSAB045001Constant.MSG_ID_ZZZM9025E, new String[] {NSAB045001Constant.MSG_STR_INTERFACE_ID });
        }
    }

    /**
     * Get globalCompanyCode
     * @return globalCompanyCode
     */
    private String getRegisteredGlobalCompanyCode() {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NSAB045001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);

        Object code = this.ssmBatchClient.queryObject("getGlobalCompanyCode", queryParam);
        if (code != null) {
            return code.toString();
        } else {
            return null;
        }
    }

    /**
     * Get meterInformationList
     * @return MeterInformation
     */
    private List<Map<String, Object>> getMeterInformationList() {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NSAB045001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        List<Map<String, Object>> result = this.ssmBatchClient.queryObjectList("getMeterInformation", queryParam);
        return result;
    }

    /**
     * Create Interface Data NSAI0450_01
     * @param meterInfoMapList
     * @return
     */
    private int createInterface(List<Map<String, Object>> meterInfoMapList) {
        for (Iterator<Map<String, Object>> it = meterInfoMapList.iterator(); it.hasNext();) {
            // Get Transaction ID
            if (BigDecimal.ZERO.compareTo(trxId) == 0) {
                trxId = trxAccess.getNextTransactionId();
            }
            Map<String, Object> meterInfoMap = it.next();
            NSAI0450_01TMsg tMsg = new NSAI0450_01TMsg();

            // START 2018/02/16 M.Naito [QC#23855, ADD]
            // Check send Meter Read to CUSA from Meter Group and Meter Label
            if (MTR_GRP_TP.BLACK_AND_WHITE.equals(meterInfoMap.get(NSAB045001Constant.COL_MTR_GRP_TP_CD)) && ZYPConstant.FLG_ON_Y.equals(meterInfoMap.get(NSAB045001Constant.COL_BW_MTR_FLG))) {
                insertCount = insertCount + 1;
                continue;
            }
            // ENDT 2018/02/16 M.Naito [QC#23855, ADD]

            // INTERFACE_ID
            tMsg.interfaceId.setValue(interfaceId);
            // TRANSACTION_ID
            tMsg.transactionId.setValue(trxId);
            // SEGMENT_ID
            tMsg.segmentId.setValue(new BigDecimal(ZYPConstant.FLG_ON_1));
            // UNIT_ID
            tMsg.unitId.setValue(new BigDecimal(ZYPConstant.FLG_ON_1));
            // SEQ_NUMBER
            tMsg.seqNumber.setValue(insertCount + 1);
            // SER_NUM
            String serNum = meterInfoMap.get(NSAB045001Constant.COL_SER_NUM).toString();
            if (serNum.length() > NSAB045001Constant.VALUE_LENGTH_SER_NUM) {
                tMsg.serNum.setValue(serNum.substring(0, NSAB045001Constant.VALUE_LENGTH_SER_NUM));
            } else {
                tMsg.serNum.setValue(serNum);
            }
            // MTR_READ_DT
            tMsg.mtrReadDt.setValue(meterInfoMap.get(NSAB045001Constant.COL_MTR_READ_DT).toString());
            // READ_MTR_CNT
            tMsg.readMtrCnt.setValue(Integer.parseInt(meterInfoMap.get(NSAB045001Constant.COL_READ_MTR_CNT).toString()));
            // MTR_CNTR_ID
            tMsg.mtrCntrId.setValue(meterInfoMap.get(NSAB045001Constant.COL_MTR_CNTR_ID).toString());
            // TEST_MTR_CNT
            tMsg.testMtrCnt.setValue(new BigDecimal(ZYPConstant.FLG_OFF_0));
            // ORG_NM
            tMsg.orgNm.setValue(NSAB045001Constant.ORG_NM);

            EZDTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSAB045001Constant.MSG_ID_NMAM0176E);
            }
            insertCount = insertCount + 1;
        }
        return insertCount;
    }

    /**
     * update SVC_PHYS_MTR_READ set ROSS_BAT_PROC_STS_CD='1'
     * @param meterInfoMapList
     */
    private void updateSvcPhysMtrRead(List<Map<String, Object>> meterInfoMapList) {
        for (Iterator<Map<String, Object>> it = meterInfoMapList.iterator(); it.hasNext();) {
            Map<String, Object> meterInfoMap = it.next();

            SVC_PHYS_MTR_READTMsg tMsg = new SVC_PHYS_MTR_READTMsg();
            // PrimaryKey Condition
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.svcPhysMtrReadPk, new BigDecimal(meterInfoMap.get(NSAB045001Constant.COL_SVC_PHYS_MTR_READ_PK).toString()));

            tMsg = (SVC_PHYS_MTR_READTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
            // set column for update (ROSS_BAT_PROC_STS_CD)
            ZYPEZDItemValueSetter.setValue(tMsg.rossBatProcStsCd, ZYPConstant.FLG_ON_1);

            EZDTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSAB045001Constant.MSG_ID_NMAM0176E);
            }
        }

    }
}
