#!/bin/bash

PURPLE='0;35'
NC='\033[0m' 
VERSAO=11
	

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Verificando se o Java 17 já está instalado"

sleep 2

java --version
if [ $? -eq 0 ]; then
    echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Java 17 já está instalado."
fi



echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Adicionando o repositório do Java 17"
sleep 2

add-apt-repository ppa:openjdk-r/ppa -y
apt-get update

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Instalando o Java 17"
sleep 2
apt-get install openjdk-17-jdk -y


java --version
if [ $? -eq 0 ]; then
    echo " $(tput setaf 10)[Bot assistant]:$(tput setaf 7) Java 17 foi instalado com sucesso."
else
    echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Houve um erro durante a instalação do Java 17."
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

##  curl faz requisições HTTP
##  gpg(dearmor) usada para manipular chaves, ela está salvando a chave no seguinte caminho -> /usr/share/keyrings/docker-archive-keyring.gpg

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
if [ $? -eq 0 ]; then
    echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Docker foi instalado com sucesso."
else
    echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Houve um erro durante a instalação do Docker."
fi

echo  "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Você deseja efetuar a criação de um container? (S/N)"
read inst

if ["$inst" != "S" ] && [ "$inst" != "s" ]; then
     echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Até a próxima."
    exit 0
else
    echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Instalando a imagem do Mysql."
    sudo docker pull mysql:8.0
    sleep 2

    echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Criando o container e efetuando a configuração de nome do banco e senha"
    sudo docker run -d -p 3306:3306 --name ContainerBD -e "MYSQL_DATABASE=retria" -e "MYSQL_ROOT_PASSWORD=urubu100" mysql:8.0
    echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Nome banco: retria - Senha: urubu100"
    sleep 8
fi


