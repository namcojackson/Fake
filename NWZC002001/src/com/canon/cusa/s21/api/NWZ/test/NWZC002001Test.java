package com.canon.cusa.s21.api.NWZ.test;

import business.parts.NWZC002001PMsg;
import com.canon.cusa.s21.api.NWZ.NWZC002001.NWZC002001;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiTestDriver;
import com.canon.cusa.s21.framework.api.S21ApiUtil;

public class NWZC002001Test extends S21ApiTestDriver {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO 自動生成されたメソッド・スタブ
        NWZC002001Test api = new NWZC002001Test();

        api.initBatch("NWZC002001", null, null, "ABR");

        api.test1();

    }

    public void test1() {

        NWZC002001PMsg pmsg = new NWZC002001PMsg();

        NWZC002001 api = new NWZC002001();

        try {
            pmsg.glblCmpyCd.setValue("ADB");
            pmsg.mdseCd.setValue("3609B994AA");
            pmsg.ordQty.setValue(12345);
            pmsg.shpgSvcLvlCd.setValue("04");
            pmsg.frtChrgMethCd.setValue("1");
            pmsg.frtChrgToCd.setValue("1");
            pmsg.xxRddDt.setValue("");
            pmsg.xxRsdDt.setValue("");
            pmsg.xxRwsdDt.setValue("20151004");
            pmsg.invtyLocCd.setValue("P075V");
            pmsg.shipToPostCd.setValue("30093");
            pmsg.shipToCustCd.setValue("A61J");
            pmsg.sellToCustCd.setValue("A62J");
            pmsg.shipToStCd.setValue("NY");
            pmsg.uomCd.setValue("EA");
            pmsg.slsDt.setValue("20151005");
            pmsg.configFlg.setValue("Y");

            api.execute(pmsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);

            if (!S21ApiUtil.isXxMsgId(pmsg)) {
                return;
            }
            //commit();
            rollback();

        } catch (Exception e) {
            //rollback();
            e.printStackTrace();
        }

    }
}
