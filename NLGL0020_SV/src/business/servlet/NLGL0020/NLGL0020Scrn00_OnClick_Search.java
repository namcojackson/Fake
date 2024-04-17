/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0020.NLGL0020CMsg;
import business.servlet.NLGL0020.common.NLGL0020CommonLogic;
import business.servlet.NLGL0020.constant.NLGL0020Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * PO Maintenance
 * 
 * Date           Company         Name              Create/Update      Defect No
 * ------------------------------------------------------------------------------------
 * 08/30/2013     CSAI            N.Sekine          Create             MW Replace Initial
 * 05/25/2017     CITS            S.Endo            Update             RS#3173
 *</pre>
 */
public class NLGL0020Scrn00_OnClick_Search extends S21CommonHandler implements NLGL0020Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.whCd_02);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_01);
        scrnMsg.addCheckItem(scrnMsg.invtyOwnrCd_01);
        scrnMsg.addCheckItem(scrnMsg.wmsPoId_01);
        scrnMsg.addCheckItem(scrnMsg.xxSoSrchFromDt_01);
        scrnMsg.addCheckItem(scrnMsg.xxSoSrchThruDt_01);
        scrnMsg.addCheckItem(scrnMsg.wmsMdseCd_01);
        scrnMsg.addCheckItem(scrnMsg.sceOrdTpCd_02);
        scrnMsg.addCheckItem(scrnMsg.wmsTrxCd_02);

        if (ZYPDateUtil.compare(MIN_DATE_FOR_SEARCH, scrnMsg.xxSoSrchFromDt_01.getValue()) == 1) {
            scrnMsg.xxSoSrchFromDt_01.setErrorInfo(1, NLGM0058E);
            scrnMsg.addCheckItem(scrnMsg.xxSoSrchFromDt_01);
        }

        if (ZYPDateUtil.compare(MIN_DATE_FOR_SEARCH, scrnMsg.xxSoSrchThruDt_01.getValue()) == 1) {
            scrnMsg.xxSoSrchThruDt_01.setErrorInfo(1, NLGM0058E);
            scrnMsg.addCheckItem(scrnMsg.xxSoSrchThruDt_01);
        }

        if (ZYPDateUtil.compare(scrnMsg.xxSoSrchFromDt_01.getValue(), MAX_DATE_FOR_SEARCH) == 1) {
            scrnMsg.xxSoSrchFromDt_01.setErrorInfo(1, NLGM0058E);
            scrnMsg.addCheckItem(scrnMsg.xxSoSrchFromDt_01);
        }

        if (ZYPDateUtil.compare(scrnMsg.xxSoSrchThruDt_01.getValue(), MAX_DATE_FOR_SEARCH) == 1) {
            scrnMsg.xxSoSrchThruDt_01.setErrorInfo(1, NLGM0058E);
            scrnMsg.addCheckItem(scrnMsg.xxSoSrchThruDt_01);
        }

        if (ZYPDateUtil.compare(scrnMsg.xxSoSrchFromDt_01.getValue(), scrnMsg.xxSoSrchThruDt_01.getValue()) == 1) {
            scrnMsg.xxSoSrchFromDt_01.setErrorInfo(1, NLGM0059E, new String[] {NAME_FOR_MESSAGE_TO_DT, NAME_FOR_MESSAGE_FROM_DT });
            scrnMsg.xxSoSrchThruDt_01.setErrorInfo(1, NLGM0059E, new String[] {NAME_FOR_MESSAGE_TO_DT, NAME_FOR_MESSAGE_FROM_DT });
            scrnMsg.addCheckItem(scrnMsg.xxSoSrchFromDt_01);
            scrnMsg.addCheckItem(scrnMsg.xxSoSrchThruDt_01);
            scrnMsg.putErrorScreen();
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.whCd_02) && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_01) && !ZYPCommonFunc.hasValue(scrnMsg.wmsPoId_01.getValue())) {
            scrnMsg.wmsPoId_01.setErrorInfo(1, NLGM0029E,  new String[] {NAME_FOR_MESSAGE_PO_ID});
            scrnMsg.addCheckItem(scrnMsg.wmsPoId_01);
        }

        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;
        NLGL0020CMsg bizMsg = NLGL0020CommonLogic.setRequestData_NLGL0020Scrn00_Function_20();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0020CMsg bizMsg = (NLGL0020CMsg) cMsg;
        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NLGL0020CommonLogic.inputFieldControl_NLGL0020Scrn00_Search_Protect(scrnMsg);
    }
}
