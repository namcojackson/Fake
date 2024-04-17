/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1630;

import static business.servlet.NWAL1630.constant.NWAL1630Constant.BIZ_ID;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1630.NWAL1630CMsg;
import business.servlet.NWAL1630.common.NWAL1630CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1630Scrn00_CMN_Close
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         C.Yokoi         Create          N/A
 * 2016/08/30   Fujitsu         S.Takami        Update          S21_NA#9806
 * 2017/09/22   Fujitsu         T.Ogura         Update          S21_NA#18859(Sol#154)
 * 2019/01/22   Fujitsu         K.Ishizuka      Update          S21_NA#29446
 *</pre>
 */
public class NWAL1630Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1630BMsg scrnMsg = (NWAL1630BMsg) bMsg;
        NWAL1630CommonLogic.addCheckItem(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1630BMsg scrnMsg = (NWAL1630BMsg) bMsg;

        // 2017/09/22 QC#18859 Mod Start
//        if (!ZYPCommonFunc.hasValue(scrnMsg.dlrRefNum) && !ZYPCommonFunc.hasValue(scrnMsg.csmpContrNum)) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.serNum) && !ZYPCommonFunc.hasValue(scrnMsg.dsContrNum)) {
        // 2017/09/22 QC#18859 Mod End
            return null;
        }
        NWAL1630CMsg bizMsg = new NWAL1630CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1630BMsg scrnMsg = (NWAL1630BMsg) bMsg;

        // 2017/09/22 QC#18859 Mod Start
//        if (ZYPCommonFunc.hasValue(scrnMsg.dlrRefNum) || ZYPCommonFunc.hasValue(scrnMsg.csmpContrNum)) {
        if (ZYPCommonFunc.hasValue(scrnMsg.serNum) || ZYPCommonFunc.hasValue(scrnMsg.dsContrNum)) {
        // 2017/09/22 QC#18859 Mod End
            NWAL1630CMsg bizMsg = (NWAL1630CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            // 2017/09/22 QC#18859 Mod Start
//            if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
//                throw new EZDFieldErrorException();
//            }
            scrnMsg.addCheckItem(scrnMsg.serNum);
            scrnMsg.addCheckItem(scrnMsg.dsContrNum);
            scrnMsg.putErrorScreen();
            // 2017/09/22 QC#18859 Mod End
        }

        Object[] arg = (Object[]) getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            // Set Parameter
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[0], scrnMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[6], scrnMsg.dsOrdPosnNum);
            ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[7], scrnMsg.dsCpoLineNum);
            ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[8], scrnMsg.dsCpoLineSubNum);
//            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[9], scrnMsg.csmpContrNum); 2016/08/30 S21_NA#9806 Del
//            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[10], scrnMsg.dlrRefNum); 2016/08/30 S21_NA#9806 Del
//            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[11], scrnMsg.csmpPrcListCd); 2016/08/30 S21_NA#9806 Del
            // 2017/09/22 QC#18859 Add Start
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[14], scrnMsg.dsContrNum);
            // 2017/09/22 QC#18859 Add End
            // ZYPEZDItemValueSetter.setValue((EZDBDateItem) params[19], scrnMsg.rntlTrmnDt); // 2019/01/22 S21_NA#29446 Del
            // 2017/09/22 QC#18859 Add Start
            if (!ZYPCommonFunc.hasValue(scrnMsg.serNum) && !ZYPCommonFunc.hasValue(scrnMsg.dsContrNum)) {
                scrnMsg.svcMachMstrPk.clear();
            }
            ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[16], scrnMsg.svcMachMstrPk); // 21→16 S21_NA#29446
            // 2017/09/22 QC#18859 Add End
        }
    }
}
