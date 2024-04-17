package canon.apps.common;

import java.io.InputStream;

import com.aspose.cells.License;

public class CanonAsposeUtil {
	
    public static void loadLicense() {
		try {
			if (!License.isLicenseSet()) {
				License lic = new License();
				InputStream is = lic.getClass().getClassLoader().getResourceAsStream("META-INF/Aspose.Cells.lic");
				if (is != null) {
					lic.setLicense(is);
					is.close();
					System.out.println("ASPOSE License loaded.");
				} else {
					System.out.println("ASPOSE License not found.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
    public static void main(String [] args){
    	try {
			loadLicense();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
    }
}
