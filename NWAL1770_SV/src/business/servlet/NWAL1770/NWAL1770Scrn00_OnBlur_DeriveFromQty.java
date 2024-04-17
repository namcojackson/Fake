/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.MSG_PARAM_ITEM_NUM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.ZZM9000E;
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
 * 2016/01/26   Fujitsu         T.Yoshida       Create          N/A
 * 2016/08/10   Fujitsu         S.Iidaka        Update          S21_NA#13173
 * 2017/10/24   Hitachi         J.Kim           Update          QC#21312
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 **</pre>
 */
public class NWAL1770Scrn00_OnBlur_DeriveFromQty extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        int slctLine = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(slctLine);

        // Check Qty
        scrnMsg.addCheckItem(scrnMsg.B.no(slctLine).ordCustUomQty_B);
        scrnMsg.putErrorScreen();

        // Check MDSE
        scrnMsg.B.no(slctLine).mdseCd_B.clearErrorInfo();
        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(slctLine).mdseCd_B)) {
            scrnMsg.B.no(slctLine).mdseCd_B.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_ITEM_NUM });
            scrnMsg.addCheckItem(scrnMsg.B.no(slctLine).mdseCd_B);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        NWAL1770CMsg bizMsg = new NWAL1770CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        setResult(ZYPConstant.FLG_OFF_N);

        int slctLine = scrnMsg.xxCellIdx.getValueInt();

        NWAL1770CommonLogicForScrnFields.setEnableByAllRgtnAuth(this, scrnMsg);// S21_NA#13173 Add
        scrnMsg.addCheckItem(scrnMsg.B.no(slctLine).mdseCd_B);
        // START 2017/10/24 J.Kim [QC#21312,ADD]
        scrnMsg.addCheckItem(scrnMsg.B.no(slctLine).ordCustUomQty_B);
        // END 2017/10/24 J.Kim [QC#21312,ADD]
        scrnMsg.putErrorScreen();

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        NWAL1770CommonLogic.checkItemLineWarning(scrnMsg);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            Object[] params = NWAL1770CommonLogic.getParamNWAL1870(scrnMsg);
            setArgForSubScreen(params);
            return;
        }

        NWAL1770CommonLogicForScrnFields.setProtect(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.B.no(slctLine).custUomCd_B);
    }
}
