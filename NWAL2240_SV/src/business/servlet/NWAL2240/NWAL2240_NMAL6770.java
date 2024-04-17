/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2240;

import static business.servlet.NWAL2240.constant.NWAL2240Constant.CMN_CLOSE;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_1;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_13;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_14;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_19;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_25;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_3;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_4;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_6;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_7;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_8;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_9;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDItemAttribute;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWAL2240_NMAL6770 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2240BMsg scrnMsg = (NWAL2240BMsg) bMsg;

        if (!CMN_CLOSE.equals(getLastGuard())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(POP_PAR_25).xxPopPrm)) {
                int idx = Integer.parseInt(scrnMsg.P.no(POP_PAR_25).xxPopPrm.getValue());

                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).dsCtacPntPk_C0, scrnMsg.P.no(POP_PAR_19).dsCtacPntPk);
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).ctacPsnTpCd_C0, leftString(scrnMsg.P.no(POP_PAR_1).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL2240Bean.ctacPsnTpCd_C0));
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).dsAcctNum_C0, leftString(scrnMsg.P.no(POP_PAR_3).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL2240Bean.dsAcctNum_C0));
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).locNum_C0, leftString(scrnMsg.P.no(POP_PAR_4).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL2240Bean.locNum_C0));
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).ctacPsnFirstNm_C0, leftString(scrnMsg.P.no(POP_PAR_6).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL2240Bean.ctacPsnFirstNm_C0));
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).ctacPsnLastNm_C0, leftString(scrnMsg.P.no(POP_PAR_7).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL2240Bean.ctacPsnLastNm_C0));
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).ctacPsnTelNum_C0, leftString(scrnMsg.P.no(POP_PAR_8).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL2240Bean.ctacPsnTelNum_C0));
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).ctacPsnExtnNum_C0, leftString(scrnMsg.P.no(POP_PAR_13).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL2240Bean.ctacPsnExtnNum_C0));
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).ctacPsnEmlAddr_C0, leftString(scrnMsg.P.no(POP_PAR_9).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL2240Bean.ctacPsnEmlAddr_C0));
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).ctacPsnFaxNum_C0, leftString(scrnMsg.P.no(POP_PAR_14).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL2240Bean.ctacPsnFaxNum_C0));
                scrnMsg.setFocusItem(scrnMsg.C.no(idx).ctacPsnTpCd_C0);
            }
        }
    }

    private static String leftString(String originlVlue, EZDBMsg bMsg, String itemName) {
        EZDItemAttribute attr = bMsg.getAttr(itemName);
        if (attr != null) {
            return leftString(originlVlue, attr.getDigit());
        } else {
            return originlVlue;
        }
    }

    private static String leftString(String originlVlue, int maxLength) {

        if (S21StringUtil.isEmpty(originlVlue)) {
            return "";
        }
        return ZYPCommonFunc.trimTail(S21StringUtil.subStringByLength(originlVlue, 0, maxLength));
    }
}
