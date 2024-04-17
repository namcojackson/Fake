/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2100;

import static business.servlet.NFBL2100.constant.NFBL2100Constant.*;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2100.NFBL2100CMsg;
import business.servlet.NFBL2100.common.NFBL2100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.pagenation.S21SequentialSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Lease Buyout Approve List
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/22   Hitachi         K.Kojima        Create          N/A
 * 2016/04/05   Hitachi         K.Kojima        Update          CSA QC#5531
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 *</pre>
 */
public class NFBL2100_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2100BMsg scrnMsg = (NFBL2100BMsg) bMsg;

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof String) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum, (String) prm[0]);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum_H, (String) prm[0]);
                }
            }
        }

        NFBL2100CMsg bizMsg = new NFBL2100CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2100BMsg scrnMsg = (NFBL2100BMsg) bMsg;
        NFBL2100CMsg bizMsg = (NFBL2100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFBL2100CommonLogic.setupScreenItems(this, scrnMsg);
        // START 2016/04/05 K.Kojima [QC#5531,ADD]
        NFBL2100CommonLogic.setupListTable(scrnMsg);
        // END 2016/04/05 K.Kojima [QC#5531,ADD]
        scrnMsg.setFocusItem(scrnMsg.apDsWfStsCd_SV);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFBL2100BMsg scrnMsg = (NFBL2100BMsg) bMsg;

        scrnMsg.apDsWfStsCd_SV.setNameForMessage("Workflow Status");
        // START 2017/12/22 [QC#22831, MOD]
        scrnMsg.vndCd.setNameForMessage("Supplier Site");
        // END   2017/12/22 [QC#22831, MOD]
        scrnMsg.xxFromDt.setNameForMessage("Order Date(From)");
        scrnMsg.xxToDt.setNameForMessage("Order Date(To)");
        scrnMsg.cpoOrdNum.setNameForMessage("Order#");
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21SequentialSearchPagenationScrnSupport.getCurrentPageFieldName());

    }

}
