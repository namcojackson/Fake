/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3200;

import static business.servlet.NMAL3200.constant.NMAL3200Constant.BIZ_APP_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_SEARCH;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_UPLOAD;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.MKTG_FLD_MAP_NM_DB;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.NMAM0836E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL3200.NMAL3200CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NMAL3200 Marketing Data Analysis
 * Function Name : Search_MktFldMap
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            K.Ogino         Create          N/A
 */
public class NMAL3200Scrn00_Search_MktFldMap extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.mktgFldMapNm_DB)) {
            scrnMsg.mktgFldMapNm_DB.setErrorInfo(1, NMAM0836E, new String[] {MKTG_FLD_MAP_NM_DB });
        }
        scrnMsg.addCheckItem(scrnMsg.mktgFldMapNm_DB);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;

        NMAL3200CMsg bizMsg = new NMAL3200CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;
        NMAL3200CMsg bizMsg = (NMAL3200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (ZYPCommonFunc.hasValue(scrnMsg.mktgFldMapPk)) {
            scrnMsg.setFocusItem(scrnMsg.contrAssnTrgtTpCd_SL);
            this.setButtonEnabled(BTN_SEARCH, false);
            this.setButtonEnabled(BTN_UPLOAD, true);
            scrnMsg.xxFileData.setInputProtected(false);
            if (ZYPCommonFunc.hasValue(scrnMsg.mktgMapRqstPk_UP)) {
                scrnMsg.mktgFldMapNm_SC.setInputProtected(true);
            } else {
                scrnMsg.mktgFldMapNm_SC.setInputProtected(false);
            }
        }
    }
}
