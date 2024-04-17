/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Tak Yoshida
 * Company: SRA AMERICA, Inc.
 * Date: Apr 30, 2009
 */
package business.blap.ZZML0010;

import java.util.HashMap;
import java.util.Map;

// 2012.7.24 Start ABR Removal
import parts.common.EZDSystemEnv;
// 2012.7.24 End   ABR Removal

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * @author q02673
 */
public final class ZZML0010Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZML0010Query INSTANCE = new ZZML0010Query();

    /**
     * Constructor.
     */
    private ZZML0010Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZML0010Query
     */
    public static ZZML0010Query getInstance() {
        return INSTANCE;
    }

    /**
     * @param cMsg EZDCMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLanguageList(ZZML0010CMsg cMsg) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 2012.7.24 Start ABR Removal
        // map.put("glblCmpyCd", "ABR");
        map.put("glblCmpyCd", EZDSystemEnv.getString("S21.global_company_code"));
        // 2012.7.24 End   ABR Removal
        return getSsmEZDClient().queryObjectList("getLanguageList", map);
    }

    /**
     * @param cMsg EZDCMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMailSystemConfig(ZZML0010CMsg cMsg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", cMsg.glblCmpyCd_S.getValue());
        return getSsmEZDClient().queryEZDMsg("getMailSystemConfig", map, cMsg);
    }
}
