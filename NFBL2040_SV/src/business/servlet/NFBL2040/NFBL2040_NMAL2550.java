/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import static business.servlet.NFBL2040.common.NFBL2040CommonLogic.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NFBL2040.common.NFBL2040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/02   Hitachi         T.Tsuchida      Update          QC#12040
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#12040
 * 2016/11/14   Hitachi         Y.Tsuchimoto    Update          QC#15773
 * 2018/04/10   CITS            K.Ogino         Update          QC#24985
 *</pre>
 */
public class NFBL2040_NMAL2550 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/09/26 T.Tsuchida [QC#12040,ADD]
        if (ZYPCommonFunc.hasValue(this.getButtonStatus(BTN_CMN_SUBMIT[0]))) {
            return;
        }
        // END 2016/09/26 T.Tsuchida [QC#12040,ADD]
        // QC#24985 Start
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        int idx = scrnMsg.xxCellIdx.getValueInt();

        if (scrnMsg.A.no(idx).xxCmntTxt_A1.isInputProtected()) {
           return;
        }
        // QC#24985 End
        
        StringBuffer sb = new StringBuffer();
        sb.append(scrnMsg.xxPopPrm_I1.getValue());
        sb.append(STR_COMMA);
        sb.append(scrnMsg.xxPopPrm_I2.getValue());
        sb.append(STR_COMMA);
        sb.append(scrnMsg.xxPopPrm_I3.getValue());
        sb.append(STR_COMMA);
        sb.append(scrnMsg.xxPopPrm_I4.getValue());
        sb.append(STR_COMMA);
        sb.append(scrnMsg.xxPopPrm_I5.getValue());
        sb.append(STR_COMMA);
        sb.append(scrnMsg.xxPopPrm_I6.getValue());
        sb.append(STR_COMMA);
        sb.append(scrnMsg.xxPopPrm_I7.getValue());
        sb.append(STR_COMMA);
        sb.append(scrnMsg.xxPopPrm_I8.getValue());
        sb.append(STR_COMMA);
        sb.append(scrnMsg.xxPopPrm_I9.getValue());

        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxCmntTxt_A1, sb.toString());
        scrnMsg.setFocusItem(scrnMsg.A.no(idx).xxCmntTxt_A1);

        // START 2016/11/14 Y.Tsuchimoto [QC#15773,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I4.getValue())) {
            String coaAcctDescTxt = NFBL2040CommonLogic.getCoaAcctDescTxt(getGlobalCompanyCode(), scrnMsg.xxPopPrm_I4.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).apAcctDescTxt_A1, coaAcctDescTxt);
        }
        // END   2016/11/14 Y.Tsuchimoto [QC#15773,ADD]

        clearRowsBG_NoSelectTab(bMsg);
    }
}
