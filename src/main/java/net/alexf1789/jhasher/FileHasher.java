package net.alexf1789.jhasher;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileHasher implements Hasher {

    private File file;
    private String[] hash;

    public FileHasher(File file) {
        this.file = file;
        this.hash = new String[3];
    }

    public String[] getHash() {
        if(!file.isFile())
            throw new RuntimeException("The specified file is not valid!");

        try(FileInputStream fstream = new FileInputStream(file)) {
            hash[0] = DigestUtils.sha1Hex(fstream);
            hash[1] = DigestUtils.sha256Hex(fstream);
            hash[2] = DigestUtils.sha512Hex(fstream);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return hash;
    }

}
