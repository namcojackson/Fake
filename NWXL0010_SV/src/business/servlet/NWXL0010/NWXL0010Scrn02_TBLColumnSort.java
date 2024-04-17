/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/10/2010   Fujitsu         K.Tajima        Create          N/A
 * 08/05/2010   Fujitsu         R.Nakamura      Update          QC#9078
 *</pre>
 */
package business.servlet.NWXL0010;

import static business.servlet.NWXL0010.common.NWXL0010CommonLogic.newCMsgForSearch;
import static business.servlet.NWXL0010.common.NWXL0010CommonLogic.setGuiAttr;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWXL0010.NWXL0010CMsg;
import business.servlet.NWXL0010.constant.NWXL0010Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class NWXL0010Scrn02_TBLColumnSort extends S21CommonHandler implements NWXL0010Constant, S21TableColumnSortConstant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing to do.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWXL0010BMsg scrnMsg = (NWXL0010BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(ORDER_BY);

        // Table:B
        if ("B".equals(sortTblNm)) {
            if (scrnMsg.B.getValidCount() > 0) {

                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                return newCMsgForSearch(scrnMsg);
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWXL0010BMsg scrnMsg = (NWXL0010BMsg) bMsg;
        NWXL0010CMsg bizMsg = (NWXL0010CMsg) cMsg;

        if (bizMsg != null) {

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            // set GUI attribute.
            setGuiAttr(ScrnId.NWXL0010Scrn02, scrnMsg, this);

            if ("B".equals(scrnMsg.xxSortTblNm.getValue())) {

                // control image file of sort column (ASC or DESC.)
                S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.B.no(0).getBaseContents());
            }
        }
    }

}
