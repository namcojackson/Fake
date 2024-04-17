/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8899;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NYEL8899.constant.NYEL8899Constant;
import business.db.WF_SUBMT_INFOTMsg;

import com.canon.cusa.s21.common.NYX.NYXC889010.NYEL8890TokenBizFactory;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenObj;
import com.canon.cusa.s21.framework.nwf.core.token.impl.S21NwfTokenImpl;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.nwf.util.process.S21NwfUtilProcessFactory;
import com.canon.cusa.s21.framework.nwf.util.token.S21NwfUtilTokenObj;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.SIGNAL_APPROVE;
import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.SIGNAL_REJECT;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0007E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0008E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ZZM8100I;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ZZM9000E;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/**
 *<pre>
 * NYEL8899BL04
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8899BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NYEL8899CMsg bizMsg = (NYEL8899CMsg) cMsg;

            if ("NYEL8899Scrn00_CMN_Approve".equals(screenAplID)) {
                doProcess_NYEL8899Scrn00_CMN_Approve(bizMsg, sMsg);

            } else if ("NYEL8899Scrn00_CMN_Reject".equals(screenAplID)) {
                doProcess_NYEL8899Scrn00_CMN_Reject(bizMsg, sMsg);

            } else if ("NYEL8899Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NYEL8899Scrn00_CMN_Submit(bizMsg, sMsg);

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
    private void doProcess_NYEL8899Scrn00_CMN_Approve(NYEL8899CMsg bizMsg, EZDSMsg glblMsg) {
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        List<S21NwfProcess> process;
        try {
            S21NwfContext context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            process = context.getProcess(bizMsg.wfProcNm.getValue(), bizMsg.wfBizAttrbTxt_01.getValue());
        } catch (S21NwfSystemException e1) {
            EZDMessageInfo info = e1.getMessageInfo();

            if (info != null) {
                bizMsg.setMessageInfo(e1.getMessageInfo().getCode(), e1.getMessageInfo().getParameter());
            } else {
                bizMsg.setMessageInfo(NYEM0007E, new String[] {bizMsg.wfProcNm.getValue() });
            }

            return;
        }

        if ((process == null) || (process.size() <= 0)) {
            bizMsg.setMessageInfo(NYEM0007E, new String[] {bizMsg.wfProcNm.getValue() });
            return;
        }
        S21NwfToken token = process.get(0).getToken();
        try {

            String comment = bizMsg.xxWfAsgCmntTxt.getValue();

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
            bizMsg.setMessageInfo(NYEM0007E, new String[] {bizMsg.wfProcNm.getValue() });
            return;
        }
        bizMsg.setMessageInfo(ZZM8100I);
    }

    /**
     * CMN_Reject Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NYEL8899Scrn00_CMN_Reject(NYEL8899CMsg bizMsg, EZDSMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.xxWfAsgCmntTxt)) {
            bizMsg.xxWfAsgCmntTxt.setErrorInfo(1, ZZM9000E, new String[] {"Comment" });
            return;
        }
        // Initialize NWF
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        List<S21NwfProcess> process;
        try {
            S21NwfContext context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            process = context.getProcess(bizMsg.wfProcNm.getValue(), bizMsg.wfBizAttrbTxt_01.getValue());
        } catch (S21NwfSystemException e1) {
            EZDMessageInfo info = e1.getMessageInfo();

            if (info != null) {
                bizMsg.setMessageInfo(e1.getMessageInfo().getCode(), e1.getMessageInfo().getParameter());
            } else {
                bizMsg.setMessageInfo(NYEM0008E, new String[] {bizMsg.wfProcNm.getValue() });
            }
            return;
        }
        S21NwfToken token = process.get(0).getToken();

        if (process == null) {
            bizMsg.setMessageInfo(NYEM0008E, new String[] {bizMsg.wfProcNm.getValue() });
            return;
        }

        try {
            String comment = bizMsg.xxWfAsgCmntTxt.getValue();

            if (comment != null) {
                S21NwfTokenObj tokenObj = token.getTokenObj();

                if (tokenObj == null) {
                    tokenObj = new S21NwfTokenObj();
                    token.setTokenObj(tokenObj);
                }
                tokenObj.setComment(comment);
            }

            token.signal(SIGNAL_REJECT);
        } catch (S21NwfException e) {
            bizMsg.setMessageInfo(NYEM0008E, new String[] {bizMsg.wfProcNm.getValue() });
            return;
        }
        bizMsg.setMessageInfo(ZZM8100I);
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NYEL8899Scrn00_CMN_Submit(NYEL8899CMsg bizMsg, EZDSMsg glblMsg) {

        // Initialize NWF
        S21NwfProcess process;

        try {
            S21NwfContextFactory factory = new S21NwfUtilContextFactory();
            S21NwfContext context = factory.getContex();
            process = context.newProcess(bizMsg.wfProcNm.getValue());
        } catch (S21NwfSystemException e) {
            // System Error Logic
            EZDMessageInfo info = e.getMessageInfo();

            if (info != null) {
                bizMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
            } else {
                if (S21StringUtil.isEmpty(e.getMessage())){
                    bizMsg.setMessageInfo("NFCM0576E", new String[] {"Workflow signal" });
                } else {
                    bizMsg.setMessageInfo("NFCM0576E", new String[] {"Workflow signal:" + e.getMessage() });
                }
            }
            return;
        }

        S21NwfTokenImpl token = (S21NwfTokenImpl) process.getToken();

        process.setDocumentId(bizMsg.wfProcDocId.getValue());

        String factoryClazz = bizMsg.wfClsNm.getValue();
        String pattern = bizMsg.wfTestPtrnNm.getValue();
        S21NwfUtilTokenObj tokenObj = null;

        if (S21StringUtil.isNotEmpty(factoryClazz) && S21StringUtil.isNotEmpty(pattern)) {
            Class<NYEL8890TokenBizFactory> clazz = null;
            try {
                clazz = (Class<NYEL8890TokenBizFactory>) Class.forName(factoryClazz);
                if (clazz == null) {
                    bizMsg.setMessageInfo(NYEL8899Constant.NFCM0501E, new String[] {factoryClazz });
                    return;
                }

                tokenObj = clazz.newInstance().crateTokenBizObject(pattern);
            } catch (ClassNotFoundException e) {
                bizMsg.setMessageInfo(NYEL8899Constant.NFCM0501E, new String[] {factoryClazz });
                e.printStackTrace();
                return;
            } catch (InstantiationException e) {
                bizMsg.setMessageInfo(NYEL8899Constant.NFCM0501E, new String[] {factoryClazz });
                e.printStackTrace();
                return;
            } catch (IllegalAccessException e) {
                bizMsg.setMessageInfo(NYEL8899Constant.NFCM0501E, new String[] {factoryClazz });
                e.printStackTrace();
                return;
            }
        } else {
            tokenObj = new S21NwfUtilTokenObj();
        }

        tokenObj.setAttribute1(bizMsg.wfBizAttrbTxt_01.getValue());
        tokenObj.setAttribute2(bizMsg.wfBizAttrbTxt_02.getValue());
        tokenObj.setAttribute3(bizMsg.wfBizAttrbTxt_03.getValue());
        tokenObj.setAttribute4(bizMsg.wfBizAttrbTxt_04.getValue());
        tokenObj.setAttribute5(bizMsg.wfBizAttrbTxt_05.getValue());

        tokenObj.setAttribute1Lbl(bizMsg.wfBizAttrbLbTxt_01.getValue());
        tokenObj.setAttribute2Lbl(bizMsg.wfBizAttrbLbTxt_02.getValue());
        tokenObj.setAttribute3Lbl(bizMsg.wfBizAttrbLbTxt_03.getValue());
        tokenObj.setAttribute4Lbl(bizMsg.wfBizAttrbLbTxt_04.getValue());
        tokenObj.setAttribute5Lbl(bizMsg.wfBizAttrbLbTxt_05.getValue());

        tokenObj.setCondStr1(bizMsg.condValTxt_01.getValue());
        tokenObj.setCondStr2(bizMsg.condValTxt_02.getValue());
        tokenObj.setCondStr3(bizMsg.condValTxt_03.getValue());
        tokenObj.setCondStr4(bizMsg.condValTxt_04.getValue());
        tokenObj.setCondStr5(bizMsg.condValTxt_05.getValue());
        tokenObj.setCondStr6(bizMsg.condValTxt_06.getValue());
        tokenObj.setCondStr7(bizMsg.condValTxt_07.getValue());
        tokenObj.setCondStr8(bizMsg.condValTxt_08.getValue());
        tokenObj.setCondStr9(bizMsg.condValTxt_09.getValue());
        tokenObj.setCondStr10(bizMsg.condValTxt_10.getValue());

        tokenObj.setCondNum1(bizMsg.condValNum_01.getValue());
        tokenObj.setCondNum2(bizMsg.condValNum_02.getValue());
        tokenObj.setCondNum3(bizMsg.condValNum_03.getValue());
        tokenObj.setCondNum4(bizMsg.condValNum_04.getValue());
        tokenObj.setCondNum5(bizMsg.condValNum_05.getValue());
        tokenObj.setCondNum6(bizMsg.condValNum_06.getValue());
        tokenObj.setCondNum7(bizMsg.condValNum_07.getValue());
        tokenObj.setCondNum8(bizMsg.condValNum_08.getValue());
        tokenObj.setCondNum9(bizMsg.condValNum_09.getValue());
        tokenObj.setCondNum10(bizMsg.condValNum_10.getValue());

        // Biz Screen Transition
        tokenObj.setBizScreenId("NYEL8899");
        tokenObj.setBizScreenParams(bizMsg.wfProcNm.getValue(), bizMsg.wfDataNm.getValue());

        // Token作成
        try {
            String comment = bizMsg.xxWfAsgCmntTxt.getValue();
            if (comment != null) {
                tokenObj.setComment(comment);
            }
            token.setTokenObj(tokenObj);
            token.start();
        } catch (S21NwfException e) {
            EZDMessageInfo info = e.getMessageInfo();

            if (info != null) {
                bizMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
            } else {
                if (S21StringUtil.isEmpty(e.getMessage())){
                    bizMsg.setMessageInfo("NFCM0576E", new String[] {"Workflow signal" });
                } else {
                    bizMsg.setMessageInfo("NFCM0576E", new String[] {"Workflow signal:" + e.getMessage() });
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
            // Submit
            regist(bizMsg);
        }

    }

    private void regist(NYEL8899CMsg bizMsg) {
        WF_SUBMT_INFOTMsg condMsg = new WF_SUBMT_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, this.getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(condMsg.wfProcNm, bizMsg.wfProcNm.getValue());
        ZYPEZDItemValueSetter.setValue(condMsg.wfProcDocId, bizMsg.wfProcDocId.getValue());
        ZYPEZDItemValueSetter.setValue(condMsg.wfTestPtrnNm, bizMsg.wfDataNm.getValue());

        WF_SUBMT_INFOTMsg updateMsg = (WF_SUBMT_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(condMsg);

        String wfTestPtrnNm = bizMsg.wfDataNm.getValue();
        String wfDataNm = bizMsg.wfTestPtrnNm.getValue();

        if (updateMsg == null) {
            // insert
            updateMsg = new WF_SUBMT_INFOTMsg();

            EZDMsg.copy(bizMsg, null, updateMsg, null);
            ZYPEZDItemValueSetter.setValue(updateMsg.wfTestPtrnNm, wfTestPtrnNm);
            ZYPEZDItemValueSetter.setValue(updateMsg.wfDataNm, wfDataNm);
            ZYPEZDItemValueSetter.setValue(updateMsg.glblCmpyCd, this.getGlobalCompanyCode());
            EZDTBLAccessor.insert(updateMsg);
        } else {
            // update
            EZDMsg.copy(bizMsg, null, updateMsg, null);
            ZYPEZDItemValueSetter.setValue(updateMsg.wfTestPtrnNm, wfTestPtrnNm);
            ZYPEZDItemValueSetter.setValue(updateMsg.wfDataNm, wfDataNm);
            EZDTBLAccessor.update(updateMsg);
        }

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateMsg.getReturnCode())) {
            throw new S21AbendException("ZZZM9004E");
        }
    }

}
