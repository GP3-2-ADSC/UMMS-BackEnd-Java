#!/bin/bash

PURPLE='0;35'
NC='\033[0m' 
VERSAO=11
	
echo  "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Olá Aluno, serei seu assistente para instalação do Java e também do docker.;"
echo  "$(tput setaf 10)[Bot assistant]:$(tput setaf 7)  Verificando aqui se você possui o Java e docker instalado...;"
sleep 2
	
echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Olá! Vou te ajudar a instalar o Java 17."
echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Verificando se o Java já está instalado..."

java -version
if [ $? -eq 0 ]; then
    echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) O Java já está instalado."
    exit 0
fi

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) O Java 17 não foi encontrado."
echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Deseja continuar a instalação? (S/N)"
read inst

if [ "$inst" != "S" ] && [ "$inst" != "s" ]; then
    echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Instalação cancelada. Até a próxima!"
    exit 0
fi

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Adicionando o repositório do Java 17..."

sudo apt-add-repository --yes ppa:linuxuprising/java
sudo apt-get update

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Instalando o Java 17..."

sudo apt-get install -y oracle-java17-installer
sudo apt-get install -y oracle-java17-set-default

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Java 17 instalado com sucesso!"

java -version

