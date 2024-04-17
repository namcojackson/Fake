/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import static business.servlet.NSAL0990.constant.NSAL0990Constant.BIZ_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0990.NSAL0990CMsg;
import business.servlet.NSAL0990.common.NSAL0990CommonLogic;
import business.servlet.NSAL0990.constant.NSAL0990Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         K.Kasai         Create          N/A
 * 2016/07/06   Hitachi         O.Okuma         Update          QC#9630
 * 2016/09/21   Hitachi         N.Arai          Update          QC#11616
 * 2019/01/21   Hitachi         A.Kohinata      Update          QC#27304
 *</pre>
 */
public class NSAL0990Scrn00_Add_Mdse extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        if (!hasValue(scrnMsg.mdseCd)) {
            scrnMsg.mdseCd.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String []{"Mdse Cd"});
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;

        NSAL0990CMsg bizMsg = new NSAL0990CMsg();
        bizMsg.setBusinessID("NSAL0990");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        NSAL0990CMsg bizMsg  = (NSAL0990CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NSAL0990CommonLogic.activateButtons(this, scrnMsg, functionList);
        NSAL0990CommonLogic.activateButtonsForAdd(this, scrnMsg, functionList);
        NSAL0990CommonLogic.activateScreenItems(this, functionList, scrnMsg);
        NSAL0990CommonLogic.activateScreenItemsForAdd(scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.putErrorScreen();
        // mod start 2019/01/21 QC#27304
        //if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {
        if ("E".equals(bizMsg.getMessageKind())) {
        // mod end 2019/01/21 QC#27304
            scrnMsg.setFocusItem(scrnMsg.mdseCd);
            return;
        }
        scrnMsg.setFocusItem(scrnMsg.C.no(scrnMsg.C.getValidCount() - 1).ordCustUomQty_C);
        NSAL0990CommonLogic.formatItem(scrnMsg);
        NSAL0990CommonLogic.alternateTableRowColor(scrnMsg);
        scrnMsg.mdseCd.clear();
     // START 2016/09/21 N.Arai [QC#11616, MOD]
     // scrnMsg.mdseNm.clear();
        scrnMsg.mdseDescShortTxt.clear();
     // END 2016/09/21 N.Arai [QC#11616, MOD]
        NSAL0990CommonLogic.setXxDplyCtrlFlg(scrnMsg);
    }
}
