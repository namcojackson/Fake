/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NMX.NMXC105001;

import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmEZDClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/08/16   Fujitsu         K.Ishizuka      Create          S21_NA#27716
 * </pre>
 */
public class NMXC10500PriceListDataCacheForSSM {

    /** My Instance Key in Tread Local */
    private static final String INSTANCE_KEY = NMXC10500PriceListDataCacheForSSM.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, Object>, String> pkgUomCache = new S21LRUMap<Map<String, Object>, String>();

    /** Private Constructor */
    private NMXC10500PriceListDataCacheForSSM() {
    }

    /**
     * Get Singleton Instance
     * @return NMXC10500PriceListDataCacheForSSM
     */
    public static NMXC10500PriceListDataCacheForSSM getInstance() {

        NMXC10500PriceListDataCacheForSSM myInstance = (NMXC10500PriceListDataCacheForSSM) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new NMXC10500PriceListDataCacheForSSM();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * Get Package UOM From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Package UOM Code
     */
    public String getPkgUom(Map<String, Object> ssmParam, S21SsmEZDClient ssm) {

        String pkgUomCd = pkgUomCache.get(ssmParam);

        if (!ZYPCommonFunc.hasValue(pkgUomCd)) {
            S21SsmEZDResult ssmResult = ssm.queryObject("getPkgUomCd", ssmParam);

            if (ssmResult.isCodeNotFound()) {
                return null;
            }
            pkgUomCd = (String) ssmResult.getResultObject();

            if (!ZYPCommonFunc.hasValue(pkgUomCd)) {
                return null;
            }
            pkgUomCache.put(ssmParam, pkgUomCd);
        }

        return pkgUomCd;
    }

}
