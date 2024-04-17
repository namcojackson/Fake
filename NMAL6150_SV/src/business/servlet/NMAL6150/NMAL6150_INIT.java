/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6150;

import static business.servlet.NMAL6150.constant.NMAL6150Constant.BTN_CMN_CLEAR;
import static business.servlet.NMAL6150.constant.NMAL6150Constant.BTN_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6150.NMAL6150CMsg;
import business.servlet.NMAL6150.constant.NMAL6150Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   SRAA            Y.Chen          Create          QC#4504
 *</pre>
 */
public class NMAL6150_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6150BMsg scrnMsg = (NMAL6150BMsg) bMsg;
        Object[] params = (Object[]) getArgForSubScreen();

        ZYPEZDItemValueSetter.setValue(scrnMsg.firstLineAddr_O1, (EZDBStringItem)params[0]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.scdLineAddr_O1, (EZDBStringItem)params[1]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.thirdLineAddr_O1, (EZDBStringItem)params[2]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.frthLineAddr_O1, (EZDBStringItem)params[3]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddr_O1, (EZDBStringItem)params[4]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_O1, (EZDBStringItem)params[5]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.postCd_O1, (EZDBStringItem)params[6]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm_O1, (EZDBStringItem)params[7]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.provNm_O1, (EZDBStringItem)params[8]);
        
        ZYPEZDItemValueSetter.setValue(scrnMsg.firstLineAddr_S1, (EZDBStringItem)params[9]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.scdLineAddr_S1, (EZDBStringItem)params[10]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.thirdLineAddr_S1, (EZDBStringItem)params[11]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.frthLineAddr_S1, (EZDBStringItem)params[12]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddr_S1, (EZDBStringItem)params[13]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_S1, (EZDBStringItem)params[14]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.postCd_S1, (EZDBStringItem)params[15]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm_S1, (EZDBStringItem)params[16]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.provNm_S1, (EZDBStringItem)params[17]);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6150BMsg scrnMsg = (NMAL6150BMsg) bMsg;
        NMAL6150CMsg bizMsg = new NMAL6150CMsg();
        bizMsg.setBusinessID("NMAL6150");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6150BMsg scrnMsg = (NMAL6150BMsg) bMsg;
        NMAL6150CMsg bizMsg  = (NMAL6150CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
        
        compareAddress(scrnMsg, scrnMsg.firstLineAddr_O1, scrnMsg.firstLineAddr_S1);
        compareAddress(scrnMsg, scrnMsg.scdLineAddr_O1, scrnMsg.scdLineAddr_S1);
        compareAddress(scrnMsg, scrnMsg.thirdLineAddr_O1, scrnMsg.thirdLineAddr_S1);
        compareAddress(scrnMsg, scrnMsg.frthLineAddr_O1, scrnMsg.frthLineAddr_S1);
        compareAddress(scrnMsg, scrnMsg.ctyAddr_O1, scrnMsg.ctyAddr_S1);
        compareAddress(scrnMsg, scrnMsg.stCd_O1, scrnMsg.stCd_S1);
        compareAddress(scrnMsg, scrnMsg.postCd_O1, scrnMsg.postCd_S1);
        compareAddress(scrnMsg, scrnMsg.cntyNm_O1, scrnMsg.cntyNm_S1);
        compareAddress(scrnMsg, scrnMsg.provNm_O1, scrnMsg.provNm_S1);
        scrnMsg.putErrorScreen();
        
        scrnMsg.setFocusItem(scrnMsg.firstLineAddr_O1);
    }
    
    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL6150BMsg scrnMsg = (NMAL6150BMsg) bMsg;

        scrnMsg.firstLineAddr_O1.setNameForMessage("Address1");
        scrnMsg.scdLineAddr_O1.setNameForMessage("Address2");
        scrnMsg.thirdLineAddr_O1.setNameForMessage("Address3");
        scrnMsg.frthLineAddr_O1.setNameForMessage("Address4");
        scrnMsg.ctyAddr_O1.setNameForMessage("City");
        scrnMsg.stCd_O1.setNameForMessage("State");
        scrnMsg.postCd_O1.setNameForMessage("Postal Code");
        scrnMsg.cntyNm_O1.setNameForMessage("County");
        scrnMsg.provNm_O1.setNameForMessage("Provine");
        
        scrnMsg.firstLineAddr_S1.setNameForMessage("Address1");
        scrnMsg.scdLineAddr_S1.setNameForMessage("Address2");
        scrnMsg.thirdLineAddr_S1.setNameForMessage("Address3");
        scrnMsg.frthLineAddr_S1.setNameForMessage("Address4");
        scrnMsg.ctyAddr_S1.setNameForMessage("City");
        scrnMsg.stCd_S1.setNameForMessage("State");
        scrnMsg.postCd_S1.setNameForMessage("Postal Code");
        scrnMsg.cntyNm_S1.setNameForMessage("County");
        scrnMsg.provNm_S1.setNameForMessage("Provine");
    }
    
    private void compareAddress(NMAL6150BMsg scrnMsg, EZDBStringItem origItem, EZDBStringItem sugItem){
        if(!origItem.getValue().equals(sugItem.getValue())){
            sugItem.setErrorInfo(2, NMAL6150Constant.NMAM8508W);
            scrnMsg.addCheckItem(sugItem);
        }
    }
}
