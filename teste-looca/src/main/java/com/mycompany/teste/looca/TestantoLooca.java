/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teste.looca;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.rede.RedeInterfaceGroup;
import com.github.britooo.looca.api.group.sistema.Sistema;
import java.util.List;

/**
 *
 * @author silvam
 */
public class TestantoLooca {

    public static void main(String[] args) {
        Looca looca = new Looca();
        Sistema sistema = new Sistema();
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();

        List<Disco> discos = grupoDeDiscos.getDiscos();
                
        System.out.println("Processador \n" + looca.getProcessador());
        System.out.println("Memória ram \n" + looca.getMemoria());
        System.out.println("Rede \n" + looca.getRede());
        
        Rede rede = looca.getRede();
        RedeInterfaceGroup rig = rede.getGrupoDeInterfaces();
        
        
        System.out.println(looca.getRede().getGrupoDeInterfaces().getInterfaces());
        
        for (Disco disco : discos) {
            System.out.println("Disco: ");
            System.out.println(sistema);
        }
    }
}
