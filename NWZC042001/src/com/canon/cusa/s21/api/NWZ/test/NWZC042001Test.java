package com.canon.cusa.s21.api.NWZ.test;

import parts.common.EZDDebugOutput;
import business.parts.NWZC042001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC042001.NWZC042001;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiTestDriver;

/**
 * <pre>                                                                                                                        
 * 
 * This is deriving default warehouse API test driver.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/23/2009   Canon           H.Sakamoto      Create          N/A       
 * 
 * </pre>
 */
public class NWZC042001Test extends S21ApiTestDriver {

    public static void main(String[] args) {

        EZDDebugOutput.clearEJBDebugLevel();
        EZDDebugOutput.setEJBDebugLevel(new Integer(1));

        NWZC042001Test api = new NWZC042001Test();
        api.initBatch("NWZC042001", null, null, "ABR");

        api.test();
        // api.testPmsg();
        // api.isGlobalCompanyCode();
        // api.checkMerchandiseCode();
        // api.checkMdseStructure();
        // api.checkCustomerPaymentTermData();
    }

    public void test() {
        NWZC042001PMsg pmsg = new NWZC042001PMsg();
        NWZC042001 api = new NWZC042001();

        try {
            pmsg.glblCmpyCd.setValue("ABR");
            pmsg.cpoOrdNum.setValue("CP000240");
            pmsg.A.no(0).txtTpCd.setValue("10");
            pmsg.A.no(0).msgTxtInfoTxt.setValue("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA65");
            pmsg.A.setValidCount(1);
            
            api.execute(pmsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);

            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
    }

    public void debugln(String msg) {
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, msg, this);
        }
    }


}
