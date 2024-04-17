/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6810;

import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Item Master Template Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/17   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public class NMAL6810Scrn00_Select_Template extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL6810BMsg scrnMsg = (NMAL6810BMsg) bMsg;

        //NMAL6810CMsg bizMsg = new NMAL6810CMsg();
        //bizMsg.setBusinessID("NMAL6810");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6810BMsg scrnMsg = (NMAL6810BMsg) bMsg;


        int index = getButtonSelectNumber();
        BigDecimal xxMdseCratTmplPk = scrnMsg.A.no(index).mdseCratTmplPk_A1.getValue();
        String xxMdseItemTpCd = scrnMsg.A.no(index).mdseItemTpCd_A1.getValue();
        String xxMdseCratTmplNm = scrnMsg.A.no(index).mdseCratTmplNm_A1.getValue();

        Object[] arg = (Object[]) getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            EZDBBigDecimalItem param0 = (EZDBBigDecimalItem) params[0];
            EZDBStringItem param1 = (EZDBStringItem) params[1];
            EZDBStringItem param2 = (EZDBStringItem) params[2];

            param0.setValue(xxMdseCratTmplPk);
            param1.setValue(xxMdseItemTpCd);
            param2.setValue(xxMdseCratTmplNm);
        }
    }
}
