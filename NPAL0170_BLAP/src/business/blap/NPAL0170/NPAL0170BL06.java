/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL0170;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL0170.constant.NPAL0170Constant;

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
 *</pre>
 */
public class NPAL0170BL06 extends S21BusinessHandler implements NPAL0170Constant {

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {

        super.preDoProcess(ezdCMsg, ezdSMsg);
        super.postDoProcess(ezdCMsg, ezdSMsg);
    }
}
