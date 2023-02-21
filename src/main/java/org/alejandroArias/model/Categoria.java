package org.alejandroArias.model;

import java.util.ArrayList;
import java.util.Collection;

public class Categoria {


    private String codigo;
    private String descripcion;
    private ArrayList<Categoria> listaSubCategorias;
    private ArrayList<Producto> listaProductos;
    private String nombre;

    public Categoria(String nombre) {

        this.nombre = nombre;
        listaProductos = new ArrayList<>();
        listaSubCategorias = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Categoria> getListaSubCategorias() {
        return listaSubCategorias;
    }

    public void setListaSubCategorias(ArrayList<Categoria> listaSubCategorias) {
        this.listaSubCategorias = listaSubCategorias;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addProd(Producto producto) {
        listaProductos.add(producto);
        producto.setCategoria(this);
    }

    public void addCategoria(Categoria categoria) {
        listaSubCategorias.add(categoria);
    }


    public boolean containsCategorias() {
        return listaSubCategorias.isEmpty();
    }

    public ArrayList<Producto> getProductosRecursivamente(String categoria, int index, ArrayList<Producto> productos) {

        if (index == listaSubCategorias.size()) return productos;
        if (listaSubCategorias.get(index).getNombre().equals(categoria))
            productos.addAll(listaSubCategorias.get(index).getListaProductos());
        if (listaSubCategorias.get(index).containsCategorias())
            productos.addAll(listaSubCategorias.get(index).getProductosRecursivamente(categoria, 0, productos));
        return getProductosRecursivamente(categoria, ++index, productos);

    }


    @Override
    public String toString() {
        return "Categoria{" +
                " listaSubCategorias=" + listaSubCategorias +
                ", listaProductos=" + listaProductos +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public ArrayList<Producto> getProductosRangoPreciosRecursivamente(double precioMin, double precioMax, int i, ArrayList<Producto> productos) {

        if (i == listaSubCategorias.size()) return productos;
        if (listaSubCategorias.get(i).containsCategorias()) productos.addAll(listaSubCategorias.get(i).getProductosRangoPreciosRecursivamente(precioMin, precioMax, 0, productos));
        productos.addAll(listaSubCategorias.get(i).getProductosRangoPrecios(precioMin, precioMax));
        return getProductosRangoPreciosRecursivamente(precioMin, precioMax, ++i, productos);

    }

    private ArrayList<Producto> getProductosRangoPrecios(double precioMin, double precioMax) {
            ArrayList<Producto> productos = new ArrayList<>();
            for (Producto producto : listaProductos) {
                if (producto.getPrecio() >= precioMin && producto.getPrecio() <= precioMax) productos.add(producto);
            }
            return productos;
    }

    public void imprimirProductosPorCategoriaRecursivamente(int i) {
        if (i == listaSubCategorias.size()) return;
        System.out.println(listaSubCategorias.get(i).getNombre());
        for (Producto producto : listaSubCategorias.get(i).getListaProductos()) {
            System.out.println(producto.getNombre());
        }
        if (listaSubCategorias.get(i).containsCategorias()) listaSubCategorias.get(i).imprimirProductosPorCategoriaRecursivamente(0);
        imprimirProductosPorCategoriaRecursivamente(++i);
    }
}
