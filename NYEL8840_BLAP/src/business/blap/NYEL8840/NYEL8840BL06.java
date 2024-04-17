/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8840;

import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0004I;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0020E;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;

import business.blap.NYEL8840.common.NYEL8840CommonLogic;
import business.db.WF_DLGT_USRTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.user.S21NwfUserDelegate;
import com.canon.cusa.s21.framework.nwf.core.user.S21NwfUserDelegateContainer;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NYEL8840BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/26   Fujitsu         M.Yamada        Create          N/A
 * 2016/09/08   Fujitsu         M.Ugaki         Update          N/A
 * 2018/02/05   Fujitsu         M.Ugaki         Update          N/A
 * 2018/09/26   Fujitsu         C.Ogaki         Update          N/A
 * 2022/12/26   Fujitsu         Mz.Takahashi    Update          QC#60743
 *</pre>
 */
public class NYEL8840BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NYEL8840CMsg bizMsg = (NYEL8840CMsg) cMsg;
            NYEL8840SMsg glblMsg = (NYEL8840SMsg) sMsg;

            if ("NYEL8840Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NYEL8840Scrn00_CMN_Submit(bizMsg, glblMsg);

// DEL Start 2018/09/26
//            } else if ("NYEL8840Scrn00_Add".equals(screenAplID)) {
//                doProcess_NYEL8840Scrn00_Add(bizMsg, glblMsg);
//
//            } else if ("NYEL8840Scrn00_Delete".equals(screenAplID)) {
//                doProcess_NYEL8840Scrn00_Delete(bizMsg, glblMsg);
//
//            } else if ("NYEL8840Scrn00_Modify".equals(screenAplID)) {
//                doProcess_NYEL8840Scrn00_Modify(bizMsg, glblMsg);

// DEL End 2018/09/26
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NYEL8840Scrn00_CMN_Submit(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {

// ADD Start 2018/09/26
        NYEL8840CommonLogic.copyCMsgToSMsg(bizMsg, glblMsg);

        if (glblMsg.A.getValidCount() == 0 && glblMsg.D.getValidCount() == 0){
            // no data
            return;
        }
// ADD End 2018/09/26

// DEL Start 2018/09/26
//        // Current Dt
//        SimpleDateFormat formatA = new SimpleDateFormat("yyyyMMdd");
//        Date curDt = null;
//
//        try {
//            curDt = formatA.parse(ZYPDateUtil.getCurrentSystemTime("yyyyMMdd"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//            throw new S21AbendException("Cannot parse system time.");
//        }

// DEL End 2018/09/26
        //Get Assigner
        String fromUser = bizMsg.xxWfAsgCd_F.getValue();
        if (!ZYPCommonFunc.hasValue(fromUser)) {
            return;
        }

        // Redmine#2106 Bug fix
        // Adjust target fromUser's data only
// MOD Start 2018/09/26
//        adjustMsgs(bizMsg);
//        NYEL8840CommonLogic.adjustMsgs(bizMsg);
        if (ZYPCommonFunc.hasValue(bizMsg.xxWfAsgCd_FS)) {
            if (!bizMsg.xxWfAsgCd_F.getValue().equals(bizMsg.xxWfAsgCd_FS.getValue())) {
                bizMsg.xxWfAsgCd_F.setErrorInfo(1, NYEM0020E, new String[] {bizMsg.xxWfAsgCd_FS.getValue() });
                return;
            }
        }
// MOD End 2018/09/26

        try {
// ADD Start 2018/09/26
            // DELETE
            if (glblMsg.D.getValidCount() > 0) {
                WF_DLGT_USRTMsg tmsg = null;
                S21NwfUserDelegate dlgt = null;
                StringBuilder sb = new StringBuilder();
                String param = "";
                for (int i=0; i<glblMsg.D.getValidCount(); i++) {
                    param = "'" + glblMsg.D.no(i).wfDlgtUsrPk_D1.getValueInt() + "'";
                    if (i > 0) {
                        sb.append("," + param);
                    } else {
                        sb.append(param);
                    }
                }
                List<S21NwfUserDelegate> listDlg = S21NwfUserDelegateContainer.getInstance().getDelegateByPks(sb.toString());
                if (listDlg != null) {
                    for (S21NwfUserDelegate dlg : listDlg) {
                        S21NwfUserDelegateContainer.getInstance().removeByUId(dlg);
                    }
                }
            }
            EZDDebugOutput.println( 1, "Delete : " + glblMsg.D.getValidCount(), null );

            NYEL8840_ASMsg msg = null;
            int count = 0;
            for (int i=0; i<glblMsg.A.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).ezUpTime_A1)) {
                    // New Data
                    msg = glblMsg.A.no(i);

                    // Param,Start,End,org User, delegeate user, comment
                    S21NwfUserDelegateContainer.getInstance().addDelegateUser(
                            msg.wfBizAppId_A1.getValue()
                            , msg.effFromDt_A1.getValue()
                            , msg.effFromHourMn_AH.getValue() + msg.effFromHourMn_AM.getValue()
                            , msg.effThruDt_A1.getValue()
                            , msg.effThruHourMn_AH.getValue() + msg.effThruHourMn_AM.getValue()
                            , msg.xxWfAsgCd_A2.getValue()
                            , msg.xxWfAsgCd_A1.getValue()
                            , msg.wfDescTxt_A1.getValue());
                    count++;

                }
            }
            EZDDebugOutput.println( 1, "Create : " + count, null );

            ZYPTableUtil.clear(bizMsg.A);
            ZYPTableUtil.clear(glblMsg.A);
            ZYPTableUtil.clear(glblMsg.D);
            ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAppId, bizMsg.wfBizAppId_S.getValue());
            NYEL8840CommonLogic.search(bizMsg, glblMsg);
            NYEL8840CommonLogic.copySMsgToCMsg(bizMsg, glblMsg);
