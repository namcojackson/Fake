/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1360;

import static business.servlet.NPAL1360.constant.NPAL1360Constant.BIZ_APP_ID;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.NPAM0046E;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1360.NPAL1360CMsg;
import business.servlet.NPAL1360.common.NPAL1360CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1360 Kitting Work Order Entry
 * Function Name : Submit
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/13/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1360Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.fnshGoodsOrdQty)) {
            if (BigDecimal.ZERO.compareTo(scrnMsg.fnshGoodsOrdQty.getValue()) > 0) {
                scrnMsg.wrkOrdNum.setErrorInfo(1, NPAM0046E);
            }
        }

        scrnMsg.addCheckItem(scrnMsg.dsWrkOrdTpCd_SL);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.cpltRtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt);
        scrnMsg.addCheckItem(scrnMsg.fnshGoodsMdseCd);
        scrnMsg.addCheckItem(scrnMsg.fnshGoodsOrdQty);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).splyRtlSwhCd_A1);
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;

        NPAL1360CMsg bizMsg = new NPAL1360CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;
        NPAL1360CMsg bizMsg  = (NPAL1360CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.wrkOrdNum);
        scrnMsg.addCheckItem(scrnMsg.dsWrkOrdTpCd_SL);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.cpltRtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt);
        scrnMsg.addCheckItem(scrnMsg.fnshGoodsMdseCd);
        scrnMsg.addCheckItem(scrnMsg.fnshGoodsOrdQty);
        scrnMsg.addCheckItem(scrnMsg.serNum);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).splyRtlSwhCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A1);
        }

        scrnMsg.putErrorScreen();

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);
        NPAL1360CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.wrkOrdNum);

    }
}
