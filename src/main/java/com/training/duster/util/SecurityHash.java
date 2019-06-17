package com.training.duster.util;

import org.mindrot.jbcrypt.BCrypt;
/**
 * The {@code SecurityHash} class is used to hash and check passwords
 */
public class SecurityHash {
    public String hashFunction(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}


