/*
 * Disciplina Programação Orientada a Objetos
 * Autores Luís Tendai RA 204585
 * João Victor Tiodoro RA
 * Mateus Alves RA 201980
 * Atividade Final 3EC
 * Data 13/06/2024
 */


import java.util.LinkedList;

public class ControleDePilha {
    private LinkedList<Pilha> pilhas;
    private Pilha pilhaComSenhaChamada;
    private int contadorChamadasPrioritarias;

    public ControleDePilha() {
        pilhas = new LinkedList<>();
        pilhas.add(new Pilha(TipoLista.URGENTE));
        pilhas.add(new Pilha(TipoLista.VIP));
        pilhas.add(new Pilha(TipoLista.PREFERENCIAL));
        pilhas.add(new Pilha(TipoLista.IDOSO80));
        pilhas.add(new Pilha(TipoLista.IDOSO));
        pilhas.add(new Pilha(TipoLista.NORMAL));
        pilhaComSenhaChamada = null;
        contadorChamadasPrioritarias = 0;
    }

    private Pilha obterPilha(TipoLista tipoLista) {
        for (Pilha pilha : pilhas) {
            if (pilha.getTipoLista() == tipoLista) {
                return pilha;
            }
        }
        return null;
    }

    public String inserir(TipoLista tipoLista) {
        Pilha pilha = obterPilha(tipoLista);
        return pilha.inserir();
    }

    public void remover() {
        for (Pilha pilha : pilhas) {
            if (!pilha.isEmpty()) {
                pilha.remover();
                return;
            }
        }
    }

    public String chamar() {
        if (pilhaComSenhaChamada != null) {
            return "Já existe uma senha chamada que precisa ser atendida.";
        }

        if (contadorChamadasPrioritarias >= 3) {
            Pilha pilhaNormal = obterPilha(TipoLista.NORMAL);
            if (!pilhaNormal.isEmpty()) {
                contadorChamadasPrioritarias = 0;
                String resultado = pilhaNormal.chamar();
                pilhaComSenhaChamada = pilhaNormal;
                return resultado;
            }
        }

        for (Pilha pilha : pilhas) {
            if (!pilha.isEmpty()) {
                String resultado = pilha.chamar();
                if (!resultado.equals("Já existe uma senha chamada que precisa ser atendida.")) {
                    pilhaComSenhaChamada = pilha;
                    contadorChamadasPrioritarias++;
                    pilha.incrementarVezesChamada();
                    return resultado;
                }
            }
        }
        return "";
    }

    public String atender() {
        if (pilhaComSenhaChamada == null) {
            return "Nenhuma senha chamada para ser atendida.";
        }
        String resultado = pilhaComSenhaChamada.atender();
        if (!resultado.equals("Nenhuma senha chamada para ser atendida.")) {
            pilhaComSenhaChamada = null;
        }
        return resultado;
    }

    public String listar() {
        StringBuilder lista = new StringBuilder();
        for (Pilha pilha : pilhas) {
            if (pilha.listar() != null) {
                lista.append("Senhas ").append(pilha.getTipoLista()).append(":\n");
                lista.append(pilha.listar()).append("\n");
            }

        }
        if (lista.isEmpty()) { return "Nao há senhas para serem listadas!";}
        return lista.toString();
    }
}
