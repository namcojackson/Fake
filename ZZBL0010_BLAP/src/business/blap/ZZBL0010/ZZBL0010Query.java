package business.blap.ZZBL0010;

import java.util.HashMap;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.ZZBL0010.ZZBL0010CMsg;
import business.blap.ZZBL0010.ZZBL0010Query;
import business.blap.ZZBL0010.ZZBL0010SMsg;
import business.blap.ZZBL0010.ZZBL0010_ASMsgArray;
import business.blap.ZZBL0010.constant.ZZBL0010Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmEZDClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;

public final class ZZBL0010Query implements ZZBL0010Constant {

    /**
     * Singleton instance.
     */
    private static final ZZBL0010Query myInstance = new ZZBL0010Query();

    /**
     * Constructor.
     */
    private ZZBL0010Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return SSML0002Query
     */
    public static ZZBL0010Query getInstance() {
        return myInstance;
    }

    /**
     * Execute ZZBL0010Query SQL.
     * @param cMsg
     * @param sMsg
     * @return
     */
    public int exec(EZDCMsg cMsg, EZDSMsg sMsg) {

        ZZBL0010CMsg bizMsg = (ZZBL0010CMsg) cMsg;
        ZZBL0010SMsg sesMsg = (ZZBL0010SMsg) sMsg;

        HashMap<String, Object> mapParam = new HashMap<String, Object>();

        if (!bizMsg.batProcLogPk.isClear()) {
            mapParam.put("batProcLogPk", bizMsg.batProcLogPk.getValue());
        }

        if (!bizMsg.glblCmpyCd.isClear()) {
            mapParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        }

        if (!bizMsg.batProcSubSysCd.isClear()) {
            mapParam.put("batProcSubSysCd", bizMsg.batProcSubSysCd.getValue());
        }

        if (!bizMsg.batProcJbntId.isClear()) {
            mapParam.put("batProcJbntId", bizMsg.batProcJbntId.getValue());
        }

        if (!bizMsg.batProcJobId.isClear()) {
            mapParam.put("batProcJobId", bizMsg.batProcJobId.getValue());
        }

        if (!bizMsg.batProcPgmId.isClear()) {
            mapParam.put("batProcPgmId", bizMsg.batProcPgmId.getValue());
        }

        if (!bizMsg.batProcTrmCd.getValue().equals("-1")) {
            mapParam.put("batProcTrmCd", bizMsg.batProcTrmCd.getValue());
        }

        if (!bizMsg.effFromDt.isClear()) {
            mapParam.put("effFromDt", bizMsg.effFromDt.getValue() + "000000000");
        }

        if (!bizMsg.effToDt.isClear()) {
            mapParam.put("effToDt", bizMsg.effToDt.getValue() + "235959999");
        }
        ZZBL0010_ASMsgArray resultObject = sesMsg.A;

        int hitCount = 0;
        S21SsmEZDClient ssmClient = S21SsmEZDClient.getClient(this.getClass());
        S21SsmEZDResult result = ssmClient.queryEZDMsgArray("getBatchLog", mapParam, resultObject);
        if (result != null) {
            hitCount = resultObject.getValidCount();
            if (result.getQueryResultCount() > MAX_ROW) {
                bizMsg.setMessageInfo("ZZZM9002W");
            }
            // numbering row number.
            if (resultObject != null) {
                int vc = resultObject.getValidCount();
                String dtYear;
                String dtTime;
                String dtTimeFormat;
                String dtMSec;

                for (int i = 0; i < vc; i++) {
                    resultObject.no(i).xxRowNum_A.setValue(i + 1);

                    // Start Time
                    dtYear = ZYPDateUtil.convertFormat(resultObject.no(i).batProcStartTs_A.getValue().substring(0, 8), ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, '/');
                    dtTime = resultObject.no(i).batProcStartTs_A.getValue().substring(8, 14);
                    dtTimeFormat = dtTime.substring(0, 2) + ":" + dtTime.substring(2, 4) + ":" + dtTime.substring(4, 6);
                    dtMSec = resultObject.no(i).batProcStartTs_A.getValue().substring(14, resultObject.no(i).batProcStartTs_A.getValue().length());
                    resultObject.no(i).xxDtTm_ST.setValue(dtYear + " " + dtTimeFormat + ":" + dtMSec);

                    // End Time
                    dtYear = ZYPDateUtil.convertFormat(resultObject.no(i).batProcEndTs_A.getValue().substring(0, 8), ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, '/');
                    dtTime = resultObject.no(i).batProcEndTs_A.getValue().substring(8, 14);

                    dtTimeFormat = dtTime.substring(0, 2) + ":" + dtTime.substring(2, 4) + ":" + dtTime.substring(4, 6);
                    resultObject.no(i).xxDtTm_EN.setValue(dtYear + " " + dtTimeFormat + ":" + dtMSec);
                }
            }
        } else {
            bizMsg.A.setValidCount(0);
            bizMsg.setMessageInfo("ZZZM9001E");
        }
        return hitCount;
    }
}
