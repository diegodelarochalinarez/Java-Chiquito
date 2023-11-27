package modelo;

import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class AnalizadorSintactico {
    private Vector<String> tokens;
    private int indiceActual;
    private String errorSintactico;
    private String todosLosTokensString;

    public AnalizadorSintactico(Vector<String> tokens) {;
        this.tokens = tokens;
        this.indiceActual = 0;

        todosLosTokensString = "";
        for(int i=0; i<tokens.size(); i++){
            todosLosTokensString = todosLosTokensString+tokens.elementAt(i)+" ";
        }
        System.out.println(todosLosTokensString);
    }

    public void analizar() {
        while (indiceActual < tokens.size()) {
            analizarSentencia();
            indiceActual++;
        }
    }

    private void analizarSentencia() {
        String tokenActual = tokens.elementAt(indiceActual);

        if (esProgram()) {

        } else if (esClassList()) {

        } else if (esClass()){

        }
    }
     private boolean evaluarTxt(String expresionRegular, String texto){
                Pattern pattern = Pattern.compile(expresionRegular, Pattern.UNICODE_CASE); 
                Matcher matcher = pattern.matcher(texto);
                return matcher.find();
        }
    private boolean esProgram() {
        boolean band=false;
        //Program := (ClassList)? (ClassMethodList)? MainFunc
        //System.out.println("Evaluando "+todosLosTokensString);
        if(evaluarTxt("^ClassList? ClassMethodList? MainFunc",todosLosTokensString)){
            band=true;
        }
        return band;
    }

    private boolean esClassList() {
        boolean band=false;
       //ClassList := (Class)+
        if(evaluarTxt("^Class+",todosLosTokensString)){

        }
        return band;
    }

    private boolean esClass() {
        boolean band=false;
       //Class := class id { (private : Member)? (public : Member)? } 
        if(evaluarTxt("^class id\\{ private|Member? public|member? \\}",todosLosTokensString)){

        }
        return band;
    }
    
    private boolean esMember() {
        boolean band=false;
       //Member := (VarDeclList)? (MethodDeclList)? (MethodDefList)? 
        if(evaluarTxt("VarDecList? MethodDecList?\sMethodDefList?",todosLosTokensString)){

        }
        return band;
    }
     private boolean esVarDecList() {
        boolean band=false;
       //VarDeclList := (VarDecl)+ 
        if(evaluarTxt("VarDecl+ MethodDecList MethodDefList?",todosLosTokensString)){

        }
        return band;
    }
    private boolean esMethodDecList() {
        boolean band=false;
      // MethodDeclList := (FuncDecl)+
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
     private boolean esMethodDefList() {
        boolean band=false;
      //MethodDefList := (FuncDef)+
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
    private boolean esVarDecl() {
        boolean band=false;
      //VarDecl := Type Ident (= (int | float))? ; 
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
    private boolean esFuncDecl() {
        boolean band=false;
      //FuncDecl := Type id ( (ParamList)? ) ;
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
    private boolean esFuncDef() {
        boolean band=false;
      //FuncDef := Type id ( (ParamList)? ) CompoundStmt
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
    private boolean esClassMethodList() {
        boolean band=false;
      //ClassMethodList := (ClassMethodDef)+
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
private boolean esClassMethodDef() {
        boolean band=false;
      //ClassMethodDef := Type id :: id ( (ParamList)? ) CompoundStmt
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
private boolean esMainFunc() {
        boolean band=false;
      //MainFunc := int main ( ) CompoundStmt
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
private boolean esParamList() {
        boolean band=false;
      //ParamList := Param (, Param)*
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
private boolean esParam() {
        boolean band=false;
      //Param := Type Ident
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }

private boolean esIdent() {
        boolean band=false;
      //Ident := id | id [ intnum ]
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
private boolean esType() {
        boolean band=false;
      //Type := int | float | id
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
private boolean esCompoundStmt() {
        boolean band=false;
      //CompoundStmt := { (VarDeclList)? (StmtList)? }
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }

private boolean esStmtList() {
        boolean band=false;
      //StmtList := (Stmt)+
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
private boolean esStmt() {
        boolean band=false;
      //Stmt := ExprStmt| AssignStmt | RetStmt | WhileStmt | DoStmt | ForStmt | IfStmt | CompoundStmt | ;
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
private boolean esExprStmt() {
        boolean band=false;
      //Stmt := ExprStmt := Expr ;
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
private boolean esAssignStmt() {
        boolean band=false;
      //Stmt := AssignStmt := RefVarExpr = Expr ;
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }

private boolean esRetStmt() {
        boolean band=false;
      //RetStmt := return (Expr)? ;
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }

  private boolean esWhileStmt() {
        boolean band=false;
      //WhileStmt := while ( Expr ) Stmt
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }

  private boolean esDoStmt() {
        boolean band=false;
      //DoStmt := do Stmt while ( Expr ) ;
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
  private boolean esForStmt() {
        boolean band=false;
      //ForStmt := for ( Expr ; Expr ; Expr ) Stmt
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
private boolean esIfStmt() {
        boolean band=false;
      //IfStmt := if ( Expr ) Stmt (else Stmt)?
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
    private boolean esExpr() {
        boolean band=false;
      //Expr := OperExpr| RefExpr | intnum | floatnum
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
     private boolean esOperExpr() {
        boolean band=false;
      //OperExpr := unop Expr | Expr addiop Expr | Expr multop Expr | Expr relaop Expr | Expr eqltop Expr | ( Expr )
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
    private boolean esRefExpr() {
        boolean band=false;
      //RefExpr := RefVarExpr | RefCallExpr
        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
private boolean esRefVarExpr() {
        boolean band=false;
      //RefVarExpr := (RefExpr .)? IdentExpr

        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
private boolean esRefCallExpr() {
        boolean band=false;
      //RefCallExpr := (RefExpr .)? CallExpr

        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
private boolean esIdentExpr() {
        boolean band=false;
      //IdentExpr := id [ Expr ] | id

        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
private boolean esCallExpr() {
        boolean band=false;
      //CallExpr := id ( (ArgList)? )

        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }
private boolean esArgList() {
        boolean band=false;
      //ArgList := Expr (, Expr)*

        if(evaluarTxt("",todosLosTokensString)){

        }
        return band;
    }



}
