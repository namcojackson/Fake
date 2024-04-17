/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB001001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.PO_SER_NUMTMsg;
import business.db.PO_SER_NUMTMsgArray;
import business.db.SHIP_SER_NUMTMsg;
import business.db.SHIP_SER_NUMTMsgArray;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_MACH_CTAC_PSNTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.parts.NSZC001001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_QLFY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Machine Master Creation Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 05/05/2013   Fujitsu         Y.Kamide        Create          N/A
 * 08/26/2013   WDS Team        K.Aratani       Update          QC1864
 * 08/26/2013   WDS Team        K.Aratani       Update          QC1823
 * 12/10/2013   Fujitsu         T.Nakamura      Update          WDS Defect#2957
 * 10/01/2015   Hitachi         T.Tomita        Update          CSA
 * 12/04/2015   Hitachi         T.Tomita        Update          CSA QC#1500
 * 2015/12/10   Hitachi         T.Tomita        Update          CSA QC#1795
 * 2015/12/11   Hitachi         T.Tomita        Update          CSA QC#1799
 * 2015/12/21   Hitachi         K.Yamada        Update          CSA QC#2261
 * 2016/01/06   Hitachi         T.Tomita        Update          CSA QC#2753
 * 2016/01/18   Hitachi         T.Tomita        Update          CSA QC#2525
 * 2016/02/09   Hitachi         T.Tomita        Update          CSA QC#1801
 * 2016/07/04   Hitachi         T.Tomita        Update          CSA QC#10464
 * 2016/07/26   Hitachi         T.Tomita        Update          CSA QC#12511, 12642
 * 2016/10/06   Hitachi         T.Tomita        Update          CSA QC#12642
 * 2016/10/11   Hitachi         T.Tomita        Update          CSA QC#14708
 * 2017/03/21   Hitachi         K.Kojima        Update          CSA QC#16600
 * 2017/03/22   Hitachi         K.Kitachi       Update          CSA QC#15679
 * 2017/03/31   Hitachi         K.Kitachi       Update          CSA QC#18077
 * 2017/09/21   Hitachi         T.Tomita        Update          CSA QC#20989
 * 2017/10/05   Hitachi         T.Tomita        Update          CSA QC#21565
 * 2017/10/16   CITS            M.Naito         Update          CSA QC#20246
 * 2017/10/17   Hitachi         T.Tomita        Update          CSA QC#21798
 * 2017/11/21   Hitachi         K.Ochiai        Update          CSA QC#21698
 * 2017/11/30   Hitachi         T.Tomita        Update          CSA QC#21242
 * 2017/12/06   Hitachi         T.Tomita        Update          CSA QC#22490
 * 2018/02/22   Hitachi         U.Kim           Update          CSA QC#24192
 * 2018/03/02   Hitachi         T.Tomita        Update          CSA QC#23425
 * 2018/03/06   CITS            M.Naito         Update          CSA QC#23412
 * 2018/04/18   Hitachi         T.Tomita        Update          CSA QC#19154
 * 2018/07/09   CITS            K.Ogino         Update          CSA QC#26242
 * 2018/07/12   Hitachi         K.Kitachi       Update          CSA QC#26526
 * 2018/09/25   Hitachi         K.Kitachi       Update          CSA QC#27788
 * 2018/12/03   CITS            K.Ogino         Update          CSA QC#29406
 * 2019/02/01   Hitachi         S.Kitamura      Update          CSA QC#30081
 * 2019/02/19   Hitachi         T.Tomita        Update          CSA QC#30398
 * 03/27/2019   CITS            K.Ogino         Update          CSA QC#30848
 * 2019/10/30   Hitachi         K.Kitachi       Update          CSA QC#54324
 * 2019/12/27   Hitachi         K.Kitachi       Update          CSA QC#55139
 * 2020/04/30   CITS            T.Wada          Update          CSA QC#56209
 *</pre>
 */
public class NSAB001001 extends S21BatchMain implements NSAB001001Constant {

    /** Total Counter */
    private int totCnt = 0;

    /** Error Counter */
    private int errCnt = 0;

    /** message list */
    private List<String> msgList = new ArrayList<String>();

