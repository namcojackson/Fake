/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static business.servlet.NSAL0300.constant.NSAL0300Constant.DIV_STR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0300.NSAL0300CMsg;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;
import business.servlet.NSAL0300.constant.NSAL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Hitachi         T.Kanasaka      Create          N/A
 * 2016/01/21   Hitachi         T.Tomita        Update          QC#2182
 * 2016/04/07   Hitachi         M.Gotou         Update          QC#5312,5113
 * 2017/01/24   Hitachi         N.Arai          Update          QC#17228
 *</pre>
 */
public class NSAL0300_NSAL0420 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
     // START 2017/01/24 N.Arai [QC#17228, MOD]
        // START 2016/01/21 T.Tomita [QC#2182, MOD]
        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
        bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
        // return null;
        // END 2016/01/21 T.Tomita [QC#2182, MOD]
      // END 2017/01/24 N.Arai [QC#17228, MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/01/21 T.Tomita [QC#2182, MOD]
//        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
//        NSAL0300CMsg bizMsg = (NSAL0300CMsg) cMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
//        NSAL0300CommonLogic.addCheckItem(scrnMsg);
//
//        scrnMsg.putErrorScreen();
//        if ("E".equals(bizMsg.getMessageKind())) {
//            throw new EZDFieldErrorException();
//        }
        // add start 2016/04/07 CSA Defect#5312,5313
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
     // START 2017/01/24 N.Arai [QC#17228, MOD]
        NSAL0300CMsg bizMsg = (NSAL0300CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
     // END 2017/01/24 N.Arai [QC#17228, MOD]

        String brNm = "";
        brNm = brNm.concat(scrnMsg.svcLineBizCd.getValue());
        if (ZYPCommonFunc.hasValue(brNm)) {
            brNm = brNm.concat(DIV_STR);
        }
        brNm = brNm.concat(scrnMsg.svcContrBrDescTxt.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyByCdNmCnctTxt, brNm);

        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.xxDplyByCdNmCnctTxt);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.xxPsnNm);
        // add end 2016/04/07 CSA Defect#5312,5313
        return;
        // END 2016/01/21 T.Tomita [QC#2182, MOD]
    }
}
