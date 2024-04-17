/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.DISPLAY_NM_PRCH_REQ_TP_DESC_TXT;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.DISPLAY_NM_SRC_RTL_SWH_CD;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.DISPLAY_NM_SRC_RTL_WH_CD;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.ZZZM9025E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Button Action to Get_SrcSwhH
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/08/2017   CITS            K.Ogino         Create          QC#17483
 *</pre>
 */
public class NPBL0020Scrn00_Get_SrcSwhH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.prchReqTpCd_SL)) {
            scrnMsg.prchReqTpCd_SL.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_PRCH_REQ_TP_DESC_TXT });
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.srcRtlSwhCd)) {
            scrnMsg.srcRtlSwhCd.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SRC_RTL_SWH_CD });
        }

        scrnMsg.addCheckItem(scrnMsg.prchReqTpCd_SL);
        scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.srcRtlSwhCd);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
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

        scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.srcRtlSwhCd);
        scrnMsg.putErrorScreen();
        
        // set focus
        scrnMsg.setFocusItem(scrnMsg.rtlSwhNm_SS);

    }
}
