/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.blap.NPAL0170;

import static business.blap.NPAL0170.constant.NPAL0170Constant.BIND_GLBL_CMPY_CD;
import static business.blap.NPAL0170.constant.NPAL0170Constant.BIND_MSG;
import static business.blap.NPAL0170.constant.NPAL0170Constant.BIND_ROWNUM;
import static business.blap.NPAL0170.constant.NPAL0170Constant.BIND_PO_MDG_TP_CD;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/21/2016   CITS         K.Ogino            Create          N/A
 * 03/25/2016   CITS         T.Tokutomi         Update          QC#5773
 *</pre>
 */
public final class NPAL0170Query extends S21SsmEZDQuerySupport {

    /**
     * 
     */
    private static final NPAL0170Query MY_INSTANCE = new NPAL0170Query();

    private NPAL0170Query() {
        super();
    }

    /**
     * @return NPAL1510Query
     */
    public static NPAL0170Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * @param cMsg NPAL0170CMsg
     * @param glblCmpyCd String
     * @param rowNum rowNum
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoMessageList(NPAL0170CMsg cMsg, String glblCmpyCd, int rowNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, getGlobalCompanyCode());
        params.put(BIND_MSG, cMsg);
        params.put(BIND_ROWNUM, rowNum);
        return getSsmEZDClient().queryObjectList("getPoMessageList", params);
    }

    /**
     * getPoMsgDescTxt
     * @param glblCmpyCd
     * @param poMsgTpCd
     * @return poMsgTpDescText(String) or Empty String
     */
    public String getPoMsgDescTxt(String glblCmpyCd, String poMsgTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_PO_MDG_TP_CD, poMsgTpCd);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getPoMsgDescTxt", params);

        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        } else {
            return new String();
        }
    }
}
