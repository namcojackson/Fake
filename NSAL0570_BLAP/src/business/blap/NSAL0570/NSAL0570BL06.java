/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0570;

import static business.blap.NSAL0570.constant.NSAL0570Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0570.common.NSAL0570CommonLogic;
import business.db.DS_CONTR_TRKTMsg;
import business.parts.NSZC047008PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_RSN;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Overage Pricing Effectivity
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2015/12/10   Hitachi         A.Kohinata      Update          QC1718
 * 2015/12/10   Hitachi         T.Kanasaka      Update          QC#1815
 * 2015/12/23   Hitachi         K.Yamada        Update          CSA QC#1983
 * 2016/02/16   Hitachi         K.Kishimoto     Update          QC#2063,QC#3844,QC#3845,QC#3846
 * 2016/04/06   Hitachi         K.Kishimoto     Update          QC#6585
 * 2016/04/11   Hitachi         K.Kishimoto     Update          QC#6728
 * 2016/05/20   Hitachi         T.Tomita        Update          QC#4923
 * 2016/06/03   Hitachi         T.Tomita        Update          QC#1523, 4624
 * 2019/11/25   Hitachi         K.Kitachi       Update          QC#54703
 *</pre>
 */
public class NSAL0570BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0570CMsg cMsg = (NSAL0570CMsg) arg0;
        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0570Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0570Scrn00_CMN_Submit(cMsg);
            }
            // del start 2015/12/23 CSA Defect#1983
            //else if ("NSAL0570Scrn00_TopSchedules".equals(screenAplID)) {
            //    doProcess_NSAL0570Scrn00_TopSchedules(cMsg);
            //}
            // del end 2015/12/23 CSA Defect#1983
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSAL0570Scrn00_CMN_Submit(NSAL0570CMsg cMsg) {
        doProcess(cMsg);
    }

    // START 2016/05/20 T.Tomita [QC#4923, DEL]
//    private void doProcess_NSAL0570Scrn00_TopSchedules(NSAL0570CMsg cMsg) {
//        doProcess(cMsg);
//    }
    // END 2016/05/20 T.Tomita [QC#4923, DEL]

    //Mod Start 02/16/2016 <QC#3844,QC#3845,QC#3846>
    // START 2015/12/10 T.Kanasaka [QC#1815, MOD]
    private void doProcess(NSAL0570CMsg cMsg) {
        // START 2019/11/25 K.Kitachi [QC#54703, ADD]
        boolean existUnbilledRebill = NSAL0570Query.getInstance().existUnbilledRebill(cMsg.glblCmpyCd.getValue(), cMsg.dsContrBllgMtrPk_H1.getValue());
        if (existUnbilledRebill) {
            cMsg.setMessageInfo(NSAM0754E);
            return;
        }
        // END 2019/11/25 K.Kitachi [QC#54703, ADD]
        //Add Start 04/06/2016 <QC#6585>
        NSAL0570CommonLogic.copyPeBlock(cMsg);
        //Add End   04/06/2016 <QC#6585>
        // START 2016/05/20 T.Tomita [QC#4923, DEL]
//        // set reason / comment
//        NSAL0570CommonLogic.setReasonComment(cMsg);
        // END 2016/05/20 T.Tomita [QC#4923, DEL]
        // error check
        if (NSAL0570CommonLogic.isErrorTopSchedule(cMsg)) {
            return;
        }
        // call api
        NSZC047008PMsg pMsg = NSAL0570CommonLogic.createApi(cMsg);
        NSAL0570CommonLogic.executeApi(pMsg);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            cMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[] {pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue(), pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_1.getValue() });
            return;
        }
        // Add Start 02/16/2016 <QC#2063>
        if (!NSAL0570CommonLogic.createXsCopy(cMsg, cMsg.dsContrDtlPk_H1.getValue(), cMsg.dsContrBllgMtrPk_H1.getValue())) {
            return;
        }
        // Add End   02/16/2016 <QC#2063>

        //update price effectivity for Machine of Aggregate contract
        if (DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.dsContrDtlTpCd_A.getValue())) {
            NSAL0570CommonLogic.updateUnderAgg(cMsg);
        }

        // create reason / comment
        NSAL0570CommonLogic.insertSvcMemo(cMsg);

        // add start 2016/06/03 CSA Defect#1523, 4624
        if (!callContrTrkAPI(cMsg, cMsg.dsContrPk_H1.getValue())) {
            return;
        }
        // add end 2016/06/03 CSA Defect#1523, 4624
        // START 2016/05/20 T.Tomita [QC#4923, ADD]
        cMsg.setMessageInfo(NSAM0200I);
        // END 2016/05/20 T.Tomita [QC#4923, ADD]

        // api result copy (dsContrPrcEffPk)
//        setPMsgToCMsg(cMsg, pMsg);
    }
    //Mod End  02/16/2016 <QC#3844,QC#3845,QC#3846>

    // START 2016/05/20 T.Tomita [QC#4923, DEL]
//    private void setPMsgToCMsg(NSAL0570CMsg cMsg, NSZC047008PMsg pMsg) {
//        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
//            // START 2015/12/10 [QC1718, MOD]
//            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).dsContrPrcEffPk_A1, pMsg.xxMtrLineList.no(i).dsContrPrcEffPk_ML);
//            // END 2015/12/10 [QC1718, MOD]
//        }
//    }
    // END 2016/05/20 T.Tomita [QC#4923, DEL]
    // END 2015/12/10 T.Kanasaka [QC#1815, MOD]

    // add start 2016/06/03 CSA Defect#1523, 4624
    private boolean callContrTrkAPI(NSAL0570CMsg cMsg, BigDecimal dsContrPk) {
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String userId = getContextUserInfo().getUserId();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        String stsMemoRsnCd = DS_CONTR_TRK_RSN.CONTRACT_MODE_CHANGE;
        String stsMemoTxt = cMsg.svcCmntTxt_H1.getValue();
        DS_CONTR_TRKTMsg dsContrTrkTMsg = new DS_CONTR_TRKTMsg();
        if (hasValue(stsMemoTxt) && stsMemoTxt.length() > dsContrTrkTMsg.getAttr("stsMemoTxt").getDigit()) {
            stsMemoTxt = stsMemoTxt.substring(0, dsContrTrkTMsg.getAttr("stsMemoTxt").getDigit());
        }
        if (!NSXC001001ContractTracking.callContrTrk(glblCmpyCd, ContrTrkProcMode.USER_OPERATION.code, dsContrPk, userId, slsDt, stsMemoRsnCd, stsMemoTxt, ONBATCH_TYPE.ONLINE)) {
            cMsg.setMessageInfo(NSXC001001ContractTracking.ERR_MSG_ID);
            return false;
        }
        return true;
    }
    // add end 2016/06/03 CSA Defect#1523, 4624
}
