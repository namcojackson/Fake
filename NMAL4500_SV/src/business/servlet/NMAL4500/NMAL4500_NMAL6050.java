/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2009   SRA             T.Chijimatsu    Create          N/A
 * 2013/09/19   Fujitsu         N.Sugiura       Update          MEX-LC004
 * 2013/10/21   Fujitsu         D.Yanagisawa    Update          MEX-LC001
 *</pre>
 */
package business.servlet.NMAL4500;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL4500.constant.NMAL4500Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL4500_NMAL6050 extends S21CommonHandler implements NMAL4500Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
		
		//return NMAL6050 set data and foecus
		if (scrnMsg.xxScrEventNm_01.getValue().equals(POPUP_ACTION_VENDOR)) {
            // Back no param
            if ("CMN_Close".equals(getLastGuard())) {
                scrnMsg.setFocusItem(scrnMsg.vndCd_01);
                // Exists param
            } else {
            	scrnMsg.vndCd_01.setValue(scrnMsg.xxCondCd_Z1.getValue());
            	scrnMsg.locNm_01.setValue(scrnMsg.xxCondNm_Z1.getValue());
            	scrnMsg.vndCd_01.setInputProtected(false);
            	scrnMsg.locNm_01.setInputProtected(false);
            }

        } else if (scrnMsg.xxScrEventNm_01.getValue().equals(POPUP_ACTION_CITY)) {
            // Back no param
            if ("CMN_Close".equals(getLastGuard())) {
                scrnMsg.setFocusItem(scrnMsg.ctyAddr_01);
                // Exists param
            } else {
				// mod start D727
//            	scrnMsg.ctyAddr_01.setValue(scrnMsg.xxCondNm_Z1.getValue());
				if (ZYPCommonFunc.hasValue(scrnMsg.xxCondNm_Z1)) {
					String[] xxCondNm_Z1= scrnMsg.xxCondNm_Z1.getValue().split(",");
					scrnMsg.ctyAddr_01.setValue(xxCondNm_Z1[0]);
				}
				// mod end D727
            	
                // QC No.889 <2009/10/21 Y.Chen>
//              scrnMsg.ctyAddr_01.setInputProtected(true);
                /** CHG-START 2009/12/08 takamura **/
//                scrnMsg.setFocusItem(scrnMsg.ctyAddr_01);
                scrnMsg.postCd_01.setValue(scrnMsg.xxCondCd_Z1.getValue());
                scrnMsg.setFocusItem(scrnMsg.cntyPk_03);
                /** CHG-END **/
            }

        } else if (scrnMsg.xxScrEventNm_01.getValue().equals(POPUP_ACTION_STATE)) {
            // Back no param
            if ("CMN_Close".equals(getLastGuard())) {
                scrnMsg.setFocusItem(scrnMsg.stCd_01);
                // Exists param
            } else {
            	scrnMsg.stCd_01.setValue(scrnMsg.xxCondCd_Z1.getValue());
                // QC No.890 <2009/10/21 Y.Chen>
//               scrnMsg.stCd_01.setInputProtected(true);
                /** CHG-START 2009/12/08 takamura **/
//                scrnMsg.setFocusItem(scrnMsg.stCd_01);
                scrnMsg.setFocusItem(scrnMsg.provNm_01);
                /** CHG-END **/
            }

        } else if (scrnMsg.xxScrEventNm_01.getValue().equals(POPUP_ACTION_AFFILIATION)) {
            // Back no param
            if ("CMN_Close".equals(getLastGuard())) {
                scrnMsg.setFocusItem(scrnMsg.coaAfflCd_01);
                // Exists param
            } else {
            	scrnMsg.coaAfflCd_01.setValue(scrnMsg.xxCondCd_Z1.getValue());
                // QC No.886 <2009/10/21 Y.Chen>
//              scrnMsg.coaAfflCd_01.setInputProtected(true);
                /** CHG-START 2009/12/08 takamura **/
//                scrnMsg.setFocusItem(scrnMsg.coaAfflCd_01);
                scrnMsg.setFocusItem(scrnMsg.intlVndFlg_01);
                /** CHG-END **/
            }
        // ADD START 2013/09/19 MEX-LC004
        } else if (scrnMsg.xxScrEventNm_01.getValue().equals(POPUP_ACTION_CURRENCY_CODE)) {
            // Back no param
            if ("CMN_Close".equals(getLastGuard())) {
                scrnMsg.setFocusItem(scrnMsg.dealCcyCd_01);
                // Exists param
            } else {
                scrnMsg.dealCcyCd_01.setValue(scrnMsg.xxCondCd_Z1.getValue());
                scrnMsg.setFocusItem(scrnMsg.dealCcyCd_01);
            }
        // ADD END 2013/09/19 MEX-LC004

        // ADD START 2013/10/21 MEX-LC001
        } else if (scrnMsg.xxScrEventNm_01.getValue().equals(POPUP_ACTION_INVOICE_VENDOR)) {
            // Back no param
            if ("CMN_Close".equals(getLastGuard())) {
                scrnMsg.setFocusItem(scrnMsg.invVndCd_01);
                // Exists param
            } else {
                scrnMsg.invVndCd_01.setValue(scrnMsg.xxCondCd_Z1.getValue());
                scrnMsg.locNm_02.setValue(scrnMsg.xxCondNm_Z1.getValue());
                scrnMsg.invVndCd_01.setInputProtected(false);
                scrnMsg.locNm_02.setInputProtected(true);
            }
        // ADD E N D 2013/10/21 MEX-LC001

        }

	}

}
