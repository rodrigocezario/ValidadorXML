package br.edu.unifebe;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class AppExecuta {

	public static void main(String[] args) {

		// args[0].toString()

		Scanner sc = new Scanner(System.in);
		int escolha;
		String arquivo;
		System.out.println("Selecione (1) para parse para DTD\n" + "Selecione (2) para parse para XSD.");
		escolha = sc.nextInt();
		System.out.print("Informe o nome do arquivo xml com extensão: ");
		arquivo = sc.next();

		switch (escolha) {
		case 1:
			dtdParse(arquivo);
			break;
		case 2:
			xsdParse(arquivo);
			break;
		default:
			System.out.println("Opção de parse inválida...");
			break;
		}
	}

	public static void dtdParse(String arquivo) {
		try {
			// carregar o nome do arquivo .xml
			File arq = new File(arquivo);
			if (arq.exists()) {
				// vamos para o parser
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

				dbf.setValidating(true);

				DocumentBuilder db = dbf.newDocumentBuilder();

				Document doc = db.parse(arquivo);

				System.out.println("O arquivo " + arq.getName() + " é válido!");

			} else {
				System.out.println("Arquivo não encontrado...");
			}

		} catch (Exception e) {
			System.out.println("Erro ao carregar arquivo: " + e.getMessage());
		}
	}

	public static void xsdParse(String arquivo) {
		boolean valido = true;
		try {

			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setNamespaceAware(true);

			SchemaFactory sf = SchemaFactory.
					newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			Schema schema = sf.newSchema(new
					File(arquivo.substring(0, arquivo.length() - 4) + ".xsd"));
			spf.setSchema(schema);

			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			xr.setErrorHandler(new ValidadorHandler());

			InputSource xml = new
					InputSource(new FileReader(arquivo));
			xr.parse(xml);

		} catch (IOException | SAXException | ParserConfigurationException e) {
			System.out.println("Erro ao carregar arquivo: " +
					e.getMessage());
			valido = false;
		}

		if (valido)
			System.out.println("O arquivo " + arquivo + " é válido!");
		else
			System.out.println("O arquivo " + arquivo + " não é válido!");

	}

}
