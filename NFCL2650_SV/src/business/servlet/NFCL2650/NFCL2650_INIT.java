/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2650;

import static business.servlet.NFCL2650.constant.NFCL2650Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2650.NFCL2650CMsg;
import business.servlet.NFCL2650.common.NFCL2650CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         M.Ohno          Create          N/A
 * 2016/10/20   Hitachi         K.Kojima        Update          QC#13259
 * 2016/11/25   Fujitsu         T.Murai         Update          QC#13259
 *</pre>
 */
public class NFCL2650_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2650BMsg scrnMsg = (NFCL2650BMsg) bMsg;

        NFCL2650CMsg bizMsg = new NFCL2650CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2650BMsg scrnMsg = (NFCL2650BMsg) bMsg;
        NFCL2650CMsg bizMsg = (NFCL2650CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFCL2650CommonLogic.initControlScreen(this, scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg msg) {
        NFCL2650BMsg scrnMsg = (NFCL2650BMsg) msg;
        // START 2016/10/20 K.Kojima [QC#13259,MOD]
        // scrnMsg.dsAcctNum_FR.setNameForMessage("Bill to Customer Account From");
        // scrnMsg.dsAcctNum_TO.setNameForMessage("Bill to Customer Account To");
        // scrnMsg.dsAcctNm_F2.setNameForMessage("Bill to Customer Account Name From");
        // scrnMsg.dsAcctNm_T2.setNameForMessage("Bill to Customer Account Name To");
        // scrnMsg.billToCustCd.setNameForMessage("Bill to Customer Code");
        scrnMsg.dsAcctNum_FR.setNameForMessage("Customer Number From");
        scrnMsg.dsAcctNum_TO.setNameForMessage("Customer Number To");
        scrnMsg.dsAcctNm_F2.setNameForMessage("Customer Name From");
        scrnMsg.dsAcctNm_T2.setNameForMessage("Customer Name To");
        scrnMsg.locNum.setNameForMessage("Location"); // MOD 2016/11/25 T.Murai [QC#13259] billToCustCd -> locNum
        // END 2016/10/20 K.Kojima [QC#13259,MOD]
    }
}
