/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7220;

import static business.servlet.NMAL7220.constant.NMAL7220Constant.REG_AUTHORITY;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.BIZ_ID;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7220.NMAL7220CMsg;

import business.servlet.NMAL7220.common.NMAL7220CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL7220_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAL7220_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

        NMAL7220BMsg scrnMsg = (NMAL7220BMsg) bMsg;
        S21UserProfileService userProfileService = getUserProfileService();
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        for (String functionId : functionIds) {
            if (REG_AUTHORITY.equals(functionId)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxYesNoCd, ZYPConstant.FLG_ON_Y);
                break;
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7220BMsg scrnMsg = (NMAL7220BMsg) bMsg;
        NMAL7220CMsg bizMsg = new NMAL7220CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null) {
            if (params[0] instanceof EZDBBigDecimalItem) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcFmlaPk_BK, (EZDBBigDecimalItem) params[0]);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcFmlaPk_BK, (BigDecimal) params[0]);
            }
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7220BMsg scrnMsg = (NMAL7220BMsg) bMsg;
        NMAL7220CMsg bizMsg = (NMAL7220CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        boolean authorityFlg = NMAL7220CommonLogic.createAuthorityFlg(scrnMsg.xxYesNoCd.getValue());

        NMAL7220CommonLogic.initCmnBtnProp(this, authorityFlg);
        NMAL7220CommonLogic.scrnProtect(scrnMsg, false);
        NMAL7220CommonLogic.setBtnProp(this, scrnMsg, authorityFlg);

        scrnMsg.setFocusItem(scrnMsg.prcFmlaPk_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7220BMsg scrnMsg = (NMAL7220BMsg) bMsg;

        scrnMsg.prcFmlaPk_H1.setNameForMessage("Formula ID");
        scrnMsg.prcFmlaNm_H1.setNameForMessage("Formula Name");
        scrnMsg.prcFmlaDescTxt_H1.setNameForMessage("Formula Description");
        scrnMsg.actvFlg_H1.setNameForMessage("Active");
        scrnMsg.effFromDt_H1.setNameForMessage("Effective Date From");
        scrnMsg.effThruDt_H1.setNameForMessage("Effective Date To");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).prcFmlaTpCd_A1.setNameForMessage("Formula Description");
            scrnMsg.A.no(i).prcCatgNm_A1.setNameForMessage("Source Price List");
            scrnMsg.A.no(i).prcFmlaOpCd_A1.setNameForMessage("Operator");
            scrnMsg.A.no(i).prcFmlaNum_A1.setNameForMessage("Factor Value");
            scrnMsg.A.no(i).prcFmlaTpCd_A1.setNameForMessage("Custom Price Function Name");
        }
    }
}
