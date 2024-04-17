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
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NYEL8840_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/26   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NYEL8840_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8840BMsg scrnMsg = (NYEL8840BMsg) bMsg;
        NYEL8840CMsg bizMsg = new NYEL8840CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8840BMsg scrnMsg = (NYEL8840BMsg) bMsg;
        NYEL8840CMsg bizMsg = (NYEL8840CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NYEL8840CommonLogic.protectFields(scrnMsg);
        NYEL8840CommonLogic.initCmnBtnProp(this);

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
        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

        NYEL8840CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        scrnMsg.setFocusItem(scrnMsg.xxWfAsgCd);
        scrnMsg.xxWfAsgCd_FK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxWfAsgCd_LK.setValue(ZYPConstant.FLG_ON_Y);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NYEL8840BMsg scrnMsg = (NYEL8840BMsg) bMsg;

        scrnMsg.xxWfAsgCd_F.setNameForMessage("Assigner");
        scrnMsg.xxWfAsgCd.setNameForMessage("Assignee");
        scrnMsg.effFromDt.setNameForMessage("Delegate Period From Date");
        scrnMsg.effThruDt.setNameForMessage("Delegate Period To Date");
        scrnMsg.wfDescTxt.setNameForMessage("Comment");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("Check Box");
        }
    }
}
