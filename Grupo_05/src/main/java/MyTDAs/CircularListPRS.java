/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyTDAs;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Pc
 */
public class CircularListPRS<E> implements List<E> {
    private Node actual;
    private Node primero;
    private Node ultimo;
    private int size;
    
    protected class Node{
        Node ant;
        E contenido;
        Node sig;
        
        public Node(E e){
            ant = null;
            contenido = e;
            sig = null;
        }
    }
    
    protected class CircularListIteratorPRS implements Iterator<E>{
        private Node actual = primero;
        private Node ultimoRetornado = null;
        private int nextIndex = 0;
        
        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            ultimoRetornado = actual;
            actual = actual.sig;
            nextIndex++;
            return ultimoRetornado.contenido;
        }
    }

    protected class ListIteratorPRS implements ListIterator<E>{
        private Node actual = primero;
        private Node ultimoRetornado = null;
        private int nextIndex = 0;
        
        @Override
        public boolean hasNext(){
            return nextIndex < size; 
        }
        
        @Override
        public E next(){
            if(!hasNext()) throw new NoSuchElementException();
            
            ultimoRetornado = actual;
            actual = actual.sig;
            nextIndex++;
            return ultimoRetornado.contenido;
        }
        
        @Override
        public boolean hasPrevious(){
            return nextIndex > 0;
        }
        
        @Override
        public E previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            
            if (actual == null) actual = ultimo;
            else actual = actual.ant;
            
            ultimoRetornado = actual;
            nextIndex--;
            return ultimoRetornado.contenido;
        }
        
        @Override
        public int nextIndex() {
            return nextIndex;
        }
        
        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }
        
        @Override
        public void remove(){
            if (ultimoRetornado == null) throw new IllegalStateException();
            Node nextNode = ultimoRetornado.sig;
            Node prevNode = ultimoRetornado.ant;
            
            if (prevNode == null) primero = nextNode;
            else {
                prevNode.sig = nextNode;
                ultimoRetornado.ant = null;
            }
            
            if (nextNode == null) ultimo = prevNode;
            else {
                nextNode.ant = prevNode;
                ultimoRetornado.sig = null;
            }
            
            if (actual == ultimoRetornado) actual = nextNode;
            else {
                nextIndex--;
            }
            
            ultimoRetornado = null;
            size--;
        }
        
        @Override
        public void set(E e) {
            if (ultimoRetornado == null) {
                throw new IllegalStateException();
            }
            ultimoRetornado.contenido = e;
        }
        
        @Override
        public void add(E e) {
            Node newNode = new Node(e);

            if (actual == null) {
                newNode.ant = ultimo;
                if (ultimo != null) {
                    ultimo.sig = newNode;
                }
                ultimo = newNode;
                if (primero == null) {
                    primero = newNode;
                }
            } else {
                newNode.sig = actual;
                newNode.ant = actual.ant;
                if (actual.ant != null) {
                    actual.ant.sig = newNode;
                }
                actual.ant = newNode;
                if (actual == primero) {
                    primero = newNode;
                }
            }

            nextIndex++;
            size++;
            ultimoRetornado = null;
        }
    }
    
    public E retornarActual(){
        return actual.contenido;
    }
    
    public E actualNode(E element){
        actual = this.primero;
        if (this.contains(element)){
            while (!actual.contenido.equals(element)) actual = actual.sig;
        }
        return actual.contenido;
    }
    
    public E nextNode(){
        actual = actual.sig;
        return actual.contenido;
    }
    
    public E prevNode(){
        actual = actual.ant;
        return actual.contenido;
    }
    
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.primero == null;
    }

    @Override
    public boolean contains(Object o) {        
        Node i = this.primero;
        int c = 0;
        while ( (c++) < this.size ){
            if ( i.contenido.equals(o) ) return true;
            i = i.sig;
        }
        return false;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new CircularListIteratorPRS();
    }

    @Override
    public Object[] toArray() {
        Object[] arreglo = new Object[this.size()];
        Iterator<E> it = this.iterator();
        int c = 0;
        while(it.hasNext()) arreglo[c++] = it.next();
        return arreglo;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(E e) {
        if ( e == null ) return false;
        else if ( this.isEmpty() ){
            this.ultimo = this.primero = new Node(e);
            this.primero.ant = this.ultimo;
            this.primero.sig = this.ultimo;
            this.ultimo.sig = this.primero;
            this.ultimo.ant = this.primero;
        }else {
            Node newNode = new Node(e);
            newNode.sig = this.primero;
            newNode.ant = this.ultimo;
            this.ultimo.sig = newNode;
            this.primero.ant = newNode;
            this.ultimo = newNode;
        }
        this.size++;
        return true;
    }
    
    public void addFirst(E e){
        if ( e == null ) throw new NullPointerException();
        else if ( this.isEmpty() ) this.add(e);
        this.add(0, e);
    }
    
    public void addLast(E e){
        this.add(e);
    }
    
    public E removeFirst(){
        return remove(0);
    }
    
    public E removeLast(){
        return remove(this.size()-1);
    }
    
    @Override
    public boolean remove(Object o) {
        if ( o == null ) return false;
        for ( int i = 0; i<this.size; i++){
            if(this.get(i).equals(o)) {
                this.remove(i);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if ( c == null ) return false;
        for ( E element: c ) this.add(element);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        this.primero = null;
        this.ultimo = null;
        this.actual = null;
        this.size = 0;
    }

    @Override
    public E get(int index) {
        if ( index < 0 || index >= this.size() ) throw new IndexOutOfBoundsException();
        Node iNode = this.primero;
        for ( int i = 0; i <= index; i++ ){
            if ( i == index ) return iNode.contenido;
            iNode = iNode.sig;
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        if ( index < 0 || index >= this.size() ) throw new IndexOutOfBoundsException();
        else if ( element == null ) throw new NullPointerException();
        Node iNode = this.primero;
        E removido = null;
        for ( int i = 0; i <= index; i++ ){
            if ( i == index ){
                removido = iNode.contenido;
                iNode.contenido = element;
            }
            iNode = iNode.sig;
        }
        return removido;
    }

    @Override
    public void add(int index, E element) {
        if ( index < 0 || index > this.size() || element == null ) throw new NullPointerException();
        
        Node refNode = this.primero;
        Node ultimoRetornado;
        Node newNode = new Node(element);
        
        if ( index == 0 ){
            newNode.sig = refNode;
            newNode.ant = this.ultimo;
            this.ultimo.sig = newNode;
            refNode.ant = newNode;
            this.primero = newNode;
            this.size++;
        }else if ( index == this.size() ){
            refNode = this.ultimo;
            newNode.sig = this.primero;
            newNode.ant = refNode;
            this.primero.ant = newNode;
            refNode.sig = newNode;
            this.ultimo = newNode;
            this.size++;
        }else{
            ultimoRetornado = refNode;
            refNode = refNode.sig;
            for ( int i = 1; i <= index ; i++ ){
                if ( i == index ){
                    newNode.sig = refNode;
                    newNode.ant = ultimoRetornado;
                    ultimoRetornado.sig = newNode;
                    refNode.ant = newNode;
                    this.size++;
                }else{
                    ultimoRetornado = refNode;
                    refNode = refNode.sig;
                }   
            }
        }
    }
    
    @Override
    public E remove(int index) {
        if ( index < 0 || index >= this.size() ) throw new NullPointerException();
        Node refNode = this.primero;
        Node ultimoRetornado;
        E element = null;
        if ( index == 0 ){
            element = refNode.contenido;
            refNode.sig.ant = this.ultimo;
            this.ultimo.sig = refNode.sig;
            this.primero = refNode.sig;
        }else if ( index == this.size - 1){
            refNode = this.ultimo;
            element = refNode.contenido;
            refNode.ant.sig = this.primero;
            this.primero.ant = refNode.ant;
            this.ultimo = refNode.ant;
        }else{
            ultimoRetornado = refNode;
            refNode = refNode.sig;
            for ( int i = 1; i <= index ; i++ ){
                if ( i == index ){
                    element = refNode.contenido;
                    refNode.sig.ant = ultimoRetornado;
                    ultimoRetornado.sig = refNode.sig;
                }
                ultimoRetornado = refNode;
                refNode = refNode.sig;
            }
        }
        this.size--;
        return element;
    }
    
    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIteratorPRS();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void sort(Comparator<? super E> c) {
        List.super.sort(c); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    @Override
    public String toString(){
        if( this.isEmpty() ) return null;
        
        String[] cadena = new String[this.size()];
        int c = 0;
        
        for (E e: this){
            cadena[c++] = String.valueOf(e);
        }
        
        return String.join(", ", cadena);
    }
    
}
