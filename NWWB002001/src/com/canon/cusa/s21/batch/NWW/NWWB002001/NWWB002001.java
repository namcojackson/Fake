/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWW.NWWB002001;

import static com.canon.cusa.s21.batch.NWW.NWWB002001.constant.NWWB002001Constant.NWWM0022E;
import static com.canon.cusa.s21.batch.NWW.NWWB002001.constant.NWWB002001Constant.ZZZM9025E;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.NTFY_SEND_EML_INFOTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Notification Purge Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/05   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWB002001 extends S21BatchMain {

    /** Term Code */
    private TERM_CD termCd;

    /** Error Record Count */
    private int errRecCnt;

    /** records */
    private int totalRecCnt;

    /** Normal Record Count */
    private int normalRecCnt;

    /** SSM */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDate = null;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWWB002001().executeBatch();
    }

    @Override
    protected void initRoutine() {
        this.normalRecCnt = 0;
        this.errRecCnt = 0;
        this.termCd = TERM_CD.NORMAL_END;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, toArray("Global Company Code"));
        }

        // Sales Date
        this.slsDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.slsDate)) {
            throw new S21AbendException(ZZZM9025E, toArray("Sales Date"));
        }
    }

    @Override
    protected void mainRoutine() {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("slsDt", this.slsDate);

        boolean rslt = (Boolean) ssmBatchClient.queryObject("getDelNtfySendEmlInfo", ssmParam, new DeleteNtfySendEmlInfo());
        if (!rslt) {
            if (this.normalRecCnt > 0) {
                this.termCd = TERM_CD.WARNING_END;
            } else {
                this.termCd = TERM_CD.ABNORMAL_END;
            }
        }
    }

    @Override
    protected void termRoutine() {
        this.totalRecCnt = this.normalRecCnt + this.errRecCnt;
        super.setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);
    }

    /**
     * ResultSet of SQL process.
     */
    protected class DeleteNtfySendEmlInfo extends S21SsmBooleanResultSetHandlerSupport {

        @Override
        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            Boolean rslt = Boolean.TRUE;
            int commitCount = getCommitCount();
            int tranCount = 0;

            while (rs.next()) {

                BigDecimal ntfySendEmlInfoPk = rs.getBigDecimal("NTFY_SEND_EML_INFO_PK");

                NTFY_SEND_EML_INFOTMsg ntfySendEmlInfoTMsg = new NTFY_SEND_EML_INFOTMsg();
                ZYPEZDItemValueSetter.setValue(ntfySendEmlInfoTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(ntfySendEmlInfoTMsg.ntfySendEmlInfoPk, ntfySendEmlInfoPk);

                EZDTBLAccessor.remove(ntfySendEmlInfoTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ntfySendEmlInfoTMsg.getReturnCode())) {
                    StringBuilder msg = new StringBuilder();
                    msg.append(ntfySendEmlInfoTMsg.getTableName());
                    msg.append(" [NTFY_SEND_EML_INFO_PK:");
                    msg.append(ntfySendEmlInfoPk);
                    msg.append("]");

                    S21InfoLogOutput.println(NWWM0022E, toArray(msg.toString()));
                    errRecCnt++;
                } else {
                    normalRecCnt++;
                }

                tranCount++;
                if (tranCount > commitCount) {
                    tranCount = 0;
                    commit();
                }
            }

            if (errRecCnt > 0) {
                rslt = Boolean.FALSE;
            }
            return rslt;
        }
    }

    /**
     * <pre>
     * To Array
     * </pre>
     * @param appendMsgList String[]
     * @return String[]
     */
    private String[] toArray(String... appendMsgList) {
        String[] msgArray = new String[appendMsgList.length];
        for (int i = 0; i < msgArray.length; i++) {
            msgArray[i] = appendMsgList[i];
        }
        return msgArray;
    }

}
