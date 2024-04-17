/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0860;

import static business.blap.NSAL0860.constant.NSAL0860Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;

import business.db.SVC_MACH_MSTRTMsg;
import business.db.UGW_ACTTMsg;
import business.file.NSAL0860F00FMsg;
import business.parts.NSZC049001PMsg;
import business.parts.NSZC050001PMsg;
import business.blap.NSAL0860.common.NSAL0860CommonLogic;

import com.canon.cusa.s21.api.NSZ.NSZC049001.NSZC049001;
import com.canon.cusa.s21.api.NSZ.NSZC050001.NSZC050001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UGW_ACT;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Register & Deregister Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/06   Hitachi         Y.Osawa         Create          N/A
 * 2016/06/17   Hitachi         O.Okuma         Update          QC#10238
 * 2016/07/15   Hitachi         Y.Osawa         Update          QC#9956
 * 2018/03/24   Hitachi         U.Kim           Update          QC#23071
 *</pre>
 */
public class NSAL0860BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0860CMsg cMsg = (NSAL0860CMsg) arg0;
        NSAL0860SMsg sMsg = (NSAL0860SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0860Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(cMsg, sMsg);
            } else if ("NSAL0860Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(cMsg, sMsg);
            } else if ("NSAL0860Scrn00_Deregister".equals(screenAplID)) {
                doProcess_NSAL0860Scrn00_Deregister(cMsg, sMsg);
            } else if ("NSAL0860Scrn00_Register".equals(screenAplID)) {
                doProcess_NSAL0860Scrn00_Register(cMsg, sMsg);
            } else if ("NSAL0860Scrn00_Upload".equals(screenAplID)) {
                doProcess_NSAL0860Scrn00_Upload(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0860Scrn00_Register(NSAL0860CMsg cMsg, NSAL0860SMsg sMsg) {
        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        // START 2018/03/27 U.Kim [QC#23071, ADD]
        String firstMsgId = null;
        // END 2018/03/27 U.Kim [QC#23071, ADD]

        NSAL0860CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }

        NSZC049001 api = new NSZC049001();
        String msgId = null;
        String msgStr = null;

        NSZC049001PMsg pMsg = new NSZC049001PMsg();
        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.slsDt, cMsg.slsDt);
        setValue(pMsg.usrId, getContextUserInfo().getUserId());
        setValue(pMsg.svcCallFlg, ZYPConstant.FLG_ON_Y);

        for (int i : selectedRows) {
            setValue(sMsg.A.no(i).ugwActDescTxt_A, UGW_ACT.REGISTER);

            getSvcMachMstr(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).svcMachMstrPk_A.getValue());

            setValue(pMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A);
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);

            if (S21ApiUtil.isXxMsgId(pMsg)) {
                boolean isErr = false;
                for (int j = 0; j < S21ApiUtil.getXxMsgList(pMsg).size(); j++) {
                    msgId = S21ApiUtil.getXxMsgList(pMsg).get(j).getXxMsgid();
                    // START 2018/03/27 U.Kim [QC#23071, ADD]
                    if (!ZYPCommonFunc.hasValue(firstMsgId)) {
                        firstMsgId = msgId;
                    }
                    // END 2018/03/27 U.Kim [QC#23071, ADD]

                    if (msgId.endsWith("E")) {
                        isErr = true;
                        break;
                    }
                }
                msgStr = S21MessageFunc.clspGetMessage(msgId);

                setValue(sMsg.A.no(i).xxMsgTxt_A, msgStr);

                if (isErr) {
                    EZDConnectionMgr.getInstance().rollback();
                    setValue(sMsg.A.no(i).ugwActStsDescTxt_A, ERROR);
                } else {
                    EZDConnectionMgr.getInstance().commit();
                    setValue(sMsg.A.no(i).ugwActStsDescTxt_A, WARNING);
                }
            } else {
                EZDConnectionMgr.getInstance().commit();
                setValue(sMsg.A.no(i).ugwActStsDescTxt_A, SUCCESS);
            }
        }
        for (int i = 0; i < cMsg.A.length(); i++) {
            if (pageFromNum + i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(pageFromNum + i), null, cMsg.A.get(i), null);
            }
        }
        // START 2018/03/27 U.Kim [QC#23071, MOD]
        // cMsg.setMessageInfo(NZZM0002I);
        if (!ZYPCommonFunc.hasValue(firstMsgId)) {
            cMsg.setMessageInfo(NZZM0002I);
        } else {
            cMsg.setMessageInfo(firstMsgId);
        }
        // END 2018/03/27 U.Kim [QC#23071, MOD]
    }

    private void doProcess_NSAL0860Scrn00_Deregister(NSAL0860CMsg cMsg, NSAL0860SMsg sMsg) {
        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        // START 2018/03/27 U.Kim [QC#23071, ADD]
        String firstMsgId = null;
        // END 2018/03/27 U.Kim [QC#23071, ADD]
        NSAL0860CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }

        NSZC050001 api = new NSZC050001();
        String msgId = null;
        String msgStr = null;

        NSZC050001PMsg pMsg = new NSZC050001PMsg();
        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.slsDt, cMsg.slsDt);

        for (int i : selectedRows) {
            setValue(sMsg.A.no(i).ugwActDescTxt_A, UGW_ACT.DE_REGISTER);

            getSvcMachMstr(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).svcMachMstrPk_A.getValue());

            setValue(pMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A);
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                boolean isErr = false;
                for (int j = 0; j < S21ApiUtil.getXxMsgList(pMsg).size(); j++) {
                    msgId = S21ApiUtil.getXxMsgList(pMsg).get(j).getXxMsgid();
                    // START 2018/03/27 U.Kim [QC#23071, ADD]
                    if (!ZYPCommonFunc.hasValue(firstMsgId)) {
                        firstMsgId = msgId;
                    }
                    // END 2018/03/27 U.Kim [QC#23071, ADD]

                    if (msgId.endsWith("E")) {
                        isErr = true;
                        break;
                    }
                }
                msgStr = S21MessageFunc.clspGetMessage(msgId);

                setValue(sMsg.A.no(i).xxMsgTxt_A, msgStr);

                if (isErr) {
                    EZDConnectionMgr.getInstance().rollback();
                    setValue(sMsg.A.no(i).ugwActStsDescTxt_A, ERROR);
                } else {
                    EZDConnectionMgr.getInstance().commit();
                    setValue(sMsg.A.no(i).ugwActStsDescTxt_A, WARNING);
                }
            } else {
                EZDConnectionMgr.getInstance().commit();
                setValue(sMsg.A.no(i).ugwActStsDescTxt_A, SUCCESS);
                // START 2018/03/27 U.Kim [QC#23071, ADD]
                cMsg.setMessageInfo(NZZM0002I);
                // END 2018/03/27 U.Kim [QC#23071, ADD]
            }
        }
        for (int i = 0; i < cMsg.A.length(); i++) {
            if (pageFromNum + i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(pageFromNum + i), null, cMsg.A.get(i), null);
            }
        }
     // START 2018/03/27 U.Kim [QC#23071, MOD]
        // cMsg.setMessageInfo(NZZM0002I);
        if (!ZYPCommonFunc.hasValue(firstMsgId)) {
            cMsg.setMessageInfo(NZZM0002I);
        } else {
            cMsg.setMessageInfo(firstMsgId);
        }
        // END 2018/03/27 U.Kim [QC#23071, MOD]
    }

    private void doProcess_NSAL0860Scrn00_Upload(NSAL0860CMsg cMsg, NSAL0860SMsg sMsg) {
        String path = cMsg.xxFileData_U.getTempFilePath();
        NSAL0860F00FMsg fMsg = new NSAL0860F00FMsg();

        // create output file
        cMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BIZ_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        NSAL0860F00FMsg outFMsg = new NSAL0860F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_D.getTempFilePath(), outFMsg);
        NSAL0860CommonLogic.writeCsvFileHeader(csvOutFile, outFMsg, cMsg);

        String csvFilePath = ZYPExcelUtil.excelToCsvFile(path);
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(csvFilePath, fMsg, option);

        EZDCSVInFile mappedFileForCountCheck = new EZDCSVInFile(csvFilePath, fMsg, option);
        int count = 0;

        try {
            while (mappedFileForCountCheck.read() != 1) {
                count++;
            }
            if (count > FMSG_MAX_COUNT + 2) {
                cMsg.setMessageInfo(ZYEM0013E, new String[] {MAX_COUNT_STR });
                return;
            }

            // first header line
            if (mappedFile.read() == 1) {
                cMsg.setMessageInfo(ZYEM0004E);
                return;
            }
            // second header line
            if (mappedFile.read() == 1) {
                cMsg.setMessageInfo(ZYEM0004E);
                return;
            }
            // third header line
            if (mappedFile.read() != 1) {
                // copy upload file to format check file
                if (!"Model Name".equals(fMsg.t_MdlNm_A.getValue())) {
                    cMsg.setMessageInfo(ZYEM0012E);
                    return;
                }

                if (!"Serial Number".equals(fMsg.serNum_A.getValue())) {
                    cMsg.setMessageInfo(ZYEM0012E);
                    return;
                }

                if (!"Action".equals(fMsg.ugwActDescTxt_A.getValue())) {
                    cMsg.setMessageInfo(ZYEM0012E);
                    return;
                }

                if (!"Status".equals(fMsg.ugwActStsDescTxt_A.getValue())) {
                    cMsg.setMessageInfo(ZYEM0012E);
                    return;
                }

                if (!"Message".equals(fMsg.xxMsgTxt_A.getValue())) {
                    cMsg.setMessageInfo(ZYEM0012E);
                    return;
                }
            }
            // do basic check and load to screen(for all csv data)
            while (mappedFile.read() != 1) {
                // copy upload file to download file
                EZDMsg.copy(fMsg, null, outFMsg, null);

                if (!ZYPCommonFunc.hasValue(fMsg.serNum_A)) {
                    setValue(outFMsg.ugwActStsDescTxt_A, ERROR);
                    setValue(outFMsg.xxMsgTxt_A, S21MessageFunc.clspGetMessage(ZZM9000E, new String[] {"Serial Number" }));
                    csvOutFile.write();
                    continue;
                }

                if (!ZYPCommonFunc.hasValue(fMsg.ugwActDescTxt_A)) {
                    setValue(outFMsg.ugwActStsDescTxt_A, ERROR);
                    setValue(outFMsg.xxMsgTxt_A, S21MessageFunc.clspGetMessage(ZZM9000E, new String[] {"Action" }));
                    csvOutFile.write();
                    continue;
                }

                String ugwActCd = null;
                if (UGW_ACT.REGISTER.compareToIgnoreCase(fMsg.ugwActDescTxt_A.getValue()) != 0 && UGW_ACT.DE_REGISTER.compareToIgnoreCase(fMsg.ugwActDescTxt_A.getValue()) != 0) {
                    setValue(outFMsg.ugwActStsDescTxt_A, ERROR);
                    setValue(outFMsg.xxMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0522E));
                    csvOutFile.write();
                    continue;
                } else if (UGW_ACT.REGISTER.compareToIgnoreCase(fMsg.ugwActDescTxt_A.getValue()) == 0) {
                    ugwActCd = UGW_ACT.REGISTER;
                } else {
                    ugwActCd = UGW_ACT.DE_REGISTER;
                }

                S21SsmEZDResult ssmResult = NSAL0860Query.getInstance().getMachineInfo(cMsg, fMsg);

                if (ssmResult != null && ssmResult.isCodeNormal()) {
                    // Result > 2
                    int queryResCnt = ssmResult.getQueryResultCount();

                    if (queryResCnt > 2) {
                        setValue(outFMsg.ugwActStsDescTxt_A, ERROR);
                        setValue(outFMsg.xxMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0523E));
                        csvOutFile.write();
                        continue;
                    }
                    // add start 2016/06/15 CSA Defect#9954
                    List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
                    if (rsltList.get(0).get("T_MDL_NM") == null) {
                        setValue(outFMsg.ugwActStsDescTxt_A, ERROR);
                        setValue(outFMsg.xxMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0523E));
                        csvOutFile.write();
                        continue;
                    }
                    if (ZYPConstant.FLG_ON_Y.equals(rsltList.get(0).get("SHR_DLR_FLG").toString())) {
                        setValue(outFMsg.ugwActStsDescTxt_A, ERROR);
                        setValue(outFMsg.xxMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0536E));
                        csvOutFile.write();
                        continue;
                    }
                    // add end 2016/06/15 CSA Defect#9954
                } else {
                    // No result
                    setValue(outFMsg.ugwActStsDescTxt_A, ERROR);
                    setValue(outFMsg.xxMsgTxt_A, S21MessageFunc.clspGetMessage(NSZM0703E, new String[] {fMsg.serNum_A.getValue() }));
                    csvOutFile.write();
                    continue;
                }

                List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
                BigDecimal svcMachMstrPk = (BigDecimal) rsltList.get(0).get("SVC_MACH_MSTR_PK");

                UGW_ACTTMsg inMsg = new UGW_ACTTMsg();
                setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
                setValue(inMsg.ugwActCd, ugwActCd);
                UGW_ACTTMsg outMsg = (UGW_ACTTMsg) EZDTBLAccessor.findByKey(inMsg);

                // Set Register
                if (ZYPConstant.FLG_ON_Y.equals(outMsg.ugwRgtnFlg.getValue())) {

                    NSZC049001 api = new NSZC049001();
                    // S21ApiMessage msg = new S21ApiMessage();
                    String msgId = null;
                    String msgStr = null;

                    getSvcMachMstr(cMsg.glblCmpyCd.getValue(), svcMachMstrPk);

                    NSZC049001PMsg pMsg = new NSZC049001PMsg();
                    setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
                    setValue(pMsg.slsDt, cMsg.slsDt);
                    setValue(pMsg.usrId, getContextUserInfo().getUserId());
                    setValue(pMsg.svcCallFlg, ZYPConstant.FLG_ON_Y);
                    setValue(pMsg.svcMachMstrPk, svcMachMstrPk);

                    api.execute(pMsg, ONBATCH_TYPE.ONLINE);

                    if (S21ApiUtil.isXxMsgId(pMsg)) {
                        boolean isErr = false;
                        for (S21ApiMessage msg : S21ApiUtil.getXxMsgList(pMsg)) {
                            msgId = msg.getXxMsgid();

                            if (msgId.endsWith("E")) {
                                isErr = true;
                                break;
                            }
                        }
                        msgStr = S21MessageFunc.clspGetMessage(msgId);

                        setValue(outFMsg.xxMsgTxt_A, msgStr);

                        if (isErr) {
                            EZDConnectionMgr.getInstance().rollback();
                            setValue(outFMsg.ugwActStsDescTxt_A, ERROR);
                        } else {
                            EZDConnectionMgr.getInstance().commit();
                            setValue(outFMsg.ugwActStsDescTxt_A, WARNING);
                        }
                    } else {
                        EZDConnectionMgr.getInstance().commit();
                        setValue(outFMsg.ugwActStsDescTxt_A, SUCCESS);
                    }
                    csvOutFile.write();

                    // Set De-Register
                } else if (ZYPConstant.FLG_ON_Y.equals(outMsg.ugwDeinsFlg.getValue())) {

                    NSZC050001 api = new NSZC050001();
                    String msgId = null;
                    String msgStr = null;

                    getSvcMachMstr(cMsg.glblCmpyCd.getValue(), svcMachMstrPk);

                    NSZC050001PMsg pMsg = new NSZC050001PMsg();
                    setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
                    setValue(pMsg.slsDt, cMsg.slsDt);
                    setValue(pMsg.svcMachMstrPk, svcMachMstrPk);

                    api.execute(pMsg, ONBATCH_TYPE.ONLINE);
                    if (S21ApiUtil.isXxMsgId(pMsg)) {
                        boolean isErr = false;
                        for (S21ApiMessage msg : S21ApiUtil.getXxMsgList(pMsg)) {
                            msgId = msg.getXxMsgid();

                            if (msgId.endsWith("E")) {
                                isErr = true;
                                break;
                            }
                        }
                        msgStr = S21MessageFunc.clspGetMessage(msgId);

                        setValue(outFMsg.xxMsgTxt_A, msgStr);

                        if (isErr) {
                            EZDConnectionMgr.getInstance().rollback();
                            setValue(outFMsg.ugwActStsDescTxt_A, ERROR);
                        } else {
                            EZDConnectionMgr.getInstance().commit();
                            setValue(outFMsg.ugwActStsDescTxt_A, WARNING);
                        }
                    } else {
                        EZDConnectionMgr.getInstance().commit();
                        setValue(outFMsg.ugwActStsDescTxt_A, SUCCESS);
                    }
                    csvOutFile.write();
                }
            }
            if (mappedFile.getReadRecCount() <= HEADER_LINE_CONST) {
                cMsg.setMessageInfo(ZYEM0004E);
            }
        } finally {

            mappedFileForCountCheck.close();
            mappedFile.close();
            csvOutFile.close();
        }
    }

    private SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();

        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcMachMstrPk, svcMachMstrPk);

        return (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
    }
}
