/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1250;

import static business.blap.NPAL1250.constant.NPAL1250Constant.BIZ_ID;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Big Deal Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public final class NPAL1250Query extends S21SsmEZDQuerySupport {
    /**
     * Singleton instance.
     */
    private static final NPAL1250Query INSTANCE = new NPAL1250Query();

    /**
     * Constructor.
     */
    private NPAL1250Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPAL1250Query
     */
    public static NPAL1250Query getInstance() {
        return INSTANCE;
    }

    /**
     * Search Account List
     * @param cMsg NPAL1250CMsg
     * @param sMsg NPAL1250SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchAcctList(NPAL1250CMsg cMsg, NPAL1250SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue(), BIZ_ID));
        ssmParam.put("sellToCustCd", cMsg.sellToCustCd);
        ssmParam.put("shipToCustCd", cMsg.shipToCustCd);
        ssmParam.put("locNm", cMsg.locNm);
        ssmParam.put("dsAcctNm", cMsg.dsAcctNm);
        ssmParam.put("bigDealNum", cMsg.bigDealNum);

        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        ssmParam.put("rowNum", sMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("searchAcctList", ssmParam, sMsg.A);
    }
}
