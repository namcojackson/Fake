/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2340.common;

import static business.blap.NWAL2340.constant.NWAL2340Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NWAL2340.constant.NWAL2340Constant.DB_PARAM_SELL_TO_CUST_CD;
import static business.blap.NWAL2340.constant.NWAL2340Constant.DB_PARAM_SHIP_TO_CUST_CD;
import static business.blap.NWAL2340.constant.NWAL2340Constant.NWAM0702E;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

import business.blap.NWAL2340.NWAL2340CMsg;
import business.blap.NWAL2340.NWAL2340Query;
import business.blap.NWAL2340.NWAL2340SMsg;

/**
 *<pre>
 * SOM Address Mass Apply
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/22   CITS            S.Tanikawa      Create          N/A
 * 2017/09/20   Fujitsu         R.Nakamura      Update          QC#21114
 *</pre>
 */
public class NWAL2340CommonLogic {

    /**
     * checkRelation
     * @param cMsg NWAL2340CMsg
     * @param sMsg NWAL2340SMsg
     * @param glblCmpyCd String
     */
    public static void checkRelation(NWAL2340CMsg cMsg, NWAL2340SMsg sMsg, String glblCmpyCd) {

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_SELL_TO_CUST_CD, cMsg.A.no(i).shipToCustAcctCd_A.getValue());
            ssmParam.put(DB_PARAM_SHIP_TO_CUST_CD, cMsg.A.no(i).shipToCustCd_A.getValue());

            Integer resCnt = NWAL2340Query.getInstance().getCntAcct(ssmParam);

            if (resCnt <= 0) {
                cMsg.A.no(i).shipToCustAcctCd_A.setErrorInfo(1, NWAM0702E, new String[] {"Customer Number", "Location Number" });
                cMsg.A.no(i).shipToCustCd_A.setErrorInfo(1, NWAM0702E, new String[] {"Customer Number", "Location Number" });
            }
        }
    }

    // Add Start 2017/09/20 QC#21114
    /**
     * checkRelation
     * @param cMsg NWAL2340CMsg
     * @param shipToCustInfoList Map<String, String>
     */
    public static void setAcctInfoList(NWAL2340CMsg cMsg, Map<String, String> shipToCustInfo) {

        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustAcctNm_H, shipToCustInfo.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustAcctCd_H, shipToCustInfo.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd_H, shipToCustInfo.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToFirstLineAddr_H, shipToCustInfo.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToScdLineAddr_H, shipToCustInfo.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToThirdLineAddr_H, shipToCustInfo.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToFrthLineAddr_H, shipToCustInfo.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCtyAddr_H, shipToCustInfo.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToStCd_H, shipToCustInfo.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToPostCd_H, shipToCustInfo.get("POST_CD"));
    }
    // Add End 2017/09/20 QC#21114
}
