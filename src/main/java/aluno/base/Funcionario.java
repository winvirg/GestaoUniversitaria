package aluno.base;

import cliente.IRHService.Tipo;

public  class Funcionario {

    protected void calcSalario(){}

    public Tipo getTipo(){return Tipo.PROF;}

    public boolean adicionarDiaria(){return false;}

    public void lucroPartilhado(double value){}

    public void iniciaMes(){}

    public boolean deuCerto(){return true;}


    public String getNome() {
        return null;
    }

    public String getCpf() {
        return null;
    }

    public double getSalario() {
        return 0.0;
    }
}