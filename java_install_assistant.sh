#!/bin/bash

RED='0;35'
NC='\033[0m' 
VERSAO=11
	
echo  "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Olá, usuário, serei seu assistente para instalação do Java e também do docker.;"
echo  "$(tput setaf 10)[Bot assistant]:$(tput setaf 7)  Verificando aqui se você possui o Java e docker instalado...;"
sleep 2
	
echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Olá! Vou te ajudar a instalar o Java 17."
echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Verificando se o Java já está instalado..."

java --version
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
sudo apt update
sleep 3


echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Instalando o Java 17..."

sleep 2


sudo apt install default-jre ; apt install openjdk-17-jre-headless; -y

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Java 17 instalado com sucesso!"

java --version

sleep 5

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Você deseja instalar o docker? (S/N)"
read inst

if [ "$inst" != "S" ] && [ "$inst" != "s" ]; then
    echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Instalação cancelada. Até a próxima!"
    exit 0
fi

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Olá! Vou te ajudar a instalar o Docker 17."
echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Verificando se o Docker já está instalado..."
sleep 2

docker --version

if [ $? -eq 0 ]; then
    echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) O Docker já está instalado."
    exit 0
fi

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) O docker não foi encontrado."
echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Deseja continuar a instalação? (S/N)"
read inst

if [ "$inst" != "S" ] && [ "$inst" != "s" ]; then
    echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Instalação cancelada. Até a próxima!"
    exit 0
fi

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Adicionando as dependências para adicionar o repositório via HTTPS"
sudo  apt install -y apt-transport-https ca-certificates curl software-properties-common

sleep 2

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Adicionando o par de chaves do Docker"

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
sleep 2

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Adicionando o repositório do Docker..."

echo "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

sudo apt update

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Instalando o Docker..."

apt install -y docker.io

sleep 5

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Iniciando serviço e configurando docker"

systemctl enable docker
sudo service docker start
sleep 2

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Verificando versão do docker"
docker --version

