package pe.com.lacunza.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.com.lacunza.models.entity.Cliente;

@Component("/views/clientes/listar")
public class PdfListarClientes extends AbstractPdfView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// DATA
		List<Cliente> listadoClientes = (List<Cliente>) model.get("clientes");
		
		// formato de la hoja
		document.setPageSize(PageSize.LETTER.rotate());
		document.setMargins(-20, -20, 30, 20);
		document.open();
		
		// TITUTLO DE LA HOJA con estilos
		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda = null;
		
		Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Color.BLUE);
		Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLUE);
		Font fuenteDataCeldas = FontFactory.getFont(FontFactory.HELVETICA, 11, Color.BLACK);
		
		celda = new PdfPCell(new Phrase("LISTADO GENERAL DE CLIENTES", fuenteTitulo));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(40,190,138));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(25);
		
		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);
		
		// LISTA DE3 CLIENTES (ESTILOS)
		PdfPTable tablaClientes = new PdfPTable(6);
		tablaClientes.setWidths(new float[] {0.8f, 2f, 2f, 1.5f, 3.5f, 1.5f});
		
		celda = new PdfPCell(new Phrase("Id", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaClientes.addCell(celda);
		
		celda = new PdfPCell(new Phrase("Nombres", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaClientes.addCell(celda);
		
		celda = new PdfPCell(new Phrase("Apellidos", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaClientes.addCell(celda);
		
		celda = new PdfPCell(new Phrase("Telefono", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaClientes.addCell(celda);
		
		celda = new PdfPCell(new Phrase("Email", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaClientes.addCell(celda);
		
		celda = new PdfPCell(new Phrase("Ciudad", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaClientes.addCell(celda);
		
		for(Cliente cliente : listadoClientes) {
			celda = new PdfPCell(new Phrase(cliente.getId().toString(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaClientes.addCell(celda);
			
			celda = new PdfPCell(new Phrase(cliente.getNombres(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaClientes.addCell(celda);
			
			celda = new PdfPCell(new Phrase(cliente.getApellidos(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaClientes.addCell(celda);
			
			celda = new PdfPCell(new Phrase(cliente.getTelefono(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaClientes.addCell(celda);
			
			celda = new PdfPCell(new Phrase(cliente.getEmail(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaClientes.addCell(celda);
			
			celda = new PdfPCell(new Phrase(cliente.getCiudad().getCiudad(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaClientes.addCell(celda);
		}
		
		// LISTA DE3 CLIENTES (DATA)
		/*PdfPTable tablaCliente = new PdfPTable(6);
		listadoClientes.forEach(cliente -> {
			tablaCliente.addCell(cliente.getId().toString());
			tablaCliente.addCell(cliente.getNombres());
			tablaCliente.addCell(cliente.getApellidos());
			tablaCliente.addCell(cliente.getTelefono());
			tablaCliente.addCell(cliente.getEmail());
			tablaCliente.addCell(cliente.getCiudad().getCiudad());
		});
		*/
		
		document.add(tablaTitulo);
		document.add(tablaClientes);
	}

}
