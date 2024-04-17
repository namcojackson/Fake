/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1360;

import static business.servlet.NPAL1360.constant.NPAL1360Constant.BIZ_APP_ID;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.DISPLAY_ORD_QTY;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.MIN_QTY;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.NPAM0079E;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.NPAM1185E;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1360.NPAL1360CMsg;
import business.servlet.NPAL1360.common.NPAL1360CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1360 Kitting Work Order Entry
 * Function Name : Component
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/13/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1360Scrn00_Component extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;
        // Order Quantity Check
        if (ZYPCommonFunc.hasValue(scrnMsg.fnshGoodsOrdQty.getValue())) {
            if (BigDecimal.ZERO.compareTo(scrnMsg.fnshGoodsOrdQty.getValue()) >= 0) {
                scrnMsg.fnshGoodsOrdQty.setErrorInfo(1, NPAM1185E, new String[] {DISPLAY_ORD_QTY, MIN_QTY});
            }
        }
        // Request Completion Date Check
        if (ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt.getValue())) {
            if (ZYPDateUtil.compare(scrnMsg.slsDt.getValue(), scrnMsg.rqstRcvDt.getValue()) > 0) {
                scrnMsg.rqstRcvDt.setErrorInfo(1, NPAM0079E);
            }
        }

        scrnMsg.addCheckItem(scrnMsg.fnshGoodsMdseCd);
        scrnMsg.addCheckItem(scrnMsg.fnshGoodsOrdQty);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.cpltRtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;

        NPAL1360CMsg bizMsg = new NPAL1360CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;
        NPAL1360CMsg bizMsg  = (NPAL1360CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.fnshGoodsMdseCd);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.cpltRtlSwhCd);
        scrnMsg.putErrorScreen();

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);
        NPAL1360CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.wrkOrdNum);
    }
}
