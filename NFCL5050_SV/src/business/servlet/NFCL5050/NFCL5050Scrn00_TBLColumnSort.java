/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/07/2010   SRA             D.Yoshida       Create          DefID:8124 No.378
 * 09/16/2010   SRA             D.Yoshida       Update          DefID:8124 No.386
 * 09/17/2010   SRA             D.Yoshida       Update          DefID:8124 No.388
 * 10/13/2010   Fujitsu         T.Tanaka        Update          Merge R2->R3
 *</pre>
 */
package business.servlet.NFCL5050;

import business.servlet.NFCL5050.constant.NFCL5050Constant;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL5050.NFCL5050CMsg;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;
import business.servlet.NFCL5050.common.NFCL5050CommonLogic;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import parts.common.EZDFieldErrorException;

public class NFCL5050Scrn00_TBLColumnSort extends S21CommonHandler implements NFCL5050Constant{

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        
        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;

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
                
                NFCL5050CMsg bizMsg = new NFCL5050CMsg();
                bizMsg.setBusinessID("NFCL5050");
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }
        
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;
        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;

        S21TableColorController tblColor = new S21TableColorController("NFCL5050Scrn00", scrnMsg);
        ZYPTableUtil.clear(scrnMsg.A);
        tblColor.clearRowsBG("A", scrnMsg.A);
//        tblColor.clearRowsBG("A1", scrnMsg.A);
//        tblColor.clearRowsBG("A2", scrnMsg.A);

        if (bizMsg != null) {
            if ("E".equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            NFCL5050CommonLogic.transMsgCheck(scrnMsg);
            scrnMsg.putErrorScreen();

            NFCL5050CommonLogic.initialize(this, scrnMsg);

            if (SUMMARY_STATUS_Y.equals(scrnMsg.xxRsltStsCd.getValue())) {
                NFCL5050CommonLogic.scrnItemControl_NFCL5050Scrn00_SearchInvoice(scrnMsg);
            }

            NFCL5050CommonLogic.setRowBg(scrnMsg);

            // scrnMsg.xxChkBox.setValue("0");
            scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A1);
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
//            tblColor.setAlternateRowsBG("A1", scrnMsg.A);
//            tblColor.setAlternateRowsBG("A2", scrnMsg.A);   
        }
        
        // Logic provided by S21 Architecture team.
        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.controlIMG( ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents() );
        
    }

}
