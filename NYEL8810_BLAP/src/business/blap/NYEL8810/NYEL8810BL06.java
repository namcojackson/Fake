/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8810;

import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.SIGNAL_APPROVE;
import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.SIGNAL_REJECT;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0007E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0008E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0006I;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ZZM8100I;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ZZM9000E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0009E;
//import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ZZSM4110E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ZZZM9006E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0010E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0001E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0013E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0014E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0015E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0016E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0018E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0019E;
import static business.blap.NYEL8810.constant.NYEL8810Constant.SCRN_ID_00;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NYEL8810.common.NYEL8810CommonLogic;

import com.canon.cusa.s21.common.NYX.NYXC880001.NYXC880001;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002Query;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.common.util.S21StopWatch;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.model.impl.S21NwfWorkItemImpl;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenObj;
import com.canon.cusa.s21.framework.nwf.core.user.S21NwfUserRole.USER_ROLE_TYPE;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.nwf.util.process.S21NwfUtilProcessFactory;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NYEL8810BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/01   Fujitsu         M.Yamada        Create          N/A
 * 2018/05/07   Fujitsu         Q10814          Update          QC#23516
 * 2019/01/15   Fujitsu         Q10627          Update          QC#29683
 * 2019/03/26   Fujitsu         Q10627          Update          QC#30827
 *</pre>
 */
public class NYEL8810BL06 extends S21BusinessHandler {

    private static final S21Logger logger = S21LoggerFactory.getLogger(NYEL8810BL06.class);
    
