/**
 * @author Willian Silva
 *
 */

public class Conta {
    private final int numero;
    private double saldo;
    private double limit;
    private final double[] extrato;
    private int operacoes;

    public Conta(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
        operacoes = 0;
        extrato = new double[10];
        limit = 100;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo + limit;
    }

    public double getLimite() {
        return limit;
    }

    public boolean sacar(double valor) {
        if (valor < 0 || valor > getSaldo())
            return false;

        if (valor > saldo) {
            limit -= (valor - saldo);
            saldo = 0;
        } else {
            saldo -= valor;
        }

        extrato[operacoes++] = -valor;

        return true;
    }

    public boolean depositar(double valor) {
        if (valor < 0)
            return false;

        if (limit < 100) {
            if (valor > 100 - limit) {
                saldo += (valor - (100 - limit));
                limit = 100;
            } else {
                limit += valor;
            }
        } else {
            saldo += valor;
        }

        extrato[operacoes++] = valor;

        return true;
    }

    public boolean transferir(Conta destino, double valor) {
        if (valor < 0 || valor > getSaldo())
            return false;

        destino.depositar(valor);

        if (valor > saldo) {
            limit -= (valor - saldo);
            saldo = 0;
        } else {
            saldo -= valor;
        }

        extrato[operacoes++] = -valor;

        return true;
    }

    public double[] verExtrato() {
        return extrato;
    }

    @Override
    public String toString() {
        return "Conta{" + "numero=" + numero + ", saldo=" + saldo + ", limite=" + limit + "}";
    }
}