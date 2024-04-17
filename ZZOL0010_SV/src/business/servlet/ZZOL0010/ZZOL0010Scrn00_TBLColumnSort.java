/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0010;

import parts.common.*;
import parts.servletcommon.*;
// import business.blap.ZZOL0010.ZZOL0010CMsg;

import business.blap.ZZOL0010.ZZOL0010CMsg;
import business.servlet.ZZOL0010.common.ZZOL0010CommonLogic;
import business.servlet.ZZOL0010.constant.ZZOL0010Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21TableColumnSortConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

public class ZZOL0010Scrn00_TBLColumnSort extends S21CommonHandler implements ZZOL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Parameters for Table Column Sort.
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String sortTblNm = param.getSingleValue(S21TableColumnSortConstant.SORT_TABLE_NAME);
        String sortItemNm = param.getSingleValue(S21TableColumnSortConstant.SORT_ITEM_NAME);
        String sortOrderBy = param.getSingleValue(S21TableColumnSortConstant.ORDER_BY);

        // sortItemNm in BMsg and CMsgs are different
        if(sortItemNm.equals("xxEzOnlStopFlagDisp_A1")) {
            sortItemNm = "ezOnlStopFlag_A1";
            
        } else if(sortItemNm.equals("xxEzAcctInfoModeDisp_A1")) {
            sortItemNm = "ezAcctInfoMode_A1";
            
        } else if(sortItemNm.equals("xxEzDebugLevelDisp_A1")) {
            sortItemNm = "ezDebugLevel_A1";
            
        }
        
        ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;

        // Table:A
        if ("A".equals(sortTblNm)) {
            if (scrnMsg.A.getValidCount() > 0) {

                scrnMsg.xxSortTblNm.setValue(sortTblNm);
                scrnMsg.xxSortItemNm.setValue(sortItemNm);
                scrnMsg.xxSortOrdByTxt.setValue(sortOrderBy);

                ZZOL0010CMsg bizMsg = new ZZOL0010CMsg();
                bizMsg.setBusinessID("ZZOL0010");
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
                return bizMsg;

            }
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;
        ZZOL0010CMsg bizMsg = (ZZOL0010CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        
        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG("ZZOL0010Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());
        S21SortColumnIMGController.controlIMG(ctx, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        
        // Table Color Controll
        S21TableColorController tblColor = new S21TableColorController(ZZOL0010Constant.pageID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        
        ZZOL0010CommonLogic.convertFlagDisplay(scrnMsg, bizMsg);
        ZZOL0010CommonLogic.convertTimeDisplay(scrnMsg);

    }

}
