package business.servlet.NMAL6830;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6830.NMAL6830CMsg;
import business.servlet.NMAL6830.common.NMAL6830CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Cost Update Group Search Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL6830Scrn00_TBLColumnSort extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6830BMsg scrnMsg = (NMAL6830BMsg) bMsg;
        if (scrnMsg.A.getValidCount() == 0) {
            return null;
        }

        NMAL6830CMsg bizMsg = new NMAL6830CMsg();
        bizMsg.setBusinessID("NMAL6830");
        bizMsg.setFunctionCode("20");

        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortTblNm, param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortItemNm, param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSortOrdByTxt, param.getSingleValue(S21TableColumnSortConstant.ORDER_BY));
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6830BMsg scrnMsg = (NMAL6830BMsg) bMsg;
        NMAL6830CMsg bizMsg = (NMAL6830CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL6830CommonLogic.initScrn(scrnMsg, scrnMsg.A.no(0).getBaseContents());

        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
