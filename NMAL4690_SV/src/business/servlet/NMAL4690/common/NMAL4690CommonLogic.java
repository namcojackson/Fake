/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NMAL4690.common;

import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL4690.NMAL4690BMsg;
import business.servlet.NMAL4690.constant.NMAL4690Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * Class name: NMAL4690CommonLogic
 * <dd>The class explanation: Common Logic for screen component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NMAL4690CommonLogic implements NMAL4690Constant {

    public static void initCommonButton(NMAL4690BMsg scrnMsg, EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_BLANK8[0], BTN_CMN_BLANK8[1], BTN_CMN_BLANK8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK10[0], BTN_CMN_BLANK10[1], BTN_CMN_BLANK10[2], 1, null);
    }


    /**
     * Method name: setNameForMessage
     * <dd>The method explanation: Set Name For Message.
     * <dd>Remarks:
     * @param scrnMsg NMAL4690BMsg
     */
    public static void setNameForMessage(NMAL4690BMsg scrnMsg) {

//        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    }

    /**
     * Method name: setInputProtectedForListInputFiled
     * <dd>The method explanation: Set input protected
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public void setInputProtectedForListInputFiled(NMAL4690BMsg scrnMsg) {
        
        scrnMsg.cmpyNm.setInputProtected(true);
        
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mstrAttDataNm_A.setInputProtected(true);
            scrnMsg.A.no(i).xxCondNm_A.setInputProtected(true);
            scrnMsg.A.no(i).xxAllPtyAddr_A.setInputProtected(true);
            scrnMsg.A.no(i).mstrAttDataVol_A.setInputProtected(true);
            scrnMsg.A.no(i).mstrAttDataDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).mstrActvNm_A.setInputProtected(true);
        }
    }
    
    /**
     * get "party location pk" from ezd parameters.
     * @param ezdParam ezd parameters from called screen
     * @return attachments grouping text
     */
    public static BigDecimal getPtyLocPk(NMAL4690BMsg scrnMsg, Serializable ezdParam) {

        EZDBBigDecimalItem ezdItem = scrnMsg.ptyLocPk;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            ezdItem.setValue(getParameterBigDecimal(ezdParam, PARAM_PTY_LOC_PK));
        }
        return ezdItem.getValue();
    }

    /**
     * get "company pk" from ezd parameters.
     * @param ezdParam ezd parameters from called screen
     * @return attachments grouping text
     */
    public static BigDecimal getCmpyPk(NMAL4690BMsg scrnMsg, Serializable ezdParam) {

        EZDBBigDecimalItem ezdItem = scrnMsg.cmpyPk;

        if (!ZYPCommonFunc.hasValue(ezdItem)) {
            ezdItem.setValue(getParameterBigDecimal(ezdParam, PARAM_CMPY_PK));
        }
        return ezdItem.getValue();
    }
    
    private static BigDecimal getParameterBigDecimal(Serializable ezdParam, int getIndex) {

        Object[] params = getArgForSubScreen(ezdParam);

        if (params.length > getIndex) {
            if (params[getIndex] instanceof BigDecimal) {
                return (BigDecimal) params[getIndex];
            } else {
                throw new S21AbendException("Illegal Argument. Parameter[" + getIndex + "] must be 'String'. But... -> [" + params[getIndex] + "]");
            }
        } else {
            throw new S21AbendException("Illegal Argument. Parameter length must be '6'. But... -> [" + params.length + "]");
        }
    }
    
    private static Object[] getArgForSubScreen(Serializable ezdParams) {
        if (ezdParams instanceof Object[]) {
            return (Object[]) ezdParams;
        } else {
            throw new S21AbendException("Illegal Argument. Parameter must be Object[]. But... -> [" + ezdParams + "]");
        }
    }
    
    
}
