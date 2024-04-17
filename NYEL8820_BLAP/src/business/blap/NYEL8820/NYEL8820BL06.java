/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8820;

import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0007E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0008E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ZZM8100I;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ZZM9000E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0001E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0018E;
import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.SIGNAL_APPROVE;
import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.SIGNAL_REJECT;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDSMsg;

import business.blap.NYEL8820.common.NYEL8820CommonLogic;

import com.canon.cusa.s21.common.NYX.NYXC880001.NYXC880001;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenObj;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.nwf.util.process.S21NwfUtilProcessFactory;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 *<pre>
 * NYEL8820BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/17   Fujitsu         Q09079          Create          N/A
 * 2018/04/24   Fujitsu         Q10814          Update          QC#23516
 * 2018/11/19   Fujitsu         Q10627          Update          QC#29337
 * 2019/03/26   Fujitsu         Q10627          Update          QC#30827
 *</pre>
 */
public class NYEL8820BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NYEL8820CMsg bizMsg = (NYEL8820CMsg) cMsg;
            NYEL8820SMsg glblMsg = (NYEL8820SMsg) sMsg;

            if ("NYEL8820Scrn00_CMN_Approve".equals(screenAplID)) {
                doProcess_NYEL8820Scrn00_CMN_Approve(bizMsg, glblMsg);

            } else if ("NYEL8820Scrn00_CMN_Reject".equals(screenAplID)) {
                doProcess_NYEL8820Scrn00_CMN_Reject(bizMsg, glblMsg);

            // QC#23516 ADD START 2018/04/24
            } else if ("NYEL8820Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NYEL8820Scrn00_CMN_Save(bizMsg, glblMsg);

            // QC#23516 ADD END 2018/04/24
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Approve Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NYEL8820Scrn00_CMN_Approve(NYEL8820CMsg bizMsg, NYEL8820SMsg glblMsg) {
        // Initialize NWF
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfProcess process;
        int index = bizMsg.xxCellIdx.getValue().intValue();

        try {
            S21NwfContext context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            process = context.getProcess(bizMsg.P.no(index).wfProcPk_P.getValue());
        } catch (NumberFormatException e1) {
            bizMsg.setMessageInfo(NYEM0007E, new String[] {bizMsg.P.no(index).wfProcPk_P.getValue().toPlainString() });
            return;
        } catch (S21NwfSystemException e1) {
            EZDMessageInfo info = e1.getMessageInfo();

            if (info != null) {
                bizMsg.setMessageInfo(e1.getMessageInfo().getCode(), e1.getMessageInfo().getParameter());
            } else {
                bizMsg.setMessageInfo(NYEM0007E, new String[] {bizMsg.P.no(index).wfProcPk_P.getValue().toPlainString() });
            }
            return;
        }

        if (process == null) {
            bizMsg.setMessageInfo(NYEM0007E, new String[] {bizMsg.P.no(index).wfProcPk_P.getValue().toPlainString() });
            return;
        }
        S21NwfToken token = process.getToken();
        try {

            String comment = bizMsg.wfCmntTxt.getValue();

            if (comment != null) {
                S21NwfTokenObj tokenObj = token.getTokenObj();

                if (tokenObj == null) {
                    tokenObj = new S21NwfTokenObj();
                    token.setTokenObj(tokenObj);
                }
                tokenObj.setComment(comment);
            }

            token.signal(SIGNAL_APPROVE);

            bizMsg.P.no(index).wfProcStsCd_P.setValue(process.getStatus().getCode());
            bizMsg.wfProcStsCd.setValue(NYXC880001.toWorklistProcStatusCd(process.getStatus().getCode()));

// 2018/11/19 UPD START QC#29337
//            NYEL8820CommonLogic.next(bizMsg);
//            NYEL8820CommonLogic.search(bizMsg, glblMsg, getGlobalCompanyCode(), this.getContextUserInfo().getUserId());
            if (NYEL8820CommonLogic.next(bizMsg)) {
                NYEL8820CommonLogic.search(bizMsg, glblMsg, getGlobalCompanyCode(), this.getContextUserInfo().getUserId());
            }
            else {
                NYEL8820CommonLogic.search(bizMsg, glblMsg, getGlobalCompanyCode(), this.getContextUserInfo().getUserId(), process);
            }
// 2018/11/19 UPD END   QC#29337
        } catch (S21NwfException e) {
            if (e.getMessageInfo() != null) {
                if ((e.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_WARNING) || (e.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_INFORMATION)) {
                    bizMsg.setMessageInfo(NYEM0001E, new String[] {e.getMessageInfo().getCode(), NYXC880001.parameterJoin(e.getMessageInfo().getParameter())});
                } else {
                    bizMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
                }
            } else {
                bizMsg.setMessageInfo(NYEM0007E, new String[] {bizMsg.P.no(index).wfProcPk_P.getValue().toPlainString()});
            }
            return;
        }

        List<EZDMessageInfo> list = token.getLastBizMessage();

        if ((list != null) && (list.size() > 0)){
            bizMsg.setMessageInfo(list.get(0).getCode(), list.get(0).getParameter());
        } 

        if (S21StringUtil.isEmpty(bizMsg.getMessageCode())){
            bizMsg.setMessageInfo(ZZM8100I);
        }
    }

    /**
     * CMN_Reject Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NYEL8820Scrn00_CMN_Reject(NYEL8820CMsg bizMsg, NYEL8820SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.wfCmntTxt)) {
            bizMsg.wfCmntTxt.setErrorInfo(1, ZZM9000E, new String[] {"Comment" });
            return;
        }
        // Initialize NWF
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfProcess process;
        int index = bizMsg.xxCellIdx.getValue().intValue();

        try {
            S21NwfContext context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            process = context.getProcess(BigDecimal.valueOf(Long.parseLong(bizMsg.P.no(index).wfProcPk_P.getValue().toPlainString())));
        } catch (NumberFormatException e1) {
            bizMsg.setMessageInfo(NYEM0008E, new String[] {bizMsg.P.no(index).wfProcPk_P.getValue().toPlainString() });
            return;
        } catch (S21NwfSystemException e1) {
            EZDMessageInfo info = e1.getMessageInfo();

            if (info != null) {
                bizMsg.setMessageInfo(e1.getMessageInfo().getCode(), e1.getMessageInfo().getParameter());
            } else {
                bizMsg.setMessageInfo(NYEM0008E, new String[] {bizMsg.P.no(index).wfProcPk_P.getValue().toPlainString() });
            }
            return;
        }

        S21NwfToken token = process.getToken();

        if (process == null) {
            bizMsg.setMessageInfo(NYEM0008E, new String[] {bizMsg.P.no(index).wfProcPk_P.getValue().toPlainString() });
            return;
        }

        try {
            String comment = bizMsg.wfCmntTxt.getValue();

            if (comment != null) {
                S21NwfTokenObj tokenObj = token.getTokenObj();

                if (tokenObj == null) {
                    tokenObj = new S21NwfTokenObj();
                    token.setTokenObj(tokenObj);
                }
                tokenObj.setComment(comment);
            }

// 2019/03/26 UPD START QC#30827
//            token.signal(SIGNAL_REJECT);
            boolean ignoreAuth = false;
            token.signal(SIGNAL_REJECT, ignoreAuth);
// 2019/03/26 UPD END   QC#30827

            bizMsg.P.no(index).wfProcStsCd_P.setValue(process.getStatus().getCode());
            bizMsg.wfProcStsCd.setValue(NYXC880001.toWorklistProcStatusCd(process.getStatus().getCode()));
// 2018/11/19 UPD START QC#29337
//            NYEL8820CommonLogic.next(bizMsg);
//            NYEL8820CommonLogic.search(bizMsg, glblMsg, getGlobalCompanyCode(), this.getContextUserInfo().getUserId());
            if (NYEL8820CommonLogic.next(bizMsg)) {
                NYEL8820CommonLogic.search(bizMsg, glblMsg, getGlobalCompanyCode(), this.getContextUserInfo().getUserId());
            }
            else {
                NYEL8820CommonLogic.search(bizMsg, glblMsg, getGlobalCompanyCode(), this.getContextUserInfo().getUserId(), process);
            }
// 2018/11/19 UPD END   QC#29337
          } catch (S21NwfException e) {
            if (e.getMessageInfo() != null) {
                if ((e.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_WARNING) || (e.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_INFORMATION)) {
                    bizMsg.setMessageInfo(NYEM0001E, new String[] {e.getMessageInfo().getCode(), NYXC880001.parameterJoin(e.getMessageInfo().getParameter())});
                } else {
                    bizMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
                }
            } else {
                bizMsg.setMessageInfo(NYEM0008E, new String[] {bizMsg.P.no(index).wfProcPk_P.getValue().toPlainString()});
            }
            return;
        }

        List<EZDMessageInfo> list = token.getLastBizMessage();

        if ((list != null) && (list.size() > 0)){
            bizMsg.setMessageInfo(list.get(0).getCode(), list.get(0).getParameter());
        } 

        if (S21StringUtil.isEmpty(bizMsg.getMessageCode())){
            bizMsg.setMessageInfo(ZZM8100I);
        }
    }

// QC#23516 ADD START 2018/04/24
    /**
     * CMN_Save Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NYEL8820Scrn00_CMN_Save(NYEL8820CMsg bizMsg, NYEL8820SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.wfCmntTxt)) {
            bizMsg.wfCmntTxt.setErrorInfo(1, ZZM9000E, new String[] {"Comment" });
            return;
        }
        // Initialize NWF
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfProcess process;
        int index = bizMsg.xxCellIdx.getValue().intValue();

        try {
            S21NwfContext context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            process = context.getProcess(BigDecimal.valueOf(Long.parseLong(bizMsg.P.no(index).wfProcPk_P.getValue().toPlainString())));
        } catch (NumberFormatException e1) {
            bizMsg.setMessageInfo(NYEM0018E, new String[] {bizMsg.P.no(index).wfProcPk_P.getValue().toPlainString() });
            return;
        } catch (S21NwfSystemException e1) {
            EZDMessageInfo info = e1.getMessageInfo();

            if (info != null) {
                bizMsg.setMessageInfo(e1.getMessageInfo().getCode(), e1.getMessageInfo().getParameter());
            } else {
                bizMsg.setMessageInfo(NYEM0018E, new String[] {bizMsg.P.no(index).wfProcPk_P.getValue().toPlainString() });
            }
            return;
        }

        S21NwfToken token = process.getToken();

        if (process == null) {
            bizMsg.setMessageInfo(NYEM0018E, new String[] {bizMsg.P.no(index).wfProcPk_P.getValue().toPlainString() });
            return;
        }

        try {
            String comment = bizMsg.wfCmntTxt.getValue();

            token.commentActiveWorkItem(comment);

            bizMsg.wfCmntTxt.clear();
// 2018/11/19 UPD START QC#29337
//            NYEL8820CommonLogic.search(bizMsg, glblMsg, getGlobalCompanyCode(), this.getContextUserInfo().getUserId());
            NYEL8820CommonLogic.search(bizMsg, glblMsg, getGlobalCompanyCode(), this.getContextUserInfo().getUserId(), process);
// 2018/11/19 UPD END   QC#29337
        } catch (S21NwfException e) {
            EZDMessageInfo info = e.getMessageInfo();

            if (info != null) {
                bizMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
            } else {
                bizMsg.setMessageInfo(NYEM0018E, new String[] {bizMsg.P.no(index).wfProcPk_P.getValue().toPlainString() });
            }
            return;
        }

        if (S21StringUtil.isEmpty(bizMsg.getMessageCode())){
            bizMsg.setMessageInfo(ZZM8100I);
        }
    }
// QC#23516 ADD END 2018/04/24
}
