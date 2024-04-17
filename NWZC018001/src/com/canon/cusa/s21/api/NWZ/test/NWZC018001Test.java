package com.canon.cusa.s21.api.NWZ.test;

import parts.common.EZDDebugOutput;
import business.parts.NWXC005001PMsg;
import business.parts.NWZC018001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC018001.NWZC018001;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiTestDriver;
import com.canon.cusa.s21.framework.api.S21ApiUtil;

/**
 * @author Q03023
 *
 */
public class NWZC018001Test extends S21ApiTestDriver {

    /**
     * @param args String
     */
    public static void main(String[] args) {

        NWZC018001Test api = new NWZC018001Test();

        api.initBatch("NWZC018001", null, null, "ABR");

        api.test1();

    }

    /**
     * Shipping Hold API test<BR>
     */
    public void test1() {

        NWXC005001PMsg pmsg = new NWXC005001PMsg();

        NWZC018001 api = new NWZC018001();

        try {
            pmsg.glblCmpyCd.setValue("ABR");
            pmsg.cpoOrdNum_I.setValue("");
            pmsg.cpoDtlLineNum_I.setValue("");
            pmsg.cpoDtlLineSubNum_I.setValue("");
            pmsg.shpgPlnNum_I.setValue("SHPG1825");
            pmsg.slsDt.setValue("");

            NWXC005001ValidationBean validationBean = new NWXC005001ValidationBean(pmsg, null, null, null);

            // *** debug log start ***//
            EZDDebugOutput.println(1, "[TEST DEBUG]:Msg:" + pmsg.toString(), this);
            // *** debug log end ***//

//            api.execute(pmsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);
            api.execute(validationBean, S21ApiInterface.ONBATCH_TYPE.ONLINE);

            // *** debug log start ***//
            EZDDebugOutput.println(1, "[TEST DEBUG]:Msg:" + pmsg.toString(), this);
            // *** debug log end ***//

            if (!S21ApiUtil.isXxMsgId(pmsg)) {
                return;
            }
            //commit();

        } catch (Exception e) {
            //rollback();
            e.printStackTrace();
        }

    }
}
