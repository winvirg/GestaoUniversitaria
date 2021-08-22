package aluno.base;

import cliente.IRHService.Tipo;

public class STA extends Funcionario{

    private final String cpf;
    private final String nome;
    private final int nivel;
    private double salario;
    private int diaria;
    private final int diariaMax = 1;
    private double lucroParilhado;

    private boolean deuCerto = true;

    @Override
    protected void calcSalario() {
        salario = 1000 + (100 * nivel);
    }

    public STA(String cpf, String nome, int nivel) {
        if(nivel < 1 || nivel > 30)
            deuCerto = false;
        this.cpf = cpf;
        this.nome = nome;
        this.nivel = nivel;
        calcSalario();
    }

    @Override
    public boolean deuCerto() {
        return deuCerto;
    }

    @Override
    public void iniciaMes() {
        diaria = 0;
        lucroParilhado = 0;
    }

    @Override
    public void lucroPartilhado(double value) {
        lucroParilhado = value;
    }

    @Override
    public boolean adicionarDiaria() {
        if(diaria == diariaMax)
            return false;
        diaria++;
        return true;
    }

    @Override
    public Tipo getTipo() {
        return Tipo.STA;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String getCpf() {
        return this.cpf;
    }

    @Override
    public double getSalario() {
        return this.salario + (diaria * 100) + lucroParilhado;
    }

    @Override
    public String toString(){
        return nome;
    }

}