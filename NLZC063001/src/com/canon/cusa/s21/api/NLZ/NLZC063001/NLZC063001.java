/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC063001;

import static com.canon.cusa.s21.api.NLZ.NLZC063001.constant.NLZC063001Constant.BIND_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC063001.constant.NLZC063001Constant.BIND_PARAM_PHYS_INVTY_NUM;
import static com.canon.cusa.s21.api.NLZ.NLZC063001.constant.NLZC063001Constant.DB_RESULT_COL_PHYS_INVTY_CTRL_PK;
import static com.canon.cusa.s21.api.NLZ.NLZC063001.constant.NLZC063001Constant.NDAM0013E;
import static com.canon.cusa.s21.api.NLZ.NLZC063001.constant.NLZC063001Constant.NLCM0154E;
import static com.canon.cusa.s21.api.NLZ.NLZC063001.constant.NLZC063001Constant.NLCM0155E;
import static com.canon.cusa.s21.api.NLZ.NLZC063001.constant.NLZC063001Constant.NLCM0163E;
import static com.canon.cusa.s21.api.NLZ.NLZC063001.constant.NLZC063001Constant.NLCM0164E;
import static com.canon.cusa.s21.api.NLZ.NLZC063001.constant.NLZC063001Constant.NMZM0011E;
import static com.canon.cusa.s21.api.NLZ.NLZC063001.constant.NLZC063001Constant.TIMESTAMP_PATTERN;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.PHYS_INVTY_CTRLTMsg;
import business.db.PHYS_INVTY_STSTMsg;
import business.parts.NLZC063001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_CNT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;

/**
 *<pre>
 * PI Close API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/14/2016   CITS            K.Ogino           Create          Initial
 * 09/21/2018   CITS            K.Ogino           Update         QC#28191
 * 10/19/2018   CITS            M.Naito           Update         QC#28770
 *</pre>
 */
public class NLZC063001 extends S21ApiCommonBase {
    // -------------------------------------------------
    // Instance Fields
    // -------------------------------------------------

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchTypeApi = null;

    /**
     * Constructor
     */
    public NLZC063001() {
        super();
    }

