package org.example.constructor;

import org.example.utility.StringUtil;
import java.util.Date;

public class Block {

    // Caso o dado do blockChain muda, o  Hash também mudará

    public String hash;
    private String data;
    private long timeStamp;
    public String prevHash;
    private int nonce;

    // Construtor do bloco
    public Block (String data, String prevHash) {
        this.data = data;
        this.prevHash = prevHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calulatedHash = StringUtil.applySha256(
                prevHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
                );
        return calulatedHash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while(!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Bloco Minerado!!! : " + hash);
    }

}
