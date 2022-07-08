package analisadorSemantico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Token;

import java.util.Stack;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnalisadorSemantico {
    private VariableTable variableTable = new VariableTable();
    private Stack<Token> identifierStack = new Stack<>();
    private Stack<String> expressionsTypes = new Stack<>();
    private String lastProcedure = "";
    private String type;
    private String category;
    private Integer level = 0;

    // flags
    private Boolean isStating = true;
    private Boolean isCallingProcedure = false;
    private Boolean isCommand = false;
    private Boolean isExpression = false;
    private Boolean isDefaultParameter = false;

    // Strings
    private static final String CONST = "CONST";
    private static final String INTEGER = "INTEGER";
    private static final String STRING_VAZIA = "";
    private static final String ERRO = "ERRO";
    private static final int ZERO = 0;

    public String analyze() {
        String response = STRING_VAZIA;

        if ((!identifierStack.isEmpty()) && isStating) {
            if (!category.isEmpty() && category.equals(CONST)) {
                type = INTEGER;
            }
            response = addOnVariableTable();
        }

        return response;
    }

    private String addOnVariableTable() {
        String resp = STRING_VAZIA;

        while (!identifierStack.isEmpty()) {
            Token identifier = identifierStack.pop();
            String response = variableTable.add(formatVariable(identifier), level);

            Variable temp = formatVariable(identifier);

            if (isDefaultParameter) {
                Variable variable = variableTable.getVariable(lastProcedure, ZERO);
                variable.addParam(formatVariable(identifier).getType());
            }

            System.out.println(temp.getCategory() + ", " + temp.getName() + ", " + temp.getType() + ", " + temp.getLevel());

            if (response.equalsIgnoreCase(ERRO)) {
                resp = "identifier already declared";
                break;
            }
        }
        return resp;
    }

    public void clearParameters() {
        this.identifierStack.clear();
        type = null;
        category = null;
    }

    public String searchOnIdentifierTable(String name) {
        if (variableTable.containsVariable(name, level)) {
            if (isExpression) {
                Variable variable = variableTable.getVariable(name, level);
                expressionsTypes.add(variable.getType());
            }

            if (isCallingProcedure) {
                Variable variable = variableTable.getVariable(name, level);
                Variable last = variableTable.getVariable(lastProcedure, ZERO);

                if ((last.getParameters()).contains(variable.getType())) {
                    return STRING_VAZIA;
                } else {
                    return "type of parameter incorrect";
                }
            }

            return STRING_VAZIA;
        } else {
            return "Identifier was not declared";
        }
    }

    public String checkExpressionTypes() {
        String response = STRING_VAZIA;
        if (isExpression) {
            while (!expressionsTypes.isEmpty()) {
                String temp = expressionsTypes.pop();
                if (!expressionsTypes.isEmpty()) {
                    String temp2 = expressionsTypes.pop();
                    if (!temp.equals(temp2)) {
                        response = "expression with different types";
                        break;
                    }
                }
            }
        }
        return response;
    }

    public void deleteAllByLevel(Integer level) {
        variableTable.deleteByLevel(level);
    }

    private Variable formatVariable(Token token) {
        return new Variable(token.getPalavra(), this.type, this.level, this.category);
    }

    public void pushOnIdentifierStack(Token identifier) {
        this.identifierStack.add(identifier);
    }
}
