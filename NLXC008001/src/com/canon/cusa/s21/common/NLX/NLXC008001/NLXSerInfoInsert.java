/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC008001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.SER_EVENT_LGCY_PRMO_DFNTMsg;
import business.db.SER_EVENT_STSTMsg;
import business.db.SER_EVENT_STSTMsgArray;
import business.db.SER_EVENT_STS_WRKTMsg;
import business.db.SER_NUM_ERRTMsg;

import com.canon.cusa.s21.common.NLX.NLXC007001.NLXSerInfoCheck;
import com.canon.cusa.s21.common.NLX.NLXC014001.NLXC014001;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_PROC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.batch.S21ResultSetMapper;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * RegisterSerialData
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/23/2009   Fujitsu         M.Irisawa       Create          N/A
 * 01/11/2009   Fujitsu         T.Motoyama      Update
 * 04/05/2010   Fujitsu         S.Yoshida       Update          Change SER_EVENT_STS
 * 10/06/2010   CSAI            D.Fukaya        Update          
 * 02/10/2011   CSAI            D.Fukaya        Update          1482
 * 02/11/2011   CSAI            D.Fukaya        Update          1514
 * 07/01/2011   CSAI            D.Fukaya        Update          L201
 * 11/09/2011   CSAI            M.Takahashi     Update          SR#362045
 * 02/23/2012   CSAI            N.Sasaki        Update          ITG#382732
 *</pre>
 */
public class NLXSerInfoInsert implements NLXC008001Constant {

    /** SSM */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** Create S21SsmLowLevelCodingClient instance. */
    private S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    /**
     * ownerID having SetMdseCd
     */
    private static enum ownerIDSetMdse {

        /**  */
        OWNER_ID_DC("01"),
        /**  */
        OWNER_ID_ROSS("03"),
        /**  */
        OWNER_ID_PROCUREMENT("08");

        /**  */
        private String key;

        private ownerIDSetMdse(String key) {
            this.key = key;
        }

        private String getKey() {
            return key;
        }
    }

    /**
     * ownerID having ShpgOrder Info
     */
    private static enum ownerIDShpgOrd {

        /**  */
        OWNER_ID_DC("01"),
        /**  */
        OWNER_ID_ROSS("03"),
        /**  */
        OWNER_ID_PROCUREMENT("05");

        /**  */
        private String key;

        private ownerIDShpgOrd(String key) {
            this.key = key;
        }

        private String getKey() {
            return key;
        }
    }

    /**
     * cnaRecord
     */
    private static enum cnaRecord {

        /**  */
        CNA_EVENT_CD("211"),
        /**  */
        CNA_SYS_NAME("CNA"),
        /**  */
        CNA_OWNER_ID("00");

        /**  */
        private String key;

        private cnaRecord(String key) {
            this.key = key;
        }

        private String getKey() {
            return key;
        }
    }

    /** Serial Flag Information */
    private Map<String, String[]> serFlagInfoList = null;