    /** API Error message list */
    private List<String> apiErrMsgList = new ArrayList<String>();

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** Term Code */
    private TERM_CD termCd = null;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB001001().executeBatch(NSAB001001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NASM0010E);
        }

        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BUSINESS_ID);
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getShpgPln();
            rs = ps.executeQuery();

            List<MechMstrData> machMstrDataList;
            while (rs.next()) {
                // Add Start 2017/11/29 QC#21242
                if (isMachMstrProcessed(rs)) {
                    continue;
                }
                // Add End 2017/11/29 QC#21242

                this.apiErrMsgList = new ArrayList<String>();
                machMstrDataList = new ArrayList<MechMstrData>();
                this.totCnt++;

                findMachMstrData(rs, machMstrDataList);
                if (this.apiErrMsgList.size() > 0) {
                    this.msgList.addAll(this.apiErrMsgList);
                    this.errCnt++;
                    rollback();
                    continue;
                }

                updateMachMstrData(machMstrDataList);
                if (this.apiErrMsgList.size() > 0) {
                    this.msgList.addAll(this.apiErrMsgList);
                    this.errCnt++;
                    rollback();
                    continue;
                }
                commit();
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    @Override
    protected void termRoutine() {
        sendEmail();
        setTermState(termCd, totCnt - errCnt, errCnt, totCnt);
    }

    private PreparedStatement getShpgPln() throws SQLException {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        List<String> svcMachProcStsCdList = new ArrayList<String>();
        svcMachProcStsCdList.add(SVC_MACH_PROC_STS.IN_COMPLETED);
        queryParam.put("svcMachProcStsCdList", svcMachProcStsCdList);
        List<String> shpgStsCdList = new ArrayList<String>();
        shpgStsCdList.add(SHPG_STS.SHIPPED);
        shpgStsCdList.add(SHPG_STS.ARRIVED);
        queryParam.put("shpgStsCdList", shpgStsCdList);
        queryParam.put("actlShipDt", this.slsDt);
        // START 2019/10/30 K.Kitachi [QC#54324, ADD]
        // START 2019/12/27 K.Kitachi [QC#55139, DEL]
//        queryParam.put("svcMachProcStsCompleted", SVC_MACH_PROC_STS.COMPLETED);
        // END 2019/12/27 K.Kitachi [QC#55139, DEL]
        // END 2019/10/30 K.Kitachi [QC#54324, ADD]
        // START 2019/12/27 K.Kitachi [QC#55139, ADD]
        queryParam.put("poQlfyCd", PO_QLFY.DROPSHIP);
        // END 2019/12/27 K.Kitachi [QC#55139, ADD]

        return this.ssmLLClient.createPreparedStatement("getShpgPln", queryParam, getExecPrm());
    }

    private void findMachMstrData(ResultSet rs, List<MechMstrData> dataList) throws SQLException {
        if (ZYPConstant.FLG_ON_Y.equals(rs.getString("INVTY_CTRL_FLG"))) {
            if (ZYPConstant.FLG_ON_Y.equals(rs.getString("SHPG_SER_TAKE_FLG"))) {
                // Serialized Item
                if (!ZYPCommonFunc.hasValue(rs.getString("VND_CD"))) {
                    // Ship
                    serializedItemForShip(rs, dataList);
                } else {
                    // Vendor Drop Ship
                    serializedItemForDropShip(rs, dataList);
                }
            } else {
                // Non Serialized Item
                nonSerializedItem(rs, dataList);
            }
        } else {
            int ordQty = rs.getBigDecimal("ORD_QTY").intValue();

            MechMstrData data;
            NSZC001001PMsg pMsg;
            for (int i = 0; i < ordQty; i++) {
                data = setMachMstrData(rs);
                // Create Machine Master (Non Serial Number)
                // START 2016/02/09 T.Tomita [QC#1801, MOD]
                pMsg = createSvcMachMstr(rs, null);
                // END 2016/02/09 T.Tomita [QC#1801, MOD]
                if (isApiError(pMsg, data.getShpgPlnNum())) {
                    return;
                }
                setMachMstrData(data, pMsg);
                dataList.add(data);
            }
        }
    }

    private void serializedItemForShip(ResultSet rs, List<MechMstrData> dataList) throws SQLException {
        String soNum = rs.getString("SO_NUM");
        String soSlipNum = rs.getString("SO_SLP_NUM");
        SHIP_SER_NUMTMsgArray shipSerNumTmsgArray = getShipSerNum(soNum, soSlipNum);
        MechMstrData data;
        NSZC001001PMsg pMsg;
        if (shipSerNumTmsgArray.getValidCount() == 0) {
            // No Data SHIP_SER_NUM
            int ordQty = rs.getBigDecimal("ORD_QTY").intValue();
            for (int i = 0; i < ordQty; i++) {
                data = setMachMstrData(rs);
                // Create Machine Master (Dummy Serial Number)
                // START 2016/02/09 T.Tomita [QC#1801, MOD]
                pMsg = createSvcMachMstr(rs, null);
                // END 2016/02/09 T.Tomita [QC#1801, MOD]
                if (isApiError(pMsg, data.getShpgPlnNum())) {
                    return;
                }
                setMachMstrData(data, pMsg);
                dataList.add(data);
            }
            return;
        }
        SVC_MACH_MSTRTMsg svcMachMstrTmsg;

        for (int i = 0; i < shipSerNumTmsgArray.getValidCount(); i++) {
            data = setMachMstrData(rs);
            // get Machine Master for Serial Number
            // START 2017/03/21 K.Kojima [QC#16600,MOD]
            // svcMachMstrTmsg = getSvcMachMstrForSerNum(shipSerNumTmsgArray.no(i).serNum.getValue());
            svcMachMstrTmsg = getSvcMachMstrForSerNum(shipSerNumTmsgArray.no(i).serNum.getValue(), shipSerNumTmsgArray.no(i).mdseCd.getValue());
            // END 2017/03/21 K.Kojima [QC#16600,MOD]
            if (svcMachMstrTmsg == null) {
                // START 2017/03/22 K.Kitachi [QC#15679, MOD]
                BigDecimal svcMachMstrPk = null;
                if (ZYPConstant.FLG_OFF_N.equals(rs.getString("RCV_SER_TAKE_FLG"))) {
                    svcMachMstrPk = getSvcMachMstrPk(rs.getString("MDSE_CD"), rs.getString("INVTY_LOC_CD"), rs.getString("STK_STS_CD"));
                }
                if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                    // Update Machine Master (Serial Change)
                    pMsg = updateSvcMachMstr(rs, shipSerNumTmsgArray.no(i).serNum.getValue(), svcMachMstrPk);
                } else {
                    // Create Machine Master (Serial Number)
                    // START 2016/02/09 T.Tomita [QC#1801, MOD]
                    pMsg = createSvcMachMstr(rs, shipSerNumTmsgArray.no(i).serNum.getValue());
                    // END 2016/02/09 T.Tomita [QC#1801, MOD]
                }
                // END 2017/03/22 K.Kitachi [QC#15679, MOD]
                if (isApiError(pMsg, data.getShpgPlnNum())) {
                    return;
                }
                setMachMstrData(data, pMsg);
                dataList.add(data);
                continue;
            }

            if (isErrMachMstrSts(svcMachMstrTmsg.svcMachMstrStsCd.getValue())) {
                // Create Machine Master (Dummy Serial Number)
                // START 2016/02/09 T.Tomita [QC#1801, MOD]
                pMsg = createSvcMachMstr(rs, shipSerNumTmsgArray.no(i).serNum.getValue());
                // END 2016/02/09 T.Tomita [QC#1801, MOD]
                if (isApiError(pMsg, data.getShpgPlnNum())) {
                    return;
                }
                setMachMstrData(data, pMsg);
                dataList.add(data);
                continue;
            }

            setMachMstrData(data, svcMachMstrTmsg);
            dataList.add(data);
        }
    }

    private void serializedItemForDropShip(ResultSet rs, List<MechMstrData> dataList) throws SQLException {
        String trxHdrNum = rs.getString("TRX_HDR_NUM");
        String trxLineNum = rs.getString("TRX_LINE_NUM");
        String trxLineSubNum = rs.getString("TRX_LINE_SUB_NUM");
        PO_SER_NUMTMsgArray poSerNumTmsgArray = getPoSerNum(trxHdrNum, trxLineNum, trxLineSubNum);
        MechMstrData data;
        NSZC001001PMsg pMsg;
        if (poSerNumTmsgArray.getValidCount() == 0) {
            // No Data PO_SER_NUM
            int ordQty = rs.getBigDecimal("ORD_QTY").intValue();
            for (int i = 0; i < ordQty; i++) {
                data = setMachMstrData(rs);

                // START 2018/03/06 M.Naito [QC#23412, ADD]
                BigDecimal svcMachMstrPk = null;
                // get Machine Master (Dummy Serial Number) from ASN
                String dropShipWhCd = ZYPCodeDataUtil.getVarCharConstValue(DROP_SHIP_WH_CD, this.glblCmpyCd);
                svcMachMstrPk = getSvcMachMstrPk(rs.getString("MDSE_CD"), dropShipWhCd, rs.getString("STK_STS_CD"));
                if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                    SVC_MACH_MSTRTMsg tMsg = getSvcMachMstrForPk(svcMachMstrPk);
                    pMsg = updateSvcMachMstr(rs, tMsg.serNum.getValue(), svcMachMstrPk);
                    setMachMstrData(data, pMsg);
                    dataList.add(data);
                    continue;
                }
                // END 2018/03/06 M.Naito [QC#23412, ADD]

                // Create Machine Master (Dummy Serial Number)
                // START 2016/02/09 T.Tomita [QC#1801, MOD]
                pMsg = createSvcMachMstr(rs, null);
                // END 2016/02/09 T.Tomita [QC#1801, MOD]
                if (isApiError(pMsg, data.getShpgPlnNum())) {
                    return;
                }
                setMachMstrData(data, pMsg);
                dataList.add(data);
            }
            return;
        }
        SVC_MACH_MSTRTMsg svcMachMstrTmsg;

        for (int i = 0; i < poSerNumTmsgArray.getValidCount(); i++) {
            data = setMachMstrData(rs);
            // START 2017/03/21 K.Kojima [QC#16600,MOD]
            // svcMachMstrTmsg = getSvcMachMstrForSerNum(poSerNumTmsgArray.no(i).serNum.getValue());
            svcMachMstrTmsg = getSvcMachMstrForSerNum(poSerNumTmsgArray.no(i).serNum.getValue(), poSerNumTmsgArray.no(i).mdseCd.getValue());
            // END 2017/03/21 K.Kojima [QC#16600,MOD]
            if (svcMachMstrTmsg == null) {
                // Create Machine Master (Serial Number)
                // START 2016/02/09 T.Tomita [QC#1801, MOD]
                pMsg = createSvcMachMstr(rs, poSerNumTmsgArray.no(i).serNum.getValue());
                // END 2016/02/09 T.Tomita [QC#1801, MOD]
                if (isApiError(pMsg, data.getShpgPlnNum())) {
                    return;
                }
                setMachMstrData(data, pMsg);
                dataList.add(data);
                continue;
            }

            if (isErrMachMstrSts(svcMachMstrTmsg.svcMachMstrStsCd.getValue())) {
                // Create Machine Master (Dummy Serial Number)
                // START 2016/02/09 T.Tomita [QC#1801, MOD]
                pMsg = createSvcMachMstr(rs, poSerNumTmsgArray.no(i).serNum.getValue());
                // END 2016/02/09 T.Tomita [QC#1801, MOD]
                if (isApiError(pMsg, data.getShpgPlnNum())) {
                    return;
                }
                setMachMstrData(data, pMsg);
                dataList.add(data);
                continue;
            }

            setMachMstrData(data, svcMachMstrTmsg);
            dataList.add(data);
        }
    }

    private void nonSerializedItem(ResultSet rs, List<MechMstrData> dataList) throws SQLException {
        BigDecimal svcMachMstrPk = rs.getBigDecimal("SVC_MACH_MSTR_PK");
        SVC_MACH_MSTRTMsg svcMachMstrTmsg = getSvcMachMstrForPk(svcMachMstrPk);
        MechMstrData data;
        // START 2019/02/01 S.Kitamura [QC#30081, MOD]
        String dsOrdCatgCd = rs.getString("DS_ORD_TP_CD");
        // if (svcMachMstrTmsg != null) {
        if (!isIBTrmnSplyOrd(dsOrdCatgCd) && svcMachMstrTmsg != null) {
        // END 2019/02/01 S.Kitamura [QC#30081, MOD]
            data = setMachMstrData(rs);
            setMachMstrData(data, svcMachMstrTmsg);
            dataList.add(data);
        } else {
            int ordQty = rs.getBigDecimal("ORD_QTY").intValue();
            String mdseCd = rs.getString("MDSE_CD");
            String curLocNum = rs.getString("INVTY_LOC_CD");
            // START 2017/03/31 K.Kitachi [QC#18077, ADD]
            if (ZYPCommonFunc.hasValue(rs.getString("VND_CD"))) {
                curLocNum = ZYPCodeDataUtil.getVarCharConstValue(DS_DROP_SHIP_WH_CD, this.glblCmpyCd);
            }
            // END 2017/03/31 K.Kitachi [QC#18077, ADD]
            String stkStkCd = rs.getString("STK_STS_CD");
            List<BigDecimal> svcMachMstrPkList = getSvcMachMstrForNonSerNum(mdseCd, curLocNum, stkStkCd);

            if (ordQty > svcMachMstrPkList.size()) {
                for (BigDecimal machMstrPk : svcMachMstrPkList) {
                    data = setMachMstrData(rs);
                    setMachMstrData(data, machMstrPk);
                    dataList.add(data);
                }
                NSZC001001PMsg pMsg;
                for (int i = 0; i < ordQty - svcMachMstrPkList.size(); i++) {
                    data = setMachMstrData(rs);
                    // Create Machine Master (Non Serial Number)
                    // START 2016/02/09 T.Tomita [QC#1801, MOD]
                    pMsg = createSvcMachMstr(rs, null);
                    // END 2016/02/09 T.Tomita [QC#1801, MOD]
                    if (isApiError(pMsg, data.getShpgPlnNum())) {
                        return;
                    }
                    setMachMstrData(data, pMsg);
                    dataList.add(data);
                }
            } else  {
                // mod start 2015/12/21 CSA Defect#2261
                //for (BigDecimal machMstrPk : svcMachMstrPkList) {
                for (int i = 0; i < ordQty; i++) {
                    BigDecimal machMstrPk = svcMachMstrPkList.get(i);
                    data = setMachMstrData(rs);
                    setMachMstrData(data, machMstrPk);
                    dataList.add(data);
                }
                // mod end 2015/12/21 CSA Defect#2261
            }
        }
    }

    // START 2016/02/09 T.Tomita [QC#1801, MOD]
    private NSZC001001PMsg createSvcMachMstr(ResultSet rs, String serNum) throws SQLException {
        NSZC001001PMsg pMsg = new NSZC001001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.INSERT_WAREHOUSE.code);
        ZYPEZDItemValueSetter.setValue(pMsg.serNum, serNum);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, rs.getString("MDSE_CD"));
        // del start 2016/08/01 CSA Defect#12511
//        if (ZYPConstant.FLG_ON_Y.equals(rs.getString("BASE_CMPT_FLG"))) {
//            ZYPEZDItemValueSetter.setValue(pMsg.svcMachTpCd, SVC_MACH_TP.MACHINE);
//        } else {
//            ZYPEZDItemValueSetter.setValue(pMsg.svcMachTpCd, SVC_MACH_TP.ACCESSORY);
//        }
        // del end 2016/08/01 CSA Defect#12511
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
        ZYPEZDItemValueSetter.setValue(pMsg.locNm, rs.getString("SHIP_TO_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, rs.getString("STK_STS_CD"));
//        if (ZYPConstant.FLG_ON_Y.equals(dummyFlg)) {
        ZYPEZDItemValueSetter.setValue(pMsg.shipDt, rs.getString("ACTL_SHIP_DT"));
//        }
        ZYPEZDItemValueSetter.setValue(pMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.effFromDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_TRANSIT);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachQty, BigDecimal.ONE);
        // START 2016/01/06 T.Tomita [QC#2753, MOD]
//        String sceOrdTpCd = rs.getString("SCE_ORD_TP_CD");
//        if (ZYPCommonFunc.hasValue(sceOrdTpCd) && SCE_ORD_TP.LOAN.equals(sceOrdTpCd)) {
//            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, LOC_STS.TRIAL_USE);
//        } else {
//            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, rs.getString("SVC_MACH_MSTR_LOC_STS_CD"));
//        }
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, rs.getString("SVC_MACH_MSTR_LOC_STS_CD"));
        // END 2016/01/06 T.Tomita [QC#2753, MOD]
        // Mod Start 2017/12/06 QC#22490
        // Mod Start 2017/10/05 QC#21565
