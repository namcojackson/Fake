/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC100001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.db.ORD_TAKE_MDSETMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
/**
 * <pre>
 * Supply Contract Check
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/12   Fujitsu         MN.Nakazawa     Create          N/A
 * 2013/08/28   Fujitsu         Y.Murasaki      Update          Def#1968
 * 2013/09/27   Fujitsu         S.Yamamoto      Update          Def#2319
 *</pre>
 */
public class NWXC100001CheckSplyContr {
    /** SSM Batch Client */
    public static final S21SsmBatchClient SSM_BATCH_CLIENT = S21SsmBatchClient.getClient(NWXC100001CheckSplyContr.class);
    public NWXC100001CheckSplyContrBean  checkSplyContr(String glblCmpyCd, String dsContrNum, String dsContrSqNum, String splyMdseCd, BigDecimal svcConfigMstrPk, String sellToCustCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrNum", dsContrNum);
        ssmParam.put("dsContrSqNum", dsContrSqNum);
        ssmParam.put("mdseCd", splyMdseCd);
        ssmParam.put("ordTakeMdseCd", splyMdseCd.substring(0, Math.min(splyMdseCd.length(), new ORD_TAKE_MDSETMsg().getAttr("ordTakeMdseCd").getDigit())));
        ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);
        ssmParam.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.ACTIVE);
        ssmParam.put("dsContrStsCd", DS_CONTR_STS.EFFECTIVE);
        ssmParam.put("sellToCustCd", sellToCustCd);        
        return (NWXC100001CheckSplyContrBean) SSM_BATCH_CLIENT.queryObject("queryTagetData", ssmParam);

    }
}
