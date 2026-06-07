<img width="1600" style="height:auto; border-radius: 12px;" alt="banner Gostô?" src="../docs/images/gosto-banner.png" />

# Código do projeto

O **Gostô?** é dividido em dois módulos independentes, que se comunicam via API REST autenticada por JWT:

| Módulo | Stack | Descrição |
|---|---|---|
| [`front/`](front) | Astro + Tailwind CSS | Aplicação web — login, cadastro, descoberta de estabelecimentos, avaliações, reservas e perfis |
| [`back/`](back) | Spring Boot + MySQL + JWT | API REST — regras de negócio, persistência e autenticação |

Cada módulo tem seu próprio README com stack detalhada, estrutura de pastas, variáveis de ambiente e instruções de instalação e execução:

- [Código do front-end](front) — [`front/README.md`](front/README.md)
- [Código do back-end](back) — [`back/README.md`](back/README.md)

> Para subir o projeto completo (frontend + backend), siga o passo a passo do [README raiz](../README.md#-instalação-e-execução).
