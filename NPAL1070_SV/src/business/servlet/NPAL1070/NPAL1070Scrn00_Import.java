/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1070;

import static business.servlet.NPAL1070.constant.NPAL1070Constant.BIZ_APP_ID;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_CHECK_ALL;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_CMN_BTN_2;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_DELETE_ROW;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_DISABLE;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_UNCHECK_ALL;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.SCRN_ID;

import java.util.Arrays;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NPAL1070.NPAL1070CMsg;
import business.servlet.NPAL1070.common.NPAL1070CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1070 Min-Max Planning Entry
 * Function Name : Import
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/24/2016   CITS            Keiichi Masaki  Create          N/A
 * 02/09/2017   CITS            Y.IWASAKI       Update          QC#17478
 *</pre>
 */
public class NPAL1070Scrn00_Import extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;

        if (scrnMsg.xxFileData_UP.isUploaded()) {
            ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData_UP, Arrays.asList(new String[]{"csv", "txt"}));
        } else {
            scrnMsg.xxFileData_UP.setErrorInfo(1, "ZYPM0188E", new String[]{"csv,txt"});
        }

        scrnMsg.addCheckItem(scrnMsg.xxFileData_UP);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;

        if (scrnMsg.xxFileData_UP.isUploaded()) {
            scrnMsg.xxFileData_UP.makeTempFile(
                null,
                ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData_UP,
                        getUserProfileService().getContextUserInfo().getUserId()),
                "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData_UP)
            );
        }

        NPAL1070CMsg bizMsg = new NPAL1070CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        scrnMsg.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;
        NPAL1070CMsg bizMsg  = (NPAL1070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NPAL1070CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // Search Condition Clear
        scrnMsg.rtlWhCatgDescTxt.clear();
        scrnMsg.coaMdseTpCd.clear();
        scrnMsg.coaProdCd.clear();
        // scrnMsg.mrpPlnNm.clear();
        scrnMsg.mdseCd.clear();
        scrnMsg.mdseDescShortTxt.clear();
        scrnMsg.mdseTpCd.clear();
        scrnMsg.rtlWhCatgCd_SL.clear();
        scrnMsg.rtlWhCd.clear();
        scrnMsg.rtlWhNm_W1.clear();
        scrnMsg.rtlSwhCd.clear();
        scrnMsg.rtlSwhNm_S1.clear();
        scrnMsg.whMgrPsnCd.clear();
        scrnMsg.fullPsnNm.clear();
        scrnMsg.procrTpCd_SL.clear();
        scrnMsg.srcRtlWhCd.clear();
        scrnMsg.rtlWhNm_W2.clear();
        scrnMsg.srcRtlSwhCd.clear();
        scrnMsg.rtlSwhNm_S2.clear();
        scrnMsg.mrpEnblFlg.clear();
        // scrnMsg.rplshDlyFlg.clear();
        // scrnMsg.rplshMonFlg.clear();
        // scrnMsg.rplshTueFlg.clear();
        // scrnMsg.rplshWedFlg.clear();
        // scrnMsg.rplshThuFlg.clear();
        // scrnMsg.rplshFriFlg.clear();

        // Set focus
        scrnMsg.setFocusItem(scrnMsg.A.no(1).mrpPlnNm_A1);

        if ("E".equals(bizMsg.getMessageKind())) {
            this.setButtonEnabled(BTN_CHECK_ALL, false);
            this.setButtonEnabled(BTN_UNCHECK_ALL, false);
            this.setButtonEnabled(BTN_DELETE_ROW, false);
            this.setButtonEnabled(BTN_DISABLE, false);
            // CMN_Submit
            this.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        } else {
            // Set Message
            scrnMsg.setMessageInfo("NPAM0005I");
        }
    }
}
