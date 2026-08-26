package UI;

import Business.PeixeController;

import java.util.Scanner;

public class UIPeixe {
    private final PeixeController controlador;

    private Scanner scn;
    private Scanner scl; //Usado para ler Strings

    public UIPeixe(){
        controlador = new PeixeController();
        scn = new Scanner(System.in);
        scl = new Scanner(System.in);
    }

    public void add(){

    }

    public void update(){

    }

    public void deleteById(){

    }

    public void getById(){

    }

    public void listar(){

    }
}
