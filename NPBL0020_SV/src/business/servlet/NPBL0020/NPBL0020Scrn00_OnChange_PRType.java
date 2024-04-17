/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.SCREEN_CTRL_VALUE_MULTIPLE;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.NPAM0009E;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : On Change PRType
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/03/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NPBL0020Scrn00_OnChange_PRType extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.prchReqTpCd_SL)) {
            scrnMsg.shipToLocNm.clear();
            scrnMsg.shipToAddlLocNm.clear();
            scrnMsg.xxShipVndAddr.clear();
            scrnMsg.shipToPostCd.clear();
            scrnMsg.shipToCtyAddr.clear();
            scrnMsg.shipToCntyNm.clear();
            scrnMsg.shipToStCd.clear();
            scrnMsg.shipToProvNm.clear();
            scrnMsg.ctryNm.clear();
            scrnMsg.prchReqTpCd_SL.setErrorInfo(1, NPAM0009E, new String[] {});
        }

        scrnMsg.addCheckItem(scrnMsg.prchReqTpCd_SL);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        NPBL0020CMsg bizMsg = new NPBL0020CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        NPBL0020CMsg bizMsg = (NPBL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPBL0020CommonLogic.setCtrlScrnItemDispInit(this, scrnMsg);

        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(scrnMsg.srcRtlWhCd.getValue())) {
            scrnMsg.srcRtlWhCd.clear();
        }
        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(scrnMsg.srcRtlSwhCd.getValue())) {
            scrnMsg.srcRtlSwhCd.clear();
        }
        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(scrnMsg.destRtlWhCd.getValue())) {
            scrnMsg.destRtlWhCd.clear();
        }
        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(scrnMsg.destRtlSwhCd.getValue())) {
            scrnMsg.destRtlSwhCd.clear();
        }
        if (SCREEN_CTRL_VALUE_MULTIPLE.equals(scrnMsg.shipToCustCd_EO.getValue())) {
            scrnMsg.shipToCustCd_EO.clear();
        }

        // set focus
        scrnMsg.setFocusItem(scrnMsg.prchReqTpCd_SL);

    }
}
