/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFDL0020.NFDL0020CMsg;
//import business.servlet.NFDL0020.constant.NFDL0020Constant;

import business.blap.NFDL0020.NFDL0020CMsg;
import business.servlet.NFDL0020.common.NFDL0020CommonLogic;
import static business.servlet.NFDL0020.constant.NFDL0020Constant.SCRN_ID_00;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 06/15/2018   Hitachi         Y.Takeno        Update          QC#24792
 * 2019/02/12   Fujitsu         S.Ohki          Update          QC#30143
 * 2022/08/08   Hitachi         S.Naya          Update          QC#56154
 *</pre>
 */
public class NFDL0020Scrn00_Click_TransactionSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.invDueDt_A1);
        scrnMsg.addCheckItem(scrnMsg.invDueDt_A2);
        // START 2018/06/15 [QC#24792, ADD]
        scrnMsg.addCheckItem(scrnMsg.dsContrNum_AH);
        scrnMsg.addCheckItem(scrnMsg.serNum_AH);
        scrnMsg.addCheckItem(scrnMsg.arCustRefNum_AH);
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum_AH);
        // END   2018/06/15 [QC#24792, ADD]
        // START 2022/08/08 [QC#56154, ADD]
        scrnMsg.addCheckItem(scrnMsg.grpInvNum_AH);
        // END   2022/08/08 [QC#56154, ADD]

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        NFDL0020CMsg bizMsg = new NFDL0020CMsg();
        bizMsg.setBusinessID("NFDL0020");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        NFDL0020CMsg bizMsg  = (NFDL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFDL0020CommonLogic.initialize(this, scrnMsg);
        // START 2019/02/12 S.Ohki [QC#30143,ADD]
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // END 2019/02/12 S.Ohki [QC#30143,ADD]
    }
}
