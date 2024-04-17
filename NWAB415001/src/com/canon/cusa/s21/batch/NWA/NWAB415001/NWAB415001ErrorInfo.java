package com.canon.cusa.s21.batch.NWA.NWAB415001;

import java.math.BigDecimal;

import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;

/**
 * <pre>
 * EOPS Interface to S21 Import Data Batch
 * NWAB415001ErrorInfo
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 *</pre>
 */
public class NWAB415001ErrorInfo extends NWXC220001ErrorInfo {

    private BigDecimal transactionId;

    private BigDecimal unitId;

    private BigDecimal seqNumber;

    public NWAB415001ErrorInfo(BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, NWXC220001ErrorInfo errorInfo) {
        super(errorInfo.getErrMsgId(), errorInfo.getParamArray());

        this.transactionId = transactionId;
        this.unitId = unitId;
        this.seqNumber = seqNumber;
    }

    public NWAB415001ErrorInfo(BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String errMsgId, Object... param) {
        super(errMsgId, param);

        this.transactionId = transactionId;
        this.unitId = unitId;
        this.seqNumber = seqNumber;
    }

    public NWAB415001ErrorInfo(BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String errMsgId, String[] paramArray) {
        super(errMsgId, paramArray);

        this.transactionId = transactionId;
        this.unitId = unitId;
        this.seqNumber = seqNumber;
    }

    public BigDecimal getTransactionId() {
        return transactionId;
    }

    public BigDecimal getUnitId() {
        return unitId;
    }

    public BigDecimal getSeqNumber() {
        return seqNumber;
    }


}
