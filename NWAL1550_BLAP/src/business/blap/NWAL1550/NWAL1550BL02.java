/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1550;

import static business.blap.NWAL1550.constant.NWAL1550Constant.BIZ_ID;
import static business.blap.NWAL1550.constant.NWAL1550Constant.NWAM0142E;
import static business.blap.NWAL1550.constant.NWAL1550Constant.NWAM0189E;
import static business.blap.NWAL1550.constant.NWAL1550Constant.NWAM0242E;
import static business.blap.NWAL1550.constant.NWAL1550Constant.OPEN_FLG_OFF;
import static business.blap.NWAL1550.constant.NWAL1550Constant.ZZZM9003I;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1550.common.NWAL1550CommonLogic;
import business.parts.NWZC155001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC155001.NWZC155001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NWAL1550BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         S.Ohki          Create          N/A
 * 2017/02/27   Fujitsu         T.Yoshida       Update          S21_NA#16035
 * 2022/10/07   CITS            F.Fadriquela    Update          QC#60623
 *</pre>
 */
public class NWAL1550BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1550CMsg bizMsg = (NWAL1550CMsg) cMsg;

            if ("NWAL1550_INIT".equals(screenAplID)) {
                doProcess_NWAL1550_INIT(bizMsg);

            } else if ("NWAL1550Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWAL1550Scrn00_CMN_Reset(bizMsg);

            } else if ("NWAL1550Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWAL1550Scrn00_CMN_Submit(bizMsg);

            } else if ("NWAL1550Scrn00_ExecDIChk".equals(screenAplID)) {
                doProcess_NWAL1550Scrn00_ExecDIChk(bizMsg);

            } else if ("NWAL1550Scrn00_RefreshDIChkRslt".equals(screenAplID)) {
                doProcess_NWAL1550Scrn00_RefreshDIChkRslt(bizMsg);

            } else if ("NWAL1550Scrn00_Search_Order".equals(screenAplID)) {
                doProcess_NWAL1550Scrn00_Search_Order(bizMsg);

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
     */
    private void doProcess_NWAL1550_INIT(NWAL1550CMsg bizMsg) {
        NWAL1550CommonLogic.getOrderInfo(bizMsg);
        NWAL1550CommonLogic.setAuthority(bizMsg, getUserProfileService()); // S21_NA#16035 Add
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1550Scrn00_CMN_Reset(NWAL1550CMsg bizMsg) {
        NWAL1550CommonLogic.getOrderInfo(bizMsg);
        NWAL1550CommonLogic.setAuthority(bizMsg, getUserProfileService()); // S21_NA#16035 Add
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1550Scrn00_CMN_Submit(NWAL1550CMsg bizMsg) {
        NWAL1550CommonLogic.getOrderInfo(bizMsg);
    }

    /**
     * ExecDIChk Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1550Scrn00_ExecDIChk(NWAL1550CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1550Query.getInstance().getCpoDtlV(bizMsg, OPEN_FLG_OFF);

        if (!ssmResult.isCodeNormal()) {
            bizMsg.setMessageInfo(NWAM0142E);
            return;
        }

        // EXECUTE NWZC1550(DI Check API)
        String cpoOrdTs = (String) ssmResult.getResultObject();

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        NWZC155001PMsg pMsg = new NWZC155001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, bizMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.diChkSubmtPsnCd, userProfSvc.getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(pMsg.diJobId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.ordDt, cpoOrdTs);

        NWZC155001 api = new NWZC155001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            List<String> errList = S21ApiUtil.getXxMsgIdList(pMsg);

            if (!errList.isEmpty()) {
                for (String xxMsgId : errList) {
                    // START 2022/10/07 F.Fadriquela [QC#60623, ADD]
                    if (xxMsgId.equals(NWAM0242E)) {
                        bizMsg.setMessageInfo(NWAM0242E);
                        return;
                    }
                    // END 2022/10/07 F.Fadriquela [QC#60623, ADD]
                    if (xxMsgId.endsWith("E")) {
                        bizMsg.setMessageInfo(NWAM0189E, new String[] {xxMsgId });
                        return;
                    }
                }
            }
            return;
        }

        // Get Order Information
        NWAL1550CommonLogic.getOrderInfo(bizMsg);

        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {
            return;
        }

        bizMsg.setMessageInfo(ZZZM9003I, new String[] {"DI Check" });
    }

    /**
     * RefreshDIChkRslt Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1550Scrn00_RefreshDIChkRslt(NWAL1550CMsg bizMsg) {
        NWAL1550CommonLogic.getCheckResult(bizMsg);
    }

    /**
     * Search_Order Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1550Scrn00_Search_Order(NWAL1550CMsg bizMsg) {

        NWAL1550CommonLogic.getOrderInfo(bizMsg);
        NWAL1550CommonLogic.setAuthority(bizMsg, getUserProfileService()); // S21_NA#16035 Add
    }
}
