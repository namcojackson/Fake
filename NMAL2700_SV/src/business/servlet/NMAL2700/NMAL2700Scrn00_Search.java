/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2700;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.BIZ_ID;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2700.NMAL2700CMsg;
import business.servlet.NMAL2700.common.NMAL2700CommonLogic;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL2700Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/05   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL2700Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2700BMsg scrnMsg = (NMAL2700BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.firstOrgCd);
        scrnMsg.addCheckItem(scrnMsg.orgFuncRoleTpCd);
        scrnMsg.addCheckItem(scrnMsg.orgFuncRoleTpNm);
        scrnMsg.addCheckItem(scrnMsg.orgFuncRoleTpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.mgrFlg);
        scrnMsg.addCheckItem(scrnMsg.spclFlg);
        scrnMsg.addCheckItem(scrnMsg.equipFlg);
        scrnMsg.addCheckItem(scrnMsg.cmsnFlg);
        scrnMsg.addCheckItem(scrnMsg.actvFlg);
        NMAL2700CommonLogic.checkMandator(scrnMsg);
        scrnMsg.putErrorScreen();
    }


    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2700BMsg scrnMsg = (NMAL2700BMsg) bMsg;
        NMAL2700CMsg bizMsg = new NMAL2700CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2700BMsg scrnMsg = (NMAL2700BMsg) bMsg;
        NMAL2700CMsg bizMsg  = (NMAL2700CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NMAL2700CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A1", scrnMsg.A, 1);
        tblColor.setAlternateRowsBG("A2", scrnMsg.A, 1);
        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }
}
