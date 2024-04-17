/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZLL0010;


import parts.common.*;
import parts.servletcommon.*;
import business.blap.ZZLL0010.ZZLL0010CMsg;

import business.servlet.ZZLL0010.common.ZZLL0010CommonLogic;
import business.servlet.ZZLL0010.constant.ZZLL0010Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class ZZLL0010Scrn00_TBLColumnSort extends S21CommonHandler implements ZZLL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZLL0010BMsg scrnMsg = (ZZLL0010BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);
        
        if(sortItemNm.equals("xxFromDt_TR")) {
            sortItemNm = "ezInTime_TR";
        }
        
		ZZLL0010BMsg scrnMsg = (ZZLL0010BMsg) bMsg;

        // Table:A
        if ("A".equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {

                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                ZZLL0010CMsg bizMsg = new ZZLL0010CMsg();
                bizMsg.setBusinessID("ZZLL0010");
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
                return bizMsg;
            }
        }
        return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZLL0010BMsg scrnMsg = (ZZLL0010BMsg) bMsg;
        ZZLL0010CMsg bizMsg = (ZZLL0010CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        
        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG(ZZLL0010Constant.pageID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        
        // Table Color Controll
        S21TableColorController tblColor = new S21TableColorController(ZZLL0010Constant.pageID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        
        ZZLL0010CommonLogic.convertTimeToDisplay(scrnMsg, bizMsg);
        scrnMsg.A.setInputProtected(true);

	}

}
