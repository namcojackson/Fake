/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1350;

import static business.servlet.NPAL1350.constant.NPAL1350Constant.BIZ_APP_ID;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.FUNCTION_CD_PRINT;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_STS;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import java.util.List;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1350.NPAL1350CMsg;
//import business.servlet.NPAL1350.constant.NPAL1350Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/03/14   CITS            K.Fukumura      Create          N/A
 *</pre>
 */
public class NPAL1350Scrn00_Print extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1350BMsg scrnMsg = (NPAL1350BMsg) bMsg;


        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        
        NPAL1350BMsg scrnMsg = (NPAL1350BMsg) bMsg;

        NPAL1350CMsg bizMsg = new NPAL1350CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_PRINT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1350BMsg scrnMsg = (NPAL1350BMsg) bMsg;
        NPAL1350CMsg bizMsg  = (NPAL1350CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),DEL]
//        bizMsg = (NPAL1360CMsg) new S21PrintingBusinessApInOutEJBLocal().executeBlap(ctx, bizMsg, BIZ_APP_ID, FUNCTION_CD_PRINT);
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),DEL]

        if (scrnMsg.wrkOrdNum.isError()) {
            scrnMsg.addCheckItem(scrnMsg.wrkOrdNum);
            scrnMsg.putErrorScreen();
            return;
        }

        if (!"E".equals(bizMsg.getMessageKind())) {
            String tempFilePath = bizMsg.xxFileData.getTempFilePath();
            executeDownloadPdf(tempFilePath, true);
        }

        scrnMsg.putErrorScreen();

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.wrkOrdNum);
    }
}
