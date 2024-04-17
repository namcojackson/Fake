/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL9010;

import java.util.HashMap;
import java.util.Map;

import business.blap.NFAL9010.constant.NFAL9010Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * Class name: NFAL9010Query
 * <dd>The class explanation: SSM
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */

/**
 * <pre>
 * COA Account Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   CSAI            K.Uramori       Update          CSA Modification
 * </pre>
 */
public final class NFAL9010Query extends S21SsmEZDQuerySupport implements NFAL9010Constant {

    /**
     * Singleton instance.
     */
    private static final NFAL9010Query INSTANCE = new NFAL9010Query();

    /**
     * Constructor.
     */
    private NFAL9010Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return SSML0001Query
     */
    public static NFAL9010Query getInstance() {
        return INSTANCE;
    }

    /**
     * @param bizMsg NFAL9010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getResultCountNFAL9010(NFAL9010CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());

        ssmParam.put("coaAcctCd", bizMsg.coaAcctCd.getValue() + "%");
        ssmParam.put("coaAcctNm", bizMsg.coaAcctNm.getValue() + "%");

        ssmParam.put("trialBalTpCd", bizMsg.trialBalTpCd_3.getValue());
        //ssmParam.put("drCrTpCd", bizMsg.drCrTpCd_3.getValue());
        //ssmParam.put("bsPlTpCd", bizMsg.bsPlTpCd_3.getValue());
        //ssmParam.put("plCatgCd", bizMsg.plCatgCd_3.getValue());

        ssmParam.put("bizMsg", bizMsg);

        return getSsmEZDClient().queryEZDMsg("getResultCountNFAL9010", ssmParam, bizMsg);
    }

    /**
     * @param bizMsg NFAL9010CMsg
     * @param globalMsg NFAL9010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getResultListNFAL9010(NFAL9010CMsg bizMsg, NFAL9010SMsg globalMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());

        ssmParam.put("coaAcctCd", bizMsg.coaAcctCd.getValue() + "%");
        ssmParam.put("coaAcctNm", bizMsg.coaAcctNm.getValue() + "%");

        ssmParam.put("trialBalTpCd", bizMsg.trialBalTpCd_3.getValue());
        //ssmParam.put("drCrTpCd", bizMsg.drCrTpCd_3.getValue());
        //ssmParam.put("bsPlTpCd", bizMsg.bsPlTpCd_3.getValue());
        //ssmParam.put("plCatgCd", bizMsg.plCatgCd_3.getValue());

        ssmParam.put("rowNum", globalMsg.A.length() + 1);
        ssmParam.put("bizMsg", bizMsg);

        return getSsmEZDClient().queryEZDMsgArray("getResultNFAL9010", ssmParam, globalMsg.A);
    }

    /**
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrialBalTp() {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", this.getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getTrialBalTp", queryParam, -1, -1);
    }

    /**
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDrCrTp() {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", this.getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getDrCrTp", queryParam, -1, -1);
    }

    /**
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBsPl() {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", this.getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getBsPl", queryParam, -1, -1);
    }

    /**
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPlCatg() {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", this.getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getPlCatg", queryParam, -1, -1);
    }

}
