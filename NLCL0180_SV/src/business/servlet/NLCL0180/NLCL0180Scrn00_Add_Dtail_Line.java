/**
 * <Pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</Pre>
 */
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/01/2009   Fujitsu         FXS)BY.Bao          Create          N/A
 *</pre>
 */
package business.servlet.NLCL0180;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0180.NLCL0180CMsg;
import business.servlet.NLCL0180.common.NLCL0180CommonLogic;
import business.servlet.NLCL0180.constant.NLCL0180Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class NLCL0180Scrn00_Add_Dtail_Line extends S21CommonHandler implements NLCL0180Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0180BMsg scrnMsg = (NLCL0180BMsg) bMsg;

        if (scrnMsg.mdseCd.isError()) {
            scrnMsg.mdseNm.clear();
        }

        NLCL0180CommonLogic.checkInput_NLCL0180Scrn00_Add_Dtail_Line(scrnMsg);

        scrnMsg.putErrorScreen();

        if (scrnMsg.A.getValidCount() == MAX_ADD_COUNT) {

            scrnMsg.mdseCd.setErrorInfo(1, "NLCM0025E", new String[] {"100" });
            scrnMsg.addCheckItem(scrnMsg.mdseCd);
            scrnMsg.putErrorScreen();
        } else {
        }

        if (new BigDecimal(0).compareTo(scrnMsg.xxRqstQty_H1.getValue()) == 0
                && new BigDecimal(0).compareTo(scrnMsg.xxRqstQty_H2.getValue()) == 0) {

            scrnMsg.xxRqstQty_H1.setErrorInfo(1, "NLCM0060E");
            scrnMsg.addCheckItem(scrnMsg.xxRqstQty_H1);
            scrnMsg.putErrorScreen();
        } else {
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.mdseCd.getValue().equals(scrnMsg.A.no(i).mdseCd_A1.getValue())) {
                scrnMsg.mdseCd.setErrorInfo(1, "NLCM0019E");
                scrnMsg.addCheckItem(scrnMsg.mdseCd);
                scrnMsg.putErrorScreen();
            } else {
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0180CMsg bizMsg = null;

        NLCL0180BMsg scrnMsg = (NLCL0180BMsg) bMsg;

        bizMsg = NLCL0180CommonLogic.setRequestData_NLCL0180Scrn00_Add_Dtail_Line();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0180CMsg bizMsg = (NLCL0180CMsg) cMsg;

        NLCL0180BMsg scrnMsg = (NLCL0180BMsg) bMsg;

        if (bizMsg != null) {
            if ("E".equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }

            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NLCL0180CommonLogic.transMsgCheck(scrnMsg);
        scrnMsg.putErrorScreen();

        NLCL0180CommonLogic.initialize(this, scrnMsg);

        NLCL0180CommonLogic.setScrnItemValue_NLCL0180Scrn00_Add_Dtail_Line(scrnMsg);

        NLCL0180CommonLogic.commonBtnControl_NLCL0180Scrn00_Add_Dtail_Line(this);

        NLCL0180CommonLogic.scrnItemControl_NLCL0180Scrn00_Add_Dtail_Line(scrnMsg);

        S21TableColorController tblColor = new S21TableColorController("NLCL0180Scrn00", scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mdseNm_A1.setInputProtected(true);
        }
    }
}
