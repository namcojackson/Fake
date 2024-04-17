package business.blap.NYEL0050;

import java.util.HashMap;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import business.blap.NYEL0050.constant.NYEL0050Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmEZDClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

public final class NYEL0050Query implements NYEL0050Constant {

    /**
     * Singleton instance.
     */
    private static final NYEL0050Query myInstance = new NYEL0050Query();

    /**
     * Constructor.
     */
    private NYEL0050Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return SSML0002Query
     */
    public static NYEL0050Query getInstance() {
        return myInstance;
    }

    /**
     * Execute NYEL0050Query SQL.
     * @param cMsg
     * @param sMsg
     * @return
     */
    public int exec(EZDCMsg cMsg, EZDSMsg sMsg) {

        NYEL0050CMsg bizMsg = (NYEL0050CMsg) cMsg;

        HashMap<String, Object> mapParam = new HashMap<String, Object>();

        if (!bizMsg.A.no(0).glblCmpyCd.isClear()) {
            mapParam.put("glblCmpyCd", bizMsg.A.no(0).glblCmpyCd.getValue());
        }

        NYEL0050_ACMsgArray resultObject = bizMsg.A;

        int hitCount = 0;
        int vc = 0;
        S21SsmEZDClient ssmClient = S21SsmEZDClient.getClient(this.getClass());
        S21SsmEZDResult result = ssmClient.queryEZDMsgArray("getBusinessApps", mapParam, resultObject);

        if (result != null) {
            hitCount = resultObject.getValidCount();
            if (result.getQueryResultCount() > MAX_ROW) {
                bizMsg.setMessageInfo("ZZZM9002W");
            }
            // numbering row number.
            if (resultObject != null)
                vc = resultObject.getValidCount();
            EZDDebugOutput.println(1, "result object rows :" + vc, this);

        } else {
            bizMsg.A.setValidCount(0);
            bizMsg.setMessageInfo("ZZZM9001E");
        }
        return hitCount;
    }
}
