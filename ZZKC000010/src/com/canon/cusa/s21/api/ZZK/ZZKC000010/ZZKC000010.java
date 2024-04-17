package com.canon.cusa.s21.api.ZZK.ZZKC000010;

import java.util.List;
import java.util.UUID;

import parts.dbcommon.EZDTBLAccessor;

import business.db.RPT_JOB_WRKTMsg;
import business.parts.ZZKC000010PMsg;
import business.parts.ZZKC000010_xxDateListPMsgArray;
import business.parts.ZZKC000010_xxMsgIdListPMsgArray;
import business.parts.ZZKC000010_xxTestListPMsgArray;

import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * Mobile Test Api
 * @author Q04877
 *
 */
public class ZZKC000010 extends S21ApiCommonBase {

    public ZZKC000010() {
        super();
    }
    
    public void execute(final ONBATCH_TYPE onBatchType){
        return;
    }

    public void execute(final ZZKC000010PMsg param1, final ZZKC000010PMsg param2, final ONBATCH_TYPE onBatchType) {
        S21InfoLogOutput.println("ZZKC000010PMsg, ZZKC000010PMsg");
        execApi(param1, onBatchType);
        execApi(param2, onBatchType);
    }
    
    public void execute(final List<ZZKC000010PMsg> plist, final ONBATCH_TYPE onBatchType) {
        S21InfoLogOutput.println("List<ZZKC000010PMsg>");
        int len = plist.size();
        for (int i = 0; i < len; i++) {
            execApi(plist.get(i), onBatchType);
        }
    }

    public void execute(final List<ZZKC000010PMsg> plist, final ZZKC000010PMsg param, final ONBATCH_TYPE onBatchType) {
        S21InfoLogOutput.println("List<ZZKC000010PMsg>, ZZKC000010PMsg");
        int len = plist.size();
        for (int i = 0; i < len; i++) {
            execApi(plist.get(i), onBatchType);
        }
        execApi(param, onBatchType);
    }

    public void execute(final ZZKC000010PMsg param, final List<ZZKC000010PMsg> plist, final ONBATCH_TYPE onBatchType) {
        S21InfoLogOutput.println("ZZKC000010PMsg, List<ZZKC000010PMsg>");
        execApi(param, onBatchType);
        int len = plist.size();
        for (int i = 0; i < len; i++) {
            execApi(plist.get(i), onBatchType);
        }
    }

    public void execute(final List<ZZKC000010PMsg> plist1, final List<ZZKC000010PMsg> plist2, final ONBATCH_TYPE onBatchType) {
        S21InfoLogOutput.println("List<ZZKC000010PMsg>, List<ZZKC000010PMsg>");
        int len = plist1.size();
        for (int i = 0; i < len; i++) {
            execApi(plist1.get(i), onBatchType);
        }
        int len2 = plist2.size();
        for (int i = 0; i < len2; i++) {
            execApi(plist2.get(i), onBatchType);
        }
    }

    public void execute(final ZZKC000010PMsg param, final ONBATCH_TYPE onBatchType) {
        S21InfoLogOutput.println("ZZKC000010PMsg");
        execApi(param, onBatchType);
    }
    
    public void executeS21Abend(final ZZKC000010PMsg param, final ONBATCH_TYPE onBatchType) {
        throw new S21AbendException("Test S21AbendException");
    }
    
    public void executeException(final ZZKC000010PMsg param, final ONBATCH_TYPE onBatchType) throws Exception {
        throw new Exception("Test Exception");
    }
    
    public void executeRuntimeException(final ZZKC000010PMsg param, final ONBATCH_TYPE onBatchType) throws Exception {
        throw new RuntimeException("Test RuntimeException");
    }