//        ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, rs.getString("SHIP_TO_CUST_LOC_CD"));
        String curLocNum = rs.getString("INVTY_LOC_CD");
        if (!ZYPCommonFunc.hasValue(curLocNum)) {
            curLocNum = rs.getString("SHIP_TO_CUST_LOC_CD");
        }
        ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, curLocNum);
        // Mod End 2017/10/05 QC#21565
        // Mod End 2017/12/06 QC#22490

        // Mod Start 2017/09/21 QC#20989
        List<Map<String, Object>> configMdseList = getMdseCdForConfig(rs.getString("CPO_ORD_NUM"), rs.getBigDecimal("DS_CPO_CONFIG_PK"));
        setCmptMachList(pMsg, configMdseList);
//        int i = 0;
//        String baseCmptFlg;
//        List<Map<String, Object>> configMdseList = getMdseCdForConfig(rs.getString("CPO_ORD_NUM"), rs.getBigDecimal("DS_CPO_CONFIG_PK"));
//        for (Map<String, Object> mdse : configMdseList) {
//            baseCmptFlg = (String) mdse.get("BASE_CMPT_FLG");
//            if (ZYPCommonFunc.hasValue(baseCmptFlg)) {
//                ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).mdseCd, (String) mdse.get("MDSE_CD"));
//                if (ZYPConstant.FLG_ON_Y.equals(baseCmptFlg)) {
//                    ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).svcMachTpCd, SVC_MACH_TP.MACHINE);
//                } else {
//                    ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).svcMachTpCd, SVC_MACH_TP.ACCESSORY);
//                }
//                i++;
//            }
//        }
//        pMsg.xxCmptMachList.setValidCount(i);
        // Mod End 2017/09/21 QC#20989

        // Call API
        NSZC001001 api = new NSZC001001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        return pMsg;
    }
    // END 2016/02/09 T.Tomita [QC#1801, MOD]

    private boolean isErrMachMstrSts(String svcMachMstrStsCd) {
        if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {
            return true;
        }
        return false;
    }

    private MechMstrData setMachMstrData(ResultSet rs) throws SQLException {
        MechMstrData data = new MechMstrData();
        // START 2015/12/10 T.Tomita [QC#1795, ADD]
        data.setDsCpoConfigPk(rs.getBigDecimal("DS_CPO_CONFIG_PK"));
        // END 2015/12/10 T.Tomita [QC#1795, ADD]
        data.setSvcConfigMstrPk(rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
        data.setMdseCd(rs.getString("MDSE_CD"));
        if (ZYPConstant.FLG_ON_Y.equals(rs.getString("BASE_CMPT_FLG"))) {
            data.setSvcMachTpCd(SVC_MACH_TP.MACHINE);
        } else {
            data.setSvcMachTpCd(SVC_MACH_TP.ACCESSORY);
        }
        data.setLocNm(rs.getString("SHIP_TO_LOC_NM"));
        data.setFirstLineAddr(rs.getString("SHIP_TO_FIRST_LINE_ADDR"));
        data.setScdLineAddr(rs.getString("SHIP_TO_SCD_LINE_ADDR"));
        data.setThirdLineAddr(rs.getString("SHIP_TO_THIRD_LINE_ADDR"));
        data.setFrthLineAddr(rs.getString("SHIP_TO_FRTH_LINE_ADDR"));
        data.setCtyAddr(rs.getString("SHIP_TO_CTY_ADDR"));
        data.setStCd(rs.getString("SHIP_TO_ST_CD"));
        data.setProvNm(rs.getString("SHIP_TO_PROV_ADDR"));
        data.setCntyNm(rs.getString("SHIP_TO_CNTY_NM"));
        data.setPostCd(rs.getString("SHIP_TO_POST_CD"));
        data.setCtryCd(rs.getString("SHIP_TO_CTRY_CD"));
        data.setStkStsCd(rs.getString("STK_STS_CD"));
        data.setShipFromWhCd(rs.getString("INVTY_LOC_CD"));
        data.setShipDt(rs.getString("ACTL_SHIP_DT"));
        data.setCpoOrdNum(rs.getString("CPO_ORD_NUM"));
        data.setCpoDtlLineNum(rs.getString("CPO_DTL_LINE_NUM"));
        data.setCpoDtlLineSubNum(rs.getString("CPO_DTL_LINE_SUB_NUM"));
        data.setCustIssPoNum(rs.getString("CUST_ISS_PO_NUM"));
        data.setShpgPlnNum(rs.getString("SHPG_PLN_NUM"));
        data.setSoNum(rs.getString("SO_NUM"));
        data.setSoSlpNum(rs.getString("SO_SLP_NUM"));
        String dsOrdTpCd = rs.getString("DS_ORD_TP_CD");
        data.setDsOrdTpCd(dsOrdTpCd);
        data.setDsOrdRsnCd(rs.getString("DS_ORD_RSN_CD"));
        data.setSvcMachUsgStsCd(SVC_MACH_USG_STS.IN_TRANSIT);
        data.setSvcMachTrxTpCd(getSvcMachTrxTpCd(dsOrdTpCd));
        data.setCtrlFldTxt_01(rs.getString("FIRST_BLLG_ATTRB_VAL_TXT"));
        data.setCtrlFldTxt_02(rs.getString("SCD_BLLG_ATTRB_VAL_TXT"));
        data.setCtrlFldTxt_03(rs.getString("THIRD_BLLG_ATTRB_VAL_TXT"));
        data.setCtrlFldTxt_04(rs.getString("FRTH_BLLG_ATTRB_VAL_TXT"));
        data.setCtrlFldTxt_05(rs.getString("FIFTH_BLLG_ATTRB_VAL_TXT"));
        data.setCtrlFldTxt_06(rs.getString("SIXTH_BLLG_ATTRB_VAL_TXT"));
        // mod start 2016/07/04 CSA Defect#10464
//        data.setPrcContrNum(rs.getString("DS_CONTR_NUM"));
        data.setPrcContrNum(rs.getString("PRC_CONTR_NUM"));
        // mod end 2016/07/04 CSA Defect#10464
        data.setHardDriveRmvInclFlg(rs.getString("HARD_DRIVE_RMV_INCL_FLG"));
        data.setHardDriveEraseInclFlg(rs.getString("HARD_DRIVE_ERASE_INCL_FLG"));
        String leaseRtrnFeeInclFlg = rs.getString("LEASE_RTRN_FEE_INCL_FLG");
        if (ZYPCommonFunc.hasValue(leaseRtrnFeeInclFlg)) {
            data.setLeaseRtrnFeeInclFlg(leaseRtrnFeeInclFlg);
        } else {
            data.setLeaseRtrnFeeInclFlg(ZYPConstant.FLG_OFF_N);
        }
        data.setSvcLicCnt(BigDecimal.ZERO);
        // START 2016/01/06 T.Tomita [QC#2753, MOD]
//        String sceOrdTpCd = rs.getString("SCE_ORD_TP_CD");
//        data.setSceOrdTpCd(sceOrdTpCd);
//        if (ZYPCommonFunc.hasValue(sceOrdTpCd) && SCE_ORD_TP.LOAN.equals(sceOrdTpCd)) {
//            data.setSvcMachMstrLocStsCd(LOC_STS.TRIAL_USE);
//        }
        data.setSvcMachMstrLocStsCd(rs.getString("SVC_MACH_MSTR_LOC_STS_CD"));
        // END 2016/01/06 T.Tomita [QC#2753, MOD]
        // add start 2016/07/25 CSA Defect#12511
        data.setSceOrdTpCd(rs.getString("SCE_ORD_TP_CD"));
        // add end 2016/07/25 CSA Defect#12511
        data.setOwnrAcctNum(rs.getString("BILL_TO_CUST_ACCT_CD"));
        data.setOwnrLocNum(rs.getString("BILL_TO_CUST_LOC_CD"));
        data.setBillToAcctNum(rs.getString("SELL_TO_CUST_CD"));
        data.setBillToLocNum(rs.getString("SOLD_TO_CUST_LOC_CD"));
        data.setCurLocAcctNum(rs.getString("SHIP_TO_CUST_ACCT_CD"));
        data.setCurLocNum(rs.getString("SHIP_TO_CUST_LOC_CD"));
        data.setPickSvcConfigMstrPk(rs.getBigDecimal("PICK_SVC_CONFIG_MSTR_PK"));

        return data;
    }

    private void setMachMstrData(MechMstrData data, NSZC001001PMsg pMsg) {
        data.setSvcMachMstrPk(pMsg.svcMachMstrPk.getValue());
        data.setSerNum(pMsg.serNum.getValue());
    }

    private void setMachMstrData(MechMstrData data, SVC_MACH_MSTRTMsg tMsg) {
        data.setSvcMachMstrPk(tMsg.svcMachMstrPk.getValue());
        data.setSerNum(tMsg.serNum.getValue());
    }

    private void setMachMstrData(MechMstrData data, BigDecimal svcMachMstrPk) {
        data.setSvcMachMstrPk(svcMachMstrPk);
        data.setSerNum(null);
    }

    private void updateMachMstrData(List<MechMstrData> machMstrDataList) {
        // START 2015/12/04 T.Tomita [QC#1500, MOD]
        List<Map<String, Object>> configMdseList;
        // END 2015/12/04 T.Tomita [QC#1500, MOD]
        NSZC001001PMsg inPmsg;
        for (MechMstrData data : machMstrDataList) {
            // START 2015/12/10 T.Tomita [QC#1795, MOD]
            configMdseList = getMdseCdForConfig(data.getCpoOrdNum(), data.getDsCpoConfigPk());
            // END 2015/12/10 T.Tomita [QC#1795, MOD]
            if (isSvcExchange(data.getDsOrdTpCd())) {
                // Service Exchange
                // Add Start 2018/03/02 QC#23425
                configMdseList = getMdseCdForSvcExchng(data.getCpoOrdNum(), data.getDsCpoConfigPk(), data.getSvcConfigMstrPk());
                if (!existsMainMach(configMdseList)) {
                    setErrorInfo(NSAM0713E, new String[] {"glblCmpyCd=" + this.glblCmpyCd + ", svcConfigMstrPk=" + data.getSvcConfigMstrPk() });
                    return;
                }
                // Add End 2018/03/02 QC#23425
                inPmsg = setApiParamForSvcExchange(data, configMdseList);
            // Mod Start 2017/10/05 QC#21565
            } else if (isIBTrmnOrd(data.getDsOrdTpCd())) {
                inPmsg = setApiParamForIBTrmnOrd(data);
            // Mod End 2017/10/05 QC#21565
            } else if (isWhTransfer(data.getSceOrdTpCd())) {
                // Add Start 2018/04/18 QC#19154
                if (isRecievedWHT(data)) {
                    // This machine received to warehouse.
                    updateShpgOrdDtl(data.getSoNum(), data.getSoSlpNum());
                    continue;
                }
                // Add End 2018/04/18 QC#19154
                // mod start 2016/07/29 CSA Defect#12642
                // WH Transfer
                inPmsg = setApiParamForWhTransfer(data, configMdseList);
                // mod end 2016/07/29 CSA Defect#12642
            } else {
                inPmsg = setApiParamForShip(data, configMdseList);
            }

            callApi(inPmsg);
            if (isApiError(inPmsg, data.getShpgPlnNum())) {
                return;
            }

            updateShpgOrdDtl(data.getSoNum(), data.getSoSlpNum());

            // START 2017/10/16 M.Naito [QC#20246, ADD]
            // Create Service Machine Contact
            List<Map<String, Object>> cpoCtacList = getCpoCtacInfo(data.getCpoOrdNum(), data.getDsCpoConfigPk());
            insertSvcMachCtacPsn(data.getSvcMachMstrPk(), cpoCtacList);
            // END 2017/10/16 M.Naito [QC#20246, ADD]
        }
    }

    private void updateShpgOrdDtl(String soNum, String soSlpNum) {
        if (!ZYPCommonFunc.hasValue(soNum) || !ZYPCommonFunc.hasValue(soSlpNum)) {
            return;
        }

        SHPG_ORD_DTLTMsg param = new SHPG_ORD_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(param.soSlpNum, soSlpNum);

        SHPG_ORD_DTLTMsg inMsg = (SHPG_ORD_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(param);
        if (inMsg == null) {
            setErrorInfo(NSAM0001E, new String[] {"SHPG_ORD_DTL", "soNum=" + soNum + ", soSlpNum=" + soSlpNum});
            return;
        }

        ZYPEZDItemValueSetter.setValue(inMsg.svcMachProcStsCd, "1");

        EZDTBLAccessor.update(inMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            setErrorInfo(NSAM0001E, new String[] {"SHPG_ORD_DTL", "soNum=" + soNum + ", soSlpNum=" + soSlpNum});
        }
    }

    private boolean isSvcExchange(String dsOrdTpCd) {
        List<Map<String, Object>> ctxMapList = getOrdCatgBizCtxForDsOrdTp(dsOrdTpCd, "SERVICE_EXCHANGE");
        if (ctxMapList.size() > 0) {
            return true;
        }
        return false;
    }

    private boolean isDealerOrd(String dsOrdTpCd) {
        List<Map<String, Object>> ctxMapList = getOrdCatgBizCtxForDsOrdTp(dsOrdTpCd, "DEALER_ORDER");
        if (ctxMapList.size() > 0) {
            return true;
        }
        return false;
    }

    // Add Start 2017/10/05 QC#21565
    private boolean isIBTrmnOrd(String dsOrdTpCd) {
        List<Map<String, Object>> ctxMapList = getOrdCatgBizCtxForDsOrdTp(dsOrdTpCd, ORD_CATG_CTX_TP.IB_TERMINATE_ORDER_CATEGORY);
        if (ctxMapList.size() > 0) {
            return true;
        }
        return false;
    }
    // Add End 2017/10/05 QC#21565
    // START 2019/02/01 S.Kitamura [QC#30081, ADD]
    private boolean isIBTrmnSplyOrd(String dsOrdTpCd) {
        List<Map<String, Object>> ctxMapList = getOrdCatgBizCtxForDsOrdTp(dsOrdTpCd, ORD_CATG_CTX_TP.IB_TERMINATE_SUPPLY_ORDER);
        if (ctxMapList.size() > 0) {
            return true;
        }
        return false;
    }
    // END 2019/02/01 S.Kitamura [QC#30081, ADD]
    private boolean isWhTransfer(String sceOrdTpCd) {
        if (ZYPCommonFunc.hasValue(sceOrdTpCd) && sceOrdTpCd.equals(SCE_ORD_TP.DC_TRANSFER)) {
            return true;
        }
        return false;
    }

    private boolean isApiError(NSZC001001PMsg pMsg, String shpgPlnNum) {
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                setApiErrorInfo(shpgPlnNum, "NSZC001001", pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return true;
        }
        return false;
    }

    private void callApi(NSZC001001PMsg inPmsg) {
        NSZC001001 api = new NSZC001001();
        api.execute(inPmsg, ONBATCH_TYPE.BATCH);
    }

    // START 2015/12/04 T.Tomita [QC#1500, MOD]
    private NSZC001001PMsg setApiParamForSvcExchange(MechMstrData data, List<Map<String, Object>> configMdseList) {
    // END 2015/12/04 T.Tomita [QC#1500, MOD]
        NSZC001001PMsg pMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.SERVICE_EXCHANGE.code);
        // START 2015/12/11 T.Tomita [QC#1799, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, getSvcConfigMstrPk(data.getDsCpoConfigPk(), data.getSvcConfigMstrPk(),  data.getSvcMachTpCd()));
        // END 2015/12/11 T.Tomita [QC#1799, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, data.getSvcMachMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.serNum, data.getSerNum());
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, data.getMdseCd());
        // Mod Start 2018/03/02 QC#23425
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachTpCd, getSvcMachTpForSvcExchng(data.getMdseCd(), configMdseList));
        // Mod End 2018/03/02 QC#23425
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        ZYPEZDItemValueSetter.setValue(pMsg.locNm, data.getLocNm());
        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr, data.getFirstLineAddr());
        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr, data.getScdLineAddr());
        ZYPEZDItemValueSetter.setValue(pMsg.thirdLineAddr, data.getThirdLineAddr());
        ZYPEZDItemValueSetter.setValue(pMsg.frthLineAddr, data.getFrthLineAddr());
        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr, data.getCtyAddr());
        ZYPEZDItemValueSetter.setValue(pMsg.stCd, data.getStCd());
        ZYPEZDItemValueSetter.setValue(pMsg.provNm, data.getProvNm());
        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm, data.getCntyNm());
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, data.getPostCd());
        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd, data.getCtryCd());
        ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, data.getStkStsCd());
        ZYPEZDItemValueSetter.setValue(pMsg.shipFromWhCd, data.getShipFromWhCd());
        ZYPEZDItemValueSetter.setValue(pMsg.shipDt, data.getShipDt());
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, data.getCpoOrdNum());
        ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineNum, data.getCpoDtlLineNum());
        ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineSubNum, data.getCpoDtlLineSubNum());
        ZYPEZDItemValueSetter.setValue(pMsg.custIssPoNum, data.getCustIssPoNum());
        ZYPEZDItemValueSetter.setValue(pMsg.shpgPlnNum, data.getShpgPlnNum());
        ZYPEZDItemValueSetter.setValue(pMsg.soNum, data.getSoNum());
        ZYPEZDItemValueSetter.setValue(pMsg.soSlpNum, data.getSoSlpNum());
        ZYPEZDItemValueSetter.setValue(pMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, data.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdRsnCd, data.getDsOrdRsnCd());

        BigDecimal svcConfigMstrDtlPk = getConfigMstrDtl(data.getSvcConfigMstrPk(), data.getSvcMachMstrPk());
        if (ZYPCommonFunc.hasValue(svcConfigMstrDtlPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrDtlPk, svcConfigMstrDtlPk);
        }

        // Mod Start 2018/03/02 QC#23425
        if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.prntSvcMachMstrPk, data.getSvcMachMstrPk());
            ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum, data.getSerNum());
        } else {
            String configSvcMachTpCd;
            BigDecimal prntSvcMachMstrPk = null;
            for (Map<String, Object> configMdse : configMdseList) {
                configSvcMachTpCd = (String) configMdse.get("SVC_MACH_TP_CD");
                if (SVC_MACH_TP.MACHINE.equals(configSvcMachTpCd)) {
                    prntSvcMachMstrPk = (BigDecimal) configMdse.get("SVC_MACH_MSTR_PK");
                    break;
                }
            }

            SVC_MACH_MSTRTMsg svcMachMstrTmsg;
            if (ZYPCommonFunc.hasValue(prntSvcMachMstrPk)) {
                ZYPEZDItemValueSetter.setValue(pMsg.prntSvcMachMstrPk, prntSvcMachMstrPk);
                svcMachMstrTmsg = getSvcMachMstr(prntSvcMachMstrPk);
                if (svcMachMstrTmsg != null) {
                    ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum, svcMachMstrTmsg.serNum);
                }
            } else {
                SVC_CONFIG_MSTRTMsg svcConfigMsrTmsg = getConfigMstrForPk(data.getSvcConfigMstrPk());
                if (svcConfigMsrTmsg != null) {
                    ZYPEZDItemValueSetter.setValue(pMsg.prntSvcMachMstrPk, svcConfigMsrTmsg.svcMachMstrPk);
    
                    svcMachMstrTmsg = getSvcMachMstr(svcConfigMsrTmsg.svcMachMstrPk.getValue());
                    if (svcMachMstrTmsg != null) {
                        ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum, svcMachMstrTmsg.serNum);
                    }
                }
            }
        }
        // Mod End 2018/03/02 QC#23425
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_TRANSIT);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachTrxTpCd, data.getSvcMachTrxTpCd());
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_01, data.getCtrlFldTxt_01());
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_02, data.getCtrlFldTxt_02());
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_03, data.getCtrlFldTxt_03());
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_04, data.getCtrlFldTxt_04());
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_05, data.getCtrlFldTxt_05());
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_06, data.getCtrlFldTxt_06());
        ZYPEZDItemValueSetter.setValue(pMsg.prcContrNum, data.getPrcContrNum());
        ZYPEZDItemValueSetter.setValue(pMsg.hardDriveRmvInclFlg, data.getHardDriveRmvInclFlg());
        ZYPEZDItemValueSetter.setValue(pMsg.hardDriveEraseInclFlg, data.getHardDriveEraseInclFlg());
        ZYPEZDItemValueSetter.setValue(pMsg.leaseRtrnFeeInclFlg, data.getLeaseRtrnFeeInclFlg());
        ZYPEZDItemValueSetter.setValue(pMsg.dsKeyAcctFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.svcLicCnt, data.getSvcLicCnt());

        // Mod Start 2017/09/21 QC#20989
        setCmptMachList(pMsg, configMdseList);
