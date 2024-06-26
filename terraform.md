# Lembretes

* Sempre chame de main o arquivo que tem o bloco terraform

# Doc

https://developer.hashicorp.com/terraform?product_intent=terraform

# Estrutura

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/c942f9c6-c579-4206-a9cc-6c3a3b1f0f4e)

# Estrutura de blocos da linguagem hscl

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/36a5719f-0027-4e51-b190-c81288310e54)

## Exemplo inicial:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ffd14cc0-aa75-491a-84f7-92d8eeb7f885)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/5cf175b5-eefb-4f26-9b6a-c689bd8fb7a3)

# Bloco terraform

## Doc

https://developer.hashicorp.com/terraform/language/settings

Aceita 6 tipos de parâmetros

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/dbb0b3cc-5732-4613-9842-e56f33d7b9fc)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/043174e6-45bf-4b06-84b7-2954a3fe06b6)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/fe0684b0-33b4-4569-8804-352cfefe727f)

# Terraform CLI

## Doc:

https://developer.hashicorp.com/terraform/cli

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/716640d5-d1ec-4971-8261-e28bbb70d648)

# Criando bucket na AWS

## Doc para aws

https://registry.terraform.io/providers/hashicorp/aws/latest/docs

## Doc regiões aws

https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/Concepts.RegionsAndAvailabilityZones.html

Por segurança sempre exporte as suas configs sensíveis dessa forma:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ff28c7a9-45d3-49f3-94eb-023dc29eb871)

Config:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/e6295ba5-7bb8-4eb3-a3e6-5f38b6660083)

Criando o bucket:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/4baa4176-5531-4335-8dee-6d9a47303bf9)

# Testando comandos do terraform

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ec42417f-8bac-4190-9aee-b76d95b8d676)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/49c45e52-dcf4-4f7c-b6c7-e481d7aadc12)

Após rodar o comando isso será criado:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/5d853c69-eca6-4bfe-9f5f-f9afd217e5fe)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/be5d3ab2-ab84-49b8-8589-059ab30a143e)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/c1726fc7-5ec3-4a6d-b97a-00474a54e4d7)

# Variáveis

Doc: https://developer.hashicorp.com/terraform/language/values/variables

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/165c0e4c-f60f-4c64-8227-a4a943dcb8f3)

Referenciando as variáveis

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/cf74bc24-cfbf-476d-977e-412cdcd2b6b9)

# Referenciando blocos em outros blocos

Doc: https://developer.hashicorp.com/terraform/language/expressions/references

## Referenciando um bloco de recursos

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/e70cc265-5074-4d4e-a6c8-bf46d512687d)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/e0b1cddc-ba5d-4614-a730-ae048f01a081)

# Local values

Usado para funções que se repetem no código ou variáveis

Doc: https://developer.hashicorp.com/terraform/language/values/locals

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/7a434bd4-626f-4339-bea1-18922bd354dd)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/794cc441-d849-4f10-bb2e-3d1e44c13ffc)

# Outputs

Server para expor informações


Doc: https://developer.hashicorp.com/terraform/language/values/outputs

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/931b387b-7abb-46a1-81c6-8cfad5dadef0)

# Remote States

Configurar o provider

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/75aca739-32a7-47bd-aa8e-bb69b3dd81e8)

Configurar a bucket

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/cb75d24d-a7df-4cb9-8258-ae09543baf3e)

Configurando o VPV

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/dc502970-4eed-4943-9126-5d828dbffafa)

Doc: https://developer.hashicorp.com/terraform/language/settings/backends/s3

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/177312b8-c528-4cd5-9784-5d52bfafd427)


# Modulos

Doc: https://developer.hashicorp.com/terraform/language/modules