    /**
     * Tech-PI Approval to WF API
     * @param param pMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NLZC063001PMsg param, final ONBATCH_TYPE onBatchType) {
        this.msgMap = new S21ApiMessageMap(param);
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.onBatchTypeApi = onBatchType;
        NLZC063001PMsg pMsg = (NLZC063001PMsg) this.msgMap.getPmsg();
        doProcess(pMsg);
        this.msgMap.flush();
    }

    /**
     * doProcess
     * @param pMsg NLZC063001PMsg
     */
    private void doProcess(NLZC063001PMsg pMsg) {
        checkInputParam(pMsg);

        if (hasError()) {
            return;
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(BIND_PARAM_GLBL_CMPY_CD, pMsg.glblCmpyCd.getValue());
        paramMap.put(BIND_PARAM_PHYS_INVTY_NUM, pMsg.physInvtyNum.getValue());
        updatePhysInvtyCtrl("findPiControl", paramMap);
    }

    // -----------------------------------------------------------
    // Validation
    // -----------------------------------------------------------
    private void checkInputParam(NLZC063001PMsg pMsg) {

        if (!hasValue(pMsg.glblCmpyCd)) {
            addError(NDAM0013E);
        }
        if (!hasValue(pMsg.salesDate)) {
            addError(NMZM0011E);
        }
        if (!hasValue(pMsg.physInvtyNum)) {
            addError(NLCM0155E);
        }
        if (!hasValue(pMsg.physInvtyCntStsCd)) {
            addError(NLCM0164E);
        }
    }

    // -----------------------------------------------------------
    // Util
    // -----------------------------------------------------------
    private boolean hasError() {
        return this.msgMap.getMsgMgr().isXxMsgId();
    }

    private void addError(String xxMsgId) {
        this.msgMap.addXxMsgId(xxMsgId);
    }

    // -----------------------------------------------------------
    // Data base Query
    // -----------------------------------------------------------
    /**
     * db query
     * @param sqlId String
     * @param paramMap Map<String, Object>
     */
    private void updatePhysInvtyCtrl(String sqlId, Map<String, Object> paramMap) {
        this.ssmBatchClient.queryObject(sqlId, paramMap, new UpdatePhysInvtyCtrl(msgMap));
    }

    /**
     * SSM Innner Class
     */
    protected class UpdatePhysInvtyCtrl extends S21SsmVoidResultSetHandlerSupport {

        /**
         * S21ApiMessageMap
         */
        private S21ApiMessageMap msgMap;

        public UpdatePhysInvtyCtrl(S21ApiMessageMap msgMap) {
            this.msgMap = msgMap;
        }

        public void doProcessQueryResult(ResultSet rs) throws SQLException {
            NLZC063001PMsg pMsg = (NLZC063001PMsg) this.msgMap.getPmsg();
            int rsCount = 0;
            for (; rs.next();) {
                // find pk
                BigDecimal physInvtyCtrlPk = rs.getBigDecimal(DB_RESULT_COL_PHYS_INVTY_CTRL_PK);

                // Lock Table PHYS_INVTY_CTRL
                PHYS_INVTY_CTRLTMsg inMsg = new PHYS_INVTY_CTRLTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
                inMsg.physInvtyCtrlPk.setValue(physInvtyCtrlPk);
                PHYS_INVTY_CTRLTMsg tMsg = (PHYS_INVTY_CTRLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    this.msgMap.addXxMsgId(NLCM0163E);
                    return;
                }

                // update table PHYS_INVTY_CTRL
                ZYPEZDItemValueSetter.setValue(tMsg.physInvtyNum, pMsg.physInvtyNum);
                PHYS_INVTY_STSTMsg phisTMsg = new PHYS_INVTY_STSTMsg();
                ZYPEZDItemValueSetter.setValue(phisTMsg.glblCmpyCd, pMsg.glblCmpyCd);
                // QC#28191
                if (PHYS_INVTY_CNT_STS.PI_CANCELED.equals(pMsg.physInvtyCntStsCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(phisTMsg.physInvtyStsCd, PHYS_INVTY_STS.CANCELLED);
                    ZYPEZDItemValueSetter.setValue(tMsg.physInvtyStsCd, PHYS_INVTY_STS.CANCELLED);
                } else {
                    ZYPEZDItemValueSetter.setValue(phisTMsg.physInvtyStsCd, PHYS_INVTY_STS.CLOSE);
                    ZYPEZDItemValueSetter.setValue(tMsg.physInvtyStsCd, PHYS_INVTY_STS.CLOSE);
                }
                PHYS_INVTY_STSTMsg outPhisTMsg = (PHYS_INVTY_STSTMsg) S21ApiTBLAccessor.findByKey(phisTMsg);
                ZYPEZDItemValueSetter.setValue(tMsg.physInvtyStsNm, outPhisTMsg.physInvtyStsDescTxt.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.physInvtyCntStsCd, pMsg.physInvtyCntStsCd);
                // START 2018/10/19 M.Naito [QC#28770, MOD]
                if (!ZYPCommonFunc.hasValue(tMsg.adjSubmtTs.getValue())) {
                    ZYPEZDItemValueSetter.setValue(tMsg.adjSubmtTs, ZYPDateUtil.getCurrentSystemTime(TIMESTAMP_PATTERN));
                }
                // END 2018/10/19 M.Naito [QC#28770, MOD]
                S21ApiTBLAccessor.update(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    this.msgMap.addXxMsgId(NLCM0163E);
                    return;
                }
                rsCount++;
            }
            if (rsCount == 0) {
                this.msgMap.addXxMsgId(NLCM0154E);
            }
        }
    }
}
