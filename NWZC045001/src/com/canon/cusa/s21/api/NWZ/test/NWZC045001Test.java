package com.canon.cusa.s21.api.NWZ.test;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDDebugOutput;
import business.parts.NWZC045001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC045001.NWZC045001;
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
 * 01/20/2010   Canon           M.Nakamura      Update          N/A
 * 
 * </pre>
 */
public class NWZC045001Test extends S21ApiTestDriver {

    public static void main(String[] args) {

        EZDDebugOutput.clearEJBDebugLevel();
        EZDDebugOutput.setEJBDebugLevel(new Integer(1));

        NWZC045001Test api = new NWZC045001Test();
        api.initBatch("NWZC046001", null, null, "ABR");

//        api.test();
        // api.testPmsg();
        // api.isGlobalCompanyCode();
        // api.checkMerchandiseCode();
        // api.checkMdseStructure();
        // api.checkCustomerPaymentTermData();
        api.listTest();
    }

    private void listTest() {
        NWZC045001 api = new NWZC045001();
        NWZC045001PMsg pmsg1 = new NWZC045001PMsg();
        NWZC045001PMsg pmsg2 = new NWZC045001PMsg();
        NWZC045001PMsg pmsg3 = new NWZC045001PMsg();
        List<NWZC045001PMsg> list = new ArrayList<NWZC045001PMsg>();
        pmsg1.glblCmpyCd.setValue("ABR");
        pmsg1.mdseCd.setValue("2657A002AA");
        pmsg1.billToCustCd.setValue("U402");
        list.add(pmsg1);
        pmsg2.glblCmpyCd.setValue("ABR");
        pmsg2.mdseCd.setValue("FLD-SSHOT");
        pmsg2.billToCustCd.setValue("U402");
        list.add(pmsg2);
        pmsg3.glblCmpyCd.setValue("ABR");
        pmsg3.mdseCd.setValue("2030A009AA");
        pmsg3.billToCustCd.setValue("U402");       
        list.add(pmsg3);
        api.execute(list, S21ApiInterface.ONBATCH_TYPE.ONLINE);
        debugln("Line1: " + ((NWZC045001PMsg) (list.get(0))).pmtTermCd.getValue() + " - " + ((NWZC045001PMsg) (list.get(0))).cashDiscTermCd.getValue());
        debugln("Line2: " + ((NWZC045001PMsg) (list.get(1))).pmtTermCd.getValue() + " - " + ((NWZC045001PMsg) (list.get(1))).cashDiscTermCd.getValue());
        debugln("Line3: " + ((NWZC045001PMsg) (list.get(2))).pmtTermCd.getValue() + " - " + ((NWZC045001PMsg) (list.get(2))).cashDiscTermCd.getValue());
        
    }

    public void test() {
        NWZC045001PMsg pmsg = new NWZC045001PMsg();
        NWZC045001 api = new NWZC045001();

        try {
            pmsg.glblCmpyCd.setValue("ABR");
            // pmsg.mdseCd.setValue("2657A002AA");
            // pmsg.mdseCd.setValue("1546A011");
            pmsg.zerothProdCtrlCd.setValue("02");
            // pmsg.firstProdCtrlCd.setValue("A");
            // pmsg.scdProdCtrlCd.setValue("A1");
            // pmsg.shipToCustCd.setValue("US0135");
            // pmsg.sellToCustCd.setValue("U402A1");
            pmsg.billToCustCd.setValue("U402");

            api.execute(pmsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);

            
            // commit();
        } catch (Exception e) {
            // rollback();
            e.printStackTrace();
        }
    }

    public void debugln(String msg) {
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, msg, this);
        }
    }

    public void isGlobalCompanyCode() {
        // NWZC045001PMsg pmsg = new NWZC045001PMsg();
        // NWZC045001 api = new NWZC045001();
        //
        // try {
        // // pmsg.glblCmpyCd.setValue("ABR");
        // // //pmsg.glblCmpyCd.setValue("AB1");
        // // pmsg.mdseCd.setValue("2657A002AA");
        // // pmsg.zerothProdCtrlCd.setValue("");
        // // pmsg.firstProdCtrlCd.setValue("");
        // // pmsg.scdProdCtrlCd.setValue("");
        // // pmsg.shipToCustCd.setValue("");
        // // pmsg.sellToCustCd.setValue("");
        // // pmsg.billToCustCd.setValue("");
        //
        // // api.execute(pmsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);
        // // boolean rtn = api.isGlobalCompanyCode("ABR");
        // // boolean rtn = api.isGlobalCompanyCode("AB1");
        // // this.debugln("isGlobalCompanyCode =" + rtn);
        //
        //            // commit();
        //        } catch (Exception e) {
        //            // rollback();
        //            e.printStackTrace();
        //        }
    }

    public void checkMerchandiseCode() {
        NWZC045001PMsg pmsg = new NWZC045001PMsg();
        NWZC045001 api = new NWZC045001();

        try {
            pmsg.glblCmpyCd.setValue("ABR");
            // pmsg.mdseCd.setValue("2657A002AA");
            pmsg.mdseCd.setValue("1546A011");
            // pmsg.mdseCd.setValue("2764A002AA");
            // pmsg.mdseCd.setValue("Z657A002AA");
            pmsg.zerothProdCtrlCd.setValue("");
            pmsg.firstProdCtrlCd.setValue("");
            pmsg.scdProdCtrlCd.setValue("");
            pmsg.shipToCustCd.setValue("");
            pmsg.sellToCustCd.setValue("");
            pmsg.billToCustCd.setValue("");

            api.execute(pmsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);

            // commit();
        } catch (Exception e) {
            // rollback();
            e.printStackTrace();
        }
    }

    public void checkMdseStructure() {
        NWZC045001PMsg pmsg = new NWZC045001PMsg();
        NWZC045001 api = new NWZC045001();

        try {
            pmsg.glblCmpyCd.setValue("ABR");
            // pmsg.mdseCd.setValue("2657A002AA");
            // pmsg.mdseCd.setValue("1546A011");
            pmsg.zerothProdCtrlCd.setValue("");
            pmsg.firstProdCtrlCd.setValue("A");
            pmsg.scdProdCtrlCd.setValue("");
            pmsg.shipToCustCd.setValue("");
            pmsg.sellToCustCd.setValue("");
            pmsg.billToCustCd.setValue("");

            api.execute(pmsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);

            // commit();
        } catch (Exception e) {
            // rollback();
            e.printStackTrace();
        }
    }

    public void checkCustomerPaymentTermData() {
        /*NWZC045001 api = new NWZC045001();

         try {
         String glblCmpyCd = "ABR";
         String zerothProdCtrlCd = "02";// 02
         String firstProdCtrlCd = "A";// A
         String scdProdCtrlCd = "A1";// A1
         String billToCustCd = "US0135";// US0135
         String sellToCustCd = "U402A1";// U402A1

         // List rtnList =
         // api.getCustomerPaymentTermData(glblCmpyCd,
         // zerothProdCtrlCd, firstProdCtrlCd, scdProdCtrlCd,
         // billToCustCd, sellToCustCd);
         // this.debugln("rtnList =" + rtnList);

         // commit();
         } catch (Exception e) {
         // rollback();
         e.printStackTrace();
         }*/
    }
}
