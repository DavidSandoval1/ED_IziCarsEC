/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyTDAs;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Pc
 * @param <E>
 */
public class LinkedListPRS<E> implements List<E> {
    private Node primero;
    private Node ultimo;
    
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
    
    protected class LinkedListIteratorPRS<E> implements Iterator<E>{
        LinkedListPRS<E>.Node act;
        
        LinkedListIteratorPRS(LinkedListPRS<E> list){
            act = list.primero;
        }

        @Override
        public boolean hasNext() {
            return act != null;
        }

        @Override
        public E next() {
            E element = act.contenido;
            act = act.sig;
            return element;
        }
    }

    @Override
    public int size() {
        if (this.isEmpty()) return 0;
        
        int count = 0;
        Node i = this.primero;
        do {
            count++;
        } while ( ( i = i.sig ) != null );
        return count;
    }

    @Override
    public boolean isEmpty() {
        return this.primero == null;
    }

    @Override
    public boolean contains(Object o) {        
        Node i = this.primero;
        while ( i != null ){
            if ( i.contenido.equals(o) ) return true;
            i = i.sig;
        }
        return false;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIteratorPRS(this);
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(E e) {
        if ( e == null ) return false;
        else if ( this.isEmpty() ){
            this.primero = new Node(e);
            this.ultimo = this.primero;
            return true;
        }
        Node iNode = this.primero;
        while ( iNode.sig != null ) iNode = iNode.sig;
        Node jNode = new Node(e);
        iNode.sig = jNode;
        jNode.ant = iNode;
        this.ultimo = jNode;
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
        for (E element: this) if (o.equals(element)) return true;
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
        Node iNode = this.primero;
        Node newNode = new Node(element);
        for ( int i = 0; i <= index; i++ ){
            if ( i == index ){
                if ( i == this.size() ) this.addLast(element);
                else if ( i == 0 ){
                    iNode.ant = newNode;
                    newNode.sig = iNode;
                    this.primero = newNode;
                } else {
                    Node befNode = iNode.ant;
                    newNode.ant = befNode;
                    befNode.sig = newNode;
                    newNode.sig = iNode;
                    iNode.ant = newNode;
                }
                break;
            }
            iNode = iNode.sig;
        }
    }
    
    @Override
    public E remove(int index) {
        if ( index < 0 || index >= this.size() ) throw new NullPointerException();
        Node iNode = this.primero;
        E element = null;
        if ( index == 0 ){
            element = iNode.contenido;
            iNode = iNode.sig;
            if ( this.size() > 1 ) iNode.ant = null;
            this.primero = iNode;
        } else if ( index == this.size() - 1 ) {
            Node jNode = this.ultimo;
            element = jNode.contenido;
            jNode = jNode.ant;
            jNode.sig = null;
            this.ultimo = jNode;
        } else {
            iNode = iNode.sig;
            for ( int i = 1; i <= index; i++ ){
                if ( i == index ){
                   element = iNode.contenido;
                   Node befNode = iNode.ant;
                   Node aftNode = iNode.sig;
                   befNode.sig = aftNode;
                   aftNode.ant = befNode;
                }
                
            }
        }
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public String toString(){
        if( this.isEmpty() ) return null;
        
        String[] cadena = new String[this.size()];
        int c = 0;
        
        for ( E e: this){
            cadena[c++] = String.valueOf(e);
        }
        
        return String.join(", ", cadena);
    }
    
}
