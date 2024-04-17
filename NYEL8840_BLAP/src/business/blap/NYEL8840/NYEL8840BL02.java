/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8840;

import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0009E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0020E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ZZM9000E;
import static business.blap.NYEL8840.constant.NYEL8840Constant.HHMM_SEPARATOR;
import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NYEL8840.common.NYEL8840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NYEL8840BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/26   Fujitsu         M.Yamada        Create          N/A
 * 2018/02/05   Fujitsu         M.Ugaki         Update          N/A
 * 2018/09/26   Fujitsu         C.Ogaki         Update          N/A
 *</pre>
 */
public class NYEL8840BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NYEL8840CMsg bizMsg = (NYEL8840CMsg) cMsg;
            NYEL8840SMsg glblMsg = (NYEL8840SMsg) sMsg;

            if ("NYEL8840_INIT".equals(screenAplID)) {
                doProcess_NYEL8840_INIT(bizMsg, glblMsg);

            } else if ("NYEL8840Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NYEL8840Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NYEL8840Scrn00_Get_AssignerName".equals(screenAplID)) {
                doProcess_NYEL8840Scrn00_Get_AssignerName(bizMsg, glblMsg);

            } else if ("NYEL8840Scrn00_Get_AssigneeName".equals(screenAplID)) {
                doProcess_NYEL8840Scrn00_Get_AssigneeName(bizMsg, glblMsg);

            } else if ("NYEL8840Scrn00_Search".equals(screenAplID)) {
                doProcess_NYEL8840Scrn00_Search(bizMsg, glblMsg);

// ADD Start 2018/09/26
            } else if( "NYEL8840Scrn00_PageNext".equals( screenAplID ) ) {
                doProcess_NYEL8840Scrn00_PageNext( bizMsg, glblMsg );

            } else if( "NYEL8840Scrn00_PagePrev".equals( screenAplID ) ) {
                doProcess_NYEL8840Scrn00_PagePrev( bizMsg, glblMsg );

            } else if( "NYEL8840Scrn00_Add".equals( screenAplID ) ) {
                doProcess_NYEL8840Scrn00_Add( bizMsg, glblMsg );

            } else if( "NYEL8840Scrn00_Delete".equals( screenAplID ) ) {
                doProcess_NYEL8840Scrn00_Delete( bizMsg, glblMsg );

// ADD End 2018/09/26
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NYEL8840_INIT(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {
        bizMsg.xxWfAsgNm_F.clear();
        bizMsg.xxWfAsgCd.clear();
        bizMsg.xxWfAsgNm.clear();
        bizMsg.wfDescTxt.clear();
        bizMsg.effFromDt.clear();
        bizMsg.effThruDt.clear();

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
// ADD Start 2018/09/26
        ZYPTableUtil.clear(glblMsg.D);

        // pull down
        NYEL8840CommonLogic.initPullDown(bizMsg);

        String myUser = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgCd_F, myUser);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgNm_F, NYEL8840CommonLogic.getAssignerNm(bizMsg, myUser));

        NYEL8840CommonLogic.search(bizMsg, glblMsg);
        NYEL8840CommonLogic.copySMsgToCMsg(bizMsg, glblMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxMaxSrchCnt, BigDecimal.valueOf(glblMsg.A.length()));
// ADD End 2018/09/26
// DEL Start 2018/09/26
//        String myUser = getContextUserInfo().getUserId();
//
//        if (!ZYPCommonFunc.hasValue(bizMsg.xxWfAsgCd_F)) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgCd_F, myUser);
//        }
//
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgNm_F, NYEL8840CommonLogic.getAssignerNm(bizMsg, myUser));
//        S21SsmEZDResult result = NYEL8840Query.getInstance().getDelegateUser(bizMsg, glblMsg);
//
//        if (result.isCodeNormal()) {
//            List resultList = (List) result.getResultObject();
//
//            int max = resultList.size() ;
//
//            // Add For Redmine#2106 Bug fix
//            if (max > glblMsg.A.length()) {
//                bizMsg.setMessageInfo("NZZM0001W");
//                 max = glblMsg.A.length();
//            }
//
//            SimpleDateFormat formatA = new SimpleDateFormat("yyyyMMdd");
//            Date curDt = null;
//
//            try {
//                curDt = formatA.parse(ZYPDateUtil.getCurrentSystemTime("yyyyMMdd"));
//            } catch (ParseException e) {
//                e.printStackTrace();
//                throw new S21AbendException("Cannot parse system time.");
//            }
//
//            for (int cnt = 0; cnt < max; cnt++) {
//                Map map = (Map) resultList.get(cnt);
//                NYEL8840_ACMsg msg = bizMsg.A.no(cnt);
//
//                SimpleDateFormat formatT = new SimpleDateFormat("yyyyMMdd");
//
//                try {
//                    if (NYEL8840CommonLogic.isDisplay(curDt, formatT.parse((String) map.get("EFF_THRU_DT"))) == false) {
//                        continue;
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                    throw new S21AbendException("Cannot parse time.");
//                }
//
//                // Assigner Cd
//                ZYPEZDItemValueSetter.setValue(msg.xxWfAsgCd_A2, bizMsg.xxWfAsgCd_F.getValue());
//
//                // Assigner Name
//                ZYPEZDItemValueSetter.setValue(msg.xxWfAsgNm_A2, NYEL8840CommonLogic.getAssignerNm(bizMsg, myUser));
//
//                // Assignee Cd
//                String asgCd = (String) map.get("WF_USR_ID");
//                ZYPEZDItemValueSetter.setValue(msg.xxWfAsgCd_A1, asgCd);
//
//                // Assignee Name
//                ZYPEZDItemValueSetter.setValue(msg.xxWfAsgNm_A1, NYXC880001.getAssigneeNmFromS21Psn(asgCd));
//
//                // Process Name
//                ZYPEZDItemValueSetter.setValue(msg.wfBizAppId_A1, (String) map.get("WF_BIZ_APP_ID"));
//                ZYPEZDItemValueSetter.setValue(msg.wfDescTxt_AP, (String) map.get("PROCNM"));
//
//                // From
//                ZYPEZDItemValueSetter.setValue(msg.effFromDt_A1, (String) map.get("EFF_FROM_DT"));
//
//                // To
//                ZYPEZDItemValueSetter.setValue(msg.effThruDt_A1, (String) map.get("EFF_THRU_DT"));
//
//                // Comment
//                ZYPEZDItemValueSetter.setValue(msg.wfDescTxt_A1, (String) map.get("WF_DESC_TXT"));
//
//                // EZUPTIME (WF_DLGT_USR)
//                ZYPEZDItemValueSetter.setValue(msg.ezUpTime_A1, (String) map.get("EZUPTIME"));
//            }
//
//            bizMsg.A.setValidCount(max);
//        }
//
//        // pull down
//        NYEL8840CommonLogic.initPullDown(bizMsg);
// DEL Start 2018/09/26
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NYEL8840Scrn00_CMN_Reset(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {
        String myUser = getContextUserInfo().getUserId();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgCd_F, myUser);
        ZYPEZDItemValueSetter.setValue(//
                bizMsg.xxWfAsgNm_F //
                , NYEL8840CommonLogic.getAssignerNm(bizMsg, myUser));

// ADD Start 2018/09/26
        bizMsg.wfBizAppId_S.clear();
        bizMsg.xxWfAsgCd_FS.clear();
// ADD End 2018/09/26
        doProcess_NYEL8840_INIT(bizMsg, glblMsg);

    }

    /**
     * Get_AssignerName Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NYEL8840Scrn00_Get_AssignerName(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.xxWfAsgCd_F)) {
            bizMsg.xxWfAsgCd_F.setErrorInfo(1, ZZM9000E, new String[] {"Assigner" });
            return;
        }

        ZYPEZDItemValueSetter.setValue(//
                bizMsg.xxWfAsgNm_F //
                , NYEL8840CommonLogic.getAssignerNm(bizMsg, getContextUserInfo().getUserId()));
    }

    /**
     * Get_AssigneeName Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NYEL8840Scrn00_Get_AssigneeName(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.xxWfAsgCd)) {
            bizMsg.xxWfAsgCd.setErrorInfo(1, ZZM9000E, new String[] {"Assignee" });
            return;
        }

        ZYPEZDItemValueSetter.setValue(//
                bizMsg.xxWfAsgNm //
                , NYEL8840CommonLogic.getAssigneeNm(bizMsg));
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NYEL8840Scrn00_Search(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.xxWfAsgCd_F)) {
            bizMsg.xxWfAsgCd_F.setErrorInfo(1, ZZM9000E, new String[] {"Assigner" });
            return;
        }

// MOD Start 2018/09/26
//        doProcess_NYEL8840_INIT(bizMsg, glblMsg);
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.D);
        bizMsg.xxPageShowFromNum.clear();
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();
        NYEL8840CommonLogic.search(bizMsg, glblMsg);
        NYEL8840CommonLogic.copySMsgToCMsg(bizMsg, glblMsg);
// MOD End 2018/09/26
    }

// ADD Start 2018/09/26
    private void doProcess_NYEL8840Scrn00_PageNext(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {

        NYEL8840CommonLogic.copyCMsgToSMsg(bizMsg, glblMsg);

        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(bizMsg.A);
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt());
        NYEL8840CommonLogic.copySMsgToCMsg(bizMsg, glblMsg);
    }

    private void doProcess_NYEL8840Scrn00_PagePrev(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {

        NYEL8840CommonLogic.copyCMsgToSMsg(bizMsg, glblMsg);

        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(bizMsg.A);
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length() - 1);
        NYEL8840CommonLogic.copySMsgToCMsg(bizMsg, glblMsg);
    }

    private void doProcess_NYEL8840Scrn00_Add(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.xxWfAsgCd_F)) {
            bizMsg.xxWfAsgCd_F.setErrorInfo(1, ZZM9000E, new String[] {"Assigner" });
            return;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.xxWfAsgCd_FS)) {
            if (!bizMsg.xxWfAsgCd_F.getValue().equals(bizMsg.xxWfAsgCd_FS.getValue())) {
                bizMsg.xxWfAsgCd_F.setErrorInfo(1, NYEM0020E, new String[] {bizMsg.xxWfAsgCd_FS.getValue() });
                return;
            }
        }

        String assignFromNm = NYEL8840CommonLogic.getAssignerNm(bizMsg, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgNm_F, assignFromNm);
        if (!ZYPCommonFunc.hasValue(assignFromNm)) {
            return;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.xxWfAsgCd)) {
            bizMsg.xxWfAsgCd.setErrorInfo(1, ZZM9000E, new String[] {"Assignee" });
            return;
        }
        NYEL8840CommonLogic.getAssigneeNm(bizMsg);
        if (bizMsg.xxWfAsgCd.getErrorCode() != 0) {
            return;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.effFromDt)) {
            bizMsg.effFromDt.setErrorInfo(1, ZZM9000E, new String[] {"Period(From)" });
            return;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.effFromHourMn_H)) {
            bizMsg.effFromHourMn_H.setErrorInfo(1, ZZM9000E, new String[] {"Period(From Hour)" });
            return;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.effFromHourMn_M)) {
            bizMsg.effFromHourMn_M.setErrorInfo(1, ZZM9000E, new String[] {"Period(From Minite)" });
            return;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.effThruDt)) {
            bizMsg.effThruDt.setErrorInfo(1, ZZM9000E, new String[] {"Period(To)" });
            return;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.effThruHourMn_H)) {
            bizMsg.effThruHourMn_H.setErrorInfo(1, ZZM9000E, new String[] {"Period(To Hour)" });
            return;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.effThruHourMn_M)) {
            bizMsg.effThruHourMn_M.setErrorInfo(1, ZZM9000E, new String[] {"Period(To Minute)" });
            return;
        }

        if (!NYEL8840CommonLogic.isValidPeriod(bizMsg, glblMsg)) {
            return;
        }

        String asgCd = bizMsg.xxWfAsgCd.getValue();
        String asgFromCd = bizMsg.xxWfAsgCd_F.getValue();

        if (asgFromCd.equals(asgCd)) {
            bizMsg.xxWfAsgCd.setErrorInfo(1, NYEM0009E, new String[] {asgCd});
            return;
        }

        int addRowIdx = glblMsg.A.getValidCount();
        glblMsg.A.setValidCount(addRowIdx + 1);

        String AsigneeNm = NYEL8840CommonLogic.getAssigneeNm(bizMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgNm, AsigneeNm);

        NYEL8840_ASMsg bizLineMsg = glblMsg.A.no(addRowIdx);
        ZYPEZDItemValueSetter.setValue(bizLineMsg.xxWfAsgCd_A2, bizMsg.xxWfAsgCd_F);
        ZYPEZDItemValueSetter.setValue(//
                bizLineMsg.xxWfAsgNm_A2 //
                , assignFromNm);
        ZYPEZDItemValueSetter.setValue(bizLineMsg.xxWfAsgCd_A1, bizMsg.xxWfAsgCd);
        ZYPEZDItemValueSetter.setValue(//
                bizLineMsg.xxWfAsgNm_A1 //
                , AsigneeNm);
        ZYPEZDItemValueSetter.setValue(bizLineMsg.wfBizAppId_A1, bizMsg.wfBizAppId);
        ZYPEZDItemValueSetter.setValue(bizLineMsg.wfDescTxt_AP, procIdToNm(bizMsg));
        ZYPEZDItemValueSetter.setValue(bizLineMsg.wfDescTxt_A1, bizMsg.wfDescTxt);

        ZYPEZDItemValueSetter.setValue(bizLineMsg.effFromDt_A1, bizMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(bizLineMsg.effFromHourMn_AH, bizMsg.effFromHourMn_H);
        ZYPEZDItemValueSetter.setValue(bizLineMsg.effFromHourMn_AM, bizMsg.effFromHourMn_M);

        ZYPEZDItemValueSetter.setValue(bizLineMsg.wfDescTxt_F1, 
                ZYPDateUtil.DateFormatter(bizLineMsg.effFromDt_A1.getValue(), "yyyyMMdd", "MM/dd/yyyy")
                + " " 
                + bizMsg.effFromHourMn_H.getValue() 
                + HHMM_SEPARATOR 
                + bizMsg.effFromHourMn_M.getValue());

        ZYPEZDItemValueSetter.setValue(bizLineMsg.effThruDt_A1, bizMsg.effThruDt);
        ZYPEZDItemValueSetter.setValue(bizLineMsg.effThruHourMn_AH, bizMsg.effThruHourMn_H);
        ZYPEZDItemValueSetter.setValue(bizLineMsg.effThruHourMn_AM, bizMsg.effThruHourMn_M);
        ZYPEZDItemValueSetter.setValue(bizLineMsg.wfDescTxt_T1, 
                ZYPDateUtil.DateFormatter(bizLineMsg.effThruDt_A1.getValue(), "yyyyMMdd", "MM/dd/yyyy")
                + " " 
                + bizMsg.effThruHourMn_H.getValue() 
                + HHMM_SEPARATOR 
                + bizMsg.effThruHourMn_M.getValue());

        //
        bizMsg.xxWfAsgCd.clear();
        bizMsg.xxWfAsgNm.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAppId, bizMsg.wfBizAppId_S.getValue());
        bizMsg.effFromDt.clear();
        bizMsg.effThruDt.clear();
        bizMsg.wfDescTxt.clear();
        bizMsg.effFromHourMn_H.clear();
        bizMsg.effFromHourMn_M.clear();
        bizMsg.effThruHourMn_H.clear();
        bizMsg.effThruHourMn_M.clear();

        int lastPage = addRowIdx / bizMsg.A.length();
        ZYPTableUtil.clear(bizMsg.A);
        bizMsg.xxPageShowFromNum.setValue(lastPage * bizMsg.A.length());
        NYEL8840CommonLogic.copySMsgToCMsg(bizMsg, glblMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgCd_FS, bizMsg.xxWfAsgCd_F);
    }

    private void doProcess_NYEL8840Scrn00_Delete(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {
        NYEL8840CommonLogic.copyCMsgToSMsg(bizMsg, glblMsg);
        NYEL8840CommonLogic.deleteRowSMsg(bizMsg, glblMsg);

        int lastPage = 0;
        if (glblMsg.A.getValidCount() > 0) {
            lastPage = (glblMsg.A.getValidCount() - 1) / bizMsg.A.length();
        }
        bizMsg.xxPageShowFromNum.setValue(lastPage * bizMsg.A.length());
        NYEL8840CommonLogic.copySMsgToCMsg(bizMsg, glblMsg);
    }

    private String procIdToNm(NYEL8840CMsg bizMsg) {
        String ret = "";

        if (!ZYPCommonFunc.hasValue(bizMsg.wfBizAppId)) {
            return ret;
        }

        String procId = bizMsg.wfBizAppId.getValue();

        int max = bizMsg.wfBizAppId_L.length();

        for (int cnt = 0; cnt < max; cnt++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.wfBizAppId_L.no(cnt))) {
                continue;
            }

            if (procId.equals(bizMsg.wfBizAppId_L.no(cnt).getValue())) {
                ret = bizMsg.wfDescTxt_L.no(cnt).getValue();
                break;
            }
        }
        return ret;
    }
// ADD Start 2018/09/26
}
