/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0260;

import static business.servlet.NSBL0260.constant.NSBL0260Constant.BUSINESS_ID;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.FUNCTION_SEARCH;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PRMS_26;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.SELECT_POPUP_SER_HEADER_COMB;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.SELECT_POPUP_SER_LINE;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0260.NSBL0260CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2018/06/07   Fujitsu         T.Ogura         Update          QC#22395
 *</pre>
 */
public class NSBL0260_NSAL1240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0260BMsg scrnMsg = (NSBL0260BMsg) bMsg;
        NSBL0260CMsg bizMsg = new NSBL0260CMsg();
        if ("CMN_Close".equals(getLastGuard()) && SELECT_POPUP_SER_LINE.equals(scrnMsg.xxPopPrm_SE.getValue())) {
            int rowNum = getButtonSelectNumber();
            setValue(scrnMsg.xxRowNum_SE, new BigDecimal(rowNum));
            bizMsg.setBusinessID(BUSINESS_ID);
            bizMsg.setFunctionCode(FUNCTION_SEARCH);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0260BMsg scrnMsg = (NSBL0260BMsg) bMsg;
        NSBL0260CMsg bizMsg = (NSBL0260CMsg) cMsg;
        if (hasValue(scrnMsg.O.no(0).serNum_O)) {
            if (bizMsg != null) {
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
            }
            if (SELECT_POPUP_SER_HEADER_COMB.equals(scrnMsg.xxPopPrm_SE.getValue())) {
                setValue(scrnMsg.serNum_C, scrnMsg.O.no(0).serNum_O);
                scrnMsg.setFocusItem(scrnMsg.serNum_C);
            } else if (SELECT_POPUP_SER_LINE.equals(scrnMsg.xxPopPrm_SE.getValue())) {
                int index = getButtonSelectNumber();
                // START 2018/06/07 T.Ogura [QC#22395,ADD]
                setValue(scrnMsg.A.no(index).mdlNm_A, scrnMsg.xxPopPrm_26);
                // END   2018/06/07 T.Ogura [QC#22395,ADD]
                setValue(scrnMsg.A.no(index).serNum_A, scrnMsg.O.no(0).serNum_O);
                scrnMsg.setFocusItem(scrnMsg.A.no(index).serNum_A);
            }
        }

    }
}
