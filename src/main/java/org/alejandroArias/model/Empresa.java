package org.alejandroArias.model;

import java.util.ArrayList;

public class Empresa {

    private ArrayList<Categoria> listaCategorias;
    private String nombre;


    public Empresa( String nombre) {

        this.listaCategorias = new ArrayList<>();
        this.nombre = nombre;
    }


    public ArrayList<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(ArrayList<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Producto> getProductosCategoria(String categoria) {
        return obtenerProductosCategoriaRecursivamente(categoria, 0, new ArrayList<Producto>());

    }

    public void  addCategoria(Categoria categoria){
        listaCategorias.add(categoria);
    }

    /**
     * Metodo que recorre una lista
     * @param categoria
     * @param index
     * @param listaProd
     * @return
     */
    private ArrayList<Producto> obtenerProductosCategoriaRecursivamente(String categoria, int index, ArrayList<Producto> listaProd) {

        if(index == listaCategorias.size()) return listaProd;
        if(listaCategorias.get(index).containsCategorias()) listaProd.addAll(listaCategorias.get(index).getProductosRecursivamente(categoria, 0, listaProd));
        return obtenerProductosCategoriaRecursivamente(categoria, ++index, listaProd);

    }

    /*
        Obtener los productos que se encuentren en un rango de precios dado por el usuario.
     */

    public ArrayList<Producto> getProductosRangoPrecios(double precioMin, double precioMax) {
        return obtenerProductosRangoPreciosRecursivamente(precioMin, precioMax, 0, new ArrayList<Producto>());
    }

    private ArrayList<Producto> obtenerProductosRangoPreciosRecursivamente(double precioMin, double precioMax, int i, ArrayList<Producto> productos) {
        if(i == listaCategorias.size()) return productos;
        if(listaCategorias.get(i).containsCategorias()) productos.addAll(listaCategorias.get(i).getProductosRangoPreciosRecursivamente(precioMin, precioMax, 0, productos));
        return obtenerProductosRangoPreciosRecursivamente(precioMin, precioMax, ++i, productos);
    }

    /*
        Imprimir todos los productos de la empresa por categoria.
     */

    public void imprimirProductosPorCategoria() {
        imprimirProductosPorCategoriaRecursivamente(0);
    }

    private void imprimirProductosPorCategoriaRecursivamente(int i) {
        if(i == listaCategorias.size()) return;
        if(listaCategorias.get(i).containsCategorias()) listaCategorias.get(i).imprimirProductosPorCategoriaRecursivamente(0);
        System.out.println(listaCategorias.get(i).getNombre());
        System.out.println(listaCategorias.get(i).getListaProductos());
        imprimirProductosPorCategoriaRecursivamente(++i);
    }

}
