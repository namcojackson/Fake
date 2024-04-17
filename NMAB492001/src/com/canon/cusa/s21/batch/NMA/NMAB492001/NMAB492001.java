/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB492001;

import java.math.BigDecimal;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import parts.dbcommon.EZDTBLAccessorForBatch;
import business.db.NMAI2080_01TMsg;
import business.db.NMAI2080_01TMsgArray;
import business.db.NMAI2080_02TMsg;
import business.db.NMAI2080_03TMsg;
import business.db.NMAI2080_03TMsgArray;
import business.db.NMAI2080_04TMsg;
import business.db.NMAI2080_04TMsgArray;
import business.db.PTY_LOC_WRKTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DPL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_GRP;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 *  schedule batch
 *  NMAB4920 Load DPL by batch(Inbound)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/09/01   SRA             Takeyama        Create          N/A
 * 2010/12/22   SRA             Y.Chen          Update          N/A
 * 2016/05/20   Fujitsu         S.Ohki          Update          CSA[mod]
 *</pre>
 */

public class NMAB492001 extends S21BatchMain {

    /** message of processed record's count */
    private static final String ZZBM0009I = "ZZBM0009I";

    /** error message(DB access error) */
    private static final String DB_ACCESS_ERR = "NMAM8010E";

    /** error message(InterfaceID no data) */
    private static final String INTFC_ID_ERR = "NMAM8009E";

    /** error message(argument error) */
    private static final String ARG_ERR = "NMAM8039E";

    // /** warning message(Request ftpget error) */
    // private static final String REQ_FTPGET_ERR = "NMAM8022W";

    /** Data Updated */
    private static final String DATA_UPDATED = "update";

    /** DB Table : PTY_LOC_WRK */
    private static final String PTY_LOC_WRK = "PTY_LOC_WRK";

    /** DB Table : BILL_TO_CUST */
    private static final String BILL_TO_CUST = "BILL_TO_CUST";

    /** DB Table : SELL_TO_CUST */
    private static final String SELL_TO_CUST = "SELL_TO_CUST";

    /** DB Table : SHIP_TO_CUST */
    private static final String SHIP_TO_CUST = "SHIP_TO_CUST";

    /** RESPONSE TASK（Add） */
    private static final String RESPONSE_TASK_ADD = "Add";

    /** RESPONSE TASK（ReverseScreening） */
    private static final String RESPONSE_TASK_REVERSE_SCREENING = "ReverseScreening";

    /** RESPONSE TASK（OVERRIDE） */
    private static final String RESPONSE_TASK_OVERRIDE = "OVERRIDE";

    /** RESPONSE SERVICE TYPE（Embargo） */
    private static final String RESPONSE_SERVICE_TYPE_EMBARGO = "Embargo";

    /** RESPONSE SERVICE TYPE（DeniedParty） */
    private static final String RESPONSE_SERVICE_TYPE_DENIED_PARTY = "DeniedParty";

    /** termineted code */
    private TERM_CD termCd;

    /** update count PTY_LOC_WRK */
    private int updCountPtyLocWrk = 0;

    // 2016/05/20 S21_CSA[mod] Delete Start
    // /** update count BILL_TO_CUST */
    // private int updCountBillToCust = 0;
    //
    // /** update count SELL_TO_CUST */
    // private int updCountSellToCust = 0;
    // 2016/05/20 S21_CSA[mod] Delete End

    /** update count SHIP_TO_CUST */
    private int updCountShipToCust = 0;

    /** commit update count PTY_LOC_WRK */
    private int updCommitCountPtyLocWrk = 0;

    /** commit update count BILL_TO_CUST */
    private int updCommitCountBillToCust = 0;

    /** commit update count SELL_TO_CUST */
    private int updCommitCountSellToCust = 0;

    /** commit update count SHIP_TO_CUST */
    private int updCommitCountShipToCust = 0;

    /** Interface ID */
    private String interfaceId = null;

    /**
     * Main method
     * @param args String[]
     */
    public static void main(String[] args) {

        // Initialize S21BatchMain
        new NMAB492001().executeBatch(NMAB492001.class.getSimpleName());

    }

