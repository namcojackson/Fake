package business.blap.NSAL9900.addon;

import business.blap.NSAL9900.NSAL9900CMsg;
import business.blap.NSAL9900.NSAL9900SMsg;

/**
 * <pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 2015/05/11   Hitachi         T.Aoyagi        Create          N/A
 * </pre>
 */
public interface Addon {

    /**
     * validation
     * @param cMsg NSAL9900CMsg
     * @param sMsg NSAL9900SMsg
     * @return boolean
     */
    public boolean validation(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg);
}
