package models;

public class StudentClass {
    private String nome, codigo, prontuario;
    private int modulo, atraso, ppc;

    public StudentClass() {
    }

    public StudentClass(String nome, String codigo, String prontuario, int modulo) {
        this.nome = nome;
        this.codigo = codigo;
        this.prontuario = prontuario;
        this.modulo = modulo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public int getModulo() {
        return modulo;
    }

    public void setModulo(int modulo) {
        this.modulo = modulo;
    }

    public int getAtraso() {
        return atraso;
    }

    public void setAtraso(int atraso) {
        this.atraso = atraso;
    }

    public int getPpc() {
        return ppc;
    }

    public void setPpc(int ppc) {
        this.ppc = ppc;
    }
}