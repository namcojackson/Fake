/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL2020;

import static business.servlet.NLBL2020.constant.NLBL2020Constant.BUSINESS_ID;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.FUNC_PRNT;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.FUNC_UPD;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.ZZZM9003I;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL2020.NLBL2020CMsg;
import business.servlet.NLBL2020.common.NLBL2020CommonLogic;
import business.servlet.NLBL2020.constant.NLBL2020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.printing.S21PrintingBusinessApInOutEJBLocal;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 2016/12/07   CITS            Y.Fujii         Update          R360
 * 2018/03/23   CITS            S.Katsuma       Update          QC#24895
 * 2018/03/30   CITS            K.Fukumura      Update          QC#25023
 *</pre>
 */
public class NLBL2020Scrn00_Print extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // QC#25023 2018/03/30 Start
//        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;
//
//        // START 2018/03/23 S.Katsuma [QC#24895,DEL]
////        NLBL2020CommonLogic.chkSoLineCheck(scrnMsg);
//        // END 2018/03/23 S.Katsuma [QC#24895,DEL]
//        NLBL2020CommonLogic.addCheckItemApply(scrnMsg);
//        NLBL2020CommonLogic.addCheckItemSoDetail(scrnMsg);
//
//        List<Integer> list = new ArrayList<Integer>();
//        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//            NLBL2020_ABMsg record = scrnMsg.A.no(i);
//            if (!ZYPCommonFunc.hasValue(record.xxExstFlg_A1) || !ZYPCommonFunc.hasValue(record.soNum_A1)) {
//                continue;
//            }
//            list.add(i);
//        }
//
//        if (list.size() > 1) {
//            for(int i = 0; i < list.size(); i++) {
//                int line = list.get(i);
//                scrnMsg.A.no(line).xxExstFlg_A1.setErrorInfo(1, NLBL2020Constant.NLBM1357E);
//                scrnMsg.addCheckItem(scrnMsg.A.no(line).xxExstFlg_A1);
//            }
//            scrnMsg.setMessageInfo(NLBL2020Constant.ZZM9037E, null, 1);
//        }
//        scrnMsg.putErrorScreen();
     // QC#25023 2018/03/30 End
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;

        NLBL2020CMsg bizMsg = new NLBL2020CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;
        NLBL2020CMsg bizMsg = (NLBL2020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        NLBL2020CommonLogic.ctrlScrnItemProtection(scrnMsg, this, funcList);

        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {

            NLBL2020CommonLogic.addCheckTableItem(scrnMsg);
            scrnMsg.putErrorScreen();

        } else {

            bizMsg = (NLBL2020CMsg) new S21PrintingBusinessApInOutEJBLocal().executeBlap(ctx, bizMsg, BUSINESS_ID, FUNC_PRNT);

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {
                // execute file download
                String tempFilePath = bizMsg.xxFileData.getTempFilePath();
                executeDownloadPdf(tempFilePath, true);

                scrnMsg.setMessageInfo(ZZZM9003I, new String[] {"Print" });
                scrnMsg.setFocusItem(scrnMsg.trxHdrNum_H1);
            }
        }
    }
}
