/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/19   Fujitsu         R.Mori          Create          N/A
 * 2013/10/30   Fujitsu         K.Shibuya       Update          2852
 *</pre>
 */
package business.servlet.NLBL0100;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL0100.NLBL0100CMsg;
import business.servlet.NLBL0100.common.NLBL0100CommonLogic;
import business.servlet.NLBL0100.constant.NLBL0100Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class NLBL0100_INIT extends S21INITCommonHandler implements NLBL0100Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL0100BMsg scrnMsg = (NLBL0100BMsg) bMsg;
        
        scrnMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        
        Serializable arg = getArgForSubScreen();

        // get parameters from NLBL0090
        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            EZDBStringItem param0 = (EZDBStringItem) params[0];        // CARR_CD
            EZDBStringItem param1 = (EZDBStringItem) params[1];        // LOC_NM
            EZDBStringItem param2 = (EZDBStringItem) params[2];        // BOL_NUM
            EZDBStringItem param3 = (EZDBStringItem) params[3];        // PRO_NUM
            
            scrnMsg.carrCd.setValue(param0.getValue());
            scrnMsg.carrNm.setValue(param1.getValue());
            scrnMsg.bolNum.setValue(param2.getValue());
            scrnMsg.proNum.setValue(param3.getValue());
        }

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
        
        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG("NLBL0100Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());

        NLBL0100CMsg bizMsg = new NLBL0100CMsg();
        bizMsg.setBusinessID("NLBL0100");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL0100BMsg scrnMsg = (NLBL0100BMsg) bMsg;
        NLBL0100CMsg bizMsg = (NLBL0100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Input Protect
        NLBL0100CommonLogic.setInputProtected(scrnMsg); // 10/14/2015 add

        // format date
        if (ZYPCommonFunc.hasValue(scrnMsg.ezInTime.getValue()) && scrnMsg.ezInTime.getValue().length() >= 8) {
            // Mod #2852 2013/10/30 K.Shibuya Start
            // String formattedDt = ZYPDateUtil.DateFormatter(scrnMsg.ezInTime.getValue().substring(0, 8), "yyyyMMdd", "MM/dd/yyyy");
            String formattedDt = ZYPDateUtil.formatEzd8ToDisp(scrnMsg.ezInTime.getValue().substring(0, 8));
            // Mod #2852 2013/10/30 K.Shibuya End
            scrnMsg.ezInTime.setValue(formattedDt);
        }

        this.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        this.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        
        // set backgroung color in table
        NLBL0100CommonLogic.setTblBackColor(scrnMsg);
        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG("NLBL0100Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

    }
    

}
