# SBSeg2021
Reposit贸rio com os documentos e c贸digos fontes submetidos ao Sal茫o de Ferramentas do SBSeg 2021.

## 馃搶 Overview
 #### 1. **<a href="https://github.com/anonymousgithub21/SBSeg2021/tree/master/Aplicativo"> 馃摫 Aplicativo </a>**

Nesta pasta, encontram-se todos os c贸digos-fontes necess谩rios para rodar a aplica莽茫o m贸vel em um em um emulador ou _smartphone_ com sistema operacional Android.

#### 2.  **<a href="https://github.com/anonymousgithub21/SBSeg2021/tree/master/Gestor%20Automatizado"> 馃懛 Gestor Automatizado </a>**

Nesta pasta, encontram-se todos os c贸digos-fontes necess谩rios para rodar o algoritmo do Gestor Automatizado.

#### 3. **<a href="https://github.com/anonymousgithub21/SBSeg2021/tree/master/BKE_Auth4ISP_Scyther"> 鉁? Verifica莽茫o de Protocolo de Seguran莽a com Scyther </a>**

Na pasta BKE_Auth4ISP_Scyther encontra-se o c贸digo .spdl utilizado para verificar a seguran莽a do protocolo BKE4ISP, al茅m de instru莽玫es sobre como execut谩-lo.


## 馃摑Manual de Instala莽茫o e Instru莽玫es para Testes no Aplicativo e no Gestor Automatizado.

## **馃彿锔? Nota** 
Nesta implementa莽茫o, utilizamos o Java SE Development Kit 8 para executar arquivos java, a IDE Apache Netbeans em sua vers茫o 12.3 para implantar o Gestor Automatizado, o ambiente de desenvolvimento integrado Android Studio, na sua vers茫o 4.2 para desenvolver para a plataforma Android, al茅m dos  injetores de depend锚ncias Maven (para o Netbeans) e Gradle (para o Android Studio). O desenvolvimento foi realizado em uma m谩quina com o Sistema Operacional de 64 bits - Windows 10 Home. Os dados s茫o persistidos no Firebase Realtime Database.

## **馃摑 Requisitos** 
N贸s disponibilizamos tr锚s formas de testes. Na primeira e segunda, os requisitos necess谩rios consistem no Java SE Devolopment Kit para rodar um arquivo do tipo .jar e um _smartphone_ com o sistema operacional Android para rodar um arquivo com a extens茫o .apk. Na terceira forma, 茅 necess谩rio os mesmos requisitos da 1潞 e 2潞 forma, al茅m das IDEs e os injetores de depend锚ncia citados na nota acima. Observa莽茫o: O _smartphone_ deve estar configurado para permitir a instala莽茫o de aplicativos desconhecidos. Para saber como ativar esse tipo de insta莽茫o, **<a href="https://www.showmetech.com.br/instalando-aplicativos-android-de-fontes-desconhecidas/">clique aqui</a>**.

## **鈿欙笍 Instala莽茫o**
#### **鈿狅笍 Importante**: Para que todos os processos funcionem corretamente, 茅 necess谩rio que o **Gestor Automatizado** esteja **em execu莽茫o**.

## **1锔忊儯 Forma** 
N贸s disponibilizamos o Gestor Automatizado em um servidor p煤blico, dispensando a execu莽茫o manual. Assim, basta instalar o aplicativo em seu smartphone utilizando o arquivo <a href="https://github.com/anonymousgithub21/SBSeg2021/blob/master/identificaispremoto.apk">identificaispremoto.apk</a>. Na tela de login, basta entrar com um dos usu谩rios pr茅-cadastrados:
|              |  Cliente       |    T茅cnico       | Gestor Humano   |
| :---:        |     :---:      |         :---:    |  :---:   |
| Usu谩rio 1:   | 408.345.420-21 | 514.775.490-30   |  946.234.100-13 |
| Password 1:  | 40834542021    | 51477549030      |  94623410013    |
| Usu谩rio 2:   | 716.421.960-53 | 583.753.510-16   |  - |
| Password 2:  | 71642196053    | 58375351016      |  - |


