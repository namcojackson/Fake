/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC053001;


import static com.canon.cusa.s21.api.NSZ.NSZC053001.constant.NSZC053001Constant.*;

import java.util.ArrayList;
import java.util.List;

import business.db.SVC_CR_REBILTMsg;
import business.db.SVC_CR_REBIL_MTR_READTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Credit Rebill Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/01/2016   Hitachi         T.Aoyagi        Create          N/A
 * 04/08/2016   Hitachi         T.Aoyagi        Update          QC#5901
 * 05/10/2016   Hitachi         T.Aoyagi        Update          QC#7734
 * 2017/09/26   Hitachi         K.Kitachi       Update          QC#21212
 * 2018/06/05   Hitachi         K.Kojima        Update          QC#21974
 * 2018/06/25   Hitachi         K.Kitachi       Update          QC#22245
 * 2018/07/18   Hitachi         K.Kojima        Update          QC#26791
 * 2018/09/05   Hitachi         K.Kishimoto     Update          QC#24555
 *</pre>
 */
public class CIEntryProcess implements ZYPConstant {

    /** onBatchTp */
    private ONBATCH_TYPE onBatchTp;

    // START 2017/09/26 K.Kitachi [QC#21212, ADD]
    /** NSZC0530Query */
    private NSZC0530Query query = NSZC0530Query.getInstance();
    // END 2017/09/26 K.Kitachi [QC#21212, ADD]

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        this.onBatchTp = onBatchType;
        cIEntryProcess(msgMap);
    }

    private void cIEntryProcess(S21ApiMessageMap msgMap) {

        if (NSZC053001CommonLogic.existSvcCrRebilByIncdtId(msgMap)) {
            // already exist
            msgMap.addXxMsgId(NSZM0889E);
            return;
        }
        // START 05/10/2016 T.Aoyagi [QC#7734, ADD]
        if (NSZC053001CommonLogic.existSvcCrRebilBySvcInvNum(msgMap)) {
            // already exist
            return;
        }
        // END 05/10/2016 T.Aoyagi [QC#7734, ADD]

        // ----------------------------------------
        // Create SVC_CR_REBIL
        // ----------------------------------------
        SVC_CR_REBILTMsg crRebilTMsg = NSZC053001CommonLogic.createCrRebil(msgMap);

        List<String> svcInvNumList = NSZC053001CommonLogic.getSvcInvNumListFromPMsg(msgMap);
        // START 2017/09/26 K.Kitachi [QC#21212, ADD]
        List<String> tmpSvcInvNumList = new ArrayList<String>();
        // END 2017/09/26 K.Kitachi [QC#21212, ADD]
        for (String svcInvNum : svcInvNumList) {

            List<SVC_CR_REBIL_MTR_READTMsg> fixMtrReadList = new ArrayList<SVC_CR_REBIL_MTR_READTMsg>();

            // ----------------------------------------
            // Create SVC_CR_REBIL Info
            // ----------------------------------------
            NSZC053001CommonLogic.createCrRebilInfo(msgMap, svcInvNum, crRebilTMsg, fixMtrReadList);

            // START 2018/07/18 K.Kojima [QC#26791,ADD]
            NSZC053001CommonLogic.adjustMtrFmla(msgMap, crRebilTMsg.svcCrRebilPk.getValue(), svcInvNum, fixMtrReadList, false);
            // END 2018/07/18 K.Kojima [QC#26791,ADD]

            // START 2017/09/26 K.Kitachi [QC#21212, ADD]
            if (NSZC053001CommonLogic.isPriceChange(msgMap)) {
                List<String> trgtSvcInvNumList = query.getTrgtSvcInvNumList(msgMap, svcInvNum);
                for (String trgtSvcInvNum : trgtSvcInvNumList) {
                    if (svcInvNumList.contains(trgtSvcInvNum) || tmpSvcInvNumList.contains(trgtSvcInvNum)) {
                        continue;
                    }
                    NSZC053001CommonLogic.createCrRebilInfo(msgMap, svcInvNum, trgtSvcInvNum, crRebilTMsg);
                    tmpSvcInvNumList.add(trgtSvcInvNum);
                }
            }
            // END 2017/09/26 K.Kitachi [QC#21212, ADD]

            // ----------------------------------------
            // Adjust Meter Read Info
            // ----------------------------------------
            // START 2018/06/05 K.Kojima [QC#21974,MOD]
            // NSZC053001CommonLogic.adjustMtrRead(msgMap, crRebilTMsg, fixMtrReadList);
            NSZC053001CommonLogic.adjustMtrRead(msgMap, crRebilTMsg, fixMtrReadList, svcInvNum, tmpSvcInvNumList);
            // END 2018/06/05 K.Kojima [QC#21974,MOD]
        }

        // START 2018/09/05 [QC#24555, ADD]
        NSZC053001CommonLogic.roundAdjustForCiEntry(msgMap, crRebilTMsg);
        // END   2018/09/05 [QC#24555, ADD]

        // START 04/08/2016 T.Aoyagi [QC#5901, MOD]
        NSZC053001CommonLogic.callApi(msgMap, this.onBatchTp, crRebilTMsg, FLG_ON_Y, FLG_OFF_N);
        // END 04/08/2016 T.Aoyagi [QC#5901, MOD]
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC053001CommonLogic.updateCrRebilInfo(msgMap, crRebilTMsg);

        // START 2018/06/25 K.Kitachi [QC#22245, ADD]
        NSZC053001CommonLogic.billingHld(msgMap, crRebilTMsg, this.onBatchTp);
        // END 2018/06/25 K.Kitachi [QC#22245, ADD]
    }

}
