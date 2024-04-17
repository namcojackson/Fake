/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0110;

import static business.servlet.NWCL0110.constant.NWCL0110Constant.NWCM0001E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0110.NWCL0110CMsg;
import business.servlet.NWCL0110.common.NWCL0110CommonLogic;
import business.servlet.NWCL0110.constant.NWCL0110Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/24   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
public class NWCL0110Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0110BMsg scrnMsg = (NWCL0110BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.invPrtBatTpCd);
        scrnMsg.addCheckItem(scrnMsg.xxInvNum_FR);
        scrnMsg.addCheckItem(scrnMsg.xxInvNum_TO);
        scrnMsg.addCheckItem(scrnMsg.invPrtBrCd);
        scrnMsg.addCheckItem(scrnMsg.conslBillNum_FR);
        scrnMsg.addCheckItem(scrnMsg.conslBillNum_TO);
        scrnMsg.addCheckItem(scrnMsg.procDt_FR);
        scrnMsg.addCheckItem(scrnMsg.procDt_TO);
        scrnMsg.addCheckItem(scrnMsg.invNum_FR);
        scrnMsg.addCheckItem(scrnMsg.invNum_TO);
        scrnMsg.putErrorScreen();

        // checkstyle error ignore
        if (!ZYPCommonFunc.hasValue(scrnMsg.invPrtBatTpCd)
         && !ZYPCommonFunc.hasValue(scrnMsg.invPrtBrCd)
         && !ZYPCommonFunc.hasValue(scrnMsg.xxInvNum_FR)
         && !ZYPCommonFunc.hasValue(scrnMsg.xxInvNum_TO)
         && !ZYPCommonFunc.hasValue(scrnMsg.invPrtBrCd)
         && !ZYPCommonFunc.hasValue(scrnMsg.conslBillNum_FR)
         && !ZYPCommonFunc.hasValue(scrnMsg.conslBillNum_TO)
         && !ZYPCommonFunc.hasValue(scrnMsg.invNum_FR)
         && !ZYPCommonFunc.hasValue(scrnMsg.invNum_TO)
         && !ZYPCommonFunc.hasValue(scrnMsg.procDt_FR)
         && !ZYPCommonFunc.hasValue(scrnMsg.procDt_TO)
         ) {

            scrnMsg.setMessageInfo(NWCM0001E);
            throw new EZDFieldErrorException();
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxInvNum_FR) && ZYPCommonFunc.hasValue(scrnMsg.xxInvNum_TO)) {
            if (scrnMsg.xxInvNum_FR.getValue().compareTo(scrnMsg.xxInvNum_TO.getValue()) > 0) {
                scrnMsg.setMessageInfo(NWCL0110Constant.ZZZM9010E, new String[] {"Request#From", "Request#To"});
                throw new EZDFieldErrorException();
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.conslBillNum_FR) && ZYPCommonFunc.hasValue(scrnMsg.conslBillNum_TO)) {
            if (scrnMsg.conslBillNum_FR.getValue().compareTo(scrnMsg.conslBillNum_TO.getValue()) > 0) {
                scrnMsg.setMessageInfo(NWCL0110Constant.ZZZM9010E, new String[] {"Bill#From", "Bill#To"});
                throw new EZDFieldErrorException();
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.invNum_FR) && ZYPCommonFunc.hasValue(scrnMsg.invNum_TO)) {
            if (scrnMsg.invNum_FR.getValue().compareTo(scrnMsg.invNum_TO.getValue()) > 0) {
                scrnMsg.setMessageInfo(NWCL0110Constant.ZZZM9010E, new String[] {"Invoice#From", "Invoice#To"});
                throw new EZDFieldErrorException();
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.procDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.procDt_TO)) {
            if (scrnMsg.procDt_FR.getValue().compareTo(scrnMsg.procDt_TO.getValue()) > 0) {
                scrnMsg.setMessageInfo(NWCL0110Constant.NWCM0002E, new String[] {"Output Date"});
                throw new EZDFieldErrorException();
            }
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0110BMsg scrnMsg = (NWCL0110BMsg) bMsg;

        NWCL0110CMsg bizMsg = new NWCL0110CMsg();
        bizMsg.setBusinessID(NWCL0110Constant.BIZ_ID);
        bizMsg.setFunctionCode(NWCL0110Constant.FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0110BMsg scrnMsg = (NWCL0110BMsg) bMsg;
        NWCL0110CMsg bizMsg = (NWCL0110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        NWCL0110CommonLogic.setTblBackColor(scrnMsg);
        NWCL0110CommonLogic.controlDtl(this, scrnMsg);

//        scrnMsg.setFocusItem(scrnMsg.xxTpCd);
        // clear sort icons
//        S21SortColumnIMGController.clearIMG(NWCL0110Constant.SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
