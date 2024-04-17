/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2010   Fujitsu         K.Tajima        Create          N/A
 *</pre>
 */
package business.servlet.NWDL0260;

import static business.servlet.NWDL0260.common.NWDL0260CommonLogic.createCMsgForSearch;
import static business.servlet.NWDL0260.common.NWDL0260CommonLogic.setGuiAttr;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWDL0260.NWDL0260CMsg;
import business.servlet.NWDL0260.constant.NWDL0260Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class NWDL0260Scrn00_TBLColumnSort extends S21CommonHandler implements NWDL0260Constant, S21TableColumnSortConstant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing to do.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWDL0260BMsg scrnMsg = (NWDL0260BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm   = param.getSingleValue(SORT_TABLE_NAME);
        String sortItemNm  = param.getSingleValue(SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(ORDER_BY);

        // Table:A
        if ("A".equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {

                setValue(scrnMsg.xxSortTblNm, sortTblNm);
                setValue(scrnMsg.xxSortItemNm, sortItemNm);
                setValue(scrnMsg.xxSortOrdByTxt, sortOrderBy);

                return createCMsgForSearch(scrnMsg);
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWDL0260BMsg scrnMsg = (NWDL0260BMsg) bMsg;
        NWDL0260CMsg bizMsg = (NWDL0260CMsg) cMsg;

        if (bizMsg != null) {

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            // set GUI attributes.
            setGuiAttr(this, scrnMsg, ScrnId.Scrn00);

            if ("A".equals(scrnMsg.xxSortTblNm.getValue())) {

                // control image file of sort column (ASC or DESC.)
                S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());

                // set focus.
                scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A1);
            }
        }
    }

}
