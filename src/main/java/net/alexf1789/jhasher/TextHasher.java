package net.alexf1789.jhasher;

import org.apache.commons.codec.digest.DigestUtils;

public class TextHasher implements Hasher {

    private String input;
    private String[] hash;

    public TextHasher(String input) {
        this.input = input;
        this.hash = new String[3];
    }

    public String[] getHash() {
        hash[0] = DigestUtils.sha1Hex(input);
        hash[1] = DigestUtils.sha256Hex(input);
        hash[2] = DigestUtils.sha512Hex(input);

        return hash;
    }

}
