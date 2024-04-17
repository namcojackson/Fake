/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZPL0030;

import java.text.SimpleDateFormat;
import java.util.Date;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZPL0030.ZZPL0030CMsg;
import business.servlet.ZZPL0030.common.ZZPL0030CommonLogic;
import business.servlet.ZZPL0030.constant.ZZPL0030Constant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0030Scrn01_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;

        // check data item type
        scrnMsg.addCheckItem(scrnMsg.xxCratDt_FR);
        scrnMsg.addCheckItem(scrnMsg.xxCratDt_TO);
        scrnMsg.addCheckItem(scrnMsg.xxRptSrchTxt_1);
        scrnMsg.addCheckItem(scrnMsg.xxRptSrchTxt_2);
        scrnMsg.addCheckItem(scrnMsg.xxRptSrchTxt_3);
        scrnMsg.addCheckItem(scrnMsg.xxRptSrchTxt_4);
        scrnMsg.putErrorScreen();

        // compare creation date(from) with creation date(to)
        if (scrnMsg.xxCratDt_TO.getValue() != null && !scrnMsg.xxCratDt_TO.getValue().equals("")) {
            SimpleDateFormat dtFormat = new SimpleDateFormat(ZZPL0030Constant.DATE_FORMAT);
            Date fromDate;
            Date toDate;
            try {
                fromDate = dtFormat.parse(scrnMsg.xxCratDt_FR.getValue());
                toDate = dtFormat.parse(scrnMsg.xxCratDt_TO.getValue());
                if (fromDate.compareTo(toDate) > 0) {
                    scrnMsg.xxCratDt_TO.setErrorInfo(1, "ZZPM0065E", new String[] {scrnMsg.xxCratDt_FR.getValue(), scrnMsg.xxCratDt_TO.getValue() });
                    scrnMsg.addCheckItem(scrnMsg.xxCratDt_TO);
                }
            } catch (java.text.ParseException e) {
                throw new S21AbendException("ZZPM0045E", new String[] {e.getMessage() });
            }
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;

        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.B);
        scrnMsg.xxPageShowFromNum_1.clear();
        scrnMsg.xxPageShowToNum_1.clear();
        scrnMsg.xxPageShowOfNum_1.clear();

        ZZPL0030CMsg bizMsg = new ZZPL0030CMsg();
        bizMsg.setBusinessID(ZZPL0030Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(ZZPL0030Constant.FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;
        ZZPL0030CMsg bizMsg = (ZZPL0030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZPL0030CommonLogic.setCreationTimeFormat(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.xxCratDt_FR);

        // set table background color
        ZZPL0030CommonLogic.setTableRowColor(scrnMsg, ZZPL0030Constant.SCREENID_SCRN01);

        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG(ZZPL0030Constant.SCREENID_SCRN01, scrnMsg, scrnMsg.B.no(0).getBaseContents());
    }
}
