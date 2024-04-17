/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0510;

import static business.servlet.NSAL0510.constant.NSAL0510Constant.BIZ_ID;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0510.NSAL0510CMsg;
import business.servlet.NSAL0510.common.NSAL0510CommonLogic;
import business.servlet.NSAL0510.constant.NSAL0510Constant.FUNC;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Sub Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/06   Hitachi         T.Tsuchida      Create          N/A
 * 2016/01/08   Hitachi         T.Tsuchida      Update          QC#2844
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 *</pre>
 */
public class NSAL0510_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0510BMsg scrnMsg = (NSAL0510BMsg) bMsg;

        NSAL0510CMsg bizMsg = new NSAL0510CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0510BMsg scrnMsg = (NSAL0510BMsg) bMsg;
        NSAL0510CMsg bizMsg = (NSAL0510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0510CommonLogic.initControlCommonButton(this);
        NSAL0510CommonLogic.initCommonButton(this);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0510BMsg scrnMsg = (NSAL0510BMsg) bMsg;
        // START 2017/12/22 [QC#22831, MOD]
        scrnMsg.vndCd_H.setNameForMessage("Supplier Site");
        // END   2017/12/22 [QC#22831, MOD]
        scrnMsg.dsAcctNum_H.setNameForMessage("Account");
        scrnMsg.dsContrNum_H.setNameForMessage("Contract#");
        scrnMsg.dsContrCtrlStsNm_H.setNameForMessage("Contract Status");
        scrnMsg.dlrFleetNum_H.setNameForMessage("Dealer Fleet#");
        scrnMsg.serNum_H.setNameForMessage("Serial#");
        scrnMsg.mdlActvFlg_H.setNameForMessage("Active Status");
        scrnMsg.t_MdlNm_H.setNameForMessage("Model Name");
    }

}
