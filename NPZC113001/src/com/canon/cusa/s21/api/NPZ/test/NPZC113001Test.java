package com.canon.cusa.s21.api.NPZ.test;

import parts.common.EZDDebugOutput;
import business.parts.NPZC113001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC113001.NPZC113001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTestDriver;
import com.canon.cusa.s21.framework.api.S21ApiUtil;

/**
 * <pre>
 * Test Driver for NPZC113001.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/18   CITS            M.Ouchi         Create          N/A
 * </pre>
 */
public class NPZC113001Test extends S21ApiTestDriver {

    /**
     * <p>
     * Execution method.
     * </p>
     * @param args none
     */
    public static void main(String[] args) {

        NPZC113001Test test = new NPZC113001Test();

        test.initBatch("NPZC113001", null, null, "ADB");
        TestEnvContext context = new TestEnvContext();
        context.setEzCmpyCd("ADB");
        test.init(context);

        test.test01();
        test.test02();
        test.test03();
        test.test04();
        test.test05();
        test.test06();
        test.test07();
        test.test08();
    }

    private void test(NPZC113001PMsg pMsg) {
        try {
            new NPZC113001().execute(pMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);
            // Logging.
            EZDDebugOutput.println(1, "---------- Returned Value ---------- ", this);
            EZDDebugOutput.println(1, "glblCmpyCd   :" + pMsg.glblCmpyCd.getValue(), this);
            EZDDebugOutput.println(1, "slsDt        :" + pMsg.slsDt.getValue(), this);
            EZDDebugOutput.println(1, "shipToCustCd :" + pMsg.shipToCustCd.getValue(), this);
            EZDDebugOutput.println(1, "mdseCd       :" + pMsg.mdseCd.getValue(), this);
            EZDDebugOutput.println(1, "vndCd        :" + pMsg.vndCd.getValue(), this);
            EZDDebugOutput.println(1, "unitPrcAmt   :" + pMsg.unitPrcAmt.getValue(), this);
            EZDDebugOutput.println(1, "xxErrFlg     :" + pMsg.xxErrFlg.getValue(), this);
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                for (S21ApiMessage msg : S21ApiUtil.getXxMsgList(pMsg)) {
                    EZDDebugOutput.println(1, msg.getXxMsgid() + ":" + msg.getXxMsgPrmList(), this);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Case 01 : Specify Big Deal#.
     */
    private void test01() {
        EZDDebugOutput.println(1, ">>>>>>>>>>>>>> test01 start. >>>>>>>>>>>>>", this);
        NPZC113001PMsg pMsg = new NPZC113001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, "ADB");
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, "20150801");
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, "0000A001OM");
        test(pMsg);
        EZDDebugOutput.println(1, "<<<<<<<<<<<<<< test01 end  . <<<<<<<<<<<<<<", this);
    }

    /**
     * Case 02 : Specify Ship To Customer Code.
     */
    private void test02() {
        EZDDebugOutput.println(1, ">>>>>>>>>>>>>> test02 start. >>>>>>>>>>>>>", this);
        NPZC113001PMsg pMsg = new NPZC113001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, "ADB");
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, "20150801");
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, "0000A001OM");
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, "100");
        test(pMsg);
        EZDDebugOutput.println(1, "<<<<<<<<<<<<<< test02 end  . <<<<<<<<<<<<<<", this);
    }

    /**
     * Case 03 : Specify Vendor Code.
     */
    private void test03() {
        EZDDebugOutput.println(1, ">>>>>>>>>>>>>> test03 start. >>>>>>>>>>>>>", this);
        NPZC113001PMsg pMsg = new NPZC113001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, "ADB");
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, "20150801");
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, "0000A001OM");
        ZYPEZDItemValueSetter.setValue(pMsg.vndCd, "O000102");
        test(pMsg);
        EZDDebugOutput.println(1, "<<<<<<<<<<<<<< test03 end  . <<<<<<<<<<<<<<", this);
    }

    /**
     * Case 04 : Specify Item Number Only.
     */
    private void test04() {
        EZDDebugOutput.println(1, ">>>>>>>>>>>>>> test04 start. >>>>>>>>>>>>>", this);
        NPZC113001PMsg pMsg = new NPZC113001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, "ADB");
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, "20150801");
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, "0000A003OM");
        test(pMsg);
        EZDDebugOutput.println(1, "<<<<<<<<<<<<<< test04 end  . <<<<<<<<<<<<<<", this);
    }

    /**
     * Case 05 : Specify All Parameters.
     */
    private void test05() {
        EZDDebugOutput.println(1, ">>>>>>>>>>>>>> test05 start. >>>>>>>>>>>>>", this);
        NPZC113001PMsg pMsg = new NPZC113001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, "ADB");
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, "20150801");
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, "XXX");
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, "ZZZ");
        ZYPEZDItemValueSetter.setValue(pMsg.vndCd, "WWW");
        test(pMsg);
        EZDDebugOutput.println(1, "<<<<<<<<<<<<<< test05 end  . <<<<<<<<<<<<<<", this);
    }

    /**
     * Case 06 : NOT specify Item Number.
     */
    private void test06() {
        EZDDebugOutput.println(1, ">>>>>>>>>>>>>> test06 start. >>>>>>>>>>>>>", this);
        NPZC113001PMsg pMsg = new NPZC113001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, "ADB");
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, "20150801");
        test(pMsg);
        EZDDebugOutput.println(1, "<<<<<<<<<<<<<< test06 end  . <<<<<<<<<<<<<<", this);
    }

    /**
     * Case 07 : NOT specify Global Company Code.
     */
    private void test07() {
        EZDDebugOutput.println(1, ">>>>>>>>>>>>>> test07 start. >>>>>>>>>>>>>", this);
        NPZC113001PMsg pMsg = new NPZC113001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, "20150801");
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, "TEST");
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, "100");
        ZYPEZDItemValueSetter.setValue(pMsg.vndCd, "100");
        test(pMsg);
        EZDDebugOutput.println(1, "<<<<<<<<<<<<<< test07 end  . <<<<<<<<<<<<<<", this);
    }

    /**
     * Case 08 : NOT specify Sales Date.
     */
    private void test08() {
        EZDDebugOutput.println(1, ">>>>>>>>>>>>>> test08 start. >>>>>>>>>>>>>", this);
        NPZC113001PMsg pMsg = new NPZC113001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, "ADB");
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, "TEST");
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, "100");
        ZYPEZDItemValueSetter.setValue(pMsg.vndCd, "100");
        test(pMsg);
        EZDDebugOutput.println(1, "<<<<<<<<<<<<<< test08 end  . <<<<<<<<<<<<<<", this);
    }
}
