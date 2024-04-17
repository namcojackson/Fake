package business.servlet.NMAL0110;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0110.common.NMAL0110CommonLogic;
import business.servlet.NMAL0110.constant.NMAL0110Constant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import business.servlet.ZYPL0310.constant.ZYPL0310Constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL0110Scrn00_Attachments extends S21CommonHandler implements NMAL0110Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		return null;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
		scrnMsg.xxRsltFlg_H1.clear(); // Manufacture Item Duplication Warning Flag
		scrnMsg.xxRsltFlg_H2.clear(); // Mercury Code and Parts Code does not exists Warning Flag
		scrnMsg.xxRsltFlg_H3.clear(); // "Save as Tmpl" button Disabled Flag

		ZYPTableUtil.clear(scrnMsg.P);
        boolean authHeaderEdit = getUserProfileService().isFunctionGranted(getUserProfileService().getContextUserInfo().getUserId(), NMAL0110Constant.FUNC_EDIT_HEADER);
        if (authHeaderEdit) {
			scrnMsg.P.no(0).xxPopPrm.setValue(ZYPL0310Constant.DISPLAY_MODE_MODIFICATION);  //[0]:display mode
        } else {
			scrnMsg.P.no(0).xxPopPrm.setValue(ZYPL0310Constant.DISPLAY_MODE_READ_ONLY);  //[0]:display mode
        }
		scrnMsg.P.no(1).xxPopPrm.setValue(BUSINESS_ID);  //[1]:business application id
		scrnMsg.P.no(2).xxPopPrm.setValue(scrnMsg.mdseCd_H1.getValue());  //[2]:attachments grouping text
		scrnMsg.P.no(3).xxPopPrm.setValue("Item Master Attachments");  //[3]:function name
		scrnMsg.P.no(4).xxPopPrm.setValue("Item Number");  //[4]:primary key
		scrnMsg.P.no(5).xxPopPrm.setValue("MDSE_ATT_DOC_TP");  //[5]:document type list //Code Table Name
		scrnMsg.P.no(6).xxPopPrm.setValue("NMAL0110_PARAM_ATT_LIMIT_NUM");  //[6]:attachments limit (This is key of  //NUM_CONST's key
		//scrnMsg.P.no(7).xxPopPrm.setValue("NMAL0110_PARAM_AUTHE_FILE_EXT");  //[7]:authorize file extentions (This is key of //VAR_CHAR_CONST's key
		scrnMsg.P.no(7).xxPopPrm.setValue("");  //Default
		scrnMsg.P.no(8).xxPopPrm.setValue("NMAL0110_PARAM_ATT_DATA_VOL");  //[8]:authorize file volume(size) (This is key of //NUM_CONST's key
		scrnMsg.P.no(29).xxPopPrm.setValue("NMAL0110Scrn00_Attachments");
		Object[] params = NMAL0110CommonLogic.toArray_popupForZYPL0310(scrnMsg.P, 11);
		setArgForSubScreen(params);

	}

}
