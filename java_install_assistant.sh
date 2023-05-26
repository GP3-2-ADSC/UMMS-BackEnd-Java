#!/bin/bash

RED='0;35'
NC='\033[0m' 
VERSAO=11
	

# Verifica se o Java 17 já está instalado
java --version
if [ $? -eq 0 ]; then
    echo "Java 17 já está instalado."
    exit 0
fi

# Define as variáveis de ambiente necessárias
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$PATH:$JAVA_HOME/bin

# Adiciona o repositório do Java 17
add-apt-repository ppa:openjdk-r/ppa -y
apt-get update

# Instala o Java 17
apt-get install openjdk-17-jdk -y

# Verifica se a instalação foi bem sucedida
java --version
if [ $? -eq 0 ]; then
    echo "Java 17 foi instalado com sucesso."
else
    echo "Houve um erro durante a instalação do Java 17."
fi

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