//        int i = 0;
//        // START 2015/12/04 T.Tomita [QC#1500, MOD]
//        String baseCmptFlg;
//        for (Map<String, Object> mdse : configMdseList) {
//            baseCmptFlg = (String) mdse.get("BASE_CMPT_FLG");
//            if (ZYPCommonFunc.hasValue(baseCmptFlg)) {
//                ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).mdseCd, (String) mdse.get("MDSE_CD"));
//                if (ZYPConstant.FLG_ON_Y.equals(baseCmptFlg)) {
//                    ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).svcMachTpCd, SVC_MACH_TP.MACHINE);
//                } else {
//                    ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).svcMachTpCd, SVC_MACH_TP.ACCESSORY);
//                }
//                i++;
//            }
//        }
//        // END 2015/12/04 T.Tomita [QC#1500, MOD]
//        pMsg.xxCmptMachList.setValidCount(i);
        // Mod End 2017/09/21 QC#20989

        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, data.getSvcMachMstrLocStsCd());
        ZYPEZDItemValueSetter.setValue(pMsg.ownrAcctNum, data.getOwnrAcctNum());
        ZYPEZDItemValueSetter.setValue(pMsg.ownrLocNum, data.getOwnrLocNum());
        ZYPEZDItemValueSetter.setValue(pMsg.billToAcctNum, data.getBillToAcctNum());
        ZYPEZDItemValueSetter.setValue(pMsg.billToLocNum, data.getBillToLocNum());
        ZYPEZDItemValueSetter.setValue(pMsg.curLocAcctNum, data.getCurLocAcctNum());
        ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, data.getCurLocNum());
        return pMsg;
    }

    // mod start 2016/07/29 CSA Defect#12642
    private NSZC001001PMsg setApiParamForWhTransfer(MechMstrData data, List<Map<String, Object>> configMdseList) {
        NSZC001001PMsg pMsg = new NSZC001001PMsg();
        SVC_MACH_MSTRTMsg machTMsg = getSvcMachMstr(data.getSvcMachMstrPk());

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.TRANSFER_OUT.code);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, data.getSvcMachMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, machTMsg.svcMachMstrStsCd);
        ZYPEZDItemValueSetter.setValue(pMsg.locNm, data.getLocNm());
        ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, data.getStkStsCd());
        ZYPEZDItemValueSetter.setValue(pMsg.shipFromWhCd, data.getShipFromWhCd());
        ZYPEZDItemValueSetter.setValue(pMsg.shipDt, data.getShipDt());
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, data.getSvcMachUsgStsCd());
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, data.getSvcMachMstrLocStsCd());
        ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, data.getCurLocNum());

        ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, getSvcConfigMstrPk(data.getDsCpoConfigPk(), data.getSvcConfigMstrPk(),  data.getSvcMachTpCd()));
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachTpCd, data.getSvcMachTpCd());
        // add start 2016/10/06 CSA Defect#12642
        ZYPEZDItemValueSetter.setValue(pMsg.pickSvcConfigMstrPk, data.getPickSvcConfigMstrPk());
        // add end 2016/10/06 CSA Defect#12642
        // START 2018/07/12 K.Kitachi [QC#26526, ADD]
        // QC#26526. Mod QC#30848
        boolean isKeep = isKeepConfigIdOrdTp(this.glblCmpyCd, data.getDsOrdTpCd());
        if (isKeep) {
            // QC#29406
            if (ZYPCommonFunc.hasValue(pMsg.svcConfigMstrPk) && !ZYPCommonFunc.hasValue(data.getPickSvcConfigMstrPk())) {
                pMsg.pickSvcConfigMstrPk.clear();
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, data.getPickSvcConfigMstrPk());
                pMsg.pickSvcConfigMstrPk.clear();
            }
        }
        // END 2018/07/12 K.Kitachi [QC#26526, ADD]
        if (SVC_MACH_TP.MACHINE.equals(data.getSvcMachTpCd())) {
            ZYPEZDItemValueSetter.setValue(pMsg.prntSvcMachMstrPk, data.getSvcMachMstrPk());
            ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum, data.getSerNum());
        } else {
            SVC_CONFIG_MSTRTMsg svcConfigMsrTmsg = getConfigMstrForPk(data.getSvcConfigMstrPk());
            if (svcConfigMsrTmsg != null) {
                ZYPEZDItemValueSetter.setValue(pMsg.prntSvcMachMstrPk, svcConfigMsrTmsg.svcMachMstrPk);

                SVC_MACH_MSTRTMsg svcMachMstrTmsg = getSvcMachMstr(svcConfigMsrTmsg.svcMachMstrPk.getValue());
                if (svcMachMstrTmsg != null) {
                    ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum, svcMachMstrTmsg.serNum);
                }
            }
        }

        // Mod Start 2017/09/21 QC#20989
        setCmptMachList(pMsg, configMdseList);
