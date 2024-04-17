/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770;

import static business.blap.NWAL1770.constant.NWAL1770Constant.NUM_OF_LINE_HIST;
import static business.blap.NWAL1770.constant.NWAL1770Constant.NUM_OF_ORD_HIST;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/07/20   Hitachi         T.Fukuta        Create          CSA-QC#61467
 * </pre>
 */
public class NWAL1770QueryForOrderHistory extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1770QueryForOrderHistory MY_INSTANCE = new NWAL1770QueryForOrderHistory();

    /**
     * Constructor.
     */
    private NWAL1770QueryForOrderHistory() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1770QueryForOrderHistory
     */
    public static NWAL1770QueryForOrderHistory getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Order History
     * @param bizMsg NWAL1770CMsg
     * @return Order History
     */
    public S21SsmEZDResult getOrderHistory(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("soldToCode", bizMsg.soldToCustLocCd.getValue());
        params.put("catgCd", bizMsg.splyQuoteCatgCd.getValue());
        params.put("ordHdrStsCd", ORD_HDR_STS.CANCELLED);
        params.put("numOfOrder", NUM_OF_ORD_HIST);

        return getSsmEZDClient().queryObjectList("getOrderHistory", params);
    }

    /**
     * Get Order Line History
     * @param bizMsg NWAL1770CMsg
     * @param ordNum Order Number
     * @return Order Line History
     */
    public S21SsmEZDResult getOrderLineHistory(NWAL1770CMsg bizMsg, String ordNum) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("ordNum", ordNum);
        params.put("ordLineStsCd", ORD_LINE_STS.CANCELLED);
        params.put("numOfLine", NUM_OF_LINE_HIST);

        return getSsmEZDClient().queryObjectList("getOrderLineHistory", params);
    }
}
