package org.globant.repository;

import java.util.Scanner;

public class ScannerRepository {
    private static ScannerRepository instance;
    private final Scanner scanner;


    private ScannerRepository() {
        this.scanner = new Scanner(System.in);
    }

    public  static ScannerRepository getInstance(){
        if(instance == null){
            instance = new ScannerRepository();
        }
        return instance;
    }

    public Scanner getScanner(){
        return this.scanner;
    }
}
