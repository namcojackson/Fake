/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1600;

import static business.servlet.NWAL1600.constant.NWAL1600Constant.BIZ_ID;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.BTN_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1600.NWAL1600CMsg;
import business.servlet.NWAL1600.common.NWAL1600CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/19   Fujitsu         T.Ishii         Update          S21_NA#4338
 * 2016/05/12   Fujitsu         T.Murai         Update          S21_NA#7861
 *</pre>
 */
public class NWAL1600_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;
        int index = getButtonSelectNumber();

        // has parameter
        if (!BTN_CMN_CLOSE.equals(getLastGuard())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.S.no(5).xxComnScrColValTxt_S)) {

                String eventName = scrnMsg.xxScrEventNm.getValue();

                if (S21StringUtil.isEquals(eventName, "OnBlur_DeriveFromSalesCreditName")) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).slsRepTocCd_A, scrnMsg.S.no(5).xxComnScrColValTxt_S);
                    scrnMsg.A.no(index).tocNm_A.clear();
                    scrnMsg.A.no(index).coaBrNm_A.clear();
                    scrnMsg.A.no(index).coaCcNm_A.clear();
                    scrnMsg.A.no(index).coaExtnNm_A.clear();
                } else if (S21StringUtil.isEquals(eventName, "OnBlur_DeriveFromSalesCreditCode")) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).slsRepTocCd_A, scrnMsg.S.no(5).xxComnScrColValTxt_S);
                    scrnMsg.A.no(index).tocNm_A.clear();
                    scrnMsg.A.no(index).coaBrNm_A.clear();
                    scrnMsg.A.no(index).coaCcNm_A.clear();
                    scrnMsg.A.no(index).coaExtnNm_A.clear();
                } else if (S21StringUtil.isEquals(eventName, "OnBlur_DeriveFromSalesRepName")) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).slsRepTocCd_B, scrnMsg.S.no(5).xxComnScrColValTxt_S);
                    scrnMsg.B.no(index).tocNm_B.clear();
                    scrnMsg.B.no(index).coaBrNm_B.clear();
                    scrnMsg.B.no(index).coaCcNm_B.clear();
                    scrnMsg.B.no(index).coaExtnNm_B.clear();
                } else if (S21StringUtil.isEquals(eventName, "OnBlur_DeriveFromSalesRepCode")) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).slsRepTocCd_B, scrnMsg.S.no(5).xxComnScrColValTxt_S);
                    scrnMsg.B.no(index).tocNm_B.clear();
                    scrnMsg.B.no(index).coaBrNm_B.clear();
                    scrnMsg.B.no(index).coaCcNm_B.clear();
                    scrnMsg.B.no(index).coaExtnNm_B.clear();
                } else if (S21StringUtil.isEquals(eventName, "OpenWin_SlsRep")) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).slsRepTocCd_A, scrnMsg.S.no(5).xxComnScrColValTxt_S);
                    scrnMsg.A.no(index).tocNm_A.clear();
                    scrnMsg.A.no(index).coaBrNm_A.clear();
                    scrnMsg.A.no(index).coaCcNm_A.clear();
                    scrnMsg.A.no(index).coaExtnNm_A.clear();
                } else if (S21StringUtil.isEquals(eventName, "OpenWin_NoQuoteSlsRep")) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).slsRepTocCd_B, scrnMsg.S.no(5).xxComnScrColValTxt_S);
                    scrnMsg.B.no(index).tocNm_B.clear();
                    scrnMsg.B.no(index).coaBrNm_B.clear();
                    scrnMsg.B.no(index).coaCcNm_B.clear();
                    scrnMsg.B.no(index).coaExtnNm_B.clear();
                } else {

                }
            }

            NWAL1600CMsg bizMsg = new NWAL1600CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;
        int index = getButtonSelectNumber();

        // has parameter
        if (!BTN_CMN_CLOSE.equals(getLastGuard())) {
            NWAL1600CMsg bizMsg = (NWAL1600CMsg) cMsg;

            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        String eventName = scrnMsg.xxScrEventNm.getValue();

        if (S21StringUtil.isEquals(eventName, "OnBlur_DeriveFromSalesCreditName")) {

            NWAL1600CommonLogic.addCheckSlsRepItems(scrnMsg);
            scrnMsg.putErrorScreen();

            scrnMsg.setFocusItem(scrnMsg.A.no(index).tocNm_A);
        } else if (S21StringUtil.isEquals(eventName, "OnBlur_DeriveFromSalesCreditCode")) {

            NWAL1600CommonLogic.addCheckSlsRepItems(scrnMsg);
            scrnMsg.putErrorScreen();

            scrnMsg.setFocusItem(scrnMsg.A.no(index).psnNum_A); // 2016/05/12 S21_NA#7861 Mod psnCd_A -> psnNum_A
        } else if (S21StringUtil.isEquals(eventName, "OnBlur_DeriveFromSalesRepName")) {

            NWAL1600CommonLogic.addCheckNonQuoteItems(scrnMsg);
            scrnMsg.putErrorScreen();

            scrnMsg.setFocusItem(scrnMsg.B.no(index).tocNm_B);
        } else if (S21StringUtil.isEquals(eventName, "OnBlur_DeriveFromSalesRepCode")) {

            NWAL1600CommonLogic.addCheckNonQuoteItems(scrnMsg);
            scrnMsg.putErrorScreen();

            scrnMsg.setFocusItem(scrnMsg.B.no(index).psnNum_B); // 2016/05/12 S21_NA#7861 Mod psnCd_B -> psnNum_B
        } else if (S21StringUtil.isEquals(eventName, "OpenWin_SlsRep")) {

            NWAL1600CommonLogic.addCheckSlsRepItems(scrnMsg);
            scrnMsg.putErrorScreen();

            scrnMsg.setFocusItem(scrnMsg.A.no(index).tocNm_A);
        } else if (S21StringUtil.isEquals(eventName, "OpenWin_NoQuoteSlsRep")) {

            NWAL1600CommonLogic.addCheckNonQuoteItems(scrnMsg);
            scrnMsg.putErrorScreen();

            scrnMsg.setFocusItem(scrnMsg.B.no(index).tocNm_B);
        } else {
            NWAL1600CommonLogic.setDefaultForcus(scrnMsg);
        }
    }
}
