/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3200;

import static business.servlet.NLBL3200.constant.NLBL3200Constant.BUSINESS_ID;
import static business.servlet.NLBL3200.constant.NLBL3200Constant.FUNC_UPD;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3200.NLBL3200CMsg;
import business.servlet.NLBL3200.common.NLBL3200CommonLogic;
import business.servlet.NLBL3200.constant.NLBL3200Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/11/05   FUJITSU         H.Tomimatsu     Create          N/A
 *</pre>
 */
public class NLBL3200Scrn00_Picking extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {


    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        
        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;

        NLBL3200CMsg bizMsg = new NLBL3200CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
    	

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;
        NLBL3200CMsg bizMsg = (NLBL3200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL3200Constant.BUSINESS_ID);
        NLBL3200CommonLogic.ctrlScrnItemProtection(scrnMsg, this, funcList);

        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {

            NLBL3200CommonLogic.addCheckTableItem(scrnMsg);
            scrnMsg.putErrorScreen();


        } else {
        	
        	// TODO 印刷処理 作成保留
//            bizMsg = (NLBL3200CMsg) new S21PrintingBusinessApInOutEJBLocal().executeBlap(ctx, bizMsg, NLBL3200Constant.BUSINESS_ID, NLBL3200Constant.BUSINESS_ID);
//
//            EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//            if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {
//            	
//                // execute file download
//                String tempFilePath = bizMsg.xxFileData.getTempFilePath();
//                executeDownloadPdf(tempFilePath, true);
//
                scrnMsg.setMessageInfo(NLBL3200Constant.ZZZM9003I, new String[] {"Picking Doc Print" });
                scrnMsg.setFocusItem(scrnMsg.trxHdrNum_H1);
//            }
        }
        

    }
}
