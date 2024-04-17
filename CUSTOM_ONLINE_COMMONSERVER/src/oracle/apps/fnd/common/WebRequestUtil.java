/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.apps.fnd.common;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import canon.apps.common.CanonS21SessionValidate;

public class WebRequestUtil {
	/*
	 * Minic OA Framework
	 */
    public static WebAppsContext validateContext(HttpServletRequest request, HttpServletResponse response) throws IOException{
        return CanonS21SessionValidate.validateContext(request, response);
    }
    
    static void printSessions(HttpServletRequest request, OutputStream out) throws IOException{
        HttpSession session=request.getSession();
        Enumeration<String> names= session.getAttributeNames();
        PrintStream ps=new PrintStream (out);
        ps.println("<!--");
        for(String name:Collections.list(names)){
            String line=name+"=="+session.getAttribute(name);
            System.out.println(line);
            ps.println(line);
        }
        ps.println("-->");
        
    }
    
}