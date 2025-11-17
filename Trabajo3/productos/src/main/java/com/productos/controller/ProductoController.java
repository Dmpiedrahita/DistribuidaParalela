package com.productos.controller;

import com.productos.entity.Producto;
import com.productos.repository.ProductoRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoRepository repo;
    public ProductoController(ProductoRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Producto> listar(@RequestParam(required = false) String q, @RequestParam(required = false) String categoria) {
        if (q != null && !q.isEmpty()) return repo.findByNombreContainingIgnoreCase(q);
        if (categoria != null && !categoria.isEmpty()) return repo.findByCategoria(categoria);
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Producto obtener(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public Producto crear(@RequestBody Producto p) {
        return repo.save(p);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto datos) {
        Producto p = repo.findById(id).orElseThrow();
        p.setNombre(datos.getNombre());
        p.setDescripcion(datos.getDescripcion());
        p.setCategoria(datos.getCategoria());
        p.setValor(datos.getValor());
        p.setStock(datos.getStock());
        return repo.save(p);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { repo.deleteById(id); }

    @GetMapping(value="/reporte/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String generarXml() throws Exception {
        List<Producto> productos = repo.findAll();
        double total = productos.stream().mapToDouble(Producto::getValor).sum();


        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();


        Element root = doc.createElement("productos");
        doc.appendChild(root);


        Element meta = doc.createElement("meta");
        Element totalNode = doc.createElement("total_valor");
        totalNode.setTextContent(String.format("%.2f", total));
        meta.appendChild(totalNode);
        root.appendChild(meta);


        for (Producto p : productos) {
            Element node = doc.createElement("producto");
            node.setAttribute("id", p.getId().toString());


            Element nombre = doc.createElement("nombre"); nombre.setTextContent(p.getNombre()); node.appendChild(nombre);
            Element categoria = doc.createElement("categoria"); categoria.setTextContent(p.getCategoria() == null ? "" : p.getCategoria()); node.appendChild(categoria);
            Element valor = doc.createElement("valor"); valor.setTextContent(String.format("%.2f", p.getValor())); node.appendChild(valor);
            Element stock = doc.createElement("stock"); stock.setTextContent(String.valueOf(p.getStock())); node.appendChild(stock);


            double porcentaje = total != 0 ? (p.getValor() / total * 100) : 0;
            Element porc = doc.createElement("porcentaje"); porc.setTextContent(String.format("%.2f", porcentaje)); node.appendChild(porc);


            root.appendChild(node);
        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");


        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        return writer.toString();
    }
}
