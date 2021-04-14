package ${package};

import com.github.abdonia.domino.exemplos.JogadorMamao;
import com.github.abdonia.domino.log.LoggerDominoEventListener;
import com.github.abdonia.domino.motor.DominoConfig;
import com.github.abdonia.domino.motor.DominoConfigException;
import com.github.abdonia.domino.motor.Jogo;

public class App {

    public static void main( final String[] args )
            throws DominoConfigException{

        final DominoConfig config =
            //cria um builder...
            new DominoConfig.Builder()

            //seta os jogadores da primeira dupla (dupla 0)
            .withJogador("MeuJogador1",new ${classeJogador}(),0,0)
            .withJogador("MeuJogador2",new ${classeJogador}(),0,1)

            //seta os jogadores da segunda dupla (dupla 1)
            .withJogador("Bruno",new JogadorMamao(),1,0)
            .withJogador("Igor",new JogadorMamao(),1,1)

            //seta o lister que loga o jogo no console
            .withEventListener(LoggerDominoEventListener.class)

            //e agora, com tudo setado, cria o DomingoConfig
            .build();

        final Jogo jogo = new Jogo(config);

        jogo.jogar();
    }
}
