package org.example.constructor;

import org.example.utility.StringUtil;
import java.util.Date;

public class Block {

    // Caso o dado do blockChain muda, o  Hash também mudará

    public String hash;
    private String data;
    private long timeStamp;
    public String prevHash;

    public String calculateHash() {
        String calulatedHash = StringUtil.applySha256(
                    prevHash +
                    Long.toString(timeStamp) +
                    data
        );

        return calulatedHash;
    }

    // Construtor do bloco
    public Block (String data, String prevHash) {
        this.data = data;
        this.prevHash = prevHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

}
