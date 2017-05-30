package ${package};

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Optional;

import com.github.abdonia.domino.Jogada;
import com.github.abdonia.domino.Jogador;
import com.github.abdonia.domino.Lado;
import com.github.abdonia.domino.Mesa;
import com.github.abdonia.domino.Numero;
import com.github.abdonia.domino.Pedra;
import com.github.abdonia.domino.Vontade;

public class ${classeJogador} implements Jogador{

    private Mesa mesa;
    private Collection<Pedra> mao;
    
    private boolean ehPrimeiraPartida;

    /**
     * O Jogador precisa decidir entre as pedras que tem na mão que encaixam na
     * mesa qual vai jogar.
     * @param numeroDaEsquerda O {@link Numero} que está {@link 
     * Mesa#getNumeroEsquerda() de um lado da mesa}.
     * @param numeroDaDireita O {@link Numero} que está {@link 
     * Mesa#getNumeroEsquerda() do outro lado da mesa}.
     * @param pedrasPossiveisDeJogar Subconjunto das pedras que o jogador tem na 
     * mão que podem ser jogadas na mesa no momento.
     * 
     * @return A jogada que o jogador vai fazer agora.
     */
    private Jogada escolheJogada(
            final Numero numeroDaEsquerda, 
            final Numero numeroDaDireita, 
            final Collection<Pedra> pedrasPossiveisDeJogar) {
            
        Pedra pedra = ....; // escolhe uma pedra entre as possiveiss
        Lado lado = //diz se vai ser Lado.Esquerdo e Lado.Direito
                
        return Jogada.jogada(pedra, lado);
    }
    
    /**
     * A dupla deste jogador ganhou a partida anterior e ele é o jogador a 
     * iniciar esta partida. Pode jogar qualquer {@link Pedra} que tenha na mão.  
     * 
     * @return Uma {@link Jogada} pra iniciar a partida.
     */
    private Jogada escolheJogadaInicial() {
        //escolher uma pedra da Collection "mao", e de que Lado vai jogar ela.
        Pedra pedra = ....
        
        return Jogada.jogada(pedra, Lado.ESQUERDO);//lado tanto faz. mesa vazia.
    }

    
    @Override
    public void sentaNaMesa(final Mesa mesa, final int cadeiraQueSentou) {
        this.mesa = mesa;
        ehPrimeiraPartida = true;
    }
    
    @Override
    public void recebeMao(
            final Pedra pedra1, 
            final Pedra pedra2, 
            final Pedra pedra3, 
            final Pedra pedra4, 
            final Pedra pedra5, 
            final Pedra pedra6) {
        
        this.mao = EnumSet.of(pedra1, pedra2, pedra3, pedra4, pedra5, pedra6);
    }

    @Override
    public Jogada joga() {
        
        final Jogada jogada;
        
        if(mesa.taVazia()){
            if(ehPrimeiraPartida){
                //não tem opção.
                jogada = jogaMaiorCarroca();
            } else{
                //pode escolher uma pedra pra iniciar
                jogada = escolheJogadaInicial();
            }
            
        } else {
            //esse código pode ser melhorado usando streams....
            final Numero numeroDaEsquerda = mesa.getNumeroEsquerda();
            final Numero numeroDaDireita = mesa.getNumeroDireita();
            
            final Collection<Pedra> pedrasPossiveisDeJogar = 
                    filtraPedrasPossiveisDeJogar(numeroDaEsquerda, numeroDaDireita);
            
            if(pedrasPossiveisDeJogar.isEmpty()){
                jogada = Jogada.TOQUE;
            } else if(pedrasPossiveisDeJogar.size() == 1){
                jogada = 
                    fazJogaComUnicaPedraJogavel(
                        numeroDaEsquerda, 
                        numeroDaDireita, 
                        pedrasPossiveisDeJogar.iterator().next());
                
            } else {
                jogada = escolheJogada(
                            numeroDaEsquerda, 
                            numeroDaDireita, 
                            pedrasPossiveisDeJogar);
            }
        }
        
        /*
          removendo da mão a pedra que vou jogar, pra não correr o risco de 
          querer jogar ela de novo...
        */
        if(jogada != Jogada.TOQUE){
            this.mao.remove(jogada.getPedra());
        }
        
        return jogada;
    }

    private Jogada fazJogaComUnicaPedraJogavel(
            final Numero numeroDaEsquerda, 
            final Numero numeroDaDireita, 
            final Pedra pedra) {
        
        final Jogada jogada;
        final boolean pedraCabeNaEsquerda = pedra.temNumero(numeroDaEsquerda);
        final boolean pedraCabeNaDireita = pedra.temNumero(numeroDaDireita);

        if(pedraCabeNaEsquerda && pedraCabeNaDireita){
            jogada = escolheJogada(
                    numeroDaEsquerda,
                    numeroDaDireita,
                    EnumSet.of(pedra));
        } else {
            final Lado lado = pedraCabeNaDireita ? Lado.DIREITO : Lado.ESQUERDO;
            jogada = Jogada.jogada(pedra, lado);
        }
        return jogada;
    }

    private Collection<Pedra> filtraPedrasPossiveisDeJogar(
            final Numero numeroDaEsquerda, 
            final Numero numeroDaDireita) {
        
        final Collection<Pedra> pedrasPossiveisDeJogar = new ArrayList<>();
        for (final Pedra pedra : this.mao) {
            if(pedra.temNumero(numeroDaEsquerda)
                || pedra.temNumero(numeroDaDireita)){
                
                pedrasPossiveisDeJogar.add(pedra);
                
            }
        }
        return pedrasPossiveisDeJogar;
    }

    
    /**
     * Diz sempre que {@link Vontade#QUERO quer começar}.
     * @return {@link Vontade#QUERO}.
     */
    @Override
    public Vontade vontadeDeComecar() {
        this.ehPrimeiraPartida = false;
        return Vontade.QUERO;
    }

    /**
     * Retorna uma {@link Jogada jogada} da maior {@link Pedra#isCarroca() 
     * carroça} que este jogador tem na {@link #mao mão}.
     * 
     * @return a maior {@link Pedra#isCarroca() carroça} que este jogador tem na
     * {@link #mao mão}.
     * 
     * @throws IllegalStateException caso não tenha nenhuma carroça na mão.
     */
    private Jogada jogaMaiorCarroca() {
        
        final Jogada jogadaDaMaiorCarroca;
        
        final Optional<Pedra> optional = 
            this.mao.parallelStream()
                    .filter(Pedra::isCarroca)
                    .max(Pedra::compareTo);
        
        if(optional.isPresent()){
            final Pedra maiorCarroca = optional.get();
            final Lado lado = Lado.ESQUERDO; //qualquer um....
            
            jogadaDaMaiorCarroca = Jogada.jogada(maiorCarroca,lado);
        } else {
            throw new IllegalStateException("não tenho carroças");
        }
        return jogadaDaMaiorCarroca;
    }
}
