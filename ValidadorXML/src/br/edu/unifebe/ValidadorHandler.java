package br.edu.unifebe;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ValidadorHandler implements ErrorHandler {

	@Override
	public void warning(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("Avido de validação do XML : "+
		e.getMessage());
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("Ocorreu um problema na validação do XML na linha "+
		e.getLineNumber()+ " na coluna "+ e.getColumnNumber() +" : "+
				e.getMessage());
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("Ocorreu um erro grave na validação do XML : "+
		e.getMessage());
	}

}
