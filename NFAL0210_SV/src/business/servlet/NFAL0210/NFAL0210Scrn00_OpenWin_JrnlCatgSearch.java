/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0210;

import static business.servlet.NFAL0210.constant.NFAL0210Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Manual Journal Entry Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/14   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NFAL0210Scrn00_OpenWin_JrnlCatgSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0210BMsg scrnMsg = (NFAL0210BMsg) bMsg;

        // Code Table Name
        scrnMsg.P.no(PARAM_NUM_0).xxPopPrm.setValue("JRNL_CATG");
        // Code of Code Table
        scrnMsg.P.no(PARAM_NUM_1).xxPopPrm.setValue("JRNL_CATG_CD");
        // Desc of Code Table
        scrnMsg.P.no(PARAM_NUM_2).xxPopPrm.setValue("JRNL_CATG_NM");
        // Sort num of Code Table
        // scrnMsg.xxSortItemNm.setValue("JRNL_CATG_SORT_NUM");
        scrnMsg.P.no(PARAM_NUM_3).xxPopPrm.setValue("JRNL_CATG_NM");
        // Screen Title
        scrnMsg.P.no(PARAM_NUM_4).xxPopPrm.setValue("journal Catg Search");
        // ID label for search field
        scrnMsg.P.no(PARAM_NUM_5).xxPopPrm.setValue("JrnlCatg");
        // Description label for search field
        scrnMsg.P.no(PARAM_NUM_6).xxPopPrm.setValue("JrnlCatg Name");
        // ID filed for table header
        scrnMsg.P.no(PARAM_NUM_7).xxPopPrm.setValue("JRNL_CATG_CD");
        // Desc filed for table header
        scrnMsg.P.no(PARAM_NUM_8).xxPopPrm.setValue("JRNL_CATG_NM");

        Object[] param = new Object[PARAM_NUM_11];
        param[PARAM_NUM_0] = scrnMsg.P.no(PARAM_NUM_0).xxPopPrm;
        param[PARAM_NUM_1] = scrnMsg.P.no(PARAM_NUM_1).xxPopPrm;
        param[PARAM_NUM_2] = scrnMsg.P.no(PARAM_NUM_2).xxPopPrm;
        param[PARAM_NUM_3] = scrnMsg.P.no(PARAM_NUM_3).xxPopPrm;
        param[PARAM_NUM_4] = scrnMsg.P.no(PARAM_NUM_4).xxPopPrm;
        param[PARAM_NUM_5] = scrnMsg.P.no(PARAM_NUM_5).xxPopPrm;
        param[PARAM_NUM_6] = scrnMsg.P.no(PARAM_NUM_6).xxPopPrm;
        param[PARAM_NUM_7] = scrnMsg.P.no(PARAM_NUM_7).xxPopPrm;
        param[PARAM_NUM_8] = scrnMsg.P.no(PARAM_NUM_8).xxPopPrm;
        // Code for input filed
        param[PARAM_NUM_9] = scrnMsg.jrnlCatgCd;
        param[PARAM_NUM_10] = scrnMsg.jrnlCatgDescTxt;

        setArgForSubScreen(param);
    }
}
