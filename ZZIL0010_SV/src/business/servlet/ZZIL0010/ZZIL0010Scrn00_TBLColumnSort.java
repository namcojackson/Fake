/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.ZZIL0010;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZIL0010.ZZIL0010CMsg;
import business.servlet.ZZIL0010.common.ZZIL0010CommonLogic;
import business.servlet.ZZIL0010.constant.ZZIL0010Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class ZZIL0010Scrn00_TBLColumnSort extends S21CommonHandler implements ZZIL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0010BMsg scrnMsg = (ZZIL0010BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.interfaceId);
        scrnMsg.addCheckItem(scrnMsg.transactionId);
        scrnMsg.addCheckItem(scrnMsg.processedFlag_PS);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_RF);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_R1);
        scrnMsg.addCheckItem(scrnMsg.xxToDt_RT);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_R2);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_UF);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_U1);
        scrnMsg.addCheckItem(scrnMsg.xxToDt_UT);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_U2);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0010BMsg scrnMsg = (ZZIL0010BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        // Table:A
        if ("A".equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {

                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                ZZIL0010CMsg bizMsg = new ZZIL0010CMsg();
                bizMsg.setBusinessID("ZZIL0010");
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0010BMsg scrnMsg = (ZZIL0010BMsg) bMsg;
        ZZIL0010CMsg bizMsg = (ZZIL0010CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        ZZIL0010CommonLogic.setTableColor(scrnMsg, bizMsg);

        scrnMsg.addCheckItem(scrnMsg.interfaceId);
        scrnMsg.addCheckItem(scrnMsg.transactionId);
        scrnMsg.addCheckItem(scrnMsg.processedFlag_PS);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_RF);
        scrnMsg.addCheckItem(scrnMsg.xxToDt_RT);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_UF);
        scrnMsg.addCheckItem(scrnMsg.xxToDt_UT);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_R1);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_R2);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_U1);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_U2);
        scrnMsg.putErrorScreen();
    }

}
