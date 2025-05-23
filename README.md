# Conta Bancária Simples

![GitHub repo size](https://img.shields.io/github/repo-size/WillianSilva51/ContaBancariaSimples?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/WillianSilva51/ContaBancariaSimples?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/WillianSilva51/ContaBancariaSimples?style=for-the-badge)

> A ideia desta atividade é que você seja capaz de implementar as funcionalidades
> básicas de uma conta bancária.

<figure>
  <img src="contabancaria.jpg" alt="ilustração de operações típicas de contas bancárias">
  <figcaption style="text-align: center"><a href="https://www.freevector.com/free-iconic-atm-vectors-25886">Image obtida em freevector.com</a></figcaption>
</figure>

## Índice
- [Requisitos](#requisitos)
- [Diagrama](#diagrama)
- [Exemplo de execução](#exemplo-de-execução)


## Requisitos

- Inicialização
  - Os valores iniciais do limite e do extrato devem ser respectivamente, 100 e uma lista vazia
  - O valor do saldo deve ser informando no momento da criação da conta
  - A conta suporta no máximo 10 operações
- Saldo
  - O saldo da conta é igual ao saldo mais o limite
- Saques
  - Não deve ser possível sacar um valor negativo
  - Não deve ser possível sacar um valor que exceda o limite da conta (saldo + limite)
- Depósitos
  - Não deve ser possível depositar um valor negativo
- Transferência
  - o Usuário deve informar a conta de destino
  - O valor da transferência não pode ser negativo
- Extrato
  - Na lista de operações saques e transferências devem iniciar com o sinal de subtração (-)
  - Apenas os depósitos devem apresentar o sinal de adição (+)
  

## Diagrama

```mermaid
classDiagram
direction TB
    class Conta {
	    - int numero
	    - double saldo
	    - double limite
	    - List~Double~ extrato
	    - int operacoesRealizadas

	    + Conta(int numero, double saldoInicial)
	    + double getSaldo()
	    + boolean sacar(double valor)
	    + boolean depositar(double valor)
	    + boolean transferir(Conta destino, double valor)
	    + double[] verExtrato()
	    + String toString()
    }


```

## Exemplo de execução

```java
public class Runner {

    public static void main(final String[] args) {

        Conta minhaConta = new Conta(1001, 2000);
        System.out.println(minhaConta); //Conta{numero=1001, saldo=2000.0, limite=100.0}

        Conta destino = new Conta(10, 0);

        minhaConta.sacar(200);
        System.out.println(minhaConta); //Conta{numero=1001, saldo=1800.0, limite=100.0}

        if(!minhaConta.sacar(10000)){
            System.out.println("Saldo insuficiente"); //Saldo insuficiente
        }

        minhaConta.depositar(500);
        System.out.println(minhaConta); //Conta{numero=1001, saldo=2300.0, limite=100.0}

        minhaConta.transferir(destino, 400);
        System.out.println(minhaConta); //Conta{numero=1001, saldo=1900.0, limite=100.0}

        if(!minhaConta.transferir(destino, 4000000)) {
            System.out.println("Saldo insuficiente"); //Saldo insuficiente
        }

        minhaConta.sacar(1950);
        System.out.println(minhaConta); //Conta{numero=1001, saldo=0.0, limite=50.0}

        minhaConta.depositar(50);
        System.out.println(minhaConta); //Conta{numero=1001, saldo=0.0, limite=100.0}

        double[] extrato = minhaConta.verExtrato();
        for (double v : extrato) {
            System.out.println(v); // -200.0 500.0 -400.0 -1950.0 50.0
        }
    }
}
```
