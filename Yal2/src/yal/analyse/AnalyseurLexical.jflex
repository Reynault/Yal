package yal.analyse ;

import java_cup.runtime.*;
import yal.exceptions.AnalyseLexicaleException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{

  private StringBuilder chaine ;

  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

idf = [A-Za-z_][A-Za-z_0-9]*

csteE = [0-9]+
guillemet = [\"]

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]

commentaire = [/][/].*

%%

"programme"            { return symbol(CodesLexicaux.PROGRAMME); }
"debut"                { return symbol(CodesLexicaux.DEBUT); }
"fin"              	   { return symbol(CodesLexicaux.FIN); }

"entier"                 { return symbol(CodesLexicaux.TYPE, yytext()); }

"ecrire"               { return symbol(CodesLexicaux.ECRIRE); }

"=="                   { return symbol(CodesLexicaux.EGALLOGIQUE, yytext()); }

"ou"                   { return symbol(CodesLexicaux.OU, yytext()); }

"et"                   { return symbol(CodesLexicaux.ET, yytext()); }

"!="                   { return symbol(CodesLexicaux.DIFFERENT, yytext()); }

"<"                   { return symbol(CodesLexicaux.INFERIEUR, yytext()); }

">"                   { return symbol(CodesLexicaux.SUPERIEUR, yytext()); }

"="                    { return symbol(CodesLexicaux.EGAL, yytext()); }

"non"                   { return symbol(CodesLexicaux.NON, yytext()); }

"+"                   { return symbol(CodesLexicaux.ADDITION, yytext()); }

"-"                   { return symbol(CodesLexicaux.MOINS, yytext()); }

"/"                   { return symbol(CodesLexicaux.DIVISION, yytext()); }

"*"                   { return symbol(CodesLexicaux.MULTIPLICATION, yytext()); }

"lire"                 { return symbol(CodesLexicaux.LIRE); }

";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }

"("                    { return symbol(CodesLexicaux.PAROUVERT); }

")"                    { return symbol(CodesLexicaux.PARFERMER); }

{csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }

{idf}      	           { return symbol(CodesLexicaux.IDF, yytext()); }

{commentaire}          { }

{espace}               { }
.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }