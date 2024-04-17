/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC043001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * S21TransactionParamTableAccessor is an API class that provides the access to transaction parameter table for integration 
 * API is offered when required to access Database
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2021/09/17   CITS            M.Furugoori     Create          QC#58267
 * </pre>
 */
public class S21TransactionParamTableAccessor {

    /** SQL access compornent */
    private S21SsmBatchClient ssmBatchClient = null;

    /** CODE parameterName */
    private static final String PARAMETER_NAME_TIMESTAMP = "TIMESTAMP";

    /** MAX_LEN_INTERFACE_ID */
    private static final int MAX_LEN_INTERFACE_ID = 30;

    /** INTERFACE_TRANSACTION_SQ_DIGIT */
    private static final int INTERFACE_TRANSACTION_SQ_DIGIT = 28;

    /** COL_INTERFACE_ID */
    private static final String COL_INTERFACE_ID = "InterfaceId";

    /** COL_TRANSACTION_ID */
    private static final String COL_TRANSACTION_ID = "TransactionId";

    /** SSM Client Custom */
    private S21SsmBatchClientCustom ssmBatchClientCustom = null;

    /**
     * Constructor
     */
    public S21TransactionParamTableAccessor() {
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }
    /**
     * Adds a new record to the integration transaction parameter table(For
     * batch)<BR>
     * Additional records will be given a unique keys, transactionId
     * and interfaceId. <BR>
     * If the operation fails, throws S21AbendException.
     * @param interfaceId Adding records interfaceId
     * @param transactionId Adding records transactionId
     */
    public void createIntegrationRecordForBatch(final String interfaceId, final BigDecimal transactionId, final String parameterValue) {

        // check parameter
        if (interfaceId == null || interfaceId.length() == 0) {
            throw new S21AbendException("ZZM9000E", new String[] {COL_INTERFACE_ID });
        } else if (interfaceId.length() > MAX_LEN_INTERFACE_ID) {
            throw new S21AbendException("ZZM9001E", new String[] {COL_INTERFACE_ID });
        } else if (transactionId == null) {
            throw new S21AbendException("ZZM9000E", new String[] {COL_TRANSACTION_ID });
        } else if (transactionId.movePointLeft(INTERFACE_TRANSACTION_SQ_DIGIT).compareTo(new BigDecimal(1)) > 0 || transactionId.compareTo(new BigDecimal(1)) < 0) {
            throw new S21AbendException("ZZM9001E", new String[] {COL_TRANSACTION_ID });
        }

        ssmBatchClientCustom = new S21SsmBatchClientCustom(this.getClass());

        // insert TransactionParamTable
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("timestamp", PARAMETER_NAME_TIMESTAMP);
        queryParam.put("parameterValue", parameterValue);

        /** SQL Hint */
        
        int cnt= ssmBatchClientCustom.insert("createInterfaceTransactionParam", queryParam);

        return;
    }

}
