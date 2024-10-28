package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entidade.Jogo;

@ManagedBean
@SessionScoped
public class JogoBean {
	
	private List<Jogo> listaDeJogos = new ArrayList<>();
	private Jogo jogo = new Jogo();
	private int contadorId = 1;

	public void adicionarJogo() {
	    // Verifica se o número aposta está fora do intervalo permitido (1 a 5)
	    if (jogo.getNumeroAposta() < 1 || jogo.getNumeroAposta() > 5) {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Resultado:", "Jogo não cadastrado! Informe um valor entre 1 a 5"));
	        jogo = new Jogo();
	        return; // Interrompe o método para evitar o salvamento
	    }

	    // Define o ID e data do jogo
	    jogo.setId(contadorId);
	    jogo.setData(new Date());
	    Random random = new Random(); // Gera o número secreto entre 1 e 5
	    int numeroSecreto = random.nextInt(5) + 1;
	    jogo.setNumeroSecreto(numeroSecreto);

	    // Verifica se o número aposta é igual ao número secreto
	    if (jogo.getNumeroAposta().equals(numeroSecreto)) {
	        jogo.setResultado("Acertou");
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado:", "Acertou"));
	    } else {
	        jogo.setResultado("Não acertou");
	        FacesContext.getCurrentInstance().addMessage(null, 
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Resultado:", "Não acertou"));
	    }

	    // Adiciona o jogo na lista
	    listaDeJogos.add(jogo);
	    contadorId++;
	    jogo = new Jogo(); // Limpa o objeto jogo para um novo cadastro
	}
	
	public void removerJogo(Jogo jogo) { 
		listaDeJogos.remove(jogo);
	}
	
	// Retorna o número total  de elementos na lista
	public int getQuantidadeDeJogos() {
		return listaDeJogos.size();
	}
	
	// Retorna a lista inteira de objetos do tipo "Jogo"
	public List<Jogo> getListaDeJogos() {
		return listaDeJogos;
	}
	
	public void setListaDeJogos(List<Jogo> listaDeJogos) {
		this.listaDeJogos = listaDeJogos;
	}
	
	public Jogo getJogo() {
		return jogo;
	}
	
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
}
