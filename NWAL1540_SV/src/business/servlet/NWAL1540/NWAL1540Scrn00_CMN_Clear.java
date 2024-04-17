/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1540;

import static business.servlet.NWAL1540.constant.NWAL1540Constant.BIZ_ID;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NWAL1540.NWAL1540CMsg;
//import business.servlet.NWAL1540.constant.NWAL1540Constant;

import business.blap.NWAL1540.NWAL1540CMsg;
import business.servlet.NWAL1540.common.NWAL1540CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2022/04/18   CITS            J.Evangelista   Update          QC#59934
 *</pre>
 */
public class NWAL1540Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1540BMsg scrnMsg = (NWAL1540BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // START 2022/04/18 J.Evangelista [QC#59934,ADD]
        NWAL1540BMsg scrnMsg = (NWAL1540BMsg) bMsg;

        NWAL1540CMsg bizMsg = new NWAL1540CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        // END 2022/04/18 J.Evangelista [QC#59934,ADD]

        // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//        return null;
        return bizMsg;
        // END 2022/04/18 J.Evangelista [QC#59934,MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // START 2022/04/18 J.Evangelista [QC#59934,ADD]
        NWAL1540BMsg scrnMsg = (NWAL1540BMsg) bMsg;
        NWAL1540CMsg bizMsg = (NWAL1540CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL1540CommonLogic.initCmnBtnProp(this);
        NWAL1540CommonLogic.initBizBtnProp(this, scrnMsg);
        NWAL1540CommonLogic.setInputControl(scrnMsg);
        NWAL1540CommonLogic.setGpControl(this, scrnMsg);// 2016/03/10 S21_NA#2939

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            return;
        }

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A, 1);
        // END 2022/04/18 J.Evangelista [QC#59934,ADD]
    }
}
