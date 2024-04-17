package business.servlet.NWAL2040;

import parts.common.*;
import parts.servletcommon.*;
import business.blap.NWAL2040.NWAL2040CMsg;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import business.servlet.NWAL2040.NWAL2040BMsg;
import business.servlet.NWAL2040.common.NWAL2040CommonLogic;
import business.servlet.NWAL2040.constant.NWAL2040Constant;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 2017/09/12   Fujitsu         T.Ogura         Update          QC#19805
 *</pre>
 */
public class NWAL2040Scrn00_TBLColumnSort extends S21CommonHandler implements NWAL2040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
		//Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        // 2017/09/12 QC#19805 Add Start
        if (TAB_MAP_TMPL_QLFY.equals(scrnMsg.xxDplyTab_H1.getValue())) {
        // 2017/09/12 QC#19805 Add End
            //Table:A
            if ("A".equals(sortTblNm)) {
                if (scrnMsg.A.getValidCount() > 0) {

                    // Set sort information
                    scrnMsg.xxSortTblNm_01.setValue(sortTblNm);
                    scrnMsg.xxSortItemNm_01.setValue(sortItemNm);
                    scrnMsg.xxSortOrdByTxt_01.setValue(sortOrderBy);

                    NWAL2040CMsg bizMsg = new NWAL2040CMsg();
                    bizMsg.setBusinessID(BUSINESS_ID);
                    bizMsg.setFunctionCode(FUNC_CD_SRCH);
                    EZDMsg.copy(scrnMsg, null, bizMsg, null);
                    return bizMsg;
                    
                }
            }

        // 2017/09/12 QC#19805 Add Start
        } else if (TAB_MAP_TMPL_QLFY_RMA.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            //Table:E
            if ("E".equals(sortTblNm)) {
                if (scrnMsg.E.getValidCount() > 0) {

                    // Set sort information
                    scrnMsg.xxSortTblNm_01.setValue(sortTblNm);
                    scrnMsg.xxSortItemNm_01.setValue(sortItemNm);
                    scrnMsg.xxSortOrdByTxt_01.setValue(sortOrderBy);

                    NWAL2040CMsg bizMsg = new NWAL2040CMsg();
                    bizMsg.setBusinessID(BUSINESS_ID);
                    bizMsg.setFunctionCode(FUNC_CD_SRCH);
                    EZDMsg.copy(scrnMsg, null, bizMsg, null);
                    return bizMsg;
                    
                }
            }
        }
        // 2017/09/12 QC#19805 Add End

		return null;
		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
		NWAL2040CMsg bizMsg  = (NWAL2040CMsg) cMsg;

		if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
		
            //control image file of sort column (Gif file. ASC or DESC.)
            // 2017/09/12 QC#19805 Add Start
            if (TAB_MAP_TMPL_QLFY.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            // 2017/09/12 QC#19805 Add End
                S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
            // 2017/09/12 QC#19805 Add Start
            } else if (TAB_MAP_TMPL_QLFY_RMA.equals(scrnMsg.xxDplyTab_H1.getValue())) {
                S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.E.no(0).getBaseContents());
            }
            // 2017/09/12 QC#19805 Add End
            
    		NWAL2040CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
//    		scrnMsg.setFocusItem(scrnMsg.mdseCstUpdTpCd_H1);
		}
		
	}

}
