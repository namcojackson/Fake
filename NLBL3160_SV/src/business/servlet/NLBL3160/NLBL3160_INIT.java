/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3160;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3160.NLBL3160CMsg;
import business.servlet.NLBL3160.common.NLBL3160CommonLogic;
import business.servlet.NLBL3160.constant.NLBL3160Constant;

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
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/13   CITS            S.Katsuma       Create          N/A
 * 2022/10/07   Hitachi         T.NEMA          Update          QC#60607
 *</pre>
 */
public class NLBL3160_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NLBL3160Constant.BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;
        NLBL3160CMsg bizMsg = new NLBL3160CMsg();

        scrnMsg.clear();
        ZYPTableUtil.clear(scrnMsg.A);

        // Set parameter
        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(scrnMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSelFlg_H, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_S, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_DC, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_NA, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_F, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_RS, ZYPConstant.FLG_ON_Y);
        // START 2022/11/07 T.NEMA [QC#60607, ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_I, ZYPConstant.FLG_OFF_N);
        // END   2022/11/07 T.NEMA [QC#60607, ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_S, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_DC, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_NA, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_F, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_RS, BigDecimal.ZERO);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL3160Constant.BUSINESS_ID);

        if (funcList != null && !funcList.isEmpty()) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxFuncId, funcList.get(funcList.size() - 1));
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxFuncId)) {
            if (NLBL3160Constant.FUNC_ID_COORD.equals(scrnMsg.xxFuncId.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.psnCd, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnFirstMidLastNm, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getFullName());
            }
        }

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param00 = (EZDBStringItem) params[0];

            if (ZYPCommonFunc.hasValue(param00)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.cpoNum, param00);
            }
        }

        bizMsg.setBusinessID(NLBL3160Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;
        NLBL3160CMsg bizMsg  = (NLBL3160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3160CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.cpoNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;

        scrnMsg.cpoNum.setNameForMessage("Sales Order Number");
        scrnMsg.psnCd.setNameForMessage("Coordinator");
        scrnMsg.dsOrdCatgDescTxt.setNameForMessage("Order Category");
        scrnMsg.schdCoordStsCd.setNameForMessage("Scheduling Status");
        scrnMsg.svcConfigMstrPk.setNameForMessage("Config ID");
        scrnMsg.shpgSvcLvlCd.setNameForMessage("Service Level");
        scrnMsg.lineBizTpCd.setNameForMessage("LOB");
        scrnMsg.mdseCd.setNameForMessage("Item Number");
        scrnMsg.dsAcctNum.setNameForMessage("Customer");
        scrnMsg.stCd.setNameForMessage("Deliver to ST");
        scrnMsg.svcBrCd.setNameForMessage("Deliver to BR");
        scrnMsg.rddDt_FR.setNameForMessage("Req. Del. Date From");
        scrnMsg.rddDt_TO.setNameForMessage("Req. Del. Date To");
        scrnMsg.rtlWhCd.setNameForMessage("Warehouse");
        scrnMsg.rtlSwhCd.setNameForMessage("Sub Warehouse");
        scrnMsg.nextActDt_FR.setNameForMessage("Next Action Date From");
        scrnMsg.nextActDt_TO.setNameForMessage("Next Action Date To");
        scrnMsg.xxPageShowCurNum_A.setNameForMessage("Current Page Number");
    }
}