    /**
     * Initialize process
     */
    protected void initRoutine() {

        // Initialize variable
        this.termCd = TERM_CD.NORMAL_END;

        // argument check(glblCmpyCd)
        if (!ZYPCommonFunc.hasValue(this.getGlobalCompanyCode())) {
            throw new S21AbendException(ARG_ERR, new String[] {"GlobalCompanyCode" });
        }

        // get InterfaceId(NMAI2080_xx)
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            // INTERFACE ID is no data
            throw new S21AbendException(INTFC_ID_ERR);
        }

        EZDDebugOutput.println(1, "----- [ DEBUG PRINT ] ----- interfaceId : " + this.interfaceId, this);

    }

    /**
     * Main process
     */
    protected void mainRoutine() {

        // Execute application process
        doProcess();

    }

    /**
     * Application process
     */
    private void doProcess() {

        // // Request Integration Record
        // S21IntegrationTransporter s21itp = new
        // S21IntegrationTransporter();
        //        
        // int s21itpRet = s21itp.requestFTPget(this.interfaceId);
        //        
        // if (S21IntegrationTransporter.TRANSPORT_SUCCESS !=
        // s21itpRet) {
        // this.termCd = TERM_CD.WARNING_END;
        // S21InfoLogOutput.printlnv(REQ_FTPGET_ERR, s21itpRet,
        // this.interfaceId);
        // return;
        // }

        // Get transactionId
        S21TransactionTableAccessor s21tta = new S21TransactionTableAccessor();
        BigDecimal[] transactionIdList = (BigDecimal[]) s21tta.getIntegrationRecordInitAsc(this.interfaceId);

        if (transactionIdList.length == 0) {
            return;
        }

        for (int idx = 0; idx < transactionIdList.length; idx++) {
            EZDDebugOutput.println(1, "----- [ DEBUG PRINT ] ----- (doProcess) transactionIdList:" + transactionIdList[idx], this);

            // get interface_table record
            NMAI2080_02TMsg condIf = new NMAI2080_02TMsg();
            NMAI2080_02TMsg recIf;

            condIf.setSQLID("001");
            condIf.setConditionValue("interfaceId01", interfaceId);
            condIf.setConditionValue("transactionId01", transactionIdList[idx]);
            EZDTBLAccessorForBatch accessorIf = new EZDTBLAccessorForBatch(condIf);

            for (recIf = (NMAI2080_02TMsg) accessorIf.next(); recIf != null; recIf = (NMAI2080_02TMsg) accessorIf.next()) {

                // 2016/05/20 S21_CSA[mod] Start
                // // get pty_loc_pk of PTY
                // PTYTMsg ptyRslt =
                // getPtyRecord(recIf.serviceUserdefinedL100If.getValue());
                // if (ptyRslt == null ||
                // !ZYPCommonFunc.hasValue(ptyRslt.ptyLocPk.getValue()))
                // {
                // continue;
                // }

                SHIP_TO_CUSTTMsg shipRslt = getShipToCustRecord(recIf.serviceUserdefinedL100If.getValue());
                if (shipRslt == null) {
                    continue;
                }
                // 2016/05/20 S21_CSA[mod] End

                // get PTY_LOC_WRK (Record Lock)
                PTY_LOC_WRKTMsg ptyLocWrkRslt = getPtyLocWrkRecord(shipRslt); // 2016/05/20 S21_CSA[mod]
                if (ptyLocWrkRslt == null || !ZYPCommonFunc.hasValue(ptyLocWrkRslt.ptyLocPk.getValue())) {
                    continue;
                }

                // Get response type
                NMAI2080_01TMsg tMsgNMAI2080_01 = getNMAI2080_01ByNMAI2080_02(recIf);
                if (tMsgNMAI2080_01 == null) {
                    EZDDebugOutput.println(1, "----- [ DEBUG PRINT ] ----- Response task type is not found", this);
                    continue;
                }

                // Check response error
                NMAI2080_04TMsg tMsgNMAI2080_04 = getNMAI2080_04ByNMAI2080_02(recIf);
                if (tMsgNMAI2080_04 != null) {
                    if (ZYPCommonFunc.hasValue(tMsgNMAI2080_04.errorMessageL3500If)) {
                        debugOutputResponseError(recIf, tMsgNMAI2080_04);
                        continue;
                    }
                }

                // Set check results
                if (RESPONSE_TASK_ADD.equals(tMsgNMAI2080_01.adminTaskL50If.getValue()) || RESPONSE_TASK_REVERSE_SCREENING.equals(tMsgNMAI2080_01.adminTaskL50If.getValue())) {

                    if (RESPONSE_SERVICE_TYPE_EMBARGO.equals(recIf.serviceTypeL50If.getValue())) {

                        if (ZYPCommonFunc.hasValue(tMsgNMAI2080_04.embargoL1If)) {
                            ZYPEZDItemValueSetter.setValue(ptyLocWrkRslt.embgoFlg, tMsgNMAI2080_04.embargoL1If.getValue());
                        }

                    } else if (RESPONSE_SERVICE_TYPE_DENIED_PARTY.equals(recIf.serviceTypeL50If.getValue())) {

                        ZYPEZDItemValueSetter.setValue(ptyLocWrkRslt.dplStsCd, DPL_STS.FAIL);
                        ZYPEZDItemValueSetter.setValue(ptyLocWrkRslt.dplRsnTxt, getDplRsnTxt(recIf, ptyLocWrkRslt.getAttr("dplRsnTxt").getDigit()));

                    } else {
                        EZDDebugOutput.println(1, "----- [ DEBUG PRINT ] ----- Unknown serive type for task 'Add'/'ReverseScreening':" + recIf.serviceTypeL50If.getValue(), this);
                        continue;
                    }

                } else if (RESPONSE_TASK_OVERRIDE.equals(tMsgNMAI2080_01.adminTaskL50If.getValue())) {

                    if (RESPONSE_SERVICE_TYPE_DENIED_PARTY.equals(recIf.serviceTypeL50If.getValue())) {

                        ZYPEZDItemValueSetter.setValue(ptyLocWrkRslt.dplStsCd, DPL_STS.PASS);
                        ptyLocWrkRslt.dplRsnTxt.clear();

                    } else {
                        EZDDebugOutput.println(1, "----- [ DEBUG PRINT ] ----- Unknown serive type for task 'OVERRIDE':" + recIf.serviceTypeL50If.getValue(), this);
                        continue;
                    }

                } else {
                    EZDDebugOutput.println(1, "----- [ DEBUG PRINT ] ----- Unknown RDPS response admin task:" + tMsgNMAI2080_01.adminTaskL50If.getValue(), this);
                    continue;
                }

                // Save check results to S21 Master DB
                updateDplCheckResult(ptyLocWrkRslt, tMsgNMAI2080_01);

                commit();

                this.updCommitCountPtyLocWrk = this.updCountPtyLocWrk;
                // 2016/05/20 S21_CSA[mod] Delete Start
                // this.updCommitCountBillToCust =
                // this.updCountBillToCust;
                // this.updCommitCountSellToCust =
                // this.updCountSellToCust;
                // 2016/05/20 S21_CSA[mod] Delete End
                this.updCommitCountShipToCust = this.updCountShipToCust;

            }

            accessorIf.releaseResource();

            // INTEGRATION_TABLE process end
            s21tta.endIntegrationProcess(this.interfaceId, transactionIdList[idx]);

        }
    }

    private String getDplLastScreenedDate(NMAI2080_01TMsg tMsgNMAI2080_01) {
        if (ZYPCommonFunc.hasValue(tMsgNMAI2080_01.adminResponsedateL50If)) {
            String responseDate = tMsgNMAI2080_01.adminResponsedateL50If.getValue();
            // Date format of the response date: ISO 8601
            // [ccyy-mm-ddThh:mm:ss±hhmm]
            if (responseDate.length() >= 10) {
                return responseDate.substring(0, 4) + responseDate.substring(5, 7) + responseDate.substring(8, 10);
            }
        }
        return "";
    }

    private String getDplRsnTxt(NMAI2080_02TMsg tMsgNMAI2080_02, int maxLenOfDplRsnTxt) {
        NMAI2080_03TMsgArray tMsgArray = getNMAI2080_03ArrayByNMAI2080_02(tMsgNMAI2080_02);
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < tMsgArray.length(); i++) {
            NMAI2080_03TMsg tMsg = tMsgArray.no(i);
            if (i > 0) {
                sb.append(",");
            }
            sb.append(tMsg.deniedpartyWordsL300If.getValue());
        }

        if (sb.length() > maxLenOfDplRsnTxt) {
            return sb.substring(0, maxLenOfDplRsnTxt);
        }

        return sb.toString();
    }

    private NMAI2080_01TMsg getNMAI2080_01ByNMAI2080_02(NMAI2080_02TMsg tMsgNMAI2080_02) {
        NMAI2080_01TMsg tMsg = new NMAI2080_01TMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("interfaceId01", tMsgNMAI2080_02.interfaceId.getValue());
        tMsg.setConditionValue("transactionId01", tMsgNMAI2080_02.transactionId.getValue());
        tMsg.setConditionValue("segmentId01", tMsgNMAI2080_02.segmentId.getValue());
        NMAI2080_01TMsgArray tMsgArray = (NMAI2080_01TMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0);
        }
        return null;
    }

    private NMAI2080_03TMsgArray getNMAI2080_03ArrayByNMAI2080_02(NMAI2080_02TMsg tMsgNMAI2080_02) {
        NMAI2080_03TMsg tMsg = new NMAI2080_03TMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("interfaceId01", tMsgNMAI2080_02.interfaceId.getValue());
        tMsg.setConditionValue("transactionId01", tMsgNMAI2080_02.transactionId.getValue());
        tMsg.setConditionValue("segmentId01", tMsgNMAI2080_02.segmentId.getValue());
        tMsg.setConditionValue("unitId01", tMsgNMAI2080_02.unitId.getValue());
        NMAI2080_03TMsgArray tMsgArray = (NMAI2080_03TMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        return tMsgArray;
    }

    private NMAI2080_04TMsg getNMAI2080_04ByNMAI2080_02(NMAI2080_02TMsg tMsgNMAI2080_02) {
        NMAI2080_04TMsg tMsg = new NMAI2080_04TMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("interfaceId01", tMsgNMAI2080_02.interfaceId.getValue());
        tMsg.setConditionValue("transactionId01", tMsgNMAI2080_02.transactionId.getValue());
        tMsg.setConditionValue("segmentId01", tMsgNMAI2080_02.segmentId.getValue());
        tMsg.setConditionValue("unitId01", tMsgNMAI2080_02.unitId.getValue());
        NMAI2080_04TMsgArray tMsgArray = (NMAI2080_04TMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0);
        }
        return null;
    }

    // 2016/05/20 S21_CSA[mod] Start
    // /**
    // * get pty_loc_pk of PTY
    // */
    // private PTYTMsg getPtyRecord(String ptyCd) {
    //
    // PTYTMsg ptyRec = new PTYTMsg();
    // PTYTMsg ptyRslt = new PTYTMsg();
    //
    // // get item attribute of ptyCd property in PTYTMsg
    // EZDItemAttribute ptyCdAttr = ptyRec.getAttr("ptyCd");
    // int digit = ptyCdAttr.getDigit();
    // if (ptyCd.length() > digit) {
    // return ptyRslt;
    // }
    //
    // ptyRec.glblCmpyCd.setValue(this.getGlobalCompanyCode());
    // ptyRec.ptyCd.setValue(ptyCd);
    // ptyRec.locGrpCd.setValue(LOC_GRP.CUSTOMER);
    //
    // ptyRslt = (PTYTMsg) S21FastTBLAccessor.findByKey(ptyRec);
    // return ptyRslt;
    //
    // }
    /**
     * Get pty_loc_pk of SHIP_TO_CUST
     */
    private SHIP_TO_CUSTTMsg getShipToCustRecord(String ptyCd) {

        SHIP_TO_CUSTTMsg shipCondRec = new SHIP_TO_CUSTTMsg();

        shipCondRec.setSQLID("051");
        shipCondRec.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        shipCondRec.setConditionValue("shipToCustCd01", ptyCd);
        shipCondRec.setConditionValue("locGrpCd01", LOC_GRP.CUSTOMER);

        SHIP_TO_CUSTTMsgArray shipTMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipCondRec);

        if (shipTMsgArray == null || shipTMsgArray.getValidCount() == 0 || !ZYPCommonFunc.hasValue(shipTMsgArray.no(0).ptyLocPk)) {
            return null;
        }
        return shipTMsgArray.no(0);
    }

    // 2016/05/20 S21_CSA[mod] End

    /**
     * get PTY_LOC_WRK (Record Lock)
     */
    // 2016/05/20 S21_CSA[mod] Start
    // private PTY_LOC_WRKTMsg getPtyLocWrkRecord(PTYTMsg ptyRslt) {
    private PTY_LOC_WRKTMsg getPtyLocWrkRecord(SHIP_TO_CUSTTMsg ptyRslt) {
    // 2016/05/20 S21_CSA[mod] End

        PTY_LOC_WRKTMsg ptyLocWrkRec = new PTY_LOC_WRKTMsg();
        PTY_LOC_WRKTMsg ptyLocWrkRslt;

        ptyLocWrkRec.glblCmpyCd.setValue(this.getGlobalCompanyCode());
        ptyLocWrkRec.ptyLocPk.setValue(ptyRslt.ptyLocPk.getValue());

        ptyLocWrkRslt = (PTY_LOC_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdate(ptyLocWrkRec);
        return ptyLocWrkRslt;

    }

    /**
     * Update of result DPL CHECK
     */
    private void updateDplCheckResult(PTY_LOC_WRKTMsg ptyLocWrkRslt, NMAI2080_01TMsg tMsgNMAI2080_01) {

        // QC 4070 <Y.Chen 20100224>
        // // get system date+time
        // String systemDateTm =
        // ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");
        //        
        // // update PTY_LOC_WRK
        // ZYPEZDItemValueSetter.setValue(ptyLocWrkRslt.dplRspDtTmTs,
        // systemDateTm);
        ZYPEZDItemValueSetter.setValue(ptyLocWrkRslt.dplRspDtTmTs, getDplLastScreenedDate(tMsgNMAI2080_01));

        S21FastTBLAccessor.update(ptyLocWrkRslt);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(ptyLocWrkRslt.getReturnCode())) {
            outputProcessCnt();
            throw new S21AbendException(DB_ACCESS_ERR, new String[] {PTY_LOC_WRK, DATA_UPDATED });
        }
        this.updCountPtyLocWrk++;

        // 2016/05/20 S21_CSA[mod] Delete Start
        // // update BILL_TO_CUST
        // BILL_TO_CUSTTMsg condBill = new BILL_TO_CUSTTMsg();
        // condBill.setSQLID("044");
        // condBill.setConditionValue("glblCmpyCd01",
        // this.getGlobalCompanyCode());
        // condBill.setConditionValue("ptyLocPk01",
        // ptyLocWrkRslt.ptyLocPk.getValue());
        // BILL_TO_CUSTTMsgArray updtBill = (BILL_TO_CUSTTMsgArray)
        // EZDTBLAccessor.findByCondition(condBill);
        // for (int i = 0; i < updtBill.length(); i++) {
        // ZYPEZDItemValueSetter.setValue(updtBill.no(i).dplStsCd,
        // ptyLocWrkRslt.dplStsCd);
        // ZYPEZDItemValueSetter.setValue(updtBill.no(i).dplRsnTxt,
        // ptyLocWrkRslt.dplRsnTxt);
        // ZYPEZDItemValueSetter.setValue(updtBill.no(i).dplRspDtTmTs,
        // ptyLocWrkRslt.dplRspDtTmTs);
        // ZYPEZDItemValueSetter.setValue(updtBill.no(i).embgoFlg,
        // ptyLocWrkRslt.embgoFlg);
        //
        // S21FastTBLAccessor.update(updtBill.no(i));
        // if
        // (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updtBill.no(i).getReturnCode()))
        // {
        // outputProcessCnt();
        // throw new S21AbendException(DB_ACCESS_ERR, new String[]
        // {BILL_TO_CUST, DATA_UPDATED});
        // }
        // this.updCountBillToCust++;
        // }
        //
        // // update SELL_TO_CUST
        // SELL_TO_CUSTTMsg condSell = new SELL_TO_CUSTTMsg();
        // condSell.setSQLID("028");
        // condSell.setConditionValue("glblCmpyCd01",
        // this.getGlobalCompanyCode());
        // condSell.setConditionValue("ptyLocPk01",
        // ptyLocWrkRslt.ptyLocPk.getValue());
        // SELL_TO_CUSTTMsgArray updtSell = (SELL_TO_CUSTTMsgArray)
        // EZDTBLAccessor.findByCondition(condSell);
        // for (int i = 0; i < updtSell.length(); i++) {
        // ZYPEZDItemValueSetter.setValue(updtSell.no(i).dplStsCd,
        // ptyLocWrkRslt.dplStsCd);
        // ZYPEZDItemValueSetter.setValue(updtSell.no(i).dplRsnTxt,
        // ptyLocWrkRslt.dplRsnTxt);
        // ZYPEZDItemValueSetter.setValue(updtSell.no(i).dplRspDtTmTs,
        // ptyLocWrkRslt.dplRspDtTmTs);
        // ZYPEZDItemValueSetter.setValue(updtSell.no(i).embgoFlg,
        // ptyLocWrkRslt.embgoFlg);
        //
        // S21FastTBLAccessor.update(updtSell.no(i));
        // if
        // (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updtSell.no(i).getReturnCode()))
        // {
        // outputProcessCnt();
        // throw new S21AbendException(DB_ACCESS_ERR, new String[]
        // {SELL_TO_CUST, DATA_UPDATED});
        // }
        // this.updCountSellToCust++;
        // }
        // 2016/05/20 S21_CSA[mod] Delete End

        // update SHIP_TO_CUST
        SHIP_TO_CUSTTMsg condShip = new SHIP_TO_CUSTTMsg();
        condShip.setSQLID("027");
        condShip.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        condShip.setConditionValue("ptyLocPk01", ptyLocWrkRslt.ptyLocPk.getValue());
        SHIP_TO_CUSTTMsgArray updtShip = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condShip);
        for (int i = 0; i < updtShip.length(); i++) {
            ZYPEZDItemValueSetter.setValue(updtShip.no(i).dplStsCd, ptyLocWrkRslt.dplStsCd);
            ZYPEZDItemValueSetter.setValue(updtShip.no(i).dplRsnTxt, ptyLocWrkRslt.dplRsnTxt);
            ZYPEZDItemValueSetter.setValue(updtShip.no(i).dplRspDtTmTs, ptyLocWrkRslt.dplRspDtTmTs);
            ZYPEZDItemValueSetter.setValue(updtShip.no(i).embgoFlg, ptyLocWrkRslt.embgoFlg);

            S21FastTBLAccessor.update(updtShip.no(i));
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updtShip.no(i).getReturnCode())) {
                outputProcessCnt();
                throw new S21AbendException(DB_ACCESS_ERR, new String[] {SHIP_TO_CUST, DATA_UPDATED });
            }
            this.updCountShipToCust++;
        }

    }

    /**
     * Output Process record count process
     */
    private void outputProcessCnt() {
        S21InfoLogOutput.printlnv(ZZBM0009I, PTY_LOC_WRK, DATA_UPDATED, this.updCommitCountPtyLocWrk);
        S21InfoLogOutput.printlnv(ZZBM0009I, BILL_TO_CUST, DATA_UPDATED, this.updCommitCountBillToCust);
        S21InfoLogOutput.printlnv(ZZBM0009I, SELL_TO_CUST, DATA_UPDATED, this.updCommitCountSellToCust);
        S21InfoLogOutput.printlnv(ZZBM0009I, SHIP_TO_CUST, DATA_UPDATED, this.updCommitCountShipToCust);
    }

    private void debugOutputResponseError(NMAI2080_02TMsg tMsgNMAI2080_02, NMAI2080_04TMsg tMsgNMAI2080_04) {
        EZDDebugOutput.println(1, "RDPS Response Error:", this);
        EZDDebugOutput.println(1, "[RDPS InterfaceId]:" + tMsgNMAI2080_02.interfaceId.getValue(), this);
        EZDDebugOutput.println(1, "[RDPS TransactionId]:" + tMsgNMAI2080_02.transactionId.getValue(), this);
        EZDDebugOutput.println(1, "[RDPS PtyCd]:" + tMsgNMAI2080_02.serviceUserdefinedL100If.getValue(), this);
        EZDDebugOutput.println(1, "[RDPS ErrSrc]:" + tMsgNMAI2080_04.errorSourceL350If.getValue(), this);
        EZDDebugOutput.println(1, "[RDPS ErrTp]:" + tMsgNMAI2080_04.errorTypeL20If.getValue(), this);
        EZDDebugOutput.println(1, "[RDPS ErrCd]:" + tMsgNMAI2080_04.errorCodeL20If.getValue(), this);
        EZDDebugOutput.println(1, "[RDPS ErrMsg]:" + tMsgNMAI2080_04.errorMessageL3500If.getValue(), this);
    }

    /**
     * Terminate process
     */
    protected void termRoutine() {

        // processed record's count output
        outputProcessCnt();

        // termineted code set
        setTermState(this.termCd);

    }

}
