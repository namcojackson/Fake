/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0620;

import static business.servlet.NSAL0620.constant.NSAL0620Constant.BIZ_ID;
import static business.servlet.NSAL0620.constant.NSAL0620Constant.SCREEN_ID;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0620.NSAL0620CMsg;
import business.servlet.NSAL0620.common.NSAL0620CommonLogic;
import business.servlet.NSAL0620.constant.NSAL0620Constant.FUNC;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         T.Tsuchida      Create          N/A
 * 2016/05/09   Hitachi         M.Gotou         Upadte          QC#4093
 * 2024/02/08   Hitachi         K.Watanabe      Upadte          QC#63464
 *</pre>
 */
public class NSAL0620Scrn00_SrchContr extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
        NSAL0620CommonLogic.addCheckItem(scrnMsg);
        // mod start 2016/05/09 CSA Defect#4093
        NSAL0620CommonLogic.inputCheckMondatory(scrnMsg);
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        // mod end 2016/05/09 CSA Defect#4093
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        // START 2024/02/07 K.Watanabe [QC#63464,ADD]
        scrnMsg.xxSrchCnt.setValue(BigDecimal.ZERO);
        // END 2024/02/07 K.Watanabe [QC#63464,ADD]
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NSAL0620CMsg bizMsg = new NSAL0620CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
        NSAL0620CMsg bizMsg  = (NSAL0620CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        NSAL0620CommonLogic.srchControlScreen(this, scrnMsg);
        NSAL0620CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }
}