// ADD End 2018/09/26
// DEL Start 2018/09/26
//            //
//            // All delete
//            //
//            List<S21NwfUserDelegate> listDlg = S21NwfUserDelegateContainer.getInstance().getDelegateIncFutuerByFromUser(fromUser);
//          //List<S21NwfUserDelegate> listDlg = S21NwfUserDelegateContainer.getInstance().getDelegateIncFutuerByFromUser(getContextUserInfo().getUserId());
//
//            if (listDlg != null) {
//
//                int maxDeleteSize = listDlg.size();
//                SimpleDateFormat formatT = new SimpleDateFormat("yyyyMMdd");
//
//                for (int cnt = maxDeleteSize - 1; cnt >= 0; cnt--) {
//                    S21NwfUserDelegate dlgInf = listDlg.get(cnt);
//                    String toDt = dlgInf.getEnd();
//
//                    try {
//                        if (NYEL8840CommonLogic.isDisplay(curDt, formatT.parse(toDt)) == false) {
//                            continue;
//                        }
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                        throw new S21AbendException("Cannot parse time.[" + toDt + "]");
//                    }
//
//                    S21NwfUserDelegateContainer.getInstance().removeByUId(dlgInf);
//                }
//            }
//
//            //
//            // All add
//            //
//            int max = bizMsg.A.getValidCount();
//
//            for (int cnt = 0; cnt < max; cnt++) {
//                NYEL8840_ACMsg msg = bizMsg.A.no(cnt);
//
//                // Param,Start,End,org User, delegeate user, comment
//                //S21NwfUserDelegateContainer.getInstance().addDelegateUser(msg.wfBizAppId_A1.getValue(), msg.effFromDt_A1.getValue(), msg.effThruDt_A1.getValue(), getContextUserInfo().getUserId(), msg.xxWfAsgCd_A1.getValue(), msg.wfDescTxt_A1.getValue());
//                S21NwfUserDelegateContainer.getInstance().addDelegateUser(msg.wfBizAppId_A1.getValue(), msg.effFromDt_A1.getValue(), msg.effThruDt_A1.getValue(), msg.xxWfAsgCd_A2.getValue(), msg.xxWfAsgCd_A1.getValue(), msg.wfDescTxt_A1.getValue());
//            }
// DEL End 2018/09/26
        } catch (S21NwfSystemException e) {
            e.printStackTrace();
            throw new S21AbendException("Cannot Submit");
        }
