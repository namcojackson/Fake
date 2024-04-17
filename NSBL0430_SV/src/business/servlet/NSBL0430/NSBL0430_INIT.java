/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0430;

import static business.servlet.NSBL0430.constant.NSBL0430Constant.BIZ_ID;
import static business.servlet.NSBL0430.constant.NSBL0430Constant.EZD_FUNC_CD_INQ;
import static business.servlet.NSBL0430.common.NSBL0430CommonLogic.initialControlScreen;
import static business.servlet.NSBL0430.common.NSBL0430CommonLogic.setFocus;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSBL0430.NSBL0430CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/28   Hitachi         O.Okuma         Create          N/A
 * 2016/04/18   Hitachi         M.Gotou         Update          QC#3425
 * 2016/07/12   Hitachi         O.Okuma         Update          QC#11685
 * 2016/09/30   Hitachi         J.Sumi          Update          QC#12582
 *</pre>
 */
public class NSBL0430_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0430BMsg scrnMsg = (NSBL0430BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();

        int index = 0;
        setValue(scrnMsg.svcModPk, (EZDBBigDecimalItem) params[index]);

        NSBL0430CMsg bizMsg = new NSBL0430CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0430BMsg scrnMsg = (NSBL0430BMsg) bMsg;
        NSBL0430CMsg bizMsg  = (NSBL0430CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);
        setFocus(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSBL0430BMsg scrnMsg = (NSBL0430BMsg) bMsg;
        // mod start 2016/04/18 CSA Defect#3425
        scrnMsg.mdseCd_F.setNameForMessage("Mdse Code");
        // mod start 2016/04/18 CSA Defect#3425
        // START 2016/09/30 J.Sumi [QC#12582 , MOD]
        scrnMsg.mdseCd.setNameForMessage("Item Code");
        // END 2016/09/30 J.Sumi [QC#12582 , MOD]
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).svcModFromSerNum_A.setNameForMessage("Serial# Range From");
            scrnMsg.A.no(i).svcModToSerNum_A.setNameForMessage("Serial# Range To");
            scrnMsg.A.no(i).autoCratCallFlg_A.setNameForMessage("Auto Create Calls");
            scrnMsg.A.no(i).autoCratRfrsTmgCd_A.setNameForMessage("Auto Create Refresh Rate");
        }
    }
}
