/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0420;

import static business.servlet.NSBL0420.common.NSBL0420CommonLogic.addCheckItem;
import static business.servlet.NSBL0420.common.NSBL0420CommonLogic.initialControlScreen;
import static business.servlet.NSBL0420.constant.NSBL0420Constant.BUSINESS_ID;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0420.NSBL0420CMsg;
import business.servlet.NSBL0420.common.NSBL0420CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Mods Parts Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Tsuchida      Create          N/A
 * 2016/07/13   Hitachi         O.Okuma         Update          QC#11685
 * 2016/09/29   Hitachi         J.Sumi          Update          QC#12582
 *</pre>
 */
public class NSBL0420_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSBL0420BMsg scrnMsg = (NSBL0420BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        int index = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, (EZDBStringItem) params[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcModDtlPk, (EZDBBigDecimalItem) params[index++]);

        NSBL0420CMsg bizMsg = new NSBL0420CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSBL0420BMsg scrnMsg = (NSBL0420BMsg) bMsg;
        NSBL0420CMsg bizMsg  = (NSBL0420CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        initialControlScreen(this, scrnMsg);
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        NSBL0420CommonLogic.setFocus(scrnMsg);
        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }
    }
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSBL0420BMsg scrnMsg = (NSBL0420BMsg) bMsg;
        // START 2016/09/29 J.Sumi [QC#12582, MOD]
        scrnMsg.mdseCd_TF.setNameForMessage("Item Code");
        // END 2016/09/29 J.Sumi [QC#12582, MOD]
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).svcModQty_A.setNameForMessage("Qty");
         // START 2016/09/29 J.Sumi [QC#12582, DEL]
         // scrnMsg.A.no(i).mdseCd_A.setNameForMessage("Mdse Code");
         // scrnMsg.A.no(i).mdseDescShortTxt_A.setNameForMessage("Mdse Name");
         // END 2016/09/29 J.Sumi [QC#12582, DEL]
        }
    }

}
