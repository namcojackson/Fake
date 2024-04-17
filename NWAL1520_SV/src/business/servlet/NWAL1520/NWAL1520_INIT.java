/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1520;

import static business.servlet.NWAL1520.constant.NWAL1520Constant.SCRN_ID_00;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.BIZ_ID;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.NWAM0298E;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.ZZSM4300E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1520.NWAL1520CMsg;
import business.servlet.NWAL1520.common.NWAL1520CommonLogic;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

/**
 *<pre>
 * NWAL1520_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/29   Fujitsu         A.Suda          Create          N/A
 *</pre>
 */
public class NWAL1520_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

        // set Function IDs.
        final NWAL1520_BBMsgArray functionIdArray = ((NWAL1520BMsg) bMsg).B;
        List<String> functionIdList = getUserProfileService().getFunctionCodeListForBizAppId("NWAL1520");
        for (int i = 0; i < functionIdList.size(); i++) {
            functionIdArray.no(i).xxFuncId.setValue(functionIdList.get(i));
        }
        functionIdArray.setValidCount(functionIdList.size());

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;
        NWAL1520CMsg bizMsg = new NWAL1520CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();


        if (params != null && params.length > 1) {
            setValue(scrnMsg.cpoOrdNum_A,     (EZDBStringItem) params[0]);
            setValue(scrnMsg.cpoOrdNum_V,     (EZDBStringItem) params[0]);
            setValue(scrnMsg.cpoOrdNum_BK,    (EZDBStringItem) params[0]);
            setValue(scrnMsg.configCatgCd_A,  (EZDBStringItem) params[1]);
            setValue(scrnMsg.configCatgCd_V,  (EZDBStringItem) params[1]);
            setValue(scrnMsg.configCatgCd_BK, (EZDBStringItem) params[1]);
            setValue(scrnMsg.condSqlTxt_A,    (String) params[2]);
            setValue(scrnMsg.condSqlTxt_V,    (String) params[2]);
            setValue(scrnMsg.condSqlTxt_BK,   (String) params[2]);
            setValue(scrnMsg.xxDplyCtrlFlg,   FLG_ON_Y);

        } else {

            // To process the menu transition.
            scrnMsg.cpoOrdNum_A.clear();
            scrnMsg.cpoOrdNum_V.clear();
            scrnMsg.cpoOrdNum_BK.clear();
            scrnMsg.configCatgCd_A.clear();
            scrnMsg.configCatgCd_V.clear();
            scrnMsg.configCatgCd_BK.clear();
            scrnMsg.condSqlTxt_A.clear();
            scrnMsg.condSqlTxt_V.clear();
            scrnMsg.condSqlTxt_BK.clear();

        }

        NWAL1520CommonLogic.initScrnValues(scrnMsg);

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;
        NWAL1520CMsg bizMsg = (NWAL1520CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg.getValue()) && !NWAL1520CommonLogic.hasUpperTabFuncId(scrnMsg.B)) {
            S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
            scrnMsg.setMessageInfo(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }
        if (FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg.getValue()) && !hasValue(scrnMsg.cpoOrdNum_V)) {
            scrnMsg.setMessageInfo(NWAM0298E, new String[] {scrnMsg.cpoOrdNum_V.getNameForMessage() });
        }

        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        NWAL1520CommonLogic.initCmnBtnProp(this);
        NWAL1520CommonLogic.setScreenItemInit(this, scrnMsg);
        NWAL1520CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.L, "A_Right");
        NWAL1520CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.V, "V_Right");
        NWAL1520CommonLogic.setInputProtectedforCheckBox(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum_V);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;
        scrnMsg.cpoOrdNum_V.setNameForMessage("Order Number");
        scrnMsg.cpoOrdNum_A.setNameForMessage("Order Number");
        scrnMsg.condSqlTxt_V.setNameForMessage("Line Number");
        scrnMsg.condSqlTxt_A.setNameForMessage("Line Number");

        scrnMsg.hldRsnDescTxt_V.setNameForMessage("Hold Name");
        scrnMsg.hldApplyRsnDescTxt_V.setNameForMessage("Hold Reason Code");
        scrnMsg.hldUntilDt_V.setNameForMessage("Hold Until Date");

        scrnMsg.hldRsnDescTxt_A.setNameForMessage("Hold Name");
        scrnMsg.hldApplyRsnDescTxt_A.setNameForMessage("Hold Reason Code");
        scrnMsg.hldUntilDt_A.setNameForMessage("Hold Until Date");
        scrnMsg.hldDtlTxt_A.setNameForMessage("Hold Comments");

        scrnMsg.relMemoTxt_RH.setNameForMessage("Release Comments");
    }

}
