package com.canon.cusa.s21.api.NWZ.test;

import java.math.BigDecimal;

import parts.common.EZDDebugOutput;
import business.parts.NWZC403001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC403001.NWZC403001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiTestDriver;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * Main method Test program
 */
public class NWZC403001Test extends S21ApiTestDriver {

    /**
     * Main method
     * @param arg something item
     */
    public static void main(String[] arg) {
        NWZC403001Test api = new NWZC403001Test();

        api.initBatch("NWZC403001", null, null, "ABR");
        api.test();
    }

    private void test() {

        NWZC403001 api = new NWZC403001();
        NWZC403001PMsg param = new NWZC403001PMsg();

        // enter parameters here
        String glblCmpyCd = "EXM";

        param.glblCmpyCd.setValue(glblCmpyCd);

        param.xxModeCd.setValue("09");
        param.vndRtrnNum.setValue("VR000017");

//      param.vndRtrnCancDt.setValue("20091108");


        for (int row = 0; row < 999; row++) {
            param.List.no(row).trxLineNum.setValue(String.valueOf(row + 1));
//            if (row != 998) {
                param.List.no(row).trxLineSubNum.setValue(String.valueOf(row + 1));
//            }
            param.List.no(row).vndRtrnDtlCancDt.setValue("20091105");
//            param.List.no(row).vndRtrnDtlCloDt.setValue("20091017");
//            param.List.no(row).vndRtrnSubmtDt_D1.setValue("20091001");
//            param.List.no(row).mdseCd.setValue("1234567890123456");
//            param.List.no(row).mdseNm.setValue("098765432109876543210987654321");
//            param.List.no(row).avalQty.setValue(BigDecimal.valueOf(1234567890));
//            param.List.no(row).rtrnQty.setValue(BigDecimal.valueOf(1134567890));
//            param.List.no(row).exchRate.setValue(BigDecimal.valueOf(1234.56789));
//            param.List.no(row).locStsCd.setValue("22");
//            param.List.no(row).stkStsCd.setValue("9");
//            param.List.no(row).uomCd.setValue("4444");
//            param.List.no(row).invNum.setValue("1234567890123");
//            param.List.no(row).slsRepOrSlsTeamTocCd_D1.setValue("88888888");
//            param.List.no(row).usTaxCd_D1.setValue("ABCDEF");
//            param.List.no(row).funcCcyCd.setValue("FFF");
//            param.List.no(row).dealCcyCd_D1.setValue("NDD");
//            param.List.no(row).dealVndRtrnUnitPrcAmt.setValue(BigDecimal.valueOf(123456789012345.6788));
//            param.List.no(row).origVndRtrnUnitPrcAmt.setValue(BigDecimal.valueOf(123456789012345.6787));
//            param.List.no(row).coaAcctCd.setValue("87654321");
//            param.List.no(row).trxCd.setValue("XRT");
//            param.List.no(row).trxRsnCd.setValue("DC");
//            param.List.no(row).exptLicRqstTpCd.setValue("9");
//            param.List.no(row).exptLicNum.setValue("9876543210");
//            param.List.no(row).exptLicExprDt.setValue("20091011");
//            param.List.no(row).exptGenlLicSymCd.setValue("1234567890");
//            param.List.no(row).eccnNum.setValue("123456789012345678901234567890");
//            param.List.no(row).schdBNum.setValue("98765432109");
//            param.List.no(row).rwsNum.setValue("88880000");
//            param.List.no(row).rwsDtlLineNum.setValue("333");
//            param.List.no(row).inbdSrcTpCd.setValue("22");
//            param.List.no(row).rwsWhInEtaDt.setValue("20091015");
//            param.List.no(row).vndRtrnDtlHldFlg.setValue("N");
        }

        param.List.setValidCount(999);

        api.execute(param, ONBATCH_TYPE.BATCH);

        if (!ZYPCommonFunc.hasValue(param.xxMsgIdList.no(0).xxMsgId.getValue())) {
            commit();
        } else {
            rollback();
            if (EZDDebugOutput.isDebug()) {
                EZDDebugOutput.println(1, "[NWZC403001] setMsgId:" + param.xxMsgIdList.no(0).xxMsgId.getValue(), this);
                EZDDebugOutput.println(1, "[NWZC403001] VND_RTRN_NUM:" + param.vndRtrnNum.getValue(), this);
                EZDDebugOutput.println(1, "[NWZC403001] TRX_LINE_NUM:" + param.List.no(0).trxLineNum.getValue(), this);
                EZDDebugOutput.println(1, "[NWZC403001] TRX_LINE_SUB_NUM:" + param.List.no(0).trxLineSubNum.getValue(), this);
            }
        }
    }

}
