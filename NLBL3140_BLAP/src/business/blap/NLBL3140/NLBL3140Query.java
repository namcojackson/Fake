/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3140;

import static business.blap.NLBL3140.constant.NLBL3140Constant.ASTERISK;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_WH_COND_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NLBL3140Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/12   Fujitsu         K.Ishizuka      Create          N/A
 * 2023/07/10   Hitachi         G.Quan          Update          QC#61543
 *</pre>
 */
public final class NLBL3140Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NLBL3140Query MY_INSTANCE = new NLBL3140Query();

    /**
     * Private constructor
     */
    private NLBL3140Query() {
        super();
    }

    /**
     * Get the NLBL3140Query instance.
     * @return NLBL3140Query instance
     */
    public static NLBL3140Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * execute DB access to get item information list
     * @param bizMsg NLBL3140CMsg
     * @param glblMsg NLBL3140SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NLBL3140CMsg bizMsg, NLBL3140SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("lineBizTpCd", bizMsg.lineBizTpCd.getValue());
        params.put("condTpCd", MDSE_WH_COND_TP.AUTO);
        params.put("rtlWhCd", bizMsg.rtlWhCd.getValue());
        params.put("rtlSwhCd", bizMsg.rtlSwhCd.getValue());
        // 2017/08/14 QC#20555 MOD BEGIN
//        params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());
//        params.put("dsOrdCatgDescTxt", bizMsg.dsOrdCatgDescTxt.getValue());
        if (ASTERISK.equals(bizMsg.dsOrdCatgDescTxt.getValue())) {
            params.put("dsOrdCatgCd", ASTERISK);
            params.put("dsOrdCatgDescTxt", null);
        } else {
            params.put("dsOrdCatgCd", null);
            params.put("dsOrdCatgDescTxt", bizMsg.dsOrdCatgDescTxt.getValue());
        }
        // 2017/08/14 QC#20555 MOD END
        params.put("hardAllocTpCd", bizMsg.hardAllocTpCd.getValue());
        // START 2023/07/06 G.Quan [QC#61543, ADD]
        params.put("coaProdCd", bizMsg.coaProdCd.getValue());
        // End 2023/07/06 G.Quan [QC#61543, ADD]
        params.put("rowNum", glblMsg.A.length());

        return getSsmEZDClient().queryEZDMsgArray("searchResultList", params, glblMsg.A);
    }

    /**
     * execute DB access to check order Category
     * @param acMsg NLBL3140_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkOrdCatg(NLBL3140_ASMsg acMsg) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        // 2017/08/14 QC#20555 DEL BEGIN
//        params.put("dsOrdCatgCd", acMsg.dsOrdCatgCd_A.getValue());
        // 2017/08/14 QC#20555 DEL END
        params.put("dsOrdCatgDescTxt", acMsg.dsOrdCatgDescTxt_A.getValue());

        return getSsmEZDClient().queryObject("checkOrdCatg", params);
    }

    /**
     * execute DB access to check registered item
     * @param acMsg NLBL3140_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkRegisteredRule(NLBL3140_ASMsg acMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", ASTERISK);
        params.put("rtlWhCd", acMsg.rtlWhCd_A.getValue());
        params.put("rtlSwhCd", acMsg.rtlSwhCd_A.getValue());
        params.put("lineBizTpCd", acMsg.lineBizTpCd_A.getValue());
        params.put("dsOrdCatgCd", acMsg.dsOrdCatgCd_A.getValue());
        params.put("mdseWhCondTpCd", MDSE_WH_COND_TP.AUTO);
        // 2017/08/14 QC#20555 ADD BEGIN
        params.put("mdseWhCondPk", acMsg.mdseWhCondPk_A.getValue());
        params.put("invtyHardAllocTpCd", acMsg.hardAllocTpCd_A.getValue()); // 2017/08/23 QC#20555
        params.put("asterisk", ASTERISK);
        // 2017/08/14 QC#20555 ADD END
        // START 2023/07/10 G.Quan [QC#61543, ADD]
        params.put("coaProdCd", acMsg.coaProdCd_A);
        // END 2023/07/10 G.Quan [QC#61543, ADD]

        return getSsmEZDClient().queryObject("checkRegisteredRule", params);
    }

    // START 2023/07/10 G.Quan [QC#61543, ADD]
    /**
     * getProductCodeCount
     * @param coaProdCd 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countProductCode(String coaProdCd) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("coaProdCd", coaProdCd);

        return getSsmEZDClient().queryObject("countProductCode", queryParam);
    }
    // END 2023/07/10 G.Quan [QC#61543, ADD]
}
