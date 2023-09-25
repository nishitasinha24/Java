/*
	Describe what kind of computer you’re running this code on: MacBook Air (M1, 2020) - macOS 12.3, 8 GB
	How long does it take to do a brute force attack on 5 character passwords?: 10 seconds
	How many passwords does it find?- 4768
	Split the problem into several threads
	What is the maximum number of threads that minimizes the time is takes to brute force all of the passwords?: 8
	How fast can your computer process 6 character passwords using multithreading?: 32 Seconds
 */


package bruteforceattack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BruteForceAttack {

    static final char startLower = 'a';
    static final char startUpper = 'A';
    static final char startNumber = '0';
    static final char[] letters = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k','l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v','w','x', 'y', 'z', 'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
    public static char getChar(int i) {
        return letters[i];
    }

    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static int getInt(char c) {
        if ((c >= 'a') && (c <= 'z')) {
            return c - 'a';
        } else if ((c >= 'A') && (c <= 'Z')) {
            return 26 + (c - 'A');
        } else if ((c >= '0') && (c <= '9')) {
            return 52 + (c - '0');
        } else {
            return 0;
        }
    }

    public static Set<String> hashedpasswords(String filename) {
        Set<String> hashSet = new HashSet<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String inline = br.readLine();
            while (inline != null) {
                hashSet.add(inline);
                inline = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashSet;

    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Set<String> passwordSet = hashedpasswords("hashedpassword.txt");
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        final AtomicInteger numfound = new AtomicInteger(0);
        final int len = 6;

        double max = Math.pow(26, len);
        byte[] pass = new byte[len];
        for (int k = 0;k<pass.length ;k++) {
            pass[k] = startLower;
        }

        // Creating multiple threads to work on different parts of the search space
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("No. of threads: " + numOfThreads);
        ExecutorService exec = Executors.newFixedThreadPool(numOfThreads);
        double range = max / numOfThreads;
        for (int i = 0; i < numOfThreads; i++) {
            final int thrId = i;
            exec.submit(() -> {
                int start = (int) (thrId * range);
                int end = (int) ((thrId + 1) * range);
                if (thrId == numOfThreads - 1) {
                    end = (int) max;
                }

                byte[] passwordLocal = new byte[len];
                for (int k = 0;k<passwordLocal.length ;k++) {
                	passwordLocal[k] = pass[k];
                }

                for (long j=start ; j < end ;j++) {
                    int v = (int)(j % 26L);
                    if ((v == 0) && (j!=0)) {

                    	passwordLocal[0] = startLower;
                        for (int k = 1; k < passwordLocal.length; k++) {
                            if (passwordLocal[k] == 'z') {
                                if (k != passwordLocal.length-1) {
                                	passwordLocal[k] = startLower;
                                    continue;
                                } else {
                                    break;
                                }
                            } else {
                                int value = getInt((char)passwordLocal[k]);
                                value++;
                                passwordLocal[k] = (byte)letters[value];
                                break;
                            }
                        }
                    } else {
                    	passwordLocal[0] = (byte)letters[v];
                    }

                    byte[] encodedhash = digest.digest(passwordLocal);

                    String hashpass = BruteForceAttack.bytesToHex(encodedhash);
                    if (passwordSet.contains(hashpass)) {
                        String passString = new String(passwordLocal);
                        System.out.println("found password " + passString);
                        numfound.incrementAndGet();
                    }
                }
            });
        }

        // Waiting for all threads to finish
        exec.shutdown();
        try {
        	exec.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("found " + numfound.get() + " out of " + passwordSet.size());
    }


}   