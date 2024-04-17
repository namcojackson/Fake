/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0170;

import java.util.HashMap;
import java.util.Map;

import business.blap.NFDL0170.common.NFDL0170CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Customer Account Search Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/07   Hitachi         T.Tsuchida      Create          QC#19574
 * 2018/01/15   Fujitsu         H.Ikeda         Update          QC#22759
 * 2018/06/05   Fujitsu         Y.Matsui        Update          QC#26456
 *</pre>
 */
public final class NFDL0170Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NFDL0170Query MY_INSTANCE = new NFDL0170Query();

    /**
     * Private constructor
     */
    private NFDL0170Query() {
        super();
    }

    /**
     * Get the NFDL0170Query instance.
     * @return NFDL0170Query instance
     */
    public static NFDL0170Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NFDL0170CMsg
     * @param glblMsg NFDL0170SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NFDL0170CMsg bizMsg, NFDL0170SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cMsg", bizMsg);
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("billToCustAcctNm_H", bizMsg.billToCustAcctNm_H.getValue());
        params.put("billToCustAcctCd_H", bizMsg.billToCustAcctCd_H.getValue());
        params.put("shipToLocNm_H", bizMsg.shipToLocNm_H.getValue());
        params.put("shipToCustCd_H", bizMsg.shipToCustCd_H.getValue());
        params.put("invNum_FR", bizMsg.invNum_FR.getValue());

        String searchFlg = NFDL0170CommonLogic.setSearchFlg(bizMsg.invNum_FR.getValue());

        params.put("searchFlg", searchFlg);

        params.put("invNum_TO", bizMsg.invNum_TO.getValue());
        params.put("mdlNm_H", bizMsg.mdlNm_H.getValue());
        // START 2018/01/15 H.Ikeda [QC#22759,ADD]
        params.put("grpInvNum_H", bizMsg.grpInvNum_H.getValue());
        // END   2018/01/15 H.Ikeda [QC#22759,ADD]
        params.put("serNum_H", bizMsg.serNum_H.getValue());
        // START 2018/06/05 Y.Matsui [QC#26456,ADD]
        params.put("custIssPoNum_H", bizMsg.custIssPoNum_H.getValue());
        // END   2018/06/05 Y.Matsui [QC#26456,ADD]

        if (!ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_CL.getValue())) {
            params.put("inCloInv", new String[] {AR_CASH_APPLY_STS.APPLIED, AR_CASH_APPLY_STS.VOID});
        }

        params.put("rowNum", glblMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getCustList", params, glblMsg.A);
    }
}
