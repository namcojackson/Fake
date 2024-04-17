/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB496001;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CNTYTMsg;
import business.db.CNTY_POST_RELNTMsg;
import business.db.POSTTMsg;

import com.canon.cusa.s21.batch.NMA.NMAB496001.constant.NMAB496001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Location Mass Upload Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   SRAA            Y.Chen          Create          QC#4505
 *</pre>
 */

public class NMAB496001 extends S21BatchMain {

    /** SSM Client */
    private S21SsmBatchClient ssmBatClnt;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** CUSA Global Company Code */
    private String cusaGlblCmpyCd = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Fetch Size */
    private int fetchSize = 1000;

    public static void main(String[] args) {
        new NMAB496001().executeBatch(NMAB496001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;

        this.glblCmpyCd = this.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NMAB496001Constant.NMZM0001E);
        }

        this.cusaGlblCmpyCd = ZYPCodeDataUtil.getVarCharConstValue("CUSA_GLBL_CMPY_CD", this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cusaGlblCmpyCd)) {
            throw new S21AbendException(NMAB496001Constant.NMAM8432E, new String[] {"CUSA Global Company Code" });
        }

        this.ssmBatClnt = S21SsmBatchClient.getClient(getClass());
    }

    @Override
    protected void mainRoutine() {
        refreshPost();
        if (TERM_CD.ABNORMAL_END.equals(this.termCd)) {
            return;
        }

        refreshCntyPostReln();
        if (TERM_CD.ABNORMAL_END.equals(this.termCd)) {
            return;
        }

        refreshCnty();
        if (TERM_CD.ABNORMAL_END.equals(this.termCd)) {
            return;
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, normalCount, errorCount);
    }

    private void refreshPost() {
        Map<String, Object> ssmParam = getSsmParam();
        S21SsmExecutionParameter ssmExecParam = getSsmExecParam();
        this.ssmBatClnt.queryObject("getPostDelta", ssmParam, ssmExecParam, new PostDeltaHandler());
    }

    private void refreshCntyPostReln() {
        Map<String, Object> ssmParam = getSsmParam();
        S21SsmExecutionParameter ssmExecParam = getSsmExecParam();
        this.ssmBatClnt.queryObject("getCntyPostRelnDelta", ssmParam, ssmExecParam, new CntyPostRelnDeltaHandler());
    }

    private void refreshCnty() {
        Map<String, Object> ssmParam = getSsmParam();
        S21SsmExecutionParameter ssmExecParam = getSsmExecParam();
        this.ssmBatClnt.queryObject("getCntyDelta", ssmParam, ssmExecParam, new CntyDeltaHandler());
    }

    private Map<String, Object> getSsmParam() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("cusaGlblCmpyCd", this.cusaGlblCmpyCd);
        return ssmParam;
    }

    private S21SsmExecutionParameter getSsmExecParam() {
        S21SsmExecutionParameter ssmExecParam = new S21SsmExecutionParameter();
        ssmExecParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmExecParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmExecParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        ssmExecParam.setFetchSize(this.fetchSize);
        return ssmExecParam;
    }

    private abstract class DeltaHandler extends S21SsmVoidResultSetHandlerSupport {

        protected List<EZDTMsg> listCreate;

        protected List<EZDTMsg> listInsert;

        protected List<EZDTMsg> listUpdate;

        protected List<EZDTMsg> listDelete;

        DeltaHandler() {
            listCreate = new ArrayList<EZDTMsg>();
            listInsert = new ArrayList<EZDTMsg>();
            listUpdate = new ArrayList<EZDTMsg>();
            listDelete = new ArrayList<EZDTMsg>();
        }

        @Override
        protected void doProcessQueryResult(ResultSet rs) throws SQLException {
            while (rs.next()) {
                String deltaTp = rs.getString("DELTA_TP");

                if (NMAB496001Constant.DELTA_CREATE.equals(deltaTp)) {
                    EZDTMsg tMsg = buildTMsg(rs);
                    listCreate.add(tMsg);

                } else if (NMAB496001Constant.DELTA_INSERT.equals(deltaTp)) {
                    EZDTMsg tMsg = buildTMsg(rs);
                    listInsert.add(tMsg);

                } else if (NMAB496001Constant.DELTA_UPDATE.equals(deltaTp)) {
                    EZDTMsg tMsg = buildTMsg(rs);
                    listUpdate.add(tMsg);

                } else if (NMAB496001Constant.DELTA_DELETE.equals(deltaTp)) {
                    EZDTMsg tMsg = buildTMsg(rs);
                    listDelete.add(tMsg);
                }
            }

            if (listCreate.size() > 0) {
                if (!createData(listCreate.toArray(new EZDTMsg[0]))) {
                    return;
                }
            }
            if (listInsert.size() > 0) {
                if (!insertData(listInsert.toArray(new EZDTMsg[0]))) {
                    return;
                }
            }
            if (listUpdate.size() > 0) {
                if (!updateData(listUpdate.toArray(new EZDTMsg[0]))) {
                    return;
                }
            }
            if (listDelete.size() > 0) {
                if (!deleteData(listDelete.toArray(new EZDTMsg[0]))) {
                    return;
                }
            }

            S21InfoLogOutput.println("=================================================");
            S21InfoLogOutput.println("Process Result of [" + this.getClass().getSimpleName() + "]");
            S21InfoLogOutput.println("Create Count: [" + listCreate.size() + "]");
            S21InfoLogOutput.println("Insert Count: [" + listInsert.size() + "]");
            S21InfoLogOutput.println("Update Count: [" + listUpdate.size() + "]");
            S21InfoLogOutput.println("Delete Count: [" + listDelete.size() + "]");
            S21InfoLogOutput.println("=================================================");
        }

        protected abstract EZDTMsg buildTMsg(ResultSet rs) throws SQLException;

        private boolean createData(EZDTMsg[] tMsgArray) {
            for (EZDTMsg tMsg : tMsgArray) {
                EZDTBLAccessor.create(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    return checkDbOpertaionResult(new EZDTMsg[] {tMsg }, 0);
                } else {
                    normalCount++;
                }
            }
            return true;
        }

        private boolean insertData(EZDTMsg[] tMsgArray) {
            int resultCount = S21FastTBLAccessor.insert(tMsgArray);
            return checkDbOpertaionResult(tMsgArray, resultCount);
        }

        private boolean updateData(EZDTMsg[] tMsgArray) {
            int resultCount = S21FastTBLAccessor.update(tMsgArray);
            return checkDbOpertaionResult(tMsgArray, resultCount);
        }

        private boolean deleteData(EZDTMsg[] tMsgArray) {
            int resultCount = S21FastTBLAccessor.removeLogical(tMsgArray);
            return checkDbOpertaionResult(tMsgArray, resultCount);
        }

        private boolean checkDbOpertaionResult(EZDTMsg[] tMsgArray, int resultCount) {
            if (resultCount != tMsgArray.length) {
                String errLog = getErrorLog(tMsgArray, resultCount);
                S21InfoLogOutput.println(errLog);

                termCd = TERM_CD.ABNORMAL_END;
                errorCount += tMsgArray.length;
                rollback();
                return false;
            } else {
                normalCount += resultCount;
                return true;
            }
        }

        private String getErrorLog(EZDTMsg[] tMsgArray, int resultCount) {
            StringBuffer sb = new StringBuffer();
            sb.append("Request [" + tMsgArray.length + "] records, but [" + resultCount + "] records are processed.");
            for (int i = 0; i < tMsgArray.length; i++) {
                sb.append("\n").append("[" + tMsgArray[i] + "]");
            }
            return sb.toString();
        }
    }

    private class PostDeltaHandler extends DeltaHandler {
        protected EZDTMsg buildTMsg(ResultSet rs) throws SQLException {
            POSTTMsg tMsg = new POSTTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.postPk, rs.getBigDecimal("POST_PK"));
            ZYPEZDItemValueSetter.setValue(tMsg.postCd, rs.getString("POST_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, rs.getString("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.stCd, rs.getString("ST_CD"));
            return tMsg;
        }
    }

    private class CntyPostRelnDeltaHandler extends DeltaHandler {
        protected EZDTMsg buildTMsg(ResultSet rs) throws SQLException {
            CNTY_POST_RELNTMsg tMsg = new CNTY_POST_RELNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.postPk, rs.getBigDecimal("POST_PK"));
            ZYPEZDItemValueSetter.setValue(tMsg.cntyPk, rs.getBigDecimal("CNTY_PK"));
            ZYPEZDItemValueSetter.setValue(tMsg.geoCd, rs.getString("GEO_CD"));
            return tMsg;
        }
    }

    private class CntyDeltaHandler extends DeltaHandler {
        protected EZDTMsg buildTMsg(ResultSet rs) throws SQLException {
            CNTYTMsg tMsg = new CNTYTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.cntyPk, rs.getBigDecimal("CNTY_PK"));
            ZYPEZDItemValueSetter.setValue(tMsg.cntyNm, rs.getString("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(tMsg.stCd, rs.getString("ST_CD"));
            return tMsg;
        }
    }
}