## 2锔忊儯 **Forma** 
A segunda forma consiste em realizar o download do arquivo que representa o Gestor Automatizado (<a href="https://github.com/anonymousgithub21/SBSeg2021/blob/master/gestorautomatizado.jar">gestorautomatizado.jar</a>) e do aplicativo Identifica ISP (<a href="https://github.com/anonymousgithub21/SBSeg2021/blob/master/identificaisplocal.apk">identificaisplocal.apk</a>) para execut谩-los localmente. O Gestor Automatizado deve ser executado em uma m谩quina (i.e., computador) e deve conter o arquivo (<a href="https://github.com/anonymousgithub21/SBSeg2021/blob/master/inovaisp-firebase-adminsdk-urcvf-1b5492eea5.json">inovaisp-firebase-adminsdk-urcvf-1b5492eea5.json</a>) no mesmo diret贸rio. O aplicativo deve ser executado em um smartphone. Os arquivos est茫o dispon铆veis neste reposit贸rio. Para testar o fluxo de autentica莽茫o, o gestor automatizado deve estar em execu莽茫o. Na tela de login, 茅 necess谩rio entrar com o seguinte usu谩rio pr茅-cadastrado:
|              |  Cliente       |    T茅cnico       | Gestor Humano   |
| :---:        |     :---:      |         :---:    |  :---:   |
| Usu谩rio 1:   | 955.860.260-40 | 588.407.470-01   |  946.234.100-13 |
| Password 1:  | 95586026040    | 58840747001      |  94623410013    |


## 3锔忊儯 **Forma**
A terceira forma abrange o download e a execu莽茫o manual dos c贸digos-fontes do **<a href="https://github.com/anonymousgithub21/SBSeg2021/tree/master/Aplicativo">Aplicativo </a>** e do **<a href="https://github.com/anonymousgithub21/SBSeg2021/tree/master/Gestor%20Automatizado">Gestor Automatizado</a>**. Tais aplica莽玫es est茫o identificadas no reposit贸rio conforme o nome destacado em negrito. Ap贸s a execu莽茫o de ambos, os testes podem ser feitos utilizando os mesmos usu谩rios da 2陋 Forma.

馃彿锔? Nota: Utilizamos os IDEs Apache Netbeans em sua vers茫o 12.3 (para o Gestor Automatizado) e Android Studio na vers茫o 4.2 (para o Aplicativo), junto com os injetores de depend锚ncia Maven e Gradle. Tamb茅m, utilizamos o Java SE Development Kit na vers茫o 8 em um Windows 10 com base em 64 bits.

## 鉁旓笍 J谩 instalei! Como Testar?!

Basicamente,  o aplicativo Identifica ISP tem tr锚s funcionalidades principais: _(i)_ autenticar clientes e t茅cnicos, _(ii)_ simular a integra莽茫o com uma API externa, _(iii)_ avalia莽茫o de t茅cnicos.

鉃★笍 _(i)_ Para testar o processo de autentica莽茫o, o **T茅cnico**, na tela inicial, deve clicar em _**gerar c贸digo de autentica莽茫o**_. O **Cliente**, por sua vez, deve clicar em _**ler c贸digo de autentica莽茫o**_ e apontar a c芒mera para o QR Code do **t茅cnico**. Ap贸s a autentica莽茫o, ambas identidades s茫o exibidas. 

鉃★笍 **O Gestor Humano** pode revogar a identifica莽茫o de um cliente ou t茅cnico, clicando no bot茫o _**revogar identifica莽茫o**_. Assim, o mesmo n茫o poder谩 mais ser autenticado. **RECOMENDAMOS N脙O REVOGAR A IDENTIFICA脟脙O DOS USU脕RIOS, POIS OS MESMOS FORAM AUTORIZADOS MANUALMENTE PARA ESSES TESTES** (uma vez que o servi莽o de identifica莽茫o n茫o consta nesse demo).

鉃★笍 _(ii)_ Para simular uma **API Externa**, o Gestor Humano deve clicar em _**gerenciar usu谩rios**_ e no bot茫o representado por um "_**+**_". Para simular adi莽茫o de clientes, os CPFs podem ser utilizados:  **24268060014** ou **94547931011**. Para t茅cnico, o CPF **04186570094**.

鉃★笍 _(iii)_ Para simular a avalia莽茫o de um t茅cnico, o Cliente deve ter um chamado registrado. Depois, 茅 s贸 clicar no **_chamado_**, preencher com uma ou cinco estrelas, e opcionalmente, escrever um coment谩rio. Depois, 茅 s贸 relatar o feedback.

**Observa莽茫o.** Os clientes com CPFs 40834542021 e 95586026040 possuem chamados registrados.
