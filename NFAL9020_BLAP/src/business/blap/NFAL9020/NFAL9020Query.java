/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL9020;

import java.util.HashMap;
import java.util.Map;

import business.blap.NFAL9020.constant.NFAL9020Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * Class name: NFAL9020Query
 * <dd>The class explanation: SSM
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public final class NFAL9020Query extends S21SsmEZDQuerySupport implements NFAL9020Constant {

    /**
     * Singleton instance.
     */
    private static final NFAL9020Query INSTANCE = new NFAL9020Query();

    /**
     * Constructor.
     */
    private NFAL9020Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return SSML0001Query
     */
    public static NFAL9020Query getInstance() {
        return INSTANCE;
    }

    /**
     * @param bizMsg NFAL9020CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getResultCountNFAL9020(NFAL9020CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("bizMsg", bizMsg);
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("ajeIdLike", createAjeIDParam(bizMsg));

        return getSsmEZDClient().queryEZDMsg("getResultCountNFAL9020", ssmParam, bizMsg);
    }

    /**
     * @param bizMsg NFAL9020CMsg
     * @param globalMsg NFAL9020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getResultListNFAL9020(NFAL9020CMsg bizMsg, NFAL9020SMsg globalMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("bizMsg", bizMsg);
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("ajeIdLike", createAjeIDParam(bizMsg));
        ssmParam.put("rowNum", globalMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getResultNFAL9020", ssmParam, globalMsg.A);
    }

    private String createAjeIDParam(NFAL9020CMsg bizMsg) {

        if (!bizMsg.ajeId.isClear()) {
            return bizMsg.ajeId.getValue() + "%";
        } else {
            if (!bizMsg.sysSrcCd_3.isClear() && !bizMsg.trxCd_3.isClear() && !bizMsg.trxRsnCd_3.isClear()) {
                // SrcCd-Trx-TrxRsn
                return bizMsg.sysSrcCd_3.getValue() + "-" + bizMsg.trxCd_3.getValue() + "-" + bizMsg.trxRsnCd_3.getValue();
            } else if (!bizMsg.sysSrcCd_3.isClear() && bizMsg.trxCd_3.isClear() && bizMsg.trxRsnCd_3.isClear()) {
                // SrcCd%
                return bizMsg.sysSrcCd_3.getValue() + "-%";
            } else if (bizMsg.sysSrcCd_3.isClear() && !bizMsg.trxCd_3.isClear() && bizMsg.trxRsnCd_3.isClear()) {
                // %Trx%
                return "%-" + bizMsg.trxCd_3.getValue() + "-%";
            } else if (!bizMsg.sysSrcCd_3.isClear() && !bizMsg.trxCd_3.isClear() && bizMsg.trxRsnCd_3.isClear()) {
                // SrcCd-Trx%
                return bizMsg.sysSrcCd_3.getValue() + "-" + bizMsg.trxCd_3.getValue() + "-%";
            } else if (bizMsg.sysSrcCd_3.isClear() && !bizMsg.trxCd_3.isClear() && !bizMsg.trxRsnCd_3.isClear()) {
                // %Trx-TrxRsn
                return "%-" + bizMsg.trxCd_3.getValue() + "-" + bizMsg.trxRsnCd_3.getValue();
            } else {
                return "%%";
            }
        }
    }

}
