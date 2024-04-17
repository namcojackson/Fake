/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB039001;

import java.util.HashMap;
import java.util.Map;

import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.internal.S21BatchTransactionQuery;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * CFS Interface Data Refresh
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/03/28   Fujitsu         K.Ishizuka      Create          QC#23279
 * </pre>
 */
public class NWCB039001 extends S21BatchMain {

    /** global company code */
    private String glblCmpyCd;

    /** sales date */
    private String salesDate;

    /** SSM Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Target IF Table From */
    private String ifTargetTblTo = null;

    /** Target IF Table To */
    private String ifTargetTblFrom = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    @Override
    protected void initRoutine() {
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException("ZZZM9025E", new String[] {"Global Company Code" });
        }

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);
        if (null == glblCmpyTMsg) {
            throw new S21AbendException("ZZZM9026E", new String[] {"Global Company Code" });
        }

        // Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException("ZZZM9025E", new String[] {"Sales Date" });
        }

        // User Variable 1
        this.ifTargetTblTo = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(this.ifTargetTblTo)) {
            throw new S21AbendException("ZZZM9025E", new String[] {"User Valiable 1" });
        }

        // User Variable 2
        this.ifTargetTblFrom = getUserVariable2();
        if (!ZYPCommonFunc.hasValue(this.ifTargetTblFrom)) {
            throw new S21AbendException("ZZZM9025E", new String[] {"User Valiable 2" });
        }
    }

    @Override
    protected void mainRoutine() {
        int ifTargetTblToColCnt = getTblColCnt(ifTargetTblTo);
        int ifTargetTblFromColCnt = getTblColCnt(ifTargetTblFrom);

        if (ifTargetTblToColCnt == 0 || ifTargetTblFromColCnt == 0 || ifTargetTblToColCnt != ifTargetTblFromColCnt) {
            totalErrCount++;
            this.termCd = TERM_CD.WARNING_END;
            S21InfoLogOutput.println("Target tables have some difference.");
            return;
        }

        execRefreshData(ifTargetTblFrom, ifTargetTblTo);
    }

    @Override
    protected void termRoutine() {
        this.totalCount = this.totalNmlCount + this.totalErrCount;
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // initialize S21BatchMain
        new NWCB039001().executeBatch(NWCB039001.class.getSimpleName());
    }

    /**
     * get Table Column Count
     * @param tableName
     * @return count
     */
    private Integer getTblColCnt(String tableName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tableName", tableName);

        return (Integer) this.ssmBatchClient.queryObject("getTblColCnt", params);
    }

    /**
     * execute sql for IF data refresh
     * @param ifTargetTblFrom
     * @param ifTargetTblTo
     */
    private void execRefreshData(String ifTargetTblFrom, String ifTargetTblTo) {
        S21BatchTransactionQuery queryComponet = new S21BatchTransactionQuery();
        StringBuilder sb = new StringBuilder();
        sb.append("DECLARE COUNT_NUM PLS_INTEGER; ");
        sb.append("BEGIN ");
        sb.append("SELECT COUNT(*) INTO COUNT_NUM  FROM ");
        sb.append(ifTargetTblFrom);
        sb.append("; ");
        sb.append("IF COUNT_NUM <> 0 THEN ");
        sb.append("EXECUTE IMMEDIATE ");
        sb.append("'DELETE FROM ");
        sb.append(ifTargetTblTo);
        sb.append("'; ");
        sb.append("INSERT INTO ");
        sb.append(ifTargetTblTo);
        sb.append(" SELECT * FROM ");
        sb.append(ifTargetTblFrom);
        sb.append("; ");
        sb.append("END IF; ");
        sb.append("END;");
        int num = queryComponet.executeUpdate(sb.toString());

        if (num < 0) {
            totalErrCount++;
            this.termCd = TERM_CD.WARNING_END;
            S21InfoLogOutput.println(String.format("Error happened \"%s\"", sb.toString()));
        } else {
            totalNmlCount++;
            S21InfoLogOutput.println(String.format("Successfully executed \"%s\"", sb.toString()));
        }
    }

}
