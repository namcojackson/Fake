/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1910;

import java.util.HashMap;
import java.util.Map;

import static business.blap.NWAL1910.constant.NWAL1910Constant.PROCESS_LVL_HEADER;
import static business.blap.NWAL1910.constant.NWAL1910Constant.ROUND_FACTOR;
import static business.blap.NWAL1910.constant.NWAL1910Constant.TAX;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
/**
 *<pre>
 * NWAL1910Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/14   Fujitsu         M.Ishii         Create          N/A
 *</pre>
 */
public final class NWAL1910Query extends S21SsmEZDQuerySupport {
    /** Singleton instance */
    private static final NWAL1910Query MY_INSTANCE = new NWAL1910Query();

    /**
     * Private constructor
     */
    private NWAL1910Query() {
        super();
    }

    /**
     * Get the NWAL1910Query instance.
     * @return NWAL1910Query instance
     */
    public static NWAL1910Query getInstance() {
        return MY_INSTANCE;
    }
    /**
     * <pre>
     * getFromOrderEntry
     * @param bizMsg NWAL1910CMsg
     * @param glblMsg NWAL1910SMsg
     * @param glblCmpyCd String
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getFromOrderEntry(NWAL1910CMsg bizMsg, NWAL1910SMsg glblMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", bizMsg.ordSrcRefNum.getValue());
        params.put("configCatgCd", bizMsg.configCatgCd.getValue());
        params.put("rndFctr", ROUND_FACTOR);
        params.put("tax", TAX);
        if (!PROCESS_LVL_HEADER.equals(bizMsg.xxModeCd.getValue())) {
            params.put("dsOrdPosnNum", bizMsg.dsOrdPosnNum.getValue());
            params.put("dsCpoLineNum", bizMsg.dsCpoLineNum.getValue());
        }
        return getSsmEZDClient().queryEZDMsgArray("getFromOrderEntry", params, glblMsg.A);
    }

    /**
     * <pre>
     * getFromSupplyQuote
     * @param bizMsg NWAL1910CMsg
     * @param glblMsg NWAL1910SMsg
     * @param glblCmpyCd String
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getFromSupplyQuote(NWAL1910CMsg bizMsg, NWAL1910SMsg glblMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("splyQuoteNum", bizMsg.ordSrcRefNum.getValue());
        params.put("rndFctr", ROUND_FACTOR);
        params.put("tax", TAX);
        if (!PROCESS_LVL_HEADER.equals(bizMsg.xxModeCd.getValue())) {
            params.put("dplyQuoteLineNum", bizMsg.dsOrdPosnNum.getValue());
        }
        return getSsmEZDClient().queryEZDMsgArray("getFromSupplyQuote", params, glblMsg.A);
    }

    /**
     * <pre>
     * getFromScheduleAgreement
     * @param bizMsg NWAL1910CMsg
     * @param glblMsg NWAL1910SMsg
     * @param glblCmpyCd String
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getFromScheduleAgreement(NWAL1910CMsg bizMsg, NWAL1910SMsg glblMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("schdAgmtNum", bizMsg.ordSrcRefNum.getValue());
        params.put("rndFctr", ROUND_FACTOR);
        params.put("tax", TAX);
        if (!PROCESS_LVL_HEADER.equals(bizMsg.xxModeCd.getValue())) {
            params.put("schdAgmtLineNum", bizMsg.dsOrdPosnNum.getValue());
        }
        return getSsmEZDClient().queryEZDMsgArray("getFromScheduleAgreement", params, glblMsg.A);
    }
}
