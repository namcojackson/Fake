/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Tak Yoshida
 * Company: SRA AMERICA, Inc.
 * Date: Apr 30, 2009
 */
package business.blap.ZZML0071;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * @author q02673
 */
public final class ZZML0071Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZML0071Query INSTANCE = new ZZML0071Query();

    /**
     * Constructor.
     */
    private ZZML0071Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZML0071Query
     */
    public static ZZML0071Query getInstance() {
        return INSTANCE;
    }

    
    /**
     * @param cMsg ZZML0071CMsg
     * @param sMsg ZZML0071SMsg
     * @param rowNum int  
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMlGrpAddr(ZZML0071CMsg cMsg, ZZML0071SMsg sMsg, int rowNum) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        map.put("mlGrpId", cMsg.A.no(rowNum).mlGrpId_A.getValue());

        return getSsmEZDClient().queryObject("getMlGrpAddr", map);
    }
    
    /**
     * @param cMsg ZZML0071CMsg
     * @param sMsg ZZML0071SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMlUsrAddr(ZZML0071CMsg cMsg, ZZML0071SMsg sMsg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        map.put("mlUsrAddrPk", cMsg.A.no(0).mlUsrAddrPk_A.getValue());

        return getSsmEZDClient().queryEZDMsgArray("getMlUsrAddr", map, sMsg.A);
    }

}
