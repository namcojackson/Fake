package business.servlet.NWAL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2040.NWAL2040BMsg;
import business.servlet.NWAL2040.constant.NWAL2040Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 2017/09/12   Fujitsu         T.Ogura         Update          QC#19805
 *</pre>
 */
public class NWAL2040_NWAL1130 extends S21CommonHandler implements NWAL2040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		return null;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
		String event = scrnMsg.P.no(29).xxPopPrm.getValue();
		if (!CMN_CLOSE.equals(getLastGuard())) {
			if ("NWAL2040Scrn00_LineItem_OTBD_DEF_WH".equals(event)) {
				int row = Integer.parseInt(scrnMsg.P.no(28).xxPopPrm.getValue());
				scrnMsg.A.no(row).otbdDefWhCd_A1.setValue(scrnMsg.O.no(0).xxComnScrColValTxt_O1.getValue());
				scrnMsg.setFocusItem(scrnMsg.A.no(row).otbdDefWhCd_A1);
			} else if ("NWAL2040Scrn00_LineItem_OTBD_DEF_SWH".equals(event)) {
				int row = Integer.parseInt(scrnMsg.P.no(28).xxPopPrm.getValue());
				scrnMsg.A.no(row).otbdDefSwhCd_A1.setValue(scrnMsg.O.no(0).xxComnScrColValTxt_O1.getValue());
				scrnMsg.A.no(row).rtlSwhNm_03.setValue(scrnMsg.O.no(1).xxComnScrColValTxt_O1.getValue());
				scrnMsg.setFocusItem(scrnMsg.A.no(row).rtlSwhNm_03);
			} else if ("NWAL2040Scrn00_LineItem_OTBD_PRIM_ON_HND_CHK_WH".equals(event)) {
				int row = Integer.parseInt(scrnMsg.P.no(28).xxPopPrm.getValue());
				scrnMsg.A.no(row).otbdPrimOnHndChkWhCd_A1.setValue(scrnMsg.O.no(0).xxComnScrColValTxt_O1.getValue());
				scrnMsg.setFocusItem(scrnMsg.A.no(row).otbdPrimOnHndChkWhCd_A1);
			} else if ("NWAL2040Scrn00_LineItem_OTBD_PRIM_ON_HND_CHK_SWH".equals(event)) {
				int row = Integer.parseInt(scrnMsg.P.no(28).xxPopPrm.getValue());
				scrnMsg.A.no(row).otbdPrimOnHndChkSwhCd_A1.setValue(scrnMsg.O.no(0).xxComnScrColValTxt_O1.getValue());
				scrnMsg.A.no(row).rtlSwhNm_01.setValue(scrnMsg.O.no(1).xxComnScrColValTxt_O1.getValue());
				scrnMsg.setFocusItem(scrnMsg.A.no(row).rtlSwhNm_01);
			} else if ("NWAL2040Scrn00_LineItem_OTBD_SCD_ON_HND_CHK_WH".equals(event)) {
				int row = Integer.parseInt(scrnMsg.P.no(28).xxPopPrm.getValue());
				scrnMsg.A.no(row).otbdScdOnHndChkWhCd_A1.setValue(scrnMsg.O.no(0).xxComnScrColValTxt_O1.getValue());
				scrnMsg.setFocusItem(scrnMsg.A.no(row).otbdScdOnHndChkWhCd_A1);
			} else if ("NWAL2040Scrn00_LineItem_OTBD_SCD_ON_HND_CHK_SWH".equals(event)) {
				int row = Integer.parseInt(scrnMsg.P.no(28).xxPopPrm.getValue());
				scrnMsg.A.no(row).otbdScdOnHndChkSwhCd_A1.setValue(scrnMsg.O.no(0).xxComnScrColValTxt_O1.getValue());
				scrnMsg.A.no(row).rtlSwhNm_02.setValue(scrnMsg.O.no(1).xxComnScrColValTxt_O1.getValue());
				scrnMsg.setFocusItem(scrnMsg.A.no(row).rtlSwhNm_02);
			} else if ("NWAL2040Scrn00_LineItem_RMA_DEF_WH".equals(event)) {
				int row = Integer.parseInt(scrnMsg.P.no(28).xxPopPrm.getValue());
				// 2017/09/12 QC#19805 Mod Start
//                scrnMsg.A.no(row).rmaDefWhCd_A1.setValue(scrnMsg.O.no(0).xxComnScrColValTxt_O1.getValue());
//                scrnMsg.setFocusItem(scrnMsg.A.no(row).rmaDefWhCd_A1);
                scrnMsg.E.no(row).rmaDefWhCd_E1.setValue(scrnMsg.O.no(0).xxComnScrColValTxt_O1.getValue());
                scrnMsg.setFocusItem(scrnMsg.E.no(row).rmaDefWhCd_E1);
				// 2017/09/12 QC#19805 Mod End
			// 2017/09/12 QC#19805 Del Start
//			} else if ("NWAL2040Scrn00_LineItem_RMA_DEF_SWH".equals(event)) {
//				int row = Integer.parseInt(scrnMsg.P.no(28).xxPopPrm.getValue());
//				scrnMsg.A.no(row).rmaDefSwhCd_A1.setValue(scrnMsg.O.no(0).xxComnScrColValTxt_O1.getValue());
//				scrnMsg.A.no(row).rtlSwhNm_04.setValue(scrnMsg.O.no(1).xxComnScrColValTxt_O1.getValue());
//				scrnMsg.setFocusItem(scrnMsg.A.no(row).rtlSwhNm_04);
			// 2017/09/12 QC#19805 Del End
			}

		}
		
	}

}
