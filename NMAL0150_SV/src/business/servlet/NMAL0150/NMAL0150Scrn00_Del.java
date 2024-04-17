package business.servlet.NMAL0150;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0150.NMAL0150CMsg;
import business.servlet.NMAL0150.common.NMAL0150CommonLogic;
import business.servlet.NMAL0150.constant.NMAL0150Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 2022/05/30   Hitachi         A.Kohinata      Update          QC#55970
  *</pre>
 */
public class NMAL0150Scrn00_Del extends S21CommonHandler implements NMAL0150Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // del start 2022/05/30 QC#55970
    	//NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;
        //List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        //if (selectedRows.isEmpty()) {
        //    //NMAM8054E=0,Please select item(s).
        //    scrnMsg.setMessageInfo("NMAM8054E");
        //    throw new EZDFieldErrorException();
        //}
        // del end 2022/05/30 QC#55970
        
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // mod start 2022/05/30 QC#55970
        //return null;
        NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;
        NMAL0150CMsg bizMsg = new NMAL0150CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // mod end 2022/05/30 QC#55970
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;
        // mod start 2022/05/30 QC#55970
        //List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        //ZYPTableUtil.deleteRows(scrnMsg.A, selectedRows);
        //NMAL0150CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
        NMAL0150CMsg bizMsg = (NMAL0150CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL0150CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.A.no(0).mdseCd_A1);
        // mod end 2022/05/30 QC#55970
    }

}
