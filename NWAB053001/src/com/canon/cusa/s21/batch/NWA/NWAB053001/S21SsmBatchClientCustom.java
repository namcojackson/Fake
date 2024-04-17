/*
 * <Pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB053001;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Create Open Order Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 12/14/2018   FUJITSU         Mz.Takahashi    CREATE          QC#29024
 *</pre>
 * <p>
 * S21SsmBatchClientCustom extends S21SsmBatchClient to use
 * ssmClient.delete ,ssmClient.insert and ssmClient.update Method.
 * <p>
 * S21SsmBatchClientCustom requires SSM mapping definition file in the
 * same folder of the class that uses S21SsmBatchClientCustom. When
 * applications uses multiple types of SSMClients, the definition file
 * is shared for all clients, so applications should take care of
 * statement usage by themselvs.. The main features of
 * S21SsmBatchClientCustom are,
 * <ul>
 * <li>Create SQL statement dynamicly by applying given query
 * parameter to SQL statement defined externally.
 * <li>Call given S21SsmResultSetHandler object by passing ResultSet
 * object.
 * <li>Database resources, Statement and ResultSet, are automatically
 * released when the method invocation ends.
 * <li>Applications can control query execution by setting Statement
 * attributes through S21SsmExecutionParameter object.
 * </ul>
 * </p>
 * <p>
 * S21SsmEZDClient is unique in terms of accessing the ResultSet
 * object. Since batch programs are manual and burst process, the
 * program needs full control of programming capability when it access
 * ResultSet. This characteristic makes the APIs little different from
 * well-knonw Apache SQLMap, meaning the .S21SsmResultSetHandler
 * object, which is defined as inner class, must be supplied as an
 * argument.
 * </p>
 * <p>
 * The result object of each query method are different by the return
 * object of the S21SsmResultSetHandler, and SSM provide following
 * abstract classes for the well used types.
 * <ul>
 * <li>S21SsmBooleanResultSetHandlerSupport
 * <li>S21SsmIntegerResultSetHandlerSupport
 * <li>S21SsmStringResultSetHandlerSupport
 * <li>S21SsmBigDecimalResultSetHandlerSupport
 * <li>S21SsmVoidResultSetHandlerSupport
 * <li>S21SsmListResultSetHandlerSupport
 * </ul>
 * </p>
 * @author $Author: Masayuki Takahashi $
 * @version $Revision: 1.0 $ $Date: 2011/02/22 13:28:40 $
 */
public class S21SsmBatchClientCustom extends S21SsmBatchClient {

    /**
     * No Factory method to get S21SsmBatchClientCustom. Object is not
     * cached.
     * @param useClass
     * @return S21SsmBatchClientCustom 
     */
    protected S21SsmBatchClientCustom(Class useClass) {
        super(useClass);
        useClassName = useClass.getSimpleName();
    }

    protected S21SsmBatchClientCustom(Class useClass, Class... ezdMsgClasses) {
        super(useClass, ezdMsgClasses);
        useClassName = useClass.getSimpleName();
    }

    /**
     * delete
     * @param statementId String
     * @param param Object
     * @return int
     */
    public int delete(String statementId, Object param) {
        try {
            return ssmClient.delete(useClassName + "." + statementId, param);

        } catch (Exception e) {
            String msg = "Failed delete. statementId=" + statementId + "\n param=" + param;
            throw convertEZDRuntimeException(e, msg);
        }
    }

    /**
     * insert
     * @param statementId String
     * @param param Object
     * @return int
     */
    public int insert(String statementId, Object param) {
        try {
            return ssmClient.insert(useClassName + "." + statementId, param);

        } catch (Exception e) {
            String msg = "Failed insert. statementId=" + statementId + "\n param=" + param;
            throw convertEZDRuntimeException(e, msg);
        }
    }

    /**
     * update
     * @param statementId String
     * @param param Object
     * @return int
     */
    public int update(String statementId, Object param) {
        try {
            return ssmClient.update(useClassName + "." + statementId, param);

        } catch (Exception e) {
            String msg = "Failed update. statementId=" + statementId + "\n param=" + param;
            throw convertEZDRuntimeException(e, msg);
        }
    }
}
