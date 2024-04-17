/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3050.common.NLBL3050CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Distribution Coordinator Work Bench
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/21/2016   CSAI            Y.Imazu         Create          QC#2048
 *</pre>
 */
public class NLBL3050Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3050BMsg scrnMsg = (NLBL3050BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);

        if (NLBL3050Bean.A.equals(sortTblNm) && 0 < scrnMsg.A.getValidCount()) {

            scrnMsg.xxListNum_A1.clear();
            NLBL3050CommonLogic.tblColumnSort(scrnMsg, scrnMsg.A, scrnMsg.A.no(0).getBaseContents(), param, ctx);

        } else if (NLBL3050Bean.B.equals(sortTblNm) && 0 < scrnMsg.B.getValidCount()) {

            scrnMsg.xxListNum_B1.clear();
            NLBL3050CommonLogic.tblColumnSort(scrnMsg, scrnMsg.B, scrnMsg.B.no(0).getBaseContents(), param, ctx);

        } else if (NLBL3050Bean.C.equals(sortTblNm) && 0 < scrnMsg.C.getValidCount()) {

            scrnMsg.xxListNum_C1.clear();
            NLBL3050CommonLogic.tblColumnSort(scrnMsg, scrnMsg.C, scrnMsg.C.no(0).getBaseContents(), param, ctx);

        } else if (NLBL3050Bean.D.equals(sortTblNm) && 0 < scrnMsg.D.getValidCount()) {

            scrnMsg.xxListNum_D1.clear();
            NLBL3050CommonLogic.tblColumnSort(scrnMsg, scrnMsg.D, scrnMsg.D.no(0).getBaseContents(), param, ctx);

        } else if (NLBL3050Bean.F.equals(sortTblNm) && 0 < scrnMsg.F.getValidCount()) {

            scrnMsg.xxListNum_F1.clear();
            NLBL3050CommonLogic.tblColumnSort(scrnMsg, scrnMsg.F, scrnMsg.F.no(0).getBaseContents(), param, ctx);
        }

        NLBL3050CommonLogic.controlScreenFields(scrnMsg);
    }
}
