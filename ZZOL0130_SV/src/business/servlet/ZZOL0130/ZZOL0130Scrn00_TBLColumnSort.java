/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0130.ZZOL0130CMsg;
import business.servlet.ZZOL0130.constant.ZZOL0130Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class ZZOL0130Scrn00_TBLColumnSort extends S21CommonHandler implements ZZOL0130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZOL0130BMsg scrnMsg = (ZZOL0130BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        ZZOL0130BMsg scrnMsg = (ZZOL0130BMsg) bMsg;

        // Parameters for Table Column Sort.
        Parameters param  = ((HttpDispatchContext)ctx.getDispatchContext()).getParameters();
        String sortTblNm   = param.getSingleValue( S21TableColumnSortConstant.SORT_TABLE_NAME );
        String sortItemNm  = param.getSingleValue( S21TableColumnSortConstant.SORT_ITEM_NAME );
        String sortOrderBy = param.getSingleValue( S21TableColumnSortConstant.ORDER_BY );

        // Table:A
        if( "A".equals( sortTblNm ) ) {
            if( scrnMsg.A.getValidCount() > 0 ) {
                
                scrnMsg.xxSortTblNm.setValue( sortTblNm );
                scrnMsg.xxSortItemNm.setValue( sortItemNm );
                scrnMsg.xxSortOrdByTxt.setValue( sortOrderBy );
                
                ZZOL0130CMsg bizMsg = new ZZOL0130CMsg();
                bizMsg.setBusinessID("ZZOL0130");
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }
        
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0130BMsg scrnMsg = (ZZOL0130BMsg) bMsg;
        ZZOL0130CMsg bizMsg = (ZZOL0130CMsg) cMsg;        
        
        if( bizMsg != null ) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.controlIMG( ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents() );
        


    }

}
