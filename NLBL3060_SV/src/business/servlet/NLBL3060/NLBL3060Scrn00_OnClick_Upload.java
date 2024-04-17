/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3060;

import static business.servlet.NLBL3060.constant.NLBL3060Constant.*;

import java.util.Arrays;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3060.NLBL3060CMsg;
import business.servlet.NLBL3060.common.NLBL3060CommonLogic;
import business.servlet.NLBL3060.constant.NLBL3060Constant.MESSAGE_ID;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/04/13   Hitachi         T.Kuroda        Create          QC#61166
 * 2023/10/18   Hitachi         Y.Ogura         Update          QC#61793
 *</pre>
 */
public class NLBL3060Scrn00_OnClick_Upload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.xxFileData_UP);
        scrnMsg.putErrorScreen();

        if (scrnMsg.xxFileData_UP.isUploaded()) {
            ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData_UP
                    , Arrays.asList(new String[] {FILE_EXTENSION_CSV}));
        } else {
            scrnMsg.xxFileData_UP.setErrorInfo(
                    1, MESSAGE_ID.ZZM9000E.toString(), new String[] {"Upload File"});
        }

        scrnMsg.addCheckItem(scrnMsg.xxFileData_UP);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;

        if (scrnMsg.xxFileData_UP.isUploaded()) {
            scrnMsg.xxFileData_UP.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(
                    scrnMsg.xxFileData_UP
                    , getUserProfileService().getContextUserInfo().getUserId())
                    , "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData_UP));
        }

        NLBL3060CMsg bizMsg = new NLBL3060CMsg();
        bizMsg.setBusinessID(APPLICATION_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;
        NLBL3060CMsg bizMsg  = (NLBL3060CMsg) cMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2023/10/18 Y.Ogura [QC#61793, ADD]
        scrnMsg.addCheckItem(scrnMsg.rtlWhCatgCd_HD);
        scrnMsg.addCheckItem(scrnMsg.physWhCd_HD);
        // END 2023/10/18 Y.Ogura [QC#61793, ADD]
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.schdCoordPsnCd);
        scrnMsg.putErrorScreen();

        String submitBtnFlg = ZYPConstant.FLG_OFF_N;
        if (bizMsg.A.getValidCount() > 0) {
            submitBtnFlg = ZYPConstant.FLG_ON_Y;
        }

        NLBL3060CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID(), submitBtnFlg);
        scrnMsg.putErrorScreen();

        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        scrnMsg.setMessageInfo(MESSAGE_ID.NZZM0002I.toString());

    }
}
