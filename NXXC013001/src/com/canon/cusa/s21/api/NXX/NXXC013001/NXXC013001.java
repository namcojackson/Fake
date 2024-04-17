package com.canon.cusa.s21.api.NXX.NXXC013001;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import business.parts.NXXC013001PMsg;

/**
 * <pre>
 * IVP API Inbound
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/11/18   Fujitsu         S.Kosaka        Create          N/A
 *</pre>
 */
public class NXXC013001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** Constructor */
    public NXXC013001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NXXC013001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NXXC013001PMsg param, final ONBATCH_TYPE onBatchType) {
        msgMap = new S21ApiMessageMap(param);
        runInterfaceVerficationProcess(param);
        msgMap.flush();
        S21LoggerFactory.getLogger(NXXC013001.class).info("Interface ID = NXXI0130, INTFC_TEST_TXT = " + param.intfcTestTxt.getValue());
    }

    private void runInterfaceVerficationProcess(NXXC013001PMsg param) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("intfcTestTxt", param.intfcTestTxt.getValue());
        // execute query
        param.intfcTestTxt.setValue((String) ssmBatchClient.queryObject("getIntfcTestTxt", mapParam));
    }
}