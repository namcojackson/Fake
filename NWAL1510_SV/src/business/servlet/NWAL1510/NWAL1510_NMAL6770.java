/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1510;

import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.setTabProtect;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BUSINESS_ID;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.CMN_CLOSE;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_1;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_13;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_14;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_19;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_6;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_7;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_8;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_9;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDItemAttribute;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1510.NWAL1510CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1510_NMAL6770
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Fujitsu         S.Ohki          Create          N/A
 * 2015/11/20   Fujitsu         T.ishii         Update          S21_NA#999
 * 2016/10/21   Fujitsu         M.Ohno          Update          S21_NA#15354
 * 2017/08/08   Fujitsu         H.Nagashima     Update          S21_NA#16452
 * 2018/03/09   Fujitsu         A.Kosai         Update          S21_NA#22387
 *</pre>
 */
public class NWAL1510_NMAL6770 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // QC#16452 mod Start
//        return null;
        if (CMN_CLOSE.equals(getLastGuard())) {
            return null;
        }
        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;
        int slctLine = getButtonSelectNumber();
        scrnMsg.xxCellIdx_CO.setValue(slctLine);

        NWAL1510CMsg bizMsg = new NWAL1510CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // QC#16452 mod End
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;
        NWAL1510CMsg bizMsg = (NWAL1510CMsg) cMsg;

        if (!CMN_CLOSE.equals(getLastGuard())) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
//            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(POP_PAR_25).xxPopPrm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.xxCellIdx_CO)) {
//                int idx = Integer.parseInt(scrnMsg.P.no(POP_PAR_25).xxPopPrm.getValue());
                int idx = scrnMsg.xxCellIdx_CO.getValue().intValue();
                // S21_NA#999 modify start
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).dsCtacPntPk_C0, scrnMsg.P.no(POP_PAR_19).dsCtacPntPk);
                // QC#16452 del Start
//                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).ctacPsnPk_C0, scrnMsg.P.no(POP_PAR_19).dsCtacPntPk); // Add Start 2016/10/21 M.Ohno S21_NA#15354
                // QC#16452 del End
                // 2018/03/09 S21_NA#22387 Del Start
//                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).ctacPsnTpCd_C0, leftString(scrnMsg.P.no(POP_PAR_1).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL1510Bean.ctacPsnTpCd_C0));
                // 2018/03/09 S21_NA#22387 Del End
                // QC#16452 del Start
//                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).dsAcctNum_C0, leftString(scrnMsg.P.no(POP_PAR_3).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL1510Bean.dsAcctNum_C0));
//                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).locNum_C0, leftString(scrnMsg.P.no(POP_PAR_4).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL1510Bean.locNum_C0));
                // QC#16452 del End
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).ctacPsnFirstNm_C0, leftString(scrnMsg.P.no(POP_PAR_6).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL1510Bean.ctacPsnFirstNm_C0));
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).ctacPsnLastNm_C0, leftString(scrnMsg.P.no(POP_PAR_7).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL1510Bean.ctacPsnLastNm_C0));
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).ctacPsnTelNum_C0, leftString(scrnMsg.P.no(POP_PAR_8).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL1510Bean.ctacPsnTelNum_C0));
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).ctacPsnExtnNum_C0, leftString(scrnMsg.P.no(POP_PAR_13).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL1510Bean.ctacPsnExtnNum_C0));
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).ctacPsnEmlAddr_C0, leftString(scrnMsg.P.no(POP_PAR_9).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL1510Bean.ctacPsnEmlAddr_C0));
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).ctacPsnFaxNum_C0, leftString(scrnMsg.P.no(POP_PAR_14).xxPopPrm.getValue(), scrnMsg.C.no(idx), NWAL1510Bean.ctacPsnFaxNum_C0));
                // S21_NA#999 modify end
                setTabProtect(this, scrnMsg);   // QC#16452 add
                scrnMsg.setFocusItem(scrnMsg.C.no(idx).ctacPsnTpCd_C0);
                //QC#16452 add start
                if (scrnMsg.C.no(idx).ctacPsnTpCd_C0.isInputProtected()) {
                    scrnMsg.setFocusItem(scrnMsg.C.no(idx).ctacPsnTelNum_C0);
                }
                //QC#16452 add End
            }
        }
    }

    // S21_NA#999 add start
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
    // S21_NA#999 add end
}
