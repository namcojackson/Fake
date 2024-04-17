package parts.common;

import java.util.ArrayList;
import java.util.List;

public class PopupTestBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	private static final long serialVersionUID = 1L;

	public PopupTestBMsg() {
		this(false, -1);
	}

	public PopupTestBMsg(boolean child, int eleNo) {
		super(child, eleNo);
	}
    
    @Override
    public EZDMsgArray getMyArray() {
        return null;
    }
    
    List<String[]> baseContents = new ArrayList<String[]>();
    
    protected String[][] getBaseContents() {
        return (String[][])baseContents.toArray( new String[0][0] );
    }
    
    void addBaseContent( String[] contents ) {
        baseContents.add( contents );
    }

}