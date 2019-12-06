package resources;

public enum StudentStatus {
    VERMELHO("RISCO"),
    AMARELO("ALERTA"),
    VERDE("REGULAR");

    private String descricao;

    StudentStatus(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
