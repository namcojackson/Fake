/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1240;

import static business.servlet.NSAL1240.constant.NSAL1240Constant.*;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1240.NSAL1240CMsg;
import business.servlet.NSAL1240.common.NSAL1240CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Config# Search Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Hitachi         A.Kohinata      Create          N/A
 * 2016/03/29   Hitachi         M.Gotou         Update          QC#4949
 * 2016/05/16   Hitachi         T.Tomita        Update          QC#4578
 * 2016/09/23   Hitachi         J.Sumi          Update          QC#12582
 *</pre>
 */
public class NSAL1240_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1240BMsg scrnMsg = (NSAL1240BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        NSAL1240CommonLogic.setInputParam(scrnMsg, arg);

        NSAL1240CMsg bizMsg = new NSAL1240CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1240BMsg scrnMsg = (NSAL1240BMsg) bMsg;
        NSAL1240CMsg bizMsg = (NSAL1240CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1240CommonLogic.initialize(this);
        NSAL1240CommonLogic.screenItemControl(this, scrnMsg);
        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        scrnMsg.setFocusItem(scrnMsg.xxScrItem29Txt_H1);
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL1240BMsg scrnMsg = (NSAL1240BMsg) bMsg;
        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        scrnMsg.xxScrItem29Txt_H1.setNameForMessage("Configuration#");
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
        scrnMsg.serNum_H.setNameForMessage("Serial#");
        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        scrnMsg.xxScrItem29Txt_H2.setNameForMessage("Install Base ID");
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
        // START 2016/09/23 J.Sumi [QC#12582, MOD]
        scrnMsg.mdseCd_H.setNameForMessage("Item Code");
        // END 2016/09/23 J.Sumi [QC#12582, MOD]
        scrnMsg.t_MdlNm_H.setNameForMessage("Model Name");
        scrnMsg.shipDt_H1.setNameForMessage("Shipped From");
        scrnMsg.shipDt_H2.setNameForMessage("Shipped Thru");
        scrnMsg.svcMachUsgStsCd_H.setNameForMessage("Install Base Usage");
        scrnMsg.xxChkBox_H1.setNameForMessage("Install Base Status");
        scrnMsg.xxChkBox_H2.setNameForMessage("Install Base Status");
        scrnMsg.xxChkBox_H3.setNameForMessage("Install Base Status");
        scrnMsg.xxChkBox_H4.setNameForMessage("Install Base Status");
        scrnMsg.xxChkBox_H5.setNameForMessage("Install Base Status");
        scrnMsg.xxChkBox_H6.setNameForMessage("Install Base Status");
        scrnMsg.xxChkBox_H7.setNameForMessage("Install Base Status");
        scrnMsg.xxChkBox_H8.setNameForMessage("Install Base Status");
        scrnMsg.xxChkBox_HA.setNameForMessage("Machine Allocated");
        scrnMsg.xxChkBox_HB.setNameForMessage("Machine Allocated");
        scrnMsg.xxChkBox_HC.setNameForMessage("Machine Only");
        scrnMsg.stkStsCd_H.setNameForMessage("Stock Status");
        scrnMsg.curLocNum_H1.setNameForMessage("WH Code");
        scrnMsg.curLocNum_H2.setNameForMessage("Sub WH Code");
        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        scrnMsg.xxScrItem29Txt_H3.setNameForMessage("Solution#");
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
        scrnMsg.svcSlnNm_H.setNameForMessage("Solution Name");
        scrnMsg.dsContrNum_H.setNameForMessage("Contract#");
        scrnMsg.ownrAcctNum_H.setNameForMessage("IB Owner Account#");
        // del start 2016/05/16 CSA Defect#4578
//        scrnMsg.ownrLocNum_H.setNameForMessage("IB Owner Location#");
        // del end 2016/05/16 CSA Defect#4578
        scrnMsg.billToAcctNum_H.setNameForMessage("IB Bill To Account#");
        // mod start 2016/05/16 CSA Defect#4578
        scrnMsg.indBillToLocNum_H.setNameForMessage("IB Bill To Location#");
        // mod end 2016/05/16 CSA Defect#4578
        scrnMsg.curLocAcctNum_H.setNameForMessage("IB Current Loc Account#");
        // mod start 2016/05/16 CSA Defect#4578
        scrnMsg.indCurLocNum_H.setNameForMessage("IB Current Location#");
        // mod end 2016/05/16 CSA Defect#4578
    }
}
