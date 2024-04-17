/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Tak Yoshida
 * Company: SRA AMERICA, Inc.
 * Date: Apr 30, 2009
 */
package business.blap.ZZML0070;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * @author q02673
 */
public final class ZZML0070Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZML0070Query INSTANCE = new ZZML0070Query();

    /**
     * Constructor.
     */
    private ZZML0070Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZML0070Query
     */
    public static ZZML0070Query getInstance() {
        return INSTANCE;
    }

    /**
     * @param cMsg ZZML0070CMsg
     * @param sMsg ZZML0070SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMlGrpAddr(ZZML0070CMsg cMsg, ZZML0070SMsg sMsg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", cMsg.glblCmpyCd_S.getValue());
        map.put("mlGrpId", cMsg.mlGrpId_S.getValue());
        map.put("mlKeyFirstCd", cMsg.mlKeyFirstCd_S.getValue());
        map.put("mlKeyScdCd", cMsg.mlKeyScdCd_S.getValue());
        map.put("mlKeyThirdCd", cMsg.mlKeyThirdCd_S.getValue());
        map.put("mlUsrId", cMsg.mlUsrId_S.getValue());
        map.put("mlUsrAddr", cMsg.mlUsrAddr_S.getValue());
        //map.put("glblCmpyCd_LANG", "ABR");
        map.put("rowNum", sMsg.A.length() + 1);
        return getSsmEZDClient().queryEZDMsgArray("getMlUsrAddr", map, sMsg.A);
    }
}
