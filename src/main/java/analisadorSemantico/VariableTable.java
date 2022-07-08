package analisadorSemantico;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
public class VariableTable {
    private final Map<String, Variable> variableTable = new HashMap<>();
    private static final String ERRO = "ERRO";
    private static final String SUCESSO = "SUCESSO";

    public String add(Variable variable, Integer level) {
        if (containsVariable(variable.getName(), level)) {
            return ERRO;
        } else {
            variableTable.put(variable.getName() + "_" + level, variable);
            return SUCESSO;
        }

    }

    public Variable getVariable(String variable, Integer level) {
        return variableTable.get(variable + "_" + level);
    }

    public void delete(String key, Integer level) {
        variableTable.remove(key + "_" + level);
    }

    public void deleteByLevel(Integer level) {
        ArrayList<String> aux = new ArrayList<>();

        for (Map.Entry<String, Variable> variable : variableTable.entrySet()) {
            if (variable.getValue().getLevel().equals(level)) {
                aux.add(variable.getKey());
            }
        }

        aux.forEach(variableTable.keySet()::remove);
    }

    public boolean containsVariable(String key, Integer level) {
        return variableTable.containsKey(key + "_" + level);
    }
}
