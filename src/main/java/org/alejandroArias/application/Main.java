package org.alejandroArias.application;

import org.alejandroArias.model.*;

import java.util.ArrayList;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Empresa empresa = new Empresa("El triunfo");

        Categoria categoria1 = new Categoria("cat1");
        Categoria subCat11 = new Categoria("Hogar");
        Categoria subCat12 = new Categoria("subCat12");
        Categoria subCat13 = new Categoria("subCat13");

        Producto tv = new Producto("tv");
        tv.setPrecio(1000.0);
        Producto radio = new Producto("radio");
        radio.setPrecio(500.0);

        subCat11.addProd(tv);
        subCat11.addProd(radio);

        categoria1.addCategoria(subCat11);
        categoria1.addCategoria(subCat12);
        categoria1.addCategoria(subCat13);

        Categoria categoria2 = new Categoria("cat2");
        Categoria subCat21 = new Categoria("subCat21");
        Categoria subCat22 = new Categoria("subCat12");
        Categoria subCat23 = new Categoria("Hogar");


        Producto lavadora = new Producto("Lavadora");
        lavadora.setPrecio(1200.0);
        subCat23.addProd(lavadora);


        categoria2.addCategoria(subCat21);
        categoria2.addCategoria(subCat22);
        categoria2.addCategoria(subCat23);

        empresa.addCategoria(categoria1);
        empresa.addCategoria(categoria2);

        ArrayList<Producto> productos = empresa.getProductosCategoria("Hogar");
        //Creo una estructura de datos, que es un set, para que no se repitan los productos
        Set<Producto> setProductos = Set.copyOf(productos);
        System.out.println("setProductos = " + setProductos);

        System.out.println("----------------" );
        empresa.imprimirProductosPorCategoria();

        System.out.println("--------" );
        Set<Producto> setProductos2 =  Set.copyOf(empresa.getProductosRangoPrecios(1100.0, 1500.0));
        System.out.println("setProductos2 = " + setProductos2);
    }


}