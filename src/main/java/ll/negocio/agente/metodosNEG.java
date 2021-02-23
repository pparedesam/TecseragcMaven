/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.agente;

import java.text.DecimalFormatSymbols;

/**
 *
 * @author CesGue
 */
public class metodosNEG {
    
    public static boolean isStringNumeric( String str )
{
    DecimalFormatSymbols currentLocaleSymbols = DecimalFormatSymbols.getInstance();
    char localeMinusSign = currentLocaleSymbols.getMinusSign();

    if ( !Character.isDigit( str.charAt( 0 ) ) && str.charAt( 0 ) != localeMinusSign ) return false;

    boolean isDecimalSeparatorFound = false;
    char localeDecimalSeparator = currentLocaleSymbols.getDecimalSeparator();

    for ( char c : str.substring( 1 ).toCharArray() )
    {
        if ( !Character.isDigit( c ) )
        {
            if ( c == localeDecimalSeparator && !isDecimalSeparatorFound )
            {
                isDecimalSeparatorFound = true;
                continue;
            }
            return false;
        }
    }
    return true;
}
}
