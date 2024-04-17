/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2010   Fujitsu         K.Tajima        Create          N/A
 *</pre>
 */
package business.servlet.NWDL0260;

import static business.servlet.NWDL0260.common.NWDL0260CommonLogic.setGuiAttr;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWDL0260.constant.NWDL0260Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class NWDL0260_INIT extends S21INITCommonHandler implements NWDL0260Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWDL0260BMsg scrnMsg = (NWDL0260BMsg) bMsg;

        // set GUI attributes.
        setGuiAttr(this, scrnMsg, ScrnId.Scrn00);

        // set focus.
        scrnMsg.setFocusItem(scrnMsg.mdseCd);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWDL0260BMsg scrnMsg = (NWDL0260BMsg) bMsg;

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        String label;

        // Merchandise Code
        label = labelConv.convLabel2i18nLabel(ScrnId.Scrn00.getId(), "Merchandise Code");
        scrnMsg.mdseCd.setNameForMessage(label);

        // Product Line
        label = labelConv.convLabel2i18nLabel(ScrnId.Scrn00.getId(), "Product Line");
        scrnMsg.firstProdCtrlCd.setNameForMessage(label);

        // WH
        label = labelConv.convLabel2i18nLabel(ScrnId.Scrn00.getId(), "WH");
        scrnMsg.invtyLocCd.setNameForMessage(label);

        // Allocation Qty (On Hand)
        label = labelConv.convLabel2i18nLabel(ScrnId.Scrn00.getId(), "Allocation Qty (On Hand)");
        scrnMsg.invtyAdvcOnHandQty_AL.setNameForMessage(label);

        // Allocation Qty (In Transit)
        label = labelConv.convLabel2i18nLabel(ScrnId.Scrn00.getId(), "Allocation Qty (In Transit)");
        scrnMsg.invtyAdvcInTrnstQty_AL.setNameForMessage(label);

        for (int i = 0; i < scrnMsg.A.length(); i++) {

            NWDL0260_ABMsg lineMsg = scrnMsg.A.no(i);

            // Check Box
            label = labelConv.convLabel2i18nLabel(ScrnId.Scrn00.getId(), "Check Box");
            lineMsg.xxChkBox_A1.setNameForMessage(label);
        }
    }
}
