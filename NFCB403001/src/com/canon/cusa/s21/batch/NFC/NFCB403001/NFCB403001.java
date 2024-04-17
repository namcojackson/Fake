/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB403001;

import static com.canon.cusa.s21.batch.NFC.NFCB403001.constant.NFCB403001Constant.NFCM0531E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDDBCICarrier;

import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Create Territory Rule Month Snapshot
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/05/13   Fujitsu         M.Ohno           Create          N/A
 * </pre>
 */

public class NFCB403001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** SQL access parts */
    private S21SsmBatchClientCustom ssmBatchClientCustom = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    @Override
    protected void initRoutine() {
        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NFCM0531E, new String[] {"Global Company Code" });
        }
        // Initialize
        this.ssmBatchClientCustom = new S21SsmBatchClientCustom(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        // delete
        Map<String, String> param = new HashMap<String, String>();
        ssmBatchClientCustom.delete("truncateSnapShot", param);

        // select insert
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        try {
            String dateTime = EZDDBCICarrier.getStartDateTime();
            String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
            String upPgId = EZDDBCICarrier.getUppgID();
            String upTimeZone = EZDDBCICarrier.getUpTimeZone();
            String userId = EZDDBCICarrier.getUserID();
            param.put("ezintime", dateTime);
            param.put("ezintimezone", upTimeZone);
            param.put("ezincompanycode", upCmpyCd);
            param.put("ezinuserid", userId);
            param.put("ezinaplid", upPgId);

            param.put("ezuptime", dateTime);
            param.put("ezuptimezone", upTimeZone);
            param.put("ezupcompanycode", upCmpyCd);
            param.put("ezupuserid", userId);
            param.put("ezupaplid", upPgId);

            param.put("glblCmpyCd", this.glblCmpyCd);
            stmtSelect = this.ssmLLClient.createPreparedStatement("insertSnapShot", param);
            stmtSelect.executeUpdate();
            commit();
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFCB403001().executeBatch(NFCB403001.class.getSimpleName());

    }

}
