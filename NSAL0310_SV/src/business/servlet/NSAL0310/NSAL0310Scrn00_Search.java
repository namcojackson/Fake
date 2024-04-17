/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0310;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0310.NSAL0310CMsg;
import business.servlet.NSAL0310.common.NSAL0310CommonLogic;
import business.servlet.NSAL0310.constant.NSAL0310Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/26   CUSA            SRAA            Create          N/A
 * 2016/02/12   Hitachi         A.Kohinata      Update          QC#3019
 * 2016/02/16   Hitachi         A.Kohinata      Update          QC#3017
 * 2016/09/15   Hitachi         N.Arai          Update          QC#11616
 * 2018/10/30   Hitachi         K.Kim           Update          QC#28890
 *</pre>
 */
public class NSAL0310Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.serNum);
        scrnMsg.addCheckItem(scrnMsg.mdlNm);
        // [QC#3017,MOD]START
        scrnMsg.addCheckItem(scrnMsg.xxComnScrColValTxt_H);
        // [QC#3017,MOD]END
        scrnMsg.addCheckItem(scrnMsg.xxScrItem29Txt);
        // START 2016/09/15 N.Arai [QC#11616, MOD]
        // scrnMsg.addCheckItem(scrnMsg.mdseNm);
        scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt);
        // END 2016/09/15 N.Arai [QC#11616, MOD]
        // START 2018/10/30 [QC#28890, MOD]
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H);
        NSAL0310CommonLogic.inputCheckMondatory(scrnMsg);
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        // END 2018/10/30 [QC#28890, MOD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;
        NSAL0310CMsg bizMsg = new NSAL0310CMsg();
        bizMsg.setBusinessID(NSAL0310Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0310Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;
        NSAL0310CMsg bizMsg = (NSAL0310CMsg) cMsg;

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0310Constant.BIZ_ID);
        NSAL0310CommonLogic.activateButtons(this, scrnMsg, functionList);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0310CommonLogic.setupScreenItems(scrnMsg, functionList);
        NSAL0310CommonLogic.alternateTableRowColor(scrnMsg);
        // START 2016/02/12 [QC#3019, ADD]
        S21SortColumnIMGController.clearIMG(NSAL0310Constant.SCR_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // END 2016/02/12 [QC#3019, ADD]
        NSAL0310CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        // START 2018/10/30 [QC#28890, MOD]
        // scrnMsg.setFocusItem(scrnMsg.serNum);
        scrnMsg.setFocusItem(scrnMsg.dsAcctNum_H);
        // END 2018/10/30 [QC#28890, MOD]
    }
}
