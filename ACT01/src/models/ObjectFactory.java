//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2019.02.10 a las 11:57:58 PM CET 
//


package models;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the models package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: models
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Modulos }
     * 
     */
    public Modulos createModulos() {
        return new Modulos();
    }

    /**
     * Create an instance of {@link Modulos.Modulo }
     * 
     */
    public Modulos.Modulo createModulosModulo() {
        return new Modulos.Modulo();
    }

    /**
     * Create an instance of {@link Modulos.Modulo.Alumno }
     * 
     */
    public Modulos.Modulo.Alumno createModulosModuloAlumno() {
        return new Modulos.Modulo.Alumno();
    }

}
