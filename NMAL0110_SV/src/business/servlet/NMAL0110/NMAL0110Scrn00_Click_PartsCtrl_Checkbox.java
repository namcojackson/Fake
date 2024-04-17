package business.servlet.NMAL0110;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0110.NMAL0110BMsg;
import business.servlet.NMAL0110.constant.NMAL0110Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/12/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0110Scrn00_Click_PartsCtrl_Checkbox extends S21CommonHandler implements NMAL0110Constant {

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
		
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_RR) || FLG_OFF_N.equals(scrnMsg.xxChkBox_RR.getValue())) {
        	//QC#4203
            //scrnMsg.rtrnCtrlTpCd_H1.setInputProtected(true);
            //scrnMsg.rtrnDsplTpCd_H1.setInputProtected(true);
            //scrnMsg.rtrnToVndCd_H1.setInputProtected(true);
            //scrnMsg.rtrnCtrlTpCd_H1.clear();
            //scrnMsg.rtrnDsplTpCd_H1.clear();
            //scrnMsg.rtrnToVndCd_H1.clear();
            
            scrnMsg.xxLinkProt_30.setInputProtected(true);
            scrnMsg.rtrnCtrlTpCd_H1.setInputProtected(true);//hidden
            scrnMsg.rtrnCtrlTpNm_H1.setInputProtected(true);
            scrnMsg.rtrnDsplTpCd_H1.setInputProtected(true);
            scrnMsg.rtrnToVndCd_H1.setInputProtected(true);//hidden
            scrnMsg.locNm_V1.setInputProtected(true);
            scrnMsg.rtrnToPrntVndCd_H1.setInputProtected(true);//hidden
            scrnMsg.locNm_P1.setInputProtected(true);
            scrnMsg.rtrnWhCd_H1.setInputProtected(true);//hidden
            scrnMsg.locNm_W1.setInputProtected(true);
            
            scrnMsg.rtrnCtrlTpCd_H1.clear();//hidden
            scrnMsg.rtrnCtrlTpNm_H1.clear();
            scrnMsg.rtrnDsplTpCd_H1.clear();
            scrnMsg.rtrnToVndCd_H1.clear();//hidden
            scrnMsg.locNm_V1.clear();
            scrnMsg.rtrnToPrntVndCd_H1.clear();//hidden
            scrnMsg.locNm_P1.clear();
            scrnMsg.rtrnWhCd_H1.clear();//hidden
            scrnMsg.locNm_W1.clear();
            
        } else {
        	//QC#4203
            //scrnMsg.rtrnCtrlTpCd_H1.setInputProtected(false);
            //scrnMsg.rtrnDsplTpCd_H1.setInputProtected(false);
            //scrnMsg.rtrnToVndCd_H1.setInputProtected(false);
            
            scrnMsg.xxLinkProt_30.setInputProtected(false);
            scrnMsg.rtrnCtrlTpCd_H1.setInputProtected(true);//hidden
            scrnMsg.rtrnCtrlTpNm_H1.setInputProtected(true);
            scrnMsg.rtrnDsplTpCd_H1.setInputProtected(false);
            scrnMsg.rtrnToVndCd_H1.setInputProtected(true);//hidden
            scrnMsg.locNm_V1.setInputProtected(true);
            scrnMsg.rtrnToPrntVndCd_H1.setInputProtected(true);//hidden
            scrnMsg.locNm_P1.setInputProtected(true);
            scrnMsg.rtrnWhCd_H1.setInputProtected(true);//hidden
            scrnMsg.locNm_W1.setInputProtected(true);
            
        }
        
    }

}