//        int i = 0;
//        String baseCmptFlg;
//        for (Map<String, Object> mdse : configMdseList) {
//            baseCmptFlg = (String) mdse.get("BASE_CMPT_FLG");
//            if (ZYPCommonFunc.hasValue(baseCmptFlg)) {
//                ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).mdseCd, (String) mdse.get("MDSE_CD"));
//                if (ZYPConstant.FLG_ON_Y.equals(baseCmptFlg)) {
//                    ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).svcMachTpCd, SVC_MACH_TP.MACHINE);
//                } else {
//                    ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).svcMachTpCd, SVC_MACH_TP.ACCESSORY);
//                }
//                i++;
//            }
//        }
//        pMsg.xxCmptMachList.setValidCount(i);
        // Mod End 2017/09/21 QC#20989
        return pMsg;
    }
    // mod end 2016/07/29 CSA Defect#12642

    // START 2015/12/04 T.Tomita [QC#1500, MOD]
    private NSZC001001PMsg setApiParamForShip(MechMstrData data, List<Map<String, Object>> configMdseList) {
    // END 2015/12/04 T.Tomita [QC#1500, MOD]
        NSZC001001PMsg pMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.SHIPMENT.code);
        // START 2015/12/11 T.Tomita [QC#1799, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, getSvcConfigMstrPk(data.getDsCpoConfigPk(), data.getSvcConfigMstrPk(),  data.getSvcMachTpCd()));
        // END 2015/12/11 T.Tomita [QC#1799, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, data.getSvcMachMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.serNum, data.getSerNum());
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, data.getMdseCd());
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachTpCd, data.getSvcMachTpCd());
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        ZYPEZDItemValueSetter.setValue(pMsg.locNm, data.getLocNm());
        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr, data.getFirstLineAddr());
        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr, data.getScdLineAddr());
        ZYPEZDItemValueSetter.setValue(pMsg.thirdLineAddr, data.getThirdLineAddr());
        ZYPEZDItemValueSetter.setValue(pMsg.frthLineAddr, data.getFrthLineAddr());
        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr, data.getCtyAddr());
        ZYPEZDItemValueSetter.setValue(pMsg.stCd, data.getStCd());
        ZYPEZDItemValueSetter.setValue(pMsg.provNm, data.getProvNm());
        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm, data.getCntyNm());
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, data.getPostCd());
        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd, data.getCtryCd());
        ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, data.getStkStsCd());
        ZYPEZDItemValueSetter.setValue(pMsg.shipFromWhCd, data.getShipFromWhCd());
        ZYPEZDItemValueSetter.setValue(pMsg.shipDt, data.getShipDt());
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, data.getCpoOrdNum());
        ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineNum, data.getCpoDtlLineNum());
        ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineSubNum, data.getCpoDtlLineSubNum());
        ZYPEZDItemValueSetter.setValue(pMsg.custIssPoNum, data.getCustIssPoNum());
        ZYPEZDItemValueSetter.setValue(pMsg.shpgPlnNum, data.getShpgPlnNum());
        ZYPEZDItemValueSetter.setValue(pMsg.soNum, data.getSoNum());
        ZYPEZDItemValueSetter.setValue(pMsg.soSlpNum, data.getSoSlpNum());
        ZYPEZDItemValueSetter.setValue(pMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, data.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdRsnCd, data.getDsOrdRsnCd());

        BigDecimal svcConfigMstrDtlPk = getConfigMstrDtl(data.getSvcConfigMstrPk(), data.getSvcMachMstrPk());
        if (ZYPCommonFunc.hasValue(svcConfigMstrDtlPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrDtlPk, svcConfigMstrDtlPk);
        }

        if (SVC_MACH_TP.MACHINE.equals(data.getSvcMachTpCd())) {
            ZYPEZDItemValueSetter.setValue(pMsg.prntSvcMachMstrPk, data.getSvcMachMstrPk());
            ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum, data.getSerNum());
        } else {
            SVC_CONFIG_MSTRTMsg svcConfigMsrTmsg = getConfigMstrForPk(data.getSvcConfigMstrPk());
            if (svcConfigMsrTmsg != null) {
                ZYPEZDItemValueSetter.setValue(pMsg.prntSvcMachMstrPk, svcConfigMsrTmsg.svcMachMstrPk);

                SVC_MACH_MSTRTMsg svcMachMstrTmsg = getSvcMachMstr(svcConfigMsrTmsg.svcMachMstrPk.getValue());
                if (svcMachMstrTmsg != null) {
                    ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum, svcMachMstrTmsg.serNum);
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_TRANSIT);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachTrxTpCd, data.getSvcMachTrxTpCd());
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_01, data.getCtrlFldTxt_01());
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_02, data.getCtrlFldTxt_02());
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_03, data.getCtrlFldTxt_03());
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_04, data.getCtrlFldTxt_04());
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_05, data.getCtrlFldTxt_05());
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_06, data.getCtrlFldTxt_06());
        ZYPEZDItemValueSetter.setValue(pMsg.prcContrNum, data.getPrcContrNum());
        ZYPEZDItemValueSetter.setValue(pMsg.hardDriveRmvInclFlg, data.getHardDriveRmvInclFlg());
        ZYPEZDItemValueSetter.setValue(pMsg.hardDriveEraseInclFlg, data.getHardDriveEraseInclFlg());
        ZYPEZDItemValueSetter.setValue(pMsg.leaseRtrnFeeInclFlg, data.getLeaseRtrnFeeInclFlg());
        ZYPEZDItemValueSetter.setValue(pMsg.dsKeyAcctFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.svcLicCnt, data.getSvcLicCnt());
        // add start 2016/10/06 CSA Defect#12642
        ZYPEZDItemValueSetter.setValue(pMsg.pickSvcConfigMstrPk, data.getPickSvcConfigMstrPk());
        // add end 2016/10/06 CSA Defect#12642

        // Mod Start 2017/09/21 QC#20989
        setCmptMachList(pMsg, configMdseList);
