
# 🛒 Sistema de Controle de Pedidos - E-commerce

Desenvolvido por **Igor Gabriel**, **Mateus Tomaz Siqueira** e **Victor Chicas**

Este projeto é uma **API desenvolvida com Spring Boot** para controle de pedidos de um e-commerce.  
O sistema permite acompanhar o **ciclo de vida de um pedido** (desde a criação até o envio ou cancelamento), **calcular o valor do frete** com diferentes estratégias de envio e realizar **integrações com ferramentas como Swagger ou Postman**.

---

## 🚀 Funcionalidades

- Criar e consultar pedidos;
- Calcular valor do frete com base na modalidade de envio;
- Gerenciar o fluxo de status dos pedidos:
  - **Aguardando Pagamento**
  - **Pago**
  - **Enviado**
  - **Cancelado**
- Persistência dos dados em banco de dados;
- APIs documentadas e testáveis via **Swagger/Postman**.

---

## 🧠 Padrões de Projeto Utilizados

### 🧩 1. Padrão **State**

O **ciclo de vida de um pedido** é gerenciado utilizando o padrão de projeto **State**, o que facilita o controle das regras específicas para cada status do pedido.

#### 📦 Interface:

```java
public interface State {
    void sucessoAoPagar();
    void cancelarPedido();
    void despacharPedido();
}
```

#### 🧱 Implementações:

- **AguardandoPagamentoState**
- **PagoState**
- **EnviadoState**
- **CanceladoState**

Cada estado define de forma isolada **quais ações são válidas**. Por exemplo:

- Um pedido **aguardando pagamento** pode ser **pago ou cancelado**.
- Um pedido **pago** pode ser **enviado**, mas **não pode ser cancelado**.
- Um pedido **cancelado** **não permite mais alterações**.

#### ✅ Vantagens:

- Organiza a lógica de negócio por **estado**, evitando **ifs** ou **switch** aninhados;
- Facilita a manutenção e adição de novos estados;
- Encapsula o comportamento em cada etapa do ciclo de vida do pedido.

---

### 📦 2. Padrão **Strategy**

O **cálculo de frete** é implementado com o padrão **Strategy**, permitindo **trocar dinamicamente o algoritmo de cálculo de frete** com base no tipo selecionado pelo cliente.

#### 📦 Interface:

```java
public interface FreteStrategy {
    BigDecimal calcularFrete(BigDecimal valorPedido);
    String getDescricao();
}
```

#### ✈ Implementações:

- **FreteTerrestre** (5% do valor do pedido);
- **FreteAereo** (10% do valor do pedido);

A escolha da estratégia é feita por uma **fábrica** (`FreteStrategyFactory`), baseada no tipo de frete (**enum TipoFrete**).

#### ✅ Vantagens:

- Facilita a **adição de novos tipos de frete** (ex: marítimo, expresso);
- Evita **acoplamento entre a lógica do pedido e o cálculo do frete**;
- Promove **reuso** e **responsabilidade única** para cada regra de frete.

---
