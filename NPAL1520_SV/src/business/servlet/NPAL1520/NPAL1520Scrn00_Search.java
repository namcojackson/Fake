/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1520;

import static business.servlet.NPAL1520.constant.NPAL1520Constant.BIZ_APP_ID;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.SCRN_ID;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.NMAM0288E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1520.NPAL1520CMsg;
import business.servlet.NPAL1520.common.NPAL1520CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1520 Min-Max Planning Inquiry
 * Function Name : Search
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/11/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */

public class NPAL1520Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1520BMsg scrnMsg = (NPAL1520BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.mrpPlnNm)
            && !ZYPCommonFunc.hasValue(scrnMsg.mdseCd)
            && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)
            && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd)
            && !ZYPCommonFunc.hasValue(scrnMsg.whMgrPsnCd)
            && !ZYPCommonFunc.hasValue(scrnMsg.srcRtlWhCd)
            && !ZYPCommonFunc.hasValue(scrnMsg.srcRtlSwhCd)) {
            scrnMsg.mrpPlnNm.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.mdseCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rtlWhCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rtlSwhCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.whMgrPsnCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.srcRtlWhCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.srcRtlSwhCd.setErrorInfo(1, NMAM0288E, new String[] {});
        }

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        scrnMsg.addCheckItem(scrnMsg.mrpPlnNm);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.whMgrPsnCd);
        scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.srcRtlSwhCd);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1520BMsg scrnMsg = (NPAL1520BMsg) bMsg;

        // set values to items of pagination for next page pagination
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NPAL1520CMsg bizMsg = new NPAL1520CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1520BMsg scrnMsg = (NPAL1520BMsg) bMsg;
        NPAL1520CMsg bizMsg = (NPAL1520CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPAL1520CommonLogic.setTableScreen(scrnMsg);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NPAL1520CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // set focus
        scrnMsg.setFocusItem(scrnMsg.mrpPlnNm);

    }
}
