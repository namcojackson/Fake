/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0290;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLCL0290.NLCL0290CMsg;
//import business.servlet.NLCL0290.constant.NLCL0290Constant;

import business.blap.NLCL0290.NLCL0290CMsg;
import business.servlet.NLCL0290.common.NLCL0290CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/24   CITS            R.Shimamoto     Create          QC#11785
 * 2018/04/06   CITS            K.Masaki        Update          QC#18472
 *</pre>
 */
public class NLCL0290Scrn00_OpenWin_Account_Detail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;
        int idx = getButtonSelectNumber();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(idx));
        // QC:18472
        //scrnMsg.addCheckItem(scrnMsg.A.no(idx).xxScrItem130Txt_A);
        //scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // QC:18472
//        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;
//
//        NLCL0290CMsg bizMsg = new NLCL0290CMsg();
//        bizMsg.setBusinessID("NLCL0290");
//        bizMsg.setFunctionCode("20");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
          return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;
        // QC:18472
//        NLCL0290CMsg bizMsg = (NLCL0290CMsg) cMsg;
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int idx = getButtonSelectNumber();
        Object[] params = NLCL0290CommonLogic.setParamForAccountPopup(scrnMsg, idx);

        setArgForSubScreen(params);

//        if (!"E".equals(bizMsg.getMessageKind())) {
//            Object[] params = NLCL0290CommonLogic.setParamForAccountPopup(scrnMsg, idx);
//
//            setArgForSubScreen(params);
//        } else {
//            scrnMsg.addCheckItem(scrnMsg.A.no(idx).xxScrItem130Txt_A);
//            scrnMsg.putErrorScreen();
//        }
    }
}
