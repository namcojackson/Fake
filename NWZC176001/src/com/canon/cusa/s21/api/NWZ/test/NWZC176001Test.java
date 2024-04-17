/**
 *  <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.test;

import parts.common.EZDDebugOutput;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.DS_CPO_CONFIGTMsg;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC176001.NWZC176001;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiTestDriver;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.internal.calendar.S21DateManagement;

/**
 * NWZC176001 Test Class
 * @author q09388
 */
public class NWZC176001Test extends S21ApiTestDriver {

    /**
     * Main
     * @param arg Input Parameters
     */
    public static void main(String[] arg) {
        NWZC176001Test apiTest = new NWZC176001Test();
        // EZDフレームワークを初期化します。
        apiTest.initBatch("NWZC176001", null, null, "ADB");
        apiTest.test();
    }

    private void test() {

        String glblCmpyCd = "ADB";
//        String cpoOrdNum = "TEST0001";
        String cpoOrdNum = "20001809";

        NWZC176001 api = new NWZC176001();

        // CPOTMsg
        CPOTMsg cpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, cpoOrdNum);
        cpoTMsg = (CPOTMsg) S21CacheTBLAccessor.findByKey(cpoTMsg);

        // CPO_DTLTMsg
        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        cpoDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        cpoDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        cpoDtlTMsg.setSQLID("001");

        CPO_DTLTMsgArray cpoDtlTMsgArray = (CPO_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(cpoDtlTMsg);
        if (cpoDtlTMsgArray != null && cpoDtlTMsgArray.length() > 0) {
            cpoDtlTMsg = cpoDtlTMsgArray.no(0);
        }

//        // DS_CPOTMsg
//        DS_CPOTMsg dsCoTMsg = new DS_CPOTMsg();
//        ZYPEZDItemValueSetter.setValue(dsCoTMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(dsCoTMsg.cpoOrdNum, cpoOrdNum);
//        dsCoTMsg = (DS_CPOTMsg) S21CacheTBLAccessor.findByKey(dsCoTMsg);
//
//        // DS_CPO_DTLTMsg
//        DS_CPO_DTLTMsg dsCoDtlTMsg = new DS_CPO_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(dsCoDtlTMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(dsCoDtlTMsg.cpoOrdNum, cpoDtlTMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(dsCoDtlTMsg.cpoDtlLineNum, cpoDtlTMsg.cpoDtlLineNum);
//        ZYPEZDItemValueSetter.setValue(dsCoDtlTMsg.cpoDtlLineSubNum, cpoDtlTMsg.cpoDtlLineSubNum);
//        dsCoDtlTMsg = (DS_CPO_DTLTMsg) S21CacheTBLAccessor.findByKey(dsCoDtlTMsg);

        DS_CPO_CONFIGTMsg dsCoConfTMsg = new DS_CPO_CONFIGTMsg();

        NWXC005001PMsg pMsg = new NWXC005001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum_I, cpoDtlTMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineNum_I, cpoDtlTMsg.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineSubNum_I, cpoDtlTMsg.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, S21DateManagement.getBatProcDate(glblCmpyCd));

        NWXC005001ValidationBean bean = new NWXC005001ValidationBean(pMsg, cpoTMsg, cpoDtlTMsg, null, dsCoConfTMsg);

        try {
            api.execute(bean, S21ApiInterface.ONBATCH_TYPE.BATCH);

            // TODO : del
            EZDDebugOutput.println(1, pMsg.toString(), this);

            if (!S21ApiUtil.isXxMsgId(pMsg)) {
                return;
            }
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }

    }

}
