/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0630;

import static business.servlet.NLCL0630.constant.NLCL0630Constant.BIZ_APP_ID;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0630.NLCL0630CMsg;
import business.servlet.NLCL0630.common.NLCL0630CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0630 Tech PI Inquiry
 * Function Name : Search
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/26/2016   CITS            Makoto Okigami  Create          N/A
 * 08/07/2018   CITS            Y.Iwasaki       Update          QC#27010
 * 09/12/2018   CITS            M.Naito         Update          QC#28190
 * 12/11/2018   Fujitsu         T.Ogura         Update          QC#28755
 *</pre>
 */
public class NLCL0630Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0630BMsg scrnMsg = (NLCL0630BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.techTocCd);
        scrnMsg.addCheckItem(scrnMsg.physInvtyDt_DF);
        scrnMsg.addCheckItem(scrnMsg.physInvtyDt_DT);
        scrnMsg.addCheckItem(scrnMsg.physInvtyCtrlNm);
        scrnMsg.addCheckItem(scrnMsg.coaBrCd);
        // START 2018/12/11 T.Ogura [QC#28755,ADD]
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        // END   2018/12/11 T.Ogura [QC#28755,ADD]

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0630BMsg scrnMsg = (NLCL0630BMsg) bMsg;

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NLCL0630CMsg bizMsg = new NLCL0630CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0630BMsg scrnMsg = (NLCL0630BMsg) bMsg;
        NLCL0630CMsg bizMsg  = (NLCL0630CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.techTocCd);
        scrnMsg.addCheckItem(scrnMsg.physInvtyDt_DF);
        scrnMsg.addCheckItem(scrnMsg.physInvtyDt_DT);
        scrnMsg.addCheckItem(scrnMsg.physInvtyCtrlNm);
        scrnMsg.addCheckItem(scrnMsg.coaBrCd);
        // START 2018/12/11 T.Ogura [QC#28755,ADD]
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        // END   2018/12/11 T.Ogura [QC#28755,ADD]
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        NLCL0630CommonLogic.setTableScreen(scrnMsg);

        // set focus
        scrnMsg.setFocusItem(scrnMsg.techTocCd);

    }
}
