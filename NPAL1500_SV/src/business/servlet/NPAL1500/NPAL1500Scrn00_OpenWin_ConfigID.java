/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.NPAM0009E;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NPAL1500.common.NPAL1500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CITS            N.Akaishi       Create          N/A
 * 2016/10/24   CITS            S.Endo          Update          QC#15226
 *</pre>
 */
public class NPAL1500Scrn00_OpenWin_ConfigID extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.srcRtlWhCd_SC.getValue())) {

            scrnMsg.srcRtlWhCd_SC.setErrorInfo(1, NPAM0009E);
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.prntVndCd.getValue())) {

            scrnMsg.prntVndCd.setErrorInfo(1, NPAM0009E);
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.vndCd.getValue())) {

            scrnMsg.vndCd.setErrorInfo(1, NPAM0009E);
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.destRtlWhCd_DS.getValue())) {

            scrnMsg.destRtlWhCd_DS.setErrorInfo(1, NPAM0009E);
        }

        scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd_SC);
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.destRtlWhCd_DS);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        // Initialization of subscreen delivery information
        NPAL1500CommonLogic.setInitParamForConfigPopup(scrnMsg);

        // Making of subscreen delivery information
        Object[] params = NPAL1500CommonLogic.setParamForConfigPopup(scrnMsg);

        // Subscreen transition
        setArgForSubScreen(params);
    }
}
