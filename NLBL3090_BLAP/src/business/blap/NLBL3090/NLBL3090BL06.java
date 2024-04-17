package business.blap.NLBL3090;

import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_CARR_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_CARR_CTAC_EML_ADDR;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_CARR_CTAC_TEL_NUM;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_EFF_FROM_DT;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_EFF_THRU_DT;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_EZUPTIME;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_EZUPTIMEZONE;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_GLBL_CMPY_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_RTL_WH_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_SCHD_COORD_PSN_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_ST_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_PARAM_ROW_ID;
import static business.blap.NLBL3090.constant.NLBL3090Constant.NLAM0014E;
import static business.blap.NLBL3090.constant.NLBL3090Constant.NLAM1091E;
import static business.blap.NLBL3090.constant.NLBL3090Constant.NPAM0006E;
import static business.blap.NLBL3090.constant.NLBL3090Constant.SCHD_COORD_ASG_RELN_SQ;
import static business.blap.NLBL3090.constant.NLBL3090Constant.TAB_ASSIGN;
import static business.blap.NLBL3090.constant.NLBL3090Constant.ZZM9037E;
import static business.blap.NLBL3090.constant.NLBL3090Constant.ZZZM9003I;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSDateItem;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.common.EZDTDateItem;
import parts.common.EZDTMsg;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3090.common.NLBL3090CommonLogic;
import business.db.SCHD_COORD_ASGTMsg;
import business.db.SCHD_COORD_ASG_RELNTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NLBL3090 Call Coordinator Assignment Setup
 * Function Name : update business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            Yasushi Nomura  Create          N/A
 * 04/01/2016   CSAI            D.Fukaya        Update          QC#6090
 *</pre>
 */
