package business.servlet.NWAL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2040.NWAL2040CMsg;
import business.servlet.NWAL2040.NWAL2040BMsg;
import business.servlet.NWAL2040.common.NWAL2040CommonLogic;
import business.servlet.NWAL2040.constant.NWAL2040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 2017/09/12   Fujitsu         T.Ogura         Update          QC#19805
 *</pre>
 */
public class NWAL2040Scrn00_CMN_Submit extends S21CommonHandler implements NWAL2040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
   		for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
   			//Mandatory Check
   	        scrnMsg.addCheckItem(scrnMsg.A.no(i).defWhMapTmplCd_A1);
   	        scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseItemClsTpCd_A1);
   	        scrnMsg.addCheckItem(scrnMsg.A.no(i).fromPostCd_A1);
   	        scrnMsg.addCheckItem(scrnMsg.A.no(i).toPostCd_A1);
   		}
        scrnMsg.putErrorScreen();
        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(ERROR_CODE_E)) {
            throw new EZDFieldErrorException();
        }
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
		NWAL2040CMsg bizMsg = new NWAL2040CMsg();
		bizMsg.setBusinessID(BUSINESS_ID);
		bizMsg.setFunctionCode(FUNC_CD_SBMT);
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
 		return bizMsg;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
		NWAL2040CMsg bizMsg  = (NWAL2040CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		NWAL2040CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);

		if (ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab_H1) && "SrcCatg".equals(scrnMsg.xxDplyTab_H1.getValue())) {
			if (scrnMsg.B.getValidCount() > 0) {
				for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
					scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_B1);
					scrnMsg.addCheckItem(scrnMsg.B.no(i).dsOrdCatgCd_B1);
					scrnMsg.addCheckItem(scrnMsg.B.no(i).dsOrdTpCd_B1);
					scrnMsg.addCheckItem(scrnMsg.B.no(i).firstBizCtxAttrbTxt_B1);
					scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B1);
					scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_B1);
				}
			}
		// 2017/09/12 QC#19805 Mod Start
//		} else {
        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab_H1) && TAB_MAP_TMPL_QLFY.equals(scrnMsg.xxDplyTab_H1.getValue())) {
		// 2017/09/12 QC#19805 Mod End
			if (scrnMsg.A.getValidCount() > 0) {
				for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
					scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
					scrnMsg.addCheckItem(scrnMsg.A.no(i).defWhMapTmplCd_A1);
					scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseItemClsTpCd_A1);
					scrnMsg.addCheckItem(scrnMsg.A.no(i).fromPostCd_A1);
					scrnMsg.addCheckItem(scrnMsg.A.no(i).toPostCd_A1);
					scrnMsg.addCheckItem(scrnMsg.A.no(i).onHndChkFlg_A1);
					scrnMsg.addCheckItem(scrnMsg.A.no(i).otbdPrimOnHndChkWhCd_A1);
					scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlSwhNm_01);
					scrnMsg.addCheckItem(scrnMsg.A.no(i).otbdPrimOnHndLinSrcCd_A1);
					scrnMsg.addCheckItem(scrnMsg.A.no(i).otbdScdOnHndChkWhCd_A1);
					scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlSwhNm_02);
					scrnMsg.addCheckItem(scrnMsg.A.no(i).otbdScdOnHndLineSrcCd_A1);
					scrnMsg.addCheckItem(scrnMsg.A.no(i).otbdDefWhCd_A1);
					scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlSwhNm_03);
					scrnMsg.addCheckItem(scrnMsg.A.no(i).otbdDefLineSrcCd_A1);
					// 2017/09/12 QC#19805 Del Start
//					scrnMsg.addCheckItem(scrnMsg.A.no(i).rmaDefWhCd_A1);
//					scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlSwhNm_04);
//					scrnMsg.addCheckItem(scrnMsg.A.no(i).rmaDefLineSrcCd_A1);
					// 2017/09/12 QC#19805 Del End
					scrnMsg.addCheckItem(scrnMsg.A.no(i).thirdPtyItemFlg_A1);
 				}
			}
		// 2017/09/12 QC#19805 Add Start
        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab_H1) && TAB_MAP_TMPL_QLFY_RMA.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            if (scrnMsg.E.getValidCount() > 0) {
                for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
                    scrnMsg.addCheckItem(scrnMsg.E.no(i).xxChkBox_E1);
                    scrnMsg.addCheckItem(scrnMsg.E.no(i).defWhMapTmplCd_E1);
                    scrnMsg.addCheckItem(scrnMsg.E.no(i).mdseItemClsTpCd_E1);
                    scrnMsg.addCheckItem(scrnMsg.E.no(i).thirdPtyItemFlg_E1);
                    scrnMsg.addCheckItem(scrnMsg.E.no(i).fromPostCd_E1);
                    scrnMsg.addCheckItem(scrnMsg.E.no(i).toPostCd_E1);
                    scrnMsg.addCheckItem(scrnMsg.E.no(i).onHndChkFlg_E1);
                    scrnMsg.addCheckItem(scrnMsg.E.no(i).thirdPtyDspTpCd_E1);
                    scrnMsg.addCheckItem(scrnMsg.E.no(i).dropRtrnVndCd_E1);
                    scrnMsg.addCheckItem(scrnMsg.E.no(i).rmaDefWhCd_E1);
                }
            }
        // 2017/09/12 QC#19805 Add End
		}
		scrnMsg.putErrorScreen();
		
	}

}
