/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.setValueTMsg;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.parts.NSZC057001PMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TGTR_TP;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/02   Hitachi         K.Kim           Create          QC#28627
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * </pre>
 */
public class CheckLinkNumber {

    /**
     * checkLinkNumber
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkLinkNumber(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        if (!ONBATCH_TYPE.ONLINE.equals(mainClass.onBatchType)) {
            return rtrnTMsgArray;
        }

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);

        if (!hasValue(dsContrTMsg.contrLinkNum)) {
            return rtrnTMsgArray;
        }

        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

        String dsContrNum = dsContrTMsg.dsContrNum.getValue();
        String contrLinkNum = dsContrTMsg.contrLinkNum.getValue();
        String altPayerCustCd = dsContrTMsg.altPayerCustCd.getValue();
        DS_CONTRTMsg parentDsContrTMsg = NSZC057001CommonLogic.getParentDsContr(dsContrTMsg.glblCmpyCd.getValue(), contrLinkNum);

        if (parentDsContrTMsg == null) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1355E, new String[] {contrLinkNum}), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        } else {
            String dsContrStsCd = parentDsContrTMsg.dsContrStsCd.getValue();
            String parentDsContrNum = parentDsContrTMsg.dsContrNum.getValue();
            String parentContrLinkNum = parentDsContrTMsg.contrLinkNum.getValue();
            String parentAltPayerCustCd = parentDsContrTMsg.altPayerCustCd.getValue();
            // START 2022/03/22 [QC#59683, ADD]
            String dsInvTgtrTpCd = parentDsContrTMsg.dsInvTgtrTpCd.getValue();
            // END   2022/03/22 [QC#59683, ADD]

            if (DS_CONTR_STS.EXPIRED.equals(dsContrStsCd) || DS_CONTR_STS.CANCELLED.equals(dsContrStsCd) || DS_CONTR_STS.TERMINATED.equals(dsContrStsCd)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1356E, new String[] {parentDsContrNum}), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
            } else if (!parentDsContrNum.equals(parentContrLinkNum)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1357E), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
            } else if (!parentAltPayerCustCd.equals(altPayerCustCd)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1358E, new String[] {dsContrNum, parentDsContrNum}), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
            // START 2022/03/22 [QC#59683, ADD]
            } else if (DS_INV_TGTR_TP.INVOICE_INDIVIDUALLY.equals(dsInvTgtrTpCd)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSAM0448E, new String[] {"Link#", "Parent Contract's Invoice Option:" + parentDsContrNum, "Invoice Individually" }), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
            // END   2022/03/22 [QC#59683, ADD]
            }
        }

        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        return rtrnTMsgArray;
    }

}
