/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL0190.common;

import static business.servlet.NPAL0190.constant.NPAL0190Constant.BUSINESS_ID;
import static business.servlet.NPAL0190.constant.NPAL0190Constant.FUNCTION_UPDATE;

import java.util.List;

import parts.servletcommon.EZDApplicationContext;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL0190.NPAL0190BMsg;
import business.servlet.NPAL0190.NPAL0190Bean;
import business.servlet.NPAL0190.constant.NPAL0190Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 * <pre>
 * PO History Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/29/2012   SRA             N.Otsuji        Create          N/A
 * 03/14/2013   Hitachi         T.Arakawa       Create          K25(QC822)
 * 10/29/2013   Hitachi         H.Narumi        Update          QC2852
 * 01/25/2016   CITS            K.Ogino         Update          CSA
 * 01/17/2017   CITS            T.Kikuhara      Update          QC#16256
 *</pre>
 */
public class NPAL0190CommonLogic {

    /**
     * Alternate table row colors
     * @param scrnMsg NPAL0190BMsg
     */
    public static void alternateTableRowColors(NPAL0190BMsg scrnMsg) {

        S21TableColorController control = new S21TableColorController(NPAL0190Constant.SCREEN_ID, scrnMsg);
        control.setAlternateRowsBG(NPAL0190Bean.A, scrnMsg.A);
    }

    /**
     * Initialize command buttons
     * @param handler EZDCommonHandler
     */
    public static void initializeCommandButtons(EZDCommonHandler handler) {
        // QC#16256 add start
        // This Screen is Read only Screen
        if (isUpdatable()) {
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_1_ID, NPAL0190Constant.BTN_CMN_BTN_1_NAME, NPAL0190Constant.BTN_CMN_BTN_1_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_2_ID, NPAL0190Constant.BTN_CMN_BTN_2_NAME, NPAL0190Constant.BTN_CMN_BTN_2_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_3_ID, NPAL0190Constant.BTN_CMN_BTN_3_NAME, NPAL0190Constant.BTN_CMN_BTN_3_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_4_ID, NPAL0190Constant.BTN_CMN_BTN_4_NAME, NPAL0190Constant.BTN_CMN_BTN_4_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_5_ID, NPAL0190Constant.BTN_CMN_BTN_5_NAME, NPAL0190Constant.BTN_CMN_BTN_5_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_6_ID, NPAL0190Constant.BTN_CMN_BTN_6_NAME, NPAL0190Constant.BTN_CMN_BTN_6_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_7_ID, NPAL0190Constant.BTN_CMN_BTN_7_NAME, NPAL0190Constant.BTN_CMN_BTN_7_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_8_ID, NPAL0190Constant.BTN_CMN_BTN_8_NAME, NPAL0190Constant.BTN_CMN_BTN_8_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_9_ID, NPAL0190Constant.BTN_CMN_BTN_9_NAME, NPAL0190Constant.BTN_CMN_BTN_9_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_10_ID, NPAL0190Constant.BTN_CMN_BTN_10_NAME, NPAL0190Constant.BTN_CMN_BTN_10_VAL, 1, null);
        } else {
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_1_ID, NPAL0190Constant.BTN_CMN_BTN_1_NAME, NPAL0190Constant.BTN_CMN_BTN_1_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_2_ID, NPAL0190Constant.BTN_CMN_BTN_2_NAME, NPAL0190Constant.BTN_CMN_BTN_2_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_3_ID, NPAL0190Constant.BTN_CMN_BTN_3_NAME, NPAL0190Constant.BTN_CMN_BTN_3_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_4_ID, NPAL0190Constant.BTN_CMN_BTN_4_NAME, NPAL0190Constant.BTN_CMN_BTN_4_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_5_ID, NPAL0190Constant.BTN_CMN_BTN_5_NAME, NPAL0190Constant.BTN_CMN_BTN_5_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_6_ID, NPAL0190Constant.BTN_CMN_BTN_6_NAME, NPAL0190Constant.BTN_CMN_BTN_6_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_7_ID, NPAL0190Constant.BTN_CMN_BTN_7_NAME, NPAL0190Constant.BTN_CMN_BTN_7_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_8_ID, NPAL0190Constant.BTN_CMN_BTN_8_NAME, NPAL0190Constant.BTN_CMN_BTN_8_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_9_ID, NPAL0190Constant.BTN_CMN_BTN_9_NAME, NPAL0190Constant.BTN_CMN_BTN_9_VAL, 0, null);
            handler.setButtonProperties(NPAL0190Constant.BTN_CMN_BTN_10_ID, NPAL0190Constant.BTN_CMN_BTN_10_NAME, NPAL0190Constant.BTN_CMN_BTN_10_VAL, 1, null);
        }
        // QC#16256 add end
    }

    /**
     * Format timestamp. Because the sort column and the display
     * column are different.
     * @param scrnMsg NPAL0190BMsg
     */
    public static void formatTs(NPAL0190BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String poBizProcLogUpdTs = formatTs(scrnMsg.A.no(i).xxScrItem19Txt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxScrItem19Txt, poBizProcLogUpdTs);
        }
    }

    private static String formatTs(String ts) {
        if (ZYPCommonFunc.hasValue(ts)) {

            if (ts.length() <= 8) {
                return ZYPDateUtil.formatEzd8ToDisp(ts);
            } else {
                return ZYPDateUtil.formatEzd14ToDisp(ts.substring(0, 14));
            }

        } else {
            return NPAL0190Constant.EMPTY_STR;
        }
    }

    /**
     * Set table column sort parameters
     * @param ctx EZDApplicationContext
     * @param scrnMsg NPAL0190BMsg
     */
    public static void setTableColumnSortParameters(EZDApplicationContext ctx, NPAL0190BMsg scrnMsg) {

        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortTblNm, param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortItemNm, param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortOrdByTxt, param.getSingleValue(S21TableColumnSortConstant.ORDER_BY));
    }

    /**
     * setInputProtected column are different.
     * @param scrnMsg NPAL0190BMsg
     */
    public static void setInputProtected(NPAL0190BMsg scrnMsg) {
        scrnMsg.poOrdNum_H.setInputProtected(true);
        scrnMsg.dispPoDtlLineNum_H.setInputProtected(true);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).eventId.setInputProtected(true);
            scrnMsg.A.no(i).serNumListTxt.setInputProtected(true);
            scrnMsg.A.no(i).poOrdDtlCmntTxt.setInputProtected(true);

            // Amount (example: 100.10)
            scrnMsg.A.no(i).entDealNetUnitPrcAmt.setAppFracDigit(2);
            scrnMsg.A.no(i).entPoDtlDealNetAmt.setAppFracDigit(2);

            // Quantity
            scrnMsg.A.no(i).poCancQty.setAppFracDigit(0);
            scrnMsg.A.no(i).poDispQty.setAppFracDigit(0);
            scrnMsg.A.no(i).poInvQty.setAppFracDigit(0);
            scrnMsg.A.no(i).poRcvQty.setAppFracDigit(0);
        }
    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BUSINESS_ID);
        return functionList.contains(FUNCTION_UPDATE);
    }
    // QC#16256 add end

}
