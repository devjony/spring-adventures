package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Aluno;
import br.com.senac.service.AlunoService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("aluno")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;
	
	@GetMapping("/listarAlunos")
	public ModelAndView listaTodosAlunos() {
		ModelAndView mv = new ModelAndView("aluno/paginaListaAlunos");
		mv.addObject("alunos", alunoService.buscarTodosAlunos());
		return mv;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarAluno() {
		ModelAndView mv = new ModelAndView("aluno/paginaCadastraAluno");
		mv.addObject("aluno", new Aluno());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarAluno(Aluno aluno) {
		alunoService.salvar(aluno);
		return listaTodosAlunos();
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterarAluno(@PathVariable("id") Integer idAluno) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("aluno/paginaAlteraAluno");
		mv.addObject("aluno", alunoService.buscaPorId(idAluno));
		return mv;
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterar(Aluno alunoAlterado) throws ObjectNotFoundException {
		alunoService.salvarAteracao(alunoAlterado);
		return listaTodosAlunos();
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluirAluno(@PathVariable("id") Integer id) {
		alunoService.excluir(id);
		return listaTodosAlunos();
	}
}