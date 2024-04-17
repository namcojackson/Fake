/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8840;

import static business.servlet.NYEL8840.constant.NYEL8840Constant.BIZ_ID;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.MESSAGE_KIND_ERROR;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0002E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0003W;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8840.NYEL8840CMsg;
import business.servlet.NYEL8840.common.NYEL8840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8840Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/26   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NYEL8840Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8840BMsg scrnMsg = (NYEL8840BMsg) bMsg;

        //TODO [Template] change the item name(xxx_H)
        //scrnMsg.addCheckItem(scrnMsg.xxx_H);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8840BMsg scrnMsg = (NYEL8840BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NYEL8840CMsg bizMsg = new NYEL8840CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8840BMsg scrnMsg = (NYEL8840BMsg) bMsg;
        NYEL8840CMsg bizMsg  = (NYEL8840CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        //TODO [Template] Please change your Table ID and MsgArray.
        //NYEL8840CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        //TODO [Template] focus set
        //scrnMsg.setFocusItem(scrnMsg.xxx);
        

        NYEL8840CommonLogic.protectFields(scrnMsg);
//        NYEL8840CommonLogic.initCmnBtnProp(this);

        NYEL8840CommonLogic.buttonCtrl(this, scrnMsg);
//        if (scrnMsg.xxPageShowOfNum.getValueInt() == scrnMsg.xxMaxSrchCnt.getValueInt()) {
////        if (scrnMsg.A.getValidCount() == scrnMsg.A.length()) {
//            setButtonEnabled("Add", false);
//        } else {
//            setButtonEnabled("Add", true);
//        }
//
//        if (scrnMsg.A.getValidCount() == 0) {
//            setButtonEnabled("Delete", false);
//        } else {
//            setButtonEnabled("Delete", true);
//        }
        NYEL8840CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        scrnMsg.addCheckItem(scrnMsg.xxWfAsgCd_F);
        scrnMsg.putErrorScreen();

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.setFocusItem(scrnMsg.xxWfAsgCd);
        scrnMsg.xxWfAsgCd_FK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxWfAsgCd_LK.setValue(ZYPConstant.FLG_ON_Y);
    }

}
