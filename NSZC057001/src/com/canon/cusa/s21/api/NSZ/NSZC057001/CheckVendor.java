/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.common.EZDMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.parts.NSZC057001PMsg;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         A.Kohinata      Create          N/A
 * 2016/02/16   Hitachi         A.Kohinata      Update          QC2263
 * 2016/04/19   Hitachi         K.Kishimoto     Update          QC9682
 * 2017/02/20   Hitachi         T.Kanasaka      Update          QC#17614
 * </pre>
 */
public class CheckVendor {

    /**
     * checkValidSalesrep
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkValidSalesrep(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);

        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        if (!hasValue(dsContrTMsg.tocCd)) {
            // START 2016/02/16 A.Kohinata [QC2263, MOD]
//            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0771E), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0867E), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
            // END 2016/02/16 A.Kohinata [QC2263, MOD]
        // START 2017/02/20 T.Kanasaka [QC#17614, ADD]
        } else if (!existSlsRep(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.tocCd.getValue(), param.slsDt.getValue())) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1116E), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        // END 2017/02/20 T.Kanasaka [QC#17614, ADD]
        }
        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        return rtrnTMsgArray;
    }

    // Add Start 04/19/2016 <QC#9682>
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkValidContractrep(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);

        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        if (!hasValue(dsContrTMsg.contrAdminPsnCd)) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0964E), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        }
        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        return rtrnTMsgArray;
    }
    // Add End   04/19/2016 <QC#9682>
}