    /**
     * insertSerInfo
     * @param serEventStsWrkTMsg SER_EVENT_STS_WRKTMsg
     * @param errList List<Map<String, String>>
     * @param serFlagInfoList Map<String, String[]>
     * @param serNumErr SER_NUM_ERRTMsg
     * @param serErrListForMail  List<Map<String, String>>
     * @return Map<String, Integer>
     * @throws SQLException SQLException
     */
    public Map<String, Integer> insertSerInfo(SER_EVENT_STS_WRKTMsg serEventStsWrkTMsg
                                            , List<Map<String, String>> errList
                                            , Map<String, String[]> serFlagInfoList
                                            , SER_NUM_ERRTMsg serNumErr
                                            , List<Map<String, String>> serErrListForMail) throws SQLException {

        if (serNumErr == null) {

            serNumErr = new SER_NUM_ERRTMsg();
        }
        if (serErrListForMail == null) {

            serErrListForMail = new ArrayList<Map<String, String>>();
        }

        Map<String, Integer> procCnt = new HashMap<String, Integer>();
        int procCntErr100Tmp = 0;
        int procCntErr200Tmp = 0;

        this.serFlagInfoList = serFlagInfoList;

        //SER_EVENT_STSTMsg condSerEventStsOLDTMsg = null;
        //SER_EVENT_STSTMsgArray serEventStsOLDTMsgArray = null;
        //SER_EVENT_STSTMsg condSerEventStsLANTMsg = null;
        //SER_EVENT_STSTMsgArray serEventStsLANTMsgArray = null;
        SER_EVENT_STSTMsg valSerEventStsTMsg = new SER_EVENT_STSTMsg();

        // Set SER_EVENT_STSTMsg from SER_EVENT_STS_WRKTMsg (input parameter).
        setSerEventStsTMsgFromWrk(serEventStsWrkTMsg, valSerEventStsTMsg);

        checkSerNum(serEventStsWrkTMsg, serNumErr, serErrListForMail, valSerEventStsTMsg);

        // Search SER_EVENT_STS.
        if (ZYPCommonFunc.hasValue(serEventStsWrkTMsg.licAccsNum)) {
            // 2.3.1
            SER_EVENT_STSTMsg condSerEventStsLANTMsg = new SER_EVENT_STSTMsg();
            setSerEventStsTMsgForLan(condSerEventStsLANTMsg, serEventStsWrkTMsg);
            // 2.3.2
            SER_EVENT_STSTMsgArray serEventStsLANTMsgArray = (SER_EVENT_STSTMsgArray) S21ApiTBLAccessor.findByCondition(condSerEventStsLANTMsg);

            // Data does not exist in Serial Event Status.
            // Set Serial Error Status is LAN.  
            // Even if serNumErrFlg is not used, is it OK? serErrStsCd is overwrited
            if (serEventStsLANTMsgArray == null || serEventStsLANTMsgArray.getValidCount() <= 0) {

                valSerEventStsTMsg.serErrStsCd.setValue(NLXSceConst.SER_ERR_STS_CD_LICENSE_ACCESS_NUM_NOT_FOUND);
                String[] msgList = new String[] {SER_EVENT_STS, "LIC_ACCS_NUM", serEventStsWrkTMsg.licAccsNum.getValue() };
                S21InfoLogOutput.println(NLBM1039E, msgList);

                Map<String, String> errMap = new HashMap<String, String>();
                errMap.put(NLXSerInfoCheck.SYS_ID, serEventStsWrkTMsg.serOwnrId.getValue());
                errMap.put(NLXSerInfoCheck.ERR_MSG, S21MessageFunc.clspGetMessage(NLBM1039E, msgList));
                errList.add(errMap);
                procCntErr100Tmp++;
            }
        }

        // Work on Old Serial Number case
        if (ZYPCommonFunc.hasValue(serEventStsWrkTMsg.oldSerNum)) {
            String[] serFlagInfo = null;
            String serProcTp = null;
            String serEventCd = serEventStsWrkTMsg.serEventCd.getValue();
            if (serFlagInfoList.containsKey(serEventCd)) {
                serFlagInfo = serFlagInfoList.get(serEventCd);
                serProcTp = serFlagInfo[0];
            }

            // 2.2.1
            boolean oldSerEventFlg = false;
            SER_EVENT_STSTMsg condSerEventStsOLDTMsg = new SER_EVENT_STSTMsg();

            if (SER_PROC_TP.SERIAL_REPLACE_ALL.equals(serProcTp)) {
                setSerEventStsTMsgForSql001(serEventStsWrkTMsg, condSerEventStsOLDTMsg);
                oldSerEventFlg = true;
            } else if (SER_PROC_TP.SERIAL_REPLACE_SAME_OWNER_ID.equals(serProcTp) || SER_PROC_TP.DEALER_REPLACE.equals(serProcTp)) {
                setSerEventStsTMsgForSql002(serEventStsWrkTMsg, condSerEventStsOLDTMsg);
                oldSerEventFlg = true;
            }

            // 2.2.2
            if (oldSerEventFlg) {
                SER_EVENT_STSTMsgArray serEventStsOLDTMsgArray = (SER_EVENT_STSTMsgArray) S21ApiTBLAccessor.findByCondition(condSerEventStsOLDTMsg);

                // 2.5
                if (serEventStsOLDTMsgArray != null && serEventStsOLDTMsgArray.getValidCount() > 0) {

                    if (!SER_PROC_TP.DEALER_REPLACE.equals(serProcTp)) {

                        String serLocGrp = valSerEventStsTMsg.serLocGrpCd.getValue();
                        // For 3rd party vendor drop Serial# change
                        if (SER_LOC_GRP.DEALER_OR_RETAILER_OR_DISTRIBUTOR.equals(serLocGrp) && (CHNGE_SER_281).equals(serEventCd)) {
                            // Do nothing
                        } else {
                            for (int j = 0; j < serEventStsOLDTMsgArray.getValidCount(); j++) {
                                SER_EVENT_STSTMsg serEventStsOLDTMsg = (SER_EVENT_STSTMsg) serEventStsOLDTMsgArray.get(j);

                                // Remove old serial record.
                                SER_EVENT_STSTMsg remSerEventStsOLD = new SER_EVENT_STSTMsg();
                                setSerEventStsTMsgForPk(serEventStsOLDTMsg, remSerEventStsOLD);
                                S21ApiTBLAccessor.logicalRemove(remSerEventStsOLD);

                                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(remSerEventStsOLD.getReturnCode())) {
                                    throw new S21AbendException(NLBM1019E, new String[] {SER_EVENT_STS, LOGICAL_REMOVE, remSerEventStsOLD.getReturnCode() });
                                }

                                // Insert serial record for old event
                                // info.
                                SER_EVENT_STSTMsg valSerEventStsOLDTMsg = new SER_EVENT_STSTMsg();
                                setSerEventStsTMsgForOld(serEventStsWrkTMsg, serEventStsOLDTMsg, valSerEventStsOLDTMsg);
                                S21ApiTBLAccessor.insert(valSerEventStsOLDTMsg);

                                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(valSerEventStsOLDTMsg.getReturnCode())) {
                                    throw new S21AbendException(NLBM1019E, new String[] {SER_EVENT_STS, NLXSceConst.PROC_INSERT, valSerEventStsOLDTMsg.getReturnCode() });
                                }
                            }
                        }
                    }

                // Data does not exist in Serial Event Status.
                } else {
                    // Even if serNumErrFlg is not used, is it OK? serErrStsCd is overwrited
                    if (!valSerEventStsTMsg.serErrStsCd.getValue().equals(NLXSceConst.SER_ERR_STS_CD_LICENSE_ACCESS_NUM_NOT_FOUND)) {
                        valSerEventStsTMsg.serErrStsCd.setValue(NLXSceConst.SER_ERR_STS_CD_OLD_SERIAL_NUM_NOT_FOUND);
                    }
                    String[] msgList = new String[] {SER_EVENT_STS, "OLD_SER_NUM", NLXC014001.nullToEmpty(serEventStsWrkTMsg.oldSerNum.getValue()) };
                    S21InfoLogOutput.println(NLBM1039E, msgList);

                    Map<String, String> errMap = new HashMap<String, String>();
                    errMap.put(NLXSerInfoCheck.SYS_ID, serEventStsWrkTMsg.serOwnrId.getValue());
                    errMap.put(NLXSerInfoCheck.ERR_MSG, S21MessageFunc.clspGetMessage(NLBM1039E, msgList));
                    errList.add(errMap);
                    procCntErr200Tmp++;
                }
            }
        }

        String serEventCd = serEventStsWrkTMsg.serEventCd.getValue();
        String serLocGrp = valSerEventStsTMsg.serLocGrpCd.getValue();
        boolean retCd = false;
        // For 3rd party vendor drop Serial# change
        if (SER_LOC_GRP.DEALER_OR_RETAILER_OR_DISTRIBUTOR.equals(serLocGrp) && (CHNGE_SER_281).equals(serEventCd)) {
            retCd = updateSerEventSts(valSerEventStsTMsg, serEventStsWrkTMsg, serErrListForMail);
        }
        // non 3rd party vendor drop Serial# change or update failed
        if (!retCd) {
            // Insert Serial Event Status from Work.
            S21ApiTBLAccessor.insert(valSerEventStsTMsg);
            createCopySerEventSts(valSerEventStsTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(valSerEventStsTMsg.getReturnCode())) {
                throw new S21AbendException(NLBM1019E, new String[] {SER_EVENT_STS, NLXSceConst.PROC_INSERT, valSerEventStsTMsg.getReturnCode() });
            }
        }

        // Update Serial Event Status Work is Processed.
        SER_EVENT_STS_WRKTMsg updateSerEventStsWrkTMsg = new SER_EVENT_STS_WRKTMsg();
        setSerEventStsWrkTMsgForUpd(serEventStsWrkTMsg, updateSerEventStsWrkTMsg);
        S21ApiTBLAccessor.updateSelectionField(updateSerEventStsWrkTMsg, new String[] {PROC_STS_CD });

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateSerEventStsWrkTMsg.getReturnCode())) {
            throw new S21AbendException(NLBM1019E, new String[] {//
                    NLXSceConst.TBL_SER_EVENT_STS_WRK, UPDATE_SELECTION_FIELD, updateSerEventStsWrkTMsg.getReturnCode() });
        }
        setReturnCode(procCnt, procCntErr100Tmp, procCntErr200Tmp);
        return procCnt;
    }

    /**
     * Set return Code.
     * 
     * @param procCnt
     * @param procCntErr100Tmp
     * @param procCntErr200Tmp
     */
    private void setReturnCode(Map<String, Integer> procCnt, int procCntErr100Tmp, int procCntErr200Tmp) {
        procCnt.put(CNT_ERR_100, procCntErr100Tmp);
        procCnt.put(CNT_ERR_200, procCntErr200Tmp);
    }

    /**
     * Set update conditions.
     * This is set update data.
     * 
     * @param tmpWrkTMsg
     * @param updateSerEventStsWrkTMsg
     */
    private void setSerEventStsWrkTMsgForUpd(SER_EVENT_STS_WRKTMsg tmpWrkTMsg, SER_EVENT_STS_WRKTMsg updateSerEventStsWrkTMsg) {
        updateSerEventStsWrkTMsg.glblCmpyCd.setValue(tmpWrkTMsg.glblCmpyCd.getValue());
        updateSerEventStsWrkTMsg.serEventStsWrkPk.setValue(NLXC014001.nullToZero(tmpWrkTMsg.serEventStsWrkPk.getValue()));
        updateSerEventStsWrkTMsg.procStsCd.setValue(PROC_STS.COMPLEATED);
    }

    /**
     * Set search conditions.
     * This is set insert data.
     * 
     * @param condSerEventStsLANTMsg
     * @param tmpWrkTMsg
     */
    private void setSerEventStsTMsgForLan(SER_EVENT_STSTMsg condSerEventStsLANTMsg, SER_EVENT_STS_WRKTMsg tmpWrkTMsg) {
        condSerEventStsLANTMsg.setSQLID(SQLID_SES_03);
        condSerEventStsLANTMsg.setConditionValue(GLBL_CMPY_CD_KEY, tmpWrkTMsg.glblCmpyCd.getValue());
        condSerEventStsLANTMsg.setConditionValue(SER_NUM_KEY, tmpWrkTMsg.licAccsNum.getValue());
    }

    /**
     * Set insert conditions.
     * This is set insert data.
     * 
     * @param tmpWrkTMsg
     * @param serEventStsOLDTMsg
     * @param valSerEventStsOLDTMsg
     */
    private void setSerEventStsTMsgForOld(SER_EVENT_STS_WRKTMsg tmpWrkTMsg, SER_EVENT_STSTMsg serEventStsOLDTMsg, SER_EVENT_STSTMsg valSerEventStsOLDTMsg) {

        String glblCmpyCd = NLXC014001.nullToEmpty(serEventStsOLDTMsg.glblCmpyCd.getValue());
        String serEventCd = NLXC014001.nullToEmpty(serEventStsOLDTMsg.serEventCd.getValue());

        valSerEventStsOLDTMsg.glblCmpyCd.setValue(glblCmpyCd);
        valSerEventStsOLDTMsg.serEventStsPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SER_EVENT_STS_SQ));
        valSerEventStsOLDTMsg.serOwnrId.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.serOwnrId.getValue()));
        valSerEventStsOLDTMsg.mdseCd.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.mdseCd.getValue()));
        valSerEventStsOLDTMsg.serNum.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.serNum.getValue()));
        valSerEventStsOLDTMsg.serEventTs.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.serEventTs.getValue()));
        valSerEventStsOLDTMsg.serLocGrpCd.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.serLocGrpCd.getValue()));
        valSerEventStsOLDTMsg.serEventCd.setValue(serEventCd);
        valSerEventStsOLDTMsg.trxCd.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.trxCd.getValue()));
        valSerEventStsOLDTMsg.trxRsnCd.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.trxRsnCd.getValue()));
        valSerEventStsOLDTMsg.cpoOrdTpCd.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.cpoOrdTpCd.getValue()));
        valSerEventStsOLDTMsg.fromLocCd.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.fromLocCd.getValue()));
        valSerEventStsOLDTMsg.fromLocNm.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.fromLocNm.getValue()));
        valSerEventStsOLDTMsg.toLocCd.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.toLocCd.getValue()));
        valSerEventStsOLDTMsg.toLocNm.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.toLocNm.getValue()));
        valSerEventStsOLDTMsg.licAccsNum.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.licAccsNum.getValue()));
        valSerEventStsOLDTMsg.oldSerNum.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.oldSerNum.getValue()));
        valSerEventStsOLDTMsg.chngSerNum.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.chngSerNum.getValue()));
        valSerEventStsOLDTMsg.prodLineProdCtrlCd.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.prodLineProdCtrlCd.getValue()));
        valSerEventStsOLDTMsg.keyInfoCd_01.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.keyInfoCd_01.getValue()));
        valSerEventStsOLDTMsg.keyInfoCd_02.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.keyInfoCd_02.getValue()));
        valSerEventStsOLDTMsg.keyInfoCd_03.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.keyInfoCd_03.getValue()));
        valSerEventStsOLDTMsg.keyInfoCd_04.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.keyInfoCd_04.getValue()));
        valSerEventStsOLDTMsg.keyInfoCd_05.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.keyInfoCd_05.getValue()));
        valSerEventStsOLDTMsg.keyInfoCd_06.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.keyInfoCd_06.getValue()));
        valSerEventStsOLDTMsg.keyInfoCd_07.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.keyInfoCd_07.getValue()));
        valSerEventStsOLDTMsg.keyInfoCd_08.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.keyInfoCd_08.getValue()));
        valSerEventStsOLDTMsg.keyInfoCd_09.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.keyInfoCd_09.getValue()));
        valSerEventStsOLDTMsg.keyInfoCd_10.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.keyInfoCd_10.getValue()));
        valSerEventStsOLDTMsg.serCitsOutStsCd.setValue(PROC_STS.IN_COMPLETED);
        valSerEventStsOLDTMsg.serCnaOutStsCd.setValue(PROC_STS.IN_COMPLETED);
        valSerEventStsOLDTMsg.serDwhOutStsCd.setValue(PROC_STS.IN_COMPLETED);
        valSerEventStsOLDTMsg.serErrStsCd.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.serErrStsCd.getValue()));
        valSerEventStsOLDTMsg.serProcIntfcFlg.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.serProcIntfcFlg.getValue()));
        valSerEventStsOLDTMsg.setMdseCd.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.setMdseCd.getValue()));
        valSerEventStsOLDTMsg.itemRefTxt.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.itemRefTxt.getValue()));
        valSerEventStsOLDTMsg.billToCustCd.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.billToCustCd.getValue()));
        valSerEventStsOLDTMsg.sellToCustCd.setValue(NLXC014001.nullToEmpty(serEventStsOLDTMsg.sellToCustCd.getValue()));

        SER_EVENT_LGCY_PRMO_DFNTMsg selpdTMsg = new SER_EVENT_LGCY_PRMO_DFNTMsg();
        selpdTMsg.glblCmpyCd.setValue(glblCmpyCd);
        selpdTMsg.serEventCd.setValue(serEventCd);
        selpdTMsg = (SER_EVENT_LGCY_PRMO_DFNTMsg) S21ApiTBLAccessor.findByKey(selpdTMsg);

        if (selpdTMsg != null) {
            valSerEventStsOLDTMsg.intfcSysNm.setValue(selpdTMsg.intfcSysNm.getValue());
        }
    }

    /**
     * Set remove conditions.
     * This is set pk from old serial record for remove.
     * 
     * @param serEventStsOLDTMsg
     * @param remSerEventStsOLD
     */
    private void setSerEventStsTMsgForPk(SER_EVENT_STSTMsg serEventStsOLDTMsg, SER_EVENT_STSTMsg remSerEventStsOLD) {
        remSerEventStsOLD.glblCmpyCd.setValue(serEventStsOLDTMsg.glblCmpyCd.getValue());
        remSerEventStsOLD.serEventStsPk.setValue(NLXC014001.nullToZero(serEventStsOLDTMsg.serEventStsPk.getValue()));
    }

    /**
     * Set search conditions. for SQL002
     * This is set old serial number and owner id from work.
     * 
     * @param tmpWrkTMsg
     * @param condSerEventStsOLDTMsg
     */
    private void setSerEventStsTMsgForSql002(SER_EVENT_STS_WRKTMsg tmpWrkTMsg, SER_EVENT_STSTMsg condSerEventStsOLDTMsg) {
        condSerEventStsOLDTMsg.setSQLID(SQLID_SES_02);
        condSerEventStsOLDTMsg.setConditionValue(GLBL_CMPY_CD_KEY, tmpWrkTMsg.glblCmpyCd.getValue());
        condSerEventStsOLDTMsg.setConditionValue(MDSE_CD_KEY, tmpWrkTMsg.mdseCd.getValue());
        condSerEventStsOLDTMsg.setConditionValue(SER_NUM_KEY, tmpWrkTMsg.oldSerNum.getValue());
        condSerEventStsOLDTMsg.setConditionValue(SER_OWNER_ID_KEY, tmpWrkTMsg.serOwnrId.getValue());
    }

    /**
     * Set search conditions. for SQL001
     * This is set old serial number from work.
     * 
     * @param tmpWrkTMsg
     * @param condSerEventStsOLDTMsg
     */
    private void setSerEventStsTMsgForSql001(SER_EVENT_STS_WRKTMsg tmpWrkTMsg, SER_EVENT_STSTMsg condSerEventStsOLDTMsg) {
        condSerEventStsOLDTMsg.setSQLID(SQLID_SES_01);
        condSerEventStsOLDTMsg.setConditionValue(GLBL_CMPY_CD_KEY, tmpWrkTMsg.glblCmpyCd.getValue());
        condSerEventStsOLDTMsg.setConditionValue(MDSE_CD_KEY, tmpWrkTMsg.mdseCd.getValue());
        condSerEventStsOLDTMsg.setConditionValue(SER_NUM_KEY, tmpWrkTMsg.oldSerNum.getValue());
    }

    /**
     * Set insert conditions.
     * This is set insert data.
     * 
     * @param tmpWrkTMsg
     * @param valSerEventStsTMsg
     */
    private void setSerEventStsTMsgFromWrk(SER_EVENT_STS_WRKTMsg tmpWrkTMsg, SER_EVENT_STSTMsg valSerEventStsTMsg) throws SQLException {

        String glblCmpyCd = NLXC014001.nullToEmpty(tmpWrkTMsg.glblCmpyCd.getValue());
        String serEventCd = NLXC014001.nullToEmpty(tmpWrkTMsg.serEventCd.getValue());
        String serOwnrId = NLXC014001.nullToEmpty(tmpWrkTMsg.serOwnrId.getValue());

        if (ownerIDShpgOrd.OWNER_ID_DC.getKey().equals(serOwnrId) || ownerIDShpgOrd.OWNER_ID_ROSS.getKey().equals(serOwnrId) || ownerIDShpgOrd.OWNER_ID_PROCUREMENT.getKey().equals(serOwnrId)) {
            getShpgOrdInfo(tmpWrkTMsg, valSerEventStsTMsg);
        }

        valSerEventStsTMsg.glblCmpyCd.setValue(NLXC014001.nullToEmpty(glblCmpyCd));
        valSerEventStsTMsg.serEventStsPk.setValue(NLXC014001.nullToZero(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SER_EVENT_STS_SQ)));
        valSerEventStsTMsg.serOwnrId.setValue(NLXC014001.nullToEmpty(serOwnrId));
        valSerEventStsTMsg.mdseCd.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.mdseCd.getValue()));
        valSerEventStsTMsg.serNum.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.serNum.getValue()));
        valSerEventStsTMsg.serEventTs.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.serEventTs.getValue()));
        valSerEventStsTMsg.serLocGrpCd.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.serLocGrpCd.getValue()));
        valSerEventStsTMsg.serEventCd.setValue(NLXC014001.nullToEmpty(serEventCd));
        valSerEventStsTMsg.trxCd.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.trxCd.getValue()));
        valSerEventStsTMsg.trxRsnCd.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.trxRsnCd.getValue()));
        valSerEventStsTMsg.cpoOrdTpCd.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.cpoOrdTpCd.getValue()));
        valSerEventStsTMsg.fromLocCd.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.fromLocCd.getValue()));
        valSerEventStsTMsg.fromLocNm.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.fromLocNm.getValue()));
        valSerEventStsTMsg.toLocCd.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.toLocCd.getValue()));
        valSerEventStsTMsg.toLocNm.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.toLocNm.getValue()));
        valSerEventStsTMsg.licAccsNum.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.licAccsNum.getValue()));
        valSerEventStsTMsg.oldSerNum.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.oldSerNum.getValue()));
        valSerEventStsTMsg.prodLineProdCtrlCd.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.prodLineProdCtrlCd.getValue()));
        valSerEventStsTMsg.keyInfoCd_01.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.keyInfoCd_01.getValue()));
        valSerEventStsTMsg.keyInfoCd_02.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.keyInfoCd_02.getValue()));
        valSerEventStsTMsg.keyInfoCd_03.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.keyInfoCd_03.getValue()));
        valSerEventStsTMsg.keyInfoCd_04.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.keyInfoCd_04.getValue()));
        valSerEventStsTMsg.keyInfoCd_05.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.keyInfoCd_05.getValue()));
        valSerEventStsTMsg.keyInfoCd_06.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.keyInfoCd_06.getValue()));
        valSerEventStsTMsg.keyInfoCd_07.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.keyInfoCd_07.getValue()));
        valSerEventStsTMsg.keyInfoCd_08.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.keyInfoCd_08.getValue()));
        valSerEventStsTMsg.keyInfoCd_09.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.keyInfoCd_09.getValue()));
        valSerEventStsTMsg.keyInfoCd_10.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.keyInfoCd_10.getValue()));
        valSerEventStsTMsg.serCitsOutStsCd.setValue(PROC_STS.IN_COMPLETED);
        valSerEventStsTMsg.serCnaOutStsCd.setValue(PROC_STS.IN_COMPLETED);
        valSerEventStsTMsg.serDwhOutStsCd.setValue(PROC_STS.IN_COMPLETED);
        valSerEventStsTMsg.serErrStsCd.setValue(NLXSceConst.SER_ERR_STS_CD_SPACE);
        valSerEventStsTMsg.serProcIntfcFlg.setValue(NLXC014001.nullToEmpty(tmpWrkTMsg.serProcIntfcFlg.getValue()));

        String[] serFlagInfo = null;
        String setMdseCdExistFlg = null;
        if (serFlagInfoList.containsKey(serEventCd)) {
            serFlagInfo = serFlagInfoList.get(serEventCd);
            setMdseCdExistFlg = serFlagInfo[1];
        } else {
            setMdseCdExistFlg = ZYPConstant.FLG_OFF_N;
        }

        if (ZYPConstant.FLG_ON_Y.equals(setMdseCdExistFlg)) {
            if (ownerIDSetMdse.OWNER_ID_DC.getKey().equals(serOwnrId)) {
                valSerEventStsTMsg.setMdseCd.setValue(tmpWrkTMsg.keyInfoCd_05.getValue());
            } else if (ownerIDSetMdse.OWNER_ID_ROSS.getKey().equals(serOwnrId)) {
                valSerEventStsTMsg.setMdseCd.setValue(tmpWrkTMsg.keyInfoCd_10.getValue());
            } else if (ownerIDSetMdse.OWNER_ID_PROCUREMENT.getKey().equals(serOwnrId)) {
                valSerEventStsTMsg.setMdseCd.setValue(tmpWrkTMsg.keyInfoCd_09.getValue());
            }
        }

        SER_EVENT_LGCY_PRMO_DFNTMsg selpdTMsg = new SER_EVENT_LGCY_PRMO_DFNTMsg();
        selpdTMsg.glblCmpyCd.setValue(glblCmpyCd);
        selpdTMsg.serEventCd.setValue(serEventCd);
        selpdTMsg = (SER_EVENT_LGCY_PRMO_DFNTMsg) S21ApiTBLAccessor.findByKey(selpdTMsg);
        if (selpdTMsg != null) {
            valSerEventStsTMsg.intfcSysNm.setValue(selpdTMsg.intfcSysNm.getValue());
        }
    }

    /**
     * getShpgOrdInfo
     * Get Shipping Order Information in the case of OwnerID('01','03','05')
     * 
     * @param soNum
     * @param valSerEventStsTMsg
     */
    private void getShpgOrdInfo(SER_EVENT_STS_WRKTMsg tmpWrkTMsg, SER_EVENT_STSTMsg valSerEventStsTMsg) throws SQLException {

        String soNum = tmpWrkTMsg.keyInfoCd_02.getValue();
        if (soNum != null) {

            S21SsmExecutionParameter execParam = null;
            PreparedStatement prdStmt = null;
            ResultSet rs = null;

            try {

                // Set S21SsmExecutionParameter.
                execParam = new S21SsmExecutionParameter();
                execParam.setFetchSize(1);

                // Set search parameters.
                Map<String, String> paramMap = new HashMap<String, String>();
                paramMap.put("glblCmpyCd", tmpWrkTMsg.glblCmpyCd.getValue());
                paramMap.put("soNum", soNum);

                // Search copy data.
                prdStmt = ssmLLClient.createPreparedStatement("getShpgOrdInfo", paramMap, execParam);

                // Copy data does not exist.
                rs = prdStmt.executeQuery();
                if (!rs.next()) {
                    S21InfoLogOutput.println("There is no data at getShpgOrdInfo" + paramMap.toString());
                    return;
                } else {
                    valSerEventStsTMsg.billToCustCd.setValue(NLXC014001.nullToEmpty(rs.getString(KEY_BILL_TO_CUST_CD)));
                    valSerEventStsTMsg.sellToCustCd.setValue(NLXC014001.nullToEmpty(rs.getString(KEY_SELL_TO_CUST_CD)));
                }
            } catch (SQLException e) {
                throw e;

            } finally {
                S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
            }
        }
    }
    /**
     * updateSerEventSts
     * For 3rd party vendor drop Serial# change
     * 
     * @param valSerEventStsTMsg SER_EVENT_STSTMsg
     */
    private boolean updateSerEventSts(SER_EVENT_STSTMsg valSerEventStsTMsg, SER_EVENT_STS_WRKTMsg serEventStsWrkTMsg, List<Map<String, String>> serErrListForMail)
    throws SQLException {

        S21SsmExecutionParameter execParam = null;
        PreparedStatement prdStmt = null;
        ResultSet rs = null;
        SER_EVENT_STSTMsg serEventStsTMsg = new SER_EVENT_STSTMsg();

        try {

            // Set S21SsmExecutionParameter.
            execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(1);

            // Set search parameters.
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("glblCmpyCd", valSerEventStsTMsg.glblCmpyCd.getValue());
            paramMap.put("serNum", valSerEventStsTMsg.oldSerNum.getValue());
            paramMap.put("mdseCd", valSerEventStsTMsg.mdseCd.getValue());
            paramMap.put("keyInfoCd_01", valSerEventStsTMsg.keyInfoCd_01.getValue());
            paramMap.put("keyInfoCd_02", valSerEventStsTMsg.keyInfoCd_02.getValue());
            paramMap.put("keyInfoCd_03", valSerEventStsTMsg.keyInfoCd_03.getValue());
            paramMap.put("keyInfoCd_04", valSerEventStsTMsg.keyInfoCd_04.getValue());
            paramMap.put("keyInfoCd_05", valSerEventStsTMsg.keyInfoCd_05.getValue());

            if (ZYPCommonFunc.hasValue(valSerEventStsTMsg.keyInfoCd_06.getValue())) {
                paramMap.put("keyInfoCd_06", valSerEventStsTMsg.keyInfoCd_06.getValue());
            }
            if (ZYPCommonFunc.hasValue(valSerEventStsTMsg.keyInfoCd_07.getValue())) {
                paramMap.put("keyInfoCd_07", valSerEventStsTMsg.keyInfoCd_07.getValue());
            }
            paramMap.put("keyInfoCd_08", valSerEventStsTMsg.keyInfoCd_08.getValue());
            paramMap.put("serEventCd", THIRD_PARTY_SER_280);
            paramMap.put("locGrpCd", SER_LOC_GRP.DEALER_OR_RETAILER_OR_DISTRIBUTOR);

            // Search update data.
            prdStmt = ssmLLClient.createPreparedStatement("getUpdateSerStsPk", paramMap, execParam);

            // Search update data.
            rs = prdStmt.executeQuery();

            if (!rs.next()) {
                S21InfoLogOutput.println("There is no data at getUpdateSerStsPk" + paramMap.toString());
                return false;

            } else {
                serEventStsTMsg.serEventStsPk.setValue(rs.getBigDecimal("SER_EVENT_STS_PK"));
                serEventStsTMsg.glblCmpyCd.setValue(valSerEventStsTMsg.glblCmpyCd.getValue());
            }
        } catch (SQLException e) {
            throw e;

        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }

        // get oldSerNum data
        serEventStsTMsg = (SER_EVENT_STSTMsg) S21ApiTBLAccessor.findByKey(serEventStsTMsg);

        // update serNum only.
        serEventStsTMsg.serNum.setValue(valSerEventStsTMsg.serNum.getValue());
        S21ApiTBLAccessor.update(serEventStsTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(serEventStsTMsg.getReturnCode())) {
            throw new S21AbendException(NLBM1019E, new String[] {SER_EVENT_STS, "Update", serEventStsTMsg.getReturnCode() });
        }

        return true;
    }

    /**
     * createCopySerEventSts
     * Create Copy Serial Event Status.
     * @param valSerEventStsTMsg
     * @exception SQLException
     */
    private void createCopySerEventSts(SER_EVENT_STSTMsg valSerEventStsTMsg) throws SQLException {

        S21SsmExecutionParameter execParam = null;
        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {

            // Set S21SsmExecutionParameter.
            execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(1);

            // Set search parameters.
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("glblCmpyCd", valSerEventStsTMsg.glblCmpyCd.getValue());
            paramMap.put("soNum",      valSerEventStsTMsg.keyInfoCd_02.getValue());
            paramMap.put("cpoExstFlg", ZYPConstant.FLG_ON_Y);
            paramMap.put("cpoSrcTpCd", CPO_SRC_TP.C_N_A);

            // Search copy data.
            prdStmt = ssmLLClient.createPreparedStatement("getCopySerEventSts", paramMap, execParam);

            // Copy data does not exist.
            rs = prdStmt.executeQuery();
            if (!rs.next()) {
                return;
            }

            // Insert copy serial event status.
            SER_EVENT_STSTMsg serEventStsTMsg = new SER_EVENT_STSTMsg();
            EZDMsg.copy(valSerEventStsTMsg, null, serEventStsTMsg, null);
            S21ResultSetMapper.map(rs, serEventStsTMsg);
            serEventStsTMsg.serEventCd.setValue(cnaRecord.CNA_EVENT_CD.getKey());
            serEventStsTMsg.intfcSysNm.setValue(cnaRecord.CNA_SYS_NAME.getKey());
            serEventStsTMsg.serOwnrId.setValue(cnaRecord.CNA_OWNER_ID.getKey());
            serEventStsTMsg.serEventStsPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SER_EVENT_STS_SQ));
            S21ApiTBLAccessor.insert(serEventStsTMsg);

        } catch (SQLException e) {
            throw e;

        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }
    }

    /**
     * checkSerNum.
     * @param serEventStsWrkTMsg    SER_EVENT_STS_WRKTMsg
     * @param serNumErr             SER_NUM_ERRTMsg
     * @param serErrListForMail     List<Map<String, String>>
     * @param valSerEventStsTMsg    SER_EVENT_STSTMsg
     * @exception SQLException
     */
    @SuppressWarnings("unchecked")
    private boolean checkSerNum(//
            SER_EVENT_STS_WRKTMsg serEventStsWrkTMsg, //
            SER_NUM_ERRTMsg serNumErr, //
            List<Map<String, String>> serErrListForMail, //
            SER_EVENT_STSTMsg valSerEventStsTMsg) throws SQLException {

        EZDDebugOutput.println(1, PROGRAM_ID + "[ checkSerNum ] start", this);

        boolean serNumErrFlg = false;

        String serOwnrId = serEventStsWrkTMsg.serOwnrId.getValue();

        if (serOwnrId == null || !NLXSceConst.SER_OWNR_ID_SCE.equals(serOwnrId)) {
            return serNumErrFlg;
        }

        //Serial# Length & Range check
        // get MDSE_SER_NUM_RNG of this MDSE
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", serEventStsWrkTMsg.glblCmpyCd.getValue());
        queryParam.put("mdseCd", serEventStsWrkTMsg.mdseCd.getValue());
        List<Map> ssmResList = (List<Map>) ssmBatchClient.queryObjectList("getMdseSerNumRng", queryParam);

        if (ssmResList != null && !ssmResList.isEmpty()) {
            // Check only when there is the MDSE_CD in MDSE_SER_NUM_RNG

            // MDSE_SER_NUM_RNG Length check for Serial Error Report
            boolean lengtherr = true;
            for (Map map : ssmResList) {
                BigDecimal lgSerNum = (BigDecimal) map.get("LG_SER_NUM");
                String serNum = serEventStsWrkTMsg.serNum.getValue();

                if (lgSerNum == null) {
                    continue;
                }

                if (lgSerNum.intValue() == serNum.length()) {
                    lengtherr = false;
                    break;
                }
            }

            if (lengtherr) {
                // Length check error
                EZDDebugOutput.println(1, PROGRAM_ID + "This Serial# is out of length.", this);
                setSerErrListForMail(NLZM2045W, serEventStsWrkTMsg, serErrListForMail);

                // Always true
                if (!serNumErrFlg) {
                    valSerEventStsTMsg.serErrStsCd.setValue(NLXSceConst.SER_ERR_STS_CD_LENGTH_ERROR);
                    // for Serial Error Report
                    BigDecimal lgErrQty = serNumErr.lgErrQty.getValue();
                    serNumErr.lgErrQty.setValue(lgErrQty.add(BigDecimal.ONE));
                    serNumErrFlg = true;
                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Serial# Length Error", this);
                }
            }

            // Serial# not in the setting range (MDSE_SER_NUM_RNG)
            boolean rngErr = true;
            for (Map map : ssmResList) {
                String fromSerNum = (String) map.get("FROM_SER_NUM");
                String thruSerNum = (String) map.get("THRU_SER_NUM");
                String serNum = serEventStsWrkTMsg.serNum.getValue();

                if (fromSerNum == null || thruSerNum == null) {
                    continue;
                }

                if (fromSerNum.compareTo(serNum) <= 0 && thruSerNum.compareTo(serNum) >= 0) {
                    rngErr = false;
                    break;
                }
            }

            if (rngErr) {
                // Range check error
                EZDDebugOutput.println(1, PROGRAM_ID + "This Serial# is out of range.", this);
                setSerErrListForMail(NLZM2042W, serEventStsWrkTMsg, serErrListForMail);

                // serErrStsCd can't have multiple Err code 
                if (!serNumErrFlg) {
                    valSerEventStsTMsg.serErrStsCd.setValue(NLXSceConst.SER_ERR_STS_CD_RANGE_ERROR);
                    // for Serial Error Report
                    BigDecimal rngErrQty = serNumErr.rngErrQty.getValue();
                    serNumErr.rngErrQty.setValue(rngErrQty.add(BigDecimal.ONE));
                    serNumErrFlg = true;
                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Serial# Range Error", this);
                }
            }
        }

        // Duplication Check is done against valid Serial# 
        if (serNumErrFlg) {
            EZDDebugOutput.println(1, PROGRAM_ID + "[ checkSerNum ] end1", this);
            return serNumErrFlg;
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("glblCmpyCd", serEventStsWrkTMsg.glblCmpyCd.getValue());
        paramMap.put("serNum", serEventStsWrkTMsg.serNum.getValue());
        paramMap.put("mdseCd", serEventStsWrkTMsg.mdseCd.getValue());
        
        // Duplicated Serial#
        // ITG#382732 start TODO
        S21SsmEZDResult serEventResult = NLXC008001Query.getInstance().checkSerialEvent(paramMap);
        if (serEventResult.isCodeNormal()) {
            List list = (List) serEventResult.getResultObject();
            if (list != null && list.size() == 1) {
                Map map = (Map) list.get(0);
                String serOwnrIdToCheckEvent = nullToEmpty((String) map.get("SER_OWNR_ID"));
                if (!serOwnrIdToCheckEvent.equals("05")) {
                    // ----------------------------------
                    // Serial Event already exists
                    // but it is NOT "Return"
                    // i.e. Item has been shipped (NG)
                    // ----------------------------------
                    // NLZM2041W=0,Serial# is duplicated.
                    setSerErrListForMail(NLZM2041W, serEventStsWrkTMsg, serErrListForMail);
                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Serial# Duplicated Error(non Return)", this);
                    valSerEventStsTMsg.serErrStsCd.setValue(NLXSceConst.SER_ERR_STS_CD_DUPLICATION_ERROR);
                    BigDecimal dupErrQty = serNumErr.dupErrQty.getValue();
                    serNumErr.dupErrQty.setValue(dupErrQty.add(BigDecimal.ONE));
                }
            }
        }
        // ITG#382732 end

        EZDDebugOutput.println(1, PROGRAM_ID + "[ checkSerNum ] end", this);
        return serNumErrFlg;
    }

    /**
     * Do Error Process
     * Add xxMsgId to msgMap and Output Log
     * @param xxMsgId String
     */
    private void setSerErrListForMail(String xxMsgId, SER_EVENT_STS_WRKTMsg serEventStsWrkTMsg, List<Map<String, String>> serErrListForMail) {

        String whCd = ZYPCommonFunc.paddingSpace(nullToEmpty(serEventStsWrkTMsg.fromLocCd.getValue()), false, 5);
        String soNum = ZYPCommonFunc.paddingSpace(nullToEmpty(serEventStsWrkTMsg.keyInfoCd_02.getValue()), false, 10);
        String mdseCd = ZYPCommonFunc.paddingSpace(nullToEmpty(serEventStsWrkTMsg.mdseCd.getValue()), false, 14);
        String serNum = ZYPCommonFunc.paddingSpace(nullToEmpty(serEventStsWrkTMsg.serNum.getValue()), false, 30);
        String message = S21MessageFunc.clspGetMessage(xxMsgId).substring(10);

        Map<String, String> serErrMap = new HashMap<String, String>();
        serErrMap.put(NLXSerInfoCheck.WH_CD, serEventStsWrkTMsg.fromLocCd.getValue());
        serErrMap.put(NLXSerInfoCheck.ERR_MSG, whCd + soNum + mdseCd + serNum + message);
        serErrListForMail.add(serErrMap);

        S21InfoLogOutput.println(xxMsgId);
    }

    /**
     * nullToEmpty
     * Convert null to empty
     * @param str String
     * @return result String
     */
    private String nullToEmpty(String str) {

        if (str == null) {
            return "";
        } else {
            return str;
        }
    }
}
