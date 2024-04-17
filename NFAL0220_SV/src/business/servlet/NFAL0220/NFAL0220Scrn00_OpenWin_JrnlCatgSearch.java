/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0220;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/07   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NFAL0220Scrn00_OpenWin_JrnlCatgSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0220BMsg scrnMsg = (NFAL0220BMsg) bMsg;

        // Code Table Name
        scrnMsg.P.no(0).xxPopPrm.setValue("JRNL_CATG");
        // Code of Code Table
        scrnMsg.P.no(1).xxPopPrm.setValue("JRNL_CATG_CD");
        // Desc of Code Table
        scrnMsg.P.no(2).xxPopPrm.setValue("JRNL_CATG_NM");
        // Sort num of Code Table
        // scrnMsg.xxSortItemNm.setValue("JRNL_CATG_SORT_NUM");
        scrnMsg.P.no(3).xxPopPrm.setValue("JRNL_CATG_NM");
        // Screen Title
        scrnMsg.P.no(4).xxPopPrm.setValue("journal Catg Search");
        // ID label for search field
        scrnMsg.P.no(5).xxPopPrm.setValue("JrnlCatg");
        // Description label for search field
        scrnMsg.P.no(6).xxPopPrm.setValue("JrnlCatg Name");
        // ID filed for table header
        scrnMsg.P.no(7).xxPopPrm.setValue("JRNL_CATG_CD");
        // Desc filed for table header
        scrnMsg.P.no(8).xxPopPrm.setValue("JRNL_CATG_NM");
        if (!ZYPCommonFunc.hasValue(scrnMsg.jrnlCatgCd)) {
            scrnMsg.jrnlCatgDescTxt.clear();
        }

        Object[] param = new Object[11];
        param[0] = scrnMsg.P.no(0).xxPopPrm;
        param[1] = scrnMsg.P.no(1).xxPopPrm;
        param[2] = scrnMsg.P.no(2).xxPopPrm;
        param[3] = scrnMsg.P.no(3).xxPopPrm;
        param[4] = scrnMsg.P.no(4).xxPopPrm;
        param[5] = scrnMsg.P.no(5).xxPopPrm;
        param[6] = scrnMsg.P.no(6).xxPopPrm;
        param[7] = scrnMsg.P.no(7).xxPopPrm;
        param[8] = scrnMsg.P.no(8).xxPopPrm;
        // Code for input filed
        param[9] = scrnMsg.jrnlCatgCd;
        param[10] = scrnMsg.jrnlCatgDescTxt;

        setArgForSubScreen(param);
    }
}
