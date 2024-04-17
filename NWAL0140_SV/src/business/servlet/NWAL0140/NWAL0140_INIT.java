/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/23/2009   CUSA            Fujitsu         Update          N/A
 * 04/23/2010   CUSA            K.Tajima        Update          4202
 *</pre>
 */
package business.servlet.NWAL0140;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL0140.NWAL0140CMsg;
import business.servlet.NWAL0140.common.NWAL0140CommonLogic;
import business.servlet.NWAL0140.constant.NWAL0140Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class NWAL0140_INIT extends S21INITCommonHandler implements NWAL0140Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing to do.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;
        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            
            // [0] : Line Number
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            scrnMsg.xxDtlLineNum.setValue(param0.getValue());
            
            // [1] : Ship To Code
            EZDBStringItem param1 = (EZDBStringItem) params[1];
            scrnMsg.shipToCustCd.setValue(param1.getValue());
            
            // [2] : Ship To Name
            EZDBStringItem param2 = (EZDBStringItem) params[2];
            scrnMsg.locNm.setValue(param2.getValue());
            
            // [3] : Ship To Additional Name
            EZDBStringItem param3 = (EZDBStringItem) params[3];
            scrnMsg.addlLocNm.setValue(param3.getValue());

            // [4] : Address Line 1
            EZDBStringItem param4 = (EZDBStringItem) params[4];
            scrnMsg.firstLineAddr.setValue(param4.getValue());
            
            // [5] : Address Line 2
            EZDBStringItem param5 = (EZDBStringItem) params[5];
            scrnMsg.scdLineAddr.setValue(param5.getValue());

            // [6] : Address Line 3
            EZDBStringItem param6 = (EZDBStringItem) params[6];
            scrnMsg.thirdLineAddr.setValue(param6.getValue());

            // [7] : Address Line 4
            EZDBStringItem param7 = (EZDBStringItem) params[7];
            scrnMsg.frthLineAddr.setValue(param7.getValue());
            
            // [8] : Ship To First Ref Cmnt Txt
            EZDBStringItem param8 = (EZDBStringItem) params[8];
            scrnMsg.firstRefCmntTxt.setValue(param8.getValue());

            // [9] : Ship To Scd Ref Cmnt Txt
            EZDBStringItem param9 = (EZDBStringItem) params[9];
            scrnMsg.scdRefCmntTxt.setValue(param9.getValue());

            // [10] : Ship To City
            EZDBStringItem param10 = (EZDBStringItem) params[10];
            scrnMsg.ctyAddr.setValue(param10.getValue());

            // [11] : Ship To State
            EZDBStringItem param11 = (EZDBStringItem) params[11];
            scrnMsg.stCd.setValue(param11.getValue());
            
            // [12] : Ship To Post Code
            EZDBStringItem param12 = (EZDBStringItem) params[12];
            scrnMsg.postCd.setValue(param12.getValue());
            
            // [13] : Ship To Country
            EZDBStringItem param13 = (EZDBStringItem) params[13];
            scrnMsg.ctryCd.setValue(param13.getValue());
            
            // [14] : Ship To County Name
            EZDBStringItem param14 = (EZDBStringItem) params[14];
            scrnMsg.cntyNm.setValue(param14.getValue());
            
            // [15] : Drop Ship Flag
            EZDBStringItem param15 = (EZDBStringItem) params[15];
            scrnMsg.dropShipFlg.setValue(param15.getValue());
            
            // [16] : Read Only Flg
            EZDBStringItem param16 = (EZDBStringItem) params[16];
            scrnMsg.xxReadOnlyFlg.setValue(param16.getValue());
            
            // [17] : Bill To Code
            EZDBStringItem param17 = (EZDBStringItem) params[17];
            scrnMsg.billToCustCd_J1.setValue(param17.getValue());
            scrnMsg.billToCustCd_BK.setValue(param17.getValue());
            
            // [18] : Bill To Name
            // nothing to do.
            
            // [19] : Sell To Code
            EZDBStringItem param19 = (EZDBStringItem) params[19];
            scrnMsg.sellToCustCd_J1.setValue(param19.getValue());
            scrnMsg.sellToCustCd_BK.setValue(param19.getValue());
            
            // [20] : Sell To Name
            // nothing to do.

// ********** [Def# 4202] K.Tajima - START
            // [21] : Province
            if( params.length > 21 ) {
                EZDBStringItem param21 = (EZDBStringItem) params[21];
                scrnMsg.provNm.setValue(param21.getValue());
            }
// ********** [Def# 4202] K.Tajima - E N D
        }

        NWAL0140CMsg bizMsg = new NWAL0140CMsg();
        bizMsg.setBusinessID("NWAL0140");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;

        if (FLG_OFF_N.equals(scrnMsg.xxReadOnlyFlg.getValue())) {
            NWAL0140CommonLogic.initCommonButton(this);
        } else {
            NWAL0140CommonLogic.initCommonButtonReadOnly(this);
        }

        EZDBMsg.copy(cMsg, null, scrnMsg, null);

        NWAL0140CommonLogic.protectedInput(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) arg0;

        scrnMsg.shipToCustCd.setNameForMessage("Ship To");
        scrnMsg.locNm.setNameForMessage("Loc Nm");
        scrnMsg.addlLocNm.setNameForMessage("Add Loc Nm");        
        scrnMsg.firstRefCmntTxt.setNameForMessage("Ship To Txt1");
        scrnMsg.scdRefCmntTxt.setNameForMessage("Ship To Txt2");        
        scrnMsg.firstLineAddr.setNameForMessage("Addr Ln1");
        scrnMsg.scdLineAddr.setNameForMessage("Addr Ln2");
        scrnMsg.thirdLineAddr.setNameForMessage("Addr Ln3");
        scrnMsg.frthLineAddr.setNameForMessage("Addr Ln4");
        scrnMsg.postCd.setNameForMessage("Post Code");
        scrnMsg.ctyAddr.setNameForMessage("City");
        scrnMsg.cntyNm.setNameForMessage("County");
        scrnMsg.stCd.setNameForMessage("State");
        scrnMsg.provNm.setNameForMessage("Province");
        scrnMsg.ctryCd.setNameForMessage("Country");
    }
}
