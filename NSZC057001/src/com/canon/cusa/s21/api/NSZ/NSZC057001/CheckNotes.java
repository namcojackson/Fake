/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.setValueTMsg;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.parts.NSZC057001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/03/14   Hitachi         K.Kojima        Create          QC#24238
 * </pre>
 */
public class CheckNotes {

    /**
     * checkFmContract
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkNotes(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        if (!ONBATCH_TYPE.ONLINE.equals(mainClass.onBatchType)) {
            return rtrnTMsgArray;
        }

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);

        if (!ZYPConstant.FLG_ON_Y.equals(dsContrTMsg.qltyAsrnHldFlg.getValue())) {
            return rtrnTMsgArray;
        }

        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

        BigDecimal notesCount = NSZC057001CommonLogic.getNotesCount(mainClass, dsContrTMsg.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());
        if (notesCount != null && notesCount.compareTo(BigDecimal.ZERO) == 0) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1322E), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        }
        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        return rtrnTMsgArray;
    }

}
