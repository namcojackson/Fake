/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NWAL1570.NWAL1570CMsg;
//import business.servlet.NWAL1570.constant.NWAL1570Constant;

import business.servlet.NWAL1570.constant.NWAL1570Constant.HDR_STS;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2017/06/12   Fujitsu         R.Nakamura      Update          QC#19031, 18186
 * 2018/07/05   Fujitsu         M.Ishii         Update          QC#25786
 *</pre>
 */
public class NWAL1570Scrn01_TransitionByHeaderStatus extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        //NWAL1570CMsg bizMsg = new NWAL1570CMsg();
        //bizMsg.setBusinessID("NWAL1570");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        NWAL1570_ABMsg recordMsg = scrnMsg.A.no(getButtonSelectNumber());

        String resultId = "";
        if (HDR_STS.DI_HLD.getHdrStsNm().equals(recordMsg.xxHdrDplyStsNm_A1.getValue())) {
            List<EZDBStringItem> param = createNWAL1550Parameters(recordMsg);
            setArgForSubScreen(param.toArray(new EZDBStringItem[0]));
            resultId = "GoToDICheck";
         // 2018/07/05 QC#25786 mod start
//        } else if (HDR_STS.SPLY_ABUSE.getHdrStsNm().equals(recordMsg.xxHdrDplyStsNm_A1.getValue())
        } else if (HDR_STS.SPLY_ENFORCEMENT.getHdrStsNm().equals(recordMsg.xxHdrDplyStsNm_A1.getValue())
         // 2018/07/05 QC#25786 mod end
                || HDR_STS.PRFT.getHdrStsNm().equals(recordMsg.xxHdrDplyStsNm_A1.getValue())
                || HDR_STS.VLD.getHdrStsNm().equals(recordMsg.xxHdrDplyStsNm_A1.getValue())
                || HDR_STS.CR_HLD.getHdrStsNm().equals(recordMsg.xxHdrDplyStsNm_A1.getValue())) {
            // Del Start 2017/06/12 QC#19031
//            List<EZDBStringItem> param = createNYEL8810Parameters(recordMsg);
//            setArgForSubScreen(param.toArray(new EZDBStringItem[0]));
            // Del End 2017/06/12 QC#19031
            resultId = "GoToWorkflow";
        }

        setResult(resultId);

    }

    private List<EZDBStringItem> createNWAL1550Parameters(NWAL1570_ABMsg recordMsg) {

        List<EZDBStringItem> param = new ArrayList<EZDBStringItem>();

        // [0]:cpoOrdNum
        param.add(recordMsg.srcRefOrCpoOrdNum_A1);

        return param;
    }

    // Del Start 2017/06/12 QC#19031
//    private List<EZDBStringItem> createNYEL8810Parameters(NWAL1570_ABMsg recordMsg) {
//
//        List<EZDBStringItem> param = new ArrayList<EZDBStringItem>();
//
//        // nothing
//
//        return param;
//    }
    // Del End 2017/06/12 QC#19031

}
