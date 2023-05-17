#!/bin/bash

RED='0;35'
NC='\033[0m' 
VERSAO=11
	
echo  "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Olá, usuário, serei seu assistente para instalação do Docker.;"
echo  "$(tput setaf 10)[Bot assistant]:$(tput setaf 7)  Verificando aqui se você possui o docker instalado...;"
sleep 2
	
echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Olá! Vou te ajudar a instalar o Docker 17."
echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Verificando se o Docker já está instalado..."

docker -version
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

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Adicionando o repositório do Docker..."

sudo apt-get update
sudo apt-get install ca-certificates curl gnupg

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Instalando o Docker..."

sudo install -m 0755 -d /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
sudo chmod a+r /etc/apt/keyrings/docker.gpg

echo "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Docker instalado com sucesso!"

docker -version

