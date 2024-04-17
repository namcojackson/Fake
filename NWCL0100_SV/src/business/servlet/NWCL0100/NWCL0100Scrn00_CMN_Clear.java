/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0100;

import static business.servlet.NWCL0100.constant.NWCL0100Constant.BIZ_ID;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWCL0100.NWCL0100CMsg;
import business.servlet.NWCL0100.common.NWCL0100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWCL0100Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/04/18   CITS            J.Evangelista   Create          QC#59934
 *</pre>
 */
public class NWCL0100Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0100BMsg scrnMsg = (NWCL0100BMsg) bMsg;
        NWCL0100CMsg bizMsg = new NWCL0100CMsg();

        scrnMsg.clear();
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);

        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(scrnMsg.billToAcctNum_LK, "link");

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0100BMsg scrnMsg = (NWCL0100BMsg) bMsg;
        NWCL0100CMsg bizMsg = (NWCL0100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWCL0100CommonLogic.initCmnBtnProp(this);

        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.xxBillToAcctCdSrchTxt_H0);
    }

}
