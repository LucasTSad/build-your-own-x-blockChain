package org.example;

import org.example.constructor.Block;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class NoobChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 6;

    public static void main(String[] args) {

        blockchain.add(new Block("Primeiro bloco", "0"));
        System.out.println("Tentando minerar o bloco 1...");
        blockchain.get(0).mineBlock(difficulty);
        //-----------------------------------------------
        blockchain.add(new Block("Segundo bloco", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Tentando minerar o bloco 2...");
        blockchain.get(1).mineBlock(difficulty);
        //-----------------------------------------------
        blockchain.add(new Block("Terceiro bloco", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Tentando minerar o bloco 3...");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain é valido: " + isChainVaild());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("Blockchain JSON: " + blockchainJson);

    }

    public static Boolean isChainVaild() {

        Block currentBlock;
        Block prevBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop pelo blockchain para vereficiar os hashes
        for(int i=1; i < blockchain.size(); i++) {

            currentBlock = blockchain.get(i);
            prevBlock = blockchain.get(i-1);
            // compara o hash registrado e o hash calculado
            if(!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("Hash atual diferente");
                return false;
            }
            // compara o hash anterior e o hash anterior que foi registrado
            if(!prevBlock.hash.equals(currentBlock.prevHash)){
                System.out.println("Hash anterior diferente");
                return false;
            }
            // confere se o hash foi resolvido
            if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("Esse bloco não foi minerado");
                return false;
            }
        }

        return true;
    }

}
