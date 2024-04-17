package business.blap.ZZOL0052;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZOL0052BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZOL0052_INIT".equals(screenAplID)) {
                doProcess_ZZOL0052_INIT((ZZOL0052CMsg) cMsg);

            } else if ("ZZOL0052Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZOL0052Scrn00_Search((ZZOL0052CMsg) cMsg, sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * initialization processing
     * @param cMsg EZDCMsg
     */
    private void doProcess_ZZOL0052_INIT(ZZOL0052CMsg cMsg) {

        doProcess_ZZOL0052Scrn00_Search(cMsg, null);
    }

    /**
     * search processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZOL0052Scrn00_Search(ZZOL0052CMsg cMsg, EZDSMsg sMsg) {

        S21SsmEZDResult ssmResult = ZZOL0052Query.getInstance().getList(cMsg);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > cMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = cMsg.A.length();
            }
            cMsg.A.setValidCount(queryResCnt);

            // 検索結果なし
        } else {
            cMsg.setMessageInfo("ZZZM9001E");
        }
    }

}
