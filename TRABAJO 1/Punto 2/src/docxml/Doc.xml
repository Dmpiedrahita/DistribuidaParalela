/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docxml;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;


class docXML{


   public static void escribe(Document doc, String nombreArchivo) throws Exception{
        
        //Se especifica la fuente del archivo XML-BPEL
        Source fuente = new DOMSource(doc); 
        //se escribe el archivo (nombre archivo)
        Result result = new StreamResult(new java.io.File(nombreArchivo));
        
        //Se crea el objeto que permite transformar el documento en un archivo
        Transformer tranformador = TransformerFactory.newInstance ().newTransformer();
        tranformador.transform(fuente,result);//aqui lo crea fisicamente.
        tranformador.transform(fuente,new StreamResult(System.out));//aqui lo saca por consola
        System.out.println("");
        System.out.println("------------");
        System.out.println("construido, revise en la misma carpeta");
    }
    
    
        private static Document construye() throws Exception{//es de tipo Document porque returna el docuemnto al final
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//constructor de documentos
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        
        Document document = implementation.createDocument(null,"coleccion_cd",null);//crea el doc. raiz es el nombre de la raiz
         //aqui crea elementos no asociado a ningun padre
        Element gen = document.createElement("genero");//etiqueta
        Element alb = document.createElement("album");
        Element can = document.createElement("cancion");
        Element aut = document.createElement("autor");
         //crear atributos
        Attr atributo = document.createAttribute("id");
        atributo.setValue("1");
        Attr atributo2 = document.createAttribute("id");
        atributo2.setValue("1");
       //aqui los asocia al elemento 
        gen.setAttributeNode(atributo);
        alb.setAttributeNode(atributo2);
       //aqui crea nodos texto      
        Text text_alb = document.createTextNode("nombre");
        Text text_aut = document.createTextNode("nombre autor");
        //aqui asocia elemento gen a la raiz
        document.getDocumentElement().appendChild(gen);
        document.getDocumentElement().appendChild(can);
       //aqui le agrega hijos 
        gen.appendChild(alb);
        alb.appendChild(text_alb);

        can.appendChild(aut);
        aut.appendChild(text_aut);
    
        return document; 
    }


public static void main(String[] args) throws Exception {
	
 
 System.out.println("Programa que construye un documento XML");
 
 //llama el metodo escribe, mandandole como parametro lo que devuelve construye y una ruta para el archivo y el nombre
 docXML.escribe(construye(),".\\archivo.xml");
}


}
