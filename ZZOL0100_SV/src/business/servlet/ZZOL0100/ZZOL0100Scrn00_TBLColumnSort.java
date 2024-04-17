/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/28/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.ZZOL0100;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0100.ZZOL0100CMsg;
import business.servlet.ZZOL0100.constant.ZZOL0100Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class ZZOL0100Scrn00_TBLColumnSort extends S21CommonHandler implements ZZOL0100Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
       ZZOL0100BMsg scrnMsg = (ZZOL0100BMsg) bMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);
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
                
                ZZOL0100CMsg bizMsg = new ZZOL0100CMsg();
                bizMsg.setBusinessID("ZZOL0100");
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0100BMsg scrnMsg = (ZZOL0100BMsg) bMsg;
        ZZOL0100CMsg bizMsg  = (ZZOL0100CMsg) cMsg;

        if( bizMsg != null ) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // Color Settings
        S21TableColorController tblColor = new S21TableColorController(SCREEN_NAME, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.controlIMG( ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents() );


	}

}
