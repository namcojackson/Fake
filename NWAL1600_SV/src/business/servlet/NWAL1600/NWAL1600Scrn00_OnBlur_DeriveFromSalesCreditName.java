/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1600;

import static business.servlet.NWAL1600.constant.NWAL1600Constant.BIZ_ID;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.SCRN_TITLE;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL1600.NWAL1600CMsg;
import business.servlet.NWAL1600.common.NWAL1600CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/19/2016   Fujitsu         T.Ishii         Create          N/A
 *</pre>
 */
public class NWAL1600Scrn00_OnBlur_DeriveFromSalesCreditName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;
        NWAL1600CommonLogic.ezItemValidationAllItem(scrnMsg, false);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;

        int index = getButtonSelectNumber();
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(index).tocNm_A)) {
            return null;
        }

        NWAL1600CMsg bizMsg = new NWAL1600CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        bizMsg.xxCellIdx.setValue(getButtonSelectNumber());

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;
        NWAL1600CMsg bizMsg = (NWAL1600CMsg) cMsg;

        int index = getButtonSelectNumber();
        if (bizMsg == null) {
            setResult(ZYPConstant.FLG_OFF_N);
            scrnMsg.setFocusItem(scrnMsg.A.no(index).tocNm_A);
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1600CommonLogic.addCheckSlsRepItems(scrnMsg);
        scrnMsg.putErrorScreen();

        setResult(scrnMsg.xxCondCd.getValue());

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxCondCd.getValue())) {

            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

            ZYPTableUtil.clear(scrnMsg.P);

            Object[] params = new Object[7];

            String selectStr = NWAL1600CommonLogic.setSlsrepSelectStr(getGlobalCompanyCode(), index);
            List<Object[]> whereList = NWAL1600CommonLogic.setSlsrepWhereList(scrnMsg, null, scrnMsg.A.no(index).tocNm_A.getValue());
            List<Object[]> tblColumnList = NWAL1600CommonLogic.setSlsrepTblColumnList(scrnMsg);
            List<Object[]> sortCondList = NWAL1600CommonLogic.setSlsrepSortList(scrnMsg);

            params[0] = "S"; // 0.Suffix
            params[1] = SCRN_TITLE; // 1.Screen Title
            params[2] = selectStr; // 2.Table Name
            params[3] = whereList; // 3.Search Criteria
            params[4] = tblColumnList; // 4.Column
            params[5] = sortCondList; // 5.Sort Order
            params[6] = scrnMsg.S; // 6.Output(S)

            setArgForSubScreen(params);
        } else {

            scrnMsg.setFocusItem(scrnMsg.A.no(index).tocNm_A);
        }
    }
}
