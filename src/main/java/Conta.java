/**
 * @author Willian Silva
 *
 */

import java.util.List;
import java.util.ArrayList;

public class Conta {
    private final int numero;
    private double saldo;
    private double limit;
    private final List<Double> extrato;
    private int operates;

    public Conta(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
        operates = 0;
        extrato = new ArrayList<>(10);
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
        if (valor > 0 && valor <= getSaldo()) {

            reajusteSaldo(valor);

            extrato.add(operates++, -valor);

            return true;
        }
        return false;
    }

    private void reajusteSaldo(double valor) {
        if (valor > saldo) {
            limit -= (valor - saldo);
            saldo = 0;
        } else {
            saldo -= valor;
        }
    }

    public boolean depositar(double valor) {
        if (valor > 0) {
            if (limit < 100) {
                final double novoLimit = limit + valor;

                if (novoLimit > 100) {
                    saldo += novoLimit - 100;
                    limit = 100;
                } else {
                    limit = novoLimit;
                }
            } else {
                saldo += valor;
            }

            extrato.add(operates++, valor);

            return true;
        }
        return false;
    }

    public boolean transferir(Conta destino, double valor) {
        if (valor > 0 && valor <= getSaldo()) {
            destino.depositar(valor);

            reajusteSaldo(valor);

            extrato.add(operates++, -valor);

            return true;
        }
        return false;
    }

    public double[] verExtrato() {
        return extrato.stream().mapToDouble(Double::doubleValue).toArray();
    }

    @Override
    public String toString() {
        return "Conta{" + "numero=" + numero + ", saldo=" + saldo + ", limite=" + limit + "}";
    }
}