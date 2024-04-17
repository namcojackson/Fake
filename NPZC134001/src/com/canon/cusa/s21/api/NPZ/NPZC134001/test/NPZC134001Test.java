package com.canon.cusa.s21.api.NPZ.NPZC134001.test;

import business.parts.NPZC134001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC134001.NPZC134001;
import com.canon.cusa.s21.api.NPZ.NPZC134001.test.NPZC134001Test;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiTestDriver;

public class NPZC134001Test extends S21ApiTestDriver {

    /**
     * @param args
     */
    public static void main(String[] args) {
        NPZC134001Test apiTest = new NPZC134001Test();

        // EZDフレームワークを初期化します。
        apiTest.initBatch("NPZC1340", null, null, "ADB");
        apiTest.test();

    }

    private void test() {

        NPZC134001 api = new NPZC134001();

        NPZC134001PMsg NPZC134001PMsg = new NPZC134001PMsg();

        NPZC134001PMsg.glblCmpyCd.setValue("ADB");
        NPZC134001PMsg.slsDt.setValue("20151216");
        NPZC134001PMsg.xxProcTpCd.setValue("1");
        NPZC134001PMsg.poOrdNum.setValue("VP000179");
        NPZC134001PMsg.xxdrctCpoCratFlg.setValue("Y");
        NPZC134001PMsg.xxsendPoIfCratFlg.setValue("Y");

        api.execute(NPZC134001PMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);

        // commit();
        rollback();

    }
}