//        int i = 0;
//        // START 2015/12/04 T.Tomita [QC#1500, MOD]
//        String baseCmptFlg;
//        for (Map<String, Object> mdse : configMdseList) {
//            baseCmptFlg = (String) mdse.get("BASE_CMPT_FLG");
//            if (ZYPCommonFunc.hasValue(baseCmptFlg)) {
//                ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).mdseCd, (String) mdse.get("MDSE_CD"));
//                if (ZYPConstant.FLG_ON_Y.equals(baseCmptFlg)) {
//                    ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).svcMachTpCd, SVC_MACH_TP.MACHINE);
//                } else {
//                    ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).svcMachTpCd, SVC_MACH_TP.ACCESSORY);
//                }
//                i++;
//            }
//        }
//        // END 2015/12/04 T.Tomita [QC#1500, MOD]
//        pMsg.xxCmptMachList.setValidCount(i);
        // Mod End 2017/09/21 QC#20989

        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, data.getSvcMachMstrLocStsCd());
        ZYPEZDItemValueSetter.setValue(pMsg.ownrAcctNum, data.getOwnrAcctNum());
        ZYPEZDItemValueSetter.setValue(pMsg.ownrLocNum, data.getOwnrLocNum());
        ZYPEZDItemValueSetter.setValue(pMsg.billToAcctNum, data.getBillToAcctNum());
        ZYPEZDItemValueSetter.setValue(pMsg.billToLocNum, data.getBillToLocNum());
        ZYPEZDItemValueSetter.setValue(pMsg.curLocAcctNum, data.getCurLocAcctNum());
        ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, data.getCurLocNum());

        // Dealer Sales
        if (isDealerOrd(data.getDsOrdTpCd())) {
            pMsg.svcConfigMstrPk.clear();
            pMsg.pickSvcConfigMstrPk.clear();
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.DEALER_SERVICE);
            // add start 2016/10/11 CSA Defect#14708
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.AT_CUSTOMER);
            // add end 2016/10/11 CSA Defect#14708
        } else {
            // START 2017/11/21 K.Ochiai [QC#21698, MOD]
            List<Map<String, Object>> ctxMapList = getIbOwnerCd(data.getCpoOrdNum(), "IB_OWNER_OVERRIDE");
            // END 2017/11/21 K.Ochiai [QC#21698, MOD]
            if (ctxMapList.size() > 0) {
                // Rental/Loan/EMSD
                String firstBizCtxAttrbTxt = (String) ctxMapList.get(0).get("FIRST_BIZ_CTX_ATTRB_TXT");
                // START 2016/01/18 T.Tomita [QC#2525, MOD]
                ZYPEZDItemValueSetter.setValue(pMsg.ownrAcctNum, getSellToCustCd(firstBizCtxAttrbTxt));
                // END 2016/01/18 T.Tomita [QC#2525, MOD]
                ZYPEZDItemValueSetter.setValue(pMsg.ownrLocNum, firstBizCtxAttrbTxt);
            }
        }
        return pMsg;
    }

    // START 2017/11/21 K.Ochiai [QC#21698, ADD]
    private List<Map<String, Object>> getIbOwnerCd(String cpoOrdNum, String ordCatgCtxTpCd) {
        if (!ZYPCommonFunc.hasValue(cpoOrdNum) || !ZYPCommonFunc.hasValue(ordCatgCtxTpCd)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("ordCatgCtxTpCd", ordCatgCtxTpCd);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rtnMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getIbOwnerCd", param);
        return rtnMapList;
    }
    // END 2017/11/21 K.Ochiai [QC#21698, ADD]

    private SHIP_SER_NUMTMsgArray getShipSerNum(String soNum, String soSlpNum) {
        if (!ZYPCommonFunc.hasValue(soNum) || !ZYPCommonFunc.hasValue(soSlpNum)) {
            return new SHIP_SER_NUMTMsgArray();
        }

        SHIP_SER_NUMTMsg shipSerNumTMsgCond = new SHIP_SER_NUMTMsg();
        shipSerNumTMsgCond.setSQLID("004");
        shipSerNumTMsgCond.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        shipSerNumTMsgCond.setConditionValue("soNum01", soNum);
        shipSerNumTMsgCond.setConditionValue("soSlpNum01", soSlpNum);

        return (SHIP_SER_NUMTMsgArray) EZDTBLAccessor.findByCondition(shipSerNumTMsgCond);
    }

    private PO_SER_NUMTMsgArray getPoSerNum(String keyInfoCd01, String keyInfoCd02, String keyInfoCd03) {
        if (!ZYPCommonFunc.hasValue(keyInfoCd01) || !ZYPCommonFunc.hasValue(keyInfoCd02) || !ZYPCommonFunc.hasValue(keyInfoCd03)) {
            return new PO_SER_NUMTMsgArray();
        }

        PO_SER_NUMTMsg poSerNumTMsgCond = new PO_SER_NUMTMsg();
        poSerNumTMsgCond.setSQLID("005");
        poSerNumTMsgCond.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        poSerNumTMsgCond.setConditionValue("keyInfoCd_0101", keyInfoCd01);
        poSerNumTMsgCond.setConditionValue("keyInfoCd_0201", keyInfoCd02);
        poSerNumTMsgCond.setConditionValue("keyInfoCd_0301", keyInfoCd03);

        return (PO_SER_NUMTMsgArray) EZDTBLAccessor.findByCondition(poSerNumTMsgCond);
    }

    // START 2017/03/21 K.Kojima [QC#16600,MOD]
    // private SVC_MACH_MSTRTMsg getSvcMachMstrForSerNum(String serNum) {
    //     if (!ZYPCommonFunc.hasValue(serNum)) {
    //         return null;
    //     }
    //     SVC_MACH_MSTRTMsg param = new SVC_MACH_MSTRTMsg();
    //     param.setSQLID("002");
    //     param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
    //     param.setConditionValue("serNum01", serNum);
    // 
    //     SVC_MACH_MSTRTMsgArray rtnList = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(param);
    //     if (rtnList.getValidCount() == 0) {
    //         return null;
    //     }
    //     return rtnList.no(0);
    // }
    private SVC_MACH_MSTRTMsg getSvcMachMstrForSerNum(String serNum, String mdseCd) {
        if (!ZYPCommonFunc.hasValue(serNum) || !ZYPCommonFunc.hasValue(mdseCd)) {
            return null;
        }
        SVC_MACH_MSTRTMsg param = new SVC_MACH_MSTRTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("serNum01", serNum);
        param.setConditionValue("mdseCd01", mdseCd);

        SVC_MACH_MSTRTMsgArray rtnList = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(param);
        if (rtnList.getValidCount() == 0) {
            return null;
        }
        return rtnList.no(0);
    }
    // END 2017/03/21 K.Kojima [QC#16600,MOD]

    private SVC_MACH_MSTRTMsg getSvcMachMstrForPk(BigDecimal svcMachMstrPk) {
        if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            return null;
        }
        SVC_MACH_MSTRTMsg param = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.svcMachMstrPk, svcMachMstrPk);

        return (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(param);
    }

    private List<BigDecimal> getSvcMachMstrForNonSerNum(String mdseCd, String curLocNum, String stkStkCd) {
        if (!ZYPCommonFunc.hasValue(mdseCd) || !ZYPCommonFunc.hasValue(curLocNum) || !ZYPCommonFunc.hasValue(stkStkCd)) {
            return new ArrayList<BigDecimal>();
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("mdseCd", mdseCd);
        param.put("curLocNum", curLocNum);
        param.put("stkStkCd", stkStkCd);
        // START 2018/07/09 K.Ogino QC#26242
        param.put("flgY", ZYPConstant.FLG_ON_Y);
        // END 2018/07/09 K.Ogino QC#26242
        String[] svcMachMstrStsCdList = new String[] {SVC_MACH_MSTR_STS.CREATED, SVC_MACH_MSTR_STS.REMOVED };
        param.put("svcMachMstrStsCdList", svcMachMstrStsCdList);
        // START 2018/02/22 U.Kim [QC#24192, ADD]
        String[] svcMachUsgStsCdList = new String[] {SVC_MACH_USG_STS.IN_INVENTORY, SVC_MACH_USG_STS.RETURNED};
        param.put("svcMachUsgStsCdList", svcMachUsgStsCdList);
        // END 2018/02/22 U.Kim [QC#24192, ADD]

        @SuppressWarnings("unchecked")
        List<BigDecimal> rtnList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getSvcMachMstrForNonSerNum", param);
        return rtnList;
    }

    private BigDecimal getConfigMstrDtl(BigDecimal svcConfigMstrPk, BigDecimal svcMachMstrPk) {
        if (!ZYPCommonFunc.hasValue(svcConfigMstrPk) || !ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcConfigMstrPk", svcConfigMstrPk);
        param.put("svcMachMstrPk", svcMachMstrPk);

        return (BigDecimal) ssmBatchClient.queryObject("getConfigMstrDtl", param);
    }

    private SVC_CONFIG_MSTRTMsg getConfigMstrForPk(BigDecimal svcConfigMstrPk) {
        if (!ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
            return null;
        }
        SVC_CONFIG_MSTRTMsg param = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.svcConfigMstrPk, svcConfigMstrPk);

        return (SVC_CONFIG_MSTRTMsg) EZDTBLAccessor.findByKey(param);
    }

    private SVC_MACH_MSTRTMsg getSvcMachMstr(BigDecimal svcMachMstrPk) {
        if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            return null;
        }
        SVC_MACH_MSTRTMsg param = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.svcMachMstrPk, svcMachMstrPk);

        return (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(param);
    }

    // START 2015/12/04 T.Tomita [QC#1500, MOD]
    private List<Map<String, Object>> getMdseCdForConfig(String cpoOrdNum, BigDecimal dsCpoConfigPk) {
        if (!ZYPCommonFunc.hasValue(cpoOrdNum) || !ZYPCommonFunc.hasValue(dsCpoConfigPk)) {
            return new ArrayList<Map<String, Object>>();
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("dsCpoConfigPk", dsCpoConfigPk);
        // Add Start 2017/09/21 QC#20989
        param.put("svcMachTpCdMach", SVC_MACH_TP.MACHINE);
        param.put("svcMachTpCdAcc", SVC_MACH_TP.ACCESSORY);
        // Add End 2017/09/21 QC#20989

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rtnList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMdseCdForConfig", param);
        return rtnList;
    }
    // END 2015/12/04 T.Tomita [QC#1500, MOD]
    // Add Start 2018/03/02 QC#23425
    private List<Map<String, Object>> getMdseCdForSvcExchng(String cpoOrdNum, BigDecimal dsCpoConfigPk, BigDecimal svcConfigMstrPk) {
        if (!ZYPCommonFunc.hasValue(cpoOrdNum) || !ZYPCommonFunc.hasValue(dsCpoConfigPk) || !ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
            return new ArrayList<Map<String, Object>>();
        }
        List<Map<String, Object>> rtnList = new ArrayList<Map<String,Object>>();
        boolean existsMachFlg = false;
        // Exists Config Machine
        rtnList = getExistConfigMachForSvcExchng(cpoOrdNum, svcConfigMstrPk);
        String svcMachTpCd = null;
        for (Map<String, Object> existsData : rtnList) {
            svcMachTpCd = (String) existsData.get("SVC_MACH_TP_CD");
            if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
                existsMachFlg = true;
                break;
            }
        }
        List<Map<String, Object>> newConfigList = getNewConfigMachForSvcExchng(cpoOrdNum, dsCpoConfigPk, svcConfigMstrPk);
        for (Map<String, Object> newData : newConfigList) {
            if (existsMachFlg) {
                newData.put("SVC_MACH_TP_CD", SVC_MACH_TP.ACCESSORY);
            }
            rtnList.add(newData);
        }

        return rtnList;
    }

    private List<Map<String, Object>> getExistConfigMachForSvcExchng(String cpoOrdNum, BigDecimal svcConfigMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("svcConfigMstrPk", svcConfigMstrPk);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getExistConfigMachForSvcExchng", param);
    }

    private List<Map<String, Object>> getNewConfigMachForSvcExchng(String cpoOrdNum, BigDecimal dsCpoConfigPk, BigDecimal svcConfigMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("dsCpoConfigPk", dsCpoConfigPk);
        param.put("svcConfigMstrPk", svcConfigMstrPk);
        param.put("svcMachTpCdMach", SVC_MACH_TP.MACHINE);
        param.put("svcMachTpCdAcc", SVC_MACH_TP.ACCESSORY);
        param.put("configCatgOut", CONFIG_CATG.OUTBOUND);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getNewConfigMachForSvcExchng", param);
    }

    private String getSvcMachTpForSvcExchng(String mdseCd, List<Map<String, Object>> configMdseList) {
        String svcMachTpCd = SVC_MACH_TP.ACCESSORY;
        String configMdseCd;
        for (Map<String, Object> data : configMdseList) {
            configMdseCd = (String) data.get("MDSE_CD");
            if (mdseCd.equals(configMdseCd)) {
                svcMachTpCd = (String) data.get("SVC_MACH_TP_CD");
            }
        }
        return svcMachTpCd;
    }

    private boolean existsMainMach(List<Map<String, Object>> configMdseList) {
        String svcMachTpCd = null;
        for (Map<String, Object> data : configMdseList) {
            svcMachTpCd = (String) data.get("SVC_MACH_TP_CD");
            if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
                return true;
            }
        }
        return false;
    }
    // Add End 2018/03/02 QC#23425

    private List<Map<String, Object>> getOrdCatgBizCtxForDsOrdTp(String dsOrdTpCd, String ordCatgCtxTpCd) {
        if (!ZYPCommonFunc.hasValue(dsOrdTpCd) || !ZYPCommonFunc.hasValue(ordCatgCtxTpCd)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsaOrdTpCd", dsOrdTpCd);
        param.put("ordCatgCtxTpCd", ordCatgCtxTpCd);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rtnMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getOrdCatgBizCtxForDsOrdTp", param);
        return rtnMapList;
    }

    private String getSvcMachTrxTpCd(String dsOrdTpCd) {
        if (!ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            return null;
        }
        List<Map<String, Object>> rsltMapList = getOrdCatgBizCtxForDsOrdTp(dsOrdTpCd, "IB_TRX_ORD_MAP");

        if (rsltMapList.size() == 0) {
            return null;
        }

        String svcMachTrxTpCd = (String) rsltMapList.get(0).get("FIRST_BIZ_CTX_ATTRB_TXT");
        if (ZYPCommonFunc.hasValue(svcMachTrxTpCd) && svcMachTrxTpCd.length() > SVC_MACH_TRX_TP_CD_LEN) {
            svcMachTrxTpCd = svcMachTrxTpCd.substring(0, SVC_MACH_TRX_TP_CD_LEN);
        }

        return svcMachTrxTpCd;
    }

    // START 2015/12/11 T.Tomita [QC#1799, ADD]
    private BigDecimal getSvcConfigMstrPk(BigDecimal dsCpoConfigPk, BigDecimal svcConfigMstrPk, String svcMachTpCd) {
        if (ZYPCommonFunc.hasValue(svcConfigMstrPk) || SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
            return svcConfigMstrPk;
        }

        // get Accessory SVC_CONFIG_MSTR_PK
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsCpoConfigPk", dsCpoConfigPk);

        return (BigDecimal) ssmBatchClient.queryObject("getSvcConfigMstrPkOfAcc", param);
    }
    // END 2015/12/11 T.Tomita [QC#1799, ADD]

    // START 2016/01/18 T.Tomita [QC#2525, MOD]
    private String getSellToCustCd(String billToCustCd) {
        if (!ZYPCommonFunc.hasValue(billToCustCd)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("billToCustCd", billToCustCd);

        return (String) ssmBatchClient.queryObject("getSellToCustCd", param);
    }
    // END 2016/01/18 T.Tomita [QC#2525, MOD]

    // START 2017/03/22 K.Kitachi [QC#15679, ADD]
    private BigDecimal getSvcMachMstrPk(String mdseCd, String invtyLocCd, String stkStsCd) {
        if (!ZYPCommonFunc.hasValue(mdseCd) || !ZYPCommonFunc.hasValue(invtyLocCd) || !ZYPCommonFunc.hasValue(stkStsCd)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("mdseCd", mdseCd);
        param.put("invtyLocCd", invtyLocCd);
        param.put("stkStsCd", stkStsCd);
        List<String> svcMachMstrStsCdList = new ArrayList<String>();
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.CREATED);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.REMOVED);
        param.put("svcMachMstrStsCdList", svcMachMstrStsCdList);

        return (BigDecimal) this.ssmBatchClient.queryObject("getSvcMachMstrPk", param);
    }

    private NSZC001001PMsg updateSvcMachMstr(ResultSet rs, String serNum, BigDecimal svcMachMstrPk) throws SQLException {
        NSZC001001PMsg pMsg = new NSZC001001PMsg();
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(svcMachMstrPk);

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.UPDATE_WAREHOUSE.code);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.serNum, serNum);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, svcMachMstrTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.custMachCtrlNum, serNum);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, svcMachMstrTMsg.svcMachMstrStsCd);
        ZYPEZDItemValueSetter.setValue(pMsg.oldSerNum, svcMachMstrTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(pMsg.iwrCondCd, svcMachMstrTMsg.iwrCondCd);
        ZYPEZDItemValueSetter.setValue(pMsg.istlDt, svcMachMstrTMsg.istlDt);
        ZYPEZDItemValueSetter.setValue(pMsg.effFromDt, svcMachMstrTMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(pMsg.effThruDt, svcMachMstrTMsg.effThruDt);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, svcMachMstrTMsg.svcMachUsgStsCd);
        ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, svcMachMstrTMsg.stkStsCd);
        ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum, svcMachMstrTMsg.prntSerNum);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachTrxTpCd, svcMachMstrTMsg.svcMachTrxTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, svcMachMstrTMsg.curLocNum);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachQty, svcMachMstrTMsg.svcMachQty);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, svcMachMstrTMsg.svcMachMstrLocStsCd);
        // Mod Start 2017/09/21 QC#20989
        // Del Start 2019/02/19 QC#30398
