/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6160;

import static business.servlet.NMAL6160.constant.NMAL6160Constant.BIZ_ID;
import static business.servlet.NMAL6160.constant.NMAL6160Constant.BIZ_ID_NMAL2630;
import static business.servlet.NMAL6160.constant.NMAL6160Constant.BIZ_ID_NMAL6760;
import static business.servlet.NMAL6160.constant.NMAL6160Constant.PARAM_LG;
import static business.servlet.NMAL6160.constant.NMAL6160Constant.SCRN_ID_00;
import static business.servlet.NMAL6160.constant.NMAL6160Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6160.NMAL6160CMsg;
import business.servlet.NMAL6160.common.NMAL6160CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Multi Candinate Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/15   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL6160_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6160BMsg scrnMsg = (NMAL6160BMsg) bMsg;
        NMAL6160CMsg bizMsg = new NMAL6160CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == PARAM_LG) {
            NMAL6160CommonLogic.setItemInValueErrorInfo(scrnMsg.xxModeNm23Txt, (EZDBStringItem) params[0], scrnMsg);
            ZYPEZDItemValueSetter.setValue(scrnMsg.bizId, (EZDBStringItem) params[1]);
            NMAL6160CommonLogic.setItemInValueErrorInfo(scrnMsg.xxScrItem61Txt, (EZDBStringItem) params[2], scrnMsg);

            if (!ZYPCommonFunc.hasValue((String) params[3])) {
                scrnMsg.setMessageInfo(ZZM9000E, new String[] {"Suffix" });
                return null;
            }
            EZDMsg.copy((EZDMsgArray) params[4], (String) params[3], scrnMsg.Z, "Z");

        } else {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {scrnMsg.xxModeNm23Txt.getNameForMessage() });
            return null;
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6160BMsg scrnMsg = (NMAL6160BMsg) bMsg;
        NMAL6160CMsg bizMsg = (NMAL6160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6160CommonLogic.initCmnBtnProp(this);

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        if (BIZ_ID_NMAL6760.equals(bizMsg.bizId.getValue())) {
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        } else if (BIZ_ID_NMAL2630.equals(bizMsg.bizId.getValue())) {
            tblColor.setAlternateRowsBG("B", scrnMsg.B);
        } else {
            tblColor.setAlternateRowsBG("C", scrnMsg.C);
        }

        scrnMsg.setFocusItem(scrnMsg.xxDsMsgEntryTxt);
        scrnMsg.xxModeNm23Txt.setInputProtected(true);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL6160BMsg scrnMsg = (NMAL6160BMsg) bMsg;

        scrnMsg.xxModeNm23Txt.setNameForMessage("Mode Name");
        scrnMsg.bizId.setNameForMessage("Business Id");
        scrnMsg.xxScrItem61Txt.setNameForMessage("Header Description");
        scrnMsg.xxDsMsgEntryTxt.setNameForMessage("Filter Param");
    }
}
