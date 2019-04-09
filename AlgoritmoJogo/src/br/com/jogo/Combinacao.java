package br.com.jogo;

public class Combinacao {
	
	private Long r ;
    private Integer[] entrada ;
    private long MAX ;
    private long N ;
 
    /** se r e' zero entao iremos fazer todas
     * as combinacoes (com qualquer quantidade
     * de elementos).
     */
    public Combinacao(Integer[] entrada, Long r) {
        this.r = r ;
        this.entrada = entrada ;
        this.MAX = ~(1 << entrada.length) ;
        this.N = 1;
    }
 
    /** Retorna true quando ha pelo menos
     * uma combinacao disponivel.
     */
    public boolean hasNext() {
        if ( r != 0 ) {
            while ( ((this.N & this.MAX) != 0) && (countbits() != r) ) N+=1 ;
        }
 
        return (this.N & this.MAX) != 0;
    }
 
    /** Retorna a quantidade de bits ativos (= 1)
     * de N.
     */
    private long countbits() {
        long i;
        long c;
 
        i = 1;
        c = 0;
        while ( (this.MAX & i) != 0 ) {
            if ( (this.N & i) != 0) {
                c++;
            }
            i = i << 1 ;
        }
 
        return c ;
    }
 
    /** Util para obter o tamanho da saida. Esse
     * tamanho e' o numero de posicoes do vetor
     * retornado por next.
     */
    public Long getSaidaLength() {
        if (r != 0) {
            return r;
        }
 
        return this.countbits();
    }
 
    /** Retorna uma combinacao.
     *
     * ATENCAO: Sempre use next() quando se
     * tem certeza que ha uma combinacao
     * disponivel. Ou seja, sempre use next()
     * quando hasNext() retornar true.
     */
    public Integer[] next() {
        int saida_index, entrada_index, i;
 
        Integer[] saida = new Integer[Integer.parseInt(this.getSaidaLength().toString())];
 
        entrada_index = 0;
        saida_index = 0;
        i = 1;
 
        while ((this.MAX & i) != 0) {
            if ((this.N & i) != 0) {
                saida[saida_index] = entrada[entrada_index];
                saida_index += 1;
            }
            entrada_index += 1;
            i = i << 1;
        }
 
        N += 1;
 
        return saida;
    }
    
}
