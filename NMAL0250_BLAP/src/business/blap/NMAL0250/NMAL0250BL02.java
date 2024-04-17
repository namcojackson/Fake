/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL0250;

import static business.blap.NMAL0250.constant.NMAL0250Constant.INIT;
import static business.blap.NMAL0250.constant.NMAL0250Constant.MAX_COUNT;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.MDSETMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * BOM Text Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/28/2018   CITS            K.Kameoka       Create          #22324
 *</pre>
 */
public class NMAL0250BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {

        super.preDoProcess(ezdCMsg, ezdSMsg);

        NMAL0250CMsg cMsg = (NMAL0250CMsg) ezdCMsg;

        String screenAplId = cMsg.getScreenAplID();

        try {
            if (INIT.equals(screenAplId)) {
                doProcess_NMAL0250_INIT(cMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            super.postDoProcess(ezdCMsg, ezdSMsg);
        }
    }

    /**
     * doProcess_NMAL0250_INIT
     * @param cMsg
     */
    private void doProcess_NMAL0250_INIT(NMAL0250CMsg cMsg) {

        String mdseCd = cMsg.mdseCd.getValue();
        String glblCmpyCd = getGlobalCompanyCode();

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, mdseTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, mdseTMsg.mdseDescShortTxt);

        S21SsmEZDResult ssmResult = NMAL0250Query.getInstance().getPoMessageList(glblCmpyCd, mdseCd, MAX_COUNT);

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (Map<String, Object> cmpsnMsgMap : resultMapList) {
                setCmsg(cMsg, cmpsnMsgMap);
            }
        }
    }

    /**
     * setCmsg
     * @param cMsg NMAL0250CMsg
     * @param cmpsnMsgMap
     */
    private void setCmsg(NMAL0250CMsg cMsg, Map<String, Object> cmpsnMsgMap) {

        BigDecimal msgPk = (BigDecimal) cmpsnMsgMap.get("CMPSN_MSG_PK");
        BigDecimal segID = (BigDecimal) cmpsnMsgMap.get("CMPSN_MSG_SEG_ID");
        String msgTxt = (String) cmpsnMsgMap.get("CMPSN_MSG_TXT");

        int count = segID.intValue();

        switch (count) {
            case 1:
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgPk_A0, msgPk);
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgSegId_A0, segID);
                if (!ZYPCommonFunc.hasValue(cMsg.cmpsnMsgTxt_A0)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgTxt_A0, msgTxt);
                }
                break;
            case 2:
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgPk_A1, msgPk);
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgSegId_A1, segID);
                if (!ZYPCommonFunc.hasValue(cMsg.cmpsnMsgTxt_A1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgTxt_A1, msgTxt);
                }
                break;
            case 3:
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgPk_A2, msgPk);
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgSegId_A2, segID);
                if (!ZYPCommonFunc.hasValue(cMsg.cmpsnMsgTxt_A2)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgTxt_A2, msgTxt);
                }
                break;
            case 4:
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgPk_A3, msgPk);
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgSegId_A3, segID);
                if (!ZYPCommonFunc.hasValue(cMsg.cmpsnMsgTxt_A3)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgTxt_A3, msgTxt);
                }
                break;
            case 5:
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgPk_A4, msgPk);
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgSegId_A4, segID);
                if (!ZYPCommonFunc.hasValue(cMsg.cmpsnMsgTxt_A4)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgTxt_A4, msgTxt);
                }
                break;
            case 6:
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgPk_A5, msgPk);
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgSegId_A5, segID);
                if (!ZYPCommonFunc.hasValue(cMsg.cmpsnMsgTxt_A5)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgTxt_A5, msgTxt);
                }
                break;
            case 7:
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgPk_A6, msgPk);
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgSegId_A6, segID);
                if (!ZYPCommonFunc.hasValue(cMsg.cmpsnMsgTxt_A6)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgTxt_A6, msgTxt);
                }
                break;
            case 8:
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgPk_A7, msgPk);
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgSegId_A7, segID);
                if (!ZYPCommonFunc.hasValue(cMsg.cmpsnMsgTxt_A7)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgTxt_A7, msgTxt);
                }
                break;
            case 9:
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgPk_A8, msgPk);
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgSegId_A8, segID);
                if (!ZYPCommonFunc.hasValue(cMsg.cmpsnMsgTxt_A8)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgTxt_A8, msgTxt);
                }
                break;
            case 10:
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgPk_A9, msgPk);
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgSegId_A9, segID);
                if (!ZYPCommonFunc.hasValue(cMsg.cmpsnMsgTxt_A9)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgTxt_A9, msgTxt);
                }
                break;
            case 11:
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgPk_AA, msgPk);
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgSegId_AA, segID);
                if (!ZYPCommonFunc.hasValue(cMsg.cmpsnMsgTxt_AA)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgTxt_AA, msgTxt);
                }
                break;
            case 12:
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgPk_AB, msgPk);
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgSegId_AB, segID);
                if (!ZYPCommonFunc.hasValue(cMsg.cmpsnMsgTxt_AB)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgTxt_AB, msgTxt);
                }
                break;
            case 13:
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgPk_AC, msgPk);
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgSegId_AC, segID);
                if (!ZYPCommonFunc.hasValue(cMsg.cmpsnMsgTxt_AC)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgTxt_AC, msgTxt);
                }
                break;
            case 14:
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgPk_AD, msgPk);
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgSegId_AD, segID);
                if (!ZYPCommonFunc.hasValue(cMsg.cmpsnMsgTxt_AD)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgTxt_AD, msgTxt);
                }
                break;
            case 15:
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgPk_AE, msgPk);
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgSegId_AE, segID);
                if (!ZYPCommonFunc.hasValue(cMsg.cmpsnMsgTxt_AE)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgTxt_AE, msgTxt);
                }
                break;
            default:
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgPk_A0, msgPk);
                ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgSegId_A0, segID);
                if (!ZYPCommonFunc.hasValue(cMsg.cmpsnMsgTxt_A0)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.cmpsnMsgTxt_A0, msgTxt);
                }
                break;
        }
    }
}