    private static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
    
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NYEL8810CMsg bizMsg = (NYEL8810CMsg) cMsg;
            S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]Start %s", screenAplID));
            S21StopWatch sw = new S21StopWatch();
            sw.start();

            if ("NYEL8810Scrn00_CMN_Submit".equals(screenAplID)) {
                if (NYEL8810CommonLogic.isValidParameter((NYEL8810CMsg) cMsg, this.getContextUserInfo().getUserId())){
                    doProcess_NYEL8810Scrn00_CMN_Submit(bizMsg, (NYEL8810SMsg)sMsg);
                }

            } else if ("NYEL8810Scrn00_CMN_Approve".equals(screenAplID)) {
                if (NYEL8810CommonLogic.isValidParameter((NYEL8810CMsg) cMsg, this.getContextUserInfo().getUserId())){
                    doProcess_NYEL8810Scrn00_Approve(bizMsg, (NYEL8810SMsg)sMsg);
                }

            } else if ("NYEL8810Scrn00_CMN_Reject".equals(screenAplID)) {
                if (NYEL8810CommonLogic.isValidParameter((NYEL8810CMsg) cMsg, this.getContextUserInfo().getUserId())){
                    doProcess_NYEL8810Scrn00_Reject(bizMsg, (NYEL8810SMsg)sMsg);
                }

            } else if ("NYEL8810Scrn00_ReAssign".equals(screenAplID)) {
                if (NYEL8810CommonLogic.isValidParameter((NYEL8810CMsg) cMsg, this.getContextUserInfo().getUserId())){
                    doProcess_NYEL8810Scrn00_ReAssign(bizMsg, (NYEL8810SMsg)sMsg);
                }

            } else if ("NYEL8810Scrn00_CMN_Cancel".equals(screenAplID)) {
                if (NYEL8810CommonLogic.isValidParameter((NYEL8810CMsg) cMsg, this.getContextUserInfo().getUserId())){
                    doProcess_NYEL8810Scrn00_Cancel(bizMsg, (NYEL8810SMsg)sMsg);
                }

            } else if ("NYEL8810Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NYEL8810CMsg) cMsg, (NYEL8810SMsg) sMsg);

            } else if ("NYEL8810Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NYEL8810CMsg) cMsg, (NYEL8810SMsg) sMsg);

            } else if ("NYEL8810Scrn00_SaveSearch".equals(screenAplID)) {
                if (NYEL8810CommonLogic.isValidParameter((NYEL8810CMsg) cMsg, this.getContextUserInfo().getUserId())){
                    doProcess_NYEL8810Scrn00_SaveSearch(bizMsg, (NYEL8810SMsg)sMsg);
                }
                
            } else if ("NYEL8810Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcessNYEL8810Scrn00_DeleteSearch(bizMsg, (NYEL8810SMsg)sMsg);

            // QC#23516 ADD START 2018/05/07
            } else if ("NYEL8810Scrn00_CMN_Save".equals(screenAplID)) {
                if (NYEL8810CommonLogic.isValidParameter((NYEL8810CMsg) cMsg, this.getContextUserInfo().getUserId())){
                    doProcess_NYEL8810Scrn00_CMN_Save(bizMsg, (NYEL8810SMsg)sMsg);
                }

            // QC#23516 ADD END 2018/05/07
            }
            sw.stop();
            S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]End %s [%d]", screenAplID, sw.getElapsedMilliSec()));
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NYEL8810Scrn00_CMN_Submit(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxWfAprChkFlg_A", ZYPConstant.CHKBOX_ON_Y);

        if (selectedRows.isEmpty()) {
            int max = bizMsg.A.getValidCount();
            for (int i = 0; i < max; i++) {
                bizMsg.A.no(i).xxWfAprChkFlg_A.setErrorInfo(1, NYEM0010E);
            }
            return;
        }

        // Check Auth
        boolean errFlg = false;
        for (int cnt : selectedRows) {
            if (ZYPConstant.FLG_OFF_N.equals(bizMsg.A.no(cnt).xxWfFyiChkVisFlg_A.getValue())) {
                bizMsg.A.no(cnt).xxWfAprChkFlg_A.setErrorInfo(1, NYEM0013E, new String[]{"Submit authority"});
                errFlg = true;
            }
        }

        if (errFlg){
            return;
        }
        
        
        for (int cnt : selectedRows) {

            boolean ret = fyi(bizMsg, bizMsg.A.no(cnt).wfProcPk_A.getValue(), bizMsg.A.no(cnt).wfWrkItemPk_A.getValue());

            if (ret == false) {
                break;
            }
        }

        // 2018/02/06 ADD START
        ZYPTableUtil.clear(bizMsg.A);
        // 2018/02/06 ADD END
        NYEL8810CommonLogic.search(bizMsg, sMsg, this.getGlobalCompanyCode(), this.getContextUserInfo().getUserId());
    }

    private void doProcess_NYEL8810Scrn00_Approve(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxWfAprChkFlg_A", ZYPConstant.CHKBOX_ON_Y);

        if (selectedRows.isEmpty()) {
            int max = bizMsg.A.getValidCount();
            for (int i = 0; i < max; i++) {
                bizMsg.A.no(i).xxWfAprChkFlg_A.setErrorInfo(1, NYEM0010E);
            }
            return;
        }

        // Check Auth
        boolean errFlg = false;
        for (int cnt : selectedRows) {
            if (ZYPConstant.FLG_OFF_N.equals(bizMsg.A.no(cnt).xxWfAprChkVisFlg_A.getValue())) {
                bizMsg.A.no(cnt).xxWfAprChkFlg_A.setErrorInfo(1, NYEM0013E, new String[]{"Approval authority"});
                errFlg = true;
            }
        }

        if (errFlg){
            return;
        }

        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_Approve]Start Approve"));
        S21StopWatch sw = new S21StopWatch();
        sw.start();

        for (int cnt : selectedRows) {
            boolean ret = approve(bizMsg, bizMsg.A.no(cnt).wfProcPk_A.getValue(), bizMsg.A.no(cnt).wfWrkItemPk_A.getValue());
            
            if (ret == false) {
                return;
            }
        }

        sw.stop();
        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_Approve]End Approve [%d]", sw.getElapsedMilliSec()));

        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_Approve]Start Search"));
        sw = new S21StopWatch();
        sw.start();

        // 2018/02/06 ADD START
        ZYPTableUtil.clear(bizMsg.A);
        // 2018/02/06 ADD END
        NYEL8810CommonLogic.search(bizMsg, sMsg, this.getGlobalCompanyCode(), this.getContextUserInfo().getUserId());
        bizMsg.wfCmntTxt.clear();

        sw.stop();
        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_Approve]End Search [%d]", sw.getElapsedMilliSec()));

    }

    private void doProcess_NYEL8810Scrn00_Reject(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxWfAprChkFlg_A", ZYPConstant.CHKBOX_ON_Y);

        if (selectedRows.isEmpty()) {
            int max = bizMsg.A.getValidCount();
            for (int i = 0; i < max; i++) {
                bizMsg.A.no(i).xxWfAprChkFlg_A.setErrorInfo(1, NYEM0010E);
            }
            return;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.wfCmntTxt)) {
            bizMsg.wfCmntTxt.setErrorInfo(1, ZZM9000E, new String[] {"Comment" });
            return;
        }

        // Check Auth
        boolean errFlg = false;
        for (int cnt : selectedRows) {
            if (ZYPConstant.FLG_OFF_N.equals(bizMsg.A.no(cnt).xxWfAprChkVisFlg_A.getValue())) {
                bizMsg.A.no(cnt).xxWfAprChkFlg_A.setErrorInfo(1, NYEM0013E, new String[]{"Reject authority"});
                errFlg = true;
            }
        }

        if (errFlg){
            return;
        }

        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_Reject]Start Reject"));
        S21StopWatch sw = new S21StopWatch();
        sw.start();

        for (int cnt : selectedRows) {
            boolean ret = reject(bizMsg, bizMsg.A.no(cnt).wfProcPk_A.getValue(), bizMsg.A.no(cnt).wfWrkItemPk_A.getValue());

            if (ret == false) {
                return;
            }
        }

        sw.stop();
        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_Reject]End Reject [%d]", sw.getElapsedMilliSec()));

        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_Approve]Start Search"));
        sw = new S21StopWatch();
        sw.start();

        // 2018/02/06 ADD START
        ZYPTableUtil.clear(bizMsg.A);
        // 2018/02/06 ADD END
        NYEL8810CommonLogic.search(bizMsg, sMsg, this.getGlobalCompanyCode(), this.getContextUserInfo().getUserId());
        bizMsg.wfCmntTxt.clear();

        sw.stop();
        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_Approve]End Search [%d]", sw.getElapsedMilliSec()));

    }

    private void doProcess_NYEL8810Scrn00_ReAssign(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxWfAprChkFlg_A", ZYPConstant.CHKBOX_ON_Y);

        if (selectedRows.isEmpty()) {
            int max = bizMsg.A.getValidCount();
            for (int i = 0; i < max; i++) {
                bizMsg.A.no(i).xxWfAprChkFlg_A.setErrorInfo(1, NYEM0010E);
            }
            return;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.xxWfAsgCd)) {
            bizMsg.xxWfAsgCd.setErrorInfo(1, ZZM9000E, new String[] {"Assignee" });
            return;
        }

        // asgCdCheck
        String assigneeNm = NYXC880001.getAssigneeNmFromS21Psn(bizMsg.xxWfAsgCd.getValue());

        if ((assigneeNm == null) || (assigneeNm.length() <= 0)) {
            // QC#23516 MOD START 2018/05/07
//            bizMsg.xxWfAsgCd.setErrorInfo(1, ZZSM4110E, new String[] {"Assignee" });
            bizMsg.xxWfAsgCd.setErrorInfo(1, NYEM0016E, new String[] {"Assignee" });
            // QC#23516 MOD END 2018/05/07
            return;
        }

        // Check Auth
        boolean errFlg = false;
        for (int cnt : selectedRows) {
            if (ZYPConstant.FLG_OFF_N.equals(bizMsg.A.no(cnt).xxWfRasgChkVisFlg_A.getValue())) {
                bizMsg.A.no(cnt).xxWfAprChkFlg_A.setErrorInfo(1, NYEM0013E, new String[]{"Reassign authority"});
                errFlg = true;
            }
        }

        if (errFlg){
            return;
        }

        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_Approve]Start Reassign"));
        S21StopWatch sw = new S21StopWatch();
        sw.start();

        bizMsg.xxWfAsgNm.setValue(assigneeNm);

        // 2018/02/06 ADD START
        for (int cnt = 0; cnt < bizMsg.A.getValidCount(); cnt++) {
            bizMsg.A.no(cnt).xxWfAprChkFlg_A.clear();
        }
        // 2018/02/06 ADD END
        for (int cnt : selectedRows) {
            boolean ret = reassign(bizMsg, sMsg,bizMsg.A.no(cnt).wfProcPk_A.getValue(), bizMsg.A.no(cnt).wfWrkItemPk_A.getValue());
            boolean isError = true;
            
            if (ret == false) {
                if (bizMsg.getMessageInfo() != null) {
                    if ((bizMsg.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_WARNING) || (bizMsg.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_INFORMATION)) {
                        isError = false;
                    }
                }
                
                if (isError){
                    return;
                }
            }
        }
        bizMsg.wfCmntTxt.clear();
        sw.stop();
        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_Approve]End Reassign [%d]", sw.getElapsedMilliSec()));

    }

    private void doProcess_NYEL8810Scrn00_Cancel(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxWfAprChkFlg_A", ZYPConstant.CHKBOX_ON_Y);

        if (selectedRows.isEmpty()) {
            int max = bizMsg.A.getValidCount();
            for (int i = 0; i < max; i++) {
                bizMsg.A.no(i).xxWfAprChkFlg_A.setErrorInfo(1, NYEM0010E);
            }
            return;
        }

        // Check Auth
        boolean errFlg = false;
        for (int cnt : selectedRows) {
            if (ZYPConstant.FLG_OFF_N.equals(bizMsg.A.no(cnt).cancFlg_VF.getValue())) {
                bizMsg.A.no(cnt).xxWfAprChkFlg_A.setErrorInfo(1, NYEM0013E, new String[]{"Cancel authority"});
                errFlg = true;
            }
        }

        if (errFlg){
            return;
        }

        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_Approve]Start Cancel"));
        S21StopWatch sw = new S21StopWatch();
        sw.start();

        for (int cnt : selectedRows) {
            boolean ret = cancel(bizMsg, bizMsg.A.no(cnt).wfProcPk_A.getValue(), bizMsg.A.no(cnt).wfWrkItemPk_A.getValue());
            boolean isError = true;
            
            if (ret == false) {
                if (bizMsg.getMessageInfo() != null) {
                    if ((bizMsg.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_WARNING) || (bizMsg.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_INFORMATION)) {
                        isError = false;
                    }
                }
                
                if (isError){
                    return;
                }
            }
        }

        sw.stop();
        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_Approve]End Cancel [%d]", sw.getElapsedMilliSec()));

        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_Approve]Start Search"));
        sw = new S21StopWatch();
        sw.start();

        // 2018/02/06 ADD START
        ZYPTableUtil.clear(bizMsg.A);
        // 2018/02/06 ADD END
        NYEL8810CommonLogic.search(bizMsg, sMsg, this.getGlobalCompanyCode(), this.getContextUserInfo().getUserId());
        bizMsg.wfCmntTxt.clear();

        sw.stop();
        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_Approve]End Search [%d]", sw.getElapsedMilliSec()));
    }


    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NYEL8810Scrn00_SaveSearch(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) //
                && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, ZZM9000E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }
        if (NYEL8810CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, NYEM0014E // You can not [@] this record Because of [@] already exists.
                    , new String[] {//
                    converter.convLabel2i18nLabel(SCRN_ID_00, "Save") //
                            , converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }

        NYEL8810CommonLogic.callNszc0330forSaveSearch(//
                bizMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());

    }

    private void doProcessNYEL8810Scrn00_DeleteSearch(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            bizMsg.srchOptPk_S.setErrorInfo(1, NYEM0015E // [@] is not selected.
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Saved Search Options") });
            return;
        }

        NYEL8810CommonLogic.callNszc0330forDeleteSearch(//
                bizMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }
    
    /**
     * Approve Event
     * @param bizMsg Business Msg
     * @param procID Process ID
     * @param taskID Task ID
     * @return
     */
    private boolean approve(NYEL8810CMsg bizMsg, BigDecimal procID, BigDecimal taskID) {
        // Initialize NWF
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfProcess process;

        try {
            S21NwfContext context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            process = context.getProcess(procID);
        } catch (NumberFormatException e1) {
            bizMsg.setMessageInfo(NYEM0007E, new String[] {procID.toPlainString() });
            logger.error(e1);
            e1.printStackTrace();
            return false;
        } catch (S21NwfSystemException e1) {
            EZDMessageInfo info = e1.getMessageInfo();

            if (info != null) {
                bizMsg.setMessageInfo(e1.getMessageInfo().getCode(), e1.getMessageInfo().getParameter());
            } else {
                bizMsg.setMessageInfo(NYEM0007E, new String[] {procID.toPlainString() });
            }
            logger.error(e1);
            e1.printStackTrace();
            return false;
        }

        if (process == null) {
            bizMsg.setMessageInfo(NYEM0007E, new String[] {procID.toPlainString() });
            return false;
        }

        S21NwfToken token = process.getToken();

        if ((token.getActiveWorkItem() == null) || (!taskID.equals(token.getActiveWorkItem().getWorkItemUId()))) {
            bizMsg.setMessageInfo(NYEM0006I);
            return false;
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

            token.signal(SIGNAL_APPROVE);

        } catch (S21NwfException e) {
            if (e.getMessageInfo() != null) {
                if ((e.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_WARNING) || (e.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_INFORMATION)) {
                    bizMsg.setMessageInfo(NYEM0001E, new String[] {e.getMessageInfo().getCode(), parameterJoin(e.getMessageInfo().getParameter())});
                } else {
                    bizMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
                }
            } else {
                bizMsg.setMessageInfo(NYEM0007E, new String[] {procID.toPlainString() });
            }

            logger.error(e);

            return false;
        }

        S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess_approve]Start getLastBizMessage"));
        S21StopWatch sw = new S21StopWatch();
        sw.start();
        List<EZDMessageInfo> list = token.getLastBizMessage();

        if ((list != null) && (list.size() > 0)){
            bizMsg.setMessageInfo(list.get(0).getCode(), list.get(0).getParameter());
        } 

        sw.stop();
        S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess_approve]End getLastBizMessage [%d]", sw.getElapsedMilliSec()));

        if (S21StringUtil.isEmpty(bizMsg.getMessageCode())){
            bizMsg.setMessageInfo(ZZM8100I);
        }

        return true;
    }

    /**
     * Reject Event
     * @param bizMsg Business Msg
     * @param procID Process ID
     * @param taskID Task ID
     * @return
     */
    private boolean reject(NYEL8810CMsg bizMsg, BigDecimal procID, BigDecimal taskID) {

        // Initialize NWF
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfProcess process;
        try {
            S21NwfContext context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            process = context.getProcess(procID);
        } catch (NumberFormatException e1) {
            bizMsg.setMessageInfo(NYEM0008E, new String[] {procID.toPlainString() });
            logger.error(e1);
            return false;
        } catch (S21NwfSystemException e1) {
            EZDMessageInfo info = e1.getMessageInfo();

            if (info != null) {
                bizMsg.setMessageInfo(e1.getMessageInfo().getCode(), e1.getMessageInfo().getParameter());
            } else {
                bizMsg.setMessageInfo(NYEM0008E, new String[] {procID.toPlainString() });
            }
            logger.error(e1);
            return false;
        }
        S21NwfToken token = process.getToken();

        if ((token.getActiveWorkItem() == null) || (!taskID.equals(token.getActiveWorkItem().getWorkItemUId()))) {
            bizMsg.setMessageInfo(NYEM0006I);
            return false;
        }

        if (process == null) {
            bizMsg.setMessageInfo(NYEM0008E, new String[] {procID.toPlainString() });
            return false;
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
//          token.signal(SIGNAL_REJECT);
          boolean ignoreAuth = false;
          token.signal(SIGNAL_REJECT, ignoreAuth);
//2019/03/26 UPD END   QC#30827

        } catch (S21NwfException e) {
        	e.printStackTrace();
            if (e.getMessageInfo() != null) {
                if ((e.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_WARNING) || (e.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_INFORMATION)) {
                    bizMsg.setMessageInfo(NYEM0001E, new String[] {e.getMessageInfo().getCode(), parameterJoin(e.getMessageInfo().getParameter())});
                } else {
                    bizMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
                }
            } else {
                bizMsg.setMessageInfo(NYEM0008E, new String[] {procID.toPlainString() });
            }

            logger.error(e);
            return false;
        }

        List<EZDMessageInfo> list = token.getLastBizMessage();

        if ((list != null) && (list.size() > 0)){
            bizMsg.setMessageInfo(list.get(0).getCode(), list.get(0).getParameter());
        }

        if (S21StringUtil.isEmpty(bizMsg.getMessageCode())){
            bizMsg.setMessageInfo(ZZM8100I);
        }

        return true;
    }

    /**
     * Cancel Event
     * @param bizMsg Business Msg
     * @param procID Process ID
     * @param taskID Task ID
     * @return
     */
    private boolean cancel(NYEL8810CMsg bizMsg, BigDecimal procID, BigDecimal taskID) {
        if (!ZYPCommonFunc.hasValue(bizMsg.wfCmntTxt)) {
            bizMsg.wfCmntTxt.setErrorInfo(1, ZZM9000E, new String[] {"Comment" });
            return false;
        }

        // Initialize NWF
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfProcess process;
        try {
            S21NwfContext context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            process = context.getProcess(procID);
        } catch (NumberFormatException e1) {
            bizMsg.setMessageInfo(NYEM0008E, new String[] {procID.toPlainString() });
            logger.error(e1);
            return false;
        } catch (S21NwfSystemException e1) {
            EZDMessageInfo info = e1.getMessageInfo();

            if (info != null) {
                bizMsg.setMessageInfo(e1.getMessageInfo().getCode(), e1.getMessageInfo().getParameter());
            } else {
                bizMsg.setMessageInfo(NYEM0008E, new String[] {procID.toPlainString() });
            }
            logger.error(e1);
            return false;
        }

        S21NwfToken token = process.getToken();

        if (token.getActiveWorkItem() == null) {
            bizMsg.setMessageInfo(NYEM0006I);
            return false;
        }

        if (process == null) {
            bizMsg.setMessageInfo(NYEM0008E, new String[] {procID.toPlainString() });
            return false;
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

// 2019/01/15 MOD START QC#29683
//            token.cancel();
            token.cancelWithAuth();
// 2019/01/15 MOD END   QC#29683

        } catch (S21NwfException e) {
            if (e.getMessageInfo() != null) {
                bizMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
            } else {
                bizMsg.setMessageInfo(NYEM0008E, new String[] {procID.toPlainString() });
            }
            logger.error(e);
            return false;
        }
        bizMsg.setMessageInfo(ZZM8100I);

        return true;
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param procID Process ID
     * @param taskID Task ID
     * @return
     */
    private boolean fyi(NYEL8810CMsg bizMsg, BigDecimal procID, BigDecimal taskID) {

        // Initialize NWF
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfProcess process;
        try {
            S21NwfContext context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());

            process = context.getProcess(procID);
        } catch (NumberFormatException e1) {
            bizMsg.setMessageInfo(NYEM0008E, new String[] {procID.toPlainString() });
            logger.error(e1);
            return false;
        } catch (S21NwfSystemException e1) {
            EZDMessageInfo info = e1.getMessageInfo();

            if (info != null) {
                bizMsg.setMessageInfo(e1.getMessageInfo().getCode(), e1.getMessageInfo().getParameter());
            } else {
                bizMsg.setMessageInfo(NYEM0008E, new String[] {procID.toPlainString() });
            }
            logger.error(e1);
            return false;
        }

        if (process == null) {
            bizMsg.setMessageInfo(NYEM0008E, new String[] {procID.toPlainString() });
            return false;
        }

        try {
            S21NwfWorkItemImpl wi = process.findWorkItem(taskID);
            wi.confirmFyi();
        } catch (S21NwfException e) {
            if (e.getMessageInfo() != null) {
                bizMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
            } else {
                bizMsg.setMessageInfo(NYEM0008E, new String[] {procID.toPlainString() });
            }
            logger.error(e);
            return false;
        }
        bizMsg.setMessageInfo(ZZM8100I);

        return true;
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private boolean reassign(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg, BigDecimal procID, BigDecimal taskID) {

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfProcess proc = null;
        try {
            S21NwfContext context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            proc = context.getProcess(procID);
        } catch (NumberFormatException e1) {
            e1.printStackTrace();
            bizMsg.setMessageInfo(ZZZM9006E, new String[] {procID.toPlainString() });
            logger.error(e1);
            return false;
        } catch (S21NwfSystemException e1) {
            e1.printStackTrace();

            EZDMessageInfo msginfo = e1.getMessageInfo();

            if (msginfo != null) {
                bizMsg.setMessageInfo(e1.getMessageInfo().getCode(), e1.getMessageInfo().getParameter());
            } else {
                bizMsg.setMessageInfo(ZZZM9006E, new String[] {procID.toPlainString() });
            }
            logger.error(e1);
            return false;
        }

        //
        // recv reassignable check
        //
        boolean isRecvRasg = NYXC880002.isRecvReassignable(taskID, bizMsg.xxWfAsgCd.getValue());

        if (isRecvRasg == false) {
            // QC#23516 MOD START 2018/05/07
//            bizMsg.setMessageInfo(ZZSM4110E, new String[] {bizMsg.xxWfAsgCd.getValue() });
            bizMsg.setMessageInfo(NYEM0019E, new String[] {bizMsg.xxWfAsgCd.getValue(), "Assignee"});
            // QC#23516 MOD END 2018/05/07
            return false;
        }

        //
        // duplicate check
        //
        boolean isDuplicate = isDuplicateAsgCd(bizMsg, taskID);

        if (isDuplicate) {
            bizMsg.xxWfAsgCd.setErrorInfo(1, NYEM0009E, new String[] {bizMsg.xxWfAsgCd.getValue() });
            return false;
        }

        S21NwfToken token = proc.getToken();
        S21NwfTokenObj tokenObj = token.getTokenObj();

        try {
            // add
            token.reassignActiveWorkItem(USER_ROLE_TYPE.USER, bizMsg.xxWfAsgCd.getValue(), bizMsg.wfCmntTxt.getValue());

            // Refresh
            NYEL8810CommonLogic.search(bizMsg, sMsg, this.getGlobalCompanyCode(), this.getContextUserInfo().getUserId());
        } catch (S21NwfException e) {
            
            EZDMessageInfo msginfo = e.getMessageInfo();

            if (msginfo != null) {
                if ((msginfo.getMessageType() == EZDMessageInfo.MSGTYPE_WARNING) || (msginfo.getMessageType() == EZDMessageInfo.MSGTYPE_INFORMATION)) {
                    bizMsg.setMessageInfo(NYEM0001E, new String[] {msginfo.getCode(), parameterJoin(msginfo.getParameter())});
                } else {
                    bizMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
                }
            } else {
                bizMsg.setMessageInfo(ZZZM9006E, new String[] {procID.toPlainString() });
            }
            logger.error(e);
            return false;
        }

        if (tokenObj != null){
            List<EZDMessageInfo> list = tokenObj.getMessageInfo();

            if ((list != null) && (list.size() > 0)){
                bizMsg.setMessageInfo(list.get(0).getCode(), list.get(0).getParameter());
            } 
        }

        if (S21StringUtil.isEmpty(bizMsg.getMessageCode())){
            bizMsg.setMessageInfo(ZZM8100I);
        }

        bizMsg.setMessageInfo(ZZM8100I);

        return true;
    }

    private boolean isDuplicateAsgCd(NYEL8810CMsg bizMsg, BigDecimal taskID) {
        boolean ret = false;

        // To User
        S21SsmEZDResult userList = NYXC880002Query.getInstance().getToUsers(taskID, this.getContextUserInfo().getUserId(), bizMsg.procStsCd.getValue());

        // No Data
        if (userList.isCodeNormal()) {
            // Normal
            List userltList = (List) userList.getResultObject();
            int maxSize = userltList.size();
            String toUserID = "";
            String asgCd = bizMsg.xxWfAsgCd.getValue();

            for (int i = 0; i < maxSize; i++) {
                Map userMap = (Map) userltList.get(i);
                toUserID = (String) userMap.get("WF_USR_ID");

                if (S21StringUtil.isNotEmpty(toUserID) && toUserID.equals(asgCd)) {
                    ret = true;
                    break;
                }
            }
        }
        return ret;
    }

    private String parameterJoin(String[] parameter){
        StringBuilder sb = new StringBuilder();
        
        if (parameter != null){
            
            for (String param : parameter){
                if (sb.length() <= 0){
                    sb.append(param);
                } else {
                    sb.append(" , " + param);
                }
            }
        }
        return sb.toString();
    }

    // QC#23516 ADD START 2018/05/07
    private void doProcess_NYEL8810Scrn00_CMN_Save(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxWfAprChkFlg_A", ZYPConstant.CHKBOX_ON_Y);

        if (selectedRows.isEmpty()) {
            int max = bizMsg.A.getValidCount();
            for (int i = 0; i < max; i++) {
                bizMsg.A.no(i).xxWfAprChkFlg_A.setErrorInfo(1, NYEM0010E);
            }
            return;
        }

        // Check Auth
        boolean errFlg = false;
        for (int cnt : selectedRows) {
            if (ZYPConstant.FLG_OFF_N.equals(bizMsg.A.no(cnt).xxWfAprChkVisFlg_A.getValue())) {
                bizMsg.A.no(cnt).xxWfAprChkFlg_A.setErrorInfo(1, NYEM0013E, new String[]{"Approval authority"});
                errFlg = true;
            }
        }

        if (errFlg){
            return;
        }

        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_CMN_Save]Start Comment"));
        S21StopWatch sw = new S21StopWatch();
        sw.start();

        for (int cnt : selectedRows) {
            boolean ret = comment(bizMsg, bizMsg.A.no(cnt).wfProcPk_A.getValue(), bizMsg.A.no(cnt).wfWrkItemPk_A.getValue());
            boolean isError = true;
            
            if (ret == false) {
                if (bizMsg.getMessageInfo() != null) {
                    if ((bizMsg.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_WARNING) || (bizMsg.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_INFORMATION)) {
                        isError = false;
                    }
                }
                
                if (isError){
                    return;
                }
            }
        }

        sw.stop();
        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_CMN_Save]End Comment [%d]", sw.getElapsedMilliSec()));

        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_CMN_Save]Start Search"));
        sw = new S21StopWatch();
        sw.start();

        ZYPTableUtil.clear(bizMsg.A);
        NYEL8810CommonLogic.search(bizMsg, sMsg, this.getGlobalCompanyCode(), this.getContextUserInfo().getUserId());
        bizMsg.wfCmntTxt.clear();

        sw.stop();
        S21InfoLogOutput.println(String.format("[doProcess_NYEL8810Scrn00_CMN_Save]End Search [%d]", sw.getElapsedMilliSec()));
    }

    /**
     * Comment Event
     * @param bizMsg Business Msg
     * @param procID Process ID
     * @param taskID Task ID
     * @return
     */
    private boolean comment(NYEL8810CMsg bizMsg, BigDecimal procID, BigDecimal taskID) {
        if (!ZYPCommonFunc.hasValue(bizMsg.wfCmntTxt)) {
            bizMsg.wfCmntTxt.setErrorInfo(1, ZZM9000E, new String[] {"Comment" });
            return false;
        }

        // Initialize NWF
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfProcess process;
        try {
            S21NwfContext context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            process = context.getProcess(procID);
        } catch (NumberFormatException e1) {
            bizMsg.setMessageInfo(NYEM0018E, new String[] {procID.toPlainString() });
            logger.error(e1);
            return false;
        } catch (S21NwfSystemException e1) {
            EZDMessageInfo info = e1.getMessageInfo();

            if (info != null) {
                bizMsg.setMessageInfo(e1.getMessageInfo().getCode(), e1.getMessageInfo().getParameter());
            } else {
                bizMsg.setMessageInfo(NYEM0018E, new String[] {procID.toPlainString() });
            }
            logger.error(e1);
            return false;
        }

        S21NwfToken token = process.getToken();

        if (token.getActiveWorkItem() == null) {
            bizMsg.setMessageInfo(NYEM0006I);
            return false;
        }

        if (process == null) {
            bizMsg.setMessageInfo(NYEM0018E, new String[] {procID.toPlainString() });
            return false;
        }

        try {
            String comment = bizMsg.wfCmntTxt.getValue();

            token.commentActiveWorkItem(comment);

        } catch (S21NwfException e) {
            if (e.getMessageInfo() != null) {
                bizMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
            } else {
                bizMsg.setMessageInfo(NYEM0018E, new String[] {procID.toPlainString() });
            }
            logger.error(e);
            return false;
        }
        bizMsg.setMessageInfo(ZZM8100I);

        return true;
    }
    // QC#23516 ADD END 2018/05/07
}
