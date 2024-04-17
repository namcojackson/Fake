/**
 * <Pre>Copyright (c) 2019 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Get Accessory Service Program MDSE 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/16/2019   Hitachi         K.Kim           Create          QC#29782
 * </pre>
 */
public class NSXC001001DefaultSvcPgmGetter {

    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001DefaultSvcPgmGetter.class);

    /**
     * get Accessory Service Program MDSE
     * @param glblCmpyCd String
     * @param svcLineBizCd String
     * @param mdseCd String
     * @param rntlFlg String
     * @param svcPgmMdseCd String
     */
    public String getAccSvcPgmMdse(String glblCmpyCd, String svcLineBizCd, String mdseCd, String rntlFlg, String svcPgmMdseCd) {
        // input check
        if (!hasValue(glblCmpyCd) || !hasValue(svcLineBizCd) || !hasValue(mdseCd) || !hasValue(rntlFlg) || !hasValue(svcPgmMdseCd)) {
            return null;
        }

        Map<String, Object> mdseInfo = getMdseInfo(glblCmpyCd, mdseCd);
        if (mdseInfo == null) {
            return null;
        }

        Map<String, Object> prntMdseInfo = getMdseInfo(glblCmpyCd, svcPgmMdseCd);
        if (prntMdseInfo == null) {
            return null;
        }

        String accSvcPgmMdseCd = getDefSvcPgmRule(glblCmpyCd, svcLineBizCd, mdseInfo, prntMdseInfo, rntlFlg, ZYPConstant.FLG_OFF_N, SVC_MACH_TP.ACCESSORY);
        return accSvcPgmMdseCd;
    }

    /**
     * get Rental Split Service Program MDSE
     * @param glblCmpyCd String
     * @param svcLineBizCd String
     * @param mdseCd String
     * @param rntlFlg String
     * @param svcPgmMdseCd String
     * @param svcMachTp String
     */
    public String getRentalSplitSvcPgmMdse(String glblCmpyCd, String svcLineBizCd, String mdseCd, String rntlFlg, String svcPgmMdseCd, String svcMachTp) {
        // input check
        if (!hasValue(glblCmpyCd) || !hasValue(svcLineBizCd) || !hasValue(mdseCd) || !hasValue(rntlFlg) || !hasValue(svcPgmMdseCd)) {
            return null;
        }

        Map<String, Object> mdseInfo = getMdseInfo(glblCmpyCd, mdseCd);
        if (mdseInfo == null) {
            return null;
        }

        Map<String, Object> prntMdseInfo = getMdseInfo(glblCmpyCd, svcPgmMdseCd);
        if (prntMdseInfo == null) {
            return null;
        }

        String rntlSplSvcPgmMdseCd = getDefSvcPgmRule(glblCmpyCd, svcLineBizCd, mdseInfo, prntMdseInfo, rntlFlg, ZYPConstant.FLG_ON_Y, svcMachTp);
        return rntlSplSvcPgmMdseCd;
    }

    private Map<String, Object> getMdseInfo(String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("allCd", "*");

        return (Map<String, Object>) SSM_CLIENT.queryObject("getMdseInfo", ssmParam);
    }

    private String getDefSvcPgmRule(String glblCmpyCd, String svcLineBizCd, Map<String, Object> mdseInfo, Map<String, Object> prntMdseInfo, String rntlFlg, String rntlSplFlg, String svcMachTp) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        String coaProdCd = (String) mdseInfo.get("COA_PROD_CD");
        String coaMdseTp = (String) mdseInfo.get("COA_MDSE_TP_CD");
        String mdseCd = (String) mdseInfo.get("MDSE_CD");
        String mdseItemTp = (String) mdseInfo.get("MDSE_ITEM_TP_CD");
        String swCatgCd = (String) mdseInfo.get("SW_CATG_CD");
        String svcCovBaseTp = (String) prntMdseInfo.get("SVC_COV_BASE_TP_CD");
        String svcPgmTp = (String) prntMdseInfo.get("SVC_PGM_TP_CD");

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcLineBizCd", svcLineBizCd);
        ssmParam.put("rntlFlg", rntlFlg);
        ssmParam.put("rntlSplFlg", rntlSplFlg);
        ssmParam.put("svcMachTp", svcMachTp);
        ssmParam.put("coaProdCd", coaProdCd);
        ssmParam.put("coaMdseTp", coaMdseTp);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("mdseItemTp", mdseItemTp);
        ssmParam.put("swCatgCd", swCatgCd);
        ssmParam.put("svcCovBaseTp", svcCovBaseTp);
        ssmParam.put("svcPgmTp", svcPgmTp);
        ssmParam.put("allCd", "*");

        return (String) SSM_CLIENT.queryObject("getDefSvcPgmRule", ssmParam);
    }

}
