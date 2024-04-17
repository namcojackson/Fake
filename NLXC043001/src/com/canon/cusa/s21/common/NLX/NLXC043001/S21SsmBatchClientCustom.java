/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC043001;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * S21SsmBatchClient Custome Class.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2021/09/21   CITS            K.Ogino         Create          QC#58267
 * </pre>
 */
public class S21SsmBatchClientCustom extends S21SsmBatchClient {

    /**
     * No Factory method to get S21SsmBatchClientCustom. Object is not
     * cached.
     * @param useClass
     * @return S21SsmBatchClientCustom pbject
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
}
