package asktechforum.negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import asktechforum.dominio.Pergunta;
import asktechforum.dominio.ResultConsultarPergunta;
import asktechforum.exceptions.ExceptionPergunta;
import asktechforum.repositorio.CadastroPerguntasDAO;
import asktechforum.interfaces.CadastroPergunta;

public class CadastroPerguntaBC implements CadastroPergunta {

	private CadastroPerguntasDAO cadastro;
	private ArrayList<Pergunta> lstPergunta;
	private ArrayList<ResultConsultarPergunta> lstQtdPergunta;

	public CadastroPerguntaBC() {
		cadastro = new CadastroPerguntasDAO();
	}

	@Override
	public void adcionarPergunta(Pergunta pergunta)  {
		// TODO Auto-generated method stub
		try {
			if (pergunta.getData() == null) {
				
			} else if (pergunta.getDescricao().isEmpty()) {

			} else if (pergunta.getHora() == null) {

			} else if (pergunta.getTitulo().isEmpty()) {

			} else if (pergunta.getUsuario() == 0) {

			} else {
				cadastro.adcionarPergunta(pergunta);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deletarPergunta(int id) {
		// TODO Auto-generated method stub
		try {
			if (id == 0) {

			} else {
				cadastro.deletarPergunta(id);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Pergunta consultarPerguntaPorIdPergunta(int id) {

		Pergunta pergunta = new Pergunta();
		try {
			if (id == 0) {

			} else {
				pergunta = cadastro.consultarPerguntaPorIdPergunta(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pergunta;
	}

	@Override
	public ArrayList<Pergunta> consultarPerguntaIdUsuario(int id)
			throws SQLException {
		this.lstPergunta = new ArrayList<Pergunta>();
		try {

			if (id == 0) {

			} else {
				this.lstPergunta = cadastro.consultarPerguntaIdUsuario(id);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstPergunta;
	}

	@Override
	public ArrayList<Pergunta> consultarTodasPerguntas() {
		lstPergunta = new ArrayList<Pergunta>();
		try {

			this.lstPergunta = cadastro.consultarTodasPerguntas();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstPergunta;
	}

	@Override
	public ArrayList<ResultConsultarPergunta> consultarPerguntaPorTag(String tag){
		lstQtdPergunta = new ArrayList<ResultConsultarPergunta>();
		try {
			if(tag.equals("all")){
				this.lstQtdPergunta = cadastro.consultarPerguntaPorTodasTags();
			}else{
				this.lstQtdPergunta = cadastro.consultarPerguntaPorTag(tag);
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstQtdPergunta;
	}

	@Override
	public ArrayList<ResultConsultarPergunta> consultarPerguntaPorTodasTags()
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}