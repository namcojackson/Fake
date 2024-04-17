/*
 * Copyright (c) 2008 Canon USA Inc. All rights reserved.
 * Original Author: Kazuhiro Tajima
 * Company: Fujitsu Limited
 * Date: Jan 7, 2009
 */
package parts.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PopupTestParameterSetter {

    public static Object[] getStubParameters( String stubDir, String businessApplicationId ) {
        
        String  stubFilePath = stubDir + businessApplicationId + ".properties";
            
        BufferedReader br = null;
        
        // 1. create EZDBMsg
        EZDBMsg bMsg = null;
        try {
            br   = new BufferedReader( new InputStreamReader( new FileInputStream( stubFilePath ) ) );
            bMsg = createEZDBMsg( br );
        } catch( IOException e ) {
//            e.printStackTrace();
        } finally {
            if( br != null ) {
                try {
                    br.close();
                } catch (IOException e) {
//                    e.printStackTrace();
                }
            }
        }
                   
        // 2. create Parameter for Popup Test
        Object[] ret = null;
        try {
            br  = new BufferedReader( new InputStreamReader( new FileInputStream( stubFilePath ) ) );
            ret = createParameter( br, bMsg );
        } catch( IOException e ) {
//            e.printStackTrace();
        } finally {
            if( br != null ) {
                try {
                    br.close();
                } catch (IOException e) {
//                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    
    private static EZDBMsg createEZDBMsg( BufferedReader br ) throws IOException {
        PopupTestBMsg bMsg = new PopupTestBMsg();
        
        String line = readNextLine( br );
        if ( line == null ) {
            return bMsg;
        }
        
        do {
            line = trimBoth( line );

            
            String indexStr   = trimBoth( line.substring( 0, line.indexOf( "=" ) ) );
            String ezdAttrStr = trimBoth( line.substring( line.indexOf( "{" ) + 1, line.indexOf( "}" ) ) );
//            String valueStr   = trimBoth( line.substring( line.indexOf( "[" ) + 1, line.indexOf( "]" ) ) );
            
//            System.out.println( "●indexStr   = " + indexStr );
//            System.out.println( "●ezdAttrStr = " + ezdAttrStr );
//            System.out.println( "●valueStr   = " + valueStr );
            
            // [0] Data type
            // [1] Java Type
            // [2] Array length
            // [3] Number of digits
            // [4] Number of decimal digits
            String[] attrs = ezdAttrStr.split( "," );
            String dataType               = trimBoth( attrs[0] );
            String javaType               = trimBoth( attrs[1] );
            String arrayLength            = trimBoth( attrs[2] );
            String numberOfDigits         = trimBoth( attrs[3] );
            String numberOfDeecimalDigits = trimBoth( attrs[4] );
            
            // Java Type is EZDeveloper
            if( isEZDType( javaType ) ) {
                // [0] Key name
                // [1] Variabler
                // [2] Qualifier
                // [3] Array length
                // [4] Data type
                // [5] Number of digits
                // [6] Number of decimal digits
                List<String> attrList = new ArrayList<String>();
                attrList.add( indexStr );
                attrList.add( indexStr );
                attrList.add( null );
                attrList.add( arrayLength );
                attrList.add( dataType );
                attrList.add( numberOfDigits );
                attrList.add( numberOfDeecimalDigits );
                bMsg.addBaseContent( attrList.toArray( new String[0] ) );
            }
        } while( (line = readNextLine( br )) != null );
            
        return bMsg;
    }

    private static Object[] createParameter( BufferedReader br, EZDBMsg bMsg ) throws IOException {
        List<EZDMsgItemizable> paramList = new ArrayList<EZDMsgItemizable>(); 
        
        String line = readNextLine( br );
        if ( line == null ) {
            return new Object[0];
        }
        
        do {
            line = trimBoth( line );

            
            String indexStr   = trimBoth( line.substring( 0, line.indexOf( "=" ) ) );
            String ezdAttrStr = trimBoth( line.substring( line.indexOf( "{" ) + 1, line.indexOf( "}" ) ) );
            String valueStr   = trimBoth( line.substring( line.indexOf( "[" ) + 1, line.indexOf( "]" ) ) );
            
//            System.out.println( "●indexStr   = " + indexStr );
//            System.out.println( "●ezdAttrStr = " + ezdAttrStr );
//            System.out.println( "●valueStr   = " + valueStr );
            
            // [0] Data type
            // [1] Java Type
            // [2] Array length
            // [3] Number of digits
            // [4] Number of decimal digits
            String[] attrs = ezdAttrStr.split( "," );
//            String dataType               = trimBoth( attrs[0] );
            String javaType               = trimBoth( attrs[1] );
            String arrayLength            = trimBoth( attrs[2] );
//            String numberOfDigits         = trimBoth( attrs[3] );
//            String numberOfDeecimalDigits = trimBoth( attrs[4] );
            
            // Java Type is EZDeveloper
            if( isEZDType( javaType ) ) {
                // 単項目
                if( Integer.parseInt( arrayLength ) <= 0 ) {
                    paramList.add( createEZDBItem( bMsg, javaType, indexStr, valueStr ) );
                // 配列項目
                } else {
                    paramList.add( createEZDBItemArray( bMsg, javaType, indexStr, arrayLength, valueStr ) );
                }
            } else {
                
            }
            
        } while( (line = readNextLine( br )) != null );
            
        return paramList.toArray( new Object[0] );
    }
    
    
    private static EZDBItem createEZDBItem( EZDBMsg bMsg, String javaType, String key, String value ) {
        EZDBItem ezdbItem = null;
        
        if( javaType.equals( "EZDBStringItem" ) ) {
            ezdbItem = new EZDBStringItem();
            ezdbItem.init( bMsg, key, -1 );
            ((EZDBStringItem)ezdbItem).setValue( value );
        } else if( javaType.equals( "EZDBBigDecimalItem" ) ) {
            ezdbItem = new EZDBBigDecimalItem();
            ezdbItem.init( bMsg, key, -1 );
            ((EZDBBigDecimalItem)ezdbItem).setValue( new BigDecimal( value ) );
        } else if( javaType.equals( "EZDBDateItem" ) ) {
            ezdbItem = new EZDBDateItem();
            ezdbItem.init( bMsg, key, -1 );
            ((EZDBDateItem)ezdbItem).setValue( value );
        }

        return ezdbItem;
    }
    
    private static EZDBItemArray createEZDBItemArray( EZDBMsg bMsg, String javaType, String key, String arrayLength, String value ) {
        EZDBItemArray ezdbItemArray = null;

        String[] values = value.split( "," );
        if( javaType.equals( "EZDBStringItem" ) ) {
            ezdbItemArray = new EZDBStringItemArray();
            ezdbItemArray.init( bMsg, key, Integer.parseInt( arrayLength ) );
            for( int i = 0; i < values.length; i ++ ) {
                //TODO
                System.out.println( "★values["+i+"] =" + values[i] );
//                ezdbItemArray.get( i ).setValueString( values[i] );
                if( ezdbItemArray.getItemList()[i] == null ) {
                    ((EZDBStringItemArray)ezdbItemArray).no( i ).setValue( values[i] );
                }
                
            }
        } else if( javaType.equals( "EZDBBigDecimalItem" ) ) {
            ezdbItemArray = new EZDBBigDecimalItemArray();
            ezdbItemArray.init( bMsg, key, Integer.parseInt( arrayLength ) );
            for( int i = 0; i < values.length; i ++ ) {
                ezdbItemArray.get( i ).setValueString( values[i] );
            }
        } else if( javaType.equals( "EZDBDateItem" ) ) {
            ezdbItemArray = new EZDBDateItemArray();
            ezdbItemArray.init( bMsg, key, Integer.parseInt( arrayLength ) );
            for( int i = 0; i < values.length; i ++ ) {
                ezdbItemArray.get( i ).setValueString( values[i] );
            }
        }
        
        return ezdbItemArray;
    }
    
    private static String readNextLine( BufferedReader inputLine ) throws IOException {
        String line;
        while ( (line = inputLine.readLine() ) != null ) {
            if ( trimBoth( line ).length() == 0 ) {
                continue;
            }
            if ( line.charAt(0) != '#' ) {
                return line;
            }
            line = inputLine.readLine();
        }
        return null;
    }
 
    private static boolean isEZDType( String javaType ) {
        return "EZDBStringItem".equals( javaType ) || "EZDBBigDecimalItem".equals( javaType ) || "EZDBDateItem".equals( javaType );
    }
    
    private static String trimBoth( String str ) {
        return EZDCommonFunc.trimTail( EZDCommonFunc.trimHead( str ) );
    }
    
}