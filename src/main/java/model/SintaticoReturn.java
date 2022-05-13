package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SintaticoReturn {
    public Queue<Token> tokens = new LinkedList<>();
    public Queue<NotificacaoConsole> notificacaoConsoles = new LinkedList<>();
}
