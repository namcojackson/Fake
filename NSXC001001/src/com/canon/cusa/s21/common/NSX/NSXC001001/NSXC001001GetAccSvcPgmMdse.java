/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Get Accessory Service Program MDSE 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/21/2018   Hitachi         K.Kishimoto     Create          QC#22821
 * </pre>
 */
public class NSXC001001GetAccSvcPgmMdse {

    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001GetAccSvcPgmMdse.class);

    /**
     * get Accessory Service Program MDSE
     * @param glblCmpyCd String
     * @param svcLineBizCd String
     * @param mdseCd String
     * @param rntlFlg String
     */
    public String getAccSvcPgmMdse(String glblCmpyCd, String svcLineBizCd, String mdseCd, String rntlFlg) {
        // input check
        if (!hasValue(glblCmpyCd) || !hasValue(svcLineBizCd) || !hasValue(mdseCd) || !hasValue(rntlFlg)) {
            return null;
        }

        // get SVC_COV_TMPL_TP_CD
        Map<String, Object> coaInfo = getCoaInfo(glblCmpyCd, mdseCd);
        if (coaInfo == null) {
            return null;
        }

        String accSvcPgmMdseCd = getAccSvcPgmMdseCd(glblCmpyCd, svcLineBizCd, coaInfo, rntlFlg);
        return accSvcPgmMdseCd;
    }

    private Map<String, Object> getCoaInfo(String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);

        return (Map<String, Object>) SSM_CLIENT.queryObject("getCoaInfo", ssmParam);
    }

    private String getAccSvcPgmMdseCd(String glblCmpyCd, String svcLineBizCd, Map<String, Object> coaInfo, String rntlFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcLineBizCd", svcLineBizCd);
        ssmParam.put("svcMachTpAcc", SVC_MACH_TP.ACCESSORY);
        String coaProdCd = (String) coaInfo.get("COA_PROD_CD");
        if (!hasValue(coaProdCd)) {
            coaProdCd = "";
        }
        String coaMdseTp = (String) coaInfo.get("COA_MDSE_TP_CD");
        if (!hasValue(coaMdseTp)) {
            coaMdseTp = "";
        }
        ssmParam.put("svcMachTpAcc", SVC_MACH_TP.ACCESSORY);
        ssmParam.put("coaProdCd", coaProdCd);
        ssmParam.put("coaMdseTp", coaMdseTp);
        ssmParam.put("allCd", "*");
        ssmParam.put("rntlFlg", rntlFlg);

        return (String) SSM_CLIENT.queryObject("getAccSvcPgmMdseCd", ssmParam);
    }

}
