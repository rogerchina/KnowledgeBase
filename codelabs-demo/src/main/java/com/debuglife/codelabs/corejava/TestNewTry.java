package com.debuglife.codelabs.corejava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TestNewTry {

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new FileReader("/bin"))) {

        } catch (Exception e) {

        } finally {

        }

        try(FileReader f = new FileReader("/bin");
            BufferedReader br = new BufferedReader(f)) {

        } catch (Exception e) {

        }
    }



}
