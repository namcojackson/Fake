/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Tak Yoshida
 * Company: SRA AMERICA, Inc.
 * Date: Apr 30, 2009
 */
package business.blap.ZZML0060;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * @author q02673
 */
public final class ZZML0060Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZML0060Query INSTANCE = new ZZML0060Query();

    /**
     * Constructor.
     */
    private ZZML0060Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZML0060Query
     */
    public static ZZML0060Query getInstance() {
        return INSTANCE;
    }

    /**
     * @param cMsg ZZML0060CMsg
     * @param sMsg ZZML0060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMlGrpAddr(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", cMsg.glblCmpyCd_S.getValue());
        map.put("mlGrpId", cMsg.mlGrpId_S.getValue());
        map.put("mlGrpNm", cMsg.mlGrpNm_S.getValue());
        map.put("rowNum", sMsg.A.length() + 1);
        return getSsmEZDClient().queryEZDMsgArray("getMlGrpAddr", map, sMsg.A);
    }

    /**
     * @param cMsg ZZML0060CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMlGrpAddrByKey(ZZML0060CMsg cMsg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", cMsg.glblCmpyCd_01.getValue());
        map.put("mlGrpId", cMsg.mlGrpId_01.getValue());
        return getSsmEZDClient().queryEZDMsg("getMlGrpAddrByKey", map, cMsg);
    }
}

