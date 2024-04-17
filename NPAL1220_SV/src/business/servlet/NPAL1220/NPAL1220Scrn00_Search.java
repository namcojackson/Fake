/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1220;

import static business.servlet.NPAL1220.constant.NPAL1220Constant.BIZ_APP_ID;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.NMAM0288E;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.SCRN_ID;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NPAL1220.NPAL1220CMsg;
import business.servlet.NPAL1220.common.NPAL1220CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1220 ASL Search
 * Function Name : Button Action to Search
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/17/2015   CITS       Takeshi Tokutomi     Create          N/A
 * 01/11/2018   CITS            S.Katsuma       Update          QC#12226
 *</pre>
 */
public class NPAL1220Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1220BMsg scrnMsg = (NPAL1220BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.aslNm) && !ZYPCommonFunc.hasValue(scrnMsg.prntVndCd) && !ZYPCommonFunc.hasValue(scrnMsg.splyItemNum) && !ZYPCommonFunc.hasValue(scrnMsg.mdseCd)
                // START 2018/01/11 S.Katsuma [QC#12226,ADD]
                && !ZYPCommonFunc.hasValue(scrnMsg.vndCd)) {
                // END 2018/01/11 S.Katsuma [QC#12226,ADD]
            scrnMsg.aslNm.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.prntVndCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.splyItemNum.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.mdseCd.setErrorInfo(1, NMAM0288E, new String[] {});
            // START 2018/01/11 S.Katsuma [QC#12226,ADD]
            scrnMsg.vndCd.setErrorInfo(1, NMAM0288E, new String[] {});
            // END 2018/01/11 S.Katsuma [QC#12226,ADD]
        }

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        scrnMsg.addCheckItem(scrnMsg.aslNm);
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.addCheckItem(scrnMsg.splyItemNum);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        // START 2018/01/11 S.Katsuma [QC#12226,ADD]
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        // END 2018/01/11 S.Katsuma [QC#12226,ADD]
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1220BMsg scrnMsg = (NPAL1220BMsg) bMsg;

        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NPAL1220CMsg bizMsg = new NPAL1220CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1220BMsg scrnMsg = (NPAL1220BMsg) bMsg;
        NPAL1220CMsg bizMsg = (NPAL1220CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPAL1220CommonLogic.setTableScreen(scrnMsg);

        // set focus
        scrnMsg.setFocusItem(scrnMsg.aslNm);
    }
}