    private void execApi(final ZZKC000010PMsg param, final ONBATCH_TYPE onBatchType) {
        printDateList(param.xxDateList);
        printMsgIdList(param.xxMsgIdList);
        printTestList(param.xxTestList);
        String ezServerCallTime = (param.ezServerCallTime.getValue() != null && !"".equals(param.ezServerCallTime.getValue())) ? param.ezServerCallTime.getValue() : "ezServerCallTime is not set";
        String glblCmpyCd = (param.glblCmpyCd.getValue() != null && !"".equals(param.glblCmpyCd.getValue())) ? param.glblCmpyCd.getValue() : "glblCmpyCd is not set";
//        S21InfoLogOutput.println("#" + ezServerCallTime + "#");
//        S21InfoLogOutput.println("#" + glblCmpyCd + "#");
        
        RPT_JOB_WRKTMsg tmsg = new RPT_JOB_WRKTMsg();
        
        String uniqueID = UUID.randomUUID().toString();
        String ts = String.valueOf(System.currentTimeMillis());
        
        String uniqProcessId = ts + uniqueID;
        uniqProcessId = uniqProcessId.substring(0, 28);
        tmsg.rptJobProcId.setValue(uniqProcessId);
        tmsg.rptJobId.setValue(ezServerCallTime);
        tmsg.glblCmpyCd.setValue("ADB");
        EZDTBLAccessor.create(tmsg);
        S21InfoLogOutput.println("#" + "Create RPT_JOB_WRK Table by ZZKC000010" + "#");
        S21InfoLogOutput.println("#" + "rptJobProcId" + "#" + uniqProcessId);
        S21InfoLogOutput.println("#" + "rptJobId" + "#" + ezServerCallTime);
    }
    
    public void _hookError(final ONBATCH_TYPE onBatchType){
        S21InfoLogOutput.println("hookError : No args");
        return;
    }

    public void _hookError(final ZZKC000010PMsg param1, final ZZKC000010PMsg param2, final ONBATCH_TYPE onBatchType) {
        S21InfoLogOutput.println("hookError : ZZKC000010PMsg, ZZKC000010PMsg");
    }
    
    public void _hookError(final List<ZZKC000010PMsg> plist, final ONBATCH_TYPE onBatchType) {
        S21InfoLogOutput.println("hookError : List<ZZKC000010PMsg>");
    }

    public void _hookError(final List<ZZKC000010PMsg> plist, final ZZKC000010PMsg param, final ONBATCH_TYPE onBatchType) {
        S21InfoLogOutput.println("hookError : List<ZZKC000010PMsg>, ZZKC000010PMsg");
    }

    public void _hookError(final ZZKC000010PMsg param, final List<ZZKC000010PMsg> plist, final ONBATCH_TYPE onBatchType) {
        S21InfoLogOutput.println("hookError : ZZKC000010PMsg, List<ZZKC000010PMsg>");
    }

    public void _hookError(final List<ZZKC000010PMsg> plist1, final List<ZZKC000010PMsg> plist2, final ONBATCH_TYPE onBatchType) {
        S21InfoLogOutput.println("hookError : List<ZZKC000010PMsg>, List<ZZKC000010PMsg>");
    }

    public void _hookError(final ZZKC000010PMsg param, final ONBATCH_TYPE onBatchType) {
        S21InfoLogOutput.println("hookError : ZZKC000010PMsg");
        execApi(param, onBatchType);
    }
    
    

    private void printDateList(ZZKC000010_xxDateListPMsgArray list) {
        String key = "ezServerCallTime";
        for (int i = 0; i < list.getValidCount(); i++) {
            S21InfoLogOutput.println("DateList " + i + "#" + list.get(i).getValueString(key, -1) + "#");
        }
    }

    private void printMsgIdList(ZZKC000010_xxMsgIdListPMsgArray list) {
        String key = "xxMsgId";
        for (int i = 0; i < list.getValidCount(); i++) {
            S21InfoLogOutput.println("MsgIdList " + i + "#" + list.get(i).getValueString(key, 0) + "#");
        }
    }

    private void printTestList(ZZKC000010_xxTestListPMsgArray list) {
        String key = "xxScrItem19Txt";
        for (int i = 0; i < list.getValidCount(); i++) {
            S21InfoLogOutput.println("TestList " + i + "#" + list.get(i).getValueString(key, 0) + "#");
        }
    }
}
