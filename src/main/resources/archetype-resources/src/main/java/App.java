package ${package};

import com.github.abdonia.domino.exemplos.JogadorMamao;
import com.github.abdonia.domino.log.LoggerDominoEventListener;
import com.github.abdonia.domino.motor.DominoConfig;
import com.github.abdonia.domino.motor.DominoConfigException;
import com.github.abdonia.domino.motor.Jogo;

public class App 
{
    public static void main( final String[] args ) throws DominoConfigException{
        
        DominoConfig config = new DominoConfig();
        
        //dupla 1
        config.setJogador("${classeJogador}1",new ${classeJogador}(),1,1);
        config.setJogador("${classeJogador}2",new ${classeJogador}(),1,2);

        //dupla 2
        config.setJogador("Bruno",new JogadorMamao(),2,1);
        config.setJogador("Igor",new JogadorMamao(),2,2);
        
        config.addEventListener(new LoggerDominoEventListener(System.out));
        
        Jogo jogo = new Jogo(config);
        
        jogo.jogar();
    }
}
