/**
 * <pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NLGL0010.NLGL0010CMsg;
import business.servlet.NLGL0010.common.NLGL0010CommonLogic;
import business.servlet.NLGL0010.constant.NLGL0010Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * SO Maintenance
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 09/20/2013     CSAI            Y.Miyauchi        Create            MW Replace Initial
 *</pre>
 */
public class NLGL0010Scrn00_OnClick_Search extends S21CommonHandler implements NLGL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0010BMsg scrnMsg = (NLGL0010BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.whCd_02);
        scrnMsg.addCheckItem(scrnMsg.xxSoSrchFromDt_01);
        scrnMsg.addCheckItem(scrnMsg.xxSoSrchThruDt_01);

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
        }

//        if (WH_ALL_SELECTION_VALUE.equals(scrnMsg.whCd_02.getValue()) && !ZYPCommonFunc.hasValue(scrnMsg.wmsSoId_01.getValue())) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.whCd_02) && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_01) && !ZYPCommonFunc.hasValue(scrnMsg.wmsSoId_01.getValue())) {
            scrnMsg.wmsSoId_01.setErrorInfo(1, NLGM0029E,  new String[] {NAME_FOR_MESSAGE_SO});
            scrnMsg.addCheckItem(scrnMsg.wmsSoId_01);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0010BMsg scrnMsg = (NLGL0010BMsg) bMsg;
        NLGL0010CMsg bizMsg = NLGL0010CommonLogic.setScrnBizFun_20();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0010BMsg scrnMsg = (NLGL0010BMsg) bMsg;
        NLGL0010CMsg bizMsg = (NLGL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_SO_LIST);
        NLGL0010CommonLogic.busSoListControl_DataList(scrnMsg);
        NLGL0010CommonLogic.commonBtnControl_Tab_SO_LIST(this);
    }
}