// ADD Start 2018/09/26
        bizMsg.setMessageInfo(NYEM0004I);
// ADD End 2018/09/26
    }

// DEL Start 2018/09/26
//    /**
//     * Add Event
//     * @param bizMsg Business Msg
//     * @param glblMsg Global Msg
//     */
//    private void doProcess_NYEL8840Scrn00_Add(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {
//        //
//        if (!ZYPCommonFunc.hasValue(bizMsg.xxWfAsgCd_F)) {
//            bizMsg.xxWfAsgCd_F.setErrorInfo(1, ZZM9000E, new String[] {"Assigner" });
//            return;
//        }
//
//        // Redmine#2106 Bug fix
//        // Adjust target fromUser's data only
//        adjustMsgs(bizMsg);
//
//        //Check and Get Assigner Name
//        String assignFromNm = NYEL8840CommonLogic.getAssignerNm(bizMsg, getContextUserInfo().getUserId());
//        if (!ZYPCommonFunc.hasValue(assignFromNm)) {
//            return;
//        }
//
//        if (!ZYPCommonFunc.hasValue(bizMsg.xxWfAsgCd)) {
//            bizMsg.xxWfAsgCd.setErrorInfo(1, ZZM9000E, new String[] {"Assignee" });
//            return;
//        }
//        NYEL8840CommonLogic.getAssigneeNm(bizMsg);
//        if (bizMsg.xxWfAsgCd.getErrorCode() != 0) {
//            return;
//        }
//
//        if (!ZYPCommonFunc.hasValue(bizMsg.effFromDt)) {
//            bizMsg.effFromDt.setErrorInfo(1, ZZM9000E, new String[] {"Period(From)" });
//            return;
//        }
//        if (!ZYPCommonFunc.hasValue(bizMsg.effThruDt)) {
//            bizMsg.effThruDt.setErrorInfo(1, ZZM9000E, new String[] {"Period(To)" });
//            return;
//        }
//
//        if (bizMsg.A.getValidCount() == bizMsg.A.length()) {
//            bizMsg.xxWfAsgCd.setErrorInfo(1, NYEM0011E);
//            return;
//        }
//
//        if (!NYEL8840CommonLogic.isValidPeriod(bizMsg)) {
//            return;
//        }
//
//        //String uid = this.getContextUserInfo().getUserId();
//        String asgCd = bizMsg.xxWfAsgCd.getValue();
//        String asgFromCd = bizMsg.xxWfAsgCd_F.getValue();
//
//        if (asgFromCd.equals(asgCd)) {
//            bizMsg.xxWfAsgCd.setErrorInfo(1, NYEM0009E, new String[] {asgCd});
//            return;
//        }
//
//        NYEL8840_ACMsg bizLineMsg = bizMsg.A.no(bizMsg.A.getValidCount());
//        ZYPEZDItemValueSetter.setValue(bizLineMsg.xxWfAsgCd_A2, bizMsg.xxWfAsgCd_F);
//        ZYPEZDItemValueSetter.setValue(//
//                bizLineMsg.xxWfAsgNm_A2 //
//                , assignFromNm);
//        ZYPEZDItemValueSetter.setValue(bizLineMsg.xxWfAsgCd_A1, bizMsg.xxWfAsgCd);
//        ZYPEZDItemValueSetter.setValue(//
//                bizLineMsg.xxWfAsgNm_A1 //
//                , NYEL8840CommonLogic.getAssigneeNm(bizMsg));
//        ZYPEZDItemValueSetter.setValue(bizLineMsg.wfBizAppId_A1, bizMsg.wfBizAppId);
//        ZYPEZDItemValueSetter.setValue(bizLineMsg.wfDescTxt_AP, procIdToNm(bizMsg));
//        ZYPEZDItemValueSetter.setValue(bizLineMsg.wfDescTxt_A1, bizMsg.wfDescTxt);
//        ZYPEZDItemValueSetter.setValue(bizLineMsg.effFromDt_A1, bizMsg.effFromDt);
//        ZYPEZDItemValueSetter.setValue(bizLineMsg.effThruDt_A1, bizMsg.effThruDt);
//
//        bizMsg.A.setValidCount(bizMsg.A.getValidCount() + 1);
//
////        bizMsg.xxWfAsgCd_F.clear();
////        bizMsg.xxWfAsgNm_F.clear();
//        bizMsg.xxWfAsgCd.clear();
//        bizMsg.xxWfAsgNm.clear();
//        bizMsg.wfBizAppId.setValue(WF_BIZ_APP_ID_ALL);
//        bizMsg.effFromDt.clear();
//        bizMsg.effThruDt.clear();
//        bizMsg.wfDescTxt.clear();
//
//    }
//
//    // Redmine#2106 Bug fix
//    /**
//     * @param bizMsg
//     * @return
//     */
//    private void adjustMsgs(NYEL8840CMsg bizMsg) {
//        int dataSize = bizMsg.A.getValidCount();
//        if (dataSize == 0) {
//            return;
//        }
//
//        // Remove another assigners data
//        NYEL8840CMsg newBizMsg = new NYEL8840CMsg();
//        String asgFromCd = bizMsg.xxWfAsgCd_F.getValue();
//
//        int j = 0;
//        for (int i=0; i<bizMsg.A.getValidCount(); i++) {
//            if (bizMsg.A.no(i).xxWfAsgCd_A2.getValue().equals(asgFromCd)) {
//                EZDMsg.copy(bizMsg.A.no(i), null, newBizMsg.A.no(j), null);
//                j++;
//            }
//        }
//        newBizMsg.A.setValidCount(j);
//        bizMsg.A.clear();
//        EZDMsg.copy(newBizMsg.A, null, bizMsg.A, null);
//    }
//
//    /**
//     * @param bizMsg
//     * @return
//     */
//    private String procIdToNm(NYEL8840CMsg bizMsg) {
//        String ret = "";
//
//        if (!ZYPCommonFunc.hasValue(bizMsg.wfBizAppId)) {
//            return ret;
//        }
//
//        String procId = bizMsg.wfBizAppId.getValue();
//
//        int max = bizMsg.wfBizAppId_L.length();
//
//        for (int cnt = 0; cnt < max; cnt++) {
//            if (!ZYPCommonFunc.hasValue(bizMsg.wfBizAppId_L.no(cnt))) {
//                continue;
//            }
//
//            if (procId.equals(bizMsg.wfBizAppId_L.no(cnt).getValue())) {
//                ret = bizMsg.wfDescTxt_L.no(cnt).getValue();
//                break;
//            }
//        }
//        return ret;
//    }
// 
//    /**
//     * Delete Event
//     * @param bizMsg Business Msg
//     * @param glblMsg Global Msg
//     */
//    private void doProcess_NYEL8840Scrn00_Delete(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {
//        //
//        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);
//        if (selectedRows.isEmpty()) {
//            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//                bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NYEM0010E);
//            }
//            return;
//        }
//        ZYPTableUtil.deleteRows(bizMsg.A, selectedRows);
//    }
//
//    /**
//     * Modify Event
//     * @param bizMsg Business Msg
//     * @param glblMsg Global Msg
//     */
//    private void doProcess_NYEL8840Scrn00_Modify(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {
//        //
//    }
// DEL Start 2018/09/26

}
