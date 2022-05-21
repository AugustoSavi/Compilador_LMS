package analisadorSintatico;

import java.util.HashMap;
import java.util.Map;

public class Parsing {

    private final Map<String, String> parsingTable = new HashMap<>();


    public Parsing() {
        this.initGrammarTable();
    }

    public Boolean containsKey(Integer key_1, Integer key_2) {
        return parsingTable.containsKey(formatKey(key_1, key_2));
    }

    public String[] getCombinado(Integer key_1, Integer key_2) {
        String chave = formatKey(key_1, key_2);

        if (parsingTable.containsKey(chave)) {
            return (parsingTable.get(chave)).split("\\|");
        }
        return null;
    }

    private String formatKey(Integer key_1, Integer key_2) {
        return key_1.toString() + "," + key_2.toString();
    }

    public void initGrammarTable() {
        parsingTable.put("52,1", "PROGRAM|IDENTIFICADOR|;|BLOCO|.");
        parsingTable.put("53,2", "DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO");
        parsingTable.put("53,3", "DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO");
        parsingTable.put("53,4", "DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO");
        parsingTable.put("53,5", "DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO");
        parsingTable.put("53,6", "DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO");
        parsingTable.put("54,2", "LABEL|LID|;");
        parsingTable.put("54,3", "NULL");
        parsingTable.put("54,4", "NULL");
        parsingTable.put("54,5", "NULL");
        parsingTable.put("54,6", "NULL");
        parsingTable.put("55,25", "IDENTIFICADOR|REPIDENT");
        parsingTable.put("56,39", "NULL");
        parsingTable.put("56,46", ",|IDENTIFICADOR|REPIDENT");
        parsingTable.put("56,47", "NULL");
        parsingTable.put("57,3", "CONST|IDENTIFICADOR|=|INTEIRO|;|LDCONST");
        parsingTable.put("57,4", "NULL");
        parsingTable.put("57,5", "NULL");
        parsingTable.put("57,6", "NULL");
        parsingTable.put("58,4", "NULL");
        parsingTable.put("58,5", "NULL");
        parsingTable.put("58,6", "NULL");
        parsingTable.put("58,25", "IDENTIFICADOR|=|INTEIRO|;|LDCONST");
        parsingTable.put("59,4", "VAR|LID|:|TIPO|;|LDVAR");
        parsingTable.put("59,5", "NULL");
        parsingTable.put("59,6", "NULL");
        parsingTable.put("60,5", "NULL");
        parsingTable.put("60,6", "NULL");
        parsingTable.put("60,25", "LID|:|TIPO|;|LDVAR");
        parsingTable.put("61,8", "INTEGER");
        parsingTable.put("61,9", "ARRAY|[|INTEIRO|..|INTEIRO|]|OF|INTEGER");
        parsingTable.put("62,5", "PROCEDURE|IDENTIFICADOR|DEFPAR|;|BLOCO|;|DCLPROC");
        parsingTable.put("62,6", "NULL");
        parsingTable.put("63,36", "(|LID|:|INTEGER|)");
        parsingTable.put("63,39", "NULL");
        parsingTable.put("64,6", "BEGIN|COMANDO|REPCOMANDO|END");
        parsingTable.put("65,7", "NULL");
        parsingTable.put("65,47", ";|COMANDO|REPCOMANDO");
        parsingTable.put("66,6", "CORPO");
        parsingTable.put("66,7", "NULL");
        parsingTable.put("66,11", "CALL|IDENTIFICADOR|PARAMETROS");
        parsingTable.put("66,12", "GOTO|IDENTIFICADOR");
        parsingTable.put("66,13", "IF|EXPRESSAO|THEN|COMANDO|ELSEPARTE");
        parsingTable.put("66,15", "NULL");
        parsingTable.put("66,16", "WHILE|EXPRESSAO|DO|COMANDO");
        parsingTable.put("66,18", "REPEAT|COMANDO|UNTIL|EXPRESSAO");
        parsingTable.put("66,19", "NULL");
        parsingTable.put("66,20", "READLN|(|VARIAVEL|REPVARIAVEL|)");
        parsingTable.put("66,21", "WRITELN|(|ITEMSAIDA|REPITEM|)");
        parsingTable.put("66,25", "IDENTIFICADOR|RCOMID");
        parsingTable.put("66,27", "FOR|IDENTIFICADOR|:=|EXPRESSAO|TO|EXPRESSAO|DO|COMANDO");
        parsingTable.put("66,29", "CASE|EXPRESSAO|OF|CONDCASE|END");
        parsingTable.put("66,47", "NULL");
        parsingTable.put("67,34", "RVAR|:=|EXPRESSAO");
        parsingTable.put("67,38", "RVAR|:=|EXPRESSAO");
        parsingTable.put("67,39", ":|COMANDO");
        parsingTable.put("68,34", "[|EXPRESSAO|]");
        parsingTable.put("68,38", "NULL");
        parsingTable.put("69,7", "NULL");
        parsingTable.put("69,15", "NULL");
        parsingTable.put("69,19", "NULL");
        parsingTable.put("69,36", "(|EXPRESSAO|REPPAR|)");
        parsingTable.put("69,47", "NULL");
        parsingTable.put("70,37", "NULL");
        parsingTable.put("70,46", ",|EXPRESSAO|REPPAR");
        parsingTable.put("71,7", "NULL");
        parsingTable.put("71,15", "ELSE|COMANDO");
        parsingTable.put("71,19", "NULL");
        parsingTable.put("71,47", "NULL");
        parsingTable.put("72,25", "IDENTIFICADOR|VARIAVEL1");
        parsingTable.put("73,7", "NULL");
        parsingTable.put("73,10", "NULL");
        parsingTable.put("73,14", "NULL");
        parsingTable.put("73,15", "NULL");
        parsingTable.put("73,17", "NULL");
        parsingTable.put("73,19", "NULL");
        parsingTable.put("73,22", "NULL");
        parsingTable.put("73,23", "NULL");
        parsingTable.put("73,28", "NULL");
        parsingTable.put("73,30", "NULL");
        parsingTable.put("73,31", "NULL");
        parsingTable.put("73,32", "NULL");
        parsingTable.put("73,33", "NULL");
        parsingTable.put("73,34", "[|EXPRESSAO|]");
        parsingTable.put("73,35", "NULL");
        parsingTable.put("73,37", "NULL");
        parsingTable.put("73,40", "NULL");
        parsingTable.put("73,41", "NULL");
        parsingTable.put("73,42", "NULL");
        parsingTable.put("73,43", "NULL");
        parsingTable.put("73,44", "NULL");
        parsingTable.put("73,45", "NULL");
        parsingTable.put("73,46", "NULL");
        parsingTable.put("73,47", "NULL");
        parsingTable.put("74,37", "NULL");
        parsingTable.put("74,46", ",|VARIAVEL|REPVARIAVEL");
        parsingTable.put("75,24", "EXPRESSAO");
        parsingTable.put("75,25", "EXPRESSAO");
        parsingTable.put("75,26", "EXPRESSAO");
        parsingTable.put("75,30", "EXPRESSAO");
        parsingTable.put("75,31", "EXPRESSAO");
        parsingTable.put("75,36", "EXPRESSAO");
        parsingTable.put("75,48", "LITERAL");
        parsingTable.put("76,37", "NULL");
        parsingTable.put("76,46", ",|ITEMSAIDA|REPITEM");
        parsingTable.put("77,24", "EXPSIMP|REPEXPSIMP");
        parsingTable.put("77,25", "EXPSIMP|REPEXPSIMP");
        parsingTable.put("77,26", "EXPSIMP|REPEXPSIMP");
        parsingTable.put("77,30", "EXPSIMP|REPEXPSIMP");
        parsingTable.put("77,31", "EXPSIMP|REPEXPSIMP");
        parsingTable.put("77,36", "EXPSIMP|REPEXPSIMP");
        parsingTable.put("78,7", "NULL");
        parsingTable.put("78,10", "NULL");
        parsingTable.put("78,14", "NULL");
        parsingTable.put("78,15", "NULL");
        parsingTable.put("78,17", "NULL");
        parsingTable.put("78,19", "NULL");
        parsingTable.put("78,28", "NULL");
        parsingTable.put("78,35", "NULL");
        parsingTable.put("78,37", "NULL");
        parsingTable.put("78,40", "=|EXPSIMP");
        parsingTable.put("78,41", ">|EXPSIMP");
        parsingTable.put("78,42", ">=|EXPSIMP");
        parsingTable.put("78,43", "<|EXPSIMP");
        parsingTable.put("78,44", "<=|EXPSIMP");
        parsingTable.put("78,45", "<>|EXPSIMP");
        parsingTable.put("78,46", "NULL");
        parsingTable.put("78,47", "NULL");
        parsingTable.put("79,24", "TERMO|REPEXP");
        parsingTable.put("79,25", "TERMO|REPEXP");
        parsingTable.put("79,26", "TERMO|REPEXP");
        parsingTable.put("79,30", "+|TERMO|REPEXP");
        parsingTable.put("79,31", "-|TERMO|REPEXP");
        parsingTable.put("79,36", "TERMO|REPEXP");
        parsingTable.put("80,7", "NULL");
        parsingTable.put("80,10", "NULL");
        parsingTable.put("80,14", "NULL");
        parsingTable.put("80,15", "NULL");
        parsingTable.put("80,17", "NULL");
        parsingTable.put("80,19", "NULL");
        parsingTable.put("80,22", "OR|TERMO|REPEXP");
        parsingTable.put("80,28", "NULL");
        parsingTable.put("80,30", "+|TERMO|REPEXP");
        parsingTable.put("80,31", "-|TERMO|REPEXP");
        parsingTable.put("80,35", "NULL");
        parsingTable.put("80,37", "NULL");
        parsingTable.put("80,40", "NULL");
        parsingTable.put("80,41", "NULL");
        parsingTable.put("80,42", "NULL");
        parsingTable.put("80,43", "NULL");
        parsingTable.put("80,44", "NULL");
        parsingTable.put("80,45", "NULL");
        parsingTable.put("80,46", "NULL");
        parsingTable.put("80,47", "NULL");
        parsingTable.put("81,24", "FATOR|REPTERMO");
        parsingTable.put("81,25", "FATOR|REPTERMO");
        parsingTable.put("81,26", "FATOR|REPTERMO");
        parsingTable.put("81,36", "FATOR|REPTERMO");
        parsingTable.put("82,7", "NULL");
        parsingTable.put("82,10", "NULL");
        parsingTable.put("82,14", "NULL");
        parsingTable.put("82,15", "NULL");
        parsingTable.put("82,17", "NULL");
        parsingTable.put("82,19", "NULL");
        parsingTable.put("82,22", "NULL");
        parsingTable.put("82,23", "AND|FATOR|REPTERMO");
        parsingTable.put("82,28", "NULL");
        parsingTable.put("82,30", "NULL");
        parsingTable.put("82,31", "NULL");
        parsingTable.put("82,32", "*|FATOR|REPTERMO");
        parsingTable.put("82,33", "/|FATOR|REPTERMO");
        parsingTable.put("82,35", "NULL");
        parsingTable.put("82,37", "NULL");
        parsingTable.put("82,40", "NULL");
        parsingTable.put("82,41", "NULL");
        parsingTable.put("82,42", "NULL");
        parsingTable.put("82,43", "NULL");
        parsingTable.put("82,44", "NULL");
        parsingTable.put("82,45", "NULL");
        parsingTable.put("82,46", "NULL");
        parsingTable.put("82,47", "NULL");
        parsingTable.put("83,24", "NOT|FATOR");
        parsingTable.put("83,25", "VARIAVEL");
        parsingTable.put("83,26", "INTEIRO");
        parsingTable.put("83,36", "(|EXPRESSAO|)");
        parsingTable.put("84,26", "INTEIRO|RPINTEIRO|:|COMANDO|CONTCASE");
        parsingTable.put("85,7", "NULL");
        parsingTable.put("85,47", ";|CONDCASE");
        parsingTable.put("86,39", "NULL");
        parsingTable.put("86,46", ",|INTEIRO|RPINTEIRO");
    }
}