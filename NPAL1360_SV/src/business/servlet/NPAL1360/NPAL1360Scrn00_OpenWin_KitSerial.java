/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1360;

import static business.servlet.NPAL1360.constant.NPAL1360Constant.BIZ_APP_ID;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.EVENT_OPEN_WIN_KIT_SERIAL;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_0;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.NOT_SHOW_DIALOG;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.SHOW_DIALOG;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1360.NPAL1360CMsg;
import business.servlet.NPAL1360.common.NPAL1360CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1360 Kitting Work Order Entry
 * Function Name : Open Kit Serial# Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/13/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1360Scrn00_OpenWin_KitSerial extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;

        NPAL1360CommonLogic.checkSerialNumber(scrnMsg);

        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.cpltRtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.fnshGoodsMdseCd);
        scrnMsg.addCheckItem(scrnMsg.fnshGoodsOrdQty);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;

        NPAL1360CMsg bizMsg = new NPAL1360CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;
        NPAL1360CMsg bizMsg  = (NPAL1360CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.rtlWhCd.isError()) {
            scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
            scrnMsg.putErrorScreen();
            setResult(NOT_SHOW_DIALOG);
            return;
        }

        if (scrnMsg.fnshGoodsMdseCd.isError()) {
            scrnMsg.addCheckItem(scrnMsg.fnshGoodsMdseCd);
            scrnMsg.putErrorScreen();
            setResult(NOT_SHOW_DIALOG);
            return;
        }

        if (scrnMsg.serNum.isError()) {
            scrnMsg.addCheckItem(scrnMsg.serNum);
            scrnMsg.putErrorScreen();
            setResult(NOT_SHOW_DIALOG);
            return;
        }

        if ("E".equals(bizMsg.getMessageKind())) {
            scrnMsg.putErrorScreen();
            setResult(NOT_SHOW_DIALOG);
            return;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.eventNm, EVENT_OPEN_WIN_KIT_SERIAL);
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        Object[] params = NPAL1360CommonLogic.setSerialPopupParam(scrnMsg, funcList, INDEX_0);
        setArgForSubScreen(params);
        setResult(SHOW_DIALOG);

    }
}
