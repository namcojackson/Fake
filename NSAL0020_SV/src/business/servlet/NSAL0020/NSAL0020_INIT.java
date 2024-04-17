/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0020;

import static business.servlet.NSAL0020.constant.NSAL0020Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0020.NSAL0020CMsg;
import business.servlet.NSAL0020.common.NSAL0020CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/04   Hitachi         T.Yonekura      Create          N/A
 * 2015/10/19   Hitachi         Y.Tsuchimoto    Update          NA(No Mark up comment)
 * 2016/04/20   Hitachi         M.Gotou         Update          QC6749
 * 2016/05/13   Hitachi         T.Tomita        Update          QC#4578
 * 2016/09/15   Hitachi         N.Arai          Update          QC#11616
 * 2016/11/28   Hitachi         K.Kojima        Update          QC#16116
 * 2016/12/13   Hitachi         K.Ochiai        Update          QC#16563
 *</pre>
 */
public class NSAL0020_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/11/28 K.Kojima [QC#16116,MOD]
        // return;
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
        // END 2016/11/28 K.Kojima [QC#16116,MOD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;
        NSAL0020CMsg bizMsg = NSAL0020CommonLogic.setRequestData_SearchCommon();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;
        NSAL0020CMsg bizMsg = (NSAL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0020CommonLogic.screenControlProcess(this, scrnMsg);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;

        scrnMsg.serNum.setNameForMessage("Serial#");
        scrnMsg.svcSlnSq.setNameForMessage("Solution#");
        scrnMsg.svcSlnNm.setNameForMessage("Solution Name");
        scrnMsg.svcConfigMstrPk.setNameForMessage("Configuration#");
        scrnMsg.svcMachMstrPk.setNameForMessage("Install Base ID");
        scrnMsg.xxChkBox.setNameForMessage("Show Terminated Products");
        scrnMsg.istlDt_FR.setNameForMessage("Install Date From");
        scrnMsg.istlDt_TO.setNameForMessage("Install Date Thru");
        scrnMsg.svcMachMstrStsCd_PS.setNameForMessage("Install Base Status");
        scrnMsg.svcLicCnt.setNameForMessage("Software License#");
        // START 2016/05/13 T.Tomita [QC#4578, MOD]
        scrnMsg.xxComnScrColValTxt_O.setNameForMessage("Account Owner Name");
        scrnMsg.ownrAcctNum.setNameForMessage("Account#");
//        scrnMsg.ownrLocNum.setNameForMessage("Address(Loc#)");
        scrnMsg.xxComnScrColValTxt_B.setNameForMessage("Account Bill To Name/Address ");
        scrnMsg.billToAcctNum.setNameForMessage("Account#");
        scrnMsg.indBillToLocNum.setNameForMessage("Address(Loc#)");
        scrnMsg.xxComnScrColValTxt_C.setNameForMessage("Current Location Name/Address");
        scrnMsg.curLocAcctNum.setNameForMessage("Account#");
        scrnMsg.indCurLocNum.setNameForMessage("Address(Loc#)");
        // END 2016/05/13 T.Tomita [QC#4578, MOD]
        scrnMsg.t_MdlNm.setNameForMessage("Service Model Name");
        // mod start 2016/04/20 CSA Defect#6749
        scrnMsg.mdseCd.setNameForMessage("Item#");
        // START 2016/09/15 N.Arai [QC#11616, MOD]
        //scrnMsg.mdseNm.setNameForMessage("Item Description");
        scrnMsg.mdseDescShortTxt.setNameForMessage("Item Description");
        // END 2016/09/15 N.Arai [QC#11616, MOD]
        // mod end 2016/04/20 CSA Defect#6749
        scrnMsg.coaMdseTpCd_PS.setNameForMessage("Mdse Type");
        scrnMsg.cpoOrdNum.setNameForMessage("Order#");
        // mod start 2016/04/20 CSA Defect#6749
        scrnMsg.custMachCtrlNum.setNameForMessage("IB External Ref#");
        // mod end 2016/04/20 CSA Defect#6749
        // START 2016/12/13 K.Ochiai [QC#16563, MOD]
        scrnMsg.custIssPoNum.setNameForMessage("End Customer PO#");
        // END 2016/12/13 K.Ochiai [QC#16563, MOD]
        scrnMsg.dsContrNum.setNameForMessage("Contract#");
        scrnMsg.ctacPsnLastNm.setNameForMessage("Contact Last Name");
        // mod start 2016/04/20 CSA Defect#6749
        scrnMsg.svcCtacTpCd_PS.setNameForMessage("IB Contact Type");
        // mod end 2016/04/20 CSA Defect#6749
        scrnMsg.tocNm.setNameForMessage("Sales Rep Name");
    }

}
