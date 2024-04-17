/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2010   Fujitsu         K.Tajima        Create          N/A
 *</pre>
 */
package business.servlet.NWDL0260;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWDL0260.constant.NWDL0260Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NWDL0260Scrn00_OpenWin_Merchandise extends S21CommonHandler implements NWDL0260Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing to do.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWDL0260BMsg scrnMsg = (NWDL0260BMsg) bMsg;

        setArgForSubScreen(createNMAL6040Parameters(scrnMsg).toArray(new EZDBStringItem[0]));

        // regist this event-name to BMsg.
        scrnMsg.xxScrEventNm.setValue(getClass().getSimpleName());
    }

    static List<EZDBStringItem> createNMAL6040Parameters(NWDL0260BMsg scrnMsg) {

        final List<EZDBStringItem> params = new ArrayList<EZDBStringItem>();

        final EZDBStringItem xxPopPrm = scrnMsg.xxPopPrm;
        xxPopPrm.clear();

        EZDBStringItem paramItem;

        // --------------------------------------------------
        // Request
        // --------------------------------------------------
        // [0]:ZEROTH_PROD_CTRL_CD
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [1]:ZEROTH_PROD_CTRL_NM
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [2]:FIRST_PROD_CTRL_CD
        paramItem = scrnMsg.firstProdCtrlCd;
        params.add(paramItem);
        // [3]:FIRST_PROD_CTRL_NM
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [4]:SCD_PROD_CTRL_CD
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [5]:SCD_PROD_CTRL_NM
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [6]:THIRD_PROD_CTRL_CD
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [7]:THIRD_PROD_CTRL_NM
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [8]:FRTH_PROD_CTRL_CD
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [9]:FRTH_PROD_CTRL_NM
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [10]:FIFTH_PROD_CTRL_CD
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [11]:FIFTH_PROD_CTRL_NM
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [12]:MDSE_CD
        paramItem = scrnMsg.mdseCd;
        params.add(paramItem);
        // [13]:MDSE_NM
        paramItem = xxPopPrm;
        params.add(paramItem);

        // --------------------------------------------------
        // Response
        // --------------------------------------------------
        // [14]:ZEROTH_PROD_CTRL_CD
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [15]:ZEROTH_PROD_CTRL_NM
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [16]:FIRST_PROD_CTRL_CD
        paramItem = scrnMsg.firstProdCtrlCd;
        params.add(paramItem);
        // [17]:FIRST_PROD_CTRL_NM
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [18]:SCD_PROD_CTRL_CD
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [19]:SCD_PROD_CTRL_NM
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [20]:THIRD_PROD_CTRL_CD
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [21]:THIRD_PROD_CTRL_NM
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [22]:FRTH_PROD_CTRL_CD
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [23]:FRTH_PROD_CTRL_NM
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [24]:FIFTH_PROD_CTRL_CD
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [25]:FIFTH_PROD_CTRL_NM
        paramItem = xxPopPrm;
        params.add(paramItem);
        // [26]:MDSE_CD
        paramItem = scrnMsg.mdseCd;
        params.add(paramItem);
        // [27]:MDSE_NM
        paramItem = xxPopPrm;
        params.add(paramItem);

        return params;
    }
    
}
