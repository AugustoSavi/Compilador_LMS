package analisadorSemantico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Variable {

    private String name;
    private String type;
    private String category;
    private Integer level;
    private ArrayList<String> parameters = new ArrayList<>();

    public Variable(String name, String type, Integer level, String category) {
        this.name = name;
        this.level = level;
        this.type = type;
        this.category = category;
    }

    public void addParam(String param) {
        this.parameters.add(param);
    }
}
