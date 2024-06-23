/*
 * Disciplina Programação Orientada a Objetos
 * Autores Luís Tendai RA 204585
 * João Victor Tiodoro RA
 * Mateus Alves RA 201980
 * Atividade Final 3EC
 * Data 13/06/2024
 */

import java.util.LinkedList;

public class Pilha extends EstrategiaLIFO {
    private LinkedList<Senha> pilha;
    private TipoLista tipoLista;
    private Senha senhaChamada;
    private int vezesChamada;

    public Pilha(TipoLista tipoLista) {
        this.tipoLista = tipoLista;
        this.pilha = new LinkedList<>();
        this.senhaChamada = null;
        this.vezesChamada = 0;
    }

    public TipoLista getTipoLista() {
        return tipoLista;
    }

    public boolean isEmpty() {
        return pilha.isEmpty();
    }

    public int getVezesChamada() {
        return vezesChamada;
    }

    public void incrementarVezesChamada() {
        vezesChamada++;
    }

    @Override
    public String inserir() {
        Senha senha = new Senha();
        senha.gerarSenha();
        pilha.push(senha);
        return "Senha " + senha.retornarSenha() + " (" + tipoLista + ") inserida com sucesso!";
    }

    @Override
    public void remover() {
        if (!pilha.isEmpty()) {
            pilha.pop();
        }
    }

    @Override
    public String chamar() {
        if (senhaChamada != null) {
            return "Já existe uma senha chamada que precisa ser atendida.";
        }
        if (!pilha.isEmpty()) {
            senhaChamada = pilha.pop();
            return "Senha chamada: " + tipoLista + "-" + senhaChamada.retornarSenha() + "*";
        } else {
            return "A pilha está vazia.";
        }
    }

    @Override
    public String atender() {
        if (senhaChamada != null) {
            String resultado = "Senha " + senhaChamada.retornarSenha() + " (" + tipoLista + ") foi atendida.";
            senhaChamada = null;
            return resultado;
        } else {
            return "Nenhuma senha chamada para ser atendida.";
        }
    }

    @Override
    public String listar() {
        if (pilha.isEmpty()) {
            return null;
        } else {
            StringBuilder lista = new StringBuilder();
            for (Senha senha : pilha) {
                lista.append(senha.retornarSenha()).append("\n");
            }
            return lista.toString();
        }
    }
}
