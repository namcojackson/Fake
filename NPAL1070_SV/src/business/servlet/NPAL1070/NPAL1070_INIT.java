/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1070;

import static business.servlet.NPAL1070.constant.NPAL1070Constant.BIZ_APP_ID;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.FUNCTION_CD_SEARCH;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1070.NPAL1070CMsg;
import business.servlet.NPAL1070.common.NPAL1070CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1070 Min-Max Planning Entry
 * Function Name : Init
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/24/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1070_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length == 4) {
            EZDBStringItem param01 = (EZDBStringItem) params[0];
            EZDBStringItem param02 = (EZDBStringItem) params[1];
            EZDBStringItem param03 = (EZDBStringItem) params[2];
            EZDBStringItem param04 = (EZDBStringItem) params[3];
            ZYPEZDItemValueSetter.setValue(scrnMsg.mrpPlnNm_H1, param01);
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_H2, param02);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_H3, param03);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd_H4, param04);
        }
        NPAL1070CMsg bizMsg = new NPAL1070CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;
        NPAL1070CMsg bizMsg  = (NPAL1070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NPAL1070CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);
        // START 2023/04/17 S.Dong [QC#61348,ADD]
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxSelFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSelFlg, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxWrnSkipFlg_SB.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg_SB, ZYPConstant.FLG_OFF_N);
        }
        // END 2023/04/17 S.Dong [QC#61348,ADD]
        // Set Focus (Plan Name)
        scrnMsg.setFocusItem(scrnMsg.mrpPlnNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg scrnMsg) {
        NPAL1070CommonLogic.setNameForMessage((NPAL1070BMsg) scrnMsg);

    }
}
