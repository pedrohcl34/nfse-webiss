# Simples API para emissão de NFSE (ABRASF/WebISS)

Seguindo a padronização que tem acontecido em diversos municípios quanto à emissão de Nota Fiscal de Serviços, essa a api trata de forma simplificada a emissão, busca e cancelamento de nfse.

## Configuração
Esta biblioteca utiliza Java 8.

Para configurar basta compilar a api utilizando o maven

```groovy
maven install
```

Logo em seguida adicionar a dependência ao arquivo pom

```xml
   <dependency>
     <groupId>br.com.webiss</groupId>
     <artifactId>nfs-webiss</artifactId>
     <version>0.0.1</version>
   </dependency>
   ```
## Pré-requisitos

É necessário ter um certificado digital para quem irá emitir a nota e estar cadastro no sistema webiss de seu município.

A documentação para o sistema webiss pode ser encontrada no endereço eletrônico: http://www.abrasf.org.br/paginas_multiplas_detalhes.php?cod_pagina=1&titulo=TEMAS%20T%C9CNICOS&data=nao