public class NLBL3090BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL3090Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLBL3090Scrn00_CMN_Submit((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else if ("NLBL3090Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NLBL3090Scrn00_DeleteRow((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Submit Event
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    private void doProcess_NLBL3090Scrn00_CMN_Submit(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        if (TAB_ASSIGN.equals(cMsg.xxDplyTab.getValue())) {
            // Assign
            submitAssign(cMsg, sMsg);
        } else {
            // Coordination
            submitCoordination(cMsg, sMsg);
        }
    }

    private void submitAssign(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        NLBL3090CommonLogic.copyFromCmsgOntoSmsgAssign(cMsg, sMsg);
        NLBL3090CommonLogic.updateValueForInvisibleFieldsAssignTab(sMsg);
        /***********************************/
        /***   Master Existence check   ****/
        /***********************************/
        int firstErrIndex = NLBL3090CommonLogic.checkMasterExistenceAssignTab(sMsg, getGlobalCompanyCode());
        if (firstErrIndex != -1) {
            cMsg.setMessageInfo(ZZM9037E);
            cMsg.xxPageShowFromNum_P1.setValue(firstErrIndex);
            NLBL3090CommonLogic.copyFromSMsgOntoCmsgAssign(cMsg, sMsg);
            return;
        }
        /******************************/
        /***   Duplication check   ****/
        /******************************/
        firstErrIndex = NLBL3090CommonLogic.checkDuplicationForAssignTab(sMsg);
        if (firstErrIndex != -1) {
            cMsg.setMessageInfo(ZZM9037E);
            cMsg.xxPageShowFromNum_P1.setValue(firstErrIndex);
            NLBL3090CommonLogic.copyFromSMsgOntoCmsgAssign(cMsg, sMsg);
            return;
        }

        firstErrIndex = NLBL3090CommonLogic.checkDuplicationWithDBForAssignTab(getGlobalCompanyCode(), sMsg);
        if (firstErrIndex != -1) {
            cMsg.setMessageInfo(ZZM9037E);
            cMsg.xxPageShowFromNum_P1.setValue(firstErrIndex);
            NLBL3090CommonLogic.copyFromSMsgOntoCmsgAssign(cMsg, sMsg);
            return;
        }
        /******************************/
        /***   DB Registration     ****/
        /******************************/
        List<SCHD_COORD_ASG_RELNTMsg> insertMsgs = new ArrayList<SCHD_COORD_ASG_RELNTMsg>();
        List<SCHD_COORD_ASG_RELNTMsg> updateMsgs = new ArrayList<SCHD_COORD_ASG_RELNTMsg>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3090_ASMsg bizMsg = sMsg.A.no(i);
            if (bizMsg.xxNewRowNum_A1.getValueInt() < 0) {
                // New
                insertMsgs.add(createInsertDataForCoordinatorAssignReln(bizMsg));
            } else {
                // Update
                SCHD_COORD_ASG_RELNTMsg tMsg = lockCoordinatorAssignReln(cMsg, bizMsg);
                if (tMsg == null) {
                    return;
                }
                if (!compareUpdateData(bizMsg, tMsg)) {
                    updateMsgs.add(createUpdateDataForCoordinatorAssignReln(bizMsg, tMsg));
                }
            }
        }
        // Update DB
        for (EZDTMsg msg : updateMsgs) {
            S21FastTBLAccessor.update(msg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(msg.getReturnCode())) {
                cMsg.setMessageInfo(NLAM0014E);
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return;
            }
        }
        // Insert DB
        if (0 < insertMsgs.size()) {
            int ret = S21FastTBLAccessor.insert(insertMsgs.toArray(new SCHD_COORD_ASG_RELNTMsg[insertMsgs.size()]));
            if (ret != insertMsgs.size()) {
                cMsg.setMessageInfo(NLAM1091E);
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return;
            }
        }
        // Normal End
        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });
    }

    private void submitCoordination(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        NLBL3090CommonLogic.copyFromCmsgOntoSmsgCoordination(cMsg, sMsg);
        NLBL3090CommonLogic.updateValueForInvisibleFieldsCoordinationTab(sMsg);
        /***********************************/
        /***   Master Existence check   ****/
        /***********************************/
        int firstErrIndex = NLBL3090CommonLogic.checkMasterExistenceCoordinationTab(sMsg, getGlobalCompanyCode());
        if (firstErrIndex != -1) {
            cMsg.setMessageInfo(ZZM9037E);
            cMsg.xxPageShowFromNum_P2.setValue(firstErrIndex);
            NLBL3090CommonLogic.copyFromSMsgOntoCmsgCoordination(cMsg, sMsg);
            return;
        }
        /******************************/
        /***   Duplication check   ****/
        /******************************/
        firstErrIndex = NLBL3090CommonLogic.checkDuplicationForCoordinationTab(sMsg);
        if (firstErrIndex != -1) {
            cMsg.setMessageInfo(ZZM9037E);
            cMsg.xxPageShowFromNum_P2.setValue(firstErrIndex);
            NLBL3090CommonLogic.copyFromSMsgOntoCmsgCoordination(cMsg, sMsg);
            return;
        }

        firstErrIndex = NLBL3090CommonLogic.checkDuplicationWithDBForCoordinationTab(getGlobalCompanyCode(), sMsg);
        if (firstErrIndex != -1) {
            cMsg.setMessageInfo(ZZM9037E);
            cMsg.xxPageShowFromNum_P2.setValue(firstErrIndex);
            NLBL3090CommonLogic.copyFromSMsgOntoCmsgCoordination(cMsg, sMsg);
            return;
        }
        /******************************/
        /***   DB Registration     ****/
        /******************************/
        List<SCHD_COORD_ASGTMsg> insertMsgs = new ArrayList<SCHD_COORD_ASGTMsg>();

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            NLBL3090_BSMsg bizMsg = sMsg.B.no(i);

            if (bizMsg.xxNewRowNum_B1.getValueInt() < 0) {
                // New
                insertMsgs.add(createInsertDataForCoordinatorAssign(bizMsg));

            } else {
                // Update
                SCHD_COORD_ASGTMsg tMsg = lockCoordinatorAssign(cMsg, bizMsg);
                if (tMsg == null) {
                    return;
                }
                if (!compareUpdateData(bizMsg, tMsg)) {
                    if (!updateCoordinatorAssign(bizMsg, tMsg)) {
                        cMsg.setMessageInfo(NLAM0014E);
                        cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                        return;
                    }
                }
            }
        }
        // Insert DB
        if (0 < insertMsgs.size()) {
            int ret = S21FastTBLAccessor.insert(insertMsgs.toArray(new SCHD_COORD_ASGTMsg[insertMsgs.size()]));
            if (ret != insertMsgs.size()) {
                cMsg.setMessageInfo(NLAM1091E);
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return;
            }
        }
        // Normal End
        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });
    }

    /**
     * Note: record is supposed to be present. Even when record is not
     * found, it sets lock error message.
     * @param bizMsg
     * @param sMsg
     * @return tMsg if lock is successfully acquired.
     */
    private SCHD_COORD_ASG_RELNTMsg lockCoordinatorAssignReln(NLBL3090CMsg bizMsg, NLBL3090_ASMsg sMsg) {
        SCHD_COORD_ASG_RELNTMsg tMsg = findCoordinatorAssignReln(sMsg);
        if (tMsg == null) {
            bizMsg.setMessageInfo(NPAM0006E);
            return null;
        }

        String preUpTime = sMsg.xxRqstTs_A.getValue();
        String preTimeZone = sMsg.xxRqstTz_A.getValue();
        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();
        if (ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, currUpTime, currTimeZone)) {
            return tMsg;
        } else {
            bizMsg.setMessageInfo(NPAM0006E);
            return null;
        }
    }

    /**
     * Find SCHD_COORD_ASG_RELN by primary key, for update.
     * @param bizMsg
     * @return
     */
    private SCHD_COORD_ASG_RELNTMsg findCoordinatorAssignReln(NLBL3090_ASMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.schdCoordAsgRelnPk_A)) {
            return null;
        }

        SCHD_COORD_ASG_RELNTMsg inMsg = new SCHD_COORD_ASG_RELNTMsg();
        setValue(inMsg.schdCoordAsgRelnPk, bizMsg.schdCoordAsgRelnPk_A);
        setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());

        SCHD_COORD_ASG_RELNTMsg outMsg = (SCHD_COORD_ASG_RELNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
        return outMsg;
    }

    /**
     * Note: record is supposed to be present. Even when record is not
     * found, it sets lock error message.
     * @param bizMsg
     * @param sMsg
     * @return tMsg if lock is successfully acquired.
     */
    private SCHD_COORD_ASGTMsg lockCoordinatorAssign(NLBL3090CMsg bizMsg, NLBL3090_BSMsg sMsg) {
        SCHD_COORD_ASGTMsg tMsg = findCoordinatorAssign(sMsg);
        if (tMsg == null) {
            bizMsg.setMessageInfo(NPAM0006E);
            return null;
        }

        String preUpTime = sMsg.xxRqstTs_B.getValue();
        String preTimeZone = sMsg.xxRqstTz_B.getValue();
        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();
        if (ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, currUpTime, currTimeZone)) {
            return tMsg;
        } else {
            bizMsg.setMessageInfo(NPAM0006E);
            return null;
        }
    }

    private SCHD_COORD_ASGTMsg findCoordinatorAssign(NLBL3090_BSMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.xxRowId_B)) {
            return null;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_ROW_ID, bizMsg.xxRowId_B);

        // Execute
        S21SsmEZDResult result = NLBL3090Query.getInstance().getCoordinatorAssign(ssmParam);

        SCHD_COORD_ASGTMsg outMsg = null;
        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();
            Map<String, Object> recode = (Map<String, Object>) resultMap.get(0);
            outMsg = new SCHD_COORD_ASGTMsg();
            ZYPEZDItemValueSetter.setValue(outMsg.schdCoordPsnCd, (String) recode.get(DB_COLUMN_SCHD_COORD_PSN_CD));
            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, (String) recode.get(DB_COLUMN_GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(outMsg.stCd, (String) recode.get(DB_COLUMN_ST_CD));
            ZYPEZDItemValueSetter.setValue(outMsg.effFromDt, (String) recode.get(DB_COLUMN_EFF_FROM_DT));
            ZYPEZDItemValueSetter.setValue(outMsg.effThruDt, (String) recode.get(DB_COLUMN_EFF_THRU_DT));
            ZYPEZDItemValueSetter.setValue(outMsg.carrCd, (String) recode.get(DB_COLUMN_CARR_CD));
            ZYPEZDItemValueSetter.setValue(outMsg.carrCtacEmlAddr, (String) recode.get(DB_COLUMN_CARR_CTAC_EML_ADDR));
            ZYPEZDItemValueSetter.setValue(outMsg.carrCtacTelNum, (String) recode.get(DB_COLUMN_CARR_CTAC_TEL_NUM));
            ZYPEZDItemValueSetter.setValue(outMsg.rtlWhCd, (String) recode.get(DB_COLUMN_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(outMsg.ezUpTime, (String) recode.get(DB_COLUMN_EZUPTIME));
            ZYPEZDItemValueSetter.setValue(outMsg.ezUpTimeZone, (String) recode.get(DB_COLUMN_EZUPTIMEZONE));
        }
        return outMsg;
    }

    private SCHD_COORD_ASG_RELNTMsg createInsertDataForCoordinatorAssignReln(NLBL3090_ASMsg bizMsg) {
        SCHD_COORD_ASG_RELNTMsg tMsg = new SCHD_COORD_ASG_RELNTMsg();
        // SCHD_COORD_ASG_RELN_PK
        tMsg.schdCoordAsgRelnPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(SCHD_COORD_ASG_RELN_SQ));
        // GLBL_CMPY_CD
        tMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        // RTL_WH_CD
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, bizMsg.rtlWhCd_A);
        // MGR_PSN_CD
        ZYPEZDItemValueSetter.setValue(tMsg.mgrPsnCd, bizMsg.mgrPsnCd_AM);
        // SUPV_PSN_CD
        ZYPEZDItemValueSetter.setValue(tMsg.supvPsnCd, bizMsg.supvPsnCd_AS);
        // SCHD_COORD_PSN_CD
        ZYPEZDItemValueSetter.setValue(tMsg.schdCoordPsnCd, bizMsg.schdCoordPsnCd_AC);

        return tMsg;
    }

    private SCHD_COORD_ASG_RELNTMsg createUpdateDataForCoordinatorAssignReln(NLBL3090_ASMsg bizMsg, SCHD_COORD_ASG_RELNTMsg tMsg) {
        // RTL_WH_CD
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, bizMsg.rtlWhCd_A);
        // MGR_PSN_CD
        ZYPEZDItemValueSetter.setValue(tMsg.mgrPsnCd, bizMsg.mgrPsnCd_AM);
        // SUPV_PSN_CD
        ZYPEZDItemValueSetter.setValue(tMsg.supvPsnCd, bizMsg.supvPsnCd_AS);
        // SCHD_COORD_PSN_CD
        ZYPEZDItemValueSetter.setValue(tMsg.schdCoordPsnCd, bizMsg.schdCoordPsnCd_AC);

        return tMsg;
    }

    private SCHD_COORD_ASGTMsg createInsertDataForCoordinatorAssign(NLBL3090_BSMsg bizMsg) {
        SCHD_COORD_ASGTMsg tMsg = new SCHD_COORD_ASGTMsg();
        // SCHD_COORD_PSN_CD
        ZYPEZDItemValueSetter.setValue(tMsg.schdCoordPsnCd, bizMsg.schdCoordPsnCd_BC);
        // RTL_WH_CD
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, bizMsg.rtlWhCd_B);
        // ST_CD
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, bizMsg.stCd_B);
        // EFF_FROM_DT
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, bizMsg.effFromDt_B);
        // GLBL_CMPY_CD
        tMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        // CARR_CD
        ZYPEZDItemValueSetter.setValue(tMsg.carrCd, bizMsg.carrCd_B);
        // CARR_CTAC_EML_ADDR
        ZYPEZDItemValueSetter.setValue(tMsg.carrCtacEmlAddr, bizMsg.carrCtacEmlAddr_B);
        // CARR_CTAC_TEL_NUM
        ZYPEZDItemValueSetter.setValue(tMsg.carrCtacTelNum, bizMsg.carrCtacTelNum_B);
        // EFF_THRU_DT
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, bizMsg.effThruDt_B);

        return tMsg;
    }

    private boolean compareUpdateData(NLBL3090_ASMsg bizMsg, SCHD_COORD_ASG_RELNTMsg tMsg) {
        // RTL_WH_CD
        if (!compare(bizMsg.rtlWhCd_A, tMsg.rtlWhCd)) {
            return false;
        }
        // MGR_PSN_CD
        if (!compare(bizMsg.mgrPsnCd_AM, tMsg.mgrPsnCd)) {
            return false;
        }
        // SUPV_PSN_CD
        if (!compare(bizMsg.supvPsnCd_AS, tMsg.supvPsnCd)) {
            return false;
        }
        // SCHD_COORD_PSN_CD
        if (!compare(bizMsg.schdCoordPsnCd_AC, tMsg.schdCoordPsnCd)) {
            return false;
        }
        return true;
    }

    private boolean compareUpdateData(NLBL3090_BSMsg bizMsg, SCHD_COORD_ASGTMsg tMsg) {
        // SCHD_COORD_PSN_CD
        if (!compare(bizMsg.schdCoordPsnCd_BC, tMsg.schdCoordPsnCd)) {
            return false;
        }
        // RTL_WH_CD
        if (!compare(bizMsg.rtlWhCd_B, tMsg.rtlWhCd)) {
            return false;
        }
        // ST_CD
        if (!compare(bizMsg.stCd_B, tMsg.stCd)) {
            return false;
        }
        // EFF_FROM_DT
        if (!compare(bizMsg.effFromDt_B, tMsg.effFromDt)) {
            return false;
        }
        // CARR_CD
        if (!compare(bizMsg.carrCd_B, tMsg.carrCd)) {
            return false;
        }
        // CARR_CTAC_EML_ADDR
        if (!compare(bizMsg.carrCtacEmlAddr_B, tMsg.carrCtacEmlAddr)) {
            return false;
        }
        // CARR_CTAC_TEL_NUM
        if (!compare(bizMsg.carrCtacTelNum_B, tMsg.carrCtacTelNum)) {
            return false;
        }
        // EFF_THRU_DT
        if (!compare(bizMsg.effThruDt_B, tMsg.effThruDt)) {
            return false;
        }
        return true;
    }

    private boolean compare(EZDSStringItem item1, EZDTStringItem item2) {
        if (ZYPCommonFunc.hasValue(item1) && ZYPCommonFunc.hasValue(item2)) {
            return item1.getValue().equals(item2.getValue());
        }
        if (!ZYPCommonFunc.hasValue(item1) && !ZYPCommonFunc.hasValue(item2)) {
            return true;
        }
        return false;
    }

    private boolean compare(EZDSDateItem item1, EZDTDateItem item2) {
        if (ZYPCommonFunc.hasValue(item1) && ZYPCommonFunc.hasValue(item2)) {
            return item1.getValue().equals(item2.getValue());
        }
        if (!ZYPCommonFunc.hasValue(item1) && !ZYPCommonFunc.hasValue(item2)) {
            return true;
        }
        return false;
    }

    private boolean updateCoordinatorAssign(NLBL3090_BSMsg newMsg, SCHD_COORD_ASGTMsg oldMsg) {
        SCHD_COORD_ASGTMsg tMsg = new SCHD_COORD_ASGTMsg();
        // SCHD_COORD_PSN_CD
        ZYPEZDItemValueSetter.setValue(tMsg.schdCoordPsnCd, newMsg.schdCoordPsnCd_BC);
        // RTL_WH_CD
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, newMsg.rtlWhCd_B);
        // ST_CD
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, newMsg.stCd_B);
        // EFF_FROM_DT
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, newMsg.effFromDt_B);
        // EFF_THRU_DT
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, newMsg.effThruDt_B);
        // CARR_CD
        ZYPEZDItemValueSetter.setValue(tMsg.carrCd, newMsg.carrCd_B);
        // CARR_CTAC_EML_ADDR
        ZYPEZDItemValueSetter.setValue(tMsg.carrCtacEmlAddr, newMsg.carrCtacEmlAddr_B);
        // CARR_CTAC_TEL_NUM
        ZYPEZDItemValueSetter.setValue(tMsg.carrCtacTelNum, newMsg.carrCtacTelNum_B);

        S21FastTBLAccessor.updateByPartialValue(oldMsg, new String[] {"glblCmpyCd", "schdCoordPsnCd", "rtlWhCd", "stCd", "effFromDt" }, //
                tMsg, new String[] {"schdCoordPsnCd", "rtlWhCd", "stCd", "effFromDt", "effThruDt", "carrCd", "carrCtacEmlAddr", "carrCtacTelNum" });
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Delete row Event
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    private void doProcess_NLBL3090Scrn00_DeleteRow(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        if (TAB_ASSIGN.equals(cMsg.xxDplyTab.getValue())) {
            // Assign
            deleteRowAssign(cMsg, sMsg);
        } else {
            // Coordination
            deleteRowCoordination(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Delete row Event in Assign TAB
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    private void deleteRowAssign(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        NLBL3090CommonLogic.copyFromCmsgOntoSmsgAssign(cMsg, sMsg);

        int mgNo = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3090_ASMsg bizMsg = sMsg.A.no(i);
            if ((mgNo != 0) && (mgNo == bizMsg.xxNewRowNum_A2.getValueInt())) {
                bizMsg.xxChkBox_AC.setValue(ZYPConstant.FLG_ON_Y);
            } else if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_AM.getValue())) {
                bizMsg.xxChkBox_AC.setValue(ZYPConstant.FLG_ON_Y);
                mgNo = bizMsg.xxNewRowNum_A2.getValueInt();
            } else {
                mgNo = 0;
            }
        }
        mgNo = 0;
        int svNo = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3090_ASMsg bizMsg = sMsg.A.no(i);
            if ((svNo != 0) && (mgNo == bizMsg.xxNewRowNum_A2.getValueInt()) && (svNo == bizMsg.xxNewRowNum_A3.getValueInt())) {
                bizMsg.xxChkBox_AC.setValue(ZYPConstant.FLG_ON_Y);
            } else if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_AS.getValue())) {
                bizMsg.xxChkBox_AC.setValue(ZYPConstant.FLG_ON_Y);
                mgNo = bizMsg.xxNewRowNum_A2.getValueInt();
                svNo = bizMsg.xxNewRowNum_A3.getValueInt();
            } else {
                mgNo = 0;
                svNo = 0;
            }
        }

        // remove add row
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3090_ASMsg bizMsg = sMsg.A.no(i);
            if ((bizMsg.xxNewRowNum_A1.getValueInt() < 0) && (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_AC.getValue()))) {
                removeRow(sMsg.A, i);
                i--;
            }
        }
        // Delete Row
        List<SCHD_COORD_ASG_RELNTMsg> deleteMsgs = new ArrayList<SCHD_COORD_ASG_RELNTMsg>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3090_ASMsg bizMsg = sMsg.A.no(i);
            if ((0 <= bizMsg.xxNewRowNum_A1.getValueInt()) && (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_AC.getValue()))) {
                SCHD_COORD_ASG_RELNTMsg tMsg = lockCoordinatorAssignReln(cMsg, bizMsg);
                if (tMsg == null) {
                    return;
                }
                deleteMsgs.add(tMsg);
            }
        }
        // Delete DB
        if (0 < deleteMsgs.size()) {
            int ret = S21FastTBLAccessor.removeLogical(deleteMsgs.toArray(new SCHD_COORD_ASG_RELNTMsg[deleteMsgs.size()]));
            if (ret != deleteMsgs.size()) {
                cMsg.setMessageInfo(NLAM0014E);
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return;
            }
        }

        // Normal End
        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Delete Row" });
    }

    private void deleteRowCoordination(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        NLBL3090CommonLogic.copyFromCmsgOntoSmsgCoordination(cMsg, sMsg);

        int coNo = 0;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NLBL3090_BSMsg bizMsg = sMsg.B.no(i);
            if ((coNo != 0) && (coNo == bizMsg.xxNewRowNum_B2.getValueInt())) {
                bizMsg.xxChkBox_BS.setValue(ZYPConstant.FLG_ON_Y);
            } else if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_BC.getValue())) {
                bizMsg.xxChkBox_BS.setValue(ZYPConstant.FLG_ON_Y);
                coNo = bizMsg.xxNewRowNum_B2.getValueInt();
            } else {
                coNo = 0;
            }
        }
        // remove add row
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NLBL3090_BSMsg bizMsg = sMsg.B.no(i);
            if ((bizMsg.xxNewRowNum_B1.getValueInt() < 0) && (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_BS.getValue()))) {
                removeRow(sMsg.B, i);
                i--;
            }
        }

        // Delete Row
        List<SCHD_COORD_ASGTMsg> deleteMsgs = new ArrayList<SCHD_COORD_ASGTMsg>();
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NLBL3090_BSMsg bizMsg = sMsg.B.no(i);
            if ((0 <= bizMsg.xxNewRowNum_B1.getValueInt()) && (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_BS.getValue()))) {
                SCHD_COORD_ASGTMsg tMsg = lockCoordinatorAssign(cMsg, bizMsg);
                if (tMsg == null) {
                    return;
                }
                deleteMsgs.add(tMsg);
            }
        }
        // Delete DB
        if (0 < deleteMsgs.size()) {
            int ret = S21FastTBLAccessor.removePhysical(deleteMsgs.toArray(new SCHD_COORD_ASGTMsg[deleteMsgs.size()]));
            if (ret != deleteMsgs.size()) {
                cMsg.setMessageInfo(NLAM0014E);
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return;
            }
        }

        // Normal End
        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Delete Row" });
    }

    private void removeRow(NLBL3090_ASMsgArray table, int index) {
        if (index < 0) {
            return;
        }
        for (int i = index; i < table.getValidCount() - 1; i++) {
            EZDMsg.copy(table.no(i + 1), null, table.no(i), null);
        }
        table.no(table.getValidCount() - 1).clear();
        table.setValidCount(table.getValidCount() - 1);
    }

    private void removeRow(NLBL3090_BSMsgArray table, int index) {
        if (index < 0) {
            return;
        }
        for (int i = index; i < table.getValidCount() - 1; i++) {
            EZDMsg.copy(table.no(i + 1), null, table.no(i), null);
        }
        table.no(table.getValidCount() - 1).clear();
        table.setValidCount(table.getValidCount() - 1);
    }
}
