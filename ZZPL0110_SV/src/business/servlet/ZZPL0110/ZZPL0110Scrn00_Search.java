/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZPL0110;

import java.util.Arrays;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
// import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
// import business.blap.ZZPL0110.ZZPL0110CMsg;
// import business.servlet.ZZPL0110.constant.ZZPL0110Constant;

import business.blap.ZZPL0110.ZZPL0110CMsg;
import business.servlet.ZZPL0110.common.ZZPL0110CommonLogic;
import business.servlet.ZZPL0110.constant.ZZPL0110Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/12   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0110Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZPL0110BMsg scrnMsg = (ZZPL0110BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.eipRptProcLogPk);
        scrnMsg.addCheckItem(scrnMsg.subSysCd);
        scrnMsg.addCheckItem(scrnMsg.rptJobId);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt);
        scrnMsg.addCheckItem(scrnMsg.xxToDt);
        scrnMsg.putErrorScreen();

        if ((scrnMsg.xxFromDt != null && !scrnMsg.xxFromDt.equals("")) && (scrnMsg.xxToDt != null && !scrnMsg.xxToDt.equals(""))) {
            ZZPL0110CommonLogic.compareFromToDate(scrnMsg);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZPL0110BMsg scrnMsg = (ZZPL0110BMsg) bMsg;

        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        ZZPL0110CMsg bizMsg = new ZZPL0110CMsg();
        bizMsg.setBusinessID(ZZPL0110Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(ZZPL0110Constant.FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        // return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZPL0110BMsg scrnMsg = (ZZPL0110BMsg) bMsg;
        ZZPL0110CMsg bizMsg = (ZZPL0110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.eipRptProcLogPk);

        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG(ZZPL0110Constant.SCREENID_SCRN00, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        // set table background color
        ZZPL0110CommonLogic.setTableRowColor(scrnMsg, ZZPL0110Constant.SCREENID_SCRN00);

        ZZPL0110CommonLogic.setActivationPulldownList(scrnMsg);
        ZZPL0110CommonLogic.convertJobTimeFormat(scrnMsg);

        setButtonEnabled(ZZPL0110Constant.BTN_CMN_BTN2[0], false);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String rptJobSts = scrnMsg.A.no(i).rptJobStsTxt_A.getValue();
            if (Arrays.asList(ZZPL0110Constant.RPT_JOB_STS_CHANGEABLE).contains(rptJobSts)) {
                setButtonEnabled(ZZPL0110Constant.BTN_CMN_BTN2[0], true);
                break;
            }
        }
    }
}
