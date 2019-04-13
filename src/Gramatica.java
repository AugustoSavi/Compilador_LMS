
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Gramatica {

    public static final Map<String, Integer> TERMINAIS_E_NAO_TERMINAIS = new HashMap<String, Integer>();
    public static final Map<String, String> GRAMATICA = new HashMap<String, String>();

    static {
        //palavras reservadas, que podem ser utilizadas (terminais)
        // Código < 52
        TERMINAIS_E_NAO_TERMINAIS.put("PROGRAM", 1);
        TERMINAIS_E_NAO_TERMINAIS.put("VAR", 4);
        TERMINAIS_E_NAO_TERMINAIS.put("BEGIN", 6);
        TERMINAIS_E_NAO_TERMINAIS.put("END", 7);
        TERMINAIS_E_NAO_TERMINAIS.put("INTEGER", 8);
        TERMINAIS_E_NAO_TERMINAIS.put("REPEAT", 18);
        TERMINAIS_E_NAO_TERMINAIS.put("UNTIL", 19);
        TERMINAIS_E_NAO_TERMINAIS.put("WRITELN", 21);
        TERMINAIS_E_NAO_TERMINAIS.put("IDENTIFICADOR", 25);
        TERMINAIS_E_NAO_TERMINAIS.put("INTEIRO", 26);
        TERMINAIS_E_NAO_TERMINAIS.put("(", 36);
        TERMINAIS_E_NAO_TERMINAIS.put(")", 37);
        TERMINAIS_E_NAO_TERMINAIS.put(":=", 38);
        TERMINAIS_E_NAO_TERMINAIS.put(":", 39);
        TERMINAIS_E_NAO_TERMINAIS.put(">", 41);
        TERMINAIS_E_NAO_TERMINAIS.put(",", 46);
        TERMINAIS_E_NAO_TERMINAIS.put(";", 47);
        TERMINAIS_E_NAO_TERMINAIS.put(".", 49);

        //simbolos não terminais (que possuem deriações em outros blocos)
        TERMINAIS_E_NAO_TERMINAIS.put("PROGRAMA", 52);
        TERMINAIS_E_NAO_TERMINAIS.put("BLOCO", 53);
        TERMINAIS_E_NAO_TERMINAIS.put("DCLROT", 54);
        TERMINAIS_E_NAO_TERMINAIS.put("LID", 55);
        TERMINAIS_E_NAO_TERMINAIS.put("REPIDENT", 56);
        TERMINAIS_E_NAO_TERMINAIS.put("DCLCONST", 57);
        TERMINAIS_E_NAO_TERMINAIS.put("DCLVAR", 59);
        TERMINAIS_E_NAO_TERMINAIS.put("LDVAR", 60);
        TERMINAIS_E_NAO_TERMINAIS.put("TIPO", 61);
        TERMINAIS_E_NAO_TERMINAIS.put("DCLPROC", 62);
        TERMINAIS_E_NAO_TERMINAIS.put("CORPO", 64);
        TERMINAIS_E_NAO_TERMINAIS.put("REPCOMANDO", 65);
        TERMINAIS_E_NAO_TERMINAIS.put("COMANDO", 66);
        TERMINAIS_E_NAO_TERMINAIS.put("RCOMID", 67);
        TERMINAIS_E_NAO_TERMINAIS.put("RVAR", 68);
        TERMINAIS_E_NAO_TERMINAIS.put("VARIAVEL", 72);
        TERMINAIS_E_NAO_TERMINAIS.put("VARIAVEL1", 73);
        TERMINAIS_E_NAO_TERMINAIS.put("ITEMSAIDA", 75);
        TERMINAIS_E_NAO_TERMINAIS.put("REPITEM", 76);
        TERMINAIS_E_NAO_TERMINAIS.put("EXPRESSAO", 77);
        TERMINAIS_E_NAO_TERMINAIS.put("REPEXPSIMP", 78);
        TERMINAIS_E_NAO_TERMINAIS.put("EXPSIMP", 79);
        TERMINAIS_E_NAO_TERMINAIS.put("REPEXP", 80);
        TERMINAIS_E_NAO_TERMINAIS.put("REPTERMO", 82);
        TERMINAIS_E_NAO_TERMINAIS.put("TERMO", 81);
        TERMINAIS_E_NAO_TERMINAIS.put("FATOR", 83);

        //montagem da gramatica / tabela de parsing
        GRAMATICA.put("52,1", "PROGRAM|IDENTIFICADOR|;|BLOCO|.");
        GRAMATICA.put("53,4", "DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO");
        GRAMATICA.put("59,4", "VAR|LID|:|TIPO|;|LDVAR");
        GRAMATICA.put("55,25", "IDENTIFICADOR|REPIDENT");
        GRAMATICA.put("56,46", ",|IDENTIFICADOR|REPIDENT");
        GRAMATICA.put("61,8", "INTEGER");
        GRAMATICA.put("64,6", "BEGIN|COMANDO|REPCOMANDO|END");
        GRAMATICA.put("66,25", "IDENTIFICADOR|RCOMID");
        GRAMATICA.put("67,38", "RVAR|:=|EXPRESSAO");
        GRAMATICA.put("77,26", "EXPSIMP|REPEXPSIMP");
        GRAMATICA.put("79,26", "TERMO|REPEXP");
        GRAMATICA.put("81,26", "FATOR|REPTERMO");
        GRAMATICA.put("83,26", "INTEIRO");
        GRAMATICA.put("65,47", ";|COMANDO|REPCOMANDO");
        GRAMATICA.put("66,18", "REPEAT|COMANDO|UNTIL|EXPRESSAO");
        GRAMATICA.put("66,6", "CORPO");
        GRAMATICA.put("66,21", "WRITELN|(|ITEMSAIDA|REPITEM|)");
        GRAMATICA.put("75,25", "EXPRESSAO");
        GRAMATICA.put("77,25", "EXPSIMP|REPEXPSIMP");
        GRAMATICA.put("79,25", "TERMO|REPEXP");
        GRAMATICA.put("81,25", "FATOR|REPTERMO");
        GRAMATICA.put("83,25", "|VARIAVEL");
        GRAMATICA.put("72,25", "IDENTIFICADOR|VARIAVEL1");
        GRAMATICA.put("76,46", ",|ITEMSAIDA|REPITEM");
        GRAMATICA.put("78,41", ">|EXPSIMP");
    }

    public static String getTerminalENaoTerminalByCodigo(Integer codigo) {
        if (codigo == null) {
            return null;
        }
        for (Map.Entry<String, Integer> entry : TERMINAIS_E_NAO_TERMINAIS.entrySet()) {
            if (entry.getValue().compareTo(codigo) == 0) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Este mtodo retorna uma lista com os cdigos equivalentes as palavras que
     * foram derivadas
     */
    public static Integer[] geraDadosCruzamentoTabParsingToken(String str) {
        if ((str != null) && (str.length() != 0) && !"null".equals(str)) {
            StringTokenizer strTokenizer = new StringTokenizer(str, "|");
            Integer[] dados = new Integer[strTokenizer.countTokens()];
            int i = 0;

            while (strTokenizer.hasMoreTokens()) {
                String palavra = strTokenizer.nextToken();
                dados[i++] = TERMINAIS_E_NAO_TERMINAIS.get(palavra);
            }

            return dados;
        }

        return null;
    }

}
