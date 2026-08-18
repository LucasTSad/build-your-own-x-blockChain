package org.example;

import org.example.constructor.Block;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class NoobChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();

    public static void main(String[] args) {

        blockchain.add(new Block("Primeiro bloco", "0"));
        blockchain.add(new Block("Segundo bloco", blockchain.get(blockchain.size()-1).hash));
        blockchain.add(new Block("Terceiro bloco", blockchain.get(blockchain.size()-1).hash));

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);

    }

}
