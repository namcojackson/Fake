/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2010   Fujitsu         K.Tajima        Create          N/A
 *</pre>
 */
package business.servlet.NWDL0260.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBItem;
import parts.common.EZDBItemArray;
import parts.common.EZDBMsgArray;
import parts.common.EZDGUIAttribute;
import parts.common.EZDMsg;
import parts.servletcommon.gui.EZDGUITableAttribute;
import business.blap.NWDL0260.NWDL0260CMsg;
import business.servlet.NWDL0260.NWDL0260BMsg;
import business.servlet.NWDL0260.NWDL0260Bean;
import business.servlet.NWDL0260.constant.NWDL0260Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class NWDL0260CommonLogic implements NWDL0260Constant {

    public static void addCheckAllItems(NWDL0260BMsg scrnMsg, String[][] baseContents) {

        for (int i = 0; i < baseContents.length; i++) {

            String itemKey = baseContents[i][0];

            Object bMsgField;
            try {
                bMsgField = scrnMsg.getClass().getField(itemKey).get(scrnMsg);
            } catch (Exception e) {
                continue;
            }

            if (bMsgField instanceof EZDBItem) {

                scrnMsg.addCheckItem((EZDBItem) bMsgField);

            } else if (bMsgField instanceof EZDBItemArray) {

                // nothing to do.

            } else if (bMsgField instanceof EZDBMsgArray) {

                final List<String> checkItemList = new ArrayList<String>();
                checkItemList.add(NWDL0260Bean.xxChkBox_A1);

                ((EZDBMsgArray) bMsgField).setCheckParam(checkItemList.toArray(new String[0]), 1);
                scrnMsg.addCheckItem((EZDBMsgArray) bMsgField);

            } else {
                // nothing to do.
            }
        }
    }

    public static NWDL0260CMsg createCMsgForSearch(NWDL0260BMsg scrnMsg) {

        final NWDL0260CMsg bizMsg = new NWDL0260CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FunctionCd.Search.getCd());
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    public static NWDL0260CMsg createCMsgForUpdate(NWDL0260BMsg scrnMsg) {

        final NWDL0260CMsg bizMsg = new NWDL0260CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FunctionCd.Update.getCd());
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    public static boolean hasError(NWDL0260CMsg bizMsg) {
        return bizMsg != null && "E".equals(bizMsg.getMessageKind());
    }

    public static boolean isClosed(String lastGuard) {
        return lastGuard.toLowerCase().endsWith("close");
    }

    public static BigDecimal nullToZero(BigDecimal val) {

        if (val == null) {
            val = ZERO;
        }
        return val;
    }

    public static void setGuiAttr(S21CommonHandler handler, NWDL0260BMsg scrnMsg, ScrnId nextScrnId) {

        List<String> functionIdList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);

        if (ScrnId.Scrn00 == nextScrnId) {

            setGuiAttrForScrn00(handler, scrnMsg, functionIdList);

        } else {
            // nothing to do.
        }
    }

    public static String[] toArray(String... strs) {
        return Arrays.asList(strs).toArray(new String[0]);
    }

    private static void disable(EZDBItem item) {
        item.setInputProtected(true);
    }

    private static void disable(S21CommonHandler handler, BizBtn_Scrn00 bizBtn) {
        handler.setButtonEnabled(bizBtn.getHtmlNm(), false);
    }

    private static void disable(S21CommonHandler handler, CmnBtn cmnBtn) {
        handler.setButtonProperties(cmnBtn.getHtmlNm(), cmnBtn.getEventNm(), cmnBtn.getLabel(), 0, null);
    }

    private static void enable(EZDBItem item) {
        item.setInputProtected(false);
    }

    private static void enable(S21CommonHandler handler, BizBtn_Scrn00 bizBtn) {
        handler.setButtonEnabled(bizBtn.getHtmlNm(), true);
    }

    private static void enable(S21CommonHandler handler, CmnBtn cmnBtn) {
        handler.setButtonProperties(cmnBtn.getHtmlNm(), cmnBtn.getEventNm(), cmnBtn.getLabel(), 1, null);
    }

    private static void setGuiAttrForScrn00(S21CommonHandler handler, NWDL0260BMsg scrnMsg, List<String> functionIdList) {

        scrnMsg.clearAllGUIAttribute(ScrnId.Scrn00.getId());

        // has Modification Role.
        if (functionIdList.contains(FunctionId.Modification.getId())) {

            scrnMsg.setInputProtected(false);

            // On Hand Qty
            final EZDBBigDecimalItem avalOnHandQtyItem  = scrnMsg.invtyAdvcOnHandQty_AV;
            final EZDBBigDecimalItem allocOnHandQtyItem = scrnMsg.invtyAdvcOnHandQty_AL;
            if (!hasValue(avalOnHandQtyItem) || ZERO.compareTo(avalOnHandQtyItem.getValue()) >= 0) {
                disable(allocOnHandQtyItem);
            }
            
            // In Transit Qty
            final EZDBBigDecimalItem avalInTrnstQtyItem  = scrnMsg.invtyAdvcInTrnstQty_AV;
            final EZDBBigDecimalItem allocInTrnstQtyItem = scrnMsg.invtyAdvcInTrnstQty_AL;
            if (!hasValue(avalInTrnstQtyItem) || ZERO.compareTo(avalInTrnstQtyItem.getValue()) >= 0) {
                disable(allocInTrnstQtyItem);
            }
            
            // --------------------------------------------------
            // Biz Buttons
            // --------------------------------------------------
            if (allocOnHandQtyItem.isInputProtected() && allocInTrnstQtyItem.isInputProtected()) {
                disable(handler, BizBtn_Scrn00.Allocate);
            } else {
                enable(handler, BizBtn_Scrn00.Allocate);
            }
            
            if (scrnMsg.A.getValidCount() <= 0) {
                disable(handler, BizBtn_Scrn00.Cancel);
            } else {
                enable(handler, BizBtn_Scrn00.Cancel);
            }

        } else {

            scrnMsg.setInputProtected(true);
            enable(scrnMsg.mdseCd);
            enable(scrnMsg.invtyLocCd);
            enable(scrnMsg.xxChkBox_HD);

            // --------------------------------------------------
            // Biz Buttons
            // --------------------------------------------------
            disable(handler, BizBtn_Scrn00.SearchAvailableQty);
            disable(handler, BizBtn_Scrn00.Allocate);
            disable(handler, BizBtn_Scrn00.Cancel);
        }

        // Page Jump Fields.
        enable(scrnMsg.xxPageShowCurNum);
        enable(scrnMsg.xxPageShowTotNum);

        // --------------------------------------------------
        // Common Buttons
        // --------------------------------------------------
        disable(handler, CmnBtn.Save);
        disable(handler, CmnBtn.Submit);
        disable(handler, CmnBtn.Apply);
        disable(handler, CmnBtn.Approve);
        disable(handler, CmnBtn.Reject);
        disable(handler, CmnBtn.Download);
        disable(handler, CmnBtn.Delete);
        enable(handler, CmnBtn.Clear);
        disable(handler, CmnBtn.Reset);
        enable(handler, CmnBtn.Return);

        // set background-color in List
        final EZDGUITableAttribute tblLeftAttr = new EZDGUITableAttribute(ScrnId.Scrn00.getId(), "A_LEFT");
        final EZDGUITableAttribute tblRightAttr = new EZDGUITableAttribute(ScrnId.Scrn00.getId(), "A_RIGHT");
        for (int i = 1; i < scrnMsg.A.getValidCount() + 1; i++) {
            if ((i + 1) % 2 == 0) {
            	tblLeftAttr.setRowStyle(i, "pEvenNumberBGcolor");
            	tblRightAttr.setRowStyle(i, "pEvenNumberBGcolor");
            } else {
            	tblLeftAttr.setRowStyle(i, "");
            	tblRightAttr.setRowStyle(i, "");
            }
        }
        scrnMsg.addGUIAttribute(tblLeftAttr);
        scrnMsg.addGUIAttribute(tblRightAttr);
        
        // highlight checked lines
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                final EZDGUIAttribute guiLeftAttr = new EZDGUIAttribute(ScrnId.Scrn00.getId(), "A_LEFT_TR_#" + i);
                final EZDGUIAttribute guiRightAttr = new EZDGUIAttribute(ScrnId.Scrn00.getId(), "A_RIGHT_TR_#" + i);
                guiLeftAttr.setStyleAttribute("background", ColorCd.CancelLine.getCd());
                guiRightAttr.setStyleAttribute("background", ColorCd.CancelLine.getCd());
                scrnMsg.addGUIAttribute(guiLeftAttr);
                scrnMsg.addGUIAttribute(guiRightAttr);
            }
        }
        
        
        
        
        
    }

}
