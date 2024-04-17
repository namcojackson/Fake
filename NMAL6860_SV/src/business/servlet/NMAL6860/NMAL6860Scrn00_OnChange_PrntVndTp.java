/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.setCursorRuleForSwhDetail;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.setInputProtectedForCommonHeader;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.setInputProtectedForDetailTab;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.setInputProtectedForGeneralTab;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2016   CITS            T.Gotoda        Create          QC#13313
 * 2020/02/28   Fujitsu         C.Hara          Update          QC#55971
 *</pre>
 */
public class NMAL6860Scrn00_OnChange_PrntVndTp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // 2020/02/28 QC#55971 Del Start
//        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;
//        
//        for (int i= 0; i < scrnMsg.prntVndTpCd_L1.length(); i++) {
//            if (!ZYPCommonFunc.hasValue(scrnMsg.prntVndTpCd_L1.no(i))) {
//                break;
//            }
//
//            if (scrnMsg.prntVndTpCd.getValue().equals(scrnMsg.prntVndTpCd_L1.no(i).getValue())) {
//                String vndTpCd = scrnMsg.vndTpCd_L1.no(i).getValue();
//
//                ZYPEZDItemValueSetter.setValue(scrnMsg.vndTpCd, vndTpCd);
//                break;
//            }
//        }
//
//        for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
//            if (VND_TP.SUPPLIER.equals(scrnMsg.vndTpCd.getValue())
//                    && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).splyPoFlg_A.getValue())) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).splyPoFlg_A, ZYPConstant.FLG_ON_Y);
//            }
//        }
//
//        // sets the input fields of Common Header.
//        setInputProtectedForCommonHeader(scrnMsg);
//        // sets the input fields of General Tab.
//        setInputProtectedForGeneralTab(scrnMsg);
//        // sets the input fields of Detail Tab.
//        setInputProtectedForDetailTab(scrnMsg);
//
//        setCursorRuleForSwhDetail(scrnMsg);
        // 2020/02/28 QC#55971 Del End
    }
}
