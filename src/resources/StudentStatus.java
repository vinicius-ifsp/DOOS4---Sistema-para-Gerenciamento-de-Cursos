package resources;

public enum StudentStatus {
    VERMELHO("RISCO"),
    AMARELO("ATRASADO"),
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
