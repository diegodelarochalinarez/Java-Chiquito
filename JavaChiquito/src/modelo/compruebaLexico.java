package modelo;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * compruebaLexico
 */
public class compruebaLexico {
    
        private HashMap<String, String> keywordsAndOperatorsMap;

        public compruebaLexico() {
                //introducir simbolos especiales
                 this.keywordsAndOperatorsMap = new HashMap<String, String>();
                 keywordsAndOperatorsMap.put("for", "palabraReservada");
                 keywordsAndOperatorsMap.put("while", "palabraReservada");
                 keywordsAndOperatorsMap.put("do" , "palabraReservada");
                 //DoStmt := do Stmt while ( Expr ) ;
                 keywordsAndOperatorsMap.put("if" , "palabraReservada");
                 //IfStmt := if ( Expr ) Stmt (else Stmt)?
                 keywordsAndOperatorsMap.put("else" , "palabraReservada");
                 //elseStmt := else (Expr)
                 keywordsAndOperatorsMap.put("return" , "palabraReservada");
                 keywordsAndOperatorsMap.put("-" , "unop");
                 //unop : -
                 keywordsAndOperatorsMap.put("+" , "addiop");
                 keywordsAndOperatorsMap.put("-" , "addiop");
                 //addiop : + , -
                 keywordsAndOperatorsMap.put("*" , "multop");
                 keywordsAndOperatorsMap.put("/" , "multop");
                 //multop : * , / 
                 keywordsAndOperatorsMap.put("<" , "relaop");
                 keywordsAndOperatorsMap.put(">" , "relaop");
                 keywordsAndOperatorsMap.put("<=" , "relaop");
                 keywordsAndOperatorsMap.put(">=" , "relaop");
                 //relaop : <, >, <=, >=
                 keywordsAndOperatorsMap.put("==" , "eqltop");
                keywordsAndOperatorsMap.put("!=" , "eqltop");
                 //eqltop : == , != 
                
        }
        
        private boolean evaluarToken(String expresionRegular, String token){
                Pattern pattern = Pattern.compile(expresionRegular, Pattern.UNICODE_CASE); 
                Matcher matcher = pattern.matcher(token);
                return matcher.find();
        }
        
        public String analizadorDeTokens(String token){

                if(keywordsAndOperatorsMap.containsKey(token)){
                        return keywordsAndOperatorsMap.get(token);
                }

                if(evaluarToken("^[^\\d][A-Za-z0-9_]*$", token)) {
                        //mandar a la tabla de simbolos??
                        
                        return "id"; //si es un nombre de variable
                }

                if(evaluarToken("^\\d+$", token)) return "intnum";
                
                if(evaluarToken("^[0-9]+\\.[0-9]+$", token)) return "floatnum";
                
                
                

                return null; 
                //solo llega aqui si no es ninguno de los anteriores, esto es un ERROR LEXICO
        }
}