/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0050;

import static business.servlet.NWCL0050.constant.NWCL0050Constant.BIZ_ID;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.NWCM0100E;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0050.NWCL0050CMsg;
import business.servlet.NWCL0050.common.NWCL0050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/26   Fujitsu         T.Yoshida       Create          N/A
 * 2018/05/28   Fujitsu         Y.Matsui        Update          QC#26342
 *</pre>
 */
public class NWCL0050Scrn00_Search_Invoice extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0050BMsg scrnMsg = (NWCL0050BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.invNum);
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        scrnMsg.addCheckItem(scrnMsg.conslBillNum);
        scrnMsg.addCheckItem(scrnMsg.billToDsAcctNum);
        scrnMsg.addCheckItem(scrnMsg.billToDsAcctNm);
        scrnMsg.addCheckItem(scrnMsg.billToLocNum);
        scrnMsg.addCheckItem(scrnMsg.xxSerNumSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.dsContrNum);
        scrnMsg.addCheckItem(scrnMsg.invAvgGrpNum);
        scrnMsg.addCheckItem(scrnMsg.xxUrnNum);
        scrnMsg.addCheckItem(scrnMsg.conslBillInvDt_FR);
        scrnMsg.addCheckItem(scrnMsg.conslBillInvDt_TO);
        scrnMsg.addCheckItem(scrnMsg.xxCratDt_FR);
        scrnMsg.addCheckItem(scrnMsg.xxCratDt_TO);
        scrnMsg.putErrorScreen();

        // checkstyle error ignore
        if (!ZYPCommonFunc.hasValue(scrnMsg.invNum) 
                && !ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum) 
                && !ZYPCommonFunc.hasValue(scrnMsg.conslBillNum) 
                && !ZYPCommonFunc.hasValue(scrnMsg.billToDsAcctNum)
                && !ZYPCommonFunc.hasValue(scrnMsg.billToDsAcctNm) 
                && !ZYPCommonFunc.hasValue(scrnMsg.billToLocNum) 
                && !ZYPCommonFunc.hasValue(scrnMsg.xxSerNumSrchTxt) 
                && !ZYPCommonFunc.hasValue(scrnMsg.dsContrNum)
                && !ZYPCommonFunc.hasValue(scrnMsg.invAvgGrpNum) 
                && !ZYPCommonFunc.hasValue(scrnMsg.xxUrnNum) 
                && !ZYPCommonFunc.hasValue(scrnMsg.invPrtBatTpCd) 
                && !ZYPCommonFunc.hasValue(scrnMsg.invPrtBrCd) 
                && !ZYPCommonFunc.hasValue(scrnMsg.invProcTpCd) 
                && !ZYPCommonFunc.hasValue(scrnMsg.ordClsCd) 
                && !ZYPCommonFunc.hasValue(scrnMsg.invSmryLineTpCd) 
                && !ZYPCommonFunc.hasValue(scrnMsg.conslBillInvDt_FR) 
                && !ZYPCommonFunc.hasValue(scrnMsg.conslBillInvDt_TO)
                // START 2018/05/28 Y.Matsui [QC#26342,ADD]
                && !ZYPCommonFunc.hasValue(scrnMsg.custIssPoNum)
                // END   2018/05/28 Y.Matsui [QC#26342,ADD]
                && !ZYPCommonFunc.hasValue(scrnMsg.xxCratDt_FR) 
                && !ZYPCommonFunc.hasValue(scrnMsg.xxCratDt_TO)) {

            scrnMsg.setMessageInfo(NWCM0100E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0050BMsg scrnMsg = (NWCL0050BMsg) bMsg;
        scrnMsg.xxDplyCtrlFlg_EM.clear();

        NWCL0050CMsg bizMsg = new NWCL0050CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0050BMsg scrnMsg = (NWCL0050BMsg) bMsg;
        NWCL0050CMsg bizMsg = (NWCL0050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        NWCL0050CommonLogic.setAuthority(this);
        NWCL0050CommonLogic.controlFileLink(scrnMsg);
        NWCL0050CommonLogic.setTblBackColor(scrnMsg);
        NWCL0050CommonLogic.setProtected(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.invNum);

        // clear sort icons
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
