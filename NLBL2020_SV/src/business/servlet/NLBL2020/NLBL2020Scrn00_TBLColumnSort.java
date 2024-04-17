/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL2020;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL2020.NLBL2020CMsg;
import business.servlet.NLBL2020.common.NLBL2020CommonLogic;
import business.servlet.NLBL2020.constant.NLBL2020Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 2022/10/18   Hitachi         A.Kohinata      Update          QC#60559
 *</pre>
 */
public class NLBL2020Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        if (NLBL2020Constant.TAB_SO_LIST.equals(scrnMsg.xxDplyTab.getValue())) {

            if (NLBL2020Bean.A.equals(sortTblNm)) {

                if (scrnMsg.A.getValidCount() > 0) {

                    scrnMsg.xxSortTblNm.setValue(sortTblNm);
                    scrnMsg.xxSortItemNm.setValue(sortItemNm);
                    scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                    NLBL2020CMsg bizMsg = new NLBL2020CMsg();
                    bizMsg.setBusinessID(NLBL2020Constant.BUSINESS_ID);
                    bizMsg.setFunctionCode("20");
                    EZDMsg.copy(scrnMsg, null, bizMsg, null);

                    return bizMsg;
                }
            }

        } else {

            if (NLBL2020Bean.B.equals(sortTblNm)) {

                if (scrnMsg.B.getValidCount() > 0) {

                    scrnMsg.xxSortTblNm.setValue(sortTblNm);
                    scrnMsg.xxSortItemNm.setValue(sortItemNm);
                    scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                    NLBL2020CMsg bizMsg = new NLBL2020CMsg();
                    bizMsg.setBusinessID(NLBL2020Constant.BUSINESS_ID);
                    bizMsg.setFunctionCode("20");
                    EZDMsg.copy(scrnMsg, null, bizMsg, null);

                    return bizMsg;
                }
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;
        NLBL2020CMsg bizMsg = (NLBL2020CMsg) cMsg;

        if (bizMsg != null) {

            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // control image file of sort column (Gif file. ASC or DESC.)
        if (NLBL2020Constant.TAB_SO_LIST.equals(scrnMsg.xxDplyTab.getValue())) {

            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        } else {

            S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.B.no(0).getBaseContents());
        }

        // screen ctrl
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL2020Constant.BUSINESS_ID);
        // mod start 2022/10/18 QC#60559
        //NLBL2020CommonLogic.ctrlScrnItem(scrnMsg, this, funcList);
        NLBL2020CommonLogic.ctrlScrnItemProtection(scrnMsg, this, funcList);
        // mod end 2022/10/18 QC#60559
    }
}
