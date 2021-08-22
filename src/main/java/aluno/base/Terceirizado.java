package aluno.base;

import cliente.IRHService.Tipo;

public class Terceirizado extends Funcionario{

    private final String cpf;
    private final String nome;
    private final boolean insalubre;
    private double salario;
    private double lucroParilhado;

    @Override
    protected void calcSalario() {
        if(insalubre)
            salario = 1500;
        else
            salario = 1000;
    }

    public Terceirizado(String cpf, String nome, boolean insalubre) {
        this.nome = nome;
        this.cpf = cpf;
        this.insalubre = insalubre;
        calcSalario();
    }

    @Override
    public boolean deuCerto() {
        return super.deuCerto();
    }

    @Override
    public void iniciaMes() {
        lucroParilhado = 0;
    }

    @Override
    public void lucroPartilhado(double value) {
        lucroParilhado = value;
    }

    @Override
    public boolean adicionarDiaria() {
        return super.adicionarDiaria();
    }

    @Override
    public Tipo getTipo() {
        return Tipo.TERC;
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
        return this.salario + lucroParilhado;
    }

    @Override
    public String toString(){
        return nome;
    }

}