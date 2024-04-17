/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0040;

import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.SIGNAL_APPROVE;
import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.SIGNAL_REJECT;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import business.blap.NSBL0040.constant.NSBL0040Constant;
import business.db.FSRTMsg;
import business.db.SVC_TASKTMsg;
import business.parts.NSZC038001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC038001.NSZC038001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.impl.S21NwfTokenImpl;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.nwf.util.process.S21NwfUtilProcessFactory;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * Credit Approval
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/01   SRA             Otsuji          Create          N/A
 * 2015/12/14   Hitachi         K.Yamada        Update          CSA QC#895
 * 2018/06/25   CITS            M.Naito         Update          QC#26634
 *</pre>
 */
public class NSBL0040BL06 extends S21BusinessHandler implements NSBL0040Constant {

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {

        super.preDoProcess(ezdCMsg, ezdSMsg);

        try {
            NSBL0040CMsg cMsg = (NSBL0040CMsg) ezdCMsg;
            NSBL0040SMsg sMsg = (NSBL0040SMsg) ezdSMsg;
            String scrnAplId = cMsg.getScreenAplID();
            if ("NSBL0040Scrn00_CMN_Approve".equals(scrnAplId)) {
                doProcess_NSBL0040Scrn00_CMN_Approve(cMsg, sMsg);
            } else if ("NSBL0040Scrn00_CMN_Reject".equals(scrnAplId)) {
                doProcess_NSBL0040Scrn00_CMN_Reject(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + scrnAplId);
            }
        } finally {
            super.postDoProcess(ezdCMsg, ezdSMsg);
        }
    }

    private void doProcess_NSBL0040Scrn00_CMN_Approve(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg) {

        doUpdateProcess(cMsg, sMsg, MODE_APPROVE);
        cMsg.setMessageInfo(NZZM0002I, new String[] {"Approve" });
    }

    // add start 2015/12/14 CSA Defect#895
    private void doProcess_NSBL0040Scrn00_CMN_Reject(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg) {

        doUpdateProcess(cMsg, sMsg, MODE_REJECT);
        // START 2018/06/25 M.Naito [QC#26634, MOD]
//        cMsg.setMessageInfo(NZZM0002I, new String[] {"Approve" });
        cMsg.setMessageInfo(NZZM0002I, new String[] {"Reject" });
        // END 2018/06/25 M.Naito [QC#26634, MOD]
    }
    // add end 2015/12/14 CSA Defect#895

    private void doUpdateProcess(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg, String mode) {

        List<Integer> selectedIndex = ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        if (selectedIndex == null || selectedIndex.isEmpty()) {
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                cMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NSBM0015E);
            }
            cMsg.setMessageInfo(NSBM0015E);
            return;
        }

        NSBL0040Query query = NSBL0040Query.getInstance();
        //MOD START 11/19/2015 for CSA
        NSZC038001 api = new NSZC038001();
        //NSZC008001 api = new NSZC008001();
        //MOD END 11/19/2015 for CSA
        
        String apvlDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode(), "NSBL0040");
        String apvlTm = ZYPDateUtil.getCurrentSystemTime("HHmmss");

        for (Integer index : selectedIndex) {
            int i = index.intValue();
            try {
                // mod start 2015/12/14 CSA Defect#895
                FSRTMsg tMsg = query.getFsrForUpdate(getGlobalCompanyCode(), cMsg.A.no(i).fsrNum_A1.getValue());
                if (tMsg == null) {
                    cMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NZZM0003E);
                // mod end 2015/12/14 CSA Defect#895
                } else if (!ZYPDateUtil.isSameTimeStamp(cMsg.A.no(i).ezUpTime_A1.getValue(), cMsg.A.no(i).ezUpTimeZone_A1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    cMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NZZM0003E);
                } else {
                    //MOD START 11/19/2015 for CSA
                    //NSZC008001PMsg pMsg = new NSZC008001PMsg();
                    //ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NSZC008001.APPROVAL);
                    //ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
                    //ZYPEZDItemValueSetter.setValue(pMsg.svcTaskNum, cMsg.A.no(i).svcTaskNum_A1);
                    //ZYPEZDItemValueSetter.setValue(pMsg.svcTaskApvlByUsrId, getContextUserInfo().getUserId());
                    //ZYPEZDItemValueSetter.setValue(pMsg.svcTaskApvlDt, apvlDt);
                    //ZYPEZDItemValueSetter.setValue(pMsg.svcTaskApvlTm, apvlTm);

                    // START 2018/06/25 M.Naito [QC#26634, MOD]
                    boolean activeWorkItemFlg = true;
                    S21NwfUtilContextFactory factory = new S21NwfUtilContextFactory();
                    S21NwfContext context = factory.getContex();
                    context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
                    List<S21NwfProcess> process = context.getProcess(PROCESS_DEF_NSWP0004, tMsg.fsrNum.getValue());

                    if ((process == null) || (process.size() <= 0)) {
                        activeWorkItemFlg = false;
                    } else {
                        S21NwfTokenImpl token = (S21NwfTokenImpl) (process.get(0).getToken());
                        if (token.getActiveWorkItem() == null) {
                            activeWorkItemFlg = false;
                        } else {
                            if (MODE_APPROVE.equals(mode) && token.getActiveWorkItem().isApprovable()) {
                                token.signal(SIGNAL_APPROVE);
                            } else if (MODE_REJECT.equals(mode) && token.getActiveWorkItem().isRejectable()) {
                                token.signal(SIGNAL_REJECT);
                            } else {
                                cMsg.setMessageInfo(NSAM0437E);
                                return;
                            }
                        }
                    }
                    if (!activeWorkItemFlg) {
                        NSZC038001PMsg pMsg = new NSZC038001PMsg();
                        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
                        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, apvlDt);
                        ZYPEZDItemValueSetter.setValue(pMsg.usrId, cMsg.getUserID());
                        ZYPEZDItemValueSetter.setValue(pMsg.fsrNum, cMsg.A.no(i).fsrNum_A1);
                        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, mode);
                        ZYPEZDItemValueSetter.setValue(pMsg.svcTaskApvlDt, apvlDt);
                        ZYPEZDItemValueSetter.setValue(pMsg.svcTaskApvlTm, apvlTm);
                        // MOD END 11/19/2015 for CSA
                        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
                        List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
                        if (xxMsgIdList.size() > 0) {
                            // mod start 2015/12/14 CSA Defect#895
                            // for (String xxMsgId : xxMsgIdList) {
                        //    cMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, xxMsgId);
                            // }
                            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                            S21ApiMessage msg = msgList.get(0);
                            if (msg.getXxMsgid().endsWith("E")) {
                                cMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, msg.getXxMsgid(), msg.getXxMsgPrmArray());
                            } else {
                                cMsg.A.no(i).xxChkBox_A1.setErrorInfo(2, msg.getXxMsgid(), msg.getXxMsgPrmArray());
                            }
                            // mod end 2015/12/14 CSA Defect#895
                        }
                    }
                }
                // END 2018/06/25 M.Naito [QC#26634, MOD]
            } catch (EZDDBRecordLockedException e) {
                cMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NZZM0003E);
            // START 2018/06/25 M.Naito [QC#26634, MOD]
            } catch (S21NwfException e) {
                EZDMessageInfo info = e.getMessageInfo();
                if (info != null) {
                    cMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
                } else {
                    cMsg.setMessageInfo(NSAM0436E, new String[] {PROCESS_DEF_NSWP0004, cMsg.A.no(i).fsrNum_A1.getValue() });
                }
            // END 2018/06/25 M.Naito [QC#26634, MOD]
            }
        }
    }

}
