/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1410;

import static business.servlet.NPAL1410.constant.NPAL1410Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.EVENT_NAME_CONF_POPUP_CO;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.EVENT_NAME_CONF_POPUP_H;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.EVENT_NM_CMN_CLOSE;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1410.NPAL1410CMsg;
import business.servlet.NPAL1410.common.NPAL1410CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1410 Reman Workbench
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 4/05/2016   CITS       Yasushi Nomura       Create          N/A
 * 09/20/2017   CITS            T.Kikuhara      Update          QC#18675(SOL#219)
 * 04/01/2019   CITS            K.Ogino         Update          QC#30851
 *</pre>
 */
public class NPAL1410_NSAL1240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1410CMsg bizMsg = null;
        NPAL1410BMsg scrnMsg = (NPAL1410BMsg) bMsg;
        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard()) && (0 < scrnMsg.O.getValidCount())) {
            if (EVENT_NAME_CONF_POPUP_H.equals(scrnMsg.eventNm.getValue())) {
                // QC#30851
                ZYPEZDItemValueSetter.setValue(scrnMsg.rmnfMainUnitSerNum_H1, scrnMsg.O.no(0).serNum_O);
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_H1, scrnMsg.O.no(0).mdseCd_O);
                bizMsg = new NPAL1410CMsg();
                bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
            }
        }
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1410BMsg scrnMsg = (NPAL1410BMsg) bMsg;
        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard()) && (0 < scrnMsg.O.getValidCount())) {
            if (EVENT_NAME_CONF_POPUP_H.equals(scrnMsg.eventNm.getValue())) {
                NPAL1410CMsg bizMsg = (NPAL1410CMsg) cMsg;
                EZDMsg.copy(bizMsg, null, scrnMsg, null);

                List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
                NPAL1410CommonLogic.commonInit(scrnMsg);
                NPAL1410CommonLogic.control(this, scrnMsg, funcList);

                scrnMsg.setFocusItem(scrnMsg.rmnfMainUnitSerNum_H1);
            } else if (EVENT_NAME_CONF_POPUP_CO.equals(scrnMsg.eventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).serNum_A1, scrnMsg.O.no(0).serNum_O);
                scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).serNum_A1);
            }
            // QC#30851
            scrnMsg.eventNm.clear();
            //QC#18675 ADD START
            scrnMsg.addCheckItem(scrnMsg.rmnfMainUnitSerNum_H1);
            scrnMsg.putErrorScreen();
            //QC#18675 ADD END

        }
    }
}
