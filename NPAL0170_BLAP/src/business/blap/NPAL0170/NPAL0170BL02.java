/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL0170;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL0170.constant.NPAL0170Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * PO Text Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/24/2012   SRA             N.Otsuji        Create          N/A
 * 02/05/2013   CSAI            K.Lee           Update          WDS#69
 * 01/21/2016   CITS            K.Ogino         Update          CSA
 * 03/08/2016   CITS            K.Ogino         Update          QC#4975
 * 03/25/2016   CITS            T.Tokutomi      Update          QC#5773
 * 03/30/2016   CITS            T.Tokutomi      Update          QC#5755
 *</pre>
 */
public class NPAL0170BL02 extends S21BusinessHandler implements NPAL0170Constant {

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {

        super.preDoProcess(ezdCMsg, ezdSMsg);

        NPAL0170CMsg cMsg = (NPAL0170CMsg) ezdCMsg;

        String screenAplId = cMsg.getScreenAplID();

        try {
            if (INIT.equals(screenAplId)) {
                doProcess_NPAL0170_INIT(cMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            super.postDoProcess(ezdCMsg, ezdSMsg);
        }
    }

    /**
     * doProcess_NPAL0170_INIT
     * @param cMsg
     */
    private void doProcess_NPAL0170_INIT(NPAL0170CMsg cMsg) {
        String poMsgTpCd = cMsg.poMsgTpCd.getValue();
        String glblCmpyCd = getGlobalCompanyCode();

        // Set poMsgTpDescText

        String poMsgTpDescTxt = NPAL0170Query.getInstance().getPoMsgDescTxt(glblCmpyCd, poMsgTpCd);
        ZYPEZDItemValueSetter.setValue(cMsg.poMsgTpDescTxt, poMsgTpDescTxt);
    }

    private void setCmsg(NPAL0170CMsg cMsg, int count) {
        switch (count) {
            case 1:
                ZYPEZDItemValueSetter.setValue(cMsg.poMsgSegId_A0, new BigDecimal((count)));
                break;
            case 2:
                ZYPEZDItemValueSetter.setValue(cMsg.poMsgSegId_A1, new BigDecimal((count)));
                break;
            case 3:
                ZYPEZDItemValueSetter.setValue(cMsg.poMsgSegId_A2, new BigDecimal((count)));
                break;
            case 4:
                ZYPEZDItemValueSetter.setValue(cMsg.poMsgSegId_A3, new BigDecimal((count)));
                break;
            case 5:
                ZYPEZDItemValueSetter.setValue(cMsg.poMsgSegId_A4, new BigDecimal((count)));
                break;
            case 6:
                ZYPEZDItemValueSetter.setValue(cMsg.poMsgSegId_A5, new BigDecimal((count)));
                break;
            case 7:
                ZYPEZDItemValueSetter.setValue(cMsg.poMsgSegId_A6, new BigDecimal((count)));
                break;
            case 8:
                ZYPEZDItemValueSetter.setValue(cMsg.poMsgSegId_A7, new BigDecimal((count)));
                break;
            case 9:
                ZYPEZDItemValueSetter.setValue(cMsg.poMsgSegId_A8, new BigDecimal((count)));
                break;
            case 10:
                ZYPEZDItemValueSetter.setValue(cMsg.poMsgSegId_A9, new BigDecimal((count)));
                break;
            default:
                ZYPEZDItemValueSetter.setValue(cMsg.poMsgSegId_A0, new BigDecimal((count)));
                break;
        }
    }
}
