/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.servlet.NWAL1770.common.NWAL1770CommonLogic;
import business.servlet.NWAL1770.common.NWAL1770CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Fujitsu         T.Yoshida       Create          N/A
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 *</pre>
 */
public class NWAL1770Scrn00_OnBlur_DeriveFromItem extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        int slctLine = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(slctLine);

        scrnMsg.addCheckItem(scrnMsg.B.no(slctLine).mdseCd_B);
        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(slctLine).ordCustUomQty_B)) {
            scrnMsg.addCheckItem(scrnMsg.B.no(slctLine).ordCustUomQty_B);
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        int slctLine = scrnMsg.xxCellIdx.getValueInt();
        scrnMsg.B.no(slctLine).mnfItemCd_B.clear();
        scrnMsg.B.no(slctLine).custMdseCd_B.clear();

        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(slctLine).mdseCd_B)) {
            return null;
        }

        NWAL1770CMsg bizMsg = new NWAL1770CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        setResult(ZYPConstant.FLG_OFF_N);
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        int slctLine = scrnMsg.xxCellIdx.getValueInt();
        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(slctLine).mdseCd_B)) {
            scrnMsg.setFocusItem(scrnMsg.B.no(slctLine).ordCustUomQty_B);
            return;
        }

        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.B.no(slctLine).mdseCd_B);
        scrnMsg.putErrorScreen();

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        NWAL1770CommonLogic.checkItemLineWarning(scrnMsg);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NWAL1770CommonLogic.getParamNMAL6800(scrnMsg);
            setArgForSubScreen(params);
            return;
        }

        // Add Start 2016/09/29 S21_NA#11655
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg_MN.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NWAL1770CommonLogic.getParamNMAL6800ForMan(scrnMsg);
            setArgForSubScreen(params);
            return;
        }
        // Add End 2016/09/29 S21_NA#11655

        scrnMsg.setFocusItem(scrnMsg.B.no(slctLine).ordCustUomQty_B);
        NWAL1770CommonLogic.setTblBackColorForItem(scrnMsg);
        NWAL1770CommonLogicForScrnFields.setProtect(this, scrnMsg);
    }
}
