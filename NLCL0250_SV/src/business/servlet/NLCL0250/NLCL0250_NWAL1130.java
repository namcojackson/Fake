/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0250;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0250.constant.NLCL0250Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Onhand Inventory Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 * 01/20/2016   CSAI            Y.Imazu         Update          QC#3134
 * 12/18/2017   CITS            S.Katsuma       Update          QC#22469
 *</pre>
 */
public class NLCL0250_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0250BMsg scrnMsg = (NLCL0250BMsg) bMsg;

        // START 2017/12/18 S.Katsuma [QC#22469,ADD]
        if (NLCL0250Constant.EVENT_NM_RTRN_CTRL_TP_LINK.equals(scrnMsg.xxMntEventNm.getValue())) {
            if (!NLCL0250Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnCtrlTpVndRelnPk_H1, new BigDecimal(scrnMsg.Q.no(0).xxComnScrColValTxt_Q.getValue()));
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnCtrlTpCd_H1, scrnMsg.Q.no(1).xxComnScrColValTxt_Q);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnCtrlTpNm_H1, scrnMsg.Q.no(2).xxComnScrColValTxt_Q);
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd_H1, scrnMsg.Q.no(3).xxComnScrColValTxt_Q);
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm_H1, scrnMsg.Q.no(4).xxComnScrColValTxt_Q);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd_H1, scrnMsg.Q.no(5).xxComnScrColValTxt_Q);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndNm_H1, scrnMsg.Q.no(6).xxComnScrColValTxt_Q);

                String xxScrItem500Txt_H1 = null;
                if (ZYPCommonFunc.hasValue(scrnMsg.rtrnCtrlTpNm_H1)) {
                    xxScrItem500Txt_H1 = scrnMsg.rtrnCtrlTpNm_H1.getValue();
                }
                if (ZYPCommonFunc.hasValue(scrnMsg.prntVndNm_H1)) {
                    if (ZYPCommonFunc.hasValue(xxScrItem500Txt_H1)) {
                        xxScrItem500Txt_H1 = xxScrItem500Txt_H1 + "/" + scrnMsg.prntVndNm_H1.getValue();
                    } else {
                        xxScrItem500Txt_H1 = scrnMsg.prntVndNm_H1.getValue();
                    }
                }
                if (ZYPCommonFunc.hasValue(scrnMsg.vndNm_H1)) {
                    if (ZYPCommonFunc.hasValue(xxScrItem500Txt_H1)) {
                        xxScrItem500Txt_H1 = xxScrItem500Txt_H1 + "/" + scrnMsg.vndNm_H1.getValue();
                    } else {
                        xxScrItem500Txt_H1 = scrnMsg.vndNm_H1.getValue();
                    }
                }
                if (ZYPCommonFunc.hasValue(xxScrItem500Txt_H1)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem500Txt_H1, xxScrItem500Txt_H1);
                }
            }
            // Set Focus
            scrnMsg.setFocusItem(scrnMsg.rtrnCtrlTpVndRelnPk_H1);
        } else {
            if (!NLCL0250Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxFldValTxt_HC, scrnMsg.P.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxFldValTxt_HN, scrnMsg.P.no(1).xxComnScrColValTxt);
            }
            // Set Focus
            scrnMsg.setFocusItem(scrnMsg.xxFldValTxt_HC);
        }
        // END 2017/12/18 S.Katsuma [QC#22469,ADD]
    }
}
