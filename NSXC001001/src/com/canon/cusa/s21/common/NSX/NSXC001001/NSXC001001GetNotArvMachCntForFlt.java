/**
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Check Not Arrived Machine for Fleet Contract
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/26/2017   Hitachi         K.Kasai         Create          QC#18882
 *</pre>
 */
public class NSXC001001GetNotArvMachCntForFlt {

    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001GetNotArvMachCntForFlt.class);

    /**
     * getNotArvMachCnt
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal getNotArvMachCnt(String glblCmpyCd, BigDecimal dsContrPk) {
        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsContrPk", dsContrPk);
        queryParam.put("dsContrCatgCd", DS_CONTR_CATG.FLEET);
        queryParam.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.ORDER);
        queryParam.put("ordLineStsCd", ORD_LINE_STS.CANCELLED);
    
        BigDecimal notArvMachCnt = (BigDecimal) SSM_CLIENT.queryObject("getNotArvMachCnt", queryParam);
        return notArvMachCnt;
    }
}
