package com.canon.cusa.s21.api.NWZ.test;

import parts.common.EZDDebugOutput;
import business.parts.NWXC005001PMsg;
import business.parts.NWZC017001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC017001.NWZC017001;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiTestDriver;
import com.canon.cusa.s21.framework.api.S21ApiUtil;

/**
 * @author Q03023
 *
 */
public class NZWC017001Test extends S21ApiTestDriver {

    /**
     * @param args
     */
    public static void main(String[] args) {

        NZWC017001Test api = new NZWC017001Test();

        api.initBatch("NWZC017001", null, null, "ABR");

        api.test1();

    }

    public void test1() {

        NWXC005001PMsg pmsg = new NWXC005001PMsg();

        NWZC017001 api = new NWZC017001();

        try {
            pmsg.glblCmpyCd.setValue("ABR");
            pmsg.cpoOrdNum_I.setValue("CP018003");
            pmsg.cpoDtlLineNum_I.setValue("001");
            pmsg.cpoDtlLineSubNum_I.setValue("001");
            pmsg.shpgPlnNum_I.setValue("SHPG1737");
            pmsg.slsDt.setValue("20090720");

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