//        List<Map<String, Object>> configMdseList = getMdseCdForConfig(rs.getString("CPO_ORD_NUM"), rs.getBigDecimal("DS_CPO_CONFIG_PK"));
//        setCmptMachList(pMsg, configMdseList);
        // Del End 2019/02/19 QC#30398
//        int i = 0;
//        String baseCmptFlg;
//        List<Map<String, Object>> configMdseList = getMdseCdForConfig(rs.getString("CPO_ORD_NUM"), rs.getBigDecimal("DS_CPO_CONFIG_PK"));
//        for (Map<String, Object> mdse : configMdseList) {
//            baseCmptFlg = (String) mdse.get("BASE_CMPT_FLG");
//            if (ZYPCommonFunc.hasValue(baseCmptFlg)) {
//                ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).mdseCd, (String) mdse.get("MDSE_CD"));
//                if (ZYPConstant.FLG_ON_Y.equals(baseCmptFlg)) {
//                    ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).svcMachTpCd, SVC_MACH_TP.MACHINE);
//                } else {
//                    ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).svcMachTpCd, SVC_MACH_TP.ACCESSORY);
//                }
//                i++;
//            }
//        }
//        pMsg.xxCmptMachList.setValidCount(i);
        // Mod End 2017/09/21 QC#20989

        // Call API
        NSZC001001 api = new NSZC001001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        return pMsg;
    }
    // END 2017/03/22 K.Kitachi [QC#15679, ADD]

    private S21SsmExecutionParameter getExecPrm() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    private void setApiErrorInfo(String shpgPlnNum, String apiBizId, String xxMsgId) {
        String[] params = new String[] {apiBizId, xxMsgId, "shpgPlnNum=" + shpgPlnNum };
        setErrorInfo(NSAM0003E, params);
    }

    private void setErrorInfo(String msgId, String[] params) {
        S21InfoLogOutput.println(msgId, params);
        this.apiErrMsgList.add(S21MessageFunc.clspGetMessage(msgId, params));
    }

    private void sendEmail() {

        if (msgList.isEmpty()) {
            return;
        }

        S21MailGroup fromGrp = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();

        S21Mail mail = new S21Mail(glblCmpyCd);

        if (fromAddrList.size() > 0) {

            mail.setFromAddress(fromAddrList.get(0));

            S21MailGroup toGrp = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
            List<S21MailAddress> toAddrList = toGrp.getMailAddress();
            if (!toAddrList.isEmpty()) {

                mail.setToAddressList(toAddrList);

                S21MailTemplate tmpl = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);

                if (ZYPCommonFunc.hasValue(tmpl.getSubject())) {

                    String newLine = System.getProperty("line.separator");

                    StringBuilder msgBuf = new StringBuilder();
                    for (String msg : msgList) {
                        msgBuf.append(msg);
                        msgBuf.append(newLine);
                    }

                    SimpleDateFormat errTmFmt = new SimpleDateFormat(DATE_TIME_PATTERN_FOR_MAIL);

                    tmpl.setTemplateParameter("batchId", BUSINESS_ID);
                    tmpl.setTemplateParameter("errDate", errTmFmt.format(new Date()));
                    tmpl.setTemplateParameter("message", msgBuf.toString());

                    mail.setMailTemplate(tmpl);
                    mail.postMail();
                }
            }
        }
    }
    // Add Start 2017/09/21 QC#20989
    private void setCmptMachList(NSZC001001PMsg pMsg, List<Map<String, Object>> configMdseList) {
        int i = 0;
        for (Map<String, Object> mdse : configMdseList) {
            //QC#56209 Add Start
            if (ZYPCommonFunc.hasValue((BigDecimal)mdse.get("SVC_MACH_MSTR_PK"))) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).svcMachMstrPk, (BigDecimal)mdse.get("SVC_MACH_MSTR_PK"));
            }
            //QC#56209 Add End
            ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).mdseCd, (String) mdse.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).svcMachTpCd, (String) mdse.get("SVC_MACH_TP_CD"));
            i++;
        }
        pMsg.xxCmptMachList.setValidCount(i);
    }
    // Add End 2017/09/21 QC#20989
    // Add Start 2017/10/05 QC#21565
    private NSZC001001PMsg setApiParamForIBTrmnOrd(MechMstrData data) {
        NSZC001001PMsg pMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.UPDATE_TERMINATION.code);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, data.getSvcMachMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.TERMINATED);
        ZYPEZDItemValueSetter.setValue(pMsg.effThruDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.GONE);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachQty, BigDecimal.ONE);
        // Add Start 2017/10/17 QC#21798
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, data.getCpoOrdNum());
        ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineNum, data.getCpoDtlLineNum());
        ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineSubNum, data.getCpoDtlLineSubNum());
        ZYPEZDItemValueSetter.setValue(pMsg.shpgPlnNum, data.getShpgPlnNum());
        ZYPEZDItemValueSetter.setValue(pMsg.soNum, data.getSoNum());
        ZYPEZDItemValueSetter.setValue(pMsg.soSlpNum, data.getSoSlpNum());
        // Add End 2017/10/17 QC#21798

        return pMsg;
    }
    // Add End 2017/10/05 QC#21565

    // START 2017/10/16 M.Naito [QC#20246, ADD]
    private List<Map<String, Object>> getCpoCtacInfo(String cpoOrdNum, BigDecimal dsCpoConfigPk) {
        if (!ZYPCommonFunc.hasValue(cpoOrdNum) || !ZYPCommonFunc.hasValue(dsCpoConfigPk)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("dsCpoConfigPk", dsCpoConfigPk);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rtnList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getCpoCtacInfo", param);
        return rtnList;
    }

    private void insertSvcMachCtacPsn(BigDecimal svcMachMstrPk, List<Map<String, Object>> cpoCtacList) {
        if (!ZYPCommonFunc.hasValue(svcMachMstrPk) || cpoCtacList == null) {
            return;
        }

        for (Map<String, Object> ctac : cpoCtacList) {
            // check SVC_MACH_CTAC_PSN data
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("svcMachMstrPk", svcMachMstrPk);
            param.put("dsCtacPntPk", (BigDecimal) ctac.get("DS_CTAC_PNT_PK"));
            param.put("svcCtacTpCd", (String) ctac.get("SVC_CTAC_TP_CD"));
            param.put("slsDt", this.slsDt);
            param.put("defEffThruDt", DEF_EFF_THRU_DT);
            BigDecimal chkSvcMachCtacPsn = (BigDecimal) ssmBatchClient.queryObject("countSvcMachCtacPsn", param);
            if (BigDecimal.ZERO.compareTo(chkSvcMachCtacPsn) < 0) {
                continue;
            }

            SVC_MACH_CTAC_PSNTMsg tMsg = new SVC_MACH_CTAC_PSNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.svcMachCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_MACH_CTAC_PSN_SQ"));
            ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
            ZYPEZDItemValueSetter.setValue(tMsg.dsCtacPntPk, (BigDecimal) ctac.get("DS_CTAC_PNT_PK"));
            ZYPEZDItemValueSetter.setValue(tMsg.svcCtacTpCd, (String) ctac.get("SVC_CTAC_TP_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, this.slsDt);
            // START 2018/09/25 K.Kitachi [QC#27788, ADD]
            ZYPEZDItemValueSetter.setValue(tMsg.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
            // END 2018/09/25 K.Kitachi [QC#27788, ADD]
            EZDTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                setErrorInfo(NSAM0012E, new String[] {"SVC_MACH_CTAC_PSN", "glblCmpyCd=" + this.glblCmpyCd + ", svcMachMstrPk=" + tMsg.svcMachMstrPk.getValue() });
            }
        }
    }
    // END 2017/10/16 M.Naito [QC#20246, ADD]
    // Add Start 2017/11/29 QC#21242
    private boolean isMachMstrProcessed(ResultSet rs) throws SQLException {
        String svcMachProcStsCd = rs.getString("SVC_MACH_PROC_STS_CD");
        if (ZYPCommonFunc.hasValue(svcMachProcStsCd) && !SVC_MACH_PROC_STS.IN_COMPLETED.equals(svcMachProcStsCd)) {
            return true;
        }
        return false;
    }
    // Add End 2017/11/29 QC#21242
    // Add Start 2018/04/18 QC#19154
    private boolean isRecievedWHT(MechMstrData data) {
        BigDecimal svcMachMstrPk = data.getSvcMachMstrPk();
        String curLocNum = data.getCurLocNum();
        if (!ZYPCommonFunc.hasValue(svcMachMstrPk) || !ZYPCommonFunc.hasValue(curLocNum)) {
            return false;
        }

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(svcMachMstrPk);
        if (curLocNum.equals(svcMachMstrTMsg.curLocNum.getValue())) {
            return true;
        }
        return false;
    }
//    // Add End 2018/04/18 QC#19154
//    // START 2018/07/12 K.Kitachi [QC#26526, ADD]
//    private boolean isTecsys(String invtyLocCd) {
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", this.glblCmpyCd);
//        queryParam.put("invtyLocCd", invtyLocCd);
//        queryParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
//        queryParam.put("tecsys", MW_GRP_CD_TECSYS);
//
//        String wmsWhCd = (String) this.ssmBatchClient.queryObject("getWmsWhCdForTecsys", queryParam);
//        if (!ZYPCommonFunc.hasValue(wmsWhCd)) {
//            return false;
//        }
//        return true;
//    }
//    // END 2018/07/12 K.Kitachi [QC#26526, ADD]

    /**
     * isKeepConfigIdOrdTp
     * QC#30848 Add method.
     * @param glblCmpyCd String
     * @param dsOrdTpCd String
     */
    private boolean isKeepConfigIdOrdTp(String glblCmpyCd, String dsOrdTpCd){
        
        String varOrdTpCd = ZYPCodeDataUtil.getVarCharConstValue("KEEP_CONFIG_ID_ORD_TP  ", glblCmpyCd);

        if (varOrdTpCd != null) {

            List<String> ordTpCdList = new ArrayList<String>();

            String[] ordTpCds = varOrdTpCd.split(",");

            for (int i = 0; i < ordTpCds.length; i++) {

                ordTpCdList.add(ordTpCds[i]);
            }

            if (ordTpCdList.contains(dsOrdTpCd)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
