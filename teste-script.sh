#!/bin/bash
echo  "$(tput setaf 10)[Bot assistant]:$(tput setaf 7) Olá, usuário, serei seu assistente para instalação do Docker.;"
echo  "$(tput setaf 10)[Bot assistant]:$(tput setaf 7)  Verificando aqui se você possui o docker instalado...;"
sleep 2
# Verifica se o script está sendo executado como root
if [ "$EUID" -ne 0 ]; then
  echo "Este script precisa ser executado como root. Por favor, execute-o com sudo ou como usuário root."
  exit
fi

# Atualiza o sistema
apt update

# Instala as dependências necessárias para adicionar repositórios via HTTPS
apt install -y apt-transport-https ca-certificates curl software-properties-common

# Adiciona a chave GPG oficial do Docker
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg

# Adiciona o repositório estável do Docker ao sistema
echo "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

# Atualiza o sistema novamente após adicionar o repositório do Docker
apt update

# Instala o Docker.io
apt install -y docker.io

# Inicia o serviço do Docker e configura para iniciar automaticamente na inicialização do sistema
systemctl start docker
systemctl enable docker

# Verifica a versão do Docker instalada
docker --version

# Verifica se o usuário atual faz parte do grupo "docker" e, se não, adiciona-o ao grupo
if ! groups "$(whoami)" | grep -q "\bdocker\b"; then
  usermod -aG docker "$(whoami)"
  echo "O usuário $(whoami) foi adicionado ao grupo 'docker'. É necessário fazer logout e login novamente para que as alterações tenham efeito."
fi
