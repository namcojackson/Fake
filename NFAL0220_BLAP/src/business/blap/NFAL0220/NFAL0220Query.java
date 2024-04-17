/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFAL0220;

import static business.blap.NFAL0220.constant.NFAL0220Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.JRNL_ENTRYTMsg;
import business.db.JRNL_ENTRYTMsgArray;
import business.db.JRNL_ENTRY_SRC_TPTMsg;
import business.db.JRNL_ENTRY_SRC_TPTMsgArray;
import business.db.MAN_JRNL_ENTRY_DTLTMsg;
import business.db.MAN_JRNL_ENTRY_HDRTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NFAL0220Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/02   Fujitsu         S.Fujita        Create          N/A
 *</pre>
 */
public final class NFAL0220Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NFAL0220Query MY_INSTANCE = new NFAL0220Query();

    /**
     * Private constructor
     */
    private NFAL0220Query() {
        super();
    }

    /**
     * k Get the NFAL0220Query instance.
     * @return NFAL0220Query instance
     */
    public static NFAL0220Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * searchManJrnlEntry
     * @param cMsg NFAL0220CMsg
     * @param sMsg NFAL0220SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchManJrnlEntry(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("manJrnlEntryHdrPk", cMsg.manJrnlEntryHdrPk.getValue());
        params.put("debit", DEBIT);
        params.put("credit", CREDIT);
        params.put("limitCount", sMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("searchManJrnlEntry", params, sMsg.A);
    }

    /**
     * manJrnlEntryHdrFindByKey
     * @param cMsg NFAL0220CMsg
     * @return MAN_JRNL_ENTRY_HDRTMsg
     */
    public MAN_JRNL_ENTRY_HDRTMsg manJrnlEntryHdrFindByKey(NFAL0220CMsg cMsg) {

        MAN_JRNL_ENTRY_HDRTMsg jrnlHdrTMsg = new MAN_JRNL_ENTRY_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(jrnlHdrTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(jrnlHdrTMsg.manJrnlEntryHdrPk, cMsg.manJrnlEntryHdrPk);
        return (MAN_JRNL_ENTRY_HDRTMsg) S21FastTBLAccessor.findByKey(jrnlHdrTMsg);
    }

    /**
     * manJrnlEntryDtlFindByKey
     * @param glblCmpyCd String
     * @param manJrnlEntryDtlPk BigDecimal
     * @return MAN_JRNL_ENTRY_DTLTMsg
     */
    public MAN_JRNL_ENTRY_DTLTMsg manJrnlEntryDtlFindByKey(String glblCmpyCd, BigDecimal manJrnlEntryDtlPk) {

        MAN_JRNL_ENTRY_DTLTMsg jrnlDtlTMsg = new MAN_JRNL_ENTRY_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(jrnlDtlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(jrnlDtlTMsg.manJrnlEntryDtlPk, manJrnlEntryDtlPk);
        return (MAN_JRNL_ENTRY_DTLTMsg) S21FastTBLAccessor.findByKey(jrnlDtlTMsg);
    }

    /**
     * getSellToCustFindByCondition
     * @param cMsg NFAL0220CMsg
     * @param dsAcctNum String
     * @return SELL_TO_CUSTTMsgArray
     */
    public SELL_TO_CUSTTMsgArray getSellToCustFindByCondition(NFAL0220CMsg cMsg, String dsAcctNum) {

        SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();
        sellToCustTMsg.setSQLID("001");
        sellToCustTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        sellToCustTMsg.setConditionValue("sellToCustCd01", dsAcctNum);
        return (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(sellToCustTMsg);
    }

    /**
     * getJrnlEntryFindByCondition
     * @param cMsg NFAL0220CMsg
     * @param manJrnlEntryHdrPk BigDecimal
     * @return JRNL_ENTRYTMsgArray
     */
    public JRNL_ENTRYTMsgArray getJrnlEntryFindByCondition(NFAL0220CMsg cMsg, BigDecimal manJrnlEntryHdrPk) {

        JRNL_ENTRYTMsg jrnlEntryTMsg = new JRNL_ENTRYTMsg();
        jrnlEntryTMsg.setSQLID("001");
        jrnlEntryTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        jrnlEntryTMsg.setConditionValue("manJrnlEntryHdrPk01", manJrnlEntryHdrPk);
        return (JRNL_ENTRYTMsgArray) EZDTBLAccessor.findByCondition(jrnlEntryTMsg);
    }

    /**
     * getJrnLEntrySrcTpFindByCondition
     * @param cMsg NFAL0220CMsg
     * @param jrnlEntrySrcTpNm String
     * @return JRNL_ENTRY_SRC_TPTMsgArray
     */
    public JRNL_ENTRY_SRC_TPTMsgArray getJrnLEntrySrcTpFindByCondition(NFAL0220CMsg cMsg, String jrnlEntrySrcTpNm) {

        JRNL_ENTRY_SRC_TPTMsg jrnlEntrySrcTpTMsg = new JRNL_ENTRY_SRC_TPTMsg();
        jrnlEntrySrcTpTMsg.setSQLID("001");
        jrnlEntrySrcTpTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        jrnlEntrySrcTpTMsg.setConditionValue("jrnlEntrySrcTpNm01", jrnlEntrySrcTpNm);
        return (JRNL_ENTRY_SRC_TPTMsgArray) EZDTBLAccessor.findByCondition(jrnlEntrySrcTpTMsg);
    }

    /**
     * getJrnLEntrySrcTpFindByKey
     * @param cMsg NFAL0220CMsg
     * @param jrnlEntrySrcTpCd String
     * @return JRNL_ENTRY_SRC_TPTMsg
     */
    public JRNL_ENTRY_SRC_TPTMsg getJrnLEntrySrcTpFindByKey(NFAL0220CMsg cMsg, String jrnlEntrySrcTpCd) {

        JRNL_ENTRY_SRC_TPTMsg jrnlEntrySrcTpTMsg = new JRNL_ENTRY_SRC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(jrnlEntrySrcTpTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(jrnlEntrySrcTpTMsg.jrnlEntrySrcTpCd, jrnlEntrySrcTpCd);
        return (JRNL_ENTRY_SRC_TPTMsg) EZDTBLAccessor.findByKey(jrnlEntrySrcTpTMsg);
    }
}
