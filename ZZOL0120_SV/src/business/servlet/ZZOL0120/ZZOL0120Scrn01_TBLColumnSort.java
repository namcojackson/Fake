/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/08/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.ZZOL0120;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0120.ZZOL0120CMsg;
import business.servlet.ZZOL0120.constant.ZZOL0120Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class ZZOL0120Scrn01_TBLColumnSort extends S21CommonHandler implements ZZOL0120Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
        ZZOL0120BMsg scrnMsg = (ZZOL0120BMsg) bMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);
        Parameters param  = ((HttpDispatchContext)ctx.getDispatchContext()).getParameters();
        String sortTblNm   = param.getSingleValue( S21TableColumnSortConstant.SORT_TABLE_NAME );
        String sortItemNm  = param.getSingleValue( S21TableColumnSortConstant.SORT_ITEM_NAME );
        String sortOrderBy = param.getSingleValue( S21TableColumnSortConstant.ORDER_BY );

        // Table:B
        if( "B".equals( sortTblNm ) ) {
            if( scrnMsg.B.getValidCount() > 0 ) {
                
                scrnMsg.xxSortTblNm_B1.setValue( sortTblNm );
                scrnMsg.xxSortItemNm_B1.setValue( sortItemNm );
                scrnMsg.xxSortOrdByTxt_B1.setValue( sortOrderBy );
                
                ZZOL0120CMsg bizMsg = new ZZOL0120CMsg();
                bizMsg.setBusinessID("ZZOL0120");
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0120BMsg scrnMsg = (ZZOL0120BMsg) bMsg;
        ZZOL0120CMsg bizMsg  = (ZZOL0120CMsg) cMsg;

        if( bizMsg != null ) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // Color Settings
        S21TableColorController tblColor = new S21TableColorController(SCREEN_NAME_01, scrnMsg);
        tblColor.setAlternateRowsBG("B", scrnMsg.B);

        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.controlIMG( ctx, scrnMsg, scrnMsg.B.no(0).getBaseContents() );


    }

}
