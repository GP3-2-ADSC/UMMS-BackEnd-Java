#!/bin/bash

# Verifica se o Docker já está instalado
if [ -x "$(command -v docker)" ]; then
    echo "O Docker já está instalado."
    exit 0
fi

# Verifica se o usuário tem privilégios de superusuário (root)
if [[ $EUID -ne 0 ]]; then
    echo "Este script precisa ser executado com privilégios de superusuário (root)."
    exit 1
fi

# Atualiza o sistema e instala as dependências necessárias
apt update
apt install -y apt-transport-https ca-certificates curl software-properties-common

# Adiciona a chave GPG oficial do Docker
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg

# Adiciona o repositório oficial do Docker
echo "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | tee /etc/apt/sources.list.d/docker.list > /dev/null

# Atualiza o sistema novamente
apt update

# Instala o Docker
apt install -y docker-ce docker-ce-cli containerd.io

# Verifica se o Docker foi instalado corretamente
if [ -x "$(command -v docker)" ]; then
    echo "O Docker foi instalado com sucesso!"
else
    echo "Houve um problema durante a instalação do Docker."
    exit 1
fi

# Adiciona o usuário atual ao grupo "docker" para executar comandos sem sudo
usermod -aG docker $USER

echo "Por favor, faça logout e login novamente para usar o Docker sem sudo."
