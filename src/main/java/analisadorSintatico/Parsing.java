package analisadorSintatico;

import java.util.HashMap;
import java.util.Map;

public class Parsing {

    private final Map<String, String> grammarTable = new HashMap<>();


    public Parsing() {
        this.initGrammarTable();
    }

    public Boolean containsKey(Integer key_1, Integer key_2) {
        return grammarTable.containsKey(formatKey(key_1, key_2));
    }

    public String[] getCombinado(Integer key_1, Integer key_2) {
        String chave = formatKey(key_1,key_2);

        if(grammarTable.containsKey(chave)) {
            return (grammarTable.get(chave)).split("\\|");
        }
        return null;
    }

    private String formatKey(Integer key_1, Integer key_2) {
        return key_1.toString() + "," + key_2.toString();
    }

    public void initGrammarTable() {
        grammarTable.put("52,1","PROGRAM|IDENTIFICADOR|;|BLOCO|.");
        grammarTable.put("53,2","DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO");
        grammarTable.put("53,3","DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO");
        grammarTable.put("53,4","DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO");
        grammarTable.put("53,5","DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO");
        grammarTable.put("53,6","DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO");
        grammarTable.put("54,2","LABEL|LID|;");
        grammarTable.put("54,3","NULL");
        grammarTable.put("54,4","NULL");
        grammarTable.put("54,5","NULL");
        grammarTable.put("54,6","NULL");
        grammarTable.put("55,25","IDENTIFICADOR|REPIDENT");
        grammarTable.put("56,39","NULL");
        grammarTable.put("56,46",",|IDENTIFICADOR|REPIDENT");
        grammarTable.put("56,47","NULL");
        grammarTable.put("57,3","CONST|IDENTIFICADOR|=|INTEIRO|;|LDCONST");
        grammarTable.put("57,4","NULL");
        grammarTable.put("57,5","NULL");
        grammarTable.put("57,6","NULL");
        grammarTable.put("58,4","NULL");
        grammarTable.put("58,5","NULL");
        grammarTable.put("58,6","NULL");
        grammarTable.put("58,25","IDENTIFICADOR|=|INTEIRO|;|LDCONST");
        grammarTable.put("59,4","VAR|LID|:|TIPO|;|LDVAR");
        grammarTable.put("59,5","NULL");
        grammarTable.put("59,6","NULL");
        grammarTable.put("60,5","NULL");
        grammarTable.put("60,6","NULL");
        grammarTable.put("60,25","LID|:|TIPO|;|LDVAR");
        grammarTable.put("61,8","INTEGER");
        grammarTable.put("61,9","ARRAY|[|INTEIRO|..|INTEIRO|]|OF|INTEGER");
        grammarTable.put("62,5","PROCEDURE|IDENTIFICADOR|DEFPAR|;|BLOCO|;|DCLPROC");
        grammarTable.put("62,6","NULL");
        grammarTable.put("63,36","(|LID|:|INTEGER|)");
        grammarTable.put("63,39","NULL");
        grammarTable.put("64,6","BEGIN|COMANDO|REPCOMANDO|END");
        grammarTable.put("65,7","NULL");
        grammarTable.put("65,47",";|COMANDO|REPCOMANDO");
        grammarTable.put("66,6","CORPO");
        grammarTable.put("66,7","NULL");
        grammarTable.put("66,11","CALL|IDENTIFICADOR|PARAMETROS");
        grammarTable.put("66,12","GOTO|IDENTIFICADOR");
        grammarTable.put("66,13","IF|EXPRESSAO|THEN|COMANDO|ELSEPARTE");
        grammarTable.put("66,15","NULL");
        grammarTable.put("66,16","WHILE|EXPRESSAO|DO|COMANDO");
        grammarTable.put("66,18","REPEAT|COMANDO|UNTIL|EXPRESSAO");
        grammarTable.put("66,19","NULL");
        grammarTable.put("66,20","READLN|(|VARIAVEL|REPVARIAVEL|)");
        grammarTable.put("66,21","WRITELN|(|ITEMSAIDA|REPITEM|)");
        grammarTable.put("66,25","IDENTIFICADOR|RCOMID");
        grammarTable.put("66,27","FOR|IDENTIFICADOR|:=|EXPRESSAO|TO|EXPRESSAO|DO|COMANDO");
        grammarTable.put("66,29","CASE|EXPRESSAO|OF|CONDCASE|END");
        grammarTable.put("66,47","NULL");
        grammarTable.put("67,34","RVAR|:=|EXPRESSAO");
        grammarTable.put("67,38","RVAR|:=|EXPRESSAO");
        grammarTable.put("67,39",":|COMANDO");
        grammarTable.put("68,34","[|EXPRESSAO|]");
        grammarTable.put("68,38","NULL");
        grammarTable.put("69,7","NULL");
        grammarTable.put("69,15","NULL");
        grammarTable.put("69,19","NULL");
        grammarTable.put("69,36","(|EXPRESSAO|REPPAR|)");
        grammarTable.put("69,47","NULL");
        grammarTable.put("70,37","NULL");
        grammarTable.put("70,46",",|EXPRESSAO|REPPAR");
        grammarTable.put("71,7","NULL");
        grammarTable.put("71,15","ELSE|COMANDO");
        grammarTable.put("71,19","NULL");
        grammarTable.put("71,47","NULL");
        grammarTable.put("72,25","IDENTIFICADOR|VARIAVEL1");
        grammarTable.put("73,7","NULL");
        grammarTable.put("73,10","NULL");
        grammarTable.put("73,14","NULL");
        grammarTable.put("73,15","NULL");
        grammarTable.put("73,17","NULL");
        grammarTable.put("73,19","NULL");
        grammarTable.put("73,22","NULL");
        grammarTable.put("73,23","NULL");
        grammarTable.put("73,28","NULL");
        grammarTable.put("73,30","NULL");
        grammarTable.put("73,31","NULL");
        grammarTable.put("73,32","NULL");
        grammarTable.put("73,33","NULL");
        grammarTable.put("73,34","[|EXPRESSAO|]");
        grammarTable.put("73,35","NULL");
        grammarTable.put("73,37","NULL");
        grammarTable.put("73,40","NULL");
        grammarTable.put("73,41","NULL");
        grammarTable.put("73,42","NULL");
        grammarTable.put("73,43","NULL");
        grammarTable.put("73,44","NULL");
        grammarTable.put("73,45","NULL");
        grammarTable.put("73,46","NULL");
        grammarTable.put("73,47","NULL");
        grammarTable.put("74,37","NULL");
        grammarTable.put("74,46",",|VARIAVEL|REPVARIAVEL");
        grammarTable.put("75,24","EXPRESSAO");
        grammarTable.put("75,25","EXPRESSAO");
        grammarTable.put("75,26","EXPRESSAO");
        grammarTable.put("75,30","EXPRESSAO");
        grammarTable.put("75,31","EXPRESSAO");
        grammarTable.put("75,36","EXPRESSAO");
        grammarTable.put("75,48","LITERAL");
        grammarTable.put("76,37","NULL");
        grammarTable.put("76,46",",|ITEMSAIDA|REPITEM");
        grammarTable.put("77,24","EXPSIMP|REPEXPSIMP");
        grammarTable.put("77,25","EXPSIMP|REPEXPSIMP");
        grammarTable.put("77,26","EXPSIMP|REPEXPSIMP");
        grammarTable.put("77,30","EXPSIMP|REPEXPSIMP");
        grammarTable.put("77,31","EXPSIMP|REPEXPSIMP");
        grammarTable.put("77,36","EXPSIMP|REPEXPSIMP");
        grammarTable.put("78,7","NULL");
        grammarTable.put("78,10","NULL");
        grammarTable.put("78,14","NULL");
        grammarTable.put("78,15","NULL");
        grammarTable.put("78,17","NULL");
        grammarTable.put("78,19","NULL");
        grammarTable.put("78,28","NULL");
        grammarTable.put("78,35","NULL");
        grammarTable.put("78,37","NULL");
        grammarTable.put("78,40","=|EXPSIMP");
        grammarTable.put("78,41",">|EXPSIMP");
        grammarTable.put("78,42",">=|EXPSIMP");
        grammarTable.put("78,43","<|EXPSIMP");
        grammarTable.put("78,44","<=|EXPSIMP");
        grammarTable.put("78,45","<>|EXPSIMP");
        grammarTable.put("78,46","NULL");
        grammarTable.put("78,47","NULL");
        grammarTable.put("79,24","TERMO|REPEXP");
        grammarTable.put("79,25","TERMO|REPEXP");
        grammarTable.put("79,26","TERMO|REPEXP");
        grammarTable.put("79,30","+|TERMO|REPEXP");
        grammarTable.put("79,31","-|TERMO|REPEXP");
        grammarTable.put("79,36","TERMO|REPEXP");
        grammarTable.put("80,7","NULL");
        grammarTable.put("80,10","NULL");
        grammarTable.put("80,14","NULL");
        grammarTable.put("80,15","NULL");
        grammarTable.put("80,17","NULL");
        grammarTable.put("80,19","NULL");
        grammarTable.put("80,22","OR|TERMO|REPEXP");
        grammarTable.put("80,28","NULL");
        grammarTable.put("80,30","+|TERMO|REPEXP");
        grammarTable.put("80,31","-|TERMO|REPEXP");
        grammarTable.put("80,35","NULL");
        grammarTable.put("80,37","NULL");
        grammarTable.put("80,40","NULL");
        grammarTable.put("80,41","NULL");
        grammarTable.put("80,42","NULL");
        grammarTable.put("80,43","NULL");
        grammarTable.put("80,44","NULL");
        grammarTable.put("80,45","NULL");
        grammarTable.put("80,46","NULL");
        grammarTable.put("80,47","NULL");
        grammarTable.put("81,24","FATOR|REPTERMO");
        grammarTable.put("81,25","FATOR|REPTERMO");
        grammarTable.put("81,26","FATOR|REPTERMO");
        grammarTable.put("81,36","FATOR|REPTERMO");
        grammarTable.put("82,7","NULL");
        grammarTable.put("82,10","NULL");
        grammarTable.put("82,14","NULL");
        grammarTable.put("82,15","NULL");
        grammarTable.put("82,17","NULL");
        grammarTable.put("82,19","NULL");
        grammarTable.put("82,22","NULL");
        grammarTable.put("82,23","AND|FATOR|REPTERMO");
        grammarTable.put("82,28","NULL");
        grammarTable.put("82,30","NULL");
        grammarTable.put("82,31","NULL");
        grammarTable.put("82,32","*|FATOR|REPTERMO");
        grammarTable.put("82,33","/|FATOR|REPTERMO");
        grammarTable.put("82,35","NULL");
        grammarTable.put("82,37","NULL");
        grammarTable.put("82,40","NULL");
        grammarTable.put("82,41","NULL");
        grammarTable.put("82,42","NULL");
        grammarTable.put("82,43","NULL");
        grammarTable.put("82,44","NULL");
        grammarTable.put("82,45","NULL");
        grammarTable.put("82,46","NULL");
        grammarTable.put("82,47","NULL");
        grammarTable.put("83,24","NOT|FATOR");
        grammarTable.put("83,25","VARIAVEL");
        grammarTable.put("83,26","INTEIRO");
        grammarTable.put("83,36","(|EXPRESSAO|)");
        grammarTable.put("84,26","INTEIRO|RPINTEIRO|:|COMANDO|CONTCASE");
        grammarTable.put("85,7","NULL");
        grammarTable.put("85,47",";|CONDCASE");
        grammarTable.put("86,39","NULL");
        grammarTable.put("86,46",",|INTEIRO|RPINTEIRO");
    }
}