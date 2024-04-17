/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZPL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZPL0030.ZZPL0030CMsg;
import business.servlet.ZZPL0030.common.ZZPL0030CommonLogic;
import business.servlet.ZZPL0030.constant.ZZPL0030Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0030Scrn00_OpenRptNm_Row extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;

        ZZPL0030CMsg bizMsg = new ZZPL0030CMsg();
        bizMsg.setBusinessID(ZZPL0030Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(ZZPL0030Constant.FUNCTION_CD_SEARCH);

        // set values to items of pagenation for ZZPL0030Scrn01 page
        // pagenation
        ZYPTableUtil.clear(scrnMsg.B);
        scrnMsg.xxPageShowFromNum_1.clear();
        scrnMsg.xxPageShowToNum_1.clear();
        scrnMsg.xxPageShowOfNum_1.clear();

        int index = getButtonSelectNumber();
        scrnMsg.xxRptNm_1.setValue(scrnMsg.A.no(index).xxRptNm_A.getValue());

        // set value to items of header
        scrnMsg.xxPrtrDescTxt_1.setValue(scrnMsg.A.no(index).xxPrtrDescTxt.getValue());

        scrnMsg.xxCratDt_FR.clear();
        scrnMsg.xxCratDt_TO.clear();

        scrnMsg.xxRptSrchCd_O1.clear();
        scrnMsg.xxRptSrchNm_O1.clear();
        scrnMsg.xxRptSrchCd_I1.clear();
        scrnMsg.xxRptSrchTxt_1.clear();

        scrnMsg.xxRptSrchCd_O2.clear();
        scrnMsg.xxRptSrchNm_O2.clear();
        scrnMsg.xxRptSrchCd_I2.clear();
        scrnMsg.xxRptSrchTxt_2.clear();

        scrnMsg.xxRptSrchCd_O3.clear();
        scrnMsg.xxRptSrchNm_O3.clear();
        scrnMsg.xxRptSrchCd_I3.clear();
        scrnMsg.xxRptSrchTxt_3.clear();

        scrnMsg.xxRptSrchCd_O4.clear();
        scrnMsg.xxRptSrchNm_O4.clear();
        scrnMsg.xxRptSrchCd_I4.clear();
        scrnMsg.xxRptSrchTxt_4.clear();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;
        ZZPL0030CMsg bizMsg = (ZZPL0030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxRptNm_1.setInputProtected(true);

        ZZPL0030CommonLogic.setCreationTimeFormat(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.xxCratDt_FR);

        // set table background color
        ZZPL0030CommonLogic.setTableRowColor(scrnMsg, ZZPL0030Constant.SCREENID_SCRN01);

        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG(ZZPL0030Constant.SCREENID_SCRN01, scrnMsg, scrnMsg.B.no(0).getBaseContents());

        // set pulldown activation
        ZZPL0030CommonLogic.setPulldownActivation(scrnMsg);

    }
}
