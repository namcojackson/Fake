/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1440;

import static business.servlet.NPAL1440.constant.NPAL1440Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1440.constant.NPAL1440Constant.BTN_CMN_BTN_8;
import static business.servlet.NPAL1440.constant.NPAL1440Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1440.constant.NPAL1440Constant.FUNCTION_UPDATE;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1440.NPAL1440CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NPAL1440 PR History Popup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/27/2016   CITS       Yasushi Nomura        Create          N/A
 * 01/17/2017   CITS            T.Kikuhara      Update          QC#16256
 * 02/06/2024   CITS            H.Iwasaki        Update         QC#63476
 *</pre>
 */
public class NPAL1440_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1440BMsg scrnMsg = (NPAL1440BMsg) bMsg;
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && (2 <= params.length) && (params[0] != null) && (params[1] != null)) {
            EZDBStringItem param01 = (EZDBStringItem) params[0];
            EZDBStringItem param02 = (EZDBStringItem) params[1];
            if (ZYPCommonFunc.hasValue(param01) && ZYPCommonFunc.hasValue(param02)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum, param01);
                ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqLineNum, param02);
            }
            if (ZYPCommonFunc.hasValue(param01) && ZYPCommonFunc.hasValue(param02) && (3 <= params.length) && (params[2] != null)) {
                if (params[2] instanceof EZDBStringItem) {
                    EZDBStringItem param03 = (EZDBStringItem) params[2];
                    ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqLineSubNum, param03);
                } else if (params[2] instanceof EZDBBigDecimalItem) {
                    EZDBBigDecimalItem param03 = (EZDBBigDecimalItem) params[2];
                    ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqLineSubNum, param03);
                } else if (params[2] instanceof String) {
                    String param03 = (String) params[2];
                    ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqLineSubNum, new BigDecimal(param03));
                } else if (params[2] instanceof BigDecimal) {
                    BigDecimal param03 = (BigDecimal) params[2];
                    ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqLineSubNum, param03);
                }
            }
        }
        NPAL1440CMsg bizMsg = new NPAL1440CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1440BMsg scrnMsg = (NPAL1440BMsg) bMsg;
        NPAL1440CMsg bizMsg = (NPAL1440CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // init
        // QC#16256 add start
        // This Screen is Read only Screen
        if (isUpdatable()) {
            this.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
            this.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        } else {
            this.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
            this.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        }
        // QC#16256 add end

        scrnMsg.prchReqNum_H.setInputProtected(true);
        scrnMsg.prchReqTpDescTxt_H.setInputProtected(true);
        scrnMsg.prchReqCratDt_H.setInputProtected(true);
        scrnMsg.prchReqStsDescTxt_H.setInputProtected(true);
        scrnMsg.fullPsnNm_H.setInputProtected(true);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).fullPsnNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).ezInAplID_D1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqLogModeTxt_D1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqLogEventDescTxt_D1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqApvlStsDescTxt_D1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqLineStsDescTxt_D1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_D1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqQty_D1.setInputProtected(true);
            scrnMsg.A.no(i).procrTpDescTxt_D1.setInputProtected(true);
            scrnMsg.A.no(i).prntVndNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).locNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).shipQty_D1.setInputProtected(true);
            scrnMsg.A.no(i).rwsPutAwayQty_D1.setInputProtected(true);
            // QC# 63476
            scrnMsg.A.no(i).techCd_D1.setInputProtected(true);
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        return functionList.contains(FUNCTION_UPDATE);
    }
    // QC#16256 add end

}
