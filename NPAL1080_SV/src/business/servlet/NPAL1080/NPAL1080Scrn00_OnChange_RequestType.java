/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1080.NPAL1080CMsg;
import business.servlet.NPAL1080.common.NPAL1080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS       Yasushi Nomura        Create          N/A
 * 10/25/2017   CITS            S.Katsuma       Update          QC#21896
 *</pre>
 */
public class NPAL1080Scrn00_OnChange_RequestType extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        
        //QC:7389
        scrnMsg.dsCondConstCd_SE.clear();
        scrnMsg.dsCondConstCd_CD.clear();
        scrnMsg.rqstRcvDtTxt_DI.clear();
        scrnMsg.D.clear();
        
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        NPAL1080CMsg bizMsg = new NPAL1080CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1080CMsg bizMsg = (NPAL1080CMsg) cMsg;
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2017/10/25 S.Katsuma QC#21896 ADD
//        NPAL1080CommonLogic.controlLineType(scrnMsg);
        NPAL1080CommonLogic.controlLineType(this, scrnMsg);
        // END 2017/10/25 S.Katsuma QC#21896 ADD

        // QC:7389
        if (ZYPCommonFunc.hasValue(scrnMsg.dsCondConstCd_SE)) {
            scrnMsg.dsCondConstCd_SE.setInputProtected(false);
        } else {
            scrnMsg.dsCondConstCd_SE.setInputProtected(true);
        }

    }
}
