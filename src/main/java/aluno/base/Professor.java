package aluno.base;

import cliente.IRHService.Tipo;

public class Professor extends Funcionario {

    private final String cpf;
    private final String nome;
    private final char classe;
    private double salario;
    private int diaria;
    private final int diariaMax = 3;
    private double lucroParilhado;

    private boolean deuCerto = true;

    @Override
    protected void calcSalario(){
        switch (classe){
            case 'A':
                salario = 3000;
                break;
            case 'B':
                salario = 5000;
                break;
            case 'C':
                salario = 7000;
                break;
            case 'D':
                salario = 9000;
                break;
            case 'E':
                salario = 11000;
                break;
        }
    }

    public Professor(String cpf, String nome, char classe) {
        if(classe != 'A' && classe != 'B' && classe != 'C' && classe != 'D' && classe != 'E')
            this.deuCerto = false;
        this.cpf = cpf;
        this.nome = nome;
        this.classe = classe;
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
    public boolean adicionarDiaria(){
        if(diaria == diariaMax)
            return false;
        diaria++;
        return true;
    }

    @Override
    public Tipo getTipo() {
        return Tipo.PROF;
    }

    @Override
    public String getNome() {
        if(this.nome.isEmpty())
            return super.getNome();
        return this.nome;
    }

    @Override
    public String getCpf() {
        if(this.cpf.isEmpty())
            return super.getCpf();
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