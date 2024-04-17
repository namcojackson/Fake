/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2010   Fujitsu         K.Tajima        Create          N/A
 *</pre>
 */
package business.servlet.NWDL0260;

import static business.servlet.NWDL0260.common.NWDL0260CommonLogic.addCheckAllItems;
import static business.servlet.NWDL0260.common.NWDL0260CommonLogic.createCMsgForUpdate;
import static business.servlet.NWDL0260.common.NWDL0260CommonLogic.hasError;
import static business.servlet.NWDL0260.common.NWDL0260CommonLogic.nullToZero;
import static business.servlet.NWDL0260.common.NWDL0260CommonLogic.setGuiAttr;
import static business.servlet.NWDL0260.common.NWDL0260CommonLogic.toArray;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static java.math.BigDecimal.ZERO;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDGUIAttribute;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWDL0260.NWDL0260CMsg;
import business.servlet.NWDL0260.constant.NWDL0260Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NWDL0260Scrn00_Allocate extends S21CommonHandler implements NWDL0260Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWDL0260BMsg scrnMsg = (NWDL0260BMsg) bMsg;

        // Allocation Qty (On Hand, In Transit)
        final EZDBBigDecimalItem allocQtyOnHandItem  = scrnMsg.invtyAdvcOnHandQty_AL;
        final EZDBBigDecimalItem allocQtyInTrnstItem = scrnMsg.invtyAdvcInTrnstQty_AL;
        
        scrnMsg.addCheckItem(allocQtyOnHandItem);
        scrnMsg.addCheckItem(allocQtyInTrnstItem);
        scrnMsg.putErrorScreen();

        // --------------------------------------------------
        // Mandatory
        // --------------------------------------------------
        final EZDBBigDecimalItem[] mandatoryCheckItemArray = {
            allocQtyOnHandItem, // On Hand
            allocQtyInTrnstItem // In Transit
        };
        if (!hasValue(allocQtyOnHandItem) && !hasValue(allocQtyInTrnstItem)) {
            for (EZDBBigDecimalItem  mandatoryCheckItem : mandatoryCheckItemArray) {
                if (!mandatoryCheckItem.isInputProtected()) {
                    mandatoryCheckItem.setErrorInfo(1, MsgId.ZZM9000E.name(), toArray(mandatoryCheckItem.getNameForMessage()));
                    scrnMsg.addCheckItem(mandatoryCheckItem);
                }
            }
        }
        scrnMsg.putErrorScreen();

        // --------------------------------------------------
        // Min Qty
        // --------------------------------------------------
        final EZDBBigDecimalItem[] minQtyCheckItemArray = {
            allocQtyOnHandItem, // On Hand
            allocQtyInTrnstItem // In Transit
        };
        for (EZDBBigDecimalItem minQtyCheckItem : minQtyCheckItemArray) {
            if (!minQtyCheckItem.isInputProtected()) {
                if (ZERO.compareTo(nullToZero(minQtyCheckItem.getValue())) > 0) {
                    minQtyCheckItem.setErrorInfo(1, MsgId.ZZM9003E.name(), toArray(minQtyCheckItem.getNameForMessage()));
                    scrnMsg.addCheckItem(minQtyCheckItem);
                }
            }
        }
        scrnMsg.putErrorScreen();
        
        if (ZERO.compareTo(nullToZero(allocQtyOnHandItem.getValue())) == 0 && ZERO.compareTo(nullToZero(allocQtyInTrnstItem.getValue())) == 0) {
            for (EZDBBigDecimalItem minQtyCheckItem : minQtyCheckItemArray) {
                if (!minQtyCheckItem.isInputProtected()) {
                    minQtyCheckItem.setErrorInfo(1, MsgId.ZZM9003E.name(), toArray(minQtyCheckItem.getNameForMessage()));
                    scrnMsg.addCheckItem(minQtyCheckItem);
                }
            }
        }
        scrnMsg.putErrorScreen();

        // --------------------------------------------------
        // Max Qty
        // --------------------------------------------------
        final EZDBBigDecimalItem[][] maxQtyCheckDefArray = {
            {allocQtyOnHandItem,  scrnMsg.invtyAdvcOnHandQty_AV}, // On Hand
            {allocQtyInTrnstItem, scrnMsg.invtyAdvcInTrnstQty_AV} // In Transit
        };
        for (int i = 0; i < maxQtyCheckDefArray.length; i++) {
            EZDBBigDecimalItem allocQtyItem = maxQtyCheckDefArray[i][0];
            if (!allocQtyItem.isInputProtected()) {
                EZDBBigDecimalItem avalQtyItem = maxQtyCheckDefArray[i][1];
                if (nullToZero(allocQtyItem.getValue()).compareTo(avalQtyItem.getValue()) > 0) {
                    allocQtyItem.setErrorInfo(1, MsgId.ZZM9002E.name(), toArray(allocQtyItem.getNameForMessage()));
                    scrnMsg.addCheckItem(allocQtyItem);
                }
            }
        }
        scrnMsg.putErrorScreen();
        
        // --------------------------------------------------
        // Memo
        // --------------------------------------------------
        final EZDBStringItem memo  = scrnMsg.hidInvtyMemoTxt;
        scrnMsg.addCheckItem(memo);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWDL0260BMsg scrnMsg = (NWDL0260BMsg) bMsg;

        return createCMsgForUpdate(scrnMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWDL0260BMsg scrnMsg = (NWDL0260BMsg) bMsg;
        NWDL0260CMsg bizMsg = (NWDL0260CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // set GUI attributes.
        setGuiAttr(this, scrnMsg, ScrnId.Scrn00);
        
        // error judgement.
        if (hasError(bizMsg)) {
            return;
        } else {
            addCheckAllItems(scrnMsg, scrnMsg.getBaseContents());
            scrnMsg.putErrorScreen();
        }

        // highlight a allocated line
        final EZDGUIAttribute guiRigthAttr = new EZDGUIAttribute(ScrnId.Scrn00.getId(), "A_RIGHT_TR_#" + (scrnMsg.A.getValidCount() - 1));
        final EZDGUIAttribute guiLeftAttr = new EZDGUIAttribute(ScrnId.Scrn00.getId(), "A_LEFT_TR_#" + (scrnMsg.A.getValidCount() - 1));
        guiRigthAttr.setStyleAttribute("background", ColorCd.AllocatedLine.getCd());
        guiLeftAttr.setStyleAttribute("background", ColorCd.AllocatedLine.getCd());
        scrnMsg.addGUIAttribute(guiRigthAttr);
        scrnMsg.addGUIAttribute(guiLeftAttr);
        // set focus.
        scrnMsg.setFocusItem(scrnMsg.mdseCd);
    }

}
