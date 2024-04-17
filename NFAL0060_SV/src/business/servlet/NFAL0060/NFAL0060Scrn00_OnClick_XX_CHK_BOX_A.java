/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0060;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL0060.common.NFAL0060CommonLogic;
import business.servlet.NFAL0060.constant.NFAL0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID :
 * NFAL0060Scrn00_OnClick_XX_CHK_BOX_A
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0060Scrn00_OnClick_XX_CHK_BOX_A extends S21CommonHandler implements NFAL0060Constant {

    /** Singleton instance. */
    private NFAL0060CommonLogic common = new NFAL0060CommonLogic();

    /** List<Integer> checkBoxList */
    private List<Integer> checkBoxList = new ArrayList<Integer>();

    /** int index */
    private int index = 0;

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (CHECKED.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                checkBoxList.add(i);
                index = i;
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;

        // NFAL0060CMsg bizMsg = new NFAL0060CMsg();
        // bizMsg.setBusinessID("NFAL0060");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;
        // NFAL0060CMsg bizMsg = (NFAL0060CMsg) cMsg;
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (checkBoxList.isEmpty()) {
            common.clearAddRow(scrnMsg, this);
            common.enableDeleteRow(scrnMsg, this, false);
        } else {
            common.enableDeleteRow(scrnMsg, this, true);
            scrnMsg.setFocusItem(scrnMsg.A.no(index).xxChkBox_A);
        }
    }

}
