/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3050;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3050.NLBL3050CMsg;
import business.servlet.NLBL3050.common.NLBL3050CommonLogic;
import business.servlet.NLBL3050.constant.NLBL3050Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Distribution Coordinator Work Bench
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/19/2015   Fujitsu         W.Honda         Create          N/A
 * 01/21/2016   CSAI            Y.Imazu         Update          QC#2048
 * 10/26/2016   CSAI            Y.Imazu         Update          QC#9760
 *</pre>
 */
public class NLBL3050_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NLBL3050Constant.BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3050BMsg scrnMsg = (NLBL3050BMsg) bMsg;
        NLBL3050CMsg bizMsg = new NLBL3050CMsg();

        scrnMsg.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.B);
        ZYPTableUtil.clear(scrnMsg.C);
        ZYPTableUtil.clear(scrnMsg.D);
        ZYPTableUtil.clear(scrnMsg.F);
        ZYPTableUtil.clear(scrnMsg.P);
        ZYPTableUtil.clear(scrnMsg.L);
        ZYPTableUtil.clear(scrnMsg.T);

        // Set parameter
        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(scrnMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_OA, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_IA, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_B, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_OB, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_IB, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_L, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_OL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_IL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_T, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_OT, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_IT, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(scrnMsg.usrId, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL3050Constant.BUSINESS_ID);

        if (funcList != null && !funcList.isEmpty()) {

            for (String funcId : funcList) {

                if (NLBL3050Constant.FUNC_ID_ALL.equals(funcId)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.asgTaskFlg, ZYPConstant.FLG_OFF_N);
                    break;

                } else if (NLBL3050Constant.FUNC_ID_ASSIGN.equals(funcId)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.asgTaskFlg, ZYPConstant.FLG_ON_Y);
                }
            }
        }

        // Assign Task Only
        if(ZYPCommonFunc.hasValue(scrnMsg.asgTaskFlg) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.asgTaskFlg.getValue())){

            ZYPEZDItemValueSetter.setValue(scrnMsg.psnCd_H, scrnMsg.usrId.getValue());
        }

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            EZDBStringItem param00 = (EZDBStringItem) params[0];

            if (ZYPCommonFunc.hasValue(param00)) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.cpoNum_H, param00);
            }

            EZDBStringItem param03 = (EZDBStringItem) params[3];

            if (ZYPCommonFunc.hasValue(param03)) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtlWhSrchTxt_H, param03);
            }
        }

        bizMsg.setBusinessID(NLBL3050Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3050BMsg scrnMsg = (NLBL3050BMsg) bMsg;
        NLBL3050CMsg bizMsg  = (NLBL3050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3050CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.cpoNum_H);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLBL3050BMsg scrnMsg = (NLBL3050BMsg) bMsg;

        scrnMsg.cpoNum_H.setNameForMessage("Sales Order Number");
        scrnMsg.xxRtlWhSrchTxt_H.setNameForMessage("Warehouse");
        scrnMsg.xxRtlWhSrchTxt_H.setNameForMessage("Coordinator");
    }
}
